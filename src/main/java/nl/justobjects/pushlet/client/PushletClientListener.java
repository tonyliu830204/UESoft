package nl.justobjects.pushlet.client;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.Protocol;

public abstract interface PushletClientListener extends Protocol
{
  public abstract void onAbort(Event paramEvent);

  public abstract void onData(Event paramEvent);

  public abstract void onHeartbeat(Event paramEvent);

  public abstract void onError(String paramString);
}