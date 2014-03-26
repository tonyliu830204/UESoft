<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆加装单</title>
	<script type="text/javascript">
	var staticFrtResevationParts=null;
	var staticFrtReceptionItems=null;
	$(function (){
		//车辆加装汇总
		$('#datagrid_car_fix').datagrid({
			url : 'carFixAction!getCarFixInforNoFinish.action',
			rownumbers : true,
			pagination:true,
		    fit:true,
		    singleSelect:true,
		    pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			fitColumns : true, 
			idField : 'install_id',
			loadMsg : "数据加载中，请稍后……",
			columns : [[{
				field : 'install_id',
				title : '加装号',
				width : 1,
				hidden : true
			},{
				field : 'finish_state',
				title : '完工状态号',
				width : 1,
				hidden : true
			},{
				field : 'examine',
				title : '审核状态号',
				width : 1,
				hidden : true
			},{
				field : 'finish_stateKey',
				title : '完工状态key',
				width : 1,
				hidden : true
			},{
				field : 'examineKey',
				title : '审核状态key',
				width : 1,
				hidden : true
			},{
				field : 'install_code',
				title : '加装单号',
				width : 100,
				sortable : true
			},{
				field : 'xs_car_code',
				title : '车辆编号',
				width : 100,
				sortable : true
			},{
				field : 'xs_car_licensePlate',
				title : '车牌照',
				width : 100,
				sortable : true
			},{
				field : 'xs_car_color',
				title : '车辆外观色',
				width : 100,
				sortable : true
			},{
				field : 'xs_car_vin_number',
				title : 'VIN号',
				width : 100,
				sortable : true 
			},{
				field : 'xs_car_ocn',
				title : 'OCN号',
				width : 120,
				sortable : true
			},{
				field : 'xs_car_motor_number',
				title : '发动机号',
				width : 120,
				sortable : true
			},{
				field : 'xs_car_brand',
				title : '车辆品牌',
				width : 120,
				sortable : true
			},{
				field : 'xs_car_model_id',
				title : '车辆型号',
				width : 80,
				sortable : true
			},{
				field : 'sun_money',
				title : '总金额',
				width : 100,
				sortable : true 
			},{
				field : 'finish_stateName',
				title : '完工状态',
				width : 120,
				sortable : true
			}
			]],
			onDblClickRow : function(rowIndex, rowData){
			    $('#tt').tabs('select',1);
			    $('#form_car_fix_detail').form('load', rowData);
			    var partDateGrid = $('#partsDatagrid');
				partDateGrid.datagrid({
					url : 'carFixAction!findPart.action?install_id=' +rowData.install_id
				});
		    }
		});

        //--标签页切换
		$('#tt').tabs({   
			onSelect:function(title,index){  
				if(index == 0){
					$('#spo_addBtn').linkbutton('enable');
					$('#spo_searchBtn').linkbutton('enable');
					$('#spo_clearBtn').linkbutton('enable');
				}else{
					$('#spo_addBtn').linkbutton('disable');
					$('#spo_searchBtn').linkbutton('disable');	
					$('#spo_clearBtn').linkbutton('disable');
				}
		    }   
		});

		$('#spo_addBtn').bind("click", function(){
			startTask();
		});
		
		//--查询
		$('#spo_searchBtn').bind("click", function(){
			var coudatagrid = $('#datagrid_car_fix');
			coudatagrid.datagrid('unselectAll');
			coudatagrid.datagrid('load', serializeObject($('#form_car_fix_id')));
		});

        //--清空表单
		$('#spo_clearBtn').bind("click", function(){
			$('#form_car_fix_id').form('clear');
			var coudatagrid = $('#datagrid_car_fix');
			coudatagrid.datagrid('unselectAll');
			coudatagrid.datagrid('load', serializeObject($('#form_car_fix_id')));
		});

		function startTask(){
			var coudatagrid = $('#datagrid_car_fix');
			var rows = coudatagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows[0].finish_stateKey == "<%=Contstants.FINISHWORK.NOKAIFINISH %>" || rows[0].finish_stateKey == "<%=Contstants.FINISHWORK.NOFINISH %>"){
					$('#tt').tabs('select',1);
					$('#parts_add').linkbutton('enable');
					$('#parts_remove').linkbutton('enable');
					$('#spo_saveBtn').linkbutton('enable');
					$('#spo_cancelBtn').linkbutton('enable');
					
				    $('#install_id').val(rows[0].install_id);
				    $('#install_code').val(rows[0].install_code);
				    $('#xs_car_licensePlate').val(rows[0].xs_car_licensePlate);
				    $('#xs_car_vin_number').val(rows[0].xs_car_vin_number);
				    $('#xs_car_ocn').val(rows[0].xs_car_ocn);
				    $('#sun_money').val(rows[0].sun_money);
				    $('#finish_state').val(rows[0].finish_state);
				    $('#finish_stateName').val(rows[0].finish_stateName);
				    $('#examine').val(rows[0].examine);
				    $('#examineName').val(rows[0].examineName);
	
	                /**更改完成状态**/
				    $.ajax({ 
						type : "POST",
						url : "carFixAction!finishCarFix.action",
						data : 'install_id='+rows[0].install_id+"&finish_stateKey=<%=Contstants.FINISHWORK.NOFINISH %>",
						dataType : "json",
						success:function(r){
				        	if(r.success){
				        		$('#datagrid_car_fix').datagrid('reload');
				        	}
			            }
			        });
	
				    /**配件页加载任务**/
				    var partDateGrid = $('#partsDatagrid');
					partDateGrid.datagrid({
						url : 'carFixAction!findPart.action?install_id=' +rows[0].install_id
					});
					$('#parts_add').linkbutton('enable');
					$('#parts_remove').linkbutton('enable');
					$('#spo_addBtn').linkbutton('disable');
					$('#spo_finishBtn').linkbutton('disable');
				}else{
					alertMsg("以完工无需再加装",'info');
				}
			}else{
				alertMsg("请选择要加装的车",'info');
			}
		}

		$('#spo_saveBtn').bind("click", function(){
			var $partsDatagrid = $('#partsDatagrid');
			var rowData = $partsDatagrid.datagrid('getRows');
			for(var rowIndex = 0; rowIndex < rowData.length; rowIndex++){
				$partsDatagrid.datagrid('beginEdit', rowIndex);
				var ed =$partsDatagrid.datagrid('getEditors', rowIndex);
				ed[0].target.select();
				var num = ed[0].target.val();
				if(rowData && rowData[rowIndex].partsNowCount){
					if(num > rowData[rowIndex].partsNowCount){
						alertMsg('数量不能大于库存数', 'warning');
						ed[0].target.numberbox('setValue', rowData[rowIndex].partsNowCount);
						var price = ed[1].target.val();
						var amount = accMul(parseFloat(ed[0].target.val()), parseFloat(price));
						ed[2].target.numberbox('setValue', amount);
						ed[0].target.select();
						return;
					}else{
						$partsDatagrid.datagrid('endEdit', rowIndex);
					}
				}
			}

			var partsDatagridEffectRow = saveAll($partsDatagrid);
			var itemDatagridEffectRow=null;
			var $itemDatagrid = $('#itemDatagrid');
			if($itemDatagrid==null||$itemDatagrid==undefined){
				if($itemDatagrid.length > 0){
				    endEdit($itemDatagrid);
				    itemDatagridEffectRow = saveAll($itemDatagrid);
				}
			}			
			var formEffectRow = new Object();
			formEffectRow['baseInfo'] = JSON.stringify(serializeObject($('#form_car_fix_detail')));
			if(partsDatagridEffectRow!=null){
				partsDatagridEffectRow=JSON.stringify(partsDatagridEffectRow);
			}else{
				partsDatagridEffectRow="";
			}
			if(itemDatagridEffectRow!=null){
				itemDatagridEffectRow=JSON.stringify(itemDatagridEffectRow);
			}else{
				itemDatagridEffectRow="";
			}
			var data=null;
			if(partsDatagridEffectRow || formEffectRow){
console.info(formEffectRow);
				data = "configParts="+partsDatagridEffectRow+"&baseInfo="+JSON.stringify(formEffectRow);
				data += "&configItem="+itemDatagridEffectRow;
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: "carFixAction!save.action",
				   data: data,
				   success: function(r){
					   alertMsg(r); 
					   if(r.success){
						   $partsDatagrid.datagrid('acceptChanges');
						   $itemDatagrid.datagrid('acceptChanges');
					   }
				   },
				   error : function (r){
					   alertMsg(r); 
				   }
				});
			}
			$('#spo_saveBtn').linkbutton('disable');
			$('#spo_cancelBtn').linkbutton('disable');
			$('#spo_addBtn').linkbutton('enable');
			$('#spo_searchBtn').linkbutton('enable');
			$('#spo_clearBtn').linkbutton('enable');
			$('#spo_finishBtn').linkbutton('enable');
			$('#tt').tabs('select',0);
		});

		$('#spo_cancelBtn').bind("click", function(){
			$('#parts_add').linkbutton('disable');
			$('#parts_remove').linkbutton('disable');
			$('#tt').tabs('select',0);
			$('#button').empty();
			$('#form_car_fix_detail').form('clear');
		    $('#spo_saveBtn').linkbutton('disable');
			$('#spo_cancelBtn').linkbutton('disable');
			$('#spo_addBtn').linkbutton('enable');
			$('#spo_finishBtn').linkbutton('enable');
		});

		$('#spo_finishBtn').bind("click", function(){
			var coudatagrid = $('#datagrid_car_fix');
			var row = coudatagrid.datagrid('getSelected');
			if(row){
				$.ajax({ 
					type : "POST",
					url : "carFixAction!finishCarFix.action",
					data : 'install_id='+row.install_id+"&finish_stateKey=<%=Contstants.FINISHWORK.YIFINISH %>",
					dataType : "json",
					success:function(r){
						alertMsg(r.msg,'info');
			        	if(r.success){
			        		$('#datagrid_car_fix').datagrid('reload');	
			        	}
		            }
		        });
			}
		});
		
		$('#spo_saveBtn').linkbutton('disable');
		$('#spo_cancelBtn').linkbutton('disable');
	});
	function showAmount(){
		if(staticFrtResevationParts!=null){
			staticFrtResevationParts = prosceniumUpdate('partsDatagrid',staticFrtResevationParts);
		}
		if(staticFrtReceptionItems!=null){
			staticFrtReceptionItems = prosceniumUpdate('itemDatagrid',staticFrtReceptionItems);
		}
		var items=null;
		if(staticFrtReceptionItems==null){
			items="";
		}else{
			items=JSON.stringify(staticFrtReceptionItems);
		}
		var parts=null;
		if(staticFrtResevationParts==null){
			parts="";
		}else{
			parts=JSON.stringify(staticFrtResevationParts);
		}
		/*计算收费额*/
		$.ajax({
			type : 'post',
			url : 'carFixAction!totemoney.action',
			dataType : 'json',
			data:'configParts='+parts+ '&configItem='+items,
			success : function(r) {
				if(r.success)
				$('#sun_money').val(r.obj);
			}
		});
	}
	</script>
  </head>
  <body>
    <div class="easyui-layout" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:40px;" border="false">
		     <a href="javascript:void(0);" id="spo_addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" >开工</a>
		     <a href="javascript:void(0);" id="spo_searchBtn" class="easyui-linkbutton" plain="true" iconCls="icon-search" >查询</a>
		     <a href="javascript:void(0);" id="spo_clearBtn" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" >清空</a>
		     <a href="javascript:void(0);" id="spo_finishBtn" class="easyui-linkbutton" plain="true" iconCls="icon-ok" >完工</a>
		     <a href="javascript:void(0);" id="spo_saveBtn" class="easyui-linkbutton" plain="true" iconCls="icon-save" >保存</a>
		     <a href="javascript:void(0);" id="spo_cancelBtn" class="easyui-linkbutton" plain="true" iconCls="icon-undo" >取消</a>
		     <span id="button"></span><br/> 
		</div>
		<div region="center" style="background:#eee;" border="false">
		    <div id="tt" class="easyui-tabs" fit="true" border="false">  
		         <div title="车辆加装汇总" style="display:block;"  fit="true">
			         <div id="cc" class="easyui-layout" fit="true" border="false">
			              <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
			                   <form id="form_car_fix_id">
								 <table>
									 <TR>
									 	  <td>VIN号:</td>
									 	  <td><input name="xs_car_vin_number" type="text" class="easyui-validatebox" style="width:180px;"/></td>
									 	  <td>OCN号:</td>
									 	  <td><input name="xs_car_ocn" type="text" class="easyui-validatebox" style="width:180px;"/></td>
									 	  <td>完工状态:</td>
									 	  <td><input name="finish_state" type="text" class="easyui-combobox" style="width:180px;"
									 	        data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.FINISHWORK.FINISHWORK%>',
												editable:false,
									    		valueField:'id',   
									    		textField:'text',
									    		mode : 'remote'"/>
									 	  </td>
								 	 </TR>
								 </table>
							   </form>
			              </div>
			              <div id="cun" region="center" style="background:#eee;" border="false">
					           <table id="datagrid_car_fix"></table>
			              </div>
			         </div>
		         </div>
		         <div title="加装配件明细" style="display:block;"  fit="true" onclick="showAmount();">
		             <div id="dd" class="easyui-layout" fit="true" border="false">
			             <div data-options="region:'north',title:'明细'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
			                   <form id="form_car_fix_detail">
								 <table>
								 	 <TR>
									 	  <td>加装单号:</td>
									 	  <td>
									 	  <input id="install_id" name="install_id" type="hidden" >
									 	  <input id="install_code" name="install_code" type="text" class="easyui-validatebox" style="width:180px;" readonly="readonly" />
									 	  </td>
									 	  <td>车牌照:</td>
									 	  <td><input id="xs_car_licensePlate" name="xs_car_licensePlate" type="text" class="easyui-validatebox" style="width:180px;" readonly="readonly"/></td>
									 	  <td>VIN:</td>
									 	  <td><input id="xs_car_vin_number" name="xs_car_vin_number" type="text" class="easyui-validatebox" style="width:180px;" readonly="readonly"/></td>
									 	  <td>OCN:</td>
									 	  <td><input id="xs_car_ocn" name="xs_car_ocn" type="text" class="easyui-validatebox" style="width:180px;" readonly="readonly"/></td>
								 	 </TR> 
								 	 <TR>
									 	  <td>总金额:</td>
									 	  <td><input id="sun_money" name="sun_money" type="text" class="easyui-validatebox" style="width:180px;" readonly="readonly" /></td>
									 	  <td>完工状态:</td>
									 	  <td><input id="finish_state" name="finish_state" type="hidden"><input id="finish_stateName" name="finish_stateName" type="text" class="easyui-validatebox" style="width:180px;" readonly="readonly"/></td>
									 	  <td>审核状态:</td>
									 	  <td><input id="examine" name="examine" type="hidden"><input id="examineName" name="examineName" type="text" class="easyui-validatebox" style="width:180px;" readonly="readonly"/></td>
									 	  <td></td>
									 	  <td></td>
								 	 </TR>
								 </table>
							   </form>
			              </div>
			              <div id="detail" region="center" style="background:#eee;" border="false">
					           <div id="frtResevationDetailsTabs" class="easyui-tabs" data-options="fit:true,border:false">
									<div data-options="title:'加装配件',border:'false',href:'${pageContext.request.contextPath}/sell/sellServicing/details/parts.jsp'"></div>
									<div data-options="title:'项目工时',border:'false',href:'${pageContext.request.contextPath}/sell/sellServicing/details/item.jsp'"></div>
							   </div>
			              </div>
		              </div>
		         </div>
		    </div>
		</div>
	</div>
  </body>
</html>