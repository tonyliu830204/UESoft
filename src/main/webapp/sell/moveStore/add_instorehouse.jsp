<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants" %>
<%
	String outInstore=request.getParameter("outHouse");
%>
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
	<body class="easyui-layout" >
	<script type="text/javascript">
	$(function(){
		$selectionCar = $('#selectionCar');
		$selectionCar.datagrid({
			url :'instoreMoveAction!getPagerCar.action?outInstorehouse='+<%=outInstore%>,
			pagination : true,
		   	fit:true,
			fitColumns : false,
			idField : 'carId',
			singleSelect : true,
			rownumbers : true,
				columns : [ [ {
							title : '入库单编号',
							field : 'instorehouseId',
							hidden:true
					    }, {
							title : '仓库编号',
							field : 'warehouse',
							hidden:true
						}, {
							title : '入库单明细编号',
							field : 'detailsId_',
							hidden:true
						}, {
							title : '车档案编号',
							field : 'carId',
							hidden:true
						},{
							title : '车辆编号',
							field : 'carCode',
							width : 130
						}, {
							title : 'VIN编号',
							field : 'carVinNumber',
							width : 130		
						}, {
							title : 'OCN码',
							field : 'carOcn',
							width : 100		
						},{
							title : '车辆品牌编号',
							field : 'carBrand',
							hidden:true
						},{
							title : '车辆品牌',
							field : 'carBrandName',
							hidden:true
						},{
							title : '车辆型号编号',
							field : 'carModelId',
							hidden:true	
						},{
							title : '车辆型号',
							field : 'carModelName',
							width : 100
						},{
							title : '车辆颜色编号',
							field : 'carColor',
							hidden:true	
						},{
							title : '车辆颜色',
							field : 'colorName',
							width : 100
						}, {
							title : '分销状态编号',
							field : 'carDistributState',
							hidden:true	
						}, {
							title : '分销状态',
							field : 'distributStateName',
							width : 100
						}, {
							title : '销售状态编号',
							field : 'carSellState',
							hidden:true	
						}, {
							title : '销售状态',
							field : 'sellStateName',
							width : 100
						}, {
							title : '库龄',
							field : 'carInstorageAge',
							width : 100
						}, {
							title : '厂牌名称',
							field : 'carLicenseName',
							width : 100
						}, {
							title : '发动机',
							field : 'carMotorNumber',
							width : 100
						}
				        ]],
				   onClickRow : function (rowIndex, rowData){
				   $(this).datagrid('unselectRow', rowIndex);
				},
				onDblClickRow : function(rowIndex, rowData){
					var rows = $("#instoreMoveDel").datagrid('getRows');
					if(rows.length){
						for(var i = 0;  i < rows.length; i++){
							if(rows[i].carId == rowData.carId){  
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
					$("#instoreMoveDel").datagrid('appendRow', rowData);
					var index = $("#instoreMoveDel").datagrid('getRowIndex', rowData);
					var records=$("#instoreMoveDel").datagrid("getRows");
					$("#number").val(records.length);
					validateDetail();
				}
		});	
	});
var queryCarInfo = function() {
	$('#selectionCar').datagrid('unselectAll');
	$('#selectionCar').datagrid('load', serializeObject($('#carInfoQueryForm')));
}
function clearSearch() {
	$('#carInfoQueryForm').form('clear');
	$('#selectionCar').datagrid('load', serializeObject($('#carInfoQueryForm')));

}
	</script>
		<div id="cc" class="easyui-layout" fit="true" border="false"style="">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 90px;" border="false">
				<form id="carInfoQueryForm" name="carInfoQueryForm" method="post"  fit="true" >
							<table>
								 <tr>
									<td>VIN号码:</td>
									<td><input type="text" name="carVinNumber" ></td>
									<td>车辆品牌：</td>
									<td><input type="text" name="carBrand"  id="car_brandId"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',   									
										editable:false,
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#car_brandId\']',
										invalidMessage : '请从下拉框中选择车辆品牌' " /></td>
									<td>车辆型号：</td>
									<td><input type="text" name="carModelId"  id="car_ModelId"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',   										
										editable:false,
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#car_ModelId\']',
										invalidMessage : '请从下拉框中选择车辆型号' " /></td>
									<td>外观颜色:</td>
									<td><input type="text" name="carColor"  id="car_colorId"   class="easyui-combobox" 
									data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR%>',   						
										editable:false,
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#car_colorId\']',
										invalidMessage : '请从下拉框中选择外观颜色' " />
										<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryCarInfo();">查询</a>
										<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearch();">清空</a>
										</td>
										
								</tr>
							</table>
				</form>
			</div>
			<div region="center" style="background: #eee;" border="false">
					<table id="selectionCar"></table>
			</div>
	</div>
	</body>
</html>