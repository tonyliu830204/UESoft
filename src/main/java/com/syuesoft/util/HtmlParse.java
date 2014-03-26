package com.syuesoft.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HtmlParse
{
  public static String parse(String html, Map<String, ?> map)
  {
    Document doc = Jsoup.parse(html);
    Element body = doc.body();
    Set set = map.entrySet();
    if ((html != null) && (map.size() > 0)) {
      for (Iterator it = set.iterator(); it.hasNext(); ) {
        Map.Entry entry = (Map.Entry)it.next();
        String id = ((String)entry.getKey()).toString();
        String value = entry.getValue().toString();
        Element content = doc.getElementById(id);
        if (content != null) {
          content.empty();
          content.append(value);
        }
      }
      html = body.html() + "</p>";
    }
    return html;
  }
}