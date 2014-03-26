package com.syuesoft.frt.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.BasClaimsTypeDao;
import com.syuesoft.bas.dao.BasRepairTypeDao;
import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.dao.FrtResevationDao;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.model.BasClaimsType;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.BasRepairType;
import com.syuesoft.model.FrtRushToRepair;

@Service("frtService")
public class FrtServiceImpl extends BaseLogServiceImpl implements FrtService
{
    @Autowired
    private FrtResevationDao frtResevationDao;
    @Autowired
    private BasCompanyInformationSetService basCompanyInformationSetService;
    @Autowired
    private BasClaimsTypeDao basClaimsTypeDao;
    @Autowired
    private BasRepairTypeDao basRepairTypeDao;
    @Autowired
    private BaseService baseService;
    /**
     * 查找抢修信息
     * */
    
    public FrtRushToRepair findrushToRepair(String resvId) throws Exception
    {
        return frtResevationDao.findrushToRepair(resvId);
    }

    /**
     * 更新抢修信息
     * */
    
    public void updaterushToRepair(FrtRushToRepair frtRushToRepair)
            throws Exception
    {
        frtResevationDao.updaterushToRepair(frtRushToRepair);
    }

    /**
     * 查找默认索赔管理费率
     * */
    
    public Double findDefaultClaimsRate() throws Exception
    {
        BasCompanyInformationSet basCompanyInformationSet = basCompanyInformationSetService
                .getBasCompanyInformationSet(
                        Contstants.PARAMETER_SET.INDEMNITYS,
                        Contstants.INDEMNITYS.CLAIMMANGERRATE);
        Double findDefaultClaimsRate = 0.00d;
        if (basCompanyInformationSet != null
                && basCompanyInformationSet.getCiValue() != null
                && basCompanyInformationSet.getCiValue().length() > 0)
        {
            findDefaultClaimsRate = Double.parseDouble(basCompanyInformationSet
                    .getCiValue());
        }
        return findDefaultClaimsRate;
    }

    /**
     * 查找默认索赔厂商
     * */
    
    public String findDefaultClaimsCompany() throws Exception
    {
        BasCompanyInformationSet basCompanyInformationSet = basCompanyInformationSetService
                .getBasCompanyInformationSet(
                        Contstants.PARAMETER_SET.INDEMNITYS,
                        Contstants.INDEMNITYS.CLAIMMANUCODE);
        String findDefaultClaimsCompany = null;
        if (basCompanyInformationSet != null
                && basCompanyInformationSet.getCiValue() != null
                && basCompanyInformationSet.getCiValue().length() > 0)
        {
            findDefaultClaimsCompany = basCompanyInformationSet.getCiValue();
        }
        return findDefaultClaimsCompany;
    }
    /**
     * 获取默认提醒参数
     * */
	
	public String findDefaultReceptionClew() throws Exception {
		// TODO Auto-generated method stub
		BasCompanyInformationSet basCompanyInformationSet = basCompanyInformationSetService
		        .getBasCompanyInformationSet(
		                Contstants.PARAMETER_SET.STGCARPARA,
		                Contstants.STGCARPARA.FRTRECEPTIONCLEW);
		String findDefaultReceptionClew = null;
		if (basCompanyInformationSet != null
		        && basCompanyInformationSet.getCiValue() != null
		        && basCompanyInformationSet.getCiValue().length() > 0)
		{
			findDefaultReceptionClew = basCompanyInformationSet.getCiValue();
		}
		return findDefaultReceptionClew;
	}
	/**
	 * 查找默认收款查询时间段
	 * */
	
	public String findDefaultBalanceTimeSect() throws Exception {
		BasCompanyInformationSet basCompanyInformationSet = basCompanyInformationSetService
			        .getBasCompanyInformationSet(
			                Contstants.PARAMETER_SET.OTHERPARAMETER,
			                Contstants.OTHERPARAMETER.BALANCETIMESECT);
			String fidnDefaultBalanceTimeSect = null;
			if (basCompanyInformationSet != null
			        && basCompanyInformationSet.getCiValue() != null
			        && basCompanyInformationSet.getCiValue().length() > 0)
			{
				fidnDefaultBalanceTimeSect = basCompanyInformationSet.getCiValue();
			}
			return fidnDefaultBalanceTimeSect;
	}
	/**
	 * 查找默认结算查询时间段
	 * */
	
	public String findDefaultPreclrTimeSect() throws Exception {
		BasCompanyInformationSet basCompanyInformationSet = basCompanyInformationSetService
		        .getBasCompanyInformationSet(
		                Contstants.PARAMETER_SET.OTHERPARAMETER,
		                Contstants.OTHERPARAMETER.BALANACCOUNTSECT);
		String findDefaultPreclrTimeSect = null;
		if (basCompanyInformationSet != null
		        && basCompanyInformationSet.getCiValue() != null
		        && basCompanyInformationSet.getCiValue().length() > 0)
		{
			findDefaultPreclrTimeSect = basCompanyInformationSet.getCiValue();
		}
		return findDefaultPreclrTimeSect;
	}
	/**
	 * 查找默认车间完工检查参数
	 * */
	
