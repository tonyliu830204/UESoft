<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>维修类别与接待员分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/system_management/maintenance_traffic_analysis/serviceReceiveAnalyze.js"></script>
  </head>
  <body>
  		 <div class="easyui-layout" style="width:800px; height:600px;" fit="true"
			border="false">
			 <div region="north"  split="false" style="height:70px;background: #eee;" border="false">
			    <privilege:enable code="GUESTANALY_PERSON_SEARCH">
				    <a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryMaintenanceTrafficAnalysisServiceReceiveAnalyze();">查询</a>
			   </privilege:enable>
				<privilege:enable code="GUESTANALY_PERSON_CLEAR">
				    <a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
			   </privilege:enable>
			   <privilege:enable code="GUESTANALY_PERSON_EXPORT">
				    <a id='_export' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exceptMaintenanceTrafficAnalysisServiceReceiveAnalyze();">Excel导出</a>
			   </privilege:enable>
			<br/>
			<form id="maintenanceTrafficAnalysisQueryForm">
				<table>
					<tr>
						<td>维修日期:</td>
						<td colspan="3">
						<!--<input type="text" id="serviceDateBegin" name="serviceDateBegin" style="width:140px;"
						class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						<input type="text" id="serviceDateEnd" name="serviceDateEnd" style="width: 140px;"
						class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						-->
							 <input id="serviceDateBegin" name="serviceDateBegin"  style="width:140px;" class="Wdate"
								 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'serviceDateEnd\',{d:-1})}'})"/>
				                                              至<input id="serviceDateEnd" name="serviceDateEnd" style="width:140px;" class="Wdate"
				                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'serviceDateBegin\',{d:0})}'})"/>
						</td>		
			  			<td>车辆品牌:</td>
						<td><input type="text" id="carArchives_add_cbrdId" name="cbrdId" class="easyui-combobox" data-options="
						url:'${pageContext.request.contextPath}/frtOptionsAction!findCarBrand.action',
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote',
			    		validType:'isSelected[\'#carArchives_add_cbrdId\']',
						invalidMessage : '请从下拉框中选择车辆品牌',
			    		onSelect: function(rec){  
			    			$(this).combobox('textbox').bind('keyup', function (){
			    				if($('#carArchives_add_cbrdId').combobox('getValue') == '' || $('#carArchives_add_cbrdId').combobox('getValue') != $('#carArchives_add_cbrdId').combobox('getText')){
			    					$('#carArchives_add_ctypeId').combobox('reload', 'frtOptionsAction!findCarType.action');
			    				}
			    			});
				            $('#carArchives_add_ctypeId').combobox('clear');
				            $('#carArchives_add_ctypeId').combobox('reload', 'frtOptionsAction!findCarType.action?cbrdId=' + rec.id)  
				        } "/></td>
						<td>车辆型号:</td>
						<td><input type="text" id="carArchives_add_ctypeId" name="ctypeId" class="easyui-combobox" data-options="
						url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarType.action',
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote',
			    		validType:'isSelected[\'#carArchives_add_ctypeId\']',
						invalidMessage : '请从下拉框中选择车辆型号'"/></td>
						<td>接待员:</td>
						<td><input type="text" id="frtWorkOrderPartsQueryReceivePerson" name="receivePerson" class="easyui-combobox"
							data-options="
							url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							mode : 'remote',
							validType:'isSelected[\'#frtWorkOrderPartsQueryReceivePerson\']',
							invalidMessage : '请从下拉框中选择接待员',
							valueField:'id',  
							textField:'text' "/></td>
						<td>显示样式:</td>
						<td>
							<input id="is3D"  name="is3D" class="easyui-combobox" style="width: 140px;"  
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
			<div region="center" style="background:#eee;" border="false" height="100%">
				<div class="easyui-layout" style="width:800px; height:600px;" fit="true"
					border="false">
					<div region="north" style="background:#eee;height:380px;" border="false" title="维修类别接待员分析数据">
						<div id="maintenanceTrafficAnalysis_serviceReceiveAnalyze_center" style="height:100%;">
							<table id="maintenanceTrafficAnalysis_serviceReceiveAnalyze"></table>
						</div>
					</div>
					<!--<div  region="center" border="false" ondblclick="maxImage();">
						  <span id="poleMapImg" style="width:1200px;height:360px;"></span>
						  <span id="cakeMapImg" style="width:360px;height:360px;"></span>
					</div>-->
					<div  region="center" border="false" ondblclick="maxImage();" style="width:1560px;">
						  <span id="analyseLoaderPoleMap" style="float:left;width:360px;height:360px;margin-top:100px;margin-left:100px;"></span>
						  <span id="poleMapImg" style="float:left;width:1200px;height:360px;"></span>
						  <span id="analyseLoaderCakeMap" style="float:left;width:360px;height:360px;margin-top:100px;margin-left:100px;"></span>
						  <span id="cakeMapImg" style="float:left;width:360px;height:360px;"></span>
					</div>
				</div>
			</div>
		</div>
  </body>
</html>