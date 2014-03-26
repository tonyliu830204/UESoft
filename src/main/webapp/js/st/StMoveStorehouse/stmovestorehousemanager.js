var msdmManagerName;
var editrow=undefined;

$(function(){
	 $('#msdmDateEnd').val(getSystemTime());
 getStartDate($('#msdmDateStart'));
$('#msdmDate').validatebox({  
required:true,
missingMessage:'销售日期必填'
});  

$.ajax({
	type : 'POST', 
url : 'StMoveStoreHouseAction!clear.action',
data : '',
dataType : 'json',
success : function(r){
     $('#StMoveStorehouseDetailTable').datagrid('loadData',r); 
  }
}); 
 
msdmManagerName=$('#msdmManagerName').val();

$('#inStoreId').combobox({disabled:true});

$('#outStoreId').combobox({disabled:true});

$('#outStoreId').combobox({onSelect:function(record){
$('#outStoreId').combobox({disabled:true});
$('#outStoreId').combobox('setText',record.text);
$('#outStoreId').combobox('setValue',record.id);
$('#inStoreId').combobox({disabled:false});
 }
});

$('#inStoreId').combobox({onSelect:function(record){
var outStoreId=$('#outStoreId').combobox('getValue')
var inStoreId=$('#inStoreId').combobox('getValue')
if(outStoreId==inStoreId){
	 $.messager.alert('优亿软件提示','移入仓信息不能和移出仓信息相同，请重新选择！','info',function(){
		 $('#inStoreId').combobox('unselect',inStoreId);
	 });
}else{
	$('#inStoreId').combobox({disabled:true});
	$('#inStoreId').combobox('setText',record.text);
	$('#inStoreId').combobox('setValue',record.id);
	}
  }
});

$('#smsh_addParts').linkbutton('disable');
$('#smsh_deleteParts').linkbutton('disable');

//移仓单汇总信息
$('#stMoveStorehouseMainTable').datagrid({
url:'StMoveStoreHouseAction!searchByCondition.action',
fit:true,
idField:'msdmId',
fitColumns:true,
singleSelect:true,
pageSize : 10,
sortName:'msdmId',
sortOrder:'desc',
pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
pagination:true,
loadMsg:'数据加载中...',
columns:[[{ title : '移仓单号',field : 'msdmId',width : 100,sortable:true
		}, {title : '日期',field : 'msdmDate',hidden:true
		}, {title : '移仓日期',field : 'msdmDateView',width : 100,sortable:true
		}, {title : '移仓数量',field : 'msdmSumCnt',width : 100,sortable:true
		}, {title : '移仓金额',field : 'msdmSumAmont',width : 100,sortable:true
		}, {title : '经办人',field : 'msdmManagerName',width : 100,sortable:true
		}, {title : '经办人id',field : 'msdmManager',width : 100,hidden:true
		}, {title : '移出仓',field : 'outStoreId',width : 100,hidden:true
		}, {title : '移出仓',field : 'msdmOutStorehouseName',width : 100,sortable:true
		}, {title : '移出仓',field : 'inStoreId',width : 100,hidden:true
		}, {title : '接受仓',field : 'msdmInStorehouseName',width : 100,sortable:true
		}, {title : '审核状态',field : 'examine',width : 100,sortable:true
		}, {title : '备注',field : 'msdmRemark',width : 100,sortable:true }
        ]],
 onDblClickRow:function(rowIndex, rowData){
   $('#tt').tabs('select','移仓单明细');  
   clearFormAndDatagrid();
   $('#msdmId').val(rowData.msdmId);
   $('#msdmDate').val(rowData.msdmDate);
   $('#msdmSumCnt').val(rowData.msdmSumCnt);
   $('#msdmSumAmont').val(rowData.msdmSumAmont);
   $('#msdmManagerName').val(rowData.msdmManagerName);
   $('#msdmManager').val(rowData.msdmManager);
   $('#msdmRemark').val(rowData.msdmRemark);
   $('#outStoreId').combobox({disabled:true});
   $('#inStoreId').combobox({disabled:true});
   $('#outStoreId').combobox('setValue',rowData.outStoreId);
   $('#outStoreId').combobox('setText',rowData.msdmOutStorehouseName);
   $('#inStoreId').combobox('setValue',rowData.inStoreId);
   $('#inStoreId').combobox('setText',rowData.msdmInStorehouseName);
   $('#examinestate').val(rowData.examine);
   $.ajax({
		type : 'POST', 
		url : 'StMoveStoreHouseAction!searchStMoveStorehouseDetailByMsdmId.action',
		data : 'msdmId='+rowData.msdmId,
		dataType : 'json',
		success : function(r){
	         $('#StMoveStorehouseDetailTable').datagrid('loadData',r); 
             }
       });
	 }
 });

//移仓单明细
$('#StMoveStorehouseDetailTable').datagrid({
url:'',
idField:'partsId',
fit:true,
fitColumns:true,
pagination:true,
singleSelect:true,
columns:[[ {title : '配件编码',field : 'partsId',width : 100
		}, {title : '配件名称',field : 'partsName',width : 100
		}, {title : '单位',field : 'punitName',width : 100
		}, {title : '库存量',field : 'partsNowCount',width : 50
		}, {
			title : '数量',field : 'msdCnt',width : 100,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 0.1,
					precision : 2,
					missingMessage : "采购数量为必填项!"
				}
			}
		}, {
			title : '数量',field : 'msdCnt1',width : 100,hidden:true,
			editor : {
				type : 'numberbox',
				options : {
					min : 0.1,
					precision : 2
				}
			}
		}, {title : '未税价',field : 'notaxCast',width : 100
		}, {
			title : '未税金额',field : 'msdNocastAmont',width : 100,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2
				}
			}
		}, {title : '库位',field : 'partsLibrary',width : 100
		}, {title : '备注',field : 'msdRemark',width : 100,
			editor : {
				type : 'text',
				options : {
					enabled : true
				}
			}
		}
        ]],
		onDblClickRow:function(rowIndex, rowData){
            if(editrow!=undefined)
                copyPartsDateAndBindEventItem($('#StMoveStorehouseDetailTable'), rowIndex, rowData)
            else
            	$.messager.alert('优亿软件提示','抱歉，请先选择执行地操作类型！','info',function(){}); 	
        }
});

