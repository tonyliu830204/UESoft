//综合分析 车辆销售分析js文件
$(function (){
	//初始时间

	//初试时间
 	$('#recordDate').val(getStartDate($('#recordDate')));
	$('#recordDate2').val(getSystemTime());
	//初试时间
	$('#xsSellDate').val(getStartDate($('#xsSellDate')));
	$('#xsSellDate2').val(getSystemTime());
	
	//初试时间
	$('#xsDate').val(getStartDate($('#xsDate')));
	$('#rxsDate2').val(getSystemTime());
	
	//车辆进销存日报表
	$('#datagrid_purchaseandsale_dailysheet_id').treegrid({
		url : '',
		fit : true,
		fitColumns : true, 
		showFooter : true,
		rownumbers : true,
		idField : 'carBrand',
		treeField : 'carBrandName',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'carBrand',
			title : '品牌编号',
			width : 80,
			sortable : true,
			hidden : true
		},{
			field : 'carBrandName',
			title : '品牌',
			width : 80,
			sortable : true
		},{
			field : 'topMonthNum',
			title : '月初库存',
			width : 50,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return Number(rowData.topMonthNum);
		}
		},{
			field : 'thisImput',
			title : '本期入库',
			width : 50,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return Number(rowData.thisImput);
		}
		},{
			field : 'thisOutImput',
			title : '本期出库',
			width : 50,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return Number(rowData.thisOutImput);
		} 
		},{
			field : 'thisExitCar',
			title : '本期退车',
			width : 50,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return Number(rowData.thisExitCar);
		} 
		},{
			field : 'thisExitPart',
			title : '本期退厂',
			width : 50,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return Number(rowData.thisExitPart);
		}
		},{
			field : 'surplus',
			title : '结余',
			width : 50,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return Number(rowData.surplus);
		}
		}
		]],onLoadSuccess:function(row, data){
			//treegrid 刷新方法
	    	initTreeGridPager('datagrid_purchaseandsale_dailysheet_id', 'purchaseandsaleDailysheetAction!getDailysheetParent.action', true);
			},onBeforeExpand:function(rowData){
	 	//获取form表单时间
	 	var form =  $('#form_purchaseandsale_dailysheet_id').form();
		var formvalue = serializeObject(form);
		//动态设置展开查询的url
		if($('#recordDate').val()==""||$('#recordDate').val()==null){
			$.messager.alert('提示','请选择统计开始日期!','warning');
			//alertMsg('提示,请选择统计开始日期！', 'warning');
			return;
		}
		if($('#recordDate2').val()==""||$('#recordDate2').val()==null){
			$.messager.alert('提示','请选择统计结束日期！', 'warning');
			return;
		}
		var url = 'purchaseandsaleDailysheetAction!getDailysheetChild.action?carBrand='+rowData.carBrand+'&recordDate='+formvalue.recordDate;
		$('#datagrid_purchaseandsale_dailysheet_id').treegrid("options").url = url;  	
		return true;
		}
		
	});
	

	//车辆日销售，月销售初始化
	$('#datagrid_dOrM_assay_id').datagrid({
	url : 'dayReportAssayAction!doDayreportAssayColumns.action',
	type : 'POST',
	newrap : false,
	rownumbers : true,
	fit : true,
	width : 1024,
	height : 300,
	pageSize : 30,
	remoteSort : false,
	singleSelect : true,
	idField:'date',
	//sortOrder:'desc',	
	sortName : 'date',
//	frozenColumns : [[{field : 'date',title : ' ',width : 100,sortable : true, align : 'center'}]],
	onLoadSuccess : function(data){
	if(data.total == '0'){
			var body = $(this).data().datagrid.dc.body2;//暂无符合相关条件的信息！
			body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="6">暂无符合相关条件的信息,请确认查询条件是否正确！</td></tr>');
			}
		}
	});

	
		//初始化
		$('#datagrid_unite_assay_id').datagrid({
		url : 'dayReportAssayAction!doDayreportAssayColumns.action',
		type : 'POST',
		newrap : false,
		rownumbers : true,
		fit : true,
		width : 1024,
		height : 300,
		pageSize : 30,
		remoteSort : false,
		singleSelect : true,
		idField:'date',
		sortOrder:'desc',	
		sortName : 'date',
//		frozenColumns : [[ ]],
		onLoadSuccess : function(data){
		if(data.total == '0'){
				var body = $(this).data().datagrid.dc.body2;//暂无符合相关条件的信息！
				body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="6">暂无符合相关条件的信息,请确认查询条件是否正确！</td></tr>');
				}
			}
		});

		
		$('#carAndCustomList').datagrid({
			url : 'dayReportAssayAction!getCarAndCustom.action',
			pagination : true,
			fit:true,
			rownumbers : true,
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
				},{
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
				},{
					field : 'carBrandName',
					title : '品牌',
					width : 80,
					sortable : true,
				},{
					field : 'carModel',
					title : '型号',
					width : 75,
					hidden:true,
					sortable : true
				},{
					field : 'carModelName',
					title : '型号',
					width : 125,
					sortable : true
				},{
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
				},{
					field : 'modelSalesPrice',
					title : '销售价格',
					width : 90,
					sortable : true
				},{
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
					field : 'upData',
					title : '上报时间',
					width :100,
					sortable : true
				},{
					field : 'upPerson',
					title : '上报人',
					width : 120,
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
					field : 'xsCustomDiplomaN',
					title : '学历',
					width : 80,
					sortable : true
				}, {
					field : 'xsCustomIncomeN',
					title : '收入',
					width : 100,
					sortable : true,
				}, {
					field : 'xsCustomTradeN',
					title : '所在行业',
					width : 100,
					sortable : true
				}, { 
					field : 'xsCustomAreaN', 
			    	title : '地区', 
			    	width : 180, 
			    	sortable : true 
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
					field : 'xsCustomBirthday',
					title : '出生日期',
					width : 80,
					sortable : true
				},{
					field : 'xsCustomCompany',
					title : '所在单位',
					width : 110,
					sortable : true
				},{
					field : 'xsCustomAddress',
					title : '地址',
					width :110,
					sortable : true
				},{
					field : 'XsCustomZipcode',
					title : '邮编',
					width : 80,
					sortable : true
				},{
					field : 'xsCustomPropertyN',
					title : '客户性质',
					width : 80,
					sortable : true
				},{
					field : 'pact_code',
					title : '合同号',
					width : 90,
					sortable : true
				},{
					field : 'invoice_code',
					title : '开票号',
					width : 80,
					sortable : true
				},{
					field : 'xsDistributorId',
					title : '分销商',
					width : 80,
					hidden:true,
					sortable : true
				},{
					field : 'distributorName',
					title : '分销商',
					width : 130,
					sortable : true
				},{
					field : 'consult_actual_date',
					title : '最近回访',
					width : 80,
					sortable : true
				},{
					field : 'consultDegree',
					title : '满意度',
					width : 80,
					hidden:true,
					sortable : true
				},{
					field : 'onsultDegreeN',
					title : '满意度',
					width : 90,
					sortable : true
				}
		        ]]
		   });
		});
	
		var recordArray = new Array();
		var s = "";
		var ss = "";
		var options = {};
	
	//添加不固定列
	function addColome(resault,datagridId,aType){
		var str1 ="";
		var resaults = resault.substring(1,resault.indexOf("]")).toString();
		options = {};
		options.url = '';
		if(resaults !=null && '""'!=resaults && resaults.length>0){
		var array = resaults.split(",");
				str1 += "[[{field : 'date',title : '"+aType+"',width : 100,sortable : true, align : 'center'}";
				for ( var i = 0; i < array.length; i++) {
					var str = array[i].substring(1,array[i].length-1).replace(" ", "");
					str1 += ",{title : '"+str+"',field : '"+str+"',width : 100, align : 'center'} ";
				}
				str1 += ",{title : '汇总',field : 'TOTAL',width : 100, align : 'center',sortable : true}]] ";
				options.columns = eval(str1);
				datagridId.datagrid(options);
				return true;
			}else{
				options.columns = eval(str1);
				datagridId.datagrid(options);
				return false;
			}
		}
	
		//条件查询
		function doFindbyCondition(formid,url,type,datagridId,aType){
			var form =  formid.form();
			var formvalue = serializeObject(form);
			//同时访问 后台getName方法筛选出需要的列
				$.ajax({
				type : 'POST', 
				url : 'dayReportAssayAction!doDayreportAssayColumns.action?assayType='+type+'&columnsTag=true',//
				data : formvalue,
				success : function(rest){
						var t = addColome(rest,datagridId,aType);
						if(t){
							$.ajax({
							type : 'POST', 
							url : url,
							data : formvalue,
							dataType : 'json',
							success : function(resault){
								var object=JSON.parse(resault);
								datagridId.datagrid('loadData',object);
								datagridId.datagrid('reload');
								addInitDate($('#xsSellDate'),$('#xsSellDate2'));
							}
						});
					}
						addColome("",datagridId,"");
						datagridId.datagrid('loadData',{total:0,rows:[]});	
				}
			});
		}
		
		
		//条件查询
		function getTreeInfo(treegrid,formid,url){
			var form =  formid.form();
			var formvalue = serializeObject(form);
		
			if($('#recordDate').val()==""||$('#recordDate').val()==null){
				$.messager.alert('提示','请选择统计开始日期!','warning');
				//alertMsg('提示,请选择统计开始日期！', 'warning');
				return;
			}
			if($('#recordDate2').val()==""||$('#recordDate2').val()==null){
				$.messager.alert('提示','请选择统计结束日期！', 'warning');
				return;
			}
//			$.ajax({
//				   type: 'post',
//				   dataType: 'json',
//				   url: url,
//				   data: formvalue,
//				   success: function(r){
//					   if(r){
//						   //置空treegrid
//						   treegrid.treegrid('loadData',{rows : []});
//						   treegrid.treegrid('loadData',r);
//					   }
//				   }
//				});
			reLoadTreeGrid('datagrid_purchaseandsale_dailysheet_id', 'form_purchaseandsale_dailysheet_id', url, false, true);
		}
		//清空form表单
		function doFormClear(formId){
			formId.form('clear');
			$('#carAndCustomList').datagrid('unselectAll');
			$('#carAndCustomList').datagrid('load', serializeObject($('#form_carCu_id')));
			addInitDate($('#xsSellDate'),$('#xsSellDate2'));
			$('#car_Brand_id').combobox('reload');
			$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
		}
		
		var queryCarReserve = function() {

			$('#carAndCustomList').datagrid('unselectAll');
			$('#carAndCustomList').datagrid('load',serializeObject($('#form_carCu_id')));
			addInitDate($('#xsSellDate'),$('#xsSellDate2'));
		}
		
		function doExport(datagrid,datagridDiv,callbackExcept){
			alert(datagrid);
			var data =$('#'+datagrid+'').datagrid('getData'); 
			 if(data.rows.length==0){
				 alertMsg('数据列表为空，不能导出！', 'warning');
				 return ;
			 }
			showEditDialog(datagrid,null,datagridDiv,"开始导出","导出配置",0,callbackExcept);

		}

		function _callbackExceptOne(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"车辆日销售分析"+getSystemTime());
		}
		
		function _callbackExceptTwo(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"车辆月销售分析"+getSystemTime());
		}
		
	

		function _callbackExceptThree(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"车辆销售方式分析"+getSystemTime());
		}

		

		function _callbackExceptFour(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"业务员销售分析"+getSystemTime());
		}
	

		function _callbackExceptFive(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"销售部门分析"+getSystemTime());
		}
	

		function _callbackExceptSix(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"销售班组分析"+getSystemTime());
		}
		function doExportPurchase(datagrid,datagridDiv){
			alert(datagrid);
			var data =$('#'+datagrid+'').treegrid('getData'); 
			 if(data.length==0){
				 alertMsg('数据列表为空，不能导出！', 'warning');
				 return ;
			 }
			 showEditDialog(datagrid,null,datagridDiv,"开始导出","导出配置",0,getExport);
		}
		
		function getExport(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"车辆进销存报表"+getSystemTime());
	}
	
		function carAndcustomExport(){
			var data =$('#carAndCustomList').datagrid('getData'); 
			 if(data.rows.length==0){
				 alertMsg('数据列表为空，不能导出！', 'warning');
				 return ;
			 }
					showEditDialog("carAndCustomList",null,"carAndCustomList_div","开始导出","导出配置",0,_callbackExcept);
		
		}
		
		function _callbackExcept(parentId,fieldNames){
				exportEsuyUIExcelFile(parentId,fieldNames,"车辆客户档案"+getSystemTime());
		}
	
		function _config(datagrid,datagridDiv){
		
				var data =$('#'+datagrid+'').datagrid('getData'); 
				 if(data.rows.length==0){
					 alertMsg('数据列表为空，不能打印！', 'warning');
					 return ;
				 }
				showEditDialog(datagrid,personnelSumTable,datagridDiv,"开始打印","打印配置",2,_print);
	
		}
		function _configPurchase(datagrid,datagridDiv){
			
			var data =$('#'+datagrid+'').treegrid('getData'); 
			 if(data.length==0){
				 alertMsg('数据列表为空，不能打印！', 'warning');
				 return ;
			 }
			showEditDialog(datagrid,personnelSumTable,datagridDiv,"开始打印","打印配置",2,_print);

	}
		/**
		 * 打印字段设置回调函数
		 * 将选择的列打印
		 * @return
		 */
		function _print(parentId,fieldNames){
			printEsuyUIPreview(parentId,fieldNames);
		}
		
