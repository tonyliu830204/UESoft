<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
BasUsers user = (BasUsers) request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>来电客流登记</title>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/sell_work.js"></script>
   
    <script type="text/javascript">
    
    // 1是新增 2是修改
    var dflag = 0;
		//点击新增按钮 开启 增加记录 修改记录 删除记录按钮
		function dbAddbutton(){
		dflag = 1;
			//点击新增按钮时 清空明细datafrid 数据
			$('#form_carlost_infor_detail_id').form('clear');
			$('#datagrid_back_register_detail_id').datagrid('loadData',{"rows":[],"total":0});
			
			$('#tab_back_register_id').tabs('select','登记表明细');
			$('#form_back_register_detail_south_id').form('clear');
			$('#detail_stf_Id').combobox('select','<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>');
			$('#systime').val(getSystemTime());
			var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveAllInfo('+dflag+');">保存</a>';
				var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
				if ($('#addbut').children('a').length == 0) {
					$('#addbut').append(save).append(cancel);
					$.parser.parse('#addbut');
				}
				disableBtn();
			$('#_export').linkbutton('disable');
			$('#addbutton').linkbutton('enable');		
			$('#deletebutton').linkbutton('enable');	
			
		}
		
		//点击修改按钮
		function dbEditbuttom(){
		dflag = 2;
			var value = $('#datagrid_back_register_id').datagrid('getSelections');
			if(value.length>0){
			
				$('#datagrid_back_register_detail_id').datagrid({url : 'backRegisterAction!findRecordById.action?xs_Custom_Salesman_Id='+value[0].xs_Custom_Salesman_Id});
				
				$('#tab_back_register_id').tabs('select','登记表明细');
				
				//将数据load给form 表单 
				$('#form_carlost_infor_detail_id').form('load',value[0]);
				
				var edit = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveAllInfo('+dflag+');">保存</a>';
					var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
					if ($('#editbut').children('a').length == 0) {
						$('#editbut').append(edit).append(cancel);
						$.parser.parse('#editbut');
					}
					disableBtn();
				$('#_export').linkbutton('disable');
				$('#addbutton').linkbutton('disable');		
				$('#editbutton').linkbutton('enable');		//启用按钮
				$('#deletebutton').linkbutton('enable');	
				
			}else{
				alertMsg('对不起，请先选择要修改的记录！', 'warning');
			}
		}
		
		//删除汇总信息  
		function deleteRecord(){
			var value = $('#datagrid_back_register_id').datagrid('getSelected');
			   var index=findSelectRowIndex('datagrid_back_register_id',value);
			if(value){
			$.messager.confirm('优亿软件提示','请确认是否要删除编号为【'+value.xs_Custom_Salesman_Id+'】的登记表记录？',function(b){
				if(b){
				
					//发送请求
					$.ajax({
						type : 'POST',
						url : 'backRegisterAction!deleteRecord.action',
						data : value,
					    dataType : 'json',
						success : function(r){
							if(r.success){
							    $('#datagrid_back_register_id').datagrid('clearSelections');
								$('#datagrid_back_register_id').datagrid('reload');
								setSelectRow('datagrid_back_register_id',index);
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
						alertMsg('对不起，请先选择要删除的记录！', 'warning');
		}
		}
	
		//删除明细信息  
		function deleteDetailInfo(){
			var value = $('#datagrid_back_register_detail_id').datagrid('getSelections');
			if(value && value.length>0){
				$.messager.confirm('优亿软件提示','请确认是否要删除该条明细记录？',function(b){
				if(b){
				//如果有编号存在根据编号删除 若没有 则直接remove掉
				if(value[0].xs_Custom_Salesman_Detail_Id && value[0].xs_Custom_Salesman_Detail_Id != ""){
					//发送请求
					$.ajax({
						type : 'POST',
						url : 'backRegisterAction!deleteDetailInfo.action',
						data : value[0],
					    dataType : 'json',
						success : function(r){
							if(r.success){
								$('#datagrid_back_register_detail_id').datagrid('reload');
								var records=$("#datagrid_back_register_detail_id").datagrid("getRows");
								$("#total").val(records.length);
							}
						}
				   	});
				   	}else{
				   		var index = $('#datagrid_back_register_detail_id').datagrid('getRowIndex',value[0]);
				   		$('#datagrid_back_register_detail_id').datagrid('deleteRow',index);
				   		var records=$("#datagrid_back_register_detail_id").datagrid("getRows");
						$("#total").val(records.length);
				   	}
				
				}
			});
			}else{
					alertMsg('对不起，请先选择要删除的记录！', 'warning');
			}
		}
		//取消
		function cancel(){
			$('#addbut').empty();
			$('#editbut').empty();
			$('#tab_back_register_id').tabs('select','登记表汇总');
			
			$('#addbutton').linkbutton('disable');		//禁用按钮
			$('#editbutton').linkbutton('disable');		//禁用按钮
			$('#deletebutton').linkbutton('disable');	//禁用按钮
			addreadonly();
		}
		//上传
		function upLoad(){
			removereadonly();
			var formvalues = serializeObject($('#form_back_register_detail_south_id').form());
			//上传的时候 如果是新增直接上传 如果是修改 ；判断编号是否一致 如果一致删除原来的一条 重新将修改的记录append进去
			var dvalue = $('#datagrid_back_register_detail_id').datagrid('getSelections');
			if($('#form_back_register_detail_south_id').form('validate')){
				//判断是新增还是修改
				if(dvalue!=null && dvalue[0]!=null){
					var rowIndex = $('#datagrid_back_register_detail_id').datagrid('getRowIndex',dvalue[0]);
					//删除一行
					$('#datagrid_back_register_detail_id').datagrid('deleteRow', rowIndex);
					//删除后再添加一行
					$('#datagrid_back_register_detail_id').datagrid('appendRow',formvalues);
				}else{
					$('#datagrid_back_register_detail_id').datagrid('appendRow',formvalues);
				}
				$('#upbutton').linkbutton('disable');
				$('#deletebutton').linkbutton('enable');
				addreadonly();	//启用按钮	
				$('#form_back_register_detail_south_id').form('clear');
				//form表单设为只读
				var records=$("#datagrid_back_register_detail_id").datagrid("getRows");
					$("#total").val(records.length);
			}else{
				alertMsg('请正确填写必填项内容！', 'warning');
			}
		}
		//点击增加记录
		function dbaddrecord(){
			removereadonly();
			$('#upbutton').linkbutton('enable');		//启用按钮
			//清空form 表单
			$('#form_back_register_detail_south_id').form('clear');
			//$('#sstf_id').combobox('select','<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>');
			$('#d241').val(getSystemTime2());
			$('#d242').timespinner('setValue','<%=new SimpleDateFormat("HH:mm:ss").format(new Date())%>');  
		}
		//点击修改记录
		function dbeditrecord(){
			$('#upbutton').linkbutton('enable');		//启用按钮
			removereadonly();
		}
		
		//点击保存按钮
		function saveAllInfo(a){
			var form =  $('#form_carlost_infor_detail_id').form();
			 var rows=$("#datagrid_back_register_detail_id").datagrid('getRows');
			if(rows==null||rows.length==0){
			 	alert("缺少记录，不能保存！");
			 	return;
			 }
		  	var data=$("#datagrid_back_register_detail_id").datagrid('getData');
		  	//来店合计
		  	var i=data.rows.length;
		  	$('#total').val(i);
	
			var formvalue = serializeObject(form);
			var effectRow = getChangeRows($('#datagrid_back_register_detail_id'));
				//为新增
				if(a==1){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'backRegisterAction!saveRecord.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
							   //汇总保存成功后 在把明细信息传递给后台保存
							  if(effectRow){
							   $.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: 'backRegisterAction!saveDetailAll.action',
								   data: effectRow,
								   success: function(r){
									   if(r.success){
									   	$('#datagrid_back_register_id').datagrid('load');
									   	$('#addbut').empty();
										$('#tab_back_register_id').tabs('select','登记表汇总');
										
										addreadonly();
										
									   }
								   }
								});
							}
						   }
						   	$('#datagrid_back_register_id').datagrid('load');
						   	$('#editbut').empty();
						   	$('#tab_back_register_id').tabs('select','登记表汇总');
						   	addreadonly();
					   }
					});
				}
				//修改
				if(a==2){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'backRegisterAction!saveEditRecord.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
							   //汇总保存成功后 在把明细信息传递给后台保存
							   if(effectRow){
							   $.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: 'backRegisterAction!saveEditDetailAll.action',
								   data: effectRow,
								   success: function(r){
									   if(r.success){
									   	$('#datagrid_back_register_id').datagrid('load');
									   	$('#editbut').empty();
									   	
									   	$('#tab_back_register_id').tabs('select','登记表汇总');
									   	addreadonly();
									   }
								   }
								});
								}
						   	}
						   	$('#datagrid_back_register_id').datagrid('load');
						   	$('#editbut').empty();
						   	$('#tab_back_register_id').tabs('select','登记表汇总');
						   	addreadonly();
					   }
					});
				}
			}
		
		//添加只读属性
		function addreadonly(){
			 $('#form_back_register_detail_south_id').find('input').attr("readonly","readonly");
			 	 $("#form_back_register_detail_south_id input.easyui-combobox").combobox('disable');
				$("#form_back_register_detail_south_id input").prop("disabled", true);
				$("#form_back_register_detail_south_id select").prop("disabled", true);
				$("#form_back_register_detail_south_id textarea").prop("disabled",true);
			//$('#car_Brand_id').combobox('disable');
			
			//$('#car_Model_id').combobox('disable');
		
			//$('#sstf_id').combobox('disable');
			
			//$('#custom_Level_id').combobox('disable');
			
			//$('#message_Channel_id').combobox('disable');
			
			//$('#talk_Way_id').combobox('disable');
			
			//$('#testdrive_Model_id').combobox('disable');
		}
		//删除只读属性
		function removereadonly(){
			 $('#form_back_register_detail_south_id').find('input').removeAttr("readonly");
				 $("#form_back_register_detail_south_id input.easyui-combobox").combobox('enable');
				$("#form_back_register_detail_south_id input").prop("disabled", false);
				$("#form_back_register_detail_south_id select").prop("disabled", false);
				$("#form_back_register_detail_south_id textarea").prop("disabled",false);
			 
			//$('#car_Brand_id').combobox('enable');
			
			//$('#car_Model_id').combobox('enable');
		
			//$('#sstf_id').combobox('enable');
			
			//$('#custom_Level_id').combobox('enable');
			
			//$('#message_Channel_id').combobox('enable');
			
			//$('#talk_Way_id').combobox('enable');
			
			//$('#testdrive_Model_id').combobox('enable');
		}
		//放弃跟踪 //backRegisterAction!doTurnin/doAbandonAttention
		function doAbandonAttention(id){
			$.messager.confirm('优亿软件提示','请确认是否将该记录设置为放弃跟踪记录！',function(b){
				if(b){
					//发送请求
					$.ajax({
						type : 'POST',
						url : 'backRegisterAction!doAbandonAttention.action?tracing_State='+id,
						data : '',
					    dataType : 'json',
						success : function(r){
							
						}
				   	});
				}
			});
		}
		//转入跟踪系统
		function doTurnin(){
		var value = $('#datagrid_back_register_detail_id').datagrid('getSelections');
		if(value.length>0){
			$.messager.confirm('优亿软件提示','请确认是否将该记录转为潜在客户中！',function(b){
				if(b){
					//发送请求
					$.ajax({
						type : 'POST',
						url : 'backRegisterAction!doTurnin.action',
						data : value[0],
					    dataType : 'json',
						success : function(r){
							if(r.success){
								alertMsg('转入成功！', 'info');
							}
						}
				   	});
				}
			});
			}else{
				alertMsg('请选择登记信息！', 'warning');
			}
		}
		//导出
		function doExport(){
			if(tbtitle=="登记表汇总"){
				exportEsuyUIExcelFile('datagrid_back_register_div',null,"登记表汇总"+getSystemTime());
			}if(tbtitle=="登记表明细"){
				exportEsuyUIExcelFile('datagrid_back_register_detail_div',null,"登记表明细"+getSystemTime());
			}
		}
		
    </script>
  </head>
  		<body>
  		
  		<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="_add" onclick="dbAddbutton();">新增</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  id="_remove" onclick="deleteRecord();">删除</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="_update"  onclick="dbEditbuttom();">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true"  id='_search' onclick="doConditionSubmit($('#form_back_register_id'),$('#datagrid_back_register_id'),$('#register_Date'),$('#register_Date2'));">查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id='_clear' onclick="doClear($('#form_back_register_id'),$('#datagrid_back_register_id'),$('#register_Date'),$('#register_Date2'));">清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id='_export' onclick="doExport();" >导出</a>
		<span id="addbut"></span>
		<span id="editbut"></span>
		</div>
		<div region="center" style="background:#eee;"  border="false">
		
		<div id="tab_back_register_id" class="easyui-tabs" fit="true">
		
		<div title="登记表汇总">
			<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:60px;" border="false">
				<form id="form_back_register_id">
				<fieldset>
					<legend>查询条件</legend>
					<table style="text-align: right">
							<tr>
								<td>登记日期：</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'register_Date2\',{d:-1})}'})" name="register_Date" id="register_Date" style="width: 85px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'register_Date\',{d:1})}'})" name="register_Date2" id="register_Date2" style="width: 85px;"/></td>
								<td>经办人：</td>
								<td>
									<input name="stf_Id"
									class="easyui-combobox"	data-options="
									url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
									valueField:'id',  
									textField:'text',
									multiple:false,
									mode:'remote'  "
									/>
								</td>
							</tr>
					</table>
					</fieldset>
				</form>		
				</div>
				<div id="datagrid_back_register_div" region="center" border="false" >
					<table id="datagrid_back_register_id"></table>
				</div>
			</div>
		</div>
		<div title="登记表明细" >
		 <div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		 <div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:62px;" border="false">
	
			<form id="form_carlost_infor_detail_id">
			<fieldset>
				<legend>来电（店）客流登记明细信息</legend>
				<table style="text-align: right">
					<tr>
						<td>登记日期：</td>
						<td><input type="text" id="systime" readonly="readonly"  name="register_Date" style="width: 85px;"/></td>
						<td>登记单号：</td>
						<td><input type="text" name="xs_Custom_Salesman_Id" style="width: 130px" readonly="readonly"/></td>
						<td>经办人姓名：</td>
						<td>
						<input type="text" id="detail_stf_Id" name="stf_Id" class="easyui-combobox"  
						 style="width: 140px;"
						data-options="
						disabled:true,
						url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						required : true,
						valueField:'id',  
						textField:'text',
						mode:'remote'
						"/>
						</td>
						<td>来店合计：</td>
						<td><input type="text" readonly="readonly" id="total" name="total"/></td>
						<td>备注：</td>
						<td><input style="width:300px;" name="remark" /></td>
					</tr>
				</table>
				</fieldset>
			</form>
			</div>
			<div id="datagrid_back_register_detail_div" region="center" style="background:#eee;" border="false">
				<table id="datagrid_back_register_detail_id"></table>
			</div>
			 <div data-options="region:'south',collapsible : false" style="overflow: hidden;padding:5px; background:#eee; height:200px;" border="false">
				<form id="form_back_register_detail_south_id">
					<input type="hidden" name="register">
					<input type="hidden" name="register_State">
				<table style="text-align: right">
					<tr>
						<td style="display: none;"><input name="xs_Custom_Salesman_Detail_Id" /></td>
						<td style="display: none;"><input name="xs_Custom_Salesman_Id" /></td>
						<td>进店时间：</td>
						<td> <input type="text" id="d241" name="register_Date"  style="width:140px;" data-options="required:true" readonly="readonly" /></td>
						<td>离店时间：</td>
						<td> <input id="d242" name="exit_Date"  class="easyui-timespinner"  style="width:140px;"
								  data-options="showSeconds:true,missingMessage:'该项为必填项',onSpinUp:function(){
								  		var hours=$('#d242').timespinner('getHours');
								  		var tag1=false;
								  		var tag2=false;
								  		var tag3=false;
								  		var value=null;
								  		if(hours!=null&&(hours==23||hours==00)){
								  			tag1=true;
								  			value='23';
								  		}
								  		var minutes=$('#d242').timespinner('getMinutes');
								  		if(tag1&&minutes!=null&&(minutes==59||minutes==00)){
								  			tag2=true;
								  			value+=':59';
								  		}else{
								  			value+=':'+minutes;
								  		}
								  		var seconds=$('#d242').timespinner('getSeconds');
								  		if(tag2&&seconds!=null&&(seconds==59||seconds==00)){
								  			tag3=true;
								  			value+=':59';
								  		}else{
								  			value+=':'+seconds;
								  		}
								  		if(value!=null){
								  			$('#d242').timespinner('setValue',value);
								  		}
								  },onSpinDown:function(){
								  		var hours=$('#d242').timespinner('getHours');
								  		var minutes=$('#d242').timespinner('getMinutes');
								  		var seconds=$('#d242').timespinner('getSeconds');
								  		if(parseInt(seconds)<10){
								  			seconds='0'+seconds;
								  		}
								  		var value=$('#d241').val();
								  		var	hourss=value.substring(11,13);
								  		var	minutess=value.substring(14,16);
								  		var	secondss=value.substring(17);
								  		var tag1=false;
								  		var tag2=false;
								  		var tag3=false;
								  		var values=null;
								  		if(hours!=null&&(parseInt(hours)<=parseInt(hourss))){
								  			tag1=true;
								  			values=hourss;
								  		}
								  		var minutes=$('#d242').timespinner('getMinutes');
								  		if(tag1&&minutes!=null&&(parseInt(minutes)<=parseInt(minutess))){
								  			tag2=true;
								  			values+=':'+minutess;
								  		}else{
								  			values+=':'+minutes;
								  		}
								  		var seconds=$('#d242').timespinner('getSeconds');
								  		if(tag2&&seconds!=null&&(parseInt(seconds)<=parseInt(secondss))){
								  			tag3=true;
								  			values+=':'+secondss;
								  		}else{
								  			values+=':'+seconds;
								  		}
								  		if(values!=null){
								  			$('#d242').timespinner('setValue',values);
								  		}
								  		
								  }" /> </td>
						<td>男/女：</td>
						<td><input readonly="readonly" type="text"  name="man_Num" style="width: 55px"  class="easyui-numberbox" data-options="validType:'integer'" maxlength="2"/>
							<input readonly="readonly" name="woman_Num" style="width: 55px"  class="easyui-numberbox" data-options="validType:'integer'" maxlength="2"/></td>
						<td>客户姓名：</td>
						<td><input readonly="readonly"  name="custom_Name" class="easyui-validatebox" data-options="validType:'name',required:true" maxlength="10"/></td>
						<td>联系电话：</td>
						<td><input readonly="readonly" class="easyui-validatebox" maxlength="11" data-options="validType:'mobile',editable:false,required:true"  name="telephone"/></td>
					</tr>
					
					<tr>
					<td>预购品牌：</td>
						<td><input type="text" id="car_Brand_id" disabled="disabled" name="car_Brand" style="width:140px;" class="easyui-combobox" data-options="
						required:true,
						url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote',			    		
			    		validType:'isSelected[\'#car_Brand_id\']',
			    		invalidMessage : '请从下拉框中选择车辆品牌',
							onLoadSuccess : function(){
								$('this').combobox('select',$(this).combobox('getText'));
								$('#car_Brand_Name_id').val($(this).combobox('getText'));
							},
			    		onSelect: function(rec){  
				            $('#car_Model_id').combobox('clear');
				            $('#car_Model_id').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)
				            $('#car_Brand_Name_id').val(rec.text);
				            $('this').combobox('select',rec.id);
				              
				        } "
				        />
				        <input name="car_Brand_Name" id="car_Brand_Name_id" style="display: none;"/>
				        
				        </td>
					<td>预购车型:</td>
						<td><input type="text" id="car_Model_id" disabled="disabled" name="car_Model" style="width:140px;" class="easyui-combobox" 
						data-options="
						required:true,
						url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote',
			    		validType:'isSelected[\'#car_Model_id\']',
			    		invalidMessage : '请从下拉框中选择车辆型号',
							onLoadSuccess : function(){
								$('#car_Model_Name_id').val($(this).combobox('getText'));
							},
			    		onSelect:function(rec){
			    			$(this).combobox('textbox').bind('keyup', function (){
			    				if($('#car_Model_id').combobox('getValue') == '' || $('#car_Model_id').combobox('getValue') != $('#car_Model_id').combobox('getText')){
			    					$('#carArchives_add_carCstlId').combobox('reload', 'carModelAction!findAllCarModel.action');
			    				}
			    			});  
				           
								$('#car_Model_Name_id').val(rec.text);
							} 
								 "/>
				        
				        
				        <input name="car_Model_Name"  id="car_Model_Name_id" style="display: none;"/>
				        </td>
						
						<td>销售顾问：</td>
						<td>
							<input name="stf_Id" id="sstf_id" disabled="disabled"
							class="easyui-combobox"	data-options="
							required : true,
							url : '${pageContext.request.contextPath}/basStuffClassAction!findSellOperationPerson.action',
							valueField:'id',  
							textField:'text',
							multiple:false,
							mode:'remote',
							onLoadSuccess : function(){
								$('#stf_Name_id').val($(this).combobox('getText'));
							},
							onSelect : function(record){
								$('#stf_Name_id').val(record.text);
							}  "
							/>
							<input name="stf_Name" id="stf_Name_id" style="display: none;" />
						</td>
						<td>渠道：</td>
						<td>
							<input style="width:110px" disabled="disabled" id="message_Channel_id" name="message_Channel"
							class="easyui-combobox"
							data-options="
							required : true,
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_SOURCE %>',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote',
							onLoadSuccess : function(){
								$('#message_Channel_Name_id').val($(this).combobox('getText'));
							},
							onSelect : function(record){
								$('#message_Channel_Name_id').val(record.text);
							}
							"
							/>
							<input name="message_Channel_Name" id="message_Channel_Name_id" style="display: none;"/>
						</td>
						
						<td>客户级别：</td>
						 <td>
							<input style="width:110px" disabled="disabled" id="custom_Level_id"  name="custom_Level"
							class="easyui-combobox"
							data-options="
							required : true,
							url : 'customLevaAction!findAllLeva.action',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote',
							onLoadSuccess : function(){
								$('#custom_Level_Name_id').val($(this).combobox('getText'));
							},
							onSelect : function(record){
								$('#custom_Level_Name_id').val(record.text);
							} 
							"
							/>
							<input name="custom_Level_Name" id="custom_Level_Name_id" style="display: none;"/> 
						</td>
					</tr>
					<tr>
				
						<td>试乘试驾车型：</td>
						<td>
							<input  id="testdrive_Model_id" disabled="disabled" name="testdrive_Model" style="width:140px;" class="easyui-combobox"
							data-options="url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
								valueField:'id',   
								textField:'text',
								mode : 'remote',
								validType:'isSelected[\'#testdrive_Model_id\']',
								invalidMessage : '请从下拉框中选择车辆型号',
								onLoadSuccess : function(){
								$('#testdrive_Model_Name_id').val($(this).combobox('getText'));
							},
								onSelect : function(record){
								$('#testdrive_Model_Name_id').val(record.text);
							} 
								" />
							<input name="testdrive_Model_Name" id="testdrive_Model_Name_id" style="display: none;"  />
						</td>
						<td>方式：</td>
						<td>
							<input style="width:140px" disabled="disabled" id="talk_Way_id" name="talk_Way"
							class="easyui-combobox"
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.VISIT %>',
							required:true,
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote',
							onLoadSuccess : function(){
								$('#talk_Way_Name_id').val($(this).combobox('getText'));
							},
							onSelect : function(record){
								$('#talk_Way_Name_id').val(record.text);
							}
							"
							/>
							<input name="talk_Way_Name" id="talk_Way_Name_id"  style="display: none;"/>
						
						</td>
						<td>洽谈内容：</td>
						<td colspan="5"><input style="width:500px;" readonly="readonly" type="text"  name="talk_Content" class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,50]\']'"/></td>
					</tr>
					<tr>
						
						<td>备注：</td>
						<td colspan="9"><textarea style="width :930px; resize : none;"    name="remark" class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,100]\']'"></textarea></td>
						
						
					</tr>
				</table>
				<br/>
				
				<hr/>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" disabled="true" id="addbutton"   onclick="dbaddrecord();">增加记录</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" disabled="true" id="editbutton"  onclick="dbeditrecord();">修改记录</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" disabled="true" id="deletebutton"   onclick="deleteDetailInfo();">删除记录</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  disabled="true" id="upbutton"  onclick="upLoad();">上传</a>
				
			</form>
			</div>
	  	</div>
		</div>
	</div>
	</div>
	</div>	
  </body>
</html>
