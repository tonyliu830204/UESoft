var stf;
var tbtitle;
var foreTag=false;
$(function(){
	$('#servicingNextdate2').val(getSystemTime());
	 getStartDate($('#servicingNextdate'));
	stf=$('#stf').val();
	//页面初始化时，设置按钮的状态
	$('#car_supplierId').combobox({  
	        required:true,
	        missingMessage:'供应商为必选项！'
	});
	$('#warehouse').combobox({  
	        required:true,
	        missingMessage:'仓库为必选项！'
	});
	
	$('#tt').tabs({   
		onSelect:function(title){  
		 tbtitle = title;
			if(title=='入库单汇总'){
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					enableBtn();
				}
			}else if(title=='入库单明细'){
				//disableBtn();
				//enableBtn();
			}
	    }   
	});
			//入库单汇总
			$('#ForeordainTable').datagrid({
			url:'${pageContext.request.contextPath}/instorehouseAction!getPager.action',
			fit:true,
			pagination : true,
			fitColumns : true,
			sortOrder:'asc',
		    sortName:'instorehouseCode',
			singleSelect : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			 columns : [ [{
					title : '编号',
					field : 'instorehouseId',
					hidden:true
				},{
					title : '入库单号',
					field : 'instorehouseCode',
					width : 120,
					sortable : true
				}, {
					title : '入库日期',
					field : 'instorehouseDate',
					width : 110,
					sortable : true
				}, {
					title : '发票类型',
					field : 'invoiceType',
					width : 50,
					hidden:true
				}, {
					title : '发票类型名称',
					field : 'invoiceName',
					width : 50,
					hidden:true
				},{
					title : '未税额',
					field : 'totalNotax',
					width : 50,
					sortable : true
				}, {
					title : '含税额',
					field : 'totalTax',
					width : 50,
					sortable : true
				}, {
					title : '台数',
					field : 'number',
					width : 40,
					sortable : true
				},{
					title : '经办人',
					field : 'stfId',
					width : 50,
					hidden:true
				
				},{
					title : '经办人名称',
					field : 'stfName',
					width : 100,
					sortable : true
				},{
					title : '采购员',
					field : 'purchaser',
					width : 50,
					hidden:true
				},{
					title : '采购员名称',
					field : 'purchaserName',
					width : 90,
					sortable : true
				},{
					title : '审核状况',
					field : 'examineState',
					hidden:true
				},{
					title : '审核状况名称',
					field : 'examineName',
					width : 50,
					sortable : true
				},{ 

					title : '供货商',
					field : 'supplierId',
					hidden:true
				},{
					title : '供货商',
					field : 'supplier',
					width : 120,
					sortable : true
				},{
					title : '付讫',
					field : 'state',
					hidden:true,
					sortable : true
				}, {
					title : '付讫',
					field : 'stateName',
					width : 50,
					sortable : true
				},{
					title : '订货单号',
					field : 'receipt',
					width : 70,
					sortable : true
				},{
					title : '发票备注',
					field : 'remark',
					width : 120,
					sortable : true
				},{
					title : '企业编号',
					field : 'enterprise_id',
					width : 100,
					hidden:true
				}
		        ]],
	        onDblClickRow:function(rowIndex, rowData){  
					//双击入库单汇总某条记录
				$('#tt').tabs('select','入库单明细');
				$('#StPurOrderForm').form('clear');
				$('#StPurOrderForm').form('load',rowData);
				
				var  parentId=rowData.instorehouseId;
				loadInstoreDel(parentId);
				
		}
	});
			
	//入库单明细
	$('#ForeordainDetilTable').datagrid({
		 fit:true,
		 pagination : true,
		 fitColumns : true,
		 idField : 'detailsId',
		 singleSelect : true,
		 pageSize : 20,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 rownumbers : true,
		 loadMsg : "数据加载中，请稍后……",
		 columns : [ [ {
				title : '入库单明细编号',
				field : 'detailsId',
				hidden:true
		    },{
				title : '入库单汇总编号',
				field : 'instorehouse_',
				hidden:true
		    }, {
				title : '车辆编号',
				field : 'carId',
				hidden:true
		    },{
				title : '编号',
				field : 'carCode',
				hidden:true
		    },{
				title : '车辆型号编号',
				field : 'carModelId',
				hidden:true
		    }, {
				title : '入库日期',
				field : 'instorageDate',	
				width : 100,			
				sortable : true,
				hidden:true
		    },{
				title : '车辆型号',
				field : 'carModelName',
				sortable : true
		    },{
				title : '车辆品牌编号',
				field : 'carBrand',
				hidden:true
		    },{
				title : '车辆品牌',
				field : 'carBrandName',
				width : 100,
				sortable : true
		    },{
				title : '颜色编号',
				field : 'carColor',
				hidden:true
		    },{
				title : '颜色',
				field : 'colorName',
				width : 100,
				sortable : true
		    },{
				title : 'VIN编号',
				field : 'carVinNumber',
				width : 100,
				sortable : true
		    },{
				title : 'OCN码',
				field : 'carOcn',
				width : 100,
				sortable : true
		    },{
				title : '发动机号',
				field : 'carMotorNumber',
				width : 100,
				sortable : true
		    }, {
				title : '成本',
				field : 'vehicleCost',
				width : 100,
				editor : {
				type : 'numberbox',
				options : {
			    	precision : 2,
					min : 0.00,
					validType:'multiple[\'monery\',\'length[0,12]\']'
				}
			 }	
			},{
				title : '未税额',
				field : 'notax',
				width : 100,
				editor : {
					type : 'numberbox',
					options : {
						precision : 2,
						min : 0.00,
						disabled : true,
						validType:'multiple[\'monery\',\'length[0,12]\']'
					}
				 }	
			}, {
				title : '税额',
				field : 'tax',
				width : 100,
				editor : {
					type : 'numberbox',
					options : {
						precision : 2,
						min : 0.00,
						disabled : true,
						validType:'multiple[\'monery\',\'length[0,12]\']'
					}
				 }	
			},{
				title : '改装费',
				field : 'changeMoney',
				width : 100,
				editor : {
					type : 'numberbox',
					options : {
						precision : 2,
						min : 0.00,
						validType:'multiple[\'monery\',\'length[0,12]\']'
					}
				 }	
			},{
				title : '运费',
				field : 'freightMoney',
				width : 100,
				editor : {
					type : 'numberbox',
					options : {
						precision : 2,
						min : 0.00,
						validType:'multiple[\'monery\',\'length[0,12]\']'
					}
				 }	
			}
		    ]],
		    onClickRow : function(rowIndex,rowData){
				if(foreTag){
					copyPartsDateAndBindEventItem($("#ForeordainDetilTable"), rowIndex, rowData);
					$('#ForeordainDetilTable').datagrid('beginEdit', rowIndex);
				}
			}
	   });
});
//车辆档案增加
var slo_d4;
function add_Foreordain()
{ 
	var invoiceType=$('#invoiceType').combo('getValue');
	if(invoiceType!=null && invoiceType!="" ){
		 slo_d4 = $('<div/>');
	  	 slo_d4.dialog({
					title: '车辆档案选择',   
					width: 900,
				    height:530,
				    cache: false,
				    href: projectPath+'sell/foreordain/add_instorehouse.jsp?type='+invoiceType+'&addTypeTag='+typeTag,
				    modal: true,
					onClose : function (){
				    	$(this).dialog('destroy');
				    }
			});
	}else{
		alert("请选择发票类型!");
	}
  	
}
//按钮区域增加 保存和取消按钮
function  addButton(i){
	if(i==1){
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveStOutMain();">保存</a>';
	}else if(i==2){
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="examine();">保存</a>';
	}else if(i==3){
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updateInstore();">保存</a>';
	}
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
}
//设置按钮的状态为可用
function enableBtn(){
	$('#_add').linkbutton('enable');
	$('#_remove').linkbutton('enable');
	$('#_update').linkbutton('enable');
	$('#_search').linkbutton('enable');
	$('#_clear').linkbutton('enable');
	$('#_examine').linkbutton('enable');
	$('#_print').linkbutton('enable');
	$('#_export').linkbutton('enable');
	$('#_import').linkbutton('enable');
	$('#addForeordain').linkbutton('disable');
	$('#deleteForeordain').linkbutton('disable');
}
//禁用按钮
function disableBtn(){
	$('#_add').linkbutton('disable');
	$('#_remove').linkbutton('disable');
	$('#_update').linkbutton('disable');
	$('#_search').linkbutton('disable');
	$('#_clear').linkbutton('disable');
	$('#_examine').linkbutton('disable');
	$('#_print').linkbutton('disable');
	$('#_export').linkbutton('disable');
	$('#_import').linkbutton('disable');
	
}

