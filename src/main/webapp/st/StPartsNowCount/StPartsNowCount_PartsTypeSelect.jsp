<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StPartsNowCount/StPartsNowCount_PartsTypeSelect.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:33px;">
        <form id="partsnowcount_partstypeform">
		    <table>
			    <tr>
			       <td>型号ID</td>
			       <td><input id="pnc_ptypeId" name="ptypeId" onkeyup="_query();"/></td>
			       <td>型号名称</td>
			       <td><input id="pnc_ptypeName" name="ptypeName" onkeyup="_query();"/></td>
			       <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a></td>
			    </tr>
		    </table>
	    </form>
	</div>
    <div region="center" style="background:#eee;"  border="false">
	     <table id="partsnowcount_partstypetable"></table>
    </div>
</div>