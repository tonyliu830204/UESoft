package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.INOUTDEPOT;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.model.StPurOrder;
import com.syuesoft.st.dao.StPurOrderDAO;
import com.syuesoft.st.vo.StPurOrderVo;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;

/**
 * 采购单汇总DAO
 * @author SuMing
 */
@Repository("stPurOrderDAO")
public class StPurOrderDAOImpl extends BaseDaoImpl<StPurOrder> implements
		StPurOrderDAO {
	
	@Autowired
	BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * 采购单模块    预加载
	 */
	public Json loadStPurOrder(final String sort,final String order,final int rows,final int page,final String examine,final String state,final int enterpriseId)throws Exception{
		List<StPurOrderVo> list=new ArrayList<StPurOrderVo>();
		StringBuffer sb= new StringBuffer("CALL st_purorder_view(");
		if(examine!=null&&!examine.equals(""))
			sb.append("'"+examine+"',");
		else
			sb.append("'',");
		if(state!=null&&state.equals("StGoodsStorage"))
		    sb.append("'StGoodsStorage',");
		else
			sb.append("'',");
		if (sort != null && order != null) {
			 sb.append("'"+sort+"',");
			 sb.append("'"+order+"',");
        }else{
        	 sb.append("'',");
			 sb.append("'',");
        }
		sb.append(""+enterpriseId+"");
		sb.append(")");
		int count=0;
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			count =resultList.size();
			list=copyObjectVoToListVo(resultList, list);
		}
		Json json=new Json();
		json.setTotal(count);
		json.setRows(list);
		return json;
	}
	
	/**
	 * 采购单模块       综合查询
	 */
	public Json searchByCondition(final String orderDateStart,final String orderDateEnd,final String deliveryDateStart,final String deliveryDateEnd, final String orderId,final String relcampName,final String transitToState
			,final String storageId,final String classification,final String examine,final String paid,final String state,final int enterpriseId)throws Exception
	{
		List<StPurOrderVo> list=new ArrayList<StPurOrderVo>();
		StringBuffer sb= new StringBuffer("CALL st_purorder_search(");
		if(orderDateStart!=null&&!orderDateStart.equals(""))
			sb.append("'"+orderDateStart+"',");
		else if(orderDateStart==null){
			String ciValue=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 
					Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriseId).getCiValue();
			if(ciValue!=null&&!ciValue.equals("")){
				sb.append("'"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, 
						Integer.parseInt(ciValue)) +"',");
			}else
				sb.append("'"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, 
						Integer.parseInt(7+"")) +"',");
		}
		else
			sb.append("'',");
		if(orderDateEnd!=null&&!orderDateEnd.equals(""))
			sb.append("'"+orderDateEnd+"',");
		else if(orderDateEnd==null)
			sb.append("'"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"',");
		else
			sb.append("'',");
		if(deliveryDateStart!=null&&!deliveryDateStart.equals(""))
			sb.append("'"+deliveryDateStart+"',");
		else
			sb.append("'',");
		if(deliveryDateEnd!=null&&!deliveryDateEnd.equals(""))
			sb.append("'"+deliveryDateEnd+"',");
		else
			sb.append("'',");
		if(orderId!=null&&!orderId.equals(""))
			sb.append("'"+StringEscapeUtils.escapeSql(orderId.trim())+"',");
		else
			sb.append("'',");
		if(relcampName!=null&&!relcampName.equals(""))
			sb.append("'"+relcampName+"',");
		else
			sb.append("'',");
		if(transitToState!=null&&!transitToState.equals(""))
			sb.append("'"+transitToState+"',");
		else
			sb.append("'',");
		if(storageId!=null&&!storageId.equals(""))
			sb.append("'"+storageId+"',");
		else
			sb.append("'',");
		if(classification!=null&&!classification.equals(""))
			sb.append("'"+classification+"',");
		else
			sb.append("'',");
		if(examine!=null&&!examine.equals(""))
			sb.append("'"+examine+"',");
		else
			sb.append("'',");
		if(paid!=null&&!paid.equals(""))
			sb.append("'"+paid+"',");
		else
			sb.append("'',");
		if(state!=null&&!state.equals(""))
			sb.append("'"+state+"',");
		else
			sb.append("'',");
		sb.append(""+enterpriseId+"");
		sb.append(")");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		int count =0;
		if(resultList!=null&&resultList.size()>0){
			count =resultList.size();
			list=copyObjectVoToListVo(resultList, list);
		}
		Json json=new Json();
		json.setTotal(count);
		json.setRows(list);
		return json;
	}
	
	public List<StPurOrderVo> copyObjectVoToListVo(List<Object[]> resultList,List<StPurOrderVo> list){
		if(resultList!=null&&resultList.size()>0){
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				StPurOrderVo stPurOrderVo = new StPurOrderVo();
				obj = (Object[]) resultList.get(i);
				if (obj[0] != null && !obj[0].equals("")) 
					stPurOrderVo.setOrderId(obj[0].toString());
				if (obj[1] != null && !obj[1].equals("")) {
					stPurOrderVo.setOrderDate(obj[1].toString().substring(0, 19));
					stPurOrderVo.setOrderDates(obj[1].toString().substring(0, 10));
				}
				if (obj[2] != null && !obj[2].equals("")) 
					stPurOrderVo.setRelcampName(obj[2].toString());
				if (obj[3] != null && !obj[3].equals(""))
					stPurOrderVo.setRelcampId(obj[3].toString());
				if (obj[4] != null && !obj[4].equals("")){
					stPurOrderVo.setDeliveryDate(obj[4].toString().substring(0, 19));
					stPurOrderVo.setDeliveryDates(obj[4].toString().substring(0, 10));
				}
				if (obj[5] != null && !obj[5].equals(""))
					stPurOrderVo.setNumTotal(obj[5].toString());
				if (obj[6] != null && !obj[6].equals("")) 
					stPurOrderVo.setTotalAmount(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL, obj[6].toString()));
				if (obj[7] != null && !obj[7].equals("")) 
					stPurOrderVo.setManager(obj[7].toString());
				if (obj[8] != null && !obj[8].equals("")) 
					stPurOrderVo.setManagerName(obj[8].toString());
				if (obj[9] != null && !obj[9].equals("")) 
					stPurOrderVo.setBuyer(obj[9].toString());
				if (obj[10] != null && !obj[10].equals("")) 
					stPurOrderVo.setBuyerName(obj[10].toString());
				if (obj[11] != null && !obj[11].equals("")) 
					stPurOrderVo.setOrderRemark(obj[11].toString());
				if (obj[12] != null && !obj[12].equals("")) 
					stPurOrderVo.setNotesType(obj[12].toString());
				if (obj[13] != null && !obj[13].equals("")) 
					stPurOrderVo.setClassification(obj[13].toString());
				if (obj[14] != null && !obj[14].equals("")) {
		            if(obj[14].toString().equals("未审核"))
		            	stPurOrderVo.setExamineName(new BaseAction().changeColor(obj[14].toString(), "red"));
		            else
		            	stPurOrderVo.setExamineName(obj[14].toString());
				}
				if (obj[15] != null && !obj[15].equals("")) 
					stPurOrderVo.setTransitToState(obj[15].toString());
				if (obj[16] != null && !obj[16].equals("")) 
						stPurOrderVo.setPaidName(obj[16].toString());
				if (obj[17] != null && !obj[17].equals("")) 
					stPurOrderVo.setTaxRate(new BaseAction().integerChange(obj[17].toString()));
				if (obj[18] != null && !obj[18].equals("")) 
					stPurOrderVo.setNotaxTotalamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL, obj[18].toString()));
				if (obj[19] != null && !obj[19].equals("")) 
					stPurOrderVo.setTaxCount(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL, obj[19].toString()));
				if (obj[20] != null && !obj[20].equals("")) 
					stPurOrderVo.setStorageId(obj[20].toString());
				if (obj[21] != null && !obj[21].equals("")) 
					stPurOrderVo.setNotesTypeId(obj[21].toString());
				if (obj[22] != null && !obj[22].equals("")) 
					stPurOrderVo.setClassificationId(obj[22].toString());
				if(obj[23]!=null&&obj[23].equals(""))
					stPurOrderVo.setExamine(obj[23].toString());
				if(obj[24]!=null&&obj[24].equals(""))
					stPurOrderVo.setPaid(obj[24].toString());
				if(obj[25]!=null&&obj[25].equals(""))
					stPurOrderVo.setTransitToStateId(obj[25].toString());
				list.add(stPurOrderVo);
			}
		}
		return list;
	}
}