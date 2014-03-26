//判断页面初始化加载是否完成
  function   LoadOk(){
     if(document.readyState   =="complete") {
	   initFrame();
	 }else {
	   setTimeout("LoadOk()",200);
	 }
  }
  setTimeout("LoadOk()",200);
  //判断页面初始化加载完成    执行
  function   initFrame(){
      var orderId=$('#orderId').val();
      if(orderId!=''){ 
          $('#sgmss_orderId').attr("value",orderId);
          $.ajax({
			type : 'post',
			url : 'StGoodsStorageAction!searchStPurOrderByCondition.action',
			data : $('#stgoodstorage_stpurorderform').serialize(),
			dataType : 'json',
			success : function(r){
		               $('#stgoodstorage_stpurordertable').datagrid('loadData',r);
   	        }
          });
      }
 }
	 
 $(function(){
   $('#stgoodstorage_stpurordertable').datagrid({
	 url:'StGoodsStorageAction!loadStPurOrder.action',
	 pagination:true,
     fit:true,
     sortOrder:'asc',
	 sortName:'orderId',
     singleSelect:true,
     rownumbers:true,
     pageSize : 10,
	 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	 fitColumns : true, 
	 idField : 'orderId',
	 loadMsg : "数据加载中，请稍后……",
	 columns : [ [ {title : '采购单号',field : 'orderId',width : 150,sortable:true
			}, {title : '供应商编号',field : 'relcampId',width : 30,hidden:true
			}, {title : '供应商名称',field : 'relcampName',width : 60,sortable:true
			}, {title : '经办人',field : 'managerName',width : 50,sortable:true
			}, {title : '采购员ID',field : 'buyer',hidden:true
			}, {title : '采购员',field : 'buyerName',width : 60,sortable:true
			}, {title : '采购数量',field : 'numTotal',width : 60,sortable:true
			}, {title : '采购金额',field : 'totalAmount',width : 60,sortable:true
			}, {title : '付讫',field : 'paidName',width : 60,hidden:true
			}, {title : '税率',field : 'taxRate',width : 60,hidden:true
			}, {title : '票据类型',field : 'notesType',width : 60 ,sortable:true
			}, {title : '未税额',field : 'notaxTotalamont',width : 60,sortable:true
			}, {title : '税额',field : 'taxCount',width : 60,sortable:true
			}, {title : '付讫',field : 'paid',width : 60,hidden:true
			}]],
	  onDblClickRow:function(rowIndex, rowData){  
	      	 //首先将选择的采购单汇总信息传递到表单上
	      	 $('#orderId').val(rowData.orderId);
	      	 $('#relcampName').val(rowData.relcampName);
	      	 $('#relcampId').val(rowData.relcampId);
	      	 $('#buyer').val(rowData.buyer);
	      	 $('#buyerName').val(rowData.buyerName);
	      	 $('#totalNum').val(rowData.numTotal);
	      	 $('#totalAmount').val(rowData.totalAmount);
	      	 $('#notesType').val(rowData.notesType);
	      	 $('#paid').val(rowData.paidName);
	      	 $('#taxRate').val(rowData.taxRate);
	      	 $('#notaxTotalamont').val(rowData.notaxTotalamont);
	      	 $('#taxCount').val(rowData.taxCount);
	      	 //选择完采购单信息后，加载与之对应的采购单明细信息
	      	 $.ajax({                                               //为采购单明细datagrid赋值
				type : 'post',
				url : 'StGoodsStorageAction!searchStPurOrderItemByOrderId.action',
				data :"orderId="+rowData.orderId,
				dataType : 'json',
				success : function(r){
	                        if(r.msg!="error")
	                        	 $('#StGoodsStoregeDetailTable').datagrid('loadData',r);
	                        else
								 $.messager.alert('提示','数据提交失败!','warning');
				          }
		     });
	      	 //关闭采购单选择窗体
	      	 sgsm_d2.dialog('close');
           }
        });
    })
    
	function _query(){
	    $.ajax({                                 
			type : 'POST',
			url : 'StGoodsStorageAction!searchStPurOrderByCondition.action',
			data : $('#stgoodstorage_stpurorderform').serialize(),
			dataType : 'json',
			success : function(r){
      	        $('#stgoodstorage_stpurordertable').datagrid('loadData',r);
      	    }
         });
	}
	
	function _clear(){
	   $('#stgoodstorage_stpurorderform').form('clear');
	   $('#stgoodstorage_stpurordertable').datagrid('load');
	}