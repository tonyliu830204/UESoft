package com.syuesoft.fin.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.fin.dao.StSellchargeItemDao;
import com.syuesoft.fin.vo.StSellChargeVo;
import com.syuesoft.model.StSellChargeitem;

/**
 * 销售单明细DAO实现类
 * @author SuMing
 */
@Repository("stSellchargeItemDao")
public class StSellchargeItemDaoImpl extends BaseDaoImpl<StSellChargeitem> implements StSellchargeItemDao {

	/**
	 * 销售应收款明细     预加载
	 */
	
	public List<StSellChargeVo> loadStSellchargeItemByChargeId(String chargeId) throws Exception {
		List<StSellChargeVo> list=new ArrayList<StSellChargeVo>();
		StringBuffer sql=new StringBuffer("SELECT st_sell_chargeitem.SSC_ID,"+
					" st_sell_chargeitem.SSC_PAYMENT_MONEY,"+
					" bas_stuff.STF_NAME,"+
					" bas_childdictionary.dataValue,"+
					" st_sell_chargeitem.SSC_DATE,"+
					" st_sell_chargeitem.SSC_REMARK,st_sell_chargeitem.CHARGE_ID"+
					" FROM st_sell_chargeitem,bas_stuff,bas_childdictionary"+
					" WHERE st_sell_chargeitem.STF_ID=bas_stuff.STF_ID"+
					" AND st_sell_chargeitem.PAYMENT_ID=bas_childdictionary.child_id AND st_sell_chargeitem.CHARGE_ID='"+chargeId+"'");
		List<Object[]> resultList=createSQLQuery(sql.toString(), null);
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StSellChargeVo stSellChargeVo=new StSellChargeVo();
                obj = resultList.get(i);
                if(obj[0]!=null&&!"".equals(obj[0])){
					stSellChargeVo.setCollectId(obj[0].toString());
				}
				if(obj[1]!=null&&!"".equals(obj[1])){
					stSellChargeVo.setPaidAmount(obj[1].toString());
				}
				if(obj[2]!=null&&!"".equals(obj[2])){
					stSellChargeVo.setPatment(obj[2].toString());			
				}
				if(obj[3]!=null&&!"".equals(obj[3])){
					stSellChargeVo.setPaidPerson(obj[3].toString());
				}
				if(obj[4]!=null&&!"".equals(obj[4])){
					stSellChargeVo.setPaidDate(obj[4].toString().substring(0,19));
				}
				if(obj[5]!=null&&!"".equals(obj[5])){
					stSellChargeVo.setRemark(obj[5].toString());
				}
				if(obj[6]!=null&&!"".equals(obj[6])){
					stSellChargeVo.setChargeId(obj[6].toString());
				}
				list.add(stSellChargeVo);
			}
		}
		return list;
	}


}
