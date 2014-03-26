$(function (){
	$partsOnOrOffDatagrid = $('#partsOnOrOffDatagrid');			
	$partsOnOrOffDatagrid.datagrid({
		singleSelect : true,
		url : 'basPartsArchivesAction!datagridPartsArchives.action?flag=true',
		fit : true,
		fitColumns : true,
		pagination : true,
		rownumbers : true,
			columns : [[{
				field : 'partsId',
				title : '配件编码',
				width : 80,
				sortable : true
			}, {
				field : 'partsId2',
				title : '编码二',
				width : 70,
				sortable : true
			}, {
				field : 'partsName',
				title : '配件名称',
				width : 80,
				sortable : true
			}, {
				field : 'partsSimpleId',
				title : '简码',
				width : 50,
				sortable : true
			}, {
				field : 'pbrdId',
				title : '配件品牌',
				width : 70,
				sortable : true,
				formatter: function(value,row,index){
					return row.pbrdName;
				}
			}, {
				field : 'pbrdName',
				title : '配件品牌',
				width : 70,
				hidden : true
			}, {
				field : 'ptypeId',
				title : '配件型号',
				width : 70,
				sortable : true,
				formatter: function(value,row,index){
					return row.ptypeName;
				}
			}, {
				field : 'fitPtype',
				title : '适合车型',
				width : 150,
				sortable : true,
				formatter: function(value,row,index){
					return row.fitPtypeName;
				}
			}, {
				field : 'posName',
				title : '配件部位',
				width : 45,
				sortable : true,
				formatter: function(value,row,index){
					return row.posName2;
				}
			}, {
				field : 'posName2',
				title : '配件部位',
				width : 45,
				hidden : true
			}, {
				field : 'punitName',
				title : '单位',
				width : 30,
				sortable : true,
				formatter: function(value,row,index){
					return row.punitName2;
				}
			}, {
				field : 'punitName2',
				title : '单位',
				width : 30,
				hidden : true
			}, {
				field : 'stateName',
				title : '产地',
				width : 30,
				sortable : true,
				formatter: function(value,row,index){
					return row.stateName2;
				}
			}, {
				field : 'stateName2',
				title : '产地',
				width : 30,
				hidden : true
			}, {
				field : 'partsRemark',
				title : '备注',
				width : 70,
				sortable : true
			},{
				field : 'partsNeedPoint',
				title : '现有库存量',
				width : 80,
				hidden : true
			},{
				field : 'partsFlag',
				title : '配件使用状态',
				width : 80,
				formatter: function(value,row,index){
					if(value==flag){
						return "启用";
					}else{
						return "禁用";
					}
				}
			},{
				field : 'changePriceId',
				title : '调价编号',
				width : 80,
				hidden : true
			},{
				field : 'storeId',
				title : '仓别编号',
				width : 80,
				hidden : true
			},{
				field : 'partsRepairPrice',
				title : '维修价',
				width : 80,
				hidden : true
			},{
				field : 'partsSellPrice',
				title : '销售价',
				width : 80,
				hidden : true
			},{
				field : 'partsPointPrice',
				title : '网点价',
				width : 80,
				hidden : true
			},{
				field : 'partsSpecialPrice',
				title : '特别价',
				width : 80,
				hidden : true
			},{
				field : 'partsClaimantPrice',
				title : '索赔价',
				width : 80,
				hidden : true
			},{
				field : 'partsLatestTaxprice',
				title : '入库含税价',
				width : 80,
				hidden : true
			},{
				field : 'partsLatestNotaxprice',
				title : '入库未税价',
				width : 80,
				hidden : true
			},{
				field : 'partsNowCount',
				title : '库存量',
				width : 80,
				hidden : true
			},{
				field : 'stockUpper',
				title : '库存上限',
				width : 80,
				hidden : true
			},{
				field : 'stockLower',
				title : '库存下限',
				width : 80,
				hidden : true
			}]],
 		onDblClickRow:function(rowIndex,rowData){
 			//showInfo(rowData);
 		}
	});
});
var showInfo=function(rowData){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '配件档案查看',
		width : 760,
		height : 310,
		href : projectPath+'base/partsArchives/addPartsArchives.jsp?flag=false',
		buttons : [{
        	iconCls : 'icon-undo',
			text : '关闭',
			handler : function (){
        		d.dialog('close');
			}
        }],
        onClose : function (){
	    	$(this).dialog('destroy');
	    },
	    onOpen : function (){
	    	$('#partsArchivesAddForm').form('load', rowData);
	    	if(rowData.fitPtype!= null){
			  setComboboxValues('partsArchivesEditForm_fitPtype',rowData.fitPtype);
			}	
	    }
	});
}
var query = function (){
	if($('#partsArchivesQueryForm').form('validate')){
		$partsOnOrOffDatagrid.datagrid('load', serializeObject($('#partsArchivesQueryForm')));
	}else{
		alertMsg('对不起,请确认内容及格式是否正确！', 'warning');
	}	
}

var _clear = function (){
	$('#partsArchivesQueryForm').form('clear');
	$('#partsArchives_pbrdId').combobox('reload');
	$('#partsArchives_ptypeId').combobox('reload', 'basPartsArchivesAction!findPartsType.action');
	$('#partsArchives_posName').combobox('reload');
	$('#partsArchives_stateName').combobox('reload');
	$partsOnOrOffDatagrid.datagrid('load', serializeObject($('#partsArchivesQueryForm')));
}
var useStateAsTrue=function(){
	var rowData = $('#partsOnOrOffDatagrid').datagrid('getSelected');
	if(rowData){
		if(rowData.partsFlag==flag){
			alertMsg('对不起，此配件已启用，无需再次启用！', 'warning');
			return;	
		}
		modifyPartsFlag(rowData);
	}else{
		alertMsg('对不起，请先选中要操作的记录！', 'warning');
	}
}
var useStateAsFalse=function(){
	var rowData = $('#partsOnOrOffDatagrid').datagrid('getSelected');
	if(rowData){
		if(rowData.partsFlag==flag1){
			alertMsg('对不起，此配件已禁用，无需再次禁用！', 'warning');
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
		url : 'basPartsArchivesAction!modifyPartsFlag.action',
		data:rowData,
		success : function(r) {
			if (r.success) {
				alertMsg(r);
				$('#partsOnOrOffDatagrid').datagrid('load');
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
	showEditDialog("partsOnOrOffDatagrid",null,"partsOnOrOffDatagrid_center","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"配件使用设置"+getSystemTime());
}