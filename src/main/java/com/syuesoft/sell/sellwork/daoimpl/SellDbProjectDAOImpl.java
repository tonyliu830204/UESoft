package com.syuesoft.sell.sellwork.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsSellAccount;
import com.syuesoft.sell.model.XsSellDbProject;
import com.syuesoft.sell.sellwork.dao.SellDbProjectDAO;
import com.syuesoft.sell.sellwork.vo.SellDbProjectVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;

@Repository("sellDbProjectDAO")
public class SellDbProjectDAOImpl  extends BaseDaoImpl<Object> implements SellDbProjectDAO{
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	@Autowired
	private BaseTagDAO baseTagDAO;
	private static String insuranceType="否";//215
	
	public List<Object> findExaminState(SellDbProjectVo sellDbProjectVo)
			throws Exception {
		
		return this.find("from XsSellDbProject  sellPro where sellPro.dbProjectCode='"+sellDbProjectVo.getDbProjectCode()+"'");
		
	}

	
	public List<Object> findByCode(String dbProjectCode)
			throws Exception {
		
		return this.find("from XsSellDbProject  sellPro where sellPro.dbProjectCode='"+dbProjectCode+"'");
	}
	

	
	public DatagridAnalyze queryDbInfor(SellDbProjectVo sellDbProjectVo)
			throws Exception {
		DatagridAnalyze dg = new DatagridAnalyze();
		List<SellDbProjectVo> list = new ArrayList<SellDbProjectVo>();
		StringBuffer sql = new StringBuffer("SELECT db.db_project_person,A.STF_NAME," +
				"SUM(db.db_project_cost) AS sums,SUM(db.cost_price)AS sums2," +
				"(SUM(db.db_project_cost)-SUM(db.cost_price))AS chae" +
				" FROM xs_sell_db_project db " +
				"JOIN bas_stuff A ON a.STF_ID=db.db_project_person " +
				"JOIN xs_car_sell_info sell ON sell.xs_car_sel_id=db.xs_car_sel_id " +
				"JOIN xs_sell_flow_control flow ON flow.xsfcontrol_document=sell.sell_code " +
				"AND flow.xsfcontrol_car_id=sell.xs_car_id " +
				"JOIN xs_custom_info cus ON cus.custom_id=sell.custom_id where 1=1");
		//企业编号
		if(sellDbProjectVo.getEnterpriseId()!=null && !sellDbProjectVo.getEnterpriseId().equals("")){
			sql.append(" AND sell.Enterprise_Id = " + sellDbProjectVo.getEnterpriseId() + "");
		}

		if(sellDbProjectVo.getXs_Car_Sel_Data()!=null&&!"".equals(sellDbProjectVo.getXs_Car_Sel_Data())){
			sql.append(" and DATE(sell.xs_car_sel_data) >= '" +sellDbProjectVo.getXs_Car_Sel_Data() + "'");
		}
		if(sellDbProjectVo.getXs_Car_Sel_Data2()!=null&&!"".equals(sellDbProjectVo.getXs_Car_Sel_Data2())){
			sql.append(" and DATE(sell.xs_car_sel_data) <= '" +sellDbProjectVo.getXs_Car_Sel_Data2() + "'");
		}	
		
		if((sellDbProjectVo.getXs_Car_Sel_Data()==null||"".equals(sellDbProjectVo.getXs_Car_Sel_Data()))
				&&(sellDbProjectVo.getXs_Car_Sel_Data2()==null||"".equals(sellDbProjectVo.getXs_Car_Sel_Data2()))){
			sql	.append(" and DATE(sell.xs_car_sel_data) between '"+ FormatTime.yesTempTady(
					FormatTime.DEFAULT_FORMAT2,Integer.parseInt(
							basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
									Contstants.COLLIGATES.DEFAULTSHOWDAY,sellDbProjectVo.getEnterpriseId()).getCiValue()))
									+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if(sellDbProjectVo.getXs_Custom_Name()!=null&&!"".equals(sellDbProjectVo.getXs_Custom_Name())){
		sql.append(" and cus.xs_custom_name like '%"+sellDbProjectVo.getXs_Custom_Name()+"%'");
		}

		sql.append(" GROUP BY db.db_project_person");
		List<Object[]> resultList = createSQLQuery(sql.toString(),
				sellDbProjectVo.getPage(), sellDbProjectVo.getRows());
		List<SellDbProjectVo> footers = new ArrayList<SellDbProjectVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SellDbProjectVo sell = null;
			for (int i = 0; i < resultList.size(); i++) {
				sell = new SellDbProjectVo();
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				obj = resultList.get(i);
				sell.setDbProjectPerson(obj[0] != null ? Integer.parseInt(obj[0].toString()) : null);
				sell.setDbPersonName(obj[1] != null ? obj[1].toString() : null);
				sell.setDb_project_cost(obj[2] != null ?Double.parseDouble( obj[2].toString()) : null);
				sell.setCost_price(obj[3] != null ? Double.parseDouble( obj[3].toString()) : null);
				sell.setChae(obj[4] != null ? Double.parseDouble( obj[4].toString()) : null);
				sell.setState("closed");
				sell.setIconCls("icon-blank");
				list.add(sell);
			}
				// 汇总
				sell = new SellDbProjectVo();
				sell.setDbPersonName("汇总");
				sell.setState("open");
				sell.setIconCls("icon-blank");
				Double sum1 = 0.0;
				Double sum2 = 0.0;
				Double sum3= 0.0;
				for (SellDbProjectVo vo : list) {
					if (vo.getDb_project_cost() != null
							&& !"".equals(vo.getDb_project_cost())) {
						sum1 += (vo.getDb_project_cost());
					}

					if (vo.getCost_price() != null
							&& !"".equals(vo.getCost_price())) {
						sum2 += (vo.getCost_price());
					}

					if (vo.getChae() != null
							&& !"".equals(vo.getChae())) {
						sum3+=  vo.getChae();
					}

				}
				sell.setDb_project_cost(sum1);
				sell.setCost_price(sum2);
				sell.setChae(sum3);
				
				footers.add(sell);
			}
			int total = this.getSQLCount(sql.toString(), null);
			dg.setRows(list);
			dg.setFooter(footers);
			dg.setTotal(total);
			return dg;
	}

	
	public List queryDbChildInfor(SellDbProjectVo sellDbProjectVo)
			throws Exception {
		List<SellDbProjectVo> list = new ArrayList<SellDbProjectVo>();
		StringBuffer sql = new StringBuffer("SELECT db.db_project_code,sell.custom_id,cus.xs_custom_name," +
				"DATE(sell.xs_car_sel_data),db.db_project_id ,pro.xs_project_name," +
				"db.db_project_cost,db.cost_price,(db.db_project_cost-db.cost_price) AS cha" +
				" FROM xs_sell_db_project db " +
				"JOIN bas_stuff A ON a.STF_ID=db.db_project_person " +
				"JOIN xs_car_sell_info sell ON sell.xs_car_sel_id=db.xs_car_sel_id " +
				"JOIN xs_sell_flow_control flow ON flow.xsfcontrol_document=sell.sell_code " +
				"AND flow.xsfcontrol_car_id=sell.xs_car_id " +
				"JOIN xs_custom_info cus ON cus.custom_id=sell.custom_id " +
				"JOIN xs_db_project pro ON pro.xs_project_id=db.db_project_id " +
				" where db.db_project_person="+sellDbProjectVo.getDbProjectPerson());
		//企业编号
		if(sellDbProjectVo.getEnterpriseId()!=null && !sellDbProjectVo.getEnterpriseId().equals("")){
			sql.append(" AND sell.Enterprise_Id = " + sellDbProjectVo.getEnterpriseId() + "");
		}
		if(sellDbProjectVo.getXs_Car_Sel_Data()!=null&&!"".equals(sellDbProjectVo.getXs_Car_Sel_Data())){
			sql.append(" and DATE(sell.xs_car_sel_data) >= '" +sellDbProjectVo.getXs_Car_Sel_Data() + "'");
		}
		if(sellDbProjectVo.getXs_Car_Sel_Data2()!=null&&!"".equals(sellDbProjectVo.getXs_Car_Sel_Data2())){
			sql.append(" and DATE(sell.xs_car_sel_data) <= '" +sellDbProjectVo.getXs_Car_Sel_Data2() + "'");
		}	
		
		if((sellDbProjectVo.getXs_Car_Sel_Data()==null||"".equals(sellDbProjectVo.getXs_Car_Sel_Data()))
				&&(sellDbProjectVo.getXs_Car_Sel_Data2()==null||"".equals(sellDbProjectVo.getXs_Car_Sel_Data2()))){
			sql	.append(" and DATE(sell.xs_car_sel_data) between '"+ FormatTime.yesTempTady(
					FormatTime.DEFAULT_FORMAT2,Integer.parseInt(
							basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
									Contstants.COLLIGATES.DEFAULTSHOWDAY,sellDbProjectVo.getEnterpriseId()).getCiValue()))
									+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if(sellDbProjectVo.getXs_Custom_Name()!=null&&!"".equals(sellDbProjectVo.getXs_Custom_Name())){
		sql.append(" and cus.xs_custom_name like '%"+sellDbProjectVo.getXs_Custom_Name()+"%'");
		}
		List<Object[]> resultList = createSQLQuery(sql.toString(),
				sellDbProjectVo.getPage(), sellDbProjectVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SellDbProjectVo sell = null;
			for (int i = 0; i < resultList.size(); i++) {
				sell = new SellDbProjectVo();
				
				obj = resultList.get(i);
				sell.setDbPersonName(obj[0] != null ? obj[0].toString() : null);
				sell.setCustomId(obj[1] != null ? Integer.parseInt(obj[1].toString()) : null);
				sell.setXs_Custom_Name(obj[2] != null ? obj[2].toString() : null);
				sell.setXs_Car_Sel_Data(obj[3] != null ? obj[3].toString() : null);
				sell.setProjectId(obj[4] != null ? Integer.parseInt(obj[4].toString()) : null);
				sell.setProjectName(obj[5] != null ? obj[5].toString() : null);
				sell.setDb_project_cost(obj[6] != null ?Double.parseDouble( obj[6].toString()) : null);
				sell.setCost_price(obj[7] != null ? Double.parseDouble( obj[7].toString()) : null);
				sell.setChae(obj[8] != null ? Double.parseDouble( obj[8].toString()) : null);
				sell.setState("open");
				sell.setIconCls("icon-blank");
				list.add(sell);
				
			}
		}
		return list;
	}

}
