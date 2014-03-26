$(function (){
	var $workValue = $('#vip_work_discount_id').datagrid('getSelected');
	if($workValue){
		$('form').form('load',{
			discount_id : $workValue.id1,
			vip_type : $workValue.id2,
			work_type : $workValue.id3,
			discount_score : $workValue.id4
		})
	}
});