$(function (){
	  onmouseoverhider();
	  loadbody();
	  buildCheck(); 
	  setChecked('unchecked');
});

var classpanle_ = 'panel-body accordion-body';

/**
 * 初始化左导航折叠
 * @return
 */
function onmouseoverhider(){
	//$("body").layout('collapse','west');
}

/**
 * 根据登录不同的系统显示不同的Home页面
 * @return
 */
function loadbody(){
	$.ajax({
		   type:'POST',
		   dataType: 'json',
		   url:'login_getLoginUser.action',
		   success : function(r){
		       if(r.success){
		    	   var result = r.msg;
		    	   var data = result.split("*");
		    	   login_Name = data[0];
		    	   systemId = data[1];
		    	   sessionUserId = data[2];
			       $("#loginName").html(login_Name);
			       loadBgImg(systemId);
			       regist();
			   }else{
				   alertMsg(r.msg,'info');
			   }
	       }
   });
}

/**
 * 大图标小图标导航切换
 * @return
 */
function buildCheck(){
	var iconche = $("#changeIcon");
	iconche.click(function(){
		  var state = 'unchecked';
		  if (iconche.is(":checked")){
			  state = 'checked';
		  }else{
			  state = 'unchecked';
		  }
		  setChecked(state);
	 });
	 iconche.attr("checked", false);
}

function setChecked(check){
	$.ajax({
		   type:'POST',
		   dataType: 'json',
		   url:'baseAction!setChecked.action',
		   data:{check:check},
		   success : function(r){
		       if(check == 'checked'){
		    	   bigIcon(); 
		       }else{
		    	   smallIcon();
		       }
	       }
   });
}

/**
 * 加载Home页面
 * @param sy
 * @return
 */
