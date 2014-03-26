package com.syuesoft.bas.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.dao.BasPartsStateDao;
import com.syuesoft.bas.dao.BasRelationCampanyAttrDao;
import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.bas.dao.DefaultDAO;
import com.syuesoft.bas.dao.FrtCarAllowCarTypeDao;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.base.vo.BasStorehouseVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.COLLIGATES;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.BasParentdictionary;
import com.syuesoft.model.BasPartsState;
import com.syuesoft.model.BasRelationCampanyAttr;
import com.syuesoft.model.BasStorehouse;
import com.syuesoft.model.FrtCarAllowCarType;
import com.syuesoft.st.dao.StGoodsStorageDAO;
import com.syuesoft.st.dao.StSellOrderDAO;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.ComboBoxJson2;

@Service("baseService")
public class BaseServiceImpl implements BaseService
{
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(BaseServiceImpl.class);
    @Autowired  private BasStorehouseDAO basStorehouseDAO;
    @Autowired private BasPartsStateDao basPartsStateDao;
    @Autowired private DefaultDAO defaultDao;
    @Autowired private BaseDaoI<Object> baseDao;
    @Autowired private BasRelationCampanyAttrDao basRelationCampanyAttrDao;
    @Autowired private FrtCarAllowCarTypeDao frtCarAllowCarTypeDao;
    @Autowired private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
    @Autowired private StSellOrderDAO stSellOrderDAO;
	@Autowired StGoodsStorageDAO stGoodsStorageDAO;         // 入库单汇总DAO
	
    /**
     * 查询所有仓库
     */
    
    public List<ComboBoxJson> findAllStorehouse(BasStorehouseVo basStorehouseVo)
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<BasStorehouse> bshList = basStorehouseDAO.findAll(basStorehouseVo);
        if (bshList != null && bshList.size() > 0)
        {
            for (BasStorehouse bsh : bshList)
            {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(bsh.getStoreId().toString());
                json.setText(bsh.getStoreName());
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 查询员工
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllStuff(String q,int enterprise_id) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String hql = "SELECT bs.STF_ID,bs.STF_NAME FROM bas_stuff bs INNER JOIN bas_users bu ON bu.STF_ID=bs.STF_ID and bs.enterprise_id="+enterprise_id+" AND bu.LEVAL_ ='"+Contstants.EMPLOYEELEVEL.GENERAL+"' ";
        if (q != null && q.length() > 0)
            hql += " and bs.STF_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        List<Object[]> bsList = defaultDao.createSQLQuery(hql);
        return beanAdd(list, bsList);
    }
    /**
     * 销售单模块 销售分类信息加载
     */
    public List<ComboBoxJson> loadBasPartsSell(StSellOrderVo stSellOrderVo) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String sql = "SELECT bas_parts_sell.PSELL_ID,bas_parts_sell.PSELL_NAME "
                + "FROM bas_parts_sell where  bas_parts_sell.enterprise_id="+stSellOrderVo.getEnterpriseId()+" ORDER BY bas_parts_sell.PSELL_ID";
        List<Object[]> resultList = stSellOrderDAO.createSQLQuery(sql);
        return beanAdd(list,resultList);
    }

    /**
     * 查询所有车辆牌照
     * 
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllCarLicense(String q,Integer id) throws Exception
    {
        String hql = "SELECT fc.CAR_ID,fc.CAR_LICENSE FROM frt_car fc where 1 = 1 and fc.enterprise_id="+id;
        if (q != null && !"".equals(q))
        {
            hql += " and fc.CAR_LICENSE like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> fcList = defaultDao.createSQLQuery(hql);
        return beanAdd(list, fcList);
    }

    /**
     * 查询客户名称
     * @throws Exception 
     */
    
