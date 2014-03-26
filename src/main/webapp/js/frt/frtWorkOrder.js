function queryFrtWorkOrderBase(){
			if($('#frtWorkOrderBaseQueryForm').form('validate')){
				var url="frtWorkOrderAction!datagridFrtWorkOrderBase.action?";
				url+=$('#frtWorkOrderBaseQueryForm').serialize();
				$('#frtWorkOrderBaseDatagrid').treegrid({
					url:url
				});
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		function clearFrtWorkOrderBase(){
		 $('#frtWorkOrderBaseQueryForm').form('clear');
		 var url="frtWorkOrderAction!datagridFrtWorkOrderBase.action?";
			url+=$('#frtWorkOrderBaseQueryForm').serialize();
			$('#frtWorkOrderBaseDatagrid').treegrid({
				url:url
			});
		}
		function _exceptFrtWorkOrderBase(){
			showEditDialog("frtWorkOrderBaseDatagrid",null,"frtWorkOrderBaseDatagrid_center","开始导出","导出配置",0,_callbackExceptFrtWorkOrderBase);
		}
		function _callbackExceptFrtWorkOrderBase(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"工单-基本信息"+getSystemTime());
		}