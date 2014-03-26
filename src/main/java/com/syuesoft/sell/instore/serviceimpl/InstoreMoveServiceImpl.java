package com.syuesoft.sell.instore.serviceimpl;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.instore.dao.InstoreMoveDAO;
import com.syuesoft.sell.instore.service.InstoreMoveService;
import com.syuesoft.sell.instore.vo.SellRetreatVo;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellInstorehouseDel;
import com.syuesoft.sell.model.XsSellRetreat;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;


@Service("instoreMoveService")
public class InstoreMoveServiceImpl extends BaseLogServiceImpl implements InstoreMoveService {
	private InstoreMoveDAO instoreMoveDAO;
	private BaseTagDAO baseTagDAO;
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	@Log(systemName="销售系统", moduleName="车辆移库管理",opertype="新增")
	public void addInstore(SellRetreatVo instoreMoveVo) throws Exception {
		//明细
		List<SellRetreatVo> insertedList = JSON.parseArray(instoreMoveVo.getInstoreDelGrid(), SellRetreatVo.class);	
		if(insertedList != null){
			String code = CreateID.createId("sellMove", Contstants.SELL_BILLSDEPLOY.INSTOREMOVE);
		    for(SellRetreatVo  retreatVo1:insertedList){
				XsSellInstorehouseDel instoreDel=(XsSellInstorehouseDel) instoreMoveDAO.get("from XsSellInstorehouseDel del where  del.detailsId="+ retreatVo1.getDetailsId_());
				XsCarInfo car=instoreDel.getCarInfo();
				//if(warehouse!=retreatVo1.getInInstorehouse()){
					//移库单
					XsSellRetreat sellRetreat = JSON.parseObject(instoreMoveVo.getSellRetreat(), XsSellRetreat.class);
					sellRetreat.setOutInstorehouse(retreatVo1.getWarehouse());
					sellRetreat.setInInstorehouse(sellRetreat.getInInstorehouse());
					sellRetreat.setPerson(sellRetreat.getPerson());
					sellRetreat.setRetreatCode(code);
					sellRetreat.setContext(sellRetreat.getContext());
					sellRetreat.setExamine(baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE, Contstants.ADUIT.ADUIT,instoreMoveVo.getEnterprise_id()));
					sellRetreat.setDetails(instoreDel);
					sellRetreat.setInstorehouseType(baseTagDAO.findChildCon(Contstants.INSTORESTYLE.MOVE,Contstants.INSTORESTYLE.PARENTINSTORE,instoreMoveVo.getEnterprise_id()));
					sellRetreat.setEnterprise_id(instoreMoveVo.getEnterprise_id());
					instoreMoveDAO.save(sellRetreat);
					XsChilddictionary type=baseTagDAO.findById(sellRetreat.getInInstorehouse());
					XsChilddictionary type2=baseTagDAO.findById(sellRetreat.getOutInstorehouse());
					setContent("新增【车辆移库管理】编号为【"+sellRetreat.getRetreatCode()+"】,VIN号【"+car.getCarVinNumber()+"】," +
							"现在库为【"+type.getDataValue()+"】,原来库为【"+type2.getDataValue()+"】," +
									"移库理由为【"+sellRetreat.getContext()+"】的信息！");
					
				//}
			}
		}
		
	}
	
	
	@Log(systemName="销售系统", moduleName="车辆移库管理",opertype="删除")
	public void deleteInstore(SellRetreatVo instoreMoveVo) throws Exception {
		//获取同一个编号的退车单集合
		List<BaseBean>  sellRetreat= instoreMoveDAO.find(" from XsSellRetreat retreat where 1=1 and  retreat.retreatCode='"+instoreMoveVo.getRetreatCode()+"'");
		if(sellRetreat!=null && sellRetreat.size()>0){
			for(BaseBean b:sellRetreat){
				XsSellRetreat retreat =(XsSellRetreat) b;
				//删除移库记录
				instoreMoveDAO.delete(retreat);
			}
		}
		setContent("删除编号为【"+instoreMoveVo.getRetreatCode()+"】的【车辆移库管理】!");
		
	}
	public Datagrid getPagerCar(SellRetreatVo instoreMoveVo)throws Exception{
		Datagrid dg = new Datagrid();
		StringBuffer sb=new StringBuffer("SELECT bb.* FROM (SELECT aa.temp1,(CASE WHEN aa.temp21!='' THEN aa.temp21 ELSE aa.temp2 END) AS temp2,");
		sb.append(" aa.temp3,aa.temp4,aa.temp5,aa.temp6,aa.temp7,aa.temp8,aa.temp9,aa.temp10,");
		sb.append(" aa.temp11,aa.temp12,aa.temp13,aa.temp14,aa.temp15,aa.temp16,aa.temp17,aa.temp18,aa.temp19,aa.temp20");
		sb.append(" FROM ( SELECT  instore.instorehouse_id AS temp1,  instore.warehouse AS temp2,  del.details_id AS temp3,  del.xs_car_id AS temp4,");
		sb.append(" car.xs_car_code AS temp5,  car.xs_car_vin_number AS temp6,  car.xs_car_ocn AS temp7,  car.xs_car_brand AS temp8,");
		sb.append(" (SELECT    child.dataValue  FROM xs_childdictionary child  WHERE child.child_id = car.xs_car_brand) AS temp9,  ");
		sb.append(" car.xs_car_model_id AS temp10,");
		sb.append(" (SELECT    model.xs_model_name  FROM xs_car_model model  WHERE model.xs_model_id = car.xs_car_model_id) AS temp11,");
		sb.append(" car.xs_car_color AS temp12,");  
		sb.append(" (SELECT    child.dataValue  FROM xs_childdictionary child  WHERE child.child_id = car.xs_car_color) AS temp13,");
		sb.append(" car.xs_car_distribut_state AS temp14,"); 
		sb.append(" (SELECT     child.dataValue  FROM xs_childdictionary child  WHERE child.child_id = car.xs_car_distribut_state) AS temp15,");
		sb.append(" car.xs_car_sell_state AS temp16,"); 
		sb.append(" (SELECT    child.dataValue  FROM xs_childdictionary child  WHERE child.child_id = car.xs_car_sell_state) AS temp17,");
		sb.append(" (SELECT    DATEDIFF(NOW(),instore.instorehouse_date)) AS temp18, car.xs_car_motor_number AS temp19,");
		sb.append(" car.xs_car_licenseName AS temp20, kk.in_instorehouse AS temp21");
		sb.append(" FROM xs_sell_instorehouse instore INNER JOIN  xs_sell_instorehouse_del del " +
				"ON del.instorehouse_id=instore.instorehouse_id AND instore.examine_state="+baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.YISHENHE,instoreMoveVo.getEnterprise_id()));
		sb.append(" INNER JOIN  xs_car_info car ON car.xs_car_id=del.xs_car_id");
		sb.append(" LEFT OUTER JOIN (");
		sb.append(" SELECT xsr.* FROM (SELECT temp.* FROM xs_sell_retreat temp ORDER BY temp.retreat_id DESC ) xsr " +
				"WHERE xsr.retreat_code LIKE '"+Contstants.SELL_BILLSDEPLOY.INSTOREMOVE+"%' " +
								" GROUP BY xsr.details_id ORDER BY xsr.retreat_id DESC");
		sb.append(" ) kk ON kk.details_id=del.details_id");
		sb.append(" WHERE car.xs_car_sell_state NOT IN(" +
				//""+baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.MAKING)+"" +
				""+baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.DISTRi,instoreMoveVo.getEnterprise_id())
				+","+baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.CARBACK,instoreMoveVo.getEnterprise_id())
				+","+baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.AFTERSELL,instoreMoveVo.getEnterprise_id())
				+","+baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.SELLOUT,instoreMoveVo.getEnterprise_id())
				+","+baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.SWAPOUT,instoreMoveVo.getEnterprise_id())+")");
		sb.append("  and instore.enterprise_id="+instoreMoveVo.getEnterprise_id()+") aa ) bb WHERE 1=1");
		if(instoreMoveVo.getOutInstorehouse()!=null && !("".equals(instoreMoveVo.getOutInstorehouse()))){
			sb.append(" and bb.temp2="+instoreMoveVo.getOutInstorehouse());
		}else{
			sb.append(" and bb.temp2=-1");
		}
		if(instoreMoveVo.getCarVinNumber()!=null && !("".equals(instoreMoveVo.getCarVinNumber()))){
			sb.append(" and bb.temp6 like '%"+instoreMoveVo.getCarVinNumber()+"%'");
		}
		if(instoreMoveVo.getCarBrand()!=null && !("".equals(instoreMoveVo.getCarBrand()))){
			sb.append(" and bb.temp8="+instoreMoveVo.getCarBrand());
		}
		if(instoreMoveVo.getCarModelId()!=null && !("".equals(instoreMoveVo.getCarModelId()))){
			sb.append(" and bb.temp10="+instoreMoveVo.getCarModelId());
		}
		if(instoreMoveVo.getCarColor()!=null && !("".equals(instoreMoveVo.getCarColor()))){
			sb.append(" and bb.temp12="+instoreMoveVo.getCarColor());
		}
