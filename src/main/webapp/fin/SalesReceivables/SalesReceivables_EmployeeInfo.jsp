<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/SalesReceivables/SalesReceivables_EmployeeInfo.js"></script>
			 <script type="text/javascript">
			          
			</script>
           <!-- 采购员选择 -->
		   <div id="cc" class="easyui-layout"  fit="true" border="false">  
			    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
				    <form id="spo_basStuffForm">
					               编号:<input type="text" id="stfId" name="stfId" onkeyup="searchBasStuff();"/>&nbsp;&nbsp;
						    采购员名称:<input type="text" id="stfName" name="stfName" onkeyup="searchBasStuff();"/>
						  &nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSerachCondition();" plain="true">清空</a> 
				    </form>
			    </div>
			    <div region="center" style="background:#eee;"  border="false">
			       <table id="EmployInfo"></table>  
			    </div>  
	       </div>
