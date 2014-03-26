<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>工单-维修配件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtWorkOrder/workOrderParts.js"></script>
  </head>
  
  <body>
    	<div class="easyui-layout" style="width:800px; height:600px;" fit="true"
			border="false">
			<div region="north" title="查询条件"
				style="overflow: hidden;background:#eee;height:120px;" border="false">
				<div>
				    <privilege:enable code="COLLIGATE_SPARTS_SEARCH">
				      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryFrtWorkOrderParts();">查询</a>
				   </privilege:enable>
					<privilege:enable code="COLLIGATE_SPARTS_CLEAR">
				      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearFrtWorkOrderParts();">清空</a>
				   </privilege:enable>
					<privilege:enable code="COLLIGATE_SPARTS_EXPROT">
				      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exceptFrtWorkOrderParts();">Excel导出</a>
				   </privilege:enable>
		      </div>  
				<form id="frtWorkOrderPartsQueryForm">
					<table>
						<tr>
							<td>结算日期:</td>
							<td colspan="3">
							<input id="frtWorkOrderPartsQueryPreclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
								 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'frtWorkOrderPartsQueryPreclrTimeEnd\',{d:-1})}'})"/>
				                                              至<input id="frtWorkOrderPartsQueryPreclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
				                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'frtWorkOrderPartsQueryPreclrTimeBegin\',{d:0})}'})"/>
							</td>
							<td>客户名称:</td>
							<td><input type="text" id="frtWorkOrderPartsQueryCustomId" name="customId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllCustom.action',
								validType:'isSelected[\'#frtWorkOrderPartsQueryCustomId\']',
								invalidMessage : '请从下拉框中选择客户名称',
								mode : 'remote',
								valueField:'id',  
								textField:'text' "/></td>
							<td>配件型号:</td>
							<td><input type="text" id="frtWorkOrderPartsQueryPtypeId" name="ptypeId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllPartsType.action',
								validType:'isSelected[\'#frtWorkOrderPartsQueryPtypeId\']',
								invalidMessage : '请从下拉框中选择配件型号',
								mode : 'remote',
								valueField:'id',  
								textField:'text' "/></td>
							<td>接待员:</td>
							<td><input type="text" id="frtWorkOrderPartsQueryReceivePerson" name="receivePerson" class="easyui-combobox"
								data-options="
								url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
								validType:'isSelected[\'#frtWorkOrderPartsQueryReceivePerson\']',
								invalidMessage : '请从下拉框中选择接待员',
								mode : 'remote',
								valueField:'id',  
								textField:'text' "/></td>
							<td>检修技师:</td>
							<td><input type="text" id="frtWorkOrderPartsQueryReceptionTechnician"  name="receptionTechnician" class="easyui-combobox"
								data-options="
								url : 'basStuffClassAction!findEnterpriseMaintainArtificer.action',
								validType:'isSelected[\'#frtWorkOrderPartsQueryReceptionTechnician\']',
								invalidMessage : '请从下拉框中选择维修技师',
								valueField:'id',   
							    textField:'text',  
								mode : 'remote' "/>
							</td>
						</tr>
			
						<tr>
							<td>车辆牌照:</td>
							<td><input type="text" id="frtWorkOrderPartsQueryCarId" name="carId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllCarLicense.action',
								    editable : true,
								    mode:'remote',
								    validType:'isSelected[\'#frtWorkOrderPartsQueryCarId\']',
									invalidMessage : '请从下拉框中选择车辆牌照',
									valueField:'id',
									panelHeight:130,
									textField:'text'"
								/></td>
							<td>配件名称:</td>
							<td><input type="text" id="frtWorkOrderPartsQueryPartsName" name="partsName"/>
							</td>
							<td>维修类别:</td>
							<td><input type="text" id="frtWorkOrderPartsQueryReptId" name="reptId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllReptype.action',
								valueField:'id',  
								textField:'text',
								validType:'isSelected[\'#frtWorkOrderPartsQueryReptId\']',
								invalidMessage : '请从下拉框中选择维修类别',
								mode:'remote'  "/></td>
							<td>索赔分类:</td>
							<td><input type="text" id="frtWorkOrderPartsQueryClaimsId" name="claimsId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllClaimsType.action',
								valueField:'id',   
							    textField:'text',
							    validType:'isSelected[\'#frtWorkOrderPartsQueryClaimsId\']',
								invalidMessage : '请从下拉框中选择索赔分类',
								mode:'remote' "/></td>
							<td>结算单分类:</td>
							<td><input type="text" id="frtWorkOrderPartsQueryBalanceClass" name="balanceClass" class="easyui-combobox"
								data-options="
								disabled:true,
								url : 'frtOptionsAction!findBaseListData.action?key=balClass',
								valueField:'id',  
								textField:'text' "/></td>
							<td>领料员:</td>
							<td><input type="text" id="frtWorkOrderPartsQueryReceivePerson"  name="bringPerson" class="easyui-combobox"
								data-options="
								url : 'basStuffClassAction!findEnterpriseMaterielPerson.action',
								valueField:'id',   
							    textField:'text',  
								validType:'isSelected[\'#frtWorkOrderPartsQueryReceivePerson\']',
								invalidMessage : '请从下拉框中选择领料员',
								mode:'remote'  "/></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="frtWorkOrderPartsDatagrid_center" region="center" style="background:#eee;" border="false">
				<table id="frtWorkOrderPartsDatagrid"></table>
			</div>
		</div>
  </body>
</html>
