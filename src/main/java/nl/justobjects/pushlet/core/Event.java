package nl.justobjects.pushlet.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import nl.justobjects.pushlet.util.Sys;

public class Event
  implements Protocol, Serializable
{
  protected Map attributes = new HashMap(3);

  public Event(String anEventType) {
    this(anEventType, null);
  }

  public Event(String anEventType, Map theAttributes)
  {
    if (theAttributes != null) {
      setAttrs(theAttributes);
    }

    setField("p_event", anEventType);

    setField("p_time", System.currentTimeMillis() / 1000L);
  }

  public Event(Map theAttributes) {
    if (!theAttributes.containsKey("p_event")) {
      throw new IllegalArgumentException("p_event not found in attributes");
    }
    setAttrs(theAttributes);
  }

  public static Event createDataEvent(String aSubject) {
    return createDataEvent(aSubject, null);
  }

  public static Event createDataEvent(String aSubject, Map theAttributes) {
    Event dataEvent = new Event("data", theAttributes);
    dataEvent.setField("p_subject", aSubject);
    return dataEvent;
  }

  public String getEventType() {
    return getField("p_event");
  }

  public String getSubject() {
    return getField("p_subject");
  }

  public void setField(String name, String value) {
    this.attributes.put(name, value);
  }

  public void setField(String name, int value) {
    this.attributes.put(name, value);
  }

  public void setField(String name, long value) {
    this.attributes.put(name, value);
  }

  public String getField(String name) {
    return (String)this.attributes.get(name);
  }

  public String getField(String name, String aDefault)
  {
    String result = getField(name);
    return result == null ? aDefault : result;
  }

  public Iterator getFieldNames() {
    return this.attributes.keySet().iterator();
  }

  public String toString() {
    return this.attributes.toString();
  }

  public String toQueryString()
  {
    String queryString = "";
    String amp = "";
    for (Iterator iter = getFieldNames(); iter.hasNext(); ) {
      String nextAttrName = (String)iter.next();
      String nextAttrValue = getField(nextAttrName);
      queryString = queryString + amp + nextAttrName + "=" + nextAttrValue;

      amp = "&";
    }

    return queryString;
  }

  public String toXML(boolean strict) {
    String xmlString = "<event ";
    for (Iterator iter = getFieldNames(); iter.hasNext(); ) {
      String nextAttrName = (String)iter.next();
      String nextAttrValue = getField(nextAttrName);
      xmlString = xmlString + nextAttrName + "=\"" + (strict ? Sys.forHTMLTag(nextAttrValue) : nextAttrValue) + "\" ";
    }

    xmlString = xmlString + "/>";
    return xmlString;
  }

  public String toXML() {
    return toXML(false);
  }

  public Object clone()
  {
    return new Event(this.attributes);
  }

  private void setAttrs(Map theAttributes)
  {
    this.attributes.putAll(theAttributes);
  }
}