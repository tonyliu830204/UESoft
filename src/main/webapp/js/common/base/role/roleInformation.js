$(function (){
	loadRoleDataGrid();
	selecttab();
});

var selecttab = function (){
	$('#tt').tabs({   
		onSelect:function(title,index){
			if(index == 0){
				$("#addRoleBtn").unbind().click(addRole);
				$("#deleteRoleBtn").unbind().click(deleteRole);
				$("#updateRoleBtn").unbind().click(updateRole);
				$("#searchRoleBtn").unbind().click(searchRole);
				$("#setDefaultRoleBtn").unbind().click(setDefaultRole);
				$("#qcRoleBtn").unbind().click(clearRole);
				$("#dcRoleBtn").unbind().click(excelRole);
				$('#addRoleBtn').linkbutton('enable');
				$('#deleteRoleBtn').linkbutton('enable');
				$('#updateRoleBtn').linkbutton('enable');
				$('#searchUserRoleBtn').linkbutton('disable');
				$('#setDefaultRoleBtn').linkbutton('enable');
			} else {
				loadRoleUserDataGrid();
				$("#searchRoleBtn").unbind().click(searchRoleUser);
				$("#qcRoleBtn").unbind().click(clearRoleUser);
				$("#dcRoleBtn").unbind().click(excelRoleUser);
				$("#searchUserRoleBtn").unbind().click(searchUserRoleInfo);
				$('#addRoleBtn').linkbutton('disable');
				$('#deleteRoleBtn').linkbutton('disable');
				$('#updateRoleBtn').linkbutton('disable');
				$('#searchUserRoleBtn').linkbutton('enable');
				$('#setDefaultRoleBtn').linkbutton('disable');
			}
	    }   
	});
	$('#tt').tabs('select',0);
}

var loadRoleUserDataGrid = function(){
	$personnelInformationSet=$('#personnelInformationSet');
	$personnelInformationSet.datagrid({
		url : projectPath+'basPersonnelInformationSetAction!findUserRole.action',
		singleSelect : true,
		fit : true,
		pagination : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		rownumbers : true,
		sortOrder:'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		loadMsg : "数据加载中，请稍候……",
		columns : [[
           {
				field : 'stfId',
				title : '编号',
				width : 1,
				hidden : true
			},{
				field : 'stfYid',
				title : '编码',
				sortable : true,
				width : 30
		   },{
				field : 'stfName',
				title : '姓名',
				sortable : true,
				width : 30
		   }, {
				field : 'stfMark',
				title : '助记码',
				sortable:true,
				width : 20
		   }, {
				field : 'stfSexvalue',
				title : '性别',
				sortable:true,
				width : 20
		   },{
				field : 'stfYesvalue',
				title : '系统用户',
				sortable:true,
				width : 20
		   },{
				field : 'loginName',
				title : '登陆帐号',
				sortable:true,
				width : 20
		    },{
				field : 'roleName',
				title : '角色',
				width : 100
		    },{
				field : 'leavl',
				title : '级别',
				hidden : true
		    },{
				field : 'systemId',
				title : '系统key',
				hidden : true
		    }
         ]]		
	});
}

var loadRoleDataGrid = function(){
	$roleInformation=$('#roleInformation');
	$roleInformation.datagrid({
		url : projectPath+'roleAction!datagrid.action',
		singleSelect : true,
		fit : true,
		pagination : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		rownumbers : true,
		idField : 'roleId',
		columns : [[{
			field : 'roleId',
			title : '编号',
			hidden : true
		},{
			field : 'person',
			title : '编号',
			hidden : true
		},{
			field : 'enterpriseId',
			title : '编号',
			hidden : true
		},{
			field : 'systemTypekey',
			title : 'key',
			hidden : true
		},{
			field : 'roleName',
			title : '角色名称',
			sortable : true,
			width : 30,
			formatter: function(value,row,index){
				if(row.roleDefaultTag=='y')
					return "<font color=\"red\">"+value+"</font>";
				else
					return value;
			}
		},{
			field : 'systemTypevalue',
			title : '所属系统',
			sortable:true,
			width : 20
		},{
			field : 'personName',
			title : '创建人',
			sortable:true,
			width : 20
		},{
			field : 'createDate',
			title : '创建日期',
			sortable:true,
			width : 20
		},{
			field : 'remark',
			title : '备注',
			sortable:true,
			width : 20
		},{
			field : 'roleDefaultTag',
			title : '默认角色标示',
			hidden:true,
			width : 20
		}]],
		onDblClickRow : function (rowIndex, rowData){
			showDialog(rowData, false, "查看角色",true);
		}
	});
}

