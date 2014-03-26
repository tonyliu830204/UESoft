<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%
BasUsers user = (BasUsers) request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>退车管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/sell_work.js"></script>
    <script type="text/javascript">
		//新增
		function dbAddbuttom(){
			//显示图片按钮
			$('#imgid').linkbutton({disabled:false});
			$("#exitCar_Type").combobox({required:true});
			$('#sell_Codes_s').validatebox({required:true});
			$('#form_quitcar_manage_id').form('clear');
			$('#form_quitcar_manage_id_div').show();
			$('#xs_Car_Sel_Data').datetimebox('setValue','{now}');
			$('#stf_Id').combobox('setValue',<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>);
			$('#form_quitcar_manage_id_div').dialog({
				modal:true,
				closable : true,
				title : '新增退车记录',
				width : 400,
				height : 350,
				cache: false,
				buttons : [{
					iconCls : 'icon-save',
					text : '保存',
					handler : function (){
						addQuitInfor();
					}
		        },{
		        	iconCls : 'icon-undo',
					text : '取消',
					handler : function (){
						$('#form_quitcar_manage_id_div').dialog('close');
					}
		        }],onBeforeClose:function(){
					$('#exitCar_Type').combobox({required:false});
					$('#sell_Codes_s').validatebox({required:false});
					$('#sell_Codes_s').validatebox('validate');
	       		 }
			});
		}
		
		//点击修改按钮
		function dbEditbuttom(){
			var value = $('#datagrid_quitcar_manage_id').datagrid('getSelected');
			if(value){
				if(value.examine_Name=="已审核"){
					alertMsg('该记录已审核不可修改！', 'warning');
					return;
				}
				//隐藏图片按钮
				$('#imgid').linkbutton({disabled:true});
				$('#sell_codes').validatebox({required:true});
				$('#form_quitcar_manage_id').form('clear');
				//将数据load给form 表单 
				$('#form_quitcar_manage_id').form('load',value);
				$('#form_quitcar_manage_id_div').show();
				//$('#xs_Car_Sel_Data').datetimebox('setValue','{now}');
				$('#stf_Id').combobox('setValue',<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>);
				$('#form_quitcar_manage_id_div').dialog({
					modal:true,
					closable : true,
					title : '编辑退车记录',
					width : 400,
					height : 350,
					buttons : [{
						iconCls : 'icon-save',
						text : '保存',
						handler : function (){
							editQuitInfor();
						}
			        },{
			        	iconCls : 'icon-undo',
						text : '取消',
						handler : function (){
							$('#form_quitcar_manage_id_div').dialog('close');
							$('#form_quitcar_manage_id_div').hide();
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
		
		//删除退车记录  
		function deleteQuitInfor(){
			var value = $('#datagrid_quitcar_manage_id').datagrid('getSelections');
		    var data = $('#datagrid_quitcar_manage_id').datagrid('getSelected');
		    var index=findSelectRowIndex('datagrid_quitcar_manage_id',data);
			if(value.length>0){
			if(value[0].examine_Name=="未审核"){
			$.messager.confirm('优亿软件提示','请确认是否要删除编号为【'+value[0].exitCar_Code+'】的退车记录？',function(b){
				if(b){
					//发送请求
					$.ajax({
						type : 'POST',
						url : 'quitCarManageAction!deleteQuitInfor.action',
						data : value[0],
					    dataType : 'json',
						success : function(r){
							if(r.success){
								$('#datagrid_quitcar_manage_id').datagrid('clearSelections');
								$('#datagrid_quitcar_manage_id').datagrid('reload');
								setSelectRow('datagrid_quitcar_manage_id',index);	
							}
						}
				   	});
					
				}
			});
			}else{
				alertMsg('该记录已审核不可删除！', 'warning');
			}
			}else{
				alertMsg('对不起，请先选择要删除的记录！', 'warning');
			}
		}
		
		//保存修改后的退车记录
		function editQuitInfor(){
			var form =  $('#form_quitcar_manage_id').form();
			var formvalue = serializeObject(form);
					if($('#form_quitcar_manage_id').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'quitCarManageAction!updateQuitInfor.action',
						   data: formvalue,
						   success: function(r){
							   if(r.success){
							   		$('#form_quitcar_manage_id_div').dialog('close');
							   		$('#datagrid_quitcar_manage_id').datagrid('reload');
							   		$('#form_quitcar_manage_id_div').hide();
							   }
						   }
						});
					}else{
						alertMsg('请正确填写必填内容！', 'warning');
					}
				}
				
		//保存退车记录
		function addQuitInfor(){
			var form =  $('#form_quitcar_manage_id').form();
			var formvalue = serializeObject(form);
					if($('#form_quitcar_manage_id').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'quitCarManageAction!addQuitInfor.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
						   		$('#form_quitcar_manage_id_div').dialog('close');
						   		$('#datagrid_quitcar_manage_id').datagrid('reload');
						   		$('#form_quitcar_manage_id_div').hide();
						   		$("#exitCar_Type").combobox({required:false});
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
		//销售单号查询
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
		
		//销售单号选择窗口
		var dlog;
		function showShDlog(url){
			dlog = $('<div/>');
			dlog.dialog({
				title: '销售单信息',   
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
		 //
		function dbRefundment(){
			var value = $('#datagrid_quitcar_manage_id').datagrid('getSelected');
			if(value){
				if(value.examine_Name=="已审核"){
					if(value.notice=='1'){
						alertMsg('已通知退款,不能重复通知退款！', 'warning');
						return;
					}
					//通过后台获取应退金额
					$.ajax({
						type : 'post',
						url : 'quitCarManageAction!getAmountByExit.action',
						data : value,
						dataType : 'json',
						success : function(r){
						   //获取应退金额
			               $('#backCar_Sunmoney_id').val(r.backCar_Sunmoney);
			               $('#reciveOrSellTag').val(r.reciveOrSellTag);
			               $('#reserveId').val(r.reserveId);
							//将数据load给form 表单 
							$('#form_notice_quitcar_manage_id').form('load',value);
							$('#backCar_stf_Id').combobox('setValue',<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>);
							$('#form_notice_quitcar_manage_id_div').dialog({
								modal:true,
								closable : true,
								title : '通知退款',
								width : 380,
								height : 245,
								buttons : [{
									iconCls : 'icon-save',
									text : '保存',
									handler : function (){
										doNoticeRefundment();
										
									}
						        },{
						        	iconCls : 'icon-undo',
									text : '取消',
									handler : function (){
										$('#form_notice_quitcar_manage_id_div').dialog('close');
										
										
									}
						        }]
							});
						  }
					});			
				}else{
					alertMsg('请选择已审核记录！', 'warning');
				}
			}else{
				alertMsg('请先选择要通知退款的记录！', 'warning');
			}
		}
		//保存通知退款记录		
		function doNoticeRefundment(){
			var value = serializeObject($('#form_notice_quitcar_manage_id').form());
				//console.info(value);
				//发送请求
				$.ajax({
					type : 'POST',
					url : 'quitCarManageAction!doNoticeRefundment.action',
					data : value,
				    dataType : 'json',
					success : function(r){
						if(r.success){
							alertMsg(r.msg,"info");
							$('#datagrid_quitcar_manage_id').datagrid('reload');
							$('#form_notice_quitcar_manage_id_div').dialog('close');
						}else{
							alertMsg(r.msg,"info");
						}
					}
			   	});
			}
		//审核
		function doAudit(datagrid,url){
			var value = $('#datagrid_quitcar_manage_id').datagrid('getSelected');
			if(value){
				if(value.examine_Name=="已审核"){
					alertMsg('此单已审核，不能反审核！', 'warning');
					return;
			   	}
				$.ajax({
					type : 'POST',
					url : 'quitCarManageAction!doAuditQuitInfor.action',
					data : value,
				    dataType : 'json',
					success : function(r){
						if(r.success){
							$('#datagrid_quitcar_manage_id').datagrid('reload');
						}else{
							alertMsg(r.msg,"info");
						}
					}
			   	});
			}else{
				alertMsg('对不起，请先选择要审核的记录！', 'warning');
			}
       }
       //清空
		function clearSearchCondition(formid,datagrid,dateId1,dateId2){
			$('#form_south_quitcar_manage_id').find('input').val('');
			$('#form_south_quitcar_manage_id').find('select').val('');
			$('#datagrid_quitcar_manage_id').datagrid('unselectAll');
			doConditionSubmit(formid,datagrid,dateId1,dateId2)
		}
    </script>
  </head>
  		<body>
  		<div class="easyui-layout" style="width:900px;height:800px;" border="false" fit="true">
  		
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:30px;" border="false">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dbAddbuttom();">新增</a>
		<!--<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteQuitInfor();">删除</a>
		--><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dbEditbuttom();">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit($('#form_south_quitcar_manage_id'),$('#datagrid_quitcar_manage_id'),$('#exitCar_Date'),$('#exitCar_Date2'));">查询</a>
		<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-cancel" plain="true"  onclick="clearSearchCondition($('#form_south_quitcar_manage_id'),$('#datagrid_quitcar_manage_id'),$('#exitCar_Date'),$('#exitCar_Date2'));">清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="doAudit();" >审核</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="dbRefundment();" plain="true">通知退款</a>
		</div>
		<div region="center" style="background:#eee;"  border="false">
		
		 <div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true" >
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:90px;" border="false">
			   <form id="form_south_quitcar_manage_id">
			   <table>
			   <tr>
			   			<td>退车日期:</td>
                 			<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'exitCar_Date2\',{d:-1})}'})"
									name="exitCar_Date" id="exitCar_Date" style="width: 95px;"  />
								至
								</td>
								<td><input type="text" class="Wdate"onclick="WdatePicker({minDate:'#F{$dp.$D(\'exitCar_Date\',{d:1})}'})"
									name="exitCar_Date2" id="exitCar_Date2" style="width: 95px;" 	 />
											</td>
				     <td>VIN号:</td>
					<td><input type="text" name="xs_Car_Vin_Number" style="width: 130px;"></td>
			         <td> 退车单号:</td>
			         <td><input id="" name="exitCar_Code" style="width: 130px;" /></td>
			   
				    <td>客户名称:</td>
				    <td><input id="" name="xs_Custom_Name" style="width: 130px;"/></td>
				    </tr>
				       <tr>
			   			<td>销售日期:</td>
                 			<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xs_Car_Sel_Date2\',{d:-1})}'})"
									name="xs_Car_Sel_Date" id="xs_Car_Sel_Date" style="width: 95px;"  />
								至
								</td>
								<td><input type="text" class="Wdate"onclick="WdatePicker({minDate:'#F{$dp.$D(\'xs_Car_Sel_Date\',{d:1})}'})"
									name="xs_Car_Sel_Date2" id="xs_Car_Sel_Date2" style="width: 95px;" 
													 />
											</td>
				     <td>销售单号:</td>
					<td><input type="text" name="sell_Code" style="width: 130px;"></td>
			        	<td>退车分类：</td>
						<td>
							<input style="width:130px"  id="q" name="exitCar_Type"
							class="easyui-combobox"
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.QUITCARTYPE.QUITCARTYPEKEY %>',
							valueField:'id',  
							textField:'text',
							mode:'remote'"/>
						</td>
			   
				    <td>审核人：</td>
						<td>
							<input id="exitCar_Check_Person" name="exitCar_Check_Person"  style="width:130px" 
							class="easyui-combobox"	data-options="
							url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote'"
							/>
				    </tr>
			 </table>
			    </form>
		   </div>
			<div id="datagrid_quitcar_manage_id_div" region="center" style="background:#eee;" border="false">
				<table id="datagrid_quitcar_manage_id"></table>
			</div>
	  	</div>
	  	
	  	<div id="form_quitcar_manage_id_div" >
				<form id="form_quitcar_manage_id" >
				<table style="text-align: right">
					<tr>
						<td>
							<input name="custom_Id"  style="display: none;"/>
							<input name="xs_Car_Id"  style="display: none;"/>
							<input name="xs_Car_Sel_Id" style="display: none;"/>
							<input name="exitCar_Id" style="display: none;"/>
						</td>
					</tr>
					<br/>
					<tr>
						<td width="110px">销售编号：</td>
						<td><input style="width:200px"  id="sell_Codes_s" data-options="required:true" name="sell_Code" value="fff"  class="easyui-validatebox" /></td>
						<td style="width: 25px;">	
						<a href="javascript:void(0);" id="imgid" class="easyui-linkbutton" iconCls="icon-choice"  plain="true"
						onclick="showShDlog('${pageContext.request.contextPath}/sell/sell_work/choice_sell_infor.jsp?isCarOut=true');"></a>
						</td>
					</tr>
					<tr>
						<td>销售日期：</td>
						<td> 
						<input type="text" id="xs_Car_Sel_Data" name="xs_Car_Sel_Date" 
					value="{now}" readonly="readonly"
					class="easyui-datetimebox" style="width: 200px;background:#EBEBE4;"
				data-options="disabled:true,required : true, editable : false "/>
						</td>
					</tr>
					<tr>
						<td>客户名称：</td>
						<td> <input type="text" style="width:200px" name="xs_Custom_Name" readonly="readonly"/></td>
					</tr>
					
					<tr>	
						<td>VIN号：</td>
						<td><input id="" style="width:200px" name="xs_Car_Vin_Number" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>业务员：</td>
						<td>
							<input id="stf_Id" name="stf_Id"  style="width:200px" 
							class="easyui-combobox"	data-options="
							disabled : true,
							url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote',
							onLoadSuccess : function(){
								$('account_Person_id').val($(this).combobox('getText'));
							},
							onSelect : function(record){
								$('#account_Person_id').val(record.id);
							}  "
							/>
							<input name="account_Person" id="account_Person_id" style="display: none;"/>
						</td>
					</tr>
					<tr>
						<td>退车分类：</td>
						<td>
							<input style="width:200px"  id="exitCar_Type" name="exitCar_Type"
							class="easyui-combobox"
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.QUITCARTYPE.QUITCARTYPEKEY %>',
							required:true,
							valueField:'id',  
							textField:'text',
							mode:'remote'"/>
						</td>
					</tr>
					<tr>
						<td>备注</td>
						<td>
							<textarea rows="" cols="" name="exitCar_Remark"  
							style="width:200px;height:50px;;resize:none;"></textarea>
						</td>
					</tr>
					<!--<tr>
						<td>售前检测日期：</td>
						<td>
						<input style="width:200px"  id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()"  name="xs_Car_Pds_Date"/>
						 </td>
					</tr>
					<tr>
						<td>PDS人员：</td>
						<td>
						<input name="xs_Car_Pds_Person" id="xs_Car_Pds_Person_id" style="width:200px" 
							class="easyui-combobox"	data-options="
							url : 'sellUtilAction!findStfName.action',
							valueField:'id',  
							textField:'name',
							multiple:false ,
							mode:'remote',
							onLoadSuccess : function(){
								$('xs_Car_Pds_Person_id').val($(this).combobox('getText'));
							},
							onSelect : function(record){
								$('#xs_Car_Pds_Person_id').val(record.id);
							}  "
							/>
						</td>
					</tr>
					<tr>
						<td rowspan="2">PDS结果：</td>
						<td rowspan="2"><input style="height : 30px;width:200px" id="" name="xs_Car_Pds_Result" />
						</td>
					</tr>
				-->
				</table>
			</form>
			</div>
	  		<!-- 通知退款弹出窗口 -->
				<div id="form_notice_quitcar_manage_id_div" >
				<form id="form_notice_quitcar_manage_id" >
				<table style="text-align: right">
					<tr>
						<td>
							<input name="custom_Id"  style="display: none;"/>
							<input name="xs_Car_Id"  style="display: none;"/>
							<input name="xs_Car_Sel_Id" type="hidden"/>
							<input name="exitCar_Id" type="hidden"/>
							<input id="reserveId" name="reserveId" type="hidden" />
							<input id="reciveOrSellTag" name="reciveOrSellTag" type="hidden"/>
						</td>
					</tr>
					<tr>
						<td>退车单编号：</td>
						<td> <input type="text" style="width:200px" name="exitCar_Code" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>客户名称：</td>
						<td> <input type="text" style="width:200px" name="xs_Custom_Name" readonly="readonly"/></td>
					</tr>
					<tr>	
						<td>VIN号：</td>
						<td><input id="" style="width:200px" name="xs_Car_Vin_Number" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>通知退款人：</td>
						<td>
							<input id="backCar_stf_Id" name="backCar_Person"  style="width:200px" 
							class="easyui-combobox"	data-options="
							disabled : true,
							url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote' "
							/>
						</td>
					</tr>
					<tr>
						<td>应退金额：</td>
						<td><font color="red"><input type="text" style="width:200px" id="backCar_Sunmoney_id" name="backCar_Sunmoney" readonly="readonly"/></font></td>
					</tr>
				
					
				</table>
			</form>
			</div>
	  	
		</div>
	</div>
	
	
  </body>
</html>
