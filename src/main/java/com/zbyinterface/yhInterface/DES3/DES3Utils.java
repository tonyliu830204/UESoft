package com.zbyinterface.yhInterface.DES3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class DES3Utils
{
  private static String Algorithm = "DESede";
  private static String fileName = "DesKey.xml";
  private static String filePath = "/conf";
  private static File file = null;

  static { URL serverRealPath = Thread.currentThread().getContextClassLoader().getResource("");
    file = new File(serverRealPath.getPath());
    filePath = file.getAbsolutePath();
  }

  public static void saveDesKey(String deskey, String enterpriseId)
    throws Exception
  {
    String filePath1 = getPath(enterpriseId);
    File fileMdk = new File(filePath1);
    if (!fileMdk.exists())
      fileMdk.mkdirs();
    File fileDes = new File(filePath1 + fileName);
    if (fileDes.exists()) {
      if ((fileDes.delete()) && 
        (fileDes.createNewFile())) {
        write(deskey, fileDes.getAbsolutePath());
      }

    }
    else if (fileDes.createNewFile())
      write(deskey, fileDes.getAbsolutePath());
  }

  public static void createDesKey(String enterpriseId)
    throws Exception
  {
    SecureRandom sr = null;
    String filePath1 = getPath(enterpriseId);
    if (enterpriseId != null) {
      Random rnd = new Random();
      String seed = enterpriseId + rnd;
      sr = new SecureRandom(seed.getBytes());
    } else {
      sr = new SecureRandom();
    }
    File fileMdk = new File(filePath1);
    if (!fileMdk.exists())
      fileMdk.mkdirs();
    File fileDes = new File(filePath1 + fileName);
    KeyGenerator kg = KeyGenerator.getInstance(Algorithm);
    kg.init(sr);
    if (fileDes.exists()) {
      if ((fileDes.delete()) && 
        (fileDes.createNewFile())) {
        write(kg, fileDes.getAbsolutePath());
      }

    }
    else if (fileDes.createNewFile())
      write(kg, fileDes.getAbsolutePath());
  }

  public static SecretKey getDesKey(String enterpriseId)
    throws Exception
  {
    SecretKey kp = null;
    String filePath1 = getPath(enterpriseId);
    FileInputStream is = new FileInputStream(filePath1 + fileName);
    ObjectInputStream oos = new ObjectInputStream(is);
    kp = (SecretKey)oos.readObject();
    oos.close();
    return kp;
  }

  public static String getDesKeyToDyte(String enterpriseId)
    throws Exception
  {
    SecretKey kp = null;
    String filePath1 = getPath(enterpriseId);
    FileInputStream is = new FileInputStream(filePath1 + fileName);
    ObjectInputStream oos = new ObjectInputStream(is);
    kp = (SecretKey)oos.readObject();
    oos.close();
    return ConverBase64.encode(kp.getEncoded());
  }

  private static void write(KeyGenerator kg, String fileName)
    throws Exception
  {
    FileOutputStream fos = new FileOutputStream(fileName);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    SecretKey deskey = kg.generateKey();
    oos.writeObject(deskey);
    oos.close();
  }

  private static void write(String deskey, String fileName)
    throws Exception
  {
    FileOutputStream fos = new FileOutputStream(fileName);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(makeDESedeKey(deskey));
    oos.close();
  }

  public static boolean encryptFile(String file, String dest, String enterpriseId)
    throws Exception
  {
    boolean isfinash = true;
    Cipher cipher = Cipher.getInstance(Algorithm);
    cipher.init(1, getDesKey(enterpriseId));
    InputStream is = new FileInputStream(file);
    OutputStream out = new FileOutputStream(dest);
    CipherInputStream cis = new CipherInputStream(is, cipher);
    byte[] buffer = new byte[20480];
    int r;
    while ((r = cis.read(buffer)) > 0)
    {
      out.write(buffer, 0, r);
    }
    cis.close();
    is.close();
    out.close();
    return isfinash;
  }

  public static boolean decryptFile(String file, String dest, String enterpriseId)
    throws Exception
  {
    boolean isfinash = true;
    Cipher cipher = Cipher.getInstance(Algorithm);
    cipher.init(2, getDesKey(enterpriseId));
    InputStream is = new FileInputStream(file);
    OutputStream out = new FileOutputStream(dest);
    CipherOutputStream cos = new CipherOutputStream(out, cipher);
    byte[] buffer = new byte[20480];
    int r;
    while ((r = is.read(buffer)) >= 0)
    {
      cos.write(buffer, 0, r);
    }
    cos.close();
    out.close();
    is.close();
    return isfinash;
  }

  public static String encryptBwToString(String data, String enterpriseId)
    throws Exception
  {
    byte[] result = (byte[])null;
    String resultStr = "";
    byte[] databyte = data.getBytes("GBK");
    Cipher cipher = Cipher.getInstance(Algorithm);
    cipher.init(1, getDesKey(enterpriseId));
    result = cipher.doFinal(databyte);
    resultStr = ConverBase64.encode(result);
    return resultStr;
  }

  public static String decryptBwToString(String data, String enterpriseId)
    throws Exception
  {
    byte[] result = (byte[])null;
    String resultStr = null;
    Cipher cipher = Cipher.getInstance(Algorithm);
    cipher.init(2, getDesKey(enterpriseId));
    result = cipher.doFinal(ConverBase64.decodeBase64(data.getBytes()));
    resultStr = new String(result);
    return resultStr;
  }

  public static SecretKey makeDESedeKey(String desStr)
    throws Exception
  {
    SecretKeyFactory skf = SecretKeyFactory.getInstance(Algorithm);
    return skf.generateSecret(new DESedeKeySpec(ConverBase64.decodeBase64(desStr)));
  }

  public static boolean getDESedeKeyExist(String enterpriseId) throws Exception
  {
    boolean isExisr = false;
    String filePath1 = getPath(enterpriseId);
    File fileDes = new File(filePath1 + fileName);
    if (fileDes.exists()) {
      FileInputStream fin = new FileInputStream(fileDes);
      int size = fin.available();
      if (size > 0) {
        isExisr = true;
      }
    }
    return isExisr;
  }

  private static String getPath(String enterpriseId) throws Exception {
    String filePath1 = filePath + File.separator;
    if (enterpriseId != null)
      filePath1 = filePath + File.separator + enterpriseId + File.separator;
    return filePath1;
  }

  public static String readTxtFile(String filePath) throws Exception {
    StringBuffer buf = new StringBuffer();
    try {
      String encoding = "GBK";
      File file = new File(filePath);
      if ((file.isFile()) && (file.exists())) {
        InputStreamReader read = new InputStreamReader(
          new FileInputStream(file), encoding);
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        while ((lineTxt = bufferedReader.readLine()) != null) {
          buf.append(lineTxt);
          buf.append("\n");
        }
        read.close();
      } else {
        System.out.println("找不到指定的文件");
      }
    } catch (Exception e) {
      System.out.println("读取文件内容出错");
      e.printStackTrace();
    }
    return buf.toString();
  }

  public static boolean writeTxtFile(String filenameTemp, String newStr)
    throws IOException
  {
    boolean flag = false;
    String filein = newStr + "\r\n";
    String temp = "";

    FileInputStream fis = null;
    InputStreamReader isr = null;
    BufferedReader br = null;

    FileOutputStream fos = null;
    PrintWriter pw = null;
    try
    {
      File file = new File(filenameTemp);
      if (!file.exists()) {
        file.createNewFile();
      }
      fis = new FileInputStream(file);
      isr = new InputStreamReader(fis);
      br = new BufferedReader(isr);
      StringBuffer buf = new StringBuffer();

      for (int j = 1; (temp = br.readLine()) != null; j++) {
        buf = buf.append(temp);

        buf = buf.append(System.getProperty("line.separator"));
      }
      buf.append(filein);

      fos = new FileOutputStream(file);
      pw = new PrintWriter(fos);
      pw.write(buf.toString().toCharArray());
      pw.flush();
      flag = true;
    } catch (IOException e1) {
      throw e1;
    } finally {
      if (pw != null) {
        pw.close();
      }
      if (fos != null) {
        fos.close();
      }
      if (br != null) {
        br.close();
      }
      if (isr != null) {
        isr.close();
      }
      if (fis != null) {
        fis.close();
      }
    }
    return flag;
  }
}