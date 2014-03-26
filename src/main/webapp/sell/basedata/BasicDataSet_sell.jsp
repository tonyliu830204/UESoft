<%@ page language="java" import="java.util.*" pageEncoding="utf-8" deferredSyntaxAllowedAsLiteral="true"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>

<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	 <head>
	    <title>数据字典</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
		 var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/sell/basedata/BasicData_sell.js"></script>
	 </head>
	 <body>
		 <div id="cc" class="easyui-layout" fit="true" border="false">   
		       <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
				<privilege:enable code="DATA_ADD">
				 <a id='_add' href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" >新增</a>
				</privilege:enable>
				<privilege:enable code="DATA_REMOVE">
				 <a id='_remove' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除</a>
				</privilege:enable>
				<privilege:enable code="DATA_UPDATE">
				 <a id='_update' href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-edit" plain="true" >修改</a>
				</privilege:enable>
				<privilege:enable code="DATA_SEARCH">
				 <a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" >查询</a>
				</privilege:enable>
				<privilege:enable code="DATA_CLEAR"> 
				 <a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">清空</a>
				</privilege:enable>
				<privilege:enable code="DATA_PRINT"> 
				 <a id='_print' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
				</privilege:enable>
				<privilege:enable code="DATA_EXPORT"> 
				 <a id='_export' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
				</privilege:enable>
				<!--<privilege:enable code="DATA_IMPORT"> 
				 <a id='_import' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
				</privilege:enable>
				--><span id="button"></span><br/>
		       </div>  
		       <div region="center"  split="false" border="false">
				 <div id="tt" class="easyui-tabs" fit="true" border="false">  
					 <div title="类型列表" style="display:block;"  fit="true">
						<div id="cc" class="easyui-layout" style="width:600px;height:500px;" fit="true" border="false">							  				
							  <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
								<form id="StPurOrderSearchForm" >
									<table>
										<tr>
										    <td>键名</td>
										    <td><input type="text" id="pqueryKey" name="pqueryKey"  style="background-color: #c0d8d8;"/></td>
										    <td>键值</td>
										    <td><input type="text" id="pqueryValue" name="pqueryValue"  style="background-color:#c0d8d8;" /></td>
										</tr>
									</table>                                                  
								</form>
							  </div>  										  
							  <div id="leixin" region="center" style="background:#eee;" border="false">
								<table id="stPurOrderTable" name="stPurOrderTable"></table>
							  </div> 		
						</div>
					 </div>	
					 <div title="类型明细" style="display:block;" closable="false"  fit="true">				      
						<div id="cc" class="easyui-layout" style="width:600px;height:500px;" fit="true" >
							  <div region="north" title="条件查询" split="false" style="overflow: hidden;background:#eee;height:80px;" border="false">  
								<form id="StPurOrderForm" method="post" >	
								     
									<table>
										<tr>
										    <td>键名</td>
										    <td><input type="text" id="cquerydataKey" name="cquerydataKey"  style="background-color: #c0d8d8;" /></td>
										    <td>键值</td>
										    <td><input type="text" id="cquerydataValue" name="cquerydataValue"  style="background-color:#c0d8d8;" /></td>
										</tr>
								        </table>   
								</form>						
							  </div>
							  <div id="mingxi" region="center" style="background:#eee;" border="false">
								<table id="StOrderItemTable" name="StOrderItemTable"></table>
							  </div>  	  
						</div> 	
					 </div>  
				</div>
		       </div>
		</div>
	 </body>
</html>
