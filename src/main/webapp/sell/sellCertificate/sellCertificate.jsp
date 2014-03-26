<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	 <title>合格证管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	var pkey="<%=Contstants.SELLSTATE.BASE_SELLSTATE %>";
	var ckey="<%=Contstants.SELLSTATE.FORSALE %>";
	
		var sgsm_d2=null;
	function addReceipt()
	{
	 sgsm_d2 = $('<div/>');
	 sgsm_d2.dialog({
		title: '承兑汇票选择',   
	    width: 550,   
	    height: 350,
	    cache: false,   
	    href: '${pageContext.request.contextPath}/sell/sellCertificate/addReceipt.jsp',
	    modal: true,
	    onClose : function (){
	    	sgsm_d2.dialog('destroy');
	      }
	   });
	}
	</script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellCertificate/sellCertificate.js"></script>
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
   <privilege:enable code="CERTIFICATE_LZ">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-remove" plain="true" onclick="certificate_()">领证</a> 
	</privilege:enable>
	<privilege:enable code="CERTIFICATE_REMOVE">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-remove" plain="true" onclick="removeCertificate()">删除</a> 
	</privilege:enable>
	<privilege:enable code="CERTIFICATE_EDIT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" onclick="editCertificate();">修改</a> 
	</privilege:enable>
	<privilege:enable code="CERTIFICATE_SEARCH">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="queryCertificate();">查询</a> 
	</privilege:enable>
	<privilege:enable code="CERTIFICATE_CLEAR">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="clearfrom();">清空</a> 
	</privilege:enable>
	<privilege:enable code="CERTIFICATE_PRINT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true" onclick="dopoint('sellCertificate','sellCertificate_div_id');">打印</a> 
	</privilege:enable>
	<privilege:enable code="CERTIFICATE_EXPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" onclick="doexcept('sellCertificate','sellCertificate_div_id');">导出</a> 
	</privilege:enable>
	<!--<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-import" plain="true">导入</a>-->
    </div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:110px;">
		    	<form id="certificateQueryForm" name="receiptQueryForm" method="post"  fit="true" style="margin-top: 10px" >
							<input type="hidden" id="receiptId" name="receiptId"/>
							<table>
								<tr>
								 	
								 	<td>领证日期:</td>
									<td colspan="2"><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'instorehouseDate2\',{d:-1})}'})" name="queryInstorehouseDate" id="instorehouseDate" style="width: 110px;"/> 至 
									<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'instorehouseDate\',{d:1})}'})" name="queryInstorehouseDate2" id="instorehouseDate2" style="width: 110px;"/></td>
									
									<td>销售状态:</td>
											<td><input type="text" name="sellState" id="sellState"  style="width:110px;" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.SELLSTATE.BASE_SELLSTATE%>',   
												editable:false,
												valueField:'id',   
												textField:'text',
												mode : 'remote',
												validType:'isSelected[\'#sellState\']',
												invalidMessage : '请从下拉框中选择销售状态' ,
												onLoadSuccess:function(){
												$.ajax({
												   type: 'post',
												   dataType: 'json',
												   url: 'baseTagAction!getDataByChildDataKey.action',
												   data: 'pdataKey=<%=Contstants.SELLSTATE.BASE_SELLSTATE %>&dataKey=<%=Contstants.SELLSTATE.FORSALE %>',
												   success: function(r){
												   		$('#sellState').combobox('select',r);
												   }
												});
											}" /></td>
											<td>VIN号:</td><td><input name="carVinNumber"/></td>
									<td>
										品牌:
									</td>
										<td><input type="text" id="car_Brand_id" name="carBrand" style="width:135px;" class="easyui-combobox" data-options="
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
										<td><input type="text" id="car_Model_id" name="carModelId" style="width:150px;"  class="easyui-combobox" 
										data-options="
										
										url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
										valueField:'id',   
							    		textField:'text',
							    		mode:'remote',
							    		validType:'isSelected[\'#car_Model_id\']',
							    		invalidMessage : '请从下拉框中选择车辆型号'"/>
								</td>
								</tr>
								 <tr>
								 <td width="70px">开票日期:</td>
									<td colspan="2"><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'queryStartDate2\',{d:-1})}'})" name="queryStartDate" id="queryStartDate" style="width: 110px;"/> 至 
									<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'queryStartDate\',{d:1})}'})" name="queryStartDate2" id="queryStartDate2" style="width: 110px;"/></td>
									<td width="70px">到期日期:</td>
									<td ><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'queryEndDate2\',{d:-1})}'})" name="queryEndDate" id="queryEndDate" style="width: 110px;"/> </td>
							 		 <td >  至 &nbsp;&nbsp;&nbsp;</td>
							        <td ><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'queryEndDate\',{d:1})}'})" name="queryEndDate2" id="queryEndDate2" style="width: 110px;"/></td>
								 	
									<td>票据编号:</td>
									<td colspan="3">
									<input  name="receiptCode"    style="width:312px" type="text"/>
									</td>	
									
								</tr>
							</table>
			 </form>
		    </div>
		    <div id="sellCertificate_div_id"
		    data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="sellCertificate"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
