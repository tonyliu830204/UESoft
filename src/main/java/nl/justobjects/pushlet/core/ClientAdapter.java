package nl.justobjects.pushlet.core;

import java.io.IOException;

public abstract interface ClientAdapter
{
  public abstract void start()
    throws IOException;

  public abstract void push(Event paramEvent)
    throws IOException;

  public abstract void stop()
    throws IOException;
}