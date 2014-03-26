	$(function (){
		
		$('#sst_sspd_preclrDateEnd').val(getSystemTime());
		loadPreClrTime($('#sst_sspd_preclrDateStart'));
		
		//销售结算单汇总
		st_sell_preclr_Main_TableDategrid = $('#st_sell_preclr_Main_Table');
		st_sell_preclr_Main_TableDategrid.datagrid({
			url : 'StSellPreclrAction!loadStSellPreclrMain.action',
			fit : true,
			fitColumns : true,
			sortName:'sellmmId',
			sortOrder:'asc',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			pagination : true,
			singleSelect : true,
			columns : [[{
				field : 'preclrId',
				title : '结算单号',
				width : 90
			},{
				field : 'preclrDate',
				title : '结算日期',
				width : 75
			},{
				field : 'tpreclrDate',
				title : '结算日期',
				width : 75,
				hidden:true
			},{
				field : 'cerNo',
				title : '销退单号',
				width : 75
			},{
				field : 'customId',
				title : '客户名称ID',
				width : 75,
				hidden:true
			},{
				field : 'customName',
				title : '客户名称',
				width : 75
			},{
				field : 'preclrNum',
				title : '总数量',
				width : 75
			},{
				field : 'preclrAmount',
				title : '总金额',
				width : 75
			},{
				field : 'preclrCostAmount',
				title : '成本额',
				width : 75
			},{
				field : 'classfication',
				title : '收款分类ID',
				width : 75,
				hidden:true
			},{
				field : 'classficationName',
				title : '收款分类',
				width : 75
			},{
				field : 'manager',
				title : '经办人ID',
				width : 75,
				hidden:true
			},{
				field : 'managerName',
				title : '经办人',
				width : 75
			},{
				field : 'remark',
				title : '备注',
				width : 75
			},{
				field : 'state',
				title : '是否已转结算',
				width : 75
			}]],
			onDblClickRow:function(rowIndex, rowData){
			    $('#st_sell_preclr_tabs').tabs('select','销售单结算明细');
			    ssp_StSellPercharge_clearFormAndDatagrid();
	            $('#st_sspd_preclrDate').val(rowData.tpreclrDate);
	            $('#st_sspd_preclrId').val(rowData.preclrId);
	            $('#st_sspd_manager').val(rowData.manager);
	            $('#st_sspd_customId').combobox('setValue',rowData.customId);
	            $('#st_sspd_cerNo').combogrid('setText',rowData.cerNo);
	            $('#st_sspd_preclrNum').val(rowData.preclrNum);
	            $('#st_sspd_preclrAmount').val(rowData.preclrAmount);
	            $('#st_sspd_preclrCostAmount').val(rowData.preclrCostAmount);
	            $('#st_sspd_classfication').combobox('setValue',rowData.classfication);
	            $('#st_sspd_remark').val(rowData.remark);
	            $('#st_sspd_state').val(rowData.state);
	            $.ajax({
					type : 'POST',
					url :'StSellPreclrAction_findIdByCondition.action',
					data : 'cerNo='+rowData.cerNo,
					dataType : 'json',
					success : function(r){
		            	if(r.total>=0){
		            	   st_sell_preclr_detail_TableDategrid .datagrid('loadData',r);
		            	}else{
		            		$.messager.alert('提示',r.msg,'warning');
		            	}
				    }
			    });
			}
		});
		
		//销售结算单明细
		st_sell_preclr_detail_TableDategrid = $('#st_sell_preclr_detail_Table');
		st_sell_preclr_detail_TableDategrid.datagrid({
			url : '',
			fit : true,
			fitColumns : true,
			sortName:'sellmmId',
			sortOrder:'asc',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			pagination : true,
			singleSelect : true,
			columns : [[{
				field : 'sellmmId',
				title : '销退单号',
				width : 90
			},{
				field : 'sellmmDate',
				title : '消退日期',
				width : 75
			},{
				field : 'partsId',
				title : '配件编号',
				width : 75
			},{
				field : 'partsName',
				title : '配件名称',
				width : 75
			},{
				field : 'punitName',
				title : '单位',
				width : 75
			},{
				field : 'selldCnt',
				title : '数量',
				width : 75
			},{
				field : 'selldPrice',
				title : '单价',
				width : 75
			},{
				field : 'selldAmount',
				title : '金额',
				width : 75
			},{
				field : 'selldCostPrice',
				title : '成本',
				width : 75
			},{
				field : 'selldCostAmount',
				title : '成本额',
				width : 75
			}]]
		});
		
        $('#st_sspd_customId').combobox({
        	onSelect:function(record){
        	 $('#st_sspd_cerNo').combogrid({disabled:false});
        	},
        	onChange:function(newValue, oldValue){
        		 $('#st_sspd_cerNo').combogrid('clear');
        		 $.ajax({
     				type : 'POST',
     				url :'StSellPreclrAction_clear.action',
     				data : '',
     				dataType : 'json',
     				success : function(r){
     			       st_sell_preclr_detail_TableDategrid.datagrid('loadData',r);
     			    }
     			});
		    }
        });
        
	    $('#st_sspd_cerNo').combogrid({
	        panelWidth:500,
	        multiple:true,
	        textField:'sellmmId',  
	        disabled:true,
	        fitColum:true,
	        columns:[[ {title:'销退单号',field:'sellmmId',width:120},
			 			{title:'车牌照',field:'carLicense',width:70},
			 			{title:'客户名称',field:'sellcustomName',width:70},
			 			{title:'数量',field:'sellmmCnt',width:70},
			 			{title:'销退金额',field:'sellmmSumAmount',width:70},
			 			{title:'销退成本额',field:'sellmmSumCost',width:70}
			        ]],
			onShowPanel:function(){
	    	  var customName=$('#st_sspd_customId').combobox('getValue');
	    	  if(customName != null && customName != '')
	    		  $('#st_sspd_cerNo').combogrid({
		    		  url:projectPath+'StSellPreclrAction!loadSellOrderInfo.action?'+$('#st_sell_preclr_detail_form').serialize()
		          });
	    	  else
	    		  $.messager.alert('提示','请先选择客户名称','warning');
	        },
			onClickRow:function(rowIndex, rowData){
			   $.ajax({
					type : 'POST',
					url :'StSellPreclrAction!loadStSellOrderitemBySellmmId.action',
					data : 'sellmmId='+rowData.sellmmId,
					dataType : 'json',
					success : function(r){
						if(r.total>=0){
							 st_sell_preclr_detail_TableDategrid .datagrid('loadData',r);
							 $.ajax({
									type : 'POST',
									url :'StSellPreclrAction_getSum.action',
									data : 'sellmmId='+rowData.sellmmId,
									dataType : 'json',
									success : function(r){
									   $('#st_sspd_preclrNum').val(r.preclrNum);
									   $('#st_sspd_preclrAmount').val(r.preclrAmount);
									   $('#st_sspd_preclrCostAmount').val(r.preclrCostAmount);
								    }
							  });
						}else
							$.messager.alert('提示',r.msg,'warning');
				    }
				});
	        }
	      });  
	    });

		function addPersonnel(i){
	 	   if(i==1){
	 		    $('#st_sell_preclr_tabs').tabs('select','销售单结算明细');
				var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addStSellPreclr();">保存</a>';
				var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="canceladdStSellPreclr();">取消</a>';
				if($('#button').children('a').length == 0){
					$('#button').append(save).append(cancel);
					$.parser.parse('#button');
				}
				$('#st_sell_preclr_detail_form').form('clear');
	            $('#st_sspd_manager').val('888');
	            $('#st_sspd_preclrDate').val(getSystemTime2());
	 	   }else{
	 		   var st_sspd_preclrId = $('#st_sspd_preclrId').val();
	 		   if(st_sspd_preclrId!=null&&st_sspd_preclrId!=''){
	 			   var state=$('#st_sspd_state').val();
	 			   if(state=='是'){
	 				  $.messager.alert('优亿软件提示','对不起，该结算单已转收银，不能修改！','warning',function(){});
	 			   }else{
	 			       $('#st_sell_preclr_tabs').tabs('select','销售单结算明细');
		    		   var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updateStSellPreclr();">保存</a>';
		   			   var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancelupdateStSellPreclr();">取消</a>';
		   			   if($('#button').children('a').length == 0){
		   				 $('#button').append(save).append(cancel);
		   				 $.parser.parse('#button');
		   			   };
	 			   }
	 		   }else{
	 			   $.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){});
	 		   }
	 	   }
	    }

		//取消添加
	   function canceladdStSellPreclr(){
		   $('#button').empty();
		   ssp_StSellPercharge_clearFormAndDatagrid();
		   $('#st_sspd_cerNo').combogrid({ disabled:true});
		   st_sell_preclr_detail_TableDategrid.datagrid('load');
	   }

	   //修改
	   function updateStSellPreclr()
	   {
		   $.ajax({
				type : 'POST',
				url :'StSellPreclrAction_updateStSellPreclr.action',
				data : 'preclrDate='+$('#st_sspd_preclrDate').val()+'&preclrId='+$('#st_sspd_preclrId').val()+'&manager='+$('#st_sspd_manager').val()+
				'&customId='+$('#st_sspd_customId').combobox('getValue')+'&cerNo='+$('#st_sspd_cerNo').combogrid('getText')+
				'&preclrNum='+$('#st_sspd_preclrNum').val()+'&preclrAmount='+$('#st_sspd_preclrAmount').val()+
				'&preclrCostAmount='+$('#st_sspd_preclrCostAmount').val()+'&classfication='+$('#st_sspd_classfication').combobox('getValue')+
				'&remark='+$('#st_sspd_remark').val(),
				dataType : 'json',
				success : function(r){
					if(r.success){
						$('#st_sell_preclr_tabs').tabs('select','销售单结算汇总');
						canceladdStSellPreclr();
						st_sell_preclr_Main_TableDategrid.datagrid('load');
					}else{
						$.messager.alert('提示',r.msg,'warning');
					}
			    }
			});
	   }
	   
	   //取消修改
	   function cancelupdateStSellPreclr()
	   {
		   $('#button').empty();
	   }

	   //清除明细表单数据
	   function ssp_StSellPercharge_clearFormAndDatagrid()
	   {
		   $('#st_sell_preclr_detail_form').form('clear');
		   $.ajax({
				type : 'POST',
				url :'StSellPreclrAction_clear.action',
				data : '',
				dataType : 'json',
				success : function(r){
			       st_sell_preclr_detail_TableDategrid.datagrid('loadData',r);
			    }
			});
	   }

	   //新增
       function addStSellPreclr(){
    	   $.ajax({
				type : 'POST',
				url :'StSellPreclrAction_addStSellPreclr.action',
				data : 'preclrDate='+$('#st_sspd_preclrDate').val()+'&manager='+$('#st_sspd_manager').val()+
				'&customId='+$('#st_sspd_customId').combobox('getValue')+'&cerNo='+$('#st_sspd_cerNo').combogrid('getText')+
				'&preclrNum='+$('#st_sspd_preclrNum').val()+'&preclrAmount='+$('#st_sspd_preclrAmount').val()+
				'&preclrCostAmount='+$('#st_sspd_preclrCostAmount').val()+'&classfication='+$('#st_sspd_classfication').combobox('getValue')+
				'&remark='+$('#st_sspd_remark').val(),
				dataType : 'json',
				success : function(r){
					if(r.success){
						$('#st_sell_preclr_tabs').tabs('select','销售单结算汇总');
						canceladdStSellPreclr();
						st_sell_preclr_Main_TableDategrid.datagrid('load');
					}else{
						$.messager.alert('提示',r.msg,'warning');
					}
			    }
			});
       }
       
       //删除
       function deleteStSellPercharge()
       {
    	   var st_sspd_preclrId = $('#st_sspd_preclrId').val();
 		   if(st_sspd_preclrId!=null&&st_sspd_preclrId!=''){
 			  var state=$('#st_sspd_state').val();
			   if(state=='是'){
				  $.messager.alert('优亿软件提示','对不起，该结算单已转收银，不能修改！','warning',function(){});
			   }else{
	 			  $.messager.confirm('优亿软件提示', '你确定要删除销售结算单号为【'+st_sspd_preclrId+'】该记录信息吗?', function(r) {
					  if(r){
						  $.ajax({
								type : 'POST',
								url :'StSellPreclrAction_deleteStSellPercharge.action',
								data : 'preclrId='+$('#st_sspd_preclrId').val(),
								dataType : 'json',
								success : function(r){
								    if(r.success){
					  		        	ssp_StSellPercharge_clearFormAndDatagrid();
					  		        	$('#st_sell_preclr_tabs').tabs('select','销售单结算汇总');
					  		        	st_sell_preclr_Main_TableDategrid.datagrid('load');
					  		        }else{
					  		        	$.messager.alert('提示',r.msg,'warning');
					  		        }
							    }
							});
					  }
	    	      });
			   }
 		   }else{
 			   $.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){});
 		   }
       }
       
       //是否转收银
   	  function isChangePaid(){
	   		var selected=st_sell_preclr_Main_TableDategrid.datagrid('getSelected')
	   		var state=selected.state;
	   		if(state=='否'){
	   			$.messager.confirm('优亿软件提示', '你确定要将单号为【'+selected.preclrId+'】的结算单转收银吗?', function(r) {
	   				if(r){
		   				$.ajax({
		   					type : 'POST',
		   					url : 'StSellPreclrAction_changePaid.action',
		   					data : 'preclrId='+selected.preclrId+'&state='+state+'&preclrAmount='+selected.preclrAmount,
		   					dataType : 'json',
		   					success : function(r){
		   			   				 if(r.success){
		   			   					st_sell_preclr_Main_TableDategrid.datagrid('load');
		   			   				 }
		   	                }
		   	            });
	   				}
	   			});
	   		}else if(state=='是'){
	   		    $.messager.alert('优亿软件提示','对不起，单号为【'+selected.preclrId+'】的结算单已转收银！','warning',function(){});
	   		}
       }
       
       //汇总综合查询
       function ssp_StSellPercharge_searchByCondition(){
    	   $('#st_sell_preclr_tabs').tabs('select','销售单结算汇总');
    	   $.ajax({
				type : 'POST',
				url :'StSellPreclrAction_searchStSellPreclrMainByCondition.action',
				data : 'preclrDateStart='+$('#sst_sspd_preclrDateStart').val()+'&preclrDateEnd='+$('#sst_sspd_preclrDateEnd').val()+'&preclrId='+$('#sst_sspd_preclrId').val()
				+'&customId='+$('#sst_sspd_customId').combobox('getValues')+'&classfication='+$('#sst_sspd_classfication').combobox('getValues')+'&cerNo='+$('#sst_sspd_cerNo').val()
				+'&state='+$('#sst_sspd_state').combobox('getText'),
				dataType : 'json',
				success : function(r){
    		      st_sell_preclr_Main_TableDategrid.datagrid('loadData',r);
			    }
		   });
       }

       //汇总取消综合查询
       function ssp_StSellPercharge_clearSearchByCondition(){
    	   $('#st_sell_preclr_tabs').tabs('select','销售单结算汇总');
    	   $('#st_sell_preclr_main_form').form('clear');
    	   st_sell_preclr_Main_TableDategrid.datagrid('load');
       }