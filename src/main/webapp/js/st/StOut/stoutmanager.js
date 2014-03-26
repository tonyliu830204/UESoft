var manager;
var updateState=null;//是否可修改状态标识

$(function(){
	
	$('#stomDateEnd').val(getSystemTime());
	getStartDate($('#stomDateStart'));
	manager=$('#manager').val();
	
	$('#stomDate').validatebox({  
        required:true,
        missingMessage:'出库日期必填'
    });  
	
//    $('#receptionId').validatebox({  
//        required:true,
//        missingMessage:'工单必填'
//    });  
//    
//    $('#pickingMemberName').validatebox({  
//        required:true,
//        missingMessage:'领料员必填' 
//    });  
	
//    //为结算工单信息加载
//    $('#NoPreclrTable').datagrid({
//    	 url:'StOutAction!loadFrtReception.action',
//		 pagination:true,
//	     fit:true,
//	     singleSelect:true,
//	     border:false,
//	     pageSize : 10,
//		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
//		 fitColumns : true,
//		 idField : 'receptionId',
//		 loadMsg : "数据加载中，请稍后……",
//		 columns : [ [ {title : '工单号',field : 'receptionId',width : 80
//				}, {title : '车牌',field : 'carLicense',width : 80
//				}, {title : '车类型',field : 'ctypeName',width : 80
//				}, {title : '进厂日期',field : 'resvRealTime',width : 100
//				}, {title : '客户名称',field : 'customName',width : 80
//				}, {title : '维修班组',field : 'repgrpId',width : 100
//				}, {title : '接待员',field : 'stfName',width : 80
//				}, {title : 'vin号',field : 'carVan',width : 100
//				} ,{title : '最后维修里程数',field : 'carLastRepairDistance',width : 100
//				} ,{title : '维修类别',field : 'reptName',width : 80
//				}]],
//		   onDblClickRow:function(rowIndex, rowData){
//		     addPersonnel(1);
//    	     $('#st_outTabs').tabs('select','出库单明细');
//    	     $('#receptionId').val(rowData.receptionId);
//	         $('#carLicense').val(rowData.carLicense);
//	         $('#ctypeName').val(rowData.ctypeName);
//	         $('#resvRealTime').val(rowData.resvRealTime);
//	         $('#customName').val(rowData.customName);
//	         $('#stfName').val(rowData.stfName);
//	         $('#repgrpId').val(rowData.repgrpId);
//	         $('#carVan').val(rowData.carVan);
//	         $('#carLastRepairDistance').val(rowData.carLastRepairDistance);
//	         $('#reptName').val(rowData.reptName); 
//	  	     $.ajax({   
//					type : "POST",
//					url : "StOutAction!loadFrtReceptParts.action",
//					data : 'receptionId='+rowData.receptionId,
//					dataType : "json",
//					success : function callback(r) {
//			             $('#so_stOutItemTable').datagrid('loadData',r);
//			             calculate();
//			        }
//			  });
//             }                 
//	     });
    
	     $('#st_outTabs').tabs({  
	        border:false,  
	        onSelect:function(title){  
	            if(title=='未结算工单'){
//            	   $('#NoPreclrTable').datagrid({
//				      url:'StOutAction!loadFrtReception.action',
//				      loadMsg:'更新数据中......'
//			       });
	            }
	        }  
	     });  
    
    //出库单汇总
    $('#StOutSearchTable').datagrid({
		 url:'StOutAction!searchByCondition.action',
		 pagination:true,
	     fit:true,
	     pageSize : 10,
	     sortOrder:'desc',
	     sortName:'stomId',
	     rownumbers : true,
	     singleSelect:true,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true, 
		 idField : 'stomId',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [ [  {title : '出库单号',field : 'stomId',width : 110,sortable:true
			},{title : '日期',field : 'stomDate',hidden:true
			},{title : '出库日期',field : 'stomDateView',width : 70,sortable:true
			}, {title : '车牌照',field : 'carLicense',width : 60,sortable:true
			}, {title : '工单号',field : 'receptionId',width : 110,sortable:true
			}, {title : '维修类别',field : 'reptName',width : 60,sortable:true
			}, {title : '车型',field : 'ctypeName',width : 60,sortable:true
			}, {title : '里程',field : 'carLastRepairDistance',width : 60,sortable:true
			}, {title : 'van号',field : 'carVan',width : 60,sortable:true
			}, {title : '客户名称',field : 'customName',width : 60,sortable:true
			}, {title : '经办人ID',field : 'manager',width : 60,hidden:true
			}, {title : '经办人',field : 'managerName',width : 60,sortable:true
			}, {title : '领料员ID',field : 'pickingMember',width : 60,hidden:true
			}, {title : '领料员',field : 'pickingMemberName',width : 60,sortable:true
			}, {title : '班组',field : 'repgrpName',width : 60,sortable:true
			}, {title : '进厂日期',field : 'resvRealTime',width : 60,sortable:true
			}, {title : '数量',field : 'totalNum',width : 60,sortable:true
			}, {title : '销售额',field : 'stomSumAmount',width : 60,sortable:true
			}, {title : '销价系数',field : 'xiaojiaxishu',width : 60
			}, {title : '含税成本额',field : 'taxCastamont',width : 60,sortable:true
			}, {title : '未税成本额',field : 'notaxCastamont',width : 60,sortable:true
			}, {title : '接待员',field : 'stfName',width : 60,sortable:true
			}, {title : '备注',field : 'stomRemark',width : 60,sortable:true
			}, {title : '配件调价编号',field : 'changePriceId',width : 60,hidden:true
			} ]],
           onDblClickRow:function(rowIndex, rowData){
    	       loadDetailData(rowData);
            }                 
	       });  
    
		    /**出库明细**/
		    $('#so_stOutItemTable').datagrid({
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
					}, {title : '索赔',field : 'claimsId',width : 50,
						editor : {
							type : 'combobox',
							options : {
							url : 'StOutAction!loadClaimsType.action',
							mode : 'remote',
							required:true,
							width: 70,
							valueField:'id',
						    textField:'text',
						    onSelect : function (record){
					    	var row = $('#so_stOutItemTable').datagrid('getSelected');
				    		var index = $('#so_stOutItemTable').datagrid('getRowIndex', row);
				    		var ed = $('#so_stOutItemTable').datagrid('getEditor', {index:index,field:'claimsType'});
				    		ed.target.val(record.text);
					      }
						 }
						},
						formatter : function (value, row, index){
							return row.claimsType;
						}
					}, {title : '索赔名称',field : 'claimsType',hidden:true,
						editor : {
						    type : 'text'
					    }  
					}, {title : '库存',field : 'partsNowCount',width : 50
					}, {title : '收费性质',field : 'itemChargeId',width : 50,
						editor : {
							type : 'combobox',
							options : {
								url : 'StOutAction!loadBasRepairType.action',
								mode : 'remote',
								required:true,
								width: 70,
								valueField:'id',
							    textField:'text',
							    onSelect : function (record){
							    	var row = $('#so_stOutItemTable').datagrid('getSelected');
						    		var index = $('#so_stOutItemTable').datagrid('getRowIndex', row);
						    		var ed = $('#so_stOutItemTable').datagrid('getEditor', {index:index,field:'itemCharge'});
						    		ed.target.val(record.text);
							    }
						    }
					    },
						formatter : function (value, row, index){
							return row.itemCharge;
						}
					}, {title : '收费性质名称',field : 'itemCharge',hidden:true,
						editor : {
						    type : 'text'
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
		    		   if(updateState==null){
	    			       $('#so_stOutItemTable').datagrid('endEdit', rowIndex);
			    		   copyPartsDateAndBindEventItem($('#so_stOutItemTable'), rowIndex, rowData)
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
					 ed[1].target.click(function (){
							ed[1].target.select();
					 });
					 ed[1].target.keydown(function (e){
						 if(e.keyCode == '13'){
						    ed[10].target.select();
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
	   			if(ed[9])
	   			    ed[9].target.numberbox('setValue', rowData.itemCount);//数量
   			    if(ed[10]){
					 ed[10].target.select();
					 ed[10].target.click(function (){
							ed[10].target.select();
					 });
					 ed[10].target.keydown(function (e){
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
						var taxCastAmont=accMul(parseInt(num), parseFloat(rowData.taxCast));
						ed[2].target.numberbox('setValue',taxCastAmont);//含税成本额
						var notaxCastAmont=accMul(parseInt(num), parseFloat(rowData.notaxCast));
						ed[3].target.numberbox('setValue',notaxCastAmont);//未税成本额
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
					ed[9].target.numberbox('setValue', num);
		      });
		      
		      ed[2].target.numberbox({
					onChange : function (newValue, oldValue){
		    	         $('#taxCastamont').val(accAdd(accSub(parseFloat($('#taxCastamont').val()),parseFloat(oldValue)),parseFloat(newValue)));
					}
			  });
		      ed[3].target.numberbox({
					onChange : function (newValue, oldValue){
		    	         $('#noTaxCastamont').val(accAdd(accSub(parseFloat($('#noTaxCastamont').val()),parseFloat(oldValue)),parseFloat(newValue)));
					}
			  });
		      ed[4].target.numberbox({
					onChange : function (newValue, oldValue){
		    	         $('#stomSumAmount').val(accAdd(accSub(parseFloat($('#stomSumAmount').val()),parseFloat(oldValue)),parseFloat(newValue)));
					}
			  });
		      ed[9].target.numberbox({
					onChange : function (newValue, oldValue){
		    	         $('#totalNum').val(accAdd(accSub(parseFloat($('#totalNum').val()),parseFloat(oldValue)),parseFloat(newValue)));
					}
			  });
        }
		
 function loadDetailData(rowData){
		 doFromAndDatagridClear();
		 $('#saveOrCancelBtn').empty();
  	     $('#st_outTabs').tabs('select','出库单明细');
  	     $('#stOutMainForm').form('load',rowData);
         $.ajax({   
				type : "POST",
				url : "StOutAction!serachStOutItemByStomId.action",
				data : 'stomId='+$('#stomId').val(),
				dataType : "json",
				success : function callback(r) {
			          $('#so_stOutItemTable').datagrid('loadData',r);
               }
	      });
}
		

/**后台计算采购数量，含税总额，未税总额，税额结果**/  
function calculate() {
	var totalNum=0.0;
	var taxCastamont=0.0;
	var noTaxCastamont=0.0;
	var stomSumAmount=0.0;
	var rows=$('#so_stOutItemTable').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		totalNum=accAdd(parseFloat(totalNum),parseFloat(rows[i].itemCount));
		taxCastamont=accAdd(parseFloat(taxCastamont),parseFloat(rows[i].taxCastamont));
		noTaxCastamont=accAdd(parseFloat(noTaxCastamont),parseFloat(rows[i].notaxCastamont));
		stomSumAmount=accAdd(parseFloat(stomSumAmount),parseFloat(rows[i].amount));
	}
	$('#totalNum').val(totalNum);
    $('#taxCastamont').val(taxCastamont);
    $('#noTaxCastamont').val(noTaxCastamont);
    $('#stomSumAmount').val(stomSumAmount);
}
 
function doFromAndDatagridClear(){
  $('#stOutMainForm').form('clear');//添加成功后清空出库汇总表单
  $('#so_stOutItemTable').datagrid('loadData', {total:0,rows:[]});
}
        
function btnEnable() {
	$('#so_add').linkbutton('enable');
	$('#so_delete').linkbutton('enable');
	$('#so_update').linkbutton('enable');
	$('#so_serachBtn').linkbutton('enable');
	$('#so_clearBtn').linkbutton('enable');
	$('#so_printBtn').linkbutton('enable');
	$('#so_exportBtn').linkbutton('enable');
	$('#so_importBtn').linkbutton('enable');
	$('#addParts').linkbutton('disable');
	$('#deleteParts').linkbutton('disable');
//	$('#stomDate').validatebox('remove');
//	$('#receptionId').validatebox('remove');
//	$('#pickingMemberName').validatebox('remove');
	$('#stomDate').val(getSystemTime2());
}
        
function btnDisable() {
	$('#so_add').linkbutton('disable');
	$('#so_delete').linkbutton('disable');
	$('#so_update').linkbutton('disable');
	$('#so_serachBtn').linkbutton('disable');
	$('#so_clearBtn').linkbutton('disable');
	$('#so_printBtn').linkbutton('disable');
	$('#so_exportBtn').linkbutton('disable');
	$('#so_importBtn').linkbutton('disable');
	$('#addParts').linkbutton('enable');
	$('#deleteParts').linkbutton('enable');
//	$('#stomDate').validatebox('reduce');
//	$('#receptionId').validatebox('reduce');
//	$('#pickingMemberName').validatebox('reduce');
}
        
       
        
//添加修改按钮
function addPersonnel(i) 
{ 
   if(i==1){
		$('#st_outTabs').tabs('select','出库单明细');
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveStOutMain();">保存</a>';
		var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="add_cancel();">取消</a>';
		if ($('#saveOrCancelBtn').children('a').length == 0) {
			$('#saveOrCancelBtn').append(save).append(cancel);
			$.parser.parse('#saveOrCancelBtn');
		}
		btnDisable();
		doFromAndDatagridClear();
		$('#manager').val(manager); 
		$('#stomDate').val(getSystemTime2());
	}else if(i==2){
		var selects = $('#StOutSearchTable').datagrid('getSelected');
		if (selects != null && selects != '') {
			 $.ajax({ 
					type : 'POST',
					url : 'StGoodsStorageAction!doMonthlyStatemont.action',
					data : 'storageDate='+selects.stomDate,
					dataType : 'json',
					success : function(r){
				    	  if(r.success){
				    		  $.ajax({   
				  					type : "POST",
				  					url : "StOutAction!doDeleteOrUpdate.action",
				  					data : 'stomId='+selects.stomId+'&receptionId='+selects.receptionId,
				  					dataType : "json",
				  					success : function callback(r) {
				  		    	     if(r.success){
				  		    	    	$.messager.confirm('优亿软件提示', '请确认是否要修改出库单号为【'+selects.stomId+'】的出库单?', function(r){
				 		                   if(r){  
				 		                	   btnDisable();
				 		                	   loadDetailData(selects);
				 			   				   $('#st_outTabs').tabs('select','出库单明细');
				 			   				   var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updateStOutMain();">保存</a>';
				 			   				   var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="update_cancel();">取消</a>';
				 			   				   if ($('#saveOrCancelBtn').children('a').length == 0) {
				 			   						$('#saveOrCancelBtn').append(save).append(cancel);
				 			   						$.parser.parse('#saveOrCancelBtn');
				 			   				   }
				 			   				   updateState='update';
				 		                   }
				 		        	    });
				  		    	     }else
				  		    	    	$.messager.alert('优亿软件提示','抱歉，该出库单已存在于工单退料中，不能进行修改操作！');
				  		            }
				  		      });
				    	  }else
				    		  $.messager.alert('优亿软件提示', '抱歉，该出库单月结，不能进行删除操作！');
		               }
             });  
		} else 
		    $.messager.alert('优亿软件提示', '抱歉，请先选中要修改的记录！');
    }
}
    	
 function add_cancel() {
 	$('#saveOrCancelBtn').empty();
 	doFromAndDatagridClear();
	btnEnable();
 }
 
 function update_cancel(){
 	$('#saveOrCancelBtn').empty();
 	updateState=null;
 	btnEnable();
 }    	
        
/**保存出库单汇总信息**/
function saveStOutMain(){
	var rows=$('#so_stOutItemTable').datagrid('getRows');
    if(fill_verification(rows)){
		endEdit($('#so_stOutItemTable'));
		var data=$('#so_stOutItemTable').datagrid('getData');
	    $.ajax({   
			type : "POST",
			url : "StOutAction!add.action",
			data : $('#stOutMainForm').serialize()+'&detailData='+JSON.stringify(data),
			dataType : "json",
			success : function callback(r) {
	        	if(r.success){
	        		add_cancel();
	        		$('#st_outTabs').tabs('select','出库单汇总');
	        		$('#StOutSearchForm').form('clear');
	        		$('#StOutSearchTable').datagrid('load');
	        	}else
	        		$.messager.show( {title : '提示',msg : '销售单添加失败！',showType : 'slide'});
	        }
	    });
	}else
		 $.messager.alert('优亿软件提示','销售汇总必填项信息或销售明细信息不完整，记录无法保存！');
 }
        
/**必填项验证**/
function fill_verification(rows){
	var isno=$('#stOutMainForm').form('validate');
	var row_isno=true
	if(rows!=null&&rows!=''){
		if(rows.length>0){
	    	for(var i=0;i<rows.length;i++){
	         	var isn=$('#so_stOutItemTable').datagrid('validateRow',i);
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
		
//出库单汇总信息      删除
function del(){
	var selected = $('#StOutSearchTable').datagrid('getSelected');
	if (selected != null && selected != '') {
    	$.messager.confirm('优亿软件提示', '请确认是否要删除出库单号为【'+selected.stomId+'】的出库单吗？', function(r){
    		 if(r){
    			    $.ajax({ 
						type : 'POST',
						url : 'StGoodsStorageAction!doMonthlyStatemont.action',
						data : 'storageDate='+selected.stomDate,
						dataType : 'json',
						success : function(r){
					    	  if(r.success){
					    		  $.ajax({
					    				type : "POST",
					    				url : "StOutAction!doDeleteOrUpdate.action",
					    				data : 'stomId='+selected.stomId+'&receptionId='+selected.receptionId,
					    				dataType : "json",
					    				success : function callback(r) {
					    		    	    if(r.success){
					    		    	    	 $.ajax({
		    		 	            					type : "POST",
		    		 	            					url : "StOutAction!del.action",
		    		 	            					data : 'stomId='+selected.stomId,
		    		 	            					dataType : "json",
		    		 	            					success : function callback(r) {
		    		     	       	     		    	    if(r.success){
		    		     	       	     		    	         $('#st_outTabs').tabs('select','出库单汇总');
		    		     	       	     		    	    	 add_cancel();
		    		     	       	     		    	    	 $('#StOutSearchForm').form('clear');
		    		     	       	     		        		 $('#StOutSearchTable').datagrid('load');
		    		     	       	     		    	    }
		    		 	            		            }
		    		      		                   });
					    		    	    }else
					    		    	    	$.messager.alert('优亿软件提示','抱歉，该出库单已存在于工单退料中，不能进行删除操作！');
					    		        } 
				            	   });
					    	  }else
					    		  $.messager.alert('优亿软件提示', '抱歉，该出库单月结，不能进行删除操作！');
    		               }
	                 });
               }
		 });
	} else 
		$.messager.alert('优亿软件提示', '对不起，请先选中要删除的记录！');
}
        
/**修改采购单信息**/
function updateStOutMain()
{
	var rows=$('#so_stOutItemTable').datagrid('getRows');
    if(fill_verification(rows)){
		endEdit($('#so_stOutItemTable'));
		var data=$('#so_stOutItemTable').datagrid('getData');
	    $.ajax({   
			type : "POST",
			url : "StOutAction!update.action",
			data : $('#stOutMainForm').serialize()+'&detailData='+JSON.stringify(data),
			dataType : "json",
			success : function callback(r) {
	        	if(r.success){
	        		add_cancel();
	        		$('#st_outTabs').tabs('select','出库单汇总');
	        		$('#StOutSearchForm').form('clear');
	        		$('#StOutSearchTable').datagrid('load');
	        	}else
	        		$.messager.show( {title : '提示',msg : '销售单添加失败！',showType : 'slide'});
	        }
	    });
	}else
		 $.messager.alert('优亿软件提示','销售汇总必填项信息或销售明细信息不完整，记录无法保存！');
 }
        
function searchByCondition(){
	 add_cancel();
	 var tabName=$('#st_outTabs').tabs('getSelected');
	 var tab = tabName.panel('options').title;
     if(tab=='出库单汇总'){
		 $.ajax({
				type : "POST",
				url : "StOutAction!searchByCondition.action",
				data : serializeObject($('#StOutSearchForm')),
				dataType : "json",
				success : function callback(r) {
			      $('#StOutSearchTable').datagrid('loadData',r);
			      if(r.total==0)
			    	  $.messager.alert('优亿软件提示','对不起，没有满足此查询条件的记录，请确认查询条件是否正确！','warning',function(){});
			      if(r.msg!=undefined)
			    	  $.messager.alert('优亿软件提示',r.msg,'warning',function(){});
		        }
		 });
     }else if(tab=='未结算工单'){
    	  $.ajax({   
				type : "POST",
				url : "StOutAction!loadFrtReception.action",
				data : serializeObject($('#NoPreclrForm')),
				dataType : "json",
				success : function callback(r) {
	    	       $('#NoPreclrTable').datagrid('loadData',r);
	            }
	      });
     }else
    	 $.messager.alert('优亿软件提示','请选择查询页面及查询条件！','warning',function(){});
}
    	
function clearSearchByCondition(){
	 var tabName=$('#st_outTabs').tabs('getSelected');
	 var tab = tabName.panel('options').title;
     if(tab=='出库单汇总'){
    	 $('#StOutSearchForm').form('clear');
		 $('#StOutSearchTable').datagrid({
			 url:"StOutAction!searchByCondition.action?"+$('#StOutSearchForm').serialize()
		 });
     }else if(tab=='未结算工单'){
    	 $('#NoPreclrForm').form('clear');
		 $('#NoPreclrTable').datagrid('load');
     }else
    	 $.messager.alert('优亿软件提示','请选择要清空的查询页面！','warning',function(){});
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
	showEditDialog("StOutSearchTable",null,"StOutSearchTableDiv","开始导出","导出配置",0,_callbackExcept);
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
	exportEsuyUIExcelFile(parentId,fieldNames,"出库单汇总"+getSystemTime());
}

//打来领料员信息：采购员信息页面
var so_d1;
function addPickingMember(){
	 so_d1 = $('<div/>');
	 so_d1.dialog({
		title: '领料员信息选择',
	    width: 600,   
	    height: 403,
	    cache: false,
	    href: projectPath+'st/StOut/StOut_EmployeeInfo.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
}

//打来工单：工单信息页面
var so_d2;
function addFrtReception(){
	 so_d2 = $('<div/>');
	 so_d2.dialog({
		title: '优忆软件',   
	    width: 800,
	    height: 600,
	    cache: false,
	    href: projectPath+'st/StOut/StOut_AddPlanParts.jsp',
	    modal: true,
		buttons : [{
			text : '确定',
			iconCls : 'icon-add',
			handler : function (){
			       var selects =$('#so_planParts').datagrid('getSelections');
                   var selected =$('#so_frtReceptionTable').datagrid('getSelected');
					if(selected!==null&&selected!=''){
                          if($('#hiddenRecptionId').val()==selected.receptionId){
                       	     if(identifyPartsCount(selects)){
                       	    	 var data=$('#so_planParts').datagrid('getSelections');
                       	    	 $('#so_stOutItemTable').datagrid('loadData',data);
                       	    	 var selected=$('#so_frtReceptionTable').datagrid('getSelected');
  		           					 $('#receptionId').val(selected.receptionId);
  		           		   	         $('#carLicense').val(selected.carLicense);
  		           		   	         $('#ctypeName').val(selected.ctypeName);
  		           		   	         $('#resvRealTime').val(selected.resvRealTime);
  		           		   	         $('#customName').val(selected.customName);
  		           		   	         $('#stfName').val(selected.stfName);
  		           		   	         $('#repgrpId').val(selected.pregrpName);
  		           		   	         $('#carVan').val(selected.carVan);
  		           		   	         $('#carLastRepairDistance').val(selected.carLastRepairDistance);
  		           		   	         $('#reptName').val(selected.reptName);
		           				     so_d2.dialog('close');
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
				var index=$('#so_planParts').datagrid('getRowIndex', selects[k]);
				$('#so_planParts').datagrid('beginEdit', index);
      			var ed = $('#so_planParts').datagrid('getEditors', index);
               count=accAdd(parseFloat(ed[0].target.val()),parseFloat(count));
               if(parseFloat(count)>parseFloat(selects[k].partsNowCount)){
               	isSubmit=false;
            	    $.messager.alert('优亿软件提示','抱歉，出库数量大于库存量，不能出库！','warning',function(){});
            	    break;
               }
               $('#so_planParts').datagrid('endEdit', index);
			}
		}
		if(count>selects[j].partsNowCount){
			isSubmit=false;
			break;
	    }
	 }
	 return isSubmit;
}

//打来配件信息：配件信息页面
var so_d4;
function addParts()
{
	 so_d4 = $('<div/>');
	 so_d4.dialog({
		title: '配件选择',   
	    width: 850,
	    height: 560,
	    cache: false,
	    href: projectPath+'st/StOut/StOut_AddParts.jsp',
	    modal: true,
		buttons : [{
			text : '确定',
			iconCls : 'icon-add',
			handler : function (){
			    var rows=$('#so_selectedParts').datagrid('getRows');
               var isSubmit=true;
               for ( var j= 0; j < rows.length; j++) {
                   var count=0;
					for ( var k = 0; k <  rows.length; k++) {
						if(rows[j].partsId==rows[k].partsId&&rows[j].storeId==rows[k].storeId){
							$('#so_selectedParts').datagrid('beginEdit', k);
               			var ed = $('#so_selectedParts').datagrid('getEditors', k);
                           count=parseFloat(ed[0].target.val())+parseFloat(count);
							$('#so_selectedParts').datagrid('endEdit', k);
						}
					}
					if(count>rows[j].partsNowCount){
						isSubmit=false;
						break;
				    }
				}
               if(isSubmit){
                   var isnosubmit=true;
                   for(var i=0;i<rows.length;i++){
                   	var isno=$('#so_selectedParts').datagrid('validateRow',i);
                   	if(!isno){
                   		isnosubmit=false;
                   		break;
                       }
                   }
                   if(isnosubmit){
                   	endEdit($('#so_selectedParts'));
						var effectRow = saveAll($('#so_selectedParts'));
						so_d4.dialog('close');
						if(effectRow){
							$.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: 'StOutAction!addStOutItem.action',
								   data: effectRow,
								   success: function(r){
										 if(r.total>0){
											 $('#so_selectedParts').datagrid('acceptChanges');
											 $('#so_stOutItemTable').datagrid('loadData',r);
											 calculate(); 
										 }
								   }
							}).error(function (r){
								alertMsg(r);
							});
						}
                   }else
                   	 $.messager.alert('优亿软件提示','索赔为必选项，请选择索赔分类!');
               }else{
               	$.messager.alert('优亿软件提示','所选配件中出库数量合计大于库存量，不能保存!');
               	for ( var k = 0; k <  rows.length; k++) {
						$('#so_selectedParts').datagrid('beginEdit', k);
               	}
               }
			}
		}],
		onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
}

//打来领料员信息：采购员信息页面
function addprint(){
	 var selected=$('#StOutSearchTable').datagrid('getSelected')
    if(selected!=''&&selected!=null){
   	    window.open(projectPath+'st/StOut/StOutPrintReport.jsp?stomId='+selected.stomId,'demo',"fullscreen=1")
	}else
		 $.messager.alert('优亿软件提示','请选择要打印的入库单记录！','warning');
}

