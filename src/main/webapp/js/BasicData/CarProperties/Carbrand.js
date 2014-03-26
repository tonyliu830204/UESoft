var editRow=undefined;
var cbrdName=undefined;
$(function(){
  $('#table10').datagrid({
  url:projectPath+'BasCarBrandAction_view.action',
  pagination:true,
  fit:true,
  pageSize : 10,
  rownumbers:true,
  sortName:'cbrdId',
  sortOrder:'asc',
  singleSelect:true,
  pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
  fitColumns : true,
  idField:'cbrdId',
  columns : [ [ {
			title : '编号',
			field : 'cbrdId',
			width : 100,
			hidden : true
		}, {
			title : '车辆品牌',
			field : 'cbrdName',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					 validType:'multiple[\'characterDigit\',\'length[0,20]\']',
					missingMessage : "车辆品牌为必填项"
				}
			}
		}, {
			title : '工时单价',
			field : 'cbrdPrice',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 0,
					max : 2147483647,
					validType : 'length[0,9]',
					missingMessage : "工时单价为必填项"
				}
			}
		}, {
			title : '保养里程数',
			field : 'cbrdDistance',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 0,
					max : 2147483647,
					validType : 'length[0,8]',
					missingMessage : "保养里程数为必填项"
				}
			}
		}, {
			title : '保养天数',
			field : 'cbrdDays',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 0,
					max : 2147483647,
					validType : 'length[0,8]',
					missingMessage : "保养天数为必填项"
				}
			}
		}, {
			title : '车辆图标',
			field : 'cbrdLogo',
			width : 100,
			sortable : true,
			formatter : function (value, row, index){
				if(value!=undefined)
					return '<img src='+value+' style="width:60px;height:60px;"></img>';
				return '';
			}
		} ] ],
        onAfterEdit:function(rowIndex, rowData, changes)
        {
           if(rowData.cbrdId==undefined)
           {
        		 $.ajax({
		             type:"POST",
		             url:"BasCarBrandAction_add.action",
		             data:rowData,
		             dataType:"json",
		             success:function callback(r){
		            	 if(r.msg=="success")
		            	 {
		            		 $('#table10').datagrid('clearSelections');
		            		 $('#table10').datagrid('load');
		            		 cancel();
		            	 }else
		            	 {
			        		 $('#table10').datagrid('beginEdit', rowIndex);
		            		 $.messager.alert('优亿软件提示',r.msg,'info',function(){});
		            	 }
	                }
	             });
		    }else
		    {
		        $.ajax({
		             type:"POST",
		             url:"BasCarBrandAction_update.action",
		             data:rowData,
		             dataType:"json",
		             success:function callback(r){
		        	   if(r.success)
		        	   {
		        		   $('#table10').datagrid('clearSelections');
		        		   $('#table10').datagrid({
							     url:projectPath+'BasCarBrandAction_view.action',
							     loadMsg:'更新数据中......'
						   });
		        		   cancel();
		        	   }else
		        	   {
		        		   
		        		   $('#table10').datagrid('beginEdit', rowIndex);
		        		   $.messager.alert('优亿软件提示',r.msg,'info',function(){});
		        	   }
	                 }
	             });
		       }
            }
       });
    });

/**
 * 启用控件
 */
function nuDisAbledControl(){
	$('a.easyui-linkbutton').linkbutton('enable');
}

/**
 * 禁用控件
 */
function disAbledControl(){
	$('a.easyui-linkbutton').linkbutton('disable');
}

