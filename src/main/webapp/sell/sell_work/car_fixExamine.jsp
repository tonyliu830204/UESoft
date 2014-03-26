<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆加装审核</title>
	<script type="text/javascript">
	$(function (){
		//车辆加装汇总
		$('#datagrid_car_fix').datagrid({
			url : 'carFixAction!getCarFixInforExamine.action',
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
			},{
				field : 'examineName',
				title : '审核状态',
				width : 120,
				sortable : true
			}
			]],
			onDblClickRow : function(rowIndex, rowData){
			    $('#tt').tabs('select',1);
			    $('#install_id').val(rowData.install_id);
			    $('#install_code').val(rowData.install_code);
			    $('#xs_car_licensePlate').val(rowData.xs_car_licensePlate);
			    $('#xs_car_vin_number').val(rowData.xs_car_vin_number);
			    $('#xs_car_ocn').val(rowData.xs_car_ocn);
			    $('#sun_money').val(rowData.sun_money);
			    $('#finish_state').val(rowData.finish_state);
			    $('#finish_stateName').val(rowData.finish_stateName);
			    $('#examine').val(rowData.examine);
			    $('#examineName').val(rowData.examineName);

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
					$('#spo_searchBtn').linkbutton('enable');
					$('#spo_clearBtn').linkbutton('enable');
				}else{
					$('#spo_searchBtn').linkbutton('disable');	
					$('#spo_clearBtn').linkbutton('disable');	
				}
		    }   
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
		
        //--审核
		$('#spo_examineBtn').bind("click", function(){
			var coudatagrid = $('#datagrid_car_fix');
			var rows = coudatagrid.datagrid('getSelections');
			if(rows){
				if(rows[0].finish_stateKey == "<%=Contstants.FINISHWORK.YIFINISH %>"){
					$.ajax({ 
						type : "POST",
						url : "carFixAction!examineCarFix.action",
						data : 'install_id='+rows[0].install_id+"&examineKey=<%=Contstants.ADUIT.YISHENHE %>",
						dataType : "json",
						success:function(r){
							alertMsg(r.msg,'info');
				        	if(r.success){
				        		coudatagrid.datagrid('load', {total:0,rows:[]});
				        		coudatagrid.datagrid('load', serializeObject($('#form_car_fix_id')));
				        	}
			            }
			        });
				}else{
					alertMsg("加装还未完成",'info');
				}
			}
		});
	});
	</script>
  </head>
  <body>
    <div class="easyui-layout" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:40px;" border="false">
		     <a href="javascript:void(0);" id="spo_searchBtn" class="easyui-linkbutton" plain="true" iconCls="icon-search" >查询</a>
		     <a href="javascript:void(0);" id="spo_clearBtn" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" >清空</a>
			 <a href="javascript:void(0);" id="spo_examineBtn" class="easyui-linkbutton" plain="true" iconCls="icon-examine" >审核</a>
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
									 	  <td><input id="car_vin_number" name="xs_car_vin_number" type="text" class="easyui-validatebox" style="width:180px;"/></td>
									 	  <td>OCN号:</td>
									 	  <td><input id="car_ocn" name="xs_car_ocn" type="text" class="easyui-validatebox" style="width:180px;"/></td>
									 	  <td>审核状态:</td>
									 	  <td><input name="examine" type="text" class="easyui-combobox" style="width:180px;"
									 	        data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.ADUIT.ADUIT%>',
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
		         <div title="加装配件明细" style="display:block;"  fit="true">
		             <div id="dd" class="easyui-layout" fit="true" border="false">
			             <div data-options="region:'north',title:'明细'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
			                   <form id="form_car_fix_detail">
								 <table>
								 	 <TR>
									 	  <td>加装单号:</td>
									 	  <td><input id="install_id" name="install_id" type="hidden"><input id="install_code" name="install_code" type="text" class="easyui-validatebox" style="width:180px;" readonly="readonly" /></td>
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
					           <div id="detailsTabs" class="easyui-tabs" data-options="fit:true,border:false">
									<div data-options="title:'加装配件',border:'false',href:'${pageContext.request.contextPath}/sell/sell_work/details/parts.jsp'"></div>
									<div data-options="title:'项目工时',border:'false',href:'${pageContext.request.contextPath}/sell/sell_work/details/item.jsp'"></div>
							   </div>
			              </div>
		              </div>
		         </div>
		    </div>
		</div>
	</div>
  </body>
</html>
