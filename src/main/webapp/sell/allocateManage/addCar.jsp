<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆档案选择</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body class="easyui-layout" >
	<script type="text/javascript">
	
	$selectionCar = $('#selectionCar');
		$selectionCar.datagrid({
			url :'carBarnInfoAction!getCar.action',
			pagination : true,
		    fit : true, 
			fitColumns : false,
			idField : 'carId',
			singleSelect : true,
			rownumbers : true,
				columns : [ [ {
							title : '编号',
							field : 'carId',
							width : 70,
							hidden:true
					    }, {
							title : '车辆编号',
							field : 'carCode',
							width : 140
						}, {
							title : 'VIN编号',
							field : 'carVinNumber',
							width : 130
						}, {
							title : 'OCN码',
							field : 'carOcn',
							width : 100
						},{
							title : '品牌',
							field : 'carBrand',
							width : 70,
							formatter : function (value,row,index){
							return row.carBrandName;
					    	 }
						}, {
							title : '型号',
							field : 'carModel',
							width : 120,
							formatter : function (value,row,index){
							return row.carModelName;
					    	 }
						}, {
							title : '颜色',
							field : 'carColor',
							width : 70,
							formatter : function (value,row,index){
							return row.carColorName;
					    	 }
						},
						 {
							title : '销售状态',
							field : 'carSellState',
							width : 100,
							formatter : function (value,row,index){
							return row.carSellStateName;
					    	 }
						},
						{
							title : '库龄',
							field : 'carInstorageAge',
							width : 70
						},
						{
							title : '库位',
							field : 'house',
							width : 70
						}, {
							title : '厂牌名称',
							field : 'carLicenseName',
							width : 110
						}, {
							title : '发动机号',
							field : 'carMotorNumber',
							width : 120
						},
						 {
							field : 'modelCostPrice',
							title : '成本价',
							width : 100,
							sortable : true
						},
						{
							title : '入库明细编号',
							field : 'detailsId',
							width : 70,
							hidden:true
					    }
				        ]],
				   onClickRow : function (rowIndex, rowData){
				   $(this).datagrid('unselectRow', rowIndex);
				},
				onDblClickRow : function(rowIndex, rowData){
					var rows = $("#mingxiList").datagrid('getRows');
					if(rows.length){
						for(var i = 0;  i < rows.length; i++){
							if(rows[i].carId == rowData.carId){  
							       $.messager.show({
										title : '提示',
										msg : '该车辆信息已经被选取!',
										showType : 'slide'
									}); 
								return;
							}
						}
					}
					$(this).datagrid('deleteRow', rowIndex);
					$("#mingxiList").datagrid('appendRow', rowData);
					var index = $("#mingxiList").datagrid('getRowIndex', rowData);
					copyPartsDateAndBindEvent($("#mingxiList"), index, rowData);
				}
		});	
		  function copyPartsDateAndBindEvent(id, rowIndex, rowData)
       {
    			id.datagrid('beginEdit', rowIndex);
    			var ed = id.datagrid('getEditors', rowIndex);
    			
	    }
	    
	    
	    
	   function query() {

	$('#selectionCar').datagrid('unselectAll');
	$('#selectionCar').datagrid('load',serializeObject($('#carInfoQueryForm')));
}  
	   function clearSearch(){
	   	$('#carInfoQueryForm').find('input').val('');
		$('#carInfoQueryForm').find('select').val('');
		$('#selectionCar').datagrid('unselectAll');
		$('#selectionCar').datagrid('load', serializeObject($('#carInfoQueryForm')));
	   }
	</script>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 60px;" border="false">
				<form id="carInfoQueryForm" name="carInfoQueryForm" method="post"  fit="true" >
				<fieldset>
						<table>
							<tr>
								<td>
									品牌：
								</td>
								<td>
									<input type="text" id="queryBrand" name="carBrand" style="width: 95px;"
										class="easyui-combobox"
										data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
											editable:false,
											valueField:'id',   
											textField:'text',
											mode : 'remote',
											validType:'isSelected[\'#queryBrand\']',
											invalidMessage : '请从下拉框中选择车辆品牌'" />
								</td>
								

								<td>
									型号：
								</td>
								<td>
									<input type="text" id="queryModel" name="carModel" style="width: 95px;"
										class="easyui-combobox"
										data-options="url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
											editable:false,
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#queryModel\']',
											invalidMessage : '请从下拉框中选择车辆型号'" />
								</td>
								<!--<td>
									颜色：
									</td>
									<td>
										<input style="width: 80px" name="carColor" id="carColor"
											class="easyui-combobox"
											data-options="
												url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR %>',
												multiple:false,
												valueField:'id',  
												textField:'text'
												" />
									</td>
									--><td>VIN码：</td>
									<td><input name="carVinNumber" style="width: 125px" /></td>

								<td colspan="2">
									
										<a id='_search' href="javascript:void(0);" class="easyui-linkbutton"
										iconCls="icon-search" plain="true"  onclick="query();">查询</a>
										<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton"
										iconCls="icon-cancel" plain="true"  onclick="clearSearch();">清空</a>
								</td>
							</tr>
						
						</table>
						</fieldset>
				</form>
			</div>
			<div region="center" style="background: #eee;" border="false" >
					<table id="selectionCar"></table>
			</div>
	</div>
	</body>
</html>