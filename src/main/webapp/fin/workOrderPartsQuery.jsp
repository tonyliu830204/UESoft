<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>工单配件查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/workOrderPartsQuery.js"></script>
  </head>
  <body class="easyui-layout">
      <div data-options="region:'north',border:false" style="height:32px;padding:3px;background:#eee;">
       <privilege:enable code="WORKPARTSSEA_SEARCH">
			<a id="_search" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a>    
	   </privilege:enable>
       <privilege:enable code="WORKPARTSSEA_CLEAR">
			<a id="_clear" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'">清空</a>    
	   </privilege:enable>
	   <privilege:enable code="WORKPARTSSEA_EXPORT">
			<a id="_export" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'">Excel导出</a>    
	   </privilege:enable>
    </div>  
    <div data-options="region:'center'" style="background:#eee;">  
        <div id="workOrderPartsTabs" class="easyui-tabs" data-options="fit:true,border:false">  
		    <div title="工单配件查询">
		     <div class="easyui-layout" data-options="fit:true,border:false">
		            <div data-options="region:'north',border:false"
						style="height:110px;background:#eee;overflow: hidden;">
						<form id="workOrderPartsForm">
				          <table border="0">
				           <tr>
				            <td>结算日期:</td>
				            <td>
				            	<input id="workOrderParts_preclrTimeStart" name="preclrTimeStart"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'workOrderParts_preclrTimeEnd\',{d:-1})}'})"/>
		                                              至<input id="workOrderParts_preclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'workOrderParts_preclrTimeStart\',{d:0})}'})"/>
				            </td>
				          	<td>票据类型:</td>
			      			<td><input type="text" id="workOrderParts_preclrInoiceType" name="preclrInoiceType" class="easyui-combobox"
			      			style="width: 140px;"
		        			data-options="
							url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
							validType:'isSelected[\'#workOrderParts_preclrInoiceType\']',
							invalidMessage : '请从下拉框中选择票据类型',
							valueField:'id',  
							textField:'text',
							mode:'remote' "/></td>
				            <td>接车分部:</td>
							<td><input type="text" id="workOrderParts_rcptBranch" name="rcptBranch" class="easyui-combobox" style="width: 140px;"
			        			data-options="
			        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RCPTBRANCH.RCPTBRANCHKEY %>',
			        			validType:'isSelected[\'#workOrderParts_rcptBranch\']',
								invalidMessage : '请从下拉框中选择接车分部',
			        			valueField : 'id',
			        			textField : 'text',
			        			mode:'remote' "/>
			        		</td>
