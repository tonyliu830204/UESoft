var person;
var tbtitle;
$(function(){

	$('#RetreatDateEnd').val(getSystemTime());
		 getStartDate($('#retreatDateStart'));
	
	person=$('#www').val();
	//页面初始化时，设置按钮的状态
	$('#tt').tabs({   
		onSelect:function(title){
		tbtitle=title;
			if(title =='移库单汇总'){
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					enableBtn();
				}
			}else if(title =='移库单明细'){
				//disableBtn();		
			}
	    }   
	});
			//移库单汇总
			$('#instoreMove').datagrid({
			url:'${pageContext.request.contextPath}/instoreMoveAction!getPager.action',
			fit:true,
			pagination : true,
			fitColumns : true,
			sortOrder:'asc',
		    sortName:'id',
			singleSelect : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			 columns : [ [{
					title : '移仓记录编号',
					field : 'retreatId',
					width : 50,
					hidden:true
				}, {
					title : '移库单号',
					field : 'retreatCode',
					width : 50
				},{
					title : '入库明细编号',
					field : 'detailsId',
					hidden:true
				}, {
					title : '现在库编号',
					field : 'inInstorehouse',
					hidden:true
				}, {
					title : '现在库',
					field : 'house',
					width : 50
				}, {
					title : '原来库编号',
					field : 'outInstorehouse',
					width : 50,
					hidden:true
				}, {
					title : '移库日期',
					field : 'retreatDate',
					width : 50
				}, {
					title : '经办人',
					field : 'person',
					width : 50,
					hidden:true
				},
				 {
					title : '经办人',
					field : 'personName',
					width : 50
				},{
					title : '移库理由',
					field : 'context',
					width : 50
				},{
					title : '审核状态编号',
					field : 'examine',
					hidden:true
				},{
					title : '审核状态',
					field : 'examineType',
					width : 50
				},{
					title : '库存类型',
					field : 'instorehouseType',
					hidden:true
				},{
					title : '企业编号',
					field : 'enterprise_id',
					width : 100,
					hidden:true
				}
		        ]],
	        onDblClickRow:function(rowIndex, rowData){  
					//双击出库单汇总某条记录
				$('#tt').tabs('select','移库单明细');
				$('#instoreMoveForm').form('load',rowData);
				var  parentId=rowData.retreatCode;
				loadInstoreDel(parentId);
				
		}
	});		
			//移库单明细
			$('#instoreMoveDel').datagrid({
				 fit:true,
				 pagination : true,
				 fitColumns : true,
				 idField : 'retreatId',
				 singleSelect : true,
				 pageSize : 20,
				 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				 rownumbers : true,
				 loadMsg : "数据加载中，请稍后……",
				 columns : [ [  {
						title : '入库单编号',
						field : 'instorehouseId',
						hidden:true
				    }, {
						title : '仓库编号',
						field : 'warehouse',
						hidden:true
					}, {
						title : '入库单明细编号',
						field : 'detailsId_',
						hidden:true
					}, {
						title : '车档案编号',
						field : 'carId',
						hidden:true
					},{
						title : '车辆编号',
						field : 'carCode',
						width : 100
					}, {
						title : 'VIN编号',
						field : 'carVinNumber',
						width : 100		
					}, {
						title : 'OCN码',
						field : 'carOcn',
						width : 100		
					},{
						title : '车辆品牌编号',
						field : 'carBrand',
						hidden:true
					},{
						title : '车辆品牌',
						field : 'carBrandName',
						hidden:true
					},{
						title : '车辆型号编号',
						field : 'carModelId',
						hidden:true	
					},{
						title : '车辆型号',
						field : 'carModelName',
						width : 100
					},{
						title : '车辆颜色编号',
						field : 'carColor',
						hidden:true	
					},{
						title : '车辆颜色',
						field : 'colorName',
						width : 100
					}
				        ]]
			   });

});
//车辆档案增加
var slo_d4;
function add_Foreordain()
{	var outInstore=$('#outInstorehouse').combo('getValue');
	if(outInstore!=null && ""!=outInstore){
		 slo_d4 = $('<div/>');
	  	 slo_d4.dialog({
				title: '车辆选择',   
				width: 900,
			    height:530,
			    cache: false,
			    modal: true,
			    href: projectPath+'sell/moveStore/add_instorehouse.jsp?outHouse='+outInstore,
				onClose : function (){
			    	$(this).dialog('destroy');
			    }
		});
	}else{
		alert("请选择原来仓库!");
	}
  
}
//按钮区域增加 保存和取消按钮
function  addButton(i){
	if(i==1){
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveStOutMain();">保存</a>';
	}else if(i==2){
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="update();">保存</a>';
	}
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
	$('#outInstorehouse').combobox('enable');
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
	$('#tt').tabs('select',0);
	$('#saveOrCancelBtn').empty();
	enableBtn();
	$('#instoreOut').datagrid('unselectAll');
	$('#instoreOutForm').form('clear');  
}

