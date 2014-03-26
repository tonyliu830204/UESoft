<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>库存月结转</title>
  </head>
  
  <body class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div region="north" title="查询条件" split="false" style="height:100px;background:#eee;" border="false">
		
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true">月结</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true">反月结</a>
			<br/><br/><br/>
			<font style="font-size:15px">系统最后月结转日期为:2012-06-01 00:01</font>
		</div>
		<div region="south" split="false" style="height:200px;background:#eee;" border="false">
		<table>
				<tr><td><font style="font-size:20">月结提醒:</font></td></tr>
				<tr><td><font style="font-size:20">1.月结是,请确定本系统无用户使用。</font></td></tr>
				<tr><td><font style="font-size:20">2.月结完成后,截止日期前的入出库等相关进销存记录不可修改/删除。</font></td></tr>
				<tr><td><font style="font-size:20">3.月结完成后,推出系统重新进入。</font></td></tr>
			</table>
		
		</div>
		<div region="center" style="background:#eee;">
		
			<table>
				<tr>
					<td style="width:150px"><font style="font-size:15px">月结库存日期:</font></td>
				</tr><br/><br/><br/>
				
				<tr>
					<td style="width:300px"><font color="red" style="font-size:30px">库存月结历史记录:</font></td>
					<td><input class="easyui-datetimebox" editable="false" style="width:150px"/></td>
					<td width="100">  </td>
					<td colspan="3" rowspan="3">
					<div>
						<input type="checkbox" style="width:20px"/>配件入库管理<br/>
						<input type="checkbox" style="width:20px"/>配件退货管理<br/>
						<input type="checkbox" style="width:20px"/>配件出库管理<br/>
						<input type="checkbox" style="width:20px"/>配件盘点管理<br/>
						<input type="checkbox" style="width:20px"/>配件退料管理
					</div></td>
				</tr>
				<tr>
					<td style="width:300px"><font color="red" style="font-size:30px">本次月结转开始日期:</font></td>
					<td><input class="easyui-datetimebox" editable="false" style="width:150px"/></td>
				</tr>
				<tr>
					<td style="width:300px"><font color="red" style="font-size:30px">本次月结转截止日期:</font></td>
					<td><input class="easyui-datetimebox" editable="false" style="width:150px"/></td>
				</tr>
				<tr>
					<td style="width:300px"><font color="red" style="font-size:30px">下次月结转开始日期:</font></td>
					<td><input class="easyui-datetimebox" editable="false" style="width:150px"/></td>
				</tr>
				<tr>
					<td style="width:300px"><font color="red" style="font-size:30px">本次月结提醒时间:</font></td>
					<td><input class="easyui-datetimebox" editable="false" style="width:150px"/></td>
				</tr>
			</table>
			
		</div>
  </body>
</html>
