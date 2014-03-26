var payType = null;   //付款方式
function LoadOk() {
	if (document.readyState == "complete") {
		initFrame();
		findCarLicenseFormat("gatheringQueryCarId");
	} else {
		setTimeout("LoadOk();", 200);
	}
}
function initFrame(){
		runs();
		loadOld("-1");
		requirdAsFalse();
}
setTimeout("LoadOk();", 200);
var money=null; 
function runs(){
	$('#addGatheringEndTime').val(getSystemTime());
loadPreClrTime1($('#addGatheringBeginTime'));
	//前台收银->
$gatheringDatagrid = $('#gatheringDatagrid');

$gatheringDatagrid.datagrid({
	url : 'gatheringAction!datagridNoGathering.action',
	fit : true,
	fitColumns : true,
	pagination : true,
	singleSelect : true,
	rownumbers : true,
	columns : [[
		{field:'preclrId',title:'结算单号',width:100,sortable:false},
		{field:'preclrTime',title:'结算时间',width:122,sortable:false},
		{field:'customId',title:'客户编号',width:60,hidden:true},
		{field:'customName',title:'客户名称',width:60,sortable:false},
		{field:'carLicense',title:'车牌照',width:70,sortable:false},
		{field:'preclrInoiceTypeName',title:'票据类型',width:100,sortable:false},
		{field:'preclrInvoiceTime',title:'开票时间',width:130,sortable:false},
		{field:'preclrNo',title:'票据编号',width:60,sortable:false},
		{field:'preclrAmount',title:'结算金额',width:60,sortable:false},
		{field:'cumulativeAmount',title:'累计收款',width:60,sortable:false},
		{field:'arrearsAmount',title:'欠款',width:60,sortable:false}
		]],
		 onClickRow:function(rowIndex,rowData){
		 	loadOld(rowData.preclrId);
		 }
	});
}
//显示使用记录
function loadOld(id){
	$('#oldGatheringDatagrid').datagrid({
	url : 'gatheringAction!datagridGatheringOld.action?preclrId='+id,
	fit : true,
	fitColumns : true,
	pagination : true,
	singleSelect : true,
	rownumbers : true,
	columns : [[
		{field:'gatheringId',title:'收款单号',width:130,sortable:false},
		{field:'paymentTime',title:'收款时间',width:124,sortable:false},
		{field:'payableAmount',title:'应付金额',width:60,sortable:false},
		{field:'paymentAmount',title:'付款金额',width:60,sortable:false},
		{field:'fcsPayment',title:'找零金额',width:60,sortable:false},
		{field:'arrearsAmount',title:'未付金额',width:60,sortable:false},
		{field:'gatheringWise',title:'付款方式',width:60,sortable:false},
		{field:'stfName',title:'收银员',width:70,sortable:false},
		{field:'gatheringRemark',title:'收款备注',width:100}
		]]
	});
}

