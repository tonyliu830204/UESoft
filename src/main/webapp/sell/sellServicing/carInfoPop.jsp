<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆型号选择</title>
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
	    var modelName=$('#xsCarVinNumber').val();
	          if(modelName!=''){ 
		          $('#xsCarVinNumber').val(modelName);          
		           $.ajax({
						type : 'post',
						url : 'sellServicingAction!getPagerCar.action',
						data : "xsCarVinNumber="+$('#xsCarVinNumber').val(),
						dataType : 'json',
						success : function(r){
					              queryCarModel();
					 	               }
					        });
					       }
					$('#xsCarVinNumber').val('');       
					     
	}
		$(function(){
			$('#selectCarInfo').datagrid({
					url:'${pageContext.request.contextPath}/sellServicingAction!getPagerCar.action',
					fit:true,
					idField : 'modelId',
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'modelId',
					singleSelect : true,
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '入库日期',
							field : 'instorehouseDate',
							width : 50,
							sortable:true
					    }, {
							title : '车辆型号',
							field : 'xsCarModelName',
							width : 50,
							sortable:true
						}, {
							title : '车辆型号',
							field : 'xsCarModelId',
							hidden:true
						}, {
							title : '入库单明细',
							field : 'detailsId',
							hidden:true
						},{
							title : 'Vin编号',
							field : 'xsCarVinNumber',
							width : 50,
							sortable:true
						}
				      ]],
			      	 onDblClickRow:function(rowIndex, rowData){ 
			    		var today=new Date(rowData.instorehouseDate);
			    		$('#servicingDate').val(new Date(today.setDate(today.getDate()+15)).format("yyyy-MM-dd"));  
			    		var today1=new Date($('#servicingDate').val());
			    		$('#servicingNextdate').val(new Date(today1.setDate(today1.getDate()+15)).format("yyyy-MM-dd"));  
						$('#xsCarModelName').val(rowData.xsCarModelName);
						$('#xsCarModelId').val(rowData.xsCarModelId);
						$('#detailsId').val(rowData.detailsId);
						$('#xsCarVinNumber').val(rowData.xsCarVinNumber);
						sgsm_d2.dialog('close');
              		 	}
				});
		});
	var queryCarModel = function (){
		$('#selectCarInfo').datagrid('unselectAll');
		$('#selectCarInfo').datagrid('load', serializeObject($('#aa')) );
}
	function clearSearchCondition(){
		$('#aa').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'sellServicingAction!getPagerCar.action',
			data:$('#aa').serialize(),
			dataType : 'json',
			success : function(r){
				$('#selectCarInfo').datagrid('load',r);
			}
	    });
		
	}	
	</script>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 53px;" border="false">
				<form id="aa" name="aa" method="post" fit="true">
					VIN号:<input type="text" id="xsCarVinNumber" name="xsCarVinNumber" style="background-color: #c0d8d8;" onkeyup="queryCarModel()" />
					&nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSearchCondition();" plain="true">清空</a>
				</form>
			</div>
			<div region="center" style="background: #eee;" border="false">
				<table id="selectCarInfo"></table>
			</div>
		</div>
	</body>
</html>