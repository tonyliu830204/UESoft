<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>维修套餐</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/repairPackage.js"></script>
  </head>
  
  <body>
	<div class="easyui-layout" data-options="fit:true,border:false">  
	    <div region="north" style="overflow: hidden;padding:3px;background:#eee;height:32px;overflow=hidden;" border="false">
	    <privilege:enable code="SERVICINGMEAL_ADD">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="add" onclick="add();">新增</a>
	   </privilege:enable>
	   <privilege:enable code="SERVICINGMEAL_REMOVE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delete" onclick="_remove();">删除</a>
	   </privilege:enable>
	   <privilege:enable code="SERVICINGMEAL_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="edit" onclick="edit();">修改</a>
	   </privilege:enable>
		<privilege:enable code="SERVICINGMEAL_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="search" onclick="queryRp();">查询</a>
	   </privilege:enable>
		<privilege:enable code="SERVICINGMEAL_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id="cancelbut" onclick="$('#repairPackageQueryForm').form('clear');$repairPackage.datagrid('load',{});">清空</a>
	   </privilege:enable>	
		<privilege:enable code="SERVICINGMEAL_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id="export" onclick="_except();">Excel导出</a>
	   </privilege:enable>
			<span id="button"></span>
	    </div>
	    <div data-options="region:'center',border:false" style="background:#eee;">
	    	<div id="tt" class="easyui-tabs" fit="true" border="false">
	    		<div title="套餐汇总" style="display: block;" border="false" closable="false" fit="true">
					<div id="cc" class="easyui-layout" fit="true" border="false">
						<div region="north" title="条件" split="false"
							style="overflow: hidden;padding:3px;height:60px; background: #eee;" border="false">
							<form id="repairPackageQueryForm">
			    				<table>
			    					<tr>
			    						<td>套餐名称:</td>
			    						<td><input type="text" style="width: 200px;" name="rpName"/></td>
			    						<td>适合车型:</td>
			    						<td>
			    							<input id="applicableBrand" name="applicableBrands" class="easyui-combobox"
			    						  	style="width: 280px;" 
			    						    data-options="valueField:'id',textField:'text',
			    						    multiple:true,url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarTypeAsName.action'" />
			    						</td>
			    					</tr>
			    				</table>
							</form>
						</div>
						<div id="repairPackageCenter" region="center" style="background: #eee;" border="false">
							<table id="repairPackage"></table>
						</div>
					</div>
				</div>
				<div title="套餐明细" border="false" closable="false">
					<div id="dd" class="easyui-layout"  border="false" fit="true">
						<div region="north" split="false" style="overflow: hidden;height:60px; background: #eee;" border="false">
							<form id="repairPackageAddForm">
				    			<table>
					    			<tr>
					    				<td>套餐名称:</td>
					    				<td><input type="text" id="repairPackage_rpName" name="rpName" class="easyui-validatebox" style="width: 200px;" data-options="required: true,validType:'length[0,50]', missingMessage:'套餐名称为必填项'"/></td>
					    				<td>材料费:</td>
					    				<td><input type="text" id="repairPackage_partsAmount" name="partsAmount" style="width: 200px;background-color:#c0d8d8;" readonly="readonly"/></td>
					    				<td>工时费:</td>
					    				<td><input type="text" id="repairPackage_itemTimeAmount" name="itemTimeAmount" style="width: 200px;background-color:#c0d8d8;" readonly="readonly"/></td>
					    				<td>套餐金额:</td>
					    				<td><input type="text" id="repairPackage_rpAmount" name="rpAmount" style="width: 200px;background-color:#c0d8d8;" readonly="readonly"/></td>
					    			</tr>
					    			<tr>
					    				<td style="vertical-align:top;">适合车型:</td>
					    				<td colspan="3" style="text-align:left;vertical-align:top;">
					    				    <input id="applicableCarBrandValue" name="applicableBrands" class="easyui-combobox"  style="width: 280px;" 
			    						    data-options="valueField:'id',validType:'length[0,200]',textField:'text',separator:',',multiple:true,url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarTypeAsName.action'" />
					    				    <input type="hidden" name="rpId">
					    				</td>
					    				<td colspan="4">
					    				</td>
					    			</tr>
					    		</table>
				    		</form>
						</div>
						<div region="center" style="background: #eee;" aplit="false" border="false">
							<div id="ff" class="easyui-layout"  border="false" fit="true">
								<div region="center" style="background: #eee;" aplit="false" border="false">
							    	<div id="ee" class="easyui-tabs" fit="true" border="false">
							    		<div title="维修配件">
							    			<div class="easyui-layout" fit="true" border="false">
							    				<div id="repairPackagePartsCenter" region="center" style="background:#eee;" border="false">
									    			<table id="repairPackageParts"></table>
									    		</div>
							    			</div>
							    		</div>
							    		<div title="维修项目">
							    			<div class="easyui-layout" fit="true" border="false">
							    				<div id="repairPackageItemCenter" region="center" style="background:#eee;" border="false">
									    			<table id="repairPackageItem"></table>
									    		</div>
							    			</div>
							    		</div>
							    	</div>
							    </div>
							</div>
						</div>
					</div>
				</div>
			</div>
	    </div> 
  </div>
  </body>
</html>