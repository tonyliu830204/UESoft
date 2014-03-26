package com.syuesoft.frt.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.service.FrtPartsService;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.util.Json;

/**
 * 前台配件查询action
 *
 */
import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value = "basePackage")
@Action("frtPartsQueryAction")
public class FrtPartsQueryAction extends BaseAction implements
        ModelDriven<FrtPartsVo>
{
    private final Logger logger = Logger.getLogger(this.getClass());

    FrtPartsVo fpVo = new FrtPartsVo();

    private FrtPartsService frtPartsService;

    public FrtPartsService getFrtPartsService()
    {
        return frtPartsService;
    }

    @Autowired
    public void setFrtPartsService(FrtPartsService frtPartsService)
    {
        this.frtPartsService = frtPartsService;
    }

    /**
     * 前台配件查询
     */
    public void datagridFrtParts()
    {
        try
        {
        	fpVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtPartsService.datagridFrtParts(fpVo));
        }
        catch(Exception e)
        {
            logger.error("前台配件查询失败", e);
        }
    }

    /**
     * 查询未选配件
     * */
    public void findAllParts()
    {
        try
        {
        	fpVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtPartsService.findAllParts(fpVo));
        }
        catch(Exception e)
        {
            logger.error("查询未选配件失败！", e);
        }
    }

    
    public FrtPartsVo getModel()
    {
        return fpVo;
    }

}