    public List<ComboBoxJson2> findAllCustom() throws Exception
    {

        List<ComboBoxJson2> list = new ArrayList<ComboBoxJson2>();

        List<Object[]> fcList = defaultDao.createSQLQuery("SELECT fc.CUSTOM_ID,fc.CUSTOM_NAME FROM frt_custom fc");
        if (fcList != null && fcList.size() > 0)
        {
            for (Object[] obj : fcList)
            {
                ComboBoxJson2 json = new ComboBoxJson2();
                json.setId(obj[0].toString());
                json.setText(obj[1].toString());
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 查询客户名称
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllCustom(String q,Integer id) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String param = "";
        if (q != null && !"".equals(q))
            param = " and fc.custom_Name like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        List<Object[]> fcList = defaultDao.createSQLQuery("select fc.custom_id,fc.custom_Name from frt_custom fc where  fc.custom_flag = TRUE and  fc.enterprise_id="+ id + param);
        return beanAdd(list, fcList);
    }

    /**
     * 查询车辆品牌
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllCarBrand(String q,Integer id) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String param = "";
        if (q != null && !"".equals(q.trim()))
        {
            param = " and bcb.CBRD_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<Object[]> bcbList = defaultDao.createSQLQuery("SELECT bcb.CBRD_ID,bcb.CBRD_NAME FROM bas_car_brand bcb  where  1=1"
        		+ "  and  bcb.enterprise_id="+id +param);
        return beanAdd(list,bcbList);
    }

    /**
     * 查询车辆型号
     */
    
    public List<ComboBoxJson2> findAllCarType() throws Exception
    {
        List<ComboBoxJson2> list = new ArrayList<ComboBoxJson2>();
        List bctList = defaultDao.createSQLQuery("SELECT bct.CTYPE_NAME FROM bas_car_type bct");
        if (bctList != null && bctList.size() > 0)
        {
            for (Object obj : bctList)
            {
                ComboBoxJson2 json = new ComboBoxJson2();
                json.setId(obj.toString());
                json.setText(obj.toString());
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 查询车辆型号
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllCarType(FrtPartsVo fpVo) throws Exception
    {
        String param = "";
        if (fpVo.getQ() != null && !"".equals(fpVo.getQ().trim()))
        {
            param += "and bct.CTYPE_NAME like '%" + StringEscapeUtils.escapeSql(fpVo.getQ().trim()) + "%'";
        }
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> bctList = defaultDao.createSQLQuery("SELECT bct.CTYPE_ID,bct.CTYPE_NAME FROM bas_car_type bct where bct.enterprise_id="+fpVo.getEnterpriseId()+" " + param);
        return beanAdd(list,bctList);
    }

    /**
     * 查询车辆型号
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllCarType(Short cbrdId, String q,int enterprise_id) throws Exception
    {
        String param = "";
        if (cbrdId != null && !"".equals(cbrdId))
        {
            param += " and bct.CBRD_ID = " + cbrdId;
        }
        if (q != null && !"".equals(q.trim()))
        {
            param += " and bct.CTYPE_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> bctList = defaultDao.createSQLQuery("SELECT bct.CTYPE_ID,bct.CTYPE_NAME FROM bas_car_type bct where bct.enterprise_id="+enterprise_id+" " + param);
        return beanAdd(list,bctList);
    }

    /**
     * 查询车辆款式
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllCarStyle(String q) throws Exception
    {
        String param = "";
        if (q != null && !"".equals(q.trim()))
        {
            param += "and bcs.CSTL_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> bctList = defaultDao.createSQLQuery("SELECT bcs.CAR_CSTL_NAME,bcs.CSTL_NAME FROM bas_car_style bcs where 1=1 " + param);
        return beanAdd(list,bctList);
    }

    /**
     * 查询车辆款式
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllCarStyle(Short ctypeId, String q,int enterprise_id) throws Exception
    {
        String param = "SELECT bcs.CAR_CSTL_NAME,bcs.CSTL_NAME FROM bas_car_style bcs where bcs.enterprise_id="+enterprise_id;
        if (ctypeId != null && !"".equals(ctypeId))
        {
            param += " and bcs.CTYPE_ID = " + ctypeId;
        }
        if (q != null && !"".equals(q.trim()))
        {
            param += " and bcs.CSTL_NAME like '%" +StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> bctList = defaultDao.createSQLQuery(param);
        return beanAdd(list,bctList);
    }

    /**
     * 查询车身颜色
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllCarColor(String q,int enterprise_id) throws Exception
    {
        String param = "";
        if (q != null && !"".equals(q.trim()))
        {
            param += "and bcc.COLOR_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> bccList = defaultDao.createSQLQuery("SELECT bcc.COLOR,bcc.COLOR_NAME FROM bas_car_color bcc where bcc.enterprise_id="+enterprise_id+" " + param);
        return beanAdd(list,bccList);
    }

    /**
     * 查询车辆经销商
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllCarSellers(String q,int enterprise_id) throws Exception
    {
        String param = "";
        if (q != null && !"".equals(q.trim()))
            param += "and bcs.SLS_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> bctList = defaultDao.createSQLQuery("SELECT bcs.SLS_ID,bcs.SLS_NAME FROM bas_car_sellers bcs where bcs.enterprise_id="+enterprise_id+" " + param);
        return beanAdd(list,bctList);
    }

    /**
     * 查询配件品牌
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllPartsName(String q) throws Exception
    {
        String param = "";
        if (q != null && !"".equals(q.trim()))
        {
            param += " and fp.partsName like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> bpbList = defaultDao.createSQLQuery("SELECT fp.parts_id,fp.parts_name FROM frt_parts fp where 1 = 1" + param);
        return beanAdd(list,bpbList);
    }

    /**
     * 查询配件品牌
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllPartsBrand(String q,int enterpeise_id) throws Exception
    {
        String param = "";
        if (q != null && !"".equals(q.trim()))
        {
            param += " and bpb.PBRD_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> bpbList = defaultDao.createSQLQuery("SELECT bpb.PBRD_ID,bpb.PBRD_NAME FROM bas_parts_brand bpb where bpb.enterprise_id="+enterpeise_id+" " + param);
        return beanAdd(list,bpbList);
    }

    /**
     * 查询配件型号
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllPartsType(Short pbrdId, String q,Integer id) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String param = "";
        if (q != null && !"".equals(q.trim()))
        {
            param += "	and bpt.PTYPE_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        if (pbrdId != null && !"".equals(pbrdId))
        {
            param += "	and bpt.PBRD_ID = " + pbrdId;
        }
        List<Object[]> bptList = defaultDao.createSQLQuery("SELECT bpt.PTYPE_ID,bpt.PTYPE_NAME FROM bas_parts_type bpt where 1 = 1 and  bpt.enterprise_id= "+id  + param);
        return beanAdd(list,bptList);
    }

    /**
     * 查询配件部位
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllPartsPosition(String q,int enterprise_id) throws Exception
    {
        String param = "";
        if (q != null && !"".equals(q.trim()))
            param += " and bpp.POS_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> bppList = defaultDao.createSQLQuery("SELECT bpp.POS_ID,bpp.POS_NAME FROM bas_parts_position bpp where bpp.enterprise_id="+enterprise_id+" " + param);
        return beanAdd(list,bppList);
    }

    /**
     * 查询配件产地
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllPartsState(int enterprise_id) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<BasPartsState> bpsList = basPartsStateDao
                .find("select bps.stateId,bps.stateName from BasPartsState bps");
        if (bpsList != null && bpsList.size() > 0)
        {
            for (BasPartsState bps : bpsList)
            {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(bps.getStateId().toString());
                json.setText(bps.getStateName());
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 查询配件产地
     * 
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllPartsState(FrtPartsVo fpVo) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String param = "";
        if (fpVo.getQ() != null && !"".equals(fpVo.getQ().trim()))
            param += " and bps.STATE_NAME like '%" + StringEscapeUtils.escapeSql(fpVo.getQ().trim()) + "%'";
        List<Object[]> bpsList = defaultDao.createSQLQuery("SELECT bps.STATE_ID,bps.STATE_NAME FROM bas_parts_state bps  where bps.enterprise_id="+fpVo.getEnterpriseId()+" " + param);
        return beanAdd(list, bpsList);
    }

    /**
     * 查询配件单位
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllPartsUnit(int interprise_id) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> bpuList = defaultDao.createSQLQuery("SELECT bpu.PUNIT_ID,bpu.PUNIT_NAME FROM bas_parts_unit bpu where bpu.enterprise_id="+interprise_id);
        return beanAdd(list,bpuList);
    }

    /**
     * 查询维修类别
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllReptype() throws Exception
    {

        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();

        List<Object[]> rtList = defaultDao.createSQLQuery("SELECT rt.REPT_ID,rt.REPT_NAME FROM reptype rt");
        return beanAdd(list,rtList);
    }

    /**
     * 查询维修工位
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllRepairWork() throws Exception
    {

        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();

        List<Object[]> brwList = defaultDao.createSQLQuery("SELECT brw.REPWK_ID,brw.REPWK_NAME FROM bas_repair_work brw");
        return beanAdd(list,brwList);
    }

    /**
     * 查询维修班组
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllRepairGroup() throws Exception
    {

        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();

        List<Object[]> brwList = defaultDao.createSQLQuery("SELECT brg.REPGRP_ID,brg.REPGRP_NAME FROM bas_repair_group brg");
        return beanAdd(list,brwList);
    }

    /**
     * 查询索赔厂商
     * 
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllClaimManufacturers() throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String hql = "SELECT brc.RELCAMP_ID,brc.RELCAMP_NAME FROM bas_relation_campany brc where brc.RELCAMP_FLG = 1 ";
        List<Object[]> brwList = defaultDao.createSQLQuery(hql);
       return beanAdd(list, brwList);
    }

    /**
     * 查询索赔厂商
     * 
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllBasRelationCampany(FrtPartsVo fpVo) throws Exception
    {
    	  List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
          String hql = "SELECT brc.RELCAMP_ID,brc.RELCAMP_NAME FROM bas_relation_campany brc where brc.enterprise_id="+fpVo.getEnterpriseId()+" and brc.RELCAMP_FLG = 0 ";
          List<Object[]> brwList = defaultDao.createSQLQuery(hql);
         return beanAdd(list, brwList);
    }

    /**
     * 查询客户性质
     * 
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllCustomNature(String q,int interprise_id) throws Exception
    {
        String hql = "SELECT bcn.NATURE_ID,bcn.NATURE_NAME FROM bas_custom_nature bcn where bcn.enterprise_id="+interprise_id;
        if (q != null && !"".equals(q.trim()))
        {
            hql += " and bcn.NATURE_NAME like '%"+StringEscapeUtils.escapeSql(q.trim())+"%'";
        }
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> brwList = defaultDao.createSQLQuery(hql);
        return beanAdd(list, brwList);
    }

    /**
     * 查询客户分类
     * 
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllCustomGroup(String q,int enterprise_id) throws Exception
    {
        String hql = "SELECT bcg.CSTG_ID,bcg.CSTG_NAME FROM bas_custom_group bcg where bcg.enterprise_id="+enterprise_id;
        if (q != null && !"".equals(q.trim()))
            hql += " and bcg.CSTG_NAME like '%"+StringEscapeUtils.escapeSql(q.trim())+"%' ";
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> brwList = defaultDao.createSQLQuery(hql);
        return beanAdd(list, brwList);
    }

    /**
     * 查询客户类型
     * 
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllCustomType(String q,int enterprise_id) throws Exception
    {
        String hql = "SELECT bct.CST_ID,bct.CST_NAME FROM bas_custom_type bct where 1 = 1 and  bct.enterprise_id="+enterprise_id;
        if (q != null && !"".equals(q.trim()))
        {
            hql += " and bct.CST_NAME like '%"+StringEscapeUtils.escapeSql(q.trim())+"%' ";
        }
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> brwList = defaultDao.createSQLQuery(hql);
        return beanAdd(list, brwList);
    }

    /**
     * 查询客户区域
     * 
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllCustomArea(String q,int enterprise_id) throws Exception
    {
        String hql = "SELECT bca.PAREA_ID,bca.PAREA_NAME FROM bas_custom_area bca where bca.enterprise_id="+enterprise_id;
        if (q != null && !"".equals(q.trim()))
            hql += " and bca.PAREA_NAME like '%"+StringEscapeUtils.escapeSql(q.trim())+"%' ";
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<Object[]> brwList = defaultDao.createSQLQuery(hql);
        return beanAdd(list, brwList);
    }

    /**
     * 查询相关单位(包含供应商和保险公司) true表示保险公司，false表示供应商
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllRelationCampany(String q, Boolean flag,int enterprise_id) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String param = "";
        if (q != null && !"".equals(q.trim()))
            param += " and brc.RELCAMP_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        if (flag)
            param += " and brc.RELCAMP_FLG = 1 ";
        else
            param += " and brc.RELCAMP_FLG = 0 ";
        List<Object[]> brcList = defaultDao.createSQLQuery("SELECT brc.RELCAMP_ID,brc.RELCAMP_NAME FROM bas_relation_campany brc where brc.enterprise_id="+enterprise_id+" " + param);
        return beanAdd(list, brcList);
    }

    /**
     * 查询准驾车型
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllowCarType(String q) throws Exception
    {
        String hql = "from FrtCarAllowCarType fcact where  1=1";
        if (q != null && !"".equals(q.trim()))
            hql += " and fcact.allowCarTypeName like '" + StringEscapeUtils.escapeSql(q.trim()) + "'";
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<FrtCarAllowCarType> fcactList = frtCarAllowCarTypeDao.find(hql);
        if (fcactList != null && fcactList.size() > 0)
        {
            for (FrtCarAllowCarType acact : fcactList)
            {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(acact.getAllowCarTypeId().toString());
                json.setText(acact.getAllowCarTypeName());
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 查询保险(汽厂)属性
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllRelcampAttr(Integer nowEnterpriseId) throws Exception
    {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from BasRelationCampanyAttr brca where  brca.enterpriseId="+nowEnterpriseId;
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<BasRelationCampanyAttr> brcaList = basRelationCampanyAttrDao.find(hql, params);
        if (brcaList != null && brcaList.size() > 0){
            for (BasRelationCampanyAttr brca : brcaList){
                ComboBoxJson json = new ComboBoxJson();
                json.setId(brca.getAttrId().toString());
                json.setText(brca.getAttrName());
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 查询收费性质
     * @throws Exception 
     */
    
    public List<ComboBoxJson> findAllRepairType(String q,final int enterprise_id) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String params = "";
        if (q != null && !"".equals(q.trim()))
        {
            params += " and brt.REPTYP_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<Object[]> brtList = defaultDao.createSQLQuery("SELECT brt.REPTYP_ID,brt.REPTYP_NAME FROM bas_repair_type brt where brt.enterprise_id="+enterprise_id+" "+params);
        return beanAdd(list, brtList);
    }

    /**
     * 查询索赔分类
     */
    
    public List<ComboBoxJson> findAllClaimsType(String q,final int enterpriseId) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String params = "";
        if (q != null && !"".equals(q.trim()))
            params += " and bct.CLAIMS_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        List<Object[]> bctList = defaultDao.createSQLQuery("SELECT bct.CLAIMS_ID,bct.CLAIMS_NAME FROM bas_claims_type bct where bct.enterprise_id ="+enterpriseId+" "+params);
        return beanAdd(list, bctList);
    }

    /**
     * 从码表中取下拉框数据
     */
    @SuppressWarnings("unchecked")
	
    public List<ComboBoxJson> baseListData(String key){
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        StringBuffer param = new StringBuffer("");
        param.append(" and bp.dataKey = '" + key + "'");
        List<BasParentdictionary> bpList = defaultDao.getObjList("from BasParentdictionary  bp where 1 = 1 " + param);
        if (null != bpList && bpList.size() > 0)
        {
            BasParentdictionary bp = bpList.get(0);
            List<BasChilddictionary> bcList = defaultDao.getObjList("from BasChilddictionary  bc where 1=1 and bc.parentId = '"+ bp.getParentId() + "'");
            if (bcList != null && bcList.size() > 0){
                for (BasChilddictionary bcr : bcList){
                    ComboBoxJson json = new ComboBoxJson();
                    json.setId(bcr.getDataKey().toString());
                    json.setText(bcr.getDataValue());
                    list.add(json);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    
    public List<BasChilddictionary> getBasChilddictionary(String key)
    {
        List<BasChilddictionary> bcList = null;
        StringBuffer param = new StringBuffer("");
        param.append("from BasParentdictionary  bp where 1 = 1 and bp.dataKey = '"+ key + "'");
        List<BasParentdictionary> bpList = defaultDao.getObjList(param.toString());
        if (null != bpList && bpList.size() > 0){
            BasParentdictionary bp = bpList.get(0);
            bcList = defaultDao.getObjList("from BasChilddictionary  bc where 1=1 and bc.parentId = '"
                            + bp.getParentId() + "'");
        }
        return bcList;
    }

    
    public BasChilddictionary findChildById(String id)
    {
        String param = "from BasChilddictionary  bc where 1 = 1 and bc.childId = "
                + id;
        BasChilddictionary bc = (BasChilddictionary) defaultDao.getObj(param.toString());
        return bc;
    }
    
    
    public BasChilddictionary findChildBykey(String key)
    {
        String param = "from BasChilddictionary  bc where 1 = 1 and bc.dataKey = '"+key+"'";
        BasChilddictionary bc = (BasChilddictionary) defaultDao.getObj(param.toString());
        return bc;
    }

    public boolean isNum(String str)
    {
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    /**
     * 数据加载
     */
    public List<ComboBoxJson> loadDataByChildId(final String dataKey)
            throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String sql = "SELECT bas_childdictionary.child_id,bas_childdictionary.dataValue"
                + " FROM bas_childdictionary,bas_parentdictionary"
                + " WHERE  bas_childdictionary.parent_id=bas_parentdictionary.parent_id";
        if (dataKey != null && !dataKey.equals(""))
        {
            sql += "  AND bas_parentdictionary.dataKey='" + dataKey + "'";
        }
        List<Object[]> resultList = baseDao.createSQLQuery(sql);
        return beanAdd(list, resultList);
    }
    
    /**
	 * 入库单号   预加载
	 */
	@SuppressWarnings("unchecked")
	public List<ComboBoxJson> loadStorageId(String q,int enterprise_id)throws Exception{
		List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		StringBuffer sql=new StringBuffer("SELECT st_goods_storage.STORAGE_ID FROM st_goods_storage where enterprise_id="+enterprise_id);
		if(q!=null&&!q.trim().equals(""))
			sql.append(" and STORAGE_ID like '%"+q.trim()+"%'");
		List resultList = stGoodsStorageDAO.createSQLQuery(sql.toString());
		if(resultList != null && resultList.size() > 0){
			for (int i = 0; i < resultList.size(); i++) {
				ComboBoxJson json = new ComboBoxJson();
				json.setId(resultList.get(i).toString());
				json.setText(resultList.get(i).toString());
				list.add(json);
			}
		}
		return list;
	}
    
    /**
     * 获取系统默认参数
     * */
	
	public String findDefaultProperties(String type,String name,int enterpriseId) throws Exception {
		 BasCompanyInformationSet basCompanyInformationSet = basCompanyInformationSetDAO.getBasCompanyInformationSet(type,name,enterpriseId);
		 if (basCompanyInformationSet != null)
		     return basCompanyInformationSet.getCiValue();
		 return null;
	}

	/**
     * 获取系统默认参数
     * */
	
	public String findDefaultProperties(String type,String name) throws Exception {
		 BasCompanyInformationSet basCompanyInformationSet = basCompanyInformationSetDAO.getBasCompanyInformationSet(type,name);
		 if (basCompanyInformationSet != null)
		     return basCompanyInformationSet.getCiValue();
		 return null;
	}
	
	
	public String getSinglePrice(String lince,int enterpriseId) throws Exception {
		String price="";
		if(lince==null || "".equals(lince)){
			price =basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,COLLIGATES.TASKTIMEPRICE,enterpriseId).getCiValue();
			if(price == null && "".equals(price)){
			    price="1";
			}
		}else{
			String sql="SELECT t.CTYPE_PRICE FROM bas_car_type t WHERE t.CTYPE_ID = (SELECT c.CTYPE_ID FROM frt_car c WHERE c.CAR_ID='"+lince+"')";
			List lstType=defaultDao.createSQLQuery(sql);
			if(lstType!= null && lstType.size()>0){
				if(lstType.get(0)!=null){
					price=lstType.get(0).toString();
				}
				if(price==null||"".equals(price)){
					price="1";
				}
			}else{
				String sqlBrand="SELECT b.CBRD_PRICE FROM  bas_car_brand b WHERE b.CBRD_ID="+
				"(SELECT  t.CBRD_ID   FROM  bas_car_type  t WHERE t.CTYPE_ID = (SELECT    c.CTYPE_ID FROM  frt_car c WHERE c.CAR_ID='"+lince+"'))";
				List lstBrand=defaultDao.createSQLQuery(sqlBrand);
				if(lstBrand!=null && lstBrand.size()>0){
					price=lstBrand.get(0).toString();
				}else{
					price =basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,COLLIGATES.TASKTIMEPRICE,enterpriseId).getCiValue();
			    }
				if(price == null && "".equals(price)){
	                price="1";
	            }
			}
		}
		return price;
	}
	/**
	 * 填充集合
	 * */
	private List<ComboBoxJson> beanAdd(List<ComboBoxJson> list, List<Object[]> tempList){
	   if (tempList != null && tempList.size() > 0)
        {
		   ComboBoxJson json = null;
        	for (Object[] obj : tempList)
            {
                json=new ComboBoxJson();
                if(obj[0]!=null)
                	json.setId(obj[0].toString());
                if(obj[1]!=null)
                	json.setText(obj[1].toString());
                list.add(json);
            }
        }
        return list;
	}
	/**
	 * 查找指定企业的子店信息
	 * @param q 指定的父级企业序号
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆名称，false标示id为编号，text为名称，默认为false 
	 * */
	
	public List<ComboBoxJson> findAllDistributeEnterprise(String enterpriseId,String q, Boolean flag)
			throws Exception {
		 List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
	        String param = "ei.enterprise_id";
	        if(flag!=null&&flag==true){
				param="ei.enterprise_name";
			}
			String sql="SELECT "+param+",ei.enterprise_name FROM enterprise_info ei where ei.parentEnterprise_id="+enterpriseId;
			if (q != null && !"".equals(q.trim()))
				sql += " and ei.enterprise_name like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
	        List<Object[]> bctList = defaultDao.createSQLQuery(sql);
	        return beanAdd(list, bctList);
	}

	///////////////////////////////////////////以下为系统各种类型员工的查询/////////////////////////////////////////////////
	/**
	 * [1管理人员]管理人员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findManager(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY1, enterpriseId);
	}
	/**
	 * [2维修业务]业务员查询(维修客户档案)
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findServiceOperationPerson(String q,
			Boolean flag, int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY2, enterpriseId);
	}
	/**
	 * [3维修接待]接待员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findServiceReceivePerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY3, enterpriseId);
	}
	/**
	 * [4配件仓库]退料员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findPartsStorehousePerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY4, enterpriseId);
	}
	/**
	 * [5财务人员]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findFinanceicalPerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY5, enterpriseId);
	}
	/**
	 * [6售后维修]维修员查询,维修技师查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findAfterServiceMaintainPerson(String q,
			Boolean flag, int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY6, enterpriseId);
	}
	/**
	 * [7配件采购]采购员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findPartsStockPerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY7, enterpriseId);
	}
	/**
	 * [8维修领料]领料员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findServiceMaterielPerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY8, enterpriseId);
	}
	/**
	 * [9保险人员]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findInsurePerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY9, enterpriseId);
	}
	/**
	 * [10车间检验]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findPlantProvePerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY10, enterpriseId);
	}
	/**
	 * [11维修三包]三包员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findServiceEgisAvailPerson(String q,
			Boolean flag, int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY11, enterpriseId);
	}
	/**
	 * [12销售业务]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findSellOperationPerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY12, enterpriseId);
	}
	/**
	 * [13整车仓库]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findCarStorehousePerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY13, enterpriseId);
	}
	/**
	 * [14整车采购]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findCarStockPerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY14, enterpriseId);
	}
	/**
	 * [15PDI检测]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findPDIProvePerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY15, enterpriseId);
	}
	/**
	 * [16整车装潢]查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findCarUpholsterPerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY16, enterpriseId);
	}
	/**
	 * [17客服人员]回访员查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findVisitingPerson(String q, Boolean flag,
			int enterpriseId) throws Exception {
		return findBasStuffClass(q, flag, Contstants.BASJOBPROPERTY.JOBPROPERTY17, enterpriseId);
	}
	/**
	 * 指定员工类别查询
	 * @param q 下拉框查询条件
	 * @param flag true表示id和text皆姓名，false标示id为编号，text为姓名，默认为false
	 * @param basJobProperty 工作属性序号(数值<=0查询所有类别员工)
	 * @param enterpriseId 登陆用户所属公司id
	 * @exception Exception 总异常
	 * @return List<ComboBoxJson> 返回包含ComboBoxJson对象的集合
	 * */
	
	public List<ComboBoxJson> findBasStuffClass(String q, Boolean flag,
			int basJobProperty,int enterpriseId) throws Exception {
		List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		String sql=null;
		String param=null;
		if(flag!=null&&flag==true){
			param="bs.stf_name";
		}else{
			param="bs.stf_id";
		}
		sql="select "+param+",bs.stf_name from bas_stuff bs where 1=1 and bs.del_tag='n' AND bs.enterprise_id="+enterpriseId;
		if(basJobProperty>0)
			sql+=" AND bs.stf_id IN (SELECT DISTINCT bsj.stf_id FROM  bas_stuff_job bsj WHERE bsj.job_pro_id="+basJobProperty+" )";		
		if(q!=null&&q.length()>0){
			sql+=" and bs.stf_name like '%"+StringEscapeUtils.escapeSql(q.trim())+"%'";
		}
        List<Object[]> bctList = defaultDao.createSQLQuery(sql);
        return beanAdd(list, bctList);
	}

    public void saveOrUpdate(Object obj) throws Exception
    {
        baseDao.saveOrUpdate(obj);
    }

    public Object get(String hql) throws Exception
    {
        return baseDao.get(hql);
    }	
}
