<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>索赔性质</title>
  </head>
  
  <body>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/other_set.js"></script>
 	<script type="text/javascript">
	 	/**
		  * 
		  * 导出excel
		  * 选择要导出的列
		  * 参数1   dateGrid控件id属性
		  * 参数2   dateGrid控件对应数据库类型
		  * 参数3   dateGrid控件上层控件id属性
		  * 参数4   执行按钮value文本
		  * 参数5   title文本
		  * 参数6   功能类型    0导出   1导入   2打印    3隐藏列
		  * 参数7   回调函数
		  * @return
		  */
		 function _except(){
		 	showEditDialog("Claim_nature_center_id",null,"Claim_nature_center","开始导出","导出配置",0,_callbackExcept);
		 }
		 
		 /**
		  * 导出excel回调函数
		  * 将筛选出的列导出到Excel文件
		  * 只支持Microsoft Office,不支持WPS
		  * @param parentId
		  * @param fieldNames  要导出的列字段
		  * @return
		  */
		 function _callbackExcept(parentId,fieldNames){
		 	exportEsuyUIExcelFile(parentId,fieldNames,"索赔性质信息"+getSystemTime());
		 }
 	</script>
  	<div class="easyui-layout" fit="true" border="false" style="width:500px;height:400px;background: #eee">
  		 <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			<privilege:enable code="CLAIMNATUREADD">
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnadd" iconCls="icon-add" plain="true" onclick="doAdd($('#Claim_nature_center_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="CLAIMNATUREDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnremove"  iconCls="icon-remove" plain="true" onclick="doDelete($('#Claim_nature_center_id'),'${pageContext.request.contextPath}/basClaimsTypeAction_doDelete.action','${pageContext.request.contextPath}/basClaimsTypeAction_doFindAll.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="CLAIMNATUREMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate($('#Claim_nature_center_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="CLAIMNATUREEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport" iconCls="icon-export" plain="true" onclick="_except();">导出</a>
					</privilege:enable>
				<span id="saveOrCancelBtn"></span>
	   </div>
   		<div id="Claim_nature_center" region="center" style="background:#eee;" border="false">
   		    <table id="Claim_nature_center_id"></table>
   		</div>   
  	</div>
  </body>
</html>
