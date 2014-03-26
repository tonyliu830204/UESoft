var pdtitle = '盘点单汇总';
var editState = true;
var tag = false;
$(function(){
	//盘点单汇总
	$('#pandian_huizong_id').datagrid({
		url : 'accesssoriesInventoryAction!doFind.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		singleSelect : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'stinvm_Id',
			title : '盘点单号',
			width : 80,
			sortable : true
		}, {
			field : 'stinvm_Time',
			title : '盘点日期',
			width : 80,
			sortable : true
			
		}, {
			field : 'syinvm_Sum_Count',
			title : '盘点数量',
			width : 40,
			sortable : true
		
		}, {
			field : 'stinvm_Sum_Amount',
			title : '含税金额',
			width : 40,
			sortable : true
			
		}, {
			field : 'stinvm_Sum_Cost',
			title : '未税金额',
			width : 40,
			sortable : true
			
		}, {
			field : 'stf_Id',
			title : '经办人',
			hidden : true
		}, {
			field : 'stf_Name',
			title : '经办人',
			width : 40,
			sortable : true
		
		}, {
			field : 'store_Id',
			title : '仓别',
			hidden : true
		},  {
			field : 'store_Name',
			title : '仓别',
			width : 40,
			sortable : true
		}, {
			field : 'stinvm_State',
			title : '审核状态',
			hidden : true
		}, {
			field : 'stinvm_StateName',
			title : '审核状态',
			width : 40,
			sortable : true
		}, {
			field : 'stinvm_Remark',
			title : '备注',
			width : 200,
			sortable : true
		}]],
		onDblClickRow:function(rowIndex, rowData){
		    editState = false;
		    tag = true;
		    clerFrom();
		    loadDetailData(rowData);
        }
	});

	//盘点明细
	$('#pandian_mingxi_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'parts_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'index_Id',
			title : '配件明细编号',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'stinvm_Id',
			title : '盘点单号',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'parts_Id',
			title : '配件编码',
			width : 100,
			sortable : true
			
		}, {
			field : 'parts_Name',
			title : '配件名称',
			width : 100,
			sortable : true
		
		}, {
			field : 'punit_Name',
			title : '单位',
			width : 100,
			sortable : true
			
		}, {  
			field : 'stinvd_Count',
			title : '数量',
			width : 40,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					missingMessage : '此项为必填项'
				}
			}
		
		}, {
			field : 'stinvd_Price',
			title : '未税价',
			width : 40,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2,
					missingMessage : '此项为必填项'
				}
			}
		}, {
			field : 'stinvd_Cost',
			title : '未税金额',
			width : 40,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2
				}
			}
		}, {
			field : 'stinvd_Price1',
			title : '含税价',
			width : 40,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2,
					missingMessage : '此项为必填项'
				}
			}
		}, {
			field : 'stinvd_Cost1',
			title : '含税金额',
			width : 40,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2
				}
			}
		}, {
			field : 'parts_Library',
			title : '库位',
			width : 100,
			sortable : true
		}]],
		onLoadSuccess:function(rowData){
		    if(editState)
		         beginEditAll($('#pandian_mingxi_id'));
        }
	});
});

