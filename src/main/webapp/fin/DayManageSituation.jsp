<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>日营业情况查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/DayManageSituation.js"></script>
  </head>
  
  <body>
    <body class="easyui-layout">
      <div data-options="region:'north',border:false" style="height:32px;padding:3px;background:#eee;">
    	<privilege:enable code="DAYTURNOVER_SEARCH">
	    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="query();">查询</a>
   		</privilege:enable>
    	<privilege:enable code="DAYTURNOVER_CLEAR">
	    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="cancel();">清空</a>
   		</privilege:enable>
   		<privilege:enable code="DAYTURNOVER_EXPORT">
	    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'"  onclick="_except();">Excel导出</a>
   		</privilege:enable>
    </div>  
    <div data-options="region:'center',border:false" style="background:#eee;">
		<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件'" style="padding:3px;background:#eee;height:100px;">
		    	 <form id="dayManageSituationForm">
			        <table>
			           <tr>
			             <td>结算日期:</td>
			             <td>
			             	<input id="beginTime" name="beginTime"  style="width:140px;" class="Wdate"
							 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'endTime\',{d:-1})}'})"/>
			                                              至<input id="endTime" name="endTime" style="width:140px;" class="Wdate"
			                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'beginTime\',{d:0})}'})"/>
			             </td>
			             <td>结算单:</td>
						<td><input type="text" id="dayManageSituation_preclearingClass" name="preclearingClass"  class="easyui-combobox"
		        			 style="width:140px;" data-options="
		        			editable : false,
						    data:[{'id':'aa','text':'维修单'},{'id':'bb','text':'索赔单'},{'id':'cc','text':'所有'}],
							valueField:'id',
							textField:'text'"/></td>
						<td>维修类别:</td>
						<td><input type="text" id="dayManageSituation_reptId" name="reptId" class="easyui-combobox"
							style="width:140px;" data-options="
							url : 'frtOptionsAction!findAllReptype.action',
							valueField:'id',  
							textField:'text',
							validType:'isSelected[\'#dayManageSituation_reptId\']',
							invalidMessage : '请从下拉框中选择维修类别',
							mode:'remote'  "/></td>
						<td>接待员:</td>
						<td><input type="text" id="dayManageSituation_servicePerson" name="servicePerson"class="easyui-combobox"
						 style="width: 140px;"		data-options="
						url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						validType:'isSelected[\'#dayManageSituation_servicePerson\']',
			    		invalidMessage : '请从下拉框中选择接待员',  
						valueField:'id',  
						textField:'text',
						mode : 'remote' "/></td>
						<td>结算员:</td>
						<td><input type="text" id="dayManageSituation_preclrPerson" name="preclrPerson"class="easyui-combobox"
						 style="width: 140px;"		data-options="
						url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						validType:'isSelected[\'#dayManageSituation_preclrPerson\']',
			    		invalidMessage : '请从下拉框中选择结算员',  
						valueField:'id',  
						textField:'text',
						mode : 'remote' "/></td>
			           </tr>
			           <tr>
			           	 <td>客户名称:</td>
						<td><input type="text" id="dayManageSituation_customName" name="customName" class="easyui-combobox"
							style="width:296px;" data-options="
							url : 'frtOptionsAction!findAllCustomAsCustomName.action',
							mode : 'remote',
							valueField:'id',  
							textField:'text' "/></td>
			           	<td>车辆品牌:</td>
						<td><input type="text" id="dayManageSituation_cbrdId" name="cbrdId" class="easyui-combobox" 
						style="width:140px;" data-options="
						url:'${pageContext.request.contextPath}/frtOptionsAction!findCarBrand.action',
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote',
			    		validType:'isSelected[\'#dayManageSituation_cbrdId\']',
			    		invalidMessage : '请从下拉框中选择车辆品牌',
			    		onSelect: function(rec){  
			    			$(this).combobox('textbox').bind('keyup', function (){
			    				if($('#dayManageSituation_cbrdId').combobox('getValue') == '' || $('#dayManageSituation_cbrdId').combobox('getValue') != $('#dayManageSituation_cbrdId').combobox('getText')){
			    					$('#dayManageSituation_ctypeId').combobox('reload', 'frtOptionsAction!findCarType.action');
			    				}
			    			});
				            $('#dayManageSituation_ctypeId').combobox('clear');
				            $('#dayManageSituation_ctypeId').combobox('reload', 'frtOptionsAction!findCarType.action?cbrdId=' + rec.id)  
				        } "/></td>
						<td>车辆型号:</td>
						<td><input type="text" id="dayManageSituation_ctypeId" name="ctypeId" class="easyui-combobox" 
						style="width:140px;" data-options="
						url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarType.action',
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote',
			    		validType:'isSelected[\'#dayManageSituation_ctypeId\']',
			    		invalidMessage : '请从下拉框中选择车辆型号'"/></td>
						<td>车牌照:</td>
						<td><input type="text" id="dayManageSituation_carLicense" name="carLicense" class="easyui-combobox" 
						style="width:140px;" data-options="
							url : 'frtOptionsAction!findAllCarLicenseAsCarLicense.action',
							valueField : 'id',
							textField : 'text',
							mode : 'remote' "/></td>
						<td>显示样式:</td>
			            <td><input id="is3D"  name="is3D" class="easyui-combobox" style="width: 140px;"  
			            	data-options="
						    editable : false,
						    data:[{'id':'false','text':'2D'},{'id':'true','text':'3D'}],
							valueField:'id',
							textField:'text'"/>
						</td>
			           </tr>
			        </table>
			      </form>
		    </div>  
		    <div data-options="region:'center',border:false" style="background:#eee;">
		    	<div class="easyui-layout" style="width:800px; height:600px;" fit="true"
					border="false">
					<div region="north" style="background:#eee;height:380px;" border="false" title="日营业情况数据">
						<div id="dayManageSituationDatagrid_center" style="height:100%;">
							<table id="dayManageSituationDatagrid"></table>
						</div>
					</div>
					<div  region="center" border="false" ondblclick="maxImage();">
						  <span id="snapMapImg" style="width:740px;height:360px;"></span>
						  <span id="poleMapImg" style="width:740px;height:360px;"></span>
					</div>
					
				</div>
		    </div>  
		</div>
    </div>
  </body>
</html>
