package com.syuesoft.sell.base.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.dao.CarInfoDAO;
import com.syuesoft.sell.base.dao.CarModelDAO;
import com.syuesoft.sell.base.dao.DistributorInfoDAO;
import com.syuesoft.sell.base.dao.SupplierInfoDAO;
import com.syuesoft.sell.base.service.CarInfoService;
import com.syuesoft.sell.base.vo.CarInfoVo;
import com.syuesoft.sell.control.dao.XsSellFlowControlDao;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsCarModel;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsDistributorInfo;
import com.syuesoft.sell.model.XsSellAttachment;
import com.syuesoft.sell.model.XsSellFlowControl;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Msg;


@Service("carInfoService")
public class CarInfoServiceImpl extends BaseLogServiceImpl   implements CarInfoService {
	@Autowired
	private CarInfoDAO carInfoDAO;
	@Autowired
	private BaseTagDAO baseTagDAO;
	@Autowired
	private CarModelDAO carModelDAO;
	
	@Autowired
	private DistributorInfoDAO distributorInfoDAO;
	@Autowired
	private XsSellFlowControlDao xsSellFlowControlDao;
	
	
	public Datagrid getPager(CarInfoVo carInfoVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsCarInfo carInfo where 1=1" +
				" and carInfo.enterpriseId="+carInfoVo.getEnterpriseId());

		addWhere( hql,  carInfoVo);
		
