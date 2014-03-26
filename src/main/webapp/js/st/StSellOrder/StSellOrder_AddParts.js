$(function (){
		
		//待选维修配件
		$so_selectionParts = $('#so_selectionParts');
		$so_selectionParts.datagrid({
			url : 'StSellOrderAction!loadFrtParts.action?'+$('#stSellOrderMainForm').serialize(),
			pagination : false,
		    height: 225,
			width: 765,
			fitColumns : true,
			idField : 'id',
			singleSelect : true,
			rownumbers : true,
				columns : [ [ {title : '调价ID',field : 'changePriceId',width : 50,hidden:true
				} ,{title : '配件编码',field : 'partsId',width : 60
				}, {title : '配件名称',field : 'partsName',width : 50
				}, {title : '单位',field : 'punitName',width : 50
				}, {title : '适用车型',field : 'fitPtype',width : 50
				}, {title : '含税成本',field : 'taxCostPrice',width : 50
				}, {title : '销售价',field : 'selldPrice',width : 50
				}, {title : '库存量',field : 'partsNowCount',width : 50
				}, {title : '库位',field : 'partsLibrary',width : 50
				}, {title : '仓别',field : 'storeName',width : 50
				}, {title : '仓别Id',field : 'storeId',width : 50,hidden:true
				}] ],
				onClickRow : function (rowIndex, rowData){
				   $(this).datagrid('unselectRow', rowIndex);
				},
				onDblClickRow : function(rowIndex, rowData){
					var rows = $so_selectedParts.datagrid('getRows');
					if(rows.length){
						for(var i = 0;  i < rows.length; i++){
							if(rows[i].partsId == rowData.partsId&&rows[i].storeName==rowData.storeName){  
							    $.messager.show({title : '提示',msg : '对不起，你点击的配件已经存在！',showType : 'slide'}); 
								return;
							}
						}
					}
					if(rowData.selldPrice!=null&&rowData.selldPrice!=''&&rowData.selldPrice!='undefined'){
						$(this).datagrid('deleteRow', rowIndex);
						$so_selectedParts.datagrid('appendRow', rowData);
						$('#selectedPartsTotal').html('共' + $so_selectedParts.datagrid('getData').total + '条记录');
						var index = $so_selectedParts.datagrid('getRowIndex', rowData);
						copyPartsDateAndBindEvent($so_selectedParts, index, rowData);
					}else{
						alertMsg('抱歉，你选择的配件无销售价格，此配件当前不能销售！', 'warning');
					}
				},
				onLoadSuccess : function (data){
					if($('#sellmmId').val()!=null&&$('#sellmmId').val()!=''&&$('#sellmmId').val()!='undefined'){
						$so_selectedParts.datagrid('load',$('#stSellOrderMainForm').serialize())
					}else{
						var data=$('#slo_selldOrderItemTable').datagrid('getData');
						$so_selectedParts.datagrid('loadData',data);
					}
				}
		});
		
		//已选配件
		$so_selectedParts = $('#so_selectedParts');
		$so_selectedParts.datagrid({
		    url:'',
			height: 120,
			fitColumns : true,
			idField : 'id',
			rownumbers : true,
				columns : [ [ {title : '调价ID',field : 'changePriceId',width : 50,hidden:true
				} ,{title : '配件编码',field : 'partsId',width : 50
				} , {title : '配件名称',field : 'partsName',width : 50
				}, {title : '适合车型',field : 'fitPtype',width : 50
				}, {title : '单位',field : 'punitName',width : 50
				}, {title : '含税成本',field : 'taxCostPrice',width : 50
				}, {title : '销售价',field : 'selldPrice',width : 50,
					editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							precision : 2,
							min : 0.00
						}
					}
				},{field : 'selldCnt',title : '销售数量',width : 60,
					editor : {
						type : 'numberbox',
						options : {
							required : true,
							min : 0.1,
							precision : 2,
							missingMessage : "销售数量为必填项!"
						}
					}
				}, {title : '含税成本额',field : 'taxCostPriceAmont',width : 50,
					editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							precision : 2,
							min : 0.00
						}
					}
				}, {title : '销售金额',field : 'selldAmount',width : 50,
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
				}] ],
				onClickRow : function (rowIndex, rowData){
					$(this).datagrid('unselectRow', rowIndex);
					var ed = $(this).datagrid('getEditors', rowIndex);
					if(ed[1]){
					    ed[1].target.select();
					}
				},
				onDblClickRow : function(rowIndex, rowData){
					var ed = $(this).datagrid('getEditors', rowIndex);
					if(ed.length){
						$(this).datagrid('deleteRow', rowIndex);
						$('#selectedPartsTotal').html('共' + $(this).datagrid('getRows').length + '条记录');
						$so_selectionParts.datagrid('appendRow', rowData);
					}
				},
				onLoadSuccess : function (data){
					$('#selectedPartsTotal').html('共' + data.total + '条记录');
				}
		  });
		  
		  $so_selectedParts.datagrid('loadData', detailData);
		  
	   });
	
	   function copyPartsDateAndBindEvent(id, rowIndex, rowData)
       {
    			id.datagrid('beginEdit', rowIndex);
    			var ed = id.datagrid('getEditors', rowIndex);
    			if($('#priceQuotiety').val()!=null&&$('#priceQuotiety').val()!=''){
    				ed[0].target.numberbox('setValue', rowData.taxCostPrice);
            	}
    			if(ed[1]){
    				ed[1].target.numberbox('setValue', 1);
    				ed[1].target.select();
    				ed[1].target.click(function (){
    					ed[1].target.select();
    				});
    				ed[1].target.keydown(function (e){
    					if(e.keyCode == '13'){
	    						if(rowIndex < id.datagrid('getData').total - 1){
	    							var ed = id.datagrid('getEditors', rowIndex + 1);
	    							ed[1].target.select();
	    						}else{
	    							var ed = id.datagrid('getEditors', 0);
	    							ed[1].target.select();
	    						}
    				     }
    			    });
    			}
    			if(ed[2])
    				ed[2].target.numberbox('setValue', rowData.taxCostPrice);
	   			if(ed[3])
	   			    ed[3].target.numberbox('setValue', rowData.selldPrice);
	   			ed[1].target.select();
				ed[1].target.bind('keyup', function() {
					var num = ed[1].target.val();//代表出库数量
					if(parseFloat(ed[1].target.val())<=0||ed[1].target.val()==null||ed[1].target.val()==''||isNaN(ed[1].target.val())){
						alertMsg('输入销售数量为不符合条件数据，请重新输入！', 'warning');
						ed[1].target.numberbox('setValue', 1);
						num=rowData.selldCnt
					}else{
						if(parseFloat(ed[1].target.val())<= parseFloat(rowData.partsNowCount)&&parseFloat(ed[1].target.val())!=0){
							var priceQuotiety=$('#priceQuotiety').val();
							if(priceQuotiety!=null&&priceQuotiety!=''&&!isNaN( priceQuotiety )){
								if(parseInt(priceQuotiety)>=0){
									var selldCostPrice = rowData.taxCostPrice;//代表含税成本价格
									var selldPrice = rowData.selldPrice;//代表销售成本价格
									var taxCostPriceAmont = accMul(parseFloat(num), accMul(parseFloat(selldCostPrice),parseInt(priceQuotiety)));//成本金额
									ed[0].target.numberbox('setValue', selldCostPrice);//赋给销售价格列
									ed[2].target.numberbox('setValue', taxCostPriceAmont);//赋给成本额列
									ed[3].target.numberbox('setValue', taxCostPriceAmont);//赋给销售成本额列
								}
							}else{
								var selldCostPrice = rowData.taxCostPrice;//代表含税成本价格
								var selldPrice = rowData.selldPrice;//代表销售成本价格
								var taxCostPriceAmont = accMul(parseFloat(num), parseFloat(selldCostPrice));//自定义计算总额方法
								var selldPriceAmont = accMul(parseFloat(num), parseFloat(selldPrice));//自定义计算总额方法
								ed[2].target.numberbox('setValue', taxCostPriceAmont);//赋给指定列
								ed[3].target.numberbox('setValue', selldPriceAmont);//赋给指定列
							}
					    }else{
					    	ed[1].target.numberbox('setValue', 1);
							alertMsg('销售数量不能大于当前系统库存量或为零，请确认后重新填写销售数量！', 'warning');
						}
						
				    }
			    });
				if($('#priceQuotiety').val()!=null&&$('#priceQuotiety').val()!=''&&!isNaN( $('#priceQuotiety').val() )){
					if(parseInt($('#priceQuotiety').val())>=0){
						ed[2].target.numberbox('setValue', accMul(parseFloat($('#priceQuotiety').val()), rowData.taxCostPrice));
	    				ed[3].target.numberbox('setValue', accMul(parseFloat($('#priceQuotiety').val()), rowData.taxCostPrice));
					}
				}
	    }
	
		var query = function (){
			$so_selectionParts.datagrid('load', serializeObject($('#sso_selectionPartsForm')));
		}
		
		var _clear = function (){
			$('#sso_selectionPartsForm').form('clear');
			$so_selectionParts.datagrid('load', serializeObject($('#sso_selectionPartsForm')));
		}