package com.syuesoft.sell.control.daoimpl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.control.dao.XsSellFlowControlDao;
import com.syuesoft.sell.model.XsSellFlowControl;
/**
 * 流程控制dao
 * */
@Repository("xsSellFlowControlDao")
public class XsSellFlowControlDaoImpl extends BaseDaoImpl<XsSellFlowControl>
		implements XsSellFlowControlDao {
	/**
	 * 执行sql语句
	 * */
	
	public void executeSql(String sql) throws Exception {
		// TODO Auto-generated method stub
		Connection con=this.getSession().connection();
    	PreparedStatement ps=con.prepareStatement(sql);
		ps.execute();
		if(ps!=null)
			ps.close();
		if(con!=null)
			con.close();
	}

}
