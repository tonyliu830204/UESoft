$(function (){
	var $Value = $('#Score_present_set_id').datagrid('getSelected');
	if($Value){
		$('form').form('load',{
			present_id : $Value.id1,
			present_name : $Value.id2,
			present_memo : $Value.id3
		})
	}
});