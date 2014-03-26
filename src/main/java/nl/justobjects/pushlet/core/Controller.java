package nl.justobjects.pushlet.core;

import java.io.IOException;

import nl.justobjects.pushlet.util.PushletException;

public class Controller implements Protocol, ConfigDefs {
	private Session session;

	public static Controller create(Session aSession) throws PushletException {
		Controller controller;
		try {
			controller = (Controller) Config.getClass("controller.class",
					"nl.justobjects.pushlet.core.Controller").newInstance();
		} catch (Throwable t) {

			throw new PushletException(
					"Cannot instantiate Controller from config", t);
		}
		controller.session = aSession;
		return controller;
	}

	public void doCommand(Command aCommand) {
		try {
			this.session.kick();

			this.session.setAddress(aCommand.httpReq.getRemoteAddr());

			debug("doCommand() event=" + aCommand.reqEvent);

			String eventType = aCommand.reqEvent.getEventType();

			if (eventType.equals("refresh")) {
				doRefresh(aCommand);
			} else if (eventType.equals("subscribe")) {
				doSubscribe(aCommand);
			} else if (eventType.equals("unsubscribe")) {
				doUnsubscribe(aCommand);
			} else if (eventType.equals("join")) {
				doJoin(aCommand);
			} else if (eventType.equals("join-listen")) {
				doJoinListen(aCommand);
			} else if (eventType.equals("leave")) {
				doLeave(aCommand);
			} else if (eventType.equals("hb")) {
				doHeartbeat(aCommand);
			} else if (eventType.equals("publish")) {
				doPublish(aCommand);
			} else if (eventType.equals("listen")) {
				doListen(aCommand);
			}

			if ((eventType.endsWith("listen")) || (eventType.equals("refresh"))) {
				getSubscriber().fetchEvents(aCommand);
			} else {
				sendControlResponse(aCommand);
			}
		} catch (Throwable t) {
			warn("Exception in doCommand(): " + t);
			t.printStackTrace();
		}
	}

	public String toString() {
		return this.session.toString();
	}

	protected void doHeartbeat(Command aCommand) {
		aCommand.setResponseEvent(new Event("hb-ack"));
	}

	protected void doJoin(Command aCommand) throws PushletException {
		Event responseEvent = null;
		try {
			this.session.start();

			String format = aCommand.reqEvent.getField("p_format", "js");

			this.session.setFormat(format);
			responseEvent = new Event("join-ack");

			responseEvent.setField("p_id", this.session.getId());
			responseEvent.setField("p_format", format);
			info("joined");
		} catch (Throwable t) {
			this.session.stop();
			responseEvent = new Event("nack");
			responseEvent.setField("p_id", this.session.getId());
			responseEvent.setField("p_reason", "unexpected error: " + t);
			warn("doJoin() error: " + t);
			t.printStackTrace();
		} finally {
			aCommand.setResponseEvent(responseEvent);
		}
	}

	protected void doJoinListen(Command aCommand) throws PushletException {
		doJoin(aCommand);
		if (!aCommand.getResponseEvent().getEventType().equals("nack")) {
			doListen(aCommand);
			if (!aCommand.getResponseEvent().getEventType().equals("nack")) {
				aCommand.getResponseEvent().setField("p_event",
						"join-listen-ack");
			}
		}
	}

	protected void doLeave(Command aCommand) throws IOException {
		Event responseEvent = null;
		try {
			this.session.stop();

			responseEvent = new Event("leave-ack");

			responseEvent.setField("p_id", this.session.getId());
			info("left");
		} catch (Throwable t) {
			responseEvent = new Event("nack");
			responseEvent.setField("p_id", this.session.getId());
			responseEvent.setField("p_reason", "unexpected error: " + t);
			warn("doLeave() error: " + t);
			t.printStackTrace();
		} finally {
			aCommand.setResponseEvent(responseEvent);
		}
	}

	protected void doListen(Command aCommand) throws PushletException {
		String mode = "stream";

		if (Config.getBoolProperty("listen.force.pull.all")) {
			mode = "pull";
		} else {
			mode = aCommand.reqEvent.getField("p_mode", "stream");

			String userAgent = aCommand.httpReq.getHeader("User-Agent");
			if (userAgent != null) {
				userAgent = userAgent.toLowerCase();
				for (int i = 0; i < Session.FORCED_PULL_AGENTS.length; i++)
					if (userAgent.indexOf(Session.FORCED_PULL_AGENTS[i]) != -1) {
						info("Forcing pull mode for agent=" + userAgent);
						mode = "pull";
						break;
					}
			} else {
				userAgent = "unknown";
			}
		}

		getSubscriber().setMode(mode);

		Event listenAckEvent = new Event("listen-ack");

		String subject = aCommand.reqEvent.getField("p_subject");
		if (subject != null) {
			String label = aCommand.reqEvent.getField("p_label");

			Subscription subscription = getSubscriber().addSubscription(
					subject, label);

			listenAckEvent.setField("p_sid", subscription.getId());
			if (label != null) {
				listenAckEvent.setField("p_label", label);
			}

		}

		listenAckEvent.setField("p_id", this.session.getId());
		listenAckEvent.setField("p_mode", mode);
		listenAckEvent.setField("p_format", this.session.getFormat());

		getSubscriber().start();

		aCommand.setResponseEvent(listenAckEvent);

		info("Listening mode=" + mode + " userAgent="
				+ this.session.getUserAgent());
	}