//		//查询库存状态
//		Integer instore=baseTagDAO.findChildCon(Contstants.INSTORETYPE.INSTORE, Contstants.BASE_SELL.INSTORETYPE);
//		StringBuffer sql=new StringBuffer("SELECT  instore.instorehouse_id, instore.warehouse, del.details_id, del.xs_car_id, car.xs_car_code, car.xs_car_vin_number,"+
//						"car.xs_car_ocn,car.xs_car_brand,(SELECT  child.dataValue FROM xs_childdictionary child WHERE child.child_id=  car.xs_car_brand) AS car_brand_name," +
//						"car.xs_car_model_id,(SELECT  model.xs_model_name FROM  xs_car_model model WHERE model.xs_model_id=  car.xs_car_model_id) AS car_model_name," +
//						"car.xs_car_color, (SELECT  child.dataValue FROM xs_childdictionary child WHERE child.child_id=  car.xs_car_color) AS car_color_name," +
//						"car.xs_car_distribut_state,(SELECT  child.dataValue FROM xs_childdictionary child WHERE child.child_id=  car.xs_car_distribut_state) AS distribut_state_name," +
//						"car.xs_car_sell_state,(SELECT  child.dataValue FROM xs_childdictionary child WHERE child.child_id=  car.xs_car_sell_state) AS sell_state_name," +
//						"(SELECT DATEDIFF(NOW(),instore.instorehouse_date)) As age,car.xs_car_motor_number,car.xs_car_licenseName FROM  xs_sell_instorehouse instore, xs_sell_instorehouse_del del, xs_car_info car WHERE " +
//						"	instore.instorehouse_id = del.instorehouse_id AND car.xs_car_id = del.xs_car_id AND del.xs_sell_allocatel_type = "+instore );	
//		if(instoreMoveVo.getOutInstorehouse()!=null && !("".equals(instoreMoveVo.getOutInstorehouse()))){
//			sql.append(" and instore.warehouse="+instoreMoveVo.getOutInstorehouse());
//		}
		/*if(instoreMoveVo.getVinNumber()!=null && !("".equals(instoreMoveVo.getVinNumber()))){
			sql.append("  and car.xs_car_vin_number like '%"+instoreMoveVo.getVinNumber()+"%'");
		}
		if(instoreMoveVo.getQueryBrand()!=null && !("".equals(instoreMoveVo.getQueryBrand()))){
			sql.append("  and car.xs_car_brand='"+instoreMoveVo.getQueryBrand()+"'");
		}
		if(instoreMoveVo.getQueryModel()!=null && !("".equals(instoreMoveVo.getQueryModel()))){
			sql.append("  and car.xs_car_model_id='"+instoreMoveVo.getQueryModel()+"'");
		}
		if(instoreMoveVo.getQueryColor()!=null && !("".equals(instoreMoveVo.getQueryColor()))){
			sql.append("  and car.xs_car_color='"+instoreMoveVo.getQueryColor()+"'");
		}*/
		List<Object []> lst=instoreMoveDAO.createSQLQuery(sb.toString(),instoreMoveVo.getPage(), instoreMoveVo.getRows());
		List<SellRetreatVo> rows =new ArrayList<SellRetreatVo>();
		if(lst!=null && lst.size()>0){
			for(Object [] obj:lst){
				SellRetreatVo moveVo=new SellRetreatVo();
				if(obj[0]!=null && !("".equals(obj[0]))){
					moveVo.setInstorehouseId( Integer.parseInt(obj[0].toString()));
				}
				if(obj[1]!=null && !("".equals(obj[1]))){
					moveVo.setWarehouse( Integer.parseInt(obj[1].toString()));
				}
				if(obj[2]!=null && !("".equals(obj[2]))){
					moveVo.setDetailsId_( Integer.parseInt(obj[2].toString()));
				}
				if(obj[3]!=null && !("".equals(obj[3]))){
					moveVo.setCarId( Integer.parseInt(obj[3].toString()));
				}
				if(obj[4]!=null && !("".equals(obj[4]))){
					moveVo.setCarCode(obj[4].toString());
				}
				if(obj[5]!=null && !("".equals(obj[5]))){
					moveVo.setCarVinNumber(obj[5].toString());
				}
				if(obj[6]!=null && !("".equals(obj[6]))){
					moveVo.setCarOcn(obj[6].toString());
				}
				if(obj[7]!=null && !("".equals(obj[7]))){
					moveVo.setCarBrand((Integer)obj[7]);
				}
				if(obj[8]!=null && !("".equals(obj[8]))){
					moveVo.setCarBrandName(obj[8].toString());
				}
				if(obj[9]!=null && !("".equals(obj[9]))){
					moveVo.setCarModelId( Integer.parseInt(obj[9].toString()));
				}
				if(obj[10]!=null && !("".equals(obj[10]))){
					moveVo.setCarModelName(obj[10].toString());
				}
				if(obj[11]!=null && !("".equals(obj[11]))){
					moveVo.setCarColor((Integer)obj[11]);
				}
				if(obj[12]!=null && !("".equals(obj[12]))){
					moveVo.setColorName(obj[12].toString());
				}
				if(obj[13]!=null && !("".equals(obj[13]))){
					moveVo.setCarDistributState( Integer.parseInt(obj[13].toString()));
				}
				if(obj[14]!=null && !("".equals(obj[14]))){
					moveVo.setDistributStateName(obj[14].toString());
				}
				if(obj[15]!=null && !("".equals(obj[15]))){
					moveVo.setCarSellState( Integer.parseInt(obj[15].toString()));
				}
				if(obj[16]!=null && !("".equals(obj[16]))){
					moveVo.setSellStateName(obj[16].toString());
				}
				if(obj[17]!=null && !("".equals(obj[17]))){
					moveVo.setCarInstorageAge(Double.parseDouble(obj[17].toString()));
				}
				if(obj[18]!=null && !("".equals(obj[18]))){
					moveVo.setCarMotorNumber(obj[18].toString());
				}
				if(obj[19]!=null && !("".equals(obj[19]))){
					moveVo.setCarLicenseName(obj[19].toString());
				}
				rows.add(moveVo);
			}
		}
		int total = instoreMoveDAO.getSQLCount(sb.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
		
	}
	
	public Datagrid getPager(SellRetreatVo instoreMoveVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql=new StringBuffer("SELECT   retreat.retreat_code, retreat.retreat_date,retreat.examine,retreat.context_ ,retreat.in_instorehouse," +
				"retreat.out_instorehouse,retreat.person_,retreat.retreat_id,g.STF_NAME," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =retreat.in_instorehouse)  AS house," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =retreat.examine)  AS exa,retreat.enterprise_id " +
				"FROM  " +
				"xs_sell_retreat retreat,xs_sell_instorehouse_del del,  xs_car_info  car , bas_stuff g  WHERE   retreat.details_id=del.details_id  and " +
				" g.STF_ID=retreat.person_  and retreat.enterprise_id="+instoreMoveVo.getEnterprise_id()+" " +
						" AND del.xs_car_id=car.xs_car_id  and  retreat.instorehouse_type="+
						baseTagDAO.findChildCon(Contstants.INSTORESTYLE.MOVE,Contstants.INSTORESTYLE.PARENTINSTORE,instoreMoveVo.getEnterprise_id()));	
	
		if(instoreMoveVo.getRetreatDateStart()!=null && !("".equals(instoreMoveVo.getRetreatDateStart()))){
				sql.append(" AND   DATE(retreat.retreat_date)>='"+instoreMoveVo.getRetreatDateStart()+"'");
			}
		if(instoreMoveVo.getRetreatDateEnd()!=null && !("".equals(instoreMoveVo.getRetreatDateEnd()))){
				sql.append("  AND  DATE(retreat.retreat_date)<='"+instoreMoveVo.getRetreatDateEnd()+"'");
			
		}
		if((instoreMoveVo.getRetreatDateStart()==null || "".equals(instoreMoveVo.getRetreatDateStart()))&&
				(instoreMoveVo.getRetreatDateEnd()==null || "".equals(instoreMoveVo.getRetreatDateEnd()))){
			sql.append("  AND  DATE(retreat.retreat_date) BETWEEN '"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 
							(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
									Contstants.COLLIGATES.DEFAULTSHOWDAY,instoreMoveVo.getEnterprise_id()).getCiValue()))+"' " +
											"and '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		/*if(retreatVo.getQueryRetreatCode()!=null && !("".equals(retreatVo.getQueryRet
		 * reatCode()))){
			sql.append("  AND  retreat.retreat_code  like '"+retreatVo.getQueryRetreatCode()+"'");
		}
		if(retreatVo.getQuerySupplier()!=null && !("".equals(retreatVo.getQuerySupplier()))){
			sql.append("   AND car.xs_supplier_id="+retreatVo.getQuerySupplier());
		}
		if(retreatVo.getQueryExamine()!=null && !("".equals(retreatVo.getQueryExamine()))){
			sql.append(" AND  retreat.examine="+retreatVo.getQueryExamine());
		}*/
		if(instoreMoveVo.getQueryVinNumber()!=null && !("".equals(instoreMoveVo.getQueryVinNumber()))){
			sql.append("  AND 	 car.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(instoreMoveVo.getQueryVinNumber().trim())+"%'");
		}
		if(instoreMoveVo.getInInstorehouse()!=null && !("".equals(instoreMoveVo.getInInstorehouse()))){
			sql.append("  AND 	 retreat.in_instorehouse='"+instoreMoveVo.getInInstorehouse()+"'");
		}
		sql.append("  GROUP BY retreat.retreat_code order by retreat.retreat_code desc");
		List<Object[]> lst=instoreMoveDAO.createSQLQuery(sql.toString(), instoreMoveVo.getPage(), instoreMoveVo.getRows());
		List<SellRetreatVo> rows =new ArrayList<SellRetreatVo>();
		if(lst!=null && lst.size()>0){
			for(Object[] obj:lst){
				SellRetreatVo  retreatVo1=new SellRetreatVo();
				if(obj[0]!=null && !("".equals(obj[0]))){
					retreatVo1.setRetreatCode(obj[0].toString());
				}
				if(obj[1]!=null && !("".equals(obj[1]))){
					retreatVo1.setRetreatDate((Timestamp)obj[1]);
				}
				if(obj[2]!=null && !("".equals(obj[2]))){
					retreatVo1.setExamine((Integer) obj[2]);
				}
				if(obj[3]!=null && !("".equals(obj[3]))){
					retreatVo1.setContext( obj[3].toString());
				}
				if(obj[4]!=null && !("".equals(obj[4]))){
					retreatVo1.setInInstorehouse(Integer.parseInt( obj[4].toString()));
				}
				
				if(obj[5]!=null && !("".equals(obj[5]))){
					retreatVo1.setOutInstorehouse(Integer.parseInt( obj[5].toString()));
				}
			
				if(obj[6]!=null && !("".equals(obj[6]))){
					retreatVo1.setPerson( Integer.parseInt(obj[6].toString()));
				}
				if(obj[7]!=null && !("".equals(obj[7]))){
					retreatVo1.setRetreatId( Integer.parseInt(obj[7].toString()));
				}
				if(obj[8]!=null && !("".equals(obj[8]))){
					retreatVo1.setPersonName(obj[8].toString());
				}
				if(obj[9]!=null && !("".equals(obj[9]))){
					retreatVo1.setHouse(obj[9].toString());
				}
				if(obj[10]!=null && !("".equals(obj[10]))){
					retreatVo1.setExamineType(obj[10].toString());
				}
				if(obj[11]!=null && !("".equals(obj[11]))){
					retreatVo1.setEnterprise_id(Integer.parseInt(obj[11].toString()));
				}
		
				rows.add(retreatVo1);
			}
		}
		int total = instoreMoveDAO.getSQLCount(sql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	public Datagrid getPagerDel(SellRetreatVo instoreMoveVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql=new StringBuffer("SELECT   del.details_id, del.instorehouse_id,del.xs_car_id, del.notax, del.tax,car.xs_car_code,car.xs_car_vin_number,"+
				" model.xs_model_salesPrice, car.xs_car_model_id,(SELECT model.xs_model_name FROM xs_car_model model WHERE model.xs_model_id=car.xs_car_model_id) "+			
				"AS  xs_car_model_name, car.xs_car_ocn,car.xs_car_motor_number,car.xs_car_color,(SELECT child.dataValue FROM xs_childdictionary  child WHERE child.child_id=car.xs_car_color) "+
				"AS  xs_car_color_name,retreat.retreat_id,car.xs_car_brand,(SELECT child.dataValue FROM xs_childdictionary  child WHERE child.child_id=car.xs_car_brand) AS brandName" +
				"   FROM xs_sell_retreat retreat,xs_sell_instorehouse_del del, xs_car_info  car,xs_car_model model  WHERE retreat.details_id = del.details_id"+
				"  AND car.xs_car_id=del.xs_car_id AND model.xs_model_id=car.xs_car_model_id   AND   retreat.retreat_code = '"+instoreMoveVo.getRetreatCode()+"'");	
		List<Object[]> lst=instoreMoveDAO.createSQLQuery(sql.toString(), instoreMoveVo.getPage(), instoreMoveVo.getRows());
		List<SellRetreatVo> rows =new ArrayList<SellRetreatVo>();
		if(lst!=null && lst.size()>0){
			for(Object[] obj:lst){
				SellRetreatVo  retreatVo1=new SellRetreatVo();
				if(obj[0]!=null && !("".equals(obj[0]))){
					retreatVo1.setDetailsId((Integer)obj[0]);
				}
				if(obj[1]!=null && !("".equals(obj[1]))){
					retreatVo1.setInstorehouse_((Integer)obj[1]);
				}
				if(obj[2]!=null && !("".equals(obj[2]))){
					retreatVo1.setCarId((Integer) obj[2]);
				}
				if(obj[3]!=null && !("".equals(obj[3]))){
					retreatVo1.setNotax((Double) obj[3]);
				}
				if(obj[4]!=null && !("".equals(obj[4]))){
					retreatVo1.setTax((Double)obj[4]);
				}
				if(obj[5]!=null && !("".equals(obj[5]))){
					retreatVo1.setCarCode( obj[5].toString());
				}
				if(obj[6]!=null && !("".equals(obj[6]))){
					retreatVo1.setCarVinNumber( obj[6].toString());
				}
				if(obj[7]!=null && !("".equals(obj[7]))){
					retreatVo1.setModelSalesPrice((Double)obj[7]);
				}
				if(obj[8]!=null && !("".equals(obj[8]))){
					retreatVo1.setCarModelId((Integer)obj[8]);
				}
				if(obj[9]!=null && !("".equals(obj[9]))){
					retreatVo1.setCarModelName(obj[9].toString());
				}
				if(obj[10]!=null && !("".equals(obj[10]))){
					retreatVo1.setCarOcn(obj[10].toString());
				}
				if(obj[11]!=null && !("".equals(obj[11]))){
					retreatVo1.setCarMotorNumber(obj[10].toString());
				}
				if(obj[12]!=null && !("".equals(obj[12]))){
					retreatVo1.setCarColor((Integer) obj[12]);
				}
				if(obj[13]!=null && !("".equals(obj[13]))){
					retreatVo1.setColorName(obj[13].toString());
				}
				if(obj[14]!=null && !("".equals(obj[14]))){
					retreatVo1.setRetreatId((Integer)obj[14]);
				}
				if(obj[15]!=null && !("".equals(obj[15]))){
					retreatVo1.setCarBrand((Integer)obj[15]);
				}
				if(obj[16]!=null && !("".equals(obj[16]))){
					retreatVo1.setCarBrandName(obj[16].toString());
				}
				/*if(obj[17]!=null && !("".equals(obj[17]))){
					retreatVo1.setRetreatId((Integer)obj[17]);
				}*/
				
				rows.add(retreatVo1);
			}
		}
		int total = instoreMoveDAO.getSQLCount(sql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	@Log(systemName="销售系统", moduleName="车辆移库管理",opertype="审核")
	public void updateExamine(SellRetreatVo instoreMoveVo) throws Exception {
		List<BaseBean>  sellRetreat= instoreMoveDAO.find(" from XsSellRetreat retreat where 1=1 and  retreat.retreatCode='"+instoreMoveVo.getRetreatCode()+"'");
		if(sellRetreat!=null && sellRetreat.size()>0){
			String contex="";
			for(BaseBean b:sellRetreat){
				XsSellRetreat retreat =(XsSellRetreat) b;
	//	XsSellRetreat  retreat=(XsSellRetreat) instoreMoveDAO.get(" from XsSellRetreat retreat where 1=1 and  retreat.retreatId='"+instoreMoveVo.getRetreatId()+"'");
	
		int examine=baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE, Contstants.BASE_SELL.ADUIT,instoreMoveVo.getEnterprise_id());
		if(retreat.getExamine()== examine){
			Integer examine1=baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,instoreMoveVo.getEnterprise_id());
			if(examine1!=null){
				retreat.setExamine(examine1);
				contex="审核单号为【"+retreat.getRetreatCode()+"】的移库单";
			}
		}else{
			retreat.setExamine(examine);
			contex="取消审核单号为【"+retreat.getRetreatCode()+"】的移库单";
		}
		instoreMoveDAO.update(retreat);
			}
			setContent(""+contex+"");
			
		}
		
	}
	
	@Log(systemName="销售系统", moduleName="车辆移库管理",opertype="修改")
	public void updateInstore(SellRetreatVo instoreMoveVo) throws Exception {
		//明细
		List<SellRetreatVo> insertedList = JSON.parseArray(instoreMoveVo.getInserted(), SellRetreatVo.class);	
		//List<SellRetreatVo> updateList = JSON.parseArray(instoreMoveVo.getUpdated(), SellRetreatVo.class);	
		List<SellRetreatVo> deleteList = JSON.parseArray(instoreMoveVo.getDeleted(), SellRetreatVo.class);	
		SellRetreatVo retreat = JSON.parseObject(instoreMoveVo.getSellRetreat(), SellRetreatVo.class);
		List<BaseBean>  sellRet= instoreMoveDAO.find(" from XsSellRetreat retreat where 1=1 and  retreat.retreatCode='"+retreat.getRetreatCode()+"'");
		if(sellRet != null && sellRet.size()>0){
			for(BaseBean  b:sellRet){	
				XsSellRetreat retreats=(XsSellRetreat)b;
				retreats.setContext(retreat.getContext());
				retreats.setInInstorehouse(retreat.getInInstorehouse());
				instoreMoveDAO.merge(retreats);
				
			}
		}
		XsSellRetreat ret=(XsSellRetreat) instoreMoveDAO.get(" from XsSellRetreat where retreatCode='"+retreat.getRetreatCode()+"'");
		ret.setContext(retreat.getContext());
		instoreMoveDAO.merge(ret);
		if(insertedList !=null && insertedList.size()>0){
			for(SellRetreatVo  insertedVo:insertedList){
				XsSellInstorehouseDel instoreDel=(XsSellInstorehouseDel) instoreMoveDAO.get("from XsSellInstorehouseDel del where  del.detailsId="+ insertedVo.getDetailsId_());
				XsCarInfo car=instoreDel.getCarInfo();
				//移库单
				XsSellRetreat retreatInserted = JSON.parseObject(instoreMoveVo.getSellRetreat(), XsSellRetreat.class);
				retreatInserted.setOutInstorehouse(insertedVo.getWarehouse());
				retreatInserted.setRetreatCode(retreatInserted.getRetreatCode());
				retreatInserted.setInInstorehouse(retreatInserted.getInInstorehouse());
				retreatInserted.setExamine(baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE, Contstants.BASE_SELL.ADUIT,instoreMoveVo.getEnterprise_id()));
				retreatInserted.setDetails(instoreDel);
				retreatInserted.setInstorehouseType(baseTagDAO.findChildCon(Contstants.INSTORESTYLE.MOVE,Contstants.INSTORESTYLE.PARENTINSTORE,instoreMoveVo.getEnterprise_id()));
				retreatInserted.setContext(retreatInserted.getContext());
				retreatInserted.setEnterprise_id(retreatInserted.getEnterprise_id());
				instoreMoveDAO.save(retreatInserted);
				
				
			}	
		}
	/*	if(updateList !=null && updateList.size()>0){
			//退车单
			XsSellRetreat retreatUpdated = JSON.parseObject(retreatVo.getUpdated(), XsSellRetreat.class);
			
			
		}*/
		if(deleteList !=null && deleteList.size()>0){	
			for(SellRetreatVo  deletedVo:deleteList){			
				XsSellRetreat  retreats=(XsSellRetreat) instoreMoveDAO.get(" from XsSellRetreat retreat where 1=1 and  retreat.retreatId='"+deletedVo.getRetreatId()+"'");
				instoreMoveDAO.delete(retreats);
			}
			
		}
		XsChilddictionary type=baseTagDAO.findById(ret.getInInstorehouse());
		XsChilddictionary type2=baseTagDAO.findById(ret.getOutInstorehouse());
		
		setContent("修改【车辆移库管理】编号为【"+ret.getRetreatCode()+"】," +
				"现在库为【"+type.getDataValue()+"】,原来库为【"+type2.getDataValue()+"】," +
						"移库理由为【"+ret.getContext()+"】的信息！");
	}
	/**
	 * 判断是否已审核
	 * */
	
	public Boolean isRefundment(SellRetreatVo instoreMoveVo) throws Exception {
		Integer examine=baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,instoreMoveVo.getEnterprise_id());
		if(instoreMoveVo.getExamine().equals(examine))
			return true;
		return false;
	}
	
	
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}
	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}
	public InstoreMoveDAO getInstoreMoveDAO() {
		return instoreMoveDAO;
	}
	@Autowired
	public void setInstoreMoveDAO(InstoreMoveDAO instoreMoveDAO) {
		this.instoreMoveDAO = instoreMoveDAO;
	}
}
