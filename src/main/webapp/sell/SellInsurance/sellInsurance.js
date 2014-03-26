//根据汇总某条记录加载关联的保险明细信息
function  loadInstoreDel(parentId,key){
	$('#mingxiList').datagrid({
					url : 'sellInsuranceAction!getInfo.action',
					queryParams: {insuranceId:parentId},
					onClickRow : function(rowIndex,rowData){
						if(key==1){
							var index = $("#mingxiList").datagrid('getRowIndex', rowData);
							copyPartsDateAndBindEventItem($("#mingxiList"), index, rowData);
	              	 }
	}
					
				});
}
var personId;
$(function(){
	
	personId=$('#pp').val();
	
	$('#sellId').validatebox({  
        required:true,
        missingMessage:'请选择车辆销售编号！'
	});
	$('#dNameqq').validatebox({  
        required:true,
        missingMessage:'请填写保险单号！'
	});  
	$('#dName2').validatebox({  
        required:true,
        missingMessage:'请选择保险公司信息！'
});  
	$('#reDete').validatebox({  
        required:true,
        missingMessage:'请选择保险日期！'
});  
	$('#reDete2').validatebox({  
        required:true,
        missingMessage:'请选择保险日期！'
});  
	
	$('#reserveDete2').val(getSystemTime());
	 getStartDate($('#reserveDete'));
	//页面初始化时，设置按钮的状态
	$('#tt').tabs({   
		onSelect:function(title){  
			tbtitle=title;
			if(title =='保单汇总'){
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					enableBtn();
				}
			}else if(title =='保单明细'){
			//	disableBtn();		
			}
	    }   
	});
	
			//保单汇总
			$('#allocateList').datagrid({
				url : 'sellInsuranceAction!getInsuranceList.action',
				pagination : true,
				fit:true,
				fitColumns : true,
				sortOrder:'asc',
			    sortName:'insuranceId',
				singleSelect : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				rownumbers : true,
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ 
				{
					field : 'insuranceId',
					title : '编号',
					width : 50,
					hidden:true,
					sortable : true,
				},
				{
					field : 'dafeDate',
					title : '代保日期',
					width : 90,
					sortable : true
				},{
					field : 'insurancePolicy',
					title : '保险单号',
					width : 150,
					sortable : true
				 }, {
					field : 'insurer',
					title : '保险公司id',
					width : 120,			
					sortable : true,
					hidden:true
				}, 
				{
					field : 'insurerName',
					title : '保险公司',
					width : 150,
					sortable : true
				}, {
					field : 'customName',
					title : '客户名称',
					width : 90,
					sortable : true
				},
				 {
					field : 'carLicensePlate',
					title : '车牌号',
					width : 130,					
					sortable : true
				},
				 {
					field : 'insurerStartDate',
					title : '投保日期',
					width : 90,
					sortable : true
				},{
					field : 'insurerEndDate',
					title : '投保到期日期',
					width : 90,
					sortable : true
				},
				{
					field : 'examine',
					title : '审核状态',
					width : 100,
					hidden:true,
					sortable : true
				},
				{
					field : 'examineName',
					title : '审核状态',
					width : 80,
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
					field : 'invoiceReckoning',
					title : '是否转结算',
					width : 80,
					hidden:true,
					sortable : true
				},
				{
					field : 'reckoningName',
					title : '是否转结算',
					width : 80,
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
					field : 'safeAmount',
					title : '保险费',
					width : 100,
					sortable : true
				},
				{
					field : 'sum',
					title : '保费',
					width : 100,
					sortable : true
				},
				{
					field : 'customCost',
					title : '客户付款',
					width : 100,					
					sortable : true
				},
				{
					field : 'stfName',
					title : '经办人',
					width : 120,
					sortable : true
				},
				{
					field : 'person',
					title : '经办人id',
					width : 110,
					hidden:true,
					sortable : true
				},
				{
					field : 'remark',
					title : '备注',
					width : 150,
					
					sortable : true
				},
				{
					field : 'xs_Car_Sel_Id',
					title : '销售编号',
					width : 110,
					hidden:true,
					sortable : true
				},
				{
					field : 'xsCustomApplicationN',
					title : '用途',
					width : 70,
					hidden:true,
					sortable : true
					
				},{
					field : 'xsCustomApplication',
					title : '用途',
					width : 60,
					hidden:true,
					sortable : true
						
				},{
					field : 'accountCode',
					title : '结算单号',
					width : 100,
					sortable : true,
					hidden:true
				},{
					field : 'insurerCodes',
					title : '保险公司编号',
					width : 100,
					sortable : true,
					hidden:true
				},{
					field : 'buysnessCost',
					title : '商业险返点',
					width : 100,
					sortable : true,
					hidden:true
				},{
					field : 'inCost',
					title : '交强险返点',
					width : 100,
					sortable : true,
					hidden:true
				},{
					field : 'xsCustomApplicationN',
					title : '使用性质',
					width : 100,
					sortable : true,
					hidden:true
				}
				 
		        ]],
		    	onDblClickRow : function(rowIndex, rowData) {
		    		$('#tt').tabs('select','保单明细');	
		    		$('#StForm').form('clear');
		    			$('#StForm').form('load', rowData);// 将现在新选的行重新load在from表单中
		    			var  Id=rowData.insuranceId;
		    			loadInstoreDel(Id);
		    			$("#carSellImg").unbind();
		    			$("#inImg").unbind();
		    			
				
		}
	});
			
			
			//保单明细
					$mingxiList = $('#mingxiList').datagrid({
						url : '',						
						fit : true,
						singleSelect : true,
						idField : 'detailId',
						loadMsg : "数据加载中，请稍后……",
						rownumbers : true,
						columns : [ [ {
							field : 'detailId',
							title : '编号',
							width : 50,
							hidden:true,
							sortable : true
						},{
							field : 'insurancetype',
							title : '险种编号',
							width : 50,
							hidden:true,
							sortable : true
						},{
							field : 'insuranceName',
							title : '险种',
							width : 120,
							sortable : true
						},{
							field : 'insurancemoney',
							title : '险种金额（限）',
							width : 100,
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
							field : 'insurancerate',
							title : '费率',
							width : 100,
							sortable : true,
							editor : {
							type : 'numberbox',
							options : {
							disabled : true,
							min:0.0000, 
							max:1,
							precision : 4,
							validType:'length[1,6]'
							}
						 }	
						},{
							field :  'insurancecost',
							title : '保险费',
							width : 110,
							sortable : true,
							editor : {
								type : 'numberbox',
								options : {
									precision : 2,
									min : 0.00,
									validType:'multiple[\'monery\',\'length[0,12]\']'
								}
							}
						}
				        ]]
			   });

});

