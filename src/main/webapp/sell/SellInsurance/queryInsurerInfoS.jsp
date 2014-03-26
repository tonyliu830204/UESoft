<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>银行信息</title>
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
	    var insurerName=$('#dName2').val();
	          if(insurerName!=''){ 
		          $('#dName2').val(insurerName);          
		           $.ajax({
						type : 'post',
						url : 'insurerInfoAction!getPageLeva.action',
						data : "insurerId="+$('#did2').val()+"&insurerName="+insurerName,
						dataType : 'json',
						success : function(r){
					              queryInfo();
					 	               }
					        });
					       }
					$('#did2').val('');    
					$('#dName2').val('');      
					     
	}
		$(function(){
			$('#insurer').datagrid({
					url:'${pageContext.request.contextPath}/insurerInfoAction!getPageLeva.action',
					fit:true,
					idField : 'insurerId',
					pagination : true,
					sortOrder:'asc',
				    sortName:'insurerId',
					singleSelect : true,
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编号',
							field : 'insurerId',
							hidden:true
					    }, {
							title : '编号',
							field : 'insurerCode',
							width : 130
					    }, {
							title : '名称',
							field : 'insurerName',
							width : 190
						}, {
							title : '地址',
							field : 'insurerAddress',
							width : 100
						}, {
							title : '联系电话',
							field : 'insurerPhone',
							width : 110
						}, {
							title : '联系人',
							field : 'insurerPerson',
							width : 90
						},{
							title : '商业险返点',
							field : 'insurerBusinessinsurer',
							width : 90
						}, {
							title : '交强险返点',
							field : 'insurerStronginsurer',
							width : 90
						}
				      ]],
			      	 onDblClickRow:function(rowIndex, rowData){     
						$('#dName2').val(rowData.insurerName);
						$('#did2').val(rowData.insurerId);
						$('#dcode').val(rowData.insurerCode);
						$('#buysnessCost').val(rowData.insurerBusinessinsurer);
						$('#inCost').val(rowData.insurerStronginsurer);
						sgsm_d2.dialog('close');
              		 	}
				});
		});
	var queryInfor= function (){
		$('#insurer').datagrid('unselectAll');
		$('#insurer').datagrid('load', serializeObject($('#aa')) );
}
function banksSearch(){
	   	$('#aa').find('input').val('');
		$('#aa').find('select').val('');
		$('#insurer').datagrid('unselectAll');
		$('#insurer').datagrid('load', serializeObject($('#aaa')));
	   }
	</script>
		<div id="cc" class="easyui-layout" fit="true" border="false">
				<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 75px;" border="false">
				<form id="aa" name="aa" method="post" fit="true"> 
					<fieldset> 
						<table> 
							<tr> 
							<td> 
					               银行名称: 
					        </td> 
					        <td> 
					        <input type="text" id="dN" name="insurerName" style="background-color: rgb(192, 216, 216);width: 200px" onkeyup="queryInfor()"> 
					</td> 
					<td> 
					<a id="_clear" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" onclick="banksSearch();">清空</a> 
								</td> 
					</tr> 
					</table> 
					</fieldset> 
				</form> 
			</div>
			<div region="center" style="background: #eee;" border="false">
				<table id="insurer"></table>
			</div>
		</div>
	</body>
</html>
