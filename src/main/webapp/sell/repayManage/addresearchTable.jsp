<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>回访调查表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body class="easyui-layout" >
	<script type="text/javascript">
	//保存新添加信息
function saveInfo(){
		   var rows=$("#re").datagrid('getRows');
		  endEdit($("#re"));
		  var data=$("#re").datagrid('getData');
		  var effectRow = new Object();
		  effectRow['addResearchList'] =  JSON.stringify(rows);
		  $.ajax({
		   type: 'post',
		   dataType: 'json',
		   url:'${pageContext.request.contextPath}/sellCoverAction!savaResearchInfo.action',   
		   data:effectRow,
		   success: function(r){
			 if(r.success){
				 sgsm_d2.dialog('close');
			 }else{
				 alertMsg(r);
			 }
		   },
		   error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		   
		});
       
	}
	
	function cancel(){
	 sgsm_d2.dialog('close');
	}
	


   		$(function(){
			$('#re').datagrid({
					url:'${pageContext.request.contextPath}/sellCoverAction!getResearch.action',
					queryParams:{consultId:<%=request.getParameter("id")%>},
					fit:true,
					idField : 'projectId',
					sortOrder:'asc',
				    sortName:'projectId',
					singleSelect : true,
					
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ 
					{
							title : '调查编号',
							field : 'contentId',
							width : 40,
							sortable:true,
							hidden:true
					    },{
							title : '编号',
							field : 'projectId',
							width : 40,
							sortable:true,
							hidden:true
					    }, {
							title : '项目名称',
							field : 'projectName',
							width : 340,
							sortable:true
						}, {
							title : '评价',
							field : 'projectEvaluate',
							width : 65,
							sortable : true,
							formatter : function (value,row,index){
							return row.projectEvaluateN;
					    	 },
							editor : {
	  							type : 'combobox',
	  							options : {
	  								url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.PROJECTEVALUATE%>',
	  								mode : 'remote',
	  								valueField:'id',  
	  							    textField:'text',
								}
							}
						}, {
							title : '评价分数',
							field : 'projectScore',
							width : 65,
							sortable : true,
							
							
							editor : {
  							type : 'combobox',
  							options : {
	  							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.PROJECTSCORE%>',
	  								mode : 'remote',
	  								valueField:'id',  
	  							    textField:'text',
								}
						    }	
						},{
							title : '备注',
							field : 'remark',
							width : 100,
							sortable : true,
							editor : {
							type : 'text',
							options : {
							validType:'length[1,20]'
							}	
						 }	
						}
				      ]],
              		 onDblClickRow : function(rowIndex, rowData) {
	              		//var rows=$('#re').datagrid('getRows');
	              		//for(var i=0;i<rows.length;i++){
	              			$('#re').datagrid('beginEdit', rowIndex);
	              		//}
              		  }
				});
		});
	
	</script>
		<div id="cc" class="easyui-layout" fit="true" border="false">
	
			<div region="north" split="false"
				style="height: 30px; background: #eee;" border="false">
				
		<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveInfo();">保存</a>
		<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>
			</div>			
			<div region="center" style="background: #eee;" border="false">
				<table id="re"></table>	
			</div>
			
		</div>
	</body>
</html>