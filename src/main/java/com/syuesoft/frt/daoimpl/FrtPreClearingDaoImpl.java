package com.syuesoft.frt.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.fin.vo.WorkOrderPartsQueryVo;
import com.syuesoft.frt.dao.FrtPreClearingDao;
import com.syuesoft.model.FrtPreClearing;

/**
 * 交车结算dao
 * 
 * @author Liujian
 */
@Repository("frtPreClearingDao")
public class FrtPreClearingDaoImpl extends BaseDaoImpl<FrtPreClearing>
        implements FrtPreClearingDao
{

    @SuppressWarnings("unchecked")
    
    public void updateBatch(final String sql) throws Exception
    {
        super.getHibernateTemplate().execute(new HibernateCallback()
        {
            
            public Object doInHibernate(Session arg0)
                    throws HibernateException, SQLException
            {
                SQLQuery query = arg0.createSQLQuery(sql);
                query.executeUpdate();
                return null;
            }
        });
    }

    /**
     * 财务模块 工单配件查询 工单结算信息 综合查询
     */
    
    public List<WorkOrderPartsQueryVo> searchFrtPreClearingByCondition(
            String preclrDateStart, String preclrDateEnd, String receptionId,
            String carLicense) throws Exception
    {
        List<WorkOrderPartsQueryVo> list = new ArrayList<WorkOrderPartsQueryVo>();
        StringBuffer sql = new StringBuffer(
                "SELECT frt_pre_clearing.RECEPTION_ID,frt_pre_clearing.PRECLR_TIME"
                        + ",frt_car.CAR_LICENSE,bas_car_type.CTYPE_NAME"
                        + " FROM frt_pre_clearing,frt_reception,frt_car,bas_car_type"
                        + " WHERE frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID"
                        + " AND frt_reception.CAR_ID=frt_car.CAR_ID"
                        + " AND frt_car.CTYPE_ID=bas_car_type.CTYPE_ID");
        if (preclrDateStart != null && !"".equals(preclrDateStart))
        {
            if (preclrDateEnd != null && !"".equals(preclrDateEnd))
            {
                sql.append(" and frt_pre_clearing.PRECLR_TIME between '"
                        + preclrDateStart + "' and '" + preclrDateEnd + "'");
            }
            else
            {
                sql.append(" and frt_pre_clearing.PRECLR_TIME >= '"
                        + preclrDateStart + "'");
            }
        }
        else if (preclrDateEnd != null && !"".equals(preclrDateEnd))
        {
            sql.append(" and frt_pre_clearing.PRECLR_TIME <= '" + preclrDateEnd
                    + "'");
        }
        if (receptionId != null && !receptionId.equals(""))
        {
            sql.append(" and frt_pre_clearing.RECEPTION_ID like '%"
                    + StringEscapeUtils.escapeSql(receptionId.trim()) + "%'");
        }
        if (carLicense != null && !"".equals(carLicense))
        {
            sql.append(" and frt_car.CAR_LICENSE like '%" + StringEscapeUtils.escapeSql(carLicense.trim()) + "%'");
        }
        List<Object[]> resultList = createSQLQuery(sql.toString());
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                WorkOrderPartsQueryVo wopq = new WorkOrderPartsQueryVo();
                obj = resultList.get(i);
                if (obj[0] != null && !"".equals(obj[0]))
                {
                    wopq.setReceptionId(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    String preclrTime = obj[1].toString();
                    preclrTime = preclrTime.substring(0, 10);
                    wopq.setPreclrTime(preclrTime);
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    wopq.setCarLicense(obj[2].toString());
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    wopq.setCtypeName(obj[3].toString());
                    wopq.setState("closed");
                }
                list.add(wopq);
            }
        }
        return list;
    }
}