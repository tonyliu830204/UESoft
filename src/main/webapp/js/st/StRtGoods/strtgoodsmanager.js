
var manager;

$(function(){
	$('#srgm_strtgmDateEnd').val(getSystemTime());
	getStartDate($('#srgm_strtgmDateStart'));
	manager = $('#manager').val();

	$('#strtgmDate').validatebox( {
		required : true,
		missingMessage : '退货日期必填'
	});

	$('#relcampName').validatebox( {
		required : true,
		missingMessage : '供应商必填'
	});

	$('#storageId').validatebox( {
		required : true,
		missingMessage : '入库单必填'
	});  
	
	  /**退货单汇总信息**/
	 $('#srgm_StRtGoodsMainTable').datagrid({
		 url:'StRtGoodsAction!searchByCondition.action',
		 pagination:true,
	     fit:true,
	     sortOrder:'desc',
	     sortName:'strtgmId',
	     pageSize : 10,
	     singleSelect:true,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true,
		 idField : 'strtgmId',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [ [ {title : '退货单号',field : 'strtgmId',width : 135,sortable:true
				}, {title : '日期',field : 'strtgmDate',hidden:true
				}, {title : '退货日期',field : 'storageDateView',width : 100,sortable:true
				}, {title : '入库单号',field : 'storageId',width : 100,sortable:true
				}, {title : '供应商编号',field : 'relcampId',width : 100,hidden:true
				}, {title : '供应商',field : 'relcampName',width : 100,sortable:true
				}, {title : '入库时间',field : 'storageDate',width : 100,sortable:true
				}, {title : '数量',field : 'numTotal',width : 100,sortable:true
				}, {title : '退货含税成本',field : 'strtgmSumCost',width : 100,sortable:true
				}, {title : '退货未税成本',field : 'strtgmSumNoCost',width : 100,sortable:true
				}, {title : '经办人Id',field : 'managerId',width : 100,hidden:true
				}, {title : '经办人',field : 'manager',width : 100,sortable:true
				}, {title : '采购员',field : 'buyer',width : 100,sortable:true
				}, {title : '仓别',field : 'storeName',width : 100,sortable:true
				}, {title : '仓别Id',field : 'storeId',width : 100,sortable:true,hidden:true
				}, {title : '备注',field : 'strtgmRemark',width : 100,sortable:true
				}]],
		     onDblClickRow:function(rowIndex, rowData){
		      loadDetailData(rowData);
	      }
	 });
	 
	 /**退货单明细**/
	 $('#srg_StRtGoodsDetailTable').datagrid({
		 url:'',
		 pagination:true,
	     fit:true,
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true,
		 singleSelect:true,
		 rownumbers : true,
		 idField : 'itemIndex',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [ [ {title : '配件编号',field : 'partsId',width : 100
			}, {title : '配件名称',field : 'partsName',width : 100
			}, {title : '单位',field : 'punitName',width : 100
			}, {title : '入库数量',field : 'partsCount',width : 100
			}, {title : '退货数量',field : 'strtgdCnt',width : 100,
				editor : {
					type : 'numberbox',
					options : {
						required : true,
						min : 0.00,
						precision : 2,
						missingMessage : "退货数量为必填项!"
					}
				}
			}, {title : '含税成本',field : 'strtgdCostPrice',width : 100
			}, {title : '未税成本',field : 'strtgdNoCostPrice',width : 100
			}, {title : '退货含税成本额',field : 'strtgdCostAmount',width : 100,
				editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						precision : 2
					}
				}
			}, {title : '退货未成本额',field : 'strtgdNoCostAmount',width : 100,
					editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						precision : 2
					}
				}
			}, {title : '备注',field : 'strtgdRemark',width : 100,
				    editor: {
					type : 'text',
					options : {
						enabled : true
					}
				}
			}, {title : '退货数量1',field : 'strtgdCnt1',width : 100,hidden:true,
				editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						precision : 2
					}
				}
			}
		]],
	    onDblClickRow:function(rowIndex, rowData){
		       copyPartsDateAndBindEventItem($('#srg_StRtGoodsDetailTable'), rowIndex, rowData)
	    }
	   });
});

