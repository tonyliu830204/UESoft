<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<form id="sellEditForm" style="margin-top: 20px">
	<input type="hidden" name="id" id="id"  />
	<input type="hidden" name="purchaseId" id="purchaseId"  />
	<input type="hidden" id="enterprise_id" name="enterprise_id"/>
	<table align="center" cellpadding="2">
		<tr>
			<td style="width: 100px">计划月份:</td>
			<td><input type="text" name="sellDate" id="sellDate" class="easyui-datebox" editable="false" style="width: 150px" disabled="disabled"  required="required"/></td>
			<td style="width: 100px">品牌:
						</td>
							<td><input type="text" id="car_Brand" name="carBrand" style="width:140px;" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
							required:true,
							valueField:'id',   
				    		textField:'text',
				    		mode:'remote',			    		
				    		validType:'isSelected[\'#car_Brand\']',
				    		invalidMessage : '请从下拉框中选择车辆品牌',
				    		onSelect: function(rec){  
				    			$(this).combobox('textbox').bind('keyup', function (){
				    				if($('#car_Brand').combobox('getValue') == '' || $('#car_Brand').combobox('getValue') != $('#car_Brand').combobox('getText')){
				    					$('#car_Model').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
				    				}
				    			});
					            $('#car_Model').combobox('clear');
					            $('#car_Model').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
					        } "
					        />
					       
					        </td>
			

		</tr>
		<tr>
	
						<td style="width: 100px">车型:</td>
							<td><input type="text" id="car_Model" name="carModelId" style="width:150px;"  class="easyui-combobox" 
							data-options="
							url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
							required:true,
							valueField:'id',   
				    		textField:'text',
				    		mode:'remote',
				    		validType:'isSelected[\'#car_Model\']',
				    		invalidMessage : '请从下拉框中选择车辆型号',
				    		onSelect:function(rec){
				    			$(this).combobox('textbox').bind('keyup', function (){
				    				if($('#car_Model').combobox('getValue') == '' || $('#car_Model').combobox('getValue') != $('#car_Model').combobox('getText')){
				    					$('#carArchives_add_carCstlId').combobox('reload', 'carModelAction!findAllCarModel.action');
				    				}
				    			});  
					         
					        } "/>
					 </td>   
			
			<td style="width: 100px">颜色:</td>
			<td ><input type="text" name="carColor"  style="width:140px;" id="carColor"  class="easyui-combobox" disabled="disabled"   data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR%>',
				required:true,
				editable:false,
	    		valueField:'id',   
	    		textField:'text',
	    		mode : 'remote',
	    		validType:'isSelected[\'#carColor\']',
				invalidMessage : '请从下拉框中选择颜色'"/></td>
				</tr>
				<tr>
			<td style="width: 100px">计划采购数量:</td>
			<td ><input type="text" name="planNumber"  class="easyui-numberbox" style="width: 150px"    data-options="required:true,min:1,value:1,max:99999999,validType:'length[0,8]'" disabled="disabled"  /></td>
		
		
		
			<td style="width: 100px">实际采购数量:</td>
			<td ><input type="text" name="actualNumber"  id="actualNumber" class="easyui-numberbox" style="width: 140px"  data-options="min:0,value:0,max:99999999,validType:'length[0,8]'" disabled="disabled" /></td>
			
		</tr>
		
		<tr>
			<td style="width: 100px">完成百分比:</td>
			<td ><input type="text" name="planPercent"  readonly="readonly"  style="background-color:  #c0d8d8;width: 150px" disabled="disabled"/></td>	

		</tr>
		
		<tr>

			<td style="width: 100px">问题反馈:</td>
			<td colspan="3"><textarea  name="remark"  id="remark"    class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,100]\']'"   maxlength="101"  style="width: 400px;resize:none;" disabled="disabled" ></textarea></td>

			

		</tr>
	</table>
</form>