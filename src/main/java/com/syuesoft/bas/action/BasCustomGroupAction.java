package com.syuesoft.bas.action;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCustomGroupService;
import com.syuesoft.base.vo.BasCustomGroupVo;
import com.syuesoft.model.BasCustomGroup;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@Controller
@Scope("prototype")
public class BasCustomGroupAction extends BaseAction implements
        ModelDriven<BasCustomGroupVo>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private int page;
    private int rows;
    private String order;
    private String sort;
    private BasCustomGroupVo basCustomGroupVo = new BasCustomGroupVo();
    @Autowired
    private BasCustomGroupService basCustomGroupService = null;

    // 新增方法
    public void doAdd()
    {
        Message J = new Message();
        BasCustomGroup b = new BasCustomGroup();
        b.setCstgName(basCustomGroupVo.getCstgName());
        b.setMemo(basCustomGroupVo.getMemo());
        b.setEnterpriseId(getUserEnterpriseId());
        try{
            boolean bool = basCustomGroupService.add(b);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的客户分类已存在，请重新输入！");
            }else{
                J.setSuccess(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    // 删除方法
    public void doDelete()
    {
        Message J = new Message();
        BasCustomGroup b = new BasCustomGroup();
        b.setCstgId(Short.parseShort(basCustomGroupVo.getCstgId()));
        try{
            basCustomGroupService.delete(b);
            J.setSuccess(true);
            J.setMsg("删除成功!");
        }catch(Exception e){
            J.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    // 修改方法
    public void doUpdate()
    {
        Message J = new Message();
        BasCustomGroup b = new BasCustomGroup();
        b.setCstgId(Short.parseShort(basCustomGroupVo.getCstgId()));
        b.setCstgName(basCustomGroupVo.getCstgName());
        b.setMemo(basCustomGroupVo.getMemo());
        b.setEnterpriseId(getUserEnterpriseId());
        try{
            boolean bool = basCustomGroupService.update(b);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的客户分类已存在，请重新输入！");
            }else{
                J.setSuccess(true);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    // 查询方法
    public void doFindAll()
    {
        try{
        	basCustomGroupVo.setEnterpriseId(getNowEnterpriseId());
            List<BasCustomGroup> list = basCustomGroupService.findAll(page,
                    rows, sort, order,getNowEnterpriseId());
            List<BasCustomGroup> listtotle = basCustomGroupService.getTotle(basCustomGroupVo);
            List<BasCustomGroupVo> list2 = new ArrayList<BasCustomGroupVo>();
            Json json = new Json();
            json.setTotal(listtotle.size());
            for (BasCustomGroup bGroup : list){
                BasCustomGroupVo bgvo = new BasCustomGroupVo();
                bgvo.setCstgId(bGroup.getCstgId().toString());
                bgvo.setCstgName(bGroup.getCstgName());
                bgvo.setMemo(bGroup.getMemo());
                list2.add(bgvo);
            }
            json.setRows(list2);
            super.writeJson(json);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
    public BasCustomGroupVo getModel()
    {
        return basCustomGroupVo;
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

    public BasCustomGroupService getbasCustomGroupService()
    {
        return basCustomGroupService;
    }

    public void setbasCustomGroupService(
            BasCustomGroupService basCustomGroupService)
    {
        this.basCustomGroupService = basCustomGroupService;
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
