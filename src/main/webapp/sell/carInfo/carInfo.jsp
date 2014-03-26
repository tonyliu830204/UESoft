<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants" %>
<%@ taglib uri="/priveliege" prefix="privilege"%>

<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆档案</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<script type="text/javascript">
	var pkey="<%=Contstants.SELLSTATE.BASE_SELLSTATE %>";
	var ckey="<%=Contstants.SELLSTATE.FORSALE %>";
	</script>

	<body  >
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/carInfo/carInfo.js"></script>
	<%
	String  carId=request.getParameter("carId");
	if(carId!=null){
	%>
<script type="text/javascript" >
$(function() {
	
	$('#carInfo').datagrid( {
		url : 'carInfoAction!getPage.action?times=no',
		queryParams:{id:<%=carId%>},
		fit : true,
		
		pagination : true,
		rownumbers : true,
		sortOrder : 'asc',
		sortName : 'carId',
		singleSelect : true,
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
						title : '车辆编号',
						field : 'carId',
						sortable : true,
						hidden : true
					}, {
						title : '车辆编号',
						field : 'carCode',
						width : 150,
						sortable : true
					}, {
						title : '车辆品牌',
						field : 'carBrand',
						width : 100,
						formatter : function(value, row, index) {
							return row.carBrandName;
						},
						sortable : true
					}, {
						title : '内饰色',
						field : 'carInteriorColor',
						width : 100,
						formatter : function(value, row, index) {
							return row.interiorColorName;
						},
						sortable : true
					}, {
						title : '车辆型号',
						field : 'carModelId',
						width : 100,
						formatter : function(value, row, index) {
							return row.carModelName;
						},
						sortable : true
					}, {
						title : '选装件',
						field : 'carOptional',
						width : 100,
						sortable : true
					}, {
						title : '车牌照',
						field : 'carLicensePlate',
						width : 100,
						sortable : true
			
					}, {
						title : '厂牌名称',
						field : 'carLicenseName',
						width : 100,
						sortable : true
					}, {
						title : 'VIN编号',
						field : 'carVinNumber',
						width : 100,
						sortable : true
					}, {
						title : '发动机号',
						field : 'carMotorNumber',
						width : 100,
						sortable : true
					}, {
						title : 'OCN码',
						field : 'carOcn',
						width : 100,
						sortable : true
					},{
						title : '销售状态',
						field : 'carSellState',
						width : 100,
						formatter : function(value, row, index) {
							return row.sellStateName;
						},
						sortable : true
					},  {
						title : '生产日期',
						field : 'carMakeData',
						width : 100,
						sortable : true
					}, {
						title : '钥匙号',
						field : 'carUnlockingKeyNumber',
						width : 100,
						sortable : true
					}, {
						title : '商检单',
						field : 'carTradeCheckBill',
						width : 100,
						sortable : true
					}, {
						title : '产地',
						field : 'carProductionAddress',
						width : 100,
						sortable : true
					}, {
						title : '代交寄存车地址',
						field : 'carAddress',
						width : 100,
						sortable : true
					}, {
						title : '拷车日期',
						field : 'carCopyData',
						width : 100,
						sortable : true
					}, {
						title : '配车日期',
						field : 'carAssembleData',
						width : 100,
						sortable : true
					}, {
						title : '终检日期',
						field : 'carEndCheckData',
						width : 100,
						sortable : true
					}, {
						title : '首保日期',
						field : 'carFristInsuranceData',
						width : 100,
						sortable : true
					}, {
						title : '预计下线日期',
						field : 'carForecastData',
						width : 100,
						sortable : true
					}, {
						title : '车辆标准价格',
						field : 'carPrice',
						width : 100,
						sortable : true
					}, {
						title : '分销商',
						field : 'distributorId',
						width : 100,
						formatter : function(value, row, index) {
							return row.distributorName;
						},
						sortable : true
					}, {
						title : '规格',
						field : 'norms',
						width : 70,
						formatter : function(value, row, index) {
							return row.normsN;
						},
						sortable : true
					}, {
						title : '证明文件',
						field : 'proveFile',
						width : 100,
						sortable : true
					}, {
						title : '认证书',
						field : 'rzBook',
						width : 100,
						sortable : true
					}, {
						title : '工具包',
						field : 'toolCase',
						width : 70,
						formatter : function(value, row, index) {
							return row.toolCaseN;
						},
						sortable : true
					},{
						title : '脚垫',
						field : 'footd',
						width : 70,
						formatter : function(value, row, index) {
							return row.footdN;
						},
						sortable : true
						}
						,{
							title : '企业编号',
							field : 'enterpriseId',
							width : 100,
							hidden:true
					}] ],onDblClickRow : function(rowIndex, rowData) {
					var cId=rowData.carId;			
					loalDel(cId);
			}
		});
	});
