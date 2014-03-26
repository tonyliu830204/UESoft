<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>打印分组管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
   <img src="images/loading.gif" id="loading" style="display: none;">
    <div id="cc" class="easyui-layout" fit="true" border="false">  
               <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
                <a id="addBtn" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增分组</a>
				<a id="updateBtn" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改分组</a>
				<a id="deleteBtn" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除分组</a>
				<a id="setBtn" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-set" plain="true">设置默认分组</a>
				<span id="button1"></span><br/>
              </div>
			  <div region="center"  split="false" border="false">
                  <table style='width:100%;height:100%;'>
		             <tr>
		                  <td style="width:35%;height:400px;top:0px;">
			                  <table style='width:100%;height:336px;'>
				                  <tr>
					                  <td>
					                       <fieldset style='width:90%;height:336px;'>
						                   <legend >所以打印分组</legend>
					                           <div id="groupDiv" style="width:100%;height:100%;text-align:left;line-height:20px;"></div>
										   </fieldset>
					                  </td>
					               </tr>
					               <tr>
					                  <td>
									      <fieldset style='width:90%;height:80px;'>
						                  <legend >新增打印分类</legend>
										      <input id="dataGridId" type="hidden"/>
										      <input id="selectSubGroup" type="hidden"/>
										      <input id="selectSubGroupSort" type="hidden"/>
										      <input id="funType" type="hidden"/>
											  <div id="groupNameDiv" style="width:100%;height:100%;text-align:left;">
												     打印分类名称：
							                      <input id="groupName" name="groupName" type="text" style="width:90px;" />
											  </div>
											  <div id="importFileDiv" style="width:100%;height:100%;text-align:left;">
												     导入文件路径：<br/>
							                      <input id="filePath" name="filePath" type="file" style="width:190px;height:25px" />
											  </div>
										  </fieldset>
					                  </td>
				                  </tr>
			                  </table>
		                  </td>
		                  <td style="width:65%;height:400px;top:0px;">
							  <fieldset style='width:90%;height:400px;'>
							  <legend>打印列选择</legend>
			                      <table width="100%" height="350px" border="0" cellpadding="0" cellspacing="0" >
			                            <tr>
								             <td colspan="2"><CENTER>待选列</CENTER></td>
										     <td colspan="2"><CENTER>已选列</CENTER></td>
								        </tr> 
									    <tr>  
									        <td width="20">  
									            <input type="button" name="leftup" id="leftup" value="↑" style='width:20px;'/><br />  
									            <input type="button" name="leftdown" id="leftdown" value="↓" style='width:20px;'/>
									        </td>  
									        <td width="180px" >
									            <select id="left" name="left" size="10" multiple="multiple" style='width:180px;height:350px;'></select>
									        </td>  
									        <td width="30px" align="center">
									            <input type="button" name="addall" id="addall" value="&gt;" style='width:20px;'/><br />
									            <input type="button" name="add" id="add" value="&gt;&gt;" style='width:20px;'/><br />
									            <input type="button" name="remove" id="remove" value="&lt;&lt;" style='width:20px;'/><br />
									            <input type="button" name="removeall" id="removeall" value="&lt;" style='width:20px;'/>
									        </td>  
									        <td width="180px" >
									            <select id="right" name="right" size="10" multiple="multiple" style='width:180px;height:350px;'></select>
									        </td>  
									        <td width="20">  
									            <input type="button" name="rightup" id="rightup" value="↑" style='width:20px;'/><br />  
									            <input type="button" name="rightdown" id="rightdown" value="↓" style='width:20px;'/>
									        </td>  
									    </tr>  
								  </table>
							  </fieldset>
		                  </td>
		             </tr>
		        </table>
			</div>
		</div>
  </body>
</html>