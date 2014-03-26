var test = undefined;
		$(function(){
			$("#carLastMaintDate").val(getSystemTime);
			loadRemainder($("#carLastMaintDate2"),'basCompanyInformationSetAction!loadMaintence.action');
			$('#first_MaintDate').val(getSystemTime());
			loadRemainder($("#first_MaintDate2"),'basCompanyInformationSetAction!loadFirst.action');
			$('#car_Businsurance_Date').val(getSystemTime());
			loadRemainder($("#car_Businsurance_Date2"),'basCompanyInformationSetAction!loadInnsure.action');		
			$('#car_Basinsurance_Date').val(getSystemTime());
			loadRemainder($("#car_Basinsurance_Date2"),'basCompanyInformationSetAction!loadInnsure.action');		
			$('#tab_id').tabs("select",tag);
			// 保养提醒
			$('#baoyang_rember_id').datagrid({
				url : 'customerCareAction!getBytixing.action',//
				pagination : true,
				fit : true,
				rownumbers : true,
				newrap : false,
				striped : true,
				singleSelect : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				fitColumns : false, // 自适应列宽 CarId
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ {
					field : 'car_Id',
					title : '车辆id',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'car_License',
					title : '车辆牌照',
					width : 100,
					sortable : true
				}, {
					field : 'ctype_Name_id',
					title : '车品牌编号',
					hidden : true,
				}, {
					field : 'ctype_Name',
					title : '车品牌',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Cstl_id',
					title : '车型号编号',
					hidden : true,
				}, {
					field : 'car_Cstl_Name',
					title : '车型号',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Vin',
					title : 'vin号',
					width : 100,
					sortable : true,
				}, {
					field : 'yjbydate',
					title : '预计保养日期',
					width : 100,
					sortable : true	
				}, {
					field : 'yjby_Distance',
					title : '预计保养里程',
					width : 100,
					sortable : true				
				}, {
					field : 'custom_Name',
					title : '客户名称',
					width : 100,
					sortable : true				
				}, {
					field : 'custom_tel1',
					title : '车主手机',
					width : 100,
					sortable : true
				}, {
					field : 'custom_tel2',
					title : '车主固话',
					width : 100,
					sortable : true
				}, {
					field : 'car_Relation_Person',
					title : '客户联系人',
					width : 100,
					sortable : true
				}, {
					field : 'car_Relation_Person',
					title : '驾驶姓名',
					width : 100
				}, {
					field : 'prop_Tel',
					title : '托修固话',
					width : 100,
					sortable : true
				}, {
					field : 'prop_Phone',
					title : '托修手机',
					width : 100,
					sortable : true
				}, {
					field : 'custom_Name_Addr',
					title : '联系地址',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Maint_Date',
					title : '最近保养日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Maint_Distance',
					title : '最近保养里程',
					width : 100,
					sortable : true
				}, {
					field : 'by_Number',
					title : '保养次数',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Date',
					title : '最近维修日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Distance',
					title : '最近维修里程',
					width : 100,
					sortable : true
				}, {
					field : 'car_Repair_Cnt',
					title : '维修次数',
					width : 100,
					sortable : true
				}, {
					field : 'car_Distance_Per_Day',
					title : '日均里程',
					width : 100,
					sortable : true
				}, {
					field : 'wlcDays',
					title : '未来厂天数',
					width : 100,
					sortable : true
				}, {
					field : 'receptor',
					title : '最近接待员',
					width : 100,
					sortable : true
				}
				] ]
				

			});
			// 年检年审提醒
			$('#nianjian_nianshen_id').datagrid( {
				url : 'customerCareAction!getBytixing.action',
				pagination : true,
				fit : true,
				rownumbers : true,
				newrap : false,
				striped : true,
				singleSelect : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				fitColumns : false, // 自适应列宽 CarId
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ {
					field : 'car_Id',
					title : '车辆id',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'car_License',
					title : '车辆牌照',
					width : 100,
					sortable : true
				}, {
					field : 'ctype_Name_id',
					title : '车品牌编号',
					hidden : true,
				}, {
					field : 'ctype_Name',
					title : '车品牌',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Cstl_id',
					title : '车型号编号',
					hidden : true,
				}, {
					field : 'car_Cstl_Name',
					title : '车型号',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Vin',
					title : 'vin号',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Examined_Date',
					title : '年审到期日',
					width : 100,
					sortable : true	
				}, {
					field : 'car_Annual_Date',
					title : '年检到期日',
					width : 100,
					sortable : true				
				}, {
					field : 'custom_Name',
					title : '客户名称',
					width : 100,
					sortable : true				
				}, {
					field : 'custom_tel1',
					title : '车主手机',
					width : 100,
					sortable : true
				}, {
					field : 'custom_tel2',
					title : '车主固话',
					width : 100,
					sortable : true
				}, {
					field : 'car_Relation_Person',
					title : '客户联系人',
					width : 100,
					sortable : true
				}, {
					field : 'car_Relation_Person',
					title : '驾驶姓名',
					width : 100
				}, {
					field : 'prop_Tel',
					title : '托修固话',
					width : 100,
					sortable : true
				}, {
					field : 'prop_Phone',
					title : '托修手机',
					width : 100,
					sortable : true
				}, {
					field : 'custom_Name_Addr',
					title : '联系地址',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Maint_Date',
					title : '最近保养日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Maint_Distance',
					title : '最近保养里程',
					width : 100,
					sortable : true
				}, {
					field : 'by_Number',
					title : '保养次数',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Date',
					title : '最近维修日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Distance',
					title : '最近维修里程',
					width : 100,
					sortable : true
				}, {
					field : 'car_Repair_Cnt',
					title : '维修次数',
					width : 100,
					sortable : true
				}, {
					field : 'car_Distance_Per_Day',
					title : '日均里程',
					width : 100,
					sortable : true
				}, {
					field : 'wlcDays',
					title : '未来厂天数',
					width : 100,
					sortable : true
				}, {
					field : 'receptor',
					title : '最近接待员',
					width : 100,
					sortable : true
				}
				] ]
				
			});
			// 首保提醒  
			$('#customer_care_shoubao_tixing_id').datagrid( {
				url : 'customerCareAction!getBytixing.action',
				pagination : true,
				fit : true,
				rownumbers : true,
				newrap : false,
				striped : true,
				singleSelect : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				fitColumns : false, // 自适应列宽 CarId
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ {
					field : 'car_Id',
					title : '车辆id',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'car_License',
					title : '车辆牌照',
					width : 100,
					sortable : true
				}, {
					field : 'ctype_Name_id',
					title : '车品牌编号',
					hidden : true,
				}, {
					field : 'ctype_Name',
					title : '车品牌',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Cstl_id',
					title : '车型号编号',
					hidden : true,
				}, {
					field : 'car_Cstl_Name',
					title : '车型号',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Vin',
					title : 'vin号',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Buy_Date',
					title : '销售日期',
					width : 100,
					sortable : true	
				}, {
					field : 'car_Fst_Insurance_Date',
					title : '预计首保日期',
					width : 100,
					sortable : true				
				}, {
					field : 'custom_Name',
					title : '客户名称',
					width : 100,
					sortable : true				
				}, {
					field : 'custom_tel1',
					title : '车主手机',
					width : 100,
					sortable : true
				}, {
					field : 'custom_tel2',
					title : '车主固话',
					width : 100,
					sortable : true
				}, {
					field : 'car_Relation_Person',
					title : '客户联系人',
					width : 100,
					sortable : true
				}, {
					field : 'car_Relation_Person',
					title : '驾驶姓名',
					width : 100
				}, {
					field : 'prop_Tel',
					title : '托修固话',
					width : 100,
					sortable : true
				}, {
					field : 'prop_Phone',
					title : '托修手机',
					width : 100,
					sortable : true
				}, {
					field : 'custom_Name_Addr',
					title : '联系地址',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Maint_Date',
					title : '最近保养日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Maint_Distance',
					title : '最近保养里程',
					width : 100,
					sortable : true
				}, {
					field : 'by_Number',
					title : '保养次数',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Date',
					title : '最近维修日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Distance',
					title : '最近维修里程',
					width : 100,
					sortable : true
				}, {
					field : 'car_Repair_Cnt',
					title : '维修次数',
					width : 100,
					sortable : true
				}, {
					field : 'car_Distance_Per_Day',
					title : '日均里程',
					width : 100,
					sortable : true
				}, {
					field : 'wlcDays',
					title : '未来厂天数',
					width : 100,
					sortable : true
				}, {
					field : 'receptor',
					title : '最近接待员',
					width : 100,
					sortable : true
				}
				] ]	
			});
			//保险/交强提醒
			$('#baoxian_jiaoqiang_id').datagrid( {
				url : 'customerCareAction!getBytixing.action',
				pagination : true,
				fit : true,
				rownumbers : true,
				newrap : false,
				striped : true,
				singleSelect : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				fitColumns : false, // 自适应列宽 CarId
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ {
					field : 'car_Id',
					title : '车辆id',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'car_License',
					title : '车辆牌照',
					width : 100,
					sortable : true
				}, {
					field : 'ctype_Name_id',
					title : '车品牌编号',
					hidden : true,
				}, {
					field : 'ctype_Name',
					title : '车品牌',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Cstl_id',
					title : '车型号编号',
					hidden : true,
				}, {
					field : 'car_Cstl_Name',
					title : '车型号',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Vin',
					title : 'vin号',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Basinsurance_Date',
					title : '交强险到期',
					width : 100,
					sortable : true	
				}, {
					field : 'car_Businsurance_Date',
					title : '保险到期',
					width : 100,
					sortable : true				
				}, {
					field : 'relcampId',
					title : '交强险保险公司',
					width : 100,
					sortable : true				
				}, {
					field : 'busRelcamp',
					title : '商业险保险公司',
					width : 100,
					sortable : true				
				}, {
					field : 'custom_Name',
					title : '客户名称',
					width : 100,
					sortable : true				
				}, {
					field : 'custom_tel1',
					title : '车主手机',
					width : 100,
					sortable : true
				}, {
					field : 'custom_tel2',
					title : '车主固话',
					width : 100,
					sortable : true
				}, {
					field : 'car_Relation_Person',
					title : '客户联系人',
					width : 100,
					sortable : true
				}, {
					field : 'car_Relation_Person',
					title : '驾驶姓名',
					width : 100
				}, {
					field : 'prop_Tel',
					title : '托修固话',
					width : 100,
					sortable : true
				}, {
					field : 'prop_Phone',
					title : '托修手机',
					width : 100,
					sortable : true
				}, {
					field : 'custom_Name_Addr',
					title : '联系地址',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Maint_Date',
					title : '最近保养日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Maint_Distance',
					title : '最近保养里程',
					width : 100,
					sortable : true
				}, {
					field : 'by_Number',
					title : '保养次数',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Date',
					title : '最近维修日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Distance',
					title : '最近维修里程',
					width : 100,
					sortable : true
				}, {
					field : 'car_Repair_Cnt',
					title : '维修次数',
					width : 100,
					sortable : true
				}, {
					field : 'car_Distance_Per_Day',
					title : '日均里程',
					width : 100,
					sortable : true
				}, {
					field : 'wlcDays',
					title : '未来厂天数',
					width : 100,
					sortable : true
				}, {
					field : 'receptor',
					title : '最近接待员',
					width : 100,
					sortable : true
				}
				] ]	
			});
			// 生日提醒
			$('#birthday_tixing_id').datagrid( {
				url : 'customerCareAction!getBytixing.action',
				pagination : true,
				fit : true,
				rownumbers : true,
				newrap : false,
				striped : true,
				singleSelect : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				fitColumns : false, // 自适应列宽 CarId
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ {
					field : 'car_Id',
					title : '车辆id',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'car_License',
					title : '车辆牌照',
					width : 100,
					sortable : true
				}, {
					field : 'ctype_Name_id',
					title : '车品牌编号',
					hidden : true,
				}, {
					field : 'ctype_Name',
					title : '车品牌',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Cstl_id',
					title : '车型号编号',
					hidden : true,
				}, {
					field : 'car_Cstl_Name',
					title : '车型号',
					width : 100,
					sortable : true,
				}, {
					field : 'car_Vin',
					title : 'vin号',
					width : 100,
					sortable : true,
				}, {
					field : 'custom_Birthday',
					title : '客户生日',
					width : 100,
					sortable : true	
				}, {
					field : 'custom_Name',
					title : '客户名称',
					width : 100,
					sortable : true				
				}, {
					field : 'custom_tel1',
					title : '车主手机',
					width : 100,
					sortable : true
				}, {
					field : 'custom_tel2',
					title : '车主固话',
					width : 100,
					sortable : true
				}, {
					field : 'car_Relation_Person',
					title : '客户联系人',
					width : 100,
					sortable : true
				}, {
					field : 'car_Relation_Person',
					title : '驾驶姓名',
					width : 100
				}, {
					field : 'prop_Tel',
					title : '托修固话',
					width : 100,
					sortable : true
				}, {
					field : 'prop_Phone',
					title : '托修手机',
					width : 100,
					sortable : true
				}, {
					field : 'custom_Name_Addr',
					title : '联系地址',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Maint_Date',
					title : '最近保养日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Maint_Distance',
					title : '最近保养里程',
					width : 100,
					sortable : true
				}, {
					field : 'by_Number',
					title : '保养次数',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Date',
					title : '最近维修日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Distance',
					title : '最近维修里程',
					width : 100,
					sortable : true
				}, {
					field : 'car_Repair_Cnt',
					title : '维修次数',
					width : 100,
					sortable : true
				}, {
					field : 'car_Distance_Per_Day',
					title : '日均里程',
					width : 100,
					sortable : true
				}, {
					field : 'wlcDays',
					title : '未来厂天数',
					width : 100,
					sortable : true
				}, {
					field : 'receptor',
					title : '最近接待员',
					width : 100,
					sortable : true
				}
				] ]	
			});
			// 会员到期提醒
			$('#vip_tixing_id').datagrid( {

				url : '',
				pagination : true,
				fit : true,
				pageSize : 10,
				singleSelect : true,
				newrap : false,
				striped : true,
				rownumbers : true,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				fitColumns : false, // 自适应列宽
				idField : 'id',
				loadMsg : "数据加载中，请稍后……",
				frozenColumns : [ [ {
					field : 'carId',
					title : '车辆id',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'carLicense',
					title : '车牌照',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'ctypeName',
					title : '车品牌',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				} ] ],
				columns : [ [ {
					field : 'carCstlName',
					title : '车型号',
					width : 100,
					 
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'endTime',
					title : '会员到期',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true
						}
					}
				}, {
					field : 'customName',
					title : '客户名称',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'carRelationTel1',
					title : '车主手机',
					width : 100,
					sortable : true,
					editor : {
						type : 'numberbox',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'carRelationTel2',
					title : '车主固话',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'receptionRepPer',
					title : '客联系人',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'carRelationPerson',
					title : '驾驶姓名',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'propTel',
					title : '托修固话',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'propPhone',
					title : '托修手机',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'pareaName',
					title : '联系地址',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'carLastRepairDate',
					title : '最近维修日期',
					width : 100,
					sortable : true,
					editor : {
						type : 'datetime',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'carLastRepairDistance',
					title : '最近维修里程',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'carRepairCnt',
					title : '维修次数',
					width : 100,
					sortable : true,
					editor : {
						type : 'numberbox',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'carDistancePerDay',
					title : '日均里程',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'receptor',
					title : '最近接待员',
					width : 100,
					sortable : true,
					editor : {
						type : 'text',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}, {
					field : 'wlcDays',
					title : '未来厂天数',
					width : 100,
					sortable : true,
					editor : {
						type : 'numberbox',
						options : {
							required : true,
							min : 1.00,
							precision : 2,
							max : 100000000.00
						}
					}
				}

				] ]

			});
			//提醒查询center内容
			var $rember_find_id = $('#rember_find_id');
			$rember_find_id.datagrid( {
				url : 'customerCareAction!getRemenberSearch.action',
				pagination : true,
				fit : true,
				pageSize : 10,
				newrap : false,
				striped : true,
				singleSelect : true,
				rownumbers : true,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				fitColumns : false, // 自适应列宽
				idField : 'id',
				loadMsg : "数据加载中，请稍后……",
				columns : [ [{
					field : 'custom_Name',
					title : '客户名称',
					width : 100,
					sortable : true
				}, {
					field : 'car_License',
					title : '车辆牌照',
					width : 100,
					sortable : true
				}, {
					field : 'car_Cstl_id',
					title : '车型号',
					width : 100,
					sortable : true,
					formatter: function(value,row,index){
					return row.car_Cstl_Name;
					}
				}, {
					field : 'ctype_Name',
					title : '车品牌',
					width : 100,
					sortable : true,
					formatter: function(value,row,index){
					return row.ctype_Name_id;
					}
				}, {
					field : 'custom_tel1',
					title : '联系电话',
					width : 100,
					sortable : true
				}, {
					field : 'car_Buy_Date',
					title : '购车日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Vin',
					title : 'VIN号',
					width : 100,
					sortable : true
				}, {
					field : 'return_Visit_Date',
					title : '回访日期',
					width : 100,
					sortable : true
				}, {
					field : 'tx_Return_Visit_Date',
					title : '提醒回访日期',
					width : 100,
					sortable : true
				}, {
					field : 'group_Name',
					title : '回访类型',
					width : 100,
					sortable : true,
					formatter: function(value,row,index){
					return row.group_Name_value;
					}
				}, {
					field : 'visit_Content',
					title : '回访内容',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Date',
					title : '最近维修日期',
					width : 100,
					sortable : true
				}, {
					field : 'car_Last_Repair_Distance',
					title : '最近里程',
					width : 100,
					sortable : true
				}, {
					field : 'car_Repair_Cnt',
					title : '维修次数',
					width : 100,
					sortable : true
				}, {
					field : 'tx_Resault',
					title : '提醒结果',
					width : 100,
					sortable : true,
					formatter: function(value,row,index){
					return row.tx_Resault_value;
					}
				}, {
					field : 'car_lost',
					title : '流失原因',
					width : 100,
					sortable : true,
					formatter: function(value,row,index){
					return row.car_lost_value;
					}
				}, {
					field : 'wlcDays',
					title : '未来厂天数',
					width : 100,
					sortable : true
				}

				] ]

			});
			//提醒查询east内容
			var $rember_find_east_id = $('#rember_find_east_id');
			$rember_find_east_id.datagrid({
				url : 'customerCareAction!doResualt.action', //
				fit : true,
				fitColumns : true, // 自适应列宽
				newrap : false,
				striped : true,
				idField : 'id',
				loadMsg : "数据加载中，请稍后……",
				columns : [ [{
					field : 'tx_Resault',
					title : '结果分类',
					width : 200,
					sortable : true,
					formatter: function(value,row,index){
					return row.tx_Resault_value;
					}
				}, {
					field : 'car_sum',
					title : '数量',
					width : 150,
					sortable : true
				}, {
					field : 'car_percent',
					title : '比例',
					width : 150,
					sortable : true
				}
				] ]
			});
			
			
		});
		var id=null;
		var reminderType=null;
		//回访按钮点击事件
		function dbReturnButton(){
			var tab = $('#tab_id').tabs('getSelected');
			var index = $('#tab_id').tabs('getTabIndex',tab);
			if(index==0){
				//保养提醒
				id=$('#baoyang_rember_id');
				reminderType=maintenance;	
			}
			if(index==1){
				id=$('#nianjian_nianshen_id');
				reminderType=annual;
			}
			if(index==2){
				id=$('#customer_care_shoubao_tixing_id');
				reminderType=first;
			}
			if(index==3){
				id=$('#baoxian_jiaoqiang_id');
				reminderType=innsure;
			}
			if(index==4){
				id=$('#birthday_tixing_id');
				reminderType=birthr;
			}
			if(index==5){
				id=$('#rember_find_id');
				var data=id.datagrid('getSelected');
				reminderType=data.group_Name;
			}
			maintenanceRemain(id);
	}
		//保养提醒
		function maintenanceRemain(id){
			var data = id.datagrid('getSelected');
			if (data) {
				var d = $('<div/>').dialog({
									modal : true,
									closable : true,
									title : '增加',
									width : 850,
									height : 600,
									href : 'window_baoyangtixing.jsp?carId='+data.car_Id+'&group_Name='+reminderType,
									buttons : [
											{
												iconCls : 'icon-save',
												text : '保存',
												handler : function() {
													if ($('#form_baoyangtixing_id').form('validate')) {
														$.ajax({
															   type: 'post',
															   dataType: 'json',
															   url: 'customerCareAction!saveReminder.action',
															   data: $('#form_baoyangtixing_id').serialize(),
															   success: function(r){
																 if(r.success){
																	 //d.dialog('close');
																	 clearForm();
																	 $('#table_bytx_lishihuifangjilu_id').datagrid({
																			url : 'customerCareAction!getHistoricalVisit.action?car_Id='+data.car_Id+'&group_Name='+reminderType
																		});
																	 alertMsg(r);															
																 }else{
																 	alertMsg(r);
																 }
															   }
															});
													}
												}
											}, {
												iconCls : 'icon-undo',
												text : '取消',
												handler : function() {
													d.dialog('close');
												}
											} ],
									onClose : function() {
										$(this).dialog('destroy');
									},
									onLoad : function() {
										hideElement(reminderType);
										$('#form_baoyangtixing_id').form('load', data);
										$('#group_Name').val(reminderType);
										$('#txDate_id').val(getSystemTime());
										$('#return_Visit_Date_id').val(getSystemTime());
										$('#table_bytx_lishihuifangjilu_id').datagrid({
											url : 'customerCareAction!getHistoricalVisit.action?car_Id='+data.car_Id+'&group_Name='+reminderType
										});
									}
								});
			} else {
				alertMsg('对不起，请先选中要增加的记录！', 'warning');
			}
		}
		function clearForm(){
			$("#return_Visit_Date_id").val('');
			$("#visit_Content_id").val('');
			$('#txResault_id').combobox('setValue', '');
			$('#statusName_id').combobox('setValue', '');
			$("#g_Id").val('');
		}
	function 	hideElement(type){
		if(type==maintenance){
			$('#maintenance_id').show();
		    $("#annual").hide();
		    $("#fistRemain").hide();
		    $("#innsure").hide();
		    $("#birthdayId").hide();
		    $('#group_Name_value').val('保养提醒');
		}
		if(type==annual){
		    $("#annual").show();
		    $('#maintenance_id').hide();
		    $("#fistRemain").hide();
		    $("#innsure").hide();
		    $("#birthdayId").hide();
		    $('#group_Name_value').val('年检年审');
		}
	    if(type==first){
		    $("#fistRemain").show();
		    $('#maintenance_id').hide();
		    $("#annual").hide();
		    $("#innsure").hide();
		    $("#birthdayId").hide();
		    $('#group_Name_value').val('首保提醒');
		}
	    if(type==innsure){
		    $("#innsure").show();
		    $('#maintenance_id').hide();
		    $("#fistRemain").hide();
		    $("#annual").hide();
		    $("#birthdayId").hide();
		    $('#group_Name_value').val('保险提醒');
		}
	    if(type==birthr){
		    $("#birthdayId").show();
		    $('#maintenance_id').hide();
		    $("#fistRemain").hide();
		    $("#annual").hide();
		    $("#innsure").hide();
		    $('#group_Name_value').val('生日提醒');
		}
	}
