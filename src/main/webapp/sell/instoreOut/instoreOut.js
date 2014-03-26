var  parentId;
var stfName;
var zhProjectPerson;
var tbtitle;
$(function(){
	$('#retreatDateEnd').val(getSystemTime());
	 getStartDate($('#retreatDateStart'));

	
	
	stfName=$('#stfName').val();
    person=$('#zhPerson').val();
		$('#sellCode').validatebox({  
	        required:true,
	        missingMessage:'请选择销售单信息！'
	}); 
		
	//页面初始化时，设置按钮的状态
	$('#tt').tabs({   
		onSelect:function(title){  
		tbtitle=title;
			if(title =='出库单汇总'){
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					enableBtn();
				}
			}else if(title =='出库单明细'){
		//	disableBtn();		
			}
	    }   
	});
			//出库单汇总
			$('#retreat').datagrid({
			url:'${pageContext.request.contextPath}/instoreOutAction!getPager.action',
			fit:true,
			pagination : true,
			fitColumns : true,
			sortOrder:'asc',
		    sortName:'retreatDate',
			singleSelect : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			 columns : [ [{
					title : '出库记录编号',
					field : 'retreatId',
					sortable : true,
					hidden:true
				},{
					title : '出库单号',
					field : 'retreatCode',
					width : 200,
					sortable : true
				}, {
					title : '出库日期',
					field : 'retreatDate',
					width : 100,
					sortable : true
				}, {
					title : '出库理由',
					field : 'context',
					width : 300,
					sortable : true
				},{
					title : '审核状况',
					field : 'examine',
					width : 100,
					sortable : true,
					hidden:true
				},
				{
					title : '审核状况',
					field : 'examineType',
					width : 100,
					sortable : true
				},{
					title : '经办人',
					field : 'person',
					hidden:true
				},
				{
					title : '经办人',
					field : 'personName',
					width : 130,
					sortable : true
					
				},
				{
					title : 'VIN号',
					field : 'carVinNumber',
					width :125,
					sortable : true
				},
				{
					title : '入库明细编号',
					field : 'detailsId',
					width :70,
					sortable : true,
					hidden:true
				},
				{
					title : '车辆销售编号',
					field : 'xs_Car_Sel_Id',
					width : 100,
					sortable : true,
					hidden:true
					
				},
				{
					title : '车辆销售单号',
					field : 'sellCode',
					width : 100,
					sortable : true
					
				},
				{
					title : '车辆编号',
					field : 'carCode',
					width : 100,
					sortable : true
			
				},
				{
					title : '车档案编号',
					field : 'carId',
					width : 120,
					hidden:true
					
				},{
					title : '企业编号',
					field : 'enterprise_id',
					width : 100,
					hidden:true
				}
		        ]],
	        onDblClickRow:function(rowIndex, rowData){  
					//双击退车单汇总某条记录
				$('#tt').tabs('select','出库单明细');
				$('#retreatForm').form('load',rowData);
				 parentId=rowData.carId;
				loadInstoreDel(parentId);	
		}
	});
			
			//退车单明细
			$('#instoreDel').datagrid({
				fit:true,
				 pagination : true,
				 fitColumns : true,
				 idField : 'carId',
				 sortOrder:'asc',
				 sortName:'carId',
				 singleSelect : true,
				 pageSize : 20,
				 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				 rownumbers : true,
				 loadMsg : "数据加载中，请稍后……",
				 columns : [ [  {
						title : '车辆档案编号',
						field : 'carId',
						width : 100,
						//hidden:true
						sortable : true,
						hidden:true
				    },
				    {
						title : '车辆编号',
						field : 'carCode',
						width : 150,
						sortable : true
				    },{
						title : '车辆品牌 ',
						field : 'carBrand',
						width : 100,
						hidden:true
				    },{
						title : '车辆品牌 ',
						field : 'carBrandName',
						width : 100,						
				    },{
						title : '车辆颜色',
						field : 'carColor',
						width : 100,
						hidden:true
				    },
				    {
						title : '车辆颜色',
						field : 'colorName',
						width : 100,
						sortable : true
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
				    }
				        ]]
			   });

});
//车辆档案增加
var slo_d4;
function add_Foreordain()
{
  	 slo_d4 = $('<div/>');
  	 slo_d4.dialog({
				title: '车辆选择',   
				width: 900,
			    height:530,
			    cache: false,
			    href: projectPath+'sell/instoreOut/add_instoreDel.jsp',
			    modal: true,
				onClose : function (){
			    	$(this).dialog('destroy');
			    }
		});
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

}
//禁用按钮
function disableBtn(){
	$('#_add').linkbutton('disable');
	$('#_remove').linkbutton('disable');
	$('#_update').linkbutton('disable');
	$('#_search').linkbutton('disable');
	$('#_clear').linkbutton('disable');
	$('#_examine').linkbutton('disable');
	$('#_examine').linkbutton('disable');
	$('#_print').linkbutton('disable');
	$('#_export').linkbutton('disable');
	//$('#addForeordain').linkbutton('disable');
	//$('#deleteForeordain').linkbutton('disable');	
}
//启用控件
function nuDisAbledControl(num){
	$("#carSellImg").unbind();
	if(num==1){
		$("#carSellImg").bind("click",addCarSel);
	}
}
	

