package com.syuesoft.st.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.MonthlyDetail;
import com.syuesoft.model.MonthlyStatement;
import com.syuesoft.st.dao.MonthlyStatementDao;
import com.syuesoft.st.service.MonthlyStatementService;
import com.syuesoft.st.vo.MonthlyStatementVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatDate;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;

@Service("monthlyStatementService")
public class MonthlyStatementServiceImpl implements MonthlyStatementService {

	@Autowired 
	private MonthlyStatementDao monthlyStatementDao;
	
	/**
	 * 加载月结数据
	 */
    
	public Json findAllMonthlyStatemont(MonthlyStatementVo msvo) throws Exception {
		List<MonthlyStatementVo> list = new ArrayList<MonthlyStatementVo>();
		StringBuffer buf = new StringBuffer("SELECT a.MS_ID,a.MS_NUMBER,a.STF_ID,DATE_FORMAT(a.MS_STARTTIME,'%Y-%m-%d %H:%i:%s') AS MS_STARTTIME,DATE_FORMAT(a.MS_ENDTIME,'%Y-%m-%d %H:%i:%s') AS MS_ENDTIME,");
		buf.append("DATE_FORMAT(a.MS_COLSEDTIME,'%Y-%m-%d %H:%i:%s') AS MS_COLSEDTIME,DATE_FORMAT(a.MS_REMINDTIME,'%Y-%m-%d %H:%i:%s') AS MS_REMINDTIME,DATE_FORMAT(a.MS_NEXTTIME,'%Y-%m-%d %H:%i:%s') AS MS_NEXTTIME,");
		buf.append("a.MS_REMARK,b.STF_NAME FROM st_monthly_statement a, bas_stuff b WHERE a.STF_ID = b.STF_ID and a.enterprise_id="+msvo.getEnterpriseId());
		if(msvo.getMsStarttime() != null && !"".equals(msvo.getMsStarttime())){
		    buf.append(" AND a.MS_STARTTIME >= '"+msvo.getMsStarttime()+"' ");
		}
		if(msvo.getMsEndtime() != null && !"".equals(msvo.getMsEndtime())){
		    buf.append(" AND a.MS_ENDTIME <= '"+msvo.getMsEndtime()+"' ");
		}
		if(msvo.getOperStartDate() != null && !"".equals(msvo.getOperStartDate())){
		    buf.append(" AND a.MS_COLSEDTIME >= '"+msvo.getOperStartDate()+"' ");
		}
		if(msvo.getOperEndDate() != null && !"".equals(msvo.getOperEndDate())){
		    buf.append(" AND a.MS_COLSEDTIME <= '"+msvo.getOperEndDate()+"' ");
		}
		if(msvo.getMsStartRemindtime() != null && !"".equals(msvo.getMsStartRemindtime())){
		    buf.append(" AND a.MS_REMINDTIME >= '"+msvo.getMsStartRemindtime()+"' ");
		}
		if(msvo.getMsEndRemindtime() != null && !"".equals(msvo.getMsEndRemindtime())){
		    buf.append(" AND a.MS_REMINDTIME <= '"+msvo.getMsEndRemindtime()+"' ");
		}
		List<Object[]> resultList1 = monthlyStatementDao.createSQLQuery(buf.toString(), msvo.getPage(), msvo.getRows());
		int size = monthlyStatementDao.getCountBySQL(buf.toString());
		if (resultList1 != null && resultList1.size() > 0) {
			for (Object[] obj : resultList1) {
				MonthlyStatementVo monthlyStatementVo = new MonthlyStatementVo();
				if(obj[0] != null){monthlyStatementVo.setMsId(obj[0].toString());};
				if(obj[1] != null){monthlyStatementVo.setMsNumber(obj[1].toString());};
				if(obj[2] != null){monthlyStatementVo.setStfId(obj[2].toString());};
				if(obj[3] != null){monthlyStatementVo.setMsStarttime(obj[3].toString());};
				if(obj[4] != null){monthlyStatementVo.setMsEndtime(obj[4].toString());};
				if(obj[5] != null){monthlyStatementVo.setOperDate(obj[5].toString());};
				if(obj[6] != null){monthlyStatementVo.setMsRemindtime(obj[6].toString());};
				if(obj[7] != null){monthlyStatementVo.setMsNexttime(obj[7].toString());};
				if(obj[8] != null){monthlyStatementVo.setMsRemark(obj[8].toString());};
				if(obj[9] != null){monthlyStatementVo.setStfName(obj[9].toString());};
				list.add(monthlyStatementVo);
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(size);
		return json;
	}
	
    /**
     * 获取月结表中本次月结开始时间
     */
    public MonthlyStatementVo loadStratTime(MonthlyStatementVo monthlyStatementVo)throws Exception{
        MonthlyStatementVo vo = loadEndLogo();
        String sql = "SELECT MS_ID, MS_ENDTIME FROM st_monthly_statement WHERE enterprise_id="+monthlyStatementVo.getEnterpriseId()+" AND MS_REMINDTIME IS NULL ORDER BY MS_ENDTIME DESC LIMIT 0, 1";
        List<Object[]> list=monthlyStatementDao.createSQLQuery(sql);
        if(list != null && list.size() > 0){
            vo.setMsStarttime(FormatTime.timestamp2Str((Timestamp) list.get(0)[1]));
        }else{
            sql="SELECT STORAGE_ID,STORAGE_DATE FROM st_goods_storage where enterprise_id="+monthlyStatementVo.getEnterpriseId()+" ORDER BY STORAGE_DATE ASC LIMIT 0, 1";
            list=monthlyStatementDao.createSQLQuery(sql);
            if(list!=null&&list.size()>0){
                vo.setMsStarttime(FormatTime.timestamp2Str1((Timestamp) list.get(0)[1], FormatTime.DEFAULT_FORMAT));
            }
        }
        return vo;
    }
	
	/**
	 * 月结
	 */
	@SuppressWarnings("unchecked")
    public String add(MonthlyStatementVo monthlyStatementVo, BasUsers user)throws Exception{
	    MonthlyStatement ms=new MonthlyStatement();
	    String id = null;
		if(monthlyStatementVo.getMsStarttime() != null && !"".equals(monthlyStatementVo.getMsStarttime()) &&
		    monthlyStatementVo.getMsEndtime() != null && !"".equals(monthlyStatementVo.getMsEndtime())){
			String company = user.getBasStuff().getEnterpriseInfo().getEnterpriseId().toString();
			List<Object> params = new ArrayList<Object>();
	        params.add(0,monthlyStatementVo.getMsStarttime());
	        params.add(1,monthlyStatementVo.getMsEndtime());
	        params.add(2,"");
	        params.add(3,"");
	        params.add(4,"");
	        params.add(5,"");
	        params.add(6,company);
	        StringBuffer procedureName=new StringBuffer(" { CALL Invoicing2_stock_1(?,?,?,?,?,?,?) } ");
	        List<Object[]> resultList = monthlyStatementDao.getProcedureQuery(procedureName.toString(), params);
	        
	        if(resultList!=null&&!"".equals(resultList)){
	            if(monthlyStatementVo.getMsStarttime()!= null && !"".equals(monthlyStatementVo.getMsStarttime()))
	                ms.setMsStarttime(FormatTime.str2Date(monthlyStatementVo.getMsStarttime(),FormatTime.DEFAULT_FORMAT));
	            if(monthlyStatementVo.getMsEndtime()!=null && !"".equals(monthlyStatementVo.getMsEndtime()))
	                ms.setMsEndtime(Utils.dateFormat(monthlyStatementVo.getMsEndtime(),false));
	            if(monthlyStatementVo.getMsNexttime()!=null && !"".equals(monthlyStatementVo.getMsNexttime()))
	                ms.setMsNexttime(FormatTime.str2Date(monthlyStatementVo.getMsNexttime(),FormatTime.DEFAULT_FORMAT));
	            if(monthlyStatementVo.getMsRemark()!=null && !"".equals(monthlyStatementVo.getMsRemark()))
	                ms.setMsRemark(monthlyStatementVo.getMsRemark());
	            ms.setEnterpriseId(monthlyStatementVo.getEnterpriseId());
	            ms.setOperDate(new Date());
	            ms.setStfId(user.getBasStuff().getStfId());
	            ms.setMsNumber(CreateID.createId("st_monthly_statement", "YJ"));
			    for(Object[] obj : resultList){
			        MonthlyDetail md = new MonthlyDetail();
			        md.setMonthlyStatement(ms);
			        md.setPartsId(obj[0].toString());
			        md.setStoreId(obj[1].toString());
			        md.setStinvdCount(Double.parseDouble(obj[2].toString()));
			        md.setStinvdCost(md.getStinvdCount() != 0.0d ? Double.parseDouble(obj[3].toString()) : 0.0d);
			        md.setStinvdCost1(md.getStinvdCount() != 0.0d ? Double.parseDouble(obj[4].toString()) : 0.0d);
			        md.setStinvdPrice(md.getStinvdCount() != 0.0d && md.getStinvdCost() != 0.0d ? md.getStinvdCost() / md.getStinvdCount() : 0.0d);
			        md.setStinvdPrice1(md.getStinvdCount() != 0.0d && md.getStinvdCost() != 0.0d ? md.getStinvdCost1() / md.getStinvdCount() : 0.0d);
			        ms.getMonthlyDetails().add(md);
			    }
			}else{
			    id = "-1";
			}
			id = monthlyStatementDao.save(ms).toString();
		}
		return id;
	}
	
	/**
	 * 反月结
	 */
	public void delete(MonthlyStatementVo monthlyStatementVo)throws Exception{
		MonthlyStatement ms1=monthlyStatementDao.get(" from MonthlyStatement ms where ms.msId="+monthlyStatementVo.getMsId());
		ms1.setMsRemindtime(new Date());
		monthlyStatementDao.merge(ms1);
	}

    private MonthlyStatementVo loadEndLogo() throws Exception
    {
        String sql="SELECT MS_ID,MS_NUMBER,STF_ID,MS_STARTTIME,MS_ENDTIME,MS_COLSEDTIME,MS_REMINDTIME,MS_REMARK FROM st_monthly_statement WHERE MS_REMINDTIME IS NULL ORDER BY MS_ID DESC LIMIT 0,1";
        List<Object[]> list=monthlyStatementDao.createSQLQuery(sql);
        MonthlyStatementVo msvo=new MonthlyStatementVo();
        if(list!=null&&list.size()>0){
            Object[] obj=list.get(0);
            msvo.setMsId(obj[0] != null && !"".equals(obj[0].toString()) ? obj[0].toString() : "");
            msvo.setMsNumber(obj[1] != null && !"".equals(obj[1].toString()) ? obj[1].toString() : "");
            msvo.setStfId(obj[2] != null && !"".equals(obj[2].toString()) ? obj[2].toString() : "");
            msvo.setMsStarttime(obj[3] != null && !"".equals(obj[3].toString()) ? obj[3].toString() : "");
            msvo.setMsEndtime(obj[4] != null && !"".equals(obj[4].toString()) ? obj[4].toString() : "");
            msvo.setOperDate(obj[5] != null && !"".equals(obj[5].toString()) ? obj[5].toString() : "");
            msvo.setMsRemindtime(obj[6] != null && !"".equals(obj[6].toString()) ? obj[6].toString() : "");
            msvo.setMsRemark(obj[7] != null && !"".equals(obj[7].toString()) ? obj[7].toString() : "");
        }
        return msvo;
    }

    public Json findMonthlyDetail(MonthlyStatementVo msvo) throws Exception
    {
        List<MonthlyStatementVo> list = new ArrayList<MonthlyStatementVo>();
        StringBuffer buf = new StringBuffer("SELECT b.*, c.PARTS_NAME, d.STORE_NAME FROM st_monthly_statement a, st_monthly_detail b, frt_parts c, bas_storehouse d  WHERE a.enterprise_id="+msvo.getEnterpriseId()+" and a.MS_ID = b.MS_ID AND b.PARTS_ID = c.PARTS_ID AND d.STORE_ID = b.STORE_ID AND b.MS_ID = '"+msvo.getMsId()+"'");
        List<Object[]> resultList1 = monthlyStatementDao.createSQLQuery(buf.toString(), msvo.getPage(), msvo.getRows());
        int size = monthlyStatementDao.getCountBySQL(buf.toString());
        if (resultList1 != null && resultList1.size() > 0) {
            for (Object[] obj : resultList1) {
                MonthlyStatementVo monthlyStatementVo = new MonthlyStatementVo();
                if(obj[0] != null){monthlyStatementVo.setIndexId(obj[0].toString());};
                if(obj[1] != null){monthlyStatementVo.setMsId(obj[1].toString());};
                if(obj[2] != null){monthlyStatementVo.setPartsId(obj[2].toString());};
                if(obj[3] != null){monthlyStatementVo.setStinvdCount(obj[3].toString());};
                if(obj[4] != null){monthlyStatementVo.setStinvdPrice(obj[4].toString());};
                if(obj[5] != null){monthlyStatementVo.setStinvdCost(obj[5].toString());};
                if(obj[6] != null){monthlyStatementVo.setStinvdPrice1(obj[6].toString());};
                if(obj[7] != null){monthlyStatementVo.setStinvdCost1(obj[7].toString());};
                if(obj[8] != null){monthlyStatementVo.setStoreId(obj[8].toString());};
                if(obj[9] != null){monthlyStatementVo.setPartsName(obj[9].toString());};
                if(obj[10] != null){monthlyStatementVo.setStoreName(obj[10].toString());};
                list.add(monthlyStatementVo);
            }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(size);
        return json;
    }
}