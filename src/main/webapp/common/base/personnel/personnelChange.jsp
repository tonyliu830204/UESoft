<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 人事资料变更-->
	       <form  id="personnelChangeFrom" name="personnelChangeFrom" style="height:100%;width:100%;">
	       <input type="hidden" id="stfId" name="stfId" value="" />
<fieldset>
    <legend  style="font-size: 13px;  font-weight: bold" >原人事信息</legend>
    <table>
           <tr>
                <td>原人事编号 ：</td>
                <td><input type="text" id="pcStfYid" name="pcStfYid" readonly="readonly" style="background:#eee;" />
                    <input type="hidden" id="pcstfId" name="pcstfId" />
                </td>
           </tr>
            <tr>
                <td>原人事姓名 ：</td>
                <td><input  type="text"   id="pcStfName" name="pcStfName" readonly="readonly" style="background:#eee;" /></td>
           </tr>
    </table>
</fieldset>
  <p></p>
	  <fieldset>
	       <legend  style="font-size: 13px;  font-weight: bold">新人事信息</legend>
	            <table>
	                  <tr>
                          <td>新人事编号 ：</td>
                          <td><input type="text" id="newstfYid" name="stfYid" class="easyui-validatebox" 	 
                          data-options="required:true,editable:false,missingMessage:'编号为必填项'" /></td>
                      </tr>
			          <tr>
			              <td>新人事姓名 ：</td>
                          <td><input type="text" id="newstfName" name="stfName" readonly="readonly" style="background:#eee; "/></td>  
			          </tr>
	            </table>
	  </fieldset>
	       </form>
  <fieldset>
	       <legend   style="color: red; font-size:13px;  font-weight: bold">人事资料变更说明</legend>
	            <p> <label  style="color: red;">功能：如果人事编号输入错误，您可以在该界面进行变更。</label></p>
	            <p> <label  style="color: red;">注意：新人事编号请不要与系统已经存在的人事编号重复！</label></p>	             
	  </fieldset>