<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>调退单查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"src="${pageContext.request.contextPath}/sell/allocateBackQuery/queryAllocateBack.js"></script>
		 <script type="text/javascript">
		    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		    </script>
		<script type="text/javascript">
		var sgsm_d1;
	function adddis2()
	{
	 sgsm_d1 = $('<div/>');
	 sgsm_d1.dialog({
		title: '请选择',   
	    width: 650,   
	    height: 400,
	    cache: false,   
	    href: '${pageContext.request.contextPath}/sell/allocateManage/addDistributor2.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	      }
	   });
	}
</script>
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',border:false"
			style="padding: 1px; height: 27px; overflow: hidden; background: #eee;">
			<privilege:enable code="ALLBACKQ_SEARCH">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="queryCarReserve();">查询</a>
			</privilege:enable>
			<privilege:enable code="ALLBACKQ_CLEAR">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
			</privilege:enable>
			<privilege:enable code="ALLBACKQ_PRINT">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-print" plain="true" onclick="_config();">打印</a>
			</privilege:enable>
			<privilege:enable code="ALLBACKQ_EXPORT">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-export" plain="true" onclick="_except();">导出</a>
			</privilege:enable>
			
		</div>
		<div data-options="region:'center',border:false"
			style="background: #eee;">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',title:'查询条件',border:false"
					style="overflow: hidden; background: #eee; height: 100px;">
					<form id="carQueryForm" name="carModelQueryForm" method="post"
						fit="true">
						 <fieldset>
						<table>
							<tr>
								<td width="70px">
									调退日期:
								</td>
								<td style="text-align: right;" colspan="3">
									<input type="text" id="instoreStart" name="backDate"
										class="Wdate" style="width: 115px"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'instoreEnd\',{d:-1})}'});" />
									&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
									<input type="text" id="instoreEnd" name="backDate2"
										class="Wdate" style="width: 115px"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'instoreStart\',{d:1})}'});" />
								</td>
								<td width="60px">
									分销商:
								</td>
								<td colspan="3">
									<input id="dName2" name="xsDistributorName" 
										onkeypress=" if(event.keyCode==13) { adddis2(); return false;}"
										style="background-color: #c0d8d8;width: 335px" />
									<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png"
										onclick="adddis2();" />
									<input name="xsDistributorId" id="did2" type="hidden" />
								</td>
								<td width="60px">
									车类型:
								</td>
								<td>
								<input type="text" id="car_Model_id" name="carModel" style="width:155px;"  class="easyui-combobox" 
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
								
								
							</tr>
							<tr>
								<td width="70px">
									调退单号:
								</td>
								<td>
									<input type="text" name="backCode" style="width: 116px" />
								</td>
								<td >
									调退分类:
								</td>
								<td>
									<input type="text"  id="backType"
										name="backType" class="easyui-combobox" style="width: 95px"
										data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.ALLOCATETYPE.ALLOCATETYPE%>',
										
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#backType\']',
										invalidMessage : '请从下拉框中选择调拨类型'">
															
								</td>
							

								<td width="60px">
									VIN号码:
								</td>
								<td>
									<input type="text" name="carVinNumber"	 style="width: 130px"/>
								</td>

								<td width="60px">
									车辆编号:
								</td>
								<td>
									<input type="text" name="carCode" style="width: 150px">
								</td>
							

								
								
							</tr>
						</table>
						</fieldset>
						
					</form>
				</div>
				<div id="backTree" data-options="region:'center',border:false"
					style="background: #eee;">
					  <table id="Tree" name="Tree"></table>
				</div>
			</div>
		</div>
	</body>
</html>
