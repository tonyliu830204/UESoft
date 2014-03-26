<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%
String sysType = new String(request.getParameter("sysType").toString().getBytes("ISO8859_1"), "UTF-8");
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>人事资料设定</title>
    <script type="text/javascript">
	    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
	    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
	    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
	    var systemAll = "<%=Contstants.SYSTEMTYPE.ALL%>";
	    var staticAdministrator="<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
	    var staticEnterpriseAdmin="<%=Contstants.EMPLOYEELEVEL.ADMIN %>";
	    var staticEmployee="<%=Contstants.EMPLOYEELEVEL.GENERAL %>";
  		var staticLoginLevel="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getLeavl() %>";
	    var staticStfId="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId()%>";
	    var staticEnterpriseId="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getEnterpriseInfo().getEnterpriseId()%>";
	    var staticParentEnterpriseId="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getEnterpriseInfo().getParentEnterpriseId()%>";
	    var selectTag=true;
    </script>
  </head>

  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/base/personnel/qx.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mailAutoComplete-3.1.js"></script>
  	<div class="easyui-layout" style="width:100%;height:100%;">
	   <!-- 按钮区域 -->
	   <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
	        <span id="button0">
	        <privilege:enable code="PERSONADD">
	            <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true"  id="add" onclick="add();">新增</a>
	        </privilege:enable>
	        <privilege:enable code="PERSONDELETE">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delete" onclick="deletes();">删除</a>
	        </privilege:enable>
	        <privilege:enable code="PERSONUPDATA">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="update" onclick="update();">修改</a>
	        </privilege:enable>
	        <privilege:enable code="PERSONSEARCH">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"  plain="true" id="search" onclick="_search();">查询</a>
	        </privilege:enable>
	        <privilege:enable code="PERSONCLEAR">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel"  plain="true" id="qc" onclick="_clear();">清空</a>
	        </privilege:enable>
	        <privilege:enable code="PERSONTEM">
			    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" id="ody" onclick="_otherPrint();">套打模板</a>
			</privilege:enable>
			<privilege:enable code="PERSONPRINTSET">
			    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-set" plain="true" id="cf" onclick="_config();">打印设置</a>
			</privilege:enable>
			<privilege:enable code="PERSONEXPORT">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id="dc" onclick="_except();">Excel导出</a>
			</privilege:enable>
			<privilege:enable code="PERSONIMPORT">
			    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true" id="dr" onclick="_import();">Excel导入</a>
			</privilege:enable>
			<privilege:enable code="PERSONDISABLE">
			    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remind" plain="true" id="hide" onclick="_hideColumn();">隐藏列</a>
			</privilege:enable>
			<privilege:enable code="PERSONLOGOUT">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel2" plain="true" id="zx"  onclick="logout();">注销</a>
			</privilege:enable>
			<privilege:enable code="PERSONCHANGE">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-change" plain="true" id="hb"  onclick="change();">变更</a>
			</privilege:enable>
			</span>
			<span id="button"></span><br/>  
	   </div>
       <!-- 条件区域 -->
	   <div data-options="region:'center',border:false" style="background:#eee;">
		    <div id="dd" class="easyui-layout" style="width:600px;height: 60px;"   fit="true">
	            <div   region="north" style="overflow: hidden;padding:3px; background:#eee; height:30px;"  border="false" >
                     <!-- 查询条件 -->
					 <form id="pisForm" name="pisForm" method="post"  fit="true" >
						   <table   >
								<tr>
									<td>编号:</td>
									<td><input type="text" name="stfYid" style="width: 90px;" /></td>
									<td>姓名:</td>
									<td><input type="text" name="stfName"  style="width: 90px;" id="stfNameF" /></td>
									<td>系统用户:</td>
									<td>  <input id="stfYesF"  name="stfYes"  style="width: 50px;" class="easyui-combobox" data-options="
													 valueField: 'id',   
											         textField: 'text',
											         panelHeight : 75,
											         url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.SYSTEMUSER.PSTFYES %>',
											         editable: false
									"/> 
									</td>
									<td>所属部门:</td>
									<td><input id="deptIdF" class="easyui-combobox" name="deptId" data-options="   
											         valueField: 'id',   
											         textField: 'text',  
											         url: 'basPersonnelInformationSetAction!findAllDept.action',
											         editable: false"/>
									</td>
									<td>维修班组:</td>
									<td><input  id="repgrpIdF" class="easyui-combobox" name="repgrpId" style="width: 85px;" data-options="   
											         valueField: 'id',   
											         textField: 'text',  
											         url: 'basPersonnelInformationSetAction!findAllCJDept.action',
											         editable: false "  /> 
									 </td>
									<td>注销情况:</td>
									<td><input id="stfZxqkF"  name="stfZxqk "  style="width: 75px;" class="easyui-combobox" 
										data-options="valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.ZXQKF.ZXQKF %>',editable:false"/> 
									</td>
								</tr>
						   </table>
			    	 </form>
	            </div> 
	            
	            <!-- 展现数据list区域 -->	  
		        <div id="personnelCenter" region="center"  style="background:#eee;" border="false">
		           <table id="personnelInformationSet"></table>
		        </div> 
		        
		        <!-- 编辑区域 -->
				<div region="south" split="false" style="height:200px; background:#eee;"  border="false">
				   	<form  id="jBxx" name="jBxx" style="height:100%;width:100%;">
				   	    <s:token></s:token>
					   	<div id="tab_id" class="easyui-tabs"  border="false"  style="background:#eee;"  fit="true" >
					   	    <!-- 基本信息 -->
					   	   	<div title="基本信息"  >
				   			     <table  style="background:#eee; ">
									 <tr>  
									    <input type="hidden" id="stfId" name="stfId" value="" />
									    <input type="hidden" id="stfZxqk" name="stfZxqk" value="使用中"/>
									    <input type="hidden" id="userId" name="userId" />
									    <input type="hidden" id="loginName" name="loginName" />
									    <input type="hidden" id="loginPassword" name="loginPassword" />
									    <input type="hidden" id="stfYid"  name="stfYid"/>
									    <input type="hidden" id="leavl"  name="leavl"/>
									    <input type="hidden" id="systemKey" name="systemKey" value="<%=sysType%>">
									    <td width="68">姓名:</td>
									    <td><input id="stfName" name="stfName"  disabled="disabled" onkeydown="EnterPress($('#stfMark'),event)" type="text" class="easyui-validatebox" onkeyup="handle();"	 
			   							     data-options="required: true,editable : false,missingMessage:'姓名为必填项'" style="width: 140px;" /></td>
									    <td width="68">助记码:</td>
									    <td><input id="stfMark" name="stfMark" disabled="disabled" readonly="readonly" style="width:140px;background:#eee;" type="text" /></td>
									    <td width="68">所属公司:</td>
									    <td colspan="5"><input id="enterpriseId" name="enterpriseId" style="width: 570px;" class="easyui-combotree"  disabled="disabled"
				   				          data-options="editable:false,required:true,missingMessage:'公司为必选项',
				   				          	url:'${pageContext.request.contextPath}/basPersonnelInformationSetAction!findAllCompany.action',
				   				          	onSelect:function(record){
				   				          			$('#deptId').combobox('reload','${pageContext.request.contextPath}/basPersonnelInformationSetAction!findAllDept.action?enterpriseId='+record.id);
				   				          			$('#repgrpId').combobox('reload','${pageContext.request.contextPath}/basPersonnelInformationSetAction!findAllCJDept.action?enterpriseId='+record.id);
				   				          			$('#repgrpId2').combobox('reload','${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.SELLTEAM%>&enterpriseId='+record.id);
				   				          	}"/> 
									    </td>
									 </tr>
									 <tr>
									    <td width="68">性别:</td>
									    <td><input id="stfSex" name="stfSex" disabled="disabled" class="easyui-combobox" style="width: 140px;"
			   							   data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.SEXTYPE.SEX %>',panelHeight:75">
									    </td>
									    <td width="68">系统用户:</td>
									    <td><input id="stfYes"  name="stfYes" disabled="disabled"  style="width: 140px;" class="easyui-combobox" 
				   				            data-options="valueField:'id',textField:'text',required:true,missingMessage:'系统用户为必选项',url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.SYSTEMUSER.PSTFYES%>',panelHeight:75"/></td>
									    <td width="68">所属部门:</td>
									    <td><input id="deptId" name="deptId" style="width: 140px;" class="easyui-combobox" disabled="disabled"
				   				          data-options="editable:false,valueField:'id',textField:'text',required:true,missingMessage:'部门为必选项',
				   				          	url:'${pageContext.request.contextPath}/basPersonnelInformationSetAction!findAllDept.action?enterpriseId=-1'"/> 
									    </td>
									    <td width="68">维修班组:</td>
									    <td><input  id="repgrpId" name="repgrpId" class="easyui-combobox" disabled="disabled"  style="width: 140px;" 
			                              data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/basPersonnelInformationSetAction!findAllCJDept.action'"/> 
									    </td>
									    <td width="68">销售班组:</td>
									    <td><input  id="repgrpId2" name="repgrpId2" class="easyui-combobox" disabled="disabled"  style="width: 140px;" 
			                              data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.SELLTEAM%>'"/> 
									    </td>
									 </tr>         
									 <tr>
									    <td width="68">职位工种:</td>
									    <td><input  id="stfZwgz"  name="stfZwgz" onkeydown="EnterPress($('#stfPhone'),event)" disabled="disabled"  style="width: 140px;"/> </td> 
									    <td width="68">移动电话:</td>
									    <td><input type="text" id="stfPhone" name="stfPhone" disabled="disabled" style="width: 140px;" onkeydown="EnterPress($('#stfTel'),event)" class="easyui-validatebox" data-options="validType:'mobile'"/></td>
									    <td width="68">固定电话:</td>
									    <td><input type="text" name="stfTel"  disabled="disabled"  id="stfTel" style="width: 140px;" onkeydown="EnterPress($('#stfBirthday'),event)" class="easyui-validatebox"  data-options="validType:'phone'"/></td>
									    <td width="68">出生年月:</td>
									    <td><input name="stfBirthday" type="text"  disabled="disabled"  class="Wdate" onkeydown="EnterPress($('#stfGj'),event)" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;" id="stfBirthday"/></td>
									    <td width="68">入职日期:</td>
									    <td><input type="text" name="stfWorkDate" disabled="disabled" style="width: 140px;" class="Wdate" onkeydown="EnterPress($('#jobProId'),event)"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="stfWorkDate"/></td>
									</tr>
									<tr>
										<td width="68">工作属性:</td>
										<td><input id="jobProId" name="jobProId" disabled="disabled" class="easyui-combobox" style="width: 140px;"
										    data-options="url:'${pageContext.request.contextPath}/basPersonnelInformationSetAction!findAllJobProperty.action?systemKey=<%=sysType %>',
										                  valueField:'id',
										                  textField:'text',
										                  required:true,
										                  multiple:true,
										                  missingMessage:'工作属性为必选项'"/> </td>
										<td colspan="2">
										    <div id="expand" style="display:none;">
										       <table>
										            <tr>
										                <td width="68">所属系统:</td>
										                <td>
										                    <input id="systemId" name="systemId" style="width:140px;" class="easyui-combobox"  
										                    		data-options="disabled:true,
										                    		onLoadSuccess:function(){
										                    			$('#systemId').combobox('setValue','<%=Contstants.SYSTEMTYPE.ALL %>');
										                    		}"/> 
										                </td>
										            </tr>
										       </table>                        
										    </div>	
										   </td>
										 <td width="68">籍贯:</td> 
									   <td colspan="5"><input id="stfGj" name="stfGj" disabled="disabled" type="text" onkeydown="EnterPress($('#stfWorkDate'),event)" style="width: 570px;" /></td>									
									</tr>
								 </table>
				   			</div>
					   		<div title="其他信息" style="background:#eee;">
			   				   <table  >
		   						   <tr>
				   						<td width="110">毕业院校:</td>
				   						<td><input type="text" id="stfByyx" name="stfByyx" disabled="disabled" onkeydown="EnterPress($('#stfBysj'),event)" style="width: 140px;" /></td>
				   						<td width="110">毕业时间:</td>
				   						<td><input type="text" id="stfBysj" name="stfBysj" class="Wdate" onkeydown="EnterPress($('#stfSxzy'),event)" disabled="disabled" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;"/></td>
				   					    <td width="110">所学专业:</td>
				   					    <td><input type="text" id="stfSxzy" name="stfSxzy" disabled="disabled" onkeydown="EnterPress($('#stfWhcd'),event)" style=" width: 140px;"/></td>
				   					    <td width="110">文化程度:</td>
				   					    <td><input id="stfWhcd" name="stfWhcd"  style=" width: 140px;" class="easyui-combobox" 
				   					      data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.WHCD.WHCD%>',panelHeight:75">	   
					   					</td>
					   				</tr>	
					   				<tr> 
				   					    <td width="110">工作年限:</td>
				   					    <td><input type="text" id="stfGznx" name="stfGznx" style=" width: 140px;" disabled="disabled" onkeydown="EnterPress($('#stfJsdj'),event)"/></td>
				   					    <td width="110">技术等级:</td>
				   					    <td><input type="text" id="stfJsdj" name="stfJsdj" style=" width: 140px;" disabled="disabled" onkeydown="EnterPress($('#stfYhbyzs'),event)"/></td>
				   					 	<td width="110">职业证书:</td>
				   					 	<td><input type="text" id="stfYhbyzs" name="stfYhbyzs" style=" width: 140px;" disabled="disabled" onkeydown="EnterPress($('#stfSfzhm'),event)"/></td>
	  							            <td width="110">身份证号码:</td>
				   						<td><input type="text" id="stfSfzhm" name="stfSfzhm" style=" width: 140px;" disabled="disabled" onkeydown="EnterPress($('#stfSg'),event)" class="easyui-validatebox"  data-options="validType:'idcard'"/></td>
				   				   </tr>	
					   			   <tr> 
				   					    <td width="110">身高:</td>
				   						<td><input type="text" id="stfSg" name="stfSg" style=" width: 122px;" disabled="disabled" onkeydown="EnterPress($('#stfTz'),event)"/>&nbsp;cm</td>
				   					    <td width="110">体重:</td>
				   					    <td><input type="text" id="stfTz" name="stfTz" style="width: 123px;" disabled="disabled" onkeydown="EnterPress($('#stfSl'),event)"/>&nbsp;kg</td>
				   					    <td width="110">视力:</td>
				   					    <td><input type="text" id="stfSl" name="stfSl" style="width: 140px;" disabled="disabled" onkeydown="EnterPress($('#stfXx'),event)"/></td>
				   					    <td width="110">血型:</td>
				   					     <td><input id="stfXx" name="stfXx"  style="width: 140px;" class="easyui-combobox" 
				   					     data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.XX.XX%>',panelHeight:75">
					   					</td>
					   			   </tr>
		   						   <tr>
				   					    <td width="110">婚姻状况:</td>
				   					    <td>
				   					       <input id="stfHyzk" name="stfHyzk" style="width: 140px;" class="easyui-combobox" 
				   					       data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.HYZK.HYZK%>',panelHeight:75"/>
					   					</td>
				   					    <td width="110">户口所在地:</td>
				   					   	<td><input type="text" id="stfHkszd" name="stfHkszd" style="width: 140px;" disabled="disabled" onkeydown="EnterPress($('#stfMz'),event)"/></td>
	  							            <td width="110">民族:</td>
				   						<td><input type="text" id="stfMz" name="stfMz" style="width: 140px;" disabled="disabled" onkeydown="EnterPress($('#stfZzmm'),event)"/></td>
				   						<td width="110">政治面貌:</td>
				   						<td><input type="text"   style="width: 140px;" id="stfZzmm" name="stfZzmm" disabled="disabled" onkeydown="EnterPress($('#stfWysp'),event)"/></td>
				   				  </tr>
		   						  <tr>	    
				   					    <td width="110">外语水平:</td>
				   					    <td><input type="text" id="stfWysp" name="stfWysp" disabled="disabled" onkeydown="EnterPress($('#stfJsjsp'),event)" style="width: 140px;"/></td>
				   					    <td width="110">计算机水平:</td>
				   					    <td><input type="text" disabled="disabled" style="width: 140px;" id="stfJsjsp" name="stfJsjsp" onkeydown="EnterPress($('#stfDzyx'),event)"/></td>
				   					    <td width="110">电子邮箱:</td>
				   					    <td><input type="text"  style="width: 140px;" id="stfDzyx" name="stfDzyx" onkeydown="EnterPress($('#stfXjzdz'),event)" disabled="disabled" class="easyui-validatebox" data-options="validType:'email'"  /></td>
			                            <td width="110">现居住地址:</td>
				   					    <td><input type="text" id="stfXjzdz" name="stfXjzdz" style="width: 140px;" onkeydown="EnterPress($('#stfDbrxm'),event)" disabled="disabled"/></td> 
		   						   </tr>
		   						   <tr>
	  							            <td width="110">担保人姓名:</td>
				   						<td><input type="text" id="stfDbrxm" name="stfDbrxm"style="width: 140px;" disabled="disabled" onkeydown="EnterPress($('#stfDbrsfzhm'),event)"/></td>
				   						<td width="110">担保人身份证号码:</td>
				   						<td><input type="text" style="width: 140px;" id="stfDbrsfzhm" name="stfDbrsfzhm" disabled="disabled" onkeydown="EnterPress($('#stfPoxm'),event)" class="easyui-validatebox"  data-options="validType:'idcard'" /></td>
				   					    <td width="110">配偶姓名:</td>
				   					    <td><input type="text" id="stfPoxm" name="stfPoxm" disabled="disabled" onkeydown="EnterPress($('#stfPosfzhm'),event)" style="width: 140px;"/></td>
				   					    <td width="110">配偶身份证号码:</td>
				   					    <td><input type="text"  style="width: 140px;" id="stfPosfzhm" name="stfPosfzhm" disabled="disabled" onkeydown="EnterPress($('#stfYjrq'),event)" class="easyui-validatebox"  data-options="validType:'idcard'"/></td>
				   				  </tr>
		   						  <tr>
				   					    <td width="110">押金日期:</td>
				   					    <td><input type="text"  style="width: 140px;" class="Wdate" onkeydown="EnterPress($('#stfYjje'),event)" disabled="disabled" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="stfYjrq" name="stfYjrq"/></td>  
				   					    <td width="110">押金金额:</td>
				   					    <td><input type="text"  style="width: 140px;" id="stfYjje" name="stfYjje" disabled="disabled" onkeydown="EnterPress($('#stfYjbz'),event)"/></td>
				   					    <td width="110">押金备注:</td>
				   					    <td><input  type="text" id="stfYjbz" name="stfYjbz"  style="width: 140px;" disabled="disabled" onkeydown="EnterPress($('#stfAh'),event)"></td>
		   						   </tr>
		   						   <tr>
			   						   <td width="110">爱好:</td>
			   						   <td colspan="3" rowspan="5">
			   						        <textarea rows="" cols="5" id="stfAh" name="stfAh" disabled="disabled" id="stfAh" style="width:100%;height:100%;" onkeydown="EnterPress($('#stfTc'),event)"></textarea>
			   						   </td>
			   						   <td width="110">特长:</td>
		   						       <td colspan="3" rowspan="5">
			   						        <textarea rows="" cols="5"  disabled="disabled" name="stfTc"  id="stfTc"  style="width:100%;height:100%;" onkeydown="EnterPress($('#stfByyx'),event)"></textarea>
			   						   </td>
		   						   </tr>
			   				   </table>
					    	</div> 
					   	</div>
				    </form>
			   </div>
           </div>    
       </div>
       </div>
  </body>
</html>