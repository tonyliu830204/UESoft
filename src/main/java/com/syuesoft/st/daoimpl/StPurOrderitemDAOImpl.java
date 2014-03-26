package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants.INOUTDEPOT;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.model.StPurOrderitem;
import com.syuesoft.st.dao.StPurOrderitemDAO;
import com.syuesoft.st.vo.StPurOrderVo;

@Repository("stPurOrderitemDAO")
public class StPurOrderitemDAOImpl extends BaseDaoImpl<StPurOrderitem> implements
		StPurOrderitemDAO {
	
	@Autowired
	BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * (采购单模块, 入库单模块)  根据采购单单号获取采购明细信息
	 */
	public List<StPurOrderVo> searchStOrderItemByOrderId(final String orderId) throws Exception {
		List<StPurOrderVo> list = new ArrayList<StPurOrderVo>();
		StringBuffer sb=new StringBuffer("");
		if(orderId!=null&&!orderId.trim().equals(""))
			sb.append("CALL st_purorderitem_by_orderid('"+orderId.trim()+"')");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				StPurOrderVo stPurOrderVo = new StPurOrderVo();
				obj = (Object[]) resultList.get(i);
				if (obj[0] != null && !obj[0].equals("")) 
					stPurOrderVo.setStpredIndex(obj[0].toString());
				if (obj[1] != null && !obj[1].equals("")) 
					stPurOrderVo.setPartsId(obj[1].toString());
				if (obj[2] != null && !obj[2].equals("")) 
					stPurOrderVo.setPartsName(obj[2].toString());
				if (obj[3] != null && !obj[3].equals("")) 
					stPurOrderVo.setPtypeName(obj[3].toString());
				if (obj[4] != null && !obj[4].equals("")) 
					stPurOrderVo.setPbrdName(obj[4].toString());
				if (obj[5] != null && !obj[5].equals("")) 
					stPurOrderVo.setPunitName(obj[5].toString());
				if (obj[6] != null && !obj[6].equals("")){
					stPurOrderVo.setPartsNum(obj[6].toString());
					stPurOrderVo.setTransitToCount(obj[6].toString());
				}
				if (obj[7] != null && !obj[7].equals("")) 
					stPurOrderVo.setTaxPrice(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL, obj[7].toString()));
				if (obj[8] != null && !obj[8].equals("")) 
					stPurOrderVo.setNotaxPrice(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL, obj[8].toString()));
				if (obj[9] != null && !obj[9].equals("")) 
					stPurOrderVo.setTaxTotalamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL, obj[9].toString()));
				if (obj[10] != null && !obj[10].equals("")) 
					stPurOrderVo.setNotaxTotalamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL, obj[10].toString()));
				if (obj[11] != null && !obj[11].equals("")) 
					stPurOrderVo.setPartsLibrary(obj[11].toString());
				if (obj[12] != null && !obj[12].equals("")) 
					stPurOrderVo.setTaxCount(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL, obj[12].toString()));
				if (obj[13] != null && !obj[13].equals("")) 
					stPurOrderVo.setItemRemark(obj[13].toString());
				list.add(stPurOrderVo);
			}
		}
		return list;
	}

}
