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
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellDbProject/sellDbProject.js"></script>
	<script type="text/javascript">
		var mytitle = "";
	    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";

			var sgsm_d2;
			function addCarSel()
			{
			 sgsm_d2 = $('<div/>');
			 sgsm_d2.dialog({
				title: '销售单选择',   
			    width: 650,   
			    height: 420,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/sellDbProject/carSellInfoSelect.jsp',
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
	$('#dbPro').datagrid({
		url : 'sellDbProjectAction!findSellDb.action?dbProjectCode=<%=accountTypeId%>',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		fitColumns : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : '',
		loadMsg : "数据加载中，请稍后……",
		columns : [[ {
			field : 'sellid',
			title : '编号',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'sellCode',
			title : '车辆销售单号',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'projectId',
			title : '代办项目',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'projectName',
			title : '代办项目名称',
			width : 100,
			sortable : true
		},{
			field : 'projectCode',
			title : '项目代码',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'projectAmount',
			title : '代办费用',
			width : 100,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
				required : true,
				missingMessage : "代办费用为必填项!",
				min:0,  
				precision:2,
				validType:'maxLength[11]'
			}
			
		 }	
		},{
			field : 'projectMomay',
			title : '成本金额',
			width : 100,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
				required : true,
				min:0, 
				precision:2,
				missingMessage : "成本金额为必填项!",
				validType:'maxLength[11]'
			}
		 }	
		},{
			field : 'dbProjectPerson',
			title : '经办人',
			width : 100,
			sortable : true,
			hidden:true
		}
		,{
			field : 'dbProjectDate',
			title : '代办日期',
			width : 100,
			sortable : true,
			hidden:true
		}
		,{
			field : 'dbProjectCode',
			title : '代办单号',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'dbExamin',
			title : '审核状态',
			width : 100,
			sortable : true,
			hidden:true
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
       <div align="left"> 
		<font face="Agency FB"><a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-add" plain="true" id="_add" onclick="addSellPro();">新增</a>  
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" id="_update" onclick="updateSellPro();">修改</a>  
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-remove" plain="true" id="_remove" onclick="removeSellPro()">删除</a>  
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain="true" id="_search" onclick="querySellPro();">查询</a>  
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" id="_clear" onclick="clearSearchCondition();">清空</a>  
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-examine" plain="true" id="_examine" onclick="examine_();">审核</a>  
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-examine" plain="true" id="sellAcount" onclick="sellAcount();">转结算</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true"  id="_print" onclick="doThisPoint();">打印</a>  
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" id="_export" onclick="doThisExport();">导出</a>  
		<span id="saveOrCancelBtn"></span>
		</font></div>
		
    </div>
    <%
               }
     %>
		
  <div region="center"  split="false" border="false">
             <div id="tt" class="easyui-tabs" fit="true" border="false">  
               <%
               if(accountTypeId==null||accountTypeId.length()==0){
               %>
               	 <div title="销售单汇总" style="display:block;"  fit="true">
                       <div id="cc" class="easyui-layout" fit="true" border="false">
						    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
					 <form id="sellDbQueryForm">
                       <table>   
                       		    <tr>
								<td>销售日期：</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xs_Car_Sel_Data2\',{d:-1})}'})" name="xs_Car_Sel_Data" id="xs_Car_Sel_Data" style="width: 110px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'xs_Car_Sel_Data\',{d:1})}'})" name="xs_Car_Sel_Data2" id="xs_Car_Sel_Data2" style="width: 110px;"/></td>
								<td>客户名称：</td>
								<td><input type="text" name="xs_Custom_Name"/></td>
								<td>业务员：</td>
								<td colspan="2">
									<input name="stf_Name" 
									class="easyui-combobox"	data-options="
									url : 'basStuffClassAction!findSellOperationPerson.action',
									valueField:'id',  
									textField:'text',
									multiple:false  "/>
								</td>
								<td>VIN编号：</td>
								<td ><input type="text" name="xs_Car_Vin_Number"/></td>
								<td>OCN码：</td>
								<td><input name="xs_Car_Ocn"/></td>
							</tr>
							<tr>
								<td><div>代办日期：</div></td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'dbProjectDate2\',{d:-1})}'})" name="queryProjectDate" id="dbProjectDate" style="width: 110px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'dbProjectDate\',{d:1})}'})" name="queryProjectDate2" id="dbProjectDate2" style="width: 110px;"/></td>
								<td>代办单号：</td>
								<td><input name="dbProjectCode"/></td>
								<td>审核情况：</td>
								<td>
									<input  name="dbExamin"
										class="easyui-combobox"
										data-options="
										url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ADUIT %>',
										multiple:false,
										valueField:'id',  
										textField:'text'
										"
										/>	
								</td>
							</tr>       
                       </table>                                                      
				     </form>
							</div>
						    <div id="sellInfo_div_id" region="center" style="background:#eee;" border="false">
						        <table id="sellInfo"></table>
						    </div>
	                </div>
               </div>  
               <%
               }
                %>
		    <div title="代办项目" style="display:block;" closable="false"  fit="true">  
		        <div id="tt" class="easyui-layout" fit="true"> 
		          <%
               if(accountTypeId==null||accountTypeId.length()==0){
               %>
		        	<div region="north" title="入库单汇总" split="false" style="overflow: hidden;background:#eee;height:110px;" border="false">  
				     <form id="sellDbPro" method="post" style="margin-top: 10px">		 
					     	<input type="hidden" name="isdb_project"/>
					     	<input type="hidden" name="dbExamin"/>
					     	<input type="hidden" name="dbProjectPerson"/>
					     	<input type="hidden" name="dbProjectReckoning"/>
					        <table >
						      <tr>
						      	  <td style="width:50px">销售单:</td>
						      	 <td><input name="sellCode" id="sellCode" readonly="readonly" style="background-color: rgb(192, 216, 216); width: 150px;" class="easyui-validatebox" maxlength="11" data-options="required:true,missingMessage:'销售单号为必填项' " onkeypress=" if(event.keyCode==13) { addCarSel(); return false;}">   
									 <img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" id="carSellImg">
									 <input type="hidden" id="xs_Car_Sel_Id" name="xs_Car_Sel_Id" >
								  </td>
							      <td style="width:60px">代办编号:</td>
							      <td ><input type="text" id="dbProjectCode" name="dbProjectCode" style="width: 150px; background-color: rgb(192, 216, 216);" readonly="readonly"></td>
							      <td style="width:50px">经办人:</td>
							      <td ><input type="text" id="dbProjectPerson" name="dbPersonName" value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName() %>"style="width: 130px; background-color: rgb(192, 216, 216);" readonly="readonly"></td>
							      <td style="width:60px" >代办日期:</td>
							      <td ><input type="text" id="sellDbProjectDate" style="width: 130px;" name="dbProjectDate" class="Wdate" onfocus="WdatePicker({});"class="easyui-validatebox" data-options="required:true,missingMessage:'代办日期为必填项'" ></td>
						      </tr>
						      <tr>
						      	   <td style="width:75px" >收费部门及代办项目备注:</td>
							      <td colspan="3" style="text-align: left;"><textarea id="dbProjectRemark" name="dbProjectRemark" style="width: 380px; height: 30px;resize:none;" maxlength="50"></textarea></td>	
						      </tr>
						</table>
				     </form>
				    </div>  
				    <%
               }
                %> 
				    <div region="south" split="false" style="overflow: hidden;background:#eee;height:30px;" border="false">
				        <%
               if(accountTypeId==null||accountTypeId.length()==0){
               %>
						    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" id="addForeordain" plain="true" onclick="add_Foreordain()">代办项目增加</a>&nbsp;&nbsp; 
							<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconcls="icon-remove" id="deleteForeordain" onclick="delete_Foreordain()">代办项目删除</a>&nbsp;&nbsp;	
								    <%
               }
                %> 		 
					    </div>
					    <div id="dbPro_div_id" region="center" style="background:#eee;" border="false">
					         <table id="dbPro" >
					         </table>
					    </div>  
					</div> 
			 </div>  
			 </div> 
	</div>
</div>
  </body>
</html>
