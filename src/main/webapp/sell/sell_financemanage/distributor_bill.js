$(function (){
		//初试时间
	 	$('#accountDate').val(getStartDate($('#accountDate')));
		$('#accountDate2').val(getSystemTime());
		
		//页面初始化时，设置按钮的状态
		$('#tbs').tabs({   
			onSelect:function(title){  
			
			 mytitle = title;
				
		    }   
		});
		personId=$('#personId').val();
	
		//对账单汇总
		$('#datagrid_distributor_bill_id').datagrid({

			url : 'supplierBillAction!getDisBill.action',
			pagination : true,
			fit : true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'accountId',
			loadMsg : "数据加载中，请稍后……",
			columns : [[{
				field : 'accountId',
				title : '对账编号',
				width : 100,
				sortable : true,
				hidden : true  
			},{
				field : 'accountCode',
				title : '对账单号',
				width : 100,
				sortable : true
			},{
				field : 'accountDate',
				title : '对账日期',
				width : 100,
				sortable : true
			},{
				field : 'allocatecode',
				title : '调(退)单号',
				width : 100,
				sortable : true 
			},{
				field : 'num',
				title : '数量',
				width : 80,
				sortable : true
			},{
				field : 'accountSun',
				title : '金额',
				width : 80,
				sortable : true
			},{
				field : 'accountMoney',
				title : '付款',
				width : 80,
				sortable : true
			},{
				field : 'accountBalance',
				title : '应付',
				width : 80,
				sortable : true
			},{
				field : 'accountPerson',
				title : '经办人',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
					return rowData.accountPersonName;
				}
			},{
				field : 'state1',
				title : '付讫',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				return rowData.state2;
			}
			},
			{
				field : 'is_invoice',
				title : '开票',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				return rowData.invoice;
			}
			},{
				field : 'remark',
				title : '备注',
				width : 200,
				sortable : true
			}
			]],onDblClickRow : function(rowIndex, rowData){
	 		$('#tbs').tabs('select','对账单明细');
	 		$('#form_supplier_bill_detail_id').form('load', rowData);
	 		var  Id=rowData.allocatecode;
			loadInstoreDel(Id);
		}
		});
		
		//对账单明细
		$('#datagrid_supplier_bill_detail_id').treegrid({
			url : '',
			fit : true,
			fitColumns : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			pagination : true,
			idField : 'allocateId',
			treeField : 'allocatecode',
			loadMsg : "数据加载中，请稍后……",
			columns : [[{
				field : 'allocateId',
				title : '编号',
				width : 50,
				sortable : true,
				hidden:true
			}, {
				field : 'allocatecode',
				title : '调拨单单号',
				width :155,
				sortable : true
			}, {
				field : 'carCode',
				title : '车辆编号',
				width : 100,
				sortable : true
			},{
				field : 'carLicenseName',
				title : '厂牌名称',
				width : 100,
				sortable : true
			},{
				field : 'carVinNumber',
				title : 'VIN编码',
				width : 80,
				sortable : true 
			},{
				field : 'num',
				title : '数量',
				width : 80,
				sortable : true
			},{
				field : 'modelCostPrice',
				title : '成本价',
				width : 80,
				sortable : true
			},{
				field : 'allAmount',
				title : '调拨金额',
				width : 80,
				sortable : true
			},{
				field : 'invoice',
				title : '开票',
				width : 80,
				sortable : true
			},{
				field : 'is_invoice',
				title : '开票',
				width : 80,
				sortable : true,
				hidden:true
			}
			]],rowStyler:function(row,rowIndex){
			if (row.allocatecode=="汇总"){
				return 'background-color:#c0d8d8;';
			}
		 	},onBeforeExpand:function(rowData){
			//动态设置展开查询的url
			var url = 'sellAllocatelAction!getAllocatel.action?allocateId=' + rowData.allocateId;
			$('#datagrid_supplier_bill_detail_id').treegrid("options").url = url;  	
			return true;
			}
			
		});
		
		//应付款查询
		$('#datagrid_find_due_id').treegrid({
			url : 'supplierBillAction!getDuePrentInfor.action',
			fit : true,
			fitColumns : true,  
			idField : 'instorehouseId',
			treeField : 'instorehouseCode',
			loadMsg : "数据加载中，请稍后……",
			columns : [[{
				field : 'instorehouseId',
				title : '入库单号',
				width : 100,
				sortable : true,
				hidden : true
			},{
				field : 'instorehouseCode',
				title : '入库编号',
				width : 100,
				sortable : true
			},{
				field : 'instorehouseDate',
				title : '入库日期',
				width : 100,
				sortable : true
			},{
				field : 'xsCarBrand',
				title : '车辆品牌',
				width : 100,
				sortable : true
			},{
				field : 'xsCarModel',
				title : '车辆型号',
				width : 100,
				sortable : true
			},{
				field : 'xsCarVinNumber',
				title : 'VIN编码',
				width : 80,
				sortable : true 
			},{
				field : 'tax',
				title : '入库金额',
				width : 80,
				sortable : true,
				
			},{
				field : 'accountMoney',
				title : '已对金额',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				
				
			}
			},{
				field : 'accountBalance',
				title : '未对金额',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				
				
			}
			}
			]],onBeforeExpand:function(rowData){
			//动态设置展开查询的url
			var url = 'supplierBillAction!getDueChildInfor.action?instorehouseId='+ rowData.instorehouseId;
			$('#datagrid_find_due_id').treegrid("options").url = url;  	
			return true;
			},rowStyler:function(row,index){
				if (row.instorehouseCode=="汇总"){
					return 'background-color:#80FF80;';
				}
			 }
			
		});
	});	

		var mytitle;
		//条件查询提交
		function doConditionSubmit(formid,datagrid){
			var form =  formid.form();
			var formvalue = serializeObjectByflag(form,false);
			datagrid.datagrid('load',formvalue);
		}
    
    	//清空form表单
	 	function doClear(fromid,dgrid){
	 		fromid.form('clear');
	 		doConditionSubmit(fromid,dgrid);
	 	}
	 	
	 	function  loadInstoreDel(parentId){
	 		var url="sellAllocatelAction!getFFatherList.action?allocatecode="+parentId;
	 		//url+=$('#carQueryForm').serialize();
	 		$('#datagrid_supplier_bill_detail_id').treegrid({
	 			url:url
	 		});
	 	}
	//点击新增按钮 开启 增加记录 修改记录 删除记录按钮
	function dbAddbutton(){
		//点击新增按钮时 清空明细datafrid 数据
		$('#form_supplier_bill_detail_id').form('clear');
	    $('#datagrid_supplier_bill_detail_id').treegrid({
			url:"sellAllocatelAction!getFFatherList.action?allocatecode="+'null'
		});
		$('#tbs').tabs('select','对账单明细');
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveAllInfo(1)">保存</a>';
			var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
			if ($('#addbut').children('a').length == 0) {
				$('#addbut').append(save).append(cancel);
				$.parser.parse('#addbut');
			}
	
			$('#personId').combobox('select',personId);
			$('#acDate').val(getSystemTime2());
			
		
		}
	
	//点击修改按钮
	function dbEditbuttom(){
		var value = $('#datagrid_distributor_bill_id').datagrid('getSelections');
		if(value.length>0){
			$('#tbs').tabs('select','对账单明细');
			//将数据load给form 表单 
			$('#form_supplier_bill_detail_id').form('load',value[0]);
			$('#tax').val(value[0].accountSun);
			$('#nowtax').val(value[0].accountMoney);
			
			loadInstoreDel(value[0].allocatecode);
			//
			$('#imgid').attr("style","display:none");
			$('#imgid2').attr("style","display:none");
			var edit = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveAllInfo(2);">保存</a>';
				var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
				if ($('#editbut').children('a').length == 0) {
					$('#editbut').append(edit).append(cancel);
					$.parser.parse('#editbut');
				}
		}else{
			alertMsg('请选择要修改的记录！', 'warning');
		}
	}
	//
	
	//点击保存按钮
	function saveAllInfo(a){
		var form =  $('#form_supplier_bill_detail_id').form();
		var formvalue = serializeObject(form);
			//为新增
			if(a==1){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'supplierBillAction!addDistributorBill.action',
				   data: formvalue,
				   success: function(r){
					   if(r.success){
						 	$('#datagrid_distributor_bill_id').datagrid('load');
					   		$('#addbut').empty();
							$('#tbs').tabs('select','对账单汇总');
							$('#imgid').removeAttr("style");
							$('#imgid2').removeAttr("style");
					   }
				   }
				});
			}
			//修改
			if(a==2){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'supplierBillAction!doEditInfor.action',
				   data: formvalue,
				   success: function(r){
					   if(r.success){
							$('#datagrid_distributor_bill_id').datagrid('load');
						   	$('#editbut').empty();
						   	$('#tbs').tabs('select','对账单汇总');
					   	}
				   }
				});
			}
		}
	//取消
	function cancel(){
		$('#addbut').empty();
		$('#editbut').empty();
	}
	//删除记录  
	function deleteInfor(){
		var value = $('#datagrid_distributor_bill_id').datagrid('getSelections');
		$.messager.confirm('优亿软件提示','请确认是否要删除该记录？',function(b){
			if(b){
			if(value.length>0){
				//发送请求
				$.ajax({
					type : 'POST',
					url : 'supplierBillAction!doDeleteInfor.action',
					data : value[0],
				    dataType : 'json',
					success : function(r){
						if(r.success){
							$('#datagrid_distributor_bill_id').datagrid('reload');
						}
					}
			   	});
			}else{
					alertMsg('对不起，请先选择要删除的记录！', 'warning');
				}
			}
		});
	}

	// 查询
	var findinfomation = function() {
		if(mytitle=="对账单汇总"){
			doConditionSubmit($('#form_supplier_bill_id'),$('#datagrid_distributor_bill_id'));
		}
		if(mytitle=="对账单明细"){
		
		var form =  $('#form_supplier_bill_detail_id').form();
		var formvalue = serializeObject(form);
		var pageSize=$('#datagrid_supplier_bill_detail_id').treegrid('options').pageSize;
		var pageNumber=$('#datagrid_supplier_bill_detail_id').treegrid('options').pageNumber;
		$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'sellAllocatelAction!getFFatherList.action?page='+pageNumber+'&rows='+pageSize,
			   data: formvalue,
			   success: function(r){
				   if(r){
					$('#datagrid_supplier_bill_detail_id').treegrid('loadData',r);
				   }
			   }
			});
		
		}
		if(mytitle=="应付款查询"){
			var form =  $('#form_find_due_id').form();
			var formvalue = serializeObject(form);
			$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'supplierBillAction!getDuePrentInfor.action',
				   data: formvalue,
				   success: function(r){
					   if(r){
						$('#datagrid_find_due_id').treegrid('loadData',r);
					   }
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

		if(mytitle =='对账单汇总'){
			showEditDialog("datagrid_distributor_bill_id",null,"datagrid_supplier_bill_div","开始导出","导出配置",0,_callbackExcept);
			
		}else if(mytitle =='对账单明细'){

			showEditDialog("datagrid_supplier_bill_detail_id",null,"datagrid_supplier_bill_detail_div","开始导出","导出配置",0,_callbackExcept2);	
		}
		
		
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
		exportEsuyUIExcelFile(parentId,fieldNames,"对账单汇总"+getSystemTime());
	}
	function _callbackExcept2(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"对账单明细"+getSystemTime());
	}
	
	/**
	 * 打印字段设置
	 * 编辑、选择不同分组
	 * @return
	 */
	function _config(){
		if(mytitle =='对账单汇总'){
			showEditDialog("datagrid_distributor_bill_id",personnelSumTable,"datagrid_supplier_bill_div","开始打印","打印配置",2,_print);
			
		}else if(mytitle =='对账单明细'){
			showEditDialog("datagrid_supplier_bill_detail_id",personnelSumTable,"datagrid_supplier_bill_detail_div","开始打印","打印配置",2,_print);	
		}
	
	}
	/**
	 * 打印字段设置回调函数
	 * 将选择的列打印
	 * @return
	 */
	function _print(parentId,fieldNames){
		printEsuyUIPreview(parentId,fieldNames);
	}
	