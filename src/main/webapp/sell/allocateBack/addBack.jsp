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
			url:'${pageContext.request.contextPath}/sellBackAction!getSellBackByDis.action?xsDistributorId='+<%=request.getParameter("id")%>,
			pagination : true,
		    fit : true, 
			fitColumns : false,
			idField : 'carId',
			singleSelect : true,
			rownumbers : true,
				columns : [ [ {
							field : 'carId',
							title : '编号',
							width : 60,
							sortable : true,
						}, {
							field : 'carCode',
							title : '车辆编号',
							width : 120,
							sortable : true
						}, {
							field : 'carVinNumber',
							title : 'VIN号',
							width : 130,
							sortable : true
						},
						{
							field : 'carBrandName',
							title : '车辆品牌',
							width : 70,
							sortable : true
						},
						{
							field : 'carBrand',
							title : '车辆品牌id',
							hidden:true,
							width : 70,
							sortable : true
						},
						{
							field : 'carModelName',
							title : '车辆类型',
							width : 120,
							sortable : true
						},{
							field : 'carModel',
							title : '车辆类型id',
							hidden:true,
							width : 70,
							sortable : true
						},{
							field : 'carColorName',
							title : '颜色',
							width : 70,
							sortable : true
						},{
							field : 'carColor',
							title : '颜色id',
							hidden:true,
							width : 70,
							sortable : true
						},{
							field : 'allAmount',
							title : '调退金额',
							width : 70,
							sortable : true
						},{
							field : 'carOcn',
							title : 'OCN码',
							width : 70,
							sortable : true
						
					    },{
							field : 'detailsId',
							title : '入库明细编号',
							width : 70,
							sortable : true,
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
				}
		});	
	 function query() {
		$('#selectionCar').datagrid('unselectAll');
		$('#selectionCar').datagrid('load',serializeObject($('#carInfoForm')));
	}  
	function clearSearch(){
	   	$('#carInfoForm').find('input').val('');
		$('#carInfoForm').find('select').val('');
		$('#selectionCar').datagrid('unselectAll');
		$('#selectionCar').datagrid('load', serializeObject($('#carInfoForm')));
	   }
	  
	</script>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 90px;" border="false">
				<form id="carInfoForm"  >
		<fieldset>
						<table>
							<tr>
								<td>
									VIN号：
								</td>
								<td><input id="dNameww" name="carVinNumber" style="width: 130px" /></td> 
								<td>
									车辆编号：
								</td>
								<td><input id="dNa" name="carCode" style="width: 130px" />	</td>
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