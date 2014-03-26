$(function (){
	var $workValue = $('#work_score_regulation_id').datagrid('getSelected');
	if($workValue){
		$('form').form('load',{
			work_id : $workValue.id1,
			work_startcount : $workValue.id2,
			work_addscore : $workValue.id4,
			work_type : $workValue.id5,
			work_endcount : $workValue.id3
		})
	}
});