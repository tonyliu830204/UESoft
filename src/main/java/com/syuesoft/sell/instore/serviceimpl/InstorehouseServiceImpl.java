package com.syuesoft.sell.instore.serviceimpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasStuff;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.dao.CarModelDAO;
import com.syuesoft.sell.base.dao.SupplierInfoDAO;
import com.syuesoft.sell.control.dao.XsSellFlowControlDao;
import com.syuesoft.sell.instore.dao.InstorehouseDAO;
import com.syuesoft.sell.instore.service.InstorehouseService;
import com.syuesoft.sell.instore.vo.SellInstorehouseVo;
import com.syuesoft.sell.instore.vo.SellRetreatVo;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellFlowControl;
import com.syuesoft.sell.model.XsSellInstorehouse;
import com.syuesoft.sell.model.XsSellInstorehouseDel;
import com.syuesoft.sell.model.XsSellRetreat;
import com.syuesoft.sell.model.XsSupplierInfo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.TreeJson;
import com.syuesoft.util.Utils;

@Service("instorehouseService")
public class InstorehouseServiceImpl extends BaseLogServiceImpl implements InstorehouseService {
	@Autowired
	private InstorehouseDAO instorehouseDAO;
	@Autowired
	private CarModelDAO carModelDAO;
	@Autowired
	private BaseTagDAO baseTagDAO;
	@Autowired
	private XsSellFlowControlDao xsSellFlowControlDao;
	@Autowired
	private SupplierInfoDAO supplierInfoDAO;
	@Autowired
	private BasStuffDao basStuffDao;
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	// 入库单分页
	
