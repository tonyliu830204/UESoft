<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>车辆档案查询</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtCarArchives.js"></script>    
  </head>
  <body>
	<div class="easyui-layout" fit="true" border="false">  
       <div region="north" style="padding:3px;background:#eee;height:32px;" border="false">
       <privilege:enable code="CARSEARCH_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="search" iconCls="icon-search" plain="true" onclick="searchFc();">查询</a>
	   </privilege:enable>
       <privilege:enable code="CARSEARCH_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="clear" iconCls="icon-cancel" plain="true" onclick="clearFc();">清空</a>
	   </privilege:enable>
	   <privilege:enable code="CARSEARCH_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="_export" iconCls="icon-export" plain="true">Excel导出</a>
	   </privilege:enable>
       </div>
       <div region="center" style="background:#eee;">	
	<div class="easyui-layout" fit="true" border="false" >
	    <div region="north" title="查询条件" style="overflow: hidden;background:#eee;height:180px;" border="false">
				<div id="temp" class="easyui-tabs" fit="true" border="false" style="background:#eee;">
			 		<div title="条件一">
			 			<form id="carArchives1Form">
			 			<table  style="width:1100px;">
							<tr>
								<td>分析类型:</td>
								<td>
									<input type="text"  style="width:140px;" id="carArchives_analyseWay" name="analyseWay" class="easyui-combobox" data-options="
										editable:false,
										data:[{'id':'aa','text':'客户区域'},{'id':'bb','text':'车辆品牌'},{'id':'cc','text':'车辆型号'}],
				   						valueField:'id',  
				   					    textField:'text'"/>
								</td>
								<td>车牌照:</td>
								<td><input type="text" id="carArchives_carLicense" name="carLicense"  style="width:140px;" class="easyui-combobox" data-options="
									url : 'frtOptionsAction!findAllCarLicenseAsCarLicense.action',
									valueField : 'id',
									textField : 'text',
									mode : 'remote' "/></td>
								<td>VIN号:</td>
								<td><input type="text" name="carVin"  style="width:140px;"/></td>
								<td>发动机号:</td>
								<td><input type="text" name="carMotorId"  style="width:140px;"/></td>
								<td>客户区域:</td>
								<td><input type="text" id="carArchives_pareaId"  style="width:140px;" name="pareaId" class="easyui-combobox" data-options="
									url:'${pageContext.request.contextPath}/frtOptionsAction!findCustomArea.action',   
								    valueField:'id',   
								    textField:'text',
								    mode : 'remote',
								    validType:'isSelected[\'#carArchives_pareaId\']',
		    						invalidMessage : '请从下拉框中选择客户区域' "/></td>
							</tr>
							<tr>
								<td>车辆品牌:</td>
								<td><input type="text" id="carArchives_cbrdId" name="cbrdId"   style="width:140px;" class="easyui-combobox" data-options="
									url:'${pageContext.request.contextPath}/frtOptionsAction!findCarBrand.action',
									valueField:'id',   
						    		textField:'text',
						    		mode:'remote',
						    		validType:'isSelected[\'#carArchives_cbrdId\']',
						    		invalidMessage : '请从下拉框中选择车辆品牌',
						    		onSelect: function(rec){  
							            $('#carArchives_ctypeId').combobox('clear');
							            $('#carArchives_carCstlName').combobox('clear');   
							            $('#carArchives_ctypeId').combobox('reload', 'frtOptionsAction!findCarType.action?cbrdId=' + rec.id)  
							        } "/></td>
									<td>车辆型号:</td>
									<td><input type="text" id="carArchives_ctypeId" name="ctypeId"  style="width:140px;" class="easyui-combobox" data-options="
									url:'${pageContext.request.contextPath}/frtOptionsAction!findCarType.action',
									valueField:'id',   
						    		textField:'text',
						    		mode:'remote',
						    		validType:'isSelected[\'#carArchives_ctypeId\']',
						    		invalidMessage : '请从下拉框中选择车辆型号',
						    		onSelect:function(rec){  
							            $('#carArchives_carCstlName').combobox('clear');  
							            $('#carArchives_carCstlName').combobox('reload', 'frtOptionsAction!findCarStyle.action?ctypeId=' + rec.id);  
							        } "/></td>
									<td>车辆款式:</td>
									<td><input type="text" id="carArchives_carCstlName" name="carCstlName"  style="width:140px;" class="easyui-combobox" data-options="
									url:'${pageContext.request.contextPath}/frtOptionsAction!findCarStyle.action',
									valueField:'id',   
						    		textField:'text',
						    		validType:'isSelected[\'#carArchives_carCstlName\']',
						    		invalidMessage : '请从下拉框中选择车辆款式',
						    		mode:'remote' "/></td>
		    						<td>客户名称:</td>
									<td colspan="3">
										<input type="text" name="customName"  style="width:376px;"/>
									</td>
							</tr>
						</table>
						<table style="width:1100px;">
							<tr>
								<td>领证日期:</td>
								<td>
								<input type="text" id="carLicenseDateBegin" name="carLicenseDateBegin" class="Wdate"  style="width:140px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'carLicenseDateEnd\')}'});"/>
								~<input type="text" id="carLicenseDateEnd" name="carLicenseDateEnd" class="Wdate"  style="width:140px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'carLicenseDateBegin\')}'});"/></td>
								<td>交强险期:</td>
								<td>
								<input type="text" id="carBasinsuranceDateBegin" name="carBasinsuranceDateBegin"   style="width:140px;"class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'carBasinsuranceDateEnd\')}'});"/>
								~<input type="text" id="carBasinsuranceDateEnd" name="carBasinsuranceDateEnd" class="Wdate"   style="width:140px;"onfocus="WdatePicker({minDate:'#F{$dp.$D(\'carBasinsuranceDateBegin\')}'});"/></td>
								<td>商业险期:</td>
								<td>
								<input type="text" id="carBusinsuranceDateBegin" name="carBusinsuranceDateBegin"  style="width:140px;" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'carBusinsuranceDateEnd\')}'});"/>
								~<input type="text" id="carBusinsuranceDateEnd" name="carBusinsuranceDateEnd" class="Wdate"  style="width:140px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'carBusinsuranceDateBegin\')}'});"/></td>
							</tr>
							<tr>
								<td>购车日期:</td>
								<td>
								<input type="text" id="carBuyDateBegin" name="carBuyDateBegin" class="Wdate"  style="width:140px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'carBuyDateEnd\')}'});"/>
								~<input type="text" id="carBuyDateEnd" name="carBuyDateEnd" class="Wdate"  style="width:140px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'carBuyDateBegin\')}'});"/></td>
								<td>首保日期:</td>
								<td>
								<input type="text" id="carFstInsuranceDateBegin" name="carFstInsuranceDateBegin"  style="width:140px;" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'carFstInsuranceDateEnd\')}'});"/>
								~<input type="text" id="carFstInsuranceDateEnd" name="carFstInsuranceDateEnd" class="Wdate"  style="width:140px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'carFstInsuranceDateBegin\')}'});"/></td>
								<td>最后维修日:</td>
								<td>
								<input type="text" id="carLastRepairDateBegin" name="carLastRepairDateBegin"  style="width:140px;" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'carLastRepairDateEnd\')}'});"/>
								~<input type="text" id="carLastRepairDateEnd" name="carLastRepairDateEnd" class="Wdate"  style="width:140px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'carLastRepairDateBegin\')}'});"/></td>
							</tr>
						</table>
						</form>
			 		</div>
			 		<div title="条件二" >
			 			<form id="carArchives2Form">
			 			<table  style="width:1100px;" >
			 				<tr>
								<td>会员号:</td>
								<td><input type="text" id="carArchives_vipId" name="vipId"  style="width:140px;"/></td>
								<td>会员生日:</td>
								<td>
									<input type="text" id="carArchives_vipBirthDay" name="vipBirthDay"  style="width:140px;" class="Wdate" onfocus="WdatePicker({format:'yyyy-MM-dd'});"/>
								</td>
								
								<td>经销商:</td>
								<td><input type="text" id="carArchives_slsId" name="slsId"  style="width:140px;" class="easyui-combobox" data-options="
									url:'${pageContext.request.contextPath}/frtOptionsAction!findCarSellers.action',
									valueField:'id',   
						    		textField:'text',
						    		validType:'isSelected[\'#carArchives_slsId\']',
						    		invalidMessage : '请从下拉框中选择经销商',
						    		mode:'remote' "/></td>
						    	<td>维修次数:</td>
								<td><input type="text" id="carArchives_carRepairCnt" name="carRepairCnt"  style="width:140px;"/></td>
						    	<td>是否会员:</td>
								<td>
									<input type="checkbox" id="carArchives_isVip" value="true" name="isVip"  style="width:140px;"/>
								</td>	
							</tr>
							<tr>
								<td>客户性质:</td>
								<td>
								<input type="text" id="carArchives_natureId" name="natureId" class="easyui-combobox"  style="width:140px;" data-options="
									url:'${pageContext.request.contextPath}/frtOptionsAction!findCustomNature.action',   
								    valueField:'id',   
								    textField:'text',
								    mode : 'remote',
								    validType:'isSelected[\'#carArchives_natureId\']',
		    						invalidMessage : '请从下拉框中选择客户性质' " />
								</td>
								<td>客户类型:</td>
								<td>
									<input type="text" id="carArchives_cstId" name="cstId" class="easyui-combobox" style="width:140px;" data-options="
										url:'${pageContext.request.contextPath}/frtOptionsAction!findCustomType.action',   
									    valueField:'id',   
									    textField:'text',
									    mode : 'remote',
									    validType:'isSelected[\'#carArchives_cstId\']',
			    						invalidMessage : '请从下拉框中选择客户类型' "/>
								</td>
								<td>备注:</td>
								<td><input type="text" id="carArchives_carRemark" name="carRemark" style="width:140px;"/></td>
						    	<td>地址:</td>
								<td colspan="3">
									<input type="text" id="carArchives_customAddr" name="customAddr"  style="width:386px;"/>
								</td>
							</tr>
							</table>
							<table style="width:1100px;">
			 				<tr>
								<td>年检到期:</td>
								<td>
								<input type="text" id="carAnnualDateBegin" name="carAnnualDateBegin" class="Wdate"  style="width:140px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'carAnnualDateEnd\')}'});"/>
								~<input type="text" id="carAnnualDateEnd" name="carAnnualDateEnd" class="Wdate"  style="width:140px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'carAnnualDateBegin\')}'});"/></td>
								<td>年审到期:</td>
								<td>
								<input type="text" id="carExaminedDateBegin" name="carExaminedDateBegin" class="Wdate"  style="width:140px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'carExaminedDateEnd\')}'});"/>
								~<input type="text" id="carExaminedDateEnd" name="carExaminedDateEnd" class="Wdate"  style="width:140px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'carExaminedDateBegin\')}'});"/></td>
								<td>下次保养日:</td>
								<td>
								<input type="text" id="carNextMaintDateBegin" name="carNextMaintDateBegin" class="Wdate"  style="width:140px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'carNextMaintDateEnd\')}'});"/>
								~<input type="text" id="carNextMaintDateEnd" name="carNextMaintDateEnd" class="Wdate"  style="width:140px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'carNextMaintDateBegin\')}'});"/></td>
							</tr>
							<tr>
								<td>生产日期:</td>
								<td>
								<input type="text" id="carBirthdayBegin" name="carBirthdayBegin" class="Wdate"  style="width:140px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'carBirthdayEnd\')}'});"/>
								~<input type="text" id="carBirthdayEnd" name="carBirthdayEnd" class="Wdate"  style="width:140px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'carBirthdayBegin\')}'});"/></td>
								<td>创建日期:</td>
								<td>
								<input type="text" id="createDateBegin" name="createDateBegin" class="Wdate"  style="width:140px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'createDateEnd\')}'});"/>
								~<input type="text" id="createDateEnd" name="createDateEnd" class="Wdate"  style="width:140px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'createDateBegin\')}'});"/></td>
								<td>最后维修里程:</td>
								<td>
								<input type="text" id="lastDistanceBegin" name="lastDistanceBegin"  style="width:140px;"/>
								~<input type="text" id="lastDistanceEnd" name="lastDistanceEnd"  style="width:140px;"/>
								</td>
							</tr>
			 			</table>
			 			</form>
			 		</div>
				</div>
		</div>
	  
		<div region="center" style="background:#eee;" border="false">
			<div id="carArchivesTabs" class="easyui-tabs" fit="true" border="false" style="background:#eee;">
		 		<div id="carArchivesMain_center" title="主档案">
		 			<div class="easyui-layout" fit="true" border="false" >
		 				<div region="center" style="background:#eee;" border="false">
			 				<table id="carArchivesMain"></table>
			 			</div>
			 			<div id="carArchivesDetail_center" region="south"  title="维修记录" style="overflow: hidden;background:#eee;height:300px;" border="false">
			 				<table id="carArchivesDetail"></table>
			 			</div>
		 			</div>
		 		</div>
		 		<div id="carArchivesAnalyse_center" title="数据分析">
		 			<table id="carArchivesAnalyse"></table>
		 		</div>
			</div>
		</div>  
   </div>
   </div>
  </div>
  </body>
</html>
