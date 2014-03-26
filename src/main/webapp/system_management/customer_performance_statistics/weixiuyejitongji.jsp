<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>维修人员业绩统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/system_management/customer_performance_statistics/weixiuyejitongji.js"></script>
  </head>
  
  <body>
    <div class="easyui-layout" border="false" fit="true"
		style="width: 800px; height: 600px;">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:120px;" border="false">
		   <div>
			   	<privilege:enable code="ACHIEVEPERSON_SEARCH">
				    <a id="_search" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryServicePerson();">查询</a>
			   </privilege:enable>
				<privilege:enable code="ACHIEVEPERSON_CLEAR">
				    <a id="_clear" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearServicePerson();">清空</a>
			   </privilege:enable>
			   <privilege:enable code="ACHIEVEPERSON_EXPORT">
				    <a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exceptServicePerson();">Excel导出</a>
			   </privilege:enable>
		   </div>
			<form id="servicePersonQueryForm" method="post">
				<table border="0"  style="text-align: right">
					<tr>
						<td>结算日期：</td>
						<td>
							<input id="servicePerson_preclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'servicePerson_preclrTimeEnd\',{d:-1})}'})"/>
		                                              至<input id="servicePerson_preclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'servicePerson_preclrTimeBegin\',{d:0})}'})"/>
						</td>
						<td>维修班组：</td>
						<td>
							<input type="text" id="servicePerson_serviceGroupId" name="serviceGroupId" class="easyui-combobox"
	    					 style="width: 140px;"
							data-options="
							url : 'frtOptionsAction!findAllRepairGroup.action',
							validType:'isSelected[\'#servicePerson_serviceGroupId\']',
							invalidMessage : '请从下拉框中选择维修班组',
							mode:'remote',
							valueField:'id',  
							textField:'text' "/>	
						</td>
						<td>车牌照：</td>
						<td>
						<input type="text"  style="width:140px;" name="carLicense" class="easyui-combobox"
						 data-options="
						 url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCarLicenseAsCarLicense.action',
						valueField : 'id',
						textField : 'text',
						mode : 'remote'"/>
						</td>
						<td>维修类别：</td>
						<td>
							<input type="text" id="servicePerson_reptId" name="reptId" class="easyui-combobox"
	    					 style="width: 140px;"
							data-options="
							mode : 'remote',
							url : 'frtOptionsAction!findAllReptype.action',
							validType:'isSelected[\'#servicePerson_reptId\']',
							invalidMessage : '请从下拉框中选择维修类别',
							valueField:'id',  
							textField:'text' "/>
						</td>
					</tr>
					<tr>
						<td>维修项目：</td>
						<td><input type="text" name="itemName" style="width:296px;"/></td>
						<td>车辆品牌：</td>
						<td>
							<input type="text" style="width:140px;" id="servicePerson_carBrandId" name="carBrandId" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/frtOptionsAction!findCarBrand.action',
							valueField:'id',   
				    		textField:'text',
				    		mode:'remote',
				    		validType:'isSelected[\'#servicePerson_carBrandId\']',
				    		invalidMessage : '请从下拉框中选择车辆品牌',
				    		onSelect: function(rec){  
				    			$(this).combobox('textbox').bind('keyup', function (){
				    				if($('#servicePerson_carBrandId').combobox('getValue') == '' || $('#servicePerson_carBrandId').combobox('getValue') != $('#servicePerson_carBrandId').combobox('getText')){
				    					$('#servicePerson_carTypeId').combobox('reload', 'frtOptionsAction!findCarType.action');
				    				}
				    			});
					            $('#servicePerson_carTypeId').combobox('clear');   
					            $('#servicePerson_carTypeId').combobox('reload', 'frtOptionsAction!findCarType.action?cbrdId=' + rec.id)  
					        } "/>	
						</td>
						<td>车辆型号：</td>
						<td>
							<input type="text"  style="width:140px;" id="servicePerson_carTypeId" name="carTypeId" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarType.action',
							valueField:'id',   
				    		textField:'text',
				    		mode:'remote',
				    		validType:'isSelected[\'#servicePerson_carTypeId\']',
				    		invalidMessage : '请从下拉框中选择车辆型号'"/>
						</td>
					</tr>
					<tr>
						<td>索赔：</td>
						<td>
							<input type="text" name="claimsTerm" class="easyui-combobox" style="width:296px;" data-options="
							editable:false,multiple:true,separator:',',
							url : 'frtOptionsAction!findAllClaimsType.action',
							valueField:'id',   
						    textField:'text',
						    mode:'remote'"/>
						</td>
						<td>接车分部：</td>
						<td>
							<input name="rcptBranch"  style="width:140px;" id="servicePerson_rcptBranch"
								class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RCPTBRANCH.RCPTBRANCHKEY %>',
								validType:'isSelected[\'#servicePerson_rcptBranch\']',
								invalidMessage : '请从下拉框中选择接车分部',
								mode : 'remote',
								valueField : 'id',
		        				textField : 'text'"/>
						</td>
						<td>排列方式：</td>
						<td>
							<input type="text"  style="width:140px;" id="servicePerson_weaveWay" name="weaveWay" class="easyui-combobox" data-options="
							editable:false,
							data:[{'id':'false','text':'维修员'},{'id':'true','text':'工单号'}],
	   						valueField:'id',  
	   					    textField:'text'"/>
						</td>
					</tr>
				</table>
			</form>
			<table style="margin-top:-66px;margin-left:780px;">
				<tr>
					<td>维修人员：</td>
					<td>
						<input  name="stfId"  style="width:140px;" id="servicePerson_stfId"
							class="easyui-combobox"
							data-options="
							validType:'isSelected[\'#servicePerson_stfId\']',
							invalidMessage : '请从下拉框中选择维修人员',
							mode : 'remote',
							url : 'basStuffClassAction!findEnterpriseMaintainArtificer.action',
							valueField:'id',  
							textField:'text' "
							/>
					</td>
				</tr>
			</table>
		</div>
		<div id="servicePersonDatagrid_center" region="center" border="false" style="background : #eee">
			<table id="servicePersonDatagrid"></table>
		</div>
	</div>
  </body>
</html>
