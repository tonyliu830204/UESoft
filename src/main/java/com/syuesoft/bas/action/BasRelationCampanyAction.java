package com.syuesoft.bas.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasRelationCampanyService;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.base.vo.BasRelationCampanyVo;
import com.syuesoft.util.Msg;

/**
 * 相关单位action-(包含供应商和保险公司等)
 * @author suming
 */
@SuppressWarnings("serial")
@ParentPackage(value = "basePackage")
@Action("basRelationCampanyAction")
public class BasRelationCampanyAction extends BaseAction implements
        ModelDriven<BasRelationCampanyVo>
{
    BasRelationCampanyVo brcVo = new BasRelationCampanyVo();
    private final Logger logger = Logger.getLogger(this.getClass());
    private BasRelationCampanyService basRelationCampanyService;
    private BaseService baseService;

    /**
     * 查询保险(汽厂)属性
     */
    public void findAllRelcampAttr(){
        try{
            super.writeJson(baseService.findAllRelcampAttr(getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询保险(汽厂)属性失败", e);
        }
    }

    /**
     * 供应商档案datagrid
     */
    public void datagridSupplierArchives(){
        try{
        	brcVo.setNowEnterpriseId(getNowEnterpriseId());
            super.writeJson(basRelationCampanyService.datagridSupplierArchives(brcVo));
        }catch(Exception e){
            logger.error("供应商档案datagrid失败", e);
        }
    }

    /**
     * 保险(汽厂)datagrid
     */
    public void datagridInsuranceCarArchives(){
        try{
        	brcVo.setNowEnterpriseId(getNowEnterpriseId());
            super.writeJson(basRelationCampanyService.datagridInsuranceCarArchives(brcVo));
        }catch(Exception e){
            logger.error("保险(汽厂)datagrid失败", e);
        }
    }

    /**
     * 保存供应商档案
     */
    public void saveSupplierArchives(){
        Msg msg = new Msg();
        try{
        	brcVo.setEnterpriseId(getUserEnterpriseId());
        	brcVo.setNowEnterpriseId(getNowEnterpriseId());
            boolean isno = basRelationCampanyService.isExist(brcVo, 0);
            if (isno){
                basRelationCampanyService.saveSupplierArchives(brcVo);
                msg.setSuccess(true);
                msg.setMsg("保存供应商档案成功！");
            } else{
                msg.setSuccess(false);
                msg.setMsg("对不起，您输入的供应商名称已存在，请重新输入！");
            }
        }catch(Exception e){
            msg.setMsg("对不起，您输入的供应商名称已存在，请重新输入！");
            logger.error("对不起，您输入的供应商名称已存在，请重新输入！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 删除供应商档案
     */
    public void removeSupplierArchives(){
        Msg msg = new Msg();
        try{
            basRelationCampanyService.remove(brcVo.getRelcampId());
            msg.setSuccess(true);
            msg.setMsg("删除供应商档案成功！");
        }catch(Exception e){
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            logger.error("对不起，该数据已经被使用，不允许删除！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 修改供应商档案
     */
    public void editSupplierArchives(){
        Msg msg = new Msg();
        try{
        	brcVo.setEnterpriseId(getUserEnterpriseId());
        	brcVo.setNowEnterpriseId(getNowEnterpriseId());
            boolean isno = basRelationCampanyService.isExist(brcVo, 0);
            if (isno){
                basRelationCampanyService.editSupplierArchives(brcVo);
                msg.setSuccess(true);
                msg.setMsg("修改供应商档案成功！");
            } else{
                msg.setSuccess(false);
                msg.setMsg("对不起，您输入的供应商名称已存在，请重新输入！");
            }
        }catch(Exception e){
            msg.setMsg("对不起，您输入的供应商名称已存在，请重新输入！");
            logger.error("对不起，您输入的供应商名称已存在，请重新输入！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 保存保险(汽厂)档案
     */
    public void saveInsuranceCarArchives()
    {
        Msg msg = new Msg();
        try{
        	brcVo.setEnterpriseId(getNowEnterpriseId());
            boolean isno = basRelationCampanyService.isExist(brcVo, 1);
            if (isno){
                basRelationCampanyService.saveInsuranceCarArchives(brcVo);
                msg.setSuccess(true);
                msg.setMsg("保存保险(汽厂)档案成功！");
            }else{
                msg.setSuccess(false);
                msg.setMsg("对不起，您输入的名称已存在，请重新输入");
            }
        }catch(Exception e){
            msg.setMsg("保存保险(汽厂)档案失败！");
            logger.error("保存保险(汽厂)档案失败！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 删除保险(汽厂)档案
     */
    public void removeInsuranceCarArchives()
    {
        Msg msg = new Msg();
        try{
            basRelationCampanyService.remove(brcVo.getRelcampId());
            msg.setSuccess(true);
            msg.setMsg("删除保险(汽厂)档案成功！");
        }catch(Exception e){
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            logger.error("对不起，该数据已经被使用，不允许删除！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 修改保险(汽厂)档案
     */
    public void editInsuranceCarArchives(){
        Msg msg = new Msg();
        try{
        	brcVo.setEnterpriseId(getUserEnterpriseId());
            basRelationCampanyService.editInsuranceCarArchives(brcVo);
            msg.setSuccess(true);
            msg.setMsg("保存保险(汽厂)档案成功！");
        }
        catch(Exception e)
        {
            msg.setMsg("保存保险(汽厂)档案失败！");
            logger.error("保存保险(汽厂)档案失败！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 增加保险（汽厂）属性
     * */
    public void saveCarCompanyProperties(){
        Msg msg = new Msg();
        try{
        	brcVo.setEnterpriseId(getUserEnterpriseId());
        	if(basRelationCampanyService.findCarCompany(brcVo)){
				msg.setMsg("对不起，您输入的属性已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
            basRelationCampanyService.saveCarCompanyProperties(brcVo);
            msg.setMsg("增加保险（汽厂）属性成功！");
            msg.setSuccess(true);
        }catch(Exception e){
            logger.error("增加保险（汽厂）属性失败！", e);
            msg.setMsg("增加保险（汽厂）属性失败！");
        }
        super.writeJson(msg);
    }

    /**
     * 修改保险（汽厂）属性
     * */
    public void editCarCompanyProperties(){
        Msg msg = new Msg();
        try{
        	brcVo.setEnterpriseId(getUserEnterpriseId());
            basRelationCampanyService.editCarCompanyProperties(brcVo);
            msg.setMsg("修改保险（汽厂）属性成功！");
            msg.setSuccess(true);
        }catch(Exception e)
        {
            logger.error("修改保险（汽厂）属性失败！", e);
            msg.setMsg("修改保险（汽厂）属性失败！");
        }
        super.writeJson(msg);
    }

    /**
     * 删除保险（汽厂）属性
     * */
    public void removeCarCompanyProperties(){
        Msg msg = new Msg();
        try{
            basRelationCampanyService.removeCarCompanyProperties(brcVo);
            msg.setMsg("删除保险（汽厂）属性成功！");
            msg.setSuccess(true);
        }catch(Exception e){
            logger.error("删除保险（汽厂）属性失败！", e);
            msg.setMsg("删除保险（汽厂）属性失败！");
        }
        super.writeJson(msg);
    }

    /**
     * 查询保险（汽厂）属性
     * */
    public void findAllCarCompanyProperties(){
        try{
        	brcVo.setNowEnterpriseId(getNowEnterpriseId());
            super.writeJson(basRelationCampanyService
                    .findAllCarCompanyProperties(brcVo));
        }catch(Exception e){
            logger.error("查询保险（汽厂）属性失败！", e);
        }
    }

    
    public BasRelationCampanyVo getModel(){
        return brcVo;
    }
    
    public BasRelationCampanyService getBasRelationCampanyService(){
        return basRelationCampanyService;
    }

    @Autowired
    public void setBasRelationCampanyService(BasRelationCampanyService basRelationCampanyService){
        this.basRelationCampanyService = basRelationCampanyService;
    }

    public BaseService getBaseService(){
        return baseService;
    }

    @Autowired
    public void setBaseService(BaseService baseService){
        this.baseService = baseService;
    }
}