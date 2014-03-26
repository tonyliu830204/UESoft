package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasPartsType;
import com.syuesoft.st.dao.BasPartsTypeDAO;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.util.Json;

/**
 * 配件型号DAO实现类
 * @author SuMing
 */
@Repository("basPartsTypeDAO")
public class BasPartsTypeDAOImpl extends BaseDaoImpl<BasPartsType> implements BasPartsTypeDAO {

	/**
	 * 配件型号信息    预加载
	 */
	public Json loadPartsType(final int page, final int rows, final String sort,final String order,final int enterprise_id)throws Exception
	{
		List<PartsNowCountVo> list=new ArrayList<PartsNowCountVo>();
		String queryString="SELECT bas_parts_type.PTYPE_ID, bas_parts_type.PTYPE_NAME FROM bas_parts_type where bas_parts_type.enterprise_id="+enterprise_id;
		int count =getSQLCount(queryString, null);
		List<Object[]> resultList=createSQLQuery(queryString, page, rows);
		if(resultList!=null&&!resultList.equals("")){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				PartsNowCountVo partsNowCountVo=new PartsNowCountVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					partsNowCountVo.setPtypeId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					partsNowCountVo.setPtypeName(obj[1].toString());
				list.add(partsNowCountVo);
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
	/**
	 * 配件型号信息     综合查询
	 */
	public Json searchPartsTypeByCondition(final String ptypeId,final String ptypeName,final int enterprise_id)throws Exception
	{
		List<PartsNowCountVo> list=new ArrayList<PartsNowCountVo>();
		StringBuffer sb= new StringBuffer("SELECT bas_parts_type.PTYPE_ID,bas_parts_type.PTYPE_NAME FROM bas_parts_type where bas_parts_type.enterprise_id="+enterprise_id);
		if(ptypeId!=null&&!ptypeId.equals(""))
			sb.append(" and bas_parts_type.PTYPE_ID like '%"+StringEscapeUtils.escapeSql(ptypeId.trim())+"%'");
		if(ptypeName!=null&&!ptypeName.equals(""))
			sb.append(" and bas_parts_type.PTYPE_NAME like '%"+StringEscapeUtils.escapeSql(ptypeName.trim())+"%'");
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&!resultList.equals("")){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				PartsNowCountVo partsNowCountVo=new PartsNowCountVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					partsNowCountVo.setPtypeId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					partsNowCountVo.setPtypeName(obj[1].toString());
				list.add(partsNowCountVo);
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
}
