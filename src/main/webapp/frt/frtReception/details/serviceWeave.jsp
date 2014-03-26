<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 选择维修套餐 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtReception/details/serviceWeave.js"></script>
 <div class="easyui-layout" fit="true" border="false">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="_selectServiceWeaveQuery();">查询</a>  
    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_selectServiceWeaveClear();">清空</a>  
    </div>
    	
		
	<div data-options="region:'center',border:false">
	<div class="easyui-layout" fit="true" border="false">
		<div title="查询条件" style="overflow: hidden;background:#eee;height:60px;" data-options="region:'north',border:false">
			<form id="selectServiceWeaveQueryForm">
				<table>
					<tr>
						<td>套餐编号:</td>
						<td><input type="text" name="rpId"></td>
						<td>套餐名称:</td>
						<td><input type="text" name="rpName"></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="background:#eee;" data-options="region:'center',border:false">
			<table id="selectServiceWeaveDatagrid"></table>
		</div>
		</div>
	</div>
		
  </div>