	public Datagrid getPager(SellInstorehouseVo InstoreVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer(
				"SELECT DISTINCT  instore.instorehouse_id,instore.instorehouse_code,instore.instorehouse_date,instore.invoice_type,");
		sql.append(" (SELECT child.dataValue  FROM  xs_childdictionary  child  WHERE child.child_id=instore.invoice_type) AS invoice_name,");
		sql.append(" instore.STF_ID, (SELECT  stuff.STF_NAME   FROM  bas_stuff   stuff  WHERE stuff.STF_ID=instore.STF_ID) AS stf_name,");
		sql.append(" instore.warehouse, (SELECT child.dataValue  FROM  xs_childdictionary  child  WHERE child.child_id=instore.warehouse) AS warehouse_name,");
		sql.append(" instore.purchaser,  (SELECT  stuff.STF_NAME   FROM  bas_stuff   stuff  WHERE stuff.STF_ID=instore.purchaser) AS purchaser_name,");
		sql.append(" instore.receipt,instore.state,instore.number_, instore.notax,instore.tax,instore.transport_vehicles,instore.examine_state,");
		sql.append(" (SELECT child.dataValue  FROM  xs_childdictionary  child  WHERE child.child_id=instore.examine_state) AS examine_name,");
		sql.append(" instore.remark,instore.xs_supplier_id,(SELECT child.dataValue  FROM  xs_childdictionary  child  WHERE child.child_id=instore.state) AS state_name,");
		sql.append(" supplier.xs_supplier_name ,instore.enterprise_id");
		sql.append(" FROM xs_sell_instorehouse   instore");
		sql.append(" INNER JOIN  xs_sell_instorehouse_del del ON instore.instorehouse_id=del.instorehouse_id");
		sql.append(" INNER JOIN   xs_car_info  car ON del.xs_car_id=car.xs_car_id");
		sql.append(" INNER JOIN  xs_supplier_info supplier ON supplier.xs_supplier_id=instore.xs_supplier_id");
		sql.append(" INNER JOIN xs_sell_flow_control xsfc ON  xsfc.xsfcontrol_document = instore.instorehouse_code "
						+ " AND xsfc.xsfcontrol_car_id=del.xs_car_id "
						+ " AND xsfc.xsfcontrol_code="
						+ Contstants.SELLFLOWCONTROLCODE.CONTROLCODE3);
		sql.append(" WHERE 1=1 and  instore.enterprise_id="
				+ InstoreVo.getEnterprise_id());
		if (InstoreVo.getQueryInstoreDate() != null
				&& !("".equals(InstoreVo.getQueryInstoreDate()))) {
			
					sql.append(" and  DATE(instore.instorehouse_date) >= '" + InstoreVo.getQueryInstoreDate()	+ "'");
				}
		if (InstoreVo.getQueryInstoreDate2() != null
						&& !("".equals(InstoreVo.getQueryInstoreDate2()))) {
					sql.append(" and  DATE(instore.instorehouse_date) <= '" + InstoreVo.getQueryInstoreDate2()+ "'");
				}
		if((InstoreVo.getQueryInstoreDate() == null
				|| "".equals(InstoreVo.getQueryInstoreDate()))&&(InstoreVo.getQueryInstoreDate2() == null
				|| "".equals(InstoreVo.getQueryInstoreDate2()))){
			sql.append(" and  DATE(instore.instorehouse_date) " +
					"BETWEEN '" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,InstoreVo.getEnterprise_id()).getCiValue()))
					+ "' AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
		}
		if (InstoreVo.getQueryVinNumber() != null
				&& !("".equals(InstoreVo.getQueryVinNumber()))) {
			sql.append(" and  car.xs_car_vin_number like'%"
					+ StringEscapeUtils.escapeSql(InstoreVo.getQueryVinNumber()
							.trim()) + "%'");
		}
		if (InstoreVo.getQueryInstoreCode() != null
				&& !("".equals(InstoreVo.getQueryInstoreCode()))) {
			sql.append(" and  instore.instorehouse_code like'%"
					+ StringEscapeUtils.escapeSql(InstoreVo
							.getQueryInstoreCode().trim()) + "%'");
		}
		if (InstoreVo.getQueryExamine() != null
				&& !("".equals(InstoreVo.getQueryExamine()))) {
			sql.append(" and  instore.examine_state ="
					+ InstoreVo.getQueryExamine());
		}
		if (InstoreVo.getQueryCarModel() != null
				&& !("".equals(InstoreVo.getQueryCarModel()))) {
			sql.append(" and   car.xs_car_model_id ="
					+ InstoreVo.getQueryCarModel());
		}
		if (InstoreVo.getQuerySupplier() != null
				&& !("".equals(InstoreVo.getQuerySupplier()))) {
			sql.append(" and  instore.xs_supplier_id ="
					+ InstoreVo.getQuerySupplier());
		}
		sql.append(" order by  instore.instorehouse_id desc");
		List<Object[]> lst = instorehouseDAO.createSQLQuery(sql.toString(),
				InstoreVo.getPage(), InstoreVo.getRows());
		List<SellInstorehouseVo> rows = new ArrayList<SellInstorehouseVo>();
		if (lst != null && lst.size() > 0) {
			for (Object[] b : lst) {
				SellInstorehouseVo instoreVo = new SellInstorehouseVo();
				setVo(instoreVo, b);
				rows.add(instoreVo);
			}
		}
		int total = instorehouseDAO.getSQLCount(sql.toString(), null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;

	}

	public void setVo(SellInstorehouseVo instoreVo, Object[] obj) {
		if (obj[0] != null && !("".equals(obj[0]))) {
			instoreVo.setInstorehouseId((Integer) obj[0]);
		}
		if (obj[1] != null && !("".equals(obj[1]))) {
			instoreVo.setInstorehouseCode(obj[1].toString());
		}
		if (obj[2] != null && !("".equals(obj[2]))) {
			instoreVo.setInstorehouseDate((Timestamp) obj[2]);
		}
		if (obj[3] != null && !("".equals(obj[3]))) {
			instoreVo.setInvoiceType((Integer) obj[3]);
		}
		if (obj[4] != null && !("".equals(obj[4]))) {
			instoreVo.setInvoiceName(obj[4].toString());
		}
		if (obj[5] != null && !("".equals(obj[5]))) {
			instoreVo.setStfId(Long.valueOf(obj[5].toString()));
		}
		if (obj[6] != null && !("".equals(obj[6]))) {
			instoreVo.setStfName(obj[6].toString());
		}
		if (obj[7] != null && !("".equals(obj[7]))) {
			instoreVo.setWarehouse((Integer) obj[7]);
		}
		if (obj[8] != null && !("".equals(obj[8]))) {
			instoreVo.setWarehouseName(obj[8].toString());
		}
		if (obj[9] != null && !("".equals(obj[9]))) {
			instoreVo.setPurchaser((Integer) obj[9]);
		}
		if (obj[10] != null && !("".equals(obj[10]))) {
			instoreVo.setPurchaserName(obj[10].toString());
		}
		if (obj[11] != null && !("".equals(obj[11]))) {
			instoreVo.setReceipt(obj[11].toString());
		}
		if (obj[12] != null && !("".equals(obj[12]))) {
			instoreVo.setState1((Integer) obj[12]);
		}
		if (obj[13] != null && !("".equals(obj[13]))) {
			instoreVo.setNumber((Integer) obj[13]);
		}
		if (obj[14] != null && !("".equals(obj[14]))) {
			instoreVo.setTotalNotax((Double) obj[14]);
		}
		if (obj[15] != null && !("".equals(obj[15]))) {
			instoreVo.setTotalTax((Double) obj[15]);
		}
		if (obj[16] != null && !("".equals(obj[16]))) {
			instoreVo.setTransportVehicles(obj[16].toString());
		}
		if (obj[17] != null && !("".equals(obj[17]))) {
			instoreVo.setExamineState((Integer) obj[17]);
		}
		if (obj[18] != null && !("".equals(obj[18]))) {
			instoreVo.setExamineName(obj[18].toString());
		}
		if (obj[19] != null && !("".equals(obj[19]))) {
			instoreVo.setRemark(obj[19].toString());
		}
		if (obj[20] != null && !("".equals(obj[20]))) {
			instoreVo.setSupplierId((Integer) obj[20]);
		}
		if (obj[21] != null && !("".equals(obj[21]))) {
			instoreVo.setStateName(obj[21].toString());
		}
		if (obj[22] != null && !("".equals(obj[22]))) {
			instoreVo.setSupplier(obj[22].toString());
		}
		if (obj[23] != null && !("".equals(obj[23]))) {
			instoreVo.setEnterprise_id((Integer)obj[23]);
		}

	}

	// 入库单明细分页
	public Datagrid getPagerDel(SellInstorehouseVo InstoreVo) throws Exception {
		Datagrid dg = new Datagrid();
		// 查询库存状态
		/*Integer instore = baseTagDAO.findChildCon(
				Contstants.INSTORETYPE.INSTORE,
				Contstants.BASE_SELL.INSTORETYPE);*/
		// 在库待销
		Integer sellState = baseTagDAO.findChildCon(
				Contstants.SELLSTATE.FORSALE,
				Contstants.SELLSTATE.BASE_SELLSTATE,InstoreVo.getEnterprise_id());
		Integer examin = baseTagDAO.findChildCon(
				Contstants.ADUIT.YISHENHE,
				Contstants.ADUIT.ADUIT,InstoreVo.getEnterprise_id());
	
			StringBuffer sql = new StringBuffer(
					"SELECT    del.details_id,del.instorehouse_id,del.tax,del.notax,car.xs_car_id, car.xs_car_code,car.xs_car_vin_number,");
			sql
					.append(" car.xs_car_model_id,(SELECT model.xs_model_name FROM  xs_car_model model WHERE model.xs_model_id=car.xs_car_model_id) AS  car_model_name,");
			sql
					.append(" model.xs_model_salesPrice,car.xs_car_ocn,car.xs_car_motor_number,car.xs_car_color,");
			sql
					.append(" (SELECT child.dataValue FROM  xs_childdictionary child  WHERE child.child_id=car.xs_car_color) AS car_color_name,");
			sql
					.append(" car.xs_car_brand,(SELECT child.dataValue FROM  xs_childdictionary child  WHERE child.child_id=car.xs_car_brand) AS car_brand_name,del.vehicle_cost,"
							+ "del.changeMoney,del.freightMoney,(SELECT  child.dataValue" +
						"  FROM xs_childdictionary child   WHERE child.child_id =   car.xs_car_sell_state) AS staten ");
			sql
					.append(" FROM xs_sell_instorehouse house " +
							" INNER JOIN xs_sell_instorehouse_del del ON del.instorehouse_id=house.instorehouse_id");
			sql
					.append(" INNER JOIN xs_sell_flow_control xsfc ON xsfc.xsfcontrol_car_id=del.xs_car_id AND xsfc.xsfcontrol_document=house.instorehouse_code");
			sql
					.append(" INNER JOIN xs_car_info car   ON del.xs_car_id = car.xs_car_id");
			sql
					.append(" INNER JOIN xs_car_model model   ON model.xs_model_id = car.xs_car_model_id");
			sql.append(" where 1=1");
			
			if(InstoreVo.getEnterprise_id()!=null&&!"".equals(InstoreVo.getEnterprise_id())){
				sql.append(" and house.enterprise_id ="+InstoreVo.getEnterprise_id());
			}
			//carBack新增退厂单
			if (InstoreVo.getKey() != null && !("".equals(InstoreVo.getKey()))
					&& "carBack".equals(InstoreVo.getKey())) {
				sql.append("  and car.xs_car_sell_state=" + sellState + " " +
						" and house.examine_state ="+examin+"" +
						" and house.xs_supplier_id="
						+ InstoreVo.getSupplierId());
			}
			if (InstoreVo.getInstorehouse_() != null
					&& !("".equals(InstoreVo.getInstorehouse_()))) {
				sql.append("  and del.instorehouse_id="
						+ InstoreVo.getInstorehouse_());
			}
			if (InstoreVo.getDetailsId() != null
					&& !("".equals(InstoreVo.getDetailsId()))) {
				sql.append("  and  del.details_id=" + InstoreVo.getDetailsId());
			}
			if (InstoreVo.getSellAllocatelType() != null
					&& !("".equals(InstoreVo.getSellAllocatelType()))) {
				sql.append("  and  del.xs_sell_allocatel_type="
						+ InstoreVo.getSellAllocatelType());
			}

			if (InstoreVo.getQueryVinNumber() != null
					&& !("".equals(InstoreVo.getQueryVinNumber()))) {
				sql.append("  and  car.xs_car_vin_number like '%"
						+ StringEscapeUtils.escapeSql(InstoreVo
								.getQueryVinNumber().trim()) + "%'");
			}
			if (InstoreVo.getQueryBrand() != null
					&& !("".equals(InstoreVo.getQueryBrand()))) {
				sql.append("  and  car.xs_car_brand="
						+ InstoreVo.getQueryBrand());
			}
			if (InstoreVo.getQueryCarModel() != null
					&& !("".equals(InstoreVo.getQueryCarModel()))) {
				sql.append("  and  car.xs_car_model_id="
						+ InstoreVo.getQueryCarModel());
			}
			if (InstoreVo.getQueryColor() != null
					&& !("".equals(InstoreVo.getQueryColor()))) {
				sql.append("  and  car.xs_car_color="
						+ InstoreVo.getQueryColor());
			}
			List<Object[]> lst = instorehouseDAO.createSQLQuery(sql.toString(),
					InstoreVo.getPage(), InstoreVo.getRows());
			List<SellInstorehouseVo> rows = new ArrayList<SellInstorehouseVo>();
			if (lst != null && lst.size() > 0) {
				for (Object[] obj : lst) {
					SellInstorehouseVo instoreVo = new SellInstorehouseVo();
					if (obj[0] != null && !("".equals(obj[0]))) {
						instoreVo.setDetailsId((Integer) obj[0]);
					}
					if (obj[1] != null && !("".equals(obj[1]))) {
						instoreVo.setInstorehouse_((Integer) obj[1]);
					}
					if (obj[2] != null && !("".equals(obj[2]))) {
						instoreVo.setTax((Double) obj[2]);
					}
					if (obj[3] != null && !("".equals(obj[3]))) {
						instoreVo.setNotax((Double) obj[3]);
					}
					if (obj[4] != null && !("".equals(obj[4]))) {
						instoreVo.setCarId((Integer) obj[4]);
					}
					if (obj[5] != null && !("".equals(obj[5]))) {
						instoreVo.setCarCode(obj[5].toString());
					}
					if (obj[6] != null && !("".equals(obj[6]))) {
						instoreVo.setCarVinNumber(obj[6].toString());
					}
					if (obj[7] != null && !("".equals(obj[7]))) {
						instoreVo.setCarModelId((Integer) obj[7]);
					}
					if (obj[8] != null && !("".equals(obj[8]))) {
						instoreVo.setCarModelName(obj[8].toString());
					}
					if (obj[9] != null && !("".equals(obj[9]))) {
						instoreVo.setModelSalesPrice((Double) obj[9]);
					}
					if (obj[10] != null && !("".equals(obj[10]))) {
						instoreVo.setCarOcn(obj[10].toString());
					}
					if (obj[11] != null && !("".equals(obj[11]))) {
						instoreVo.setCarMotorNumber(obj[11].toString());
					}
					if (obj[12] != null && !("".equals(obj[12]))) {
						instoreVo.setCarColor((Integer) obj[12]);
					}
					if (obj[13] != null && !("".equals(obj[13]))) {
						instoreVo.setColorName(obj[13].toString());
					}
					if (obj[14] != null && !("".equals(obj[14]))) {
						instoreVo.setCarBrand((Integer) obj[14]);
					}
					if (obj[15] != null && !("".equals(obj[15]))) {
						instoreVo.setCarBrandName(obj[15].toString());
					}
					if (obj[16] != null && !("".equals(obj[16]))) {
						instoreVo.setVehicleCost((Double) obj[16]);
					}
					if (obj[17] != null && !("".equals(obj[17]))) {
						instoreVo.setChangeMoney((Double) obj[17]);
					}
					if (obj[18] != null && !("".equals(obj[18]))) {
						instoreVo.setFreightMoney((Double) obj[18]);
					}
					if (obj[19] != null && !("".equals(obj[19]))) {
						instoreVo.setSellStateName( obj[19].toString());
					}
					rows.add(instoreVo);
				}
			}
			int total = instorehouseDAO.getSQLCount(sql.toString(), null);
			dg.setRows(rows);
			dg.setTotal(total);
			return dg;

	}

	// 入库单查询方法
	public List<TreeJson> queryInstore(SellInstorehouseVo InstoreVo)
			throws Exception {
		List<TreeJson> trees = new ArrayList<TreeJson>();
		List<Object[]> objs = instorehouseDAO.queryInstore(InstoreVo.getPage(),
				InstoreVo.getRows());
		return trees;

	}

	// 修改车辆档案销售状态,删除入库单明细
	public void deleteCar(SellInstorehouseVo instoreVo) throws Exception {
		XsCarInfo car = (XsCarInfo) instorehouseDAO
				.get("from XsCarInfo car where 1=1 and  car.carId="
						+ instoreVo.getCarId());
		car.setSellStateChild(baseTagDAO.findChildsCon(
				Contstants.SELLSTATE.SELLADD,
				Contstants.SELLSTATE.BASE_SELLSTATE,instoreVo.getEnterprise_id()));
		instorehouseDAO.update(car);
		XsSellInstorehouseDel del = instorehouseDAO.findDelById(instoreVo
				.getDetailsId());
		instorehouseDAO.delete(del);
	}

	public Boolean findDels(SellInstorehouseVo instoreVo) throws Exception {
		Integer id = instoreVo.getInstorehouseId();
		if (id == null || "".equals(id)) {
			SellInstorehouseVo instorehouse = JSON.parseObject(instoreVo
					.getInstorehousedateGrid(), SellInstorehouseVo.class);
			id = instorehouse.getInstorehouseId();
		}
		List<Object[]> delList = instorehouseDAO
				.createSQLQuery("SELECT  t.details_id FROM xs_sell_retreat " +
						"" +
						" t WHERE t.details_id IN(SELECT details_id FROM xs_sell_instorehouse_del del LEFT JOIN  xs_sell_instorehouse h"
						+ " ON h.instorehouse_id=del.instorehouse_id WHERE h.instorehouse_id="
						+ id + ")  AND t.instorehouse_type!='"+baseTagDAO.findChildCon(
								Contstants.INSTORESTYLE.SECONDINSTORE,
								Contstants.INSTORESTYLE.PARENTINSTORE,instoreVo.getEnterprise_id())+"'");
		if (delList != null && delList.size() > 0) {
			return true;
		}
		delList = null;
		delList = instorehouseDAO
				.createSQLQuery("SELECT xsid.instorehouse_id FROM xs_sell_allocatel_detail xsad "
						+ "INNER JOIN xs_sell_instorehouse_del xsid "
						+ "ON xsid.details_id=xsad.details_id " +
						" where xsid.instorehouse_id="
						+ id);
		if (delList != null && delList.size() > 0) {
			return true;
		}
		//是否月结
		delList = null;
		delList = instorehouseDAO
				.createSQLQuery("SELECT del.instorehouse_id FROM xs_sell_instorehouse_del del "
						+ "INNER JOIN xs_sell_census cen "
						+ "ON del.xs_car_id=cen.xs_car_id where del.instorehouse_id="
						+ id);
		if (delList != null && delList.size() > 0) {
			return true;
		}
		// delList=null;
		// delList=instorehouseDAO.createSQLQuery("SELECT * FROM xs_sell_instorehouse xsi INNER JOIN xs_sell_instorehouse_del xsid ON xsid.instorehouse_id=xsi.instorehouse_id "
		// +
		// "INNER JOIN xs_sell_flow_control xsfc ON xsfc.xsfcontrol_car_id=xsid.xs_car_id "
		// +
		// "AND xsfc.xsfcontrol_code="+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE4+" AND xsid.instorehouse_id="+id);
		// if(delList!=null && delList.size()>0 ){
		// return true;
		// }
		return false;
	}

	// 修改车辆档案销售状态，删除入库单明细，删除入库单信息
	
	@Log(systemName="销售系统", moduleName="入库单管理",opertype="删除") 
	public void deleteInstore(SellInstorehouseVo instoreVo) throws Exception {
		List<XsSellInstorehouseDel> instoreDels = instorehouseDAO
				.findByInstoreId(instoreVo.getInstorehouseId());
		if (instoreDels != null && instoreDels.size() > 0) {
			for (XsSellInstorehouseDel instoreDel : instoreDels) {
				XsCarInfo car = instoreDel.getCarInfo();
				XsChilddictionary sellState = baseTagDAO.findChildsCon(
						Contstants.SELLSTATE.SELLADD,
						Contstants.SELLSTATE.BASE_SELLSTATE,instoreVo.getEnterprise_id());
				if (sellState != null) {
					car.setSellStateChild(sellState);
				}
				instorehouseDAO.update(car);
				// instorehouseDAO.delete(instoreDel);
			}
		}
		XsSellInstorehouse instore = instorehouseDAO.findById(instoreVo
				.getInstorehouseId());
		xsSellFlowControlDao
				.executeSql("DELETE FROM xs_sell_flow_control WHERE xsfcontrol_document='"
						+ instore.getInstorehouseCode() + "'");
		setContent("删除编号为【"+instore.getInstorehouseCode()+"】的【入库单管理】信息！"); 
	}

	// 添加入库单，修改车辆档案销售状态，添加入库明细
	
	@Log(systemName="销售系统", moduleName="入库单管理",opertype="新增") 
	public void addInstore(SellInstorehouseVo instoreVo) throws Exception {
		SellInstorehouseVo instorehouse = JSON.parseObject(instoreVo
				.getInstorehousedateGrid(), SellInstorehouseVo.class);
		List<SellInstorehouseVo> insertedList = JSON.parseArray(instoreVo
				.getDetaildateGrid(), SellInstorehouseVo.class);
		XsSellInstorehouse house = new XsSellInstorehouse();
		BeanUtils.copyProperties(instorehouse, house);
		house.setInstorehouseCode(CreateID.createId("instorehouse",
				Contstants.SELL_BILLSDEPLOY.INSTOREHOUSE));
		house.setStfId(instoreVo.getStfId());
		house.setEnterprise_id(instoreVo.getEnterprise_id());
		Integer examineState = baseTagDAO.findChildCon(
				Contstants.ADUIT.WEISHENHE, Contstants.BASE_SELL.ADUIT,instoreVo.getEnterprise_id());
		if (examineState != null) {
			house.setExamineState(examineState);
		}
		house.setTax(instorehouse.getTotalTax());
		house.setNotax(instorehouse.getTotalNotax());
		Integer state = baseTagDAO.findChildCon(Contstants.PAYMENTSTATE.UNPAID,
				Contstants.BASE_SELL.PAYMENTSTATE,instoreVo.getEnterprise_id());
		if (state != null) {
			house.setState1(state);
		}
		instorehouseDAO.save(house);
		// 根据入库日期，计算维护日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(instorehouse.getInstorehouseDate());
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.DATE, +15);// 减一个月
		str = sdf.format(lastDate.getTime());
		for (SellInstorehouseVo delVo : insertedList) {
			XsCarInfo carInfo = (XsCarInfo) instorehouseDAO
					.get("from XsCarInfo car where carId=" + delVo.getCarId());
			XsChilddictionary sellState = baseTagDAO.findChildsCon(
					Contstants.SELLSTATE.FORSALE,
					Contstants.SELLSTATE.BASE_SELLSTATE,instoreVo.getEnterprise_id());
			if (sellState != null) {
				carInfo.setSellStateChild(sellState);
			}
			//carInfo.setDistributorId(InstoreVo.getSupplierId());
			carInfo.setServicingNextdate(sdf.parse(str));
			instorehouseDAO.merge(carInfo);
			XsSellInstorehouseDel del = (XsSellInstorehouseDel) instorehouseDAO
			.get("from  XsSellInstorehouseDel del where del.carInfo.carId="
					+ delVo.getCarId());
			
				XsSellInstorehouseDel instoreDel = new XsSellInstorehouseDel();
				BeanUtils.copyProperties(delVo, instoreDel);
				instoreDel.setXsCensus(0);
				instoreDel.setCarInfo(carInfo);
				instoreDel.setInstorehouse(house);
				Integer instore = baseTagDAO.findChildCon(
						Contstants.INSTORETYPE.INSTORE,
						Contstants.BASE_SELL.INSTORETYPE,instoreVo.getEnterprise_id());
				if (instore != null) {
					instoreDel.setSellAllocatelType(instore);
				}
				instoreDel.setCarInstorageAge(instorehouseDAO
						.GetInstoreAge(house.getInstorehouseDate()));
				instorehouseDAO.save(instoreDel);
				
				//再次入库
				if (del != null
						&& !("".equals(delVo.getDetailsId()))) {
					
					XsSellRetreat retreat = new XsSellRetreat();
					
					if (del != null) {
						retreat.setDetails(instoreDel);
					}
					retreat.setPerson(Integer.parseInt(String.valueOf(instoreVo.getStfId())));
					Integer type = baseTagDAO.findChildCon(
							Contstants.INSTORESTYLE.SECONDINSTORE,
							Contstants.INSTORESTYLE.PARENTINSTORE,instoreVo.getEnterprise_id());
					if (type != null) {
						retreat.setInstorehouseType(type);
					}
					retreat.setRetreatDate(new Date());
					retreat.setEnterprise_id(instoreVo.getEnterprise_id());
					retreat.setInInstorehouse(delVo.getWarehouse());
					instorehouseDAO.save(retreat);
				} 
			
			XsSellFlowControl xsfc = new XsSellFlowControl();
			xsfc.setXsfcontrolCarId(delVo.getCarId());
			xsfc.setXsfcontrolCode(Contstants.SELLFLOWCONTROLCODE.CONTROLCODE3);
			xsfc.setXsfcontrolDocument(house.getInstorehouseCode());
			xsSellFlowControlDao.save(xsfc);
			XsSupplierInfo supplier=supplierInfoDAO.get(" from XsSupplierInfo where supplierId="+instorehouse.getSupplierId());
			XsChilddictionary type=baseTagDAO.findById(house.getInvoiceType());
			 BasStuff stuf=basStuffDao.get(" from BasStuff where stfId="+house.getPurchaser());
			 String stfName="";
			 if(stuf!=null){
				 stfName=stuf.getStfName();
			}
			setContent("新增【入库单管理】编码为【"+house.getInstorehouseCode()+"】,入库日期为【"+house.getInstorehouseDate()+"】," +
					"供应商为【"+supplier.getSupplierName()+"】,发票类型为【"+type.getDataValue()+"】," +
					"未税额为【"+house.getNotax()+"】含税额为【"+house.getInstorehouseCode()+"】," +
					"台数为【"+house.getNumber()+"】！," +
						"采购员为【"+stfName+"】的信息！");
			
		}
	}

	
	@Log(systemName="销售系统", moduleName="入库单管理",opertype="导入") 
	public void saveImportInstore(SellInstorehouseVo instoreVo)
			throws Exception {

		List<SellInstorehouseVo> delCars = JSON.parseArray(instoreVo
				.getDetaildateGrid(), SellInstorehouseVo.class);
		// 保存入库单汇总
		XsSellInstorehouse house = new XsSellInstorehouse();
		house.setInstorehouseCode(CreateID.createId("instorehouse",
				Contstants.SELL_BILLSDEPLOY.INSTOREHOUSE));
		house.setStfId(instoreVo.getStfId());
		Integer examineState = baseTagDAO.findChildCon(
				Contstants.ADUIT.WEISHENHE, Contstants.BASE_SELL.ADUIT,instoreVo.getEnterprise_id());
		if (examineState != null) {
			house.setExamineState(examineState);
		}
		house.setTax(0.0);
		house.setNotax(0.0);
		Integer state = baseTagDAO.findChildCon(Contstants.BASE_SELL.PAYMENTSTATE2,
				Contstants.BASE_SELL.PAYMENTSTATE,instoreVo.getEnterprise_id());
		if (state != null) {
			house.setState1(state);
		}
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		f.format(new Date());
		house.setInstorehouseDate(new Timestamp(new Date().getTime()));
		Integer warehouse = baseTagDAO.findChildCon(Contstants.BASE_SELL.pack,
				Contstants.BASE_SELL.ATTACHFOUR,instoreVo.getEnterprise_id());
		if (examineState != null) {
			house.setWarehouse(warehouse);
		}
		house.setEnterprise_id(instoreVo.getEnterprise_id());
		instorehouseDAO.save(house);
		// 根据入库日期，计算维护日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(house.getInstorehouseDate());
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.DATE, +15);// 减一个月
		str = sdf.format(lastDate.getTime());
		if (delCars != null && delCars.size() > 0) {
			List<XsCarInfo> carInfos = new ArrayList<XsCarInfo>();
			for (int i = 0; i < delCars.size(); i++) {
				SellInstorehouseVo carVo = delCars.get(i);
				XsCarInfo car = new XsCarInfo();
				car.setCarCode(CreateID.createId("carInfo",
						Contstants.SELL_BILLSDEPLOY.CARINFOR));
				if (carVo.getCarLicenseName() != null
						&& !("".equals(carVo.getCarLicenseName()))) {
					car.setCarLicenseName(carVo.getCarLicenseName());
				}
				if (carVo.getCarVinNumber() != null
						&& !("".equals(carVo.getCarVinNumber()))) {
					car.setCarVinNumber(carVo.getCarVinNumber());
				}
				if (carVo.getCarMotorNumber() != null
						&& !("".equals(carVo.getCarMotorNumber()))) {
					car.setCarMotorNumber(carVo.getCarMotorNumber());
				}
				if (carVo.getCarModelName() != null
						&& !("".equals(carVo.getCarModelName()))) {
					car.setCarModelId(carModelDAO.findModelIdByName(carVo
							.getCarModelName()));
				}
				if (carVo.getCarBrandName() != null
						&& !("".equals(carVo.getCarBrandName()))) {
					XsChilddictionary childId = baseTagDAO.findChildId(carVo
							.getCarBrandName(),
							Contstants.BASE_SELL.BASE_CARBRAND,instoreVo.getEnterprise_id());
					if (childId != null && !("".equals(childId))) {
						car.setBrandChild(childId);
					}
				}
				if (carVo.getColorName() != null
						&& !("".equals(carVo.getColorName()))) {
					XsChilddictionary colorId = baseTagDAO
							.findChildId(carVo.getColorName(),
									Contstants.BASE_SELL.BASE_CARCOLOR,instoreVo.getEnterprise_id());
					if (colorId != null && !("".equals(colorId))) {
						car.setColorChild(colorId);
					}
				}
				if (carVo.getInteriorName() != null
						&& !("".equals(carVo.getInteriorName()))) {
					XsChilddictionary interiorId = baseTagDAO.findChildId(carVo
							.getInteriorName(),
							Contstants.BASE_SELL.BASE_ORNAMENTCOLOR,instoreVo.getEnterprise_id());
					if (interiorId != null && !("".equals(interiorId))) {
						car.setInteriorColorChild(interiorId);
					}
				}
				if (carVo.getCarProductionAddress() != null
						&& !("".equals(carVo.getCarProductionAddress()))) {
					car.setCarAddress(carVo.getCarProductionAddress());
				}
				if (carVo.getCarMakeData() != null
						&& !("".equals(carVo.getCarMakeData()))) {
					car.setCarMakeData(carVo.getCarMakeData());
				}
				if (carVo.getCarOcn() != null
						&& !("".equals(carVo.getCarOcn()))) {
					car.setCarOcn(carVo.getCarOcn());
				}
				XsChilddictionary sellState = baseTagDAO.findChildsCon(
						Contstants.SELLSTATE.FORSALE,
						Contstants.SELLSTATE.BASE_SELLSTATE,instoreVo.getEnterprise_id());
				if (sellState != null) {
					car.setSellStateChild(sellState);
				}
				car.setServicingNextdate(sdf.parse(str));
				// 保存车辆档案
				car.setEnterpriseId(instoreVo.getEnterprise_id());
				instorehouseDAO.save(car);
				carInfos.add(car);
				// 保存入库单明细
				XsSellInstorehouseDel del = new XsSellInstorehouseDel();
				del.setInstorehouse(house);
				del.setCarInfo(car);
				Integer instoreType = baseTagDAO.findChildCon(
						Contstants.INSTORETYPE.INSTORE,
						Contstants.BASE_SELL.INSTORETYPE,instoreVo.getEnterprise_id());
				if (instoreType != null) {
					del.setSellAllocatelType(instoreType);
				}
				del.setOrderNumber(carVo.getOrderNumber());
				instorehouseDAO.save(del);
				/*XsSupplierInfo supplier=supplierInfoDAO.get(" from XsSupplierInfo where supplierId="+house.getSupplierId());
				XsChilddictionary type=baseTagDAO.findById(house.getInvoiceType());
				 BasStuff stuf=basStuffDao.get(" from BasStuff where stfId="+house.getPurchaser());
				 String stfName="";
				 if(stuf!=null){
					 stfName=stuf.getStfName();
				}*/
				 setContent("导入【入库单管理】编码为【"+house.getInstorehouseCode()+"】,入库日期为【"+house.getInstorehouseDate()+"】," +
						//"供应商为【"+supplier.getSupplierName()+"】,发票类型为【"+type.getDataValue()+"】," +
						"未税额为【"+house.getNotax()+"】含税额为【"+house.getInstorehouseCode()+"】," +
						"台数为【"+house.getNumber()+"】" 
						/*"采购员为【"+stfName+"】的信息！"*/);
			}

		}

	}

	
	@Log(systemName="销售系统", moduleName="入库单管理",opertype="修改") 
	public void updateInstore(SellInstorehouseVo instoreVo) throws Exception {
		SellInstorehouseVo instorehouse = JSON.parseObject(instoreVo
				.getInstorehousedateGrid(), SellInstorehouseVo.class);
		XsSellInstorehouse house = new XsSellInstorehouse();
		BeanUtils.copyProperties(instorehouse, house);
		house.setStfId(instoreVo.getStfId());
		house.setTax(instorehouse.getTotalTax());
		house.setNotax(instorehouse.getTotalNotax());
		Integer state = baseTagDAO.findChildCon(Contstants.PAYMENTSTATE.UNPAID,
				Contstants.BASE_SELL.PAYMENTSTATE,instoreVo.getEnterprise_id());
		house.setState1(state);
		instorehouseDAO.update(house);
		// 明细
		List<SellInstorehouseVo> insertedList = JSON.parseArray(instoreVo
				.getInserted(), SellInstorehouseVo.class);
		if (insertedList != null && insertedList.size() > 0) {
			for (SellInstorehouseVo delVo : insertedList) {
				XsCarInfo carInfo = (XsCarInfo) instorehouseDAO
						.get("from XsCarInfo car where carId="
								+ delVo.getCarId());
				XsChilddictionary sellState = baseTagDAO.findChildsCon(
						Contstants.SELLSTATE.FORSALE,
						Contstants.SELLSTATE.BASE_SELLSTATE,instoreVo.getEnterprise_id());
				if (sellState != null) {
					carInfo.setSellStateChild(sellState);
				}
				instorehouseDAO.merge(carInfo);
				XsSellInstorehouseDel instoreDel = new XsSellInstorehouseDel();
				BeanUtils.copyProperties(delVo, instoreDel);
				instoreDel.setCarInfo(carInfo);
				instoreDel.setInstorehouse(house);
				// instoreDel.setInstorehouseType(baseTagDAO.findChildId(instoreType));
				instorehouseDAO.save(instoreDel);
				XsSellFlowControl xsfc = new XsSellFlowControl();
				xsfc.setXsfcontrolCarId(delVo.getCarId());
				xsfc
						.setXsfcontrolCode(Contstants.SELLFLOWCONTROLCODE.CONTROLCODE3);
				xsfc.setXsfcontrolDocument(house.getInstorehouseCode());
				xsSellFlowControlDao.save(xsfc);
			}
		}
		List<SellInstorehouseVo> updateList = JSON.parseArray(instoreVo
				.getUpdated(), SellInstorehouseVo.class);
		if (updateList != null && updateList.size() > 0) {
			for (SellInstorehouseVo instoreDel : updateList) {
				XsSellInstorehouseDel del = (XsSellInstorehouseDel) instorehouseDAO
						.get("from XsSellInstorehouseDel  del where del.detailsId="
								+ instoreDel.getDetailsId());
				del.setVehicleCost(instoreDel.getVehicleCost());
				del.setTax(instoreDel.getTax());
				del.setNotax(instoreDel.getNotax());
				del.setChangeMoney(instoreDel.getChangeMoney());
				del.setFreightMoney(instoreDel.getFreightMoney());
				instorehouseDAO.update(del);

			}
		}
		List<SellInstorehouseVo> deleteList = JSON.parseArray(instoreVo
				.getDeleted(), SellInstorehouseVo.class);
		if (deleteList != null && deleteList.size() > 0) {
			for (SellInstorehouseVo instoreDel : deleteList) {
				XsSellInstorehouseDel del = (XsSellInstorehouseDel) instorehouseDAO
						.get("from XsSellInstorehouseDel  del where del.detailsId="
								+ instoreDel.getDetailsId());
				XsCarInfo car = del.getCarInfo();
				XsChilddictionary sellState = baseTagDAO.findChildsCon(
						Contstants.SELLSTATE.SELLADD,
						Contstants.SELLSTATE.BASE_SELLSTATE,instoreVo.getEnterprise_id());
				if (sellState != null) {
					car.setSellStateChild(sellState);
				}
				instorehouseDAO.merge(car);
				instorehouseDAO.delete(del);
				XsSellFlowControl xsfc = xsSellFlowControlDao
						.get(" from XsSellFlowControl where  xsfcontrolCode='"
								+ Contstants.SELLFLOWCONTROLCODE.CONTROLCODE3
								+ "'  and    xsfcontrolCarId=" + car.getCarId());
				xsSellFlowControlDao.delete(xsfc);
				

			}
		}
		
		XsSupplierInfo supplier=supplierInfoDAO.get(" from XsSupplierInfo where supplierId="+house.getSupplierId());
		XsChilddictionary type=baseTagDAO.findById(house.getInvoiceType());
		 BasStuff stuf=basStuffDao.get(" from BasStuff where stfId="+house.getPurchaser());
		String stfName="";
		 if(stuf!=null){
			 stfName=stuf.getStfName();
		}
		 setContent("修改编码为【"+house.getInstorehouseCode()+"】的【入库单管理】信息,入库日期为【"+house.getInstorehouseDate()+"】," +
				"供应商为【"+supplier.getSupplierName()+"】,发票类型为【"+type.getDataValue()+"】," +
				"未税额为【"+house.getNotax()+"】含税额为【"+house.getInstorehouseCode()+"】," +
				"台数为【"+house.getNumber()+"】," +
				"采购员为【"+stfName+"】！");

	}
	@Log(systemName="销售系统", moduleName="入库单管理",opertype="审核") 
	public void updateExamine(SellInstorehouseVo instoreVo) throws Exception {
		XsSellInstorehouse instorehouse = instorehouseDAO.findById(instoreVo
				.getInstorehouseId());
		String contex="";
		int examine = baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE,
				Contstants.BASE_SELL.ADUIT,instoreVo.getEnterprise_id());
		if (instorehouse.getExamineState() == examine) {
			Integer examine1 = baseTagDAO.findChildCon(
					Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,instoreVo.getEnterprise_id());
			if (examine1 != null) {
				instorehouse.setExamineState(examine1);
				contex="审核单号为【"+instorehouse.getInstorehouseCode()+"】的入库单";
				
			}
		} else {
			instorehouse.setExamineState(examine);
			contex="取消审核单号为【"+instorehouse.getInstorehouseCode()+"】的入库单";
		}
		instorehouseDAO.update(instorehouse);
		setContent(""+contex+"");
	}

	/**
	 * 入库单查询
	 */
	
	public DatagridAnalyze findInstore(SellInstorehouseVo instoreVo) throws Exception {
		DatagridAnalyze dg = new DatagridAnalyze();
		List<SellInstorehouseVo> list = new ArrayList<SellInstorehouseVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT SUM(model.xs_model_salesPrice),SUM(del.tax) AS tax1,SUM(del.notax) AS notax1,"
						+ "SUM(del.tax+del.notax), house.instorehouse_code,(SELECT s.xs_supplier_name FROM  xs_supplier_info"
						+ "  s WHERE s.xs_supplier_id=house.xs_supplier_id) AS supplierName,house.instorehouse_date," +
						"house.receipt, COUNT(*)  "
						+ "FROM xs_sell_instorehouse  house  "
						+ " JOIN  xs_sell_instorehouse_del  del  ON  "
						+ "del.instorehouse_id=house.instorehouse_id  " +
						" JOIN xs_sell_flow_control xsfc ON  xsfc.xsfcontrol_document = house.instorehouse_code "
						+ " AND xsfc.xsfcontrol_car_id=del.xs_car_id "
						+ " AND xsfc.xsfcontrol_code="+ Contstants.SELLFLOWCONTROLCODE.CONTROLCODE3+
						"JOIN xs_car_info  car ON car.xs_car_id=del.xs_car_id " +
						" join xs_car_model model on model.xs_model_id = car.xs_car_model_id " +
						" WHERE 1=1"
						+ " and house.enterprise_id="
						+ instoreVo.getEnterprise_id());
		
		
		if (instoreVo.getInstoreStart() != null
					&& !("".equals(instoreVo.getInstoreStart()))) {
				sql.append(" and  DATE(house.instorehouse_date) >= '"
						+ instoreVo.getInstoreStart() + "'");
			}
		if (instoreVo.getInstoreEnd() != null
					&& !("".equals(instoreVo.getInstoreEnd()))) {
				sql.append(" and DATE(house.instorehouse_date) <= '"
						+ instoreVo.getInstoreEnd() + "'");
			
			}
		if((instoreVo.getInstoreStart() == null
				|| "".equals(instoreVo.getInstoreStart()))&&
				(instoreVo.getInstoreEnd() == null
				|| "".equals(instoreVo.getInstoreEnd()))){
				sql.append(" and DATE(house.instorehouse_date) BETWEEN '"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 
							(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,instoreVo.getEnterprise_id()).getCiValue())) + "' " +
									"and '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+ "'");
			}
		
		if (instoreVo.getSupplierId() != null
				&& !("".equals(instoreVo.getSupplierId()))) {
			sql.append("  and house.xs_supplier_id ="
					+ instoreVo.getSupplierId());
		}
		if (instoreVo.getCarBrand() != null
				&& !("".equals(instoreVo.getCarBrand()))) {
			sql.append("  and car.xs_car_brand =" + instoreVo.getCarBrand());
		}
		if (instoreVo.getCarModelId() != null
				&& !("".equals(instoreVo.getCarModelId()))) {
			sql.append("  and car.xs_car_model_id =" + instoreVo.getCarModelId());
		}
		if (instoreVo.getCopyDateStart() != null
				&& !("".equals(instoreVo.getCopyDateStart()))) {
			sql.append(" and car.xs_car_copy_data >= '"
					+ instoreVo.getCopyDateStart() + "'");
		}
		if (instoreVo.getCopyDateEnd() != null
				&& !("".equals(instoreVo.getCopyDateEnd()))) {
			sql.append(" and car.xs_car_copy_data <= '"
					+ instoreVo.getCopyDateEnd() + "'");
		}
		if (instoreVo.getCarVinNumber() != null
				&& !("".equals(instoreVo.getCarVinNumber()))) {
			sql.append("  and car.xs_car_vin_number like '%"
					+ StringEscapeUtils.escapeSql(instoreVo.getCarVinNumber()
							.trim()) + "%'");
		}
		if (instoreVo.getCarOcn() != null
				&& !("".equals(instoreVo.getCarOcn()))) {
			sql.append("  and car.xs_car_ocn  like '%"
					+ StringEscapeUtils.escapeSql(instoreVo.getCarOcn().trim())
					+ "%'");
		}
		if (instoreVo.getWarehouse() != null
				&& !("".equals(instoreVo.getWarehouse()))) {
			sql.append("  and house.warehouse =" + instoreVo.getWarehouse());
		}
		if (instoreVo.getInstorehouseCodes() != null
				&& !("".equals(instoreVo.getInstorehouseCodes()))) {
			sql.append("  and house.instorehouse_code like '%"
					+ StringEscapeUtils.escapeSql(instoreVo
							.getInstorehouseCodes().trim()) + "%'");
		}
		if (instoreVo.getStfId() != null && !("".equals(instoreVo.getStfId()))) {
			sql.append("  and house.purchaser =" + instoreVo.getStfId());
		}
		if (instoreVo.getExamineState() != null
				&& !("".equals(instoreVo.getExamineState()))) {
			sql.append("  and house.examine_state ="
					+ instoreVo.getExamineState());
		}
		if (instoreVo.getState1() != null
				&& !("".equals(instoreVo.getState1()))) {
			sql.append("  and house.state =" + instoreVo.getState1());
		}
		sql.append(" GROUP BY  house.instorehouse_code order by house.instorehouse_code desc");
		List<Object[]> resultList = instorehouseDAO.createSQLQuery(sql
				.toString(), instoreVo.getPage(), instoreVo.getRows());
		List<SellInstorehouseVo> footers=new ArrayList<SellInstorehouseVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SellInstorehouseVo instore = null;
			for (int i = 0; i < resultList.size(); i++) {
				obj = resultList.get(i);
				instore = new SellInstorehouseVo();
				if (obj[0] != null && !("".equals(obj[0]))) {
					instore.setVehicleCost((Double) obj[0]);
				}
				if (obj[1] != null && !("".equals(obj[1]))) {
					instore.setTax((Double) obj[1]);
				}
				if (obj[2] != null && !("".equals(obj[2]))) {
					instore.setNotax((Double) obj[2]);
				}
				if (obj[3] != null && !("".equals(obj[3]))) {
					instore.setSumTax((Double) obj[3]);
				}
				if (obj[4] != null && !("".equals(obj[4]))) {
					instore.setInstorehouseCode( obj[4].toString());
				}
				if (obj[5] != null && !("".equals(obj[5]))) {
					instore.setSupplier(obj[5].toString());
				}
				if (obj[6] != null && !("".equals(obj[6]))) {
					instore.setInstorehouseDate((Timestamp) obj[6]);
				}
				if (obj[7] != null && !("".equals(obj[7]))) {
					instore.setReceipt(obj[7].toString());
				}
				if (obj[8] != null && !("".equals(obj[8]))) {
					instore.setNumber(Integer.parseInt(obj[8].toString()));
				}
				
				instore.setState("closed");
				instore.setIconCls("icon-blank");
				list.add(instore);
			}
			instore = new SellInstorehouseVo();
			instore.setInstorehouseCode("汇总");
			instore.setState("open");
			instore.setIconCls("icon-blank");
			int num=0;;
			Double sum1 = 0.0;
			Double sum2 = 0.0;
			Double sum3 = 0.0;
			Double sum4 = 0.0;
			for (SellInstorehouseVo sell : list) {
				if (sell.getNumber() != null
						&& !"".equals(sell.getNumber())) {
					num += sell.getNumber();
				}
				if (sell.getVehicleCost() != null
						&& !"".equals(sell.getVehicleCost())) {
					sum1 += sell.getVehicleCost();
				}
				if (sell.getTax() != null && !"".equals(sell.getTax())) {
					sum2 += sell.getTax();
				}
				if (sell.getNotax() != null && !"".equals(sell.getNotax())) {
					sum3 += sell.getNotax();
				}
				if (sell.getSumTax() != null && !"".equals(sell.getSumTax())) {
					sum4 += sell.getSumTax();
				}

			}
			instore.setNumber(num);
			instore.setVehicleCost(Utils.doubleFormat(sum1));
			instore.setTax(Utils.doubleFormat(sum2));
			instore.setNotax(Utils.doubleFormat(sum3));
			instore.setSumTax(Utils.doubleFormat(sum4));
			footers.add(instore);

		}
		int total = instorehouseDAO.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setFooter(footers);
		dg.setTotal(total);
		return dg;
	}

	
	public List<SellInstorehouseVo> getTreegridChild(
			SellInstorehouseVo instoreVo) throws Exception {
		List<SellInstorehouseVo> list_ = new ArrayList<SellInstorehouseVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT   model.xs_model_name,(SELECT c.dataValue FROM  xs_childdictionary  c WHERE c.child_id=car.xs_car_brand) AS modelName,"
						+ "car.xs_car_code,car.xs_car_vin_number,car.xs_car_motor_number,car.xs_car_ocn,car.xs_car_color,(SELECT c.dataValue FROM  xs_childdictionary  c "
						+ "WHERE c.child_id=car.xs_car_color) AS colorName,del.tax,del.notax,(del.tax+del.notax) AS sumTax,model.xs_model_salesPrice "
						+ "FROM xs_sell_instorehouse  house"
						+ "  JOIN  xs_sell_instorehouse_del  del ON "
						+ "del.instorehouse_id=house.instorehouse_id "
						+ " JOIN xs_car_info  car ON car.xs_car_id=del.xs_car_id " +
						"join xs_car_model model on model.xs_model_id = car.xs_car_model_id " +
						"JOIN xs_sell_flow_control xsfc ON  xsfc.xsfcontrol_document = house.instorehouse_code "
						+ " AND xsfc.xsfcontrol_car_id=del.xs_car_id "
						+ " AND xsfc.xsfcontrol_code="+ Contstants.SELLFLOWCONTROLCODE.CONTROLCODE3+
						"WHERE house.instorehouse_code='"+ instoreVo.getInstorehouseCode()+"'" +
						" and house.enterprise_id="
						+ instoreVo.getEnterprise_id());
	
		if (instoreVo.getInstoreStart() != null
					&& !("".equals(instoreVo.getInstoreStart()))) {
				sql.append(" and  DATE(house.instorehouse_date) >= '"
						+ instoreVo.getInstoreStart() + "'");
			}
		if (instoreVo.getInstoreEnd() != null
					&& !("".equals(instoreVo.getInstoreEnd()))) {
				sql.append(" and DATE(house.instorehouse_date) <= '"
						+ instoreVo.getInstoreEnd() + "'");
			
			
			}
		if((instoreVo.getInstoreStart()== null
				|| "".equals(instoreVo.getInstoreStart()))&&(instoreVo.getInstoreEnd()== null
						|| "".equals(instoreVo.getInstoreEnd()))){
				sql.append(" and DATE(house.instorehouse_date) BETWEEN '"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 
							(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,instoreVo.getEnterprise_id()).getCiValue())) + "' " +
									"and '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+ "'");
			}
	
	if (instoreVo.getSupplierId() != null
			&& !("".equals(instoreVo.getSupplierId()))) {
		sql.append("  and house.xs_supplier_id ="
				+ instoreVo.getSupplierId());
	}
	if (instoreVo.getCarBrand() != null
			&& !("".equals(instoreVo.getCarBrand()))) {
		sql.append("  and car.xs_car_brand =" + instoreVo.getCarBrand());
	}
	if (instoreVo.getCarModelId() != null
			&& !("".equals(instoreVo.getCarModelId()))) {
		sql.append("  and car.xs_car_model_id =" + instoreVo.getCarModelId());
	}
	if (instoreVo.getCopyDateStart() != null
			&& !("".equals(instoreVo.getCopyDateStart()))) {
		sql.append(" and car.xs_car_copy_data >= '"
				+ instoreVo.getCopyDateStart() + "'");
	}
	if (instoreVo.getCopyDateEnd() != null
			&& !("".equals(instoreVo.getCopyDateEnd()))) {
		sql.append(" and car.xs_car_copy_data <= '"
				+ instoreVo.getCopyDateEnd() + "'");
	}
	if (instoreVo.getCarVinNumber() != null
			&& !("".equals(instoreVo.getCarVinNumber()))) {
		sql.append("  and car.xs_car_vin_number like '%"
				+ StringEscapeUtils.escapeSql(instoreVo.getCarVinNumber()
						.trim()) + "%'");
	}
	if (instoreVo.getCarOcn() != null
			&& !("".equals(instoreVo.getCarOcn()))) {
		sql.append("  and car.xs_car_ocn  like '%"
				+ StringEscapeUtils.escapeSql(instoreVo.getCarOcn().trim())
				+ "%'");
	}
	if (instoreVo.getWarehouse() != null
			&& !("".equals(instoreVo.getWarehouse()))) {
		sql.append("  and house.warehouse =" + instoreVo.getWarehouse());
	}
	/*if (instoreVo.getInstorehouseCode() != null
			&& !("".equals(instoreVo.getInstorehouseCode()))) {
		sql.append("  and house.instorehouse_code like '%"
				+ StringEscapeUtils.escapeSql(instoreVo
						.getInstorehouseCode().trim()) + "%'");
	}*/
	if (instoreVo.getStfId() != null && !("".equals(instoreVo.getStfId()))) {
		sql.append("  and house.purchaser =" + instoreVo.getStfId());
	}
	if (instoreVo.getExamineState() != null
			&& !("".equals(instoreVo.getExamineState()))) {
		sql.append("  and house.examine_state ="
				+ instoreVo.getExamineState());
	}
	if (instoreVo.getState1() != null
			&& !("".equals(instoreVo.getState1()))) {
		sql.append("  and house.state =" + instoreVo.getState1());
	}

		List<Object[]> resultList = instorehouseDAO.createSQLQuery(sql
				.toString());
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				Object[] obj = resultList.get(i);
				SellInstorehouseVo instore = new SellInstorehouseVo();
				
				if (obj[0] != null && !("".equals(obj[0]))) {
					instore.setCarModelName(obj[0].toString());
				}
				if (obj[1] != null && !("".equals(obj[01]))) {
					instore.setCarBrandName( obj[1].toString());
				}
				if (obj[2] != null && !("".equals(obj[2]))) {
					instore.setInstorehouseCode(obj[2].toString());
				}
				if (obj[3] != null && !("".equals(obj[3]))) {
					instore.setCarVinNumber(obj[3].toString());
				}
				if (obj[4] != null && !("".equals(obj[4]))) {
					instore.setCarMotorNumber(obj[4].toString());
				}
				if (obj[5] != null && !("".equals(obj[5]))) {
					instore.setCarOcn(obj[5].toString());
				}
				if (obj[6] != null && !("".equals(obj[6]))) {
					instore.setCarColor((Integer) obj[6]);
				}
				if (obj[7] != null && !("".equals(obj[7]))) {
					instore.setColorName(obj[7].toString());
				}
				if (obj[8] != null && !("".equals(obj[8]))) {
					instore.setTax((Double) obj[8]);
				}
				if (obj[9] != null && !("".equals(obj[9]))) {
					instore.setNotax((Double) obj[9]);
				}
				if (obj[10] != null && !("".equals(obj[10]))) {
					instore.setSumTax((Double) obj[10]);
				}
				if (obj[11] != null && !("".equals(obj[11]))) {
					instore.setVehicleCost((Double) obj[11]);
				}
				instore.setIconCls("icon-blank");
				instore.setState("open");
				list_.add(instore);
			}
		}
		return list_;
	}
	//销售作业：车辆档案查询
	
	public Datagrid getQueryData(SellInstorehouseVo instoreVo) throws Exception {
		Datagrid dg = new Datagrid();
		List<SellInstorehouseVo> list = new ArrayList<SellInstorehouseVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT car.xs_car_code,car.xs_car_vin_number,car.xs_car_licenseName,car.xs_car_motor_number,car.xs_car_brand,"
						+ "(SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id=car.xs_car_brand) AS car_brand_name,car.xs_car_model_id,"
						+ "(SELECT m.xs_model_name FROM xs_car_model m WHERE m.xs_model_id=car.xs_car_model_id) AS car_model_name,custom.xs_custom_name,"
						+ "house.xs_supplier_id,(SELECT  xs_supplier_name FROM   xs_supplier_info s WHERE s.xs_supplier_id=house.xs_supplier_id) AS suppier_name,"
						+ "car.xs_car_make_data,house.instorehouse_date,car.xs_car_color,(SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id=car.xs_car_color) AS car_color_name,"
						+ "car.xs_car_sell_state,(SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id=car.xs_car_sell_state) AS sell_state_name,sell.xs_car_sel_data,"
						+ "sell.xs_car_sel_transaction_money,insurance.insurer_end_date,insurance.insurer_,(SELECT i.xs_insurer_name FROM xs_insurer_info i WHERE i.xs_insurer_id=insurance.insurer_) AS insurer_name,"
						+ "custom.xs_custom_address,custom.xs_custom_phone,custom.xs_custom_telephone,car.xs_car_InteriorColor,(SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id=car.xs_car_InteriorColor) AS InteriorColor_name,"
						+ "car.xs_car_tradeCheck_bill,certificate.xs_car_certificate, custom.xs_custom_inputdata,car.xs_car_licensePlate,car.xs_car_production_address,     "
						+ "insurance.Insurance_agent,insurance.insurance_policy ,  car.xs_distributor_id,"
                        +"(SELECT   d.xs_distributor_name FROM  xs_distributor_info   d   WHERE d.xs_distributor_id=car.xs_distributor_id) AS distributorName  "
						+ "FROM  xs_car_sell_info  sell   " +
						" JOIN  xs_custom_info custom  ON  sell.custom_id=custom.custom_id " +
						" JOIN xs_car_info  car  ON sell.xs_car_id=car.xs_car_id  " +
						" JOIN xs_sell_instorehouse_del del ON car.xs_car_id=del.xs_car_id" +
						" JOIN  xs_sell_instorehouse house ON house.instorehouse_id=del.instorehouse_id  " +
						" LEFT JOIN xs_sell_insurance insurance ON insurance.xs_car_sel_id=sell.xs_car_sel_id " +
						" LEFT JOIN  xs_sell_certificate  certificate  ON  certificate.xs_car_id=car.xs_car_id"+
						"  WHERE 1=1 " );

		if (instoreVo.getEnterprise_id() != null
				&& !("".equals(instoreVo.getEnterprise_id()))) {
			sql.append(" and  sell.enterprise_id="
						+ instoreVo.getEnterprise_id());
			
		}
		if (instoreVo.getQueryVinNumber() != null
				&& !("".equals(instoreVo.getQueryVinNumber()))) {
			sql.append("	and car.xs_car_vin_number like '%"
					+ StringEscapeUtils.escapeSql(instoreVo.getQueryVinNumber()
							.trim()) + "%'");
		}
		if (instoreVo.getCarOcn() != null
				&& !("".equals(instoreVo.getCarOcn()))) {
			sql.append("	and car.xs_car_ocn like '%"
					+ StringEscapeUtils.escapeSql(instoreVo.getCarOcn().trim())
					+ "%'");
		}
		if (instoreVo.getCustomName() != null
				&& !("".equals(instoreVo.getCustomName()))) {
			sql.append("	and custom.xs_custom_name like '%"
					+ StringEscapeUtils.escapeSql(instoreVo.getCustomName()
							.trim()) + "%'");
		}
		//销售日期
		if (instoreVo.getCarSelData() != null
				&& !instoreVo.getCarSelData().equals("")) {
					sql.append(" and DATE(sell.xs_car_sel_data) >= '" +instoreVo.getCarSelData() + "'");
				} 
		if (instoreVo.getCarSelData2() != null
				&& !instoreVo.getCarSelData2().equals("")) {
					sql.append(" and DATE(sell.xs_car_sel_data) <= '" +instoreVo.getCarSelData2() + "'");
		}
		if((instoreVo.getCarSelData() == null
				|| instoreVo.getCarSelData().equals("")) 
				&& (instoreVo.getCarSelData2() == null
				|| instoreVo.getCarSelData2().equals(""))){
			sql.append(" and  DATE(sell.xs_car_sel_data) " +
					"BETWEEN '" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,instoreVo.getEnterprise_id()).getCiValue()))
					+ "' AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
		}
		
		if (instoreVo.getCustomName() != null
				&& !("".equals(instoreVo.getCustomName()))) {
			sql.append("	and custom.xs_custom_name like '%"
					+ StringEscapeUtils.escapeSql(instoreVo.getCustomName()
							.trim()) + "%'");
		}
		if (instoreVo.getSellState() != null
				&& !("".equals(instoreVo.getSellState()))) {
			sql.append("	and car.xs_car_sell_state='"
					+ instoreVo.getSellState() + "'");
		}
		if (instoreVo.getCarInteriorColor() != null
				&& !("".equals(instoreVo.getCarInteriorColor()))) {
			sql.append("	and car.xs_car_InteriorColor='"
					+ instoreVo.getCarInteriorColor() + "'");
		}
		if (instoreVo.getQuerySupplier() != null
				&& !("".equals(instoreVo.getQuerySupplier()))) {
			sql.append("	and house.xs_supplier_id='"
					+ instoreVo.getQuerySupplier() + "'");
		}
		//入库日期
		if (instoreVo.getQueryInstoreDate() != null
				&& !instoreVo.getQueryInstoreDate().equals("")) {
					sql.append(" and DATE(house.instorehouse_date) >= '" +instoreVo.getQueryInstoreDate() + "'");
				} 
		if (instoreVo.getQueryInstoreDate2() != null
				&& !instoreVo.getQueryInstoreDate2().equals("")) {
					sql.append(" and DATE(house.instorehouse_date) <= '" +instoreVo.getQueryInstoreDate2() + "'");
		}
		if (instoreVo.getQueryBrand() != null
				&& !("".equals(instoreVo.getQueryBrand()))) {
			sql.append("	and car.xs_car_brand='" + instoreVo.getQueryBrand()
					+ "'");
		}
		if (instoreVo.getQueryCarModel() != null
				&& !("".equals(instoreVo.getQueryCarModel()))) {
			sql.append("	and car.xs_car_model_id='"
					+ instoreVo.getQueryCarModel() + "'");
		}
		if (instoreVo.getPersonId() != null
				&& !("".equals(instoreVo.getPersonId()))) {
			sql.append("	and custom.STF_ID='"
					+ instoreVo.getPersonId() + "'");
		}
		 
		 sql.append("	GROUP BY car.xs_car_code");
		List<Object[]> resultList = instorehouseDAO.createSQLQuery(sql
				.toString(), instoreVo.getPage(), instoreVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				Object[] obj = resultList.get(i);
				SellInstorehouseVo instore = new SellInstorehouseVo();
				if (obj[0] != null && !("".equals(obj[0]))) {
					instore.setCarCode(obj[0].toString());
				}
				if (obj[1] != null && !("".equals(obj[1]))) {
					instore.setCarVinNumber(obj[1].toString());
				}
				if (obj[2] != null && !("".equals(obj[2]))) {
					instore.setCarLicenseName(obj[2].toString());
				}
				if (obj[3] != null && !("".equals(obj[3]))) {
					instore.setCarMotorNumber(obj[3].toString());
				}
				if (obj[4] != null && !("".equals(obj[4]))) {
					instore.setCarBrand((Integer) obj[4]);
				}
				if (obj[5] != null && !("".equals(obj[5]))) {
					instore.setCarBrandName(obj[5].toString());
				}
				if (obj[6] != null && !("".equals(obj[6]))) {
					instore.setCarModelId((Integer) obj[6]);
				}
				if (obj[7] != null && !("".equals(obj[7]))) {
					instore.setCarModelName(obj[7].toString());
				}
				if (obj[8] != null && !("".equals(obj[8]))) {
					instore.setCustomName(obj[8].toString());
				}
				if (obj[9] != null && !("".equals(obj[9]))) {
					instore.setSupplierId((Integer) obj[9]);
				}
				if (obj[10] != null && !("".equals(obj[10]))) {
					instore.setSupplier(obj[10].toString());
				}
				if (obj[11] != null && !("".equals(obj[11]))) {
					instore.setCarMakeData((Date) obj[11]);
				}
				if (obj[12] != null && !("".equals(obj[12]))) {
					instore.setInstorehouseDate((Timestamp) obj[12]);
				}

				if (obj[13] != null && !("".equals(obj[13]))) {
					instore.setCarColor((Integer) obj[13]);
				}
				if (obj[14] != null && !("".equals(obj[14]))) {
					instore.setColorName(obj[14].toString());
				}
				if (obj[15] != null && !("".equals(obj[15]))) {
					instore.setSellState((Integer) obj[15]);
				}
				if (obj[16] != null && !("".equals(obj[16]))) {
					instore.setSellStateName(obj[16].toString());
				}
				if (obj[17] != null && !("".equals(obj[17]))) {
					instore.setCarSelData(obj[17].toString());
				}
				if (obj[18] != null && !("".equals(obj[18]))) {
					instore.setSelTransactionMoney((Double) obj[18]);
				}
				if (obj[19] != null && !("".equals(obj[19]))) {
					instore.setInsurerEndDate(obj[19].toString());
				}
				if (obj[20] != null && !("".equals(obj[20]))) {
					instore.setInsurerId((Integer) obj[20]);
				}
				if (obj[21] != null && !("".equals(obj[21]))) {
					instore.setInsurerName(obj[21].toString());
				}
				if (obj[22] != null && !("".equals(obj[22]))) {
					instore.setCustomAddress(obj[22].toString());
				}
				if (obj[23] != null && !("".equals(obj[23]))) {
					instore.setCustomPhone(obj[23].toString());
				}

				if (obj[24] != null && !("".equals(obj[24]))) {
					instore.setCustomTelephone(obj[24].toString());
				}
				if (obj[25] != null && !("".equals(obj[25]))) {
					instore.setCarInteriorColor((Integer) obj[25]);
				}
				if (obj[26] != null && !("".equals(obj[26]))) {
					instore.setInteriorName(obj[26].toString());
				}
				if (obj[27] != null && !("".equals(obj[27]))) {
					instore.setCarTradeCheckBill(obj[27].toString());
				}
				if (obj[28] != null && !("".equals(obj[28]))) {
					instore.setCarCertificate(obj[28].toString());
				}
				if (obj[29] != null && !("".equals(obj[29]))) {
					instore.setCustomInputdata(obj[29].toString());
				}
				if (obj[30] != null && !("".equals(obj[30]))) {
					instore.setCarLicensePlate(obj[30].toString());
				}
				if (obj[31] != null && !("".equals(obj[31]))) {
					instore.setCarProductionAddress(obj[31].toString());
				}
				if (obj[32] != null && !("".equals(obj[32]))) {
					instore.setInsuranceAgent(obj[32].toString());
				}
				if (obj[33] != null && !("".equals(obj[33]))) {
					instore.setInsurancePolicy(obj[33].toString());
				}
				if (obj[35] != null && !("".equals(obj[35]))) {
					instore.setDistributorName(obj[35].toString());
				}
				list.add(instore);
			}
		}
		int total = instorehouseDAO.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	
	public List<ComboBoxJson> findAllInstore(SellInstorehouseVo instoreVo)
			throws Exception {
		List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		List<Object[]> bctList = instorehouseDAO
				.createSQLQuery("select instorehouse_id,instorehouse_code from xs_sell_instorehouse "
						+ "where enterprise_id=" + instoreVo.getEnterprise_id());
		if (bctList != null && bctList.size() > 0) {
			for (Object[] obj : bctList) {
				ComboBoxJson json = new ComboBoxJson();
				json.setId(obj[0].toString());
				json.setText(obj[1].toString());
				list.add(json);
			}
		}
		return list;
	}

	/**
	 * 判断是否已审核
	 * */
	
	public Boolean isRefundment(SellInstorehouseVo sellInstorehouseVo)
			throws Exception {
		Integer examine = baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE,
				Contstants.BASE_SELL.ADUIT,sellInstorehouseVo.getEnterprise_id());
		if (sellInstorehouseVo.getExamineState()==examine)
			return true;
		return false;
	}

	@Override
	public Boolean isfpType(SellInstorehouseVo sellInstorehouseVo)
			throws Exception {
		int type = baseTagDAO.findChildCon(Contstants.BASE_SELL.zzfp,
				Contstants.BASE_SELL.INVOICETYPE,sellInstorehouseVo.getEnterprise_id());//增值发票
		if(sellInstorehouseVo.getInvoiceType().intValue()==type)
			return true;
		return false;
	}

	@Override
	public Integer isfpTypeId(SellInstorehouseVo sellInstorehouseVo)
			throws Exception {
		return baseTagDAO.findChildCon(Contstants.BASE_SELL.zzfp,
				Contstants.BASE_SELL.INVOICETYPE,sellInstorehouseVo.getEnterprise_id());//增值发票
	}
	
}
