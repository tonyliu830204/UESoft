<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>采购计划管理</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		   

	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellPurchase/sellPurchase.js"></script>
  </head> 
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
		<privilege:enable code="PURCHASE__ADD">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="addSellPurchase();">新增</a>
		</privilege:enable>
		<privilege:enable code="PURCHASE_REMOVE">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeSellPurchase()">删除</a>
		</privilege:enable>
		<privilege:enable code="PURCHASE_EDIT">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editSellPurchase();">修改</a>
		</privilege:enable>
		<privilege:enable code="PURCHASE_SEARCH">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="querySellPurchase();">查询</a>
		</privilege:enable>
		<privilege:enable code="PURCHASE_CLEAR">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
		</privilege:enable>
		<privilege:enable code="PURCHASE_PRINT">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="_config();">打印</a>
		</privilege:enable>
		<privilege:enable code="PURCHASE_EXPORT">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="_except();">导出</a>
		</privilege:enable><!--
		<privilege:enable code="PURCHASE_IMPORT">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
    	</privilege:enable>
    --></div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:58px;">
		    	<form id="sellQueryForm" name="sellQueryForm" method="post"  fit="true" >
					<table>
						 <tr>
							<td>计划月份:</td>
							<td colspan="2"><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'queryPlanDate2\',{d:-1})}'})" name="queryPlanDate" id="queryPlanDate" style="width: 110px;"/> 至 
									<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'queryPlanDate\',{d:1})}'})" name="queryPlanDate2" id="queryPlanDate2" style="width: 110px;"/></td>
						
						<td>
							品牌:
						</td>
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
						<td>车型:</td>
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
							
							<td>颜色:</td>
							<td><input type="text" id="queryColor" name="queryColor"  class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR%>',
								editable:false,
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#queryColor\']',
								invalidMessage : '请从下拉框中选择颜色'" /></td>
						</tr>
					</table>
			</form>
		    </div>
		    <div id="sellPurchase_div" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="sellPurchase"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
