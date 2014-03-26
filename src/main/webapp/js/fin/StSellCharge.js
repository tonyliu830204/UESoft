$(function(){
	
	//销售应收款明细信息
    $('#st_sell_chargeItem_Table').datagrid({
    	 url:'',
		 pagination:true,
	     fit:true,
	     singleSelect:true,
	     border:false,
 	     sortName:'collectId',
		 sortOrder:'asc',
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true,
		 idField : 'collectId',
		 pagination:true,
		 loadMsg : "数据加载中，请稍后……",
		 columns : [[
		        {title : '销售营收款明细编号',field : 'collectId',width : 80},
		        {title : '销售应收款汇总编号',field : 'chargeId',width : 80},
		        {title : '付款金额',field : 'paidAmount',width : 80},
		        {title : '付款方式',field : 'patment',width : 80},
		        {title : '收款人',field : 'paidPerson',width : 100},
		        {title : '付款日期',field : 'paidDate',width : 80},
		        {title : '备注',field : 'remark',width : 100}
		        ]]
	 });
  
   		
	 $('#ssc_preclrId').combogrid({
	        panelWidth:360,
	        idField:'preclrAmount',  
	        textField:'preclrId',  
	        fitColum:true,
	        url:projectPath+'StSellChargeAction_loadStSellPreclr.action',
	        columns:[[  {title:'销售结算单单号',field:'preclrId',width:120},
			 			{title:'应收款金额',field:'preclrAmount',width:70},
			 			{title:'客户名称',field:'customName',width:70},
			 			{title:'收款分类',field:'classfication',width:70},
			 			{title : '应收款单号',field : 'chargeId',width : 80,hidden:true},
					    {title : '应收款金额',field : 'totalAmount',width : 80,hidden:true},
					    {title : '已付金额合计',field : 'paidAmount',width : 100,hidden:true},
					    {title : '审核日期',field : 'verifyDate',width : 80,hidden:true},
					    {title : '备注',field : 'remark',width : 100,hidden:true}
			        ]],
		    onClickRow:function(rowIndex, rowData){
		       $('#ssc_PreclrAmount').val(rowData.preclrAmount);
		       $('#ssc_CustomName').val(rowData.customName);
		       $('#ssc_Classfication').val(rowData.classfication);
        	   $('#ssc_ChargeId').val(rowData.chargeId);
        	   $('#ssc_PaidAmount').val(rowData.paidAmount);
        	   $('#ssc_VerifyDate').val(rowData.verifyDate);
        	   $('#ssc_remark').val(rowData.remark);
        	   $.ajax({
					type : 'POST',
					url : 'StSellChargeAction_clear.action',
					data : '',
					dataType : 'json',
					success : function(r){
		               $('#st_sell_chargeItem_Table').datagrid('loadData',r);
	                }
	           });
        	   $.ajax({
					type : 'POST',
					url : 'StSellChargeAction_loadStSellchargeItemByChargeId.action',
					data : 'chargeId='+$('#ssc_ChargeId').val(),
					dataType : 'json',
					success : function(r){
		                	       $('#st_sell_chargeItem_Table').datagrid('loadData',r);
	                    }
	           });
	        }        
	      });  
	 
	    //销售应收款汇总信息
	    $st_sell_chargeMain_Table=$('#st_sell_chargeMain_Table');
	    $st_sell_chargeMain_Table.datagrid({
			url:projectPath+'StSellChargeAction_loadStSellCharge.action',
			fit:true,
			singleSelect:true,
		    pageSize : 10,
	 	    sortName:'chargeId',
			sortOrder:'asc',
			fitColumns:true,
	 		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			pagination:true,
			loadMsg:'数据加载中...',
			 columns : [[ {
					title : '销售应收款汇总编号',
					field : 'chargeId',
					width : 80
				}, {
					title : '销售结算单编号',
					field : 'preclrId',
					width : 80
				}, {
					title : '是否出库',
					field : 'stockOut',
					width : 80
				}, {
					title : '应收款金额',
					field : 'paidAmount',
					width : 100
				}, {
					title : '合计收款金额',
					field : 'totalAmount',
					width : 80
				}, {
					title : '余额',
					field : 'paidBalance',
					width : 100
				}, {
					title : '审核日期',
					field : 'verifyDate',
					width : 80
				}, {
					title : '审核状态',
					field : 'verifyState',
					width : 100
				} , {
					title : '备注',
					field : 'remark',
					width : 100,
					hidden:true
				}, {
					title : '客户名称',
					field : 'customName',
					width : 100,
					hidden:true
				} , {
					title : '收费分类',
					field : 'classfication',
					width : 100,
					hidden:true
				}
			 ]],
        onDblClickRow:function(rowIndex, rowData){
	    	   $('#st_sell_chargeMain_tabs').tabs('select','销售应收款明细');
		       $('#ssc_ChargeId').val(rowData.chargeId);
		       $('#ssc_preclrId').combogrid('setValue',rowData.preclrId);
		       $('#ssc_PreclrAmount').val(rowData.paidAmount);
	     	   $('#ssc_PaidAmount').val(rowData.totalAmount);
	     	   $('#ssc_VerifyDate').val(rowData.verifyDate);
	     	   $('#ssc_CustomName').val(rowData.customName);
	     	   $('#ssc_Classfication').val(rowData.classfication);
	     	   $('#st_sell_chargeItem_Table').datagrid('load');
	     	   $('#ssc_state').val(rowData.verifyState);
	     	   $.ajax({
					type : 'POST',
					url : 'StSellChargeAction_loadStSellchargeItemByChargeId.action',
					data : 'chargeId='+$('#ssc_ChargeId').val(),
					dataType : 'json',
					success : function(r){
		                	       $('#st_sell_chargeItem_Table').datagrid('loadData',r);
	                }
	           });
	    }       
	  });
    })

    //删除销售应收款信息
    function deleteStSellCharge()
    {
    	var ssc_ChargeId=$('#ssc_ChargeId').val();
    	if(ssc_ChargeId!=null&&ssc_ChargeId!=''){
    		var ssc_state=$('#ssc_state').val();
    		if(ssc_state=='未审核')
    		{
    			$.messager.confirm('优亿软件提示', '你确定要删除单号为【'+ssc_ChargeId+'】的销售应收款信息吗?', function(r) {
        			if(r)
    	    			$.ajax({
    	    				type : 'POST',
    	    				url : 'StSellChargeAction_deleteStSellCharge.action',
    	    				data : 'chargeId='+$('#ssc_ChargeId').val(),
    	    				dataType : 'json',
    	    				success : function(r){
    	        			       if(r.success){
    	        			    	   $('#st_sell_preclr_detail_form').form('clear');
    	        			    	   $('#st_sell_chargeMain_tabs').tabs('select','销售应收款汇总');
    	        			    	   $.ajax({
    	        							type : 'POST',
    	        							url : 'StSellChargeAction_loadStSellchargeItemByChargeId.action',
    	        							data : 'chargeId='+'',
    	        							dataType : 'json',
    	        							success : function(r){
    	        				               $('#st_sell_chargeItem_Table').datagrid('loadData',r);
    	        			                }
    	        			           });
    	        			    	   $st_sell_chargeMain_Table.datagrid('load');
    	        			       }
    	        			       else
    	        			    	   $.messager.alert('优亿软件提示',r.msg,'warning',function(){});
    	                    }
    	                });
        		 });
    		}else {
    			$.messager.alert('优亿软件提示','抱歉,【'+$('#ssc_ChargeId').val()+'】销售应收款单已经审核完毕，不能删除，若要删除，请修改该销售应收款单为未审核单据！','warning',function(){});
    		}
    	}else{
    		$.messager.alert('优亿软件提示','请选择要删除的销售应收款信息！','warning',function(){});
    	}
    }

	//取消保存
    function cancelStSellChargeMain()
    {
    	$('#button').empty();
    }
    
    //综合查询
    function sscds_searchByCondition()
	{
	   $('#st_sell_chargeMain_tabs').tabs('select','销售应收款汇总');
	   $.ajax({
			type : 'POST',
			url :'StSellChargeAction_searchStSellChargeByCondition.action',
			data : 'verifyDate='+$('#sscds_verifyDate').val()+'&chargeId='+$('#sscds_chargeId').val()+'&preclrId='+$('#sscds_preclrId').val()
			+'&stockOut='+$('#sscds_stockOut').combobox('getValue')+'&verifyState='+$('#sscds_verifyState').combobox('getText'),
			dataType : 'json',
			success : function(r){
		       $st_sell_chargeMain_Table.datagrid('loadData',r);
		    }
	   });
	}
    
    /*审核*/
    function examineButton()
    {
    	var selected=$st_sell_chargeMain_Table.datagrid('getSelected');
    	$('#st_sell_chargeMain_tabs').tabs('select','销售应收款汇总');
    	if(selected!=null){
    		if(selected.verifyState=='未审核'){
    			  $.messager.confirm('优亿软件提示', '你确定要审核单号为【'+selected.chargeId+'】的销售应收款信息吗?', function(r) {
    		    		 if(r){
    		    			 $.ajax({
    		    					type : 'POST',
    		    					url :'StSellChargeAction_examine.action',
    		    					data : 'chargeId='+selected.chargeId,
    		    					dataType : 'json',
    		    					success : function(r){
    		    				        if(r.success){
    		    				        	$st_sell_chargeMain_Table.datagrid('load','');
    		    				        }else{
    		    				        	 $.messager.alert('优亿软件提示',r.msg,'warning',function(){});
    		    				        }
    		    				    }
    		    			   });
    		    		 }
    		      })
    		}else if(selected.verifyState=='已审核'){
    			 $.messager.alert('优亿软件提示','抱歉，该销售应收款单为已审核单据','warning',function(){});
    		}
    	 }else{
    		$.messager.alert('优亿软件提示','请选择要已审核的销售应收款记录！','warning',function(){});
    	 }
    }
    
    //取消审核
    function cancelExamineButton()
    {
    	var selected=$st_sell_chargeMain_Table.datagrid('getSelected');
    	$('#st_sell_chargeMain_tabs').tabs('select','销售应收款汇总');
    	if(selected!=null){
    		if(selected.verifyState=='已审核'){
    			  $.messager.confirm('优亿软件提示', '你确定要取消审核单号为【'+selected.chargeId+'】的销售应收款信息吗?', function(r) {
    		    		 if(r){
    		    			 $.ajax({
    		    					type : 'POST',
    		    					url :'StSellChargeAction_cancelExamine.action',
    		    					data : 'chargeId='+selected.chargeId,
    		    					dataType : 'json',
    		    					success : function(r){
    		    				        if(r.success){
    		    				        	$st_sell_chargeMain_Table.datagrid('load','');
    		    				        }else{
    		    				        	 $.messager.alert('优亿软件提示',r.msg,'warning',function(){});
    		    				        }
    		    				    }
    		    			   });
    		    		 }
    		      })
    		}else if(selected.verifyState=='未审核'){
    			 $.messager.alert('优亿软件提示','抱歉，该销售应收款单已经取消已审核为未审核单据','warning',function(){});
    		}
    	}else{
    		$.messager.alert('优亿软件提示','请选择要取消已审核的销售应收款记录！','warning',function(){});
    	}
    }
    
    
    //查询条件清空
    function sscds_clearSearchCondition()
    {
       $('#st_sell_chargeMain_tabs').tabs('select','销售应收款汇总');
  	   $('#st_sell_chargeMain_from').form('clear');
  	   $st_sell_chargeMain_Table.datagrid('load');
    }
    
    //付款
   function addPaid()
   {
	   var ssc_ChargeId=$('#ssc_ChargeId').val();
	   if(ssc_ChargeId!=null&&ssc_ChargeId!=''){
		   $('#st_sell_chargeMain_tabs').tabs('select','销售应收款明细');
		   paid();
	   }else{
		   $.messager.alert('优亿软件提示','请先选择要付款的销售应收款单！','warning',function(){});
	   }
	   
   }
   
   //取消付款
   function cancelPaid()
   {
	   var selelcted=$('#st_sell_chargeItem_Table').datagrid('getSelected');
	   if(selelcted!=null){
		   $.messager.confirm('优亿软件提示', '你确定删除单号为【'+selelcted.collectId+'】的销售应收款明细信息吗?', function(r) {
			   if(r){
				   $.ajax({
						type : 'POST',
						url :'StSellChargeAction_deletePaid.action',
						data : 'collectId='+selelcted.collectId+'&paidAmount='+selelcted.paidAmount+'&chargeId='+selelcted.chargeId,
						dataType : 'json',
						success : function(r){
					        if(r.success){
					        	 $.ajax({
										type : 'POST',
										url : 'StSellChargeAction_loadStSellchargeItemByChargeId.action',
										data : 'chargeId='+$('#ssc_ChargeId').val(),
										dataType : 'json',
										success : function(r){
				   		                	       $('#st_sell_chargeItem_Table').datagrid('loadData',r);
			   		                    }
			   		              });
					        	 //刷新汇总表单已收金额合计数据
		    	            	 $.ajax({
		    	         			type : 'POST',
		    	         			url :'StSellChargeAction_searchStSellChargeByChargerId.action',
		    	         			data : 'chargeId='+selelcted.chargeId,
		    	         			dataType : 'json',
		    	         			success : function(r){
		    	         		       if(r!=null&&r!=''){
		    	         		    	  var totalAmont=r.totalAmount;
		    	         		    	  $('#ssc_PaidAmount').val(totalAmont);
		                                  var preclrAmount=$('#ssc_PreclrAmount').val();
		                                  if(parseFloat(totalAmont)>parseFloat(preclrAmount)){
		                                	  $('#ssc_PaidAmount').val(preclrAmount);
		                                  }
		        	         		   }else{
		        	         			  $.messager.alert('优亿软件提示',r.msg,'warning',function(){});
		            	         	   }
		    	         		    }
		    	         	     });
					        }else{
					        	 $.messager.alert('优亿软件提示',r.msg,'warning',function(){});
					        }
					    }
				   });
		       }
		   });
	   }else{
		   $.messager.alert('优亿软件提示','请先选择要取消付款的销售应收款明细记录！','warning',function(){});
	   }
	   
   }

   
   var ssc_d1;
   function paid(){
   	 var selected=$('#ssc_preclrId').combogrid('getText');
   	 if(selected!=null&&selected!=''){
   		 ssc_d1 = $('<div/>');
   		 ssc_d1.dialog({
   			title: '添加销售收款单明细信息',   
   		    width: 630,   
   		    height:140,
   		    cache: false,   
   		    href: projectPath+'fin/stsellcharge/PaidStyle.jsp',
   		    modal: true,
   		    onLoad:function(){
   		    	$('#sscd_paidDate').val(getSystemTime2());
   		    	$('#sscd_chargeId').val($('#ssc_ChargeId').val());
   		    	$('#sscd_PreclrAmount').val($('#ssc_PreclrAmount').val());
   		    },
   		    onClose : function (){
   		    	$(this).dialog('destroy');
   		    }
   	    });
   	 }else{
   		 $.messager.alert('优亿软件提示','对不起，请先选要付款的结算单编号！','warning',function(){});
   	 }
	}




