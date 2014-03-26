var manager='';
$(function(){
		 
	  $('#strtpmDateqiEnd').val(getSystemTime());
	  getStartDate($('#strtpmDateStart'));
	  manager=$('#srp_manager').val();
	  
      $('#srp_strtpmDate').validatebox({  
         required:true,
         missingMessage:'退料日期必填'
	  });  
	  $('#srp_receptionId').validatebox({  
		  required:true,
		  missingMessage:'工单号必填'
	  });  
	  $('#srp_stfId').validatebox({  
		  required:true,
		  missingMessage:'退料员必填' 
	  });  
	  
/**退料单汇总**/
$('#StRtPartsMainTable').datagrid({
	 url:'StRtPartsAction!searchByCondition.action',
	 pagination:true,
     fit:true,
     pageSize : 10,
     sortName:'strtpmId',
     sortOrder:'desc',
     singleSelect:true,
	 pageList : [ 10, 20,30, 40, 50, 60, 70, 80 ],
	 fitColumns : true,
	 rownumbers : true,
	 idField : 'strtpmId',
	 loadMsg : "数据加载中，请稍后……",
	 columns : [ [ {title : '退料单号',field : 'strtpmId',width : 130,sortable:true
			}, {title : '退料日期',field : 'strtpmDate',width : 100,sortable:true
			}, {title : '退料员ID',field : 'stfId',width : 100,hidden:true,
			}, {title : '退料员',field : 'stfName',width : 100,sortable:true
			}, {title : '客户',field : 'customName',width : 100,sortable:true
			}, {title : '工单号',field : 'receptionId',width : 100,sortable:true
			}, {title : '退料类型',field : 'strtpmType',width : 100,sortable:true
			}, {title : '车牌照',field : 'carLicense',width : 100,sortable:true
			}, {title : '数量',field : 'strtpmSumCnt',width : 100,sortable:true
			}, {title : '金额',field : 'rcptpartsSumPrice',width : 100,sortable:true
			}, {title : '成本额',field : 'partsLatestTaxpriceAmont',width : 100,sortable:true
			}, {title : '经办人',field : 'manager',width : 100,sortable:true
			}, {title : '备注',field : 'strtpmRemark',width : 100,sortable:true
			} ]],
	   onDblClickRow:function(rowIndex, rowData){
	        loadDetail(rowData);
      }			  
 });
 

/**工单退料单明细**/
 $('#StRtPartsDetailTable').datagrid({
	 url:'',
	 pagination:true,
     fit:true,
     pageSize : 10,
	 pageList : [ 10, 20, 30, 40, ],
	 singleSelect:true,
	 fitColumns : true, 
	 idField : 'rcptpartsIndex',
	 loadMsg : "数据加载中，请稍后……",
	 columns : [ [{title : '流水号',field : 'strtpdIndex',width :100,hidden:true
	        }, {title : '流水号',field : 'indexId',width :100,hidden:true
			}, {title : '配件编号',field : 'partsId',width :  100
			}, {title : '配件名称',field : 'partsName',width : 100
			}, {title : '出库数量',field : 'partsNum',width :  100
			}, {title : '退料数量',field : 'strtpdCnt',width :  100,
				editor : {
					type : 'numberbox',
					options : {
						required : true,
						min : 0.00,
						precision : 2,
						missingMessage : '工单退料数量为必填项'
					}
				}
			}, {title : '单位',field : 'punitName',width :  100
			}, {title : '成本价',field : 'partsLatestTaxprice',width :  100
			}, {title : '成本额',field : 'partsLatestTaxpriceAmont',width :  100,
				editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						precision : 2
					}
				}
			}, {title : '单价',field : 'selldPrice',width :  100
			}, {title : '金额',field : 'rcptpartsAmount',width :  100,
				editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						precision : 2
					}
				}
			}, {title : '收费性质',field : 'reptypName',width :  100
			}, {title : '索赔分类',field : 'claimsName',width :  100
			}, {title : '仓库名称',field : 'storeName',width :  100
			}, {title : '仓库名称',field : 'storeId',width :  100
			}, {title : '参考数量',field : 'strtpdCnt1',
				width :  100,
				hidden:true,
				editor : {
					type : 'numberbox',
					options : {
						disabled : true,
						min : 0.00,
						precision : 2
					}
				}
			}, {title : '备注',field : 'strtpdRemark',width : 100,
				editor : {
					type : 'text',
					options : {
						enabled : true
					}
				}
			}]],
	   onDblClickRow:function(rowIndex, rowData){
	       copyPartsDateAndBindEventItem($('#StRtPartsDetailTable'), rowIndex, rowData)
       }
 });

 /**销售退料单明细**/
   $('#table3').datagrid({
	 url:'',
	 pagination:true,
     fit:true,
     pageSize : 10,
	 pageList : [ 10, 20, 30, 40, ],
	 singleSelect:true,
	 fitColumns : false, 
	 idField : 'strtpdIndex',
	 loadMsg : "数据加载中，请稍后……",
	 columns : [ [{title : '流水号',field : 'strtpdIndex',width :100
		    },{title : '退料单编号',field : 'strtpmId',width :100
			}, {title : '销售单号',field : 'selldIndex',width :100
			}, {title : '配件编号',field : 'partsId',width :80
			}, {title : '配件名称',field : 'partsName',width :100
			}, {title : '销售数量',field : 'selldCnt',width :80
			}, {title : '退料数量',field : 'strtpdCnt',width :80
			}, {title : '单位',field : 'punitId',width :100
			}, {title : '成本价',field : 'selldCostPrice',width :80
			}, {title : '成本额',field : 'selldCostAmount',width :80
			}, {title : '单价',field : 'selldPrice',width :80
			}, {title : '金额',field : 'selldAmount',width :80
			}, {title : '仓库名称',field : 'storeName',width :100
			}]],
	   onDblClickRow:function(rowIndex, rowData){	 
			      $('#winSellWindow').window('open');
			      $('#strtpdCnt').val("");
			      $('#selldIndex').val(rowData.selldIndex);
			      $('#partsId').val(rowData.partsId);
			      $('#partsName').val(rowData.partsName);
			      $('#selldCnt').val(rowData.selldCnt);
			      $('#punitId').val(rowData.punitId);
			      $('#selldCostPrice').val(rowData.selldCostPrice);
			      $('#selldCostAmount').val(rowData.selldCostAmount);
			      $('#selldPrice').val(rowData.selldPrice);
			      $('#selldAmount').val(rowData.selldAmount);
			      $('#storeName').val(rowData.storeName);
	       }
   });
	 
   /**销售单信息显示**/
   $('#table22').datagrid({
	 url:'StRtPartsAction!findAll1.action',
	 pagination:true,
     fit:true,
     pageSize : 10,
	 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	 fitColumns : true, 
	 idField : 'id',
	 loadMsg : "数据加载中，请稍后……",
     columns : [ [  {title : '销售单号',field : 'sellmmId',width : 100
		}, {title : '车牌',field : 'carLicense',width : 100
		}, {title : '客户名称',field : 'customName',width : 100
		}, {title : '销售日期',field : 'sellmmDate',width : 100
		} ]],
	    toolbar:[{text:'查询',iconCls:'icon-search',handler:function(){} } ],
	    onDblClickRow:function(rowIndex, rowData){
	     $('#sellmmId').val(rowData.sellmmId);
	     $('#carLicense').val(rowData.carLicense);
	     $('#customName').val(rowData.customName);
	     $.ajax({
				type : 'POST',
				url : 'StRtPartsAction!reLoadSellOrderParts.action',
				data : serializeObject($('#tuiliaodanForm')),
				dataType : 'json',
				success : function(r){
	    	      $('#table3').datagrid('loadData',r);
    	        }
	     }); 	
        }
     });
 })

