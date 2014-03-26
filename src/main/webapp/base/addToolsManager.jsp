<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>新增工具管理</title>

  </head>
  
  <body>
    <div class="easyui-tabs" fit="true" border="false">
    	<div title="基本信息" border="false" style="background:#eee;">
    		<table>
    			<tr>
    				<td>编号:</td>
    				<td><input type="text"/></td>
    				<td>登记日期:</td>
    				<td><input type="text" class="easyui-datebox" editable="false"/></td>
    			</tr>
    			<tr>
    				<td>名称:</td>
    				<td><input type="text"/></td>
    				<td>规格型号:</td>
    				<td><input type="text"/></td>
    			</tr>
    			<tr>
    				<td>单位:</td>
    				<td><input type="text"/></td>
    				<td>状态:</td>
    				<td>
    					<select style="width: 110px;">
    						<option>待借</option>
    						<option>借出</option>
    						<option>损坏</option>
    						<option>丢失</option>
    						<option>报废</option>
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>分类:</td>
    				<td>
    					<select style="width: 110px;">
    						<option>检测工具</option>
    						<option>维修工具</option>
    						<option>常用工具</option>
    					</select>
    				</td>
    				<td>使用部门:</td>
    				<td><input type="text"/>
    				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true">...</a></td>
    			</tr>
    			<tr>
    				<td>金额:</td>
    				<td><input type="text"/></td>
    			</tr>
    		</table>
    	</div>
    	<div title="购买信息" border="false" style="background:#eee;">
    		<table>
    			<tr>
    				<td>购买日期:</td>
    				<td><input type="text" class="easyui-datebox" editable="false"/></td>
    				<td>供应商:</td>
    				<td><input type="text"/></td>
    			</tr>
    			<tr>
    				<td>电话:</td>
    				<td><input type="text"/></td>
    				<td>联系人:</td>
    				<td><input type="text"/></td>
    			</tr>
    			<tr>
    				<td>传真:</td>
    				<td><input type="text"/></td>
    				<td>邮政编码:</td>
    				<td><input type="text"/></td>
    			</tr>
    			<tr>
    				<td>联系人:</td>
    				<td><input type="text"/></td>
    				<td>采购员:</td>
    				<td><input type="text"/></td>
    			</tr>
    			<tr>
    				<td>申请人:</td>
    				<td><input type="text"/></td>
    			</tr>
    		</table>
    	</div>
    	<div title="维修记录" border="false" style="padding: 5px; background:#eee;">
    		<textarea rows="" cols="" style="width: 350px; height: 160px;"></textarea>
    	</div>
    </div>
  </body>
</html>
