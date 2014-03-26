package com.jfree.chart;
import java.io.UnsupportedEncodingException;

/**
 * 查询余额
 * @author acer
 *
 */
public class Demo_GetBalance {
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		//输入软件序列号和密码

	    String sn="SDK-WSS-010-05881";
        String pwd="A84505-c";
		Client client=new Client(sn,pwd);
		
		//查询余额
		String result_balance = client.getBalance();
		System.out.print("您的余额为 : "+result_balance);
	}
}