//移仓单汇总信息
$('#stMoveStorehouseUnExamineTable').datagrid({
	url:'StMoveStoreHouseAction!loadUnExamineStMoveStorehouse.action',
	fit:true,
	idField:'msdmId',
	fitColumns:true,
	singleSelect:true,
    pageSize : 10,
    sortName:'msdmId',
	sortOrder:'asc',
	pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	pagination:true,
	loadMsg:'数据加载中...',
	columns:[[{ title : '移仓单号',field : 'msdmId',width : 100,sortable:true
			}, {title : '日期',field : 'msdmDate',hidden:true
			}, {title : '移仓日期',field : 'msdmDateView',width : 65,sortable:true
			}, {title : '移仓数量',field : 'msdmSumCnt',width : 50,sortable:true
			}, {title : '移仓金额',field : 'msdmSumAmont',width : 50,sortable:true
			}, {title : '经办人',field : 'msdmManagerName',width : 50,sortable:true
			}, {title : '经办人id',field : 'msdmManager',hidden:true
			}, {title : '移出仓',field : 'outStoreId',hidden:true
			}, {title : '移出仓',field : 'msdmOutStorehouseName',width : 45,sortable:true
			}, {title : '移出仓',field : 'inStoreId',hidden:true
			}, {title : '接受仓',field : 'msdmInStorehouseName',width : 45,sortable:true
			}, {title : '审核状态',field : 'examine',width : 50,sortable:true
			}, {title : '备注',field : 'msdmRemark',width : 40,sortable:true }
	        ]],
	  onClickRow:function(rowIndex, rowData){
		//加载移出仓配件信息
		$.ajax({ 
			type : 'POST', 
			url : 'StMoveStoreHouseAction!loadMoveOutPartsDetail.action',
			data : 'msdmId='+rowData.msdmId,
			dataType : 'json',
			success : function(r){
		         $('#stMoveOutParstTable').datagrid('loadData',r); 
             }
        }); 
		//加载移入仓配件信息
		$.ajax({ 
			type : 'POST', 
			url : 'StMoveStoreHouseAction!loadMoveInPartsDetail.action',
			data : 'msdmId='+rowData.msdmId,
			dataType : 'json',
			success : function(r){
		         $('#stMoveInParstTable').datagrid('loadData',r); 
             }
        }); 
      },
	 onDblClickRow:function(rowIndex, rowData){
    	   $('#tt').tabs('select','移仓单明细');
    	   clearFormAndDatagrid();
  		   $('#msdmId').val(rowData.msdmId);
  		   $('#msdmDate').val(rowData.msdmDate);
  		   $('#msdmSumCnt').val(rowData.msdmSumCnt);
  		   $('#msdmSumAmont').val(rowData.msdmSumAmont);
  		   $('#msdmManagerName').val(rowData.msdmManagerName);
  		   $('#msdmManager').val(rowData.msdmManager);
  		   $('#msdmRemark').val(rowData.msdmRemark);
  		   $('#outStoreId').combobox({disabled:true});
  		   $('#inStoreId').combobox({disabled:true});
  		   $('#outStoreId').combobox('setValue',rowData.outStoreId);
  		   $('#outStoreId').combobox('setText',rowData.msdmOutStorehouseName);
  		   $('#inStoreId').combobox('setValue',rowData.inStoreId);
  		   $('#inStoreId').combobox('setText',rowData.msdmInStorehouseName);
  		   $('#examinestate').val(rowData.examine);
  		   $.ajax({
  				type : 'POST', 
  				url : 'StMoveStoreHouseAction!searchStMoveStorehouseDetailByMsdmId.action',
  				data : 'msdmId='+rowData.msdmId,
  				dataType : 'json',
  				success : function(r){
  			         $('#StMoveStorehouseDetailTable').datagrid('loadData',r); 
  	             }
  	       });
	 }
 });

