package nl.justobjects.pushlet.test;

import java.io.PrintStream;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;
import nl.justobjects.pushlet.core.SessionManager;
import nl.justobjects.pushlet.util.Rand;

public class TestEventPullSources
{
  public static void e(String s)
  {
    System.out.println(s);
  }

  public static void p(String s)
  {
  }

  public static class AEXStocksEventPullSource extends EventPullSource
  {
    String[] stocks = { "abn amro", "26", "aegon", "38", "ahold", "34", "akzo nobel", "51", "asm lith h", "26", "corus plc", "2", "dsm", "40", "elsevier", "14", "fortis (nl)", "32", "getronics", "6", "gucci", "94", "hagemeyer", "25", "heineken", "61", "ing c", "78", "klm", "66", "kon olie", "66", "kpn", "13", "numico c", "44", "philips, kon", "38", "tnt", "26", "unilever c", "62", "vendex kbb", "16", "vnu", "49", "wolt-kluw c", "25" };

    public long getSleepTime()
    {
      return Rand.randomLong(2000L, 4000L);
    }

    public Event pullEvent() {
      Event event = Event.createDataEvent("/stocks/aex");
      int stockNumber = Rand.randomInt(0, this.stocks.length / 2 - 1);
      int nextStockIndex = 2 * stockNumber;

      event.setField("number", stockNumber);
      event.setField("name", this.stocks[nextStockIndex]);
      if (this.stocks[(nextStockIndex + 1)] == null) {
        this.stocks[(nextStockIndex + 1)] = String.valueOf(Rand.randomInt(50, 150));
      }
      int currentStockValue = new Integer(this.stocks[(nextStockIndex + 1)]).intValue();
      int newStockValue = currentStockValue + Rand.randomInt(-2, 2);

      event.setField("rate", newStockValue + "." + Rand.randomInt(0, 99));
      return event;
    }
  }

  public static class PingEventPullSource extends EventPullSource
  {
    public long getSleepTime()
    {
      return 3000L;
    }

    public Event pullEvent()
    {
      return Event.createDataEvent("/pushlet/ping");
    }
  }

  public static class PushletStatusEventPullSource extends EventPullSource
  {
    public long getSleepTime()
    {
      return 5000L;
    }

    public Event pullEvent() {
      Event event = Event.createDataEvent("/system/pushlet");

      event.setField("publisher", SessionManager.getInstance().getStatus());
      return event;
    }
  }

  public static class SystemStatusEventPullSource extends EventPullSource
  {
    Runtime runtime = Runtime.getRuntime();

    public long getSleepTime() {
      return 4000L;
    }

    public Event pullEvent() {
      Event event = Event.createDataEvent("/system/jvm");
      event.setField("totalMemory", this.runtime.totalMemory());
      event.setField("freeMemory", this.runtime.freeMemory());
      event.setField("maxMemory", this.runtime.maxMemory());
      int activeCount = Thread.activeCount();
      event.setField("threads", activeCount);

      return event;
    }
  }

  public static class TemperatureEventPullSource extends EventPullSource
  {
    String[] cities = { "amsterdam", "0", "rotterdam", "0", "leeuwarden", "0", "twente", "0", "limburg" };

    public long getSleepTime()
    {
      return Rand.randomLong(3000L, 5000L);
    }

    public Event pullEvent() {
      int cityNumber = Rand.randomInt(0, this.cities.length / 2 - 1);
      int nextCityIndex = 2 * cityNumber;

      Event event = Event.createDataEvent("/temperature");

      event.setField("number", cityNumber);
      event.setField("city", this.cities[nextCityIndex]);
      if (this.cities[(nextCityIndex + 1)] == null) {
        this.cities[(nextCityIndex + 1)] = String.valueOf(Rand.randomInt(5, 10));
      }
      int currentCityValue = new Integer(this.cities[(nextCityIndex + 1)]).intValue();
      int newCityValue = currentCityValue + Rand.randomInt(-2, 2);

      event.setField("value", newCityValue);
      return event;
    }
  }

  public static class TestEventPullSource extends EventPullSource
  {
    private int number = 0;

    public long getSleepTime() {
      return 2000L;
    }

    public Event pullEvent() {
      Event event = Event.createDataEvent("/system/test");

      event.setField("nr", this.number++);
      event.setField("time", System.currentTimeMillis());
      return event;
    }
  }

  public static class WebPresentationEventPullSource extends EventPullSource
  {
    String slideRootDir = "http://www.justobjects.org/cowcatcher/browse/j2ee/slides/";
    String[] slideURLs = { 
      "ejb/j2ee/ejbover/slide.0.0.html", 
      "ejb/j2ee/ejbover/slide.0.1.html", 
      "ejb/j2ee/ejbover/slide.0.2.html", 
      "ejb/j2ee/ejbover/slide.0.3.html", 
      "ejb/j2ee/ejbover/slide.0.4.html" };

    int nextSlideNumber = 0;

    public long getSleepTime() {
      return 5000L;
    }

    public Event pullEvent() {
      Event event = Event.createDataEvent("/webpres/auto");
      event.setField("url", this.slideRootDir + this.slideURLs[(this.nextSlideNumber++)]);
      if (this.nextSlideNumber == this.slideURLs.length) {
        this.nextSlideNumber = 0;
      }

      return event;
    }
  }
}