/**退货单明细行编辑时间**/
function copyPartsDateAndBindEventItem(id, rowIndex, rowData) {
	id.datagrid('beginEdit', rowIndex);
	var ed = id.datagrid('getEditors', rowIndex);
	if (ed[0]) {
		ed[0].target.numberbox('setValue',1);
		ed[0].target.select();
		ed[0].target.click(function() {
			ed[0].target.select();
		});
		ed[0].target.keydown(function(e) {
			if (e.keyCode == '13') {
				ed[3].target.select();
			}
		});
	}
	if(ed[1])
		ed[1].target.numberbox('setValue',0.0);
	if(ed[2])
		ed[2].target.numberbox('setValue',0.0);
	if(ed[4])
		ed[4].target.numberbox('setValue',0.0);
	
	if (ed[3]) {
		ed[3].target.text('setValue', rowData.strtgdRemark);
		ed[3].target.select();
		ed[3].target.click(function() {
			ed[3].target.select();
		});
		ed[3].target.keydown(function(e) {
			if (e.keyCode == '13') {
				if (rowIndex < id.datagrid('getData').total - 1) {
					id.datagrid('endEdit', rowIndex);
					id.datagrid('acceptChanges');
					id.datagrid('selectRow', rowIndex + 1);
					copyPartsDateAndBindEventItem(id, rowIndex + 1, id.datagrid('getSelected'));
				} else {
					id.datagrid('endEdit', rowIndex);
					id.datagrid('acceptChanges');
					id.datagrid('selectRow',0);
					var rowData=id.datagrid('getSelected');
					copyPartsDateAndBindEventItem(id, 0, rowData);
				}
			}
		});
	}
	ed[0].target.select();
	ed[0].target.bind('blur', function() {
		    var num = ed[0].target.val();
		    if(num==null||num==''){
		    	ed[0].target.numberbox('setValue', rowData.strtgdCnt);
				num=rowData.strtgdCnt;
			}
			if(parseFloat(ed[0].target.val())>parseFloat(rowData.partsCount)||parseFloat(ed[0].target.val())<=0||isNaN(ed[0].target.val())){
				var oldCount=rowData.strtgdCnt;
			    var newCount=ed[0].target.val();
			    var count=accSub(parseFloat(newCount),parseFloat(oldCount));
			    var partCount=rowData.partsCount;
			    if(parseFloat(partCount)<parseFloat(count)){
			    	alertMsg('退货数量必须介于【'+0+'至'+rowData.partsCount+'】之间！', 'warning');
			    	rowData.strtgdCnt=0.0;
			    	ed[0].target.numberbox('setValue', 0.0);
					num=rowData.strtgdCnt;
			    }
			}
			var strtgdCostPrice = rowData.strtgdCostPrice;//含税成本价
			var strtgdNoCostPrice=rowData.strtgdNoCostPrice;//未税成本价
			var sellPrice=rowData.strtgdPrice;//销售价
			var strtgdCostAmount = accMul(parseFloat(num),parseFloat(strtgdCostPrice));//含税成本金额
			var strtgdNoCostAmount=accMul(parseFloat(num),parseFloat(strtgdNoCostPrice));//未税成本金额
			var strtgdAmount=accMul(parseFloat(num),parseFloat(sellPrice));//销售金额
			ed[1].target.numberbox('setValue', strtgdCostAmount);
			ed[2].target.numberbox('setValue', strtgdNoCostAmount);
			ed[4].target.numberbox('setValue', num);
	});
	
	// 含税成本额 变动
	ed[1].target.numberbox( {
		onChange : function(newValue, oldValue) {
		   $('#strtgmSumCost').val(accAdd(accSub(parseFloat($('#strtgmSumCost').val()),parseFloat(oldValue)), parseFloat(newValue)));
	    }
	});
	// 未税成本额 变动
	ed[2].target.numberbox( {
		onChange : function(newValue, oldValue) {
		   $('#strtgmSumNoCost').val(accAdd(accSub(parseFloat($('#strtgmSumNoCost').val()),parseFloat(oldValue)), parseFloat(newValue)));
	    }
	});
	// 退货数量 变动
	ed[4].target.numberbox( {
		onChange : function(newValue, oldValue) {
		  $('#totalNum').val(accAdd(accSub(parseFloat($('#totalNum').val()),parseFloat(oldValue)), parseFloat(newValue)));
	    }
	});
}    


