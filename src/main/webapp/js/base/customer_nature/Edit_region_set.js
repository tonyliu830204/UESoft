$(function(){
	var $regionRowValue = $('#region_set_id').datagrid('getSelections')[0];
	if($regionRowValue){
		$('form').form('load',{
			region_id : $regionRowValue.id1,
			region_name : $regionRowValue.id2,
			region_memo : $regionRowValue.id3
			});
		}
});