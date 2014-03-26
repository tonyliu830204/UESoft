$(function (){
		var carArchives_add_customId = $('#carArchives_add_customId');
	carArchives_add_customId.combogrid({   
		fitColumns:true,
		pagination:true,
		rownumbers:true,
	    panelWidth:530,
	    panelHeight:280,
	    idField:'customId',   
	    textField:'customName',
	    mode:'remote',
		required:true,
		missingMessage:'客户名称为必选项',
		validType:'isSelected[\'#carArchives_add_customId\']',
		invalidMessage : '请从下拉框中选择客户名称',
	    url:projectPath+'basCarArchivesAction!customCombogrid.action',   
	    columns:[[   
	        {field:'customId',title:'客户编号',width:50},   
	        {field:'customName',title:'客户名称',width:60},   
	        {field:'customAddr',title:'地址',width:100},   
	        {field:'customTel1',title:'联系电话',width:60},   
	        {field:'customTel2',title:'固定电话',width:60},
	        {field:'customSex ',title:'性别',width:60,hidden:true},
	        {field:'customBirthday',title:'出生年月',width:60,hidden:true},
	        {field:'customIden',title:'身份证号',width:60,hidden:true}
	        
	    ]],
		onSelect:function(rec){
			 var customId = carArchives_add_customId.combo('getValue');
			 $.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'basCarArchivesAction!onSelectCustom.action',
				   data: 'customId=' + customId,
				   success: function(r){
					 if(r){
						 $('#carArchives_add_customId_view').val(r.customId);
						 $('#carArchivesAddForm input[name=customAddr]').val(r.customAddr);
						 $('#carArchivesAddForm input[name=customTel1]').val(r.customTel1);
						 $('#carArchivesAddForm input[name=customTel2]').val(r.customTel2);
						 $('#carArchives_add_carRealationSex').combobox('setValue',r.customSex);
						 $('#carArchivesAddForm input[name=carBirthday]').val(r.customBirthday);
						 $('#carArchivesAddForm input[name=carRealationIdentifyCd]').val(r.customIden);
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
	
	var grid = carArchives_add_customId.combogrid('grid');
	
	var pager = grid.datagrid('getPager');
	
	pager.pagination({
		buttons:[{
			iconCls:'icon-add',
			handler:function(){
				alertAddCustomDialog();
			}
		}]
	});
	
	carArchives_add_customId.combogrid('textbox').on('keyup', function (e){
		if(e.keyCode == 8){
			 $('#carArchives_add_customId_view').val('');
			 $('#carArchivesAddForm input[name=customAddr]').val('');
			 $('#carArchivesAddForm input[name=customTel1]').val('');
			 $('#carArchivesAddForm input[name=customTel2]').val('');
		}
	});
	
	/*grid.datagrid({
		onLoadSuccess : function (data){
			if(carArchives_add_customId.combogrid('getValue') != '' && data.total == 0){
				carArchives_add_customId.combogrid('textbox').on('keyup', function (e){
					if(e.keyCode == 13){
						alertAddCustomDialog();
					}
				});
			}
		}
	});*/
	
	var addCustomDialog;
	
	var alertAddCustomDialog = function (){
		carArchives_add_customId.combogrid('hidePanel');
		if(addCustomDialog == undefined){
			addCustomDialog = $('<div/>').dialog({
				modal:true,
				closable : true,
				title : '客户档案新增',
				width : 800,
				height : 380,
				href : projectPath+'frt/frtResevation/details/addCustomArchives.jsp',
				buttons : [{
					iconCls : 'icon-save',
					text : '保存',
					handler : function (){
						if($('#customArchivesAddForm').form('validate')){
							$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url: 'frtCustomAction!save.action',
							   data: $('#customArchivesAddForm').serialize(),
							   success: function(r){
								 if(r.success){
									 carArchives_add_customId.combogrid('textbox').focus();
									 carArchives_add_customId.combogrid('showPanel');
									 //var q = carArchives_add_customId.combogrid('getText');
									 //grid.datagrid('load',$.extend({},carArchives_add_customId.queryParams,{q:q}));
									 carArchives_add_customId.combogrid('grid').datagrid('reload');
									 carArchives_add_customId.combogrid('setValue', r.obj);
									 $('#carArchives_add_customId_view').val(r.obj);
									 $('#carArchives_add_customTel1').val($('#customArchives_add_customTel1').val());
									 $('#carArchives_add_customTel2').val($('#customArchives_add_customTel2').val());
									 $('#carArchives_add_customAddr').val($('#customArchives_add_customAddr').val());
									 $('#carArchives_add_carRealationSex').combobox('setValue',$('#customArchives_add_customSex').val());
									 $('#carArchives_add_carBirthday').val($('#customArchives_add_customBirthday').val());
									 $('#carArchives_add_carRealationIdentifyCd').val($('#customArchives_add_customIden').val());
									 addCustomDialog.dialog('close');
									 carArchives_add_customId.combogrid('hidePanel');
									 var e = jQuery.Event("keydown");//模拟一个键盘事件
									 e.keyCode = 13;//keyCode=13是回车 
									 carArchives_add_customId.combogrid('textbox').trigger(e);//模拟页码框按下回车
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
						}
					}
		        },{
		        	iconCls : 'icon-undo',
					text : '取消',
					handler : function (){
						addCustomDialog.dialog('close');
					}
		        }],
		        onLoad : function (){
		        	$('#customArchives_add_customName').val($('#carArchives_add_customId').combogrid('getText'));
		        	$('#customArchives_add_customJm').val(makePy($('#customArchives_add_customName').val()));
		        },
		        onClose : function (){
			    	$(this).dialog('destroy');
			    	addCustomDialog = undefined;
			    }
			});
		}
	}
});
//判断Vin号是否存在
function isExistsVin(){
	$.ajax({
				type : 'post',
				dataType : 'json',
				url: 'basCarArchivesAction!isExistsVin.action',
				data : "carVin="+$('#addcarVin').val(),
				success : function(r) {
					if (r.success) {
						alertMsg("VIN号已存在！请重新输入！","warning");
					}
				},
				error : function (r){
				   if(r.readyState == '0' && r.status == '0'){
					   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
				   }
			   	}
			});
}