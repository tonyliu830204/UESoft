var manager;
var updateState=undefined;

$(function(){
	 $('#sgmsm_storageDateEnd').val(getSystemTime());
	 getStartDate($('#sgmsm_storageDateStart'));
manager=$('#manager').val();
 
$('#storageDate').validatebox({  
    required:true,
    missingMessage:'入库日期必填'
});  

$('#orderId').validatebox({  
    required:true,
    missingMessage:'采购单号必填'
});  

$('#identifymanName').validatebox({  
    required:true,
    missingMessage:'验证人必填' 
});  

/**入库单汇总信息**/
$('#StGoodsStoregeMainTable').datagrid({
	 url:'StGoodsStorageAction!searchStGoodsStorageByCondition.action',
	 pagination:true,
     fit:true,
     singleSelect:true,
     pageSize : 10,
     sortName:'storageId',
     sortOrder:'desc',
	 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	 fitColumns : true,
	 rownumbers:true,
	 idField : 'storageId',
	 loadMsg : "数据加载中，请稍后……",
				 columns : [ [ {title : '入库单号',field : 'storageId',width : 90,sortable:true
				}, {title : '日期',field : 'storageDate',hidden:true
				}, {title : '入库日期',field : 'storageDateView',width : 65,sortable:true
				}, {title : '供应商',field : 'relcampName',width : 80,sortable:true
				}, {title : '采购单号',field : 'orderId',width : 90,sortable:true
				}, {title : '发票类型',field : 'notesType',width : 100,sortable:true
				}, {title : '发票号',field : 'bill',width : 80,sortable:true
				}, {title : '入库数量',field : 'totalNum',width : 65,sortable:true
				}, {title : '未税金额',field : 'notaxTotalamont',width : 80,sortable:true
				}, {title : '含税金额',field : 'totalAmount',width : 80,sortable:true
				}, {title : '税额',field : 'taxCount',width : 60,sortable:true
				}, {title : '仓别',field : 'storeName',width : 60,sortable:true
				}, {title : '经办人',field : 'manager',width : 60,sortable:true
				}, {title : '采购员',field : 'buyerName',width : 60,sortable:true
				}, {title : '付讫',field : 'paid',width : 45,sortable:true
				}, {title : '供应商编号',field : 'relcampId',hidden:true
				}, {title : '验收人',field : 'identifymanName',width : 80,sortable:true
				}, {title : '验收人ID',field : 'identifyman',hidden:true
				}, {title : '备注',field : 'remark',width : 80,hidden:true
				}, {title : '税率',field : 'taxRate',width : 80,hidden:true
				}, {title : '经办人ID',field : 'managerId',width : 80,hidden:true
				}, {title : '采购员ID',field : 'buyer',width : 80,hidden:true
				}, {title : '仓库ID',field : 'storeId',width : 80,hidden:true
				}, {title : '加价率',field : 'addpriceRate',width : 80,hidden:true
				}, {title : '加价率Id',field : 'addpriceRateId',width : 80,hidden:true
				} ]],
	        onDblClickRow :function (rowIndex, rowData){
				loadDetailData(rowData);
            }       
 });

/**入库单明细**/	 
$('#StGoodsStoregeDetailTable').datagrid({
	 url:'',
     fit:true,
     singleSelect:true,
     rownumbers:true,
     pageSize : 10,
	 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	 fitColumns : true, 
	 idField : 'ptypeName',
	 loadMsg : "数据加载中，请稍后……",
	 columns : [[ {title : '采购明细单号',field : 'stpredIndex',width : 80,hidden:true
			    },{title : '配件型号',field : 'ptypeName',width : 80
				}, {title : '配件编码',field : 'partsId',width : 80
				}, {title : '配件名称',field : 'partsName',width : 80
				}, {title : '单位',field : 'punitName',width : 60
				}, {title : '数量',field : 'partsNum',width : 50
				}, {title : '含税成本',field : 'taxPrice',width : 60
				}, {title : '未税成本',field : 'notaxPrice',width : 60
				}, {title : '含税成本额',field : 'taxTotalamont',width : 80
				}, {title : '未税成本额',field : 'notaxTotalamont',width : 80
				},{title : '出库数量',field : 'stOutCount',width : 80,hidden:true
				},{title : '剩余数量',field : 'surplusCount',width : 80,hidden:true}]]
});
	 
/**入库单调价**/	 
$('#StGoodsStoregeChangePriceDetailTable').datagrid({
	 pagination:true,
     fit:true,
     sortName:'sellmmId',
     sortOrder:'asc',
     pageSize : 10,
	 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	 fitColumns : true, 
	 idField : 'partsId',
	 singleSelect:true,
	 loadMsg : "数据加载中，请稍后……",
	 columns : [ [ {title : '配件调价编号',field : 'changePriceId',width : 80,hidden:true
				},{title : '配件编号一',field : 'partsId',width : 80
				}, {title : '配件编号二',field : 'partsId2',	width : 80
				}, {title : '配件名称',field : 'partsName',width : 80
				}, {title : '仓别Id',field : 'storeId',width : 80,hidden:true
				}, {title : '库位',field : 'partsLibrary',width : 80
				}, {title : '仓别名称',field : 'storeName',width : 80
				}, {title : '配件单位',field : 'punitName',width : 80
				}, {title : '配件型号',field : 'ptypeName',width : 80
				}, {title : '维修价',field : 'partsRepairPrice',width : 80,
			editor:{
                type:'numberbox',
                options:{
							required : true,
							min : 0.1,
							precision : 2,
                      		missingMessage : "维修价为必填项"
                        }
                   }
			}, {title : '销售价',field : 'partsSellPrice',width : 80,
				editor:{
	                type:'numberbox',
	                options:{
								required : true,
								min : 0.1,
								precision : 2,
	                      		missingMessage : "销售价为必填项"
	                        }
	                   }
			}, {title : '网点价',field : 'partsPointPrice',width : 80,
				editor:{
	                type:'numberbox',
	                options:{
								required : true,
								min : 0.1,
								precision : 2,
	                      		missingMessage : "网点价为必填项"
	                        }
	                   }
			}, {title : '特别价',field : 'partsSpecialPrice',width : 80,
				editor:{
	                type:'numberbox',
	                options:{
								required : true,
								min : 0.1,
								precision : 2,
	                      		missingMessage : "特别价为必填项"
	                        }
	                   }
			}, {title : '索赔价',field : 'partsClaimantPrice',width : 80,
				editor:{
                type:'numberbox',
                options:{
							required : true,
							min : 0.1,
							precision : 2,
                      		missingMessage : "索赔价为必填项"
                        }
                   }
			}, {title : '最新入库含税价',field : 'partsLatestTaxprice',width : 80
			}, {title : '最新入库未税价',field : 'partsLatestNotaxprice',width : 80
			}, {title : '库存量',field : 'partsNowCount',width : 80
			}]], onDblClickRow:function(rowIndex, rowData){
			        if(updateState=='update'){
			        	copyPartsDateAndBindEventItem($('#StGoodsStoregeChangePriceDetailTable'), rowIndex, rowData)
			        }
           }
     });
}) 
        
