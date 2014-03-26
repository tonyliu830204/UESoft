var  parentId;
var stfName;
var zhProjectPerson;
$(function(){
	//初试时间
	addInitDate($('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));
	
	stfName=$('#stfName').val();
	zhProjectPerson=$('#zhPerson').val();
		$('#sellCode').validatebox({  
	        required:true,
	        missingMessage:'请选择销售单信息！'
	});  
		
	// 页面初始化时，设置按钮的状态
	$('#tt').tabs({   
		onSelect:function(title){  	
		tbtitle=title;
			if(title=='装潢项目汇总'){
				
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					enableBtn();
				}
			}else if(title=='装潢项目明细'){
				//disableBtn();		
			}
	    }   
	});
	
	
	// 装潢项目汇总
	$('#sellInfo').datagrid({
		url : 'sellZhProjectAction!getsellCar.action?iszhProject=true',
		
		pagination : true,
		fit : true,
		rownumbers : true,
		singleSelect : true,
		
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'zhProjectCode',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'zhProjectCode',
			title : '装潢单号',
			width : 150,
			sortable : true
			
		},
		{
			field : 'zhProjectDate',
			title : '装潢日期',
			width : 90,
			sortable : true
			
		},
		   {
			field : 'sellCode',
			title : '销售单号',
			width : 100,
			sortable : true
			
		},{
			field : 'xs_Car_Sel_Data',
			title : '销售日期',
			width : 90,
			sortable : true
			
		},{
			field : 'xs_Custom_Name',
			title : '客户名称',
			width : 100,
			sortable : true
			
		}, {
			field : 'mobilephone',
			title : '电话',
			width : 100,
			sortable : true
		},{
			field : 'stfName',
			title : '经办人',
			width : 100,
			sortable : true
			
		},
		{
			field : 'zhProjectPerson',
			title : '经办人id',
			width : 100,
			hidden:true,
			sortable : true
			
			
		},
		{
			field : 'auditName',
			title : '审核状态',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			if(value=="未审核"){
				return '<font color="red">'+value+'</font>';
			}else{
				return value;
			}
			
		}
			
		},
		{
			field : 'audit',
			title : '审核状态',
			width : 100,
			hidden:true,
			sortable : true
			
		},
		{
			field : 'reckoningName',
			title : '是否转结算',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			if(value=="否"){
				return '<font color="red">'+value+'</font>';
			}else{
				return value;
			}
			
		}
			
		},
		{
			field : 'zhProjectReckoning',
			title : '是否转结算',
			width : 100,
			hidden:true,
			sortable : true
			
		},
		
		{
			field : 'xs_Car_Vin_Number',
			title : 'VIN编号',
			width : 130,
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
			
		}, {
			field : 'xs_Car_Sel_Transaction_Money',
			title : '成交价格',
			width : 100,
			sortable : true
		},
		{
			field : 'zhProjecRemark',
			title : '装潢备注',
			width : 100,
			sortable : true
		},/*{
			field : 'xs_Car_Give_Up',
			title : '购车状态',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			if(value=="0"){
				return '正常';
			}else{
				return '已取消购车';
			}
			
			}
			
		},*/{
			field : 'states',
			title : '转收银',
			width : 100,
			sortable : true,
			hidden:true
		},
		{
			field : 'accountCode',
			title : '结算单号',
			width : 100,
			sortable : true,
			hidden:true
		}
		
		
		]],
		onDblClickRow : function(rowIndex,rowData){
		$('#tt').tabs('select','装潢项目明细');
		 loadZhList(rowData.xs_Car_Sel_Id);
		 $('#StForm').form('load',rowData); 
		 $("#carSellImg").unbind();
    }	
	});	
	// 装潢项目
	$('#zhList').datagrid({
		fit : true,
		rownumbers : true,
		singleSelect : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'zhid',
		loadMsg : "数据加载中，请稍后……",
		columns : [[ {
			field : 'zhid',
			title : '编号',
			width : 50,
			sortable : true,
			
		},
		{
			field : 'zsprojectId',
			title : '项目编号',
			width : 50,
			sortable : true,
			hidden:true
		},
		{
			field : 'projectName',
			title : '项目名称',
			width : 100,
			sortable : true
		},
		{
			field : 'unitNum',
			title : '件数',
			width : 60,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
			  	required : true,
				min:0,
				validType:'length[1,8]' 
			}
		 }	
		},{
			field : 'projectCostamount',
			title : '成本价',
			width : 70,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
			min : 0.00,
			precision : 2,
			validType:'multiple[\'monery\',\'length[0,12]\']'
			}
		 }	
		},{
			field : 'projectSellamount',
			title : '销售价',
			width : 70,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
			min : 0.00,
			precision : 2,
			validType:'multiple[\'monery\',\'length[0,12]\']'
			}
		 }	
		},
		{
			field : 'preferentialPrice',
			title : '优惠价',
			width : 70,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
			  required : true,
			  min : 0.00,
			  precision : 2,
			  validType:'multiple[\'monery\',\'length[0,12]\']'
			}
		 }	
		},
		{
			field : 'decorateAmount',
			title : '装潢成本',
			width :70,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
				disabled : true,
				precision : 2,
				min : 0.00,
				
			}
		 }	
		},
		{
			field : 'decorateSell',
			title : '装潢销售',
			width : 70,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
				disabled : true,
				precision : 2,
				min : 0.00 
			}
		 }	
		} ,
		{
			field : 'zhRemark',
			title : '类型',
			width : 70,
			sortable : true,
			formatter : function (value,row,index){
			return row.remark;
	    	 },
			editor : {
	    		 	
					type : 'combobox',
					options : {
						url : 'baseTagAction!findBaseData.action?baseKey=zhRemark',
						mode : 'remote',
						valueField:'id',  
					    textField:'text',
					    required : true
				}
			}
		}
		]]

	});	
});
function copyPartsDateAndBindEvent(id, rowIndex, rowData)
{
	
	id.datagrid('beginEdit', rowIndex);
			var ed = id.datagrid('getEditors', rowIndex);
			if(ed[0]){
				ed[0].target.numberbox('setValue', rowData.unitNum);
				ed[0].target.select();
				ed[0].target.click(function (){
					ed[0].target.select();
				});
				ed[0].target.keydown(function (e){
					if(e.keyCode == '13'){
						ed[1].target.select();
					}
				});
			}
			 ed[0].target.select();
			 	if(ed[1]){
				ed[1].target.numberbox('setValue', rowData.projectCostamount);
				ed[1].target.select();
				ed[1].target.click(function (){
					ed[1].target.select();
				});
				ed[1].target.keydown(function (e){
					if(e.keyCode == '13'){
						ed[2].target.select();
					}
				});
			}
			if(ed[2]){
				ed[2].target.numberbox('setValue', rowData.projectSellamount);
				ed[2].target.select();
				ed[2].target.click(function (){
					ed[2].target.select();
				});
				ed[2].target.keydown(function (e){
					if(e.keyCode == '13'){
						ed[3].target.select();
					}
				});
			}
			if(ed[3]){
				 ed[3].target.numberbox('setValue', rowData.preferentialPrice);
						ed[3].target.select();
    				ed[3].target.click(function (){
    					ed[3].target.select();
    				});
    				ed[3].target.keydown(function (e){
    					if(e.keyCode == '13'){
    						if(rowIndex < id.datagrid('getData').total - 1){
    							var ed = id.datagrid('getEditors', rowIndex + 1);
    							ed[0].target.select();
    						}else{
    							var ed = id.datagrid('getEditors', 0);
    							ed[0].target.select();
    						}
    					}
					
				 });
			}
			ed[0].target.select();
			ed[0].target.bind('keyup', function() {
				var unitNum = ed[0].target.val();//
				if(unitNum==null||unitNum==''||isNaN(unitNum)){
					ed[0].target.numberbox('setValue', '1');
					unitNum=1;
				}
				var projectCostamount = ed[1].target.val();// 代表成本价
				ed[4].target.numberbox('setValue', accMul(parseFloat(projectCostamount),parseFloat(unitNum)));
				var projectCostamount = ed[3].target.val();// 优惠价
				ed[5].target.numberbox('setValue', accMul(parseFloat(unitNum),parseFloat(projectCostamount)));	
				
			});	
			ed[1].target.bind('keyup', function() {
				var num = ed[0].target.val();
				var price = ed[1].target.val();
				var amount = accMul(parseFloat(num), parseFloat(price));
				ed[4].target.numberbox('setValue', amount);
			});	
			ed[3].target.bind('keyup', function() {
				var num = ed[0].target.val();
				var price = ed[3].target.val();
				var amount = accMul(parseFloat(num), parseFloat(price));
				ed[5].target.numberbox('setValue', amount);
			});		
}



