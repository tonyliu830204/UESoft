<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>客户档案</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/customArchives.js"></script>
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
    	<privilege:enable code="CUSTOMARCHIVES_ADD">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="_add();">新增</a>
	   </privilege:enable>
    	<privilege:enable code="CUSTOMARCHIVES_REMOVE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="_remove();">删除</a>
	   </privilege:enable>  
    	<privilege:enable code="CUSTOMARCHIVES_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">修改</a>
	   </privilege:enable>  
    	<privilege:enable code="CUSTOMARCHIVES_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="query();">查询</a>
	   </privilege:enable>  
    	<privilege:enable code="CUSTOMARCHIVES_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clear();">清空</a>
	   </privilege:enable>     
    	<privilege:enable code="CUSTOMARCHIVES_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'" onclick="_except();">Excel导出</a>
	   </privilege:enable>  
    	<privilege:enable code="CUSTOMARCHIVES_CHANGE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-change'" onclick="customChange();">变更</a>
	   </privilege:enable>
    </div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;padding:3px;background:#eee;height:85px;">
		    	<form id="customArchivesQueryForm">
		    		<table>
		    			<tr>
		    				<td>客户编号:</td>
		    				<td><input type="text" name="customId"/></td>
		    				<td>客户名称:</td>
		    				<td><input type="text" name="customName"/></td>
		    				<td>客户性质:</td>
		    				<td><input type="text" id="customArchives_natureId" name="natureId" class="easyui-combobox" data-options="
								url:'${pageContext.request.contextPath}/frtCustomAction!findAllCustomNature.action',   
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#customArchives_natureId\']',
    							invalidMessage : '请从下拉框中选择客户性质' " /></td>
		    				<td>客户类型:</td>
		    				<td><input type="text" id="customArchives_cstgId" name="cstgId" class="easyui-combobox" data-options="
								url:'${pageContext.request.contextPath}/frtCustomAction!findAllCustomGroup.action',   
					    		valueField:'id',
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#customArchives_cstgId\']',
    							invalidMessage : '请从下拉框中选择客户类型' " /></td>
		    				<td>客户分类:</td>
		    				<td><input type="text" id="customArchives_cstId" name="cstId" class="easyui-combobox" data-options="
								url:'${pageContext.request.contextPath}/frtCustomAction!findAllCustomType.action',   
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#customArchives_cstId\']',
    							invalidMessage : '请从下拉框中选择客户分类' " /></td>
		    				<td>车辆牌照:</td>
		    				<td><input type="text" name="carLicense"/>
		    				<%--<input type="text" name="carId" class="easyui-combobox" data-options="
								url:'${pageContext.request.contextPath}/frtCustomAction!findAllCarLicense.action',   
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote' " /></td>--%>
		    			</tr>
		    			<tr>
		    				<td>建档日期:</td>
		    				<td style="text-align: left;" colspan="3"><input type="text" id="customArchives_createDateBegin" name="createDateBegin" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'customArchives_createDateEnd\')}'});"/>
		    				&nbsp;至&nbsp;&nbsp;<input type="text" id="customArchives_createDateEnd" name="createDateEnd" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'customArchives_createDateBegin\')}'});"/></td>
		    				<td>所在区域:</td>
		    				<td><input type="text" id="customArchives_pareaId" name="pareaId" class="easyui-combobox" data-options="
								url:'${pageContext.request.contextPath}/frtCustomAction!findAllCustomArea.action',   
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#customArchives_pareaId\']',
    							invalidMessage : '请从下拉框中选择所在区域' " /></td>
		    				<td>地址:</td>
		    				<td><input type="text" name="customAddr"/></td>
		    				<td>联系电话:</td>
		    				<td><input type="text" name="customTel1" class="easyui-validatebox" data-options=""/></td>
<%--<!--		    				<td>VIN号:</td>-->--%>
<%--<!--		    				<td><input type="text" name="carVin"/></td>-->--%>
		    			</tr>
		    		</table>
		    	</form>
		    </div>
		   	<div id="customArchivesDatagrid_center" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="customArchivesDatagrid"></table>
		    </div>  
		</div>
    </div>
  </body>
</html>
