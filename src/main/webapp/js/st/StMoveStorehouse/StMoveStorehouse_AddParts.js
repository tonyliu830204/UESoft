$(function (){
		//待选维修配件
		$selectionParts = $('#selectionParts');
		$selectionParts.datagrid({
			url : 'StMoveStoreHouseAction!loadSelectParts.action?storeId='+$('#outStoreId').combobox('getValue'),
			pagination : false,
		    height: 225,
			width: 765,
			fitColumns : true,
			idField : 'changePriceId',
			singleSelect : true,
			rownumbers : true,
				columns : [ [ {title : '调价ID',field : 'changePriceId',width : 50,hidden:true
				} ,{title : '配件编码',field : 'partsId',width : 30
				}, {title : '配件名称',field : 'partsName',width : 50
				}, {title : '单位',field : 'punitName',width : 50
				}, {title : '库存量',field : 'partsNowCount',width : 50
				}, {title : '未税价',field : 'notaxCast',width : 50
				}, {title : '库位',field : 'partsLibrary',width : 50
				}, {title : '仓库Id',field : 'storeId',width : 50,hidden:true
				}, {title : '仓库',field : 'storeName',width : 50
				}] ],
				onClickRow : function (rowIndex, rowData){
				   $(this).datagrid('unselectRow', rowIndex);
				},
				onDblClickRow : function(rowIndex, rowData){
					var rows = $selectedParts.datagrid('getRows');
					if(rows.length){
						for(var i = 0;  i < rows.length; i++){
							if(rows[i].storeId == rowData.storeId && rows[i].partsId == rowData.partsId){  
							       $.messager.show({title : '提示',msg : '该配件信息已经被选取！',showType : 'slide'});
								return;
							}
						}
					}
					$(this).datagrid('deleteRow', rowIndex);
					$selectedParts.datagrid('appendRow', rowData);
					$('#selectedPartsTotal').html('共' + $selectedParts.datagrid('getData').total + '条记录');
					var index = $selectedParts.datagrid('getRowIndex', rowData);
					copyPartsDateAndBindEvent($selectedParts, index, rowData);
				}
		});
		
		//已选配件
		$selectedParts = $('#selectedParts');
		
		$selectedParts.datagrid({
		    url:'StMoveStoreHouseAction!loadSelectedParts.action',//CaiGouDanAction_loadSelectedParts
			height: 120,
			fitColumns : true,
			idField : 'id',
			rownumbers : true,
				columns : [ [ {title : '调价ID',field : 'changePriceId',width : 50,hidden:true
				} ,{title : '配件编码',field : 'partsId',width : 50
				} , {title : '配件名称',field : 'partsName',width : 50
				}, {title : '单位',field : 'punitName',width : 50
				}, {title : '库存量',field : 'partsNowCount',width : 50
				}, {title : '数量',field : 'msdCnt',width : 50,
					editor : {
						type : 'numberbox',
						options : {
							required : true,
							min : 0.1,
							precision : 2,
							missingMessage : "移仓数量为必填项!"
						}
					}
				},{field : 'notaxCast',title : '未税价',width : 60,
					editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							precision : 2
						}
					}
				},{field : 'msdNocastAmont',title : '金额',width : 60,
					editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							precision : 2
						}
					}
				}, {title : '库位',field : 'partsLibrary',width : 50
				}, {title : '仓库Id',field : 'storeId',width : 50,hidden:true
				}, {title : '数量',field : 'msdCnt1',width : 50,
					hidden:true,
					editor : {
						type : 'numberbox',
						options : {
							min : 0.1,
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
						$selectionParts.datagrid('appendRow', rowData);
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
   				});
   			}
   			if(ed[2]){
   				ed[2].target.numberbox('setValue', ed[1].target.val());
   			}
   			ed[0].target.select();
			ed[0].target.bind('keyup', function() {
				ed[3].target.numberbox('setValue', '3.014159266267659');
				var num = ed[0].target.val();//代表采购数量
				if(num==null||num==''){
					num=1;
					ed[0].target.numberbox(num);
				}
				var nocastprice=ed[1].target.val();
				var nocastamont=accMul(parseFloat(num), parseFloat(nocastprice));
				ed[2].target.numberbox('setValue', nocastamont);
				if(parseFloat(ed[0].target.val())<=0){
					alertMsg('移仓数量不能小于等于零！', 'warning');
					ed[0].target.numberbox('setValue', '1.0');
				}
				if(parseFloat(ed[0].target.val())>parseFloat(rowData.partsNowCount)){
					alertMsg('移仓数量不能大于库存量！', 'warning');
					ed[0].target.numberbox('setValue', rowData.partsNowCount);
				}
				ed[3].target.numberbox('setValue', ed[0].target.val());
			});
			
	        ed[3].target.numberbox({
				onChange : function (newValue, oldValue){
	        	     ed[2].target.numberbox('setValue', accMul(parseFloat(ed[0].target.val()),parseFloat(ed[1].target.val())));
				}
		    });
	   }
	
		var query = function (){
			$selectionParts.datagrid('load', serializeObject($('#stmovestorhouse_partsselectform')));
		}
		
		var _clear = function (){
			$('#stmovestorhouse_partsselectform').form('clear');
			$selectionParts.datagrid('load', serializeObject($('#stmovestorhouse_partsselectform')));
		}