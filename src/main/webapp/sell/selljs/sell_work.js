// 车辆销售js文件
//设置按钮的状态为可用
	function enableBtn(){
		$('#_add').linkbutton('enable');
		$('#_remove').linkbutton('enable');
		$('#_update').linkbutton('enable');
		$('#_search').linkbutton('enable');
		$('#_clear').linkbutton('enable');
		$('#_examine').linkbutton('enable');
		$('#_print').linkbutton('enable');
		$('#_export').linkbutton('enable');
		$('#_import').linkbutton('enable');
		
	}
	//禁用按钮
	function disableBtn(){
		$('#_add').linkbutton('disable');
		$('#_remove').linkbutton('disable');
		$('#_update').linkbutton('disable');
		$('#_search').linkbutton('disable');
		$('#_clear').linkbutton('disable');
		$('#_examine').linkbutton('disable');
		$('#_examine').linkbutton('disable');
	
	}
		//条件查询提交
		function doConditionSubmit(formid,datagrid,dateId1,dateId2){
			var form =  formid.form();
			var formvalue = serializeObjectByflag(form,false);
			datagrid.datagrid('load',formvalue);
			addInitDate(dateId1,dateId2);
		}
    	//清空form表单
	 	function doClear(fromid,dgrid,dateId1,dateId2){
	 		fromid.form('clear');
	 		doConditionSubmit(fromid,dgrid,dateId1,dateId2);
	 		$('#carBrand_id').combobox('reload');
	 		$('#carModel_id').combobox('reload','carModelAction!findAllCarModel.action');
	 	}
	 	
	 	//获取所有行数据
		function getChangeRows(id){
			if(id.datagrid('getChanges').length) {
				var inserted = id.datagrid('getChanges', 'inserted');
				var deleted = id.datagrid('getChanges', 'deleted');
				var updated = id.datagrid('getChanges', 'updated');
			
				var effectRow = new Object();
				if(inserted){
					effectRow['inserted'] = JSON.stringify(inserted);
				}
				if(deleted){
					effectRow['deleted'] = JSON.stringify(deleted);
				}
				if(updated){
					effectRow['updated'] = JSON.stringify(updated);
				}
				return effectRow;
			};
		}

		
		//审核
		function doAudit(datagrid,url){
			var value = datagrid.datagrid('getSelections');
				if(value.length>0){
					$.ajax({
						type : 'POST',
						url : url,
						data : value[0],
					    dataType : 'json',
						success : function(r){
							if(r.success){
								datagrid.datagrid('reload');
							}
						}
				   	});
				}else{
					alertMsg('对不起，请先选择要审核的记录！', 'warning');
				}
			}

		//添加PDI检测内容
		function addPDI(){
	  		var rows = $('#datagrid_pdi_check_id').datagrid('getRows');
			for(var i = 0; i < rows.length; i++){
				var index = $('#datagrid_pdi_check_id').datagrid('getRowIndex',rows[i]);
				
				$('#datagrid_pdi_check_id').datagrid('endEdit', index);
			}	
			
			var xs_Car_Id = $('#xscarids').val();		//车辆编号
			var xs_Car_Sel_Id = $('#xscarselids').val();//销售单编号
	    	var effectRows = getChangeRows($('#datagrid_pdi_check_id'));
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'carSellManageAction!savePDI.action?xs_Car_Sel_Id='+xs_Car_Sel_Id+'&xs_Car_Id='+xs_Car_Id,
				   data: effectRows,
				   success: function(r){
				   	if(r.success){
				   		alertMsg(r.msg, 'info');
				   		$('#datagrid_pdi_check_id').datagrid('load');
				   	}
				   }
				});
    		}
		
		
		//导出
		function doexcept(dateGridId,parentId){
			showEditDialog(dateGridId,null,parentId,"开始导出","导出配置",0,_callbackExcept);
		}
		function _callbackExcept(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"车辆档案"+getSystemTime());
		}
		//打印
		function dopoint(dateGridId,parentId){
			showEditDialog(dateGridId,null,parentId,"开始打印","打印配置",2,_print);
		}
		function _print(parentId,fieldNames){
			printEsuyUIPreview(parentId,fieldNames);
		}
  	
