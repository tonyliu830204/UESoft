package com.syuesoft.fin.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.service.StSellPreclrService;
import com.syuesoft.fin.vo.StSellPreclrData;
import com.syuesoft.fin.vo.StSellPreclrVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 销售结算单Action
 * @author SuMing
 */
@SuppressWarnings("serial")
@Action("StSellPreclrAction")
public class StSellPreclrAction extends BaseAction implements
        ModelDriven<StSellPreclrVo>
{

    private final Logger logger = Logger.getLogger(this.getClass());

    private StSellPreclrVo stSellPreclrVo = new StSellPreclrVo();

    @Autowired
    StSellPreclrService stSellPreclrService;

    @Autowired
    BaseService baseService;


    /**
     * 获取移仓单明细缓存数据
     */
    @SuppressWarnings("unchecked")
    public List<StSellPreclrVo> getSessionList()
    {
        HttpSession session = super.getRequest().getSession();
        List<StSellPreclrVo> list = (List<StSellPreclrVo>) session
                .getAttribute("list");
        if (list == null)
        {
            list = new ArrayList<StSellPreclrVo>();
            session.setAttribute("list", list);
        }
        return list;
    }

    /**
     * 判断销售单号是否已存在
     */
    public void isExist()
    {
        List<StSellPreclrVo> list = this.getSessionList();
        boolean isno = false;
        if (list.size() > 0)
            for (StSellPreclrVo sspvo : list) {
                if (sspvo.getSellmmId().equals(stSellPreclrVo.getSellmmId())){
                    isno = true;
                    break;
                }
            }
        if (isno)// 存在 移除临时明细信息
            removeSessionData();
        else // 不存在 添加临时明细信息
            loadStSellOrderitemBySellmmId();
    }

    /**
     * 移除临时明细信息
     */
    public void removeSessionData()
    {
        List<StSellPreclrVo> list = this.getSessionList();
        if (list.size() > 0)
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).getSellmmId().equals(
                        stSellPreclrVo.getSellmmId())){
                    list.remove(i);
                    --i;
                }
            }
        Json json = new Json();
        json.setRows(list);
        json.setTotal(list.size());
        super.writeJson(json);
    }

    /**
     *    根据客户名称选择销售单信息
     */
    public void loadSellOrderInfo()
    {
        try{
        	stSellPreclrVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(stSellPreclrService.loadSellOrderInfo(stSellPreclrVo));
        }
        catch(Exception e){
            logger.error("销售单汇总信息    综合查询失败", e);
        }
    }

    /**
     * 销售单信息 预加载
     */
    public void loadStSellOrderitemBySellmmId()
    {
        try{
            super.writeJson(stSellPreclrService.loadStSellOrderItemBySellmmId(stSellPreclrVo));
        }
        catch(Exception e){
            logger.error("销售单信息   预加载失败", e);
        }
    }

    /**
     * 转收银
     */
    public void changePaid()
    {
        Msg msg = new Msg();
        try{
            stSellPreclrService.changePaid(stSellPreclrVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e){
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 销售结算单汇总信息 预加载
     */
    public void loadStSellPreclrMain()
    {
        try{
        	stSellPreclrVo.setEnterpriseId(Integer.parseInt(getNowEnterpriseId().toString()));
            super.writeJson(stSellPreclrService.loadStSellPreclrMain(stSellPreclrVo));
        }
        catch(Exception e){
            logger.error("销售结算单汇总信息    预加载失败", e);
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 销售结算单汇总信息 综合查询
     */
    public void searchStSellPreclrMainByCondition()
    {
        try
        {
            List<StSellPreclrVo> resultList = stSellPreclrService.searchStSellPreclrMainByCondition(stSellPreclrVo);
            Json json = new Json();
            json.setRows(resultList);
            json.setTotal(resultList.size());
            super.writeJson(json);
        }
        catch(Exception e){
            logger.error("销售结算单汇总信息  综合查询失败", e);
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 销售结算单汇总信息 添加
     */
    public void addStSellPreclr()
    {
        Msg msg = new Msg();
        try
        {
            List<StSellPreclrVo> list = this.getSessionList();
            BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
    		if(user!=null&&!user.equals(""))
    			stSellPreclrVo.setManager(user.getBasStuff().getStfId()+"");
            stSellPreclrService.addStSellPreclr(stSellPreclrVo, list);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            logger.error("销售结算单汇总信息  添加失败", e);
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 销售结算单汇总信息 删除
     */
    public void deleteStSellPercharge()
    {
        Msg msg = new Msg();
        try{
            stSellPreclrService.deleteStSellPercharge(stSellPreclrVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e){
            logger.error("销售结算单汇总信息  删除失败", e);
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 销售结算单汇总信息 修改
     */
    public void updateStSellPreclr()
    {
        Msg msg = new Msg();
        try
        {
            List<StSellPreclrVo> list = this.getSessionList();
            stSellPreclrService.updateStSellPreclr(stSellPreclrVo, list);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            logger.error("销售结算单汇总信息  修改失败", e);
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 计算数量，金额，成本额
     */
    public void getSum()
    {
        List<StSellPreclrVo> list = this.getSessionList();
        int preclrNum = 0; // 总数量
        double preclrAmount = 0; // 总金额
        double preclrCostAmount = 0;// 总成本额
        for (int i = 0; i < list.size(); i++)
        {
            preclrNum += Integer.parseInt(list.get(i).getSelldCnt());
            preclrAmount += Double.parseDouble(list.get(i).getSelldAmount());
            preclrCostAmount += Double.parseDouble(list.get(i)
                    .getSelldCostAmount());
        }
        StSellPreclrData sspd = new StSellPreclrData();
        sspd.setPreclrNum(preclrNum);
        sspd.setPreclrAmount(super.doubleFormat(preclrAmount));
        sspd.setPreclrCostAmount(super.doubleFormat(preclrCostAmount));
        super.writeJson(sspd);
    }

    /**
     * 清空临时明细数据
     */
    public void clear()
    {
        List<StSellPreclrVo> list = this.getSessionList();
        list.clear();
        Json json = new Json();
        json.setRows(list);
        json.setTotal(list.size());
        super.writeJson(json);
    }

    /**
     * 通过编号获取明细
     */
    public void findIdByCondition()
    {
        List<StSellPreclrVo> list = this.getSessionList();
        String cerNo = stSellPreclrVo.getCerNo();
        String[] str = cerNo.split(",");
        for (String s : str)
        {
            StSellPreclrVo sspvo = new StSellPreclrVo();
            sspvo.setSellmmId(s);
            try{
//                List<StSellPreclrVo> resultList = stSellPreclrService
//                        .loadStSellOrderitemBySellmmId(sspvo);
//                for (StSellPreclrVo spvo : resultList){
//                    list.add(spvo);
//                }
            }
            catch(Exception e)
            {
                logger.error("通过编号获取明细失败", e);
                Msg msg = new Msg();
                msg.setMsg("error");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
        }
        Json json = new Json();
        json.setRows(list);
        json.setTotal(list.size());
        super.writeJson(json);
    }

    public StSellPreclrVo getModel()
    {
        return stSellPreclrVo;
    }
}
