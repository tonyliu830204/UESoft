<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>数据备份</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/base/datebase/data_backup.js"></script>
  </head>
  <body>
      <div id="cc" class="easyui-layout" fit="true" border="false">  
        <div region="north"   border="false" split="false" style="height:30px;background: #eee;">
                <privilege:enable code="DATEBASEMANAGEBACK">
				     <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-backup" plain="true" onclick="backup();">备份</a>
				</privilege:enable>
				<privilege:enable code="DATEBASEMANAGEDIS">
				     <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-toreceipt" plain="true" onclick="restore();">还原</a>
				</privilege:enable>
        </div>
        <div region="center"  style="background:#eee;" split="false" border="false">   
		   <div id="ssom_tabs" class="easyui-tabs" fit="true" border="false">  
		     <div title="销售单汇总" style="display:block;">  
		        <div id="cc" class="easyui-layout" fit="true" border="false">  
				    <div region="north" title="" split="false" style="overflow: hidden;height:20px;background:#eee;" border="false">
					   <form id="stSellOrderSearchForm">
						    <table>
							    <tr>
							       <td></td>
								   <td></td>
							    </tr>
						     </table>
					    </form>
				    </div>
				    <div region="center" id="databackupDiv" border="false" style="background:#eee;">
				        <table id="datagrid_databackup_id"></table>
				    </div>  
				</div>  
		     </div> 
		   </div>
	   </div>
	 </div>
  </body>
</html>
