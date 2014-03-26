$(function(){
	
		$('#endTime').val(getSystemTime());
		getStartDate($('#startTime'));
		
	    /**出退单查询datagrid*/
		$('#StOut_StRtPartsSearchTable').datagrid({
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
			 columns : [ [ {title : '配件编号',field : 'partsId',width : 50,sortable:true
				}, {title : '配件名称',field : 'partsName',width : 50,sortable:true
				}, {title : '库位',field : 'partsLibrary',width : 30,sortable:true
				}, {title : '单位',field : 'punitName',width : 50,sortable:true
				}, {title : '配件品牌',field : 'pbrdName',width : 50,sortable:true,hidden:true
				}, {title : '仓别',field : 'storeName', width : 50
				}, {title : '出库数量',field : 'outPartsCount',width : 50,sortable:true
				}, {title : '退料数量',field : 'rtOutPartsCount',width : 50,sortable:true
				}, {title : '剩余数量',field : 'itemCount',width : 50,sortable:true
				}, {title : '出退含税成本价',field : 'taxPrice',width : 50,sortable:true
				}, {title : '出退含税金额',field : 'taxCastamont',width : 50,sortable:true
				}, {title : '当前库存含税成本价',field : 'partsLatestTaxprice',width : 50,sortable:true
				}, {title : '索赔分类',field : 'claimsName',width : 50,sortable:true
				}, {title : '备注',field : 'outItemRemark',width : 50,sortable:true
				}, {title : '配件型号',field :'ptypeName',width : 50,sortable:true,hidden:true
				}, {title : '适用车型',field : 'fitType', width : 50,hidden:true
		        }, {title : '仓别ID',field : 'storeId', width : 50,hidden:true
				}, {title : '开始时间',field : 'startTime', width : 50,hidden:true
		        }, {title : '结束时间',field : 'endTime', width : 50,hidden:true}
			 ]],
			onDblClickRow:function(rowIndex, rowData){
			     $('#st_out_rtparts_tabs').tabs('select','出退查询明细');
			     $('#StOut_StRtPartsDetailForm').form('load',rowData);
		  	     $.ajax({   
						type : 'POST',
						url : 'StOutStRtPartsSearchAction!loadStOutAndStRtPartsDetailSearchInfo.action',
						data : 'partsId='+rowData.partsId+'&storeId='+rowData.storeId+'&startTime='+rowData.startTime+'&endTime='+rowData.endTime,
						dataType : 'json',
						success : function callback(r) {
		  	    	       if(r.total>0)
				             $('#StOut_StRtPartsDetailSearchTable').datagrid('loadData',r);
		  	    	       else
		  	    	    	 $('#StOut_StRtPartsDetailSearchTable').datagrid('loadData', {total:0,rows:[]});
				        }
				  });
              }                 
        });
		$('#StOut_StRtPartsDetailSearchTable').datagrid({
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
			 columns : [ [{title : '出退分类',field : 'typeName',width : 50,sortable:true
			    },  {title : '出退单编号',field : 'stomId',width : 100,sortable:true
		        },  {title : '配件编号',field : 'partsId',width : 50,sortable:true
				}, {title : '配件名称',field : 'partsName',width : 50,sortable:true
				}, {title : '库位',field : 'partsLibrary',width : 30,sortable:true
				}, {title : '单位',field : 'punitName',width : 50,sortable:true
				}, {title : '配件品牌',field : 'pbrdName',width : 50,sortable:true,hidden:true
				}, {title : '仓别',field : 'storeName', width : 50
				}, {title : '数量',field : 'itemCount',width : 50,sortable:true
				}, {title : '出退含税成本价',field : 'taxPrice',width : 50,sortable:true
				}, {title : '出退含税金额',field : 'taxCastamont',width : 50,sortable:true
				}, {title : '当前库存含税成本价',field : 'partsLatestTaxprice',width : 50,sortable:true
				}, {title : '索赔分类',field : 'claimsName',width : 50,sortable:true
				}, {title : '备注',field : 'outItemRemark',width : 50,sortable:true
				}, {title : '配件型号',field :'ptypeName',width : 50,sortable:true,hidden:true
				}, {title : '适用车型',field : 'fitType', width : 50,hidden:true
		        }, {title : '仓别ID',field : 'storeId', width : 50,hidden:true}
			 ]]
		});
})

function searchByCondition(){
	$.ajax({   
		type : 'POST',
		url : 'StOutStRtPartsSearchAction!loadStOutAndStRtPartsSearchInfo.action',
		data : $('#StOut_StRtPartsSearchForm').serialize(),
		dataType : 'json',
		success : function callback(r) {
		   $('#StOut_StRtPartsSearchTable').datagrid('loadData',r);
	    }
    });
}

function clearSearchByCondition(){
	 $('#StOut_StRtPartsSearchForm').form('clear');
	 $('#StOut_StRtPartsSearchTable').datagrid('loadData', {total:0,rows:[]});
}
