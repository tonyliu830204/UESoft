$(function (){
	//1>人事资料的设定
	//员工展现List
    $personnelInformationSet=$('#personnelInformationSet');
     $personnelInformationSet.datagrid({
		   url : projectPath+'basPersonnelInformationSetAction!findAllParam.action',
		   singleSelect : true,
			fit : true,
			pagination : true,
			fitColumns : true,
			rownumbers : true,
			sortName: 'stfYid',//当数据表格初始化时以哪一列来排序。
			sortOrder:'asc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
			idField:'stfYid',
			loadMsg : "数据加载中，请稍候……",
			columns : [[
	            {
					field : 'stfYid',
					title : '员工编号',
					sortable : true,
					width : 30
			   }, {
					field : 'stfName',
					title : '姓名',
					sortable : true,
					width : 30,
					formatter: function(value,row,index){
				   		if(row.leavl==staticEnterpriseAdmin){
				   			return "<font color=\"red\">"+row.stfName+"</font>";
				   		}else{
				   			return row.stfName;
				   		}
					}
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
					field : 'systemValue',
					title : '所属系统',
					sortable:true,
					width : 20
			   },{
					field : 'enterpriseName',
					title : '所属公司',
					width : 80
			   },{
					field : 'deptName',
					title : '所属部门',
					width : 30
			   },{
					field : 'repgrpName',
					title : '维修班组',
					width : 30
		       }, {
					field : 'stfZwgz',
					title : '职位工种',
					sortable:true,
					width : 30
		       }, {
					field : 'jobProName',
					title : '工作属性',
					sortable:true,
					width : 30
		       }, {
					field : 'stfPhone',
					title : '移动电话',
					sortable:true,
					width : 50
			   }, {
					field : 'stfTel',
					title : '固定电话',
					sortable:true,
					width : 50
		       }, {
					field : 'stfZxqkvalue',
					title : '注销情况',
					sortable:true,
					width : 30
		       }, {
					field : 'stfId',
					title : '编号',
					hidden : true
			   }, {
					field : 'stfZxqk',
					title : '注销情况编号',
					hidden : true
		       }, {
					field : 'stfSex',
					title : '性别编号',
					hidden:true
			   },{
					field : 'leavl',
					title : '员工级别编号',
					hidden:true
			   },{
					field : 'stfYes',
					title : '系统用户编号',
					hidden:true
			   },{
					field : 'enterpriseId',
					title : '所属公司序号',
					hidden : true
		       },{
					field : 'parentEnterpriseId',
					title : '所属父公司序号',
					hidden : true
		       },{
					field : 'stfBirthday',
					title : '出生年月',
					hidden:true
		       }, {
			 		field : 'stfGj',
					title : '籍贯',
					hidden:true
		       }, { 
					 field : 'repgrpId',
					 title : '维修班组编号',
					 hidden : true
		       },{ 
					 field : 'repgrpId2',
					 title : '销售班组编号',
					 hidden : true
		       },{
					field : 'deptId',
					title : '所属部门编号',
					hidden : true
		       },{
					field : 'systemId',
					title : '系统编号',
					hidden:true
			   },{//入职日期
					field : 'stfWorkDate',
					title : '入职日期',
					hidden:true
	           }, {
					field : 'stfByyx',
					title : '毕业院校',
					hidden:true
	           }, {
					field : 'stfBysj',
					title : '毕业时间',
					hidden:true
	           }, {
					field : 'stfSxzy',
					title : '所学专业',
					hidden:true
	           }, {
					field : 'stfWhcd',
					title : '文化程度',
					hidden:true
	           }, {
					field : 'stfGznx',
					title : '工作年限',
					hidden:true
               }, {
					field : 'stfJsdj',
					title : '技术等级',
					hidden:true
	           }, {
					field : 'stfYhbyzs',
					title : '已获职业证书',
					hidden:true
               }, {
					field : 'stfSfzhm',
					title : '身份证号码',
					hidden:true
	           }, {
					field : 'stfSg',
					title : '身高',
					hidden:true
	           }, {
					field : 'stfTz',
					title : '体重',
					hidden:true
	           }, {
					field : 'stfSl',
					title : '视力',
					hidden:true
	           }, {
					field : 'stfXx',
					title : '血型',
					hidden:true
               }, {
					field : 'stfHyzk',
					title : '婚姻状况',
					hidden:true
               }, {
					field : 'stfHkszd',
					title : '户口所在地',
					hidden:true
		       }, {
					field : 'stfXjzdz',
					title : '现居住地址',
					hidden:true
		       }, {
					field : 'stfMz',
					title : '民族',
					hidden:true
		       }, {
					field : 'stfZzmm',
					title : '政治面貌',
					hidden:true
		       }, {
					field : 'stfWysp',
					title : '外语水平',
					hidden:true
			   }, {
					field : 'stfJsjsp',
					title : '计算机水平',
					hidden:true
			  }, {
					field : 'stfDzyx',
					title : '电子邮箱',
					hidden:true
			  }, {
					field : 'stfTc',
					title : '特长',
					hidden:true
			  }, {
					field : 'stfAh',
					title : '爱好',
					hidden:true
			  }, {
					field : 'stfDbrxm',
					title : '担保人姓名',
					hidden:true
			  }, {
					field : 'stfDbrsfzhm',
					title : '担保人身份证号码',
					hidden:true
		      }, {
					field : 'stfPoxm',
					title : '配偶姓名',
					hidden:true
			  }, {
					field : 'stfPoxm',
					title : '配偶身份证号码',
					hidden:true
			  }, {
					field : 'stfYjrq',
					title : '押金日期',
					hidden:true
			  }, {
					field : 'stfYjje',
					title : '押金金额',
					hidden:true
			  }, {
					field : 'stfYjbz',
					title : '押金备注',
					hidden:true
			  }, {
					field : 'jobProId',
					title : '工作属性',
					hidden:true
			  }
     ]],onClickRow:function(rowIndex, rowData){
	  	if(selectTag){
	  		$('#tab_id').tabs('select',0);
	  		clerFrom();
	  		var rows = $personnelInformationSet.datagrid('getSelections');//获取当前所选的行
	  		selectJobProId(rows);//获取用户所持有的工作属性
	  		//将现在新选的行重新load在from表单中
	  		$('#jBxx').form('load', rows[0]);
	  		setComboboxValues('jobProId',rowData.jobProId);
	  	}
     },
	rowStyler:function(index,row){
		if(row.leavl==staticAdministrator){
			return 'background-color:#80FF80;';
   		}
	}
 });
     
 $("#stfDzyx").mailAutoComplete();
				       
//用户datagrid显示区域
  
  $('#stfSex').combo('textbox').keydown(function(e){
	  if(e.keyCode == 13){
		  $('#stfYes').combo('textbox').select();
		  $('#stfYes').combo('showPanel');
	  }
  });
  
  $('#stfYes').combo('textbox').keydown(function(e){
	  if(e.keyCode == 13){
		  $('#enterpriseId').combo('textbox').select();
		  $('#enterpriseId').combo('showPanel');
	  }
  });
  $('#enterpriseId').combo('textbox').keydown(function(e){
	  if(e.keyCode == 13){
		  $('#deptId').combo('textbox').select();
		  $('#deptId').combo('showPanel');
	  }
  });
  $('#deptId').combo('textbox').keydown(function(e){
	  if(e.keyCode == 13){
		  $('#repgrpId').combo('textbox').select();
		  $('#repgrpId').combo('showPanel');
	  }
  });
  
  $('#repgrpId').combo('textbox').keydown(function(e){
	  if(e.keyCode == 13){
		  $('#stfZwgz').select();
	  }
  });
  
  //其他信息
  $('#stfWhcd').combo('textbox').keydown(function(e){
	  if(e.keyCode == 13){
		  $('#stfGznx').select();
	  }
  });
  $('#stfXx').combo('textbox').keydown(function(e){
	  if(e.keyCode == 13){
		  $('#stfHyzk').combo('textbox').select();
		  $('#stfHyzk').combo('showPanel');
	  }
  });
  $('#stfHyzk').combo('textbox').keydown(function(e){
	  if(e.keyCode == 13){
		  $('#stfHkszd').select();
	  }
  });
  
});     

