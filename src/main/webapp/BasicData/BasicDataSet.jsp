<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>基本数据设置</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/BasicData/BasicData.js"></script>
  </head>
  <body>
	        <div id="cc" class="easyui-layout" border="false" style="width:600px;height:400px;" fit="true" border="false">  
			    <div region="west" split="false"  style="width:180px;" border="true">
			      <div class="easyui-accordion" animate="false" style="width: 180px; height: 600px;" fit="true" border="false">
			          <privilege:enable code="CARNATURE">
				          <div title="车辆性质" iconCls="icon-folder">
				              <ul>
				                   <privilege:enable code="CARBRAND">
					                   <li>
											<a name="icona" href="javascript:void(0);"
											    class="easyui-linkbutton" iconName="CARBRAND"  iconCls="icon-CARBRAND"
											    onclick="openURL('${pageContext.request.contextPath}/BasicData/CarProperties/Carbrand.jsp');" 
											    plain="true" ><span>车辆品牌</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CARMODEL">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											   class="easyui-linkbutton" iconName="CARMODEL"  iconCls="icon-CARMODEL"
											   onclick="openURL('${pageContext.request.contextPath}/base/carTypeInfo.jsp');" 
											   plain="true"><span>车辆型号</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CARSTYLE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											   class="easyui-linkbutton" iconName="CARSTYLE"  iconCls="icon-CARSTYLE"
											   onclick="openURL('${pageContext.request.contextPath}/BasicData/CarProperties/CarStyle.jsp');" 
											   plain="true"><span>车辆款式</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CARCOLOR">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CARCOLOR"  iconCls="icon-CARCOLOR" 
											onclick="openURL('${pageContext.request.contextPath}/BasicData/CarProperties/CarColor.jsp');" 
											plain="true"><span>车身颜色</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CARTYPE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CARTYPE"  iconCls="icon-CARTYPE" 
											onclick="openURL('${pageContext.request.contextPath}/BasicData/CarProperties/carType.jsp');" 
											plain="true"><span>驾照类型</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CARSTATE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CARSTATE"  iconCls="icon-CARSTATE"  
											onclick="openURL('${pageContext.request.contextPath}/BasicData/CarProperties/CarState.jsp');" 
											plain="true"><span>车身状态</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CARPARTS">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CARPARTS"  iconCls="icon-CARPARTS" 
											onclick="openURL('${pageContext.request.contextPath}/BasicData/CarProperties/CarParts.jsp');" 
											plain="true"><span>车辆部位</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="PHONETRACKENRESULT">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="PHONETRACKENRESULT"  iconCls="icon-PHONETRACKENRESULT" 
											onclick="openURL('${pageContext.request.contextPath}/BasicData/CarProperties/PhoneTrackerResult.jsp');" 
											plain="true"><span>电话跟踪结果</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CARLOSS">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CARLOSS"  iconCls="icon-CARLOSS"
											onclick="openURL('${pageContext.request.contextPath}/BasicData/CarProperties/CarLoss.jsp');" 
											plain="true"><span>流失去向</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="OTHERSTORE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="OTHERSTORE"  iconCls="icon-OTHERSTORE"
											onclick="openURL('${pageContext.request.contextPath}/BasicData/CarProperties/OtherStore.jsp');" 
											plain="true"><span>其他销售店</span></a>
									   </li>
								   </privilege:enable>
				              </ul>
				          </div>
			          </privilege:enable>
			          <privilege:enable code="MENBERNATURE">
				          <div title="会员性质" iconCls="icon-folder">
				              <ul>
				                   <privilege:enable code="ACTIVITIESDISCOUNT">
					                   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="ACTIVITIESDISCOUNT"  iconCls="icon-ACTIVITIESDISCOUNT"
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/Activities_discount.jsp');" 
											plain="true"><span>活动折扣</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="ACTINITIESPROJECT">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="ACTINITIESPROJECT"  iconCls="icon-ACTINITIESPROJECT" 
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/Vip_activities_project.jsp');" 
											plain="true"><span>会员活动项目</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="INTEGRALPROJECT">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="INTEGRALPROJECT"  iconCls="icon-INTEGRALPROJECT" 
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/Vip_give_integral_project.jsp');" 
											plain="true"><span>积分赠送项目</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="GIVEPROJECT">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="GIVEPROJECT"  iconCls="icon-GIVEPROJECT" 
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/Vip_give_project.jsp');" 
											plain="true"><span>会员赠送项目</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="MENBERGROUP">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="MENBERGROUP"  iconCls="icon-MENBERGROUP" 
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/Vip_group_management.jsp');" 
											plain="true"><span>会员分组</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="MENBERLEVEL">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="MENBERLEVEL"  iconCls="icon-MENBERLEVEL" 
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/Vip_level_management.jsp');" 
											plain="true"><span>会员等级</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="MENBERREGULATION">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="MENBERREGULATION"  iconCls="icon-MENBERREGULATION" 
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/Vip_recharge_give_regulation.jsp');" 
											plain="true"><span>会员储值规则</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="DISCOUNTREGULATION">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="DISCOUNTREGULATION"  iconCls="icon-DISCOUNTREGULATION" 
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/Vip_discount_regulation.jsp');" 
											plain="true"><span>会员折扣规则</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="INTEGRALREG">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="INTEGRALREG"  iconCls="icon-INTEGRALREG" 
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/Vip_integral_regulation.jsp');" 
											plain="true"><span>会员积分规则</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="MENBERSERVICE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="MENBERSERVICE"  iconCls="icon-MENBERSERVICE" 
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/vip_service_project.jsp');" 
											plain="true"><span>会员服务套餐</span></a>
									   </li>
								   </privilege:enable>
				              </ul>
				          </div>
			          </privilege:enable>
			          <privilege:enable code="CONSUMERNATURE">
				          <div title="客户性质" iconCls="icon-folder">
				              <ul>
				                   <privilege:enable code="VISITPROJECT">
					                   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="VISITPROJECT"  iconCls="icon-VISITPROJECT" 
											onclick="openURL('${pageContext.request.contextPath}/base/customer_nature/Customer_visit_project.jsp');" 
											plain="true"><span>客户回访项目</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="PHRASE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="PHRASE"  iconCls="icon-PHRASE" 
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/Commonle_phrase.jsp');" 
											plain="true"><span>常用短语</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="ARAESET">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="ARAESET"  iconCls="icon-ARAESET" 
											onclick="openURL('${pageContext.request.contextPath}/base/customer_nature/Region_set.jsp');" 
											plain="true"><span>地区设定</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CURNATURE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CURNATURE"  iconCls="icon-CURNATURE" 
											onclick="openURL('${pageContext.request.contextPath}/base/customer_nature/Customer_nature.jsp');" 
											plain="true"><span>客户性质</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CUSTYPE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CUSTYPE"  iconCls="icon-CUSTYPE" 
											onclick="openURL('${pageContext.request.contextPath}/base/customer_nature/Customer_classification.jsp');" 
											plain="true"><span>客户类型</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CUSCLASSIFY">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CUSCLASSIFY"  iconCls="icon-CUSCLASSIFY" 
											onclick="openURL('${pageContext.request.contextPath}/base/customer_nature/Customer_fenlei.jsp');" 
											plain="true"><span>客户分类</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CUSCOMPLAINCLASSIFY">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CUSCOMPLAINCLASSIFY"  iconCls="icon-CUSCOMPLAINCLASSIFY" 
											onclick="openURL('${pageContext.request.contextPath}/base/customer_nature/Customer_complaints_classification.jsp');" 
											plain="true"><span>客户投诉分类</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="COSTTYPE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="COSTTYPE"  iconCls="icon-COSTTYPE" 
											onclick="openURL('${pageContext.request.contextPath}/base/customer_nature/Cost_type.jsp');" 
											plain="true"><span>技协费分类</span></a>
									   </li>
								   </privilege:enable>
				              </ul>
				          </div>
			          </privilege:enable>
			          <privilege:enable code="PARTSNATURE">
				          <div title="配件性质" iconCls="icon-folder">
				              <ul>
				                   <privilege:enable code="PARTSBRAND">
					                   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="PARTSBRAND"  iconCls="icon-PARTSBRAND" 
											onclick="openURL('${pageContext.request.contextPath}/BasicData/Partsproperties/Partsbrand.jsp');" 
											plain="true"><span>配件品牌</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="PARTSTYPE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="PARTSTYPE"  iconCls="icon-PARTSTYPE" 
											onclick="openURL('${pageContext.request.contextPath}/base/mountingsTypeInfo.jsp');" 
											plain="true"><span>配件型号</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="PARTSPART">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="PARTSPART"  iconCls="icon-PARTSPART"
											onclick="openURL('${pageContext.request.contextPath}/BasicData/Partsproperties/PartsPart.jsp');" 
											plain="true"><span>配件部位</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="PARTSAREA">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="PARTSAREA"  iconCls="icon-PARTSAREA" 
											onclick="openURL('${pageContext.request.contextPath}/BasicData/Partsproperties/PartState.jsp');" 
											plain="true"><span>配件产地</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="PARTSUNIT">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="PARTSUNIT"  iconCls="icon-PARTSUNIT" 
											onclick="openURL('${pageContext.request.contextPath}/BasicData/Partsproperties/PartsUnit.jsp');" 
											plain="true"><span>计量单位</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="INVITETENDERS">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="INVITETENDERS"  iconCls="icon-INVITETENDERS" 
											onclick="openURL('${pageContext.request.contextPath}/BasicData/Partsproperties/PartsReportPriceClassifaction.jsp');" 
											plain="true"><span>招标单位</span></a>
									   </li>
								   </privilege:enable>
								     <privilege:enable code="INVITETENDERS">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="INVITETENDERS"  iconCls="icon-INVITETENDERS" 
										onclick="openURL('${pageContext.request.contextPath}/BasicData/Partsproperties/StageAddprice.jsp');" 
											plain="true"><span>梯度加价率</span></a>
									   </li>
								   </privilege:enable>
								   <!--<privilege:enable code="PARTSPRICERATIO">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="PARTSPRICERATIO"  iconCls="icon-PARTSPRICERATIO" 
											onclick="openURL('');" 
											plain="true"><span>配件加价系数(暂不可用)</span></a>
									   </li>
								   </privilege:enable>
								   --><privilege:enable code="PARTSSALESTYPE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="PARTSSALESTYPE"  iconCls="icon-PARTSSALESTYPE" 
											onclick="openURL('${pageContext.request.contextPath}/BasicData/Partsproperties/PartsSalesClassfication.jsp');" 
											plain="true"><span>配件销售分类</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="STORETYPE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="STORETYPE"  iconCls="icon-STORETYPE"
											onclick="openURL('${pageContext.request.contextPath}/BasicData/Partsproperties/StorehouseClassfication.jsp');" 
											plain="true"><span>仓别分类</span></a>
									   </li>
								   </privilege:enable>
				              </ul>
				          </div>
			          </privilege:enable>
			          <privilege:enable code="SERVICINGNATURE">
				          <div title="维修性质" iconCls="icon-folder">
				              <ul>
				                   <privilege:enable code="SERVICINGTYPE">
					                   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="SERVICINGTYPE"  iconCls="icon-SERVICINGTYPE" 
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/Maintenance_category.jsp');" 
											plain="true"><span>维修类别</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="SERVICINGLOCATION">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="SERVICINGLOCATION"  iconCls="icon-SERVICINGLOCATION" 
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/Maintenance_man-bit.jsp');" 
											plain="true"><span>维修工位</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="WORKINGHOURS">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="WORKINGHOURS"  iconCls="icon-WORKINGHOURS" 
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/worksort_type.jsp');" 
											plain="true"><span>工时分类</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="PROJECTWORKHOURS">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="PROJECTWORKHOURS"  iconCls="icon-PROJECTWORKHOURS"
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/Standard_project_man-hours.jsp');" 
											plain="true"><span>标准项目工时</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="ADDPROJECT">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="ADDPROJECT"  iconCls="icon-ADDPROJECT"
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/addItem.jsp');" 
											plain="true"><span>加装项目</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CHARGSNATURE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CHARGSNATURE"  iconCls="icon-CHARGSNATURE"
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/Charges_nature.jsp');" 
											plain="true"><span>收费性质</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CLAIMNATURE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CLAIMNATURE"  iconCls="icon-CLAIMNATURE"
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/Claim_nature.jsp');" 
											plain="true"><span>索赔性质</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="INSURANCETYPE">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="INSURANCETYPE"  iconCls="icon-INSURANCETYPE"
											onclick="openURL('${pageContext.request.contextPath}/base/vip_nature/Insurance_coverage.jsp');" 
											plain="true"><span>保险险种</span></a>
									   </li>
								   </privilege:enable>
				              </ul>
				          </div>
			          </privilege:enable>
			          <privilege:enable code="OTHERNATURE">
				          <div title="其他性质" iconCls="icon-folder">
				              <ul>
				                   <privilege:enable code="DEPARTMENTSET">
					                   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="DEPARTMENTSET"  iconCls="icon-DEPARTMENTSET"
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/Department_set.jsp');" 
											plain="true"><span>部门设置</span></a>
									   </li>
								   </privilege:enable>
								   <!-- 
								   <privilege:enable code="JOBPROPERTY">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="JOBPROPERTY"  iconCls="icon-JOBPROPERTY"
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/basJobProperty.jsp?sysType=<%=Contstants.SYSTEMTYPE.WEIXIU %>');" 
											plain="true"><span>工作属性</span></a>
									   </li>
								   </privilege:enable>
								    -->
								   <privilege:enable code="DEPARTMENT">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="DEPARTMENT"  iconCls="icon-DEPARTMENT" 
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/Workshop_department.jsp');" 
											plain="true"><span>维修班组</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="CARCOMPANYPROPERTIES">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="CARCOMPANYPROPERTIES"  iconCls="icon-CARCOMPANYPROPERTIES" 
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/carCompanyProperties.jsp');" 
											plain="true"><span>保险（汽厂）属性</span></a>
									   </li>
								   </privilege:enable>
								   <privilege:enable code="DATAWORDBOOK">
									   <li>
											<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" iconName="DATAWORDBOOK"  iconCls="icon-DATAWORDBOOK" 
											onclick="openURL('${pageContext.request.contextPath}/base/other_set/dataWordBook.jsp');" 
											plain="true"><span>数据字典维护</span></a>
									   </li>
								   </privilege:enable>
								   
				              </ul>
				          </div>
			          </privilege:enable>
			      </div>
				</div>  
			    <div id="baseDataSet" region="center" style="background:#eee;" border="false"></div> 
			 </div>
  </body>
</html>
