function LoadOk() {
		if (document.readyState == "complete") {
			mainRuns();
			detailRuns();
			buildEvent();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	setTimeout("LoadOk();", 200);
	function mainRuns(){

		$('#endTime').val(getSystemTime2());
		loadPreClrTime1($('#beginTime'));
		$('#insurePersonAnalyseMainDatagrid').datagrid({
			url : 'serviceDestClerkAnalyseAction!findInsurePersonAnalyseMain.action',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			showFooter : true,
			fit : true,
			loadMsg : "数据加载中，请稍后……",
			columns : [ [ {
				field : 'insurePerson',
				title : '保险送修人',
				width : 80,
				sortable : true
			}, {
				field : 'preclrCount',
				title : '结算台次',
				width : 80,
				sortable : true
			}, {
				field : 'partsAmount',
				title : '材料费',
				width : 80,
				sortable : true
			}, {
				field : 'itemAmount',
				title : '工时费',
				width : 80,
				sortable : true
			}, {
				field : 'sumAmount',
				title : '结算金额',
				width : 80,
				sortable : true
			}, {
				field : 'taxCost',
				title : '含税成本',
				width : 60,
				sortable : true
			}, {
				field : 'noTaxCost',
				title : '未税成本',
				width : 60,
				sortable : true
			}] ],
			onDblClickRow : function(rowIndex, data) {
				$('#flag').val(true);
				$('#insurePerson').val(data.insurePerson);
				$('#insurePersonAnalyseTabs').tabs('select', '保险送修人统计明细');
				$('#insurePersonAnalyseDetailDatagrid').datagrid('load', serializeObject($('#insurePersonAnalyseQueryForm')));
			}
		});
	}
	function detailRuns(){
		$('#insurePersonAnalyseDetailDatagrid').datagrid({
			url : 'serviceDestClerkAnalyseAction!findInsurePersonAnalyseDetail.action',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			fit : true,
			loadMsg : "数据加载中，请稍后……",
			columns : [ [ {
				field : 'insurePerson',
				title : '保险送修人',
				width : 60,
				sortable : true
			}, {
				field : 'preclrId',
				title : '结算单号',
				width : 110,
				sortable : true
			},  {
				field : 'preclrTime',
				title : '结算时间',
				width : 130,
				sortable : true
			},  {
				field : 'receptionId',
				title : '工单号',
				width : 110,
				sortable : true
			},  {
				field : 'carLicense',
				title : '车牌照',
				width : 60,
				sortable : true
			},  {
				field : 'customName',
				title : '客户名称',
				width : 180,
				sortable : true
			},  {
				field : 'partsAmount',
				title : '材料费',
				width : 80,
				sortable : true
			}, {
				field : 'itemAmount',
				title : '工时费',
				width : 80,
				sortable : true
			}, {
				field : 'sumAmount',
				title : '结算金额',
				width : 80,
				sortable : true
			}, {
				field : 'taxCost',
				title : '含税成本',
				width : 60,
				sortable : true
			}, {
				field : 'noTaxCost',
				title : '未税成本',
				width : 60,
				sortable : true
			}] ]
		});
	}
	var buildEvent=function(){
		$(function(){
			$('#insurePersonAnalyseTabs').tabs({   
				onSelect:function(title){  
					if(title =='保险送修人统计汇总'){
						unbindAllButton();
						$("#_search").bind("click",_searchInsurePersonAnalyseMain);
						$("#_export").bind("click",_exceptInsurePersonAnalyseMain);
						
					}else if(title =='保险送修人统计明细'){
						unbindAllButton();
						$("#_search").bind("click",_searchInsurePersonAnalyseDetail);
						$("#_export").bind("click",_exceptInsurePersonAnalyseDetail);
					}
				}
			});
		});
	}
	function unbindAllButton(){
		$("#_search").unbind();
		$("#_export").unbind();
		$('#_search').linkbutton('enable');
		$('#_export').linkbutton('enable');
	}
	var _searchInsurePersonAnalyseMain=function(){
		if($('#insurePersonAnalyseQueryForm').form('validate')){
			$('#flag').val(false);
			$('#insurePersonAnalyseMainDatagrid').datagrid('load',serializeObject($('#insurePersonAnalyseQueryForm')));
		}else{
			alertMsg('对不起，请输入正确的查询条件！', 'warning');
		}
	}
	var _searchInsurePersonAnalyseDetail=function(){
		if($('#insurePersonAnalyseQueryForm').form('validate')){
			$('#flag').val(false);
			$('#insurePersonAnalyseDetailDatagrid').datagrid('load',serializeObject($('#insurePersonAnalyseQueryForm')));
		}else{
			alertMsg('对不起，请输入正确的查询条件！', 'warning');
		}
	}
	var _clear=function(){
		$('#insurePersonAnalyseQueryForm').form('clear');
		$('#insurePersonAnalyseMainDatagrid').datagrid('load', serializeObject($('#insurePersonAnalyseQueryForm')));
	}
	function _exceptInsurePersonAnalyseMain(){
		showEditDialog("insurePersonAnalyseMainDatagrid",null,"insurePersonAnalyseMainDatagrid_center","开始导出","导出配置",0,_callbackExceptInsurePersonAnalyseMain);
	}
	function _callbackExceptInsurePersonAnalyseMain(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"保险送修人分析汇总"+getSystemTime());
	}
	function _exceptInsurePersonAnalyseDetail(){
		showEditDialog("insurePersonAnalyseDetailDatagrid",null,"insurePersonAnalyseDetailDatagrid_center","开始导出","导出配置",0,_callbackExceptInsurePersonAnalyseDetail);
	}
	function _callbackExceptInsurePersonAnalyseDetail(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"保险送修人分析明细"+getSystemTime());
	}