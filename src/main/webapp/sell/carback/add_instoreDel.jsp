<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants" %>
<%
	String supperId=request.getParameter("supplerid");
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
	<body  >
	<script type="text/javascript">
	$(function(){
		$selectionCar = $('#selectionCar');
		$selectionCar.datagrid({
			url :'instorehouseAction!getPagerDel.action?key=carBack&supplierId='+<%=supperId%>,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		   	fit:true,
			fitColumns : false,
			idField : 'detailsId',
			singleSelect : true,
			rownumbers : true,
			sortName : 'carId',
			sortOrder : 'asc',
				columns : [ [ {
							title : '编号',
							field : 'detailsId',				
							hidden:true,
					    },{
							title : '入库单明细id',
							field : 'instorehouse_',
							hidden:true,
					    }, {
							title : '车辆编号',
							field : 'carCode',
							width : 150,
							sortable : true
						}, {
							title : 'VIN编号',
							field : 'carVinNumber',
							width : 100,
							sortable : true
						}, {
							title : 'OCN码',
							field : 'carOcn',
							width : 100,
							sortable : true
						},{
							title : '品牌',
							field : 'carBrand',
							width : 100,
							formatter : function (value,row,index){
							return row.carBrandName;
					    	 },
					    	 sortable : true
						}, {
							title : '型号',
							field : 'carModelId',
							width : 100,
							formatter : function (value,row,index){
							return row.carModelName;
					    	 },
					    	 sortable : true
						}, {
							title : '颜色',
							field : 'carColor',
							width : 100,
							formatter : function (value,row,index){
							return row.colorName;
					    	 },
					    	 sortable : true
						},{
							title : '销售状态',
							field : 'sellStateName',
							width : 100,
							
					    	 sortable : true
						}, {
							title : '发动机号',
							field : 'carMotorNumber',
							width : 100,
							sortable : true
						}, {
							title : '税额',
							field : 'tax',
							width : 100,
							sortable : true
						}, {
							title : '未税额',
							field : 'notax',
							width : 100,
							sortable : true
						}
				        ]],
				   onClickRow : function (rowIndex, rowData){
				   $(this).datagrid('unselectRow', rowIndex);
				},
				onDblClickRow : function(rowIndex, rowData){
					var rows = $("#instoreDel").datagrid('getRows');
					if(rows.length){
						for(var i = 0;  i < rows.length; i++){
							if(rows[i].detailsId == rowData.detailsId){  			
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
					$("#instoreDel").datagrid('appendRow', rowData);
					var index = $("#instoreDel").datagrid('getRowIndex', rowData);
					copyPartsDateAndBindEvent($("#instoreDel"), index, rowData);	
					var records=$("#instoreDel").datagrid("getRows");
					$("#number").val(records.length);
					 validateDetail();
				}
		});	
	});
		  function copyPartsDateAndBindEvent(id, rowIndex, rowData)
	{
		id.datagrid('beginEdit', rowIndex);
		var ed = id.datagrid('getEditors', rowIndex);			
	}
		var queryCarInfo = function() {
	$('#selectionCar').datagrid('unselectAll');
	$('#selectionCar').datagrid('load', serializeObject($('#carInfoQueryForm')));
}
function clearSearch() {
	$('#carInfoQueryForm').form('clear');
	$.ajax( {
		type : 'POST',
		url :'instorehouseAction!getPagerDel.action',
		data : $('#carInfoQueryForm').serialize(),
		dataType : 'json',
		success : function(r) {
			$('#selectionCar').datagrid('load', r);
		}
	});

}
	</script>
	<div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 100px;" border="false">
				<form id="carInfoQueryForm" name="carInfoQueryForm" method="post"  >
							<table align="center" style="margin-top: 10px">
								 <tr>
									<td>VIN号码:</td>
									<td><input type="text" name="queryVinNumber" ></td>
									<td>车辆品牌：</td>
									<td><input type="text" name="queryBrand"  id="car_brandId"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',   									
										editable:false,
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#car_brandId\']',
										invalidMessage : '请从下拉框中选择车辆品牌' " /></td>
									<td>车辆型号：</td>
									<td><input type="text" name="queryCarModel"  id="car_ModelId"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',   										
										editable:false,
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#car_ModelId\']',
										invalidMessage : '请从下拉框中选择车辆型号' " /></td>
									<td>外观颜色:</td>
									<td><input type="text" name="queryColor"  id="car_colorId"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR%>',   						
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
			<div region="center" style="background: #eee" border="false" >
					<table id="selectionCar"></table>
			</div>
	</div>
	</body>
</html>