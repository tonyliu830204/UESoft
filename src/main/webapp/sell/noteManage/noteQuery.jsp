<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>短信发送查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"	src="${pageContext.request.contextPath}/sell/noteManage/noteQuery.js"></script>
		 <script type="text/javascript">
		    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		    
		  	var sgsm_d1;
			function adddis2()
			{
			 sgsm_d1 = $('<div/>');
			 sgsm_d1.dialog({
				title: '请选择',   
			    width: 650,   
			    height:400,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/allocateManage/addDistributor2.jsp',
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
			<privilege:enable code="NOTEQUERY_SEARCH">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="queryReserve();">查询</a>
			</privilege:enable>
			<privilege:enable code="NOTEQUERY_CLEAR">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
			</privilege:enable>
			<privilege:enable code="NOTEQUERY_PRINT">
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true"  id="_print" onclick="_config();">打印</a>
			</privilege:enable>
			<privilege:enable code="NOTEQUERY_EXPORT">
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" id="_export" onclick="_except();">导出</a>
		</privilege:enable>
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
								<td width="75px">
									发送时间:
								</td>
								<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'sms_date2\',{d:-1})}'})" name="sms_date" id="sms_date1" style="width: 110px;"/> 至 
									<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'sms_date1\',{d:1})}'})" name="sms_date2" id="sms_date2" style="width: 110px;"/></td>
								<td>客户姓名:</td>
									<td ><input type="text" name="custom_Name"  style="width:120px;">   
										</td>
								<td>
								电话号码:
								</td>
								<td>
									<input type="text" name="telephone" style="width: 120px">
															
								</td>
								<td >
									业务员:
								</td>
								<td>

									<input name="sft_id" class="easyui-combobox"
										readonly="readonly" style="width: 125px;"
										data-options="url : 'sellUtilAction!findUsers.action',
										valueField:'id',  
										textField:'name',
										mode:'remote',
										multiple:false " />
				
								</tr>
						</table>
						</fieldset>
					</form>
				</div><blockquote><br></blockquote><div  id="acc" data-options="region:'center',border:false"
					style="background: #eee;">
					 <table id="noteList" name="noteList"></table> 
				</div>
				
				<div data-options="region:'south',border:false"style="background: #eee;height: 152px">
				<form id="sumqu">
				<table style="color:red;">
				<tr>
				<td style="width:220px;"><div style="font-size:16;color:red;">短信内容:</div></td>
				<td > <textarea name="sms" style="width: 450px ; height: 130px;resize:none;" readonly="readonly"></textarea>
				
				</td>
				</tr>
				</table>
				</form>
				
				</div>
			</div>
		</div>
	</body>
</html>
