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
    <title>预收款退款</title>
    <script type="text/javascript">
    	//退款记录
		$(function(){
			//初试时间
		 	$('#backMoney_Date').val(getStartDate($('#backMoney_Date')));
			$('#backMoney_Date2').val(getSystemTime());
			$('#datagrid_refundment_manage_id').datagrid({
				url : 'refundmentAction!getRefundmentInfo.action?editTag=false&prefix=<%=Contstants.SELL_BILLSDEPLOY.CARRESERVE%>',
				pagination : true,
				fit : true,
				rownumbers : true,
				singleSelect : true,
				fitColumns : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				idField : 'backCar_Id',
				loadMsg : "数据加载中，请稍后……",
				columns : [[{
					field : 'backCar_Id',
					title : '退车款编号',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'backCar_Code',
					title : '退款单号',
					width : 100,
					sortable : true
				},{
					field : 'backCar_Document',
					title : '退车单号',
					width : 100,
					sortable : true
				},{
					field : 'backCar_Sunmoney',
					title : '应退金额',
					width : 100,
					sortable : true
				},{
					field : 'backCar_Money',
					title : '已退金额',
					width : 100,
					sortable : true
				},{
					field : 'noBackCar_Money',
					title : '未退金额',
					width : 100,
					sortable : true
				},{
					field : 'invoiceId',
					title : '票据类型',
					width : 120,
					sortable : true,
					formatter:function(value,rowData,index){
						return rowData.invoice;
					}
				},{
					field : 'invoice_Num',
					title : '票据编号',
					width : 120,
					sortable : true
				},{
					field : 'backCar_Date',
					title : '经办日期',
					width : 80,
					sortable : true
				},{
					field : 'backCar_Person',
					title : '经办人',
					width : 100,
					sortable : true
				},{
					field : 'backMoney_Person',
					title : '退款人',
					width : 80,
					hidden : true
				},{
					field : 'aduitId',
					title : '审核状态',
					width : 60,
					sortable : true,
					formatter: function(value,rowData,index){
						return "<font style='color:red;'>"+rowData.aduitName+"<font>";
					}
				}
				]],
				onClickRow:function(rowIndex, rowData){
					$('#datagrid_refundment_manage_id_detail_id').datagrid('unselectAll');
					$('#datagrid_refundment_manage_id_detail_id').datagrid('options').url='refundmentAction!getRefundmentInfo.action?editTag=true&backCar_Document='+rowData.backCar_Document+'&prefix=<%=Contstants.SELL_BILLSDEPLOY.CARRESERVE%>';
					$('#datagrid_refundment_manage_id_detail_id').datagrid('reload');
				}
			});
			$('#datagrid_refundment_manage_id_detail_id').datagrid({
				url : 'refundmentAction!getRefundmentInfo.action?editTag=true&prefix=-1',
				pagination : true,
				fit : true,
				rownumbers : true,
				singleSelect : true,
				fitColumns : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				idField : 'backCar_Id',
				loadMsg : "数据加载中，请稍后……",
				columns : [[{
					field : 'backCar_Id',
					title : '退车款编号',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'backCar_Code',
					title : '退款单号',
					width : 100,
					sortable : true
				},{
					field : 'backCar_Document',
					title : '退车单号',
					width : 100,
					sortable : true
				},{
					field : 'backCar_Sunmoney',
					title : '应退金额',
					width : 100,
					sortable : true
				},{
					field : 'backCar_Money',
					title : '退还金额',
					width : 100,
					sortable : true
				},{
					field : 'invoiceId',
					title : '票据类型',
					width : 120,
					sortable : true,
					formatter:function(value,rowData,index){
						return rowData.invoice;
					}
				},{
					field : 'invoice_Num',
					title : '票据编号',
					width : 120,
					sortable : true
				},{
					field : 'backCar_Date',
					title : '经办日期',
					width : 80,
					sortable : true
				},{
					field : 'backCar_Person',
					title : '经办人',
					width : 100,
					sortable : true
				},{
					field : 'backMoney_Person',
					title : '退款人',
					width : 80,
					sortable : true,
					formatter:function(value,rowData,index){
						return rowData.stfName;
					}
				},{
					field : 'backMoney_Date',
					title : '退款日期',
					width : 130,
					sortable : true
				},{
					field : 'aduitId',
					title : '审核状态',
					width : 60,
					sortable : true,
					formatter: function(value,rowData,index){
						return "<font style='color:red;'>"+rowData.aduitName+"<font>";
					}
				}
				]],
				onLoadSuccess:function(){
					$('#datagrid_refundment_manage_id_detail_id').datagrid('options').url='refundmentAction!getRefundmentInfo.action?editTag=true&prefix=<%=Contstants.SELL_BILLSDEPLOY.CARRESERVE%>';
				}
			});
		});
    	//
		function dbRefundment(){
			var data = $('#datagrid_refundment_manage_id').datagrid('getSelected');
			if(data){
				if(data.backCar_Sunmoney==data.backCar_Money){
					alertMsg('已退清金额，无需退款！', 'warning');
					return;
				}
				$.ajax({
					type : 'POST',
				    dataType : 'json',
					url : 'refundmentAction!findNoBackMoney.action',
					data : 'backCar_Document='+data.backCar_Document,
					success : function(r){
							if(r!=null&&r!=''&&parseFloat(r)<=0){
								alertMsg('已退清金额，无需退款！', 'warning');
								return;
							}
							//将数据load给form 表单 
							$('#form_dilog_refundment_manage_id').form('load',data);
							$('#invoiceId').combobox('setValue','');
							$('#invoiceNum').val('');
							$('#backMoneyPerson').combobox('setValue','<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>');
							
							$('#backCarSunMoney').val(r);
							$('#backCarMoney').numberbox('setValue',0);
							$('#backMoneyDate').datetimebox('setValue','{now}');
							$('#form_dilog_refundment_manage_id_div').dialog({
								modal:true,
								closable : true,
								title : '退款',
								width : 350,
								height : 250,
								buttons : [{
									iconCls : 'icon-save',
									text : '保存',
									handler : function (){
											doRefundment();
									}
						        },{
						        	iconCls : 'icon-undo',
									text : '取消',
									handler : function (){
										$('#form_dilog_refundment_manage_id').form('clear');
										$('#form_dilog_refundment_manage_id_div').dialog('close');
									}
						        }]
							});					
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
			   	});
			}else{
				alertMsg('请先选择要退款的记录！', 'warning');
			}
		}
			
		//保存	
		function doRefundment(){
			if($('#form_dilog_refundment_manage_id').form('validate')){
				//发送请求
				$.ajax({
					type : 'POST',
					url : 'refundmentAction!saveRefundmentAmount.action?prefix=<%=Contstants.SELL_BILLSDEPLOY.CARRESERVE%>',
					data : '&'+$('#form_dilog_refundment_manage_id').serialize(),
				    dataType : 'json',
					success : function(r){
						if(r.success){
							$('#form_dilog_refundment_manage_id').form('clear');
							$('#form_dilog_refundment_manage_id_div').dialog('close');
							//alertMsg(r);
							$('#datagrid_refundment_manage_id').datagrid('unselectAll');
							$('#datagrid_refundment_manage_id').datagrid('reload');
						}
					}
			   	});
			
			}else{
				alertMsg('请正确填写必填内容！', 'warning');
			}
		}
			//清空
	 	function clearSearchCondition(){
	 		$('#form_refundment_manage_id').find('input').val('');
	 		$('#form_refundment_manage_id').find('select').val('');
	 		$('#datagrid_refundment_manage_id').datagrid('unselectAll');
	 		$('#datagrid_refundment_manage_id').datagrid('load', serializeObject($('#form_refundment_manage_id')));
	 		addInitDate($('#backMoney_Date'),$('#backMoney_Date2'));
	 		
	 	}
	 	// 查询
		var queryCarReserve = function() {
			$('#datagrid_refundment_manage_id').datagrid('load',serializeObject($('#form_refundment_manage_id')));
			 addInitDate($('#backMoney_Date'),$('#backMoney_Date2'));
		
		}
	 	var controllMoney=function(){
	 		var oldMoney=$('#backCarSunMoney').val();
	 		var newMoney=$('#backCarMoney').val();
	 		if(newMoney!=null&&newMoney!=''){
		 		if(parseFloat(newMoney)>parseFloat(oldMoney)){
		 			alertMsg('退还金额不能大于应退金额！', 'warning');
		 			$('#backCarMoney').numberbox('setValue',oldMoney);
		 		}
	 		}
	 	}
	 	var aduiting=function(){
	 		var datas = $('#datagrid_refundment_manage_id_detail_id').datagrid('getSelected');
				if(datas){
					$.messager.confirm('优亿软件提示', '请确认是否要审核该条记录？', function(r){
						if(r){
							$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url: 'refundmentAction!examine.action',
							   data:'editTag=false&backCar_Id='+datas.backCar_Id,
							   success: function(r){
								 if(r.success){
									 $('#datagrid_refundment_manage_id_detail_id').datagrid('unselectAll');
									$('#datagrid_refundment_manage_id_detail_id').datagrid('options').url=
									'refundmentAction!getRefundmentInfo.action?editTag=true&backCar_Document='+datas.backCar_Document+'&prefix=<%=Contstants.SELL_BILLSDEPLOY.CARRESERVE%>';
									$('#datagrid_refundment_manage_id_detail_id').datagrid('reload');
									$('#datagrid_refundment_manage_id').datagrid('reload');
									
								 }else{
									 alertMsg(r);
								 }
							   },
							   error : function (r){
								   if(r.readyState == '0' && r.status == '0'){
									   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
								   }
							   }
							});
						}
					});
			}else{
				var data = $('#datagrid_refundment_manage_id').datagrid('getSelected');
				if(data){
					$.messager.confirm('优亿软件提示', '请确认是否要审核该条记录？', function(r){
						if(r){
							$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url: 'refundmentAction!examine.action',
							   data:'editTag=true&backCar_Id='+data.backCar_Id,
							   success: function(r){
								 if(r.success){
									 $('#datagrid_refundment_manage_id').datagrid('reload');
									 $('#datagrid_refundment_manage_id_detail_id').datagrid('unselectAll');
									$('#datagrid_refundment_manage_id_detail_id').datagrid('options').url='refundmentAction!getRefundmentInfo.action?editTag=true&backCar_Document='+data.backCar_Document+'&prefix=<%=Contstants.SELL_BILLSDEPLOY.CARRESERVE%>';
									$('#datagrid_refundment_manage_id_detail_id').datagrid('reload');
								 }else{
									 alertMsg(r);
								 }
							   },
							   error : function (r){
								   if(r.readyState == '0' && r.status == '0'){
									   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
								   }
							   }
							});
						}
					});
				}else{
					$.messager.alert('优亿软件提示','对不起，请先选中要审核的记录！','warning',function(){});
				}
			}
	 	}
	 	
	 	function edit(){
			var data = $('#datagrid_refundment_manage_id_detail_id').datagrid('getSelected');
			if(data){
				if(data.backMoney_Person==null||data.backMoney_Person==""){
					alertMsg('此单为退款通知单，不能修改！', 'warning');
					return;
				}
				$.ajax({
					type : 'POST',
				    dataType : 'json',
					url : 'refundmentAction!isRefundment.action',
					data : 'aduitId='+data.aduitId,
					success : function(r){
						if(r.success){
							if(r.obj==true){
								alertMsg('已审核，不能修改！', 'warning');
								return;							
							}else{
								$.ajax({
									type : 'POST',
								    dataType : 'json',
									url : 'refundmentAction!findNoBackMoney.action',
									data : 'backCar_Document='+data.backCar_Document+'&backCar_Money='+data.backCar_Money+'&editTag=true',
									success : function(r){
											if(r!=null&&r!=''&&parseFloat(r)<=0){
												alertMsg('已退清金额，无需退款！', 'warning');
												return;
											}	
											//将数据load给form 表单 
											$('#form_dilog_refundment_manage_id').form('load',data);
											$('#backMoneyPerson').combobox('setValue','<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>');
											
											$('#backCarSunMoney').val(r);
											$('#backCarMoney').numberbox('setValue',0);
											$('#backMoneyDate').datetimebox('setValue','{now}');
											$('#form_dilog_refundment_manage_id_div').dialog({
												modal:true,
												closable : true,
												title : '修改退款',
												width : 350,
												height : 250,
												buttons : [{
													iconCls : 'icon-save',
													text : '保存',
													handler : function (){
															updateRefundment();
													}
										        },{
										        	iconCls : 'icon-undo',
													text : '取消',
													handler : function (){
														$('#form_dilog_refundment_manage_id').form('clear');
														$('#form_dilog_refundment_manage_id_div').dialog('close');
													}
										        }]
											});					
									},
									error : function (r){
									   if(r.readyState == '0' && r.status == '0'){
										   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
									   }
								   }
							   	});
							}
						}else{
							alertMsg(r);			
						}		
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
			   	});
			}else{
				alertMsg('请先选择要退款的记录！', 'warning');
			}
		}
	var updateRefundment=function(){
		if($('#form_dilog_refundment_manage_id').form('validate')){
			//发送请求
			$.ajax({
				type : 'POST',
				url : 'refundmentAction!updateRefundmentAmount.action?prefix=<%=Contstants.SELL_BILLSDEPLOY.CARRESERVE%>',
				data : '&'+$('#form_dilog_refundment_manage_id').serialize(),
			    dataType : 'json',
				success : function(r){
					if(r.success){
						$('#form_dilog_refundment_manage_id').form('clear');
						$('#form_dilog_refundment_manage_id_div').dialog('close');
						alertMsg(r);
						$('#datagrid_refundment_manage_id').datagrid('unselectAll');
						$('#datagrid_refundment_manage_id').datagrid('reload');
					}
				}
		   	});
		
		}else{
			alertMsg('请正确填写必填内容！', 'warning');
		}
	}
    </script>
  </head>
  		<body>
  		<div class="easyui-layout" style="width:900px;height:800px;" border="false" fit="true">
  		
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:30px;" border="false">
			<privilege:enable code="BEFORETK_SEARCH">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryCarReserve();">查询</a>
			</privilege:enable>
			<privilege:enable code="BEFORETK_CLEAR">
			<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton"		iconCls="icon-cancel" plain="true"  onclick="clearSearchCondition();">清空</a>
			</privilege:enable>
			<privilege:enable code="BEFORETK_TK">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-store" plain="true" onclick="dbRefundment();" >退款</a>
			</privilege:enable>
			<privilege:enable code="BEFORETK_EDIT">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="edit();" >修改</a>
			</privilege:enable>
			<privilege:enable code="BEFORETK_EXAMINE">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="aduiting();" >审核</a>
	 		</privilege:enable>
		</div>
		<div region="center" style="background:#eee;"  border="false">
		
		 <div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
			   <form id="form_refundment_manage_id">
			   <table>
			   <tr>
			            <td>
												经办日期：
											</td>
											<td>
												<input type="text" class="Wdate"
													onclick="WdatePicker({maxDate:'#F{$dp.$D(\'backMoney_Date2\',{d:-1})}'})"
													name="backCar_Date" id="backMoney_Date" style="width: 90px;"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  />
												至
											</td>
											<td>
												<input type="text" class="Wdate"
													onclick="WdatePicker({minDate:'#F{$dp.$D(\'backMoney_Date\',{d:1})}'})"
													name="backCar_Date2" id="backMoney_Date2" style="width: 90px;" 
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
											</td>  
												<td> 退车款单号:	</td>
													<td><input id="aa" name="backCar_Code"/></td>
													<td> 票据编号:	</td>
													<td><input id="ss" name="invoice_Num"/></td>
											</tr>
											</table>
			    </form>
		   </div>
			<div id="datagrid_refundment_manage_id_div" region="center" style="background:#eee;" border="false">
				<table id="datagrid_refundment_manage_id"></table>
			</div>
			<div id="datagrid_refundment_manage_id_detail_div" region="south" title="退款记录" style="background:#eee;height:300px;" border="false" animate="true">
				<table id="datagrid_refundment_manage_id_detail_id"></table>
			</div>
	  	</div>
		</div>
	</div>
			<!-- 退款弹出窗口 -->
			<div id="form_dilog_refundment_manage_id_div" >
				<form id="form_dilog_refundment_manage_id" >
				<table style="text-align: center">
					<tr>
						<td>
							<input name="backCar_Id" style="display: none;"/>
						</td>
					</tr>
					<tr>
						<td>退车单号：</td>
						<td>
							<input type="hidden"  id="" name="backCar_Code" readonly="readonly"/>
							<input style="width:200px"  id="" name="backCar_Document" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td>应退金额：</td>
						<td> <input type="text" style="width:200px" id="backCarSunMoney" name="backCar_Sunmoney" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>退还金额：</td>
						<td> <input type="text" style="width:200px" id="backCarMoney" name="backCar_Money" class="easyui-numberbox" 
							data-options="required:true,min:0,precision:2" onkeyup="controllMoney();"/>
							</td>
					</tr>
					<tr>
						<td>退款人：</td>
						<td>
							<input id="backMoneyPerson" name="backMoney_Person"  style="width:200px" 
							class="easyui-combobox"	data-options="
							url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							disabled:true,
							valueField:'id',  
							textField:'text',
							multiple:false ,
							required:true,
							mode:'remote'"
							/>
						</td>
						
					</tr>
					<tr>	
						<td>退款日期：</td>
						<td>
							<input type="text" id="backMoneyDate" name="backMoney_Date"
							class="easyui-datetimebox" style="width: 200px;background:#EBEBE4;" readonly="readonly"
							data-options="
							disabled:true,
							required : true,
							editable : false" />
						</td>
					</tr>
					<tr>
						<td>发票类型：</td>
						<td>
							<input style="width:200px" id="invoiceId" name="invoiceId"
							class="easyui-combobox"
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.INVOICETYPE %>',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote'"
							/>
						</td>
					</tr>
					<tr>
						<td>票据编号：</td>
						<td> <input type="text" style="width:200px" id="invoiceNum" name="invoice_Num" /></td>
					</tr>
				</table>
			</form>
			</div>
	
  </body>
</html>
