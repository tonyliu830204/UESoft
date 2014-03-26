package com.syuesoft.integratedservices.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.fbk.vo.ProgramTrackVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.integratedservices.dao.ProgramTrackDao;
import com.syuesoft.model.FbkDcserveyName;

@Repository("programTrackDao")
public class ProgramTrackDaoImpl extends BaseDaoImpl<FbkDcserveyName> implements
        ProgramTrackDao
{

    @SuppressWarnings("unchecked")
    
    public Datagrid findSameThing(ProgramTrackVo programTrackVo) throws Exception
    {
        Datagrid d=new Datagrid();
        StringBuffer sql=new StringBuffer("SELECT DISTINCT aa.dcNameId,f.SERVEY_NAME,IFNULL(temp1,0) AS '很好',IFNULL(temp2,0) AS '好',IFNULL(temp3,0) AS '一般',IFNULL(temp4,0) AS '不好',IFNULL(temp5,0) AS '很不好' FROM  "+ 
        	" 	(SELECT ss.dcNameId,(SELECT COUNT(*) AS cnt FROM   fbk_car_dcname f WHERE  f.EVALUATE='evaluate1'"+
            "	 GROUP BY    f.DC_NAME_ID HAVING f.DC_NAME_ID=ss.dcNameId )  AS temp1,("+
        	"	SELECT COUNT(*) AS cnt FROM   fbk_car_dcname f WHERE f.EVALUATE='evaluate2' "+
        	"   GROUP BY    f.DC_NAME_ID HAVING f.DC_NAME_ID=ss.dcNameId  )  AS temp2,( "+
        	"	SELECT COUNT(*) AS cnt FROM   fbk_car_dcname f WHERE f.EVALUATE='evaluate3' "+
        	"   GROUP BY    f.DC_NAME_ID HAVING f.DC_NAME_ID=ss.dcNameId )  AS temp3,( "+
        	"   SELECT COUNT(*) AS cnt FROM   fbk_car_dcname f WHERE  f.EVALUATE='evaluate4'	"+
        	"	GROUP BY    f.DC_NAME_ID HAVING f.DC_NAME_ID=ss.dcNameId )  AS temp4,( 	"+
        	"	SELECT COUNT(*)  FROM   fbk_car_dcname f WHERE f.EVALUATE='evaluate5'	 "+
        	"	GROUP BY    f.DC_NAME_ID HAVING f.DC_NAME_ID=ss.dcNameId )  AS temp5	"+
        	"	FROM( SELECT f.DC_NAME_ID  AS dcNameId,f.EVALUATE  AS eval , COUNT(*) AS cnt	"+
        	"	FROM   fbk_car_dcname f WHERE f.EVALUATE IS NOT NULL AND  f.enterprise_id="+programTrackVo.getEnterpriseId()+"  GROUP BY    f.DC_NAME_ID,f.EVALUATE "+
        	"	) ss  )aa   LEFT JOIN  fbk_dcservey_name  f  ON f.DC_NAME_ID=aa.dcNameId  WHERE f.enterprise_id="+programTrackVo.getEnterpriseId()
        );
        List<Object[]> objs=this.createSQLQuery(sql.toString());
        List<ProgramTrackVo> lst=new ArrayList<ProgramTrackVo>();
        if(objs!=null && objs.size()>0){
        	for(Object[] o:objs){
        		ProgramTrackVo trackVo=new ProgramTrackVo();
        		if(o[0]!=null && !("".equals(o[0]))){
        			trackVo.setDcNameId(o[0].toString());
        		}
				if(o[1]!=null && !("".equals(o[1]))){
					trackVo.setServeyName(o[1].toString());    			
				 }
				if(o[2]!=null && !("".equals(o[2]))){
					trackVo.setHenhao(o[2].toString());
				}
				if(o[3]!=null && !("".equals(o[3]))){
					trackVo.setHao(o[3].toString());
				}
				if(o[4]!=null && !("".equals(o[4]))){
					trackVo.setYiban(o[4].toString());
				}
				if(o[5]!=null && !("".equals(o[5]))){
					trackVo.setBuhao(o[5].toString());
				}
				if(o[6]!=null && !("".equals(o[6]))){
					trackVo.setHenbuhao(o[6].toString());
				}
				Integer sum=Integer.parseInt(o[2].toString())+Integer.parseInt(o[3].toString())+
				Integer.parseInt(o[4].toString())+Integer.parseInt(o[5].toString())+Integer.parseInt(o[6].toString());
				Double good=Double.parseDouble(o[2].toString())+Double.parseDouble(o[3].toString());
				trackVo.setManyilv(good/sum*100+"%");
				trackVo.setTotals(sum.toString());
				lst.add(trackVo);
        	}
        }
        int count= this.getSQLCount(sql.toString(), null);
        d.setRows(lst);
        d.setTotal(count);
		return d;
    }

    @SuppressWarnings("unchecked")
    
    public List findSameThing2(final String dcNameIds) throws Exception
    {
        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {
            
            public List doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                String hql = "SELECT fdn.dcNameId, fdn.serveyName, fpt.pingjia, COUNT(fpt.pingjia) "
                        + " FROM FbkDcserveyName fdn , FbkProgramTrack fpt "
                        + " WHERE fdn.dcNameId = fpt.fbkDcserveyName.dcNameId  "
                        + " AND fdn.dcNameId in ("
                        + dcNameIds
                        + ")"
                        + " GROUP BY fpt.pingjia";
                Query query = session.createQuery(hql);
                return query.list();
            }
        });
        return list;
    }
}
