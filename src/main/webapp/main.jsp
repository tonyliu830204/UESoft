<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>优亿汽车管理系统</title>
	<head>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
		<script type="text/javascript">
			var login_Name = "";
			var systemId = "";
			var sessionUserId = "";
			var validateUser=function(){
				if("<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getLeavl() %>"=="<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>"){
					alertMsg("超级管理员禁止访问！","warning");
					return;			
				}
				choiceStore();
			}
			
			var mainReload=function(){
				window.location.href="main.jsp";
			}
			
			var distributeValidate=function(){
				if("<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getLeavl() %>"=="<%=Contstants.EMPLOYEELEVEL.ADMIN %>"){
					addTab('SYSTEMSET7', '#tabs', '分布点权限管理', '${pageContext.request.contextPath}/common/base/role/distributpurview.jsp');
				}else{
					alertMsg("非集团管理员禁止访问！","warning");
				}
			}
		</script>
	</head>
	<body class="easyui-layout" border="false">
		<div region="north" split="false" style="overflow: hidden; height: 30px; background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">&nbsp; 
			<span style="float: right; padding-right: 30px;" class="head">
				<a href="javascript:void(0);" style="text-decoration:none;" onclick="mainReload();"><%=session.getAttribute(Contstants.ENTERPRISENAME)!=null?session.getAttribute(Contstants.ENTERPRISENAME):"" %></a>
			   [
			   	<a href="javascript:void(0)" style="text-decoration:none;" onclick="validateUser();">切换企业</a>
			   ]       
			          欢迎
			    <font color="red" style="font-size: 14px;"><strong><span id="loginName"></span></strong></font>&nbsp;&nbsp;&nbsp;|
			    <span>大图标\小图标(功能导航)切换:<input type="checkbox" id="changeIcon" name="changeIcon" style="width:18px;"/></span>&nbsp;&nbsp;&nbsp;|
			    <a href="javascript:void(0);" id="exit">退出</a>|
				<a href="javascript:void(0);" id="loginOut">注销</a>|
				<a href="javascript:void(0)" id="cp" iconCls="icon-help">控制面板</a>
				<div id="mb" style="width: 80px;" border="false">
					<div>
						<span>更换主题</span>
						<div style="width: 100px;">
						
							<div onclick="changeTheme('default');">
								默认
							</div>
							<div onclick="changeTheme('gray');">
								灰色
							</div>
							<div onclick="changeTheme('cupertino');">
								cupertino
							</div>
							<div onclick="changeTheme('dark-hive');">
								dark-hive
							</div>
							<div onclick="changeTheme('metro');">
								metro
							</div>
							<div onclick="changeTheme('pepper-grinder');">
								pepper-grinder
							</div>
							<div onclick="changeTheme('sunny');">
								sunny
							</div>
						</div>
					</div>
				</div> 
			</span>
			<span style="overflow: hidden; padding-left: 10px; font-size: 16px;">
			<img src="images/blocks.gif" align="absmiddle" />优亿汽车维修管理系统</span>
		</div>
		<div region="south" split="false" style="height: 25px; background: #D2E0F2;" border="false">
			<span id="nowDiv" style="float: right; padding-right: 30px;"></span>
		</div>
		<div id="westpanle" region="west" title="导航菜单" style="width:220px;">
			<div class="easyui-tabs" style="width:220px; height:600px;" fit="true" border="false">
			    <privilege:enable code="SERVICING">
					<div title="  维 修 系 统   " fit="true" border="false">
						<div class="easyui-accordion" animate="false" fit="true" border="false">
						     <privilege:enable code="SYSTEMSET">
								<div title="系统设置" iconCls="icon-folder">
									<ul>
									    <privilege:enable code="ENTERPRISEINFO">
										    <li>
												<a name="icona" href="javascript:void(0);" iconName="ENTERPRISE"  iconCls="icon-ENTERPRISE"
													onclick="addTab('SYSTEMSET0', '#tabs', '企业管理', '${pageContext.request.contextPath}/common/base/enterpriseInfo/enterpriseInfo.jsp');"
													class="easyui-linkbutton" plain="true"><span>企业管理</span></a>		
											</li>
										</privilege:enable>
									    <privilege:enable code="PERSONSET">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="PERSONSET"  iconCls="icon-PERSONSET"
													onclick="addTab('SYSTEMSET1', '#tabs', '人事资料设定', '${pageContext.request.contextPath}/common/base/personnel/personnelInformation.jsp?sysType=<%=Contstants.SYSTEMTYPE.WEIXIU %>');"
													plain="true"><span>人事资料设定</span></a>
											</li>
										</privilege:enable>
									    <privilege:enable code="MENUMANAGER">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="MENUMANAGER"  iconCls="icon-MENUMANAGER"
													onclick="addTab('SYSTEMSET2', '#tabs', '菜单管理', '${pageContext.request.contextPath}/common/base/menu/menuInformation.jsp');"
													plain="true"><span>菜单管理</span></a>
											</li>	
										</privilege:enable>
										<privilege:enable code="AUTHORITYSET">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="AUTHORITYSET"  iconCls="icon-AUTHORITYSET"
													onclick="addTab('SYSTEMSET3', '#tabs', '权限设置', '${pageContext.request.contextPath}/common/base/role/roleInformation.jsp');"
													plain="true"><span>权限设置</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SERVICECONTROL">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="SERVICECONTROL"  iconCls="icon-SERVICECONTROL"
													onclick="addTab('SYSTEMSET7', '#tabs', '业务权限设置', '${pageContext.request.contextPath}/common/base/control/control.jsp');"
													plain="true"><span>业务权限设置</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="UPDATEPASSWORD">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="UPDATEPASSWORD"  iconCls="icon-UPDATEPASSWORD"
													onclick="addTab('SYSTEMSET4', '#tabs', '修改密码', '${pageContext.request.contextPath}/common/base/password/password.jsp');"
													plain="true"><span>修改密码</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="DATEBASEMANAGE">
											<li>
												<a name="icona" href="javascript:void(0);" 
												 class="easyui-linkbutton" iconName="BACK"  iconCls="icon-BACK"
												 onclick="addTab('SYSTEMSET5', '#tabs', '数据库管理', '${pageContext.request.contextPath}/common/base/datebase/data_backup.jsp');"
												 plain="true"><span>数据库管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="DRUIDDBSOURCE">
											<li>
												<a name="icona" href="javascript:void(0);"
												class="easyui-linkbutton" iconName="DRUIDDBSOURCE"  iconCls="icon-DRUIDDBSOURCE"
											    onclick="addTab('SYSTEMSET6', '#tabs', 'Druid数据源监控', '${pageContext.request.contextPath}/druid/index.html');"
												plain="true"><span>Druid数据源监控</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="DISTRIBUTPURVIEW">
											<li>
												<a name="icona" href="javascript:void(0);"
												class="easyui-linkbutton" iconName="DISTRIBUTPURVIEW"  iconCls="icon-DISTRIBUTPURVIEW"
											    onclick="distributeValidate();"
												plain="true"><span>分布点权限管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="COMPANYSET">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="COMPANYSET"  iconCls="icon-COMPANYSET"
													onclick="addTab('BASE11', '#tabs', '公司信息设定', '${pageContext.request.contextPath}/base/companyInformationSet.jsp');"
													plain="true"><span>公司信息设定</span></a>
											</li>
										</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
						    <privilege:enable code="BASE">
								<div title="基础设置" iconCls="icon-folder">
									<ul>
									    <privilege:enable code="BASESET">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="BASESET"  iconCls="icon-BASESET"
													onclick="addTab('BASE1', '#tabs', '基本数据设置', '${pageContext.request.contextPath}/BasicData/BasicDataSet.jsp');"
													plain="true"><span>基本数据设置</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SERVICINGMEAL">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="SERVICINGMEAL"  iconCls="icon-SERVICINGMEAL"
													onclick="addTab('BASE2', '#tabs', '维修套餐', '${pageContext.request.contextPath}/base/repairPackage.jsp');"
													plain="true"><span>维修套餐</span></a>
											</li>
										</privilege:enable>
										<!--<privilege:enable code="TOOLSMANAGER">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="TOOLS"  iconCls="icon-TOOLS"
													onclick="addTab('BASE3', '#tabs', '工具管理', '${pageContext.request.contextPath}/base/toolsManager.jsp');"
													plain="true"><span>工具管理(暂不做)</span></a>
											</li>
										</privilege:enable>
										--><privilege:enable code="PARTSARCHIVES">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="PARTSARCHIVES"  iconCls="icon-PARTSARCHIVES"
													onclick="addTab('BASE5', '#tabs', '配件档案', '${pageContext.request.contextPath}/base/partsArchives.jsp');"
													plain="true"><span>配件档案</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="CUSTOMARCHIVES">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="CUSTOMARCHIVES"  iconCls="icon-CUSTOMARCHIVES"
													onclick="addTab('BASE6', '#tabs', '客户档案', '${pageContext.request.contextPath}/base/customArchives.jsp');"
													plain="true"><span>客户档案</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SUPPLIERARCHIVES">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="SUPPLIERARCHIVES"  iconCls="icon-SUPPLIERARCHIVES"
													onclick="addTab('BASE7', '#tabs', '供应商档案', '${pageContext.request.contextPath}/base/supplierArchives.jsp');"
													plain="true"><span>供应商档案</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="CARARCHIVES">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="CARARCHIVES"  iconCls="icon-CARARCHIVES"
													onclick="addTab('BASE8', '#tabs', '车辆档案', '${pageContext.request.contextPath}/base/carArchives.jsp');"
													plain="true"><span>车辆档案</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SYSTEMPARAMETERSET">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="SYSTEMSET"  iconCls="icon-SYSTEMSET"
													onclick="addTab('BASE9', '#tabs', '系统参数设定', '${pageContext.request.contextPath}/base/parameter/parameterSet.jsp');"
													plain="true"><span>系统参数设定</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="INSYRANCEARCHIVES">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="INSYRANCEARCHIVES"  iconCls="icon-INSYRANCEARCHIVES"
													onclick="addTab('BASE10', '#tabs', '保险(汽厂)档案', '${pageContext.request.contextPath}/base/insuranceCarArchives.jsp');"
													plain="true"><span>保险(汽厂)档案</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="PARTSONOROFF">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="PARTSONOROFF"  iconCls="icon-PARTSONOROFF"
													onclick="addTab('BASE12', '#tabs', '配件使用设置', '${pageContext.request.contextPath}/base/partsONOrOff.jsp');"
													plain="true"><span>配件使用设置</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="RCPTBRANCH">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="RCPTBRANCH"  iconCls="icon-RCPTBRANCH"
													onclick="addTab('BASE13', '#tabs', '接车分部管理', '${pageContext.request.contextPath}/base/rcptBranchManangement.jsp');"
													plain="true"><span>接车分部管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="CUSTOMONOROFF">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" iconName="CUSTOMONOROFF"  iconCls="icon-CUSTOMONOROFF"
													onclick="addTab('BASE14', '#tabs', '客户档案设置', '${pageContext.request.contextPath}/base/customONOrOff.jsp');"
													plain="true"><span>客户档案设置</span></a>
											</li>
										</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
							<privilege:enable code="STOREHOUSE">
								<div title="仓库管理" iconCls="icon-folder" selected="true" >
									<ul>
									    <privilege:enable code="STOREHOUSEMAN">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE1"  iconCls="icon-STOREHOUSE1"
													onclick="addTab('STOREHOUSE1', '#tabs', '采购单管理', '${pageContext.request.contextPath}/st/StPurOrder/StPurOrderManager.jsp');"
													plain="true"><span>采购单管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="INPUTSTOREHOUSE">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE2"  iconCls="icon-STOREHOUSE2"
													onclick="addTab('STOREHOUSE2', '#tabs', '入库单管理', '${pageContext.request.contextPath}/st/StGoodsStorage/StGoodsStorageManager.jsp');"
													plain="true"><span>入库单管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="INPUTSTOREHOUSE">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE3"  iconCls="icon-STOREHOUSE3"
													onclick="addTab('STOREHOUSE3', '#tabs', '入库调价管理', '${pageContext.request.contextPath}/st/StGoodsStorage/StGoodsStorageChangePriceManager.jsp');"
													plain="true"><span>入库调价管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="HANDBACKMAN">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE4"  iconCls="icon-STOREHOUSE4"
													onclick="addTab('STOREHOUSE4', '#tabs', '退货单管理', '${pageContext.request.contextPath}/st/StRtGoods/StRtGoodsManager.jsp');"
													plain="true"><span>退货单管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="HANDBACKMAN">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE5"  iconCls="icon-STOREHOUSE5"
													onclick="addTab('STOREHOUSE5', '#tabs', '入退单查询', '${pageContext.request.contextPath}/st/StGoodsStorage_RtGoodsSearch/StGoodsStorage_RtGoodsSearch.jsp');"
													plain="true"><span>入退单查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="OUTPUTSTOREHOUSE">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE6"  iconCls="icon-STOREHOUSE6"
													onclick="addTab('STOREHOUSE6', '#tabs', '出库单管理', '${pageContext.request.contextPath}/st/StOut/StOutManager.jsp');"
													plain="true"><span>出库单管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="BILLHANDBACK">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE7"  iconCls="icon-STOREHOUSE7"
													onclick="addTab('STOREHOUSE7', '#tabs', '工单退料单管理', '${pageContext.request.contextPath}/st/StRtParts/StRtPartsManager.jsp');"
													plain="true"><span>工单退料管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="HANDBACKMAN">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE8"  iconCls="icon-STOREHOUSE8"
													onclick="addTab('STOREHOUSE8', '#tabs', '出退单查询', '${pageContext.request.contextPath}/st/StOut_StRtPartsSearch/StOut_StRtPartsSearch.jsp');"
													plain="true"><span>出退单查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SELLBILLMAN">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE9"  iconCls="icon-STOREHOUSE9"
													onclick="addTab('STOREHOUSE9', '#tabs', '销售单管理', '${pageContext.request.contextPath}/st/StSellOrder/StSellOrderManager.jsp');"
													plain="true"><span>销售单管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SELLHANDBACK">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE10"  iconCls="icon-STOREHOUSE10"
													onclick="addTab('STOREHOUSE10', '#tabs', '销售退料单管理', '${pageContext.request.contextPath}/st/StSellRtParts/StSellRtPartsManager.jsp');"
													plain="true"><span>销售退料单管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="HANDBACKMAN">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE11"  iconCls="icon-STOREHOUSE11"
													onclick="addTab('STOREHOUSE11', '#tabs', '消退单查询', '${pageContext.request.contextPath}/st/StSellOrder_StRtSellOrderSearch/StSellOrder_StRtSellOrderSearch.jsp');"
													plain="true"><span>消退单查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="STOCKSEACH">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE12"  iconCls="icon-STOREHOUSE12"
													onclick="addTab('STOREHOUSE12', '#tabs', '库存量查询', '${pageContext.request.contextPath}/st/StPartsNowCount/StPartsNowCountMananger.jsp');"
													plain="true"><span>库存量查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="INTOSELLACCUM">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton"   iconName="STOREHOUSE13"  iconCls="icon-STOREHOUSE13"
													onclick="addTab('STOREHOUSE13', '#tabs', '进销存报表', '${pageContext.request.contextPath}/st/StStock/stStockSearch.jsp');"
													plain="true"><span>进销存报表</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="AUGUROUTHOUSE">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE14"  iconCls="icon-STOREHOUSE14"
													onclick="addTab('STOREHOUSE14', '#tabs', '预出库管理', '${pageContext.request.contextPath}/st/StPreOut/StPreOutManager.jsp');"
													plain="true"><span>预出库管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="MOVEHOUSE">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE15"  iconCls="icon-STOREHOUSE15"
													onclick="addTab('STOREHOUSE15', '#tabs', '移仓管理', '${pageContext.request.contextPath}/st/StMoveStorehouse/StMoveStorehouseManager.jsp');"
													plain="true"><span>移仓调拨单管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="MOVEHOUSE">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="STOREHOUSE16"  iconCls="icon-STOREHOUSE16"
													onclick="addTab('STOREHOUSE16', '#tabs', '库存月结转', '${pageContext.request.contextPath}/st/StMonthlyStatement/StMonthlyStatement.jsp');"
													plain="true"><span>库存月结转</span></a>
											</li>
										</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
							<privilege:enable code="STAGEMAN" >
								<div title="前台管理" iconCls="icon-folder" >
									<div style="width:210px;">
										<ul>
									    <privilege:enable code="CARSEARCH">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN1"  iconCls="icon-STAGEMAN1"
													onclick="addTab('STAGEMAN1','#tabs', '车辆档案查询', '${pageContext.request.contextPath}/frt/frtCarArchives.jsp');"
													 class="easyui-linkbutton" plain="true"><span>车辆档案查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="MAKEBILL" >
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN2"  iconCls="icon-STAGEMAN2"
													onclick="addTab('STAGEMAN2', '#tabs', '预约申请单', '${pageContext.request.contextPath}/frt/frtResevation.jsp');"
													 class="easyui-linkbutton" plain="true"><span>预约申请单</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="PROSCENIUMMANAGE">
										<li>
											<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN35"  iconCls="icon-STAGEMAN35"
												onclick="addTab('STAGEMAN35', '#tabs', '前台操作管理', 'frt/receptionManager.jsp');"
												 class="easyui-linkbutton" plain="true"><span>前台操作管理</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="STAGECAR">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN3"  iconCls="icon-STAGEMAN3"
													onclick="addTab('STAGEMAN3', '#tabs', '前台接车', '${pageContext.request.contextPath}/frt/frtReception.jsp');"
													 class="easyui-linkbutton" plain="true"><span>前台接车</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SETTLEACCOUNTS">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="STAGEMAN4"  iconCls="icon-STAGEMAN4"
													onclick="addTab('STAGEMAN4', '#tabs', '交车结算', '${pageContext.request.contextPath}/frt/frtPreClearing.jsp');"
													 class="easyui-linkbutton" plain="true"><span>交车结算</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="STAGEPARTSSEA">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN5"  iconCls="icon-STAGEMAN5"
													onclick="addTab('STAGEMAN5', '#tabs', '前台配件查询', '${pageContext.request.contextPath}/frt/frtPartsQuery.jsp');"
													 class="easyui-linkbutton" plain="true"><span>前台配件查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="COLLIGATE">
											<li>
											   <div>
													<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN6"  iconCls="icon-STAGEMAN6"
													onclick="controlManage('receptionQueryShow',4);"
													 class="easyui-linkbutton" plain="true"><span>工单综合查询</span></a>											   		
											   </div> 
											    <div id="receptionQueryShow" style="display:none;width:170px;" >
													<ul>
														<privilege:enable code="COLLIGATE_BASE" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN28"  iconCls="icon-STAGEMAN28"
																	onclick="addTab('STAGEMAN28', '#tabs', '工单-基本信息', '${pageContext.request.contextPath}/frt/frtWorkOrder.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>工单-基本信息</span></a>
															</li>
														</privilege:enable>
														<privilege:enable code="COLLIGATE_SITEM" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN29"  iconCls="icon-STAGEMAN29"
																	onclick="addTab('STAGEMAN29', '#tabs', '工单-维修项目', '${pageContext.request.contextPath}/frt/frtWorkOrder/workOrderItem.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>工单-维修项目</span></a>
															</li>
														</privilege:enable>
														<privilege:enable code="COLLIGATE_SPARTS" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN30"  iconCls="icon-STAGEMAN30"
																	onclick="addTab('STAGEMAN30', '#tabs', '工单-维修配件', '${pageContext.request.contextPath}/frt/frtWorkOrder/workOrderParts.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>工单-维修配件</span></a>
															</li>
														</privilege:enable>
														<privilege:enable code="COLLIGATE_BITEM" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN31"  iconCls="icon-STAGEMAN31"
																	onclick="addTab('STAGEMAN31', '#tabs', '结算单-工时清单', '${pageContext.request.contextPath}/frt/frtWorkOrder/preClearingItem.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>结算单-工时清单</span></a>
															</li>
														</privilege:enable>
														<privilege:enable code="COLLIGATE_BPARTS" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN32"  iconCls="icon-STAGEMAN32"
																	onclick="addTab('STAGEMAN32', '#tabs', '结算单-材料清单', '${pageContext.request.contextPath}/frt/frtWorkOrder/preClearingParts.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>结算单-材料清单</span></a>
															</li>
														</privilege:enable>
												    </ul>  	   
												</div>
										    </li>
										</privilege:enable>
										<privilege:enable code="SERVICEOPERATIONSTAT">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="STAGEMAN7"  iconCls="icon-STAGEMAN7"
													onclick="addTab('STAGEMAN7', '#tabs', '维修业务统计', '${pageContext.request.contextPath}/frt/repairStatisticalStatement.jsp');"
													 class="easyui-linkbutton" plain="true"><span>维修业务统计</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="CLAIMANTCOLLIGATEQUERY">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="STAGEMAN8"  iconCls="icon-STAGEMAN8"
													onclick="addTab('STAGEMAN8', '#tabs', '索赔综合查询', '${pageContext.request.contextPath}/frt/claimantQuery.jsp');"
													 class="easyui-linkbutton" plain="true"><span>索赔综合查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="CLAIMBILL">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN9"  iconCls="icon-STAGEMAN9"
													onclick="addTab('STAGEMAN9', '#tabs', '索理赔结算单', '${pageContext.request.contextPath}/frt/finClaimantMain.jsp');"
													 class="easyui-linkbutton" plain="true"><span>索理赔结算单</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="WORKSHOPMAN">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN10"  iconCls="icon-STAGEMAN10"
													onclick="addTab('STAGEMAN10', '#tabs', '车间管理', '${pageContext.request.contextPath}/frt/frtWorkshopManager.jsp');"
													 class="easyui-linkbutton" plain="true"><span>车间管理</span></a>
											</li>
										</privilege:enable>
