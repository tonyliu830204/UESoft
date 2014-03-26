package com.syuesoft.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.syuesoft.qx.action.UserLoginAction;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class UserLoginInterceptor
  implements Interceptor
{
  private static final long serialVersionUID = 1L;

  public void destroy()
  {
  }

  public void init()
  {
  }

  public String intercept(ActionInvocation invocation)
    throws Exception
  {
    String result = "";

    if ((invocation.getAction() instanceof UserLoginAction))
    {
      result = invocation.invoke();
    }
    else
    {
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession session = request.getSession();
      if (session.getAttribute("CUSTOMER") != null)
      {
        result = invocation.invoke();
      }
      else if (!isAjaxRequest(request))
      {
        result = "relogin";
      }
      else
      {
        HttpServletResponse response = 
          ServletActionContext.getResponse();
        PrintWriter printWriter = response.getWriter();
        printWriter.print("{sessionState:0}");
        printWriter.flush();
        printWriter.close();
      }
    }

    return result;
  }

  private boolean isAjaxRequest(HttpServletRequest request)
  {
    return (request.getHeader("x-requested-with") != null) && 
      (request.getHeader("x-requested-with").equalsIgnoreCase(
      "XMLHttpRequest"));
  }
}