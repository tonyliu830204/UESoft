package nl.justobjects.pushlet.core;

import nl.justobjects.pushlet.util.Log;

public abstract class EventPullSource
  implements EventSource, Runnable
{
  private volatile boolean alive = false;
  private volatile boolean active = false;
  private static int threadNum = 0;
  private Thread thread;

  protected abstract long getSleepTime();

  protected abstract Event pullEvent();

  public void start()
  {
    this.thread = new Thread(this, "EventPullSource-" + ++threadNum);
    this.thread.setDaemon(true);
    this.thread.start();
  }

  public boolean isAlive() {
    return this.alive;
  }

  public void stop()
  {
    this.alive = false;

    if (this.thread != null) {
      this.thread.interrupt();
      this.thread = null;
    }
  }

  public synchronized void activate()
  {
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

  public void passivate()
  {
    if (!this.active) {
      return;
    }
    this.active = false;
  }

  public void run()
  {
    Log.debug(getClass().getName() + ": starting...");
    this.alive = true;
    while (this.alive)
    {
      try {
        Thread.sleep(getSleepTime());

        if (!this.alive)
        {
          break;
        }

        synchronized (this) {
          while (!this.active) {
            Log.debug(getClass().getName() + ": waiting...");
            wait();
          }
        }
      }
      catch (InterruptedException e)
      {
      }

      try
      {
        Event event = pullEvent();

        Dispatcher.getInstance().multicast(event);
      } catch (Throwable t) {
        Log.warn("EventPullSource exception while multicasting ", t);
        t.printStackTrace();
      }
    }
    Log.debug(getClass().getName() + ": stopped");
  }
}