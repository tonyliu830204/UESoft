var ssrp_manager;

$(function(){
	 $('#strtpmDateqiEnd').val(getSystemTime());
	 getStartDate($('#strtpmDateStart'));
  ssrp_manager=$('#ssrp_manager').val();
 
  $('#ssrp_strtpmDate').validatebox({  
        required:true,
        missingMessage:'退料日期必填'
  });  
	
  $('#ssrp_sellmmId').validatebox({  
        required:true,
        missingMessage:'销售单号必填'
  });  
    
  $('#ssrp_stfName').validatebox({  
        required:true,
        missingMessage:'退料人员必填' 
  });  
 
/**销售退料单汇总**/ 
$('#StSellRtPartsMainTable').datagrid({
	 url:'StSellRtPartsAction!loadStRtPartsMain.action',
	 pagination:true,
     fit:true,
     pageSize : 10,
     sortName:'strtpmId',
     sortOrder:'desc',
	 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	 fitColumns : true,
	 singleSelect:true,
	 idField : 'strtpmId',
	 loadMsg : "数据加载中，请稍后……",
	 columns : [[{title : '退料单号',field : 'strtpmId',width : 100,sortable:true
			}, {title : '日期',field : 'strtpmDate',hidden:true
			}, {title : '退料日期',field : 'strtpmDateView',width : 100,sortable:true
			}, {title : '退料员',field : 'stfName',width : 100,sortable:true
			}, {title : '客户',field : 'customName',width : 80,sortable:true
			}, {title : '工单号',field : 'receptionId',width : 100,sortable:true
			}, {title : '退料类型',field : 'strtpmType',width : 90,sortable:true
			}, {title : '车牌照',field : 'carLicense',width : 100,sortable:true
			}, {title : '数量',field : 'strtpmSumCnt',width : 90,sortable:true
			}, {title : '金额',field : 'rcptpartsSumPrice',width : 100,sortable:true
			}, {title : '成本额',field : 'partsLatestTaxpriceAmont',width : 80,sortable:true
			}, {title : '经办人',field : 'manager',width : 100,sortable:true
			}, {title : '备注',field : 'strtpmRemark',width : 100,sortable:true
			}]],
	   onDblClickRow:function(rowIndex, rowData){
	     $('#tt').tabs('select','销售退料单明细'); 
	     doFromAndDatagridClear();
	     $('#ssrp_strtpmDate').val(rowData.strtpmDate);
	     $('#ssrp_stfId').val(rowData.stfId);
	     $('#ssrp_stfName').val(rowData.stfName);
	     $('#ssrp_strtpmType').val(rowData.strtpmType);
	     $('#ssrp_strtpmId').val(rowData.strtpmId);
	     $('#ssrp_carLicense').val(rowData.carLicense);
	     $('#ssrp_sellmmId').val(rowData.receptionId);
	     $('#ssrp_customName').val(rowData.customName);
	     $('#ssrp_strtpmRemark').val(rowData.strtpmRemark);
	     $('#ssrp_manager').val(rowData.manager);
	     $('#ssrp_strtpmSumCnt').val(rowData.strtpmSumCnt);
	     $('#ssrp_strtpmAmont').val(rowData.rcptpartsSumPrice);
	     $('#ssrp_strtpmCostAmont').val(rowData.partsLatestTaxpriceAmont);
	     $.ajax({
				type : 'POST',
				url : 'StSellRtPartsAction!searchStRtPartsDetailByStrtpmId.action',
				data : 'strtpmId='+rowData.strtpmId,
				dataType : 'json',
				success : function(r){
	    	        $('#StSellPartsDetailTable').datagrid('loadData',r);
 	            }
	     });
      }			  
 });
	
/**销售单明细**/
 $('#StSellPartsDetailTable').datagrid({
	 url:'',
	 pagination:true,
     fit:true,
     pageSize : 10,
	 pageList : [ 10, 20, 30, 40, ],
	 singleSelect:true,
	 fitColumns : true, 
	 idField : 'rcptpartsIndex',
	 loadMsg : "数据加载中，请稍后……",
	 columns : [ [{title : '流水号',field : 'strtpdIndex',width :100
		    },{title : '计划用料单号',field : 'indexId',hidden:true
			}, {title : '配件编号',field : 'partsId',width :  100
			}, {title : '配件名称',field : 'partsName',width : 100
			}, {title : '销售数量',field : 'partsNum',width :  100
			}, {title : '退料数量',field : 'strtpdCnt',width :  100,
				editor : {
					type : 'numberbox',
					options : {
						required : true,
						precision : 2,
						min :1,
						missingMessage : "采购数量为必填项!"
					}
				}
			}, {title : '单位',field : 'punitName',width :  100
			}, {title : '含税成本价',field : 'selldCostPrice',width :  100
			}, {title : '含税成本额',field : 'selldCostAmount',width :  100,
				editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						precision : 2
					}
				}
			}, {title : '销售单价',field : 'selldPrice',width :  100
			}, {title : '销售金额',field : 'rcptpartsAmount',width :  100,
				editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						precision : 2
					}
				}
			}, {title : '仓库名称',field : 'storeName',width :  100
			}, {title : '仓库Id',field : 'storeId',width :  100,hidden:true
			}, {title : '参考数量',field : 'strtpdCnt1',width :  100,hidden:true,
				editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						precision : 2
					}
				}
			}, {title : '备注',field : 'strtpdRemark',width : 100,
				editor : {
					type : 'text',
					options : {
						enabled : true
					}
				}
			}
	        ]],
	   onDblClickRow:function(rowIndex, rowData){
	       copyPartsDateAndBindEventItem($('#StSellPartsDetailTable'), rowIndex, rowData)
       }
    });
 })
     
 /**销售退料单明细行编辑事件**/
 function copyPartsDateAndBindEventItem(id, rowIndex, rowData)
 {
	id.datagrid('beginEdit', rowIndex);
	var ed= id.datagrid('getEditors', rowIndex);
	if(ed[0]){
		ed[0].target.select();
		ed[0].target.numberbox('setValue',1);
		ed[0].target.click(function (){
			ed[0].target.select();
		});
		ed[0].target.keydown(function (e){
			if(e.keyCode == '13'){
				ed[4].target.select();
		    }
		});
	}
	if(ed[1])
		ed[1].target.numberbox('setValue',0.0);
	if(ed[2])
		ed[2].target.numberbox('setValue',0.0);
	if(ed[3])
		ed[3].target.numberbox('setValue',0);
	if(ed[4])
	{
		ed[4].target.text('setValue','');
		ed[4].target.click(function (){
			ed[4].target.select();
		});
		ed[4].target.keydown(function (e){
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
    ed[0].target.bind('blur', function() {
		var num = ed[0].target.val();//代表采购数量
		if(num==null||num==''){
			ed[0].target.numberbox('setValue',1.00);
			ed[3].target.numberbox('setValue',1.00);
			num=1.00;
		}
		if(parseFloat(ed[0].target.val())>parseFloat(rowData.partsNum)||parseFloat(ed[0].target.val())<=0||isNaN(ed[0].target.val())){
			alertMsg('退料数量必须介于【'+1.00+'至'+rowData.partsNum+'】之间！', 'warning');
			ed[0].target.select();
			ed[0].target.numberbox('setValue',rowData.strtpdCnt);
			ed[3].target.numberbox('setValue',rowData.strtpdCnt);
			num=rowData.strtpdCnt;
		}else{
			var selldCostPrice=rowData.selldCostPrice;
			var selldPrice=rowData.selldPrice;
			var selldCostAmount=accMul(parseFloat(num), parseFloat(selldCostPrice));
			var rcptpartsAmount=accMul(parseFloat(num), parseFloat(selldPrice));
			ed[1].target.numberbox('setValue',selldCostAmount);
			ed[2].target.numberbox('setValue',rcptpartsAmount);
			ed[3].target.numberbox('setValue',num);
		}
      });
      
      //退货金额    变动
      ed[1].target.numberbox({
			onChange : function (newValue, oldValue){
    	         $('#ssrp_strtpmAmont').val(accAdd(accSub(parseFloat($('#ssrp_strtpmAmont').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
      //退货成本额  变动
      ed[2].target.numberbox({
			onChange : function (newValue, oldValue){
    	     $('#ssrp_strtpmCostAmont').val(accAdd(accSub(parseFloat($('#ssrp_strtpmCostAmont').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
      //退货数量   变动
      ed[3].target.numberbox({
			onChange : function (newValue, oldValue){
	    	     $('#ssrp_strtpmSumCnt').val(accAdd(accSub(parseFloat($('#ssrp_strtpmSumCnt').val()),parseFloat(oldValue)),parseFloat(newValue)));
				}
	  });
 }
  
/**销售退料单明细页面清空事件**/
function doFromAndDatagridClear(){
	 $('#StSellRtPartsMainFrom').form('clear');
	 $('#StSellPartsDetailTable').datagrid('loadData', {total:0,rows:[]});
}
    
/**按钮可用事件**/
function btnEnable(){
	$('#slo_add').linkbutton('enable');
	$('#slo_delete').linkbutton('enable');
	$('#slo_searchBtn').linkbutton('enable');
	$('#slo_clearBtn').linkbutton('enable');
	$('#slo_printBtn').linkbutton('enable');
	$('#slo_exportBtn').linkbutton('enable');
	$('#ssrp_strtpmDate').validatebox('remove');
	$('#ssrp_sellmmId').validatebox('remove');
	$('#ssrp_stfName').validatebox('remove');
	$('#ssrp_strtpmDate').val(getSystemTime2());
}	
 	
/**按钮不可用事件**/
function btnDisable(){
	$('#slo_addParts').linkbutton('disable');
	$('#slo_add').linkbutton('disable');
	$('#slo_delete').linkbutton('disable');
	$('#slo_searchBtn').linkbutton('disable');
	$('#slo_clearBtn').linkbutton('disable');
	$('#slo_printBtn').linkbutton('disable');
	$('#slo_exportBtn').linkbutton('disable');
	$('#ssrp_strtpmDate').validatebox('reduce');
	$('#ssrp_sellmmId').validatebox('reduce');
	$('#ssrp_stfName').validatebox('reduce');
}
   
/**增加添加修改按钮**/
 function addPersonnel() {
	var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addStRtPartsMain();">保存';
	var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="add_cancel();">取消';
    if ($('#button').children('a').length == 0) {
				$('#button').append(save).append(cancel);
				$.parser.parse('#button');
	}
    $('#tt').tabs('select','销售退料单明细');  
    btnDisable();
    doFromAndDatagridClear();
    $('#ssrp_strtpmDate').val(getSystemTime2());
	$('#ssrp_manager').val(ssrp_manager);//临时
 }
     
 /**退料单汇总明细新增事件**/
function addStRtPartsMain(){
	endEdit($('#StSellPartsDetailTable'));
	var data=$('#StSellPartsDetailTable').datagrid('getData');
	var isno=$('#StSellRtPartsMainFrom').form('validate');
	if(isno){
		if($('#StSellPartsDetailTable').datagrid('getChanges')!=null&&$('#StSellPartsDetailTable').datagrid('getChanges')!=''){
			$.ajax({
				type : 'POST',
				url : 'StSellRtPartsAction!addStSellRtPartsMain.action', 
				data : $('#StSellRtPartsMainFrom').serialize()+'&detailData='+JSON.stringify(data),
				dataType : 'json',
				success : function(r){
				    if(r.success){
				    	doFromAndDatagridClear();
				    	add_cancel();
				    	$('#tt').tabs('select','销售退料单汇总');
				    	$('#StSellRtPartsMainTable').datagrid('load');
				    }
		        }
		    });
		}else
			alertMsg('退料明细不存在，不能保存！', 'warning');
	}else
		alertMsg('退料汇总必填项信息填写不完整，不能保存！', 'warning');
}
 	 
/**退料单汇总明细删除事件**/
function delStSellRtPartsMain(){
	var selects = $('#StSellRtPartsMainTable').datagrid('getSelected');
	if (selects != null && selects != '') {
		 $.messager.confirm('提示', '你确定要删除该信息吗?', function(r) {
    		 if(r){
    			 $.ajax({                        
     				type : 'POST', 
     				url : 'StSellRtPartsAction!delStSellRtpartsMain.action',
     				data : 'strtpmId='+selects.strtpmId+'&receptionId='+selects.receptionId,
     				dataType : 'json',
     				success : function(r){
           		        if(r.success){
           		        	doFromAndDatagridClear();
           		        	clearSearchByCondition();
           		        }else
           		            $.messager.show({title : '提示',msg : '删除失败！',showType : 'slide'});   
     	            }
     	        }); 
    		 }
    	 });
	} else 
		$.messager.alert('优亿软件提示', '对不起，请先选中要删除的记录！');
}
 
/**新增取消事件**/
function add_cancel() {
	$('#button').empty();
	doFromAndDatagridClear();
	btnEnable();
	$('#tt').tabs('select', '销售退料单明细');
}
     
/**销售退料单综合查询事件**/
function searchByCondition() {
	$('#tt').tabs('select', '销售退料单汇总');
	$.ajax( {
		type : 'POST',
		url : 'StSellRtPartsAction!searchByCondition.action',
		data : serializeObject($('#StSellRtPartsSearchForm')),
		dataType : 'json',
		success : function(r) {
			$('#StSellRtPartsMainTable').datagrid('loadData', r);
		}
	});
}

/**销售退料单综合查询清空事件**/
function clearSearchByCondition() {
	$('#tt').tabs('select', '销售退料单汇总');
	$('#StSellRtPartsSearchForm').form('clear');
	$('#StSellRtPartsMainTable').datagrid('load');
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
	showEditDialog("StSellRtPartsMainTable",null,"StSellRtPartsMainTableDiv","开始导出","导出配置",0,_callbackExcept);
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
	exportEsuyUIExcelFile(parentId,fieldNames,"销售退料单汇总"+getSystemTime());
}

var ssrp_d1;
function ssrp_sellRecepSelect()
{
	ssrp_d1 = $('<div/>');
	ssrp_d1.dialog({
			title: '销售单信息选择',
		    width: 600,
		    height: 403,
		    cache: false,
		    href: projectPath+'st/StSellRtParts/StSellRtParts_Reception.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
	});
}

var ssrp_d2;
function ssrp_stufSelect()
{
	ssrp_d2 = $('<div/>');
	ssrp_d2.dialog({
			title: '退料员信息选择',
		    width: 600,
		    height: 403,
		    cache: false,
		    href: projectPath+'st/StSellRtParts/StSellRtParts_Stuf.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
	 });
}