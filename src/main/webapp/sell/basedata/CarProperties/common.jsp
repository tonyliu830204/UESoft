<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    request.setCharacterEncoding("utf-8");
	String key = new String(request.getParameter("key").toString().getBytes("ISO8859_1"), "UTF-8");
	String value = new String(request.getParameter("value").toString().getBytes("ISO8859_1"), "UTF-8");
	String number_ = new String(request.getParameter("number_").toString().getBytes("ISO8859_1"), "UTF-8");
	String lable_ = new String(request.getParameter("lable_").toString().getBytes("ISO8859_1"), "UTF-8");
	String value_ = new String(request.getParameter("value_").toString().getBytes("ISO8859_1"), "UTF-8");
	String confirmType = new String(request.getParameter("confirmType").toString().getBytes("ISO8859_1"), "UTF-8");
	String types=null;
	if(request.getParameter("types")!=null){
	 types = new String(request.getParameter("types").toString().getBytes("ISO8859_1"), "UTF-8");
	}
	
	number_ = number_.equals("")?"编号":number_;
	lable_ = lable_.equals("")?"键名":lable_;
	value_ = value_.equals("")?"键值":value_;
	//System.out.println(lable_);
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
		<title>基础数据公共页面</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body class="easyui-layout" >
		<script type="text/javascript">
			 var $chiledData;
			 var editRow=undefined;
			 
			 $(function(){ 
			
				 $.extend($.fn.datagrid.methods, {  
					addToolbarItem: function(jq, items){  
						return jq.each(function(){  
							var toolbar = $(this).parent().prev("div.datagrid-toolbar");
							for(var i = 0;i<items.length;i++){
								var item = items[i];
								if(item === "-"){
									toolbar.append('<div class="datagrid-btn-separator"></div>');
								}else{
									var btn=$("<a href=\"javascript:void(0)\"></a>");
									btn[0].onclick=eval(item.handler||function(){});
									btn.css("float","left").appendTo(toolbar).linkbutton($.extend({},item,{plain:true}));
								}
							}
							toolbar = null;
						});  
			 },
				removeToolbarItem: function(jq, param){  
					return jq.each(function(){  
						var btns = $(this).parent().prev("div.datagrid-toolbar").children("a");
						var cbtn = null;
						if(typeof param == "number"){
							cbtn = btns.eq(param);
						}else if(typeof param == "string"){
							var text = null;
							btns.each(function(){
								text = $(this).data().linkbutton.options.text;
								if(text == param){
									cbtn = $(this);
									text = null;
									return;
								}
							});
						} 
						if(cbtn){
							var prev = cbtn.prev()[0];
							var next = cbtn.next()[0];
							if(prev && next && prev.nodeName == "DIV" && prev.nodeName == next.nodeName){
								$(prev).remove();
							}else if(next && next.nodeName == "DIV"){
								$(next).remove();
							}else if(prev && prev.nodeName == "DIV"){
								$(prev).remove();
							}
							cbtn.remove();	
							cbtn= null;
						}						
					});  
				  } 				
		  });
				 $chiledData=$('#chiledData');
					$chiledData.datagrid({
						url:'${pageContext.request.contextPath}/baseTagAction!baseListData.action?types=<%=types%>',
						queryParams: {pdataKey:$("#pkey").val()},
						fit:true,
						fitColumns : true,
						pagination : true,
						rownumbers : true,
						sortOrder:'asc',
					    sortName:'childId',
						singleSelect : true,
						pageSize : 20,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						loadMsg : "数据加载中，请稍后……",
						columns : [ [{
										title : '<%=number_%>',
										field : 'childId',
										width : 100,
										hidden:true
								    }, {
										title : '<%=lable_%>',
										field : 'dataKey',
										width : 100,
										<%
   										 if(!lable_.equals("地区名称")){
   										%>
   											hidden:true
   										<%
   											}
   										%>
									}, {
										title : '<%=value_%>',
										field : 'dataValue',
										width : 220,
										editor : {
										type:'validatebox',
	  									options : {
	  										required:true,
    										missingMessage:'<%=value_%>必选项',
    										<%
    										 if(lable_.equals("排量编码")){
    										%>
    											   validType:'multiple[\'characterDigit\',\'length[0,4]\']'
    										<%
    											}else if(lable_.equals("短信编码")){
    										%>
    											   validType:'maxLength[100]'
    										<%
    											}else if(lable_.equals("地区名称")){
    										%>	
											      validType:'zip'
    										<%	
    											}else if(lable_.equals("险种编码")){
    											
    										%>	
    											  validType:'multiple[\'character_Digit\',\'length[0,20]\']'
    										 <% 
    											}else if(lable_.equals("学历编码")){
    											
    										%>	
    											  validType:'multiple[\'chinese\',\'length[0,10]\']'   											
    										<%
    										    }else{
    										%>
    										      validType:'multiple[\'characterDigit\',\'length[0,20]\']'   											
    										<%
    										    }
    										%>
										}
	  									}
									}, {
										title : '备注',
										field : 'remark',
										width : 100,
										editor : {
	  									type:'validatebox',
	  									options : {
											validType:'multiple[\'characterDigit\',\'length[0,50]\']'
										}
	  									}
									} ,{
										title : '父id',
										field : 'parentId',
										width : 100,
										hidden:true
								    },{
										title : '创建人',
										field : 'stfId',
										width : 100,
										hidden:true
								    },{
										title : '创建时间',
										field : 'createTime',
										width : 100
								    }
					            ]],
					 onAfterEdit:function(rowIndex, rowData, changes)
				    {
				           if(rowData.childId==undefined)
				           {				
					             $.ajax({
						             type:"POST",
						             url:"baseTagAction!addchiledDictionary.action?types=<%=types%>",		
						             data:"dataKey="+rowData.dataKey+"&dataValue="+rowData.dataValue+"&remark="+rowData.remark+"&pdataKey="+$("#pkey").val()+"&pdataValue="+$("#pvalue").val()+"&lable=<%=lable_%>",
						             dataType:"json",
						             success:function callback(r){
					            	 if(r.success)
					            	 {
					            		 $('#chiledData').datagrid('clearSelections');
					            		 $('#chiledData').datagrid('load');
					            		 cancel();
					            	 }else
					            	 {
					            		 $('#chiledData').datagrid('beginEdit',rowIndex);
					            		 $.messager.alert('优亿软件提示',r.msg,'info'); 
					            	 }
					               }
					             });
						    }else
						    {
						         $.ajax({
						             type:"POST",
						             url:"baseTagAction!updateBasChilddictionary.action",
						             data:"childId="+rowData.childId+"&dataKey="+rowData.dataKey+"&dataValue="+rowData.dataValue+"&remark="+rowData.remark
						             +"&pdataKey="+$("#pkey").val()+"&pdataValue="+$("#pvalue").val()+"&parentId="+rowData.parentId+"&stfId="+rowData.stfId+"&createTime="+rowData.createTime+"&lable=<%=lable_%>",
						             dataType:"json",
						             success:function callback(r){
						        	   if(r.success)
						        	   {
						        		   $('#chiledData').datagrid('clearSelections');
						        		   $('#chiledData').datagrid({
											     url:'${pageContext.request.contextPath}/baseTagAction!baseListData.action?types=<%=types%>',
											     loadMsg:'更新数据中......'
										    });
						        		   cancel();
						        	   }else
						        	   {
						        		   $('#chiledData').datagrid('beginEdit',rowIndex);
						        		   $.messager.alert('优亿软件提示',r.msg,'info');
						        	   }
					                 }
					             });
						       }
				            },
				         toolbar:[{
				         id:'add',
	  	    			text:'新增',
	  	    			iconCls:'icon-add',
	  	    			handler: function (){
	  	    				<%
	  	    				if(lable_.equals("地区名称")){
	  	    				%>
		  	    				$chiledData.datagrid('addEditor',[{
		 	    						title : '<%=lable_%>',
										field : 'dataKey',
										width : 100,
										editor : {
	  									type:'validatebox',
	  									options : {
											required : true,					
											missingMessage : '<%=lable_%>为必选项',
										    validType:'multiple[\'characterDigit\',\'length[0,50]\']'   		
										}
									  }
		 	    				   }]
		 	    				  );
	  	    				<%}
	  	    				%>
	  	    				add();
	  	    			}
	  	    		},{id:'delete',
	  	    			text:'删除',
	  	    			iconCls:'icon-remove',
	  	    			handler: function (){
	  	    				_remove();
	  	    			}
	  	    		},{id:'update',
	  	    			text:'修改',
	  	    			iconCls:'icon-edit',
	  	    			handler: function (){
	  	    			<%
	  	    				if(lable_.equals("地区名称")){
	  	    				%>
		  	    				$chiledData.datagrid('addEditor',[{
		 	    						title : '<%=lable_%>',
										field : 'dataKey',
										width : 100,
										editor : {
	  									type:'validatebox',
	  									options : {
											required : true,					
											missingMessage : '<%=lable_%>为必选项',
										    validType:'multiple[\'characterDigit\',\'length[0,50]\']'   		
										}
									  }
		 	    				   }]
		 	    				  );
	  	    				<%}
	  	    				%>
	  	    				edit();
	  	    			}
	  	    		},{id:'query',
	  	    			text:'查询',
	  	    			iconCls:'icon-search',
	  	    			handler: function (){
	  	    				query();
	  	    			}
	  	    		},{id:'clearform',
	  	    			text:'清空',
	  	    			iconCls:'icon-cancel',
	  	    			handler: function (){
	  	    				clearSearchCondition();
	  	    			}
	  	    		},{id:'print',
	  	    			text:'导出',
	  	    			iconCls:'icon-export',
	  	    			handler: function (){
	  	    				_except();
	  	    			}
	  	    		}],
	  	    		onLoadSuccess:function(){
		            	 if(opretion!=null  && opretion==true){
			            		$('#chiledData').datagrid('insertRow', {
									index : 0, 
									row : {}
								 });
			            		 $('#chiledData').datagrid('beginEdit', 0);
			            	}
				  	    }
					});
					
					
					 //导出
 function _except(){
	 var data =  $("#chiledData").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		
  showEditDialog("chiledData",null,"leixin","开始导出","导出配置",0,_callbackExcept);
	 
 }

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"基础数据设置"+getSystemTime());
}
var opretion=false;
			function add() {
				if (editRow != undefined) {
					 $('#chiledData').datagrid('endEdit', editRow);
					 $.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning');
				}else{
					if ($('#save').length == 0 && $('#cancel').length == 0) {
			    		$('#add').linkbutton('disable');
						$('#delete').linkbutton('disable');
						$('#update').linkbutton('disable');
						$('#print').linkbutton('disable');
						$('#query').linkbutton('disable');
						$('#clearform').linkbutton('disable');
						$('#chiledData').datagrid('insertRow', {
							index : 0, 
							row : {}
						 });
					 $('#chiledData').datagrid('addToolbarItem',[{"text":"保存","iconCls":"icon-save","handler":function(){
	    	        		save();
	    	         }},{"text":"取消","iconCls":"icon-undo","handler":function(){
	    	        		cancel();
	    	         }}])
	    	         opretion=true;
					 $('#chiledData').datagrid('beginEdit', 0);
					 
					 editRow = 0;
					 bindEnterInCloumn($('#chiledData'), editRow, 0);
					}
				}
	        }
		    
            // 采购计划保存事件处理
			function save() {
				if (editRow == undefined) {
					 $.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
						 $('#chiledData').datagrid('removeToolbarItem', '保存');
						 $('#chiledData').datagrid('removeToolbarItem', '取消');
					 });
				}else
				{
					var isno=$('#chiledData').datagrid('validateRow',editRow);
					if(isno ){
						$('#chiledData').datagrid('endEdit', editRow);
					}else{
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning');
					}
			    }	
		    }
		    
		    	
		    function _remove() {
		       var selects = $('#chiledData').datagrid('getSelections');
		       var data = $('#chiledData').datagrid('getSelected');
		        var index=findSelectRowIndex('chiledData',data);
		        if (selects.length > 0) {
			       if (selects.length == 1) {
			    	   
			    	   $.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
							if (r) {
									 $.ajax({
											type : "POST",
											dataType : "json",
											url : "baseTagAction!deleteBasChilddictionary.action",
											data :$chiledData.datagrid('getSelected'),
											success : function callback(r){
												if(r.success)
												{
													$('#chiledData').datagrid('clearSelections');
													$('#chiledData').datagrid( {
														url : 'baseTagAction!baseListData.action?types=<%=types%>',
														loadMsg : '更新数据中......'
													});
													setSelectRow('chiledData',index);
												}else
												{
													$.messager.alert('优亿软件提示',r.msg,'info');
												}
											}
										});
							         }
						 });
			          }
			   }else{
				 $.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
			   }
	    }
		
		function edit() {
			var selects = $('#chiledData').datagrid('getSelections');
			
			if (selects.length > 0) {
				if (selects.length == 1)
				{
					var isno=$('#chiledData').datagrid('validateRow',editRow);
					if(isno)
					{
						if ($('#save').length == 0 && $('#cancel').length == 0) {
			    		$('#add').linkbutton('disable');
						$('#delete').linkbutton('disable');
						$('#update').linkbutton('disable');
						$('#print').linkbutton('disable');
						$('#query').linkbutton('disable');
						$('#clearform').linkbutton('disable');
						editRow = $('#chiledData').datagrid('getRowIndex', selects[0]);
						$('#chiledData').datagrid('beginEdit', editRow);
						bindEnterInCloumn($('#chiledData'), editRow, 0);
						$('#chiledData').datagrid('addToolbarItem',[{"text":"保存","iconCls":"icon-save","handler":function(){
		    	        	 save();
		    	        }},{"text":"取消","iconCls":"icon-undo","handler":function(){
		    	        	 cancel();
		    	        }}])
		    	       }
					}else
					{
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
					    });
					}
				}
			}else {
				$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(r){
						$('#chiledData').datagrid('removeToolbarItem', '保存');
						$('#chiledData').datagrid('removeToolbarItem', '取消');
				 });
			}
		}
		// 取消事件处理
		function cancel()
		{	$('#add').linkbutton('enable');
			$('#delete').linkbutton('enable');
			$('#update').linkbutton('enable');
			$('#print').linkbutton('enable');
			$('#query').linkbutton('enable');
			$('#clearform').linkbutton('enable');
			 $('#chiledData').datagrid('unselectAll');
    		 $('#chiledData').datagrid('rejectChanges');
    		 $('#chiledData').datagrid('removeToolbarItem','保存');
    		 $('#chiledData').datagrid('removeToolbarItem','取消');
    		 opretion=false;
    		 //$('#chiledData').datagrid('deleteRow', 0);
    		 $('#chiledData').datagrid('reload');
    		 editRow = undefined;
		}
		
		var query = function (){
			$('#chiledData').datagrid('unselectAll');
			$('#chiledData').datagrid('load', serializeObject($('#childQueryForm')));
		}
		function clearSearchCondition(){
		$('#dataKey').val("");
		$('#dataValue').val("");
		$.ajax({                                           
			type : 'POST',
			url : 'baseTagAction!baseListData.action?types=<%=types%>',
			data:$('#childQueryForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#chiledData').datagrid('load',serializeObject($('#childQueryForm')));
			}
	    });
		
	}
			});
</script>
		<div class="easyui-layout" data-options="fit:true, border: false">
				<div region="center"  split="false" border="false">
					<div id="cc" class="easyui-layout" style="width:600px;height:500px;" fit="true" border="false">		
						<div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:3px; background:#eee; height:56px;" border="false">
								<form id="childQueryForm" >	
									 <input type="hidden" id="pkey" name="pdataKey" value="<%=key%>"><input type="hidden" id="pvalue" name="pdataValue" value="<%=value%>">
										     
									<table>
										 <tr>
									<%
	  	    						if(lable_.equals("地区名称")){
	  	    						%>
											<td><%=lable_%>:<br></td>
											<td><input type="text" id="dataKey" name="cquerydataKey" style="background-color: rgb(192, 216, 216);"></td>
									<%
    								    }
    								%>		
											<td><%=value_%>:<br></td>
											<td><input type="text" id="dataValue" name="cquerydataValue" style="background-color: rgb(192, 216, 216);"></td>
										</tr>
									</table>
							 </form>
			   			</div>	   		
			   			<div id="leixin" region="center" style="background:#eee;" border="false">
			   			 	<table id="chiledData" ></table>
			   			</div>   
					</div>	
		   		</div>
  	   </div>
 </body>
</html>