function dbbutton(){
	var storeID = $('#store_Name_id').combobox('getValue');
	if(storeID!=""){
	    var d = $('<div/>').dialog({
			href : projectPath+"system_management/accessories_find.jsp",
			modal:true,
			closable : true,
			title : '配件选择',
			width : 800,
			height : 500,
			buttons : [{
				text : '确定',
				handler : function (){
					var rows = $('#datagrid_accessories_find_id').datagrid('getSelections')
					if(rows.length > 0){
				        dbClickRow(rows);
					}
					d.dialog('close');
					beginEditAll($('#pandian_mingxi_id'));
				}
			},{
				text : '取消',
				handler : function (){
					d.dialog('close');
				}
			}],
		    onLoad : function (){
			     $('#tabs_accessories_find_id').tabs('select','盘点单明细'); 
			     $('#datagrid_accessories_find_id').datagrid({url : 'accesssoriesInventoryAction!doFindAccessInfor.action?store_Id='+storeID});
	        },
			onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
	}else{
		alertMsg("请选择仓别","info");
	}
}

//双击配件选择datagrid行是触发调用此方法
function dbClickRow(rowData){
	if(rowData.length>0){
		for(var i = 0;  i < rowData.length; i++){
		    $('#pandian_mingxi_id').datagrid('appendRow', rowData[i]);
		}
	}
}

//点击新增按钮时触发  
function dbAddButtom(){
	editState = true;
	reloadpagr();
	var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="doSaveOrUpdate(1);">保存</a>';
	var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	if ($('#addbutton').children('a').length == 0) {
		$('#addbutton').append(save).append(cancel);
		$.parser.parse('#addbutton');
	}
	$('#linkbutton_add_id').linkbutton('enable');             
	$('#linkbutton_delete_id').linkbutton('enable');
	$('#tabs_accessories_find_id').tabs('select','盘点单明细');
}

//点击修改按钮时触发  
function dbEditButtom(b){
	var value = $('#pandian_huizong_id').datagrid('getSelections');
	if(value.length==0){
		alertMsg('对不起，请先选择要修改的记录！', 'warning');
		return;
	}else if(value[0].stinvm_State == sate){
		alertMsg('对不起，该记录已审核不可修改！', 'warning');
		return;
	}else{
		editState = true;
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="doSaveOrUpdate(2);">保存</a>';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
		$('#linkbutton_add_id').linkbutton('enable');             
		$('#linkbutton_delete_id').linkbutton('enable');
		if ($('#editbutton').children('a').length == 0) {
			$('#editbutton').append(save).append(cancel);
			$.parser.parse('#editbutton');
		}
		beginEditAll($('#pandian_mingxi_id'));
		reloadpagr();
		loadDetailData(value[0]);
		$('#tabs_accessories_find_id').tabs('select','盘点单明细');
	}
}

//开启所有行的编辑
function beginEditAll(id){
	var rows = id.datagrid('getRows');
	if(rows.length>0){
		for ( var i = 0; i < rows.length; i++) {
			var index = id.datagrid('getRowIndex',rows[i]);
			id.datagrid('beginEdit',index);
			//盘点明细 绑定键盘事件
			doKeyPress(id, index, rows[i]);
		}
	}
}

//盘点明细 绑定键盘事件
function doKeyPress(id, rowIndex, rowData){
	id.datagrid('beginEdit', rowIndex);
	var ed = id.datagrid('getEditors', rowIndex);
	if(ed[0]){
		if(rowData.stinvd_Count==null||rowData.stinvd_Count==''){
			ed[0].target.numberbox('setValue', '1.00');
		}else{
			ed[0].target.numberbox('setValue', rowData.stinvd_Count);
		}
		ed[0].target.select();
		ed[0].target.click(function (){
		    ed[0].target.select();
	    });
	    ed[0].target.keydown(function (e){
		   if(e.keyCode == '13'){
			   ed[1].target.select();
		   }
	    });
	}
	if(ed[1]){
		if(rowData.stinvd_Price==null||rowData.stinvd_Price==''){
			ed[1].target.numberbox('setValue', '1.00');
		}else{
			ed[1].target.numberbox('setValue', rowData.stinvd_Price);
		}
		ed[1].target.click(function (){
			ed[1].target.select();
		});
		ed[1].target.keydown(function (e){
			if(e.keyCode == '13'){
				ed[2].target.numberbox('setValue', accMul(parseFloat(ed[0].target.val()),parseFloat(ed[1].target.val())));
				ed[3].target.select();
			}
		});
	}
	if(ed[2]){
		if(rowData.stinvd_Cost==null || rowData.stinvd_Cost==''){
			ed[2].target.numberbox('setValue', accMul(parseFloat(ed[0].target.val()),parseFloat(ed[1].target.val())));
		}else{
			ed[2].target.numberbox('setValue', rowData.stinvd_Cost);
		}
	}
	if(ed[3]){
		if(rowData.stinvd_Price1==null||rowData.stinvd_Price1==''){
			ed[3].target.numberbox('setValue', '1.00');
		}else{
			ed[3].target.numberbox('setValue', rowData.stinvd_Price1);
		}
		ed[3].target.click(function (){
			ed[3].target.select();
		});
		ed[3].target.keydown(function (e){
			if(e.keyCode == '13'){
				ed[4].target.numberbox('setValue', accMul(parseFloat(ed[0].target.val()),parseFloat(ed[3].target.val())));
				if (rowIndex < id.datagrid('getRows').length - 1) {
					id.datagrid('endEdit', rowIndex);
					id.datagrid('acceptChanges');
					id.datagrid('selectRow', rowIndex + 1);
					doKeyPress(id, rowIndex + 1, id.datagrid('getSelected'));
				} else {
					id.datagrid('endEdit', rowIndex);
					id.datagrid('acceptChanges');
					id.datagrid('selectRow',0);
					var rowData=id.datagrid('getSelected');
					doKeyPress(id, 0, rowData);
				}
			}
		});
	}
	if(ed[2]){
		if(rowData.stinvd_Cost1==null || rowData.stinvd_Cost1==''){
			ed[4].target.numberbox('setValue', accMul(parseFloat(ed[0].target.val()),parseFloat(ed[3].target.val())));
		}else{
			ed[4].target.numberbox('setValue', rowData.stinvd_Cost1);
		}
	}
}

//计算本次盘点的总数量及总金额
function getPriceAndCount(){
	var rowValue = $('#pandian_mingxi_id').datagrid('getData');
	var syinvm_Sum_Count = 0;
	var stinvm_Sum_Amount = 0;
	var stinvm_Sum_Cost = 0;
	for(var i=0; i < rowValue.rows.length; i++){
		//获取行索引
		var index = $('#pandian_mingxi_id').datagrid('getRowIndex',rowValue.rows[i]);
		var edter = $('#pandian_mingxi_id').datagrid('getEditors', index);
		syinvm_Sum_Count = (parseFloat(syinvm_Sum_Count)) + (parseFloat(rowValue.rows[i].stinvd_Count));
		stinvm_Sum_Cost = (parseFloat(stinvm_Sum_Amount)) + (parseFloat(rowValue.rows[i].stinvd_Price));
		stinvm_Sum_Amount = (parseFloat(stinvm_Sum_Cost)) + (parseFloat(rowValue.rows[i].stinvd_Price1));
	}
	$('#syinvm_Sum_Count').val(syinvm_Sum_Count);
	$('#stinvm_Sum_Amount').val(stinvm_Sum_Amount);
	$('#stinvm_Sum_Cost').val(stinvm_Sum_Cost);
}

//条件查询提交
function doConditionSubmit(){
	$('#pandian_huizong_id').datagrid('load',serializeObject($('#form_pandianhuizong_id')));
}

//新增或修改盘点明细后保存
function doSaveOrUpdate(a){
	//所有行结束编辑
	endEdit($('#pandian_mingxi_id'));
	//$('#pandian_mingxi_id').datagrid('acceptChanges');
	getPriceAndCount();
	var formvalues = serializeObject($('#form_pandianmingxi_id'));
	//将datagrid数据格式化成字符串对象
	var effectRow = saveAll2($('#pandian_mingxi_id'), formvalues);
	if(effectRow){
		//判断是新增还是修改的
	    if(a==1){
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'accesssoriesInventoryAction!doAddFather.action',
			   data: effectRow,
			   success: function(r){
				    alertMsg(r.msg, 'info');
				    if(r.success){
					    $('#pandian_huizong_id').datagrid('reload');
						//保存成功后隐藏保存按钮
						$('#addbutton').empty();
						//配件新增按钮配件删除按钮禁用
						$('#linkbutton_add_id').linkbutton('disable');             
						$('#linkbutton_delete_id').linkbutton('disable');
						$('#tabs_accessories_find_id').tabs('select','盘点单汇总');
						reloadpagr();
				    }
			   }
			}); 
	    }  
		if(a==2){
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'accesssoriesInventoryAction!doUpdateFather.action',
			   data: effectRow,
			   success: function(r){
				   if(r.success){
					    $('#pandian_huizong_id').datagrid('reload');
						//保存成功后隐藏保存按钮
						$('#editbutton').empty();
						//配件新增按钮配件删除按钮禁用
						$('#linkbutton_add_id').linkbutton('disable');             
						$('#linkbutton_delete_id').linkbutton('disable');
						$('#tabs_accessories_find_id').tabs('select','盘点单汇总');
						reloadpagr();
					}
			    }
			});
		}
    }
}	

