$(function (){
	loadComboTree();
});

var loadComboTree = function(){
	$menuInformation=$('#menuInformation');
	$menuInformation.treegrid({
		url : projectPath+'menuAction!treegrid.action',
		fit : true,
		fitColumns : true,  
		idField : 'menuId',
		treeField : 'menuName',
		columns : [[{
			field : 'menuName',
			title : '菜单名称',
			width : 60,
			formatter : function (value,row,index){
				return row.menuName;
			}
		},{
			field : 'menuId',
			title : '编号',
			width : 60,
			hidden : true
		},{
			field : 'menuPid',
			title : '父编号',
			width : 60,
			hidden : true
		},{
			field : 'person',
			title : '创建人',
			width : 60,
			hidden : true
		},{
			field : 'systemMenu',
			title : '所属系统',
			width : 60,
			hidden : true
		},{
			field : 'childMenu',
			title : '是否有子菜单',
			width : 60,
			hidden : true
		},{
			field : 'menuCode',
			title : '菜单CODE值',
			sortable : true,
			width : 30
		}, {
			field : 'menuPname',
			title : '父级菜单',
			sortable:true,
			width : 20
		}, {
			field : 'url',
			title : '菜单URL',
			sortable:true,
			width : 20
		},{
			field : 'caeateTime',
			title : '创建日期',
			sortable:true,
			width : 20
		},{
			field : 'personName',
			title : '创建人名称',
			sortable:true,
			width : 30
		},{
			field : 'remark',
			title : '备注',
			sortable:true,
			width : 30
		}]],
		onBeforeLoad: function (param) {
		    jsProbar();
		},
		onLoadSuccess:function(data){
			 jsCloseProbar();
		},
		onLoadError: function () {
			 jsCloseProbar();
		},
		onClickRow : function (row){
			
		},
		onDblClickRow : function (row){
			showDialog(row);
		},
		onBeforeExpand:function(rowData){
			var url = projectPath+'menuAction!getTreegridChild.action?menuId=' + rowData.menuId;
			$menuInformation.treegrid("options").url = url;
			return true;
	    }
	});
}


//添加 
var addPMenu = function(){
	showDialog(null);
}

//修改
var updatePMenu = function(){
	var data = $('#menuInformation').datagrid('getSelected');
	showDialog(data);
}

//删除
var deletePMenu = function(){
	var data = $('#menuInformation').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
			if(b){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: projectPath+'menuAction!delete.action',
				   data: data,
				   success: function(r){
					   if(r.success){
						   loadComboTree();
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

var showDialog = function(rowDate){
	var d = $('<div/>').dialog({
		href : projectPath+"common/base/menu/addMenuinfo.jsp",
		modal:true,
		closable : false,
		title : '新增菜单',
		width : 350,
		height : 290,
		buttons : [{
				text : '确定',
				handler : function (){
				    if($('#MenuAddForm').form('validate')){
				    	var node = $('#pmenuPid1').combotree('getValue');
				    	$('#pmenuPid').val(node);
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: projectPath+'menuAction!savaOrUpdata.action',
						   data: $('#MenuAddForm').serialize(),
						   success: function(r){
							   if(r.success){
								    d.dialog('close');
								    loadComboTree();
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
				text : '取消',
				handler : function (){
					d.dialog('close');
				}
			}
		],
	    onLoad : function (){
				$.ajax({
					type:'POST',
					dataType: 'json',
					url:projectPath+'login_getSessionUser.action',
					success : function(r){
					if(r!=null){
						var arrayStr=r.split(","); 
						if(loginLevel==arrayStr[0]){
							$("div[id$='expand']").css("display", "block");
							$('#systemMenu').combobox({   
      		    				url:projectPath+'frtOptionsAction!findBaseListData.action?key='+systemType,   
      		    			    valueField:'id',   
      		    			    textField:'text',
      		    			    required:true,
      		    			    missingMessage:'所属系统为必选项'
      		    			});
							if(rowDate != null)
							    $('#systemMenu').combobox("setValue",rowDate.systemMenu);
						}
					}else{
						alertMsg("失败",'info');
					}
				}
				});
		       if(rowDate != null){
		    	   $('#pmenuId').val(rowDate.menuId);
		    	   $('#pcaeateTime').val(rowDate.caeateTime);
		    	   $('#pmenuName').val(rowDate.menuName);
		    	   $('#pmenuCode').val(rowDate.menuCode);
		    	   $('#pmenuPid1').combotree('setValue', rowDate.menuPid);
		    	   $('#purl').val(rowDate.url);
		    	   $('#premark').val(rowDate.remark);
		    	   $('#person').val(rowDate.person);
		    	   $('#systemMenu').combobox('setValue', rowDate.systemMenu);
		    	   $('#childMenu').val(rowDate.childMenu);
		       }
        },
		onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
}    
/**
 * 导出
 * */
function _except(){
	showEditDialog("menuInformation",null,"mor_tabs","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"菜单管理"+getSystemTime());
}