/** 后台计算采购数量，含税总额，未税总额，税额结果* */
function calculate() {
	var totalNum=0.0;
	var strtgmSumCost=0.0;
	var strtgmSumNoCost=0.0;
	var rows=$('#srg_StRtGoodsDetailTable').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		totalNum=accAdd(parseFloat(totalNum),parseFloat(rows[i].strtgdCnt));
		strtgmSumCost=accAdd(parseFloat(strtgmSumCost),parseFloat(rows[i].strtgdCostAmount));
		strtgmSumNoCost=accAdd(parseFloat(strtgmSumNoCost),parseFloat(rows[i].strtgdNoCostAmount));
	}
	$('#totalNum').val(totalNum);//数量
	$('#strtgmSumCost').val(strtgdAmount);//含税成本
	$('#strtgmSumNoCost').val(strtgmSumNoCost);//未税成本
}

function loadDetailData(rowData){
	$('#tt').tabs('select','退货单明细');    
    doFromAndDatagridClear();
    $('#storageId').val(rowData.storageId);
    $('#strtgmId').val(rowData.strtgmId);
    $('#strtgmDate').val(rowData.strtgmDate);
    $('#relcampName').val(rowData.relcampName);
    $('#relcampId').val(rowData.relcampId);
    $('#totalNum').val(rowData.numTotal);
    $('#strtgmSumCost').val(rowData.strtgmSumCost);
    $('#strtgmSumNoCost').val(rowData.strtgmSumNoCost);
    $('#manager').val(rowData.manager);
    $('#managerId').val(rowData.managerId);
    $('#buyer').val(rowData.buyer);
    $('#srgm_storeId').val(rowData.storeId);
    $('#storeName').val(rowData.storeName);
    $('#strtgmRemark').val(rowData.strtgmRemark);
	  $.ajax({
			type : 'POST',
			url : 'StRtGoodsAction!searchStRtGoodsDetailByStrtgmId.action',
			data : $('#StRtGoodsDetailForm').serialize(),
			dataType : 'json',
			success : function(r){
	                $('#srg_StRtGoodsDetailTable').datagrid('loadData',r);
	            }
     }); 	
}

/**清空退货单明细页面汇总明细数据**/    
function doFromAndDatagridClear() {
	$('#StRtGoodsDetailForm').form('clear');
	$('#srg_StRtGoodsDetailTable').datagrid('loadData', {total:0,rows:[]});
}

/**按钮可用事件**/    
function btnEnable() {
	$('#srgm_addBtn').linkbutton('enable');
	$('#srgm_delBtn').linkbutton('enable');
	$('#srgm_updateBtn').linkbutton('enable');
	$('#srgm_searchBtn').linkbutton('enable');
	$('#srgm_clearBtn').linkbutton('enable');
	$('#srgm_printBtn').linkbutton('enable');
	$('#srgm_exportBtn').linkbutton('enable');
	$('#strtgmDate').validatebox('remove');
	$('#relcampName').validatebox('remove');
	$('#storageId').validatebox('remove');
	$('#strtgmDate').val(getSystemTime2());
}

/**按钮不可用事件**/
function btnDisable() {
	$('#srgm_addBtn').linkbutton('disable');
	$('#srgm_delBtn').linkbutton('disable');
	$('#srgm_updateBtn').linkbutton('disable');
	$('#srgm_searchBtn').linkbutton('disable');
	$('#srgm_clearBtn').linkbutton('disable');
	$('#srgm_printBtn').linkbutton('disable');
	$('#srgm_exportBtn').linkbutton('disable');
	$('#strtgmDate').validatebox('reduce');
	$('#relcampName').validatebox('reduce');
	$('#storageId').validatebox('reduce');
}

