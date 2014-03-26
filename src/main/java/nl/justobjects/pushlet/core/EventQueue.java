package nl.justobjects.pushlet.core;

import java.io.PrintStream;

public class EventQueue
{
  private int capacity = 8;
  private Event[] queue = null;
  private int front;
  private int rear;

  public EventQueue()
  {
    this(8);
  }

  public EventQueue(int capacity)
  {
    this.capacity = capacity;
    this.queue = new Event[capacity];
    this.front = (this.rear = 0);
  }

  public synchronized boolean enQueue(Event item)
    throws InterruptedException
  {
    return enQueue(item, -1L);
  }

  public synchronized boolean enQueue(Event item, long maxWaitTime)
    throws InterruptedException
  {
    while (isFull()) {
      if (maxWaitTime > 0L)
      {
        wait(maxWaitTime);

        if (isFull())
          return false;
      }
      else {
        wait();
      }

    }

    this.queue[this.rear] = item;
    this.rear = next(this.rear);

    notifyAll();
    return true;
  }

  public synchronized Event deQueue()
    throws InterruptedException
  {
    return deQueue(-1L);
  }

  public synchronized Event deQueue(long maxWaitTime)
    throws InterruptedException
  {
    while (isEmpty()) {
      if (maxWaitTime >= 0L) {
        wait(maxWaitTime);

        if (isEmpty())
          return null;
      }
      else
      {
        wait();
      }

    }

    Event result = fetchNext();

    notifyAll();

    return result;
  }

  public synchronized Event[] deQueueAll(long maxWaitTime)
    throws InterruptedException
  {
    while (isEmpty()) {
      if (maxWaitTime >= 0L) {
        wait(maxWaitTime);

        if (isEmpty())
          return null;
      }
      else
      {
        wait();
      }

    }

    Event[] events = new Event[getSize()];
    for (int i = 0; i < events.length; i++) {
      events[i] = fetchNext();
    }

    notifyAll();

    return events;
  }

  public synchronized int getSize() {
    return this.rear >= this.front ? this.rear - this.front : this.capacity - this.front + this.rear;
  }

  public synchronized boolean isEmpty()
  {
    return this.front == this.rear;
  }

  public synchronized boolean isFull()
  {
    return next(this.rear) == this.front;
  }

  private int next(int index)
  {
    return index + 1 < this.capacity ? index + 1 : 0;
  }

  private Event fetchNext()
  {
    Event temp = this.queue[this.front];
    this.queue[this.front] = null;
    this.front = next(this.front);
    return temp;
  }

  public static void p(String s) {
    System.out.println(s);
  }

  public static void main(String[] args) {
    EventQueue q = new EventQueue(8);
    Event event = new Event("t");
    try {
      q.enQueue(event);
      p("(1) size = " + q.getSize());
      q.enQueue(event);
      p("(2) size = " + q.getSize());
      q.deQueue();
      p("(1) size = " + q.getSize());
      q.deQueue();
      p("(0) size = " + q.getSize());

      q.enQueue(event);
      q.enQueue(event);
      q.enQueue(event);
      p("(3) size = " + q.getSize());
      q.deQueue();
      p("(2) size = " + q.getSize());
      q.enQueue(event);
      q.enQueue(event);
      q.enQueue(event);
      p("(5) size = " + q.getSize());
      q.enQueue(event);
      q.enQueue(event);
      p("(7) size = " + q.getSize());
      q.deQueue();
      q.deQueue();
      q.deQueue();
      p("(4) size = " + q.getSize());
      q.deQueue();
      q.deQueue();
      q.deQueue();

      q.deQueue();
      p("(0) size = " + q.getSize());

      q.enQueue(event);
      q.enQueue(event);
      q.enQueue(event);
      q.enQueue(event);
      q.enQueue(event);
      p("(5) size = " + q.getSize());

      q.deQueue();
      q.deQueue();
      q.deQueue();

      q.deQueue();
      p("(1) size = " + q.getSize());
    }
    catch (InterruptedException localInterruptedException)
    {
    }
  }
}