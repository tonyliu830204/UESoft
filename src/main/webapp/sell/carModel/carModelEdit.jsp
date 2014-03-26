<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%@ taglib uri="/priveliege" prefix="privilege"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<script type="text/javascript">
	$("#modelName").validatebox({required:true});
	$('#modelName').validatebox('validate');
	function salePrice(){
			var val1=$('#modelSalesPrice').val();
			var val2=$('#model_Sales_Limit_Price').val();
			if(parseFloat(val1)<parseFloat(val2)){
				$('#model_Sales_Limit_Price').numberbox('setValue', 0);
				alert("销售限价不能大于销售价!");
				}
		}
	function salePriceTwo(){
		var val1=$('#model_Sales_Limit_Price').val();
		var val2=$('#modelTwoSalesLimitPrice').val();
		if(parseFloat(val1)<parseFloat(val2)){
			$('#modelTwoSalesLimitPrice').numberbox('setValue', 0);
			alert("二级限价不能大于销售限价!");
			}
	}
	function salePriceThree(){
		var twoSales=$('#modelTwoSalesLimitPrice').val();
		var threeSales=$('#modelThreeSalesLimitPrice').val();
		if(parseFloat(twoSales)<parseFloat(threeSales)){
			$('#modelThreeSalesLimitPrice').numberbox('setValue', 0);
			alert("三级限价不能大于二级限价!");
			}
	}
</script>

		<form id="carModelEditForm">
			<input type="hidden" name="modelId"/>
			<input type="hidden" name="modelCode"/>
			<input type="hidden" id="enterpriseId" name="enterpriseId"/>
			<br>
			<table align="center" style="margin-top:1px;" >
				<tr>
					<td style="width:60px">车型品牌:</td>
					<td colspan="3"><input type="text"  name="carBrand" id="carBrand_id" style="width:450px;margin-left:0px;" class="easyui-combobox" data-options="
						url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
						valueField:'id',   
				   		textField:'text',
				   		required:true,
				   		validType:'isSelected[\'#carBrand_id\']',
				   		invalidMessage : '请从下拉框中选择车辆品牌',
				   		mode:'remote'
				   "/></td>
				</tr>
				<tr>
					<td style="width:60px">车型名称:</td>
					<td colspan="3"><input type="text" id="modelName" name="modelName" style="width:450px;margin-left:0px;" class="easyui-validatebox" data-options="required:true,missingMessage:'车型名称为必填项',validType:'characterDigit'"  maxlength="20"/></td >			
				</tr>
				<tr>
					<td style="width:60px">规格型号:</td>
					<td colspan="3"><input type="text" name="modelNormsModel" style="width:450px;margin-left:0px;" class="easyui-validatebox" data-options="validType:'characterDigit'" maxlength="20"/></td>
				</tr>
				<tr>
					<td style="width:60px">成本价:</td>
					<td ><input type="text" id="modelCostPrice" name="modelCostPrice"  precision="2" style="width:188px;margin-left:0px;" data-options="validType:'monery',min:0" value="0" class="easyui-numberbox" maxlength="12" /></td>
					<td style="width:60px">取消型号:</td>
					<td ><input id="modelCancelModel"  name="modelCancelModel" class="easyui-combobox" style="width: 188px;"  
			            	data-options="
						    editable : false,
						    data:[{'id':2,'text':'是'},
						    {'id':1,'text':'否'}],
							valueField:'id',
							textField:'text',
							onLoadSuccess: function(rec){
								$(this).combobox('setValue',1);
							}
							"/></td>
				</tr>
				<tr>
					<td style="width:60px">销售价:</td>
					<td><input type="text"  name="modelSalesPrice" id="modelSalesPrice" style="width:188px;margin-left:0px;"  precision="2" data-options="validType:'monery',min:0" value="0" class="easyui-numberbox" maxlength="12"/></td>
					<td colspan="2">
						<privilege:enable code="SALESLIMIT">
						    <table style="width:100%;height:100%;">
						        <tr>
									<td style="width:60px">销售限价:</td>
									<td><input type="text"  name="modelSalesLimitPrice"  id="model_Sales_Limit_Price" style="width:188px;margin-left:0px;"  value="0" 
												data-options="validType:'monery',min:0"  class="easyui-numberbox" precision="2" maxlength="12"  onkeyup="salePrice();"></td>
						        </tr>
						    </table>
						</privilege:enable>
					</td>
				</tr>
				<tr>
				    <td colspan="2">
					    <privilege:enable code="TWOSALESLIMIT">
				            <table style="width:100%;height:100%;">
						        <tr>
									<td style="width:60px">二级限价:</td>
									<td><input type="text" name="modelTwoSalesLimitPrice" id="modelTwoSalesLimitPrice" value="0" 
									precision="2"style="width:188px;margin-left:0px;" class="easyui-numberbox" 
									data-options="validType:'monery',min:0" maxlength="12"  onkeyup="salePriceTwo();"/></td>
						        </tr>
						    </table>
						</privilege:enable>
				    </td>
				    <td colspan="2">
						 <privilege:enable code="THREESALESLIMIT">
						    <table style="width:100%;height:100%;">
						        <tr>
									<td style="width:60px">三级限价:</td>
									<td><input type="text" name="modelThreeSalesLimitPrice" id="modelThreeSalesLimitPrice"
									 value="0" precision="2" style="width:188px;margin-left:0px;" class="easyui-numberbox" 
									 data-options="validType:'monery',min:0" maxlength="12" onkeyup="salePriceThree();" /></td>
						        </tr>
						    </table>
						 </privilege:enable>
				    </td>
				</tr>
				<tr>
					<td style="width:60px">备注:</td>
					<td colspan="3" ><textarea id="modelRemark" name="modelRemark" style="width:450px;margin-left:0px;;resize:none;" class="easyui-validatebox" data-options="validType:'characterDigit'" maxlength="50"/> </textarea></td>
				</tr>
			</table>
		</form>