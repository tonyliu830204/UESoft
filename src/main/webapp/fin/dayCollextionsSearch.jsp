<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>日收款查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/dayCollextionsSearch.js"></script>
  </head>
  <body class="easyui-layout">
      <div data-options="region:'north',border:false" style="height:32px;padding:3px;background:#eee;">
	       <privilege:enable code="FINANCEDAYBALANCE_SEARCH">
			      <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="searchByCondition();">查询</a>
		   </privilege:enable>
		   <privilege:enable code="FINANCEDAYBALANCE_CLEAR">
			      <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="clearCondition();">清空</a>
		   </privilege:enable>
		   <privilege:enable code="FINANCEDAYBALANCE_EXPORT">
			      <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'" onclick="_except();">Excel导出</a>
		   </privilege:enable>
    </div>  
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件'" style="background:#eee;height:95px;">
		      <form id="dayBusinessForm">
		        <table>
		           <tr>
		             <td>收款日期:</td>
		             <td>
			             <input id="preclrDateStart" name="preclrDateStart"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'preclrDateEnd\',{d:-1})}'})"/>
		                                              至<input id="preclrDateEnd" name="preclrDateEnd" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'preclrDateStart\',{d:0})}'})"/>
		             </td>
		             <td>收款分类:</td>
		             <td>
		             	<input id="dayBusiness_classWay"  name="classWay" class="easyui-combobox" style="width: 140px;"  
			            	data-options="
						    editable : false,multiple:true,separator:',',panelHeight:70,
						    data:[{'id':'service','text':'维修收款'},{'id':'claims','text':'索赔收款'}],
							valueField:'id',
							textField:'text'"/>
							<!-- ,{'id':'sell','text':'销售收款'} -->
		             </td>
		             <td>票据类型:</td>
		      		 <td><input type="text" id="dayBusiness_invoiceType"  style="width:140px;" name="invoiceType" class="easyui-combobox"
	        			data-options="
						url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
						validType:'isSelected[\'#dayBusiness_invoiceType\']',
						invalidMessage : '请从下拉框中选择票据类型',
						valueField:'id',  
						textField:'text',
						mode:'remote' "/></td>
					<td>车辆品牌:</td>
					<td>
					    <input type="text" id="dayBusiness_cbrdId" name="cbrdId" style="width:140px;"
					     class="easyui-combobox" 
					     data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findCarBrand.action',
					     validType:'isSelected[\'#dayBusiness_cbrdId\']',
						 invalidMessage : '请从下拉框中选择车辆品牌',
					     valueField:'id',
					     textField:'text',
					     mode:'remote'"/>
					</td>
					<td>维修类别:</td>
					<td><input type="text" id="dayBusiness_reptId" style="width:140px;" name="reptId" class="easyui-combobox"
					data-options="
					url : 'frtOptionsAction!findAllReptype.action',
					validType:'isSelected[\'#dayBusiness_reptId\']',
					invalidMessage : '请从下拉框中选择维修类别',
					mode : 'remote',
					valueField:'id',  
					textField:'text' "/></td>
		           </tr>
		           <tr>
		             <td>客户名称:</td>
		             <td><input type="text" name="customName" style="width:296px;"/></td>
		            <td>车牌照</td>
					<td><input type="text" id="dayBusiness_carLicense"  style="width:140px;" name="carLicense" class="easyui-combobox" data-options="
						url : 'frtOptionsAction!findAllCarLicenseAsCarLicense.action',
						valueField : 'id',
						textField : 'text',
						mode : 'remote' "/></td>
		             <td>付款方式:</td>
		             <td>
		             	<input type="text" id="dayBusiness_gatheringWise" style="width:140px;" name="gatheringWise" class="easyui-combobox"
		      			style="width:140px;"
		      			data-options="
		      			validType:'isSelected[\'#dayBusiness_gatheringWise\']',
						invalidMessage : '请从下拉框中选择收款方式',
						mode : 'remote',
		      			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY %>',
		      			valueField : 'id',
		      			textField : 'text' "/>	
					</td>
					<td>接待员:</td>
						<td><input type="text" id="dayBusiness_servicePerson" name="servicePerson" class="easyui-combobox"
						 style="width: 140px;" readonly="readonly"
						data-options="
						url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						validType:'isSelected[\'#dayBusiness_servicePerson\']',
						invalidMessage : '请从下拉框中选择接待员',
						mode : 'remote',
						valueField:'id',  
						textField:'text' "/></td>
					<td>接车分部:</td>
					<td><input type="text" id="dayBusiness_rcptBranch" style="width:140px;" name="rcptBranch" class="easyui-combobox"
	        			data-options="
	        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RCPTBRANCH.RCPTBRANCHKEY %>',
	        			validType:'isSelected[\'#dayBusiness_rcptBranch\']',
						invalidMessage : '请从下拉框中选择接车分部',
						mode : 'remote',
	        			valueField : 'id',
	        			textField : 'text' "/>
	        		</td>
		           </tr>
		        </table>
		       </form>
		    </div>  
		    <div id="dayCollextionsSearchDatagrid_center" data-options="region:'center',border:false" style="background:#eee;">
		     <table id="dayCollextionsSearchDatagrid"></table>
		    </div>  
		</div>  
    </div>
  </body>
</html>
