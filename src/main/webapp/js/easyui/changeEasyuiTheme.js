function changeTheme(e){var t=$("#easyuiTheme"),n=t.attr("href"),r=n.substring(0,n.indexOf("themes"))+"themes/"+e+"/easyui.css";t.attr("href",r);var i=$("iframe");if(i.length>0)for(var s=0;s<i.length;s++){var o=i[s];$(o).contents().find("#easyuiTheme").attr("href",r)}$.cookie("easyuiThemeName",null),$.cookie("easyuiThemeName",e,{expires:7,path:"/"})}$.cookie("easyuiThemeName")&&changeTheme($.cookie("easyuiThemeName"))