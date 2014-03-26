$(function (){
	var $vipServerValue = $('#vip_server_id').datagrid('getSelected');
	if($vipServerValue){
		$('form').form('load',{
			vip_id : $vipServerValue.id1,
			vip_name : $vipServerValue.id2,
			vip_memo : $vipServerValue.id3
		})
	}
});