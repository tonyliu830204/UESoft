<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function LoadOk() {
		if (document.readyState == "complete") {
			runs();
		} else {
			setTimeout("LoadOk();", 1000);
		}
	}
	var storeId; 
	var runs=function (){
		$.ajax({ 
			type : "POST",
			url : "carFixAction!findDefaulePartStore.action",
			dataType : "html",
			success:function(r){
				storeId = r;
				$('#storeId').val(r);

				//待选维修配件
				$selectionParts = $('#selectionParts');
				$selectionParts.datagrid({
					url : 'frtPartsQueryAction!findAllParts.action',
					queryParams: {storeId: r},
					height: 225,
					//width: 765,
					fitColumns : true,
					idField : 'id',
					singleSelect : true,
					rownumbers : true,
					pagination:true,
					columns : [[{
						field : 'partsId',
						title : '配件编码',
						width : 60,
						sortable : true
					},{
						field : 'partsName',
						title : '配件名称',
						width : 60,
						sortable : true
					},{
						field : 'pbrdId',
						title : '配件品牌',
						width : 60,
						formatter : function (value,row,index){
							return row.pbrdName;
						},
						sortable : true
					},{
						field : 'ptypeId',
						title : '配件型号',
						width : 60,
						formatter : function (value,row,index){
							return row.ptypeName;
						},
						sortable : true
					},{
						field : 'fitPtype',
						title : '适合车型',
						width : 150,
						formatter : function (value,row,index){
							return row.fitPtypeName;
						},
						sortable : true
					},{
						field : 'punitId',
						title : '单位',
						width : 60,
						hidden : true
					},{
						field : 'punitName',
						title : '单位',
						width : 60
					},{
						field : 'partsNowCount',
						title : '库存数量',
						width : 60,
						sortable : true
					},{
						field : 'partsRepairPrice',
						title : '维修价',
						width : 60,
						sortable : true
					},{
						field : 'storeId',
						title : '仓别',
						width : 60,
						formatter : function (value,row,index){
							return row.storeName;
						},
						sortable : true
					},{
						field : 'storeName',
						title : '仓别',
						width : 60,
						hidden : true
					},{
						field : 'partsRemark',
						title : '备注',
						width : 60
					}]],
					onClickRow : function (rowIndex, rowData){
						$(this).datagrid('unselectRow', rowIndex);
					},
					onDblClickRow : function(rowIndex, rowData){
						var rows = $('#selectedParts').datagrid('getRows');
						if(rows.length){
							for(var i = 0;  i < rows.length; i++){
								if(rows[i].partsId == rowData.partsId){
									alertMsg('对不起,此配件已经选过了', 'warning');
									return;
								}
							}
						}
						$(this).datagrid('deleteRow', rowIndex);
						$selectedParts.datagrid('appendRow', rowData);
						var index = $selectedParts.datagrid('getRowIndex', rowData);
						copyDateAndBindEvent($selectedParts, index, rowData,true);
					}
				});


				
            }
        });
        
		//已选配件
		$selectedParts = $('#selectedParts');
		$selectedParts.datagrid({
			rownumbers : true,
			pagination:true,
		    fit:true,
		    singleSelect:true,
		    pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			fitColumns : true, 
			idField : 'detailId',
			loadMsg : "数据加载中，请稍后……",
			columns : [[
				{
					field : 'detailId',
					title : '配件明细编号',
					width : 60,
					hidden : true
				},{
					field : 'install_id',
					title : '加装编号',
					width : 60,
					hidden : true
				},{
					field : 'partsId',
					title : '配件编码',
					width : 60
				},{
					field : 'partsName',
					title : '配件名称',
					width : 60
				},{
					field : 'punitId',
					title : '单位编号',
					width : 60,
					hidden : true
				},{
					field : 'punitName',
					title : '单位',
					width : 60
				},{
					field : 'partsNum',
					title : '数量',
					width : 60,
					editor : {
						type : 'numberbox',
						options : {
							precision : 2,	
							required : true,
							min : 1,
							missingMessage : "数量为必填项!"
						}
					}
				},{
					field : 'partsRepairPrice',
					title : '单价',
					width : 60,
					editor : {
						type : 'numberbox',
						options : {
							precision : 2,
							min : 0,
							required : true,
							missingMessage : "单价为必填项!"
						}
					}
				},{
					field : 'partsAmount',
					title : '金额',
					width : 60,
					editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							precision : 2,
							min : 0
						}
					}
				},{
					field : 'storeId',
					title : '仓别',
					width : 60,
					formatter : function (value,row,index){
						return row.storeName;
					},
					hidden : true
				},{
					field : 'storeName',
					title : '仓别',
					width : 60,
					hidden : true
				},{
					field : 'posName',
					title : '部位名称',
					width : 60,
					hidden : true
				},{
					field : 'partsNowCount',
					title : '库存量',
					width : 60
				}]],
			onClickRow : function (rowIndex, rowData){
				bindEnterInCloumn($selectedParts, rowIndex, 0);
			},
			onDblClickRow : function(rowIndex, rowData){
				var ed = $(this).datagrid('getEditors', rowIndex);
				if(ed.length){
					$(this).datagrid('deleteRow', rowIndex);
					$selectionParts.datagrid('appendRow', rowData);
				}
			}
		});
	}
	
	var _query = function (){
		$('#storeId').val(storeId);
		$selectionParts.datagrid('load', serializeObject($('#selectionPartsForm')));
	}
	
	var _clear = function (){
		$('#selectionPartsForm').form('clear');
		$('#storeId').val(storeId);
		$selectionParts.datagrid('load', serializeObject($('#selectionPartsForm')));
	}
	setTimeout("LoadOk();", 1000);
</script>
<!-- 新增维修配件 -->
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:80px;">
	    	<form id="selectionPartsForm" method="post">
	    		<fieldset style="height:62px">
	    		<legend><strong>查询条件</strong></legend>
				<table>
					<tr>
						<td>配件编码:</td>
						<td><input type="text" name="partsId"/></td>
						<td>编码二:</td>
						<td><input type="text" name="partsId2"/></td>
						<td>配件名称:</td>
						<td><input type="text" name="partsName"/></td>
					</tr>
					<tr>
						<td>配件品牌:</td>
						<td><input type="text" name="pbrdId" class="easyui-combobox" data-options="
						url : 'frtOptionsAction!findAllPartsBrand.action',
   						valueField:'id',  
   					    textField:'text',
   					    mode:'remote'"/></td>
						<td>配件型号:</td>
						<td><input type="text" name="ptypeName"/></td>
						<td></td>
						<td><input id="storeId" name="storeId" type="hidden" /></td>
				    	<td align="center">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_query();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
						</td>
					</tr>
				</table>
				</fieldset>
			</form>
	</div> 
	<div data-options="region:'center',border:false" style="background:#eee;" border="false">
		<fieldset style="height: 235px;">
	    	<legend><strong>待选配件</strong></legend>
	    	<table id="selectionParts"></table>
	  	</fieldset>
	</div>
	<div data-options="region:'south',border:false" style="background:#eee;height: 155px;">
		<fieldset style="height: 135px;">
	    	<legend><strong>已选配件</strong></legend>
	    	<table id="selectedParts"></table>
	  	</fieldset>
	</div>
</div>