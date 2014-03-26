
var manager;
var updateState=undefined;
$(function() {
	 $('#stpremTimeEnd').val(getSystemTime());
	 getStartDate($('#stpremTimeStart'));
  manager=$('#manager').val();
  
  $('#stpremTime').validatebox({  
        required:true,
        missingMessage:'预出库日期必填'
  });  
	
  $('#receptionId').validatebox({  
        required:true,
        missingMessage:'工单必填'
  });  
    
  $('#stfName').validatebox({  
        required:true,
        missingMessage:'领料员必填' 
  });  
  
  $('#spom_stpremTimeStart').val(getSystemTime());
	
 $('#StPreOutMain').datagrid({ 
	url : 'StPreOutAction!searchByCondition.action',
	pagination : true,
	fit : true,
	sortName:'stpremId',
	sortOrder:'desc',
	pageSize : 10,
	pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	fitColumns : true,
	singleSelect:true,
	idField : 'stpremId',
	loadMsg : "数据加载中，请稍后……",
	columns : [ [ {title : '预出单号',field : 'stpremId',width : 80,sortable:true
	}, {title : '预出日期',field : 'stpremTimeView',width : 80,sortable:true
	}, {title : '日期',field : 'stpremTime',hidden:true
	}, {title : '客户名称',field : 'customName',width : 80,sortable:true
	}, {title : '工单号',field : 'receptionId',width : 80,sortable:true
	}, {title : '预出金额',field : 'stpremSumAmount',width : 80,sortable:true
	}, {title : '配件成本',field : 'stpremSumCost',width : 80,sortable:true
	}, {title : '经办人',field : 'manager',width : 80,sortable:true
	}, {title : '处理方式ID',field : 'stpremFlgId',hidden:true
	}, {title : '处理方式',field : 'stpremFlg',width : 80,sortable:true
	}, {title : '领用人编号',field : 'stfId',width : 80,hidden:true
	} ,{title : '领用人姓名',field : 'stfName',width : 80,sortable:true
	}, {title : '车间',field : 'repgrpName',width : 80,sortable:true
	}, {title : '总数',field : 'numTotal',width : 80,hidden:true
	}, {title : '车辆牌照',field : 'carLicense',width : 80,hidden:true
	}, {title : '维修分类',field : 'reptName',width : 80,hidden:true
	} ,{title : '车型',field : 'ctypeName',width : 80,hidden:true
	}, {title : 'VIN号',field : 'carVan',width : 80,hidden:true
	}, {title : '备注',field : 'stomRemark',width : 80
	}]],
	onDblClickRow:function(rowIndex, rowData){
	   loadData(rowData);
    }
 });
    
	  
/**出库明细**/
$('#StPreOutDetailTable').datagrid({
	 url:'',
	 pagination:true,
     fit:true,
     singleSelect:true,
     pageSize : 5,
	 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	 fitColumns : true, 
	 idField : 'partsId',
	 loadMsg : "数据加载中，请稍后……",
	 columns:[[{title : '配件编码',field : 'partsId',width : 80
		}, {title : '配件名称',field : 'partsName',width : 80
		}, {title : '单位',field : 'punitName',width : 50
		},{title : '适用车型',field : 'fitPtype',width : 50
		}, {title : '含税成本价',field : 'taxCast',width : 70
		}, {title : '未税成本价',field : 'notaxCast',width : 70
		}, {title : '出库数量',field : 'itemCount',width : 50,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 0.1,
					precision : 2,
					missingMessage : "数量为必填项!"
				}
			}
		}, {title : '销售价',field : 'itemPrice',width : 80,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 0.1,
					precision : 2,
					missingMessage : "销售价为必填项!"
				}
			}
		}, {title : '含税成本额',field : 'taxCastamont',width : 70,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2
				}
			}
		}, {title : '未税成本额',field : 'notaxCastamont',width : 70,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2
				}
			}
		}, {title : '销售额',field : 'amount',width : 80,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2
				}
			}
		}, {title : '库位',field : 'partsLibrary', width : 40 
		}, {title : '索赔id',field : 'claimsId',width : 70,hidden:true
		}, {
			title : '索赔',field : 'claimsType',width : 70,
			editor : {
				type : 'combobox',
				options : {
				url : 'StOutAction!loadClaimsType.action',
				mode : 'remote',
				required:true,
				width: 70,
				valueField:'id',
			    textField:'text'
				}
			}
		}, {title : '库存',field : 'partsNowCount',width : 50
		}, {title : '收费性质',field : 'itemCharge',width : 40,
			editor : {
				type : 'text',
				options : {
			        disabled : true,
					precision : 2
				}
			}
		}, {title : '仓库名称',field : 'storeName',width : 80
		}, {title : '仓别Id',field : 'storeId',width : 50,hidden:true
		}, {title : '计划数量',field : 'itemCountHidden',width : 50,hidden:true
		}, {title : '出库数量',field : 'itemCount1',width : 80,hidden:true,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2
				}
			}
		}, {title : '备注',field : 'outItemRemark',width : 80,
			editor : {
			type : 'text',
			options : {
		        disabled : true,
				precision : 2
			}
		}
		}
        ]],
        onDblClickRow:function(rowIndex, rowData){
		   if(updateState==undefined){
		       $('#StPreOutDetailTable').datagrid('endEdit', rowIndex);
	    		   copyPartsDateAndBindEventItem($('#StPreOutDetailTable'), rowIndex, rowData)
		           }
	           }
      });
  });

