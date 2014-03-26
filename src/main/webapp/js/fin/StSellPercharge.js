var manager=null;
   function   LoadOk(){
	     if(document.readyState   =="complete"){
	   initFrame();
	 }else{
	   setTimeout("LoadOk()",200);
		 }
   }
   setTimeout("LoadOk()",200);
   //判断页面初始化加载完成    执行
   function   initFrame(){
	   manager=$('#ssp_stfName').val();
   }

   function addPersonnel(i){
	   if(i==1){
			var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addStSellPercharge();">保存</a>';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancelAddStSellPercharge();">取消</a>';
		if($('#button').children('a').length == 0){
			$('#button').append(save).append(cancel);
			$.parser.parse('#button');
		}
		$('#StSellPerchargeForm').form('clear');
		$('#fin_ar_group_form').form('clear');
        $('#ssp_stfName').val(manager);
        $('#perchargeDate').val(getSystemTime());
		
   }else{
	   var perchargeId = $('#perchargeId').val();
	   if(perchargeId!=null&&perchargeId!=''){
		   var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updateStRtGoodsMain();">保存</a>';
		   var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancelAddStSellPercharge();">取消</a>';
		   if($('#button').children('a').length == 0){
			 $('#button').append(save).append(cancel);
			 $.parser.parse('#button');
		   }
	   }else{
		   $.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){});
		   }
	   }
 }

 //储备金新增
 function addStSellPercharge(){
     var isno=$('#StSellPerchargeForm').form('validate');
 if(isno){
	 $.ajax({   
			type : "POST",
			url : "StSellPerchargeAction!saveStSellPercharge.action",
			data : $('#StSellPerchargeForm').serialize(),
			dataType : "json",
			success : function callback(r) {
            if(r.success){
  	           ssp_clearSearchCondition();
  	           cancelAddStSellPercharge();
            }else
            	$.messager.show({title : '提示',msg : '退货单删除失败！',showType : 'slide'});
        }
     });	  
 }else
	 $.messager.alert('优亿软件提示','抱歉，信息填写不完整，记录无法保存！','warning',function(){});
	 
 }

 //储备金删除
 function deleteStSellPercharge(){
    var selected=$('#StSellPercharge_datagrid').datagrid('getSelected');
if(selected!=null&&selected!=''){
     $.messager.confirm('优亿软件提示', '请确认是否要删除单号为【'+selected.perchargeId+'】的储备金吗？', function(r){
	 if(r){
		  $.ajax({
			type : 'POST',
			url : 'StSellPerchargeAction!deleteStSellPercharge.action',
			data : 'perchargeId='+selected.perchargeId,
			dataType : 'json',
			success : function callback(r) {
			    if(r.success)
			    	 $('#StSellPercharge_datagrid').datagrid('load')
	    	    else
	    	    	$.messager.alert('优亿软件提示','储备金删除失败！','warning',function(){});
            }
          });
	   } 
    });
}else
	$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning',function(){});
 }

 //储备金修改
 function updateStRtGoodsMain()
 {
	 var isno=$('#StSellPerchargeForm').form('validate');
 if(isno){
	 $.ajax({   
			type : "POST",
			url : "StSellPerchargeAction!updateStSellPercharge.action",
			data : $('#StSellPerchargeForm').serialize(),
			dataType : "json",
			success : function callback(r) {
            if(r.success){
  	           ssp_clearSearchCondition();
  	           cancelAddStSellPercharge();
            }else
            	$.messager.show({title : '提示',msg : '退货单删除失败！',showType : 'slide'});
        }
     });	
  }else
	 $.messager.alert('优亿软件提示','抱歉，信息填写不完整，记录无法保存！','warning',function(){});  
 }

 function cancelAddStSellPercharge(){
	 $('#button').empty();
 }

 //综合条件查询
 function ssp_searchByCondition()
 {
     var tabName=$('#fin_ar_tabs').tabs('getSelected');
 var tab = tabName.panel('options').title;
 if(tab=='维修储备金'){
	 $.ajax({
		type : 'POST',
		url : 'StSellPerchargeAction!searchStSellPerchargeByCondition.action',
		data :'perchargeDateBegin='+$('#perchargeDateBegin').val()+'&perchargeDateEnd='+$('#perchargeDateEnd').val()+
		'&carLicense='+$('#ssps_carLicense').combobox('getText')+'&vin='+$('#ssps_vin').val(),
		dataType : 'json',
		success : function(r){
			if(r.total>0)
				$('#StSellPercharge_datagrid').datagrid('loadData',r);
 			else
 				$.messager.alert('优亿软件提示','对不起，没有找到你要查询的记录！','warning',function(){});
        }
      }); 	
 }else if(tab=='储备金余额')
 {
     $.ajax({
		type : 'POST',  
		url : 'StSellPerchargeAction!searchStRechargeByCondition.action',
		data :'carLicense='+$('#spp_stRecharge_carLicense').val()+'&customName='+$('#spp_stRecharge_customName').val(),
		dataType : 'json',
		success : function(r){
			if(r.total>0)
				$('#StRechargeTable').datagrid('loadData',r);
 			else
 				$.messager.alert('优亿软件提示','对不起，没有找到你要查询的记录！','warning',function(){});
        }
     }); 	
 }else if(tab=='储备金使用记录')
 {
	 $.ajax({
		type : 'POST',
		url : 'StSellPerchargeAction_serachStUsePerchargeByCondition.action',
		data :'repcollTimeStart='+$('#ssp_StUsePercharge_repcollTimeStart').val()+'&repcollTimeEnd='+$('#ssp_StUsePercharge_repcollTimeEnd').val()+
		'&carLicense='+$('#ssp_StUsePercharge_carLicense').val()+'&customName='+$('#ssp_StUsePercharge_customName').val(),
		dataType : 'json',
		success : function(r){
 				if(r.total>0)
 					$('#StUsePerchargeTable').datagrid('loadData',r);
 	 			else
 	 				$.messager.alert('优亿软件提示','对不起，没有找到你要查询的记录！','warning',function(){});
            }
         }); 
     }
 }

 //清空查询条件
 function ssp_clearSearchCondition(){
	     var tabName=$('#fin_ar_tabs').tabs('getSelected');
     var tab = tabName.panel('options').title;
     if(tab=='维修储备金'){
       $('#StSellPerchargeForm').form('clear');
	   $('#fin_ar_group_form').form('clear');
	   $('#StSellPercharge_datagrid').datagrid('load');
     }else if(tab=='储备金余额'){
    	 $('#spp_stRecharge_form').form('clear');
    	 $('#StRechargeTable').datagrid('load');
     }else if(tab=='储备金使用记录'){
    	 $('#StUsePerchargeForm').form('clear');
    	 $('#StUsePerchargeTable').datagrid('load');
         }
 }