<!--				            <td>客户名称:</td>-->
<!--				            <td><input type="text" name="customName" style="width: 140px;"/></td>-->
				            <td>排序方式:</td>
				            <td><input id="workOrderParts_sortWay"  name="sortWay" class="easyui-combobox" style="width: 140px;"  
				            	data-options="
							    editable : false,
							    data:[{'id':'false','text':'工单'},{'id':'true','text':'配件'}],
								valueField:'id',
								textField:'text'"/>
							</td>
							<td>配件品牌</td>
				            <td><input id="workOrderParts_partsBrandId" name="partsBrandId" class="easyui-combobox"   style="width: 140px;"
				            data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findAllPartsBrand.action',
						    mode : 'remote',
						    validType:'isSelected[\'#workOrderParts_partsBrandId\']',
					    	invalidMessage : '请从下拉框中选择配件品牌',
							valueField:'id',
							textField:'text'"/></td>
				           </tr>
				           <tr>
				            <td>索赔：</td>
							<td>
								<input type="text" name="claimsType" class="easyui-combobox" style="width:296px;" data-options="
								editable:false,multiple:true,separator:',',
								url : 'frtOptionsAction!findAllClaimsType.action',
								valueField:'id',   
							    textField:'text',
							    mode:'remote'"/>
							</td>
				            <td>车辆品牌：</td>
							<td>
								<input type="text" style="width:140px;" id="workOrderParts_carBrandId" name="carBrandId" class="easyui-combobox" 
								data-options="
								url:'${pageContext.request.contextPath}/frtOptionsAction!findCarBrand.action',
								valueField:'id',   
					    		textField:'text',
					    		mode:'remote',
					    		validType:'isSelected[\'#workOrderParts_carBrandId\']',
					    		invalidMessage : '请从下拉框中选择车辆品牌',
					    		onSelect: function(rec){  
					    			$(this).combobox('textbox').bind('keyup', function (){
					    				if($('#workOrderParts_carBrandId').combobox('getValue') == '' || $('#workOrderParts_carBrandId').combobox('getValue') != $('#workOrderParts_carBrandId').combobox('getText')){
					    					$('#workOrderParts_carBrandId').combobox('reload', 'frtOptionsAction!findCarType.action');
					    				}
					    			});
						            $('#workOrderParts_carTypeId').combobox('clear');   
						            $('#workOrderParts_carTypeId').combobox('reload', 'frtOptionsAction!findCarType.action?cbrdId=' + rec.id)  
						        } "/>	
							</td>
							<td>车辆型号：</td>
							<td>
								<input type="text"  style="width:140px;" id="workOrderParts_carTypeId" name="carTypeId" class="easyui-combobox" 
								data-options="
								url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarType.action',
								valueField:'id',   
					    		textField:'text',
					    		mode:'remote',
					    		validType:'isSelected[\'#workOrderParts_carTypeId\']',
					    		invalidMessage : '请从下拉框中选择车辆型号'
					    		"/>
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
				            
				           </tr>
				           <tr>
				            <td>配件名称:</td>
				            <td><input type="text" name="partsName" style="width:296px"/>
				            	<input type="hidden" id="workOrderParts_partsId" name="partsId"/>
				            	</td>
							 <td>维修类别：</td>
							<td>
								<input type="text" id="workOrderParts_reptId" name="reptId" class="easyui-combobox"
		    					 style="width: 140px;"
								data-options="
								mode : 'remote',
								url : 'frtOptionsAction!findAllReptype.action',
								validType:'isSelected[\'#workOrderParts_reptId\']',
								invalidMessage : '请从下拉框中选择维修类别',
								valueField:'id',  
								textField:'text' "/>
							</td>
				            <td>仓别:</td>
				            <td><input id="workOrderParts_storeId" name="storeId" class="easyui-combobox"   style="width: 140px;"
				            data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findAllWorkHouse.action',
							validType:'isSelected[\'#workOrderParts_storeId\']',
							invalidMessage : '请从下拉框中选择仓别',
							mode : 'remote',
							valueField:'id',
							textField:'text'"/></td>
							<td>工单号:</td>
				            <td><input id="workOrderParts_receptionId" name="receptionId" style="width: 140px;"/></td>
				           </tr>
				          </table>
				        </form>
				    </div>
				    <div id="workOrderPartsDatagrid_center" data-options="region:'center',border:false" style="background:#eee;">
				       <table id="workOrderPartsDatagrid"></table>  
				    </div>
			     </div>
		    </div>  
		    <div title="未确认索赔配件查询">  
		        <div class="easyui-layout" data-options="fit:true,border:false">
		            <div data-options="region:'north',border:false"
						style="height:30px;background:#eee;overflow: hidden;">
						<form id="unfinishClaimPartsForm">
				          <table>
					           <tr>
						            <td>结算日期:</td>
						            <td>
						            	<input id="unfinishClaimParts_preclrTimeStart" name="preclrTimeStart"  style="width:140px;" class="Wdate"
										 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'unfinishClaimParts_preclrTimeEnd\',{d:-1})}'})"/>
						                                              至<input id="unfinishClaimParts_preclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
						                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'unfinishClaimParts_preclrTimeStart\',{d:0})}'})"/>
						            </td>
					           </tr>
				          </table>
				        </form>
				    </div>
				    <div id="unfinishClaimPartsDatagrid_center" data-options="region:'center',border:false" style="background:#eee;">
				       <table id="unfinishClaimPartsDatagrid"></table>
				    </div>
			     </div>
		    </div>  
		</div> 
    </div>
  </body>
</html>
