var  parentId;
var dbProjectPerson;
$(function(){
	//初试时间
	addInitDate($('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));
	
	dbProjectPerson=$('#dbProjectPerson').val();
	//页面初始化时，设置按钮的状态
	$('#tt').tabs({   
		onSelect:function(title){  	
			if(title=='销售单汇总'){
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					enableBtn();
					mytitle = title;
				}
			}else if(title=='代办项目'){
				//disableBtn();	
				mytitle = title;
			}
	    }   
	});
	//销售单汇总
	$('#sellInfo').datagrid({
		url : 'sellDbProjectAction!findSellInfor.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		singleSelect : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'xs_Car_Sel_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'sellCode',
			title : '销售单号',
			width : 100,
			sortable : true
			
		},{
			field : 'out_Id',
			title : '出库单号',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'reserve_Code',
			title : '预订单编号',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'xs_Car_Sel_Data',
			title : '销售日期',
			width : 100,
			sortable : true
			
		},{
			field : 'xs_Custom_Name',
			title : '客户名称',
			width : 100,
			sortable : true
			
		},{
			field : 'stf_Name',
			title : '业务员',
			width : 100,
			sortable : true
			
		},{
			field : 'xs_Car_Vin_Number',
			title : 'VIN编号',
			width : 100,
			sortable : true
		}, {
			field : 'xs_Car_Ocn',
			title : 'OCN码',
			width : 100,
			sortable : true
			
		}, {
			field : 'xs_Car_Brand',
			title : '车品牌',
			width : 100,
			sortable : true
			
		}, {
			field : 'xs_Model_Name',
			title : '车类型',
			width : 100,
			sortable : true
			
		},{
			field : 'dbProjectCode',
			title : '代办单号',
			width : 150,
			sortable : true,
		},{
			field : 'dbProjectDate',
			title : '代办日期',
			width : 130,
			sortable : true,
		},{
			field : 'dbProjectPerson',
			title : '经办人',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'dbPersonName',
			title : '经办人',
			width : 130,
			sortable : true,
		},{
			field : 'dbExamin',
			title : '审核状态',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'examinName',
			title : '审核状态',
			width : 100,
			sortable : true,
		},{
			field : 'dbProjectReckoningN',
			title : '是否转结算',
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			if(value=="否"){
				return '<font color="red">'+value+'</font>';
			}else{
				return value;
			}
			
		}
		}, {
			field : 'xs_Car_Sel_Transaction_Money',
			title : '成交价格',
			width : 100,
			sortable : true
		}/*, {
			field : 'STF_ID_PERSON',
			title : '经办人',
			width : 100,
			sortable : true
		}*/, {
			field : 'xs_Distributor_Name',
			title : '分销商',
			width : 100,
			sortable : true
		}/*, {
			field : 'examine',
			title : '审核情况',
			width : 100,
			sortable : true
		}*/, {
			field : 'limit_load_num',
			title : '限乘人数',
			width : 100,
			sortable : true
		}, {
			field : 'mobilephone',
			title : '电话',
			width : 100,
			sortable : true
		},{
			field : 'isdb_project',
			title : '是否代办',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'dbProjectRemark',
			title : '备注',
			width : 100,
			sortable : true
		},{
			field : 'accountCode',
			title : '结算单号',
			width : 100,
			sortable : true,
			hidden:true
		}
		]],
		onDblClickRow : function(rowIndex,rowData){
		$('#tt').tabs('select','代办项目');
		parentId=rowData.xs_Car_Sel_Id;
		 loadDbPro(parentId);
		 $('#sellDbPro').form('load',rowData); 
		 disableControl();
		 
    }	
	});	
	//代办项目
	$('#dbPro').datagrid({
		fit : true,
		rownumbers : true,
		singleSelect : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : '',
		loadMsg : "数据加载中，请稍后……",
		columns : [[ {
			field : 'sellid',
			title : '编号',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'xs_Car_Sel_Id',
			title : '车辆销售信息编号',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'projectId',
			title : '代办项目',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'projectName',
			title : '代办项目名称',
			width : 100,
			sortable : true
		},{
			field : 'projectCode',
			title : '项目代码',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'projectAmount',
			title : '代办费用',
			width : 100,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
				required : true,
				missingMessage : "代办费用为必填项!",
				min:0,  
				precision:2,
				validType:'maxLength[11]'
			}
			
		 }	
		},{
			field : 'projectMomay',
			title : '成本金额',
			width : 100,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
				required : true,
				min:0, 
				precision:2,
				missingMessage : "成本金额为必填项!",
				validType:'maxLength[11]'
			}
		 }	
		},{
			field : 'dbProjectPerson',
			title : '经办人',
			width : 100,
			sortable : true,
			hidden:true
		}
		,{
			field : 'dbProjectDate',
			title : '代办日期',
			width : 100,
			sortable : true,
			hidden:true
		}
		,{
			field : 'dbProjectCode',
			title : '代办单号',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'dbExamin',
			title : '审核状态',
			width : 100,
			sortable : true,
			hidden:true
		}
		]]
	});	
});

