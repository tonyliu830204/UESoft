<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>工单历史记录查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/receptionHistoryQuery.js"></script>
  </head>
  
  <body>
    	<div class="easyui-layout" style="width:800px; height:600px" fit="true"
			border="false">
			<div region="north" title="查询条件"
				style="overflow: hidden;background:#eee; height:145px;" border="false">
				<div>
				    <privilege:enable code="RECEPTIONHISTORYQUERY_SEARCH">
				      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryFrtWorkOrderItem();">查询</a>
				   </privilege:enable>
					<privilege:enable code="RECEPTIONHISTORYQUERY_CLEAR">
				      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearFrtWorkOrderItem();">清空</a>
				   </privilege:enable>
					<privilege:enable code="RECEPTIONHISTORYQUERY_EXPROT">
				      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exceptFrtWorkOrderItem();">Excel导出</a>
				   </privilege:enable>
		      </div>  
				<form id="frtWorkOrderItemQueryForm">
					<table>
						<tr>
							<td>结算日期:</td>
							<td colspan="2">
							<!--<input class="Wdate" id="frtWorkOrderItemQueryPreclrTimeBegin" name="preclrTimeBegin" onfocus="WdatePicker({});" />
							至<input type="text" class="Wdate" id="frtWorkOrderItemQueryPreclrTimeEnd" name="preclrTimeEnd" onfocus="WdatePicker({});" />
							-->
								<input id="frtWorkOrderItemQueryPreclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
							 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'frtWorkOrderItemQueryPreclrTimeEnd\',{d:-1})}'})"/>
			                                              至<input id="frtWorkOrderItemQueryPreclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
			                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'frtWorkOrderItemQueryPreclrTimeBegin\',{d:0})}'})"/>
							</td>
							<td>客户名称:</td>
							<td><input type="text" id="frtWorkOrderItemQueryCustomId" name="customId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllCustom.action',
								validType:'isSelected[\'#frtWorkOrderItemQueryCustomId\']',
								invalidMessage : '请从下拉框中选择客户名称',
								mode : 'remote',
								valueField:'id',  
								textField:'text' "/></td>
							<td>维修班组:</td>
							<td><input type="text" id="frtWorkOrderItemQueryRepgrpId" name="repgrpId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllRepairGroup.action',
								valueField:'id',  
								textField:'text', 
								validType:'isSelected[\'#frtWorkOrderItemQueryRepgrpId\']',
								invalidMessage : '请从下拉框中选择维修班组',
								mode:'remote' "/></td>
							<td>接待人员:</td>
							<td><input type="text" id="frtWorkOrderItemQueryReceivePerson" name="receivePerson" class="easyui-combobox"
								data-options="
								url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
								validType:'isSelected[\'#frtWorkOrderItemQueryReceivePerson\']',
								invalidMessage : '请从下拉框中选择接待人员',
								mode:'remote', 
								valueField:'id',  
								textField:'text' "/></td>
							<td>维修员:</td>
							<td><input type="text" id="frtWorkOrderItemQueryServicePerson"  name="servicePerson" class="easyui-combobox"
								data-options="
								url : 'basStuffClassAction!findEnterpriseMaintainArtificer.action',
								valueField:'id',   
							    textField:'text',  
								validType:'isSelected[\'#frtWorkOrderItemQueryServicePerson\']',
								invalidMessage : '请从下拉框中选择维修员',
								mode:'remote'  "/></td>
						</tr>
			
						<tr>
							<td>进厂日期:</td>
							<td colspan="2">
							<!--<input type="text" id="frtWorkOrderItemQueryInterDateBegin" name="interDateBegin" class="Wdate" onfocus="WdatePicker({});" />
							至<input type="text" class="Wdate" id="frtWorkOrderItemQueryInterDateEnd" name="interDateEnd" onfocus="WdatePicker({});" />
							-->
								<input id="frtWorkOrderItemQueryInterDateBegin" name="interDateBegin"  style="width:140px;" class="Wdate"
							 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'frtWorkOrderItemQueryInterDateEnd\',{d:-1})}'})"/>
			                                              至<input id="frtWorkOrderItemQueryInterDateEnd" name="interDateEnd" style="width:140px;" class="Wdate"
			                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'frtWorkOrderItemQueryInterDateBegin\',{d:0})}'})"/>
							</td>
							<td>车辆品牌:</td>
							<td><input type="text" id="frtWorkOrderItemQueryCbrdId" name="cbrdId" class="easyui-combobox" 
							data-options="
								url:'${pageContext.request.contextPath}/frtOptionsAction!findCarBrand.action',
								validType:'isSelected[\'#frtWorkOrderItemQueryCbrdId\']',
								invalidMessage : '请从下拉框中选择车辆品牌',
								valueField:'id',   
					    		textField:'text', 
					    		mode : 'remote'"/></td>
							<td>维修类别:</td>
							<td><input type="text" id="frtWorkOrderItemQueryReptId" name="reptId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllReptype.action',
								validType:'isSelected[\'#frtWorkOrderItemQueryReptId\']',
								invalidMessage : '请从下拉框中选择维修类别',
								mode:'remote' ,
								valueField:'id',  
								textField:'text' "/></td>
							<td>索赔分类:</td>
							<td><input type="text" id="frtWorkOrderItemQueryClaimsId" name="claimsId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllClaimsType.action',
								validType:'isSelected[\'#frtWorkOrderItemQueryClaimsId\']',
								invalidMessage : '请从下拉框中选择索赔分类',
								mode:'remote', 
								valueField:'id',   
							    textField:'text'"/></td>
							<td colspan="2"><input type="checkbox" id="frtWorkOrderItemQueryShowUnBalance" value="true"
								 name="showUnBalance" style="width:20px;">显示未结算</td>
						</tr>
			
						<tr>
							<td>车辆牌照:</td>
							<td><input type="text" id="frtWorkOrderItemQueryCarId" name="carId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllCarLicenseAsCarLicense.action',
								valueField : 'id',
								textField : 'text',
								mode : 'remote'"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修项目:</td>
							<td colspan="3"><input type="text" id="frtWorkOrderItemQueryItemName" name="itemName" style="width:160px;" />
							</td>
							<td>结算单分类:</td>
							<td><input type="text" name="balanceClass" class="easyui-combobox" 
								data-options="
								disabled:true,
								url : 'frtOptionsAction!findBaseListData.action?key=balClass',
								valueField:'id',  
								textField:'text' "/></td>
							<td>排列方式:</td>
							<td>
							<select name="arrangeWise">
									<option value="receptionId">工单号</option>
									<option value="carId">车牌照</option>
							</select></td>
							<td>检修技师:</td>
							<td><input type="text" id="frtWorkOrderItemQueryReceptionTechnician" name="receptionTechnician" class="easyui-combobox"
								data-options="
								url:'${pageContext.request.contextPath}/basStuffClassAction!findEnterpriseMaintainArtificer.action',
								validType:'isSelected[\'#frtWorkOrderItemQueryReceptionTechnician\']',
								invalidMessage : '请从下拉框中选择检修技师',
								mode : 'remote', 
								valueField:'id',  
								textField:'text' "/></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="frtWorkOrderItemDatagrid_center" region="center" style="background:#eee;" border="false">
				<table id="frtWorkOrderItemDatagrid"></table>
			</div>
		</div>
  </body>
</html>