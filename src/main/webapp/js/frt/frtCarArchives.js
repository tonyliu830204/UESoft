function LoadOk() {
			if (document.readyState == "complete") {
				execl();
				mainRuns();
				//findCarLicenseFormat("frtCarQueryCarId");
			} else {
				setTimeout("LoadOk();", 200);
			}
		}
		setTimeout("LoadOk();", 200);
		var execl=function(){
			$('#carArchivesTabs').tabs({   
				onSelect:function(title){
					if(title =='主档案'){
						unbindAllButton();
						$("#_export").bind("click",_exceptMain);
					}else if(title =='数据分析'){
						unbindAllButton();  
						$("#_export").bind("click",_exceptAnalyse);
					}
				}
			});
		}
		function unbindAllButton(){
			$("#_export").unbind();
			$('#_export').linkbutton('enable');
		}
		var mainRuns=function (){
			//车辆档案查询->主档案
			$('#carArchivesMain').datagrid({
				url : 'basCarArchivesAction!findAllCarByTerm.action',
				singleSelect : true,
				fit : true,
				pagination : true,
				rownumbers : true,
				frozenColumns : [[
					 {field:'carLicense',title:'车牌照',width:70,sortable:true}
			      ]],
			    columns : [[
					 {field:'carVin',title:'VIN号',width:140,sortable:true},
					 {field:'carMotorId',title:'发动机号',width:100,sortable:true},
		             {field:'cbrdName',title:'车辆品牌',width:60},
		             {field:'ctypeName',title:'车辆型号',width:60},
		             {field:'colorName',title:'车辆颜色',width:60,sortable:true},
			         {field:'cstlName',title:'车辆款式',width:70,sortable:true},
		             {field:'carBuyDate',title:'购车日期',width:70,sortable:true},
					 {field:'acceptBack',title:'接受回访',width:70,sortable:true},
		             {field:'carLicenseDate',title:'领证日期',width:70,sortable:true},
		             {field:'carLastRepairDate',title:'最后维修日期',width:80,sortable:true},
		             {field:'carRepairCnt',title:'车辆维修次数',width:80,sortable:true},
		             {field:'carLastRepairDistance',title:'最后维修里程数',width:90,sortable:true},
		             {field:'carBasinsuranceDate',title:'交强险期',width:70,sortable:true},
		             {field:'relcampName',title:'保险公司',width:120,sortable:true},
			         {field:'customName',title:'客户名称',width:80},
					 {field:'customAddr',title:'客户地址',width:120,sortable:true},
					 {field:'customTel1',title:'客户手机',width:90,sortable:true},
					 {field:'customTel2',title:'客户固话',width:90,sortable:true},
					 {field:'linkMan',title:'客户联系人',width:70,sortable:true},
					 {field:'carRelationPerson',title:'驾驶姓名',width:70,sortable:true},
					 {field:'carRelationTel1',title:'托修手机',width:90,sortable:true},
					 {field:'vipId',title:'会员号',width:70,sortable:true},
					 {field:'endTime',title:'会员到期',width:70,sortable:true},
					 {field:'vipBirthDay',title:'会员生日',width:70,sortable:true},
					 {field:'pareaName',title:'客户区域',width:60,sortable:true},
					 {field:'temp1',title:'任职单位(无)',width:60,sortable:true},
					 {field:'receivePerson',title:'最近接待员',width:60,sortable:true},
   				     {field:'carId',title:'车辆编号',width:70,sortable:true},
					 {field:'slsName',title:'车辆购买地',width:80,sortable:true},
					 {field:'temp2',title:'自编号(无)',width:60,sortable:true},
		             {field:'createDate',title:'创建日期',width:130,sortable:true},
		             {field:'carRemark',title:'备注',width:80,sortable:true},
		             {field:'temp3',title:'保养提醒(无)',width:60,sortable:true},
		             {field:'carExaminedDate',title:'年审到期',width:70,sortable:true},
		             {field:'carAnnualDate',title:'年检到期',width:70,sortable:true},
		             {field:'carLastMaintDate',title:'最后保养日期',width:80,sortable:true},
		             {field:'carLastMaintDistance',title:'最后保养里程数',width:90,sortable:true},
					 {field:'carMaintCnt',title:'车辆保养次数',width:80,sortable:true},
		             {field:'carNextMaintDate',title:'预计下次保养日期',width:100,sortable:true},
		             {field:'carNextMaintDistance',title:'预计下次保养里程',width:100,sortable:true},
		             {field:'carDistancePerDay',title:'日均里程数',width:80,sortable:true},
		             {field:'notIntoTheStoreDays',title:'未来厂天数',width:80,sortable:true}
		          ]],
		          onLoadSuccess:function(data){
		          		var tempData=JSON.stringify(data);
		          		var value=$('#carArchives_analyseWay').combobox('getValue');
		          		analyseRuns(tempData,value);
		          },
		          onDblClickRow:function(rowIndex, rowData){
		          	detailRuns();
		          	var url="basCarArchivesAction!findAllReceptionByCarId.action?";
					url+='carId='+rowData.carId;
					$('#carArchivesDetail').treegrid({
						url:url
					});
		          }
			});
			//车辆档案查询->数据分析
			$('#carArchivesAnalyse').datagrid({
				url : 'basCarArchivesAction!findAllCarAnalyse.action',
				singleSelect : true,
				fit : true,
				pagination : true,
				rownumbers : true,
				fitColumns:true,
			    columns : [[
		             {field:'analyseWay',title:'分析类型',width:100,sortable:true},
		             {field:'analyseCount',title:'数量',width:80,sortable:true},
		             {field:'analyseRate',title:'百分比',width:80,sortable:true,
		             	formatter : function(value, row, index) {
							var temp=value*100;
							var temps=temp+"";
							if(!(temps.indexOf(".")==-1)&&(temps.indexOf(".")+3)<=(temps.length)){
								temp=temps.substring(0,(temps.indexOf(".")+3));
							}
							return temp+"%";
						}
		             }
		          ]]
			});
			detailRuns();
		}
		var detailRuns=function (){
			//车辆档案查询->维修记录
			$('#carArchivesDetail').treegrid({
				url : 'basCarArchivesAction!findAllReceptionByCarId.action',
				singleSelect : true,
				fit : true,
				pagination : true,
				rownumbers : true,
				fitColumns:true,
				animate:true,
				autoRowHeight:true,
				loadMsg : "数据加载中，请稍后……",
				idField : 'receptionId',
				treeField : 'items',
			    columns : [[
					 {field:'interDate',title:'入厂日期',width:130,sortable:true},
					 {field:'receptionDistance',title:'行驶里程',width:100,sortable:true},
		             {field:'stfName',title:'接待员',width:60},
		             {field:'receptionFactTime',title:'出厂日期',width:60},
		             {field:'receptionTechnicianName',title:'维修技师',width:60,sortable:true},
			         {field:'receptionId',title:'工单号',width:70,sortable:true},
		             {field:'preclrSumAmount',title:'合计金额',width:70,sortable:true},
					 {field:'items',title:'维修项目',width:70,sortable:true}
		          ]],
		        onBeforeExpand : function (rowData){
					//动态设置展开查询的url
						var url = 'basCarArchivesAction!findAllReceptionByCarIdForChild.action?receptionId='+rowData.receptionId;
						$('#carArchivesDetail').treegrid("options").url = url;
						return true;
				}
			});
		}
		var analyseRuns=function (data,type){
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : 'basCarArchivesAction!findAllCarAnalyse.action?analyseWay='+type,
				data:'&analyseData='+data,
				success : function(r) {
					$('#carArchivesAnalyse').datagrid('loadData',r);
				},
				error : function (r){
				   if(r.readyState == '0' && r.status == '0'){
					   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
				   } 
			   	}
			});
		}
		function searchFc(){
			if($('#carArchives1Form').form('validate')&&$('#carArchives2Form').form('validate')){
				var url="basCarArchivesAction!findAllCarByTerm.action?";
				url+=$('#carArchives1Form').serialize()+'&'+$('#carArchives2Form').serialize();
				$('#carArchivesMain').datagrid({
					url:url
				});
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		function clearFc(){
			$('#carArchives1Form').form('clear');
			$('#carArchives2Form').form('clear');
		}
		function _exceptMain(){
			$.messager.confirm('优亿软件提示', '选择导出主档案(确认)或维修记录(取消)?', function(r){
				if (r){
					showEditDialog("carArchivesMain",null,"carArchivesMain_center","开始导出","导出配置",0,_callbackExceptMain);
				}else{
					showEditDialog("carArchivesDetail",null,"carArchivesDetail_center","开始导出","导出配置",0,_callbackExceptDetail);	
				}
			});
		}
		function _exceptAnalyse(){
			showEditDialog("carArchivesAnalyse",null,"carArchivesAnalyse_center","开始导出","导出配置",0,_callbackExceptAnalyse);	
		}
		function _callbackExceptMain(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"车辆档案查询-主档案"+getSystemTime());
		}
		function _callbackExceptDetail(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"车辆档案查询-维修记录"+getSystemTime());
		}
		function _callbackExceptAnalyse(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"车辆档案查询-数据分析"+getSystemTime());
		}