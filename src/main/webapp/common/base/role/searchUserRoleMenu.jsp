<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table>
	<tr>
           <td width="100">角色名称:</td>
           <td width="120">
               <input id="broleName" name="roleName" type="text" class="easyui-validatebox" disabled="disabled"
               data-options="editable:false" style="width:300px;" />
           </td>
	</tr>
</table>
<div region="center" fit="true" border="false">
        <div title="菜单角色关系配置" style="display:block;width:100%;height:100%;">
             <ul id="MyTree" class="easyui-tree" data-options="animate:true,dnd:true" style="width:100%;height:90%;"></ul>
        </div>
</div>