package com.syuesoft.util;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SMSSendUtil {
	//private static Logger log = Logger.getLogger(SMSSend.class);
	static String serviceUrl = null;
	static Service service = new Service(); 
	static String ID = "syuetest";
	static String PASSWROD = "123456";
	static String NEWPASSWROD = "";
    static{
    	serviceUrl = "http://61.147.124.107:8080/SGIP/SGIPService.php?wsdl";  
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//(new SMSSend()).sendSms("13659238435;13651324512;13659238435;13512345613", "", "");
		//getUserInfo();
		//getResponse();
		//updatePassword();
		//test();
	}
    
	/**
	 * 短信发送
	 * @param mod
	 * @param date
	 */
	
	public int sendSms(String mod, String context, String date){
	    Call call;
	    int a = 100;
	    date = date.equals("") ? null : date;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(serviceUrl));   
			call.setOperationName("sendSms");   
			String  reVal  = call.invoke(new Object[] {ID,PASSWROD,mod.trim(),context.trim(),"",date,"UTF-8"}).toString();
			a = xmlElements(reVal);
			//log.info("短信发送返回值："+reVal);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	/**
	 * 获取短信剩余条数
	 */
	public void getUserInfo(){
		Call call;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(serviceUrl));   
			call.setOperationName("getUserInfo");   
			String reVal = call.invoke(new Object[] {ID,PASSWROD}).toString(); 
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取回复
	 * 
	 */
	public void getResponse(){
		Call call;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(serviceUrl));   
			call.setOperationName("getResponse");   
			String reVal = call.invoke(new Object[] {ID,PASSWROD}).toString();  
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改密码
	 */
	public void updatePassword(){
		Call call;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(serviceUrl));   
			call.setOperationName("updatePassword");   
			String reVal = call.invoke(new Object[] {ID,PASSWROD,NEWPASSWROD}).toString();  
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析返回的xml格式字符串
	 */
	public int xmlElements(String xmlDoc) {
		
		int r = 100;
		//创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        //创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        try {
            //通过输入源构造一个Document
            Document doc = sb.build(source);
            //取的根元素
            Element root = doc.getRootElement();
            //得到根元素所有子元素的集合
            List jiedian = root.getChildren();
            //获得XML中的命名空间（XML中未定义可不写）
            //Namespace ns = root.getNamespace();
            Element et = null;
            for(int i=0;i<jiedian.size();i++){
                et = (Element) jiedian.get(i);//循环依次得到子元素
				r = Integer.parseInt(et.getText());
            }
           
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }
	
	//测试方法
	public static void test() {
		try {
			DocumentBuilderFactory doc = DocumentBuilderFactory.newInstance();
			DocumentBuilder bud = doc.newDocumentBuilder();
			org.w3c.dom.Document dom = bud.parse("E:\\test.txt");
			org.w3c.dom.Element dcm = dom.getDocumentElement();
			NodeList list = dcm.getElementsByTagName("value");
			for (int i = 0; i < list.getLength(); i++) {
                System.out.println("这是什么："+dcm.getElementsByTagName("test1").item(i).getFirstChild().getNodeValue());
			}
		} catch (Exception e) {
            e.printStackTrace();
        } 
		
	}
}