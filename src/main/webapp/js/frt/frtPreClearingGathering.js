function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
			$('#frtReceptionAdviceDatagrid').datagrid({
				url : 'frtReceptionAction!findFrtResvAdviceByCarId.action?carId=-1'
			});
	}
	setTimeout("LoadOk();", 200);
	$(function (){
		//前台接车单->维修建议
		$frtReceptionAdviceDatagrid = $('#frtReceptionAdviceDatagrid');
		$frtReceptionAdviceDatagrid.datagrid({
			url : '',
			singleSelect : true,
			pagination : true,
			fit : true,
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			columns : [[{
				field : 'carId',
				title : '收款单号',
				hidden : true,
				sortable:true,
				width : 70
			},{
				field : 'carLicense',
				title : '收款日期',
				sortable:true,
				width : 70
			},{
				field : 'adviceTime',
				title : '收款经办',
				sortable:true,
				width : 70
			},{
				field : 'writePerson',
				title : '票据类型',
				width : 70,
				sortable:true,
				formatter: function(value,row,index){
					return row.writePersonName;
				}
			},{
				field : 'customName',
				title : '付款方式',
				sortable:true,
				width : 70
			},{
				field : 'adviceClass',
				title : '结算单',
				sortable:true,
				width : 70,
				formatter: function(value,row,index){
					return row.adviceClassName;
				}
			},{
				field : 'adviceContext',
				title : '结算金额',
				width : 70
			},{
				field : 'procesState',
				title : '累计收款',
				width : 70,
				sortable:true,
				formatter: function(value,row,index){
					return row.procesStateName;
				}
			},{
				field : 'procesContext',
				title : '本次收款',
				width : 70
			},{
				field : 'adviceTimeEnd',
				title : '性质',
				sortable:true,
				width : 70
			},{
				field : 'procesProson',
				title : '余额',
				width : 70,
				sortable:true,
				formatter: function(value,row,index){
					return row.procesProsonName;
				}
			},{
				field : 'procesContext',
				title : '备注',
				width : 70
			},{
				field : 'procesContext',
				title : '车辆牌照',
				width : 70
			},{
				field : 'procesContext',
				title : '客户名称',
				width : 70
			},{
				field : 'procesContext',
				title : '未结清',
				width : 70
			},{
				field : 'procesContext',
				title : '消费积分',
				width : 70
			},{
				field : 'procesContext',
				title : '换金积分',
				width : 70
			},{
				field : 'procesContext',
				title : '消费项目',
				width : 70
			},{
				field : 'procesContext',
				title : '以前累计',
				width : 70
			},{
				field : 'procesContext',
				title : '预付款抵扣',
				width : 70
			}]]
			   ,onDblClickRow : function (rowIndex, rowData){
			}
		});
	});