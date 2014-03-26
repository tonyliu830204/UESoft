<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>公司信息设定</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body class="easyui-layout">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/companyInformationSet.js"></script>
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
    	<privilege:enable code="COMPANYSET_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">修改</a>
	   	</privilege:enable>
    	<privilege:enable code="COMPANYSET_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="query();">查询</a>
	   	</privilege:enable>  
    	<privilege:enable code="COMPANYSET_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clear();">清空</a>
	   	</privilege:enable>
	   	<privilege:enable code="COMPANYSET_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'" onclick="_except();">Excel导出</a>
	   </privilege:enable>  
    </div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:58px;padding:3px;">
		    	<form id="companyInfomationSetQueryForm">
		    		参数分类:
		    		<input id="companyInformationSet_ciType" name="ciType" class="easyui-combobox"
					data-options="valueField:'id',  
					              textField:'text',
					              editable : false,
					              url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=company',
					              onChange : function (newValue, oldValue){
										$companyInfomationSetQueryForm = $('#companyInfomationSetQueryForm').form();
										_search($companyInfomationSetQueryForm, companyInfomationSetDatagrid);
								  }
					"/> 
		    		参数名称:<input type="text" style="width: 200px;" id="companyInfomationSet_ciName" name="ciName"/>
		    	</form>
		    </div>
		    <div id="companyInfomationSetDatagrid_center" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="companyInfomationSetDatagrid"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
