<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>配件型号资料</title>
  </head>
  <body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/mountingsTypeInfo.js"></script>
  <div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
			<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
				<privilege:enable code="MOUNTINGSTYPEADD">
						<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="add();">新增</a>
					</privilege:enable>
					<privilege:enable code="MOUNTINGSTYPEDELETE">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
					</privilege:enable>
					<privilege:enable code="MOUNTINGSTYPEMODIFY">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update();">修改</a>
					</privilege:enable>
					<privilege:enable code="MOUNTINGSTYPEQUERY">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query();">查询</a>
					</privilege:enable>		
					<privilege:enable code="MOUNTINGSTYPECLEAR">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearTypeInfo();">清空</a>
					</privilege:enable>		
					<privilege:enable code="MOUNTINGSTYPEEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">导出</a>
					</privilege:enable>
					
					<span id="saveOrCancelBtn"></span>
			  </div>
		 <div data-options="region:'center',border:false" style="background:#eee;">
		 <div class="easyui-layout" data-options="fit:true,border:false"> 
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 50px;" border="false">
				<form id="mtiForm">
					配件品牌:<input type="text" name="pbrdId" id="pbrdId" class="easyui-combobox" data-options="
   					url : 'basMountingsTypeInfo_findAllPartsBrand.action',
					valueField:'id',  
				    textField:'text',
				    validType:'isSelected[\'#pbrdId\']',
					invalidMessage : '请从下拉框中选择配件品牌',
				    mode:'remote'"/>
   					&nbsp;&nbsp;配件型号:<input type="text" name="ptypeName"/>
   				</form>
			</div>
			<div id="mountingsTypeInfoDiv" region="center" style="background: #eee" border="false" >
				<table id="mountingsTypeInfo"></table>
			</div>
		  </div>
		</div>
	</div>
  </body>
</html>
