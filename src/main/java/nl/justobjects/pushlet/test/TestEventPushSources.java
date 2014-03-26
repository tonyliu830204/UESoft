package nl.justobjects.pushlet.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;
import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventSource;
import nl.justobjects.pushlet.util.Rand;

public class TestEventPushSources
{
  public static void e(String s)
  {
    System.out.println("AEXStocksEventPushSource: " + s);
  }

  public static void p(String s)
  {
  }

  public static void main(String[] args)
  {
  }

  public static class AEXStocksEventPushSource
    implements EventSource, Runnable
  {
    String pageURL = "http://www.debeurs.nl/koersen/aex.asp";
    Thread thread = null;
    volatile boolean active = false;
    public static final int NR_OF_STOCKS = 24;
    public static final String EMPTY = "wait...";
    private int restarts = 1;

    Vector stocksCache = new Vector(24);

    public AEXStocksEventPushSource() {
      for (int i = 0; i < 24; i++)
        this.stocksCache.addElement(new Stock());
    }

    public synchronized void activate()
    {
      TestEventPushSources.e("activating...");

      stopThread();

      this.thread = new Thread(this, "AEXStocksPublisher-" + this.restarts++);
      this.active = true;
      this.thread.start();
      TestEventPushSources.e("activated");
    }

    public synchronized void passivate()
    {
      TestEventPushSources.e("passivating...");
      this.active = false;
      stopThread();

      for (int i = 0; i < 24; i++) {
        ((Stock)this.stocksCache.elementAt(i)).modified = true;
      }

      TestEventPushSources.e("passivated");
    }

    public synchronized void stop()
    {
    }

    public void run()
    {
      publishStocks();

      int count = 5;
      while (this.active)
      {
        if (count == 5) {
          updateCache();
          count = 0;
        }
        count++;

        sendUpdates();

        if ((this.thread == null) || (this.thread.isInterrupted()))
        {
          break;
        }
        try
        {
          Thread.sleep(2000L);
        } catch (InterruptedException ie) {
          break;
        }

      }

      this.thread = null;
      this.active = false;
    }

    private String getStocksLine() {
      BufferedReader br = null;
      InputStream is = null;
      String nextLine = "";
      try
      {
        is = new URL(this.pageURL).openStream();
        br = new BufferedReader(new InputStreamReader(is));
        boolean foundLine = false;
        while (!foundLine) {
          nextLine = br.readLine();
          if (nextLine == null) {
            return "";
          }
          foundLine = nextLine.indexOf("details.asp?iid=14053&parent=aex") != -1;
        }
      } catch (Exception e) {
        TestEventPushSources.e("could not open or read URL pageURL=" + this.pageURL + " ex=" + e);
        return "";
      } finally {
        try {
          if (is != null) is.close();  } catch (IOException localIOException2) {
        }
      }try { if (is != null) is.close(); 
      }
      catch (IOException localIOException3)
      {
      }
      return nextLine;
    }

    private void publishStocks()
    {
      for (int i = 0; i < 24; i++) {
        Stock nextStock = (Stock)this.stocksCache.elementAt(i);

        if (nextStock.modified) {
          publishStock(i, nextStock.name, nextStock.rate);
          nextStock.modified = false;
          try {
            Thread.sleep(400L);
          } catch (InterruptedException ie) {
            return;
          }
        }
      }
    }

    private void publishStock(int index, String name, String rate) {
      Event event = Event.createDataEvent("/stocks/aex");
      event.setField("number", index);
      event.setField("name", name);
      event.setField("rate", rate);
      TestEventPushSources.p("publish: nr=" + index + " name=" + name + " rate=" + rate);
      Dispatcher.getInstance().multicast(event);
    }

    private void sendUpdates() {
      TestEventPushSources.p("sending updates");

      int randomIndex = Rand.randomInt(0, 23);
      Stock randomStock = (Stock)this.stocksCache.elementAt(randomIndex);
      randomStock.modified = true;

      publishStocks();
    }

    private void stopThread() {
      if (this.thread != null) {
        this.thread.interrupt();
        this.thread = null;
      }
    }

    private void updateCache() {
      TestEventPushSources.p("updating Cache");

      String stocksLine = getStocksLine();
      if ("".equals(stocksLine)) {
        TestEventPushSources.e("updateCache: stocksLine == null");
        return;
      }

      String delim = "<>";
      StringTokenizer st = new StringTokenizer(stocksLine, delim);
      String nextToken = "";
      int count = 0;
      String nextStock = "";
      String nextQuote = "";
      String currentQuote = null;
      int index = -1;
      while (st.hasMoreTokens()) {
        nextToken = st.nextToken();
        count++;

        if ((count - 5) % 57 == 0) {
          TestEventPushSources.p("c=" + count + " s=" + nextToken);
          nextStock = nextToken;
        }

        if ((count - 10) % 57 == 0) {
          nextQuote = nextToken;
          index++;
          TestEventPushSources.p("c=" + count + " val=" + nextQuote);
          Stock currentStock = (Stock)this.stocksCache.elementAt(index);

          if (("wait...".equals(currentStock.rate)) || (!currentStock.rate.equals(nextQuote))) {
            TestEventPushSources.p("modified: " + nextStock);
            currentStock.name = nextStock;
            currentStock.rate = nextQuote;
            currentStock.modified = true;
          }
        }
      }
    }

    class Stock
    {
      public String name = "wait...";
      public String rate = "wait...";
      public volatile boolean modified = false;

      Stock()
      {
      }
    }
  }

  public static class AEXStocksEventPushSourceABN
  {
    String pageURL = "http://ri2.rois.com/E36msPtnZC0e15CVb4KT97JAGfGSfCcrvv6*FcyZIoNyh/CTIB/RI2APISNAP?RIC=0%23.AEX&FORMAT=XML";
  }
}