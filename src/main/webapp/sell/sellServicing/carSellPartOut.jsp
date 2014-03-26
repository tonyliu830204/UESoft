<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>

<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆加装出库</title>
	<script type="text/javascript">
	function dbdisableButton(){
		$('#spo_searchBtn').linkbutton('disable');
		$('#spo_clearBtn').linkbutton('disable');
		$("#form_car_fix_detail input").prop("disabled", false);
	}
	
	function disableButton(){
		$('#spo_addBtn').linkbutton('disable');
		$('#spo_remove').linkbutton('disable');
		$('#spo_searchBtn').linkbutton('disable');
		$('#spo_clearBtn').linkbutton('disable');
		$('#spo_saveBtn').linkbutton('enable');
		$('#spo_cancelBtn').linkbutton('enable');
		$("#form_car_fix_detail input").prop("disabled", false);
		$('#form_car_fix_detail').find('input').val(''); 
		$('#partsDatagrid').datagrid('loadData', {total:0,rows:[]});
		$('#itemDatagrid').datagrid('loadData', {total:0,rows:[]});
		$('#install_code').validatebox('reduce'); 
	}

	function enableButton(){
		$('#spo_addBtn').linkbutton('enable');
		$('#spo_remove').linkbutton('enable');
		$('#spo_searchBtn').linkbutton('enable');
		$('#spo_clearBtn').linkbutton('enable');
		$('#spo_saveBtn').linkbutton('disable');
		$('#spo_cancelBtn').linkbutton('disable');
		$("#form_car_fix_detail input").prop("disabled", true);
		$('#form_car_fix_detail').find('input').val(''); 
		$('#partsDatagrid').datagrid('loadData', {total:0,rows:[]});
		$('#itemDatagrid').datagrid('loadData', {total:0,rows:[]});
		$('#install_code').validatebox('remove');
	}
	var buttonOper;
	var effectRowData;
	$(function (){
		//车辆加装汇总
		$('#datagrid_car_fix').datagrid({
			url : 'carSellOutAction!getOut.action',
			rownumbers : true,
			pagination:true,
		    fit:true,
		    singleSelect:true,
		    pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			fitColumns : true, 
			idField : 'outCode',
			loadMsg : "数据加载中，请稍后……",
			columns : [[
				{field : 'install_id',title : '加装号',width : 1,hidden : true},
				{field : 'xs_car_id',title : '车辆档案序号',width : 1,hidden : true},
				{field : 'xs_car_model_id',title : '车辆型号',width : 1,hidden : true},
				{field : 'xs_car_brand',title : '车辆品牌',width : 1,hidden : true},
				{field : 'xs_car_color',title : '车辆颜色',width : 1,hidden : true},
				{field : 'carInteriorColor',title : '车辆内饰色',width : 1,hidden : true},
				{field : 'outSTF_ID',title : '出库人',width : 1,hidden : true},
				{field : 'outCode',title : '出库单号',width : 100,sortable : true},
				{field : 'outSTF_NAME',title : '出库经办人',width : 100,sortable : true},
				{field : 'outDate',title : '出库日期',width : 100,sortable : true},
				{field : 'install_code',title : '加装单号',width : 100,sortable : true},
				{field : 'sun_money',title : '总金额',width : 100,sortable : true},
				{field : 'xs_car_code',title : '车辆编号',width : 100,sortable : true},
				{field : 'xs_car_licensePlate',title : '车牌照',width : 100,sortable : true},
				{field : 'xs_car_vin_number',title : 'VIN号',width : 100,sortable : true},
				{field : 'xs_car_ocn',title : 'OCN号',width : 120,sortable : true},
				{field : 'xs_car_brandName',title : '车辆品牌',width : 120,sortable : true},
				{field : 'xs_car_modelName',title : '车辆型号',width : 80,sortable : true},
				{field : 'xs_car_colorName',title : '车辆颜色',width : 80,sortable : true},
				{field : 'carInteriorColorName',title : '车辆内饰色',width : 80,sortable : true},
				{field : 'xs_car_motor_number',title : '发动机号',width : 80,sortable : true}
			]],
			onDblClickRow : function(rowIndex, rowData){
			    loadDateGrid();
		    }
		});

		//加装配件
		$partsDatagrid = $('#partsDatagrid');
		$partsDatagrid.datagrid({
			rownumbers : true,
			pagination:true,
		    fit:true,
		    singleSelect:true,
		    pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			fitColumns : true, 
			idField : 'outId',
			loadMsg : "数据加载中，请稍后……",
			columns : [[
				{field : 'outId',title : '配件明细编号',width : 60,hidden : true},
				{field : 'detailId',title : '配件明细编号',width : 60,hidden : true},
				{field : 'install_id',title : '加装编号',width : 60,hidden : true},
				{field : 'partsId',title : '配件编码',width : 60},
				{field : 'partsName',title : '配件名称',width : 60},
				{field : 'punitId',title : '单位编号',width : 60,hidden : true},
				{field : 'punitName',title : '单位',width : 60},
				{field : 'partsNum',title : '数量',width : 60,editor : {type : 'numberbox',options : {precision : 2,required : true,min : 1,missingMessage : "数量为必填项!"}}},
				{field : 'partsRepairPrice',title : '单价',width : 60,editor : {type : 'numberbox',options : {disabled : true,precision : 2,	min : 0.00 	} }	},
				{field : 'partsAmount',title : '金额',width : 60,editor : {type : 'numberbox',options : {disabled : true,precision : 2,	min : 0.00 	} }	},
				{field : 'storeId',title : '仓别',width : 60,hidden : true},
				{field : 'posName',title : '部位名称',width : 60,hidden : true},
				{field : 'partsNowCount',title : '库存量',width : 60}
			]],
			onClickRow : function (rowIndex, rowData){
				if($('#spo_saveBtn').length != 0 && $('#spo_cancelBtn').length != 0){
				    $partsDatagrid.datagrid('beginEdit', rowIndex);
					var ed =$partsDatagrid.datagrid('getEditors', rowIndex);
					ed[0].target.select();
					ed[0].target.bind('keyup', function() {
						var num = ed[0].target.val();
						if(rowData && rowData.partsNowCount){
							if(parseFloat(num) > parseFloat(rowData.partsNowCount)){
								alertMsg('数量不能大于库存数', 'warning');
								ed[0].target.numberbox('setValue', rowData.partsNowCount);
							}
								var price = ed[1].target.val();
								var amount = accMul(parseFloat(num), parseFloat(price));
								ed[2].target.numberbox('setValue', amount);
								//ed[0].target.select();
						}
					});
					ed[1].target.bind('keyup', function() {
						var num = ed[0].target.val();
						var price = ed[1].target.val();
						var amount = accMul(parseFloat(num), parseFloat(price));
						ed[2].target.numberbox('setValue', amount);
					});
					ed[0].target.focus(function (){
						ed[0].target.select();
					});
					ed[1].target.focus(function (){
						ed[1].target.select();
					});
					ed[0].target.keydown(function (e){
						if(e.keyCode == '13'){
							ed[1].target.select();
						}
					});
			   } 
			}
		});

		$itemDatagrid = $('#itemDatagrid');
		$itemDatagrid.datagrid({
			pagination : true,
			fit : true,
			fitColumns : true,
			singleSelect : true,
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'outProjectId',
			loadMsg : "数据加载中，请稍候……",
			columns : [ [
				{field : 'outProjectId',title : '项目明细编号',width : 60,hidden : true},
				{field : 'installItemId',title : '项目明细编号',width : 60,hidden : true},
				{field : 'install_id',title : '加装编号',width : 60,hidden : true},
				{field : 'itemId',title : '项目编号',width : 35,hidden : true},
				{field : 'itemName',title : '项目名称',width : 100,sortable : true},
				{field : 'itemCost',title : '项目成本',width : 60},
				{field : 'itemMoney',title : '项目金额',width : 60},
				{field : 'itemRemark',title : '备注',width : 50,sortable : true}
			]],
			onClickRow : function (rowIndex, rowData){
				if($('#spo_saveBtn').length != 0 && $('#spo_cancelBtn').length != 0){
					$itemDatagrid.datagrid('beginEdit', rowIndex);
					var ed =$itemDatagrid.datagrid('getEditors', rowIndex);
					ed[0].target.focus(function (){
						ed[0].target.select();
					});
					ed[1].target.focus(function (){
						ed[1].target.select();
					});
					ed[0].target.keydown(function (e){
						if(e.keyCode == '13'){
							ed[1].target.select();
						}
					});
					
				}
			}
		});

		function dbremove(){
			var $datagridcarfix = $('#datagrid_car_fix');
			var data = $('#datagrid_car_fix').datagrid('getSelected');
		    var index=findSelectRowIndex('datagrid_car_fix',data);
			var delrow = $datagridcarfix.datagrid('getSelections');
			if(delrow.length>0){
				$.messager.confirm('优亿软件提示','请确认是否要删除出库单['+delrow[0].outCode+']该条记录？',function(b){
					if(b){
						var $partsDatagrid = $('#partsDatagrid');
						var $itemsDatagrid = $('#itemDatagrid');
						
						var partsRowData = $partsDatagrid.datagrid('getRows');
						var itemsRowData = $itemsDatagrid.datagrid('getRows');
						var data;
						if(partsRowData.length>0 || itemsRowData.length>0){
							data = "configParts="+JSON.stringify(partsRowData)+"&baseInfo="+JSON.stringify(delrow[0])+"&configItem="+JSON.stringify(itemsRowData);
						}else{
							data = "baseInfo="+JSON.stringify(delrow[0]);
						}
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: "carSellOutAction!deleteInstall.action",
						   data: data,
						   success: function(r){
							   alertMsg(r);
							   if(r.success){
								   $('#datagrid_car_fix').datagrid('clearSelections');
								   $('#datagrid_car_fix').datagrid('reload');
								   setSelectRow('datagrid_car_fix',index);
							   }
						   },
						   error : function (r){
							   alertMsg(r); 
						   }
						});
						
					}
				});
			}else{
				alertMsg('对不起，请先选中要删除的记录！','warning');
			}
		}
		
		//--查询
		function dbsearch(){
			var coudatagrid = $('#datagrid_car_fix');
			coudatagrid.datagrid('unselectAll');
			coudatagrid.datagrid('load', serializeObject($('#form_car_fix_id')));
		}

		function dbclear(){
			$('#form_car_fix_id').form('clear');
			var coudatagrid = $('#datagrid_car_fix');
			coudatagrid.datagrid('unselectAll');
			coudatagrid.datagrid('load', serializeObject($('#form_car_fix_id')));
		}

		$('#addInstallCode').bind("click", function(){
			so_d2 = $('<div/>');
	    	so_d2.dialog({
				title: '优忆软件',   
			    width: 800,
			    height: 600,
			    cache: false,
			    href: '${pageContext.request.contextPath}/sell/sellServicing/details/addInstallCode.jsp',
			    modal: true,
				buttons : [{
					text : '确定',
					iconCls : 'icon-add',
					handler : function (){
					    getinstall();
					    so_d2.dialog('destroy');
				    }
				}],
				onClose : function (){
			    	$(this).dialog('destroy');
			    },
			    onLoadSuccess : function (data){
			    	$('#pinstall_id').val($("#install_id").val());
			    }
			});
		});

		//保存
		function dbsave(){
			var $partsDatagrid = $('#partsDatagrid');
			var $itemDatagrid = $('#itemDatagrid');
			
			var rowData = $partsDatagrid.datagrid('getRows');
			for(var rowIndex = 0; rowIndex < rowData.length; rowIndex++){
				$partsDatagrid.datagrid('beginEdit', rowIndex);
				var ed =$partsDatagrid.datagrid('getEditors', rowIndex);
				ed[0].target.select();
				var num = ed[0].target.val();
				if(rowData && rowData[rowIndex].partsNowCount){
					if(parseFloat(num) > parseFloat(rowData[rowIndex].partsNowCount)){
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
			var itemDatagridEffectRow = saveAll($itemDatagrid);
			
			var formEffectRow = new Object();
			formEffectRow['baseInfo'] = JSON.stringify(serializeObject($('#form_car_fix_detail')));

			var data;
			if(partsDatagridEffectRow || formEffectRow || itemDatagridEffectRow){
				data = "configParts="+JSON.stringify(partsDatagridEffectRow)+"&baseInfo="+JSON.stringify(formEffectRow);
				if(itemDatagridEffectRow != undefined){
					data += "&configItem="+JSON.stringify(itemDatagridEffectRow);
				}else{
					var rowData_ = $partsDatagrid.datagrid('getRows');
					if(rowData_.length > 0){
						data += "&configItem=-1";
					}
				}
				alert(data);
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: "carSellOutAction!saveOutInstall.action",
				   data: data,
				   success: function(r){
					   alertMsg(r); 
					   if(r.success){
						   $('#datagrid_car_fix').datagrid('reload');
					   }
				   },
				   error : function (r){
					   alertMsg(r); 
				   }
				});
			}
			enableButton();
			$('#tt').tabs('select',0);
		}

		$('#spo_cancelBtn').bind("click", function(){
			buttonOper = "";
			enableButton();
			$('#tt').tabs('select',0);
			$('#form_car_fix_detail').form('clear');
		});
		
		$('#spo_saveBtn').linkbutton('disable');
		$('#spo_cancelBtn').linkbutton('disable');
	});

    function loadDateGrid(){
    	var selects =$('#datagrid_car_fix').datagrid('getSelections');
		if(selects.length > 0){
		    $.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: "carSellOutAction!getOutInstallDetail.action",
			   data: "install_id="+selects[0].install_id,
			   success: function(r){
				   if(r.rows){
					    $('#tt').tabs('select',1);
					    dbdisableButton();
					    $('#form_car_fix_detail').form('load', r.rows[0]);
					    var partDateGrid = $('#partsDatagrid');
						partDateGrid.datagrid({
							 url : 'carSellOutAction!getOutPart.action?install_id=' +r.rows[0].install_id
						});
						var itemDatagrid = $('#itemDatagrid');
						itemDatagrid.datagrid({
							 url : 'carSellOutAction!getOutProject.action?install_id=' +r.rows[0].install_id
						});
				   }
			   },
			   error : function (r){
				   alertMsg(r); 
			   }
			});
		}else{
			$.messager.alert('优亿软件提示','对不起！此加装单没有配件');
		}
    }
    
	function getinstall(){
		var selects =$('#so_frtReceptionTable').datagrid('getSelections');
		if(selects.length > 0){
		     $('#form_car_fix_detail').form('load', selects[0]);
		     var partDateGrid = $('#partsDatagrid');
			 partDateGrid.datagrid({
				 url : 'carFixAction!findPart.action?install_id=' +selects[0].install_id
			 });
			 var itemDatagrid = $('#itemDatagrid');
			 itemDatagrid.datagrid({
				 url : 'carFixAction!findItem.action?install_id=' +selects[0].install_id
			 });
		}else{
			$.messager.alert('优亿软件提示','对不起！此加装单没有配件');
		}	
	}

	function dbadd(){
			disableButton();
			buttonOper = "add";
			$('#tt').tabs('select',1);
			$('#outDate').val(getSystemTime2());
			$('#outSTF_ID').val('<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>');
			$('#outSTF_NAME').val('<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName() %>');
			
			
		}
	
	</script>
  </head>
  <body>
    <div class="easyui-layout" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:40px;" border="false">
		     <a href="javascript:void(0);" id="spo_addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dbadd();">新增</a>
		     <a href="javascript:void(0);" id="spo_remove" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="dbremove();">删除</a>
		     <a href="javascript:void(0);" id="spo_searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="dbsearch();">查询</a>
		     <a href="javascript:void(0);" id="spo_clearBtn" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="dbclear();">清空</a>
		     <a href="javascript:void(0);" id="spo_saveBtn" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="dbsave();">保存</a>
		     <a href="javascript:void(0);" id="spo_cancelBtn" class="easyui-linkbutton" iconCls="icon-undo" plain="true">取消</a>
		</div>
		<div region="center" style="background:#eee;" border="false">
		    <div id="tt" class="easyui-tabs" fit="true" border="false">  
		         <div title="车辆加装汇总" style="display:block;"  fit="true">
			         <div id="cc" class="easyui-layout" fit="true" border="false">
			              <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
			                   <form id="form_car_fix_id">
								 <table>
									 <TR>
									      <td>出库单号:</td>
									 	  <td><input name="outCode" type="text" class="easyui-validatebox" style="width:140px;"/></td>
									      <td>加装单号:</td>
									 	  <td><input name="install_code" type="text" class="easyui-validatebox" style="width:140px;"/></td>
									      <td>车辆编号:</td>
									 	  <td><input name="xs_car_code" type="text" class="easyui-validatebox" style="width:140px;"/></td>
									 	  <td>VIN号:</td>
									 	  <td><input name="xs_car_vin_number" type="text" class="easyui-validatebox" style="width:140px;"/></td>
									 	  
								 	 </TR>
								 	 <TR>
								 	      <td>车辆品牌:</td>
										  <td><input type="text" name="xs_car_brand" class="easyui-combobox" style="width:140px;" data-options="
											url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
											valueField:'id',   
								    		textField:'text',
								    		mode:'remote',
								    		onSelect: function(rec){  
								    			$(this).combobox('textbox').bind('keyup', function (){
								    				if($('#xs_car_brand').combobox('getValue') == '' || $('#xs_car_brand').combobox('getValue') != $('#xs_car_brand').combobox('getText')){
								    					$('xs_car_brand').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
								    				}
								    			});
									            $('#xs_car_model_id').combobox('clear');
									            $('#xs_car_model_id').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
									        } "/></td>
										  <td>车辆型号:</td>
										  <td><input type="text" name="xs_car_model_id" class="easyui-combobox" style="width:140px;"  data-options="
											url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
											valueField:'id',   
								    		textField:'text',
								    		mode:'remote',
								    		onSelect:function(rec){
								    			$(this).combobox('textbox').bind('keyup', function (){
								    				if($('#xs_car_model_id').combobox('getValue') == '' || $('#xs_car_model_id').combobox('getValue') != $('#xs_car_model_id').combobox('getText')){
								    					$('#carArchives_add_carCstlId').combobox('reload', 'carModelAction!findAllCarModel.action');
								    				}
								    			});  
									        } "/></td>
									        <td>OCN号:</td>
									 	  <td><input name="xs_car_ocn" type="text" class="easyui-validatebox" style="width:140px;"/></td>
								 	 </TR>
								 </table>
							   </form>
			              </div>
			              <div id="cun" region="center" style="background:#eee;" border="false">
					           <table id="datagrid_car_fix"></table>
			              </div>
			         </div>
		         </div>
		         <div title="加装配件明细" style="display:block;"  fit="true">
		             <div id="dd" class="easyui-layout" fit="true" border="false">
			             <div data-options="region:'north',title:'明细'" style="overflow: hidden;padding:1px; background:#eee; height:100px;" border="false">
			                   <form id="form_car_fix_detail">
							<input id=enterpriseId name="enterpriseId" type="hidden"    />
								
								 <table>
								 	 <TR>
								 	      <td>出库单号:</td>
									 	  <td><input id="outCode" name="outCode" type="text"  style="width:150px;" readonly="readonly" /></td>
									 	  <td>加装单号:</td>
									 	  <td><input id="install_id" name="install_id" type="hidden"><input id="install_code" readonly="readonly" name="install_code" type="text" style="width:130px;" class="easyui-validatebox" required="true" missingMessage="加装单必填"/>&nbsp;&nbsp;<img id="addInstallCode" src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png"/></td>
									 	  <td>总金额:</td>
									 	  <td><input id="sun_money" name="sun_money" type="text"  style="width:150px;" readonly="readonly" /></td>
									 	  <td>车辆编号:</td>
									 	  <td><input id="xs_car_id" name="xs_car_id" type="hidden"><input id="xs_car_code" name="xs_car_code" type="text" style="width:150px;" readonly="readonly" /></td>
									 	  <td>车牌照:</td>
									 	  <td><input id="xs_car_licensePlate" name="xs_car_licensePlate" type="text"  style="width:150px;" readonly="readonly"/></td>
									 	  <td>VIN:</td>
									 	  <td><input id="xs_car_vin_number" name="xs_car_vin_number" type="text"  style="width:150px;" readonly="readonly"/></td>
								 	 </TR> 
								 	 <TR>  
								 	      <td>OCN:</td>
									 	  <td><input id="xs_car_ocn" name="xs_car_ocn" type="text"  style="width:150px;" readonly="readonly"/></td> 
				                          <td>车辆品牌:</td>
									 	  <td><input id="xs_car_brand" name="xs_car_brand" type="hidden"><input id="xs_car_brandName" name="xs_car_brandName" type="text" style="width:150px;" readonly="readonly"/></td>
									 	  <td>车辆型号:</td>
									 	  <td><input id="xs_car_model_id" name="xs_car_model_id" type="hidden"><input id="xs_car_modelName" name="xs_car_modelName" type="text"  style="width:150px;" readonly="readonly"/></td>
									 	  <td>车辆外观色:</td>
									 	  <td><input id="xs_car_color" name="xs_car_color" type="hidden"><input id="xs_car_colorName" name="xs_car_colorName" type="text"  style="width:150px;" readonly="readonly"/></td>
									 	  <td>车辆内饰色:</td>
									 	  <td><input id="carInteriorColor" name="carInteriorColor" type="hidden"><input id="carInteriorColorName" name="carInteriorColorName" type="text"  style="width:150px;" readonly="readonly"/></td>
									 	  <td>发动机号:</td>
									 	  <td><input id="xs_car_motor_number" name="xs_car_motor_number" type="text"  style="width:150px;" readonly="readonly"/></td>
								 	 </TR>
								 	 <TR>  
								 	      <td>经办人:</td>
									 	  <td><input id="outSTF_ID" name="outSTF_ID" type="hidden">
									 	  <input id="outSTF_NAME" name="outSTF_NAME" type="text"  style="width:150px;" readonly="readonly"/></td> 
				                          <td>经办日期:</td>
									 	  <td><input id="outDate" name="outDate" type="text"  style="width:150px;" readonly="readonly"/></td>
									 	 
									 
								 	 </TR>
								 </table>
							   </form>
			              </div>
			              <div id="detail" region="center" style="background:#eee;" border="false">
					           <div id="frtResevationDetailsTabs" class="easyui-tabs" data-options="fit:true,border:false">
									<div data-options="title:'加装配件',border:'false'">
									      <table id="partsDatagrid"></table>
									</div>
									<div data-options="title:'项目工时',border:'false'">
									      <table id="itemDatagrid"></table>
									</div>
							   </div>
			              </div>
		              </div>
		         </div>
		    </div>
		</div>
	</div>
  </body>
</html>