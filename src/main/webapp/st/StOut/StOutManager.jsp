<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP '出库单管理' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StOut/stoutmanager.js"></script>
  </head>
  <body>
      <div id="cc" class="easyui-layout" fit="true" border="false">  
		        <div region="north"   border="false" split="false" style="height:30px;background: #eee;">
		        <privilege:enable code="StOutAdd">
		           <a href="javascript:void(0);" id="so_add" class="easyui-linkbutton"
				    iconCls="icon-add" plain="true"  onclick="addPersonnel(1);">新增</a>
		        </privilege:enable>
		        <privilege:enable code="StOutDelete">
		           <a href="javascript:void(0);" id="so_delete" class="easyui-linkbutton"
						iconCls="icon-remove" plain="true" onclick="del();">删除</a>
		        </privilege:enable>
		        <privilege:enable code="StOutUpdate">
		           <a href="javascript:void(0);" id="so_update" class="easyui-linkbutton"
						iconCls="icon-edit" plain="true" onclick="addPersonnel(2);">修改</a>
		        </privilege:enable>
		        <privilege:enable code="StOutSearch">
		           <a href="javascript:void(0);" id="so_serachBtn" class="easyui-linkbutton" iconCls="icon-search"
					 	plain="true" onclick="searchByCondition();">查询</a>
		        </privilege:enable>
		        <privilege:enable code="StOutClear">
		           <a href="javascript:void(0);" id="so_clearBtn" class="easyui-linkbutton" iconCls="icon-cancel"
			         	plain="true" onclick="clearSearchByCondition();">清空</a>
		        </privilege:enable>
		        <privilege:enable code="StOutPrint">
		           <a href="javascript:void(0);" id="so_printBtn" class="easyui-linkbutton"
						iconCls="icon-print" plain="true" onclick="addprint();">打印</a>
		        </privilege:enable>
		        <privilege:enable code="StOutExport">
		           <a href="javascript:void(0);" id="so_exportBtn" class="easyui-linkbutton"
						iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
		        </privilege:enable>
		        <a href="javascript:void(0);" id="so_importBtn" class="easyui-linkbutton"
						iconCls="icon-import" plain="true">Excel导入</a>
				<span id="saveOrCancelBtn"></span>
		       </div>
               <div region="center"  style="background:#eee;" split="false" border="false">   
                  <div id="st_outTabs" class="easyui-tabs" fit="true"  border="false">
			           <div title="出库单汇总" style="display:block;" closable="false" fit="true"  border="false">  
		                  <div id="cc12" class="easyui-layout" fit="true"  border="false">   
				                    <div region="north" split="false" title="查询条件" style="overflow: hidden;padding:1px; height:53px;background:#eee;" border="false">
							          <form id="StOutSearchForm">
								                    出库时间:<input id="stomDateStart" name="stomDateStart" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'stomDateEnd\',{d:-1})}'})"/>&nbsp;
							                               至&nbsp;<input id="stomDateEnd" name="stomDateEnd" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'stomDateStart\',{d:0})}'})"/>&nbsp;&nbsp;
							                               出库单号:<input name="stomId" />&nbsp;&nbsp;
							                               工单号:<input name="receptionId" />&nbsp;&nbsp;
							                               车牌照:<input name="carLicense" />&nbsp;&nbsp;
							                               客户名称:<input name="customName" />&nbsp;&nbsp;
							            VAN号:<input name="carVan" />&nbsp;&nbsp;
					                  </form>
						           </div>
				                   <div region="center" id="StOutSearchTableDiv"  style="background:#eee;" split="false" border="false">
					                 <table id="StOutSearchTable"></table>
				                   </div>
				          </div>          
		              </div>  
		              <div title="出库单明细" style="overflow:false;" closable="false" border="false" fit="true">  
				          <div id="cc" class="easyui-layout" fit="true" border="false">  
									  <div region="north" title="查询条件" split="false" style="overflow:hidden;pading:1px;height:130px;background:#eee;" border="false">
										     <form id="stOutMainForm">
										        <table>
											      <tr>
											       <td>日期:</td>
											       <td><input id="stomDate" name="stomDate" readonly="readonly" style="width:140px;" class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
											       <td>出库单号:</td>
											       <td><input id="stomId" name="stomId"  readonly="readonly" style="background-color: #c0d8d8;"></td>
											       <td>车牌:</td>
											       <td><input id="carLicense" name="carLicense" readonly="readonly" style="background-color: #c0d8d8;"></td>
											       <td>工单号:</td>
											       <td><input id="receptionId" name="receptionId" class="easyui-validatebox" required="true" missingMessage="工单号必填" readonly="readonly" style="background-color: #c0d8d8;"/></td>
											       <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="addFrtReception();"/></td>
											       <td>维修类别:</td>
											       <td><input id="reptName" name="reptName" size="10" readonly="readonly" style="background-color: #c0d8d8;"></td>
											       <td>车型:</td>
											       <td><input id="ctypeName" name="ctypeName" readonly="readonly" style="background-color: #c0d8d8;"/></td>
											      </tr>
											      <tr>
											       <td>vin号:</td>
											       <td><input id="carVan" name="carVan" readonly="readonly" style="width:130px;background-color: #c0d8d8;">&nbsp;&nbsp;&nbsp;</td>
											       <td>客户名称:</td>
											       <td><input size="30" id="customName" name="customName" readonly="readonly" style="background-color: #c0d8d8;"></td>
											       <td>经办人:</td>
											       <td><input size="8" id="manager" name="managerName" value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>"  readonly="readonly" style="background-color: #c0d8d8;" ></td>
											       <td>领料员:</td>
											       <td><input id="pickingMemberName" name="pickingMemberName" class="easyui-validatebox" required="true" missingMessage="领料员必填" readonly="readonly" style="background-color: #c0d8d8;"><input type="hidden" id="pickingMember" name="pickingMember"/></td>
											       <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="addPickingMember();"/></td>
											       <td>班组:</td>
											       <td><input id="repgrpName" name="repgrpName" readonly="readonly" style="background-color: #c0d8d8;" ></td>
											       <td>里程:</td>
											       <td><input id="carLastRepairDistance" name="carLastRepairDistance" readonly="readonly" style="background-color: #c0d8d8;"></td>
											      </tr>
											      <tr>
											       <td>进厂日期:</td>
											       <td><input id="resvRealTime" name="resvRealTime" readonly="readonly" style="width:130px;background-color: #c0d8d8;">&nbsp;&nbsp;&nbsp;</td>
											       <td>数量:</td>
											       <td><input id="totalNum" name="totalNum" readonly="readonly" style="background-color: #c0d8d8;" /></td>
											       <td>销售额:</td>
											       <td><input id="stomSumAmount" name="stomSumAmount" readonly="readonly" style="background-color: #c0d8d8;" ></td>
											       <td>含税成本额:</td>
											       <td><input id="taxCastamont" name="taxCastamont" readonly="readonly" style="background-color: #c0d8d8;" ></td>
											       <td></td>
											       <td>未税成本额:</td>
											       <td><input id="noTaxCastamont" name="noTaxCastamont" readonly="readonly" style="background-color: #c0d8d8;" ></td>
											       <td>备注:</td>
											       <td><input size="30" id="stomRemark" name="stomRemark" class="easyui-validatebox" validType="length[0,100]"></td>
											       </tr>
											       <tr>
												    <td>接待员:</td>
												    <td><input id="stfName" name="stfName" readonly="readonly" style="background-color: #c0d8d8;width:130px;">&nbsp;&nbsp;&nbsp;</td>
												    <td>业务员:</td>
												    <td><input id=""  readonly="readonly" style="background-color: #c0d8d8;"></td>
											       </tr>
										        </table>
											 </form>
									  </div>
									  <div region="center" style="background:#eee;" border="false">
									      <table id="so_stOutItemTable"></table>
									  </div>
									  <div region="south" split="false" style="overflow:hidden;pading:1px;height:30px;background:#eee;" border="false">
									     <table>
									        <tr>
									          <td colspan="6">
<%--												        <a href="javascript:void(0)" id="addParts" iconCls="icon-add" name="addParts"class="easyui-linkbutton" plain="true" onclick="addParts();">配件增加</a>--%>
<%--												        <a href="javascript:void(0)" id="deleteParts" iconCls="icon-remove"  name="deleteParts" class="easyui-linkbutton" plain="true" onclick="deleteParts();">配件删除</a>--%>
<%--												        <a href="javascript:void(0)" class="easyui-linkbutton" plain="true">配件库存</a>--%>
<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="addFrtReception();">计划材料</a>
<%--												        <a href="javascript:void(0)" class="easyui-linkbutton" plain="true">从Excel中导入</a>--%>
<%--												        <a href="javascript:void(0)" class="easyui-linkbutton" plain="true">维修项目</a>--%>
														
<%--														<a href="javascript:void(0)" class="easyui-linkbutton" plain="true">历史领料记录</a>--%>
											   </td>
									         </tr>
									     </table>
									  </div>
						  </div>
			          </div>
                  </div>  
             </div>  
      </div>
  </body>
</html>
