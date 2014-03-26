
var updateState='undefined';
var editIndex='undefined';
		$(function(){
			
			    //库存量查询主表
				$('#pnc_partsNowCountTable').datagrid({
					 url:'StPartsNowCountAction!searchByCondition.action',
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
					 columns : [ [ {title : '配件调价编号',field : 'changePriceId',width : 80,hidden:true
					},{title : '配件编号一',field : 'partsId',width : 50,sortable:true
					}, {title : '配件编号二',field : 'partsId2',width : 50,sortable:true
					}, {title : '配件名称',field : 'partsName',width : 50,sortable:true
					}, {title : '仓别Id',field : 'storeId',width : 70,hidden:true
					}, {title : '库位',field : 'partsLibrary',width : 30,sortable:true
					}, {title : '仓别名称',field : 'storeName',width : 50,sortable:true
					}, {title : '配件单位',field : 'punitName',width : 50,sortable:true
					}, {title : '配件型号',field : 'ptypeName',width : 50,sortable:true
					}, {title : '品牌名称',field : 'pbrdName',width : 50,sortable:true
					}, {title : '配件部位',field : 'posName',width : 50,sortable:true
					}, {title : '维修价',field : 'partsRepairPrice', width : 50,
						editor:{
			                type:'numberbox',
			                options:{
		                               min:0,
			                      		required:true,
			                      		precision : 2,
			                      		missingMessage : "维修价为必填项"
			                        }
			                   },
								sortable:true
					}, {
						title : '销售价',
						field : 'partsSellPrice',
						width : 50,
						editor:{
			                type:'numberbox',
			                options:{
		                        min:0,
			                      		required:true,
			                      		precision : 2,
			                      		missingMessage : "销售价为必填项"
			                        }
			                   },
								sortable:true
					}, {
						title : '网点价',
						field : 'partsPointPrice',
						width : 50,
						editor:{
			                type:'numberbox',
			                options:{
		                                min:0,
			                      		required:true,
			                      		precision : 2,
			                      		missingMessage : "网点价为必填项"
			                        }
			                   },
								sortable:true
					}, {
						title : '特别价',
						field : 'partsSpecialPrice',
						width : 50,
						editor:{
			                type:'numberbox',
			                options:{
		                                min:0,
			                      		required:true,
			                      		precision : 2,
			                      		missingMessage : "特别价为必填项"
			                        }
			                   },
								sortable:true
					}, {
						title : '索赔价',
						field : 'partsClaimantPrice',
						width : 30,
						editor:{
			                type:'numberbox',
			                options:{
				                        min:0,
			                      		required:true,
			                      		precision : 2,
			                      		missingMessage : "索赔价为必填项"
			                        }
			            },
						sortable:true
					}, {
						title : '含税成本',
						field : 'partsLatestTaxprice',
						width : 50,
						editor:{
			                type:'numberbox',
			                options:{
						                min:0,
			                      		required:true,
			                      		precision : 2,
			                      		missingMessage : "最新入库含税价为必填项"
			                        }
			                   },
								sortable:true
					}, {
						title : '未税成本',
						field : 'partsLatestNotaxprice',
						width : 50,
						editor:{
			                type:'numberbox',
			                options:{
						                min:0,
			                      		required:true,
			                      		precision : 2,
			                      		missingMessage : "最新入库未税价为必填项"
			                        }
			                   },
								sortable:true
					}, {
						title : '库存量',
						field : 'partsNowCount',
						width : 50,
						sortable:true,
						formatter: function(value,row,index){
					   		return "<b>"+value+"</b>";
						}
					}, {
						title : '库存量1',field : 'partsNowCount1',hidden:true,width : 50,sortable:true
					}, {
						title : '下限',
						field : 'stockLower',
						width : 50,
						editor:{
							type:'numberbox',
			                options:{
			              		min:0,
			              		max:9999,
			                }
		                },sortable:true
					}, {
						title : '上限',
						field : 'stockUpper',
						width : 50,
						editor:{
							type:'numberbox',
			                options:{
			              		min:0,
			              		precision : 2,
			              		max:9999,
				            }
		                },sortable:true
					}, {title : '间隔天数',field : 'spaceTime',width : 50
					}, {title : '最近入出库时间',field : 'inAndOutTime',width : 70
					}]],
		        onDblClickRow:function(rowIndex, rowData){
//					if(updateState=='upadte'){
//						if(editIndex=='undefined'){
//							editIndex=rowIndex;
//							copyPartsDateAndBindEventItem($('#pnc_partsNowCountTable'), rowIndex, rowData)
//						}else{
//							$.messager.alert('优忆软件提示','抱歉，你已选择要价格变更的记录，如果放弃，请点击取消！','warning');   
//						}
//					}else
//						$.messager.alert('提示','请选择要变更价格的配件信息！','warning');   
		        },  
		        onLoadSuccess:function(data){
		        	$.ajax({
			             type:'POST',
			             url:'StPartsNowCountAction!calcutePartsChangePrice.action',
			             data:$('#pnc_PartsNowCountForm').serialize(),
			             dataType:'json',
			             success:function callback(r){
			        	   if(r.total>0){
			        		    var sumCount=0.0;
					        	var sumTaxPrice=0.0;
					        	var sumNoTaxPrice=0.0;
					        	for(var i=0;i<r.total;i++){
					        		if(r.rows[i]!=undefined && r.rows[i].partsNowCount1!=undefined)
					        		   sumCount=accAdd(sumCount,r.rows[i].partsNowCount1);
					        		if(r.rows[i]!=undefined && r.rows[i].partsLatestTaxprice!=undefined)
					        		   sumTaxPrice=accAdd(sumTaxPrice,r.rows[i].partsLatestTaxprice);
					        		if(r.rows[i]!=undefined && r.rows[i].partsLatestNotaxprice!=undefined)
					        		   sumNoTaxPrice=accAdd(sumNoTaxPrice,r.rows[i].partsLatestNotaxprice);
					        	}
					        	$('#classfication').val(r.total.toFixed(2));
					        	$('#sumCount').val(sumCount.toFixed(2));
					        	$('#sumTaxPrice').val(sumTaxPrice.toFixed(2));
					        	$('#sumNoTaxPrice').val(sumNoTaxPrice.toFixed(2));
			        	   }else{
			        		    $('#classfication').val(0.00);
					        	$('#sumCount').val(0.00);
					        	$('#sumTaxPrice').val(0.00);
					        	$('#sumNoTaxPrice').val(0.00);
			        	   }
		                 }
		            });
		        },
	            onAfterEdit:function(rowIndex, rowData, changes){
					if(updateState!='undefined'){
					    	$.ajax({
					             type:'POST',
					             url:'StPartsNowCountAction!updatePartsChangePrice.action',
					             data:rowData,
					             dataType:'json',
					             success:function callback(r){
					        	   if(r.success){
					        		   $('#pnc_partsNowCountTable').datagrid('clearSelections');
					        		   cancel();
					        	   }else{
					        		   $('#pnc_partsNowCountTable').datagrid('beginEdit', rowIndex);
					        		   $.messager.alert('提示','价格变动异常！','warning');
					        	   }
				                 }
				            });
					  }
				  }
			   });
			});
			
		function copyPartsDateAndBindEventItem(id, rowIndex, rowData) {
			id.datagrid('beginEdit', rowIndex);
			var taxRate = 17;
			taxRate = parseFloat(taxRate) / 100;
			taxRate=accAdd(taxRate,1);
			var ed = id.datagrid('getEditors', rowIndex);
			if (ed[0]) {
				ed[0].target.numberbox('setValue', rowData.partsRepairPrice);
				ed[0].target.select();
				ed[0].target.click(function() {
					ed[0].target.select();
				});
				ed[0].target.keydown(function(e) {
					if (e.keyCode == '13') {
						ed[1].target.select();
					}
				});
			}
			if (ed[1]) {
				ed[1].target.numberbox('setValue', rowData.partsSellPrice);
				ed[1].target.click(function() {
					ed[1].target.select();
				});
				ed[1].target.keydown(function(e) {
					if (e.keyCode == '13') {
						ed[2].target.select();
					}
				});
			}
			if (ed[2]) {
				ed[2].target.numberbox('setValue', rowData.partsPointPrice);
				ed[2].target.click(function() {
					ed[2].target.select();
				});
				ed[2].target.keydown(function(e) {
					if (e.keyCode == '13') {
						ed[3].target.select();
					}
				});
			}
			if (ed[3]) {
				ed[3].target.numberbox('setValue', rowData.partsSpecialPrice);
				ed[3].target.click(function() {
					ed[3].target.select();
				});
				ed[3].target.keydown(function(e) {
					if (e.keyCode == '13') {
						ed[4].target.select();
					}
				});
			}
			if (ed[4]) {
				ed[4].target.numberbox('setValue', rowData.partsClaimantPrice);
				ed[4].target.click(function() {
					ed[4].target.select();
				});
				ed[4].target.keydown(function(e) {
					if (e.keyCode == '13') {
						ed[5].target.select();
					}
				});
			}
			if (ed[5]) {
				ed[5].target.numberbox('setValue', rowData.partsLatestTaxprice);
				ed[5].target.click(function() {
					ed[5].target.select();
				});
				ed[5].target.keydown(function(e) {
					if (e.keyCode == '13') {
						ed[6].target.select();
					}
				});
			}
			if (ed[6]) {
				ed[6].target.numberbox('setValue', rowData.partsLatestNotaxprice);
				ed[6].target.click(function() {
					ed[6].target.select();
				});
				ed[6].target.keydown(function(e) {
					if (e.keyCode == '13') {
						ed[7].target.select();
					}
				});
			}
			if (ed[7]) {
				ed[7].target.numberbox('setValue', rowData.stockLower);
				ed[7].target.click(function() {
					ed[7].target.select();
				});
				ed[7].target.keydown(function(e) {
					if (e.keyCode == '13') {
						ed[8].target.select();
					}
				});
			}
			if (ed[8]) {
				ed[8].target.numberbox('setValue', rowData.stockUpper);
				ed[8].target.click(function() {
					ed[8].target.select();
				});
				ed[8].target.keydown(function(e) {
					if (e.keyCode == '13') {
						ed[0].target.select();
					}
				});
			}
			ed[0].target.select();
			ed[5].target.bind('blur', function() {
			    var taxPrice = ed[5].target.val();// 代表采购数量
			    var noTaxPrice=0.0;
				if (taxPrice == null || taxPrice == '') {
					taxPrice = 0.0;
					noTaxPrice = 0.0;
				}else
					noTaxPrice =accDiv(taxPrice,taxRate);
				
				ed[5].target.numberbox('setValue', taxPrice);// 代表在途数量
				ed[6].target.numberbox('setValue', noTaxPrice);
			});
			
			ed[6].target.bind('blur', function() {
			    var noTaxPrice = ed[6].target.val();// 代表采购数量
			    var taxPrice=0.0;
				if (noTaxPrice == null || noTaxPrice == '') {
					taxPrice = 0.0;
					noTaxPrice = 0.0;
				}else
					taxPrice =accMul(noTaxPrice,taxRate);
				ed[5].target.numberbox('setValue', taxPrice);// 代表在途数量
				ed[6].target.numberbox('setValue', noTaxPrice);
			});
		}
		
		function btnenable(){
			$('#pnc_search').linkbutton('enable');
			$('#pnc_clear').linkbutton('enable');
		}
		
		function btndisenable(){
			$('#pnc_search').linkbutton('disable');
			$('#pnc_clear').linkbutton('disable');
		}
		
		
		function addPersonnel()
		{
			var selected=$('#pnc_partsNowCountTable').datagrid('getSelected');
			if(selected!=null&&selected!=''){
				var index=$('#pnc_partsNowCountTable').datagrid('getRowIndex',selected);
				editIndex=index;
				var save ="<a id='save' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-save' plain='true' onclick='updatePartsPrice("+index+");'>保存</a>";
				var cancel="<a id='cancel' href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-undo' plain='true' onclick='cancel("+index+");'>取消</a>";
				if ($('#pnc_button').children('a').length == 0) {
					$('#pnc_button').append(save).append(cancel);
					$.parser.parse('#pnc_button');
				}
				copyPartsDateAndBindEventItem($('#pnc_partsNowCountTable'), index, $('#pnc_partsNowCountTable').datagrid('getSelected'))
				btndisenable();
				updateState='upadte';
			}else
				$.messager.alert('提示','请选择要变更价格的配件信息！','warning');
		}
			
		function updatePartsPrice(index){
		    var isno=$('#pnc_partsNowCountTable').datagrid('validateRow',index);
		    if(isno)
		    	$('#pnc_partsNowCountTable').datagrid('endEdit', index);
		    else
		    	$.messager.alert('提示','信息填写不完整！','warning');
		}
			
		function cancel(index){
			$('#pnc_button').empty();
			$('#pnc_partsNowCountTable').datagrid('endEdit',index);
			var updated = $('#pnc_partsNowCountTable').datagrid('getChanges', 'updated');
			if(updated!=null&&updated!='')
				$('#pnc_partsNowCountTable').datagrid('load')
			updateState='undefined';
			$('#pnc_partsNowCountTable').datagrid('endEdit', index);
			editIndex='undefined';
			btnenable();
		}

			
	function pnc_searchByCondition(){
		$('#pnc_partsNowCountTable').datagrid('load', serializeObject($('#pnc_PartsNowCountForm')));
	}
	
	function pnc_clearSearchByCondition(){
		$('#pnc_PartsNowCountForm').form('clear');
		$('#pnc_partsNowCountTable').datagrid('load', serializeObject($('#pnc_PartsNowCountForm')));
	}
	
	function clearPartsChangePrice(){
		 $.messager.confirm('提示', '你确定要清空配件调价信息吗?', function(r){
		   if(r){
			   $.ajax({
		             type:'POST',
		             url:'StPartsNowCountAction!clearPratsChangePrice.action',
		             data:'',
		             dataType:'json',
		             success:function callback(r){
		        	   if(r.success){
		        		   $('#pnc_partsNowCountTable').datagrid('load');
		        	   }else{
		        		   $('#pnc_partsNowCountTable').datagrid('beginEdit', rowIndex);
		        		   $.messager.alert('提示','配件清空异常！','warning');
		        	   }
	                 }
	            });
		   }
		 });
	}
	
	
	var pnc_d1;
     function partsBrandSelect(){
    	 pnc_d1 = $('<div/>');
    	 pnc_d1.dialog({
			title: '配件品牌信息选择',
		    width: 600,
		    height: 403,
		    cache: false,
		    href: projectPath+'st/StPartsNowCount/StPartsNowCount_PartsBrandSelect.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
		 });
	 }
     var pnc_d2;
     function partsNameSelect(){
    	 pnc_d2 = $('<div/>');
    	 pnc_d2.dialog({
			title: '配件名称信息选择',   
		    width: 600,
		    height: 403,
		    cache: false,
		    href: projectPath+'st/StPartsNowCount/StPartsNowCount_PartsNameSelect.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
	 }
     var pnc_d3;
     function partsTypeSelect(){
    	 pnc_d3 = $('<div/>');
    	 pnc_d3.dialog({
			title: '配件型号信息选择',   
		    width: 600,
		    height: 403,
		    cache: false,
		    href: projectPath+'st/StPartsNowCount/StPartsNowCount_PartsTypeSelect.jsp',
		    modal: true,
		    onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
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
			showEditDialog("pnc_partsNowCountTable",null,"pnc_partsNowCountTableDiv","开始导出","导出配置",0,_callbackExcept);
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
			exportEsuyUIExcelFile(parentId,fieldNames,"库存量查询"+getSystemTime());
		}
	