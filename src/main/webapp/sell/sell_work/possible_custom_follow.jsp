<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>潜在客户跟踪</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/sell_work.js"></script>
    <script type="text/javascript">
    	
    	function showWindow(a){
    		$('#from_possible_custom_follow_baseinfor_div').show();
			$('#from_possible_custom_follow_baseinfor_div').dialog({
				modal:true,
				closable : true,
				title : '潜在客户新增',
				width : 670,
				height : 438,
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
						$('#from_possible_custom_follow_baseinfor_div').dialog('close');
					}
		        }],
		        onLoad : function (){
		        	//$('#from_possible_custom_follow_baseinfor_div').show();
		        },
		        onClose : function (){
		        	$('#from_possible_custom_follow_baseinfor_div').hide();
			    }

			});
			//
			
    	}
    	
    	
    	
    	function nuDisAbledControl(){
   		$("#lasttracingdate").prop("disabled", true);
   		//$("#eee").prop("disabled", true);
   		$("#eee").attr("disabled","disabled");
   		$("#txtDate2").prop("disabled", true);
   		$("#txtDate").prop("disabled", true);	
		$("#from_possible_custom_follow_baseinfor_id input.easyui-combobox").combobox('enable');
		$("#from_possible_custom_follow_baseinfor_id input").prop("disabled", false);
		$("#from_possible_custom_follow_baseinfor_id select").prop("disabled", false);
		$("#from_possible_custom_follow_baseinfor_id textarea").prop("disabled",false)
		}
		function disAbledControl(){
		
		$("#from_possible_custom_follow_baseinfor_id input").prop("disabled", true);
		$("#from_possible_custom_follow_baseinfor_id select").prop("disabled", true);
		$("#from_possible_custom_follow_baseinfor_id textarea").prop("disabled",true)
		}
		//
		function dbAddbutton(a){
			var value = $('#datagrid_possible_custom_follow_west_id').treegrid('getSelections');
			
			if(value.length>0 && value[0].state=="open"){
			
			doremovereadonly();
			showWindow(a);
			//新增的时
			$('#from_possible_custom_follow_baseinfor_id').form('clear');
			$('#customId').val(value[0].custom_Id);
			$('#cid').val(value[0].xs_Custom_Name);
			//$('#c_Level').val(value[0].levelName);
			//$('#c_Levelid').val(value[0].level);
			$('#custom_Level_id').combobox('select',value[0].hide_Level);
			$('#txtDate').val(getSystemTime2());
			$('#eee').val(value[0].xs_Custom_Inputdata);
			//$('#custom_Property_Id').val(value[0].custom_Property_Id);
			$('#custom_Property').val(value[0].custom_Property);
			$('#tracing_Day_Number').val(value[0].levaJiange);
			$('#lasttracingdate').val(value[0].tracing_Date);
			$('#xs_Custom_Telephone').val(value[0].xs_Custom_Telephone);
			
			//var today=new Date();
		   // $('#txtDate2').val(new Date(today.setDate(today.getDate()+day)).format("yyyy-MM-dd")); 
			 var newDate =$('#txtDate').val();
			 newDate = newDate.replace(new RegExp("-","gm"),"/");
			 var starttimeHaoMiao = (new Date(newDate)).getTime(); //得到毫秒数
			 var day=value[0].levaJiange;
			 var day2=parseInt(day)*60000*60*24;
			 starttimeHaoMiao=parseInt(starttimeHaoMiao)+parseInt(day2);
			 var newTime = new Date(starttimeHaoMiao); //就得到普通的时间了 
			 $('#txtDate2').val(getTime(newTime));	
			}else{
				alertMsg('请选择要跟踪的客户信息！', 'warning');
			}
		}
		
		/**
	 * 获取时间
	 */
	function getTime(now){
	    var year=now.getFullYear();     
	    var month=now.getMonth()+1;     
	    var day=now.getDate();     
	    var hour=now.getHours();
	    var minute=now.getMinutes();     
	    var second=now.getSeconds(); 
	    if((year+'').length==1){
	    	year='0'+year;
	    }
	    if((month+'').length==1){
	    	month='0'+month;
	    }
	    if((day+'').length==1){
	    	day='0'+day;
	    }
	    if((hour+'').length==1){
	    	hour='0'+hour;
	    }
	    if((minute+'').length==1){
	    	minute='0'+minute;
	    }
	    if((second+'').length==1){
	    	second='0'+second;
	    }
	    return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	}
			
			
		function getRemindTime(){
		 var newDate =$('#txtDate').val();
		 newDate = newDate.replace(new RegExp("-","gm"),"/");
		 var starttimeHaoMiao = (new Date(newDate)).getTime(); //得到毫秒数
		 starttimeHaoMiao=parseInt(starttimeHaoMiao)+parseInt(60000);
		 var newTime = new Date(starttimeHaoMiao); //就得到普通的时间了 
		 $('#msRemindtime').val(getTime(newTime));
	}
		
		//
		function dbEditbutton(a){
		
			var value = $('#datagrid_possible_custom_follow_id').datagrid('getSelections');
				if(value && value.length>0){
					
				
					doremovereadonly();
					showWindow(a);
					
					$('#from_possible_custom_follow_baseinfor_id').form('load',value[0]);
					//最后跟踪
					$('#lasttracingdate').val(value[0].tracing_Date);
					
				}else{
					alertMsg('对不起，请先选择要修改的记录！', 'warning');
				}
			
			}
		
		//取消
		function cancel(){
			$('#addbut').empty();
			$('#editbut').empty();
			doaddreadonly();
			
		}
		
		//保存
		function saveInfor(a){
		nuDisAbledControl();
			var form =  $('#from_possible_custom_follow_baseinfor_id').form();
			var formvalue = serializeObject(form);
				
				if(a==1){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'possibleCustomFollowAction!addCustomRecord.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
						   		//关闭窗口
						   		$('#from_possible_custom_follow_baseinfor_div').dialog('close');
							  	$('#datagrid_possible_custom_follow_id').datagrid({url : 'possibleCustomFollowAction!findCustomById.action?custom_Id='+formvalue.custom_Id});
							  
							  	doCFind();
							  	//disAbledControl();
							  	cancel();
						   }
					   }
					});
				}
				
				if(a==2){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'possibleCustomFollowAction!updateCustomRecord.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
						   		//关闭窗口
						   		$('#from_possible_custom_follow_baseinfor_div').dialog('close');
								$('#datagrid_possible_custom_follow_id').datagrid('load');
								
								doCFind();
								//disAbledControl();
								cancel();
						   }
					   }
					});
				
				}
					
				}
				
		//删除  
		function deleteRecord(){
			var value = $('#datagrid_possible_custom_follow_id').datagrid('getSelections');
			var data = $('#datagrid_possible_custom_follow_id').datagrid('getSelected');
		    var index=findSelectRowIndex('datagrid_possible_custom_follow_id',data);
					if(value.length>0){
						if(value[0].examine_Flag=="0" || value[0].examine_Flag==null){
							$.messager.confirm('优亿软件提示','请确认是否要删除编号为【'+value[0].tracing_Id+'】的记录？',function(b){
								if(b){
								//发送请求
								$.ajax({
									type : 'POST',
									url : 'possibleCustomFollowAction!deleteCustomRecord.action',
									data : value[0],
								    dataType : 'json',
									success : function(r){
										if(r.success){
										    $('#datagrid_possible_custom_follow_id').datagrid('clearSelections');
										    $('#datagrid_possible_custom_follow_id').datagrid('reload');
											setSelectRow('datagrid_possible_custom_follow_id',index);
											
										}
									}
							   	});
							   	}	
							});
						   	}else{
						   		alertMsg('对不起，该记录已审核不可删除！', 'warning');
						   	}
					}else{
						alertMsg('对不起，请先选择要删除的客户跟踪记录！', 'warning');
					}
			
		}
		
		
		
		//审核
		function doFollowAudit(){
			var value = $('#datagrid_possible_custom_follow_id').datagrid('getSelections');
			var form =  $('#from_possible_custom_follow_baseinfor_id').form();
			var formvalue = serializeObject(form);
				if(value.length>0){
						
						$.ajax({
							type : 'POST',
							url : 'possibleCustomFollowAction!doAudit.action',
							data : formvalue,
						    dataType : 'json',
							success : function(r){
								if(r.success){
								alertMsg('审核成功！', 'infor');
									$('#from_possible_custom_follow_baseinfor_div').dialog('close');
									$('#datagrid_possible_custom_follow_id').datagrid('load');
									$('#doexameid').linkbutton('disable');	//禁用按钮
									nuDisAbledControl();
									doremovereadonly();
								}else{
									nuDisAbledControl();
						 		    alertMsg(r);
						 		$('#from_possible_custom_follow_baseinfor_div').dialog('close');
								 }
							}
					   	});
				}else{
					alertMsg('对不起，请先选择要审核的记录！', 'warning');
				}
			}
			
		//限制form 表单是否 可读
		function doremovereadonly(){
			$("#tracing_Address").removeAttr("readonly");
			$("#hinder_Content").removeAttr("readonly");
			$("#talk_Title").removeAttr("readonly");
			$("#tracing_Summary").removeAttr("readonly");
			$("#examine_Opinion_id").attr({readonly: "readonly"});
		}
		
		function doaddreadonly(){
			$("input").attr({ readonly: "readonly"});
			$("textarea").attr({ readonly: "readonly"});
			//审批
			$("#examine_Opinion_id").removeAttr("readonly");
			
		}
		
		//导出
		function doexcept(dateGridId,parentId){
			showEditDialog(dateGridId,null,parentId,"开始导出","导出配置",0,_callbackExcept);
		}
		function _callbackExcept(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"潜在客户跟踪"+getSystemTime());
		}
		//打印
		function dopoint(dateGridId,parentId){
			showEditDialog(dateGridId,null,parentId,"开始打印","打印配置",2,_print);
		}
		function _print(parentId,fieldNames){
			printEsuyUIPreview(parentId,fieldNames);
		}
		//条件查询treeGrid
		function doCFind(){
		 reLoadTreeGrid('datagrid_possible_custom_follow_west_id', 'from_possible_custom_follow_id', 'possibleCustomFollowAction!findCustomInforCount.action');
		 //addInitDate($('#interview_Date'),$('#interview_Date2'));
		 if(($('#interview_Date').val()==null || $('#interview_Date').val()=="" )&& ($('#interview_Date2').val()==null || $('#interview_Date2').val()=="")){
		 	$('#interview_Date').val(getSystemTime());
		 	var today=new Date();
			today=new Date(today.setDate(today.getDate()+1)).format("yyyy-MM-dd")
			$('#interview_Date2').val(today);
			}
		$('#datagrid_possible_custom_follow_id').datagrid('loadData',{total:0,rows:[]});	
		}	
		//清空form表单
	 	function doClear2(fromid){
	 		fromid.form('clear');
	 		doCFind();
	 	}
	 	//改变客户登记时
	 	var changeLevel = function(day){
 		 $('#tracing_Day_Number').val(day);
 		 var newDate =$('#txtDate').val();
		 newDate = newDate.replace(new RegExp("-","gm"),"/");
		 var starttimeHaoMiao = (new Date(newDate)).getTime(); //得到毫秒数
		 var day2=parseInt(day)*60000*60*24;
		 starttimeHaoMiao=parseInt(starttimeHaoMiao)+parseInt(day2);
		 var newTime = new Date(starttimeHaoMiao); //获取时间 
		 $('#txtDate2').val(getTime(newTime));	
	 	}
    </script>
  </head>
  
  <body>
    
    	<div id="cc" class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:140px;" border="true">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dbAddbutton(1);">新增</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRecord();">删除</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dbEditbutton(2);">修改</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doCFind();">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="dopoint('datagrid_possible_custom_follow_id','datagrid_possible_custom_follow_div');">打印</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="doexcept('datagrid_possible_custom_follow_id','datagrid_possible_custom_follow_div');">导出</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear2($('#from_possible_custom_follow_id'));">清空</a>
			<span id="addbut"></span>
			<span id="editbut"></span>
			
			<form id="from_possible_custom_follow_id">
				<fieldset style="height:91px;">
					<legend>条件筛选</legend>
					<table>
						<tr>
							<td>客户名称：</td>
							<td><input name="xs_Custom_Name"/></td>
							<td>电话：</td>
							<td><input name="xs_Custom_Telephone"/></td>
							<td>预约日期：</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'interview_Date2\',{d:-1})}'})" name="interview_Date" id="interview_Date" style="width: 85px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'interview_Date\',{d:1})}'})" name="interview_Date2" id="interview_Date2" style="width: 85px;"/></td>
							<td>车辆品牌：</td>
							<td>
								  <input  name="car_Model" style="width:120px;" class="easyui-combobox"
							data-options="url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
							valueField:'id',   
							textField:'text',
							mode : 'remote'"/>
							</td>
						</tr>
						<tr>
							
							<td>业务员：</td>
							<td colspan="3">
								<input name="stf_Id" style="width:293px"
								class="easyui-combobox"	data-options="
								url : 'basStuffClassAction!findSellOperationPerson.action',
								valueField:'id',  
								textField:'text',
								multiple:false ,
								mode:'remote'  "
								
								/>
							</td>
							
							<td>跟踪日期：</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'tracing_Date2\',{d:-1})}'})" name="tracing_Date" id="tracing_Date" style="width: 85px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'tracing_Date\',{d:1})}'})" name="tracing_Date2" id="tracing_Date2" style="width: 85px;"/></td>
							<td>联系人：</td>
							<td><input name="xs_Contacts_Person" style="width:120px"/></td>
						</tr>
						<tr>
							<td>客户级别：</td>
							<td>
								<input style="width:110px" id="xscustom_Level"  name="xs_Custom_Hide_Level"
								class="easyui-combobox"
								data-options="
								url : 'customLevaAction!findAllLeva.action',
								valueField:'id',  
								textField:'text',
								multiple:false ,
								mode:'remote'"
								/>
							</td>
							<td>成交几率：</td>
							<td>
								<input  style="width:50px;"
									class="easyui-combobox"
									data-options="
									url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BUYPROBABILITY %>',
									multiple:false,
									valueField:'id',  
									textField:'text',
									onSelect : function(rec){
										$('#buy_Probability_id').val(rec.text);
									}
									"
									/>至<input  style="width:47px;"
									class="easyui-combobox"
									data-options="
									url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BUYPROBABILITY %>',
									multiple:false,
									valueField:'id',  
									textField:'text',
									onSelect : function(rec){
										$('#buy_Probabilitys_id').val(rec.text);
									}
									"
									/>
							</td>
							<td style="display: none"><input id="buy_Probability_id" name="buy_Probability"/></td>
							<td style="display: none"><input id="buy_Probabilitys_id" name="buy_Probabilitys"/></td>
							<td>障碍：</td>
							<td colspan="2">
								<input style="width:190px;" id=""  name="obstacle"
								class="easyui-combobox"
								data-options="
								url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.IMPEDE %>',
								valueField:'id',  
								textField:'text',
								multiple:false ,
								mode:'remote'"
								/>
							</td>
						</tr>
					</table>
				</fieldset>
			</form>
			
		</div>
		<div id="datagrid_possible_custom_follow_div" region="center"  style="background:#eee;" border="false" >
			<table id="datagrid_possible_custom_follow_id"></table>
		</div>
		<div region="west"  style="background:#eee; width:360px;" border="false" >
			<table id="datagrid_possible_custom_follow_west_id"></table>
		</div>
		
		<div id="from_possible_custom_follow_baseinfor_div" >
		<form id="from_possible_custom_follow_baseinfor_id">
			<table>
					<tr><td><input name="custom_Id" id="customId" style="display: none"/></td></tr><!--
					<tr><td><input name="#c_Levelid" id="#c_Levelid" style="display: none"/></td></tr>
					<tr><td><input name="#custom_Property_Id" id="#c_Levelid" style="display: none"/></td></tr>
					--><tr><td><input name="tracing_Id" style="display: none"/></td></tr>
					<tr>
					<td>客户名称：</td>
					<td><input name="xs_Custom_Name"  id="cid" readonly="readonly" /></td>
					<td>最后跟踪：</td>
					<td> <input  style="width:140px" id="lasttracingdate" disabled="disabled"   class="Wdate" type="text" onfocus="WdatePicker()" /></td>
					<td>建档日期：</td>
					<td> <input  id="eee" style="width:140px" name="xs_Custom_Inputdata"  disabled="disabled" class="Wdate" type="text" onfocus="WdatePicker()" /></td>
					</tr>
					<tr>
					<td>电话：</td>
					<td><input id="xs_Custom_Telephone" readonly="readonly" name="xs_Custom_Telephone"/></td>
					<td>预约日期：</td>
					<td> <input readonly="readonly" id="txtDate2" name="interview_Date" disabled="disabled" style="width:140px" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
					<td>跟踪日期：</td>
					<td> <input readonly="readonly" style="width:140px" id="txtDate" disabled="disabled"  name="tracing_Date" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
					</tr>
					<tr>
					<td>跟踪单号：</td>
					<td><input name="tracing_Code" readonly="readonly" /></td>
					<td>地点：</td>
					<td><input readonly="readonly" id="tracing_Address" style="width:140px" class="easyui-validatebox" data-options="validType:'characterDigit'" name="tracing_Address"/></td>
					<td>气氛：</td>
					<td><input style="width:140px" name="ambience"
						class="easyui-combobox"
						data-options="
						url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ATMOSPHERE %>',
						multiple:false,
						valueField:'id',  
						textField:'text'
						"
						/>
					</td>
					</tr>
					<tr>
					<td>跟踪方式：</td>
					<td><input  name="tracing_Way"
						class="easyui-combobox"
						data-options="
						url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.TRACINGWAY %>',
						multiple:false,
						valueField:'id',  
						textField:'text'
						"
						/>
					</td>
					<td>试驾车型：</td>
					<td>
				    <input id="testdrive_Model_id"  name="car_Model" style="width:140px;" class="easyui-combobox"
							data-options="url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
							valueField:'id',   
							textField:'text',
							mode : 'remote'"/>
						</td>
						<td>潜在客户等级：</td>
						<td>
							<input style="width:140px" id="custom_Level_id"  name="level"
							class="easyui-combobox"
							data-options="
							url : 'customLevaAction!findAllLeva.action',
							required:true,
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote',
							onLoadSuccess : function(){
								$(this).combobox('setValue',$(this).combobox('getText'));
							},onSelect : function(record){
								$.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: 'possibleCustomFollowAction!getLevelDays.action?xs_Custom_Hide_Level_Id='+record.id,
								   data: '',
								   success: function(r){
									   if(r.success){
									   		changeLevel(r.obj.jianGe);
									   }
								   }
								});
							}"
							/>
						</td>
						
					</tr>
					<tr>
					<td>成交几率：</td>
					<td><input class="easyui-combobox" name="buy_Probability" id="add_buy_Probability_id" 
									data-options="
									url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BUYPROBABILITY %>',
									multiple:false,
									valueField:'id',  
									textField:'text',
									onSelect : function(rec){
										$('#add_buy_Probability_id').val(rec.id);
									}
									"
									/>
									</td>
					
					<td>障碍：</td>
					<td><input name="obstacle" style="width: 140px"
									class="easyui-combobox"
									data-options="
									url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.IMPEDE %>',
									multiple:false,
									valueField:'id',  
									textField:'text'
									"
									/></td>
					<td>预购车型：</td>
					<td>
				    <input id="cai_Model_Need_id"  name="cai_Model_Need" style="width:140px;" class="easyui-combobox"
							data-options="url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
							valueField:'id',   
							textField:'text',
							mode : 'remote'"/>
					</td>
					
					</tr>
					<tr>
						<td>跟踪天数：</td>
					<td>
						<input readonly="readonly"  id="tracing_Day_Number" name="tracing_Day_Number" />
					</td>
					</tr>
					<tr>
					<td>障碍描述：</td>
					<td  colspan="3"><textarea readonly="readonly" id="hinder_Content" class="easyui-validatebox" data-options="validType:'characterDigit'" style="width:330px; resize : none" name="hinder_Content" maxlength="50"></textarea></td>
					
					
					<td>讨论主题：</td>
					<td colspan="3" rowspan="2"><textarea readonly="readonly" id="talk_Title" class="easyui-validatebox" data-options="validType:'characterDigit'" style="width:140px; resize : none;height: 145px;resize:none;" name="talk_Title" maxlength="50"></textarea></td>
					</tr>
					<tr>
					<td>跟踪总结：</td>
					<td colspan="3"><textarea readonly="readonly" id="tracing_Summary" class="easyui-validatebox" data-options="validType:'characterDigit'" style="width:330px;hight:600px; resize : none" name="tracing_Summary" maxlength="50"></textarea></td>
					</tr>
					<tr>
					<td><font color="red">审批意见：</font></td>
					<td colspan="3"><input style="width:180px;" name="examine_Opinion" id="examine_Opinion_id" />
					<input style="width:125px;" disabled="disabled" id="examine_Date" name="examine_Date" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> </td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="disabled:true" plain="true" onclick="doFollowAudit();" id="doexameid"><div style="font-size:16;color:red;">确认审批</div></a></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
  </body>
</html>
