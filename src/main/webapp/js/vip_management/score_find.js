var ttitle = '';
$(function(){
	$('#tb_id').tabs({
		border:false,
		onSelect:function(title,index){
				if ( title == '积分汇总'){
					load1();
					return;
				}
				if ( title == '维修积分'){
					load2();
					return;
				}
				if ( title == '销售积分'){
					load3();
					return;
				}
				if ( title == '储值赠分'){
					load4();
					return;
				}
				if ( title == '赠送积分'){
					load5();
					return;
				}	
				if ( title == '兑换积分'){
					load6();
					return;
				}
				if ( title == '会员卡升级/降级'){
					load7();
					return;
				}
				if ( title == '续会赠分'){
					load8();
					return;
				}
				if ( title == '退会扣分'){
					load9();
					return;
				}
			}
	});
});

//积分综合查询 积分汇总
function load1(){
	$('#datagrid_vip_jifenzonghechaxun_id').datagrid({
		url : projectPath+'integralIntegratedQueryAction!getIntegralIntegratedQuery.action',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vip_Id',
		sortName:'vip_Id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
				field : 'vip_Id',
				title : '会员编号',
				width : 110,
				sortable : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width : 100,
				sortable : true
			},{
				field : 'car_License',
				title : '车辆牌照',
				width : 70,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 140,
				sortable : true
			},{
				field : 'vip_Name',
				title : '会员名称',
				width : 100,
				sortable : true
			},{
				field : 'vip_Birthday',
				title : '出生年月',
				width : 80,
				sortable : true
			},{
				field : 'vip_Tel',
				title : '联系电话',
				width : 90,
				sortable : true
			},{
				field : 'vip_Level_Name',
				title : '会员等级',
				width : 70,
				sortable : true
			},{
				field : 'vip_Group_Name',
				title : '会员分组',
				width : 70,
				sortable : true
			},{
				field : 'vip_Status',
				title : '会员卡状态',
				width : 75,
				sortable : true
			},{
				field : 'jion_Time',
				title : '入会日期',
				width : 80,
				sortable : true
			},{
				field : 'end_Time',
				title : '会员到期',
				width : 80,
				sortable : true
			},{
				field : 'vip_Age',
				title : '会龄/月',
				width : 50,
				sortable : true
			},{
				field : 'vip_Integral',//指可用积分
				title : '当前积分',
				width : 70,
				sortable : true
			},{
				field : 'vip_Balance',
				title : '卡内余额',
				width : 70,
				sortable : true
			},{
				field : 'rec_Amount',
				title : '累计储值金额',
				width : 90,
				sortable : true
			},{
				field : 'give_Amount',
				title : '累计储值赠送金额',
				width : 90,
				sortable : true
			},{
				field : 'give_Inte',
				title : '累计储值赠分',
				width : 85,
				sortable : true
			},{
				field : 'vip_Balance14',
				title : '累计维修积分',
				width : 85,
				sortable : true
			},{
				field : 'vip_Balance15',
				title : '累计销售积分',
				width : 85,
				sortable : true
			},{
				field : 'vip_Balance18',
				title : '累计兑换扣分',
				width : 85,
				sortable : true
			},{
				field : 'vip_Balance19',
				title : '累计会员卡升级赠分',
				width : 120,
				sortable : true
			},{
				field : 'vip_Balance20',
				title : '累计会员卡降级扣分',
				width : 120,
				sortable : true
			},{
				field : 'vip_Balance21',
				title : '累计续会赠分',
				width : 85,
				sortable : true
			},{
				field : 'vip_Balance22',
				title : '累计退会扣分',
				width : 85,
				sortable : true
			}
		]]
	});
}

//积分综合查询 维修积分
function load2(){
	$('#datagrid_vip_jifenzonghechaxun_id1').datagrid({
		url : projectPath+'integralIntegratedQueryAction!getMaintenancePointsInquiry.action',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'STOCK_ID',
		sortName:'STOCK_ID',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
				field : 'STOCK_ID',
				title : '积分编号',
				hidden : true
			},{
				field : 'vip_Name',
				title : '会员名称',
				width : 100,
				sortable : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width : 100,
				sortable : true
			},{
				field : 'vip_Level_Name',
				title : '会员等级',
				width : 80,
				sortable : true
			},{
				field : 'vip_Group_Name',
				title : '会员分组',
				width : 80,
				sortable : true
			},{
				field : 'car_License',
				title : '车辆牌照',
				width : 70,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 130,
				sortable : true
			},{
				field : 'preclr_Time',
				title : '结算日期',
				width : 110,
				sortable : true
			},{
				field : 'preclr_Id',
				title : '结算单号',
				width : 70,
				sortable : true
			},{
				field : 'reception_Id',
				title : '工单号',
				width : 70,
				sortable : true
			},{
				field : 'vip_Integral',
				title : '维修积分',
				width : 80,
				sortable : true
			},{
				field : 'operatorName',
				title : '经办人',
				width : 80,
				sortable : true
			}
		]]
	});
}

