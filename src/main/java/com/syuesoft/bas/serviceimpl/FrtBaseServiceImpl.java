package com.syuesoft.bas.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.bas.dao.BasRelationCampanyDao;
import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.bas.dao.DefaultDAO;
import com.syuesoft.bas.service.FrtBaseService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.dao.FrtCarDao;
import com.syuesoft.frt.dao.FrtCustomDao;
import com.syuesoft.frt.vo.FrtCarVo;
import com.syuesoft.model.BasCarType;
import com.syuesoft.model.BasRelationCampany;
import com.syuesoft.model.BasRepairGroup;
import com.syuesoft.model.BasRepairWork;
import com.syuesoft.model.BasStorehouse;
import com.syuesoft.model.FrtCar;
import com.syuesoft.model.Reptype;
import com.syuesoft.util.ComboBoxJson;

@Service("frtBaseService")
public class FrtBaseServiceImpl extends BaseServiceImpl implements
        FrtBaseService
{
    /**
     * Logger for this class
     */
    @SuppressWarnings("unused")
    private static final Logger logger = Logger
            .getLogger(FrtBaseServiceImpl.class);

    @Autowired
    private FrtCarDao frtCarDao;
    @Autowired
    private FrtCustomDao frtCustomDao;
    @Autowired
    private DefaultDAO defaultDao;
    @Autowired
    private BasStorehouseDAO basStorehouseDAO;
    @Autowired
    private BasRelationCampanyDao basRelationCampanyDao;
    /**
     * 查询含有工单号的车辆牌照
     * 
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllCarLicenseByReceptionId(String q,int enterprise_id)
            throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String hql = "SELECT fc.CAR_ID,fc.car_license FROM  frt_car fc INNER JOIN frt_reception fr ON fr.car_id=fc.car_id and fr.enterprise_id="+enterprise_id;
        if (q != null && !"".equals(q))
        {
            hql += "and fc.car_license like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<Object[]> fcList = frtCarDao.createSQLQuery(hql);
        if (fcList != null && fcList.size() > 0)
        {
            for (Object[] objects : fcList) {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(objects[0].toString());
                json.setText(objects[1].toString());
                list.add(json);
            }
        }
        return list;
    }


    /**
     * 变更车牌照
     * 
     * @throws Exception
     */
    
    public void changecarId(FrtCarVo fcVo)throws Exception
    {
    	FrtCar f=frtCarDao.get("from  FrtCar  c where c.carId='"+fcVo.getCarId()+"'  and  c.enterpriseId="+fcVo.getEnterpriseId());
        if(f!=null){
        	f.setCarLicense(fcVo.getCarLicense());
        	frtCarDao.update(f);
        }
    }
    /**
     * 查询含有工单号的车辆牌照
     * */
    
    public List<ComboBoxJson> findAllCarLicenseByReceptionIdAsCarLicense(
            String q,int enterprise_id) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String hql = "SELECT fc.car_license FROM  frt_car fc INNER JOIN frt_reception fr ON fr.car_id=fc.car_id and fc.enterprise_id="+enterprise_id;
        if (q != null && !"".equals(q))
        {
            hql += "and fc.car_license like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List fcList = frtCarDao.createSQLQuery(hql);
        if (fcList != null && fcList.size() > 0)
        {
            for (Object object : fcList) {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(object.toString());
                json.setText(object.toString());
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 查找车辆牌照
     * */
    
    public List<ComboBoxJson> findAllCarLicenseAsCarLicense(String q,Integer id)
            throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String hql = "SELECT  fc.CAR_LICENSE FROM frt_car fc,frt_custom c where 1=1  and c.CUSTOM_ID=fc.CUSTOM_ID  and  c.custom_flag = TRUE and fc.enterprise_id= "+id  ;
        if (q != null && !"".equals(q))
        {
            hql += " and fc.CAR_LICENSE like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List fcList = frtCarDao.createSQLQuery(hql);
        if (fcList != null && fcList.size() > 0)
        {
            for (Object object : fcList) {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(object.toString());
                json.setText(object.toString());
                list.add(json);
            }
        }
        return list;
    }
    /**
     * 查找可使用的车牌照
     * */
    
	public List<ComboBoxJson> findUseAllCarLicense(String q,Integer id) throws Exception {
	    List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
    	 String hql = "SELECT  fc.CAR_ID,fc.CAR_LICENSE FROM frt_car fc INNER JOIN frt_custom fct ON fct.CUSTOM_ID=fc.CUSTOM_ID where" +
    	 		" 1=1 and fct.CUSTOM_FLAG="+Contstants.ONOROFF.ONOROFFYES+"  and fc.enterprise_id="+id;
         if (q != null && !"".equals(q))
         {
             hql += " and fc.CAR_LICENSE like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
         }
         List<Object[]> fcList = frtCarDao.createSQLQuery(hql);
         if (fcList != null && fcList.size() > 0)
         {
             for (Object[] object : fcList) {
                 ComboBoxJson json = new ComboBoxJson();
                 json.setId(object[0].toString());
                 json.setText(object[1].toString());
                 list.add(json);
             }
         }
         return list;
	}

	/**
     * 查询维修类别
     */
    
    public List<ComboBoxJson> findAllReptype(String q,Integer id)
    {

        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String hql = "from Reptype rt where 1=1 and rt.enterpriseId="+id ;
        if (q != null && !"".equals(q)){
            hql += " and rt.reptName like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<Reptype> rtList = defaultDao.getObjList(hql);
        if (rtList != null && rtList.size() > 0)
        {
            for (Reptype r : rtList)
            {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(r.getReptId().toString());
                json.setText(r.getReptName());
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 查询维修工位
     */
    
    public List<ComboBoxJson> findAllRepairWork(String q,Integer id)
    {

        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String hql = "from BasRepairWork brw where 1=1 and brw.enterpriseId="+id ;
        if (q != null && !"".equals(q)){
            hql += " and brw.repwkName like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<BasRepairWork> brwList = defaultDao.getObjList(hql);
        if (brwList != null && brwList.size() > 0)
        {
            for (BasRepairWork brw : brwList)
            {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(brw.getRepwkId().toString());
                json.setText(brw.getRepwkName());
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 查询维修班组
     */
    
    public List<ComboBoxJson> findAllRepairGroup(String q,Integer id)
    {

        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String hql = "from BasRepairGroup brg where 1=1 and brg.enterpriseId="+id ;
        if (q != null && !"".equals(q)){
            hql += " and brg.repgrpName like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<BasRepairGroup> brgList = defaultDao.getObjList(hql);
        
        if (brgList != null && brgList.size() > 0)
        {
            for (BasRepairGroup brg : brgList)
            {
                ComboBoxJson json = new ComboBoxJson();
                
                json.setId(brg.getRepgrpId().toString());
                json.setText(brg.getRepgrpName());
                
                list.add(json);
            }
        }
        return list;
    }
    /**
	 * 查询索赔厂商
	 * */
	
	public List<ComboBoxJson> findAllClaimManufacturers(String q,Integer id)
			throws Exception {
		   List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
	        String hql = "from BasRelationCampany brc where brc.relcampFlg = 1  and brc.enterpriseId="+id ;
	        if (q != null && !"".equals(q)){
	        	hql+="and brc.relcampName like '%"+StringEscapeUtils.escapeSql(q.trim())+"%'";
	        }
	        List<BasRelationCampany> brcList = basRelationCampanyDao.find(hql);
	        if (brcList != null && brcList.size() > 0)
	        {
	            for (BasRelationCampany brc : brcList)
	            {
	                ComboBoxJson json = new ComboBoxJson();
	                json.setId(brc.getRelcampId().toString());
	                json.setText(brc.getRelcampName());
	                list.add(json);
	            }
	        }
	        return list;
	}
	/**
	 * 查询车型号
	 * */
	
	public List<ComboBoxJson> findAllCarTypeAsName(String q,Integer id)throws Exception {
		 List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		 String param="";
		 if (q != null && !"".equals(q)){
			 param=" and bct.ctypeName='"+q+"'";
		 }
		 List<BasCarType> bctList = defaultDao.getObjList("from BasCarType bct where 1=1  and bct.enterpriseId="+id  + param);
		 if (bctList != null && bctList.size() > 0)
		 {
		     for (BasCarType bct : bctList)
		     {
		         ComboBoxJson json = new ComboBoxJson();
		         json.setId(bct.getCtypeName());
		         json.setText(bct.getCtypeName());
		         list.add(json);
		     }
		 }
	     return list;
	}
	/**
	 * 查找客户
	 * */
	
	public List<ComboBoxJson> findAllCustomAsCustomName(String q,int enterprise_id)
			throws Exception {
	    List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		 String hql = "SELECT fc.CUSTOM_NAME FROM frt_custom fc  where fc.enterprise_id="+enterprise_id+"  and  fc.CUSTOM_FLAG=TRUE";
	     if (q != null && !"".equals(q)){
	            hql += "and fc.CUSTOM_NAME like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
	     }
	     List<Object[]> fcList = frtCustomDao.createSQLQuery(hql);
	     if (fcList != null && fcList.size() > 0)
	     {
	         for (Object objects : fcList) {
	             ComboBoxJson json = new ComboBoxJson();
	             json.setId(objects.toString());
	             json.setText(objects.toString());
	             list.add(json);
	         }
	     }
	     return list;
	}
	/**
	 * 查找仓别
	 * */
	
	public List<ComboBoxJson> findAllWorkHouse(String q,int enterprise_id) throws Exception {
	    List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		 String hql = "from BasStorehouse bsh where bsh.enterpriseId="+enterprise_id;
	     if (q != null && !"".equals(q))
	     {
	            hql += "and bsh.storeName like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
	     }
	     List<BasStorehouse> bshList = basStorehouseDAO.find(hql);
	     if (bshList != null && bshList.size() > 0)
	     {
	         for (BasStorehouse basStorehouse : bshList) {
	             ComboBoxJson json = new ComboBoxJson();
	             json.setId(basStorehouse.getStoreId().toString());
	             json.setText(basStorehouse.getStoreName());
	             list.add(json);
	         }
	     }
	     return list;
	}
    
    public List<FrtCar> getCarId(FrtCarVo frtCarVo) throws Exception
    {
        String hql = "From FrtCar where 1=1   and  carLicense='"+frtCarVo.getCarLicense()+"'";
        List<FrtCar> fp = frtCarDao.find(hql);
        return fp;
    }
}