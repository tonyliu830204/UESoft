package nl.justobjects.pushlet.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.server.UID;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import nl.justobjects.pushlet.util.Log;
import nl.justobjects.pushlet.util.PushletException;
import nl.justobjects.pushlet.util.Rand;
import nl.justobjects.pushlet.util.Sys;

public class SessionManager implements ConfigDefs {
	private static SessionManager instance;
	private Timer timer;
	private final long TIMER_INTERVAL_MILLIS = 60000L;

	private Map sessions = new HashMap(13);

	private Session[] sessionCache = new Session[0];

	private boolean sessionCacheDirty = false;

	private final Object mutex = new Object();

	static {
		try {
			instance = (SessionManager) Config.getClass("sessionmanager.class",
					"nl.justobjects.pushlet.core.SessionManager").newInstance();
			Log.info("SessionManager created className=" + instance.getClass());
		} catch (Throwable t) {
			Log.fatal("Cannot instantiate SessionManager from config", t);
		}
	}

	public void apply(Object visitor, Method method, Object[] args) {
		synchronized (this.mutex) {
			if (this.sessionCacheDirty) {
				for (int i = 0; i < this.sessionCache.length; i++) {
					this.sessionCache[i] = null;
				}

				this.sessionCache = ((Session[]) this.sessions.values()
						.toArray(this.sessionCache));
				this.sessionCacheDirty = false;
			}

			for (int i = 0; i < this.sessionCache.length; i++) {
				Session nextSession = this.sessionCache[i];

				if (nextSession == null) {
					break;
				}
				try {
					args[0] = nextSession;

					method.invoke(visitor, args);
				} catch (IllegalAccessException e) {
					Log.warn("apply: illegal method access: ", e);
				} catch (InvocationTargetException e) {
					Log.warn("apply: method invoke: ", e);
				}
			}
		}
	}

	public Session createSession(Event anEvent) throws PushletException {
		return Session.create(createSessionId());
	}

	public static SessionManager getInstance() {
		return instance;
	}

	public Session getSession(String anId) {
		synchronized (this.mutex) {
			return (Session) this.sessions.get(anId);
		}
	}

	public Session[] getSessions() {
		synchronized (this.mutex) {
			return (Session[]) this.sessions.values().toArray(new Session[0]);
		}
	}

	public int getSessionCount() {
		synchronized (this.mutex) {
			return this.sessions.size();
		}
	}

	public String getStatus() {
		Session[] sessions = getSessions();
		StringBuffer statusBuffer = new StringBuffer();
		statusBuffer.append("SessionMgr: " + sessions.length + " sessions \\n");
		for (int i = 0; i < sessions.length; i++) {
			statusBuffer.append(sessions[i] + "\\n");
		}
		return statusBuffer.toString();
	}

	public boolean hasSession(String anId) {
		synchronized (this.mutex) {
			return this.sessions.containsKey(anId);
		}
	}

	public void addSession(Session session) {
		synchronized (this.mutex) {
			this.sessions.put(session.getId(), session);
			this.sessionCacheDirty = true;
		}

		info(session.getId() + " at " + session.getAddress() + " added ");
	}

	public Session removeSession(Session aSession) {
		synchronized (this.mutex) {
			Session session = (Session) this.sessions.remove(aSession.getId());
			if (session != null) {
				info(session.getId() + " at " + session.getAddress()
						+ " removed ");
			}
			this.sessionCacheDirty = true;
			return session;
		}
	}

	public void start() throws PushletException {
		if (this.timer != null) {
			stop();
		}
		this.timer = new Timer(false);
		this.timer.schedule(new AgingTimerTask(), 60000L, 60000L);
		info("started; interval=60000ms");
	}

	public void stop() {
		if (this.timer != null) {
			this.timer.cancel();
			this.timer = null;
		}
		synchronized (this.mutex) {
			this.sessions.clear();
		}
		info("stopped");
	}

	protected String createSessionId() {
		if ((Config.hasProperty("session.id.generation"))
				&& (Config.getProperty("session.id.generation").equals("uuid"))) {
			return new UID().toString();
		}

		synchronized (this.mutex) {
			String id;
			do
				id = Rand.randomName(Config.getIntProperty("session.id.size"));
			while (hasSession(id));

			return id;
		}
	}

	protected void info(String s) {
		Log.info("SessionManager: " + new Date() + " " + s);
	}

	protected void warn(String s) {
		Log.warn("SessionManager: " + s);
	}

	protected void debug(String s) {
		Log.debug("SessionManager: " + s);
	}

	private class AgingTimerTask extends TimerTask {
		private long lastRun = Sys.now();
		private long delta;
		private Method visitMethod;

		public AgingTimerTask() throws PushletException {
			Class[] argsClasses;
			try {
				argsClasses = new Class[] { Session.class };
				this.visitMethod = getClass().getMethod("visit", argsClasses);
			} catch (NoSuchMethodException e) {

				throw new PushletException("Failed to setup AgingTimerTask", e);
			}
		}

		public void run() {
			long now = Sys.now();
			this.delta = (now - this.lastRun);
			this.lastRun = now;
			SessionManager.this.debug("AgingTimerTask: tick");

			SessionManager.getInstance().apply(this, this.visitMethod,
					new Object[1]);
		}

		public void visit(Session aSession) {
			try {
				aSession.age(this.delta);
				SessionManager.this.debug("AgingTimerTask: visit: " + aSession);

				if (aSession.isExpired()) {
					SessionManager.this
							.info("AgingTimerTask: Session expired: "
									+ aSession);
					aSession.stop();
				}
			} catch (Throwable t) {
				SessionManager.this
						.warn("AgingTimerTask: Error in timer task : " + t);
			}
		}
	}
}