function loadDetail(rowData){
	  $('#tt').tabs('select','工单退料单明细');
	     $('#srp_strtpmDate').val(rowData.strtpmDate);
	     $('#strtpmId').val(rowData.strtpmId);
	     $('#srp_carLicense').val(rowData.carLicense);
	     $('#srp_receptionId').val(rowData.receptionId);
	     $('#srp_customName').val(rowData.customName);
	     $('#srp_manager').val(rowData.manager);
	     $('#strtpmRemark').val(rowData.strtpmRemark);
	     $('#srp_strtpmSumCnt').val(rowData.strtpmSumCnt);
	     $('#srp_strtpmAmont').val(rowData.partsLatestTaxpriceAmont);
	     $('#srp_strtpmCostAmont').val(rowData.rcptpartsSumPrice);
	     $('#srp_stfId').val(rowData.stfId);
	     $('#srp_stfName').val(rowData.stfName);
	     $.ajax({
				type : 'POST',
				url : 'StRtPartsAction!searchStRtPartsDetailByStrtpmId.action',
				data : 'strtpmId='+rowData.strtpmId,
				dataType : 'json',
				success : function(r){
	    	        $('#StRtPartsDetailTable').datagrid('loadData',r);
	            }
	     });
}
  
/**工单退料明细行编辑事件**/
function copyPartsDateAndBindEventItem(id, rowIndex, rowData)
{
	id.datagrid('beginEdit', rowIndex);
	var ed= id.datagrid('getEditors', rowIndex);
	if(ed[0]){
		ed[0].target.select();
		ed[0].target.click(function (){
			ed[0].target.select();
		});
		ed[0].target.keydown(function (e){
			if(e.keyCode == '13'){
				ed[4].target.select();
		    }
		});
	}
	if(ed[1])
		ed[1].target.numberbox('setValue',0.0);
	if(ed[2])
		ed[2].target.numberbox('setValue',0.0);
	if(ed[3])
		ed[3].target.numberbox('setValue',0.0);
	if(ed[4]){
		ed[4].target.text('setValue','');
		ed[4].target.click(function (){
			ed[4].target.select();
		});
		ed[4].target.keydown(function (e){
			if(e.keyCode == '13'){
				if(rowIndex < id.datagrid('getData').total - 1){
					id.datagrid('selectRow',rowIndex + 1);
					copyPartsDateAndBindEventItem(id, rowIndex + 1,id.datagrid('getSelected'));
				}else{
					var ed = id.datagrid('getEditors', 0);
					ed[0].target.select();
				}
		    }
		});
	}
	ed[0].target.select();
    ed[0].target.bind('keyup', function() {
		var num = ed[0].target.val();//代表采购数量
		if(num==null||num==''){
			num=0;
		}
		if(parseFloat(num)>parseFloat(rowData.partsNum)){
			alertMsg('退料数量不能大于出库适量', 'warning');
			num = rowData.partsNum;
			ed[0].target.numberbox('setValue', num);
		}
		var partsLatestTaxprice=rowData.partsLatestTaxprice;
		var selldPrice=rowData.selldPrice;
		var partsLatestTaxpriceAmont=accMul(parseFloat(num), parseFloat(partsLatestTaxprice));
		var rcptpartsAmount=accMul(parseFloat(num), parseFloat(selldPrice));
		ed[1].target.numberbox('setValue',partsLatestTaxpriceAmont);
		ed[2].target.numberbox('setValue',rcptpartsAmount);
		ed[3].target.numberbox('setValue',num);
      });
      
      //退货金额    变动
      ed[1].target.numberbox({
			onChange : function (newValue, oldValue){
    	         $('#srp_strtpmAmont').val(accAdd(accSub(parseFloat($('#srp_strtpmAmont').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
      //退货成本额  变动
      ed[2].target.numberbox({
			onChange : function (newValue, oldValue){
    	         $('#srp_strtpmCostAmont').val(accAdd(accSub(parseFloat($('#srp_strtpmCostAmont').val()),parseFloat(oldValue)),parseFloat(newValue)));
			}
	  });
      //退货数量   变动
      ed[3].target.numberbox({
			onChange : function (newValue, oldValue){
	    	     $('#srp_strtpmSumCnt').val(accAdd(accSub(parseInt($('#srp_strtpmSumCnt').val()),parseInt(oldValue)),parseInt(newValue)));
			}
	  });
}    
    
  /**工单退料明细页面数据清空事件**/
 function doFromAndDatagridClear()
 {
	 $('#StRtPartsMainForm').find("input").val('');
	 $('#StRtPartsMainForm').find('select').val('');
	 $('#StRtPartsMainForm').find("textarea").html('');
	 $('#StRtPartsDetailTable').datagrid('loadData', {total:0,rows:[]});
 }
     
 /**按钮可用事件**/
 function enableBtn(){
	 $('#srpm_addBtn').linkbutton('enable');
	 $('#srpm_deleteBtn').linkbutton('enable');
	 $('#srpm_searchBtn').linkbutton('enable');
	 $('#srpm_clearBtn').linkbutton('enable');
	 $('#srpm_printBtn').linkbutton('enable');
	 $('#srpm_exportBtn').linkbutton('enable');
	 $('#srp_strtpmDate').validatebox('remove');
	 $('#srp_receptionId').validatebox('remove');
	 $('#srp_stfName').validatebox('remove');
	 $('#srp_strtpmDate').val(getSystemTime2());
 }
 
 /**按钮不可用事件**/
 function disableBtn(){
	 $('#srpm_addBtn').linkbutton('disable');
	 $('#srpm_deleteBtn').linkbutton('disable');
	 $('#srpm_searchBtn').linkbutton('disable');
	 $('#srpm_clearBtn').linkbutton('disable');
	 $('#srpm_printBtn').linkbutton('disable');
	 $('#srpm_exportBtn').linkbutton('disable');
	 $('#srp_strtpmDate').validatebox('reduce');
	 $('#srp_receptionId').validatebox('reduce');
	 $('#srp_stfName').validatebox('reduce');
 }
     
     
 /**取消保存事件**/
 function add_cancel(){
	doFromAndDatagridClear();
 	$('#button').empty();
 	enableBtn();
 }
     
 /**添加保存取消事件**/
 function addPersonnel() {
	$('#tt').tabs('select','工单退料单明细'); 
	doFromAndDatagridClear();
	var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addStRtPartsMain();">保存';
	var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="add_cancel();">取消';
    if ($('#button').children('a').length == 0) {
		$('#button').append(save).append(cancel);
		$.parser.parse('#button');
	}
    disableBtn();
	$('#srp_manager').val(manager);
	$('#srp_strtpmDate').val(getSystemTime2());
 }
 	 
 /**退料单新增事件**/
 function addStRtPartsMain()
 {
    endEdit($('#StRtPartsDetailTable'));
	var data=$('#StRtPartsDetailTable').datagrid('getData');
	var isno=$('#StRtPartsMainForm').form('validate');
	if(isno){
		if($('#StRtPartsDetailTable').datagrid('getChanges')!=null&&$('#StRtPartsDetailTable').datagrid('getChanges')!=''&&$('#srp_strtpmSumCnt').val()>0){
			$.ajax({
				type : 'POST',
				url : 'StRtPartsAction!addStRtPartsMain.action',
				data : $('#StRtPartsMainForm').serialize()+'&detailData='+JSON.stringify(data),
				dataType : 'json',
				success : function(r){
				    if(r.success){
				    	clearSearchByCondition();
				    	add_cancel();
				    }else{
				    	alertMsg(r, 'warning');
				    }
		        }
		    });
		}else
			alertMsg('退料数量不能为零！', 'warning');
	}else
		alertMsg('退料汇总必填项信息填写不完整，不能保存！', 'warning');
 }
 
 
 /**退料单删除事件**/
 function delStRtPartsMain() {
	var selects = $('#StRtPartsMainTable').datagrid('getSelected');
	if (selects != null && selects != '') {
		$.messager.confirm('提示', '你确定要删除该信息吗?', function(r) {
    		 if(r){
    			 $.ajax({                        
     				type : 'POST', 
     				url : 'StRtPartsAction!delStRtpartsMain.action',
     				data : 'strtpmId='+selects.strtpmId+'&receptionId='+selects.receptionId,
     				dataType : 'json',
     				success : function(r){
           		        if(r.success){
           		        	  doFromAndDatagridClear();
           		        	  clearSearchByCondition();
           		        }
     	              }
     	          }); 
    		 }
    	 });
	}else 
		$.messager.alert('优亿软件提示', '对不起，请先选中要删除的记录！');
 }
     
 /**退料单汇总综合查询事件**/
 function searchByCondition(){
	 $('#tt').tabs('select','工单退料单汇总');
	 $.ajax({
			type : 'POST',
			url : 'StRtPartsAction!searchByCondition.action',
			data :$('#StRtPartsSearchForm').serialize(),
			dataType : 'json',
			success : function(r){
    	        $('#StRtPartsMainTable').datagrid('loadData',r);
            }
      }); 	
 }
	 
 /**退料单汇总查询清空事件**/
 function clearSearchByCondition(){
	 $('#tt').tabs('select','工单退料单汇总');
	 $('#StRtPartsSearchForm').find("input").val("");
	 $('#StRtPartsSearchForm').find('select').val('');
	 $('#StRtPartsSearchForm').find("textarea").html("");
	 $('#StRtPartsMainTable').datagrid({
		 url:'StRtPartsAction!searchByCondition.action?'+$('#StRtPartsSearchForm').serialize()
	 })
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
 	showEditDialog("StRtPartsMainTable",null,"StRtPartsMainTableDiv","开始导出","导出配置",0,_callbackExcept);
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
 	exportEsuyUIExcelFile(parentId,fieldNames,"工单退料单汇总"+getSystemTime());
 }
 
 
 var srp_d1;
function srp_receptionSelect(){
	srp_d1 = $('<div/>');
	srp_d1.dialog({
		title: '工单信息选择',   
	    width: 600,   
	    height: 403,
	    cache: false,
	    href: projectPath+'st/StRtParts/StRtParts_Reception.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
 }
var srp_d2;
//打来采购单明细：采购员信息页面
function srp_stufSelect(){
	srp_d2 = $('<div/>');
	srp_d2.dialog({
		title: '退料员信息选择',
	    width: 600,
	    height: 403,
	    cache: false,
	    href: projectPath+'st/StRtParts/StRtParts_Stuf.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
 }