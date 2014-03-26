<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants" %>
<%
	String t=request.getParameter("type");
	String addTypeTag=request.getParameter("addTypeTag"); 
	
%>
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
	<body >
	<font face="Agency FB"><script type="text/javascript">
		var addTypeTag=<%=addTypeTag%>;
		function copyPartsDateAndBindEvent(id, rowIndex, rowData)
	{
		id.datagrid('beginEdit', rowIndex);
		var ed = id.datagrid('getEditors', rowIndex);
		if(ed[0]){
			
			if(addTypeTag==1){
				ed[0].target.numberbox('setValue', rowData.vehicleCost);
			    ed[1].target.numberbox('setValue', rowData.vehicleCost/1.17);
				ed[2].target.numberbox('setValue', rowData.vehicleCost-rowData.vehicleCost/1.17);
				ed[0].target.select();
				ed[0].target.click(function (){
					ed[0].target.select();
				});
				ed[0].target.keydown(function (e){
					if(e.keyCode == '13'){
						ed[2].target.select();
					}
				});
				ed[0].target.bind('keyup', function() {
					var vehicleCost = ed[0].target.val();
					ed[1].target.numberbox('setValue', vehicleCost/1.17);
					ed[2].target.numberbox('setValue', vehicleCost-vehicleCost/1.17);
				});
			}else if(addTypeTag==2){
				ed[0].target.numberbox('setValue', rowData.vehicleCost);
			    ed[1].target.numberbox('setValue', rowData.vehicleCost);
				ed[2].target.numberbox('setValue', 0);
				ed[0].target.select();
				ed[0].target.click(function (){
					ed[0].target.select();
				});
				ed[0].target.keydown(function (e){
					if(e.keyCode == '13'){
						ed[2].target.select();
					}
				});
				ed[0].target.bind('keyup', function() {
					var vehicleCost = ed[0].target.val();
					ed[1].target.numberbox('setValue', vehicleCost);
					ed[2].target.numberbox('setValue', 0);
				});
			}else{
				alert("请重新操作!");
			}
		}				
	}
		var runs=function(){
		$selectionCar = $('#selectionCar');
		$selectionCar.datagrid({
			url :'carInfoAction!getSellState.action',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			fitColumns : false,
			idField : 'carId',
			sortOrder : 'asc',
			sortName : 'carId',
			singleSelect : true,
			rownumbers : true,
			fit:true,
			loadMsg : "数据加载中，请稍后……",
				columns : [ [ {
							title : '编号',
							field : 'carId',				
							hidden:true,
							sortable : true
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
							field : 'carSellState',
							width : 100,
							formatter : function (value,row,index){
							return row.sellStateName;
					    	 },
					    	 sortable : true
						},{
							title : '分销状态',
							field : 'carDistributState',
							width : 100,
							formatter : function (value,row,index){
							return row.distributStateName;
					    	 },
					    	 sortable : true
						}, {
							title : '厂牌名称',
							field : 'carLicenseName',
							width : 100,
							sortable : true
						}, {
							title : '发动机号',
							field : 'carMotorNumber',
							width : 100,
							sortable : true
						}, {
						title : '成本',
						field : 'vehicleCost',
						width : 100
					}
				        ]],
				   onClickRow : function (rowIndex, rowData){
				   $(this).datagrid('unselectRow', rowIndex);
				},
				onDblClickRow : function(rowIndex, rowData){
					var rows = $("#ForeordainDetilTable").datagrid('getRows');
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
					$("#ForeordainDetilTable").datagrid('appendRow', rowData);
					var index = $("#ForeordainDetilTable").datagrid('getRowIndex', rowData);
					copyPartsDateAndBindEvent($("#ForeordainDetilTable"), index, rowData);	
					var records=$("#ForeordainDetilTable").datagrid("getRows");
					$("#number").val(records.length);
					
				}
		});	
	}
	$(function(){
		if(addTypeTag==0){
			$.ajax({
				type : 'POST',
				url :'instorehouseAction!isfpType.action',
				data :{invoiceType:<%=t%>},
				dataType : 'json',
				success : function(r) {
					if(r.success){
						if(r.obj==true){
							addTypeTag=1;
						}else{
							addTypeTag=2;
						}
						runs();
					}
				}
			});
		}else{
			runs();
		}
	});
	

var queryCarInfo = function() {
	$('#selectionCar').datagrid('unselectAll');
	$('#selectionCar').datagrid('load', serializeObject($('#carInfoQueryForm')));
}
function clearSearch() {
	$('#carInfoQueryForm').form('clear');
	$('#car_Brand_id').combobox('reload');
	$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
	$.ajax( {
		type : 'POST',
		url :'carInfoAction!getSellState.action',
		data : $('#carInfoQueryForm').serialize(),
		dataType : 'json',
		success : function(r) {
			$('#selectionCar').datagrid('load', r);
		}
	});
}
	</script></font>
		<div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 100px;" border="false">
				<form id="carInfoQueryForm" name="carInfoQueryForm" method="post"  >
							<table align="center" style="margin-top: 10px">
								 <tr>
									<td><font face="Aharoni">VIN号码:</font></td>
									<td><font face="Aharoni"><input type="text" name="vinNumber"></font></td>
										<td><font face="Aharoni">
									品牌:
								</font></td>
									<td><input type="text" id="car_Brand_id" name="queryBrand" style="width:140px;" class="easyui-combobox" data-options="
									url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
									valueField:'id',   
						    		textField:'text',
						    		mode:'remote',			    		
						    		validType:'isSelected[\'#car_Brand_id\']',
						    		invalidMessage : '请从下拉框中选择车辆品牌',
						    		onSelect: function(rec){  
						    			$(this).combobox('textbox').bind('keyup', function (){
						    				if($('#car_Brand_id').combobox('getValue') == '' || $('#car_Brand_id').combobox('getValue') != $('#car_Brand_id').combobox('getText')){
						    					$('#car_Model_id').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
						    				}
						    			});
							            $('#car_Model_id').combobox('clear');
							            $('#car_Model_id').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
							        } "
							        />
							       
							        </td>
								<td><font face="Aharoni">车型:</font></td>
									<td><input type="text" id="car_Model_id" name="queryModel" style="width:150px;"  class="easyui-combobox" 
									data-options="
									
									url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
									valueField:'id',   
						    		textField:'text',
						    		mode:'remote',
						    		validType:'isSelected[\'#car_Model_id\']',
						    		invalidMessage : '请从下拉框中选择车辆型号',
						    		onSelect:function(rec){
						    			$(this).combobox('textbox').bind('keyup', function (){
						    				if($('#car_Model_id').combobox('getValue') == '' || $('#car_Model_id').combobox('getValue') != $('#car_Model_id').combobox('getText')){
						    					$('#carArchives_add_carCstlId').combobox('reload', 'carModelAction!findAllCarModel.action');
						    				}
						    			});  
							         
							        } "/>
							        
							        
									</td>
									
									<td><font face="Aharoni">外观颜色:</font></td>
									<td><font face="Aharoni"><input type="text" name="queryColor" id="car_colorId" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR%>',   						
										editable:false,
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#car_colorId\']',
										invalidMessage : '请从下拉框中选择外观颜色' "><a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="queryCarInfo();">查询</a> 
										<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="clearSearch();">清空</a></font>
										
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