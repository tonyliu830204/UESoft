package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipGiveIntegralProjectService;
import com.syuesoft.base.vo.BasVipGiveIntegralProjectVO;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

/**
 * @author mulangtao
 * */
@ParentPackage(value = "basePackage")
@Action("BasVipGiveIntegralProjectAction")
public class BasVipGiveIntegralProjectAction extends BaseAction implements
        ModelDriven<BasVipGiveIntegralProjectVO>
{

    private static final long serialVersionUID = 1L;

    @Autowired
    private BasVipGiveIntegralProjectService basVipGiveIntegralProjectService;
    // 实例化赠送积分VO
    private BasVipGiveIntegralProjectVO bgpVo = new BasVipGiveIntegralProjectVO();
    private int page;
    private int rows;
    private String order;
    private String sort;
    
    /**
     * 添加赠送积分项目
     * */
    public void add()
    {
        Message J = new Message();
        try
        {
        	bgpVo.setEnterpriseId(getUserEnterpriseId());
            if (basVipGiveIntegralProjectService.getByName(bgpVo))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的积分赠送项目已存在，请重新输入！");
            }
            else
            {
                basVipGiveIntegralProjectService.add(bgpVo);
                J.setSuccess(true);
            }
        }
        catch(Exception e)
        {
            J.setSuccess(false);
            J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
        }
        super.writeJson(J);
    }

    /**
     * 删除赠送积分项目
     * */
    public void delete()
    {
        Message J = new Message();
        try
        {
            basVipGiveIntegralProjectService.delete(bgpVo);
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
     * 编辑赠送积分项目
     * */
    public void update()
    {
        Message J = new Message();
        try
        {
        	bgpVo.setEnterpriseId(getUserEnterpriseId());
            if (basVipGiveIntegralProjectService.getByName(bgpVo))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的积分赠送项目已存在，请重新输入！");
            }
            else
            {
                basVipGiveIntegralProjectService.update(bgpVo);
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
     * 分页查询所有赠送积分项目
     * */
    public void findAll()
    {
        Json json = new Json();
        try
        {
            List<BasVipGiveIntegralProjectVO> list = basVipGiveIntegralProjectService
                    .findAll(page, rows, order, sort,getNowEnterpriseId());
            int totalRow = basVipGiveIntegralProjectService.getTotle(getNowEnterpriseId() );
            json.setRows(list);
            json.setTotal(totalRow);
            super.writeJson(json);
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

    public BasVipGiveIntegralProjectVO getBgpVo()
    {
        return bgpVo;
    }

    public void setBgpVo(BasVipGiveIntegralProjectVO bgpVo)
    {
        this.bgpVo = bgpVo;
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

    
    public BasVipGiveIntegralProjectVO getModel()
    {
        return bgpVo;
    }
}
