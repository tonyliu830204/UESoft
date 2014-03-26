package com.syuesoft.frt.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.service.BasCarArchivesService;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.bas.service.FrtBaseService;
import com.syuesoft.bas.service.FrtRepairItemService;
import com.syuesoft.base.vo.BasStorehouseVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.vo.FrtOptionsVo;

@SuppressWarnings("serial")
@ParentPackage(value = "basePackage")
@Action("frtOptionsAction")
public class FrtOptionsAction extends BaseAction implements
        ModelDriven<FrtOptionsVo>
{
    private final Logger logger = Logger.getLogger(this.getClass());
    FrtOptionsVo fotVo = new FrtOptionsVo();
    @Autowired
    private BasCarArchivesService basCarArchivesService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private FrtBaseService frtBaseService;
    @Autowired
    private FrtService frtService;
    @Autowired
    private FrtRepairItemService frtRepairItemService;
    
    /**
	 * 查找预约估价单配件价格选择
	 * */
    public void getDefaultBillPrictType(){
    	try
        {
            super.writeJson(frtService.getDefaultBillPrictType(getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查找预约估价单配件价格选择失败！", e);
        }
    }
    /**
     * 查找默认维修类别
     * */
    public void findDefaultReceptionClass()
    {
        try
        {
            super.writeJson(baseService.findDefaultProperties(Contstants.PARAMETER_SET.STGCARPARA,
                    Contstants.STGCARPARA.DEFAULTSERTYPE,getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查找默认维修类别失败！", e);
        }
    }
    
    
    /**
     * 加载所有工单状态
     * */
    public void findReceptionState()
    {
        try
        {
            super.writeJson(baseService.baseListData(
                    Contstants.DOCUMENT_TAG.DOCUMENTKEY));
        }
        catch(Exception e)
        {
            logger.error("加载所有工单状态失败！", e);
        }
    }

    /**
     * 查找维修完工控制方式
     * */
    public void findDefaultFrtWorkShopManagerFashion()
    {
        try
        {
                String findDefaultRepairType = baseService.findDefaultProperties(Contstants.PARAMETER_SET.SCENESET,
                        Contstants.SCENESET.CONTROLBALANCEWAY,getNowEnterpriseId());
                if (findDefaultRepairType != null
                        && findDefaultRepairType.equals("finashcontrol"))
                {
                    super.writeJson("true");
                }
                else if (findDefaultRepairType != null
                        && findDefaultRepairType.equals("finashOutcontrol"))
                {
                    super.writeJson("true");
                }
                else
                {
                    super.writeJson("false");
                }
        }
        catch(Exception e)
        {
            logger.error("查找维修完工控制方式失败！", e);
        }
    }

    /**
     * 查找维修完工、仓库出料完成控制结算方式
     * */
    public void findDefaultFrtWorkShopManagerAndDepotFashion()
    {
        try
        {
            super.writeJson(baseService.findDefaultProperties(Contstants.PARAMETER_SET.SCENESET,
                    Contstants.SCENESET.CONTROLBALANCEWAY,getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error(" 查找转接车默认收费性质失败！", e);
        }
    }

    /**
     * 查找转接车默认收费性质
     * */
    public void findDefaultRepairType()
    {
        try
        {
            super.writeJson(baseService.findDefaultProperties(Contstants.PARAMETER_SET.EVALUATESE,
                    Contstants.EVALUATESE.CHANGECOLLECTNATURE,getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error(" 查找转接车默认收费性质失败！", e);
        }
    }

    /**
     * 查找转接车默认索赔性质
     * */
    public void findDefaultClaimsType()
    {
        try
        {
            super.writeJson(baseService.findDefaultProperties(Contstants.PARAMETER_SET.EVALUATESE,
                    Contstants.EVALUATESE.CHANGECLAIMNATURE,getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error(" 查找转接车默认索赔性质失败！", e);
        }
    }

    /**
     * 查找默认客户地区属性
     * */
    public void findDefaultCustomAreaId()
    {
        try{
            super.writeJson(baseService.findDefaultProperties(Contstants.PARAMETER_SET.ARCHIVESSE,Contstants.ARCHIVESSE.DEFAULTAREA,getNowEnterpriseId()));
        }catch(Exception e)
        {
            logger.error(" 查找默认客户地区属性失败！", e);
        }
    }

    /**
     * 查找默认索赔厂商编号
     * */
    public void findDefaultClaimsCompanyId()
    {
        try
        {
            super.writeJson(baseService.findDefaultProperties(Contstants.PARAMETER_SET.INDEMNITYS,
                    Contstants.INDEMNITYS.CLAIMMANUCODE,getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查找默认索赔厂商编号失败！", e);
        }
    }

    /**
     * 查找默认车牌照格式
     * */
    public void findDefaultCarLicenseFormat()
    {
        try
        {
            super.writeJson(baseService.findDefaultProperties(Contstants.PARAMETER_SET.COLLIGATES,
                    Contstants.COLLIGATES.VEHICLELICENSE,getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询默认车牌照格式失败！", e);
        }
    }

    /**
     * 查询车牌照
     */
    public void findAllCarLicense()
    {
        try
        {
        	if(fotVo.getFlag()!=null&&fotVo.getFlag()==true){
        		super.writeJson(frtBaseService.findUseAllCarLicense(fotVo.getQ(),getNowEnterpriseId()));
        	}else{
        		super.writeJson(baseService.findAllCarLicense(fotVo.getQ(),getNowEnterpriseId()));        		
        	}
        }catch(Exception e){
            logger.error("查询车牌照失败！", e);
        }
    }

    /**
     * 查询车牌照
     */
    public void findAllCarLicenseAsCarLicense()
    {
        try{
            super.writeJson(frtBaseService.findAllCarLicenseAsCarLicense(fotVo
                    .getQ(),getNowEnterpriseId()));
            // logger.info("查询车牌照成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询车牌照失败！", e);
        }
    }
    /**
     * 查询有工单号的车牌照
     */
    public void findAllCarLicenseByReceptionId()
    {
        try
        {
            super.writeJson(frtBaseService.findAllCarLicenseByReceptionId(fotVo
                    .getQ(),getNowEnterpriseId()));
            // logger.info("查询车牌照成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询有工单号的车牌照失败！", e);
        }
    }

    /**
     * 查询有工单号的车牌照
     */
    public void findAllCarLicenseByReceptionIdAsCarLicense()
    {
        try
        {
            super.writeJson(frtBaseService
                    .findAllCarLicenseByReceptionIdAsCarLicense(fotVo.getQ(),getNowEnterpriseId()));
            // logger.info("查询车牌照成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询有工单号的车牌照失败！", e);
        }
    }

    /**
     * 查询维修类别
     */
    public void findAllReptype()
    {
        try{
            super.writeJson(frtBaseService.findAllReptype(fotVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询维修类别失败！", e);
        }
    }

    /**
     * 查询客户名称
     */
    public void findAllCustom()
    {
        try
        {
        	fotVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(baseService.findAllCustom(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询客户名称失败！", e);
        }
    }
    /**
     * 查询客户
     */
    public void findAllCustomAsCustomName()
    {
        try{
            super.writeJson(frtBaseService.findAllCustomAsCustomName(fotVo
                    .getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询客户失败！", e);
        }
    }
    /**
     * 查询维修班组
     */
    public void findAllRepairGroup()
    {
        try
        {
        	fotVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtBaseService.findAllRepairGroup(fotVo.getQ(),getNowEnterpriseId()));
            // logger.info("查询维修班组成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询维修班组失败！", e);
        }
    }

    /**
     * 查询维修工位
     */
    public void findAllRepairWork()
    {
        try
        {
        	fotVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtBaseService.findAllRepairWork(fotVo.getQ(),getNowEnterpriseId()));
            // logger.info("查询维修工位成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询维修工位失败！", e);
        }
    }

    /**
     * 查询索赔厂商
     */
    public void findAllClaimManufacturers()
    {
        try{
        	super.writeJson(frtBaseService.findAllClaimManufacturers(fotVo.getQ(),getNowEnterpriseId()));
            // logger.info("查询索赔厂商成功！");
        }
        catch(Exception e){
            // TODO: handle exception
            logger.error("查询索赔厂商失败！", e);
        }
    }

    /**
     * 查询收费性质
     */
    public void findAllRepairType()
    {
        try
        {
            super.writeJson(baseService.findAllRepairType(fotVo.getQ(),getNowEnterpriseId()));
            // logger.info("查询收费性质成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询收费性质失败！", e);
        }
    }

    /**
     * 查询索赔分类
     */
    public void findAllClaimsType()
    {
        try
        {
            super.writeJson(baseService.findAllClaimsType(fotVo.getQ(),getNowEnterpriseId()));
            // logger.info("查询索赔分类成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询索赔分类失败！", e);
        }
    }

    /**
     * 查询VIN号
     * */
    public void findAllCarVin()
    {
        try
        {
        	fotVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basCarArchivesService.findAllCarVin(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询VIN号失败！", e);
        }
    }

    /* 从码表中获取下拉框数据 */
    public void findBaseListData()
    {
        try
        {
            super.writeJson(baseService.baseListData(fotVo.getKey()));
        }
        catch(Exception e)
        {
            logger.error("获取下拉框数据失败！", e);
        }
    }

    /**
     * 查询车品牌
     */
    public void findCarBrand()
    {
        try
        {
        	fotVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(baseService.findAllCarBrand(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询车品牌失败", e);
        }
    }

    /**
     * 查询车型号
     */
    public void findCarType()
    {
        try
        {
            super.writeJson(baseService.findAllCarType(fotVo.getCbrdId(), fotVo
                    .getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询车型号失败", e);
        }
    }

    /**
     * 查询车款式
     */
    public void findCarStyle()
    {
        try
        {
            super.writeJson(baseService.findAllCarStyle(fotVo.getCtypeId(),
                    fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询车款式失败", e);
        }
    }

    /**
     * 查询车身颜色
     */
    public void findCarColor()
    {
        try
        {
            super.writeJson(baseService.findAllCarColor(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询车身颜色失败", e);
        }
    }

    /**
     * 查询保险公司
     */
    public void findInsuranceCompany()
    {
        try
        {
            super.writeJson(baseService.findAllRelationCampany(fotVo.getQ(),true,getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询保险公司失败", e);
        }
    }

    /**
     * 查询车辆经销商
     */
    public void findCarSellers()
    {
        try
        {
            super.writeJson(baseService.findAllCarSellers(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询车辆经销商失败", e);
        }
    }

    /**
     * 查询客户性质
     */
    public void findCustomNature()
    {
        try
        {
            super.writeJson(baseService.findAllCustomNature(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询客户性质失败", e);
        }
    }

    /**
     * 查询客户分类
     */
    public void findCustomGroup()
    {
        try
        {
            super.writeJson(baseService.findAllCustomGroup(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询客户类型失败", e);
        }
    }

    /**
     * 查询客户类型
     */
    public void findCustomType()
    {
        try
        {
            super.writeJson(baseService.findAllCustomType(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询客户分类失败", e);
        }
    }

    /**
     * 查询客户区域
     */
    public void findCustomArea()
    {
        try
        {
            super.writeJson(baseService.findAllCustomArea(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询客户所在区域失败", e);
        }
    }

    /**
     * 查询客户名称
     */
    public void findCustomName()
    {
        try
        {
        	fotVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(baseService.findAllCustom(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询客户名称失败", e);
        }
    }

    /**
     * 选中客户名称时带数据返回
     */
    public void onSelectCustom()
    {
        try
        {
            super.writeJson(basCarArchivesService.getCustomById(fotVo.getCustomId()));
        }
        catch(Exception e)
        {
            logger.error("选中客户名称时带数据返回失败", e);
        }
    }

    /**
     * 查询准驾车型
     */
    public void findAllowCarType()
    {
        try
        {
            super.writeJson(baseService.findAllowCarType(fotVo.getQ()));
        }
        catch(Exception e)
        {
            logger.error("查询准驾车型失败", e);
        }
    }

    /**
     * 查询所有仓库
     */
    public void findAllStorehouse()
    {
        try
        {
        	BasStorehouseVo basStorehouseVo = new BasStorehouseVo();
	        basStorehouseVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(baseService.findAllStorehouse(basStorehouseVo));
        }
        catch(Exception e)
        {
            logger.error("查询所有仓库失败", e);
        }
    }

    /**
     * 查询配件产地
     */
    public void findAllPartsState()
    {
        try
        {
            super.writeJson(baseService.findAllPartsState(getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询配件产地失败", e);
        }
    }

    /**
     * 查询配件型号
     */
    public void findAllPartsType()
    {
        try
        {
            super.writeJson(baseService.findAllPartsType(fotVo.getPbrdId(),
                    fotVo.getPtypeName(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询配件型号失败", e);
        }
    }

    /**
     * 查询配件品牌
     */
    public void findAllPartsBrand()
    {
        try
        {
            super.writeJson(baseService.findAllPartsBrand(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询配件品牌失败", e);
        }
    }

    /**
     * 查找工时分类
     * */
    public void findAllWorkHours()
    {
        try {
            super.writeJson(frtRepairItemService.getBasWorkhourSort(fotVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查找工时分类失败！", e);
        }
    }
    /**
     * 查找仓别
     * */
    public void findAllWorkHouse(){
    	try {
			super.writeJson(frtBaseService.findAllWorkHouse(fotVo.getQ(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("查找仓别失败！", e);
		}
    }
    
    /**
     * 查找当前企业下的子店信息
     * */
    public void findAllDistributeEnterprise(){
    	try {
    		fotVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(baseService.findAllDistributeEnterprise(fotVo.getEnterpriseId().toString(),fotVo.getQ(),null));
		} catch (Exception e) {
			logger.error("查找所有分布点企业失败！", e);
		}
    }
    
    
    public FrtOptionsVo getModel(){
        return fotVo;
    }
}