	protected void doPublish(Command aCommand) {
		Event responseEvent = null;
		try {
			String subject = aCommand.reqEvent.getField("p_subject");
			if (subject == null) {
				responseEvent = new Event("nack");
				responseEvent.setField("p_id", this.session.getId());
				responseEvent.setField("p_reason", "no subject provided");
			} else {
				aCommand.reqEvent.setField("p_from", this.session.getId());
				aCommand.reqEvent.setField("p_event", "data");

				String to = aCommand.reqEvent.getField("p_to");
				if (to != null) {
					Dispatcher.getInstance().unicast(aCommand.reqEvent, to);
				} else {
					debug("doPublish() event=" + aCommand.reqEvent);
					Dispatcher.getInstance().multicast(aCommand.reqEvent);
				}

				responseEvent = new Event("publish-ack");
			}
		} catch (Throwable t) {
			responseEvent = new Event("nack");
			responseEvent.setField("p_id", this.session.getId());
			responseEvent.setField("p_reason", "unexpected error: " + t);
			warn("doPublish() error: " + t);
			t.printStackTrace();
		} finally {
			aCommand.setResponseEvent(responseEvent);
		}
	}

	protected void doRefresh(Command aCommand) {
		aCommand.setResponseEvent(new Event("refresh-ack"));
	}

	protected void doSubscribe(Command aCommand) throws IOException {
		Event responseEvent = null;
		try {
			String subject = aCommand.reqEvent.getField("p_subject");
			Subscription subscription = null;
			if (subject == null) {
				responseEvent = new Event("nack");
				responseEvent.setField("p_id", this.session.getId());
				responseEvent.setField("p_reason", "no subject provided");
			} else {
				String label = aCommand.reqEvent.getField("p_label");
				subscription = getSubscriber().addSubscription(subject, label);

				responseEvent = new Event("subscribe-ack");
				responseEvent.setField("p_id", this.session.getId());
				responseEvent.setField("p_subject", subject);
				responseEvent.setField("p_sid", subscription.getId());
				if (label != null) {
					responseEvent.setField("p_label", label);
				}
				info("subscribed to " + subject + " sid="
						+ subscription.getId());
			}
		} catch (Throwable t) {
			responseEvent = new Event("nack");
			responseEvent.setField("p_id", this.session.getId());
			responseEvent.setField("p_reason", "unexpected error: " + t);
			warn("doSubscribe() error: " + t);
			t.printStackTrace();
		} finally {
			aCommand.setResponseEvent(responseEvent);
		}
	}

	protected void doUnsubscribe(Command aCommand) throws IOException {
		Event responseEvent = null;
		try {
			String subscriptionId = aCommand.reqEvent.getField("p_sid");
			if (subscriptionId == null) {
				getSubscriber().removeSubscriptions();
				responseEvent = new Event("unsubscribe-ack");
				responseEvent.setField("p_id", this.session.getId());
				info("unsubscribed all");
			} else {
				Subscription subscription = getSubscriber().removeSubscription(
						subscriptionId);
				if (subscription == null) {
					responseEvent = new Event("nack");
					responseEvent.setField("p_id", this.session.getId());
					responseEvent.setField("p_reason",
							"no subscription for sid=" + subscriptionId);
					warn("unsubscribe: no subscription for sid="
							+ subscriptionId);
				} else {
					responseEvent = new Event("unsubscribe-ack");
					responseEvent.setField("p_id", this.session.getId());
					responseEvent.setField("p_sid", subscription.getId());
					responseEvent.setField("p_subject",
							subscription.getSubject());
					if (subscription.getLabel() != null) {
						responseEvent.setField("p_label",
								subscription.getLabel());
					}
					info("unsubscribed sid= " + subscriptionId);
				}
			}
		} catch (Throwable t) {
			responseEvent = new Event("nack");
			responseEvent.setField("p_id", this.session.getId());
			responseEvent.setField("p_reason", "unexpected error: " + t);
			warn("doUnsubscribe() error: " + t);
			t.printStackTrace();
		} finally {
			aCommand.setResponseEvent(responseEvent);
		}
	}

	public Subscriber getSubscriber() {
		return this.session.getSubscriber();
	}

	protected void sendControlResponse(Command aCommand) {
		try {
			aCommand.sendResponseHeaders();

			aCommand.getClientAdapter().start();

			aCommand.getClientAdapter().push(aCommand.getResponseEvent());

			aCommand.getClientAdapter().stop();
		} catch (Throwable t) {
			this.session.stop();
		}
	}

	protected void info(String s) {
		this.session.info("[Controller] " + s);
	}

	protected void warn(String s) {
		this.session.warn("[Controller] " + s);
	}

	protected void debug(String s) {
		this.session.debug("[Controller] " + s);
	}
}