//积分综合查询 销售积分
function load3(){
	$('#datagrid_vip_jifenzonghechaxun_id2').datagrid({
		url : projectPath+'integralIntegratedQueryAction!getSellPointsInquiry.action',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'STOCK_ID',
		sortName:'STOCK_ID',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
				field : 'STOCK_ID',
				title : '积分编号',
				hidden : true
			},{
				field : 'vip_Name',
				title : '会员名称',
				width : 100,
				sortable : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width : 100,
				sortable : true
			},{
				field : 'vip_Level_Name',
				title : '会员等级',
				width : 80,
				sortable : true
			},{
				field : 'vip_Group_Name',
				title : '会员分组',
				width : 80,
				sortable : true
			},{
				field : 'car_License',
				title : '车辆牌照',
				width : 70,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 130,
				sortable : true
			},{
				field : 'preclr_Time',
				title : '结算日期',
				width : 110,
				sortable : true
			},{
				field : 'preclr_Id',
				title : '结算单号',
				width : 70,
				sortable : true
			},{
				field : 'reception_Id',
				title : '工单号',
				width : 70,
				sortable : true
			},{
				field : 'vip_Integral',
				title : '销售积分',
				width : 80,
				sortable : true
			},{
				field : 'operatorName',
				title : '经办人',
				width : 80,
				sortable : true
			}
		]]
	});
}

//积分综合查询  储值赠分
function load4(){
	$('#datagrid_vip_jifenzonghechaxun_id3').datagrid({
		url : projectPath+'integralIntegratedQueryAction!getStoredValuePoints.action',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vip_Rec_Id',
		sortName:'vip_Rec_Id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
				field : 'vip_Rec_Id',
				title : '储值编号',
				hidden : true
			},{
				field : 'car_License',
				title : '车辆牌照',
				width : 70,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Name',
				title : '会员名称',
				width : 100,
				sortable : true
			},{
				field : 'vip_Level_Name',
				title : '会员等级',
				width : 80,
				sortable : true
			},{
				field : 'vip_Group_Name',
				title : '会员分组',
				width : 80,
				sortable : true
			},{
				field : 'vip_Rec_Date',
				title : '储值日期',
				width : 110,
				sortable : true
			},{
				field : 'rec_Amount',
				title : '储值金额',
				width : 80,
				sortable : true
			},{
				field : 'give_Amount',
				title : '赠送金额',
				width : 80,
				sortable : true
			},{
				field : 'give_Inte',
				title : '赠送积分',
				width : 80,
				sortable : true
			}
		]]
	});
}

//积分综合查询  赠送积分
function load5(){
	$('#datagrid_vip_jifenzonghechaxun_id4').datagrid({
		url : projectPath+'integralIntegratedQueryAction!getPresentationPoints.action',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vip_Rec_Id',
		sortName:'vip_Rec_Id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
				field : 'vip_Rec_Id',
				title : '积分赠送编号',
				hidden : true
			},{
				field : 'car_License',
				title : '车辆牌照',
				width : 70,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Name',
				title : '会员名称',
				width : 100,
				sortable : true
			},{
				field : 'vip_Level_Name',
				title : '会员等级',
				width : 80,
				sortable : true
			},{
				field : 'vip_Group_Name',
				title : '会员分组',
				width : 80,
				sortable : true
			},{
				field : 'vip_Rec_Date',
				title : '赠送日期',
				width : 110,
				sortable : true
			},{
				field : 'give_Inte',
				title : '赠送积分',
				width : 80,
				sortable : true
			}
		]]
	});
}

//积分综合查询    兑换积分
function load6(){
	$('#datagrid_vip_jifenzonghechaxun_id5').datagrid({
		url : projectPath+'integralIntegratedQueryAction!getConvertPoints.action',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vip_Rec_Id',
		sortName:'vip_Rec_Id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
				field : 'vip_Rec_Id',
				title : '积分兑换编号',
				hidden : true
			},{
				field : 'car_License',
				title : '车辆牌照',
				width : 70,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Name',
				title : '会员名称',
				width : 100,
				sortable : true
			},{
				field : 'vip_Level_Name',
				title : '会员等级',
				width : 80,
				sortable : true
			},{
				field : 'vip_Group_Name',
				title : '会员分组',
				width : 80,
				sortable : true
			},{
				field : 'vip_Rec_Date',
				title : '兑换日期',
				width : 110,
				sortable : true
			},{
				field : 'give_Inte',
				title : '兑换积分',
				width : 80,
				sortable : true
			}
		]]
	});
}

