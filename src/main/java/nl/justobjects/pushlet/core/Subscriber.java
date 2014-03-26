package nl.justobjects.pushlet.core;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.justobjects.pushlet.util.PushletException;
import nl.justobjects.pushlet.util.Rand;
import nl.justobjects.pushlet.util.Sys;

public class Subscriber implements Protocol, ConfigDefs {
	private Session session;
	private EventQueue eventQueue = new EventQueue(
			Config.getIntProperty("queue.size"));

	private long queueReadTimeoutMillis = Config
			.getLongProperty("queue.read.timeout.millis");
	private long queueWriteTimeoutMillis = Config
			.getLongProperty("queue.write.timeout.millis");
	private long refreshTimeoutMillis = Config
			.getLongProperty("pull.refresh.timeout.millis");
	volatile long lastAlive = Sys.now();

	private Map subscriptions = Collections.synchronizedMap(new HashMap(3));
	private volatile boolean active;
	private String mode;

	public static Subscriber create(Session aSession) throws PushletException {
		Subscriber subscriber;
		try {
			subscriber = (Subscriber) Config.getClass("subscriber.class",
					"nl.justobjects.pushlet.core.Subscriber").newInstance();
		} catch (Throwable t) {

			throw new PushletException(
					"Cannot instantiate Subscriber from config", t);
		}
		subscriber.session = aSession;
		return subscriber;
	}

	public void start() {
		this.active = true;
	}

	public void stop() {
		removeSubscriptions();
		this.active = false;
	}

	public void bailout() {
		this.session.stop();
	}

	public boolean isActive() {
		return this.active;
	}

	public Session getSession() {
		return this.session;
	}

	public String getId() {
		return this.session.getId();
	}

	public Subscription[] getSubscriptions() {
		return (Subscription[]) this.subscriptions.values().toArray(
				new Subscription[0]);
	}

	public Subscription addSubscription(String aSubject, String aLabel)
			throws PushletException {
		Subscription subscription = Subscription.create(aSubject, aLabel);
		this.subscriptions.put(subscription.getId(), subscription);
		info("Subscription added subject=" + aSubject + " sid="
				+ subscription.getId() + " label=" + aLabel);
		return subscription;
	}

	public Subscription removeSubscription(String aSubscriptionId) {
		Subscription subscription = (Subscription) this.subscriptions
				.remove(aSubscriptionId);
		if (subscription == null) {
			warn("No subscription found sid=" + aSubscriptionId);
			return null;
		}
		info("Subscription removed subject=" + subscription.getSubject()
				+ " sid=" + subscription.getId() + " label="
				+ subscription.getLabel());
		return subscription;
	}

	public void removeSubscriptions() {
		this.subscriptions.clear();
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String aMode) {
		this.mode = aMode;
	}

	public long getRefreshTimeMillis() {
		String minWaitProperty = "pull.refresh.wait.min.millis";
		String maxWaitProperty = "pull.refresh.wait.max.millis";
		if (this.mode.equals("poll")) {
			minWaitProperty = "poll.refresh.wait.min.millis";
			maxWaitProperty = "poll.refresh.wait.max.millis";
		}

		return Rand.randomLong(Config.getLongProperty(minWaitProperty),
				Config.getLongProperty(maxWaitProperty));
	}

	public void fetchEvents(Command aCommand) throws PushletException {
		String refreshURL = aCommand.httpReq.getRequestURI() + "?" + "p_id"
				+ "=" + this.session.getId() + "&" + "p_event" + "="
				+ "refresh";

		if (this.mode.equals("poll")) {
			this.queueReadTimeoutMillis = 0L;
			this.refreshTimeoutMillis = Config
					.getLongProperty("poll.refresh.timeout.millis");
		}

		aCommand.httpRsp.setBufferSize(128);

		aCommand.sendResponseHeaders();

		ClientAdapter clientAdapter = aCommand.getClientAdapter();
		Event responseEvent = aCommand.getResponseEvent();
		try {
			clientAdapter.start();

			clientAdapter.push(responseEvent);

			if (((this.mode.equals("poll")) || (this.mode.equals("pull")))
					&& (responseEvent.getEventType().endsWith("listen-ack"))) {
				sendRefresh(clientAdapter, refreshURL);

				return;
			}
		} catch (Throwable t) {
			bailout();
			return;
		}

		Event[] events = (Event[]) null;

		long eventSeqNr = 1L;
		while (isActive()) {
			this.lastAlive = Sys.now();

			this.session.kick();
			try {
				if ((this.mode.equals("stream")) && (eventSeqNr == 1L)) {
					this.eventQueue.enQueue(new Event("hb"));
				}

				events = this.eventQueue
						.deQueueAll(this.queueReadTimeoutMillis);
			} catch (InterruptedException ie) {
				warn("interrupted");
				bailout();
			}

			if (events == null) {
				events = new Event[1];
				events[0] = new Event("hb");
			}

			for (int i = 0; i < events.length; i++) {
				if (events[i].getEventType().equals("abort")) {
					warn("Aborting Subscriber");
					bailout();
				}

				try {
					events[i].setField("p_seq", eventSeqNr++);

					clientAdapter.push(events[i]);
				} catch (Throwable t) {
					bailout();
					return;
				}

			}

			if ((this.mode.equals("pull")) || (this.mode.equals("poll"))) {
				sendRefresh(clientAdapter, refreshURL);

				break;
			}
		}
	}

	public Subscription match(Event event) {
		Subscription[] subscriptions = getSubscriptions();
		for (int i = 0; i < subscriptions.length; i++) {
			if (subscriptions[i].match(event)) {
				return subscriptions[i];
			}
		}
		return null;
	}

	public void onEvent(Event theEvent) {
		if (!isActive()) {
			return;
		}

		long now = Sys.now();
		if (now - this.lastAlive > this.refreshTimeoutMillis) {
			warn("not alive for at least: " + this.refreshTimeoutMillis
					+ "ms, leaving...");
			bailout();
			return;
		}

		try {
			if (!this.eventQueue
					.enQueue(theEvent, this.queueWriteTimeoutMillis)) {
				warn("queue full, bailing out...");
				bailout();
			}

		} catch (InterruptedException ie) {
			bailout();
		}
	}

	protected void sendRefresh(ClientAdapter aClientAdapter, String aRefreshURL) {
		Event refreshEvent = new Event("refresh");

		refreshEvent.setField("p_wait", getRefreshTimeMillis());
		refreshEvent.setField("p_url", aRefreshURL);
		try {
			aClientAdapter.push(refreshEvent);

			aClientAdapter.stop();
		} catch (Throwable t) {
			bailout();
		}
	}

	protected void info(String s) {
		this.session.info("[Subscriber] " + s);
	}

	protected void warn(String s) {
		this.session.warn("[Subscriber] " + s);
	}

	protected void debug(String s) {
		this.session.debug("[Subscriber] " + s);
	}

	public String toString() {
		return this.session.toString();
	}
}