package com.syuesoft.sell.synthesis_assay.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.synthesis_assay.dao.PurchaseandsaleDailysheetDao;
import com.syuesoft.sell.synthesis_assay.service.PurchaseandsaleDailysheetService;
import com.syuesoft.sell.synthesis_assay.vo.PurchaseandsaleDailysheetVo;

@Service("purchaseandsaleDailysheetService")
public class PurchaseandsaleDailysheetServiceImpl extends BaseLogServiceImpl implements
PurchaseandsaleDailysheetService {

	@Resource
	private PurchaseandsaleDailysheetDao purchaseandsaleDailysheetDao;

	/**
	 * 获取车辆进销存报表详细信息(获取品牌及库存量的统计记录)
	 */
	
	public Datagrid getPurchaseandsaleDailysheet(
			PurchaseandsaleDailysheetVo purchaseandsaleDailysheetVo)
			throws Exception {
		List list = null;
		List retlist = new ArrayList();
		List footer = new ArrayList();
		if (purchaseandsaleDailysheetVo.getRecordDate() != null
				&& !purchaseandsaleDailysheetVo.getRecordDate().equals("")) {
			String[] date = purchaseandsaleDailysheetVo.getRecordDate().split(
					",");
			// 条件
			String conditonstr = "";
			if (purchaseandsaleDailysheetVo.getCarBrand() != null
					&& !purchaseandsaleDailysheetVo.getCarBrand().equals("")) {
				conditonstr += " and car.xs_car_brand="
						+ purchaseandsaleDailysheetVo.getCarBrand() + "";
			}
			if (purchaseandsaleDailysheetVo.getCarModel() != null
					&& !purchaseandsaleDailysheetVo.getCarModel().equals("")) {
				conditonstr += " and car.xs_car_model_id="
						+ purchaseandsaleDailysheetVo.getCarModel() + "";
			}
			if (purchaseandsaleDailysheetVo.getEnterprise_id() != null
					&& !purchaseandsaleDailysheetVo.getEnterprise_id().equals("")) {
				conditonstr += " and car.enterprise_id="
						+ purchaseandsaleDailysheetVo.getEnterprise_id() + "";
			}
			if (conditonstr.toString().equals("")) {
				conditonstr = "''";
			}

			list = purchaseandsaleDailysheetDao
					.createSQLQuery("{CALL sell_dayrecord_carbrand('" + date[0]
							+ "','" + date[1] + "','"
							+ conditonstr + "',"+purchaseandsaleDailysheetVo.getEnterprise_id()+")}");

			if (list != null && list.size() > 0) {
				Object[] obj = null;
				for (int i = 0; i < list.size(); i++) {
					obj = (Object[]) list.get(i);
					PurchaseandsaleDailysheetVo vo = new PurchaseandsaleDailysheetVo();
					
					// 月结库存时间
					String monthdate = purchaseandsaleDailysheetDao
							.createSQLQuery(
									"SELECT MAX(DATE(census_enddate))  FROM  xs_sell_census_collect " +
									"WHERE enterprise_id="+purchaseandsaleDailysheetVo.getEnterprise_id()+"" +
									" and DATE(census_enddate)<='"
											+ date[0] + "'").get(0)
							+ "";
					// 根据品牌筛选
					String cdtionbybrand = " AND cc.xs_car_brand=" + obj[5]
							+ "";
					String stockamount = "";
					String monthnumstr =  "";
					int thisnum = 0;
					int monthnum = 0;
					int topMonthNum = 0;
					// 判断是否有月结时间
					if (!monthdate.equals("null") && !monthdate.equals("")) {
						// 月节点到给定时间初的库存量
						String stock = "CALL sell_stock_amount('" + monthdate
												+ "','" + date[0]
												+ "','"
												+ cdtionbybrand + "')";
						
						stockamount = purchaseandsaleDailysheetDao
								.createSQLQuery(stock).get(0)
								+ "";
						
						//获取月结库存
					
						monthnumstr = purchaseandsaleDailysheetDao
						.createSQLQuery(
								"	SELECT COUNT(*) FROM  xs_sell_census A" +
								"	JOIN xs_sell_census_collect st ON A.collect_id = st.collect_id " +
								"	JOIN xs_car_info B ON A.xs_car_id = B.XS_CAR_ID " +
								"	WHERE st.enterprise_id="+purchaseandsaleDailysheetVo.getEnterprise_id()+"" +
								" and B.xs_car_brand="+obj[5]+" " +
								"	AND DATE(st.census_enddate)<='"
								+ monthdate + "'").get(0)+"";
						// 月节点到给定时间的库存量
						thisnum = stockamount != null
								&& !stockamount.equals("") ? Integer
								.parseInt(stockamount.toString()) : 0;
						// 月结库存
						monthnum = monthnumstr != null && !monthnumstr.equals("") ? Integer.parseInt(monthnumstr
								.toString()) : 0;
						// 月结库存加给定时间段库存
						topMonthNum = monthnum + thisnum;
					}

					String carBrandName = obj[0] != null ? obj[0].toString() 
							: "";
					// 给定时间段的库存量(本期入库)
					String thisImput = obj[1] != null ? obj[1].toString() 
							: "";
					String thisOutput = obj[2] != null ? obj[2].toString()
							: "";
					String thisExitCar = obj[3] != null ? obj[3].toString()
							: "";
					String thisExitPart = obj[4] != null ? obj[4].toString()
							: "";
					String carBrand = obj[5] != null ? obj[5].toString() 
							: "";

//					if (thisImput != null && !thisImput.equals("")
//							&& !thisImput.equals("0") || thisOutput != null
//							&& !thisOutput.equals("")
//							&& !thisOutput.equals("0") || thisExitCar != null
//							&& !thisExitCar.equals("")
//							&& !thisExitCar.equals("0") || thisExitPart != null
//							&& !thisExitPart.equals("")
//							&& !thisExitPart.equals("0")

//					) {
						vo.setCarBrand(carBrand);
						vo.setCarBrandName(carBrandName);
						vo.setTopMonthNum((int)topMonthNum+"");
						vo.setThisImput((int)Double.parseDouble(thisImput)+"");
						vo.setThisOutImput((int)Double.parseDouble(thisOutput)+"");
						vo.setThisExitCar((int)Double.parseDouble(thisExitCar)+"");
						vo.setThisExitPart((int)Double.parseDouble(thisExitPart)+"");
						vo.setSurplus(topMonthNum + (int)Double.parseDouble(thisImput) - (int)Double.parseDouble(thisOutput) + (int)Double.parseDouble(thisExitCar) - (int)Double.parseDouble(thisExitPart) +"");
						vo.setState("closed");
						vo.setIconCls("icon-blank");
						retlist.add(vo);
//					}
				}
				
				// 汇总
				List<PurchaseandsaleDailysheetVo> volist = retlist;
				int store = 0; // 月初库存
				int input = 0; // 本期入库
				int out = 0; // 本期出库
				int exetcar = 0; // 本期退车
				int exitpart = 0; // 本期退厂
				int surplus = 0; // 结余
				PurchaseandsaleDailysheetVo vo2 = new PurchaseandsaleDailysheetVo();
				for (PurchaseandsaleDailysheetVo pvo : volist) {
					store += Integer.parseInt(pvo.getTopMonthNum());
					input += (int)Double.parseDouble(pvo.getThisImput());
					out += (int)Double.parseDouble(pvo.getThisOutImput());
					exetcar += (int)Double.parseDouble(pvo.getThisExitCar());
					exitpart += (int)Double.parseDouble(pvo.getThisExitPart());
					surplus += (int)Double.parseDouble(pvo.getSurplus());
				}
				vo2.setCarBrandName("汇总");
				vo2.setTopMonthNum(store + "");
				vo2.setThisImput(input + "");
				vo2.setThisOutImput(out + "");
				vo2.setThisExitCar(exetcar + "");
				vo2.setThisExitPart(exitpart + "");
				vo2.setSurplus(surplus + "");
				vo2.setState("open");
				vo2.setIconCls("icon-blank");
				footer.add(vo2);
			}
		}
		Datagrid dgd = new Datagrid();
		dgd.setFooter(footer);
		dgd.setRows(retlist);
		dgd.setTotal(Integer.MAX_VALUE);
		return dgd;
	}

	/**
	 * 根据品牌编号获取型号信息
	 */
	
	public List getDailysheetChild(
			PurchaseandsaleDailysheetVo purchaseandsaleDailysheetVo)
			throws Exception {
		List list = null;
		List retlist = new ArrayList();
		if (purchaseandsaleDailysheetVo.getCarBrand() != null
				&& !purchaseandsaleDailysheetVo.getCarBrand().equals("")) {
			String[] date = null;
			if (purchaseandsaleDailysheetVo.getRecordDate() != null
					&& !purchaseandsaleDailysheetVo.getRecordDate().equals("")) {
				date = purchaseandsaleDailysheetVo.getRecordDate().split(",");
			}
			// 条件
			String conditonstr = " ";
			String modelconditons = " ";
			conditonstr += " and model.xs_car_brand="
					+ purchaseandsaleDailysheetVo.getCarBrand() + "";
			if (purchaseandsaleDailysheetVo.getCarModel() != null
					&& !purchaseandsaleDailysheetVo.getCarModel().equals("")) {
				modelconditons += " and model.xs_model_id="
					+ purchaseandsaleDailysheetVo.getCarModel() + "";
			}
			if (conditonstr.toString().equals("")) {
				conditonstr = "";
				modelconditons = "";
			}
			String calsql = "{CALL sell_dayrecord_carmodel('" + date[0]
							+ "','" + date[1] + "','"
							+ modelconditons + "','"+conditonstr+"' )}";
			
			list = purchaseandsaleDailysheetDao
					.createSQLQuery(calsql);
			if (list != null && list.size() > 0) {
				Object[] obj = null;
				for (int i = 0; i < list.size(); i++) {
					obj = (Object[]) list.get(i);
					PurchaseandsaleDailysheetVo vo = new PurchaseandsaleDailysheetVo();
					String carmodelid = obj[1] != null ? obj[1].toString() : "";
					String carmodelname = obj[2] != null ? obj[2].toString()
							: "";
					// 月结库存时间
					String monthdate = purchaseandsaleDailysheetDao
							.createSQLQuery(
									" SELECT MAX(DATE(census_enddate))  FROM  xs_sell_census_collect " +
									" WHERE enterprise_id="+purchaseandsaleDailysheetVo.getEnterprise_id()+"" +
									" and DATE(census_enddate)<='"
											+ date[0] + "'").get(0)
							+ "";
					String stockamount = "";
					String monthnumstr = "";
					int thisnum = 0;
					int monthnum = 0;
					int topMonthNum = 0;
					// 判断是否有月结时间
					if (!monthdate.equals("null") && !monthdate.equals("")) {
						String cdtionbymodelid = " AND DD.xs_model_id=" + obj[1] + "";
						// 月节点到给定时间的库存量
						String smount = "{CALL sell_stock_amount('" + monthdate +"','" + date[0]+ "','"+ cdtionbymodelid + "')}";
						stockamount = purchaseandsaleDailysheetDao.createSQLQuery(smount).get(0).toString();
						//获取月结库存
						monthnumstr = purchaseandsaleDailysheetDao
						.createSQLQuery(
								"	SELECT COUNT(*) FROM  xs_sell_census A" +
								"	JOIN xs_sell_instorehouse_del del ON A.xs_car_id = del.XS_CAR_ID " +
								"	JOIN xs_sell_census_collect st ON A.collect_id = st.collect_id " +
								"	JOIN xs_car_info B ON del.xs_car_id = B.XS_CAR_ID " +
								"	WHERE B.xs_car_model_id="+obj[1]+" " +
								"	AND B.xs_car_brand="+purchaseandsaleDailysheetVo.getCarBrand()+"" +
								"	AND DATE(st.census_enddate)<='"
								+ monthdate + "'").get(0)+"";
						
						thisnum = stockamount != null
						&& !stockamount.equals("") ? Integer
						.parseInt(stockamount.toString()) : 0;
						// 月结库存
						monthnum = monthnumstr != null && !monthnumstr.equals("") ? Integer.parseInt(monthnumstr
						.toString()) : 0;
						// 月结库存加最后月结日期到给定时间段库存
						topMonthNum = monthnum + thisnum;
					}
					
					// 当前库存 本期入库
					String thisImput = obj[3] != null ? obj[3]
							.toString() : "";
					String thisOutput = obj[4] != null ? obj[4].toString()
							: "";
					String thisExitCar = obj[5] != null ? obj[5].toString()
							: "";
					String thisExitPart = obj[6] != null ? obj[6].toString()
							: "";

//					if (
//							thisImput != null && !thisImput.equals("")
//							&& !thisImput.equals("0") || thisOutImput != null
//							&& !thisOutImput.equals("")
//							&& !thisOutImput.equals("0") || thisExitCar != null
//							&& !thisExitCar.equals("")
//							&& !thisExitCar.equals("0") || thisExitPart != null
//							&& !thisExitPart.equals("")
//							&& !thisExitPart.equals("0")

//						) {
							//vo.setCarBrand(carBrand);
							vo.setCarBrandName(carmodelname);
							vo.setTopMonthNum(topMonthNum + "");
							vo.setThisImput(thisImput);
							vo.setThisOutImput(thisOutput);
							vo.setThisExitCar(thisExitCar);
							vo.setThisExitPart(thisExitPart);
							vo.setSurplus(topMonthNum + (int)Double.parseDouble(thisImput) - (int)Double.parseDouble(thisOutput) + (int)Double.parseDouble(thisExitCar) - (int)Double.parseDouble(thisExitPart) +"");
							vo.setState("open");
							vo.setIconCls("icon-blank");
							retlist.add(vo);
//						}

				}
			}
		}
		return retlist;
	}
	
	
}
