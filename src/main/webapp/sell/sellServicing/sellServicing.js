$(function(){

	$('#servicingNextdate2').val(getSystemTime());
	getStartDate($('#servicingNextdate'));
			//车辆维护记录
			$('#servicingData').datagrid({
			url:'${pageContext.request.contextPath}/sellServicingAction!getPager.action',
			fit:true,
			pagination : true,
			fitColumns : true,
			sortOrder:'asc',
		    sortName:'detailsId',
			singleSelect : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			 columns : [ [{
					title : '入库明细编号',
					field : 'detailsId',
					hidden:true
				},{
					title : '车辆档案id',
					field : 'xsCarId',
					hidden:true,
				}, {
					title : 'VIN编号',
					field : 'xsCarVinNumber',
					width : 50,
					sortable : true
				}, {
					title : '车辆型号id',
					field : 'xsCarModelId',
					width : 50,
					hidden:true
				}, {
					title : '车辆型号',
					field : 'xsModelName',
					width : 50
				},{
					title : '预计下次维护日期',
					field : 'servicingNextdate',
					width : 50
				}, {
					title : '销售状态编号',
					field : 'xsCarSellState',
					width : 50,
					hidden:true,
					sortable : true
				}, {
					title : '销售状态',
					field : 'carSellStateName',
					width : 50,
					sortable : true
				},{
					title : '车辆编号',
					field : 'xsCarCode',
					width : 50
				}
		        ]],
	        onDblClickRow:function(rowIndex, rowData){  
				loadProDel(rowData.detailsId);
		}
	});
			//维护项目
			$('#servicingProject').datagrid({
			fit:true,
			pagination : true,
			fitColumns : true,
			sortOrder:'asc',
		    sortName:'servicingId',
			singleSelect : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			 columns : [ [{
					title : '维护序号',
					field : 'servicingId',
					hidden:true
				},{
					title : 'VIN编号',
					field : 'xsCarVinNumber',
					width : 50,
					sortable : true
				}, {
					title : '车辆型号编号',
					field : 'xsCarModelId',
					hidden:true
				}, {
					title : '车辆型号',
					field : 'xsModelName',
					width : 50,
					sortable : true
				},{
					title : '维护日期',
					field : 'servicingDate',
					width : 50,
					sortable : true
				}, {
					title : '维护项目',
					field : 'servicingProject',
					hidden:true
				}, {
					title : '维护内容',
					field : 'servicingProjectName',
					width : 50,
					sortable : true
				}, {
					title : '维护结果',
					field : 'servicingRemark',
					width : 50,
					sortable : true
				},{
					title : '预计下次维护日期',
					field : 'servicingNextdate',
					width : 50
				}
		        ]]
	});		
	
});

function    loadProDel(parentId){
	$('#servicingProject').datagrid({
					url : 'sellServicingAction!getPagerPro.action',
					queryParams: {detailsId:parentId},
					pagination : true
				});
}
function addServicing(){
	var data = $('#servicingData').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '新增',
			width : 500,
			height : 270,
			href : projectPath+'sell/sellServicing/sellServicingEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#servicingEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url:'${pageContext.request.contextPath}/sellServicingAction!saveSellServicing.action',
						   data: $('#servicingEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#servicingData').datagrid('load');
								 loadProDel($('#detailsId').val());
								 d.dialog('close');
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
	        },{
	        	iconCls : 'icon-undo',
				text : '取消',
				handler : function (){
	        		d.dialog('close');
				}
	        }],
		        onClose : function (){
			    	$(this).dialog('destroy');
			    },
			    onLoad : function (){
			    	var data = $('#servicingData').datagrid('getSelected');
			    	$('#servicingEditForm').form('load', data);
			    	$('#servicingDate').val(getSystemTime());
		    		//$('#servicingDate').val(data.servicingNextdate);  
		    		Date.prototype.Format = function(fmt)   
			    	    { //author: meizz   
			    	      var o = {   
			    	        "M+" : this.getMonth()+1,                 //月份   
			    	        "d+" : this.getDate(),                    //日   
			    	        "h+" : this.getHours(),                   //小时   
			    	        "m+" : this.getMinutes(),                 //分   
			    	        "s+" : this.getSeconds(),                 //秒   
			    	        "q+" : Math.floor((this.getMonth()+3)/3), //季度   
			    	        "S"  : this.getMilliseconds()             //毫秒   
			    	      };   
			    	      if(/(y+)/.test(fmt))   
			    	        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
			    	      for(var k in o)   
			    	        if(new RegExp("("+ k +")").test(fmt))   
			    	      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
			    	      return fmt;   
			    	    }  
		    		
		    		var today=new Date($('#servicingDate').val());
		    		$('#nextDate').val(new Date(today.setDate(today.getDate()+15)).format("yyyy-MM-dd"));  
			    }
			});
	}else{
		$.messager.alert('优亿软件提示','对不起，请先选中要操作的记录！','warning',function(){});
	}
		
	
}
function queryServicing(){
	addInitDate($('#servicingNextdate'),$('#servicingNextdate2'));
	$('#servicingData').datagrid('unselectAll');
	$('#servicingData').datagrid('load', serializeObject($('#form_should_gathering_manage_id')));
}
function clearSearchCondition(){
	$('#form_should_gathering_manage_id').form('clear');
	$('#servicingData').datagrid('load', serializeObject($('#form_should_gathering_manage_id')));
	addInitDate($('#servicingNextdate'),$('#servicingNextdate2'));
	
}
function _except(){
	var data =  $("#servicingData").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
		showEditDialog("servicingData",null,"servicingData_div","开始导出","导出配置",0,_callbackExcept);
	
	
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"车辆维护管理"+getSystemTime());
}
/*function _callbackExceptTwo(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"车辆维护项目"+getSystemTime());
}*/
function _config(){
	
	var data =  $("#servicingData").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }

		showEditDialog("servicingData",personnelSumTable,"servicingData_div","开始打印","打印配置",2,_print);
		

		
	
}

function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}
