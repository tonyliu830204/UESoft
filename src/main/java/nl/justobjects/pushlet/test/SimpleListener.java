package nl.justobjects.pushlet.test;

import java.io.PrintStream;
import nl.justobjects.pushlet.client.PushletClient;
import nl.justobjects.pushlet.client.PushletClientListener;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.Protocol;
import nl.justobjects.pushlet.util.PushletException;

public class SimpleListener
  implements PushletClientListener, Protocol
{
  private static String SUBJECT = "/temperature";
  private static final String MODE = "stream";

  public SimpleListener(String aHost, int aPort)
  {
    try
    {
      PushletClient pushletClient = new PushletClient(aHost, aPort);
      pushletClient.setDebug(false);
      pushletClient.join();
      pushletClient.listen(this, "stream", SUBJECT);
      p("pushletClient started");
    } catch (PushletException pe) {
      p("Error in setting up pushlet session pe=" + pe);
    }
  }

  public void onError(String message)
  {
    p(message);
  }

  public void onAbort(Event theEvent)
  {
    p("onAbort received: " + theEvent);
  }

  public void onData(Event theEvent)
  {
    System.out.println(theEvent.toXML());
  }

  public void onHeartbeat(Event theEvent)
  {
    p("onHeartbeat received: " + theEvent);
  }

  public void p(String s)
  {
    System.out.println("[SimpleListener] " + s);
  }

  public static void main(String[] args)
  {
    if (args.length == 0) {
      new SimpleListener("localhost", 8080);
    }
    else if (args.length == 1) {
      SUBJECT = args[0];
      new SimpleListener("localhost", 8080);
    } else {
      SUBJECT = args[0];

      new SimpleListener(args[1], Integer.parseInt(args[2]));
    }
  }
}