function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
			findCarLicenseFormat("substituteGatheringQueryCarId");
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame(){
			main();
			runs(-1);
			firstBatch();
			loadOld(null,null);
			requirdAsFalse();
	}
	setTimeout("LoadOk();", 200);
	var main=function(){

		$('#addGatheringEndTime').val(getSystemTime());
		loadPreClrTime($('#addGatheringBeginTime'));
		$('#substituteGatheringMainDatagrid').datagrid({
			url : 'gatheringAction!datagridNoSubstituteGatheringMain.action',
			fit : true,
			fitColumns : true,
			pagination : true,
			singleSelect : true,
			rownumbers : true,
			columns : [[
				{field:'customId',title:'代付人编号',width:140,hidden:true},
				{field:'customName',title:'代付人',width:60,sortable:false},
				{field:'customTel',title:'联系方式',width:100,sortable:false},
				{field:'customAddr',title:'地址',width:100,sortable:false},
				{field:'preclrAmount',title:'结算金额',width:60,sortable:false},
				{field:'cumulativeAmount',title:'累计收款',width:60,sortable:false},
				{field:'arrearsAmount',title:'欠款',width:60,sortable:false}
			]],
			onClickRow : function (rowIndex, rowData){
				var params=rowData.customId+'&'+$('#substituteGatheringQueryForm').serialize();
				runs(params);
			}
		});
	}
	var money=null; 
	function runs(customId){
		$substituteGatheringDatagrid = $('#substituteGatheringDatagrid');
		$substituteGatheringDatagrid.datagrid({
			url : 'gatheringAction!datagridNoSubstituteGathering.action?tag=false&tempCustomId='+customId,
			fit : true,
			fitColumns : true,
			pagination : true,
			singleSelect : true,
			rownumbers : true,
			columns : [[
				{field:'sspId',title:'代付结算编号',width:140,sortable:false},
				{field:'createTime',title:'创建时间',width:122,sortable:false},
				{field:'customName',title:'代付人',width:60,sortable:false},
				{field:'carLicense',title:'车牌照',width:70,sortable:false},
				{field:'preclrInoiceTypeName',title:'票据类型',width:100,sortable:false},
				{field:'preclrInvoiceTime',title:'开票时间',width:130,sortable:false},
				{field:'preclrNo',title:'票据编号',width:60,sortable:false},
				{field:'preclrAmount',title:'结算金额',width:60,sortable:false},
				{field:'cumulativeAmount',title:'累计收款',width:60,sortable:false},
				{field:'arrearsAmount',title:'欠款',width:60,sortable:false}
			]],
			onClickRow : function (rowIndex, rowData){
				loadOld(rowData.sspId,false);
			}
		});
	}
	//代付批量收款
	function firstBatch(){
		$firstSubstituteGatheringDatagrid = $('#firstSubstituteGatheringDatagrid');
		
		$firstSubstituteGatheringDatagrid.datagrid({
			url : 'gatheringAction!datagridNoSubstituteBatchGathering.action?tag=false',
			fit : true,
			fitColumns : true,
			pagination : true,
			singleSelect : true,
			rownumbers : true,
			columns : [[
				{field:'sspId',title:'代付批量结算单号',width:110,sortable:true},
				{field:'createTime',title:'创建时间',width:122,sortable:false},
				{field:'customName',title:'代付人',width:60,sortable:true},
				{field:'preclrAmount',title:'应付金额',width:60,sortable:true},
				{field:'paymentAmount',title:'已付金额',width:60,sortable:false},
				{field:'arrearsAmount',title:'未付金额',width:60,sortable:false}
			]],
			onClickRow : function (rowIndex, rowData){
			 	$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'gatheringAction!datagridSubstituteGatheringBySspId.action',
					data : rowData,
					success : function(r) {
						//_clearAdd();
						 $('#subtituteBalance_showCustom_form').form('clear');
						$('#substituteGatheringAddForm').form('load',r);
						 $('#subtituteBalance_showCustom_form').form('load',r);
						batchMoney=r.preclrAmount;
						isMember();
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
				});
				loadOld(rowData.sspId,true);
			}
		});
	}
	var isMember=function(){
		var name;
		 if($('#memberId').val()!=null&&$('#memberId').val().length>0){
		 	name="是";
		 }else{
		 	name="否";
		 }
		 document.getElementById("isMember").innerHTML=name;
	}
	//显示使用记录
	function loadOld(id1,id2){
		var url='gatheringAction!datagridSubstituteGatheringOld.action?sspId='+id1;
		$('#oldSubstituteGatheringDatagrid').datagrid({
			url : url,
			fit : true,
			fitColumns : false,
			pagination : true,
			singleSelect : true,
			rownumbers : true,
			columns : [[
				{field:'gatheringId',title:'收款单号',width:130,sortable:false},
				{field:'paymentTime',title:'收款时间',width:124,sortable:false},
				{field:'payableAmount',title:'应付金额',width:60,sortable:false},
				{field:'paymentAmount',title:'已付金额',width:60,sortable:false},
				{field:'arrearsAmount',title:'未付金额',width:60,sortable:false},
				{field:'gatheringWise',title:'付款方式',width:60,sortable:false},
				{field:'stfName',title:'收银员',width:70,sortable:false},
				{field:'gatheringRemark',title:'收款备注',width:100}
			]]
		});
	}
	var _add=function(){
			var data=$('#substituteGatheringDatagrid').datagrid('getSelected');
			if(data){
				addButton();
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'gatheringAction!datagridSubstituteGatheringByPreclrId.action?flag=true',
					data : data,
					success : function(r) {
						 _clearAdd();
						$('#substituteGatheringAddForm').form('load',r);
						 requirdAsTrue();
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
				});
			}else{
				var batchData=$('#firstSubstituteGatheringDatagrid').datagrid('getSelected');
				if(batchData){
					addButton();
					$.ajax({
						type : 'post',
						dataType : 'json',
						url : 'gatheringAction!datagridSubstituteGatheringBySspId.action?flag=true',
						data : batchData,
						success : function(r) {
							 _clearAdd();
							$('#substituteGatheringAddForm').form('load',r);
							 requirdAsTrue();
						},
						error : function (r){
						   if(r.readyState == '0' && r.status == '0'){
							   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
						   }
					   }
					});
				}else{
					alertMsg('对不起，请先选择要收款的记录！', 'warning');
				}
			}
	}
	var addButton = function() {
		if ($('#save').length == 0 && $('#cancel').length == 0) {
			var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="_save();">保存</a>';
			var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_cancel();">取消</a>';
			$('#button').append(save).append(cancel);
			$.parser.parse('#button');
			$('#add').linkbutton('disable');
			$('#search').linkbutton('disable');
			$('#clear').linkbutton('disable');
			$('#redo').linkbutton('disable');
			$('#print').linkbutton('disable');
			$('#set').linkbutton('disable');
			$('#export').linkbutton('disable');
		}
	}
	var _save = function() {
		var flag=document.getElementById('unAchieve').checked;
		var params="";
		if(flag){
			params="&unAchieve="+parame2;
		}else{
			params="&unAchieve="+parame3;
		}
		var url = 'gatheringAction!saveSubstituteGathering.action';
			if($('#substituteGatheringAddForm').form('validate')){
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : url,
					data : $('#substituteGatheringAddForm').serialize()+params,
					success : function(r) {
						if (r.success) {
							alertMsg(r);
							_cancel();
							runs();	
							firstBatch();
						}else{
							alertMsg(r);
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
	var _cancel = function() {
		 requirdAsFalse();
		$('#button').empty();
		 $('#substituteGatheringAddForm').form('clear');
		 $('#stfId').combobox('setValue',parame1);
		 $('#gatheringTime').datetimebox('setValue','{now}');
		 
		  $('#add').linkbutton('enable');
		 $('#search').linkbutton('enable');
		 $('#clear').linkbutton('enable');
		 $('#redo').linkbutton('enable');
		 $('#print').linkbutton('enable');
		 $('#set').linkbutton('enable');
		 $('#export').linkbutton('enable');
		 
		 //$('#firstSubstituteGatheringDatagrid').datagrid('unselectAll');
		 $('#substituteGatheringDatagrid').datagrid('unselectAll');
	}
	
	var _clearAdd = function() {
		 $('#stfId').combobox('setValue',parame1);
		 $('#gatheringTime').datetimebox('setValue','{now}');
	}
	var requirdAsFalse=function(){
		$('#subtituteBalance_GatheringWise').combobox({required:false,disabled:true});
		$('#preclrPayAmount').validatebox({required:false});
		$('#preclrPayAmount').validatebox('validate');
		$('#hiveUse').numberbox({required:false});
		$('#hiveUse').numberbox('setValue','');
		$('#hiveUse').numberbox('validate');
		$("#substituteGatheringAddForm input[id=hiveUse]").prop("disabled",true);
		$("#substituteGatheringAddForm input[id=preclrPayAmount]").prop("disabled",true);
		$('#preclrAmount').validatebox({required:false});
		$('#preclrAmount').validatebox('validate');
		$('#subtituteBalance_gatheringId').validatebox({required:false});
		$('#subtituteBalance_gatheringId').validatebox('validate');
		$('#substituteBalance_sspId').validatebox({required:false});
		$('#substituteBalance_sspId').validatebox('validate');
		$("#substituteGatheringAddForm textarea[name=gatheringRemark]").prop("disabled",true);
	}
	var requirdAsTrue=function(){
		$('#subtituteBalance_GatheringWise').combobox({required:true,disabled:false});
		$('#preclrPayAmount').validatebox({required:true});
		$('#preclrPayAmount').validatebox('validate');
		$('#hiveUse').numberbox({required:true});
		$('#hiveUse').numberbox('validate');
		$("#substituteGatheringAddForm input[id=hiveUse]").prop("disabled",false);
		$("#substituteGatheringAddForm input[id=preclrPayAmount]").prop("disabled",false);
		$('#preclrAmount').validatebox({required:true});
		$('#preclrAmount').validatebox('validate');
		$('#subtituteBalance_gatheringId').validatebox({required:true});
		$('#subtituteBalance_gatheringId').validatebox('validate');
		$('#substituteBalance_sspId').validatebox({required:true});
		$('#substituteBalance_sspId').validatebox('validate');
		$("#substituteGatheringAddForm textarea[name=gatheringRemark]").prop("disabled",false);
	}
	function _query(){
		//$substituteGatheringDatagrid.datagrid('load', serializeObject($('#substituteGatheringQueryForm')));
		//$firstSubstituteGatheringDatagrid.datagrid('load', serializeObject($('#substituteGatheringQueryForm')));
		if($('#substituteGatheringQueryForm').form('validate')){
			$('#substituteGatheringMainDatagrid').datagrid('load', serializeObject($('#substituteGatheringQueryForm')));
		}else{
			alertMsg('对不起，请输入正确的查询条件！', 'warning');
		}
	};
	
	function _clear(){
		$('#substituteGatheringQueryForm').form('clear');	
		$('#substituteGatheringMainDatagrid').datagrid('load', serializeObject($('#substituteGatheringQueryForm')));
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
	function accountBalance(id1,id2,id3,id4,id5){
		var value2=$('#'+id2+'').val();
		if(value2==null||value2.length==0){
			value2=0.00;
			$('#'+id2+'').val(value2);
		}
		if(id5!=null){
			var value5=$('#'+id5+'').val();
			if(parseFloat(value5)>0){
				//value2=parseFloat(value2)+parseFloat(value5);
				$('#'+id2+'').val(value2);
			}
		}
		var value1=$('#'+id1+'').val();
		if(value1==null||value1.length==0){
			value1=0.00;
			$('#'+id1+'').val(value1);
		}
		$('#'+id3+'').numberbox('setValue',parseFloat(value2)-parseFloat(value1));
		if(parseFloat(value2)>parseFloat(value1)){
			$('#'+id4+'').numberbox('setValue',value1);			
		}else{
			$('#'+id4+'').numberbox('setValue',value2);
		}
		opinionFinished('preclrAmount','preclrRealAmount','unAchieve');
	}
	//opinionMoney根据储备金额结算实收金额，找零
	function opinionMoney(id1,id2,id3,id4){
		var value1=document.getElementById(id1).value;
		var value2=document.getElementById(id2).value;
		var value4=document.getElementById(id4).value;
		if(value1==null||value1.length==0){
			value1=0.00;
			$('#'+id1+'').val(value1);
		}
		if(value2==null||value2.length==0){
			value2=0.00;
			$('#'+id2+'').val(value2);
		}
		if(value4==null||value4.length==0){
			value4=0.00;
			$('#'+id4+'').val(value4);
		}
		if(parseFloat(value1)>parseFloat(value4)){
			alertMsg('使用金额不能大于应付金额！', 'warning');
			$('#'+id1+'').numberbox('setValue',value4);
			$('#'+id3+'').val(value4);
			
		}else if(parseFloat(value1)>parseFloat(value2)){
			alertMsg('使用金额不能大于总金额！', 'warning');
			$('#'+id1+'').numberbox('setValue',value2);
			$('#'+id3+'').val(value2);
		}else{
			$('#'+id3+'').val(value1);
		}
		accountBalance('preclrAmount','preclrPayAmount','otherBalance','preclrRealAmount',null);
	}
	var loadOldBalance=function(flag,id,id2){
		blockOrNone(flag,id);
		$('#'+id2+'').datagrid('reload');
	}
	var closeBalance=function(){
		blockOrNone(false,'subtituteBalance_oldGatheringDatagrid');
	}
	var validateIsAccordion=function(){
		var tab=$('#subtituteBalance_tabs').tabs('getSelected');
		if(tab.panel('options').title=='代付结算单'){
			var data=$('#firstSubstituteGatheringDatagrid').datagrid('getSelected');
			if(data){
				$('#firstSubstituteGatheringDatagrid').datagrid('unselectAll');
				$('#substituteGatheringAddForm').form('clear');
			}
		}else if(tab.panel('options').title=='代付批量结算单'){
			var rowData=$('#substituteGatheringDatagrid').datagrid('getSelected');
			if(rowData){
				$('#substituteGatheringDatagrid').datagrid('unselectAll');
				$('#substituteGatheringAddForm').form('clear');
			}
		}
	}
	
	
	var saveSubstituteBalance=function(){
		var rowData=$('#substituteGatheringDatagrid').datagrid('getSelected');
		if(!rowData){
			alertMsg('对不起，请先选择要收款的记录！', 'warning');
			return;
		}	
		 if(parseFloat(rowData.arrearsAmount)<=0){
         	alertMsg('对不起，欠款为零，无需付款！', 'warning');
         	return ;
         }
				
		requirdAsTrue();
		 $('#stfId').combobox('setValue',parame1);
		 $('#gatheringTime').datetimebox('setValue','{now}');
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'gatheringAction!datagridSubstituteGatheringByPreclrId.action',
			data : rowData,
			success : function(r) {
				//_clearAdd();
				 $('#subtituteBalance_showCustom_form').form('clear');
				 $('#substituteGatheringAddForm').form('load',r);
				 $('#subtituteBalance_showCustom_form').form('load',r);
				 money=r.preclrAmount;
				 isMember();
				 blockOrNone(true,'substituteGatheringAdd');
				 $('#substituteGatheringAdd').dialog({
					buttons:[{
						text:'Ok',
						iconCls:'icon-ok',
						handler:function(){
							var flag=document.getElementById('unAchieve').checked;
							var params="";
							if(flag){
								params="&unAchieve="+parame2;
							}else{
								params="&unAchieve="+parame3;
							}
							var url = 'gatheringAction!saveSubstituteGathering.action';
								if($('#substituteGatheringAddForm').form('validate')){
									$.ajax({
										type : 'post',
										dataType : 'json',
										url : url,
										data : $('#substituteGatheringAddForm').serialize()+params,
										success : function(r) {
											if (r.success) {
												requirdAsFalse();
											 	$('#substituteGatheringAddForm').form('clear');
											 	$('#substituteGatheringAdd').dialog('close');
												alertMsg(r);
												main();
												runs(-1);	
											}else{
												alertMsg(r);
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
							$('#substituteGatheringAddForm').form('clear');
							$('#substituteGatheringAdd').dialog('close');
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
		$.messager.confirm('优亿软件提示', '选择导出代付收款明细(确认)或收款记录(取消)?', function(r){
			if (r){
				showEditDialog("substituteGatheringDatagrid",null,"substituteGatheringDatagrid_center","开始导出","导出配置",0,_callbackExcept);
			}else{
				showEditDialog("oldSubstituteGatheringDatagrid",null,"oldSubstituteGatheringDatagrid_center","开始导出","导出配置",0,_callbackExceptOld);	
			}
		});
	}
	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"代付收款明细"+getSystemTime());
	}
	function _callbackExceptOld(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"收款记录"+getSystemTime());
	}