<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>装潢项目</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body  >
	<script type="text/javascript">
	$(function(){
			$('#zsProData').datagrid({
					url:'${pageContext.request.contextPath}/zsProjectAction!getPageZsPro.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'zsprojectId',
					singleSelect : true,
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编号',
							field : 'zsprojectId',
							width : 50,
							hidden:true
					    },{
							title : '项目代码',
							field : 'projectCode',
							width : 100
					    }, {
							title : '项目名称',
							field : 'projectName',
							width : 100
						}, {
							title : '成本额',
							field : 'projectCostamount',
							width : 80
						}, {
							title : '销售价',
							field : 'projectSellamount',
							width : 80
						}, {
							title : '备注',
							field : 'projectRemark',
							width : 100
						}
				        ]],
			
		
				 onClickRow : function (rowIndex, rowData){
				   $(this).datagrid('unselectRow', rowIndex);
				},
				onDblClickRow : function(rowIndex, rowData){
					var rows = $("#zhList").datagrid('getRows');
					if(rows.length){
						for(var i = 0;  i < rows.length; i++){
							if(rows[i].zsprojectId == rowData.zsprojectId){  
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
					$("#zhList").datagrid('appendRow', rowData);
					var index = $("#zhList").datagrid('getRowIndex', rowData);
					copyPartsDateAndBindEvent($("#zhList"), index, rowData);	
				}
				});
	});
	
function copyPartsDateAndBindEvent(id, rowIndex, rowData)
{
	
	id.datagrid('beginEdit', rowIndex);
			var ed = id.datagrid('getEditors', rowIndex);
			if(ed[0]){
				ed[0].target.numberbox('setValue', rowData.unitNum);
				ed[0].target.select();
				ed[0].target.click(function (){
					ed[0].target.select();
				});
				ed[0].target.keydown(function (e){
					if(e.keyCode == '13'){
						ed[1].target.select();
					}
				});
			}
			 ed[0].target.select();
			 	if(ed[1]){
				ed[1].target.numberbox('setValue', rowData.projectCostamount);
				ed[1].target.select();
				ed[1].target.click(function (){
					ed[1].target.select();
				});
				ed[1].target.keydown(function (e){
					if(e.keyCode == '13'){
						ed[2].target.select();
					}
				});
			}
			if(ed[2]){
				ed[2].target.numberbox('setValue', rowData.projectSellamount);
				ed[2].target.select();
				ed[2].target.click(function (){
					ed[2].target.select();
				});
				ed[2].target.keydown(function (e){
					if(e.keyCode == '13'){
						ed[3].target.select();
					}
				});
			}
			if(ed[3]){
				 ed[3].target.numberbox('setValue', rowData.preferentialPrice);
						ed[3].target.select();
    				ed[3].target.click(function (){
    					ed[3].target.select();
    				});
    				ed[3].target.keydown(function (e){
    					if(e.keyCode == '13'){
    						if(rowIndex < id.datagrid('getData').total - 1){
    							var ed = id.datagrid('getEditors', rowIndex + 1);
    							ed[0].target.select();
    						}else{
    							var ed = id.datagrid('getEditors', 0);
    							ed[0].target.select();
    						}
    					}
					
				 });
			}
			ed[0].target.select();
			ed[0].target.bind('keyup', function() {
				var unitNum = ed[0].target.val();//
				if(unitNum==null||unitNum==''||isNaN(unitNum)){
					ed[0].target.numberbox('setValue', '1');
					unitNum=1;
				}
				var projectCostamount = ed[1].target.val();//代表成本价
				ed[4].target.numberbox('setValue', accMul(parseFloat(projectCostamount),parseFloat(unitNum)));
				var projectCostamount = ed[3].target.val();//优惠价
				ed[5].target.numberbox('setValue', accMul(parseFloat(unitNum),parseFloat(projectCostamount)));	
				
			});	
			ed[1].target.bind('keyup', function() {
				var num = ed[0].target.val();
				var price = ed[1].target.val();
				var amount = accMul(parseFloat(num), parseFloat(price));
				ed[4].target.numberbox('setValue', amount);
			});	
			ed[3].target.bind('keyup', function() {
				var num = ed[0].target.val();
				var price = ed[3].target.val();
				var amount = accMul(parseFloat(num), parseFloat(price));
				ed[5].target.numberbox('setValue', amount);
			});		
}
var queryProject = function (){
	$('#zsProData').datagrid('unselectAll');
	$('#zsProData').datagrid('load', serializeObject($('#proQueryForm')));
}
function clearS(){
		$('#proQueryForm').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'dbProjectAction!getPagePro.action',
			data:$('#proQueryForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#zsProData').datagrid('load',r);
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
													<td><input type="text" id="zsProCode" name="zsProCode"  size="10" style="background-color: #c0d8d8;"/></td>
													<td>项目名称</td>
													<td><input type="text" id="zsProName" name="zsProName"  style="background-color:#c0d8d8;" />
										<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryProject();">查询</a>
										<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearS();">清空</a>
										</td>
									</tr>
							</table>
							
				</form>
			</div>
			<div region="center" style="background: #eee" border="false" >
					<table id="zsProData"></table>
			</div>
	</div>
	</body>
</html>