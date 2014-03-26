<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'sellCarList.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
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
	    var sellId=$('#xs_Car_Sel_Id').val();
	          if(sellId!=''){ 
		          $('#xs_Car_Sel_Id').val(sellId);          
		           $.ajax({
						type : 'post',
						url : 'sellCoverAction!getInSellList.action',
						data : "xsCarSelId="+sellId,
						dataType : 'json',
						success : function(r){
					              queryInfo();
					 	               }
					        });
					       }
				 
					$('#xs_Car_Sel_Id').val('');      
					     
	}
		$(function(){
			$('#allo').datagrid({
					url:'${pageContext.request.contextPath}/sellCoverAction!getInSellList.action',
					fit:true,
					idField : 'xsCarSelId',
					pagination : true,
					sortOrder:'asc',
				    sortName:'xsCarSelId',
					singleSelect : true,
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [   {
							field : 'xsCarSelId',
							title : '销售编号',
							width : 65,
							sortable : true,
						}, {
							field : 'sell_code',
							title : '销售单号',
							width : 110,
							sortable : true
						},
						{
							field : 'carBrandName',
							title : '车辆品牌',
							width : 80,
							sortable : true
						},
						{
							field : 'carBrand',
							title : '车辆品牌id',
							hidden:true,
							width : 70,
							sortable : true
						},
						{
							field : 'carModelName',
							title : '车辆类型',
							width : 150,
							sortable : true
						},{
							field : 'carModel',
							title : '车辆类型id',
							hidden:true,
							width : 80,
							sortable : true
						},
						{
							field : 'carColorName',
							title : '颜色',
							width : 70,
							sortable : true
						},
						 {
							field : 'carColor',
							title : '颜色id',
							hidden:true,
							width : 70,
							sortable : true
						},
						{
							field : 'vinCode',
							title : 'VIN号',
							width : 130,
							sortable : true
						},{	
							field : 'carMotorNumber',
							title : '发动机号',
							width : 90,
							sortable : true
							
					    },{	
							field : 'carLicensePlate',
							title : '车牌照',
							width : 70,
							sortable : true
							
					    },{
							field : 'xsCustomName',
							title : '客户名称',
							width : 80,
							sortable : true
						
						},
						{
							field : 'customId',
							title : '客户名称id',
							width : 80,
							hidden:true,
							sortable : true
						
						},{
						field : 'xsCustomTelephone',
						title : '手机',
						width : 80,
						sortable : true
					
					},
					{
						field : 'xsCustomZipcode',
						title : '邮编',
						width : 80,
						sortable : true
					
					},{
						field : 'xsCustomAddress',
						title : '家庭住址',
						width : 150,
						sortable : true
					 },{	
						field : 'xsCustomBirthday',
						title : '出生日期',
						width : 80,
						sortable : true
					
			   		 },{
						field : 'xsCustomCredentials',
						title : '身份证号',
						width : 120,
						sortable : true
				
					},{
						field : 'xsCustomApplicationN',
						title : '用途',
						width : 70,
						sortable : true
						
					},{
						field : 'xsCustomApplication',
						title : '用途',
						width : 60,
						hidden:true,
						sortable : true
							
					},{
						field : 'travelCourse',
						title : '行驶里程',
						width : 60,						
						sortable : true
							
					},
					{
						field : 'carOutputVolume',
						title : '车辆排量',
						width : 60,						
						sortable : true
							
					},
					{
						field : 'limitLoadNum',
						title : '座位',
						width : 60,						
						sortable : true
							
					}	
				      ]],
			      	 onDblClickRow:function(rowIndex, rowData){     
						$('#sellId').val(rowData.sell_code);
						$('#id1').val(rowData.xsCustomName);
						$('#id2').val(rowData.xsCustomAddress);
						$('#id3').val(rowData.xsCustomCredentials);
						$('#id4').val(rowData.xsCustomTelephone);
						$('#id5').val(rowData.carLicensePlate);
						$('#id6').val(rowData.carColorName);
						$('#id7').val(rowData.vinCode);
						$('#id8').val(rowData.carMotorNumber);
						$('#id9').val(rowData.xsCustomApplicationN);
						$('#id10').val(rowData.travelCourse);
						$('#id11').val(rowData.carOutputVolume);
						$('#id12').val(rowData.limitLoadNum);
						$('#id13').val(rowData.xsCustomZipcode)
						$('#xs_Car_Sel_Id').val(rowData.xsCarSelId);
						
						
						
						addDiv.dialog('close');
					
              		 	}
				});
		});
	var queryInfo = function (){
		$('#allo').datagrid('unselectAll');
		$('#allo').datagrid('load', serializeObject($('#aa')) );
}
 function cSearch(){
	   	$('#aa').find('input').val('');
		$('#aa').find('select').val('');
		$('#allo').datagrid('unselectAll');
		$('#allo').datagrid('load', serializeObject($('#aa')));
	   }
	</script>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 75px;" border="false">
				<form id="aa" name="aa" method="post" fit="true">
					<fieldset>
						<table>
						<tr>
						<td>
						VIN号:</td><td><input type="text" id="vinCode" name="vinCode" style="background-color: #c0d8d8;width: 130px" onkeyup="queryInfo()" /></td>
					
						<td>
						销售编号:</td><td><input type="text" id="xsCarSelId" name="xsCarSelId" style="background-color: #c0d8d8;width: 130px" onkeyup="queryInfo()" /></td>
						
						<td >
						<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-cancel" plain="true"  onclick="cSearch();">清空</a>
						</td>
						</tr>
						</table>
					</fieldset>
				</form>
			</div>
			<div region="center" style="background: #eee;" border="false">
				<table id="allo"></table>
			</div>
		</div>
	</body>
  
</html>