function copyPartsDateAndBindEventItem(id, rowIndex, rowData)
{
		id.datagrid('beginEdit', rowIndex);
		var ed = id.datagrid('getEditors', rowIndex);
		if(ed[0]){
			ed[0].target.numberbox('setValue', rowData.itemCount);
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
			 ed[1].target.numberbox('setValue', rowData.itemPrice);
			 ed[1].target.select();
			 ed[1].target.click(function (){
					ed[1].target.select();
			 });
			 ed[1].target.keydown(function (e){
				 if(e.keyCode == '13'){
				    ed[8].target.select();
				 }
			 });
		}
		if(ed[2])
			    ed[2].target.numberbox('setValue', rowData.taxCastamont);//含税成本额
			if(ed[3])
			    ed[3].target.numberbox('setValue', rowData.notaxCastamont);//未税成本额
			if(ed[4])
			    ed[4].target.numberbox('setValue', rowData.amount);//销售额
			if(ed[5]){
			 ed[5].target.combobox({
		  		 width:70,
		  		 onSelect:function(record){
		           ed[6].target.val(record.text);
	             },onLoadSuccess:function(){
	            	 ed[5].target.combobox('setText',rowData.claimsType);//索赔性质
	            	 ed[5].target.combobox('setValue',rowData.claimsId);//索赔性质
	             }
		     });
		}
			if(ed[7])
			    ed[7].target.numberbox('setValue', rowData.itemCount);//数量
		    if(ed[8]){
			 ed[8].target.select();
			 ed[8].target.click(function (){
					ed[8].target.select();
			 });
			 ed[8].target.keydown(function (e){
				if(e.keyCode == '13'){
					if (rowIndex < id.datagrid('getData').total - 1) {
						if(id.datagrid('validateRow', rowIndex)){
							id.datagrid('endEdit', rowIndex);
							id.datagrid('acceptChanges');
							id.datagrid('selectRow', rowIndex + 1);
							copyPartsDateAndBindEventItem(id, rowIndex + 1, id.datagrid('getSelected'));
						}else
							alertMsg('抱歉，该行必填项为空，不能结束当前行编辑！', 'warning');
					} else {
						if(id.datagrid('validateRow', rowIndex)){
							id.datagrid('endEdit', rowIndex);
							id.datagrid('acceptChanges');
							id.datagrid('selectRow',0);
							var rowData=id.datagrid('getSelected');
							copyPartsDateAndBindEventItem(id, 0, rowData);
						}else
							alertMsg('抱歉，该行必填项为空，不能结束当前行编辑！', 'warning');
					}
				}
			 });
		}
		ed[0].target.select();
		ed[0].target.bind('keyup', function() {
				var num = ed[0].target.val();//代表出库数量
				if(trim(num)==null||trim(num)==''||isNaN(trim(num))||parseFloat(trim(num))<0){
					alertMsg('抱歉，您输入的数据小于零或为非法数据，请重新输入！', 'warning');
					ed[0].target.numberbox('setValue', rowData.itemCount);
					num=rowData.itemCount;
				}
				if(parseFloat(trim(num))>parseFloat(rowData.itemCountHidden)){
					alertMsg('抱歉，工单【'+$('#receptionId').val()+'】下的配件【'+rowData.partsName+'】的计划数量为【'+rowData.itemCountHidden+'】,你输入的出库数量大于该数量，该配件无法出库！', 'warning');
					ed[0].target.numberbox('setValue', rowData.itemCountHidden);
					num=rowData.itemCountHidden;
				}
				var itemPrice=ed[1].target.val();//代表销售价格
				if(trim(itemPrice)==null||trim(itemPrice)==''||isNaN(trim(itemPrice))||parseFloat(trim(itemPrice))<0){
					ed[1].target.numberbox('setValue',rowData.itemPrice);
					itemPrice=rowData.itemPrice;
				}
				var taxCastamont=accMul(parseInt(num), parseFloat(rowData.taxCast));
				ed[2].target.numberbox('setValue',taxCastamont);//含税成本额
				var notaxCastamont=accMul(parseInt(num), parseFloat(rowData.notaxCast));
				ed[3].target.numberbox('setValue',notaxCastamont);//未税成本额
				var amount=accMul(parseInt(num), parseFloat(rowData.itemPrice));
				ed[4].target.numberbox('setValue',amount);//销售额
				ed[7].target.numberbox('setValue', num);
	   });
        
       ed[1].target.bind('keyup', function() {
    	   var num = ed[0].target.val();//代表出库数量
			if(trim(num)==null||trim(num)==''||isNaN(trim(num))||parseFloat(trim(num))<0){
				alertMsg('抱歉，您输入的数据小于零或为非法数据，请重新输入！', 'warning');
				ed[0].target.numberbox('setValue', rowData.itemCount);
				num=rowData.itemCount;
			}
			if(parseFloat(trim(num))>parseFloat(rowData.itemCountHidden)){
				alertMsg('抱歉，工单【'+$('#receptionId').val()+'】下的配件【'+rowData.partsName+'】的计划数量为【'+rowData.itemCountHidden+'】,你输入的出库数量大于该数量，该配件无法出库！', 'warning');
				ed[0].target.numberbox('setValue', rowData.itemCountHidden);
				num=rowData.itemCountHidden;
			}
			var itemPrice=ed[1].target.val();//代表销售价格
			if(trim(itemPrice)==null||trim(itemPrice)==''||isNaN(trim(itemPrice))||parseFloat(trim(itemPrice))<0){
				ed[1].target.numberbox('setValue',rowData.itemPrice);
				itemPrice=rowData.itemPrice;
			}
			var taxCastamont=accMul(parseInt(num), parseFloat(rowData.taxCast));
			ed[2].target.numberbox('setValue',taxCastamont);//含税成本额
			var notaxCastamont=accMul(parseInt(num), parseFloat(rowData.notaxCast));
			ed[3].target.numberbox('setValue',notaxCastamont);//未税成本额
			var amount=accMul(parseInt(num), parseFloat(rowData.itemPrice));
			ed[4].target.numberbox('setValue',amount);//销售额
			ed[7].target.numberbox('setValue', num);
      });
      
      ed[2].target.numberbox({
			onChange : function (newValue, oldValue){
    	         $('#taxCastamont').val(accAdd(accSub(parseFloat($('#taxCastamont').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
      ed[3].target.numberbox({
			onChange : function (newValue, oldValue){
    	         $('#notaxCastamont').val(accAdd(accSub(parseFloat($('#notaxCastamont').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
      ed[4].target.numberbox({
			onChange : function (newValue, oldValue){
    	         $('#stomSumAmount').val(accAdd(accSub(parseFloat($('#stomSumAmount').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
      ed[7].target.numberbox({
			onChange : function (newValue, oldValue){
    	         $('#totalNum').val(accAdd(accSub(parseFloat($('#totalNum').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
}

function loadData(rowData){
    $('#tt').tabs('select','预出单明细'); 
    $('#stpremId').val(rowData.stpremId);
    $('#stpremTime').val(rowData.stpremTime);
    $('#customName').val(rowData.customName);
    $('#receptionId').val(rowData.receptionId);
    $('#stpremSumAmount').val(rowData.stpremSumAmount);
    $('#stpremSumCost').val(rowData.stpremSumCost);
    $('#receptionRepPer').val(rowData.receptionRepPer);
    $('#stpremFlg').combobox('setValue',rowData.stpremFlgId);
    $('#stpremFlg').combobox('setText',rowData.stpremFlg);
    $('#storeName').val(rowData.storeName);
    $('#stomRemark').val(rowData.stomRemark);
    $('#stfId').val(rowData.stfId);
    $('#stfName').val(rowData.stfName);
    $('#numTotal').val(rowData.numTotal);
    $('#repgrpName').val(rowData.repgrpName);
    $('#manager').val(rowData.manager);
    $('#carLicense').val(rowData.carLicense);
    $('#reptName').val(rowData.reptName);
    $('#ctypeName').val(rowData.ctypeName);
    $('#carVan').val(rowData.carVan);
	$.ajax({
		type : 'POST',
		url :'StPreOutAction!searchStPreOutDetailByStpremId.action',
		data :'stpremId='+rowData.stpremId,
		dataType : 'json',
		success : function(r){
			if(r.total>0)
				$('#StPreOutDetailTable').datagrid('loadData',r);
	    }
	});
}


function doFromAndDatagridClear() {
	$('#StPreOutMainForm').form('clear');
	$('#StPreOutDetailTable').datagrid('loadData', {total:0,rows:[]});
}
		
function btnEnable() {
	$('#spo_addParts').linkbutton('disable');
	$('#spo_deleteParts').linkbutton('disable');
	$('#smsh_addBtn').linkbutton('enable');
	$('#smsh_deleteBtn').linkbutton('enable');
	$('#smsh_updateBtn').linkbutton('enable');
	$('#smsh_searchBtn').linkbutton('enable');
	$('#smsh_clearBtn').linkbutton('enable');
	$('#smsh_printBtn').linkbutton('enable');
	$('#smsh_exportBtn').linkbutton('enable');
	$('#stpremTime').validatebox('remove');
	$('#receptionId').validatebox('remove');
	$('#stfName').validatebox('remove');
	$('#stpremTime').val(getSystemTime2());
}	
		  	  	
function btnDisable() {
	$('#spo_addParts').linkbutton('enable');
	$('#spo_deleteParts').linkbutton('enable');
	$('#smsh_addBtn').linkbutton('disable');
	$('#smsh_deleteBtn').linkbutton('disable');
	$('#smsh_updateBtn').linkbutton('disable');
	$('#smsh_searchBtn').linkbutton('disable');
	$('#smsh_clearBtn').linkbutton('disable');
	$('#smsh_printBtn').linkbutton('disable');
	$('#smsh_exportBtn').linkbutton('disable');
	$('#stpremTime').validatebox('reduce');
	$('#receptionId').validatebox('reduce');
	$('#stfName').validatebox('reduce');
}

 //单击增加按钮，创建保存和取消按钮事件
 function addPersonnel(i) {
	
	if(i==1){
		$('#tt').tabs('select','预出单明细');
		doFromAndDatagridClear();
		btnDisable();
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addStPreOutMain();">保存';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="add_cancel();">取消';
		if ($('#button').children('a').length == 0) {
			$('#button').append(save).append(cancel);
			$.parser.parse('#button');
		}
		$('#stpremTime').val(getSystemTime2());
		$('#manager').val(manager);
	}else if(i==2){
		 var selected = $('#StPreOutMain').datagrid('getSelected');
		 if(selected!=null&&selected!=''){
			 $.messager.confirm('优亿软件提示', '请确认是否要修改预出库单号为【'+selected.stpremId+'】的预出库单吗？', function(r){
				 if(r){
				    btnDisable();
					updateState='update';
					loadData(selected);
					var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updateStPreOutMain();">保存';
					var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="update_cancel();">取消';
					if ($('#button').children('a').length == 0) {
						$('#button').append(save).append(cancel);
						$.parser.parse('#button');
					}
				 }
			 });
			
		 }else{
			 $.messager.alert('提示','抱歉，请选择要修改的预出库记录！');
		 }
	}
 }
		
 function add_cancel(){
	$('#button').empty();
	btnEnable();
	doFromAndDatagridClear();
 }
 
 function update_cancel(){
		$('#button').empty();
		btnEnable();
 } 
 
 function calculate() {
 	var numTotal=0.0;
 	var stpremSumCost=0.0;
 	var stpremSumAmount=0.0;
 	var rows=$('#StPreOutDetailTable').datagrid('getRows');
 	for(var i=0;i<rows.length;i++){
 		numTotal=accAdd(parseFloat(numTotal),parseFloat(rows[i].itemCount));
 		stpremSumCost=accAdd(parseFloat(stpremSumCost),parseFloat(rows[i].taxCastamont));
 		stpremSumAmount=accAdd(parseFloat(stpremSumAmount),parseFloat(rows[i].amount));
 	}
 	$('#numTotal').val(numTotal);
    $('#stpremSumCost').val(stpremSumCost);
    $('#stpremSumAmount').val(stpremSumAmount);
 }
		 

function addStPreOutMain() {
	var rows=$('#StPreOutDetailTable').datagrid('getRows');
    if(fill_verification(rows)){
		endEdit($('#StPreOutDetailTable'));
		var data=$('#StPreOutDetailTable').datagrid('getData');
	    $.ajax({   
			type : "POST",
			url : "StPreOutAction!addStPreOut.action",
			data : $('#StPreOutMainForm').serialize()+'&detailData='+JSON.stringify(data),
			dataType : "json",
			success : function callback(r) {
	        	if(r.success){
	        		add_cancel();
	        		doFromAndDatagridClear();
	        		clearSearchByCondition();
	        	}else
	        		$.messager.show( {title : '提示',msg : '销售单添加失败！',showType : 'slide'});
	        }
	    });
	 }else
		 $.messager.alert('优亿软件提示','预出库汇总汇总必填项信息或预出库明细信息不完整，记录无法保存！');
}
  
/**删除预出库单*/
function deleteStPreOut() {
	var selected = $('#StPreOutMain').datagrid('getSelected');
	if (selected != null && selected != '') {
    	$.messager.confirm('优亿软件提示', '请确认是否要删除预出库单号为【'+selected.stpremId+'】的预出库单吗？', function(r){
    		 if(r){
	    	    	$.ajax({
    					type : "POST",
    					url : 'StPreOutAction!deleteStPreOut.action',
    					data : "stpremId="+selected.stpremId,
    					dataType : "json",
    					success : function callback(r) {
   	     		    	    if(r.success){
   	     		    	       doFromAndDatagridClear();
						       clearSearchByCondition();
   	     		    	    }
    		            }
                    });
    		  } 
    	  });
    }else
    	$.messager.alert('优亿软件提示','抱歉，请先选中要删除的预出库单记录！');
}

/**修改预出库汇总*/
function updateStPreOutMain(){
	  var rows=$('#StPreOutDetailTable').datagrid('getRows');
	    if(fill_verification(rows)){
			endEdit($('#StPreOutDetailTable'));
			var data=$('#StPreOutDetailTable').datagrid('getData');
		    $.ajax({   
				type : "POST",
				url : "StPreOutAction!updateStPreOut.action",
				data : $('#StPreOutMainForm').serialize()+'&detailData='+JSON.stringify(data),
				dataType : "json",
				success : function callback(r) {
		        	if(r.success){
		        		update_cancel();
		        		doFromAndDatagridClear();
		        		clearSearchByCondition();
		        	}else
		        		$.messager.show( {title : '提示',msg : '销售单添加失败！',showType : 'slide'});
		        }
		    });
		}else
			 $.messager.alert('优亿软件提示','预出库汇总汇总必填项信息或预出库明细信息不完整，记录无法保存！');
}
		  
 function searchByCondition() {
	  $('#tt').tabs('select','预出单汇总'); 
      $.ajax({
			type : "POST",
			url : "StPreOutAction!searchByCondition.action",
			data: $('#stPreOutMainSearchForm').serialize(),
			dataType : "json",
			success : function callback(r) {
			   $('#StPreOutMain').datagrid('loadData',r)
			}
      });
 }

 function clearSearchByCondition(){
	 $('#tt').tabs('select','预出单汇总'); 
	 $('#stPreOutMainSearchForm').form('clear');
	 $('#StPreOutMain').datagrid({
		 url : "StPreOutAction!searchByCondition.action?"+$('#stPreOutMainSearchForm').serialize(),
	 });
 }
 
/**必填项验证**/
function fill_verification(rows){
  	var isno=$('#StPreOutMainForm').form('validate');
  	var row_isno=true
  	if(rows!=null&&rows!=''){
  		if(rows.length>0){
  	    	for(var i=0;i<rows.length;i++){
  	         	var isn=$('#StPreOutDetailTable').datagrid('validateRow',i);
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
		showEditDialog("StPreOutMain",null,"StPreOutMainDiv","开始导出","导出配置",0,_callbackExcept);
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
		exportEsuyUIExcelFile(parentId,fieldNames,"预出单汇总"+getSystemTime());
	}
		    
/****************************************************************************************/

	
	
	var spo_d1;
	 function addFrtReception()
	 {
		 spo_d1 = $('<div/>');
		 spo_d1.dialog({
			title: '工单计划用料配件信息选择',   
		    width: 800,
		    height: 600,
		    cache: false,
		    href: projectPath+'st/StPreOut/StPreOut_AddPlanParts.jsp',
		    modal: true,
			buttons : [{
				text : '确定',
				iconCls : 'icon-add',
				handler : function (){
				        var selects =$('#spo_planParts').datagrid('getSelections');
                       var selected =$('#spo_frtReceptionTable').datagrid('getSelected');
						if(selected!==null&&selected!=''){
	                           if($('#hiddenRecptionId').val()==selected.receptionId){
	                        	     if(identifyPartsCount(selects)){
	                        	    	 var data=$('#spo_planParts').datagrid('getSelections');
	                        	    	 $('#StPreOutDetailTable').datagrid('loadData',data);
	                        	    	 var selected=$('#spo_frtReceptionTable').datagrid('getSelected');
      		           					 $('#receptionId').val(selected.receptionId);
      		           		   	         $('#carLicense').val(selected.carLicense);
      		           		   	         $('#ctypeName').val(selected.ctypeName);
      		           		   	         $('#customName').val(selected.customName);
      		           		   	         $('#repgrpName').val(selected.repgrpName);
      		           		   	         $('#carVan').val(selected.carVan);
      		           		   	         $('#reptName').val(selected.reptName);
      		           		   			 spo_d1.dialog('close');
    		           				     calculate();
	                        	     }
		                           }else
		                        	   $.messager.alert('优亿软件提示','对不起，工单信息与计划用料信息不再同一工单上，请确认双击该工单加载相应计划用料！');
						}else
							$.messager.alert('优亿软件提示','对不起，计划用料不能保存，请选择计划用料所对应的工单信息！');
			    }
			}],
			onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
	 }

	 function identifyPartsCount(selects){
        var isSubmit=true;
        for ( var j= 0; j < selects.length; j++) {
           var count=0;
			for ( var k = 0; k < selects.length; k++) {
				if(selects[j].partsId==selects[k].partsId&&selects[j].storeId==selects[k].storeId){
					var index=$('#spo_planParts').datagrid('getRowIndex', selects[k]);
					$('#spo_planParts').datagrid('beginEdit', index);
          			var ed = $('#spo_planParts').datagrid('getEditors', index);
                   count=accAdd(parseFloat(ed[0].target.val()),parseFloat(count));
                   if(parseFloat(count)>parseFloat(selects[k].partsNowCount)){
                   	isSubmit=false;
                	    $.messager.alert('优亿软件提示','抱歉，出库数量大于库存量，不能出库！','warning',function(){});
                	    break;
                   }
                   $('#spo_planParts').datagrid('endEdit', index);
				}
			}
			if(count>selects[j].partsNowCount){
				isSubmit=false;
				break;
		    }
		 }
		 return isSubmit;
    }
	 
	 var spo_d2;
	 function addBasStuff()
	 {
		 spo_d2 = $('<div/>');
		 spo_d2.dialog({
			title: '领用人信息选择',   
		    width: 600,   
		    height: 403,
		    cache: false,   
		    href: projectPath+'st/StPreOut/StPreOrder_EmployeeInfo.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
	  }

	 var spo_d3;
	 function addParts()
	 {
		 spo_d3 = $('<div/>');
		 spo_d3.dialog({
			title: '配件选择',
		    width: 850,
		    height: 560,
		    cache: false,
		    href: projectPath+'st/StPreOut/StPreOut_AddParts.jsp',
		    modal: true,
			buttons : [{
				text : '确定',
				iconCls : 'icon-add',
				handler : function (){
					endEdit($spo_selectedParts);
					var effectRow = saveAll($spo_selectedParts);
					if(effectRow){
						$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url: 'StPreOutAction!addStOutDetail.action',
							   data: effectRow,
							   success: function(r){
									 if(r.total>0){
										 $spo_selectedParts.datagrid('acceptChanges');
										 spo_d3.dialog('close');
										 $('#StPreOutDetailTable').datagrid('loadData',r);
										 calculate();
									 }
							   }
						}).error(function (r){
						});
					}
				}
			}],
			onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
	 }