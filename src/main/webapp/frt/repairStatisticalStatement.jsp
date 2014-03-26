<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>维修业务统计报表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/repairStatisticalStatement.js"></script>
  </head>

<body>
	<div class="easyui-layout" fit="true" border="false">
		<div region="north"  split="false" style="height:30px;background: #eee;" border="false">
	    <privilege:enable code="SERVICEOPERATIONSTAT_SEARCH">
	      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryFrtWorkOrderRepaiStatisticalStatement();">查询</a>
	   </privilege:enable>
		<privilege:enable code="SERVICEOPERATIONSTAT_CLEAR">
	      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearFrtWorkOrderRepaiStatisticalStatement();">清空</a>
	   </privilege:enable>
<%--		<privilege:enable code="SERVICEOPERATIONSTAT_PRINT">--%>
<%--	      	<a id="_print" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">套打模板</a>--%>
<%--	   </privilege:enable>--%>
<%--		<privilege:enable code="SERVICEOPERATIONSTAT_SET">--%>
<%--	      	<a id="_set" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-set" plain="true">打印设置</a>--%>
<%--	   </privilege:enable>--%>
		<privilege:enable code="SERVICEOPERATIONSTAT_EXPROT">
	      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
	   </privilege:enable>
	    </div>  
		<div region="center" style="background:#eee;" border="false">    
			<div class="easyui-layout" style="overflow: hidden;" data-options="fit:true,border:false">
				<div style="background:#eee;height:115px;padding:3px;" border="false" data-options="region:'north',title:'查询条件',border:false">
					<form id="frtWorkOrderRepaiStatisticalStatementQueryForm">
						<table>
							<tr>
								<td>进厂时间:</td>
								<td>
								<input type="text" id="interDateBegin" name="interDateBegin" style="width: 140px;"
								class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'interDateEnd\')}'})"/>
								至<input type="text" id="interDateEnd" name="interDateEnd" style="width: 140px;"
								class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'interDateBegin\')}'})"/></td>
								<td>车辆牌照:</td>
								<td><input type="text" id="frtWorkOrderRepaiStatisticalStatementQueryCarId" name="carId" class="easyui-combobox"
									data-options="
									url : 'frtOptionsAction!findAllCarLicenseAsCarLicense.action',
									valueField : 'id',
									textField : 'text',
									mode : 'remote'"/></td>
								<td>接待员:</td>
								<td><input type="text" id="frtWorkOrderItemQueryReceivePerson" name="receivePerson" class="easyui-combobox"
									data-options="
									url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
									valueField:'id',  
									textField:'text',
									validType:'isSelected[\'#frtWorkOrderItemQueryReceivePerson\']',
									invalidMessage : '请从下拉框中选择接待员',
									mode:'remote'  "/>
								</td>
								<td>维修类别:</td>
								<td><input type="text" id="frtWorkOrderItemQueryReptId" name="reptId" class="easyui-combobox"
									data-options="
									url : 'frtOptionsAction!findAllReptype.action',
									validType:'isSelected[\'#frtWorkOrderItemQueryReptId\']',
									invalidMessage : '请从下拉框中选择维修类别',
									mode:'remote' ,
									valueField:'id',  
									textField:'text' "/></td>
							</tr>
							<tr>
								<td>结算日期:</td>
								<td>
								<!--<input class="Wdate" id="frtWorkOrderItemQueryPreclrTimeBegin"  style="width: 140px;"
								 name="preclrTimeBegin" onfocus="WdatePicker({});" />
								至<input type="text" class="Wdate" id="frtWorkOrderItemQueryPreclrTimeEnd"  style="width: 140px;"
								 name="preclrTimeEnd" onfocus="WdatePicker({});" />
								-->
									<input id="frtWorkOrderItemQueryPreclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
									 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'frtWorkOrderItemQueryPreclrTimeEnd\',{d:-1})}'})"/>
					                                              至<input id="frtWorkOrderItemQueryPreclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
					                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'frtWorkOrderItemQueryPreclrTimeBegin\',{d:0})}'})"/>
								</td>
								<td>客户名称:</td>
								<td><input type="text" id="frtWorkOrderItemQueryCustomId" name="customId" class="easyui-combobox"
									data-options="
									url : 'frtOptionsAction!findAllCustom.action',
									valueField:'id',  
									textField:'text',
									validType:'isSelected[\'#frtWorkOrderItemQueryCustomId\']',
									invalidMessage : '请从下拉框中选择客户名称',
									mode:'remote'  "/></td>
								<td>
								           车辆品牌:
						        </td>
								<td>
								    <input type="text" id="carArchives_cbrdId" name="cbrdId" 
								    class="easyui-combobox" 
								    data-options="url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarBrand.action',
								    valueField:'id',
								    textField:'text',
								    validType:'isSelected[\'#carArchives_cbrdId\']',
									invalidMessage : '请从下拉框中选择车辆品牌',
									mode:'remote'  "/>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div id="frtWorkOrderRepaiStatisticalStatementDatagrid_center" style="background:#eee;" data-options="region:'center',border:false">
					<table id="frtWorkOrderRepaiStatisticalStatementDatagrid"></table>
				</div>
			</div> 		
	  	</div>
	</div>    
</body>
</html>