var workshopPrice=null;
var isUpdataRepairItemState=undefined;
$(function(){
	//前台接车单->计划项目
	if(receptionId){
		url='frtWorkshopManagerAction!findItemByRcptId.action?receptionId=' +receptionId;
	}else{
		url='frtWorkshopManagerAction!findItemByRcptId.action?receptionId=-1';
	}
	$frtWorkshopManagerItemDatagrid = $('#frtWorkshopManagerItemDatagrid');
	$frtWorkshopManagerItemDatagrid.datagrid({
		url : url,
		fit : true,
		singleSelect : true,
		rownumbers : true,
		fitColumns: true,
		columns : [[{
			field : 'repitemId',
			title : '项目编号',
			width : 60
		},{
			field : 'repitemName',
			title : '项目名称',
			width : 60,
			editor : {
				type : 'text'
			}
		},{
			field : 'repitemTime',
			title : '维修工时',
			width : 60,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1,
					missingMessage : "维修工时为必填项!"
				}
			}
		},{
			field : 'internalTime',
			title : '内部工时',
			width : 60,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					missingMessage : "内部工时为必填项!"
				}
			}
		},{
			field : 'repitemAmount',
			title : '工时费',
			width : 60,
			editor : {
				type : 'numberbox',
				options : {
					disabled:false,
					required : true,
					precision : 2
				}
			}
		},{
			field : 'repitemNum',
			title : '标识列',
			width : 60,
			hidden : true,
			editor : {
				type : 'text'
			}
		},{
			field : 'stfId',
			title : '维修人员',
			width : 60,
			editor : {
				type : 'combobox',
				options : {
					url : projectPath+'basStuffClassAction!findEnterpriseMaintainPerson.action',
					valueField:'id',   
				    textField:'text',
				     mode:'remote',
				    required : true,
				    missingMessage : '维修人员为必填项！',
				    onSelect : function (record){
				    	var row = $frtWorkshopManagerItemDatagrid.datagrid('getSelected');
			    		var index = $frtWorkshopManagerItemDatagrid.datagrid('getRowIndex', row);
			    		var ed = $frtWorkshopManagerItemDatagrid.datagrid('getEditor', {index:index,field:'stfName'});
			    		ed.target.val(record.text);
				    }
				}
			},
			formatter : function (value,row,index){
				return row.stfName;
			}
		},{
			field : 'stfName',
			title : '维修人员',
			width : 60,
			hidden : true,
			editor : {
				type : 'text'
			}
		},{
			field : 'planStartTime',
			title : '计划开工时间',
			width : 60,
			editor : {
				type : 'datetimebox'
			}
		},{
			field : 'planComplTime',
			title : '计划完工时间',
			width : 60,
			editor : {
				type : 'datetimebox'
			}
		},{
			field : 'reptypId',
			title : '收费性质',
			width : 60,
			editor : {
				type : 'combobox',
				options : {
					url : 'frtOptionsAction!findAllRepairType.action',
					valueField:'id',   
				    textField:'text',
				     mode:'remote',
				    required : true,
				    missingMessage : '收费性质为必填项！',
				    onSelect : function (record){
				    	var row = $frtWorkshopManagerItemDatagrid.datagrid('getSelected');
			    		var index = $frtWorkshopManagerItemDatagrid.datagrid('getRowIndex', row);
			    		var ed = $frtWorkshopManagerItemDatagrid.datagrid('getEditor', {index:index,field:'reptypName'});
			    		ed.target.val(record.text);
				    }
				}
			},
			formatter : function (value, row, index){
				return row.reptypName;
			}
		},{
			field : 'reptypName',
			title : '收费性质',
			width : 60,
			hidden : true,
			editor : {
				type : 'text'
			}
		},{
			field : 'claimsId',
			title : '索赔分类',
			width : 60,
			editor : {
				type : 'combobox',
					options : {
						url : 'frtOptionsAction!findAllClaimsType.action',
						valueField:'id',   
					    textField:'text',
					     mode:'remote',
					    required : true,
					    missingMessage : '索赔分类为必填项！',
					    onSelect : function (record){
					    	var row = $frtWorkshopManagerItemDatagrid.datagrid('getSelected');
				    		var index = $frtWorkshopManagerItemDatagrid.datagrid('getRowIndex', row);
				    		var ed = $frtWorkshopManagerItemDatagrid.datagrid('getEditor', {index:index,field:'claimsName'});
				    		ed.target.val(record.text);
					    }
					}
			},
			formatter : function (value, row, index){
				return row.claimsName;
			}
		},{
			field : 'claimsName',
			title : '索赔分类',
			width : 60,
			hidden : true,
			editor : {
				type : 'text'
			}
		},{
			field : 'repitemRemark',
			title : '备注',
			width : 60,
			editor : {
				type : 'text'
			}
		}]],
		toolbar : [{
			id : 'frtWorkshopManager_item_add',
			text : '项目新增',
			iconCls : 'icon-add',
			disabled : true,
			handler : function() {
			$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url:'baseAction!loadTaskPrice.action',  
				   success: function(r){
				      workshopPrice=r;
				   },
				   error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
				});
				var d = $('<div/>').dialog({
					modal : true,
					title : '选择维修项目',
					closable : true,
					href : projectPath+'frt/frtWorkshopManager/details/addItem.jsp',
					width : 900,
					height : 560,
					buttons : [{
						text : '确定',
						iconCls : 'icon-add',
						handler : function() {
							if(validateDatagrid('selectedItem')){
								staticFrtWorkshopManagerItems=prosceniumAdd('selectedItem','frtWorkshopManagerItemDatagrid',staticFrtWorkshopManagerItems);
								d.dialog('close');
							}else{
								alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
							}
						}
					}],
					onClose : function() {
						$(this).dialog('destroy');
					}
				});
			  }
			},{
			   id : 'frtWorkshopManager_item_remove',
			   text : '项目删除',
			   iconCls : 'icon-remove',
			   disabled : true,
			   handler : function (){
				  	var row = $('#frtWorkshopManagerItemDatagrid').datagrid('getSelected');
					if(row){
						staticFrtWorkshopManagerItems = prosceniumDelete('frtWorkshopManagerItemDatagrid',row,staticFrtWorkshopManagerItems);
					}
		   	   }
		   },{
			   id : 'frtWorkshopManager_item_diy',
			   text : '自定义项目',
			   iconCls : 'icon-edit',
			   disabled : true,
			   handler : function (){
			   		var items=null;
					if(staticFrtWorkshopManagerItems==null){
						items="";
					}else{
						items=JSON.stringify(staticFrtWorkshopManagerItems);
					}
			   		$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'frtWorkshopManagerAction!addFrtWorkshopManagerItem.action',
					   data:'items='+items,
					   success: function callback(r){
							$('#frtWorkshopManagerItemDatagrid').datagrid('loadData', r);
							var data = $('#frtWorkshopManagerItemDatagrid').datagrid('getData');
							staticFrtWorkshopManagerItems=data;
					   }
					});
		   	   }
		   }],
		onClickRow : function (rowIndex, rowData){
			if($('#save').length != 0 && $('#cancel').length != 0){
				if(isUpdataRepairItemState=='checked'){
				   copyDateAndBindEventAndThree($frtWorkshopManagerItemDatagrid, rowIndex, rowData);		
				}			
			}
		},
		onLoadSuccess : function (data){
			 if(opretion==true){
				    staticFrtWorkshopManagerItems=data;
					$('#frtWorkshopManager_item_add').linkbutton('enable');
					$('#frtWorkshopManager_item_remove').linkbutton('enable');
					 $('#frtWorkshopManager_item_diy').linkbutton('enable');
			   }
		}		
	});
})
    function loadIsUpdataRepairItemInfo(){
    	 $.ajax({   
				type : "POST",
				url : "baseAction!loadIsUpdataRepairItemInfo.action",
				data : '',
				dataType : "json",
				success : function callback(r) {
			          if(r.ciValue!=null){
			        	  isUpdataRepairItemState=r.ciValue;
				      }
            }
	      });
    }