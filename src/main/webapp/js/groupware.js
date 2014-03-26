/*限制用户输入的字符数
 * id为组件id
 * size为最大字符数
 * */
function showMaxLength(id,size){
	var inputId=document.getElementById(id);
	var data=inputId.value;
	if(data.length>=size){
		data=data.substr(0,size);
		inputId.value=data;
	}
}

/**
 * 判断里程数是否合理
 **/
function checkoutDistance(id,id2){
	var carId=$('#'+id2).combobox('getValue');
	if(carId==null||carId.length==0){
	 	alertMsg('对不起，请先选择车牌照！', 'warning');
	 	return;
	}
	$.ajax({
		type : 'post',
		url : 'baseAction!loadRepairDistance.action',
		dataType : 'json',
		success : function(r) {
		     if(r.ciValue=='checked'){
		    	 var value=document.getElementById(id).value;
		    		$.ajax({
		    			type : 'post',
		    			url : 'frtReceptionAction!findCarDistance.action?carId='+carId,
		    			dataType : 'json',
		    			success : function(r) {
		    				 if(r.success){
		    				 	if(value<=r.obj){
		    				 		document.getElementById(id).value=r.obj+1;
		    				 		//$("#"+id).focus();	
		    				 		$.messager.alert("优亿软件提示",'对不起，本次里程数不能小于上次里程数！','warning',function(){
		    				 			$("#"+id).focus();			 			
		    				 			document.getElementById(id).value=r.obj+1;
		    				 		});
		    				 	}
		    				 }
		    			}
		    		});
		     }
		}
	});
}

/**
 * 前台数据操作增加方法（项目或配件）
 * selectedId:弹出窗口中已选中项datagrid的Id
 * mainId:操作窗口datagrid的Id
 * staticParam:当前页面全局变量
 */
var prosceniumAdd = function (selectedId,mainId,staticParam){
	endEdit($('#'+selectedId+''));
	var effectRow = $('#'+selectedId+'').datagrid('getRows');
	for ( var i = 0; i < effectRow.length; i++) {
		$('#'+mainId+'').datagrid('appendRow',effectRow[i]);
	}
	staticParam=$('#'+mainId+'').datagrid('getData');
	return staticParam;
}

var prosceniumAdd1 = function (selectedId,mainId,staticParam){
	endEdit($('#'+selectedId+''));
	var effectRow = $('#'+selectedId+'').datagrid('getRows');
	for ( var i = 0; i < effectRow.length; i++) {
		var flg = true;
		var effectRow_ = $('#'+mainId+'').datagrid('getRows');
		for(var j = 0; j < effectRow_.length; j++){
			if(effectRow[i].partsId == effectRow_[j].partsId){
				flg = false;
				break;
			}
		}
		if(flg)
		    $('#'+mainId+'').datagrid('appendRow',effectRow[i]);
	}
	staticParam=$('#'+mainId+'').datagrid('getData');
	return staticParam;
}

var prosceniumAdd2 = function (selectedId,mainId,staticParam){
	endEdit($('#'+selectedId+''));
	var effectRow = $('#'+selectedId+'').datagrid('getRows');
	for ( var i = 0; i < effectRow.length; i++) {
		var flg = true;
		var effectRow_ = $('#'+mainId+'').datagrid('getRows');
		for(var j = 0; j < effectRow_.length; j++){
			if(effectRow[i].itemId == effectRow_[j].itemId){
				flg = false;
				break;
			}
		}
		if(flg)
		    $('#'+mainId+'').datagrid('appendRow',effectRow[i]);
	}
	staticParam=$('#'+mainId+'').datagrid('getData');
	return staticParam;
}

/**
 * 前台数据操作删除方法（项目或配件）
 * mainId:操作窗口datagrid的Id
 * row:要删除的行数据
 * staticParam:当前页面全局变量
 */
var prosceniumDelete = function (mainId,row,staticParam){
	var index =$('#'+mainId+'').datagrid('getRowIndex', row);
	$('#'+mainId+'').datagrid('deleteRow', index);
	var effectRow = $('#'+mainId+'').datagrid('getData');
	$('#'+mainId+'').datagrid('loadData',effectRow);
	staticParam=$('#'+mainId+'').datagrid('getData');
	return staticParam;
}
/**
 * 前台数据操作更改方法（项目或配件）
 * mainId:操作窗口datagrid的Id
 * staticParam:当前页面全局变量
 */
