$(function (){
	var $Insurance_Value = $('#Insurance_coverage_id').datagrid('getSelected');
	if($Insurance_Value){
		$('form').form('load',{
			insurance_id : $Insurance_Value.id1,
			insurance_name : $Insurance_Value.id2,
			insurance_memo : $Insurance_Value.id3
		})
	}
});