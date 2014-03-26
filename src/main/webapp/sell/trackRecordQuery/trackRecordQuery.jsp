<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>跟踪记录查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"	src="${pageContext.request.contextPath}/sell/trackRecordQuery/trackRecordQuery.js"></script>
		 <script type="text/javascript">
		    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		    
		  	var sgsm_d1;
			function adddis2()
			{
			 sgsm_d1 = $('<div/>');
			 sgsm_d1.dialog({
				title: '请选择',   
			    width: 650,   
			    height:400,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/allocateManage/addDistributor2.jsp',
			    modal: true,
			    onClose : function (){
			    	$(this).dialog('destroy');
			      }
			   });
			}
			
		    </script>

	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',border:false"
			style="padding: 1px; height: 27px; overflow: hidden; background: #eee;">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="queryReserve();">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true"  id="_print" onclick="_config();">打印</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" id="_export" onclick="_except();">导出</a>
		
		</div>
		<div data-options="region:'center',border:false"
			style="background: #eee;">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',title:'查询条件',border:false"
					style="overflow: hidden; background: #eee; height: 140px;">
					<form id="queryForm" name="carModelQueryForm" method="post"
						fit="true">
						 <fieldset>
						<table>
							<tr>
								
								<td>
							
									客户名称:
								</td>
								<td>
									<input type="text" name="xs_Custom_Name" style="width: 120px">
								</td>
								
								<td>跟踪日期:</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'tracing_Date2\',{d:-1})}'})" name="tracing_Date" id="tracing_Date" style="width: 110px;"/> 至 
										<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'tracing_Date\',{d:1})}'})" name="tracing_Date2" id="tracing_Date2" style="width: 110px;"/></td>
								<td>
								联系人:
								</td>
								<td>
									<input type="text" name="contactsPerson"
										 style="width: 120px"/>
								</td>
								<td>
								业务员:
							</td>
							<td>
							<input type="text" id="pp" name="stf_Id" class="easyui-combobox" 
								 style="background-color:#c0d8d8;width: 140px"
								data-options="url:'${pageContext.request.contextPath}/sellUtilAction!findUsers.action',  
									valueField:'id',   
									textField:'name',
									mode : 'remote',
									validType:'isSelected[\'#pp\']',
									invalidMessage : '请从下拉框中选择经办人'"  />
								</td>
								
						
								</tr>
								<tr>
								<td>成交状态:</td>
								<td><input name="xs_Custom_Deal" style="width: 120px"
									class="easyui-combobox"
									data-options="
									url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_DEALSTATE %>',
									multiple:false,
									editable:false,
									valueField:'id',  
									textField:'text'
									"
									/>
									</td>
									<td>预购日期:</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'zhProjectDate2\',{d:-1})}'})" name="predict_Buy_Date" id="predict_Buy_Date" style="width: 110px;"/> 至 
									<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'zhProjectDate1\',{d:1})}'})" name="predict_Buy_Date2" id="predict_Buy_Date2" style="width: 110px;"/></td>
								
							 
									<td>等级:</td>
									<td><input name="xs_Custom_Hide_Level_Id" style="width: 120px"
									class="easyui-combobox"
									data-options="
									url : 'customLevaAction!findAllLeva.action',
									multiple:false,
									editable:false,
									valueField:'id',  
									textField:'text'
									"
									/>
									</td>
									
									
									<td>试乘试驾车型:</td>
									<td><input type="text" id="car_Model_id" name="car_Model" style="width:140px;"  class="easyui-combobox" 
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
								</tr>
								<tr>
									<td>客户性质:</td>
									<td><input name="custom_Property_Id" style="width: 120px"
									class="easyui-combobox"
									data-options="
									url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CUSTOMNATURE %>',
									multiple:false,
									editable:false,
									valueField:'id',  
									textField:'text'
									"
									/>
									</td>
										<td>
									建档日期:
									</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'zhProjectDate2\',{d:-1})}'})" name="xs_Custom_Inputdata" id="xs_Custom_Inputdata" style="width: 110px;"/> 至 
										<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'zhProjectDate1\',{d:1})}'})" name="xs_Custom_Inputdata2" id="xs_Custom_Inputdata2" style="width: 110px;"/></td>
							 
									<td>电话:</td>
									<td><input name="xs_Custom_Telephone" style="width: 120px"/></td>
									<td>喜爱车型:</td>
								    <td><input type="text" id="car_Model_id" name="cai_Model_Need" style="width:140px;"  class="easyui-combobox" 
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
									</tr>
									<tr>
									<td>成交几率:</td>
									<td><input style="width:120px" name="buy_Probability"
									class="easyui-combobox"
									data-options="
									url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BUYPROBABILITY %>',
									multiple:false,
									valueField:'id',  
									textField:'text'
									"
									/></td>
										<td>
									放弃日期:
									</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'zhProjectDate2\',{d:-1})}'})" name="lose_Date" id="lose_Date" style="width: 110px;"/> 至 
										<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'zhProjectDate1\',{d:1})}'})" name="lose_Date2" id="lose_Date2" style="width: 110px;"/></td>		
										<td colspan="3"><a href="javascript:void(0);" class="easyui-linkbutton"
								style="width: 120px; color: blue;" onclick="queryToday();">今日需跟踪客户</a></td>			
								
								</tr>
								
						</table>
						</fieldset>
					</form>
				</div><blockquote><br>
				</blockquote>
				<div  id="acc" data-options="region:'center',border:false"
					style="background: #eee;">
					 <table id="account" name="account"></table> 
				</div>
				
				<div data-options="region:'south',border:false" title="跟踪记录" style="background: #eee;height: 240px">
					 <table id="carBrand" name="carBrand"></table>
				</div>
		</div>
		</div>
		
	</body>
</html>