		List<BaseBean> lst = carInfoDAO.find(hql.toString(), carInfoVo.getPage(), carInfoVo.getRows());
		List<CarInfoVo> rows =new ArrayList<CarInfoVo>();
		if(lst!=null && lst.size()>0){
			for(BaseBean b:lst){
				XsCarInfo carInfo=(XsCarInfo)b;
				CarInfoVo cVo=new CarInfoVo();
				//BeanUtils.copyProperties(carInfo, cVo);	
				cVo.setCarId(carInfo.getCarId());
				if(carInfo.getBrandChild()!=null){
					cVo.setCarBrand(carInfo.getBrandChild().getChildId());
				}
				if(carInfo.getColorChild()!=null){
					cVo.setCarColor(carInfo.getColorChild().getChildId());
				}
				if(carInfo.getInteriorColorChild()!=null){
					cVo.setCarInteriorColor(carInfo.getInteriorColorChild().getChildId());
				}
				cVo.setCarModelId(carInfo.getCarModelId());
				cVo.setCarOptional(carInfo.getCarOptional());
				cVo.setCarMortgageState(carInfo.getCarMortgageState());
				if(carInfo.getCertificateChild()!=null){
					cVo.setCarCertificateState(carInfo.getCertificateChild().getChildId());
				}
				if(carInfo.getCarTypeChild()!=null){
					cVo.setCarType(carInfo.getCarTypeChild().getChildId());
				}
				cVo.setCarCode(carInfo.getCarCode());
				cVo.setCarLicensePlate(carInfo.getCarLicensePlate());
				cVo.setCarLicenseName(carInfo.getCarLicenseName());
				cVo.setCarVinNumber(carInfo.getCarVinNumber());
				cVo.setCarMotorNumber(carInfo.getCarMotorNumber());
				cVo.setCarOcn(carInfo.getCarOcn());
				cVo.setCarCertificate(carInfo.getCarCertificate());
				cVo.setCarMakeData((Timestamp)carInfo.getCarMakeData());
				cVo.setCarUnlockingKeyNumber(carInfo.getCarUnlockingKeyNumber());
				cVo.setCarRideLimitNumber(carInfo.getCarRideLimitNumber());
				cVo.setCarTradeCheckBill(carInfo.getCarTradeCheckBill());
				cVo.setCarProductionAddress(carInfo.getCarProductionAddress());
				cVo.setCarAddress(carInfo.getCarAddress());
				cVo.setCarCopyData(carInfo.getCarCopyData());
				cVo.setCarAssembleData(carInfo.getCarAssembleData());
				cVo.setCarEndCheckData(carInfo.getCarEndCheckData());
				cVo.setCarFristInsuranceData(carInfo.getCarFristInsuranceData());
				cVo.setCarForecastData(carInfo.getCarForecastData());
				cVo.setCarPrice(carInfo.getCarPrice());
				cVo.setDistributorId(carInfo.getDistributorId());
				if(carInfo.getSellStateChild()!=null){
					cVo.setCarSellState(carInfo.getSellStateChild().getChildId());
				}
				if(carInfo.getDistributChild()!=null){
					cVo.setCarDistributState(carInfo.getDistributChild().getChildId());
				}
				if(carInfo.getFixStatusChild()!=null){
					cVo.setFixStatus(carInfo.getFixStatusChild().getChildId());
				}
				cVo.setUpData(carInfo.getUpData());
				cVo.setUpPerson(carInfo.getUpPerson());
				if(carInfo.getNormsChild()!=null){
					cVo.setNorms(carInfo.getNormsChild().getChildId());
				}
				if(carInfo.getToolCaseChild()!=null){
					cVo.setToolCase(carInfo.getToolCaseChild().getChildId());
				}
				cVo.setProveFile(carInfo.getProveFile());
				cVo.setRzBook(carInfo.getRzBook());
				if(carInfo.getFootdChild()!=null){
					cVo.setFootd(carInfo.getFootdChild().getChildId());
				}
				cVo.setEnterpriseId(carInfo.getEnterpriseId());
				
				setBean(carInfo,cVo);
				rows.add(cVo);
			}
		}
		int total = carInfoDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	public Datagrid getSellState(CarInfoVo carInfoVo) throws Exception{
			Datagrid dg = new Datagrid();
			Integer sellState=baseTagDAO.findChildCon(Contstants.SELLSTATE.SELLADD, Contstants.SELLSTATE.BASE_SELLSTATE,carInfoVo.getEnterpriseId());
			Integer back=baseTagDAO.findChildCon(Contstants.SELLSTATE.CARBACK, Contstants.SELLSTATE.BASE_SELLSTATE,carInfoVo.getEnterpriseId());
			Integer aduit=baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.ADUIT.ADUIT,carInfoVo.getEnterpriseId());
			StringBuffer sql=new StringBuffer();
			if(sellState!=null && back!=null){	
				sql.append("SELECT car.xs_car_id,car.xs_car_code,car.xs_car_vin_number,car.xs_car_ocn,car.xs_car_brand,(SELECT c.dataValue  FROM  xs_childdictionary c WHERE c.child_id=car.xs_car_brand) AS brand_name,"+
				 	 "car.xs_car_model_id,model.xs_model_name,car.xs_car_color,(SELECT c.dataValue  FROM  xs_childdictionary c WHERE c.child_id=car.xs_car_color) AS color_name,car.xs_car_sell_state,"+
                     "(SELECT c.dataValue  FROM  xs_childdictionary c WHERE c.child_id=car.xs_car_sell_state) AS sell_state_name,car.xs_car_distribut_state,"+
				 	 "(SELECT c.dataValue  FROM  xs_childdictionary c WHERE c.child_id=car.xs_car_distribut_state) AS distribut_state_name,car.xs_car_licenseName,"+
				 	 "car.xs_car_motor_number,model.xs_model_costPrice " +
				 	 "FROM  xs_car_info car " +
				 	 " join xs_car_model model on car.xs_car_model_id=model.xs_model_id " +
				 	 "  LEFT OUTER JOIN (SELECT ss.* FROM (" +
				 	 "	SELECT del.xs_car_id,xsr.* FROM xs_sell_retreat xsr " +
				 	 "INNER JOIN xs_sell_instorehouse_del del ON del.details_id=xsr.details_id  " +
				 	 "ORDER BY  xsr.retreat_date DESC) ss GROUP BY ss.xs_car_id " +
				 	 "	) rs" +
				 	 " ON rs.xs_car_id = car.xs_car_id" +
				 	 "  where " +
				 	  "  ( car.xs_car_sell_state ="+sellState+"" +
				 	  		" OR (car.xs_car_sell_state ="+back+" AND rs.examine="+aduit+")) " +
				 	  		"and car.enterprise_id="+carInfoVo.getEnterpriseId());
				if(carInfoVo.getVinNumber()!=null && !("".equals(carInfoVo.getVinNumber()))){
					sql.append(" and car.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(carInfoVo.getVinNumber().trim())+"%'");
				}
				if(carInfoVo.getQueryBrand()!=null && !("".equals(carInfoVo.getQueryBrand()))){
					sql.append(" and car.xs_car_brand ="+carInfoVo.getQueryBrand());
				}
				if(carInfoVo.getQueryModel()!=null && !("".equals(carInfoVo.getQueryModel()))){
					sql.append(" and car.xs_car_model_id ="+carInfoVo.getQueryModel());
				}
				if(carInfoVo.getQueryColor()!=null && !("".equals(carInfoVo.getQueryColor()))){
					sql.append(" and car.xs_car_color ="+carInfoVo.getQueryColor());
				}
				List<Object[]> lst=carInfoDAO.createSQLQuery(sql.toString(),carInfoVo.getPage(), carInfoVo.getRows());
				List<CarInfoVo> rows =new ArrayList<CarInfoVo>();
				if(lst!=null && lst.size()>0){
					for(Object[] b:lst){
						CarInfoVo cVo=new CarInfoVo();
						if(b[0]!=null && !("".equals(b[0]))){
							cVo.setCarId((Integer)b[0]);
						}
						if(b[1]!=null && !("".equals(b[1]))){
							cVo.setCarCode(b[1].toString());
						}
						if(b[2]!=null && !("".equals(b[2]))){
							cVo.setCarVinNumber(b[2].toString());
						}
						if(b[3]!=null && !("".equals(b[3]))){
							cVo.setCarOcn(b[3].toString());
						}
						if(b[4]!=null && !("".equals(b[4]))){
							cVo.setCarBrand((Integer)b[4]);
						}
						if(b[5]!=null && !("".equals(b[5]))){
							cVo.setCarBrandName(b[5].toString());
						}
						if(b[6]!=null && !("".equals(b[6]))){
							cVo.setCarModelId((Integer)b[6]);
						}
						if(b[7]!=null && !("".equals(b[7]))){
							cVo.setCarModelName(b[7].toString());
						}
						if(b[8]!=null && !("".equals(b[8]))){
							cVo.setCarColor((Integer)b[8]);
						}
						if(b[9]!=null && !("".equals(b[9]))){
							cVo.setColorName(b[9].toString());
						}
						if(b[10]!=null && !("".equals(b[10]))){
							cVo.setCarSellState((Integer)b[10]);
						}
						if(b[11]!=null && !("".equals(b[11]))){
							cVo.setSellStateName(b[11].toString());
						}
						if(b[12]!=null && !("".equals(b[12]))){
							cVo.setCarDistributState((Integer)b[12]);
						}
						if(b[13]!=null && !("".equals(b[13]))){
							cVo.setDistributStateName(b[13].toString());
						}
						if(b[14]!=null && !("".equals(b[14]))){
							cVo.setCarLicenseName(b[14].toString());
						}
						if(b[15]!=null && !("".equals(b[15]))){
							cVo.setCarMotorNumber(b[15].toString());
						}
						if(b[16]!=null && !("".equals(b[16]))){
							cVo.setVehicleCost((Double)b[16]);
						}
						rows.add(cVo);
					}
				}
				int total = carInfoDAO.getCountBySQL(sql.toString());
				dg.setRows(rows);
				dg.setTotal(total);
			
		}
			return dg;
	}
			
			
	
	
	public Datagrid getCarInstore(CarInfoVo carInfoVo) throws Exception{
			Datagrid dg = new Datagrid();
			Integer sellState=baseTagDAO.findChildCon(Contstants.SELLSTATE.FORSALE, Contstants.SELLSTATE.BASE_SELLSTATE,carInfoVo.getEnterpriseId());
			String hql="";
			if(sellState!=null){
				 hql="from XsCarInfo carInfo where 1=1  and  carInfo.sellStateChild.childId in ='"+sellState+"' and carInfo.enterpriseId="+carInfoVo.getEnterpriseId() ;	
				 List<BaseBean> lst=carInfoDAO.find(hql,carInfoVo.getPage(), carInfoVo.getRows());
					List<CarInfoVo> rows =new ArrayList<CarInfoVo>();
					if(lst!=null && lst.size()>0){
						for(BaseBean b:lst){
							XsCarInfo carInfo=(XsCarInfo)b;	
							CarInfoVo cVo=new CarInfoVo();
							cVo.setCarId(carInfo.getCarId());
							if(carInfo.getBrandChild()!=null){
								cVo.setCarBrand(carInfo.getBrandChild().getChildId());
							}
							if(carInfo.getColorChild()!=null){
								cVo.setCarColor(carInfo.getColorChild().getChildId());
							}
							if(carInfo.getInteriorColorChild()!=null){
								cVo.setCarInteriorColor(carInfo.getInteriorColorChild().getChildId());
							}
							cVo.setCarModelId(carInfo.getCarModelId());
							cVo.setCarOptional(carInfo.getCarOptional());
							cVo.setCarMortgageState(carInfo.getCarMortgageState());
							if(carInfo.getCertificateChild()!=null){
								cVo.setCarCertificateState(carInfo.getCertificateChild().getChildId());
							}
							if(carInfo.getCarTypeChild()!=null){
								cVo.setCarType(carInfo.getCarTypeChild().getChildId());
							}
							cVo.setCarCode(carInfo.getCarCode());
							cVo.setCarLicensePlate(carInfo.getCarLicensePlate());
							cVo.setCarLicenseName(carInfo.getCarLicenseName());
							cVo.setCarVinNumber(carInfo.getCarVinNumber());
							cVo.setCarMotorNumber(carInfo.getCarMotorNumber());
							cVo.setCarOcn(carInfo.getCarOcn());
							cVo.setCarCertificate(carInfo.getCarCertificate());
							cVo.setCarMakeData((Timestamp)carInfo.getCarMakeData());
							cVo.setCarUnlockingKeyNumber(carInfo.getCarUnlockingKeyNumber());
							cVo.setCarRideLimitNumber(carInfo.getCarRideLimitNumber());
							cVo.setCarTradeCheckBill(carInfo.getCarTradeCheckBill());
							cVo.setCarProductionAddress(carInfo.getCarProductionAddress());
							cVo.setCarAddress(carInfo.getCarAddress());
							cVo.setCarCopyData(carInfo.getCarCopyData());
							cVo.setCarAssembleData(carInfo.getCarAssembleData());
							cVo.setCarEndCheckData(carInfo.getCarEndCheckData());
							cVo.setCarFristInsuranceData(carInfo.getCarFristInsuranceData());
							cVo.setCarForecastData(carInfo.getCarForecastData());
							cVo.setCarPrice(carInfo.getCarPrice());
							cVo.setDistributorId(carInfo.getDistributorId());
							if(carInfo.getSellStateChild()!=null){
								cVo.setCarSellState(carInfo.getSellStateChild().getChildId());
							}
							if(carInfo.getDistributChild()!=null){
								cVo.setCarDistributState(carInfo.getDistributChild().getChildId());
							}
							if(carInfo.getFixStatusChild()!=null){
								cVo.setFixStatus(carInfo.getFixStatusChild().getChildId());
							}
							cVo.setUpData(carInfo.getUpData());
							cVo.setUpPerson(carInfo.getUpPerson());
							if(carInfo.getNormsChild()!=null){
								cVo.setNorms(carInfo.getNormsChild().getChildId());
							}
							if(carInfo.getToolCaseChild()!=null){
								cVo.setToolCase(carInfo.getToolCaseChild().getChildId());
							}
							cVo.setProveFile(carInfo.getProveFile());
							cVo.setRzBook(carInfo.getRzBook());
							if(carInfo.getFootdChild()!=null){
								cVo.setFootd(carInfo.getFootdChild().getChildId());
							}
							cVo.setEnterpriseId(carInfo.getEnterpriseId());
							setBean(carInfo,cVo);
							rows.add(cVo);
						}
					}
					int total = carInfoDAO.getCount(hql);
					dg.setRows(rows);
					dg.setTotal(total);
			}
			
			return dg;
	}
	public void addWhere(StringBuffer hql, CarInfoVo carInfoVo) throws Exception{
		if(carInfoVo.getVinNumber()!=null && !("".equals(carInfoVo.getVinNumber()))){
			hql.append(" and carInfo.carVinNumber like '%"+StringEscapeUtils.escapeSql(carInfoVo.getVinNumber().trim())+"%'");
		}
		if(carInfoVo.getInteriorColor()!=null && !("".equals(carInfoVo.getInteriorColor()))){
			hql.append(" and carInfo.carInteriorColor = '"+carInfoVo.getInteriorColor()+"%'");
		}
		if(carInfoVo.getCopyDataStart()!=null && !("".equals(carInfoVo.getCopyDataStart())) ){
			hql.append(" and carInfo.carCopyData >= '"+carInfoVo.getCopyDataStart()+"'");
		}
		if(carInfoVo.getCopyDataEnd()!=null && !("".equals(carInfoVo.getCopyDataEnd()))){
			hql.append(" and carInfo.carCopyData <= '"+carInfoVo.getCopyDataEnd()+"'");
		}
		if(carInfoVo.getOcnNumber()!=null && !("".equals(carInfoVo.getOcnNumber()))){
			hql.append(" and carInfo.carOcn like '%"+StringEscapeUtils.escapeSql(carInfoVo.getOcnNumber().trim())+"%'");
		}
		if(carInfoVo.getDistributor()!=null && !("".equals(carInfoVo.getDistributor()))){
			hql.append(" and carInfo.distributorId = '"+carInfoVo.getDistributor()+"'");
		}
		if(carInfoVo.getMortgageState()!=null && !("".equals(carInfoVo.getMortgageState()))){
			hql.append(" and carInfo.carMortgageState like '%"+StringEscapeUtils.escapeSql(carInfoVo.getMortgageState().toString().trim())+"%'");
		}
		if(carInfoVo.getDistributState()!=null && !("".equals(carInfoVo.getDistributState()))){
			hql.append(" and carInfo.certificateChild.childId = '"+carInfoVo.getDistributState()+"'");
		}
		if(carInfoVo.getSellState()!=null && !("".equals(carInfoVo.getSellState()))){
			hql.append(" and carInfo.sellStateChild.childId = '"+carInfoVo.getSellState()+"'");
		}else{
			hql.append(" and carInfo.sellStateChild.childId = '"+baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.FORSALE,carInfoVo.getEnterpriseId())+"'");
		}
		
