var ColorHex=new Array('00','33','66','99','CC','FF');
var SpColorHex=new Array('FF0000','00FF00','0000FF','FFFF00','00FFFF','FF00FF');
var widget=null;
var div=null;

function doclick(obj){
	document.getElementById(widget).value=obj;
	document.getElementById(div).style.display = "none";
}
function colorclose(){
	document.getElementById(div).style.display = "none";
}
function colorreplace(col){
	document.getElementById(col).style.display = "none";
}
function coloropen(inputId, divId){
	widget = inputId;
	div = divId;
	if(inputId != null && divId != null){
	    document.getElementById(div).style.display = "block";
	    var colorTable=''
		for (i=0;i<2;i++)
		{
			
			for (j=0;j<6;j++)
			{
				colorTable=colorTable+'<tr height=15>'
				colorTable=colorTable+'<td width=15 style="background-color:#000000">'
				if (i==0){
				   colorTable=colorTable+'<td width=15 style="cursor:pointer;background-color:#'+ColorHex[j]+ColorHex[j]+ColorHex[j]+'" onclick="javaScript:doclick(this.style.backgroundColor)">'
				}else{
					colorTable=colorTable+'<td width=15 style="cursor:pointer;background-color:#'+SpColorHex[j]+'" onclick="javaScript:doclick(this.style.backgroundColor)">'
				}
				colorTable=colorTable+'<td width=15 style="background-color:#000000">'
				for (k=0;k<3;k++)
				{
					for (l=0;l<6;l++)
					{
					    colorTable=colorTable+'<td width=15 style="cursor:pointer;background-color:#'+ColorHex[k+i*3]+ColorHex[l]+ColorHex[j]+'" onclick="javaScript:doclick(this.style.backgroundColor)">'
					}
				}
			}
		}
		colorTable='<table border="0" cellspacing="0" cellpadding="0" style="border:1px #000000 solid;border-bottom:none;border-collapse: collapse;width:337px;" bordercolor="000000">'
			+'<tr height=20><td colspan=21 bgcolor=#ffffff style="font:12px tahoma;padding-left:2px;">'
			+'<span style="float:left;color:#999999;"></span>'
			+'<span style="float:right;padding-right:3px;cursor:pointer;" onclick="colorclose()">×关闭</span>'
			+'</td></table>'
			+'<table border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="000000" style="cursor:pointer;">'
			+colorTable+'</table>';
		document.getElementById(div).innerHTML=colorTable;
		evt = arguments[0] || window.event;
		var _mousePosition = mousePosition(evt);
		var current_x = _mousePosition[0]; 
		var current_y = _mousePosition[1]; 
		document.getElementById(div).style.left = current_x + "px";
		document.getElementById(div).style.top = current_y + "px";
    }
}

function mousePosition(evt){
    var xPos,yPos;          
    evt=evt || window.event;
    if(evt.pageX){          
        xPos=evt.pageX;          
        yPos=evt.pageY;      
    } else {          
        xPos=evt.clientX+document.body.scrollLeft -document.body.clientLeft; 
        yPos=evt.clientY+document.body.scrollTop-document.body.clientTop;      
    } 
    return [xPos, yPos];
}