<%@ page language="java" import="java.util.*,ognl.*" pageEncoding="UTF-8"%>
<!-- 车辆结构 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtReception/details/vehicleStructure.js"></script>
<!-- 车身状况(车辆结构) -->
<div class="easyui-layout" data-options="fit:true">
	<div style="width: 460px;" data-options="region:'west',title:'',border:false">
		<jsp:include page="/frt/carStructure.jsp"></jsp:include>
	</div>
	<div data-options="region:'center',title:'车身状况'">
		<table id="vehicleStructureDatagrid"></table>
	</div>
</div>