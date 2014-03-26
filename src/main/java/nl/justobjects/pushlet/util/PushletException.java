package nl.justobjects.pushlet.util;

public class PushletException extends Exception
{
  private PushletException()
  {
  }

  public PushletException(String aMessage, Throwable t)
  {
    super(aMessage + "\n embedded exception=" + t.toString());
  }

  public PushletException(String aMessage) {
    super(aMessage);
  }

  public PushletException(Throwable t) {
    this("PushletException: ", t);
  }

  public String toString() {
    return "PushletException: " + getMessage();
  }
}