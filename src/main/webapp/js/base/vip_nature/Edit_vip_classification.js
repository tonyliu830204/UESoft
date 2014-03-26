$(function (){
	var $vipTypeValue = $('#vip_classification_id').datagrid('getSelected');
	if($vipTypeValue){
		$('form').form('load',{
			vip_id : $vipTypeValue.id1,
			vip_name : $vipTypeValue.id2,
			vip_score : $vipTypeValue.id4,
			vip_work : $vipTypeValue.id3
		})
	}
});