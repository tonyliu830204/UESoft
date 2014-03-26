package nl.justobjects.pushlet.core;

import nl.justobjects.pushlet.util.PushletException;
import nl.justobjects.pushlet.util.Rand;

public class Subscription implements ConfigDefs {
	public static final int ID_SIZE = 5;
	public static final String SUBJECT_SEPARATOR = ",";
	private String id = Rand.randomName(5);
	private String subject;
	private String[] subjects;
	private String label;

	public static Subscription create(String aSubject) throws PushletException {
		return create(aSubject, null);
	}

	public static Subscription create(String aSubject, String aLabel)
			throws PushletException {
		if ((aSubject == null) || (aSubject.length() == 0)) {
			throw new IllegalArgumentException("Null or emtpy subject");
		}
		Subscription subscription;
		try {
			subscription = (Subscription) Config.getClass("subscription.class",
					"nl.justobjects.pushlet.core.Subscription").newInstance();
		} catch (Throwable t) {

			throw new PushletException(
					"Cannot instantiate Subscriber from config", t);
		}
		subscription.subject = aSubject;

		subscription.subjects = aSubject.split(",");

		subscription.label = aLabel;
		return subscription;
	}

	public String getId() {
		return this.id;
	}

	public String getLabel() {
		return this.label;
	}

	public String getSubject() {
		return this.subject;
	}

	public boolean match(Event event) {
		String eventSubject = event.getSubject();

		if ((eventSubject == null) || (eventSubject.length() == 0)) {
			return false;
		}

		for (int i = 0; i < this.subjects.length; i++) {
			if (eventSubject.startsWith(this.subjects[i])) {
				return true;
			}

		}

		return false;
	}
}