<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="net.sf.jasperreports.engine.JasperFillManager" %>
<%@ page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@ page import="net.sf.jasperreports.engine.JRException"%>
<%@ page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@ page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@ page import="net.sf.jasperreports.engine.export.JRHtmlExporter"%>
<%@ page import="net.sf.jasperreports.engine.JasperReport"%>
<%@ page import="net.sf.jasperreports.engine.export.JRHtmlExporterParameter"%>
<%@ page import="com.syuesoft.util.WPS" %>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'PrintTest1.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/Lodop/print.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/print/print.js"></script>
	<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
		<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop64.exe"></embed>
	</object> 
	
  </head>
<body id="aaa" class="easyui-layout">
	  <div region="north" split="true" style="height:100px;">
	    <p>1:<a href="javascript:prn1_preview()">打印预览</a><br/>
	       2:<a href="javascript:prn1_print()">直接打印</a><br/>    
	       3:<a href="javascript:prn1_printA()">选择打印机</a><br/>
	       4:<a href="javascript:prn1_manage()">打印维护</a>
	    </p>
	  </div>  
	  <div id="StGoodsStorageReportDiv" region="center" style="background:#eee;">
	   <%
	    String tomcatpath = WPS.getRootPath();
	    String filepath =  tomcatpath+"/sell/sell_financemanage/sellBeforebandBalance.jasper";//调用你通过ireport编译好的jasper文件即可。
	    File reportFile = new File(filepath);
		ResourceBundle bundleMessage = PropertyResourceBundle.getBundle("jdbc");
		String connectionurl = bundleMessage.getString("connection.url");
		String username = bundleMessage.getString("connection.username");
	    String password = bundleMessage.getString("connection.password");
		String driver_n = bundleMessage.getString("connection.driver_class");
	   	Class.forName(driver_n);
	   	Connection conn = DriverManager.getConnection(connectionurl, username, password);
	   	Map parameters = new HashMap();
	   	String details_Id=request.getParameter("details_Id");
	   	parameters.put("details_id", Integer.parseInt(details_Id));
	   	try {
	   	JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getAbsoluteFile());
	   		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
	   		JRHtmlExporter exporter = new JRHtmlExporter();
	   		StringBuffer sbuffer = new StringBuffer();
	   		exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
	   		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
	   		exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
	   		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
	   		exporter.exportReport();
	   		conn.close();
	   	} catch (JRException ex) {
	   		out.print("Jasper Output Error:" + ex.getMessage());
	   	}
	   %>
	  </div>
 </body>  
</html>


