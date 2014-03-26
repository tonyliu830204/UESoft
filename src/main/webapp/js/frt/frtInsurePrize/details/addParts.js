function LoadOk() {
		if (document.readyState == "complete") {
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : 'frtOptionsAction!getDefaultBillPrictType.action',
				success : function(r) {
					$('#param').val(r);
					runs();
				},
				error : function (r){
				   if(r.readyState == '0' && r.status == '0'){
					   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
				   }
			   }
			});
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	setTimeout("LoadOk();", 200);
	var runs=function (){
		var url='frtPartsQueryAction!findAllParts.action';
		if($('#param').val()==null||$('#param').val()==''){
			url+='?partsId=-1';
			 alertMsg('对不起，数据加载失败，请重新打开!', 'warning');
		}else{
				url+='?param='+$('#param').val();
		}
		//待选维修配件
		$selectionParts = $('#selectionParts');
		$selectionParts.datagrid({
			url : url,
			height: 225,
			//width: 765,
			fitColumns : true,
			idField : 'id',
			singleSelect : true,
			rownumbers : true,
			pagination:true,
			columns : [[{
				field : 'partsId',
				title : '配件编码',
				width : 60,
				sortable : true
			},{
				field : 'partsName',
				title : '配件名称',
				width : 60,
				sortable : true
			},{
				field : 'pbrdId',
				title : '配件品牌',
				width : 60,
				formatter : function (value,row,index){
					return row.pbrdName;
				},
				sortable : true
			},{
				field : 'ptypeId',
				title : '配件型号',
				width : 60,
				formatter : function (value,row,index){
					return row.ptypeName;
				},
				sortable : true
			},{
				field : 'fitPtype',
				title : '适合车型',
				width : 150,
				formatter : function (value,row,index){
					return row.fitPtypeName;
				},
				sortable : true
			},{
				field : 'punitId',
				title : '单位',
				width : 60,
				formatter : function (value,row,index){
					return row.punitName;
				},
				sortable : true
			},{
				field : 'punitName',
				title : '单位',
				width : 60,
				hidden : true
			},{
				field : 'partsNowCount',
				title : '库存数量',
				width : 60,
				sortable : true
			},{
				field : 'partsRepairPrice',
				title : '维修价',
				width : 60,
				sortable : true
			},{
				field : 'storeId',
				title : '仓别',
				width : 60,
				formatter : function (value,row,index){
					return row.storeName;
				},
				sortable : true
			},{
				field : 'storeName',
				title : '仓别',
				width : 60,
				hidden : true
			},{
				field : 'partsRemark',
				title : '备注',
				width : 60
			}]],
			onClickRow : function (rowIndex, rowData){
				$(this).datagrid('unselectRow', rowIndex);
			},
			onDblClickRow : function(rowIndex, rowData){
				var rows = $selectedParts.datagrid('getRows');
				if(rows.length){
					for(var i = 0;  i < rows.length; i++){
						if(rows[i].partsId == rowData.partsId&&rows[i].storeId == rowData.storeId){
							return;
						}
					}
				}
				$(this).datagrid('deleteRow', rowIndex);
				$selectedParts.datagrid('appendRow', rowData);
				var index = $selectedParts.datagrid('getRowIndex', rowData);
				copyDateAndBindEvent($selectedParts, index, rowData,false);
			}
		});
		
		//已选配件
		$selectedParts = $('#selectedParts');
		
		$selectedParts.datagrid({
			url:'',
			//fit : true,
			height: 120,
			fitColumns : true,
			idField : 'id',
			rownumbers : true,
			columns : [[{
					field : 'partsId',
					title : '配件编码',
					width : 60
				},{
					field : 'partsName',
					title : '配件名称',
					width : 60
				},{
					field : 'punitId',
					title : '单位',
					width : 60,
					formatter : function (value,row,index){
						return row.punitName;
					}
				},{
					field : 'punitName',
					title : '单位',
					width : 60,
					hidden : true
				},{
					field : 'partsNum',
					title : '数量',
					width : 60,
					editor : {
						type : 'numberbox',
						options : {
							precision : 2,	
							required : true,
							min : 1,
							missingMessage : "数量为必填项!"
						}
					}
				},{
					field : 'partsRepairPrice',
					title : '单价',
					width : 60,
					editor : {
						type : 'numberbox',
						options : {
							precision : 2,
							min : 0,
							required : true,
							missingMessage : "单价为必填项!"
						}
					}
				},{
					field : 'partsAmount',
					title : '金额',
					width : 60,
					editor : {
						type : 'numberbox',
						options : {
							disabled : true,
							precision : 2,
							min : 0
						}
					}
				},{
					field : 'storeId',
					title : '仓别',
					width : 60,
					formatter : function (value,row,index){
						return row.storeName;
					},
					hidden : true
				},{
					field : 'storeName',
					title : '仓别',
					width : 60,
					hidden : true
				},{
					field : 'posName',
					title : '部位名称',
					width : 60,
					hidden : true
				},{
					field : 'partsNowCount',
					title : '库存数量',
					width : 60,
					hidden : true
				}]],
			onClickRow : function (rowIndex, rowData){
				bindEnterInCloumn($selectedParts, rowIndex, 0);
			},
			onDblClickRow : function(rowIndex, rowData){
				var ed = $(this).datagrid('getEditors', rowIndex);
				if(ed.length){
					$(this).datagrid('deleteRow', rowIndex);
					$selectionParts.datagrid('appendRow', rowData);
				}
			}
		});
		//$('#selectedParts').datagrid('loadData',staticFrtInsurePrizeParts);
	}
	
	var _query = function (){
		$selectionParts.datagrid('load', serializeObject($('#selectionPartsForm')));
	}
	
	var _clear = function (){
		$('#selectionPartsForm').form('clear');
		$selectionParts.datagrid('load', serializeObject($('#selectionPartsForm')));
	}