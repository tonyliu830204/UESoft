<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
		<title>跟踪统计</title>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/return_visit/customer_gztj.js"></script>
	</head>
	<body>
		<div class="easyui-layout" border="false" fit="true" style="width: 800px; height: 600px;">
				<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:0px; background:#eee; height:30px;" border="false">
						<privilege:enable code="CUSTOMSTATISTICSQUERY">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit();">查询</a>
						</privilege:enable>
						<privilege:enable code="CUSTOMSTATISTICSCLEAR">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear($('#customer_GzTj_form_id'));">清空</a>
						</privilege:enable>
						<privilege:enable code="CUSTOMSTATISTICSPRINT">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
						</privilege:enable>
						<privilege:enable code="CUSTOMSTATISTICSEXPORT">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
						</privilege:enable>
						<privilege:enable code="CUSTOMSTATISTICSSET">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-help" plain="true">设置</a>
						</privilege:enable>
				</div>
				<div region="center" border="false">
				<div class="easyui-layout" border="false" fit="true" style="width: 800px; height: 600px;">
				<div data-options="region:'north',title:'查询条件',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:65px;" border="false">
								<form id="customer_GzTj_form_id" method="post">
									<table border="0" style="text-align: right;" >
										<tr>
											<td>回访日期：</td>
											<td >	
												<input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'returnVisitDate2\',{d:-1})}'})" name="returnVisitDate" id="returnVisitDate" style="width:85px"/> 至 
												<input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'returnVisitDate\',{d:1})}'})" name="returnVisitDate" id="returnVisitDate2" style="width:85px"/>
											</td>
											<td >车品牌：</td>
											<td>
												<input style="width:100px"  id="cbrdName" name="cbrdName" class="easyui-combobox"
												data-options="
												url : 'vTrackRecordAction_getCarbrand.action',
												valueField:'id',  
												textField:'name',
    											multiple:false  "/>&nbsp;&nbsp;
											</td>
											<td >接待员：</td>
											<td>
												<input style="width:100px"  id="stfName" name="stfName" class="easyui-combobox"
												data-options="
												url : 'vTrackRecordAction_getStuff.action',
												valueField:'id',  
												textField:'text',
    											multiple:false  "/>&nbsp;&nbsp;
											</td>
											<td >车间部门：</td>
											<td>
												<input style="width:100px"  id="repgrpName" name="repgrpName" class="easyui-combobox"
												data-options="
												url : 'vTrackRecordAction_getPartment.action',
												valueField:'id',  
												textField:'name',
    											multiple:false  "/>&nbsp;&nbsp;
											</td>
											<td>项目选择：</td>
											<td><input style="width:300px"  id="serveyName" name="serveyName" class="easyui-combobox"
												data-options="
												url : 'vTrackRecordAction_getReturnSeverName.action',
												valueField:'id',  
												textField:'name',
    											multiple:true  "/>&nbsp;&nbsp;
    										</td>
										</tr>
									</table>
								</form>
							</div>
				<div region="center" split="false" border="false" style="height: 90px; background : #eee">
				<div class="easyui-tabs" style="width: 800px; height: 600px;" border="false" id="tt"
					fit="true">
					
						<div title="跟踪记录汇总"  border="false" id="ccid">
						<div class="easyui-layout" border="false" fit="true"
							style="width: 1200px; height: 600px;">
							<div region="center" border="false">
								<table id ="genzongjiluhuizong_tb_id"></table>
							</div>
						</div>
					</div>
					
					<div title="客户满意度统计"  border="false">
						<div class="easyui-layout" border="false" fit="true"
							style="width: 800px; height: 600px;">
							<div region="center" border="false">
								<table id ="satisfaction_degree_id"></table>
							</div>
						</div>
					</div>
					<div title="客户跟踪项目统计"  border="false">
						<div class="easyui-layout" border="false" fit="true"
							style="width: 800px; height: 600px;">
							<div region="center" border="false">
								<table id="customer_statistics2"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
	</body>
</html>
