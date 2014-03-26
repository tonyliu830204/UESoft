package com.jfree.chart;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 发送个性短信,即给不同的手机号发不同的内容,短信内容和手机号用英文的逗号对应好
 * 
 *
 */
public class Demo_Gxmt {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		//输入软件序列号和密码
	    String sn="SDK-WSS-010-05881";
        String pwd="A84505-c";
		Client client=new Client(sn,pwd);		
		//发送个性短信		
		String result = client.gxmt("18966719180", URLEncoder.encode("验证码：334897【西安优忆】", "GB2312"), "", "", "");
		//输出返回标识
		System.out.print(result);
	}
}