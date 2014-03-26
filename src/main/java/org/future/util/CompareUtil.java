package org.future.util;

import java.math.BigDecimal;

public class CompareUtil
{
  static boolean isDivide(long d1, long d2)
  {
    return d1 % d2 == 0L;
  }

  static long doubleFormat(double value)
  {
    BigDecimal b = new BigDecimal(value);
    long changeValue = b.setScale(0, 4).longValue();
    return changeValue;
  }
}