//点击取消按钮执行的操作
function cancel() {
	foreTag=false;
	$("#warehouse").combobox({required:false});
	$("#car_supplierId").combobox({required:false});
	$("#invoiceType").combobox({required:false});
	$('#tt').tabs('select',0);
	$('#saveOrCancelBtn').empty();
	enableBtn();
	$('#ForeordainTable').datagrid('unselectAll');
	$('#StPurOrderForm').form('clear');  
	//清空入库单明细表中的数据
	$('#ForeordainDetilTable').datagrid('loadData',{total:0,rows:[]});
}

//根据入库单汇总某条记录加载关联的入库单明细信息
function    loadInstoreDel(parentId,key){
	if(typeTag==0){
		var invoiceType=$('#invoiceType').combo('getValue');
		$.ajax({
			type : 'POST',
			url :'instorehouseAction!isfpType.action',
			data :{invoiceType:invoiceType},
			dataType : 'json',
			success : function(r) {
				if(r.success){
					if(r.obj==true){
						typeTag=1;
					}else{
						typeTag=2;
					}
					loadDelRuns(parentId);
				}
			}
		});
	}else{
		loadDelRuns(parentId);
	}
}
var loadDelRuns=function(parentId){
	$('#ForeordainDetilTable').datagrid({
		url : 'instorehouseAction!getPagerDel.action',
		queryParams: {instorehouse_:parentId},
		pagination : true,
		onClickRow : function(rowIndex,rowData){
			if(foreTag){
				copyPartsDateAndBindEventItem($("#ForeordainDetilTable"), rowIndex, rowData);
				$('#ForeordainDetilTable').datagrid('beginEdit', rowIndex);
			}
		}
	});
}
//添加按钮事件
function addPersonnel(i) { 
   if(i==1){
	   foreTag=true;
	   $('#tt').tabs('select','入库单明细');
		$('#addForeordain').linkbutton('enable');
		$('#deleteForeordain').linkbutton('enable');
		$('#_import').linkbutton('enable');
		$("#warehouse").combobox({required:true});
		$("#car_supplierId").combobox({required:true});
		$("#invoiceType").combobox({required:true});
		addButton(1);
		disableBtn();
		$('#StPurOrderForm').form('clear');
		//获取当前日期
		$('#instorehouseDate').val(getSystemTime2());
		$('#stf').combobox('select',stf);//临时
		//每次新增时清空入库单明细表中的数据
		$('#ForeordainDetilTable').datagrid('loadData',{total:0,rows:[]});
   }else if(i==2){
	   var data = $('#ForeordainTable').datagrid('getSelected');
		if(data){
			 $.ajax({
					type : 'POST',
				    dataType : 'json',
					url : 'instorehouseAction!isRefundment.action',
					data : 'examineState='+data.examineState,
					success : function(r){
						if(r.success){
							if(r.obj==true){
								alertMsg('该入库单已审核，不能修改！', 'warning');
								return;	
					}else{
						foreTag=true;
						$('#tt').tabs('select','入库单明细'); 
						$('#addForeordain').linkbutton('enable');
						$('#deleteForeordain').linkbutton('enable');
						$('#_import').linkbutton('enable');
						$("#warehouse").combobox({required:true});
						$("#car_supplierId").combobox({required:true});
						$("#invoiceType").combobox({required:true});

						disableBtn();
						addButton(3);
						$('#StPurOrderForm').form('load',data);
						loadInstoreDel(data.instorehouseId,1);
					}
					}
				}
		    });
			 	
		}else{
			$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){});
		}
	}
}
var noTaxss = 0;
var taxss=0;
function balanceMoney(tag){
	noTaxss=0;
	taxss=0;
	endEdit($("#ForeordainDetilTable"));
	var data =$("#ForeordainDetilTable").datagrid('getData'); 
	 if(tag&&data.rows.length==0){
		 alertMsg('请添加入库单明细信息！', 'warning');
		 return true;
	}
	 if(data!=null&&data.rows.length>0){
		 for(var i=0;i<data.rows.length;i++){
			 noTaxss += Number(data.rows[i].notax); 
			 taxss += Number(data.rows[i].tax); 
		 }
	 }
	 $('#totalTax').numberbox('setValue',taxss);
	 $('#totalNotax').numberbox('setValue',noTaxss);	
}
function saveStOutMain(){
	if($('#StPurOrderForm').form('validate')){
		 var rows=$("#ForeordainDetilTable").datagrid('getRows');
		 endEdit($("#ForeordainDetilTable"));  
		 if(balanceMoney(true)){
			 return;
		 }
		  var effectRow = new Object();
		  effectRow['detaildateGrid'] =  JSON.stringify(rows);
		  effectRow['instorehousedateGrid'] = JSON.stringify(serializeObject($('#StPurOrderForm')));
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url:'${pageContext.request.contextPath}/instorehouseAction!saveInstore.action',   
			   data:effectRow,
			   success: function(r){
				 if(r.success){
					 foreTag=false;
					 $("#warehouse").combobox({required:false,disabled:false});
					 $("#car_supplierId").combobox({required:false,disabled:false});
						$("#invoiceType").combobox({required:false});
					  cancel();
					  //$('#tt').tabs('select','入库单汇总');
					 $('#ForeordainTable').datagrid('load');
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
	}
function updateInstore(){
	if($('#StPurOrderForm').form('validate')){
		 var rows=$("#ForeordainDetilTable").datagrid('getRows');
		 endEdit($("#ForeordainDetilTable"));  
		
		 if(balanceMoney(true)){
			 return;
		 };		
		  var effectRow = new Object();
		  if($("#ForeordainDetilTable").datagrid('getChanges').length) {
				var inserted = $("#ForeordainDetilTable").datagrid('getChanges', 'inserted');
				var deleted = $("#ForeordainDetilTable").datagrid('getChanges', 'deleted');
				var updated = $("#ForeordainDetilTable").datagrid('getChanges', 'updated');
				if(inserted){
					effectRow['inserted'] = JSON.stringify(inserted);
				}
				if(deleted){
					effectRow['deleted'] = JSON.stringify(deleted);
				}
				if(updated){
					effectRow['updated'] = JSON.stringify(updated);
				}
		  	}
		  effectRow['instorehousedateGrid'] = JSON.stringify(serializeObject($('#StPurOrderForm')));
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url:'${pageContext.request.contextPath}/instorehouseAction!updateInstore.action',   
			   data:effectRow,
			   success: function(r){
				 if(r.success){
					 foreTag=false;
					 $("#warehouse").combobox({required:false,disabled:false});
					 $("#car_supplierId").combobox({required:false,disabled:false});
					 $("#invoiceType").combobox({required:false});
					  cancel();
					  //$('#tt').tabs('select','入库单汇总');
					 $('#ForeordainTable').datagrid('load');
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
	
}
	
//删除
function remove_(){
	var data = $('#ForeordainTable').datagrid('getSelected');
	 var index=findSelectRowIndex('ForeordainTable',data);
	if(data){
	$.ajax({
		type : 'POST',
	    dataType : 'json',
		url : 'instorehouseAction!isRefundment.action',
		data : 'examineState='+data.examineState,
		success : function(r){
			if(r.success){
				if(r.obj==true){
					alertMsg('该入库单已审核，不能删除！', 'warning');
					return;		
	}else {
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'instorehouseAction!deleteInstore.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						   $('#ForeordainTable').datagrid('clearSelections');
						   $('#ForeordainTable').datagrid('load');
						   $('#ForeordainDetilTable').datagrid('load');
						   setSelectRow('ForeordainTable',index);
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
		});
	}
			 }
			 }
			 });
	}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}		
}

function delete_Foreordain(){
	var data = $('#ForeordainDetilTable').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
					 var index= $('#ForeordainDetilTable').datagrid('getRowIndex',data);
					 $(ForeordainDetilTable).datagrid('deleteRow', index);
					 $("#number").val( $("#number").val()-1);
					 balanceMoney(false);
					 return;
				}
			});
		}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}
