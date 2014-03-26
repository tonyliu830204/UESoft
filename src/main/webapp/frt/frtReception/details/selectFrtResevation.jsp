<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<!-- 选择预约/保险估价单 -->
<script type="text/javascript">
var url='frtResevationAction!datagridFrtResevationAndInsurePrize.action?resvStatus='+'<%=Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING%>';
<%
	String carId=request.getParameter("carId");
	if(carId!=null&&carId.length()>0){
	%>
	url+='?flag=true&carId=<%=carId %>';
	<%
	}
%>
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtReception/details/selectFrtResevation.js"></script>
<div class="easyui-layout" data-options="fit:true, border: false">
				<div region="center"  split="false" border="false">
					<div id="cc" class="easyui-layout" style="width:600px;height:500px;" fit="true" border="false">		
						<div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:3px; background:#eee; height:56px;" border="false">
								<form id="resvQueryForm" >		     
									<table>
										 <tr>
										 	<td>车牌照:</td>
										 	<td><input name="carLicense"  type="text"/>  </td>
										 	<td>Vin号:</td>
										 	<td><input name="carVin"  type="text"/> <a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query();">查询</a>
      	                                       <a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearResv();">清空</a> </td>
										</tr>
									</table>
							 </form>
			   			</div>	   		
			   			<div id="leixin" region="center" style="background:#eee;" border="false">
			   			 	<table id="selectFrtResevationDatagrid" ></table>
			   			</div>   
					</div>	
		   		</div>
  	   </div>