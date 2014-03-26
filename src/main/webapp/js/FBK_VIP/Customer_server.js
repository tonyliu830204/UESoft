$(function() {
	var addrow = undefined;
	var editrow = undefined;
	var addbar = undefined;
	var editbar = undefined;
	var mark = undefined;
	$('#includehiden').hide();//跟踪管理隐藏弹出窗口 div	
		//跟踪统计的满意度查询寻提交方法
		function fandTrack(formid,url){
			var form =  formid.form();
			var formvalue = serializeObject(form);
				$.ajax({
				type : 'POST',
				url : url,
				data : formvalue,
			    dataType : 'json',
			    success : function(r){
					
			  	 	formid.find('input').val('');
					formid.find('select').val('');
			}
			   });
	 	}
		//跟踪统计的条件查询方法
		function doFindbyCondition(formid , url){
			var form =  formid.form();
			var formvalue = serializeObject(form);
				$.ajax({
				type : 'POST',
				url : url,
				data : formvalue,
			    dataType : 'json'
			   });
			   formid.form('submit', {
						url : url,
						success : function(r){
							  	 	formid.find('input').val('');
			  					 	formid.find('select').val('');
							}
						});
						
	 				}	
	//将form表单序列化为对象
	function serializeObject(form){
		var o = {};
		$.each(form.serializeArray(),function(index){
			if(o[this['name']]){
				o[this['name']]=o[this['name']]+","+this['value'];
			}else{
				o[this['name']]=this['value'];
			}
		});
		return o;
	} 

	//获取form表单数据传给后台action
	function doEditSubmit(id,formid,url1,url2){
		var form =  formid.form();
		var formvalue = serializeObject(form);
			$.ajax({
			type : 'POST',
			url : url1,
			data : formvalue,
		    dataType : 'json',
			success : function(r){
					id.datagrid({url : url2});
				}
		   });
	}
	

	//新增 修改后的 提交执行方法  
	function onafterSubmit(id,rowIndex, rowData, changes){
		var caridvalue = $('#customer_genz1').datagrid('getSelections');
		var url = 'customerGzManageAction_updateFbkCarDcname.action?carId='+caridvalue[0].carId;
			$.ajax({
				type : 'POST',
				url : url,
				data : rowData ,
				dataType : 'json',
				success : function (r){
					id.datagrid('acceptChanges');
				}
			});
		}
	
	/*
	 * 
	 * 以下为增删改查的通用方法方法
	 */
	//新增数据方法
	function doAdd(id){
		if(editrow!=undefined){
			alertMsg('对不起,此系统只支持单行新增!', 'warning');
			}else if(editrow!=undefined){
			alertMsg('请先完成当前编辑!', 'warning');	
			}else{
				var rows = id.datagrid('getSelections');
				var index = id.datagrid('getRowIndex',rows[0]);//
				if(addrow == undefined){
					id.datagrid('insertRow',{
						index: 0,	
						row: {
						}
					});
					id.datagrid('beginEdit',0);
					addrow = 0;
					//得到当前要编辑的行
					var editors = id.datagrid('getEditors', addrow);
					//设置光标位置
					 editors[0].target.select();
				if(addbar==undefined){
						id.datagrid('addToolbarItem',[{"text":"保存","iconCls":"icon-save","handler":function(){
							if(id.datagrid('validateRow', addrow)){
								id.datagrid('removeToolbarItem','保存');
								id.datagrid('removeToolbarItem','取消');
								id.datagrid('endEdit', addrow);
								editrow = undefined;
								addrow = undefined;
							}else{
								alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
							}
								
					}},{"text":"取消","iconCls":"icon-cancel","handler":function(){
							 id.datagrid('unselectAll');
	    	        		 id.datagrid('rejectChanges');
	    	        		 id.datagrid('removeToolbarItem','保存');
	    	        		 id.datagrid('removeToolbarItem','取消');
	    	        		 id.datagrid('endEdit',addrow);
	    	        		 addrow = undefined;
						}}]);}
			}else{
				id.datagrid('endEdit',addrow);
			}
	}
	}
//删除方法
function doDelete(id,url1,url2){
	if(addrow!=undefined){
		alertMsg('请先完成当前新增操作.', 'warning');
	}else if(editrow!=undefined){
		alertMsg('请先完成当前编辑!', 'warning');
	}else{
		var delrow = id.datagrid('getSelections');
	if(delrow.length>0){
		$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
			if(b){
				$.ajax({
					url : url1,
					data : delrow[0],
					success : function(data){
					var r = $.parseJSON(data);
							if(r.success){
								//清空选择
								id.datagrid('clearSelections');
							}else{
								alertMsg("对不起，该数据已经被使用，不允许删除！",'info');
							}
					
						}
					});
					id.datagrid({
					url : url2
				});
			}
		});
	}else{
		alertMsg('对不起，请先选中要删除的记录！','warning');
		}
	}
}

