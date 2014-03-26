<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会员折扣规则</title>
  </head>
  
  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
   <div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
   		<div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
				<privilege:enable code="DISCOUNTREGULATIONADD">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd($('#Vip_discount_regulation_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="DISCOUNTREGULATIONDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick="doDelete($('#Vip_discount_regulation_id'),'BasVipDiscountRegulationAction!delete.action','BasVipDiscountRegulationAction!findAll.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="DISCOUNTREGULATIONMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate($('#Vip_discount_regulation_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="DISCOUNTREGULATIONEXPORT">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport" iconCls="icon-export" plain="true" onclick=" _except('Vip_discount_regulation_idCenter','会员折扣规则信息');">导出</a>
				</privilege:enable>	
				<span id="saveOrCancelBtn"></span>
		</div>	
		<div id="Vip_discount_regulation_idCenter" region="center"  style="background:#eee;" border="false">
			<table id="Vip_discount_regulation_id"></table>
		</div>
	</div>
  </body>
</html>
