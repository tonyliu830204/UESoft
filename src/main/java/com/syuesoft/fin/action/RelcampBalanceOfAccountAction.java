package com.syuesoft.fin.action;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.service.RelcampBalanceOfAccountService;
import com.syuesoft.fin.vo.RelcampBalanceOfAccountVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;

@SuppressWarnings("serial")
@ParentPackage(value = "basePackage")
@Action("RelcampBalanceOfAccountAction")
public class RelcampBalanceOfAccountAction extends BaseAction implements
        ModelDriven<RelcampBalanceOfAccountVo>
{

    RelcampBalanceOfAccountVo relcampBalanceOfAccountVo = new RelcampBalanceOfAccountVo();

    @Autowired RelcampBalanceOfAccountService relcampBalanceOfAccountService;

    @Autowired BaseService baseService;

    private Logger logger = Logger.getLogger(this.getClass());
    
    /**
     * 点击选择供应商加载对应供应商对账汇总信息
     */
    public void loadRelcampMain(){
        try{
            super.writeJson(relcampBalanceOfAccountService
            		.loadRelcampMain(relcampBalanceOfAccountVo));
        }catch(Exception e){
        	logger.error("点击选择供应商加载对应供应商对账汇总信息   异常", e);
        }
    }

    /**
     * 供应商信息 预加载
     */
    public void loadRelcamp(){
        try{
            super.writeJson(relcampBalanceOfAccountService
                    .findAllRelationCampany(relcampBalanceOfAccountVo.getQ(),
                            false,getNowEnterpriseId()));
        }catch(Exception e){
        	logger.error("供应商信息     预加载   异常", e);
        }
    }

    /**
     * 财务模块 供应商对账 入退单信息加载
     */
    public void loadStRtGoods(){
        try{
            super.writeJson(relcampBalanceOfAccountService.loadStRtGoods(relcampBalanceOfAccountVo));
        }
        catch(Exception e){
        	logger.error("财务模块 供应商对账 入退单信息加载   异常", e);
        }
    }

    /**
     * 根据入退单号获取供应商应付账款及剩余账款
     */
    public void searchByStVendorAccount(){
        try{
            super.writeJson(relcampBalanceOfAccountService.searchByStVendorAccount(relcampBalanceOfAccountVo));
        }
        catch(Exception e){
        	logger.error("根据入退单号获取供应商应付账款及剩余账款   异常", e);
        }
    }

    /**
     * 供应商对账单信息 添加
     */
    public void add(){
        Msg msg = new Msg();
        try{
        	BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
    		if(user!=null&&!user.equals(""))
    			relcampBalanceOfAccountVo.setStfId(user.getBasStuff().getStfId().toString());
            relcampBalanceOfAccountService.add(relcampBalanceOfAccountVo);
            msg.setSuccess(true);
            super.writeJson(msg);
        }catch(Exception e){
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 供应商对账单信息 删除
     */
	public void delete() {
		Msg msg = new Msg();
		try {
			relcampBalanceOfAccountService.delete(relcampBalanceOfAccountVo);
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			msg.setSuccess(false);
			super.writeJson(msg);
			logger.error("供应商对账单信息   删除  异常", e);
		}
	}

    /**
     * 供应商对账单明细信息 加载
     */
	public void loadStVendorAccount() {
		try {
			super.writeJson(relcampBalanceOfAccountService
					.loadStVendorAccount(relcampBalanceOfAccountVo));
		} catch (Exception e) {
			logger.error("供应商对账单明细信息   加载  异常", e);
		}
	}

    /**
     * 供应商对账单汇总 预加载
     */
    public void loadStVendorAccountMain(){
        try{
            List<RelcampBalanceOfAccountVo> list = relcampBalanceOfAccountService
                    .loadStVendorAccountMain(relcampBalanceOfAccountVo);
            Json json = new Json();
            json.setRows(list);
            json.setTotal(list.size());
            super.writeJson(relcampBalanceOfAccountService.loadStVendorAccountMain(relcampBalanceOfAccountVo));
        }catch(Exception e){
        	logger.error("供应商对账单汇总 预加载 异常", e);
        }
    }

    /**
     * 供应商未对账汇总 预加载
     */
	public void loadNoPaidStVendorAccountMain() {
		try {
			relcampBalanceOfAccountVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(relcampBalanceOfAccountService
					.loadNoPaidStVendorAccountMain(relcampBalanceOfAccountVo));
		} catch (Exception e) {
			logger.error("供应商未对账汇总  预加载   异常", e);
		}
	}

    
    public RelcampBalanceOfAccountVo getModel()
    {
        return relcampBalanceOfAccountVo;
    }

}
