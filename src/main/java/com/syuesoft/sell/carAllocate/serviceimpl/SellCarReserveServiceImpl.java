package com.syuesoft.sell.carAllocate.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.carAllocate.dao.SellCarReserveDao;
import com.syuesoft.sell.carAllocate.service.SellCarReserveService;
import com.syuesoft.sell.carAllocate.vo.SellCarReserveVo;
import com.syuesoft.sell.control.dao.XsSellFlowControlDao;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsSellCarReserve;
import com.syuesoft.sell.model.XsSellFlowControl;
import com.syuesoft.util.FormatTime;

@Service("sellService")
public class SellCarReserveServiceImpl extends BaseLogServiceImpl implements SellCarReserveService {
	private SellCarReserveDao sellCarReserveDao;
	private BaseTagDAO baseTagDAO;
	private XsSellFlowControlDao xsSellFlowControlDao ;
	
	@Resource
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	

	//获取初始化时间
	private String edate = FormatTime.yesTady("yyyy-MM-dd");
	
	/*private static String sellState="客户预定";//165
	private static String sellState1="在库待销";//163
*/
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}
	public XsSellFlowControlDao getXsSellFlowControlDao() {
		return xsSellFlowControlDao;
	}
	@Autowired
	public void setXsSellFlowControlDao(XsSellFlowControlDao xsSellFlowControlDao) {
		this.xsSellFlowControlDao = xsSellFlowControlDao;
	}
	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}

	/**
	 * 
	 * 预订单清单的条件查询
	 */
	
	public Datagrid findSellCarReserve(SellCarReserveVo sellCarReserveVo)
			throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,Contstants.COLLIGATES.DEFAULTSHOWDAY,sellCarReserveVo.getEnterpriseId()).getCiValue()));

		Datagrid dg = new Datagrid();
		List<SellCarReserveVo> list_ = new ArrayList<SellCarReserveVo>();
		StringBuffer sql = new StringBuffer(
						"SELECT A.reserve_code,A.reserve_dete,A.vin_code,d.xs_model_salesPrice,"
						+ "A.first_pay_money,A.remark,B.STF_NAME,C.xs_custom_name, A.predict_take_date, D.xs_model_name," +
						" A.car_brand ,A.car_color,A.car_model,"
						+ "(SELECT xs_leva_name FROM xs_custom_leva cc WHERE cc.xs_leva_id= c.xs_custom_hide_level ) AS LEVEL1 ,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = A.car_color ) AS Cc, "
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = A.car_brand ) AS Cb," +
						" A.STF_ID, A.reserve_id ,A.custom_id,A.level,A.xs_car_id "
						+ "FROM  xs_sell_car_reserve A " +
						" join xs_sell_flow_control flow on flow.xsfcontrol_document=A.reserve_code and" +
						" flow.xsfcontrol_code='"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE4+"'" +
						" JOIN bas_stuff B ON A.STF_ID = B.STF_ID" +
						" JOIN xs_custom_info C ON A.custom_id = C.custom_id " +
						"  JOIN   xs_car_model D ON A.car_model = D.xs_model_id " +
						"WHERE  A.examine = (SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
						"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'" +
						"  AND child.enterprise_id="+sellCarReserveVo.getEnterpriseId()+") ");
		//186=已审核
			sql.append(" and A.enterprise_id=" + sellCarReserveVo.getEnterpriseId()+ "");		
		if (sellCarReserveVo.getReserveDete() != null
				&& !sellCarReserveVo.getReserveDete().equals("")) {
					sql.append( " and  DATE(A.Reserve_Dete) >= '"+sellCarReserveVo.getReserveDete()+"'" );
				} 
		if (sellCarReserveVo.getReserveDete2() != null
				&& !sellCarReserveVo.getReserveDete2().equals("")) {
					sql.append( " and  DATE(A.Reserve_Dete) <= '"+sellCarReserveVo.getReserveDete2()+"'" );
		}
		if((sellCarReserveVo.getReserveDete() == null
				|| sellCarReserveVo.getReserveDete().equals("")) 
				&& (sellCarReserveVo.getReserveDete2() == null
				|| sellCarReserveVo.getReserveDete2().equals(""))){
			sql.append(" and DATE(A.Reserve_Dete) BETWEEN '"+sdate+"' AND '"+edate+"'");
		}
		if (sellCarReserveVo.getCarBrand() != null
				&& !"".equals(sellCarReserveVo.getCarBrand())) {
			sql.append(" and A.car_brand='" + sellCarReserveVo.getCarBrand()
					+ "'");
		}
		if (sellCarReserveVo.getCarModel() != null
				&& !"".equals(sellCarReserveVo.getCarModel())) {
			sql.append(" and A.car_model='" + sellCarReserveVo.getCarModel()
					+ "'");
		}
		if (sellCarReserveVo.getStfId() != null
				&& !"".equals(sellCarReserveVo.getStfId())) {
			sql.append(" and B.STF_ID='" + sellCarReserveVo.getStfId() + "'");
		}

		if (sellCarReserveVo.getPredictTakeDate() != null
				&& !sellCarReserveVo.getPredictTakeDate().equals("")) {
					sql.append( " and  DATE(A.predict_take_date) >= '"+sellCarReserveVo.getPredictTakeDate()+"'" );
				} 
		if (sellCarReserveVo.getPredictTakeDate2() != null
				&& !sellCarReserveVo.getPredictTakeDate2().equals("")) {
					sql.append( " and  DATE(A.predict_take_date) <= '"+sellCarReserveVo.getPredictTakeDate2()+"'" );
		}
		if (sellCarReserveVo.getCustomName() != null
				&& !"".equals(sellCarReserveVo.getCustomName() )) {
			sql.append(" and C.xs_custom_name like '%"
					+ sellCarReserveVo.getCustomName()  + "%'");
		}
		sql.append(" order by A.reserve_code desc");
		List<Object[]> resultList = sellCarReserveDao.createSQLQuery(sql.toString(),sellCarReserveVo.getPage(),sellCarReserveVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellCarReserveVo wopq = new SellCarReserveVo();
				obj = resultList.get(i);
				wopq.setReserveCode(obj[0] != null ? obj[0].toString() : null);
				wopq.setReserveDete(obj[1] != null ? obj[1].toString() : null);
				wopq.setVinCode(obj[2] != null ? obj[2].toString() : null);
				wopq.setDecorateMoney(obj[3] != null ? Double
						.parseDouble(obj[3].toString()) : null);
				wopq.setFirstPayMoney(obj[4] != null ? Double
						.parseDouble(obj[4].toString()) : null);
				wopq.setRemark(obj[5] != null ? obj[5].toString() : null);
				wopq.setStfName(obj[6] != null ? obj[6].toString() : null);
				wopq.setCustomName(obj[7] != null ? obj[7].toString() : null);
				wopq.setPredictTakeDate(obj[8] != null ? obj[8].toString() : null);
				wopq.setCarModelName(obj[9] != null ? obj[9].toString() : null);
				wopq.setCarBrand(obj[10] != null ? Integer.parseInt(obj[10].toString()) : null);
				wopq.setCarColor(obj[11] != null ? Integer.parseInt(obj[11].toString()) : null);
				wopq.setCarModel(obj[12] != null ? Integer.parseInt(obj[12].toString()) : null);
				wopq.setLevelName(obj[13] != null ? obj[13].toString() : null);
				wopq.setCarColorName(obj[14] != null ? obj[14].toString(): null);
				wopq.setCarBrandName(obj[15] != null ? obj[15].toString(): null);
				wopq.setStfId(obj[16] != null ? Integer.parseInt( obj[16].toString()): null);
				wopq.setReserveId(obj[17] != null ? Integer.parseInt( obj[17].toString()): null);
				wopq.setCustomId(obj[18] != null ? Integer.parseInt( obj[18].toString()): null);
				wopq.setLevel(obj[19] != null ? Integer.parseInt( obj[19].toString()): null);
				wopq.setCarId(obj[20] != null ? Integer.parseInt( obj[20].toString()): null);
				list_.add(wopq);
			}
		}
		int total =sellCarReserveDao.getSQLCount(sql.toString(),null);
		dg.setRows(list_);
		dg.setTotal(total);
		return dg;
	
	}

	public SellCarReserveDao getSellCarReserveDao() {
		return sellCarReserveDao;
	}

	@Autowired
	public void setSellCarReserveDao(SellCarReserveDao sellCarReserveDao) {
		this.sellCarReserveDao = sellCarReserveDao;
	}

	/**
	 * 预定单交期提醒查询
	 */
	
	public Datagrid QueryCarReserveExpire(
			SellCarReserveVo sellCarReserveVo) throws Exception {

		return sellCarReserveDao.queryCarReserveExpire(sellCarReserveVo);
	}

	/**
	 * 
	 * 车辆预存信息修改
	 */
	public void updateSellCarReserve(SellCarReserveVo sellCarReserveVo)
			throws Exception {
		String hql = "from XsSellCarReserve xs where xs.reserve_id='"
				+ sellCarReserveVo.getReserveId() + "'";

		XsSellCarReserve xsSellCarReserve = (XsSellCarReserve) sellCarReserveDao.get(hql);
		xsSellCarReserve.setVinCode(sellCarReserveVo.getVinCode());
		
		
		sellCarReserveDao.update(xsSellCarReserve);
	}

	/**
	 * 
	 * 1=车辆分配 /2=取消车辆分配
	 */
	@Log(systemName="销售系统", moduleName="车辆调配作业",opertype="车辆分配")
	public void modifyReverd(SellCarReserveVo sellCarReserveVo)
			throws Exception {
		XsSellCarReserve carReserve =(XsSellCarReserve) sellCarReserveDao.get("from XsSellCarReserve where  reserveId="+ sellCarReserveVo.getReserveId());
		XsCarInfo car=(XsCarInfo)sellCarReserveDao.get("from XsCarInfo where carId="+sellCarReserveVo.getCarId());
		if (sellCarReserveVo.getVinNum() == 1) {
			carReserve.setXsCarId(sellCarReserveVo.getCarId());
			carReserve.setVinCode(sellCarReserveVo.getVinCode());
			carReserve.setCarBrand(sellCarReserveVo.getCarBrand());
			carReserve.setCarColor(sellCarReserveVo.getCarColor());
			carReserve.setCarModel(sellCarReserveVo.getCarModel());
			carReserve.setXsChilddictionary7(baseTagDAO.getChild(Contstants.BASE_SELL.ORDER_STATE, Contstants.BASE_SELL.YIJIEZHUAN,sellCarReserveVo.getEnterpriseId()));	
			car.setSellStateChild(baseTagDAO.getChild( Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.RESERVE,sellCarReserveVo.getEnterpriseId()));
			car.setCarAssembleData(new Timestamp(new Date().getTime()));
			XsSellFlowControl flow=xsSellFlowControlDao.get("from XsSellFlowControl where xsfcontrolDocument='"+sellCarReserveVo.getReserveCode()+"'and xsfcontrolCode='"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE4+"'");
			if(flow!=null){
			flow.setXsfcontrolCarId(sellCarReserveVo.getCarId());
			xsSellFlowControlDao.merge(flow);
			}
			XsSellFlowControl flow1=new XsSellFlowControl();
			flow1.setXsfcontrolCode(Contstants.SELLFLOWCONTROLCODE.CONTROLCODE5);
			flow1.setXsfcontrolDocument(sellCarReserveVo.getReserveCode());
			flow1.setXsfcontrolCarId(sellCarReserveVo.getCarId());
			xsSellFlowControlDao.save(flow1);
			setContent("给单号为【"+carReserve.getReserveCode()+"】的预订单分配了单号为【"+car.getCarCode()+"】,VIN号为【"+car.getCarVinNumber()+"】的车辆！");

		} else if (sellCarReserveVo.getVinNum() == 2) {
			carReserve.setXsCarId(null);
			carReserve.setVinCode(null);
			carReserve.setXsChilddictionary7(baseTagDAO.getChild(Contstants.BASE_SELL.ORDER_STATE, Contstants.BASE_SELL.YUDINGZHONG,sellCarReserveVo.getEnterpriseId()));
			car.setSellStateChild(baseTagDAO.getChild( Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.FORSALE,sellCarReserveVo.getEnterpriseId()));
			car.setCarAssembleData(null);
			if(sellCarReserveVo.getVinCode()!=null&&!"".equals(sellCarReserveVo.getVinCode())){
			XsSellFlowControl flow1=xsSellFlowControlDao.get("from XsSellFlowControl where xsfcontrolDocument=" +
					"'"+sellCarReserveVo.getReserveCode()+"'and xsfcontrolCode='"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE5+"'" );
			xsSellFlowControlDao.delete(flow1);
			setContent("取消给单号为【"+carReserve.getReserveCode()+"】的预订单分配了单号为【"+car.getCarCode()+"】,VIN号为【"+car.getCarVinNumber()+"】的车辆！");

			}
		}
		sellCarReserveDao.merge(car);
		sellCarReserveDao.merge(carReserve);
		

	}
	//判断是否使用
	
	public Boolean isUse(SellCarReserveVo sellCarReserveVo) throws Exception {
		String sql = "SELECT * FROM xs_car_sell_info a ,xs_sell_car_reserve B, xs_sell_flow_control flow  " +
				"WHERE  a.reserve_id=b.reserve_id  and flow.xsfcontrol_document=a.sell_code" +
				" and a.reserve_id="+sellCarReserveVo.getReserveId();
		List list = sellCarReserveDao.createSQLQuery(sql);
		if(list!=null  && list.size()>0 ){
			return true;
		}
		return false;
	}

	
	

}
