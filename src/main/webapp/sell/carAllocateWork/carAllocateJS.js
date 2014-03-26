var reserveId;
$(function() {
	//初试时间
	addInitDate($('#reserveDete'),$('#reserveDete2'));
	// 订单表汇总
	$carAllocateList = $('#carAllocateList').datagrid(
	{
		url : 'sellCarReserveAction!getSellList.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'reserveCode',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'reserveId',
			title : '编号',
			width : 60,
			sortable : true,
			 hidden:true
		}, 
		 {
			field : 'reserveDete',
			title : '预定日期',
			width : 90,
			sortable : true
		},{
			field : 'reserveCode',
			title : '预定单号',
			width : 140,
			sortable : true
		}, {
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true
		},
		 {
			field : 'customId',
			title : '客户id',
			width : 70,
			sortable : true,
			hidden:true
		},{
			field : 'stfName',
			title : '业务员',
			width : 135,
			sortable : true
		},
		{
			field : 'stfId',
			title : '业务员id',
			width : 70,
			sortable : true,
			hidden:true
		},{
			field : 'carBrandName',
			title : '车辆品牌',
			width : 80,
			sortable : true
		},{
			field : 'carBrand',
			title : '车辆品牌id',
			width : 70,
			hidden:true,
			sortable : true
		},{
			field : 'carModelName',
			title : '车辆型号',
			width : 130,
			sortable : true
		},
		 {
			field : 'carModel',
			title : '车辆型号id',
			width : 80,
			hidden:true,
			sortable : true
		 }, {
			field : 'carColorName',
			title : '颜色',
			width : 80,
			sortable : true
		}, 
		{
			field : 'carColor',
			title : '颜色id',
			width : 70,
			hidden:true,
			sortable : true
		},{
			field : 'vinCode',
			title : 'VIN编号',
			width : 130,
			sortable : true
		}, {
			field : 'predictTakeDate',
			title : '预交日期',
			width : 85,
			sortable : true
		}, {
			field : 'paymentMoney',
			title : '预付金额',
			width : 90,
			sortable : true,
			hidden:true
		}, {
			field : 'decorateMoney',
			title : '销售价格',
			width : 90,
			sortable : true
		}, {
			field : 'level',
			title : '等级id',
			width : 50,
			sortable : true,
			hidden:true
		},{
			field : 'levelName',
			title : '等级',
			width : 90,
			sortable : true
		},{
			field : 'remark',
			title : '备注',
			width : 180,
			sortable : true
		} ,
		{
			field : 'carId',
			title : '车档案编号',
			width : 180,
			sortable : true,
			hidden:true
		}] ],
		onDblClickRow : function(rowIndex, rowData) {
		    $('#tt').tabs('select','车辆档案信息');
			var url = "carBarnInfoAction!getCarList.action";
			  $('#Car_f').form('clear'); 
			$('#Car_f').form('load', rowData);
			$('#Cars').form('clear');
			reserveId=rowData.reserveId;
			var carListFrom = serializeObject($('#Car_f'));
			$('#carList').datagrid("options").url = url;
			$('#carList').datagrid('load',carListFrom);
		}
	});
	
	
	$('#carList').datagrid( {
		pagination : true,
		fit : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'carId',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'carId',
			title : '编号',
			width : 50,
			sortable : true,
			hidden:true

		},{
			field : 'carCode',
			title : '车辆编号',
			width : 120,
			sortable : true
		}, {
			field : 'carVinNumber',
			title : 'VIN编码',
			width : 140,
			sortable : true
		}, {
			field : 'carBrandName',
			width : 70,
			title : '品牌',
			sortable : true

		}, {
			field : 'carBrand',
			width : 70,
			title : '品牌id',
			sortable : true,
			hidden:true

		}, {
			field : 'carModelName',
			title : '类型',
			width : 150,
			sortable : true

		}, {
			field : 'carModel',
			width : 70,
			title : '类型id',
			sortable : true,
			hidden:true

		}, {
			field : 'carColorName',
			width : 70,
			title : '颜色',
			sortable : true

		}, {
			field : 'carColor',
			width : 70,
			title : '颜色id',
			sortable : true,
			hidden:true

		}, {
			field : 'carOcn',
			title : 'OCN码',
			width : 100,
			sortable : true

		}, {
			field : 'carSellStateName',
			width : 70,
			title : '销售状态',
			sortable : true

		},  
		 {
			field : 'carInstorageAge',
			width : 90,
			title : '车辆库龄',
			sortable : true
		},{
			field : 'carMotorNumber',
			title : '发动机号',
			width : 120,
			sortable : true
		}, {
			field : 'carLicenseName',
			title : '厂牌名称',
			width : 120,
			sortable : true
		},{
			field : 'carProductionAddress',
			width : 110,
			title : '代交、展出/存放地',
			sortable : true
		},
		{
			field : 'carCopyData',
			title : 'Call Off日期',
			width : 140,
			sortable : true
		},  {
			field : 'modelCostPrice',
			title : '成本价',
			width : 90,
			sortable : true
		},{
			field : 'modelSalesPrice',
			title : '销售价',
			width : 90,
			sortable : true
		}
		
		] ],
		onClickRow : function(rowIndex, rowData) {		
			$('#Cars').form('load', rowData);
			  }
	});
	
});


