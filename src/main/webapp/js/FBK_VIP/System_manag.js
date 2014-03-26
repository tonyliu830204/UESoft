$(function(){
	
	$('#tabs_accessories_find_id').tabs({
		onSelect:function(title){
		    pdtitle = title;
			 if(title=="盘点单明细"){
				  var value = $('#pandian_huizong_id').datagrid('getSelections');
				  if(value.length>0){
					  $('#form_pandianmingxi_id').form('load',value[0]);
					  $('#pandian_mingxi_id').datagrid({url : 'accesssoriesInventoryAction!getStInventoryDetailById.action?stinvm_Id='+value[0].stinvm_Id});
			      }
			}
	     }
	});
	
	//车辆流失信息 明细
	$('#tab_carlost_infor_id').tabs({
		onSelect:function(title){
			 if(title=="车辆流失情况明细"){
				  var value = $('#datagrid_carlost_info_id').datagrid('getSelections');
				  if(value.length>0){
					  $('#form_carlost_infor_detail_id').form('load',value[0]);
					  $('#treegrid_carlost_info_id').treegrid({url : 'carLostAnalysisAction!getDetailsInforById.action?car_Id='+value[0].car_Id});
			      }
			}
	     }
	});
	
	
	//服务业绩日报表
	$('#Performance_daily_report_center_id').datagrid({

		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			checkbox : true
		},  {
			field : 'id1',
			title : '服务人员',
			width : 100,
			sortable : true
			
		},{
			field : 'id2',
			title : '服务时间',
			width : 100,
			sortable : true
		}, {
			field : 'id3',
			title : '服务内容',
			width : 100,
			sortable : true
			
		}, {
			field : 'id4',
			title : '所需时间',
			width : 100,
			sortable : true
		
		}, {
			field : 'id5',
			title : '完成情况',
			width : 100,
			sortable : true
			
		}, {
			field : 'id6',
			title : '服务质量',
			width : 100,
			sortable : true
		
		}, {
			field : 'id7',
			title : '备注',
			width : 100,
			sortable : true
		}
		]],

		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-cancel',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnexport',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-']
	});
	//维修客流分析
	//维修时间段分析
	$('#Maintenance_traffic_analysis_time_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [{
			checkbox : true
		},  {
			field : 'id1',
			title : '登记日期',
			width : 100,
			sortable : true
			
		} ] ],
		columns : [ [ {
			field : 'id2',
			title : '08前',
			width : 100,
			sortable : true
		}, {
			field : 'id3',
			title : '08-09',
			width : 100,
			sortable : true
			
		}, {
			field : 'id4',
			title : '09-10',
			width : 100,
			sortable : true
		
		}, {
			field : 'id5',
			title : '10-11',
			width : 100,
			sortable : true
			
		}, {
			field : 'id6',
			title : '11-12',
			width : 100,
			sortable : true
		
		}, {
			field : 'id7',
			title : '12-13',
			width : 100,
			sortable : true
		}, {
			field : 'id8',
			title : '13-14',
			width : 100,
			sortable : true
		}, {
			field : 'id9',
			title : '14-15',
			width : 100,
			sortable : true
		}, {
			field : 'id10',
			title : '15-16',
			width : 100,
			sortable : true
		}, {
			field : 'id11',
			title : '16-17',
			width : 100,
			sortable : true
		}, {
			field : 'id12',
			title : '17-18',
			width : 100,
			sortable : true
		}, {
			field : 'id13',
			title : '18后',
			width : 100,
			sortable : true
		}
		]]

	});
	//维修接待员分析
	$('#Maintenance_traffic_analysis_front_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [{
			checkbox : true
		},  {
			field : 'id1',
			title : '前台接待',
			width : 100,
			sortable : true
			
		} ] ],
		columns : [ [ {
			field : 'id2',
			title : '定期保养',
			width : 100,
			sortable : true
		}, {
			field : 'id3',
			title : '一般修理',
			width : 100,
			sortable : true
			
		}, {
			field : 'id4',
			title : '首保',
			width : 100,
			sortable : true
		
		}, {
			field : 'id5',
			title : '索赔',
			width : 100,
			sortable : true
			
		}, {
			field : 'id6',
			title : '理赔',
			width : 100,
			sortable : true
		
		}, {
			field : 'id7',
			title : '事故',
			width : 100,
			sortable : true
		}, {
			field : 'id8',
			title : '钣金',
			width : 100,
			sortable : true
		}, {
			field : 'id9',
			title : '喷漆',
			width : 100,
			sortable : true
		}, {
			field : 'id10',
			title : '钣金喷漆',
			width : 100,
			sortable : true
		}, {
			field : 'id11',
			title : '返修',
			width : 100,
			sortable : true
		}, {
			field : 'id12',
			title : '免费检查',
			width : 100,
			sortable : true
		}, {
			field : 'id13',
			title : 'PDI检测',
			width : 100,
			sortable : true
		}, {
			field : 'id14',
			title : '外出救援',
			width : 100,
			sortable : true
		}, {
			field : 'id15',
			title : '装具',
			width : 100,
			sortable : true
		}, {
			field : 'id16',
			title : '合计',
			width : 100,
			sortable : true
		}
		]]

	});
	//维修品牌分析
	$('#Maintenance_traffic_analysis_brand_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [{
			checkbox : true
		},  {
			field : 'id1',
			title : '汽车品牌',
			width : 100,
			sortable : true
			
		} ] ],
		columns : [ [ {
			field : 'id2',
			title : '定期保养',
			width : 100,
			sortable : true
		}, {
			field : 'id3',
			title : '一般修理',
			width : 100,
			sortable : true
			
		}, {
			field : 'id4',
			title : '首保',
			width : 100,
			sortable : true
		
		}, {
			field : 'id5',
			title : '索赔',
			width : 100,
			sortable : true
			
		}, {
			field : 'id6',
			title : '理赔',
			width : 100,
			sortable : true
		
		}, {
			field : 'id7',
			title : '事故',
			width : 100,
			sortable : true
		}, {
			field : 'id8',
			title : '钣金',
			width : 100,
			sortable : true
		}, {
			field : 'id9',
			title : '喷漆',
			width : 100,
			sortable : true
		}, {
			field : 'id10',
			title : '钣金喷漆',
			width : 100,
			sortable : true
		}, {
			field : 'id11',
			title : '返修',
			width : 100,
			sortable : true
		}, {
			field : 'id12',
			title : '免费检查',
			width : 100,
			sortable : true
		}, {
			field : 'id13',
			title : 'PDI检测',
			width : 100,
			sortable : true
		}, {
			field : 'id14',
			title : '外出救援',
			width : 100,
			sortable : true
		}, {
			field : 'id15',
			title : '装具',
			width : 100,
			sortable : true
		}, {
			field : 'id16',
			title : '合计',
			width : 100,
			sortable : true
		}
		]]

	});
	//车辆在修情况分析
	$('#Maintenance_traffic_analysis_situation_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [{
			checkbox : true
		},  {
			field : 'id1',
			title : '车辆性质',
			width : 100,
			sortable : true
			
		} ] ],
		columns : [ [ {
			field : 'id2',
			title : '车辆品牌',
			width : 100,
			sortable : true
		}, {
			field : 'id3',
			title : '车辆牌照',
			width : 100,
			sortable : true
			
		}, {
			field : 'id4',
			title : '车辆类型',
			width : 100,
			sortable : true
		
		}, {
			field : 'id5',
			title : '客户民称',
			width : 100,
			sortable : true
			
		}, {
			field : 'id6',
			title : '走后维修日期',
			width : 100,
			sortable : true
		
		}, {
			field : 'id7',
			title : '维修次数',
			width : 100,
			sortable : true
		}, {
			field : 'id8',
			title : '客户地址',
			width : 100,
			sortable : true
		}, {
			field : 'id9',
			title : '电话一',
			width : 100,
			sortable : true
		}, {
			field : 'id10',
			title : '电话二',
			width : 100,
			sortable : true
		}
		]]
	});
	//维修记录查询
	$('#Maintenance_traffic_analysis_record_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [{
			checkbox : true
		},  {
			field : 'id1',
			title : '进厂日期',
			width : 100,
			sortable : true
			
		} ] ],
		columns : [ [ {
			field : 'id2',
			title : '工单号',
			width : 100,
			sortable : true
		}, {
			field : 'id3',
			title : '车牌号',
			width : 100,
			sortable : true
			
		}, {
			field : 'id4',
			title : '客户名称',
			width : 100,
			sortable : true
		
		}, {
			field : 'id5',
			title : '车辆型号',
			width : 100,
			sortable : true
			
		}, {
			field : 'id6',
			title : '结算状态',
			width : 100,
			sortable : true
		
		}, {
			field : 'id7',
			title : '接待员',
			width : 100,
			sortable : true
		}, {
			field : 'id8',
			title : '维修类别',
			width : 100,
			sortable : true
		}, {
			field : 'id9',
			title : '电话',
			width : 100,
			sortable : true
		}, {
			field : 'id10',
			title : '发动机号',
			width : 100,
			sortable : true
		}, {
			field : 'id11',
			title : 'VIN编号',
			width : 100,
			sortable : true
		}, {
			field : 'id12',
			title : '里程',
			width : 100,
			sortable : true
		}, {
			field : 'id13',
			title : '购买日期',
			width : 100,
			sortable : true
		}, {
			field : 'id14',
			title : '主要修理项目',
			width : 100,
			sortable : true
		}, {
			field : 'id15',
			title : '送修人',
			width : 100,
			sortable : true
		}, {
			field : 'id16',
			title : '联系电话',
			width : 100,
			sortable : true
		}, {
			field : 'id17',
			title : '地址',
			width : 100,
			sortable : true
		}
		]]
	});
	//车辆流失情况汇总
	$('#datagrid_carlost_info_id').datagrid({
		url : '',
		pagination : true,
		singleSelect : true,
		fit : true,
		rownumbers : true,
		fitColumns : false,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'car_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'car_Id',
			title : '车辆编号',
			width : 100,
			sortable : true
			
		},{
			field : 'car_License',
			title : '车辆牌照',
			width : 100,
			sortable : true
			
		},{
			field : 'car_Vin',
			title : 'VIN号',
			width : 100,
			sortable : true
		},{
			field : 'cbrd_Name',
			title : '品牌',
			width : 100,
			sortable : true
			
		},{
			field : 'ctype_Name',
			title : '型号',
			width : 100,
			sortable : true
		
		},{
			field : 'car_Last_Repair_Date',
			title : '最近维修日期',
			width : 100,
			sortable : true
			
		},{
			field : 'car_Repair_Cnt',
			title : '维修次数',
			width : 100,
			sortable : true
		
		},{
			field : 'car_Last_Maint_Distance',
			title : '最后里程',
			width : 100,
			sortable : true
		},{
			field : 'custom_Name',
			title : '客户名称',
			width : 100,
			sortable : true
		},{
			field : 'lost_date',
			title : '未来厂天数',
			width : 100,
			sortable : true
		},{
			field : 'custom_Tel1',
			title : '客户电话一',
			width : 100,
			sortable : true
		},{
			field : 'custom_Tel2',
			title : '客户电话二',
			width : 100,
			sortable : true
		},{
			field : 'linkman',
			title : '驾驶姓名',
			width : 100,
			sortable : true
		},{
			field : 'linkman_Tel',
			title : '电话',
			width : 100,
			sortable : true
		},{
			field : 'invoicing_Address',
			title : '通信地址',
			width : 100,
			sortable : true
		},{
			field : 'car_Distance_Per_Day',
			title : '日均里程',
			width : 100,
			sortable : true
		},{
			field : 'color_Name',
			title : '车身颜色',
			width : 100,
			sortable : true
		},{
			field : 'custom_Addr',
			title : '客户地址',
			width : 100,
			sortable : true
		},{
			field : 'nowdate',
			title : '回访日期',
			width : 100,
			sortable : true
		},{
			field : 'nextdate',
			title : '下次回访日期',
			width : 100,
			sortable : true
		},{
			field : 'stf_Name',
			title : '最近接待员',
			width : 100,
			sortable : true
		},{
			field : 'parea_Zip',
			title : '邮政编码',
			width : 100,
			sortable : true
		},{
			field : 'car_Buy_Date',
			title : '购车日期',
			width : 100,
			sortable : true
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
			
			$('#tab_carlost_infor_id').tabs('select',"车辆流失情况明细");
			
		}
		
	});
	
	//未进店天数统计
	$('#datagrid_carlost_count_id').datagrid({
		url : 'carLostAnalysisAction!finishvisite.action',
		singleSelect : true,
		fit : true,
		rownumbers : true,
		fitColumns : false,
		idField : '',
		loadMsg : "数据加载中，请稍后……",
		columns : [[ {
			field : 'times',
			title : '时段',
			width : 150
			
		},{
			field : 'novisite',
			title : '未访问',
			width : 60
			
		},{
			field : 'finishvisite',
			title : '已访问',
			width : 60 //
		}]],
		onClickRow : function(rowIndex, rowData){
			$.ajax({
				type : 'POST',
				url : 'carLostAnalysisAction!getCollectinfor.action',
				data : rowData,
			    dataType : 'json',
				success : function(r){
						if(rowData.times=="准流失车辆"){
							$('#datagrid_carlost_info_id').datagrid({url : 'carLostAnalysisAction!getCollectinfor.action?times=A'});
						}else if(rowData.times=="其他"){
							$('#datagrid_carlost_info_id').datagrid({url : 'carLostAnalysisAction!getCollectinfor.action?times=B'});
						}else{
							$('#datagrid_carlost_info_id').datagrid({url : 'carLostAnalysisAction!getCollectinfor.action?times='+rowData.times});
						}
					}
			   });
			}
	});
	
	//车辆流失情况   south内容
	$('#treegrid_carlost_info_id').treegrid({
		url:'',
		fit : true,
	    fitColumns : true,
	    idField:'reception_Id',  
		treeField:'inter_Date',
		columns:[[
		    {field:'inter_Date',title:'入厂日期',width:200},
			{field:'car_Last_Maint_Distance',title:'行驶里程',width:200},
			{field:'stf_Name',title:'接车员',width:200},
			{field:'exp_Del_Car_Time',title:'出厂日期',width:200},
			{field:'prop_Rep_Per',title:'主修工',width:200},
			{field:'reception_Id',title:'施工单号',width:200},
			{field:'preclr_Sum_Amount',title:'合计金额',width:200},
			{field:'rcpitem_Name',title:'维修项目',width:200}
		]],onBeforeExpand:function(rowData){
		//动态设置展开查询的url
		var url = 'carLostAnalysisAction!getChildMenus.action?reception_Id='+ rowData.reception_Id;
		$('#treegrid_carlost_info_id').treegrid("options").url = url;
		return true;
    }
	});
	
	//业务员业绩统计
	$('#yewuyuanyejitongji_id').datagrid({

		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		
		columns : [ [{
			field : 'id1',
			title : '业务员',
			width : 100,
			sortable : true
			
		},{
			field : 'id12',
			title : '结算台次',
			width : 100,
			sortable : true
			
		},{
			field : 'id3',
			title : '应收金额',
			width : 100,
			sortable : true
		
		},{
			field : 'id10',
			title : '实收金额',
			width : 100,
			sortable : true
		
		},{
			field : 'id11',
			title : '技协费',
			width : 100,
			sortable : true
		
		},{
			field : 'id12',
			title : '实收金额  - 技协费',
			width : 100,
			sortable : true
		}
		]]
	
	});
	//维修接待和班组派工分析
	$('#weixiu_paigong_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		
		columns : [ [{
			field : 'id1',
			title : '序号',
			width : 100,
			sortable : true
		}, {
			field : 'id12',
			title : '前台接待',
			width : 100,
			sortable : true
		}, {
			field : 'id3',
			title : '接待量',
			width : 100,
			sortable : true
		}, {
			field : 'id10',
			title : '接待比例',
			width : 100,
			sortable : true
		}
		]]
	
	});
	
	//维修人员业绩统计汇总
	$('#weixiuyejitongjihuizong_id').datagrid({

		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		
		columns : [ [{
			field : 'id1',
			title : '编号',
			width : 100,
			sortable : true
			
		}, {
			field : 'id12',
			title : '维修人员',
			width : 100,
			sortable : true
			
		}, {
			field : 'id4',
			title : '工时金额',
			width : 100,
			sortable : true
		
		}, {
			field : 'id5',
			title : '配件金额',
			width : 100,
			sortable : true
		
		}, {
			field : 'id6',
			title : '配件成本',
			width : 100,
			sortable : true
		}
		]]
	
	});
	//索赔结算工时统计   east内容
	$('#suopeijiesuan_center_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		
		columns : [[{
			field : 'id1',
			title : '序号',
			width : 100,
			sortable : true
		},{
			field : 'id2',
			title : '索赔日期',
			width : 100,
			sortable : true
		},{
			field : 'id3',
			title : '索赔单号',
			width : 100,
			sortable : true
		},{
			field : 'id4',
			title : '项目维修',
			width : 100,
			sortable : true
		},{
			field : 'id5',
			title : '车辆牌照',
			width : 100,
			sortable : true
		},{
			field : 'id6',
			title : '工时费',
			width : 100,
			sortable : true
		}
		]]
	});
	
