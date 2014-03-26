package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipGiveProjectService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasVipGiveProject;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

/**
 * @author mulangtao
 */
@SuppressWarnings("serial")
@ParentPackage(value = "basePackage")
@Action("BasVipGiveProjectAction")
public class BasVipGiveProjectAction extends BaseAction implements
        ModelDriven<BasVipGiveProject>
{
    @Autowired
    private BasVipGiveProjectService basVipGiveProjectService;
    // 实例化会员赠送项目
    private BasVipGiveProject basVipGiveProject = new BasVipGiveProject();
    private int page; // 当前页
    private int rows; // 每页显示记录数
    private String order;// 排序规则
    private String sort; // 排序字段

    /**
     * 添加 会员赠送项目
     * */
    public void add()
    {
        Message J = new Message();
        try
        {
        	basVipGiveProject.setEnterpriseId(getUserEnterpriseId());
            if (basVipGiveProjectService.getByName(basVipGiveProject))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的会员赠送项目已存在，请重新输入！");
            }
            else
            {
                basVipGiveProjectService.add(basVipGiveProject);
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
     * 删除 会员赠送项目
     * */
    public void delete()
    {
        Message J = new Message();
        try
        {
            basVipGiveProjectService.delete(basVipGiveProject);
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
     * 编辑会员赠送项目
     * */
    public void update()
    {
        Message J = new Message();
        try
        {
        	basVipGiveProject.setEnterpriseId(getUserEnterpriseId());
            if (basVipGiveProjectService.getByName(basVipGiveProject))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的会员赠送项目已存在，请重新输入！");
            }
            else
            {
                basVipGiveProjectService.update(basVipGiveProject);
                J.setSuccess(true);
            }
        }
        catch(Exception e)
        {
            J.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    /**
     * 分页查询所有会员赠送项目
     * */
    public void findAll()
    {
        Json json = new Json();
        try
        {
            List<BasVipGiveProject> list = basVipGiveProjectService.findAll(
                    page, rows, order, sort,getNowEnterpriseId());
            int total = basVipGiveProjectService.getTotle(getNowEnterpriseId());
            json.setRows(list);
            json.setTotal(total);
            super.writeJson(json);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public BasVipGiveProject getBasVipGiveProject()
    {
        return basVipGiveProject;
    }

    public void setBasVipGiveProject(BasVipGiveProject basVipGiveProject)
    {
        this.basVipGiveProject = basVipGiveProject;
    }

    
    public BasVipGiveProject getModel()
    {
        return basVipGiveProject;
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
}
