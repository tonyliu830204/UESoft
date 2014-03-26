<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.io.*" %>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%
String id=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId().toString();
   BufferedInputStream fileIn = new BufferedInputStream(request.getInputStream());
   request.setCharacterEncoding("UTF-8");
   String fn = request.getParameter("fileName");
   fn=new String(fn.getBytes("ISO-8859-1"),"UTF-8");
   byte[] buf = new byte[1024];
   File file = new File(pageContext.getRequest().getRealPath("tempImgs")+"\\"+id + fn);
   BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(file));
   while (true) {
      int bytesIn = fileIn.read(buf, 0, 1024);
      if (bytesIn == -1) {
         break;
      } else {
         fileOut.write(buf, 0, bytesIn);
      }
   }
   
   fileOut.flush();
   fileOut.close();
   out.print(file.getAbsolutePath());
%>