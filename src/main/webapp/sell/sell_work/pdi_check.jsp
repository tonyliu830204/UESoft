<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@page import="com.syuesoft.util.SystemUser"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%
String xsCarId = request.getParameter("xs_Car_Id");
String xsCarSelId = request.getParameter("xs_Car_Sel_Id");
String sellcode = request.getParameter("sellcode");
String vinCode = request.getParameter("vinCode");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>PDI检测</title>
    <script type="text/javascript">
    	//PDI检测
	   $(function(){
	   		 $('#datagrid_pdi_check_id').datagrid({
				url : 'carSellManageAction!getPdiCheck.action?xs_Car_Sel_Id=<%=request.getParameter("xs_Car_Sel_Id") %>',
				fit : true,
				rownumbers : true,
				fitColumns : true,
				singleSelect : true,
				idField : 'child_Id',
				loadMsg : "数据加载中，请稍后……",
				columns : [[{
					field : 'Pid',
					title : '编号',
					width : 40,
					sortable : true,
					hidden : true
					
				},{
					field : 'child_Id',
					title : '编号',
					width : 40,
					sortable : true,
					hidden : true
					
				},{
					field : 'check_Comtent',
					title : '检验内容',
					width : 400,
					sortable : true
					
				},{
					field : 'status',
					title : '检测情况',
					width : 60,
					formatter:function(value,row,index){
						return row.status_Name;
						},
					editor : {
						type : 'combobox',
						options : {
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.PDISTATUS %>',
							valueField:'id',   
						    textField:'text',
						    mode:'remote'
					    }
					 
					}
				},{
					field : 'remark',
					title : '备注',
					width : 200,
					editor :{
						type : 'text'
					}
				}
				]],onClickRow : function(rowIndex, rowData){
				$('#datagrid_pdi_check_id').datagrid('beginEdit', rowIndex);
				},onLoadSuccess : function (data){
					//var rows=$('#datagrid_pdi_check_id').datagrid('getRows');
					//if(rows!=null&&rows.length>0){
						//for (var i=0;i<rows.length;i++) {
						//	$('#datagrid_pdi_check_id').datagrid('beginEdit',$('#datagrid_pdi_check_id').datagrid('getRowIndex',rows[i]));		
						//}
					}
				});
			 });
	   
    </script>
  </head>
  
  <body>
  	 <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/sell_work.js"></script>
    
   <div id="cc" class="easyui-layout" style="width: 800px; height: 600px;" fit="true" border="false">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
		<form id="form_pdi_check_id">
			<input type="hidden" name="xs_Car_Sel_Id" id="xscarselids" value="<%=xsCarSelId %>"/>
			<input type="hidden" name="xs_Car_Id" id="xscarids" value="<%=xsCarId %>"/>
			<table>
				<tr>
					<th>车辆VIN：</th>
					<th><input type="text" name="vinCode" id="vinCode" value="<%=vinCode%>"/></th>
					<th>销售编号：</th>
					<th><input type="text" name="sellCode" id="sellcode" value="<%=sellcode %>"/></th>
					
					
					<th>检验日期：</th>
					<th><input id="txtDate" class="Wdate" disabled="disabled" type="text" onfocus="WdatePicker()" value="<%=new java.sql.Date(new java.util.Date().getTime()) %>" id="check_Date" name="check_Date"/></th>
					<th>检验员：</th>
					<th>
						<input name="check_Person" style="width:130px" disabled="disabled"  value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
							class="easyui-combobox"	data-options="
							url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							required:true,
							valueField:'id',  
							textField:'text',
							multiple:false,
							mode:'remote'"
							/>
					</th>
					<th>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addPDI();">保存</a>
					</th>
				</tr>
			</table>
		</form>
		</div>
		<div region="center"  style="background:#eee;" border="false">
			<table id="datagrid_pdi_check_id"></table>
		</div>
	</div>

  </body>
</html>
