
var sellManager;//经办人变量
var aperateState='undefined';//是否可修改状态标识
var detailData=null;//存放明细数据

$(function(){
	
	$('#sellmmDateEnd').val(getSystemTime());
	getStartDate($('#sellmmDateStart'));
	sellManager=$('#sellManagerName').val();
	
	$('#sellmmDate').validatebox({  
        required:true,
        missingMessage:'销售日期必填'
    });  
	
    $('#sellcustomName').validatebox({  
        required:true,
        missingMessage:'客户名称必填' 
    });  
    
    $('#sellmmStfName').validatebox({  
        required:true,
        missingMessage:'领料员必填' 
    });  
    
	$('#priceType').combobox({onSelect:function(record){
		$('#priceType').combobox({disabled:true})
		$('#priceType').combobox('setText',record.text);
		$('#priceType').combobox('setValue',record.id);
	  }
	});
	
	/**销售单汇总**/
	$('#stSellOrderSearchTable').datagrid({
		 url:'${pageContext.request.contextPath }/StSellOrderAction!searchByCondition.action',
		 pagination:true,
	     fit:true,
	     singleSelect:true,
	     sortName:'sellmmId',
		 sortOrder:'desc',
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true, 
		 idField : 'sellmmId',
		 loadMsg : "数据加载中，请稍后……",
		 columns:[[ {title:'销售单号',field:'sellmmId',width:100,sortable:true},
		 			{title:'销售日期',field:'sellmmDate',width:70,hidden:true},
		 			{title:'销售日期',field:'sellmmDateView',width:70,sortable:true},
		 			{title:'车牌照',field:'carLicense',width:70,sortable:true},
		 			{title:'客户名称',field:'sellcustomName',width:70,sortable:true},
		 			{title:'客户编号',field:'sellcustomId',width:70,hidden:true},
		 			{title:'数量',field:'sellmmCnt',width:70,sortable:true},
		 			{title:'销售金额',field:'sellmmSumAmount',width:70,sortable:true},
		 			{title:'销售成本额',field:'sellmmSumCost',width:70,sortable:true},
		 			{title:'经办人',field:'sellManagerName',width:60,sortable:true},
		 			{title:'经办人id',field:'sellManager',width:60,hidden:true},
		 			{title:'领料员',field:'sellmmStfName',width:60,sortable:true},
		 			{title:'领料员id',field:'sellmmStfId',width:60,hidden:true},
		 			{title:'消价系数',field:'priceQuotiety',width:60,sortable:true},
		 			{title:'票据类型',field:'preclrInoiceType',width:60,sortable:true},
		 			{title:'票据类型',field:'preclrInoiceTypeId',hidden:true},
		 			{title:'票据编号',field:'billType',width:70,sortable:true},
		 			{title:'分类编号',field:'psellId',width:60,hidden:true},
		 			{title:'分类',field:'psellName',width:60,sortable:true},
		 			{title:'价格分类Id',field:'priceTypeId',width:70,hidden:true},
		 			{title:'价格分类',field:'priceType',width:60,sortable:true},
		 			{title:'备注',field:'sellmmRemark',width:70,sortable:true},
		 			{title:'状态',field:'sellmmClearingStatus',width:60,sortable:true},
		 			{title:'结算状态',field:'preclrState',width:60,sortable:true}
		]],
		onDblClickRow:function(rowIndex, rowData) {
		     loadMainAndDetailData(rowData);//双击加载明细汇总数据到明细页面
		}
	 });
	
	/**销售单明细**/
	$('#slo_selldOrderItemTable').datagrid({
		 fit:true,
		 pagination : true,
		 fitColumns : true,
		 idField : 'partsId',
		 singleSelect : true,
		 rownumbers : true,
		 loadMsg : "数据加载中，请稍后……",
		 columns:[[
		           	{title:'明细ID',field:'selldIndex',width:80,hidden:true},
		 			{title:'适用车型',field:'fitPtype',width:80},
		 			{title:'配件编码',field:'partsId',width:80},
		 			{title:'配件名称',field:'partsName',width:80},
		 			{title:'单位',field:'punitName',width:80},
		 			{title:'含税成本',field:'taxCostPrice',width:80},
		 			{title:'销售价',field:'selldPrice',width:80,
						editor : {
							type : 'numberbox',
							options : {
								disabled : true,
								precision : 2
							}
						}
		 			},
		 			{title:'销售数量',field:'selldCnt',width:80,
						editor : {
							type : 'numberbox',
							options : {
								required : true,
								min : 1,
								precision:2,
								missingMessage : "数量为必填项!"
							}
						}
		 			},{title:'含税成本额',field:'taxCostPriceAmont',width:80,
						editor : {
							type : 'numberbox',
							options : {
								disabled : true,
								precision : 2
							}
						}
		 			},{title:'销售金额',field:'selldAmount',width:80,
						editor : {
							type : 'numberbox',
							options : {
								disabled : true,
								precision : 2
							}
						}
		 			},{title:'折扣率',field:'selldCutRate',width:80,hidden:true,
						editor : {
							type : 'numberspinner',
							options : {
								required : true,
								editable:false,
								min :50,
								max :100,
								missingMessage : "采购数量为必填项!"
							}
						}
		 			},{title:'折后价',field:'selldCutPrice',width:80,hidden:true,
						editor : {
							type : 'numberbox',
							options : {
								disabled : true,
								precision : 2
							}
						}
		 			},
		 			{title:'库存量',field:'partsNowCount',width:80},
		 			{title:'库位',field:'partsLibrary',width:80},
		 			{title:'仓库名称',field:'storeName',width:80},
		 			{title:'仓库Id',field:'storeId',hidden:true},
		 			{title : '参考数量',field : 'selldCnt1',hidden:true,
						editor : {
							type : 'numberbox',
							options : {
								disabled : true,
								precision : 2
							}
						}
					},{title:'备注',field:'sellRemark', width:80,
					   editor: {
						type : 'text',
						options : {
							enabled : true
						}
					}}
		        ]],
			 onDblClickRow:function(rowIndex, rowData){
	            if(aperateState=='update'||aperateState=='add'){
	            	copyPartsDateAndBindEventItem($('#slo_selldOrderItemTable'), rowIndex, rowData)
	            }
			 },onLoadSuccess:function(data){
				 detailData=data;
			 }
   });
	
   /**消价系数焦点离开触发事件**/
   $("#priceQuotiety").blur(function() {
		var slo_selldOrderItemTable = $("#slo_selldOrderItemTable");
		var rowsDate = slo_selldOrderItemTable.datagrid("getRows");
		for ( var obj in rowsDate) {
			slo_selldOrderItemTable.datagrid('beginEdit', obj);
			var ed = slo_selldOrderItemTable.datagrid('getEditors', obj);
			var priceQuotiety = $('#priceQuotiety').val();// 消价系数
			ed[2].target.numberbox( {// 成本额变动事件
						onChange : function(newValue, oldValue) {
							$('#sellmmSumCost').val(
									accAdd(accSub(
											parseFloat($('#sellmmSumCost').val()),
											parseFloat(oldValue)),
											parseFloat(newValue)));
						}
					});
			ed[3].target.numberbox( {// 销售额变动事件
						onChange : function(newValue, oldValue) {
							$('#sellmmSumAmount').val(
									accAdd(accSub(parseFloat($(
											'#sellmmSumAmount').val()),
											parseFloat(oldValue)),
											parseFloat(newValue)));
						}
					});
			if (priceQuotiety != null && priceQuotiety != ''
					&& !isNaN(priceQuotiety) && parseFloat(priceQuotiety) > 0) {
				var costtax = rowsDate[obj].taxCostPrice;// 成本价
				var taxcostamont = accMul(parseFloat(priceQuotiety),
						parseFloat(costtax));
				ed[0].target.numberbox('setValue', costtax);// 成本额
				ed[2].target.numberbox('setValue', taxcostamont);// 成本额
				ed[3].target.numberbox('setValue', taxcostamont);// 销售额
				var discountPrice = accSub(parseFloat(taxcostamont), accMul(
						parseFloat(taxcostamont), parseFloat(0.1)));
				ed[4].target.numberbox('setValue', discountPrice);// 折后价
			} else {
				ed[0].target.numberbox('setValue', 0.0);// 成本额
				ed[2].target.numberbox('setValue', 0.0);// 成本额
				ed[3].target.numberbox('setValue', 0.0);// 销售额
				ed[4].target.numberbox('setValue', 0.0);// 折后价
			}
			slo_selldOrderItemTable.datagrid('acceptChanges');
		}
	});
});

