<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>业务参数设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
	$(function($) {
		initOneFrame();
		verifCheckEmployee();
		$("#sava").bind("click", function(){
			save();
		});
	});
	
	var initOneFrame = function() {
		 $("#employee").combotree({
			 //获取数据URL  
			 url : '${pageContext.request.contextPath}/controlRoleAction!getBusinessUser.action?softType=<%=Contstants.SYSTEMTYPE.WEIXIU %>',
		     onChange:function(nodeId){
		         $.ajax({
		     	    type:'POST',
		     	    url : '${pageContext.request.contextPath}/controlRoleAction!getStorehouseByUser.action?employee='+nodeId,
		     	    dataType:'json',
		     	    success : function(r) {
		     			if (r != null) {
		     				$("#storeHouse").combobox("setValues",r);
		     			}else{
		     				$("#storeHouse").combobox("clear");
		     			}
		     		},
		     		error : function (r){
		     		   if(r.readyState == '0' && r.status == '0'){
		     			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
		     		   }
		     	    }
		     	 });
		     }    
		});
	}
	
	var save = function() {
		var t = $("#employee").combotree('tree');
		var n = t.tree('getSelected');
		if(n != null){
			if(
			      $('#parameterOne0Form').form('validate')
			   && $('#parameterOne1Form').form('validate')
			   && $('#parameterOne2Form').form('validate')
			   && $('#parameterOne3Form').form('validate')
			   && $('#parameterOne4Form').form('validate')
			   && $('#parameterOne5Form').form('validate')
			   && $('#parameterOne6Form').form('validate')
			   && $('#parameterOne7Form').form('validate')
			   && $('#parameterOne8Form').form('validate')
			){
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : '${pageContext.request.contextPath}/controlRoleAction!saveOrUpdate.action',
					data : 
					   $('#parameterOne0Form').serialize() + '&' + 
					   $('#parameterOne1Form').serialize() + '&' + 
					   $('#parameterOne2Form').serialize() + '&' +
					   $('#parameterOne3Form').serialize() + '&' +
					   $('#parameterOne4Form').serialize() + '&' +
					   $('#parameterOne5Form').serialize() + '&' +
					   $('#parameterOne6Form').serialize() + '&' + 
					   $('#parameterOne7Form').serialize() + '&' +
					   $('#parameterOne8Form').serialize(),
					success : function(r) {
						if (r.success) {
							$('#button').empty();
							alertMsg(r);
							$('#parameterTabs').tabs('select', 0);
						}else{
							alertMsg(r);
						}
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
				});
			}else{
				alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
			}
		}else{
            alert("请选择是哪个业务员");
		}
	}

	var verifCheckEmployee = function() {
		$('#parameterTabs').tabs({   
			onSelect: function(title,index){
				if(index > 0){
					var t = $("#employee").combotree('tree');
					var n = t.tree('getSelected');
					if(n == null){
						 $('#parameterTabs').tabs('select',0);
                         alert("请选择业务人员");
					}
				}
		    }
		});    
	}
	</script>

  </head>
  <body>
       <div id="cc" class="easyui-layout" fit="true" border="false"> 
	       <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
	            <privilege:enable code="SERVICECONTROLADD">
	       	          <a id="sava" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true">保存</a>
			    </privilege:enable>
			    <span id="button"></span>
	       </div>
           <div region="center"  split="false" border="false">
                <div id="tt" class="easyui-layout" fit="true" border="false">
                     <div data-options="region:'north',border:false" style="padding:1px;height:30px;overflow:hidden;background:#eee;">
                        <form id="parameterOne0Form">
				            <table >
								  <tr> 
									   <td width="80">业务人员 :</td>
				 					   <td>
									        <input id="employee" name="employee" style="width:220px;" class="easyui-combotree" >
				 					   </td>
				 				  </tr>
							</table>
						</form>
			         </div>
		             <div region="center"  split="false" border="false">
						<div id="parameterTabs" class="easyui-tabs" data-options="fit:true,border:false">
						    <div id="parameter1" data-options="fit:true,border:false,title:'仓别',href:'${pageContext.request.contextPath}/common/base/control/StorehouseControl.jsp'">
							</div>
							<div id="parameter2" data-options="fit:true,border:false,title:'短信发送',href:'${pageContext.request.contextPath}/common/base/control/MSNControl.jsp'">
							</div>
							<div id="parameter3" data-options="fit:true,border:false,title:'审核',href:'${pageContext.request.contextPath}/common/base/control/ExamineControl.jsp'">
							</div>
							<div id="parameter4" data-options="fit:true,border:false,title:'基础资料',href:'${pageContext.request.contextPath}/common/base/control/BaseControl.jsp'">
							</div>
							<div id="parameter5" data-options="fit:true,border:false,title:'仓库管理',href:'${pageContext.request.contextPath}/common/base/control/WarehouseControl.jsp'">
							</div>
							<div id="parameter6" data-options="fit:true,border:false,title:'前台管理',href:'${pageContext.request.contextPath}/common/base/control/StageControl.jsp'">
							</div>
							<div id="parameter7" data-options="fit:true,border:false,title:'折扣及其他',href:'${pageContext.request.contextPath}/common/base/control/DiscountControl.jsp'">
							</div>
							<div id="parameter8" data-options="fit:true,border:false,title:'客户关怀中心',href:'${pageContext.request.contextPath}/common/base/control/CustomerCareControl.jsp'">
							</div>
						</div>
		             </div>
                
                </div>
           </div>
       </div>
  </body>
</html>