//清空按钮
function _clear(){
	$('#pisForm').find('input').val('');
    $('#pisForm').find('select').val('');
    $('#personnelInformationSet').datagrid('unselectAll');
    $personnelInformationSet.datagrid('load', serializeObject($('#pisForm')));
}

//查询按钮
function _search(){
	$('#personnelInformationSet').datagrid('unselectAll');
	$personnelInformationSet.datagrid('load', serializeObject($('#pisForm')));
}

//查询  <获取form表单数据传给后台action  解决无法传 page 和 rows 的时候 用此方法递交>
function doSubmitNotPR(id,formid,url1){
	var form =  $(formid).form();
	var formvalue = serializeObject(form);
	console.info('测试:'+formvalue);
	id.datagrid({
		url : url1,
		data : formvalue 
	});
	
	id.datagrid('load',formvalue);
    id.datagrid({url : url1});	
}

function loadSystemId(){
	   $("div[id$='expand']").css("display", "block");
	   $('#systemId').combobox({   
			url:projectPath+'frtOptionsAction!findBaseListData.action?key=system_',   
		    valueField:'id',   
		    textField:'text',
		    required:true,
		    panelHeight: "auto",
		    missingMessage:'所属系统为必选项',
		    onLoadSuccess: function () { //加载完成后,设置选中第一项
		        $(this).combobox("setValue", systemAll);
         }
		});
}

