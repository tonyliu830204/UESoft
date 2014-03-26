<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
	<head>
		<title>配件盘点</title>
		<script type="text/javascript">
	    var sate = '<%=Contstants.AUDIT_TAG.AUDITYESS %>';
	    </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/system_management/accesssories_inventory.js"></script>
	</head>	
	<body>	
			<!-- 配件选择时 查询配件信息 -->
			<div id="accessories_find_id"></div>
			<div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
				<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
						<privilege:enable code="ACCESSORIESADD">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dbAddButtom();">新增</a>
						</privilege:enable>
						<privilege:enable code="ACCESSORIESDELETE">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doDelete();">删除</a>
						</privilege:enable>
						<privilege:enable code="ACCESSORIESEDIT">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dbEditButtom(3);">修改</a>
						</privilege:enable>
						<privilege:enable code="ACCESSORIESQUERY">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit();">查询</a>
						</privilege:enable>
						<privilege:enable code="ACCESSORIESCLEAR">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
						</privilege:enable>
						<privilege:enable code="ACCESSORIESEXPORT">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exception();">导出</a>
						</privilege:enable>
						<privilege:enable code="ACCESSORIESEXAMIN">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="doShenhe();">审核</a>
						</privilege:enable>
						<span id="addbutton"></span>
						<span id="editbutton"></span>
						
				</div>
				<div region="center"  style="background:#eee;">
				
			<div class="easyui-tabs" id="tabs_accessories_find_id" style="padding: 0px; width: 800px; height: 600px;"	fit="true" border="false"">
				<div title="盘点单汇总" style="width: 800px; height: 600px;" border="false">
				<div class="easyui-layout" border="false" fit="true" style="width: 800px; height: 600px;">
					<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:60px;" border="false">
						<form id="form_pandianhuizong_id" method="post">
						<fieldset style="height:40px;">
						<legend style="font-weight:bold">查询条件</legend>
							<table border="0" style="text-align: right">
							<tr>
								<td>盘点日期：</td>
								<td><input id="stinvm_Time" name="stinvm_Time" type="text" style="width: 85px;" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'stinvm_Time2\',{d:-1})}'})" /> 至 </td>
								<td><input id="stinvm_Time2" name="stinvm_Time2" type="text" style="width: 85px;" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'stinvm_Time\',{d:1})}'})" /></td>
								<td>盘点单号：</td>
								<td>
									<input id="stinvm_Id" name="stinvm_Id" type="text" />
								</td>
								<td>审核情况：</td>
								<td>
									<input style="width:110px" name="stinvm_State"
									class="easyui-combobox"
									data-options="
									url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.AUDIT_TAG.AUDITKEY %>',
									multiple:false,
									valueField:'id',   
					    		    textField:'text'  "
									/>
								</td>
							</tr>
							</table>
							</fieldset>
						</form>
					</div>
					<div id="pandian_huizong_idDiv" region="center" border="false">
						<table id="pandian_huizong_id"></table>
					</div>
				</div>
			</div>
			<div title="盘点单明细" style="width: 800px; height: 600px;" border="false">
				<div class="easyui-layout" border="false" fit="true"
					style="width: 800px; height: 600px;">
					<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:74px;" border="false">
					
						<form id="form_pandianmingxi_id" method="post">
							<table border="0" style="text-align: right">
							    <tr>
									<td>盘点日期：</td>
									<td><input id="stinvm_Time" name="stinvm_Time" type="text" readonly="readonly" style="background-color: #c0d8d8;"/></td>
									<td>盘点单号：</td>
									<td>
									<input id="stinvm_Id" name="stinvm_Id" type="text" readonly="readonly" style="background-color: #c0d8d8;"/>
									</td>
									<td>经办人：</td>
									<td>		
									<input id="stf_Id" name="stf_Name" type="text" readonly="readonly" style="background-color: #c0d8d8;"/></td>
									<td>仓别：</td>
									<td>
										<input style="width:400px" name="store_Id" id="store_Name_id"
											class="easyui-combobox"
																data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findAllStorehouse.action',
															    editable : true,
																valueField:'id',
																panelHeight:130,
																required:true,
																mode:'remote',
																missingMessage:'仓别必填',
																textField:'text'"
										/>
									</td>
								</tr>
								<tr>
									<td>盘点数量：</td>
									<td><input id="syinvm_Sum_Count" name="syinvm_Sum_Count" type="text" readonly="readonly" style="background-color: #c0d8d8;"/></td>
									<td>含税金额：</td>
									<td><input id="stinvm_Sum_Amount" name="stinvm_Sum_Amount" type="text" readonly="readonly" style="background-color: #c0d8d8;"/></td>
									<td>未税金额：</td>
									<td><input id="stinvm_Sum_Cost" name="stinvm_Sum_Cost" type="text" readonly="readonly" style="background-color: #c0d8d8;"/></td>
									<td>备注：</td>
									<td><textarea style="width:400px; height:40px; resize : none" name="stinvm_Remark"></textarea></td>
								</tr>
							
							</table>
						</form>
					</div>
					<div region="center" border="false">
						<table id="pandian_mingxi_id"></table>
					</div>
					<div region="south" border="false" split="false"
						style="height: 30px; background: #eee;">
							<a href="javascript:void(0);" class="easyui-linkbutton" id="linkbutton_add_id" disabled="disabled" iconCls="icon-add" plain="true" onclick="dbbutton();">配件新增</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>

