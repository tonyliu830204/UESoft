<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<form id="RoleAddForm">
    <input id="aroleId" name="roleId" type="hidden"/>
    <input id="asystemTypevalue" name="systemTypevalue" type="hidden"/>
    <input id="acheckeds" name="checkeds" type="hidden"/>
    <input id="aselecteds" name="selecteds" type="hidden"/>
    <input id="aperson" name="person" type="hidden"/>
    <input id="acreateDate" name="createDate" type="hidden"/>
    <input id="openect" type="hidden"/>
    <input id="enterpriseId" type="hidden"/>
	<table>
		<tr>
            <td width="90">角色名称:</td>
            <td width="120">
                <input id="aroleName" name="roleName" onkeydown="EnterPress($('#aremark'),event)" type="text" class="easyui-validatebox"  
                data-options="required:true,editable:false,missingMessage:'角色名称为必填项'" style="width:120px;" />
            </td>
            <td width="250">
                <div id="expand" style="width:100%;display:none;text-align:left;">
                  <table>
			            <tr>
			                <td width="90">所属系统:</td>
			                <td width="120">
			                    <input id="asystemType" name="systemType" style="width:120px;" class="easyui-combobox" 
			                    	data-options="disabled:true,
			                    	onLoadSuccess:function(){
			                    		$('#asystemType').combobox('setValue','<%=Contstants.SYSTEMTYPE.ALL %>');
			                    	}"/> 
			                </td>
			            </tr>
			      </table>     
                </div>
            </td>
            <td width="70">备注:</td>
            <td width="120">
                <textarea id="aremark" name="remark" rows="" cols="" style="width:200px;height:30px;"></textarea>
            </td>
		</tr>
	</table>
	<div region="center" fit="true" border="false" style="height:100%;">
	    <div id="tta" class="easyui-layout" border="false" style="width:320px;height:380px;float:left;">
	        <div title="菜单角色关系配置" data-options="region:'center'" style="display:block;width:100%;height:92%;">
	             <ul id="MyTree" class="easyui-tree" data-options="animate:true,dnd:true" style="width:100%;height:85%;"></ul>
	        </div>
	    </div>
	     <div id="tts" class="easyui-layout" border="false" style="width:410px;height:380px;float:left;">
	    <div title="人员角色关系配置" data-options="region:'center'" style="display:block;width:410px;height:380px;">
	           <CENTER>
			     <table border="0" style="width:390px;height:100%;">
					<tr>
					     <td><CENTER>未选择人员</CENTER></td>
					     <td></td>
					     <td><CENTER>已选择人员</CENTER></td>
					 </tr>
			         <tr>
				         <td width="40%" >
							  <select id="tobeselected" name="tobeselected" multiple size="8" style='width:150px;height:100%;'></select>
					     </td>
				         <td width="20%" align="center">
				              <button type="button" id="toright" name="toright">&gt;&gt;</button>
				              <button type="button" id="toleft" name="toleft">&lt;&lt;</button>
					     </td>
				         <td width="40%" >
						      <select id="toselecteds" name="toselecteds" multiple size="8" style='width:150px;height:100%;'></select>
				         </td>
			         </tr>
			      </table>
			   </CENTER>
	        </div>
	     </div>
	</div>
</form>
<div id="making" title=""  data-options="iconCls:'icon-reload',modal:true"
	style="padding: 5px; width: 200px; height: 40px;">
	<font size="26">数据加载中，请稍候...</font>
</div>