//删除方法
function doDelete(){
	var id = $('#pandian_huizong_id');
    var delrow = id.datagrid('getSelections');
    var value = $('#pandian_huizong_id').datagrid('getSelections');
    if(delrow.length>0){
    	if(delrow[0].stinvm_State == sate){
    		alertMsg('对不起，该记录已审核不可删除！', 'warning');
    		return;
    	}else{
	    	$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
	    		if(b){
	    			$.ajax({
	    				type: 'post',
	    				dataType: 'json',
	    				url: 'accesssoriesInventoryAction!doDelete.action',
	    				data: delrow[0],
	    				success : function(d){
	    				alertMsg(d.msg, 'info');
	    				if(d.success){
	    					$('#tabs_accessories_find_id').tabs('select','盘点单汇总');
	    					id.datagrid('clearSelections');
	    					id.datagrid("reload");//刷新
	    					reloadpagr();
	    				}
	    			}
	    			});
	    		}
	    	});
    	}
	}else{
		alertMsg('对不起，请先选择要删除的记录！', 'warning');
		return;
	}
}

//取消
function cancel(){
	$('#addbutton').empty();
	$('#editbutton').empty();
	$('#linkbutton_add_id').linkbutton('disable');             
	$('#linkbutton_delete_id').linkbutton('disable');
	$('#tabs_accessories_find_id').tabs('select','盘点单汇总');
	reloadpagr();
	tag = false;
}

