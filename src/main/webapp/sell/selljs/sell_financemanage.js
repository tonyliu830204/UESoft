 	
		//条件查询提交
		function doConditionSubmit(formid,datagrid){
			var form =  formid.form();
			var formvalue = serializeObject(form);
			datagrid.datagrid('load',formvalue);
		}
    
    	//清空form表单
	 	function doClear(fromid,dgrid){
	 		fromid.form('clear');
	 		doConditionSubmit(fromid,dgrid);
	 	}
	 	$(function (){
	 	 //初始化时间
	 	 addInitDate($('#reserve_Date'),$('#reserve_Date2'));
		//应收款作业
		$('#datagrid_should_gathering_manage_record_id').datagrid({
			url : 'gatheringManageAction!findShouldAccountInfor.action',
			pagination : true,
			fit : true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'collections_Id',
			loadMsg : "数据加载中，请稍后……",
			columns : [[{
				field : 'collections_Id',
				title : '收款单号',
				width : 120,
				sortable : true,
				hidden : true
			},{
				field : 'account_Code',
				title : '收款编号',
				width : 120,
				sortable : true
			},{
				field : 'account_Date',
				title : '创建日期',
				width : 120,
				sortable : true
			},{
				field : 'custom_Id',
				title : '客户编号',
				width : 80,
				sortable : true,
				hidden : true
			},{
				field : 'xs_Custom_Name',
				title : '客户名称',
				width : 90,
				sortable : true
			},{
				field : 'xs_Car_Id',
				title : '车辆序号',
				width : 80,
				sortable : true,
				hidden : true
			},{
				field : 'xs_Car_Code',
				title : '车辆编号',
				width : 150,
				sortable : true
			},{
				field : 'xs_Car_Vin_Number',
				title : 'VIN号',
				width : 120,
				sortable : true
			},{
				field : 'account_Receivables',
				title : '应收金额',
				width : 100,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				return parseFloat(value);
			}
			},{
				field : 'account_Cumulative',
				title : '累计收款',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				return parseFloat(value);
			}
			},{
				field : 'sum_Money',
				title : '累计预收金额',
				width : 100,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				return parseFloat(value);
			}
			},{
				field : 'account_Arrears',
				title : '欠款',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				return (rowData.account_Receivables-rowData.account_Cumulative-rowData.sum_Money).toFixed(2);
			}
			}, {
				field : 'account_Unfinished',
				title : '是否付清',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				if(value=="未付清"){
					return '<font color="red">'+value+'</font>';
				}else{
					return value;
				}
				
			}
			}, {
				field : 'account_Arrears_Age',
				title : '应收款帐龄',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				return parseInt(value);
			}
			}, {
				field : 'account_Type',
				title : '收款类型',
				width : 80,
				sortable : true
			}, {
				field : 'account_Classify',
				title : '收款分类',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
					return rowData.account_Classify_Name;
				}
			}, {
				field : 'account_Person',
				title : '经办人',
				width : 100,
				sortable : true
			}
			]],onClickRow : function(rowIndex, rowData){
			$('#datagrid_should_gathering_manage_detail_id').datagrid({
				url : 'gatheringManageAction!getAmonunt.action?collections_Id='+rowData.collections_Id
			});
		
			}
		});
		
		//预收款使用记录
		$('#datagrid_gathering_manage_detail_id').datagrid({
			url : '',
			pagination : false,
			fit : true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'details_Id',
			loadMsg : "数据加载中，请稍后……",  
			columns : [[{
				field : 'details_Id',
				title : '收款单号',
				width : 100,
				sortable : true,
				hidden : true
			},{
				field : 'date_',
				title : '收款日期',
				width : 100,
				sortable : true
			},{
				field : 'detail_Type',
				title : '收款类型',
				width : 100,
				sortable : true
			},{
				field : 'invoice',
				title : '发票类型',
				width : 100,
				sortable : true
			},{
				field : 'invoice_Num',
				title : '票据编号',
				width : 120,
				sortable : true
			},{
				field : 'received_Money',
				title : '收款金额',
				width : 80,
				sortable : true
			},{
				field : 'received_Way',
				title : '付款方式',
				width : 100,
				sortable : true
			}, {
				field : 'stf_Id',
				title : '收款人',
				width : 80,
				sortable : true
			},{
				field : 'remark',
				title : '备注',
				width : 200,
				sortable : true
				}
			]]
			
		});
		//应收款作业 收款次数记录  
		$('#datagrid_should_gathering_manage_detail_id').datagrid({
			url : '',
			pagination : true,
			fit : true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'details_Id',
			loadMsg : "数据加载中，请稍后……",
			columns : [[{
				field : 'details_Id',
				title : '收款单号',
				width : 100,
				sortable : true,
				hidden : true
			},{
				field : 'date_',
				title : '收款日期',
				width : 100,
				sortable : true
			},{
				field : 'detail_Type',
				title : '收款类型',
				width : 100,
				sortable : true
			},{
				field : 'invoice',
				title : '发票类型',
				width : 100,
				sortable : true
			},{
				field : 'invoice_Num',
				title : '票据编号',
				width : 120,
				sortable : true
			},{
				field : 'received_Money',
				title : '收款金额',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				return parseFloat(value);
			}
			},{
				field : 'received_Way',
				title : '付款方式',
				width : 100,
				sortable : true
			}, {
				field : 'stf_Id',
				title : '收款人',
				width : 80,
				sortable : true
			},{
				field : 'remark',
				title : '备注',
				width : 200,
				sortable : true
				}
			]]
			
		});
		//预定单选择 /预收款作业
		$('#datagrid_gathering_manage_record_id').datagrid({
			url : 'gatheringManageAction!findBillInfor.action',
			pagination : true,
			fitColumns:true,
			fit : true,
			rownumbers : true,
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'reserve_Id',
			loadMsg : "数据加载中，请稍后……",
			columns : [[{//
				field : 'reserve_Id',
				title : '预订单号',
				width : 120,
				sortable : true,
				hidden:true
			},{
				field : 'reserve_Code',
				title : '预订编号',
				width : 120,
				sortable : true
			},{
				field : 'payment_Money',
				title : '预付金额',
				width : 120,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				return parseFloat(value);
			}
			},{
				field : 'account_Cumulative',
				title : '实收金额',
				width : 120,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				return parseFloat(value);
			}
			},{
				field : 'reserve_Date',
				title : '订单日期',
				width : 120,
				sortable : true
			},{
				field : 'xs_Custom_Name',
				title : '客户名称',
				width : 80,
				sortable : true
			},{
				field : 'pact_Code',
				title : '合同编号',
				width : 120,
				sortable : true
			},{
				field : 'xs_Car_Code',
				title : '车辆编号',
				width : 120,
				sortable : true
			},{
				field : 'xs_Model_Name',
				title : '车辆型号',
				width : 120,
				sortable : true
			},{
				field : 'car_Color2',
				title : '车身颜色',
				width : 80,
				sortable : true
			}, {
				field : 'xs_Car_Vin_Number',
				title : 'VIN编码',
				width : 120,
				sortable : true
			},{
				field : 'xs_Car_Ocn',
				title : 'OCN码',
				width : 120,
				sortable : true
			},{
				field : 'stf_Id',
				title : '经办人id',
				width : 80,
				sortable : true,
				hidden : true
			},{
				field : 'stf_Name',
				title : '经办人',
				width : 200,
				sortable : true
			},{
				field : 'custom_Id',
				title : '客户编号',
				width : 80,
				sortable : true,
				hidden : true
			},{
				field : 'xs_Car_Id',
				title : '车辆编号',
				width : 80,
				sortable : true,
				hidden : true
			}
			]],onClickRow : function(rowIndex, rowData){
				$('#datagrid_gathering_manage_detail_id').datagrid({
					url : 'gatheringManageAction!getUseRecord.action?reserve_Code='+rowData.reserve_Code
				});
			},onDblClickRow : function(rowIndex, rowData){
			//将该行数据load给form表单
			$('#form_gathering_manage_south_id').form('load',rowData);
			//关闭窗口
			$('#yudingdanhaoxuanze_dalog_id').dialog('close');
			//选中本次收款
			//$('#received_Money_Id').selected();
			
			}
		});
		//车辆加装汇总    车辆档案（未申请加装）
		$('#datagrid_center_car_fix_id').datagrid({
			url : 'carFixAction!getCarInfor.action',
			pagination : true,
			fit : true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			sortOrder:'desc',
		    sortName:'xs_car_code',
			idField : 'xs_car_id',
			loadMsg : "数据加载中，请稍后……",
			columns : [[{
				field : 'xs_car_id',
				title : '车辆编号',
				width : 100,
				sortable : true,
				hidden : true
			},{
				field : 'xs_car_code',
				title : '车辆编号',
				width : 100,
				sortable : true
			},{
				field : 'xs_car_color',
				title : '车辆外观色',
				width : 100,
				sortable : true
			},{
				field : 'xs_car_vin_number',
				title : 'VIN号',
				width : 100,
				sortable : true 
			},{
				field : 'xs_car_ocn',
				title : 'OCN号',
				width : 120,
				sortable : true
			},{
				field : 'xs_car_motor_number',
				title : '发动机号',
				width : 120,
				sortable : true
			},{
				field : 'xs_car_brand',
				title : '车辆品牌',
				width : 120,
				sortable : true
			},{
				field : 'xs_car_model_id',
				title : '车辆型号',
				width : 80,
				sortable : true
			},{
				field:'fix_statusN',
				title:'操作',
				width:100,
				align:'center',
	            formatter:function(value,rec,index){
				var s = '';
				if(value==wjz){
			        s = '<a  onclick="applyFix('+index+');"><font color="red">申请加装<font/></a> ';
				}else if(value==sqjz){
					s = '<font color="black">已申请加装<font/>';
				}else{
					s = '<font color="black">已加装<font/>';
				}
	            return s;
	        	}
			}
			]],onClickRow : function(rowIndex, rowData){
			$('#datagrid_south_car_fix_id').datagrid({
				url : 'carFixAction!getCarFixInfor.action?xs_car_id='+rowData.xs_car_id
			});}
			
		});
		
		
	});