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
import com.syuesoft.combine.vo.BalanceRateAnalayseVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.dao.SupplierInfoDAO;
import com.syuesoft.sell.instore.dao.RetreatDAO;
import com.syuesoft.sell.instore.service.RetreatService;
import com.syuesoft.sell.instore.vo.SellInstorehouseVo;
import com.syuesoft.sell.instore.vo.SellRetreatVo;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellInstorehouseDel;
import com.syuesoft.sell.model.XsSellRetreat;
import com.syuesoft.sell.model.XsSupplierInfo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Utils;

@Service("retreatService")
public class RetreatServiceImpl extends BaseLogServiceImpl implements RetreatService {
	private RetreatDAO retreatDAO;
	private BaseTagDAO baseTagDAO;
	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	@Autowired
	private SupplierInfoDAO supplierInfoDAO;
	
	public Datagrid getPager(SellRetreatVo  retreatVo) throws Exception{
		Datagrid dg = new Datagrid();
		StringBuffer sql=new StringBuffer("SELECT   retreat.retreat_code, retreat.retreat_date," +
				"retreat.examine,retreat.context_,retreat.enterprise_id,ba.STF_NAME,retreat.xs_supplier_id," +
				"retreat.retreat_id  " +
				"FROM  " +
				"xs_sell_retreat retreat,xs_sell_instorehouse_del del,  xs_car_info  car,bas_stuff ba   WHERE   retreat.details_id=del.details_id " +
				" AND del.xs_car_id=car.xs_car_id and ba.STF_ID=retreat.person_  and  " +
				" retreat.enterprise_id="+retreatVo.getEnterprise_id() +
				" and retreat.instorehouse_type="+baseTagDAO.findChildCon(Contstants.INSTORESTYLE.BACK, Contstants.INSTORESTYLE.PARENTINSTORE,retreatVo.getEnterprise_id()));	
		if(retreatVo.getRetreatDateStart()!=null && !("".equals(retreatVo.getRetreatDateStart()))){
				sql.append(" AND   DATE(retreat.retreat_date)>='"+retreatVo.getRetreatDateStart()+"'");
			}
		if(retreatVo.getRetreatDateEnd()!=null && !("".equals(retreatVo.getRetreatDateEnd()))){
				sql.append("  AND   DATE(retreat.retreat_date)<='"+retreatVo.getRetreatDateEnd()+"'");
		}		
		if((retreatVo.getRetreatDateStart()==null|| "".equals(retreatVo.getRetreatDateStart()))&&
				(retreatVo.getRetreatDateEnd()==null||"".equals(retreatVo.getRetreatDateEnd()))){

			sql.append("  AND   DATE(retreat.retreat_date) BETWEEN " +
					"'"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (
							basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,retreatVo.getEnterprise_id()).getCiValue()))+"'" +
					" and '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if(retreatVo.getQueryRetreatCode()!=null && !("".equals(retreatVo.getQueryRetreatCode()))){
			sql.append("  AND  retreat.retreat_code  like '%"+StringEscapeUtils.escapeSql(retreatVo.getQueryRetreatCode().trim())+"%'");
		}
		if(retreatVo.getQuerySupplier()!=null && !("".equals(retreatVo.getQuerySupplier()))){
			sql.append("   AND retreat.xs_supplier_id="+retreatVo.getQuerySupplier());
		}
		if(retreatVo.getQueryExamine()!=null && !("".equals(retreatVo.getQueryExamine()))){
			sql.append(" AND  retreat.examine="+retreatVo.getQueryExamine());
		}
		if(retreatVo.getQueryVinNumber()!=null && !("".equals(retreatVo.getQueryVinNumber()))){
			sql.append("  AND car.xs_car_vin_number like '%"+retreatVo.getQueryVinNumber()+"%'");
		}
		sql.append("  GROUP BY retreat.retreat_code order by retreat.retreat_code desc ");
		List<Object[]> lst=retreatDAO.createSQLQuery(sql.toString(), retreatVo.getPage(), retreatVo.getRows());
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
					retreatVo1.setEnterprise_id( (Integer) obj[4]);
				}
				if(obj[5]!=null && !("".equals(obj[5]))){
					retreatVo1.setPersonName(obj[5].toString());
				}
				if(obj[6]!=null && !("".equals(obj[6]))){
					retreatVo1.setXsSupplierId((Integer) obj[6]);
				}
				if(obj[7]!=null && !("".equals(obj[7]))){
					retreatVo1.setRetreatId((Integer) obj[7]);
				}
				rows.add(retreatVo1);
			}
		}
		int total = retreatDAO.getSQLCount(sql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	//根据退车单编号，获取明细信息
	
	public Datagrid getPagerDel(SellRetreatVo  retreatVo) throws Exception{
		Datagrid dg = new Datagrid();
		StringBuffer sql=new StringBuffer("SELECT   del.details_id, del.instorehouse_id,del.xs_car_id, del.notax, del.tax,car.xs_car_code,car.xs_car_vin_number,"+
				" model.xs_model_salesPrice, car.xs_car_model_id,(SELECT model.xs_model_name FROM xs_car_model model WHERE model.xs_model_id=car.xs_car_model_id) "+			
				"AS  xs_car_model_name, car.xs_car_ocn,car.xs_car_motor_number,car.xs_car_color,(SELECT child.dataValue FROM xs_childdictionary  child WHERE child.child_id=car.xs_car_color) "+
				"AS  xs_car_color_name,retreat.retreat_id,car.xs_car_brand,(SELECT child.dataValue FROM xs_childdictionary  child WHERE child.child_id=car.xs_car_brand) AS brandName" +
				"  FROM xs_sell_retreat retreat," +
				"xs_sell_instorehouse_del del, xs_car_info  car," +
				"xs_car_model model  WHERE retreat.details_id = del.details_id"+
				"  AND car.xs_car_id=del.xs_car_id AND model.xs_model_id=car.xs_car_model_id   " +
				"AND   retreat.retreat_code = '"+retreatVo.getRetreatCode()+"'");	
		List<Object[]> lst=retreatDAO.createSQLQuery(sql.toString(), retreatVo.getPage(), retreatVo.getRows());
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
					retreatVo1.setCarInfo_((Integer) obj[2]);
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
					retreatVo1.setCarMotorNumber(obj[11].toString());
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
				
				rows.add(retreatVo1);
			}
		}
		int total = retreatDAO.getSQLCount(sql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	//删除退车单
	
	@Log(systemName="销售系统", moduleName="退厂单管理",opertype="删除")
	public void deleteRetreat(SellRetreatVo  retreatVo) throws Exception{	
		
		//获取同一个编号的退车单集合
		List<BaseBean>  sellRetreat= retreatDAO.find(" from XsSellRetreat retreat where 1=1 and  retreat.retreatCode='"+retreatVo.getRetreatCode()+"'");
		if(sellRetreat!=null && sellRetreat.size()>0){
			for(BaseBean b:sellRetreat){
				XsSellRetreat retreat =(XsSellRetreat) b;
				XsSellInstorehouseDel instoreDel=(XsSellInstorehouseDel) retreatDAO.get(" from XsSellInstorehouseDel del where 1=1 and  del.detailsId="+retreat.getDetails().getDetailsId());
				//查询库存状态
				Integer instore=baseTagDAO.findChildCon(Contstants.INSTORETYPE.INSTORE, Contstants.BASE_SELL.INSTORETYPE,retreatVo.getEnterprise_id());
				if(instore!=null){
					instoreDel.setSellAllocatelType(instore);
				}
				//更新入库单明细中，是否退厂状态
				retreatDAO.merge(instoreDel);
				//更新车的销售状态
				XsCarInfo  car=instoreDel.getCarInfo();
				XsChilddictionary carState=baseTagDAO.findChildsCon(Contstants.SELLSTATE.FORSALE, Contstants.SELLSTATE.BASE_SELLSTATE,retreatVo.getEnterprise_id());
				if(carState!=null){
					car.setSellStateChild(carState);
				}
				retreatDAO.merge(car);
				//删除退厂记录
				retreatDAO.delete(retreat);
				setContent("删除编码为【"+retreat.getRetreatCode()+"】的【退厂单管理】信息！");
				
			}
		}
	}
	//保存退车单
	
	@Log(systemName="销售系统", moduleName="退厂单管理",opertype="新增")
	public void addRetreat(SellRetreatVo  retreatVo) throws Exception{
		
		//明细
		List<SellRetreatVo> insertedList = JSON.parseArray(retreatVo.getInstoreDelGrid(), SellRetreatVo.class);	
		if(insertedList != null){
			String code = CreateID.createId("sellRetreat", Contstants.SELL_BILLSDEPLOY.RETREAT);
		    for(SellRetreatVo  retreatVo1:insertedList){
				XsSellInstorehouseDel instoreDel=(XsSellInstorehouseDel) retreatDAO.get("from XsSellInstorehouseDel del where  del.detailsId="+retreatVo1.getDetailsId());
				//查询库存状态
				Integer instore=baseTagDAO.findChildCon(Contstants.INSTORETYPE.CARBACK, Contstants.BASE_SELL.INSTORETYPE,retreatVo.getEnterprise_id());
				if(instore!=null){
					instoreDel.setSellAllocatelType(instore);
				}
				retreatDAO.merge(instoreDel);
				//更新车的销售状态
				XsCarInfo  car=instoreDel.getCarInfo();
				XsChilddictionary  back=baseTagDAO.findChildsCon(Contstants.SELLSTATE.CARBACK, Contstants.SELLSTATE.BASE_SELLSTATE,retreatVo.getEnterprise_id());
				if(back!=null){
					car.setSellStateChild(back);
					car.setCarFristInsuranceData(null);
				}
				retreatDAO.merge(car);
				//退车单
				XsSellRetreat sellRetreat = JSON.parseObject(retreatVo.getSellRetreat(), XsSellRetreat.class);
				sellRetreat.setRetreatCode(code);
				Integer examineState=baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE, Contstants.BASE_SELL.ADUIT,retreatVo.getEnterprise_id());
				if(examineState!=null){
					sellRetreat.setExamine(examineState);
				}
			
				sellRetreat.setDetails(instoreDel);
				sellRetreat.setInstorehouseType(baseTagDAO.findChildCon(Contstants.INSTORESTYLE.BACK, Contstants.INSTORESTYLE.PARENTINSTORE,retreatVo.getEnterprise_id()));
				sellRetreat.setEnterprise_id(retreatVo.getEnterprise_id());
				retreatDAO.save(sellRetreat);
				XsSupplierInfo supplier=supplierInfoDAO.get(" from XsSupplierInfo where supplierId="+sellRetreat.getXsSupplierId());
				setContent("新增【退厂单管理】编码为【"+sellRetreat.getRetreatCode()+"】,退车日期【"+sellRetreat.getRetreatDate()+"】," +
								"供应商为【"+supplier.getSupplierName()+"】的信息！");
			
			}
		}
	}
	@Log(systemName="销售系统", moduleName="退厂单管理",opertype="审核")
	public void updateExamine(SellRetreatVo  retreatVo)throws Exception{
		//获取同一个编号的退车单集合
		List<BaseBean>  sellRetreat= retreatDAO.find(" from XsSellRetreat retreat where 1=1 and  retreat.retreatCode='"+retreatVo.getRetreatCode()+"'");
		String contex="";
		if(sellRetreat != null && sellRetreat.size()>0){
			
			for(BaseBean  b:sellRetreat){
				
				XsSellRetreat retreat=(XsSellRetreat)b;
				int examine1=baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,retreatVo.getEnterprise_id());
				int examine=baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE, Contstants.BASE_SELL.ADUIT,retreatVo.getEnterprise_id());
				if(retreat.getExamine()==examine1){
					retreat.setExamine(examine);
					contex="取消审核单号为【"+retreatVo.getRetreatCode()+"】的退厂单";
				}else{
					retreat.setExamine(examine1);
					contex="审核单号为【"+retreatVo.getRetreatCode()+"】的退厂单";
				}
				retreatDAO.merge(retreat);
				
				//XsSellInstorehouseDel instoreDel=(XsSellInstorehouseDel) retreatDAO.get("from XsSellInstorehouseDel del where  del.detailsId="+retreat.getDetails().getDetailsId());
			}	
			setContent(""+contex+"");
		}		
	}
	//修改退车单
	
	@Log(systemName="销售系统", moduleName="退厂单管理",opertype="修改")
	public void updateRetreat(SellRetreatVo  retreatVo) throws Exception{
		//明细
		List<SellRetreatVo> insertedList = JSON.parseArray(retreatVo.getInserted(), SellRetreatVo.class);	
		List<SellRetreatVo> updateList = JSON.parseArray(retreatVo.getUpdated(), SellRetreatVo.class);	
		List<SellRetreatVo> deleteList = JSON.parseArray(retreatVo.getDeleted(), SellRetreatVo.class);	
		SellRetreatVo retreatInserted = JSON.parseObject(retreatVo.getSellRetreat(), SellRetreatVo.class);
		List<BaseBean>  sellRet= retreatDAO.find(" from XsSellRetreat retreat where 1=1 and  retreat.retreatCode='"+retreatInserted.getRetreatCode()+"'");
		if(sellRet != null && sellRet.size()>0){
			for(BaseBean  b:sellRet){	
				XsSellRetreat retreats=(XsSellRetreat)b;
				retreats.setContext(retreatInserted.getContext());
				retreats.setXsSupplierId(retreatInserted.getXsSupplierId());
				retreatDAO.merge(retreats);
				
			}
		}
		if(insertedList !=null && insertedList.size()>0){
			for(SellRetreatVo  insertedVo:insertedList){
				XsSellInstorehouseDel instoreDel=(XsSellInstorehouseDel) retreatDAO.get("from XsSellInstorehouseDel del where  del.detailsId="+insertedVo.getDetailsId());
				//查询库存状态
				Integer instore=baseTagDAO.findChildCon(Contstants.INSTORETYPE.CARBACK, Contstants.BASE_SELL.INSTORETYPE,retreatVo.getEnterprise_id());
				if(instore!=null){
					instoreDel.setSellAllocatelType(instore);
				}
				retreatDAO.merge(instoreDel);
				//更新车的销售状态
				XsCarInfo  car=instoreDel.getCarInfo();
				XsChilddictionary back=baseTagDAO.findChildsCon(Contstants.SELLSTATE.CARBACK, Contstants.SELLSTATE.BASE_SELLSTATE,retreatVo.getEnterprise_id());
				if(back!=null){
					car.setSellStateChild(back);
				}
				retreatDAO.merge(car);
				//退车单
				XsSellRetreat sellRetreat = JSON.parseObject(retreatVo.getSellRetreat(), XsSellRetreat.class);
				Integer examineState=baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE, Contstants.BASE_SELL.ADUIT,retreatVo.getEnterprise_id());
				if(examineState!=null){
					sellRetreat.setExamine(examineState);
				}
				sellRetreat.setDetails(instoreDel);
				sellRetreat.setInstorehouseType(baseTagDAO.findChildCon(Contstants.INSTORESTYLE.BACK, Contstants.INSTORESTYLE.PARENTINSTORE,retreatVo.getEnterprise_id()));
				retreatDAO.save(sellRetreat);
				
			}	
		}
		if(updateList !=null && updateList.size()>0){
			//退车单
			XsSellRetreat retreatUpdated = JSON.parseObject(retreatVo.getUpdated(), XsSellRetreat.class);
			retreatDAO.merge(retreatUpdated);
		}
		if(deleteList !=null && deleteList.size()>0){	
			for(SellRetreatVo  deletedVo:deleteList){
				XsSellInstorehouseDel instoreDel=(XsSellInstorehouseDel) retreatDAO.get("from XsSellInstorehouseDel del where  del.detailsId="+deletedVo.getDetailsId());
				//查询库存状态
				Integer instore=baseTagDAO.findChildCon(Contstants.INSTORETYPE.INSTORE, Contstants.BASE_SELL.INSTORETYPE,retreatVo.getEnterprise_id());
				if(instore!=null){
					instoreDel.setSellAllocatelType(instore);
				}
				retreatDAO.merge(instoreDel);
				//更新车的销售状态
				XsCarInfo  car=instoreDel.getCarInfo();
				XsChilddictionary back=baseTagDAO.findChildsCon(Contstants.SELLSTATE.FORSALE, Contstants.SELLSTATE.BASE_SELLSTATE,retreatVo.getEnterprise_id());
				if(back!=null){
					car.setSellStateChild(back);
				}
				retreatDAO.merge(car);
				//退车单
				XsSellRetreat  retreat=(XsSellRetreat) retreatDAO.get(" from XsSellRetreat retreat where 1=1 and  retreat.retreatId='"+deletedVo.getRetreatId()+"'");
				retreatDAO.delete(retreat);
			}
		}

		setContent("修改【退厂单管理】编码为【"+retreatInserted.getRetreatCode()+"】,退车日期【"+retreatInserted.getRetreatDate()+"】," +
				"退车理由为【"+retreatInserted.getContext()+"】！");
	}
	
	public DatagridAnalyze findQueryBack(SellRetreatVo retreatVo) throws Exception {
		DatagridAnalyze dg = new DatagridAnalyze();
		
		StringBuffer sql = new StringBuffer("SELECT SUM(model.xs_model_salesPrice),SUM(del.tax)  AS tax1,SUM(del.notax) AS notax1,SUM(del.tax+del.notax),car.xs_car_model_id,"+
						  " model.xs_model_name  " +
						  " FROM xs_sell_retreat  r"+
						  "   JOIN xs_sell_instorehouse_del del ON r.details_id=del.details_id " +
						  " JOIN xs_car_info car ON del.xs_car_id=car.xs_car_id	" +
						  " join xs_car_model model on model.xs_model_id = car.xs_car_model_id "+
						  " JOIN xs_sell_instorehouse h ON h.instorehouse_id=del.instorehouse_id" );
		if(retreatVo.getCarState()!=null&&!"".equals(retreatVo.getCarState())&&"carOut".equals(retreatVo.getCarState())){
			sql.append(" where   r.instorehouse_type=(SELECT child.child_id FROM xs_childdictionary  child, "
						+ "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
						+ "AND parent.dataKey='"
						+ Contstants.INSTORESTYLE.PARENTINSTORE
						+ "' AND child.dataKey='"
						+ Contstants.INSTORESTYLE.OUT
						+ "' and child.enterprise_id="+retreatVo.getEnterprise_id()+")"
						+ "");
		}else{
		sql.append(  " where  r.instorehouse_type=" +
				""+baseTagDAO.findChildCon(Contstants.INSTORESTYLE.BACK, Contstants.INSTORESTYLE.PARENTINSTORE,retreatVo.getEnterprise_id()));
		} 
		if (retreatVo.getInstoreStart() != null
				&& !("".equals(retreatVo.getInstoreStart()))) {
			sql.append(" and DATE(h.instorehouse_date)>= '"
					+ retreatVo.getInstoreStart() + "'");
		}
		if (retreatVo.getInstoreEnd() != null
				&& !("".equals(retreatVo.getInstoreEnd()))) {
			sql.append(" and DATE(h.instorehouse_date) <= '"
					+ retreatVo.getInstoreEnd() + "'");
		}
		if (retreatVo.getSupplierId() != null
				&& !("".equals(retreatVo.getSupplierId()))) {
			sql.append("  and h.xs_supplier_id ="
					+ retreatVo.getSupplierId());
		}
		if (retreatVo.getCarBrand() != null
				&& !("".equals(retreatVo.getCarBrand()))) {
			sql.append("  and car.xs_car_brand =" + retreatVo.getCarBrand());
		}
		if (retreatVo.getQuerymodel() != null
				&& !("".equals(retreatVo.getQuerymodel()))) {
			sql.append("  and car.xs_car_model_id =" + retreatVo.getQuerymodel());
		}
		
			if (retreatVo.getReDateStart() != null
					&& !("".equals(retreatVo.getReDateStart()))) {
				sql.append(" and Date(r.retreat_date) >= '"
						+ retreatVo.getReDateStart() + "'");
			}
			if (retreatVo.getRepyDateEnd() != null
					&& !"".equals(retreatVo.getRepyDateEnd())) {
				sql.append(" and Date(r.retreat_date)<= '"
						+ retreatVo.getRepyDateEnd() + "'");
			
		}if((retreatVo.getReDateStart() == null|| ("".equals(retreatVo.getReDateStart())))
				&&(retreatVo.getRepyDateEnd() == null
				|| "".equals(retreatVo.getRepyDateEnd()))){
				sql.append(" and Date(r.retreat_date) between '"
								+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,retreatVo.getEnterprise_id()).getCiValue())) + "'" +
							" and '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+ "'");
		}
		
		if (retreatVo.getCarVinNumber() != null
				&& !"".equals(retreatVo.getCarVinNumber())) {
			sql.append("  and car.xs_car_vin_number like '%"
					+ StringEscapeUtils.escapeSql(retreatVo.getCarVinNumber()
							.trim()) + "%'");
		}
		if (retreatVo.getCarOcn() != null
				&& !("".equals(retreatVo.getCarOcn()))) {
			sql.append("  and car.xs_car_ocn  like '%"
					+ StringEscapeUtils.escapeSql(retreatVo.getCarOcn().trim())
					+ "%'");
		}
	
		if (retreatVo.getRetreatCode() != null
				&& !("".equals(retreatVo.getRetreatCode()))) {
			sql.append("  and r.retreat_code like '%"
					+ StringEscapeUtils.escapeSql(retreatVo
							.getRetreatCode().trim()) + "%'");
		}
		if (retreatVo.getPerson() != null && !("".equals(retreatVo.getPerson()))) {
			sql.append("  and r.person_ =" + retreatVo.getPerson());
		}
		if (retreatVo.getExamine() != null
				&& !("".equals(retreatVo.getExamine()))) {
			sql.append("  and r.examine ="
					+ retreatVo.getExamine());
		}
		
		sql.append(" and r.enterprise_id="+retreatVo.getEnterprise_id()+" GROUP BY  car.xs_car_model_id");
		List<Object[]> resultList = retreatDAO.createSQLQuery(sql.toString(),
				retreatVo.getPage(), retreatVo.getRows());
		List<SellRetreatVo> list = new ArrayList<SellRetreatVo>();
		List<SellRetreatVo> footers=new ArrayList<SellRetreatVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SellRetreatVo rVo=null;
			for (int i = 0; i < resultList.size(); i++) {
				obj=resultList.get(i);
				 rVo=new SellRetreatVo();
				if(obj[0]!=null && !("".equals(obj[0]))){
					rVo.setVehicleCost((Double) obj[0]);
				}
				if(obj[1]!=null && !("".equals(obj[1]))){
					rVo.setTax((Double) obj[1]);			
				}
				if(obj[2]!=null && !("".equals(obj[2]))){
					rVo.setNotax((Double) obj[2]);
				}
				if(obj[3]!=null && !("".equals(obj[3]))){
					rVo.setSumTax((Double) obj[3]);
				}
				if(obj[4]!=null && !("".equals(obj[4]))){
					rVo.setCarModelId((Integer) obj[4]);
				}
				if(obj[5]!=null && !("".equals(obj[5]))){
					rVo.setCarModelName(obj[5].toString());
				}
				rVo.setState("closed");
				rVo.setIconCls("icon-blank");
				list.add(rVo);
			}
			
			
				rVo=new SellRetreatVo();
				rVo.setCarModelName("汇总");
				rVo.setState("open");
				rVo.setIconCls("icon-blank");
				 Double sum1=0.0;
				 Double sum2=0.0;
				 Double sum3=0.0;
				 Double sum4=0.0;
		for (SellRetreatVo sell : list) {
			if(sell.getVehicleCost()!=null&&!"".equals(sell.getVehicleCost())){
				 sum1+=sell.getVehicleCost();
			}
			if(sell.getTax()!=null&&!"".equals(sell.getTax())){
				 sum2+=sell.getTax();
			}
			if(sell.getNotax()!=null&&!"".equals(sell.getNotax())){
				 sum3+=sell.getNotax();
			}
			if(sell.getSumTax()!=null&&!"".equals(sell.getSumTax())){
				 sum4+=sell.getSumTax();
			}
			
		}	
		rVo.setVehicleCost(Utils.doubleFormat(sum1));
		rVo.setTax(Utils.doubleFormat(sum2));
		rVo.setNotax(Utils.doubleFormat(sum3));
		rVo.setSumTax(Utils.doubleFormat(sum4));
		footers.add(rVo);

		}
		int total = retreatDAO.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setFooter(footers);
		dg.setTotal(total);
		return dg;
	}
	
	public List<SellRetreatVo> getTreegridChild(SellRetreatVo retreatVo) throws Exception {
		List<SellRetreatVo> list_ = new ArrayList<SellRetreatVo>();
		StringBuffer sql = new StringBuffer("SELECT  r.retreat_code,car.xs_car_vin_number,car.xs_car_motor_number,car.xs_car_ocn,car.xs_car_color,(SELECT c.dataValue FROM xs_childdictionary c WHERE c.child_id " +
						   "= car.xs_car_color) AS colorName,car.xs_car_code,r.retreat_date,house.xs_supplier_id,(SELECT s.xs_supplier_name FROM xs_supplier_info s "+
						   " WHERE s.xs_supplier_id = house.xs_supplier_id) AS supplierName,house.receipt,del.tax,del.notax,(del.tax+del.notax)  AS sumTax,model.xs_model_salesPrice "+
						   " FROM xs_sell_retreat  r " +
						   " JOIN xs_sell_instorehouse_del del ON r.details_id=del.details_id " +
						   " JOIN xs_car_info car ON del.xs_car_id=car.xs_car_id " +
						   " join xs_car_model model on model.xs_model_id = car.xs_car_model_id  "+
						   " JOIN xs_sell_instorehouse house ON house.instorehouse_id=del.instorehouse_id " );
		if(retreatVo.getCarState()!=null&&!"".equals(retreatVo.getCarState())&&"carOut".equals(retreatVo.getCarState())){
			sql.append(" where  r.instorehouse_type=" +
					""+baseTagDAO.findChildCon(Contstants.INSTORESTYLE.OUT,Contstants.INSTORESTYLE.PARENTINSTORE,retreatVo.getEnterprise_id()));
			}else{
				sql.append(	   "WHERE   r.instorehouse_type=" +
						""+baseTagDAO.findChildCon(Contstants.INSTORESTYLE.BACK, Contstants.INSTORESTYLE.PARENTINSTORE,retreatVo.getEnterprise_id()) );
			}
		
		
		if (retreatVo.getInstoreStart() != null
				&& !("".equals(retreatVo.getInstoreStart()))) {
			sql.append(" and DATE(house.instorehouse_date) >= '"
					+ retreatVo.getInstoreStart() + "'");
		}
		if (retreatVo.getInstoreEnd() != null
				&& !("".equals(retreatVo.getInstoreEnd()))) {
			sql.append(" and DATE(house.instorehouse_date) <= '"
					+ retreatVo.getInstoreEnd() + "'");
		}
		if (retreatVo.getSupplierId() != null
				&& !("".equals(retreatVo.getSupplierId()))) {
			sql.append("  and house.xs_supplier_id ="
					+ retreatVo.getSupplierId());
		}
		if (retreatVo.getCarBrand() != null
				&& !("".equals(retreatVo.getCarBrand()))) {
			sql.append("  and car.xs_car_brand =" + retreatVo.getCarBrand());
		}
		
		if (retreatVo.getReDateStart() != null
					&& !("".equals(retreatVo.getReDateStart()))) {
				sql.append(" and r.retreat_date >= '"
						+ retreatVo.getReDateStart() + "'");
			}
		if (retreatVo.getRepyDateEnd() != null
					&& !"".equals(retreatVo.getRepyDateEnd())) {
				sql.append(" and r.retreat_date <= '"
						+ retreatVo.getRepyDateEnd() + "'");
			
		}
		if((retreatVo.getReDateStart() == null
				||"".equals(retreatVo.getReDateStart()))&&(retreatVo.getRepyDateEnd() == null
					||"".equals(retreatVo.getRepyDateEnd()))){
				sql.append(" and r.retreat_date between '"
								+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,retreatVo.getEnterprise_id()).getCiValue())) + "'" +
							" and '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+ "'");
		}
		if (retreatVo.getCarVinNumber() != null
				&& !"".equals(retreatVo.getCarVinNumber())) {
			sql.append("  and car.xs_car_vin_number like '%"
					+ StringEscapeUtils.escapeSql(retreatVo.getCarVinNumber()
							.trim()) + "%'");
		}
		if (retreatVo.getCarOcn() != null
				&& !("".equals(retreatVo.getCarOcn()))) {
			sql.append("  and car.xs_car_ocn  like '%"
					+ StringEscapeUtils.escapeSql(retreatVo.getCarOcn().trim())
					+ "%'");
		}
	
		if (retreatVo.getRetreatCode() != null
				&& !("".equals(retreatVo.getRetreatCode()))) {
			sql.append("  and r.retreat_code like '%"
					+ StringEscapeUtils.escapeSql(retreatVo
							.getRetreatCode().trim()) + "%'");
		}
		if (retreatVo.getPerson() != null && !("".equals(retreatVo.getPerson()))) {
			sql.append("  and r.person_ =" + retreatVo.getPerson());
		}
		if (retreatVo.getExamine() != null
				&& !("".equals(retreatVo.getExamine()))) {
			sql.append("  and r.examine ="
					+ retreatVo.getExamine());
		}
		sql.append(" and r.enterprise_id="+retreatVo.getEnterprise_id()+" AND car.xs_car_model_id ="+retreatVo.getCarModelId());
	
		List<Object[]> resultList = retreatDAO.createSQLQuery(sql.toString());
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				Object [] obj=resultList.get(i);
				SellRetreatVo rVo=new SellRetreatVo();
				
				if(obj[0]!=null && !("".equals(obj[0]))){
					rVo.setCarModelName(obj[0].toString());			
				}
				
				if(obj[1]!=null && !("".equals(obj[1]))){
					rVo.setCarVinNumber(obj[1].toString());
				}
				if(obj[2]!=null && !("".equals(obj[2]))){
					rVo.setCarMotorNumber(obj[2].toString());
				}
				if(obj[3]!=null && !("".equals(obj[3]))){
					rVo.setCarOcn(obj[3].toString());
				}
				if(obj[4]!=null && !("".equals(obj[4]))){
					rVo.setCarColor((Integer) obj[4]);
				}
				if(obj[5]!=null && !("".equals(obj[5]))){
					rVo.setColorName(obj[5].toString());			
				}
				if(obj[6]!=null && !("".equals(obj[6]))){
					rVo.setInstorehouseCode(obj[6].toString());
				}
				if(obj[7]!=null && !("".equals(obj[7]))){
					rVo.setInstorehouseDate((Timestamp) obj[7]);
				}
				if(obj[8]!=null && !("".equals(obj[8]))){
					rVo.setSupplierId((Integer) obj[8]);
				}
				if(obj[9]!=null && !("".equals(obj[9]))){
					rVo.setSupplier(obj[9].toString());
				}
				if(obj[10]!=null && !("".equals(obj[10]))){
					rVo.setReceipt(obj[10].toString());
				}
				if(obj[11]!=null && !("".equals(obj[11]))){
					rVo.setTax((Double) obj[11]);			
				}
				if(obj[12]!=null && !("".equals(obj[12]))){
					rVo.setNotax((Double) obj[12]);
				}
				if(obj[13]!=null && !("".equals(obj[13]))){
					rVo.setSumTax((Double) obj[13]);
				}
				if(obj[14]!=null && !("".equals(obj[14]))){
					rVo.setVehicleCost((Double) obj[14]);
				}
				rVo.setIconCls("icon-blank");
				rVo.setState("open");
				list_.add(rVo);
			}
		}
		return list_;
	}
	/**
	 * 判断是否已审核
	 * */
	
	public Boolean isRefundment(SellRetreatVo retreatVo) throws Exception {
		Integer examine=baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,retreatVo.getEnterprise_id());
		if(retreatVo.getExamine().equals(examine))
			return true;
		return false;
	}
	
	public RetreatDAO getRetreatDAO() {
		return retreatDAO;
	}
	@Autowired
	public void setRetreatDAO(RetreatDAO retreatDAO) {
		this.retreatDAO = retreatDAO;
	}
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}
	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}	
	
}
