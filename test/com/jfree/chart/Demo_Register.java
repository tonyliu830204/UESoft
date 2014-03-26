package com.jfree.chart;
import java.io.UnsupportedEncodingException;

/**
 * 注册方法
 * @author acer
 *
 */
public class Demo_Register {

	public static void main(String[] args) throws UnsupportedEncodingException{
		//输入软件序列号和密码
		String sn="SDK-WSS-010-05881";
		String pwd="A84505-c";
		Client client=new Client(sn,pwd);		

		//我们的序列号首次使用要先注册，且只需注册一次
		//
		//返回值说明：注册成功返回：0  成功
		String result_register = client.register("陕西省", "西安市", "计算机", "西安优忆", "莫坤", "88369859", "18966719186", "111@126.com", "88369859", "西安", "710000","");
		if(result_register==null||result_register.equals(""))
		{
		System.out.print("没有找到http://sdk2.zucp.net:8060/webservice.asmx，这个地址，您的系统可能是utf8,打开 Client.java 这个文件，请看第9到第16的注释");
		return;
		}
		
		if(result_register.startsWith("-"))
		{
			if(result_register=="-1 操作失败")
			{
			System.out.print("重复注册");
			return;
			}
			System.out.print("注册失败，返回值为："+result_register+"。请检查输入内容");
		}else {
			System.out.print("恭喜您，注册成功！");
		}
	}
}
