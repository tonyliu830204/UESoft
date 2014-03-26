$(function() {
	$('#zhProjectDate2').val(getSystemTime());
	 getStartDate($('#zhProjectDate1'));
	$('#account').datagrid( {
		url : 'instoreHouseQueryAction!getInstorehouseList.action',
		pagination : true,
		fit:true,
		sortOrder:'desc',
	    sortName:'instorehouseDate',  
		singleSelect : true,
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		rownumbers : true,
		loadMsg : "数据加载中，请稍后……",
		frozenColumns:[[
			{
				title : '入库日期',
				field : 'instorehouseDate',
				width : 70,
				sortable : true
			}, 
			{
				field : 'carInstorageAge',
				title : '库龄',
				width : 60,
				sortable : true
			
			}, 
			{
				field : 'carBrand',
				title : '品牌',
				width : 80,
				hidden:true,
				sortable : true
				
			},
			{
				field : 'carBrandName',
				title : '品牌',
				width : 80,
				sortable : true,
				hidden:true
				
			},
			{
				field : 'carModelName',
				title : '类型',
				width : 130,
				sortable : true
				
			
			},{
				field : 'carModelId',
				title : '类型',
				width : 70,
				hidden:true,
				sortable : true
			
			}, {
				field : 'carColor',
				title : '外观颜色',
				width : 100,
				hidden:true,
				sortable : true
			},{
				field : 'carColorName',
				title : '外观颜色',
				width : 80,
				sortable : true
			},  {
				field : 'carInteriorColorName',
				title : '内饰色',
				width : 80,
				sortable : true
			},{
				field : 'carInteriorColor',
				title : '内饰色',
				width : 100,
				hidden:true,
				sortable : true
			}, {
				field : 'carVinNumber',
				title : 'VIN码',
				width : 130,
				sortable : true
			},{
				field : 'carSellState',
				title : '销售状态',
				width : 80,
				hidden:true
			},{
				field : 'carSellStateN',
				title : '销售状态',
				width : 80,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				if(value=="客户预订"){
					return '<font color="red">'+value+'</font>';
				}else{
					return value;
				}
			}
				
			}, {
				field : 'carCode',
				title : '车辆编号',
				width : 125,
				sortable : true
			}]],
			columns : [ [
			             {
				field : 'xsModelCostPrice',
				title : '成本价',
				width : 80,
				sortable : true
			} ,{
				field : 'xsModelSalesPrice',
				title : '销售价',
				width : 80,
				sortable : true
			}, {
				field : 'carOcn',
				title : 'OCN码',
				width : 100,
				sortable : true
				
			},{
				field : 'carMotorNumber',
				title : '发动机号',
				width : 80,
				sortable : true
			}, {
				field : 'carCertificateState',
				title : '合格证状态',
				width : 80,
				hidden:true,
				sortable : true
			},{
				field : 'carCertificateStateN',
				title : '合格证状态',
				width : 80,
				sortable : true
			},{
				field : 'carCertificate',
				title : '合格证号',
				width : 80,
				sortable : true
			}, {
				field : 'distributorId',
				title : '分销商',
				width : 180,
				hidden:true,
				sortable : true
			},
			 {
				field : 'distributorName',
				title : '分销商',
				width : 180,
				sortable : true
			}, {
				field : 'customId',
				title : '预定客户',
				width : 80,
				hidden:true,
				sortable : true
			}, {
				field : 'customName',
				title : '预定客户',
				width : 80,
				sortable : true
			}, {
				field : 'carAddress',
				title : '代交寄存车',
				width : 150,
				sortable : true
			}, {
				field : 'warehouse',
				title : '仓库',
				width : 180,
				hidden:true,
				sortable : true
			}, {
				field : 'warehouseN',
				title : '仓库',
				width : 80,
				sortable : true
			}, {
				field : 'carUnlockingKeyNumber',
				title : '钥匙号',
				width : 100,
				sortable : true
			},{
				field : 'reserveDete',
				title : '订车日期',
				width : 70,
				sortable : true
			},{
				field : 'reserveCode',
				title : '订单号',
				width : 110,
				sortable : true
			},{
				field : 'xsSupplierName',
				title : '供应商',
				width : 100,
				sortable : true
			},{
				field : 'xsSupplierId',
				title : '供应商',
				width : 180,
				hidden:true,
				sortable : true
			},
			{
				field : 'carMakeData',
				title : '生产日期',
				width : 70,
				sortable : true
			}, {
				field : 'carAssembleData',
				title : '配车日期',
				width : 70,
				sortable : true
			}
			, {
				field : 'carForecastData',
				title : '预计下线日期',
				width : 80,
				sortable : true
			}, {
				field : 'carEndCheckData',
				title : '终检日期',
				width : 80,
				sortable : true
			}, {
				field : 'carLicenseName',
				title : '厂牌名称',
				width : 80,
				sortable : true
			}, {
				field : 'carPdsData',
				title : 'PDS日期',
				width : 70,
				sortable : true
			}, {
				field : 'carPdsPerson',
				title : 'PDS检验员',
				width : 110,
				hidden:true,
				sortable : true
			}, 
			{
				field : 'pdsPerson',
				title : 'PDS检验员',
				width : 110,
				sortable : true
			},{
				field : 'carPdsResult',
				title : 'PDS结果',
				width : 130,
				sortable : true
			}, {
				field : 'carTradeCheckBill',
				title : '商检单号',
				width : 120,
				sortable : true
			}
			
			] ]
	 });
	querySum(null);
	
});
function querySum( from ){
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'${pageContext.request.contextPath}/instoreHouseQueryAction!getInstorehouseSum.action',   
		   data:from,
		   success: function(r){
			 if(r.success){
				 var o=r.obj;
				 var insotreNum;
				 var instorePrice;
				 var instoreSale;
				 for(var i=0;i<o.length;i++){
					 insotreNum=o[i].num;
					 instorePrice=o[i].costPrice;
					 instoreSale= o[i].salesPrice;
				 }
				 $("#num").val(insotreNum);
				 $("#costPrice").val(instorePrice);
				 $("#salesPrice").val(instoreSale);
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
	
	
}


//查询
function queryReserve() {
	$('#account').datagrid('unselectAll');
	$('#account').datagrid('load',serializeObject($('#queryForm')));
	querySum(serializeObject($('#queryForm')));
	 addInitDate($('#zhProjectDate1'),$('#zhProjectDate2'));	

}
//清空
function clearSearchCondition(){
	$('#queryForm').find('input').val('');
	$('#queryForm').find('select').val('');
	$('#account').datagrid('unselectAll');
	$('#account').datagrid('load', serializeObject($('#queryForm')));
	$('#account').datagrid(
			{
				url : 'instoreHouseQueryAction!getInstorehouseList.action'
			})
	
	 addInitDate($('#zhProjectDate1'),$('#zhProjectDate2'));	
	$('#car_Brand_id').combobox('reload');
	$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
}
function queryCarAge(){
	
	$.ajax( { 
		type : 'POST',
		url : 'instoreHouseQueryAction!getInstorehouseList.action?carAge=true',
		success : function(r) {		
			if (r) {
			$('#account')
					.datagrid({
								url : 'instoreHouseQueryAction!getInstorehouseList.action?carAge=true',
								pagination : true,
								});
			$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url:'${pageContext.request.contextPath}/instoreHouseQueryAction!getInstorehouseSum.action?carAge=true',   
				   data:serializeObject($('#queryForm')),
				   success: function(r){
					 if(r.success){
						 var o=r.obj;
						 var insotreNum;
						 var instorePrice;
						 var instoreSale;
						 for(var i=0;i<o.length;i++){
							 insotreNum=o[i].num;
							 instorePrice=o[i].costPrice;
							 instoreSale= o[i].salesPrice;
						 }
						 $("#num").val(insotreNum);
						 $("#costPrice").val(instorePrice);
						 $("#salesPrice").val(instoreSale);
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
			} else {
			$.messager.alert('提示',
					'数据提交失败!', 'warning');
		}
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
	var data =  $("#account").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
		showEditDialog("account",null,"acc","开始导出","导出配置",0,_callbackExcept);
		
	
	
	
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
	exportEsuyUIExcelFile(parentId,fieldNames,"库存量查询"+getSystemTime());
}

/**
 * 打印字段设置
 * 编辑、选择不同分组
 * @return
 */
function _config(){
	
	var data =  $("#account").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }

		showEditDialog("account",personnelSumTable,"acc","开始打印","打印配置",2,_print);
		

		
	
}
/**
 * 打印字段设置回调函数
 * 将选择的列打印
 * @return
 */
function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}