	public String findDefaultWorkShopVal() throws Exception {
		return baseService.findDefaultProperties(Contstants.PARAMETER_SET.OTHERPARAMETER,Contstants.OTHERPARAMETER.WORKSHOPVALIDATE);
	}
	 /**
     * 默认索赔性质
     * */
    public String getDefaultClaimsType(Integer enterpriseId)
    {
        BasCompanyInformationSet basCompanyInformationSet = basCompanyInformationSetService
                .getBasCompanyInformationSet(
                        Contstants.PARAMETER_SET.EVALUATESE,
                        Contstants.EVALUATESE.CHANGECLAIMNATURE,enterpriseId);
        if(basCompanyInformationSet!=null){
        	return basCompanyInformationSet.getCiValue();
        }else{
        	return null;
        }
        
    }
    /**
     * 默认收费性质
     * */
    public String getDefaultRepairType(Integer enterpriseId)
    {
        BasCompanyInformationSet basCompanyInformationSet = basCompanyInformationSetService
                .getBasCompanyInformationSet(
                        Contstants.PARAMETER_SET.EVALUATESE,
                        Contstants.EVALUATESE.CHANGECOLLECTNATURE,enterpriseId);
        return basCompanyInformationSet.getCiValue();
    }
    /**
     * 查找默认索赔性质名称
     * */
	
	public String getDefaultClaimsTypeName(Integer enterpriseId) throws Exception {
		String id=getDefaultClaimsType(enterpriseId);
		if(id!=null){
			BasClaimsType bct=basClaimsTypeDao.get("from BasClaimsType bct where bct.claimsId="+id+"");
			if(bct!=null)
				return bct.getClaimsName();
			return null;
		}
		return null;
	}
	/**
	 * 查找默认收费性质名称
	 * */
	
	public String getDefaultRepairTypeName(Integer enterpriseId) throws Exception {
		String id=getDefaultRepairType(enterpriseId);
		if(id!=null){
			BasRepairType brt=basRepairTypeDao.get("from BasRepairType brt where brt.reptypId="+id+"");
			if(brt!=null)
				return brt.getReptypName();
			return null;			
		}
		return null;
	}
	/**
	 * 查找默认保养公里数
	 * */
	
	public String getDefaultMaintDistance() throws Exception {
		return baseService.findDefaultProperties(Contstants.PARAMETER_SET.JOINCARACC, Contstants.JOINCARACC.MAINTAINCOURSE);
	}
	/**
	 * 查找默认最大保养间隔天数
	 * */
	
	public String getDefaultMaintDays() throws Exception {
		return baseService.findDefaultProperties(Contstants.PARAMETER_SET.JOINCARACC, Contstants.JOINCARACC.MAINTAINSPACEDAY);
	}
	/**
	 * 查找默认接车分部
	 * */
	
	public String getDefaultRcptBranch() throws Exception {
			return baseService.findDefaultProperties(Contstants.PARAMETER_SET.STGCARPARA, Contstants.STGCARPARA.DEFAULTRCPTBRANCH);
	}
	/**
	 * 查找默认日经营情况查询成本查询是否含税
	 * */
	
	public String getDefaultwhetherTax() throws Exception {
			return baseService.findDefaultProperties(Contstants.PARAMETER_SET.FINANCEMANAGE, Contstants.FINANCEMANAGE.WHETHERTAX);
	}
	/**
	 * 指定维修类别首保项
	 * */
	
	public String getDefaultFirstmaintain() throws Exception {
			return baseService.findDefaultProperties(Contstants.PARAMETER_SET.OTHERPARAMETER, Contstants.OTHERPARAMETER.FIRSTMAINTAIN);
	}
	/**
	 * 查找预约估价单配件价格选择
	 * */
	
	public String getDefaultBillPrictType(Integer enterpriseId) throws Exception {
		return baseService.findDefaultProperties(Contstants.PARAMETER_SET.EVALUATESE, Contstants.EVALUATESE.BILLPRICTTYPE,enterpriseId);
	}
	/**
	 * 查找接车单默认完工工时(分钟)
	 * */
	
	public String getDefaultFinishTimes() throws Exception {
		return baseService.findDefaultProperties(Contstants.PARAMETER_SET.STGCARPARA, Contstants.STGCARPARA.FAINSHTIMES);
	}
	/**
	 * 查找维修管理(辅料费)费率
	 * */
	
	public String getDefaultServiceRate() throws Exception {
		return baseService.findDefaultProperties(Contstants.PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.ACCESSORIESRATE);
	}
	/**
	 * 查找维修管理(辅料费)方式
	 * */
	
	public String getDefaultServiceWay() throws Exception {
		return baseService.findDefaultProperties(Contstants.PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.ACCESSORIESWAY);
	}

}
