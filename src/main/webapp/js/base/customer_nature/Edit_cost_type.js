$(function(){
	var $rowValue = $Cost_type_id.datagrid('getSelections')[0];
	if($rowValue){
		$('form').form('load',{
			jixie_id:$rowValue.id1,
			jixie_name:$rowValue.id2,
			jixie_memo:$rowValue.id3
			});
		}
});