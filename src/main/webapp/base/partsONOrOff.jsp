<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>配件使用设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
	<script type="text/javascript">
	      var flag=<%=Contstants.ONOROFF.ONOROFFYES%>;
	      var flag1=<%=Contstants.ONOROFF.ONOROFFNO%>;
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/partsONOrOff.js"></script>
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
   		 <privilege:enable code="PARTSONOROFF_OK">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="useStateAsTrue();">启用</a>
		 </privilege:enable>
    	 <privilege:enable code="PARTSONOROFF_NO">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-no'" onclick="useStateAsFalse();">禁用</a>
		 </privilege:enable> 
    	 <privilege:enable code="PARTSONOROFF_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="query();">查询</a>
		 </privilege:enable>  
    	 <privilege:enable code="PARTSONOROFF_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clear();">清空</a>
		 </privilege:enable>   
    	 <privilege:enable code="PARTSONOROFF_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'" onclick="_except();">Excel导出</a>
		 </privilege:enable>
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
			    			validType:'isSelected[\'#partsArchives_ptypeId\']',
							invalidMessage : '请从下拉框中选择配件型号',
			    			mode : 'remote'"/></td>
						</tr>
						<tr>
							<td align="right">编码二:</td>
							<td><input type="text" name="partsId2"/></td>
							<td>适合车型:</td>
							<td>
								<input id="partsArchives_fitPtype" name="fitPtype" class="easyui-combobox"  style="width: 140px;" 
					    		data-options="valueField:'id',textField:'text',separator:',',multiple:true, mode : 'remote',
					    			validType:'isSelected[\'#partsArchives_fitPtype\']',invalidMessage : '请从下拉框中选择适合车型',
					    			url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarTypeAsName.action'" />
							</td>
							<td>配件部位:</td>
							<td><input type="text" id="partsArchives_posName" name="posName" class="easyui-combobox" data-options="
							url : 'basPartsArchivesAction!findAllPartsPosition.action',
							valueField:'id',  
			    			textField:'text',
			    			validType:'isSelected[\'#partsArchives_posName\']',
							invalidMessage : '请从下拉框中选择配件部位',
			    			mode : 'remote'"/></td>
							<td align="right">产地:</td>
							<td><input type="text" id="partsArchives_stateName" name="stateName" class="easyui-combobox" data-options="
							url : 'basPartsArchivesAction!findAllPartsState.action',
							valueField:'id',  
			    			textField:'text',
			    			validType:'isSelected[\'#partsArchives_stateName\']',
							invalidMessage : '请从下拉框中选择产地',
			    			mode : 'remote'"/></td>
						</tr>
					</table>
				</form>
		    </div>  
		    <div id="partsOnOrOffDatagrid_center" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="partsOnOrOffDatagrid"></table>
		    </div>  
		</div>  
    </div>  
  </body>
</html>
