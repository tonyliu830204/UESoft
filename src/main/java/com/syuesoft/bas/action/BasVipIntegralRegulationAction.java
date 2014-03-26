package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipIntegralRegulationService;
import com.syuesoft.base.vo.BasVipIntegralRegulationVO;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

/**
 * @author mulangtao
 * **/
@ParentPackage(value = "basePackage")
@Action("BasVipIntegralRegulationAction")
public class BasVipIntegralRegulationAction extends BaseAction implements
        ModelDriven<BasVipIntegralRegulationVO>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    @Autowired
    private BasVipIntegralRegulationService basVipIntegralRegulationService;
    private BasVipIntegralRegulationVO bvrVO = new BasVipIntegralRegulationVO();
    private int page; // 当前页
    private int rows; // 每页显示记录数
    private String order; // 排序类型
    private String sort; // 排序字段
    
    /**
     * 新增会员积分规则
     * */
    public void add()
    {
        Message J = new Message();
        try
        {
        	bvrVO.setEnterpriseId(getUserEnterpriseId());
            if (basVipIntegralRegulationService.getByLevelAndReptye(bvrVO))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的积分规则已经存在，请重新输入！");
            }
            else
            {
                basVipIntegralRegulationService.add(bvrVO);
                J.setSuccess(true);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            J.setSuccess(false);
            J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
        }
        super.writeJson(J);
    }

    /**
     * 删除会员积分规则
     * */
    public void delete()
    {
        Message J = new Message();
        try
        {
            basVipIntegralRegulationService.delete(bvrVO);
            J.setSuccess(true);
        }
        catch(Exception e)
        {
            J.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    /**
     * 编辑会员积分规则
     * */
    public void update()
    {
        Message J = new Message();
        try
        {
        	bvrVO.setEnterpriseId(getUserEnterpriseId());
            if (basVipIntegralRegulationService.getByLevelAndReptye(bvrVO))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的积分规则已经存在，请重新输入！");
            }
            else
            {
                basVipIntegralRegulationService.update(bvrVO);
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
     * 分页查询会员积分规则
     * */
    @SuppressWarnings("unchecked")
	public void findAll()
    {
        Json json = new Json();
        try
        {
            List list = basVipIntegralRegulationService.findAll(page, rows,
                    order, sort,getNowEnterpriseId());
            int totleRows = basVipIntegralRegulationService.getTotle(getNowEnterpriseId());
            json.setRows(list);
            json.setTotal(totleRows);
            super.writeJson(json);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有会员等级
     * */
    public void findAllVIPLevel()
    {
        try{
        	bvrVO.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basVipIntegralRegulationService.findAllVipLevel(bvrVO));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有维修分类
     * */
    public void findAllRepairType()
    {
        try{
        	bvrVO.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basVipIntegralRegulationService.findAllRepairType(bvrVO));
        }catch(Exception e){
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

    public BasVipIntegralRegulationVO getBvrVO()
    {
        return bvrVO;
    }

    public void setBvrVO(BasVipIntegralRegulationVO bvrVO)
    {
        this.bvrVO = bvrVO;
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

    
    public BasVipIntegralRegulationVO getModel()
    {
        return bvrVO;
    }
}
