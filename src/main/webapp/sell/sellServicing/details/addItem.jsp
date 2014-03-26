<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 预约申请单-添加维修项目 -->
<script type="text/javascript">
	function LoadOk() {
		if (document.readyState == "complete") {
			runs();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	setTimeout("LoadOk();", 200);
	var runs=function (){
		//待选维修项目		
		$selectionItem = $('#selectionItem');
		$selectionItem.datagrid({
			url : 'frtAddItmeAction!findAll.action',
			pagination : true,
			fit : true,
			fitColumns : true,
			singleSelect : true,
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'itemId',
			loadMsg : "数据加载中，请稍候……",
			columns : [ [{
				field : 'itemId',
				title : '项目编号',
				width : 35,
				hidden : true
			},{
				field : 'itemName',
				title : '项目名称',
				width : 100,
				sortable : true
			},{
				field : 'itemCost',
				title : '项目成本',
				width : 60
			},{
				field : 'itemMoney',
				title : '项目金额',
				width : 60
			},{
				field : 'remark',
				title : '备注',
				width : 50,
				sortable : true
			}]],
			onClickRow : function (rowIndex, rowData){
				$(this).datagrid('unselectRow', rowIndex);
			},
			onDblClickRow : function(rowIndex, rowData){
				$(this).datagrid('deleteRow', rowIndex);
				$selectedItem.datagrid('appendRow', rowData);
				var index = $selectedItem.datagrid('getRowIndex', rowData);
				copyDateAndBindEventAndTwo($selectedItem, index, rowData);
			}
		});
		
		//已选维修项目
		$selectedItem = $('#selectedItem');
		$selectedItem.datagrid({
			url:'${pageContext.request.contextPath}/',
			pagination : true,
			fit : true,
			fitColumns : true,
			singleSelect : true,
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'itemId',
			loadMsg : "数据加载中，请稍候……",
			columns : [ [{
				field : 'itemId',
				title : '项目编号',
				width : 35,
				hidden : true
			},{
				field : 'itemName',
				title : '项目名称',
				width : 100,
				sortable : true
			},{
				field : 'itemCost',
				title : '项目成本',
				width : 60,
				editor : {
					type : 'numberbox',
					options : {
						precision : 2,
						min : 0,
						required : true,
						missingMessage : "项目成本为必填项!"
					}
				}
			},{
				field : 'itemMoney',
				title : '项目金额',
				width : 60,
				editor : {
					type : 'numberbox',
					options : {
						precision : 2,
						min : 0,
						required : true,
						missingMessage : "项目金额为必填项!"
					}
				}
			},{
				field : 'remark',
				title : '备注',
				width : 50,
				sortable : true,
				editor : {
					type : 'validatebox',
					options : {
						validType:'characterDigitLength[0,50]'
					}
				}
			}]],
			onClickRow : function (rowIndex, rowData){
			    $(this).datagrid('unselectRow', rowIndex);
			},
			onDblClickRow : function(rowIndex, rowData){
				var ed = $(this).datagrid('getEditors', rowIndex);
				if(ed.length){
					$(this).datagrid('deleteRow', rowIndex);
					$selectionItem.datagrid('appendRow', rowData);
				}
			}
		});
		//$('#selectedItem').datagrid('loadData',staticFrtResevationItems);
	}
	var _query=function (){
		$selectionItem.datagrid('load', serializeObject($('#selectionItemForm')));
	}
	var _clear=function (){
		$('#selectionItemForm').form('clear'); 
		$selectionItem.datagrid('load', serializeObject($('#selectionItemForm')));
	}
</script>
<!-- 新增维修项目 -->
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:80px;">
	    	<form id="selectionItemForm" method="post">
	    		<fieldset style="height:63px">
	    		<legend><strong>查询条件</strong></legend>
				<table>
					<tr>
						<td>项目名称:</td>
						<td><input type="text" style="width:100px" name="itemName"/></td>
						<td>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="_query();">查询</a>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="_clear();">清空</a>
						</td>
					</tr>
				</table>
				</fieldset>
			</form>
	</div> 
	<div data-options="region:'center',border:false" style="background:#eee;" border="false">
		<fieldset style="height: 235px;">
	    	<legend><strong>待选维修项目</strong></legend>
	    	<table id="selectionItem"></table>
	  	</fieldset>
	</div>
	<div data-options="region:'south',border:false" style="background:#eee;height: 155px;">
		<fieldset style="height: 135px;">
	    	<legend><strong>已选维修项目</strong></legend>
	    	<table id="selectedItem"></table>
	  	</fieldset>
	</div>
</div>
