package com.syuesoft.qx.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * struts2默认的action 字符格式转换 、 得到资源文件中的信息 HTTPRequest HTTP SESSION APPLICATION
 * 
 * @author wucuan
 * 
 */
public class DefaultAction extends ActionSupport
{

    private static final long serialVersionUID = 6925889465025992451L;

    private static final Log log = LogFactory.getLog(DefaultAction.class);

    /* 方法区域 */

    /**
     * 1> 字符格式的转换
     */

    /**
     * 01> 把iso-8859-1格式字符串转换为gbk
     */
    public String iso2gb(String message)
    {
        if (null == message)
        {
            message = "";
        }
        try
        {
            byte[] buff = message.getBytes("ISO-8859-1");
            message = new String(buff, "GBK");
        }
        catch(UnsupportedEncodingException e)
        {
            // e.printStackTrace();
        }

        return message;
    }

    /**
     * 02> 把iso-8859-1格式字符串转换为UTF8
     */
    public String iso2UTF8(String message)
    {
        if (null == message)
        {
            message = "";
        }
        try
        {
            byte[] buff = message.getBytes("ISO-8859-1");
            message = new String(buff, "UTF-8");
        }
        catch(UnsupportedEncodingException e)
        {
            // e.printStackTrace();
        }

        return message;
    }

    /**
     * 03>把gbk格式字符串转换为UTF8
     */
    public String gbk2UTF8(String message)
    {
        if (null == message)
        {
            message = "";
        }
        try
        {
            byte[] buff = message.getBytes("GBK");
            message = new String(buff, "UTF-8");
        }
        catch(UnsupportedEncodingException e)
        {
            // e.printStackTrace();
        }

        return message;
    }

    /**
     * 2> 得到HTTPRequest
     * 
     * @return
     */
    protected HttpServletRequest getRequest()
    {
        ActionContext ctx = ActionContext.getContext(); // 获取action 上下文
        HttpServletRequest request = (HttpServletRequest) ctx
                .get(ServletActionContext.HTTP_REQUEST); // 获取
        // HttpServletRequest对象
        return request;
    }

    /**
     * 01>在REQUEST存放属性
     * 
     * @param attrName
     *            属性名称
     * @param attrValue
     *            属性值
     */
    protected void setReqAttr(String attrName, Object attrValue)
    {
        this.getRequest().setAttribute(attrName, attrValue);
    }

    /**
     * 
     * 02> 在REQUEST获取属性
     * 
     * @param attrName
     *            属性名称
     * @param clazz
     *            返回类型的.class
     */
    protected Object getReqAttr(String attrName)
    {
        Object obj = this.getRequest().getAttribute(attrName);
        return obj;
    }

    /**
     * 3> 得到HTTP SESSION
     * 
     * @return
     */
    protected HttpSession getSession()
    {
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx
                .get(ServletActionContext.HTTP_REQUEST);
        return request.getSession();
    }

    /**
     * 01> 在Session存放属性
     * 
     * @param attrName
     *            属性名称
     * @param attrValue
     *            属性值
     */
    protected void setSessionAttr(String attrName, Object attrValue)
    {
        this.getSession().setAttribute(attrName, attrValue);
    }

    /**
     * 02>在Session中得到存放的对象
     * 
     * @param attrName
     *            属性名称
     * @param clazz
     *            返回类型的.class
     * @return Session中存放的对象
     */
    protected Object getSessionAttr(String attrName)
    {
        Object obj = this.getSession().getAttribute(attrName);
        return obj;
    }

    /**
     * 4> 得到APPLICATION作用域
     * 
     * @return
     */
    protected ServletContext getApplication()
    {
        return this.getSession().getServletContext();
    }

    /**
     * 01>在Application作用域中存放对象
     * 
     * @param attrName
     * @param attrValue
     */
    protected void setAppAttr(String attrName, Object attrValue)
    {
        this.getApplication().setAttribute(attrName, attrValue);
    }

    /**
     * 02> 在Application中得到存放的对象
     * 
     * @param attrName
     *            属性名称
     * @param clazz
     *            返回类型的.class
     * @return Application中存放的对象
     */
    protected Object getAppAttr(String attrName)
    {
        Object obj = this.getApplication().getAttribute(attrName);
        return obj;
    }

    /**
     * > 得到资源文件中的信息
     * 
     * @param key
     * @return
     */
    public String getResource(String key)
    {
        String value = super.getText(key);
        // value = iso2gb(value); 可以将其内容转换成任意格式
        return value;
    }

    /**
     * 将对象转换为json格式并返回
     * 
     * @param obj
     */
    public void writeJson(Object obj)
    {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        HttpServletResponse response = (HttpServletResponse) ServletActionContext
                .getResponse();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.println(JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd"));
            // out.println(gson.toJson(obj));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } finally
        {
            out.flush();
            out.close();
        }
    }

    /**
     * 将对象转换为json格式并返回
     * 
     * @param obj
     * @param flag
     *            true标识返回日期类型为yyyy-MM-dd hh:mm:ss
     */
    public void writeJson(Object obj, boolean flag)
    {
        String dateFormat = "";
        if (flag)
        {
            dateFormat = "yyyy-MM-dd HH:mm:ss";
        }
        else
        {
            dateFormat = "MM-dd";
        }
        HttpServletResponse response = (HttpServletResponse) ServletActionContext
                .getResponse();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.println(JSON.toJSONStringWithDateFormat(obj, dateFormat));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } finally
        {
            out.flush();
            out.close();
        }
    }
    
    protected void sendJson(String json)
    {
        HttpServletResponse response = ServletActionContext
                .getResponse();
        response.setHeader("contentType", "text/html");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try
        {
            out = response.getWriter();
            out.print(json);
        }
        catch (IOException e)
        {
            log.error("异步传输异常", e);
        }
    }
}
