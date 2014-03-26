package org.future.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class compareDoubleToString {
	static List<Long> numberList = new ArrayList();
	private long numbera;
	private long numberb;

	static {
		long n = 99999L;
		for (long i = 1L; i < n; i += 1L) {
			boolean b = true;
			if (i != 1L) {
				for (long j = 2L; j < i; j += 1L) {
					if (i % j == 0L) {
						b = false;
						break;
					}
				}
				if (b)
					numberList.add(Long.valueOf(i));
			}
		}
	}

	public String compareDoubleSimple(Double a, Double b) {
		return compareDouble(a, b, Boolean.valueOf(false));
	}

	public String compareDoublePercentum(Double a, Double b) {
		return compareDouble(a, b, Boolean.valueOf(true));
	}

	public String compareDouble(Double a, Double b, Boolean tag) {
		if ((a == null) || (a.doubleValue() == 0.0D) || (b == null)
				|| (b.doubleValue() == 0.0D)) {
			String temp = "";
			if ((a == null) || (a.doubleValue() == 0.0D))
				temp = temp + "0:";
			else {
				temp = temp + "10:";
			}
			if ((b == null) || (b.doubleValue() == 0.0D))
				temp = temp + "0";
			else {
				temp = temp + "10";
			}
			return temp;
		}
		this.numbera = CompareUtil.doubleFormat(a.doubleValue());
		this.numberb = CompareUtil.doubleFormat(b.doubleValue());
		validate(numberList);
		if ((tag == null) || (!tag.booleanValue())) {
			return this.numbera + ":" + this.numberb;
		}
		double tempa = this.numbera;
		double tempb = this.numberb;
		double sa = tempa / (tempa + tempb) * 10.0D;
		double sb = tempb / (tempa + tempb) * 10.0D;
		return CompareUtil.doubleFormat(sa) + ":"
				+ CompareUtil.doubleFormat(sb);
	}

	private static List<Long> getPrimeNumber() {
		List list = new ArrayList();
		BigDecimal bd = null;
		long n = 99999L;
		for (long i = 1L; i < n; i += 1L) {
			boolean b = true;
			if (i != 1L) {
				for (long j = 2L; j < i; j += 1L) {
					if (i % j == 0L) {
						b = false;
						break;
					}
				}
				if (b) {
					list.add(Long.valueOf(i));
				}
			}
		}
		return list;
	}

	private void validate(List<Long> list) {
		boolean flag = true;
		while (flag) {
			boolean tag = false;
			for (Long ls : numberList) {
				if ((CompareUtil.isDivide(this.numbera, ls.longValue()))
						&& (CompareUtil.isDivide(this.numberb, ls.longValue()))) {
					this.numbera /= ls.longValue();
					this.numberb /= ls.longValue();
					tag = true;
					break;
				}
			}
			if (!tag)
				flag = false;
		}
	}
}