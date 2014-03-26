package com.syuesoft.interceptor;

import com.syuesoft.util.FormatTime;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInitServlet extends HttpServlet
{
  private static Logger LOG = Logger.getLogger(Log4jInitServlet.class);
  private static final long serialVersionUID = 1L;

  public void service(ServletRequest req, ServletResponse resp)
    throws ServletException, IOException
  {
  }

  public void init()
    throws ServletException
  {
    Timer timer = new Timer();

    timer.schedule(new EveryDayTasks(), 0L, 100000L);
  }

  public class EveryDayTasks extends TimerTask
  {
    public EveryDayTasks() {
    }

    public void run() {
      try {
        System.setProperty("datetime", 
          FormatTime.dateToStringTwo(new Date()));
        PropertyConfigurator.configure(Log4jInitServlet.this.getServletContext()
          .getRealPath("") + 
          Log4jInitServlet.this.getInitParameter("configfile"));
      }
      catch (Exception e)
      {
        Log4jInitServlet.LOG.error("加载log4j出错", e);
      }
    }
  }
}