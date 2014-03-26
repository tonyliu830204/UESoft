<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>企业管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  <script type="text/javascript">
  		var staticLoginLevel="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getLeavl() %>";
	    var staticAdministrator="<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
	    var staticEnterpriseAdmin="<%=Contstants.EMPLOYEELEVEL.ADMIN %>";
	    var staticEmployee="<%=Contstants.EMPLOYEELEVEL.GENERAL %>";
	    var staticEnterpriseId=<%=session.getAttribute(Contstants.ENTERPRISEID).toString()%>;
  	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/base/enterpriseInfo/enterpriseInfo.js"></script>
	   <div id="cc" class="easyui-layout" fit="true" border="false">  
          <div region="north"  split="false" style="height:30px;background: #eee;" border="false"> 
              <privilege:enable code="ENTERPRISEINFOADD">
                <a href="javascript:void(0);" id="spo_addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
              </privilege:enable>
              <privilege:enable code="ENTERPRISEINFODEL">
				<a href="javascript:void(0);" id="spo_delectBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			  </privilege:enable>
			  <privilege:enable code="ENTERPRISEINFOUPDATE">
				<a href="javascript:void(0);" id="spo_editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			  </privilege:enable>
			  <privilege:enable code="ENTERPRISEINFOSEARCH">
				<a href="javascript:void(0);" id="spo_seatchBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
			  </privilege:enable>
			  <privilege:enable code="ENTERPRISEINFOCLEAR">
				<a href="javascript:void(0);" id="spo_clearBtn" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">清空</a>
			  </privilege:enable>
			  <privilege:enable code="ENTERPRISEINFOPRING">
				<a href="javascript:void(0);" id="spo_printBtn" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
			  </privilege:enable>
			  <privilege:enable code="ENTERPRISEINFOEXCEPT">
				<a href="javascript:void(0);" id="spo_exportBtn" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
			  </privilege:enable>
			  <privilege:enable code="ENTERPRISEINFOIMPORT">
				<a href="javascript:void(0);" id="spo_inportBtn" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
			  </privilege:enable>
			  <privilege:enable code="ENTERPRISEINFOIMPORT">
			      	<a id="ok" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" disabled="disabled">确认导入</a>
			   </privilege:enable>
          </div>
		  <div region="center"  split="false" border="false">
			<div id="tt" class="easyui-tabs" fit="true" border="false">
				<div title="企业信息" style="display: block;" border="false" closable="false" fit="true">
					<div id="cc" class="easyui-layout" fit="true" border="false">
						<div region="north" title="条件" split="false"
							style="overflow: hidden;padding:3px;height:58px; background: #eee;" border="false">
							<form id="enterpriseQueryForm" name="insurerQueryForm" method="post"  fit="true" >
								<table>
									 <tr>
									    <td>企业编号:</td>
										<td><input type="text" name="enterpriseCode" style="width:200px;"/></td>
										<td>企业名称:</td>
										<td><input type="text" name="enterpriseName" style="width:200px;"/></td>
										<td>企业简称:</td>
										<td><input type="text" name="enterpriseJm" style="width:200px;"/></td>
<%--										<td>父企业:</td>--%>
<%--										<td><input name="parentEnterpriseId" style="width:200px;" class="easyui-combotree" --%>
<%--										data-options="url:'${pageContext.request.contextPath}/enterpriseInfoAction!findPEnterprise.action?enterpriseInfoFlag=true'" ></td>--%>
										<td>企业法人:</td>
										<td><input type="text" name="enterprisePerson" style="width:200px;"/></td>
									</tr>
								</table>
						    </form>
						</div>
						<div region="center" id="enterpriseInfoCenter" style="background: #eee;" border="false">
							<table id="enterpriseInfo"></table>
						</div>
					</div>
				</div>
				<div title="企业业务信息" border="false" closable="false">
					<div id="cc" class="easyui-layout"  border="false" fit="true">
						<div region="north" split="false" style="overflow: hidden;height:115px; background: #eee;" border="false">
							<form id="enterpriseDisForm" name="enterpriseDisForm" method="post"  fit="true" >
								<table style="margin-top: 10px;">
								    <input type="hidden" name="enterpriseId"  />
	                                <tr>
										<td>企业编号:</td>
										<td><input type="text" id="enterpriseCode" name="enterpriseCode" style="width:200px"/></td>
										<td>企业名称:</td>
										<td><input type="text" id="enterpriseName" name="enterpriseName" style="width:200px"/></td>
										<td>企业简称:</td>
										<td><input type="text" id="enterpriseJm" name="enterpriseJm" style="width:200px"/></td>
										<td>企业地址:</td>
										<td><input type="text" id="enterpriseAddress" name="enterpriseAddress" style="width:200px"/></td>	
									</tr>
									<tr>
										<td>邮政编码:</td>
										<td><input type="text" id="enterpriseZipcode" name="enterpriseZipcode" style="width:200px"/></td>	
										<td>传真:</td>
										<td><input type="text" id="enterpriseFax" name="enterpriseFax" style="width:200px" /></td>				
										<td>父企业:</td>
										<td><input type="text" id="parentEnterpriseName" name="parentEnterpriseName" style="width:200px" /></td>
										<td>企业法人:</td>
										<td><input type="text" id="enterprisePerson" name="enterprisePerson" style="width:200px" /></td>	
									</tr>
									<tr>
										<td>电话:</td>
										<td><input type="text" id="enterpriseTelephone" name="enterpriseTelephone" style="width:200px"/></td>
										<td>邮箱:</td>
										<td><input type="text" id="enterpriseEmail" name="enterpriseEmail" style="width:200px;"/></td>
										<td>开户银行:</td> 
										<td><input id="bank" name="bank" style="width:200px" class="easyui-combobox" 
									    data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BANKTYPE%>',   
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#bank\']',
										invalidMessage : '请从下拉框中选择属性' "/></td>
										<td>银行账号:</td>
											<td colspan="3"><input type="text" id="bankNumber" name="bankNumber" style="width:200px;"/></td>
									</tr>
									<tr>
										<td>税号:</td>
										<td><input type="text" id="dutyNumber" name="dutyNumber" style="width:200px;"/></td>
										<td>投诉电话:</td>
										<td><input type="text" id="complainTelephone" name="complainTelephone" style="width:200px"/></td>
										<td>销售热线:</td>
										<td><input type="text" id="hotlineTelephone" name="hotlineTelephone" style="width:200px"/></td>
										<td>网址:</td>
										<td><input type="text" id="enterpriseUrl" name="enterpriseUrl" style="width:200px"/></td>
									</tr>
								</table>
						    </form>
						</div>
						<div region="center" style="background: #eee;" aplit="false" border="false">
							<table id="detailTable"></table>
						</div>
					</div>
				</div>
			</div>
	      </div>
       </div>
	</body>
</html>
