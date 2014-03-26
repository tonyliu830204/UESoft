
//键盘事件
function getCode(id1,id2){
	//生成简码
  var obj = id1.val();
  id2.val(makePy(obj));
}




function doFormSubmit(datagrId,formId,url1,url2,wId){
	var bool = formId.form('validate');
	if(bool){
		var form =  formId.form();
		var formvalue = serializeObject(form);
			$.ajax({
			type : 'POST',
			url : url1,
			data : formvalue,
		    dataType : 'json',
			success : function(r){
					datagrId.datagrid({url : url2});
					wId.dialog('close');
				}
		   });
	 }else{
	 	alertMsg('对不起，保存失败，请确认数据格式及内容是否正确！', 'warning');
	 }
}

//将datagrid中的选中数据load给form表单
function datagridLoadToForm(wid,formId,datagridId){
	var values = datagridId.datagrid('getSelections');
	
	if(values.length>0){
		wid.dialog('open');
		formId.form('load', values[0]);
		$('#fitCar_id').combobox('setValues', values[0].fitCar.split(','));
	}else{
		alertMsg('对不起，请先选中要操作的记录！', 'warning');
	}

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
	showEditDialog("Standard_project_man_hours_center_id",null,"Standard_project_man_hours_center_id_div","开始导出","导出配置",0,_callbackExcept);
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
	exportEsuyUIExcelFile(parentId,fieldNames,"标准项目工时"+getSystemTime());
}



