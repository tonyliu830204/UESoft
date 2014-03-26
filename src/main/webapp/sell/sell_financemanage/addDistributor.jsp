<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>分销商</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body class="easyui-layout" >
	<script type="text/javascript">
	//判断页面初始化加载是否完成
    function   LoadOk(){
    	if(document.readyState   =="complete") {
  			 initFrame();
 		}else{
   		setTimeout("LoadOk()",200);
 		}
	}
   setTimeout("LoadOk()",200);
   
   //判断页面初始化加载完成    执行
	function   initFrame(){
	    var xsDistributorName=$('#dName').val();
	          if(xsDistributorName!=''){ 
		          $('#dName2').val(xsDistributorName);          
		           $.ajax({
						type : 'post',
						url : 'distributorInfoAction!getDistributorInfo.action',
						data : "distributorId="+$('#did').val()+"&distributorName="+xsDistributorName,
						dataType : 'json',
						success : function(r){
					              queryCarModel();
					 	               }
					        });
					       }
					$('#did').val('');    
					$('#dName').val('');      
					     
	}
		$(function(){
			$('#distributorId').datagrid({
					url:'${pageContext.request.contextPath}/distributorInfoAction!getDistributorInfo.action',
					fit:true,
					idField : 'distributorId',
					pagination : true,
					sortOrder:'asc',
				    sortName:'distributorId',
					singleSelect : true,
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编号',
							field : 'distributorId',
							width : 50,
							sortable:true
					    }, {
							title : '分销商名称',
							field : 'distributorName',
							width : 220,
							sortable:true
						}, {
							title : '电话',
							field : 'distributorTelephone',
							width : 90,
							sortable:true
						}, {
							title : '地址',
							field : 'distributorAddr',
							width : 180,
							sortable:true
						},{
							title : '联系人',
							field : 'distributorPerson',
							width : 100,
							sortable:true
						}
				      ]],
			      	 onDblClickRow:function(rowIndex, rowData){     
						$('#dName').val(rowData.distributorName);
						$('#did').val(rowData.distributorId);
						sgsm_d2.dialog('close');
              		 	}
				});
		});
	var queryCarModel = function (){
		$('#distributorId').datagrid('unselectAll');
		$('#distributorId').datagrid('load', serializeObject($('#aa')) );
}
function clearSearchgg() {
	 	$('#aa').find('input').val('');
		$('#aa').find('select').val('');
		$('#distributorId').datagrid('unselectAll');
		$('#distributorId').datagrid('load', serializeObject($('#aa')));
		
	}
	</script>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 53px;" border="false">
				<form id="aa" name="aa" method="post" fit="true">
					名称:<input type="text" id="dName" name="distributorName" style="background-color: #c0d8d8;width: 200px" onkeyup="queryCarModel()" />
					
									&nbsp;&nbsp;<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton"
										iconCls="icon-cancel" plain="true"  onclick="clearSearchgg();">清空</a>
								
					
				</form>
			</div>
			<div region="center" style="background: #eee;" border="false">
				<table id="distributorId"></table>
			</div>
		</div>
	</body>
</html>