function clearSystemId(){
	   $("div[id$='expand']").css("display", "none");
	   $('#systemId').combobox({   
			url:projectPath+'frtOptionsAction!findBaseListData.action?key=system_',   
		    valueField:'id',   
		    textField:'text',
		    required:false,
		    missingMessage:'所属系统为必选项'
		});
}

//判断用户级别
function validateLevel(rows){
	var leavl = rows.leavl;
	var stfId = rows.stfId;
	var enterpriseId = rows.enterpriseId;
	var parentEnterpriseId = rows.parentEnterpriseId;
	if(stfId == staticStfId){                                                                                     //自己操作自己
		return false;
	}else if(leavl == staticEnterpriseAdmin){
		if(staticLoginLevel == staticAdministrator && parentEnterpriseId == staticEnterpriseId){                  //超级管理操作管理员
			return false;
		}else if(staticLoginLevel == staticEnterpriseAdmin && parentEnterpriseId == staticEnterpriseId){          //上级管理员操作下级管理员
			return false;
		}else {
			return true;
		}
	}else if(leavl == staticEmployee){
		if(staticLoginLevel == staticAdministrator && enterpriseId == staticEnterpriseId){                      //管理员操作本企业员工
		    return false;
		}else if(staticLoginLevel == staticEnterpriseAdmin && enterpriseId == staticEnterpriseId){                //管理员操作本企业员工
		    return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}

//添加 <增加保存和取消按钮>
function add(){
	 selectTag=false;
     $('#tab_id').tabs('select',0);
     $.ajax({
  		   type:'POST',
  		   dataType: 'json',
  		   url:projectPath+'login_getSessionUser.action',
  		   success : function(r){
  		       if(r!=null){
  		    	   var arrayStr=r.split(","); 
  		    	   if(loginLevel==arrayStr[0]){
  		    		    loadSystemId();
		    	   }else{
		    		    if(systemAll==arrayStr[1]){
		    		    	loadSystemId();
		    		    }
		    	   }
  			   }else{
  				   alertMsg("登录超时",'info');
  			   }
  	       }
     });
     clerFrom();
     $('#jobProId').combobox('clear'); //每次新增的时候清除一下combobox的缓存记录
     $('#enterpriseId').combotree('reload','basPersonnelInformationSetAction!findAllCompany.action');
     nuDisAbledControl();
     disabledControl();
     $('#stfYid').select();
     //清除选中的行
     $personnelInformationSet.datagrid('clearSelections');
	 var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
	 var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel_a();">取消</a>';
	 if($('#button').children('a').length == 0){
	    $('#button').append(save).append(cancel);
		$.parser.parse('#button');
     }
	 $('#loginPassword').val("123456");
	 $('#loginName').attr('readonly',false);
	 $('#stfYid').attr('readonly',false);
	 $('#enterpriseId').combotree('enable');
	 $('#enterpriseId').combotree('setValue',staticEnterpriseId);
	 $('#jobProId').change(function(n,o){
		 setComboboxValues('jobProId',n);
     });
}

//修改 《增加保存和取消按钮》
function update(){
	$('#tab_id').tabs('select',0);
	$('#stfYid').attr('readonly','readonly');
	var rows = $personnelInformationSet.datagrid('getSelections');//获得要选择上的行的对象
	if(rows.length == 1){
		if(validateLevel(rows[0])){
			alertMsg('缺少权限，不能操作！','warning');
			return;
		}else{
			selectTag=false;
			nuDisAbledControl();
			disabledControl();
			var updates = '<a id="updates" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updates();">保存</a>';
			var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel_b();">取消</a>';
			 if($('#button').children('a').length == 0){
			    $('#button').append(updates).append(cancel);
				$.parser.parse('#button');
		     } //得倒这一行的对象 
			 loadSystemId();
			 if(rows[0].leavl==staticEnterpriseAdmin){
				 $('#enterpriseId').combotree('disable');
			 }
			 selectJobProId(rows);
			 $('#updates').linkbutton('enable');
			 $('#cancel').linkbutton('enable');
		     $('#jBxx').form('load', rows[0]);//将此行的对象赋给form表单
		     setComboboxValues('jobProId',rows[0].jobProId);
		     setUser(rows);
		}
	}else{
		   $.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning');
    }
	$('#stfName').select();
}

//(jBxx)基本信息的form表单进行提交
function save(){
	try {
	   if($('#jBxx').form('validate')){
		   jsProbar();
		   $('#tab_id').tabs('select',1);
		   doSave(projectPath+'basPersonnelInformationSetAction!doSave.action',serializeObject($('#jBxx')));
	   }else{
		   $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');   
	   }
	} catch (e) {
		jsCloseProbar();
	}
}

//(jBxx)基本信息的form表单进行提交
function updates(){
	try {
	   if($('#jBxx').form('validate')){
		   jsProbar();
		   $('#tab_id').tabs('select',1);
		   doUpdate(projectPath+'basPersonnelInformationSetAction!doEdit.action',serializeObject($('#jBxx')));
	   }else{
		   $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
	   }
	} catch (e) {
		jsCloseProbar();
	}
} 

function doSave(url1,form1){
	$.ajax({
		type:'POST',
		url:projectPath+'basPersonnelInformationSetAction!validateStfYid.action',
		data:{stfYid:$('#stfYid').val()},
		dataType:'json',
		success:function(r){
			selectTag=true;
			bool = r.success;
			if(!bool){
				alertMsg(r.msg,'info');
				return false;
			}else{
				$.ajax({
					type:'POST',
					url:projectPath+'basPersonnelInformationSetAction!validateStfName.action',
					data:{stfName:$('#stfName').val()},
					dataType:'json',
					success:function(r){
						bool = r.success;
						if(!bool){
							 $.messager.confirm("优亿软件提示", r.msg, function (data) {
							        if (data) {
							        	submitFrom(url1,form1); 
							        }
							        else {
							        	return false;
							        }
							 });
						}else{
							submitFrom(url1,form1); 
						}
				    }
				});
			}
	    }
	});
}

function doUpdate(url2,form2){
	submitFrom(url2,form2); 
}

function submitFrom(url,form){
	$.ajax({
		type:'POST',
		url:url,
		data:form,
		dataType:'json',
		success:function(r){
			selectTag=true;
		    alertMsg(r.msg,'info');
		    jsCloseProbar();
		    if(r.success){
			    cancel_a();
			    $personnelInformationSet.datagrid('reload');
			    clerFrom();
			    $('#stfYid').attr('readonly',false);
			    disAbledControl();
			    enableControl();
			    $('#tab_id').tabs('select',0);
	      	    $("div[id$='expand']").css("display", "none");
		    }
	    }
	});
}

// 人事资料的删除
function deletes(){
	 $('form').find('input').val(''); 
	 doDelete($personnelInformationSet ,projectPath+'basPersonnelInformationSetAction!delete.action');
}

function doDelete(id,url1){
		var delrow = id.datagrid('getSelections');
		if(delrow.length>0){
			if(validateLevel(delrow[0])){
				alertMsg('缺少权限，不能操作！','warning');
				return;
			}else{
				$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
					if(b){
						$.ajax({
							type : 'POST',
							url : url1,
							data : delrow[0],
							dataType:'json',
							success : function(r){
							    alertMsg(r.msg,'info');
							    if(r.success){
							    	id.datagrid('reload');
							    	$personnelInformationSet.datagrid('unselectAll');//取消所选的行,针对一行的
							    }
							}
						});
					}
				});
			}
		}else{
			alertMsg('对不起，请先选中要删除的记录！','warning');
		}
}

