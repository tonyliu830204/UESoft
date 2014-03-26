//综合分析 潜在客户分析js文件
$(function() {

	// 初时间
	$('#xsCustomInputdate2').val(getSystemTime());
	getStartDate($('#xsCustomInputdate'));

	// 成交障碍
	$('#datagrid_bargain_trouble_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getPotentialCustomerAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers:true,
		idField : 'obstacle',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'obstacle',
			title : '成交障碍',
			width : 50,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比%',
			width : 50,
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				return parseFloat(rowData.percentage);
			}
		} ] ]

	});
	// 成交几率
	$('#datagrid_bargain_probability_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getBargainonAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'buyProbability',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'buyProbability',
			title : '成交几率(%)',
			width : 50,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比%',
			width : 50,
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				return parseFloat(rowData.percentage);
			}
		} ] ]
	});

	// 潜在客户来源
	$('#datagrid_custom_source_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getCustomSourceAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'xsCustomSource',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'xsCustomSource',
			title : '来源',
			width : 50,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比%',
			width : 50,
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				return parseFloat(rowData.percentage);
			}
		} ] ]

	});
	// 跟踪部门分析
	$('#datagrid_dept_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getDeptAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'deptName',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'deptName',
			title : '部门',
			width : 50,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比%',
			width : 50,
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				return parseFloat(rowData.percentage);
			}
		} ] ]

	});
	// 跟踪班组分析
	$('#datagrid_sell_team_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getSellTeamAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'sellTeam',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'sellTeam',
			title : '班组',
			width : 50,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比%',
			width : 50,
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				return parseFloat(rowData.percentage);
			}
		} ] ]

	});
	// 跟踪业务员分析
	$('#datagrid_stfname_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getStfNameAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'stfName',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'stfName',
			title : '业务员',
			width : 50,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比(%)',
			width : 50,
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				return parseFloat(rowData.percentage);
			}
		} ] ]

	});
	// 潜在客户等级分析
	$('#datagrid_customer_level_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getCustomerLevelAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'customerLevel',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'customerLevel',
			title : '等级',
			width : 50,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比(%)',
			width : 50,
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				return parseFloat(rowData.percentage);
			}
		} ] ]

	});
	// 购买车型号分析
	$('#datagrid_buy_carmodel_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getBuyCarModelAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'carModel',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'carModel',
			title : '车辆型号',
			width : 100,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比(%)',
			width : 50,
			sortable : true
		} ] ]

	});
	// 战败原因分析
	$('#datagrid_lose_bell_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getLoseBellAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'loseBellReason',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'loseBellReason',
			title : '战败原因',
			width : 100,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比(%)',
			width : 50,
			sortable : true
		} ] ]

	});
	// 战败车型分析
	$('#datagrid_lose_bell_carmodel_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getLoseBellCarModelAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'carModel',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'carModel',
			title : '车型',
			width : 100,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比(%)',
			width : 50,
			sortable : true
		} ] ]

	});
	// 战败部门分析
	$('#datagrid_lose_bell_depart_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getLoseBellDepartAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'deptName',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'deptName',
			title : '部门',
			width : 100,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比(%)',
			width : 50,
			sortable : true
		} ] ]

	});
	// 战败班组分析
	$('#datagrid_lose_bell_group_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getLoseBellGroupAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'sellTeam',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'sellTeam',
			title : '班组',
			width : 100,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比(%)',
			width : 50,
			sortable : true
		} ] ]

	});
	// 战败业务分析
	$('#datagrid_lose_bell_work_assay_id').datagrid( {
		url : 'potentialcustomerAssayAction!getLoseBellWorkAssay.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : 'stfName',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'stfName',
			title : '业务员',
			width : 100,
			sortable : true
		}, {
			field : 'amount',
			title : '数量',
			width : 50,
			sortable : true
		}, {
			field : 'percentage',
			title : '百分比(%)',
			width : 50,
			sortable : true
		} ] ]

	});
	
	// 战败客户列表
	$('#datagrid_losed_customer_record_id').datagrid({
			url : '',
			type : 'POST',
			fit : true,
			pagination : true,
			newrap : false,
			rownumbers : true,
			remoteSort : false,
			singleSelect : true,
			pageSize : 20,
			pageList : [ 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'xsCustomName',
			loadMsg : "数据加载中，请稍后……",
			onLoadSuccess : function(data) {
				if (data.total == '0') {
					var body = $(this).data().datagrid.dc.body2;// 暂无符合相关条件的信息！
					body.find('table tbody').append(
							'<tr><td width="' + body.width() + '" style="height: 25px; text-align: center;" colspan="6">暂无符合相关条件的信息！</td></tr>');
				}
			}
		});

		});

