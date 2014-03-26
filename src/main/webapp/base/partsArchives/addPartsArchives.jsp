<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
var tag=false;
var flag="<%=request.getParameter("flag") %>";
var marker="<%=request.getParameter("marker") %>";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/partsArchives/addPartsArchives.js"></script>
<div class="easyui-layout" fit="true" border="false">
	<div region="center" style="background:#eee;" border="false">
		<form id="partsArchivesAddForm">
			<table align="center" width="100%" height="100%">
				<tr>
					<td>配件编码:</td>
					<td><input type="text" id="partsArchives_add_partsId" name="partsId"
					 class="easyui-validatebox" validType="length[0,30]" data-options="required : true"
					  style="width: 120px;" />
					</td>
					<td align="right">编码二:</td>
					<td><input type="text" name="partsId2" class="easyui-validatebox" validType="length[0,30]" style="width: 120px;"/>
					</td>
					<td>配件名称:</td>
					<td><input type="text" id="partsArchives_add_partsName" name="partsName" 
						class="easyui-validatebox" class="easyui-validatebox"  data-options="validType:'length[0,120]'" 
						data-options="required : true,
					    missingMessage : '配件名称为必填项' " style="width: 120px;"/>
					</td>
					<td align="right">简码:</td>
					<td><input type="text" id="partsArchives_add_partsSimpleId" name="partsSimpleId" readonly="readonly" 
					class="easyui-validatebox"   data-options="validType:'length[0,200]'" style="width: 120px;"/>
					</td>
				</tr>
				<tr>
					<td>配件品牌:</td>
					<td><input type="text" id="partsArchives_add_pbrdId" name="pbrdId" class="easyui-combobox" data-options="
					url : '${pageContext.request.contextPath}/basPartsArchivesAction!findPartsBrand.action',
					valueField:'id',  
	    			textField:'text',
	    			required : true,
	    			missingMessage : '配件品牌为必选项',
	    			validType:'isSelected[\'#partsArchives_add_pbrdId\']',
	    			invalidMessage : '请从下拉框中选择配件品牌',
	    			mode : 'remote',
	    			onSelect : function (record){
	    				$('#partsArchives_add_ptypeId').combobox('clear');
	    				$('#partsArchives_add_ptypeId').combobox('reload', '${pageContext.request.contextPath}/basPartsArchivesAction!findPartsType.action?pbrdId=' + record.id);
	    			} " style="width: 120px;"/></td>
					<td>配件型号:</td>
					<td><input type="text" id="partsArchives_add_ptypeId" name="ptypeId" class="easyui-combobox" data-options="
					url:'${pageContext.request.contextPath}/basPartsArchivesAction!findPartsType.action',
					valueField:'id',  
	    			textField:'text',
	    			required : true,
	    			missingMessage : '配件型号为必选项',
	    			validType:'isSelected[\'#partsArchives_add_ptypeId\']',
	    			invalidMessage : '请从下拉框中选择配件型号',
	    			mode : 'remote'" style="width: 120px;"/></td>
	    			<td>配件部位:</td>
					<td><input type="text" id="partsArchives_add_posName" name="posName" class="easyui-combobox" data-options="
					url : '${pageContext.request.contextPath}/basPartsArchivesAction!findAllPartsPosition.action',
					valueField:'id',  
	    			textField:'text',
	    			validType:'isSelected[\'#partsArchives_add_posName\']',
	    			invalidMessage : '请从下拉框中选择配件部位',
	    			mode : 'remote'" style="width: 120px;"/></td>
	    			<td align="right">单位:</td>
					<td><input type="text" id="partsArchives_add_punitName" name="punitName"
						class="easyui-combobox"
						data-options="
        			url : '${pageContext.request.contextPath}/basPartsArchivesAction!findAllPartsUnit.action',
					required : true,
					valueField:'id',  
				    textField:'text',  
				    validType:'isSelected[\'#partsArchives_add_punitName\']',
				    invalidMessage : '请从下拉框中选择单位',
					missingMessage : '单位为必选项', 
					mode : 'remote'" style="width: 120px;"/>
					</td>
				</tr>
				<tr>
					<!--<td>现有库存量:</td>
					<td><input type="text" name="partsNeedPoint" class="easyui-numberbox" data-options="" style="width: 120px;"/></td>
					<td align="right">库存上限:</td>
					<td><input type="text" id="partsArchives_add_stockUpper" name="stockUpper"
					 class="easyui-numberbox" data-options="min:0,precision:2" style="width: 120px;"/>
					</td>
					<td align="right">库存下限:</td>
					<td><input type="text" id="partsArchives_add_stockLower" name="stockLower"
					 class="easyui-numberbox" data-options="min:0,precision:2" style="width: 120px;"/>
					</td>-->
				 <td>适合车型:</td>
					<td colspan="7" >
					    <input id="partsArchives_add_fitPtype" name="fitPtype" class="easyui-combobox"  style="width: 670px;" 
			    		data-options="validType:'length[0,200]',valueField:'id',textField:'text',separator:',',multiple:true, mode : 'remote',
			    			url:'${pageContext.request.contextPath}/basCarArchivesAction!findCarTypeAsName.action',
			    			onLoadSuccess : function(data){
	    						
			    			}" />
					</td>
				</tr>
				<tr>
					<td align="right">特别价:</td>
					<td><input type="text" id="partsArchives_add_partsSpecialPrice" name="partsSpecialPrice"
					 class="easyui-numberbox" data-options="min:0,precision:2" style="width: 120px;"/>
					</td>
			
					<td align="right">产地:</td>
					<td colspan="3"><input type="text" id="partsArchives_add_stateName" name="stateName"
					 class="easyui-combobox" style="width:306px;" 
					 data-options="
        			url : '${pageContext.request.contextPath}/basPartsArchivesAction!findAllPartsState.action',
					valueField:'id',  
				    textField:'text',  
				    mode : 'remote',
				    validType:'isSelected[\'#partsArchives_add_stateName\']',
				    invalidMessage : '请从下拉框中选择产地',
					missingMessage : '配件产地为必选项' " style="width: 120px;"/>
					</td>
				</tr>
				<tr>
					<td align="right">维修价:</td>
					<td><input type="text" id="partsArchives_add_partsRepairPrice" name="partsRepairPrice"
					 class="easyui-numberbox" data-options="min:0,precision:2" style="width: 120px;"/>
					</td>
					<td align="right">销售价:</td>
					<td><input type="text" id="partsArchives_add_partsSellPrice" name="partsSellPrice"
					 class="easyui-numberbox" data-options="min:0,precision:2" style="width: 120px;"/>
					</td>
					<td align="right">索赔价:</td>
					<td><input type="text" id="partsArchives_add_partsClaimantPrice" name="partsClaimantPrice"
					 class="easyui-numberbox" data-options="min:0,precision:2" style="width: 120px;"/>
					</td>
					<td align="right">网点价:</td>
					<td><input type="text" id="partsArchives_add_partsPointPrice" name="partsPointPrice"
					 class="easyui-numberbox" data-options="min:0,precision:2" style="width: 120px;"/>
					</td>
				</tr>
				<tr>
					<td align="right">含税价:</td>
					<td><input type="text" id="partsArchives_add_partsLatestTaxprice" name="partsLatestTaxprice"
					 class="easyui-numberbox" data-options="min:0,precision:2" style="width: 120px;"/>
					</td>
					<td align="right">未税价:</td>
					<td><input type="text" id="partsArchives_add_partsLatestNotaxprice" name="partsLatestNotaxprice"
					 class="easyui-numberbox" data-options="min:0,precision:2" style="width: 120px;"/>
					</td>
					<td colspan="2" >
						<a href="javascript:void(0);" class="easyui-linkbutton" id="appendTranslaction"
							 data-options="iconCls:'icon-ok'" onclick="appendOrCancelTranslation(false);">全局调价</a>
					</td>
					<td colspan="2">
						<a href="javascript:void(0);" class="easyui-linkbutton"  id="cancelTranslaction"
							 data-options="iconCls:'icon-no'"  onclick="appendOrCancelTranslation(true);">取消调价</a>
					</td>
				</tr>
				<tr>
					<td align="right">库位:</td>
					<td><input type="text"name="partsLibrary" class="easyui-validatebox" validType="length[0,20]"  style="width: 120px;" maxlength="20"/>
					</td>
					<td align="right">规格:</td>
					<td><input type="text"name="partsSpecs" class="easyui-validatebox" validType="length[0,50]"   style="width: 120px;" maxlength="50"/>
					</td>
					<td align="right">属性:</td>
					<td><input type="text"name="partsProperty" class="easyui-validatebox" validType="length[0,20]"   style="width: 120px;" maxlength="20"/>
					</td>
					<td align="right">年份:</td>
					<td><input type="text"name="partsAge"  class="easyui-validatebox" validType="length[0,10]"  style="width: 120px;" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="partsFlag"/>
						<input type="hidden" id="translationFlag" name="translationFlag" value="false"/>
					</td>
				</tr>
<!--				<tr>-->
<!--					<td align="right">备注:</td>-->
<!--					<td colspan="7">-->
<!--						<textarea rows="" cols="" class="easyui-validatebox" validType="length[0,20]"  name="partsRemark"-->
<!--    						 style="width:674px;height:60px;"></textarea>-->
<!--					</td>-->
<!--				</tr>-->
			</table>
		</form>
	</div>
</div>