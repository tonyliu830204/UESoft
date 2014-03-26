package nl.justobjects.pushlet.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.HashMap;

public class EventParser
{
  public static Event parse(File aFile)
    throws IOException
  {
    BufferedReader br = new BufferedReader(new FileReader(aFile));
    return parse(br);
  }

  public static Event parse(Reader aReader)
    throws IOException
  {
    StringBuffer preparsedString = new StringBuffer(24);
    char nextChar;
    while ((nextChar = (char)aReader.read()) != '<');
    preparsedString.append(nextChar);
    do
    {
      nextChar = (char)aReader.read();
      preparsedString.append(nextChar);
    }while (nextChar != '>');

    return parse(preparsedString.toString());
  }

  public static Event parse(String aString)
    throws IOException
  {
    aString = aString.trim();

    if ((!aString.startsWith("<")) || (!aString.endsWith("/>"))) {
      throw new IOException("No start or end tag found while parsing event [" + aString + "]");
    }

    HashMap properties = new HashMap(3);

    aString = aString.substring(1, aString.length() - 2).trim();

    int index = 0;

    while ((!Character.isWhitespace(aString.charAt(index))) && 
      (index < aString.length())) {
      index++;
    }

    aString = aString.substring(index).trim();
    index = 0;

    while (index < aString.length())
    {
      while ((aString.charAt(index) != '=') && 
        (index < aString.length())) {
        index++;
      }

      String attrName = aString.substring(0, index).trim();

      aString = aString.substring(index + 1).trim();
      index = 1;

      while ((aString.charAt(index) != '"') && 
        (index < aString.length()))
      {
        if (aString.charAt(index) == '\\') {
          aString = aString.substring(0, index) + 
            aString.substring(index + 1);
        }

        index++;
      }

      String attrValue = aString.substring(1, index);

      properties.put(attrName, attrValue);

      aString = aString.substring(index + 1).trim();
      index = 0;
    }

    return new Event(properties);
  }

  public static void main(String[] args)
  {
    try
    {
      Event event = parse(new File(args[0]));
      System.out.println("OK parsed Event file " + args[0]);
      System.out.println(event.toXML());

      event = parse(event.toXML());
      System.out.println("OK parsed Event string");
      System.out.println(event.toXML());
    } catch (Throwable t) {
      System.out.println("Error parsing event file: " + args[0]);
      t.printStackTrace();
    }
  }
}