$(function(){
	$('#summary').datagrid({
		url : 'MonthlyStatementAction!findAllMonthlyStatemont.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		singleSelect : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'msId',
			title : '编号',
			width : 80,
			hidden : true
		}, {
			field : 'msNumber',
			title : '月结单',
			width : 40,
			sortable : true
		}, {
			field : 'stfId',
			title : '经办人',
			width : 50,
			sortable : true,
			hidden : true
		}, {
			field : 'stfName',
			title : '经办人',
			width : 50,
			sortable : true
		}, {
			field : 'msStarttime',
			title : '月结开始时间',
			width : 50,
			sortable : true
		}, {
			field : 'msEndtime',
			title : '月结结束时间',
			width : 50,
			sortable : true
		}, {
			field : 'operDate',
			title : '经办日期',
			width : 50,
			sortable : true
		}, {
			field : 'msNexttime',
			title : '下次月结时间',
			width : 50,
			sortable : true
		}, {
			field : 'msRemindtime',
			title : '反月结时间',
			width : 50,
			sortable : true
		}, {
			field : 'msRemark',
			title : '备注',
			width : 200,
			sortable : true
		}]],
		onDblClickRow:function(rowIndex, rowData){
			loadDetail(rowData);
        }
	});

	$('#details1').datagrid({
		url : '',
		pagination : true,
		fit : true,
		rownumbers : true,
		singleSelect : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'indexId',
			title : '编号',
			width : 80,
			hidden : true
		}, {
			field : 'msId',
			title : '月结编号',
			width : 80,
			hidden : true
		}, {
			field : 'partsId',
			title : '配件编号',
			width : 80,
			hidden : true
		}, {
			field : 'partsName',
			title : '配件名称',
			width : 40,
			sortable : true
		}, {
			field : 'storeId',
			title : '仓库编号',
			width : 40,
			hidden : true
		}, {
			field : 'storeName',
			title : '仓库名称',
			width : 40,
			sortable : true
		}, {
			field : 'stinvdCount',
			title : '配件个数',
			width : 40,
			sortable : true
		}, {
			field : 'stinvdPrice',
			title : '配件未税价格',
			width : 40,
			sortable : true
		}, {
			field : 'stinvdCost',
			title : '配件未税金额',
			width : 40,
			sortable : true
		}, {
			field : 'stinvdPrice1',
			title : '配件含税价格',
			width : 40,
			sortable : true
		}, {
			field : 'stinvdCost1',
			title : '配件含税金额',
			width : 40,
			sortable : true
		}]]
	});

	$("#addBtn").click(monthlyProvision);
	$("#delBtn").click(opposite_monthliStatemont);
	$("#serachBtn").click(serach_monthliStatemont);
	$("#clearBtn").click(clear_monthliStatemont);
});

/**月结*/
function monthlyProvision(){
	 var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="doSave();">保存</a>';
	 var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	 if ($('#button').children('a').length == 0) {
			$('#button').append(save).append(cancel);
			$.parser.parse('#button');
	 }
	 $('#tt').tabs('select','月结明细');
	 nuDisAbledControl();
	 loadData();
}

/**反月结*/
function opposite_monthliStatemont(){
	var rows = $('#summary').datagrid('getSelections')
	if(rows.length > 0){
		 $.messager.confirm('优忆软件提示', '请确认是否要进行仓库的反月结？', function(r) {
			 if(r){
				 if(rows[0].msRemindtime == null ||  rows[0].msRemindtime == ""){
					    $.ajax({
							type : "POST",
							url : 'MonthlyStatementAction!delete.action',
							data : 'msId='+rows[0].msId,
							dataType : "json",
							success : function callback(r) {
							      $.messager.alert('优亿软件提示', r.msg);
							      if(r.success){
							    	  serach_monthliStatemont();
							    	  $('#details1').datagrid('loadData',{"total":1,"rows":[]});
							      }
							}
						});
				 }else{
					 $.messager.alert('优亿软件提示', '抱歉，该记录已经是反月结项，不能重复反月结！');
				 }
			 }
		 });
	}else{
		 $.messager.alert('优亿软件提示', '请选择要反月结的项！');
	}
}

function serach_monthliStatemont(){
	$('#summary').datagrid('load', serializeObject($('#form_summary')));
}

function clear_monthliStatemont(){
	$('#form_summary').form('clear');
	serach_monthliStatemont();
}

