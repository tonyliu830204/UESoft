<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>承兑汇票选择</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body class="easyui-layout" >
	<script type="text/javascript">
 //判断页面初始化加载是否完成
    function   LoadOk(){
    	if(document.readyState   =="complete") {
  			 initFrame();
 		}else{
   		setTimeout("LoadOk()",200);
 		}
	}
   setTimeout("LoadOk()",200);
   
   //判断页面初始化加载完成    执行
function   initFrame(){
    var carVin=$('#carVinId').val();
          if(carVin!='' && carVin!=null){ 
          	 $("#aa").val(carVin);
	           $.ajax({
					type : 'post',
					url : 'carInfoAction!getCar.action',
					data : "carVinNumber="+$('#aa').val(),
					dataType : 'json',
					success : function(r){
				               query();
				 	               }
				        });
				       }
				      $('#carVinId').val('');
}
	var $carData;
	$(function() {
		$('#carData').datagrid({
					url:'${pageContext.request.contextPath}/carInfoAction!getCar.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'carId',
					singleSelect : true,
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编号',
							field : 'carId',
							width : 50,
							hidden:true
						},{
							title : '车辆品牌编号',
							field : 'carBrand',
							hidden:true
					    }, {
							title : '车辆品牌',
							field : 'carBrandName',
							width : 150
						}, {
							title : '车辆型号编号',
							field : 'carModelId',
							hidden:true
						}, {
							title : '车辆型号',
							field : 'carModelName',
							width : 100
						}, {
							title : '颜色编号',
							field : 'carColor',
							hidden:true
						}, {
							title : '颜色',
							field : 'colorName',
							width : 100
						}, {
							title : 'VIN号',
							field : 'carVinNumber',
							width : 100
						}
				        ]],
				        	onDblClickRow : function(rowIndex, rowData) {
							$('#xsCarId').val(rowData.carId);
							$('#carBrandId').val(rowData.carBrand);
							$('#carBrandNameId').val(rowData.carBrandName);
							$('#carModelId').val(rowData.carModelId);
							$('#carModelNameId').val(rowData.carModelName);
							$('#carColorId').val(rowData.carColor);
							$('#colorNameId').val(rowData.colorName);
							$('#carVinId').val(rowData.carVinNumber);
							sgsm_d1.dialog('close');
						}
				});
	});
	var query = function() {
		$('#carData').datagrid('unselectAll');
		$('#carData')
				.datagrid('load', serializeObject($('#carQuery')));
	}
	function clearSearchCondition() {
		$('#carQuery').form('clear');
		$('#carData').datagrid('load', serializeObject($('#carQuery')));

	}
</script>

		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 63px;" border="false">
				<form id="carQuery">
					VIN号:<input type="text" id="aa" name="carVinNumber" style="background-color: #c0d8d8;" onkeyup="query();" />
					&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSearchCondition();" plain="true">清空</a>
				</form>
			</div>
			<div region="center" style="background: #eee;" border="false">
				<table id="carData"></table>
			</div>
		</div>
	</body>
</html>