package com.syuesoft.sell.allocateManage.daoimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.allocateManage.dao.SellCustomAnalyseDao;
import com.syuesoft.sell.allocateManage.vo.SellCustomAnalyseVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Utils;
@Repository("sellCustomAnalyseDao")
public class SellCustomAnalyseDaoImpl extends BaseDaoImpl<BaseBean> implements SellCustomAnalyseDao {
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;

	//销售客户分析
	
	public Datagrid querySellCustomInfor(SellCustomAnalyseVo sellCustomAnalyseVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer querySql = new StringBuffer();
		if(sellCustomAnalyseVo.getXsCarSelData()!=null&&!"".equals(sellCustomAnalyseVo.getXsCarSelData())){
				querySql.append(" and DATE(aa.xs_car_sel_data) >= '" + sellCustomAnalyseVo.getXsCarSelData() + "'");
		}
		if(sellCustomAnalyseVo.getXsCarSelData2()!=null&&!"".equals(sellCustomAnalyseVo.getXsCarSelData2())){
				querySql.append(" and DATE(aa.xs_car_sel_data) <='" +sellCustomAnalyseVo.getXsCarSelData2() + "'");
		}
		if((sellCustomAnalyseVo.getXsCarSelData()==null||"".equals(sellCustomAnalyseVo.getXsCarSelData()))&&
		(sellCustomAnalyseVo.getXsCarSelData2()==null||"".equals(sellCustomAnalyseVo.getXsCarSelData2()))){
			querySql.append(" and DATE(aa.xs_car_sel_data) >= '"+ FormatTime.yesTempTady(
							FormatTime.DEFAULT_FORMAT2,(
									basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
											Contstants.COLLIGATES.DEFAULTSHOWDAY,sellCustomAnalyseVo.getEnterprise_id()).getCiValue()))
											+ "' and DATE(aa.xs_car_sel_data) <= '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if(sellCustomAnalyseVo.getCarBrand()!=null&&!"".equals(sellCustomAnalyseVo.getCarBrand())){
			querySql.append(" and bb.xs_car_brand='"+sellCustomAnalyseVo.getCarBrand()+"'");
			
		}
		if(sellCustomAnalyseVo.getCarModel()!=null&&!"".equals(sellCustomAnalyseVo.getCarModel())){
			querySql.append(" and bb.xs_car_model_id='"+sellCustomAnalyseVo.getCarModel()+"'");
			
		}
		if(sellCustomAnalyseVo.getCarType()!=null&&!"".equals(sellCustomAnalyseVo.getCarType())){
			querySql.append(" and bb.xs_car_type='"+sellCustomAnalyseVo.getCarType()+"'");
		}
		if(sellCustomAnalyseVo.getRetreat_date()!=null&&!"".equals(sellCustomAnalyseVo.getRetreat_date())){
			querySql.append(" and ff.retreat_date >= '" + sellCustomAnalyseVo.getRetreat_date() + "'");
		}
		if(sellCustomAnalyseVo.getRetreat_date2()!=null&&!"".equals(sellCustomAnalyseVo.getRetreat_date2())){
			querySql.append(" and ff.retreat_date <= '" + sellCustomAnalyseVo.getRetreat_date2() + "'");
		}
		if(sellCustomAnalyseVo.getUpDate()!=null&&!"".equals(sellCustomAnalyseVo.getUpDate())){
			querySql.append(" and bb.upDate>= '" + sellCustomAnalyseVo.getUpDate() + "'");
		}
		if(sellCustomAnalyseVo.getUpDate2()!=null&&!"".equals(sellCustomAnalyseVo.getUpDate2())){
			querySql.append(" and bb.upDate <= '" + sellCustomAnalyseVo.getUpDate2() + "'");
		}
		if(sellCustomAnalyseVo.getEnterprise_id()!=null&&!"".equals(sellCustomAnalyseVo.getEnterprise_id())){
			querySql.append(" and aa.enterprise_id  = '" + sellCustomAnalyseVo.getEnterprise_id() + "'");
		}
				
		
		StringBuffer querySql2 = new StringBuffer();
		if(sellCustomAnalyseVo.getXsCarSelData()!=null&&!"".equals(sellCustomAnalyseVo.getXsCarSelData())){
				querySql2.append(" and DATE(a.xs_car_sel_data) >= '" + sellCustomAnalyseVo.getXsCarSelData() + "'");
		}
		if(sellCustomAnalyseVo.getXsCarSelData2()!=null&&!"".equals(sellCustomAnalyseVo.getXsCarSelData2())){
				querySql2.append(" and DATE(a.xs_car_sel_data) <='" +sellCustomAnalyseVo.getXsCarSelData2() + "'");
		}
		if((sellCustomAnalyseVo.getXsCarSelData()==null||"".equals(sellCustomAnalyseVo.getXsCarSelData()))&&
				(sellCustomAnalyseVo.getXsCarSelData2()==null||"".equals(sellCustomAnalyseVo.getXsCarSelData2()))){
			querySql2.append(" and DATE(a.xs_car_sel_data) >= '"+ FormatTime.yesTempTady(
							FormatTime.DEFAULT_FORMAT2,(
									basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
											Contstants.COLLIGATES.DEFAULTSHOWDAY,sellCustomAnalyseVo.getEnterprise_id()).getCiValue()))
											+ "' and DATE(a.xs_car_sel_data) <='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if(sellCustomAnalyseVo.getCarBrand()!=null&&!"".equals(sellCustomAnalyseVo.getCarBrand())){
			querySql2.append(" and b.xs_car_brand='"+sellCustomAnalyseVo.getCarBrand()+"'");
			
		}
		if(sellCustomAnalyseVo.getCarModel()!=null&&!"".equals(sellCustomAnalyseVo.getCarModel())){
			querySql2.append(" and b.xs_car_model_id='"+sellCustomAnalyseVo.getCarModel()+"'");
			
		}
		if(sellCustomAnalyseVo.getCarType()!=null&&!"".equals(sellCustomAnalyseVo.getCarType())){
			querySql2.append(" and b.xs_car_type='"+sellCustomAnalyseVo.getCarType()+"'");
			
		}
		if(sellCustomAnalyseVo.getRetreat_date()!=null&&!"".equals(sellCustomAnalyseVo.getRetreat_date())){
			querySql2.append(" and f.retreat_date >= '" + sellCustomAnalyseVo.getRetreat_date() + "'");
		}
		if(sellCustomAnalyseVo.getRetreat_date2()!=null&&!"".equals(sellCustomAnalyseVo.getRetreat_date2())){
			querySql2.append(" and f.retreat_date <= '" + sellCustomAnalyseVo.getRetreat_date() + "'");

		}
		if(sellCustomAnalyseVo.getUpDate()!=null&&!"".equals(sellCustomAnalyseVo.getUpDate())){
			querySql.append(" and b.upDate>= '" + sellCustomAnalyseVo.getUpDate() + "'");
		}
		if(sellCustomAnalyseVo.getUpDate2()!=null&&!"".equals(sellCustomAnalyseVo.getUpDate2())){
			querySql.append(" and b.upDate <= '" + sellCustomAnalyseVo.getUpDate2() + "'");
		}
		if(sellCustomAnalyseVo.getEnterprise_id()!=null&&!"".equals(sellCustomAnalyseVo.getEnterprise_id())){
			querySql2.append(" and a.enterprise_id  = '" + sellCustomAnalyseVo.getEnterprise_id() + "'");
		}

		StringBuffer sql = new StringBuffer();
		
		if(sellCustomAnalyseVo.getCtype()==null||"".equals(sellCustomAnalyseVo.getCtype())||"type1".equals(sellCustomAnalyseVo.getCtype())){
			//客户车型分析
			sql.append("SELECT ss.n1,ss.n2,ss.nn,(ss.nn/ss.nns)*100" +
				" FROM (SELECT COUNT(*) AS nn,b.xs_car_model_id AS n1,c.xs_model_name AS n2," +
				"a.xs_car_sel_id AS n3," +
				"(SELECT COUNT(*)FROM xs_car_sell_info aa " +
				"JOIN xs_car_info bb ON bb.xs_car_id=aa.xs_car_id " +
				"JOIN xs_car_model cc ON cc.xs_model_id=bb.xs_car_model_id " +
				"JOIN xs_custom_info dd ON dd.custom_id=aa.custom_id " +
				"JOIN xs_sell_flow_control ee ON ee.xsfcontrol_document=aa.sell_code" +
				" AND ee.xsfcontrol_car_id=aa.xs_car_id " +
				" LEFT JOIN xs_sell_retreat ff ON ff.retreat_id=aa.out_id " +
				"WHERE (aa.xs_car_give_up IS NULL OR aa.xs_car_give_up=0)" );
				sql.append(querySql);
				sql.append(") AS nns " +
				"FROM xs_car_sell_info A " +
				"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id " +
				"JOIN xs_car_model c ON c.xs_model_id=b.xs_car_model_id  " +
				"JOIN xs_custom_info d ON d.custom_id=a.custom_id " +
				"JOIN xs_sell_flow_control E ON e.xsfcontrol_document=a.sell_code " +
				"AND e.xsfcontrol_car_id=a.xs_car_id " +
				" LEFT JOIN xs_sell_retreat f ON f.retreat_id=a.out_id " +
				" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " );
			 	sql.append(querySql2);
				sql.append(" GROUP BY b.xs_car_model_id) ss ");
		}
		if(sellCustomAnalyseVo.getCtype()!=null&&!"".equals(sellCustomAnalyseVo.getCtype())&&"type2".equals(sellCustomAnalyseVo.getCtype())){
			//车辆颜色分析
			sql.append("SELECT ss.n1 ,ss.n2,ss.nn,(ss.nn/ss.nns)*100 " +
					" FROM (SELECT COUNT(*) AS nn,b.xs_car_color AS n1," +
					"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =b.xs_car_color ) AS n2," +
					"(SELECT COUNT(*) FROM xs_car_sell_info aa " +
					"JOIN xs_car_info bb ON bb.xs_car_id=aa.xs_car_id " +
					"JOIN xs_car_model cc ON cc.xs_model_id=bb.xs_car_model_id " +
					"JOIN xs_custom_info dd ON dd.custom_id=aa.custom_id " +
					"JOIN xs_sell_flow_control ee ON ee.xsfcontrol_document=aa.sell_code " +
					"AND ee.xsfcontrol_car_id=aa.xs_car_id " +
					" LEFT JOIN xs_sell_retreat ff ON ff.retreat_id=aa.out_id " +
					"WHERE (aa.xs_car_give_up IS NULL OR aa.xs_car_give_up=0) " );
					sql.append(querySql);
					
					sql.append(") AS nns " +
					"FROM xs_car_sell_info A " +
					"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id " +
					"JOIN xs_car_model c ON c.xs_model_id=b.xs_car_model_id " +
					"JOIN xs_custom_info d ON d.custom_id=a.custom_id " +
					"JOIN xs_sell_flow_control E ON e.xsfcontrol_document=a.sell_code " +
					"AND e.xsfcontrol_car_id=a.xs_car_id " +
					" LEFT JOIN xs_sell_retreat f ON f.retreat_id=a.out_id " +
					" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " );
				 	sql.append(querySql2);
					sql.append(" GROUP BY b.xs_car_color) ss ");
		}
		if(sellCustomAnalyseVo.getCtype()!=null&&!"".equals(sellCustomAnalyseVo.getCtype())&&"type3".equals(sellCustomAnalyseVo.getCtype())){
			//客户性别分析
			sql.append("SELECT ss.n1 ,ss.n2,ss.nn,(ss.nn/ss.nns)*100 " +
					"FROM (SELECT COUNT(*) AS nn,D.xs_custom_sex AS n1," +
					"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =D.xs_custom_sex ) AS n2," +
					"(SELECT COUNT(*) FROM xs_car_sell_info aa " +
					"JOIN xs_car_info bb ON bb.xs_car_id=aa.xs_car_id " +
					"JOIN xs_car_model cc ON cc.xs_model_id=bb.xs_car_model_id " +
					"JOIN xs_custom_info dd ON dd.custom_id=aa.custom_id " +
					"JOIN xs_sell_flow_control ee ON ee.xsfcontrol_document=aa.sell_code" +
					" AND ee.xsfcontrol_car_id=aa.xs_car_id " +
					" LEFT JOIN xs_sell_retreat ff ON ff.retreat_id=aa.out_id " +
					"WHERE (aa.xs_car_give_up IS NULL OR aa.xs_car_give_up=0)" );
					sql.append(querySql);
					sql.append(") AS nns " +
					"FROM xs_car_sell_info A " +
					"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id  " +
					"JOIN xs_car_model c ON c.xs_model_id=b.xs_car_model_id " +
					"JOIN xs_custom_info d ON d.custom_id=a.custom_id " +
					"JOIN xs_sell_flow_control E ON e.xsfcontrol_document=a.sell_code " +
					"AND e.xsfcontrol_car_id=a.xs_car_id " +
					"LEFT JOIN xs_sell_retreat f ON f.retreat_id=a.out_id " +
					" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " );
				 	sql.append(querySql2);
					sql.append(" GROUP BY D.xs_custom_sex) ss ");
			
		}
		if(sellCustomAnalyseVo.getCtype()!=null&&!"".equals(sellCustomAnalyseVo.getCtype())&&"type4".equals(sellCustomAnalyseVo.getCtype())){
			//客户学历分析
			sql.append("SELECT ss.n1 ,ss.n2,ss.nn,(ss.nn/ss.nns)*100 " +
					"FROM (SELECT COUNT(*) AS nn,d.xs_custom_diploma AS n1," +
					"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.xs_custom_diploma ) AS n2," +
					"(SELECT COUNT(*) FROM xs_car_sell_info aa " +
					"JOIN xs_car_info bb ON bb.xs_car_id=aa.xs_car_id " +
					"JOIN xs_car_model cc ON cc.xs_model_id=bb.xs_car_model_id " +
					"JOIN xs_custom_info dd ON dd.custom_id=aa.custom_id " +
					"JOIN xs_sell_flow_control ee ON ee.xsfcontrol_document=aa.sell_code" +
					" AND ee.xsfcontrol_car_id=aa.xs_car_id " +
					" LEFT JOIN xs_sell_retreat ff ON ff.retreat_id=aa.out_id " +
					"WHERE (aa.xs_car_give_up IS NULL OR aa.xs_car_give_up=0)" );
					sql.append(querySql);
					sql.append(") AS nns " +
					"FROM xs_car_sell_info A " +
					"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id  " +
					"JOIN xs_car_model c ON c.xs_model_id=b.xs_car_model_id " +
					"JOIN xs_custom_info d ON d.custom_id=a.custom_id " +
					"JOIN xs_sell_flow_control E ON e.xsfcontrol_document=a.sell_code " +
					"AND e.xsfcontrol_car_id=a.xs_car_id " +
					"LEFT JOIN xs_sell_retreat f ON f.retreat_id=a.out_id " +
					" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " );
				 	sql.append(querySql2);
					sql.append(" GROUP BY d.xs_custom_diploma) ss ");
		}
		if(sellCustomAnalyseVo.getCtype()!=null&&!"".equals(sellCustomAnalyseVo.getCtype())&&"type5".equals(sellCustomAnalyseVo.getCtype())){
				//客户收入分析
				sql.append("SELECT ss.n1 ,ss.n2,ss.nn,(ss.nn/ss.nns)*100 " +
						"FROM (SELECT COUNT(*) AS nn,d.xs_custom_income AS n1," +
						"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.xs_custom_income ) AS n2," +
						"(SELECT COUNT(*) FROM xs_car_sell_info aa " +
						"JOIN xs_car_info bb ON bb.xs_car_id=aa.xs_car_id " +
						"JOIN xs_car_model cc ON cc.xs_model_id=bb.xs_car_model_id " +
						"JOIN xs_custom_info dd ON dd.custom_id=aa.custom_id " +
						"JOIN xs_sell_flow_control ee ON ee.xsfcontrol_document=aa.sell_code" +
						" AND ee.xsfcontrol_car_id=aa.xs_car_id " +
						"LEFT JOIN xs_sell_retreat ff ON ff.retreat_id=aa.out_id " +
						" WHERE (aa.xs_car_give_up IS NULL OR aa.xs_car_give_up=0)" );
						sql.append(querySql);
						sql.append(") AS nns " +
						"FROM xs_car_sell_info A " +
						"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id  " +
						"JOIN xs_car_model c ON c.xs_model_id=b.xs_car_model_id " +
						"JOIN xs_custom_info d ON d.custom_id=a.custom_id " +
						"JOIN xs_sell_flow_control E ON e.xsfcontrol_document=a.sell_code " +
						"AND e.xsfcontrol_car_id=a.xs_car_id " +
						"LEFT JOIN xs_sell_retreat f ON f.retreat_id=a.out_id " +
						" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " );
					 	sql.append(querySql2);
						sql.append(" GROUP BY d.xs_custom_income) ss ");
			}
		if(sellCustomAnalyseVo.getCtype()!=null&&!"".equals(sellCustomAnalyseVo.getCtype())&&"type6".equals(sellCustomAnalyseVo.getCtype())){

					//客户来源分析
					sql.append("SELECT ss.n1 ,ss.n2,ss.nn,(ss.nn/ss.nns)*100 " +
							"FROM (SELECT COUNT(*) AS nn,d.xs_custom_source AS n1," +
							"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.xs_custom_source ) AS n2," +
							"(SELECT COUNT(*) FROM xs_car_sell_info aa " +
							"JOIN xs_car_info bb ON bb.xs_car_id=aa.xs_car_id " +
							"JOIN xs_car_model cc ON cc.xs_model_id=bb.xs_car_model_id " +
							"JOIN xs_custom_info dd ON dd.custom_id=aa.custom_id " +
							"JOIN xs_sell_flow_control ee ON ee.xsfcontrol_document=aa.sell_code" +
							" AND ee.xsfcontrol_car_id=aa.xs_car_id " +
							"LEFT JOIN xs_sell_retreat ff ON ff.retreat_id=aa.out_id " +
							" WHERE (aa.xs_car_give_up IS NULL OR aa.xs_car_give_up=0)" );
							sql.append(querySql);
							sql.append(") AS nns " +
							"FROM xs_car_sell_info A " +
							"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id  " +
							"JOIN xs_car_model c ON c.xs_model_id=b.xs_car_model_id " +
							"JOIN xs_custom_info d ON d.custom_id=a.custom_id " +
							"JOIN xs_sell_flow_control E ON e.xsfcontrol_document=a.sell_code " +
							"AND e.xsfcontrol_car_id=a.xs_car_id " +
							"LEFT JOIN xs_sell_retreat f ON f.retreat_id=a.out_id " +
							" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " );
						 	sql.append(querySql2);
							sql.append(" GROUP BY d.xs_custom_source) ss ");
				}
		if(sellCustomAnalyseVo.getCtype()!=null&&!"".equals(sellCustomAnalyseVo.getCtype())&&"type7".equals(sellCustomAnalyseVo.getCtype())){

					//客户行业分析
					sql.append("SELECT ss.n1 ,ss.n2,ss.nn,(ss.nn/ss.nns)*100 " +
							"FROM (SELECT COUNT(*) AS nn,d.xs_custom_trade AS n1," +
							"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.xs_custom_trade ) AS n2," +
							"(SELECT COUNT(*) FROM xs_car_sell_info aa " +
							"JOIN xs_car_info bb ON bb.xs_car_id=aa.xs_car_id " +
							"JOIN xs_car_model cc ON cc.xs_model_id=bb.xs_car_model_id " +
							"JOIN xs_custom_info dd ON dd.custom_id=aa.custom_id " +
							"JOIN xs_sell_flow_control ee ON ee.xsfcontrol_document=aa.sell_code" +
							" AND ee.xsfcontrol_car_id=aa.xs_car_id " +
							"LEFT JOIN xs_sell_retreat ff ON ff.retreat_id=aa.out_id " +
							" WHERE (aa.xs_car_give_up IS NULL OR aa.xs_car_give_up=0)" );
							sql.append(querySql);
							sql.append(") AS nns " +
							"FROM xs_car_sell_info A " +
							"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id  " +
							"JOIN xs_car_model c ON c.xs_model_id=b.xs_car_model_id " +
							"JOIN xs_custom_info d ON d.custom_id=a.custom_id " +
							"JOIN xs_sell_flow_control E ON e.xsfcontrol_document=a.sell_code " +
							"AND e.xsfcontrol_car_id=a.xs_car_id " +
							"LEFT JOIN xs_sell_retreat f ON f.retreat_id=a.out_id " +
							" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " );
						 	sql.append(querySql2);
							sql.append(" GROUP BY d.xs_custom_trade) ss ");
				}
		if(sellCustomAnalyseVo.getCtype()!=null&&!"".equals(sellCustomAnalyseVo.getCtype())&&"type8".equals(sellCustomAnalyseVo.getCtype())){

					//客户地区分析
					sql.append("SELECT ss.n1 ,ss.n2,ss.nn,(ss.nn/ss.nns)*100 " +
							"FROM (SELECT COUNT(*) AS nn,d.xs_custom_area AS n1," +
							"(SELECT dataKey FROM xs_childdictionary A WHERE A.child_id =d.xs_custom_area ) AS n2," +
							"(SELECT COUNT(*) FROM xs_car_sell_info aa " +
							"JOIN xs_car_info bb ON bb.xs_car_id=aa.xs_car_id " +
							"JOIN xs_car_model cc ON cc.xs_model_id=bb.xs_car_model_id " +
							"JOIN xs_custom_info dd ON dd.custom_id=aa.custom_id " +
							"JOIN xs_sell_flow_control ee ON ee.xsfcontrol_document=aa.sell_code" +
							" AND ee.xsfcontrol_car_id=aa.xs_car_id " +
							"LEFT JOIN xs_sell_retreat ff ON ff.retreat_id=aa.out_id " +
							" WHERE (aa.xs_car_give_up IS NULL OR aa.xs_car_give_up=0)" );
							sql.append(querySql);
							sql.append(") AS nns " +
							"FROM xs_car_sell_info A " +
							"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id  " +
							"JOIN xs_car_model c ON c.xs_model_id=b.xs_car_model_id " +
							"JOIN xs_custom_info d ON d.custom_id=a.custom_id " +
							"JOIN xs_sell_flow_control E ON e.xsfcontrol_document=a.sell_code " +
							"AND e.xsfcontrol_car_id=a.xs_car_id " +
							"LEFT JOIN xs_sell_retreat f ON f.retreat_id=a.out_id " +
							" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " );
						 	sql.append(querySql2);
							sql.append(" GROUP BY d.xs_custom_area) ss ");
				}
		if(sellCustomAnalyseVo.getCtype()!=null&&!"".equals(sellCustomAnalyseVo.getCtype())&&"type9".equals(sellCustomAnalyseVo.getCtype())){
					//客户性质分析
					sql.append("SELECT ss.n1 ,ss.n2,ss.nn,(ss.nn/ss.nns)*100 " +
							"FROM (SELECT COUNT(*) AS nn,d.xs_custom_property AS n1," +
							"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.xs_custom_property ) AS n2," +
							"(SELECT COUNT(*) FROM xs_car_sell_info aa " +
							"JOIN xs_car_info bb ON bb.xs_car_id=aa.xs_car_id " +
							"JOIN xs_car_model cc ON cc.xs_model_id=bb.xs_car_model_id " +
							"JOIN xs_custom_info dd ON dd.custom_id=aa.custom_id " +
							"JOIN xs_sell_flow_control ee ON ee.xsfcontrol_document=aa.sell_code" +
							" AND ee.xsfcontrol_car_id=aa.xs_car_id " +
							"LEFT JOIN xs_sell_retreat ff ON ff.retreat_id=aa.out_id " +
							" WHERE (aa.xs_car_give_up IS NULL OR aa.xs_car_give_up=0)" );
							sql.append(querySql);
							sql.append(") AS nns " +
							"FROM xs_car_sell_info A " +
							"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id  " +
							"JOIN xs_car_model c ON c.xs_model_id=b.xs_car_model_id " +
							"JOIN xs_custom_info d ON d.custom_id=a.custom_id " +
							"JOIN xs_sell_flow_control E ON e.xsfcontrol_document=a.sell_code " +
							"AND e.xsfcontrol_car_id=a.xs_car_id " +
							"LEFT JOIN xs_sell_retreat f ON f.retreat_id=a.out_id " +
							" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " );
						 	sql.append(querySql2);
							sql.append(" GROUP BY d.xs_custom_property) ss ");
				}
		if(sellCustomAnalyseVo.getCtype()!=null&&!"".equals(sellCustomAnalyseVo.getCtype())&&"type10".equals(sellCustomAnalyseVo.getCtype())){
					//购车用途分析
					sql.append("SELECT ss.n1 ,ss.n2,ss.nn,(ss.nn/ss.nns)*100 " +
							"FROM (SELECT COUNT(*) AS nn,d.xs_custom_application AS n1," +
							"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.xs_custom_application ) AS n2," +
							"(SELECT COUNT(*) FROM xs_car_sell_info aa " +
							"JOIN xs_car_info bb ON bb.xs_car_id=aa.xs_car_id " +
							"JOIN xs_car_model cc ON cc.xs_model_id=bb.xs_car_model_id " +
							"JOIN xs_custom_info dd ON dd.custom_id=aa.custom_id " +
							"JOIN xs_sell_flow_control ee ON ee.xsfcontrol_document=aa.sell_code" +
							" AND ee.xsfcontrol_car_id=aa.xs_car_id " +
							"LEFT JOIN xs_sell_retreat f ON ff.retreat_id=aa.out_id " +
							" WHERE (aa.xs_car_give_up IS NULL OR aa.xs_car_give_up=0)" );
							sql.append(querySql);
							sql.append(") AS nns " +
							"FROM xs_car_sell_info A " +
							"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id  " +
							"JOIN xs_car_model c ON c.xs_model_id=b.xs_car_model_id " +
							"JOIN xs_custom_info d ON d.custom_id=a.custom_id " +
							"JOIN xs_sell_flow_control E ON e.xsfcontrol_document=a.sell_code " +
							"AND e.xsfcontrol_car_id=a.xs_car_id " +
							"LEFT JOIN xs_sell_retreat f ON f.retreat_id=a.out_id " +
							" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " );
						 	sql.append(querySql2);
							sql.append(" GROUP BY d.xs_custom_application) ss ");
				}
		if(sellCustomAnalyseVo.getCtype()!=null&&!"".equals(sellCustomAnalyseVo.getCtype())&&"type11".equals(sellCustomAnalyseVo.getCtype())){

					//选择理由分析
					sql.append("SELECT ss.n1 ,ss.n2,ss.nn,(ss.nn/ss.nns)*100 " +
							"FROM (SELECT COUNT(*) AS nn,d.xs_custom_reason AS n1," +
							"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.xs_custom_reason ) AS n2," +
							"(SELECT COUNT(*) FROM xs_car_sell_info aa " +
							"JOIN xs_car_info bb ON bb.xs_car_id=aa.xs_car_id " +
							"JOIN xs_car_model cc ON cc.xs_model_id=bb.xs_car_model_id " +
							"JOIN xs_custom_info dd ON dd.custom_id=aa.custom_id " +
							"JOIN xs_sell_flow_control ee ON ee.xsfcontrol_document=aa.sell_code" +
							" AND ee.xsfcontrol_car_id=aa.xs_car_id " +
							"LEFT JOIN xs_sell_retreat ff ON ff.retreat_id=aa.out_id " +
							" WHERE (aa.xs_car_give_up IS NULL OR aa.xs_car_give_up=0)" );
							sql.append(querySql);
							sql.append(") AS nns " +
							"FROM xs_car_sell_info A " +
							"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id  " +
							"JOIN xs_car_model c ON c.xs_model_id=b.xs_car_model_id " +
							"JOIN xs_custom_info d ON d.custom_id=a.custom_id " +
							"JOIN xs_sell_flow_control E ON e.xsfcontrol_document=a.sell_code " +
							"AND e.xsfcontrol_car_id=a.xs_car_id " +
							"LEFT JOIN xs_sell_retreat f ON f.retreat_id=a.out_id " +
							" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " );
						 	sql.append(querySql2);
							sql.append(" GROUP BY d.xs_custom_reason) ss ");
				}
		List<Object[]> resultList = createSQLQuery(sql.toString(), sellCustomAnalyseVo
				.getPage(), sellCustomAnalyseVo.getRows());
		List<SellCustomAnalyseVo> list = new ArrayList<SellCustomAnalyseVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SellCustomAnalyseVo cany=null;
			int sumCount=0;
			for (int i = 0; i < resultList.size(); i++) {
				obj = resultList.get(i);
				if(obj[2]!=null&&obj[2].toString().length()>0){
					sumCount+=Integer.parseInt(obj[2].toString());
				}
			}
			for (int i = 0; i < resultList.size(); i++) {
				cany = new SellCustomAnalyseVo();
				obj = resultList.get(i);
				cany.setCuId(obj[0] != null ? Integer.parseInt(obj[0].toString()) : null);
				cany.setCuName(obj[1] != null ?obj[1].toString() : "未知");
				cany.setNum(obj[2] != null ? Integer.parseInt(obj[2].toString()) : 0);
				cany.setPercent(Utils.doubleFormat((Double.parseDouble(cany.getNum()+"")/Double.parseDouble(sumCount+""))*100));
				list.add(cany);
			}
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}
//销售毛利分析
	