var administrator = false;
var showDialog = function(rowDate,flg,title,tag){
	var d = $('<div style="overflow:hidden;"/>').dialog({
		href : projectPath+"common/base/role/addRoleinfo.jsp",
		modal:true,
		closable : false,
		title : title,
		width : 750,
		height : 500,
		buttons : [{
				text : '确定',
				handler : function (){
			        if($("#openect").val() == "true"){
					    if($('#RoleAddForm').form('validate')){
					    	getChecked();
					    	getSelected();
							$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url: projectPath+'roleAction!savaOrUpdata.action',
							   data: $('#RoleAddForm').serialize(),
							   success: function(r){
								   jsCloseProbar();
								   if(r.success){
									    d.dialog('close');
									    loadRoleDataGrid();
								   }else{
									    alertMsg(r);
								   }
							   },
							   error : function (r){
								   if(r.readyState == '0' && r.status == '0'){
									   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
								   }
							   },
							   beforeSend:function(e){
								   jsProbar();
							   }
							});
						}
			        }else{
						d.dialog('close');
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
			var left = document.getElementById('tobeselected');
			var right = document.getElementById('toselecteds');
			var aroleName = document.getElementById('aroleName');
			var systemType = document.getElementById('asystemType');
			$('#tobeselected').dblclick(
				function () {
					moveOption(left, right);
				}
		    );
	
		    $('#toselecteds').dblclick(
		   		function () { 
		   			moveOption(right, left);
		   		}
		    );
	
		    $('#toright').click(
		     	function () { 
		     		moveOption(left, right);
		     	}
		    );
	
		    $('#toleft').click(
		       	function () { 
		       		moveOption(right, left);
		       	}
		    );
		    
		    $.ajax({
       		   type:'POST',
       		   dataType: 'json',
       		   url:projectPath+'login_getSessionUser.action',
       		   success : function(r){
       		       if(r!=null){
       		    	   var arrayStr=r.split(",");
       		    	   if(loginLevel==arrayStr[0]){
       		    		    openAdmin(rowDate, arrayStr[1],tag);
       		    	   }else{
       		    		    if(systemAll==arrayStr[1]){
       		    		    	openAdmin(rowDate, arrayStr[1],tag);
       		    		    }else{
       		    		        loadDate(rowDate,tag);
       		    		    }
       		    	   }
       			   }else{
       				   alertMsg("失败",'info');
       			   }
       	       }
           });
		    
		   if(rowDate != null){
		    	$("#aroleName").val(rowDate.roleName);
		    	$("#aremark").val(rowDate.remark);
		    	$("#aroleId").val(rowDate.roleId);
		    	$('#asystemType').combobox('setValue', rowDate.systemTypekey);
		    	$("#asystemTypevalue").val(rowDate.systemTypevalue);
		    	$("#aperson").val(rowDate.person);
		    	$("#acreateDate").val(rowDate.createDate);
		    	$("#enterpriseId").val(rowDate.enterpriseId);
		   }
		   $("#openect").val(flg);
		   if(!flg){
			   dis();
		   }
        },
		onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
} 

function dis()  
{  
        var a = document.getElementsByTagName("input");  
        for(var i=0; i<a.length; i++)  
        {  
           if(a[i].type=="checkbox" || a[i].type=="radio")
        	   a[i].disabled=true; 
           if(a[i].type=="text")
        	   a[i].disabled=true;
        }  
        var b = document.getElementsByTagName("select");  
        for (var i=0; i<b.length; i++)  
        {  
           b[i].disabled=true;  
        }  
        var c = document.getElementsByTagName("textarea");  
        for (var i=0; i<c.length; i++)  
        {  
           c[i].disabled=true;  
        }  
}   
function openAdmin(rowDate, systemTypeKey,tag){
	administrator = true;
    $("div[id$='expand']").css("display", "block");
	$('#asystemType').combobox({   
		url:projectPath+'frtOptionsAction!findBaseListData.action?key='+systemType,   
		valueField:'id',   
		textField:'text',
		required:true,
		missingMessage:'所属系统为必选项'
	});
	$('#asystemType').combobox({   
	    onChange : function(n,o){
		     var roleId = $("#aroleId").val();
		     reloadDialog(roleId, n)
		} 
	});
//	if(rowDate != null){
//		$('#asystemType').combobox("setValue",rowDate.systemTypekey);
//	}else{
//		var str = '{"roleId":"","systemTypekey":"'+systemTypeKey+'"}';
//		var obj = jQuery.parseJSON(str);
//		loadDate(obj,tag);	
//	}
}

function loadDate(rowDate,tag){
   if(rowDate != null){
	   loadnoselect(rowDate, null);
	   loadselect(rowDate, null);
	   if(tag){
		   loadtree(rowDate, null);
	   }
   }else{
	   loadnoselect(null, null);
	   loadselect(null, null);
	   if(tag){
		   loadtree(null, null);
	   }
   }
}
function reloadDialog(roleId, systemType) {
	   var str = '{"roleId":"'+roleId+'","systemTypekey":"'+systemType+'"}';
	   var obj = jQuery.parseJSON(str);
	   loadnoselect(obj, systemType);
	   loadselect(obj, systemType);
	   loadtree(obj, systemType);
}

function loadtree(rowDate, systemType){
	var url=null;
	if(rowDate != null){
		url = projectPath+'roleAction!menuRoleTree.action?roleId='+rowDate.roleId+'&systemTypekey='+rowDate.systemTypekey;
	}else if(systemType != null){
		url = projectPath+'roleAction!menuRoleTree.action?roleId=&systemTypekey='+systemType;
	}else{
		url = projectPath+'roleAction!menuRoleTree.action?roleId=';
	}
	$('#making').dialog({
		closable:false
	});
	$('#MyTree').tree({   
        checkbox: true,   
        url: url,
        onLoadSuccess : function(node, data){
		    expandAll();
		    $('#making').dialog('close');
	    }
    });
}

function expandAll(){
	var tree_ = $('#MyTree');
	var node = tree_.tree('getSelected');
	if (node != null){
		tree_.tree('expandAll', node.target);
	} else {
		tree_.tree('expandAll');
	}
}

function loadnoselect(rowDate, systemType){
	var url;
	if(rowDate != null){
		url = projectPath+'roleAction!getnoselectedStuff.action?roleId='+rowDate.roleId+'&systemTypekey='+rowDate.systemTypekey;
	}else if(systemType != null){
		url = projectPath+'roleAction!getnoselectedStuff.action?roleId=&systemTypekey='+systemType;
	}else{
		url = projectPath+'roleAction!getnoselectedStuff.action?roleId=';
	}
	$("#tobeselected").empty();
	$.ajax({
 	   type: 'post',
 	   dataType: 'json',
 	   url: url,
 	   success: function(data){
 		   if(data){
 			  for (var i = 0; i < data.length; i++) {
 				 $("#tobeselected").append("<option value='"+data[i].stfYid+"'>"+data[i].stfName+"</option>");
 			  }
 		   }
 	   },
 	   error : function (r){
 		   if(r.readyState == '0' && r.status == '0'){
 			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
 		   }
 	   }
 	});
}

function loadselect(rowDate, systemType){
	var url;
	if(rowDate != null){
		url = projectPath+'roleAction!getselectedStuff.action?roleId='+rowDate.roleId+'&systemTypekey='+rowDate.systemTypekey;
	}else if(systemType != null){
		url = projectPath+'roleAction!getselectedStuff.action?roleId=&systemTypekey='+systemType;
	}else{
		url = projectPath+'roleAction!getselectedStuff.action?roleId=';
	}
	$("#toselecteds").empty();
	$.ajax({
 	   type: 'post',
 	   dataType: 'json',
 	   url: url,
 	   success: function(data){
 		   if(data){
 			  for (var i = 0; i < data.length; i++) {
 				 $("#toselecteds").append("<option value='"+data[i].stfYid+"'>"+data[i].stfName+"</option>");
 			  }
 		   }
 	   },
 	   error : function (r){
 		   if(r.readyState == '0' && r.status == '0'){
 			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
 		   }
 	   }
 	});
}

function getChecked(){
    var nodes = $('#MyTree').tree('getCheckeds');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if (s != '') 
			s += ',';
		s += nodes[i].id;
	}
	$("#acheckeds").val(s);
}
	
function getSelected(){
	var right = document.getElementById('toselecteds');
	var s = '';
	for(var i = right.options.length - 1 ; i >= 0 ; i--)
    {
		if(right.options[i])
		{
			if (s != '') 
				s += ',';
			s += right.options[i].value;
		}
	}
	$("#aselecteds").val(s);
}

//移动
function moveOption(obj1, obj2)
{
	for(var i = obj1.options.length - 1 ; i >= 0 ; i--)
    {
		if(obj1.options[i].selected)
		{
			var opt = new Option(obj1.options[i].text,obj1.options[i].value);
			opt.selected = true;
			obj2.options.add(opt);
			obj1.remove(i);
		}
	}
} 

//添加 
var addRole = function(){
	showDialog(null, true, "添加角色",false);
}

//修改
var updateRole = function(){
	var datas = $('#roleInformation').datagrid('getSelections');
	if(datas.length>0){
		showDialog(datas[0], true, "修改角色",true);
	}else{
		
	}
}

//删除
var deleteRole = function(){
	var data = $('#roleInformation').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
			if(b){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: projectPath+'roleAction!delete.action?roleDeleteValidateFlag=false',
				   data: data,
				   success: function(r){
					   if(r.success){
						   loadRoleDataGrid();
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
var setDefaultRole = function(){
	var data = $('#roleInformation').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示','设置为默认角色后,所有用户都将拥有此角色及其权限，<br/>请确认是否要设置此角色为默认角色？',function(b){
			if(b){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: projectPath+'roleAction!modifyDefaultRole.action',
				   data: data,
				   success: function(r){
					   if(r.success){
						   $('#roleInformation').datagrid('reload');
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
		 alertMsg('对不起，请先选中要操作的记录！', 'warning');
	}
}
var searchRole = function (){
	var roleInformation = $('#roleInformation');
	roleInformation.datagrid('unselectAll');
	roleInformation.datagrid('load',serializeObject($('#roleSearchForm')));
}

function searchRoleUser(){
	var personnelInformationSet = $('#personnelInformationSet');
	personnelInformationSet.datagrid('unselectAll');
	personnelInformationSet.datagrid('load', serializeObject($('#pisForm')));
}

var clearRole = function (){
	$('#roleSearchForm').find('input').val('');
    $('#roleSearchForm').find('select').val('');
    searchRole();
}

var clearRoleUser = function (){
	$('#pisForm').find('input').val('');
    $('#pisForm').find('select').val('');
    searchRoleUser();
}

var excelRole = function (){
	alert("未实现");
}

var excelRoleUser = function (){
	alert("未实现");
}

var searchUserRoleInfo = function (){
	var personnelInformationSet = $('#personnelInformationSet');
	var rows = personnelInformationSet.datagrid('getSelections');
	if(rows){
	    showUserRoleDialog(rows[0]);
	}
}

var showUserRoleDialog = function(rowDate){
	var d = $('<div style="overflow:hidden;"/>').dialog({
		href : projectPath+"common/base/role/searchUserRoleMenu.jsp",
		modal:true,
		closable : false,
		title : '查看员工角色',
		width : 750,
		height : 500,
		buttons : [{
				text : '取消',
				handler : function (){
					d.dialog('close');
				}
			}
		],
	    onLoad : function (){
		    loadUserRoleMenuTree(rowDate);
		    loadUserRoleInfo(rowDate);
        },
		onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
} 

function loadUserRoleMenuTree(rowDate){
	var url = projectPath+'roleAction!UserRoleMenuTree.action?person='+rowDate.stfId+'&systemType='+rowDate.systemId+'&systemLevel='+rowDate.leavl;
	jsProbar();
	$('#MyTree').tree({   
        checkbox: false,   
        url: url,
        onLoadSuccess : function(node, data){
		    expandAll();
		    jsCloseProbar();
	    }
    });
}

function loadUserRoleInfo(rowDate){
	$.ajax({
	   type: 'post',
	   dataType: 'json',
	   url: projectPath+'roleAction!userRoleInfo.action',
	   data: {person:rowDate.stfId,systemType:rowDate.systemId},
	   success: function(r){
		   if(r.success){
			   $("#broleName").val(r.msg);
		   }else{
			   alertMsg(r.msg);
		   }
	   },
	   error : function (r){
		   if(r.readyState == '0' && r.status == '0'){
			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
		   }
	   }
	});
	
}