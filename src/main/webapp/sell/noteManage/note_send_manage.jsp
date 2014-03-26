<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>短信发送管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/noteManage/note_send_manage.js"></script>	
    <style type="text/css">
    	#send_Content_Id{
    		height : 100px;
    		resize : none;
    		width : 800px
    	}
    
    </style>
    <script type="text/javascript">
    

$(function(){
	$('#infomation_send_manag_dialg_id').dialog({
		closed : true
	});
});
    
  function customArchivesKeyUp(id1,id2){
   var dataLength=document.getElementById(id1).value.length;
   document.getElementById(id2).innerHTML=dataLength;
 }
    </script>
  </head>
   <body>
		  <div id="cc" class="easyui-layout"  fit="true">
				<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:30px;" border="false"> 
						<privilege:enable code="NOTE_SEARCH">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit();">查询</a>
						</privilege:enable>
						<privilege:enable code="NOTE_CLEAR">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
						</privilege:enable>
						<!--<privilege:enable code="NOTE_IMPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
						</privilege:enable>
						--><privilege:enable code="NOTE_SEND">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-send" plain="true" onclick="sendinformation();">短信发送</a>
						</privilege:enable>
		</div><div align="right"> 
		</div><div data-options="region:'center',border:false"
			style="background: #eee;">
			<div class="easyui-layout" data-options="fit:true,border:false">
			
				<div id="swe" data-options="region:'north',title:'查询条件',border:false" style="overflow: hidden; background: #eee;">
				<form id="form_infomation_send_manage_id">
					<div id="from_test_id1" style="height: 50px">
						
						<fieldset>
						<table style="text-align:right;" >
							<tr >
								<td >客户生日:</td>	
								<td ><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xsCustomBirthday2\',{d:-1})}'})" name="xsCustomBirthday" id="xsCustomBirthday" style="width:85px"/> 至 </td>
								<td><input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'xsCustomBirthday\',{d:1})}'})" name="xsCustomBirthday2" id="xsCustomBirthday2" style="width:85px"/></td>
								<td >等级:</td>
									<td><input name="xsCustomHideLevel" style="width: 110px"
									class="easyui-combobox"
									data-options="
									url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CUSTOMPROPERTY %>',
									multiple:false,
									editable:false,
									valueField:'id',  
									textField:'text'
									"
									/>
									</td>
								<td >客户姓名:</td>
								<td ><input name="xsCustomName"/></td>
									<td >成交状态:</td>
								<td><input name="xsCustomDeal" style="width: 110px"
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
									<td>手机:</td>
									<td><input name="xsCustomTelephone" style="width: 130px"/></td>
								
								
								</tr>
								<tr >
							
								<td >销售日期:</td>	
								<td ><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Basinsurance_Date2\',{d:-1})}'})" name="carsellData" id="car_Basinsurance_Date" style="width:85px"/> 至 </td>
								<td><input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Basinsurance_Date\',{d:1})}'})" name="carsellData2" id="car_Basinsurance_Date2" style="width:85px"/></td>
								<td>VIN号:</td>
								<td ><input name="carVinNumber"/></td>
								
							
								<td>车辆牌照:</td>
								<td><input type="text" name="carLicensePlate"/></td>
								<td >
									品牌:
								</td>
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
								<td >车型:</td>
									<td><input type="text" id="car_Model_id" name="carModelId" style="width:130px;"  class="easyui-combobox" 
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
								<tr >
								
								<td>保险到期:</td>	
								<td ><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'end_Time2\',{d:-1})}'})" name="insurerEndDate" id="end_Time" style="width:85px"/> 至 </td>
								<td><input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'end_Time\',{d:1})}'})" name="insurerEndDate2" id="end_Time2" style="width:85px"/></td> 
								<td>所在区域:</td>
								<td><input type="text" name="xsCustomArea" id="custom_AreaId"   
									class="easyui-combobox"
									data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_AREA%>',   
									
									valueField:'id',   
									textField:'text',
									mode : 'remote',
									validType:'isSelected[\'#custom_AreaId\']',
									invalidMessage : '请从下拉框中选择所在区域'"     
				         		 /></td>
								
			
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
											
								
								
								
				         		 <td  colspan="4" ><a href="javascript:void(0);" class="easyui-linkbutton" onclick="sendinformationTwo();"style="width: 150px; color: purple;">来店（电）客户信息</a></td>
				         		 
				         		</tr> 
				         		</table>
								</fieldset>
								
						</div>
						<div id="from_test_id2" style="height: 55px">
							
				<table style="text-align:right;">
				<br/>
								<tr> 
								<td  >来店（电）时间:</td>	
									<td ><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'inData2\',{d:-1})}'})" name="inData" id="inData" style="width:85px"/> 至 </td>
									<td ><input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'inData\',{d:1})}'})" name="inData2" id="inData2" style="width:85px"/></td> 
										<td >
									业务员:
								</td>
								<td>

									<input name="stf_Id" class="easyui-combobox"
										readonly="readonly" style="width: 125px;"
										data-options="url : 'sellUtilAction!findUsers.action',
									valueField:'id',  
									textField:'name',
									mode:'remote',
									multiple:false " />
								</td>
								<td>方式:</td>
							<td>
								<input style="width:125px" id="talk_Way_id" name="talk_Way"
								class="easyui-combobox"
								data-options="
								url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.VISIT %>',
								valueField:'id',  
								textField:'text',
								multiple:false ,
								mode:'remote'"/>
								</td>
								<td >预购车型:</td>
									<td><input type="text"  name="car_Model" style="width:125px;"  class="easyui-combobox" 
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
								<td>登记日期：</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'register_Date2\',{d:-1})}'})" name="register_Date" id="register_Date" style="width: 85px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'register_Date\',{d:1})}'})" name="register_Date2" id="register_Date2" style="width: 85px;"/></td>
								<td >客户姓名:</td>
								<td ><input name="custom_name" style="width: 125px"/></td>
									<td>手机:</td>
									<td><input name="telephone" style="width: 125px"/></td>
									
									<td colspan="2" ><a href="javascript:void(0);" class="easyui-linkbutton" onclick="sendinformationOne();" style="width: 90px;color: purple;">查看客户信息</a></td>
									
								</tr>
								</table>		
						</div>
						</form>
					
				</div>
				
				<div region="center" style="background:#eee;" border="false">
						<table id="note"></table>
			 	</div>
			 	</div>
			 	</div>
			 	
		</div>
		<%--短息发送 --%>
		<div id="infomation_send_manag_dialg_id" class="easyui-dialog" style="width:570px;height:300px;" data-options="closed:true,title:'短信发送',modal:true,fit : true,modal : true">
		 <div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
				<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:140px;" border="false">
		<form id="form_infomation_send_manag_dialg_id">
			<fieldset>
				<legend style="font-weight:bold">发送内容</legend>
					<table>
						<!-- <tr><td><font color="red">一条短信长度不可超出60字符，若超出系统将自动拆解为多条。</font></td><td></td></tr> -->
						
						<tr>
						<td  rowspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
								<textarea  id="send_Content_Id" name="send_Content" maxlength="60" style="width: 500px;resize:none;"
								onkeyup="customArchivesKeyUp('send_Content_Id','noteLength');"></textarea>
							</td>
							<td colspan="5" style="color:blue">注：一条短信最大输入长度为60个字符,已输入(<span id="noteLength" style="color:#ff3318;">00</span>)个字符</td></tr>
							<tr><td style="width: 70px"><input type="radio" name="radio"  checked="checked" onclick="selected2();" style="width: 15px;"/></td><td>立即发送</td>
							<td><input type="radio" name="radio" onclick="selected();" style="width: 15px;"/></td><td>定时发送</td>
							<td><input type="text" disabled="disabled" id="dingshifasong_id" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:138px;" name="other_Send_Date"/></td>
						</tr>
						
						<tr>
							<td colspan="2">测试号码：</td>
							<td colspan="2"><input style="width: 95px" name="test_Number" id="test_Number_Id"/></td>
							<td><a href="#" id="btn" plain="true" iconCls="icon-send" class="easyui-linkbutton" onclick="sendout();">发送</a></td>
						</tr>
					</table>
			</fieldset>
		</form>
	</div>
			<div region="center" style="background:#eee;" title="已选发送列表" border="false">
				<table id="datagrid_infomation_send_manag_dialg_id"></table>
			</div>
	</div>
	</div>
  </body>
  
</html>