$('#stMoveOutParstTable').datagrid({
	fit:true,
	title:'移出仓配件信息',
	idField:'partsId',
	fitColumns:true,
	singleSelect:true,
    pageSize : 10,
    sortName:'partsId',
	sortOrder:'asc',
	pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	pagination:true,
	loadMsg:'数据加载中...',
	columns:[[{ title : '配件编号',field : 'partsId',width : 100
			}, {title : '含税成本',field : 'taxprice',width : 100
			}, {title : '未税成本',field : 'notaxprice',width : 100
			}, {title : '库存量',field : 'partsNowCount',width : 100
			}, {title : '移出仓编号',field : 'storeId',width : 100,hidden:true
			}, {title : '移出仓名称',field : 'storeName',width : 100
			}]]
 });

$('#stMoveInParstTable').datagrid({
	fit:true,
	title:'移入仓配件信息',
	idField:'partsId',
	fitColumns:true,
	singleSelect:true,
    pageSize : 10,
    sortName:'partsId',
	sortOrder:'asc',
	pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	pagination:true,
	loadMsg:'数据加载中...',
	columns:[[{ title : '配件编号',field : 'partsId',width : 100
			}, {title : '含税成本',field : 'taxprice',width : 100
			}, {title : '未税成本',field : 'notaxprice',width : 100
			}, {title : '库存量',field : 'partsNowCount',width : 100
			}, {title : '移出仓编号',field : 'storeId',width : 100,hidden:true
			}, {title : '移出仓名称',field : 'storeName',width : 100
				}]]
	 });
	
   })
   
