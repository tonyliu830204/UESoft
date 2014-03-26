$(function(){
	var $rowValue = $('#customer_classification_id').datagrid('getSelected');
	if($rowValue){
		$('form').form('load',{
			type_id:$rowValue.id1,
			type_name:$rowValue.id2,
			type_memo:$rowValue.id3
			});
		}
});