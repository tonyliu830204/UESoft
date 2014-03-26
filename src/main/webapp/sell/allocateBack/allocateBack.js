

//根据调退单汇总某条记录加载关联的入库单明细信息
function  loadInstoreDel(parentId){
	$('#mingxiList').datagrid({
					url : 'carBarnInfoAction!getBack.action',
					queryParams: {backId:parentId},
					pagination : true
				});
	
}

var backPerson;
$(function(){
	$('#reserveDete2').val(getSystemTime());
	 getStartDate($('#reserveDete'));
	backPerson=$('#backPerson').val();
	$('#dName').validatebox({  
        required:true,
        missingMessage:'分销商不可为空！'
});  


	//页面初始化时，设置按钮的状态
	$('#tt').tabs({   
		onSelect:function(title){  
		 tbtitle = title;
			if(title =='调退单汇总'){
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					enableBtn();
				}
			}else if(title =='调退单明细'){
				//disableBtn();		
			}
	    }   
	});
	

			//调退单汇总
			$('#allocateList').datagrid({
				url : 'sellBackAction!getSellBackInfo.action',
				pagination : true,
				fit:true,
				fitColumns : true,
				sortOrder:'asc',
			    sortName:'backId',
				singleSelect : true,
				pageSize : 20,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				rownumbers : true,
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ 
				                {
									field : 'backId',
									title : '调退单',
									width : 60,
									hidden:true,
									sortable : true,
								},{
									field : 'backDate',
									title : '调退日期',
									width : 90,
									sortable : true
								},{
									field : 'xsDistributorName',
									title : '分销商',
									width : 170,
									sortable : true
								},{
									field : 'xsDistributorId',
									title : '分销商',
									width : 130,
									hidden:true,
									sortable : true
								},{
									field : 'backCode',
									title : '调退单号',
									width : 125,
									sortable : true
								
								},{
									field : 'handbackAllocateAmount',
									title : '调退金额',
									width : 100,
									sortable : true
								},{
									field : 'backPerson',
									title : '经办人id',
									hidden:true,
									width : 70,
									sortable : true
								},{
									field : 'backPersonName',
									title : '经办人',
									width : 120,
									sortable : true
								},{
									field : 'backType',
									title : '调配分类id',
									width : 70,
									hidden:true,
									sortable : true
								},{
									field : 'backTypeName',
									title : '调配分类',
									width : 100,
									sortable : true
							   
							    },{
									field : 'examineName',
									title : '审核情况',
									width : 80,
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
							   
							    },{
									field : 'balanceState',
									title : '对账',
									width : 70,
									hidden:true,
									sortable : true
								},{
									field : 'balanceStateN',
									title : '是否对账',	
									width : 70,
									sortable : true
								},{
									field : 'remark',
									title : '备注',
									width : 160,
									sortable : true
									
									},{
										title : '企业编号',
										field : 'enterprise_id',
										width : 100,
										hidden:true
									}
		        ]],
		    	onDblClickRow : function(rowIndex, rowData) {
		    		$('#tt').tabs('select','调退单明细');		    		
		    			$('#StForm').form('load', rowData);// 将现在新选的行重新load在from表单中
		    			var  Id=rowData.backId;
		    			loadInstoreDel(Id);
				
		}
	});
			
			
			//调退单明细
					$mingxiList = $('#mingxiList').datagrid({
						url : '',
						pagination : true,
						fit : true,
						singleSelect : true,
						fitColumns : true,
						pageSize : 20,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						idField : 'carId',
						loadMsg : "数据加载中，请稍后……",
						columns : [ [  {
							field : 'carId',
							title : '编号',
							width : 60,
							sortable : true,
						},{
							field : 'carCode',
							title : '车辆编号',
							width : 120,
							sortable : true
						},{
							field : 'carVinNumber',
							title : 'VIN号',
							width : 130,
							sortable : true
						},{
							field : 'carBrandName',
							title : '车辆品牌',
							width : 70,
							sortable : true
						},{
							field : 'carBrand',
							title : '车辆品牌id',
							hidden:true,
							width : 70,
							sortable : true
						},{
							field : 'carModelName',
							title : '车辆类型',
							width : 70,
							sortable : true
						},{
							field : 'carModel',
							title : '车辆类型id',
							hidden:true,
							width : 70,
							sortable : true
						},{
							field : 'carColorName',
							title : '颜色',
							width : 70,
							sortable : true
						},{
							field : 'carColor',
							title : '颜色id',
							hidden:true,
							width : 70,
							sortable : true
						},{
							field : 'carOcn',
							title : 'OCN码',
							width : 100,
							sortable : true
						},{
							field : 'allAmount',
							title : '调退金额',
							width : 90,
							sortable : true
							
						},{
							field : 'allocatel_detail_id',
							title : '调拨单明细编号',
							width : 70,
							hidden:true,
							sortable : true
						}
						
						
				        ]]
					
			   });

});

