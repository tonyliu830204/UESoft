<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>销售代办项目</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellDecorateProject/sellDecorateProject.js"></script>
	  <script type="text/javascript">
		    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		    </script>
		<script type="text/javascript">
	       var  tbtitle;
			var sgsm_d2;
			function addCarSel()
			{
			 sgsm_d2 = $('<div/>');
			 sgsm_d2.dialog({
				title: '销售单选择',   
			    width: 650,   
			    height: 420,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/sellDecorateProject/querySellCarList.jsp?iszhProject=false',
			    modal: true,
			    onClose : function (){
			    	$(this).dialog('destroy');
			      }
			   });
			}
	</script>
	</head>
     <body>
     <%
		String accountTypeId=request.getParameter("accountTypeId");
		if(accountTypeId!=null){
	%>
	<script type="text/javascript" >
	$('#zhList').datagrid({
		url:'${pageContext.request.contextPath}/sellZhProjectAction!getSellZhlist.action?zhProjectCode=<%=accountTypeId%>',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		fitColumns : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'zhid',
		loadMsg : "数据加载中，请稍后……",
		columns : [[ {
			field : 'zhid',
			title : '编号',
			width : 50,
			sortable : true,
			
		},
		{
			field : 'zsprojectId',
			title : '项目编号',
			width : 50,
			sortable : true,
			hidden:true
		},
		{
			field : 'projectName',
			title : '项目名称',
			width : 100,
			sortable : true
		},
		{
			field : 'unitNum',
			title : '件数',
			width : 60,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
				min:0,
				validType:'length[1,12]' 
			}
		 }	
		},{
			field : 'projectCostamount',
			title : '成本价',
			width : 70,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
			min : 0.00,
			precision : 2,
			validType:'length[1,12]'  
			}
		 }	
		},{
			field : 'projectSellamount',
			title : '销售价',
			width : 70,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
			min : 0.00,
			precision : 2,
			validType:'length[1,12]'
			}
		 }	
		},
		{
			field : 'preferentialPrice',
			title : '优惠价',
			width : 70,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
			min : 0.00,
			precision : 2,
			validType:'length[1,12]'
			}
		 }	
		},
		{
			field : 'decorateAmount',
			title : '装潢成本',
			width :70,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
				disabled : true,
				precision : 2,
				min : 0.00
			}
		 }	
		},
		{
			field : 'decorateSell',
			title : '装潢销售',
			width : 70,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
				disabled : true,
				precision : 2,
				min : 0.00 
			}
		 }	
		} ,
		{
			field : 'zhRemark',
			title : '类型',
			width : 70,
			sortable : true,
			formatter : function (value,row,index){
			return row.remark;
	    	 },
			editor : {
					type : 'combobox',
					options : {
						url : 'baseTagAction!findBaseData.action?baseKey=zhRemark',
						mode : 'remote',
						valueField:'id',  
					    textField:'text',
				}
			}
		}
		]]
		});	
		
		</script>
		<%
			}
 		%>
       <div id="cc" class="easyui-layout" fit="true" border="false">  
       <%
               if(accountTypeId==null||accountTypeId.length()==0){
       %>
       <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="_add" onclick="addSellPro();">新增</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="_remove" onclick="removeSellPro();">删除</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="_update"  onclick="updateSellPro();">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="_search" onclick="queryReserve();">查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id="_clear" onclick="clearSearchCondition();">清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" id="_examine" onclick="examine_();">审核</a>
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true"  id="_print" onclick="_config();">打印</a>
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" id="_export" onclick="_except();">导出</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-examine" plain="true" id="sellAccount" onclick="sellAccount();">转结算</a> 
		<span id="saveOrCancelBtn"></span>
    </div>
   		<%
               }
        %>
  <div region="center"  split="false" border="false">
             <div id="tt" class="easyui-tabs" fit="true" border="false"> 
             <%
               if(accountTypeId==null||accountTypeId.length()==0){
               %> 
                <div title="装潢项目汇总" style="display:block;"  fit="true">
                       <div id="cc" class="easyui-layout" fit="true" border="false">
						    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:95px;" border="false">
					
					 <form id="queryForm">
					  <fieldset>
					
                      	<table style="text-align: right">
							<tr>
								<td width="75px">销售日期：</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xs_Car_Sel_Data2\',{d:-1})}'})" name="xs_Car_Sel_Data" id="xs_Car_Sel_Data" style="width: 110px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'xs_Car_Sel_Data\',{d:1})}'})" name="xs_Car_Sel_Data2" id="xs_Car_Sel_Data2" style="width: 110px;"/></td>
								<td>客户名称：</td>
								<td><input type="text" name="xs_Custom_Name"/></td>
								
							
								
								<td>VIN编号：</td>
								<td colspan="2"><input type="text" name="xs_Car_Vin_Number" style="width: 130px"/></td>
									
								
								
							</tr>
							<tr>
							<td width="75px">装潢日期：</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'zhProjectDate2\',{d:-1})}'})" name="zhProjectDate" id="zhProjectDate1" style="width: 110px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'zhProjectDate1\',{d:1})}'})" name="zhProjectDate2" id="zhProjectDate2" style="width: 110px;"/></td>
							<td>OCN码：</td>
								<td><input name="xs_Car_Ocn"/></td>
							<td>装潢单号：</td>
								<td><input type="text" name="zhProjectCode" style="width: 130px"/></td>
								
							</tr>
					</table>  
				</fieldset>	                                                   
			 </form>
				     
							</div>
						    <div region="center" id="zhLists" style="background:#eee;" border="false">
						        <table id="sellInfo"></table>
						    </div>
	                </div>
               </div>  
               	 <%
              		 }
               	 %>
		    <div title="装潢项目明细" style="display:block;" closable="false"  fit="true">  
		        <div id="tt" class="easyui-layout" fit="true">  
		         <%
               			if(accountTypeId==null||accountTypeId.length()==0){
               	%>
		        			<div region="north"  split="false"
								style="overflow: hidden; background: #eee; height: 70px;"
								border="false">
								<form id="StForm" method="post">
								 <fieldset>
									<table>
										<tr>
										<td>
												日期：
											</td>
											<td>
											<input type="text" class="Wdate"  readonly="readonly"  style="width: 150px"
												name="zhProjectDate" id="zhProjectDate"   />
												
												</td>
											  <td >销售单:</td>
										      	 <td><input id="sellCode" class="easyui-validatebox" name="sellCode" readonly="readonly" disabled="disabled" style="background-color: rgb(192, 216, 216); width: 150px;"   onkeypress=" if(event.keyCode==13) { addCarSel(); return false;}">   
													 <img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" id="carSellImg">
													 <input type="hidden" id="xs_Car_Sel_Id" name="xs_Car_Sel_Id"  >
												  </td>
												 <td>装潢单号:</td>
										      	 <td><input id="zhProjectCode" name="zhProjectCode" readonly="readonly" style=" width: 150px;" />   
													 
												  </td>
									
									
											
											</tr>
											<tr>
											<td>
												经办人：
											</td>
											<td>
											<input type="text" id="zhPerson" name="zhProjectPerson"   class="easyui-combobox"  value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
												 style="background-color:#c0d8d8;width: 150px"
												data-options="
													url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
													disabled:true,  
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#zhPerson\']',
													invalidMessage : '请从下拉框中选择经办人'"  />	
													</td>
												
										
											<td>
												备注：
											</td>
											<td colspan="3">
												<input type="text" id="zhProjecRemark" name="zhProjecRemark" class="easyui-validatebox" disabled="disabled"  data-options="validType:'multiple[\'characterDigit\',\'length[0,30]\']'" style="width:380px;"/>
												
											</td>
											
											
										</tr>
									</table>
									</fieldset>
								</form>
							</div>
					 	<%
             			   }
                		%>
				    <div region="south" split="false" style="overflow: hidden;background:#eee;height:30px;" border="false">
				       	<%
              				 if(accountTypeId==null||accountTypeId.length()==0){
                      	%>
						    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" id="addForeordain" plain="true" onclick="add_Foreordain()">装潢项目增加</a>&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-remove" id="deleteForeordain" onclick="delete_Foreordain()">装潢项目删除</a>&nbsp;&nbsp;			
					   	 <%
             				  }
               			 %>
					    </div>
					    <div region="center" id="zhDetils" style="background:#eee;" border="false">
					         <table id="zhList" >  </table>
					    </div>  
					</div> 
			 </div>  
			 </div> 
	</div>
</div>
  </body>
</html>
