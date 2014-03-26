package com.syuesoft.st.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.st.service.StOutStRtPartsSearchService;
import com.syuesoft.st.vo.StOutVo;

@SuppressWarnings("serial")
@Action("StOutStRtPartsSearchAction")
public class StOutStRtPartsSearchAction extends BaseAction implements ModelDriven<StOutVo>{
	
    private StOutVo stOutVo=new StOutVo();
    @Autowired StOutStRtPartsSearchService stOutStRtPartsSearchService;
    private Logger logger = Logger.getLogger(this.getClass());
    
    /**
     * 出退单查询模块   出退单信息预加载 
     */
    public void loadStOutAndStRtPartsSearchInfo(){
    	try {
    		stOutVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stOutStRtPartsSearchService.loadStOutAndStRtPartsSearchInfo(stOutVo));
		} catch (Exception e) {
			logger.error("出退单查询模块   出退单信息预加载    异常", e);
		}
    }
	
    /**
     * 出退单明细  根据出退单时间获取
     */
    public void loadStOutAndStRtPartsDetailSearchInfo(){
    	try {
    		stOutVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stOutStRtPartsSearchService.loadStOutAndStRtPartsDetailSearchInfo(stOutVo));
		} catch (Exception e) {
			logger.error("出退单查询模块   出退单信息预加载    异常", e);
		}
    }
    
	
	public StOutVo getModel() {
		return stOutVo;
	}

}
