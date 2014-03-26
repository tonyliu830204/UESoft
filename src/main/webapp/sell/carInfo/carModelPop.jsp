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
	    var modelName=$('#car_modeName').val();
	          if(modelName!=''){ 
		          $('#carModelName').val(modelName);          
		           $.ajax({
						type : 'post',
						url : 'carModelAction!findModel.action',
						data : "modelId="+$('#car_modeId').val()+"&modelName="+modelName,
						dataType : 'json',
						success : function(r){
					              queryCarModel();
					 	               }
					        });
					       }
					$('#car_modeName').val('');    
					$('#car_modeId').val('');      
					     
	}
		$(function(){
			$('#carModel').datagrid({
					url:'${pageContext.request.contextPath}/carModelAction!getPageModel.action',
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
							title : '车型编码',
							field : 'modelId',
							width : 50,
							sortable:true
					    }, {
							title : '车型名称',
							field : 'modelName',
							width : 50,
							sortable:true
						}, {
							title : '车品牌',
							field : 'carBrand',
							width : 50,
							sortable:true
						}, {
							title : '车品牌',
							field : 'carBrandName',
							width : 50,
							sortable:true
						},{
							title : '销售价',
							field : 'modelSalesPrice',
							width : 50,
							sortable:true
						}, {
							title : '成本价',
							field : 'modelCostPrice',
							width : 50,
							sortable:true
						}, {
							title : '备注',
							field : 'modelRemark',
							width : 80,
							sortable:true
						}
				      ]],
			      	 onDblClickRow:function(rowIndex, rowData){     
						$('#car_modeName').val(rowData.modelName);
						$('#car_modeId').val(rowData.modelId);
						sgsm_d2.dialog('close');
              		 	}
				});
		});
	var queryCarModel = function (){
		$('#carModel').datagrid('unselectAll');
		$('#carModel').datagrid('load', serializeObject($('#aa')) );
}
	function clearSearchCondition(){
		$('#aa').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'carModelAction!getPageModel.action',
			data:$('#aa').serialize(),
			dataType : 'json',
			success : function(r){
				$('#carModel').datagrid('load',r);
			}
	    });
		
	}	
	</script>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 53px;" border="false">
				<form id="aa" name="aa" method="post" fit="true">
					车型名称:<input type="text" id="carModelName" name="carMName" style="background-color: #c0d8d8;" onkeyup="queryCarModel()" />
					&nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSearchCondition();" plain="true">清空</a>
				</form>
			</div>
			<div region="center" style="background: #eee;" border="false">
				<table id="carModel"></table>
			</div>
		</div>
	</body>
</html>