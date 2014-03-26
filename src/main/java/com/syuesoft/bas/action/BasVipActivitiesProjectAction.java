package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipActivitiesProjectService;
import com.syuesoft.base.vo.BasVipActivitiesProjectVO;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@ParentPackage(value = "basePackage")
@Action("BasVipActivitiesProjectAction")
public class BasVipActivitiesProjectAction extends BaseAction implements
        ModelDriven<BasVipActivitiesProjectVO>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    @Autowired
    private BasVipActivitiesProjectService basVipActivitiesProjectService;
    private BasVipActivitiesProjectVO basVipActivitiesProjectVO = new BasVipActivitiesProjectVO();
    private int page;
    private int rows;
    private String order;
    private String sort;

    /**
     * 添加会员活动项目
     * @throws Exception
     * */
    public void add()
    {
        Message J = new Message();
        try
        {
        	basVipActivitiesProjectVO.setEnterpriseId(getUserEnterpriseId());
            // 根据会员活动项目名称判断会员活动项目是否存在
            if (basVipActivitiesProjectService.getByName(basVipActivitiesProjectVO))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的会员活动项目已存在，请重新输入！");
            }
            else
            {
                basVipActivitiesProjectService.add(basVipActivitiesProjectVO);
                J.setSuccess(true);
                J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
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
     * @throws Exception
     *             删除会员活动折扣
     * */
    public void delete()
    {
        Message J = new Message();
        try
        {
            basVipActivitiesProjectService.delete(basVipActivitiesProjectVO);
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
     * @throws Exception
     *             编辑会员活动折扣
     * */
    public void update()
    {
        Message J = new Message();
        try
        {
        	basVipActivitiesProjectVO.setEnterpriseId(getUserEnterpriseId());
            // 用会员活动项目名称判断会员活动项目是否存在
            if (basVipActivitiesProjectService.getByName(basVipActivitiesProjectVO))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的会员活动名称已存在，请重新输入！");
            }
            else
            {
                basVipActivitiesProjectService.update(basVipActivitiesProjectVO);
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
     * @throws Exception
     *             分页查询所有会员活动折扣
     * */
    public void findAll()
    {
        Json json = new Json();
        try
        {
            List<BasVipActivitiesProjectVO> list = basVipActivitiesProjectService.findAll(page, rows, order, sort,getNowEnterpriseId());
            int totleRow = basVipActivitiesProjectService.getTotle(getNowEnterpriseId());
            json.setTotal(totleRow);
            json.setRows(list);
            super.writeJson(json, true);
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
    
    
    public BasVipActivitiesProjectVO getModel()
    {
        return basVipActivitiesProjectVO;
    }

    public BasVipActivitiesProjectVO getBasVipActivitiesProjectVO()
    {
        return basVipActivitiesProjectVO;
    }

    public void setBasVipActivitiesProjectVO(
            BasVipActivitiesProjectVO basVipActivitiesProjectVO)
    {
        this.basVipActivitiesProjectVO = basVipActivitiesProjectVO;
    }

    public int getPage()
    {
        return page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }
    
    public void setBasVipActivitiesProjectService(
            BasVipActivitiesProjectService basVipActivitiesProjectService)
    {
        this.basVipActivitiesProjectService = basVipActivitiesProjectService;
    }


}
