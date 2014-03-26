
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include
	page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>承兑汇票选择</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
	</head>
	<body>
	<script type="text/javascript">
 //判断页面初始化加载是否完成
    function LoadOk(){
    	if(document.readyState=="complete") {
    	
  			 initFrame();
 		}else{
   			setTimeout("LoadOk()",200);
 		}
	}
   setTimeout("LoadOk()",200);
   //判断页面初始化加载完成    执行
	function initFrame() {
		runs();
		var receiptCode = $('#add_receipt_code').val();
		if (receiptCode != '' && receiptCode != null) {
			$("#receiptCodeId").val(receiptCode);
			$.ajax( {
				type : 'post',
				url : 'sellReceiptAction!getPage.action',
				data : "receiptCode=" + $('#receiptCode').val(),
				dataType : 'json',
				success : function(r) {
					query();
				}
			});
		}
		$('#receiptCode').val('');
	}
	var runs = function() {
		$('#receiptData').datagrid({
			url : '${pageContext.request.contextPath}/sellReceiptAction!getPage.action',
			fit : true,
			pagination : true,
			fitColumns : true,
			sortOrder : 'asc',
			sortName : 'receiptId',
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			columns : [ [ {
				title : '编号',
				field : 'receiptId',
				width : 50,
				hidden : true
			}, {
				title : '票据编号',
				field : 'receiptCode',
				width : 150
			}, {
				title : '出票银行',
				field : 'receiptBank',
				hidden : true
			},{
				title : '出票银行',
				field : 'bankName',
				width : 150
			},{
				title : '金额',
				field : 'receiptMoney',
				width : 100
			}, {
				title : '出票日期',
				field : 'receiptStartDate',
				width : 100
			}, {
				title : '到期日期',
				field : 'receiptEndDate',
				width : 100
			}, {
				title : '备注',
				field : 'remark',
				width : 100
			} ] ],
			onDblClickRow : function(rowIndex, rowData) {
				$('#receipt_code').val(rowData.receiptCode);
				$('#receiptBankId').val(rowData.bankName);
				$('#bank_id').val(rowData.receiptBank);
				$('#receiptStartDateId').val(
						rowData.receiptStartDate);
				$('#receiptEndDateId').val(
						rowData.receiptEndDate);
				$('#receipt_Id').val(rowData.receiptId);
				sgsm_d2.dialog('close');
			}
		});
	}
	var query = function() {
		$('#receiptData').datagrid('unselectAll');
		$('#receiptData').datagrid('load', serializeObject($('#receiptQuery')));
	}
	function clearSearchCondition() {
		$('#receiptQuery').form('clear');
		$('#receiptData').datagrid('load', serializeObject($('#receiptQuery')));

	}
</script>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'查询条件'"
				style="overflow: hidden; padding: 1px; background: #eee; height: 63px;"
				border="false">
				<form id="receiptQuery">
					票据编号:
					<input type="text" id="receiptCodeId" name="receiptCode"
						style="background-color: #c0d8d8;" onkeyup="query();" />
					&nbsp;&nbsp;
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-cancel" onclick="clearSearchCondition();"
						plain="true">清空</a>
				</form>
			</div>
			<div region="center" style="background: #eee;" border="false">
				<table id="receiptData"></table>
			</div>
		</div>
	</body>
</html>