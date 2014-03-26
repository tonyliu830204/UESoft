package com.syuesoft.bas.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCarTypeInfoService;
import com.syuesoft.base.vo.BasCarTypeInfoVo;
import com.syuesoft.util.Msg;

/**
 * 车辆型号资料action
 * @author SuMing
 */
@Controller
@Scope("prototype")
public class BasCarTypeInfoAction extends BaseAction implements
        ModelDriven<BasCarTypeInfoVo>
{
    private static final long serialVersionUID = 1L;

    private final Logger logger = Logger.getLogger(this.getClass());
    BasCarTypeInfoVo bctiVo = new BasCarTypeInfoVo();
    @Autowired
    private BasCarTypeInfoService basCarTypeInfoService;

    /**
     * 获取具体车辆品牌信息
     * */
    public void getCarTypeInfo()
    {
        try{
        	bctiVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(basCarTypeInfoService.getCarTypeInfo(bctiVo));
        }catch(Exception e){
            logger.error("获取具体车辆品牌信息失败！", e);
        }
    }

    /**
     * 查找所有记录的方法
     * 
     * @return
     */
    public void findAll()
    {
        try{
        	bctiVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(basCarTypeInfoService.findAll(bctiVo));
        }catch(Exception e){
            logger.error("查找所有记录的方法失败", e);
        }
    }

    /**
     * 查找车辆品牌的方法
     * 
     * @return
     */
    public void findAllCarBrand()
    {
        try{
        	bctiVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(basCarTypeInfoService.findAllCarBrand(bctiVo));
        }catch(Exception e){
            logger.error("查找车辆品牌的方法失败", e);
        }
    }

    /**
     * 添加记录的方法
     * 
     * @return
     */
    public void save()
    {
        Msg msg = new Msg();
        try
        {
        	bctiVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = basCarTypeInfoService.isExist(bctiVo);
            if (isno)
            {
                msg.setSuccess(false);
                msg.setMsg("对不起，您输入的车辆型号已存在，请重新输入！");
            }
            else
            {
                basCarTypeInfoService.save(bctiVo);
                msg.setSuccess(true);
                msg.setMsg("新增车辆型号[" + bctiVo.getCtypeName() + "]成功!");
            }
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
        }
        super.writeJson(msg);
    }

    /**
     * 删除记录的方法
     * 
     * @return
     */
    public void delete()
    {
        Msg msg = new Msg();
        try
        {
            basCarTypeInfoService.delete(bctiVo.getCtypeId());
            msg.setSuccess(true);
            msg.setMsg("删除车辆型号[" + bctiVo.getCtypeName() + "]成功!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
        }
        super.writeJson(msg);
    }

    /**
     * 修改记录的方法
     * 
     * @return
     */
    public void edit()
    {
        Msg msg = new Msg();
        try{
        	bctiVo.setEnterpriseId(getUserEnterpriseId());
            boolean isno = basCarTypeInfoService.isExist(bctiVo);
            if (isno){
                msg.setSuccess(false);
                msg.setMsg("对不起，您输入的车辆型号已存在，请重新输入！");
            }else{
                basCarTypeInfoService.update(bctiVo);
                msg.setSuccess(true);
                msg.setMsg("修改车辆型号[" + bctiVo.getCtypeName() + "]成功!");
            }
        }catch(Exception e){
            msg.setSuccess(false);
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
        }
        super.writeJson(msg);
    }

    
    public BasCarTypeInfoVo getModel()
    {
        return bctiVo;
    }
}
