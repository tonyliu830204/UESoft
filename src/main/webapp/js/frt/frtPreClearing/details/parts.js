var numberBit;
function LoadOk() {
	if (document.readyState == "complete") {
		$.ajax({
			type: 'post',
			dataType: 'json',
			async:true,
			url:projectPath+'basCompanyInformationSetAction!loadNumberbit.action',  
			success: function(r){
			numberBit=r.ciValue;
			initFrame();	
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
function initFrame() {
	if(preclrId){
		$('#frtPreClearingPartsDatagrid').datagrid({
			url : 'frtPreClearingAction!findPrePartsById.action?preclrId=' + preclrId
		});
	}else{
		$('#frtPreClearingPartsDatagrid').datagrid({
			url : 'frtPreClearingAction!findPrePartsById.action?preclrId=-1'
		});
	}
	/*if($('#save').length != 0 && $('#cancel').length != 0){
			$('#frtPreClearingPartsDatagrid').datagrid({
				onLoadSuccess : function (data){
					staticFrtPreClearingParts=data;

					$('#frtPreClearing_parts_add').linkbutton('enable');
					$('#frtPreClearing_parts_remove').linkbutton('enable');

					$('#frtPreClearing_parts_accept').linkbutton('enable');
				}
			});
		}*/
}
setTimeout("LoadOk();", 200);

$(function (){
	//前台结算单->材料清单
	$frtPreClearingPartsDatagrid = $('#frtPreClearingPartsDatagrid');
	$frtPreClearingPartsDatagrid.datagrid({
		singleSelect : true,
		fit : true,
		rownumbers : true,
		fitColumns: true,
		columns : [[
			{field:'partsIndex',title:'结算配件序号',width:60,hidden:true},
			{field:'partsId',title:'配件编号',width:60},         
			{field:'partsName',title:'配件名称',width:60},         
			{id:'partsCount',field:'partsCount',title:'配件数量',width:60},         
			{id:'partsPrice',field:'partsPrice',title:'配件单价',width:60},         
			{id:'partsAmount',field:'partsAmount',title:'费用合计',width:60},
			{id:'partsRate',field:'partsRate',title:'折扣率(%)',width:60,
				editor : {
				type : 'numberbox',
				options : {
					min:0,
					max:100,
					precision:2,
					validType:'length[1,6]',
					required : true
					}
				}
			},
			{id:'partsRateAmount',field:'partsRateAmount',title:'折扣金额',width:60,
				editor : {
					type : 'numberbox',
					options : {
						precision:numberBit,
						min : 0,
						required : true
					}
				}
			},
			{id:'claimsId',field:'claimsId',title:'索赔性质',width:60,
				formatter : function (value,row,index){
					return row.claimsName;
				}
			},
			{id:'reptypId',field:'reptypId',title:'收费性质',width:60,hidden:true}
		]],
		toolbar : [{
			id : 'frtPreClearing_parts_accept',
			text : '提交更改',
			iconCls : 'icon-remove',
			disabled : true,
			handler : function() {
				staticFrtPreClearingParts = prosceniumUpdate('frtPreClearingPartsDatagrid',staticFrtPreClearingParts);	
				frtPreClearingToteMoney();
			}
		}],
		onClickRow : function (rowIndex, rowData){
		   if($('#save').length != 0 && $('#cancel').length != 0){
			   $(this).datagrid('beginEdit', rowIndex);
				var ed = $(this).datagrid('getEditors', rowIndex);
				ed[0].target.select();
				ed[0].target.bind('keyup blur', function() {
					var num = rowData.partsAmount;
					var price = ed[0].target.val();
					var amount = accMul(parseFloat(num), (parseFloat(price)/100));
					ed[1].target.numberbox('setValue', amount.toFixed(numberBit));
				});
				ed[1].target.select();
				ed[1].target.bind('keyup blur', function() {
					var num = rowData.partsAmount;
					var real = ed[1].target.val();
					var amount = accDiv(parseFloat(real),parseFloat(num))*100;
					ed[0].target.numberbox('setValue', amount);
					if(amount>100){
						alertMsg('对不起，折扣金额不能大于合计金额!', 'warning');
						var num_ = num.toFixed(numberBit);
						ed[1].target.numberbox('setValue', num.toFixed(numberBit));
					}
				});
		   }
	   },
	   onLoadSuccess : function (data){
		   if(preclearOpretion!=null && preclearOpretion==true){
			   staticFrtPreClearingParts=data;
				$('#frtPreClearing_parts_accept').linkbutton('enable');		
		   }
			
	   }         
	});
});