<%--										<privilege:enable code="WASHCAR">--%>
<%--											<li>--%>
<%--												<a name="icona" href="javascript:void(0);"   iconName="STAGEMAN11"  iconCls="icon-STAGEMAN11"--%>
<%--													onclick="addTab('STAGEMAN11', '#tabs', '洗车登记管理', '${pageContext.request.contextPath}/frt/xcdjgl.jsp');"--%>
<%--													 class="easyui-linkbutton" plain="true"><span>洗车登记管理</span></a>--%>
<%--											</li>--%>
<%--										</privilege:enable>--%>

										<privilege:enable code="PROSCENIUMGATHERING">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="STAGEMAN12"  iconCls="icon-STAGEMAN12"
													onclick="addTab('STAGEMAN12', '#tabs', '前台收银', '${pageContext.request.contextPath}/frt/balancePage.jsp');"
													 class="easyui-linkbutton" plain="true"><span>前台收银</span></a>
											</li>
										</privilege:enable>
										
										<!--<privilege:enable code="BATCHBALANCE">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="STAGEMAN13"  iconCls="icon-STAGEMAN13"
													onclick="addTab('STAGEMAN13', '#tabs', '批量收款', '${pageContext.request.contextPath}/frt/batchBalancePage.jsp');"
													 class="easyui-linkbutton" plain="true"><span>批量收款</span></a>
											</li>
										</privilege:enable>
										--><privilege:enable code="SUBSTITUTEBALANCE">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="STAGEMAN14"  iconCls="icon-STAGEMAN14"
													onclick="addTab('STAGEMAN14', '#tabs', '代付收款', '${pageContext.request.contextPath}/frt/subtituteBalance.jsp');"
													 class="easyui-linkbutton" plain="true"><span>代付收款</span></a>
											</li>
										</privilege:enable>
										<!--<privilege:enable code="SUBSTITUTEBATCHBALANCE">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="STAGEMAN15"  iconCls="icon-STAGEMAN15"
													onclick="addTab('STAGEMAN15', '#tabs', '代付批量收款', '${pageContext.request.contextPath}/frt/substituteBatchBalance.jsp');"
													 class="easyui-linkbutton" plain="true"><span>代付批量收款</span></a>
											</li>
										</privilege:enable>
										--><privilege:enable code="INSUREBILL" >
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN16"  iconCls="icon-STAGEMAN16"
													onclick="addTab('STAGEMAN16', '#tabs', '保险估价单', '${pageContext.request.contextPath}/frt/frtInsurePrize.jsp');"
													 class="easyui-linkbutton" plain="true"><span>保险估价单</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="GATHERINGQUERY" >
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN19"  iconCls="icon-STAGEMAN19"
													onclick="addTab('STAGEMAN19', '#tabs', '收款查询', '${pageContext.request.contextPath}/frt/gatheringQuery.jsp');"
													 class="easyui-linkbutton" plain="true"><span>收款查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SUBTITUTEQUERY" >
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN20"  iconCls="icon-STAGEMAN20"
													onclick="addTab('STAGEMAN20', '#tabs', '代付收款查询', '${pageContext.request.contextPath}/frt/subtituteQuery.jsp');"
													 class="easyui-linkbutton" plain="true"><span>代付收款查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="RECEPTIONHISTORYQUERY" >
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN22"  iconCls="icon-STAGEMAN22"
													onclick="addTab('STAGEMAN22', '#tabs', '工单历史记录查询', '${pageContext.request.contextPath}/frt/receptionHistoryQuery.jsp');"
													 class="easyui-linkbutton" plain="true"><span>工单历史记录查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="RECEIVEOPERATION">
											<li>
												<div>
													<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN23"  iconCls="icon-STAGEMAN23"
													onclick="controlManage('receiveOperationShow',6);"
													 class="easyui-linkbutton" plain="true"><span>前台接车接待员业绩统计</span></a>											   		
											   </div> 
											   <div id="receiveOperationShow" style="display:none;width:180px;" >
													 <ul>
														<privilege:enable code="RECEIVEOPERATIONMAIN" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN33"  iconCls="icon-STAGEMAN33"
																	onclick="addTab('STAGEMAN33', '#tabs', '接待员业绩统计汇总', '${pageContext.request.contextPath}/frt/receiveOperation/receiveOperationMain.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>接待员业绩统计汇总</span></a>
															</li>
														</privilege:enable>
														<privilege:enable code="RECEIVEOPERATIONDETAIL" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN34"  iconCls="icon-STAGEMAN34"
																	onclick="addTab('STAGEMAN34', '#tabs', '接待员业绩统计明细', '${pageContext.request.contextPath}/frt/receiveOperation/receiveOperationDetail.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>接待员业绩统计明细</span></a>
															</li>
														</privilege:enable>
												    </ul>   
											   </div>	 
											</li>
										</privilege:enable>
										<privilege:enable code="PROSCENIUMDATAANALYSE">
											<li>
											   <div>
													<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN27"  iconCls="icon-STAGEMAN27"
													onclick="controlManage('prosceniumShow',1);"
													 class="easyui-linkbutton" plain="true"><span>数据分析</span></a>											   		
											   </div> 
											   <div id="prosceniumShow" style="display:none;width:170px;" >
													 <ul>
														<privilege:enable code="BESPEAKANALYSE" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN17"  iconCls="icon-STAGEMAN17"
																	onclick="addTab('STAGEMAN17', '#tabs', '预约分析', '${pageContext.request.contextPath}/frt/bespeakAnalyse.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>预约分析</span></a>
															</li>
														</privilege:enable>
														<privilege:enable code="SERVICEDESTCLERKANALYSE" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN18"  iconCls="icon-STAGEMAN18"
																	onclick="addTab('STAGEMAN18', '#tabs', '维修接待员分析', '${pageContext.request.contextPath}/frt/serviceDestClerkAnalyse.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>维修接待员分析</span></a>
															</li>
														</privilege:enable>
														<privilege:enable code="COMPLETEANALYSE" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN21"  iconCls="icon-STAGEMAN21"
																	onclick="addTab('STAGEMAN21', '#tabs', '完工分析', '${pageContext.request.contextPath}/frt/completeAnalyse.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>完工分析</span></a>
															</li>
														</privilege:enable>
														<privilege:enable code="SERVICECLASSANALYSE" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN24"  iconCls="icon-STAGEMAN24"
																	onclick="addTab('STAGEMAN24', '#tabs', '维修类别分析', '${pageContext.request.contextPath}/frt/serviceClassAnalyse.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>维修类别分析</span></a>
															</li>
														</privilege:enable>
														<privilege:enable code="INSUREPERSONANALYSE" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN25"  iconCls="icon-STAGEMAN25"
																	onclick="addTab('STAGEMAN25', '#tabs', '保险送修人分析', '${pageContext.request.contextPath}/frt/insurePersonAnalyse.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>保险送修人分析</span></a>
															</li>
														</privilege:enable>
														<privilege:enable code="CLAIMCOSTANLAYSE" >
															<li>
																<a name="icona" href="javascript:void(0);"  iconName="STAGEMAN26"  iconCls="icon-STAGEMAN26"
																	onclick="addTab('STAGEMAN26', '#tabs', '索赔成本对比分析', '${pageContext.request.contextPath}/frt/claimCostContrastAnalyse.jsp');"
																	 class="easyui-linkbutton" plain="true"><span>索赔成本对比分析</span></a>
															</li>
														</privilege:enable>
												    </ul>   
											   </div>
										    </li>
										</privilege:enable>
									</ul>
									</div>
								</div>
							</privilege:enable>
							<privilege:enable code="FINANCE">
								<div title="财务管理" iconCls="icon-folder" >
									<ul>
									    <privilege:enable code="RESERVECOLLECT">
										    <li>
												<a name="icona" href="javascript:void(0);" iconName="FINANCE1"  iconCls="icon-FINANCE1"
													onclick="addTab('FINANCE1', '#tabs', '维修预收款', '${pageContext.request.contextPath}/fin/StRepairPrePaid.jsp');"
													 class="easyui-linkbutton" plain="true"><span>维修预收款</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SERVICLAYIN">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="FINANCE2"  iconCls="icon-FINANCE2"
													onclick="addTab('FINANCE2', '#tabs', '维修储备金', '${pageContext.request.contextPath}/fin/StSellPercharge.jsp');"
													 class="easyui-linkbutton" plain="true"><span>维修储备金</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SHOULDCOLLECT">
										    <li>
												<a name="icona" href="javascript:void(0);" iconName="FINANCE3"  iconCls="icon-FINANCE3"
													onclick="addTab('FINANCE3', '#tabs', '应收账款', '${pageContext.request.contextPath}/fin/Receivables.jsp');"
													 class="easyui-linkbutton" plain="true"><span>应收账款</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SELLSHOULDCOLLFIN">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="FINANCE4"  iconCls="icon-FINANCE4"
													onclick="addTab('FINANCE4', '#tabs', '销售单结算', '${pageContext.request.contextPath}/fin/StSellPreclr.jsp');"
													 class="easyui-linkbutton" plain="true"><span>销售单结算</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SELLSHOULDCOLL">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="FINANCE5"  iconCls="icon-FINANCE5"
													onclick="addTab('FINANCE5', '#tabs', '销售应收款', '${pageContext.request.contextPath}/fin/StSellCharge.jsp');"
													 class="easyui-linkbutton" plain="true"><span>销售应收款</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="WORKPARTSSEA">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="FINANCE6"  iconCls="icon-FINANCE6"
													onclick="addTab('FINANCE6', '#tabs', '工单配件查询', '${pageContext.request.contextPath}/fin/workOrderPartsQuery.jsp');"
													 class="easyui-linkbutton" plain="true"><span>工单配件查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="DAYTURNOVER">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="FINANCE7"  iconCls="icon-FINANCE7"
													onclick="addTab('FINANCE7', '#tabs', '日营业情况查询', '${pageContext.request.contextPath}/fin/DayManageSituation.jsp');"
													 class="easyui-linkbutton" plain="true"><span>日营业情况查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="PARTSCOLLSEA">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="FINANCE8"  iconCls="icon-FINANCE8"
													onclick="addTab('FINANCE8', '#tabs', '配件动态变化查询', '${pageContext.request.contextPath}/fin/PartsTrendsChangeSearch.jsp');"
													 class="easyui-linkbutton" plain="true"><span>配件动态变化查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="RUNSITUATION">
											<li>
											   <div>
													<a name="icona" href="javascript:void(0);"  iconName="RUNSITUATION"  iconCls="icon-RUNSITUATION"
													onclick="controlManage('manageShow',2);"
													 class="easyui-linkbutton" plain="true"><span>经营情况查询</span></a>											   		
											   </div> 
											    <div id="manageShow" style="display:none;width:150px;" >
													<ul>
														<privilege:enable code="FINANCEDAYBALANCE">
												          <li><a name="icona" href="javascript:void(0);" iconName="RUNSITUATION1"  iconCls="icon-RUNSITUATION1"
															onclick="addTab('RUNSITUATION1', '#tabs', '日收款查询', '${pageContext.request.contextPath}/fin/dayCollextionsSearch.jsp');"
															 class="easyui-linkbutton" plain="true"><span>日收款查询</span></a></li>
														</privilege:enable>
														<privilege:enable code="FINANCEDAYMANAGE">
													      <li><a name="icona" href="javascript:void(0);" iconName="RUNSITUATION2"  iconCls="icon-RUNSITUATION2"
															onclick="addTab('RUNSITUATION2', '#tabs', '日经营查询', '${pageContext.request.contextPath}/fin/DayManageSearch.jsp');"
															 class="easyui-linkbutton" plain="true"><span>日经营查询</span></a></li>
														</privilege:enable>
														<privilege:enable code="FINANCEMONTHMANAGE">
													      <li><a name="icona" href="javascript:void(0);" iconName="RUNSITUATION3"  iconCls="icon-RUNSITUATION3"
															onclick="addTab('RUNSITUATION3', '#tabs', '月营业查询', '${pageContext.request.contextPath}/fin/MonthManageSearch.jsp');"
															 class="easyui-linkbutton" plain="true"><span>月营业查询</span></a></li>
														</privilege:enable>
														<privilege:enable code="FINANCEYEARMANAGE">
														  <li><a name="icona" href="javascript:void(0);" iconName="RUNSITUATION4"  iconCls="icon-RUNSITUATION4"
															onclick="addTab('RUNSITUATION4', '#tabs', '年营业查询', '${pageContext.request.contextPath}/fin/yearManageSearch.jsp');"
															 class="easyui-linkbutton" plain="true"><span>年营业查询</span></a></li>
														</privilege:enable>
														<privilege:enable code="FINANCECARBALANCE">
													      <li><a name="icona" href="javascript:void(0);" iconName="RUNSITUATION5"  iconCls="icon-RUNSITUATION5"
															onclick="addTab('RUNSITUATION5', '#tabs', '车辆结算排行', '${pageContext.request.contextPath}/fin/carBalanceUntangle.jsp');"
															 class="easyui-linkbutton" plain="true"><span>车辆结算排行</span></a></li>
														</privilege:enable>
														<privilege:enable code="FINANCECOSTANALYSE">
													      <li><a name="icona" href="javascript:void(0);" iconName="RUNSITUATION6"  iconCls="icon-RUNSITUATION6"
															onclick="addTab('RUNSITUATION6', '#tabs', '成本分析', '${pageContext.request.contextPath}/fin/costAnalyse.jsp');"
															 class="easyui-linkbutton" plain="true"><span>成本分析</span></a></li>
														</privilege:enable>
												    </ul>  	   
												</div>
										    </li>
										</privilege:enable>
										<privilege:enable code="SUPPLIERACCOUNT">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="FINANCE9"  iconCls="icon-FINANCE9"
													onclick="addTab('FINANCE9', '#tabs', '供应商对账单', '${pageContext.request.contextPath}/fin/RelcampBalanceOfAccount.jsp');"
													 class="easyui-linkbutton" plain="true"><span>供应商对账单</span></a>
											</li>
										</privilege:enable>
