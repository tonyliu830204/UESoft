package com.syuesoft.integratedservices.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.fbk.vo.DailyclientTrackingVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.integratedservices.dao.DailyclientTrackingDao;
import com.syuesoft.model.FbkDailyclientTracking;

@Repository("dailyclientTrackingDao")
public class DailyclientTrackingDaoImpl extends
        BaseDaoImpl<FbkDailyclientTracking> implements DailyclientTrackingDao
{

    
    public void doAdd(FbkDailyclientTracking fbkDailyclientTracking)
            throws Exception
    {
        this.getHibernateTemplate().save(fbkDailyclientTracking);

    }

    
    public void doDelete(FbkDailyclientTracking fbkDailyclientTracking)
            throws Exception
    {
        this.getHibernateTemplate().delete(fbkDailyclientTracking);
    }

    /*
     * 查询所有 (non-Javadoc)
     * 
     * @see
     * com.syuesoft.integratedservices.dao.DailyclientTrackingDao#doFind(int,
     * int)
     */
    @SuppressWarnings("unchecked")
    
    public Datagrid doFind(DailyclientTrackingVo dailyclientTrackingVo)
            throws Exception
    {
    	Datagrid d=new Datagrid();
    	StringBuffer sb=new StringBuffer();
    	sb.append("SELECT car.CAR_ID ,fra.advice_id,car.CAR_LICENSE  ,fc.CUSTOM_NAME  ,fra.advice_context ,DATE_FORMAT(fra.advice_time,'%Y-%m-%d %H:%i:%s') ,fra.write_person ,"+
    			  "(SELECT  stf_name  FROM    bas_stuff s WHERE  s.stf_id=fra.write_person ) AS personName,fra.proces_state ," +
    			  "(SELECT c.datavalue  FROM bas_parentdictionary p ,bas_childdictionary c WHERE p.parent_id=c.parent_id  AND p.datakey='useState' AND c.datakey=fra.proces_state ) AS procesStateName,"+
    			  "fra.advice_time_end ,fra.proces_proson, (SELECT  stf_name  FROM    bas_stuff s WHERE  s.stf_id=fra.proces_proson ) AS procesProsonName,fc.CUSTOM_TEL1 FROM frt_resv_advice  fra"+
    			  "	LEFT JOIN frt_car car ON fra.CAR_ID=car.CAR_ID INNER JOIN frt_custom  fc  ON fc.CUSTOM_ID=car.CUSTOM_ID  and  fra.enterprise_id="+dailyclientTrackingVo.getEnterpriseId());
    	if(dailyclientTrackingVo.getAdviceTime()!=null && !("".equals(dailyclientTrackingVo.getAdviceTime()))){
    		String[] str = dailyclientTrackingVo.getAdviceTime().split(",");
				if (str[0]!=null && !("".equals(str[0]))) {
					sb.append(" and DATE_FORMAT(fra.advice_time,'%Y-%m-%d') >= '" + str[0]
							+ "'");
				}
				if (str[1]!=null && !("".equals(str[1]))) {
					sb.append(" and DATE_FORMAT(fra.advice_time,'%Y-%m-%d') <='" + str[1]
							+ "'");
				}
			
    	}
    	if(dailyclientTrackingVo.getCarLicense()!=null && !("".equals(dailyclientTrackingVo.getCarLicense()))){
    		sb.append(" and car.CAR_LICENSE like '%" + dailyclientTrackingVo.getCarLicense()+ "%'");
    	}
    	if(dailyclientTrackingVo.getProcesState()!=null && !("".equals(dailyclientTrackingVo.getProcesState()))){
    		sb.append(" and fra.proces_state ='" + dailyclientTrackingVo.getProcesState()+ "'");
    	}
    	if(dailyclientTrackingVo.getAdviceContext()!=null && !("".equals(dailyclientTrackingVo.getAdviceContext()))){
    		sb.append(" and fra.advice_context like '%" + dailyclientTrackingVo.getAdviceContext()+ "%'");
    	}
    	List<DailyclientTrackingVo> lst=new ArrayList<DailyclientTrackingVo>();
    	List<Object[]> objs=this.createSQLQuery(sb.toString(), dailyclientTrackingVo.getPage(),dailyclientTrackingVo.getRows());
    	if(objs!=null && objs.size()>0){
    		for(Object[] o:objs){
    			DailyclientTrackingVo   vo=new DailyclientTrackingVo();
    			if(o[0]!=null && !("".equals(o[0]))){
        			vo.setCarId(o[0].toString());	
        		}
				if(o[1]!=null && !("".equals(o[1]))){
				     vo.setAdviceId(o[1].toString());   			
				}
				if(o[2]!=null && !("".equals(o[2]))){
					vo.setCarLicense(o[2].toString());
				}
				if(o[3]!=null && !("".equals(o[3]))){
					vo.setCustomName(o[3].toString());
				}
				if(o[4]!=null && !("".equals(o[4]))){
					vo.setAdviceContext(o[4].toString());
				}
				if(o[5]!=null && !("".equals(o[5]))){
					vo.setAdviceTime(o[5].toString());
				}
				if(o[6]!=null && !("".equals(o[6]))){
					vo.setWritePerson(o[6].toString());
				}
				if(o[7]!=null && !("".equals(o[7]))){
					vo.setWritePersonName(o[7].toString());
				}
				if(o[8]!=null && !("".equals(o[8]))){
					vo.setProcesState(o[8].toString());
				}
				if(o[9]!=null && !("".equals(o[9]))){
					vo.setProcesStateName(o[9].toString());
				}
				if(o[10]!=null && !("".equals(o[10]))){
					vo.setAdviceTimeEnd(o[9].toString());
				}
				if(o[11]!=null && !("".equals(o[11]))){
					vo.setProcesProson(o[11].toString());
				}
				if(o[12]!=null && !("".equals(o[12]))){
					vo.setProcesProsonName(o[12].toString());
				}
				if(o[13]!=null && !("".equals(o[13]))){
					vo.setCustomTel(o[13].toString());
				}
    			lst.add(vo);
    		}
    		
    	}
    	d.setRows(lst);
    	d.setTotal(this.getSQLCount(sb.toString(), null));
    	return d;
        
    }

    
    public void doUpdate(FbkDailyclientTracking fbkDailyclientTracking)
            throws Exception
    {
        this.getHibernateTemplate().update(fbkDailyclientTracking);
    }

    /*
     * 条件查询 (non-Javadoc)
     * 
     * @see
     * com.syuesoft.integratedservices.dao.DailyclientTrackingDao#doFindByCondition
     * (com.syuesoft.model.FbkDailyclientTracking, int, int)
     */
    @SuppressWarnings("unchecked")
    
    public List<FbkDailyclientTracking> doFindByCondition(
            final FbkDailyclientTracking fbkDailyclientTracking,
            final int page, final int rows) throws Exception
    {
        List<FbkDailyclientTracking> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        HttpSession ssion = ServletActionContext.getRequest()
                                .getSession();
                        String queryString = "from FbkDailyclientTracking where 1 = 1 ";
                        if (fbkDailyclientTracking.getCarLicense() != null
                                && !fbkDailyclientTracking.getCarLicense()
                                        .trim().equals(""))
                        {
                            queryString += " and carLicense like '%"
                                    + StringEscapeUtils.escapeSql(fbkDailyclientTracking.getCarLicense()
                                            .trim().trim()) + "%'";
                        }
                        if (fbkDailyclientTracking.getCustomName() != null
                                && !fbkDailyclientTracking.getCustomName()
                                        .trim().equals(""))
                        {
                            queryString += " and customName like '%"
                                    + StringEscapeUtils.escapeSql(fbkDailyclientTracking.getCustomName()
                                            .trim().trim()) + "%'";
                        }
                        if (fbkDailyclientTracking.getTxInfomation() != null
                                && !fbkDailyclientTracking.getTxInfomation()
                                        .trim().equals(""))
                        {
                            queryString += " and txInfomation like '%"
                                    + StringEscapeUtils.escapeSql(fbkDailyclientTracking.getTxInfomation()
                                            .trim().trim()) + "%'";
                        }
                        org.hibernate.Query query = session
                                .createQuery(queryString);
                        ssion.setAttribute("querySize", query.list().size());
                        int beginRow = (page - 1) * rows;
                        query.setFirstResult(beginRow);
                        query.setMaxResults(rows);
                        return query.list();
                    }
                });
        return list;
    }

}
