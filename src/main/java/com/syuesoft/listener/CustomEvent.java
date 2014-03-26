package com.syuesoft.listener;

import java.io.Serializable;
import java.net.URLEncoder;
import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;
import nl.justobjects.pushlet.util.Log;

public class CustomEvent
  implements Serializable
{
  private static final long serialVersionUID = 1L;

  public static class CustomEventSource extends EventPullSource
  {
    private volatile boolean alive = false;
    private volatile boolean active = false;
    private static int threadNum = 0;
    private Thread thread;
    private long sleepTime = 1000L;
    private String eventName = "/qiangbi/xixian/";
    private String msg = "推送";
    private int number = 1;

    public CustomEventSource()
    {
    }

    public CustomEventSource(String eventName, String obj, int number) {
      this.eventName = eventName;
      this.msg = obj;
      this.number = number;
    }

    protected long getSleepTime()
    {
      return this.sleepTime;
    }

    public void start() {
      this.thread = new Thread(this, "EventPullSource-" + ++threadNum);
      this.thread.setDaemon(true);
      this.thread.start();
    }

    public boolean isAlive() {
      return this.alive;
    }

    public void stop() {
      this.alive = false;
      if (this.thread != null) {
        this.thread.interrupt();
        this.thread = null;
      }
    }

    public synchronized void activate() {
      if (this.active) {
        return;
      }
      this.active = true;
      if (!this.alive) {
        start();
        return;
      }
      Log.debug(getClass().getName() + ": notifying...");
      notifyAll();
    }

    public void passivate() {
      if (!this.active) {
        return;
      }
      this.active = false;
    }

    public void run() {
      Log.debug(getClass().getName() + ": starting...");
      this.alive = true;
      if (this.number == 0) {
        while (this.alive) {
          multicast();
        }
      }
      else if (this.alive) {
        for (int i = 0; i < this.number; i++) {
          multicast();
        }
      }

      Log.debug(getClass().getName() + ": stopped");
    }

    private void multicast() {
      try {
        Thread.sleep(this.sleepTime);
        synchronized (this) {
          while (!this.active) {
            Log.debug(getClass().getName() + ": waiting...");
            wait();
          }
        }
      } catch (InterruptedException e) {
        Log.error("推送异常", e);
        try
        {
          Event event = pullEvent();
          Dispatcher.getInstance().multicast(event);
        } catch (Throwable t) {
          Log.warn("EventPullSource exception while multicasting ", t);
        }
      }
    }

    protected Event pullEvent() {
      Event event = Event.createDataEvent(this.eventName);
      try
      {
        event.setField("key1", URLEncoder.encode(this.msg.toString().trim(), "UTF-8"));
      } catch (Exception e) {
        Log.error("EventPullSource exception while multicasting ", e);
      }
      return event;
    }
  }
}