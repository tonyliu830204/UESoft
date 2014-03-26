<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>套打模板设计页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
      <div id="cc" class="easyui-layout" fit="true" border="false">  
          <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true"  id="addTempletBtn">新增模板</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="updateTempletBtn">修改模板</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="deleteTempletBtn">删除模板</a>
				<span id="button2"></span><br/>
          </div>
	      <div id="printDivtitle" region="west" split="false" border="false" style="height:90%;background:#eee;width:200px;">
	           
	      </div>
	      <div region="center" split="false" border="false" style="width:100%;height:90%;">
	           <textarea rows="3" cols="88" disabled="disabled" style="resize:none;height:55px;max-height:50px;"> 考虑到多页打印的情况,所有制作模板脚本html中不要使用"p"标签,我们的模板每页的脚本html要被"p标"签包围，一个"p"标签包围的脚本认为是一页几个"p"标签就是几页如:"<p><div>第一页脚本</div></p><p><div>第二页脚本</div></p>....."
	           </textarea>     
			   <textarea id="printDivbody" rows="600" cols="88" style="resize:none;"></textarea>
	      </div>
	      <div region="east" split="false" border="false" style="height:90%;background:#eee;width:200px;">
	           <input id="templetId" type="hidden"/>
	           <input id="templetKey" type="hidden"/>
	                            模板名称:<br/>
	           <input id="templetName" name="templetName" type="text" style="width:170px;" />
	      </div>
      </div>
  </body>
</html>