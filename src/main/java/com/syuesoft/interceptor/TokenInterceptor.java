package com.syuesoft.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.opensymphony.xwork2.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class TokenInterceptor extends org.apache.struts2.interceptor.TokenInterceptor
{
  private static final long serialVersionUID = -6680894220590585506L;
  public static final String INVALID_TOKEN_CODE = "invalid.token";

  protected String doIntercept(ActionInvocation invocation)
    throws Exception
  {
    if (this.log.isDebugEnabled()) {
      this.log.debug("Intercepting invocation to check for valid transaction token.", new String[0]);
    }
    HttpSession session = ServletActionContext.getRequest().getSession(true);
    synchronized (session) {
      if (!TokenHelper.validToken()) {
        return handleInvalidToken(invocation);
      }
      return handleValidToken(invocation);
    }
  }

  protected String handleInvalidToken(ActionInvocation invocation)
    throws Exception
  {
    Object action = invocation.getAction();
    String errorMessage = LocalizedTextUtil.findText(getClass(), "struts.messages.invalid.token", invocation.getInvocationContext().getLocale(), "The form has already been processed or no token was supplied, please try again.", new Object[0]);
    if ((action instanceof ValidationAware))
      ((ValidationAware)action).addActionError(errorMessage);
    else {
      this.log.warn(errorMessage, new String[0]);
    }
    return "invalid.token";
  }

  protected String handleValidToken(ActionInvocation invocation) throws Exception
  {
    return invocation.invoke();
  }
}