// 查询
var queryCarReserve = function() {

	$('#carAllocateList').datagrid('unselectAll');
	$('#carAllocateList').datagrid(
			{
				url : 'sellCarReserveAction!getSellList.action'
			})
	$('#carAllocateList').datagrid('load',serializeObject($('#CarReserveForm')));
	//初试时间
	addInitDate($('#reserveDete'),$('#reserveDete2'));
}
// 清空按钮
function _clear() {
	$('#CarReserveForm').find('input').val('');
	$('#CarReserveForm').find('select').val('');
	$('#carAllocateList').datagrid('unselectAll');
	$('#carAllocateList').datagrid(
			{
				url : 'sellCarReserveAction!getSellList.action'
			})
	
	//初试时间
	addInitDate($('#reserveDete'),$('#reserveDete2'));

	$('#Cars').form('clear');
	$('#carList').datagrid('unselectAll');
	$('#carList').datagrid('load', serializeObject($('#Cars')));
	$('#car_Brand_id').combobox('reload');
	$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
	$('#queryBrand').combobox('reload');
	$('#queryModel').combobox('reload','carModelAction!findAllCarModel.action');
	
}
// 定位查找
function queryCar(){
	
	$('#carList').datagrid('load', serializeObject($('#Cars')) );
/*$.ajax( { 
	type : 'POST',
	url : 'carBarnInfoAction!getCarList.action',
	success : function(r) {
	if (r) {
		$('#carList').datagrid(
		 {	url : 'carBarnInfoAction!getCarList.action',pagination : true});
	} else {
		$.messager.alert('提示',
				'数据提交失败!', 'warning');
	}
}
});*/
}
// 预交期提醒

function expireWarn(){
	$('#CarReserveForm').form('clear');
	$.ajax( { 
		type : 'POST',
		url : 'sellCarReserveAction!getCarReserveExpireList.action',
		success : function(r) {
		if (r) {
			$('#carAllocateList')
					.datagrid(
							{
								url : 'sellCarReserveAction!getCarReserveExpireList.action',
								pagination : true
							});
		} else {
			$.messager.alert('提示',
					'数据提交失败!', 'warning');
		}
	}
	});
	}

//车辆分配
function distribution(i){
	var data = $('#carList').datagrid('getSelected');
	
	
	if(data){
		var vin=$('#vinCode').val();
		if(vin!=null&&vin!=''){
				alertMsg('该预订单已分配车辆，不能再次分配！', 'warning');
				return;
			}
	
		$.messager.confirm('优亿软件提示', '请确认是否执行此操作？', function(r){
			if (r){
				$('#Car_f').form('load', data);
				$('#vinCode').val(data.carVinNumber);
				$('#carid').val(data.carId);
			
				
	$.ajax({
		   url:'${pageContext.request.contextPath}/sellCarReserveAction!modifyReverd.action?vinNum='+i,
		   type: 'post',
		   dataType: 'json',
		   data:serializeObject($('#Car_f')),
		   success: function(r){
		   if(r.success){	
			   alertMsg('车辆分配成功！', 'info');
				 $('#carAllocateList').datagrid('load');
				 $('#tt').tabs('select','客户订单信息');
		   }else{
					$.messager.alert('提示','数据提交失败!','warning');
			     }
		   },
		   error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }		   
	})
			}
		});
	}else{
		alertMsg('对不起，请先选中要分配的车辆信息！', 'warning');
	}		
}

//取消分配
function cancledistribution(i){
	var data = $('#carAllocateList').datagrid('getSelected');
	
	  if(data){
		  var vin=data.vinCode;
			if(vin==null||vin==''){		
					alertMsg('该预订单未分配车辆，不能取消分配！', 'warning');
					return;			  
		  }
		  
	    	 $.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'sellCarReserveAction!isUseDocument.action',
				   data:data,
				   success: function(r){
					 if(r.success){
						 if(r.obj==true){
							 alertMsg('对不起，此预订单已经存在于销售单中，不允许取消分配！', 'warning'); 
						 }else{
	
					$.messager.confirm('优亿软件提示', '请确认是否执行此操作？', function(r){
							if (r){
						$.ajax({
						   url:'${pageContext.request.contextPath}/sellCarReserveAction!modifyReverd.action?vinNum='+i,
						   type: 'post',
						   dataType: 'json',
						   data:serializeObject($('#Car_f')),
						   success: function(r){
						   if(r.success){
							   alertMsg('取消车辆分配操作成功！', 'info');
							     $('#carAllocateList').datagrid('load');
								 $('#tt').tabs('select','客户订单信息');
						   }else{
									$.messager.alert('提示','数据提交失败!','warning');
							     }
						   },
						   error : function (r){
							   if(r.readyState == '0' && r.status == '0'){
								   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
							   }
						   }
						});
							}
					});
						 }
					 }else{
						 alertMsg(r);
					 }
				   },
				   error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
			});
	     }else
			 $.messager.alert('优亿软件提示','对不起，请先选中要操作的记录！','warning',function(){});
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
		exportEsuyUIExcelFile(parentId,fieldNames,"车辆调配作业"+getSystemTime());
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
