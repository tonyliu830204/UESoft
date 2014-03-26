package nl.justobjects.pushlet.util;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4jLogger
  implements PushletLogger
{
  private Logger logger = LogManager.getLogger("pushlet");

  public void init()
  {
    setLevel(4);
  }

  public void debug(String aMessage)
  {
    if (!this.logger.isDebugEnabled()) {
      return;
    }
    this.logger.debug(aMessage);
  }

  public void error(String aMessage)
  {
    this.logger.error(aMessage);
  }

  public void error(String aMessage, Throwable aThrowable)
  {
    this.logger.error(aMessage, aThrowable);
  }

  public void fatal(String aMessage)
  {
    this.logger.fatal(aMessage);
  }

  public void fatal(String aMessage, Throwable aThrowable)
  {
    this.logger.fatal(aMessage, aThrowable);
  }

  public void info(String aMessage)
  {
    if (!this.logger.isInfoEnabled()) {
      return;
    }
    this.logger.info(aMessage);
  }

  public void trace(String aMessage)
  {
    this.logger.trace(aMessage);
  }

  public void warn(String aMessage)
  {
    this.logger.warn(aMessage);
  }

  public void warn(String aMessage, Throwable aThrowable)
  {
    this.logger.warn(aMessage, aThrowable);
  }

  public void setLevel(int aLevel)
  {
    if (aLevel < 1)
      this.logger.setLevel(Level.OFF);
    else
      switch (aLevel) {
      case 1:
        this.logger.setLevel(Level.FATAL);
        break;
      case 2:
        this.logger.setLevel(Level.ERROR);
        break;
      case 3:
        this.logger.setLevel(Level.WARN);
        break;
      case 4:
        this.logger.setLevel(Level.INFO);
        break;
      case 5:
        this.logger.setLevel(Level.DEBUG);
        break;
      case 6:
        this.logger.setLevel(Level.TRACE);
        break;
      default:
        this.logger.setLevel(Level.INFO);
      }
  }
}