//保存
function doSave(){
	 if($('#msStarttime').val()==null&&$('#msStarttime').val()==''){
		 $.messager.alert('优亿软件提示', '"本次月结开始时间！"为必填项，必须填写！');
		 return;
	 }
	 if($('#msEndtime').val()==null&&$('#msEndtime').val()==''){
		 $.messager.alert('优亿软件提示', '"本次月结结束时间！"为必填项，必须填写！');
		 return;
	 }
	 $.messager.confirm('优忆软件提示', '请注意：月结时，请保持没有用户使用本系统。月结完成后，截止日期前的入出库等相关进销存记录不可以修改，月结完成后，请退出系统重新登录。是否现在就进行月结转处理？', function(r) {
		 if(r){
			$.ajax({
				type : "POST",
				url : 'MonthlyStatementAction!add.action',
				data : $('#form_details1').serialize(),
				dataType : "json",
				success : function callback(r) {
				      alertMsg(r.msg, 'info');
					  if(r.success){
						  disAbledControl();
						  $('#button').empty();
						  serach_monthliStatemont();
						  $('#details1').datagrid({url : 'MonthlyStatementAction!findMonthlyDetail.action?msId='+r.obj});
					  }
				}
			});
		 }
	 });
}

//取消
function cancel(){
	$('#button').empty();
	$('#tt').tabs('select','月结汇总');
}

/**加载初始化数据*/
function loadData(){
	$.ajax({
		type : "POST",
		url : 'MonthlyStatementAction!loadStratTime.action',
		data : '',
		dataType : "json",
		success : function callback(r) {
			   $('#msStarttime').val(r.msStarttime);
			   getEndTime();
		}
	});
}

/**启用控件*/
function nuDisAbledControl(){
	$("#form_details1 input").prop("disabled", false);
	$("#form_details1 textarea").prop("disabled",false);
}

/**禁用控件*/
function disAbledControl(){
	$("#form_details1 input").prop("disabled", true);
	$("#form_details1 textarea").prop("disabled",true);
}

function loadDetail(rowData){
	$('#tt').tabs('select','月结明细');
	$('#form_details1').form('load', rowData);
    disAbledControl();
    $('#details1').datagrid({url : 'MonthlyStatementAction!findMonthlyDetail.action?msId='+rowData.msId});
}
/**月结结算日期*/
function getEndTime(){
	 var newDate =$('#msStarttime').val();
	 newDate = newDate.replace(new RegExp("-","gm"),"/");
	 var newTime = new Date(newDate); //就得到普通的时间了
	 var dataValue = getTime(newTime, 0,0,0,0,0,0);      //根据结束日期推算
	 var nowValue = getTime(new Date(),0,0,0,0,0,0);    //当前日期
	 if(isStartEndDate(nowValue,dataValue)){              //根据结束日期推算 > 当前日期
	     $('#msEndtime').val(dataValue); 
	 }else{                                                       //根据结束日期推算 < 当前日期
		 $('#msEndtime').val(nowValue);
	 }
	 getRemindTime();
}

/**月结下次提醒日期*/
function getRemindTime(){
	 var newDate =$('#msEndtime').val();
	 newDate = newDate.replace(new RegExp("-","gm"),"/");
	 var newTime = new Date(newDate); //就得到普通的时间了
	 var dataValue = getTime(newTime, 0,0,0,1,0,0);      //根据结束日期推算
	 var nowValue = getTime(new Date(),0,0,0,0,0,0);    //当前日期
	 if(isStartEndDate(dataValue,nowValue)){              //根据结束日期推算 > 当前日期
	     $('#msNexttime').val(dataValue); 
	 }else{                                                       //根据结束日期推算 < 当前日期
		 $('#msNexttime').val(getTime(new Date(),0,0,0,1,0,0));
	 }
}

/**
 * 日期处理
 */
function getTime(now, year_, month_, day_, hour_, minute_, second_){
    var year=now.getFullYear() + year_;     
    var month=now.getMonth() + 1 + month_;     
    var day=now.getDate() + day_;     
    var hour=now.getHours() + hour_;
    var minute=now.getMinutes() + minute_;     
    var second=now.getSeconds() + second_; 
    if((year+'').length==1){
    	year='0'+year;
    }
    if((month+'').length==1){
    	month='0'+month;
    }
    if((day+'').length==1){
    	day='0'+day;
    }
    if((hour+'').length==1){
    	hour='0'+hour;
    }
    if((minute+'').length==1){
    	minute='0'+minute;
    }
    if((second+'').length==1){
    	second='0'+second;
    }
    return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
}

/**
 * 日期比较
 */
function isStartEndDate(startDate,endDate){   
	if(startDate.length>0&&endDate.length>0){   
	      var startDateTemp = startDate.split(" ");   
	      var endDateTemp = endDate.split(" ");   
	      var arrStartDate = startDateTemp[0].split("-");   
	      var arrEndDate = endDateTemp[0].split("-");   
	      var arrStartTime = startDateTemp[1].split(":");   
	      var arrEndTime = endDateTemp[1].split(":");   
	      var allStartDate = new Date(arrStartDate[0],arrStartDate[1],arrStartDate[2],arrStartTime[0],arrStartTime[1],arrStartTime[2]);   
	      var allEndDate = new Date(arrEndDate[0],arrEndDate[1],arrEndDate[2],arrEndTime[0],arrEndTime[1],arrEndTime[2]);   
          if(allStartDate.getTime()>allEndDate.getTime()){   
               return false;   
           }   
     }   
     return true;  
}  