function copyPartsDateAndBindEventItem(id, rowIndex, rowData){
		id.datagrid('beginEdit', rowIndex);
		var ed= id.datagrid('getEditors', rowIndex);
		if(ed[0]){
			ed[0].target.numberbox('setValue',rowData.partsRepairPrice);
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
			ed[1].target.numberbox('setValue',rowData.partsSellPrice);
			ed[1].target.click(function (){
				ed[1].target.select();
			});
			ed[1].target.keydown(function (e){
				if(e.keyCode == '13'){
					ed[2].target.select();
			    }
			});
		}
		if(ed[2]){
			ed[2].target.numberbox('setValue',rowData.partsPointPrice);
			ed[2].target.click(function (){
				ed[2].target.select();
			});
			ed[2].target.keydown(function (e){
				if(e.keyCode == '13'){
					ed[3].target.select();
			    }
			});
		}
		if(ed[3]){
			ed[3].target.numberbox('setValue',rowData.partsSpecialPrice);
			ed[3].target.click(function (){
				ed[3].target.select();
			});
			ed[3].target.keydown(function (e){
				if(e.keyCode == '13'){
					ed[4].target.select();
			    }
	       });
	    }
		if(ed[4]){
			ed[4].target.numberbox('setValue',rowData.partsClaimantPrice);
			ed[4].target.click(function (){
				ed[4].target.select();
			});
			ed[4].target.keydown(function (e){
				if(e.keyCode == '13'){
					if(rowIndex < id.datagrid('getData').total - 1){
						id.datagrid('selectRow',rowIndex + 1);
						copyPartsDateAndBindEventItem(id, rowIndex + 1,id.datagrid('getSelected'));
					}else{
						var ed = id.datagrid('getEditors', 1.0);
						ed[0].target.select();
					}
			    }
	       });
	    }
	    ed[0].target.select();
}


