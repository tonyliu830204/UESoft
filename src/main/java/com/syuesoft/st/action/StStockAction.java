package com.syuesoft.st.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.st.service.StStockService;
import com.syuesoft.st.vo.StockPartsVo;

/**
* @ClassName: StStockAction 
* @Description: TODO(进销存) 
* @author HeXin 
* @date 2013-9-27 下午04:58:30 
* @version 1.0
 */
@ParentPackage(value="basePackage")
@Action("stStockAction")
public class StStockAction extends BaseAction implements ModelDriven<StockPartsVo>
{
    private Logger logger = Logger.getLogger(this.getClass());
    private static final long serialVersionUID = 1L;
    private StockPartsVo stockPartsVo = new StockPartsVo();
    @Autowired
    private StStockService stStockService;
    
    public void getStockSummary(){
        try {
        	stockPartsVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(stStockService.loadStockSummary(stockPartsVo));
        } catch (Exception e) {
            logger.error("进销存预加载    异常", e);
        }
    }
    
    public void getStockDetails(){
        try {
        	stockPartsVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(stStockService.loadStock(stockPartsVo));
        } catch (Exception e) {
            logger.error("进销存明细加载 异常", e);
        }
    }
    
    public StockPartsVo getModel()
    {
        return stockPartsVo;
    }
}
