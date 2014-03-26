package com.syuesoft.bas.action;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCarArchivesService;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.bas.service.FrtBaseService;
import com.syuesoft.frt.vo.FrtCarVo;
import com.syuesoft.model.FrtCar;
import com.syuesoft.util.Msg;

/**
 * 车档案action
 * @author Liujian
 */
@ParentPackage(value = "basePackage")
@Action("basCarArchivesAction")
public class BasCarArchivesAction extends BaseAction implements
        ModelDriven<FrtCarVo>
{

    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    FrtCarVo fcVo = new FrtCarVo();
    private BasCarArchivesService basCarArchivesService;
    private BaseService baseService;
    private FrtBaseService frtBaseService; 

	/**
     * 查询车品牌
     */
    public void findCarBrand(){
        try{
            super.writeJson(baseService.findAllCarBrand(fcVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询车品牌失败", e);
        }
    }

    /**
     * 查询车型号
     */
    public void findCarType(){
        try{
            super.writeJson(baseService.findAllCarType(fcVo.getCbrdId(), fcVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询车型号失败", e);
        }
    }
    
    /**
     * 查询车型号
     */
    public void findCarTypeAsName()
    {
        try{
        	
            super.writeJson(frtBaseService.findAllCarTypeAsName(fcVo
                    .getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询车型号失败", e);
        }
    }
    
    /**
     * 变更车牌照
     */
    public void changecarId()
    {
        Msg msg = new Msg();
        try{
        	fcVo.setEnterpriseId(getNowEnterpriseId());
           frtBaseService.changecarId(fcVo);
           msg.setSuccess(true);
           msg.setMsg("变更车牌照成功！");
        } catch(Exception e) {
            msg.setMsg("变更车牌照失败");
            logger.error("变更车牌照失败", e);
        }
        super.writeJson(msg);
    }
    
    public void isExistsCarId()
    {
        Msg msg = new Msg();
        try{
        	fcVo.setEnterpriseId(getNowEnterpriseId());
            List<FrtCar> list = frtBaseService.getCarId(fcVo);
            if (list != null)
            {
                msg.setMsg("此车牌照已存在，请重新输入！");
                msg.setSuccess(true);
            }
            super.writeJson(msg);
        }catch(Exception e){
            logger.error("校验车牌照失败！", e);
        }
    }
    /**
     * 查询车款式
     */
    public void findCarStyle()
    {
        try{
            super.writeJson(baseService.findAllCarStyle(fcVo.getCtypeId(), fcVo
                    .getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询车款式失败", e);
        }
    }

    /**
     * 查询车身颜色
     */
    public void findCarColor()
    {
        try{
            super.writeJson(baseService.findAllCarColor(fcVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询车身颜色失败", e);
        }
    }

    /**
     * 查询保险公司
     */
    public void findInsuranceCompany()
    {
        try{
            super.writeJson(baseService.findAllRelationCampany(fcVo.getQ(),
                    true,getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询保险公司失败", e);
        }
    }

    /**
     * 查询车辆经销商
     */
    public void findCarSellers()
    {
        try{
            super.writeJson(baseService.findAllCarSellers(fcVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询车辆经销商失败", e);
        }
    }

    /**
     * 查询客户性质
     */
    public void findCustomNature()
    {
        try{
            super.writeJson(baseService.findAllCustomNature(fcVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询客户性质失败", e);
        }
    }

    /**
     * 查询客户类型
     */
    public void findCustomGroup()
    {
        try{
            super.writeJson(baseService.findAllCustomGroup(fcVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询客户类型失败", e);
        }
    }

    /**
     * 查询客户分类
     */
    public void findCustomType()
    {
        try{
            super.writeJson(baseService.findAllCustomType(fcVo.getQ(),getNowEnterpriseId()));
        } catch(Exception e){
            logger.error("查询客户分类失败", e);
        }
    }

    /**
     * 查询客户所在区域
     */
    public void findCustomArea()
    {
        try{
            super.writeJson(baseService.findAllCustomArea(fcVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询客户所在区域失败", e);
        }
    }

    /**
     * 查询客户名称
     */
    public void findCustomName()
    {
        try{
            super.writeJson(baseService.findAllCustom(fcVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询客户名称失败", e);
        }
    }

    /**
     * 选中客户名称时带数据返回
     */
    public void onSelectCustom()
    {
        try{
            super.writeJson(basCarArchivesService.getCustomById(fcVo.getCustomId()));
        }catch(Exception e){
            logger.error("选中客户名称时带数据返回失败", e);
        }
    }

    /**
     * 查询准驾车型
     */
    public void findAllowCarType()
    {
        try{
        	
            super.writeJson(baseService.loadDataByChildId("87"));
        }catch(Exception e){
            logger.error("查询准驾车型失败", e);
        }
    }

    /**
     * 查档案datagrid
     */
    public void datagrid()
    {
        try{
        	fcVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basCarArchivesService.datagrid(fcVo));
        }catch(Exception e){
            logger.error("查档案datagrid失败", e);
        }
    }

    /**
     * 客户信息combogrid
     */
    public void customCombogrid()
    {
        try{
        	fcVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basCarArchivesService.datagridCustom(fcVo));
        }catch(Exception e){
            logger.error("客户信息combogrid失败", e);
        }
    }

    /**
     * 保存车档案
     */
    public void save()
    {
        Msg msg = new Msg();
        try
        {
            if (basCarArchivesService.carLicenseIsExits(fcVo.getCarLicense()) != null){
                msg.setMsg("对不起，您输入的车辆牌照已存在，请重新输入！");
                super.writeJson(msg);
                return;
            }
            if (basCarArchivesService.carVinIsExits(fcVo.getCarVin()) != null){
                msg.setMsg("对不起，您输入的VIN号已存在，请重新输入！");
                super.writeJson(msg);
                return;
            }
            fcVo.setEnterpriseId(getNowEnterpriseId());
            msg = basCarArchivesService.save(fcVo);
        }catch(Exception e){
            logger.error("保存车档案     异常", e);
        }
        super.writeJson(msg);
    }

    /**
     * 根据VIN获取车档案
     * */
    public void findFrtCarByVIN()
    {
        try{
            super.writeJson(basCarArchivesService.findFrtCarByVin(fcVo
                    .getCarVin()));
        }catch(Exception e){
            logger.error("根据VIN获取车档案失败!", e);
        }
    }
    /**
     * 根据VIN获取车档案
     * */
    public void findFrtCarByCustomId()
    {
        try{
            super.writeJson(basCarArchivesService.findFrtCarByCustomId(fcVo
                    .getCustomId()));
        }catch(Exception e){
            logger.error("根据VIN获取车档案失败!", e);
        }
    }
    /**
     * 删除车档案
     */
    public void remove()
    {
        Msg msg = new Msg();
        try{
            basCarArchivesService.remove(fcVo.getCarId());
            msg.setSuccess(true);
            msg.setMsg("删除车档案成功！");
        }catch(Exception e){
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            logger.error("对不起，该数据已经被使用，不允许删除！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 更新车档案
     */
    public void edit()
    {
        Msg msg = new Msg();
        try{
        	fcVo.setEnterpriseId(getNowEnterpriseId());
            basCarArchivesService.edit(fcVo);
            msg.setSuccess(true);
            msg.setMsg("更新车档案成功!");
        }catch(Exception e){
            msg.setMsg("更新车档案失败!");
            logger.error("更新车档案失败", e);
        }
        super.writeJson(msg);
    }

    /************************* 前台-查询车档案模块 ********************************/

    /**
     * 查询车牌照
     */
    public void findAllCarLicense()
    {
        try{
            super.writeJson(baseService.findAllCarLicense(fcVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询车牌照失败", e);
        }
    }

    /**
     * 查询车档案
     * */
    public void findAll()
    {
        try{
            super.writeJson(basCarArchivesService.datagrid(fcVo));
        }catch(Exception e){
            logger.error("查询车档案失败", e);
        }
    }
    /**
     * 前台车档案查询
     * */
    public void findAllCarByTerm(){
    	try {
    		fcVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(basCarArchivesService.findAllCarByTerm(fcVo));
		} catch (Exception e) {
			logger.error("前台车档案查询失败！", e);
		}
    }
    /**
     * 前台车档案查询-维修记录
     * */
    public void findAllReceptionByCarId(){
    	try {
			super.writeJson(basCarArchivesService.findAllReceptionByCarId(fcVo));
		} catch (Exception e) {
			logger.error("前台车档案查询-维修记录失败！", e);
		}
    }
    /**
     * 前台车档案查询-维修记录-子项
     * */
    public void findAllReceptionByCarIdForChild(){
    	try {
			super.writeJson(basCarArchivesService.findAllReceptionByCarIdForChild(fcVo));
		} catch (Exception e) {
			logger.error("前台车档案查询-维修记录-子项失败！", e);
		}
    }
    /**
     * 前台车档案查询-数据分析
     * */
    public void findAllCarAnalyse(){
    	try {
			super.writeJson(basCarArchivesService.findAllCarAnalyse(fcVo));
		} catch (Exception e) {
			logger.error("前台车档案查询-数据分析失败！", e);
		}
    }
    /**
     * 查询VIN号
     * */
    public void findAllCarVin()
    {
        try{
            super.writeJson(basCarArchivesService.findAllCarVin(fcVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("查询VIN号失败！", e);
        }
    }

    /**
     * 判断VIN是否存在
     * */
    public void isExistsVin()
    {
        try{
            super.writeJson(basCarArchivesService.isExistsVin(fcVo
                            .getCarVin()));
        } catch(Exception e){
            logger.error("查询VIN号失败！", e);
        }
    }

    
    public FrtCarVo getModel()
    {
        return fcVo;
    }
    
    public BasCarArchivesService getBasCarArchivesService()
    {
        return basCarArchivesService;
    }

    @Autowired
    public void setBasCarArchivesService(
            BasCarArchivesService basCarArchivesService)
    {
        this.basCarArchivesService = basCarArchivesService;
    }

    public BaseService getBaseService()
    {
        return baseService;
    }

    @Autowired
    public void setBaseService(BaseService baseService)
    {
        this.baseService = baseService;
    }

    public FrtBaseService getFrtBaseService() {
		return frtBaseService;
	}
    @Autowired
	public void setFrtBaseService(FrtBaseService frtBaseService) {
		this.frtBaseService = frtBaseService;
	}
}