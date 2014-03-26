  
    var managerName;
    var detailData=null;//存放明细数据
    $(function() {
	  $('#orderDateEnd').val(getSystemTime());
	  getStartDate($('#orderDateStart'));
	  searchByCondition();
	  managerName=$('#managerName').val();
	  
	  $('#orderDate').validatebox({  
	         required:true,
	         missingMessage:'采购日期必填'
	  });  
	  $('#relcampName2').validatebox({  
		  required:true,
		  missingMessage:'供应商必填'
	  });  
	  $('#buyerName').validatebox({  
		  required:true,
		  missingMessage:'采购员必填' 
	  });  
	  
	  $('#deliveryDate').validatebox({  
		  required:true,
		  invalidMessage:'到货日期必填' 
	  });  
	  
      $('#taxRate').numberbox({  
          min:0,  
          required:true,  
          issingMessage:'税率必填' ,
          precision:2  
      });  
	  
	  $('#notesType').combobox({onSelect:function(record){
			$('#notesType').combobox({
				disabled:true
			});
			$('#taxRate').val('17');
			$('#notesType').combobox('setText',record.text);
			$('#notesType').combobox('setValue',record.id);
		   }
	 });
	
	  $('#classification').combobox({
		onSelect : function() {
			$('#classificationValue').val(
					$('#classification').combobox('getValue'));
		}
	 });
	  
	 $('#notesType').removeAttr('disabled'); 
	  
	 $('#stPurOrderTable').datagrid({
		 url:'StPurOrderAction!searchByCondition.action',
		 fit:true,
		 pagination : true,
		 sortOrder:'desc',
	     sortName:'orderId',
		 singleSelect : true,
		 fitColumns : true,
		 pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 rownumbers : true,
		 loadMsg : "数据加载中，请稍后……",
		 columns : [ [ {title : '采购单号',field : 'orderId',width : 120,sortable:true
				}, {title : '采购日期',field : 'orderDate',width : 75,hidden:true
				}, {title : '采购日期',field : 'orderDates',width : 75,sortable:true
				}, {title : '供应商',field : 'relcampName',width : 80,sortable:true
				}, {title : '供应商ID',field : 'relcampId',width : 60,hidden:true
				}, {title : '到货日期',field : 'deliveryDate',width : 85,hidden:true
				}, {title : '到货日期',field : 'deliveryDates',width : 70,sortable:true
				}, {title : '票据类型',field : 'notesType',width : 80,sortable:true
				}, {title : '票据类型ID',field : 'notesTypeId',hidden:true,width : 80
				}, {title : '采购数量',field : 'numTotal',width : 55,sortable:true
				}, {title : '未税金额',field : 'notaxTotalamont',width : 60,sortable:true
				}, {title : '含税金额',field : 'totalAmount',width : 60,sortable:true
				}, {title : '税率',field : 'taxRate',width : 40,sortable:true
				}, {title : '税额',field : 'taxCount',width : 40,sortable:true
				}, {title : '车辆牌照',field : 'CarBrand',width : 60,sortable:true,hidden:true
				}, {title : '工单号',field : 'RecptionId',width : 60,sortable:true,hidden:true
				}, {title : '经办人',field : 'manager',hidden:true,width : 50
				}, {title : '采购员',field : 'buyerName',width : 50,sortable:true
				}, {title : '经办人',field : 'managerName',width : 50,sortable:true
				}, {title : '采购员',field : 'buyer',hidden:true,width : 50
				}, {title : '采购备注',field : 'orderRemark',width : 55,sortable:true
				}, {title : '订单类型',field : 'classification',width : 60,sortable:true
				}, {title : '订单类型Id',field : 'classificationId',hidden:true,width : 60
				}, {title : '在途状态',field : 'transitToState',width : 60,sortable:true
				}, {title : '在途状态',field : 'transitToStateId',hidden:true
				}, {title : '审核Id',field : 'examine',width : 50,hidden:true
				}, {title : '审核',field : 'examineName',width : 50,sortable:true
				}, {title : '付讫Id',field : 'paid',width : 30,hidden:true
				}, {title : '付讫',field : 'paidName',width : 30,sortable:true
				}, {title : '入库单号',field : 'storageId',width : 60,sortable:true
				} ]],
				onDblClickRow:function(rowIndex, rowData){
					loadDetailData(rowData);
	            }
	  });
			 
	  $('#StOrderItemTable').datagrid({
		 fit:true,
		 fitColumns : true,
		 idField : 'id',
		 singleSelect : true,
		 rownumbers : true,
		 loadMsg : "数据加载中，请稍后……",
		 columns : [ [ {title : '流水号',field : 'stpredIndex',width : 50,hidden:true
			    }, {title : '配件编码',field : 'partsId',width : 100
				}, {title : '配件名称',field : 'partsName',width : 100
				}, {title : '配件品牌',field : 'pbrdName',width : 100
				},{title : '配件型号',field : 'ptypeName',width : 100
				}, {title : '单位',field : 'punitName',width : 100
				}, {title : '采购数量',field : 'partsNum',width : 100, 
					editor : {
						type : 'numberbox',
						options : {
							required : true,
							min : 0.1,
							precision : 2,
							validType:'length[1,12]',
							missingMessage : "数量为必填项!"
						}
					}
				}, {title : '在途数量',field : 'transitToCount',width : 100,
					formatter: function(value,row,index){
						return row.partsNum;
					},
					editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							min : 0.1,
							precision : 2
						}
					}
				}, {title : '含税价',field : 'taxPrice',width : 100,
					editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							precision : 2
						}
					}
				}, {
					title : '未税价',field : 'notaxPrice',width : 100,
					editor : {
						type : 'numberbox',
						options : {
							required : true,
							missingMessage : "未税价为必填项!",
							precision : 2
						}
					}
				}, {title : '含税金额',field : 'taxTotalamont',width : 100,
					editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							precision : 2
						}
					}
				}, {title : '未税金额',field : 'notaxTotalamont',width : 100,
					editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							precision : 2
						}
					}
				}, {title : '税额',field : 'taxCount',width : 100,
					editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							precision : 2
						}
					}
				}, {title : '库位',field : 'partsLibrary',width : 100
				}, {title : '采购备注',field :'itemRemark',width : 50,
					editor : {
					type : 'text',
					options : {
						validType:'length[1,100]',
					}
		        }
			}
		        ]],
		        onDblClickRow:function(rowIndex, rowData){
		            copyPartsDateAndBindEventItem($('#StOrderItemTable'), rowIndex, rowData)
		        },onLoadSuccess:function(data){
					 detailData=data;
				}
	    });
	 
	    $("#taxRate").blur(function (){
	    	var StOrderItemTable = $("#StOrderItemTable");
	    	var rowsDate = StOrderItemTable.datagrid("getRows");
	    	for(var obj in rowsDate){
	    		StOrderItemTable.datagrid('beginEdit', obj);
	    		var ed = StOrderItemTable.datagrid('getEditors', obj);
	    		var taxRate=$('#taxRate').val();
	    		var taxRate1=taxRate/100;
	    		var num = ed[0].target.val();//代表采购数量
	    		var notax=ed[3].target.numberbox('getValue');
	    		
		        ed[1].target.numberbox({
					onChange : function (newValue, oldValue){
		    	         $('#numTotal').val(accAdd(accSub(parseInt($('#numTotal').val()),parseInt(oldValue)),parseInt(newValue)));
					}
			    });
		        ed[4].target.numberbox({
					onChange : function (newValue, oldValue){
		    	         $('#totalAmount').val(accAdd(accSub(parseFloat($('#totalAmount').val()),parseFloat(oldValue)),parseFloat(newValue)));
					}
			    });
		        ed[5].target.numberbox({
					onChange : function (newValue, oldValue){
		    	         $('#notaxTotalamont').val((accAdd(accSub(parseFloat($('#notaxTotalamont').val()),parseFloat(oldValue)),parseFloat(newValue))).toFixed(2));
					}
			    });
		        ed[6].target.numberbox({
					onChange : function (newValue, oldValue){
		    	         $('#taxCount').val(accAdd(accSub(parseFloat($('#taxCount').val()),parseFloat(oldValue)),parseFloat(newValue)));
					}
			    });
				var notaxtotalamont=accMul(parseFloat(num), parseFloat(notax));
				ed[5].target.numberbox('setValue',notaxtotalamont);
				var rate=accMul(parseFloat(taxRate1), parseFloat(notax));
				var taxprice=accAdd(parseFloat(notax),parseFloat(rate));
		    	ed[2].target.numberbox('setValue',taxprice);
				var taxtotalamont=accMul(parseFloat(num), parseFloat(taxprice));
				ed[4].target.numberbox('setValue',taxtotalamont);
				var taxcount=taxtotalamont-notaxtotalamont;
				ed[6].target.numberbox('setValue',taxcount);
			    StOrderItemTable.datagrid('acceptChanges');
	    	}
	    });
    });
  
	function copyPartsDateAndBindEventItem(id, rowIndex, rowData) {
		id.datagrid('beginEdit', rowIndex);
		var taxRate = $('#taxRate').val();
		if(taxRate==null|| taxRate==''){
			taxRate=17;
		}
		taxRate = parseFloat(taxRate) / 100;
		taxRate=accAdd(taxRate,1);
		var ed = id.datagrid('getEditors', rowIndex);
		if (ed[0]) {
			if(rowData.partsNum==null||rowData.partsNum=='')
				ed[0].target.numberbox('setValue', '1.00');
			else
				ed[0].target.numberbox('setValue', rowData.partsNum);
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
		if (ed[3]) {
			ed[3].target.numberbox('setValue', rowData.notaxPrice);
			ed[3].target.click(function() {
				ed[3].target.select();
			});
			ed[3].target.keydown(function(e) {
				if (e.keyCode == '13') {
					ed[7].target.select();
				}
			});
		}
		if(ed[7]){
			 ed[7].target.text('setValue', rowData.itemRemark);
			 ed[7].target.click(function (){
				ed[7].target.select();
			 });
			 ed[7].target.keydown(function (e){
				if(e.keyCode == '13'){
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
		
		if(ed[1])
			ed[1].target.numberbox('setValue', ed[0].target.val());
		ed[0].target.select();
		ed[0].target.bind('keyup', function() {
		    var num = ed[0].target.val();// 代表采购数量
			if (num == null || num == '') {
				num = 0;
			}
			ed[1].target.numberbox('setValue', num);// 代表在途数量
			var noTaxPrice = ed[3].target.val();// 代表采购含税价格
			if (noTaxPrice == null || noTaxPrice == '') {
				noTaxPrice = 0;
			}
			var taxPrice = accMul(taxRate,parseFloat(noTaxPrice));
			ed[2].target.numberbox('setValue', taxPrice);
			var taxTotalAmont = accMul(parseFloat(num),
					parseFloat(taxPrice));
			ed[4].target.numberbox('setValue', taxTotalAmont);
			var noTaxTotalAmont = accMul(parseFloat(num),
					parseFloat(notaxprice));
			ed[5].target.numberbox('setValue', noTaxTotalAmont);
			var taxCount = accSub(parseFloat(taxTotalAmont),
					parseFloat(noTaxTotalAmont));
			ed[6].target.numberbox('setValue', taxCount);
			if (rowData && rowData.partsNum) {
				if (parseFloat(rowData.partsNum) < 0) {
					alertMsg('采购数量不能小于零', 'warning');
					ed[0].target.numberbox('setValue', rowData.partsNum);
				}
			}
		});
		
		ed[3].target.bind('keyup', function() {
			var num = ed[0].target.val();// 代表采购数量
				if (num == null || num == '') {
					num = 0.0;
				}
				var noTaxPrice = ed[3].target.val();
				if (noTaxPrice == null || noTaxPrice == '') {
					noTaxPrice = 0.0;
				}
				var noTaxTotalAmont = accMul(parseFloat(num),parseFloat(noTaxPrice));
				ed[5].target.numberbox('setValue', noTaxTotalAmont);
				var taxPrice = accMul(parseFloat(noTaxPrice), parseFloat(taxRate));
				ed[2].target.numberbox('setValue', taxPrice);
				var taxTotalAmont = accMul(parseFloat(num), parseFloat(taxPrice));
				ed[4].target.numberbox('setValue', taxTotalAmont);
				var taxCount = accSub(taxTotalAmont,noTaxTotalAmont);
				ed[6].target.numberbox('setValue', taxCount);
		});
		
		ed[1].target.numberbox( {
			onChange : function(newValue, oldValue) {
				$('#numTotal').val(
						accAdd(accSub(parseInt($('#numTotal').val()),
								parseInt(oldValue)), parseInt(newValue)));
			}
		});
		ed[4].target.numberbox( {
			onChange : function(newValue, oldValue) {
				$('#totalAmount').val(
						accAdd(accSub(parseFloat($('#totalAmount').val()),
								parseFloat(oldValue)), parseFloat(newValue)));
			}
		});
		ed[5].target.numberbox( {
			onChange : function(newValue, oldValue) {
				$('#notaxTotalamont').val(
						(accAdd(accSub(parseFloat($('#notaxTotalamont').val()),
								parseFloat(oldValue)), parseFloat(newValue)))
								.toFixed(2));
			}
		});
		
		ed[6].target.numberbox( {
			onChange : function(newValue, oldValue) {
				$('#taxCount').val(
						accAdd(accSub(parseFloat($('#taxCount').val()),
								parseFloat(oldValue)), parseFloat(newValue)));
			}
		});
	}
		  
	function loadDetailData(rowData){
	    doFromAndDatagridClear();
        $('#tt').tabs('select','采购单明细');
	    $('#orderId').val(rowData.orderId) ;
	    $('#orderDate').val(rowData.orderDate) ;
	    $('#relcampName2').val(rowData.relcampName) ;
	    $('#relcampId').val(rowData.relcampId) ;
	    $('#deliveryDate').val(rowData.deliveryDate) ;
	    $('#numTotal').val(rowData.numTotal) ;
	    $('#totalAmount').val(rowData.totalAmount) ;
	    $('#manager').val(rowData.manager) ;
	    $('#managerName').val(rowData.managerName) ;
	    $('#buyer').val(rowData.buyer) ;
	    $('#buyerName').val(rowData.buyerName) ;
	    $('#notesType').attr("disabled",'disabled');
	    var examine=rowData.examineName;
	    if(examine=='已审核')
	    	$('#examine').val(rowData.examineName);
	    else
	    	$('#examine').val('未审核');
	    $('#paid').val(rowData.paidName);
	    $('#taxRate').val(rowData.taxRate);
	    $('#notaxTotalamont').val(rowData.notaxTotalamont);
	    $('#taxCount').val(rowData.taxCount);
	    $('#orderRemark').val(rowData.orderRemark);
	    $('#classification').combobox('setValue',rowData.classificationId);
	    $('#classification').combobox('setText',rowData.classification);
	    $('#notesType').combobox({disabled:true});
	    $('#notesType').combobox('setValue',rowData.notesTypeId);
	    $('#notesType').combobox('setText',rowData.notesType);
		$.ajax({   
			type : 'POST',
			url : 'StPurOrderAction!searchStOrderItemByOrderId.action',
			data : serializeObject($('#StPurOrderForm')),
			dataType : 'json',
			beforeSend:ajaxLoading,
			success : function(r){
			           if(r.total>0){
			        	   ajaxLoadEnd();
					       $('#StOrderItemTable').datagrid('loadData',r);
			           }
            }
	    }); 
		$('#spod_addPartsBtn').linkbutton('disable');             //将配件增加配件删除按钮置为不可用
		$('#spod_delPartsBtn').linkbutton('disable');
	}
	
//	function loadDefaultBillType(id){
//		$.ajax({   
//			type : 'POST',
//			url : 'StPurOrderAction!loadDefaultBillType.action',
//			data : '',
//			dataType : 'json',
//			success : function(r){
//				$('#notesType').combobox('setValue',r);
//	        }
//	    }); 
//	}
		  
	/**5.设置页面配件新增，删除按钮不可用，汇总新增，删除，修改按钮为可用状态**/
	function btnEnable(){
		 $('#spo_addBtn').linkbutton('enable');
		 $('#spo_delBtn').linkbutton('enable');
		 $('#spo_updateBtn').linkbutton('enable');
		 $('#spo_searchBtn').linkbutton('enable');
		 $('#spo_clearBtn').linkbutton('enable');
		 $('#spo_printBtn').linkbutton('enable');
		 $('#spo_exportBtn').linkbutton('enable');
		 $('#spo_importBtn').linkbutton('enable');
		 $('#spo_examineBtn').linkbutton('enable');
		 $('#spod_addPartsBtn').linkbutton('disable');
		 $('#spod_delPartsBtn').linkbutton('disable');
		 $('#orderDate').validatebox('remove'); 
		 $('#relcampName2').validatebox('remove'); 
		 $('#buyerName').validatebox('remove'); 
		 $('#deliveryDate').validatebox('remove'); 
		 $('#orderDate').val(getSystemTime2());
	}
			
	/**6.设置页面配件新增，删除按钮可用，汇总新增，删除，修改按钮为不可用状态**/
	function btnDisable(){
		 $('#spo_addBtn').linkbutton('disable');
		 $('#spo_delBtn').linkbutton('disable');
		 $('#spo_updateBtn').linkbutton('disable');
		 $('#spo_searchBtn').linkbutton('disable');
		 $('#spo_clearBtn').linkbutton('disable');
		 $('#spo_printBtn').linkbutton('disable');
		 $('#spo_exportBtn').linkbutton('disable');
		 $('#spo_importBtn').linkbutton('disable');
		 $('#spo_examineBtn').linkbutton('disable');
		 $('#spod_addPartsBtn').linkbutton('enable');
		 $('#spod_delPartsBtn').linkbutton('enable');
		 $('#orderDate').validatebox('reduce'); 
		 $('#relcampName2').validatebox('reduce'); 
		 $('#buyerName').validatebox('reduce'); 
		 $('#deliveryDate').validatebox('reduce'); 
	}
		  
	/**单击增加按钮，创建保存和取消按钮事件**/
	function addPersonnel(i){
		if(i==1){
			$('#tt').tabs('select','采购单明细');
		    doFromAndDatagridClear();
		    btnDisable();
		    $('#taxRate').numberbox('setValue',17);
		    $('#notesType').combobox({disabled:false });
		    $('#classification').combobox({disabled:false});
			var save ="<a id='save' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-save' plain='true' onclick='addStPurOrder();'>保存</a>";
			var cancel="<a id='cancel' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-undo' plain='true' onclick='save_cancel();'>取消</a>";
			if ($('#button').children('a').length == 0) {
				$('#button').append(save).append(cancel);
				$.parser.parse('#button');
			}
		    $('#orderDate').val(getSystemTime2());
		    $('#managerName').val(managerName);
		    //loadDefaultBillType("notesType");
		}else if(i==2){
			var selects = $('#stPurOrderTable').datagrid('getSelected');
			if (selects != null && selects != '') {
				loadDetailData(selects);
		        var examine=selects.examineName;
		        var pos = examine.indexOf('未审核');  
		        if(pos == -1) 
		    	    $.messager.alert('优亿软件提示', '对不起，该采购单已经被审核，不允许修改！', 'warning', function() {});
		        else
		        	$.messager.confirm('优亿软件提示', '请确认是否要修改采购单号为【'+selects.orderId+'】的采购单?', function(r){
	                   if(r){
	            	        btnDisable();		
							var save = "<a id='save' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-save' plain='true' onclick='updateStPurOrder();'>保存</a>";
							var cancel = "<a id='cancel' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-undo' plain='true' onclick='update_cancel();'>取消</a>";
							if ($('#button').children('a').length == 0) {
								$('#button').append(save).append(cancel);
								$.parser.parse('#button');
						    }
	                   }
		        	});
		    } else 
			    $.messager.alert('优亿软件提示', '抱歉，请先选中要修改的记录！');
		}
	}
	
	/** 采购单汇总，明细信息添加* */
	function addStPurOrder(){
		endEdit($('#StOrderItemTable'));
		var rows=$('#StOrderItemTable').datagrid('getRows');
		if(fill_verification(rows)){
			var data=$('#StOrderItemTable').datagrid('getData');
			$.ajax({
				type : 'POST',
				url : 'StPurOrderAction!addStPurOrder.action',                      
				data : $('#StPurOrderForm').serialize()+'&detailData='+JSON.stringify(data),
				dataType : 'json',
				success : function(r) {
				    if(r.success) {
				        save_cancel();
					    clearSearchCondition();
	  	            }
		        }
		    });
		}else
			 $.messager.alert('优亿软件提示','抱歉，表单必填项信息填写不完整或者采购单明细信息不存在，记录无法保存！');
	}
	
	/** 采购单汇总，明细信息修改* */
	function updateStPurOrder(){
		endEdit($('#StOrderItemTable'));
		var rows=$('#StOrderItemTable').datagrid('getRows');
		if(fill_verification(rows)){
			var data=$('#StOrderItemTable').datagrid('getData');
			$.ajax({
				type : 'POST',
				url : 'StPurOrderAction!updateStPurOrder.action',                      
				data : $('#StPurOrderForm').serialize()+'&detailData='+JSON.stringify(data),
				dataType : 'json',
				success : function(r) {
				    if(r.success) {
				    	update_cancel();
					    clearSearchCondition();
	  	            }
		        }
		    });
		}else
			$.messager.alert('优亿软件提示','抱歉，表单必填项信息填写不完整或者采购单明细信息不存在，记录无法保存！');
	}
	
	/**保存取消事件*/
	function save_cancel(){
	   doFromAndDatagridClear();
	   btnEnable();
	   $('#button').empty();
	}
	
	   /**修改取消事件**/
	function update_cancel() {
	   btnEnable();
	   $('#button').empty();	
	}
	
	/** 采购单汇总，明细信息删除* */
	function deleteStPurOrder(){
		var selects = $('#stPurOrderTable').datagrid('getSelected');
		if (selects != null && selects != '') {
			var examine = selects.examineName;
			pos = examine.indexOf('未审核');
			if (pos == -1)
				$.messager.alert('优亿软件提示', '对不起，该采购单已经被审核，不允许删除！');
			else {
				if(selects.storageId == null || selects.storageId == '') {
					$.messager.confirm('优亿软件提示', '请确认是否要删除采购单号为【' + selects.orderId + '】的采购单?', function(r) {
						if (r) {
							$.ajax( {
								type : "POST",
								url : "StPurOrderAction!deleteStPurOrder.action",
								data : 'orderId='+selects.orderId,
								dataType : "json",
								success : function callback(r) {
									if (r.success) {
										doFromAndDatagridClear();
										clearSearchCondition();
									} else
										$.messager.alert('优亿软件提示', '采购单删除方法异常！');
								}
							});
						}
					})
				}else
					$.messager.alert('优亿软件提示', '对不起，该采购单已经被入库，不允许删除！');
			}
		} else 
			$.messager.alert('优亿软件提示', '对不起，请先选中要删除的记录！');
	}
	
	/**必填项验证**/
	function fill_verification(rows){
		var isno=$('#StPurOrderForm').form('validate');
	    var isnosubmit=true;
	    if(rows!=null&&rows!=''){
	    	for(var i=0;i<rows.length;i++){
	        	var isn=$('#StOrderItemTable').datagrid('validateRow',i);
	        	if(!isn){
	        		isnosubmit=false;
	        		break;
	            }
	        }
	    }else
	    	isnosubmit=false;
	    if(isno&&isnosubmit)
			return true
		else
			return false;
	}
	
	/**配件删除**/
	function deleteDetailParts() {
		var selected = $('#StOrderItemTable').datagrid('getSelected');
		if (selected) {
			 $.messager.confirm('提示', '你确定要删除配件【'+selected.partsId+'】吗?', function(r){
				 if(r){
					 var index=$('#StOrderItemTable').datagrid('getRowIndex',selected);
					 $('#StOrderItemTable').datagrid('deleteRow',index);
					 calculate();
				 }
			 })
		}else 
			 $.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning',function(){});
	}
		   
	/**配件增加**/
	var d4;
	var closeState='undefined';
	function spomd_openAddPartsPage()
	{
		endEdit($('#StOrderItemTable'));
		detailData=$('#StOrderItemTable').datagrid('getData');
		var notesType=$('#notesType').combobox('getText')
		if(notesType!=null&&notesType!=''){
			if(notesType=='增值税票（含税）'){
		    	d4 = $('<div/>');
					d4.dialog({
						title: '配件选择',   
					    width: 800,
					    height: 560,
					    cache: false,
					    href: projectPath+'st/StPurOrder/StPurOrder_AddParts_Tax.jsp',
					    modal: true,
						buttons : [{
							text : '确定',
							iconCls : 'icon-add',
							handler : function (){
							   closeState=true;
							   var rows=$selectedParts.datagrid('getRows');
		                       var isnosubmit=true;
		                       for(var i=0;i<rows.length;i++){
		                         	var isno=$selectedParts.datagrid('validateRow',i);
		                         	if(!isno){
		                         		isnosubmit=false;
		                         		break;
		                             }
		                       }
		                       if(isnosubmit){
								   endEdit($selectedParts);
								   var selectPartsData=$selectedParts.datagrid('getData');
		                           $('#StOrderItemTable').datagrid('loadData',selectPartsData);
		                           calculate();
		                           d4.dialog('close');
							   }else
								   $.messager.alert('优亿软件提示','采购数量必须大于0！','warning',function(){});
						}}],
						onClose : function (){
						    if(closeState=='undefined')
						        $('#StOrderItemTable').datagrid('loadData', {total:0,rows:[]});
					    	$(this).dialog('destroy');
					    },
					    onLoad : function (){
		                   $("#pr").val($("#taxRate").val());
					    }
					});
			}else{
		    	d4 = $('<div/>');
					d4.dialog({
						title: '配件选择',   
					    width: 800,
					    height: 560,
					    cache: false,
					    href: projectPath+'st/StPurOrder/StPurOrder_AddParts.jsp',
					    modal: true,
						buttons : [{
							text : '确定',
							iconCls : 'icon-add',
							handler : function (){
							   closeState=true;
							   var rows=$selectedParts.datagrid('getRows');
		                       var isnosubmit=true;
		                       for(var i=0;i<rows.length;i++){
		                         	var isno=$selectedParts.datagrid('validateRow',i);
		                         	if(!isno){
		                         		isnosubmit=false;
		                         		break;
		                             }
		                       }
		                       if(isnosubmit){
								   endEdit($selectedParts);
								   var selectPartsData=$selectedParts.datagrid('getData');
		                           $('#StOrderItemTable').datagrid('loadData',selectPartsData);
		                           calculate();
		                           d4.dialog('close');
							   }else
								   $.messager.alert('优亿软件提示','采购数量必须大于0！','warning',function(){});
						}}],
						onClose : function (){
						    if(closeState=='undefined')
						        $('#StOrderItemTable').datagrid('loadData', {total:0,rows:[]});
					    	$(this).dialog('destroy');
					    },
					    onLoad : function (){
		                   $("#pr").val($("#taxRate").val());
					    }
					});
			}
	    }else
	    	$.messager.alert('优亿软件提示', '对不起，请先选择票据类型！', 'warning');
	 }
	
	 /**审核采购单事件**/
	 function examineButton(){
		 var selected = $('#stPurOrderTable').datagrid('getSelected');
	     if(selected!=''&&selected!=null){
	         if(selected.storageId==null||selected.storageId==''){
	        	 var examine=selected.examineName;
			     pos = examine.indexOf('未审核');  
			     if (pos == -1) {
			    	 $.messager.confirm('优亿软件提示', '请确认是否要取消审核该采购单？', function(r){
			              if(r)
			            	  updateExamine(selected);
	        		 });
			     }else{
			    	 $.messager.confirm('优亿软件提示', '请确认是否要审核该采购单？', function(r){
			               if(r)
			            	  updateExamine(selected);
	        		 });
			     }
	         }else
	        	 $.messager.alert('优亿软件提示', '对不起，该采购单已经被入库，不允许取消审核，请先删除入库单号【'+selected.storageId+'】的入库单后再执行本操作！', 'info', function() {});
	     }else
			 $.messager.alert('优亿软件提示','对不起，请先选中要审核的记录！','warning',function(){});	
	 }
	
	 /**修改审审核状态**/
	 function updateExamine(selected)
	 {
		 $.ajax({
				type : 'POST',
				url : 'StPurOrderAction!updateExamine.action',
				data :"orderId="+selected.orderId,
				dataType : 'json',
				success : function(r){
	        		 if(r.success)
	        			 clearSearchCondition()
	 	       }
	     });
	 }
	
	 /**清空采购单明细页面数据**/
	 function doFromAndDatagridClear()
	 {
		 $('#StPurOrderForm').form('clear');
		 $('#StOrderItemTable').datagrid('loadData', {total:0,rows:[]});
	 }
	   
	 /**计算采购数量，含税总额，未税总额，税额结果**/  
	 function calculate() {
	 	var numTotal=0.0;
	 	var totalAmount=0.0;
	 	var notaxTotalamont=0.0;
	 	var taxCount=0.0;
	 	var rows=$('#StOrderItemTable').datagrid('getRows');
	 	for(var i=0;i<rows.length;i++){
	 		numTotal=accAdd(parseFloat(numTotal),parseFloat(rows[i].partsNum));
	 		totalAmount=accAdd(parseFloat(totalAmount),parseFloat(rows[i].taxTotalamont));
	 		notaxTotalamont=accAdd(parseFloat(notaxTotalamont),parseFloat(rows[i].notaxTotalamont));
	 	}
	 	taxCount=accSub(parseFloat(totalAmount),parseFloat(notaxTotalamont));
	 	$('#numTotal').val(numTotal);
	    $('#totalAmount').val(totalAmount);
	    $('#notaxTotalamont').val(notaxTotalamont); 
	    $('#taxCount').val(taxCount);
	 }
	
	 /**清空查询条件**/
	 function clearSearchCondition()
	 {
	   $('#tt').tabs('select','采购单汇总');
	   $('#StPurOrderSearchForm').form('clear');
	   $('#stPurOrderTable').datagrid({
	    	url:'StPurOrderAction!searchByCondition.action?'+$('#StPurOrderSearchForm').serialize()
	   });
	 }
	 
	 /**综合查询**/
	 function searchByCondition(){
		 $('#tt').tabs('select','采购单汇总');
		 $.ajax({                                           
				type : 'POST',
		  		url : 'StPurOrderAction!searchByCondition.action',
				data : $('#StPurOrderSearchForm').serialize(),
				dataType : 'json',
				success : function(r){
				   $('#stPurOrderTable').datagrid('loadData',r);
			    }
		    }); 
	 }
	
	/**打开采购员信息选择页面**/
	 var d1;
	 function spom_openStfPage(){
		d1 = $('<div/>');
		d1.dialog({
			title: '采购员信息选择',   
		    width: 600,
		    height: 403,
		    cache: false,
		    href: projectPath+'st/StPurOrder/StPurOrder_EmployeeInfo.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
	 }
	
	 /**打开供应商信息选择页面（汇总）**/
	 var d2;
	 function spom_openRecampPage()
	 {
	    d2 = $('<div/>');
		d2.dialog({
			title: '供应商选择',   
		    width: 600,   
		    height: 403,
		    cache: false,
		    href: projectPath+'st/StPurOrder/StPurOrder_RecampInfo1.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
	 }
	 
	 /**打开供应商信息选择页面（明细）**/
	 var d3;
	 function spomd_openRecampPage()
	 {
	    d3 = $('<div/>');
		d3.dialog({
			title: '供应商选择',   
		    width: 600,
		    height: 403,
		    cache: false,
		    href: projectPath+'st/StPurOrder/StPurOrder_RecampInfo2.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
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
	 	showEditDialog("stPurOrderTable",null,"stPurOrderTablDiv","开始导出","导出配置",0,_callbackExcept);
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
	 	exportEsuyUIExcelFile(parentId,fieldNames,"采购单汇总"+getSystemTime());
	 }
 
	 function _except1(){
		 	showEditDialog("StOrderItemTable",null,"st_orderItem_table","开始导出","导出配置",0,_callbackExcept1);
	 }
		 
		 /**
		  * 导出excel回调函数
		  * 将筛选出的列导出到Excel文件
		  * 只支持Microsoft Office,不支持WPS
		  * @param parentId
		  * @param fieldNames  要导出的列字段
		  * @return
		  */
		 function _callbackExcept1(parentId,fieldNames){
		 	exportEsuyUIExcelFile(parentId,fieldNames,"采购单明细"+getSystemTime());
		 }
		 /**
		  * 导入excel
		  * 将要导入的Excel文件列顺序与选择对应的列顺序一致
		  * @return
		  */
		 function importStpurorder(){
			 $('#tt').tabs('select','采购单明细');
			    doFromAndDatagridClear();
			    btnDisable();
			    $('#notesType').combobox({disabled:false });
			    $('#classification').combobox({disabled:false});
				var save ="<a id='save' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-save' plain='true' onclick='addStPurOrderImport();'>保存</a>";
				var cancel="<a id='cancel' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-undo' plain='true' onclick='save_cancel();'>取消</a>";
				if ($('#button').children('a').length == 0) {
					$('#button').append(save).append(cancel);
					$.parser.parse('#button');
				}
			    $('#orderDate').val(getSystemTime2());
			    $('#managerName').val(managerName);
			   // loadDefaultBillType("notesType");
		 	    showEditDialog("StOrderItemTable",null,"st_orderItem_table","开始导入","导入配置",1,callbackStpurorder);
		 }
		 
		 
		 function addStPurOrderImport(){
				endEdit($('#StOrderItemTable'));
				var rows=$('#StOrderItemTable').datagrid('getRows');
			 if(fill_verification(rows)){
					var data=$('#StOrderItemTable').datagrid('getData');
					$.ajax({
						type : 'POST',
						url : 'StPurOrderAction!addStPurOrderImport.action',                      
						data : $('#StPurOrderForm').serialize()+'&detailData='+JSON.stringify(data),
						dataType : 'json',
						beforeSend:ajaxLoading,
						success : function(r) {
						    if(r.success) {
						    	ajaxLoadEnd();
						        save_cancel();
							    clearSearchCondition();
			  	            }
				        }
				    });
				}else{
					 $.messager.alert('优亿软件提示','抱歉，表单必填项信息填写不完整或者采购单明细信息不存在，记录无法保存！');
				}	
		 }
		 function callbackStpurorder(pid, data){
		 	var json = $.parseJSON(data);  
		 	$('#StOrderItemTable').datagrid('loadData',json);
		 	var stItemData=json.rows;
			var notaxTotal=0.00;
			var taxTotal=0.00;
			var taxCount=0.00;
		 	var sumNum=0.00;
		 	for(var i=0;i<stItemData.length;i++){
		 		if(stItemData[i].partsId==null || stItemData[i].partsId==""){
		 			$.messager.alert('优亿软件提示', '对不起，配件编码不能为空！', 'warning');
		 		}
		 		if(stItemData[i].partsNum!=null&&stItemData[i].partsNum!=''&&stItemData[i].partsNum!=undefined)
		 		   sumNum=accAdd(sumNum,stItemData[i].partsNum);
		 		if(stItemData[i].notaxTotalamont!=null&&stItemData[i].notaxTotalamont!=''&&stItemData[i].notaxTotalamont!=undefined)
		 		   notaxTotal=accAdd(notaxTotal,stItemData[i].notaxTotalamont);
		 		if(stItemData[i].taxTotalamont!=null&&stItemData[i].taxTotalamont!=''&&stItemData[i].taxTotalamont!=undefined)
		 		   taxTotal=accAdd(taxTotal,stItemData[i].taxTotalamont);
		 		if(stItemData[i].taxCount!=null&&stItemData[i].taxCount!=''&&stItemData[i].taxCount!=undefined)
		 		   taxCount=accAdd(taxCount,stItemData[i].taxCount);
		 	}
		 	$('#numTotal').val(sumNum.toFixed(2));
		 	$('#notaxTotalamont').val(notaxTotal.toFixed(2));
		 	$('#totalAmount').val(taxTotal.toFixed(2));
		 	$('#taxCount').val(taxCount.toFixed(2));
			
		 }
		 
		//打来领料员信息：采购员信息页面
		 function addprint(){
		 	 var selected=$('#stPurOrderTable').datagrid('getSelected')
		     if(selected!=''&&selected!=null){
		    	 window.open(projectPath+'st/StPurOrder/stpurorderprint.jsp?orderId='+selected.orderId,'demo',"fullscreen=1")
		 	}else
		 		 $.messager.alert('优亿软件提示','请选择要打印的采购单记录！','warning');
		 }

 