package com.syuesoft.listener;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.syuesoft.model.BasUsers;

public class SessionListener
  implements HttpSessionAttributeListener
{
  private static ListOrderedMap onLineSession = new ListOrderedMap();

  Log logger = LogFactory.getLog(getClass());

  public static ListOrderedMap getOnLineUsers()
  {
    return onLineSession;
  }

  public void attributeAdded(HttpSessionBindingEvent arg0)
  {
    if (arg0.getName().equals("CUSTOMER"))
    {
      getOnLineUsers().put(arg0.getSession().getId(), arg0.getSession());
    }
  }

  public void attributeRemoved(HttpSessionBindingEvent arg0)
  {
    if (arg0.getName().equals("CUSTOMER"))
    {
      onLineSession.remove(arg0.getSession().getId());
    }
  }

  public void attributeReplaced(HttpSessionBindingEvent arg0)
  {
    if (arg0.getName().equals("CUSTOMER"))
    {
      HttpSession session = (HttpSession)getOnLineUsers().get(
        arg0.getSession().getId());

      if (session != arg0.getSession())
      {
        if (session != null)
        {
          session.invalidate();
        }
        onLineSession.put(arg0.getSession().getId(), arg0.getSession());
      }
    }
  }

  public static HttpSession isAlreadyEnter(BasUsers user)
  {
    HttpSession flag = null;
    Iterator iter = getOnLineUsers().entrySet().iterator();
    while (iter.hasNext())
    {
      Map.Entry entry = (Map.Entry)iter.next();
      HttpSession session = (HttpSession)entry.getValue();
      BasUsers user1 = (BasUsers)session
        .getAttribute("CUSTOMER");
      if (!user.getUserId().equals(user1.getUserId()))
        continue;
      flag = session;
      break;
    }

    return flag;
  }
}