var prosceniumUpdate = function (mainId,staticParam){
	endEdit($('#'+mainId+''));
	var effectRow = $('#'+mainId+'').datagrid('getData');
	//$('#'+mainId+'').datagrid('loadData',effectRow);
	staticParam=$('#'+mainId+'').datagrid('getData');
	return staticParam;
}
/**
 * 校验datagrid数据是否完整
 * selectedId:弹出窗口中已选中项datagrid的Id
 * */
var validateDatagrid=function(selectedId){
	var tag=true;
	var rows=$('#'+selectedId+'').datagrid('getRows');
	for ( var i = 0; i < rows.length; i++) {
		var flag=$('#'+selectedId+'').datagrid('validateRow',i);
		if(!flag){
			tag=false;
			break;
		}
	}
	return tag;
}
/**
 * 设置多选下拉列表值
 * id:多选下拉列表的id
 * source:原始值
 */
var setComboboxValues = function (id,source){
	var value = new Array();
	if(source!=null&&source.length>0){
		value = source.split(',');
    }
	$('#'+id+'').combobox('setValues', value);
}
/**
 * 查找默认维修类别
 * 
 */
function findDefaultReceptionClass(id){
	$.ajax({
	   type: 'post',
	   dataType: 'json',
	   url: 'frtOptionsAction!findDefaultReceptionClass.action',
	   success: function(r){
	     	$('#'+id).combobox('setValue', r);
	   }
	});
}
/**
 * 控制指定元素隐藏与显示
 * flag:true为显示，false为隐藏
 * id:指定元素Id
 * */
var blockOrNone=function(flag,id){
	if(flag!=null&&flag==true){
		document.getElementById(id).style.display="block";
	}else{
		document.getElementById(id).style.display="none";
	}
}
/**
 * 获取指定行的索引
 * id:要操作的datagrid ID
 * rowData:选中的行数据
 * */
var findSelectRowIndex=function(id,rowData){
	return $('#'+id+'').datagrid('getRowIndex',rowData);
}
/**
 * 选中指定行
 * datagrid需重载
 * id:要操作的datagrid ID
 * index:要选中的行索引
 **/
var setSelectRow=function(id,index){
	$('#'+id+'').datagrid({
		onLoadSuccess:function(rowData){
			var rows=$('#'+id+'').datagrid('getRows');
			if((rows.length-1)>=index){
				$('#'+id+'').datagrid('selectRow',index);				
			}else{
				$('#'+id+'').datagrid('selectRow',rows.length-1);
			}
		}
	});
}
/**
 * 查询锁定
 * */
var startQuery=function(){
	var obj = $('<div/>').dialog({
		title:"",
		modal:true,
		closable:false,
		width : 180,
		height : 40,
		href : analyseImagePath+'/queryLock.jsp',
        onClose : function (){
	    	$(this).dialog('close');
	    }
	});
	return obj;
}
/**
 * 解除锁定
 * obj 指定的dailog对象
 * */
var endQuery=function(obj){
	obj.dialog('close');
}
/**
 * 提示：图片加载中
 * id 提示图片展示区域id
 * id2 分析图片展示区域id
 * */
var analyseLoader=function(id,id2){
	analyseLoaderHidden(id2,id);
	document.getElementById(id).innerHTML="<image src=\""+analyseImagePath+"/images/analyseLoading.jpg\" />";
}
/**
 * 隐藏图片加载中
 * id 提示图片展示区域id
 * id2 分析图片展示区域id
 * */
var analyseLoaderHidden=function(id,id2){
	document.getElementById(id).style.display="none";
	document.getElementById(id2).style.display="block";
}
/**
 * 动态显示输入字符数
 * id1 数据输入框id
 * id2 长度展示标签id
 * */
function dynamicShowLength(id1,id2){
	var dataLength=document.getElementById(id1).value.length;
	document.getElementById(id2).innerHTML=dataLength;
}
