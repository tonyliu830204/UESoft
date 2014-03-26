package nl.justobjects.pushlet.util;

import java.io.PrintStream;
import nl.justobjects.pushlet.core.Config;
import nl.justobjects.pushlet.core.ConfigDefs;

public class Log
  implements ConfigDefs
{
  private static PushletLogger logger = new DefaultLogger();

  public static void init()
  {
    try
    {
      logger = (PushletLogger)Config.getClass("logger.class", "nl.justobjects.pushlet.util.DefaultLogger").newInstance();
    }
    catch (Throwable t) {
      System.out.println("Cannot instantiate Logger from config ex=" + t);
      return;
    }

    logger.init();

    logger.setLevel(Config.getIntProperty("log.level"));

    logger.info("Logging intialized logger class=" + logger.getClass());
  }

  public static void trace(String aMessage)
  {
    logger.debug(aMessage);
  }

  public static void debug(String aMessage)
  {
    logger.debug(aMessage);
  }

  public static void info(String aMessage)
  {
    logger.info(aMessage);
  }

  public static void warn(String aMessage)
  {
    logger.warn(aMessage);
  }

  public static void warn(String aMessage, Throwable aThrowable)
  {
    logger.warn(aMessage, aThrowable);
  }

  public static void error(String aMessage)
  {
    logger.error(aMessage);
  }

  public static void error(String aMessage, Throwable aThrowable)
  {
    logger.error(aMessage, aThrowable);
  }

  public static void fatal(String aMessage)
  {
    logger.fatal(aMessage);
  }

  public static void fatal(String aMessage, Throwable aThrowable)
  {
    logger.fatal(aMessage, aThrowable);
  }

  public static void setLevel(int aLevel)
  {
    logger.setLevel(aLevel);
  }
}