package nl.justobjects.pushlet.util;

import nl.justobjects.pushlet.core.ConfigDefs;

public abstract interface PushletLogger extends ConfigDefs
{
  public abstract void init();

  public abstract void trace(String paramString);

  public abstract void debug(String paramString);

  public abstract void info(String paramString);

  public abstract void warn(String paramString);

  public abstract void warn(String paramString, Throwable paramThrowable);

  public abstract void error(String paramString);

  public abstract void error(String paramString, Throwable paramThrowable);

  public abstract void fatal(String paramString);

  public abstract void fatal(String paramString, Throwable paramThrowable);

  public abstract void setLevel(int paramInt);
}