// 根据汇总某条记录加载关联的明细信息
function    loadZhList(parentId,key){
	$('#zhList').datagrid({
					url : 'sellZhProjectAction!getSellZhlist.action',
					queryParams: {xs_Car_Sel_Id:parentId},
					pagination : true,
					onClickRow : function(rowIndex,rowData){
						if(key==1){
						
							var index = $("#zhList").datagrid('getRowIndex', rowData);
							copyPartsDateAndBindEvent($("#zhList"), index, rowData);	
						}	
					}
				});
}
// 装潢项目增加
var slo_d4;
function add_Foreordain()

{
	var id=$("#xs_Car_Sel_Id").val();
	if(id==null || id=="" || id==undefined){
		$.messager.alert('提示','请先选择销售单信息！','warning');
		return ;
	}
  	 slo_d4 = $('<div/>');
  	 slo_d4.dialog({
				title: '装潢项目选择',   
				width: 650,
			    height:480,
			    cache: false,
			    href: projectPath+'sell/sellDecorateProject/add_decorateProject.jsp',
			    modal: true,
				onClose : function (){
			    	$(this).dialog('destroy');
			    }
		});
}
// 装潢项目删除
function delete_Foreordain(){
	var data = $('#zhList').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
					 var index= $('#zhList').datagrid('getRowIndex',data);
					 $('#zhList').datagrid('deleteRow', index);
					 return;
				}
			});
		}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}

