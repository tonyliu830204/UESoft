package com.syuesoft.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter
  implements Filter
{
  public void destroy()
  {
  }

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
    throws IOException, ServletException
  {
    HttpServletRequest request = (HttpServletRequest)req;
    HttpServletResponse response = (HttpServletResponse)res;
    String path = request.getServletPath();
    if ((path.indexOf("/index.jsp") != -1) || (path.indexOf("/login.jsp") != -1) || (path.indexOf("/relogin.jsp") != -1))
    {
      fc.doFilter(request, response);
    }
    else
    {
      HttpSession session = request.getSession();
      if (session.getAttribute("CUSTOMER") == null)
      {
        String url = request.getContextPath();
        response.sendRedirect(url + "/relogin.jsp");
      }
      else
      {
        fc.doFilter(request, response);
      }
    }
  }

  public void init(FilterConfig arg0)
    throws ServletException
  {
  }
}