//导出
function doExport(datagriddivid,content){
	exportEsuyUIExcelFile(datagriddivid,null,content);
}
//打印	
function doPoint(datagriddivid,content){
	printEsuyUIPreview(datagriddivid,content);
}
//导入
function doInport(dateGridId,parentId){
	showEditDialog(dateGridId,null,parentId,"开始导入","导入配置",1,null);
}
//根据退车单汇总某条记录加载关联的退车单明细信息
function    loadDbPro(parentId,key){
	$('#dbPro').datagrid({
			url : 'sellDbProjectAction!findSellDb.action',
			queryParams: {xs_Car_Sel_Id:parentId},
			pagination : true,
			onClickRow : function(rowIndex,rowData){
				if(key==1){
					$('#dbPro').datagrid('beginEdit', rowIndex);
				}		
      			
	}
	});
}
//代办项目增加
var slo_d4;
function add_Foreordain()
{
  	 slo_d4 = $('<div/>');
  	 slo_d4.dialog({
				title: '代办项目选择',   
				width: 600,
			    height:400,
			    cache: false,
			    href: projectPath+'sell/sellDbProject/add_DbProject.jsp',
			    modal: true,
				onClose : function (){
			    	$(this).dialog('destroy');
			    }
		});
}
//代办项目删除
function delete_Foreordain(){
	var data = $('#dbPro').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
					 var index= $('#dbPro').datagrid('getRowIndex',data);
					 $('#dbPro').datagrid('deleteRow', index);
					 return;
				}
			});
		}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
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
	$('#sellAcount').linkbutton('enable');
	
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
	$('#sellAcount').linkbutton('disable');
	//$('#addForeordain').linkbutton('disable');
	//$('#deleteForeordain').linkbutton('disable');
}
//点击取消按钮执行的操作
function cancel() {
		$('#tt').tabs('select',0);
		$('#saveOrCancelBtn').empty();
		$("#sellCode").validatebox({required:false});
		enableBtn();
		$('#sellDbPro').form('clear');
		$('#sellInfo').datagrid('unselectAll');
		$('#sellInfo').datagrid('load');
		$('#dbPro').datagrid('loadData',{total:0,rows:[]});
}
function addSellPro(){
		 $('#tt').tabs('select','代办项目');
		 	nuDisAbledControl(1);
		 	 disableBtn();
			$('#addForeordain').linkbutton('enable');
			$('#deleteForeordain').linkbutton('enable');
			$("#sellCode").validatebox({required:true});
			//添加保存，取消按钮
			var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
			var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
			if ($('#saveOrCancelBtn').children('a').length == 0) {
				$('#saveOrCancelBtn').append(save).append(cancel);
				$.parser.parse('#saveOrCancelBtn');
			}
			$('#sellDbPro').form('clear');
			$("#sellDbProjectDate").val(getSystemTime());
			$('#dbProjectPerson').val(dbProjectPerson);
			//每次新增时清空明细表格中的数据
			$('#dbPro').datagrid('loadData',{total:0,rows:[]});
	
	  
}
function save(){
	if($('#sellDbPro').form('validate')){
		endEdit($("#dbPro")); 
		var rows=$("#dbPro").datagrid('getRows');
		if(rows.length==0){
			  alertMsg('对不起，请添加代办项目！', 'warning');
			  return ;
		}
		var isnosubmit=true;
        for(var i=0;i<rows.length;i++){
        	var isno=$("#dbPro").datagrid('validateRow',i);
        	if(!isno){
        		isnosubmit=false;
        		break;
          }
        }
        if(isnosubmit){
        	var effectRow = new Object();
    		effectRow['sellProGrid'] =  JSON.stringify(rows);
    		effectRow['sellReserve']=JSON.stringify(serializeObject($('#sellDbPro')));
    		$.ajax({
    			   type: 'post',
    			   dataType: 'json',
    			   url:'${pageContext.request.contextPath}/sellDbProjectAction!saveSellPro.action',   
    			   data:effectRow,
    			   success: function(r){
    				 if(r.success){
    					  cancel();
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
}
//删除
function removeSellPro(){
	var data = $('#sellInfo').datagrid('getSelected');
	 var index=findSelectRowIndex('sellInfo',data);
	if(data!=null){
		 $.ajax({
				type : 'POST',
			    dataType : 'json',
				url : 'sellDbProjectAction!isRefundment.action',
				data : 'dbExamin='+data.dbExamin,
				success : function(r){
					if(r.success){
						if(r.obj==true){
							alertMsg('该代办单已审核，不能删除！', 'warning');
							return;							
						}else{
							$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
								if (r){
									$.ajax({
										type: 'post',
										dataType: 'json',
										url: 'sellDbProjectAction!deleteSellPro.action',
										data: data,
										success: function(r){
										if(r.success){
											  $('#sellInfo').datagrid('clearSelections');
											  $('#sellInfo').datagrid('reload');
											  setSelectRow('sellInfo',index);
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

function  updateSellPro(){
	var data=$('#sellInfo').datagrid('getSelected');
	if(data){
		 $.ajax({
				type : 'POST',
			    dataType : 'json',
				url : 'sellDbProjectAction!isRefundment.action',
				data : 'dbExamin='+data.dbExamin,
				success : function(r){
					if(r.success){
						if(r.obj==true){
							alertMsg('该代办单已审核，不能修改！', 'warning');
							return;							
						}else{

					$('#tt').tabs('select','代办项目');
				 	
				 	nuDisAbledControl();
				 	disableBtn();
					$('#addForeordain').linkbutton('enable');
					$('#deleteForeordain').linkbutton('enable');
					$("#sellCode").validatebox({required:true});
					//添加保存，取消按钮
					var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="update();">保存</a>';
					var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
					if ($('#saveOrCancelBtn').children('a').length == 0) {
						$('#saveOrCancelBtn').append(save).append(cancel);
						$.parser.parse('#saveOrCancelBtn');
					}
					loadDbPro(data.xs_Car_Sel_Id,1);
					 $('#sellDbPro').form('load',data); 
					}
			}
		 }
		 });
	}else{
		alertMsg('对不起，请先选中要修改的销售单汇总记录！', 'warning');
	}
}
function update(){
	var data=$('#sellInfo').datagrid('getSelected');
	endEdit($("#dbPro")); 
	var rows=$("#dbPro").datagrid('getRows');
	if(rows.length==0){
		  alertMsg('对不起，请添加代办项目！', 'warning');
		  return ;
	}
	var isnosubmit=true;
	for(var i=0;i<rows.length;i++){
		var isno=$("#dbPro").datagrid('validateRow',i);
		if(!isno){
			isnosubmit=false;
			break;
	  }
	}
	var effectRow = new Object();
	 if($("#dbPro").datagrid('getChanges').length) {
			var inserted = $("#dbPro").datagrid('getChanges', 'inserted');
			var deleted = $("#dbPro").datagrid('getChanges', 'deleted');
			var updated = $("#dbPro").datagrid('getChanges', 'updated');
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
	effectRow['sellReserve']=JSON.stringify(serializeObject($('#sellDbPro')));
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'${pageContext.request.contextPath}/sellDbProjectAction!updateSellPro.action',   
		   data:effectRow,
		   success: function(r){
			 if(r.success){
				  cancel();
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
/**审核事件**/
/*function  examine_(){
	 var selected = $('#sellInfo').datagrid('getSelected');
     if(selected!=''&&selected!=null){
         if(selected.accountCode==null||selected.accountCode==''){
        	 var examine=selected.examinName;
		     pos = examine.indexOf('未审核');  
		     if (pos == -1) {
		    	 $.messager.confirm('优亿软件提示', '请确认是否要取消审核该代办单？', function(r){
		              if(r)
		            	  examineState(selected);
        		 });
		     }else{
		    	 $.messager.confirm('优亿软件提示', '请确认是否要审核该代办单？', function(r){
		               if(r)
		            	   examineState(selected);
        		 });
		     }
         }else
        	 $.messager.alert('优亿软件提示', '对不起，该代办单已经被结算，不允许取消审核，请先删除结算单号为【'+selected.accountCode+'】的结算单后再执行本操作！', 'info', function() {});
     }else
		 $.messager.alert('优亿软件提示','对不起，请先选中要审核的记录！','warning',function(){});	
 }
*/
//审核
function examine_(){
	 var selected = $('#sellInfo').datagrid('getSelected');
     if(selected!=''&&selected!=null){
    	 $.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'sellDbProjectAction!isUseDocument.action',
			   data:selected,
			   success: function(r){
				 if(r.success){
					 if(r.obj==true){
						 alertMsg('对不起，此代办单已被结算,不能取消审核！', 'warning'); 
					 }else{
				$.messager.confirm('优亿软件提示', '请确认是否执行此操作？', function(r){
					if(r){
				$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'sellDbProjectAction!updateExamin.action',
					   data: selected,
					   success: function(r){
						 if(r.success){
							 $('#sellInfo').datagrid('load');
						 }else{
							 alertMsg(r);
						 }
					   },error : function (r){
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

//清空
function clearSearchCondition(){
	$('#sellDbQueryForm').form('clear');
	querySellPro();
}
//查询
var querySellPro = function() {
	$('#sellInfo').datagrid('unselectAll');
	$('#sellInfo').datagrid('load', serializeObjectByflag($('#sellDbQueryForm'),false));
	addInitDate($('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));
}
//禁用控件
function disableControl(){
	$("#carSellImg").unbind();
	$("#sellDbProjectDate").prop("disabled", true);
	$("#dbProjectRemark").prop("disabled", true);
}
//启用控件
function nuDisAbledControl(key){
	$("#carSellImg").unbind();
	if(key==1){
		$("#carSellImg").bind("click", addCarSel); 
	}
	$("#sellDbProjectDate").prop("disabled", true);
	$("#dbProjectRemark").prop("disabled", false);
}
//转结算
function sellAcount(){
	var data = $('#sellInfo').datagrid('getSelected');
	if(data!=null){
		 $.ajax({
				type : 'POST',
			    dataType : 'json',
				url : 'sellDbProjectAction!isNotRefundment.action',
				data : 'dbExamin='+data.dbExamin,
				success : function(r){
					if(r.success){
						if(r.obj==true){
							alertMsg('该代办单未被审核，不能转结算！', 'warning');
							return;							
						}else{
			if(data.accountCode!=null&&data.accountCode!=''){
				  $.messager.alert('优亿软件提示', '该代办单已被转结算！', 'warning', function() {});
				  return ;
			}
			$.messager.confirm('优亿软件提示', '请确认是否要转结算？', function(r){
				if (r){
					$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'sellDbProjectAction!updateSellAcount.action',
						   data: data,
						   success: function(r){
							 if(r.success){
								 alertMsg('转结算成功！', 'info');
								 $('#sellInfo').datagrid('load');
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
			alertMsg('对不起，请先选中要转结算的记录！', 'warning');
		 }
	
}
function doThisExport(){
	if(mytitle =='销售单汇总'){
		var data =  $('#sellInfo').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("sellInfo",null,"sellInfo_div_id","开始导出","导出配置",0,_callbackExcept);
		
	}else{
		var data =  $('#dbPro').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("dbPro",null,"dbPro_div_id","开始导出","导出配置",0,_callbackExcept2);	
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
	exportEsuyUIExcelFile(parentId,fieldNames,"销售单汇总"+getSystemTime());
}
function _callbackExcept2(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"代办项目"+getSystemTime());
}

/**
 * 打印字段设置
 * 编辑、选择不同分组
 * @return
 */
function doThisPoint(){
	
	if(mytitle =='销售单汇总'){
		var data =  $('#sellInfo').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
		showEditDialog("sellInfo",personnelSumTable,"sellInfo_div_id","开始打印","打印配置",2,_print);
		
	}else{
		var data =  $('#dbPro').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
		showEditDialog("dbPro",personnelSumTable,"dbPro_div_id","开始打印","打印配置",2,_print);	
	}
	
}
/**
 * 打印字段设置回调函数
 * 将选择的列打印
 * @return
 */
function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}