//回访记录删除方法
function doDeletes(id,url1){
	var delrow = id.datagrid('getSelections');
console.info(delrow);	
	if(delrow.length>0){
		$.messager.confirm('优亿软件提示','请确定是否要删除该选项!',function(b){
			if(b){
				$.ajax({
					type : 'POST',
					url : url1,
					data : delrow[0],
					success : function(b){
						id.datagrid({url : 'customerCareAction!factoryVisit.action?carId='+delrow[0].carId});
					}
				});
			}
		});
	}else{
		$.messager.alert('优亿软件提示','请选择你要删除的回访记录!','warning');
	}
}
//修改方法
function doUpdate(id){
	if(addrow!=undefined){
		alertMsg('请先完成当前新增操作!', 'warning');
	}else if(editrow!=undefined){
		alertMsg('对不起,此系统只支持单行编辑操作!', 'warning');
	}else{
		var rows = id.datagrid('getSelections');
		if(rows.length == 1){
			if(editbar==undefined){
				id.datagrid('addToolbarItem',[{"text":"保存","iconCls":"icon-save","handler":function(){
					if(id.datagrid('validateRow', editrow)){
						id.datagrid('removeToolbarItem','保存');
						id.datagrid('removeToolbarItem','取消');
						id.datagrid('endEdit', editrow);
						editrow = undefined;
						addrow = undefined;
					}else{
						alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
					}
				}},{"text":"取消","iconCls":"icon-cancel","handler":function(){
					id.datagrid('unselectAll');
	        		 	id.datagrid('rejectChanges');
	        		 	id.datagrid('removeToolbarItem','保存');
	        		 	id.datagrid('removeToolbarItem','取消');
					id.datagrid('endEdit',editrow);
					//editbar = undefined;
					editrow = undefined;
					}}]);
				}
			if(editrow != undefined){
				id.datagrid('endEdit',editrow);
			}
			if(editrow == undefined){
				var index = id.datagrid('getRowIndex',rows[0]);//
				id.datagrid('beginEdit',index);
				editrow = index;
				//得到当前要编辑的行
				var editor = id.datagrid('getEditors', editrow);
				//设置光标位置
				 editor[0].target.select();
				id.datagrid('unselectAll');
				}
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	}
//查询的方法
function doFind(id,formid,url){
	var form =  $(formid).form();
	var formvalue = serializeObject(form);
		$.ajax({
		type : 'POST',
		url : url,
		data : formvalue,
		dataType : 'json',
		success : function(r){
				id.datagrid({
					url : url
				});
		}
	   });
}

//结束编辑提交方法
function endEdit(id,url1,url2,url3,rowIndex, rowData, changes){
	var inserted = id.datagrid('getChanges','inserted');
	var updated = id.datagrid('getChanges','updated');
	var url = '';
	if(inserted.length>0){
		url = url1;
		}
	if(updated.length>0){
		url = url2;
	}
		//ajax提交新增内容
		$.ajax({
			type : 'POST',
			url : url,
			data : rowData,
			dataType : 'json',
			success : function (d){
			if(d.success){
				id.datagrid('acceptChanges');
				id.datagrid('clearSelections');
				id.datagrid({
					url : url3
				});
				addrow = undefined;
				addbar = undefined;
			}else{
				id.datagrid('rejectChanges');
				alertMsg(''+d.msg+'', 'warning');
				addrow = undefined;
				addbar = undefined;
				}
			}
		});
	}//onAfterEdit结束


			
			//会员到期回访  历史维修记录展示
			$('#huiyuandaoqi_table_south_id').datagrid({
			url : '',
			fit : true,
			singleSelect : true,
			newrap : false,
			striped : true,
			rownumbers : true,
			fitColumns : false, // 自适应列宽 
			idField : 'id',
			loadMsg : "数据加载中，请稍后……",
			columns : [ [ {
				field : 'interDate',
				title : '进店日期',
				width : 100,
				sortable : true
			} ,{
				field : 'receptionDistance',
				title : '行驶里程',
				width : 100,
				sortable : true
			} ,{
				field : 'receptor',
				title : '接待员',
				width : 100,
				sortable : true
			},{
				field : 'preclrTime',
				title : '出厂日期',
				width : 100,
				sortable : true
			},{
				field : 'stfName',
				title : '主修工',
				width : 100,
				sortable : true
			},{
				field : 'receptionId',
				title : '工单号',
				width : 100,
				sortable : true
			},{
				field : 'preclrSumAmount',
				title : '合计金额',
				width : 100,
				sortable : true
			},{
				field : 'rcptitemName',
				title : '主修项目',
				width : 100,
				sortable : true
				}
			]]
			});
			//会员到期回访  历史记录展示
			$('#huiyuandaoqi_table_center_id').datagrid({
			url : 'customerCareAction!factoryVisit.action',
			pagination : true,
			fit : true,
			newrap : false,
			striped : true,
			pageSize : 4,
			singleSelect : true,
			rownumbers : true,
			pageList : [ 4, 10, 30, 40, 50],
			fitColumns : false, // 自适应列宽 
			idField : 'id',
			loadMsg : "数据加载中，请稍后……",
			columns : [ [ {
				field : 'ffvId',
				title : 'id',
				width : 100,
				sortable : true,
				hidden : true
			} ,{
				field : 'carId',
				title : 'cid',
				width : 100,
				sortable : true,
				hidden : true
			} ,{
				field : 'returnVisitDate',
				title : '回访日期',
				width : 100,
				sortable : true
			} ,{
				field : 'txReturnVisitDate',
				title : '提醒回访日期',
				width : 100,
				sortable : true
			} ,{
				field : 'groupName',
				title : '回访类型',
				width : 100,
				sortable : true
			},{
				field : 'visitContent',
				title : '回访内容',
				width : 100,
				sortable : true
			},{
				field : 'txResault',
				title : '回访结果',
				width : 100,
				sortable : true
			}
			]],
			toolbar : [ {
				id : 'btnsearch',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function (){
				doDeletes($('#huiyuandaoqi_table_center_id'),'customerCareAction!doDelete.action')
			}
			}]
			});
			/*会员到期提醒结束*/
			
			//生日回访  历史维修记录展示
			$('#shengritixing_table_south_id').datagrid({
			url : 'customerCareAction!getFactoryWxRecord.action',
			fit : true,
			singleSelect : true,
			rownumbers : true,
			newrap : false,
			striped : true,
			fitColumns : false, // 自适应列宽 
			idField : 'id',
			loadMsg : "数据加载中，请稍后……",
			columns : [ [ {
				field : 'interDate',
				title : '进店日期',
				width : 100,
				sortable : true
			} ,{
				field : 'receptionDistance',
				title : '行驶里程',
				width : 100,
				sortable : true
			} ,{
				field : 'receptor',
				title : '接待员',
				width : 100,
				sortable : true
			},{
				field : 'preclrTime',
				title : '出厂日期',
				width : 100,
				sortable : true
			},{
				field : 'stfName',
				title : '主修工',
				width : 100,
				sortable : true
			},{
				field : 'receptionId',
				title : '工单号',
				width : 100,
				sortable : true
			},{
				field : 'preclrSumAmount',
				title : '合计金额',
				width : 100,
				sortable : true
			},{
				field : 'rcptitemName',
				title : '主修项目',
				width : 100,
				sortable : true
				}
			]]
			});
			//生日回访  历史记录展示
		$('#shengritixing_table_center_id').datagrid({
			url : 'customerCareAction!factoryVisit.action',
			pagination : true,
			fit : true,
			pageSize : 4,
			singleSelect : true,
			newrap : false,
			striped : true,
			rownumbers : true,
			pageList : [ 4, 10, 30, 40, 50],
			fitColumns : false, // 自适应列宽 
			idField : 'id',
			loadMsg : "数据加载中，请稍后……",
			columns : [ [ {
				field : 'ffvId',
				title : 'id',
				width : 100,
				sortable : true,
				hidden : true
			} ,{
				field : 'carId',
				title : 'cid',
				width : 100,
				sortable : true,
				hidden : true
			} ,{
				field : 'returnVisitDate',
				title : '回访日期',
				width : 100,
				sortable : true
			} ,{
				field : 'txReturnVisitDate',
				title : '提醒回访日期',
				width : 100,
				sortable : true
			} ,{
				field : 'groupName',
				title : '回访类型',
				width : 100,
				sortable : true
			},{
				field : 'visitContent',
				title : '回访内容',
				width : 100,
				sortable : true
			},{
				field : 'txResault',
				title : '回访结果',
				width : 100,
				sortable : true
			}
			]],
			toolbar : [ {
				id : 'btnsearch',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function (){
				doDeletes($('#shengritixing_table_center_id'),'customerCareAction!doDelete.action')
			}
			}]
			});
			/*生日提醒结束*/
	
		//保险交强回访  历史维修记录展示
		$('#baoxianjiaoqiang_table_south_id').datagrid({
		url : 'customerCareAction!getFactoryWxRecord.action',
		fit : true,
		singleSelect : true,
		rownumbers : true,
		newrap : false,
		striped : true,
		fitColumns : false, // 自适应列宽 
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'interDate',
			title : '进店日期',
			width : 100,
			sortable : true
		} ,{
			field : 'receptionDistance',
			title : '行驶里程',
			width : 100,
			sortable : true
		} ,{
			field : 'receptor',
			title : '接待员',
			width : 100,
			sortable : true
		},{
			field : 'preclrTime',
			title : '出厂日期',
			width : 100,
			sortable : true
		},{
			field : 'stfName',
			title : '主修工',
			width : 100,
			sortable : true
		},{
			field : 'receptionId',
			title : '工单号',
			width : 100,
			sortable : true
		},{
			field : 'preclrSumAmount',
			title : '合计金额',
			width : 100,
			sortable : true
		},{
			field : 'rcptitemName',
			title : '主修项目',
			width : 100,
			sortable : true
			}
		]]
		});
		//保险交强回访  历史记录展示
		$('#baoxianjiaoqiang_table_center_id').datagrid({
		url : 'customerCareAction!factoryVisit.action',
		pagination : true,
		fit : true,
		pageSize : 4,

		singleSelect : true,
		rownumbers : true,
		pageList : [ 4, 10, 30, 40, 50],
		fitColumns : false, // 自适应列宽 
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'ffvId',
			title : 'id',
			width : 100,
			sortable : true,
			hidden : true
		} ,{
			field : 'carId',
			title : 'cid',
			width : 100,
			sortable : true,
			hidden : true
		} ,{
			field : 'returnVisitDate',
			title : '回访日期',
			width : 100,
			sortable : true
		} ,{
			field : 'txReturnVisitDate',
			title : '提醒回访日期',
			width : 100,
			sortable : true
		} ,{
			field : 'groupName',
			title : '回访类型',
			width : 100,
			sortable : true
		},{
			field : 'visitContent',
			title : '回访内容',
			width : 100,
			sortable : true
		},{
			field : 'txResault',
			title : '回访结果',
			width : 100,
			sortable : true
		}
		]],
		toolbar : [ {
			id : 'btnsearch',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDeletes($('#baoxianjiaoqiang_table_center_id'),'customerCareAction!doDelete.action')
		}
		}]
		});
		/*保险交强结束*/
		//首保回访  历史维修记录展示
		$('#shoubaotixing_table_south_id').datagrid({
		url : 'customerCareAction!getFactoryWxRecord.action',
		fit : true,
		singleSelect : true,
		newrap : false,
		striped : true,
		rownumbers : true,
		fitColumns : false, // 自适应列宽 
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'interDate',
			title : '进店日期',
			width : 100,
			sortable : true
		} ,{
			field : 'receptionDistance',
			title : '行驶里程',
			width : 100,
			sortable : true
		} ,{
			field : 'receptor',
			title : '接待员',
			width : 100,
			sortable : true
		},{
			field : 'preclrTime',
			title : '出厂日期',
			width : 100,
			sortable : true
		},{
			field : 'stfName',
			title : '主修工',
			width : 100,
			sortable : true
		},{
			field : 'receptionId',
			title : '工单号',
			width : 100,
			sortable : true
		},{
			field : 'preclrSumAmount',
			title : '合计金额',
			width : 100,
			sortable : true
		},{
			field : 'rcptitemName',
			title : '主修项目',
			width : 100,
			sortable : true
			}
		]]
		});
		//首保回访  历史记录展示
		$('#shoubaotixing_table_center_id').datagrid({
		url : 'customerCareAction!factoryVisit.action',
		pagination : true,
		fit : true,
		pageSize : 4,
		singleSelect : true,
		rownumbers : true,
		pageList : [ 4, 10, 30, 40, 50],
		fitColumns : false, // 自适应列宽 
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'ffvId',
			title : 'id',
			width : 100,
			sortable : true,
			hidden : true
		} ,{
			field : 'carId',
			title : 'cid',
			width : 100,
			sortable : true,
			hidden : true
		} ,{
			field : 'returnVisitDate',
			title : '回访日期',
			width : 100,
			sortable : true
		} ,{
			field : 'txReturnVisitDate',
			title : '提醒回访日期',
			width : 100,
			sortable : true
		} ,{
			field : 'groupName',
			title : '回访类型',
			width : 100,
			sortable : true
		},{
			field : 'visitContent',
			title : '回访内容',
			width : 100,
			sortable : true
		},{
			field : 'txResault',
			title : '提醒结果',
			width : 100,
			sortable : true
		},{
			field : 'statusName',
			title : '流失原因',
			width : 100,
			sortable : true
		}
		]],
		toolbar : [ {
			id : 'btnsearch',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDeletes($('#shoubaotixing_table_south_id'),'customerCareAction!doDelete.action')
		}
		}]
		});
	/*首保提醒结束*/
	//年检年审提醒回访  历史维修记录展示
	$('#nianjiannianshen_table_south_id').datagrid({
	url : 'customerCareAction!getFactoryWxRecord.action',
	fit : true,
	singleSelect : true,
	newrap : false,
	striped : true,
	rownumbers : true,
	fitColumns : false, // 自适应列宽 
	idField : 'id',
	loadMsg : "数据加载中，请稍后……",
	columns : [ [ {
		field : 'interDate',
		title : '进店日期',
		width : 100,
		sortable : true
	} ,{
		field : 'receptionDistance',
		title : '行驶里程',
		width : 100,
		sortable : true
	} ,{
		field : 'receptor',
		title : '接待员',
		width : 100,
		sortable : true
	},{
		field : 'preclrTime',
		title : '出厂日期',
		width : 100,
		sortable : true
	},{
		field : 'stfName',
		title : '主修工',
		width : 100,
		sortable : true
	},{
		field : 'receptionId',
		title : '工单号',
		width : 100,
		sortable : true
	},{
		field : 'preclrSumAmount',
		title : '合计金额',
		width : 100,
		sortable : true
	},{
		field : 'rcptitemName',
		title : '主修项目',
		width : 100,
		sortable : true
	}
	]]

});

