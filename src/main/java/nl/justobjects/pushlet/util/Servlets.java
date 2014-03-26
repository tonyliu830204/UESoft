package nl.justobjects.pushlet.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlets
{
  public static String getParameter(HttpServletRequest aRequest, String aName)
  {
    return getParameter(aRequest, aName, null);
  }

  public static String getParameter(HttpServletRequest aRequest, String aName, String aDefault)
  {
    String value = aRequest.getParameter(aName);
    if ((value == null) || (value.length() == 0)) {
      value = aDefault;
    }
    return value;
  }

  public static void setNoCacheHeaders(HttpServletResponse aResponse)
  {
    aResponse.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");

    aResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

    aResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");

    aResponse.setHeader("Pragma", "no-cache");
  }
}