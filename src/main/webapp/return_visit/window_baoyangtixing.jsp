<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'window_baoyangtixing.jsp' starting page</title>
  </head>
<body>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/return_visit/window_baoyangtixing.js">
 </script>
    		<script type="text/javascript">
    		/*保养提醒*/
		     var maintenance='<%=Contstants.VISITGROPNAME.MAINTENREMINDER%>';
		     var carId='<%=request.getParameter("carId")%>';
		     var type='<%=request.getParameter("group_Name")%>';
		</script>
 	<div  class="easyui-layout" fit="true" >
		<div data-options="region:'north',collapsible : false" style="overflow: hidden; background:#eee; height:200px;" border="false">
			<form id="form_baoyangtixing_id">
				<input type="hidden" name="car_Id" id="carId"/>
				<input type="hidden" name="g_Id" id="g_Id"/>
				<input type="hidden" name="group_Name" id="group_Name"/>
				<table  > 
					<tr>
						<td>提醒分类：</td>
						<td><input  name="group_Name_value" id="group_Name_value" readonly="readonly" style="width: 150px;"/></td>
						<td>车辆牌照：</td>
						<td><input name="car_License" id="car_License_id" readonly="readonly" style="width: 150px;"/></td>
						
						<td>提醒日期：</td>
						<td><input name="tx_Return_Visit_Date" id="txDate_id" class="Wdate" name="tx_Return_Visit_Date" onfocus="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd'});"  style="width: 150px;"/></td>						
						<td rowspan="2"><a  class="easy-linkbutton" plain="true" onclick="">维修档案</a></td>
					</tr>
					<tr>
						<td>联系人：</td>
						<td><input name="car_Relation_Person" readonly="readonly" style="width: 150px;"/></td>
						<td>车主固话：</td>
						<td><input name="custom_tel2" readonly="readonly" style="width: 150px;"/></td>
						
						<td>车主手机：</td>
						<td><input name="custom_tel1" readonly="readonly" style="width: 150px;"/></td>
						
					</tr>
					<tr>
						<td>托修人：</td>
						<td><input name="car_Relation_Person"  readonly="readonly" style="width: 150px;"/></td>
						<td>托修人固话：</td>
						<td><input name="prop_Tel"  readonly="readonly" style="width: 150px;"/></td>
						
						<td>托修人手机：</td>
						<td><input name="prop_Phone"  readonly="readonly" style="width: 150px;"/></td>						
					</tr>
					<tr>
						<td>客户名称：</td>
						<td colspan="3"><input style="width : 380px;" name="custom_Name" readonly="readonly"/></td>
						<td>下次跟踪：</td>
						<td><input name="return_Visit_Date" id="return_Visit_Date_id" readonly="readonly"style="width: 150px;" class="Wdate" onfocus="WdatePicker({});" /></td>
						<td rowspan="2"><a  class="easy-linkbutton" plain="true" onclick="">维修档案</a></td>
						
					</tr>
					<tr id="maintenance_id">
						<td>最近保养：</td>
						<td><input name="car_Last_Maint_Date" style="width: 150px;" readonly="readonly" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" /></td>
						<td>保养到期：</td>
						<td><input name="car_Last_Maint_Date" readonly="readonly" style="width: 150px;" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" /></td>
							
					</tr>
					<tr id="annual"  >
							<td >年检到期:</td>
							<td><input name="car_Annual_Date" readonly="readonly" style="width: 150px;"/></td>
							<td >年审到期:</td>
							<td><input name="car_Examined_Date" readonly="readonly" style="width: 150px;"/></td>
					</tr>
					<tr id="fistRemain" >
						<td >首保日期:</td>
						<td><input name="car_Fst_Insurance_Date" readonly="readonly" style="width: 150px;"/></td>
						<td >销售日期:</td>
						<td><input name="car_Buy_Date" readonly="readonly" style="width: 150px;"/></td>
					</tr>
					<tr id="innsure" >
						<td >保险到期:</td>
						<td><input name="car_Businsurance_Date" readonly="readonly" style="width: 150px;"/></td>
						<td>交强险到期</td>
						<td><input name="car_Basinsurance_Date" readonly="readonly" style="width: 150px;"/></td>
					</tr>
					<tr id="birthdayId" >
						<td >客户生日:</td>
						<td><input name="custom_Birthday" readonly="readonly" style="width: 150px;"/></td>
					</tr>
					
					<tr>
						<td>提醒内容：</td>
						<td colspan="3" rowspan="2"><textarea name="visit_Content" id="visit_Content_id" style="width : 375px;height : 70px; resize : none;"></textarea></td>
						<td>提醒结果：</td>
						<td><input type="text"  id="txResault_id" name="tx_Resault"  style="width: 150px;"  class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.REMAINDERESULT.TXRESAULT %>',
							valueField:'id',
							textField:'text',										
							validType:'isSelected[\'#txResault_id\']',
				    		invalidMessage : '请从流失去向'
				    		 "/></td>
					</tr>
					<tr>
						<td></td>
						<td>流失去向：</td>
						<td><input type="text"  id="statusName_id" name="car_lost"  style="width: 150px;" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.CARLOST.CARLOST %>',
							valueField:'id',
							textField:'text',										
							validType:'isSelected[\'#statusName_id\']',
				    		invalidMessage : '请从流失去向'
				    		 "/></td>
						<td><a href="javascript:void(0);" class="easy-linkbutton" plain="true" onclick="openAdvice();">添加维修建议</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" title="历史回访记录" style="background:#eee;">
			    <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
		       <!-- <a href="javascript:void(0);" class="easyui-linkbutton" id="btn_maintanence_update" iconCls="icon-edit" plain="true" onclick="updateMaintenance();">修改</a> -->
				<a href="javascript:void(0);" class="easyui-linkbutton" id="btn_maintanence_delete" iconCls="icon-remove" plain="true" onclick="deleteMaintenance();">删除</a>
				<span id="saveOrCancelBtn"></span>
		       </div>  
			<table id="table_bytx_lishihuifangjilu_id"></table>
		</div>
		<!-- <div region="south" title="历史维修记录" style="height:200px;background:#eee;">
			
		</div> -->
	</div>	
	</body>
</html>
