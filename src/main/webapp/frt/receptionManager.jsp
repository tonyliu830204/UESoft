<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'receptionManager.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	$(function(){
		//前台接车datagrid
		$receptionTableDatagrid = $('#receptionTable');
		$receptionTableDatagrid.datagrid({
			url : 'frtReceptionAction!loadFrtReception.action',
			singleSelect : true,
			pagination : true,
			fit : true,
			sortOrder:'desc',
		    sortName:'interDate',
			rownumbers : true,
			fitColumns : true,
			loadMsg : "数据加载中，请稍后……",
			idField:'receptionId',
			columns : [[
				{field:'receptionId',title:'工单号',width:100,sortable:true},
				{field:'preclrId',title:'结算单号',width:100,sortable:true},
				{field:'carLicense',title:'车牌照',width:100,sortable:true},
				{field:'carVin',title:'VIN号',width:100,hidden:true},
				{field:'customName',title:'客户名称',width:100,sortable:true},
				{field:'customTel1',title:'客户电话',width:100,sortable:true},
				{field:'reptName',title:'维修类型',width:100,sortable:true},
				{field:'receptorName',title:'接待员',width:100,sortable:true},
				{field:'interDate',title:'接车日期',width:100,sortable:true},
				{field:'preclrTime',title:'结算日期',width:100,sortable:true},
				{field:'receptionStatusName',title:'工单状态',width:100,sortable:true},
				{field:'receptionRepPer',title:'保险送修人',width:100,sortable:true},
				{field:'carId',title:'车编号',width:100,sortable:true,hidden:true}
	         ]],
	         onClickRow:function(rowIndex, rowData){
			     if(rowData.receptionStatusName=='未派工'){
			    	$('#so_workshopBtn').linkbutton('disable');
			    	$('#so_preclrBtn').linkbutton('disable');
				 }else if(rowData.receptionStatusName!='未派工'&&rowData.receptionStatusName!='完工'&&rowData.receptionStatusName!='已转结算'&&rowData.receptionStatusName!='已结算'
		            	&&rowData.receptionStatusName!='洗车'&&rowData.receptionStatusName!='等待交车' && rowData.receptionStatusName!='收款'){
			    	$('#so_workshopBtn').linkbutton('enable');
			    	$('#so_preclrBtn').linkbutton('disable');
			     }else if(rowData.receptionStatusName=='完工'||rowData.receptionStatusName=='已结算'||rowData.receptionStatusName=='洗车'||rowData.receptionStatusName=='等待交车'||rowData.receptionStatusName=='已转结算'){
			    	$('#so_workshopBtn').linkbutton('disable');
			    	$('#so_preclrBtn').linkbutton('enable');
				 }else{
			    	$('#so_workshopBtn').linkbutton('enable');
			    	$('#so_preclrBtn').linkbutton('enable');
			     }
	         } 
		});
    })

	//关闭子窗体刷新父窗体
	function win_close(){
		_query();
	}
	  
    //前台接车操作窗体打开
    function receptionWindows(){
        var selected=$receptionTableDatagrid.datagrid('getSelected');
        if(selected!=null&&selected!=''){
            if(selected.receptionStatusName=='未派工'){
            	window.open('frt/frtReception_1.jsp?receptionId='+selected.receptionId+'&carId='+selected.carId,'demo',　'toolbar=no,scrollbars=yes,resizable=no,top=200,left=220,width=1400,height=800');
            }else{
            	$.messager.confirm('优亿软件提示','该工单为【'+selected.receptionStatusName+'】状态，不能再次打开， 请确认是否新增接车单！',function(b){
					if(b){
						window.open('frt/frtReception_2.jsp','demo',　'toolbar=no,scrollbars=yes,resizable=no,top=200,left=220,width=1400,height=800');
					}
				});
            }
        	
        }else{
        	window.open('frt/frtReception_2.jsp','demo',　'toolbar=no,scrollbars=yes,resizable=no,top=200,left=220,width=1400,height=800');
        }
	}
    
	//车间管理操作窗体打开
    function frtWorkshopManagerOpenWindows(){
        var selected=$receptionTableDatagrid.datagrid('getSelected');
        if(selected!=null&&selected!=''){
            if(selected.receptionStatusName!='未派工'&&selected.receptionStatusName!='完工'&&selected.receptionStatusName!='已转结算'&&selected.receptionStatusName!='已结算'
            	&&selected.receptionStatusName!='洗车'&&selected.receptionStatusName!='等待交车' && selected.receptionStatusName!='收款'){
            	window.open('frt/frtWorkshopManager_1.jsp?receptionId='+selected.receptionId+'&carId='+selected.carId+'&flage=true','demo',　'toolbar=no,scrollbars=yes,resizable=no,top=200,left=220,width=1400,height=800');
            }else
            	alertMsg('对不起，该工单为【'+selected.receptionStatusName+'】状态，不能进行车间操作！', 'warning');
        }else{
        	alertMsg('对不起，请选择要车间管理的工单记录！', 'warning');
        }
	}

    //交车结算操作窗体打开事件
    function receptionPreclrOpenWindow(){
    	var selected=$receptionTableDatagrid.datagrid('getSelected');
        if(selected!=null&&selected!=''){
            if(selected.receptionStatusName=='完工'||selected.receptionStatusName=='已结算'||selected.receptionStatusName=='洗车'||selected.receptionStatusName=='等待交车'||selected.receptionStatusName=='已转结算'){
            	window.open('frt/frtPreClearing_1.jsp?receptionId='+selected.receptionId+'&preclrId='+selected.preclrId,'demo',　'toolbar=no,scrollbars=yes,resizable=no,top=200,left=220,width=1400,height=800');
            }else
            	alertMsg('对不起，该工单为【'+selected.receptionStatusName+'】状态，不能进行交车结算操作！', 'warning');
        }else{
        	alertMsg('对不起，请选择要交车结算的工单记录！', 'warning');
        }
    }

    function _query(){
    	$('#receptionTable').datagrid('load', serializeObject($('#receptionMainForm')));
    	$('#receptionTable').datagrid('clearSelections');
    }
    function _except(){
		showEditDialog("receptionTable",null,"receptionInfoDiv","开始导出","导出配置",0,_callbackExcept);
	}
    function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"前台接车单"+getSystemTime());
	}
    function _clear(){
    	$('#receptionMainForm').form('clear');	
    	$('#so_workshopBtn').linkbutton('enable');
    	$('#so_preclrBtn').linkbutton('enable');
    	_query();
    }
	</script>
  </head>
  <body>
      <div id="cc" class="easyui-layout" fit="true" border="false">  
		        <div region="north"   border="false" split="false" style="height:30px;background: #eee;">
			       <privilege:enable code="PROSCENIUMMANAGE_SEARCH">
			           <a href="javascript:void(0);" id="spo_searchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_query();">查询</a>
				   </privilege:enable>
				   <privilege:enable code="PROSCENIUMMANAGE_CLEAR">
					   <a href="javascript:void(0);" id="spo_clearBtn" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
				   </privilege:enable>
				   <privilege:enable code="PROSCENIUMMANAGE_RECEIVE">
			           <a href="javascript:void(0);" id="so_receptionBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="receptionWindows();">前台接车</a>
				   </privilege:enable>
				   <privilege:enable code="PROSCENIUMMANAGE_STORE">
			           <a href="javascript:void(0);" id="so_workshopBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="frtWorkshopManagerOpenWindows();">车间管理</a>
				   </privilege:enable>
				   <privilege:enable code="PROSCENIUMMANAGE_BALANCE">
			           <a href="javascript:void(0);" id="so_preclrBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="receptionPreclrOpenWindow();">交车结算</a>
				   </privilege:enable>
				   <privilege:enable code="STAGECAR_EXPORT">
	      	        <a href="javascript:void(0);" class="easyui-linkbutton" id="export" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
	            </privilege:enable>
		       </div>
               <div region="center" style="background:#eee;" split="false" border="false">
                   <div id="cc" class="easyui-layout" fit="true" border="false">
					    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
						  <form id="receptionMainForm">
						     <table>
						       <tr>
						         <td>接车日期:</td>
						         <td><input id="interDateBegin" name="interDateBegin" readonly="readonly"  style="width:140px;"  class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',errDealMode:1,maxDate:'#F{$dp.$D(\'interDateEnd\',{d:-1})}'})"/></td>
						         <td>至</td>
						         <td><input id="interDateEnd" name="interDateEnd" readonly="readonly" style="width:140px;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,minDate:'#F{$dp.$D(\'interDateBegin\',{d:0})}'})"/></td>
						         <td>工单号:</td>
						         <td><input id="receptionId" name="receptionId" /></td>
						         <td>车牌照:</td>
						         <td><input id="carLicense" name="carLicense" class="easyui-combobox" readonly="readonly"
					        			 data-options="	url : 'frtOptionsAction!findAllCarLicense.action',
										 disabled : false,
										 valueField : 'id',
										 textField : 'text', 
										 mode : 'remote'"/></td>
						         <td>客户名称:</td>
						         <td><input id="customName" name="customName" /></td>
						         <td>客户电话:</td>
						         <td><input id="customTel1" name="customTel1" /></td>
						         <td>维修类型:</td>
						         <td><input id="reptName" name="reptName" class="easyui-combobox"  readonly="readonly"
										style="width:110px;"
										data-options="
										disabled:false,
										url : 'frtOptionsAction!findAllReptype.action',
										valueField:'id',  
										textField:'text' "/></td>
						       </tr>
						       <tr>
						         <td>结算日期:</td>
						         <td><input id="frt_preclrTimeBegin" name="preclrTimeBegin" readonly="readonly"  style="width:140px;"  class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',errDealMode:1,maxDate:'#F{$dp.$D(\'frt_preclrTimeEnd\',{d:-1})}'})"/></td>
						         <td>至</td>
						         <td><input id="frt_preclrTimeEnd" name="preclrTimeEnd"  readonly="readonly" style="width:140px;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,minDate:'#F{$dp.$D(\'frt_preclrTimeBegin\',{d:0})}'})"/></td>
						         <td>结算单号:</td>
						         <td><input id="preclrId" name="preclrId" /></td>
						         <td>接待员:</td>
						         <td><input id="receptorName" name="receptorName" /></td>
						         <td>工单状态:</td>
						         <td><input id="receptionStatusName" name="receptionStatusName"class="easyui-combobox" readonly="readonly"
					        			 data-options="	url : 'frtOptionsAction!findReceptionState.action',
										 disabled : false,
										 valueField : 'id',
										 textField : 'text', 
										 mode : 'remote'"/></td>
						         <td>保险送修人:</td>
						         <td><input id="receptionRepPer" name="receptionRepPer" /></td>
						       </tr>
						     </table>
						  </form>
						</div>
					    <div id="receptionInfoDiv" title="工单信息汇总"  region="center" style="background:#eee;" border="false">
					        <table id="receptionTable"></table>
					    </div>
	              </div>
               </div>
       </div>
  </body>
</html>
