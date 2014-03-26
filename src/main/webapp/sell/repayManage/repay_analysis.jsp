<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>售后回访分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <style type="text/css">
    	#send_Content_Id{
    		height : 100px;
    		resize : none;
    		width : 800px
    	}
    </style>
    <script type="text/javascript">
    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/repayManage/repay_analysis.js"></script>
  </head>
   <body>
		  <div id="cc" class="easyui-layout"  fit="true">
				<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:120px;" border="false"> 
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doFindbyCondition($('#customer_GzTj_form_id'));">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
						<a id='_print' href="javascript:void(0);" class="easyui-linkbutton"	iconCls="icon-print" plain="true" onclick="_config();">打印</a>
						<a id='_export' href="javascript:void(0);" class="easyui-linkbutton"iconCls="icon-export" plain="true"  onclick="_except();">导出</a>
			
					<form id="customer_GzTj_form_id">
					<fieldset style="height:100px">
						<legend style="font-weight:bold">查询条件</legend>
						<table style="text-align:right;" >
							<tr >
								<td >回访日期:</td>	
								<td ><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'consultActualDate2\',{d:-1})}'})" name="consultActualDate" id="consultActualDate" style="width:85px"/> 至 </td>
								<td><input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'consultActualDate\',{d:1})}'})" name="consultActualDate2" id="consultActualDate2" style="width:85px"/></td>
								<td >品牌:</td>
								<td><input type="text" id="car_Brand_id" name="carBrand" style="width:110px;" class="easyui-combobox" data-options="
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
								
									<td >回访进度:</td>
									<td><input type="text" id="consulTRate" style="width: 110px"
													name="consulTRate" class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/sellCoverAction!getInfo.action',
													
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#consulTRate\']',
													invalidMessage : '请从下拉框中选择回访进度类型'">
									</td>
									<td>部门:</td>
									<td><input id="deptIdF" class="easyui-combobox" name="deptId" data-options="   
								         valueField: 'id',   
								         textField: 'text',  
								         url: 'basPersonnelInformationSetAction!findAllDept.action',
								         mode : 'remote'"/>
									</td>
				         		</tr> 
				         		<tr>
				         		<td >销售日期:</td>	
										<td ><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xsCarSelData2\',{d:-1})}'})" name="xsCarSelData" id="xsCarSelData" style="width:85px"/> 至 </td>
										<td><input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'xsCarSelData\',{d:1})}'})" name="xsCarSelData2" id="xsCarSelData2" style="width:85px"/></td>
				         		
				         		<td >
									业务员:
								</td>
								<td><input name="stfId" class="easyui-combobox"
										readonly="readonly" style="width: 110px;"
										data-options="url : 'basStuffClassAction!findSellOperationPerson.action',
									valueField:'id',  
									textField:'text',
									mode : 'remote' " />
								</td>
								<td>显示样式:</td>
								<td>
								<input id="is3D"  name="is3D" class="easyui-combobox" style="width: 110px;"  
					            	data-options="
								    editable : false,
								    data:[{'id':'false','text':'2D'},{'id':'true','text':'3D'}],
									valueField:'id',
									textField:'text'"/>
								</td>
								<td  colspan="4" ><a href="javascript:void(0);" class="easyui-linkbutton" onclick="sendinformation();"style="width: 150px; color: purple;">跟踪管理信息</a></td>
	         					</tr>
	         				</table>
						</fieldset>
					</form>
				</div>
				<div region="center" style="background:#eee;" border="false">
				
			 <div id="tt" class="easyui-tabs"  border="false"
					fit="true">
						<div title="跟踪满意度统计"  border="false" id="ccid">
						<div class="easyui-layout" border="false" fit="true"
							style="width: 1200px; height: 600px;">
							<div  id="genzong" region="center" border="false">
								<table id ="tb_khmanyidutongji_id"></table>
							</div>
							<div  region="south" border="false" title="" style="height: 450px">
						  		<img id="snapMapImg" style="width:1200px;height:360px;" src="sellCoverAction!getMapbyTime.action"></img>
							</div>	
						</div>
					</div>
					
					<div title="跟踪项目统计"  border="false">
						<div  class="easyui-layout" border="false" fit="true"
							style="width: 800px; height: 600px;">
							
							<div  id="xiangmu" region="center" border="false">
								<table id ="customer_s"></table>
							</div>
						</div>
					</div>
					<div title="回访及时性分析"  border="false">
						<div class="easyui-layout" border="false" fit="true"
							style="width: 800px; height: 600px;">
							<div id="timely" region="center" border="false">
								<table id="timely_analysis"></table>
							</div>
						</div>
					</div>
						<div title="跟踪记录汇总"  border="false">
						<div class="easyui-layout" border="false" fit="true"
							style="width: 800px; height: 600px;">
							
							<div id="gzxm_all" region="center" border="false">
								<table id ="tb_khmanyidutongji_collect_id"></table>
							</div>
						</div>
					</div>
				</div>
				</div>
				</div>	
		
		<%--跟踪管理信息 --%>
		<div id="infomation_send_manag_dialg_id" class="easyui-dialog" style="width:570px;height:300px;" data-options="closed:true,title:'跟踪管理信息',modal:true,fit : true,modal : true">
		 <div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
				<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:120px;" border="false">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit();">查询</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClearTwo();">清空</a>
				<a  href="javascript:void(0);" class="easyui-linkbutton"	iconCls="icon-print" plain="true" onclick="_configTwo();">打印</a>
				<a  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true"  onclick="_exceptTwo();">导出</a>
		<form id="form_infomation_send_manag_dialg_id">
			<fieldset>
				<legend style="font-weight:bold">查询条件</legend>
					<table>
						
						
							<tr >
								<td >回访日期:</td>	
								<td ><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'consultActualDate2\',{d:-1})}'})" name="consultActualDate" id="date1" style="width:85px"/> 至 </td>
								<td><input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'consultActualDate\',{d:1})}'})" name="consultActualDate2" id="date2" style="width:85px"/></td>
								<td >
									客户姓名:
								</td>
								<td >
									<input name="xsCustomName"  type="text" >
								</td>
							<td >车型:</td>
									<td><input type="text" id="car_Model_id" name="carModel" style="width:110px;"  class="easyui-combobox" 
									data-options="
									
									url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
									valueField:'id',   
						    		textField:'text',
						    		mode:'remote',
						    		validType:'isSelected[\'#car_Model_id\']',
						    		invalidMessage : '请从下拉框中选择车辆型号' "/></td>
								
								<td >回访进度:</td>
									<td><input type="text" id="consulTRate" style="width: 110px"
													name="consulTRate" class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/sellCoverAction!getInfo.action',
													
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#consulTRate\']',
													invalidMessage : '请从下拉框中选择回访进度类型'">
									</td>
								<td>部门:</td>
									<td><input id="deptIdF" class="easyui-combobox" name="deptId" data-options="   
											         valueField: 'id',   
											         textField: 'text',  
											         url: 'basPersonnelInformationSetAction!findAllDept.action',
											         editable: false"/>
									</td>
										
								</tr>
								<tr>
								<td >销售日期:</td>	
										<td ><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xsCarSelData2\',{d:-1})}'})" name="xsCarSelData" id="xsCarSelData" style="width:85px"/> 至 </td>
										<td><input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'xsCarSelData\',{d:1})}'})" name="xsCarSelData2" id="xsCarSelData2" style="width:85px"/></td>
											<td>
												满意程度:
											</td>
											<td>

												<input type="text" id="consultDegree" 
													style="width: 110px" name="consultDegree"
													class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CONSULTDEGREE%>',
													
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#consultDegree\']',
													invalidMessage : '请从下拉框中满意程度'">


											</td>
						<td>
												通话:
											</td>
											<td>

												<input type="text" id="consultCallState"
													style="width: 110px" name="consultCallState"
													class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CALLSTATE%>',
													
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#consultCallState\']',
													invalidMessage : '请从下拉框中通话类型'">

											</td>
									<td >
										业务员:
									</td>
									<td>

									<input name="stfId" class="easyui-combobox"
										readonly="readonly" style="width: 110px;"
										data-options="url : 'sellUtilAction!findUsers.action',
									valueField:'id',  
									textField:'name',
									multiple:false " />
								</td>
								 <td  colspan="2" ><a href="javascript:void(0);" class="easyui-linkbutton" onclick="sendinformation2();"style="width: 150px; color: purple;">回访统计信息</a></td>
						
						
						
						</tr>
					</table>
			</fieldset>
		</form>
	</div>
			<div id="info_table" region="center" style="background:#eee;"  border="false">
				<table id="datagrid_infomation_send_manag_dialg_id"></table>
			</div>
	</div>
	</div>
  </body>
  
</html>
