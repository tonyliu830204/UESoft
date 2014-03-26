package com.syuesoft.timer.serviceimpl;

import org.apache.log4j.Logger;

import com.syuesoft.util.WPS;

public class MyTimer
{
  public void restart()
  {
    Logger logger = Logger.getLogger(getClass());
    String path = WPS.getRootPath();
    path = path.replace(WPS.webappsCharacteristic, "bin/");
    path = path.substring(0, path.indexOf("bin/") + 4);
    String MYSQL = path + "restartMySql.bat";
    String STARTTOMCAT = path + "start.bat";
    String STOPTOMCAT = path + "stop.bat";

    String strcmd = "cmd /c start batFile";
    System.out.println(MYSQL);
    System.out.println(STARTTOMCAT);
    System.out.println(STOPTOMCAT);
    try
    {
      logger.info("定时停止TOMCAT服务开始 !");
      strcmd = strcmd.replace("batFile", STOPTOMCAT);
      Process proc = Runtime.getRuntime().exec(strcmd);
      proc.waitFor();
      logger.info("停止TOMCAT服务成功 !");
      logger.info("定时重启MySQL服务开始 !");
      strcmd = strcmd.replace("batFile", MYSQL);
      Process proc1 = Runtime.getRuntime().exec(strcmd);
      proc1.waitFor();
      logger.info("重启MySQL服务成功 !");
      logger.info("定时重新启动MySQL服务开始 !");
      strcmd = strcmd.replace("batFile", STARTTOMCAT);
      Process proc2 = Runtime.getRuntime().exec(strcmd);
      proc2.waitFor();
      logger.info("重新启动MySQL服务成功 !");
    } catch (Exception e) {
      logger.error("定时重启服务失败!", e);
    }
  }
}