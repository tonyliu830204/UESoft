package nl.justobjects.pushlet.core;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import nl.justobjects.pushlet.util.Log;

class XMLAdapter
  implements ClientAdapter
{
  private String contentType = "text/plain;charset=UTF-8";
  private ServletOutputStream out = null;
  private HttpServletResponse servletRsp;
  private boolean strictXML;

  public XMLAdapter(HttpServletResponse aServletResponse)
  {
    this(aServletResponse, false);
  }

  public XMLAdapter(HttpServletResponse aServletResponse, boolean useStrictXML)
  {
    this.servletRsp = aServletResponse;

    this.strictXML = useStrictXML;
    if (this.strictXML)
      this.contentType = "text/xml;charset=UTF-8";
  }

  public void start()
    throws IOException
  {
    this.servletRsp.setContentType(this.contentType);

    this.out = this.servletRsp.getOutputStream();

    this.servletRsp = null;

    if (this.strictXML)
      this.out.print("<pushlet>");
  }

  public void push(Event anEvent)
    throws IOException
  {
    debug("event=" + anEvent);

    this.out.print(anEvent.toXML(this.strictXML));
    this.out.flush();
  }

  public void stop()
    throws IOException
  {
    if (this.strictXML) {
      this.out.print("</pushlet>");
      this.out.flush();
    }
  }

  private void debug(String s) {
    Log.debug("[XMLAdapter]" + s);
  }
}