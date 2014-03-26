<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
    boolean failash = false;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>重启tomcat</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	</head>
  
  <body>
    <%
	Runtime run = Runtime.getRuntime();
	Runtime runtime = Runtime.getRuntime();
	try {
		 String batName = "restart.bat";
		 String tomcatPath = application.getRealPath("/");
		 path = path.replace("/","\\");
		 tomcatPath=tomcatPath.replace("webapps", "bin").replace(path, "");
		 File file = new File(tomcatPath); 
		 String batPath = tomcatPath+file.separator+batName;
		 Process process = runtime.exec(batPath);
		 runtime.gc();
		 BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		 String line = null;
		 String result = "";
		 while((line=br.readLine()) != null) {
			result = line + "\r\n";
			out.print(result);
	     }
	     br.close();
	     failash = true;
	} catch (IOException e) {
		 e.printStackTrace();
	}
	%>
	<% 
	if(failash){
	%>
	<script language="javascript"type="text/javascript">
           window.location.href="${pageContext.request.contextPath}/";
    </script>
    <% 
	}
    %>
  </body>
</html>