//注销某个员工
function logout(){
	   var rows = $personnelInformationSet.datagrid('getSelections');//获得要选择上的行的对象
	   var zxqk='使用中';
		if(rows.length == 1){
			var url = projectPath+'basPersonnelInformationSetAction!logoutUser.action';
			var massage = "";
			if(rows[0].stfZxqk==zxqk){
				massage = '请确认是否注销编号为【'+rows[0].stfYid+'】，姓名为【'+rows[0].stfName+'】的人事资料？';
			}else{
				massage = '请确认是否恢复已注销的编号为【'+rows[0].stfYid+'】，姓名为【'+rows[0].stfName+'】的人事资料？';
			}
			$.messager.confirm('优亿软件提示',massage,function(b){
				if(b){
					doLogout(url,rows[0].stfId, rows[0].stfZxqk);
				}
			});
		}else{
			   $.messager.alert('优亿软件提示','对不起，请先选中要注销的记录 !','warning');
		}
}

//去后台修改要注销人的资料
function doLogout(logUrl,id, stfZxqk){
	$.ajax({
		 type:'POST',
		    url:logUrl,
		    data:{stfId:id,stfZxqk:stfZxqk},
		    dataType:'json',
		    success : function(r){
		    	$.messager.alert('优亿软件提示',r.msg,'info');
		        if(r.success){
		    	    $personnelInformationSet.datagrid('unselectAll');//取消所选的行
		    	    $personnelInformationSet.datagrid('reload');
		        }
	      }
	});
}

