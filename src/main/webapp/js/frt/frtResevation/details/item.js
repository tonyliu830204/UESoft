var sPrice;
	function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		var items=null;
		var resvId=document.getElementById("resvId").value;
		var rowData = $('#frtResevationSummaryDatagrid').datagrid('getSelected');
		if(rowData){
			$('#frtResevationItemDatagrid').datagrid({
				url : 'frtResevationAction!findItemByResvId.action?resvId=' +rowData.resvId
			});
		}else  if(staticRpId){
			if(staticFrtResevationItems==null){
				items="";
			}else{
				items=JSON.stringify(staticFrtResevationItems);
			}
			$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'frtResevationAction!findItemListByRpId.action',
				   data: 'rpId='+staticRpId+'&items='+items,
				   success: function(r){
						$('#frtResevationItemDatagrid').datagrid('loadData', r);
						var data = $('#frtResevationItemDatagrid').datagrid('getData');
						staticFrtResevationItems=data;
				   }
				}).error(function (r){
					alertMsg(r);
				});
		}else{
			$('#frtResevationItemDatagrid').datagrid({
				url : 'frtResevationAction!findItemByResvId.action?resvId=-1'
			});
		}
		if($('#save').length != 0 && $('#cancel').length != 0){
			$('#frtResevationItemDatagrid').datagrid({
				onLoadSuccess : function (data){
					staticFrtResevationItems=data;
					$('#frtResevation_item_add').linkbutton('enable');
					$('#frtResevation_item_remove').linkbutton('enable');
					$('#frtResevation_item_accept').linkbutton('enable');
					 $('#frtResevation_item_diy').linkbutton('enable');
				}
			});
		}
	}
	setTimeout("LoadOk();", 200);

	$(function (){
		//预约申请单->计划项目
		var url='';
		var rowData = $('#frtResevationSummaryDatagrid').datagrid('getSelected');
		if(rowData){
			url='frtResevationAction!findItemByResvId.action?resvId=' +rowData.resvId;
		}
		$frtResevationItemDatagrid = $('#frtResevationItemDatagrid');
		$frtResevationItemDatagrid.datagrid({
			url : url,
			singleSelect : true,
			rownumbers : true,
			fitColumns : true,
			showFooter : true,
			fit : true,
			loadMsg : "数据加载中，请稍后……",
			columns : [[{
					field : 'repitemId',
					title : '项目编号',
					width : 60
				}, {
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
						    	var row = $frtResevationItemDatagrid.datagrid('getSelected');
					    		var index = $frtResevationItemDatagrid.datagrid('getRowIndex', row);
					    		var ed = $frtResevationItemDatagrid.datagrid('getEditor', {index:index,field:'stfName'});
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
				}]],
			toolbar : [{
					id : 'frtResevation_item_add',
					text : '项目新增',
					iconCls : 'icon-add',
					disabled : true,
					handler : function() {
				    var frtResevation_details_carLicense=$('#frtResevation_details_carLicense').combobox('getValue');
					if(frtResevation_details_carLicense!=null && frtResevation_details_carLicense!=""){
						for(var i=0;i<frtResevation_details_carLicense.length;i++){
							if(!(frtResevation_details_carLicense.charAt(i)>='0'&&frtResevation_details_carLicense.charAt(i)<='9')){
								alertMsg('请先选择车牌照！', 'warning');
								return ;
							}
						}
						$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url:'baseAction!loadTaskPrice.action?lince='+frtResevation_details_carLicense,  
							   success: function(r){
								sPrice=r;
							   },
							   error : function (r){
								   if(r.readyState == '0' && r.status == '0'){
									   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
								   }
							   }
							});
						var d = $('<div/>').dialog({
							modal:true,
							title : '维修项目选择',
							closable : true,
							href : projectPath+'frt/frtResevation/details/addItem.jsp',
							width : 900,
							height : 560,
							buttons : [{
								text : '确定',
								iconCls : 'icon-add',
								handler : function (){
									if(validateDatagrid('selectedItem')){
										staticFrtResevationItems=prosceniumAdd('selectedItem','frtResevationItemDatagrid',staticFrtResevationItems);
										d.dialog('close');
									}else{
										alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
									}
								}
							}],
							onClose : function (){
						    	$(this).dialog('destroy');
						    }
						});
					  }else{
						  alertMsg('请先选择车牌照！', 'warning');
						  }
						
					}
				},{
				   id : 'frtResevation_item_remove',
				   text : '项目删除',
				   iconCls : 'icon-remove',
				   disabled : true,
				   handler : function (){	
					  var row = $('#frtResevationItemDatagrid').datagrid('getSelected');
						if(row){
							staticFrtResevationItems = prosceniumDelete('frtResevationItemDatagrid',row,staticFrtResevationItems);
						}
			   	   }
			   },{
				   id : 'frtResevation_item_diy',
				   text : '自定义项目',
				   iconCls : 'icon-edit',
				   disabled : true,
				   handler : function (){
				   		var items=null;
						if(staticFrtResevationItems==null){
							items="";
						}else{
							items=JSON.stringify(staticFrtResevationItems);
						}
				   		$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'frtResevationAction!addFrtResevationItem.action',
						   data:'items='+items,
						   success: function callback(r){
								$('#frtResevationItemDatagrid').datagrid('loadData', r);
								var data = $('#frtResevationItemDatagrid').datagrid('getData');
								staticFrtResevationItems=data;
						   }
						});
			   	   }
			   }],
			onClickRow : function (rowIndex, rowData){
				if($('#save').length != 0 && $('#cancel').length != 0){
					//alert(sPrice);
					copyDateAndBindEventAndThree($frtResevationItemDatagrid, rowIndex, rowData);
				}
			},
			 onLoadSuccess : function (data){
				staticFrtResevationItems=data;
		   }
		});
	});