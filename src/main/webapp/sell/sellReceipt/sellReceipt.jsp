<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	 <title>承兑汇票管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellReceipt/sellReceipt.js"></script>
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
	<privilege:enable code="SELLRECEIPT_ADD">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="addReceipt();">新增</a>
	</privilege:enable>
	<privilege:enable code="SELLRECEIPT_REMOVE">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeReceipt()">删除</a>
	</privilege:enable>
	<privilege:enable code="SELLRECEIPT_EDIT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editReceipt();">修改</a>
	</privilege:enable>
	<privilege:enable code="SELLRECEIPT_SEARCH">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryReceipt();">查询</a>
	</privilege:enable>
	<privilege:enable code="SELLRECEIPT_CLEAR">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
	</privilege:enable>
	<privilege:enable code="SELLRECEIPT_PRINT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="dopoint('sellReceipt','sellReceipt_div_id');">打印</a>
	</privilege:enable>
	<privilege:enable code="SELLRECEIPT_EXPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="doexcept('sellReceipt','sellReceipt_div_id');">导出</a>
	</privilege:enable>
	<!--<privilege:enable code="SELLRECEIPT_IMPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="doinport('sellReceipt','sellReceipt_div_id');">导入</a>
   </privilege:enable>
    --></div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:80px;">
		    	<form id="receiptQueryForm" name="receiptQueryForm" method="post"  fit="true" style="margin-top: 10px" >
							<table>
								 <tr>
									<td width="70px">出票日期:</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'queryStartDate2\',{d:-1})}'})" name="queryStartDate" id="queryStartDate" style="width: 110px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'queryStartDate\',{d:1})}'})" name="queryStartDate2" id="queryStartDate2" style="width: 110px;"/></td>
								 	<td>票据编号:</td>
									<td ><input type="text"  name="receiptCode" style="width: 320px"   maxlength="20"/></td>
									<td width="70px">到期日期:</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'queryEndDate2\',{d:-1})}'})" name="queryEndDate" id="queryEndDate" style="width: 110px;"/> 至 </td>
							      <td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'queryEndDate\',{d:1})}'})" name="queryEndDate2" id="queryEndDate2" style="width: 110px;"/></td>
								</tr>
							</table>
			 </form>
		    </div>
		    <div id="sellReceipt_div_id" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="sellReceipt"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