</script>
<%
	}
 %>
	
	
		
		
		<div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
			
			<%
               if(carId==null||carId.length()==0){
     	  %>
			<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
				<privilege:enable code="CARARCHIVES_ADD">
				<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="addCarInfo();">新增</a>
				</privilege:enable>
				<privilege:enable code="CARARCHIVES_REMOVE">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeCarInfo()">删除</a>
				</privilege:enable>
				<privilege:enable code="CARARCHIVES_EDIT">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCarInfo();">修改</a>
				</privilege:enable>
				<privilege:enable code="CARARCHIVES_SEARCH">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryCarInfo();">查询</a>
				</privilege:enable>
				<privilege:enable code="CARARCHIVES_CLEAR">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
				</privilege:enable>
				<privilege:enable code="CARARCHIVES_PRINT">
				<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="dopoint('carInfo','carInfo_div_id');">打印</a>
				</privilege:enable>
				<privilege:enable code="CARARCHIVES_EXPORT">
				<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="doexcept('carInfo','carInfo_div_id');">导出</a>
				</privilege:enable>
				<!--<privilege:enable code="CARARCHIVES_IMPORT">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="doinport('carInfo',$'carInfo_div_id');">导入</a>
				</privilege:enable>
			  --></div>
			  <%
               }
        %>
		 <div data-options="region:'center',border:false" style="background:#eee;">
		 <div class="easyui-layout" data-options="fit:true,border:false"> 
			  <%
               if(carId==null||carId.length()==0){
               %> 
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 100px;" border="false">
				<form id="carInfoQueryForm" name="carInfoQueryForm" method="post">
									<table>
										 <tr>
										 <td style="display: none"><input name="times" value="no"/></td>
											<td>VIN号码:</td>
											<td><input type="text" name="vinNumber" maxlength="17" style="width:120px;"></td>
											<td>内饰色:</td>
											<td><input type="text" id="interiorColor" name="interiorColor"  style="width:120px;" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_ORNAMENTCOLOR%>',
											valueField:'id',   
										textField:'text',
										validType:'isSelected[\'#interiorColor\']',
										invalidMessage : '请从下拉框中选择内饰色',
										mode:'remote' "/></td>
										
										<td>拷车日期:</td>
											<td style="text-align: left;" ><input type="text" id="copyDataStart" name="copyDataStart" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'copyDataEnd\')}'});"/>
											&nbsp;至&nbsp;&nbsp;<input type="text" id="copyDataEnd" name="copyDataEnd" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'copyDataStart\')}'});"/></td>
										</tr>
										<tr>
										<td>OCN码:</td>
											<td><input type="text"  name="ocnNumber" style="width:120px;"></td>
													
											
										<td>销售状态:</td>
											<td><input type="text" name="sellState" id="sellState"  style="width:120px;" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.SELLSTATE.BASE_SELLSTATE%>',   
												editable:false,
												valueField:'id',   
												textField:'text',
												mode : 'remote',
												validType:'isSelected[\'#sellState\']',
												invalidMessage : '请从下拉框中选择销售状态' ,
												onLoadSuccess:function(){
												$.ajax({
												   type: 'post',
												   dataType: 'json',
												   url: 'baseTagAction!getDataByChildDataKey.action',
												   data: 'pdataKey=<%=Contstants.SELLSTATE.BASE_SELLSTATE %>&dataKey=<%=Contstants.SELLSTATE.FORSALE %>',
												   success: function(r){
												   		$('#sellState').combobox('select',r);
												   }
												});
											}" /></td>
												<td>分销商:</td>
											<td ><input type="text" name="distributor" id="distributor" style="width:250px;" class="easyui-combobox" data-options="
											url:'${pageContext.request.contextPath}/distributorAction!findAllInfo.action',
											valueField:'id',   
											textField:'text',
											validType:'isSelected[\'#distributor\']',
											invalidMessage : '请从下拉框中选择分销商',
											mode:'remote' "/></td>	
											</tr>
									</table>
					 </form>
			</div>
			 <%
              		 }
               	 %>
			<div id="carInfo_div_id" region="center" style="background: #eee" border="false" >
					<table id="carInfo"></table>
			</div>
		  </div>
		</div>
	</div>
	</body>
</html>