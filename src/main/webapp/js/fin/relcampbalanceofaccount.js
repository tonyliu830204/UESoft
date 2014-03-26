$(function(){
	//对账单汇总
	$('#RelcampBalanceOfAccountMainTable').datagrid({
		url : '',
		fit : true,
		fitColumns : true,
		sortName:'relcampId',
		sortOrder:'asc',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		columns : [[{
			field : 'relcampId',
			title : '供应商Id',
			width : 100,
			hidden:true
		},{
			field : 'relcampName',
			title : '供应商',
			width : 100
		},{
			field : 'sumTotalNum',
			title : '总数量',
			width : 100
		},{
			field : 'sumTotalAmont',
			title : '总金额',
			width : 100
		},{
			field : 'sumTaxCount',
			title : '总成本额',
			width : 100
		},{
			field : 'sumRecAmont',
			title : '应付金额',
			width : 100
		},{
			field : 'sumPaidAmont',
			title : '已付金额',
			width : 100
		},{
			field : 'sumBalance',
			title : '余额',
			width : 100
		}]],
		onDblClickRow:function(rowIndex, rowData){
		   $('#RelcampBalanceOfAccountTab').tabs('select','对账单明细');  
		   $('#RelcampBalanceOfAccountMainForm').form('load',rowData);
		   $.ajax({
				type : 'POST',
				url : 'RelcampBalanceOfAccountAction!loadStVendorAccount.action',
				data : 'relcampId='+rowData.relcampId,
				dataType : 'json',
				success : function(r){
			     if(r.total>0){
			    	 $('#RelcampBalanceOfAccountTable').datagrid('loadData',r);
			    	 $.ajax({
	          				type : 'POST',
	          				url : 'RelcampBalanceOfAccountAction!loadRelcampMain.action',
	          				data : 'relcampId='+rowData.relcampId,
	          				dataType : 'json',
	          				success : function(r){
		          			        $('#RelcampBalanceOfAccountMainForm').form('load',r); 
	          		        }
	          		  });
			     }else if(r.total==0){
			    	 $('#RelcampBalanceOfAccountTable').datagrid('loadData',r);
			    	 $('#rboam_sumTotalNum').val('');
				     $('#rboam_sumTotalAmont').val('');
				     $('#rboam_sumTaxCount').val('');
				     $('#rboam_sumPaidAmont').val('');
				     $('#rboam_sumRecAmont').val('');
				     $('#rboam_sumBalance').val('');
			     }
		        }
		    });
		   
		}
	});
	
	$('#rboam_relcampId').combobox({
		onSelect:function(record){
			$.ajax({
				type : 'POST',
				url : 'RelcampBalanceOfAccountAction!loadStVendorAccount.action',
				data : 'relcampId='+record.id,
				dataType : 'json',
				success : function(r){
			     if(r.total>0){
			    	 $('#RelcampBalanceOfAccountTable').datagrid('loadData',r);
			    	 $.ajax({
	          				type : 'POST',
	          				url : 'RelcampBalanceOfAccountAction!loadRelcampMain.action',
	          				data : 'relcampId='+record.id,
	          				dataType : 'json',
	          				success : function(r){
		          			        $('#RelcampBalanceOfAccountMainForm').form('load',r); 
	          		        }
	          		  });
			     }else if(r.total==0){
			    	 $('#RelcampBalanceOfAccountTable').datagrid('loadData',r);
			    	 $('#rboam_sumTotalNum').val('');
				     $('#rboam_sumTotalAmont').val('');
				     $('#rboam_sumTaxCount').val('');
				     $('#rboam_sumPaidAmont').val('');
				     $('#rboam_sumRecAmont').val('');
				     $('#rboam_sumBalance').val('');
			     }
		        }
		    });
	    }
	})
	
	//对账单明细
	$('#RelcampBalanceOfAccountTable').datagrid({
		url : '',
		fit : true,
		fitColumns : true,
		sortName:'accountIndex',
		sortOrder:'asc',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		columns : [[{
			field : 'accountIndex',
			title : '对账单号',
			width : 90
		},{
			field : 'accountDate',
			title : '对账日期',
			width : 85
		},{
			field : 'accountReceipt',
			title : '票据类型',
			width : 75,
			hidden:true
		},{
			field : 'accountReceiptName',
			title : '票据类型',
			width : 75
		},{
			field : 'receiptId',
			title : '入退单号',
			width : 75
		},{
			field : 'relcampId',
			title : '供应商名称',
			width : 75,
			hidden:true
		},{
			field : 'relcampName',
			title : '供应商名称',
			width : 75
		},{
			field : 'operType',
			title : '收款类型',
			width : 75,
			hidden:true
		},{
			field : 'operTypeName',
			title : '收款类型',
			width : 75
		},{
			field : 'recAmount',
			title : '应付金额',
			width : 75
		},{
			field : 'paidAmount',
			title : '已付金额',
			width : 75
		},{
			field : 'nowPaidAmount',
			title : '本次付款金额',
			width : 75
		},{
			field : 'vendorBalance',
			title : '余额',
			width : 75
		},{
			field : 'stfId',
			title : '经办人',
			width : 75,
			hidden:true
		},{
			field : 'stfName',
			title : '经办人',
			width : 75
		},{
			field : 'vendorRemark',
			title : '备注',
			width : 75
		}]],
		onDblClickRow:function(rowIndex, rowData){
		}
	});
	
	//入退单信息加载
	 $('#receiptId').combogrid({
	        panelWidth:360,
	        idField:'preclrAmount',  
	        textField:'storageId',  
	        fitColum:true,
	        width:110,
	        columns:[[  {title:'入退单号',field:'storageId',width:100},
			 			{title:'入退日期',field:'storageDate',width:100},
			 			{title:'总数量',field:'totalNum',width:70},
			 			{title:'总金额',field:'totalAmount',width:70},
			 			{title:'总成本',field:'taxCount',width:100,hidden:true},
			 			{title : '备注',field : 'remark',width : 100,hidden:true}
			        ]],
	        onShowPanel:function(){
	        	var relcampId=$('#relcampId').combobox('getValue');
	        	if(relcampId != null && relcampId != ''){
	        		$.ajax({
							type : 'POST',
							url : 'RelcampBalanceOfAccountAction!loadStRtGoods.action',
							data : 'relcampId='+relcampId,
							dataType : 'json',
							success : function(r){
	        			     if(r.total>0){
	        			    	 $('#receiptId').combogrid({
	        			    		 url:projectPath+'RelcampBalanceOfAccountAction!loadStRtGoods.action?relcampName='+relcampName 
	        			    	 });
	        			     }else{
	        			    	 $('#receiptId').combogrid('hidePanel');
	        			    	 $.messager.alert('提示','没有相应的入退汇总信息！','warning');
	        			     }
					        }
					 });
	        	}else
	        		$.messager.alert('提示','请先选择供应商！','warning');
	       },
	       onClickRow:function(rowIndex, rowData){
	    	   $('#rboa_totalNum').val(rowData.totalNum);
	    	   $('#rboa_totalAmount').val(rowData.totalAmount);
	    	   $('#rboa_taxCount').val(rowData.taxCount);
	       }
	});  
	
	//未对账查询
	$('#NoRelcampBalanceOfAccountTable').datagrid({
		url : 'RelcampBalanceOfAccountAction!loadNoPaidStVendorAccountMain.action',
		fit : true,
		fitColumns : true,
		sortName:'relcampId',
		sortOrder:'asc',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		columns : [[{
			field : 'relcampId',
			title : '供应商Id',
			width : 100,
			hidden:true
		},{
			field : 'relcampName',
			title : '供应商',
			width : 100
		},{
			field : 'sumTotalNum',
			title : '总数量',
			width : 100
		},{
			field : 'sumTotalAmont',
			title : '总金额',
			width : 100
		},{
			field : 'sumTaxCount',
			title : '总成本额',
			width : 100
		},{
			field : 'sumRecAmont',
			title : '应付金额',
			width : 100
		}]]
	});
     })

	 /**3.添加保存取消按钮**/
	 function addPersonnel(i)
	 {
		 $('#RelcampBalanceOfAccountTab').tabs('select','对账单明细');
		 if(i==1){//添加状态
		   var relcampId=$('#rboam_relcampId').combobox('getValue');
		   if(relcampId!=null&&relcampId!='')
			   paid();
		   else
			   $.messager.alert('提示','请选中要付款对账的供应商！','warning');
		 }else{//修改状态
		   $('#RelcampBalanceOfAccountTab').tabs('select','对账单明细');
	       var selected=$('#RelcampBalanceOfAccountTable').datagrid('getSelected');
	       if(selected!=null&&selected!=''){
	    	   u_paid(selected);
	       }else{
	    	   $.messager.alert('提示','请选中要删除的供应商对账单信息！','warning');
	       }
		 }
	  }
   
      /**（新增）取消按钮事件*/
      function add_cancel(){
    	  $('#saveOrCancelBtn').empty();
    	  $('#RelcampBalanceOfAccountDetailForm').form('clear')
      }
      
      function del(){
    	  $('#RelcampBalanceOfAccountTab').tabs('select','对账单明细');
    	  var selected=$('#RelcampBalanceOfAccountTable').datagrid('getSelected');
    	  if(selected!=null && selected!=''){
    		  $.messager.confirm('优亿软件提示', '你确定要删除单号为【'+selected.accountIndex+'】的供应商对账单明细信息吗?', function(r) {
	    		if(r){
    			  $.ajax({
  					type : 'POST',
  					url : 'RelcampBalanceOfAccountAction!delete.action',
  					data : 'accountIndex='+selected.accountIndex,
  					dataType : 'json',
  					success : function(r){
	  			     if(r.success){
	  			    	var relcampId=$('#rboam_relcampId').combobox('getValue');
	  			    	$.ajax({
	         				type : 'POST',
	         				url : 'RelcampBalanceOfAccountAction!loadStVendorAccount.action',
	         				data : 'relcampId='+relcampId,
	         				dataType : 'json',
	         				success : function(r){
		         			     if(r.total>=0){
		         			    	 $('#RelcampBalanceOfAccountTable').datagrid('loadData',r);
		         			    	 $('#RelcampBalanceOfAccountMainTable').datagrid('load');
		         			     }
	         		        }
	         		    });
  		  			    $.ajax({
  	          				type : 'POST',
  	          				url : 'RelcampBalanceOfAccountAction!loadRelcampMain.action',
  	          				data : 'relcampId='+relcampId,
  	          				dataType : 'json',
  	          				success : function(r){
  		          			       $('#RelcampBalanceOfAccountMainForm').form('load',r); 
  	          		        }
  	          		     });
	  			     }else
	  			    	$.messager.alert('提示','供应商对账单信息删除失败！','warning');
  			        }
  			     });
	    		}
    		 })
    	  }else
    		  $.messager.alert('提示','请选中要删除的供应商对账单信息！','warning');
      }

      /**对账单汇总  查询*/
      function rboam_serachCondition(){
    	  var tabName=$('#RelcampBalanceOfAccountTab').tabs('getSelected');
 		  var tab = tabName.panel('options').title;
 		  if(tab=='对账单汇总'){
 			  var  relcampId=$('#s_rboam_relcampId').combobox('getValue');
 	    	  if(relcampId!=null&&relcampId!=''){
 	    		  $.ajax({
 	   				type : 'POST',
 	   				url : 'RelcampBalanceOfAccountAction!loadStVendorAccountMain.action',
 	   				data : 'relcampId='+relcampId,
 	   				dataType : 'json',
 	   				success : function(r){
 	       			    $('#RelcampBalanceOfAccountMainTable').datagrid('loadData',r);
 	       			     
 	   		        }
 	   		      });
 	    	  }else{
 	    		  $.messager.alert('提示','请输入查询条件！','warning');
 	    	  }
 		  }else if(tab=='未对账查询'){
 			  var  relcampId=$('#no_rboam_relcampId').combobox('getValue');
	    	  if(relcampId!=null&&relcampId!=''){
	    		  $.ajax({
	   				type : 'POST',
	   				url : 'RelcampBalanceOfAccountAction!loadNoPaidStVendorAccountMain.action',
	   				data : 'relcampId='+relcampId,
	   				dataType : 'json',
	   				success : function(r){
	       			    $('#NoRelcampBalanceOfAccountTable').datagrid('loadData',r);
	   		        }
	   		      });
	    	  }else
	    		  $.messager.alert('提示','请输入查询条件！','warning');
 		  }
      }
      
      /**对账单汇总 查询条件清空*/
      function rboam_clearCondition(){
    	  var tabName=$('#RelcampBalanceOfAccountTab').tabs('getSelected');
 		  var tab = tabName.panel('options').title;
 		  if(tab=='对账单汇总'){
 			  $('#RelcampBalanceOfAccountMainSearchForm').form('clear');
 	    	  $('#RelcampBalanceOfAccountMainTable').datagrid('loadData', {total:0,rows:[]});
 		  }else if(tab=='未对账查询'){
 			  $('#NoRelcampBalanceOfAccountMainSearchForm').form('clear');
 	    	  $('#NoRelcampBalanceOfAccountTable').datagrid('load');
 		  }
      }
      
      var rboa_d1;
	    function paid(){
	  	  rboa_d1 = $('<div/>');
	  		    rboa_d1.dialog({
	  			title: '供应商对账添加',   
	  		    width: 750,   
	  		    height:200,
	  		    cache: false,
			    href: projectPath+'fin/relcampbalanceofaccount/relcamppay.jsp',
			    modal: true,
				buttons : [{
					text : '付款',
					iconCls : 'icon-add',
					handler : function (){
					 var isno=$('#RelcampBalanceOfAccountDetailForm').form('validate');
			    	 if(isno){
			    		 $.ajax({
			    				type : 'POST',
			    				url : 'RelcampBalanceOfAccountAction!add.action',
			    				data : $('#RelcampBalanceOfAccountDetailForm').serialize(),
			    				dataType : 'json',
			    				success : function(r){
			    	                  if(r.success){
				    	                 var relcampId=$('#rboam_relcampId').combobox('getValue');
			    	                     $.ajax({
			    	         				type : 'POST',
			    	         				url : 'RelcampBalanceOfAccountAction!loadStVendorAccount.action',
			    	         				data : 'relcampId='+relcampId,
			    	         				dataType : 'json',
			    	         				success : function(r){
			    		         			     if(r.total>=0){
			    		         			    	 $('#RelcampBalanceOfAccountTable').datagrid('loadData',r);
			    		         			    	 $('#RelcampBalanceOfAccountMainTable').datagrid('load');
			    		         			     }
			    	         		        }
			    	         		     });
			    	                     $.ajax({
			    	          				type : 'POST',
			    	          				url : 'RelcampBalanceOfAccountAction!loadRelcampMain.action',
			    	          				data : 'relcampId='+relcampId,
			    	          				dataType : 'json',
			    	          				success : function(r){
				    		          			   $('#RelcampBalanceOfAccountMainForm').form('load',r)
			    	          		        }
			    	          		     });
										 rboa_d1.dialog('destroy');
			    	                  }else
			    	                	 $.messager.alert('优亿软件提示','供应商对账添加失败！','warning',function(){});
			    	            }
			    	        });
			    	 }else
						 $.messager.alert('优亿软件提示','表单必填项信息填写不完整，记录无法保存！','warning',function(){});
				    }
				}],
				onClose : function (){
			    	$(this).dialog('destroy');
			    }
		    });
	    }