function loadBgImg(sy){
	var homeDiv ;
	if(sy == "weixiu"){
		var imgTag = "<img src=\"images/weixiu.png\" alt=\"\" width=\"1900\" height=\"909\" border=\"0\" usemap=\"#Map\"/>";
		var areaTag1 = "<area shape=\"rect\" coords=\"505,105,607,159\" href=\"javascript:void(0)\" alt=\"会员入会\" title=\"会员入会\" onclick=\"addTab('#tabs', '会员档案管理',"+ projectPath+"'vip_management/vip_info_management.jsp');\" />";
		var areaTag2 = "<area shape=\"rect\" coords=\"918,107,1023,161\" href=\"javascript:void(0)\" alt=\"会员卡储值\" title=\"会员卡储值\" onclick=\"addTab('#tabs', '会员卡储值',"+ projectPath+"'vip_management/vip_chongzhi.jsp');\" />";
		var areaTag3 = "<area shape=\"rect\" coords=\"1327,107,1434,160\" href=\"javascript:void(0)\" alt=\"预约估价\" title=\"预约估价\" onclick=\"addTab('#tabs', '预约估价单',"+ projectPath+"'frt/frtResevation.jsp');\" />";
		var areaTag4 = "<area shape=\"rect\" coords=\"1328,299,1432,355\" href=\"javascript:void(0)\" alt=\"前台接车\" title=\"前台接车\" onclick=\"addTab('#tabs', '前台接车',"+ projectPath+"'frt/frtReception.jsp');\" />";
		var areaTag5 = "<area shape=\"rect\" coords=\"912,302,1016,356\" href=\"javascript:void(0)\" alt=\"仓库出库\" title=\"仓库出库\" onclick=\"addTab('#tabs', '工单退料单管理',"+ projectPath+"'st/StRtPartsManager.jsp');\" />";
		var areaTag6 = "<area shape=\"rect\" coords=\"504,303,608,357\" href=\"javascript:void(0)\" alt=\"交车结算\" title=\"交车结算\" onclick=\"addTab('#tabs', '交车结算',"+ projectPath+"'frt/frtPreClearing.jsp');\" />";
		var areaTag7 = "<area shape=\"rect\" coords=\"502,516,608,568\" href=\"javascript:void(0)\" alt=\"礼品兑换\" title=\"礼品兑换\" onclick=\"addTab('#tabs', '礼品兑换管理',"+ projectPath+"'vip_management/vip_lipinduihuan.jsp');\" />";
		var areaTag8 = "<area shape=\"rect\" coords=\"912,517,1015,571\" href=\"javascript:void(0)\" alt=\"客户跟踪\" title=\"客户跟踪\" onclick=\"addTab('#tabs', '客户跟踪管理',"+ projectPath+"'return_visit/customer_gz.jsp');\" />";
		var areaTag9 = "<area shape=\"rect\" coords=\"1330,517,1432,570\" href=\"javascript:void(0)\" alt=\"短信发送\" title=\"短信发送\" onclick=\"addTab('#tabs', '短信发送管理',"+ projectPath+"'vip_management/infomation_send_manag.jsp');\" />";
		var mapTag = "<map name=\"Map\" id=\"Map\">"+areaTag1+areaTag2+areaTag3+areaTag4+areaTag5+areaTag6+areaTag7+areaTag8+areaTag9+"</map>";
		homeDiv = imgTag+mapTag;
	}else{
		var imgTag = "<img src=\"images/xiaoshou.png\" alt=\"\" width=\"1832\" height=\"910\" border=\"0\" usemap=\"#Map\"/>";
		var areaTag1 = "<area shape=\"rect\" coords=\"259,107,398,159\" href=\"javascript:void(0)\" alt=\"来电(店)客流 登记\" title=\"来电(店)客流 登记\" onclick=\"addTab('#tabs', '来电(店)客流 登记',"+ projectPath+"'vip_management/vip_info_management.jsp');\" />";
		var areaTag2 = "<area shape=\"rect\" coords=\"528,103,662,160\" href=\"javascript:void(0)\" alt=\"潜在客户跟踪\" title=\"潜在客户跟踪\" onclick=\"addTab('#tabs', '潜在客户跟踪',"+ projectPath+"'vip_management/vip_chongzhi.jsp');\" />";
		var areaTag3 = "<area shape=\"rect\" coords=\"804,105,949,160\" href=\"javascript:void(0)\" alt=\"车辆预订\" title=\"车辆预订\" onclick=\"addTab('#tabs', '车辆预订',"+ projectPath+"'frt/frtResevation.jsp');\" />";
		var areaTag4 = "<area shape=\"rect\" coords=\"809,284,944,337\" href=\"javascript:void(0)\" alt=\"车辆调配作业\" title=\"车辆调配作业\" onclick=\"addTab('#tabs', '车辆调配作业',"+ projectPath+"'frt/frtReception.jsp');\" />";
		var areaTag5 = "<area shape=\"rect\" coords=\"526,285,665,339\" href=\"javascript:void(0)\" alt=\"车辆出库\" title=\"车辆出库\" onclick=\"addTab('#tabs', '车辆出库',"+ projectPath+"'st/StRtPartsManager.jsp');\" />";
		var areaTag6 = "<area shape=\"rect\" coords=\"255,284,397,340\" href=\"javascript:void(0)\" alt=\"车辆销售\" title=\"车辆销售\" onclick=\"addTab('#tabs', '车辆销售',"+ projectPath+"'frt/frtPreClearing.jsp');\" />";
		var areaTag7 = "<area shape=\"rect\" coords=\"255,477,398,531\" href=\"javascript:void(0)\" alt=\"售后回访\" title=\"售后回访\" onclick=\"addTab('#tabs', '售后回访',"+ projectPath+"'vip_management/vip_lipinduihuan.jsp');\" />";
		var mapTag = "<map name=\"Map\" id=\"Map\">"+areaTag1+areaTag2+areaTag3+areaTag4+areaTag5+areaTag6+areaTag7+"</map>";
		homeDiv = imgTag+mapTag;
	}
	$("#homeBody").empty();
	$("#homeBody").html(homeDiv);
}

