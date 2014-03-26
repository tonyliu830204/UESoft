<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆加装申请</title>
	
	
	<script type="text/javascript">
	var wjz = "<%=Contstants.JZZT.wjz %>";//未加装
	var sqjz = "<%=Contstants.JZZT.sqjz %>";//申请加装
	var yjz = "<%=Contstants.JZZT.yjz %>";//已加装
	//申请加装
	function applyFix(index){
		if($('#datagrid_center_car_fix_id').datagrid('selectRow',index)){
		   var value = $('#datagrid_center_car_fix_id').datagrid('getSelections');
		   $.ajax({
				type : 'post',
				url : 'carFixAction!doApplyFix.action',
				data : value[0],
				dataType : 'json',
				success : function(r){
		            $('#datagrid_center_car_fix_id').datagrid('load');
		        }
		    });
		}
	}
	</script>
  </head>
  <body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/sell_financemanage.js"></script>
    <div class="easyui-layout" style="width: 800px; height: 600px;" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:40px;" border="false">
			 <form id="form_car_fix_id">
				 <table>
					 <TR>
					 	  <td>VIN号:</td>
					 	  <td><input id="car_vin_number" name="xs_car_vin_number" type="text" class="easyui-validatebox" style="width:180px;"/></td>
					 	  <td>OCN号:</td>
					 	  <td><input id="car_ocn" name="xs_car_ocn" type="text" class="easyui-validatebox" style="width:180px;"/></td>
					 	  <TD>
					 	  <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit($('#form_car_fix_id'),$('#datagrid_center_car_fix_id'));">查询</a>&nbsp;
					 	  <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear($('#form_car_fix_id'),$('#datagrid_center_car_fix_id'));">清空</a>
					 	  </TD>
				 	 </TR> 
				 </table>
			   </form>
		</div>
		<div region="center" style="background:#eee;" border="false">
			<table id="datagrid_center_car_fix_id"></table>
		</div>
	</div>
  </body>
</html>
