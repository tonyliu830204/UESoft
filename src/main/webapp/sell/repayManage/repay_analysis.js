var tbtitle;

$(function(){
    
    	//初始时间
		addInitDate($('#consultActualDate'),$('#consultActualDate2'));
		
		$('#infomation_send_manag_dialg_id').dialog({
			closed : true
		});
		
		//页面初始化时，设置按钮的状态
		$('#tt').tabs({   
			onSelect:function(title){  
			 tbtitle = title;
			 if(title=="跟踪满意度统计"){
			   	 $('#is3D').combobox('enable');
			 }else{
				 $('#is3D').combobox('disable');
			 }
		    }   
		});
	
		$('#datagrid_infomation_send_manag_dialg_id').datagrid({
			url : 'sellCoverAction!getSellCoverMange.action',
			fit : true,
			rownumbers : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'custom_Id',
			sortName:'id',
			sortOrder:'asc',
			loadMsg : "数据加载中，请稍后……",
			columns : [ [	{
			field : 'consultActualDate',
			title : '回访日期',
			width : 130
		},{
			field : 'consultDegree',
			title : '满意度',
			width : 80,
			hidden : true
			
		},{
			field : 'consultDegreeType',
			title : '满意度',
			width : 80
			
		},{
			field : 'consulTRate',
			title : '回访进度',
			width : 100,
			hidden : true
		
		},{
			field : 'consulTRateN',
			title : '回访进度',
			width : 80
		
		},{
			field : 'travelCourse',
			title : '行驶里程',
			width : 70
		
		},{
			field : 'consultSuggest',
			title : '意见',
			width : 110
			
		},{
			field : 'remark',
			title : '备注',
			width : 130
		
		},{
			field : 'consultCallState',
			title : '通话情况',
			width : 100,
			hidden : true
			
		},{
			field : 'consultCallStateN',
			title : '通话情况',
			width : 70
			
		},{
			field : 'carModel',
			title : '车型号',
			width : 100,
			hidden : true
			
		},{
			field : 'carModelName',
			title : '车型号',
			width : 125
			
		},{
			field : 'carColorName',
			title : '车颜色',
			width : 80
			
		},{
			field : 'vinCode',
			title : 'VIN号',
			width : 120
			
		},{
			field : 'carMotorNumber',
			title : '发动机号',
			width : 100
		},{
			field : 'carLicensePlate',
			title : '车牌照',
			width : 100
		},{
			field : 'xsCarSelData',
			title : '销售日期',
			width : 100
		},{
			field : 'xsCarSelRemark',
			title : '销售备注',
			width : 100
		},{
			field : 'xsCustomName',
			title : '客户姓名',
			width : 100
		},{
			field : 'sexName',
			title : '性别',
			width : 80
		},{
			field : 'xsCustomPhone',
			title : '固话',
			width : 100
		},{
			field : 'xsCustomTelephone',
			title : '手机',
			width : 100
		},{
			field : 'customPropertyN',
			title : '性质',
			width : 100
		},{
			field : 'xsCustomAddress',
			title : '地址',
			width : 100
		},{
			field : 'customTrade',
			title : '从事行业',
			width : 100
		},{
			field : 'xsCustomIncomeN',
			title : '收入',
			width : 100
		},{
			field : 'xsCustomBirthday',
			title : '出生年月',
			width : 100
		},{
			field : 'xsCustomDiplomaN',
			title : '学历',
			width : 100
		},{
			field : 'xsCustomCredentials',
			title : '身份证号',
			width : 100
		},{
			field : 'stfName',
			title : '业务员',
			width : 100
		}
			]]
			
		});	
		//跟踪项目统计
		 $('#customer_s').datagrid({
			url : 'sellCoverAction!getProjectStatistics.action',
			fit : true,
			fitColumns : true,
			singleSelect : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			rownumbers : true,
			idField : 'projectName',
			loadMsg : "数据加载中，请稍后……",
			columns : [ [ {field : 'projectName',title : '项目名称',width :230}, 
						{field : 'veryGood',title : '很好(s1)',width : 80}, 
						{field : 'good',title : '好(s2)',width : 80}, 
						{field : 'yiBan',title : '一般(s3)',width : 80}, 
						{field : 'notGood',title : '不好(s4)',width : 80}, 
						{field : 'veryNotGood',title : '很不好(s5)',width : 80}, 
						{field : 'rate',title : '满意率(s1+s2)',width : 100,
						formatter : function(value,rowData,rowIndex){
						if(value!=0){
									return value+"%";	
									}
									return value;				
						}}, 
						{field : 'sumCount',title : 's=(s1+s2+s3+s4+s5)',width : 80} 
						]]
						
		});
		
		$('#timely_analysis').datagrid({
				url : 'sellCoverAction!geTtimelyAnalysis.action',
				pagination : true,
				fit:true,
				fitColumns : true,
				sortOrder:'asc',
			    sortName:'xsCarSelId',
				singleSelect : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				rownumbers : true,
				loadMsg : "数据加载中，请稍后……",
				columns : [[ 
			 			{
							field : 'consultPlanDate',
							title : '日期',
							width : 100,
							sortable : true,
						},{
							field : 'planDate',
							title : '计划跟踪',
							width : 80,
							sortable : true
						},{
							field : 'actualDate',
							title : '实际跟踪',
							width : 80,
							sortable : true
						}, {
							field : 'planIn',
							title : '计划内跟踪',
							width : 80,
							sortable : true
						},
						{
							field : 'planOut',
							title : '计划外跟踪',
							width : 80,
							sortable : true
						},
						{
							field : 'nottrack',
							title : '未跟踪',
							width : 80,
							sortable : true
						},
						{
							field : 'timely',
							title : '跟踪及时度',
							width : 80,
							sortable : true,
							formatter : function(value,rowData,rowIndex){
							if(value!=0){
							 	return value+"%";	
								}
									return value;				
						}}
		        ]]
		    
	});
			
    	
	
		//跟踪记录汇总  初始化加载datagrid头信息
		$('#tb_khmanyidutongji_collect_id').datagrid({
			url : 'sellCoverAction!getDatagridValue.action',
			type : 'POST',
			newrap : false,
			striped : true,
			fit : true,
			width : 1024,
			height : 500,
			pageSize : 30,
			remoteSort : false,
			pagination : true,
			rownumbers : true,
			singleSelect : true
		});
		//调用该方法返回datagrid格式的字符串解析
			getDatagridString();
    	
		
		//初始化跟踪满意度统计
		$('#tb_khmanyidutongji_id').datagrid({
			url : 'sellCoverAction!getDateInfomation.action',
			type : 'POST',
			newrap : false,
			fitColumns : true,
			fit : true,
			width : 1024,
			height : 260,
			pageSize : 30,
			remoteSort : false,
			singleSelect : true,
			columns : [[
		            {field : 'consultActualDate',title : '时间',width : 100,sortable : true, align : 'center'},
		            {field : 'verySatisfaction',title : '很满意',width : 100,sortable : true, align : 'center'},
		            {field : 'satisfaction',title : '满意',width : 100,sortable : true, align : 'center'},
		            {field : 'ordinary',title : '一般',width : 100,sortable : true, align : 'center'},
		            {field : 'notSatisfaction',title : '不满意',width : 100,sortable : true, align : 'center'},
		            {field : 'veryNotSatisfaction',title : '很不满',width : 100,sortable : true, align : 'center'},
		            {field : 'not',title : '无',width : 100,sortable : true, align : 'center'}
		            ]],
		            onLoadSuccess : function(data){
					if(data.total == '0'){
						var body = $(this).data().datagrid.dc.body2;
					body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="6">暂无满足要求的信息！</td></tr>');
					}
					}
//					,onClickRow : function(rowIndex, rowData){
//						var form =  $('#customer_GzTj_form_id').form();
//						var rndImg = document.getElementById('snapMapImg');
//						var url = 'sellCoverAction!getMapbyTime.action?flag=true&consultActualDate3='+rowData.consultActualDate+'&'+form.serialize();
//						rndImg.setAttribute('src', url);
//					}
					,onClickCell : function(rowIndex, field, value){
//						analyseLoader('analyseLoaderSnapMap', 'snapMapImg');
//						var data=$('#bespeakAnalyseDatagrid').datagrid('options');
//						document.getElementById("snapMapImg").innerHTML=
//						"<img  onload=\"analyseLoaderHidden('analyseLoaderSnapMap','snapMapImg');\" src=\"bespeakAnalyseAction!findSnapMap.action?page="+data.pageNumber+"&rows="+data.pageSize+"&"+
						
						$('#tb_khmanyidutongji_id').datagrid('unselectRow',rowIndex);
						$('#tb_khmanyidutongji_id').datagrid('showColumn',field);
						var form =  $('#customer_GzTj_form_id').form();
						var rndImg = document.getElementById('snapMapImg');
						var url = 'sellCoverAction!getMapbyTime.action?flag=true&satisfactionName='+field+'&'+form.serialize();
						rndImg.setAttribute('src', url);
					}   
				});
  			});	
	
