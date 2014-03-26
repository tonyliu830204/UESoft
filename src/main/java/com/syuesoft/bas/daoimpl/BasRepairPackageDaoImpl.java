package com.syuesoft.bas.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasRepairPackageDao;
import com.syuesoft.base.vo.BasRepairPackageVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.model.BasCarType;
import com.syuesoft.model.BasRepairPackage;
import com.syuesoft.st.dao.BasCarTypeDAO;

/**
 * 维修套餐
 * 
 * @author Liujian
 * 
 */
@Repository("basRepairPackageDao")
public class BasRepairPackageDaoImpl extends BaseDaoImpl<BasRepairPackage>
        implements BasRepairPackageDao
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
            .getLogger(BasRepairPackageDaoImpl.class);

    @Autowired
    private BasCarTypeDAO basCarTypeDAO;

    /**
     * 待选配件
     */
    @SuppressWarnings("unchecked")
    
    public List<FrtPartsVo> findAllSelectionParts(String param)
    {
        try
        {
            return this
                    .getHibernateTemplate()
                    .getSessionFactory()
                    .getCurrentSession()
                    .createQuery(
                            "select new com.syuesoft.base.vo.BasRepairPackagePartsVo(CONCAT(CONCAT(fp.partsId, ','), pcp.storeId), fp.partsId, fp.partsId2, fp.partsName, bpb.pbrdId, bpb.pbrdName, bpt.ptypeId, bpt.ptypeName, fp.fitPtype,fp.partsRemark, bps.stateId, bps.stateName, bpp.posId, bpp.posName, bpu.punitId, bpu.punitName, pcp.partsNowCount, pcp.partsRepairPrice, bsh.storeId, bsh.storeName) from FrtParts fp, BasPartsBrand bpb, BasPartsType bpt, BasPartsPosition bpp, BasPartsState bps, BasPartsUnit bpu, PartsChangePrice pcp, BasStorehouse bsh where fp.basPartsType.ptypeId = bpt.ptypeId and fp.posName = bpp.posId and fp.stateName = bps.stateId and fp.punitName = bpu.punitId and pcp.partsId = fp.partsId and pcp.storeId = bsh.storeId and bpt.basPartsBrand.pbrdId = bpb.pbrdId "
                                    + param).list();
        }
        catch(Exception es)
        {
            es.printStackTrace();
        }
        return null;
    }

    /**
     * 待选项目
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    
    public List findAllSelectionItem(String param,BasRepairPackageVo brpVo)
            throws Exception
    {
        StringBuffer sb = new StringBuffer("select fri.*,bws.WH_SORT_NAME from frt_repair_item fri,bas_workhour_sort bws");
        sb.append(" where bws.WH_SORT_ID=fri.REPITEM_SERIES  and fri.enterprise_id="+brpVo.getEnterpriseId());
        sb.append(param);
        List<BasRepairPackageVo> rows = new ArrayList();
        BasRepairPackageVo brp = null;
        List<Object[]> rowsList = super.createSQLQuery(sb.toString());
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] obj : rowsList)
            {
                brp = new BasRepairPackageVo();
                if (obj[0] != null && obj[0].toString().length() > 0)
                    brp.setRepitemId(obj[0].toString());
                if (obj[1] != null && obj[1].toString().length() > 0)
                    brp.setRepitemName(obj[1].toString());
                if (obj[2] != null && obj[2].toString().length() > 0)
                    brp.setRepitemCode(obj[2].toString());
                if (obj[3] != null && obj[3].toString().length() > 0)
                    brp.setRepitemSeries(Short.parseShort(obj[3].toString()));
                if (obj[4] != null && obj[4].toString().length() > 0)
                    brp.setRepitemAmount(Double.parseDouble(obj[4].toString()));
                if (obj[5] != null && obj[5].toString().length() > 0)
                    brp.setRepitemTime(Double.parseDouble(obj[5].toString()));
                if (obj[6] != null && obj[6].toString().length() > 0)
                    brp.setInternalTime(Double.parseDouble(obj[6].toString()));
                if (obj[7] != null && obj[7].toString().length() > 0)
                    brp.setClaimTime(Double.parseDouble(obj[7].toString()));
                if (obj[8] != null && obj[8].toString().length() > 0)
                    brp.setFitCar(obj[8].toString());
                if (obj[9] != null && obj[9].toString().length() > 0)
                    brp.setRepitemRemark(obj[9].toString());
                if (obj[11] != null && obj[11].toString().length() > 0)
                    brp.setRepitemSeriesName(obj[11].toString());

                if (brp.getFitCar() == null)
                {
                    brp.setFitCarName(null);
                }
                else
                {
                    brp.setFitCar(brp.getFitCar().replaceAll(" ", ""));
                    String[] applicableBrands = brp.getFitCar().split(",");
                    StringBuffer sbr = new StringBuffer();
                    if (applicableBrands != null && applicableBrands.length > 0)
                    {
                        List<BasCarType> bctList = basCarTypeDAO
                                .find("select bct from BasCarType bct where bct.ctypeId in("
                                        + brp.getFitCar().trim() + ")");
                        if (bctList != null && bctList.size() > 0)
                            for (BasCarType basCarType : bctList)
                            {
                                sbr.append(basCarType.getCtypeName() + ",");
                            }
                        if (sb.length() > 0)
                        {
                            brp.setFitCarName(sbr
                                    .substring(0, sbr.length() - 1));
                        }
                        else
                        {
                            brp.setFitCarName(null);
                        }
                    }
                    else
                    {
                        brp.setFitCarName(null);
                    }
                }
                rows.add(brp);
            }
        return rows;
        
    }

}