/**
 * 设置页面的大小
 * @return
 */
function pageSize(){
	var resolution = $("#resolution");
	resolution.change(function(){
		  var data=$(this).children('option:selected').val();
		  var index = data.indexOf("*");
 	      var width = data.substring(0,index);
 	      var height = data.substring(index+1,data.length);
		  $("body").css({"width": width+"px","height": height+"px"});
	});
}

/**
 * 监听同一帐号不同人或者地方登录
 * @return
 */
function regist(){
	PL._init(); 
	PL.joinListen('/qiangbi/xixian/');
}
/**
 * 回调函数
 * @param event
 * @return
 */
function onData(event) { 
	var result = decodeURIComponent(event.get("key1"));
	if(result != null){
		result = result.substring(1,result.length-1);
	    var data = result.split(",");
	    if(data.length > 1){
		    if(data[1] == sessionUserId){
		    	alert(data[0]);  
			    invalidateSession();
		    }
	    }
	}
}
/**
 * 处理同时登录就将前着逼迫退出
 * @return
 */
function invalidateSession(){
	location.href = 'login_dologout.action';
}


var flag1=true;
var flag2=true;
var flag3=true;
var flag4=true;
var flag5=true;
var flag6=true;
var controlManage=function(id,tag){
	var showFlag;
	if(flag1==false&&tag==1){
		showFlag=false;
		flag1=true;
	}else if(flag1==true&&tag==1){
		showFlag=true;
		flag1=false;
	}else if(flag2==false&&tag==2){
		showFlag=false;
		flag2=true;
	}else if(flag2==true&&tag==2){
		showFlag=true;
		flag2=false;
	}else if(flag3==false&&tag==3){
		showFlag=false;
		flag3=true;
	}else if(flag3==true&&tag==3){
		showFlag=true;
		flag3=false;
	}else if(flag4==false&&tag==4){
		showFlag=false;
		flag4=true;
	}else if(flag4==true&&tag==4){
		showFlag=true;
		flag4=false;
	}else if(flag5==false&&tag==5){
		showFlag=false;
		flag5=true;
	}else if(flag5==true&&tag==5){
		showFlag=true;
		flag5=false;
	}else if(flag6==false&&tag==6){
		showFlag=false;
		flag6=true;
	}else if(flag6==true&&tag==6){
		showFlag=true;
		flag6=false;
	}
	if(showFlag==true){
		document.getElementById(id).style.display="block";
	}else{
		document.getElementById(id).style.display="none";
	}
}
/**选择企业*/
var choiceStoreDialog =null;
var choiceStore= function(){
	choiceStoreDialog=$('<div/>').dialog({
		modal : true,
		title : '切换企业',
		closable : true,
		href : projectPath+'choiceStore.jsp',
		width : 800,
		height : 420,
		buttons : [ {
			text : '确定',
			iconCls : 'icon-add',
			handler : function() {
				reStart();
			}
		},{
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
			choiceStoreDialog.dialog('close');
			}
		} ],
		onClose : function() {
			$(this).dialog('destroy');
		}
	});
}
var reStart=function(){
	var rowData=$('#showStoreDatagrid').datagrid('getSelected');
	if(rowData){
		$('#making').dialog({
			closable:false
		});
		 $.ajax({
     		   type:'POST',
     		   dataType: 'json',
     		   url:'login_reStart.action',
     		   data:'enterpriseId='+rowData.enterpriseId,
     		   success : function(r){
     		       if(r.success){
     		    	  choiceStoreDialog.dialog('close');
     		    	 $('#making').dialog('close');
     		    	  window.location.href="main.jsp";
     			   }else{
     				   $('#making').dialog('close');
     				   alertMsg(r);
     			   }
     	       }
         });
	}else{
		alertMsg('请选择要切换的企业！','warning');
	}
}