package com.syuesoft.sell.sellwork.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.sell.sellwork.service.CarFixService;
import com.syuesoft.sell.sellwork.vo.CarFixVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;

@ParentPackage(value="basePackage")
@Action("carFixAction")
public class CarFixAction extends BaseAction implements ModelDriven<CarFixVo> {
    private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private CarFixService carFixService;
	
	private CarFixVo carFixVo = new CarFixVo(); 
	
	public CarFixVo getModel() {
		return carFixVo;
	}

	/**
	 * 获取在库代销 和新增档案状态的 车辆信息
	 */
	public void getCarInfor(){
		try {
				carFixVo.setEnterpriseId(getNowEnterpriseId());
			Json json = carFixService.getCarInfor(carFixVo);
			super.writeJson(json);
		} catch (Exception e) {
		    logger.error("获取在库代销 和新增档案状态的 车辆信息失败", e);
		}
	}
	
	/**
	 * 申请加装
	 */
	public void doApplyFix(){
		Message msg = new Message();
		try {
				carFixVo.setEnterpriseId(getUserEnterpriseId());
			carFixService.doApplyFix(carFixVo, this.getUsers());
			msg.setMsg("申请成功！");
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("申请加装失败", e);
		}
		super.writeJson(msg);
	}
	/**
	 * 获取加装汇总信息
	 */
	public void getCarFixInforNoFinish(){
		try {
				carFixVo.setEnterpriseId(getNowEnterpriseId());
		    writeJson(carFixService.getCarFixInforNoFinish(carFixVo));
		} catch (Exception e) {
		    logger.error("获取加装汇总信息失败", e);
		}
		
	}
	
	/**
     * 获取审核汇总信息
     */
    public void getCarFixInforExamine(){
        try {
				carFixVo.setEnterpriseId(getNowEnterpriseId());
            writeJson(carFixService.getCarFixInforExamine(carFixVo));
        } catch (Exception e) {
            logger.error("获取加装汇总信息失败", e);
        }
        
    }
    
	/**
	* @Title: getCarFixDetail 
	* @Description: TODO(获取加装明细) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getCarFixDetail(){
	    try {
	    	carFixVo.setEnterpriseId(getNowEnterpriseId());
            writeJson(carFixService.getCarFixDetail(carFixVo));
        } catch (Exception e) {
            logger.error("获取加装明细失败", e);
        }
	}
	
	/**
    * @Title: examineCarFix 
    * @Description: TODO(加装完成状态) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void finishCarFix(){
        Msg msg = new Msg();
        try {
        	carFixVo.setEnterpriseId(getNowEnterpriseId());
            carFixService.updateCarFixFinishState(carFixVo);
            msg.setSuccess(true);
            msg.setMsg("更新成功");
        } catch (Exception e) {
            msg.setSuccess(false);
            msg.setMsg("更新失败");
            logger.error("审核加装失败", e);
        }
        writeJson(msg);
    }
	    
	/**
	* @Title: examineCarFix 
	* @Description: TODO(审核加装) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void examineCarFix(){
	    Msg msg = new Msg();
	    try {
	    	carFixVo.setEnterpriseId(getNowEnterpriseId());
	    	msg= carFixService.updateCarFixExamineState(carFixVo);
	       
        } catch (Exception e) {
            msg.setSuccess(false);
            msg.setMsg("审核加装失败");
            logger.error("审核加装失败", e);
        }
        writeJson(msg);
	}
	
	/**
	* @Title: findDefaulePartStore 
	* @Description: TODO(查询默认的销售加装仓) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
    public void findDefaulePartStore(){
        try
        {   
        		carFixVo.setEnterpriseId(getNowEnterpriseId());
            writeJson(carFixService.findDefaulePartStore(carFixVo));
        }
        catch(Exception e)
        {
            logger.error("查询默认的销售加装仓失败", e);
        }
    }
    
    /**
    * @Title: findPart 
    * @Description: TODO(查询已加装的配件) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
	public void findPart(){
	    try
        {
	    	  
        		carFixVo.setEnterpriseId(getNowEnterpriseId());
            writeJson(carFixService.findPart(carFixVo));
        }
        catch(Exception e)
        {
            logger.error("查询默认的销售加装仓失败", e);
        }
	}
	
	/**
    * @Title: findPart 
    * @Description: TODO(查询已加装的项目) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void findItem(){
        try
        {
        	carFixVo.setEnterpriseId(getNowEnterpriseId());
            writeJson(carFixService.findItem(carFixVo));
        }
        catch(Exception e)
        {
            logger.error("查询默认的销售加装仓失败", e);
        }
    }
    
    /**
    * @Title: save 
    * @Description: TODO(保存加装配件以及项目) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void save(){
        Msg msg = new Msg();
        try {
        	  
        		carFixVo.setEnterpriseId(getNowEnterpriseId());
            carFixService.save(carFixVo, this.getUsers());
            msg.setSuccess(true);
            msg.setMsg("保存成功");
        } catch (Exception e) {
            msg.setSuccess(false);
            msg.setMsg("保存失败");
            logger.error("保存加装失败", e);
        }
        writeJson(msg);
    }
    /**
     * @Title: totemoney 
     * @Description: TODO(结算加装费用) 
     * @param     设定文件 
     * @return void    返回类型 
     * @throws
      */
    public void totemoney(){
    	Msg msg = new Msg();
        try {
            msg.setSuccess(true);
            msg.setObj(carFixService.totemoney(carFixVo));
        } catch (Exception e) {
        	logger.error("结算加装费用失败！", e);
            msg.setSuccess(false);
            msg.setMsg("结算加装费用失败！");
        }
        writeJson(msg);
    }
}