function loadDetailData(rowData) {
	$('#tt').tabs('select', '入库单明细');
	$('#StGoodsStorageDetailForm').form('load',rowData);
	$.ajax({
		type : 'POST',
		url : 'StGoodsStorageAction!searchByIdStStorageItem.action',
		data : 'storageId=' + rowData.storageId,
		dataType : 'json',
		beforeSend:ajaxLoading,
		success : function(r) {
		    if(r.total>0){
		    	ajaxLoadEnd();
		    	$('#StGoodsStoregeDetailTable').datagrid('loadData', r);
		    }
		}
	});
}

/** 清空入库单明细页面信息* */
function doFromAndDatagridClear(){
	clearFrom();
    $('#StGoodsStoregeDetailTable').datagrid('loadData', {total:0,rows:[]});
}

/**设置页面配件新增，删除按钮不可用，汇总新增，删除，修改按钮为可用状态**/       
function btnEnable(){
	 $('#sgs_addBtn').linkbutton('enable');
	 $('#sgs_delBtn').linkbutton('enable');
	 $('#sgs_serachBtn').linkbutton('enable');
	 $('#sgs_clearBtn').linkbutton('enable');
	 $('#sgs_printBtn').linkbutton('enable');
	 $('#sgs_exportBtn').linkbutton('enable');
	 $('#storageDate').validatebox('remove'); 
	 $('#orderId').validatebox('remove'); 
	 $('#identifymanName').validatebox('remove'); 
	 $('#storageDate').val(getSystemTime2());
}

/**设置页面配件新增，删除按钮可用，汇总新增，删除，修改按钮为不可用状态**/      
function btnDisable(){
	 $('#sgs_addBtn').linkbutton('disable');
	 $('#sgs_delBtn').linkbutton('disable');
	 $('#sgs_serachBtn').linkbutton('disable');
	 $('#sgs_clearBtn').linkbutton('disable');
	 $('#sgs_printBtn').linkbutton('disable');
	 $('#sgs_exportBtn').linkbutton('disable');
	 $('#storageDate').validatebox('reduce'); 
	 $('#orderId').validatebox('reduce'); 
	 $('#identifymanName').validatebox('reduce'); 
}

/**清空明细页面汇总表单信息**/		
function clearFrom(){
	$('#StGoodsStorageDetailForm').find('input').val('');
	$('#StGoodsStorageDetailForm').find('select').val('');
	$('#StGoodsStorageDetailForm').find('textarea').html('');
}

/**入库单价格，点击调价按钮事件**/		
function changePrice(){
    if($('#sgscp_storageId').val()!=null&&$('#sgscp_storageId').val()!=''){
    	var save ="<a id='save' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-save' plain='true' onclick='updateChangePrice();'>保存</a>";
		var cancel="<a id='cancel' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-undo' plain='true' onclick='cancelChangePrice();'>取消</a>";
		if ($('#changePriceButton').children('a').length == 0) {
			$('#changePriceButton').append(save).append(cancel);
			$.parser.parse('#changePriceButton');
		}
		updateState='update';
		$('#sgs_changeBtn').linkbutton('disable');
    }else
    	$.messager.alert('优亿软件提示','请选择入库单单号！','warning');
}

/**入库单价格信息   修改**/	
function updateChangePrice(){
    endEdit($('#StGoodsStoregeChangePriceDetailTable'));
	var inserted =  $('#StGoodsStoregeChangePriceDetailTable').datagrid('getChanges', 'inserted');
	var deleted = $('#StGoodsStoregeChangePriceDetailTable').datagrid('getChanges', 'deleted');
	var updated =  $('#StGoodsStoregeChangePriceDetailTable').datagrid('getChanges', 'updated');
	if(inserted!=''||inserted!=''||updated!=''){
		var effectRow = saveAll($('#StGoodsStoregeChangePriceDetailTable'));
		var rows= $('#StGoodsStoregeChangePriceDetailTable').datagrid('getRows');
			if(rows.length>0){
				$.ajax({   
    				type : "POST",
    				url : "StPartsNowCountAction!acceptChangesSelectedParts.action",
    				data : effectRow,
    				dataType : "json",
    				success : function callback(r){
					     if(r.success)
					    	 updateMethod();
				    }
				});
			}
	}else
		updateMethod();
}

