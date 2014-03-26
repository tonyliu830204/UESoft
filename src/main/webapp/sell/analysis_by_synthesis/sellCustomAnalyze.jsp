<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>销售客户分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <script type="text/javascript">
    	var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
	</script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/sell/analysis_by_synthesis/sellCustomAnalyze.js"></script>
  </head>
  <body>
  		 <div class="easyui-layout" style="width:800px; height:600px;" fit="true"
			border="false">
			 <div region="north"  split="false" style="height:85px;background: #eee;" border="false">
			    <privilege:enable code="GUESTANALY_PERSON_SEARCH">
				    <a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryReceiveAnalyze();">查询</a>
			   </privilege:enable>
				<privilege:enable code="GUESTANALY_PERSON_CLEAR">
				    <a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
			   </privilege:enable>
			   <privilege:enable code="GUESTANALY_PERSON_EXPORT">
				    <a id='_export' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exceptMaintenanceTrafficAnalysisServiceReceiveAnalyze();">导出</a>
			   </privilege:enable>
			   	<a id='_print' href="javascript:void(0);" class="easyui-linkbutton"	iconCls="icon-print" plain="true" onclick="_config();">打印</a>
			   
			<br/>
			<form id="QueryForms">
				<table>
					<tr>
					
						<td style="display: none"><input name="flag" value="flase"/></td>	
						<td>销售日期:</td>
						<td >
						<input type="text" class="Wdate"
						onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xsCarSelData2\',{d:-1})}'})"
						name="xsCarSelData" id="xsCarSelData" style="width: 100px;" 
						 />至
				
					<input type="text" class="Wdate"
						onclick="WdatePicker({minDate:'#F{$dp.$D(\'xsCarSelData\',{d:1})}'})"
						name="xsCarSelData2" id="xsCarSelData2" style="width: 100px;" 
						 />
					</td>
						
			  			<td>
							品牌:
						</td>
							<td><input type="text" id="car_Brand_id" name="carBrand" style="width:100px;" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
							valueField:'id',   
				    		textField:'text',
				    		mode:'remote',			    		
				    		validType:'isSelected[\'#car_Brand_id\']',
				    		invalidMessage : '请从下拉框中选择车辆品牌',
				    		onSelect: function(rec){  
				    			$(this).combobox('textbox').bind('keyup', function (){
				    				if($('#car_Brand_id').combobox('getValue') == '' || $('#car_Brand_id').combobox('getValue') != $('#car_Brand_id').combobox('getText')){
				    					$('#car_Model_id').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
				    				}
				    			});
					            $('#car_Model_id').combobox('clear');
					            $('#car_Model_id').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
					        } "
					        />
					       
					        </td>
						<td>车型:</td>
							<td><input type="text" id="car_Model_id" name="carModel" style="width:130px;"  class="easyui-combobox" 
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
				    					$('#carArchives_add_carCstlId').combobox('reload', 'carModelAction!findAllCarModel.action');
				    				}
				    			});  
					         
					        } "/>
					        
					        </td>
							<td >代交寄车:</td>
							<td><input type="text" name="carType" id="car_type" class="easyui-combobox" style="width:140px;" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARTYPE%>',
								valueField:'id',   
								textField:'text',
								mode : 'remote',
								validType:'isSelected[\'#car_type\']',
								invalidMessage : '请从下拉框中选择代交寄车'"></td>
						
						</tr>
					<tr>
						<td>出库日期:</td>
						<td  >
							<input type="text" class="Wdate"
							onclick="WdatePicker({maxDate:'#F{$dp.$D(\'retreat_date2\',{d:-1})}'})"
							name="retreat_date" id="retreat_date" style="width: 100px;" />至
				
							<input type="text" class="Wdate"
							onclick="WdatePicker({minDate:'#F{$dp.$D(\'retreat_date\',{d:1})}'})"
							name="retreat_date2" id="retreat_date2" style="width: 100px;" />
						</td>
						<td>上报日期:</td>
						<td colspan="3">
							 <input type="text" class="Wdate"
						onclick="WdatePicker({maxDate:'#F{$dp.$D(\'sb2\',{d:-1})}'})"
						name="upDate" id="sb" style="width: 125px;" 	 />至
				
					<input type="text" class="Wdate"
						onclick="WdatePicker({minDate:'#F{$dp.$D(\'sb\',{d:1})}'})"
						name="upDate2" id="sb2" style="width:125px;" 	 />
						</td>	
						<td>分析类型:</td>
						<td>
							<input id="cType"  name="ctype" class="easyui-combobox" style="width: 140px;"  
			            	data-options="
						    editable : false,
						    data:[{'id':'type1','text':'客户车型分析'},{'id':'type2','text':'车辆颜色分析'}
						    ,{'id':'type3','text':'客户性别分析'},{'id':'type4','text':'客户学历分析'}
						    ,{'id':'type5','text':'客户收入分析'},{'id':'type6','text':'客户来源分析'}
						    ,{'id':'type7','text':'客户行业分析'},{'id':'type8','text':'客户地区分析'}
						    ,{'id':'type9','text':'客户性质分析'},{'id':'type10','text':'购车用途分析'}
						    ,{'id':'type11','text':'选择理由分析'}],
							valueField:'id',
							textField:'text',
							onLoadSuccess: function(rec){
								$(this).combobox('setValue','type1');
							}
							"/>
						</td>	
						</tr>
				</table>
			</form>
		    </div> 
			<div region="center" style="background:#eee;" border="false" height="100%">
				<div class="easyui-layout" style="width:800px; height:600px;" fit="true"
					border="false">
					<div region="north" style="background:#eee;height:360px;" border="false" title="销售客户分析数据">
						<div id="sellCustomAnalyze_center" style="height:100%;">
							<table id="sellCustomAnalyze"></table>
						</div>
					</div>
					<div  region="center" border="false" align="center" ondblclick="maxImage();">
						 <!--  <span id="poleMapImg" style="width:1200px;height:360px;"></span> -->
						  <span id="cakeMapImg" style="width:500px;height:500px;"></span>
					</div>
					
				</div>
			</div>
		</div>
  </body>
</html>