<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>配件档案</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/partsArchives.js"></script>
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
      <privilege:enable code="PARTSARCHIVES_ADD">
	      	<a id="add" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add();">新增</a>
	   </privilege:enable>
    	<privilege:enable code="PARTSARCHIVES_REMOVE">
	      	<a id="remove" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="_remove();">删除</a>
	   </privilege:enable>  
    	<privilege:enable code="PARTSARCHIVES_EDIT">
	      	<a id="edit" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">修改</a>
	   </privilege:enable>  
    	<privilege:enable code="PARTSARCHIVES_SEARCH">
	      	<a id="search" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="query();">查询</a>
	   </privilege:enable>    
    	<privilege:enable code="PARTSARCHIVES_CLEAR">
	      	<a id="clear" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clear();">清空</a>
	   </privilege:enable>  
    	<privilege:enable code="PARTSARCHIVES_EXPORT">
	      	<a id="export" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'" onclick="_except();">Excel导出</a>
	   </privilege:enable>    
    	<privilege:enable code="PARTSARCHIVES_IMPORT">
	      	<a id="import" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-import'" onclick="_import();">导入</a>
	   </privilege:enable>
	   <privilege:enable code="PARTSARCHIVES_IMPORTOK">
	      	<a id="ok" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" disabled="disabled" onclick="_importVisable();">确认导入</a>
	   </privilege:enable>
    	<privilege:enable code="PARTSARCHIVES_CHANGE">
	      	<a id="change" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-change'" onclick="change();">变更</a>
	   </privilege:enable>  
	   <span id="saveOrCancelBtn"></span>
    </div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	 <div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;padding:3px;background:#eee;height:85px;">
		    	<form id="partsArchivesQueryForm">
					<table>
						<tr>
							<td>配件编码:</td>
							<td><input type="text" name="partsId"/></td>
							<td>配件名称:</td>
							<td><input type="text" name="partsName"/></td>
							<td>配件品牌:</td>
							<td><input type="text" id="partsArchives_pbrdId" name="pbrdId" class="easyui-combobox" data-options="
							url : 'basPartsArchivesAction!findPartsBrand.action',
							valueField:'id',  
			    			textField:'text',
			    			required:false,
			    			mode : 'remote',
			    			validType:'isSelected[\'#partsArchives_pbrdId\']',
							invalidMessage : '请从下拉框中选择配件品牌',
			    			onSelect : function (record){
			    				$('#partsArchives_ptypeId').combobox('clear');
			    				$('#partsArchives_ptypeId').combobox('reload', 'basPartsArchivesAction!findPartsType.action?pbrdId=' + record.id);
			    			} "/></td>
							<td>配件型号:</td>
							<td><input type="text" id="partsArchives_ptypeId" name="ptypeId" class="easyui-combobox" data-options="
							url : 'basPartsArchivesAction!findPartsType.action',
							valueField:'id',  
			    			textField:'text',
			    			required:false,
			    			validType:'isSelected[\'#partsArchives_ptypeId\']',
							invalidMessage : '请从下拉框中选择配件型号',
			    			mode :'remote'"/></td>
						</tr>
						<tr>
							<td align="right">编码二:</td>
							<td><input type="text" name="partsId2"/></td>
							<td>适合车型:</td>
							<td>
								<input id="partsArchives_fitPtype"  name="fitPtype" class="easyui-combobox"  style="width: 140px;" 
					    		data-options="url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarTypeAsName.action',
					    		valueField:'id',
					    		editable:true,
					    		textField:'text',
					    		multiple:true,
					    		mode:'remote'
					    		" />
							</td>
							<td>配件部位:</td>
							<td><input type="text" id="partsArchives_posName"  name="posName" class="easyui-combobox" data-options="
							url : 'basPartsArchivesAction!findAllPartsPosition.action',
							valueField:'id',  
			    			textField:'text',
			    			editable:false,
			    			required:false,
			    			validType:'isSelected[\'#partsArchives_posName\']',
							invalidMessage : '请从下拉框中选择配件部位',
			    			mode : 'remote'"/></td>
							<td align="right">产地:</td>
							<td><input type="text" id="partsArchives_stateName" name="stateName" class="easyui-combobox" data-options="
							url : 'basPartsArchivesAction!findAllPartsState.action',
							validType:'isSelected[\'#partsArchives_stateName\']',
							invalidMessage : '请从下拉框中选择产地',
							valueField:'id',  
							editable:false,
							required:false,
			    			textField:'text',
			    			mode : 'remote'"/></td>
						</tr>
					</table>
				</form>
		    </div>  
		    <div id="partsArchivesDatagrid_center" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="partsArchivesDatagrid"></table>
		    </div>  
		</div>  
    </div>  
  </body>
</html>