//积分综合查询 会员卡升级/降级
function load7(){
	$('#datagrid_vip_jifenzonghechaxun_id6').datagrid({
		url : projectPath+'integralIntegratedQueryAction!getVipCardUpgrade.action',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vip_Rec_Id',
		sortName:'vip_Rec_Id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
				field : 'vip_Rec_Id',
				title : '升级/降级编号',
				hidden : true
			},{
				field : 'car_License',
				title : '车辆牌照',
				width : 70,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Name',
				title : '会员名称',
				width : 100,
				sortable : true
			},{
				field : 'vip_Level_Name',
				title : '会员等级',
				width : 80,
				sortable : true
			},{
				field : 'vip_Group_Name',
				title : '会员分组',
				width : 80,
				sortable : true
			},{
				field : 'vip_Rec_Date',
				title : '升级/降级日期',
				width : 110,
				sortable : true
			},{
				field : 'give_Inte',
				title : '赠送积分',
				width : 80,
				sortable : true
			},{
				field : 'deductionI_ntegration',
				title : '扣除积分',
				width : 80,
				sortable : true
			}
		]]
	});
}

//积分综合查询 续会赠分
function load8(){
	$('#datagrid_vip_jifenzonghechaxun_id7').datagrid({
		url : projectPath+'integralIntegratedQueryAction!getAdjournmentFind.action',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vip_Rec_Id',
		sortName:'vip_Rec_Id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
				field : 'vip_Rec_Id',
				title : '续会编号',
				hidden : true
			},{
				field : 'car_License',
				title : '车辆牌照',
				width : 70,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Name',
				title : '会员名称',
				width : 100,
				sortable : true
			},{
				field : 'vip_Level_Name',
				title : '会员等级',
				width : 80,
				sortable : true
			},{
				field : 'vip_Group_Name',
				title : '会员分组',
				width : 80,
				sortable : true
			},{
				field : 'vip_Rec_Date',
				title : '续会日期',
				width : 110,
				sortable : true
			},{
				field : 'give_Inte',
				title : '赠送积分',
				width : 80,
				sortable : true
			}
		]]
	});
}

//积分综合查询   退会扣分
function load9(){
	$('#datagrid_vip_jifenzonghechaxun_id8').datagrid({
		url : projectPath+'integralIntegratedQueryAction!getExitMemberFind.action',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vip_Rec_Id',
		sortName:'vip_Rec_Id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
				field : 'vip_Rec_Id',
				title : '续会编号',
				hidden : true
			},{
				field : 'car_License',
				title : '车辆牌照',
				width : 70,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Name',
				title : '会员名称',
				width : 100,
				sortable : true
			},{
				field : 'vip_Level_Name',
				title : '会员等级',
				width : 80,
				sortable : true
			},{
				field : 'vip_Group_Name',
				title : '会员分组',
				width : 80,
				sortable : true
			},{
				field : 'vip_Rec_Date',
				title : '续会日期',
				width : 110,
				sortable : true
			},{
				field : 'give_Inte',
				title : '赠送积分',
				width : 80,
				sortable : true
			}
		]]
	});
	
	$('#tb_id').tabs({
		border:false,
		onSelect:function(title){
		    ttitle = title;
		}
	});
}


//将form表单序列化为对象
function serializeObject(form){
	var o = {};
	$.each(form.serializeArray(),function(index){
		if(o[this['name']]){
			o[this['name']]=o[this['name']]+","+this['value'];
		}else{
			o[this['name']]=this['value'];
		}
	});
	return o;
} 

//条件查询提交  
function doConditionSubmit(){
	var datagridId = null;
	var form =  $('#form_north_condition_vip_mananement_id').form();
	var formvalue = serializeObject(form);
	//判断当前选中的是那个选项卡    
	if(ttitle=="积分汇总"){
		datagridId = $('#datagrid_vip_jifenzonghechaxun_id');
	} 
	if(ttitle=="维修积分"){
		datagridId = $('#datagrid_vip_jifenzonghechaxun_id1');
	} 
	if(ttitle=="储值赠分"){
		datagridId = $('#datagrid_vip_jifenzonghechaxun_id3');
	}
	if(ttitle=="赠送积分"){
		datagridId = $('#datagrid_vip_jifenzonghechaxun_id4');
	}
	if(ttitle=="会员卡升级/降级"){
		datagridId = $('#datagrid_vip_jifenzonghechaxun_id6');
	}
	if(ttitle=="续会赠分"){
		datagridId = $('#datagrid_vip_jifenzonghechaxun_id7');
	}
	if(ttitle=="退会扣分"){
		datagridId = $('#datagrid_vip_jifenzonghechaxun_id8');
	}
	datagridId.datagrid('load',formvalue);
}

function clearForm(){
	$('#form_north_condition_vip_mananement_id').form('clear');
	doConditionSubmit();
}