		if(carInfoVo.getId()!=null && !("".equals(carInfoVo.getId()))&& !("null".equals(carInfoVo.getId()))){
			hql.append(" and carInfo.carId ="+carInfoVo.getId());
		}
		
		 if (carInfoVo.getSort() != null && !"".equals(carInfoVo.getSort().trim())
	                && carInfoVo.getOrder() != null
	                && !"".equals(carInfoVo.getOrder().trim()))
	        {
			  hql.append(" order by carInfo." + carInfoVo.getSort() + " "
	                    + carInfoVo.getOrder());
	        }
		}
	public void setBean(XsCarInfo  carInfo,CarInfoVo cVo) throws Exception{

		if(carInfo.getInteriorColorChild()!=null){
			XsChilddictionary interior= baseTagDAO.findById(carInfo.getInteriorColorChild().getChildId());
			if(interior!=null){
				cVo.setInteriorColorName(interior.getDataValue());
			}
		}
		if(carInfo.getDistributorId()!=null && !("".equals(carInfo.getDistributorId()))){
			XsDistributorInfo distributor=distributorInfoDAO.get(XsDistributorInfo.class,carInfo.getDistributorId());
			if(distributor!=null){
				cVo.setDistributorName(distributor.getDistributorName());
			}		
		}
		if(carInfo.getSellStateChild().getChildId()!=null && !("".equals(carInfo.getSellStateChild().getChildId()))){
			
			XsChilddictionary sellState=baseTagDAO.findById(carInfo.getSellStateChild().getChildId());
			if(sellState!=null){
				cVo.setSellStateName(sellState.getDataValue());
			}
		}
		if(carInfo.getCarDistributState()!=null && !("".equals(carInfo.getCarDistributState()))){
			XsChilddictionary distributState=baseTagDAO.findById(carInfo.getCarDistributState());
			if(distributState!=null){
				cVo.setDistributStateName(distributState.getDataValue());
			}
		}
		if(carInfo.getBrandChild()!=null ){
			XsChilddictionary brand=baseTagDAO.findById(carInfo.getBrandChild().getChildId());
			if(brand!=null){
				cVo.setCarBrandName(brand.getDataValue());
			}
		}
		if(carInfo.getCarModelId()!=null && !("".equals(carInfo.getCarModelId()))){
			XsCarModel  model=carModelDAO.get(XsCarModel.class,carInfo.getCarModelId());
			if(model!=null){
				cVo.setCarModelName(model.getModelName());
			}
		}
		if(carInfo.getColorChild()!=null ){
			XsChilddictionary brand=baseTagDAO.findById(carInfo.getColorChild().getChildId());
			if(brand!=null){
				cVo.setColorName(brand.getDataValue());
			}
		}
		if(carInfo.getNormsChild()!=null){
			XsChilddictionary norm=baseTagDAO.findById(carInfo.getNormsChild().getChildId());
			if(norm!=null){
				cVo.setNormsN(norm.getDataValue());
			}
		}
		if(carInfo.getToolCaseChild()!=null ){
			XsChilddictionary tool=baseTagDAO.findById(carInfo.getToolCaseChild().getChildId());
			if(tool!=null){
				cVo.setToolCaseN(tool.getDataValue());
			}
		}
		if(carInfo.getFootdChild()!=null){
			XsChilddictionary foot=baseTagDAO.findById(carInfo.getFootdChild().getChildId());
			if(foot!=null){
				cVo.setFootdN(foot.getDataValue());
			}
		}
	}
	
	@Log(systemName="销售系统", moduleName="车辆档案",opertype="删除")
	public Msg deleteCarInfo(CarInfoVo carInfoVo) throws Exception {	
		Msg msg = new Msg();
		List list=carInfoDAO.createSQLQuery(" select del.xs_car_id from xs_sell_instorehouse_del del" +
				" join xs_car_info car on car.xs_car_id=del.xs_car_id where car.xs_car_id="+carInfoVo.getCarId());
		if(list!=null && list.size()>0){
			msg.setMsg("该车辆档案已使用，不可以删除！");
			msg.setSuccess(false);
		}else{
		XsCarInfo carInfo=(XsCarInfo) carInfoDAO.get("from XsCarInfo where carId="+carInfoVo.getCarId());
		carInfoDAO.delete(carInfo);
		setContent("删除【车辆档案】编码为【"+carInfo.getCarCode()+"】," +
				"VIN为【"+carInfo.getCarVinNumber()+"】的信息！");
		msg.setMsg("删除成功！");
		msg.setSuccess(true);
		}
		return msg;
	}
	
	@Log(systemName="销售系统", moduleName="车辆档案",opertype="新增")
	public void addCarInfo(CarInfoVo carInfoVo) throws Exception {
		XsCarInfo carInfo=new XsCarInfo();
		//BeanUtils.copyProperties(carInfoVo, carInfo);
		carInfo.setCarId(carInfo.getCarId());
		if(carInfoVo.getCarBrand()!=null && !("".equals(carInfoVo.getCarBrand()))){
			XsChilddictionary carBrand=baseTagDAO.findById(carInfoVo.getCarBrand());
			carInfo.setBrandChild(carBrand);
		}
		if(carInfoVo.getCarColor()!=null && !("".equals(carInfoVo.getCarColor()))){
			XsChilddictionary carColor=baseTagDAO.findById(carInfoVo.getCarColor());
			carInfo.setColorChild(carColor);
		}
		if(carInfoVo.getCarInteriorColor()!=null && !("".equals(carInfoVo.getCarInteriorColor()))){
			XsChilddictionary interiorColor=baseTagDAO.findById(carInfoVo.getCarInteriorColor());
			carInfo.setInteriorColorChild(interiorColor);
		}
		carInfo.setCarModelId(carInfoVo.getCarModelId());
		carInfo.setCarOptional(carInfoVo.getCarOptional());
		carInfo.setCarMortgageState(carInfoVo.getCarMortgageState());
		if(carInfoVo.getCarCertificateState()!=null && !("".equals(carInfoVo.getCarCertificateState()))){
			XsChilddictionary certificate=baseTagDAO.findById(carInfoVo.getCarCertificateState());
			carInfo.setCertificateChild(certificate);
		}
		if(carInfoVo.getCarType()!=null && !("".equals(carInfoVo.getCarType()))){
			XsChilddictionary CarType=baseTagDAO.findById(carInfoVo.getCarType());
			carInfo.setCarTypeChild(CarType);
		}
		carInfo.setCarLicensePlate(carInfoVo.getCarLicensePlate());
		carInfo.setCarLicenseName(carInfoVo.getCarLicenseName());
		carInfo.setCarVinNumber(carInfoVo.getCarVinNumber());
		carInfo.setCarMotorNumber(carInfoVo.getCarMotorNumber());
		carInfo.setCarOcn(carInfoVo.getCarOcn());
		carInfo.setCarMakeData((Timestamp)carInfoVo.getCarMakeData());
		carInfo.setCarUnlockingKeyNumber(carInfoVo.getCarUnlockingKeyNumber());
		carInfo.setCarRideLimitNumber(carInfoVo.getCarRideLimitNumber());
		carInfo.setCarTradeCheckBill(carInfoVo.getCarTradeCheckBill());
		carInfo.setCarProductionAddress(carInfoVo.getCarProductionAddress());
		carInfo.setCarAddress(carInfoVo.getCarAddress());
		carInfo.setCarCopyData(carInfoVo.getCarCopyData());
		carInfo.setCarAssembleData(carInfoVo.getCarAssembleData());
		carInfo.setCarEndCheckData(carInfoVo.getCarEndCheckData());
		carInfo.setCarFristInsuranceData(carInfoVo.getCarFristInsuranceData());
		carInfo.setCarForecastData(carInfoVo.getCarForecastData());
		carInfo.setCarPrice(carInfoVo.getCarPrice());
		carInfo.setDistributorId(carInfoVo.getDistributorId());
		carInfo.setUpData(carInfoVo.getUpData());
		carInfo.setUpPerson(carInfoVo.getUpPerson());
		if(carInfoVo.getNorms()!=null && !("".equals(carInfoVo.getNorms()))){
			XsChilddictionary norms=baseTagDAO.findById(carInfoVo.getNorms());
			carInfo.setNormsChild(norms);
		}
		if(carInfoVo.getToolCase()!=null && !("".equals(carInfoVo.getToolCase()))){
			XsChilddictionary toolCase=baseTagDAO.findById(carInfoVo.getToolCase());
			carInfo.setToolCaseChild(toolCase);
		}
		carInfo.setProveFile(carInfoVo.getProveFile());
		carInfo.setRzBook(carInfoVo.getRzBook());
		if(carInfoVo.getFootd()!=null && !("".equals(carInfoVo.getFootd()))){
			XsChilddictionary footd=baseTagDAO.findById(carInfoVo.getFootd());
			carInfo.setToolCaseChild(footd);
		}
		carInfo.setEnterpriseId(carInfoVo.getEnterpriseId());
		carInfo.setCarCode(CreateID.createId("carInfo", Contstants.SELL_BILLSDEPLOY.CARINFOR));
		XsChilddictionary sellState=baseTagDAO.findChildsCon(Contstants.SELLSTATE.SELLADD, Contstants.SELLSTATE.BASE_SELLSTATE,carInfoVo.getEnterpriseId());
		if(sellState!=null){
			carInfo.setSellStateChild(sellState);
		}
		XsChilddictionary disState=baseTagDAO.findChildsCon(Contstants.DISTRIBUTESTATE.COMPANY, Contstants.BASE_SELL.BASE_DISTRIBUTESTATE,carInfoVo.getEnterpriseId());
		if(disState!=null){
			carInfo.setDistributChild(disState);
		}
		List<XsChilddictionary> list = baseTagDAO.findChilds(Contstants.JZZT.JZZT,null,carInfoVo.getEnterpriseId());
        if(list != null){
            for(XsChilddictionary child : list){
                if(child.getDataKey().equals(Contstants.JZZT.wjz)){
                    carInfo.setFixStatusChild(child);
                    break;
                }
            }
        }
		carInfoDAO.save(carInfo);
		XsSellFlowControl xsfc=new XsSellFlowControl();
		xsfc.setXsfcontrolCarId(carInfo.getCarId());
		xsfc.setXsfcontrolCode(Contstants.SELLFLOWCONTROLCODE.CONTROLCODE1);
		xsfc.setXsfcontrolDocument(carInfo.getCarCode());
		xsSellFlowControlDao.save(xsfc);
		XsChilddictionary carBrand=null;
		XsChilddictionary ncolor=null;
		XsChilddictionary color=null;
		if(carInfo.getBrandChild()!=null){
			 carBrand=baseTagDAO.findById(carInfo.getBrandChild().getChildId());
		}
		if(carInfo.getInteriorColorChild()!=null){
			 ncolor=baseTagDAO.findById(carInfo.getInteriorColorChild().getChildId());
		}
		if(carInfo.getColorChild()!=null){
			 color=baseTagDAO.findById(carInfo.getColorChild().getChildId());
		}	
		XsCarModel model= carModelDAO.get(" from XsCarModel where modelId="+carInfo.getCarModelId());
		setContent("新增【车辆档案】编码为【"+carInfo.getCarCode()+"】,品牌为【"+carBrand.getDataValue()+"】," +
				"型号名称为【"+model.getModelName()+"】,VIN为【"+carInfo.getCarVinNumber()+"】," +
				"发动机为【"+carInfo.getCarMotorNumber()+"】,OCN为【"+carInfo.getCarOcn()+"】," +
				"内饰色为【"+ncolor.getDataValue()+"】," +
				"外观色为【"+color.getDataValue()+"】的信息！");
	}
	
	@Log(systemName="销售系统", moduleName="车辆档案",opertype="修改")
	public void updateCarInfo(CarInfoVo carInfoVo) throws Exception {
		XsCarInfo carInfo=(XsCarInfo) carInfoDAO.get("from XsCarInfo where carId="+carInfoVo.getCarId());
		if(carInfoVo.getCarBrand()!=null && !("".equals(carInfoVo.getCarBrand()))){
			XsChilddictionary carBrand=baseTagDAO.findById(carInfoVo.getCarBrand());
			carInfo.setBrandChild(carBrand);
		}
		if(carInfoVo.getCarColor()!=null && !("".equals(carInfoVo.getCarColor()))){
			XsChilddictionary carColor=baseTagDAO.findById(carInfoVo.getCarColor());
			carInfo.setColorChild(carColor);
		}
		if(carInfoVo.getCarInteriorColor()!=null && !("".equals(carInfoVo.getCarInteriorColor()))){
			XsChilddictionary interiorColor=baseTagDAO.findById(carInfoVo.getCarInteriorColor());
			carInfo.setInteriorColorChild(interiorColor);
		}
		carInfo.setCarModelId(carInfoVo.getCarModelId());
		carInfo.setCarOptional(carInfoVo.getCarOptional());
		carInfo.setCarMortgageState(carInfoVo.getCarMortgageState());
		if(carInfoVo.getCarCertificateState()!=null && !("".equals(carInfoVo.getCarCertificateState()))){
			XsChilddictionary certificate=baseTagDAO.findById(carInfoVo.getCarCertificateState());
			carInfo.setCertificateChild(certificate);
		}
		if(carInfoVo.getCarType()!=null && !("".equals(carInfoVo.getCarType()))){
			XsChilddictionary CarType=baseTagDAO.findById(carInfoVo.getCarType());
			carInfo.setCarTypeChild(CarType);
		}
		carInfo.setCarCode(carInfoVo.getCarCode());
		carInfo.setCarLicensePlate(carInfoVo.getCarLicensePlate());
		carInfo.setCarLicenseName(carInfoVo.getCarLicenseName());
		carInfo.setCarVinNumber(carInfoVo.getCarVinNumber());
		carInfo.setCarMotorNumber(carInfoVo.getCarMotorNumber());
		carInfo.setCarOcn(carInfoVo.getCarOcn());
		carInfo.setCarMakeData((Timestamp)carInfoVo.getCarMakeData());
		carInfo.setCarUnlockingKeyNumber(carInfoVo.getCarUnlockingKeyNumber());
		carInfo.setCarRideLimitNumber(carInfoVo.getCarRideLimitNumber());
		carInfo.setCarTradeCheckBill(carInfoVo.getCarTradeCheckBill());
		carInfo.setCarProductionAddress(carInfoVo.getCarProductionAddress());
		carInfo.setCarAddress(carInfoVo.getCarAddress());
		carInfo.setCarCopyData(carInfoVo.getCarCopyData());
		carInfo.setCarAssembleData(carInfoVo.getCarAssembleData());
		carInfo.setCarEndCheckData(carInfoVo.getCarEndCheckData());
		carInfo.setCarFristInsuranceData(carInfoVo.getCarFristInsuranceData());
		carInfo.setCarForecastData(carInfoVo.getCarForecastData());
		carInfo.setCarPrice(carInfoVo.getCarPrice());
		carInfo.setDistributorId(carInfoVo.getDistributorId());
		carInfo.setUpData(carInfoVo.getUpData());
		carInfo.setUpPerson(carInfoVo.getUpPerson());
		if(carInfoVo.getNorms()!=null && !("".equals(carInfoVo.getNorms()))){
			XsChilddictionary norms=baseTagDAO.findById(carInfoVo.getNorms());
			carInfo.setNormsChild(norms);
		}
		if(carInfoVo.getToolCase()!=null && !("".equals(carInfoVo.getToolCase()))){
			XsChilddictionary toolCase=baseTagDAO.findById(carInfoVo.getToolCase());
			carInfo.setToolCaseChild(toolCase);
		}
		carInfo.setProveFile(carInfoVo.getProveFile());
		carInfo.setRzBook(carInfoVo.getRzBook());
		if(carInfoVo.getFootd()!=null && !("".equals(carInfoVo.getFootd()))){
			XsChilddictionary footd=baseTagDAO.findById(carInfoVo.getFootd());
			carInfo.setFootdChild(footd);
		}
		carInfoDAO.merge(carInfo);
		XsChilddictionary carBrand=null;
		XsChilddictionary ncolor=null;
		XsChilddictionary color=null;
		if(carInfo.getBrandChild()!=null){
			 carBrand=baseTagDAO.findById(carInfo.getBrandChild().getChildId());
		}
		if(carInfo.getInteriorColorChild()!=null){
			 ncolor=baseTagDAO.findById(carInfo.getInteriorColorChild().getChildId());
		}
		if(carInfo.getColorChild()!=null){
			 color=baseTagDAO.findById(carInfo.getColorChild().getChildId());
		}
		// carBrand=baseTagDAO.findById(carInfo.getCarBrand(),carInfoVo.getEnterpriseId());
		// ncolor=baseTagDAO.findById(carInfo.getCarInteriorColor(),carInfoVo.getEnterpriseId());
		 //color=baseTagDAO.findById(carInfo.getCarBrand(),carInfoVo.getEnterpriseId());
		XsCarModel model= carModelDAO.get(" from XsCarModel where modelId="+carInfo.getCarModelId());
		setContent("修改编码为【"+carInfo.getCarCode()+"】的【车辆档案】信息,品牌为【"+carBrand.getDataValue()+"】," +
				"型号名称为【"+model.getModelName()+"】,VIN为【"+carInfo.getCarVinNumber()+"】," +
				"发动机为【"+carInfo.getCarMotorNumber()+"】,OCN为【"+carInfo.getCarOcn()+"】," +
				"内饰色为【"+ncolor.getDataValue()+"】," +
				"外观色为【"+color.getDataValue()+"】！");
	}
	
	public Datagrid getCar(CarInfoVo carInfoVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("SELECT car.xs_car_id,car.xs_car_brand,(SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id=car.xs_car_brand) carBrandName,"+
						"car.xs_car_model_id,(SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id=car.xs_car_model_id) carModelName,car.xs_car_color,  "+
						"(SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id=car.xs_car_color) carColorName,car.xs_car_vin_number FROM xs_car_info car,xs_car_model model WHERE  car.xs_car_model_id=model.xs_model_id" +
						" and  car.enterprise_id="+carInfoVo.getEnterpriseId());
		if(carInfoVo.getCarVinNumber()!=null && !("".equals(carInfoVo.getCarVinNumber()))){
			hql.append("  and car.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(carInfoVo.getCarVinNumber().trim())+"%'");
		}
		List<Object[]> lst=carInfoDAO.createSQLQuery(hql.toString(), carInfoVo.getPage(), carInfoVo.getRows());
		List<CarInfoVo> rows =new ArrayList<CarInfoVo>();
		if(lst!=null && lst.size()>0){
			for(Object[] b:lst){
				CarInfoVo cVo=new CarInfoVo();
				if(b[0]!=null && !("".equals(b[0]))){
					cVo.setCarId((Integer)b[0]);
				}
				if(b[1]!=null && !("".equals(b[1]))){
					cVo.setCarBrand((Integer)b[1]);
				}
				if(b[2]!=null && !("".equals(b[2]))){
					cVo.setCarBrandName(b[2].toString());
				}
				if(b[3]!=null && !("".equals(b[3]))){
					cVo.setCarModelId((Integer)b[3]);
				}
				if(b[4]!=null && !("".equals(b[4]))){
					cVo.setCarModelName(b[4].toString());
				}
				if(b[5]!=null && !("".equals(b[5]))){
					cVo.setCarColor((Integer)b[5]);
				}
				if(b[6]!=null && !("".equals(b[6]))){
					cVo.setColorName(b[6].toString());
				}
				if(b[7]!=null && !("".equals(b[7]))){
					cVo.setCarVinNumber(b[7].toString());
				}
				rows.add(cVo);
			}
		}
		int total = carInfoDAO.getSQLCount(hql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	public boolean findVintwo (String vinNo,Integer id) throws Exception{
		List<Object[]> cars=carInfoDAO.createSQLQuery("SELECT car.xs_car_id  FROM xs_car_info car WHERE car.xs_car_vin_number='"+vinNo+"'" +
				" and car.enterprise_id ="+id);
		if(cars!=null && cars.size()>0){
			return true;
		}
		return false;
	}
	public boolean findVin (String vinNo,Integer carId,Integer id) throws Exception{
		List<Object[]> cars=carInfoDAO.createSQLQuery("SELECT car.xs_car_id  FROM xs_car_info car WHERE car.xs_car_vin_number='"+vinNo+"' " +
				"and car.xs_car_id!='"+carId+"'and car.enterprise_id ="+id);
		if(cars!=null && cars.size()>0){
			return true;
		}
		return false;
	}
	
	public Datagrid queryCarUpInfor(CarInfoVo carInfoVo) throws Exception {
		return carInfoDAO.queryCarUpInfor(carInfoVo);
	}
	//车辆上报
	
	@Log(systemName="销售系统", moduleName="车辆上报管理",opertype="上报")
	public void updateCarUpInfo(CarInfoVo carInfoVo) throws Exception {
		XsCarInfo carInfo=(XsCarInfo) carInfoDAO.get("from XsCarInfo where carCode='"+carInfoVo.getCarCode()+"'");
		carInfo.setUpData(new Date().toLocaleString());
		 BasUsers user = (BasUsers) ServletActionContext.getRequest().getSession().getAttribute(Contstants.CUSTOMER);
		carInfo.setUpPerson(Integer.parseInt(String.valueOf(user.getBasStuff().getStfId())));
		carInfoDAO.saveOrUpdate(carInfo);
		XsChilddictionary carBrand=baseTagDAO.findById(carInfo.getCarBrand());
		//XsChilddictionary ncolor=baseTagDAO.findById(carInfo.getCarInteriorColor(),carInfoVo.getEnterpriseId());
		//XsChilddictionary color=baseTagDAO.findById(carInfo.getCarBrand(),carInfoVo.getEnterpriseId());
		XsCarModel model= carModelDAO.get(" from XsCarModel where modelId="+carInfo.getCarModelId());
		setContent("上报编码为【"+carInfo.getCarCode()+"】,品牌为【"+carBrand.getDataValue()+"】," +
				"型号名称为【"+model.getModelName()+"】,VIN为【"+carInfo.getCarVinNumber()+"】的车辆信息," +
				"上报时间【"+carInfo.getUpData()+"】,上报人【"+String.valueOf(user.getBasStuff().getStfName())+"】！");
		
	}
	
	public Datagrid getCarAttachment(CarInfoVo carInfoVo) throws Exception {
		return carInfoDAO.getCarAttachment(carInfoVo);
	}
	
	public Datagrid getAttachmentDel(CarInfoVo carInfoVo) throws Exception {
		return carInfoDAO.getAttachmentDel(carInfoVo);
	}
	//新增附件明细
	
	@Log(systemName="销售系统", moduleName="随车附件管理",opertype="新增")
	public void addAttachmentDel(CarInfoVo carInfoVo) throws Exception {
		XsSellAttachment att=new XsSellAttachment();
		att.setCount1(carInfoVo.getCount1());
		att.setCount2(carInfoVo.getCount2());
		att.setOperator_date(carInfoVo.getOperator_date());
		att.setOperator_persion(carInfoVo.getOperator_persion());
		XsChilddictionary Operator_type=baseTagDAO.findById(carInfoVo.getOperator_type());
		att.setXsChilddictionary(Operator_type);
		att.setRemark(carInfoVo.getRemark());
		att.setXs_car_id(carInfoVo.getCarId());
		att.setSend_persion(carInfoVo.getSend_persion());
		carInfoDAO.save(att);
		XsCarInfo carInfo=(XsCarInfo) carInfoDAO.get(" from XsCarInfo where carId="+carInfoVo.getCarId());
		setContent("给车辆编码为【"+carInfo.getCarCode()+"】,VIN为【"+carInfo.getCarVinNumber()+"】的车辆档案新增附件," +
				"点烟器【"+carInfoVo.getCount1()+"】个,烟灰缸【"+carInfoVo.getCount2()+"】个," +
				"附件类型为【"+Operator_type.getDataValue()+"】," +
				"借还人为【"+carInfoVo.getSend_persion()+"】！");
	}
	
	@Log(systemName="销售系统", moduleName="随车附件管理",opertype="删除")
	public void deleteAttachmentDel(CarInfoVo carInfoVo) throws Exception {
		XsSellAttachment att=(XsSellAttachment) carInfoDAO.get("from XsSellAttachment where attachment_no="+carInfoVo.getAttachment_no());
		carInfoDAO.delete(att);
		XsCarInfo carInfo=(XsCarInfo) carInfoDAO.get(" from XsCarInfo where carId="+att.getXs_car_id());
		setContent("删除车辆编码为【"+carInfo.getCarCode()+"】,VIN为【"+carInfo.getCarVinNumber()+"】的车辆附件信息！");
	}
	
	@Log(systemName="销售系统", moduleName="随车附件管理",opertype="修改")
	public void updateAttachmentDel(CarInfoVo carInfoVo) throws Exception {
		XsSellAttachment att=(XsSellAttachment) carInfoDAO.get("from XsSellAttachment where attachment_no="+carInfoVo.getAttachment_no());
		att.setCount1(carInfoVo.getCount1());
		att.setCount2(carInfoVo.getCount2());
		att.setOperator_date(carInfoVo.getOperator_date());
		att.setOperator_persion(carInfoVo.getOperator_persion());
		XsChilddictionary Operatortype= baseTagDAO.findById(carInfoVo.getOperator_type());
		att.setXsChilddictionary(Operatortype);
		//att.setOperator_type(carInfoVo.getOperator_type());
		att.setRemark(carInfoVo.getRemark());
		att.setXs_car_id(carInfoVo.getCarId());
		att.setSend_persion(carInfoVo.getSend_persion());
		carInfoDAO.saveOrUpdate(att);
		//XsChilddictionary Operator_type=baseTagDAO.findById(carInfoVo.getOperator_type());
		XsCarInfo carInfo=(XsCarInfo) carInfoDAO.get(" from XsCarInfo where carId="+att.getXs_car_id());
		setContent("修改车辆编码为【"+carInfo.getCarCode()+"】,VIN为【"+carInfo.getCarVinNumber()+"】的车辆附件信息," +
				"点烟器【"+carInfoVo.getCount1()+"】个,烟灰缸【"+carInfoVo.getCount2()+"】个," +
				"附件类型为【"+Operatortype.getDataValue()+"】," +
				"借还人为【"+carInfoVo.getSend_persion()+"】！");
	}
	

}
