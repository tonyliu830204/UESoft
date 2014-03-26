<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆型号资料</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/carModel/carModel.js"></script>
  <script type="text/javascript">
    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
 
  </script>
  
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
	<privilege:enable code="CARMODEl_ADD">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="addCarModel();">新增</a>
	</privilege:enable>
	<privilege:enable code="CARMODEl_REMOVE">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeCarModel()">删除</a>
	</privilege:enable>
	<privilege:enable code="CARMODEl_EDIT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCarModel();">修改</a>
	</privilege:enable>
	<privilege:enable code="CARMODEl_SEARCH">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryCarModel();">查询</a>
	</privilege:enable>
	<privilege:enable code="CARMODEl_CLEAR">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
	</privilege:enable>
	<privilege:enable code="CARMODEl_PRINT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true"  onclick="_config();">打印</a>
	</privilege:enable>
	<privilege:enable code="CARMODEl_EXPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="_except();">导出</a>
	</privilege:enable>
	<!--<privilege:enable code="CARMODEl_IMPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
    </privilege:enable>
    --></div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:58px;">
		    	<form id="carModelQueryForm" name="carModelQueryForm" method="post"  fit="true" >
							<table>
								 <tr>
									<td>车型编码:</td>
									<td><input type="text" id="carMId" name="modelCode"  size="10" style="background-color: #c0d8d8;"/></td>
									
								   <td>
														品牌:
													</td>
														<td><input type="text" id="car_Brand_id" name="carMBrand" style="width:140px;" class="easyui-combobox" data-options="
														url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
														valueField:'id',   
											    		textField:'text',
											    		mode:'remote',			    		
											    		validType:'isSelected[\'#car_Brand_id\']',
											    		invalidMessage : '请从下拉框中选择车辆品牌',
											    		onSelect: function(rec){  
											    			$(this).combobox('textbox').bind('keyup', function (){
											    				if($('#car_Brand_id').combobox('getValue') == '' || $('#car_Brand_id').combobox('getValue') != $('#car_Brand_id').combobox('getText')){
											    					$('#car_Brand_id').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
											    				}
											    			});
												            $('#car_Model_id').combobox('clear');
												            $('#car_Model_id').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
												        } "
												        />
												       
												        </td>
													<td>车型:</td>
														<td><input type="text" id="car_Model_id" name="carMId" style="width:150px;"  class="easyui-combobox" 
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
											    					$('car_Model_id').combobox('reload', 'carModelAction!findAllCarModel.action');
											    				}
											    			});  
															 
												        } "/>
												        
												        
														</td>
								</tr>
							</table>
			 </form>
		    </div>
		    <div id="carModel_div" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="carModel"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
