function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
			findCarLicenseFormat("batchSubstituteGatheringQueryCarId");
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame(){
			runs();
			requirdAsFalse();
			firstBatch();
			loadOld(null,null);
			secondBatch();
	}
	setTimeout("LoadOk();", 200);
	
	function runs(){
		$batchSubstituteGatheringDatagrid = $('#batchSubstituteGatheringDatagrid');
		
		$batchSubstituteGatheringDatagrid.datagrid({
			url : 'gatheringAction!datagridNoBatchSubstituteGathering.action?tag=true',
			fit : true,
			fitColumns : true,
			pagination : true,
			singleSelect : false,
			rownumbers : true,
			idField:'sspId',
			columns : [[
				{field:'ck',checkbox:true},
				{field:'sspId',title:'代付结算编号',width:140,sortable:false},
				{field:'createTime',title:'创建时间',width:122,sortable:false},
				{field:'customId',title:'代付人编号',width:60,hidden:true},
				{field:'customName',title:'代付人',width:60,sortable:false},
				{field:'carLicense',title:'车牌照',width:70,sortable:false},
				{field:'preclrInoiceTypeName',title:'票据类型',width:100,sortable:false},
				{field:'preclrInvoiceTime',title:'开票时间',width:130,sortable:false},
				{field:'preclrNo',title:'票据编号',width:60,sortable:false},
				{field:'preclrAmount',title:'结算金额',width:60,sortable:false},
				{field:'cumulativeAmount',title:'累计收款',width:60,sortable:false},
				{field:'arrearsAmount',title:'欠款',width:60,sortable:false}
			]],
			onLoadSuccess : function (data){
				$('#batchSubstituteGatheringDatagrid').datagrid('clearSelections');
			},
			onSelect:function(rowIndex,rowData){
				validateIsAccordion();
				showSelectAll_1(rowIndex);
			},
			onUnselect:function(rowIndex,rowData){
				validateIsAccordion();
				showSelectAll_1(rowIndex);
			},
			onSelectAll:function(rows){
				validateIsAccordion();
				showSelectAll_1(null);
			},
			onUnselectAll:function(rows){
				showSelectAll_1("");
			}
		});
	}
	var showSelectAll_1=function(rowIndex){
		$('#batchSubstituteGatheringAddForm').form('clear');
		var ids = [];
		var rows =new Array();
		if(rowIndex!=null){
			rows=$('#batchSubstituteGatheringDatagrid').datagrid('getSelections');
		}else{
			rows=$('#batchSubstituteGatheringDatagrid').datagrid('getRows');
		}
		for(var i=1;i<rows.length;i++){
			for ( var j = 0; j < rows.length-i; j++) {
				if(rows[j].customId!=rows[j+1].customId){
					 alertMsg('对不起，批量收款只限同一客户！', 'warning');
					 if(rowIndex!=null){
					 	$('#batchSubstituteGatheringDatagrid').datagrid('unselectRow',rowIndex);
					 	$('#batchSubstituteGatheringDatagrid').datagrid('unselectRow',rows[j].index);
					 }else{
					 	$('#batchSubstituteGatheringDatagrid').datagrid('unselectAll');
					 		for ( var k = 0; k < rows.length; k++) {
						 		$('#batchSubstituteGatheringDatagrid').datagrid('unselectRow',rows[k].index);							
							}
					 }
					 return;
				}
			}
		}
		if(rowIndex!=null){
			rows=$('#batchSubstituteGatheringDatagrid').datagrid('getSelections');
		}else{
			rows=$('#batchSubstituteGatheringDatagrid').datagrid('getRows');
		}
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].sspId);
		}
		var tempSspIds=null;
		tempSspIds=ids.toString().replace(",","\n");
		for(var i=0;i<ids.length;i++){
			tempSspIds=tempSspIds.replace(",","\n");
		} 
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'gatheringAction!datagridBatchSubstituteGatheringBySspId.action?tag=true',
			data : 'preclrIds='+ids,
			success : function(r) {
				//_clearAdd();
				$('#batchSubstituteGatheringAddForm').form('load',r);
				$('#tempPreclrIds').val(tempSspIds);
			},
			error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		});
	}
	//批量代付收款汇总
	function firstBatch(){
		$firstBatchSubstituteGatheringDatagrid = $('#firstBatchSubstituteGatheringDatagrid');
		
		$firstBatchSubstituteGatheringDatagrid.datagrid({
			url : 'gatheringAction!datagridBatchSubstituteGatheringCollect.action',
			fit : true,
			fitColumns : true,
			pagination : true,
			singleSelect : true,
			rownumbers : true,
			idField:'sspId',
			columns : [[
				{field:'sspId',title:'汇总单号',width:110,sortable:true},
				{field:'createTime',title:'创建日期',width:112,sortable:true},
				{field:'customName',title:'代付人',width:60,sortable:true},
				{field:'payableAmount',title:'应付金额',width:60,sortable:false},
				{field:'paymentAmount',title:'已付金额',width:60,sortable:false},
				{field:'arrearsAmount',title:'未付金额',width:60,sortable:false},
				{field:'differenceBalance',title:'差额结算',width:60,hidden:true}
			]],
			onClickRow : function (rowIndex, rowData){
				validateIsAccordion();
				loadOld(rowData.sspId);
			 	$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'gatheringAction!datagridBatchSubstituteGatheringBySspId.action?tag=false',
					data : rowData,
					success : function(r) {
						//_clearAdd();
						$('#batchSubstituteGatheringAddForm').form('load',r);
						batchMoney=r.preclrAmount;
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
	//代付批量收款
	function secondBatch(){
		$secondSubstituteGatheringDatagrid = $('#secondSubstituteGatheringDatagrid');
		
		$secondSubstituteGatheringDatagrid.datagrid({
			url : 'gatheringAction!datagridNoSubstituteBatchGathering.action?tag=true',
			fit : true,
			fitColumns : true,
			pagination : true,
			singleSelect : false,
			rownumbers : true,
			idField:'sspId',
			columns : [[
				{field:'ck',checkbox:true},
				{field:'sspId',title:'代付批量结算单号',width:110,sortable:true},
				{field:'createTime',title:'创建时间',width:122,sortable:false},
				{field:'customId',title:'代付人编号',width:60,hidden:true},
				{field:'customName',title:'代付人',width:60,sortable:true},
				{field:'preclrAmount',title:'应付金额',width:60,sortable:true},
				{field:'paymentAmount',title:'已付金额',width:60,sortable:false},
				{field:'arrearsAmount',title:'未付金额',width:60,sortable:false}
			]],
			onLoadSuccess : function (data){
				$('#secondSubstituteGatheringDatagrid').datagrid('clearSelections');
			},
			onSelect:function(rowIndex,rowData){
				validateIsAccordion();
				showSelectAll_2(rowIndex);
			},
			onUnselect:function(rowIndex,rowData){
				validateIsAccordion();
				showSelectAll_2(rowIndex);
			},
			onSelectAll:function(rows){
				validateIsAccordion();
				showSelectAll_2(null);
			},
			onUnselectAll:function(rows){
				showSelectAll_2("");
			}
		});
	}
	var showSelectAll_2=function(rowIndex){
		$('#batchSubstituteGatheringAddForm').form('clear');
		var ids = [];
		var rows =new Array();
		if(rowIndex!=null){
			rows=$('#secondSubstituteGatheringDatagrid').datagrid('getSelections');
		}else{
			rows=$('#secondSubstituteGatheringDatagrid').datagrid('getRows');
		}
		for(var i=1;i<rows.length;i++){
			for ( var j = 0; j < rows.length-i; j++) {
				if(rows[j].customId!=rows[j+1].customId){
					 alertMsg('对不起，批量收款只限同一客户！', 'warning');
					 if(rowIndex!=null){
					 	$('#secondSubstituteGatheringDatagrid').datagrid('unselectRow',rowIndex);
					 	$('#secondSubstituteGatheringDatagrid').datagrid('unselectRow',rows[j].index);
					 }else{
					 	$('#secondSubstituteGatheringDatagrid').datagrid('unselectAll');
				 		for ( var k = 0; k < rows.length; k++) {
					 		$('#secondSubstituteGatheringDatagrid').datagrid('unselectRow',rows[k].index);							
						}
					 }
					 return;
				}
			}
		}
		if(rowIndex!=null){
			rows=$('#secondSubstituteGatheringDatagrid').datagrid('getSelections');
		}else{
			rows=$('#secondSubstituteGatheringDatagrid').datagrid('getRows');
		}
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].sspId);
		}
		var tempPreclrIds=null;
		tempPreclrIds=ids.toString().replace(",","\n");
		for(var i=0;i<ids.length;i++){
			tempPreclrIds=tempPreclrIds.replace(",","\n");
		} 
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'gatheringAction!datagridBatchSubstituteGatheringBySspId.action?tag=true',
			data : 'preclrIds='+ids,
			success : function(r) {
				//_clearAdd();
				$('#batchSubstituteGatheringAddForm').form('load',r);
				batchMoney=r.preclrAmount;
				$('#tempPreclrIds').val(tempPreclrIds);
			},
			error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		});
	}
	//显示使用记录
	function loadOld(id1){
		$('#oldBatchSubstituteGatheringDatagrid').datagrid({
			url : 'gatheringAction!datagridBatchSubstituteGatheringOld.action?sspId='+id1,
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
			var data=$('#firstBatchSubstituteGatheringDatagrid').datagrid('getSelected');
			if(data){
				addButton();
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'gatheringAction!datagridBatchSubstituteGatheringBySspId.action?tag=false&flag=true',
					data : data,
					success : function(r) {
						//_clearAdd();
						$('#substituteBatchBalance_gatheringId').val(r.gatheringId);
						requirdAsTrue();
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
				});
			}else{
				var rows = $('#batchSubstituteGatheringDatagrid').datagrid('getSelections');
				if(rows!=null&&rows.length>0){
					addButton();
					var ids = [];
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].sspId);
					}
					$.ajax({
						type : 'post',
						dataType : 'json',
						url : 'gatheringAction!datagridBatchSubstituteGatheringBySspId.action?tag=true&flag=true',
						data : 'preclrIds='+ids,
						success : function(r) {
							//_clearAdd();
							$('#substituteBatchBalance_gatheringId').val(r.gatheringId);
							requirdAsTrue();
						},
						error : function (r){
						   if(r.readyState == '0' && r.status == '0'){
							   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
						   }
					   }
					});
				}else{
					var rows =$('#secondSubstituteGatheringDatagrid').datagrid('getSelections');
					if(rows!=null&&rows.length>0){
						addButton();
						var ids = [];
						for(var i=0;i<rows.length;i++){
							ids.push(rows[i].sspId);
						}
						$.ajax({
							type : 'post',
							dataType : 'json',
							url : 'gatheringAction!datagridBatchSubstituteGatheringBySspId.action?tag=true&flag=true',
							data : 'preclrIds='+ids,
							success : function(r) {
								//_clearAdd();
								$('#substituteBatchBalance_gatheringId').val(r.gatheringId);
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
		var ids=$('#tempPreclrIds').val();
		var temp=ids.toString().substring(0,ids.indexOf("\n",0));
		if(temp.length==0){
			temp=ids;
		}
		var url = 'gatheringAction!saveBatchSubstituteGathering.action';
			if($('#batchSubstituteGatheringAddForm').form('validate')){
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : url,
					data : $('#batchSubstituteGatheringAddForm').serialize()+'&preclrId='+temp,
					success : function(r) {
						if (r.success) {
							alertMsg(r);
							_cancel();
							runs();	
							firstBatch();
							secondBatch();
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
		 $('#batchSubstituteGatheringAddForm').form('clear');
		 $('#stfId').combobox('setValue',parame1);
		 $('#gatheringTime').datetimebox('setValue','{now}');
		 
		  $('#add').linkbutton('enable');
		 $('#search').linkbutton('enable');
		 $('#clear').linkbutton('enable');
		 $('#redo').linkbutton('enable');
		 $('#print').linkbutton('enable');
		 $('#export').linkbutton('enable');
	}
	var _clearAdd = function() {
		 $('#stfId').combobox('setValue',parame1);
		 $('#gatheringTime').datetimebox('setValue','{now}');
	}
	var requirdAsFalse=function(){
		$('#substituteBatchBalance_GatheringWise').combobox({required:false,disabled:true});
		$("#batchSubstituteGatheringAddForm input[id=differenceBalance]").prop("disabled",true);
		$('#preclrPayAmount').validatebox({required:false});
		$('#preclrPayAmount').validatebox('validate');
		$("#batchSubstituteGatheringAddForm input[id=preclrPayAmount]").prop("disabled",true);
		$('#preclrAmount').validatebox({required:false});
		$('#preclrAmount').validatebox('validate');
		$('#substituteBatchBalance_gatheringId').validatebox({required:false});
		$('#substituteBatchBalance_gatheringId').validatebox('validate');
		$("#batchSubstituteGatheringAddForm textarea[name=gatheringRemark]").prop("disabled",true);
	}
	var requirdAsTrue=function(){
		$('#substituteBatchBalance_GatheringWise').combobox({required:true,disabled:false});
		$("#batchSubstituteGatheringAddForm input[id=differenceBalance]").prop("disabled",false);
		$('#preclrPayAmount').validatebox({required:true});
		$('#preclrPayAmount').validatebox('validate');
		$("#batchSubstituteGatheringAddForm input[id=preclrPayAmount]").prop("disabled",false);
		$('#preclrAmount').validatebox({required:true});
		$('#preclrAmount').validatebox('validate');
		$('#substituteBatchBalance_gatheringId').validatebox({required:true});
		$('#substituteBatchBalance_gatheringId').validatebox('validate');
		$("#batchSubstituteGatheringAddForm textarea[name=gatheringRemark]").prop("disabled",false);
	}
	function _query(){
		$batchSubstituteGatheringDatagrid.datagrid('load', serializeObject($('#batchSubstituteGatheringQueryForm')));
		$firstBatchSubstituteGatheringDatagrid.datagrid('load', serializeObject($('#batchSubstituteGatheringQueryForm')));
		$secondSubstituteGatheringDatagrid.datagrid('load', serializeObject($('#batchSubstituteGatheringQueryForm')));
	};
	
	function _clear(){
		$('#batchSubstituteGatheringQueryForm').form('clear');	
	}
	function batchAccountBalance(id1,id2,id3){
		var value1=$('#'+id1+'').val();
		var value2=$('#'+id2+'').val();
		$('#'+id3+'').val(parseFloat(value2)-parseFloat(value1));
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
		var value1=$('#'+id1+'').val();
		var value2=$('#'+id2+'').val();
		if(parseFloat(value2)>parseFloat(value1)){
			$('#'+id3+'').numberbox('setValue',value1);			
		}else{
			$('#'+id3+'').numberbox('setValue',value2);
		}
	}
	var loadOldBalance=function(flag,id,id2){
		blockOrNone(flag,id);
		$('#'+id2+'').datagrid('reload');
	}
	var closeBalance=function(){
		blockOrNone(false,'substituteBatchBalance_oldGatheringDatagrid');
	}
	var validateIsAccordion=function(){
		var tab=$('#substituteBatchBalance_tabs').tabs('getSelected');
		if(tab.panel('options').title=='代付结算单'){
			var data=$('#firstBatchSubstituteGatheringDatagrid').datagrid('getSelected');
			var rows=$('#secondSubstituteGatheringDatagrid').datagrid('getSelections');
			if(data){
				$('#firstBatchSubstituteGatheringDatagrid').datagrid('unselectAll');
				$('#batchSubstituteGatheringAddForm').form('clear');
			}
			if(rows!=null&&rows.length>0){
				$('#secondSubstituteGatheringDatagrid').datagrid('unselectAll');
				$('#batchSubstituteGatheringAddForm').form('clear');
			}
		}else if(tab.panel('options').title=='代付批量结算单'){
			var data=$('#firstBatchSubstituteGatheringDatagrid').datagrid('getSelected');
			var rows=$('#batchSubstituteGatheringDatagrid').datagrid('getSelections');
			if(data){
				$('#firstBatchSubstituteGatheringDatagrid').datagrid('unselectAll');
				$('#batchSubstituteGatheringAddForm').form('clear');
			}
			if(rows!=null&&rows.length>0){
				$('#batchSubstituteGatheringDatagrid').datagrid('unselectAll');
				$('#batchSubstituteGatheringAddForm').form('clear');
			}
		}else if(tab.panel('options').title=='批量代付收款汇总'){
			var rows1=$('#batchSubstituteGatheringDatagrid').datagrid('getSelections');
			if(rows1!=null&&rows1.length>0){
				$('#batchSubstituteGatheringDatagrid').datagrid('unselectAll');
				$('#batchSubstituteGatheringAddForm').form('clear');
			}
			var rows2=$('#secondSubstituteGatheringDatagrid').datagrid('getSelections');
			if(rows2!=null&&rows2.length>0){
				$('#secondSubstituteGatheringDatagrid').datagrid('unselectAll');
				$('#batchSubstituteGatheringAddForm').form('clear');
			}
		}
	}