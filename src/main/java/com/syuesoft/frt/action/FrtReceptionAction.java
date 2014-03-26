package com.syuesoft.frt.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.contstants.Contstants.STGCARPARA;
import com.syuesoft.frt.service.FrtReceptionService;
import com.syuesoft.frt.service.FrtResevationService;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.util.Msg;

@SuppressWarnings("serial")
@ParentPackage(value = "basePackage")
@Action("frtReceptionAction")
public class FrtReceptionAction extends BaseAction implements
        ModelDriven<FrtReceptionVo>
{
    private static final Logger logger = Logger.getLogger(FrtReceptionAction.class);
    FrtReceptionVo freVo = new FrtReceptionVo();
    @Autowired private FrtReceptionService frtReceptionService;
    @Autowired private FrtResevationService frtResevationService;
    
    /**
     * 前台接车汇总datagrid
     */
    public void loadFrtReception()
    {
        try{
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtReceptionService.loadFrtReception(freVo));
        }catch(Exception e){
            logger.error("前台接车汇总datagrid失败！", e);
        }
    }
    
    /**
     * 前台接车汇总datagrid
     */
    public void datagridReception()
    {
        try{
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtReceptionService.datagridReception(freVo));
        }catch(Exception e){
            logger.error("前台接车汇总datagrid失败！", e);
        }
    }

    /**
     * 查询维修档案
     * */
    public void findServiceReception()
    {
        try
        {
            super.writeJson("");
            // logger.info("查询维修档案成功！");
        }
        catch(Exception e)
        {
            logger.error("查询维修档案失败！", e);
        }
    }

    /**
     * 新增接车单
     */
    public void save()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            Boolean flag = frtReceptionService.isDistanceTrue(freVo);
            if (flag != null && flag == true){
                Msg msg = new Msg();
                msg.setMsg("对不起，本次里程数不能小于上次里程数！");
                super.writeJson(msg);
            }else{
                super.writeJson(frtReceptionService.save(freVo));
            }
        }catch(Exception e){
            logger.error("新增接车单失败！", e);
        }

    }

    /**
     * 删除接车单
     */
    public void remove()
    {
        try{
            super.writeJson(frtReceptionService.remove(freVo.getReceptionId()));
        }
        catch(Exception e){
            logger.error("删除接车单失败！", e);
        }
    }

    @Autowired BasCompanyInformationSetService basCompanyInformationSetService;
    /**
     * 更新接车单
     */
    public void edit(){
        try
        {
        	String ciVlaue=basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.STGCARPARA,STGCARPARA.DISTANCELOSSORI ,getNowEnterpriseId()).getCiValue();
        	freVo.setEnterpriseId(getNowEnterpriseId());
        	if(ciVlaue!=null&&ciVlaue.equals("checked")){
            	Boolean flag = frtReceptionService.isDistanceTrue(freVo);
                if (flag == true){
                    Msg msg = new Msg();
                    msg.setMsg("对不起，本次里程数不能小于上次里程数！");
                    super.writeJson(msg);
                }
                else
                    super.writeJson(frtReceptionService.edit(freVo));
            }else
            	 super.writeJson(frtReceptionService.edit(freVo));
            // logger.info("更新接车单成功！");
        }
        catch(Exception e){
            logger.error("更新接车单失败！", e);
        }
    }

    /**
     * 从数据库中查询维修配件
     */
    public void findPartsByRcptId()
    {
        try
        {
            super.writeJson(frtReceptionService.findPartsByRcptId(freVo
                    .getReceptionId()));
            // logger.info("数据库中查询维修配件成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("数据库中查询维修配件失败！", e);
        }
    }

    /**
     * 从数据库中查询预约维修配件
     */
    public void findPartsByResvId()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtResevationService.findPartsByResvId(freVo
                    .getResvId(),freVo.getEnterpriseId()));
            // logger.info("数据库中查询预约维修配件成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("数据库中查询预约维修配件失败！", e);
        }
    }

    /**
     * 从数据库中查询维修项目
     */
    public void findItemByRcptId()
    {
        try
        {
            super.writeJson(frtReceptionService.findItemByRcptId(freVo
                    .getReceptionId()));
            // logger.info("数据库中查询维修项目成功！");
        }
        catch(Exception e)
        {
            logger.error("数据库中查询维修项目失败！", e);
        }
    }

    /**
     * 从数据库中查询预约维修项目
     */
    public void findItemByResvId()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtResevationService.findItemByResvId(freVo
                    .getResvId(),freVo.getEnterpriseId()));
            // logger.info("数据库中查询预约维修项目成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("数据库中查询预约维修项目失败！", e);
        }
    }

    /**
     * 从数据库中查询其他费用明细
     */
    public void findCostByRcptId()
    {
        try
        {
            super.writeJson(frtReceptionService.findCostByRcptId(freVo
                    .getReceptionId()));
            // logger.info("数据库中查询其他费用明细成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("数据库中查询其他费用明细失败！", e);
        }
    }

    /** 增加其他费用 */
    public void addFrtReceptionCost()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtReceptionService.addFrtReceptionCost(freVo));
            // logger.info("增加其他费用成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("增加其他费用失败！", e);
        }
    }

    /**
     * 从数据库查询车身状况
     */
    public void findvehicleStructureList()
    {
        try
        {
            super.writeJson(frtReceptionService.findVehicleStructure(freVo));
            // logger.info("数据库查询车身状况成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("数据库查询车身状况失败！", e);
        }
    }

    /**
     * 根据预约/保险估价单编号查找相应的车辆信息
     */
    public void findvehicleStructureListByResvId()
    {
        try
        {
            super.writeJson(frtReceptionService
                    .findvehicleStructureListByResvId(freVo));
            // logger.info("session中查询车身状况成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("session中查询车身状况失败！", e);
        }
    }

    /** 前台接车转车间 */
    public void transFormPlant()
    {
        try
        {
            super.writeJson(frtReceptionService.modifyTransFormPlant(freVo));
            // logger.info("前台接车转车间成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("前台接车转车间失败！", e);
        }
    }

    /** 前台接车转结算 */
    public void transFormBalanace()
    {
        try
        {
            super.writeJson(frtReceptionService.modifyTransFormBalanace(freVo));
            // logger.info("前台接车转结算成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("前台接车转结算失败！", e);
        }
    }

    /** 增加维修建议 */
    public void addFrtResvAdvice()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtReceptionService.addFrtResvAdvice(freVo));
            // logger.info("增加维修建议成功！");
        }
        catch(Exception e)
        {
            logger.error("增加维修建议失败！", e);
        }
    }

    /** 更新维修建议 */
    public void updateFrtResvAdvice()
    {
        try
        {
            super.writeJson(frtReceptionService.updateFrtResvAdvice(freVo));
            // logger.info("更新维修建议成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("更新维修建议失败！", e);
        }
    }

    /** 删除维修建议 */
    public void deleteFrtResvAdvice()
    {
        try
        {
            super.writeJson(frtReceptionService.deleteFrtResvAdvice(freVo));
            // logger.info("删除维修建议成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("删除维修建议失败！", e);
        }
    }

    /** 查找维修建议 */
    public void findFrtResvAdviceByCarId()
    {
        try
        {
            super.writeJson(frtReceptionService.findFrtResvAdviceByCarId(freVo));
            // logger.info("查找维修建议成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查找维修建议失败！", e);
        }
    }

    /** 计算费用总和 */
    public void totemoney()
    {
        try
        {
            super.writeJson(frtReceptionService.totemoney(freVo));
            // logger.info("计算费用总和成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("计算费用总和失败！", e);
        }
    }

    /**
     * 增加自定义维修项目
     * */
    public void addFrtReceptionItem()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtReceptionService.addFrtReceptionItem(freVo));
        }
        catch(Exception e)
        {
            logger.error("增加自定义维修项目失败！", e);
        }
    }

    /**
     * 查找维修履历
     * */
    public void serviceRecord()
    {
        try
        {
            super.writeJson(frtReceptionService.serviceRecord(freVo));
        }
        catch(Exception e)
        {
            logger.error("增加自定义维修项目失败！", e);
        }
    }

    /**
     * 查找维修套餐
     * */
    public void serviceWeave()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtReceptionService.selectServiceWeave(freVo));
        }
        catch(Exception e)
        {
            logger.error("查找维修套餐失败！", e);
        }
    }

    /**
     * 查询套餐项目并添加到接车单中
     */
    public void findItemListByRpId()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtReceptionService.findItemListByRpId(freVo));
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询套餐项目并添加到接车单中失败！", e);
        }
    }

    /**
     * 查询套餐配件并添加到接车单中
     */
    public void findPartsListByRpId()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtReceptionService.findPartsListByRpId(freVo));
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询套餐配件并添加到接车单中失败！", e);
        }

    }
    
    /**
     * 更改里程数
     * */
    public void modifyDistance()
    {
        try
        {
            super.writeJson(frtReceptionService.modifyDistance(freVo));
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("更改里程数失败！", e);
        }
    }

    /**
     * 获取车档案里程数
     * */
    public void findCarDistance()
    {
        try
        {
            super.writeJson(frtReceptionService.findCarDistance(freVo));
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("获取车档案里程数失败！", e);
        }
    }
    /**
     * 查询前台接车提醒信息
     * */
    public void findLastService(){
    	try {
    		super.writeJson(frtReceptionService.findLastService(freVo));
		} catch (Exception e) {
			logger.error("查询前台接车提醒信息失败！", e);
		}
    }
    /**
     * 查询车辆预约信息
     * */
    public void bespeakClew(){
    	try {
    		super.writeJson(frtReceptionService.bespeakClew(freVo));
		} catch (Exception e) {
			logger.error("查询车辆预约信息失败！", e);
		}
    }
    

    /**
     * 完工分析查询
     */
    public void completeAnalyse()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtReceptionService.completeAnalyse(freVo));
        }
        catch(Exception e)
        {
            logger.error("完工分析查询失败！", e);
        }
    }
    /**
     * 接车分部管理查询
     * */
    public void rcptBranchDatagrid(){
    	try {
    		freVo.setEnterpriseId(getNowEnterpriseId());
    		 super.writeJson(frtReceptionService.rcptBranchDatagrid(freVo));
		} catch (Exception e) {
			 logger.error("接车分部管理查询失败！", e);
		}
    }
    /**
     * 接车分部管理修改
     * */
    public void editRcptBranch(){
    	try {
    		 super.writeJson(frtReceptionService.editRcptBranch(freVo));
		} catch (Exception e) {
			 logger.error("接车分部管理修改失败！", e);
		}
    }
    /**
     * 前台接车接待员业务统计
     */
    public void loadSearchReceptorWork(){
    	 try{
             super.writeJson(frtReceptionService.loadSearchReceptorWork(freVo));
         }catch(Exception e){
             logger.error("前台接车接待员业务统计失败！", e);
         }
    }
    /**
     *  设置前台预计完工时间
     */
    public void getSetFinishTime(){
    	 try{
             super.writeJson(frtReceptionService.getSetFinishTime(freVo));
         }catch(Exception e){
             logger.error("设置前台预计完工时间失败！", e);
         }
    }
    
    public FrtReceptionVo getModel()
    {
        return freVo;
    }
}