//清空编辑
function reloadpagr(){
	$('#pandian_mingxi_id').datagrid('loadData',{"total":1,"rows":[]});
	clerFrom();
}

function clerFrom(){
   $('#form_pandianmingxi_id').find('input').val(''); 
   $('#form_pandianmingxi_id').find('select').val('');
   $('#form_pandianmingxi_id').find('textarea').val('');
}

function loadDetailData(data){
	//将现在新选的行重新load在from表单中
	$('#tabs_accessories_find_id').tabs('select','盘点单明细'); 
	$('#form_pandianmingxi_id').form('load', data);
	$('#pandian_mingxi_id').datagrid({url : 'accesssoriesInventoryAction!getStInventoryDetailById.action?stinvm_Id='+data.stinvm_Id});
}

//清空
function doClear(){
	$('#form_pandianhuizong_id').form('clear');
	doConditionSubmit();
}

//审核方法
function doShenhe(){
	var id = $('#pandian_huizong_id');
    var delrow = id.datagrid('getSelections');
    if(delrow.length > 0){
    	if(delrow[0].stinvm_State == sate){
    		alertMsg('对不起，该记录已审核不可审核！', 'warning');
    		return;
    	}else{
	    	$.messager.confirm('优亿软件提示','请确认是否要审核该条记录？',function(b){
	    		if(b){
	    			$.ajax({
	    				type: 'post',
	    				dataType: 'json',
	    				url: 'accesssoriesInventoryAction!doShenhe.action',
	    				data: delrow[0],
	    				success : function(d){
	    				alertMsg(d.msg, 'info');
	    				if(d.success){
	    					$('#tabs_accessories_find_id').tabs('select','盘点单汇总');
	    					id.datagrid('clearSelections');
	    					id.datagrid("reload");//刷新
	    					reloadpagr();
	    				}
	    			}
	    			});
	    		}
	    	});
    	}
	}else{
		alertMsg('对不起，请先选择要审核的记录！', 'warning');
		return;
	}
}

function _exception(){
	showEditDialog("pandian_huizong_id",null,"pandian_huizong_idDiv","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"盘点单"+getSystemTime());
}