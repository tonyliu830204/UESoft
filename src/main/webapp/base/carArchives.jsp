<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>车辆档案</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body class="easyui-layout">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/carArchives.js"></script>
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
	   	<privilege:enable code="CARARCHIVES_ADD">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add();">新增</a>
	  	</privilege:enable>
    	<privilege:enable code="CARARCHIVES_REMOVE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="_remove();">删除</a>
	  	</privilege:enable>  
    	<privilege:enable code="CARARCHIVES_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">修改</a>
	  	</privilege:enable>    
    	<privilege:enable code="CARARCHIVES_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="query();">查询</a>
	  	</privilege:enable>  
    	<privilege:enable code="CARARCHIVES_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clear();">清空</a>
	  	</privilege:enable>    
    	<privilege:enable code="CARARCHIVES_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'" onclick="_except();">Excel导出</a>
	  	</privilege:enable>
	  	<privilege:enable code="CARARCHIVES_CHANGE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-change'" onclick="carChange();">变更</a>
	  	</privilege:enable>
    	<privilege:enable code="CARARCHIVES_REMIND">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remind'" onclick="carRemind();">新增维修建议</a>
	  	</privilege:enable>
    </div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	 <div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;padding:3px;background:#eee;height:108px;">
		    	<form id="carArchivesQueryForm">
					<table>
						<tr>
							<td>建档日期:</td>
							<td style="text-align: left;" colspan="3"><input type="text" id="carArchives_createDateBegin" name="createDateBegin" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'carArchives_createDateEnd\')}'});"/>
							&nbsp;至&nbsp;&nbsp;<input type="text" id="carArchives_createDateEnd" name="createDateEnd" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'carArchives_createDateBegin\')}'});"/></td>
							<td>车辆编号:</td>
							<td><input type="text" name="carId"/></td>
							<td>车辆牌照:</td>
							<td><input type="text" name="carLicense"/></td>
							<td>VIN号:</td>
							<td><input type="text" name="carVin"/></td>
							<td>档案号:</td>
							<td><input type="text" name="carRecord"/></td>
						</tr>
						<tr>
							<td>购车日期:</td>
							<td style="text-align: left;" colspan="3"><input type="text" id="carArchives_carBuyDateBegin" name="carBuyDateBegin" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'carArchives_carBuyDateEnd\')}'});"/>
							&nbsp;至&nbsp;&nbsp;<input type="text" id="carArchives_carBuyDateEnd" name="carBuyDateEnd" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'carArchives_carBuyDateBegin\')}'});"/></td>
							<td>车辆品牌:</td>
							<td><input type="text" id="carArchives_cbrdId" name="cbrdId" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarBrand.action',   
						    valueField:'id',   
						    textField:'text',
						    mode : 'remote',
						    validType:'isSelected[\'#carArchives_cbrdId\']',
    						invalidMessage : '请从下拉框中选择车辆品牌',
						    onSelect: function(rec){  
				    			$(this).combobox('textbox').bind('keyup', function (){
				    				if($('#carArchives_cbrdId').combobox('getValue') == '' || $('#carArchives_cbrdId').combobox('getValue') != $('#carArchives_cbrdId').combobox('getText')){
				    					$('#carArchives_ctypeId').combobox('reload', '${pageContext.request.contextPath}/basCarArchivesAction!findCarType.action');
				    				}
				    			});
					            $('#carArchives_ctypeId').combobox('clear');
					            $('#carArchives_add_carCstlId').combobox('clear');   
					            $('#carArchives_ctypeId').combobox('reload', '${pageContext.request.contextPath}/basCarArchivesAction!findCarType.action?cbrdId=' + rec.id);  
					        } "/></td>
							<td>车辆型号:</td>
							<td><input type="text" id="carArchives_ctypeId" name="ctypeId" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarType.action',   
						    valueField:'id',   
						    textField:'text',
						    mode : 'remote',
						    validType:'isSelected[\'#carArchives_ctypeId\']',
    						invalidMessage : '请从下拉框中选择车辆型号',
						    onSelect:function(rec){
				    			$(this).combobox('textbox').bind('keyup', function (){
				    				if($('#carArchives_ctypeId').combobox('getValue') == '' || $('#carArchives_ctypeId').combobox('getValue') != $('#carArchives_ctypeId').combobox('getText')){
				    					$('#carArchives_add_carCstlId').combobox('reload', '${pageContext.request.contextPath}/basCarArchivesAction!findCarStyle.action');
				    				}
				    			});  
					            $('#carArchives_cstlId').combobox('clear');  
					            $('#carArchives_cstlId').combobox('reload', '${pageContext.request.contextPath}/basCarArchivesAction!findCarStyle.action?ctypeId=' + rec.id);  
					        } "/></td>
							<td>车辆款式:</td>
							<td><input type="text" id="carArchives_cstlId" name="carCstlName" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarStyle.action',   
						    valueField:'id',   
						    textField:'text',
						    mode : 'remote',
						    validType:'isSelected[\'#carArchives_cstlId\']',
    						invalidMessage : '请从下拉框中选择车辆型号' "/></td>
							<td>经销商:</td>
							<td><input type="text" id="carArchives_slsId" name="slsId" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarSellers.action',   
						    valueField:'id',   
						    textField:'text',
						    mode : 'remote',
						    validType:'isSelected[\'#carArchives_cstlId\']',
    						invalidMessage : '请从下拉框中选择经销商' "/></td>
						</tr>
						<tr>
							<td>客户名称:</td>
							<td><input type="text" name="customName"/></td>
							<td>客户性质:</td>
							<td><input type="text" id="carArchives_natureId" name="natureId" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/basCarArchivesAction!findCustomNature.action',   
						    valueField:'id',   
						    textField:'text',
						    mode : 'remote',
						    validType:'isSelected[\'#carArchives_natureId\']',
    						invalidMessage : '请从下拉框中选择客户性质' " style="width: 78px;"/></td>
							<td>客户分类:</td>
							<td><input type="text" id="carArchives_cstgId" name="cstgId" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/basCarArchivesAction!findCustomGroup.action',   
						    valueField:'id',   
						    textField:'text',
						    mode : 'remote',
						    validType:'isSelected[\'#carArchives_cstgId\']',
    						invalidMessage : '请从下拉框中选择客户类型' "/></td>
							<td>客户类型:</td>
							<td><input type="text" id="carArchives_cstId" name="cstId" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/basCarArchivesAction!findCustomType.action',   
						    valueField:'id',   
						    textField:'text',
						    mode : 'remote',
						    validType:'isSelected[\'#carArchives_cstId\']',
    						invalidMessage : '请从下拉框中选择客户分类' "/></td>
							<td>所在区域:</td>
							<td><input type="text" id="carArchives_pareaId" name="pareaId" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/basCarArchivesAction!findCustomArea.action',   
						    valueField:'id',   
						    textField:'text',
						    mode : 'remote',
						    validType:'isSelected[\'#carArchives_pareaId\']',
    						invalidMessage : '请从下拉框中选择所在区域' "/></td>
							<td>联系电话:</td>
							<td><input type="text" name="customTel1"/></td>
						</tr>
					</table>
				</form>
		    </div>  
		    <div id="carArchivesDatagrid_center" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="carArchivesDatagrid"></table>
		    </div>  
		</div>  
    </div>  
  </body>
</html>