function queryRemain(){
	var tab = $('#tab_id').tabs('getSelected');
	var index = $('#tab_id').tabs('getTabIndex',tab);
	if(index==0){
		$('#baoyang_rember_id').datagrid('unselectAll');
		$('#baoyang_rember_id').datagrid('load', serializeObject($('#maintenance_form_id')));
	}
	if(index==1){
		$('#nianjian_nianshen_id').datagrid('unselectAll');
		$('#nianjian_nianshen_id').datagrid('load', serializeObject($('#annual_form_id')));
		}
	if(index==2){
		$('#customer_care_shoubao_tixing_id').datagrid('unselectAll');
		$('#customer_care_shoubao_tixing_id').datagrid('load', serializeObject($('#first_form_id')));
	}
	if(index==3){
		$('#baoxian_jiaoqiang_id').datagrid('unselectAll');
		$('#baoxian_jiaoqiang_id').datagrid('load', serializeObject($('#innsure_form_id')));
	}
	if(index==4){
		$('#birthday_tixing_id').datagrid('unselectAll');
		$('#birthday_tixing_id').datagrid('load', serializeObject($('#customBirth_form_id')));
	}
	if(index==5){
		$('#rember_find_id').datagrid('unselectAll');
		$('#rember_find_id').datagrid('load', serializeObject($('#customcare_form_id')));
		$('#rember_find_east_id').datagrid('unselectAll');
		$('#rember_find_east_id').datagrid('load', serializeObject($('#customcare_form_id')));
	}
}

