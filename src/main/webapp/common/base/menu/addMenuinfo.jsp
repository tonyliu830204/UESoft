<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<form id="MenuAddForm">
    <input id="pmenuId"  name="menuId" type="hidden"/>
    <input id="pmenuPid"  name="menuPid" type="hidden"/>
    <input id="person"  name="person" type="hidden"/>
    <input id="childMenu"  name="childMenu" type="hidden"/>
	<table>
        <tr>
            <td width="100">菜单名称:</td>
            <td width="120">
                <input id="pmenuName" name="menuName" onkeydown="EnterPress($('#menuCode'),event)" type="text" class="easyui-validatebox" onkeyup="handle();" 
                data-options="required: true, editable:false,missingMessage:'菜单名称为必填项'" style="width: 200px;" />
            </td>
        </tr>
        <tr>
            <td width="100">菜单唯一CODE值:</td>
            <td width="120">
                <input id="pmenuCode"  name="menuCode" onkeydown="EnterPress($('#menuPid'),event)" type="text" class="easyui-validatebox"  
                data-options="required: true,editable:false,missingMessage:'菜单唯一CODE值为必填项'" style="width: 200px;" />
            </td>
        </tr>
        <tr>
            <td width="100">父菜单:</td>
            <td width="120">
                <input id="pmenuPid1" name="pmenuPid1" style="width:200px;" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath}/menuAction!comboTreeMenu.action'" >
            </td>
        </tr>
        <tr>
            <td width="100">菜单URL:</td>
            <td width="120">
                <input id="purl"  name="url" onkeydown="EnterPress($('#remark'),event)" type="text" class="easyui-validatebox" style="width:200px;" /> 
            </td>
        </tr>
        <tr>
            <td colspan="2">
              <div id="expand" style="width:100%;display:none;text-align:left;">
                  <table>
			            <tr>
			                <td width="100">所属系统:</td>
			                <td width="120">
			                    <input id="systemMenu" name="systemMenu" style="width:200px;" class="easyui-combobox"/> 
			                </td>
			            </tr>
			      </table>     
              </div>
            </td>
        </tr>
        <tr>
            <td width="100">备注:</td>
            <td width="120">
                <textarea id="premark"  name="remark" rows="" cols="" style="width:200px; height:30px;"></textarea>
            </td>
		</tr>
	</table>
</form>