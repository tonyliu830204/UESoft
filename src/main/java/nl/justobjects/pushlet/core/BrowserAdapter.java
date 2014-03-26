package nl.justobjects.pushlet.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;
import nl.justobjects.pushlet.util.Log;

public class BrowserAdapter
  implements ClientAdapter, Protocol
{
  public static final String START_DOCUMENT = "<html><head><meta http-equiv=\"Pragma\" content=\"no-cache\"><meta http-equiv=\"Expires\" content=\"Tue, 31 Dec 1997 23:59:59 GMT\"></head><body>\n<script language=\"JavaScript\"> var url=\" \"; \nfunction refresh() { document.location.href=url; }</script>";
  public static final String END_DOCUMENT = "</body></html>";
  private PrintWriter servletOut;
  private HttpServletResponse servletRsp;
  private int bytesSent;

  public BrowserAdapter(HttpServletResponse aServletResponse)
  {
    this.servletRsp = aServletResponse;
  }

  public void start()
    throws IOException
  {
    this.servletRsp.setStatus(200);
    this.servletRsp.setContentType("text/html;charset=UTF-8");

    this.servletOut = this.servletRsp.getWriter();
    send("<html><head><meta http-equiv=\"Pragma\" content=\"no-cache\"><meta http-equiv=\"Expires\" content=\"Tue, 31 Dec 1997 23:59:59 GMT\"></head><body>\n<script language=\"JavaScript\"> var url=\" \"; \nfunction refresh() { document.location.href=url; }</script>");
  }

  public void push(Event anEvent)
    throws IOException
  {
    Log.debug("BCA event=" + anEvent.toXML());

    if (anEvent.getEventType().equals("refresh"))
    {
      long refreshWaitMillis = Long.parseLong(anEvent.getField("p_wait"));

      String url = anEvent.getField("p_url");
      String jsRefreshTrigger = "\n<script language=\"JavaScript\">url='" + url + "';\n setTimeout(\"refresh()\", " + refreshWaitMillis + ");\n</script>";

      send(jsRefreshTrigger + "</body></html>");
    } else {
      send(event2JavaScript(anEvent));
    }
  }

  public void stop()
  {
    this.servletOut = null;
  }

  protected void send(String s)
    throws IOException
  {
    if (this.servletOut == null) {
      throw new IOException("Client adapter was stopped");
    }

    this.servletOut.print(s);

    this.servletOut.flush();

    this.servletRsp.flushBuffer();

    this.bytesSent += s.length();
    Log.debug("bytesSent= " + this.bytesSent);
  }

  protected String event2JavaScript(Event event)
    throws IOException
  {
    String jsArgs = "";
    for (Iterator iter = event.getFieldNames(); iter.hasNext(); ) {
      String name = (String)iter.next();
      String value = event.getField(name);
      String nextArgument = (jsArgs.equals("") ? "" : ",") + "'" + name + "'" + ", \"" + value + "\"";
      jsArgs = jsArgs + nextArgument;
    }

    return "<script language=\"JavaScript\">parent.push(" + jsArgs + ");</script>";
  }
}