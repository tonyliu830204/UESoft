<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 退货单模块-入库单信息选择 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StRtGoods/StRtGoodsDetial_StStorageItem.js"></script>
<!--入库单信息选择 -->
 <div id="cc" class="easyui-layout" fit="true" border="false">   
       <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
	    <form id="srg_StStorageMainTableForm">
	         <table>
		        <tr>
		          <td>入库单号:</td>
		          <td><input name="storageId" /></td>
				  <td>仓别:</td>
				  <td><input type="text" name="storeId" class="easyui-combobox" data-options="
					url:'${pageContext.request.contextPath}/StMoveStoreHouseAction!loadBasStorehouse.action'
					,editable:false
					,valueField:'id'   
		    		,textField:'text'"/>
		    	  </td>
		    	  <td>
				      <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query1();">查询</a>
					  <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear1();">清空</a>
				  </td>
		        </tr>
	         </table>
       </form>
    </div>
    <div region="center" style="background:#eee;" border="false">
      <table id="srg_StStorageMainTable"></table> 
    </div>
 </div>


