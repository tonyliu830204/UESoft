<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%@page import="com.syuesoft.util.SystemUser"%>
<%
BasUsers user = (BasUsers) request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>应收款管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/sell_financemanage.js"></script>
    
    <script type="text/javascript">
		
		//收款
		function getAmount(){
		$('#form_should_gathering_manage_south_id').form('clear');
			var value = $('#datagrid_should_gathering_manage_record_id').datagrid('getSelections');
			if(value.length>0){
				if(parseFloat(value[0].account_Arrears)>0){
					$('#form_should_gathering_manage_south_id').form('load',value[0]);
					//获取应收金额  （应收金额 = 应收金额 - 累计预收金额）
					var shouldgetamount = parseFloat(value[0].account_Receivables+"");
					var nowmoney = parseFloat(value[0].sum_Money+"");
					var accountcumulative = parseFloat(value[0].account_Cumulative+"");
					$('#account_Arrears_id').val(shouldgetamount-nowmoney - accountcumulative);
					$('#sstf_id').combobox('select',<%=SystemUser.getUser().getBasStuff().getStfId() %>);
					$('#received_Money_Id').validatebox('validate');
					$('#received_Money_Id').validatebox({required : true});
						$('#form_should_gathering_manage_south_div').dialog({
						modal:true,
						closable : true,
						title : '应收款',
						width : 530,
						height : 320,
						buttons : [{
							iconCls : 'icon-save',
							text : '保存',
							handler : function (){
							var bool = amountJudge();
								if(bool){
									addAmonunt();
								}
							}
				        },{
				        	iconCls : 'icon-undo',
							text : '取消',
							handler : function (){
								$('#form_should_gathering_manage_south_div').dialog('close');
							}
				        }],
				        onLoad : function (){
				        },
				        onClose : function (){
				        	$('#received_Money_Id').validatebox('validate');
				        	$('#received_Money_Id').validatebox({required : false});
					    }
					});
				}else{
					alertMsg('该订单款项已付清！', 'warning');
				}
			}else{
					alertMsg('对不起，请先选择要收款的记录！', 'warning');
			}
		}
		//保存收款记录
		function addAmonunt(){
			var form =  $('#form_should_gathering_manage_south_id').form();
			var formvalue = serializeObject(form);
			if($('#form_should_gathering_manage_south_id').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'gatheringManageAction!saveAmonunt.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
						   		$('#form_should_gathering_manage_south_div').dialog('close');
						   		$('#datagrid_should_gathering_manage_record_id').datagrid('reload');
						   		$('#datagrid_should_gathering_manage_detail_id').datagrid('reload');
						   		$('#form_should_gathering_manage_south_id').form('clear');
						   }
					   }
					});
				}else{
					alertMsg('请正确填写必填项内容！', 'warning');
				}
			}
		//清空
	 	function clearSearchCondition(){
	 		$('#form_should_gathering_manage_id').find('input').val('');
	 		$('#form_should_gathering_manage_id').find('select').val('');
	 		$('#datagrid_should_gathering_manage_record_id').datagrid('unselectAll');
	 		$('#datagrid_should_gathering_manage_record_id').datagrid('load', serializeObject($('#form_should_gathering_manage_id')));
	 		addInitDate($('#reserve_Date'),$('#reserve_Date2'));
	 		
	 	}
	 	//判断本次收款金额是否超过应收金额
	 	var amountJudge = function(){
	 		var shouldamount = parseFloat($('#account_Arrears_id').val()+"");
	 		var nowamount = parseFloat($('#received_Money_Id').val()+"");
	 		if(nowamount>shouldamount){
	 		$.messager.confirm('优亿软件提示', '本次收款金额不能大于应收金额！', function(r){
	 			if(r){
	 				//本次收款默认为应收款
	 				$('#received_Money_Id').val(shouldamount);
	 				}
	 			});
	 			return false;
	 		}
	 		return true;
	 	} 
	 	//条件查询提交
		function doConditionSubmits(formid,datagrid){
			datagrid.datagrid('load',serializeObject(formid));
			addInitDate($('#reserve_Date'),$('#reserve_Date2'));
		}
    	function sellBalancePrint(){
		 	var selected=$('#datagrid_should_gathering_manage_detail_id').datagrid('getSelected')
		  	if(selected!=''&&selected!=null){
		  	    window.open(projectPath+'sell/sell_financemanage/sellBalancePrintReport.jsp?details_Id='+selected.details_Id,'demo',"fullscreen=1")
			}else{
				 $.messager.alert('优亿软件提示','请选择要打印的应收款记录！','warning');
			}
	 }
    </script>
  </head>
  		<body>
  		<div class="easyui-layout" style="width:900px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:32px;" border="false">
		<privilege:enable code="SHOULDSK_SEARCH">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" 
		onclick="doConditionSubmits($('#form_should_gathering_manage_id'),$('#datagrid_should_gathering_manage_record_id'));">查询</a>
		</privilege:enable>
		<privilege:enable code="SHOULDSK_CLEAR">
		<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-cancel" plain="true"  onclick="clearSearchCondition();">清空</a>
		</privilege:enable>
		<privilege:enable code="SHOULDSK_SK">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-store" plain="true" onclick="getAmount();">收款</a>
		</privilege:enable>
		<privilege:enable code="SHOULDSK_SK">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="sellBalancePrint();">打印</a>
		</privilege:enable>
		<span id="addbut"></span>
		<span id="editbut"></span>
		</div>
		<div region="center" style="background:#eee;"  border="false">
		
		 <div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		 <div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:62px;" border="false">
			
			<form id="form_should_gathering_manage_id">
			<fieldset>
				<legend>查询条件</legend>
				<table style="text-align: right">
					<tr>
						<td>客户名称:</td>
						<td> <input style="width:110px" name="xs_Custom_Name" /></td>
						<td>VIN号:</td>
						<td><input style="width:110px" name="xs_Car_Vin_Number" /></td>
						<td>创建日期:</td><td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'reserve_Date2\',{d:-1})}'})"
				name="account_Date" id="reserve_Date" style="width: 90px;"	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  />
					至
				<input type="text" class="Wdate"onclick="WdatePicker({minDate:'#F{$dp.$D(\'reserve_Date\',{d:1})}'})"
				name="account_Date2" id="reserve_Date2" style="width: 90px;" 
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
					</tr>
				</table>
				</fieldset>
			</form>
			</div>
			<div id="datagrid_should_gathering_manage_record_div" region="center" style="background:#eee;" border="false">
				<table id="datagrid_should_gathering_manage_record_id"></table>
			</div>
			<div id="datagrid_should_gathering_manage_detail_div" region="south" title="收款记录" style="background:#eee;height:300px;" border="false" animate="true">
				<table id="datagrid_should_gathering_manage_detail_id"></table>
			</div>
	  	</div>
	  	
	  	<div id="form_should_gathering_manage_south_div" data-options="remote : true">
				<form id="form_should_gathering_manage_south_id" >
				<table style="text-align: right">
					<tr>
						<td>
						<input name="custom_Id" style="display: none;"/>
						<input name="xs_Car_Id" style="display: none;"/>
						<input name="collections_Id" style="display: none;"/>
					</td>
					</tr>
						<tr>
						<td>收款编号：</td>
						<td> <input type="text" style="width:150px;background: #c0d8d8;" name="account_Code" readonly="readonly" /></td>
						
						<td>应收金额：</td>
						<td><input style="width:150px;background: #c0d8d8;" id="account_Arrears_id" name="account_Arrears" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td>本次收款：</td>
						<td><input style="width:150px" id="received_Money_Id" name="received_Money" class="easyui-validatebox" precision="2" data-options="validType:'monery'"
						  onblur="amountJudge();" maxlength="8"/>
						</td>
						<td>累计收款：</td>
						<td><input style="width:150px;background: #c0d8d8;" id="account_Cumulative" name="account_Cumulative" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td>累计预收款：</td>
						<td><input style="width:150px;background: #c0d8d8;" id="sum_Money_id" name="sum_Money" readonly="readonly"/>
						</td>
						<td>客户名称：</td>
						<td> <input type="text" style="width:150px;background: #c0d8d8;" name="xs_Custom_Name" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>车辆编号：</td>
						<td> <input type="text" style="width:150px;background: #c0d8d8;" name="xs_Car_Code" readonly="readonly"/></td>
						<td>VIN号：</td>
						<td><input id="" style="width:150px;background: #c0d8d8;" name="xs_Car_Vin_Number" readonly="readonly"/></td>
					</tr>
						<tr>
						<td>收款人：</td>
						<td>
							<input name="stf_Id" id="sstf_id" style="width:150px" 
							class="easyui-combobox"	data-options="
							disabled : true,
							url : 'basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote',
							onLoadSuccess : function(){
								$(this).combobox('select',<%=SystemUser.getUser().getBasStuff().getStfId() %>);
							}
							"
							/>
						</td>
						<td>付款方式：</td>
						<td>
							<input style="width:150px" id="received_Way_id" name="received_Way"
							class="easyui-combobox"
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.PAYMENTWAY %>',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote'
							"
							/>
						</td>
					</tr>
					<tr>
						<td>发票类型：</td>
						<td>
							<input style="width:150px" id="" name="invoice"
							class="easyui-combobox"
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.INVOICETYPE %>',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote'
							"
							/>
							<input name="#invoice_Name" id="#invoice_Name_id" style="display: none;"/>
						</td>
						<td>票据编号：</td>
						<td><input style="width:150px"  type="text"  name="invoice_Num"/></td>
					</tr>
					<tr>
						<td rowspan="2">备注：</td>
						<td colspan="3"><textarea style="width:380px; height:60px; resize : none" name="remark"></textarea></td>
					</tr>
					<tr>
					</tr>
				</table>
			</form>
			</div>
	  	
	</div>
	</div>
	
  </body>
</html>
