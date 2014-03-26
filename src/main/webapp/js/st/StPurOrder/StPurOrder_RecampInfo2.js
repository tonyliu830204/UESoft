//判断页面初始化加载是否完成
       function   LoadOk(){
	     if(document.readyState   =="complete")
		   initFrame();
		 else
		   setTimeout("LoadOk()",200);
	   }
       setTimeout("LoadOk()",200);
      
       //判断页面初始化加载完成    执行
	   function   initFrame() {
           var relcampName=$('#relcampName2').val();
           if(relcampName!=''){ 
                $('#relcampName22').val(relcampName);
	       $.ajax({
			    type : 'post',
			    url : 'StGoodsStorageAction!searchBasRelationCampanyByCondition.action',
			    data : $('#stpurorder_basrelationcampanydetailform').serialize(),
			    dataType : 'json',
			    success : function(r){
	               $('#stpurorder_basrelationcampanydetailtable').datagrid('loadData',r);
  	            }
             });
           }
	   }
	  
        $(function(){
	  //采购单明细：供应商
	  $('#stpurorder_basrelationcampanydetailtable').datagrid({
		 url:'StGoodsStorageAction!loadBasRelationCampany.action',
		 pagination:true,
	     fit:true,
	     singleSelect:true,
	     sortOrder:'asc',
	     sortName:'relcampId',
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true,
		 rownumbers:true,
		 idField : 'relcampId',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [[ {title : '编号',field : 'relcampId',sortable:true,width : 50
			}, {title : '供应商',field : 'relcampName',sortable:true,width : 100
			}, {title : '联系电话',field : 'relcampTel1',sortable:true,width : 100
			}, {title : '联系人',field : 'relcampContact',sortable:true,width : 100
			}
	        ]],onDblClickRow:function(rowIndex, rowData){    
			     var selections = $('#stpurorder_basrelationcampanydetailtable').datagrid('getSelections');
				 if (selections != null) {
					$('#relcampId').val(selections[0].relcampId);
					$('#relcampName2').val(selections[0].relcampName);
					$('#relcampTel1').val(selections[0].relcampTel1);
					$('#relcampContact').val(selections[0].relcampContact);
					d3.dialog('close');
					$('#stpurorder_basrelationcampanydetailform').form('clear');
				 }
	        }
	   });
	})	
					  
	function _query()
	{
		    $.ajax({
				type : 'post',
				url : 'StGoodsStorageAction!searchBasRelationCampanyByCondition.action',
				data : $('#stpurorder_basrelationcampanydetailform').serialize(),
				dataType : 'json',
				success : function(r){
		              $('#stpurorder_basrelationcampanydetailtable').datagrid('loadData',r);
				}
	     });
	} 	 
						  
	function _clear()
	{
	   $('#stpurorder_basrelationcampanydetailform').form('clear');
	   $('#stpurorder_basrelationcampanydetailtable').datagrid('load');
	}