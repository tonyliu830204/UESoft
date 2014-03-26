package com.syuesoft.util;

import java.util.Date;

public class IncrementId {
	public static String getItemId(){
		return "DIY"+new Date().getTime();
	}
}	
