$(function (){
	$.ajax({
		   type:'POST',
		   dataType: 'json',
		   url:'baseAction!getChecked.action',
		   success : function(r){
		       if(r == 'checked'){
		    	   bigIcon(); 
		       }
	       }
    });
	
});


function openURL(url){
	if(url != undefined){
		$('#baseDataSet').panel('refresh', url);
	}
}