var requirdAsFalse=function(){
	$('#balancePage_GatheringWise').combobox('disable');
	$('#hiveUse').numberbox('setValue','');
	$('#hiveUse').numberbox('validate');
	$('#preclrPayAmount').validatebox('validate');
	$('#preclrAmount').validatebox('validate');
	$('#balancePage_gatheringId').validatebox('validate');
	$("#gatheringAddForm textarea").prop("disabled",true);
	$('#subFrtCustomAdd_linkbutton').linkbutton('disable');
	$('#subFrtCustomRemove_linkbutton').linkbutton('disable');
	$('#subClaimantAdd_button').linkbutton('disable');
	$('#subClaimantRemove_linkbutton').linkbutton('disable');
	
	removeSubStitute('substituteCustomIda','substituteMoney','preclrAmount');
	removeSubStitute('substituteCustomIdb','substituteMoney','preclrAmount');
}
var requirdAsTrue=function(){
	$('#hiveUse').numberbox('validate');
	$('#preclrPayAmount').validatebox('validate');
	$('#preclrAmount').validatebox('validate');
	$('#balancePage_gatheringId').validatebox('validate');
	$('#balancePage_GatheringWise').combobox('enable');
	$("#gatheringAddForm textarea").prop("disabled",false);
	$('#subFrtCustomAdd_linkbutton').linkbutton('enable');
	$('#subFrtCustomRemove_linkbutton').linkbutton('enable');
	$('#subClaimantAdd_button').linkbutton('enable');
	$('#subClaimantRemove_linkbutton').linkbutton('enable');
}
var _clearAdd = function() {
	$('#stfId').combobox('setValue',parame3);
    $('#gatheringTime').datetimebox('setValue','{now}');
}
function _query(){
	if($('#gatheringQueryForm').form('validate')){
	$('#gatheringDatagrid').datagrid('load', serializeObject($('#gatheringQueryForm')));
}else{
	alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
};

function _clear(){
	$('#gatheringQueryForm').form('clear');	
$('#gatheringDatagrid').datagrid('load', serializeObject($('#gatheringQueryForm')));
}
//判断是否收清
function opinionFinished(id1,id2,id3){
	var value1=$('#'+id1+'').val();
var value2=$('#'+id2+'').val();
	if(parseFloat(value1)==parseFloat(value2)){
		document.getElementById(id3).checked=false;
	}else{
		document.getElementById(id3).checked=true;
	}
}
//accountBalance根据付款金额结算实收金额，找零
function accountBalance(id1,id2,id3,id4,id5,id6,id7, id8){
	var value1=document.getElementById(id1).value;//应收金额
	var value2=document.getElementById(id2).value;//付款金额
	var value3=document.getElementById(id3).value;//找零
	var value4=document.getElementById(id4).value;//实收金额
	var value5=document.getElementById(id5).value;//储备金抵扣金额
	var value6=document.getElementById(id6).value;//储备金余额
	var value7=document.getElementById(id7).value;//预收款余额
	var value8=document.getElementById(id8).value;//预收款抵扣额
	if(value1==null||value1==''||value1.length==0||parseFloat(value1)<=0){//应收金额
		$('#'+id1+'').val(0.00);
	}
	if(value2==null||value2==''||value2.length==0||parseFloat(value2)<=0){//付款金额
		$('#'+id2+'').val(0.00);
	}
	if(value3==null||value3==''||value3.length==0||parseFloat(value3)<=0){//找零
		$('#'+id3+'').val(0.00);
	}
	if(value4==null||value4==''||value4.length==0||parseFloat(value4)<=0){//实收金额
		$('#'+id4+'').val(0.00);
	}
	if(value5==null||value5==''||value5.length==0||parseFloat(value5)<=0){//储备金使用金额
		$('#'+id5+'').val(0.00);
	}
	if(value6==null||value6==''||value6.length==0||parseFloat(value6)<=0){//储备金余额
		$('#'+id6+'').val(0.00);
	}
	if(value7==null||value7==''||value7.length==0||parseFloat(value7)<=0){//预收款余额
		$('#'+id7+'').val(0.00);
	}
	if(value8==null||value8==''||value8.length==0||parseFloat(value8)<=0){//预收款抵扣额
		$('#'+id8+'').val(0.00);
	}
	if(payType==tempId){                                                                            //维修预收款抵扣
		if(value8!=null || value8 !='' || value8!='0.00' || value8!='0.0' || value8!='0'){          //预收款抵扣额不为零
	    	if(parseFloat(value8) > parseFloat(value7)){                                            //预收款抵扣额大于预收款余额
	    		$('#'+id8+'').numberbox('setValue',value7);                                         //预收款抵扣额将赋予预收款余额
	        	if(parseFloat(value7) > parseFloat(value1)){                                        //预收款抵扣额大于应收金额
	        		$('#otherBalance').val(accSub(parseFloat(value7),parseFloat(value1)));          //找零
	        		$('#preclrRealAmount').val(value7);                                             //实收金额	
	        	}else{                                                                              //预收款抵扣额小于等于应收金额
	        		$('#otherBalance').val('0.00');                                                 //找零
	        		$('#preclrRealAmount').val(value7);                                             //实收金额
	        	}
	    	}else{                                                                                  //预收款抵扣额小于等于预收款余额
	    		if(parseFloat(value8) > parseFloat(value1)){                                        //预收款抵扣额大于应收金额
	        		$('#otherBalance').val(accSub(parseFloat(value8),parseFloat(value1)));          //找零
	        		$('#preclrRealAmount').val(value8);                                             //实收金额	
	        	}else{                                                                              //预收款抵扣额小于等于应收金额
	        		$('#otherBalance').val('0.00');                                                 //找零
	        		$('#preclrRealAmount').val(value8);                                             //实收金额
	        	}
	    	}
	    }
	}else if(payType==tempId2){                                                                     //维修储备金抵扣
	    if(value5!=null || value5 !='' || value5!='0.00' || value5!='0.0' || value5!='0'){          //储备金抵扣金额不为零
	    	if(parseFloat(value5) > parseFloat(value6)){                                            //储备金抵扣金额大于储备金余额
	    		$('#'+id5+'').numberbox('setValue',value6);                                         //储备金抵扣金额将赋予储备金余额
	        	if(parseFloat(value6) > parseFloat(value1)){                                        //储备金抵扣金额大于应收金额
	        		$('#otherBalance').val(accSub(parseFloat(value6),parseFloat(value1)));          //找零
	        		$('#preclrRealAmount').val(value6);                                             //实收金额	
	        	}else {                                                                             //储备金抵扣金额小于等于应收金额
	        		$('#otherBalance').val('0.00');                                                 //找零
	        		$('#preclrRealAmount').val(value6);                                             //实收金额
	        	}
	    	}else{                                                                                  //储备金抵扣金额小于等于储备金余额
	    		if(parseFloat(value5) > parseFloat(value1)){                                        //储备金抵扣金额大于应收金额
	        		$('#otherBalance').val(accSub(parseFloat(value5),parseFloat(value1)));          //找零
	        		$('#preclrRealAmount').val(value5);                                             //实收金额	
	        	}else{                                                                              //储备金抵扣金额小于等于应收金额
	        		$('#otherBalance').val('0.00');                                                 //找零
	        		$('#preclrRealAmount').val(value5);                                             //实收金额
	        	}
	    	}
	    }
	}else{                                                                                          //其他付款方式 
		if(parseFloat(value2) > parseFloat(value1)){                                                //付款金额大于应收金额     
        	$('#otherBalance').val(accSub(parseFloat(value2),parseFloat(value1)));                  //找零
        	$('#preclrRealAmount').val(value2);                                                     //实收金额	
    	}else{                                                                                      //付款金额小于等于应收金额  
        	$('#otherBalance').val('0.00');                                                         //找零
        	$('#preclrRealAmount').val(value2);                                                     //实收金额	
    	}
	}
}

//添加代付人操作
function addSubstitute(id1,id2){
	$('#'+id1+'').combobox({required:true});
	$('#'+id1+'').combobox('enable');
	$('#'+id2+'').numberbox('enable');
	$('#'+id2+'').numberbox('validate');
	$('#balancePage_GatheringWise').combobox('setValue',parame4);
	$('#balancePage_GatheringWise').combobox('disable');
}
//移除代付人
function removeSubStitute(id1,id2,id3){
	$('#'+id1+'').combobox('setValue','');
	$('#'+id1+'').validatebox({required:false});
	$('#'+id1+'').combobox('disable');
    $('#'+id2+'').numberbox('setValue','');
    $('#'+id2+'').numberbox('disable');
    $('#'+id3+'').val(money);
}
//代付金额操作
function substitutePayment(id1,id2){
	var value1=$('#'+id1+'').val();
	var value2=$('#'+id2+'').val();
	if(value2==null||value2.length==0){
		value2=0.00;
		$('#'+id2+'').val(value2);
	}
	if(parseFloat(value2)>parseFloat(money)){
		alertMsg('代付金额不能大于应付金额！', 'warning');
		$('#'+id2+'').numberbox('setValue',money);
		$('#'+id1+'').val(0.00);
	}else{
		$('#'+id1+'').val(parseFloat(money)-parseFloat(value2));
	}
	accountBalance('preclrAmount','preclrPayAmount','otherBalance','preclrRealAmount',null);
}
function changeBlockOrNone(tag){
	if(tag==true){
		document.getElementById("subFrtCustom").style.display="block";
		document.getElementById("subFrtCustomAdd").style.display="block";
		document.getElementById("subFrtCustomRemove").style.display="block";
		document.getElementById("subClaimant").style.display="none";
		document.getElementById("subClaimantAdd").style.display="none";
		document.getElementById("subClaimantRemove").style.display="none";
	}else{
		document.getElementById("subFrtCustom").style.display="none";
		document.getElementById("subFrtCustomAdd").style.display="none";
		document.getElementById("subFrtCustomRemove").style.display="none";
		document.getElementById("subClaimant").style.display="block";
		document.getElementById("subClaimantAdd").style.display="block";
		document.getElementById("subClaimantRemove").style.display="block";
	}
}

//转结算
var modifyTransBalance = function (){
var rowData = $gatheringDatagrid.datagrid('getSelected');
if(rowData){
		if(rowData.preclrAmount==rowData.arrearsAmount){
			$.ajax({
			   type: 'post',
		   dataType: 'json',
		   url: 'gatheringAction!findIsClaims.action',//查找结算单是否已转索赔单
		   data: rowData,
		   success: function(r){
		   		if(!r.success){
		   			$.messager.confirm('系统提示', '您确定要进行转结算操作?', function(r){
						if (r){
							$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url: 'gatheringAction!modifyTransBalance.action',//收银转结算
							   data: rowData,
							   success: function(r){
							     	alertMsg(r);
							     	if(r.success)
								     	$('#gatheringDatagrid').datagrid('load');
							   }
							});
						}
					});		
		   		}else
		   			alertMsg('对不起，此单已索理赔，不能转结算！', 'warning');
		   }
		});
	}else if(rowData.preclrAmount>rowData.arrearsAmount)
		alertMsg('对不起，此单已收款，不能转结算！', 'warning');
}else
	alertMsg('对不起，请先选中要操作的记录！', 'warning');
}
	
	var loadOldBalance=function(flag,id,id2){
		blockOrNone(flag,id);
		$('#'+id2+'').datagrid('reload');
}
var closeBalance=function(){
	blockOrNone(false,'balancePage_oldGatheringDatagrid');
}