//获取返回的datagrid格式的字符串
var options = {};
var s = "";
function getDatagridString(){
s = "[[";
s = s + "{field : 'xsCarSelDate',title : '销售日期',width : 90,sortable : true	}, " +
		"{field : 'xsCustomName',title : '车主名称',width : 90,sortable : true	}," +
		"{field : 'xsCarModel',title : '车辆型号',width : 80,sortable : true	}, " +
		"{field : 'stfName',title : '销售顾问',width : 100,sortable : true	}, " +
		"{field : 'xsCustomTel',title : '电话号码',width :90,sortable : true	}, "+
		"{field : 'consultSuggest',title : '意见',width : 120,sortable : true	} ";
		
	$.ajax({
	type : 'POST', 
	url : 'sellCoverAction!getDatagridString.action',
	data : '',
	success : function(str){
	var newstr = str.replace('"',' ');
	var newstr = newstr.replace('"',' ');
		s += newstr
	 	s = s +"]]";
	 	options.url = 'sellCoverAction!getDatagridValue.action';
	 	options.columns = eval(s);
		$('#tb_khmanyidutongji_collect_id').datagrid(options);
		$('#tb_khmanyidutongji_collect_id').datagrid('reload');
		}
	});
}
  










//  //跟踪满意度统计添加不固定列方法
//	function addColome(resault,formvalue){
//		var array = new Array();
//		var str = "[[{field : 'date',title : '时间',width : 100,sortable : true, align : 'center'}";
//		array = resault.substring(2,resault.length-3).replace(/"/g, "").split(",");
//		for ( var i = 0; i < array.length; i++) {
//			str += ",{field : '"+array[i]+"',title : '"+array[i]+"',width : 80,sortable : true, align : 'center'} ";
//		}
//		str += "]]";
//		 options = {};
//		 options.url = 'sellCoverAction!getDateInfomation.action?consultActualDate='+formvalue.consultActualDate;
//		 options.columns = eval(str);
//			 $('#tb_khmanyidutongji_id').datagrid(options);
//			 $('#tb_khmanyidutongji_id').datagrid('reload');
//	} 
    //获取form表单数据传给后台action  解决无法传 page 和 rows 的时候 用此方法递交
	function doFindbyCondition(formid){
		var form =  formid.form();
		var formvalue = serializeObjectByflag(form,false);	
		if(tbtitle=="跟踪满意度统计"){
		//同时访问xxx方法获取要显示的月份以及月份对应的满意度统计信息
		$.ajax({
			type : 'POST', 
			url : 'sellCoverAction!getDateInfomation.action',
			dataType : 'json',
			data : formvalue,
			success : function(r){
				$('#tb_khmanyidutongji_id').datagrid('loadData',r);
				var rndImg = document.getElementById('snapMapImg');
				var url = 'sellCoverAction!getMapbyTime.action?flag=false&'+form.serialize();
				rndImg.setAttribute('src', url);
				
				}
			});
   	 	}if(tbtitle=="跟踪记录汇总"){
   	 		$('#tb_khmanyidutongji_collect_id').datagrid('load',formvalue);
   	 	}if(tbtitle=="跟踪项目统计"){
   	 		$('#customer_s').datagrid('unselectAll');
   	 		$('#customer_s').datagrid('load',formvalue);
   	 	}if(tbtitle=="回访及时性分析"){
   	 		$('#timely_analysis').datagrid('unselectAll');
   	 		$('#timely_analysis').datagrid('load',formvalue);
   	 	}
	
	}
    	//
 	
	//条件查询提交
	function doConditionSubmit(){
		$('#datagrid_infomation_send_manag_dialg_id').datagrid('unselectAll');
		$('#datagrid_infomation_send_manag_dialg_id').datagrid('load',serializeObjectByflag($('#form_infomation_send_manag_dialg_id'),false));
		//初始时间
		addInitDate($('#consultActualDate'),$('#consultActualDate2'));
		
	}
	//清空 
	function doClear(){
		$('#customer_GzTj_form_id').form('clear');
		var form =  $('#customer_GzTj_form_id').form();
		var formvalue = serializeObjectByflag(form,false);	
		if(tbtitle=="跟踪满意度统计"){
			$('#tb_khmanyidutongji_id').datagrid('load',formvalue);
			//初始时间
			addInitDate($('#consultActualDate'),$('#consultActualDate2'));
   	 	}if(tbtitle=="跟踪记录汇总"){
   	 		$('#tb_khmanyidutongji_collect_id').datagrid('load',formvalue);
   	 	//初始时间
   			addInitDate($('#consultActualDate'),$('#consultActualDate2'));
   	 	}if(tbtitle=="跟踪项目统计"){
   	 		$('#customer_s').datagrid('load',formvalue);
   	 	//初始时间
   			addInitDate($('#consultActualDate'),$('#consultActualDate2'));
   	 	}if(tbtitle=="回访及时性分析"){
   	 		$('#timely_analysis').datagrid('load',formvalue);
   	 	//初始时间
   			addInitDate($('#consultActualDate'),$('#consultActualDate2'));
   	 	}
   	 	
		}
	//清空
	function doClearTwo(){
		
		$('#form_infomation_send_manag_dialg_id').form('clear');
		$('#datagrid_infomation_send_manag_dialg_id').datagrid('unselectAll');
		$('#datagrid_infomation_send_manag_dialg_id').datagrid('load',serializeObjectByflag($('#form_infomation_send_manag_dialg_id'),false));
		$('#car_Brand_id').combobox('reload');
		$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
	}
	
	function sendinformation(){
	
		$('#form_infomation_send_manag_dialg_id').form('clear');	  
		 $('#infomation_send_manag_dialg_id').dialog('open');
		 getStartDate($('#date1'));
			$('#date2').val(getSystemTime());
		
			
	}
	function sendinformation2(){
		 $('#infomation_send_manag_dialg_id').dialog('close');
			
	}
	
			/**
		 * 
		 * 导出excel
		 * 选择要导出的列
		 */
		function _except(){
			if(tbtitle =='跟踪满意度统计'){
				var data =  $("#tb_khmanyidutongji_id").datagrid('getData'); 
				 if(data.rows.length==0){
					 alertMsg('数据列表为空，不能导出！', 'warning');
					 return ;
				}
				showEditDialog("tb_khmanyidutongji_id",null,"genzong","开始导出","导出配置",0,_callbackExcept);
				
			}else if(tbtitle =='跟踪项目统计'){
				var data =  $("#customer_s").datagrid('getData'); 
				 if(data.rows.length==0){
					 alertMsg('数据列表为空，不能导出！', 'warning');
					 return ;
				}
				showEditDialog("customer_s",null,"xiangmu","开始导出","导出配置",0,_callbackExcept2);	
			}else if(tbtitle=="回访及时性分析"){
				var data =  $("#timely_analysis").datagrid('getData'); 
				 if(data.rows.length==0){
					 alertMsg('数据列表为空，不能导出！', 'warning');
					 return ;
				}
				showEditDialog("timely_analysis",null,"timely","开始导出","导出配置",0,_callbackExcept3);	
			}else if(tbtitle=="跟踪记录汇总"){
				var data =  $("#tb_khmanyidutongji_collect_id").datagrid('getData'); 
				 if(data.rows.length==0){
					 alertMsg('数据列表为空，不能导出！', 'warning');
					 return ;
				}
			    showEditDialog("tb_khmanyidutongji_collect_id",null,"gzxm_all","开始导出","导出配置",0,_callbackExcept4);	
			}
			
			
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
			exportEsuyUIExcelFile(parentId,fieldNames,"跟踪满意度统计"+getSystemTime());
		}
		function _callbackExcept2(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"跟踪项目统计"+getSystemTime());
		}
		function _callbackExcept3(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"回访及时性分析"+getSystemTime());
		}
		function _callbackExcept4(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"跟踪记录汇总"+getSystemTime());
		}
		
		
		/**
		 * 打印字段设置
		 * 编辑、选择不同分组
		 * @return
		 */
		function _config(){
			if(tbtitle =='跟踪满意度统计'){
				var data =  $("#tb_khmanyidutongji_id").datagrid('getData'); 
				 if(data.rows.length==0){
					 alertMsg('数据列表为空，不能打印！', 'warning');
					 return ;
				}
				showEditDialog("tb_khmanyidutongji_id",personnelSumTable,"genzong","开始打印","打印配置",2,_print);
				
			}else if(tbtitle =='跟踪项目统计'){
				var data =  $("#customer_s").datagrid('getData'); 
				 if(data.rows.length==0){
					 alertMsg('数据列表为空，不能打印！', 'warning');
					 return ;
				}
				showEditDialog("customer_s",personnelSumTable,"xiangmu","开始打印","打印配置",2,_print);	
			}else if(tbtitle=="回访及时性分析"){
				var data =  $("#timely_analysis").datagrid('getData'); 
				 if(data.rows.length==0){
					 alertMsg('数据列表为空，不能打印！', 'warning');
					 return ;
				}
				showEditDialog("timely_analysis",personnelSumTable,"timely","开始打印","打印配置",2,_print);	
			}else if(tbtitle=="跟踪记录汇总"){
				var data =  $("#tb_khmanyidutongji_collect_id").datagrid('getData'); 
				 if(data.rows.length==0){
					 alertMsg('数据列表为空，不能打印！', 'warning');
					 return ;
				}
				showEditDialog("tb_khmanyidutongji_collect_id",personnelSumTable,"gzxm_all","开始打印","打印配置",2,_print);	
			}
		
		}
		/**
		 * 打印字段设置回调函数
		 * 将选择的列打印
		 * @return
		 */
		function _print(parentId,fieldNames){
			printEsuyUIPreview(parentId,fieldNames);
		}
		
		
		
		function _exceptTwo(){
			var data =  $("#datagrid_infomation_send_manag_dialg_id").datagrid('getData'); 
			 if(data.rows.length==0){
				 alertMsg('数据列表为空，不能导出！', 'warning');
				 return ;
			}
				showEditDialog("datagrid_infomation_send_manag_dialg_id",null,"info_table","开始导出","导出配置",0,_callbackExceptTwo);
		
		}
	
		function _callbackExceptTwo(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"跟踪管理信息"+getSystemTime());
		}

		function _configTwo(){
			var data =  $("#datagrid_infomation_send_manag_dialg_id").datagrid('getData'); 
			 if(data.rows.length==0){
				 alertMsg('数据列表为空，不能打印！', 'warning');
				 return ;
			}
				showEditDialog("datagrid_infomation_send_manag_dialg_id",personnelSumTable,"info_table","开始打印","打印配置",2,_printTwo);
			}

		function _printTwo(parentId,fieldNames){
			printEsuyUIPreview(parentId,fieldNames);
		}
		