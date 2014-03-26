package com.syuesoft.bas.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasPartsReportPriceClassficationService;
import com.syuesoft.base.vo.BasPartsReportpriceclassficationVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 基础资料-->配件性质：配件报价分类系数Action
 * 
 * @author SuMing
 */

public class BasPartsReportPriceClassficationAction extends BaseAction
        implements ModelDriven<BasPartsReportpriceclassficationVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    BasPartsReportPriceClassficationService logic = null;
    /** 配件报价分类Service对象 */
    BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo = new BasPartsReportpriceclassficationVo();
    /** 配件报价分类Model对象 */

    /**
     * 基础资料-->配件性质：配件报价分类 增加
     * @return
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
        	basPartsReportpriceclassficationVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            logic.add(basPartsReportpriceclassficationVo);
            msg.setMsg("添加招标单位成功！");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            msg.setMsg("添加招标单位失败！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：配件报价分类 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
            logic.delete(basPartsReportpriceclassficationVo);
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
     * 基础资料-->配件性质：配件报价分类 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
        	basPartsReportpriceclassficationVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            logic.update(basPartsReportpriceclassficationVo);
            msg.setMsg("添加招标单位成功！");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            msg.setMsg("添加招标单位失败！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：配件报价分类 全部显示
     */
    public void view()
    {
        try{
        	basPartsReportpriceclassficationVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasPartsReportpriceclassficationVo> baspartsreportpriceclassficationrecord = logic.getAllByPage(basPartsReportpriceclassficationVo);
            /** baspartsreportpriceclassficationrecord用于存储当前页面数据 */
            List<BasPartsReportpriceclassficationVo> baspartsreportpriceclassficationall = logic.findAll(basPartsReportpriceclassficationVo);
            /** baspartsreportpriceclassficationall用于存储所有页面数据 */
            Json json = new Json();
            json.setTotal(baspartsreportpriceclassficationall.size());
            json.setRows(baspartsreportpriceclassficationrecord);
            super.writeJson(json);
        }
        catch(Exception e){
        }
    }

    public BasPartsReportpriceclassficationVo getModel()
    {
        return basPartsReportpriceclassficationVo;
    }

    public BasPartsReportPriceClassficationService getLogic()
    {
        return logic;
    }

    public void setLogic(BasPartsReportPriceClassficationService logic)
    {
        this.logic = logic;
    }
}
