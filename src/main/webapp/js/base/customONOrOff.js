$(function (){			
	$('#customOnOrOffDatagrid').datagrid({
		url : 'frtCustomAction!datagrid_frt_custom.action',
		singleSelect : true,
		fit : true,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		frozenColumns:[[
			{
				field : 'customId',
				title : '客户编号',
				width : 60,
				sortable : true
			}, {
				field : 'customName',
				title : '客户名称',
				width : 60,
				sortable : true
			}
		]],
		columns : [[{
			field : 'customJm',
			title : '客户简码',
			width : 60,
			sortable : true
		},{
			field : 'createDate',
			title : '建档日期',
			width : 80,
			sortable : true
		},{
			field : 'natureId',
			title : '客户性质',
			width : 60,
			sortable : true,
			formatter: function(value,row,index){
				return row.natureName;
			}
		},{
			field : 'natureName',
			title : '客户性质',
			width : 60,
			hidden : true
		},{
			field : 'cstgId',
			title : '客户分类',
			width : 80,
			sortable : true,
			formatter: function(value,row,index){
				return row.cstgName;
			}
		},{
			field : 'cstgName',
			title : '客户分类',
			width : 80,
			hidden : true
		},{
			field : 'cstId',
			title : '客户类型',
			width : 80,
			sortable : true,
			formatter: function(value,row,index){
				return row.cstName;
			}
		},{
			field : 'cstName',
			title : '客户类型',
			width : 80,
			hidden : true
		},{
			field : 'pareaId',
			title : '所在区域',
			width : 80,
			sortable : true,
			formatter: function(value,row,index){
				return row.pareaName;
			}
		},{
			field : 'pareaName',
			title : '所在区域',
			width : 80,
			hidden : true
		},{
			field : 'customAddr',
			title : '地址',
			width : 120,
			sortable : true
		},{
			field : 'customTel1',
			title : '联系电话',
			width : 80,
			sortable : true
		},{
			field : 'customTel2',
			title : '固定电话',
			width : 80,
			sortable : true
		},{
			field : 'customEmail',
			title : '电子邮箱',
			width : 100,
			sortable : true
		},{
			field : 'customFlag',
			title : '客户状态',
			width : 100,
			formatter: function(value,row,index){
				if(value==flag){
					return "启用";
				}else{
					return "禁用";
				}
			}
		},{
			field : 'bankOfDeposit',
			title : '开户银行',
			width : 100,
			hidden : true
		},{
			field : 'bankAccount',
			title : '银行账号',
			width : 100,
			hidden : true
		},{
			field : 'taxId',
			title : '税号',
			width : 100,
			hidden : true
		},{
			field : 'invoicingAddress',
			title : '开票地址',
			width : 150,
			hidden : true
		},{
			field : 'invoicingTel',
			title : '开票电话',
			width : 100,
			hidden : true
		},{
			field : 'fax',
			title : '传真',
			width : 100,
			hidden : true
		},{
			field : 'linkman',
			title : '公司联系人',
			width : 100,
			hidden : true
		},{
			field : 'linkmanTel',
			title : '公司联系人电话',
			width : 100,
			hidden : true
		},{
					field : 'customRemark1',
					title : '备注一',
					width : 150,
					hidden : true
				}, {
					field : 'customRemark2',
					title : '备注二',
					width : 150,
					hidden : true
				}]],
		  		onDblClickRow:function(rowIndex,rowData){
		 			showInfo(rowData);
		 		}
			});
		});
		var showInfo=function(rowData){
			var d = $('<div/>').dialog({
				modal:true,
				closable : true,
				title : '客户档案查看',
				width : 800,
				height : 400,
				href : projectPath+'base/customArchives/addCustomArchives.jsp',
				buttons : [{
		        	iconCls : 'icon-undo',
					text : '关闭',
					handler : function (){
		        		d.dialog('close');
					}
		        }],
				onClose : function (){
			    	d.dialog('destroy');
			    },
			    onLoad : function (){
					$('#customArchivesAddForm').form('load', rowData);
			    }
			});
		}
		var query = function (){
			if($('#customOnOrOffQueryForm').form('validate')){
				$('#customOnOrOffDatagrid').datagrid('load', serializeObject($('#customOnOrOffQueryForm')));
			}else{
				alertMsg('对不起,请确认内容及格式是否正确！', 'warning');
			 }	
		}
		
		var _clear = function (){
			$('#customOnOrOffQueryForm').form('clear');
			$('#customOnOrOffDatagrid').datagrid('load', serializeObject($('#customOnOrOffQueryForm')));
		}
		var useStateAsTrue=function(){
			var rowData = $('#customOnOrOffDatagrid').datagrid('getSelected');
			if(rowData){
				if(rowData.customFlag==flag){
					alertMsg('对不起，此客户已启用，无需再次启用！', 'warning');
					return;	
				}
				modifyPartsFlag(rowData);
			}else{
				alertMsg('对不起，请先选中要操作的记录！', 'warning');
			}
		}
		var useStateAsFalse=function(){
			var rowData = $('#customOnOrOffDatagrid').datagrid('getSelected');
			if(rowData){
				if(rowData.customFlag==flag1){
					alertMsg('对不起，此客户已禁用，无需再次禁用！', 'warning');
					return;	
				}
				modifyPartsFlag(rowData);
			}else{
				alertMsg('对不起，请先选中要操作的记录！', 'warning');
			}
		}
		var modifyPartsFlag=function(rowData){
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : 'frtCustomAction!modifyPartsFlag.action',
				data:rowData,
				success : function(r) {
					if (r.success) {
						alertMsg(r);
						$('#customOnOrOffDatagrid').datagrid('load');
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
		//导出
		function _except(){
			showEditDialog("customOnOrOffDatagrid",null,"customOnOrOffDatagrid_center","开始导出","导出配置",0,_callbackExcept);
		}
		function _callbackExcept(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"客户档案设置"+getSystemTime());
		}