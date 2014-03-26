$(function() {
		//工单综合查询->维修项目
		$frtResevationRepairArchivesDatagrid = $('#frtResevationRepairArchivesDatagrid');

		$frtResevationRepairArchivesDatagrid.treegrid({
			url : '',
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			singleSelect : true,
			fit : true,
			idField : 'id',
			treeField : 'receptionId',
			columns : [ [ {
				field : 'receptionId',
				title : '工单号',
				width : 150,
				sortable : true
			}, {
				field : 'propRepPer',
				title : '托修人',
				width : 60,
				sortable : true
			}, {
				field : 'propPhone',
				title : '托修人手机',
				width : 90,
				sortable : true
			}, {
				field : 'propTel',
				title : '托修人电话',
				width : 90,
				sortable : true
			}, {
				field : 'repitemId',
				title : '项目编号',
				width : 60,
				sortable : true
			}, {
				field : 'rcptitemName',
				title : '项目名称',
				width : 100,
				sortable : true
			}, {
				field : 'rcptitemTime',
				title : '所需工时',
				width : 60,
				sortable : true
			}, {
				field : 'rcptitemInnerTime',
				title : '内部工时',
				width : 60,
				sortable : true
			}, {
				field : 'rcptitemAmout',
				title : '金额',
				width : 60,
				sortable : true
			}, {
				field : 'stfId',
				title : '维修员编号',
				width : 60,
				sortable : true
			}, {
				field : 'planStartTime',
				title : '计划开工时间',
				width : 140,
				sortable : true
			}, {
				field : 'planComplTime',
				title : '计划完工时间',
				width : 140,
				sortable : true
			}, {
				field : 'charge',
				title : '收费性质',
				width : 60,
				sortable : true
			}, {
				field : 'claimsType',
				title : '索赔分类',
				width : 60,
				sortable : true
			}, {
				field : 'receptionRemark',
				title : '备注',
				width : 60,
				sortable : true
			} ] ]
		});
	});