<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%


%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆流失情况</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/FBK_VIP/System_manag.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/system_management/car_lost_info.js"></script>
  </head>
  		<body>
  		<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
		<privilege:enable code="CARLOSTINFODELETE">
		 <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="">删除</a>
		</privilege:enable>
		<privilege:enable code="CARLOSTINFOQUERY">
		 <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit($('#datagrid_carlost_info_id'),$('#form_carlost_infor_id'),'carLostAnalysisAction!getCollectinfor');">查询</a>
		</privilege:enable>
		<privilege:enable code="CARLOSTINFOCLEAR">
		 <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
		</privilege:enable>
		<privilege:enable code="CARLOSTINFOPRINT">
		 <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
		</privilege:enable>
		<privilege:enable code="CARLOSTINFOEXPORT">
		 <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
		</privilege:enable>
		</div>
		<div region="center" style="background:#eee;" fit="true" border="false">
		
		<div id="tab_carlost_infor_id" class="easyui-tabs" fit="true" >
		<div title="车辆流失情况汇总">
			<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:65px;" border="false">
				<form id="form_carlost_infor_id">
				<fieldset>
					<legend>查询条件</legend>
					<table style="text-align: right">
							<tr>
								<td>车牌照：</td>
								<td><input type="text" name="car_License"/></td>
								<td>VIN号：</td>
								<td><input type="text" name="car_Vin"/></td>
								
								<td>最后接待：</td>
								<td>
									<input name="stf_Name"
													class="easyui-combobox"
														data-options="
														url : 'carInsuranceManageAction_getBasStuff.action',
														valueField:'id',  
														textField:'name',
														multiple:false  "
														/>
								</td>
								<td>车品牌：</td>
								<td>
									<input name ="cbrd_Name"
													class="easyui-combobox"
													data-options="
													url : 'vTrackRecordAction_getCarbrand.action',
													valueField:'id',  
													textField:'name',
													multiple:false  "
													/>
								</td>
								<td>车型号：</td>
								<td>
									<input style="width:110px" name="ctype_Name"
									class="easyui-combobox"
									data-options="
									url : 'VipRecordMessageAction!getBasCarType.action',
									valueField:'id',  
									textField:'name',
									multiple:false  "
									/>
								</td>
								
								<td>在修状况：</td>
								<td>
									<input style="width:110px" name="cst_Name"
													class="easyui-combobox"
													data-options="
													url : 'frtOptionsAction!findBaseListData.action?key=repairstatus',
													valueField:'text',  
													textField:'text',
													multiple:false  "
													/>
								</td>
							</tr>
					</table>
					</fieldset>
				</form>		
				</div>
				<div region="west" border="false" style="width: 300px">
					<table id="datagrid_carlost_count_id"></table>
				</div>
				<div region="center" border="false"  border="false">
					<table id="datagrid_carlost_info_id"></table>
				</div>
			</div>
		</div>
		<div title="车辆流失情况明细" >
		 <div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		 <div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:92px;" border="false">
			
			<form id="form_carlost_infor_detail_id">
			<fieldset>
				<legend>车辆流失明细信息</legend>
				<table style="text-align: right">
						<tr>
						<td>车牌照</td>
						<td><input name="car_License"/>
						<input id="cId" name="car_Id" style="display: none"/>
						</td>
						<td>车品牌</td>
						<td><input type="text" name="cbrd_Name"/></td>
						<td>车类型</td>
						<td><input type="text" name="ctype_Name"/></td>
						<td>最后维修</td>
						<td><input type="text" name="car_Last_Repair_Date"/></td>
						<td>客户名</td>
						<td><input type="text" name="custom_Name"/></td>
						<td width="100"></td>
						<td>
							<a href="return_visit/customer_care.jsp?tag=6" class="easyui-linkbutton">跟踪记录</a>
							<a href="base/customArchives.jsp" class="easyui-linkbutton">客户档案</a>
						</td>
					</tr>
					<tr>
						<td>电话一</td>
						<td><input type="text" name="custom_Tel1"/></td>
						<td>电话二</td>
						<td><input type="text" name="custom_Tel2"/></td>
						<td>地址</td>
						<td><input type="text" name="custom_Addr"/></td>
						<td>跟踪日</td>
						<td><input type="text" name=""/></td>
						<td>最后接待</td>
						<td><input type="text" name="stf_Name"/></td>
						<td width="100"></td>   
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="dbLostButton();" >流失跟踪</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" onclick="updateToF()">转准流失</a>
						</td>
					</tr>
				</table>
				</fieldset>
			</form>
			</div>
			<div region="center" style="background:#eee;" border="false">
				<table id="treegrid_carlost_info_id"></table>
			</div>
	  	</div>
		</div>
	</div>
	</div>
	</div>	
  </body>
</html>
