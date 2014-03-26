$(function(){
	var $rowValue = $customer_visit_project_id.datagrid('getSelections')[0];
	if($rowValue){
		$('form').form('load',{
			project_id:$rowValue.id1,
			project_name:$rowValue.id2,
			project_memo:$rowValue.id3
			});
		}
});