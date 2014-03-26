package com.zbyinterface.yhInterface.DES3;

import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ConverBase64
{
  private static final Log log = LogFactory.getLog(ConverBase64.class);

  public static byte[] encodeBase64(byte[] data)
  {
    byte[] e_data = (byte[])null;
    if ((data != null) && (data.length > 0))
    {
      e_data = Base64.encodeBase64(data);
    }
    return e_data;
  }

  public static String encode(byte[] data)
  {
    String svalue = null;
    if ((data != null) && (data.length > 0))
    {
      BASE64Encoder en = new BASE64Encoder();
      svalue = en.encodeBuffer(data);
    }
    return svalue;
  }

  public static byte[] decodeBase64(byte[] data)
  {
    byte[] d_data = (byte[])null;
    if ((data != null) && (data.length > 0))
    {
      d_data = Base64.decodeBase64(data);
    }
    return d_data;
  }

  public static byte[] decodeBase64(String s_value)
  {
    byte[] d_data = (byte[])null;
    if ((s_value != null) && 
      (!s_value.trim().equals("")))
    {
      BASE64Decoder de = new BASE64Decoder();
      try
      {
        d_data = de.decodeBuffer(s_value);
      }
      catch (IOException e)
      {
        log.error("", e);
      }
    }
    return d_data;
  }
}