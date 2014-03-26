package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipGruopService;
import com.syuesoft.base.vo.BasVipGruopVO;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@ParentPackage(value = "basePackage")
@Action("BasVipGruopAction")
public class BasVipGruopAction extends BaseAction implements
        ModelDriven<BasVipGruopVO>
{

    private static final long serialVersionUID = 1L;
    @Autowired
    private BasVipGruopService basVipGruopService;
    private BasVipGruopVO basVipGruopVO = new BasVipGruopVO();
    private int page;
    private int rows;
    private String order;
    private String sort;

    /**
     * 添加会员分组
     */
    public void add()
    {
        Message J = new Message();
        try
        {
        	basVipGruopVO.setEnterpriseId(getUserEnterpriseId());
            if (basVipGruopService.getByName(basVipGruopVO))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的会员分组已存在，请重新输入！");
            }
            else
            {
                basVipGruopService.add(basVipGruopVO);
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
     * 删除 会员分组
     * */
    public void delete()
    {
        Message J = new Message();
        try
        {
            basVipGruopService.delete(basVipGruopVO);
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
     * 编辑会员分组
     * */
    public void update()
    {
        Message J = new Message();
        try
        {
        	basVipGruopVO.setEnterpriseId(getUserEnterpriseId());
            if (basVipGruopService.getByName(basVipGruopVO)){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的会员分组已存在，请重新输入！");
            }else{
                basVipGruopService.update(basVipGruopVO);
                J.setSuccess(true);
            }
        }catch(Exception e){
            J.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    /**
     * 分页获取所有会员分组
     * */
    public void findAll()
    {
        Json json = new Json();
        try
        {
            List<BasVipGruopVO> list = basVipGruopService.findAll(page, rows,
                    order, sort,getNowEnterpriseId());
            int totleRows = basVipGruopService.getTotle(getNowEnterpriseId());
            json.setRows(list);
            json.setTotal(totleRows);
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

    public BasVipGruopVO getBasVipGruopVO()
    {
        return basVipGruopVO;
    }

    public void setBasVipGruopVO(BasVipGruopVO basVipGruopVO)
    {
        this.basVipGruopVO = basVipGruopVO;
    }

    
    public BasVipGruopVO getModel()
    {
        return basVipGruopVO;
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
