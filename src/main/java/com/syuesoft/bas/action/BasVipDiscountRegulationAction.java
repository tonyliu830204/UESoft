package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipDiscountRegulationService;
import com.syuesoft.base.vo.BasVipDiscountRegulationVO;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

/**
 * @author mulangtao
 * */
@ParentPackage(value = "basePackage")
@Action("BasVipDiscountRegulationAction")
public class BasVipDiscountRegulationAction extends BaseAction implements
        ModelDriven<BasVipDiscountRegulationVO>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    @Autowired
    private BasVipDiscountRegulationService basVipDiscountRegulationService;
    // 实例化会员折扣规则VO
    private BasVipDiscountRegulationVO basVipDiscountRegulationVO = new BasVipDiscountRegulationVO();
    private int page;
    private int rows;
    private String order;
    private String sort;

    /**
     * 添加会员折扣规则
     * */
    public void add()
    {
        Message J = new Message();
        try
        {
        	basVipDiscountRegulationVO.setEnterpriseId(getUserEnterpriseId());
            // 根据会员等级和维修分类查询会员折扣规则是否存在
            if (basVipDiscountRegulationService.getByLevelAndReptype(basVipDiscountRegulationVO))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的会员折扣规则已存在，请重新输入！");
            }
            else
            {
                basVipDiscountRegulationService.add(basVipDiscountRegulationVO);
                J.setSuccess(true);
            }
        }
        catch(Exception e)
        {
            J.setSuccess(false);
            J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    /**
     * 删除会员折扣规则
     * */
    public void delete()
    {
        Message J = new Message();
        try
        {
            basVipDiscountRegulationService.delete(basVipDiscountRegulationVO);
            J.setSuccess(true);
        }
        catch(Exception e)
        {
            J.setSuccess(false);
            J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    /**
     * 编辑会员折扣规则
     * */
    public void update()
    {
        Message J = new Message();
        try
        {
        	basVipDiscountRegulationVO.setEnterpriseId(getUserEnterpriseId());
            // 根据会员等级和维修分类判断会员折扣规则是否存在
            if (basVipDiscountRegulationService
                    .getByLevelAndReptype(basVipDiscountRegulationVO))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的会员折扣规则已存在，请重新输入！");
            }
            else
            {
                basVipDiscountRegulationService
                        .update(basVipDiscountRegulationVO);
                J.setSuccess(true);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            J.setSuccess(true);
        }
        super.writeJson(J);
    }

    /**
     * 分页查询会员折扣规则
     * */
    public void findAll()
    {
        Json json = new Json();
        try
        {
            List<BasVipDiscountRegulationVO> list = basVipDiscountRegulationService
                    .findAll(page, rows, order, sort,getNowEnterpriseId());
            int totleRows = basVipDiscountRegulationService.getTotle(getNowEnterpriseId());
            json.setRows(list);
            json.setTotal(totleRows);
            super.writeJson(json);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    public BasVipDiscountRegulationVO getModel()
    {
        return basVipDiscountRegulationVO;
    }

    /**
     * 查询所有会员等级
     * */
    public void findVipLevel()
    {
        try
        {
        	basVipDiscountRegulationVO.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basVipDiscountRegulationService
                    .findAllVipLevel(basVipDiscountRegulationVO));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有维修分类
     * */
    @SuppressWarnings("unchecked")
	public void findAllRepairType()
    {
        try
        {
            List list = basVipDiscountRegulationService
                    .findAllRepairType(basVipDiscountRegulationVO);
            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }
}
