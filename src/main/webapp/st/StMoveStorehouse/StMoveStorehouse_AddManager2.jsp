<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StMoveStorehouse/StMoveStorehouse_AddManager2.js"></script>
<!-- 经办人信息 -->
<div id="cc" class="easyui-layout" fit="true" border="false">  
    <div region="north" title="查询条件" split="false" style="height:55px;background:#eee;" border="false">
	    <form id="stmovestorehouse_uemployeeform">
		    <table>
			    <tr>
			       <td>经办人编号</td>
			       <td><input id="sms_stfId" name="stfId" onkeyup="_query();"/></td>
			       <td>经办人名称</td>
			       <td><input id="nsms_stfName" name="stfName" onkeyup="_query();"/></td>
			       <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();"  plain="true">清空</a></td>
			    </tr>
		    </table>
	    </form>
    </div>
    <div region="center" style="background:#eee;" border="false">
        <table id="stmovestorehouse_uemployeetable"></table>
    </div>
</div>