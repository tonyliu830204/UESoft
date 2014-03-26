<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆型号资料</title>
  </head>
  <body>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/carTypeInfo.js"></script>
  	<div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
			<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
				<privilege:enable code="CARTYPEINFOADD">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="add" iconCls="icon-add" plain="true" onclick="add();">新增</a>
					</privilege:enable>
					<privilege:enable code="CARTYPEINFODELETE">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="delete" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
					</privilege:enable>
					<privilege:enable code="CARTYPEINFOMODIFY">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="update" iconCls="icon-edit" plain="true" onclick="update();">修改</a>
					</privilege:enable>
					<privilege:enable code="CARTYPEINFOQUERY">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="query" iconCls="icon-search" plain="true" onclick="queryType();">查询</a>
					</privilege:enable>		
					<privilege:enable code="CARTYPEINFOCLEAR">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="clear" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
					</privilege:enable>		
					<privilege:enable code="CARTYPEINFOEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="export" iconCls="icon-export" plain="true" onclick="_except();">导出</a>
					</privilege:enable>
					
					<span id="saveOrCancelBtn"></span>
			  </div>
		 <div data-options="region:'center',border:false" style="background:#eee;">
		 <div class="easyui-layout" data-options="fit:true,border:false"> 
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 50px;" border="false">
				<form id="ctibForm">
   				编号:<input type="text" name="ctypeId"/>
   				&nbsp;&nbsp;车辆品牌:<input type="text"  name="cbrdId" class="easyui-combobox" data-options="
				url : 'basCarTypeInfo_findAllCarBrand.action',
                editable : false,
				valueField:'id',
			    textField:'text'" 
			    style="width: 115px;"/>
   				&nbsp;&nbsp;车辆型号:<input type="text" name="ctypeName"/>
   			</form>
			</div>
			<div id="carTypeInfoDiv" region="center" style="background: #eee" border="false" >
					<table id="carTypeInfo"></table>
			</div>
		  </div>
		</div>
	</div>
  </body>
</html>
