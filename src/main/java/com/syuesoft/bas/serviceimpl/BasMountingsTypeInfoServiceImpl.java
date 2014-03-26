package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasMountingsTypeInfoDAO;
import com.syuesoft.bas.dao.BasPartsBrandDAO;
import com.syuesoft.bas.dao.BaseCarTypeDAO;
import com.syuesoft.bas.dao.DefaultDAO;
import com.syuesoft.bas.service.BasMountingsTypeInfoService;
import com.syuesoft.base.vo.BasMountingsTypeInfo;
import com.syuesoft.model.BasCarType;
import com.syuesoft.model.BasPartsBrand;
import com.syuesoft.model.BasPartsType;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Json;

/**
 * 配件型号资料service
 * 
 * @author Liujian
 * 
 */
@Transactional
@Service("basMountingsTypeInfoService")
public class BasMountingsTypeInfoServiceImpl extends BaseLogServiceImpl
        implements BasMountingsTypeInfoService
{

    private BasMountingsTypeInfoDAO basMountingsTypeInfoDAO;

    private DefaultDAO defaultDao;

    private BasPartsBrandDAO basPartsBrandDAO;

    public boolean isExist(BasMountingsTypeInfo mti) throws Exception
    {
        String param = "";
        if (mti.getPtypeId() != null && !"".equals(mti.getPtypeId()))
            param += " and bpt.ptypeId!=" + mti.getPtypeId();
        if (mti.getPbrdId() != null && !"".equals(mti.getPbrdId()))
            param += " and bpt.basPartsBrand.pbrdId=" + mti.getPbrdId();
        if (mti.getPtypeName() != null && !"".equals(mti.getPtypeName()))
            param += " and bpt.ptypeName='" + mti.getPtypeName() + "'";
        List<BasCarType> list = baseCarTypeDAO
                .find("FROM BasPartsType  bpt WHERE bpt.enterpriseId="+mti.getEnterpriseId()+" " + param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增配件型号资料", content = "基础资料-->新增配件型号资料")
    public void save(BasMountingsTypeInfo mti) throws Exception
    {
        BasPartsType pt = new BasPartsType();
        Short maxId = basMountingsTypeInfoDAO.getMaxId(mti.getPbrdId());
        Short ptypeId = 0;
        if (maxId != null)
            ptypeId = (short) (maxId + 1);
        else
            ptypeId = new Short(mti.getPbrdId().toString() + "01");
        pt.setPtypeId(ptypeId);
        pt.setPtypeName(mti.getPtypeName());
        BasPartsBrand basPartsBrand = new BasPartsBrand();
        basPartsBrand.setPbrdId(mti.getPbrdId());
        pt.setBasPartsBrand(basPartsBrand);
        if (mti.getRepairRate() == null || mti.getRepairRate().equals(""))
            pt.setRepairRate(1.0);
        else
            pt.setRepairRate(mti.getRepairRate());
        if (mti.getSellRate() == null || mti.getSellRate().equals(""))
            pt.setSellRate(1.0);
        else
            pt.setSellRate(mti.getSellRate());
        if (mti.getPointRate() == null || mti.getPointRate().equals(""))
            pt.setPointRate(1.0);
        else
            pt.setPointRate(mti.getPointRate());
        if (mti.getSpecialRate() == null || mti.getSpecialRate().equals(""))
            pt.setSpecialRate(1.0);
        else
            pt.setSpecialRate(mti.getSpecialRate());
        if (mti.getClaimRate() == null || mti.getClaimRate().equals(""))
            pt.setClaimRate(1.0);
        else
            pt.setClaimRate(mti.getClaimRate());
        pt.setEnterpriseId(mti.getEnterpriseId());
        Serializable bb = basMountingsTypeInfoDAO.save(pt);
        setContent("基础资料-->新增配件型号资料,配件型号资料编号:" + bb);
    }

    
    @Log(moduleName = "基础资料", opertype = "删除配件型号资料", content = "基础资料-->删除配件型号资料")
    public void delete(Short id)throws Exception
    {
    	BasPartsType pt = basMountingsTypeInfoDAO.get(BasPartsType.class,id);
    	if(pt!=null){
    		basMountingsTypeInfoDAO.delete(pt);
            setContent("基础资料-->删除配件型号资料,配件型号资料编号:" + id);
    	}
    }

    
    @Log(moduleName = "基础资料", opertype = "修改配件型号资料", content = "基础资料-->修改配件型号资料")
    public void update(BasMountingsTypeInfo mti)throws Exception
    {
        BasPartsType pt = basMountingsTypeInfoDAO.get(BasPartsType.class, mti.getPtypeId());
        if(pt!=null){
        	 pt.setPtypeName(mti.getPtypeName());
             BasPartsBrand basPartsBrand = basPartsBrandDAO.get(BasPartsBrand.class,mti.getPbrdId());
             if(basPartsBrand!=null)
                 pt.setBasPartsBrand(basPartsBrand);
             if (mti.getRepairRate() == null || mti.getRepairRate().equals(""))
                 pt.setRepairRate(1.0);
             else
                 pt.setRepairRate(mti.getRepairRate());
             if (mti.getSellRate() == null || mti.getSellRate().equals(""))
                 pt.setSellRate(1.0);
             else
                 pt.setSellRate(mti.getSellRate());
             if (mti.getPointRate() == null || mti.getPointRate().equals(""))
                 pt.setPointRate(1.0);
             else
                 pt.setPointRate(mti.getPointRate());
             if (mti.getSpecialRate() == null || mti.getSpecialRate().equals(""))
                 pt.setSpecialRate(1.0);
             else
                 pt.setSpecialRate(mti.getSpecialRate());
             if (mti.getClaimRate() == null || mti.getClaimRate().equals(""))
                 pt.setClaimRate(1.0);
             else
                 pt.setClaimRate(mti.getClaimRate());
             basMountingsTypeInfoDAO.merge(pt);
             setContent("基础资料-->修改配件型号资料,配件型号资料编号:" + pt.getBasPartsBrand().getPbrdId());
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Json findAll(BasMountingsTypeInfo mti)
    {
        String param = "";
        if (mti.getPtypeId() != null && !"".equals(mti.getPtypeId()))
            param += " and pt.ptypeId like '%" + StringEscapeUtils.escapeSql(mti.getPtypeId().toString().trim()) + "%'";
        if (mti.getPbrdId() != null && !"".equals(mti.getPbrdId()))
            param += " and pb.pbrdId = '" + mti.getPbrdId() + "'";
        if (mti.getPtypeName() != null && !"".equals(mti.getPtypeName().trim()))
            param += " and pt.ptypeName like '%" + StringEscapeUtils.escapeSql(mti.getPtypeName().trim()) + "%'";
        param += "and pt.enterpriseId="+mti.getEnterpriseId();
        if (mti.getSort() != null && !"".equals(mti.getSort())&& mti.getOrder() != null && !"".equals(mti.getOrder()))
        {
            String sort = "pt";
            if ("pbrdId".equals(mti.getSort()))
                sort = "pt.basPartsBrand";
            param += " order by " + sort + "." + mti.getSort() + " "
                    + mti.getOrder();
        }
       
        Json json = new Json();
        json.setTotal(basMountingsTypeInfoDAO.findAll(param).size());
        json.setRows(basMountingsTypeInfoDAO.findAll(param, mti.getPage(), mti
                .getRows()));
        return json;
    };

    /**
     * 查询配件品牌
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @SuppressWarnings("unchecked")
    
    public List findAllPartsBrand(BasMountingsTypeInfo mti)
    {
        String param = "";
        if (mti.getQ() != null && !"".equals(mti.getQ().trim())){
            param += " and bpb.pbrdName like '%" + StringEscapeUtils.escapeSql(mti.getQ().trim()) + "%'";
        }

        List list = new ArrayList();
        List<BasPartsBrand> bpbList = defaultDao.getObjList("from BasPartsBrand bpb where 1 = 1  and  bpb.enterpriseId="+mti.getEnterpriseId()  + param);
        for (BasPartsBrand pb : bpbList){
            ComboBoxJson json = new ComboBoxJson();
            json.setId(pb.getPbrdId().toString());
            json.setText(pb.getPbrdName());
            list.add(json);
        }
        return list;
    }
    @Autowired
    private BaseCarTypeDAO baseCarTypeDAO;

    public BasMountingsTypeInfoDAO getBasMountingsTypeInfoDAO()
    {
        return basMountingsTypeInfoDAO;
    }

    @Autowired
    public void setBasMountingsTypeInfoDAO(
            BasMountingsTypeInfoDAO basMountingsTypeInfoDAO)
    {
        this.basMountingsTypeInfoDAO = basMountingsTypeInfoDAO;
    }

    public DefaultDAO getDefaultDao()
    {
        return defaultDao;
    }

    @Autowired
    public void setDefaultDao(DefaultDAO defaultDao)
    {
        this.defaultDao = defaultDao;
    }

    public BasPartsBrandDAO getBasPartsBrandDAO()
    {
        return basPartsBrandDAO;
    }

    @Autowired
    public void setBasPartsBrandDAO(BasPartsBrandDAO basPartsBrandDAO)
    {
        this.basPartsBrandDAO = basPartsBrandDAO;
    }

}
