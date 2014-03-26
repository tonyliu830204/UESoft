package com.syuesoft.bas.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.StageAddpriceDao;
import com.syuesoft.base.vo.StageAddpriceVo;
import com.syuesoft.model.StageAddprice;
import com.syuesoft.util.Json;

/**
 * 剃度加价模块DaoImpl
 * 
 * @author SuMing
 */
@Repository("stageAddpriceDao")
public class StageAddpriceDaoImpl extends BaseDaoImpl<StageAddprice> implements
        StageAddpriceDao
{

    /**
     * 基础资料 配件性质 梯度信息 分页
     */
    public Json findAll(final StageAddpriceVo stageAddpriceVo) throws Exception
    {
    	List<StageAddprice> list=new ArrayList<StageAddprice>();
    	StringBuffer sb=new StringBuffer("select* from (SELECT STAGEID AS stageId,StartAmont AS startAmont,EndAmont AS endAmont,REPAIR_RATE AS repairRate,"+
		" SELL_RATE AS sellRate,POINT_RATE AS pointRate,SPECIAL_RATE AS specialRate,CLAIM_RATE AS claimRate "+
		" FROM stage_addprice WHERE enterprise_id="+stageAddpriceVo.getEnterpriseId()+") as a");
    	if (stageAddpriceVo.getSort() != null || stageAddpriceVo.getOrder() != null)
    		sb.append(" order by " +stageAddpriceVo.getSort() + " " + stageAddpriceVo.getOrder());
    	List<Object[]> resultList=createSQLQuery(sb.toString());
    	if(resultList!=null&&resultList.size()>0){
    		Object[] obj=null;
    		for (int i = 0; i < resultList.size(); i++) {
    			StageAddprice sa=new StageAddprice();
    			obj=resultList.get(i);
    			if(obj[0]!=null&&!obj[0].equals(""))
    				sa.setStageId(Short.parseShort(obj[0].toString()));
    			if(obj[1]!=null&&!obj[1].equals(""))
    				sa.setStartAmont(Double.parseDouble(obj[1].toString()));
    			if(obj[2]!=null&&!obj[2].equals(""))
    				sa.setEndAmont(Double.parseDouble(obj[2].toString()));
    			if(obj[3]!=null&&!obj[3].equals(""))
    				sa.setRepairRate(Double.parseDouble(obj[3].toString()));
    			if(obj[4]!=null&&!obj[4].equals(""))
    				sa.setSellRate(Double.parseDouble(obj[4].toString()));
    			if(obj[5]!=null&&!obj[5].equals(""))
    				sa.setPointRate(Double.parseDouble(obj[5].toString()));
    			if(obj[6]!=null&&!obj[6].equals(""))
    				sa.setSpecialRate(Double.parseDouble(obj[6].toString()));
    			if(obj[7]!=null&&!obj[7].equals(""))
    				sa.setClaimRate(Double.parseDouble(obj[7].toString()));
    			list.add(sa);
			}
    	}
    	int count =getSQLCount(sb.toString(),null);
    	Json json=new Json();
    	json.setRows(list);
    	json.setTotal(count);
    	return json;
    }
}
