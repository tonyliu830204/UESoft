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
    <title>车辆维护管理</title>
   <script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		   

	</script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellServicing/sellServicing.js"></script>
  </head>
  		<body>
  		<div class="easyui-layout" style="width:900px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:32px;" border="false">
			<privilege:enable code="SELLSERVIC_ADD">
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="_add" onclick="addServicing();">新增</a>
			</privilege:enable>
			<privilege:enable code="SELLSERVIC_SEARCH">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="_search" onclick="queryServicing();">查询</a>
			</privilege:enable>
			<privilege:enable code="SELLSERVIC_CLEAR">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id="_clear" onclick="clearSearchCondition();">清空</a>
			</privilege:enable>
			<privilege:enable code="SELLSERVIC_PRINT">
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" id="_print" plain="true" onclick="_config();">打印</a>
			</privilege:enable>
			<privilege:enable code="SELLSERVIC_EXPORT">
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" id="_export" plain="true" onclick="_except();">导出</a>
			</privilege:enable>
			<!--<privilege:enable code="SELLSERVIC_IMPORT">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" id="_import" plain="true">导入</a>
			</privilege:enable>
			--><span id="saveOrCancelBtn"></span>
		</div>
		<div region="center" style="background:#eee;"  border="false">
		
		 <div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		 <div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:62px;" border="false">
			
			<form id="form_should_gathering_manage_id">
			<fieldset>
				<legend>查询条件</legend>
				<table style="text-align: right">
					<tr>
						<td>预计维护日期:</td>
						<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'servicingNextdate2\',{d:-1})}'})" name="servicingNextdate" id="servicingNextdate" style="width:95px;"/> 至 </td>
						<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'servicingNextdate\',{d:1})}'})" name="servicingNextdate2" id="servicingNextdate2" style="width: 95px;"/></td>
						<td>VIN号:</td>
						<td><input style="width:120px" name="xsCarVinNumber" maxlength="17" /></td>
					</tr>
				</table>
				</fieldset>
			</form>
			</div>
			<div id="servicingData_div" region="center" style="background:#eee;" border="false">
				<table id="servicingData"></table>
			</div>
			<div id="servicingProject_detail_div" region="south" title="维护项目" style="background:#eee;height:300px;" border="false" animate="true"  col>
				<table id="servicingProject"></table>
			</div>
	  	</div>  	
	</div>
	</div>
	
  </body>
</html>
