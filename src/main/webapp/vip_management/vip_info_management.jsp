<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会员档案管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/vip_info_management.js"></script>
  </head>
  <body class="easyui-layout" style="width:1000px;height:750px;" border="false" fit="true">

		<div data-options="region:'north',split:false" border="false" style="height:125px;background:#eee;">
		  <div class="easyui-layout" border="false" fit="true" style="width: 800px; height: 600px;">
				<div region="north" split="false" border="false" style="height: 35px; background : #eee">
						<privilege:enable code="VIPINFOEDIT">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doUpdate($('#Modify_vip_info'),$('#form_Modify_vip_info'),$('#vip_management_center_id'));">修改</a>
						</privilege:enable>
						<privilege:enable code="VIPINFOQUERY">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSearch($('#vip_management_center_id'),$('#form_north_condition_vip_mananement_id'));">查询</a>
						</privilege:enable>
						<privilege:enable code="VIPINFOCLEAR">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doEmpty();">清空</a>
						</privilege:enable>
						<privilege:enable code="VIPINFOEXPORT">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exception();">导出</a>
						</privilege:enable>
						<privilege:enable code="VIPINFOADD">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="doRegister($('#vip_mananement_vip_info_dialog'),$('#form_vip_mananement_vip_info_dialog'),$('#vip_management_center_id'));">会员入会</a>
						</privilege:enable>
						<privilege:enable code="VIPINFOLOSS">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-change" plain="true" onclick="doLossOf($('#LossOf_vip_info'),$('#form_LossOf_vip_info'),$('#vip_management_center_id'));">会员卡挂失</a>
						</privilege:enable>
						<privilege:enable code="VIPLOGOUT">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-change" plain="true" onclick="doLogOut();">会员卡注销</a>
						</privilege:enable>
						<privilege:enable code="VIPFREEZE">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-change" plain="true" onclick="doFreeze();">会员卡冻结</a>
						</privilege:enable>
						<privilege:enable code="VIPUNFREEZE">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-change" plain="true" onclick="doNnfreeze();">会员卡解冻</a>
						</privilege:enable>
				</div>
		<div region="center" split="false" border="false" fit="true" style="background : #eee">
		<form id="form_north_condition_vip_mananement_id">
		<table style="text-align:right" width="1300">
			<tr>
				<td>入会日期:</td>
				<td><input id="join_Time" name="join_Time" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'join_Time2\',{d:-1})}'})" /> 至 </td>
				<td><input id="join_Time2" name="join_Time2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'join_Time\',{d:1})}'})" /></td>
				<td>车辆牌照:</td>
				<td><input type="text" name="car_License"/></td>
				<td>VIN号:</td>
				<td><input type="text" name="car_Vin"/></td>
				<td>会员姓名:</td>	
				<td><input type="text" name="vip_Name"/></td>
				<td>联系电话:</td>
				<td><input type="text" name="vip_Tel"/></td>
				<td>档案状态:</td>
				<td>
				<input style="width:150px" name="car_Flag"
					class="easyui-combobox"
					data-options="
					url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=dazt',
					valueField:'id',   
	    		    textField:'text',
	    		    mode:'remote',
					multiple:false  "
					/>
				</td>
			</tr>
			<tr>
				<td>会员到期:</td>
				<td><input id="end_Time" name="end_Time" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'end_Time2\',{d:-1})}'})"/> 至 </td>
				<td><input id="end_Time2" name="end_Time2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'end_Time\',{d:1})}'})"/></td>
				<td>会员卡号:</td>
				<td><input type="text" name="vip_Number"/></td>
				<td>会员等级:</td>
				<td><input style="width:110px" name="vip_Level_Id"
					class="easyui-combobox"
					data-options="
					url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipLevel.action',
					valueField:'id',  
					textField:'name',
					mode:'remote',
					multiple:false "
					/>
				</td>  
				<td>会员分组:</td>
				<td><input style="width:110px" name="vip_Group_Id"
					class="easyui-combobox"
					data-options="
					url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipGroup.action',
					valueField:'id',  
					textField:'name',
					mode:'remote',
					multiple:false "
					/>
				</td>
				<td>会员卡状态:</td>
				<td>
				  <input type="text" name="vip_Status" class="easyui-combobox" data-options="
					url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.HYKZT.HYKZT %>',   
		    		valueField:'id',   
		    		textField:'text',
		    		mode : 'remote' " style="width: 110px;" />
				</td>
				<td>积分数:</td>
				<td><input style="width:65px" name="vip_Integral"/> 至 <input style="width:65px" name="vip_Integral"/></td>
			</tr> 
			<tr>
				<td>会员生日:</td>
				<td><input id="vip_Birthday" name="vip_Birthday" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'vip_Birthday2\',{d:-1})}'})"/>至</td>
				<td><input id="vip_Birthday2" name="vip_Birthday2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'vip_Birthday\',{d:1})}'})"/></td>
				<td>车辆品牌:</td>
				<td><input type="text" id="cbrd_Name" name="cbrd_Name" class="easyui-combobox" data-options="
				url:'${pageContext.request.contextPath }/frtOptionsAction!findCarBrand.action',
				valueField:'id',   
	    		textField:'text',
	    		mode:'remote',
	    		validType:'isSelected[\'#cbrd_Name\']',
	    		onSelect: function(rec){  
	    			$(this).combobox('textbox').bind('keyup', function (){
	    				if($('#cbrd_Name').combobox('getValue') == '' || $('#cbrd_Name').combobox('getValue') != $('#cbrd_Name').combobox('getText')){
	    					$('#ctype_Name').combobox('reload', '${pageContext.request.contextPath }/frtOptionsAction!findCarType.action');
	    				}
	    			});
		            $('#ctype_Name').combobox('clear');
		            $('#cbrl_Name').combobox('clear');   
		            $('#ctype_Name').combobox('reload', '${pageContext.request.contextPath }/frtOptionsAction!findCarType.action?cbrdId=' + rec.id);  
		        } "/></td>
				<td>车辆型号:</td>
				<td><input type="text" id="ctype_Name" name="ctype_Name" class="easyui-combobox" data-options="
				url:'${pageContext.request.contextPath }/basCarArchivesAction!findCarType.action',
				valueField:'id',   
	    		textField:'text',
	    		mode:'remote',
	    		validType:'isSelected[\'#ctype_Name\']',
	    		onSelect:function(rec){
	    			$(this).combobox('textbox').bind('keyup', function (){
	    				if($('#ctype_Name').combobox('getValue') == '' || $('#ctype_Name').combobox('getValue') != $('#ctype_Name').combobox('getText')){
	    					$('#cbrl_Name').combobox('reload', '${pageContext.request.contextPath }/frtOptionsAction!findCarStyle.action');
	    				}
	    			});  
		            $('#cbrl_Name').combobox('clear');  
		            $('#cbrl_Name').combobox('reload', '${pageContext.request.contextPath }/frtOptionsAction!findCarStyle.action?ctypeId=' + rec.id);  
		        } "/></td>
				<td>车辆款式:</td>
				<td><input type="text" id="cbrl_Name" name="cbrl_Name" class="easyui-combobox" data-options="
				url:'${pageContext.request.contextPath }/frtOptionsAction!findCarStyle.action',
				valueField:'id',   
	    		textField:'text',
	    		mode:'remote',
	    		validType:'isSelected[\'#cbrl_Name\']',
	    		mode:'remote' "/></td>
				<td>会龄:</td>
				<td><input style="width : 100px" name="vip_Age"/>月</td>
			</tr>
		</table>
		</form>
		</div>
	</div>
	</div>
		<div id="vip_management_center_idDiv" region="center" style="background:#eee;" border="false">
			<table id="vip_management_center_id"></table>
		</div>
		<%-- 会员入会 --%>
		<div id="vip_mananement_vip_info_dialog" style="width:650px;background:#eee" class="easyui-dialog" data-options="title:'会员入会',closed:true,modal : true,  buttons:[
		{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				doVipSubmit($('#form_vip_mananement_vip_info_dialog'),'${pageContext.request.contextPath}/VipRecordMessageAction!doAdd.action',$('#vip_mananement_vip_info_dialog'),$('#vip_management_center_id'))
			}
		},{
			text:'取消',
			iconCls:'icon-undo',
			handler:function(){
				$('#vip_mananement_vip_info_dialog').dialog('close');
			}
		}]"> 
		    <form id="form_vip_mananement_vip_info_dialog">
				<fieldset>
					<legend style="font-weight:bold">车辆信息</legend>
					<table style="text-align:right">
						<tr>
							<td>车辆编号:</td>
							<td><input name="car_Id" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>车辆牌照:</td>
							<td><input id="carLicense" name="car_License" readonly="readonly" style="width:110px;background: #c0d8d8;" /></td>
							<td>VIN号:</td>
							<td><input name="car_Vin" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
						</tr>
						<tr>
							<td>发动机号:</td>
							<td><input name="car_Motor_Id" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>车辆品牌:</td>
							<td><input name="cbrd_Name" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>车辆型号:</td>
							<td><input name="ctype_Name" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
						</tr>
						<tr>
							<td>车身颜色:</td>
							<td><input name="color_Name" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>购车日期:</td>
							<td><input id="txtDate" name="car_Buy_Date" type="text" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>领证日期:</td>
							<td><input id="txtDate" name="car_License_Date" type="text" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
						</tr>
						<tr>
							<td>经销商:</td>
							<td><input name="" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>交强险到期:</td>
							<td><input id="txtDate" name="car_Basinsurance_Date" type="text" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>商业险到期:</td>
							<td><input id="txtDate" name="car_Businsurance_Date" type="text" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
						</tr>
						<tr>
							<td>保险公司:</td>
							<td><input readonly="readonly" name="" style="width:110px;background: #c0d8d8;"/></td>
							<td>上次维修日期:</td>
							<td><input id="txtDate" name="car_Last_Repair_Date" type="text" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>上次保养日期:</td>
							<td><input id="txtDate" name="car_Last_Maint_Date" type="text" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend style="font-weight:bold ">客户信息</legend>
					<table style="text-align:right">
						<tr>
							<td>客户编号:</td>
							<td><input name="custom_Id" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>客户名称:</td>
							<td><input name="custom_Name" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>性别:</td>
							<td><input name="custom_Sex" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
						</tr>
						<tr>
						    <td>地址:</td>
							<td><input name="custom_Addr" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>联系电话:</td>
							<td><input name="custom_Tel2" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>固定电话:</td>
							<td><input name="custom_Tel1" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
						</tr>
						<tr>
							<td>客户性质:</td>
							<td><input name="cstg_Name" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>客户类型:</td>
							<td><input name="cst_Name" readonly="readonly" style="width:110px;background: #c0d8d8;"/></td>
							<td>出生年月:</td>
							<td><input id="txtDate" name="custom_Birthday" type="text" style="width:110px;background: #c0d8d8;"/></td>
						</tr>
						<tr>
							<td>爱好:</td>
							<td rowspan="2" colspan="5"><textarea rows="2" cols="5" style="width:460px;height:60px;resize:none" name="vip_Hobby" maxlength="30"></textarea></td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend style="font-weight:bold">会员卡信息</legend>
					<table style="text-align:right">
						<tr>
							<td>会员卡号:</td>
							<td><input style="width:110px;" name="vip_Number" class="easyui-validatebox" data-options="required:true,missingMessage:'会员卡号必填'"/></td>
							<td>会员到期:</td>
							<td><input id="txtDate" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" data-options="required:true"  name="end_Time" style="width:110px;"/></td>
							<td>卡工本费:</td>
							<td><input name="gb_Fee" style="width:110px;" class="easyui-validatebox" validType="monery" invalidMessage="卡工本费格式不正确,请输入正确金额" maxlength="12"/></td>
						</tr><tr>
							<td>会员等级:</td>
							<td><input name="vip_Level_Id"
								class="easyui-combobox"
								data-options="
								required : true,
								url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipLevel.action',
								valueField:'id',  
								textField:'name',
								mode:'remote',
								required:true,missingMessage:'会员等级必填'"
							    style="width:110px;" /></td>
							<td>会员分组:</td>
							<td><input name="vip_Group_Id"
								class="easyui-combobox"
								data-options="
								required : true,
								url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipGroup.action',
								valueField:'id',  
								textField:'name',
								mode:'remote',
								required:true,missingMessage:'会员分组必填'"
							    style="width:110px;"/></td>
							<td>新密码:</td>
							<td>
							    <input type="password" id="vip_Password" name="vip_Password" class="easyui-validatebox" data-options="missingMessage:'密码由6位数字组成而成'" style="width:110px;""/>
							</td>
						</tr>
						<tr>
							<td>卡内余额:</td>
							<td>￥<input id="add_vip_Balance" name="vip_Balance" readonly="readonly" value="0.00" style="background: #c0d8d8;width:110px"/></td>
							<td>当前积分:</td>
							<td><input id="add_vip_Integral" name="vip_Integral" readonly="readonly" value="0" style="background: #c0d8d8;" style="width:110px;"/></td>
							<td>新密码确认:</td>
							<td>
							    <input type="password" id="vip_confirmPassword" name="vip_confirmPassword"  class="easyui-validatebox" data-options='missingMessage:"密码由6位数字组成而成"' validType="same['vip_Password']" invalidMessage="两次输入密码不匹配" style="width:110px;"/>
							</td>
						</tr>
						<tr>
							<td>入会备注:</td>
							<td rowspan="2" colspan="5"><textarea rows="2" cols="5" style="width:470px;height:60px; resize : none" name="memo" maxlength="30"></textarea></td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
		
		<%--修改会员信息 --%>
		<div id="Modify_vip_info" class="easyui-dialog" style="width:570px;height:300px;" data-options="closed:true,title:'编辑会员信息',modal:true,modal : true,buttons:[{
			text:'保存',
			iconCls:'',
			handler:function(){
				doSubmit($('#form_Modify_vip_info'),'${pageContext.request.contextPath}/VipRecordMessageAction!doUpdate.action',$('#Modify_vip_info'));
			}
		},{
			text:'取消',
			iconCls:'',
			handler:function(){
				$('#Modify_vip_info').dialog('close');
			}
		}]">
			<form id="form_Modify_vip_info">
				<fieldset>
					<legend style="font-weight:bold">会员信息</legend>
					<table>
						<tr>
							<td>车辆牌照:</td>
							<td><input name="car_License" readonly="readonly" style="background: #c0d8d8;"/></td>
							<td>车架号:</td>
							<td><input name="car_Vin" readonly="readonly" style="background: #c0d8d8;"/></td>
							<td>会员姓名:</td>
							<td><input name="vip_Name" class="easyui-validatebox" data-options="required:true,missingMessage:'会员姓名必填'" validType="character" invalidMessage="会员姓名格式不正确,请输入会员姓名"/></td>
						</tr>
						<tr>
							<td>会员等级:</td>
							<td colspan="2">
							   <input style="width:160px" name="vip_Level_Id" class="easyui-combobox" 
									data-options="
									url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipLevel.action',
									valueField:'id',  
									textField:'name',
									mode:'remote',
									required:true,missingMessage:'会员等级必填'"/>
							</td>
							<td>会员分组:</td>
							<td colspan="2">
								<input style="width:160px" name="vip_Group_Id" class="easyui-combobox"
									data-options="
									url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipGroup.action',
									valueField:'id',  
									textField:'name',
									mode:'remote',
									required:true,missingMessage:'会员分组必填'"/>
							</td>
						</tr>
						<tr>
							<td>会员生日:</td>
							<td><input id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" name="vip_Birthday" disabled="disabled"/></td>
							<td>联系电话:</td>
							<td><input name="vip_Tel" class="easyui-validatebox" data-options="required:true,missingMessage:'联系电话必填'" validType="mobile" invalidMessage="联系电话格式不正确,请输入正确的联系电话" maxlength="11"/></td>
							<td>入会时间:</td>
							<td><input id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" name="join_Time" disabled="disabled"/></td>
						</tr>
						<tr>
							<td>会员到期:</td>
							<td><input id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" name="end_Time"/></td>
							<td>会员爱好:</td>
							<td colspan="3"><input style="width:280px" name="vip_Hobby" maxlength="30"/></td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend style="font-weight:bold">会员卡信息</legend>
					<table>
						<tr>
							<td>会员卡号:</td>
							<td><input id="Modify_vip_Number" name="vip_Number" readonly="readonly" style="background: #c0d8d8;"/></td>
							<td>累计积分:</td>
							<td><input name="vip_Total_Integral" disabled="disabled"/></td>
							<td>可用积分:</td>
							<td><input name="vip_Integral" disabled="disabled"/></td>
						</tr>
						<tr >
							<td>卡片余额:</td>
							<td><input name="vip_Balance" disabled="disabled"/></td>
						</tr>
					</table>
					<input type="hidden" name="vip_Id">
				</fieldset>
			</form>
		</div>
	    
	    <%--会员挂失 --%>
		<div id="LossOf_vip_info" class="easyui-dialog" style="width:570px;height:300px;" data-options="closed:true,title:'会员挂失',modal:true,modal : true,buttons:[{
			text:'保存',
			iconCls:'',
			handler:function(){
				doLossOfSubmit($('#form_LossOf_vip_info'),'${pageContext.request.contextPath}/VipRecordMessageAction!doLossOfUpdate.action',$('#LossOf_vip_info'));
			}
		},{
			text:'取消',
			iconCls:'',
			handler:function(){
				$('#LossOf_vip_info').dialog('close');
			}
		}]">
			<form id="form_LossOf_vip_info">
				<fieldset>
					<legend style="font-weight:bold">会员信息</legend>
					<table>
						<tr>
							<td>车辆牌照:</td>
							<td><input name="car_License" readonly="readonly"/></td>
							<td>车架号:</td>
							<td><input name="car_Vin" readonly="readonly"/></td>
							<td>会员姓名:</td>
							<td><input name="vip_Name" readonly="readonly"/></td>
						</tr>
						<tr>
							<td>会员等级:</td>
							<td colspan="2">
							   <input style="width:165px" name="vip_Level_Id" class="easyui-combobox" 
									data-options="
									url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipLevel.action',
									mode:'remote',
									valueField:'id',  
									textField:'name'" disabled="disabled"/>
							</td>
							<td>会员分组:</td>
							<td colspan="2">
								<input style="width:165px" name="vip_Group_Id" class="easyui-combobox"
									data-options="
									url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipGroup.action',
									mode:'remote',
									editable : false,
									valueField:'id',  
									textField:'name'" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td>会员生日:</td>
							<td><input id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" name="vip_Birthday" disabled="disabled"/></td>
							<td>联系电话:</td>
							<td><input name="vip_Tel" class="easyui-validatebox" disabled="disabled"/></td>
							<td>入会时间:</td>
							<td><input id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" name="join_Time" disabled="disabled"/></td>
						</tr>
						<tr>
							<td>会员到期:</td>
							<td><input id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" name="end_Time" disabled="disabled"/></td>
							<td>会员爱好:</td>
							<td colspan="3"><input style="width:280px" name="vip_Hobby" maxlength="30" disabled="disabled"/></td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend style="font-weight:bold">会员卡信息</legend>
					<table>
						<tr>
						    <td>原会员卡号:</td>
							<td><input id="vip_OldNumber" name="vip_OldNumber" readonly="readonly"/></td>
							<td>会员卡号:</td>
							<td><input id="LossOf_vip_Number" name="vip_Number" class="easyui-validatebox" data-options="required:true,missingMessage:'会员卡号必填'"/></td>
							<td>卡工本费:</td>
							<td><input name="gb_Fee" style="width:110px;" class="easyui-validatebox" validType="monery" invalidMessage="卡工本费格式不正确,请输入正确金额" maxlength="12"/></td>
						</tr>
						<tr >
							<td>累计积分:</td>
							<td><input name="vip_Total_Integral" disabled="disabled"/></td>
							<td>可用积分:</td>
							<td><input name="vip_Integral" disabled="disabled"/></td>
							<td>卡片余额:</td>
							<td><input name="vip_Balance" disabled="disabled"/></td>
						</tr>
					</table>
					<input type="hidden" name="vip_Id">
				</fieldset>
			</form>
		</div>
  </body>
</html>