//年检年审提醒回访  历史回访记录展示
$('#nianjiannianshen_table_center_id').datagrid({
	url : 'customerCareAction!factoryVisit.action',
	pagination : true,
	fit : true,
	pageSize : 4,
	singleSelect : true,
	rownumbers : true,
	pageList : [ 4, 10, 30, 40, 50],
	fitColumns : false, // 自适应列宽 
	idField : 'id',
	loadMsg : "数据加载中，请稍后……",
	columns : [ [ {
		field : 'ffvId',
		title : 'id',
		width : 100,
		sortable : true,
		hidden : true
	},{
		field : 'carId',
		title : 'cid',
		width : 100,
		sortable : true,
		hidden : true
	},{
		field : 'returnVisitDate',
		title : '回访日期',
		width : 100,
		sortable : true
	},{
		field : 'txReturnVisitDate',
		title : '提醒回访日期',
		width : 100,
		sortable : true
	},{
		field : 'groupName',
		title : '回访类型',
		width : 100,
		sortable : true
	},{
		field : 'visitContent',
		title : '回访内容',
		width : 100,
		sortable : true
	},{
		field : 'txResault',
		title : '回访结果',
		width : 100,
		sortable : true
	}
	]],
	toolbar : [ {
		id : 'btnsearch',
		text : '删除',
		iconCls : 'icon-remove',
		handler : function (){
		doDeletes($('#nianjiannianshen_table_center_id'),'customerCareAction!doDelete.action')
	}
	}]
});
	/*年检年审结束*/
	//保养提醒回访  历史维修记录展示
	$('#baoyangtixing_table_south_id').datagrid({
		url : 'customerCareAction!getFactoryWxRecord.action',
		fit : true,
		singleSelect : true,
		rownumbers : true,
		newrap : false,
		striped : true,
		fitColumns : false, // 自适应列宽 
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'interDate',
			title : '进店日期',
			width : 100,
			sortable : true
		} ,{
			field : 'receptionDistance',
			title : '行驶里程',
			width : 100,
			sortable : true
		} ,{
			field : 'receptor',
			title : '接待员',
			width : 100,
			sortable : true
		},{
			field : 'preclrTime',
			title : '出厂日期',
			width : 100,
			sortable : true
		},{
			field : 'stfName',
			title : '主修工',
			width : 100,
			sortable : true
		},{
			field : 'receptionId',
			title : '工单号',
			width : 100,
			sortable : true
		},{
			field : 'preclrSumAmount',
			title : '合计金额',
			width : 100,
			sortable : true
		},{
			field : 'rcptitemName',
			title : '主修项目',
			width : 100,
			sortable : true
		}
		]]
	
	});

	//保养提醒回访  历史维修记录展示
	$('#baoyangtixing_table_center_id').datagrid({

		url : 'customerCareAction!factoryVisit.action',
		pagination : true,
		fit : true,
		pageSize : 4,
		singleSelect : true,
		rownumbers : true,
		pageList : [ 4, 10, 30, 40, 50],
		fitColumns : false, //自适应列宽 
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'ffvId',
			title : 'id',
			width : 100,
			sortable : true,
			hidden : true
		} ,{
			field : 'carId',
			title : 'cid',
			width : 100,
			sortable : true,
			hidden : true
		} ,{
			field : 'returnVisitDate',
			title : '回访日期',
			width : 100,
			sortable : true
		} ,{
			field : 'txReturnVisitDate',
			title : '提醒回访日期',
			width : 100,
			sortable : true
		} ,{
			field : 'groupName',
			title : '回访类型',
			width : 100,
			sortable : true
		},{
			field : 'visitContent',
			title : '回访内容',
			width : 100,
			sortable : true
		},{
			field : 'txResault',
			title : '提醒结果',
			width : 100,
			sortable : true
		},{
			field : 'statusName',
			title : '流失原因',
			width : 100,
			sortable : true
		},{
			field : '',
			title : '回访结果',
			width : 100,
			sortable : true
		}
		]],
		toolbar : [ {
			id : 'btnsearch',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDeletes($('#baoyangtixing_table_center_id'),'customerCareAction!doDelete.action')
			}
		}]
	});
	//客户满意度统计khmydtj_id    
	$('#').datagrid({
		url : '',//vTrackRecordAction_findTrack.action
		pagination : true,
		fit : true,
		pageSize : 10,
		singleSelect : true,
		rownumbers : true,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : true, // 自适应列宽 
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'drId',
			title : '一月份',
			width : 100,
			sortable : true
		} ,{
			field : 'carLicense',
			title : '二月份',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'customName',
			title : '三月份',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'txInfomation',
			title : '四月份',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'memoInfomation',
			title : '五月份',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'txDj',
			title : '六月份',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'clStatus',
			title : '七月份',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'clStatus',
			title : '八月份',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'clStatus',
			title : '九月份',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'clStatus',
			title : '十月份',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'clStatus',
			title : '十一月份',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'clStatus',
			title : '十二月份',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}
		]],
		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
			fandTrack($('#customer_GzTj_form_id'),'vTrackRecordAction_findTrack.action');
		}
		}]
	
	});
	
	//日常客户跟踪
	$('#Daily_client_tracking_center_id').datagrid({

		url : 'dailyclientTrackingAction_doFind.action',
		pagination : true,
		fit : true,
		pageSize : 10,
		newrap : false,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : true, // 自适应列宽 
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'drId',
			title : 'id',
			width : 100,
			sortable : true,
			hidden : true
		} ,{
			field : 'carLicense',
			title : '车辆牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'customName',
			title : '客户姓名',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'txInfomation',
			title : '提醒信息',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'memoInfomation',
			title : '备注信息',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'txDj',
			title : '提醒等级',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'clStatus',
			title : '处理情况',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}
		]],
		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Daily_client_tracking_center_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#Daily_client_tracking_center_id'),'dailyclientTrackingAction_doDelete.action','dailyclientTrackingAction_doFind.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Daily_client_tracking_center_id'));
			}
		},{
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
			doSubmitNotPR($('#Daily_client_tracking_center_id'),$('#richanggenzongtixing_form_id'),'dailyclientTrackingAction_findByCondition.action');
		}
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#Daily_client_tracking_center_id'),'dailyclientTrackingAction_doAdd.action','dailyclientTrackingAction_doUpdate.action','dailyclientTrackingAction_doFind.action',rowIndex, rowData, changes);
	}	
	
	});
	//新车跟踪提醒
	//south内容
	$('#New_Car_trackremind_center_south_id').datagrid({

		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		newrap : false,
		striped : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			checkbox : true
		}, {
			field : 'id1',
			title : '跟踪单号',
			width : 100,
			sortable : true
			
		}, {
			field : 'id2',
			title : '跟踪日期照',
			width : 100,
			sortable : true
		
		}, {
			field : 'id3',
			title : '满意程度',
			width : 100,
			sortable : true
			
		} ,{
			field : 'id4',
			title : '回访次数',
			width : 100,
			sortable : true
			
		}, {
			field : 'id5',
			title : '通话情况',
			width : 100,
			sortable : true
		}, {
			field : 'id6',
			title : '行驶里程',
			width : 100,
			sortable : true
		}, {
			field : 'id7',
			title : '信息反馈',
			width : 100,
			sortable : true
		}, {
			field : 'id8',
			title : '备注',
			width : 100,
			sortable : true
		}, {
			field : 'id9',
			title : '跟踪人员',
			width : 100,
			sortable : true
		}]]
	});
	//center内容
	$('#New_Car_trackremind_center_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		newrap : false,
		striped : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			checkbox : true
		}, {
			field : 'id1',
			title : '序号',
			width : 100,
			sortable : true
			
		}, {
			field : 'id2',
			title : '车牌照',
			width : 100,
			sortable : true
		
		}, {
			field : 'id3',
			title : '车型号',
			width : 100,
			sortable : true
			
		} ,{
			field : 'id4',
			title : '颜色',
			width : 100,
			sortable : true
			
		}, {
			field : 'id5',
			title : 'VIN号',
			width : 100,
			sortable : true
		}, {
			field : 'id6',
			title : '销售日期',
			width : 100,
			sortable : true
		}, {
			field : 'id7',
			title : '客户姓名',
			width : 100,
			sortable : true
		}, {
			field : 'id8',
			title : '联系人',
			width : 100,
			sortable : true
		}, {
			field : 'id9',
			title : '固定电话',
			width : 100,
			sortable : true
		}, {
			field : 'id10',
			title : '手机',
			width : 100,
			sortable : true
		}, {
			field : 'id11',
			title : '客户地址',
			width : 100,
			sortable : true
		}, {
			field : 'id12',
			title : '首保提醒员',
			width : 100,
			sortable : true
		}, {
			field : 'id13',
			title : '备注',
			width : 100,
			sortable : true
		}, {
			field : 'id14',
			title : '预计首保',
			width : 100,
			sortable : true
		}, {
			field : 'id15',
			title : '客户编号',
			width : 100,
			sortable : true
		}]]
	
	
	});
	//车俩保单查询
	$('#Car_policy_find_center_id').datagrid({

		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		newrap : false,
		striped : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [{
			checkbox : true
		}, {
			field : 'id1',
			title : '代保日期',
			width : 100,
			sortable : true
			
		}, {
			field : 'id2',
			title : '记录编号',
			width : 100,
			sortable : true
		
		}] ],
		columns : [ [ {
			field : 'id3',
			title : '审核情况',
			width : 100,
			sortable : true
			
		} ,{
			field : 'id4',
			title : '保险单号',
			width : 100,
			sortable : true
			
		}, {
			field : 'id5',
			title : '商保发票号',
			width : 100,
			sortable : true
			
		}, {
			field : 'id6',
			title : '交强单号',
			width : 100,
			sortable : true
		
		}, {
			field : 'id7',
			title : '交强发票号',
			width : 100,
			sortable : true
			
		}, {
			field : 'id8',
			title : '车辆牌照',
			width : 100,
			sortable : true
			
		}, {
			field : 'id9',
			title : 'VIN码',
			width : 100,
			sortable : true
			
		}, {
			field : 'id10',
			title : '被保险人',
			width : 100,
			sortable : true
		
		}, {
			field : 'id11',
			title : '上年保险公司',
			width : 100,
			sortable : true
		
		}, {
			field : 'id12',
			title : '保险公司',
			width : 100,
			sortable : true
		
		}, {
			field : 'id13',
			title : '省份证号码',
			width : 100,
			sortable : true
			
		}, {
			field : 'id14',
			title : '被保地址',
			width : 100,
			sortable : true
			
		}, {
			field : 'id15',
			title : '车辆价格',
			width : 100,
			sortable : true
			
		}, {
			field : 'id16',
			title : '联系电话',
			width : 100,
			sortable : true
			
		}, {
			field : 'id17',
			title : '发动机号',
			width : 100,
			sortable : true
		}, {
			field : 'id18',
			title : '厂牌型号',
			width : 100,
			sortable : true
		}, {
			field : 'id19',
			title : '车身颜色',
			width : 100,
			sortable : true
		}, {
			field : 'id20',
			title : '商业到期',
			width : 100,
			sortable : true
		}, {
			field : 'id21',
			title : '交强到期',
			width : 100,
			sortable : true
		}, {
			field : 'id22',
			title : '商业险',
			width : 100,
			sortable : true
		}, {
			field : 'id23',
			title : '商保实收',
			width : 100,
			sortable : true
		}, {
			field : 'id24',
			title : '交强险',
			width : 100,
			sortable : true
		}, {
			field : 'id25',
			title : '交强实收',
			width : 100,
			sortable : true
		}, {
			field : 'id26',
			title : '车船税',
			width : 100,
			sortable : true
		}, {
			field : 'id27',
			title : '车船税号',
			width : 100,
			sortable : true
		}, {
			field : 'id28',
			title : '上交保费',
			width : 100,
			sortable : true
		}, {
			field : 'id29',
			title : '实付款',
			width : 100,
			sortable : true
		}, {
			field : 'id30',
			title : '登记年月',
			width : 100,
			sortable : true
		}, {
			field : 'id31',
			title : '客户实付',
			width : 100,
			sortable : true
		}, {
			field : 'id32',
			title : '客户回款',
			width : 100,
			sortable : true
		}, {
			field : 'id33',
			title : '利润',
			width : 100,
			sortable : true
		}, {
			field : 'id34',
			title : '联系人',
			width : 100,
			sortable : true
		}, {
			field : 'id35',
			title : '优惠率',
			width : 100,
			sortable : true
		}, {
			field : 'id36',
			title : '业务提成',
			width : 100,
			sortable : true
		}, {
			field : 'id37',
			title : '赠送项目',
			width : 100,
			sortable : true
		}, {
			field : 'id38',
			title : '票券编号',
			width : 100,
			sortable : true
		}, {
			field : 'id39',
			title : '赠送价值',
			width : 100,
			sortable : true
		}, {
			field : 'id40',
			title : '刷卡类型',
			width : 100,
			sortable : true
		}, {
			field : 'id41',
			title : '收款日期',
			width : 100,
			sortable : true
		}, {
			field : 'id42',
			title : '业务部门',
			width : 100,
			sortable : true
		}, {
			field : 'id43',
			title : '经办人',
			width : 100,
			sortable : true
		}, {
			field : 'id44',
			title : '发票类型',
			width : 100,
			sortable : true
		}, {
			field : 'id45',
			title : '接待人员',
			width : 100,
			sortable : true
		}, {
			field : 'id46',
			title : '保险分类',
			width : 100,
			sortable : true
		}, {
			field : 'id47',
			title : '备注',
			width : 100,
			sortable : true
		}] ],

		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-cancel',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}]
	});

	//保单汇总
	$('#Car_policy_management_center_id').datagrid({
		url : 'carInsuranceManageAction_onlyFind.action',
		pagination : true,
		fit : true,
		fitColumns : false,
		singleSelect : true,
		newrap : false,
		striped : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'carInsuranceManageId',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [  {
			field : 'carInsuranceManageId',
			title : 'ID',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'insuranceDate',
			title : '代保日期',
			width : 100,
			sortable : true
			
		}, {
			field : 'recordNumber',
			title : '记录编号',
			width : 100,
			sortable : true
		
		},{
			field : 'auditSituation',
			title : '审核情况',
			width : 100,
			sortable : true
			
		} ,{
			field : 'insuranceNumber',
			title : '保险单号',
			width : 100,
			sortable : true
			
		}, {
			field : 'invoiceNumber',
			title : '商保发票号',
			width : 100,
			sortable : true
			
		}, {
			field : 'jqSingleNumber',
			title : '交强单号',
			width : 100,
			sortable : true
		
		}, {
			field : 'jqInvoiceNumber',
			title : '交强发票号',
			width : 100,
			sortable : true
			
		}, {
			field : 'carBrand',
			title : '车辆牌照',
			width : 100,
			sortable : true
			
		}, {
			field : 'vinNumber',
			title : 'VIN码',
			width : 100,
			sortable : true
			
		}, {
			field : 'theInsuredPerson',
			title : '被保险人',
			width : 100,
			sortable : true
		
		}, {
			field : 'nextInsuranceCompany',
			title : '上年保险公司',
			width : 100,
			sortable : true
		
		}, {
			field : 'insuranceCompany',
			title : '保险公司',
			width : 100,
			sortable : true
		
		}, {
			field : 'idCard',
			title : '省份证号码',
			width : 100,
			sortable : true
			
		}, {
			field : 'insuredAddress',
			title : '被保地址',
			width : 100,
			sortable : true
			
		}, {
			field : 'carPrice',
			title : '车辆价格',
			width : 100,
			sortable : true
			
		}, {
			field : 'tel',
			title : '联系电话',
			width : 100,
			sortable : true
			
		}, {
			field : 'engineNumber',
			title : '发动机号',
			width : 100,
			sortable : true
		}, {
			field : 'brandModel',
			title : '厂牌型号',
			width : 100,
			sortable : true
		}, {
			field : 'carColor',
			title : '车身颜色',
			width : 100,
			sortable : true
		}, {
			field : 'businessDate',
			title : '商业到期',
			width : 100,
			sortable : true
		}, {
			field : 'jqDate',
			title : '交强到期',
			width : 100,
			sortable : true
		}, {
			field : 'businessInsurance',
			title : '商业险',
			width : 100,
			sortable : true
		}, {
			field : 'businessPaid',
			title : '商保实收',
			width : 100,
			sortable : true
		}, {
			field : 'jqInsurance',
			title : '交强险',
			width : 100,
			sortable : true
		}, {
			field : 'jqPaid',
			title : '交强实收',
			width : 100,
			sortable : true
		}, {
			field : 'travelTax',
			title : '车船税',
			width : 100,
			sortable : true
		}, {
			field : 'travelTaxNumber',
			title : '车船税号',
			width : 100,
			sortable : true
		}, {
			field : 'premiums',
			title : '上交保费',
			width : 100,
			sortable : true
		}, {
			field : 'actuallyPaid',
			title : '实付款',
			width : 100,
			sortable : true
		}, {
			field : 'registerDate',
			title : '登记年月',
			width : 100,
			sortable : true
		}, {
			field : 'customerPaid',
			title : '客户实付',
			width : 100,
			sortable : true
		}, {
			field : 'customerBacksection',
			title : '客户回款',
			width : 100,
			sortable : true
		}, {
			field : 'profit',
			title : '利润',
			width : 100,
			sortable : true
		}, {
			field : 'contact',
			title : '联系人',
			width : 100,
			sortable : true
		}, {
			field : 'discountRate',
			title : '优惠率',
			width : 100,
			sortable : true
		}, {
			field : 'commissionBusiness',
			title : '业务提成',
			width : 100,
			sortable : true
		}, {
			field : 'giftItems',
			title : '赠送项目',
			width : 100,
			sortable : true
		}, {
			field : 'billsNumber',
			title : '票券编号',
			width : 100,
			sortable : true
		}, {
			field : 'presentationValue',
			title : '赠送价值',
			width : 100,
			sortable : true
		}, {
			field : 'credirCardTypes',
			title : '刷卡类型',
			width : 100,
			sortable : true
		}, {
			field : 'receiptDate',
			title : '收款日期',
			width : 100,
			sortable : true
		}, {
			field : 'businessUnits',
			title : '业务部门',
			width : 100,
			sortable : true
		}, {
			field : 'manager',
			title : '经办人',
			width : 100,
			sortable : true
		}, {
			field : 'invoiceType',
			title : '发票类型',
			width : 100,
			sortable : true
		}, {
			field : 'receptor',
			title : '接待人员',
			width : 100,
			sortable : true
		}, {
			field : 'insuranceGroup',
			title : '保险分类',
			width : 100,
			sortable : true
		}, {
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true
		}]],
		onDblClickRow : function(rowIndex, rowData){
		onDbClickRow(rowIndex, rowData);
	}
	});
	
	//车辆保单管理
	//保单明细
	$('#car_policy_management_detial_id').datagrid({
		url : 'carInsuranceManageAction_doFindByInsuranceType.action',
		pagination : false,
		fit : true,
		fitColumns : true,
		newrap : false,
		striped : true,
		pageSize : 10,
		rownumbers : true,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			checkbox : true
		},{
			field : 'id',
			title : 'ID',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'intype',
			title : '险种',
			width : 100,
			sortable : true
			
		}, {
			field : 'incount',
			title : '保险金额(限)',
			width : 100,
			sortable : true
		
		}, {
			field : 'infeelv',
			title : '费率(%)',
			width : 100,
			sortable : true
			
		} ,{
			field : 'infee',
			title : '保险费',
			width : 100,
			sortable : true
			
		}]]
	
	});
	// 跟踪管理汇总
	$('#customer_genz1').datagrid({
		url : 'customerGzManageAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : false,
		newrap : false,
		striped : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		rownumbers : true,
		singleSelect : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'carLicense',
			title : '车牌照',
			width : 100,
			sortable : true
		},{
			field : 'returnVisitDate',
			title : '回访日期',
			width : 100,
			sortable : true,
		},{
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true,
		},{
			field : 'customTel1',
			title : '客户电话',
			width : 100,
			sortable : true
		
		},{
			field : 'carVan',
			title : 'Vin号',
			width : 100,
			sortable : true
			
		} ,{
			field : 'rcptitemName',
			title : '维修项目',
			width : 100,
			sortable : true
			
		}, {
			field : 'ctypeName',
			title : '品牌-车型',
			width : 100,
			sortable : true
			
		}, {
			field : 'receptionDistance',
			title : '公里数',
			width : 100,
			sortable : true
		
		}, {
			field : 'customLinkMan',
			title : '客户联系人',
			width : 100,
			sortable : true
			
		}, {
			field : 'receptionId',
			title : '工单号',
			width : 100,
			sortable : true
			
		}/*, {
			field : '',
			title : '自编号',
			width : 100,
			sortable : true
			
		}*/, {
			field : 'interDate',
			title : '进厂日期',
			width : 100,
			sortable : true
		
		}, {
			field : 'preclrTime',
			title : '结算日期',
			width : 100,
			sortable : true
		
		}, {
			field : 'preclrId',
			title : '结算单号',
			width : 100,
			sortable : true
			
		}, {
			field : 'receptor',
			title : '前台接待',
			width : 100,
			sortable : true
			
		}, {
			field : 'carBuyDate',
			title : '购车日期',
			width : 100,
			sortable : true
			
		}, {
			field : 'propRepPer',
			title : '托修人',
			width : 100,
			sortable : true
			
		}, {
			field : 'propPhone',
			title : '托修人电话',
			width : 100,
			sortable : true
		}, {
			field : 'customAddr',
			title : '地址',
			width : 100,
			sortable : true
		}, {
			field : 'repgrpId',
			title : '维修班组',
			width : 100,
			sortable : true
		}, {
			field : 'satisfaction',
			title : '满意度',
			width : 100,
			sortable : true
		}/*, {
			field : '',
			title : '用户建议及意见',
			width : 100,
			sortable : true
		}*/, {
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true
		}, {
			field : 'preclrRealAmount',
			title : '实际收款',
			width : 100,
			sortable : true
		}/*, {
			field : '',
			title : '处理时间及结果',
			width : 100,
			sortable : true
		}*/, {
			field : 'color',
			title : '颜色',
			width : 100,
			sortable : true
		}, {
			field : 'carMotorId',
			title : '发动机号',
			width : 100,
			sortable : true
		}, {
			field : 'returnVisitMembers',
			title : '回访人',
			width : 100,
			sortable : true
		}, {
			field : 'callSituation',
			title : '通话情况',
			width : 100,
			sortable : true
		}, {
			field : 'reptName',
			title : '维修类别',
			width : 100,
			sortable : true
		}, {
			field : 'reciptReturnvisit',
			title : '接收回访',
			width : 100,
			sortable : true
		}/*, {
			field : '',
			title : '不满意处理员',
			width : 100,
			sortable : true
		}*/, {
			field : 'customSex',
			title : '性别',
			width : 100,
			sortable : true
		}, {
			field : 'complaintContent',
			title : '客户投诉内容',
			width : 100,
			sortable : true
		}, {
			field : 'handleResult',
			title : '投诉处理结果',
			width : 100,
			sortable : true
		}, {
			field : 'complaintType',
			title : '投诉类型',
			width : 100,
			sortable : true
		}, {
			field : 'complaintDegree',
			title : '投诉程度',
			width : 100,
			sortable : true 
		}, {
			field : 'preclrRemark',
			title : '结算备注',
			width : 100,
			sortable : true,
			hidden:true
		}, {
			field : 'problemDesc',
			title : '故障描述',
			width : 100,
			sortable : true,
			hidden:true
		}, {
			field : 'handleProgram',
			title : '处理方案',
			width : 100,
			sortable : true,
			hidden:true
		}, {
			field : 'serviceProposal',
			title : '维修建议',
			width : 100,
			sortable : true ,
			hidden:true
		}, {
			field : 'handlePerson',
			title : '投诉处理人',
			width : 100,
			sortable : true ,
			hidden:true
		},{
			field : 'carId',
			title : '车辆id',
			width : 100,
			sortable : true,
			hidden:true
		}] ],
		onDblClickRow : function(rowIndex, rowData){
			Dbclick(rowData);
		}
	});
	
	
	
	
	// 跟踪记录汇总（此datagrid在页面上并接而成）
	
	
	// 跟踪管理--明细(工单维修明细,用户信息反馈及建议,投诉及处理结果)
	// 用户信息反馈
	var $user_info_back = $('#user_info_back');
	$user_info_back.datagrid( {
		url : '',
		fit : true,
		fitColumns : false,
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'id1',
			title : '用户信息返库',
			width : 100
			
		}, {
			field : 'id2',
			title : '备注',
			width : 100
		}, {
			field : 'id3',
			title : '处理方案',
			width : 100
		}, {
			field : 'id4',
			title : '维修建议',
			width : 100
		} ] ],
		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnsearch',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnedit',
			text : '设置',
			iconCls : 'icon-help',
			handler : function (){
		   	alert('!!!!!!');}
		}]

	});
	// 工单维修明细
	var $weixiu_details = $('#weixiu_details');
	$weixiu_details.datagrid( {
		url : '',
		fit : true,
		fitColumns : false,
		newrap : false,
		striped : true,
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'id1',
			title : '维修内容',
			width : 100
		}, {
			field : 'id2',
			title : '结算备注',
			width : 100
		}, {
			field : 'id3',
			title : '投诉及处理结果',
			width : 100
		} ] ],
		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnsearch',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}

		}, '-', {
			id : 'btnedit',
			text : '设置',
			iconCls : 'icon-help',
			handler : function (){
		   	alert('!!!!!!');}
		}]

	});
	// 跟踪管理3DC调查
	var $customer_genz3 = $('#customer_genz3');
	$customer_genz3.datagrid({
		pagination : true,
		fit : true,
		newrap : false,
		striped : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'dcNameId',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'dcNameId',
			title : 'ID',
			hidden : true
		},{
			field : 'preclrId',
			title : '结算单号',
			hidden : true
		},{
			field : 'projectName',
			title : '项目名称',
			width : 200
		},{
			field : 'evaluateOne',
			title : '很好',
			width : 50
		},{
			field : 'evaluateTwo',
			title : '好',
			width : 50
		},{
			field : 'evaluateThree',
			title : '一般',
			width : 50
		},{
			field : 'evaluateFour',
			title : '不好',
			width : 50
		},{
			field : 'evaluateFive',
			title : '很不好',
			width : 50
		},{
			field : 'score',
			title : '评分',
			width : 50
		},{
			field : 'note',
			title : '备注信息',
			width : 300
		}] ],
		
	onClickRow : function (rowIndex, rowData){
		if(hfid==1){
		$('#customer_genz3').datagrid('beginEdit',rowIndex);
		}
	},
	onAfterEdit : function(rowIndex, rowData, changes){
		onafterSubmit($('#customer_genz3'),rowIndex, rowData, changes);
	}
	});

	// 跟踪管理历史满意度
	$customer_genz2 = $('#customer_genz2');
	$customer_genz2.datagrid( {
		pagination : true,
		fit : true,
		newrap : false,
		striped : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'returnVisitDate',
			title : '回访日期',
			width : 100
		}, {
			field : 'satisfaction',
			title : '满意度',
			width : 100
		}, {
			field : 'complaintContent',
			title : '信息反馈',
			width : 100
		} ] ]
	});

	// 会员积分三
	var $VIP_score_find3 = $('#VIP_score_find3');
	$VIP_score_find3.datagrid( {
		url : '',
		pagination : true,
		fit : true,
		newrap : false,
		striped : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			checkbox : true
		}, {
			field : '',
			title : '序号',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '会员号',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '牌照号',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '单据日期',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '单据编号',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '分类',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '消费积分',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '说明',
			width : 100,
			sortable : true
		} ] ],

		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}

		}]
	});
	// 会员积分查询二
	var $VIP_score_find2 = $('#VIP_score_find2');
	$VIP_score_find2.datagrid( {
		url : '',
		pagination : true,
		fit : true,
		newrap : false,
		striped : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			checkbox : true
		},{
			field : '',
			title : '序号',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '结算日期',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '结算工单号',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '车辆牌照',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '会员号',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '客户名称',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '结算金额',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '本次积分',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '积分类型',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '说明',
			width : 100,
			sortable : true
		}

		] ],

		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}

		}]

	});
	// 会员积分查询1
	var $VIP_score_find1 = $('#VIP_score_find1');
	$VIP_score_find1.datagrid( {
		url : '',
		pagination : true,
		fit : true,
		newrap : false,
		striped : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			checkbox : true
		}, {
			field : '',
			title : '序号',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '客户名称',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '会员号',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '会员类别',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '车辆牌照',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '车辆品牌',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '车辆类型',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '累计积分',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '累计消分',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '当前积分',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '电话一',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '电话二',
			width : 100,
			sortable : true
		}

		] ],

		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}

		}, '-']
	});
	// 会员项目服务查询
	var $project_score_find = $('#project_score_find');
	$project_score_find.datagrid( {

		url : '',
		pagination : true,
		fit : true,
		newrap : false,
		striped : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			checkbox : true
		},{
			field : '',
			title : '工单号',
			width : 100,
			 
			sortable : true
		}, {
			field : '',
			title : '工作时间',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '车牌照',
			width : 100,
			sortable : true
			
		}, {
			field : '',
			title : '服务项目',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '服务金额',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '优惠率',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '优惠金额',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '其他费用',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '合计收费',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '超服里程',
			width : 100,
			sortable : true
		}, {
			field : '',
			title : '备注',
			width : 100,
			sortable : true
		}

		] ],
		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}

		}, '-']

	});
	// 车辆维修积分查询
	var $car_score_find = $('#car_score_find');
	$car_score_find.datagrid( {
		url : '',
		pagination : true,
		fit : true,
		newrap : false,
		striped : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			checkbox : true
		},{
			field : '',
			title : '序号',
			width : 100,
			 
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true

				}
			}
		}, {
			field : '',
			title : '结算日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {
					required : true
				}
			}
		}, {
			field : '',
			title : '工单号',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '车辆牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '会员号',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '客户名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '结算金额',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '积分类型',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '本次积分',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '获赠维修金',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '积分说明',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}

		] ],

		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}

		}, '-']

	});
	// 赠送积分单/销售积分单查询
	var $zxiao_score_find = $('#zxiao_score_find');
	$zxiao_score_find.datagrid( {

		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		pageSize : 10,
		newrap : false,
		striped : true,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			checkbox : true
		},{
			field : '',
			title : '序号',
			width : 100,
			 
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true

				}
			}
		}, {
			field : '',
			title : '单据日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {
					required : true
				}
			}
		}, {
			field : '',
			title : '单据类型',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '单据号码',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '车牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '会员号',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '会员名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '积分',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : '',
			title : '备注信息',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}

		] ],

		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}

		}, '-']

	});

	// 維修合同汇总
	var $table9 = $('#table9');
	$table9.datagrid( {

		url : '',
		pagination : true,
		fit : true,
		newrap : false,
		striped : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false, // 自适应列宽
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [ {
			checkbox : true
		}, {
			field : 'id',
			title : '序号',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true

				}
			}
		}, {
			field : 'car_num',
			title : '车牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true

				}
			}
		} ] ],
		columns : [ [ {
			field : 'visit_date',
			title : '回访日期',
			width : 100,
			 
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true

				}
			}
		}, {
			field : 'user_name',
			title : '客户名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {
					required : true
				}
			}
		}, {
			field : 'user_phone',
			title : '客户电话',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : 'VIN_num',
			title : 'VIN号',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : 'weixiu_project',
			title : '维修项目',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}, {
			field : 'pingpai_chexing',
			title : '品牌/车型',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}

		] ],

		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnupdate',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
		   	alert('!!!!!!');}

		}, '-', {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}

		}, '-', {
			id : 'btntotal',
			text : '汇总',
			iconCls : 'icon-undo',
			handler : function (){
		   	alert('!!!!!!');}

		}, '-', {
			id : 'btndetail',
			text : '明细',
			iconCls : 'icon-tip',
			handler : function (){
		   	alert('!!!!!!');}

		}, '-', {
			id : 'btnmanyidu',
			text : '历史满意度',
			iconCls : 'icon-undo'

		}, '-', {
			id : 'btn3dc',
			text : '3DC调查',
			iconCls : 'icon-undo',
			handler : function (){
		   	alert('!!!!!!');}
		} ]
	});
	// 提醒查询
	//提醒查询east内容
	var $rember_find_east_id = $('#rember_find_east_id');
	$rember_find_east_id.datagrid({
		url : 'customerCareAction!doResualt.action', //
		fit : true,
		fitColumns : true, // 自适应列宽
		newrap : false,
		striped : true,
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'totals',
			title : '总数',
			width : 150,
			rowspan : 2,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'statusName',
			title : '结果分类',
			width : 200,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'number',
			title : '数量',
			width : 150,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'bili',
			title : '比例',
			width : 150,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}
		] ]
	});
	//提醒查询center内容
	var $rember_find_id = $('#rember_find_id');
	$rember_find_id.datagrid( {

		url : 'customerCareAction!getRemenberSearch.action',
		pagination : true,
		fit : true,
		pageSize : 10,
		newrap : false,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false, // 自适应列宽
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'carId',
			title : '车辆id',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'carLicense',
			title : '车辆牌照',
			width : 100,
			 
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'carCstlName',
			title : '车品牌',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'carRelationTel1',
			title : '联系电话',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carBuyDate',
			title : '购车日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datebox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carVan',
			title : 'VIN号',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'returnVisitDate',
			title : '回访日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datebox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'txReturnVisitDate',
			title : '提醒回访日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datebox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'groupName',
			title : '回访类型',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'visitContent',
			title : '回访内容',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastRepairDate',
			title : '最近维修日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetime',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastRepairDistance',
			title : '最近里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRepairCnt',
			title : '维修次数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'txResault',
			title : '提醒结果',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'statusName',
			title : '流失原因',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'wlcDays',
			title : '未来厂天数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}

		] ]

	});
	
	// 会员到期提醒
	$('#vip_tixing_id').datagrid( {

		url : '',
		pagination : true,
		fit : true,
		pageSize : 10,
		singleSelect : true,
		newrap : false,
		striped : true,
		rownumbers : true,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false, // 自适应列宽
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [ {
			field : 'carId',
			title : '车辆id',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'carLicense',
			title : '车牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'ctypeName',
			title : '车品牌',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		} ] ],
		columns : [ [ {
			field : 'carCstlName',
			title : '车型号',
			width : 100,
			 
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'endTime',
			title : '会员到期',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationTel1',
			title : '车主手机',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationTel2',
			title : '车主固话',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'receptionRepPer',
			title : '客联系人',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationPerson',
			title : '驾驶姓名',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'propTel',
			title : '托修固话',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'propPhone',
			title : '托修手机',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'pareaName',
			title : '联系地址',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastRepairDate',
			title : '最近维修日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetime',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastRepairDistance',
			title : '最近维修里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRepairCnt',
			title : '维修次数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carDistancePerDay',
			title : '日均里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'receptor',
			title : '最近接待员',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'wlcDays',
			title : '未来厂天数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}

		] ]

	});
	// 生日提醒
	$('#birthday_tixing_id').datagrid( {

		url : 'customerCareAction!getSrtixing.action',
		pagination : true,
		fit : true,
		pageSize : 10,
		singleSelect : true,
		newrap : false,
		striped : true,
		rownumbers : true,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false, // 自适应列宽
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [ {
			field : 'carId',
			title : '车辆id',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'carLicense',
			title : '车牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'ctypeName',
			title : '车品牌',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true

				}
			}
		} ] ],
		columns : [ [ {
			field : 'carCstlName',
			title : '车型号',
			width : 100,
			 
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'customBirthday',
			title : '出生年月',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationTel1',
			title : '车主手机',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationTel2',
			title : '车主固话',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'receptionRepPer',
			title : '客联系人',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationPerson',
			title : '驾驶姓名',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'propTel',
			title : '托修固话',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'propPhone',
			title : '托修手机',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'customNameAddr',
			title : '联系地址',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastRepairDate',
			title : '最近维修日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetime',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastRepairDistance',
			title : '最近维修里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRepairCnt',
			title : '维修次数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carDistancePerDay',
			title : '日均里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'receptor',
			title : '最近接待员',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'wlcDays',
			title : '未来厂天数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}

		] ]
	});
	//保险/交强提醒
	$('#baoxian_jiaoqiang_id').datagrid( {
		url : 'customerCareAction!getBxjqtixing.action',
		pagination : true,
		fit : true,
		pageSize : 10,
		singleSelect : true,
		newrap : false,
		striped : true,
		rownumbers : true,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false, // 自适应列宽
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [  {
			field : 'carId',
			title : '车辆id',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'carLicense',
			title : '车牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'ctypeName',
			title : '车品牌',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		},{
			field : 'carCstlName',
			title : '车型号',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		} ,{field : 'carBasinsuranceDate',
			title : '交强到期',
			width : 100,
			 
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {
					required : true
				}
			}
		}, {field : 'carBusinsuranceDate',
			title : '保险到期',
			width : 100,
			 
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id4',
			title : '保险公司',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationTel1',
			title : '车主手机',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationTel2',
			title : '车主固话',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'receptionRepPer',
			title : '客联系人',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationPerson',
			title : '驾驶姓名',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'propTel',
			title : '托修固话',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'propPhone',
			title : '托修手机',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'customNameAddr',
			title : '联系地址',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastRepairDate',
			title : '最近维修日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetime',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastRepairDistance',
			title : '最近维修里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRepairCnt',
			title : '维修次数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carDistancePerDay',
			title : '日均里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'receptor',
			title : '最近接待员',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'txResault',
			title : '回访结果',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'wlcDays',
			title : '未来厂天数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carBuyDate',
			title : '购车日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetime',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}

		] ]
	});
	// 首保提醒  //
	$('#customer_care_shoubao_tixing_id').datagrid( {

		url : '',
		pagination : true,
		fit : true,
		pageSize : 10,
		singleSelect : true,
		newrap : false,
		striped : true,
		rownumbers : true,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false, // 自适应列宽
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [{
			field : 'carId',
			title : '车辆id',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'carLicense',
			title : '车牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			field : 'ctypeName',
			title : '车品牌',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {}
			}
		} ] ],
		columns : [ [ {
			field : 'carCstlName',
			title : '车型号',
			width : 100,
			 
			sortable : true,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			field : 'carBuyDate',
			title : '销售日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {}
			}
		}, {
			field : 'yjsbDate',
			title : '预计首保日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {}
			}
		}, {
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			field : 'carRelationTel1',
			title : '车主手机',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {}
			}
		}, {
			field : 'carRelationTel2',
			title : '车主固话',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			field : 'receptionRepPer',
			title : '客联系人',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			field : 'carRelationPerson',
			title : '驾驶姓名',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {}
			}
		},{
			field : 'pareaName',
			title : '联系地址',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {}
			}
		},{
			field : 'receptor',
			title : '最近接待员',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {}
			}
		}, {
			field : 'shoubaotixing',
			title : '首保提醒员',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {}
			}
		}
		] ]
	});
	// 年检年审提醒
	$('#nianjian_nianshen_id').datagrid( {
		url : 'vTrackRecordAction!getNianjianShen.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		newrap : false,
		striped : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false, // 自适应列宽
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'carId',
			title : '车辆id',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'carLicense',
			title : '车辆牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'carCstlName',
			title : '品牌类型',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'carExaminedDate',
			title : '年审到期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datebox',
				options : {
					required : true

				}
			}
		}, {
			field : 'carAnnualDate',
			title : '年检到期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datebox',
				options : {
					required : true
				}
			}
		}, {
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'carRelationTel1',
			title : '车主手机',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true
				}
			}
		}, {
			field : 'carRelationTel2',
			title : '车主固话',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'receptionRepPer',
			title : '客联系人',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'carRelationPerson',
			title : '驾驶姓名',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'propTel',
			title : '托修固话',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'propPhone',
			title : '托修手机',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'pareaName',
			title : '联系地址',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'carLastRepairDate',
			title : '最近维修日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'carLastRepairDistance',
			title : '最近维修里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'carRepairCnt',
			title : '维修次数',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'carDistancePerDay',
			title : '日均里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'wlcDays',
			title : '未来厂天数',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}, {
			field : 'receptor',
			title : '最近接待员',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}

		] ]

	});
	// 保养提醒
	$('#baoyang_rember_id').datagrid({
		url : 'customerCareAction!getBytixing.action',//
		pagination : true,
		fit : true,
		rownumbers : true,
		newrap : false,
		striped : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : false, // 自适应列宽 CarId
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'carId',
			title : '车辆id',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'carLicense',
			title : '车辆牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}, {
			field : 'ctypeName',
			title : '车品牌',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}, {
			field : 'carCstlName',
			title : '车型号',
			width : 100,
			 
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}, {
			field : 'yjbydate',
			title : '预计保养日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {
					required : true
				}
			}
		}, {
			field : 'yjbyDistance',
			title : '预计保养里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationTel1',
			title : '车主手机',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationTel2',
			title : '车主固话',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'receptionRepPer',
			title : '客户联系人',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRelationPerson',
			title : '驾驶姓名',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'propTel',
			title : '托修固话',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'propPhone',
			title : '托修手机',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'pareaName',
			title : '联系地址',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastMaintDate',
			title : '最近保养日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastMaintDistance',
			title : '最近保养里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'byNumber',
			title : '保养次数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastRepairDate',
			title : '最近维修日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carLastRepairDistance',
			title : '最近维修里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carRepairCnt',
			title : '维修次数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'carDistancePerDay',
			title : '日均里程',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'wlcDays',
			title : '未来厂天数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'receptor',
			title : '最近接待员',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'id23',
			title : '首保跟踪员',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'id24',
			title : '短信提醒日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}

		] ]

	});
	
	
	//车辆流失回访   历史维修记录
	$('#datagrid_carlost_repair_id').treegrid({
		url:'',
		fit : true,
	    fitColumns : true,
	    idField:'reception_Id',  
		treeField:'reception_Id',
		columns:[[
		    {field:'reception_Id',title:'施工单号',width:200},
		    {field:'inter_Date',title:'入厂日期',width:200},
			{field:'car_Last_Maint_Distance',title:'行驶里程',width:200},
			{field:'stf_Name',title:'接车员',width:200},
			{field:'exp_Del_Car_Time',title:'出厂日期',width:200},
			{field:'prop_Rep_Per',title:'主修工',width:200},
			{field:'preclr_Sum_Amount',title:'合计金额',width:200},
			{field:'rcpitem_Name',title:'维修项目',width:200}
		]],onBeforeExpand:function(rowData){
		//动态设置展开查询的url
		var url = 'customerCareAction!getFactoryRepairRecordChild.action?reception_Id='+rowData.reception_Id;
		$('#datagrid_carlost_repair_id').treegrid("options").url = url;
		return true;
    }
	});
	
	//车辆流失回访   历史回访记录
	$('#datagrid_carlost_return_id').datagrid({

		url : '',//
		fit : true,
		rownumbers : true,
		newrap : false,
		striped : true,
		singleSelect : true,
		fitColumns : true, // 自适应列宽 CarId  
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'return_Visit_Date',
			title : '回访日期',
			width : 100,
			sortable : true
		},{
			field : 'tx_Return_Visit_Date',
			title : '提醒回访日期',
			width : 100,
			sortable : true
		},{
			field : 'group_Name',
			title : '回访类型',
			width : 100,
			sortable : true
		},{
			field : 'visit_Content',
			title : '回访内容',
			width : 100,
			sortable : true
		},{
			field : 'tx_Resault',
			title : '流失原因',
			width : 100,
			sortable : true
		}]]
	});
});
//双击行时触发此方法
function Dbclick(rowData){
	$('#tt').tabs('select',1);
	$('#CustomerGzManage_form_south_id').form('load', rowData);
	$('#GD_WeiXiu_detail_form_id').form('load', rowData);
	$('#userReturn_infomation_form_id').form('load', rowData);
	$('#GD_WeiXiu_detail_form').form('load', rowData);
	$('#customer_genz2').datagrid({
		url : 'customerGzManageAction_doFindCollect.action',
		queryParams: {preclrId:rowData.preclrId}
	});
	$('#customer_genz3').datagrid({
		url : 'customerGzManageAction_doFindCarDcname.action',
		queryParams: {preclrId:rowData.preclrId}
	});
	
}