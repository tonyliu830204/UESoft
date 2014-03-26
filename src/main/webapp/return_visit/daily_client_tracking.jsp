<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>日常客户跟踪</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/return_visit/daily_client_tracking.js"></script>
  </head>
  
  <body>
	   <div id="cc" class="easyui-layout" style="width:600px;height:400px;"  fit="true"  >
         
       <!-- 条件区域 -->
		    <div region="north"  split="false" style="background:#eee;height:70px;"  border="false"  >
		       <div id="dd" class="easyui-layout" style="width:600px;height: 30px;"   fit="true">
		             <div   region="north" style="overflow: hidden;padding:3px; background:#eee; height:30px;"  border="false" >
			            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addAdvice();">维修建议及提醒</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryAdvice();">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
						<!--<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
					 
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-help" plain="true">设置</a> -->
		            </div> 
		            <div  region="center"  style="overflow: hidden;padding:3px; background:#eee; height:40px;" border="false">
		            <!-- 查询条件 -->
					<form id="richanggenzongtixing_form_id" method="post">
  						<table border="0">
							<tr>
								<td>建议日期：</td>
								<td>
									<input style="width: 100px;" id="genzongdaoqi"  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'genzongdaoqi2\',{d:-1})}'})"  name="adviceTime"/>至
									<input style="width: 100px;" id="genzongdaoqi2"  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'genzongdaoqi\',{d:1})}'})"   name="adviceTime"/>
								</td>
								<td>车辆牌照：</td>
								<td><input type="text" style="width:100px" name="carLicense"/></td>
								<td>处理情况：</td>
								<td><input type="text"  id="proces_state" name="procesState"  style="width: 150px;" class="easyui-combobox" data-options="
									url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.PROCESSTATE_TAG.PROCESSTATEKEY %>',
									valueField:'id',
									textField:'text',										
									validType:'isSelected[\'#proces_state\']',
								 "/></td>
							<td>维修建议：</td>
								<td><input type="text" style="width:200px" name="adviceContext"/></td>
							</tr>
  						</table>
  				      </form>		  
		            </div>
	         </div>
 </div>
		           
	    	
 <!-- 展现数据list区域 -->	  
     
		    <div region="center"  style="background:#eee;" border="false">
		         <table id="Daily_client_tracking_center_id"></table>
		    </div>  
		    
	        <!-- 编辑区域 -->
			
   </div>
  </body>
</html>
