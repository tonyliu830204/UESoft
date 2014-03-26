//入库单汇总datagrid
$(function (){
	 $('#srg_StStorageMainTable').datagrid({
	 url:'StRtGoodsAction!searchStGoodsStorageByRelcampId.action?relcampId='+$('#relcampId').val(),
     singleSelect:true,
     pagination:true,
     pageSize : 10,
     fit:true,
     sortOrder:'asc',
     sortName:'storageId',
	 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	 fitColumns : true, 
	 idField : 'storageId',
	 loadMsg : "数据加载中，请稍后……",
	 columns : [ [ {title : '入库单号',field : 'storageId',width : 103
		    },{title : '供应商编号',field : 'relcampId',width : 50
			}, {title : '供应商名称',field : 'relcampName',width : 50
			}, {title : '经办人名称',field : 'manager',width : 80
			}, {title : '经办人编号',field : 'managerId',hidden:true
			}, {title : '采购员编号',field : 'buyerId',hidden:true
			}, {title : '采购员名称',field : 'buyer',width : 80
			}, {title : '仓库名称',field : 'storeName',width : 80
			}, {title : '仓库Id',field : 'storeId',width : 80,hidden:true
			}]],
		    onDblClickRow:function(rowIndex, rowData){
		      $('#storageId').val(rowData.storageId);
	          $('#buyer').val(rowData.buyer);
	          $('#buyerId').val(rowData.buyerId);
	          $('#storeName').val(rowData.storeName);
	          $('#srgm_storeId').val(rowData.storeId);
			  $('#totalNum').val(0);
	          $('#strtgmSumCost').val(0);
	          $('#strtgmSumNoCost').val(0);//未税成本
        	   $.ajax({
					type : 'POST',
					url : 'StRtGoodsAction!searchStGoodsStorageByStorageId.action',
					data : "storageId="+rowData.storageId,
					dataType : 'json',
					success : function(r){
							$('#srg_StRtGoodsDetailTable').datagrid('loadData',r);
							d4.dialog('close');
		    	        }
			       });
		       }         
	 });
  });

	var query1 = function (){
		 $('#srg_StStorageMainTable').datagrid('load', serializeObject($('#srg_StStorageMainTableForm')));
}

var _clear1 = function (){
	$('#srg_StStorageMainTableForm').form('clear');
	 $('#srg_StStorageMainTable').datagrid('load', serializeObject($('#srg_StStorageMainTableForm')));
}

var query2 = function (){
	if(storageId!=undefined){
		$('#srg_StGoodsStorageItemTable').datagrid('load', serializeObject($('#srg_StGoodsStorageItemTableForm')));
		 $.ajax({
				type : 'POST',
				url : 'StRtGoodsAction!searchStGoodsStorageByCondition.action',
				data : 'storageId='+storageId+'&partsId='+$('#srg_s_partsId').val()+'&partsName='+$('#srg_s_partsName').val(),
				dataType : 'json',
				success : function(r){
								 $('#srg_StGoodsStorageItemTable').datagrid('loadData',r);
    	        }
	      });
	}else{
		$.messager.alert('优亿软件提示','当前没有可查询配件，请先选择入库单加载相关明细配件信息！','warning',function(){});
	}
	
}

var _clear2 = function (){
	if(storageId!=undefined){
		$('#srg_StGoodsStorageItemTableForm').form('clear');
		$.ajax({
				type : 'POST',
				url : 'StRtGoodsAction!searchStGoodsStorageByCondition.action',
				data : 'storageId='+storageId,
				dataType : 'json',
				success : function(r){
								 $('#srg_StGoodsStorageItemTable').datagrid('loadData',r);
    	        }
	    });
	}else{
		$.messager.alert('优亿软件提示','当前没有可查询配件，清空无效！','warning',function(){});
    }
}