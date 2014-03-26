<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆销售管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/sell_work.js"></script>
    
    <script type="text/javascript">
		//显示销售单信息
		function showSellInfor(){
			$('#form_car_sell_manage_id').form('clear');
			var value = $('#datagrid_car_sell_manage_id').datagrid('getSelected');
			$('#form_dialog_car_sell_manage_div').show();
			$('#form_car_sell_manage_id').form('load',value);
			disableBankInfo();
			$.ajax({
				type : 'POST',
				url : 'carSellManageAction!findContact.action',
				data : value,
			    dataType : 'json',
				success : function(r){
					$('#form_car_sell_manage_id').form('load',r);
					$('#choice1').linkbutton('disable');
					$('#choice2').linkbutton('disable');
					$('#choice3').linkbutton('disable');
					$('#form_dialog_car_sell_manage_div').dialog({
						modal:true,
						closable : true,
						title : '销售单明细',
						width : 850,
						height : 506,
						buttons : [{
				        	iconCls : 'icon-undo',
							text : '关闭',
							handler : function (){
								$('#form_dialog_car_sell_manage_div').dialog('close');
							}
				        }]
					});
				}
		   	});
		}
		//弹出对话框
		function showSellDialog(a){
			$('#xs_Car_Stf_Id').combobox('select','<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>');
			$('#form_dialog_car_sell_manage_div').show();
			disableBankInfo();
			//弹出对话框
			$('#form_dialog_car_sell_manage_div').dialog({
			modal:true,
			closable : true,
			title : '销售单明细',
			width : 850,
			height : 506,
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					saveAllInfo(a);
				}
	        },{
	        	iconCls : 'icon-undo',
				text : '取消',
				handler : function (){
					cancel();
					$('#form_dialog_car_sell_manage_div').dialog('close');
				}
	        }],
	        onLoad : function(){
	        	//$('#custom_Level_id').combobox('select',1);	
	        	//$('#form_dialog_car_sell_manage_div').show();
	        },
	        onClose : function (){
	        	$('#xs_Car_Sel_Type').combobox({required:false});
				$('#xs_Car_Sel_Type').combobox('validate');
	        	//$(this).dialog('destroy');
	        	//$('#form_dialog_car_sell_manage_div').hide();
		    }
		});
		}
		
		//点击新增按钮 开启 增加记录 修改记录 删除记录按钮
		function dbAddbutton(){
			$('#form_car_sell_manage_id').form('clear');
			//取消销售单明细表单只读属性
			$('#form_car_sell_manage_id').find('input').removeAttr("readonly");
			$('#choice1').linkbutton('enable');
			$('#choice2').linkbutton('enable');
			$('#choice3').linkbutton('enable');
			$('#xs_Car_Sel_Type').combobox({required:true});
			$('#xs_Car_Sel_Type').combobox('validate');
			showSellDialog(1);
			$('#xs_Car_Sel_Data_Id').datetimebox('setValue','{now}');
			//
			$('#addcustombutton_id').linkbutton('enable');
			$('#cash_id').linkbutton('disable');
		}
		
		//点击修改按钮
		function dbEditbuttom(){
			var value = $('#datagrid_car_sell_manage_id').datagrid('getSelected');
			if(value){
				if(value.examine=="未审核"){
					if(value.xs_Car_Give_Up=='1'){
						alertMsg('该销售单已放弃购车，不可更改！', 'warning');
						return;
					}
					removereadonly();
					//将数据load给form 表单 
					$('#xs_Car_Sel_Data_Id').datetimebox('setValue','{now}');
					$('#xs_Car_Sel_Type').combobox({required:true});
					$('#xs_Car_Sel_Type').combobox('validate');
					$.ajax({
						type : 'POST',
						url : 'carSellManageAction!findContact.action',
						data : value,
					    dataType : 'json',
						success : function(r){
							$('#form_car_sell_manage_id').form('load',r);
							$('#form_car_sell_manage_id').form('load',value);
							$('#choice1').linkbutton('disable');
							$('#choice2').linkbutton('disable');
							$('#choice3').linkbutton('disable');
							showSellDialog(2);
							$('#cash_id').linkbutton('enable');
							$('#addcustombutton_id').linkbutton('disable');
						}
				   	});
				}else{
					alertMsg('该销售单已审核不可更改！', 'warning');
				}
			}else{
				alertMsg('请先选择要修改的记录！', 'warning');
			}
		}
		
		//删除汇总信息  
		function deleteSellRecord(){
			var value = $('#datagrid_car_sell_manage_id').datagrid('getSelected');
			var index=findSelectRowIndex('datagrid_car_sell_manage_id',value);
			if(value){
				//判断是否审核
				if(value.examine=="未审核"){
					if(value.xs_Car_Give_Up=='1'){
						alertMsg('该销售单已放弃购车，不可删除！', 'warning');
						return;
					}
					if(value.out_Id ==null || value.out_Id=='0' ){
						$.messager.confirm('优亿软件提示','请确认是否要删除编号为【'+value.xs_Car_Sel_Id+'】的销售单记录？',function(b){
							if(b){
								//发送请求
								$.ajax({
									type : 'POST',
									url : 'carSellManageAction!deleteSellInfor.action',
									data : value,
								    dataType : 'json',
									success : function(r){
										if(r.success){
										    $('#datagrid_car_sell_manage_id').datagrid('clearSelections');
											$('#datagrid_car_sell_manage_id').datagrid('reload');
											setSelectRow('datagrid_car_sell_manage_id',index);
										}else{
											alertMsg(r.msg, 'warning');
										}
									}
							   	});
							}
						});
					}else{
						alertMsg('对不起，该销售单已出库不可删除！', 'warning');
					}
				}else{
					alertMsg('对不起，该销售单已审核不可删除！', 'warning');
				}
			}else{
				alertMsg('对不起，请先选择要删除的记录！', 'warning');
			}
		}
	
		//取消
		function cancel(){
			$('#addbut').empty();
			$('#editbut').empty();
			$('#first_Payment').numberbox('setValue','');
			$('#balance_').numberbox('setValue','');
			$('#year_').numberbox('setValue','');
			//添加只读属性
			addreadonly();
		}
		
		//点击保存按钮
		function saveAllInfo(a){
			var customdilag;
			var form =  $('#form_car_sell_manage_id').form();
			var formvalue = serializeObject(form);
			if($('#form_car_sell_manage_id').form('validate')){
				//为新增
				if(a==1){
					if(formvalue.xs_Car_Vin_Number==null||formvalue.xs_Car_Vin_Number==""){
						alertMsg("请选择车辆！","warning");
						return;
					}
					//判断是否是新客户  如果是则先新增客户
					if(formvalue.xs_Custom_Id && formvalue.xs_Custom_Id !=null){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'carSellManageAction!addSellInfor.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
							  $('#form_dialog_car_sell_manage_div').dialog('close');
							  $('#datagrid_car_sell_manage_id').datagrid('reload');
							  cancel();
					   		}else{
					   			alertMsg(r.msg,"warning");
					   		}
						   }
						});
					}else{
					//自动保存客户信息
					$.messager.confirm('提示','无此客户记录，是否添加当前客户信息！',function(r){
					if(r){
							//调用添加客户信息页面
							customdilag = $('<div/>');
							customdilag.dialog({
								title: '添加客户信息',   
							    width: 850,
							    height: 450,
							    cache: false,
							    href: '${pageContext.request.contextPath}/sell/customInfo/customInfoEdit.jsp',
							    modal: true,
							    buttons : [
								{
									iconCls : 'icon-save',
									text : '保存',
									handler : function() {
										if ($('#jBxx').form('validate')) {
											$("#custom_DealId").combobox("enable");
											$("#xsCustomInputdata").removeAttr("disabled");	
											$.ajax( {
													type : 'post',
													dataType : 'json',
													url : 'customInfoAction!saveCustomInfo.action',
													data : $('#jBxx').serialize(),
													success : function(r) {
														if (r.success) {
															customdilag.dialog('close');
															$.ajax( {
																type : 'post',
																dataType : 'json',
																url : 'carSellManageAction!getCustomInforByCustomId.action',
																data : r.obj,
																success : function(value) {
																	$('#form_car_sell_manage_id').form('load',value);
																},
																error : function(r) {
																	if (r.readyState == '0'
																			&& r.status == '0') {
																		alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！','warning');
																	}
																}
															});
														} else {
															alertMsg(r);
														}
													},
													error : function(r) {
														if (r.readyState == '0'
																&& r.status == '0') {
															alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！','warning');
														}
													}
											});
										}
									}
								}, {
									iconCls : 'icon-undo',
									text : '取消',
									handler : function() {
										customdilag.dialog('close');
									}
								} ],
							    onClose : function (){
							    	customdilag.dialog('destroy');
							    }
							});	
							}
						});
					}
				}
				//修改
				if(a==2){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'carSellManageAction!updateSellInfor.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
							   $('#form_dialog_car_sell_manage_div').dialog('close');
							   $('#datagrid_car_sell_manage_id').datagrid('load');
							   cancel();
						   }
					   }
					});
				}
				}//validate 验证
			}
		
		//添加只读属性
		function addreadonly(){
			//为form 表单的input 添加readonly 属性
			$('#form_car_sell_manage_id').find('input').attr("readonly","readonly");
		}
		//删除只读属性
		function removereadonly(){
			 $('#form_car_sell_manage_id').find('input').removeAttr("readonly");
		}
		//PDI检测	
		function  pdicheck(){
			var vals = $('#datagrid_car_sell_manage_id').datagrid('getSelected');
			$('#datagrid_pdi_check_id').datagrid('loadData',{total:0,rows:[]});
   			if(vals){
   				if(vals.xs_Car_Give_Up=='1'){
					alertMsg('该销售单已放弃购车，不可操作！', 'warning');
					return;
				}
				if($('#selltabs').tabs('exists','PDI检测')){
					$('#selltabs').tabs('close','PDI检测');
				}
   				addTab('XSSALESOPERATION60', '#selltabs', 'PDI检测', '${pageContext.request.contextPath}/sell/sell_work/pdi_check.jsp?xs_Car_Id='+vals.xs_Car_Id+'&xs_Car_Sel_Id='+vals.xs_Car_Sel_Id+'&sellcode='+vals.sell_Code+'&vinCode='+vals.xs_Car_Vin_Number);
			}else{
				alertMsg("请先选择检测记录！","warning");
			}
		}
		////导出
		function doExport(){
			exportEsuyUIExcelFile('datagrid_car_sell_manage_div',null,"销售单汇总信息"+getSystemTime());
		}
		
		//预订单弹出窗口
		var dlog;
		function showYdDlog(url,tag){
			var title='预订单信息';
			if(tag!=null&&tag==1){
				title='车辆信息';
			}
			if(tag!=null&&tag==2){
				title='客户信息';
			}
			dlog = $('<div/>');
			dlog.dialog({
				title: title,   
			    width: 850,
			    height: 403,
			    cache: false,
			    href: url,
			    modal: true,
			    onClose : function (){
			    	$(this).dialog('destroy');
			    }
			});
		 }
		 
		//转结算
		function toCash(){
			var data = $('#datagrid_car_sell_manage_id').datagrid('getSelected');
			if(data){
				if(data.examine=="已审核"){
					if(data.invoice_reckoning=="是"){
						alert("此单已转结算！");
						return;
					}
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'carSellManageAction!doCash.action',
					   data: data,
					   success: function(r){
						   if(r.success){
							 alertMsg('转结算成功！', 'info');
							 $('#datagrid_car_sell_manage_id').datagrid('reload');
					   		}else{
					   			alertMsg(r);
					   		}
					   }
					});
				}else{
					alertMsg('此单未审核，不能转结算！', 'warning');
				}
			}else{
				alertMsg('请选择要转结算的记录！', 'warning');
			}
		}
		//添加客户信息
		function addOutCustom(){
			var form =  $('#form_car_sell_manage_id').form();
			var formvalue = serializeObject(form);
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'carSellManageAction!addOutCustom.action',
				   data: formvalue,
				   success: function(r){
					   if(r.success){
						 alertMsg(r.msg, 'info');
				   		}
				   }
				});
			}
		//转售后
		function sellAfter(){
			var value = $('#datagrid_car_sell_manage_id').datagrid('getSelected');
			if(value){
				if(value.examine=="已审核"){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'carSellManageAction!doSellAfter.action',
					   data: value,
					   success: function(r){
						   if(r.success){
							 	$('#datagrid_car_sell_manage_id').datagrid('reload');
					   		}else{
					   			alertMsg(r);	
					   		}
					   }
					});
				}else{
					alertMsg('请选择已审核的记录！', 'warning');
				}
			}else{
				alertMsg('请先选择销售单记录！', 'warning');
			}
		}
		//按键后将  销售价格  = 应收价格  
		function doKeyUp(){
			$('#cost_Price_id').val($('#xs_Car_Sel_Transaction_Money_id').val());
		}
		
		//审核
		function doSellOrderAudit(){
			var value = $('#datagrid_car_sell_manage_id').datagrid('getSelected');
			if(value){
				if(value.xs_Car_Give_Up=='1'){
					alertMsg('该销售单已放弃购车，不可操作！', 'warning');
					return;
				}
				if(value.examine=="已审核"){
					alertMsg('该销售单已审核！', 'warning');
					return;
				}
				$.ajax({
					type : 'POST',
					url : 'carSellManageAction!auditSellInfor.action',
					data : value,
				    dataType : 'json',
					success : function(r){
						if(r.success){
							$('#datagrid_car_sell_manage_id').datagrid('reload');
						}else{
							alertMsg(r.msg, 'warning');
						}
					}
			   	});
			}else{
				alertMsg('对不起，请先选择要审核的记录！', 'warning');
			}
		}
		
		function quitShopCar(){
			var data = $('#datagrid_car_sell_manage_id').datagrid('getSelected');
			if(data){
				if(data.examine=="未审核"){
					if(data.xs_Car_Give_Up=='1'){
						alertMsg('该销售单已放弃购车！', 'warning');
						return;
					}
					$.messager.confirm('提示','确认放弃购车！',function(r){
						if(r){
							$.ajax({
								type : 'POST',
								 url: 'carSellManageAction!doAabandon.action',
								data : data,
							    dataType : 'json',
								success : function(r){
									if(r.success){
										$('#datagrid_car_sell_manage_id').datagrid('reload');
									}else{
										alertMsg(r);
									}
								}
						   	});	
						}
					});
				}else{
					alertMsg('此单已审核，不能放弃购车！', 'warning');
				}
			}else{
				alertMsg('对不起，请先选择要操作的记录！', 'warning');
			}
		}
		function changeMoney(){
			var firstPayment= $('#first_Payment').val();
			var sellMoney=$('#xs_Car_Sel_Transaction_Money_id').val();
			if(!(sellMoney!=null&&sellMoney!='')){
					alert('应付金额不能为空！');
					return;
			}
			if(parseFloat(sellMoney)<parseFloat(firstPayment)){
				alert('付款金额不能大于应付金额！');	
				$('#first_Payment').numberbox('setValue',sellMoney);
				firstPayment= $('#first_Payment').val();
			}
			$('#balance_').numberbox('setValue',parseFloat(sellMoney)-parseFloat(firstPayment));
		} 
		function controlBankInfor(){
			var value1=$('#loan_Bank').combobox('getValue');
			var text1=$('#loan_Bank').combobox('getText');
			if(value1!=text1){
				$('#first_Payment').numberbox('enable');
				$('#balance_').numberbox('enable');
				$('#year_').numberbox('enable');
				$('#first_Payment').numberbox({required:true});
				$('#balance_').numberbox({required:true});
				$('#year_').numberbox({required:true});
				$('#first_Payment').numberbox('validate');
				$('#balance_').numberbox('validate');
				$('#year_').numberbox('validate');
			}else{
				$('#first_Payment').numberbox('setValue','');
				$('#balance_').numberbox('setValue','');
				$('#year_').numberbox('setValue','');
				$('#first_Payment').numberbox('disable');
				$('#balance_').numberbox('disable');
				$('#year_').numberbox('disable');
				$('#first_Payment').numberbox({required:false});
				$('#balance_').numberbox({required:false});
				$('#year_').numberbox({required:false});
				$('#first_Payment').numberbox('validate');
				$('#balance_').numberbox('validate');
				$('#year_').numberbox('validate');
			}
		}
		function disableBankInfo(){
			$('#first_Payment').numberbox('disable');
			$('#balance_').numberbox('disable');
			$('#year_').numberbox('disable');
			$('#first_Payment').numberbox({required:false});
			$('#balance_').numberbox({required:false});
			$('#year_').numberbox({required:false});
			$('#first_Payment').numberbox('validate');
			$('#balance_').numberbox('validate');
			$('#year_').numberbox('validate');
		}
		function sellCarSellListPrint(){
			 var selected=$('#datagrid_car_sell_manage_id').datagrid('getSelected')
		   if(selected!=''&&selected!=null){
		  	    window.open(projectPath+'sell/sell_work/sellCarSellListPrintReport.jsp?xs_Car_Sel_Id='+selected.xs_Car_Sel_Id,'demo',"fullscreen=1")
			}else{
				 $.messager.alert('优亿软件提示','请选择要打印的销售单记录！','warning');
			}
		}
    </script>
  </head>
  		<body>
  		<div id="YdDlog_id"></div>
  		<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
		<a id="butid01" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dbAddbutton();">新增</a>
		<a id="butid02" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteSellRecord();">删除</a>
		<a id="butid03" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dbEditbuttom();">修改</a>
		<a id="butid04" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit($('#form_head_car_sell_manage_id'),$('#datagrid_car_sell_manage_id'),$('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));">查询</a>
		<a id="butid05" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear($('#form_head_car_sell_manage_id'),$('#datagrid_car_sell_manage_id'),$('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));">清空</a>
		<a id="butid11" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="sellCarSellListPrint();">打印</a>
		<a id="butid06" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="doExport();">导出</a>
		<a id="butid07" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="doSellOrderAudit();">审核</a>
		<a id="butid08" href="javascript:void(0);" class="easyui-linkbutton"  data-options="disabled : true" iconCls="icon-tocash" plain="true" onclick="toCash();">转结算</a>
		<a id="butid09" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-formalchange" plain="true" onclick="sellAfter();" >转售后</a>
		<a id="butid10" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-formalchange" plain="true" onclick="quitShopCar();" >放弃购车</a>
		<a href="javascript:void(0);"  iconCls="icon-CARMODEL"class="easyui-linkbutton"  plain="true" onclick="pdicheck();"><span>PDI检测</span></a>
		<span id="addbut"></span>
		<span id="editbut"></span>
		</div>
		<div region="center" style="background:#eee;" border="false">
			
			<div id="selltabs" class="easyui-tabs"fit="true" border="false">
				<div title="车辆销售管理" >
		
			<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:80px;" border="false">
				<form id="form_head_car_sell_manage_id">
				<fieldset style="height : 60px">
					<legend>查询条件</legend>
					<table style="text-align: right">
							<tr>
								<td>销售日期:</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xs_Car_Sel_Data2\',{d:-1})}'})" name="xs_Car_Sel_Data" id="xs_Car_Sel_Data" style="width: 110px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'xs_Car_Sel_Data\',{d:1})}'})" name="xs_Car_Sel_Data2" id="xs_Car_Sel_Data2" style="width: 110px;"/></td>
								<td>客户名称:</td>
								<td><input type="text" name="xs_Custom_Name" /></td>
								
								<td>业务员:</td>
								<td colspan="2">
									<input name="stf_Name" 
									class="easyui-combobox"	data-options="
									url : 'sellUtilAction!findUsers.action',
									valueField:'id',  
									mode : 'remote',
									textField:'name',
									multiple:false  "/>
								</td>
							</tr>
							<tr>
								
								<td>VIN编号:</td>
								<td colspan="2"><input style="width: 110px" type="text" name="xs_Car_Vin_Number" />
									审核情况:
									<input style="width:70px" name="examine"
										class="easyui-combobox"
										data-options="
										url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ADUIT %>',
										multiple:false,
										valueField:'id',  
										textField:'text'
										"
										/>		
								</td>
								<td>OCN码:</td>
								<td><input name="xs_Car_Ocn"/></td>
								<td>备注:</td>
								<td><input  name="remark"/></td>
							</tr>
					</table>
					</fieldset>
				</form>		
				</div>
				<div id="datagrid_car_sell_manage_div" region="center" border="false">
					<table id="datagrid_car_sell_manage_id"></table>
				</div>
			</div>
			</div>
			</div>
		<div id="form_dialog_car_sell_manage_div" >
			<form id="form_car_sell_manage_id">
			<fieldset>
				<legend>车辆信息</legend>
				<table style="text-align: right">
					<tr>
						<td style="display: none;"><input id="carid" name="xs_Car_Id" /></td>
						<td style="display: none;"><input  name="xs_Custom_Id" /></td>
						<td style="display: none;"><input  name="xs_Car_Sel_Id" /></td>
						<td style="display: none;"><input  name="sell_Code" /></td>
						<td>销售日期:</td>
						<td>
							<input type="text" id="xs_Car_Sel_Data_Id" name="xs_Car_Sel_Data"
								class="easyui-datetimebox" style="background:#EBEBE4;" readonly="readonly"
								value="{now}" data-options="
								disabled:true,
								required : true,
								editable : false " />
						</td>
						<td>出库日期:</td>
						<td>
					 		<input type="text" id="outTime" name="outTime"
							class="easyui-datetimebox" style="background:#EBEBE4;" readonly="readonly"
							value="{now}" data-options="
							disabled:true,
							required : true,
							editable : false " />
						</td>
						<td>预订单号:</td>
						<td><input id="reserve_Code_id" name="reserve_Code"  style="width:130px" readonly="readonly"/>
						</td>
						<td style="width: 25px;">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-choice" plain="true" id="choice1"
								onclick="showYdDlog('${pageContext.request.contextPath}/sell/sell_work/choice_reserve_order.jsp');"></a>
						</td>
						<td>内饰色:</td>
						<td>
							<input style="width:110px"  name="xs_Car_InteriorColor"
							class="easyui-combobox" disabled="disabled" 
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_ORNAMENTCOLOR %>',
							multiple:false,
							valueField:'id',  
							textField:'text',
			    			mode:'remote',			    		
							onLoadSuccess : function(){
								$(this).combobox('setValue',$(this).combobox('getText'));
							}
							"
							/>
						</td>
					</tr>
					<tr>
						<td>发动机号:</td>
						<td><input type="text" name="carMotor_Number" disabled="disabled" /></td>
						<td>厂牌名称:</td>
						<td><input type="text" name="xs_Car_LicenseName" disabled="disabled" style="width : 110px;"/></td>
						<td>VIN编码:</td>
						<td>
							<div>
								<input type="text" id="xs_Car_Vin_Number" name="xs_Car_Vin_Number"
									 class="easyui-validatebox"	 style="width:130px;background:#EBEBE4;"/>
							</div>
							<div style="width:110px;height:20px;z-index: 1;position:relative;z-index:1;margin-top:-20px;margin-left:26px;">
							
							</div>
						</td>	 
						<td style="width: 25px;">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-choice" plain="true" id="choice2"
								onclick="showYdDlog('${pageContext.request.contextPath}/sell/sell_work/choice_car_infor.jsp',1);"></a>
						</td>
						<td>出库分类:</td>
						<td>
							<input style="width:110px" id="xs_Car_Sel_Type"  name="xs_Car_Sel_Type"
							class="easyui-combobox"
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.OUTSTORAGE %>',
							required:true,
							multiple:false,
							valueField:'id',  
							textField:'text',
			    			mode:'remote',	
			    			editable:false"
							/>
						</td>
					</tr>
					<tr>
						<td>OCN码:</td>
						<td><input type="text" name="xs_Car_Ocn" disabled="disabled"/></td>
						<td>车辆品牌:</td>
						<td><input type="text" id="car_Brand_id" name="car_Brand_Id"  disabled="disabled" class="easyui-combobox" data-options="
						disabled : true,
						url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote'"
				        />
				        </td>
						<td>车辆型号:</td>
						<td><input type="text" id="car_Model_id"  name="xs_Car_Model_Id"  disabled="disabled" style="width : 130px;" class="easyui-combobox" 
							data-options="
							disabled : true,
							url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
							valueField:'id',   
				    		textField:'text',
				    		mode:'remote'"/>
				        </td>
						<td></td>
						<td>车身颜色:</td>
						<td>
						<input style="width:110px;" id="car_Color" name="car_Color"  disabled="disabled"
							class="easyui-combobox"
							data-options="
							disabled : true,
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR %>',
							multiple:false,
							valueField:'id',  
							textField:'text',
							mode:'remote'"
							/>
							</td>
					</tr>
					<tr>
						<td>车牌照:</td>
						<td><input name="xs_Car_LicensePlate" disabled="disabled"/></td>
						<td>经办人:</td>
						<td>
							<input id="xs_Car_Stf_Id" name="xs_Car_Stf_Id"  disabled="disabled"
							class="easyui-combobox"	data-options="
							url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							valueField:'id',  
							textField:'text',
							multiple:false,
							mode:'remote',
							required:true,
							disabled:true "
							/>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>备注:</td>
						<td colspan="8"><input style="width : 730px;" type="text" name="xs_Car_Sel_Remark"/></td>
					</tr>
				</table>
				</fieldset>
			<fieldset>
				<legend>客户信息</legend>
				<table style="text-align: right">
					<tr>
						<td>客户名称:</td>
						<td><input name="xs_Custom_Name"  class="easyui-validatebox"  disabled="disabled" /></td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-choice" plain="true" id="choice3"
								onclick="showYdDlog('${pageContext.request.contextPath}/sell/sell_work/choice_custom_infor.jsp',2);"></a>
						</td>
						<td>业务员:</td>
						<td>
							<input name="stf_Id"  disabled="disabled" 
							class="easyui-combobox"	data-options="
							url : 'sellUtilAction!findUsers.action',
							valueField:'id',  
							textField:'name',
							multiple:false,
							mode:'remote'"
							/>
						</td>
						
						<td>班组:</td>
						<td><input type="text" disabled="disabled" style="background: #c0d8d8"/></td>
						<td>邮编:</td>
						<td><input type="text" name="xs_Custom_Zipcode" disabled="disabled" /></td>
						
					</tr>
					<tr>
						<td>手机号码:</td>
						<td><input type="text" name="xs_Custom_Telephone" disabled="disabled" /></td>
						<td></td>
						<td>代码证:</td>
						<td><input type="text" name="xs_Custom_Code_Card" disabled="disabled" /></td>
						<td>客户级别:</td>
						<td>
							<input style="width:110px" id="custom_Level_id" disabled="disabled"   name="xs_Custom_Hide_Level"
							class="easyui-combobox"
							data-options="
							url : 'customLevaAction!findAllLeva.action',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote'"
							/>
						</td>
						<td>信息来源:</td>
						<td>
							<input style="width:110px"  name="xs_Custom_Source" disabled="disabled" 
							class="easyui-combobox"
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_SOURCE %>',
							multiple:false,
							valueField:'id',  
							textField:'text'"
							/>
						</td>
					</tr>
					<tr>
						<td>证件号:</td>
						<td><input type="text" name="xs_Custom_Credentials" disabled="disabled" /></td>
						<td></td>
						<td>客户性质:</td>
						<td><input type="text" name="xs_Custom_Property" id="custom_nature" style="width:110px;" disabled="disabled"   
						class="easyui-combobox" 
						data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CUSTOMNATURE%>',
							valueField:'id',   
							textField:'text',
							mode : 'remote'" /></td>	
						<td>地址:</td>
						<td><input type="text" name="xs_Custom_Address" disabled="disabled" /></td>
						<td>联系人:</td>
						<td><input type="text" name="xs_Contacts_Person" disabled="disabled" /></td>
						<td>
						<!-- <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" id="addcustombutton_id" 
							data-options="disabled : true" plain="true" onclick="addOutCustom();">添加客户信息</a> -->
						</td>
					</tr>
					
				</table>
				</fieldset>
			<fieldset>
				<legend>财务信息</legend>
				<table style="text-align: right">
					<tr>
						<td>标准价:</td>
						<td><input type="text" readonly="readonly" id="xs_Model_SalesPrice" class="easyui-numberbox" 
										 data-options="min:0,precision:2"  disabled="disabled" 
										name="xs_Model_SalesPrice"  maxlength="15" /></td>
						<td>销售限价:</td>
						<td><input type="text" readonly="readonly"  name="xs_Model_SalesLimitPrice" class="easyui-numberbox" 
							  disabled="disabled" data-options="validType:'monery'"  maxlength="15"  data-options="min:0,precision:2"/></td>
						<td>销售价格:</td>
						<td><input type="text" readonly="readonly" onkeyup="doKeyUp();" id="xs_Car_Sel_Transaction_Money_id" 
							name="xs_Car_Sel_Transaction_Money" class="easyui-numberbox"  maxlength="15" data-options="min:0,precision:2"/></td>
						<td>预付金额:</td>
						<td><input type="text" readonly="readonly" style="background: #c0d8d8" name="payment_Money"  disabled="disabled"
							 class="easyui-numberbox"  maxlength="15"  data-options="min:0,precision:2"/></td>
					</tr>
					<tr>
						<td>按揭服务费:</td>
						<td><input type="text" name="" class="easyui-numberbox" d data-options="min:0,precision:2"
						  disabled="disabled"  maxlength="15" /></td>
						<td>成本价:</td>
						<td><input type="text" name="xs_Model_CostPrice" readonly="readonly" class="easyui-numberbox"  disabled="disabled" 
							 data-options="min:0,precision:2"  maxlength="15" style="background: #c0d8d8"/></td>
						<td>中介优惠:</td>
						<td><input type="text" name="" class="easyui-numberbox" data-options="validType:'monery'"
							  maxlength="15"   disabled="disabled"/></td>
						<td>付款方式:</td>
						<td>
							<input style="width:110px"  name="payment_Way" disabled="disabled"
							class="easyui-combobox"
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.PAYMENTWAY %>',
							multiple:false,
							valueField:'id',  
							textField:'text'"/>
						</td>
					</tr>
					<tr>
						<td>应收汇总:</td>
						<td><input type="text" id="cost_Price_id" name="cost_Price"  disabled="disabled" class="easyui-numberbox" 
								data-options="validType:'monery'"  maxlength="15"  style="background: #c0d8d8"/></td>
						<td>成本合计:</td>
						<td><input type="text" id="cost_Sum_id" name="cost_Sum"  disabled="disabled" class="easyui-numberbox" 
								data-options="validType:'monery'"  maxlength="15"  style="background: #c0d8d8"/></td>
						<td>按揭银行:</td>
						<td>
							<input id="loan_Bank" name="loan_Bank"	class="easyui-combobox"	data-options="
							url : 'sellUtilAction!findBank.action',
							valueField:'id',  
							textField:'name',
							multiple:false,
							onChange:function(newValue,oldValue){
								controlBankInfor();
							}"/>
						</td>
						<td>首付款:</td>
						<td><input type="text" id="first_Payment" name="first_Payment" class="easyui-numberbox" data-options="validType:'intOrFloat',min:0"
							onkeyup="changeMoney();"  maxlength="12" /></td>
					</tr>
					<tr>
						<td>按揭余额:</td>
						<td><input type="text" id="balance_" name="balance_" maxlength="12" class="easyui-numberbox" data-options="validType:'intOrFloat',min:0"/></td>
						<td>按揭年限:</td>
						<td><input type="text" id="year_" class="easyui-numberbox"  data-options="validType:'intOrFloat',min:0" name="year_"  maxlength="3" /></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="2"></td>
					</tr>
				</table>
				</fieldset>
			</form>
		</div>
	</div>
	</div>	
  </body>
</html>
