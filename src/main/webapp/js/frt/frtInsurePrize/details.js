var  frtInsurePrize_carLicense=null;
var  frtInsurePrizeCount=0;
	function LoadOk() {
		if (document.readyState == "complete") {
			if(staticFrtInsurePrizeDisabled==false){
				findCarLicenseFormat("frtInsurePrize_details_carLicense");
				findDefaultReceptionClass('frtInsurePrize_details_reptId');
				requiredAsForm(false,'frtInsurePrizeAddForm');
				staticFrtInsurePrizeDisabled==true;
			}
			initFrame();
			addCarArchives();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame(){
		var data = $('#frtInsurePrizeSummaryDatagrid').datagrid('getSelected');
		if(data){
			$('#frtInsurePrizeAddForm').form('load', data);
		}
	}
	setTimeout("LoadOk();", 200);
	function addCarArchives() {
		
		$('#frtInsurePrize_details_carLicense').combobox('textbox').on('keyup', function(e)
        {
           if (e.keyCode == 13)
           {
        	   carLic();
           }
        });
        carLic = function(){
        	 var val1 = $('#frtInsurePrize_details_carLicense').combobox('getValue');
             var val2 = $('#frtInsurePrize_details_carLicense').combobox('getText');
             if(val1 == '' || val1 == val2){
      		  $.messager.confirm('系统提示', '您输入的车牌照不存在,是否进行车档案登记?', function(r){
          			if (r){
          				var d = $('<div/>').dialog({
          					href : projectPath+"frt/frtResevation/details/addCarArchives.jsp?carLicense="+val2,
          					modal:true,
          					closable : false,
          					title : '新增车辆档案',
          					width : 800,
          					height : 430,
          					buttons : [{
          						text : '确定',
          						handler : function (){
          							if($('#carArchivesAddForm').form('validate')){
          								$.ajax({
          									   type: 'post',
          									   dataType: 'json',
          									   url: 'basCarArchivesAction!save.action',
          									   data: $('#carArchivesAddForm').serialize(),
          									   success: function(r){
          										   if(r.success){
	          										   $('#frtInsurePrize_details_carLicense').combobox('reload','frtOptionsAction!findAllCarLicense.action');
	          										   $('#frtInsurePrize_details_carLicense').combobox('select', r.obj);
	          										   $('#frtInsurePrize_details_customId').combobox('reload','frtOptionsAction!findAllCustom.action');
            							     		   $('#frtInsurePrize_details_customId').combobox('select', $('#carArchives_add_customId_view').val());
            							     		    $('#frtInsurePrize_details_resvVin').combobox('reload','frtOptionsAction!findAllCarVin.action');
	          										   $('#frtInsurePrize_details_resvVin').combobox('select',$('#addcarVin').val());
          											    d.dialog('close');
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
          						text : '取消',
          						handler : function (){
          							d.dialog('close');
          						}
          					}],
          				    onLoad : function (){
          				    	$('#frtInsurePrize_details_carLicense').val($('#carLicenseview').val());
          			        	$('#carLicenseview').val(val2);
          			        },
          					onClose : function (){
          				    	d.dialog('destroy');
          				    }
          				});
          			}
          	   });
             }else{
          	   $.post('frtResevationAction!getFrtCar.action', 
                  	   { carId : val1 }, 
                  	   function (r){
								$('#frtInsurePrize_details_customId').combobox('setValue', r.customId);
								$('#frtInsurePrize_details_resvVin').combobox('setValue',r.carVin);
								$('input[name=resvFixpel]').val(r.carRelationPerson);
								$('input[name=resvFixtel]').val(r.carRelationTel1);
								$('input[name=resvFixphone]').val(r.carRelationTel2);
		                   },
		                   'json'
		           );
	         }
        }
        //新增客户档案
		$('#frtInsurePrize_details_customId').combobox('textbox').on('keyup', function(e)
        {
              if (e.keyCode == 13)
              {
               var val1 = $('#frtInsurePrize_details_customId').combobox('getValue');
               var val2 = $('#frtInsurePrize_details_customId').combobox('getText');
               if(val1 == '' || val1 == val2){
           		   $.messager.confirm('系统提示', '您输入的客户名称不存在,是否新建客户档案?', function(r){
            			if (r){
            				var d = $('<div/>').dialog({
            					title: '新增客户档案',   
            					width : 800,
            					height : 380,
            				    cache: false,   
            				    href: projectPath+"frt/frtResevation/details/addCustomArchives.jsp?customId="+val2,   
            				    modal: true,
            				    onClose : function (){
            				    	$(this).dialog('destroy');
            				    },
            				    buttons:[{
            						text:'新增',
            						iconCls:'icon-add',
            						handler:function(){
            							$.ajax({
            							   type: 'post',
            							   dataType: 'json',
            							   url: 'frtCustomAction!saveCustom.action',
            							   data: $('#customArchivesAddForm').serialize()+"&carId="+$('#frtInsurePrize_details_carLicense').combobox('getValue'),
            							   success: function(r){
            							     	if(r.success){
            							     	    $('#frtInsurePrize_details_customId').combobox('reload','frtOptionsAction!findAllCustom.action');
            							     		$('#frtInsurePrize_details_customId').combobox('select', r.obj);
            							     		d.dialog('close');
            							     	}else{
            							     		alertMsg(r);
            							     	}
            							   }
            							});
            						}
            				    },{
            						text:'取消',
            						iconCls:'icon-cancel',
            						handler:function(){
            							d.dialog('close');
            						}
            				    }],
            				    onLoad : function (){
            			        	$('#customArchives_add_customName').val(val2);
            			        	$('#customArchives_add_customJm').val(makePy(val2));
            			        }
            				});
            			}
            	   });
               }else{
		           $.post('basCarArchivesAction!findFrtCarByCustomId.action', 
	                 	   { customId : val1 }, 
	                 	   function (r){
	                 	   		$('#frtInsurePrize_details_carLicense').combobox('select', r.carId);
								$('#frtInsurePrize_details_customId').combobox('select', r.frtCustom.customId);
								$('#frtInsurePrize_details_resvVin').combobox('setValue',r.carVin);
								$('input[name=resvFixpel]').val(r.carRelationPerson);
								$('input[name=resvFixtel]').val(r.carRelationTel1);
								$('input[name=resvFixphone]').val(r.carRelationTel2);
		                   },
		                   'json'
		           );
		           
               }
              }
        });
		//显示VIN号关联信息
		$('#frtInsurePrize_details_resvVin').combobox('textbox').on('keyup', function(e)
        {
              if (e.keyCode == 13)
              {
	               var val1 = $('#frtInsurePrize_details_resvVin').combobox('getText');
	              	$.post('basCarArchivesAction!findFrtCarByVIN.action', 
	                 	   { carVin : val1 }, 
	                 	   function (r){
	                 	   		$('#frtInsurePrize_details_carLicense').combobox('select', r.carId);
								$('#frtInsurePrize_details_customId').combobox('select', r.frtCustom.customId);
								$('input[name=carVin]').val(r.carVin);
								$('input[name=resvFixpel]').val(r.carRelationPerson);
								$('input[name=resvFixtel]').val(r.carRelationTel1);
								$('input[name=resvFixphone]').val(r.carRelationTel2);
		                   },
		                   'json'
		           );
              }
        });
		/*************************/
	}