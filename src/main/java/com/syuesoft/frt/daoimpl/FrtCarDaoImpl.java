package com.syuesoft.frt.daoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.dao.FrtCarDao;
import com.syuesoft.model.FrtCar;

/**
 * 车档案dao
 * 
 * @author Liujian
 */
@Repository("frtCarDao")
public class FrtCarDaoImpl extends BaseDaoImpl<FrtCar> implements FrtCarDao
{

    /**
     * 车档案datagrid
     * 
     * @throws Exception
     */
    
    public List<Object[]> datagrid(String params) throws Exception
    {
    	StringBuffer sb=new StringBuffer("select v.*,bc.dataValue from frt_car_view v,frt_custom fc,");
    	sb.append(" Bas_Childdictionary bc,Bas_Parentdictionary bp ");
    	sb.append(" where bp.parent_Id=bc.parent_Id and bc.dataKey=v.carClass and bp.dataKey='"+Contstants.CARCLASS_TAG.CARCLASSKEY + "'");
    	sb.append(" and fc.custom_id=v.customId and fc.custom_flag="+Contstants.ONOROFF.ONOROFFYES+" ");
        if (params != null && !params.equals(""))
        {
            sb.append(params);
        }
        return this.createSQLQuery(sb.toString());
    }

    /**
     * 车档案datagrid，分页
     */
    
    public List<Object[]> datagrid(String params, int page, int rows)
            throws Exception
    {
    	StringBuffer sb=new StringBuffer("select v.*,bc.dataValue from frt_car_view v,frt_custom fc,");
    	sb.append(" Bas_Childdictionary bc,Bas_Parentdictionary bp ");
    	sb.append(" where bp.parent_Id=bc.parent_Id and bc.dataKey=v.carClass and bp.dataKey='"+Contstants.CARCLASS_TAG.CARCLASSKEY + "'");
    	sb.append(" and fc.custom_id=v.customId and fc.custom_flag="+Contstants.ONOROFF.ONOROFFYES+" ");
        if (params != null && !params.equals(""))
        {
            sb.append(params);
        }
        return super.createSQLQuery(sb.toString(), page, rows);
    }
	/**
	 * 执行sql语句
	 * */
	
	public void executeSql(String sql) throws Exception {
		// TODO Auto-generated method stub
		super.getSession().createSQLQuery(sql).executeUpdate();
	}
    
}