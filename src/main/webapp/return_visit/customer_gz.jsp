<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.contstants.Contstants,com.syuesoft.model.BasUsers" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
		<title>跟踪管理</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/return_visit/customer_gz.js"></script>
	</head>
	<body>
		<div id="cc" class="easyui-layout" fit="true" border="false">   
		       <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
		        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCollect();">修改</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteCollect();">删除</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doFind();">查询</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear($('#CustomerGzManage_north_form_id'));">清空</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-help" plain="true">设置</a>
			    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addAdvice();">维修建议及提醒</a>
				<span id="saveOrCancelBtn"></span>
		       </div>  
		       <div region="center"  split="false" border="false">
				 <div id="tt" class="easyui-tabs" fit="true" border="false">  
					 <div title="类型列表" style="display:block;"  fit="true">
						<div id="cc" class="easyui-layout" style="width:600px;height:500px;" fit="true" border="false">							  				
							  <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
									<form id="CustomerGzManage_north_form_id" method="post">
										<table  style="text-align: right;">
										<tr>
												<td >结算日期：</td>
												<td >
													<input id="txtStartDate" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'txtEndDate\',{d:-1})}'})" editable="false" name="preclrTime" style="width:85px;"/>至
													<input  id="txtEndDate" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'txtStartDate\',{d:1})}'})" editable="false" name="preclrTime" style="width:85px;"/>
												</td>
												<td >前台接待：</td>
												<td>
													<input name="receptor"
														class="easyui-combobox"
															data-options="
															url : 'carInsuranceManageAction_getBasStuff.action',
															valueField:'id',  
															textField:'name',
															multiple:false  "
															/>
												</td>
												<td>客户名称</td>
												<td><input type="text" name="customName"/></td>
												<td>维修类别</td>
												<td>
													<input style="width:110px" name="reptName"
														class="easyui-combobox"
														data-options="
														url : 'customerGzManageAction_getBasRepairTypeName.action',
														valueField:'id',  
														textField:'name',
														multiple:false  "
														/>
												</td>
												<td >满意度：</td>
												<td>
												<input style="width:110px" name=""
													class="easyui-combobox"
													data-options="
													url : 'frtOptionsAction!findBaseListData.action\?key=content',
													valueField:'id',  
													textField:'text' "
													/>
												</td>
												<td>通话情况：</td>
												<td>
													<input name="callSituation"
														class="easyui-combobox"
														data-options="url : 'frtOptionsAction!findBaseListData.action\?key=callState',
														valueField:'id',  
														textField:'text' "
														/>
												</td>
											</tr>
											<tr>
												<td >回访日期：</td>
												<td >
													<input id="returnVisitDate" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'returnVisitDate2\',{d:-1})}'})" editable="false" name="returnVisitDate" style="width:85px;"/>至
													<input id="returnVisitDate2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'returnVisitDate\',{d:1})}'})" editable="false" name="returnVisitDate" style="width:85px;"/>
												</td>
												<td >接车分部：</td>
												<td>
													<input name="dept_Name"
														class="easyui-combobox"
														data-options="url : 'carInsuranceManageAction_getbasDept.action',
														valueField:'id',  
														textField:'name',
														multiple:false  "
														/>
												</td>
												<td >车辆品牌：</td>
												<td >
													<input name ="cbrd_Name"
														class="easyui-combobox"
														data-options="
														url : 'vTrackRecordAction_getCarbrand.action',
														valueField:'id',  
														textField:'name',
														multiple:false  "
														/>
												</td>	
												<td >车牌照：</td>
												<td>
													<input type="text" name="carLicense"/>
												</td>
												<td >投诉情况：</td>
												<td >
													<input style="width:110px" name="complaintQK"
														class="easyui-combobox"
														data-options="
														url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=tsqk',
														valueField:'id',  
														textField:'text' "
														/>
												</td>
												<td>备注：</td>
												<td>
													<input type="text" name="memo"/>
												</td>
												
										</tr>
									</table>									
									</form>
							  </div>  										  
							  <div id="leixin" region="center" style="background:#eee;" border="false">
								<table id="customer_genz1"></table>
							  </div> 		
						</div>
					 </div>
					 <div title="类型明细" style="display:block;" closable="false"  fit="true">	
						<div id="cc" class="easyui-layout" style="width:600px;height:500px;" fit="true" > 
							  <div region="north" title="" split="false" style="overflow: hidden;background:#eee;height:125px;" border="false">  
									<form id="CustomerGzManage_form_south_id">
												<input type="hidden" name="collectId"/>
												<input type="hidden" name="preclrId"/>
												<input type="hidden" name="detailId"/>
												<table >
													<tr>
														<td>工单号：</td>
														<td><input type="text" name="receptionId" disabled="disabled" readonly="readonly" style="width : 150px;color: #ff0000"/>&nbsp;&nbsp;</td>
														<td>接收回访：</td>
														<td><input type="text" style="background-color: #c0d8d8;width : 150px" id="reciptReturnvisit_id" name="reciptReturnvisit"   class="easyui-combobox" data-options="
																url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.RECIPTVISIT.RECIPTRETURNVISIT %>',
																valueField:'id',
																textField:'text',										
																validType:'isSelected[\'#reciptReturnvisit_id\']',
													    		invalidMessage : '请从下拉框中选择接收回访'
													    		 "/></td>
														<td>客户名称：</td>
														<td><input type="text" name="customName"  style="width : 150px;"  disabled="disabled" readonly="readonly"/>&nbsp;&nbsp;</td>
														<td >手机号：</td>
														<td><input type="text" name="customTel1"  style="width : 150px;" disabled="disabled" readonly="readonly"/>&nbsp;&nbsp;</td>
													</tr>
													<tr>
														<td >车牌照：</td>
														<td ><input type="text" name="carLicense" disabled="disabled" readonly="readonly" style="width:150px; color: #ff0000"/>&nbsp;&nbsp;</td>
														<td>通话情况：</td>
														<td><input type="text" style="background-color: #c0d8d8;width : 150px" id="callSituation_id" name="callSituation"   class="easyui-combobox" data-options="
																url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.callCondition.CALLSITUATION %>',
																valueField:'id',
																textField:'text',										
																validType:'isSelected[\'#callSituation_id\']',
													    		invalidMessage : '请从下拉框中选择通话情况'
													    		 "/></td>
														<td >托修人：</td>
														<td ><input type="text" style="width : 150px;"  name="propRepPer" disabled="disabled" readonly="readonly"/>&nbsp;&nbsp;</td>
														<td >电话：</td>
														<td><input type="text"  style="width : 150px;" name="propPhone" disabled="disabled" readonly="readonly"/>&nbsp;&nbsp;</td>
													</tr>
													<tr>
														<td>维修班组：</td>
														<td><input  style="width : 150px;"  type="text" name="pergrpId" disabled="disabled" readonly="readonly"/>&nbsp;&nbsp;</td>
														<td>满意程度：</td>
														<td><input type="text" style="background-color: #c0d8d8;width : 150px" id="satisfaction_id" name="satisfaction"   class="easyui-combobox" data-options="
																url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.SATISFACTION.SATISFACTIONDEGREE %>',
																valueField:'id',
																textField:'text',										
																validType:'isSelected[\'#satisfaction_id\']',
													    		invalidMessage : '请从下拉框中选择满意程度'
													    		 "/></td>
														<td >性别：</td>
														<td ><input type="text" style="background-color: #c0d8d8;" id="customSex_id" name="customSex" class="easyui-combobox" data-options="
															url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.SEXTYPE.SEX %>',
															valueField:'id',
															textField:'text',
															validType:'isSelected[\'#customSex_id\']',
												    		invalidMessage : '请从下拉框中选择性别'
												    		 "/></td>
														<td>满意评价：</td>
														<td><input type="text" name=""  style="width:150px;" />&nbsp;&nbsp;</td>
													</tr>
													<tr>
														<td>回访日期：</td>
														<td><input id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()"  style="width : 150px;"  editable="false" name="returnVisitDate" />&nbsp;&nbsp;</td>
														<td >不满意处理员：</td>
														<td><input type="text" id="handlePerson_id" name="handlePerson"class="easyui-combobox"
															 style="width: 150px;" 
															value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
															data-options="url:'${pageContext.request.contextPath}/${pageContext.request.contextPath }/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
															valueField:'id',  
															textField:'text' "/></td>
														<td >回访员：</td>
														<td ><input type="text" style="width : 150px;"  name="returnVisitMembers" />&nbsp;&nbsp;</td>
														<td >联系人：</td>
														<td><input type="text" name="receptionRepPer" style="width : 150px;"  disabled="disabled" readonly="readonly"/>&nbsp;&nbsp;</td>
													</tr>
												
												</table>
											</form>				
							  </div>
							 <div region="center" split="false" border="false" style="height: 90px; background : #eee">
									<div class="easyui-tabs" id="tabsId" style="width: 800px; height: 600px;" fit="true" border="false">
											<div title="工单维修明细" iconCls="icon-reload" border="false">
												<div class="easyui-layout" border="false" fit="true" style="width: 800px; height: 600px;">
													<div region="center" border="false" style=" background: #eee">
														<form id="GD_WeiXiu_detail_form_id">
																<table border="0">
																	<tr>
																		<td>维修内容</td><td>结算备注</td><td>故障描述</td>
																		</tr>
																		<tr>
																		<td><textarea style="width:300px;height:300px;"rows="20" cols="20" name="rcptitemName" disabled="disabled"></textarea></td>
																		<td><textarea style="width:300px;height:300px;"rows="20" cols="20" name="preclrRemark" disabled="disabled"></textarea></td>
																		<td><textarea style="width:300px;height:300px;"rows="20" cols="20" name="problemDesc" disabled="disabled"></textarea></td>
																	</tr>
																</table>
														</form>	
													</div>
												</div>
											</div>
											<div title="用户信息反馈及建议" iconCls="icon-reload">
												<div class="easyui-layout" border="false" fit="true" border="false"style="width: 800px; height: 600px;">
													<div region="center" border="false" style=" background: #eee">
														<form id="userReturn_infomation_form_id">
																<table border="0" style="text-align: right;">
																	<tr>
																		<td>用户信息反馈</td><td>备注</td><td>处理方案</td><td>维修建议</td>
																		</tr>
																		<tr>
																		<td><textarea style="width:300px;height:300px;"rows="20" cols="20" name="" ></textarea></td>
																		<td><textarea style="width:300px;height:300px;"rows="20" cols="20" name="" ></textarea></td>
																		<td><textarea style="width:300px;height:300px;"rows="20" cols="20" name="handleProgram" ></textarea></td>
																		<td><textarea style="width:300px;height:300px;"rows="20" cols="20" name="serviceProposal"></textarea></td>
																	</tr>
															   </table>
														</form>	
													</div>
												</div>
										</div>
										
										<div title="投诉及处理结果" iconCls="icon-reload">
												<div class="easyui-layout" border="false" fit="true" border="false"style="width: 800px; height: 600px;">
													<div region="center" border="false" style=" background: #eee">
														<form id="GD_WeiXiu_detail_form">
															<table >
																<tr>
																	<td style="width: 150px">投诉类型</td>
																	<td colspan="2"><input type="text" style="background-color: #c0d8d8;width: 300px" id="satisfaction_id" name="complaintType"   class="easyui-combobox" data-options="
																	url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.complaintStyle.COMPLAINTTYPE %>',
																	valueField:'id',
																	textField:'text',										
																	validType:'isSelected[\'#satisfaction_id\']',
														    		invalidMessage : '请从下拉框中选择投诉类型'
														    		 "/></td>
																	<td style="width: 150px">投诉程度</td>
														            <td colspan="2"><input type="text" style="background-color: #c0d8d8;width: 300px" id="satisfaction_id" name="complaintDegree"   class="easyui-combobox" data-options="
																	url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.compalinDegree.COMPALINDEGREE %>',
																	valueField:'id',
																	textField:'text',										
																	validType:'isSelected[\'#satisfaction_id\']',
														    		invalidMessage : '请从下拉框中选择投诉程度'
														    		 "/></td>							     
																</tr>
																<tr>
																	<td style="width: 150px">投诉责任人</td>
																	<td colspan="5"><textarea style="width: 765px;height:50px;"rows="20" cols="20" name="" ></textarea></td>
																</tr>
																<tr>
																	<td style="width: 150px">客户投诉内容及要求</td>
																	<td colspan="5"><textarea style="width: 765px;height:100px;"rows="20" cols="20" name="complaintContent" ></textarea></td>
																</tr>
																<tr>
																	<td style="width: 150px">投诉处理过程及结果</td>
																	<td colspan="5"><textarea style="width: 765px;height:100px;"rows="20" cols="20" name="handleResult" ></textarea></td>
																</tr>
															</table>
														</form>	
													</div>
												</div>
										</div>
										
										<div title="历史满意度" iconCls="icon-reload" border="false">
											<div class="easyui-layout" border="false" fit="true"style="width: 800px; height: 600px;">
												<div region="center" border="false">
													<table id="customer_genz2"></table>
												</div>
											</div>
									   </div>
							
									   <div title="3DC调查" iconCls="icon-reload">
											<div class="easyui-layout" border="false" fit="true" style="width: 800px; height: 600px;">
												<div region="center" border="false">
												    <table id="customer_genz3"></table>
												</div>
											</div>
									   </div>
									</div>  	   
						</div>
		            </div>
		        </div>	
        	</div>
      	 </div>
      </div>		
	</body>
</html>