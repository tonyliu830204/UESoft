$(function (){
	$("#customEmail").mailAutoComplete();
});
function customArchivesKeyUp(id1,id2){
	var dataLength=document.getElementById(id1).value.length;
	document.getElementById(id2).innerHTML=dataLength;
}