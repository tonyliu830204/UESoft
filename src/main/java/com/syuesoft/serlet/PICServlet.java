package com.syuesoft.serlet;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;

/** 
 * @ClassName: PICServlet 
 * @Description: TODO(车辆品牌图标) 
 * @author HeXin 
 * @date 2013-10-10 下午04:32:37 
 * @version 1.0 
 */
public class PICServlet extends HttpServlet
{

    public PICServlet()
    {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost( request,  response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Connection con = null;
        String CBRD_ID = request.getParameter("cbrdId");
        ResourceBundle bundleMessage = PropertyResourceBundle.getBundle("jdbc");
        String connectionurl = bundleMessage.getString("connection.url");
        String username = bundleMessage.getString("connection.username");
        String password = bundleMessage.getString("connection.password");
        String driver_n = bundleMessage.getString("connection.driver_class");
        try{
            Class.forName(driver_n).newInstance();
            con = DriverManager.getConnection( connectionurl, username, password);
            // 准备语句执行对象
            Statement stmt = con.createStatement();
            String sql = " SELECT CBRD_LOGO FROM bas_car_brand WHERE CBRD_ID = ' "+ CBRD_ID +"'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                byte[] bs = rs.getBytes(1);
                if(bs != null){
                    response.setContentType("image/jpeg");
                    response.setHeader("Pragma","No-cache");  
                    response.setHeader("Cache-Control","no-cache");  
                    response.setDateHeader("Expires", 0);  
                    ServletOutputStream outs = response.getOutputStream(); 
                    outs.write(bs);
                    outs.flush();
                    outs.close();
                }
                rs.close(); 
            }else {
                rs.close();
                response.sendRedirect("images/error-500.gif");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try
            {
                con.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}