<%--										<privilege:enable code="OUTACCOUNT">--%>
<%--											<li>--%>
<%--												<a name="icona" href="javascript:void(0);" iconName="FINANCE10"  iconCls="icon-FINANCE10"--%>
<%--													onclick="addTab('FINANCE10', '#tabs', '出库对账单管理', '${pageContext.request.contextPath}/fin/ckddgl.jsp');"--%>
<%--													 class="easyui-linkbutton" plain="true"><span>出库对单管理</span></a>--%>
<%--											</li>--%>
<%--										</privilege:enable>--%>
<%--										<privilege:enable code="RECEPTIONCOUNT">--%>
<%--											<li>--%>
<%--												<a name="icona" href="javascript:void(0);" iconName="FINANCE11"  iconCls="icon-FINANCE11"--%>
<%--													onclick="addTab('FINANCE11', '#tabs', '外包及接待员统计', '${pageContext.request.contextPath}/fin/outsourcingAndReceptionistStatistics.jsp');"--%>
<%--													 class="easyui-linkbutton" plain="true"><span>外包及接待员统计</span></a>--%>
<%--											</li>--%>
<%--										</privilege:enable>--%>
<%--										<privilege:enable code="INVOICEMAN">--%>
<%--											<li>--%>
<%--												<a name="icona" href="javascript:void(0);" iconName="FINANCE12"  iconCls="icon-FINANCE12"--%>
<%--													onclick="addTab('FINANCE12', '#tabs', '发票管理', '${pageContext.request.contextPath}/fin/fpgl.jsp');"--%>
<%--													 class="easyui-linkbutton" plain="true"><span>发票管理</span></a>--%>
<%--											</li>--%>
<%--										</privilege:enable>--%>
<%--										<privilege:enable code="DAYCOLLDETAIL">--%>
<%--											<li>--%>
<%--												<a name="icona" href="javascript:void(0);" iconName="FINANCE13"  iconCls="icon-FINANCE13"--%>
<%--													onclick="addTab('FINANCE13', '#tabs', '日收款明细', '${pageContext.request.contextPath}/fin/rskmx.jsp');"--%>
<%--													 class="easyui-linkbutton" plain="true"><span>日收款明细</span></a>--%>
<%--											</li>--%>
<%--										</privilege:enable>--%>
<%--										<privilege:enable code="FINANCECOLLPAY">--%>
<%--											<li>--%>
<%--												<a name="icona" href="javascript:void(0);" iconName="FINANCE14"  iconCls="icon-FINANCE14"--%>
<%--													onclick="addTab('FINANCE14', '#tabs', '财务收支管理', '${pageContext.request.contextPath}/fin/financeResultManager.jsp');"--%>
<%--													 class="easyui-linkbutton" plain="true"><span>财务收支管理</span></a>--%>
<%--											</li>--%>
<%--										</privilege:enable>--%>
									</ul>
								</div>
	                        </privilege:enable>
	                        <privilege:enable code="VIPMANAGER">
								<div title="会员管理" iconCls="icon-folder">
									<ul>
									    <privilege:enable code="VIPARCHIVES">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER1"  iconCls="icon-VIPMANAGER1"
													onclick="addTab('VIPMANAGER1', '#tabs', '会员档案管理', '${pageContext.request.contextPath}/vip_management/vip_info_management.jsp');"
													 class="easyui-linkbutton" plain="true"><span>会员档案管理</span></a>
											</li>
		                                </privilege:enable>
		                                <privilege:enable code="VIPCARDLAY">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER2"  iconCls="icon-VIPMANAGER2"
													onclick="addTab('VIPMANAGER2', '#tabs', '会员卡储值', '${pageContext.request.contextPath}/vip_management/vip_chongzhi.jsp');"
													 class="easyui-linkbutton" plain="true"><span>会员卡储值</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="VIPPOINTS">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER3"  iconCls="icon-VIPMANAGER3"
													onclick="addTab('VIPMANAGER3', '#tabs', '积分赠送管理', '${pageContext.request.contextPath}/vip_management/vip_jifenzengsong.jsp');"
													 class="easyui-linkbutton" plain="true"><span>积分赠送管理</span></a>
											</li>
		                                </privilege:enable>
		                                <privilege:enable code="GIFTEXCHANGE">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER4"  iconCls="icon-VIPMANAGER4"
													onclick="addTab('VIPMANAGER4', '#tabs', '礼品兑换管理', '${pageContext.request.contextPath}/vip_management/vip_lipinduihuan.jsp');"
													 class="easyui-linkbutton" plain="true"><span>礼品兑换管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="POINTSCOLLSEA">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER5"  iconCls="icon-VIPMANAGER5"
													onclick="addTab('VIPMANAGER5', '#tabs', '积分综合查询', '${pageContext.request.contextPath}/vip_management/score_find.jsp');"
													 class="easyui-linkbutton" plain="true"><span>积分综合查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="VIPUPDOWN">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER6"  iconCls="icon-VIPMANAGER6"
													onclick="addTab('VIPMANAGER6', '#tabs', '会员卡升级/降级', '${pageContext.request.contextPath}/vip_management/vip_shengji.jsp');"
													 class="easyui-linkbutton" plain="true"><span>会员卡升级/降级</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="VIPCONTINUE">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER7"  iconCls="icon-VIPMANAGER7"
													onclick="addTab('VIPMANAGER7', '#tabs', '会员续会', '${pageContext.request.contextPath}/vip_management/vip_xuhui.jsp');"
													 class="easyui-linkbutton" plain="true"><span>会员续会</span></a>
											</li>
		                                </privilege:enable>
		                                <privilege:enable code="VIPQUIT">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER8"  iconCls="icon-VIPMANAGER8"
													onclick="addTab('VIPMANAGER8', '#tabs', '会员退会', '${pageContext.request.contextPath}/vip_management/vip_tuihui.jsp');"
													 class="easyui-linkbutton" plain="true"><span>会员退会</span></a>
											</li>
		                                </privilege:enable>
										<privilege:enable code="VIPCARDSERVER">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER11"  iconCls="icon-VIPMANAGER11"
													onclick="addTab('VIPMANAGER11', '#tabs', '会员卡服务项目', '${pageContext.request.contextPath}/vip_management/vipcard_server_project.jsp');"
													 class="easyui-linkbutton" plain="true"><span>会员卡服务项目</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="VIPACCOUNT">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER11"  iconCls="icon-VIPMANAGER11"
													onclick="addTab('VIPMANAGER12', '#tabs', '会员对账单', '${pageContext.request.contextPath}/vip_management/vipAccount.jsp');"
													 class="easyui-linkbutton" plain="true"><span>会员对账单</span></a>
											</li>
										</privilege:enable>
									</ul>
								</div>
	                        </privilege:enable>
	                        <privilege:enable code="CUSTOMERSERVER">
								<div title="客户服务" iconCls="icon-folder">
									<ul>
		                                <privilege:enable code="CUSTOMERFOLLOW">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="CUSTOMERSERVER2"  iconCls="icon-CUSTOMERSERVER2"
													onclick="addTab('CUSTOMERSERVER2', '#tabs', '客户跟踪管理', '${pageContext.request.contextPath}/return_visit/customer_gz.jsp');"
													 class="easyui-linkbutton" plain="true"><span>客户跟踪管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="CUSTOMERCENTER">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="CUSTOMERSERVER3"  iconCls="icon-CUSTOMERSERVER3"
													onclick="addTab('CUSTOMERSERVER3', '#tabs', '客户关怀中心', '${pageContext.request.contextPath}/return_visit/customer_care.jsp?tag=0');"
													 class="easyui-linkbutton" plain="true"><span>客户关怀中心</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="DAILYCUSTFOLLOW">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="CUSTOMERSERVER4"  iconCls="icon-CUSTOMERSERVER4"
													onclick="addTab('CUSTOMERSERVER4', '#tabs', '维修建议管理', '${pageContext.request.contextPath}/return_visit/daily_client_tracking.jsp');"
													 class="easyui-linkbutton" plain="true"><span>维修建议管理</span></a>
											</li>
										</privilege:enable>
										<!--<privilege:enable code="CUSTFOLLOWCOUNT">
											<li>
												<a name="icona" href="javascript:void(0);" id="khgztj_id"   iconName="CUSTOMERSERVER5"  iconCls="icon-CUSTOMERSERVER5"
													onclick="addTab('CUSTOMERSERVER5', '#tabs', '客户跟踪统计', '${pageContext.request.contextPath}/return_visit/customer_gztj.jsp');"
													 class="easyui-linkbutton" plain="true"><span>客户跟踪统计</span></a>
											</li>
										</privilege:enable>-->
									</ul>
								</div>
	                        </privilege:enable>
	                        
	                          <privilege:enable code="COMBINEOPERATION">
								<div title="集团操作" iconCls="icon-folder">
									<ul>
									    <privilege:enable code="COMBINEFRTCOUNT">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="COMBINEOPERATION1"  iconCls="icon-COMBINEOPERATION1"
													onclick="addTab('COMBINEOPERATION1', '#tabs', '接车台次分析', '${pageContext.request.contextPath}/combine/receptionCountAnalyse.jsp');"
													 class="easyui-linkbutton" plain="true"><span>接车台次分析</span></a>
											</li>
		                                </privilege:enable>
		                                 <privilege:enable code="COMBINEPRECOUNT">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="COMBINEOPERATION2"  iconCls="icon-COMBINEOPERATION2"
													onclick="addTab('COMBINEOPERATION2', '#tabs', '结算台次分析', '${pageContext.request.contextPath}/combine/balanceCountAnalyse.jsp');"
													 class="easyui-linkbutton" plain="true"><span>结算台次分析</span></a>
											</li>
		                                </privilege:enable>
		                                <privilege:enable code="COMBINEPRERATE">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="COMBINEOPERATION3"  iconCls="icon-COMBINEOPERATION3"
													onclick="addTab('COMBINEOPERATION3', '#tabs', '结算费用分析', '${pageContext.request.contextPath}/combine/balanceRateAnalyse.jsp');"
													 class="easyui-linkbutton" plain="true"><span>结算费用分析</span></a>
											</li>
		                                </privilege:enable>
		                                <privilege:enable code="ACCOUNTRECEIVE">
											<li>
												<a name="icona" href="javascript:void(0);"   iconName="COMBINEOPERATION3"  iconCls="icon-COMBINEOPERATION3"
													onclick="addTab('COMBINEOPERATION3', '#tabs', '应收账款分析', '${pageContext.request.contextPath}/combine/accountReceivableAnalyse.jsp');"
													 class="easyui-linkbutton" plain="true"><span>应收账款分析</span></a>
											</li>
		                                </privilege:enable>
									</ul>
								</div>
	                        </privilege:enable>
	                        
	                        <privilege:enable code="SYSTEMOPER">
								<div title="系统操作" iconCls="icon-folder">
									<ul>
										<privilege:enable code="PARTSCHECK">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="SYSTEMOPER3"  iconCls="icon-SYSTEMOPER3"
													onclick="addTab('SYSTEMOPER3', '#tabs', '配件盘点', '${pageContext.request.contextPath}/system_management/accesssories_inventory.jsp');"
													 class="easyui-linkbutton" plain="true"><span>配件盘点</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="CHECKBILLSSEA">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="SYSTEMOPER4"  iconCls="icon-SYSTEMOPER4"
													onclick="addTab('SYSTEMOPER4', '#tabs', '盘点单查询', '${pageContext.request.contextPath}/system_management/inventory_find.jsp');"
													 class="easyui-linkbutton" plain="true"><span>盘点单查询</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="ACHIEVEMENT">
											<li>
											   <div>
													<a name="icona" href="javascript:void(0);"  iconName="SYSTEMOPER5"  iconCls="icon-SYSTEMOPER5"
													onclick="controlManage('systemShow',3);"
													 class="easyui-linkbutton" plain="true"><span>员工业绩统计</span></a>											   		
											   </div> 
											    <div id="systemShow" style="display:none;width:200px;" >
													<ul>
														<privilege:enable code="ACHIEVEPERSON">
												          <li><a name="icona" href="javascript:void(0);" iconName="ACHIEVEMENT1"  iconCls="icon-ACHIEVEMENT1"
															onclick="addTab('ACHIEVEMENT1', '#tabs', '维修人员业绩统计', '${pageContext.request.contextPath}/system_management/customer_performance_statistics/weixiuyejitongji.jsp');"
															 class="easyui-linkbutton" plain="true"><span>维修人员业绩统计</span></a></li>
														</privilege:enable>
														<privilege:enable code="ACHIEVETIME">
													      <li><a name="icona" href="javascript:void(0);" iconName="ACHIEVEMENT2"  iconCls="icon-ACHIEVEMENT2"
															onclick="addTab('ACHIEVEMENT2', '#tabs', '结算工时查询', '${pageContext.request.contextPath}/system_management/customer_performance_statistics/jiesuangongshichaxun.jsp');"
															 class="easyui-linkbutton" plain="true"><span>结算工时查询</span></a></li>
														</privilege:enable>
														<privilege:enable code="ACHIEVECLAIMS">
													      <li><a name="icona" href="javascript:void(0);" iconName="ACHIEVEMENT3"  iconCls="icon-ACHIEVEMENT3"
															onclick="addTab('ACHIEVEMENT3', '#tabs', '索赔结算工时统计', '${pageContext.request.contextPath}/system_management/customer_performance_statistics/suopeijiesuangongshitongji.jsp');"
															 class="easyui-linkbutton" plain="true"><span>索赔结算工时统计</span></a></li>
														</privilege:enable>
														<privilege:enable code="ACHIEVEPERSONMAIN">
														  <li><a name="icona" href="javascript:void(0);" iconName="ACHIEVEMENT4"  iconCls="icon-ACHIEVEMENT4"
															onclick="addTab('ACHIEVEMENT4', '#tabs', '维修人员业绩统计汇总', '${pageContext.request.contextPath}/system_management/customer_performance_statistics/weixiuyejitongjihuizong.jsp');"
															 class="easyui-linkbutton" plain="true"><span>维修人员业绩统计汇总</span></a></li>
														</privilege:enable>
												    </ul>  	   
												</div>
										    </li>
										</privilege:enable>
										<privilege:enable code="LOGMANAGER">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="SYSTEMOPER6"  iconCls="icon-SYSTEMOPER6" 
													class="easyui-linkbutton"
													onclick="addTab('SYSTEMOPER6', '#tabs', '日志管理', '${pageContext.request.contextPath}/system_management/system_log.jsp');"
													plain="true"><span>系统日志</span></a>
											</li>
										</privilege:enable>
										<!--<privilege:enable code="LOWSANALYSiS">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="SYSTEMOPER7"  iconCls="icon-SYSTEMOPER7"
													onclick="addTab('SYSTEMOPER7', '#tabs', '车辆流失情况分析', '${pageContext.request.contextPath}/system_management/car_lost_info.jsp');"
													 class="easyui-linkbutton" plain="true"><span>车辆流失情况分析</span></a>
											</li>
										</privilege:enable>-->
										<privilege:enable code="GUESTFLOWANALY">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="SYSTEMOPER8"  iconCls="icon-SYSTEMOPER8"
													onclick="addTab('SYSTEMOPER8', '#tabs', '维修客流分析', '${pageContext.request.contextPath}/system_management/maintenance_traffic_analysis.jsp');"
													 class="easyui-linkbutton" plain="true"><span>维修客流分析</span></a>
											</li>
										</privilege:enable>
										
										<privilege:enable code="GUESTANALY">
											<li>
											   <div>
													<a name="icona" href="javascript:void(0);"  iconName="SYSTEMOPER8"  iconCls="icon-SYSTEMOPER8"
													onclick="controlManage('guestShow',5);"
													 class="easyui-linkbutton" plain="true"><span>维修客流分析</span></a>											   		
											   </div> 
											    <div id="guestShow" style="display:none;width:200px;" >
													<ul>
														<privilege:enable code="GUESTANALY_LOG">
												          <li><a name="icona" href="javascript:void(0);" iconName="SYSTEMOPER10"  iconCls="icon-SYSTEMOPER10"
															onclick="addTab('SYSTEMOPER10', '#tabs', '维修记录查询', '${pageContext.request.contextPath}/system_management/maintenance_traffic_analysis/serviceLog.jsp');"
															 class="easyui-linkbutton" plain="true"><span>维修记录查询</span></a></li>
														</privilege:enable>
														<privilege:enable code="GUESTANALY_TIME">
													      <li><a name="icona" href="javascript:void(0);" iconName="SYSTEMOPER11"  iconCls="icon-SYSTEMOPER11"
															onclick="addTab('SYSTEMOPER11', '#tabs', '维修时间段分析', '${pageContext.request.contextPath}/system_management/maintenance_traffic_analysis/serviceTimeAnalyze.jsp');"
															 class="easyui-linkbutton" plain="true"><span>维修时间段分析</span></a></li>
														</privilege:enable>
														<privilege:enable code="GUESTANALY_PERSON">
													      <li><a name="icona" href="javascript:void(0);" iconName="SYSTEMOPER12"  iconCls="icon-SYSTEMOPER12"
															onclick="addTab('SYSTEMOPER12', '#tabs', '维修类别与接待员分析', '${pageContext.request.contextPath}/system_management/maintenance_traffic_analysis/serviceReceiveAnalyze.jsp');"
															 class="easyui-linkbutton" plain="true"><span>维修类别与接待员分析</span></a></li>
														</privilege:enable>
														<!--<privilege:enable code="GUESTANALY_BRAND">
														  <li><a name="icona" href="javascript:void(0);" iconName="SYSTEMOPER13"  iconCls="icon-SYSTEMOPER13"
															onclick="addTab('SYSTEMOPER13', '#tabs', '维修品牌分析', '${pageContext.request.contextPath}/system_management/maintenance_traffic_analysis/serviceBrandAnalyze.jsp');"
															 class="easyui-linkbutton" plain="true"><span>维修品牌分析</span></a></li>
														</privilege:enable>
														<privilege:enable code="GUESTANALY_SERVICE">
														  <li><a name="icona" href="javascript:void(0);" iconName="SYSTEMOPER14"  iconCls="icon-SYSTEMOPER14"
															onclick="addTab('SYSTEMOPER14', '#tabs', '车辆在修情况分析', '${pageContext.request.contextPath}/system_management/maintenance_traffic_analysis/servicingCarAnalyze.jsp');"
															 class="easyui-linkbutton" plain="true"><span>车辆在修情况分析</span></a></li>
														</privilege:enable>-->
												    </ul>  	   
												</div>
										    </li>
										</privilege:enable>
										<!--
										<privilege:enable code="ACHIEVEMENTREPORT">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="SYSTEMOPER9"  iconCls="icon-SYSTEMOPER9" 
												    onclick="addTab('SYSTEMOPER9', '#tabs', '服务业绩日报表', '${pageContext.request.contextPath}/system_management/performance_daily_report.jsp');"
													 class="easyui-linkbutton" plain="true"><span>服务业绩日报表</span></a>
											</li>
										</privilege:enable>-->
										<privilege:enable code="SMSMANAGER">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER9"  iconCls="icon-VIPMANAGER9"
													onclick="addTab('VIPMANAGER9', '#tabs', '短信发送管理', '${pageContext.request.contextPath}/vip_management/infomation_send_manag.jsp');"
													 class="easyui-linkbutton" plain="true"><span>短信发送管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="SMSSEACH">
											<li>
												<a name="icona" href="javascript:void(0);"  iconName="VIPMANAGER10"  iconCls="icon-VIPMANAGER10"
													onclick="addTab('VIPMANAGER10', '#tabs', '短信记录查询', '${pageContext.request.contextPath}/vip_management/sms_record_management.jsp');"
													 class="easyui-linkbutton" plain="true"><span>短信记录查询</span></a>
											</li>
										</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
						</div>
					</div>
				</privilege:enable>
				<privilege:enable code="VENDITION">
					<div title="  销 售 系 统   " fit="true" border="false">
						<div class="easyui-accordion" animate="false" fit="true">
						    <privilege:enable code="XSSYSTEMOPT">
								<div title="系统设置" iconCls="icon-folder">
									<ul>
									   <privilege:enable code="XSENTERPRISEINFO">
										    <li>
												<a name="icona" href="javascript:void(0);" iconName="ENTERPRISE"  iconCls="icon-ENTERPRISE"
													onclick="addTab('SYSTEMSET0', '#tabs', '企业管理', '${pageContext.request.contextPath}/common/base/enterpriseInfo/enterpriseInfo.jsp');"
													class="easyui-linkbutton" plain="true"><span>企业管理</span></a>		
											</li>
										</privilege:enable>
									    <privilege:enable code="XSPERSON">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="PERSONSET"  iconCls="icon-PERSONSET"
													onclick="addTab('XSSYSTEMOPT1', '#tabs', '人事资料设定', '${pageContext.request.contextPath}/common/base/personnel/personnelInformation.jsp?sysType=<%=Contstants.SYSTEMTYPE.XIAOSHOU %>');"
													plain="true"><span>人事资料设定</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSDATASAFEMANAGE">
											<li>
												<a name="icona" href="javascript:void(0);" 
												 class="easyui-linkbutton" iconName="BACK"  iconCls="icon-BACK"
												 onclick="addTab('SYSTEMSET5', '#tabs', '数据库管理', '${pageContext.request.contextPath}/common/base/datebase/data_backup.jsp');"
												 plain="true"><span>数据库管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSMENUMANAGE">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="MENUMANAGER"  iconCls="icon-MENUMANAGER"
													onclick="addTab('XSSYSTEMOPT3', '#tabs', '菜单管理', '${pageContext.request.contextPath}/common/base/menu/menuInformation.jsp');"
													plain="true"><span>菜单管理</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSRALEMANAGE">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="AUTHORITYSET"  iconCls="icon-AUTHORITYSET"
													onclick="addTab('XSSYSTEMOPT4', '#tabs', '权限设置', '${pageContext.request.contextPath}/common/base/role/roleInformation.jsp');"
													plain="true"><span>权限设置</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSUPDATEPASSWORD">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" iconName="UPDATEPASSWORD"  iconCls="icon-UPDATEPASSWORD"
													onclick="addTab('XSSYSTEMOPT5', '#tabs', '修改密码', '${pageContext.request.contextPath}/common/base/password/password.jsp');"
													plain="true"><span>用户密码修改</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSDRUIDDBSOURCE">
										    <li>
												<a name="icona" href="javascript:void(0);"
												class="easyui-linkbutton" iconName="DRUIDDBSOURCE"  iconCls="icon-DRUIDDBSOURCE"
											    onclick="addTab('SYSTEMSET6', '#tabs', 'Druid数据源监控', '${pageContext.request.contextPath}/druid/index.html');"
												plain="true"><span>Druid数据源监控</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSCOMPANYINFOR">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="ENTERPRISE1"  iconCls="icon-ENTERPRISE1"
												onclick="addTab('XSBASEDATA14', '#tabs', '公司信息设定', '${pageContext.request.contextPath}/base/companyInformationSet.jsp');"
												class="easyui-linkbutton" plain="true"><span>公司信息设定</span></a>		
										</li>
										</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
							<privilege:enable code="XSBASEDATA">
								<div title="基础资料" iconCls="icon-folder">
									<ul>
										<li>
											<a name="icona" href="javascript:void(0);" iconName="BASESET"  iconCls="icon-BASESET"
											onclick="addTab('XSBASEDATA1', '#tabs', '基本数据设置', '${pageContext.request.contextPath}/sell/basedata/BasicDataSet.jsp');"
												class="easyui-linkbutton" plain="true"><span>基本数据设置</span></a>
										</li>
										<privilege:enable code="XSDATADICTIONARY">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="DATASET"  iconCls="icon-DATASET"
											onclick="addTab('XSBASEDATA2', '#tabs', '数据字典', '${pageContext.request.contextPath}/sell/basedata/BasicDataSet_sell.jsp');"
												class="easyui-linkbutton" plain="true"><span>数据字典</span></a>
										</li>
										</privilege:enable>
										
										<privilege:enable code="XSCARMODEL">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="CARMODEL"  iconCls="icon-CARMODEL"
													onclick="addTab('XSBASEDATA3', '#tabs', '车辆型号资料', '${pageContext.request.contextPath}/sell/carModel/carModel.jsp');"
													class="easyui-linkbutton" plain="true"><span>车辆型号资料</span></a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSCARARCHIVES">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CARINFO"  iconCls="icon-CARINFO"
												onclick="addTab('XSBASEDATA4', '#tabs', '车辆档案', '${pageContext.request.contextPath}/sell/carInfo/carInfo.jsp');"
												class="easyui-linkbutton" plain="true"><span>车辆档案</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSCARARCHIVESKZ">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>车辆档案扩展</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSCARUPINFOR">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CARINFO"  iconCls="icon-CARINFO"
												onclick="addTab('XSBASEDATA281', '#tabs', '车辆上报管理', '${pageContext.request.contextPath}/sell/carInfo/carUpInfo.jsp');"
												class="easyui-linkbutton" plain="true"><span>车辆上报管理</span></a>						
										</li>
										</privilege:enable>
										<privilege:enable code="XSCUSTOMARCHIVES">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
												onclick="addTab('XSBASEDATA6', '#tabs', '客户档案', '${pageContext.request.contextPath}/sell/customInfo/customInfo.jsp');"
												class="easyui-linkbutton" plain="true"><span>客户档案</span></a>						
										</li>
										</privilege:enable>
										<privilege:enable code="XSSUPPLIERINFOR">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="SUPPLIER"  iconCls="icon-SUPPLIER"
											    onclick="addTab('XSBASEDATA7', '#tabs', '供应商档案', '${pageContext.request.contextPath}/sell/supplierInfo/supplierInfo.jsp');"
												class="easyui-linkbutton" plain="true"><span>供应商档案</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSPROVIDEBANK">
										
										<li>
											<a name="icona" href="javascript:void(0);" iconName="PROVIDEBANK"  iconCls="icon-PROVIDEBANK"
												onclick="addTab('XSBASEDATA8', '#tabs', '贷款银行档案', '${pageContext.request.contextPath}/sell/providebank/providebank.jsp');"
												class="easyui-linkbutton" plain="true"><span>贷款银行档案</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSDISTRIBUTORINFOR">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="DISTRIBUTOR"  iconCls="icon-DISTRIBUTOR"
												onclick="addTab('XSBASEDATA9', '#tabs', '分销售商档案', '${pageContext.request.contextPath}/sell/DistributorInfo/DistributorInfo.jsp');"
												class="easyui-linkbutton" plain="true"><span>分销售商档案</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSINSURERINFOR">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="INSURER"  iconCls="icon-INSURER"
												onclick="addTab('XSBASEDATA10', '#tabs', '保险公司档案', '${pageContext.request.contextPath}/sell/insurerInfo/insurerInfo.jsp');"
												class="easyui-linkbutton" plain="true"><span>保险公司档案</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSSELLTARGET">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="SELLTARGET"  iconCls="icon-SELLTARGET"
												onclick="addTab('XSBASEDATA11', '#tabs', '销售指标设定', '${pageContext.request.contextPath}/sell/sellTarget/sellTarget.jsp');"
												class="easyui-linkbutton" plain="true"><span>销售指标设定</span></a>		
										</li>
										</privilege:enable>
										<privilege:enable code="XSSYSTEMPARAM">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CAIGOUDAN"  iconCls="icon-CAIGOUDAN"
											class="easyui-linkbutton" plain="true" onclick="addTab('XSBASEDATA13', '#tabs', '系统参数设定', '${pageContext.request.contextPath}/sell/sellSystemParamSet/parameterSet.jsp');">
												<span>系统参数设定</span></a>
										</li>
										</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
							<privilege:enable code="XSSTOREHOUSE">
								<div title="仓库管理" iconCls="icon-folder">
									<ul>
									    <privilege:enable code="XSPROCUREMENT">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="CAIGOUDAN"  iconCls="icon-CAIGOUDAN"
													onclick="addTab('XSSTOREHOUSE1', '#tabs', '采购计划管理', '${pageContext.request.contextPath}/sell/sellPurchase/sellPurchase.jsp');"
													class="easyui-linkbutton" plain="true"><span>采购计划管理</span></a>
											</li>
									    </privilege:enable>
									    <privilege:enable code="XSINSTOREHOUSE">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="INSTOREHOUSE"  iconCls="icon-INSTOREHOUSE"
											onclick="addTab('XSSTOREHOUSE2', '#tabs', '入库单管理', '${pageContext.request.contextPath}/sell/foreordain/foreordainInfo.jsp');"
												class="easyui-linkbutton" plain="true"><span>入库单管理</span></a>
										</li>
										</privilege:enable>
										
										 <privilege:enable code="XSCARBANK">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="RETREAT"  iconCls="icon-RETREAT"
												onclick="addTab('XSSTOREHOUSE4', '#tabs', '退厂单管理', '${pageContext.request.contextPath}/sell/carback/carback.jsp');"
												class="easyui-linkbutton" plain="true"><span>退厂单管理</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSINSTOREOUT">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="INSTOREOUT"  iconCls="icon-INSTOREOUT"
												onclick="addTab('XSSTOREHOUSE5', '#tabs', '车辆出库管理', '${pageContext.request.contextPath}/sell/instoreOut/instoreOut.jsp');"
												class="easyui-linkbutton" plain="true"><span>车辆出库管理</span></a>
										</li>
										</privilege:enable>
										
										<privilege:enable code="XSINSTOREQUERY">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="INSTOREQUERY"  iconCls="icon-INSTOREQUERY"
												onclick="addTab('XSSTOREHOUSE7', '#tabs', '入库单查询', '${pageContext.request.contextPath}/sell/instorehouseQuery/queryInstore.jsp');"
												class="easyui-linkbutton" plain="true"><span>入库单查询</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSCARBACKQUERY">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CARBACKQUERY"  iconCls="icon-CARBACKQUERY"
												onclick="addTab('XSSTOREHOUSE8', '#tabs', '退厂单查询', '${pageContext.request.contextPath}/sell/carBackQuery/carBackQuery.jsp');"
												class="easyui-linkbutton" plain="true"><span>退厂单查询</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSINSTOREOUTQUERY">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="INSTOREOUTQUERY"  iconCls="icon-INSTOREOUTQUERY"
												onclick="addTab('XSSTOREHOUSE9', '#tabs', '出库单查询', '${pageContext.request.contextPath}/sell/instoreOutQuery/queryInstoreOut.jsp');"
												class="easyui-linkbutton" plain="true"><span>出库单查询</span></a>
										</li>
										</privilege:enable>
										
							        	<privilege:enable code="XSINVENTORYQUERY">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CARKZ"  iconCls="icon-CARKZ"
												onclick="addTab('XSSTOREHOUSE11', '#tabs', '库存量查询', '${pageContext.request.contextPath}/sell/inventoryQuery/inventory.jsp');"
												class="easyui-linkbutton" plain="true"><span>库存量查询</span></a>
										</li>
										</privilege:enable>
										
										<privilege:enable code="XSCARSERVICING">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="SERVICING"  iconCls="icon-SERVICING"
												onclick="addTab('XSSTOREHOUSE13', '#tabs', '车辆维护管理', '${pageContext.request.contextPath}/sell/sellServicing/sellServicing.jsp');"
												class="easyui-linkbutton" plain="true"><span>车辆维护管理</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSINSTOREMOVE">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="INSTOREMOVE"  iconCls="icon-INSTOREMOVE"
												onclick="addTab('XSSTOREHOUSE14', '#tabs', '车辆移库管理', '${pageContext.request.contextPath}/sell/moveStore/instoreMove.jsp');"
												class="easyui-linkbutton" plain="true"><span>车辆移库管理</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSFINANCEMANAGE">
											<li>
												<a name="icona" iconName="CAIGOUDAN"  iconCls="icon-CAIGOUDAN" href="javascript:void(0);" 
													class="easyui-linkbutton" plain="true"
													onclick="addTab('XSSTOREHOUSE15', '#tabs', '库存月结转', '${pageContext.request.contextPath}/sell/sell_financemanage/monthly_statement.jsp');"
													><span>库存月结转</span>
												</a>
											</li>
									    </privilege:enable>
									    
									   <privilege:enable code="XSBORROWMANAGE">
										<li>
											<a name="icona" href="javascript:void(0);" 
											onclick="addTab('XSSTOREHOUSE3', '#tabs', '借入单管理', '${pageContext.request.contextPath}/sell/borrow/borrowInfo.jsp');"
												class="easyui-linkbutton" plain="true"><span>借入单管理</span></a>
										</li>
										</privilege:enable>
										
										<privilege:enable code="XSPREPAREQUERY">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>预入单查询</span></a>
										</li>
										</privilege:enable>
										
										<privilege:enable code="XSSELLQUERY">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>车辆销售查询</span></a>
										</li>
										</privilege:enable>
										<privilege:enable code="XSCARADD">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>车辆加装管理</span></a>
										</li>
										</privilege:enable>
										
									</ul>
								</div>
							</privilege:enable>
							<privilege:enable code="XSDISTRIBUTION">
								<div title="分销作业" iconCls="icon-folder">
									<ul>
									    <privilege:enable code="XSALLOCATEMANAGE">
											<li>
												<a name="icona" iconName="CAIGOUDAN"  iconCls="icon-CAIGOUDAN" href="javascript:void(0);" 
													class="easyui-linkbutton" plain="true"
													onclick="addTab('XSDISTRIBUTION1', '#tabs', '调拨单管理', '${pageContext.request.contextPath}/sell/allocateManage/allocateManage.jsp');"
													><span>调拨单管理</span>
												</a>
											</li>
									    </privilege:enable>
									    <privilege:enable code="XSALLOCATEBACK">
										<li>
											<a name="icona" iconName="BASESET6"  iconCls="icon-BASESET6" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"
												onclick="addTab('XSDISTRIBUTION2', '#tabs', '调退单管理', '${pageContext.request.contextPath}/sell/allocateBack/allocateBack.jsp');">
												<span>调退单管理</span>
											</a>
										</li>
										</privilege:enable>
										 <privilege:enable code="XSALLOCATEQUERY">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CARKZ"  iconCls="icon-CARKZ"
												class="easyui-linkbutton" plain="true"
												onclick="addTab('XSDISTRIBUTION3', '#tabs', '调拨单查询', '${pageContext.request.contextPath}/sell/allocateQuery/queryAllocateList.jsp');">
												<span>调拨单查询</span>
											</a>
										</li>
										</privilege:enable>
										 <privilege:enable code="XSALLOCATEBACKQUERY">
										<li>
											<a name="icona" href="javascript:void(0);"  iconName="CARKZ"  iconCls="icon-CARKZ"
												class="easyui-linkbutton" plain="true"
												onclick="addTab('XSDISTRIBUTION4', '#tabs', '调退单查询', '${pageContext.request.contextPath}/sell/allocateBackQuery/queryAllocateBack.jsp');"><span>调退单查询</span>
											</a>
										</li>
										</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
							<privilege:enable code="XSSALESOPERATION ">
								<div title="销售作业" iconCls="icon-folder">
									<ul>
									    <privilege:enable code="XSREGISTRATION">
											<li>
												<a name="icona" href="javascript:void(0);" 
													class="easyui-linkbutton" plain="true" iconName="CARMODEL"  iconCls="icon-CARMODEL"
													onclick="addTab('XSSALESOPERATION1', '#tabs', '来电（店）登记', '${pageContext.request.contextPath}/sell/sell_work/back_register.jsp');"
													><span>来店(电)登记</span>
												</a>
											</li>
									    </privilege:enable>
										
										<li>
											<div>
												<a name="icona" href="javascript:void(0);"  iconName="QUERYCUSTOM"  iconCls="icon-SYSTEMOPER5"
												onclick="controlManage('register_assay_id',3);"
												 class="easyui-linkbutton" plain="true"><span>來店(电)客流分析</span></a>											   		
											 </div> 
											<div id="register_assay_id"
												style="display: none; width: 170px;">
												<ul>
													<li>
														<a name="icona" href="javascript:void(0);"
															class="easyui-linkbutton" plain="true"
															iconName="CARMODEL" iconCls="icon-CARMODEL"
															onclick="addTab('XSSALESOPERATION3', '#tabs', '客流时间段分析', '${pageContext.request.contextPath}/sell/sell_work/register_time_assay.jsp');"><span>客流时间段分析</span>
														</a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);"
															class="easyui-linkbutton" plain="true"
															iconName="CARMODEL" iconCls="icon-CARMODEL"
															onclick="addTab('XSSALESOPERATION4', '#tabs', '客流业务员分析', '${pageContext.request.contextPath}/sell/sell_work/register_worker.jsp');"><span>客流业务员分析</span>
														</a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);"
															class="easyui-linkbutton" plain="true"
															iconName="CARMODEL" iconCls="icon-CARMODEL"
															onclick="addTab('XSSALESOPERATION5', '#tabs', '客流车型分析', '${pageContext.request.contextPath}/sell/sell_work/register_carmodel.jsp');"><span>客流车型分析</span>
														</a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);"
															class="easyui-linkbutton" plain="true"
															iconName="CARMODEL" iconCls="icon-CARMODEL"
															onclick="addTab('XSSALESOPERATION6', '#tabs', '接待员车型分析', '${pageContext.request.contextPath}/sell/sell_work/register_user_carmodel.jsp');"><span>接待员车型分析</span>
														</a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);"
															class="easyui-linkbutton" plain="true"
															iconName="CARMODEL" iconCls="icon-CARMODEL"
															onclick="addTab('XSSALESOPERATION7', '#tabs', '接待员品牌分析', '${pageContext.request.contextPath}/sell/sell_work/register_carBrand.jsp');"><span>接待员品牌分析</span>
														</a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);"
															class="easyui-linkbutton" plain="true"
															iconName="CARMODEL" iconCls="icon-CARMODEL"
															onclick="addTab('XSSALESOPERATION8', '#tabs', '客流车来源分析', '${pageContext.request.contextPath}/sell/sell_work/register_customSource.jsp');"><span>客流车来源分析</span>
														</a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);"
															class="easyui-linkbutton" plain="true"
															iconName="CARMODEL" iconCls="icon-CARMODEL"
															onclick="addTab('XSSALESOPERATION9', '#tabs', '客流车级别分析', '${pageContext.request.contextPath}/sell/sell_work/register_carLevel.jsp');"><span>客流车级别分析</span>
														</a>
													</li>
												</ul>
											</div>
										</li>


										<privilege:enable code="XSREGISTERLOOK">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" plain="true" iconName="CARMODEL"
													iconCls="icon-CARMODEL"
													onclick="addTab('XSSALESOPERATION2', '#tabs', '客流登记浏览', '${pageContext.request.contextPath}/sell/sell_work/back_register_look.jsp');"><span>客流登记浏览</span>
												</a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSCUSTOMFOLLOW">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true" iconName="CARMODEL"  iconCls="icon-CARMODEL"
												onclick="addTab('XSSALESOPERATION10', '#tabs', '潜在客户跟踪', '${pageContext.request.contextPath}/sell/sell_work/possible_custom_follow.jsp');"	
												><span>潜在客户跟踪</span>
											</a>
										</li>
										 </privilege:enable >
										 
										<privilege:enable code="XSCARESERVE">
											<li>
												<a name="icona" href="javascript:void(0);"
													iconName="CARMODEL" iconCls="icon-CARMODEL"
													class="easyui-linkbutton" plain="true"
													onclick="addTab('XSSALESOPERATION11', '#tabs', '车辆预订管理', '${pageContext.request.contextPath}/sell/sell_work/car_book.jsp');">
													<span>车辆预订管理</span>
												</a>
											</li>
										</privilege:enable>

										<privilege:enable code="XSCARALLOCATE">
											<li>
												<a name="icona" href="javascript:void(0);"
													iconName="CARMODEL" iconCls="icon-CARMODEL"
													class="easyui-linkbutton" plain="true"
													onclick="addTab('XSSALESOPERATION12', '#tabs', '车辆调配作业', '${pageContext.request.contextPath}/sell/carAllocateWork/carAllocate.jsp');">
													<span>车辆调配作业</span>
												</a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSCARSELLMANAGE">
											<li>
												<div>
													<a name="icona" href="javascript:void(0);"
														iconName="SYSTEMOPER5" iconCls="icon-SYSTEMOPER5"
														onclick="controlManage('car_sell_id',3);"
														class="easyui-linkbutton" plain="true"><span>车辆销售管理</span>
													</a>
												</div>
												<div id="car_sell_id" style="display: none; width: 170px;">
													<ul>
														<privilege:enable code="XSCARSELL">
															<li>
																<a name="icona" href="javascript:void(0);"
																	iconName="CARMODEL" iconCls="icon-CARMODEL" id="mb1"
																	class="easyui-linkbutton" plain="true"
																	onclick="addTab('XSSALESOPERATION13', '#tabs', '车辆销售', '${pageContext.request.contextPath}/sell/sell_work/car_sell_manage.jsp');">
																	<span>车辆销售</span> </a>
															</li>
														</privilege:enable>
														<privilege:enable code="XSPDICHECk">
															<li>
																<a name="icona" href="javascript:void(0);"
																	iconName="CARMODEL" iconCls="icon-CARMODEL" id="mb1"
																	class="easyui-linkbutton" plain="true"
																	onclick="addTab('1XSSALESOPERATION14', '#tabs', 'PDI检测', '${pageContext.request.contextPath}/sell/sell_work/pdi_check_record.jsp');">
																	<span>PDI检测</span> </a>
															</li>
														</privilege:enable>
													</ul>
												</div>
											</li>
										</privilege:enable>
										<privilege:enable code="XSCARSELLQUERY">
											<li>
												<a name="icona" href="javascript:void(0);"
													iconName="CARMODEL" iconCls="icon-CARMODEL"
													class="easyui-linkbutton" plain="true"
													onclick="addTab('XSSALESOPERATION15', '#tabs', '车辆销售查询', '${pageContext.request.contextPath}/sell/sell_work/querySellCarList.jsp');">
													<span>车辆销售查询</span>
												</a>
											</li>
										</privilege:enable>

										<privilege:enable code="XSQUITCAR">
											<li>
												<a name="icona" href="javascript:void(0);"
													iconName="CARMODEL" iconCls="icon-CARMODEL"
													class="easyui-linkbutton" plain="true"
													onclick="addTab('XSSALESOPERATION16', '#tabs', '销售退车管理', '${pageContext.request.contextPath}/sell/sell_work/quitcar_manage.jsp');">
													<span>销售退车管理</span>
												</a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSREPAYMANAGE">
											<li>
												<a name="icona" href="javascript:void(0);"
													iconName="CAIGOUDAN" iconCls="icon-CAIGOUDAN"
													class="easyui-linkbutton" plain="true"
													onclick="addTab('XSSALESOPERATION17', '#tabs', '售后回访管理', '${pageContext.request.contextPath}/sell/repayManage/repayManage.jsp');">
													<span>售后回访管理</span>
												</a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSSELLDBPRo">
											<li>
												<a name="icona" href="javascript:void(0);"
													iconName="SELLDBPRO" iconCls="icon-SELLDBPRO"
													class="easyui-linkbutton" plain="true"
													onclick="addTab('XSSALESOPERATION18', '#tabs', '销售代办项目', '${pageContext.request.contextPath}/sell/sellDbProject/sellDbProject.jsp');">
													<span>销售代办项目</span>
												</a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSSELLZHPRo">
											<li>
												<a name="icona" href="javascript:void(0);"
													iconName="SELLDECORATE" iconCls="icon-SELLDECORATE"
													class="easyui-linkbutton" plain="true"
													onclick="addTab('XSSALESOPERATION19', '#tabs', '装潢(销售及赠送)', '${pageContext.request.contextPath}/sell/sellDecorateProject/sellDecorateProject.jsp');">
													<span>装潢(销售及赠送)</span>
												</a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSCARFIX">
											<li>
												<a name="icona" href="javascript:void(0);"
													iconName="SELLADDPART" iconCls="icon-SELLADDPART"
													class="easyui-linkbutton" plain="true"
													onclick="addTab('XSSALESOPERATION21', '#tabs', '车辆加装申请', '${pageContext.request.contextPath}/sell/sell_work/car_fix.jsp');">
													<span>车辆加装申请</span>
												</a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSREPAYANALY">
											<li>
												<a name="icona" href="javascript:void(0);"
													iconName="SELLADDPART" iconCls="icon-SELLADDPART"
													onclick="addTab('XSSALESOPERATION022', '#tabs', '售后回访分析', '${pageContext.request.contextPath}/sell/repayManage/repay_analysis.jsp');"
													class="easyui-linkbutton" plain="true"> <span>售后回访分析</span>
												</a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSTRACKQUERY">
											<li>
												<a name="icona" href="javascript:void(0);" iconName="CARKZ"
													iconCls="icon-CARKZ" class="easyui-linkbutton" plain="true"
													onclick="addTab('XSSALESOPERATION24', '#tabs', '跟踪记录查询', '${pageContext.request.contextPath}/sell/trackRecordQuery/trackRecordQuery.jsp');"><span>跟踪记录查询</span>
												</a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSCARINFORQUERY">
											<li>
												<a name="icona" href="javascript:void(0);"
													iconName="QUERYCAR" iconCls="icon-QUERYCAR"
													class="easyui-linkbutton" plain="true"
													onclick="addTab('XSSALESOPERATION25', '#tabs', '车辆档案查询', '${pageContext.request.contextPath}/sell/queryCarInfo/queryCarInfo.jsp');"><span>车辆档案查询</span>
												</a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSCUSTOMINFORQUERY">
											<li>
												<a name="icona" href="javascript:void(0);"
													iconName="QUERYCUSTOM" iconCls="icon-QUERYCUSTOM"
													onclick="addTab('XSSALESOPERATION26', '#tabs', '客户资料查询', '${pageContext.request.contextPath}/sell/queryCustomInfo/queryCustomInfo.jsp');"
													class="easyui-linkbutton" plain="true"><span>客户资料查询</span>
												</a>
											</li>
										</privilege:enable>
										<privilege:enable code="XSCARBACkBILL">
											<li>
												<a name="icona" href="javascript:void(0);"
													class="easyui-linkbutton" plain="true"><span>退车单查询</span>
												</a>
											</li>
										</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
							
							<privilege:enable code="XSCOLLIGATE">
								<div title="综合分析" iconCls="icon-folder">
									<ul>
											<li>
												<div>
													<a name="icona" href="javascript:void(0);"  iconName="QUERYCUSTOM"  iconCls="icon-SYSTEMOPER5"
													onclick="controlManage('car_sell_assay_id',3);"
													 class="easyui-linkbutton" plain="true"><span>车辆销售分析</span></a>											   		
											   </div> 
											   <div id="car_sell_assay_id"  style="display:none;width:170px;">
											   		<ul>
											   		<li>
														<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
														onclick="addTab('XSCOLLIGATE1', '#tabs', '车辆日销售分析', '${pageContext.request.contextPath}/sell/synthesis_assay/dayreport_assay.jsp');"
														class="easyui-linkbutton" plain="true"><span>车辆日销售分析</span></a>
													</li>
											   		<li>
														<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
														onclick="addTab('XSCOLLIGATE2', '#tabs', '车辆月销售分析', '${pageContext.request.contextPath}/sell/synthesis_assay/monthreport_assay.jsp');"
														class="easyui-linkbutton" plain="true"><span>车辆月销售分析</span></a>
													</li>
												
													<li>
														<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
															onclick="addTab('XSCOLLIGATE3', '#tabs', '车辆销售方式分析', '${pageContext.request.contextPath}/sell/synthesis_assay/sellway_assay.jsp');"
															class="easyui-linkbutton" plain="true"><span>车辆销售方式分析</span></a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
															onclick="addTab('XSCOLLIGATE4', '#tabs', '业务员销售分析', '${pageContext.request.contextPath}/sell/synthesis_assay/workersell_assay.jsp');"
															class="easyui-linkbutton" plain="true"><span>业务员销售分析</span></a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
															onclick="addTab('XSCOLLIGATE6', '#tabs', '销售部门分析', '${pageContext.request.contextPath}/sell/synthesis_assay/department_assay.jsp');"
															class="easyui-linkbutton" plain="true"><span>销售部门分析</span></a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
															onclick="addTab('XSCOLLIGATE7', '#tabs', '销售班组分析', '${pageContext.request.contextPath}/sell/synthesis_assay/sellTeams_assay.jsp');"
															class="easyui-linkbutton" plain="true"><span>销售班组分析</span></a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
															onclick="addTab('XSCOLLIGATE8', '#tabs', '车辆客户档案', '${pageContext.request.contextPath}/sell/synthesis_assay/carAndCustomInfor.jsp');"
															class="easyui-linkbutton" plain="true"><span>车辆客户档案</span></a>
													</li>
											    
													</ul>
											   </div>
										</li>
										<li>
											<div>
												<a name="icona" href="javascript:void(0);"  iconName="QUERYCUSTOM"  iconCls="icon-SYSTEMOPER5"
												onclick="controlManage('car_custom_assay_id',3);"
												 class="easyui-linkbutton" plain="true"><span>潜在客户分析</span></a>											   		
										   </div> 
										   <div id="car_custom_assay_id"  style="display:none;width:170px;">
										   		<ul>
												<li>
													<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
									                 onclick="addTab('XSCOLLIGATE11', '#tabs', '成交障碍分析', '${pageContext.request.contextPath}/sell/synthesis_assay/bargain_trouble_assay.jsp');"
													class="easyui-linkbutton" plain="true"><span>成交障碍分析</span></a>
												</li>
												<li>
													<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
									                 onclick="addTab('XSCOLLIGATE12', '#tabs', '成交几率分析', '${pageContext.request.contextPath}/sell/synthesis_assay/bargain_probability_assay.jsp');"
													class="easyui-linkbutton" plain="true"><span>成交几率分析</span></a>
												</li>
												<li>
													<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
									                 onclick="addTab('XSCOLLIGATE13', '#tabs', '潜在客户来源', '${pageContext.request.contextPath}/sell/synthesis_assay/custom_source_assay.jsp');"
													class="easyui-linkbutton" plain="true"><span>潜在客户来源</span></a>
												</li>
												<li>
													<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
									                 onclick="addTab('XSCOLLIGATE14', '#tabs', '跟踪部门分析', '${pageContext.request.contextPath}/sell/synthesis_assay/dept_assay.jsp');"
													class="easyui-linkbutton" plain="true"><span>跟踪部门分析</span></a>
												</li>
										   		<li>
													<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
									                 onclick="addTab('XSCOLLIGATE15', '#tabs', '跟踪班组分析', '${pageContext.request.contextPath}/sell/synthesis_assay/sell_team_assay.jsp');"
													class="easyui-linkbutton" plain="true"><span>跟踪班组分析</span></a>
												</li>
												<li>
													<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
									                 onclick="addTab('XSCOLLIGATE16', '#tabs', '客户等级分析', '${pageContext.request.contextPath}/sell/synthesis_assay/customer_level_assay.jsp');"
													class="easyui-linkbutton" plain="true"><span>客户等级分析</span></a>
												</li>
												<li>
													<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
									                 onclick="addTab('XSCOLLIGATE17', '#tabs', '购买车型号分析', '${pageContext.request.contextPath}/sell/synthesis_assay/buy_carmodel_assay.jsp');"
													class="easyui-linkbutton" plain="true"><span>购买车型号分析</span></a>
												</li>
												<li>
													<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
									                 onclick="addTab('XSCOLLIGATE18', '#tabs', '跟踪业务员分析', '${pageContext.request.contextPath}/sell/synthesis_assay/stfname_assay.jsp');"
													class="easyui-linkbutton" plain="true"><span>跟踪业务员分析</span></a>
												</li>
										    
												</ul>
										   </div>
									</li>
										<li>
											<div>
												<a name="icona" href="javascript:void(0);"  iconName="QUERYCUSTOM"  iconCls="icon-SYSTEMOPER5"
												onclick="controlManage('lost_assay_id',3);"
												 class="easyui-linkbutton" plain="true"><span>放弃客户分析</span></a>											   		
										   </div> 
										   <div id="lost_assay_id"  style="display:none;width:170px;">
										   		<ul>
										    		<li>
														<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
										                 onclick="addTab('XSCOLLIGATE18', '#tabs', '战败客户列表', '${pageContext.request.contextPath}/sell/synthesis_assay/losed_customer_record.jsp');"
														class="easyui-linkbutton" plain="true"><span>战败客户列表</span></a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
										                 onclick="addTab('XSCOLLIGATE19', '#tabs', '战败原因分析', '${pageContext.request.contextPath}/sell/synthesis_assay/lose_bell_assay.jsp');"
														class="easyui-linkbutton" plain="true"><span>战败原因分析</span></a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
										                 onclick="addTab('XSCOLLIGATE20', '#tabs', '战败车型分析', '${pageContext.request.contextPath}/sell/synthesis_assay/lose_bell_carmodel_assay.jsp');"
														class="easyui-linkbutton" plain="true"><span>战败车型分析</span></a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
										                 onclick="addTab('XSCOLLIGATE21', '#tabs', '战败部门分析', '${pageContext.request.contextPath}/sell/synthesis_assay/lose_bell_depart_assay.jsp');"
														class="easyui-linkbutton" plain="true"><span>战败部门分析</span></a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
										                 onclick="addTab('XSCOLLIGATE22', '#tabs', '战败班组分析', '${pageContext.request.contextPath}/sell/synthesis_assay/lose_bell_group_assay.jsp');"
														class="easyui-linkbutton" plain="true"><span>战败班组分析</span></a>
													</li>
													<li>
														<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
										                 onclick="addTab('XSCOLLIGATE23', '#tabs', '战败业务分析', '${pageContext.request.contextPath}/sell/synthesis_assay/lose_bell_work_assay.jsp');"
														class="easyui-linkbutton" plain="true"><span>战败业务分析</span></a>
													</li>
												</ul>
										   </div>
										</li>
										<privilege:enable code="XSSELLCUSTOMANALYSIS">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
							                 onclick="addTab('XSCOLLIGATE9', '#tabs', '销售客户分析', '${pageContext.request.contextPath}/sell/analysis_by_synthesis/sellCustomAnalyze.jsp');"
											class="easyui-linkbutton" plain="true">
											<span>销售客户分析</span></a>
										</li>
										
										</privilege:enable>
										<li>
											<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
												onclick="addTab('XSCOLLIGATE5', '#tabs', '车辆进销存日报表', '${pageContext.request.contextPath}/sell/synthesis_assay/purchaseandsale_dailysheet.jsp');"
												class="easyui-linkbutton" plain="true"><span>车辆进销存日报表</span></a>
										</li>
									
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CUSTOMINFO"  iconCls="icon-CUSTOMINFO"
							                 onclick="addTab('XSCOLLIGATE10', '#tabs', '销售日报表', '${pageContext.request.contextPath}/sell/analysis_by_synthesis/sellDayReport.jsp');"
											class="easyui-linkbutton" plain="true"><span>销售日报表</span></a>
										</li>
										
										
									<privilege:enable code="XSCARKCLANALY">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
												onclick="addTab('XSCOLLIGATE011', '#tabs', '车辆库存量分析', '${pageContext.request.contextPath}/sell/analysis_by_synthesis/carStockAnalyze.jsp');"
												class="easyui-linkbutton" plain="true"><span>车辆库存量分析</span></a>
										</li>
									</privilege:enable>
								
									<privilege:enable code="XSDBPROQ">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
												onclick="addTab('XSCOLLIGATE012', '#tabs', '代办项目查询', '${pageContext.request.contextPath}/sell/sellDbProject/queryDbInfor.jsp');"
												class="easyui-linkbutton" plain="true"><span>代办项目查询</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSZSPROQ">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
												onclick="addTab('XSCOLLIGATE013', '#tabs', '装潢项目查询', '${pageContext.request.contextPath}/sell/sellDecorateProject/queryZhInfor.jsp');"
												class="easyui-linkbutton" plain="true"><span>装潢项目查询</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSBANKDKQ">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>银行贷款查询</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSCARDBQ">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
												onclick="addTab('XSCOLLIGATE014', '#tabs', '车辆代保查询', 
												'${pageContext.request.contextPath}/sell/SellInsurance/queryInsur/queryrInsurance.jsp');"
												class="easyui-linkbutton" plain="true"><span>车辆代保查询</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSSELLMLANALY">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="QUERYCUSTOM"  iconCls="icon-QUERYCUSTOM"
											onclick="addTab('XSCOLLIGATE015', '#tabs', '销售毛利分析', 
												'${pageContext.request.contextPath}/sell/analysis_by_synthesis/sellMlAnalyze.jsp');"
												class="easyui-linkbutton" plain="true">
												<span>销售毛利分析</span></a>
										</li>
									</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
							<privilege:enable code="XSCARINSURANCE">
								<div title="车辆保险" iconCls="icon-folder">
									<ul>
 								    <privilege:enable code="XSCUSTOMGH"> 
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>客户关怀管理</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSINSURANCE"> 
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CAIGOUDAN"  iconCls="icon-CAIGOUDAN"
											class="easyui-linkbutton" plain="true" onclick="addTab('XSCARINSURANCE1', '#tabs', '车辆保单管理', '${pageContext.request.contextPath}/sell/SellInsurance/sellInsurance.jsp');">
												<span>车辆保单管理</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSINSURANCEQUERY"> 
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true" iconName="CARKZ"  iconCls="icon-CARKZ"
												onclick="addTab('XSCARINSURANCE2', '#tabs', '车辆保单查询', '${pageContext.request.contextPath}/sell/SellInsurance/queryCarInsurance.jsp');">
												<span>车辆保单查询</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSCARSBQUERY"> 
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true" iconName="CARKZ"  iconCls="icon-CARKZ"
												onclick="addTab('XSCARINSURANCE3', '#tabs', '车辆保险（首保）查询', '${pageContext.request.contextPath}/sell/SellInsurance/InsuranceCarQuery.jsp');">
												<span>车辆保险查询</span></a>
										</li>
									</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
							<privilege:enable code="XSFINANCE">
								<div title="财务管理" iconCls="icon-folder">
									<ul>
									<privilege:enable code="XSGATHERING"> 
										<li>
											<a name="icona" href="javascript:void(0);"  iconName="CAIGOUDAN"  iconCls="icon-CAIGOUDAN"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCARINSURANC1', '#tabs', '预收款作业', '${pageContext.request.contextPath}/sell/sell_financemanage/gathering_manage.jsp');">
												<span>预收款作业</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSSHOULDSK"> 
										<li>
											<a name="icona" href="javascript:void(0);"  iconName="CAIGOUDAN"  iconCls="icon-CAIGOUDAN"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCARINSURANC2', '#tabs', '应收款作业', '${pageContext.request.contextPath}/sell/sell_financemanage/should_gathering_manage.jsp');">
												<span>应收款作业</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSSELLACCOUNT"> 
										<li>
											<a name="icona" href="javascript:void(0);" iconName="SELLDECORATE"  iconCls="icon-SELLDECORATE"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCARINSURANC3', '#tabs', '结算单管理', '${pageContext.request.contextPath}/sell/sellAccount/sellAccount.jsp');">
												<span>结算单管理</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSREFUNDENT"> 
										<li>
											<a name="icona" href="javascript:void(0);" iconName="SELLDECORATE"  iconCls="icon-SELLDECORATE"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCARINSURANC4', '#tabs', '退车单退款', '${pageContext.request.contextPath}/sell/sell_financemanage/refundment.jsp');">
												<span>退车单退款</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSBEFORETK"> 
										<li>
											<a name="icona" href="javascript:void(0);" iconName="BEFOREREFUNDMENT"  iconCls="icon-BEFOREREFUNDMENT"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCARINSURANC6', '#tabs', '预收款退款', '${pageContext.request.contextPath}/sell/sell_financemanage/beforeRefundment.jsp');">
												<span>预收款退款</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSSELLRECEIPT"> 
										<li>
											<a name="icona" href="javascript:void(0);" iconName="SELLRECEIPT"  iconCls="icon-SELLRECEIPT"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCARINSURANC5', '#tabs', '承兑汇票管理', '${pageContext.request.contextPath}/sell/sellReceipt/sellReceipt.jsp');">
												<span>承兑汇票管理</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSCERTIFICATE"> 
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CERTIFICATE"  iconCls="icon-CERTIFICATE"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCARINSURANC7', '#tabs', '合格证管理', '${pageContext.request.contextPath}/sell/sellCertificate/sellCertificate.jsp');">
												<span>合格证管理</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSSELLINVOICE">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="SELLINVOICE"  iconCls="icon-SELLINVOICE"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSSELLINVOICE1', '#tabs', '车辆销售财务信息管理', '${pageContext.request.contextPath}/sell/sellInvoice/sellInvoice.jsp');">
												<span>车辆销售财务信息管理</span></a>
										</li>
									</privilege:enable>
									<li>
											<a name="icona" href="javascript:void(0);" iconName="SELLINVOICE"  iconCls="icon-SELLINVOICE"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSSELLINVOICE2', '#tabs', '随车附件管理', '${pageContext.request.contextPath}/sell/carInfo/carAttachmentManage/carAttachmentManage.jsp');">
												<span>随车附件管理</span></a>
									</li>
									
									   
									<privilege:enable code="XSYSKQUERY">
										<li>
											<a name="icona" href="javascript:void(0);"iconName="CARMODEL"  iconCls="icon-CARMODEL"
												onclick="addTab('XSFINANCE11', '#tabs', '预收款查询', 
												'${pageContext.request.contextPath}/sell/analysis_by_synthesis/readyBill_query.jsp');" 
												class="easyui-linkbutton" plain="true"><span>预收款查询</span></a>
										</li>
									</privilege:enable>
										<li>
											<a name="icona" href="javascript:void(0);"iconName="CARMODEL"  iconCls="icon-CARMODEL"
												onclick="addTab('XSFINANCE12', '#tabs', '应收账款统计', 
												'${pageContext.request.contextPath}/sell/analysis_by_synthesis/shouldBill_query.jsp');" 
												class="easyui-linkbutton" plain="true"><span>应收账款统计</span></a>
										</li>
									<privilege:enable code="XSSUPPLIERBILL">
									<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>供应商对账管理(暂不做)</span></a>
										</li>
										<!--<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true" iconName="CARMODEL"  iconCls="icon-CARMODEL"
												onclick="addTab('XSFINANCE10', '#tabs', '供应商对账管理', '${pageContext.request.contextPath}/sell/sell_financemanage/supplier_bill.jsp');"
												><span>供应商对账管理</span></a>
										</li>
								    --></privilege:enable>
									<privilege:enable code="XSSELLBILLM">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>销售发票管理(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSCUSTOMHK">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>客户还款计划(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSDKSKWORk">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>贷款收款作业(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSSKQUERy">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>收款情况查询(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSCARSK">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>车辆收款记录(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSDBFKJL">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>代办付款记录(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSBANKAJJL">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>银行按揭记录(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSBXFPAYMENT">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>保险费付款(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSSELLJZWORk">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>销售结帐作业(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSPLEDGEM">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>抵押车辆管理(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSCARCWSJM">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>车辆财务数据管理(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSDISCWM">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>分销商财务管理(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSDISSKJL">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>分销收款记录(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSDISBILLM">
									<li>
										<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" plain="true"><span>分销商对账管理(暂不做)</span></a>
									</li>
									<!--<li>
										<a name="icona" href="javascript:void(0);" 
											class="easyui-linkbutton" plain="true" iconName="CARMODEL"  iconCls="icon-CARMODEL"
											onclick="addTab('XSFINANCE111', '#tabs', '分销商对账管理', '${pageContext.request.contextPath}/sell/sell_financemanage/distributor_bill.jsp');">
											<span>分销商对账管理</span></a>
										</li>-->
									</privilege:enable>
									<privilege:enable code="XSDISPWMANAGE">
										<li>
											<a name="icona" href="javascript:void(0);"											
												class="easyui-linkbutton" plain="true"><span>分销票务管理(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSDISYEQUERY">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>分销商余额查询(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSDBPAYMENTQ">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>代办付款查询(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSDAYSKQUERY">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>日收付款查询(暂不做)</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSSELLYSK">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>销售应收款查询(暂不做)</span></a>
										</li>
								 	</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
							<privilege:enable code="XSCOMMONTOOLS">
								<div title="常用工具" iconCls="icon-folder">
									<ul>
									<privilege:enable code="XSNOTESEND">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CAIGOUDAN"  iconCls="icon-CAIGOUDAN"
												class="easyui-linkbutton" plain="true"
												onclick="addTab('XSCOMMONTOOLS1', '#tabs', '短信发送', '${pageContext.request.contextPath}/sell/noteManage/note_send_manage.jsp');"
												><span>短信发送</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSSENDQUERT">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="CARKZ"  iconCls="icon-CARKZ"
												class="easyui-linkbutton" plain="true"
												onclick="addTab('XSCOMMONTOOLS2', '#tabs', '短信发送查询', '${pageContext.request.contextPath}/sell/noteManage/noteQuery.jsp');"
												><span>短信发送查询</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSNOTERECEIVE">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>短信接收</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSCALCULATOR">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>计算器</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSNOTEPAD">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton" plain="true"><span>记事本</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSLOGMANAGER">
										<li>
											<a name="icona" href="javascript:void(0);" 
												class="easyui-linkbutton"
												onclick="addTab('SYSTEMOPER7', '#tabs', '日志管理', '${pageContext.request.contextPath}/system_management/system_log.jsp');"
												plain="true"><span>系统日志</span></a>
										</li>
									</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
							<privilege:enable code="XSCUSTOMERSERVICE">
								<div title="售后服务" iconCls="icon-folder">
									<ul>
									<privilege:enable code="XSCARFIXSHEET">
										<li>
										    <a name="icona" href="javascript:void(0);"  iconName="ASINGLE"  iconCls="icon-ASINGLE"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCUSTOMERSERVICE1', '#tabs', '车辆加装单', '${pageContext.request.contextPath}/sell/sellServicing/car_fixSheet.jsp');">
												<span>车辆加装单</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSCARFIXEXAMICE">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="SELLADDEXAMICE"  iconCls="icon-SELLADDEXAMICE"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCUSTOMERSERVICE2', '#tabs', '车辆加装审核', '${pageContext.request.contextPath}/sell/sell_work/car_fixExamine.jsp');">
												<span>车辆加装审核</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSSELLPARTOUT">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="SELLOUT"  iconCls="icon-SELLOUT"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCUSTOMERSERVICE3', '#tabs', '车辆加装出库', '${pageContext.request.contextPath}/sell/sellServicing/carSellPartOut.jsp');">
												<span>车辆加装出库</span></a>
										</li>
									</privilege:enable>
   							        <privilege:enable code="XSSTPURORDEl">
									
										<li>
											<a name="icona" href="javascript:void(0);" iconName="STOREHOUSE1"  iconCls="icon-STOREHOUSE1"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCUSTOMERSERVICE4', '#tabs', '采购单管理', '${pageContext.request.contextPath}/st/StPurOrder/StPurOrderManager.jsp');">
												<span>采购单管理</span></a>
										</li>
									</privilege:enable>
									<privilege:enable code="XSRKMANAGE">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="STOREHOUSE2"  iconCls="icon-STOREHOUSE2"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCUSTOMERSERVICE5', '#tabs', '入库单管理', '${pageContext.request.contextPath}/st/StGoodsStorage/StGoodsStorageManager.jsp');">
												<span>入库单管理</span></a>
										</li>
									</privilege:enable>
									
									<privilege:enable code="XSMOVEHOSEM">
										<li>
											<a name="icona" href="javascript:void(0);" iconName="STOREHOUSE15"  iconCls="icon-STOREHOUSE15"
												class="easyui-linkbutton" plain="true" onclick="addTab('XSCUSTOMERSERVICE6', '#tabs', '移仓管理', '${pageContext.request.contextPath}/st/StMoveStorehouse/StMoveStorehouseManager.jsp');">
												<span>移仓调拨单管理</span></a>
										</li>
									</privilege:enable>
									</ul>
								</div>
							</privilege:enable>
						</div>
				    </div>
				</privilege:enable>
			</div>
		</div>
		<div region="center" border="false">
			<div id="tabs" class="easyui-tabs"
				style="width:800px;height:600px;" fit="true" border="false">
				<div title="Home">
					<div id="homeBody" class="cs-home-remark">
						
					</div>
				</div>
			</div>
		</div>

		<!-- 标签页右键菜单 -->
		<div id="tm" class="easyui-menu" style="width: 120px;">
			<div id="tm-tabupdate" icon="icon-reload">
				刷新
			</div>
			<div class="menu-sep"></div>
			<div id="tm-tabclose">
				关闭
			</div>
			<div id="tm-tabcloseall">
				关闭全部
			</div>
			<div id="tm-tabcloseother">
				关闭其他
			</div>
			<div class="menu-sep"></div>
			<div id="tm-exit">
				退出
			</div>
		</div>
	</body>
</html>