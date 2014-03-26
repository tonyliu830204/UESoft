<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆选择</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body  >
	<script type="text/javascript">
	$(function(){
		$('#dbProject').datagrid({
					url:'${pageContext.request.contextPath}/dbProjectAction!getPagePro.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'projectId',
					singleSelect : true,
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编号',
							field : 'projectId',
							width : 50,
							hidden:true
					    },{
							title : '项目代码',
							field : 'projectCode',
							width : 50
					    }, {
							title : '项目名称',
							field : 'projectName',
							width : 100
						}, {
							title : '代办成本',
							field : 'projectMomay',
							width : 100
						}, {
							title : '收费金额',
							field : 'projectAmount',
							width : 100
						}, {
							title : '收取部门',
							field : 'projectDept',
							width : 100		
						}
				        ]],
				 onClickRow : function (rowIndex, rowData){
				   $(this).datagrid('unselectRow', rowIndex);
				},
				onDblClickRow : function(rowIndex, rowData){
					var rows = $("#dbPro").datagrid('getRows');
					if(rows.length){
						for(var i = 0;  i < rows.length; i++){
							if(rows[i].projectId == rowData.projectId){  
							       $.messager.show({
										title : '提示',
										msg : '该条记录已经被选取！',
										showType : 'slide'
									}); 
								return;
							}
						}
					}
					$(this).datagrid('deleteRow', rowIndex);
					$("#dbPro").datagrid('appendRow', rowData);
					var index = $("#dbPro").datagrid('getRowIndex', rowData);
					copyPartsDateAndBindEvent($("#dbPro"), index, rowData);	
				}
				});
	});
	
function copyPartsDateAndBindEvent(id, rowIndex, rowData)
{
	id.datagrid('beginEdit', rowIndex);
	var ed = id.datagrid('getEditors', rowIndex);			
}
var queryProject = function (){
	$('#dbProject').datagrid('unselectAll');
	$('#dbProject').datagrid('load', serializeObject($('#proQueryForm')));
}
function clearSearch(){
		$('#proQueryForm').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'dbProjectAction!getPagePro.action',
			data:$('#proQueryForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#dbProject').datagrid('load',r);
			}
	    });
	}
	</script>
	<div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 80px;" border="false">
				<form id="proQueryForm" name="carInfoQueryForm" method="post"  >
							<table align="center">
								  <tr>
										<td>项目代码</td>
										<td><input type="text" id="proCode" name="proCode"  size="10" style="background-color: #c0d8d8;"/></td>
										<td>项目名称</td>
										<td><input type="text" id="proName" name="proName"  style="background-color:#c0d8d8;" />
										<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryProject();">查询</a>
										<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearch();">清空</a>
										</td>
									</tr>
							</table>
							
				</form>
			</div>
			<div region="center" style="background: #eee" border="false" >
					<table id="dbProject"></table>
			</div>
	</div>
	</body>
</html>