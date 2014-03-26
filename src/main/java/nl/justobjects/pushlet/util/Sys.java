package nl.justobjects.pushlet.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.StringCharacterIterator;
import java.util.Properties;

public class Sys
{
  public static String forHTMLTag(String aTagFragment)
  {
    StringBuffer result = new StringBuffer();

    StringCharacterIterator iterator = new StringCharacterIterator(aTagFragment);
    char character = iterator.current();
    while (character != 65535) {
      if (character == '<')
        result.append("&lt;");
      else if (character == '>')
        result.append("&gt;");
      else if (character == '"')
        result.append("&quot;");
      else if (character == '\'')
        result.append("&#039;");
      else if (character == '\\')
        result.append("&#092;");
      else if (character == '&') {
        result.append("&amp;");
      }
      else
      {
        result.append(character);
      }
      character = iterator.next();
    }
    return result.toString();
  }

  public static Properties loadPropertiesResource(String aResourcePath)
    throws IOException
  {
    try
    {
      ClassLoader classLoader = Sys.class.getClassLoader();

      Properties properties = new Properties();

      properties.load(classLoader.getResourceAsStream(aResourcePath));
      return properties; } catch (Throwable t) {
    }
    throw new IOException("failed loading Properties resource from " + aResourcePath);
  }

  public static Properties loadPropertiesFile(String aFilePath)
    throws IOException
  {
    try
    {
      Properties properties = new Properties();

      properties.load(new FileInputStream(aFilePath));
      return properties; } catch (Throwable t) {
    }
    throw new IOException("failed loading Properties file from " + aFilePath);
  }

  public static long now()
  {
    return System.currentTimeMillis();
  }
}