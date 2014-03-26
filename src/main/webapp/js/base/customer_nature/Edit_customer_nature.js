$(function(){
	var $rowValue = $('#customer_nature_id').datagrid('getSelected');
	if($rowValue){
		$('form').form('load',{
			nature_id:$rowValue.id1,
			nature_name:$rowValue.id2,
			nature_memo:$rowValue.id3
			});
		}
});