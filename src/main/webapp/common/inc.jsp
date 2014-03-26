<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
   var projectPath = "${pageContext.request.contextPath}/";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax-ph.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/AjaxUpload.js"></script>
<link rel="stylesheet" id="easyuiTheme" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" id="easyuiiconTheme" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/icon.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/changeEasyuiTheme.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/default.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my97/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/groupware.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Lodop/print.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/HashMap.js"></script>
<script type="text/javascript">
var analyseImagePath="${pageContext.request.contextPath}";
var bigicon = 'unchecked';
var _class = 'easyui-linkbutton l-btn l-btn-plain';
var _replaceclass = 'easyui-linkbutton l-btn1 l-btn-plain1';
function bigIcon(){
	var atag = $("a[name='icona']");
	atag.each(function(i){
		var obj = $(this);
		var childrenObj = obj.children();
		if(childrenObj.length = 1){
			var iconname = obj.attr("iconName");
			if(iconname != undefined){
				obj.attr('class',_replaceclass);
				var fspan = obj.find("span");
				var sspan = fspan.find("span");
                var text1 = sspan.find("span").html();
				obj.empty();
				obj.html('<img src="css/icons/'+iconname+'_MAX.png" width="64px" height="64px" sytle="text-align:center;"/><br/><span class="l-btn-left1"><span class="l-btn-text1" style="padding-left: 20px;"><span style="padding:0px 0px 0px 0px;">'+text1+'</span></span>');
			}  
		} 
	});
}

function smallIcon(){
	var atag = $("a[name='icona']");
	atag.each(function(i){
		var obj = $(this);
		var childrenObj = obj.children();
		if(childrenObj.length > 1){
			var iconname = obj.attr("iconName");
			if(iconname != undefined){
				obj.attr('class',_class);
				var fspan = obj.find("span");
				var sspan = fspan.find("span");
                var text1 = sspan.find("span").html();
				obj.empty();
				obj.html('<span class="l-btn-left"><span style="padding-left: 20px;" class="l-btn-text icon-'+iconname+'"><span>'+text1+'</span></span></span>');
			}  
		} 
	});
}

function jsonToString (obj){   
    var THIS = this;    
    switch(typeof(obj)){   
        case 'string':   
            return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';   
        case 'array':   
            return '[' + obj.map(THIS.jsonToString).join(',') + ']';   
        case 'object':   
             if(obj instanceof Array){   
                var strArr = [];   
                var len = obj.length;   
                for(var i=0; i<len; i++){   
                    strArr.push(THIS.jsonToString(obj[i]));   
                }   
                return '[' + strArr.join(',') + ']';   
            }else if(obj==null){   
                return 'null';   

            }else{   
                var string = [];   
                for (var property in obj) string.push(THIS.jsonToString(property) + ':' + THIS.jsonToString(obj[property]));   
                return '{' + string.join(',') + '}';   
            }   
        case 'number':   
            return obj;   
        case false:   
            return obj;   
    }   
}


function stringToJSON(obj){   
    return eval_r('(' + obj + ')');   
}

$.ajaxSetup({   
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    cache:false,   
    complete:function(XMLHttpRequest,textStatus){
    	 var resText=XMLHttpRequest.responseText; //通过XMLHttpRequest取得响应头，sessionstatus 
    	 if(resText=="{sessionState:0}"){ 
              var nav=judgeNavigator();
        	  if(nav.indexOf("IE:6")>-1){
        	      window.opener=null;
        	      window.close();
        	      window.open('${pageContext.request.contextPath}/relogin.jsp','');
        	  }else{
        	      window.open('${pageContext.request.contextPath}/relogin.jsp','_top');
        	  }      
         }else{
              this;
         }   
    	 XMLHttpRequest = null;
    },
    error : function (r){
		  if(r.readyState == '0' && r.status == '0'){
			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
		  }
	}   
});  
function judgeNavigator()
{
   return navigator.userAgent;
}

function jsProbar() {
    $(document).ready(function () {
        $.blockUI({ message: '<h1><img src="${pageContext.request.contextPath}/images/loading.gif" />  请稍后...</h1>' });
    });
}

function jsCloseProbar() {
    $(document).ready(function () {
        $.unblockUI();
    });
}
</script>