package com.syuesoft.sell.instore.serviceimpl;

import java.io.Serializable;
import java.sql.Timestamp;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;

import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.control.dao.XsSellFlowControlDao;
import com.syuesoft.sell.instore.dao.InstoreOutDAO;
import com.syuesoft.sell.instore.service.InstoreOutService;

import com.syuesoft.sell.instore.vo.SellRetreatVo;

import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellFlowControl;

import com.syuesoft.sell.model.XsSellInstorehouseDel;
import com.syuesoft.sell.model.XsSellRetreat;
import com.syuesoft.sell.sellwork.dao.CarSellManageDao;

import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;

@Service("instoreOutService")
public class InstoreOutServiceImpl extends BaseLogServiceImpl implements InstoreOutService {
	@Autowired
	private InstoreOutDAO instoreOutDAO;
	@Autowired
	private BaseTagDAO baseTagDAO;
	@Autowired
	private XsSellFlowControlDao xsSellFlowControlDao;
	@Autowired
	private CarSellManageDao carSellManageDao;
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;

	/**
	 * 出库单汇总
	 */
	public Datagrid getPager(SellRetreatVo retreatVo) throws Exception {
		Datagrid dg = new Datagrid();

		StringBuffer sql = new StringBuffer(
				"SELECT  retreat.retreat_code, retreat.retreat_date,retreat.examine,"
						+ "retreat.context_  ,"
						+ "(SELECT k.datavalue FROM xs_childdictionary k  WHERE retreat.examine = k.child_id) AS audit,"
						+ "del.xs_car_id,us.STF_NAME,sell.xs_car_sel_id,retreat.details_id,retreat.retreat_id,"
						+ "sell.sell_code , car.xs_car_code,car.xs_car_vin_number,retreat.enterprise_id  "
						+ "FROM xs_sell_retreat retreat,xs_sell_instorehouse_del del, " +
							" xs_car_info  car ,bas_stuff us, xs_car_sell_info sell ,  xs_sell_flow_control xsfc "
						+ " WHERE retreat.details_id=del.details_id  AND del.xs_car_id=car.xs_car_id AND us.STF_ID=retreat.person_"
						+ " AND sell.xs_car_id=xsfc.xsfcontrol_car_id   AND sell.sell_code=xsfc.xsfcontrol_document"
						+ "  AND sell.xs_car_id=del.xs_car_id AND sell.out_id=retreat.retreat_id AND  retreat.instorehouse_type=(SELECT child.child_id FROM xs_childdictionary  child, "
						+ "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
						+ "AND parent.dataKey='"
						+ Contstants.INSTORESTYLE.PARENTINSTORE + "' AND "
						+ "child.dataKey='" + Contstants.INSTORESTYLE.OUT + "'"
						+ " and child.enterprise_id="+retreatVo.getEnterprise_id()+") and  retreat.enterprise_id="+retreatVo.getEnterprise_id());
		
		if (retreatVo.getRetreatDateStart() != null
					&& !"".equals(retreatVo.getRetreatDateStart())) {
				sql.append(" and  DATE(retreat.retreat_date)>='"
						+ retreatVo.getRetreatDateStart() + "'");
			}
		if (retreatVo.getRetreatDateEnd() != null
					&& !"".equals(retreatVo.getRetreatDateEnd())) {
				sql.append(" and  DATE(retreat.retreat_date)<='"
						+ retreatVo.getRetreatDateEnd()+ "'");
		
		}
		if((retreatVo.getRetreatDateStart() == null
				|| "".equals(retreatVo.getRetreatDateStart()))&&(retreatVo.getRetreatDateEnd()== null
				||"".equals(retreatVo.getRetreatDateEnd()))){
			sql.append(" and  DATE(retreat.retreat_date) BETWEEN '"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 
						(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,retreatVo.getEnterprise_id()).getCiValue()))+"'" +
						" and '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+ "'");
		}
		if (retreatVo.getRetreatCode() != null
				&& !"".equals(retreatVo.getRetreatCode())) {
			sql.append(" and  retreat.retreat_code like '%"
					+ retreatVo.getRetreatCode() + "%'");
		}
		if (retreatVo.getExamine() != null
				&& !"".equals(retreatVo.getExamine())) {
			sql
					.append(" and  retreat.examine='" + retreatVo.getExamine()
							+ "'");
		}
		if (retreatVo.getXs_Car_Sel_Id() != null
				&& !"".equals(retreatVo.getXs_Car_Sel_Id())) {
			sql.append(" and  sell.xs_car_sel_id='"
					+ retreatVo.getXs_Car_Sel_Id() + "'");
		}
		if (retreatVo.getCarVinNumber() != null
				&& !"".equals(retreatVo.getCarVinNumber())) {
			sql.append(" and  car.xs_car_vin_number like '%"
					+ retreatVo.getCarVinNumber() + "%'");
		}
		sql.append(" order by retreat.retreat_code desc");
		List<Object[]> lst = instoreOutDAO.createSQLQuery(sql.toString(),
				retreatVo.getPage(), retreatVo.getRows());
		List<SellRetreatVo> rows = new ArrayList<SellRetreatVo>();
		if (lst != null && lst.size() > 0) {
			for (Object[] obj : lst) {
				SellRetreatVo retreatVo1 = new SellRetreatVo();
				if (obj[0] != null && !("".equals(obj[0]))) {
					retreatVo1.setRetreatCode(obj[0].toString());
				}
				if (obj[1] != null && !("".equals(obj[1]))) {
					retreatVo1.setRetreatDate((Timestamp) obj[1]);
				}
				if (obj[2] != null && !("".equals(obj[2]))) {
					retreatVo1.setExamine((Integer) obj[2]);
				}
				if (obj[3] != null && !("".equals(obj[3]))) {

					retreatVo1.setContext(obj[3].toString());
				}
				if (obj[4] != null && !("".equals(obj[4]))) {
					retreatVo1.setExamineType(obj[4].toString());
				}
				if (obj[5] != null && !("".equals(obj[5]))) {
					retreatVo1.setCarId((Integer) obj[5]);
				}
				if (obj[6] != null && !("".equals(obj[6]))) {
					retreatVo1.setPersonName(obj[6].toString());
				}
				if (obj[7] != null && !("".equals(obj[7]))) {
					retreatVo1.setXs_Car_Sel_Id((Integer) obj[7]);
				}
				if (obj[8] != null && !("".equals(obj[8]))) {
					retreatVo1.setDetailsId((Integer) obj[8]);
				}
				if (obj[9] != null && !("".equals(obj[9]))) {
					retreatVo1.setRetreatId((Integer) obj[9]);
				}
				if (obj[10] != null && !("".equals(obj[10]))) {
					retreatVo1.setSellCode(obj[10].toString());
				}
				if (obj[11] != null && !("".equals(obj[11]))) {
					retreatVo1.setCarCode(obj[11].toString());
				}
				if (obj[12] != null && !("".equals(obj[12]))) {
					retreatVo1.setCarVinNumber(obj[12].toString());
				}
				if (obj[13] != null && !("".equals(obj[13]))) {
					retreatVo1.setEnterprise_id((Integer)obj[13]);
				}
				rows.add(retreatVo1);
			}
		}
		int total = instoreOutDAO.getSQLCount(sql.toString(), null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

	// 入库单明细分页
	public Datagrid getPagerDel(SellRetreatVo retreatVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer(
				"SELECT   del.details_id, del.instorehouse_id,del.xs_car_id, del.notax, del.tax,car.xs_car_code,car.xs_car_vin_number,"
						+ " model.xs_model_salesPrice, car.xs_car_model_id,(SELECT model.xs_model_name FROM xs_car_model model WHERE model.xs_model_id=car.xs_car_model_id) "
						+ "AS  xs_car_model_name, car.xs_car_ocn,car.xs_car_motor_number,car.xs_car_color,(SELECT child.dataValue FROM xs_childdictionary  child WHERE child.child_id=car.xs_car_color) "
						+ "AS  xs_car_color_name,retreat.retreat_id,car.xs_car_brand,(SELECT child.dataValue FROM xs_childdictionary  child WHERE child.child_id=car.xs_car_brand) AS brandName"
						+ "  FROM xs_sell_retreat retreat,xs_sell_instorehouse_del del, xs_car_info  car,xs_car_model model  WHERE retreat.details_id = del.details_id"
						+ "  AND car.xs_car_id=del.xs_car_id AND model.xs_model_id=car.xs_car_model_id   AND   retreat.retreat_code = '"
						+ retreatVo.getRetreatCode() + "'");
		List<Object[]> lst = instoreOutDAO.createSQLQuery(sql.toString(),
				retreatVo.getPage(), retreatVo.getRows());
		List<SellRetreatVo> rows = new ArrayList<SellRetreatVo>();
		if (lst != null && lst.size() > 0) {
			for (Object[] obj : lst) {
				SellRetreatVo retreatVo1 = new SellRetreatVo();
				if (obj[0] != null && !("".equals(obj[0]))) {
					retreatVo1.setDetailsId((Integer) obj[0]);
				}
				if (obj[1] != null && !("".equals(obj[1]))) {
					retreatVo1.setInstorehouse_((Integer) obj[1]);
				}
				if (obj[2] != null && !("".equals(obj[2]))) {
					retreatVo1.setCarInfo_((Integer) obj[2]);
				}
				if (obj[3] != null && !("".equals(obj[3]))) {
					retreatVo1.setNotax((Double) obj[3]);
				}
				if (obj[4] != null && !("".equals(obj[4]))) {
					retreatVo1.setTax((Double) obj[4]);
				}
				if (obj[5] != null && !("".equals(obj[5]))) {
					retreatVo1.setCarCode(obj[5].toString());
				}
				if (obj[6] != null && !("".equals(obj[6]))) {
					retreatVo1.setCarVinNumber(obj[6].toString());
				}
				if (obj[7] != null && !("".equals(obj[7]))) {
					retreatVo1.setModelSalesPrice((Double) obj[7]);
				}
				if (obj[8] != null && !("".equals(obj[8]))) {
					retreatVo1.setCarModelId((Integer) obj[8]);
				}
				if (obj[9] != null && !("".equals(obj[9]))) {
					retreatVo1.setCarModelName(obj[9].toString());
				}
				if (obj[10] != null && !("".equals(obj[10]))) {
					retreatVo1.setCarOcn(obj[10].toString());
				}
				if (obj[11] != null && !("".equals(obj[11]))) {
					retreatVo1.setCarMotorNumber(obj[10].toString());
				}
				if (obj[12] != null && !("".equals(obj[12]))) {
					retreatVo1.setCarColor((Integer) obj[12]);
				}
				if (obj[13] != null && !("".equals(obj[13]))) {
					retreatVo1.setColorName(obj[13].toString());
				}
				if (obj[14] != null && !("".equals(obj[14]))) {
					retreatVo1.setRetreatId((Integer) obj[14]);
				}
				if (obj[15] != null && !("".equals(obj[15]))) {
					retreatVo1.setCarBrand((Integer) obj[15]);
				}
				if (obj[16] != null && !("".equals(obj[16]))) {
					retreatVo1.setCarBrandName(obj[16].toString());
				}
				rows.add(retreatVo1);
			}
		}
		int total = instoreOutDAO.getSQLCount(sql.toString(), null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

	
	@Log(systemName="销售系统", moduleName="车辆出库管理",opertype="新增")
	public void saveInstore(SellRetreatVo retreatVo) throws Exception {
		// 明细
		SellRetreatVo insertedList = JSON.parseObject(retreatVo
				.getSellRetreat(), SellRetreatVo.class);
		if (insertedList != null) {
			String code = CreateID.createId("retreatOut",
					Contstants.SELL_BILLSDEPLOY.INSTOREOUT);
			XsSellInstorehouseDel instoreDel = (XsSellInstorehouseDel) instoreOutDAO
					.get("from XsSellInstorehouseDel del where  del.detailsId="
							+ insertedList.getDetailsId());
			instoreDel.setSellAllocatelType(baseTagDAO.findChildCon(
					Contstants.INSTORETYPE.INSTOREOUT,
					Contstants.BASE_SELL.INSTORETYPE,retreatVo.getEnterprise_id()));// 出库
			instoreOutDAO.merge(instoreDel);
			// 更新车的销售状态
			XsCarInfo car = instoreDel.getCarInfo();
			XsChilddictionary sellState = baseTagDAO.findChildsCon(
					Contstants.SELLSTATE.SELLOUT,
					Contstants.SELLSTATE.BASE_SELLSTATE,retreatVo.getEnterprise_id());
			car.setSellStateChild(sellState);
			instoreOutDAO.merge(car);
			// 退车单
			XsSellRetreat sellRetreat = new XsSellRetreat();
			sellRetreat.setRetreatCode(code);
			int examineState = baseTagDAO.findChildCon(
					Contstants.ADUIT.WEISHENHE, Contstants.BASE_SELL.ADUIT,retreatVo.getEnterprise_id());
			sellRetreat.setExamine(examineState);
			sellRetreat.setDetails(instoreDel);
			int instoreStyle = baseTagDAO.findChildCon(
					Contstants.INSTORESTYLE.OUT,
					Contstants.INSTORESTYLE.PARENTINSTORE,retreatVo.getEnterprise_id());
			sellRetreat.setInstorehouseType(instoreStyle);
			sellRetreat.setPerson(insertedList.getPerson());
			sellRetreat.setRetreatDate((insertedList.getRetreatDate()));
			sellRetreat.setContext(insertedList.getContext());
			sellRetreat.setEnterprise_id(retreatVo.getEnterprise_id());
			Serializable id = instoreOutDAO.save(sellRetreat);
			XsCarSellInfo sell = (XsCarSellInfo) instoreOutDAO
					.get("from XsCarSellInfo  where xsCarSelId="
							+ insertedList.getXs_Car_Sel_Id());
			sell.setOutId((Integer) id);
			instoreOutDAO.merge(sell);
			XsSellFlowControl xsfc = new XsSellFlowControl();
			xsfc.setXsfcontrolDocument(sellRetreat.getRetreatCode());
			xsfc.setXsfcontrolCode(Contstants.SELLFLOWCONTROLCODE.CONTROLCODE9);
			xsfc.setXsfcontrolCarId(sell.getXsCarId());
			xsSellFlowControlDao.save(xsfc);
			XsCarSellInfo sellcar=carSellManageDao.get(" from XsCarSellInfo where outId="+sellRetreat.getRetreatId());
			
			setContent("新增【车辆出库管理】编码为【"+sellRetreat.getRetreatCode()+"】,出库日期【"+sellRetreat.getRetreatDate()+"】," +
					"出库理由为【"+sellRetreat.getContext()+"】,销售单号为【"+sellcar.getSellCode()+"】的信息！");
		}
	}

	
	public void deleteInstore(SellRetreatVo retreatVo) throws Exception {
		XsSellInstorehouseDel instoreDel = (XsSellInstorehouseDel) instoreOutDAO
				.get(" from XsSellInstorehouseDel del where 1=1 and  del.detailsId="
						+ retreatVo.getDetailsId());
		// 查询库存状态
		Integer instore = baseTagDAO.findChildCon(
				Contstants.INSTORETYPE.INSTORE,
				Contstants.BASE_SELL.INSTORETYPE,retreatVo.getEnterprise_id());
		instoreDel.setSellAllocatelType(instore);
		// 更新入库单明细中，是否退厂状态
		instoreOutDAO.merge(instoreDel);
		// 更新车的销售状态
		XsCarInfo car = instoreDel.getCarInfo();
		XsChilddictionary sellState = baseTagDAO.findChildsCon(Contstants.SELLSTATE.FORSALE,
				Contstants.SELLSTATE.BASE_SELLSTATE,retreatVo.getEnterprise_id());
		car.setSellStateChild(sellState);
		instoreOutDAO.merge(car);
		XsCarSellInfo sell = (XsCarSellInfo) instoreOutDAO
				.get("from XsCarSellInfo  where xsCarSelId="
						+ retreatVo.getXs_Car_Sel_Id());
		sell.setOutId(null);
		instoreOutDAO.merge(sell);
		// 删除记录
		XsSellRetreat retreat = (XsSellRetreat) instoreOutDAO
				.get(" from XsSellRetreat retreat where 1=1 and  retreat.retreatId="
						+ retreatVo.getRetreatId());
		instoreOutDAO.delete(retreat);
	}

	
	@Log(systemName="销售系统", moduleName="车辆出库管理",opertype="审核")
	public void updateExamine(SellRetreatVo retreatVo) throws Exception {
		String contex="";
		XsSellRetreat instorehouse = (XsSellRetreat) instoreOutDAO
				.get("from XsSellRetreat retreat where 1=1 and  retreat.retreatId="
						+ retreatVo.getRetreatId());
		int examineState1 = baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE,
				Contstants.BASE_SELL.ADUIT,retreatVo.getEnterprise_id());
		int examineState = baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE,
				Contstants.BASE_SELL.ADUIT,retreatVo.getEnterprise_id());
//		if (instorehouse.getExamine() == examineState1) {
//			instorehouse.setExamine(examineState);
//			contex="取消审核单号为【"+instorehouse.getRetreatCode()+"】的移库单";
//		} else {
//			instorehouse.setExamine(examineState1);
//			contex="审核单号为【"+instorehouse.getRetreatCode()+"】的移库单";
//		}

		if(instorehouse.getExamine() == examineState){
			instorehouse.setExamine(examineState1);
			contex="审核单号为【"+instorehouse.getRetreatCode()+"】的移库单";
			instoreOutDAO.update(instorehouse);
			setContent(""+contex+"");
		}

	}

	
	public void updateInstore(SellRetreatVo retreatVo) throws Exception {

		SellRetreatVo insertedList = JSON.parseObject(retreatVo
				.getSellRetreat(), SellRetreatVo.class);

		XsSellRetreat retreatInserted = (XsSellRetreat) instoreOutDAO
				.get("from XsSellRetreat  where retreatCode='"
						+ insertedList.getRetreatCode() + "'");
		retreatInserted.setContext(insertedList.getContext());
		instoreOutDAO.update(retreatInserted);
	}

	
	public List<SellRetreatVo> findByCarId(Integer sellId) throws Exception {
		return instoreOutDAO.findByCarId(sellId);
	}

	
	public Boolean isRefundment(SellRetreatVo sellRetreatVo) throws Exception {
		Integer examine = baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE,
				Contstants.BASE_SELL.ADUIT,sellRetreatVo.getEnterprise_id());
		if (sellRetreatVo.getExamine().equals(examine))
			return true;
		return false;
	}

}
