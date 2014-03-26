//根据调拨单汇总某条记录加载关联的入库单明细信息
function  loadInstoreDel(parentId,key){

	$('#mingxiList').datagrid({
					url : 'carBarnInfoAction!getAllocatel.action',
					queryParams: {allocateId:parentId},
					pagination : true,
					onClickRow : function(rowIndex,rowData){
						if(foreTag){
							editB($("#mingxiList"), rowIndex, rowData);
						}
					}
					
				});
}



var allocatePerson;

var allocatePersonId;
var foreTag=false;
$(function(){
	$('#reserveDete2').val(getSystemTime());
	 getStartDate($('#reserveDete'));
	
	allocatePerson=$('#allocatePersonName').val();
	allocatePersonId=$('#allocatePerson').val();
	consignee=$('#consignee').val();
	$('#dName2').validatebox({  
        required:true,
        missingMessage:'分销商不可为空！'
});
	$('#warehouse').combobox({  
        required:true,
        missingMessage:'仓库为必选项！'
}); 
	
	
	//页面初始化时，设置按钮的状态
	$('#tt').tabs({   
		onSelect:function(title){  
		
		 tbtitle = title;
			if(title =='调拨单汇总'){
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					enableBtn();
					
					
				}
			}else if(title =='调拨单明细'){
				//disableBtn();	
				
			}
	    }   
	});
	
			//调拨单汇总
			$('#allocateList').datagrid({
				url : 'sellAllocatelAction!getSellAllocatelList.action',
				pagination : true,
				fit:true,
				fitColumns : true,
				sortOrder:'asc',
			    sortName:'allocateId',
				singleSelect : true,
				pageSize :10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				rownumbers : true,
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ 
				    {
					field : 'allocateId',
					title : '编号',
					width : 50,
					hidden:true,
					sortable : true
				}, {
					field : 'allocatecode',
					title : '调拨单号',
					width : 120,
					sortable : true,
				}, {
					field : 'allocateDate',
					title : '调拨日期',
					width : 75,
					sortable : true
				}, {
					field : 'xsDistributorName',
					title : '分销商',
					width : 170,
					sortable : true
				}, {
					field : 'xsDistributorId',
					title : '分销商id',
					width : 130,
					hidden:true,
					sortable : true
				}, {
					field : 'costprice',
					title : '调拨成本',
					width : 90,
					sortable : true
				}, {
					field : 'allocateAmount',
					title : '调拨价',
					width : 90,
					sortable : true
				},{
					field : 'allocatePerson',
					title : '经办人id',
					width : 70,
					hidden:true,
					sortable : true
				},{
					field : 'allocatePersonName',
					title : '经办人',
					width : 130,
					sortable : true
				},{
					field : 'allocateTypeName',
					title : '调拨分类',
					width :100,
					sortable : true
				 },{
					field : 'allocateType',
					title : '调拨分类id',
					width : 80,
					hidden:true,
					sortable : true
			   
			    },{
					field : 'examineName',
					title : '审核情况',
					width : 90,
					sortable : true,
					formatter : function(value,rowData,rowIndex){
					if(value=="未审核"){
						return '<font color="red">'+value+'</font>';
					}else{
						return value;
					}
					
				}
			    }, {
					field : 'examine',
					title : '审核情况id',
					width : 80,
					hidden:true,
					sortable : true
			   
			    }, {
					field : 'paymentStateName',
					title : '是否付款',
					width : 80,
					sortable : true,
				}, {
					field : 'paymentState',
					title : '付款情况id',
					width : 80,
					hidden:true,
					sortable : true
				}, {
					field : 'house',
					title : '仓库',
					width : 80,
					sortable : true,
				}, {
					field : 'warehouse',
					title : '仓库id',
					width : 80,
					hidden:true,
					sortable : true
				}, { 
					field : 'remark', 
			    	title : '发票号及备注', 
			    	width : 180, 
			    	sortable : true 
			    }, {
					field : 'backCode',
					title : '调退单号',
					width : 80,
					hidden:true,
					sortable : true
				},{
					title : '企业编号',
					field : 'enterprise_id',
					width : 100,
					hidden:true
				}
		        ]],
		    	onDblClickRow : function(rowIndex, rowData) {
		    		$('#tt').tabs('select','调拨单明细');		    		
		    			$('#StForm').form('load', rowData);// 将现在新选的行重新load在from表单中
		    			var  Id=rowData.allocateId;
		    			loadInstoreDel(Id);
				
		}
	});
			
			
			//调拨单明细
					$mingxiList = $('#mingxiList').datagrid({
						url : '',
						pagination : true,
						fit : true,
						fitColumns : true,
						singleSelect : true,
						pageSize : 20,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						idField : 'carId',
						loadMsg : "数据加载中，请稍后……",
						columns : [ [ {
							field : 'carId',
							title : '编号',
							width : 50,
							sortable : true,
						}, {
							field : 'carCode',
							title : '车辆编号',
							width : 130,
							sortable : true
						}, {
							field : 'carVinNumber',
							title : 'VIN号',
							width : 130,
							sortable : true
						}, {
							field : 'carBrandName',
							title : '车辆品牌',
							width : 90,
							sortable : true
						}, {
							field : 'carBrand',
							title : '车辆品牌id',
							hidden:true,
							width : 90,
							sortable : true
						}, {
							field : 'carModelName',
							title : '车辆类型',
							width : 120,
							sortable : true
						}, {
							field : 'carModel',
							title : '车辆类型id',
							hidden:true,
							width : 80,
							sortable : true
						}, {
							field : 'carColorName',
							title : '颜色',
							width : 90,
							sortable : true
						}, {
							field : 'carColor',
							title : '颜色id',
							hidden:true,
							width : 70,
							sortable : true
						}, {
							field : 'house',
							title : '库位',
							width : 100,
							sortable : true
						}, {
							field : 'houseid',
							title : '库位id',
							width : 80,
							hidden:true,
							sortable : true
						 },	{
							field : 'allAmount',
							title : '调拨价',
							width : 100,
							sortable : true,
							editor : {
							type : 'numberbox',
							options : {
							required : true,
							min:0.00, 
							precision : 2,
							validType:'multiple[\'monery\',\'length[0,12]\']'
							}
						 }	
							
						}, {
							field : 'modelCostPrice',
							title : '成本价',
							width : 100,
							sortable : true
						}, {
							title : '入库明细编号',
							field : 'detailsId',
							width : 70,
							hidden:true
						}
				        ]],
					    onClickRow : function(rowIndex,rowData){
						if(foreTag){
							editB($("#mingxiList"), rowIndex, rowData);
							$('#mingxiList').datagrid('beginEdit', rowIndex);
						}
					}
				        
			   });

});
//编辑
function editB(id, rowIndex, rowData)
{
	id.datagrid('beginEdit', rowIndex);
	var ed = id.datagrid('getEditors', rowIndex);
}



