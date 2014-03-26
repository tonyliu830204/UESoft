<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%
	String tag = request.getParameter("tag");
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>

<html>
	<head>
		
		<title>客户关怀中心</title>
		<script type="text/javascript">
		     var tag = <%=tag %>;
		     //保养提醒
		     var maintenance='<%=Contstants.VISITGROPNAME.MAINTENREMINDER%>';
		     //年检年审
		     var annual='<%=Contstants.VISITGROPNAME.ANNUALREMAINDER%>';
		    //首保
		     var first='<%=Contstants.VISITGROPNAME.FIRSTMAINREMAINDER%>';
		     //保险交强险
		     var innsure='<%=Contstants.VISITGROPNAME.INNSUREREMINDER%>';
		     //生日
		     var birthr='<%=Contstants.VISITGROPNAME.BIRTHREMINDER%>';
		     //会员
		     var vipMaintenance='<%=Contstants.VISITGROPNAME.VIPREMINDER%>';
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/return_visit/customer_care.js"></script>
	</head>
	<body>
		
		<div class="easyui-layout" border="false" fit ="true" style="width : 980px ;height: 665px;" >
		
			<div region="north"  split="true" style="height:35px;background:#eee;" border="false">
			<!-- 		
				<privilege:enable code="CUSTOMCAREDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="">删除</a>
				</privilege:enable>
			 -->
				<privilege:enable code="CUSTOMCAREQUERY">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryRemain();">查询</a>
				</privilege:enable>
				<privilege:enable code="CUSTOMCARECLEAR">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
				</privilege:enable>
				<privilege:enable code="CUSTOMCAREPRINT">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
				</privilege:enable>
				<privilege:enable code="CUSTOMCAREEXPORT">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
				</privilege:enable>
				<privilege:enable code="CUSTOMCAREVISIT">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="dbReturnButton();">回访</a>
				</privilege:enable>
			</div>		
		<div region="center" border="false" >
		<div id="tab_id" class="easyui-tabs" style="width: 600px; height: 750px; padding: 0px;" border="false"
			fit="true">
			<div title="保养提醒" fit="true" border="false">
				<div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
				 	<div data-options="region:'center',border:false" style="background:#eee;">
					 	<div class="easyui-layout" data-options="fit:true,border:false"> 
								<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 60px;" border="false">
										<form id="maintenance_form_id" method="post">
											<table  style="text-align: right;">
												<tr>
													<td>保养日期：</td>
													<td>
														<input style="width: 100px;" id="carLastMaintDate" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'carLastMaintDate2\',{d:-1})}'})"   name="yjbydate" />至
														<input  style="width: 100px;" id="carLastMaintDate2"  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'carLastMaintDate\',{d:1})}'})"   name="yjbydate" />
													</td>
													<td>保养次数：</td>
													<td><input type="text" style="width:130px;"  name="by_Number" /></td>
													<td>车牌照：</td>
													<td ><input type="text" style="width:217px;"  name="car_License"  /></td>
													<td>底盘：</td>
													<td colspan="2"><input type="text" style="width:200px" name="car_Vin"  /></td>
												</tr>
											</table>
										</form>
								</div>
								<div region="center" style="background: #eee" border="false" >
										<table id="baoyang_rember_id"></table>
								</div>
					  	</div>
					</div>
				</div>
			</div>
			
			<div title="年检/年审提醒"  fit="true" border="false"> 
			   <div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
				 	<div data-options="region:'center',border:false" style="background:#eee;">
					 	<div class="easyui-layout" data-options="fit:true,border:false"> 
								<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 100px;" border="false">
										<form id="annual_form_id" method="post">
											<table  style="text-align: right;">
												<tr>
													<td>年检日期：</td>
													<td>
														<input style="width: 100px;" type="text" id="car_Annual_Date" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Annual_Date2\',{d:-1})}'})"   name="car_Annual_Date" />至
														<input  style="width: 100px;" type="text"  id="car_Annual_Date2" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Annual_Date\',{d:1})}'})"   name="car_Annual_Date" />
													</td>
													<td>年审日期：</td>
													<td>
														<input style="width: 100px;" type="text" id="car_Examined_Date" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Examined_Date2\',{d:-1})}'})"   name="car_Examined_Date" />至
														<input  style="width: 100px;"type="text"  id="car_Examined_Date2" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Examined_Date\',{d:1})}'})"   name="car_Examined_Date" />
													</td>
													<td>保养次数：</td>
													<td><input type="text" style="width:130px;"  name="by_Number" /></td>
													<td>车牌照：</td>
													<td ><input type="text" style="width:217px;"  name="car_License"  /></td>
													<td>底盘：</td>
													<td colspan="2"><input type="text" style="width:200px" name="car_Vin"  /></td>
												</tr>
												<tr>
													<td>购车日期：</td>
													<td>
														<input style="width: 100px;" type="text" class="Wdate" id="car_Buy_Date" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Buy_Date2\',{d:-1})}'})"   name="car_Buy_Date" />至
														<input  style="width: 100px;" type="text" class="Wdate" id="car_Buy_Date2" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Buy_Date\',{d:1})}'})"   name="car_Buy_Date" />
													</td>
												</tr>
											</table>
										</form>
								</div>
								<div region="center" style="background: #eee" border="false" >
										<table id="nianjian_nianshen_id"></table>
								</div>
					  	</div>
					</div>
			 </div>
			</div>
			
			<div title="首保提醒"  fit="true" border="false">
				 <div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
				 	<div data-options="region:'center',border:false" style="background:#eee;">
					 	<div class="easyui-layout" data-options="fit:true,border:false"> 
								<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 70px;" border="false">
										<form id="first_form_id" method="post">
											<table  style="text-align: right;">
												<tr>
													<td>保养日期：</td>
													<td>
														<input style="width: 100px;" id="first_MaintDate" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'first_MaintDate2\',{d:-1})}'})"   name="car_Fst_Insurance_Date" />至
														<input  style="width: 100px;"id="first_MaintDate2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'first_MaintDate\',{d:1})}'})"   name="car_Fst_Insurance_Date" />
													</td>
													<td>购车日期：</td>
													<td>
														<input style="width: 100px;"id="car_Buy_Date_id" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Buy_Date_id2\',{d:-1})}'})"   name="car_Buy_Date" />至
														<input  style="width: 100px;"id="car_Buy_Date_id2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Buy_Date_id\',{d:1})}'})"   name="car_Buy_Date" />
													</td>
													<td>保养次数：</td>
													<td><input type="text" style="width:130px;"  name="by_Number"  /></td>
													<td>车牌照：</td>
													<td ><input type="text" style="width:217px;"  name="car_License"  /></td>
													<td>底盘：</td>
													<td colspan="2"><input type="text" style="width:200px" name="car_Vin" /></td>
												</tr>
											</table>
										</form>
								</div>
								<div region="center" style="background: #eee" border="false" >
										<table id="customer_care_shoubao_tixing_id"></table>
								</div>
					  	</div>
					</div>
			   </div>
			</div>
			
			<div title="保险/交强提醒" fit="true" border="false">
				<div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
				 	<div data-options="region:'center',border:false" style="background:#eee;">
					 	<div class="easyui-layout" data-options="fit:true,border:false"> 
								<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 100px;" border="false">
										<form id="innsure_form_id" method="post">
											<table  style="text-align: right;">
												<tr>
													<td>商业险提醒：</td>
													<td>
														<input style="width: 100px;" id="car_Businsurance_Date" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Businsurance_Date2\',{d:-1})}'})"   name="car_Businsurance_Date" />至
														<input  style="width: 100px;"id="car_Businsurance_Date2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Businsurance_Date\',{d:1})}'})"   name="car_Businsurance_Date" />
													</td>
													<td>交强险提醒：</td>
													<td>
														<input style="width: 100px;" id="car_Basinsurance_Date" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_Basinsurance_Date2\',{d:-1})}'})"   name="car_Basinsurance_Date" />至
														<input  style="width: 100px;"id="car_Basinsurance_Date2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_Basinsurance_Date\',{d:1})}'})"   name="car_Basinsurance_Date" />
													</td>
													<td>保养次数：</td>
													<td><input type="text" style="width:130px;"  name="by_Number" /></td>
													<td>车牌照：</td>
													<td ><input type="text" style="width:217px;"  name="car_License"  /></td>
													<td>底盘：</td>
													<td colspan="2"><input type="text" style="width:200px" name="car_Vin"  /></td>
												<tr>
													<td>购车日期：</td>
													<td>
														<input style="width: 100px;"id="innsure_car_Buy_Date" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'innsure_car_Buy_Date2\',{d:-1})}'})"   name="car_Buy_Date" />至
														<input  style="width: 100px;"id="innsure_car_Buy_Date2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'innsure_car_Buy_Date\',{d:1})}'})"   name="car_Buy_Date" />
													</td>
												</tr>
											</table>
										</form>
								</div>
								<div region="center" style="background: #eee" border="false" >
										<table id="baoxian_jiaoqiang_id"></table>
								</div>
					  	</div>
					</div>
			   </div>
			</div>		
			<div title="生日提醒" fit="true" border="false">
				<div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
				 	<div data-options="region:'center',border:false" style="background:#eee;">
					 	<div class="easyui-layout" data-options="fit:true,border:false"> 
								<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 70px;" border="false">
										<form id="customBirth_form_id" method="post">
											<table  style="text-align: right;">
												<tr>
													<td>客户生日：</td>
													<td>
														<input style="width: 100px;"id="custom_Birthday" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'custom_Birthday2\',{d:-1})}'})"   name="custom_Birthday" />至
														<input  style="width: 100px;"id="custom_Birthday2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'custom_Birthday\',{d:1})}'})"   name="custom_Birthday" />
													</td>
													<td>购车日期：</td>
													<td>
														<input style="width: 100px;"id="birth_car_Buy_Date" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'birth_car_Buy_Date2\',{d:-1})}'})"   name="car_Buy_Date" />至
														<input  style="width: 100px;"id="birth_car_Buy_Date2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'birth_car_Buy_Date\',{d:1})}'})"   name="car_Buy_Date" />
													</td>
													<td>保养次数：</td>
													<td><input type="text" style="width:130px;"  name="by_Number" /></td>
													<td>车牌照：</td>
													<td ><input type="text" style="width:217px;"  name="car_License"  /></td>
													<td>底盘：</td>
													<td colspan="2"><input type="text" style="width:200px" name="car_Vin"  /></td>
												</tr>
											</table>
										</form>
								</div>
								<div region="center" style="background: #eee" border="false" >
										<table id="birthday_tixing_id"></table>
								</div>
					  	</div>
					</div>
			   </div>		
			</div>
	<!-- 
			<div title="会员到期提醒"  fit="true" border="false">
						<table id="vip_tixing_id"></table>
			</div>
		 -->
			<div title="提醒查询"  fit="true" border="false">
				<div class="easyui-layout" border="false" fit="true" style="width: 800px; height: 600px;">
					 <div data-options="region:'center',border:false" style="background:#eee;">
				 		<div class="easyui-layout" data-options="fit:true,border:false"> 
							<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 70px;" border="false">
								<form id="customcare_form_id" method="post">
									<table  style="text-align: right;">
										<tr>
											
											<td>跟踪日期：</td>
											<td>
												<input style="width: 100px;" id="genzongdaoqi"  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'genzongdaoqi2\',{d:-1})}'})"  name="return_Visit_Date"/>至
												<input style="width: 100px;" id="genzongdaoqi2"  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'genzongdaoqi\',{d:1})}'})"   name="return_Visit_Date"/>
											</td>
											<td>跟踪类别：</td>
											<td><input type="text"  id="group_Name_id" name="group_Name"  style="width:110px"  class="easyui-combobox" data-options="
												url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.VISITGROPNAME.VISITTYPENAME %>',
												valueField:'id',
												textField:'text',										
												validType:'isSelected[\'#group_Name_id\']',
									    		invalidMessage : '跟踪类别'
									    		 "/></td>
											<td>车牌照：</td>
											<td><input type="text" style="width:110px;"  name="car_License" id="carLicense" /></td>
											<td>跟踪结果：</td>
											<td><input type="text"  id="txResault_id" name="tx_Resault"  style="width: 150px;"  class="easyui-combobox" data-options="
												url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.REMAINDERESULT.TXRESAULT %>',
												valueField:'id',
												textField:'text',										
												validType:'isSelected[\'#txResault_id\']',
									    		invalidMessage : '请从流失去向'
									    		 "/></td>
											<td>流失原因：</td>
											<td><input type="text"  id="car_lost_id" name="car_lost"  style="width: 150px;" class="easyui-combobox" data-options="
												url:'${pageContext.request.contextPath}/${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.CARLOST.CARLOST %>',
												valueField:'id',
												textField:'text',										
												validType:'isSelected[\'#car_lost_id\']',
									    		invalidMessage : '请从流失去向'
									    		 "/></td>
										</tr>
									</table>
								</form>
							</div>
							<div region="center" border="false" style="background:#eee">
								<table id="rember_find_id"></table>
							</div>
							<div region="east" border="false" style="width:250px;background:#eee">
								<table id="rember_find_east_id"></table>
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
