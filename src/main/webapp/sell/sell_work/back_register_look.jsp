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
    <title>来电客流登记浏览</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/sell_work.js"></script>
    <script type="text/javascript">
		
		//放弃跟踪 
		function doAbandonAttention(index){
	
			$.ajax({
				type : 'POST',
				url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ABANDON_REASON %>',
				data : '',
			    dataType : 'json',
				success : function(r){
					if(!$('#mm').children().length>0){
						for ( var i = 0; i < r.length; i++) {
							$('#mm').append('<div><a href="javascript:void(0);" onclick="abandonok('+r[i].id+');">'+r[i].text+'<a/><div/>');
						}
					}
						$('#abandonfollowid'+index).menubutton({
							menu : '#mm'
						});
				}
		   	});
		}
		//确认放弃
		var abandonok = function(id){
			var value = $('#datagrid_back_register_look_id').datagrid('getSelections');
			$.messager.confirm('优亿软件提示','请确认是否将该记录设置为放弃跟踪记录！',function(b){
				if(b){
					//发送请求
					$.ajax({
						type : 'POST',
						url : 'backRegisterAction!doAbandonAttention.action?abandreasonid='+id,//
						data : value[0],
					    dataType : 'json',
						success : function(r){
							$('#datagrid_back_register_look_id').datagrid('reload');
							//销毁div
							//刷新tab
							FlushTab();
						}
				   	});
				}
			});
			
		}
		var FlushTab = function(){
			// 刷新
			$('#tm-tabupdate').click(function() {
				var currTab = $('#tabs').tabs('getSelected');
				var url = $(currTab.panel('options').content).attr('src');
				if(url != undefined && currTab.panel('options').title != 'Home') {
					$('#tabs').tabs('update', {
						tab : currTab,
						options : {
							content : createFrame(url)
						}
					})
				}
			})
		}
		
		//转入跟踪系统
		function doTurnin(){
		var value = $('#datagrid_back_register_look_id').datagrid('getSelections');
			$.messager.confirm('优亿软件提示','请确认是否将该记录转为潜在客户中！',function(b){
				if(b){
					//发送请求
					$.ajax({
						type : 'POST',
						url : 'backRegisterAction!doTurnin.action',
						data : value[0],
					    dataType : 'json',
						success : function(r){
							$('#datagrid_back_register_look_id').datagrid('reload');
						}
				   	});
				}
			});
		}
		
    </script>
  	</head>
  		<body>
  		
  		<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div region="center" style="background:#eee;"  border="false">
		<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:55px;" border="false">
			<form id="form_back_register_look_id">
				<fieldset style="height: 35px;">
					<legend>查询条件</legend>
					<table style="text-align: right">
							<tr>
								<td>登记日期：</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'register_Date2\',{d:-1})}'})" name="register_Date" id="register_Date" style="width: 85px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'register_Date\',{d:1})}'})" name="register_Date2" id="register_Date2" style="width: 85px;"/></td>
								<td>业务员：</td>
								<td>
									<input name="stf_Id"
									class="easyui-combobox"	data-options="
									url : 'basStuffClassAction!findSellOperationPerson.action',
									valueField:'id',  
									textField:'text',
									multiple:false,
									mode:'remote'  "
									/>
								</td>
								<td>
									<a href="javascript:void(0);"  class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit($('#form_back_register_look_id'),$('#datagrid_back_register_look_id'),$('#register_Date'),$('#register_Date2'));">查询</a>
									<a href="javascript:void(0);"  class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id='_clear' onclick="doClear($('#form_back_register_look_id'),$('#datagrid_back_register_look_id'),$('#register_Date'),$('#register_Date2'));">清空</a>
								</td>
							</tr>
					</table>
					</fieldset>
				</form>		
			</div>
			<div id="datagrid_back_register_look_div" region="center" style="background:#eee;" border="false">
				<table id="datagrid_back_register_look_id"></table>
			</div>
	  	</div>
	</div>
	</div>
	<div id="mm" style="width:120px;"></div>
	<div><font ></font></div>
  </body>
</html>
