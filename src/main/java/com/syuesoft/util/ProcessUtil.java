package com.syuesoft.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class ProcessUtil
{
  Logger logger = Logger.getLogger(getClass());

  public Msg excuteCommand(String command, String msg)
  {
    Msg msgO = new Msg();
    if ((command != null) && (!"".equals(command))) {
      Runtime r = Runtime.getRuntime();
      Process p = null;
      this.logger.debug(msg);
      try
      {
        p = r.exec(command);

        StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "Error");
        StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(), "Output");
        errorGobbler.start();
        outputGobbler.start();
        p.waitFor();
      } catch (IOException e) {
        if (p != null) {
          p.destroy();
        }
        this.logger.error("执行失败", e);
        msgO.setMsg("执行失败");
        msgO.setSuccess(false);
      }
      catch (InterruptedException e)
      {
        if (p != null) {
          p.destroy();
        }
        this.logger.error("执行失败", e);
        msgO.setMsg("执行失败");
        msgO.setSuccess(false);
      } finally {
        this.logger.debug("执行批处理结束");
        if (p != null) {
          p.destroy();
        }
        msgO.setMsg("执行成功");
        msgO.setSuccess(true);
      }
    }
    return msgO;
  }

  public String getJdkBit()
  {
    return System.getProperty("sun.arch.data.model");
  }
  class StreamGobbler extends Thread { InputStream is;
    String type;

    StreamGobbler(InputStream is, String type) { this.is = is;
      this.type = type; }

    public void run()
    {
      try {
        InputStreamReader isr = new InputStreamReader(this.is);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null)
          if (this.type.equals("Error"))
            ProcessUtil.this.logger.error(line);
          else
            ProcessUtil.this.logger.debug(line);
      }
      catch (IOException ioe)
      {
        ioe.printStackTrace();
      }
    }
  }
}