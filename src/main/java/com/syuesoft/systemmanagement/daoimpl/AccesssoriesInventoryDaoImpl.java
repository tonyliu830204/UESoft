package com.syuesoft.systemmanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.alibaba.fastjson.JSON;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.StInventoryMain;
import com.syuesoft.systemmanagement.dao.AccesssoriesInventoryDao;
import com.syuesoft.systemmanagement.vo.AccesssoriesInventoryVo;
import com.syuesoft.util.GetDateByString;

@Repository("accesssoriesInventoryDao")
public class AccesssoriesInventoryDaoImpl extends BaseDaoImpl<StInventoryMain> implements
		AccesssoriesInventoryDao {
     
	/**
	 * 更新字表盘点配件信息
	 */
	@SuppressWarnings("unchecked")
	
	public void doUpdateChild(final AccesssoriesInventoryVo accesssoriesInventoryVo)
			throws Exception {
		final List<AccesssoriesInventoryVo> updatelist = JSON.parseArray(accesssoriesInventoryVo.getUpdated(),AccesssoriesInventoryVo.class);
		this.getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql1 = "";
				for (AccesssoriesInventoryVo vo : updatelist) {
					sql1 = "update st_inventory_detail set STINVD_COUNT='"+vo.getStinvd_Count()+"',STINVD_PRICE='"+vo.getStinvd_Price()+"',STINVD_COST='"+vo.getStinvd_Cost()+"' where INDEX_ID='"+vo.getIndex_Id()+"'";
					session.createSQLQuery(sql1).executeUpdate();
				}
				return null;
			}
			
		});
	}
	
	
	/**
	 * 更新主表配件盘点汇总信息
	 */
	@SuppressWarnings("unchecked")
	
	public void doUpdateFather(final AccesssoriesInventoryVo accesssoriesInventoryVo)
			throws Exception {
		this.getHibernateTemplate().execute(new HibernateCallback() {

			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//修改主表的盘点数量和合计金额
				
				String STF_ID = "";
				String STINVM_TIME = "";
				String SYINVM_SUM_COUNT = "";
				String STINVM_SUM_AMOUNT = "";
				String STINVM_STATE = "";
				String STINVM_REMARK = "";
				
				if(accesssoriesInventoryVo.getStinvm_State()!=null && accesssoriesInventoryVo.getStinvm_State().equals("已审核")){
					STINVM_STATE = "1";
				}
				if(accesssoriesInventoryVo.getStinvm_Time()==null || accesssoriesInventoryVo.getStinvm_Time().equals("") ){
					GetDateByString nowdate = new GetDateByString();
					STINVM_TIME = "'"+nowdate.getNowDate().toString()+"'";
				}else{STINVM_TIME = "'"+accesssoriesInventoryVo.getStinvm_Time()+"'";}
				if(accesssoriesInventoryVo.getStf_Id()!=null && !accesssoriesInventoryVo.getStf_Id().equals("")){STF_ID = "'"+accesssoriesInventoryVo.getStf_Id()+"'";}else{STF_ID = null;}
				if(accesssoriesInventoryVo.getSyinvm_Sum_Count()!=null && !accesssoriesInventoryVo.getSyinvm_Sum_Count().equals("")){SYINVM_SUM_COUNT = "'"+accesssoriesInventoryVo.getSyinvm_Sum_Count()+"'";}else{SYINVM_SUM_COUNT = null;}
				if(accesssoriesInventoryVo.getStinvm_Sum_Amount()!=null && !accesssoriesInventoryVo.getStinvm_Sum_Amount().equals("")){STINVM_SUM_AMOUNT = "'"+accesssoriesInventoryVo.getStinvm_Sum_Amount()+"'";}else{STINVM_SUM_AMOUNT = null;}
				if(accesssoriesInventoryVo.getStinvm_State()!=null && accesssoriesInventoryVo.getStinvm_State().equals("未审核")){STINVM_STATE = "0";}else{STINVM_STATE = null;}
				if(accesssoriesInventoryVo.getStinvm_Remark()!=null && !accesssoriesInventoryVo.getStinvm_Remark().equals("")){STINVM_REMARK = "'"+accesssoriesInventoryVo.getStinvm_Remark()+"'";}else{STINVM_REMARK = null;}
				//更新主表
				String sql2 = "update st_inventory_main set STF_ID="+STF_ID+",STINVM_TIME="+STINVM_TIME+",SYINVM_SUM_COUNT="+SYINVM_SUM_COUNT+",STINVM_SUM_AMOUNT="+STINVM_SUM_AMOUNT+",STINVM_STATE="+STINVM_STATE+",STINVM_REMARK="+STINVM_REMARK+" where STINVM_ID='"+accesssoriesInventoryVo.getStinvm_Id()+"' ";
				session.createSQLQuery(sql2).executeUpdate();
				return null;
			}
			
		});
		
	}
}