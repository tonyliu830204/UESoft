package com.syuesoft.fin.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.service.StSellPerchargeService;
import com.syuesoft.fin.vo.StSellPerchargeVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Msg;
/**
 * 维修预收款Action
 * @author Suming
 */
@SuppressWarnings("serial")
public class StSellPerchargeAction extends BaseAction implements
        ModelDriven<StSellPerchargeVo>
{

    private final Logger logger = Logger.getLogger(this.getClass());
    StSellPerchargeVo sspvo = new StSellPerchargeVo();
    @Autowired
    StSellPerchargeService stSellPerchargeService;
    @Autowired
    BaseService baseService;

    /**
     * 维修储备金信息 预加载
     */
    public void loadStSellPercharge()
    {
        try{
        	sspvo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(stSellPerchargeService.loadStSellPercharge(sspvo));
        }
        catch(Exception e){
            logger.error("维修预收款信息   预加载失败", e);
        }
    }

    /**
     * 维修储备金信息 综合查询
     */
    public void searchStSellPerchargeByCondition()
    {
        try{
        	sspvo.setEnterpriseId(getNowEnterpriseId());;
             super.writeJson(stSellPerchargeService.searchStSellPerchargeByCondition(sspvo));
        }
        catch(Exception e) {
            logger.error("维修预收款信息   综合查询失败", e);
        }
    }

    /**
     * 维修预收款信息 预加载
     */
    public void loadPreStSellPercharge()
    {
        try{
        	sspvo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(stSellPerchargeService.loadPreStSellPercharge(sspvo));
        }catch(Exception e){
        	logger.error("维修预收款信息    预加载     异常", e);
        }
    }

    /**
     * 维修预收款信息 综合查询
     */
    public void searchPreStSellPerchargeByCondition()
    {
        try{
            super.writeJson(stSellPerchargeService.searchPreStSellPerchargeByCondition(sspvo));
        }
        catch(Exception e){
        	logger.error("维修预收款信息 综合查询     异常", e);
        }
    }
    
    /**
     * 维修储备金信息         添加
     */
    public void saveStSellPercharge()
    {
        Msg msg = new Msg();
        try{
        	BasUsers user = (BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
        	sspvo.setEnterpriseId(getNowEnterpriseId());
            if (user != null && !user.equals(""))
                sspvo.setStfId(user.getBasStuff().getStfId());
            stSellPerchargeService.saveStSellPercharge(sspvo);
            msg.setSuccess(true);
            super.writeJson(msg);
        }catch(Exception e){
            logger.error("维修预收款信息    添加失败", e);
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }
    
    /**
     * 维修预收款，储备金信息 删除
     */
    public void deleteStSellPercharge(){
    	Msg msg = new Msg();
        try{
            stSellPerchargeService.deleteStSellPercharge(sspvo);
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e){
            logger.error("维修预收款信息    删除失败", e);
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 维修预收款信息   添加
     */
    public void savePreStSellPercharge(){
        Msg msg = new Msg();
        BasUsers user = (BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
        sspvo.setEnterpriseId(getNowEnterpriseId());
        if (user != null && !user.equals(""))
            sspvo.setStfId(user.getBasStuff().getStfId());
        try{
            stSellPerchargeService.savePreStSellPercharge(sspvo);
            msg.setSuccess(true);
            super.writeJson(msg);
        }catch(Exception e){
        	logger.error("维修预收款信息   添加 异常", e);
        	msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 维修预收款信息      修改
     */
    public void updatePreStSellPercharge()
    {
        Msg msg = new Msg();
        try{
        	sspvo.setEnterpriseId(getNowEnterpriseId());
            stSellPerchargeService.updatePreStSellPercharge(sspvo);
            msg.setSuccess(true);
            super.writeJson(msg);
        }catch(Exception e) {
            logger.error("维修预收款信息      修改   异常", e);
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 维修储备金信息 修改
     */
    public void updateStSellPercharge()
    {
        Msg msg = new Msg();
        try{
        	sspvo.setEnterpriseId(getNowEnterpriseId());
            stSellPerchargeService.updateStSellPercharge(sspvo);
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e){
            logger.error("维修预收款信息    修改    异常", e);
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 维修储备金余额信息 预加载
     */
    public void loadStRecharge()
    {
        try{
            super.writeJson(stSellPerchargeService.loadStRecharge());
        }
        catch(Exception e){
            logger.error("维修预收款余额信息    预加载失败", e);
        }
    }

    /**
     * 维修预收款余额信息     综合查询
     */
    public void searchStRechargeByCondition()
    {
        try{
            super.writeJson(stSellPerchargeService.searchStRechargeByCondition(sspvo));
        }
        catch(Exception e){
            logger.error("维修预收款余额信息    预加载失败", e);
        }
    }
    
    /**
     * 维修预收款余额信息       预加载
     */
    public void loadPreStRecharge()
    {
        try{
        	sspvo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(stSellPerchargeService.loadPreStRecharge(sspvo));
        }
        catch(Exception e){
        	logger.error(" 维修预收款余额信息       预加载    异常", e);
        }
    }

    /**
     * 维修预收款余额信息     综合查询
     */
    public void searchStPreRechargeByCondition()
    {
        try{
            super.writeJson(stSellPerchargeService.searchStPreRechargeByCondition(sspvo));
        }
        catch(Exception e){
            logger.error("维修预收款余额信息    预加载失败", e);
        }
    }

    /**
     * 车辆信息 预加载
     */
    public void loadFrtCarInfo()
    {
        try{
        	sspvo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(stSellPerchargeService.loadFrtCarInfo(sspvo));
        }catch(Exception e){
            logger.error("车辆信息    预加载失败", e);
        }
    }

    /**
     * 车辆信息 综合查询
     */
    public void searchFrtCarInfoByCondition()
    {
        try{
        	sspvo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(stSellPerchargeService.searchFrtCarInfoByCondition(sspvo));
        }
        catch(Exception e){
            logger.error("车辆信息    综合查询失败", e);
        }
    }

    /**
     * 付款方式信息 预加载
     */
    public void loadPaidStyle()
    {
        try{
            super.writeJson(stSellPerchargeService.loadData(Contstants.PAIDWAY.PAIDWAYKRY));
        }
        catch(Exception e){
            logger.error("付款方式信息     预加载失败", e);
        }
    }

    /**
     * 车辆牌照信息 预加载，综合查询
     */
    public void findAllCarLicense()
    {
        try{
        	sspvo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(baseService.findAllCarLicense(sspvo.getQ(),sspvo.getEnterpriseId()));
        }catch(Exception e){
            logger.error("车辆牌照信息     预加载，综合查询失败", e);
        }
    }

    /**
     * 根据储备金汇总编号获取储备金明细信息
     */
    public void findStSellPerchargeById()
    {
        try{
            super.writeJson(stSellPerchargeService.findStSellPerchargeById(sspvo));
        }catch(Exception e){
            logger.error("根据储备金汇总编号获取储备金明细信息   异常", e);
        }
    }

    /**
     * 根据储备金汇总编号获取储备金明细信息
     */
    public void findPreStSellPerchargeById()
    {
        try{
            super.writeJson(stSellPerchargeService.findPreStSellPerchargeById(sspvo));
        }
        catch(Exception e){ 
        	logger.error("根据储备金汇总编号获取储备金明细信息   异常", e);
        }
    }

    /**
     * 储备金使用记录信息      预加载
     */
    public void loadStUsePercharge()
    {
        try{
        	sspvo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(stSellPerchargeService.loadStUsePercharge(sspvo));
        }catch(Exception e){
            logger.error("储备金使用记录信息     预加载失败", e);
        }
    }

    /**
     * 储备金使用记录信息     综合查询
     */
    public void serachStUsePerchargeByCondition()
    {
        try{
            super.writeJson(stSellPerchargeService.serachStUsePerchargeByCondition(sspvo));
        }catch(Exception e){
            logger.error("储备金使用记录信息     综合查询失败", e);
        }
    }

    /**
     * 预收款使用记录信息 预加载
     */
    public void loadPreStUsePercharge()
    {
        try{
        	sspvo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(stSellPerchargeService.loadPreStUsePercharge(sspvo));
        }
        catch(Exception e){
        	logger.error("预收款使用记录信息 预加载  异常", e);
        }
    }

    /**
     * 预收款使用记录信息     综合查询
     */
    public void serachPreStUsePerchargeByCondition()
    {
        try{
            super.writeJson(stSellPerchargeService.serachPreStUsePerchargeByCondition(sspvo));
        }catch(Exception e){
        	logger.error("预收款使用记录信息     综合查询    异常", e);
        }
    }

    public StSellPerchargeVo getModel(){
        return sspvo;
    }
}
