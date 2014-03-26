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
			$('#receiveOperationMainDatagrid').datagrid({
				 url:'receiveOperationAction!receiveOperationMain.action',
			     fit:true,
				 fitColumns : true, 
				 singleSelect:true,
				 showFooter:true,
				 pagination:true,
				 loadMsg : "数据加载中，请稍后……",
				 columns : [ [ 
				 {title : '接待员',field : 'stfName',width : 50,sortable:true}, 
				 {title : '结算台次',field : 'count',width : 50,sortable:true}, 
				 {title : '工时费',field : 'itemsAmount',width : 50,sortable:true}, 
				 {title : '材料费',field : 'partsAmount',width : 50,sortable:true}, 
				 {title : '结算金额',field : 'sumAmount',width : 50,sortable:true}, 
				 {title : '未税成本',field : 'noTaxCost',width : 70,sortable:true},
				 {title : '含税成本',field : 'taxCost',width : 70,sortable:true},
				 {title : '料工比',field : 'partsCompareTime',width : 30,sortable:true}
				 ]]
			});
		}
		var _search=function(){
			if($('#receiveOperationMainForm').form('validate')){
				$('#receiveOperationMainDatagrid').datagrid('load',serializeObject($('#receiveOperationMainForm')));
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		var _clear=function(){
			$('#receiveOperationMainForm').form('clear');
			$('#receiveOperationMainDatagrid').datagrid('load', serializeObject($('#receiveOperationMainForm')));
		}
		function _except(){
			showEditDialog("receiveOperationMainDatagrid",null,"receiveOperationMainDatagrid_center","开始导出","导出配置",0,_callbackExcept);
		}
		function _callbackExcept(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"接待员业绩统计汇总"+getSystemTime());
		}