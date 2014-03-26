$(function (){
	var $workValue = $('#department_set_id').datagrid('getSelected');
	if($workValue){
		$('form').form('load',{
			department_id : $workValue.id1,
			department_name : $workValue.id2,
			memo : $workValue.id3
		})
	}
});