package com.syuesoft.sell.sellwork.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.sell.sellwork.service.CarSellOutService;
import com.syuesoft.sell.sellwork.vo.CarFixVo;
import com.syuesoft.util.Msg;

@ParentPackage(value="basePackage")
@Action("carSellOutAction")
public class CarSellOutAction extends BaseAction implements ModelDriven<CarFixVo> {
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private CarSellOutService carSellOutService;
	
	private CarFixVo carFixVo = new CarFixVo(); 
	
	
	public CarFixVo getModel() {
		return carFixVo;
	}
    
	/**
	* @Title: getOut 
	* @Description: TODO(查询出库单) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getOut(){
	    try {

	    		carFixVo.setEnterpriseId(getNowEnterpriseId());

            writeJson(carSellOutService.getSellOut(carFixVo));
        } catch (Exception e) {
            logger.error("获取加装汇总信息失败", e);
        }
	}
	
	/**
	* @Title: getOutPart 
	* @Description: TODO(查询预计装配件) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getOutPart(){
        try {
	    		carFixVo.setEnterpriseId(getNowEnterpriseId());
            writeJson(carSellOutService.getSellOutPart(carFixVo));
        } catch (Exception e) {
            logger.error("获取加装汇总信息失败", e);
        }
    }
	/**
	* @Title: getOutProject 
	* @Description: TODO(查询预加载的项目) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getOutProject(){
        try {
            writeJson(carSellOutService.getSellOutProject(carFixVo));
        } catch (Exception e) {
            logger.error("获取加装汇总信息失败", e);
        }
    }
	/**
	* @Title: getOutInstall 
	* @Description: TODO(查询已经审核过的加装单) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getOutInstall(){
        try {
	    		carFixVo.setEnterpriseId(getNowEnterpriseId());
            writeJson(carSellOutService.getOutInstall(carFixVo));
        } catch (Exception e) {
            logger.error("获取加装汇总信息失败", e);
        }
    }
	
	/**
    * @Title: getOutInstall 
    * @Description: TODO(查询已经审核过的加装单) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void getOutInstallDetail(){
        try {
	    		carFixVo.setEnterpriseId(getNowEnterpriseId());
            writeJson(carSellOutService.getOutInstallDetail(carFixVo));
        } catch (Exception e) {
            logger.error("获取加装汇总信息失败", e);
        }
    }
    
	/**
	* @Title: saveOutInstall 
	* @Description: TODO(保存出库) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void saveOutInstall(){
	    Msg msg = new Msg();
	    try {
	    	carFixVo.setEnterpriseId(getUserEnterpriseId());
            carSellOutService.saveOutInstall(carFixVo, getUsers());
            msg.setMsg("保存成功");
            msg.setSuccess(true);
        } catch (Exception e) {
        	
        	
            msg.setMsg("保存失败");
            msg.setSuccess(false);
            logger.error("保存加装出库失败", e);
        } 
        writeJson(msg);
	}
	
	/**
	    * @Title: saveOutInstall 
	    * @Description: TODO(删除出库) 
	    * @param     设定文件 
	    * @return void    返回类型 
	    * @throws
	     */
	    public void deleteInstall(){
	        Msg msg = new Msg();
	        try {
	            carSellOutService.deleteInstall(carFixVo);
	            msg.setMsg("删除成功");
	            msg.setSuccess(true);
	        } catch (Exception e) {
	            msg.setMsg("删除失败");
	            msg.setSuccess(false);
	            logger.error("删除加装出库失败", e);
	        } 
	        writeJson(msg);
	    }
}