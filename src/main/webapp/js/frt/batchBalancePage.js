function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
			findCarLicenseFormat("batchGatheringQueryCarId");
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame(){
			runs();
			firstBatch();
			requirdAsFalse();
			loadOld(null);
	}
	setTimeout("LoadOk();", 200);
	var batchMoney=null; 
	function runs(){
		$batchGatheringDatagrid = $('#batchGatheringDatagrid');
		
		$batchGatheringDatagrid.datagrid({
			url : 'gatheringAction!datagridNoBatchGathering.action?tag=true',
			fit : true,
			fitColumns : true,
			pagination : true,
			singleSelect : false,
			rownumbers : true,
			idField:'preclrId',
			columns : [[
				{field:'ck',checkbox:true},
				{field:'preclrId',title:'结算单号',width:110,sortable:true},
				{field:'preclrTime',title:'结算时间',width:130,sortable:true},
				{field:'customId',title:'客户编号',width:60,hidden:true},
				{field:'customName',title:'客户名称',width:60,sortable:true},
				{field:'carLicense',title:'车牌照',width:70,sortable:true},
				{field:'preclrInoiceTypeName',title:'票据类型',width:100,sortable:true},
				{field:'preclrInvoiceTime',title:'开票时间',width:130,sortable:true},
				{field:'preclrNo',title:'票据编号',width:60,sortable:true},
				{field:'preclrAmount',title:'结算金额',width:60,sortable:true}
			]],
			onLoadSuccess : function (data){
				$('#batchGatheringDatagrid').datagrid('clearSelections');
			},
			onSelect:function(rowIndex,rowData){
				//alert(rowIndex);
				validateIsAccordion();
				showSelectAll(rowIndex);
			},
			onUnselect:function(rowIndex,rowData){
				//alert(rowIndex+"--");
				validateIsAccordion();
				showSelectAll(rowIndex);
			},
			onSelectAll:function(rows){
				//alert(rows);
				validateIsAccordion();
				showSelectAll(null);
			},
			onUnselectAll:function(rows){
				//alert(rows+"---");
				showSelectAll("");
			}
		});
	}
	var showSelectAll=function(rowIndex){
		$('#batchGatheringAddForm').form('clear');
		var ids = [];
	 	var licenses = [];
		var rows=new Array();
		if(rowIndex!=null){
			rows=$('#batchGatheringDatagrid').datagrid('getSelections');
		}else{
			rows=$('#batchGatheringDatagrid').datagrid('getRows');
		}
		for(var i=1;i<rows.length;i++){
			for ( var j = 0; j < rows.length-i; j++) {
				if(rows[j].customId!=rows[j+1].customId){
					 alertMsg('对不起，批量收款只限同一客户！', 'warning');
					 if(rowIndex!=null){
					 	$('#batchGatheringDatagrid').datagrid('unselectRow',rowIndex);
					 	$('#batchGatheringDatagrid').datagrid('unselectRow',rows[j].index);
					 }else{
					 	$('#batchGatheringDatagrid').datagrid('unselectAll');
					 	for ( var k = 0; k < rows.length; k++) {
					 		$('#batchGatheringDatagrid').datagrid('unselectRow',rows[k].index);							
						}
					 }
					 return;
				}
			}
		}
		if(rowIndex!=null){
			rows=$('#batchGatheringDatagrid').datagrid('getSelections');
		}else{
			rows=$('#batchGatheringDatagrid').datagrid('getRows');
		}
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].preclrId);
		}
		for(var i=0;i<rows.length;i++){
			licenses.push(rows[i].carLicense);
		}
		var tempPreclrIds=null;
		var tempCarLicenses=null;
		tempPreclrIds=ids.toString().replace(",","\n");
		tempCarLicenses=licenses.toString().replace(",","\n");
		for(var i=0;i<ids.length;i++){
			tempPreclrIds=tempPreclrIds.replace(",","\n");
			tempCarLicenses=tempCarLicenses.replace(",","\n");
		} 
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'gatheringAction!datagridBatchGatheringByPreclrId.action?tag=true',
			data : 'preclrIds='+ids,
			success : function(r) {
				$('#batchBalancePage_showCustom_form').form('clear');
				_clearAdd();
				$('#batchGatheringAddForm').form('load',r);
				$('#batchBalancePage_showCustom_form').form('load',r);
				isMember();
				batchMoney=r.preclrAmount;
				$('#tempPreclrIds').val(tempPreclrIds);
				$('#tempCarLicenses').val(tempCarLicenses);
				
				var batchBalancePage_gatheringId=$('#batchBalancePage_gatheringId').val();
				 var tempTag="";
				 for(var i=0;i<batchBalancePage_gatheringId.length;i++){
				 	if(!(batchBalancePage_gatheringId.charAt(i)>='0'&&batchBalancePage_gatheringId.charAt(i)<='9')){
				 		tempTag=tempTag+batchBalancePage_gatheringId.charAt(i);
				 	}
				 }
				 if(tempTag==r.tagId){
				 	changeBlockOrNone(false);
				 }else{
				 	changeBlockOrNone(true);
				 }
			},
			error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		});
	}
	function firstBatch(){
		$firstBatchGatheringDatagrid = $('#firstBatchGatheringDatagrid');
		
		$firstBatchGatheringDatagrid.datagrid({
			url : 'gatheringAction!datagridBatchGatheringCollect.action',
			fit : true,
			fitColumns : true,
			pagination : true,
			singleSelect : true,
			rownumbers : true,
			columns : [[
				{field:'bbgId',title:'汇总单号',width:110,sortable:true},
				{field:'createTime',title:'创建日期',width:112,sortable:true},
				{field:'customName',title:'客户名称',width:60,sortable:true},
				{field:'payableAmount',title:'应付金额',width:60,sortable:false},
				{field:'paymentAmount',title:'已付金额',width:60,sortable:false},
				{field:'arrearsAmount',title:'未付金额',width:60,sortable:false},
				{field:'differenceBalance',title:'差额结算',width:60,hidden:true}
			]],
			onClickRow : function (rowIndex, rowData){
				validateIsAccordion();
				loadOld(rowData.bbgId);
			 	$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'gatheringAction!datagridBatchGatheringByPreclrId.action?tag=false',
					data : rowData,
					success : function(r) {
						$('#batchBalancePage_showCustom_form').form('clear');
						_clearAdd();
						$('#batchGatheringAddForm').form('load',r);
						$('#batchBalancePage_showCustom_form').form('load',r);
						isMember();
						batchMoney=r.preclrAmount;
						var batchBalancePage_gatheringId=$('#batchBalancePage_gatheringId').val();
						 var tempTag="";
						 for(var i=0;i<batchBalancePage_gatheringId.length;i++){
						 	if(!(batchBalancePage_gatheringId.charAt(i)>='0'&&batchBalancePage_gatheringId.charAt(i)<='9')){
						 		tempTag=tempTag+batchBalancePage_gatheringId.charAt(i);
						 	}
						 }
						 if(tempTag==r.tagId){
						 	changeBlockOrNone(false);
						 }else{
						 	changeBlockOrNone(true);
						 }
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
				});
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
	function loadOld(id){
		$('#oldGatheringDatagrid').datagrid({
			url : 'gatheringAction!datagridBatchGatheringOld.action?bbgId='+id,
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
	function isExists(data,target){
		for ( var i = 0; i < data.length; i++) {
			if(data.charAt(i)==','){
				return true;
			}
		}
		return false;
	}
	var _add=function(){
			var rowData=$('#firstBatchGatheringDatagrid').datagrid('getSelected');
			if(rowData){
				var tag=parame1;
				if(rowData.differenceBalance==tag){
					 alertMsg('已差额结算!不能多次结算！', 'warning');
					 return;
				}
				addButton();
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'gatheringAction!datagridBatchGatheringByPreclrId.action?tag=false&flag=true',
					data : rowData,
					success : function(r) {
						_clearAdd();
						$('#batchGatheringAddForm').form('load',r);
						$('#batchBalancePage_showCustom_form').form('load',r);
						var batchBalancePage_gatheringId=$('#batchBalancePage_gatheringId').val();
						 var tempTag="";
						 for(var i=0;i<batchBalancePage_gatheringId.length;i++){
						 	if(!(batchBalancePage_gatheringId.charAt(i)>='0'&&batchBalancePage_gatheringId.charAt(i)<='9')){
						 		tempTag=tempTag+batchBalancePage_gatheringId.charAt(i);
						 	}
						 }
						 if(tempTag==r.tagId){
						 	changeBlockOrNone(false);
						 }else{
						 	changeBlockOrNone(true);
						 }
						 requirdAsTrue();
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
				});
			}else{
				var rows=$('#batchGatheringDatagrid').datagrid('getSelections');
				if(rows!=null&&rows.length>0){
					addButton();
					var ids = [];
					var rows=$('#batchGatheringDatagrid').datagrid('getSelections');
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].preclrId);
					}
					$.ajax({
						type : 'post',
						dataType : 'json',
						url : 'gatheringAction!datagridBatchGatheringByPreclrId.action?tag=true&flag=true',
						data : 'preclrIds='+ids,
						success : function(r) {
							$('#batchBalancePage_gatheringId').val(r.gatheringId);		
							var batchBalancePage_gatheringId=$('#batchBalancePage_gatheringId').val();						
							 var tempTag="";
							 for(var i=0;i<batchBalancePage_gatheringId.length;i++){
							 	if(!(batchBalancePage_gatheringId.charAt(i)>='0'&&batchBalancePage_gatheringId.charAt(i)<='9')){
							 		tempTag=tempTag+batchBalancePage_gatheringId.charAt(i);
							 	}
							 }
							 if(tempTag==r.tagId){
							 	changeBlockOrNone(false);
							 }else{
							 	changeBlockOrNone(true);
							 }
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
			$('#export').linkbutton('disable');
			_clearAdd();
		}
	}
	
	var _save = function() {
		var rowData=$('#firstBatchGatheringDatagrid').datagrid('getSelected');
		if(rowData){
			saveSure();
		}else{
			$.messager.confirm('警告', '批量结算后不可回退！请确认批量结算？', function(r){
				if (r){
					saveSure();
				}
			});
		}
	}
	function saveSure(){
		var differenceBalance=document.getElementById('differenceBalance').checked;
		if(differenceBalance==true){
			$.messager.confirm('警告', '差额结算后不能再进行结算!', function(r){
				if (r){
					saveData();
				}
			});
		}else{
			saveData();
		}
	}
	function saveData(){
		var ids=$('#tempPreclrIds').val();
		var temp=ids.toString().substring(0,ids.indexOf("\n",0));
		if(temp.length==0){
			temp=ids;
		}
		var url = 'gatheringAction!saveBatchGathering.action';
		if($('#batchGatheringAddForm').form('validate')){
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : url,
				data : $('#batchGatheringAddForm').serialize()+'&preclrId='+temp,
				success : function(r) {
					if (r.success) {
						alertMsg(r);
						_cancel();
						runs();
						$('#batchBalancePage_accordion').accordion('select', '批量收款汇总');
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
		 $('#batchGatheringAddForm').form('clear');
		 $('#stfId').combobox('setValue',parame2);
		 $('#gatheringTime').datetimebox('setValue','{now}');
		 
		  $('#add').linkbutton('enable');
		 $('#search').linkbutton('enable');
		 $('#clear').linkbutton('enable');
		 $('#redo').linkbutton('enable');
		 $('#print').linkbutton('enable');
		 $('#export').linkbutton('enable');
	}
	var _clearAdd = function() {
		 $('#stfId').combobox('setValue',parame2);
		 $('#gatheringTime').datetimebox('setValue','{now}');
	}
	var requirdAsFalse=function(){
		$('#batchBalancePage_GatheringWise').combobox({required:false,disabled:true});
		$("#batchGatheringAddForm input[id=differenceBalance]").prop("disabled",true);
		$('#preclrPayAmount').validatebox({required:false});
		$('#preclrPayAmount').validatebox('validate');
		$("#batchGatheringAddForm input[id=preclrPayAmount]").prop("disabled",true);
		$('#preclrAmount').validatebox({required:false});
		$('#preclrAmount').validatebox('validate');
		$('#batchBalancePage_gatheringId').validatebox({required:false});
		$('#batchBalancePage_gatheringId').validatebox('validate');
		$("#batchGatheringAddForm textarea[name=gatheringRemark]").prop("disabled",true);
		$('#subFrtCustomAdd_linkbutton').linkbutton('disable');
		$('#subFrtCustomRemove_linkbutton').linkbutton('disable');
		$('#subClaimantAdd_button').linkbutton('disable');
		$('#subClaimantRemove_linkbutton').linkbutton('disable');
		
		removeSubStitute('substituteCustomIda','substituteMoney','preclrAmount');
		removeSubStitute('substituteCustomIdb','substituteMoney','preclrAmount');
	}
	var requirdAsTrue=function(){
		$('#batchBalancePage_GatheringWise').combobox({required:true,disabled:false});
		$("#batchGatheringAddForm input[id=differenceBalance]").prop("disabled",false);
		$('#preclrPayAmount').validatebox({required:true});
		$('#preclrPayAmount').validatebox('validate');
		$("#batchGatheringAddForm input[id=preclrPayAmount]").prop("disabled",false);
		$('#preclrAmount').validatebox({required:true});
		$('#preclrAmount').validatebox('validate');
		$('#batchBalancePage_gatheringId').validatebox({required:true});
		$('#batchBalancePage_gatheringId').validatebox('validate');
		$("#batchGatheringAddForm textarea[name=gatheringRemark]").prop("disabled",false);
		
		$('#subFrtCustomAdd_linkbutton').linkbutton('enable');
		$('#subFrtCustomRemove_linkbutton').linkbutton('enable');
		$('#subClaimantAdd_button').linkbutton('enable');
		$('#subClaimantRemove_linkbutton').linkbutton('enable');
	}
	function _query(){
		$batchGatheringDatagrid.datagrid('load', serializeObject($('#batchGatheringQueryForm')));
		$firstBatchGatheringDatagrid.datagrid('load', serializeObject($('#batchGatheringQueryForm')));
	};
	
	function _clear(){
		$('#batchGatheringQueryForm').form('clear');	
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
	function batchOpinionMoney(id1,id2){
		var value1=document.getElementById(id1).value;
		var value2=document.getElementById(id2).value;
		if(parseFloat(value1)>parseFloat(value2)){
			alertMsg('使用金额不能大于总金额！', 'warning');
			$('#'+id1+'').numberbox('setValue',value2);
		}
	}
	function batchRealMoney(id1,id2,id3){
		var value2=$('#'+id2+'').val();
		if(value2==null||value2.length==0){
			value2=0.00;
			$('#'+id2+'').val(value2);
		}
		var value1=$('#'+id1+'').val();
		if(value1==null||value1.length==0){
			value1=0.00;
			$('#'+id1+'').val(value1);
		}
		if(parseFloat(value2)>parseFloat(value1)){
			$('#'+id3+'').numberbox('setValue',value1);			
		}else{
			$('#'+id3+'').numberbox('setValue',value2);
		}
	}
	//batchAccountBalance根据付款金额结算实收金额，找零
	function batchAccountBalance(id1,id2,id3,id4){
		var value2=$('#'+id2+'').val();
		if(value2==null||value2.length==0){
			value2=0.00;
			$('#'+id2+'').val(value2);
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
	//添加代付人操作
	function addSubstitute(id1,id2){
		$('#'+id1+'').combobox('enable');
		$('#'+id2+'').numberbox('enable');
		$('#'+id1+'').combobox('validate');
		$('#'+id2+'').numberbox('validate');
	}
	//移除代付人
	function removeSubStitute(id1,id2,id3){
		$('#'+id1+'').combobox('setValue','');
		$('#'+id2+'').numberbox('setValue','');
		$('#'+id1+'').combobox('disable');
		$('#'+id2+'').numberbox('disable');
		$('#'+id3+'').val(batchMoney);
	}
	//代付金额操作
	function substitutePayment(id1,id2){
		var value1=$('#'+id1+'').val();
		var value2=$('#'+id2+'').val();
		if(value2==null||value2.length==0){
			value2=0.00;
			$('#'+id2+'').val(value2);
		}
		if(parseFloat(value2)>parseFloat(batchMoney)){
			alertMsg('代付金额不能大于应付金额！', 'warning');
			$('#'+id2+'').numberbox('setValue',batchMoney);
			$('#'+id1+'').val(0.00);
		}else{
			$('#'+id1+'').val(parseFloat(batchMoney)-parseFloat(value2));
		}
		batchAccountBalance('preclrAmount','preclrPayAmount','otherBalance','preclrRealAmount');
		//accountBalance('preclrAmount','preclrPayAmount','otherBalance','preclrRealAmount');
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
	
	var loadOldBalance=function(flag,id,id2){
		blockOrNone(flag,id);
		$('#'+id2+'').datagrid('reload');
	}
	var closeBalance=function(){
		blockOrNone(false,'batchBalancePage_oldGatheringDatagrid');
	}
	var validateIsAccordion=function(){
		var selected=$('#batchBalancePage_accordion').accordion('getSelected');
		var index = $('#batchBalancePage_accordion').accordion('getPanelIndex',selected);
		if(index==0){
			var rows=$('#batchGatheringDatagrid').datagrid('getSelections');
			if(rows!=null&&rows.length>0){
				$('#batchGatheringDatagrid').datagrid('unselectAll');
				$('#batchGatheringAddForm').form('clear');
			}
		}else if(index==1){
			var rowData=$('#firstBatchGatheringDatagrid').datagrid('getSelected');
			if(rowData){
				$('#firstBatchGatheringDatagrid').datagrid('unselectAll');
				$('#batchGatheringAddForm').form('clear');
			}
		}
	}