//判断页面初始化加载是否完成
	function   LoadOk(){
		     if(document.readyState   =="complete")
			   initFrame();
			 else
			   setTimeout("LoadOk()",200);
	}
	setTimeout("LoadOk()",200);
	
	//判断页面初始化加载完成    执行
	function   initFrame()
	{
	    var receptionId=$('#receptionId').val();
	    if(receptionId!=null&&receptionId!=''){ 
	        $('#stpreout_addplanparts_receptionId').attr("value",receptionId);
	        query();
	        $('#hiddenRecptionId').val(receptionId); 
	        $('#spo_planParts').datagrid('options').url='StOutAction!loadFrtReceptParts.action?receptionId='+receptionId;
       	    $('#spo_planParts').datagrid('reload');
		    }
		}

  var dbClickState='undefined';	
  $(function (){
	 
	//工单信息
$('#spo_frtReceptionTable').datagrid({
     url:'StOutAction!loadFrtReception.action',
     pagination:true,
     fit:false,
     height:200,
	 width:760,
     singleSelect:true,
     pageSize : 5,
   	 pageList : [ 5, 10, 15, 20, 25, 30, 35, 40 ],
   	 fitColumns : true,
   	 idField : 'receptionId',
   	 loadMsg : "数据加载中，请稍后……",
   	 columns : [ [ {title : '工单号',field : 'receptionId',width : 150
		}, {title : '车牌',field : 'carLicense',width : 60
		}, {title : '车类型',field : 'ctypeName',width : 60
		}, {title : '进厂日期',field : 'resvRealTime',width : 60
		}, {title : '客户名称',field : 'customName',width : 60
		}, {title : '维修班组',field : 'repgrpName',width : 60
		}, {title : '接待员',field : 'stfName',width : 60
		} ,{title : 'vin号',field : 'carVan',width : 60
		} ,{title : '最后维修里程数',field : 'carLastRepairDistance',width : 60
		} ,{title : '维修类别',field : 'reptName',width : 60
		}] ],
       onDblClickRow:function(rowIndex, rowData){
 	     $('#hiddenRecptionId').val(rowData.receptionId);  
 	     $('#spo_planParts').datagrid("options").url='StOutAction!loadFrtReceptParts.action?receptionId='+rowData.receptionId;
   	     $('#spo_planParts').datagrid("reload");
   	     var data=$('#spo_planParts').datagrid('getRows');
   	     dbClickState='dbClick';
 	   },
 	   onLoadSuccess:function(rowIndex, rowData){
		  var receptionId=$('#receptionId').val();
	      if(trim(receptionId)!=null&&trim(receptionId)!=''){ 
	    	$('#spo_frtReceptionTable').datagrid('selectRow',0);
	      }
   	   }
    });
 
	//计划用料信息
	$('#spo_planParts').datagrid({
		url : '',
		fit:false,
		height:200,
		width:760,
		fitColumns : true,
		pageSize : 1,
		pageList : [ 1,5, 10, 15, 20, 25, 30, 35, 40 ],
		idField : 'rcptpartsIndex',
		singleSelect : false,
		rownumbers : true,
		 columns:[[{field:'ck',checkbox:true
			}, {title : '计划用料编号',field : 'rcptpartsIndex',width : 80,hidden:true
			}, {title : '配件编码',field : 'partsId',width : 80
			}, {title : '配件名称',field : 'partsName',width : 80
			}, {title : '单位',field : 'punitName',width : 50
			}, {title : '适用车型',field : 'fitPtype',width : 50,hidden:true
			}, {title : '含税成本价',field : 'taxCast',width : 70
			}, {title : '未税成本价',field : 'notaxCast',width : 70,hidden:true
			}, {title : '销售价',field : 'itemPrice',width : 80
			}, {title : '计划数量',field : 'itemCountHidden',width : 50,hidden:true
			}, {title : '出库数量',field : 'itemCount',width : 50,
					editor : {
					type : 'numberbox',
					options : {
						required : true,
						min : 0.1,
						precision : 2,
						missingMessage :'出库数量为必填项!'
					}
				}
			}, {title : '含税成本额',field : 'taxCastamont',width : 70,
					editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						precision : 2,
						min : 0.00
					}
				}
			}, {title : '未税成本额',field : 'notaxCastamont',width : 70,
						editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							precision : 2,
							min : 0.00
						}
					}
			}, {title : '销售额',field : 'amount',width : 80,
					editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						precision : 2,
						min : 0.00
					}
				}
			}, {title : '库位',field : 'partsLibrary', width : 40 ,hidden:true
			}, {title : '索赔',field : 'claimsType',width : 70
			}, {title : '索赔id',field : 'claimsId',width : 70,hidden:true
			}, {title : '库存',field : 'partsNowCount',width : 50
			}, {title : '收费性质',field : 'itemCharge',width : 40
			}, {title : '仓库名称',field : 'storeName',width : 80
			}, {title : '仓别Id',field : 'storeId',width : 50,hidden:true
			}, {title : '出库数量',field : 'itemCount1',width : 80,hidden:true,
				editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						precision : 2
					}
			    }
			}, {title : '备注',field : 'outItemRemark',width : 80,
				editor : {
					type : 'text',
					options : {
				        disabled : true,
						precision : 2
					}
				}
			}]],
			onLoadSuccess:function(rowIndex, rowData){
			     if(dbClickState=='dbClick'){
			    	 var rows=$(this).datagrid('getRows');
			    	 if(rows.length==0){
		        	    	alertMsg('抱歉，该工单下没有计划用料信息！', 'warning');
			    	 }
				 }
			     dbClickState='undefined';	
		    },onSelect:function(rowIndex, rowData){
	        	//bindRowsEvent($(this), rowIndex, rowData);
                var rows =$(this).datagrid('getSelections');
                for ( var i = 0; i < rows.length; i++) {
                	 for ( var j = i+1; j < rows.length; j++) {
 						if(rows[i].partsId==rows[j].partsId&&rows[i].storeId==rows[j].storeId){
 							alert('抱歉，该配件已选，同一出库单下，相同配件只能选择一次！', 'warning');
 							$(this).datagrid('unselectRow', rowIndex);
 							$(this).datagrid('unselectRow', rows[i].index);
                            return;
     				    }
 					 }
				}
		    }
	});
 });

 function copyPartsDateAndBindEvent(id, rowIndex, rowData)
 {
		id.datagrid('beginEdit', rowIndex);
		var ed = id.datagrid('getEditors', rowIndex);
		if(ed[0]){
			ed[0].target.numberbox('setValue', rowData.itemCount);
			ed[0].target.select();
			ed[0].target.click(function (){
				ed[0].target.select();
			});
			ed[0].target.keydown(function (e){
				if(e.keyCode == '13'){
					ed[5].target.select();
				}
			});
		}
		if(ed[1])
			ed[1].target.numberbox('setValue', rowData.taxCast);
		if(ed[2])
		    ed[2].target.numberbox('setValue', rowData.notaxCast);
		if(ed[3])
		    ed[3].target.numberbox('setValue', rowData.itemPrice);
		if(ed[4])
		    ed[4].target.numberbox('setValue', rowData.itemCount);
		if(ed[5]){
   			 ed[5].target.select();
			 ed[5].target.click(function (){
					ed[5].target.select();
			 });
			 ed[5].target.keydown(function (e){
				if(e.keyCode == '13'){
					ed[0].target.select();
				}
			 });
   	    }
	    
   		ed[0].target.select();
   		
		ed[0].target.bind('keyup', function() {
			var num = ed[0].target.val();//代表出库数量
			if(parseFloat(num)>0){
				if(parseFloat(num)>parseFloat(rowData.itemCountHidden)){
					alertMsg('抱歉，当前配件的计划数量为【'+rowData.itemCountHidden+'】,你输入的出库数量大于该数量，无法出库！', 'warning');
					ed[0].target.numberbox('setValue', rowData.itemCountHidden);
					num=rowData.itemCountHidden;
				}
			}else{
				alertMsg('抱歉，您输入的数据为非法数据，请重新输入！', 'warning');
				ed[0].target.numberbox('setValue', 1.00);
				num=ed[0].target.val();
			}
			var partsLatestTaxprice = rowData.taxCast;//代表含税价格
			var partsLatestNotaxprice = rowData.notaxCast;//代表未税价格
			var partsSellPrice = rowData.itemPrice;//代表销售价格
			var taxCastamont = accMul(parseFloat(num), parseFloat(partsLatestTaxprice));//含税成本额
			var notaxCastamont = accMul(parseFloat(num), parseFloat(partsLatestNotaxprice));//未税成本额
			var amount = accMul(parseFloat(num), parseFloat(partsSellPrice));//销售额
			ed[1].target.numberbox('setValue', taxCastamont);
			ed[2].target.numberbox('setValue', notaxCastamont);
			ed[3].target.numberbox('setValue', amount);
			ed[4].target.numberbox('setValue', num);
			
		});
    }
    
function query(){
	 $.ajax({   
			type : "POST",
			url : 'StOutAction!searchFrtReceptInfoByCondition.action',
			data : $('#stpreout_selectplanpartsform').serialize(),
			dataType : "json",
			success : function callback(r) {
    	    	$('#spo_frtReceptionTable').datagrid('loadData',r);
            }
     });
}

function _clear(){
	$('#stpreout_selectplanpartsform').form('clear');
	$('#spo_frtReceptionTable').datagrid('load',serializeObject($('#stpreout_selectplanpartsform')));
	$('#spo_planParts').datagrid('loadData', {total:0,rows:[]});
}