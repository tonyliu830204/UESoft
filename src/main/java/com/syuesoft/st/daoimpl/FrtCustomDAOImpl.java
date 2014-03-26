package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.FrtCustom;
import com.syuesoft.st.dao.FrtCustomDAO;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.Json;

public class FrtCustomDAOImpl extends BaseDaoImpl<FrtCustom> implements FrtCustomDAO {

	/**
	 * 销售单汇总模块     客户信息   预加载
	 */
	public Json loadBasCustom(final int page, final int rows, final String sort,final String order,final int enterprise_id)throws Exception
	{
		List<StSellOrderVo> list=new ArrayList<StSellOrderVo>();
		String queryString ="SELECT frt_custom.CUSTOM_ID,frt_custom.CUSTOM_NAME FROM frt_custom where frt_custom.enterprise_id="+enterprise_id;
		List<Object[]> resultList=createSQLQuery(queryString, page, rows);
		int count=getSQLCount(queryString, null);
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StSellOrderVo sovo=new StSellOrderVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					sovo.setCustomId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					sovo.setCustomName(obj[1].toString());
				list.add(sovo);
			}
		}
		Json json = new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
	/**
	 * 销售单汇总模块     客户信息   预加载
	 */
	public Json searchBasCustomByCondition(final String customId,final String customName,final int enterprise_id)throws Exception
	{
		Json json = new Json();
		List<StSellOrderVo> list=new ArrayList<StSellOrderVo>();
		StringBuffer sb = new StringBuffer("SELECT frt_custom.CUSTOM_ID,frt_custom.CUSTOM_NAME FROM frt_custom where frt_custom.enterprise_id="+enterprise_id+"");
		if(customId != null && !customId.equals("")) 
			sb.append(" and frt_custom.CUSTOM_ID like '%"+StringEscapeUtils.escapeSql(customId.trim())+"%'");
		if(customName != null && !customName.equals("")) 
			sb.append(" and frt_custom.CUSTOM_NAME like '%"+ StringEscapeUtils.escapeSql(customName.trim()) + "%'");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StSellOrderVo sovo=new StSellOrderVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					sovo.setCustomId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					sovo.setCustomName(obj[1].toString());
				list.add(sovo);
			}
		}
		json.setRows(list);
		return json;
	}
}