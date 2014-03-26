<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>结算单-配件清单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtWorkOrder/preClearingParts.js"></script>
  </head>
  
  <body>
    	<div class="easyui-layout" style="width:800px; height:600px;" fit="true">
			<div region="north" style="height:60px;background:#eee;padding:3px;"
				border="false">
				<div>
				    <privilege:enable code="COLLIGATE_BPARTS_SEARCH">
				      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryFrtWorkOrderPreClearingParts();">查询</a>
				   </privilege:enable>
					<privilege:enable code="COLLIGATE_BPARTS_CLEAR">
				      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearFrtWorkOrderPreClearingParts();">清空</a>
				   </privilege:enable>
					<privilege:enable code="COLLIGATE_BPARTS_EXPROT">
				      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exceptFrtWorkOrderPreClearingParts();">Excel导出</a>
				   </privilege:enable>
			    </div>  
				<form id="frtWorkOrderPreClearingPartsQueryForm">
					<table>
						<tr>
							<td>结算日期:</td>
							<td colspan="2">
							<!--<input type="text" id="preclrTimeBegin" name="preclrTimeBegin" style="width: 140px;"
							class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>至
							<input type="text" id="preclrTimeEnd" name="preclrTimeEnd" style="width: 140px;"
							class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
							-->
								<input id="preclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
								 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'preclrTimeEnd\',{d:-1})}'})"/>
				                                              至<input id="preclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
				                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'preclrTimeBegin\',{d:0})}'})"/>
							</td>
							<td>车辆牌照:</td>
							<td><input type="text" id="frtWorkOrderPreClearingPartsQueryCarId" name="carId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllCarLicenseAsCarLicense.action',
								valueField : 'id',
								textField : 'text',
								mode : 'remote'"/></td>
								<td>客户名称:</td>
							<td><input type="text" id="frtWorkOrderPreClearingPartsQueryCustomId" name="customId" class="easyui-combobox"
								data-options="
								url : 'frtOptionsAction!findAllCustom.action',
								validType:'isSelected[\'#frtWorkOrderPreClearingPartsQueryCustomId\']',
								invalidMessage : '请从下拉框中选择客户名称',
								mode:'remote' ,
								valueField:'id',  
								textField:'text' "/></td>
							<td>结算单号:</td>
							<td>
								<input type="text" name="preclrId" style="width:180px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="frtWorkOrderPreClearingPartsDatagrid_center" region="center" style="background:#eee;" border="false">
				<table id="frtWorkOrderPreClearingPartsDatagrid"></table>
			</div>
		</div>
  </body>
</html>