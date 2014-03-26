function addPersonnel(i){
	   if(i==1){
		    disAbledControl();
			var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addStSellPercharge();">保存</a>';
			var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancelAddStSellPercharge();">取消</a>';
			if($('#button').children('a').length == 0){
				$('#button').append(save).append(cancel);
				$.parser.parse('#button');
			}
			$('#pre_StSellPerchargeForm').form('clear');
			$('#pre_fin_ar_group_form').form('clear');
            $('#pre_ssp_stfName').val('888');
            $('#pre_perchargeDate').val(getSystemTime());
	   }else{
		   var perchargeId = $('#pre_perchargeId').val();
		   if(perchargeId!=null&&perchargeId!=''){
			   disAbledControl();
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

//维修预收款新增
 function addStSellPercharge(){
     var isno=$('#pre_StSellPerchargeForm').form('validate');
     if(isno){
    	 $.ajax({   
				type : "POST",
				url : "StSellPerchargeAction!savePreStSellPercharge.action",
				data : $('#pre_StSellPerchargeForm').serialize(),
				dataType : "json",
				success : function callback(r) {
				   if(r.success){
					   pre_ssp_clearSearchCondition();
		  	           cancelAddStSellPercharge();
				   }
				   else
					   $.messager.alert('优亿软件提示','抱歉，维修预收款新增失败！','warning',function(){});
            }
         });	  
	 }else
		 $.messager.alert('优亿软件提示','抱歉，信息填写不完整，记录无法保存！','warning',function(){});
	 
 }

//维修预收款删除
 function deleteStSellPercharge()
 {
	var selected=$('#pre_StSellPrePercharge_datagrid').datagrid('getSelected')
 	if(selected!=null&&selected!=''){
 	  $.messager.confirm('优亿软件提示', '请确认是否要删除单号为【'+selected.perchargeId+'】的维修预收款单吗？', function(r){
 		 if(r){
 			  $.ajax({
				type : "POST",
				url : "StSellPerchargeAction!deleteStSellPercharge.action",
				data : 'perchargeId='+selected.perchargeId,
				dataType : "json",
				success : function callback(r) {
 		    	    if(r.success)
 		    	    	pre_ssp_clearSearchCondition();
 		    	    else
 		    	    	$.messager.alert('优亿软件提示','抱歉，维修预收款删除失败！','warning',function(){});
	            }
	          });
 		   } 
 	    });
 	}else{
 		$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning',function(){});
 	}
 }

 //维修预收款修改
 function updateStRtGoodsMain()
 {
	 var isno=$('#pre_StSellPerchargeForm').form('validate');
     if(isno){
    	 $.ajax({   
				type : 'POST',
				url : 'StSellPerchargeAction!updatePreStSellPercharge.action',
				data : $('#pre_StSellPerchargeForm').serialize(),
				dataType : 'json',
				success : function callback(r) {
  	            if(r.success)
  	            	pre_ssp_clearSearchCondition();
  	            else
  	            	$.messager.alert('优亿软件提示','抱歉，维修预收款修改失败！','warning',function(){});
            }
         });	
     }else
		 $.messager.alert('优亿软件提示','抱歉，信息填写不完整，记录无法保存！','warning',function(){});  
 }

 //新增取消按钮
 function cancelAddStSellPercharge(){
	 $('#button').empty();
	 nuDisAbledControl()
 }

 //综合查询
 function ssp_searchByCondition()
 {
     var tabName=$('#pre_fin_ar_tabs').tabs('getSelected');
     var tab = tabName.panel('options').title;
     if(tab=='维修预收款'){
    	 $.ajax({
			type : 'POST',
			url : 'StSellPerchargeAction!searchStSellPerchargeByCondition.action',
			data :'perchargeDateBegin='+$('#pre_perchargeDateBegin').val()+'&perchargeDateEnd='+$('#pre_perchargeDateEnd').val()+
			'&carLicense='+$('#pre_ssps_carLicense').combobox('getText')+'&vin='+$('#pre_ssps_vin').val(),
			dataType : 'json',
			success : function(r){
 				if(r.total>0)
 					$('#pre_StSellPercharge_datagrid').datagrid('loadData',r);
 	 			else
 	 				$.messager.alert('优亿软件提示','对不起，没有找到你要查询的记录！','warning',function(){});
            }
          }); 	
     }else if(tab=='预收款余额')
     {
         $.ajax({
			type : 'POST',  
			url : 'StSellPerchargeAction!searchStPreRechargeByCondition.action',
			data :'carLicense='+$('#spp_pre_stRecharge_carLicense').val()+'&customName='+$('#spp_pre_stRecharge_customName').val(),
			dataType : 'json',
			success : function(r){
 				if(r.total>0)
 					$('#st_pre_RechargeTable').datagrid('loadData',r);
 	 			else
 	 				$.messager.alert('优亿软件提示','对不起，没有找到你要查询的记录！','warning',function(){});
            }
         }); 	
     }else if(tab=='预收款使用记录')
     {
    	 $.ajax({
			type : 'POST',
			url : 'StSellPerchargeAction!serachPreStUsePerchargeByCondition.action',
			data :'repcollTimeStart='+$('#ssp_pre_StUsePercharge_repcollTimeStart').val()+'&repcollTimeEnd='+$('#ssp_pre_StUsePercharge_repcollTimeEnd').val()+
			'&carLicense='+$('#ssp_pre_StUsePercharge_carLicense').val()+'&customName='+$('#ssp_pre_StUsePercharge_customName').val(),
			dataType : 'json',
			success : function(r){
	 				if(r.total>0)
	 					$('#pre_StUsePerchargeTable').datagrid('loadData',r);
	 	 			else
	 	 				$.messager.alert('优亿软件提示','对不起，没有找到你要查询的记录！','warning',function(){});
            }
         }); 
     }
 }

 //清空查询条件
 function pre_ssp_clearSearchCondition()
 {
	     var tabName=$('#pre_fin_ar_tabs').tabs('getSelected');
	     nuDisAbledControl();
         var tab = tabName.panel('options').title;
         if(tab=='维修预收款'){
           $('#pre_StSellPerchargeForm').form('clear');
		   $('#pre_fin_ar_group_form').form('clear');
		   $('#pre_StSellPrePercharge_datagrid').datagrid('load');
         }else if(tab=='预收款余额'){
        	 $('#spp_pre_stRecharge_form').form('clear');
        	 $('#st_pre_RechargeTable').datagrid('load');
         }else if(tab=='预收款使用记录'){
        	 $('#pre_StUsePerchargeForm').form('clear');
        	 $('#pre_StUsePerchargeTable').datagrid('load');
         }
 }
 
	/**
	 * 启用控件
	 */
	function nuDisAbledControl(){
		$('a.easyui-linkbutton').linkbutton('enable');
	}

	/**
	 * 禁用控件
	 */
	function disAbledControl(){
		$('a.easyui-linkbutton').linkbutton('disable');
	}
	
	/**
	 * 
	 * 导出excel
	 * 选择要导出的列
	 * 参数1   dateGrid控件id属性
	 * 参数2   dateGrid控件对应数据库类型
	 * 参数3   dateGrid控件上层控件id属性
	 * 参数4   执行按钮value文本
	 * 参数5   title文本
	 * 参数6   功能类型    0导出   1导入   2打印    3隐藏列
	 * 参数7   回调函数
	 * @return
	 */
	function _except(){
		showEditDialog("pre_StSellPrePercharge_datagrid",null,"pre_StSellPrePercharge_datagrid_div","开始导出","导出配置",0,_callbackExcept);
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
		exportEsuyUIExcelFile(parentId,fieldNames,"维修预收款"+getSystemTime());
	}