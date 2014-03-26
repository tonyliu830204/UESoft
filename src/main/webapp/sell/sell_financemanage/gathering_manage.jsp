<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%
BasUsers user = (BasUsers) request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>预收款管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/sell_financemanage.js"></script>
    <script type="text/javascript">
    	var personId=<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId()%>;
		//新增
		function getAmount(){
		$('#form_gathering_manage_south_id').form('clear');
			var value = $('#datagrid_gathering_manage_record_id').datagrid('getSelections');
			if(value.length>0){
			//判断该单子是否已经通知退款，如果已经通知退款，不能继续收款
				$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'gatheringManageAction!dowhetheradvice.action',
					   data: value[0],
					   success: function(r){
						   if(r.success){
						   		$('#form_gathering_manage_south_id').form('load',value[0]);
								$('#received_Money_id').validatebox('validate');
								$('#received_Money_id').validatebox({required:true});
								
								$('#gathering_stf_id').combobox('select',personId);
								$('#form_gathering_manage_south_div').dialog({
									modal:true,
									closable : true,
									title : '预收款',
									width : 530,
									height : 320,
									buttons : [{
										iconCls : 'icon-save',
										text : '保存',
										handler : function (){
											addAmonunt();
										}
							        },{
							        	iconCls : 'icon-undo',
										text : '取消',
										handler : function (){
											$('#form_gathering_manage_south_div').dialog('close');
										}
											
							        }],
							        onLoad : function (){
							        },
							        onClose : function (){
							        	$('#received_Money_id').validatebox({required:false	});
										$('#received_Money_id').validatebox('validate');
								    }
								});
						   }else{
						   		alertMsg(r.msg, 'warning');
						   }
					   }
					});
			
			}else{
				alertMsg('对不起，请先选择要收款的记录！', 'warning');
			}
		}
		
		//点击修改按钮
		function dbEditbuttom(){
			
			var value = $('#datagrid_gathering_manage_record_id').datagrid('getSelections');
			if(value.length>0){
					
				//隐藏图片按钮
				$('#imgid').attr("style","display: none;");
				//将数据load给form 表单 
				$('#form_gathering_manage_south_id').form('load',value[0]);
				
				$('#form_gathering_manage_south_div').show();
				$('#form_gathering_manage_south_div').dialog({
					modal:true,
					closable : true,
					title : '编辑预收款',
					width : 530,
					height : 320,
					buttons : [{
						iconCls : 'icon-save',
						text : '保存',
						handler : function (){
							editAmonunt();
						}
			        },{
			        	iconCls : 'icon-undo',
						text : '取消',
						handler : function (){
							$('#form_gathering_manage_south_div').dialog('close');
						}
			        }],
			        onLoad : function (){
			        },
			        onClose : function (){
				    }
				});
				
			}else{
				alertMsg('对不起，请先选择要修改的记录！', 'warning');
			}
		}
		
		//删除预收款记录  
		function deleteYamount(){
			var value = $('#datagrid_gathering_manage_record_id').datagrid('getSelections');
			$.messager.confirm('优亿软件提示','请确认是否要删除编号为【'+value[0].account_Code+'】的收款记录？',function(b){
				if(b){
				if(value.length>0){
					//发送请求
					$.ajax({
						type : 'POST',
						url : 'gatheringManageAction!deleteYamount.action',
						data : value[0],
					    dataType : 'json',
						success : function(r){
							if(r.success){
								$('#datagrid_gathering_manage_record_id').datagrid('reload');
							}
						}
				   	});
				}else{
						alertMsg('对不起，请先选择要删除的记录！', 'warning');
					}
				}
			});
		}
		
		//保存修改后的预收款记录
		function editAmonunt(){
			var form =  $('#form_gathering_manage_south_id').form();
			var formvalue = serializeObject(form);
					if($('#form_gathering_manage_south_id').form('validate')){
							$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'gatheringManageAction!updateYamount.action',
						   data: formvalue,
						   success: function(r){
							   if(r.success){
							   		$('#form_gathering_manage_south_div').dialog('close');
							   		$('#datagrid_gathering_manage_record_id').datagrid('reload');
							   }
						   }
						});
					}else{
						alertMsg('请正确填写必填内容！', 'warning');
					}
				}
				
		//保存预收款记录
		function addAmonunt(){
			var form =  $('#form_gathering_manage_south_id').form();
			var formvalue = serializeObject(form);
					if($('#form_gathering_manage_south_id').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'gatheringManageAction!addYamount.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
						   		$('#form_gathering_manage_south_div').dialog('close');
						   		$('#datagrid_gathering_manage_detail_id').datagrid('reload');
						   		$('#datagrid_gathering_manage_record_id').datagrid('reload');
						   		$('#form_gathering_manage_south_id').form('clear');
						   }
					   }
					});
				}else{
					alertMsg('请正确填写必填内容！', 'warning');
				}
			}		
	
		//导出
		function doExport(){
				exportEsuyUIExcelFile('datagrid_gathering_manage_record_div',null,"预收款记录"+getSystemTime());
		}
		//预定单号查询
		function _query(){
		    $.ajax({
				type : 'post',
				url : 'gatheringManageAction.action!findBillInfor.action',
				data : $('#yudingdanhaoxuanze_dalog_form').serialize(),
				dataType : 'json',
				success : function(r){
		               $('#datagrid_yudingdanhaoxuanze_dalog_id').datagrid('loadData',r);
		        }
		    });
		}
		
		//预定单号选择窗口
		 function showsubscribewindow(){
			$('#yudingdanhaoxuanze_dalog_id').dialog({
				title: '预收款选择',   
			    width: 850,   
			    height: 450,
			    cache: false,
			    modal: true,
			    onClose : function (){
			    	//$('#yudingdanhaoxuanze_dalog_id').dialog('destroy');
			    }
			});
		 }
		 //清空
	 	function clearSearchCondition(){
	 		$('#yudingdanhaoxuanze_dalog_form').form('clear');
	 		queryCarReserve();
	 		
	 	}
		 // 查询
		var queryCarReserve = function(){
			$('#datagrid_gathering_manage_record_id').datagrid('load',serializeObject($('#yudingdanhaoxuanze_dalog_form')));
			//
			addInitDate($('#reserve_Date'),$('#reserve_Date2'));
		}
		function controlInputMoney(){
			var data = $('#datagrid_gathering_manage_record_id').datagrid('getSelected');
			var value=$('#received_Money_id').val();
			if(value!=null&&value!=''){
				if((parseFloat(value)+parseFloat(data.account_Cumulative))>parseFloat(data.payment_Money)){
					alert("预收款金额不能大于预付金额");
					$('#received_Money_id').val(parseFloat(data.payment_Money)-parseFloat(data.account_Cumulative));
				}
			}
		}
		function sellBeforebandBalancePrint(){
		 	var selected=$('#datagrid_gathering_manage_detail_id').datagrid('getSelected')
		  	if(selected!=''&&selected!=null){
		  	    window.open(projectPath+'sell/sell_financemanage/sellBeforebandBalancePrintReport.jsp?details_Id='+selected.details_Id,'demo',"fullscreen=1")
			}else{
				 $.messager.alert('优亿软件提示','请选择要打印的预收款记录！','warning');
			}
	 }
			
    </script>
  </head>
  		<body>
  		<div class="easyui-layout" style="width:900px;height:800px;" border="false" fit="true">
  		
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:30px;" border="false">
		<privilege:enable code="GATHERING_SEARCH">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryCarReserve();">查询</a>
		</privilege:enable>
		<privilege:enable code="GATHERING_CLEAR">
		<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-cancel" plain="true"  onclick="clearSearchCondition();">清空</a>
		</privilege:enable>
		<privilege:enable code="GATHERING_SK">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-store" plain="true" onclick="getAmount();">收款</a>
		</privilege:enable>
			<privilege:enable code="GATHERING_SK">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="sellBeforebandBalancePrint();">打印</a>
		</privilege:enable>
		<span id="addbut"></span>
		<span id="editbut"></span>
		</div>
		<div region="center" style="background:#eee;"  border="false">
		
		 <div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
			   <form id="yudingdanhaoxuanze_dalog_form">
              	 订单编号:<input id="" name="reserve_Code" />
	   	 		 客户名称:<input id="" name="xs_Custom_Name" />
				 订单日期:<input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'reserve_Date2\',{d:-1})}'})"
				name="reserve_Date" id="reserve_Date" style="width: 90px;"	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  />
					至
				<input type="text" class="Wdate"onclick="WdatePicker({minDate:'#F{$dp.$D(\'reserve_Date\',{d:1})}'})"
				name="reserve_Date2" id="reserve_Date2" style="width: 90px;" 
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
											
			    </form>
		   </div>
			<div id="datagrid_gathering_manage_record_div" region="center" style="background:#eee;" border="false">
				<table id="datagrid_gathering_manage_record_id"></table>
			</div>
			
			<div id="datagrid_should_gathering_manage_detail_div" region="south" title="预收款使用记录" style="background:#eee;height:300px;" border="false" animate="true">
				<table id="datagrid_gathering_manage_detail_id"></table>
			</div>
	  	</div>
	  	
	  	<div id="form_gathering_manage_south_div" data-options="remote : true" >
				<form id="form_gathering_manage_south_id" >
				<table style="text-align: right">
					<tr>
						<td>
						<input name="custom_Id"  style="display: none;"/>
						<input name="xs_Car_Id"  style="display: none;"/>
						<input name="reserve_Id" style="display: none;"/>
					</td>
					</tr>
					<tr>
						<td>预订单号：</td>
						<td>
						<input style="width:150px;background: #c0d8d8;"   name="reserve_Code" readonly="readonly"/>
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
							<input id="gathering_stf_id" name="stf_Id"  style="width:150px" 
							class="easyui-combobox"	data-options="
							url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							disabled:true,
							mode:'remote',
							onLoadSuccess : function(){
								$(this).combobox('setValue',$(this).combobox('getText'));
							},
							onSelect : function(record){
								$('#account_Person_id').val(record.text);
							}  "
							/>
							<input name="account_Person" id="account_Person_id" style="display: none;"/>
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
							mode:'remote',
							onLoadSuccess : function(){
								$('#received_Way_Name_id').val($(this).combobox('getText'));
							},
							onSelect : function(record){
								$('#received_Way_Name_id').val(record.text);
							}
							"
							/>
							<input name="received_Way_Name" id="received_Way_Name_id" style="display: none;"/>
						</td>
					</tr>
					<tr>
						<td>收款编号：</td>
						<td> <input type="text" style="width:150px;background: #c0d8d8;" name="account_Code" readonly="readonly" /></td>
						<td>本次收款：</td>
						<td><input style="width:150px" id="received_Money_id" name="received_Money" maxlength="12" class="easyui-validatebox" precision="2" data-options="validType:'monery'"
								onkeyup="controlInputMoney();"  />
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
							mode:'remote',
							onLoadSuccess : function(){
								$('#invoice_Name_id').val($(this).combobox('getText'));
							},
							onSelect : function(record){
								$('#invoice_Name_id').val(record.text);
							}
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
