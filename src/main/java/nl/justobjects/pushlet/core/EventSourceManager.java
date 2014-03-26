package nl.justobjects.pushlet.core;

import java.io.File;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import nl.justobjects.pushlet.util.Log;
import nl.justobjects.pushlet.util.Sys;

public class EventSourceManager
{
  private static Vector eventSources = new Vector(0);
  private static final String PROPERTIES_FILE = "sources.properties";

  public static void start(String aDirPath)
  {
    Log.info("EventSourceManager: start");

    Properties properties = null;
    try
    {
      properties = Sys.loadPropertiesResource("sources.properties");
    }
    catch (Throwable t) {
      String filePath = aDirPath + File.separator + "sources.properties";
      Log.info("EventSourceManager: cannot load sources.properties from classpath, will try from " + filePath);
      try
      {
        properties = Sys.loadPropertiesFile(filePath);
      } catch (Throwable t2) {
        Log.fatal("EventSourceManager: cannot load properties file from " + filePath, t);

        Log.warn("EventSourceManager: not starting local event sources (maybe that is what you want)");
        return;
      }

    }

    eventSources = new Vector(properties.size());

    for (Enumeration e = properties.keys(); e.hasMoreElements(); ) {
      String nextKey = (String)e.nextElement();
      String nextClass = properties.getProperty(nextKey);
      EventSource nextEventSource = null;
      try {
        nextEventSource = (EventSource)Class.forName(nextClass).newInstance();
        Log.info("created EventSource: key=" + nextKey + " class=" + nextClass);
        eventSources.addElement(nextEventSource);
      } catch (Exception ex) {
        Log.warn("Cannot create EventSource: class=" + nextClass, ex);
      }
    }

    activate();
  }

  public static void activate()
  {
    Log.info("Activating " + eventSources.size() + " EventSources");
    for (int i = 0; i < eventSources.size(); i++) {
      ((EventSource)eventSources.elementAt(i)).activate();
    }
    Log.info("EventSources activated");
  }

  public static void passivate()
  {
    Log.info("Passivating " + eventSources.size() + " EventSources");
    for (int i = 0; i < eventSources.size(); i++) {
      ((EventSource)eventSources.elementAt(i)).passivate();
    }
    Log.info("EventSources passivated");
  }

  public static void stop()
  {
    Log.info("Stopping " + eventSources.size() + " EventSources...");
    for (int i = 0; i < eventSources.size(); i++) {
      ((EventSource)eventSources.elementAt(i)).stop();
    }
    Log.info("EventSources stopped");
  }
}