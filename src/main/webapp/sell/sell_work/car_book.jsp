<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆预定管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/sell_work.js"></script>
    <script type="text/javascript">
    	var staticCarControlPrice=0;
    	function showdilog(a){
    		$('#form_car_book_detail_id_div').show();
    		//弹出对话框
			$('#form_car_book_detail_id_div').dialog({
			modal:true,
			closable : true,
			title : '预订单明细',
			width : 710,
			height : 480,
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					saveInfor(a);
				}
	        },{
	        	iconCls : 'icon-undo',
				text : '取消',
				handler : function (){
					$('#form_car_book_detail_id_div').dialog('close');
				}
	        }],
	        onBeforeClose:function(){
				$("#car_Brand_id").combobox({required:false});
				$("#car_Model_id").combobox({required:false});
				$("#xs_Car_Color_ids").combobox({required:false});
				$("#payment_Money").numberbox('setValue',0);
	        	$("#sellingprice").numberbox('setValue',0);
	        }
		});
    	}
		
		//
		function dbAddbutton(a){
		$('#form_car_book_detail_id').form('clear');
		$('#reserve_Detes').val(getSystemTime());
		$("#image_Dlog").linkbutton('enable');
		$("#car_Brand_id").combobox({required:true});
		$("#car_Model_id").combobox({required:true});
		$("#xs_Car_Color_ids").combobox({required:true});
			showdilog(a);
		}
			
		//
		function dbEditbutton(a){
			var value = $('#datagrid_car_book_id').datagrid('getSelected');
				if(value){
					$.ajax({
						type : 'POST',
					    dataType : 'json',
						url : 'carBookAction!isRefundment.action',
						data : 'examine='+value.examine,
						success : function(r){
							if(r.success){
								if(r.obj==true){
									alertMsg('该预定单已审核，不能修改！', 'warning');
									return;							
								}else{
									$('#form_car_book_detail_id').form('clear');
									$("#image_Dlog").linkbutton('disable');
									$('#form_car_book_detail_id').form('load',value);
									$('#custom_Level_id1').combobox('setValue', value.level);
									showdilog(a);
								}
							}
						}
					});
				}else{
					alertMsg('请先选择要修改的记录！', 'warning');
				}
			}
		//取消
		function cancel(){
			$("#car_Brand_id").combobox({required:false});
			$("#car_Model_id").combobox({required:false});
			$("#xs_Car_Color_ids").combobox({required:false});
			$('#addbut').empty();
			$('#editbut').empty();
			$('#tabs_car_book_id').tabs('select','预订单汇总');
			
		}
		
		//保存
		function saveInfor(a){
		if($('#form_car_book_detail_id').form('validate')){
			var form =  $('#form_car_book_detail_id').form();
			var formvalue = serializeObject(form);
				if(formvalue.xs_Custom_Name!=null && formvalue.xs_Custom_Name!=""){
				if(a==1){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'carBookAction!addCarBookInfor.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
						   		$("#car_Brand_id").combobox({required:false});
						   		$("#car_Model_id").combobox({required:false});
						   		$("#xs_Car_Color_ids").combobox({required:false});						   	
							  	$('#form_car_book_detail_id_div').dialog('close');
							  	$('#datagrid_car_book_id').datagrid('reload');
						   }else{
						   		alertMsg(r.msg, 'warning');
						   }
					   }
					});
					}
					}else{
						alertMsg('请选择客户信息！', 'warning');
					}
				}else{
					//提示 未填写客户信息
					alertMsg('请正确输入必填项信息！', 'warning');
				}
				if(a==2){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'carBookAction!updateCarBookInfor.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
						   	$("#car_Brand_id").combobox({required:false});
						   	$("#car_Model_id").combobox({required:false});
						   	$("#xs_Car_Color_ids").combobox({required:false});
						   		
								$('#datagrid_car_book_id').datagrid('load');
								$('#form_car_book_detail_id_div').dialog('close');
						   }
					   }
					});
				}
			}
				
		//删除汇总信息  
		function deleteRecord(){
			var value = $('#datagrid_car_book_id').datagrid('getSelected');
		    var index=findSelectRowIndex('datagrid_car_book_id',value);
			if(value){
			$.ajax({
					type : 'POST',
				    dataType : 'json',
					url : 'carBookAction!isRefundment.action',
					data : 'examine='+value.examine,
					success : function(r){
						if(r.success){
							if(r.obj==true){
								alertMsg('该预定单已审核，不能删除！', 'warning');
								return;							
							}else{
				
						
						
			$.messager.confirm('优亿软件提示','请确认是否要删除预定编号为【'+value.reserve_Code+'】的记录？',function(b){
				if(b){
				
					//发送请求
					$.ajax({
						type : 'POST',
						url : 'carBookAction!deleteCarBookInfor.action',
						data : value,
					    dataType : 'json',
						success : function(r){
							if(r.success){
								   $('#datagrid_car_book_id').datagrid('clearSelections');
								   $('#datagrid_car_book_id').datagrid('reload');
									setSelectRow('datagrid_car_book_id',index);
							}else{
								alertMsg(r.msg, 'info');
							}
						}
				   	});
				}
				
			});
			}
			}
			}
			});
			}else{
					alertMsg('对不起，请先选择要删除的记录！', 'warning');
				}
		}
		
		//取消客户预定单
		function doAduitCancelBook(){
			var value = $('#datagrid_car_book_id').datagrid('getSelected');
				if(value){
				$.ajax({
					type : 'POST',
				    dataType : 'json',
					url : 'carBookAction!isRefundment.action',
					data : 'examine='+value.examine,
					success : function(r){
						if(r.success){
							if(r.obj==true){
								alertMsg('该预定单已审核，取消该预订单！', 'warning');
								return;							
							}else{
					
					$.messager.confirm('优亿软件提示','请确认是否要取消该预订单？',function(b){
						if(b){
							$.ajax({
							type : 'POST',
							url : 'carBookAction!doAduitCancelBook.action',
							data : value,
						    dataType : 'json',
							success : function(r){
								if(r.success){
										alertMsg(r.msg, 'warning');
									}else{
										$.messager.confirm('优亿软件提示',''+r.msg+'请确认是否通知退款？',function(c){
											if(c){
												$.ajax({
												type : 'POST',
												url : 'carBookAction!doCancelBook.action',
												data : value,
											    dataType : 'json',
												success : function(r){
													//$('#datagrid_car_book_id').datagrid('reload');
													var index=$('#datagrid_car_book_id').datagrid('getRowIndex',value);
											   		value.order_State_Name='取消';
											   		$('#datagrid_car_book_id').datagrid('updateRow',{index:index,row:value});
													}
										   		});
											}
										});
									}
								}
					   		});
					   	}
					});
					}
					}
					}
					});
				}else{
					alertMsg('对不起，请先选择要取消预定客户的记录！', 'warning');
				}
		}
		
		//计算按揭额  车售价 - 首付
		function getAmount(){
			var sellingprice_id = $('#sellingprice_id').val();
			var firstPay_Money_id = $('#firstPay_Money_id').val();
			var amount = Number(sellingprice_id) - Number(firstPay_Money_id);
			$('#loan_Limit_Money_id').val(Number(amount));
		}
		//客户信息选择窗口
		//预订单弹出窗口
			var dg;
			function showYdDlog(url){
				dg = $('<div/>');
				dg.dialog({
					title: '基本信息',   
				    width: 720,
				    height: 440,
				    cache: false,
				    href: url,
				    modal: true,
				    onClose : function (){
				    	$(this).dialog('destroy');
				    }
				});
			 }
			 //审核
			 function carbookaduit(datagrid,url){
			 	var value = datagrid.datagrid('getSelections');
				if(value && value.length>0){
					
						$.ajax({
						type : 'POST',
						url : url,
						data : value[0],
					    dataType : 'json',
						success : function(r){
							if(r.success){
								datagrid.datagrid('reload');
							}
							alertMsg(r.msg, 'info');
						}
				   	});
				}else{
					alertMsg('对不起，请先选择要审核的记录！', 'warning');
				}
			 }
			function controlNumber(){
				var sellPrice=$('#sellingprice').val();
				if(parseFloat(sellPrice)>parseFloat(staticCarControlPrice)){
					alert("销售价不能低于限价！");
					$('#sellingprice').numberbox('setValue',staticCarControlPrice);
				}
				sellPrice=$('#sellingprice').val();
				var payment_Money=$('#payment_Money').val();
				if(payment_Money!=null&&payment_Money!=""&&parseFloat(sellPrice)<parseFloat(payment_Money)){
					alert("预付金额不能大于销售价！");
					$('#payment_Money').numberbox('setValue',sellPrice);
				}
			} 	
			function controlNumberBefore(){
				var sellPrice=$('#sellingprice').val();
				if(sellPrice==null||sellPrice==""){
					alert("请先输入销售价！");
					$('#payment_Money').numberbox('setValue',0);
				}
				var payment_Money=$('#payment_Money').val();
				if(payment_Money!=null&&payment_Money!=""&&parseFloat(sellPrice)<parseFloat(payment_Money)){
					alert("预付金额不能大于销售价！");
					$('#payment_Money').numberbox('setValue',sellPrice);
				}
			}
			function sellReservePrint(){
				 var selected=$('#datagrid_car_book_id').datagrid('getSelected')
			   if(selected!=''&&selected!=null){
			  	    window.open(projectPath+'sell/sell_work/sellReservePrintReport.jsp?reserveId='+selected.reserve_Id,'demo',"fullscreen=1")
				}else{
					 $.messager.alert('优亿软件提示','请选择要打印的预订单记录！','warning');
				}
			}
    </script>
  </head>
  <body>
    	<div id="cc" class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:30px;" border="false">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dbAddbutton(1);">新增</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRecord();">删除</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dbEditbutton(2);">修改</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit($('#form_car_book_record_id'),$('#datagrid_car_book_id'),$('#reserve_Dete'),$('#reserve_Dete2'));">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="sellReservePrint();">打印</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="doexcept('datagrid_car_book_id','datagrid_car_book_div')">导出</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doAduitCancelBook();">取消订单</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear($('#form_car_book_record_id'),$('#datagrid_car_book_id'),$('#reserve_Dete'),$('#reserve_Dete2'));">清空</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="carbookaduit($('#datagrid_car_book_id'),'carBookAction!doAudit.action');">审核</a>
			<span id="addbut"></span>
			<span id="editbut"></span>
		</div>
			<div region="center"  style="background:#eee;" border="false" >
		
						<div id="cc" class="easyui-layout" style="width: 800px; height: 600px;" fit="true">
						<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:110px;" border="false">
						<form id="form_car_book_record_id">
							<fieldset>
							<legend>筛选条件</legend>
							<table>
								<tr>
									<td>预订日期:</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'reserve_Dete2\',{d:-1})}'})" name="reserve_Dete" id="reserve_Dete" style="width: 85px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'reserve_Dete\',{d:1})}'})" name="reserve_Dete2" id="reserve_Dete2" style="width: 85px;"/></td>
									<td>订单状态:</td>
									<td>
										<input style="width:120px" name="order_State"
										class="easyui-combobox"
										data-options="
										url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ORDER_STATE %>',
										multiple:false,
										valueField:'id',  
										textField:'text'
										"
										/>
									</td>
									<td>客户名称:</td>
									<td>
										<input style="width:150px"  id="" name="xs_Custom_Name" class="easyui-combobox"
										data-options="
										requird : true,
										url : 'sellUtilAction!findCustom.action',
										valueField:'id',  
										textField:'name',
										multiple:false,
										mode:'remote'   "/>
									</td>
									<td>预订单号:</td>
									<td><input name="reserve_Code" style="width:120px"/></td>
								</tr>
								<tr>
									<td>预交日期:</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'predict_Take_Date2\',{d:-1})}'})" name="predict_Take_Date" id="predict_Take_Date" style="width: 85px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'predict_Take_Date\',{d:1})}'})" name="predict_Take_Date2" id="predict_Take_Date2" style="width: 85px;"/></td>
								<td>
									品牌:
								</td>
									<td><input type="text" id="carBrand_id" name="xs_Car_Brand" style="width:120px;" class="easyui-combobox" data-options="
									url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
									valueField:'id',   
						    		textField:'text',
						    		mode:'remote',			    		
						    		validType:'isSelected[\'#car_Brand_id\']',
						    		invalidMessage : '请从下拉框中选择车辆品牌',
						    		onSelect: function(rec){  
						    			$(this).combobox('textbox').bind('keyup', function (){
						    				if($('#carBrand_id').combobox('getValue') == '' || $('#carBrand_id').combobox('getValue') != $('#carBrand_id').combobox('getText')){
						    					$('#carModel_id').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
						    				}
						    			});
							            $('#carModel_id').combobox('clear');
							            $('#carModel_id').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
							        } "
							        />
							        </td>
								<td>车型:</td>
									<td><input type="text" id="carModel_id" name="xs_Car_Model_Id" style="width:150px;"  class="easyui-combobox" 
									data-options="
									
									url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
									valueField:'id',   
						    		textField:'text',
						    		mode:'remote',
						    		validType:'isSelected[\'#carModel_id\']',
						    		invalidMessage : '请从下拉框中选择车辆型号',
						    		onSelect:function(rec){
						    			$(this).combobox('textbox').bind('keyup', function (){
						    				if($('#carModel_id').combobox('getValue') == '' || $('#carModel_id').combobox('getValue') != $('#carModel_id').combobox('getText')){
						    					$('#carArchives_add_carCstlId').combobox('reload', 'carModelAction!findAllCarModel.action');
						    				}
						    			});  
							         
							        } "/>
									</td>
									<td>车身颜色:</td>
									<td>
										<input style="width:120px" name="xs_Car_Color"
										class="easyui-combobox"
										data-options="
										url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR %>',
										multiple:false,
										valueField:'id',  
										textField:'text',
										mode:'remote' 
										"
										/>									
									</td>
								</tr>
								<tr>
								<td>电话:</td>
									<td  colspan="2"><input name="xs_Custom_Telephone" style="width:190px"/></td>
									
									<td>级别:</td>
									<td>
										<input style="width:120px" id="custom_Level_id"  name="level"
										class="easyui-combobox"
										data-options="
										url : 'customLevaAction!findAllLeva.action',
										valueField:'id',  
										textField:'text',
										multiple:false ,
										mode:'remote',
										onLoadSuccess : function(){
										$(this).combobox('select',$(this).combobox('getText'));
										}"
										/>
									</td>
									<td>业务员:</td>
									<td>
											<input name="stf_Id" style="width:150px"
											class="easyui-combobox"	data-options="
											url : 'sellUtilAction!findUsers.action',
											valueField:'id',  
											textField:'name',
											multiple:false ,
											mode:'remote'  "
											/>
									</td>
									<td>部门:</td>
									<td><input id="deptIdF" class="easyui-combobox" name="dept_Name" style="width:120px"
							                data-options="   
									         valueField: 'id',   
									         textField: 'text',  
									         url: 'basPersonnelInformationSetAction!findAllDept.action',
									         mode : 'remote'"/>
									</td>
								</tr>
							</table>
							</fieldset>	
						</form>
						</div>
						<div id="datagrid_car_book_div" region="center"  style="background:#eee;" border="false">
							<table id="datagrid_car_book_id"></table>
						</div>
						</div>
		</div>
	</div>
    	
    <!--弹出窗口 -->
    <div id="form_car_book_detail_id_div">
		<form id="form_car_book_detail_id">
			<input type="hidden" name="stf_Id_Person"/>
		<fieldset>
			<legend>预定客户信息</legend>
			<table>
				<tr>
					<td style="display: none"><input name="xs_Custom_Id"/></td>
					<td style="display: none"><input name="xs_Car_Id"/></td>
					<td>客户名称:</td>
					<td>
					<input style="width:150px;background: #c0d8d8;"  id="" name="xs_Custom_Name" readonly="readonly"/>
					</td>
					<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-choice" plain="true" id="image_Dlog"
								onclick="showYdDlog('${pageContext.request.contextPath}/sell/sell_work/ydchoice_custom_infor.jsp');" ></a>
					
					<!--<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" id="image_Dlog"  onclick="showYdDlog('${pageContext.request.contextPath}/sell/sell_work/ydchoice_custom_infor.jsp');" />-->
					</td>
					<td>手机号码:</td>
					<td><input name="xs_Custom_Telephone" readonly="readonly" style="width:120px;background: #c0d8d8;"/></td>
					<td>潜在级别:</td>
					<td>
						<input style="width:110px" id="custom_Level_id1"  name="xs_Custom_Hide_Level"
						class="easyui-combobox"
						data-options="
						url : 'customLevaAction!findAllLeva.action',
						valueField:'id',  
						textField:'text',
						multiple:false ,
						mode:'remote',
						onLoadSuccess : function(){
						$(this).combobox('select',$(this).combobox('getText'));
						}"
						/>
					</td>
				</tr>
				<tr>
					<td>业务员:</td>
					<td>
						<input name="stf_Id" style="width:150px"
							class="easyui-combobox"	data-options="
							disabled : true,
							url : 'sellUtilAction!findStfName.action',
							valueField:'id',  
							textField:'name',
							multiple:false ,
							onLoadSuccess : function(){
								$(this).combobox('select',$(this).combobox('getText'));
							}
							"
							/>
						</td>
						<td></td>
					<td>客户要求:</td>
					<td colspan="3"><input name="custom_Opinion" style="width:300px"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>预定车辆信息</legend>
			<table>
			<tr>
				<td>预订品牌:</td>
						<td><input type="text" id="car_Brand_id"  name="xs_Car_Brand" style="width:150px" class="easyui-combobox" data-options="
						url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
						required : true,
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote',			    		
			    		onSelect: function(rec){ 
			    			 $('#xs_Car_Brand_id').val(rec.id);
			    			$(this).combobox('textbox').bind('keyup', function (){
			    				if($('#car_Brand_id').combobox('getValue') == '' || $('#car_Brand_id').combobox('getValue') != $('#car_Brand_id').combobox('getText')){
			    					$('#car_Model_id').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
			    				}
			    			});
				            $('#car_Model_id').combobox('clear');
				            $('#car_Model_id').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
				        	},onLoadSuccess : function(){
							$(this).combobox('select',$(this).combobox('getText'));
							}  "
				        />
				        </td>
				         <!--<td style="width: 25px; display: none" ><img src="js/easyui/themes/icons/choice.png" onclick="showYdDlog('${pageContext.request.contextPath}/sell/sell_work/ydchoice_car_infor.jsp');"/></td>
						--><td>车辆型号:</td>
						<td><input type="text" id="car_Model_id"  name="xs_Car_Model_Id" style="width:150px;"  class="easyui-combobox" 
						data-options="
						required : true,
						url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote',
			    		onSelect:function(rec){
				    		$.ajax({
								type : 'POST',
							    dataType : 'json',
								url : 'carBookAction!findCarTypeSellPriceOrControlPrice.action',
								data : 'flag=true&carModerId='+rec.id, 
								success : function(r){
									if(r.success){
										staticCarControlPrice=r.obj;
										$.ajax({
											type : 'POST',
										    dataType : 'json',
											url : 'carBookAction!findCarTypeSellPriceOrControlPrice.action',
											data : 'flag=false&carModerId='+rec.id, 
											success : function(rs){
												if(rs.success){
													$('#sellingprice').numberbox('setValue',rs.obj);
													$('#sellingprice').numberbox('validate');
												}else{
													alertMsg(rs);
												}
											}
									   	});
									}else{
										alertMsg(r);
									}
								}
							});
				        }"/>
						</td>
				
					<td>车身颜色:</td>
					<td>
						<input style="width:110px" name="xs_Car_Color" id="xs_Car_Color_ids"
						class="easyui-combobox"
						data-options="
						url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR %>',
						required : true,
						multiple:false,
						valueField:'id',  
						textField:'text',
						mode:'remote',
						onLoadSuccess : function(){
							$(this).combobox('select',$(this).combobox('getText'));
							}
							"
							/>
							<input name="xs_Car_Color_Id" id="xs_Car_Color_id" style="display: none;"/>
						</td>
					
				</tr>
				<tr>
					<td>内饰:</td>
					<td>
						<input style="width:150px" name="xs_Car_Trim" id="car_Trim_id"
						class="easyui-combobox"
						data-options="
						url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_ORNAMENTCOLOR %>',
						multiple:false,
						valueField:'id',  
						textField:'text',
						mode:'remote',
						onLoadSuccess : function(){
							$(this).combobox('select',$(this).combobox('getText'));
							}
							"
							/>
						</td>
					<td>排量:</td>
					<td>
						<input style="width:150px" name="car_Output_Volume"
						class="easyui-combobox"
						data-options="
						url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.OUTPUTOLUME %>',
						multiple:false,
						valueField:'id',  
						textField:'text',
						mode:'remote',
						onLoadSuccess : function(){
							$(this).combobox('select',$(this).combobox('getText'));
							}
							"
							/>
						</td>
					<td>限乘人数:</td>
					<td><input name="limit_Load_Num"/></td>
				</tr>
				<tr>
					<td style="display: none">车辆售价:</td>
					<td style="display: none"><input name="xs_Car_Price" id="sellingprice_id"/></td>
					 <td style="display: none"></td>
					<td>VIN编号:</td>
					<td><input name="xs_Car_Vin_Number" style="width:150px;background: #c0d8d8;" readonly="readonly"/></td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset>
			<legend>其他信息</legend>
			<table>
				<tr>
					<td>预订单号:</td>
					<td><input name="reserve_Code" readonly="readonly" style="background: #c0d8d8;"/></td>
					<td>预订日期:</td>
					<td><input type="text" class="Wdate"  name="reserve_Dete" id="reserve_Detes"  readonly="readonly" /> </td>
					<td>合同编号:</td>
					<td><input name="pact_Code"/></td>
				</tr>
				<tr>
					<td>预付金额:</td>
					<td><input id="payment_Money" name="payment_Money" class="easyui-numberbox" data-options="required:true" onkeyup="controlNumberBefore();"/></td>
					<td>销售价格:</td>
					<td><input id="sellingprice" name="sellingprice"  class="easyui-numberbox" data-options="required:true" onkeyup="controlNumber();"/></td>
					<td>付款方式:</td>
					<td>
						<input style="width:110px" name="pay_Way"
						class="easyui-combobox"
						data-options="
						disabled : true,
						url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.PAYMENTWAY %>',
						multiple:false,
						valueField:'id',  
						textField:'text',
						onLoadSuccess : function(){
							$(this).combobox('select',$(this).combobox('getText'));
						}"/>
					</td>
				</tr>
				<tr>
					<td>订单状态:</td>
					<td>
						<input style="width:110px" name="order_State" id="order_State"
						class="easyui-combobox"
						data-options="
						disabled : true,
						url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ORDER_STATE %>',
						multiple:false,
						valueField:'id',  
						textField:'text',
							onLoadSuccess : function(){
								$('#order_State').val($(this).combobox('setValue','预定中'));
								$('order_State').val($(this).combobox('getText'));
								$(this).combobox('select',$(this).combobox('getText'));
							}
							"
							/>
						</td>
					<td>预交日期:</td>
					<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'predict_Take_Date\',{d:-1})}'})" name="predict_Take_Date" id="predict_Take_Date" /> </td>
					<td>审核情况:</td>
					<td>
						<input style="width:110px" id="examine_id" name="examine"
						class="easyui-combobox"
						data-options="
						disabled : true,
						url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ADUIT %>',
						multiple:false,
						valueField:'id',  
						textField:'text',
							onLoadSuccess : function(){
								$('#examine_id').val($(this).combobox('setValue','未审核'));
								$('#examine_id').val($(this).combobox('getText'));
								$(this).combobox('select',$(this).combobox('getText'));
							}
							"
							/>
					</td>
				</tr>
				<tr>
					<td>分销商:</td>
					<td><input name="xs_Distributor_Code" readonly="readonly" style="background: #c0d8d8;"/></td>
					<td>按揭首付:</td> 
					<td><input name="firstPay_Money" id="firstPay_Money_id" onblur="getAmount();" readonly="readonly" style="background: #c0d8d8;"/></td>
					<td>按揭额:</td> 
					<td><input name="loan_Limit_Money" id="loan_Limit_Money_id" readonly="readonly" style="background: #c0d8d8;"/></td>
				
				</tr>
				<tr>
					<td>按揭行:</td>
					<td>
						<input name="loan_Bank"
							class="easyui-combobox"	data-options="
							disabled : true,
							url : 'sellUtilAction!findBank.action',
							valueField:'id',  
							textField:'name',
							multiple:false ,
							onLoadSuccess : function(){
								$(this).combobox('select',$(this).combobox('getText'));
							}
							"
							/>
						</td>
					<td>按揭年限:</td>
					<td><input name="loan_Limit_Year" readonly="readonly" style="background: #c0d8d8;"/></td>
					<td>预测上牌费用:</td>
					<td><input name="shingle_Money" readonly="readonly" style="background: #c0d8d8;"/></td>
				</tr>
				<tr>
				
					<td>车辆性质:</td>
					<td>
						<input style="width:110px" name="xs_Custom_Property"
						class="easyui-combobox"
						data-options="
						disabled : true,
						url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CARPROPERTY %>',
						multiple:false,
						valueField:'id',  
						textField:'text',
							onLoadSuccess : function(){
								$(this).combobox('select',$(this).combobox('getText'));
							}
							"
							/>
							<input name="custom_Property_Id" id="custom_Property_id" style="display: none;"/>
						</td>
					<td>装潢金额:</td>
					<td><input name="decorate_Money" readonly="readonly" style="background: #c0d8d8;"/></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td colspan="5"><input name="remark" style="width: 530px;"/></td>
				</tr>
			</table>
		</fieldset>
	</form>
	</div>	
  </body>
</html>