function  doClear(){
	var tab = $('#tab_id').tabs('getSelected');
	var index = $('#tab_id').tabs('getTabIndex',tab);
	if(index==0){
		$('#baoyang_rember_id').datagrid('unselectAll');
		$('#maintenance_form_id').form('clear');
		$('#baoyang_rember_id').datagrid('load', serializeObject($('#maintenance_form_id')));
	}
	if(index==1){
		$('#nianjian_nianshen_id').datagrid('unselectAll');
		$('#annual_form_id').form('clear');
		$('#nianjian_nianshen_id').datagrid('load', serializeObject($('#annual_form_id')));
		}
	if(index==2){
		$('#customer_care_shoubao_tixing_id').datagrid('unselectAll');
		$('#first_form_id').form('clear');
		$('#customer_care_shoubao_tixing_id').datagrid('load', serializeObject($('#first_form_id')));
	}
	if(index==3){
		$('#baoxian_jiaoqiang_id').datagrid('unselectAll');
		$('#innsure_form_id').form('clear');
		$('#baoxian_jiaoqiang_id').datagrid('load', serializeObject($('#innsure_form_id')));
	}
	if(index==4){
		$('#birthday_tixing_id').datagrid('unselectAll');
		$('#customBirth_form_id').form('clear');
		$('#birthday_tixing_id').datagrid('load', serializeObject($('#customBirth_form_id')));
	}
	if(index==5){
		$('#customcare_form_id').form('clear');
		$('#rember_find_id').datagrid('unselectAll');
		$('#rember_find_id').datagrid('load', serializeObject($('#customcare_form_id')));
		$('#rember_find_east_id').datagrid('unselectAll');
		$('#rember_find_east_id').datagrid('load', serializeObject($('#customcare_form_id')));
	}
}