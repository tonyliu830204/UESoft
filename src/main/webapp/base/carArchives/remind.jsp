<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 车辆档案维修提醒 -->
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-change'" onclick="accept();">变更</a>  
     </div>
     <div data-options="region:'center',border:false" style="background:#eee;">
     	<form action="carArchivesRemind">
     		<table>
     			<tr>
     				<td>车辆牌照:</td>
     				<td><input type="text" name="carLicense" style="width: 140px;"/></td>
     				<td>工单号:</td>
     				<td><input type="text" name="no" /></td>
     			</tr>
     			<tr>
     				<td>发布日期:</td>
     				<td><input type="text" name="carLicense" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});" style="width: 140px;"/></td>
     				<td>发布人员:</td>
     				<td><input type="text" name="stfName" /></td>
     			</tr>
     			<tr>
     				<td>客户名称:</td>
     				<td><input type="text" name="customName" style="width: 140px;"></td>
     			</tr>
     			<tr>
     				<td>维修建议:</td>
     				<td colspan="3"><textarea style="width: 310px;height: 200px;"></textarea></td>
     			</tr>
     			<tr>
     				<td>处理进度:</td>
     				<td><input type="text" name="" style="width: 140px;"/></td>
     				<td>处理日期:</td>
     				<td><input type="text" class="Wdate" onfocus="WdatePicker({readOnly:true});"/></td>
     			</tr>
     			<tr>
     				<td>经办人:</td>
     				<td><input type="text" name="stfId" style="width: 140px;"/></td>
     			</tr>
     		</table>
     	</form>
     </div>
</div>
