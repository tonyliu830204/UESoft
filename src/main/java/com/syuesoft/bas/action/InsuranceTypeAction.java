package com.syuesoft.bas.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.InsuranceTypeService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fbk.vo.InsuranceTypeVo;
import com.syuesoft.model.InsuranceType;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@Controller
@Scope("prototype")
public class InsuranceTypeAction extends BaseAction implements
        ModelDriven<InsuranceTypeVo>
{

    private static final long serialVersionUID = 1L;
    private InsuranceTypeVo insuranceTypeVo = new InsuranceTypeVo();
    private String order;
    private String sort;
   
    @Autowired
    private InsuranceTypeService insuranceTypeService;

    // 保单管理 保单表新增方法
    public String doAdd()
    {
        Message J = new Message();
        try
        {
        	insuranceTypeVo.setEnterpriseId(getUserEnterpriseId());
        	insuranceTypeVo.setNowEnterpriseId(getNowEnterpriseId());
            boolean bool = insuranceTypeService.add(insuranceTypeVo);
            if (bool)
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的保险险种已存在，请重新输入！");
            }
            else
            {
                J.setSuccess(true);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;
    }

    // 保单管理 保单表删除方法
    public String doDelete()
    {
        Message mg = new Message();
        try
        {
            insuranceTypeService.delete(insuranceTypeVo);
            mg.setSuccess(true);
        }
        catch(Exception e)
        {
            mg.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(mg);
        return null;
    }

    // 保单管理 保单表修改方法
    public String doUpdate()
    {
        Message J = new Message();
        try
        {
        	insuranceTypeVo.setEnterpriseId(getUserEnterpriseId());
        	insuranceTypeVo.setNowEnterpriseId(getNowEnterpriseId());
            boolean bool = insuranceTypeService.update(insuranceTypeVo);
            if (bool)
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的保险险种已存在，请重新输入！");
            }
            else
            {
                J.setSuccess(true);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;
    }

    // 保单管理 保单表查询方法
    public String doFind()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List list = new ArrayList();
        Json json = new Json();
        try
        {
            List<InsuranceType> inlist = insuranceTypeService.findAll(
                    insuranceTypeVo.getPage(), insuranceTypeVo.getRows(), sort,
                    order,getNowEnterpriseId());
            for (InsuranceType insuranceType : inlist)
            {
                InsuranceTypeVo vo = new InsuranceTypeVo();
                vo.setId(insuranceType.getId().toString());
                vo.setIncount(insuranceType.getIncount());
                vo.setInfee(insuranceType.getInfee());
                vo.setInfeelv(insuranceType.getInfeelv());
                vo.setIntype(insuranceType.getIntype());
                vo.setMemo(insuranceType.getMemo());
                list.add(vo);
            }
            json.setRows(list);
            json.setTotal(Integer.parseInt(session.getAttribute("size")
                    .toString()));
            super.writeJson(json);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    
    public InsuranceTypeVo getModel()
    {
        return insuranceTypeVo;
    }
    
    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }
    
    public InsuranceTypeService getInsuranceTypeService()
    {
        return insuranceTypeService;
    }

    public void setInsuranceTypeService(
            InsuranceTypeService insuranceTypeService)
    {
        this.insuranceTypeService = insuranceTypeService;
    }

}
