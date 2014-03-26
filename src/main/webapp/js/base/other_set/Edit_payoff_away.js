$(function (){
	var $workValue = $('#payoff_away_id').datagrid('getSelected');
	if($workValue){
		$('form').form('load',{
			payoff_id : $workValue.id1,
			payoff_name : $workValue.id2,
			memo : $workValue.id3
		})
	}
});