//根据入库单汇总某条记录加载关联的入库单明细信息
function loadInstoreDel(parentId){
	$('#instoreMoveDel').datagrid({
					url : 'instoreMoveAction!getPagerDel.action',
					queryParams: {retreatCode:parentId},
					pagination : true
					,onLoadSuccess:function(data){
						validateDetail();
					}
				});
}
//添加按钮事件
function addPersonnel(i) { 
	
   if(i==1){	
	   $('#tt').tabs('select','移库单明细');
		$('#addForeordain').linkbutton('enable');
		$('#deleteForeordain').linkbutton('enable');
		$('#_print').linkbutton('disable');
		$('#_export').linkbutton('disable');
		$('#_import').linkbutton('disable');
		addButton(1);
		$('#instoreMoveForm').form('clear');
		//每次新增时清空入库单明细表中的数据
		disableBtn();
		$('#www').combobox('select',person);
		$('#re').val(getSystemTime()); 
		
		$('#instoreMoveDel').datagrid('loadData',{total:0,rows:[]});
	}else if(i==2){
		var data = $('#instoreMove').datagrid('getSelected');
		if(data){
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url:'${pageContext.request.contextPath}/instoreMoveAction!isRefundment.action',   
			   data:data,
			   success: function(r){
				 if(r.success){
					 if(r.obj==true){
						 alertMsg('该条记录已被审核过,不能修改！', 'warning');
						 return;
					 }
					 $('#tt').tabs('select','移库单明细');
					$('#addForeordain').linkbutton('enable');
					$('#deleteForeordain').linkbutton('enable');
					addButton(2);
					disableBtn();
					$('#_print').linkbutton('disable');
					$('#_export').linkbutton('disable');
					$('#_import').linkbutton('disable');
					$('#instoreMoveForm').form('load',data);
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

function  update(){
	if($('#instoreMoveForm').form('validate')){
		  var effectRow = new Object();
		  if($("#instoreMoveDel").datagrid('getChanges').length) {
				var inserted = $("#instoreMoveDel").datagrid('getChanges', 'inserted');
				var deleted = $("#instoreMoveDel").datagrid('getChanges', 'deleted');
				if(inserted){
					effectRow['inserted'] = JSON.stringify(inserted);
				}
				if(deleted){
					effectRow['deleted'] = JSON.stringify(deleted);
				}
				
		  	}
		  effectRow['sellRetreat'] = JSON.stringify(serializeObject($('#instoreMoveForm')));
		  
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url:'${pageContext.request.contextPath}/instoreMoveAction!updateInstoreMove.action',   
			   data:effectRow,
			   success: function(r){
				 if(r.success){
					  cancel();
					  //$('#tt').tabs('select','移库单汇总');
					 $('#instoreMove').datagrid('load');
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
	if($('#instoreMoveForm').form('validate')){
		   var rows=$("#instoreMoveDel").datagrid('getRows');
		   if(!(rows!=null&&rows.length>0)){
			   alertMsg('缺少移库信息，无法保存！', 'warning');
			   return ;
		   }
		  var effectRow = new Object();
		  effectRow['instoreDelGrid'] =  JSON.stringify(rows);
		  effectRow['sellRetreat'] = JSON.stringify(serializeObject($('#instoreMoveForm')));
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url:'${pageContext.request.contextPath}/instoreMoveAction!saveInstoreMove.action',   
			   data:effectRow,
			   success: function(r){
				 if(r.success){
					  cancel();
					 // $('#tt').tabs('select','移库单汇总');
					 $('#instoreMove').datagrid('load');
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
	var data = $('#instoreMove').datagrid('getSelected');
	 var index=findSelectRowIndex('instoreMove',data);
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
						   url: 'instoreMoveAction!deleteInstoreMove.action',
						   data: data,
						   success: function(r){
							 if(r.success){
								  $('#instoreMove').datagrid('clearSelections');
								  $('#instoreMove').datagrid('reload');
								  $('#instoreMoveDel').datagrid('reload');
								  setSelectRow('instoreMove',index);
								 
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
	var data = $('#instoreMoveDel').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				 var index= $('#instoreMoveDel').datagrid('getRowIndex',data);
				 $('#instoreMoveDel').datagrid('deleteRow', index);
				 validateDetail();
			}
		});
		}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}

function examineState(){
	var data = $('#instoreMove').datagrid('getSelected');
	if(data){
		$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'${pageContext.request.contextPath}/instoreMoveAction!isRefundment.action',   
		   data:data,
		   success: function(r){
			 if(r.success){
				 if(r.obj==true){
					 alertMsg('该条记录已被审核过！', 'warning');
					 return;
				 }else{
					 $.messager.confirm('优亿软件提示', '请确认是否要审核该条记录？', function(r){
						 if(r){
						 $.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'instoreMoveAction!examine.action',
						   data:data,
						   success: function(r){
							 if(r.success){
								 $('#instoreMove').datagrid('reload');
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
	addInitDate($('#retreatDateStart'),$('#RetreatDateEnd'));
	$('#instoreMove').datagrid('unselectAll');
	$('#instoreMove').datagrid('load', serializeObject($('#moveQueryForm')) );
	
}
function clearSearchCondition(){
	$('#moveQueryForm').form('clear');
	$('#instoreMove').datagrid('load', serializeObject($('#moveQueryForm')) );
	addInitDate($('#retreatDateStart'),$('#RetreatDateEnd'));
	
	
}


function _except(){

	if(tbtitle =='移库单汇总'){
		var data =  $("#instoreMove").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("instoreMove",null,"instoreMove_div","开始导出","导出配置",0,_callbackExcept);
		
	}else if(tbtitle =='移库单明细'){
		var data =  $("#instoreMoveDel").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("instoreMoveDel",null,"instoreMoveDel_div","开始导出","导出配置",0,_callbackExcept2);	
	}
	
	
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"移库单汇总"+getSystemTime());
}
function _callbackExcept2(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"移库单明细"+getSystemTime());
}

function _config(){
	if(tbtitle =='移库单汇总'){	
		var data =  $("#instoreMove").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
		showEditDialog("instoreMove",personnelSumTable,"instoreMove_div","开始打印","打印配置",2,_print);
	}else  if(tbtitle =='移库单明细'){
		var data =  $("#instoreMoveDel").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
		showEditDialog("instoreMoveDel",personnelSumTable,"instoreMoveDel_div","开始打印","打印配置",2,_print);

	}
	}

	function _print(parentId,fieldNames){
		printEsuyUIPreview(parentId,fieldNames);
	}
var validateDetail=function(){
	var rows=$('#instoreMoveDel').datagrid('getRows');
	if(rows!=null&&rows.length>0){
		$('#outInstorehouse').combobox('disable');
	}else{
		$('#outInstorehouse').combobox('enable');
	}
}