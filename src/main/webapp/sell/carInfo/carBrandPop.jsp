<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String key = new String(request.getParameter("key").toString().getBytes("ISO8859_1"), "UTF-8");
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆品牌选择</title>
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
    var brandName=$('#carBrandName').val();
          if(brandName!='' && brandName!=null){ 
	         	 $('#dataValue').val(brandName);          
	           $.ajax({
					type : 'post',
					url : 'baseTagAction!findChildByValue.action',
					data : "childId="+$('#car_brandId').val()+"&dataValue="+brandName,
					dataType : 'json',
					success : function(r){
				               //$('#chiledData').datagrid('loadData',r);
				               query();
				 	               }
				        });
				       }
				      $('#carBrandName').val('');
				      $('#car_brandId').val('');
}
	var $chiledData;
	$(function() {
		$chiledData = $('#chiledData');
		$chiledData.datagrid( {
			url : 'baseTagAction!baseListData.action',
			queryParams : {
				pdataKey : $("#pkey").val()
			},
			fit : true,
			idField : 'childId',
			fitColumns : true,
			pagination : true,
			rownumbers : true,
			sortOrder : 'asc',
			sortName : 'pparentId',
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			loadMsg : "数据加载中，请稍后……",
			columns : [ [ {
				title : '编号',
				field : 'childId',
				width : 100,
				hidden : true

			}, {
				title : '代码',
				field : 'dataKey',
				width : 100
			}, {
				title : '名称',
				field : 'dataValue',
				width : 100
			}, {
				title : '备注',
				field : 'remark',
				width : 100
			} ] ],
			onDblClickRow : function(rowIndex, rowData) {
				$('#carBrandName').val(rowData.dataValue);
				$('#car_brandId').val(rowData.childId);
				sgsm_d1.dialog('close');
			}
		});
	});
	var query = function() {
		$('#chiledData').datagrid('unselectAll');
		$('#chiledData')
				.datagrid('load', serializeObject($('#childQueryForm')));
	}
	function clearSearchCondition() {
		$('#dataKey').val("");
		$('#dataValue').val("");
		$.ajax( {
			type : 'POST',
			url : 'baseTagAction!baseListData.action',
			data : $('#childQueryForm').serialize(),
			dataType : 'json',
			success : function(r) {
				$('#chiledData').datagrid('load',
						serializeObject($('#childQueryForm')));
			}
		});

	}
</script>

		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 63px;" border="false">
				<form id="childQueryForm">
					<input type="hidden" id="pkey" name="pdataKey" value="<%=key%>" />
					名称:<input type="text" id="dataValue" name="cquerydataValue" style="background-color: #c0d8d8;" onkeyup="query();" />
					&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSearchCondition();" plain="true">清空</a>
				</form>
			</div>
			<div region="center" style="background: #eee;" border="false">
				<table id="chiledData"></table>
			</div>
		</div>
	</body>
</html>