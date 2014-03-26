package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.fin.vo.RelcampBalanceOfAccountVo;
import com.syuesoft.model.BasRelationCampany;
import com.syuesoft.st.dao.BasRelationCampanyDAO;
import com.syuesoft.st.vo.StPurOrderVo;
import com.syuesoft.util.Json;

/**
 * 供应商DaoImpl
 * @author SuMing
 */
@Repository("basRelationCampanyDAO")
public class BasRelationCampanyDAOImpl extends BaseDaoImpl<BasRelationCampany> implements
		BasRelationCampanyDAO {

	/**
	 * 供应商信息查询(按供应商编号，名称查询)
	 */
	public Json loadBasRelationCampany(final int page, final int rows, final String sort,final String order,final int enterprise_id)throws Exception
	{
		List<StPurOrderVo> list = new ArrayList<StPurOrderVo>();
		StringBuffer sb= new StringBuffer("select * from (SELECT bas_relation_campany.RELCAMP_ID as relcampId,bas_relation_campany.RELCAMP_NAME as relcampName,"+
				" bas_relation_campany.RELCAMP_TEL1 as relcampTel1,bas_relation_campany.RELCAMP_CONTACT as relcampContact, bas_relation_campany.enterprise_id"+
				" FROM bas_relation_campany WHERE bas_relation_campany.RELCAMP_FLG=0) as a where enterprise_id="+enterprise_id);
		if(sort!=null||order!=null)
			sb.append(" order by "+sort+" "+order);
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString(), page, rows);
		if(resultList!=null&&resultList.size()>0){
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				StPurOrderVo stPurOrderVo = new StPurOrderVo();
				obj = (Object[]) resultList.get(i);
				if (obj[0] != null && !obj[0].equals("")) 
					stPurOrderVo.setRelcampId(obj[0].toString());
				if (obj[1] != null && !obj[1].equals("")) 
					stPurOrderVo.setRelcampName(obj[1].toString());
				if (obj[2] != null && !obj[2].equals("")) 
					stPurOrderVo.setRelcampTel1(obj[2].toString());
				if (obj[3] != null && !obj[3].equals("")) 
					stPurOrderVo.setRelcampContact(obj[3].toString());
				list.add(stPurOrderVo);
			}
		}
		Json json = new Json();
		json.setTotal(count);
		json.setRows(list);
		return json;
	}
	
	/**
	 *  入库单模块     供应商信息查询(按供应商编号，名称查询)
	 */
	public Json searchBasRelationCampanyByCondition(final String relcampId,final String relcampName,final int enterprise_id)throws Exception
	{
		List<StPurOrderVo> list = new ArrayList<StPurOrderVo>();
		StringBuffer sb= new StringBuffer("SELECT bas_relation_campany.RELCAMP_ID,bas_relation_campany.RELCAMP_NAME,"+
							" bas_relation_campany.RELCAMP_TEL1,bas_relation_campany.RELCAMP_CONTACT"+
							" FROM bas_relation_campany WHERE bas_relation_campany.RELCAMP_FLG=0 and enterprise_id="+enterprise_id);
		if(relcampId!=null&&!relcampId.equals(""))
			sb.append(" and bas_relation_campany.RELCAMP_ID like '%"+Short.parseShort(relcampId.trim())+"%'");
		if(relcampName!=null&&!relcampName.equals(""))
			sb.append(" and bas_relation_campany.RELCAMP_NAME like '%"+StringEscapeUtils.escapeSql(relcampName.trim())+"%' or bas_relation_campany.RELCAMP_JM like '%"+StringEscapeUtils.escapeSql(relcampName.trim())+"%'");
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				StPurOrderVo stPurOrderVo = new StPurOrderVo();
				obj = (Object[]) resultList.get(i);
				if (obj[0] != null && !obj[0].equals("")) 
					stPurOrderVo.setRelcampId(obj[0].toString());
				if (obj[1] != null && !obj[1].equals("")) 
					stPurOrderVo.setRelcampName(obj[1].toString());
				if (obj[2] != null && !obj[2].equals("")) 
					stPurOrderVo.setRelcampTel1(obj[2].toString());
				if (obj[3] != null && !obj[3].equals("")) 
					stPurOrderVo.setRelcampContact(obj[3].toString());
				list.add(stPurOrderVo);
			}
		}
		Json json = new Json();
		json.setTotal(count);
		json.setRows(list);
		return json;
	}
	
	/**
	 * 财务模块    供应商对账   入退单汇总信息加载
	 */
	public Json loadStRtGoods(String relcampId)throws Exception{
		Json json=new Json();
		List<RelcampBalanceOfAccountVo> list=new ArrayList<RelcampBalanceOfAccountVo>();
		StringBuffer sb=new StringBuffer("SELECT st_goods_storage.STORAGE_ID,st_goods_storage.STORAGE_DATE"+
		" ,st_goods_storage.TOTAL_NUM,st_goods_storage.TOTAL_AMOUNT AS TOTAL_AMOUNT,"+
		" st_goods_storage.TOTAL_AMOUNT AS COST_AMOUNT,st_goods_storage.REMARK FROM st_goods_storage WHERE 1=1 ");
		if(relcampId!=null&&!relcampId.trim().equals(""))
		   sb.append(" AND st_goods_storage.RELCAMP_ID="+relcampId.trim()+"");
		sb.append(" UNION SELECT st_rt_goods_main.STRTGM_ID,st_rt_goods_main.STRTGM_DATE"+
		" ,st_rt_goods_main.NUM_TOTAL,st_rt_goods_main.STRTGM_SUM_COST AS TOTAL_AMOUNT"+
		" ,st_rt_goods_main.STRTGM_SUM_COST AS COST_AMOUNT,st_rt_goods_main.STRTGM_REMARK FROM st_rt_goods_main WHERE 1=1 ");
		if(relcampId!=null&&!relcampId.trim().equals(""))
		   sb.append(" AND st_rt_goods_main.RELCAMP_ID="+relcampId.trim());
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				RelcampBalanceOfAccountVo sravo=new RelcampBalanceOfAccountVo();
				obj=resultList.get(i);
				if(obj[0]!=null&&!"".equals(obj[0]))
					sravo.setReceiptId(obj[0].toString());
				if(obj[1]!=null&&!"".equals(obj[1]))
					sravo.setStorageDate(obj[1].toString());			
				if(obj[2]!=null&&!"".equals(obj[2]))
					sravo.setTotalNum(obj[2].toString());
				if(obj[3]!=null&&!"".equals(obj[3]))
					sravo.setTotalAmount(obj[3].toString());
				if(obj[4]!=null&&!"".equals(obj[4]))
					sravo.setTaxCount(obj[4].toString());
				if(obj[5]!=null&&!"".equals(obj[5]))
					sravo.setRemark(obj[5].toString());
				list.add(sravo);
			}
		}
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
}
