<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StSellOrder/StSellOrder_PickingMember.js"></script> 
<!-- 领料员信息 -->
<div id="cc" class="easyui-layout" fit="true" border="false">  
   <div region="north" title="查询条件" split="false" style="height:55px;background:#eee;" border="false">
    <form id="slo_pickingMemberForm">
	    <table>
		    <tr>
		       <td>领料员编号</td>
		       <td><input type="text" id="slo_stfId" name="stfId" onkeyup="_query();"/></td>
		       <td>领料员名称</td>
		       <td><input type="text" id="slo_stfName" name="stfName" onkeyup="_query();"/></td>
		       <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();"  plain="true">清空</a></td>
		    </tr>
	    </table>
    </form>
   </div>
   <div region="center" style="background:#eee;" border="false">
       <table id="slo_pickingMemberTable"></table>
   </div>  
</div>