//点击取消按钮执行的操作
function cancel() {
	$('#tt').tabs('select',0);
	$('#saveOrCancelBtn').empty();
	enableBtn();
	$('#ForeordainTable').datagrid('unselectAll');
	$('#StPurOrderForm').form('clear');  
}

//根据退车单汇总某条记录加载关联的退车单明细信息
function    loadInstoreDel(parentId){
	$('#instoreDel').datagrid({
					url : 'instoreOutAction!getCarInfo.action',
					queryParams: {carId:parentId},
					pagination : true
				});
}
//添加按钮事件
function addRetreat(i) { 
	
   if(i==1){	
	   $('#tt').tabs('select','出库单明细');
		$('#addForeordain').linkbutton('enable');
		$('#deleteForeordain').linkbutton('enable');
		 nuDisAbledControl(1);
		 disableBtn();
		addButton(1);
		$('#retreatForm').form('clear');
		$('#retreatDate').val(getSystemTime());
		$('#stfName').val(stfName);
		$('#zhPerson').val(person);
		//每次新增时清空入库单明细表中的数据
		$('#instoreDel').datagrid('loadData',{total:0,rows:[]});
	}else if(i==2){
		var data = $('#retreat').datagrid('getSelected');
		if(data){
				 $.ajax({
						type : 'POST',
					    dataType : 'json',
						url : 'instoreOutAction!isRefundment.action',
						data : 'examine='+data.examine,
						success : function(r){
							if(r.success){
								if(r.obj==true){
									alertMsg('该出库单已审核，不能修改！', 'warning');
									return;							
								}else{
							 	$('#tt').tabs('select','出库单明细');
								$('#addForeordain').linkbutton('enable');
								$('#deleteForeordain').linkbutton('enable');
								addButton(2);
								disableBtn();
								nuDisAbledControl();
								$('#retreatForm').form('load',data);
								loadInstoreDel(data.carId);
								}
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
		//  endEdit($("#instoreDel"));    
		  var effectRow = new Object();
				effectRow['sellRetreat'] = JSON.stringify(serializeObject($('#retreatForm')));	  
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url:'${pageContext.request.contextPath}/instoreOutAction!updateInstore.action',   
				   data:effectRow,
				   success: function(r){
					 if(r.success){
						  cancel();
						  //$('#tt').tabs('select','出库单汇总');
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
			   url:'${pageContext.request.contextPath}/instoreOutAction!saveInstore.action',   
			   data:effectRow,
			   success: function(r){
				 if(r.success){
					  cancel();
					 // $('#tt').tabs('select','出库单汇总');
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
       }
	}
	
//删除
function removeRetreat(){
	var data = $('#retreat').datagrid('getSelected');
	  var index=findSelectRowIndex('retreat',data);
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'instoreOutAction!deleteInstore.action',
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
			}
		});
		}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}
//审核
function examine_(){
	var data = $('#retreat').datagrid('getSelected');
	if(data){
		 $.ajax({
				type : 'POST',
			    dataType : 'json',
				url : 'instoreOutAction!isRefundment.action',
				data : 'examine='+data.examine,
				success : function(r){
					if(r.success){
						if(r.obj==true){
							alertMsg('该出库单已审核，不能取消审核！', 'warning');
							return;							
						}else{
							$.messager.confirm('优亿软件提示', '请确认是否执行此操作？', function(r){
						if(r){
							$.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: 'instoreOutAction!examine.action',
								   data:{retreatId:data.retreatId},
								   success: function(r){
									 if(r.success){
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

var queryRetreat = function (){
	$('#retreat').datagrid('unselectAll');
	$('#retreat').datagrid('load', serializeObject($('#retreatQueryForm')) );
	addInitDate($('#retreatDateStart'),$('#retreatDateEnd'));
	

}
function clearSearchCondition(){
	$('#retreatQueryForm').form('clear');
	$('#retreat').datagrid('load', serializeObject($('#retreatQueryForm')) );
	addInitDate($('#retreatDateStart'),$('#retreatDateEnd'));


}
function _except(){

	if(tbtitle =='出库单汇总'){
		var data =  $("#retreat").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("retreat",null,"retreat_id","开始导出","导出配置",0,_callbackExcept);
		
	}else if(tbtitle =='出库单明细'){
		var data =  $("#instoreDel").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("instoreDel",null,"instoreDel_id","开始导出","导出配置",0,_callbackExcept2);	
	}
	
	
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"出库单汇总"+getSystemTime());
}
function _callbackExcept2(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"出库单明细"+getSystemTime());
}
function outstorehousePrint(){
	 var selected=$('#retreat').datagrid('getSelected')
   if(selected!=''&&selected!=null){
  	    window.open(projectPath+'sell/instoreOut/outstorehousePrintReport.jsp?outId='+selected.retreatId,'demo',"fullscreen=1")
	}else{
		 $.messager.alert('优亿软件提示','请选择要打印的出库单记录！','warning');
	}
}
