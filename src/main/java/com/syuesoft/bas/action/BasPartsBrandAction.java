package com.syuesoft.bas.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasPartsBrandService;
import com.syuesoft.st.vo.BasPartsBrandVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 基础资料-->配件性质：配件品牌Action
 * 
 * @author SuMing
 */
@Action("BasPartsBrandAction")
public class BasPartsBrandAction extends BaseAction implements
        ModelDriven<BasPartsBrandVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    @Autowired
    BasPartsBrandService basPartsBrandService;

    BasPartsBrandVo baspartsbrandvo = new BasPartsBrandVo();

    /**
     * 基础资料-->配件性质：配件品牌 增加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
        	baspartsbrandvo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = basPartsBrandService.isExist(baspartsbrandvo);
            if (isno){
                msg.setMsg("对不起，您输入的配件品牌已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                basPartsBrandService.add(baspartsbrandvo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：配件品牌 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
            basPartsBrandService.delete(baspartsbrandvo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：配件品牌 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
        	baspartsbrandvo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = basPartsBrandService.isExist(baspartsbrandvo);
            if (isno){
                msg.setMsg("对不起，您输入的配件品牌已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                basPartsBrandService.update(baspartsbrandvo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：配件品牌 全部显示
     */
    public void view()
    {
        try
        {
        	baspartsbrandvo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasPartsBrandVo> baspartsbrandrecord = basPartsBrandService.getAllByPage(baspartsbrandvo);
            /** baspartsbrandrecord用于存储当前页面数据 */
            List<BasPartsBrandVo> baspartsbrandall = basPartsBrandService.findAll(baspartsbrandvo);
            /** baspartsbrandall用于存储所有页面数据 */
            Json json = new Json();
            json.setTotal(baspartsbrandall.size());
            json.setRows(baspartsbrandrecord);
            super.writeJson(json);
        }
        catch(Exception e)
        {
        }
    }

    public BasPartsBrandVo getModel()
    {
        return baspartsbrandvo;
    }

}
