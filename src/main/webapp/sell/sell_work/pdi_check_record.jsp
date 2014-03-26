<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.util.SystemUser"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>PDI检测</title>
  </head>
  
  <body>
  	 <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/sell_work.js"></script>
    
   <div id="cc" class="easyui-layout" style="width: 800px; height: 600px;" fit="true" border="false">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
		<form id="form_pdi_check_record_id">
			<table>
				<tr>
					<th>VIN编号：</th>
					<th><input/></th>
					<th>检验日期：</th>
					<th><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'check_Date2\',{d:-1})}'})" name="check_Date" id="check_Date" style="width: 110px;"/> 至 </th>
					<th><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'check_Date\',{d:1})}'})" name="check_Date2" id="check_Date2" style="width: 110px;"/></th>
					<th>检验员：</th>
					<th>
						<input name="check_Person" style="width:130px"
							class="easyui-combobox"	data-options="
							url : 'sellUtilAction!findUsers.action',
							valueField:'id',  
							textField:'name',
							multiple:false,
							mode:'remote' "
							/>
					</th>
					<th>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit($('#form_pdi_check_record_id'),$('#datagrid_pdi_check_record_id'),$('#check_Date'),$('#check_Date2'));">查询</a>
					</th>
					<th>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear($('#form_pdi_check_record_id'),$('#datagrid_pdi_check_record_id'),$('#check_Date'),$('#check_Date2'));">清空</a>
					</th>
				</tr>
			</table>
		</form>
		</div>
		<div region="center"  style="background:#eee;" border="false">
			<table id="datagrid_pdi_check_record_id"></table>
		</div>
	</div>

  </body>
</html>
