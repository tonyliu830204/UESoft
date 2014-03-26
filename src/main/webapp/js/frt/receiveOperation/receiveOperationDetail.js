function LoadOk() {
			if (document.readyState == "complete") {
				runs();
			} else {
				setTimeout("LoadOk();", 200);
			}
		}
		setTimeout("LoadOk();", 200);
		var runs=function(){
			$('#preclrTimeEnd').val(getSystemTime2());
			loadPreClrTime1($('#preclrTimeBegin'));
			$('#receiveOperationDetailDatagrid').datagrid({
				 url:'receiveOperationAction!receiveOperationDetail.action',
			     fit:true,
				 fitColumns : true, 
				 singleSelect:true,
				 showFooter:true,
				 loadMsg : "数据加载中，请稍后……",
				 columns : [ [ 
				 {title : '接待员',field : 'stfName',width : 50,sortable:true}, 
				 {title : '工单号',field : 'receptionId',width : 50,sortable:true}, 
				 {title : '车牌照',field : 'carLicense',width : 50,sortable:true}, 
				 {title : '客户名称',field : 'customName',width : 50,sortable:true}, 
				 {title : '进厂时间',field : 'interDate',width : 50,sortable:true}, 
				 {title : '结算时间',field : 'prelcrTime',width : 70,sortable:true},
				 {title : '结算类型',field : 'preclrType',width : 70,sortable:true},
				 {title : '工时费',field : 'itemsAmount',width : 70,sortable:true},
				 {title : '材料费',field : 'partsAmount',width : 70,sortable:true},
				 {title : '其他费用',field : 'otherAmount',width : 70,sortable:true},
				 {title : '收入合计',field : 'sumAmount',width : 70,sortable:true},
				 {title : '实际收款',field : 'realAmount',width : 70,sortable:true}
				 ]],
				 rowStyler:function(index,row){
					if (row.preclrType==null){
						return 'background-color:#80FF80;';
					}
				 }
			});
		}
		var _search=function(){
			if($('#receiveOperationDetailForm').form('validate')){
				$('#receiveOperationDetailDatagrid').datagrid('load',serializeObject($('#receiveOperationDetailForm')));
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		var _clear=function(){
			$('#receiveOperationDetailForm').form('clear');
			$('#receiveOperationDetailDatagrid').datagrid('load', serializeObject($('#receiveOperationDetailForm')));
		}
		function _except(){
			showEditDialog("receiveOperationDetailDatagrid",null,"receiveOperationDetailDatagrid_center","开始导出","导出配置",0,_callbackExcept);
		}
		function _callbackExcept(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"接待员业绩统计明细"+getSystemTime());
		}