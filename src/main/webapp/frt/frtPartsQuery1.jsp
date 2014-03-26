<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtPartsQuery.js"></script>
<div class="easyui-layout" fit="true" border="false">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
       <privilege:enable code="STAGEPARTSSEA_SEARCH">
	      	<a href="javascript:void(0);" id="search_parts" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="_query();">查询</a>
	   </privilege:enable>
    	<privilege:enable code="STAGEPARTSSEA_CLEAR">
	      	<a href="javascript:void(0);" id="clear_parts" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clear();">清空</a>
	   </privilege:enable>
    	<privilege:enable code="STAGEPARTSSEA_PRINT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="print" iconCls="icon-print" plain="true">套打模板</a>
	   </privilege:enable>
		<privilege:enable code="STAGEPARTSSEA_SET">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="set" iconCls="icon-set" plain="true">打印设置</a>
	   </privilege:enable> 
	   <privilege:enable code="STAGEPARTSSEA_EXPROT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="export" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
	   </privilege:enable>
    </div>
	<div data-options="region:'center',border:false">
		<div class="easyui-layout" fit="true" border="false">
				<div title="查询条件" style="overflow: hidden;background:#eee;height:50px;" data-options="region:'north',border:false">
					<form id="frtPartsQueryForm">
						<table>
							<tr>
								<td>配件编码:</td>
								<td><input type="text" name="partsId" class="easyui-numberbox"></td>
								<td>配件名称:</td>
								<td><input type="text" name="partsName"></td>
								<td>简码:</td>
								<td><input type="text" name="partsSimpleId"></td>
							</tr>
						</table>
					</form>
				</div>
				<div id="frtPartsQueryDatagrid_center" style="background:#eee;" data-options="region:'center',border:false">
					<table id="frtPartsQueryDatagrid"></table>
				</div>
		 </div>
	</div>
</div>