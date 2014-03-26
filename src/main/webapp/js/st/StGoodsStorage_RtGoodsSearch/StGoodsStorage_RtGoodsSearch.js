$(function(){
	
		 $('#endTime').val(getSystemTime());
		 getStartDate($('#startTime'));
		 
	    /**入退单查询datagrid*/
		$('#StGoodsStorage_RtGoodsSearchTable').datagrid({
			 url:'',
			 pagination:true,
		     fit:true,
		     sortName:'changePriceId',
		     sortOrder:'asc',
		     pageSize : 10,
		     rownumbers:true,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : true, 
			 idField : 'partsId',
			 singleSelect:true,
			 loadMsg : "数据加载中，请稍后……",
			 columns : [ [ {title : '配件编号',field : 'partsId',width : 50,sortable:true
				}, {title : '配件名称',field : 'partsName',width : 50,sortable:true
				}, {title : '库位',field : 'partsLibrary',width : 30,sortable:true
				}, {title : '单位',field : 'punitName',width : 50,sortable:true
				}, {title : '仓别',field : 'storeName', width : 50
				}, {title : '入库数量',field : 'storagePartsCount',width : 50,sortable:true
				}, {title : '退货数量',field : 'rtStoragePartsCount',width : 50,sortable:true
				}, {title : '剩余数量',field : 'partsCount',width : 50,sortable:true
				}, {title : '入退含税成本价',field : 'taxPrice',width : 50,sortable:true
				}, {title : '入退含税金额',field : 'taxTotalAmount',width : 50,sortable:true
				}, {title : '当前库存含税成本价',field : 'partsLatestTaxprice',width : 50,sortable:true
				}, {title : '配件型号',field :'ptypeName',width : 50,sortable:true,hidden:true
				}, {title : '配件品牌',field : 'pbrdName',width : 50,sortable:true,hidden:true
				}, {title : '适用车型',field : 'fitType', width : 50,hidden:true
		        }, {title : '仓别ID',field : 'storeId', width : 50,hidden:true
		        }, {title : '开始时间',field : 'startTime', width : 50,hidden:true
		        }, {title : '结束时间',field : 'endTime', width : 50,hidden:true
		        }
			 ]],
		     onDblClickRow:function(rowIndex, rowData){
			     $('#st_goodsstorage_regoods_tabs').tabs('select','入退查询明细');
			     $('#StGoodsStorage_RtGoodsDetailSearchForm').form('load',rowData);
		  	     $.ajax({   
						type : 'POST',
						url : 'StGoodsStorageRtGoodsSearchAction!loadStGoodsStorageRtGoodsDetailSearchInfo.action',
						data : 'partsId='+rowData.partsId+'&storeId='+rowData.storeId+'&startTime='+rowData.startTime+'&endTime='+rowData.endTime,
						dataType : 'json',
						success : function callback(r) {
		  	    	       if(r.total>0)
				             $('#StGoodsStorage_RtGoodsSearchDetailTable').datagrid('loadData',r);
		  	    	       else
		  	    	    	 $('#StGoodsStorage_RtGoodsSearchDetailTable').datagrid('loadData', {total:0,rows:[]});
				        }
				  });
             }                 
	     });
		
		
		$('#StGoodsStorage_RtGoodsSearchDetailTable').datagrid({
			 url:'',
			 pagination:true,
		     fit:true,
		     sortName:'changePriceId',
		     sortOrder:'asc',
		     pageSize : 10,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : true, 
			 idField : 'partsId',
			 singleSelect:true,
			 loadMsg : "数据加载中，请稍后……",
			 columns : [ [ {title : '入退分类',field : 'typeName',width : 50,sortable:true
			 }, {title : '入退单号',field : 'storageId',width : 100,sortable:true
			    }, {title : '配件编号',field : 'partsId',width : 50,sortable:true
				}, {title : '配件名称',field : 'partsName',width : 50,sortable:true
				}, {title : '库位',field : 'partsLibrary',width : 30,sortable:true
				}, {title : '单位',field : 'punitName',width : 50,sortable:true
				}, {title : '仓别',field : 'storeName', width : 50
				}, {title : '数量',field : 'partsCount',width : 50,sortable:true
				}, {title : '入退含税成本价',field : 'taxPrice',width : 50,sortable:true
				}, {title : '入退含税金额',field : 'taxTotalAmount',width : 50,sortable:true
				}, {title : '当前库存含税成本价',field : 'partsLatestTaxprice',width : 50,sortable:true
				}, {title : '配件型号',field :'ptypeName',width : 50,sortable:true,hidden:true
				}, {title : '配件品牌',field : 'pbrdName',width : 50,sortable:true,hidden:true
				}, {title : '适用车型',field : 'fitType', width : 50,hidden:true
		        }, {title : '仓别ID',field : 'storeId', width : 50,hidden:true}
			 ]]
		});
})

function searchByCondition(){
	$.ajax({   
		type : 'POST',
		url : 'StGoodsStorageRtGoodsSearchAction!loadStGoodsStorageRtGoodsSearchInfo.action',
		data : $('#StGoodsStorage_RtGoodsSearchForm').serialize(),
		dataType : 'json',
		success : function callback(r) {
        		$('#StGoodsStorage_RtGoodsSearchTable').datagrid('loadData',r);
        }
    });
}
function clearSearchByCondition(){
	 $('#StGoodsStorage_RtGoodsSearchForm').form('clear');
	 $('#StGoodsStorage_RtGoodsSearchTable').datagrid('loadData', {total:0,rows:[]});
}

/**
 * 
 * 导出excel
 * 选择要导出的列
 * 参数1   dateGrid控件id属性
 * 参数2   dateGrid控件对应数据库类型
 * 参数3   dateGrid控件上层控件id属性
 * 参数4   执行按钮value文本
 * 参数5   title文本
 * 参数6   功能类型    0导出   1导入   2打印    3隐藏列
 * 参数7   回调函数
 * @return
 */
function _except(){
	showEditDialog("StGoodsStorage_RtGoodsSearchDetailTable",null,"pnc_partsNowCountTableDiv","开始导出","导出配置",0,_callbackExcept);
}

/**
 * 导出excel回调函数
 * 将筛选出的列导出到Excel文件
 * 只支持Microsoft Office,不支持WPS
 * @param parentId
 * @param fieldNames  要导出的列字段
 * @return
 */
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"入退单汇总"+getSystemTime());
}
