var  parentId;
var backPerson;
var tbtitle;
$(function(){
	

	$('#retreatDateEnd').val(getSystemTime());
	getStartDate($('#retreatDateStart'));
	
	backPerson=$('#backPerson').val();
	//页面初始化时，设置按钮的状态
	$('#tt').tabs({   
		onSelect:function(title){ 
		tbtitle=title;
			if(title =='退厂单汇总'){
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					enableBtn();
				}
			}else if(title =='退厂单明细'){
			//	disableBtn();		
			}
	    }   
	});
			//退厂单汇总
			$('#retreat').datagrid({
			url:'${pageContext.request.contextPath}/retreatAction!getPager.action',
			fit:true,
			pagination : true,
			fitColumns : true,
			sortOrder:'asc',
		    sortName:'retreatId',
			singleSelect : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			 columns : [ [{
					title : '退厂记录编号',
					field : 'retreatId',
					hidden:true
				},{
					title : '入库单明细编号',
					field : 'detailsId_',
					hidden:true
				},{
					title : '序号',
					field : 'retreatCode',
					width : 150,
					sortable : true
				}, {
					title : '退车日期',
					field : 'retreatDate',
					width : 80,
					sortable : true
				}, {
					title : '经办人',
					field : 'personName',
					width : 100,
					sortable : true
				},{
					title : '退车理由',
					field : 'context',
					width : 250,
					sortable : true
				},{
					title : '审核状况',
					field : 'examine',
					width : 80,
					sortable : true,
					formatter : function(value,rowData,rowIndex){
					if(value==185){
						return '<font color="red">'+'未审核'+'</font>';
						}else{
						return '已审核';
					}
					
				}
				},{
					title : '库存类型',
					field : 'instorehouseType',
					hidden:true
				},{
					title : '企业编号',
					field : 'enterprise_id',
					width : 100,
					hidden:true
				},{
					title : '供应商',
					field : 'xsSupplierId',
					width : 100,
					hidden:true
				}
		        ]],
	        onDblClickRow:function(rowIndex, rowData){  
					//双击退厂单汇总某条记录
				$('#retreatForm').form('load',rowData);
				$('#tt').tabs('select','退厂单明细');
				
				 parentId=rowData.retreatCode;
				loadInstoreDel(parentId);
				
		}
	});
			
			//退厂单明细
			$('#instoreDel').datagrid({
				fit:true,
				 pagination : true,
				 fitColumns : true,
				 idField : 'detailsId_',
				 sortOrder:'asc',
				 sortName:'retreatId',
				 singleSelect : true,
				 pageSize : 20,
				 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				 rownumbers : true,
				 loadMsg : "数据加载中，请稍后……",
				 columns : [ [  {
						title : '序号',
						field : 'retreatId',
						width : 100,
						hidden:true
				    },{
						title : '入库单明细编号',
						field : 'detailsId',
						width : 100,
						hidden:true
				    },{
						title : '入库单汇总编号',
						field : 'instorehouse_',
						width : 100,
						hidden:true
				    }, {
						title : '编号',
						field : 'carId',
						width : 100,
						hidden:true
				    },{
						title : '编号',
						field : 'carCode',
						width : 150,
						hidden:true
				    },{
						title : '车辆型号编号',
						field : 'carModelId',
						width : 100,
						hidden:true
				    },{
						title : '车辆型号',
						field : 'carModelName',
						width : 100,
						sortable : true
				    },{
						title : '车辆品牌编号',
						field : 'carBrand',
						width : 100,
						hidden:true
				    },{
						title : '车辆品牌',
						field : 'carBrandName',
						width : 100,
						sortable : true
				    },{
						title : '颜色编号',
						field : 'carColor',
						width : 100,
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
				    },{
						title : '未税额',
						field : 'notax',
						width : 100,
						sortable : true
					},{
						title : '税额',
						field : 'tax',
						width : 100,
						sortable : true
					}, {
						title : '销售价',
						field : 'modelSalesPrice',
						width : 100,
						sortable : true
					}
				        ]]
			   });

});
//车辆档案增加
var slo_d4;
function add_Foreordain()
{
	var suppler=$("#xs_supplier_id").combobox('getValue');
	if(suppler!=null && suppler!=""){
		slo_d4 = $('<div/>');
	  	 slo_d4.dialog({
					title: '车辆选择',   
					width: 900,
				    height:530,
				    cache: false,
				    href: projectPath+'sell/carback/add_instoreDel.jsp?supplerid='+suppler,
				    modal: true,
					onClose : function (){
				    	$(this).dialog('destroy');
				    }
			});
	}else{
		alert("请先选择供应商!");
	}
  	 
}
//按钮区域增加 保存和取消按钮
function  addButton(i){
	if(i==1){
		//点击新增调用的保存方法
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveStOutMain();">保存</a>';
	}else if(i==2){
		//点击修改调用的保存方法
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updateRetreat();">保存</a>';
	}
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
	$('#xs_supplier_id').combobox('enable');
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
	$('#tt').tabs('select',0);
	$('#saveOrCancelBtn').empty();
	enableBtn();
	$('#ForeordainTable').datagrid('unselectAll');
	$('#StPurOrderForm').form('clear');  
}

