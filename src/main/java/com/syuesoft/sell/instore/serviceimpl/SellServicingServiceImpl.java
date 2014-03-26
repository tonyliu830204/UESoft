
package com.syuesoft.sell.instore.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.instore.dao.SellServicingDAO;
import com.syuesoft.sell.instore.service.SellServicingService;
import com.syuesoft.sell.instore.vo.SellServicingVo;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellInstorehouseDel;
import com.syuesoft.sell.model.XsSellServicing;
import com.syuesoft.util.FormatTime;

@Service("sellServicingService")
public class SellServicingServiceImpl extends BaseLogServiceImpl implements SellServicingService {
	private SellServicingDAO  sellServicingDAO ; 
	private BaseTagDAO baseTagDAO;
	@Autowired 
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	//库存车辆维护分页
	
	public Datagrid getPager(SellServicingVo servicingVo) throws Exception{
		Datagrid dg = new Datagrid();
		//在库待销
		Integer sellState=baseTagDAO.findChildCon(Contstants.SELLSTATE.FORSALE, Contstants.SELLSTATE.BASE_SELLSTATE,servicingVo.getEnterprise_id());
		StringBuffer sql=new StringBuffer("SELECT  del.details_id,del.xs_car_id,car.xs_car_vin_number,car.xs_car_model_id,model.xs_model_name,car.servicing_nextdate,"+
						  "	car.xs_car_sell_state,(SELECT  c.dataValue  FROM xs_childdictionary c WHERE c.child_id = car.xs_car_sell_state) AS sell_state_name,"+
						  "	car.xs_car_code  FROM xs_sell_instorehouse_del del," +
						  " xs_car_info car,xs_car_model model,xs_sell_instorehouse sell   " +
						  " WHERE car.xs_car_id = del.xs_car_id "+
						  "	AND car.xs_car_model_id = model.xs_model_id	" +
						  " and sell.instorehouse_id = del.instorehouse_id " +
						  " and car.xs_car_sell_state ="+sellState+" " +
						  "and sell.examine_state=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
								"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'" +
						" AND child.enterprise_id="+servicingVo.getEnterprise_id()+") and del.details_id NOT IN(" +
						"SELECT details_id FROM xs_sell_retreat WHERE instorehouse_type=" +
						" (SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.INSTORESTYLE.PARENTINSTORE+"' AND " +
						"child.dataKey='"+Contstants.INSTORESTYLE.BACK+"' AND child.enterprise_id="+servicingVo.getEnterprise_id()+")) ");
		
		sql.append( " and car.enterprise_id="+servicingVo.getEnterprise_id());
		if(servicingVo.getServicingNextdate()!=null  && !("".equals(servicingVo.getServicingNextdate()))){	
					sql.append( " and DATE(car.servicing_nextdate) >= '"+servicingVo.getServicingNextdate()+"'" );
			}
		if(servicingVo.getServicingNextdate2()!=null  && !("".equals(servicingVo.getServicingNextdate2()))){
					sql.append(" and DATE(car.servicing_nextdate) <= '"+servicingVo.getServicingNextdate2()+"'"); 
		}
		if((servicingVo.getServicingNextdate()==null||"".equals(servicingVo.getServicingNextdate()))
				&&(servicingVo.getServicingNextdate2()==null ||"".equals(servicingVo.getServicingNextdate2()))){
			sql.append( " and DATE(car.servicing_nextdate) BETWEEN " +
					"'"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,servicingVo.getEnterprise_id()).getCiValue()))+"'" +
					" AND '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if(servicingVo.getXsCarVinNumber()!=null  && !("".equals(servicingVo.getXsCarVinNumber()))){
			sql.append(" and car.xs_car_vin_number  like '%"+StringEscapeUtils.escapeSql(servicingVo.getXsCarVinNumber().trim())+"%'");
		}
		List<Object[]> lst=sellServicingDAO.createSQLQuery(sql.toString(), servicingVo.getPage(), servicingVo.getRows());
		List<SellServicingVo> rows =new ArrayList<SellServicingVo>();
		if(lst!=null && lst.size()>0){
			for(Object[] b:lst){
				SellServicingVo servicing=new SellServicingVo();
				if(b[0]!=null && !("".equals(b[0]))){servicing.setDetailsId((Integer)b[0]);};
				if(b[1]!=null && !("".equals(b[1]))){servicing.setXsCarId((Integer)b[1]);};
				if(b[2]!=null && !("".equals(b[2]))){servicing.setXsCarVinNumber(b[2].toString());};
				if(b[3]!=null && !("".equals(b[3]))){servicing.setXsCarModelId((Integer)b[3]);};
				if(b[4]!=null && !("".equals(b[4]))){servicing.setXsModelName(b[4].toString());};
				if(b[5]!=null && !("".equals(b[5]))){servicing.setServicingNextdate(new SimpleDateFormat("yyyy-MM-dd").format(b[5]));};
				if(b[6]!=null && !("".equals(b[6]))){servicing.setXsCarSellState((Integer)b[6]);};
				if(b[7]!=null && !("".equals(b[7]))){servicing.setCarSellStateName(b[7].toString());};
				if(b[8]!=null && !("".equals(b[8]))){servicing.setXsCarCode(b[8].toString());};
				
				rows.add(servicing);
			}
		}
		int total = sellServicingDAO.getSQLCount(sql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	//代办项目分页
	
	public Datagrid getPagerPro(SellServicingVo servicingVo) throws Exception{
		Datagrid dg = new Datagrid();
		StringBuffer sql=new StringBuffer("SELECT  servicing.servicing_id,servicing.details_id,servicing.servicing_project,(SELECT GROUP_CONCAT(c.dataValue)	"+
						"FROM xs_childdictionary c  WHERE c.child_id IN(servicing.servicing_project)GROUP BY servicing.details_id) AS servicing_project_name,servicing.servicing_person,"+
						"servicing.servicing_date,servicing.servicing_remark,info.xs_car_vin_number,info.xs_car_model_id,model.xs_model_name,servicing.servicing_nextdate"+
						"  FROM xs_sell_servicing servicing, xs_sell_instorehouse_del del,xs_car_info info,xs_car_model model	WHERE del.xs_car_id = info.xs_car_id	"+
						"AND info.xs_car_model_id = model.xs_model_id	AND del.details_id = servicing.details_id  AND servicing.details_id = "+servicingVo.getDetailsId());
		List<Object[]> lst=sellServicingDAO.createSQLQuery(sql.toString(), servicingVo.getPage(), servicingVo.getRows());
		List<SellServicingVo> rows =new ArrayList<SellServicingVo>();
		if(lst!=null && lst.size()>0){
			for(Object[] b:lst){
				SellServicingVo servicing=new SellServicingVo();
				if(b[0]!=null && !("".equals(b[0]))){servicing.setServicingId((Integer)b[0]);};
				if(b[1]!=null && !("".equals(b[1]))){servicing.setDetailsId((Integer)b[1]);};
				if(b[2]!=null && !("".equals(b[2]))){servicing.setServicingProject((Integer)b[2]);};
				if(b[3]!=null && !("".equals(b[3]))){servicing.setServicingProjectName(b[3].toString());};
				if(b[4]!=null && !("".equals(b[4]))){servicing.setServicingPerson((Integer)b[4]);};
				if(b[5]!=null && !("".equals(b[5]))){servicing.setServicingDate(new SimpleDateFormat("yyyy-MM-dd").format(b[5]));};
				if(b[6]!=null && !("".equals(b[6]))){servicing.setServicingRemark(b[6].toString());};
				if(b[7]!=null && !("".equals(b[7]))){servicing.setXsCarVinNumber(b[7].toString());};
				if(b[8]!=null && !("".equals(b[8]))){servicing.setXsCarModelId((Integer)b[8]);};
				if(b[9]!=null && !("".equals(b[9]))){servicing.setXsModelName(b[9].toString());};
				if(b[10]!=null && !("".equals(b[10]))){servicing.setServicingNextdate(new SimpleDateFormat("yyyy-MM-dd").format(b[10]));};
				rows.add(servicing);
			}
		}
		int total = sellServicingDAO.getSQLCount(sql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	public Object getPagerCar(SellServicingVo servicingVo) throws Exception {
		Datagrid dg = new Datagrid();
		//查询库存状态
		Integer instore=baseTagDAO.findChildCon(Contstants.INSTORETYPE.INSTORE, Contstants.BASE_SELL.INSTORETYPE,servicingVo.getEnterprise_id());
		StringBuffer sql=new StringBuffer("SELECT house.instorehouse_date,del.details_id ,info.xs_car_vin_number,info.xs_car_model_id,model.xs_model_name	"+
						"FROM  xs_sell_instorehouse_del  del ,xs_car_info  info,xs_car_model  model,xs_sell_instorehouse house WHERE del.xs_car_id=info.xs_car_id"+
						"	AND  model.xs_model_id=info.xs_car_model_id  AND house.instorehouse_id=del.instorehouse_id  AND  del.xs_sell_allocatel_type="+instore+"" +
								" and house.enterprise_id ="+servicingVo.getEnterprise_id());
		if(servicingVo.getXsCarVinNumber()!= null && !("".equals(servicingVo.getXsCarVinNumber()))){
			sql.append("	AND info.xs_car_vin_number like'%"+StringEscapeUtils.escapeSql(servicingVo.getXsCarVinNumber().trim())+"%'");
		}
		List<Object[]> lst=sellServicingDAO.createSQLQuery(sql.toString(), servicingVo.getPage(), servicingVo.getRows());
		List<SellServicingVo> rows =new ArrayList<SellServicingVo>();
		if(lst!=null && lst.size()>0){
			for(Object[] b:lst){
				SellServicingVo servicing=new SellServicingVo();
				if(b[0]!=null && !("".equals(b[0]))){servicing.setInstorehouseDate(new SimpleDateFormat("yyyy-MM-dd").format(b[0]));};
				if(b[1]!=null && !("".equals(b[1]))){servicing.setDetailsId((Integer)b[1]);};
				if(b[2]!=null && !("".equals(b[2]))){servicing.setXsCarVinNumber(b[2].toString());};
				if(b[3]!=null && !("".equals(b[3]))){servicing.setXsCarModelId((Integer)b[3]);};
				if(b[4]!=null && !("".equals(b[4]))){servicing.setXsCarModelName(b[4].toString());};
				rows.add(servicing);
			}
		}
		int total = sellServicingDAO.getSQLCount(sql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	@Log(systemName="销售系统", moduleName="车辆维护管理",opertype="新增")
	public void saveSellServicing(SellServicingVo servicingVo) throws Exception {
		//保存维护记录
		XsSellServicing sellServicing=new XsSellServicing();
		sellServicing.setServicingDate(new SimpleDateFormat("yyyy-MM-dd").parse(servicingVo.getServicingDate()));
		sellServicing.setServicingPerson(servicingVo.getServicingPerson());
		sellServicing.setServicingProject(servicingVo.getServicingProject());
		sellServicing.setServicingRemark(servicingVo.getServicingRemark());
		sellServicing.setServicingNextdate(new SimpleDateFormat("yyyy-MM-dd").parse(servicingVo.getServicingNextdate()));
		XsSellInstorehouseDel del=(XsSellInstorehouseDel) sellServicingDAO.get("from XsSellInstorehouseDel  del where del.detailsId="+servicingVo.getDetailsId());
		sellServicing.setDetailsId(del);
		sellServicingDAO.save(sellServicing);
		//更新车辆档案
		XsCarInfo  carInfo=(XsCarInfo) sellServicingDAO.get("from XsCarInfo  car  where car.carId="+servicingVo.getXsCarId());
		carInfo.setServicingNextdate(new SimpleDateFormat("yyyy-MM-dd").parse(servicingVo.getServicingNextdate()));
		sellServicingDAO.merge(carInfo);
		XsChilddictionary type=baseTagDAO.findById(sellServicing.getServicingProject());
		setContent("新增【车辆维护管理】编号为【"+sellServicing.getServicingId()+"】,VIN号【"+carInfo.getCarVinNumber()+"】," +
				"维护项目为【"+type.getDataValue()+"】,维护日期为【"+sellServicing.getServicingDate()+"】," +
						"预计下次维护日期为【"+sellServicing.getServicingNextdate()+"】," +
								"维护结果为【"+sellServicing.getServicingRemark()+"】的信息！");
		
	}

	public SellServicingDAO getSellServicingDAO() {
		return sellServicingDAO;
	}
	@Autowired
	public void setSellServicingDAO(SellServicingDAO sellServicingDAO) {
		this.sellServicingDAO = sellServicingDAO;
	}
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}
	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}
}
