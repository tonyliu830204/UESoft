package com.syuesoft.sell.pcassay.serviceimpl;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.pcassay.dao.PotentialcustomerAssayDao;
import com.syuesoft.sell.pcassay.service.PotentialcustomerAssayService;
import com.syuesoft.sell.pcassay.vo.PotentialcustomerAssayVo;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;

@Service("potentialcustomerAssayService")
public class PotentialcustomerAssayServiceImpl extends BaseLogServiceImpl
		implements PotentialcustomerAssayService {
	// 图形报表
	private JFreeChart chart;
	@Resource
	private PotentialcustomerAssayDao potentialcustomerAssayDao;
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
//	private Calendar can = Calendar.getInstance();;
//	private String year = can.get(Calendar.YEAR) + "";
//	private String month = can.get(Calendar.MONTH) + 1 < 10 ? "0"
//			+ can.get(Calendar.MONTH) + 1 : can.get(Calendar.MONTH) + 1 + "";
//	private String day = can.get(Calendar.DAY_OF_MONTH) + "";
//	private String day2 = "01";
//	private String str[0] = year + "-" + month + "-" + day2;
//	private String edate = year + "-" + month + "-" + day;
	
	// 设置JfreeChart图形报表样式
	
	public JFreeChart getJFreeChart(List list, PieDataset defaultpiedataset,
			String titles) {
		chart = ChartFactory.createPieChart3D(titles, defaultpiedataset, true,
				false, false);
		chart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F,
				Color.LIGHT_GRAY, 10000F, 0.0F, Color.YELLOW));

		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

		Font font = new Font("黑体", Font.TRUETYPE_FONT, 10);

		TextTitle title = chart.getTitle();
		title.setFont(new Font("SimSun", Font.BOLD, 14));
		title.setMargin(5.0, 0, 5.0, 0);

		// chart.addSubtitle(new TextTitle("2012年3季度" , font));

		LegendTitle legend = (LegendTitle) chart.getLegend();
		legend.setItemFont(font);

		Plot plot = chart.getPlot();
		plot.setNoDataMessage("无数据显示");
		plot.setNoDataMessageFont(font);
		plot.setNoDataMessagePaint(Color.RED);
		// plot.setBackgroundAlpha(0.5F);

		PiePlot piePlot = (PiePlot) plot;
		piePlot.setStartAngle(290);
		piePlot.setDirection(Rotation.CLOCKWISE);
		piePlot.setForegroundAlpha(0.7F);
		piePlot.setLabelFont(font);
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}:{1}({2})"));
		// piePlot.setExplodePercent("苹果", 0.2);
		piePlot.setCircular(false);
		piePlot.setSectionOutlinesVisible(false);
		return chart;
	}

	// 获取成交障碍统计信息
	
	public List getPotentialCustomerAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		
 	StringBuffer strbf = new StringBuffer();
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and cc.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		
			
			if (potentialcustomerAssayVo.getXsCustomInputdate() != null
					&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
					&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
							"undefined")) {
				
					strbf.append(" and DATE(cc.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
				} 
			if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
						&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
						&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
								"undefined")) {
					
					strbf.append(" and DATE(cc.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
				}
			
		if((potentialcustomerAssayVo.getXsCustomInputdate() == null
				||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
				(potentialcustomerAssayVo.getXsCustomInputdate2() == null
						||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
			strbf.append(" and DATE(cc.xs_custom_inputdata) between '"+ FormatTime.yesTempTady(
							FormatTime.DEFAULT_FORMAT2,(
									basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
											Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
											+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		/*}
		strbf.append(" and cc.xs_custom_inputdata between '" + str[0]
				+ "' and '" + edate + "'");*/
		
		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and cc.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
		StringBuffer sql = new StringBuffer(
				"SELECT ss.s1,ss.counts,(ss.counts/ss.sums)*100  " +
				"FROM  (SELECT aa.obstacle AS s1, COUNT(*) AS counts, " +
				" (SELECT COUNT(*) FROM xs_sell_custom_tracing aa  " +
				"INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id  " +
				"WHERE 1 = 1  ");
		sql.append(strbf);
		sql.append(") AS sums " );
		sql.append("  FROM xs_sell_custom_tracing aa  " +
				"INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id " +
				" WHERE 1 = 1 " );
		sql.append(strbf);
		sql.append(" GROUP BY aa.obstacle ) AS ss "); 
		
		
		
		
		List rlist = potentialcustomerAssayDao.createSQLQuery(sql.toString());
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("0")) {
					XsChilddictionary xsChilddictionary = (XsChilddictionary) potentialcustomerAssayDao
							.get("from XsChilddictionary WHERE childId="
									+ obj[0] + "");
					vo.setObstacle(xsChilddictionary.getDataValue());
				} else {
					vo.setObstacle("不确定");
				}

				vo.setAmount(obj[1] != null ? obj[1].toString() : "");
				vo.setPercentage(obj[2] != null ? obj[2].toString() : "");
				list.add(vo);
			}
		}
		return list;
	}

	// 成交障碍给饼图设值
	
	public PieDataset createPieDatasetOfPotentialCustomerAssay(List list) {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				defaultpiedataset.setValue("" + vo.getObstacle() != null ? vo
						.getObstacle().toString() : "" + "",
						vo.getAmount() != null ? Integer.parseInt(vo
								.getAmount().toString()) : 0);
			}
		}
		return defaultpiedataset;
	}

	// 获取成交几率分析信息
	
	public List getBargainonAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		StringBuffer strbf = new StringBuffer();
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and cc.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		if (potentialcustomerAssayVo.getXsCustomInputdate() != null
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
						"undefined")) {
			
				strbf.append(" and DATE(cc.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
			} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
							"undefined")) {
				
				strbf.append(" and DATE(cc.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
			}
		
	if((potentialcustomerAssayVo.getXsCustomInputdate() == null
			||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
			(potentialcustomerAssayVo.getXsCustomInputdate2() == null
					||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
		strbf.append(" and DATE(cc.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
						FormatTime.DEFAULT_FORMAT2,(
								basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
										Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
										+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}
		
		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and cc.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
		
		StringBuffer sql = new StringBuffer(
				"select ss.s1,ss.counts,(ss.counts/ss.sums)*100 from" +
				" (SELECT aa.buy_probability as s1, COUNT(*) AS counts, "
						+ " (SELECT COUNT(*)  " +
							"FROM xs_sell_custom_tracing aa  "
						+ " INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id "
						+ " WHERE 1 = 1");
		sql.append(strbf);
		sql.append(") AS sums " 
						+ " FROM xs_sell_custom_tracing aa  "
						+ " INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id "
						+ " WHERE 1 = 1 " );
		sql.append(strbf);		
		sql.append(" GROUP BY aa.buy_probability)as ss "); //
		
		
		List rlist = potentialcustomerAssayDao.createSQLQuery(sql.toString());
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("0")) {
					XsChilddictionary xsChilddictionary = (XsChilddictionary) potentialcustomerAssayDao
							.get("from XsChilddictionary WHERE childId="
									+ obj[0] + "");
					vo.setBuyProbability(xsChilddictionary.getDataValue());
				} else {
					vo.setBuyProbability("不确定");
				}

				vo.setAmount(obj[1] != null ? obj[1].toString() : "");
				vo.setPercentage(obj[2] != null ? obj[2].toString() : "");
				list.add(vo);
			}
		}
		return list;
	}

	// 成交几率给饼图设值
	
	public PieDataset createPieDatasetOfBargainonAssay(List list) {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				defaultpiedataset.setValue(
						"" + vo.getBuyProbability() != null ? vo
								.getBuyProbability().toString() : "" + "", vo
								.getAmount() != null ? Integer.parseInt(vo
								.getAmount().toString()) : 0);
			}
		}
		return defaultpiedataset;
	}

	// 获取潜在客户来源分析信息
	
	public List getCustomSourceAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		StringBuffer strbf = new StringBuffer();
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and cc.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		if (potentialcustomerAssayVo.getXsCustomInputdate() != null
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
						"undefined")) {
			
				strbf.append(" and DATE(cc.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
			} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
							"undefined")) {
				
				strbf.append(" and DATE(cc.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
			}
		
	if((potentialcustomerAssayVo.getXsCustomInputdate() == null
			||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
			(potentialcustomerAssayVo.getXsCustomInputdate2() == null
					||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
		strbf.append(" and DATE(cc.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
						FormatTime.DEFAULT_FORMAT2,(
								basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
										Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
										+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}
		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and cc.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
		StringBuffer sql = new StringBuffer(
				" select ss.s1,ss.counts,(ss.counts/ss.sums)*100 from( " +
					"SELECT cc.Xs_Custom_Source as s1, COUNT(*) AS counts, "
						+ " (SELECT COUNT(*)  "
						+ " FROM  xs_sell_custom_tracing aa  "
						+ " INNER join xs_custom_info cc ON aa.custom_id = cc.custom_id "
						+ " WHERE 1 = 1 " );
		sql.append(strbf);
		sql.append(" ) AS sums");
		sql.append(" FROM xs_sell_custom_tracing aa  "
						+ " INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id "
						+ " WHERE 1 = 1 ");
		sql.append(strbf);
		sql.append(	" GROUP BY cc.Xs_Custom_Source )as ss "); //
		
		
		List rlist = potentialcustomerAssayDao.createSQLQuery(sql.toString());
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("0")) {
					XsChilddictionary xsChilddictionary = (XsChilddictionary) potentialcustomerAssayDao
							.get("from XsChilddictionary WHERE childId="
									+ obj[0] + "");
					vo.setXsCustomSource(xsChilddictionary.getDataValue());
				} else {
					vo.setXsCustomSource("其他");
				}
				vo.setAmount(obj[1] != null ? obj[1].toString() : "");
				vo.setPercentage(obj[2] != null ? obj[2].toString() : "");
				list.add(vo);
			}
		}
		return list;
	}

	// 潜在客户来源给饼图设值
	
	public PieDataset createPieDatasetOfCustomSource(List list) {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				defaultpiedataset.setValue(
						"" + vo.getXsCustomSource() != null ? vo
								.getXsCustomSource().toString() : "" + "", vo
								.getAmount() != null ? Integer.parseInt(vo
								.getAmount().toString()) : 0);
			}
		}
		return defaultpiedataset;
	}

	// 跟踪部门分析
	
	public List getDeptAssay(PotentialcustomerAssayVo potentialcustomerAssayVo)
			throws Exception {
		StringBuffer strbf = new StringBuffer();
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and cc.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		
		if (potentialcustomerAssayVo.getXsCustomInputdate() != null
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
						"undefined")) {
			
				strbf.append(" and DATE(cc.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
			} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
							"undefined")) {
				
				strbf.append(" and DATE(cc.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
			}
		
	if((potentialcustomerAssayVo.getXsCustomInputdate() == null
			||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
			(potentialcustomerAssayVo.getXsCustomInputdate2() == null
					||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
		strbf.append(" and DATE(cc.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
						FormatTime.DEFAULT_FORMAT2,(
								basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
										Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
										+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}
		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and cc.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
		StringBuffer sql = new StringBuffer(
				" select ss.s1,ss.counts,(ss.counts/ss.sums)*100 from("
						+ "	SELECT ee.dept_name as s1, COUNT(*) AS counts, " +
							"(SELECT COUNT(*) FROM xs_sell_custom_tracing aa" +
							" join xs_custom_info cc ON aa.custom_id = cc.custom_id" +
							" JOIN bas_stuff dd ON cc.stf_id = dd.stf_id "
						+ "	 JOIN bas_dept ee ON dd.dept_id = ee.dept_id " +
								" where 1=1");
		sql.append(strbf);
		sql.append("	) AS sums " +
						" FROM  xs_sell_custom_tracing aa  "
						+ "	 JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id"
						+ "	 JOIN bas_stuff dd ON cc.stf_id = dd.stf_id"
						+ "	 JOIN bas_dept ee ON dd.dept_id = ee.dept_id" +
								" where 1=1");
		sql.append(strbf);
		sql.append("	GROUP BY ee.dept_name) as ss ");
		
		
		List rlist = potentialcustomerAssayDao.createSQLQuery(sql.toString());
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("")) {
					vo.setDeptName(obj[0].toString());
				} else {
					vo.setDeptName("其他");
				}
				vo.setAmount(obj[1] != null ? obj[1].toString() : "");
				vo.setPercentage(obj[2] != null ? obj[2].toString() : "");
				list.add(vo);
			}
		}
		return list;
	}

	// 跟踪部门给饼图设值
	
	public PieDataset createPieDatasetOfDeptAssay(List list) throws Exception {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				defaultpiedataset.setValue("" + vo.getDeptName() != null ? vo
						.getDeptName().toString() : "" + "",
						vo.getAmount() != null ? Integer.parseInt(vo
								.getAmount().toString()) : 0);
			}
		}
		return defaultpiedataset;
	}

	// 获取跟踪班组分析信息
	
	public List getSellTeamAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		StringBuffer strbf = new StringBuffer();
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and cc.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		if (potentialcustomerAssayVo.getXsCustomInputdate() != null
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
						"undefined")) {
			
				strbf.append(" and DATE(cc.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
			} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
							"undefined")) {
				
				strbf.append(" and DATE(cc.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
			}
		
	if((potentialcustomerAssayVo.getXsCustomInputdate() == null
			||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
			(potentialcustomerAssayVo.getXsCustomInputdate2() == null
					||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
		strbf.append(" and DATE(cc.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
						FormatTime.DEFAULT_FORMAT2,(
								basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
										Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
										+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}
		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and cc.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
		StringBuffer sql = new StringBuffer(
				" select ss.s1,ss.counts,(ss.counts/ss.sums)*100 from ("
						+ "	SELECT ee.datavalue as s1, COUNT(*) AS counts,"
						+ "	(SELECT COUNT(*) FROM xs_sell_custom_tracing aa" +
							" INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id" +
							" JOIN bas_stuff dd ON cc.stf_id = dd.stf_id"
						+ "	 left JOIN xs_childdictionary ee ON dd.REPGRP_ID2 = ee.child_id" +
							" where 1=1");
		sql.append(strbf);
		sql.append(") AS sums"
						+ "	FROM xs_sell_custom_tracing aa"
						+ "	INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id"
						+ "	 JOIN bas_stuff dd ON cc.stf_id = dd.stf_id"
						+ "	 left JOIN xs_childdictionary ee ON dd.REPGRP_ID2 = ee.child_id" +
								" where 1=1 ");
		sql.append(strbf);
		sql.append(" GROUP BY  dd.REPGRP_ID2) as ss  "); //
		

		List rlist = potentialcustomerAssayDao.createSQLQuery(sql.toString());
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("0")) {
					vo.setSellTeam(obj[0].toString());
				} else {
					vo.setSellTeam("其他");
				}
				vo.setAmount(obj[1] != null ? obj[1].toString() : "");
				vo.setPercentage(obj[2] != null ? obj[2].toString() : "");
				list.add(vo);
			}
		}
		return list;
	}

	// 跟踪班组给饼图设值
	
	public PieDataset createPieDatasetOfSellTeamAssay(List list)
			throws Exception {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				defaultpiedataset.setValue("" + vo.getSellTeam() != null ? vo
						.getSellTeam().toString() : "" + "",
						vo.getAmount() != null ? Integer.parseInt(vo
								.getAmount().toString()) : 0);
			}
		}
		return defaultpiedataset;
	}

	// 跟踪业务员分析
	
	public List getStfNameAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		StringBuffer strbf = new StringBuffer();

				if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
					strbf.append(" and cc.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
				}
				
				if (potentialcustomerAssayVo.getXsCustomInputdate() != null
						&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
						&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
								"undefined")) {
					
						strbf.append(" and DATE(cc.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
					} 
				if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
							&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
							&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
									"undefined")) {
						
						strbf.append(" and DATE(cc.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
					}
				
			if((potentialcustomerAssayVo.getXsCustomInputdate() == null
					||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
					(potentialcustomerAssayVo.getXsCustomInputdate2() == null
							||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
				strbf.append(" and DATE(cc.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
								FormatTime.DEFAULT_FORMAT2,(
										basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
												Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
												+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
			}
				if (potentialcustomerAssayVo.getStfId() != null
						&& !potentialcustomerAssayVo.getStfId().equals("")
						&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
					strbf.append(" and cc.stf_id ="
							+ potentialcustomerAssayVo.getStfId() + "");
				}
		StringBuffer sql = new StringBuffer(
				" select ss.s1,ss.counts,(ss.counts/ss.sums)*100 from("
						+ "	SELECT dd.stf_name as s1, COUNT(*) AS counts,"
						+ "	(SELECT COUNT(*) FROM xs_sell_custom_tracing aa " +
						" INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id "
						+ "	LEFT JOIN bas_stuff dd ON cc.stf_id = dd.stf_id " +
						" where 1=1");
		sql.append(strbf);
		sql.append( "	) AS sums"
						
						+ "	FROM xs_sell_custom_tracing aa"
						+ "	INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id"
						+ "	LEFT JOIN bas_stuff dd ON cc.stf_id = dd.stf_id"
						+ "	WHERE 1 = 1 "); //
		sql.append(strbf);
		sql.append(" GROUP BY dd.stf_name) as ss");
		List rlist = potentialcustomerAssayDao.createSQLQuery(sql.toString());
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("0")) {
					vo.setStfName(obj[0].toString());
				} else {
					vo.setStfName("其他");
				}
				vo.setAmount(obj[1] != null ? obj[1].toString() : "");
				vo.setPercentage(obj[2] != null ? obj[2].toString() : "");
				list.add(vo);
			}
		}
		return list;
	}

	// 跟踪业务员分析给饼图设值
	
	public PieDataset createPieDatasetOfStfNameAssay(List list)
			throws Exception {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				defaultpiedataset.setValue("" + vo.getStfName() != null ? vo
						.getStfName().toString() : "" + "",
						vo.getAmount() != null ? Integer.parseInt(vo
								.getAmount().toString()) : 0);
			}
		}
		return defaultpiedataset;
	}

	// 获取潜在客户等级分析信息
	
	public List getCustomerLevelAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		StringBuffer strbf = new StringBuffer();
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and cc.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		
		if (potentialcustomerAssayVo.getXsCustomInputdate() != null
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
						"undefined")) {
			
				strbf.append(" and DATE(cc.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
			} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
							"undefined")) {
				
				strbf.append(" and DATE(cc.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
			}
		
	if((potentialcustomerAssayVo.getXsCustomInputdate() == null
			||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
			(potentialcustomerAssayVo.getXsCustomInputdate2() == null
					||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
		strbf.append(" and DATE(cc.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
						FormatTime.DEFAULT_FORMAT2,(
								basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
										Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
										+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}
		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and cc.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
		StringBuffer sql = new StringBuffer(
				" select ss.s1,ss.counts,(ss.counts/ss.sums)*100 from("
						+ "	SELECT dd.xs_leva_name as s1, COUNT(*) AS counts,"
						+ "	(SELECT COUNT(*) FROM xs_sell_custom_tracing aa " +
						" INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id "
						+ "	 LEFT JOIN xs_custom_leva dd ON cc.xs_custom_hide_level = dd.xs_leva_id " +
						" where 1=1");
		sql.append(strbf);
		sql.append( "	) AS sums"
						
						+ "	FROM xs_sell_custom_tracing aa"
						+ "	INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id"
						+ "	 LEFT JOIN xs_custom_leva dd ON cc.xs_custom_hide_level = dd.xs_leva_id"
						+ "	WHERE 1 = 1 "); //
		sql.append(strbf);
		sql.append(" GROUP BY dd.xs_leva_name) as ss");

		
		List rlist = potentialcustomerAssayDao.createSQLQuery(sql.toString());
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("0")) {
					vo.setCustomerLevel(obj[0].toString());
				} else {
					vo.setCustomerLevel("其他");
				}
				vo.setAmount(obj[1] != null ? obj[1].toString() : "");
				vo.setPercentage(obj[2] != null ? obj[2].toString() : "");
				list.add(vo);
			}
		}
		return list;
	}

	// 潜在客户等级分析给饼图设值
	
	public PieDataset createPieDatasetOfCustomerLevelAssay(List list)
			throws Exception {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				defaultpiedataset.setValue(
						"" + vo.getCustomerLevel() != null ? vo
								.getCustomerLevel().toString() : "" + "", vo
								.getAmount() != null ? Integer.parseInt(vo
								.getAmount().toString()) : 0);
			}
		}
		return defaultpiedataset;
	}

	// 购买车型号信息分析
	
	public List getBuyCarModelAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		StringBuffer strbf = new StringBuffer();
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and cc.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		if (potentialcustomerAssayVo.getXsCustomInputdate() != null
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
						"undefined")) {
			
				strbf.append(" and DATE( cc.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
			} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
							"undefined")) {
				
				strbf.append(" and DATE( cc.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
			}
		
	if((potentialcustomerAssayVo.getXsCustomInputdate() == null
			||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
			(potentialcustomerAssayVo.getXsCustomInputdate2() == null
					||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
		strbf.append(" and DATE( cc.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
						FormatTime.DEFAULT_FORMAT2,(
								basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
										Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
										+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}
		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and cc.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
		StringBuffer sql = new StringBuffer(
				"SELECT ss.s1,ss.counts,(ss.counts/ss.sums)*100  " +
				"FROM  (SELECT model.xs_model_name AS s1, COUNT(*) AS counts, " +
				" (SELECT COUNT(*) FROM xs_sell_custom_tracing aa  " +
				" INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id  " +
				" left JOIN xs_car_model model ON  model.xs_model_id = aa.caiModel_need " +
				" WHERE 1 = 1  ");
		sql.append(strbf);
		sql.append(") AS sums " );
		sql.append("  FROM xs_sell_custom_tracing aa  " +
				" INNER JOIN xs_custom_info cc ON aa.custom_id = cc.custom_id " +
				" LEFT JOIN xs_car_model model ON  model.xs_model_id = aa.caiModel_need " +
				" WHERE 1 = 1 " );
		sql.append(strbf);
		sql.append(" GROUP BY aa.caiModel_need ) AS ss "); 
		/*StringBuffer sql = new StringBuffer(
				"SELECT model.xs_model_name, COUNT(* ) AS counts,"
						+ "	custom.xs_custom_inputdata,"
						+ "	custom.stf_id,sell.enterprise_id "
						+ "	FROM  xs_car_sell_info sell"
						
						+ "	 JOIN xs_sell_flow_control flow ON sell.sell_code = flow.xsfcontrol_document " +
								"and  sell.xs_car_id=flow.xsfcontrol_car_id"
						+ "	 JOIN xs_car_info car ON car.xs_car_id = sell.xs_car_id"
						+ "	 JOIN xs_car_model model ON  model.xs_model_id = car.xs_car_model_id "
						+ "	 JOIN xs_custom_info custom ON  custom.custom_id = sell.custom_id "
						+ " WHERE  sell.xs_car_sel_id not in( select xs_car_sel_id from xs_sell_exitCar) " +
								" 	GROUP BY model.xs_model_name HAVING 1 = 1 ");*/
	
			List rlist = potentialcustomerAssayDao.createSQLQuery(sql.toString());
			List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("0")) {
					vo.setCarModel(obj[0].toString());
				}else{
					vo.setCarModel("其他");
				}
				vo.setAmount(obj[1] != null ? obj[1].toString() : "");
				vo.setPercentage(obj[2] != null ? obj[2].toString() : "");
				list.add(vo);
			}
		}
		return list;
	}

	// 购买车型号分析给饼图设值
	
	public PieDataset createPieDatasetOfBuyCarModelAssay(List list)
			throws Exception {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				defaultpiedataset.setValue("" + vo.getCarModel() != null ? vo
						.getCarModel().toString() : "" + "",
						vo.getAmount() != null ? Integer.parseInt(vo
								.getAmount().toString()) : 0);
			}
		}
		return defaultpiedataset;
	}

	/**
	 * 获取战败客户记录动态列
	 * */
	
	public List getLosedCustomerColumn(PotentialcustomerAssayVo potentialcustomerAssayVo)throws Exception{
		List list = new ArrayList();
		String str = getLosedCustomerRecord(potentialcustomerAssayVo);
		if(!(str.equals(""))){
			String[] strcolumn = str.substring(1).split(",");
			for (int i = 0; i < strcolumn.length; i++) {
				if(strcolumn[i]!=null && !strcolumn[i].equals("")){
					list.add(strcolumn[i]);
				}
			}
			
		}
		return list;	
	}
	
	// 获取战败客户记录
	
	public String getLosedCustomerRecord(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		Json json = new Json();
		List list = new ArrayList();
		String jsonstr = "";
		List rlist = null;
		Object[] obj = null;
		List tempList = new ArrayList();
		StringBuffer sb = new StringBuffer("{\"rows\":[{");
		StringBuffer strbf = new StringBuffer(
				"	SELECT datas.datavalue , ctm.xs_custom_name,ctm.xs_contacts_phone,ctm.xs_custom_telephone,ctm.xs_custom_address," +
				"	btf.STF_NAME, ctm.xs_custom_inputdata, del.lose_date, md.xs_model_name" +
				"	FROM xs_sell_custom_salesman_detail del" +
				"	JOIN  xs_sell_receiveRelation xr ON del.xs_custom_salesman_detail_id = xr.xs_custom_salesman_detail_id" +
				"	JOIN xs_custom_info ctm ON ctm.custom_id = xr.custom_id" +
				"	JOIN xs_car_model md ON md.xs_model_id = del.car_model" +
				"	JOIN bas_stuff btf ON btf.stf_id = ctm.STF_ID" +
				"	JOIN(" +
				"	SELECT child.child_id, child.dataValue FROM xs_childdictionary child JOIN xs_parentdictionary parent ON child.parent_id = parent.parent_id WHERE parent.dataKey='"+Contstants.BASE_SELL.ABANDON_REASON+"'" );
		strbf.append(" and child.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id() +
				" ) AS datas  ON del.register_state = datas.child_id " +
				"	WHERE 1 = 1 " +
				" 	");
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and ctm.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		

		if (potentialcustomerAssayVo.getXsCustomInputdate() != null && !potentialcustomerAssayVo.getXsCustomInputdate().equals("")) {
			strbf.append(" and DATE(ctm.xs_custom_inputdata) >= '"
					+ potentialcustomerAssayVo.getXsCustomInputdate() + "'");
		} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null && !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")) {
			strbf.append(" and DATE(ctm.xs_custom_inputdata) <= '"
					+ potentialcustomerAssayVo.getXsCustomInputdate2() + "'");
		} 
		if((potentialcustomerAssayVo.getXsCustomInputdate() == null
				||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
				(potentialcustomerAssayVo.getXsCustomInputdate2() == null
						||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
			strbf.append(" and DATE( ctm.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
							FormatTime.DEFAULT_FORMAT2,(
									basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
											Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
											+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if (potentialcustomerAssayVo.getLoseDate() != null && !potentialcustomerAssayVo.getLoseDate().equals("")) {
			strbf.append(" and DATE(del.lose_date) >= '"
					+ potentialcustomerAssayVo.getLoseDate() + "'");
		} 
		if (potentialcustomerAssayVo.getLoseDate2() != null && !potentialcustomerAssayVo.getLoseDate2().equals("")) {
			strbf.append(" and DATE(del.lose_date) <= '"
					+ potentialcustomerAssayVo.getLoseDate2() + "'");
		} 

		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")) {
			strbf.append(" AND ctm.stf_id="
					+ potentialcustomerAssayVo.getStfId() + " ");
		}
		rlist = potentialcustomerAssayDao.createSQLQuery(strbf.toString());
		
		//如果是初始化返回查询结果字符串
		if(potentialcustomerAssayVo.getFlag()!=null && potentialcustomerAssayVo.getFlag()==true){
			String str = "";
			if(rlist!=null && rlist.size()>0){
				Object[] objt = null;
				List temp=new ArrayList();
				for (int i = 0; i < rlist.size(); i++) {
					objt = (Object[])rlist.get(i);
					if(!temp.contains(objt[0])){
						str += ","+objt[0];
						temp.add(temp.add(objt[0]));
					}
				}
				str.substring(1);
			}
			return str;
		}
		Object[] objs = null;
		if(rlist!=null && rlist.size()>0){
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				List temp=new ArrayList();
				for (int j = 0; j < rlist.size(); j++) {
					objs = (Object[])rlist.get(j);
					if(obj[0]!=null && obj[0].equals(objs[0])){
							if(!temp.contains(obj[0].toString())){
								sb.append("\"" +objs[0].toString().trim().replaceAll(" ", "")+ "\":\" √ \",");
								temp.add(obj[0].toString());
							}
						}
					}
				if(obj[1]!=null && !obj[1].equals("")){
					sb.append("\"xsCustomName\":\""	+obj[1]+ "\",");
				}
				if(obj[2]!=null && !obj[2].equals("")){
					sb.append("\"xsContactsPhone\":\""+obj[2]+ "\",");
				}
				if(obj[3]!=null && !obj[3].equals("")){
					sb.append("\"xsCustomTelephone\":\""+obj[3]+ "\",");
				}
				if(obj[4]!=null && !obj[4].equals("")){
					sb.append("\"xsCustomAddress\":\""+obj[4]+ "\",");
				}
				if(obj[5]!=null && !obj[5].equals("")){
					sb.append("\"stfName\":\""+obj[5]+ "\",");
				}
				if(obj[6]!=null && !obj[6].equals("")){
					sb.append("\"xsCustomInputdate\":\""+obj[6]+ "\",");
				}
				if(obj[7]!=null && !obj[7].equals("")){
					sb.append("\"loseDate\":\""+obj[7]+ "\",");
				}
				if(obj[8]!=null && !obj[8].equals("")){
					sb.append("\"carModel\":\""+obj[8]+ "\"");
				}
				sb.append("},{");
			}
		}
		String s1 =null;
		if(sb.length()>10){
			s1=sb.substring(0, sb.length() - 2);
		}else{
			s1=sb.substring(0, sb.length() - 1);
		}
		sb = new StringBuffer(s1);
		sb.append("],\"total\":"
				+ potentialcustomerAssayDao.getCountBySQL(strbf.toString())
				+ "}");
		String source = sb.toString();
		return source;
	}
	

	/**
	 * 战败原因分析 list ： 返回分析信息的集合
	 */
	
	public List getLoseBellAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		StringBuffer strbf = new StringBuffer(
				""
						+ "	SELECT"
						+ "	(SELECT ch.datavalue FROM xs_childdictionary ch WHERE ch.child_id = del.register_state )AS losereason,"
						+ "	COUNT(del.xs_custom_salesman_detail_id) AS counts,"
						+ "	custom.xs_custom_inputdata,"
						+ "	custom.stf_id,"
						+ "	del.lose_date,custom.enterprise_id"
						+ " FROM  xs_sell_custom_salesman_detail del"
						+ "	JOIN xs_sell_receiverelation re ON del.xs_custom_salesman_detail_id = re.xs_custom_salesman_detail_id"
						+ "	JOIN xs_custom_info custom ON re.custom_id = custom.custom_id"
						+ "	WHERE custom.xs_custom_deal="
						+ "	("
						+ "	SELECT child.child_id FROM xs_childdictionary child,xs_parentdictionary parent " +
								"WHERE child.parent_id = parent.parent_id " +
								"AND child.dataKey='"+Contstants.BASE_SELL.DS+"' AND parent.dataKey='"+Contstants.BASE_SELL.BASE_DEALSTATE+"'"
						+ " and child.enterprise_id="+potentialcustomerAssayVo.getEnterprise_id()
						+ "	) ");
		
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and custom.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		if (potentialcustomerAssayVo.getXsCustomInputdate() != null && !potentialcustomerAssayVo.getXsCustomInputdate().equals("")) {
			strbf.append(" and DATE(custom.xs_custom_inputdata) >= '"
					+ potentialcustomerAssayVo.getXsCustomInputdate() + "'");
		} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null && !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")) {
			strbf.append(" and DATE(custom.xs_custom_inputdata) <= '"
					+ potentialcustomerAssayVo.getXsCustomInputdate2() + "'");
		} 
		if((potentialcustomerAssayVo.getXsCustomInputdate() == null
				||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
				(potentialcustomerAssayVo.getXsCustomInputdate2() == null
						||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
			strbf.append(" and DATE( custom.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
							FormatTime.DEFAULT_FORMAT2,(
									basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
											Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
											+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if (potentialcustomerAssayVo.getLoseDate() != null && !potentialcustomerAssayVo.getLoseDate().equals("")) {
			strbf.append(" and DATE(del.lose_date) >= '"
					+ potentialcustomerAssayVo.getLoseDate() + "'");
		} 
		if (potentialcustomerAssayVo.getLoseDate2() != null && !potentialcustomerAssayVo.getLoseDate2().equals("")) {
			strbf.append(" and DATE(del.lose_date) <= '"
					+ potentialcustomerAssayVo.getLoseDate2() + "'");
		}
		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and custom.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
		
		strbf.append(" group by del.register_state ");
		List rlist = potentialcustomerAssayDao.createSQLQuery(strbf.toString());
		StringBuffer select2 = new StringBuffer(
				" SELECT SUM(R.counts) AS sums from ( ");
		// 获取总记录
		List sum = potentialcustomerAssayDao.createSQLQuery(select2.toString() + strbf.toString() + ") as R");

		// 总记录
		int sums = sum != null && sum.get(0) != null && !sum.get(0).equals("") ? Integer
				.parseInt(sum.get(0).toString())
				: 0;
		List list = new ArrayList();
		Object[] obj = null;
		if (rlist != null && rlist.size() > 0) {
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("0")) {
					vo.setLoseBellReason(obj[0].toString());
				}else{
					vo.setLoseBellReason("其他");
				}
				vo.setAmount(obj[1] != null ? obj[1].toString() : "");
				if (obj[1] != null && sums > 0) {
					int isums = Integer.parseInt(obj[1].toString()) * 100
							/ sums;
					vo.setPercentage(isums + "");
				}
				list.add(vo);
			}
		}
		return list;
	}

	/**
	 * 返回战败原因分析饼图
	 */
	
	public PieDataset createPieDatasetOfLoseBell(List list) throws Exception {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				if (vo.getLoseBellReason() != null
						&& !vo.getLoseBellReason().equals("")) {
					defaultpiedataset.setValue(
							"" + vo.getLoseBellReason() != null ? vo
									.getLoseBellReason().toString() : "" + "",
							vo.getAmount() != null ? Integer.parseInt(vo
									.getAmount().toString()) : 0);
				}
			}
		}
		return defaultpiedataset;
	}

	/**
	 * 战败车型分析
	 * */
	
	public List getLoseBellCarModelAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		StringBuffer strbf = new StringBuffer(
				""
						+ "	SELECT	model.xs_model_name,"
						+ "	COUNT(del.xs_custom_salesman_detail_id) AS counts,"
						+ "	custom.xs_custom_inputdata,"
						+ "	custom.stf_id,"
						+ "	del.lose_date"
						+ "	FROM  xs_sell_custom_salesman_detail del"
						+ "	JOIN xs_sell_receiverelation re ON del.xs_custom_salesman_detail_id = re.xs_custom_salesman_detail_id"
						+ "	JOIN xs_custom_info custom ON re.custom_id = custom.custom_id"
						+ "	LEFT JOIN xs_car_model model ON del.car_model = model.xs_model_id"
						+ "	WHERE custom.xs_custom_deal=	("
						+ "	SELECT child.child_id FROM xs_childdictionary child,xs_parentdictionary parent " +
								"WHERE child.parent_id = parent.parent_id " +
								" AND child.dataKey='"+Contstants.BASE_SELL.DS+"' AND parent.dataKey='"+Contstants.BASE_SELL.BASE_DEALSTATE+"'"
						+" and child.enterprise_id="+potentialcustomerAssayVo.getEnterprise_id()
						+ "	)  ");
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and custom.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		if (potentialcustomerAssayVo.getXsCustomInputdate() != null
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
						"undefined")) {
			
				strbf.append(" and DATE( custom.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
			} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
							"undefined")) {
				
				strbf.append(" and DATE( custom.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
			}
		
	if((potentialcustomerAssayVo.getXsCustomInputdate() == null
			||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
			(potentialcustomerAssayVo.getXsCustomInputdate2() == null
					||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
		strbf.append(" and DATE( custom.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
						FormatTime.DEFAULT_FORMAT2,(
								basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
										Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
										+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}
	if (potentialcustomerAssayVo.getLoseDate() != null && !potentialcustomerAssayVo.getLoseDate().equals("")) {
		strbf.append(" and DATE(del.lose_date) >= '"
				+ potentialcustomerAssayVo.getLoseDate() + "'");
	} 
	if (potentialcustomerAssayVo.getLoseDate2() != null && !potentialcustomerAssayVo.getLoseDate2().equals("")) {
		strbf.append(" and DATE(	del.lose_date) <= '"
				+ potentialcustomerAssayVo.getLoseDate2() + "'");
	}
		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and custom.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
		
		strbf.append(" group by model.xs_model_name");
		List rlist = potentialcustomerAssayDao.createSQLQuery(strbf.toString());
		StringBuffer select2 = new StringBuffer(
				" SELECT SUM(R.counts) AS sums from ( ");

		// 获取总记录
		List sum = potentialcustomerAssayDao.createSQLQuery(select2.toString() + strbf.toString() + ") as R");

		// 总记录
		int sums = sum != null && sum.get(0) != null && !sum.get(0).equals("") ? Integer
				.parseInt(sum.get(0).toString())
				: 0;
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("0")) {
					vo.setCarModel(obj[0].toString());
				}
				vo.setAmount(obj[1] != null && !obj[1].equals("0") ? obj[1]
						.toString() : "");
				if (obj[1] != null && sums > 0) {
					int isums = Integer.parseInt(obj[1].toString()) * 100
							/ sums;
					vo.setPercentage(isums + "");
				}
				list.add(vo);
			}
		}
		return list;
	}

	/**
	 *战败车型 分析图
	 */
	
	public PieDataset createPieDatasetOfLoseBellCarModel(List list)
			throws Exception {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				if (vo.getCarModel() != null && !vo.getCarModel().equals("")) {
					defaultpiedataset.setValue(
							"" + vo.getCarModel() != null ? vo.getCarModel()
									.toString() : "" + "",
							vo.getAmount() != null ? Integer.parseInt(vo
									.getAmount().toString()) : 0);
				}
			}
		}
		return defaultpiedataset;
	}

	/**
	 * 战败部门分析
	 * */
	
	public List getLoseBellDepartAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		StringBuffer strbf = new StringBuffer(
				""
						+ "	SELECT	dp.DEPT_NAME,"
						+ "	COUNT(del.xs_custom_salesman_detail_id) AS counts,"
						+ "	custom.xs_custom_inputdata,"
						+ "	custom.stf_id,"
						+ "	del.lose_date"
						+ "	FROM  xs_sell_custom_salesman_detail del"
						+ "	JOIN xs_sell_receiverelation re ON del.xs_custom_salesman_detail_id = re.xs_custom_salesman_detail_id"
						+ "	JOIN xs_custom_info custom ON re.custom_id = custom.custom_id"
						+ "	 JOIN bas_stuff stf ON custom.STF_ID = stf.STF_ID"
						+ "	LEFT JOIN bas_dept dp ON stf.DEPT_ID = dp.DEPT_ID"
						+ "	WHERE custom.xs_custom_deal=	("
						+ "	SELECT child.child_id FROM xs_childdictionary child," +
								"xs_parentdictionary parent WHERE child.parent_id = parent.parent_id " +
								" AND child.dataKey='"+Contstants.BASE_SELL.DS+"' AND parent.dataKey='"+Contstants.BASE_SELL.BASE_DEALSTATE+"'"
						+" and child.enterprise_id="+potentialcustomerAssayVo.getEnterprise_id()
						+ "	)  ");
		
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and custom.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		
		if (potentialcustomerAssayVo.getXsCustomInputdate() != null
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
						"undefined")) {
			
				strbf.append(" and DATE( custom.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
			} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
							"undefined")) {
				
				strbf.append(" and DATE( custom.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
			}
		
	if((potentialcustomerAssayVo.getXsCustomInputdate() == null
			||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
			(potentialcustomerAssayVo.getXsCustomInputdate2() == null
					||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
		strbf.append(" and DATE( custom.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
						FormatTime.DEFAULT_FORMAT2,(
								basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
										Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
										+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}
	if (potentialcustomerAssayVo.getLoseDate() != null && !potentialcustomerAssayVo.getLoseDate().equals("")) {
		strbf.append(" and DATE(del.lose_date) >= '"
				+ potentialcustomerAssayVo.getLoseDate() + "'");
	} 
	if (potentialcustomerAssayVo.getLoseDate2() != null && !potentialcustomerAssayVo.getLoseDate2().equals("")) {
		strbf.append(" and DATE(del.lose_date) <= '"
				+ potentialcustomerAssayVo.getLoseDate2() + "'");
	}
		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and custom.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
		strbf.append(" group by  stf.DEPT_ID");
		List rlist = potentialcustomerAssayDao.createSQLQuery(strbf.toString());
		StringBuffer select2 = new StringBuffer(
				" SELECT SUM(R.counts) AS sums from ( ");

		// 获取总记录
		List sum = potentialcustomerAssayDao.createSQLQuery(select2.toString() + strbf.toString() + ") as R");

		// 总记录
		int sums = sum != null && sum.get(0) != null && !sum.get(0).equals("") ? Integer
				.parseInt(sum.get(0).toString())
				: 0;
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("0")) {
					vo.setDeptName(obj[0].toString());
				}
				vo.setAmount(obj[1] != null && !obj[1].equals("0") ? obj[1]
						.toString() : "");
				if (obj[1] != null && sums > 0) {
					int isums = Integer.parseInt(obj[1].toString()) * 100
							/ sums;
					vo.setPercentage(isums + "");
				}
				list.add(vo);
			}
		}
		return list;
	}

	/**
	 * 战败部门分析图形报表
	 * */
	
	public PieDataset createPieDatasetOfLoseBellDepart(List list)
			throws Exception {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				if (vo.getDeptName() != null && !vo.getDeptName().equals("")) {
					defaultpiedataset.setValue(
							"" + vo.getDeptName() != null ? vo.getDeptName()
									.toString() : "" + "",
							vo.getAmount() != null ? Integer.parseInt(vo
									.getAmount().toString()) : 0);
				}
			}
		}
		return defaultpiedataset;
	}

	/**
	 * 战败班组分析
	 * */
	
	public List getLoseBellGroupAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		StringBuffer strbf = new StringBuffer(
				""
						+ "	SELECT	(select child2.dataValue from xs_childdictionary child2 where child2.child_id = stf.REPGRP_ID2 ) AS gruopname,"
						+ "	COUNT(del.xs_custom_salesman_detail_id) AS counts,"
						+ "	custom.xs_custom_inputdata,"
						+ "	custom.stf_id,"
						+ "	del.lose_date"
						+ "	FROM  xs_sell_custom_salesman_detail del"
						+ "	JOIN xs_sell_receiverelation re ON del.xs_custom_salesman_detail_id = re.xs_custom_salesman_detail_id"
						+ "	JOIN xs_custom_info custom ON re.custom_id = custom.custom_id"
						+ "	LEFT JOIN bas_stuff stf ON custom.STF_ID = stf.STF_ID"
						+ "	WHERE custom.xs_custom_deal=("
						+ "	SELECT child.child_id FROM xs_childdictionary child,xs_parentdictionary parent WHERE " +
								"child.parent_id = parent.parent_id " +
								"AND child.dataKey='"+Contstants.BASE_SELL.DS+"' AND parent.dataKey='"+Contstants.BASE_SELL.BASE_DEALSTATE+"'"
						+" and child.enterprise_id="+potentialcustomerAssayVo.getEnterprise_id()
						+ "	)  ");
		
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and custom.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		if (potentialcustomerAssayVo.getXsCustomInputdate() != null
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
						"undefined")) {
			
				strbf.append(" and DATE( custom.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
			} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
							"undefined")) {
				
				strbf.append(" and DATE( custom.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
			}
		
	if((potentialcustomerAssayVo.getXsCustomInputdate() == null
			||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
			(potentialcustomerAssayVo.getXsCustomInputdate2() == null
					||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
		strbf.append(" and DATE( custom.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
						FormatTime.DEFAULT_FORMAT2,(
								basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
										Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
										+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}
	if (potentialcustomerAssayVo.getLoseDate() != null && !potentialcustomerAssayVo.getLoseDate().equals("")) {
		strbf.append(" and DATE(del.lose_date) >= '"
				+ potentialcustomerAssayVo.getLoseDate() + "'");
	} 
	if (potentialcustomerAssayVo.getLoseDate2() != null && !potentialcustomerAssayVo.getLoseDate2().equals("")) {
		strbf.append(" and DATE(del.lose_date) <= '"
				+ potentialcustomerAssayVo.getLoseDate2() + "'");
	}

		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and custom.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
	
		strbf.append(" GROUP BY stf.REPGRP_ID2 ");
		List rlist = potentialcustomerAssayDao.createSQLQuery(strbf.toString());
		
		StringBuffer select2 = new StringBuffer(
				" SELECT SUM(R.counts) AS sums from ( ");

		// 获取总记录
		List sum = potentialcustomerAssayDao.createSQLQuery(select2.toString() + strbf.toString() + ") as R");
		// 总记录
		int sums = sum != null && sum.get(0) != null && !sum.get(0).equals("") ? Integer
				.parseInt(sum.get(0).toString())
				: 0;
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("")) {
					vo.setSellTeam(obj[0].toString());
				}else{
					vo.setSellTeam("其他");
				}
				vo.setAmount(obj[1] != null && !obj[1].equals("0") ? obj[1]
						.toString() : "");
				if (obj[1] != null && sums > 0) {
					int isums = Integer.parseInt(obj[1].toString()) * 100
							/ sums;
					vo.setPercentage(isums + "");
				}
				list.add(vo);
			}
		}
		return list;
	}

	/**
	 * 战败班组分析图形报表
	 * */
	
	public PieDataset createPieDatasetOfLoseBellGroup(List list)
			throws Exception {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				if (vo.getSellTeam() != null && !vo.getSellTeam().equals("")) {
					defaultpiedataset.setValue(
							"" + vo.getSellTeam() != null ? vo.getSellTeam()
									.toString() : "" + "",
							vo.getAmount() != null ? Integer.parseInt(vo
									.getAmount().toString()) : 0);
				}
			}
		}
		return defaultpiedataset;
	}

	/**
	 * 战败业务分析
	 * */
	
	public List getLoseBellWorkAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception {
		StringBuffer strbf = new StringBuffer(
				""
						+ "	SELECT	stf.STF_NAME,"
						+ "	COUNT(del.xs_custom_salesman_detail_id) AS counts,"
						+ "	custom.xs_custom_inputdata,"
						+ "	custom.stf_id,"
						+ "	del.lose_date"
						+ "	FROM  xs_sell_custom_salesman_detail del"
						+ "	JOIN xs_sell_receiverelation re ON del.xs_custom_salesman_detail_id = re.xs_custom_salesman_detail_id"
						+ "	JOIN xs_custom_info custom ON re.custom_id = custom.custom_id"
						+ "	LEFT JOIN bas_stuff stf ON custom.STF_ID = stf.STF_ID"
						+ "	WHERE custom.xs_custom_deal=	("
						+ "	SELECT child.child_id FROM xs_childdictionary child,xs_parentdictionary parent WHERE " +
								"child.parent_id = parent.parent_id AND " +
								"child.dataKey='"+Contstants.BASE_SELL.DS+"' AND parent.dataKey='"+Contstants.BASE_SELL.BASE_DEALSTATE+"'"
						+  "  AND   child.enterprise_id="+potentialcustomerAssayVo.getEnterprise_id()+"	)  ");
		
		if(potentialcustomerAssayVo.getEnterprise_id()!=null&&!"".equals(potentialcustomerAssayVo.getEnterprise_id())){
			strbf.append(" and custom.enterprise_id ="+potentialcustomerAssayVo.getEnterprise_id());
		}
		if (potentialcustomerAssayVo.getXsCustomInputdate() != null
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals("")
				&& !potentialcustomerAssayVo.getXsCustomInputdate().equals(
						"undefined")) {
			
				strbf.append(" and DATE( custom.xs_custom_inputdata) >= '" + potentialcustomerAssayVo.getXsCustomInputdate() + "'");
			} 
		if (potentialcustomerAssayVo.getXsCustomInputdate2() != null
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals("")
					&& !potentialcustomerAssayVo.getXsCustomInputdate2().equals(
							"undefined")) {
				
				strbf.append(" and DATE( custom.xs_custom_inputdata) <= '"+potentialcustomerAssayVo.getXsCustomInputdate2()+ "'");
			}
		
	if((potentialcustomerAssayVo.getXsCustomInputdate() == null
			||potentialcustomerAssayVo.getXsCustomInputdate().equals(""))&&
			(potentialcustomerAssayVo.getXsCustomInputdate2() == null
					||potentialcustomerAssayVo.getXsCustomInputdate2().equals(""))){
		strbf.append(" and DATE( custom.xs_custom_inputdata)between '"+ FormatTime.yesTempTady(
						FormatTime.DEFAULT_FORMAT2,(
								basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
										Contstants.COLLIGATES.DEFAULTSHOWDAY,potentialcustomerAssayVo.getEnterprise_id()).getCiValue()))
										+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}
	if (potentialcustomerAssayVo.getLoseDate() != null && !potentialcustomerAssayVo.getLoseDate().equals("")) {
		strbf.append(" and DATE(del.lose_date) >= '"
				+ potentialcustomerAssayVo.getLoseDate() + "'");
	} 
	if (potentialcustomerAssayVo.getLoseDate2() != null && !potentialcustomerAssayVo.getLoseDate2().equals("")) {
		strbf.append(" and DATE(del.lose_date) <= '"
				+ potentialcustomerAssayVo.getLoseDate2() + "'");
	}
		if (potentialcustomerAssayVo.getStfId() != null
				&& !potentialcustomerAssayVo.getStfId().equals("")
				&& !potentialcustomerAssayVo.getStfId().equals("undefined")) {
			strbf.append(" and custom.stf_id ="
					+ potentialcustomerAssayVo.getStfId() + "");
		}
		strbf.append(" group by stf.STF_NAME");
		List rlist = potentialcustomerAssayDao.createSQLQuery(strbf.toString());
		StringBuffer select2 = new StringBuffer(
				" SELECT SUM(R.counts) AS sums from ( ");

		// 获取总记录
		List sum = potentialcustomerAssayDao.createSQLQuery(select2.toString() + strbf.toString() + ") as R");

		// 总记录
		int sums = sum != null && sum.get(0) != null && !sum.get(0).equals("") ? Integer
				.parseInt(sum.get(0).toString())
				: 0;
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				PotentialcustomerAssayVo vo = new PotentialcustomerAssayVo();
				if (obj[0] != null && !obj[0].toString().equals("0")) {
					vo.setStfName(obj[0].toString());
				}
				vo.setAmount(obj[1] != null && !obj[1].equals("0") ? obj[1]
						.toString() : "");
				if (obj[1] != null && sums > 0) {
					int isums = Integer.parseInt(obj[1].toString()) * 100
							/ sums;
					vo.setPercentage(isums + "");
				}
				list.add(vo);
			}
		}
		return list;
	}

	/**
	 * 战败业务分析图形报表
	 * */
	
	public PieDataset createPieDatasetOfLoseBellWork(List list)
			throws Exception {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		if (list != null && list.size() > 0) {
			PotentialcustomerAssayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				vo = (PotentialcustomerAssayVo) list.get(i);
				if (vo.getStfName() != null && !vo.getStfName().equals("")) {
					defaultpiedataset.setValue(
							"" + vo.getStfName() != null ? vo.getStfName()
									.toString() : "" + "",
							vo.getAmount() != null ? Integer.parseInt(vo
									.getAmount().toString()) : 0);
				}
			}
		}
		return defaultpiedataset;
	}

}
