package com.syuesoft.bas.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.VipServiceProjectService;
import com.syuesoft.base.vo.VipServiceProjectVo;
import com.syuesoft.model.VipMeal;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Message;

@ParentPackage(value = "basePackage")
@Action("vipServiceProjectAction")
public class VipServiceProjectAction extends BaseAction implements
        ModelDriven<VipServiceProjectVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private VipServiceProjectVo vipServiceProjectVo = new VipServiceProjectVo();
    @Autowired
    private VipServiceProjectService vipServiceProjectService;

    // 会员卡服务项目汇总查询
	public String doFind()
    {
        try{
        	vipServiceProjectVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(vipServiceProjectService.doFind(vipServiceProjectVo));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // 删除
    public String doDelete()
    {
        Message J = new Message();
        try{
            vipServiceProjectService.doDelete(vipServiceProjectVo);
            J.setSuccess(true);
        }catch(Exception e){
            J.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;
    }

    // 修改
    public String doUpdate()
    {
        Message J = new Message();
        try{
        	vipServiceProjectVo.setEnterpriseId(getNowEnterpriseId());
            vipServiceProjectService.doUpdate(vipServiceProjectVo);
            J.setSuccess(true);
        }catch(Exception e){
            e.printStackTrace();
            J.setSuccess(false);
            J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
        }
        super.writeJson(J);
        return null;
    }

    // 新增
    public String doAdd()
    {
        Message J = new Message();
        try{
        	vipServiceProjectVo.setEnterpriseId(getUserEnterpriseId());
            vipServiceProjectService.doAdd(vipServiceProjectVo);
            J.setSuccess(true);
        }catch(Exception e){
            e.printStackTrace();
            J.setSuccess(false);
            J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
        }
        super.writeJson(J);
        return null;
    }

    // 会员卡服务项目明细查询
	public String doDetailFind()
    {
        try{
            super.writeJson(vipServiceProjectService.doDetailFind(vipServiceProjectVo));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // 删除
    public String doDetailDelete()
    {
        Message J = new Message();
        try{
            vipServiceProjectService.doDetailDelete(vipServiceProjectVo);
            J.setSuccess(true);
        }catch(Exception e){
            J.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;
    }

    // 修改
    public String doDetailUpdate()
    {
        Message J = new Message();
        try{
            vipServiceProjectService.doDetailUpdate(vipServiceProjectVo);
            J.setSuccess(true);
        }catch(Exception e){
            e.printStackTrace();
            J.setSuccess(false);
            J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
        }
        super.writeJson(J);
        return null;
    }

    // 新增
    public String doDetailAdd()
    {
        Message j = new Message();
        try{
            vipServiceProjectService.doDetailAdd(vipServiceProjectVo);
            j.setMsg("添加成功");
            j.setSuccess(true);
        }catch(Exception e){
            e.printStackTrace();
            j.setSuccess(false);
            j.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
        }
        super.writeJson(j);
        return null;
    }

    // 获取套餐名称
    @SuppressWarnings("unchecked")
	public String getMealName()
    {
        List list = new ArrayList();
        try{
            List<VipMeal> rlist = vipServiceProjectService.getMealName(getNowEnterpriseId());
            for (VipMeal vipMeal : rlist)
            {
                ComboBoxJson cjson = new ComboBoxJson();
                cjson.setId(vipMeal.getMealId().toString());
                cjson.setText(vipMeal.getMealName());
                list.add(cjson);
            }
            super.writeJson(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    public VipServiceProjectVo getModel(){
        return vipServiceProjectVo;
    }
    
    public VipServiceProjectService getVipServiceProjectService(){
        return vipServiceProjectService;
    }

    public void setVipServiceProjectService(
            VipServiceProjectService vipServiceProjectService){
        this.vipServiceProjectService = vipServiceProjectService;
    }

}
