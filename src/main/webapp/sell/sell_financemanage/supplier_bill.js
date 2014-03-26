	$(function(){
		
		//
 		$('#tbs').tabs({
 			onSelect:function(title){
 				mytitle = title;
 			}
 		});
 		
 		
		//初试时间
	 	$('#accountDate').val(getStartDate($('#accountDate')));
		$('#accountDate2').val(getSystemTime());
		$('#instorehouseDate').val(getStartDate($('#instorehouseDate')));
		$('#instorehouseDate2').val(getSystemTime());
		//对账单汇总
		$('#datagrid_supplier_bill_id').datagrid({

			url : 'supplierBillAction!getSupplierBillInfor.action',
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
				title : '对账单号',
				width : 100,
				sortable : true,
				hidden : true  
			},{
				field : 'accountCode',
				title : '对账编号',
				width : 100,
				sortable : true
			},{
				field : 'accountDate',
				title : '对账日期',
				width : 100,
				sortable : true
			},{
				field : 'instorehouseCode',
				title : '入库单号',
				width : 100,
				sortable : true 
			},{
				field : 'number',
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
			},{
				field : 'remark',
				title : '备注',
				width : 200,
				sortable : true
			}
			]],onClickRow : function(rowIndex, rowData){}
			
		});
		
		//对账单明细
		$('#datagrid_supplier_bill_detail_id').treegrid({
			url : '',
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
				field : 'xsCarCode',
				title : '车辆编号',
				width : 100,
				sortable : true
			},{
				field : 'xsCarLicenseName',
				title : '厂牌名称',
				width : 100,
				sortable : true
			},{
				field : 'xsCarVinNumber',
				title : 'VIN编码',
				width : 80,
				sortable : true 
			},{
				field : 'number',
				title : '数量',
				width : 80,
				sortable : true
			},{
				field : 'vehicleCost',
				title : '单价',
				width : 80,
				sortable : true
			},{
				field : 'tax',
				title : '金额',
				width : 80,
				sortable : true
			}
			]],rowStyler:function(row,rowIndex){
			if (row.instorehouseCode=="汇总"){
				return 'background-color:#80FF80;';
			}
		 	},onBeforeExpand:function(rowData){
			//动态设置展开查询的url
			var url = 'supplierBillAction!getSupplierBillDetailChildInfor.action?instorehouseId='+ rowData.instorehouseId;
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
				sortable : true
			},{
				field : 'accountMoney',
				title : '已对金额',
				width : 80,
				sortable : true
			},{
				field : 'accountBalance',
				title : '未对金额',
				width : 80,
				sortable : true
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

		//点击新增按钮 开启 增加记录 修改记录 删除记录按钮
		function dbAddbutton(){
			//点击新增按钮时 清空明细datafrid 数据
			$('#form_supplier_bill_detail_id').form('clear');
			$('#accountPerson').combobox('select',personId);
			$('#tbs').tabs('select','对账单明细');
			$('#datagrid_supplier_bill_detail_id').treegrid('loadData',{total:0,rows:[]});
			var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveAllInfo(1)">保存</a>';
				var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
				if ($('#addbut').children('a').length == 0) {
					$('#addbut').append(save).append(cancel);
					$.parser.parse('#addbut');
				}
			
			}
		
		//点击修改按钮
		function dbEditbuttom(){
			var value = $('#datagrid_supplier_bill_id').datagrid('getSelections');
			if(value.length>0){
				$('#tbs').tabs('select','对账单明细');
				//将数据load给form 表单 
				$('#form_supplier_bill_detail_id').form('load',value[0]);
				$('#tax').val(value[0].accountSun);
				$('#nowtax').val(value[0].accountMoney);
				
				//
				$('#imgid').attr("style","display:none");
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
					   url: 'supplierBillAction!addSupplierBillInfor.action',
					   data: formvalue,
					   success: function(r){
						   if(r.success){
							 	$('#datagrid_supplier_bill_id').datagrid('load');
						   		$('#addbut').empty();
								$('#tbs').tabs('select','对账单汇总');
								
								$('#imgid').removeAttr("style");
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
								$('#datagrid_supplier_bill_id').datagrid('load');
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
			$('#form_supplier_bill_detail_id').form('clear');
			$('#datagrid_supplier_bill_detail_id').treegrid('loadData',{total:0,rows:[]});
			$('#tbs').tabs('select','对账单汇总');
		}
		//删除记录  
		function deleteInfor(){
			var value = $('#datagrid_supplier_bill_id').datagrid('getSelections');
		    var data = $('#datagrid_supplier_bill_id').datagrid('getSelected');
		    var index=findSelectRowIndex('datagrid_supplier_bill_id',data);
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
								  $('#datagrid_supplier_bill_id').datagrid('clearSelections');
								  $('#datagrid_supplier_bill_id').datagrid('reload');
								  setSelectRow('datagrid_supplier_bill_id',index);
							}
						}
				   	});
				}else{
						alertMsg('对不起，请先选择要删除的记录！', 'warning');
					}
				}
			});
		}
	
		
		//弹出窗口
			var dlog;
			function showYdDlog(url){
				dlog = $('<div/>');
				dlog.dialog({
					title: '入库信息',   
				    width: 850,
				    height: 403,
				    cache: false,
				    href: url,
				    modal: true,
				    onClose : function (){
				    	$(this).dialog('destroy');
				    }
				});
			 }
		
			var findinfomation = function() {
				if(mytitle=="对账单汇总"){
					$('#datagrid_supplier_bill_id').datagrid('load',serializeObject($('#form_supplier_bill_id')))
					addInitDate($('#accountDate'),$('#accountDate2'));
				}
				if(mytitle=="对账单明细"){
					reLoadTreeGrid('datagrid_supplier_bill_detail_id', 'form_supplier_bill_detail_id', 'supplierBillAction!getSupplierBillDetailPrentInfor.action', false);

				
				}
				if(mytitle=="应付款查询"){
					reLoadTreeGrid('datagrid_find_due_id', 'form_find_due_id', 'supplierBillAction!getDuePrentInfor.action', false);
					addInitDate($('#instorehouseDate'),$('#instorehouseDate2'));	
				}
			}
			function doConditionSubmit(formid,datagrid){
				var form =  formid.form();
				var formvalue = serializeObjectByflag(form,false);
				datagrid.datagrid('load',formvalue);
			}
	    
	    	//清空form表单
		 	function doClears(){
		 		if(mytitle=="对账单汇总"){
		 			$('#form_supplier_bill_id').form('clear');
					$('#datagrid_supplier_bill_id').datagrid('load',serializeObject($('#datagrid_supplier_bill_id')))
					addInitDate($('#accountDate'),$('#accountDate2'));
		 		}
		 		if(mytitle=="应付款查询"){
		 			$('#form_find_due_id').form('clear');
					reLoadTreeGrid('datagrid_supplier_bill_detail_id', 'form_supplier_bill_detail_id', 'supplierBillAction!getSupplierBillDetailPrentInfor.action', true);
		 		}
		 		if(mytitle=="对账单明细"){
		 			$('#form_supplier_bill_detail_id').form('clear');
		 			reLoadTreeGrid('datagrid_find_due_id', 'form_find_due_id', 'supplierBillAction!getDuePrentInfor.action', true);
					addInitDate($('#instorehouseDate'),$('#instorehouseDate2'));
		 		}
		 		
		 	}
		 	
			//导出
			function doexcept(){
				showEditDialog('datagrid_gathering_manage_record',null,'datagrid_gathering_manage_record_div',"开始导出","导出配置",0,_callbackExcept);
			}