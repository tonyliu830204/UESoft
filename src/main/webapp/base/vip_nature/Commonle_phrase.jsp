<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<html>
  <head>
    <title>常用短语</title>
    
  </head>
  
  <body>
<!--    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>-->
	<script type="text/javascript">
		   var editRow=undefined;
	       $(function(){
	           //常用短语
				$('#Commonle_phrase_id').datagrid({
					url : projectPath+'basUsedPhrasesAction_doFindAll.action',
					pagination : true,
					fit : true,
					fitColumns : true,
					singleSelect : true,
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					idField : 'phrasesId',
					sortName:'phrasesId',
					sortOrder:'asc',
					loadMsg : "数据加载中，请稍候……",
					columns : [ [ {
						field : 'phrasesId',
						title : '编号',
						hidden : true,
						width : 100
						
					} ,{
						field : 'phrassesName',
						title : '短语名称',
						sortable : true,
						width : 100,
						editor : {
							type : 'validatebox',
							options : {
								required : true,
								validType:'length[1,30]',
								missingMessage : '短语名称为必填项'
								
							}
						}
					},{
						field : 'memo',
						title : '备注',
						width : 100,
						sortable : true,
						editor : {
							type : 'validatebox',
							options : {
						        validType:'length[0,200]'
							}
						}
					}
					]],  
			          onAfterEdit:function(rowIndex, rowData, changes)
				      {
				           if(rowData.phrasesId==undefined){
					             $.ajax({
						             type:"POST",
						             url:"basUsedPhrasesAction_doAdd.action",
						             data:rowData,
						             dataType:"json",
						             success:function callback(r){
					            	 if(r.success)
					            	 {
					            		  $('#Commonle_phrase_id').datagrid('clearSelections');
					            		  $('#Commonle_phrase_id').datagrid('load');
					            		  cancel();
					            	 }else
					            	 {
					            		 $('#Commonle_phrase_id').datagrid('beginEdit', rowIndex);
					            		 $.messager.alert('优亿软件优亿软件提示',r.msg,'info',function(){});
					            	 }
					               }
					             });
						    }else{
						        $.ajax({
						             type:"POST",
						             url:"basUsedPhrasesAction_doUpdate.action",
						             data:rowData,
						             dataType:"json",
						             success:function callback(r){
							        	 if(r.success){
							        		  $('#Commonle_phrase_id').datagrid('clearSelections');
							        		  $('#Commonle_phrase_id').datagrid('load');
							        		  cancel();
							        	 }else{
							        		 $('#Commonle_phrase_id').datagrid('beginEdit', rowIndex);
							        		 $.messager.alert('优亿软件提示',r.msg,'info',function(){});
							        	 }
					                 }
					             });
						      }
				          }
			        });
	       })
	    
		        //车辆颜色添加事件处理
				function add()
				{
				    if (editRow != undefined) {
						$('#Commonle_phrase_id').datagrid('endEdit', editRow);
						$.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning',function(){
						});
					} else {
						$('#add').linkbutton('disable');
						$('#delete').linkbutton('disable');
						$('#update').linkbutton('disable');
						$('#export').linkbutton('disable');
						 $('#Commonle_phrase_id').datagrid('insertRow', {
							index : 0,
							row : {}
						 });
						  addButton();
						 $('#Commonle_phrase_id').datagrid('beginEdit', 0);
						 editRow = 0;
						 bindEnterInCloumn($('#Commonle_phrase_id'), editRow, 0);
					}
				}
	
				//车辆颜色删除事件操作
				function del() {
					  var data = $('#Commonle_phrase_id').datagrid('getSelected');
					  var index=findSelectRowIndex('Commonle_phrase_id',data);
					  if (data) {
							$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
								if (r) {
									$.ajax({
										type : "POST",
										url : "basUsedPhrasesAction_doDelete.action",
										data : "phrasesId=" + data.phrasesId,
										dataType : "json",
										success : function callback(r){
											if(r.success)
											{
											    $('#Commonle_phrase_id').datagrid('clearSelections');
												$('#Commonle_phrase_id').datagrid('load');
												setSelectRow('Commonle_phrase_id',index);
											}else
												$.messager.alert('优亿软件提示',r.msg,'info',function(){});
										}
									});
						         }
							 });
				   }else{
					   $.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
				   }
			  }
				
			  //车辆颜色修改事件处理
			  function update() {
				 var selects = $('#Commonle_phrase_id').datagrid('getSelections');
				 if (selects.length > 0) {
					if (selects.length == 1) {
						var isno=$('#Commonle_phrase_id').datagrid('validateRow',editRow);
						if(isno)
						{
							$('#add').linkbutton('disable');
							$('#delete').linkbutton('disable');
							$('#update').linkbutton('disable');
							$('#export').linkbutton('disable');
							editRow = $('#Commonle_phrase_id').datagrid('getRowIndex', selects[0]);
							$('#Commonle_phrase_id').datagrid('beginEdit', editRow);
							bindEnterInCloumn($('#Commonle_phrase_id'), editRow, 0);
							  addButton();
						}else
						{
							$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
						   });
						}
					}
				} else {
					 $.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){
						 $('#saveOrCancelBtn').empty();
					 });
				}
			}
	
			//车辆颜色保存事件处理
			function save() {
				if (editRow == undefined) {
					 $.messager.alert('优亿软件提示','当前没有要保存的记录！','info',function(){
						 $('#saveOrCancelBtn').empty();
					 });
				}else
				{
					var isno=$('#Commonle_phrase_id').datagrid('validateRow',editRow);
					if(isno)
						$('#Commonle_phrase_id').datagrid('endEdit', editRow);
					else
						 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(){
						 });
				}
			}	
			
			//按钮区域增加 保存和取消按钮
			function  addButton(){
				var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
				var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					$('#saveOrCancelBtn').append(save).append(cancel);
					$.parser.parse('#saveOrCancelBtn');
				}
			}
			
			//车辆颜色取消事件处理
			function cancel() {
				 $('#Commonle_phrase_id').datagrid('unselectAll');
	    		 $('#Commonle_phrase_id').datagrid('rejectChanges');
	    		 $('#saveOrCancelBtn').empty();
	    		 $('#add').linkbutton('enable');
				$('#delete').linkbutton('enable');
				$('#update').linkbutton('enable');
				$('#export').linkbutton('enable');
	    		 editRow = undefined;
			}
			
			/**
			 * 导出excel
			 */
			function _except(){
				exportEsuyUIExcelFile("Commonle_phrase_idCenter",null,'常用短语信息'+getSystemTime());
			}
			
	</script>
    
    <div class="easyui-layout"  border="false" fit="true">
 		<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			    <privilege:enable code="COMMONLEPHRAESADD">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="add"  iconCls="icon-add" plain="true" onclick="add();">新增</a>
				</privilege:enable>
				<privilege:enable code="COMMONLEPHRAESDELETE">
				<a href="javascript:void(0);" class="easyui-linkbutton" id="delete"  iconCls="icon-remove" plain="true" onclick="del();">删除</a>
				</privilege:enable>
				<privilege:enable code="COMMONLEPHRAESMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="update"  iconCls="icon-edit" plain="true" onclick="update();">修改</a>
				</privilege:enable>
				<privilege:enable code="COMMONLEPHRAESEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="export"  iconCls="icon-export" plain="true" onclick="_except();">导出</a>
				</privilege:enable>
				<span id="saveOrCancelBtn"></span>
	    </div>
		<div id="Commonle_phrase_idCenter" region="center"  style="background:#eee;" border="false">
			<table id="Commonle_phrase_id"></table>
		</div>
	</div>
  </body>
</html>