function examineState(){
	var data = $('#ForeordainTable').datagrid('getSelected');
	
	if(data){
		$.ajax({
			type : 'POST',
		    dataType : 'json',
			url : 'instorehouseAction!isRefundment.action',
			data : 'examineState='+data.examineState,
			success : function(r){
				if(r.success){
					if(r.obj==true){
						alertMsg('该入库单已被审核过！', 'warning');
						return;	
					 }else{
						 $.messager.confirm('优亿软件提示', '请确认是否要审核该条记录？', function(r){
							 if(r){
								 $.ajax({
									   type: 'post',
									   dataType: 'json',
									   url: 'instorehouseAction!examine.action',
									   data:{instorehouseId:data.instorehouseId},
									   success: function(r){
										 if(r.success){
											 $('#ForeordainTable').datagrid('reload');
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
	}else{
		$.messager.alert('优亿软件提示','对不起，请先选中要审核的记录！','warning',function(){});
	}
}

var queryForeordain = function (){
	$('#ForeordainTable').datagrid('unselectAll');
	$('#ForeordainTable').datagrid('load', serializeObject($('#foreordainQueryForm')) );
	addInitDate($('#servicingNextdate'),$('#servicingNextdate2'));
	
}
function clearSearchCondition(){
	$('#foreordainQueryForm').form('clear');
	$('#ForeordainTable').datagrid('load', serializeObject($('#foreordainQueryForm')) );
	addInitDate($('#servicingNextdate'),$('#servicingNextdate2'));


}
var slo_d;
function importDel(){
	    $('#tt').tabs('select','入库单明细');
		$('#addForeordain').linkbutton('disable');
		$('#deleteForeordain').linkbutton('disable');
		$('#_import').linkbutton('enable');
		$("#warehouse").combobox({required:true});
		$("#car_supplierId").combobox({required:true});
		$("#invoiceType").combobox({required:true});
		var save ="<a id='save' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-save' plain='true' onclick='importCar();'>保存</a>";
		var cancel="<a id='cancel' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-undo' plain='true' onclick='cancel();'>取消</a>";
		if ($('#saveOrCancelBtn').children('a').length == 0) {
			$('#saveOrCancelBtn').append(save).append(cancel);
			$.parser.parse('#saveOrCancelBtn');
		}
		disableBtn();
		$('#StPurOrderForm').form('clear');
		//获取当前日期
		$('#instorehouseDate').val(getSystemTime2());
		$('#stf').combobox('select',stf);//临时
		//每次新增时清空入库单明细表中的数据
		$('#ForeordainDetilTable').datagrid('showColumn','instorageDate');
		$('#ForeordainDetilTable').datagrid('loadData',{total:0,rows:[]});
		showEditDialog("ForeordainDetilTable",null,"ForeordainDetilTable_div","开始导入","导入配置",1,callBackInstore);
}
/**
 * 导入excel
 * 将要导入的Excel文件列顺序与选择对应的列顺序一致
 * @return
 */
function importCar(){
	var carData=$('#ForeordainDetilTable').datagrid('getData');
		  var effectRow = new Object();
		  effectRow['detaildateGrid'] =  JSON.stringify(carData.rows);
		$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url:'${pageContext.request.contextPath}/instorehouseAction!importInstore.action',   
			   data:effectRow,
			   success: function(r){
				 if(r.success){
					 $('#ForeordainTable').datagrid('load');
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
function callBackInstore(pid, data){
	var json = $.parseJSON(data);  
 	$('#ForeordainDetilTable').datagrid('loadData',json);
 	var detilData=json.rows;
	var notaxTotal=0.00;
	var taxTotal=0.00;
 	var sumNum=0.00;
 	for(var i=0;i<detilData.length;i++){
 		if(detilData[i].notax!=null&&detilData[i].notax!=''&&detilData[i].notax!=undefined)
 		   notaxTotal=accAdd(notaxTotal,detilData[i].notax);
 		if(detilData[i].tax!=null&&detilData[i].tax!=''&&detilData[i].tax!=undefined)
 		   taxTotal=accAdd(taxTotal,detilData[i].tax);
 	}
 	$('#number').val(detilData.length);
 	$('#totalTax').val(notaxTotal.toFixed(2));
 	$('#totalNotax').val(taxTotal.toFixed(2));
		
}

//编辑
function copyPartsDateAndBindEventItem(id, rowIndex, rowData)
{	
	var invoiceType=$('#invoiceType').combo('getValue');
	id.datagrid('beginEdit', rowIndex);
	var ed = id.datagrid('getEditors', rowIndex);
	if(ed[0]){
		if(typeTag==1){
			ed[0].target.numberbox('setValue', rowData.vehicleCost);
		    ed[1].target.numberbox('setValue', rowData.vehicleCost/1.17);
			ed[2].target.numberbox('setValue', rowData.vehicleCost-rowData.vehicleCost/1.17);
			ed[0].target.select();
			ed[0].target.click(function (){
				ed[0].target.select();
			});
			ed[0].target.keydown(function (e){
				if(e.keyCode == '13'){
					ed[2].target.select();
				}
			});
			ed[0].target.bind('keyup', function() {
				var vehicleCost = ed[0].target.val();
				ed[1].target.numberbox('setValue', vehicleCost/1.17);
				ed[2].target.numberbox('setValue', vehicleCost-vehicleCost/1.17);
			});
		}else if(typeTag==2){
			ed[0].target.numberbox('setValue', rowData.vehicleCost);
		    ed[1].target.numberbox('setValue', rowData.vehicleCost);
			ed[2].target.numberbox('setValue', 0);
			ed[0].target.select();
			ed[0].target.click(function (){
				ed[0].target.select();
			});
			ed[0].target.keydown(function (e){
				if(e.keyCode == '13'){
					ed[2].target.select();
				}
			});
			ed[0].target.bind('keyup', function() {
				var vehicleCost = ed[0].target.val();
				ed[1].target.numberbox('setValue', rowData.vehicleCost);
				ed[2].target.numberbox('setValue', 0);
			});
		}else{
			alert('请重新操作！');
		}
		
	}				
	
}
var tag=true;
var invoiceId=0;
//下拉框值发生变化时
function comboxChange()
{	
	if(invoiceId==0){
		$.ajax({
			type : 'POST',
			url :'instorehouseAction!isfpTypeId.action',
			dataType : 'json',
			success : function(r) {
				if(r.success){
					invoiceId=r.obj;
					typeChange();
				}
			}
		});
	}else{
		typeChange();
	}
	
}
var typeChange=function(){
	var invoiceType=$('#invoiceType').combobox('getValue');
	endEdit($("#ForeordainDetilTable"));
	$.ajax({
		type : 'POST',
		url :'instorehouseAction!isfpType.action',
		data :{invoiceType:invoiceType},
		dataType : 'json',
		success : function(r) {
			if(r.success){
				if(r.obj==true){
					typeTag=1;
				}else{
					typeTag=2;
				}
				var rows=$("#ForeordainDetilTable").datagrid('getData');
				if(rows.rows.length>0){
					for(var i=0;i<rows.rows.length;i++){
						if(invoiceType==invoiceId){
							rows.rows[i].notax = parseFloat(rows.rows[i].vehicleCost)/1.17;
							rows.rows[i].tax = parseFloat(rows.rows[i].vehicleCost)-parseFloat(rows.rows[i].vehicleCost)/1.17;
						}else{
							rows.rows[i].notax = rows.rows[i].vehicleCost;
							rows.rows[i].tax = 0.00;
						}				
					}
					$("#ForeordainDetilTable").datagrid('loadData',rows);
					balanceMoney(false);
					var data=$("#ForeordainDetilTable").datagrid('getData');
					for(var i=0;i<data.rows.length;i++){
						$("#ForeordainDetilTable").datagrid('beginEdit', i);
					}
					endEdit($("#ForeordainDetilTable"));
			}
			}
		}
	});
}
function _except(){
	if(tbtitle =='入库单汇总'){
		var data =  $("#ForeordainTable").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("ForeordainTable",null,"ForeordainTable_div","开始导出","导出配置",0,_callbackExcept);
		
	}else{
		var data =  $("#ForeordainDetilTable").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("ForeordainDetilTable",null,"ForeordainDetilTable_div","开始导出","导出配置",0,_callbackExcept2);	
	}
	
	
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"入库单汇总"+getSystemTime());
}
function _callbackExcept2(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"入库单明细"+getSystemTime());
}

function instorehousePrint(){
	 var selected=$('#ForeordainTable').datagrid('getSelected')
   if(selected!=''&&selected!=null){
  	    window.open(projectPath+'sell/foreordain/instorehousePrintReport.jsp?instorehouseId='+selected.instorehouseId,'demo',"fullscreen=1")
	}else{
		 $.messager.alert('优亿软件提示','请选择要打印的入库单记录！','warning');
	}
}