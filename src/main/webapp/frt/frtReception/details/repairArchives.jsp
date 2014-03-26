<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 维修档案 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtReception/details/repairArchives.js"></script>
<div class="easyui-layout" style="width:800px; height:600px" data-options="fit:true,border:false">
	<div title="查询条件" style="overflow: hidden;background:#eee; height:80px;" data-options="region:'north',border:false">
		<form id="">
			<table>
				<td>车牌照:</td>
				<td><input type="text" name="carId" class="easyui-combobox"
					data-options="
						url : 'frtOptionsAction!findAllCarLicense.action',
						valueField : 'id',
						textField : 'text',
						mode : 'remote'" />
				</td>
			</table>
		</form>
	</div>
	<div style="background:#eee;" data-options="region:'center',border:false">
		<table id="frtResevationRepairArchivesDatagrid"></table>
	</div>
</div>
