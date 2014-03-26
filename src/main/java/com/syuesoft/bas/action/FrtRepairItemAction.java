package com.syuesoft.bas.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.FrtRepairItemService;
import com.syuesoft.base.vo.FrtRepairItemVo;
import com.syuesoft.contstants.Contstants;

@ParentPackage(value = "basePackage")
@Action("frtRepairItemAction")
public class FrtRepairItemAction extends BaseAction implements
        ModelDriven<FrtRepairItemVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger
            .getLogger(FrtRepairItemAction.class);

    @Autowired
    private FrtRepairItemService frtRepairItemService;

    private FrtRepairItemVo frtRepairItemVo = new FrtRepairItemVo();

    /**
     * 查询标准项目工时
     * */
    public void findAll()
    {
        try
        {
        	frtRepairItemVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(frtRepairItemService.findAll(frtRepairItemVo));
        }
        catch(Exception e)
        {
            logger.error("查询标准项目工时失败！", e);
        }
    }

    /**
     * 增加标准项目工时
     * */
    public void save()
    {
        try
        {
        	frtRepairItemVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(frtRepairItemService.save(frtRepairItemVo));
        }
        catch(Exception e)
        {
            logger.error("增加标准项目工时失败！", e);
        }
    }

    /**
     * 更新标准项目工时
     * */
    public void update()
    {
        try
        {
        	frtRepairItemVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtRepairItemService.update(frtRepairItemVo));
        }
        catch(Exception e)
        {
            logger.error("更新标准项目工时失败！", e);
        }
    }

    /**
     * 删除标准项目工时
     * */
    public void delete()
    {
        try
        {
            super.writeJson(frtRepairItemService.delete(frtRepairItemVo));
        }
        catch(Exception e)
        {
            logger.error("删除标准项目工时失败！", e);
        }
    }

    
    public FrtRepairItemVo getModel()
    {
        return frtRepairItemVo;
    }

    // 获取工时分类用于combox
    public String getBasWorkhourSort()
    {
        try{
        	frtRepairItemVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtRepairItemService.getBasWorkhourSort(frtRepairItemVo.getQ(),frtRepairItemVo.getEnterpriseId()));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
