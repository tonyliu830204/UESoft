package nl.justobjects.pushlet.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventParser;
import nl.justobjects.pushlet.core.Protocol;
import nl.justobjects.pushlet.util.PushletException;

public class PushletClient
  implements Protocol
{
  private String pushletURL;
  private boolean debug;
  private String id;
  protected DataEventListener dataEventListener;

  public PushletClient(String aPushletURL)
  {
    this.pushletURL = aPushletURL;
  }

  public PushletClient(String aHost, int aPort)
  {
    this("http://" + aHost + ":" + aPort + "/pushlet/pushlet.srv");
  }

  public void setProxyOptions(String aProxyHost, String aProxyPort, String theNonProxyHosts, String aUserName, String aPassword, String anNTLMDomain)
  {
    System.setProperty("http.proxySet", "true");
    System.setProperty("http.proxyHost", aProxyHost);
    System.setProperty("http.proxyPort", aProxyPort);

    if (theNonProxyHosts != null) {
      System.setProperty("http.nonProxyHosts", theNonProxyHosts);
    }

    if (aUserName != null) {
      System.setProperty("http.proxyUser", aUserName);
      System.setProperty("http.proxyPassword", aPassword);

      Authenticator.setDefault(new HTTPAuthenticateProxy(aUserName, aPassword));

      if (anNTLMDomain != null)
        System.setProperty("http.auth.ntlm.domain", anNTLMDomain);
    }
  }

  public void join()
    throws PushletException
  {
    Event event = new Event("join");
    event.setField("p_format", "xml");
    Event response = doControl(event);
    throwOnNack(response);

    this.id = response.getField("p_id");
  }

  public void leave()
    throws PushletException
  {
    stopListen();
    throwOnInvalidSession();
    Event event = new Event("leave");
    event.setField("p_id", this.id);
    Event response = doControl(event);

    throwOnNack(response);
    this.id = null;
  }

  public void listen(PushletClientListener aListener)
    throws PushletException
  {
    listen(aListener, "stream");
  }

  public void listen(PushletClientListener aListener, String aMode)
    throws PushletException
  {
    listen(aListener, aMode, null);
  }

  public void listen(PushletClientListener aListener, String aMode, String aSubject)
    throws PushletException
  {
    throwOnInvalidSession();
    stopListen();

    String listenURL = this.pushletURL + 
      "?" + "p_event" + "=" + "listen" + 
      "&" + "p_id" + "=" + this.id + 
      "&" + "p_mode" + "=" + aMode;
    if (aSubject != null) {
      listenURL = listenURL + "&" + "p_subject" + "=" + aSubject;
    }

    startDataEventListener(aListener, listenURL);
  }

  public void joinListen(PushletClientListener aListener, String aMode, String aSubject)
    throws PushletException
  {
    stopListen();

    String listenURL = this.pushletURL + 
      "?" + "p_event" + "=" + "join-listen" + 
      "&" + "p_format" + "=" + "xml" + 
      "&" + "p_mode" + "=" + aMode + 
      "&" + "p_subject" + "=" + aSubject;

    startDataEventListener(aListener, listenURL);
  }

  public void publish(String aSubject, Map theAttributes)
    throws PushletException
  {
    throwOnInvalidSession();
    Event event = new Event("publish", theAttributes);
    event.setField("p_subject", aSubject);
    event.setField("p_id", this.id);
    Event response = doControl(event);
    throwOnNack(response);
  }

  public String subscribe(String aSubject, String aLabel)
    throws PushletException
  {
    throwOnInvalidSession();
    Event event = new Event("subscribe");
    event.setField("p_id", this.id);
    event.setField("p_subject", aSubject);

    if (aLabel != null) {
      event.setField("p_label", aLabel);
    }

    Event response = doControl(event);
    throwOnNack(response);

    return response.getField("p_sid");
  }

  public String subscribe(String aSubject)
    throws PushletException
  {
    return subscribe(aSubject, null);
  }

  public void unsubscribe(String aSubscriptionId)
    throws PushletException
  {
    throwOnInvalidSession();
    Event event = new Event("unsubscribe");
    event.setField("p_id", this.id);

    if (aSubscriptionId != null) {
      event.setField("p_sid", aSubscriptionId);
    }

    Event response = doControl(event);
    throwOnNack(response);
  }

  public void unsubscribe()
    throws PushletException
  {
    unsubscribe(null);
  }

  public void stopListen()
    throws PushletException
  {
    if (this.dataEventListener != null) {
      unsubscribe();
      this.dataEventListener.stop();
      this.dataEventListener = null;
    }
  }

  public void setDebug(boolean b) {
    this.debug = b;
  }

  protected void startDataEventListener(PushletClientListener aListener, String aListenURL)
  {
    this.dataEventListener = new DataEventListener(aListener, aListenURL);

    synchronized (this.dataEventListener) {
      this.dataEventListener.start();
      try
      {
        this.dataEventListener.wait();
      } catch (InterruptedException localInterruptedException) {
      }
    }
  }

  protected void throwOnNack(Event anEvent) throws PushletException {
    if (anEvent.getEventType().equals("nack"))
      throw new PushletException("Negative response: reason=" + anEvent.getField("p_reason"));
  }

  protected void throwOnInvalidSession() throws PushletException
  {
    if (this.id == null)
      throw new PushletException("Invalid pushlet session");
  }

  protected Reader openURL(String aURL) throws PushletException
  {
    try
    {
      p("Connecting to " + aURL);
      URL url = new URL(aURL);
      URLConnection urlConnection = url.openConnection();

      urlConnection.setUseCaches(false);
      urlConnection.setDefaultUseCaches(false);

      return new InputStreamReader(urlConnection.getInputStream());
    }
    catch (Throwable t) {
      warn("openURL() could not open " + aURL, t);
      throw new PushletException(" could not open " + aURL, t);
    }
  }

  protected Event doControl(Event aControlEvent)
    throws PushletException
  {
    String controlURL = this.pushletURL + "?" + aControlEvent.toQueryString();

    p("doControl to " + controlURL);

    Reader reader = openURL(controlURL);

    Event event = null;
    try {
      p("Getting event...");

      event = EventParser.parse(reader);
      p("Event received " + event);
      return event;
    }
    catch (Throwable t) {
      warn("doControl() exception", t);
      throw new PushletException(" error parsing response from" + controlURL, t);
    }
  }

  protected void p(String s)
  {
    if (this.debug)
      System.out.println("[PushletClient] " + s);
  }

  protected void warn(String s)
  {
    warn(s, null);
  }

  protected void warn(String s, Throwable t)
  {
    System.err.println("[PushletClient] - WARN - " + s + " ex=" + t);

    if (t != null)
      t.printStackTrace();
  }

  protected class DataEventListener
    implements Runnable
  {
    private PushletClientListener listener;
    private Thread receiveThread = null;
    private Reader reader;
    private String refreshURL;
    private String listenURL;

    public DataEventListener(PushletClientListener aListener, String aListenURL)
    {
      this.listener = aListener;
      this.listenURL = aListenURL;
    }

    public void start()
    {
      this.receiveThread = new Thread(this);
      this.receiveThread.start();
    }

    public void stop()
    {
      PushletClient.this.p("In stop()");
      bailout();
    }

    public void run()
    {
      PushletClient.this.p("Start run()");
      try
      {
        do {
          this.reader = PushletClient.this.openURL(this.listenURL);

          synchronized (this)
          {
            notify();
          }

          do
          {
            Event event = null;
            try
            {
              event = EventParser.parse(this.reader);
              PushletClient.this.p("Event received " + event);
            }
            catch (Throwable t)
            {
              if (this.listener == null) break; 
              this.listener.onError("exception during receive: " + t);
            }

            if ((event == null) || (this.listener == null))
              continue;
            String eventType = event.getEventType();
            if (eventType.equals("hb")) {
              this.listener.onHeartbeat(event);
            } else if (eventType.equals("data")) {
              this.listener.onData(event);
            } else if (eventType.equals("join-listen-ack")) {
              PushletClient.this.id = event.getField("p_id");
            } else if (eventType.equals("listen-ack")) {
              PushletClient.this.p("Listen ack ok"); } else {
              if (eventType.equals("refresh-ack"))
                continue;
              if (eventType.equals("abort")) {
                this.listener.onAbort(event);
                this.listener = null;
                break;
              }if (eventType.equals("refresh"))
                refresh(event);
              else
                handleUnknownEventType(eventType, event, this.listener);
            }
          }
          while ((this.receiveThread != null) && (this.receiveThread.isAlive()));

          if (this.receiveThread == null) break; 
        }while (this.receiveThread.isAlive());
      }
      catch (Throwable t)
      {
        PushletClient.this.warn("Exception in run() ", t);
      }
    }

    protected void disconnect()
    {
      PushletClient.this.p("start disconnect()");
      if (this.reader != null)
      {
        try
        {
          PushletClient.this.p("Closed reader ok");
        } catch (Exception localException) {
        } finally {
          this.reader = null;
        }
      }
      PushletClient.this.p("end disconnect()");
    }

    public void stopThread()
    {
      PushletClient.this.p("In stopThread()");

      Thread targetThread = this.receiveThread;

      this.receiveThread = null;

      if ((targetThread != null) && (targetThread.isAlive()))
      {
        targetThread.interrupt();
        try
        {
          targetThread.join(500L);
        }
        catch (InterruptedException localInterruptedException)
        {
        }

        if (targetThread.isAlive())
        {
          targetThread.stop();
          try
          {
            targetThread.join(500L);
          }
          catch (Throwable localThrowable) {
          }
        }
        PushletClient.this.p("Stopped receiveThread alive=" + targetThread.isAlive());
      }
    }

    public void bailout()
    {
      PushletClient.this.p("In bailout()");
      stopThread();
      disconnect();
    }

    protected void refresh(Event aRefreshEvent)
      throws PushletException
    {
      try
      {
        Thread.sleep(Long.parseLong(aRefreshEvent.getField("p_wait")));
      } catch (Throwable t) {
        PushletClient.this.warn("abort while refresing");
        this.refreshURL = null;
        return;
      }

      if (this.receiveThread == null) {
        return;
      }

      this.refreshURL = 
        (PushletClient.this.pushletURL + 
        "?" + "p_id" + "=" + PushletClient.this.id + 
        "&" + "p_event" + "=" + "refresh");

      if (this.reader != null) {
        try {
          this.reader.close();
        }
        catch (IOException localIOException)
        {
        }
        this.reader = null;
      }

      this.reader = PushletClient.this.openURL(this.refreshURL);
    }

    protected void handleUnknownEventType(String eventType, Event event, PushletClientListener listener)
    {
      PushletClient.this.warn("unsupported event type received: " + eventType);
    }
  }

  private static class HTTPAuthenticateProxy extends Authenticator
  {
    private String thePassword = "";
    private String theUser = "";

    public HTTPAuthenticateProxy(String username, String password)
    {
      this.thePassword = password;
      this.theUser = username;
    }

    protected PasswordAuthentication getPasswordAuthentication()
    {
      return new PasswordAuthentication(this.theUser, this.thePassword.toCharArray());
    }
  }
}