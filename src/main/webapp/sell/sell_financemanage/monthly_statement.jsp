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
    <title>库存月结转</title>
    <script type="text/javascript">
    $(function(){
    	$('#censusEdate').val(getSystemTime2());
    	//月结（查询入库信息）
		$('#datagrid_monthly_statement_id').datagrid({
			url : 'sellMonthlyStatementAction!getStock.action',
			pagination : true,
			fit : true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'collectId',
			loadMsg : "数据加载中，请稍后……",
			columns : [[{
				field : 'collectId',
				title : '月结编号',
				width : 120,
				sortable : true,
				hidden : true
			},{
				field : 'censusSdate',
				title : '月结开始日期',
				width : 120,
				sortable : true
			},{
				field : 'censusEdate',
				title : '月结结束日期',
				width : 120,
				sortable : true
			},{
				field : 'censusSum',
				title : '月结库存量',
				width : 90,
				sortable : true
			},{
				field : 'censusRdate',
				title : '反月结日期',
				width : 100,
				sortable : true
			},{
				field : 'remark',
				title : '备注',
				width : 300,
				sortable : true
			}]]
		
		});
    	
    });
    	//导出
		function doExport(){
				exportEsuyUIExcelFile('datagrid_gathering_manage_record_div',null,"预收款记录"+getSystemTime());
		}
		
		 //清空
	 	function clearSearchCondition(){
	 		$('#search_monthly_statement_form_id').find('input').val('');
	 		$('#search_monthly_statement_form_id').find('select').val('');
	 		$('#datagrid_monthly_statement_id').datagrid('unselectAll');
	 		$('#datagrid_monthly_statement_id').datagrid('load', serializeObject($('#search_monthly_statement_form_id')));
	 		
	 	}
		//查询
		var queryCarReserve = function(){
			$('#datagrid_monthly_statement_id').datagrid('load',serializeObject($('#search_monthly_statement_form_id')));
		}
		//月结 
		function doStock(){
		$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url: 'sellMonthlyStatementAction!doStock.action',
		   data: serializeObject($('#form_monthly_statement_id')),
		   success: function(r){
		   		if(r.success){
		   			$('#datagrid_monthly_statement_id').datagrid('load');
		   			alertMsg(r.msg,"info");
		   		}
			   }
			});
		}
		//反月结 
		function doReturnStock(){
		var value = $('#datagrid_monthly_statement_id').datagrid('getSelections');
		if(value[0]!=null){
		$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url: 'sellMonthlyStatementAction!doReturnStock.action',
		   data: value[0],
		   success: function(r){
		   			if(r.success){
		   			$('#datagrid_monthly_statement_id').datagrid('load');
		   		}
		   		alertMsg(r.msg,"info");
			   }
			});
			}else{
			alertMsg("请选择月结点！  ","info");
			}
		}
		
    </script>
  </head>
  		<body>
  		<div class="easyui-layout" style="width:900px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:90px;" border="false">
		<privilege:enable code="MONTHLY_DOSTOCK">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-store" plain="true" onclick="doStock();">月结</a>
		</privilege:enable>
		<privilege:enable code="MONTHLY_RETURNSTOCK">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-store" plain="true" onclick="doReturnStock();">反月结</a>
		</privilege:enable>
		 <form id="form_monthly_statement_id" method="post">
			    		<fieldset style="height:40px; width:800px;">
			    		     <legend><strong>月结</strong></legend>
				    		 <table>
						       <tr>
						       		<td>本次月结结束时间：<input type="text" id="censusEdate" name="censusEdate" style="width:140px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-{%d-1} 24:00:00'})" class="Wdate" /></td>
						       		<td>月结备注：<input id="remark" name="remark" style="width:240px;"/><input type="hidden" style="width:240px;"/>
						       	</td>
						       </tr>
						     </table>
			    		</fieldset>
			    	</form>
		</div>
		<div region="center" style="background:#eee;"  border="false">
			<div class ="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
			   <form id="search_monthly_statement_form_id">
			               月结开始日期:<input name="censusSdate"  id="censusSdate" style="width:120px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'censusSdate2\',{d:-1})}'})" class="Wdate" />
			               			至
			             <input name="censusSdate2"  id="censusSdate2" style="width:120px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'censusSdate\',{d:1})}'})"
			               			class="Wdate" />
				  
				    月结结束日期:<input  name="censusEdate"  name="censusEdate" style="width:120px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'censusEdate2\',{d:-1})}'})" class="Wdate" />
				   		   至<input  name="censusEdate2" id="censusEdate2" style="width:120px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'censusEdate\',{d:1})}'})" class="Wdate" />
				   		   
				   			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryCarReserve();">查询</a>   
				   			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>   
			    </form>
		   </div>
			<div id="datagrid_monthly_statement_div" region="center" style="background:#eee;" border="false">
				<table id="datagrid_monthly_statement_id"></table>
			</div>
			
	  	</div>
	</div>
	</div>
  </body>
</html>
