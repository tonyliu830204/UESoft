<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/StSellPercharge/StSellPercharge_CarLicense.js"></script>
  <div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:33px;">
        <form id="ssp_carInfoForm">
		    <table>
			    <tr>
			       <td>车牌照</td>
			       <td><input type="text" id="sspc_carLicense" name="sspc_carLicense"  onkeyup="sspc_searchByCondition();"/></td>
			       <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSearchCondition();" plain="true">清空</a></td>
			    </tr>
		    </table>
	    </form>
	</div>
    <div region="center" style="background:#eee;"  border="false">
	     <table id="ssp_CarInfoTable"></table>
    </div>
</div>
