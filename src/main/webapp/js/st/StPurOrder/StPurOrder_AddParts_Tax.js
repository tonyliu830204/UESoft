$(function (){
		//待选维修配件
$selectionParts = $('#selectionParts');
$selectionParts.datagrid({
	url : 'StPurOrderAction!loadSelectParts.action',
	pagination : false,
    height: 225,
	width: 765,
	sortName:'partsId',
	sortOrder:'asc',
	fitColumns : true,
	pageSize : 10,
	pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	idField : 'id',
	singleSelect : true,
	rownumbers : true,
		columns : [ [ {title : '配件编码',field : 'partsId',width : 80,sortable:true
		} ,{title : '配件编码2',field : 'partsId2',width : 80,sortable:true
		}, {title : '配件名称',field : 'partsName',width : 80,sortable:true
		}, {title : '配件品牌',field : 'pbrdName',width : 80,sortable:true
		}, {title : '配件型号',field : 'ptypeName',width : 80,sortable:true
		}, {title : '适合车型',field : 'fitPtype',width : 50,sortable:true
		}, {title : '产地',field : 'stateName',width : 50,sortable:true
		}, {title : '单位',field : 'punitName',width : 50,sortable:true
		}, {title : '库位',field : 'partsLibrary',width : 50,sortable:true}
		] ],
		onClickRow : function (rowIndex, rowData){
		   $(this).datagrid('unselectRow', rowIndex);
		},
		onDblClickRow : function(rowIndex, rowData){
			var rows = $selectedParts.datagrid('getRows');
			if(rows.length){
				for(var i = 0;  i < rows.length; i++){
					if(rows[i].partsId == rowData.partsId){  
						alertMsg('抱歉，配件【'+rows[i].partsName+'】信息已经被选取！', 'warning');
						return;
					}
				}
			}
			$selectedParts.datagrid('appendRow', rowData);
			$('#selectedPartsTotal').html('共' + $selectedParts.datagrid('getData').total + '条记录');
			var index = $selectedParts.datagrid('getRowIndex', rowData);
			copyPartsDateAndBindEvent($selectedParts, index, rowData);
		}
});

//已选配件
$selectedParts = $('#selectedParts');
$selectedParts.datagrid({
    url:'',
	height: 120,
	fitColumns : true,
	idField : 'partsId',
	singleSelect : true,
	rownumbers : true,
		columns : [ [{title : '配件编码',field : 'partsId',width : 80
		},{title : '配件编码2',field : 'partsId2',width : 80,hidden:true
		} , {title : '配件名称',field : 'partsName',width : 80
		}, {title : '配件品牌',field : 'pbrdName',width : 80,hidden:true
		}, {title : '配件型号',field : 'ptypeName',width : 80
		}, {title : '适合车型',field : 'fitPtype',width : 50,hidden:true
		}, {title : '产地',field : 'stateName',width : 50,hidden:true
		}, {title : '单位',field : 'punitName',width : 50
		},{field : 'partsNum',title : '采购数量',width : 60,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 0.01,
					precision : 2,
					validType:'length[1,12]',
					missingMessage : '采购数量为必填项!'
				}
			}
		},{field : 'notaxPrice',title : '未税价',width : 60,
			editor : {
			type : 'numberbox',
			options : {
		        precision : 2,
		        disabled : true,
				min : 0.01
				
			}
		}
		},{field : 'notaxTotalamont',title : '未税金额',width : 60,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2,
					min : 0.01
				}
			}
		},{field : 'taxPrice',title : '含税价',width : 60,
			editor : {
			type : 'numberbox',
			options : {
		        precision : 2,
				required : true,
				validType:'length[1,12]',
				min : 0.01
				
			}
		}
		},{field : 'taxTotalamont',title : '含税金额',width : 60,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2,
					min : 0.01
				}
			}
		},{field : 'taxCount',title : '税额',width : 60,
			editor : {
			type : 'numberbox',
			options : {
				disabled : true,
				precision : 2,
				min : 0.01
			}
		}
	    }, {title : '库位',field :'partsLibrary',width : 50
	    }, {title : '采购备注',field :'itemRemark',width : 50,
			editor : {
				type : 'text',
				options : {
					validType:'length[1,100]',
				}
	        }}]],
		onDblClickRow : function(rowIndex, rowData){
			var ed = $(this).datagrid('getEditors', rowIndex);
			if(ed.length){
				$(this).datagrid('deleteRow', rowIndex);
				$('#selectedPartsTotal').html('共' + $(this).datagrid('getRows').length + '条记录');
			}
		},
		onLoadSuccess : function (data){
			$('#selectedPartsTotal').html('共' + data.total + '条记录');
		}
  });
  $selectedParts.datagrid('loadData',detailData);
  });

   function copyPartsDateAndBindEvent(id, rowIndex, rowData)
   {
	   
		var taxRate = $('#taxRate').val();
		if(taxRate==null|| taxRate==''){
			taxRate=17;
		}
		taxRate = parseFloat(taxRate)/100;
		taxRate=accAdd(taxRate,1);
        id.datagrid('beginEdit', rowIndex);
		var ed = id.datagrid('getEditors', rowIndex);
		if(ed[0]){
			if(rowData.partsNum==null||rowData.partsNum==''){
				ed[0].target.numberbox('setValue', '1.00');
			}else{
				ed[0].target.numberbox('setValue', rowData.partsNum);
			}
			ed[0].target.select();
			ed[0].target.click(function (){
				ed[0].target.select();
			});
			ed[0].target.keydown(function (e){
				if(e.keyCode == '13'){
					ed[3].target.select();
				}
			});
		}
		if(ed[1]){
			if(rowData.notaxPrice==null||rowData.notaxPrice==''){
				ed[1].target.numberbox('setValue', '1.00');
			}else{
				ed[1].target.numberbox('setValue', rowData.notaxPrice);
			}
		}
		if(ed[2]){
			if(rowData.notaxTotalamont==null||rowData.notaxTotalamont==''){
				ed[2].target.numberbox('setValue', accMul(parseFloat(ed[0].target.val()),parseFloat(ed[1].target.val())));
			}else{
				ed[2].target.numberbox('setValue', rowData.notaxTotalamont);
			}
   		}
		if(ed[3]){
            if(rowData.taxPrice==null||rowData.taxPrice==''){
				ed[3].target.numberbox('setValue', '1.17');
			}else{
				ed[3].target.numberbox('setValue', rowData.taxPrice);
			}
            ed[3].target.click(function (){
				ed[3].target.select();
			});
			ed[3].target.keydown(function (e){
				if(e.keyCode == '13'){
					ed[6].target.select();
				}
			});
   		}
		if(ed[4]){
            if(rowData.taxTotalamont==null||rowData.taxTotalamont==''){
				ed[4].target.numberbox('setValue', '1.17');
			}else{
				ed[4].target.numberbox('setValue', rowData.taxTotalamont);
			}
   		}	  
		if(ed[5]){
   		 	 if(rowData.taxCount==null||rowData.taxCount==''){
				ed[5].target.numberbox('setValue', 0.17);
			 }else{
				ed[5].target.numberbox('setValue', rowData.taxCount);
			 }
   		}	  
		if(ed[6]){
   		 	 if(rowData.itemRemark==null||rowData.itemRemark==''){
				ed[6].target.text('setValue', '');
			 }else{
				ed[6].target.text('setValue', rowData.itemRemark);
			 }
			 ed[6].target.click(function (){
				ed[6].target.select();
			 });
			 ed[6].target.keydown(function (e){
				if(e.keyCode == '13'){
					if(rowIndex < id.datagrid('getData').total - 1){
						id.datagrid('endEdit', rowIndex);
						id.datagrid('acceptChanges');
						id.datagrid('selectRow', rowIndex + 1);
						copyPartsDateAndBindEvent(id, rowIndex + 1, id.datagrid('getSelected'));
					}else{
						id.datagrid('endEdit', rowIndex);
						id.datagrid('acceptChanges');
						id.datagrid('selectRow',0);
						var rowData=id.datagrid('getSelected');
						copyPartsDateAndBindEvent(id, 0, rowData);
					}
				}
			 });
   		}	  
		ed[0].target.select();
		ed[0].target.bind('blur', function() {
			var num = ed[0].target.val();
			if(num==null||num==''||parseFloat(num)<=0||isNaN(num)){
				alertMsg('采购数量不能为空,非法字符或者小于等于零！', 'warning');
				ed[0].target.numberbox('setValue', '1.00');
				num=1.00;
			}
			var taxPrice = ed[3].target.val();
			if(taxPrice==null||taxPrice==''||parseFloat(taxPrice)<=0||isNaN(taxPrice)){
				alertMsg('含税金额不能为空,非法字符或者小于等于零！', 'warning');
				ed[3].target.numberbox('setValue', '1.17');
				taxPrice=1.17;
		    }
			var taxAmount = accMul(parseFloat(num), parseFloat(taxPrice));//自定义计算总额方法
			ed[4].target.numberbox('setValue', taxAmount);//赋给指定列
            var noTaxPrice=accDiv(parseFloat(taxPrice),parseFloat(taxRate));
            ed[1].target.numberbox('setValue', noTaxPrice);//赋给指定列
            var noTaxAmount=accMul(parseFloat(num),parseFloat(noTaxPrice));
            ed[2].target.numberbox('setValue', noTaxAmount);//赋给指定列
            var taxCount=accSub(parseFloat(taxAmount),parseFloat(noTaxAmount));
            ed[5].target.numberbox('setValue', taxCount);//赋给指定列
		});
		ed[3].target.bind('blur', function() {
			var num = ed[0].target.val();
			if(num==null||num==''||parseFloat(num)<=0||isNaN(num)){
				alertMsg('采购数量不能为空,非法字符或者小于等于零！', 'warning');
				ed[0].target.numberbox('setValue', '1.00');
				num=1.00;
			}
			var taxPrice = ed[3].target.val();
			if(taxPrice==null||taxPrice==''||parseFloat(taxPrice)<=0||isNaN(taxPrice)){
				alertMsg('采购金额不能为空,非法字符或者小于等于零！', 'warning');
				ed[3].target.numberbox('setValue', '1.17');
				taxPrice=1.17;
		    }
			var taxAmount = accMul(parseFloat(num), parseFloat(taxPrice));//自定义计算总额方法
			ed[4].target.numberbox('setValue', taxAmount);//赋给指定列
            var noTaxPrice=accDiv(parseFloat(taxPrice),parseFloat(taxRate));
            ed[1].target.numberbox('setValue', noTaxPrice);//赋给指定列
            var noTaxAmount=accMul(parseFloat(num),parseFloat(noTaxPrice));
            ed[2].target.numberbox('setValue', noTaxAmount);//赋给指定列
            var taxCount=accSub(parseFloat(taxAmount),parseFloat(noTaxAmount));
            ed[5].target.numberbox('setValue', taxCount);//赋给指定列
		});
}

var query = function (){
	$selectionParts.datagrid('load', serializeObject($('#stpurorder_selectpartsform')));
}

var _clear = function (){
	$('#stpurorder_selectpartsform').form('clear');
	$selectionParts.datagrid('load', serializeObject($('#stpurorder_selectpartsform')));
}