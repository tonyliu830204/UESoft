$(function (){
	var $workValue = $('#Judgment_word_id').datagrid('getSelected');
	if($workValue){
		$('form').form('load',{
			word_id : $workValue.id1,
			word_name : $workValue.id2,
			memo : $workValue.id3
		})
	}
});