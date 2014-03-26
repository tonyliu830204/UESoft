package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.StPreOutDetail;
import com.syuesoft.st.dao.StPreOutDetailDAO;
import com.syuesoft.st.vo.StPreOutVo;

@Repository("stPreOutDetailDAO")
public class StPreOutDetailDAOImpl extends BaseDaoImpl<StPreOutDetail> implements
		StPreOutDetailDAO {

	/**
	 * 通过预出库汇总Id查找预出库明细多表信息
	 */
	public List<StPreOutVo> searchStPreOutDetailByStpremId(final String stpremId)throws Exception
	{
		List<StPreOutVo> list=new ArrayList<StPreOutVo>();
		StringBuffer sb=new StringBuffer(" SELECT FIT_PTYPE,PARTS_ID,PARTS_NAME,PUNIT_NAME,STPRED_CNT,PARTS_NOW_COUNT,"+
		" ITEMPRICE,AMONT,TAXCAST,TAXCASTAMONT,PARTS_LIBRARY,STORE_NAME,"+
		" CLAIMS_NAME,REPTYP_NAME,NOTAXCAST,NOTAXCASTAMONT,STORE_ID,CLAIMS_ID,STPREM_ID FROM("+
		" SELECT e.*,bas_repair_type.REPTYP_NAME FROM ("+
		" SELECT d.*,bas_claims_type.CLAIMS_NAME FROM ("+
		" SELECT c.*,bas_storehouse.STORE_NAME FROM ("+
		" SELECT b.*,parts_change_price.PARTS_NOW_COUNT FROM ("+
		" SELECT a.*,bas_parts_unit.PUNIT_NAME FROM ("+
		" SELECT st_pre_out_detail.*,frt_parts.PUNIT_NAME AS PUNIT_ID,frt_parts.FIT_PTYPE,frt_parts.PARTS_NAME,frt_parts.PARTS_LIBRARY"+
		" FROM st_pre_out_detail LEFT JOIN frt_parts ON st_pre_out_detail.PARTS_ID=frt_parts.PARTS_ID) AS a"+
		" LEFT JOIN bas_parts_unit ON a.PUNIT_ID=bas_parts_unit.PUNIT_ID) AS b"+
		" LEFT JOIN parts_change_price ON b.PARTS_ID=parts_change_price.PARTS_ID"+
		" AND b.STORE_ID=parts_change_price.STORE_ID) AS c"+
		" LEFT JOIN bas_storehouse ON c.STORE_ID=bas_storehouse.STORE_ID) AS d"+
		" LEFT JOIN bas_claims_type ON d.CLAIMS_ID=bas_claims_type.CLAIMS_ID) AS e"+
		" LEFT JOIN bas_repair_type ON e.CHARGE_ID=bas_repair_type.REPTYP_ID) AS f where 1=1 ");
		if(stpremId!=null&&!"".equals(stpremId))
			sb.append(" AND STPREM_ID ='"+stpremId+"'");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StPreOutVo spovo=new StPreOutVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					spovo.setFitPtype(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					spovo.setPartsId(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					spovo.setPartsName(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					spovo.setPunitName(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					spovo.setItemCount(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					spovo.setPartsNowCount(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					spovo.setItemPrice(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					spovo.setAmount(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					spovo.setTaxCast(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					spovo.setTaxCastamont(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					spovo.setPartsLibrary(obj[10].toString());
				if(obj[11]!=null&&!obj[11].equals(""))
					spovo.setStoreName(obj[11].toString());
				if(obj[12]!=null&&!obj[12].equals(""))
					spovo.setClaimsType(obj[12].toString());
				if(obj[13]!=null&&!obj[13].equals(""))
					spovo.setItemCharge(obj[13].toString());
				if(obj[14]!=null&&!obj[14].equals(""))
					spovo.setNotaxCast(obj[14].toString());
				if(obj[15]!=null&&!obj[15].equals(""))
					spovo.setNotaxCastamont(obj[15].toString());
				if(obj[16]!=null&&!obj[16].equals(""))
					spovo.setStoreId(obj[16].toString());
				if(obj[17]!=null&&!obj[17].equals(""))
					spovo.setClaimsId(obj[17].toString());
				list.add(spovo);
			}
		}
		return list;
	}
}