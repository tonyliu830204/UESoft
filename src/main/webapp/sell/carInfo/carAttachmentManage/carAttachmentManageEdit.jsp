<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%@page import="com.syuesoft.model.BasUsers"%>

<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>

<html>
	<head>
		
		<title>车辆附件管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body class="easyui-layout" >		
	<script type="text/javascript">
	$(function(){

			$('#detaileList').datagrid({
				url : 'carInfoAction!getAttachmentDel.action?carId='+<%=request.getParameter("carId")%>,
				pagination : true,
				fit : true,
				rownumbers : true,
				singleSelect : true,
				fitColumns : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				idField : 'attachment_no',
				loadMsg : "数据加载中，请稍后……",
				columns : [[{
					field : 'carId',
					title : '车辆编号',
					width : 100,
					sortable : true,
					hidden:true
					
				},{
					field : 'attachment_no',
					title : '编号',
					width : 100,
					sortable : true,
					hidden:true
					
				},{
					field : 'count1',
					title : '点烟器',
					width : 60,
					sortable : true
					
				},{
					field : 'count2',
					title : '烟灰缸',
					width : 60,
					sortable : true
					
				},{
					field : 'operator_type',
					title : '附件类型',
					width : 80,
					sortable : true,
					hidden:true
					
				},{
					field : 'types',
					title : '附件类型',
					width : 85,
					sortable : true
					
				},{
					field : 'send_persion',
					title : '录/领/借/还件人',
					width : 85,
					sortable : true
					
				},{
					field : 'operator_date',
					title : '经办日期',
					width : 90,
					sortable : true
					
				},{
					field : 'person',
					title : '经办人',
					width : 120,
					sortable : true
					
				},{
					field : 'operator_persion',
					title : '经办人',
					width : 100,
					sortable : true,
					hidden:true
					
				},{
					field : 'remark',
					title : '备注',
					width : 110,
					sortable : true
				}
				]],onClickRow : function(rowIndex, rowData) { 
					if(otherFlag){
						$('#carAttachmentEdit').form('clear');  
						$('#carAttachmentEdit').form('load', rowData);
					}
		    	  }
				});
		});
			
</script>
	<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'随车附件管理明细'" style="overflow: hidden; padding: 1px; background: #eee; height:280px;" border="false">
				
				<table id="detaileList"></table>
			</div>
			<div region="center" style="background: #eee;" border="false" >
					
					<form id="carAttachmentEdit"  >
					<fieldset>
						<table>
							<tr>
								<td width="100px">
									点烟器：
								</td>
								<td><input id="count1" name="count1" style="width: 100px" value="0"/>(个)</td> 
								<td>
									烟灰缸：
								</td>
								<td><input id="count2" name="count2" style="width: 100px" value="0"/>(个)</td>
								<td>
									备注：
								</td>
								<td  rowspan="3"><textarea id="remark" name="remark" style="width: 130px;height: 85px;resize:none;" > </textarea></td> 
							</tr>
							<tr>
								<td>
									录/领/借/还件人：
								</td>
								<td><input id="send_persion" name="send_persion" style="width: 120px" class="easyui-validatebox" data-options="required:true"/></td> 
								<td width="80px">
									附件类型：
								</td >
								<td><input id="operator_type" name="operator_type" style="width: 120px" class="easyui-combobox"  
										    data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.ATTACHMENTTYPES.ATTACHMENTTYPE%>',
												required:true,
									    		valueField:'id',   
									    		textField:'text',
									    		mode : 'remote',
									    		validType:'isSelected[\'#operator_type\']',
												invalidMessage : '请从下拉框中选择类型'"/></td> 
							</tr>
								<tr>
								<td>
									经办人：
				 				</td>
								<td><input id="operator_persion" name="operator_persion" style="width: 120px" class="easyui-combobox"  
									value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId()%>"
												data-options="url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
													disabled:true,  
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													invalidMessage : '请从下拉框中选择经办人'"  />	</td> 
								<td>
									经办日期：
								</td>
								<td><input type="text" class="Wdate" style="width: 120px" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',,maxDate:'%y-%M-%d'})" 
												name="operator_date" id="operator_date_edit" /></td>
									<td><input type="hidden" id="carId" name="carId" style="width: 130px" value="<%=request.getParameter("carId")%>"/></td>
									<td><input type="hidden" id="attachment_no" name="attachment_no" style="width: 130px" /></td>
							</tr>
						</table>
						</fieldset>
				</form>
			</div>
	</div>
	</body>
	</html>