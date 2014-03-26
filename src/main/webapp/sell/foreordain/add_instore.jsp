<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body  >
	<script type="text/javascript">
	$(function(){
		$impotrCar = $('#impotrCar');
		$impotrCar.datagrid({
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			fitColumns : true,
			sortOrder : 'asc',
			singleSelect : true,
			rownumbers : true,
			fit:true,
			loadMsg : "数据加载中，请稍后……",
				columns : [ [ {
							title : '入库日期',
							field : 'instorageDate',	
							width : 100,			
							sortable : true
					    }, {
							title : '厂牌名称',
							field : 'carLicenseName',
							width : 150,
							sortable : true
						}, {
							title : 'VIN号 ',
							field : 'carVinNumber',
							width : 100,
							sortable : true
						}, {
							title : '发动机号',
							field : 'carMotorNumber',
							width : 100,
							sortable : true
						},{
							title : '车型号',
							field : 'carModelName',
							width : 100,
					    	 sortable : true
						}, {
							title : '品牌',
							field : 'carBrandName',
							width : 100,							
					    	 sortable : true
						}, {
							title : '外观色',
							field : 'colorName',
					    	 sortable : true
						},{
							title : '内饰色',
							field : 'interiorName',
							width : 100,
					    	 sortable : true
						},{
							title : '产地',
							field : 'carProductionAddress',
							width : 100,
					    	sortable : true
						}, {
							title : '生产日期',
							field : 'carMakeData',
							width : 100,
							sortable : true
						}, {
							title : '订货单号',
							field : 'orderNumber',
							width : 100,
							sortable : true
						}, {
							title : 'OCN码',
							field : 'carOcn',
							width : 100,
							sortable : true
						}
				        ]]
		});	
	});

	</script>
	 <div id="cc" class="easyui-layout" style="width:600px;height:300px;"  fit="true"  >
		    <div region="north"  split="false" style="background:#eee;height:50px;"  border="false"  >
		       <div id="dd" class="easyui-layout" style="width:600px;height: 45px;"   fit="true">
		             <div   region="north" style="overflow: hidden;padding:3px; background:#eee; height:30px;"  border="false" >
		                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" id="_import" plain="true"  onclick="importCar();" >导入</a><!--
						<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" id="_print" plain="true">打印</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" id="_export" plain="true">导出</a>
		            --></div> 
	         </div>
 			</div>  
     
		    <div region="center" id="carSel" style="background:#eee;" border="false">
		           <table id="impotrCar"></table>
		    </div>  
   </div>

	</body>
</html>