//收款点击事件
var saveGatheringBalance=function(){
	var rowData=$('#gatheringDatagrid').datagrid('getSelected');
	if(!rowData){
		alertMsg('对不起，请先选择要收款的记录！', 'warning');
		return;
	}		
    if(parseFloat(rowData.arrearsAmount)<=0){
    	alertMsg('对不起，欠款为零，无需付款！', 'warning');
    	return ;
    }
	requirdAsTrue();
	$('#stfId').combobox('setValue',parame3);
	$('#gatheringTime').datetimebox('setValue','{now}');
	$.ajax({
		type : 'post',
		dataType : 'json',
		url : 'gatheringAction!datagridGatheringByPreclrId.action',
		data : rowData,
		success : function(r) {
			 $('#balancePage_showCar_form').form('clear');
			 $('#balancePage_showCustom_form').form('clear');
			 $('#balancePage_showPreclr_form').form('clear');
			 $('#gatheringAddForm').form('load',r);
			 $('#balancePage_showCar_form').form('load',r);
			 $('#balancePage_showCustom_form').form('load',r);
			 $('#balancePage_showPreclr_form').form('load',r);
			 var name;
			 if($('#memberId').val()!=null&&$('#memberId').val().length>0)
			 	name="是";
			 else
			 	name="否";
			 document.getElementById("isMember").innerHTML=name;
			 money=r.preclrAmount;
			 var preclrId=$('#preclrId').val();
			 var tempTag="";
			 for(var i=0;i<preclrId.length;i++){
			 	if(!(preclrId.charAt(i)>='0'&&preclrId.charAt(i)<='9'))
			 		tempTag=tempTag+preclrId.charAt(i);
			 }
			 if(tempTag==r.tagId){
			 	changeBlockOrNone(false);
			 }else{
			 	changeBlockOrNone(true);
			 }
			 blockOrNone(true,'gatheringAdd');
			 $('#hiveUse').numberbox('setValue','0.00');
			 $('#beforeMoney1').numberbox('setValue','0.00');
			 $('#preclrPayAmount').numberbox('setValue','0.00');
			 $('#otherBalance').val('0.00');
	         $('#preclrRealAmount').val('0.00');
			 $('#hiveBalance').attr('readonly','readonly');
			 $('#hiveUse').attr('readonly','readonly'); 
			 $('#beforeMoney').attr('readonly','readonly'); 
			 $('#beforeMoney1').attr('readonly','readonly');
			 $('#preclrAmount').attr('readonly','readonly');
			 $('#preclrPayAmount').attr('readonly','readonly');
			 $('#gatheringAdd').dialog({
				  width: 900,   
		   		  height:360,
		   		  cache: false, 
				  buttons:[{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						var flag=document.getElementById('unAchieve').checked;
						var params="";
						if(flag)
							params="&unAchieve="+parame1;//付清状态  未付清
						else
							params="&unAchieve="+parame2;//付清状态   付清
						var url = 'gatheringAction!saveGathering.action';
						if($('#gatheringAddForm').form('validate')){
							$.ajax({
								type : 'post',
								dataType : 'json',
								url : url,
								data : $('#gatheringAddForm').serialize()+params,
								success : function(r) {
								    alertMsg(r);
									if (r.success) {
										requirdAsFalse();
									 	$('#gatheringAddForm').form('clear');
									 	$('#gatheringAdd').dialog('close');
										loadOld(null);
										runs();	
									}
								},
								error : function (r){
								   if(r.readyState == '0' && r.status == '0'){
									   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
								   }
							   }
							});
						}else{
							alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
						}
					}
				},{
					text:'Cancel',
					handler:function(){
						requirdAsFalse();
						$('#gatheringAddForm').form('clear');
						$('#gatheringAdd').dialog('close');
					}
				}]
			});
		},
		error : function (r){
		   if(r.readyState == '0' && r.status == '0'){
			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
		   }
	   }
    });
}

function _except(){
	$.messager.confirm('优亿软件提示', '选择导出收款信息(确认)或收款记录(取消)?', function(r){
		if (r){
			showEditDialog("gatheringDatagrid",null,"gatheringDatagrid_center","开始导出","导出配置",0,_callbackExcept);
		}else{
			showEditDialog("oldGatheringDatagrid",null,"oldGatheringDatagrid_center","开始导出","导出配置",0,_callbackExceptOld);	
		}
	});
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"前台收银"+getSystemTime());
}

function _callbackExceptOld(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"收款记录"+getSystemTime());
}