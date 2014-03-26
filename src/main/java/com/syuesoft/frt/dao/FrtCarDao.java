package com.syuesoft.frt.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.FrtCar;

public interface FrtCarDao extends BaseDaoI<FrtCar>
{

    public List<Object[]> datagrid(String params) throws Exception; // 车辆档案datagrid

    public List<Object[]> datagrid(String params, int page, int rows)
            throws Exception; // 车辆档案datagrid
    
    public void executeSql(String sql)throws Exception;//执行sql语句

}