//车辆档案增加
var sc;
function add_Foreordain()
{
	 
  	 sc = $('<div/>');
  	 sc.dialog({
				title: '车辆档案选择',   
				width: 750,
			    height: 450,
			    cache: false,
			    href: projectPath+'sell/allocateManage/addCar.jsp',
			    modal: true,
				onClose : function (){
			    	$(this).dialog('destroy');
			    }
		});
}
//按钮区域增加 保存和取消按钮
function  addButton(i){
	if(i==1){
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveStOutMain();">保存</a>';
	}else if(i==2){
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updates();">保存</a>';
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
		
}

//点击取消按钮执行的操作
function cancel() {
	foreTag=false;
	$("#dName2").validatebox({required:false});
	$("#warehouse").combobox({required:false});
	$('#tt').tabs('select',0);
	$('#saveOrCancelBtn').empty();
	enableBtn();
	$('#allocateList').datagrid('unselectAll');
	$('#StForm').form('clear');  
	
}


//添加按钮事件
function addPersonnel(i) { 
   if(i==1){	
	   foreTag=true;
	   $('#tt').tabs('select','调拨单明细');
		$('#addForeordain').linkbutton('enable');
		$('#deleteForeordain').linkbutton('enable');
		$('#_print').linkbutton('disable');
		$('#_export').linkbutton('disable');
		$('#_import').linkbutton('disable');
		$("#dName2").validatebox({required:true});
		$("#warehouse").combobox({required:true});
		nuDisAbledControl(1);
		addButton(1);
		disableBtn();
		$('#StForm').form('clear');
		$('#allocateDate').val(getSystemTime());
		$('#allocatePerson').combobox('select',allocatePersonId);
		$('#consignee').val(consignee);
		
		//每次新增时清空明细表中的数据
		$('#mingxiList').datagrid('loadData',{total:0,rows:[]});
	}else if(i==2){                  //修改
		var data = $('#allocateList').datagrid('getSelected');
		 if(data){
			 $.ajax({
					type : 'POST',
				    dataType : 'json',
					url : 'sellAllocatelAction!isRefundment.action',
					data : 'examine='+data.examine,
					success : function(r){
						if(r.success){
							if(r.obj==true){
								alertMsg('该调拨单已审核，不能修改！', 'warning');
								return;							
							}else{
					$('#tt').tabs('select','调拨单明细');
					  foreTag=true;
					nuDisAbledControl();
					disableBtn();
					$('#_print').linkbutton('disable');
					$('#_export').linkbutton('disable');
					$('#_import').linkbutton('disable');
					$('#addForeordain').linkbutton('enable');
					$('#deleteForeordain').linkbutton('enable');
					var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updates();">保存</a>';
					var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
					if ($('#saveOrCancelBtn').children('a').length == 0) {
						$('#saveOrCancelBtn').append(save).append(cancel);
						$.parser.parse('#saveOrCancelBtn');
					}
					$('#StForm').form('load', data);
					loadInstoreDel(data.allocateId,1);
					var index = $("#mingxiList").datagrid('getRowIndex', rowData);
							}
						}
			 }
						});
			 
					}else{
						   $.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning');
				    
				}
  $('#allocatecode').select();
		}
	}

var costpr = 0;
var vehicleCosts=0;
function sumMoney(tag){
	costpr=0;
	vehicleCosts=0;
	endEdit($("#mingxiList"));
	var data =$("#mingxiList").datagrid('getData'); 
	 if(tag&&data.rows.length==0){
		  alertMsg('对不起，请添加车辆信息！', 'warning');
		 return true;
	}
	 if(data!=null&&data.rows.length>0){
		 for(var i=0;i<data.rows.length;i++){
			 var amount=data.rows[i].allAmount;
			  var price=data.rows[i].modelCostPrice;
			 if( amount!=null &&  amount!="" &&  amount!=undefined){
				  vehicleCosts += Number(amount); 
				  }
			 if( price!=null &&  price!="" &&  price!=undefined){
				  costpr+=Number(price);
				  }
			  
		 }
	 }
	 $('#costprice').numberbox('setValue',costpr);
	  $('#allocateAmount').numberbox('setValue',vehicleCosts);
}

//保存新添加车辆信息
function saveStOutMain(){
	
	if($('#StForm').form('validate')){
		
		   var rows=$("#mingxiList").datagrid('getRows');
		  endEdit($("#mingxiList"));
		  if(sumMoney(true)){
				 return;
			 }
			
		  var effectRow = new Object();
		  effectRow['detaildateGrid'] =  JSON.stringify(rows);
		  effectRow['instorehousedateGrid'] = JSON.stringify(serializeObject($('#StForm')));
		 
		  $.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'${pageContext.request.contextPath}/sellAllocatelAction!addInstoreCar.action',   
		   data:effectRow,
		   success: function(r){
			 if(r.success){
				 
				 $("#dName2").validatebox({required:false});
					$("#warehouse").combobox({required:false});

				  cancel();
				  
				 $('#allocateList').datagrid('load');
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
//保存修改
function updates(){

	if($('#StForm').form('validate')){
		   var rows=$("#mingxiList").datagrid('getRows');
		  endEdit($("#mingxiList"));
		  if(sumMoney(true)){
				 return;
			 }
		var effectRow = new Object();
		effectRow['detaildateGrid'] =  JSON.stringify(rows);
		 if($("#mingxiList").datagrid('getChanges').length) {
			 
				var inserted = $("#mingxiList").datagrid('getChanges', 'inserted');
				var deleted = $("#mingxiList").datagrid('getChanges', 'deleted');
				var updated = $("#mingxiList").datagrid('getChanges', 'updated');
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
		 effectRow['instorehousedateGrid'] = JSON.stringify(serializeObject($('#StForm')));
		
		$.ajax({
			url:'${pageContext.request.contextPath}/sellAllocatelAction!modifySellAllocatel.action',
			type: 'post',
			dataType: 'json',
			data:effectRow,
			success: function(r){
			if(r.success){	
				 $("#dName2").validatebox({required:false});
				cancel();
				
				 $('#allocateList').datagrid('load');
				 $('#mingxiList').datagrid('load');
				$('#button').empty();
				
				
			}else{
				$.messager.alert('提示','数据提交失败!','warning');
			}
 }
});
	}
}	
//删除调拨单汇总
function remove_(){
	var data = $('#allocateList').datagrid('getSelected');
    var index=findSelectRowIndex('allocateList',data);
	if(data){
		 $.ajax({
				type : 'POST',
			    dataType : 'json',
				url : 'sellAllocatelAction!isRefundment.action',
				data : 'examine='+data.examine,
				success : function(r){
					if(r.success){
						if(r.obj==true){
							alertMsg('该调拨单已审核，不能删除！', 'warning');
							return;							
						}else{

				$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
				if (r){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'sellAllocatelAction!deleteInfo.action',
					   data: data,
					   success: function(r){
						 if(r.success){
							 $('#allocateList').datagrid('clearSelections');
							 $('#allocateList').datagrid('load');
							 $('#mingxiList').datagrid('load');
							 setSelectRow('allocateList',index);
							 
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
//删除明细
function delete_Foreordain(){
	var data = $('#mingxiList').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				//alert(data.detailsId);
					 var index= $('#mingxiList').datagrid('getRowIndex',data);
					 $('#mingxiList').datagrid('deleteRow', index);
					 sumMoney(false);
					 return;
				 }
			});
		}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}



// 查询
var queryCarReserve = function() {

	$('#allocateList').datagrid('unselectAll');
	$('#allocateList').datagrid('load',serializeObject($('#queryForm')));
	addInitDate($('#reserveDete'),$('#reserveDete2'));

}

//清空
function clearSearchCondition(){
	$('#queryForm').form('clear');
	
	$('#allocateList').datagrid('unselectAll');
	$('#allocateList').datagrid('load',serializeObject($('#queryForm')));
	addInitDate($('#reserveDete'),$('#reserveDete2'));

	
}
/**审核事件**/
/*function examineButton(){
	 var selected = $('#allocateList').datagrid('getSelected');
     if(selected!=''&&selected!=null){
         if(selected.backCode==null||selected.backCode==''){
        	 var examine=selected.examineName;
		     pos = examine.indexOf('未审核');  
		     if (pos == -1) {
		    	 $.messager.confirm('优亿软件提示', '请确认是否要取消审核该调拨单？', function(r){
		              if(r)
		            	  examineState(selected);
        		 });
		     }else{
		    	 $.messager.confirm('优亿软件提示', '请确认是否要审核该调拨单？', function(r){
		               if(r)
		            	   examineState(selected);
        		 });
		     }
         }else
        	 $.messager.alert('优亿软件提示', '对不起，该调拨单已经被调退，不允许取消审核，请先删除调退单号为【'+selected.backCode+'】的调退单后再执行本操作！', 'info', function() {});
     }else
		 $.messager.alert('优亿软件提示','对不起，请先选中要审核的记录！','warning',function(){});	
 }
*/
//审核
function examineButton(){
	 var selected = $('#allocateList').datagrid('getSelected');
     if(selected){
    	 $.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'sellAllocatelAction!isUseDocument.action',
			   data:selected,
			   success: function(r){
				 if(r.success){
					 if(r.obj==true){
						 alertMsg('对不起，此调拨单已经被调退,不允许取消审核！', 'warning'); 
					 }else{
				$.messager.confirm('优亿软件提示', '请确认是否执行此操作？', function(r){
					if(r){
						$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'sellAllocatelAction!examine.action',
					   data:selected,
					   success: function(r){
						 if(r.success){
							 $('#allocateList').datagrid('load');
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
     }else
		 $.messager.alert('优亿软件提示','对不起，请先选中要审核的记录！','warning',function(){});
			
		
}




//启用控件
function nuDisAbledControl(num){
	$("#StForm input.easyui-combobox").combobox('enable');
	$("#StForm input").prop("disabled", false);
	$("#StForm select").prop("disabled", false);
	$("#StForm textarea").prop("disabled",false);
	$("#DisImg").unbind();
	if(num==1){
		$("#DisImg").bind("click",adddis2);
	}
	$('#allocatePerson').combobox({disabled:true});
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
	if(tbtitle =='调拨单汇总'){
		var data =  $("#allocateList").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("allocateList",null,"qingdan","开始导出","导出配置",0,_callbackExcept);
		
	}else{
		var data =  $("#mingxiList").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("mingxiList",null,"mingxi","开始导出","导出配置",0,_callbackExcept2);	
	}
	
	
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
	exportEsuyUIExcelFile(parentId,fieldNames,"调拨单汇总"+getSystemTime());
}
function _callbackExcept2(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"调拨单明细"+getSystemTime());
}

function sellExchangePrint(){
	 var selected=$('#allocateList').datagrid('getSelected')
  if(selected!=''&&selected!=null){
 	    window.open(projectPath+'sell/allocateManage/sellExchangePrintReport.jsp?allocateId='+selected.allocateId,'demo',"fullscreen=1")
	}else{
		 $.messager.alert('优亿软件提示','请选择要打印的调拨单记录！','warning');
	}
}
