package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipLevelService;
import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@ParentPackage(value = "basePackage")
@Action("BasVipLevelAction")
public class BasVipLevelAction extends BaseAction implements
        ModelDriven<BasVipLevelVO>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private BasVipLevelVO bvlVO = new BasVipLevelVO();
    @Autowired
    private BasVipLevelService basVipLevelService;
    private int page;
    private int rows;
    private String order;
    private String sort;
    
    /**
     * 添加会员等级
     */
    public void add()
    {
        Message J = new Message();
        try
        {
        	bvlVO.setEnterpriseId(getUserEnterpriseId());
            if (basVipLevelService.getByName(bvlVO))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的会员等级已存在，请重新输入！");
            }
            else
            {
                basVipLevelService.add(bvlVO);
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
     * 删除会员等级
     * */
    public void delete()
    {
        Message J = new Message();
        try
        {
            basVipLevelService.delete(bvlVO);
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
     * 编辑会员等级
     * */
    public void update()
    {
        Message J = new Message();
        try
        {
        	bvlVO.setEnterpriseId(getUserEnterpriseId());
            if (basVipLevelService.getByName(bvlVO)){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的会员等级已存在，请重新输入！");
            }else{
                basVipLevelService.update(bvlVO);
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
     * 分页查询所有会员等级
     * */
    public void findAll()
    {
        Json json = new Json();
        try
        {
            List<BasVipLevelVO> list = basVipLevelService.findAll(page, rows,
                    order, sort,getNowEnterpriseId());
            int totleRow = basVipLevelService.getTotle(getNowEnterpriseId());
            json.setRows(list);
            json.setTotal(totleRow);
            super.writeJson(json);
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

    public BasVipLevelVO getBvlVO()
    {
        return bvlVO;
    }

    public void setBvlVO(BasVipLevelVO bvlVO)
    {
        this.bvlVO = bvlVO;
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
    
    
    public BasVipLevelVO getModel()
    {
        return bvlVO;
    }
}
