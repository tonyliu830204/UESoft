$(function (){
     $('#accountDate').val(getSystemTime2());
	 $('#relcampName').val($('#rboam_relcampId').combobox('getText'));
	 $('#relcampId').val($('#rboam_relcampId').combobox('getValue'));
     //入退单信息加载
	 $('#rboa_receiptId').combogrid({
	        panelWidth:360,
	        width:130,
	        required:true,
		    missingMessage:'入退单信息必填',
	        invalidMessage : '请在下拉框中选择入退单信息',
	        idField:'preclrAmount',  
	        textField:'receiptId',  
	        fitColum:true,
            columns:[[{title:'入退单号',field:'receiptId',width:100},
		 			{title:'入退日期',field:'storageDate',width:100},
		 			{title:'总数量',field:'totalNum',width:70},
		 			{title:'总金额',field:'totalAmount',width:70},
		 			{title:'总成本',field:'taxCount',width:100,hidden:true},
		 			{title : '备注',field : 'remark',width : 100,hidden:true}
			        ]],
	        onShowPanel:function(){
	        	var relcampId=$('#relcampId').val();
	        	if(relcampId!=null&&relcampId!='')
	        		 $('#rboa_receiptId').combogrid({
   			    		 url:projectPath+'RelcampBalanceOfAccountAction!loadStRtGoods.action?relcampId='+relcampId 
   			    	 });
	        	else
	        		$.messager.alert('提示','请先选择供应商！','warning');
	       },
	       onClickRow:function(rowIndex, rowData){
	    	   $('#RelcampBalanceOfAccountDetailForm').form('load',rowData)
	    	   $('#rboa_receiptId').combogrid('setValue',rowData.receiptId);
	    	   $('#rboa_receiptId').combogrid('setText',rowData.receiptId);
	    	   $.ajax({
				type : 'POST',
				url : 'RelcampBalanceOfAccountAction!searchByStVendorAccount.action',
				data : $('#RelcampBalanceOfAccountDetailForm').serialize(),
				dataType : 'json',
				success : function(r){
   			     if(r!=null&&r!=''){
   			    	  $('#rboa_paidAmount').val(r.paidAmount);
   			    	  $('#rboa_recAmount').val(r.recAmount);
   			     }
		        }
		  });
	   }
	});  
});