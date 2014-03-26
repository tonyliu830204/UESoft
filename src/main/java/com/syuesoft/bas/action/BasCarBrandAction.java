package com.syuesoft.bas.action;

import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCarBrandService;
import com.syuesoft.base.vo.BasCarBrandVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Msg;

/**
 * 基础资料-->车辆性质：车辆品牌Action
 * 
 * @author SuMing
 */
public class BasCarBrandAction extends BaseAction implements
        ModelDriven<BasCarBrandVo>
{
    Logger logger=Logger.getLogger(this.getClass());
    private static final long serialVersionUID = 1L;
    BasCarBrandVo cbVo = new BasCarBrandVo(); /* 车辆品牌VO对象 */
    BasCarBrandService logic = null; /* 车辆品牌Service */
    
    /**
     * 基础资料-->车辆性质：车辆品牌 增加
     */
    public void add()
    {
        Msg msg = new Msg();
        try{
        	cbVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(cbVo);
            if (isno){
                msg.setMsg("对不起，您输入的车辆品牌已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }else{
                logic.add(cbVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }catch(Exception e){
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            super.writeJson(msg);
            logger.error("对不起，记录无法保存，请确认内容及格式是否正确！", e);
        }
    }

    /**
     * 基础资料-->车辆性质：车辆品牌 删除
     */
    public void del(){
        Msg msg = new Msg();
        try{
            logic.delete(cbVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }catch(Exception e){
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            msg.setSuccess(false);
            super.writeJson(msg);
            logger.error("对不起，该数据已经被使用，不允许删除！", e);
        }
    }

    /**
     * 基础资料-->车辆性质：车辆品牌 判断是否满足删除条件
     */
    @SuppressWarnings("unchecked")
    public void delCondition(){
        Msg msg = new Msg();
        try
        {
            List list = logic.delCondition(cbVo);
            if ((list != null) && (list.size() > 0)){
                msg.setMsg("isExist");
                msg.setSuccess(true);
                super.writeJson(msg);
            }else{
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }catch(Exception e){
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
            logger.error("车辆品牌 判断是否满足删除条件！", e);
        }
    }

    /**
     * 基础资料-->车辆性质：车辆品牌 修改
     */
    @SuppressWarnings("deprecation")
	public void update(){
        Msg msg = new Msg();
        try{
        	cbVo.setEnterpriseId(getNowEnterpriseId());
            boolean isno = logic.isExist(cbVo);
            if (isno){
                msg.setMsg("对不起，您输入的车辆品牌已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }else{
            	if(cbVo.getCbrdLogo()!=null&&cbVo.getCbrdLogo().length()>0){
            		boolean flag=false;
            		for (int i = 0; i < cbVo.getCbrdLogo().length(); i++) {
						if(cbVo.getCbrdLogo().charAt(i)=='?'){
							cbVo.setCbrdLogo(null);
							flag=true;
							break;
						}
					}
            		if(!flag)
            			cbVo.setCbrdLogo(ServletActionContext.getRequest().getRealPath("")+"\\"+cbVo.getCbrdLogo());
            	}
            	
            	cbVo.setStfId(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId().toString());
                logic.update(cbVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }catch(Exception e){
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            super.writeJson(msg);
            logger.error("对不起，记录无法保存，请确认内容及格式是否正确！", e);
        }
    }

    /**
     * 基础资料-->车辆性质：车辆品牌 全部显示
     */
    public void view(){
        try{
        	cbVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(logic.getAllByPage(cbVo, getRequest()));
        }catch(Exception e){
            logger.error("车辆品牌 全部显示失败！", e);
        }
    }
    
    /**
     * 基础资料-->车辆性质：更改车辆品牌图标
     */
    public void uploadImg(){
        try{
            super.writeJson(logic.uploadImg(cbVo));
        }catch(Exception e){
            logger.error("更改车辆品牌图标失败！", e);
        }
    }

    public BasCarBrandVo getModel(){
        return cbVo;
    }

    public BasCarBrandService getLogic(){
        return logic;
    }

    public void setLogic(BasCarBrandService logic){
        this.logic = logic;
    }
}