//调退信息增加
var sc;
function add_Foreordain()
{
	var id=$("#did").val();
	if(id==null || id=="" || id==undefined){
		$.messager.alert('提示','请先选择分销商!','warning');
		return ;
	}
  	 sc = $('<div/>');
  	 sc.dialog({
				title: '调退信息选择',   
				width: 750,
			    height: 450,
			    cache: false,
			    href: projectPath+'sell/allocateBack/addBack.jsp?id='+$("#did").val(),
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
	$('#_print').linkbutton('disable');
	$('#_export').linkbutton('disable');
	$('#_import').linkbutton('disable');	
}

//点击取消按钮执行的操作
function cancel() {
	$("#dName").validatebox({required:false});
	$('#tt').tabs('select',0);
	$('#saveOrCancelBtn').empty();
	enableBtn();
	$('#allocateList').datagrid('unselectAll');
	$('#StForm').form('clear');  
	
}


//添加按钮事件
function addPersonnel(i) { 
   if(i==1){	
	   $('#tt').tabs('select','调退单明细');
		$('#addForeordain').linkbutton('enable');
		$('#deleteForeordain').linkbutton('enable');
		 $("#dName").validatebox({required:true});
		
		nuDisAbledControl(1);
		addButton(1);
		disableBtn();
		$('#StForm').form('clear');
		$('#allocateDate').val(getSystemTime());  
		$('#backPerson').combobox('select',backPerson);
		
		//每次新增时清空入库单明细表中的数据
		$('#mingxiList').datagrid('loadData',{total:0,rows:[]});
	}else if(i==2){
		var data = $('#allocateList').datagrid('getSelected');//修改
		
	 if(data){
	 $.ajax({
			type : 'POST',
		    dataType : 'json',
			url : 'sellBackAction!isRefundment.action',
			data : 'examine='+data.examine,
			success : function(r){
				if(r.success){
					if(r.obj==true){
						alertMsg('该调退单已审核，不能修改！', 'warning');
						return;							
					}else{
		
			$('#tt').tabs('select','调退单明细');
			disableBtn();
			nuDisAbledControl();
			$('#addForeordain').linkbutton('enable');
			$('#deleteForeordain').linkbutton('enable');
			addButton(2);
			
			$('#StForm').form('load', data);
			loadInstoreDel(data.backId);
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

//保存新添加车辆信息
function saveStOutMain(){
	if($('#StForm').form('validate')){
		   var rows=$("#mingxiList").datagrid('getRows');
		   if(rows.length==0){
				  alertMsg('对不起，请添加调拨信息！', 'warning');
				  return ;
			}
		  var data=$("#mingxiList").datagrid('getData');
		  var amount = 0;
		  for(var i=0;i<data.rows.length;i++){
			 var backAmount= data.rows[i].allAmount;
			  if( backAmount!= undefined){
				
				  amount+=Number(backAmount);
			  }
		  }
		 
		  $('#han').val(amount);
		  
		  
		  var effectRow = new Object();
		  effectRow['detaildateGrid'] =  JSON.stringify(rows);
		 
		  effectRow['instorehousedateGrid'] = JSON.stringify(serializeObject($('#StForm')));
		 
		  $.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'${pageContext.request.contextPath}/sellBackAction!addInstoreCar.action',   
		   data:effectRow,
		   success: function(r){
			 if(r.success){
				 $("#dName").validatebox({required:false});
				  cancel();
				  //$('#tt').tabs('select','调退单汇总');
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
		  var data=$("#mingxiList").datagrid('getData');
		  var amount = 0;
		  for(var i=0;i<data.rows.length;i++){
			 var backAmount= data.rows[i].allAmount;
			  if( backAmount!= undefined){
				  amount+=Number(backAmount);
			  }
		  }
		  $('#han').val(amount);
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
	url:'${pageContext.request.contextPath}/sellBackAction!modifyBack.action',
	type: 'post',
	dataType: 'json',
	data:effectRow,
	success: function(r){
	if(r.success){		
		 $("#dName").validatebox({required:false});
		 $('#tt').tabs('select','调退单汇总');
		 $('#allocateList').datagrid('load');
		 $('#mingxiList').datagrid('load');
		$('#button').empty();
		 cancel();
		
	}else{
		$.messager.alert('提示','数据提交失败!','warning');
	}
 }
});
}
}
	
//删除汇总
function remove_(){
	var data = $('#allocateList').datagrid('getSelected');
    var index=findSelectRowIndex('allocateList',data);
	if(data){
		$.ajax({
			type : 'POST',
		    dataType : 'json',
			url : 'sellBackAction!isRefundment.action',
			data : 'examine='+data.examine,
			success : function(r){
				if(r.success){
					if(r.obj==true){
						alertMsg('该调退单已审核，不能删除！', 'warning');
						return;							
					}else{
						$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
						if (r){
							$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url: 'sellBackAction!deleteAllocatel.action',
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
					 var index= $('#mingxiList').datagrid('getRowIndex',data);
					 $('#mingxiList').datagrid('deleteRow', index);
					 return;
				 }
			});
		}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}
//审核
function examineState(){
	var data = $('#allocateList').datagrid('getSelected');
	if(data){
		
		$.messager.confirm('优亿软件提示', '请确认要执行此操作？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'sellBackAction!examine.action',
				   data:{backId:data.backId},
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
	}else{
		$.messager.alert('优亿软件提示','对不起，请先选中要审核的记录！','warning',function(){});
	}
}



// 查询
var queryCarReserve = function(dateId1,dateId2) {
	
	$('#allocateList').datagrid('unselectAll');
	$('#allocateList').datagrid('load',serializeObject($('#queryFrom')));
	addInitDate(dateId1,dateId2);
	
}


function clearSearchCondition(dateId1,dateId2){
	$('#queryFrom').form('clear');
	$('#allocateList').datagrid('load',serializeObject($('#queryFrom')));
	addInitDate(dateId1,dateId2);
	

	
}



//启用控件
function nuDisAbledControl(num){
	$("#StForm input.easyui-combobox").combobox('enable');
	$("#StForm input").prop("disabled", false);
	$("#StForm select").prop("disabled", false);
	$("#StForm textarea").prop("disabled",false);
	$("#DisImg").unbind();
	if(num==1){
		$("#DisImg").bind("click",adddis);
	}
	$('#backPerson').combobox({disabled:true});
}
function _except(){
	if(tbtitle =='调退单汇总'){
		var data =  $("#allocateList").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("allocateList",null,"qingdan","开始导出","导出配置",0,_callbackExcept);
		
	}else if(tbtitle =='调退单明细'){
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
	exportEsuyUIExcelFile(parentId,fieldNames,"调退单汇总"+getSystemTime());
}
function _callbackExcept2(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"调退单明细"+getSystemTime());
}

function sellExchangeBackPrint(){
	 var selected=$('#allocateList').datagrid('getSelected')
 if(selected!=''&&selected!=null){
	    window.open(projectPath+'sell/allocateBack/sellExchangeBackPrintReport.jsp?backId='+selected.backId,'demo',"fullscreen=1")
	}else{
		 $.messager.alert('优亿软件提示','请选择要打印的退厂单记录！','warning');
	}
}