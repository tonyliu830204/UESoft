package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipResentService;
import com.syuesoft.base.vo.BasVipResentVO;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import org.apache.struts2.convention.annotation.ParentPackage;

@SuppressWarnings("serial")
@ParentPackage(value = "basePackage")
@Action("BasVipResentAction")
public class BasVipResentAction extends BaseAction implements
        ModelDriven<BasVipResentVO>
{
    @Autowired
    private BasVipResentService basVipResentService;
    private BasVipResentVO bvpVO = new BasVipResentVO();
    private int pgae;
    private int rows;
    private String order;
    private String sort;

    /**
     * 添加礼品
     * */
    public void add()
    {
        Message J = new Message();
        try
        {
            if (basVipResentService.getByName(bvpVO.getPstName()))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的礼品名称已存在，请重新输入！");
            }
            else
            {
                basVipResentService.add(bvpVO);
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
     * 删除礼品
     * */
    public void delete()
    {
        Message J = new Message();
        try
        {
            basVipResentService.delete(bvpVO);
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
     * 编辑礼品
     * */
    public void update()
    {
        Message J = new Message();
        try
        {
            if (basVipResentService.getByName(bvpVO.getPstName()))
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的礼品名称已存在，请重新输入！");
            }
            else
            {
                basVipResentService.update(bvpVO);
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
     * 分页查询所有礼品
     * */
    public void findAll()
    {
        Json json = new Json();
        try
        {
            List<BasVipResentVO> list = basVipResentService.findAll(pgae, rows,
                    order, sort);
            int totleRows = basVipResentService.getTotle();
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

    public BasVipResentVO getBvpVO()
    {
        return bvpVO;
    }

    public void setBvpVO(BasVipResentVO bvpVO)
    {
        this.bvpVO = bvpVO;
    }

    public int getPgae()
    {
        return pgae;
    }

    public void setPgae(int pgae)
    {
        this.pgae = pgae;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    
    public BasVipResentVO getModel()
    {
        return bvpVO;
    }
}
