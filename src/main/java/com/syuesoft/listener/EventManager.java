package com.syuesoft.listener;

import java.util.HashMap;
import java.util.Map;

public class EventManager
{
  private static EventManager em = new EventManager();

  private Map<String, CustomEvent.CustomEventSource> eventList = new HashMap();

  public static EventManager getInstance()
  {
    return em;
  }

  public Map<String, CustomEvent.CustomEventSource> getEventList()
  {
    return this.eventList;
  }

  public void createEvent(String eventName, String obj, int number)
  {
    CustomEvent.CustomEventSource cevent = new CustomEvent.CustomEventSource(eventName, obj, number);
    this.eventList.put(eventName, cevent);
    cevent.activate();
  }

  public void removeEvent(String eventName)
  {
    if (this.eventList.containsKey(eventName)) {
      CustomEvent.CustomEventSource cevent = new CustomEvent.CustomEventSource();
      cevent.stop();
      this.eventList.remove(eventName);
    }
  }
}