/*****************************************自定义工具函数******************************************/
   
     //编辑datagrid，绑定事件
 function copyPartsDateAndBindEventItem(id, rowIndex, rowData)
 {
	id.datagrid('beginEdit', rowIndex);
	var ed= id.datagrid('getEditors', rowIndex);
	if(ed[0]){
		ed[0].target.select();
		ed[0].target.numberbox('setValue',rowData.msdCnt);
		ed[1].target.numberbox('setValue',rowData.msdCnt);
		ed[0].target.click(function (){
			ed[0].target.select();
		});
		ed[0].target.keydown(function (e){
			if(e.keyCode == '13'){
				ed[3].target.select();
		    }
		});
	}
	if(ed[2])
		ed[2].target.numberbox('setValue',rowData.msdNocastAmont);
	if(ed[3])
	{
		ed[3].target.text('setValue','');
		ed[3].target.click(function (){
			ed[3].target.select();
		});
		ed[3].target.keydown(function (e){
			if(e.keyCode == '13'){
				if(rowIndex < id.datagrid('getData').total - 1){
					id.datagrid('selectRow',rowIndex + 1);
					copyPartsDateAndBindEventItem(id, rowIndex + 1,id.datagrid('getSelected'));
				}else{
					var ed = id.datagrid('getEditors', 0);
					ed[0].target.select();
				}
		    }
		});
	}
	ed[0].target.select();
    ed[0].target.bind('keyup', function() {
		var num = ed[0].target.val();//代表采购数量
		ed[1].target.numberbox('setValue','3.1415926646258');
		if(num==null||num==''||num==0){
			num=1;
			ed[0].target.numberbox(num);
		}
		
		ed[2].target.numberbox('setValue',accMul(parseFloat(num), parseFloat(rowData.notaxCast)));
		if(rowData && rowData.msdCnt){
			if(parseFloat(ed[0].target.val())<=0){
				alertMsg('移仓数量不能小于等于零！', 'warning');
				ed[0].target.numberbox('setValue', '1.0');
			}else if(parseFloat(ed[0].target.val())>parseFloat(rowData.partsNowCount)){
				alertMsg('移仓数量不能大于库存量！', 'warning');
				ed[0].target.numberbox('setValue', rowData.partsNowCount);
			}
		}
		ed[1].target.numberbox('setValue',ed[0].target.val());
      });
    
      //移仓数量   变动
      ed[1].target.numberbox({
			onChange : function (newValue, oldValue){
    	      var msdmSumCnt=accAdd(accSub(parseFloat($('#msdmSumCnt').val()),parseFloat(oldValue)),parseFloat(newValue));
    	      $('#msdmSumCnt').val(msdmSumCnt);
    	      ed[2].target.numberbox('setValue', accMul(parseFloat(ed[0].target.val()),parseFloat(rowData.notaxCast)));
			}
	  });
      
      //移仓金额    变动
      ed[2].target.numberbox({
			onChange : function (newValue, oldValue){
    	    	  $('#msdmSumAmont').val(accAdd(accSub(parseFloat($('#msdmSumAmont').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
 }

 /**1.清除表单及明细数据**/
 function clearFormAndDatagrid()
 {
	 $('#stMoveStorehouseMainForm').form('clear');
     $.ajax({                              //清空退料明细页面数据
				type : 'POST', 
				url : 'StMoveStoreHouseAction!clear.action',
				data : '',
				dataType : 'json',
				success : function(r){
			         $('#StMoveStorehouseDetailTable').datagrid('loadData',r); 
	             }
	  }); 
 }
 
 /**2.移仓总数量，移仓未税总金额计算**/
 function calculate()
 {
	$.ajax({
		type : 'POST',
		url : 'StMoveStoreHouseAction!getSum.action',
		data : '',
		dataType : 'json',
		success : function(r){
		     $('#msdmSumCnt').val(r.msdmSumCnt);
		     $('#msdmSumAmont').val(r.msdmSumAmont);
        }
    });
 }
 
 /**3.添加保存取消按钮**/
 function addPersonnel(i)
 {
	 $('#tt').tabs('select','移仓单明细');
	 if(i==1){//添加状态
	   var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addStMoveStorehouse();">保存';
	   var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="add_cancel();">取消';
	   if ($('#button').children('a').length == 0) {
		  $('#button').append(save).append(cancel);
		  $.parser.parse('#button');
	   }
	   clearFormAndDatagrid();
	   $('#msdmDate').val(getSystemTime2());
	   $('#outStoreId').combobox({disabled:false});
	   $('#msdmManagerName').val(msdmManagerName);
	   editrow='add';
	   disableBtn();
	 }else{//修改状态
		 if($('#msdmId').val()!=null&&$('#msdmId').val()!=''){
			 if($('#examinestate').val()!='已审核'){
				 var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updateStMoveStorehouse();">保存';
				 var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="update_cancel();">取消';
				 if ($('#button').children('a').length == 0) {
					  $('#button').append(save).append(cancel);
					  $.parser.parse('#button');
				 }
				 disableBtn();
				 editrow='update'
			 }else
				 $.messager.alert('优亿软件提示','对不起，该移仓单已被审核，不能进行修改！','info',function(){}); 
		 }else
			 $.messager.alert('优亿软件提示','对不起，请选择要修改的移仓单信息！','info',function(){}); 
	 }
}
 
/**4.清除保存取消按钮**/
function add_cancel()
{
	$('#inStoreId').combobox({disabled:true})
	$('#outStoreId').combobox({disabled:true})
	clearFormAndDatagrid();
	$('#button').empty();
	enableBtn();
	editrow=undefined;
}
function update_cancel()
{
    $('#button').empty();
    enableBtn();
    editrow=undefined;
}

/**5.设置页面配件新增，删除按钮不可用，汇总新增，删除，修改按钮为可用状态**/
function enableBtn() {
	$('#smsh_add').linkbutton('enable');
	$('#smsh_delete').linkbutton('enable');
	$('#smsh_update').linkbutton('enable');
	$('#smsh_searchBtn').linkbutton('enable');
	$('#smsh_clearBtn').linkbutton('enable');
	$('#smsh_printBtn').linkbutton('enable');
	$('#smsh_exportBtn').linkbutton('enable');
	$('#smsh_examineBtn').linkbutton('enable');
	$('#smsh_addParts').linkbutton('disable');
	$('#smsh_deleteParts').linkbutton('disable');
	$('#msdmDate').validatebox('remove');
	$('#msdmDate').val(getSystemTime2());
}

/** 6.设置页面配件新增，删除按钮可用，汇总新增，删除，修改按钮为不可用状态* */
function disableBtn() {
	$('#smsh_add').linkbutton('disable');
	$('#smsh_delete').linkbutton('disable');
	$('#smsh_update').linkbutton('disable');
	$('#smsh_searchBtn').linkbutton('disable');
	$('#smsh_clearBtn').linkbutton('disable');
	$('#smsh_printBtn').linkbutton('disable');
	$('#smsh_exportBtn').linkbutton('disable');
	$('#smsh_examineBtn').linkbutton('disable');
	$('#smsh_addParts').linkbutton('enable');
	$('#smsh_deleteParts').linkbutton('enable');
	$('#msdmDate').validatebox('reduce');
}

/** **************************自定义实际页面操作函数************************************ */

/**1.移仓单明细配件信息删除**/
function deleteParts()
{
	var selected=$('#StMoveStorehouseDetailTable').datagrid('getSelected');
	if(selected!=null){
    	   $.messager.confirm('优亿软件提示', '你确定要删除该配件信息吗?', function(r) {
			  if(r)
			     $.ajax({
					type : 'POST',
					url : 'StMoveStoreHouseAction!deleteStMoveStorehouseDetail.action',
					data : 'partsId='+selected.partsId,
					dataType : 'json',
					success : function(r){
				       $('#StMoveStorehouseDetailTable').datagrid('loadData',r);
				       calculate();
			        }
			     });
			});
	}else
		 $.messager.alert('优亿软件提示','请选择要删除的配件！','info',function(){}); 


}

/**2.移仓单汇总添加**/
function addStMoveStorehouse()
{
	var rows=$('#StMoveStorehouseDetailTable').datagrid('getRows');
	if(rows.length>0){
		endEdit($('#StMoveStorehouseDetailTable'));
		var inserted = $('#StMoveStorehouseDetailTable').datagrid('getChanges', 'inserted');
		var deleted = $('#StMoveStorehouseDetailTable').datagrid('getChanges', 'deleted');
		var updated = $('#StMoveStorehouseDetailTable').datagrid('getChanges', 'updated');
		if(inserted!=''||inserted!=''||updated!=''){
		   if(rows.length>0)
			    var effectRow = saveAll($('#StMoveStorehouseDetailTable'));
				$.ajax({   
    				type : "POST",
    				url : "StMoveStoreHouseAction!acceptChangesSelectedParts.action",
    				data : effectRow,
    				dataType : "json",
    				success : function callback(r){
            		     if(r.success)
            		    	    save();
				    }
				 });
		}else
			save();
	 }else
		$.messager.alert('优亿软件提示','出库单明细信息不能为空，记录无法保存！','info',function(){}); 
 }
 
 /**后台保存**/
 function save()
 {
	 $.ajax({
			type : 'POST', 
			url : 'StMoveStoreHouseAction!addMoveStorehouseMain.action',
			data : $('#stMoveStorehouseMainForm').serialize(),
			dataType : 'json',
			success : function(r){
		        if(r.success){
		        	 clearFormAndDatagrid();
		        	 add_cancel();
		        	 $('#tt').tabs('select','移仓单审核');
		        	 $('#stMoveStorehouseUnExamineTable').datagrid('load');
		        	 $('#stMoveOutParstTable').datagrid('loadData',{"rows":[],"total":0});
		        	 $('#stMoveInParstTable').datagrid('loadData',{"rows":[],"total":0});
		        }else
		        	$.messager.alert('优亿软件提示','移仓单添加失败！','info',function(){}); 
            }
     }); 
 }
 
/**3.移仓单汇总删除**/
function deleteStMoveStorehouse()
{
	var msdmId=$('#msdmId').val()
	if(msdmId!=null&&msdmId!=''){
      if($('#examinestate').val()!='已审核'){
    	  $.messager.confirm('提示', '你确定要删除该信息吗?', function(r) {
			  if(r)
				 $.ajax({
						type : 'POST', 
						url : 'StMoveStoreHouseAction!delMoveStorehouseMain.action',
						data : 'msdmId='+msdmId,
						dataType : 'json',
						success : function(r){
					         if(r.msg=="success"){
					        	 clearFormAndDatagrid();
					        	 $('#tt').tabs('select','移仓单审核');
					        	 $('#stMoveStorehouseUnExamineTable').datagrid('load');
					        	 $('#stMoveOutParstTable').datagrid('loadData',{"rows":[],"total":0});
					        	 $('#stMoveInParstTable').datagrid('loadData',{"rows":[],"total":0});
					         }else
					        	 $.messager.alert('优亿软件提示',r.msg,'info',function(){}); 
			             }
			     });
		 });
      }else
   	     $.messager.alert('优亿软件提示','对不起，该采购单已被审核，不能删除！','info',function(){}); 
    }else
		 $.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','info',function(){}); 
	
	var tabName=$('#stMoveStorehouse_tab').tabs('getSelected');
	var tab = tabName.panel('options').title;
	if(tab=='移仓单汇总'){
		 var selected=$('#stMoveStorehouseMainTable').datagrid('getSelected');
		 if(selected.examine=='已审核')
			 $.messager.alert('优亿软件提示','移仓单号为【'+selected.msdmId+'】的单据已被审核，不能删除！','info',function(){}); 
	}
	if(tab=='移仓单明细'){
		var msdmId=$('#msdmId').val();
		var examinestate=$('#examinestate').val();
		if(msdmId!=null&&msdmId!=''&&examinestate!=null&&examinestate!=''&&examinestate!='已审核'){
			$.messager.confirm('优亿软件提示', '你确定要删除移仓单号为【'+msdmId+'】吗?', function(r) {
				  if(r)
					  $.ajax({
							type : 'POST', 
							url : 'StMoveStoreHouseAction!delMoveStorehouseMain.action',
							data : 'msdmId='+msdmId,
							dataType : 'json',
							success : function(r){
						         if(r.msg=="success"){
						        	 clearFormAndDatagrid();
						        	 $('#tt').tabs('select','移仓单审核');
						        	 $('#stMoveStorehouseUnExamineTable').datagrid('load');
						        	 $('#stMoveOutParstTable').datagrid('loadData',{"rows":[],"total":0});
						        	 $('#stMoveInParstTable').datagrid('loadData',{"rows":[],"total":0});
						         }else
						        	 $.messager.alert('优亿软件提示',r.msg,'info',function(){}); 
				             }
				     });
			});
		}else
			$.messager.alert('优亿软件提示','请选择要删除的记录！','info',function(){}); 
	}
    if(tab=='移仓单审核'){
    	var selected=$('#stMoveStorehouseUnExamineTable').datagrid('getSelected');
    	if(selected!=null&&selected!=''){
    	   $.messager.confirm('优亿软件提示', '你确定要删除移仓单号为【'+selected.msdmId+'】吗?', function(r) {
			  if(r)
				  $.ajax({
						type : 'POST', 
						url : 'StMoveStoreHouseAction!delMoveStorehouseMain.action',
						data : 'msdmId='+selected.msdmId,
						dataType : 'json',
						success : function(r){
					         if(r.msg=="success"){
					        	 clearFormAndDatagrid();
					        	 $('#tt').tabs('select','移仓单审核');
					        	 $('#stMoveStorehouseUnExamineTable').datagrid('load');
					        	 $('#stMoveOutParstTable').datagrid('loadData',{"rows":[],"total":0});
					        	 $('#stMoveInParstTable').datagrid('loadData',{"rows":[],"total":0});
					         }else
					        	 $.messager.alert('优亿软件提示',r.msg,'info',function(){}); 
			             }
			     });
			});
    	}else
    		$.messager.alert('优亿软件提示','请选择要删除的记录！','info',function(){}); 
	}
}

/**4.移仓单汇总修改**/
function updateStMoveStorehouse()
{
	var rows=$('#StMoveStorehouseDetailTable').datagrid('getRows');
   	if(rows.length>0){
		endEdit($('#StMoveStorehouseDetailTable'));
		var inserted = $('#StMoveStorehouseDetailTable').datagrid('getChanges', 'inserted');
		var deleted = $('#StMoveStorehouseDetailTable').datagrid('getChanges', 'deleted');
		var updated = $('#StMoveStorehouseDetailTable').datagrid('getChanges', 'updated');
		if(inserted!=''||inserted!=''||updated!=''){
			if(rows.length>0){
				var effectRow = saveAll($('#StMoveStorehouseDetailTable'));
				$.ajax({
    				type : "POST",
    				url : "StMoveStoreHouseAction!acceptChangesSelectedParts.action",
    				data : effectRow,
    				dataType : "json",
    				success : function callback(r){
            		     if(r.success)
            		          update();
				   }
				});
		     }
		} else{
			 update();
		 }
	}else
   		 $.messager.alert('优亿软件提示','移仓单明细为空，不能保存！','warning',function(){});
}

/**后台修改**/
function update()
{
	 $.ajax({    
			type : 'POST', 
			url : 'StMoveStoreHouseAction!updateMoveStorehouseMain.action',
			data : $('#stMoveStorehouseMainForm').serialize(),
			dataType : 'json',
			success : function(r){
		        if(r.success){
		        	 clearFormAndDatagrid();
		        	 update_cancel();
		        	 $('#tt').tabs('select','移仓单审核');
		        	 $('#stMoveStorehouseUnExamineTable').datagrid('load');
		        	 $('#stMoveOutParstTable').datagrid('loadData',{"rows":[],"total":0});
		        	 $('#stMoveInParstTable').datagrid('loadData',{"rows":[],"total":0});
		        }else
		        	$.messager.alert('优亿软件提示',r.msg,'warning',function(){});
            }
     }); 
}

/**审核事件*/
function examineButton(){
	var select=$('#tt').tabs('getSelected');
	var tabName=select.panel('options').title;
	if(tabName=='移仓单汇总'){
		var selectRow1=$('#stMoveStorehouseMainTable').datagrid('getSelected');
		if(selectRow1){
			$.messager.confirm('提示', '你确定要取消审核移仓单号为【'+selectRow1.msdmId+'】吗?', function(r) {
				if(r){
					$.ajax({
						type : "POST",
						url : "StMoveStoreHouseAction!examine.action",
						//data : 'msdmId='+selectRow1.msdmId+'&examine='+selectRow1.examine,
						data :selectRow1,
						dataType : "json",
						success : function callback(r){
		        		     if(r.success){
		        		    	 clearFormAndDatagrid();
		        		    	 $('#stMoveStorehouseUnExamineTable').datagrid('load');
		        		    	 $('#stMoveOutParstTable').datagrid('loadData',{"rows":[],"total":0});
					        	 $('#stMoveInParstTable').datagrid('loadData',{"rows":[],"total":0});
		        		    	 $('#tt').tabs('select','移仓单汇总');
		        		    	 $('#stMoveStorehouseMainTable').datagrid('load');
		        		     }
					   }
					});
				}
			});
		  }else{
			$.messager.alert('优亿软件提示','请选择要审核的移仓单汇总信息！','warning',function(){});
		}
	}else if(tabName=='移仓单审核'){
		var selectRow=$('#stMoveStorehouseUnExamineTable').datagrid('getSelected');
		 if(selectRow){
				$.messager.confirm('提示', '你确定要审核移仓单号为【'+selectRow.msdmId+'】吗?', function(r) {
					if(r){
						$.ajax({
							type : "POST",
							url : "StMoveStoreHouseAction!examine.action",
							data :selectRow,
							dataType : "json",
							success : function callback(r){
			        		     if(r.success){
			        		    	 clearFormAndDatagrid();
			        		    	 $('#stMoveStorehouseUnExamineTable').datagrid('load');
			        		    	 $('#stMoveOutParstTable').datagrid('loadData',{"rows":[],"total":0});
						        	 $('#stMoveInParstTable').datagrid('loadData',{"rows":[],"total":0});
			        		    	 $('#tt').tabs('select','移仓单汇总');
			        		    	 $('#stMoveStorehouseMainTable').datagrid('load');
			        		     }
						   }
						});
					}
				});
			}else{
				$.messager.alert('优亿软件提示','请选择要审核的移仓单汇总信息！','warning',function(){});
			}
	}

}

/**5.移仓单汇总综合查询**/
function searchByCondition(){
	 $('#tt').tabs('select','移仓单汇总');
	 $.ajax({
	 		type : 'POST',
			url : 'StMoveStoreHouseAction!searchByCondition.action',
			data : $('#stMoveStorehouseMainSearchForm').serialize(),
			dataType : 'json',
			success : function(r){
		        	 $('#stMoveStorehouseMainTable').datagrid('loadData',r);
             }
      });
}

/**6.移仓单汇总查询条件清空**/
function clearSearchCondition(){
	 $('#tt').tabs('select','移仓单汇总');
	 $('#stMoveStorehouseMainSearchForm').form('clear');
	 $('#stMoveStorehouseMainTable').datagrid({
		 url : 'StMoveStoreHouseAction!searchByCondition.action?'+$('#stMoveStorehouseMainSearchForm').serialize(),
	 });
}

function unexamine_searchByCondition(){
	 $('#tt').tabs('select','移仓单审核');
	 $.ajax({
	 		type : 'POST',
			url : 'StMoveStoreHouseAction!searchUnExamineByCondition.action',
			data : $('#stMoveStorehouseMainUnExamineSearchForm').serialize(),
			dataType : 'json',
			success : function(r){
		        	 $('#stMoveStorehouseUnExamineTable').datagrid('loadData',r);
             }
      });
}

function unexamine_clearSearchCondition(){
	 $('#tt').tabs('select','移仓单审核');
	 $('#stMoveStorehouseMainUnExamineSearchForm').form('clear');
	 $('#stMoveStorehouseUnExamineTable').datagrid('load');
}

/**
 * 
 * 导出excel
 * 选择要导出的列
 * 参数1   dateGrid控件id属性
 * 参数2   dateGrid控件对应数据库类型
 * 参数3   dateGrid控件上层控件id属性
 * 参数4   执行按钮value文本
 * 参数5   title文本
 * 参数6   功能类型    0导出   1导入   2打印    3隐藏列
 * 参数7   回调函数
 * @return
 */
function _except(){
	showEditDialog("stMoveStorehouseMainTable",null,"stMoveStorehouseMainTableDiv","开始导出","导出配置",0,_callbackExcept);
}

/**
 * 导出excel回调函数
 * 将筛选出的列导出到Excel文件
 * 只支持Microsoft Office,不支持WPS
 * @param parentId
 * @param fieldNames  要导出的列字段
 * @return
 */
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"移仓单汇总"+getSystemTime());
}

function isConditionAddParts()
 {
    var outStoreName=$('#outStoreId').combobox('getText');
    if(outStoreName!=null&&outStoreName!=''){
    	//加载相关移仓调拨单明细信息
        $.ajax({
			type : 'POST',
			url : 'StMoveStoreHouseAction!loadSelectParts.action',
			data : 'storeId='+$('#outStoreId').combobox('getValue'),
			dataType : 'json',
			success : function(r){
		           if(r.total>0){
		        	    addParts();
				   }else{
					    $.messager.show({title : '提示',msg : '对不起，你选择的仓库没有配件',showType : 'slide'});
				   }
            }
         });
    }else{
    	 $.messager.show({title : '提示',msg : '请先选择移出仓名称！',showType : 'slide'});
    }
 }
 
 var sms_d1;
 function addParts()
 {
	 sms_d1 = $('<div/>');
	 sms_d1.dialog({
		title: '配件选择',
	    width: 800,
	    height: 560,
	    cache: false,
	    href: projectPath+'st/StMoveStorehouse/StMoveStorehouse_AddParts.jsp',
	    modal: true,
		buttons : [{
			text : '确定',
			iconCls : 'icon-add',
			handler : function (){
				endEdit($selectedParts);
				var effectRow = saveAll($selectedParts);
				if(effectRow){
					$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'StMoveStoreHouseAction!addStMoveStorehouseDetail.action',
						   data: effectRow,
						   success: function(r){
								 if(r.total>0){
									 $selectedParts.datagrid('acceptChanges');
									 sms_d1.dialog('close');
									 $('#StMoveStorehouseDetailTable').datagrid('loadData',r);
									 calculate();
								 }
						   }
					}).error(function (r){
						alertMsg(r);
					});
				}else
				{
					sms_d1.dialog('destroy');
				}
			}
		}],
		onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
 }
 
 var sms_d2;
 function addMananger()
 {
	 sms_d2 = $('<div/>');
	 sms_d2.dialog({
		title: '经办人信息选择',   
	    width: 600,   
	    height: 403,
	    cache: false,   
	    href: projectPath+'st/StMoveStorehouse/StMoveStorehouse_AddManager.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
 }

 var sms_d3;
 function addMananger2()
 {
	 sms_d3 = $('<div/>');
	 sms_d3.dialog({
		title: '经办人信息选择',   
	    width: 600,   
	    height: 403,
	    cache: false,   
	    href: projectPath+'st/StMoveStorehouse/StMoveStorehouse_AddManager2.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
 }
