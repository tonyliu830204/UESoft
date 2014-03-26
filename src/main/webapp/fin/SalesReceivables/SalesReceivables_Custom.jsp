<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/SalesReceivables/SalesReceivables_Custom.js"></script>
		<script type="text/javascript">
				  
		</script>
        <!-- 客户信息选择 -->
		<div id="cc" class="easyui-layout" fit="true" border="false">  
		     <div region="north" title="条件" split="false" style="height:55px;background:#eee;" border="false">
			   <form id="sle_customerSelectForm">
				    <table>
					    <tr>
					       <td>客户编号</td>
					       <td><input type="text" id="slo_customId" name="slo_customId" onkeyup="searchByCondition();"/></td>
					       <td>客户名称</td>
					       <td><input type="text" id="slo_customName" name="slo_customName"  onkeyup="searchByCondition();"/></td>
					       <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSearchByCondition();" plain="true">清空</a> 
						   </td>
					    </tr>
				    </table>
			    </form>
		    </div>
		    <div region="center" style="background:#eee;" border="false">
		        <table id="sle_customInfoTable"></table>
		    </div>
       </div>
