//综合分析 车辆销售分析js文件
$(function (){
	/*$('#car_sellDate').val(getStartDate($('#car_sellDate')));
	$('#car_sellDate2').val(getSystemTime()); 
	*/
	
	$('#tt').tabs({   
		onSelect:function(title){  
		
		 tbtitle = title;
			
	    }   
	});
	
		//初始化
		$('#carColor_datagrid_id').datagrid({
		url : 'instoreHouseQueryAction!doAssayColumns.action',
		type : 'POST',
		newrap : false,
		
		fit : true,
		width : 1024,
		height : 300,
		pageSize : 30,
		remoteSort : false,
		singleSelect : true,
		idField:'date',	
		sortOrder:'desc',
		sortName : 'date',	
		frozenColumns : [[{field : 'date',title : '',width : 150,sortable : true, align : 'center'}]],
		onLoadSuccess : function(data){
		if(data.total == '0'){
				var body = $(this).data().datagrid.dc.body2;//暂无符合相关条件的信息！
				body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="6">对不起，没有符合的信息！</td></tr>');
				}
			}
		});
		
		$('#carSellState_datagrid_id').datagrid({
			url : 'instoreHouseQueryAction!doAssayColumns.action',
			type : 'POST',
			newrap : false,
			fit : true,
			width : 1024,
			height : 300,
			pageSize : 30,
			remoteSort : false,
			singleSelect : true,
			idField:'date',	
			sortName : 'date',
			sortOrder:'desc',	
			frozenColumns : [[{field : 'date',title : '',width : 150,sortable : true, align : 'center'}]],
			onLoadSuccess : function(data){
			if(data.total == '0'){
				var body = $(this).data().datagrid.dc.body2;
				body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="6">对不起，没有符合的信息！</td></tr>');
				}
				}
			});
		
		$('#dis_datagrid_id').datagrid({
			url : 'instoreHouseQueryAction!doAssayColumns.action',
			type : 'POST',
			newrap : false,
			fit : true,
			width : 1024,
			height : 300,
			pageSize : 30,
			remoteSort : false,
			singleSelect : true,
			idField:'date',	
			sortName : 'date',
			sortOrder:'desc',	
			frozenColumns : [[{field : 'date',title : '',width : 150,sortable : true, align : 'center'}]],
			onLoadSuccess : function(data){
			if(data.total == '0'){
				var body = $(this).data().datagrid.dc.body2;
				body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="6">对不起，没有符合的信息！</td></tr>');
				}
				}
			});
		doFindbyCondition();
});
		
	
		var recordArray = new Array();
		var s = "";
		var ss = "";
		var options = {};
	
	//客户满意度统计添加不固定列方法
	function addColome(resault,daId){
		var str1 ="[[";
		var str2 = "";
		if(resault !=null && "null"!=resault&&resault.length>0){
		var array = resault.substring(1,resault.indexOf("]")).split(",");
			for ( var i = 0; i < array.length; i++) {
				var str = array[i].substring(1,array[i].length-1).replace(" ", "");
				str2 = str2 + ",{title : '"+str+"',field : 'carmodel"+str+"',width : 100,sortable : true, align : 'center'} ";
			}
			str2 = str2 + ",{title : '汇总',field : 'TOTAL',width : 100,sortable : true, align : 'center'} ";
		}
		var newstr2 = str2.substring(1);
		
		var str3 = "]]";
		var laststr = str1 + newstr2 + str3;
			 options = {};
			 options.url = '';
			 options.columns = eval(laststr);
			 daId.datagrid(options);
			return true;	 
		}
	
		//条件查询
		function doFindbyCondition(){
			
			var form = $('#kucun_form_id').form();
			var formvalue = serializeObject(form);
			
				
				//同时访问 后台getName方法筛选出需要的列
				$.ajax({
				type : 'POST', 
				url : 'instoreHouseQueryAction!doAssayColumns.action?columnsTag=true&tabType=carColor',
				data : formvalue,
				success : function(rest){
						var t = addColome(rest,$('#carColor_datagrid_id'));
						if(t){
							$.ajax({
							type : 'POST', 
							url : 'instoreHouseQueryAction!getCarColor.action',
							data : formvalue,
							dataType : 'json',
							success : function(resault){
								var object=JSON.parse(resault);
								$('#carColor_datagrid_id').datagrid('loadData',object);		

							}
							});
						}
				}
				});
			
			
				
				//同时访问 后台getName方法筛选出需要的列
				$.ajax({
				type : 'POST', 
				url : 'instoreHouseQueryAction!doAssayColumns.action?columnsTag=true&tabType=sellState',
				data : formvalue,
				success : function(rest){
						var t = addColome(rest,$('#carSellState_datagrid_id'));
						if(t){
							$.ajax({
							type : 'POST', 
							url : 'instoreHouseQueryAction!getInforBySellState.action',
							data : formvalue,
							dataType : 'json',
							success : function(resault){
								var object=JSON.parse(resault);
								$('#carSellState_datagrid_id').datagrid('loadData',object);
						}
					});
				}
			}
		});
		
				//同时访问 后台getName方法筛选出需要的列
				$.ajax({
				type : 'POST', 
				url : 'instoreHouseQueryAction!doAssayColumns.action?columnsTag=true&tabType=dis',
				data : formvalue,
				success : function(rest){
						var t = addColome(rest,$('#dis_datagrid_id'));
						if(t){
							$.ajax({
							type : 'POST', 
							url : 'instoreHouseQueryAction!getInforByDis.action',
							data : formvalue,
							dataType : 'json',
							success : function(resault){
								var object=JSON.parse(resault);
								$('#dis_datagrid_id').datagrid('loadData',object);

						}
					});
				}
			}
		});
			
			
	}

		//清空 
		function doClear(){
			$('#kucun_form_id').form('clear');
			
			if(tbtitle=="车辆颜色分类"){
				$('#carColor_datagrid_id').datagrid('unselectAll');
				
	   	 	}if(tbtitle=="车辆分销状态"){
	   	 		$('#dis_datagrid_id').datagrid('unselectAll');
	   	 		
	   	 	}
		if(tbtitle=="车辆销售状态"){
   	 		$('#carSellState_datagrid_id').datagrid('unselectAll');
   	 		
   	 	}
	   	 	
			}
		
		
		
		function _except(){
		
				if(tbtitle =='车辆颜色分类'){
					var data =  $("#carColor_datagrid_id").datagrid('getData'); 
					 if(data.rows.length==0){
						 alertMsg('数据列表为空，不能导出！', 'warning');
						 return ;
					 }
					showEditDialog("carColor_datagrid_id",null,"carColor","开始导出","导出配置",0,_callbackExcept);
					
				}else if(tbtitle =='车辆分销状态'){
					var data =  $("#dis_datagrid_id").datagrid('getData'); 
					 if(data.rows.length==0){
						 alertMsg('数据列表为空，不能导出！', 'warning');
						 return ;
					 }
					showEditDialog("dis_datagrid_id",null,"fenx","开始导出","导出配置",0,_callbackExcept2);	
				}else if(tbtitle=="车辆销售状态"){
					var data =  $("#carSellState_datagrid_id").datagrid('getData'); 

					if(data.rows.length==0){
						 alertMsg('数据列表为空，不能导出！', 'warning');
						 return ;
					 }
					showEditDialog("carSellState_datagrid_id",null,"carSellState","开始导出","导出配置",0,_callbackExcept3);
					
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
				exportEsuyUIExcelFile(parentId,fieldNames,"车辆颜色分类"+getSystemTime());
			}
			function _callbackExcept2(parentId,fieldNames){
				exportEsuyUIExcelFile(parentId,fieldNames,"车辆分销状态"+getSystemTime());
			}function _callbackExcept2(parentId,fieldNames){
				exportEsuyUIExcelFile(parentId,fieldNames,"车辆销售状态"+getSystemTime());
			}
			
			/**
			 * 打印字段设置
			 * 编辑、选择不同分组
			 * @return
			 */
			function _config(){
				if(tbtitle =='车辆颜色分类'){
					var data =  $("#carColor_datagrid_id").datagrid('getData'); 
					 if(data.rows.length==0){
						 alertMsg('数据列表为空，不能打印！', 'warning');
						 return ;
					 }
					showEditDialog("carColor_datagrid_id",personnelSumTable,"carColor","开始打印","打印配置",2,_print);
					
				}else if(tbtitle =='车辆分销状态'){
					var data =  $("#dis_datagrid_id").datagrid('getData'); 
					 if(data.rows.length==0){
						 alertMsg('数据列表为空，不能打印！', 'warning');
						 return ;
					 }
					showEditDialog("dis_datagrid_id",personnelSumTable,"fenx","开始打印","打印配置",2,_print);	
				}else if(tbtitle=="车辆销售状态"){
					var data =  $("#carSellState_datagrid_id").datagrid('getData'); 
					 if(data.rows.length==0){
						 alertMsg('数据列表为空，不能打印！', 'warning');
						 return ;
					 }
					showEditDialog("carSellState_datagrid_id",personnelSumTable,"carSellState","开始打印","打印配置",2,_print);
					
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
			
