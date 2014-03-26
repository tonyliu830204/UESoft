<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/SalesReceivables/SalesReceivables_CarLicense.js"></script>
	   <div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:33px;">
		        <form id="slo_carLicenceForm">
				    <table>
					    <tr>
					       <td>车牌照</td>
					       <td><input type="text" id="slo_carLicense" name="slo_carLicense" onkeyup="searchByCondition();"/></td>
					       <td>VIN号</td>
					       <td><input type="text" id="slo_carVan" name="slo_carVan" onkeyup="searchByCondition();"/></td>
					       <td>发动机号</td>
					       <td><input type="text" id="slo_carMotorId" name="carMotorId" onkeyup="searchByCondition();"/></td>
					       <td><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSearchCondition();" plain="true">清空</a></td>
					    </tr>
				    </table>
			    </form>
			</div>
		    <div region="center" style="background:#eee;"  border="false">
			     <table id="slo_CarLicenseTable"></table>
		    </div>
		</div>