/**销售单明细行编辑事件**/
function copyPartsDateAndBindEventItem(id, rowIndex, rowData)
{
		id.datagrid('beginEdit', rowIndex);
		var ed = id.datagrid('getEditors', rowIndex);
		if(ed[1]){
			ed[1].target.numberbox('setValue', rowData.selldCnt);
			ed[1].target.select();
			ed[1].target.click(function (){
				ed[1].target.select();
			});
			ed[1].target.keydown(function (e){
				if(e.keyCode == '13'){
					ed[6].target.select();
				}
			});
		}
		if(ed[2])
			  ed[2].target.numberbox('setValue', rowData.taxCostPriceAmont);
		if(ed[3])
			  ed[3].target.numberbox('setValue', rowData.selldAmount);
		if(ed[4])
			  ed[4].target.numberbox('setValue', rowData.selldCutPrice);
		if(ed[5])
			  ed[5].target.numberbox('setValue', rowData.selldCnt);
		if(ed[6]){
			ed[6].target.select();
			ed[6].target.click(function (){
				ed[6].target.select();
			});
			ed[6].target.keydown(function (e){
				if(e.keyCode == '13'){
					if(rowIndex < id.datagrid('getData').total - 1){
						id.datagrid('selectRow',rowIndex + 1);
						copyPartsDateAndBindEventItem(id, rowIndex + 1,id.datagrid('getSelected'));
					}else{
						var ed = id.datagrid('getEditors', 0);
						ed[1].target.select();
					}
				}
			});
		}
	  ed[1].target.select();
	 
      ed[1].target.bind('keyup', function() {
		var num = ed[1].target.val();//代表采购数量
		if(parseFloat(ed[1].target.val())<=0||ed[1].target.val()==null||ed[1].target.val()==''||isNaN(ed[1].target.val())){
			alertMsg('输入销售数量为不符合条件数据，请重新输入！', 'warning');
			ed[1].target.numberbox('setValue', rowData.selldCnt);
			ed[5].target.numberbox('setValue', rowData.selldCnt);
			num=rowData.selldCnt;
		}else{
			if(parseFloat(ed[1].target.val())<=parseFloat(rowData.partsNowCount)){
				var taxCostPrice=rowData.taxCostPrice;//代表销售成本价
				var selldPrice=rowData.selldPrice;
				var taxCostPriceAmont=accMul(parseFloat(num), parseFloat(taxCostPrice));
				ed[2].target.numberbox('setValue',taxCostPriceAmont);
				var selldAmount=accMul(parseFloat(num), parseFloat(selldPrice));
				ed[3].target.numberbox('setValue',selldAmount);
				var selldCutPrice=accSub(parseFloat(selldAmount),accMul(0.1,selldAmount));
				ed[4].target.numberbox('setValue',selldCutPrice);
				ed[5].target.numberbox('setValue',num);
			}else{
				ed[1].target.numberbox('setValue', rowData.selldCnt);
				ed[5].target.numberbox('setValue',rowData.selldCnt);
				alertMsg('销售数量不能大于当前系统库存量，请确认后重新填写销售数量！', 'warning');
			}
		}
      });
      ed[2].target.numberbox({
			onChange : function (newValue, oldValue){
    	         $('#sellmmSumCost').val(accAdd(accSub(parseFloat($('#sellmmSumCost').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
      ed[3].target.numberbox({
			onChange : function (newValue, oldValue){
    	         $('#sellmmSumAmount').val(accAdd(accSub(parseFloat($('#sellmmSumAmount').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
      ed[5].target.numberbox({
			onChange : function (newValue, oldValue){
    	         $('#sellmmCnt').val(accAdd(accSub(parseFloat($('#sellmmCnt').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
}

/**通过销售单号获取销售汇总明细信息**/
function  loadMainAndDetailData(rowData){
	  $('#ssom_tabs').tabs('select','销售单明细');
	  doFromAndDatagridClear();
	  $('#priceType').combobox({disabled:true});
	  $('#stSellOrderMainForm').form('load',rowData);
	  $('#priceType').combobox('setValue',rowData.priceTypeId)
	  $('#preclrInoiceType').combobox('setValue',rowData.preclrInoiceTypeId)
	  $.ajax({ 
			type : "POST",
			url : "${pageContext.request.contextPath }/StSellOrderAction!loadStSellOrderItemBySellmmId.action",
			data : 'sellmmId='+rowData.sellmmId,
			dataType : "json",
			success : function callback(r) {
	        	$('#slo_selldOrderItemTable').datagrid('loadData',r);
          }
      });
}

function loadDefaultPriceType(){
	$.ajax({   
		type : 'POST',
		url : '${pageContext.request.contextPath }/StSellOrderAction!loadDefaultPriceType.action',
		data : '',
		dataType : 'json',
		success : function(r){
			$('#priceType').combobox('setValue',r);
        }
    }); 
}


/**配件信息删除**/
function deleteParts(){
	 var selected = $('#slo_selldOrderItemTable').datagrid('getSelected');
	 if(selected!=null&&selected!=''){
		 $.messager.confirm('提示', '你确定要删除该信息吗?', function(r){
			 if(r){
				 var index=$('#slo_selldOrderItemTable').datagrid('getRowIndex',selected);
				 $('#slo_selldOrderItemTable').datagrid('deleteRow',index);
				 calculate();
			 }
		 })
	 }else
		 $.messager.alert('优亿软件提示', '抱歉，请选择要删除的配件明细信息！');
}

/**页面按钮可用事件**/
function btnEnable() {
	$('#slo_addParts').linkbutton('disable');
	$('#slo_delParts').linkbutton('disable');
	$('#slo_add').linkbutton('enable');
	$('#slo_delete').linkbutton('enable');
	$('#slo_update').linkbutton('enable');
	$('#slo_searchBtn').linkbutton('enable');
	$('#slo_preclrBtn').linkbutton('enable');
	$('#slo_clearBtn').linkbutton('enable');
	$('#slo_printBtn').linkbutton('enable');
	$('#slo_exportBtn').linkbutton('enable');
	$('#sellmmDate').validatebox('remove');
	$('#sellcustomName').validatebox('remove');
	$('#sellmmStfName').validatebox('remove');
	$('#sellmmDate').val(getSystemTime2());
}	

/**页面按钮不可用**/
function btnDisable() {
	$('#slo_addParts').linkbutton('enable');
	$('#slo_delParts').linkbutton('enable');
	$('#slo_add').linkbutton('disable');
	$('#slo_delete').linkbutton('disable');
	$('#slo_update').linkbutton('disable');
	$('#slo_searchBtn').linkbutton('disable');
	$('#slo_preclrBtn').linkbutton('disable');
	$('#slo_clearBtn').linkbutton('disable');
	$('#slo_printBtn').linkbutton('disable');
	$('#slo_exportBtn').linkbutton('disable');
	$('#sellmmDate').validatebox('reduce');
	$('#sellcustomName').validatebox('reduce');
	$('#sellmmStfName').validatebox('reduce');
}

/**清除销售单明细页面汇总明细数据事件**/
function doFromAndDatagridClear(){
	$('#stSellOrderMainForm').form('clear'); 
	$('#slo_selldOrderItemTable').datagrid('loadData', {total:0,rows:[]});
}

/**增加"保存取消按钮**/
function addPersonnel(i){
	if(i==1){//处于添加状态
		$('#ssom_tabs').tabs('select','销售单明细');
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveSellOrder();">保存</a>';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="add_cancel();">取消</a>';
		if($('#button').children('a').length == 0){
			$('#button').append(save).append(cancel);
			$.parser.parse('#button');
		}
		btnDisable();
		doFromAndDatagridClear();
		$('#sellManagerName').val(sellManager);
		$('#sellmmDate').val(getSystemTime2());
		aperateState='add';
		loadDefaultPriceType();
	}else if(i==2){//处于修改状态
		var selected = $('#stSellOrderSearchTable').datagrid('getSelected');
		if (selected != null && selected != '') {
			 var preClrState=selected.preclrState;
			 if(preClrState=='已结算'||preClrState=='已转结算'){
				 alertMsg('对不起，该销售单【'+preClrState+'】,不能修改！', 'warning');
			 }else{
				 $.ajax({
						type : "POST",
						url : "${pageContext.request.contextPath }/StSellOrderAction!doDeleteOrUpdate.action",
						data : 'sellmmId='+selected.sellmmId,
						dataType : "json",
						success : function callback(r) {
					      if(r.success){
							$.messager.confirm('优亿软件提示', '请确认是否要修改销售单号为【'+selected.sellmmId+'】的销售单吗?', function(r){
				                   if(r){  
				                	    $('#ssom_tabs').tabs('select','销售单明细');
					       				btnDisable();
					       				loadMainAndDetailData(selected);
					       				var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updateSellOrder();">保存</a>';
					       				var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="update_cancel();">取消</a>';
					       				if($('#button').children('a').length == 0){
					       					$('#button').append(save).append(cancel);
					       					$.parser.parse('#button');
					       				}
					       				aperateState='update';
				                   }
				        	});
						  }else
							$.messager.alert('优亿软件提示','抱歉，该销售单已存在于销售退料单中，不能进行修改操作！');
				      }
				  });
			  }
		} else 
			$.messager.alert('优亿软件提示', '抱歉，请选择要修改的销售单信息！');
	}
}

/**新增取消事件**/
function add_cancel(){
	 $('#button').empty();
	 $('#priceType').combobox({disabled:false});
	 doFromAndDatagridClear();
	 aperateState='undefined';
	 btnEnable();
}

/**修改取消事件**/
function update_cancel(){
	$('#button').empty();
	aperateState='undefined';
	btnEnable();
}

/**销售单汇总保存新增事件**/
function saveSellOrder(){
	var rows=$('#slo_selldOrderItemTable').datagrid('getRows');
    if(fill_verification(rows)){
    	if(number_verification(rows)){
    		endEdit($('#slo_selldOrderItemTable'));
    		var data=$('#slo_selldOrderItemTable').datagrid('getData');
    	    $.ajax({   
    			type : "POST",
    			url : "${pageContext.request.contextPath }/StSellOrderAction!addStSellOrder.action",
    			data : $('#stSellOrderMainForm').serialize()+'&detailData='+JSON.stringify(data),
    			dataType : "json",
    			success : function callback(r) {
    	        	if(r.success){
    	        		add_cancel();
    	        		clearSearchByCondition();
    	        	}else
    	        		$.messager.show( {title : '提示',msg : '销售单添加失败！',showType : 'slide'});
    	        }
    	    });
    	}else
    		$.messager.alert('优亿软件提示','所选配件中出库数量合计大于库存量，不能保存!');
	}else
		 $.messager.alert('优亿软件提示','销售汇总必填项信息或销售明细信息不完整，记录无法保存！','warning',function(){});
}

/**销售单汇总信息保存修改**/
function updateSellOrder(){
	var rows=$('#slo_selldOrderItemTable').datagrid('getRows');
    if(fill_verification(rows)){
     	if(number_verification(rows)){
     		         endEdit($('#slo_selldOrderItemTable'));
    				 var data=$('#slo_selldOrderItemTable').datagrid('getData');
    				 $.ajax({
    						type : "POST",
    						url : "${pageContext.request.contextPath }/StSellOrderAction!updateStSellOrder.action",
    						data : $('#stSellOrderMainForm').serialize()+'&detailData='+JSON.stringify(data),
    						dataType : "json",
    						success : function callback(r) {
    					       if(r.success){
    					    	   update_cancel();
    					    	   doFromAndDatagridClear();
    					    	   clearSearchByCondition();
    					       }else
    					    	   $.messager.show( {title : '优亿软件提示',msg : '销售单信息修改失败！',showType : 'slide'});
    				        }
    				 });
     	}else
     		$.messager.alert('优亿软件提示','所选配件相同配件合计大于该配件库存量，记录无法保存!');
    }else
		$.messager.alert('优亿软件提示','销售汇总汇总填写不完整或明细不存在，记录无法保存！','warning',function(){});
}

/**销售单删除事件**/
function deleteSellOrder(){
	var selected = $('#stSellOrderSearchTable').datagrid('getSelected');
	if (selected != null && selected != '') {
		 var preClrState=selected.preclrState;
		 if(preClrState=='已结算'||preClrState=='已转结算'){
			 alertMsg('对不起，该销售单【'+preClrState+'】,不能删除！', 'warning');
		 }else{
			 $.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath }/StSellOrderAction!doDeleteOrUpdate.action",
					data : 'sellmmId='+selected.sellmmId,
					dataType : "json",
					success : function callback(r) {
				      if(r.success){
				    	  $.messager.confirm('优亿软件提示', '请确认是否要删除出库单号为【'+selected.sellmmId+'】的出库单吗？', function(r){
					     	   if(r){
					     		   $.ajax({
					 	   				type : "POST",
					 	   				url : "${pageContext.request.contextPath }/StSellOrderAction!deleteStSellOrder.action",
					 	   				data : 'sellmmId='+selected.sellmmId,
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
				      } else
				    	  $.messager.alert('优亿软件提示','抱歉，该销售单已存在于销售退料单中，不能进行删除操作！');
			        }
			 });
		 }
	} else 
		$.messager.alert('优亿软件提示', '对不起，请先选中要删除的记录！');
}

/**必填项验证**/
function fill_verification(rows){
	var isno=$('#stSellOrderMainForm').form('validate');
	var row_isno=true
	if(rows!=null&&rows!=''){
		if(rows.length>0){
	    	for(var i=0;i<rows.length;i++){
	         	var isn=$('#slo_selldOrderItemTable').datagrid('validateRow',i);
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

/**库存量合计验证**/
function  number_verification(rows){
    for ( var j= 0; j < rows.length; j++) {
            var count=0;
			for ( var k = 0; k <  rows.length; k++) {
				if(rows[j].partsId==rows[k].partsId&&rows[j].storeId==rows[k].storeId){
					   $('#slo_selldOrderItemTable').datagrid('beginEdit', k);
	    			   var ed = $('#slo_selldOrderItemTable').datagrid('getEditors', k);
	                   count=parseFloat(ed[1].target.val())+parseFloat(count);
	                   $('#slo_selldOrderItemTable').datagrid('endEdit', k);
				}
			}
			if(count>rows[j].partsNowCount)
				return false;
	}
    return true;
}

/**后台计算采购数量，含税总额，未税总额，税额结果**/  
function calculate() {
	var sellmmCnt=0.0;
	var sellmmSumAmount=0.0;
	var sellmmSumCost=0.0;
	var rows=$('#slo_selldOrderItemTable').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		sellmmCnt=accAdd(parseFloat(sellmmCnt),parseFloat(rows[i].selldCnt));
		sellmmSumAmount=accAdd(parseFloat(sellmmSumAmount),parseFloat(rows[i].selldAmount));
		sellmmSumCost=accAdd(parseFloat(sellmmSumCost),parseFloat(rows[i].taxCostPriceAmont));
	}
	$('#sellmmCnt').val(sellmmCnt);
	$('#sellmmSumAmount').val(sellmmSumAmount);
	$('#sellmmSumCost').val(sellmmSumCost);
}

/**销售转结算*/
function _changePreclr(){
	 var selected=$('#stSellOrderSearchTable').datagrid('getSelected')
	 if(selected!=null&&selected!=''){
		$.messager.confirm('优亿软件提示', '请确认是否要结算销售单号为【'+selected.sellmmId+'】的销售单吗?', function(r){
            if(r){  
   				btnDisable();
   				$.ajax({
   					type : "POST",
   					url : "${pageContext.request.contextPath }/StSellOrderAction!modifyPreclr.action",
   					data : 'sellmmId='+selected.sellmmId,
   					dataType : "json",
   					success : function callback(r) {
   					   if(r.success){
   						   $.messager.alert('优亿软件提示','销售单【'+selected.sellmmId+'】转结算成功！');
   						   clearSearchByCondition();
   						   btnEnable();
   					   }else
   						   $.messager.alert('优亿软件提示','对不起，转结算异常，不能转结算！');
   				    }
   			    });
            }
 	    });
	 }else
		$.messager.alert('优亿软件提示','抱歉，请选中要转结算的销售单记录！');
	 
}



/**销售单汇总综合查询**/
function searchByCondition(){
    $('#ssom_tabs').tabs('select','销售单汇总');
    $.ajax({   
		type : "POST",
		url : "${pageContext.request.contextPath }/StSellOrderAction!searchByCondition.action",
		data : serializeObject($('#stSellOrderSearchForm')),
		dataType : "json",
		success : function callback(r) {
		   $('#stSellOrderSearchTable').datagrid('loadData',r);
	    }
    });
}

/**销售单综合查询清空事件**/
function clearSearchByCondition() {
	$('#ssom_tabs').tabs('select', '销售单汇总');
	$('#stSellOrderSearchForm').form('clear');
	$('#stSellOrderSearchTable').datagrid({
		url:"${pageContext.request.contextPath }/StSellOrderAction!searchByCondition.action?"+$('#stSellOrderSearchForm').serialize(),
	});
}

/**验证消价系数是否合法**/
function identify() {
	var priceQuotiety = $('#priceQuotiety').val();
	if (isNaN(priceQuotiety)) {
		$.messager.confirm('优亿软件提示', '您输入的消价系数为非数字类型，请重新输入！', function(r) {
			if (r) {
				$('#priceQuotiety').select();
			}
		});
	} else if (parseFloat(priceQuotiety) < 0) {
		$.messager.confirm('优亿软件提示', '消价系数必须大于等于零，请重新输入！', function(r) {
			if (r) {
				$('#priceQuotiety').select();
			}
		});
	}
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
	showEditDialog("stSellOrderSearchTable",null,"stSellOrderSearchTableDiv","开始导出","导出配置",0,_callbackExcept);
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
	exportEsuyUIExcelFile(parentId,fieldNames,"销售单汇总"+getSystemTime());
}

var slo_d1;
function slo_CarLicenseSelect()
{
	  slo_d1 = $('<div/>');
  	 slo_d1.dialog({
			title: '车辆牌照选择',
		    width: 600,
		    height: 403,
		    cache: false,
		    href: projectPath+'st/StSellOrder/StSellOrder_CarLicense.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
}

var slo_d2;
function slo_CustomSelect()
{
	  slo_d2 = $('<div/>');
  	 slo_d2.dialog({
			title: '客户名称选择',
		    width: 600,
		    height: 403,
		    cache: false,
		    href: projectPath+'st/StSellOrder/StSellOrder_Custom.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
}

var slo_d3;
function slo_PickingMemberSelect()
{
	  slo_d3 = $('<div/>');
  	 slo_d3.dialog({
			title: '领料员选择',
		    width: 600,
		    height: 403,
		    cache: false,
		    href: projectPath+'st/StSellOrder/StSellOrder_PickingMember.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
}

var slo_d4;
function addParts(){
       var priceType=$('#priceType').combobox('getText');
       if(priceType!=null&&priceType!=''){
      	 slo_d4 = $('<div/>');
	    	 slo_d4.dialog({
					title: '配件选择',   
				    width: 800,
				    height: 560,
				    cache: false,
				    href: projectPath+'st/StSellOrder/StSellOrder_AddParts.jsp',
				    modal: true,
					buttons : [{
						text : '确定',
						iconCls : 'icon-add',
						 handler : function (){
						      endEdit($so_selectedParts);
						      var rows =$so_selectedParts.datagrid('getData');
					          var isSubmit=true;
					          for ( var j= 0; j < rows.length; j++) {
					                var count=0;
									for ( var k = 0; k <  rows.length; k++) {
  									if(isSubmit){
  										if(rows[j].partsId==rows[k].partsId&&rows[j].storeId==rows[k].storeId){
  											$so_selectedParts.datagrid('beginEdit', k);
  					          			    var ed = $so_selectedParts.datagrid('getEditors', k);
  					                        count=parseFloat(rows[j].selldCnt)+parseFloat(count);
  					                        if(count>rows[j].partsNowCount){
  	    										isSubmit=false;
  	    										break;
  	    								    }
  					                        $so_selectedParts.datagrid('endEdit', k);
  										}
      							    }else{
											break;
          							}
									}
							 }
						     if(isSubmit){
                              var selectPartsData=$so_selectedParts.datagrid('getData');
                              $('#slo_selldOrderItemTable').datagrid('loadData',selectPartsData);
                              calculate();
						    	slo_d4.dialog('close');
  						 }else
  							$.messager.alert('优亿软件提示','所选配件中出库数量合计大于库存量，不能保存!');
						}
					}],
					onClose : function (){
				    	$(this).dialog('destroy');
				    }
			});
       }else
      	 $.messager.alert('优亿软件提示','您还没有选择配件价格，不能操作配件！','warning',function(){});
}