/**添加保存取消事件**/   
function addPersonnel(i) {
	if (i == 1) {
		$('#tt').tabs('select', '退货单明细');
		add_cancel();
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addStRtGoods();">保存</a>';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="add_cancel();">取消</a>';
		if ($('#button').children('a').length == 0) {
			$('#button').append(save).append(cancel);
			$.parser.parse('#button');
		}
		btnDisable();
		$('#strtgmDate').val(getSystemTime2());
		$('#manager').val(manager);
	} else {
		$('#tt').tabs('select', '退货单明细');
		var strtgmId = $('#strtgmId').val();
		if (strtgmId != null && strtgmId != '') {
			var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updateStRtGoodsMain();">保存</a>';
			var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="update_cancel();">取消</a>';
			if ($('#button').children('a').length == 0) {
				$('#button').append(save).append(cancel);
				$.parser.parse('#button');
			}
			btnDisable();
		} else {
			$.messager.show( {title : '提示',msg : '请选择要修改的退货单信息！',showType : 'slide'});
		}
	}
}

/**新增取消事件**/  
function add_cancel() {
	$('#button').empty();
	$('#storageIdButton').empty();
	$('#relcampButton').empty();
	doFromAndDatagridClear();
	btnEnable();
}

/**修改取消事件**/    
function update_cancel() {
	$('#button').empty();
	btnEnable();
}

/**退货单保存事件**/   
function addStRtGoods() {
	var rows=$('#srg_StRtGoodsDetailTable').datagrid('getRows');
    if(fill_verification(rows)&&$('#totalNum').val()>0){
    		endEdit($('#srg_StRtGoodsDetailTable'));
    		var data=$('#srg_StRtGoodsDetailTable').datagrid('getData');
    		$.ajax({
    			type : "POST",
    			url : "StRtGoodsAction!addStRtGoodsMain.action",
    			data : $('#StRtGoodsDetailForm').serialize()+'&detailData='+JSON.stringify(data),
    			dataType : "json",
    			success : function callback(r) {
    				if (r.success) {
    					clearSearchByCondition();
    					doFromAndDatagridClear();
    					add_cancel();
    				}
    			}
    		});
	}else
		 $.messager.alert('优亿软件提示','退货单必填项信息或退货数量为零，记录无法保存！','warning',function(){});
}

function deleteStRtGoods(){
	var selected = $('#srgm_StRtGoodsMainTable').datagrid('getSelected');
	if (selected != null && selected != '') {
		 $.messager.confirm('提示', '你确定要删除退货单号为【'+selected.strtgmId+'】的退货单信息吗?', function(r) {
    		 if(r){
    			 $.ajax({                        
     				type : 'POST', 
     				url : 'StRtGoodsAction!deleteStRtGoodsMain.action',
     				data : 'strtgmId='+selected.strtgmId,
     				dataType : 'json',
     				success : function(r){
           		        if(r.success){
           		        	doFromAndDatagridClear();
    						clearSearchByCondition();
           		        }
     	            }
     	        }); 
    		 }
    	 });
	} else 
		$.messager.alert('优亿软件提示', '对不起，请先选中要删除的记录！');
}

/**退货单修改事件**/      
function updateStRtGoodsMain() {
	var rows=$('#srg_StRtGoodsDetailTable').datagrid('getRows');
    if(fill_verification(rows)){
    		endEdit($('#srg_StRtGoodsDetailTable'));
    		var data=$('#srg_StRtGoodsDetailTable').datagrid('getData');
			$.ajax( {
				type : 'POST',
				url : 'StRtGoodsAction!updateStRtGoodsMain.action',
				data : $('#StRtGoodsDetailForm').serialize()+'&detailData='+JSON.stringify(data),
				dataType : 'json',
				success : function(r) {
					if (r.success) {
						doFromAndDatagridClear();
						clearSearchByCondition();
						update_cancel();
					}
				}
			});
    }else
		 $.messager.alert('优亿软件提示','退货单必填项信息或退货明细信息填写不完整，记录无法保存！','warning',function(){});
}

