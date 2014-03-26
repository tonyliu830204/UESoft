$(function (){
	var $Value = $('#Commonle_phrase_id').datagrid('getSelected');
	if($Value){
		$('form').form('load',{
			commonle_id : $Value.id1,
			commonle_name : $Value.id2,
			commonle_memo : $Value.id3
		})
	}
});