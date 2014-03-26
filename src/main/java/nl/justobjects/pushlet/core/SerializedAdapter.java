package nl.justobjects.pushlet.core;

import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.servlet.http.HttpServletResponse;

class SerializedAdapter
  implements ClientAdapter
{
  private ObjectOutputStream out = null;
  public static final String CONTENT_TYPE = "application/x-java-serialized-object";
  private HttpServletResponse servletRsp;

  public SerializedAdapter(HttpServletResponse aServletResponse)
  {
    this.servletRsp = aServletResponse;
  }

  public void start() throws IOException
  {
    this.servletRsp.setContentType("application/x-java-serialized-object");

    this.out = new ObjectOutputStream(this.servletRsp.getOutputStream());

    this.servletRsp = null;
  }

  public void push(Event anEvent)
    throws IOException
  {
    this.out.writeObject(anEvent);

    this.out.flush();
  }

  public void stop()
    throws IOException
  {
  }
}