/**必填项验证**/
function fill_verification(rows){
	var isno=$('#StRtGoodsDetailForm').form('validate');
	var row_isno=true
	if(rows!=null&&rows!=''){
		if(rows.length>0){
	    	for(var i=0;i<rows.length;i++){
	         	var isn=$('#srg_StRtGoodsDetailTable').datagrid('validateRow',i);
	         	if(!isn){
	         		row_isno=false;
	         		break;
	             }
	        }
	    }
	}else
		row_isno=false;
	if(isno&&row_isno)
		return true
	else
		return false;
}



/**退货单汇总综合查询**/
function  searchByConditon(){
	$('#tt').tabs('select','退货单汇总');
    $.ajax({
		type : 'post',
		url : 'StRtGoodsAction!searchByCondition.action',
		data : serializeObject($('#srgm_StRtGoodsForm')),
		dataType : 'json',
		success : function(r){
                $('#srgm_StRtGoodsMainTable').datagrid('loadData',r);
        }
    });
}
      
/**退货单汇总综合查询清空事件**/
function clearSearchByCondition() {
	$('#srgm_StRtGoodsForm').form('clear');
	$('#tt').tabs('select','退货单汇总');
    $('#srgm_StRtGoodsMainTable').datagrid({
    	url:'StRtGoodsAction!searchByCondition.action?'+$('#srgm_StRtGoodsForm').serialize()
    });
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
	showEditDialog("srgm_StRtGoodsMainTable",null,"srgm_StRtGoodsMainTableDiv","开始导出","导出配置",0,_callbackExcept);
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
	exportEsuyUIExcelFile(parentId,fieldNames,"退货单汇总"+getSystemTime());
}

var d1;
//打来退货单汇总：供应商信息页面
function add_Srgm_RelCamp()
{
   d1 = $('<div/>');
	d1.dialog({
		title: '供应商信息选择',
	    width: 600,   
	    height: 403,
	    cache: false,   
	    href: projectPath+'st/StRtGoods/StRtGoodsMian_RecampInfo.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
}

var d2;
//打来采退化单明细：供应商信息页面
function add_Srgd_RelCamp()
{
   d2 = $('<div/>');
	d2.dialog({
		title: '供应商信息选择',   
	    width: 600,   
	    height: 403,
	    cache: false,   
	    href: projectPath+'st/StRtGoods/StRtGoodsDetail_RecampInfo.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
}

 //入库单号选择
 function searchStGoodsStorageInfo()
 {
	  var relcampId=$('#relcampId').val();
	  if(relcampId==''||relcampId==null)
		  alertMsg('抱歉，请选择要退货的供应商信息！', 'warning');
	  else {
	      $.ajax({   
				type : "POST",
				url : "StRtGoodsAction!searchStGoodsStorageByRelcampId.action",
				data : "relcampId="+relcampId,
				dataType : "json",
				success : function callback(r) {
	    	    	 if(r.total>0){
	    	    		addStGoodsStorageDetail();
   	    	     }else
   	    	    	 alertMsg('抱歉，该供应商没有相对应的入库单信息！', 'warning');
   	    	}
	       });
      }
  }

var d4;
 function addStGoodsStorageDetail()
 {
    d4 = $('<div/>');
	d4.dialog({
		title: '入库单信息选择',   
	    width: 600,   
	    height: 403,
	    cache: false,   
	    href: projectPath+'st/StRtGoods/StRtGoodsDetial_StStorageItem.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
 }
 
 function addprint(){
     var selected=$('#srgm_StRtGoodsMainTable').datagrid('getSelected')
     if(selected!=''&&selected!=null)
    	 window.open(projectPath+'print/strtgoodsprint.jsp?strtgmId='+selected.strtgmId,'demo',"fullscreen=1")
	 else
		 $.messager.alert('优亿软件提示','请选择要打印的退货单记录！','warning');
  }