/**后台修改**/		
function updateMethod(){
	 $.ajax({
		type : "POST",
		url : "StPartsNowCountAction!updateStRtGoodsDetailPrice.action",
		data : '',
		dataType : "json",
		success : function callback(r) {
	       if(r.success){
	    	   $.ajax({
					type : 'POST',
					url : 'StPartsNowCountAction!loadPartsInfoByStorageId.action',
					data : 'storageId='+$('#sgscp_storageId').combobox('getValue'),
					dataType : 'json',
					success : function(r){
		                	  if(r.total>0) {
		                          $('#StGoodsStoregeChangePriceDetailTable').datagrid('loadData',r);
		                          cancelChangePrice();
		                	  }else
		                		  $.messager.alert('提示',r.msg,'warning');
	                  }
	           });
	       }else
	    	   $.messager.show( {title : '提示',msg : '销售单信息修改失败！',showType : 'slide'});
        }
    });
}

/**取消调价事件**/		
function cancelChangePrice(){
		$('#changePriceButton').empty();
		updateState=undefined;
		$('#sgs_changeBtn').linkbutton('enable');
		var rows=$('#StGoodsStoregeChangePriceDetailTable').datagrid('getRows');
		for(var i=0;i<rows.length;i++){
			$('#StGoodsStoregeChangePriceDetailTable').datagrid('endEdit',i);
		}
}

/**增加"保存取消按钮**/		
function addPersonnel(){
    $('#tt').tabs('select','入库单明细');
    doFromAndDatagridClear();
    btnDisable();
	var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
	var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="add_cancel();">取消</a>';
	if($('#button').children('a').length == 0){
		$('#button').append(save).append(cancel);
		$.parser.parse('#button');
	}
	$('#storageDate').val(getSystemTime2());
	$('#manager').val(manager); 
}

/**入库保存事件**/		
function save(){
	 var isno=$('#StGoodsStorageDetailForm').form('validate');
	 var rows=$('#StGoodsStoregeDetailTable').datagrid('getRows');
     if(isno&&rows.length>0){
    	 var data=$('#StGoodsStoregeDetailTable').datagrid('getData');
		 $.ajax({
			type : 'POST',
			url : 'StGoodsStorageAction!add.action',
			data : $('#StGoodsStorageDetailForm').serialize()+'&detailData='+JSON.stringify(data),
			dataType : 'json',
			beforeSend:ajaxLoading,
			success : function(r){
                   if(r.success){
                	  ajaxLoadEnd();
                	  clearSearchByCondition();
					  add_cancel();
                   }else if(r.msg=='noexist_1')
    				  $.messager.alert('提示','对不起，该配件含税成本价不在梯度调价范围内，系统无法调价,请先设置梯度调价范围！','warning');
    			   else if(r.msg=='noexist_2')
    				  $.messager.alert('提示','对不起，该配件含税成本价不在默认调价范围内，系统无法调价,请先设置该配件的默认调价率！','warning');
    			   else
                	  $.messager.alert({title : '提示',msg : '入库单信息添加失败！',showType : 'slide'}); 
		    }
	     }); 
     }else
		 $.messager.alert('优亿软件提示','表单必填项信息填写不完整，记录无法保存！');
}

function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
}

function ajaxLoadEnd(){
     $(".datagrid-mask").remove();
     $(".datagrid-mask-msg").remove();            
} 

/**入库删除事件**/		
function delStStorage(){
	var selected = $('#StGoodsStoregeMainTable').datagrid('getSelected');
	if (selected != null && selected != '') {
			 $.messager.confirm('优亿软件提示', '请确认是否要删除入库单单号为【'+selected.storageId+'】的入库单吗？', function(r){
				   if(r){
					    $.ajax({ 
							type : 'POST',
							url : 'StGoodsStorageAction!doMonthlyStatemont.action',
							data : 'storageDate='+selected.storageDate,
							dataType : 'json',
							success : function(r){
						    	  if(r.success){
						    		  $.ajax({ 
											type : 'POST',
											url : 'StGoodsStorageAction!doDeleteAndUpdate.action',
											data : 'storageId='+selected.storageId,
											dataType : 'json',
											success : function(r){
										    	  if(r.success){
										    		   $.ajax({ 
															type : 'POST',
															url : 'StGoodsStorageAction!del.action',
															data : 'storageId='+selected.storageId,
															dataType : 'json',
															success : function(r){
														    	  if(r.success){
														    		  doFromAndDatagridClear();
														    		  clearSearchByCondition();
														    	  }else
														    		  $.messager.show({title : '提示',msg : '入库单删除异常！',showType : 'slide'}); 
													        }
											             });
										    	  }else
										    		  $.messager.alert('优亿软件提示', '抱歉，该入库单已在退货单中退货，不能进行删除操作！');
									        }
							             });
						    	  }else
						    		  $.messager.alert('优亿软件提示', '抱歉，该入库单月结，不能进行删除操作！');
					        }
			             });
				    }
			 });
	} else 
		$.messager.alert('优亿软件提示', '对不起，请先选中要删除的入库单信息记录！');
}

