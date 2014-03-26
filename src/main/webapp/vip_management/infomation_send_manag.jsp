<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>短信发送管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/infomation_send_manag.js"></script>	
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/infomation_send_manag.css"></link>
  </head>
  <body>
	<div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
		<div data-options="region:'north',collapsible:false" style="overflow: hidden;padding:3px;background:#eee;height:165px;" border="false"> 
			<privilege:enable code="MESSAGESENDQUERY">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit();">查询</a>
			</privilege:enable>
			<privilege:enable code="MESSAGESENDCLEAR">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
			</privilege:enable>
			<privilege:enable code="MESSAGESENDEXPORT">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">导入</a>
			</privilege:enable>
			<privilege:enable code="MESSAGESEND">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-send" plain="true" onclick="sendinformation();">短信发送</a>
			</privilege:enable>
			<form id="form_infomation_send_manage_id">
				<fieldset style="height:115px">
					<legend style="font-weight:bold">查询条件</legend>
					<table style="text-align:right">
						<tr>
							<td>商业险到期:</td>	
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Businsurance_Date2\',{d:-1})}'})" name="car_Businsurance_Date" id="car_Businsurance_Date" style="width:85px"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Businsurance_Date\',{d:1})}'})" name="car_Businsurance_Date2" id="car_Businsurance_Date2" style="width:85px"/></td>
							<td>购车日期:</td>	
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Buy_Date2\',{d:-1})}'})" name="car_Buy_Date" id="car_Buy_Date" style="width:85px"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Buy_Date\',{d:1})}'})" name="car_Buy_Date2" id="car_Buy_Date2" style="width:85px"/></td>
							<td>VIN号:</td>
							<td><input name="car_Vin"/></td>
							<td>车辆品牌:</td>
							<td><input name ="cbrd_Name" class="easyui-combobox"
								data-options="url:'basCarArchivesAction!findCarBrand.action',
											  valueField:'id',  
											  textField:'text',
											  multiple:false,
											  onSelect:function(record){
											     $('#ctype_Name_id').combobox({ 
													      url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarType.action?cbrdId='+record.id, 
												 });  
											  }"/>
							</td>
							<td>车辆型号:</td>
							<td><input style="width:110px" id="ctype_Name_id" name="ctype_Name" class="easyui-combobox"
								data-options="url:'basCarArchivesAction!findCarType.action',
											  valueField:'id',  
											  textField:'text',
											  multiple:false"/>
							</td>
						</tr>
						<tr>
							<td>交强险到期:</td>	
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Basinsurance_Date2\',{d:-1})}'})" name="car_Basinsurance_Date" id="car_Basinsurance_Date" style="width:85px"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Basinsurance_Date\',{d:1})}'})" name="car_Basinsurance_Date2" id="car_Basinsurance_Date2" style="width:85px"/></td>
							<td>年审到期:</td>	
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Exanined_Date2\',{d:-1})}'})" name="car_Exanined_Date" id="car_Exanined_Date" style="width:85px"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Exanined_Date\',{d:1})}'})" name="car_Exanined_Date2" id="car_Exanined_Date2" style="width:85px"/></td>
							<td>所在区域:</td>
							<td><input style="width:110px" name="parea_Name" class="easyui-combobox"
								data-options="url:'VipRecordMessageAction!getBasCustomArea.action',
											  valueField:'id',  
											  textField:'name',
											  multiple:false"/>
							</td>
							<td>客户名称:</td>
							<td><input name="custom_Name"/></td>
							<td>车辆牌照:</td>
							<td><input type="text" name="car_License"/></td>
						</tr>
						<tr>
							<td>保养日期:</td>	
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Last_Maint_Date2\',{d:-1})}'})" name="car_Last_Maint_Date" id="car_Last_Maint_Date" style="width:85px"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Last_Maint_Date\',{d:1})}'})" name="car_Last_Maint_Date2" id="car_Last_Maint_Date2" style="width:85px"/></td>
							<td>年检到期:</td>	
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Exanined_Date2\',{d:-1})}'})" name="car_Exanined_Date" id="car_Exanined_Date" style="width:85px"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Exanined_Date\',{d:1})}'})" name="car_Exanined_Date2" id="car_Exanined_Date2" style="width:85px"/></td>
							<td>发送情况:</td>
							<td>
							<input style="width:110px" name="" class="easyui-combobox"
								data-options="url:'frtOptionsAction!findBaseListData.action\?key=lookState',
											  valueField:'id',  
											  textField:'name',
											  multiple:false"/>
							</td>
							<td>会员卡状态:</td>
							<td>
							<input style="width:110px" name="vip_Status" class="easyui-combobox"
								data-options="url:'frtOptionsAction!findBaseListData.action\?key=hykzt',
											  valueField:'id',  
											  textField:'name',
											  multiple:false"/>
							</td>
<%--							<td>出生月份:</td>--%>
<%--							<td><input style="width:45px" name=""/>至 <input style="width:45px" name=""/></td>--%>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
		<div region="center" style="background:#eee;" border="false">
			<table id="datagrid_infomation_send_manag_id" border="0"></table>
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
								<td rowspan="2">
									<textarea  id="send_Content_Id" name="send_Content"></textarea>
								</td>
								<td><input type="radio" name="radio" checked="checked" onclick="selected2();" style="width: 15px;"/></td><td>立即发送</td>
								<td><input type="radio" name="radio" onclick="selected();" style="width: 15px;"/></td><td>定时发送</td>
								<td><input type="text" disabled="disabled" id="dingshifasong_id" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:138px;" name="other_Send_Date"/></td>
							</tr>
							<tr>
								<td colspan="2">测试号码：</td>
								<td colspan="2"><input style="width: 85px" name="test_Number" id="test_Number_Id"/></td>
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