package nl.justobjects.pushlet.core;

import nl.justobjects.pushlet.util.Log;
import nl.justobjects.pushlet.util.PushletException;

public class Session implements Protocol, ConfigDefs {
	private Controller controller;
	private Subscriber subscriber;
	private String userAgent;
	private long LEASE_TIME_MILLIS = Config
			.getLongProperty("session.timeout.mins") * 60L * 1000L;
	private volatile long timeToLive = this.LEASE_TIME_MILLIS;

	public static String[] FORCED_PULL_AGENTS = Config.getProperty(
			"listen.force.pull.agents").split(",");

	private String address = "unknown";
	private String format = "xml";
	private String id;

	public static Session create(String anId) throws PushletException {
		Session session;
		try {
			session = (Session) Config.getClass("session.class",
					"nl.justobjects.pushlet.core.Session").newInstance();
		} catch (Throwable t) {

			throw new PushletException(
					"Cannot instantiate Session from config", t);
		}
		session.id = anId;
		session.controller = Controller.create(session);
		session.subscriber = Subscriber.create(session);
		return session;
	}

	public String getAddress() {
		return this.address;
	}

	public Controller getController() {
		return this.controller;
	}

	public String getFormat() {
		return this.format;
	}

	public String getId() {
		return this.id;
	}

	public Subscriber getSubscriber() {
		return this.subscriber;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	protected void setAddress(String anAddress) {
		this.address = anAddress;
	}

	protected void setFormat(String aFormat) {
		this.format = aFormat;
	}

	public void setUserAgent(String aUserAgent) {
		this.userAgent = aUserAgent;
	}

	public void age(long aDeltaMillis) {
		this.timeToLive -= aDeltaMillis;
	}

	public boolean isExpired() {
		return this.timeToLive <= 0L;
	}

	public void kick() {
		this.timeToLive = this.LEASE_TIME_MILLIS;
	}

	public void start() {
		SessionManager.getInstance().addSession(this);
	}

	public void stop() {
		this.subscriber.stop();
		SessionManager.getInstance().removeSession(this);
	}

	public void info(String s) {
		Log.info("S-" + this + ": " + s);
	}

	public void warn(String s) {
		Log.warn("S-" + this + ": " + s);
	}

	public void debug(String s) {
		Log.debug("S-" + this + ": " + s);
	}

	public String toString() {
		return getAddress() + "[" + getId() + "]";
	}
}