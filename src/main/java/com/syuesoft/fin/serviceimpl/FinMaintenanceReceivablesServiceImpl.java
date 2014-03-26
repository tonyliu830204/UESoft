package com.syuesoft.fin.serviceimpl;

import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;

import com.syuesoft.fin.dao.FinClaimsReceivablesDao;
import com.syuesoft.fin.dao.FinMaintenanceReceivablesDao;
import com.syuesoft.fin.dao.StSellChargeDao;
import com.syuesoft.fin.service.FinMaintenanceReceivablesService;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.MaintenanceReceivablesVo;

/**
 * 维修应收款,索赔应收款,索赔应收款ServiceImpl
 * 
 * @author SuMing
 */
public class FinMaintenanceReceivablesServiceImpl implements
        FinMaintenanceReceivablesService
{

    @Autowired
    FinMaintenanceReceivablesDao finMaintenanceReceivablesDao;

    @Autowired
    FinClaimsReceivablesDao finClaimsReceivablesDao;

    @Autowired
    StSellChargeDao stSellChargeDao;

    /**
     * 维修应收款信息 预加载
     */
    
    public Datagrid loadFinMaintenanceReceivables(
            MaintenanceReceivablesVo mrvo) throws Exception
    {
        return finMaintenanceReceivablesDao.loadFinMaintenanceReceivables(mrvo
                .getPage(), mrvo.getRows(),mrvo.getPreclrTimeStart(),mrvo.getPreclrDateEnd(),mrvo.getEnterpriseId());
    }

    /**
     * 维修应收款信息 综合查询
     */
    
    public List<MaintenanceReceivablesVo> searchFinMaintenanceReceivablesByCondition(
            MaintenanceReceivablesVo mrvo) throws Exception
    {
        return finMaintenanceReceivablesDao
                .searchFinMaintenanceReceivablesByCondition(mrvo
                        .getPreclrTimeStart(), mrvo.getPreclrTimeEnd(), mrvo
                        .getPreclrSumAmount(), mrvo.getPreFlg(), mrvo
                        .getStfId(), mrvo.getResvRealTimeStart(), mrvo
                        .getResvRealTimeEnd(), mrvo.getReceptionId(), mrvo
                        .getCustomName(), mrvo.getCarLicense(), mrvo
                        .getCarVin(), mrvo.getCarBrand());
    }

    /**
     * 索赔应收款信息 预加载
     */
    
    public Datagrid loadFinClaimsReceivables(
            MaintenanceReceivablesVo mrvo) throws Exception
    {
        return finClaimsReceivablesDao.loadFinClaimsReceivables(mrvo.getPage(),
                mrvo.getRows(),mrvo.getClaimantmTimeStart(),mrvo.getClaimantmTimeEnd(),mrvo.getEnterpriseId());
    }

    /**
     * 索赔应收款信息 综合查询
     */
    
    public List<MaintenanceReceivablesVo> searchFinClaimsReceivablesByCondition(
            MaintenanceReceivablesVo mrvo) throws Exception
    {
        return finClaimsReceivablesDao
                .searchFinClaimsReceivablesByCondition(mrvo
                        .getClaimantmTimeStart(), mrvo.getClaimantmTimeEnd(),
                        mrvo.getClaimantmAmount(), mrvo.getStfName(), mrvo
                                .getResvRealTimeStart(), mrvo
                                .getResvRealTimeEnd(), mrvo.getReceptionId(),
                        mrvo.getRelcampName(), mrvo.getReceLicense(), mrvo
                                .getCarVin(), mrvo.getCbrdName());
    }

    /**
     * 销售应收款信息 预加载
     */
    
    public Datagrid loadStSellCharge(
            MaintenanceReceivablesVo mrvo) throws Exception
    {
        return stSellChargeDao.loadStSellCharge(mrvo.getPage(), mrvo.getRows(),mrvo.getSellmmDateStart(),mrvo.getSellmmDateStart(),mrvo.getEnterpriseId());
    }

    /**
     * 销售应收款信息 综合查询
     */
    
    public List<MaintenanceReceivablesVo> searchStSellChargeByCondition(
            MaintenanceReceivablesVo mrvo) throws Exception
    {
        return stSellChargeDao.searchStSellChargeByCondition(mrvo
                .getSellmmDateStart(), mrvo.getSellmmDateEnd(), mrvo
                .getPreclrSumAmount(), mrvo.getCarBrand(), mrvo
                .getPreclrDateStart(), mrvo.getPreclrDateEnd(),
                mrvo.getCerNo(), mrvo.getCustomName(), mrvo.getCarLicense(),
                mrvo.getCarVin());
    }

}