// 装潢项目增加
function addSellPro(){
	 $('#tt').tabs('select','装潢项目明细');
	 nuDisAbledControl(1);
	 	disableBtn();

	 	$('#_print').linkbutton('disable');
		$('#_export').linkbutton('disable');
		$('#_import').linkbutton('disable');
	 	$('#addForeordain').linkbutton('enable');
		$('#deleteForeordain').linkbutton('enable');
		 $("#sellCode").validatebox({required:true});
		// 添加保存，取消按钮
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
		var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
		if ($('#saveOrCancelBtn').children('a').length == 0) {
			$('#saveOrCancelBtn').append(save).append(cancel);
			$.parser.parse('#saveOrCancelBtn');
		}
		$('#StForm').form('clear');
		$('#zhProjectDate').val(getSystemTime());
		
		$('#zhPerson').combobox('select',zhProjectPerson);
		// 每次新增时清空表中的数据
		$('#zhList').datagrid('loadData',{total:0,rows:[]});
}
// 保存新增加信息
function save(){
	if($('#StForm').form('validate')){
		endEdit($("#zhList")); 
		var rows=$("#zhList").datagrid('getRows');
		if(rows.length==0){
			  alertMsg('对不起，请添加装潢项目！', 'warning');
			  return ;
		}
		var isnosubmit=true;
        for(var i=0;i<rows.length;i++){
        	var isno=$("#zhList").datagrid('validateRow',i);
        	if(!isno){
        		isnosubmit=false;
        		break;
          }
        }
   
        if(isnosubmit){
        	var effectRow = new Object();
    		effectRow['zhLists'] =  JSON.stringify(rows);
    		effectRow['zhFrom']=JSON.stringify(serializeObject($('#StForm')));
    		$.ajax({
    			   type: 'post',
    			   dataType: 'json',
    			   url:'${pageContext.request.contextPath}/sellZhProjectAction!savaZhInfor.action',   
    			   data:effectRow,
    			   success: function(r){
    				 if(r.success){
    					  
    					  $("#sellCode").validatebox({required:false});
    					  cancel();
    					  	//$('#tt').tabs('select','装潢项目汇总');
    						 $('#sellInfo').datagrid('load');
    						 $('#zhList').datagrid('load');
    					 
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
// 修改按钮
function  updateSellPro(){
	var data=$('#sellInfo').datagrid('getSelected');
	
		 if(data){
			 $.ajax({
					type : 'POST',
				    dataType : 'json',
					url : 'sellZhProjectAction!isRefundment.action',
					data : 'audit='+data.audit,
					success : function(r){
						if(r.success){
							if(r.obj==true){
								alertMsg('该装潢单已审核，不能修改！', 'warning');
								return;							
							}else{
					parentId=data.xs_Car_Sel_Id;
					$('#tt').tabs('select','装潢项目明细');
					$('#_print').linkbutton('disable');
					$('#_export').linkbutton('disable');
					$('#_import').linkbutton('disable');
				 	
				 	nuDisAbledControl();
				 	disableBtn();
					$('#addForeordain').linkbutton('enable');
					$('#deleteForeordain').linkbutton('enable');
					// 添加保存，取消按钮
					var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updateSave();">保存</a>';
					var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
					if ($('#saveOrCancelBtn').children('a').length == 0) {
						$('#saveOrCancelBtn').append(save).append(cancel);
						$.parser.parse('#saveOrCancelBtn');
					}
					 loadZhList(parentId,1);
					 $('#StForm').form('load',data); 
							}
						}
			 }
			 });
					
	}else{
		alertMsg('对不起，请先选中要修改的装潢单汇总记录！', 'warning');
	}
}
// 保存修改
function updateSave(){
	
	var data=$('#sellInfo').datagrid('getSelected');
	endEdit($("#zhList")); 
	var rows=$("#zhList").datagrid('getRows');
	if(rows.length==0){
		  alertMsg('对不起，请添加装潢项目！', 'warning');
		  return ;
	}
	var isnosubmit=true;
	for(var i=0;i<rows.length;i++){
		var isno=$("#zhList").datagrid('validateRow',i);
		if(!isno){
			isnosubmit=false;
			break;
	  }
	}
	var effectRow = new Object();
	effectRow['zhLists'] =  JSON.stringify(rows);
	 if($("#zhList").datagrid('getChanges').length) {
			var inserted = $("#zhList").datagrid('getChanges', 'inserted');
			var deleted = $("#zhList").datagrid('getChanges', 'deleted');
			var updated = $("#zhList").datagrid('getChanges', 'updated');
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

	effectRow['zhFrom']=JSON.stringify(serializeObject($('#StForm')));
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'${pageContext.request.contextPath}/sellZhProjectAction!updateZhInfor.action',   
		   data:effectRow,
		   success: function(r){
			 if(r.success){
				
				  $("#sellCode").validatebox({required:false});

				  cancel();
				 // $('#tt').tabs('select','装潢项目汇总');
					 $('#sellInfo').datagrid('load');
					 $('#zhList').datagrid('load');
					
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

// 删除
function removeSellPro(){
	var data = $('#sellInfo').datagrid('getSelected');
	 var index=findSelectRowIndex('sellInfo',data);
		if(data!=null){
				 $.ajax({
						type : 'POST',
					    dataType : 'json',
						url : 'sellZhProjectAction!isRefundment.action',
						data : 'audit='+data.audit,
						success : function(r){
							if(r.success){
								if(r.obj==true){
									alertMsg('该装潢单已审核，不能删除！', 'warning');
									return;							
								}else{
									$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
										if (r){
											$.ajax({
											   type: 'post',
											   dataType: 'json',
											   url: 'sellZhProjectAction!deleteZhInfor.action',
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


// 点击取消按钮执行的操作
function cancel() {
	 $("#sellCode").validatebox({required:false});
		$('#tt').tabs('select',0);
		$('#saveOrCancelBtn').empty();
		enableBtn();
		$('#StForm').form('clear');
		$('#sellInfo').datagrid('unselectAll');
		$('#sellInfo').datagrid('load');
		$('#zhList').datagrid('loadData',{total:0,rows:[]});
		
}
// 设置按钮的状态为可用
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
	$('#sellAccount').linkbutton('enable');
}
// 禁用按钮
function disableBtn(){
	$('#_add').linkbutton('disable');
	$('#_remove').linkbutton('disable');
	$('#_update').linkbutton('disable');
	$('#_search').linkbutton('disable');
	$('#_clear').linkbutton('disable');
	$('#_examine').linkbutton('disable');
	
	$('#sellAccount').linkbutton('disable');


}
// 启用控件
function nuDisAbledControl(num){
	$("#StForm input.easyui-combobox").combobox('enable');
	$("#StForm input").prop("disabled", false);
	$("#StForm select").prop("disabled", false);
	$("#StForm textarea").prop("disabled",false);
	//$("#zhProjectDate").prop("disabled",true);
	$("#carSellImg").unbind();
	if(num==1){
		$("#carSellImg").bind("click",addCarSel);	
	}
	$('#zhPerson').combobox({disabled:true});
}
// 清空
function clearSearchCondition(){
	$('#queryForm').find('input').val('');
	$('#queryForm').find('select').val('');
	queryReserve();
}
// 查询
function queryReserve() {

	$('#sellInfo').datagrid('unselectAll');
	$('#sellInfo').datagrid('load',serializeObjectByflag($('#queryForm'),false));
	//初试时间
	addInitDate($('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));
}

/**审核事件**/
function examine_1(){
	 var selected = $('#sellInfo').datagrid('getSelected');
     if(selected!=''&&selected!=null){
         if(selected.accountCode==null||selected.accountCode==''){
        	 var examine=selected.auditName;
		     pos = examine.indexOf('未审核');  
		     if (pos == -1) {
		    	 $.messager.confirm('优亿软件提示', '请确认是否要取消审核该装潢单？', function(r){
		              if(r)
		            	  examineState(selected);
        		 });
		     }else{
		    	 $.messager.confirm('优亿软件提示', '请确认是否要审核该装潢单？', function(r){
		               if(r)
		            	   examineState(selected);
        		 });
		     }
         }else
        	 $.messager.alert('优亿软件提示', '对不起，该装潢单已经被结算，不允许取消审核，请先删除结算单号为【'+selected.accountCode+'】的结算单后再执行本操作！', 'info', function() {});
     }else
		 $.messager.alert('优亿软件提示','对不起，请先选中要审核的记录！','warning',function(){});	
 }

// 审核
function examine_(){
	 var selected = $('#sellInfo').datagrid('getSelected');
     if(selected){
    	 $.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'sellZhProjectAction!isUseDocument.action',
			   data:selected,
			   success: function(r){
				 if(r.success){
					 if(r.obj==true){
						 alertMsg('对不起，此装潢单已被结算,不能取消审核！', 'warning'); 
					 }else{
						 $.messager.confirm('优亿软件提示', '请确认是否执行此操作？', function(r){
					if(r){
					$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'sellZhProjectAction!updateAudit.action',
						   data: selected,
						   success: function(r){
							 if(r.success){
								 $('#sellInfo').datagrid('load');
							 }else{
								 alertMsg(r.msg, 'warning');
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



// 转结算
function sellAccount(){
	var data = $('#sellInfo').datagrid('getSelected');
	if(data!=null){
		 $.ajax({
				type : 'POST',
			    dataType : 'json',
				url : 'sellZhProjectAction!isNotRefundment.action',
				data : 'audit='+data.audit,
				success : function(r){
					if(r.success){
						if(r.obj==true){
							alertMsg('该装潢单未被审核，不能转结算！', 'warning');
							return;							
						}else{
							if(data.reckoningName=='是'){
								$.messager.alert('优亿软件提示', '该装潢单已被转结算！', 'warning', function() {});
								return ;
							}
							$.messager.confirm('优亿软件提示', '请确认是否要转结算？', function(r){
						if (r){
							$.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: 'sellZhProjectAction!updatesum.action',
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
	if(tbtitle =='装潢项目汇总'){
		var data =  $('#sellInfo').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("sellInfo",null,"zhLists","开始导出","导出配置",0,_callbackExcept);
		
	}else{
		var data =  $('#zhList').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("zhList",null,"wowo","开始导出","导出配置",0,_callbackExcept2);	
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
	exportEsuyUIExcelFile(parentId,fieldNames,"装潢项目汇总"+getSystemTime());
}
function _callbackExcept2(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"装潢项目明细"+getSystemTime());
}

/**
 * 打印字段设置
 * 编辑、选择不同分组
 * @return
 */
function _config(){
	
	if(tbtitle =='装潢项目汇总'){
		var data =  $('#sellInfo').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
		showEditDialog("sellInfo",personnelSumTable,"zhLists","开始打印","打印配置",2,_print);
		
	}else{
		var data =  $('#zhList').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
		showEditDialog("zhList",personnelSumTable,"zhDetils","开始打印","打印配置",2,_print);	
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