	public DatagridAnalyze querySellMlAnaly(SellCustomAnalyseVo sellCustomAnalyseVo)
			throws Exception {
		DatagridAnalyze dg = new DatagridAnalyze();
		StringBuffer sql = new StringBuffer("SELECT ff.n1,ff.n2,ff.n3,ff.n4,ff.n5,ff.n6,ff.n7," +
				"ff.n8,ff.n9,ff.n10,ff.n11,ff.n12,ff.n13," +
				"( CASE WHEN ff.n22 IN (SELECT xs_car_sel_id FROM xs_sell_exitCar) THEN -(ff.n13-ff.n12) ELSE (ff.n13-ff.n12) END ) AS zhml," +
				"ff.n14,ff.n15,(CASE WHEN ff.n22 IN (SELECT xs_car_sel_id FROM xs_sell_exitCar) THEN -(ff.n14-ff.n15) ELSE (ff.n14-ff.n15)END )AS dbml," +
				"ff.n16,ff.n17,(CASE WHEN ff.n22 IN (SELECT xs_car_sel_id FROM xs_sell_exitCar) THEN -(ff.n16-ff.n17) ELSE (ff.n16-ff.n17) END ) AS bxml," +
				"(CASE WHEN ff.n22 IN (SELECT xs_car_sel_id FROM xs_sell_exitCar) THEN -((ff.n13-ff.n12)+(ff.n14-ff.n15)+(ff.n16-ff.n17))" +
				" ELSE ((ff.n13-ff.n12)+(ff.n14-ff.n15)+(ff.n16-ff.n17)) END )AS zongMl,ff.n18," +
				"(CASE WHEN ff.n22 IN (SELECT xs_car_sel_id FROM xs_sell_exitCar) THEN -(ff.n11-ff.n18) ELSE (ff.n11-ff.n18) END ) AS xjml," +
				"ff.n19,ff.n20,ff.n21,ff.n22 " +
				"FROM(SELECT zz.n1 AS n1,zz.n2 AS n2,zz.n3 AS n3,zz.n4 AS n4," +
				"zz.n5 AS  n5,zz.n6 AS n6,zz.n7 AS n7,zz.n8 AS n8,zz.n9 AS n9," +
				"(CASE WHEN zz.n10 IS NOT NULL THEN zz.n10 ELSE 0 END)AS n10," +
				"(CASE WHEN zz.n11 IS NOT NULL THEN zz.n11 ELSE 0 END)AS n11," +
				"(CASE WHEN zz.n12 IS NOT NULL THEN zz.n12 ELSE 0 END)AS n12," +
				"(CASE WHEN zz.n13 IS NOT NULL THEN zz.n13 ELSE 0 END)AS n13," +
				"(CASE WHEN zz.n14 IS NOT NULL THEN zz.n14 ELSE 0 END)AS n14," +
				"(CASE WHEN zz.n15 IS NOT NULL THEN zz.n15 ELSE 0 END)AS n15," +
				"(CASE WHEN zz.n16 IS NOT NULL THEN zz.n16 ELSE 0 END)AS n16," +
				"(CASE WHEN zz.n17 IS NOT NULL THEN zz.n17 ELSE 0 END)AS n17," +
				"(CASE WHEN zz.n18 IS NOT NULL THEN zz.n18 ELSE 0 END)AS n18," +
				"zz.n19,zz.n20,zz.n21,zz.n22 " +
				"FROM (SELECT D.xs_custom_name AS n1,a.xs_car_sel_data AS n2," +
				"c.STF_NAME AS n3," +
				"(SELECT datavalue FROM xs_childdictionary Aa WHERE Aa.child_id =B.xs_car_brand) AS n4," +
				"mo.xs_model_name AS n5," +
				"(SELECT datavalue FROM xs_childdictionary Acc WHERE Acc.child_id =b.xs_car_color) AS  n6," +
				"b.xs_car_vin_number AS n7,b.xs_car_motor_number AS n8,b.xs_car_ocn AS n9," +
				"b.xs_car_price AS n10,a.xs_car_sel_transaction_money AS n11,SUM(zh.decorate_amount) AS n12," +
				"SUM(zh.decorate_sell) AS n13,SUM(db.cost_price)  AS n14,SUM(db.db_project_cost)  AS n15," +
				"SUM(ins.custom_cost)  AS n16,SUM(ins.prime_cost)  AS n17,mo.xs_model_salesLimitPrice  AS n18," +
				"invo.invoice_parce  AS n19,invo.invoice_date AS n20,invo.invoice_code  AS n21,a.xs_car_sel_id AS n22" +
				" FROM xs_car_sell_info A " +
				"JOIN xs_sell_flow_control co ON co.xsfcontrol_car_id=a.xs_car_id " +
				"AND co.xsfcontrol_document=a.sell_code " +
				"JOIN xs_car_info B ON a.xs_car_id=b.xs_car_id " +
				"JOIN xs_car_model mo ON mo.xs_model_id=b.xs_car_model_id " +
				"JOIN bas_stuff C ON c.STF_ID=a.xs_car_stf_id " +
				"JOIN xs_custom_info D ON d.custom_id=a.custom_id " +
				"LEFT JOIN xs_sell_db_project db ON db.xs_car_sel_id=a.xs_car_sel_id " +
				"LEFT JOIN xs_sell_zh_project zh ON zh.xs_car_sel_id=a.xs_car_sel_id " +
				"LEFT JOIN xs_sell_insurance ins ON ins.xs_car_sel_id=a.xs_car_sel_id " +
				"LEFT JOIN xs_sell_invoice invo ON invo.xs_car_sel_id=a.xs_car_sel_id " +
				" JOIN xs_sell_retreat ret ON a.out_id=ret.retreat_id where 1=1 " );
		        if(sellCustomAnalyseVo.getEnterprise_id()!=null&&"yes".equals(sellCustomAnalyseVo.getEnterprise_id())){
					sql.append(" and a.enterprise_id ='"+sellCustomAnalyseVo.getEnterprise_id()+"'");	

		        }

		        if(sellCustomAnalyseVo.getXsCarSelData()!=null&&!"".equals(sellCustomAnalyseVo.getXsCarSelData())){
						sql.append(" and DATE(a.xs_car_sel_data)>='"+sellCustomAnalyseVo.getXsCarSelData()+"'");	
						
					}
		        if(sellCustomAnalyseVo.getXsCarSelData2()!=null&&!"".equals(sellCustomAnalyseVo.getXsCarSelData2())){
						sql.append(" and DATE(a.xs_car_sel_data)<='"+sellCustomAnalyseVo.getXsCarSelData2()+"'");	
						
		        }
		        if((sellCustomAnalyseVo.getXsCarSelData()==null||"".equals(sellCustomAnalyseVo.getXsCarSelData()))&&
		        		(sellCustomAnalyseVo.getXsCarSelData2()==null||"".equals(sellCustomAnalyseVo.getXsCarSelData2()))){
		        	sql	.append(" and DATE(a.xs_car_sel_data) between '"+ FormatTime.yesTempTady(
							FormatTime.DEFAULT_FORMAT2,(
									basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
											Contstants.COLLIGATES.DEFAULTSHOWDAY,sellCustomAnalyseVo.getEnterprise_id()).getCiValue()))
											+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		        	
		        }
		        
				
				if(sellCustomAnalyseVo.getRetreat_date()!=null&&!"".equals(sellCustomAnalyseVo.getRetreat_date())){
					sql.append(" and DATE(ret.retreat_date)>='"+sellCustomAnalyseVo.getRetreat_date()+"'");	
					
				}
				if(sellCustomAnalyseVo.getRetreat_date2()!=null&&!"".equals(sellCustomAnalyseVo.getRetreat_date2())){
					sql.append(" and DATE(ret.retreat_date)<='"+sellCustomAnalyseVo.getRetreat_date2()+"'");	
					
				}
				if(sellCustomAnalyseVo.getCarBrand()!=null&&!"".equals(sellCustomAnalyseVo.getCarBrand())){
					sql.append(" and B.xs_car_brand='"+sellCustomAnalyseVo.getCarBrand()+"'");	
					
				}
				if(sellCustomAnalyseVo.getCarModel()!=null&&!"".equals(sellCustomAnalyseVo.getCarModel())){
					sql.append(" and b.xs_car_model_id='"+sellCustomAnalyseVo.getCarModel()+"'");	
					
				}
				if(sellCustomAnalyseVo.getCarVinNumber()!=null&&!"".equals(sellCustomAnalyseVo.getCarVinNumber())){
					sql.append(" and b.xs_car_vin_number like '%"+sellCustomAnalyseVo.getCarVinNumber()+"%'");	
					
				}
				if(sellCustomAnalyseVo.getCuName()!=null&&!"".equals(sellCustomAnalyseVo.getCuName())){
					sql.append(" and D.xs_custom_name like '%"+sellCustomAnalyseVo.getCuName()+"%'");	
					
				}
				if(sellCustomAnalyseVo.getXsCarStfId()!=null&&!"".equals(sellCustomAnalyseVo.getXsCarStfId())){
					sql.append(" and a.xs_car_stf_id = '"+sellCustomAnalyseVo.getXsCarStfId()+"'");	
					
				}
		sql.append("GROUP BY a.xs_car_sel_id)AS zz)AS ff");

		List<Object[]> resultList = createSQLQuery(sql.toString(), sellCustomAnalyseVo
				.getPage(), sellCustomAnalyseVo.getRows());
		List<SellCustomAnalyseVo> list = new ArrayList<SellCustomAnalyseVo>();
		List<SellCustomAnalyseVo> footers = new ArrayList<SellCustomAnalyseVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SellCustomAnalyseVo ml=null;
			for (int i = 0; i < resultList.size(); i++) {
				 ml = new SellCustomAnalyseVo();
				obj = resultList.get(i);
				ml.setCuName(obj[0] != null ?obj[0].toString() : null);
				ml.setXsCarSelData(obj[1] != null ?obj[1].toString() : null);
				ml.setStfName(obj[2] != null ?obj[2].toString() : null);
				ml.setCarBrandName(obj[3] != null ?obj[3].toString() : null);
				ml.setCarModelName(obj[4] != null ?obj[4].toString() : null);
				ml.setCarColorName(obj[5] != null ?obj[5].toString() : null);
				ml.setCarVinNumber(obj[6] != null ?obj[6].toString() : null);
				ml.setFdJ(obj[7] != null ?obj[7].toString() : null);
				ml.setOcn(obj[8] != null ?obj[8].toString() : null);
				ml.setModelCostPrice(obj[9] != null ?Double.parseDouble(obj[9].toString()) : null);
				ml.setXsCarSelTransactionMoney(obj[10] != null ?Double.parseDouble(obj[10].toString()) : null);
				ml.setDecorate_amount(obj[11] != null ?Double.parseDouble(obj[11].toString()) : null);
				ml.setDecorate_sell(obj[12] != null ?Double.parseDouble(obj[12].toString()) : null);
				ml.setZhml(obj[13] != null ?Double.parseDouble(obj[13].toString()) : null);
				ml.setCost_price(obj[14] != null ?Double.parseDouble(obj[14].toString()) : null);
				ml.setDb_project_cost(obj[15] != null ?Double.parseDouble(obj[15].toString()) : null);
				ml.setDbml(obj[16] != null ?Double.parseDouble(obj[16].toString()) : null);
				ml.setCustom_cost(obj[17] != null ?Double.parseDouble(obj[17].toString()) : null);
				ml.setPrime_cost(obj[18] != null ?Double.parseDouble(obj[18].toString()) : null);
				ml.setBxml(obj[19] != null ?Double.parseDouble(obj[19].toString()) : null);
				ml.setZml(obj[20] != null ?Double.parseDouble(obj[20].toString()) : null);
				ml.setXs_model_salesLimitPrice(obj[21] != null ?Double.parseDouble(obj[21].toString()) : null);
				ml.setXjml(obj[22] != null ?Double.parseDouble(obj[22].toString()) : null);
				ml.setInvoice_parce(obj[23] != null ?Double.parseDouble(obj[23].toString()) : null);
				ml.setInvoice_date(obj[24] != null ?obj[24].toString() : null);
				ml.setInvoice_code(obj[25] != null ?obj[25].toString() : null);
				ml.setXsCarSelId(obj[26] != null ?Integer.parseInt(obj[26].toString()) : null);
				list.add(ml);
				
			}
			 ml = new SellCustomAnalyseVo();
			 ml.setCuName("汇总");
			 Double sum1=0.0;
			 Double sum2=0.0;
			 Double sum3=0.0;
			 Double sum4=0.0;
			 Double sum5=0.0;
			 Double sum6=0.0;
			 Double sum7=0.0;
			 Double sum8=0.0;
			 Double sum9=0.0;
			 Double sum10=0.0;
			 Double sum11=0.0;
			 Double sum12=0.0;
			 Double sum13=0.0;
			 Double sum14=0.0;
			 Double sum15=0.0;
			 for (SellCustomAnalyseVo wo : list) {
			 if(wo.getModelCostPrice()!=null&&!"".equals(wo.getModelCostPrice())){
				 sum1+=wo.getModelCostPrice();
				 }
			 if(wo.getXsCarSelTransactionMoney()!=null&&!"".equals(wo.getXsCarSelTransactionMoney())){
				 sum2+=wo.getXsCarSelTransactionMoney();
				 }
			
			 if(wo.getDecorate_amount()!=null&&!"".equals(wo.getDecorate_amount())){
				 sum3+=wo.getDecorate_amount();
				 }
			 if(wo.getDecorate_sell()!=null&&!"".equals(wo.getDecorate_sell())){
				 sum4+=wo.getDecorate_sell();
				 }
			 if(wo.getZhml()!=null&&!"".equals(wo.getZhml())){
				 sum5+=wo.getZhml();
				 }
			 if(wo.getCost_price()!=null&&!"".equals(wo.getCost_price())){
				 sum6+=wo.getCost_price();
				 }
			 
			 if(wo.getDb_project_cost()!=null&&!"".equals(wo.getDb_project_cost())){
				 sum7+=wo.getDb_project_cost();
				 }
			 
			 if(wo.getDbml()!=null&&!"".equals(wo.getDbml())){
				 sum8+=wo.getDbml();
				 }
			 
			 if(wo.getCustom_cost()!=null&&!"".equals(wo.getCustom_cost())){
				 sum9+=wo.getCustom_cost();
				 }
			 if(wo.getPrime_cost()!=null&&!"".equals(wo.getPrime_cost())){
				 sum10+=wo.getPrime_cost();
				 }
			 if(wo.getBxml()!=null&&!"".equals(wo.getBxml())){
				 sum11+=wo.getBxml();
				 }
			 if(wo.getZml()!=null&&!"".equals(wo.getZml())){
				 sum12+=wo.getZml();
				 }
			 if(wo.getXs_model_salesLimitPrice()!=null&&!"".equals(wo.getXs_model_salesLimitPrice())){
				 sum13+=wo.getXs_model_salesLimitPrice();
				 }
			 if(wo.getXjml()!=null&&!"".equals(wo.getXjml())){
				 sum14+=wo.getXjml();
				 }
			 if(wo.getInvoice_parce()!=null&&!"".equals(wo.getInvoice_parce())){
				 sum15+=wo.getInvoice_parce();
				 }
			 
			 }
			 	ml.setModelCostPrice(sum1);
				ml.setXsCarSelTransactionMoney(sum2);
				ml.setDecorate_amount(sum3);
				ml.setDecorate_sell(sum4);
				ml.setZhml(sum5);
				ml.setCost_price(sum6);
				ml.setDb_project_cost(sum7);
				ml.setDbml(sum8);
				ml.setCustom_cost(sum9);
				ml.setPrime_cost(sum10);
				ml.setBxml(sum11);
				ml.setZml(sum12);
				ml.setXs_model_salesLimitPrice(sum13);
				ml.setXjml(sum14);
				ml.setInvoice_parce(sum15);
				footers.add(ml);
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setFooter(footers);
		dg.setTotal(total);
		return dg;
	}

}
