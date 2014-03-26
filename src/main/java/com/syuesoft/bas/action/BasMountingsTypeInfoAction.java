package com.syuesoft.bas.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasMountingsTypeInfoService;
import com.syuesoft.base.vo.BasMountingsTypeInfo;
import com.syuesoft.util.Msg;

/**
 * 配件型号资料Action
 * @author SUMING
 */
@Controller
@Scope("prototype")
public class BasMountingsTypeInfoAction extends BaseAction implements
        ModelDriven<BasMountingsTypeInfo>
{

    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(this.getClass());
    BasMountingsTypeInfo mti = new BasMountingsTypeInfo();
    @Autowired
    private BasMountingsTypeInfoService basMountingsTypeInfoService;

    /**
     * 带条件查询数据的方法
     * @return
     */
    public String findAll()
    {
        try{
        	mti.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(basMountingsTypeInfoService.findAll(mti));
        }
        catch(Exception e){
            logger.error("带条件查询数据的方法失败", e);
        }
        return null;
    }

    /**
     * 保存数据的方法
     * 
     * @return
     */
    public String save()
    {
        Msg msg = new Msg();
        try
        {
            boolean isno = basMountingsTypeInfoService.isExist(mti);
            mti.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            if (isno){
                msg.setSuccess(false);
                msg.setMsg("对不起，您输入的配件型号已存在，请重新输入！");
            }
            else{
                basMountingsTypeInfoService.save(mti);
                msg.setSuccess(true);
                msg.setMsg("配件型号添加成功！");
            }
        }
        catch(Exception e){
            msg.setSuccess(false);
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            logger.error("对不起，记录无法保存，请确认内容及格式是否正确", e);
        }
        super.writeJson(msg);
        return null;
    }

    /**
     * 查询配件品牌的方法
     * 
     * @return
     */
    public String findAllPartsBrand()
    {
        try
        {
        	mti.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basMountingsTypeInfoService.findAllPartsBrand(mti));
        }
        catch(Exception e)
        {
            logger.error("查询配件品牌的方法失败", e);
        }

        return null;
    }

    /**
     * 根据id删除数据的方法
     * @return
     */
    public void delete()
    {
        Msg msg = new Msg();
        try{
            basMountingsTypeInfoService.delete(mti.getPtypeId());
            msg.setSuccess(true);
            msg.setMsg("配件型号删除成功！");
        }
        catch(Exception e){
            e.printStackTrace();
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            logger.error("对不起，该数据已经被使用，不允许删除！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 修改(更新)数据的方法
     * 
     * @return
     */
    public void edit()
    {
        Msg msg = new Msg();
        try
        {
        	mti.setEnterpriseId(getNowEnterpriseId());
            boolean isno = basMountingsTypeInfoService.isExist(mti);
            if (isno)
            {
                msg.setSuccess(false);
                msg.setMsg("对不起，您输入的配件型号已存在，请重新输入！");
            }
            else
            {
                basMountingsTypeInfoService.update(mti);
                msg.setSuccess(true);
                msg.setMsg("配件型号修改成功！");
            }
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            logger.error("对不起，记录无法保存，请确认内容及格式是否正确！", e);
        }
        super.writeJson(msg);
    }

    
    public BasMountingsTypeInfo getModel()
    {
        return mti;
    }

}
