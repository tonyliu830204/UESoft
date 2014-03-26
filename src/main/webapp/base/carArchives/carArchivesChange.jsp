<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 车辆代码变更 -->
<script type="text/javascript">
var projectPath = "${pageContext.request.contextPath}/";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/carArchives/carArchivesChange.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-change'" onclick="accept();">变更</a>  
     </div>
     <div data-options="region:'center',border:false" style="background:#eee;">
     	<fieldset>
	    		<legend><strong>编码变更:</strong></legend>
    			<form id="carArchives_changeBeforeForm">
    			<td><input type="hidden" name="carId" id="frt_car_id"/></td>
    				<table>
    					<tr>
    						<td>原车辆牌照:</td>
    						<td><input type="text" name="carLicense" readonly="readonly" style="width: 200px;background-color: #c0d8d8;"/></td>
    					</tr>
    					<tr>
    						<td>原客户名称:</td>
    						<td><input type="text" name="customName" readonly="readonly" style="width: 200px;background-color: #c0d8d8;"></td>
    					</tr>
    				</table>
    			</form>
	   	</fieldset>
	   	<br/>
     	<fieldset>
	    		<legend><strong></strong></legend>
    			<form id="carArchives_changeAfterForm">
    				<table>
    					<tr>
    						<td>新车辆牌照:</td>
    						<td><input type="text" name="carLicense" style="width: 200px"/></td>
    					</tr>
    					<!-- <tr>
    						<td>新客户名称:</td>
    						<td><input type="text" name="customName" style="width: 200px;" /></td>
    					</tr> -->
    				</table>
    			</form>
	   	</fieldset>
     </div>
     <div data-options="region:'south',border:false" style="background:#eee;height: 110px; color: red;">
     	 &nbsp;&nbsp; 车辆牌照修改:<br/>
     	 &nbsp;&nbsp; 主要包扩以下两种情况:<br/>
     	 &nbsp;&nbsp;(一)、车辆牌照书写错误，需要进行更正，<br/>
     	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 如陕UA0090(错误)，更正为:陕AU9090(正确);<br/>
     	 &nbsp;&nbsp;(二)、车辆牌照的合并，如同一辆车在车辆档案中重复登记，<br/>
     	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 需要合并;
     	 &nbsp;&nbsp;(三)、临时牌照修改，在新车建档过程中使用临时，<br/>
     	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 待上牌后在此更正;
     	 
     </div>
</div>