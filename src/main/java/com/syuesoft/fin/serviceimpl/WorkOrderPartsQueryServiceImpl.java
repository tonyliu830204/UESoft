package com.syuesoft.fin.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.bas.serviceimpl.BaseServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.dao.FinClaimantPartsDao;
import com.syuesoft.fin.service.WorkOrderPartsQueryService;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.WorkOrderPartsQueryVo;
import com.syuesoft.frt.dao.FrtPreClearingDao;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.vo.GatheringVo;
import com.syuesoft.st.dao.StOutMainDAO;
import com.syuesoft.util.MyBeanUtils;

@Service("workOrderPartsQueryService")
public class WorkOrderPartsQueryServiceImpl extends BaseServiceImpl implements
        WorkOrderPartsQueryService
{

    @Autowired
    private FrtPreClearingDao frtPreClearingDao;
    @Autowired
    private StOutMainDAO stOutMainDAO;
    
    @Autowired
    private FrtService frtService;
    
    @Autowired BaseDaoI<Object> baseDao;
    /**
     * 工单结算信息(按配件)
     * */
    
	public Datagrid loadFrtPreClearingForParts(
			WorkOrderPartsQueryVo wopVo) throws Exception {
    	setDefaultPreclrTimeSect(wopVo);
    	if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
    		wopVo.setCustomName(new String(wopVo.getCustomName().getBytes("ISO-8859-1"),"UTF-8"));
    	if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
    		wopVo.setCarLicense(new String(wopVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
    	if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
    		wopVo.setPartsName(new String(wopVo.getPartsName().getBytes("ISO-8859-1"),"UTF-8"));
    	 List<WorkOrderPartsQueryVo> rows = new ArrayList<WorkOrderPartsQueryVo>();
    	 StringBuffer sb=new StringBuffer("SELECT  aa.temp1,aa.temp2,aa.temp3,aa.temp4,SUM(aa.temp5),SUM(aa.temp6),SUM(aa.temp7)");
    	 sb.append(" FROM ( ( SELECT frt_parts.PARTS_ID AS temp1,frt_parts.PARTS_NAME AS temp2,bas_parts_unit.PUNIT_NAME AS temp3,frt_parts.PARTS_LIBRARY AS temp4,"); 
    	 sb.append(" st_out_item.ITEM_COUNT AS temp5,(st_out_item.ITEM_COUNT*st_out_item.ITEM_PRICE) AS temp6,");
    	 sb.append(" st_out_item.TAX_CASTAMONT AS temp7,st_out_main.STOM_ID"); 
    	 sb.append(" FROM frt_pre_clearing,frt_reception,frt_car,bas_car_type,frt_custom,"); 
    	 sb.append(" st_out_main,st_out_item, frt_parts,bas_parts_brand,bas_parts_type,bas_parts_unit,bas_claims_type"); 
    	 sb.append(" WHERE 	frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID"); 
    	 sb.append(" AND frt_pre_clearing.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND frt_reception.CAR_ID=frt_car.CAR_ID"); 
    	 sb.append(" AND frt_car.CTYPE_ID=bas_car_type.CTYPE_ID AND frt_custom.CUSTOM_ID=frt_reception.CUSTOM_ID"); 
    	 sb.append(" AND st_out_main.STOM_ID=st_out_item.STOM_ID AND st_out_main.CER_NO=frt_reception.RECEPTION_ID"); 
    	 sb.append(" AND st_out_item.PARTS_ID=frt_parts.PARTS_ID AND bas_parts_type.PTYPE_ID=frt_parts.PTYPE_ID"); 
    	 sb.append(" AND bas_parts_brand.PBRD_ID=bas_parts_type.PBRD_ID AND frt_pre_clearing.PRE_FLG="+Contstants.DELETE_TAG.DELETENO); 
    	 if(wopVo.getPreclrTimeStart()!=null&&wopVo.getPreclrTimeStart().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME>='"+wopVo.getPreclrTimeStart()+"'");
         if(wopVo.getPreclrTimeEnd()!=null&&wopVo.getPreclrTimeEnd().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME<='"+wopVo.getPreclrTimeEnd()+"'");
         if(wopVo.getPreclrInoiceType()!=null&&wopVo.getPreclrInoiceType().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_INOICE_TYPE='"+wopVo.getPreclrInoiceType()+"'");
         if(wopVo.getRcptBranch()!=null&&wopVo.getRcptBranch().length()>0)
        	 sb.append(" AND frt_reception.RCPT_BRANCH='"+wopVo.getRcptBranch()+"'");
         if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
        	 sb.append(" AND frt_custom.CUSTOM_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCustomName().trim())+"%'");
         if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
        	 sb.append(" AND frt_car.CAR_LICENSE LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCarLicense().trim())+"%'");
         if(wopVo.getCarBrandId()!=null&&wopVo.getCarBrandId().length()>0)
        	 sb.append(" AND bas_car_type.CBRD_ID="+wopVo.getCarBrandId());
         if(wopVo.getCarTypeId()!=null&&wopVo.getCarTypeId().length()>0)
        	 sb.append(" AND bas_car_type.CTYPE_ID="+wopVo.getCarTypeId());
         if(wopVo.getReceptionId()!=null&&wopVo.getReceptionId().length()>0)
        	 sb.append(" AND frt_reception.RECEPTION_ID LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getReceptionId().trim())+"%'");
         if(wopVo.getStoreId()!=null&&wopVo.getStoreId().length()>0)
        	 sb.append(" AND st_out_item.STORE_ID="+wopVo.getStoreId());
         if(wopVo.getClaimsType()!=null&&wopVo.getClaimsType().length()>0)
        	 sb.append(" AND st_out_item.CLAIMS_TYPE in("+wopVo.getClaimsType()+")");
         if(wopVo.getPartsBrandId()!=null&&wopVo.getPartsBrandId().length()>0)
        	 sb.append(" AND bas_parts_brand.PBRD_ID="+wopVo.getPartsBrandId());
         if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
        	 sb.append(" AND frt_parts.PARTS_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getPartsName().trim())+"%'");
         if(wopVo.getReptId()!=null&&wopVo.getReptId().length()>0)
        	 sb.append(" AND frt_reception.REPT_ID="+wopVo.getReptId());
    	 sb.append(" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID AND bas_claims_type.CLAIMS_ID=st_out_item.CLAIMS_TYPE )"); 
    	 sb.append(" UNION"); 
    	 sb.append(" ( SELECT frt_parts.PARTS_ID AS temp1,frt_parts.PARTS_NAME AS temp2,bas_parts_unit.PUNIT_NAME AS temp3,frt_parts.PARTS_LIBRARY AS temp4,");  
    	 sb.append(" st_rt_parts_detail.STRTPD_CNT AS temp5,(st_rt_parts_detail.STRTPD_CNT*parts_change_price.PARTS_SELL_PRICE) AS temp6,"); 
    	 sb.append(" ROUND(parts_change_price.PARTS_LATEST_TAXPRICE*st_rt_parts_detail.STRTPD_CNT,2) AS temp7,st_rt_parts_main.STRTPM_ID");
    	 sb.append(" FROM st_rt_parts_main,st_rt_parts_detail,frt_parts,bas_parts_unit,parts_change_price,");  
    	 sb.append(" frt_pre_clearing,frt_reception,frt_car,bas_car_type,frt_custom, st_out_main,");
    	 sb.append(" st_out_item,bas_parts_brand,bas_parts_type,bas_claims_type WHERE"); 	
    	 sb.append(" frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID AND frt_pre_clearing.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES); 
    	 sb.append(" AND frt_reception.CAR_ID=frt_car.CAR_ID AND frt_car.CTYPE_ID=bas_car_type.CTYPE_ID"); 
    	 sb.append(" AND frt_custom.CUSTOM_ID=frt_reception.CUSTOM_ID AND st_out_main.STOM_ID=st_out_item.STOM_ID"); 
    	 sb.append(" AND st_out_main.CER_NO=frt_reception.RECEPTION_ID AND st_out_item.PARTS_ID=frt_parts.PARTS_ID"); 
    	 sb.append(" AND bas_parts_type.PTYPE_ID=frt_parts.PTYPE_ID AND bas_parts_brand.PBRD_ID=bas_parts_type.PBRD_ID"); 
    	 sb.append(" AND frt_pre_clearing.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+" AND st_rt_parts_detail.INDEX_ID=st_out_item.INDEX_ID"); 
    	 sb.append(" AND bas_claims_type.CLAIMS_ID=st_out_item.CLAIMS_TYPE"); 
    	 sb.append(" AND  st_rt_parts_main.STRTPM_ID=st_rt_parts_detail.STRTPM_ID AND st_rt_parts_main.RECEPTION_ID=frt_reception.RECEPTION_ID"); 
    	 sb.append(" AND st_rt_parts_detail.PARTS_ID=frt_parts.PARTS_ID AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID"); 
    	 sb.append(" AND st_rt_parts_detail.PARTS_ID=parts_change_price.PARTS_ID"); 
    	 if(wopVo.getPreclrTimeStart()!=null&&wopVo.getPreclrTimeStart().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME>='"+wopVo.getPreclrTimeStart()+"'");
         if(wopVo.getPreclrTimeEnd()!=null&&wopVo.getPreclrTimeEnd().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME<='"+wopVo.getPreclrTimeEnd()+"'");
         if(wopVo.getPreclrInoiceType()!=null&&wopVo.getPreclrInoiceType().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_INOICE_TYPE='"+wopVo.getPreclrInoiceType()+"'");
         if(wopVo.getRcptBranch()!=null&&wopVo.getRcptBranch().length()>0)
        	 sb.append(" AND frt_reception.RCPT_BRANCH='"+wopVo.getRcptBranch()+"'");
         if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
        	 sb.append(" AND frt_custom.CUSTOM_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCustomName().trim())+"%'");
         if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
        	 sb.append(" AND frt_car.CAR_LICENSE LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCarLicense().trim())+"%'");
         if(wopVo.getCarBrandId()!=null&&wopVo.getCarBrandId().length()>0)
        	 sb.append(" AND bas_car_type.CBRD_ID="+wopVo.getCarBrandId());
         if(wopVo.getCarTypeId()!=null&&wopVo.getCarTypeId().length()>0)
        	 sb.append(" AND bas_car_type.CTYPE_ID="+wopVo.getCarTypeId());
         if(wopVo.getReceptionId()!=null&&wopVo.getReceptionId().length()>0)
        	 sb.append(" AND frt_reception.RECEPTION_ID LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getReceptionId().trim())+"%'");
         if(wopVo.getStoreId()!=null&&wopVo.getStoreId().length()>0)
        	 sb.append(" AND st_out_item.STORE_ID="+wopVo.getStoreId());
         if(wopVo.getClaimsType()!=null&&wopVo.getClaimsType().length()>0)
        	 sb.append(" AND st_out_item.CLAIMS_TYPE in("+wopVo.getClaimsType()+")");
         if(wopVo.getPartsBrandId()!=null&&wopVo.getPartsBrandId().length()>0)
        	 sb.append(" AND bas_parts_brand.PBRD_ID="+wopVo.getPartsBrandId());
         if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
        	 sb.append(" AND frt_parts.PARTS_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getPartsName().trim())+"%'");
         if(wopVo.getReptId()!=null&&wopVo.getReptId().length()>0)
        	 sb.append(" AND frt_reception.REPT_ID="+wopVo.getReptId());
    	 sb.append(" AND st_rt_parts_detail.STORE_ID=parts_change_price.STORE_ID )");
         sb.append(" )AS aa GROUP BY aa.temp1");
         List<Object[]> resultList = frtPreClearingDao.createSQLQuery(sb.toString(),wopVo.getPage(),wopVo.getRows());
         if (resultList != null && resultList.size() > 0)
         {
             Object[] obj = null;
             for (int i = 0; i < resultList.size(); i++)
             {
                 WorkOrderPartsQueryVo wopq = new WorkOrderPartsQueryVo();
                 obj = resultList.get(i);
                 if (obj[0] != null && !"".equals(obj[0]))
                 {
                     wopq.setPartsId(obj[0].toString());
                 }
                 if (obj[1] != null && !"".equals(obj[1]))
                 {
                	  wopq.setPartsName(obj[0].toString());
                 }
                 if (obj[2] != null && !"".equals(obj[2]))
                 {
                     wopq.setPunitName(obj[2].toString());
                 }
                 if (obj[3] != null && !"".equals(obj[3]))
                 {
                	  wopq.setPartsLibrary(obj[3].toString());
                 }
                 if (obj[4] != null && !"".equals(obj[4]))
                 {
                     wopq.setItemCount(obj[4].toString());
                 }
                 if (obj[5] != null && !"".equals(obj[5]))
                 {
                     wopq.setItemAmount(Double.parseDouble(obj[5].toString()));
                 }
                 if (obj[6] != null && !"".equals(obj[6]))
                 {
                     wopq.setTaxCastamont(obj[6].toString());
                 }
                 wopq.setReceptionId("查看详情");
                 wopq.setState("closed");
                 rows.add(wopq);
             }
         }
         int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
    	Datagrid dg=new Datagrid();
    	dg.setRows(rows);
    	dg.setTotal(total);
    	return dg;
	}
    /**
     * 工单结算信息-子项信息(按配件)
     * */
	
	public List<WorkOrderPartsQueryVo> loadStOutAndStRtPartsDetailForParts(
			WorkOrderPartsQueryVo wopVo) throws Exception {
		setDefaultPreclrTimeSect(wopVo);
		if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
    		wopVo.setCustomName(new String(wopVo.getCustomName().getBytes("ISO-8859-1"),"UTF-8"));
    	if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
    		wopVo.setCarLicense(new String(wopVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
    	if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
    		wopVo.setPartsName(new String(wopVo.getPartsName().getBytes("ISO-8859-1"),"UTF-8"));
		List<WorkOrderPartsQueryVo> list=new ArrayList<WorkOrderPartsQueryVo>();
		StringBuffer sb=new StringBuffer("SELECT * FROM (");
		sb.append(" (");
		sb.append(" SELECT st_out_item.PARTS_ID,frt_pre_clearing.RECEPTION_ID,frt_car.CAR_LICENSE,");
		sb.append(" st_out_main.STOM_ID,st_out_main.STOM_DATE,bas_claims_type.CLAIMS_NAME,");		  	 
		sb.append(" st_out_item.ITEM_COUNT, st_out_item.ITEM_PRICE AS A,");		 
		sb.append(" (st_out_item.ITEM_COUNT*st_out_item.ITEM_PRICE) AS sumAmount,");		 
		sb.append(" st_out_item.ITEM_PRICE AS B,st_out_item.TAX_CASTAMONT,");		 
		sb.append(" ROUND(st_out_item.TAX_CASTAMONT/st_out_item.ITEM_COUNT,2) AS C");		  
		sb.append(" FROM frt_pre_clearing,frt_reception,frt_car,bas_car_type,frt_custom,");		 
		sb.append(" st_out_main,st_out_item, frt_parts,bas_parts_brand,bas_parts_type,bas_parts_unit,bas_claims_type");		 
		sb.append(" WHERE 	frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID");		 
		sb.append(" AND frt_pre_clearing.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND frt_reception.CAR_ID=frt_car.CAR_ID");		  
		sb.append(" AND frt_car.CTYPE_ID=bas_car_type.CTYPE_ID AND frt_custom.CUSTOM_ID=frt_reception.CUSTOM_ID");		 
		sb.append(" AND st_out_main.STOM_ID=st_out_item.STOM_ID AND st_out_main.CER_NO=frt_reception.RECEPTION_ID");		 
		sb.append(" AND st_out_item.PARTS_ID=frt_parts.PARTS_ID AND bas_parts_type.PTYPE_ID=frt_parts.PTYPE_ID");		 
		sb.append(" AND bas_parts_brand.PBRD_ID=bas_parts_type.PBRD_ID AND frt_pre_clearing.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);		 
		sb.append(" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID AND bas_claims_type.CLAIMS_ID=st_out_item.CLAIMS_TYPE");
		if(wopVo.getPreclrTimeStart()!=null&&wopVo.getPreclrTimeStart().length()>0)
       	 sb.append(" AND frt_pre_clearing.PRECLR_TIME>='"+wopVo.getPreclrTimeStart()+"'");
        if(wopVo.getPreclrTimeEnd()!=null&&wopVo.getPreclrTimeEnd().length()>0)
       	 sb.append(" AND frt_pre_clearing.PRECLR_TIME<='"+wopVo.getPreclrTimeEnd()+"'");
        if(wopVo.getPreclrInoiceType()!=null&&wopVo.getPreclrInoiceType().length()>0)
       	 sb.append(" AND frt_pre_clearing.PRECLR_INOICE_TYPE='"+wopVo.getPreclrInoiceType()+"'");
        if(wopVo.getRcptBranch()!=null&&wopVo.getRcptBranch().length()>0)
       	 sb.append(" AND frt_reception.RCPT_BRANCH='"+wopVo.getRcptBranch()+"'");
        if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
       	 sb.append(" AND frt_custom.CUSTOM_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCustomName().trim())+"%'");
        if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
       	 sb.append(" AND frt_car.CAR_LICENSE LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCarLicense().trim())+"%'");
        if(wopVo.getCarBrandId()!=null&&wopVo.getCarBrandId().length()>0)
       	 sb.append(" AND bas_car_type.CBRD_ID="+wopVo.getCarBrandId());
        if(wopVo.getCarTypeId()!=null&&wopVo.getCarTypeId().length()>0)
       	 sb.append(" AND bas_car_type.CTYPE_ID="+wopVo.getCarTypeId());
        if(wopVo.getStoreId()!=null&&wopVo.getStoreId().length()>0)
       	 sb.append(" AND st_out_item.STORE_ID="+wopVo.getStoreId());
        if(wopVo.getClaimsType()!=null&&wopVo.getClaimsType().length()>0)
       	 sb.append(" AND st_out_item.CLAIMS_TYPE in("+wopVo.getClaimsType()+")");
        if(wopVo.getPartsBrandId()!=null&&wopVo.getPartsBrandId().length()>0)
       	 sb.append(" AND bas_parts_brand.PBRD_ID="+wopVo.getPartsBrandId());
        if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
       	 sb.append(" AND frt_parts.PARTS_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getPartsName().trim())+"%'");
        if(wopVo.getReptId()!=null&&wopVo.getReptId().length()>0)
       	 sb.append(" AND frt_reception.REPT_ID="+wopVo.getReptId());
		sb.append(" )");		
		sb.append(" UNION");		  
		sb.append(" (");	 
		sb.append(" SELECT st_rt_parts_detail.PARTS_ID,st_rt_parts_main.RECEPTION_ID,frt_car.CAR_LICENSE,");		 
		sb.append(" st_rt_parts_main.STRTPM_ID,st_rt_parts_main.STRTPM_DATE,bas_claims_type.CLAIMS_NAME,");		 
		sb.append(" st_rt_parts_detail.STRTPD_CNT,parts_change_price.PARTS_SELL_PRICE AS A, ");
		sb.append(" (st_rt_parts_detail.STRTPD_CNT*parts_change_price.PARTS_SELL_PRICE) AS sumAmount,");		 
		sb.append(" parts_change_price.PARTS_SELL_PRICE AS B,");		  
		sb.append(" ROUND(parts_change_price.PARTS_LATEST_TAXPRICE*st_rt_parts_detail.STRTPD_CNT,2) AS C,");		  
		sb.append(" ROUND(parts_change_price.PARTS_LATEST_TAXPRICE*st_rt_parts_detail.STRTPD_CNT/st_rt_parts_detail.STRTPD_CNT,2) AS D");		 
		sb.append(" FROM st_rt_parts_main,st_rt_parts_detail,frt_parts,bas_parts_unit,parts_change_price, ");		 
		sb.append(" frt_pre_clearing,frt_reception,frt_car,bas_car_type,frt_custom,");		 
		sb.append(" st_out_main,st_out_item,bas_parts_brand,bas_parts_type,bas_claims_type");		 
		sb.append(" WHERE 	frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID");		  
		sb.append(" AND frt_pre_clearing.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND frt_reception.CAR_ID=frt_car.CAR_ID");		 
		sb.append(" AND frt_car.CTYPE_ID=bas_car_type.CTYPE_ID AND frt_custom.CUSTOM_ID=frt_reception.CUSTOM_ID");		 
		sb.append(" AND st_out_main.STOM_ID=st_out_item.STOM_ID AND st_out_main.CER_NO=frt_reception.RECEPTION_ID");		 
		sb.append(" AND st_out_item.PARTS_ID=frt_parts.PARTS_ID AND bas_parts_type.PTYPE_ID=frt_parts.PTYPE_ID");		 
		sb.append(" AND bas_parts_brand.PBRD_ID=bas_parts_type.PBRD_ID AND frt_pre_clearing.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);		 
		sb.append(" AND st_rt_parts_detail.INDEX_ID=st_out_item.INDEX_ID AND bas_claims_type.CLAIMS_ID=st_out_item.CLAIMS_TYPE");		
		sb.append(" AND  st_rt_parts_main.STRTPM_ID=st_rt_parts_detail.STRTPM_ID");		  
		sb.append(" AND st_rt_parts_main.RECEPTION_ID=frt_reception.RECEPTION_ID AND st_rt_parts_detail.PARTS_ID=frt_parts.PARTS_ID");		  
		sb.append(" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID AND st_rt_parts_detail.PARTS_ID=parts_change_price.PARTS_ID");		  
		sb.append(" AND st_rt_parts_detail.STORE_ID=parts_change_price.STORE_ID");		
		if(wopVo.getPreclrTimeStart()!=null&&wopVo.getPreclrTimeStart().length()>0)
       	 sb.append(" AND frt_pre_clearing.PRECLR_TIME>='"+wopVo.getPreclrTimeStart()+"'");
        if(wopVo.getPreclrTimeEnd()!=null&&wopVo.getPreclrTimeEnd().length()>0)
       	 sb.append(" AND frt_pre_clearing.PRECLR_TIME<='"+wopVo.getPreclrTimeEnd()+"'");
        if(wopVo.getPreclrInoiceType()!=null&&wopVo.getPreclrInoiceType().length()>0)
       	 sb.append(" AND frt_pre_clearing.PRECLR_INOICE_TYPE='"+wopVo.getPreclrInoiceType()+"'");
        if(wopVo.getRcptBranch()!=null&&wopVo.getRcptBranch().length()>0)
       	 sb.append(" AND frt_reception.RCPT_BRANCH='"+wopVo.getRcptBranch()+"'");
        if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
       	 sb.append(" AND frt_custom.CUSTOM_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCustomName().trim())+"%'");
        if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
       	 sb.append(" AND frt_car.CAR_LICENSE LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCarLicense().trim())+"%'");
        if(wopVo.getCarBrandId()!=null&&wopVo.getCarBrandId().length()>0)
       	 sb.append(" AND bas_car_type.CBRD_ID="+wopVo.getCarBrandId());
        if(wopVo.getCarTypeId()!=null&&wopVo.getCarTypeId().length()>0)
       	 sb.append(" AND bas_car_type.CTYPE_ID="+wopVo.getCarTypeId());
        if(wopVo.getStoreId()!=null&&wopVo.getStoreId().length()>0)
       	 sb.append(" AND st_out_item.STORE_ID="+wopVo.getStoreId());
        if(wopVo.getClaimsType()!=null&&wopVo.getClaimsType().length()>0)
       	 sb.append(" AND st_out_item.CLAIMS_TYPE in("+wopVo.getClaimsType()+")");
        if(wopVo.getPartsBrandId()!=null&&wopVo.getPartsBrandId().length()>0)
       	 sb.append(" AND bas_parts_brand.PBRD_ID="+wopVo.getPartsBrandId());
        if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
       	 sb.append(" AND frt_parts.PARTS_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getPartsName().trim())+"%'");
        if(wopVo.getReptId()!=null&&wopVo.getReptId().length()>0)
       	 sb.append(" AND frt_reception.REPT_ID="+wopVo.getReptId());
		sb.append(" )");		 
		sb.append(" )AS A");	  
		sb.append(" WHERE A.PARTS_ID ='"+wopVo.getPartsId()+"'");
		List<Object[]> resultList=baseDao.createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				WorkOrderPartsQueryVo wopq=new WorkOrderPartsQueryVo();
				obj=resultList.get(i);
				if(obj[0]!=null&&!"".equals(obj[0])){
					//wopq.setPartsId(obj[0].toString());
				}
				if(obj[1]!=null&&!"".equals(obj[1])){
					wopq.setReceptionId(obj[1].toString());
				}
				if(obj[2]!=null&&!"".equals(obj[2])){
					wopq.setCarLicense(obj[2].toString());
				}
				if(obj[3]!=null&&!"".equals(obj[3])){
					wopq.setStomId(obj[3].toString());
				}
				if(obj[4]!=null&&!"".equals(obj[4])){
					wopq.setStomDate(MyBeanUtils.getInstance().formatString(obj[4].toString()));
				}
				if(obj[5]!=null&&!"".equals(obj[5])){
					wopq.setClaimsType(obj[5].toString());
				}
				if(obj[6]!=null&&!"".equals(obj[6])){
					wopq.setItemCount(obj[6].toString());				
				}
				if(obj[7]!=null&&!"".equals(obj[7])){
					wopq.setItemPrice(obj[7].toString());
				}
				if(obj[8]!=null&&!"".equals(obj[8])){
					wopq.setItemAmount(Double.parseDouble(obj[8].toString()));
				}
				if(obj[9]!=null&&!"".equals(obj[9])){
					wopq.setItemPriceAvage(obj[9].toString());
				}
				if(obj[10]!=null&&!"".equals(obj[10])){
					wopq.setTaxCastamont(obj[10].toString());
				}
				if(obj[11]!=null&&!"".equals(obj[11])){
					wopq.setTaxCastamontAvage(obj[11].toString());
				}
				wopq.setState("open");
				wopq.set_parentId(wopVo.getPartsId());
				list.add(wopq);
			}
		}
		return list;
	}

	/**
     * 工单结算信息(按工单)
     */
    public Datagrid  loadFrtPreClearing( WorkOrderPartsQueryVo wopVo) throws Exception
    {
    	setDefaultPreclrTimeSect(wopVo);
    	if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
    		wopVo.setCustomName(new String(wopVo.getCustomName().getBytes("ISO-8859-1"),"UTF-8"));
    	if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
    		wopVo.setCarLicense(new String(wopVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
    	if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
    		wopVo.setPartsName(new String(wopVo.getPartsName().getBytes("ISO-8859-1"),"UTF-8"));
    	 List<WorkOrderPartsQueryVo> rows = new ArrayList<WorkOrderPartsQueryVo>();
    	 StringBuffer sb=new StringBuffer("SELECT aa.temp1,aa.temp2,aa.temp3,aa.temp4,SUM(aa.temp5),SUM(aa.temp6),SUM(aa.temp7),enterprise_id"); 
         sb.append(" FROM ( ( SELECT  frt_pre_clearing.RECEPTION_ID AS temp1,frt_pre_clearing.PRECLR_TIME AS temp2,"); 
         sb.append(" frt_car.CAR_LICENSE AS temp3,bas_car_type.CTYPE_NAME AS temp4,st_out_item.ITEM_COUNT AS temp5,");
         sb.append(" (st_out_item.ITEM_COUNT*st_out_item.ITEM_PRICE) AS temp6,st_out_item.TAX_CASTAMONT AS temp7,");
         sb.append(" st_out_main.STOM_ID,frt_pre_clearing.enterprise_id ");
         sb.append(" FROM frt_pre_clearing,frt_reception,frt_car,bas_car_type,frt_custom,");
         sb.append(" st_out_main,st_out_item, frt_parts,bas_parts_brand,bas_parts_type,bas_parts_unit,bas_claims_type WHERE");
         sb.append(" frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID  AND frt_pre_clearing.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES);  	
         sb.append(" AND frt_reception.CAR_ID=frt_car.CAR_ID AND frt_car.CTYPE_ID=bas_car_type.CTYPE_ID");  
         sb.append(" AND frt_custom.CUSTOM_ID=frt_reception.CUSTOM_ID AND st_out_main.STOM_ID=st_out_item.STOM_ID ");  
         sb.append(" AND st_out_main.CER_NO=frt_reception.RECEPTION_ID AND st_out_item.PARTS_ID=frt_parts.PARTS_ID"); 
         sb.append(" AND bas_parts_type.PTYPE_ID=frt_parts.PTYPE_ID AND bas_parts_brand.PBRD_ID=bas_parts_type.PBRD_ID"); 
         sb.append(" AND frt_pre_clearing.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID"); 
         if(wopVo.getPreclrTimeStart()!=null&&wopVo.getPreclrTimeStart().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME>='"+wopVo.getPreclrTimeStart()+"'");
         if(wopVo.getPreclrTimeEnd()!=null&&wopVo.getPreclrTimeEnd().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME<='"+wopVo.getPreclrTimeEnd()+"'");
         if(wopVo.getPreclrInoiceType()!=null&&wopVo.getPreclrInoiceType().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_INOICE_TYPE='"+wopVo.getPreclrInoiceType()+"'");
         if(wopVo.getRcptBranch()!=null&&wopVo.getRcptBranch().length()>0)
        	 sb.append(" AND frt_reception.RCPT_BRANCH='"+wopVo.getRcptBranch()+"'");
         if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
        	 sb.append(" AND frt_custom.CUSTOM_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCustomName().trim())+"%'");
         if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
        	 sb.append(" AND frt_car.CAR_LICENSE LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCarLicense().trim())+"%'");
         if(wopVo.getCarBrandId()!=null&&wopVo.getCarBrandId().length()>0)
        	 sb.append(" AND bas_car_type.CBRD_ID="+wopVo.getCarBrandId());
         if(wopVo.getCarTypeId()!=null&&wopVo.getCarTypeId().length()>0)
        	 sb.append(" AND bas_car_type.CTYPE_ID="+wopVo.getCarTypeId());
         if(wopVo.getReceptionId()!=null&&wopVo.getReceptionId().length()>0)
        	 sb.append(" AND frt_reception.RECEPTION_ID LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getReceptionId().trim())+"%'");
         if(wopVo.getStoreId()!=null&&wopVo.getStoreId().length()>0)
        	 sb.append(" AND st_out_item.STORE_ID="+wopVo.getStoreId());
         if(wopVo.getClaimsType()!=null&&wopVo.getClaimsType().length()>0)
        	 sb.append(" AND st_out_item.CLAIMS_TYPE in("+wopVo.getClaimsType()+")");
         if(wopVo.getPartsBrandId()!=null&&wopVo.getPartsBrandId().length()>0)
        	 sb.append(" AND bas_parts_brand.PBRD_ID="+wopVo.getPartsBrandId());
         if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
        	 sb.append(" AND frt_parts.PARTS_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getPartsName().trim())+"%'");
         if(wopVo.getReptId()!=null&&wopVo.getReptId().length()>0)
        	 sb.append(" AND frt_reception.REPT_ID="+wopVo.getReptId());
         sb.append(" AND bas_claims_type.CLAIMS_ID=st_out_item.CLAIMS_TYPE ) "); 
         sb.append(" UNION"); 
         sb.append(" ( SELECT  frt_pre_clearing.RECEPTION_ID AS temp1,frt_pre_clearing.PRECLR_TIME AS temp2,"); 
         sb.append(" frt_car.CAR_LICENSE AS temp3,bas_car_type.CTYPE_NAME AS temp4,st_rt_parts_detail.STRTPD_CNT AS temp5,");
         sb.append(" (st_rt_parts_detail.STRTPD_CNT*parts_change_price.PARTS_SELL_PRICE) AS temp6,");   
         sb.append(" ROUND(parts_change_price.PARTS_LATEST_TAXPRICE*st_rt_parts_detail.STRTPD_CNT,2) AS temp7,"); 
         sb.append(" st_rt_parts_main.STRTPM_ID,st_rt_parts_main.enterprise_id"); 
         sb.append(" FROM st_rt_parts_main,st_rt_parts_detail,frt_parts,bas_parts_unit,parts_change_price,");  
         sb.append(" frt_pre_clearing,frt_reception,frt_car,bas_car_type,frt_custom, st_out_main,st_out_item,");
         sb.append(" bas_parts_brand,bas_parts_type,bas_claims_type WHERE");
         sb.append(" frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID AND frt_pre_clearing.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES);  
         sb.append(" AND frt_reception.CAR_ID=frt_car.CAR_ID AND frt_car.CTYPE_ID=bas_car_type.CTYPE_ID "); 
         sb.append(" AND frt_custom.CUSTOM_ID=frt_reception.CUSTOM_ID AND st_out_main.STOM_ID=st_out_item.STOM_ID ");
         sb.append(" AND st_out_main.CER_NO=frt_reception.RECEPTION_ID AND st_out_item.PARTS_ID=frt_parts.PARTS_ID");
         sb.append(" AND bas_parts_type.PTYPE_ID=frt_parts.PTYPE_ID AND bas_parts_brand.PBRD_ID=bas_parts_type.PBRD_ID");  
         sb.append(" AND frt_pre_clearing.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+" AND st_rt_parts_detail.INDEX_ID=st_out_item.INDEX_ID");   
         sb.append(" AND bas_claims_type.CLAIMS_ID=st_out_item.CLAIMS_TYPE AND  st_rt_parts_main.STRTPM_ID=st_rt_parts_detail.STRTPM_ID");  
         sb.append(" AND st_rt_parts_main.RECEPTION_ID=frt_reception.RECEPTION_ID AND st_rt_parts_detail.PARTS_ID=frt_parts.PARTS_ID");  
         sb.append(" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID AND st_rt_parts_detail.PARTS_ID=parts_change_price.PARTS_ID");
         if(wopVo.getPreclrTimeStart()!=null&&wopVo.getPreclrTimeStart().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME>='"+wopVo.getPreclrTimeStart()+"'");
         if(wopVo.getPreclrTimeEnd()!=null&&wopVo.getPreclrTimeEnd().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME<='"+wopVo.getPreclrTimeEnd()+"'");
         if(wopVo.getPreclrInoiceType()!=null&&wopVo.getPreclrInoiceType().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_INOICE_TYPE='"+wopVo.getPreclrInoiceType()+"'");
         if(wopVo.getRcptBranch()!=null&&wopVo.getRcptBranch().length()>0)
        	 sb.append(" AND frt_reception.RCPT_BRANCH='"+wopVo.getRcptBranch()+"'");
         if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
        	 sb.append(" AND frt_custom.CUSTOM_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCustomName().trim())+"%'");
         if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
        	 sb.append(" AND frt_car.CAR_LICENSE LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getCarLicense().trim())+"%'");
         if(wopVo.getCarBrandId()!=null&&wopVo.getCarBrandId().length()>0)
        	 sb.append(" AND bas_car_type.CBRD_ID="+wopVo.getCarBrandId());
         if(wopVo.getCarTypeId()!=null&&wopVo.getCarTypeId().length()>0)
        	 sb.append(" AND bas_car_type.CTYPE_ID="+wopVo.getCarTypeId());
         if(wopVo.getReceptionId()!=null&&wopVo.getReceptionId().length()>0)
        	 sb.append(" AND frt_reception.RECEPTION_ID LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getReceptionId().trim())+"%'");
         if(wopVo.getStoreId()!=null&&wopVo.getStoreId().length()>0)
        	 sb.append(" AND st_out_item.STORE_ID="+wopVo.getStoreId());
         if(wopVo.getClaimsType()!=null&&wopVo.getClaimsType().length()>0)
        	 sb.append(" AND st_out_item.CLAIMS_TYPE in("+wopVo.getClaimsType()+")");
         if(wopVo.getPartsBrandId()!=null&&wopVo.getPartsBrandId().length()>0)
        	 sb.append(" AND bas_parts_brand.PBRD_ID="+wopVo.getPartsBrandId());
         if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
        	 sb.append(" AND frt_parts.PARTS_NAME LIKE '%"+StringEscapeUtils.escapeSql(wopVo.getPartsName().trim())+"%'");
         if(wopVo.getReptId()!=null&&wopVo.getReptId().length()>0)
        	 sb.append(" AND frt_reception.REPT_ID="+wopVo.getReptId());
         sb.append(" AND st_rt_parts_detail.STORE_ID=parts_change_price.STORE_ID )");
         sb.append(" )AS aa WHERE aa.enterprise_id="+wopVo.getEnterpriseId()+" GROUP BY aa.temp1");
         List<Object[]> resultList = frtPreClearingDao.createSQLQuery(sb.toString(),wopVo.getPage(),wopVo.getRows());
         if (resultList != null && resultList.size() > 0)
         {
             Object[] obj = null;
             for (int i = 0; i < resultList.size(); i++)
             {
                 WorkOrderPartsQueryVo wopq = new WorkOrderPartsQueryVo();
                 obj = resultList.get(i);
                 if (obj[0] != null && !"".equals(obj[0]))
                 {
                     wopq.setReceptionId(obj[0].toString());
                 }
                 if (obj[1] != null && !"".equals(obj[1]))
                 {
                     wopq.setPreclrTime(MyBeanUtils.getInstance().formatString(obj[1].toString()));
                 }
                 if (obj[2] != null && !"".equals(obj[2]))
                 {
                     wopq.setCarLicense(obj[2].toString());
                 }
                 if (obj[3] != null && !"".equals(obj[3]))
                 {
                     wopq.setCtypeName(obj[3].toString());
                 }
                 if (obj[4] != null && !"".equals(obj[4]))
                 {
                     wopq.setItemCount(obj[4].toString());
                 }
                 if (obj[5] != null && !"".equals(obj[5]))
                 {
                     wopq.setItemAmount(Double.parseDouble(obj[5].toString()));
                 }
                 if (obj[6] != null && !"".equals(obj[6]))
                 {
                     wopq.setTaxCastamont(obj[6].toString());
                 }
                 wopq.setState("closed");
                 rows.add(wopq);
             }
         }
         int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
    	Datagrid dg=new Datagrid();
    	dg.setRows(rows);
    	dg.setTotal(total);
    	return dg;
    }

    /**
     * 工单结算信息-子项信息(按工单)
     */
    public List<WorkOrderPartsQueryVo> loadStOutAndStRtPartsDetail(
            WorkOrderPartsQueryVo workOrderPartsQueryVo) throws Exception
    {
    	setDefaultPreclrTimeSect(workOrderPartsQueryVo);
        return stOutMainDAO.loadStOutAndStRtPartsDetail(workOrderPartsQueryVo
                .getReceptionId(),workOrderPartsQueryVo);
    }

    /**
     * 未确认索赔配件信息 预加载
     */
    public Datagrid loadFinClaimantParts(WorkOrderPartsQueryVo wopVo) throws Exception
    {
    	setDefaultPreclrTimeSect(wopVo);
    	 List<WorkOrderPartsQueryVo> rows = new ArrayList<WorkOrderPartsQueryVo>();
    	 StringBuffer sb=new StringBuffer("SELECT fin_claimant_parts.CLAIMANTP_INDEX, ");
         sb.append(" fin_claimant_parts.PARTS_ID");
         sb.append(" ,fin_claimant_parts.PARTS_NAME,"); 
         sb.append(" fin_claimant_parts.CLAIMANTP_COUNT,");
         sb.append(" fin_claimant_parts.CLAIMANTP_AMOUNT,");
         sb.append(" frt_pre_clearing.PRECLR_TIME");
         sb.append(" FROM fin_claimant_parts,fin_claimant_main,frt_pre_clearing"); 
         sb.append(" WHERE fin_claimant_parts.CLAIMANTM_ID=fin_claimant_main.CLAIMANTM_ID");
         sb.append(" AND fin_claimant_main.CLAIMANTM_STATUS='"+Contstants.AUDIT_TAG.AUDITNOS+"'");
         sb.append(" AND fin_claimant_main.CLAIMANTM_TAG="+Contstants.DELETE_TAG.DELETENO);
         sb.append(" AND fin_claimant_main.PRECLR_ID=frt_pre_clearing.PRECLR_ID");
         if(wopVo.getPreclrTimeStart()!=null&&wopVo.getPreclrTimeStart().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME>='"+wopVo.getPreclrTimeStart()+"'");
         if(wopVo.getPreclrTimeEnd()!=null&&wopVo.getPreclrTimeEnd().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME<='"+wopVo.getPreclrTimeEnd()+"'");
         sb.append(" GROUP BY fin_claimant_parts.CLAIMANTP_INDEX");
         List<Object[]> resultList = frtPreClearingDao.createSQLQuery(sb.toString(),wopVo.getPage(),wopVo.getRows());
         if (resultList != null && resultList.size() > 0)
         {
             Object[] obj = null;
             for (int i = 0; i < resultList.size(); i++)
             {
                 WorkOrderPartsQueryVo wopq = new WorkOrderPartsQueryVo();
                 obj = resultList.get(i);
                 if (obj[0] != null && !"".equals(obj[0]))
                 {
                     wopq.setClaimantpIndex(obj[0].toString());
                 }
                 if (obj[1] != null && !"".equals(obj[1]))
                 {
                     wopq.setPartsId(obj[1].toString());
                 }
                 if (obj[2] != null && !"".equals(obj[2]))
                 {
                     wopq.setPartsName(obj[2].toString());
                 }
                 if (obj[3] != null && !"".equals(obj[3]))
                 {
                     wopq.setClaimantpCount(obj[3].toString());
                 }
                 if (obj[4] != null && !"".equals(obj[4]))
                 {
                     wopq.setClaimantpAmonut(obj[4].toString());
                 }
                 if (obj[5] != null && !"".equals(obj[5]))
                 {
                     wopq.setPreclrTime(MyBeanUtils.getInstance().formatString(obj[5].toString()));
                 }
                 rows.add(wopq);
             }
         }
         int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
         Datagrid dg=new Datagrid();
         dg.setRows(rows);
         dg.setTotal(total);
         return dg;
    }
    private void setDefaultPreclrTimeSect(WorkOrderPartsQueryVo wopVo)throws Exception{
		if((wopVo.getPreclrTimeStart()==null||wopVo.getPreclrTimeStart().length()==0)&&
				(wopVo.getPreclrTimeEnd()==null||wopVo.getPreclrTimeEnd().length()==0)){
			String temp=frtService.findDefaultBalanceTimeSect();
			if(temp!=null&&temp.length()>0){
				Date date=new Date();
				long time=date.getTime();
				long count=Long.parseLong(temp);
				time=time-Math.abs(count*60*60*24*1000);
				wopVo.setPreclrTimeEnd(MyBeanUtils.getInstance().getString(date));
				wopVo.setPreclrTimeStart(MyBeanUtils.getInstance().getString(new Date(time)));
			}
		}
	}
}
