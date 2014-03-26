package com.syuesoft.bas.action;

import java.util.Date;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.apache.struts2.convention.annotation.ParentPackage;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.DataWordBookService;
import com.syuesoft.base.vo.BarChildrenAndParentVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Msg;
import com.syuesoft.util.MyBeanUtils;

/**
 * 数据字典Action
 * 
 */
@Scope("prototype")
@ParentPackage(value = "basePackage")
@Action("dataWordBookAction")
public class DataWordBookAction extends BaseAction implements
        ModelDriven<BarChildrenAndParentVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    private Logger logger = Logger.getLogger(this.getClass());

    BarChildrenAndParentVo bcapVo = new BarChildrenAndParentVo();

    @Autowired
    private DataWordBookService dataWordBookService;

    /**
     * 查询子级数据
     * */
    public void datagridC()
    {
        try
        {
            super.writeJson(dataWordBookService.datagridC(bcapVo));
        }
        catch(Exception e)
        {
            logger.error("查询子级数据失败！", e);
        }
    }

    /**
     * 查询父级数据
     * */
    public void datagridP()
    {
        try
        {
            super.writeJson(dataWordBookService.datagridP(bcapVo));
        }
        catch(Exception e)
        {
            logger.error("查询父级数据失败！", e);
        }
    }

    /**
     * 更新子级数据
     * */
    public void editC()
    {
        Msg msg = new Msg();
        try
        {
            Boolean flag = dataWordBookService.isExistsKeyC(bcapVo);
            if (flag == true)
            {
                msg.setMsg("此键值已存在！请重新输入！");
            }
            else
            {
                bcapVo.setStfId(Short.parseShort(((BasUsers) this.getSession()
                        .getAttribute(Contstants.CUSTOMER)).getBasStuff()
                        .getStfId().toString()));
                bcapVo.setCreateTime(MyBeanUtils.getInstance().getString(
                        new Date()));
                dataWordBookService.editC(bcapVo);
                msg.setSuccess(true);
                msg.setMsg("更新子级数据成功！");
            }
        }
        catch(Exception e)
        {
            logger.error("更新子级数据失败！", e);
            msg.setMsg("更新子级数据失败！");
        } finally
        {
            super.writeJson(msg);
        }
    }

    /**
     * 更新父级数据
     * */
    public void editP()
    {
        Msg msg = new Msg();
        try
        {
            bcapVo.setStfId(Short.parseShort(((BasUsers) this.getSession()
                    .getAttribute(Contstants.CUSTOMER)).getBasStuff()
                    .getStfId().toString()));
            bcapVo.setCreateTime(MyBeanUtils.getInstance().getString(
                    new Date()));
            dataWordBookService.editP(bcapVo);
            msg.setSuccess(true);
            msg.setMsg("更新父级数据成功！");
        }
        catch(Exception e)
        {
            logger.error("更新父级数据失败！", e);
            msg.setMsg("更新父级数据失败！");
        } finally
        {
            super.writeJson(msg);
        }
    }

    /**
     * 删除子级数据
     * */
    public void removeC()
    {
        Msg msg = new Msg();
        try
        {
            dataWordBookService.removeC(bcapVo);
            msg.setSuccess(true);
            msg.setMsg("删除子级数据成功！");
        }
        catch(Exception e)
        {
            logger.error("删除子级数据失败！", e);
            msg.setMsg("删除子级数据失败！");
        } finally
        {
            super.writeJson(msg);
        }
    }

    /**
     * 删除父级数据
     * */
    public void removeP()
    {
        Msg msg = new Msg();
        try
        {
            Boolean flag = dataWordBookService.isExists(bcapVo);
            if (flag == true)
            {
                msg.setMsg("含有子级数据，请先删除子级数据！");
            }
            else
            {
                dataWordBookService.removeP(bcapVo);
                msg.setSuccess(true);
                msg.setMsg("删除父级数据成功！");
            }
        }
        catch(Exception e)
        {
            logger.error("删除父级数据失败！", e);
            msg.setMsg("删除父级数据失败！");
        } finally
        {
            super.writeJson(msg);
        }
    }

    /**
     * 增加子级数据
     * */
    public void saveC()
    {
        Msg msg = new Msg();
        try
        {
            Boolean flag = dataWordBookService.isExistsKeyC(bcapVo);
            if (flag == true)
            {
                msg.setMsg("此键值已存在！请重新输入！");
            }
            else
            {
                bcapVo.setStfId(Short.parseShort(((BasUsers) this.getSession()
                        .getAttribute(Contstants.CUSTOMER)).getBasStuff()
                        .getStfId().toString()));
                bcapVo.setCreateTime(MyBeanUtils.getInstance().getString(
                        new Date()));
                dataWordBookService.saveC(bcapVo);
                msg.setSuccess(true);
                msg.setMsg("增加子级数据成功！");
            }
        }
        catch(Exception e)
        {
            logger.error("增加子级数据失败！", e);
            msg.setMsg("增加子级数据失败！");
        } finally
        {
            super.writeJson(msg);
        }
    }

    /**
     * 增加父级数据
     * */
    public void saveP()
    {
        Msg msg = new Msg();
        try
        {
            Boolean flag = dataWordBookService.isExistsKeyP(bcapVo);
            if (flag == true)
            {
                msg.setMsg("此键值已存在！请重新输入！");
            }
            else
            {
                bcapVo.setStfId(Short.parseShort(((BasUsers) this.getSession()
                        .getAttribute(Contstants.CUSTOMER)).getBasStuff()
                        .getStfId().toString()));
                bcapVo.setCreateTime(MyBeanUtils.getInstance().getString(
                        new Date()));
                dataWordBookService.saveP(bcapVo);
                msg.setSuccess(true);
                msg.setMsg("增加父级数据成功！");
            }
        }
        catch(Exception e)
        {
            logger.error("增加父级数据失败！", e);
            msg.setMsg("增加父级数据失败！");
        } finally
        {
            super.writeJson(msg);
        }
    }

    /**
     * 判断子级Key是否重复
     * */
    public void isExistsKeyC()
    {
        Msg msg = new Msg();
        try
        {
            Boolean flag = dataWordBookService.isExistsKeyC(bcapVo);
            if (flag == true)
            {
                msg.setMsg("此键值已存在！请重新输入！");
            }
            else
            {
                msg.setMsg("此键值不存在，可使用！");
                msg.setSuccess(true);
            }
        }
        catch(Exception e)
        {
            logger.error("判断子级Key是否重复失败！", e);
        } finally
        {
            super.writeJson(msg);
        }
    }

    /**
     * 判断父级Key是否重复
     * */
    public void isExistsKeyP()
    {
        Msg msg = new Msg();
        try
        {
            Boolean flag = dataWordBookService.isExistsKeyP(bcapVo);
            if (flag == true)
            {
                msg.setMsg("此键值已存在！请重新输入！");
            }
            else
            {
                msg.setMsg("此键值不存在，可使用！");
                msg.setSuccess(true);
            }
        }
        catch(Exception e)
        {
            logger.error("判断父级Key是否重复失败！", e);
        } finally
        {
            super.writeJson(msg);
        }
    }

    
    public BarChildrenAndParentVo getModel()
    {
        return bcapVo;
    }
}