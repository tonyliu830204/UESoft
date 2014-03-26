package com.syuesoft.frt.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.dao.FrtPartsDao;
import com.syuesoft.frt.service.FrtPartsService;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.model.BasCarType;
import com.syuesoft.st.dao.BasCarTypeDAO;

@Service("frtPartsService")
public class FrtPartsServiceImpl extends BaseLogServiceImpl implements
        FrtPartsService
{

    private FrtPartsDao frtPartsDao;

    private BasCarTypeDAO basCarTypeDAO;

    public FrtPartsDao getFrtPartsDao()
    {
        return frtPartsDao;
    }

    @Autowired
    public void setFrtPartsDao(FrtPartsDao frtPartsDao)
    {
        this.frtPartsDao = frtPartsDao;
    }

    public BasCarTypeDAO getBasCarTypeDAO()
    {
        return basCarTypeDAO;
    }

    @Autowired
    public void setBasCarTypeDAO(BasCarTypeDAO basCarTypeDAO)
    {
        this.basCarTypeDAO = basCarTypeDAO;
    }

    /**
     * 前台配件查询
     */
    
    public Datagrid datagridFrtParts(FrtPartsVo fpVo) throws Exception
    {
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM frt_parts_query_view v where 1 = 1  and  v.enterpriseId="+fpVo.getEnterpriseId());
        if (fpVo.getPartsId() != null && !"".equals(fpVo.getPartsId().trim()))
        {
            sql.append(" and v.partsId like '%"
                    + StringEscapeUtils.escapeSql(fpVo.getPartsId().trim())
                    + "%'");
        }
        if (fpVo.getPartsName() != null
                && !"".equals(fpVo.getPartsName().trim()))
        {
            sql.append(" and v.partsName like '%"
                    + StringEscapeUtils.escapeSql(fpVo.getPartsName().trim())
                    + "%'");
        }
        if (fpVo.getPartsSimpleId() != null
                && !"".equals(fpVo.getPartsSimpleId().trim()))
        {
            sql.append(" and v.partsSimpleId like '%"
                    + StringEscapeUtils.escapeSql(fpVo.getPartsSimpleId()
                            .trim()) + "%'");
        }
        if (fpVo.getSort() != null && !"".equals(fpVo.getSort().trim())
                && fpVo.getOrder() != null
                && !"".equals(fpVo.getOrder().trim()))
        {
            sql.append(" order by v." + fpVo.getSort().trim() + " "
                    + fpVo.getOrder().trim());
        }
        List<FrtPartsVo> rows = new ArrayList<FrtPartsVo>();
        List<Object[]> rowsList = frtPartsDao.createSQLQuery(sql.toString(), fpVo.getPage(), fpVo.getRows());
        DataConvert(rows, rowsList);
        int total = frtPartsDao.getSQLCount(sql.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    @SuppressWarnings("unused")
	private String addWhere(FrtPartsVo fpVo, String sql) 
    {
        if (fpVo.getPartsId() != null && !"".equals(fpVo.getPartsId().trim()))
        {
            sql += " and v.partsId like '%"+StringEscapeUtils.escapeSql(fpVo.getPartsId().trim())+"%'";
        }
        if (fpVo.getPartsName() != null
                && !"".equals(fpVo.getPartsName().trim()))
        {
            sql += " and v.partsName like  '%"+fpVo.getPartsName().trim().trim()+"%'";
        }
        if (fpVo.getPartsSimpleId() != null
                && !"".equals(fpVo.getPartsSimpleId().trim()))
        {
            sql += " and v.partsSimpleId like '%"+StringEscapeUtils.escapeSql(fpVo.getPartsSimpleId().trim())+"%'";
        }
        if (fpVo.getSort() != null && !"".equals(fpVo.getSort().trim())
                && fpVo.getOrder() != null
                && !"".equals(fpVo.getOrder().trim()))
        {
            sql += " order by v." + fpVo.getSort().trim() + " "
                    + fpVo.getOrder().trim();
        }
        return sql;
    }

    /**
     * 数据库类型到vo类型的转换封装
     * 
     * @param rows
     * @param rowsList
     */
    private void DataConvert(List<FrtPartsVo> rows, List<Object[]> rowsList)
            throws Exception
    {
        if (rowsList != null && rowsList.size() > 0)
        {
            for (Object[] obj : rowsList)
            {
                FrtPartsVo fVo = new FrtPartsVo();
                if (obj[0] != null)
                {
                    fVo.setPartsId(obj[0].toString());
                }
                if (obj[1] != null)
                {
                    fVo.setPartsId2(obj[1].toString());
                }
                if (obj[2] != null)
                {
                    fVo.setPtypeName(obj[2].toString());
                }
                if (obj[3] != null)
                {
                    fVo.setPartsName(obj[3].toString());
                }
                if (obj[4] != null)
                {
                    fVo.setPartsSimpleId(obj[4].toString());
                }
                if (obj[5] != null)
                {
                    fVo.setPosName(obj[5].toString());
                }
                if (obj[6] != null)
                {
                    fVo.setStateName(obj[6].toString());
                }
                if (obj[7] != null)
                {
                    fVo.setPunitName(obj[7].toString());
                }
                if (obj[8] != null)
                {
                    fVo.setPartsLibrary(obj[8].toString());
                }
                if (obj[9] != null)
                {
                    fVo.setFitPtype(obj[9].toString());
                }
                if (obj[10] != null)
                {
                    fVo.setPartsNowCount(Double.parseDouble(obj[10]
                                    .toString()));
                }
                if (obj[11] != null)
                {
                    fVo.setStoreName(obj[11].toString());
                }
                if (obj[12] != null)
                {
                    fVo.setPartsRepairPrice(new Double(obj[12].toString()));
                }
                if (obj[13] != null)
                {
                    fVo.setPartsSellPrice(new Double(obj[13].toString()));
                }
                if (obj[14] != null)
                {
                    fVo.setPartsPointPrice(new Double(obj[14].toString()));
                }
                if (obj[15] != null)
                {
                    fVo.setPartsSpecialPrice(new Double(obj[15].toString()));
                }
                if (obj[16] != null)
                {
                    fVo.setPartsClaimantPrice(new Double(obj[16].toString()));
                }
                if (obj[17] != null)
                {
                    fVo.setPartsLatestTaxprice(new Double(obj[17].toString()));
                }
                if (obj[18] != null)
                {
                    fVo
                            .setPartsLatestNotaxprice(new Double(obj[18]
                                    .toString()));
                }
                if (obj[19] != null)
                {
                    fVo.setStockUpper(Double.parseDouble(obj[19].toString()));
                }
                if (obj[20] != null)
                {
                    fVo.setStockLower(Double.parseDouble(obj[20].toString()));
                }
                if (obj[10] != null)
                {
                	
                }
                rows.add(fVo);
            }
        }
    }

    /**
     * 查询未选配件   配件必须从仓库选择   因为仓库在指定发料的时候不用选仓
     * */
    
    public List findAllParts(FrtPartsVo fpVo) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        if (fpVo.getIsStock()==null||fpVo.getIsStock() == false)
        {
        	 sb.append("SELECT fp.PARTS_ID,fp.PARTS_NAME,bpu.PUNIT_ID,bpu.PUNIT_NAME,");
        	 sb.append(" fp.FIT_PTYPE,pcp.PARTS_LATEST_TAXPRICE,pcp.PARTS_LATEST_NOTAXPRICE,");
        	 if(fpVo.getParam()!=null&&fpVo.getParam().length()>0){
        		 if(fpVo.getParam().equals(Contstants.BILLPRICTTYPE.SERVICEPRICE)){
        			 sb.append(" pcp.PARTS_REPAIR_PRICE,");
        		 }else if(fpVo.getParam().equals(Contstants.BILLPRICTTYPE.CLAIMSPRICE)){
        			 sb.append(" pcp.PARTS_CLAIMANT_PRICE,");        			 
        		 }else if(fpVo.getParam().equals(Contstants.BILLPRICTTYPE.SELLPRICE)){
        			 sb.append(" pcp.PARTS_SELL_PRICE,");        			 
        		 }
        	 }else{
        		 sb.append(" pcp.PARTS_REPAIR_PRICE,");
        	 }
        	 sb.append(" pcp.PARTS_NOW_COUNT,");
        	 sb.append(" fp.PARTS_LIBRARY,bsh.STORE_ID,bsh.STORE_NAME,fp.PARTS_REMARK,");
        	 sb.append("  bpb.PBRD_ID,bpb.PBRD_NAME,bpt.PTYPE_ID,bpt.PTYPE_NAME");
        	 sb.append(" FROM frt_parts fp LEFT JOIN  bas_parts_unit bpu  ON bpu.PUNIT_ID = fp.PUNIT_NAME"+
                      "  LEFT JOIN parts_change_price pcp  ON fp.PARTS_ID = pcp.PARTS_ID  "+
                      "  LEFT JOIN bas_storehouse bsh  ON pcp.STORE_ID = bsh.STORE_ID  "+
                      "  LEFT JOIN bas_parts_type bpt ON fp.PTYPE_ID = bpt.PTYPE_ID   "+
                      "  LEFT JOIN bas_parts_brand bpb ON bpb.PBRD_ID = bpt.PBRD_ID  ");
        	 sb.append(" WHERE   fp.PARTS_FLAG="+Contstants.ONOROFF.ONOROFFYES);
        }else{
        	sb.append("SELECT fp.PARTS_ID,fp.PARTS_NAME,bpu.PUNIT_ID,bpu.PUNIT_NAME,");
        	sb.append(" fp.FIT_PTYPE,'','',");
        	sb.append(" '','',");
        	sb.append(" fp.PARTS_LIBRARY,'','',fp.PARTS_REMARK,");
        	sb.append("  bpb.PBRD_ID,bpb.PBRD_NAME,bpt.PTYPE_ID,bpt.PTYPE_NAME");
        	sb.append(" FROM frt_parts fp,bas_parts_unit bpu,");
        	sb.append(" bas_parts_brand bpb,bas_parts_type bpt");
        	sb.append(" WHERE bpu.PUNIT_ID=fp.PUNIT_NAME");
        	sb.append(" and fp.PARTS_FLAG="+Contstants.ONOROFF.ONOROFFYES);
        	sb.append(" AND fp.PTYPE_ID=bpt.PTYPE_ID AND bpb.PBRD_ID=bpt.PBRD_ID  ");
        }
        if (fpVo.getPartsId() != null && !"".equals(fpVo.getPartsId()))
        {
            sb.append(" and fp.PARTS_ID like '%" + StringEscapeUtils.escapeSql(fpVo.getPartsId().trim()) + "%'");
        }
        if (fpVo.getPartsId2() != null && !"".equals(fpVo.getPartsId2()))
        {
            sb.append(" and fp.PARTS_ID2 like '%" + StringEscapeUtils.escapeSql(fpVo.getPartsId2().trim()) + "%'");
        }
        if (fpVo.getPartsName() != null
                && !"".equals(fpVo.getPartsName().trim()))
        {
            sb.append(" and fp.PARTS_NAME like '%"
                    + StringEscapeUtils.escapeSql(fpVo.getPartsName().trim().toUpperCase()) + "%'");
        }
        if (fpVo.getPbrdId() != null && !"".equals(fpVo.getPbrdId()))
        {
            sb.append(" and bpb.PBRD_ID = " + fpVo.getPbrdId());
        }
        if (fpVo.getPtypeName() != null
                && !"".equals(fpVo.getPtypeName().trim()))
        {
            sb.append(" and bpt.PTYPE_NAME like '%"
                    + StringEscapeUtils.escapeSql(fpVo.getPtypeName().trim()) + "%'");
        }
        if (fpVo.getStoreId() != null && !"".equals(fpVo.getStoreId())&&(fpVo.getIsStock()==null||fpVo.getIsStock() == false))
        {
            sb.append(" and bsh.STORE_ID = " + fpVo.getStoreId());
        }
        sb.append("  AND  pcp.PARTS_NOW_COUNT>0 " );
        sb.append("	 AND fp.enterprise_id="+fpVo.getEnterpriseId()  );
        sb.append(" limit 500");
        List<FrtPartsVo> rows = new ArrayList<FrtPartsVo>();
        List<Object[]> rowsList = frtPartsDao.createSQLQuery(sb.toString());
        FrtPartsVo fpVo2 = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] obj : rowsList)
            {
                fpVo2 = new FrtPartsVo();
                if (obj[0] != null && !"".equals(obj[0]))
                {
                    fpVo2.setPartsId(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    fpVo2.setPartsName(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    fpVo2.setPunitId(Short.parseShort(obj[2].toString()));
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    fpVo2.setPunitName(obj[3].toString());
                }
                if (obj[4] != null && !"".equals(obj[4]))
                {
                    fpVo2.setFitPtype(obj[4].toString());
                }
                /*****
                 * 5含税价 6未税价
                 ****/
                if (obj[7] != null && !"".equals(obj[7]))
                {
                    fpVo2.setPartsRepairPrice(Double.parseDouble(obj[7]
                            .toString()));
                }
                if (obj[8] != null && !"".equals(obj[8]))
                {
                    fpVo2.setPartsNowCount(Double
                            .parseDouble(obj[8].toString()));
                }
                if (obj[9] != null && !"".equals(obj[9]))
                {
                    fpVo2.setPartsLibrary(obj[9].toString());
                }
                if (obj[10] != null && !"".equals(obj[10]))
                {
                    fpVo2.setStoreId(obj[10].toString());
                }
                if (obj[11] != null && !"".equals(obj[11]))
                {
                    fpVo2.setStoreName(obj[11].toString());
                }
                if (obj[12] != null && !"".equals(obj[12]))
                {
                    fpVo2.setPartsRemark(obj[12].toString());
                }
                if (obj[13] != null && !"".equals(obj[13]))
                {
                	fpVo2.setPbrdId(Short.parseShort(obj[13].toString()));
                }
                if (obj[14] != null && !"".equals(obj[14]))
                {
                	fpVo2.setPbrdName(obj[14].toString());
                }
                if (obj[15] != null && !"".equals(obj[15]))
                {
                	fpVo2.setPtypeId(Short.parseShort(obj[15].toString()));
                }
                if (obj[16] != null && !"".equals(obj[16]))
                {
                	fpVo2.setPtypeName(obj[16].toString());
                }
                if (fpVo2.getFitPtype() == null)
                {
                    fpVo2.setFitPtypeName(null);
                }
                else
                {
//                    fpVo2.setFitPtype(fpVo2.getFitPtype().replaceAll(" ", ""));
//                    String[] applicableBrands = fpVo2.getFitPtype().split(",");
//                    StringBuffer temp = new StringBuffer();
//                    if (applicableBrands != null && applicableBrands.length > 0)
//                    {
//                        List<BasCarType> bctList = basCarTypeDAO
//                                .find("select bct from BasCarType bct where bct.ctypeId in("
//                                        + fpVo2.getFitPtype().trim() + ")");
//                        if (bctList != null && bctList.size() > 0)
//                            for (BasCarType basCarType : bctList)
//                            {
//                                temp.append(basCarType.getCtypeName() + ",");
//                            }
//                        if (temp.length() > 0)
//                        {
//                            fpVo2.setFitPtypeName(temp.substring(0, temp
//                                    .length() - 1));
//                        }
//                        else
//                        {
//                            fpVo2.setFitPtypeName(null);
//                        }
//                    }
//                    else
//                    {
//                        fpVo2.setFitPtypeName(null);
//                    }
                }
                fpVo2.setFitPtypeName(fpVo2.getFitPtype());
                rows.add(fpVo2);
            }
        return rows;

    }

}