//索赔结算工时统计west内容
	$('#suopeijiesuan_west_id').datagrid({
		url : '',
		fit : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		
		columns : [ [{
			field : 'id1',
			title : '序号',
			width : 100,
			sortable : true
			
		}, {
			field : 'id12',
			title : '维修人员',
			width : 100,
			sortable : true
			
		}, {
			field : 'id3',
			title : '工时费',
			width : 100,
			sortable : true
		}
		]]
	});
	//结算工时查询
	$('#jisuangongsshi_find_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		
		columns : [ [ {
			field : 'id1',
			title : '员工/车间',
			width : 100,
			sortable : true
		}, {
			field : 'id2',
			title : '工单号',
			width : 100,
			sortable : true
		}, {
			field : 'id3',
			title : '车牌照/类型',
			width : 100,
			sortable : true
		}, {
			field : 'id4',
			title : '项目维修',
			width : 100,
			sortable : true
		}, {
			field : 'id5',
			title : '施工工时',
			width : 100,
			sortable : true
		}, {
			field : 'id6',
			title : '内部工时',
			width : 100,
			sortable : true
		}, {
			field : 'id7',
			title : '客户名称',
			width : 100,
			sortable : true
		}, {
			field : 'id8',
			title : '进厂日期',
			width : 100,
			sortable : true
		}, {
			field : 'id9',
			title : '完工日期',
			width : 100,
			sortable : true
		}
		]]
	
	
	});
	//维修人员业绩统计
	$('#weixiuyeji_tongji_id').datagrid({
		url : 'customerPerformanceStatisticsAction!getRepairResault.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		
		columns : [ [{
			field : 'stf_Name',
			title : '员工姓名',
			width : 100,
			sortable : true
			
		}, {
			field : 'repgrp_Name',
			title : '维修班组',
			width : 100,
			sortable : true
			
		}, {
			field : 'reception_Id',
			title : '工单号',
			width : 100,
			sortable : true
		
		}, {
			field : 'custom_Name',
			title : '客户名称',
			width : 100,
			sortable : true
			
		}, {
			field : 'car_License',
			title : '车牌照',
			width : 100,
			sortable : true
		
		}, {
			field : 'ctype_Name',
			title : '车类型',
			width : 100,
			sortable : true
		}, {
			field : 'repitem_Num',
			title : '施工工时',
			width : 100,
			sortable : true
		}, {
			field : 'id8',
			title : '工时金额',
			width : 100,
			sortable : true
		}, {
			field : 'id9',
			title : '配件金额',
			width : 100,
			sortable : true
		}, {
			field : 'preclr_Wktime_Rate',
			title : '工时折扣',
			width : 100,
			sortable : true
		}, {
			field : 'id11',
			title : '业绩',
			width : 100,
			sortable : true
		}
		]]
	
	});
	
	
	//历史库存量查询
	var $factorysave_find_id = $('#factorysave_find_id');
	$factorysave_find_id.datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		
		columns : [ [ {
			checkbox : true
		},{
			field : 'id1',
			title : '配件代码',
			width : 100,
			sortable : true
		}, {
			field : 'id2',
			title : '配件名称',
			width : 100,
			sortable : true
			
		}, {
			field : 'id3',
			title : '库存量',
			width : 100,
			sortable : true
		
		}, {
			field : 'id4',
			title : '成本价',
			width : 100,
			sortable : true
			
		}, {
			field : 'id5',
			title : '成本额',
			width : 100,
			sortable : true
		
		}, {
			field : 'id6',
			title : '库位',
			width : 100,
			sortable : true
		}, {
			field : 'id7',
			title : '仓别',
			width : 100,
			sortable : true
		}]],

		toolbar : [ {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-cancel',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-', {
			id : 'btnedit',
			text : '设置',
			iconCls : 'icon-help',
			handler : function (){
		   	alert('!!!!!!');}
		}, '-']

	});
	
	//选择配件时  查询配件信息
	$('#datagrid_accessories_find_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		singleSelect : false,
		rownumbers : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'parts_Id',
		loadMsg : "数据加载中，请稍后……",
		
		columns : [ [ {
			field : 'parts_Id',
			title : '配件代码',
			width : 100,
			sortable : true
		}, {
			field : 'parts_Name',
			title : '配件名称',
			width : 100,
			sortable : true
			
		}, {
			field : 'parts_Sell_Price',
			title : '销售价格',
			width : 100,
			sortable : true
		
		}, {
			field : 'stinvd_Price1',
			title : '含税价',
			width : 100,
			sortable : true
		
		}, {
			field : 'stinvd_Price',
			title : '未税价',
			width : 100,
			sortable : true
		
		}, {
			field : 'pbrd_Name',
			title : '配件品牌',
			width : 100,
			sortable : true
			
		}, {
			field : 'parts_Need_Point',
			title : '库存量',
			width : 100,
			sortable : true
		
		}, {
			field : 'parts_Library',
			title : '库位',
			width : 100,
			sortable : true
		}, {
			field : 'store_Name',
			title : '仓别',
			width : 100,
			sortable : true
		}, {
			field : 'ptype_Name',
			title : '配件类型',
			width : 100,
			sortable : true 
		}, {
			field : 'fit_Ptype',
			title : '适合车型',
			width : 200,
			sortable : true
		}, {
			field : 'punit_Name',
			title : '单位',
			width : 50,
			hidden : true,
			sortable : true
		}
		]]
  	});
});