var tbtitle;
//初始化
$(function(){
		//初试时间
		addInitDate($('#register_Date'),$('#register_Date2'));
		addInitDate($('#reserve_Dete'),$('#reserve_Dete2'));
		addInitDate($('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));
		addInitDate($('#exitCar_Date'),$('#exitCar_Date2'));
		addInitDate($('#check_Date'),$('#check_Date2'));
//		addInitDate($('#interview_Date'),$('#interview_Date2'));
		//潜在客户跟踪  
		$('#interview_Date').val(getSystemTime());
		var today=new Date();
		today=new Date(today.setDate(today.getDate()+1)).format("yyyy-MM-dd")
		$('#interview_Date2').val(today);
		
		$('#form_quitcar_manage_id_div').hide();
		//车辆销售页面控制按钮的使用
		$('#selltabs').tabs({
			onSelect:function(title){
				if(title=="车辆销售管理"){
					$('#butid01').linkbutton('enable');
					$('#butid02').linkbutton('enable');
					$('#butid03').linkbutton('enable');
					$('#butid04').linkbutton('enable');
					$('#butid05').linkbutton('enable');
					$('#butid06').linkbutton('enable');
					$('#butid07').linkbutton('enable');
					$('#butid08').linkbutton('enable');
					$('#butid09').linkbutton('enable');
					$('#butid10').linkbutton('enable');
				}
				if(title=="PDI检测"){
					$('#butid01').linkbutton('disable');
					$('#butid02').linkbutton('disable');
					$('#butid03').linkbutton('disable');
					$('#butid04').linkbutton('disable');
					$('#butid05').linkbutton('disable');
					$('#butid06').linkbutton('disable');
					$('#butid07').linkbutton('disable');
					$('#butid08').linkbutton('disable');
					$('#butid09').linkbutton('disable');
					$('#butid10').linkbutton('disable');
				}
			}
		});
		
		//隐藏潜在客户弹出窗口div
		$('#from_possible_custom_follow_baseinfor_div').hide();
		//隐藏客户预定弹出窗口div
		$('#form_car_book_detail_id_div').hide();
		//隐藏车辆销售弹出窗口div
		$('#form_dialog_car_sell_manage_div').hide();
		
		$('#tab_back_register_id').tabs({   
				onSelect:function(title){  
				 tbtitle = title;
					if(title =='登记表汇总'){
						if (($('#addbut').children('a').length == 0)&&($('#editbut').children('a').length == 0) ){
							enableBtn();
						}
					}else if(title =='登记表明细'){
						//disableBtn();		
					}
			    }   
			});
	
	//给销售单明细添加 只读属性
	$('#form_car_sell_manage_id').find('input').attr("readonly","readonly");
	
	//登记表汇总
	$('#datagrid_back_register_id').datagrid({

		url : 'backRegisterAction!findRecord.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'xs_Custom_Salesman_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'xs_Custom_Salesman_Id',
			title : '登记单号',
			width : 150,
			sortable : true
		},{
			field : 'register_Date',
			title : '登记日期',
			width : 100,
			sortable : true
		},{
			field : 'weather',
			title : '当日天气',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'total',
			title : '合计来店',
			width : 100,
			sortable : true
			
		},{
			field : 'stf_Id',
			title : '经办人',
			width : 100,
			sortable : true,
			hidden :　true
		
		},{
			field : 'stf_Name',
			title : '经办人',
			width : 100,
			sortable : true
		
		},{
			field : 'remark',
			title : '备注信息',
			width : 500,
			sortable : true
		}
		]],
		onDblClickRow : function(rowIndex, rowData){
		$('#datagrid_back_register_detail_id').datagrid({url : 'backRegisterAction!findRecordById.action?xs_Custom_Salesman_Id='+rowData.xs_Custom_Salesman_Id});
		$('#tab_back_register_id').tabs('select','登记表明细');
		//将数据load给form 表单 
		$('#form_carlost_infor_detail_id').form('load',rowData);
		
		$('#id2').linkbutton('enable');	
	}
	});
	
	//PDI检测记录查询
	$('#datagrid_pdi_check_record_id').datagrid({

		url : 'carSellManageAction!findFactoryPdiCheck.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'id',
			title : '检测编号',
			width : 50,
			sortable : true,
			hidden : true
		},{
			field : 'check_Date',
			title : '检测日期',
			width : 100,
			sortable : true
		},{
			field : 'check_Person',
			title : '检测人',
			width : 100,
			sortable : true
			
		},{
			field : 'car_Vin_Number',
			title : 'VIN号',
			width : 150,
			sortable : true
		
		},{
			field : 'xs_Car_Brand',
			title : '车辆品牌',
			width : 100,
			sortable : true
		
		},{
			field : 'xs_Car_Model',
			title : '车辆型号',
			width : 150,
			sortable : true
		},{
			field : 'status',
			title : '检测结果',
			width : 100,
			sortable : true
			
		},{
			field : 'check_Comtent',
			title : '检测内容',
			width : 400,
			sortable : true
		},{
			field : 'remark',
			title : '备注',
			width : 400,
			sortable : true
		}
		]],
		onDblClickRow : function(rowIndex, rowData){
		
	}
	
		
	});

    
	//來店客流登记浏览
	$('#datagrid_back_register_look_id').datagrid({
		url : 'backRegisterAction!findRecordLook.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : false,
		singleSelect : true,
		nowrap : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'xs_Custom_Salesman_Detail_Id',
		sortName : 'xs_Custom_Salesman_Detail_Id',
		sortOrder : 'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'xs_Custom_Salesman_Detail_Id',
			title : '编号',
			width : 130,
			sortable : true
			
		},{
			field : 'xs_Custom_Salesman_Id',
			title : '汇总编号',
			width : 200,
			sortable : true,
			hidden : true
			
		},{
			field : 'register_Date',
			title : '登记日期',
			width : 90,
			sortable : true
			
		},{
			field : 'custom_Name',
			title : '客户名称',
			width : 80,
			sortable : true
		
		},{
			field : 'telephone',
			title : '电话',
			width : 100,
			sortable : true
			
		},{
			field : 'car_Model',
			title : '预购车型编号',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'car_Model_Name',
			title : '预购车型',
			width : 150,
			sortable : true
		},{
			field : 'stf_Id',
			title : '业务员',
			width : 90,
			sortable : true,
			formatter:function(value,row,index){
			return row.stf_Name;
			}
		},{
			field : 'custom_Level',
			title : '级别编号',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'custom_Level_Name',
			title : '级别',
			width : 90,
			sortable : true
		},{
			field : 'message_Channel',
			title : '渠道编号',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'message_Channel_Name',
			title : '渠道',
			width : 80,
			sortable : true
		},{
			field : 'talk_Way',
			title : '方式编号',
			width : 80,
			sortable : true,
			hidden : true
		},{
			field : 'talk_Way_Name',
			title : '方式',
			width : 80,
			sortable : true
		},{
			field : 'testdrive_Model',
			title : '试驾车型编号',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'testdrive_Model_Name',
			title : '试驾车型',
			width : 110,
			sortable : true
		},{
			field : 'visit_Status',
			title : '后续跟踪状态',
			width : 100,
			sortable : true
		},{
			field : 'talk_Content',
			title : '洽谈内容',
			width : 200,
			sortable : true
		},{
			field : 'remark',
			title : '备注',
			width : 250,
			sortable : true
		},{
			field:'xs_Custom_Deal',
			title:'状态',
			width:100,
			align:'center',
            formatter:function(value,row,index){
			//
				var s = '';
				if(value=="ss"){
			        s = '潜在客户';
				}if(value=="swa"){
					s = '预定客户';
				}if(value=="ds"){
					s = '放弃客户';
				}if(value=="de"){
					s = '购他车客户';
				}if(value=="dw"){
					s = '准放弃客户';
				}if(value=="as"){
					s = '成交客户';
				}
				return s;
        	}
		},{
			field:'operate',
			title:'操作',
			width:100,
			align:'center',
            formatter:function(value,row,index){
			//
				var s = '';
				if(value=="ss"){
			        s = '<a href="javascript:void(0)" id="abandonfollowid'+index+'" onclick="doAbandonAttention('+index+');" ><font color="red">放弃跟踪<font/></a> ';
				}if(value == "null" || value == "" || value == undefined){
					s = '<a href="javascript:void(0)" onclick="doTurnin();"><font color="red">转入跟踪系统<font/></a> ';
				}
				return s;
        	}
		}
		]]
	
	});
	//
	
	//登记表明细
	$('#datagrid_back_register_detail_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'xs_Custom_Salesman_Detail_Id',
			title : '编号',
			width : 130,
			sortable : true
			
		},{
			field : 'xs_Custom_Salesman_Id',
			title : '汇总编号',
			width : 120,
			sortable : true,
			hidden : true
			
		},{
			field : 'register_Date',
			title : '时间',
			width : 140,
			sortable : true
			
		},{
			field : 'exit_Date',
			title : '离店时间',
			width : 70,
			sortable : true
		},{
			field : 'xs_Custom_Id',
			title : '客户名称',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.custom_Name;
		}
		
		},{
			field : 'telephone',
			title : '电话',
			width : 110,
			sortable : true
		},{
			field : 'car_Brand',
			title : '预购品牌',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.car_Brand_Name;
		}
		},{
			field : 'car_Model',
			title : '预购车型',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.car_Model_Name;
		}
		},{
			field : 'testdrive_Model',
			title : '试驾车型',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.testdrive_Model_Name;
		}
		},{
			field : 'talk_Way',
			title : '方式',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.talk_Way_Name;
		}
		},{
			field : 'stf_Name',
			title : '销售顾问',
			width : 100,
			sortable : true
		},{
			field : 'stf_Id',
			title : '销售顾问id',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'talk_Content',
			title : '洽谈内容',
			width : 100,
			sortable : true
		},{
			field : 'custom_Level',
			title : '客户级别',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.custom_Level_Name;
		}
		},{
			field : 'message_Channel',
			title : '渠道',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.message_Channel_Name;
		}
		},{
			field : 'register',
			title : '后续跟踪状态',
			width : 110,
			sortable : true
		},{
			field : 'register_State',
			title : '后续跟踪状态',
			width : 110,
			hidden : true
		},{
			field : 'remark',
			title : '备注',
			width : 150,
			sortable : true
		} 
		
		]],onClickRow : function(rowIndex, rowData){
			
			$('#form_back_register_detail_south_id').form('load',rowData);
			
			$('#car_Brand_id').combobox('setValue',rowData.car_Brand);
			$('#car_Brand_id').combobox('setText',rowData.car_Brand_Name);
			
			$('#car_Model_id').combobox('setValue',rowData.car_Model);
			$('#car_Model_id').combobox('setText',rowData.car_Model_Name);
		
			$('#sstf_id').combobox('setValue',rowData.stf_Id);
			$('#sstf_id').combobox('setText',rowData.stf_Name);
			
			$('#custom_Level_id').combobox('setValue',rowData.custom_Level);
			$('#custom_Level_id').combobox('setText',rowData.custom_Level_Name);
			
			$('#message_Channel_id').combobox('setValue',rowData.message_Channel);
			$('#message_Channel_id').combobox('setText',rowData.message_Channel_Name);
			
			$('#talk_Way_id').combobox('setValue',rowData.talk_Way);
			$('#talk_Way_id').combobox('setText',rowData.talk_Way_Name);
			
			$('#testdrive_Model_id').combobox('setValue',rowData.testdrive_Model);
			$('#testdrive_Model_id').combobox('setText',rowData.testdrive_Model_Name);
			$('#form_back_register_detail_south_id').find('input').attr("readonly","readonly");
		 	 $("#form_back_register_detail_south_id input.easyui-combobox").combobox('disable');
			$("#form_back_register_detail_south_id input").prop("disabled", true);
			$("#form_back_register_detail_south_id select").prop("disabled", true);
			$("#form_back_register_detail_south_id textarea").prop("disabled",true);
			
		}
	
	});
	
	//预订单汇总
	$('#datagrid_car_book_id').datagrid({
		url : 'carBookAction!findCarBookInfor.action',
		fit : true,
		fitColumns : false,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 20,
		pageList : [ 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'reserve_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'reserve_Id',
			title : '编号',
			width : 80,
			sortable : true,
			hidden : true
		},{
			field : 'xs_Custom_Id',
			title : '客户编号',
			width : 80,
			sortable : true,
			hidden : true
		},{
			field : 'xs_Car_Id',
			title : '车辆编号',
			width : 80,
			sortable : true,
			hidden : true
		},{
			field : 'reserve_Code',
			title : '预定单号',
			width : 120,
			sortable : true
		},{
			field : 'reserve_Dete',
			title : '预订日期',
			width : 100,
			sortable : true
		},{
			field : 'xs_Custom_Name',
			title : '客户名称',
			width : 90,
			sortable : true
		}, {
			field : 'xs_Custom_Telephone',
			title : '电话',
			width : 100,
			sortable : true
		}, {
			field : 'stf_Id',
			title : '业务员',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.stf_Name;
		}
		}, {
			field : 'xs_Car_Brand',
			title : '车辆品牌',
			width : 80,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
				return rowData.xs_Car_Brand_Name;
			}
		}, {
			field : 'xs_Car_Model_Id',
			title : '车辆型号',
			width : 120,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
				return rowData.xs_Model_Name;
			}
		}, {
			field : 'xs_Car_Color',
			title : '颜色',
			width : 70,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.xs_Car_Color_Name;
		}
		}, {
			field : 'xs_Car_Vin_Number',
			title : 'VIN号',
			width : 130,
			sortable : true
		}, {
			field : 'stf_Id_Person',
			title : '经办人',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
				return rowData.stf_Id_PersonName;
			}
		}, {
			field : 'payment_Money',
			title : '预付金额',
			width : 70,
			sortable : true
		}, {
			field : 'sellingprice',
			title : '销售价格',
			width : 70,
			sortable : true
		}, {
			field : 'predict_Take_Date',
			title : '预交日期',
			width : 90,
			sortable : true
		}, {
			field : 'order_State',
			title : '预定状态',
			width : 70,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.order_State_Name;
		}
		},{
			field : 'car_Output_Volume',
			title : '排量',
			width : 60,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.car_Output_Volume_Name;
		}
		}, {
			field : 'limit_Load_Num',
			title : '限乘人数',
			width : 55,
			sortable : true
		}, {
			field : 'level',
			title : '客户级别',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			return rowData.level_Name;
		}
		}, {
			field : 'pact_Code',
			title : '合同单号',
			width : 100,
			sortable : true
		},{
			field : 'examine',
			title : '审核情况',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			if(rowData.examine_Name=="未审核"){
				return '<font color="red">未审核</font>';
			}else{
				return '已审核';
			}
			
			}
		}, {
			field : 'custom_Opinion',
			title : '客户要求',
			width : 200,
			sortable : true
		}, {
			field : 'remark',
			title : '备注',
			width : 300,
			sortable : true
		}, {
			field : 'xs_Distributor_Code',
			title : '分销商',
			width : 100,
			sortable : true,
			hidden : true
		}, {
			field : 'first_Pay_Money',
			title : '按揭首付',
			width : 100,
			sortable : true,
			hidden : true
		}, {
			field : 'loan_Bank',
			title : '按揭行',
			width : 100,
			sortable : true,
			hidden : true 
		}, {
			field : 'loan_Limit_Money',
			title : '按揭额',
			width : 100,
			sortable : true,
			hidden : true
		}, {
			field : 'loan_Limit_Year',
			title : '按揭年限',
			width : 100,
			sortable : true,
			hidden : true
		}, {
			field : 'shingle_Money',
			title : '预测上牌费用',
			width : 100,
			sortable : true,
			hidden : true  
		}, {
			field : 'custom_Property',
			title : '车辆性质',
			width : 100,
			sortable : true,
			hidden : true
		}, {
			field : 'decorate_Money',
			title : '装潢金额',
			width : 100,
			sortable : true,
			hidden : true
		}
		
		]],onDblClickRow : function(rowIndex, rowData){
			$('#tabs_car_book_id').tabs('select','预订单明细');
			$('#form_car_book_detail_id').form('load',rowData);
		}
	
	});
	
	//潜在客户跟踪 
	$('#datagrid_possible_custom_follow_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'reserve_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'reserve_Id',
			title : '编号',
			width : 200,
			sortable : true,
			hidden : true
			
		},{
			field : 'tracingCode',
			title : '跟踪单号',
			width : 200,
			sortable : true,
			hidden : true
		},{
			field : 'tracing_Id',
			title : '潜在客户编号',
			width : 200,
			sortable : true,
			hidden : true
			
		},{
			field : 'xs_Custom_Name',
			title : '客户姓名',
			width : 100,
			sortable : true
			
		},{
			field : 'tracing_Date',
			title : '跟踪日期',
			width : 135,
			sortable : true
			
		},{
			field : 'interview_Date',
			title : '下次预约',
			width : 135,
			sortable : true
			
		},{
			field : 'tracing_Way',
			title : '记录分类',
			width : 80,
			sortable : true,
			hidden : true
			
		},{
			field : 'tracing',
			title : '记录分类',
			width : 100,
			sortable : true
			
		},{
			field : 'tracing_Address',
			title : '地点',
			width : 100,
			sortable : true
		},{
			field : 'ambience',
			title : '气氛情况',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'ambienceN',
			title : '气氛情况',
			width : 100,
			sortable : true
			
		}, {
			field : 'car_Model',
			title : '是乘试驾车型',
			width : 100,
			sortable : true,
			hidden : true
		},
		{
			field : 'carModelN',
			title : '试乘试驾车型',
			width : 130,
			sortable : true
		},{
			field : 'talk_Title',
			title : '讨论主题',
			width : 200,
			sortable : true
			
		},{
			field : 'hinder_Content',
			title : '成交障碍',
			width : 100,
			sortable : true
		},{
			field : 'examine_Opinion',
			title : '领导批示',
			width : 100,
			sortable : true
			
		},{
			field : 'tracing_Summary',
			title : '跟踪总结',
			width : 100,
			sortable : true
			
		},{
			field : 'level',
			title : '等级',
			width : 100,
			hidden:true
			
		},{
			field : 'xs_Custom_Inputdata',
			title : '建档日期',
			width : 100,
			hidden:true
			
		},{
			field : 'examine_Flag',
			title : '是否审批',
			width : 130,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			if(value==null || value=="0"){
				return '否';
			}else{
				return '是';
			}
		}
		}
		
		]],onClickRow : function(rowIndex,rowData){
    	$('#from_possible_custom_follow_baseinfor_id').form('load',rowData); 
	    },onDblClickRow : function(rowIndex, rowData){
	    	
	    	$('#from_possible_custom_follow_baseinfor_id').form('clear');
	    	
    		$('#from_possible_custom_follow_baseinfor_id').form('load',rowData);
    	
			showWindow(3);
			disAbledControl();
			$("#examine_Opinion_id").removeAttr("readonly");
			//最后跟踪
			$('#lasttracingdate').val(rowData.tracing_Date);
			$('#examine_Date').val(getSystemTime());
			//启用审批按钮按钮
			$('#doexameid').linkbutton('enable');
			
			
		}
	
	});
	//潜在客户跟踪西面  
	//treegrid
	$('#datagrid_possible_custom_follow_west_id').treegrid({  
	    url:'${pageContext.request.contextPath}/possibleCustomFollowAction!findCustomInforCount.action',  
	    fit : true,
	    fitColumns : true,
	    idField:'xs_Custom_Hide_Level_Id',  
	    treeField:'xs_Custom_Hide_Level',  
	    columns:[[  
                  {field:'xs_Custom_Hide_Level_Id',title:'编号',width:160,hidden:true},  
                  {field:'xs_Custom_Hide_Level',title:'级别',width:130},  
	              {field:'xs_Custom_Name',title:'数量/客户名称',width:100},
	              
	              {field:'custom_Id',title:'客户编号',width:50,hidden:true},
	              //{field:'custom_Property_Id',title:'客户性质',width:50,hidden:true},
	              //{field:'custom_Property',title:'客户性质',width:50,hidden:true},
	              //{field:'level',title:'客户潜在级别',width:50,hidden:true},
	              //{field:'levelName',title:'客户潜在级别名称',width:50,hidden:true},
	              {field:'xs_Custom_Inputdata',title:'建档日期',width:50,hidden:true},
	              {field:'levaJiange',title:'跟踪间隔',width:50,hidden:true},
	              {field:'xs_Custom_Telephone',title:'电话',width:50,hidden:true},
	              {field:'Tracing_Date',title:'跟踪日期',width:50,hidden:true},
	              {field:'hide_Level',title:'级别id',width:50,hidden:true}
	             
	             ]],onBeforeExpand:function(rowData){
					//动态设置展开查询的url
					var url = 'possibleCustomFollowAction!findCustomInfor.action?xs_Custom_Hide_Level_Id='+rowData.xs_Custom_Hide_Level_Id+'&'+$('#from_possible_custom_follow_id').serialize();
					
					$('#datagrid_possible_custom_follow_west_id').treegrid("options").url = url;  	
					return true;
			    },onBeforeLoad : function(row,param){
			    	
			    },onClickRow : function(row){
			    	$('#datagrid_possible_custom_follow_id').datagrid({url : 'possibleCustomFollowAction!findCustomById.action?custom_Id='+row.custom_Id});
			    	//$('#from_possible_custom_follow_west_id').form('load',row);
			    }
			});//
	
	//车辆销售管理 销售单汇总
	$('#datagrid_car_sell_manage_id').datagrid({
		url : 'carSellManageAction!findSellInfor.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		singleSelect : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName: 'xs_Car_Sel_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'xs_Car_Sel_Id',
			title : '销售单号',
			width : 100,
			hidden : true//
		},{
			field : 'sell_Code',
			title : '销售单号',
			width : 150,
			sortable : true
		},{
			field : 'out_Id',
			title : '出库单号',
			width : 100,
			hidden : true
		},{
			field : 'reserve_Code',
			title : '预订单编号',
			width : 100,
			hidden : true
		},{
			field : 'xs_Car_Sel_Data',
			title : '销售日期',
			width : 100,
			sortable : true
		},{
			field : 'xs_Custom_Name',
			title : '客户名称',
			width : 100,
			sortable : true
		},{
			field : 'xs_Car_Vin_Number',
			title : 'VIN编号',
			width : 160,
			sortable : true
		}, {
			field : 'xs_Car_Ocn',
			title : 'OCN码',
			width : 100,
			sortable : true
		},{
			field : 'xs_Car_Id',
			title : '车辆编号',
			width : 100,
			hidden : true
		},  {
			field : 'xs_Car_Brand',
			title : '车品牌',
			width : 100,
			hidden : true
		},  {
			field : 'xs_Car_Brand_Name',
			title : '车品牌',
			width : 100,
			sortable : true
		}, {
			field : 'xs_Model_Name',
			title : '车类型',
			width : 150,
			sortable : true
		}, {
			field : 'xs_Car_Sel_Transaction_Money',
			title : '成交价格',
			width : 100,
			sortable : true
		},{
			field : 'stf_Name',
			title : '业务员',
			width : 100,
			sortable : true
		}, {
			field : 'xs_Car_Stf_Id',
			title : '经办人',
			width : 100,
			hidden:true,
			sortable : true
		},{
			field : 'xs_Car_Stf_Name',
			title : '经办人',
			width : 100,
			sortable : true
		},{
			field : 'limit_load_num',
			title : '限乘人数',
			width : 100,
			sortable : true
		},{
			field : 'mobilephone',
			title : '电话',
			width : 130,
			sortable : true
		},{
			field : 'xs_Car_Sel_Type',
			title : '销售分类',
			width : 100,
			hidden : true
		},{
			field : 'xs_Car_Sel_TypeName',
			title : '销售分类',
			width : 100,
			sortable : true
		},{
			field : 'examinePerson',
			title : '审核人',
			width : 80,
			hidden : true
		},{
			field : 'examine',
			title : '审核情况',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			if(value=="未审核"){
				return '<font color="red">'+value+'</font>';
			}else{
				return value;
			}
			}
		},{
			field : 'invoice_reckoning',
			title : '是否转结算',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
				if(value=="否"){
					return '<font color="red">'+value+'</font>';
				}else{
					return value;
				}
			}
		},{
			field : 'xs_Car_Give_Up',
			title : '是否放弃购车',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
				if(value==null || value=="0"){
					return '否';
				}else{
					return '是';
				}
			}	
		},{
			field : 'xs_Car_Sel_Remark',
			title : '备注',
			width : 160,
			sortable : true
		}
		]],onDblClickRow : function(rowIndex, rowData){
			showSellInfor();
			
		},onClickRow : function(rowIndex,rowData){
	    	
	    }
	
	});
	
	
	//退车信息查询
	$('#datagrid_quitcar_manage_id').datagrid({
		url : 'quitCarManageAction!getQuitInfor.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		singleSelect : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName : 'exitCar_Code',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'exitCar_Id',
			title : '退车编号',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'exitCar_Code',
			title : '退车单号',
			width : 130,
			sortable : true
			
		},{
			field : 'exitCar_Date',
			title : '退车日期',
			width : 100,
			sortable : true
			
		},{
			field : 'exitCar_Person',
			title : '经办人',
			width : 100,
			sortable : true
			
		},{
			field : 'xs_Car_Sel_Id',
			title : '销售单号',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'sell_Code',
			title : '销售单号',
			width : 120,
			sortable : true
			
		},{
			field : 'out_Id',
			title : '出库单号',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'xs_Car_Sel_Date',
			title : '销售日期',
			width : 100,
			sortable : true
			
		},{
			field : 'xs_Custom_Name',
			title : '客户名称',
			width : 100,
			sortable : true
			
		},{
			field : 'xs_Car_Vin_Number',
			title : 'VIN编号',
			width : 100,
			sortable : true
		},{
			field : 'xs_Car_Ocn',
			title : 'OCN码',
			width : 100,
			sortable : true
			
		},{
			field : 'xs_Car_Id',
			title : '车辆编号',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'exitCar_Type',
			title : '退车分类',
			width : 80,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
				return rowData.exitCarTypeName;
			}
		},{
			field : 'exitCar_Check_Person',
			title : '审核人员',
			width : 100,
			sortable : true
		},{
			field : 'examine',
			title : '审核情况',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'examine_Name',
			title : '审核情况',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			if(value=="未审核"){
				return '<font color="red">'+value+'</font>';
			}else{
				return value;
			}
			}
			
		},{
			field : 'notice',
			title : '是否通知退款',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			if(value=='1'){
				return '已通知退款';
			}else{
				return '未通知退款';
			}
			}
		},{
			field : 'exitCar_Remark',
			title : '备注',
			width : 140
		}
		]],onClickRow : function(rowIndex,rowData){
	    	
	    }
	
	});
	
});