//变更某个员工change
function change(){
	    var rows = $personnelInformationSet.datagrid('getSelections');//获得要选择上的行的对象
		if(rows.length == 1){
			var d = $('<div style="background:#eee;" />').dialog({
				modal:true,
				closable : true,
				title : '人事资料变更',
				width : 360,
				height : 370,
				href : projectPath+'common/base/personnel/personnelChange.jsp',
				buttons : [{
					iconCls : 'icon-save',
					text : '保存',
					handler : function (){
						if($('#personnelChangeFrom').form('validate')){
							//判断一个变更号是否存在
						    $.messager.confirm('优亿软件提示','请确认是否将原人事编号【'+rows[0].stfYid+'】变更为【'+$('#newstfYid').val()+'】？',function(b){
						    	if(b){
						    		$.ajax({
						    			type: 'post',
										dataType: 'json',
										url: projectPath+'basPersonnelInformationSetAction!doChangeBasStuff.action',
										data: serializeObject($('#personnelChangeFrom')),
										success: function(r){
						    			    $.messager.alert('优亿软件提示',r.msg,'info');
						    			    if(r.success){
											    d.dialog('close');
											    $personnelInformationSet.datagrid('reload');
											    //清空所有的form表单
											    $personnelInformationSet.datagrid('unselectAll');
										    } 
										} 
									});
								}
							});     
						}else{
							 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning');
							 $('#newStfYid');
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
			    	$('#pcstfId').val(rows[0].stfId);
			    	$('#pcStfYid').val(rows[0].stfYid);
			        $('#pcStfName').val(rows[0].stfName);
			        $('#newstfName').val(rows[0].stfName);
			        $('#newstfYid').focus();
			    }
			});
		}else{
			 $.messager.alert('优亿软件提示','对不起，请先选中要变更的记录！','warning');
			
		}
}

//清空from表单
function clerFrom(){
	   var name = $("#jBxx input[name='struts.token.name']").val();
	   var value = $("#jBxx input[name='"+name+"']").val();
	   $('#jBxx').find('input').val(''); 
	   $('#jBxx').find('select').val('');
	   $('#jBxx').find('textarea').val('');
	   $("#jBxx input[name='struts.token.name']").val(name);
	   $("#jBxx input[name='"+name+"']").val(value);
}
  
//给用户赋值
function  setUser(users){
	$.ajax({
	    type:'POST',
	    url:projectPath+'basPersonnelInformationSetAction!getUserInfo.action',
	    data:{stfYid:users[0].stfYid},
	    dataType:'json',
	    success : function(data){
			$('#userId').val(data.userId);
			$('#loginName').val(data.loginName);
			$('#loginPassword').val(data.loginPassword);
		}
	 });
}

function selectJobProId(rows){ 
	 //去查询员工所拥有的id 
     $.ajax({
	    type:'POST',
	    url:projectPath+'basPersonnelInformationSetAction!selectJobProId.action',
	    data:rows[0],
	    dataType:'json',
	    success : function(list){
           $('#jobProId').combobox('setValues',list);
		}
	 });
}

function cancel_a(){
	$('#stfYid').removeAttr("readonly");
	disAbledControl();
	enableControl();
	$('#tab_id').tabs('select',0);
	$('#button').empty();
    $('#pisForm').find('input').val('');
    $('#pisForm').find('select').val('');
    $('#stfYid').attr('readonly',false);
    clerFrom();
    $personnelInformationSet.datagrid('unselectAll');
    clearSystemId(); 
}
			
function cancel_b(){
	selectTag=true;
	$('#stfYid').removeAttr("readonly");
	disAbledControl();
	enableControl();
	$('#tab_id').tabs('select',0);
	$('#button').empty();
    $('#pisForm').find('input').val('');
    $('#pisForm').find('select').val('');
     //$('#stfYid').attr('readonly',false);
    //$('#stfYid').attr('style','background:white;width:90px');
    clerFrom();
    $personnelInformationSet.datagrid('unselectAll');
}

//隐藏所有按钮
function displayButton(){
	$('a.easyui-linkbutton').linkbutton('disable');
}
			    
//清空所有选中的行 clearSelections
function clearSelectRow(formId){
	$(formId).datagrid('clearSelections');
}

/*
 * 助记码生成器
 */
function handle(oEvent){
    if(window.event){
    	oEvent=window.event;
    }
    var stfName = $('#stfName').val();
    $('#stfMark').empty();
	$('#stfMark').val(makePy(stfName));
}

/**
 * 启用控件
 */
function nuDisAbledControl(){
	$("#jBxx input.easyui-combotree").combobox('enable');
	$("#jBxx input.easyui-combobox").combobox('enable');
	$("#jBxx input").prop("disabled", false);
	$("#jBxx select").prop("disabled", false);
	$("#jBxx textarea").prop("disabled",false);
}

/**
 * 禁用控件
 */
function disAbledControl(){
	$("#jBxx input.easyui-combotree").combobox('disable');
	$("#jBxx input.easyui-combobox").combobox('disable');
	$("#jBxx input").prop("disabled", true);
	$("#jBxx select").prop("disabled", true);
	$("#jBxx textarea").prop("disabled",true);
}

function disabledControl(){
	$("#button0 a.easyui-linkbutton").linkbutton('disable');
}


function enableControl(){
	$("#button0 a.easyui-linkbutton").linkbutton('enable');
}

/**
 * 给控件绑定回车事件
 * @param id 控件ID
 * @param event
 * @return
 */
function EnterPress(id,event){
	if(event.keyCode==13){
		if(id[0].classList[0] == "easyui-combobox"){
			id.combo('textbox').select();
			id.combo('showPanel');
		}else{
			id.select();
		}
	}
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
	showEditDialog("personnelInformationSet",null,"personnelCenter","开始导出","导出配置",0,_callbackExcept);
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
	exportEsuyUIExcelFile(parentId,fieldNames,"人事资料设定"+getSystemTime());
}
/**
 * 导入excel
 * 将要导入的Excel文件列顺序与选择对应的列顺序一致
 * @return
 */
function _import(){
	showEditDialog("personnelInformationSet",null,"personnelCenter","开始导入","导入配置",1,null);
}

/**
 * 隐藏列
 * 将选的列因此
 * @return
 */
function _hideColumn(){
	showEditDialog("personnelInformationSet",null,"personnelCenter","隐藏列","隐藏列配置",3,null);
}

/**
 * 打印字段设置
 * 编辑、选择不同分组
 * @return
 */
function _config(){
	showEditDialog("personnelInformationSet",personnelSumTable,"personnelCenter","开始打印","打印配置",2,_print);
}
/**
 * 打印字段设置回调函数
 * 将选择的列打印
 * @return
 */
function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}
var stfYid;
/**
 * 套打模板打印
 * 设计、选择套打模板
 * @return
 */
function _otherPrint(){
	var rows = $personnelInformationSet.datagrid('getSelections');//获得要选择上的行的对象
	if(rows.length > 0){
		 this.stfYid = rows[0].stfYid;
	     coloropen(personneltempletA,searchprintTemple);
	}else{
		 $.messager.alert('优亿软件提示','对不起,请先选中要打印记录 !','warning');
	}
}
/**
 * 套打模板打印回调函数
 * 对选择的模板打印
 * @param id
 * @return
 */
function searchprintTemple(id){
	$.ajax({
	 	   type: 'post',
	 	   dataType: 'html',
	 	   url:projectPath+'basPersonnelInformationSetAction!print.action',
	 	   data:{printTempletId: id,stfYid:this.stfYid},
	 	   success: function(data){
			   showPrintDialog(data);
	 	   },
	 	   error : function (r){
	 		   if(r.readyState == '0' && r.status == '0'){
	 			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
	 		   }
	 	   }
	 });
}