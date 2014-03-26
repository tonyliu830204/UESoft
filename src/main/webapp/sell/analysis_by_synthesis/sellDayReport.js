
$(function (){
	//初试时间
	$('#xsSellDate').val(getStartDate($('#xsSellDate')));
	$('#xsSellDate2').val(getSystemTime());
		$('#carAndCustomList').datagrid({
			url : 'dayReportAssayAction!getSellDayReport.action',
			pagination : true,
			fit:true,
			sortOrder:'asc',
		    sortName:'sellDate',
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			frozenColumns:[[
			                {
			                	field : 'carId',
			    				title : '编号',
			    				width : 50,
			    				hidden:true,
			    				sortable : true
			    			},{
			    				field : 'sellDate',
			    				title : '销售日期',
			    				width : 120,
			    				sortable : true
			    			}, {
			    				field : 'carVinNumber',
			         			title : 'VIN码',
			    				width : 120,
			    				sortable : true
			    			}
			                   ]],
			               
							columns : [ [ 
							{
								field : 'carBrand',
								title : '品牌',
								width : 80,
								hidden:true,
								sortable : true,
							}, {
								field : 'carBrandName',
								title : '品牌',
								width : 80,
								sortable : true,
							}, {
								field : 'carModel',
								title : '型号',
								width : 75,
								hidden:true,
								sortable : true
							}, {
								field : 'carModelName',
								title : '型号',
								width : 125,
								sortable : true
							}, {
								field : 'carColor',
								title : '颜色',
								width : 70,
								hidden:true,
								sortable : true
							},{
								field : 'carColorName',
								title : '颜色',
								width : 80,
								sortable : true
							},
							    {
								field : 'carOcn',
								title : 'OCN码',
								width : 120,
								sortable : true
							},  {
								field : 'carMotorNumber',
								title : '发动机',
								width : 90,
								sortable : true
							},{
								field : 'stfName',
								title : '销售顾问',
								width : 130,
								sortable : true
							},{
						    	field : 'customName',
								title : '客户姓名',
								width : 90,
								sortable : true
						   
						    }, {
								field : 'customSex',
								title : '性别',
								width : 65,
								sortable : true,
							}, {
								field : 'xsCustomPhone',
								title : '电话',
								width : 90,
								sortable : true
							},{
								field : 'xsCustomTelephone',
								title : '手机',
								width : 100,
								sortable : true
							},{
								field : 'xs_culstom_credentials',
								title : '身份证号',
								width : 80,
								sortable : true
							},{
								field : 'xsCustomBirthday',
								title : '出生日期',
								width : 80,
								sortable : true
							},{
								field : 'xsCustomAddress',
								title : '地址',
								width :110,
								sortable : true
							},{
								field : 'xs_model_costPrice',
								title : '成本价',
								width : 90,
								sortable : true
							}, {
								field : 'modelSalesPrice',
								title : '销售价格',
								width : 90,
								sortable : true
							},{
								field : 'xs_car_price',
								title : '市场价',
								width : 90,
								sortable : true
							},{
								field : 'decorate_amount',
								title : '装潢成本',
								width : 90,
								sortable : true
							},{
								field : 'decorate_sell',
								title : '装潢销售',
								width : 90,
								sortable : true
							},{
								field : 'cost_price',
								title : '代办成本',
								width : 90,
								sortable : true
							},{
								field : 'db_project_cost',
								title : '代办销售',
								width : 90,
								sortable : true
							},{
								field : 'prime_cost',
								title : '保险成本',
								width : 90,
								sortable : true
							},{
								field : 's_sum',
								title : '保险金额',
								width : 90,
								sortable : true
							},{
								field : 'invoice_parce',
								title : '开票金额',
								width : 90,
								sortable : true
							},{
								field : 'pact_code',
								title : '合同号',
								width : 90,
								sortable : true
							},{
								field : 'invoice_number',
								title : '发票号',
								width : 80,
								sortable : true
							},{
								field : 'xs_car_certificate',
								title : '合格证',
								width : 80,
								sortable : true
							},{
								field : 'state',
								title : '合格证状态',
								width : 80,
								sortable : true
							},{
								field : 'xs_car_make_data',
								title : '生产日期',
								width : 90,
								sortable : true
							},{
								field : 'instorehouse_date',
								title : '入库日期',
								width : 90,
								sortable : true
							},{
								field : 'check_comtent',
								title : 'PDI检测内容',
								width : 90,
								sortable : true
							}
						    ]],rowStyler:function(row,rowIndex){
							if (row.sellDate=="汇总"){
								
								return 'background-color:#c0d8d8;';
							}
		}
	   });
	});
	
		
	//清空form表单
		function doFormClear(){
			$('#form_list_id').form('clear');
			$('#carAndCustomList').datagrid('unselectAll');
			$('#carAndCustomList').datagrid('load', serializeObject($('#form_list_id')));
			addInitDate($('#xsSellDate'),$('#xsSellDate2'));
			$('#car_Brand_id').combobox('reload');
			$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
	}
		//查询
		var queryCarReserve = function() {

			$('#carAndCustomList').datagrid('unselectAll');
			$('#carAndCustomList').datagrid('load',serializeObject($('#form_list_id')));
			addInitDate($('#xsSellDate'),$('#xsSellDate2'));
			
		}
		/**
		 * 隐藏列
		 * 将选的列因此
		 * @return
		 */
		function _hideColumn(){
			showEditDialog("carAndCustomList",null,"datagrid_center_div","隐藏列","隐藏列配置",3,null);
		}
		
		
		
		//导出
		function doexcept(dateGridId,parentId){
			var data =  $('#'+dateGridId+'').datagrid('getData'); 
			 if(data.rows.length==0){
				 alertMsg('数据列表为空，不能导出！', 'warning');
				 return ;
			 }
			showEditDialog(dateGridId,null,parentId,"开始导出","导出配置",0,_callbackExcept);
		}
		function _callbackExcept(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"销售日报表"+getSystemTime());
	}
		
		//打印
		function dopoint(dateGridId,parentId){
			var data =  $('#'+dateGridId+'').datagrid('getData'); 
			 if(data.rows.length==0){
				 alertMsg('数据列表为空，不能打印！', 'warning');
				 return ;
			 }
			showEditDialog(dateGridId,null,parentId,"开始打印","打印配置",2,_print);
		}
		function _print(parentId,fieldNames){
			printEsuyUIPreview(parentId,fieldNames);
		}
		
		