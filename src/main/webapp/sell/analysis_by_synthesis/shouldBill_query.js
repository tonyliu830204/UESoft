//综合分析 车辆销售分析js文件
$(function (){

	$('#accountDate2').val(getSystemTime());
	 getStartDate($('#accountDate'));
	 $('#date2').val(getSystemTime());
	 getStartDate($('#date1'));
	
	$('#tt').tabs({   
		onSelect:function(title){  
		 tbtitle = title;
	}   
	});
	$('#datagrid_ys_bill_id').datagrid({
		url : 'gatheringManageAction!getShouldBillInfor.action',
		pagination : true,
		fit:true,
		fitColumns : true,
		sortOrder:'asc',
	    sortName:'account_Code',
		singleSelect : true,
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		rownumbers : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ 
		    {
			field : 'account_Code',
			title : '预收单号',
			width : 100,
			sortable : true
		}, {
			field : 'account_Date',
			title : '创建日期',
			width : 90,
			sortable : true,
		},
		{
			field : 'date_',
			title : '收款日期',
			width : 90,
			sortable : true,
		},{
			field : 'account_Id',
			title : '结算单号',
			width : 95,
			sortable : true
		}, {
			field : 'carBrand',
			title : '品牌',
			width : 70,
			sortable : true
		}, {
			field : 'xs_Model_Name',
			title : '型号',
			width : 130,
			sortable : true
		}, {
			field : 'xs_Car_Vin_Number',
			title : 'VIN号',
			width : 130,
			sortable : true
		}, {
			field : 'xs_Custom_Name',
			title : '客户姓名',
			width : 90,
			sortable : true
		}, {
			field : 'tel',
			title : '手机',
			width : 90,
			sortable : true
		},{
			field : 'stf_Name',
			title : '经办人',
			width : 120,
			sortable : true
		},{
			field : 'received_Money',
			title : '付款金额',
			width : 90,
			sortable : true
		},{
			field : 'received_Way',
			title : '付款方式',
			width :90,
			sortable : true
		},{
			field : 'invoice',
			title : '票据类型',
			width :90,
			sortable : true
		},{
			field : 'invoice_Num',
			title : '票据编号',
			width :90,
			sortable : true
		},{
			field : 'account_Classify_Name',
			title : '收款分类',
			width :80,
			sortable : true
		},{
			field : 'remark',
			title : '备注',
			width :100,
			sortable : true
		}
        ]]
	});	
	$('#datagrid_ye_bill_detail_id').datagrid({
		url : 'gatheringManageAction!getQkBillInfor.action',
		pagination : true,
		fit:true,
		fitColumns : true,
		sortOrder:'asc',
	    sortName:'account_Code',
		singleSelect : true,
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		rownumbers : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ 
		    {
			field : 'account_Code',
			title : '预收单号',
			width : 90,
			sortable : true
		}, {
			field : 'account_Id',
			title : '结算单号',
			width : 95,
			sortable : true
		}, {
			field : 'carBrand',
			title : '品牌',
			width : 70,
			sortable : true
		}, {
			field : 'xs_Model_Name',
			title : '型号',
			width : 130,
			sortable : true
		},{
			field : 'xs_Car_Vin_Number',
			title : 'VIN号',
			width : 130,
			sortable : true
		},  {
			field : 'xs_Custom_Name',
			title : '客户姓名',
			width : 90,
			sortable : true
		}, {
			field : 'tel',
			title : '手机',
			width : 90,
			sortable : true
		},{
			field : 'account_Arrears',
			title : '欠款金额',
			width : 90,
			sortable : true
		},{
			field : 'account_Classify_Name',
			title : '收款分类',
			width :80,
			sortable : true
		}
        ]]
	});	
});
	
		
	
		
		
	
		//条件查询
		function doFindbyCondition(){

			if(tbtitle=="应收款查询"){
				$('#datagrid_ys_bill_id').datagrid('unselectAll');
				$('#datagrid_ys_bill_id').datagrid('load',serializeObject($('#form_ys_bill_id')));	
				addInitDate($('#accountDate'),$('#accountDate2'));

			}
			if(tbtitle=="应收款欠款查询"){
				$('#datagrid_ye_bill_detail_id').datagrid('unselectAll');
				$('#datagrid_ye_bill_detail_id').datagrid('load',serializeObject($('#form_ye_bill_detail_id')));		
				addInitDate($('#date'),$('#date2'));

			}
			
			
	}

		//清空 
		function doClear(){
			
			
			if(tbtitle=="应收款查询"){
				$('#form_ys_bill_id').form('clear');
				$('#datagrid_ys_bill_id').datagrid('load', serializeObject($('#form_ys_bill_id')));
				addInitDate($('#accountDate'),$('#accountDate2'));

	   	 	}if(tbtitle=="应收款欠款查询"){
	   	 		$('#form_ye_bill_detail_id').form('clear');
	   	 		$('#datagrid_ye_bill_detail_id').datagrid('load', serializeObject($('#form_ye_bill_detail_id')));
				addInitDate($('#date'),$('#date2'));

	   	 	}
		
   	 		
   	 	}
	   	 	
			
		
		
		
		function _except(){
			
		
				if(tbtitle =='应收款查询'){
					var data =  $("#datagrid_ys_bill_id").datagrid('getData'); 
					 if(data.rows.length==0){
						 alertMsg('数据列表为空，不能导出！', 'warning');
						 return ;
					 }
					showEditDialog("datagrid_ys_bill_id",null,"datagrid_ys_bill_div","开始导出","导出配置",0,_callbackExcept);
					
				}else if(tbtitle =='应收款欠款查询'){
					var data =  $("#datagrid_ye_bill_detail_id").datagrid('getData'); 
					 if(data.rows.length==0){
						 alertMsg('数据列表为空，不能导出！', 'warning');
						 return ;
					 }
					showEditDialog("datagrid_ye_bill_detail_id",null,"datagrid_ye_bill_detail_div","开始导出","导出配置",0,_callbackExcept2);	
				}
				
			}
			
			function _callbackExcept(parentId,fieldNames){
				exportEsuyUIExcelFile(parentId,fieldNames,"应收款查询"+getSystemTime());
			}
			function _callbackExcept2(parentId,fieldNames){
				exportEsuyUIExcelFile(parentId,fieldNames,"应收款欠款查询"+getSystemTime());
			}
			
			/**
			 * 打印字段设置
			 * 编辑、选择不同分组
			 * @return
			 */
			function _config(){
				if(tbtitle =='应收款查询'){
					var data =  $("#datagrid_ys_bill_id").datagrid('getData'); 
					 if(data.rows.length==0){
						 alertMsg('数据列表为空，不能打印！', 'warning');
						 return ;
					 }
					showEditDialog("datagrid_ys_bill_id",personnelSumTable,"datagrid_ys_bill_div","开始打印","打印配置",2,_print);
					
				}else if(tbtitle =='应收款欠款查询'){
					var data =  $("#datagrid_ye_bill_detail_id").datagrid('getData'); 
					 if(data.rows.length==0){
						 alertMsg('数据列表为空，不能打印！', 'warning');
						 return ;
					 }
					showEditDialog("datagrid_ye_bill_detail_id",personnelSumTable,"datagrid_ye_bill_detail_div","开始打印","打印配置",2,_print);	
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
			
