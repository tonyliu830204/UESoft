$(function (){
		//待选维修配件
$spo_selectionParts = $('#spo_selectionParts');
$spo_selectionParts.datagrid({
	url : 'StOutAction!loadFrtParts.action',
	pagination : false,
    height: 225,
	width: 765,
	fitColumns : true,
	idField : 'id',
	singleSelect : true,
	rownumbers : true,
		columns : [ [ {title : '调价ID',field : 'changePriceId',width : 50,hidden:true
		} ,{title : '配件编码',field : 'partsId',width : 30
		}, {title : '配件名称',field : 'partsName',width : 50
		}, {title : '单位',field : 'punitName',width : 50
		}, {title : '适合车型',field : 'fitPtype',width : 50
		}, {title : '含税成本',field : 'taxCast',width : 50
		}, {title : '未税成本',field : 'noTaxCast',width : 50
		}, {title : '销售价',field : 'itemPrice',width : 50
		}, {title : '库存量',field : 'partsNowCount',width : 50
		}, {title : '库位',field : 'partsLibrary',width : 50
		}, {title : '仓别',field : 'storeName',width : 50
		}] ],
		onClickRow : function (rowIndex, rowData){
		   $(this).datagrid('unselectRow', rowIndex);
		},
		onDblClickRow : function(rowIndex, rowData){
			var rows = $spo_selectedParts.datagrid('getRows');
			if(rows.length){
				for(var i = 0;  i < rows.length; i++){
					if(rows[i].partsId == rowData.partsId&&rows[i].storeName == rowData.storeName){  
					       $.messager.show({
								title : '提示',
								msg : '该配件信息已经被选取！',
								showType : 'slide'
							}); 
						return;
					}
				}
			}
			$(this).datagrid('deleteRow', rowIndex);
			$spo_selectedParts.datagrid('appendRow', rowData);
			$('#selectedPartsTotal').html('共' + $spo_selectedParts.datagrid('getData').total + '条记录');
			var index = $spo_selectedParts.datagrid('getRowIndex', rowData);
			copyPartsDateAndBindEvent($spo_selectedParts, index, rowData);
		}
});

//已选配件
$spo_selectedParts = $('#spo_selectedParts');

$spo_selectedParts.datagrid({
    url:'StOutAction!loadStPreOutItem.action',
	height: 120,
	fitColumns : true,
	idField : 'id',
	rownumbers : true,
		columns : [ [ {title : '调价ID',field : 'changePriceId',width : 50,hidden:true
		} ,{title : '配件编码',field : 'partsId',width : 50
		} , {title : '配件名称',field : 'partsName',width : 50
		}, {title : '适合车型',field : 'fitPtype',width : 50
		}, {title : '单位',field : 'punitName',width : 50
		}, {title : '含税成本',field : 'taxCast',width : 50
		}, {title : '未税成本',field : 'noTaxCast',width : 50
		}, {title : '销售价',field : 'itemPrice',width : 50
		},{field : 'itemCount',title : '出库数量',width : 60,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1,
					missingMessage : "采购数量为必填项!"
				}
			}
		}, {title : '含税金额',field : 'taxCastAmont',width : 50,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2,
					min : 0.00
				}
			}
		}, {title : '未税金额',field : 'noTaxCastAmont',width : 50,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2,
					min : 0.00
				}
			}
		}, {title : '销售金额',field : 'amount',width : 50,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2,
					min : 0.00
				}
			}
		}, {title : '库存量',field : 'partsNowCount',width : 50
		}, {title : '库位',field : 'partsLibrary',width : 50
		}, {title : '仓别',field : 'storeName',width : 50
		},{field : 'itemCount1',title : '参考数量',width : 60,hidden:true,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2
				}
		}
		},{title : '索赔',field : 'claimsType',width : 40,
				editor : {
					type : 'combobox',
					options : {
					url : 'StOutAction!loadClaimsType.action',
					mode : 'remote',
					width:40,
					valueField:'id',
				    textField:'text'
					}
				}
		},{title : '收费性质',field : 'itemCharge',width : 40,
			editor : {
				type : 'text',
				options : {
			        disabled : true,
					precision : 2
				}
			}
		}] ],
		onClickRow : function (rowIndex, rowData){
			$(this).datagrid('unselectRow', rowIndex);
			var ed = $(this).datagrid('getEditors', rowIndex);
			if(ed[0]){
			    ed[0].target.select();
			}
		},
		onDblClickRow : function(rowIndex, rowData){
			var ed = $(this).datagrid('getEditors', rowIndex);
			if(ed.length){
				$(this).datagrid('deleteRow', rowIndex);
				$('#selectedPartsTotal').html('共' + $(this).datagrid('getRows').length + '条记录');
				$spo_selectionParts.datagrid('appendRow', rowData);
			}
		},
		onLoadSuccess : function (data){
			$('#selectedPartsTotal').html('共' + data.total + '条记录');
			}
	  });
  });

   function copyPartsDateAndBindEvent(id, rowIndex, rowData)
   {
			id.datagrid('beginEdit', rowIndex);
		var ed = id.datagrid('getEditors', rowIndex);
		if(ed[0]){
			ed[0].target.numberbox('setValue', '1');
			ed[0].target.select();
			ed[0].target.click(function (){
				ed[0].target.select();
			});
			ed[0].target.keydown(function (e){
				if(e.keyCode == '13'){
					if(rowIndex < id.datagrid('getData').total - 1){
						var ed = id.datagrid('getEditors', rowIndex + 1);
						ed[0].target.select();
					}else{
						var ed = id.datagrid('getEditors', 0);
						ed[0].target.select();
					}
				}
		   ed[5].target.select();
			});
		}
		if(ed[1])
			ed[1].target.numberbox('setValue', rowData.taxCast);
		if(ed[2])
		    ed[2].target.numberbox('setValue', rowData.noTaxCast);
		if(ed[3])
		    ed[3].target.numberbox('setValue', rowData.itemPrice);
		if(ed[4])
		    ed[4].target.numberbox('setValue', 1);
		if(ed[5]){
		    ed[5].target.select();
		}
		ed[0].target.select();
		ed[0].target.bind('keyup', function() {
		var num = ed[0].target.val();//代表出库数量
		var partsLatestTaxprice = rowData.taxCast;//代表出库价格
		var partsLatestNotaxprice = rowData.noTaxCast;//代表出库价格
		var partsSellPrice = rowData.itemPrice;//代表出库价格
		var amount = accMul(parseFloat(num), parseFloat(partsLatestTaxprice));//自定义计算总额方法
		var taxCastamont = accMul(parseFloat(num), parseFloat(partsLatestNotaxprice));//自定义计算总额方法
		var notaxCastamont = accMul(parseFloat(num), parseFloat(partsSellPrice));//自定义计算总额方法
		ed[1].target.numberbox('setValue', amount);//赋给指定列
		ed[2].target.numberbox('setValue', taxCastamont);//赋给指定列
		ed[3].target.numberbox('setValue', notaxCastamont);//赋给指定列
		ed[4].target.numberbox('setValue', num);//赋给指定列
		if(rowData && rowData.partsNum){
			if(parseInt(rowData.partsNum)<0)
			{
				alertMsg('采购数量不能为负数', 'warning');
				ed[0].target.numberbox('setValue', rowData.partsNum);
			}
		}
	});
	if(ed[5])
	{
		 ed[5].target.combobox({
	  		 width:40,
	  		 onSelect:function(record){
	           ed[6].target.val(record.text);
            }
	  	  })
	}
}

var query = function (){
	$spo_selectionParts.datagrid('load', serializeObject($('#stpreout_partsselectform')));
}

var _clear = function (){
	$('#stpreout_partsselectform').form('clear');
	$spo_selectionParts.datagrid('load', serializeObject($('#stpreout_partsselectform')));
}