//根据退厂单汇总某条记录加载关联的退厂单明细信息
function    loadInstoreDel(parentId){
	$('#instoreDel').datagrid({
		url : 'retreatAction!getPagerDel.action',
		queryParams: {retreatCode:parentId},
		pagination : true,
		onLoadSuccess:function(data){
			validateDetail();
		}
	});
}
//添加按钮事件
function addRetreat(i) { 
   if(i==1){	
	   $('#tt').tabs('select','退厂单明细');
		$('#addForeordain').linkbutton('enable');
		$('#deleteForeordain').linkbutton('enable');
		 disableBtn();
		addButton(1);
		$('#retreatForm').form('clear');
		$('#retreatDate').val(getSystemTime());  
		$('#backPerson').combobox('select',backPerson);//临时
		//每次新增时清空入库单明细表中的数据
		$('#instoreDel').datagrid('loadData',{total:0,rows:[]});
	}else if(i==2){
		var data = $('#retreat').datagrid('getSelected');
		if(data){
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url:'${pageContext.request.contextPath}/retreatAction!isRefundment.action',   
			   data:data,
			   success: function(r){
				 if(r.success){
					 if(r.obj==true){
						 alertMsg('该条记录已被审核过,不能修改！', 'warning');
						 return;
					 }
					 $('#tt').tabs('select','退厂单明细');
					$('#addForeordain').linkbutton('enable');
					$('#deleteForeordain').linkbutton('enable');
					 disableBtn();
					addButton(2);
					$('#retreatForm').form('load',data);
					loadInstoreDel(data.retreatCode);
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
			$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){});
		}
	}
}
function updateRetreat(){
	if($('#retreatForm').form('validate')){
		 var rows=$("#instoreDel").datagrid('getRows');  
		  if(rows.length==0){
			  alertMsg('对不起，请添加退厂单！', 'warning');
			  return ;
		}
		
		  var effectRow = new Object();
		  effectRow['sellRetreat'] = JSON.stringify(serializeObject($('#retreatForm')));	  
		  if($("#instoreDel").datagrid('getChanges').length) {
				var inserted = $("#instoreDel").datagrid('getChanges', 'inserted');
				var deleted = $("#instoreDel").datagrid('getChanges', 'deleted');
				var updated = $("#instoreDel").datagrid('getChanges', 'updated');
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
				
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url:'${pageContext.request.contextPath}/retreatAction!updateRetreat.action',   
				   data:effectRow,
				   success: function(r){
					 if(r.success){
						  cancel();
						  //$('#tt').tabs('select','退厂单汇总');
						 $('#retreat').datagrid('load');
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

function saveStOutMain(){
	if($('#StPurOrderForm').form('validate')){
		   var rows=$("#instoreDel").datagrid('getRows');	  
		  var effectRow = new Object();
		  effectRow['instoreDelGrid'] =  JSON.stringify(rows);
		  effectRow['sellRetreat'] = JSON.stringify(serializeObject($('#retreatForm')));
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url:'${pageContext.request.contextPath}/retreatAction!saveRetreat.action',   
			   data:effectRow,
			   success: function(r){
				 if(r.success){
					 $('#retreat').datagrid('load');
					  cancel();
					  //$('#tt').tabs('select','退厂单汇总');
					
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
function removeRetreat(){
	var data = $('#retreat').datagrid('getSelected');
	 var index=findSelectRowIndex('retreat',data);
	if(data){
		$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'${pageContext.request.contextPath}/instoreMoveAction!isRefundment.action',   
		   data:data,
		   success: function(r){
			 if(r.success){
				 if(r.obj==true){
					 alertMsg('该条记录已被审核过,不能删除！', 'warning');
					 return;
				 }
				 $.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
					if (r){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'retreatAction!deleteRetreat.action',
						   data: data,
						   success: function(r){
							 if(r.success){
								    $('#retreat').datagrid('clearSelections');
								    $('#retreat').datagrid('load');
									$('#instoreDel').datagrid('load');
									setSelectRow('retreat',index);
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
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}		
}

function delete_Foreordain(){
	var data = $('#instoreDel').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				var id=data.retreatId;
				 var index= $('#instoreDel').datagrid('getRowIndex',data);
				  $('#instoreDel').datagrid('deleteRow', index);
				  validateDetail();
			}
		});
		}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}
function examine_(){
	var data = $('#retreat').datagrid('getSelected');
	if(data){
		$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'${pageContext.request.contextPath}/retreatAction!isRefundment.action',   
		   data:data,
		   success: function(r){
			 if(r.success){
				 if(r.obj==true){
					 alertMsg('该条记录已被审核过,不能反审核！', 'warning');
					 return;
				 }
				 $.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'retreatAction!updateExamine.action',
				   data:{retreatCode:data.retreatCode},
				   success: function(r){
					 if(r.success){
						 $('#retreat').datagrid('reload');
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

var queryRetreat = function (){
	$('#retreat').datagrid('unselectAll');
	$('#retreat').datagrid('load', serializeObject($('#retreatQueryForm')) );
	addInitDate($('#retreatDateStart'),$('#retreatDateEnd'));
}
function clearSearchCondition(){
	$('#retreatQueryForm').form('clear');
	addInitDate($('#retreatDateStart'),$('#retreatDateEnd'));
	$('#retreat').datagrid('load', serializeObject($('#retreatQueryForm')) );
}

var validateDetail=function(){
	var rows=$('#instoreDel').datagrid('getRows');
	if(rows!=null&&rows.length>0){
		$('#xs_supplier_id').combobox('disable');
	}else{
		$('#xs_supplier_id').combobox('enable');
	}
}

function _except(){
	if(tbtitle =='退厂单汇总'){
		var data =  $("#retreat").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("retreat",null,"retreat_id","开始导出","导出配置",0,_callbackExcept);
		
	}else if(tbtitle =='退厂单明细'){
		var data =  $("#instoreDel").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("instoreDel",null,"instoreDel_id","开始导出","导出配置",0,_callbackExcept2);	
	}
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"退厂单汇总"+getSystemTime());
}
function _callbackExcept2(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"退厂单明细"+getSystemTime());
}

function sellSendBackCarPrint(){
	 var selected=$('#retreat').datagrid('getSelected')
  if(selected!=''&&selected!=null){
	    window.open(projectPath+'sell/carback/sellSendBackCarPrintReport.jsp?retreatId='+selected.retreatId,'demo',"fullscreen=1")
	}else{
		 $.messager.alert('优亿软件提示','请选择要打印的退厂单记录！','warning');
	}
}