package nl.justobjects.pushlet.util;

import java.io.PrintStream;

public class DefaultLogger
  implements PushletLogger
{
  private int level = 4;

  public void init()
  {
  }

  public void trace(String aMessage)
  {
    if (this.level < 6) {
      return;
    }
    print("TRACE", aMessage);
  }

  public void debug(String aMessage)
  {
    if (this.level < 5) {
      return;
    }
    print("DEBUG", aMessage);
  }

  public void info(String aMessage)
  {
    if (this.level < 4) {
      return;
    }
    print("INFO", aMessage);
  }

  public void warn(String aMessage)
  {
    if (this.level < 3) {
      return;
    }
    print("WARN", aMessage);
  }

  public void warn(String aMessage, Throwable aThrowable)
  {
    warn(aMessage + " exception=" + aThrowable);
  }

  public void error(String aMessage)
  {
    if (this.level < 2) {
      return;
    }
    print("FATAL", aMessage);
  }

  public void error(String aMessage, Throwable aThrowable)
  {
    error(aMessage + " exception=" + aThrowable);
  }

  public void fatal(String aMessage)
  {
    if (this.level < 1) {
      return;
    }
    print("FATAL", aMessage);
  }

  public void fatal(String aMessage, Throwable aThrowable)
  {
    fatal(aMessage + " exception=" + aThrowable);
  }

  public void setLevel(int aLevel)
  {
    this.level = aLevel;
  }

  private void print(String aTag, String aMessage)
  {
    System.out.println("Pushlet[" + aTag + "] " + aMessage);
  }
}