//按钮区域增加 保存和取消按钮
function  addButton(){
	disAbledControl();
	var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
}
 	//车辆品牌添加事件处理
    function add() {
    	if ($('#save').length == 0 && $('#cancel').length == 0) {
    		$('#table10').datagrid('insertRow', {index : 0, row : {cbrdPrice:0.0}});
    		addButton();
    		$('#table10').datagrid('beginEdit', 0);
    		editRow = 0;
    		bindEnterInCloumn($('#table10'), editRow, 0);		    		
    	}
    }
    
    //车辆品牌保存事件处理
	function save() {
		if (editRow == undefined) {
			 $.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
				 $('#table10').datagrid('removeToolbarItem', '保存');
				 $('#table10').datagrid('removeToolbarItem', '取消');
			 });
		}else
		{
			var isno=$('#table10').datagrid('validateRow',editRow);
			if(isno == true)
				 $('#table10').datagrid('endEdit', editRow);
			else
				 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(){ });
	    }	
    }

	//配件品牌删除操作
    function del() {
        var data = $('#table10').datagrid('getSelected');
        var index=findSelectRowIndex('table10',data);
	       if (data) {
	    	  $.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
				if (r) {
					$.ajax( {
						type : "POST",
						url : "BasCarBrandAction_delCondition.action",
						data : "cbrdId=" + data.cbrdId,
						dataType : "json",
						success : function callback(r) {
					      if(r.msg=="success")
					      {
					    		$.ajax({
									type : "POST",
									url : "BasCarBrandAction_del.action",
									data : "cbrdId=" + data.cbrdId,
									dataType : "json",
									success : function callback(r) {
										if(r.msg=="success"){
										    $('#table10').datagrid('clearSelections');
											$('#table10').datagrid('reload');
											setSelectRow('table10',index);
										}else
											 $.messager.alert('优亿软件提示',r.msg,'info',function(){});
									}
								});
					      }else if(r.msg=="isExist")
					    	  $.messager.alert('优亿软件提示','对不起，该数据已经被使用，不允许删除！','info',function(){});
					      else 
					    	  $.messager.alert('优亿软件提示','对不起，车辆品牌删除异常！','info',function(){ });
					}});
				 }
		     });
          }else{
        	  $.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning',function(){});
          }
		 
}

//车辆品牌修改事件处理    
function update() {
	if ($('#save').length == 0 && $('#cancel').length == 0) {
		var selects = $('#table10').datagrid('getSelections');
		if (selects.length > 0) {
			var isno=$('#table10').datagrid('validateRow',editRow);
			if(isno)
			{
				editRow = $('#table10').datagrid('getRowIndex', selects[0]);
				$('#table10').datagrid('beginEdit', editRow);
				bindEnterInCloumn($('#table10'), editRow, 0);
				addButton();
			}else{
				$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){ });
			}
		}else {
			$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(r){
					$('#table10').datagrid('removeToolbarItem', '保存');
					$('#table10').datagrid('removeToolbarItem', '取消');
			 });
		}
	}
}

//取消事件处理
function cancel()
{
	 $('#table10').datagrid('unselectAll');
	 $('#table10').datagrid('rejectChanges');
	 $('#saveOrCancelBtn').empty();
	 nuDisAbledControl()
	 editRow = undefined;
}

function showWindow(){
	$('#aa').dialog({
		buttons:[{
			text:'Ok',
			iconCls:'icon-ok',
			handler:function(){
		    	var file=document.forms[0].file.value;
		    	if(file != null){
			    	$('#demoForm').attr('action','javaScript:uploadAndSubmit();');
			    	$('#demoForm').submit();
		    	}
			}
		},{
			text:'Cancel',
			handler:function(){
				$('#aa').dialog('close');
			}
		}]
	});
}

function showImg(){
	var path = $('#demoForm input[id="file"]').val();
	var imguri="<img src=\""+path+"\" style=\"float:left;width:200px;height:200px;\"></img>";
	document.getElementById("imgs").innerHTML=imguri;
}

/**
 * 
 * 导出excel
 * @return
 */
function _except(){
	exportEsuyUIExcelFile("CarbrandCenter",null,'车辆品牌信息'+getSystemTime());
}

function uploadAndSubmit() {
	var selects = $('#table10').datagrid('getSelections');
	if (selects.length > 0) {
		var form = document.forms["demoForm"];
		if (form["file"].files.length > 0)
		{
			var size = form["file"].files[0].size / 1024;
			if(size <= 65){
				$.ajaxFileUpload
			    (
			        {
			        	url: projectPath+'BasCarBrandAction_uploadImg.action?cbrdId='+selects[0].cbrdId+'&file='+$("#file").val(),//用于文件上传的服务器端请求地址
			            secureuri:false,//一般设置为false
			            fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
			            dataType: 'json',//返回值类型 一般设置为json
			            beforeSend: function() {  
				            $("#loading").show();  //开始上传文件时显示一个图片
				        },  
				        complete: function() {  
				            $("#loading").hide();  //文件上传完成将图片隐藏起来
				        }, 
			            success: function (data, status)  //服务器成功响应处理函数
			            {
				        	alertMsg(data.msg,'info');
				        	$('#aa').dialog('close');
							$('#table10').datagrid('reload');
			            },
			            error: function (data, status, e)//服务器响应失败处理函数
			            {
			            	alert(data.msg);
			            }
			        }
			    )
			}else{
				alert ("文件大小不能超过65KB");
			}
		}
		else
		{
			alert ("请先选择要上传的图片！");
		}
	}else{
		$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(r){});
	}
}