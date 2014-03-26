package com.syuesoft.bas.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.dao.EnterpriseInfoDAO;
import com.syuesoft.contstants.Contstants.SYSTEMTYPE;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.st.vo.StPreOutVo;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.MyBeanUtils;

/**
 * 员工信息dao
 */
@Repository("basStuffDao")
public class BasStuffDaoImpl extends BaseDaoImpl<BasStuff> implements
        BasStuffDao
{
    @SuppressWarnings("unchecked")
    public List findDepart() throws Exception
    {
        String sql = "SELECT basDept.deptId,basDept.deptName FROM BasDept basDept";
        return this.getHibernateTemplate().find(sql);
    }

    /**
     * 销售单管理模块 领料员信息预加载 预出库管理模块 领用人信息预加载
     */
    public Json loadPickingMember(final int page, final int rows,
            final String sort, final String order,final int enterprise_id) throws Exception
    {
        Json json = new Json();
        List<StSellOrderVo> list = new ArrayList<StSellOrderVo>();
        String queryString = "SELECT bas_stuff.STF_ID,bas_stuff.STF_NAME FROM bas_stuff " +
        		             " INNER JOIN bas_users ON bas_users.STF_ID=bas_stuff.STF_ID and bas_stuff.enterprise_id="+enterprise_id;
        List<Object[]> resultList = createSQLQuery(queryString, page, rows);
        int count = getSQLCount(queryString, null);
        json.setTotal(count);
        if (resultList != null && resultList.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                StSellOrderVo sovo = new StSellOrderVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null || !obj[0].equals(""))
                    sovo.setStfId(obj[0].toString());
                if (obj[1] != null || !obj[1].equals(""))
                    sovo.setStfName(obj[1].toString());
                list.add(sovo);
            }
        }
        json.setRows(list);
        return json;
    }

    /**
     * 销售单管理模块 领料员信息查询 预出库管理模块 领用人信息查询
     */
    public Json searchPickingMemberByCondition(final String stfId,
            final String stfName,final int enterprise_id) throws Exception{
        Json json = new Json();
        List<StSellOrderVo> list = new ArrayList<StSellOrderVo>();
        StringBuffer sb = new StringBuffer("SELECT bas_stuff.STF_ID,bas_stuff.STF_NAME FROM bas_stuff INNER JOIN bas_users ON bas_users.STF_ID=bas_stuff.STF_ID and bas_stuff.enterprise_id="+enterprise_id+" AND bas_users.SYSTEMID='"+SYSTEMTYPE.WEIXIU+"'");
        if (stfId != null && !stfId.equals(""))
            sb.append(" and  bas_stuff.STF_ID like '%" + StringEscapeUtils.escapeSql(stfId.trim()) + "%'");
        if (stfName != null && !stfName.equals(""))
            sb.append(" and  bas_stuff.STF_NAME like '%" + StringEscapeUtils.escapeSql(stfName.trim()) + "%'");
        int count = getSQLCount(sb.toString(), null);
        json.setTotal(count);
        List<Object[]> resultList = createSQLQuery(sb.toString());
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                StSellOrderVo sovo = new StSellOrderVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null || !obj[0].equals(""))
                    sovo.setStfId(obj[0].toString());
                if (obj[1] != null || !obj[1].equals(""))
                    sovo.setStfName(obj[1].toString());
                list.add(sovo);
            }
        }
        json.setRows(list);
        return json;
    }
    /**
     * 获取指定用户所属公司Id
     * */
    
	public String findEnterpriseInfoOfIdByStfId(String stfId) throws Exception {
		// TODO Auto-generated method stub
    	BasStuff bs=get("from BasStuff bs where bs.stfId="+stfId);
		return bs.getEnterpriseInfo().getEnterpriseId().toString();
	}

	/**
     * 获取指定用户所属公司信息
     * */
	
	public EnterpriseInfo findEnterpriseInfoByStfId(String stfId)
			throws Exception {
		BasStuff bs=get("from BasStuff bs where bs.stfId="+stfId);
		EnterpriseInfo info =new EnterpriseInfo();
		info.setEnterpriseId(bs.getEnterpriseInfo().getEnterpriseId());       //企业序号
		info.setParentEnterpriseId(bs.getEnterpriseInfo().getParentEnterpriseId()); //父企业序号
		info.setEnterpriseCode(bs.getEnterpriseInfo().getEnterpriseCode());	    //企业编号
		info.setEnterpriseName(bs.getEnterpriseInfo().getEnterpriseName());	    //企业名称
		info.setEnterpriseJm(bs.getEnterpriseInfo().getEnterpriseJm());	    //企业简称
		info.setEnterpriseAddress(bs.getEnterpriseInfo().getEnterpriseAddress());	//企业地址
		info.setEnterpriseZipcode(bs.getEnterpriseInfo().getEnterpriseZipcode());	//邮政编码
		info.setEnterpriseFax(bs.getEnterpriseInfo().getEnterpriseFax());		//传真
		info.setEnterpriseTelephone(bs.getEnterpriseInfo().getEnterpriseTelephone());	//电话
		info.setEnterprisePerson(bs.getEnterpriseInfo().getEnterprisePerson());	//企业法人
		info.setBank(bs.getEnterpriseInfo().getBank());				//开户银行
		info.setBankNumber(bs.getEnterpriseInfo().getBankNumber());			//帐号
		info.setDutyNumber(bs.getEnterpriseInfo().getDutyNumber());			//税号
		info.setComplainTelephone(bs.getEnterpriseInfo().getComplainTelephone());	//投诉电话
		info.setHotlineTelephone(bs.getEnterpriseInfo().getHotlineTelephone());	//销售热线
		info.setEnterpriseUrl(bs.getEnterpriseInfo().getEnterpriseUrl());		//网址
		info.setEnterpriseEmail(bs.getEnterpriseInfo().getEnterpriseEmail());		//邮箱
		return info;
	}
    
}