/**入库单（新增）取消事件**/
function add_cancel() {
	$('#button').empty();
	doFromAndDatagridClear();
	btnEnable();
}

/**入库单综合查询**/		
function searchByCondition(){
	$('#tt').tabs('select','入库单汇总');
	$.ajax({ 
		type : 'POST',
		url : 'StGoodsStorageAction!searchStGoodsStorageByCondition.action',
		data : $('#StGoodsStorageSearchForm').serialize(),
		dataType : 'json',
		success : function(r){
	            $('#StGoodsStoregeMainTable').datagrid('loadData',r);
        }
  });
}
/**入库单综合查询清空事件**/		
function clearSearchByCondition(){
  $('#StGoodsStorageSearchForm').form('clear');
  $('#tt').tabs('select','入库单汇总');
  $('#StGoodsStoregeMainTable').datagrid({
    	url:'StGoodsStorageAction!searchStGoodsStorageByCondition.action?'+$('#StGoodsStorageSearchForm').serialize()
   });
}

 var sgsm_d1;
 function addRelcamp()
 {
	 sgsm_d1 = $('<div/>');
	 sgsm_d1.dialog({
		title: '供应商选择',   
	    width: 600,   
	    height: 403,
	    cache: false,   
	    href: projectPath+'st/StGoodsStorage/StGoodsStorage_RecampInfo.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
  }
  
  var sgsm_d2;
  function addStPurOrder()
  {
	  sgsm_d2 = $('<div/>');
	  sgsm_d2.dialog({
		title: '采购单信息选择',   
	    width: 600,   
	    height: 403,
	    cache: false,   
	    href: projectPath+'st/StGoodsStorage/StGoodsStorage_StPurOrderInfo.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
  }

  var sgsm_d3;
  function addBasStuff()
  {
	  sgsm_d3 = $('<div/>');
	  sgsm_d3.dialog({
		title: '验收人信息选择',   
	    width: 600,   
	    height: 403,
	    cache: false,   
	    href: projectPath+'st/StGoodsStorage/StGoodsStorage_EmployeeInfo.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
  }
  
  /**
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
  	showEditDialog("StGoodsStoregeMainTable",null,"StGoodsStoregeMainTableDiv","开始导出","导出配置",0,_callbackExcept);
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
  	exportEsuyUIExcelFile(parentId,fieldNames,"入库单汇总"+getSystemTime());
  }
  
  function addprint(){
     var selected=$('#StGoodsStoregeMainTable').datagrid('getSelected')
     if(selected!=''&&selected!=null)
    	 window.open(projectPath+'print/stgoodsstorage.jsp?storageId='+selected.storageId,'demo',"fullscreen=1")
	 else
		 $.messager.alert('优亿软件提示','请选择要打印的入库单记录！','warning');
  }
  
  function searchByCondition_change(){
  	$.ajax({
  		type : 'POST',
  		url : 'StPartsNowCountAction!loadPartsInfoByStorageId.action',
  		data : 'storageId='+$('#sgscp_storageId').val(),
  		dataType : 'json',
  		success : function(r){
          	  if(r.total>0)
                    $('#StGoodsStoregeChangePriceDetailTable').datagrid('loadData',r);
          	  else
          		  $.messager.alert('提示',r.msg,'warning');
            }
     });
  }

  function searchByCondition_clear(){
  	 $('#StGoodsStorageSearchForm').form('clear');
  	 $.ajax({
  			type : 'POST',
  			url : 'StPartsNowCountAction!clearSession.action',
  			data : '',
  			dataType : 'json',
  			success : function(r){
  	               $('#StGoodsStoregeChangePriceDetailTable').datagrid('loadData',r);
  	          }
  	 });
  }