// 添加不固定列
function addDynamicColumn(resault) {
console.info(resault);
	var str1 = "[[" +
			"  {field : 'xsCustomName',title : '客户名称',width : 100,align : 'center',sortable : true}" +
			", {field : 'xsContactsPhone',title : '固话',	width : 100,align : 'center',sortable : true}" +
			", {field : 'xsCustomTelephone',title : '手机',width : 100,align : 'center',sortable : true}" +
			", {field : 'xsCustomAddress',title : '地址',	width : 100,align : 'center',sortable : true}" +
			", {field : 'stfName',title : '业务员',width : 100,align : 'center',sortable : true}" +
			", {field : 'xsCustomInputdate',title : '建档日期',width : 100,align : 'center',sortable : true}" +
			", {field : 'loseDate',	title : '流失日期',width : 130,align : 'center',sortable : true}" +
			", {field : 'carModel',title : '预购车型',width : 150,align : 'center',sortable : true}";
	if (resault != null &&resault!="" && resault.length > 0) {
		var array = resault.substring(1, resault.indexOf("]")).split(",");
		for ( var i = 0; i < array.length; i++) {
			var str = array[i].substring(1, array[i].length - 1).replace(" ","");
			str1 +=  ",{title : '"+str+"',field : '"+str+"',width : 100, align : 'center'} ";
		}
	}
	var laststr = str1 + "]]";
	 options = {};
	 options.url = '';
	 options.columns = eval(laststr);
	$('#datagrid_losed_customer_record_id').datagrid(options);
	return true;
}

// 战败客户类表条件查询
function doFindforRecord(formid) {
	var form = formid.form();
	var formvalue = serializeObject(form);
	// 同时访问 后台getName方法筛选出需要的列
	$.ajax( {
			type : 'POST',
			url : 'potentialcustomerAssayAction!getLosedCustomerColumn.action?flag=true',//
			data : formvalue,
			success : function(rest) {
				var t = addDynamicColumn(rest);
				if (t) {
					$.ajax( {
						type : 'POST',
						url : 'potentialcustomerAssayAction!getLosedCustomerRecord.action',
						data : formvalue,
						dataType : 'json',
						success : function(resault) {
							var object = JSON.parse(resault);
							$('#datagrid_losed_customer_record_id')
									.datagrid('loadData', object);
							$('#datagrid_losed_customer_record_id')
									.datagrid('reload');
							addInitDate($('#xsCustomInputdate'),$('#xsCustomInputdate2'));
						}
					});
				}
			}
		});
	}

// 条件查询
function doFindbyCondition(datagrid, formid, url, imgid) {
	var form = formid.form();
	var formvalue = serializeObject(form);
	datagrid.datagrid('loadData', {
		"rows" : [],
		"total" : 0
	});
	datagrid.datagrid('load', serializeObject(formid));
	// 获取jfreemchart
	var jfreemchartid = document.getElementById(imgid);
	jfreemchartid.setAttribute('src',  url + '?stfId='+ formvalue.stfId
			+ '&xsCustomInputdate=' + formvalue.xsCustomInputdate
			+ '&xsCustomInputdate2=' + formvalue.xsCustomInputdate2
			+ '&loseDate=' + formvalue.loseDate
			+ '&loseDate2=' + formvalue.loseDate2);
	addInitDate($('#xsCustomInputdate'),$('#xsCustomInputdate2'));
	}
// 清空form表单
function doFormClear(datagrid, formid, url, imgid) {
	formid.form('clear');
	var form = formid.form();
	var formvalue = serializeObject(form);
	datagrid.datagrid('load',  serializeObject(formid));
	// 获取jfreemchart
	if(imgid!=null&&imgid!=''&&imgid!=undefined){
	var jfreemchartid = document.getElementById(imgid);
	jfreemchartid.setAttribute('src', url + '?stfId=' + formvalue.stfId
			+ '&xsCustomInputdate=' + formvalue.xsCustomInputdate+ 
			'&loseDate=' + formvalue.loseDate );
	}
	addInitDate($('#xsCustomInputdate'),$('#xsCustomInputdate2'));
	//alert(imgid);
}

function doExport(datagrid,datagridDiv,except) {
	var data =$('#'+datagrid+'').datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
	showEditDialog(datagrid, null,datagridDiv	, "开始导出", "导出配置", 0,	except);
}

function _callbackExcept(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "成交障碍分析" + getSystemTime());
}



function _callbackExceptTwo(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "成交几率分析" + getSystemTime());
}


function _callbackExceptThree(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "潜在客户来源" + getSystemTime());
}


function _callbackExceptFour(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "跟踪部门分析" + getSystemTime());
}


function _callbackExceptFive(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "跟踪班组分析" + getSystemTime());
}


function _callbackExceptSix(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "客户等级分析" + getSystemTime());
}


function _callbackExceptSeven(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "购买车型号分析" + getSystemTime());
}
function docallbackExcept(parentId, fieldNames){
exportEsuyUIExcelFile(parentId, fieldNames, "跟踪业务员分析" + getSystemTime());
}

function _callbackExceptEight(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "战败客户列表" + getSystemTime());
}

function _callbackExceptNine(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "战败原因分析" + getSystemTime());
}

function _callbackExceptTen(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "战败车型分析" + getSystemTime());
}


function _callbackExceptEleven(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "战败部门分析" + getSystemTime());
}


function _callbackExceptTwelve(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "战败班组分析" + getSystemTime());
}


function _callbackExceptThirteen(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "战败业务分析" + getSystemTime());
}


function _config(datagrid,datagridDiv){

		var data =$('#'+datagrid+'').datagrid('getData'); 
		 if(data.rows.length==0){
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

