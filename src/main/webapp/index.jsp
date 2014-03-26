<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>优亿汽车管理系统</title>
	</head>
	<body>
		<script type="text/javascript">
		function load(){
			var Browser_Name=navigator.appName;                        //浏览器名称
	        var Browser_Version=parseFloat(navigator.appVersion);      //浏览器版本
	        var Browser_Agent=navigator.userAgent;                     //
	        
	        var is_IE = (Browser_Name=="Microsoft Internet Explorer"); //判读是否为ie浏览器
	        var is_NN = (Browser_Name=="Netscape");                    //判断是否为netscape浏览器
	        var is_op = (Browser_Name=="Opera");                       //判断是否为Opera浏览器
	        
	        if(is_NN == true){
	        	window.location.href="${pageContext.request.contextPath}/login_main.action";
	        }else{
	            var msg = "<h>很遗憾！ 您的浏览器不支持本系统应用</h></br>请使用谷歌浏览器google chrome或者火狐浏览器Firefox </br>"
	                +"下载地址: </br>"
	                +"<a href='https://dl.google.com/tag/s/appguid%3D%7B8A69D345-D564-463C-AFF1-A69D9E530F96%7D%26iid%3D%7BE1661374-9741-7FD3-93FC-44381D403A1C%7D%26lang%3Dzh-CN%26browser%3D3%26usagestats%3D0%26appname%3DGoogle%2520Chrome%26needsadmin%3Dprefers%26installdataindex%3Ddefaultbrowser/update2/installers/ChromeSetup.exe'>谷歌浏览器google chrome</a> </br>"
	                +"<a href='http://download.firefox.com.cn/releases/stub/official/zh-CN/Firefox-latest.exe'>火狐浏览器Firefox</a>";
	        	sAlert(msg);
	        	return;
	        }
		}
		
        function sAlert(str){ 
       	  var msgw,msgh,bordercolor; 
       	  msgw=400;//Width
       	  msgh=180;//Height 
       	  titleheight=25 //title Height
       	  bordercolor="#336699";//boder color
       	  titlecolor="#99CCFF";//title color
       	  var sWidth,sHeight; 
       	  sWidth=document.body.offsetWidth; 
       	  sHeight=screen.height; 
       	  var bgObj=document.createElement("div"); 
       	  bgObj.setAttribute('id','bgDiv'); 
       	  bgObj.style.position="absolute"; 
       	  bgObj.style.top="0"; 
       	  bgObj.style.background="#777"; 
       	  bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75"; 
       	  bgObj.style.opacity="0.6"; 
       	  bgObj.style.left="0"; 
       	  bgObj.style.width=sWidth + "px"; 
       	  bgObj.style.height=sHeight + "px"; 
       	  bgObj.style.zIndex = "10000"; 
       	  document.body.appendChild(bgObj); 
       	  var msgObj=document.createElement("div") 
       	  msgObj.setAttribute("id","msgDiv"); 
       	  msgObj.setAttribute("align","center"); 
       	  msgObj.style.background="white"; 
       	  msgObj.style.border="1px solid " + bordercolor; 
       	  msgObj.style.position = "fixed"; 
       	  msgObj.style.left = "50%"; 
       	  msgObj.style.top = "50%"; 
       	  msgObj.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif"; 
       	  msgObj.style.marginLeft = "-225px" ; 
       	  msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px"; 
       	  msgObj.style.width = msgw + "px"; 
       	  msgObj.style.height =msgh + "px"; 
       	  msgObj.style.textAlign = "center"; 
       	  msgObj.style.lineHeight ="25px"; 
       	  msgObj.style.zIndex = "10001"; 
       	  var title=document.createElement("h4"); 
       	  title.setAttribute("id","msgTitle"); 
       	  title.setAttribute("align","right"); 
       	  title.style.margin="0"; 
       	  title.style.padding="3px"; 
       	  title.style.background=bordercolor; 
       	  title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);"; 
       	  title.style.opacity="0.75"; 
       	  title.style.border="1px solid " + bordercolor; 
       	  title.style.height="30px"; 
       	  title.style.font="12px Verdana, Geneva, Arial, Helvetica, sans-serif"; 
       	  title.style.color="white"; 
       	  title.style.cursor="pointer"; 
       	  title.innerHTML="关闭"; 
       	  title.onclick=function(){ 
       			 document.body.removeChild(bgObj); 
       			 document.getElementById("msgDiv").removeChild(title); 
       			 document.body.removeChild(msgObj); 
          } 
       	  document.body.appendChild(msgObj); 
       	  document.getElementById("msgDiv").appendChild(title); 
       	  var txt=document.createElement("p"); 
       	  txt.style.margin="1em 0" 
       	  txt.setAttribute("id","msgTxt"); 
       	  txt.innerHTML=str; 
       	  document.getElementById("msgDiv").appendChild(txt); 
       } 
       load();
	   </script>
	</body>
</html>