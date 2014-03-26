<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StPartsNowCount/StPartsNowCount_PartsBrandSelect.js"></script>    
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:33px;">
        <form id="partsnowcount_partsbrandform">
		    <table>
			    <tr>
			       <td>品牌ID</td>
			       <td><input id="pnc_pbrdId" name="pnc_pbrdId" onkeyup="_query();"/></td>
			       <td>品牌名称</td>
			       <td><input id="pnc_pbrdName" name="pnc_pbrdName" onkeyup="_query();"/></td>
			       <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a></td>
			    </tr>
		    </table>
	    </form>
	</div>
    <div region="center" style="background:#eee;"  border="false">
	     <table id="partsnowcount_partsbrandtable"></table>
    </div>
</div>