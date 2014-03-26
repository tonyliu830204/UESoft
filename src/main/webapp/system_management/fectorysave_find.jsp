<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
	<head>
		<title>历史库存量查询</title>

	</head>

	<body class="easyui-layout" border="false" fit="true" 
		style="width: 800px; height: 600px;">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden; background:#eee; height:80px;" border="false">
			<form id="ff" method="post">
			<fieldset style="height:60px;">
				<legend style="font-weight:bold">查询条件</legend>
				<table border="0" style="text-align: right">
					<tr>
						<td>配件代码：</td>
						<td><input type="text" /></td>
						<td>车辆品牌：</td>
						<td><input name ="cbrd_Name"
							class="easyui-combobox"
							data-options="
							url : 'vTrackRecordAction_getCarbrand.action',
							valueField:'id',  
							textField:'name',
							multiple:false  "
							/>
						</td>
						<td>车辆型号：</td>
						<td><input style="width:110px" name="ctype_Name"
							class="easyui-combobox"
							data-options="
							url : 'VipRecordMessageAction!getBasCarType.action',
							valueField:'id',  
							textField:'name',
							multiple:false "
							/>
						</td>
						<td>车辆部位：</td>
						<td>
							<select style="width:110px">
								<option>
									车头
								</option>
								<option>
									车门
								</option>
								<option>
									尾部
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>月结选择：</td>
						<td>
							<select style="width:45px">
								<option>
									A
								</option>
								<option>
									B
								</option>
								<option>
									C
								</option>
							</select>
							<input TYPE="BUTTON" VALUE="为零显示"  style="width:60px"/>
						</td>
						<td>分类：</td>
						<td>
							<select style="width:110px">
								<option>
									仓库成本
								</option>
								<option>
									财务成本
								</option>
							</select>
						</td>

						<td>仓别：</td>
						<td>
							<select style="width:110px">
								<option>
									配件一库
								</option>
								<option>
									配件二库
								</option>
								<option>
									配件三库
								</option>
							</select>
						</td>
					</tr>

				</table>
				</fieldset>
			</form>
		</div>
		<div region="west" split="false" style="width: 150px;">

			<ul class="easyui-tree">
				<li>
					<span>悦达起亚</span>
					<ul>
						<li>
							<span>k5</span>
						</li>
						<li>
							<span>智跑</span>
						</li>
						<li>
							<span>狮跑</span>
						</li>
						<li>
							<span>福瑞迪</span>
						</li>
						<li>
							<span>嘉华</span>
						</li>
						<li>
							<span>千里马</span>
						</li>
					</ul>
				</li>
				<li>
					<span>北京现代</span>
					<ul>
						<li>
							<span>索拉塔第八代</span>
						</li>
						<li>
							<span>索拉塔第九代</span>
						</li>
						<li>
							<span>索拉塔第十代</span>
						</li>
					</ul>
				</li>
			</ul>


		</div>
		<div region="center" border="false">
		<table id="factorysave_find_id"></table>
		</div>
	</body>
</html>
