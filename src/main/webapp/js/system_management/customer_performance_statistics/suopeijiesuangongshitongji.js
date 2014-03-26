function LoadOk() {
			if (document.readyState == "complete") {
				spRunsWest();
				spRunsEast('');
			} else {
				setTimeout("LoadOk();", 200);
			}
		}
		setTimeout("LoadOk();", 200);
  		//索赔结算工时统计west内容
  		var spRunsWest=function(){
  			$('#claimsHoursMainDatagrid').datagrid({
				url : 'customerPerformanceStatisticsAction!findClaimsHoursMain.action',
				fit : true,
				fitColumns : true,
				singleSelect:true,
				rownumbers : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				idField : 'id',
				loadMsg : "数据加载中，请稍后……",
				columns : [ [{
					field : 'stfId',
					title : '维修员编号',
					width : 100,
					hidden : true
				}, {
					field : 'stfName',
					title : '维修员',
					width : 100,
					sortable : true
				}, {
					field : 'itemAmount',
					title : '工时费',
					width : 100,
					sortable : true
				}
				]],
				onDblClickRow:function(rowIndex, rowData){
					if($('#claimsHoursForm').form('validate')){
						$('#claimsHours_stfId').combobox('setValue',rowData.stfId);
						var params=$('#claimsHoursForm').serialize();
						spRunsEast(params);
					}else{
						alertMsg('对不起，请输入正确的查询条件！', 'warning');
					}
				}
			});
  		}
  		//索赔结算工时统计east内容
  		var spRunsEast=function(params){
  			$('#claimsHoursDetailDatagrid').datagrid({
				url : 'customerPerformanceStatisticsAction!findClaimsHoursDetail.action?'+params,
				pagination : true,
				fit : true,
				singleSelect:true,
				fitColumns : true,
				idField : 'id',
				loadMsg : "数据加载中，请稍后……",
				columns : [[{
					field : 'claimantmTime',
					title : '索赔日期',
					width : 130,
					sortable : true
				},{
					field : 'claimantmId',
					title : '索赔单号',
					width : 110,
					sortable : true
				},{
					field : 'carLicense',
					title : '车辆牌照',
					width : 60,
					sortable : true
				},{
					field : 'itemName',
					title : '维修项目',
					width : 100,
					sortable : true
				},{
					field : 'itemAmount',
					title : '工时费',
					width : 100,
					sortable : true
				}
				]]
			});
  		}
  		
  		function queryClaimsHours(){
			if($('#claimsHoursForm').form('validate')){
				$('#claimsHoursMainDatagrid').datagrid('load', serializeObject($('#claimsHoursForm')));
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		function clearClaimsHours(){
		 $('#claimsHoursForm').form('clear');
		}
		function _exceptClaimsHours(){
			showEditDialog("claimsHoursDetailDatagrid",null,"claimsHoursDetailDatagrid_center","开始导出","导出配置",0,_callbackExceptClaimsHours);
		}
		function _callbackExceptClaimsHours(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"索赔结算工时统计"+getSystemTime());
		}