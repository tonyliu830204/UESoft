<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StPartsNowCount/StPartsNowCount_PartsNameSelect.js"></script>  
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:33px;">
        <form id="partsnoewcount_selectpartsform">
		    <table>
			    <tr>
			       <td>配件ID</td>
			       <td><input id="pnc_partsId" name="partsId" onkeyup="_query();"/></td>
			       <td>配件名称</td>
			       <td><input id="partsName" name="partsName" onkeyup="_query();"/></td>
			       <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a></td>
			    </tr>
		    </table>
	    </form>
	</div>
    <div region="center" style="background:#eee;"  border="false">
	     <table id="partsnoewcount_selectpartstable"></table>
    </div>
</div>