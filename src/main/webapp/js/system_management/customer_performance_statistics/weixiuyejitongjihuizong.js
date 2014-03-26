function LoadOk() {
			if (document.readyState == "complete") {
				spmRuns();
			} else {
				setTimeout("LoadOk();", 200);
			}
		}
		setTimeout("LoadOk();", 200);
		
		var spmRuns=function (){
			//维修人员业务统计汇总
			$('#servicePersonMainDatagrid').datagrid({
					url : 'customerPerformanceStatisticsAction!findServicePersonMain.action',
				pagination : true,
				fit : true,
				rownumbers : true,
				showFooter : true,
				animate:true,
				autoRowHeight:true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				fitColumns : true,
				loadMsg : "数据加载中，请稍后……",
				columns : [ [{
					field : 'stfId',
					title : '维修员编号',
					width : 100,
					hidden : true
				},{
					field : 'stfName',
					title : '维修员',
					width : 100,
					sortable : true
				}, {
					field : 'itemAmount',
					title : '工时金额',
					width : 100,
					sortable : true
				}, {
					field : 'itemRebateAmount',
					title : '折后工时金额',
					width : 100,
					sortable : true
				}, {
					field : 'partsAmount',
					title : '配件金额',
					width : 100,
					sortable : true
				}, {
					field : 'partsRebateAmount',
					title : '折后配件金额',
					width : 100,
					sortable : true
				}, {
					field : 'taxAmount',
					title : '含税成本',
					width : 100,
					sortable : true
				}, {
					field : 'noTaxAmount',
					title : '未税成本',
					width : 100,
					sortable : true
				}
				]]
			});
		}
		
		function queryServicePersonMain(){
			if($('#servicePersonMainForm').form('validate')){
				$('#servicePersonMainDatagrid').datagrid('load', serializeObject($('#servicePersonMainForm')));
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		function clearServicePersonMain(){
		 $('#servicePersonMainForm').form('clear');
		}
		function _exceptServicePersonMain(){
			showEditDialog("servicePersonMainDatagrid",null,"servicePersonMainDatagrid_center","开始导出","导出配置",0,_callbackExceptServicePersonMain);
		}
		function _callbackExceptServicePersonMain(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"维修人员业务统计汇总"+getSystemTime());
		}