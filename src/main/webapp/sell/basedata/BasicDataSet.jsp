<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	deferredSyntaxAllowedAsLiteral="true"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include
	page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>基本数据设置</title>
		<script type="text/javascript">
	$(function() {
		$.ajax( {
			type : 'POST',
			dataType : 'json',
			url : 'baseAction!getChecked.action',
			success : function(r) {
				if (r == 'checked') {
					bigIcon();
				}
			}
		});

	});
	function openURL(url) {
		if (url != undefined) {
			$('#baseDataSet').panel('refresh', url);
		}
	}
</script>
	</head>
	<body>
		<div id="cc" class="easyui-layout" border="false"
			style="width: 600px; height: 400px;" fit="true" border="false">
			<div region="west" split="false" style="width: 180px;" border="true">
				<div class="easyui-accordion" animate="false"
					style="width: 180px; height: 600px;" fit="true" border="false">
					<div title="客户属性" iconCls="icon-folder">
						<ul>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=setArea&value=地区设定&number_=地区编号&lable_=地区名称&value_=邮政编码&confirmType=zip&types=qiye');"
									plain="true">地区设定</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=customeNature&value=客户性质&number_=&lable_=性质编号&value_=性质名称&confirmType=characterDigit');"
									plain="true">客户性质</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=income&value=个人收入&number_=&lable_=收入编码&value_=收入名称&confirmType=characterDigit');"
									plain="true">个人收入</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=vocationNature&value=职业性质&number_=&lable_=职业编码&value_=职业名称&confirmType=characterDigit');"
									plain="true">职业性质</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=educational&value=学历设置&number_=&lable_=学历编码&value_=学历名称&confirmType=characterDigit');"
									plain="true">学历设置</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=customeSource&value=客户来源&number_=&lable_=来源编码&value_=来源名称&confirmType=characterDigit');"
									plain="true">客户来源</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=tradeClassify&value=行业分类&number_=&lable_=行业编码&value_=行业名称&confirmType=characterDigit');"
									plain="true">行业分类</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=impede&value=成交障碍&number_=&lable_=障碍编码&value_=障碍名称&confirmType=characterDigit');"
									plain="true">成交障碍</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/custom/customLeva.jsp');"
									plain="true">潜在客户等级</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=sellWay&value=销售方式&number_=&lable_=销售代码&value_=销售方式&confirmType=characterDigit');"
									plain="true">销售方式</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/repay/repay.jsp');"
									plain="true">回访进度</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=vanquishCause&value=战败原因&number_=&lable_=战败编码&value_=战败名称&confirmType=characterDigit');"
									plain="true">战败原因</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=contrastModel&value=对比车型&number_=&lable_=对比车型编码&value_=对比车型名称&confirmType=characterDigit');"
									plain="true">对比车型</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=choiceCause&value=选择理由&number_=&lable_=选择理由编码&value_=选择理由名称&confirmType=characterDigit');"
									plain="true">选择理由</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=carPurpose&value=车辆用途&number_=&lable_=车辆用途编码&value_=车辆用途名称&confirmType=characterDigit');"
									plain="true">车辆用途</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=otherClassify&value=其他分类&number_=&lable_=其他分类编码&value_=其他分类名称&confirmType=characterDigit');"
									plain="true">其他分类</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=dealState&value=成交状态&number_=&lable_=成交状态编码&value_=成交状态名称&confirmType=characterDigit');"
									plain="true">成交状态</a>
							</li>
						</ul>
					</div>
					<div title="车辆属性" iconCls="icon-folder">
						<ul>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=carBrand&value=车辆品牌&number_=&lable_=品牌编码&value_=品牌名称&confirmType=characterDigit&types=qiye');"
									plain="true">车辆品牌</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=carColor&value=车身颜色&number_=&lable_=颜色编码&value_=颜色名称&confirmType=characterDigit&types=qiye');"
									plain="true">车身颜色</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=ornamentColor&value=内饰色&number_=&lable_=分类编码&value_=分类名称&confirmType=characterDigit&types=qiye');"
									plain="true">内饰色</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=insuranceType&value=保险险种&number_=&lable_=险种编码&value_=险种名称&confirmType=characterDigit&types=qiye');"
									plain="true">保险险种</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=outputVolume&value=车辆排量&number_=&lable_=排量编码&value_=排量名称&confirmType=characterDigit');"
									plain="true">车辆排量</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=certificateState&value=合格证状态&number_=&lable_=合格证状态编码&value_=合格证状态名称&confirmType=characterDigit');"
									plain="true">合格证状态</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=attachOne&value=附加属性一&number_=&lable_=附加属性一编码&value_=附加属性一名称&confirmType=characterDigit');"
									plain="true">附加属性一</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=attachTwo&value=附加属性二&number_=&lable_=附加属性一编码&value_=附加属性二名称&confirmType=characterDigit');"
									plain="true">附加属性二</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=attachThree&value=附加属性三&number_=&lable_=附加属性三编码&value_=附加属性三名称&confirmType=characterDigit');"
									plain="true">附加属性三</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=attachFour&value=仓库&number_=&lable_=仓库编码&value_=仓库名称&confirmType=characterDigit&types=qiye');"
									plain="true">仓库</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=carNorms&value=规格&number_=&lable_=规格编码&value_=规格名称&confirmType=characterDigit');"
									plain="true">车辆规格</a>
							</li>
						</ul>
					</div>
					<div title="财务相关" iconCls="icon-folder">
						<ul>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=paymentWay&value=付款方式&number_=&lable_=付款编码&value_=付款名称&confirmType=characterDigit');"
									plain="true">付款方式</a>
							</li>
						</ul>
					</div>
					<div title="综合服务" iconCls="icon-folder">
						<ul>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/dbProject/dbProject.jsp');"
									plain="true">代办项目</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/zsProject/zsProject.jsp');"
									plain="true">赠送项目</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/repayPro/repayPro.jsp');"
									plain="true">客户回访项目</a>
							</li>
						</ul>
					</div>
					<div title="公司相关" iconCls="icon-folder">
						<ul>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/base/other_set/Department_set.jsp');"
									plain="true">部门设置</a>
							</li>
							<!-- 
							<li>
								<a name="icona" href="javascript:void(0);"
									class="easyui-linkbutton" iconName="JOBPROPERTY"
									iconCls="icon-JOBPROPERTY"
									onclick="openURL('${pageContext.request.contextPath}/base/other_set/basJobProperty.jsp?sysType=<%=Contstants.SYSTEMTYPE.XIAOSHOU %>');"
									plain="true"><span>工作属性</span>
								</a>
							</li>
							 -->
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=sellTeam&value=销售班组&number_=&lable_=班组编码&value_=班组名称&confirmType=characterDigit&types=qiye');"
									plain="true">销售班组</a>
							</li>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=supplierClassify&value=供应商分类&number_=&lable_=分类编码&value_=分类名称&confirmType=characterDigit');"
									plain="true">供应商分类</a>
							</li>
						</ul>
					</div>
					<div title="会员性质" iconCls="icon-folder">
						<ul>
							<li>
								<a href="javascript:void(0);" iconCls="icon-item"
									class="easyui-linkbutton"
									onclick="openURL('${pageContext.request.contextPath}/sell/basedata/CarProperties/common.jsp?key=commonCMS&value=常用短信&number_=&lable_=短信编码&value_=短信名称&confirmType=characterDigit&types=qiye');"
									plain="true">常用短信</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div id="baseDataSet" region="center" style="background: #eee;"
				border="false"></div>
		</div>
	</body>
</html>
