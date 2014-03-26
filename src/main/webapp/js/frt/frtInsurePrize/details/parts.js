var effectRowData;
	function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		var data = $('#frtInsurePrizeSummaryDatagrid').datagrid('getSelected');
		var resvId = document.getElementById("resvId").value;
		if(data){
			$frtInsurePrizePartsDatagrid.datagrid({
				url : 'frtInsurePrizeAction!findPartsByResvId.action?resvId=' +data.resvId
			});
		}else if(staticRpId){
			var parts=null;
			if(staticFrtInsurePrizeParts==null){
				parts="";
			}else{
				parts=JSON.stringify(staticFrtInsurePrizeParts);
			}
			 var rowData = $('#selectServiceWeaveDatagrid').datagrid('getSelected');
        	   $.ajax({
				type : 'post',
				dataType : 'json',
				url : 'frtInsurePrizeAction!findPartsListByRpId.action',
				data: 'rpId='+staticRpId+'&parts='+parts,
				success : function(r) {
					$('#frtInsurePrizePartsDatagrid').datagrid('loadData', r);
					var data = $('#frtInsurePrizePartsDatagrid').datagrid('getData');
					staticFrtInsurePrizeParts=data;
				}
			}).error(function(r) {
				alertMsg(r);
			});
		}else{
			$frtInsurePrizePartsDatagrid.datagrid({
				url : 'frtInsurePrizeAction!findPartsByResvId.action?resvId=-1'
			});
		}
		if($('#save').length != 0 && $('#cancel').length != 0){
			$('#frtInsurePrizePartsDatagrid').datagrid({
				onLoadSuccess : function (){
					$('#frtInsurePrize_parts_add').linkbutton('enable');
					$('#frtInsurePrize_parts_remove').linkbutton('enable');
					$('#frtInsurePrize_parts_accept').linkbutton('enable');
				}
			});
		}
	}
	setTimeout("LoadOk();", 200);

	$(function (){
		//预约申请单->计划材料
		var url='';
		var rowData = $('#frtInsurePrizeSummaryDatagrid').datagrid('getSelected');
		if(rowData){
			url='frtInsurePrizeAction!findPartsByResvId.action?resvId=' +rowData.resvId;
		}
		$frtInsurePrizePartsDatagrid = $('#frtInsurePrizePartsDatagrid');
		$frtInsurePrizePartsDatagrid.datagrid({
			url : url,
			singleSelect : true,
			fit : true,
			rownumbers : true,
			fitColumns: true,
			columns : [[
				{field:'partsId',title:'配件编码',width:60},         
				{field:'partsName',title:'配件名称',width:60},         
				{
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
					id:'partsNum',
					field:'partsNum',
					title:'数量',
					width:60,
					editor : {
						type : 'numberbox',
						options : {
							precision : 2,
							min : 1,
							required : true,
							missingMessage : "数量为必填项!"
						}
					}
				},         
				{
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
							precision : 2,
							min : 0,
							required : true,
							missingMessage : "金额为必填项!"
						}
					}
				},{
					field : 'id',
					width : 60,
					hidden : true
				},{
					field : 'storeId',
					title : '仓别',
					width : 60,
					hidden : true
				},{
					field : 'storeName',
					title : '仓别',
					width : 60,
					hidden : true
				}]],
			toolbar : [
			   {
				   id : 'frtInsurePrize_parts_add',
				   text : '配件新增',
				   iconCls : 'icon-add',
				   disabled : true,
				   handler : function (){
				   		var d = $('<div/>').dialog({
				   			modal:true,
							title : '配件选择',
							closable : true,
							href : projectPath+'frt/frtInsurePrize/details/addParts.jsp',
							width : 900,
							height : 560,
							buttons : [{
								text : '确定',
								iconCls : 'icon-add',
								handler : function (){
									if(validateDatagrid('selectedParts')){
										staticFrtInsurePrizeParts=prosceniumAdd('selectedParts','frtInsurePrizePartsDatagrid',staticFrtInsurePrizeParts);
										d.dialog('close');
									}else{
										alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
									}
								}
							}],
							onClose : function (){
						    	$(this).dialog('destroy');
						    }
				   		});
			   	   }
			   },{
				   id : 'frtInsurePrize_parts_remove',
				   text : '配件删除',
				   iconCls : 'icon-remove',
				   disabled : true,
				   handler : function (){
					  var row = $('#frtInsurePrizePartsDatagrid').datagrid('getSelected');
						if(row){
							staticFrtInsurePrizeParts = prosceniumDelete('frtInsurePrizePartsDatagrid',row,staticFrtInsurePrizeParts);
						}
			   	   }
			   }],
			   onClickRow : function (rowIndex, rowData){
				   if($('#save').length != 0 && $('#cancel').length != 0){
					   $(this).datagrid('beginEdit', rowIndex);
						var ed = $(this).datagrid('getEditors', rowIndex);
						ed[0].target.select();
						ed[0].target.bind('keyup', function() {
							var num = ed[0].target.val();
							var price = ed[1].target.val();
							var amount = accMul(parseFloat(num), parseFloat(price));
							ed[2].target.numberbox('setValue', amount);
						});
						ed[1].target.bind('keyup', function() {
							var num = ed[0].target.val();
							var price = ed[1].target.val();
							var amount = accMul(parseFloat(num), parseFloat(price));
							ed[2].target.numberbox('setValue', amount);
						});
						ed[0].target.focus(function (){
							ed[0].target.select();
						});
						ed[1].target.focus(function (){
							ed[1].target.select();
						});
						ed[0].target.keydown(function (e){
							if(e.keyCode == '13'){
								ed[1].target.select();
							}
						});
				   }
			   },
			   onLoadSuccess : function (data){
					staticFrtInsurePrizeParts=data;
			   }
		});
	});