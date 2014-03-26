<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>收款查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		var parame1='<%=Contstants.OPINIONFINISHED_TAG.FINISHED%>';
		var parame2='<%=Contstants.OPINIONFINISHED_TAG.UNPAYMENT%>';
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/gatheringQuery.js"></script>
  </head>

<body class="easyui-layout">
    <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
    <privilege:enable code="GATHERINGQUERY_SEARCH">
	    <a id="search" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_query();">查询</a>
   </privilege:enable>
	<privilege:enable code="GATHERINGQUERY_CLEAR">
	    <a id="clear" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
   </privilege:enable>
<!--	<privilege:enable code="GATHERINGQUERY_REDO">-->
<!--	    <a id="redo" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="modifyTransBalance();">转结算</a>-->
<!--   </privilege:enable>-->
	<privilege:enable code="GATHERINGQUERY_PRINT">
	    <a href="javascript:void(0);" class="easyui-linkbutton" id="print" iconCls="icon-print" plain="true">套打模板</a>
   </privilege:enable>
	<privilege:enable code="GATHERINGQUERY_SET">
      	<a href="javascript:void(0);" class="easyui-linkbutton" id="set" iconCls="icon-set" plain="true">打印设置</a>
   </privilege:enable>
   <privilege:enable code="GATHERINGQUERY_EXPORT">
      	<a id="export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
   </privilege:enable>
		<span id="button"></span>
      </div>  
	<div region="center" style="background:#eee;" border="false">
			<div class="easyui-layout"
				style="overflow: hidden;width:100%;height:100%;" fit="true"
				border="false">
				<div region="north" title="查询条件"
					style="background:#eee;height:102px;padding:3px;" border="false">
					<form id="gatheringQueryForm">
						<table style="min-width:1240px;">
							<tr>
								<td>结算时间:</td>
								<td colspan="3">
									<!--<input type="text" id="addGatheringBeginTime" name="gatheringBeginTime" 
			    					class="easyui-datetimebox" style="width: 140px;"
									data-options="
									editable : false"  />至
									<input type="text" id="addGatheringEndTime" name="gatheringEndTime" 
			    					class="easyui-datetimebox" style="width: 140px;"
									value="{now}"
									data-options="
									editable : false"  />
									-->
									<input id="addGatheringBeginTime" name="gatheringBeginTime"  style="width:140px;" class="Wdate"
									 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'addGatheringEndTime\',{d:-1})}'})"/>
					                                              至<input id="addGatheringEndTime" name="gatheringEndTime" style="width:140px;" class="Wdate"
					                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'addGatheringBeginTime\',{d:0})}'})"/>
								</td>
								<td>结算单号:</td>
								<td><input type="text" name="balanceId" class="easyui-validatebox"></td>
								<td>票据类型:</td>
								<td><input type="text" id="addInvoiceType" name="invoiceType" class="easyui-combobox"
				        			data-options="
				        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
				        			valueField : 'id',
				        			textField : 'text',
				        			validType:'isSelected[\'#addInvoiceType\']',
									invalidMessage : '请从下拉框中选择票据类型',
									mode:'remote'  "/></td>
								<td>票据编号:</td>
								<td><input type="text" name="invoiceNo" class="easyui-validatebox"></td>
								<td>开票时间:</td>
								<td colspan="3">
								<!--<input type="text" id="addinvoiceBeginTime" name="invoiceBeginTime" 
			    					class="easyui-datetimebox" style="width: 140px;"
									data-options="
									editable : false"  />至
									<input type="text" id="addinvoiceEndTime" name="invoiceEndTime" 
			    					class="easyui-datetimebox" style="width: 140px;"
									value="{now}"
									data-options="
									editable : false"  />
								-->
									<input id="addinvoiceBeginTime" name="invoiceBeginTime"  style="width:140px;" class="Wdate"
									 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'addinvoiceEndTime\',{d:-1})}'})"/>
					                                              至<input id="addinvoiceEndTime" name="invoiceEndTime" style="width:140px;" class="Wdate"
					                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'addinvoiceBeginTime\',{d:0})}'})"/>
								</td>
							</tr>
							<tr>
								<td>车牌照:</td>
								<td><input type="text" id="gatheringQueryCarId" name="carId" class="easyui-combobox" data-options="
									url : 'frtOptionsAction!findAllCarLicense.action',
									valueField : 'id',
									textField : 'text',
									mode : 'remote' "/></td>
								<td>VIN号:</td>
								<td><input type="text" id="carVin" maxlength="20" name="carVin"
									 class="easyui-combobox" data-options="
									url : 'frtOptionsAction!findAllCarVin.action',
									editabled:true,
									valueField : 'text',
									textField : 'text',
									mode : 'remote'"/></td>
								<td>联系人:</td>
								<td><input type="text" name="linkMan" class="easyui-validatebox"></td>
								<td>联系人电话:</td>
								<td><input type="text" name="linkTel" class="easyui-validatebox"></td>
								<td>客户名称:</td>
								<td><input type="text" id="addCustomId" name="customId"
									class="easyui-combobox"
									data-options="
									url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCustom.action', 
									valueField:'id',  
									textField:'text', 
								 	validType:'isSelected[\'#addCustomId\']',
									invalidMessage : '请从下拉框中选择客户名称',
									mode:'remote' " /></td>
								<td>维修类别:</td>
								<td><input type="text" id="addReptId" name="reptId" class="easyui-combobox"
									data-options="
									url : 'frtOptionsAction!findAllReptype.action',
									valueField:'id',  
									textField:'text',
									validType:'isSelected[\'#addReptId\']',
									invalidMessage : '请从下拉框中选择维修类别',
									mode:'remote'  "/></td>
								<td>结算单分类:</td>
								<td><input type="text" id="addPreclearingClass" name="preclearingClass" class="easyui-combobox"
				        			data-options="
				        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSKEY %>',
				        			valueField : 'id',
				        			textField : 'text',
				        			validType:'isSelected[\'#addPreclearingClass\']',
									invalidMessage : '请从下拉框中选择结算单分类',
									mode:'remote'  "/></td>
							</tr>
							</table>
					</form>
				</div>
				<div region="center" style="background:#EEEEEE;" border="false">
						<div class="easyui-layout"
							style="overflow: hidden;width:100%;height:100%;" fit="true"
							border="false">
							<div id="gatheringDatagrid_center" region="center" border="false" style="background:#eeeeee;">
									<table id="gatheringDatagrid"></table>
							</div>
							<div id="oldGatheringDatagrid_center" region="south" title="收款记录" style="height:240px;background:#eeeeee;" border="false">
									<table id="oldGatheringDatagrid"></table>
							</div>
						</div>
				</div>
			</div>	    
	</div>
</body>