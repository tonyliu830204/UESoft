<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>赠消积分管理</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/FBK_VIP/Vip_manag.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/score_management.js"></script>
	</head>
	<body>
	<div id="add" class="easyui-window" modal="true" title="新增赠送与消费积分明细" style="width:250px;height:205px;background: #ccff;"closed="true" maximizable="false" minimizable="false" collapsible="fasle">
		<div id="aa"></div>
	</div>
	<!-- 赠送与消费积分明细 新增 窗口 -->
	<form id="score_management_datil_form_add" >
		<table with="100%">
			<tr>
				<td>序号:</td>
				<td><input class="easyui-alidatebox" type="text" id="number" name="number" required="true"/></td>
			</tr>
				<tr>
				<td>车牌照:</td>
				<td><input class="easyui-alidatebox" type="text" id="carbrand" name="carbrand" required="true"/></td>
			</tr>
				<tr>
				<td>会员号:</td>
				<td><input class="easyui-alidatebox" type="text" id="vipnumber" name="vipnumber" required="true"/></td>
			</tr>
				<tr>
				<td>会员名称:</td>
				<td><input class="easyui-alidatebox" type="text" id="vipname" name="vipname" required="true"/></td>
			</tr>
				<tr>
				<td>积分数:</td>
				<td><input class="easyui-alidatebox" type="text" id="score" name="score" required="true"/></td>
			</tr>
				
			<tr align="center">
				<td align="center" rowspan="2" colspan="2"><a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0);" onclick="submitForm($('#ZENG_XIAO_DETAIL'),$('#score_management_datil_form_add'),'addOrMinusAction_doAdd','addOrMinusAction_doFindAll')">确定</a> <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0);" onclick="doCancel()">取消</a></td>
			</tr>
		</table>
	</form>
		<!-- 赠送与消费积分明细 新增 窗口 -->
	<form id="score_management_datil_form_edit">
		<table with="100%">
			<tr>
				<td>序号:</td>
				<td><input class="easyui-alidatebox" type="text" id="number" name="number" required="true"/></td>
			</tr>
				<tr>
				<td>车牌照:</td>
				<td><input class="easyui-alidatebox" type="text" id="carnumber" name="carnumber" required="true"/></td>
			</tr>
				<tr>
				<td>会员号:</td>
				<td><input class="easyui-alidatebox" type="text" id="vipnumber" name="vipnumber" required="true"/></td>
			</tr>
				<tr>
				<td>会员名称:</td>
				<td><input class="easyui-alidatebox" type="text" id="vipname" name="vipname" required="true"/></td>
			</tr>
				<tr>
				<td>积分数:</td>
				<td><input class="easyui-alidatebox" type="text" id="score" name="score" required="true"/></td>
			</tr>
				
				<tr align="center">
				<td align="center" rowspan="2" colspan="2"><a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0);" onclick="doEdit()">修改</a> <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0);" onclick="doCancel()">取消</a></td>
			</tr>
		</table>
	</form>
	<!-- 赠送与消费积分明细 新增 窗口 -->
		<div class="easyui-tabs" style="width: 600px; height: 750px;"fit="true" border="false">
			<div title="赠送与消费积分明细" fit="true" border="false">
				<div class="easyui-layout" border="false" fit="true" style="width: 800px; height: 600px;">
					<div region="north" title="条件筛选" split="false" border="false"
						style="height: 70px; background : #eee">
						<form id="ff" method="post">
							<table border="0"  align="center">
								<tr>
									<td style="width:50px;">
										单据日期
									</td>
									<td style="width:280px;" readonly="readonly">
										<input id="cc" class="easyui-datebox" editable="false"/>
										<input id="cc" class="easyui-datebox" editable="false"/>
									</td>

									<td style="width:50px;">
										单据号码
									</td>
									<td width="100">
										<input type="text" readonly="readonly" />
									</td>
									<td style="width:50px;">
										操作分类
									</td>
									<td width="100">
										<select style="width:100px;">
											<option>
												消费积分
											</option>
											<option>
												赠送积分
											</option>
										</select>
									</td>
									<td style="width:70px;">
										经办人姓名
									</td>
									<td >
										<select style="width:100px;">
											<option>
												王菊
											</option>
											<option>
												李稻葵
											</option>
										</select>
									</td>
									<td style="width:30px;">
										合计
									</td>
									<td widht="100">
										<input type="text" readonly="readonly" />
									</td>
									<td style="width:30px;">
										备注
									</td>
									<td >
										<input type="text" />
									</td>
								</tr>
							</table>
						</form>

					</div>
					<div region="center" border="false">
						<table id="ZENG_XIAO_DETAIL"></table>
					</div>
				</div>
			</div>


			<div title="赠送与消费积分汇总" fit="true" border="false">
				<div class="easyui-layout"
					style="width: 800px; height: 600px;" fit="true" border="false">
					<div region="north" title="条件筛选" split="false" border="false"
						style="height: 70px;background : #eee">
						<form id="ff" method="post">
							<table border="0">
								<tr>

									<td style="width:50px;">
										单据日期
									</td>
									<td style="width:280px;">
										<input id="cc" class="easyui-datebox" editable="false"/>
										<input id="cc" class="easyui-datebox" editable="false" />
									</td>
									<td style="width:50px;">
										单据号码
									</td>
									<td style="width:80px;">
										<input type="text"/>
									</td>

								</tr>
							</table>
						</form>

					</div>
					<div region="center" border="false">
						<table id="ZENG_XIAO_HZONG"></table>
					</div>
				</div>
			</div>

		</div>
	</body>
</html>
