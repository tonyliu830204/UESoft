<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants,com.syuesoft.model.BasUsers" %>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>交车结算</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtPreClearingGathering.js"></script>
  </head>
 <div style="padding:3px; height:32px; background:#eee;" data-options="region:'north',border:false">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="_remove();">删除</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit();">修改</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query();">查询</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清除</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
	<span id="button"></span>
 </div>
<div class="easyui-layout" data-options="fit:true,border:false">
     		<div style="overflow: hidden;background: #eee; height: 110px;" data-options="region:'north',title:'结算信息',border:false">
		<form id="frtReceptionAddForm">
			<table>
    				<tr>
    					<td>客户名称:</td>
    					<td><input type="text" id="frtRcptCustomId" name="customId" class="easyui-combobox"
						data-options="
						required : true,
						editable : false,
						url : 'frtOptionsAction!findAllCustom.action',
						missingMessage:'客户名称为必填项',
						valueField:'id',  
						textField:'text' "/></td>
						<td>工单号:</td>
    					<td><input type="text" name="carVin"/></td>
    					<td><span id="button2"></span>车牌照:</td>
    					<td><input type="text" name="carLicense" class="easyui-validatebox" readonly="readonly"
        			data-options=" disabled:true"/></td>
    					<td>会员号:</td>
    					<td><input type="text" name="carVin"/></td>
    					<td>会员类别:</td>
    					<td><input type="text" name="reptId" class="easyui-combobox"
						data-options="
						required : true,
						editable : false,
						url : 'frtReceptionAction!findAllReptype.action',
						missingMessage:'维修类别为必填项',
						valueField:'id',  
						textField:'text' "/></td>
    				</tr>
    				<tr>
    					<td>结算金额:</td>
    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount" readonly="readonly"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true "/></td> 
						<td>票据类型:</td>
    					<td><input type="text" name="preclrInoiceType" class="easyui-combobox"
	        			data-options="
	        			required : true,
	        			url : 'frtOptionsAction!findBaseListData.action?key=billType', 
	        			missingMessage : '票据类型为必填项',
	        			valueField : 'id',
	        			textField : 'text' "/></td>
    					<td>付款性质:</td>
    					<td><input type="text" id="frtRcptCarId" name="carId" class="easyui-combobox"
						data-options="
						url : 'frtReceptionAction!findAllCarLicense.action',
						valueField : 'id',
						textField : 'text',
						mode : 'remote'"/></td>
    					<td>结算单号:</td>
    					<td><input type="text" name="carVin"/></td>
    					<td>结算日期:</td>
    					<td><input type="text" name="resvRealTime" style="width: 140px;" 
						value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd HH:mm:ss'/>"
						class="easyui-datetimebox" style="width: 140px;"
					data-options="  editable : false "/>
						</td>
    				</tr>
    				<tr>
    					<td>累计收款:</td>
    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount" readonly="readonly"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true "/></td> 
						<td>收款经办:</td>
    					<td><input type="text" name="receptor" class="easyui-combobox"
						value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
						data-options="
						disabled:true,
						url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						valueField:'id',  
						textField:'text' "/></td>
    					<td>收款审核日期:</td>
    					<td><input type="text" name="resvRealTime" style="width: 140px;" 
						value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd HH:mm:ss'/>"
						class="easyui-datetimebox" style="width: 140px;"
					data-options="  editable : false "/>
						</td>
    					<td>实付金额:</td>
    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount" readonly="readonly"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true "/></td> 
    					<td>维修类别:</td>
    					<td><input type="text" name="reptId" class="easyui-combobox"
						data-options="
						required : true,
						editable : false,
						url : 'frtReceptionAction!findAllReptype.action',
						missingMessage:'维修类别为必填项',
						valueField:'id',  
						textField:'text' "/></td>
    				</tr>
    			</table>
		</form>
	</div>
	
	<div style="background: #eee;" data-options="region:'center',border:false">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div style="overflow: hidden;background: #eee; height: 240px;" data-options="region:'north',title:'积分信息',border:false">
					<form id="frtReceptionAddForm">
					<table>
						<tr>
							<td>
								<table>
				    				<tr>
				    					<td>收款单号:</td>
				    					<td><input type="text" id="frtRcptCustomId" name="customId"/></td>
										<td>未收清:</td>
				    					<td><input type="checkbox" name="carVin"/></td>
				    				</tr>
				    				<tr>
				    					<td>收款日期:</td>
				    					<td><input type="text" name="resvRealTime" style="width: 140px;" 
										value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd HH:mm:ss'/>"
										class="easyui-datetimebox" style="width: 140px;"
									data-options="  editable : false "/>
										</td>
				    				</tr>
				    				<tr>
				    					<td>收款人:</td>
				    					<td><input type="text" name="stfId" class="easyui-combobox"
											value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
											data-options="
											url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
											required : true,
											editable : false,
											missingMessage : '接待员为必填项', 
											valueField:'id',  
											textField:'text' " />
										</td>
				    				</tr>
				    				<tr>
				    					<td>储备金余额:</td>
				    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount" readonly="readonly"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true "/></td> 
				    				</tr>
				    				<tr>
				    					<td>储备金使用:</td>
				    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:','"/></td> 
				    				</tr>
				    				<tr>
				    					<td>本次收款:</td>
				    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:','"/></td> 
				    				</tr>
				    				<tr>
				    					<td>欠付金额:</td>
				    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount" readonly="readonly"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true "/></td> 
				    				</tr>
				    				<tr>
				    					<td>备注:</td>
				    					<td><input type="text" id="frtRcptCustomId" name="customId" class="easyui-combobox"/></td>
				    				</tr>
				    			</table>
							</td>
							<td>
								<table>
				    				<tr>
				    					<td>本次结算积分:</td>
				    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount" readonly="readonly"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true "/></td> 
										<td>消费项目:</td>
				    				</tr>
				    				<tr>
				    					<td>本次最终积分:</td>
				    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount" readonly="readonly"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true "/></td> 
				    					<td rowspan="3">
				    						<textarea rows="" cols="" name="resvRemark"
							style="width: 180px; height: 46px;"></textarea>
				    					</td>
				    				</tr>
				    				<tr>
				    					<td>可使用积分:</td>
				    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount" readonly="readonly"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true "/></td> 
				    				</tr>
				    				<tr>
				    					<td>消费使用积分:</td>
				    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount" readonly="readonly"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true "/></td> 
				    				</tr>
				    				<tr>
				    					<td>兑换现金积分:</td>
				    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',' "/></td> 
				    					<td rowspan="3">
				    						<textarea rows="" cols="" name="resvRemark"
							style="width: 180px; height: 46px;"></textarea>
				    					</td>
				    				</tr>
				    				<tr>
				    					<td>兑换现金:</td>
				    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount" readonly="readonly"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true "/></td> 
				    				</tr>
				    				<tr>
				    					<td>兑换金额比例:</td>
				    					<td><input type="text" id="preclrSumAmount" name="preclrSumAmount" readonly="readonly"
        				 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true "/></td> 
				    				</tr>
				    			</table>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div style="background: #eee;" data-options="region:'center',border:false">
		     	<table id="frtReceptionAdviceDatagrid"></table>
			</div>
		</div>
	</div>
</div>

</html>