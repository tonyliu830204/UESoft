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
    <title>客流时间段分析</title>
    <script type="text/javascript">
		$(function (){
			//初试时间
			addInitDate($('#register_Date'),$('#register_Date2'));
			//初始化
			$('#datagrid_center_register_time_assay_id').datagrid({
			url : '',
			type : 'POST',
			newrap : false,
			fitColumns : true,
			fit : true,
			width : 1024,
			height : 300,
			pageSize : 30,
			remoteSort : false,
			singleSelect : true,
			frozenColumns : [[{field : 'registerdate',title : '登记日期',width : 100,sortable : true, align : 'center'}]],
			onLoadSuccess : function(data){
			if(data.total == '0'){
					var body = $(this).data().datagrid.dc.body2;
					body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="6">暂无满足相关条件的记录！</td></tr>');
					}
				}
			});
		});
	
		var recordArray = new Array();
		var s = "";
		var ss = "";
		var options = {};
	
		//客户满意度统计添加不固定列方法
		function addColome(resault){
			var str1 ="[[";
			var str2 = "";
			var array = new Array();
			array = resault.substring(1,resault.indexOf("]")).split(",");
			for ( var i = 0; i < array.length; i++) {
			var str = array[i].substring(1,array[i].length-1);
					var newss = str.replace(':', '').replace('-', '').replace(':', '');
					str2 += ",{title : '"+str+"',field : 'count"+newss+"',width : 100,sortable : true, align : 'center'} ";
			}
					str2 += ",{title : '合计',field : 'counttotal',width : 100,sortable : true, align : 'center'} ";
			var newstr2 = str2.substring(1);
			
			var str3 = "]]";
			var laststr = str1 + newstr2 + str3;
				 options = {};
				 options.url = '';
				 options.columns = eval(laststr);
					 $('#datagrid_center_register_time_assay_id').datagrid(options);
				return true;	 
			}
	
		//条件查询
		function doFindbyCondition(datagrid,formid,hours,dateId1,dateId2){
			var form =  formid.form();
			var formvalue = serializeObject(form);
			//初始化时间
			addInitDate(dateId1,dateId2);
			//同时访问 后台getName方法筛选出需要的列
				$.ajax({
				type : 'POST', 
				url : 'backRegisterAction!doTimeAssay.action?hours='+hours,
				data : formvalue,
				success : function(rest){
						var rest = rest;
						var t = addColome(rest);
						if(t){
							$.ajax({
							type : 'POST', 
							url : 'backRegisterAction!getTimeAssay.action?hours='+hours,
							data : formvalue,
							dataType : 'json',
							success : function(resault){
							var obj =JSON.parse(resault);
							//$('#datagrid_center_register_time_assay_id').datagrid('loadData',{total:0,rows:[]});
							$('#datagrid_center_register_time_assay_id').datagrid('loadData',obj);			
						}
					});
				}
			}
		});
		}
		//清空form表单
		var doFormClear = function(dateId1,dateId2){
			$('#form_register_time_assay_id').form('clear');
			doFindbyCondition($('#datagrid_center_register_time_assay_id'),$('#form_register_time_assay_id'),120,$('#register_Date'),$('#register_Date2'));
		}
		
		//导出
		function _except(){
		var data =  $("#datagrid_center_register_time_assay_id").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
			showEditDialog("datagrid_center_register_time_assay_id",null,"datagrid_center_register_time_assay_div","开始导出","导出配置",0,_callbackExcept);
		
		}
		/**
		* 导出excel回调函数
		* 将筛选出的列导出到Excel文件
		* 只支持Microsoft Office,不支持WPS
		* @param parentId
		* @param fieldNames  要导出的列字段
		* @return
		*/
		function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"客流时间段分析"+getSystemTime());
		}
		/**
		* 打印字段设置
		* 编辑、选择不同分组
		* @return
		*/
		function _config(){
		var data =  $("#datagrid_center_register_time_assay_id").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
			showEditDialog("datagrid_center_register_time_assay_id",null,"datagrid_center_register_time_assay_div","开始打印","打印配置",2,_print);		
		
		}
		/**
		* 打印字段设置回调函数
		* 将选择的列打印
		* @return
		*/
		function _print(parentId,fieldNames){
			printEsuyUIPreview(parentId,fieldNames);
		}
		
    </script>
  	</head>
  		<body>
  		<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
		<a href="javascript:void(0)" id="mb" class="easyui-menubutton" data-options="menu:'#mm',iconCls:'icon-search'" >查询</a>
		
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id='_clear' onclick="doFormClear($('#register_Date'),$('#register_Date2'));">清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" id='_print' onclick="_config();" >打印</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id='_export' onclick="_except();" >导出</a>
		</div>
		<div id="mm" style="width:150px;">
		<div onclick="doFindbyCondition($('#datagrid_center_register_time_assay_id'),$('#form_register_time_assay_id'),30,$('#register_Date'),$('#register_Date2'));">30分钟段统计</div>
		<div onclick="doFindbyCondition($('#datagrid_center_register_time_assay_id'),$('#form_register_time_assay_id'),60,$('#register_Date'),$('#register_Date2'));">60分钟段统计</div>
		<div onclick="doFindbyCondition($('#datagrid_center_register_time_assay_id'),$('#form_register_time_assay_id'),90,$('#register_Date'),$('#register_Date2'));">90分钟段统计</div>
		<div onclick="doFindbyCondition($('#datagrid_center_register_time_assay_id'),$('#form_register_time_assay_id'),120,$('#register_Date'),$('#register_Date2'));">120分钟段统计</div>
		</div>
		<div region="center" style="background:#eee;"  border="false">
		
			<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:90px;" border="false">
				<form id="form_register_time_assay_id">
				<fieldset>
					<legend>查询条件</legend>
					<table style="text-align: right">
						<tr>
							<td>來店日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'register_Date2\',{d:-1})}'})" name="register_Date" id="register_Date" style="width: 85px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'register_Date\',{d:1})}'})" name="register_Date2" id="register_Date2" style="width: 85px;"/></td>
							
							<td>业务员:</td>
							<td>
								<input name="stf_Id" style="width:140px;"
								class="easyui-combobox"	data-options="
								url : 'sellUtilAction!findUsers.action',
								valueField:'id',  
								textField:'name',
								multiple:false,
								mode:'remote'  "
								/>
							</td>
							<td>客户名称:</td>
							<td><input name="custom_Name"/></td>
							<td>方式:</td>
							<td>
							<input id="talk_Way_id" name="talk_Way"
							class="easyui-combobox"
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.VISIT %>',
							valueField:'id',  
							textField:'text',
							multiple:false ,
							mode:'remote'"
							/>
							</td>
						</tr>
						<tr>
						<td>车辆品牌:</td>
						<td colspan="2"><input type="text" id="car_Brand_id" name="car_Brand" style="width:190px;" class="easyui-combobox" data-options="
						url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote',			    		
			    		validType:'isSelected[\'#car_Brand_id\']',
			    		invalidMessage : '请从下拉框中选择车辆品牌',
							onLoadSuccess : function(){
								$('#car_Brand_Name_id').val($(this).combobox('getText'));
							},
			    			onSelect: function(rec){  
			    			$(this).combobox('textbox').bind('keyup', function (){
			    				if($('#car_Brand_id').combobox('getValue') == '' || $('#car_Brand_id').combobox('getValue') != $('#car_Brand_id').combobox('getText')){
			    					$('#car_Model_id').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
			    				}
			    			});
				            $('#car_Model_id').combobox('clear');
				            $('#carArchives_add_carCstlId').combobox('clear');   
				            $('#car_Model_id').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
				        } "
				        />
				        <input name="car_Brand_Name" id="car_Brand_Name_id" style="display: none;"/>
				        
				        </td>
						<td>车辆型号:</td>
						<td><input type="text" id="car_Model_id" name="car_Model" style="width:140px;" class="easyui-combobox" 
						data-options="
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
				        <td>渠道:</td>
						<td>
							<input style="width:110px" id="message_Channel_id" name="message_Channel"
							class="easyui-combobox"
							data-options="
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
						
						<td>客户级别:</td>
						 <td>
							<input style="width:110px" id="custom_Level_id"  name="custom_Level"
							class="easyui-combobox"
							data-options="
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
							}"
							/>
							<input name="custom_Level_Name" id="custom_Level_Name_id" style="display: none;"/> 
						</td>
						</tr>
					</table>
					</fieldset>
				</form>		
				</div>
				<div id="datagrid_center_register_time_assay_div" region="center" border="false" >
					<table id="datagrid_center_register_time_assay_id"></table>
				</div>
			</div>
		</div>
	</div>	
  </body>
</html>
