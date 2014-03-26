<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>月结反月结</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StMonthlyStatement/StMonthlyStatement.js"></script>
  </head>
  <body>	
	<div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
				<privilege:enable code="StMonthlyStatementAdd">
		        	 <a id="addBtn"  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-month" plain="true" >月结</a>
		        </privilege:enable>
		        <privilege:enable code="StMonthlyStatementDelete">
		       		 <a id="delBtn" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undomonth" plain="true" >反月结</a>
		        </privilege:enable>
		        <privilege:enable code="StMonthlyStatementSearch">
			         <a id="serachBtn" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
			     </privilege:enable>
			     <privilege:enable code="StMonthlyStatementClear">
			         <a id="clearBtn" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">清空</a>
			     </privilege:enable>
			    <span id="button"></span>
		</div>
		<div region="center"  style="background:#eee;">
			<div id="tt" class="easyui-tabs" style="padding: 0px; width: 800px; height: 600px;"	fit="true" border="false"">
				<div title="月结汇总" style="width: 800px; height: 600px;" border="false">
					<div class="easyui-layout" border="false" fit="true" style="width: 800px; height: 600px;">
						<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:60px;" border="false">
							<form id="form_summary" method="post">
							     <table>
								      <tr>
								          <td>月结节点时间:</td>
								          <td><input id="s_msStarttime"  name="msStarttime" style="width:190px;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:true,errDealMode:1,maxDate:'#F{$dp.$D(\'s_msEndtime\',{d:-1})}'})" /></td>
								          <td>至</td>
								          <td><input id="s_msEndtime" name="msEndtime" style="width:190px;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:true,errDealMode:1,minDate:'#F{$dp.$D(\'s_msStarttime\',{d:0})}'})"/></td>
								          <td>经办日期:</td>
									      <td><input id="s_operStartDate"  name="operStartDate" style="width:190px;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:true,errDealMode:1,maxDate:'#F{$dp.$D(\'s_operEndDate\',{d:-1})}'})" /></td>
									      <td>至</td>
									      <td><input id="s_operEndDate" name="operEndDate" style="width:190px;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:true,errDealMode:1,minDate:'#F{$dp.$D(\'s_operStartDate\',{d:0})}'})"/></td>
								      </tr>
								      <tr>
							              <td>反月结日期:</td>
									      <td><input id="s_msStartRemindtime"  name="msStartRemindtime" style="width:190px;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:true,errDealMode:1,maxDate:'#F{$dp.$D(\'s_msEndRemindtime\',{d:-1})}'})" /></td>
									      <td>至</td>
									      <td><input id="s_msEndRemindtime" name="msEndRemindtime" style="width:190px;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:true,errDealMode:1,minDate:'#F{$dp.$D(\'s_msStartRemindtime\',{d:0})}'})"/></td>
								          
								      </tr>
							      </table>
							</form>
						</div>
						<div region="center" border="false">
							<table id="summary"></table>
						</div>
					</div>
				</div>
				<div title="月结明细" style="width: 800px; height: 600px;" border="false">
					<div class="easyui-layout" border="false" fit="true"
						style="width: 800px; height: 600px;">
						<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:74px;" border="false">
							<form id="form_details1" method="post">
							    <table>
							         <tr>
							              <td>本次月结开始时间：</td>
							              <td><input id="msStarttime" name="msStarttime" type="text" readonly="readonly" style="background-color:#c0d8d8;width:170px;"/>
				                          </td>
							              <td>本次月结结束时间：</td>
							              <td>
							                  <input id="msEndtime" name="msEndtime" type="text" style="width:170px;" class="Wdate"
							                        onclick="WdatePicker({onpicked:function(dp){getRemindTime(dp);},dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:true,errDealMode:1,minDate:'#F{$dp.$D(\'msStarttime\',{d:0})}'})"
													data-options="
													disabled:true,
													required : true,
													editable : false,
													missingMessage: '接待日期为必填' " />
							              </td>
							              <td>下次月结提示时间：</td>
							              <td><input id="msNexttime" name="msNexttime" type="text" readonly="readonly" style="background-color:#c0d8d8;width:170px;"/></td>
							         </tr>
							         <tr>
							              <td>反月结提示时间：</td>
							              <td><input id="msRemindtime" name="msRemindtime" type="text" readonly="readonly" style="background-color:#c0d8d8;width:170px;"/></td>
							              <td>月结备注：</td>
							              <td colspan="3">
							                  <textarea id="msRemark" name="msRemark" type="text" style="width:460px; height:40px; resize : none" ></textarea>
							                  <input type="hidden" id="msId" name="msId" style="width:140px;"/>
							              </td>
							         </tr>
						        </table>
							</form>
						</div>
						<div region="center" border="false">
							<table id="details1"></table>
						</div>
					</div>
				</div>
		    </div>
        </div>
          </div>
</body>
</html>