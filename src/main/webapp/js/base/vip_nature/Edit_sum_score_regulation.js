$(function (){
	var $sumValue = $('#Sum_score_regulation_center_id').datagrid('getSelections')[0];
	if($sumValue){
		$('form').form('load',{
			sum_id : $sumValue.id1,
			sum_startcount : $sumValue.id2,
			sum_addscore : $sumValue.id4,
			sum_addcount : $sumValue.id5,
			sum_type : $sumValue.id6,
			sum_endcount : $sumValue.id3
		})
	}
});