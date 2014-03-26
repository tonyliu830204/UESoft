package nl.justobjects.pushlet.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import nl.justobjects.pushlet.util.Log;
import nl.justobjects.pushlet.util.PushletException;

public class Dispatcher implements Protocol, ConfigDefs {
	private static Dispatcher instance;
	protected SessionManagerVisitor sessionManagerVisitor;

	static {
		try {
			instance = (Dispatcher) Config.getClass("dispatcher.class",
					"nl.justobjects.pushlet.core.Dispatcher").newInstance();
			Log.info("Dispatcher created className=" + instance.getClass());
		} catch (Throwable t) {
			Log.fatal("Cannot instantiate Dispatcher from config", t);
		}
	}

	public static Dispatcher getInstance() {
		return instance;
	}

	public synchronized void broadcast(Event anEvent) {
		try {
			Object[] args = new Object[2];
			args[1] = anEvent;
			Method method = this.sessionManagerVisitor
					.getMethod("visitBroadcast");
			SessionManager.getInstance().apply(this.sessionManagerVisitor,
					method, args);
		} catch (Throwable t) {
			Log.error("Error calling SessionManager.apply: ", t);
		}
	}

	public synchronized void multicast(Event anEvent) {
		try {
			Method method = this.sessionManagerVisitor
					.getMethod("visitMulticast");
			Object[] args = new Object[2];
			args[1] = anEvent;
			SessionManager.getInstance().apply(this.sessionManagerVisitor,
					method, args);
		} catch (Throwable t) {
			Log.error("Error calling SessionManager.apply: ", t);
		}
	}

	public synchronized void unicast(Event event, String aSessionId) {
		Session session = SessionManager.getInstance().getSession(aSessionId);
		if (session == null) {
			Log.warn("unicast: session with id=" + aSessionId
					+ " does not exist");
			return;
		}

		session.getSubscriber().onEvent((Event) event.clone());
	}

	public void start() throws PushletException {
		Log.info("Dispatcher started");

		this.sessionManagerVisitor = new SessionManagerVisitor();
	}

	public void stop() {
		Log.info("Dispatcher stopped: broadcast abort to all subscribers");
		broadcast(new Event("abort"));
	}

	private class SessionManagerVisitor {
		private final Map visitorMethods = new HashMap(2);

		SessionManagerVisitor() throws PushletException {
			Class[] argsClasses;
			try {
				argsClasses = new Class[] { Session.class, Event.class };
				this.visitorMethods.put("visitMulticast",
						getClass().getMethod("visitMulticast", argsClasses));
				this.visitorMethods.put("visitBroadcast",
						getClass().getMethod("visitBroadcast", argsClasses));
			} catch (NoSuchMethodException e) {

				throw new PushletException(
						"Failed to setup SessionManagerVisitor", e);
			}
		}

		public Method getMethod(String aName) {
			return (Method) this.visitorMethods.get(aName);
		}

		public void visitBroadcast(Session aSession, Event event) {
			aSession.getSubscriber().onEvent((Event) event.clone());
		}

		public void visitMulticast(Session aSession, Event event) {
			Subscriber subscriber = aSession.getSubscriber();
			Subscription subscription;
			if ((subscription = subscriber.match(event)) != null) {
				Event clonedEvent = (Event) event.clone();

				clonedEvent.setField("p_sid", subscription.getId());
				if (subscription.getLabel() != null) {
					event.setField("p_label", subscription.getLabel());
				}

				subscriber.onEvent(clonedEvent);
			}
		}
	}
}