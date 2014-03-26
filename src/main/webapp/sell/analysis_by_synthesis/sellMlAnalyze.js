
$(function (){
	$('#xsSellDate2').val(getSystemTime());
	 getStartDate($('#xsSellDate'));
	 
	
		$('#carAndCustomList').datagrid({
			url : 'sellCustomAnalyseAction!querySellMlAnaly.action',
			pagination : true,
			fit:true,
			sortOrder:'asc',
		    sortName:'xsCarSelData',
			singleSelect : true,
			showFooter:true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			frozenColumns:[[
			                {
			                	field : 'cuName',
			    				title : '客户姓名',
			    				width : 80,
			    				sortable : true
			    			},{
			    				field : 'xsCarSelData',
			    				title : '销售日期',
			    				width : 125,
			    				sortable : true
			    			}, {
			    				field : 'carVinNumber',
			         			title : 'VIN码',
			    				width : 125,
			    				sortable : true
			    			}
			                   ]],rowStyler:function(row,rowIndex){
							if (row.cuName=="汇总"){
								return 'background-color:#dce8db;';
							}
							},
			               
							columns : [ [ 
							{
								field : 'stfName',
								title : '业务员',
								width : 80,
								sortable : true,
							}, {
								field : 'carBrandName',
								title : '品牌',
								width : 80,
								sortable : true,
							},{
								field : 'carModelName',
								title : '型号',
								width : 125,
								sortable : true
							},{
								field : 'carColorName',
								title : '颜色',
								width : 80,
								sortable : true
							},
							    {
								field : 'ocn',
								title : 'OCN码',
								width : 100,
								sortable : true
							},  {
								field : 'fdj',
								title : '发动机',
								width : 90,
								sortable : true
							},{
								field : 'modelCostPrice',
								title : '成本价',
								width : 90,
								sortable : true
							},{
						    	field : 'xsCarSelTransactionMoney',
								title : '销售价',
								width : 90,
								sortable : true
						   
						    }, {
								field : 'decorate_amount',
								title : '装潢成本',
								width : 85,
								sortable : true,
							}, {
								field : 'decorate_sell',
								title : '装潢销售',
								width : 90,
								sortable : true
							},{
								field : 'zhml',
								title : '装潢毛利',
								width : 100,
								sortable : true
							},{
								field : 'cost_price',
								title : '代办成本',
								width : 80,
								sortable : true
							},{
								field : 'db_project_cost',
								title : '代办销售',
								width : 80,
								sortable : true
							},{
								field : 'dbml',
								title : '代办毛利',
								width :90,
								sortable : true
							},{
								field : 'custom_cost',
								title : '客户付保',
								width : 90,
								sortable : true
							}, {
								field : 'prime_cost',
								title : '保险成本',
								width : 90,
								sortable : true
							},{
								field : 'bxml',
								title : '保险毛利',
								width : 90,
								sortable : true
							},{
								field : 'zml',
								title : '总毛利',
								width : 90,
								sortable : true
							},{
								field : 'xs_model_salesLimitPrice',
								title : '销售限价',
								width : 90,
								sortable : true
							},{
								field : 'xjml',
								title : '限价毛利',
								width : 90,
								sortable : true
							},{
								field : 'invoice_parce',
								title : '开票金额',
								width : 90,
								sortable : true
							},{
								field : 'invoice_date',
								title : '开票日期',
								width : 90,
								sortable : true
							},{
								field : 'invoice_code',
								title : '开票单号',
								width : 90,
								sortable : true
							}
						    ]]
	   });
	});
	
		
	//清空form表单
		function doFormClear(){
			$('#form_list_id').form('clear');
			$('#carAndCustomList').datagrid('unselectAll');
			$('#carAndCustomList').datagrid('load', serializeObject($('#form_list_id')));
			$('#car_Brand_id').combobox('reload');
			$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
			addInitDate($('#xsSellDate'),$('#xsSellDate2'));

		}
		
		var queryCarReserve = function() {

			$('#carAndCustomList').datagrid('unselectAll');
			$('#carAndCustomList').datagrid('load',serializeObject($('#form_list_id')));
			addInitDate($('#xsSellDate'),$('#xsSellDate2'));
		}
		
		
		function doExport() {
			var data =  $("#carAndCustomList").datagrid('getData'); 
			 if(data.rows.length==0){
				 alertMsg('数据列表为空，不能导出！', 'warning');
				 return ;
			 }
			showEditDialog("carAndCustomList", null, "datagrid_center_div",
					"开始导出", "导出配置", 0, _callbackExcept);
		}

		function _callbackExcept(parentId, fieldNames) {
			exportEsuyUIExcelFile(parentId, fieldNames, "销售毛利分析" + getSystemTime());
		}
		
		function _config(){

			var data =$('#carAndCustomList').datagrid('getData'); 
			 if(data.rows.length==0){
				 alertMsg('数据列表为空，不能打印！', 'warning');
				 return ;
			 }
			showEditDialog('carAndCustomList',personnelSumTable,'datagrid_center_div',"开始打印","打印配置",2,_print);

	}
	/**
	 * 打印字段设置回调函数
	 * 将选择的列打印
	 * @return
	 */
	function _print(parentId,fieldNames){
		printEsuyUIPreview(parentId,fieldNames);
	}
