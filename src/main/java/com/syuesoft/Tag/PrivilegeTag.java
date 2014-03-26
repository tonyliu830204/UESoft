package com.syuesoft.Tag;

import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class PrivilegeTag extends TagSupport
{
  private static final long serialVersionUID = -532517444654109642L;
  private String code;

  public String getCode()
  {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int doStartTag()
    throws JspException
  {
    int result = 0;
    Set auth = (Set)this.pageContext.getSession().getAttribute("CURRUSERAUTH");
    if (auth != null)
    {
      if (auth.contains(this.code))
        result = 6;
      else {
        result = 0;
      }
    }
    return result;
  }
}