// 编辑
function copyPartsDateAndBindEventItem(id, rowIndex, rowData)
{	
	id.datagrid('beginEdit', rowIndex);
	var ed = id.datagrid('getEditors', rowIndex);
	if(ed[0]){
		ed[0].target.numberbox('setValue', rowData.insurancemoney);
		ed[0].target.select();
		ed[0].target.click(function (){
			ed[0].target.select();
		});
		ed[0].target.keydown(function (e){
			if(e.keyCode == '13'){
				ed[2].target.select();
			}
		});
	}
	
	if(ed[2]){
		ed[2].target.numberbox('setValue', rowData.insurancecost);
		ed[2].target.select();
		ed[2].target.click(function (){
			ed[2].target.select();
		});
		ed[2].target.keydown(function (e){
			if(e.keyCode == '13'){
				if(rowIndex < id.datagrid('getData').length - 1){
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
	if(ed[1])
	    ed[1].target.numberbox('setValue', rowData.insurancerate);	
	ed[0].target.bind('keyup', function() {
		var insurancemoney = ed[0].target.val();
		var insurancerate = ed[2].target.val();		
		var insurancecost = accDiv(parseFloat(insurancerate),parseFloat(insurancemoney));
		ed[1].target.numberbox('setValue', insurancecost);
		if(rowData && rowData.insurancemoney){
			if(parseInt(rowData.insurancemoney)<0){
				alertMsg('险种金额不能为负数', 'warning');
				ed[0].target.numberbox('setValue', 0.0);
			}
		}
		
	});
	ed[2].target.bind('keyup', function() {
		var insurancemoney = ed[0].target.val();
		var insurancerate = ed[2].target.val();
	
		
		var insurancecost = accDiv( parseFloat(insurancerate),parseFloat(insurancemoney));
		ed[1].target.numberbox('setValue', insurancecost);
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
	$('#sellSum').linkbutton('enable');
	
}
//禁用按钮
function disableBtn(){
	$('#_add').linkbutton('disable');
	$('#_remove').linkbutton('disable');
	$('#_update').linkbutton('disable');
	$('#_search').linkbutton('disable');
	$('#_clear').linkbutton('disable');
	$('#_examine').linkbutton('disable');
	$('#sellSum').linkbutton('disable');
	
}

//点击取消按钮执行的操作
function cancel() {
	 $("#dName2").validatebox({required:false});
	 $("#dNameqq").validatebox({required:false});
	 $("#sellId").validatebox({required:false});
	 $("#reDete").validatebox({required:false});
	 $("#reDete2").validatebox({required:false});
	$('#tt').tabs('select',0);
	$('#saveOrCancelBtn').empty();
	enableBtn();
	$('#allocateList').datagrid('unselectAll');
	$('#StForm').form('clear'); 
	
}



function  loadInfo(key){

	$('#mingxiList').datagrid({
					url : 'sellInsuranceAction!getInsurance.action',
					onClickRow : function(rowIndex,rowData){
		if(key==1){
			var index = $("#mingxiList").datagrid('getRowIndex', rowData);
			copyPartsDateAndBindEventItem($("#mingxiList"), index, rowData);
  	 }
	}
					
		});
}



//添加按钮事件
function addPersonnel(i) { 
   if(i==1){	
	   $('#tt').tabs('select','保单明细');
	   	$('#_print').linkbutton('disable');
		$('#_export').linkbutton('disable');
		$('#_import').linkbutton('disable');
		 $("#dName2").validatebox({required:true});
		 $("#dNameqq").validatebox({required:true});
		 $("#sellId").validatebox({required:true});
		 $("#reDete").validatebox({required:true});
		 $("#reDete2").validatebox({required:true});
		nuDisAbledControl(1);
		addButton(1);
		disableBtn();
		$('#StForm').form('clear');
		$('#recordDate').val(getSystemTime());
		$('#allocateDate').val(getSystemTime());
		$('#pp').combobox('select',personId);
		//每次新增时清空明细表中的数据
		
		loadInfo(1);
	}else if(i==2){                  //修改
		var data = $('#allocateList').datagrid('getSelected');
		if(data){
			$.ajax({
				type : 'POST',
			    dataType : 'json',
				url : 'sellInsuranceAction!isRefundment.action',
				data : 'examine='+data.examine,
				success : function(r){
					if(r.success){
						if(r.obj==true){
							alertMsg('该保险单已审核，不能修改！', 'warning');
							return;							
						}else{
				$('#_print').linkbutton('disable');
				$('#_export').linkbutton('disable');
				$('#_import').linkbutton('disable');
			$('#tt').tabs('select','保单明细');
			nuDisAbledControl();
			disableBtn();
			$('#addForeordain').linkbutton('enable');
			$('#deleteForeordain').linkbutton('enable');
			var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updates();">保存</a>';
			var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
			if ($('#saveOrCancelBtn').children('a').length == 0) {
				$('#saveOrCancelBtn').append(save).append(cancel);
				$.parser.parse('#saveOrCancelBtn');
			}
			$('#StForm').form('load', data);
			loadInstoreDel(data.insuranceId,1);	
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


function countSum(){
	 var rows=$("#mingxiList").datagrid('getRows');
	  endEdit($("#mingxiList"));
	  var data=$("#mingxiList").datagrid('getData');
	  var sum=0;
	  for(var i=0;i<data.rows.length;i++){
		  var cost=data.rows[i].insurancecost;
		  if( cost!=null &&  cost!="" &&  cost!=undefined){
		 // alert( cost)
		  sum+=parseFloat(cost); 
		 
		  }
		  $('#ss').numberbox('setValue',sum);
	  }
	 
	  
}



//保存新添加信息
function saveStOutMain(){
	if($('#StForm').form('validate')){
		   var rows=$("#mingxiList").datagrid('getRows');
		  endEdit($("#mingxiList"));
		  var data=$("#mingxiList").datagrid('getData');
		  countSum();
		  var effectRow = new Object();
		  effectRow['detaildateGrid'] =  JSON.stringify(rows);
		  effectRow['dateGridForm'] = JSON.stringify(serializeObject($('#StForm')));
		 
		  $.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'${pageContext.request.contextPath}/sellInsuranceAction!addInsurance.action',   
		   data:effectRow,
		   success: function(r){
			 if(r.success){
				 $("#dName2").validatebox({required:false});
				 $("#dNameqq").validatebox({required:false});
				 $("#sellId").validatebox({required:false});
				 $("#reDete").validatebox({required:false});
				 $("#reDete2").validatebox({required:false});
				  cancel();
				  //$('#tt').tabs('select','保单汇总');
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
	
//删除汇总
function remove_(){
	
	
	var data = $('#allocateList').datagrid('getSelected');
	var index=findSelectRowIndex('allocateList',data);
	if(data){
		$.ajax({
			type : 'POST',
		    dataType : 'json',
			url : 'sellInsuranceAction!isRefundment.action',
			data : 'examine='+data.examine,
			success : function(r){
				if(r.success){
					if(r.obj==true){
						alertMsg('该保险单已审核，不能删除！', 'warning');
						return;							
					}else{
			$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'sellInsuranceAction!deleteInsurance.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						  $('#allocateList').datagrid('clearSelections');
						  $('#allocateList').datagrid('reload');
						  $('#mingxiList').datagrid('reload');
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



// 查询
var queryCarReserve = function() {

	$('#allocateList').datagrid('unselectAll');
	$('#allocateList').datagrid('load',serializeObject($('#queryF')));
	addInitDate($('#reserveDete'),$('#reserveDete2'));

}

//清空
function clearSearchCondition(){
	$('#queryF').form('clear');
	$('#allocateList').datagrid('unselectAll');
	$('#allocateList').datagrid('load',serializeObject($('#queryF')));
	addInitDate($('#reserveDete'),$('#reserveDete2'));

	
}

//保存修改
function updates(){
	if($('#StForm').form('validate')){
	   var rows=$("#mingxiList").datagrid('getRows');
		  endEdit($("#mingxiList"));
		  var data=$("#mingxiList").datagrid('getData');
		  countSum();
		  var effectRow = new Object();
		  effectRow['detaildateGrid'] =  JSON.stringify(rows);
		  effectRow['dateGridForm'] = JSON.stringify(serializeObject($('#StForm')));
		 
		$.ajax({
			url:'${pageContext.request.contextPath}/sellInsuranceAction!modifyInsurance.action',
			type: 'post',
			dataType: 'json',
			data:effectRow,
			success: function(r){
			if(r.success){	
				 $("#dName2").validatebox({required:false});
				 $("#dNameqq").validatebox({required:false});
				 $("#sellId").validatebox({required:false});
				 $("#reDete").validatebox({required:false});
				 $("#reDete2").validatebox({required:false});
				cancel();
				//  $('#tt').tabs('select','保单汇总');
				 $('#allocateList').datagrid('load');
				$('#button').empty();
				
			}else{
				$.messager.alert('提示','数据提交失败!','warning');
			}
 }
});
}
}

//审核
function examineState(){
	 var selected = $('#allocateList').datagrid('getSelected');
     if(selected!=''&&selected!=null){
    	 $.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'sellInsuranceAction!isUseDocument.action',
			   data:selected,
			   success: function(r){
				 if(r.success){
					 if(r.obj==true){
						 alertMsg('对不起，此保险单已经被结算,不能取消审核！', 'warning'); 
					 }else{
				$.messager.confirm('优亿软件提示', '请确认是否执行此操作？', function(r){
					if(r){
	
					$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'sellInsuranceAction!updateExamine.action',
						   data:"insuranceId="+selected.insuranceId,
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
//转结算
function sellSum(){
	var data = $('#allocateList').datagrid('getSelected');
	if(data!=null){
		 $.ajax({
				type : 'POST',
			    dataType : 'json',
				url : 'sellInsuranceAction!isNotRefundment.action',
				data : 'examine='+data.examine,
				success : function(r){
					if(r.success){
						if(r.obj==true){
							alertMsg('该保险单未被审核，不能转结算！', 'warning');
							return;							
						}else{
							if(data.reckoningName=='是'){
								$.messager.alert('优亿软件提示', '该保险单已被转结算！', 'warning', function() {});
								return ;
							}
							$.messager.confirm('优亿软件提示', '请确认是否要转结算？', function(r){
							if (r){
								$.ajax({
									   type: 'post',
									   dataType: 'json',
									   url: 'sellInsuranceAction!updateSum.action',
									   data: data,
									 success: function(r){
										 if(r.success){
											 alertMsg('转结算成功！', 'info');
											
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
					}
		 }
		 });
	}
		else{
			alertMsg('对不起，请先选中要转结算的记录！', 'warning');
		 }
}



//启用控件
function nuDisAbledControl(num){
	$("#StForm input.easyui-combobox").combobox('enable');
	$("#StForm input").prop("disabled", false);
	$("#StForm select").prop("disabled", false);
	$("#StForm textarea").prop("disabled",false);
	$("#carSellImg").unbind();
	$("#inImg").unbind();
	if(num==1){
		$("#carSellImg").bind("click",addSellInfo);
		$("#inImg").bind("click",adddis2);
	}
	$('#pp').combobox({disabled:true});
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
	if(tbtitle =='保单汇总'){
		var data =  $('#allocateList').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("allocateList",null,"qingdan","开始导出","导出配置",0,_callbackExcept);
		
	}else{
		var data =  $('#mingxiList').datagrid('getData'); 
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
	exportEsuyUIExcelFile(parentId,fieldNames,"保单汇总"+getSystemTime());
}
function _callbackExcept2(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"保单明细"+getSystemTime());
}

/**
 * 打印字段设置
 * 编辑、选择不同分组
 * @return
 */
function _config(){
	
	if(tbtitle =='保单汇总'){
		var data =  $('#allocateList').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
		showEditDialog("allocateList",personnelSumTable,"qingdan","开始打印","打印配置",2,_print);
		
	}else{
		var data =  $('#mingxiList').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
		showEditDialog("mingxiList",personnelSumTable,"mingxi","开始打印","打印配置",2,_print);	
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