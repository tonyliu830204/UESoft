<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>跟踪记录查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"	src="${pageContext.request.contextPath}/sell/sell_financemanage/distributor_bill_Manage.js"></script>
		 <script type="text/javascript">
		    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		    
		var sgsm_d1;
		function adddis()
		{
		 sgsm_d1 = $('<div/>');
		 sgsm_d1.dialog({
			title: '请选择',   
		    width:650,   
		    height:400,
		    cache: false,   
		    href: '${pageContext.request.contextPath}/sell/sell_financemanage/addDistributor.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		      }
		   });
		}
    
			
		    </script>

	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',border:false"
			style="padding: 1px; height: 27px; overflow: hidden; background: #eee;">
			<a id='_add' href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-add" plain="true"  onclick="addPersonnel(1);">新增</a>
			<a id='_remove' href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true"  onclick="remove_()" >删除</a>
			<a id='_update' href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"   onclick="addPersonnel(2);">修改</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="queryReserve();">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true"  id="_print" onclick="_config();">打印</a>
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" id="_export" onclick="_except();">导出</a>
			<span id="saveOrCancelBtn"></span>
		</div>
		<div data-options="region:'center',border:false"
			style="background: #eee;">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',title:'查询条件',border:false"
					style="overflow: hidden; background: #eee; height: 80px;">
					<form id="queryForm" name="carModelQueryForm" method="post"
						fit="true">
						 <fieldset>
						<table>
							<tr>
								<td>
								预购日期:
								</td>
								<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'zhProjectDate2\',{d:-1})}'})" name="predict_Buy_Date" id="zhProjectDate1" style="width: 110px;"/> 至 
									<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'zhProjectDate1\',{d:1})}'})" name="predict_Buy_Date" id="zhProjectDate2" style="width: 110px;"/></td>
								
								<td>
									分销商：
								</td>
									<td><input id="dName" name="xsDistributorName" style="background-color:#c0d8d8; width: 200px";" onkeypress=" if(event.keyCode==13) { adddis(); return false;}"  style="background-color:#c0d8d8;"/>
									<img id="imgid2" src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="adddis();"/>
									<input name="allocateId" id="did" type="hidden"  />
								
								</tr>
								
								
								
						</table>
						</fieldset>
					</form>
				</div><blockquote><br>
				</blockquote>
				<div  id="acc" data-options="region:'center',border:false"
					style="background: #eee;">
					 <table id="account" name="account"></table> 
				</div>
				<div region="east"  style="background:#eee; width:420px;" border="false" >
			      <form id="queryForm" name="carModelQueryForm" method="post"
						fit="true">
						
						<table>
							<tr>
								
								
								<td>
								预购日期:
								</td>
								<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'zhProjectDate2\',{d:-1})}'})" name="predict_Buy_Date" id="zhProjectDate1" style="width: 110px;"/> 至 
									<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'zhProjectDate1\',{d:1})}'})" name="predict_Buy_Date" id="zhProjectDate2" style="width: 110px;"/></td>
								
								<td>
									分销商：
								</td>
									<td><input id="dName" name="xsDistributorName" />
									
								
								
								</tr>
								
								
								
						</table>
						
					</form>
		        </div>
				<div data-options="region:'south',border:false" title="跟踪记录" style="background: #eee;height: 300px">
					 <table id="carBrand" name="carBrand"></table>
				</div>
		</div>
		</div>
		
	</body>
</html>
