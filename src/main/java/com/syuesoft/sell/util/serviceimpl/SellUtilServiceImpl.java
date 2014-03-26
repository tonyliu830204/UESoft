package com.syuesoft.sell.util.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.BasStuff;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsCarModel;
import com.syuesoft.sell.model.XsCustomInfo;
import com.syuesoft.sell.model.XsDistributorInfo;
import com.syuesoft.sell.model.XsProvidebank;
import com.syuesoft.sell.sellwork.vo.CarSellManageVo;
import com.syuesoft.sell.util.SellUtilVo;
import com.syuesoft.sell.util.dao.SellUtilDao;
import com.syuesoft.sell.util.service.SellUtilService;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Msg;

@Service("sellUtilService")
public class SellUtilServiceImpl implements SellUtilService {
	
	@Resource
	private SellUtilDao sellUtilDao;
	@Autowired
	private BasCompanyInformationSetService basCompanyInformationSetService;  
	
	public List findCustom(String q) throws Exception {
		List list = new ArrayList();
		List<XsCustomInfo> rlist = sellUtilDao.findCustom(q);
		if(rlist!=null && rlist.size()>0){
		for (XsCustomInfo xsCustomInfo : rlist) {
			ComboxVo vo = new ComboxVo();
			vo.setId(xsCustomInfo.getCustomId().toString());
			vo.setName(xsCustomInfo.getXsCustomName());
			list.add(vo);
		}
	}
		return list;
	}
	
	public List findStfName(String q) throws Exception {
		List list = new ArrayList();
		List<BasStuff> rlist = sellUtilDao.findStfName(q);
		if(rlist!=null && rlist.size()>0){
		for (BasStuff basStuff : rlist) {
			ComboxVo vo = new ComboxVo();
			vo.setId(basStuff.getStfId().toString());
			vo.setName(basStuff.getStfName());
			list.add(vo);
		}
		}
		return list;
	}
	
	public List findCarModel() throws Exception {

		List list = new ArrayList();
		List<XsCarModel> rlist = sellUtilDao.findCarModel();
		if(rlist!=null && rlist.size()>0){
			for (XsCarModel xsCarModel : rlist) {
				ComboxVo vo = new ComboxVo();
				vo.setId(xsCarModel.getModelId().toString());
				vo.setName(xsCarModel.getModelName());
				list.add(vo);
			}
		}
		return list;
	
	}
	/**
	 * 获取银行信息
	 */
	
	public List findBank() throws Exception {

		List list = new ArrayList();
		List<XsProvidebank> rlist = sellUtilDao.findBank();
		if(rlist!=null && rlist.size()>0){
		for (XsProvidebank xsProvidebank : rlist) {
			ComboxVo vo = new ComboxVo();
			vo.setId(xsProvidebank.getProvidebankId().toString());
			vo.setName(xsProvidebank.getProvidebankName());
			list.add(vo);
		}
		}
		return list;
	
	}
	
	public List findBussness() throws Exception {

		List list = new ArrayList();
		List<XsDistributorInfo> rlist = sellUtilDao.findBussness();
		if(rlist!=null && rlist.size()>0){
		for (XsDistributorInfo xsDistributorInfo : rlist) {
			ComboxVo vo = new ComboxVo();
			vo.setId(xsDistributorInfo.getDistributorId().toString());
			vo.setName(xsDistributorInfo.getDistributorName());
			list.add(vo);
		}
		}
		return list;
	
	}
	
	public List findCarId() throws Exception {

		List list = new ArrayList();
		List<XsCarInfo> rlist = sellUtilDao.findCarId();
		if(rlist!=null && rlist.size()>0){
		for (XsCarInfo xsCarInfo : rlist) {
			ComboxVo vo = new ComboxVo();
			vo.setId(xsCarInfo.getCarId().toString());
			vo.setName(xsCarInfo.getCarId().toString());
			list.add(vo);
		}
		}
		return list;
	
	}
	
	
	public List findUsers() throws Exception {

		List list = new ArrayList();
		List rlist = sellUtilDao.findUsers();
		Object[] obj = null;
		if(rlist!=null && !rlist.equals("")){
		for (int i = 0; i < rlist.size(); i++) {
			obj = (Object[])rlist.get(i);
			ComboxVo vo = new ComboxVo();
			if(obj[0]!=null && !obj[1].equals("")){
			vo.setId(obj[0].toString());
			vo.setName(obj[1].toString());
			}
			list.add(vo);
		}
		}
		return list;
	
	}
	/**
	 * 获取经办人
	 * */
	
	public List findUsers(String q,String enterpriseId) throws Exception {
		List list = new ArrayList();
		List rlist = sellUtilDao.findUsers(q,enterpriseId);
		Object[] obj = null;
		if(rlist!=null && !rlist.equals("")){
		for (int i = 0; i < rlist.size(); i++) {
			obj = (Object[])rlist.get(i);
			ComboxVo vo = new ComboxVo();
			if(obj[0]!=null && !obj[1].equals("")){
			vo.setId(obj[0].toString());
			vo.setName(obj[1].toString());
			}
			list.add(vo);
		}
		}
		return list;
	}
	/**
	 * 获取预定时的客户信息 车辆信息
	 */
	
	public List findCustominfo() throws Exception {
		String sql ="SELECT  " +
				" a.xs_custom_name, a.xs_custom_telephone," +
				" e.xs_model_id, e.xs_model_name," +
				" c.car_brand," +
				" (SELECT dataValue FROM xs_childdictionary WHERE child_id=c.car_brand) AS car_brand_name," +
				" e.xs_model_salesPrice," +
				" d.xs_leva_id" +
				" FROM  xs_custom_info a, xs_sell_receiveRelation b, xs_sell_custom_salesman_detail c , xs_custom_leva d , xs_car_model e " +
				" WHERE c.car_model = e.xs_model_id" +
				" AND a.custom_id = b.custom_id" +
				" AND b.xs_custom_salesman_detail_id = c.xs_custom_salesman_detail_id" +
				" AND c.custom_level = d.xs_leva_id ";
		List info = sellUtilDao.createSQLQuery(sql);
		Object[] obj = null;
		List list = new ArrayList();
		if(info!=null && info.size()>0){
			for (int i = 0; i < info.size(); i++) {
				obj = (Object[])info.get(i);
				CarSellManageVo vo = new CarSellManageVo();
				if(obj[0]!=null){vo.setXs_Custom_Name(obj[0].toString());}
				if(obj[1]!=null){vo.setXs_Custom_Telephone(obj[1].toString());}
				if(obj[2]!=null){vo.setXs_Model_Id(obj[2].toString());}
				if(obj[3]!=null){vo.setXs_Model_Name(obj[3].toString());}
				if(obj[4]!=null){vo.setCar_Brand_Id(obj[4].toString());}
				if(obj[5]!=null){vo.setXs_Car_Brand(obj[5].toString());}
				if(obj[6]!=null){vo.setXs_Model_CostPrice(obj[6].toString());}
				if(obj[7]!=null){vo.setXs_Custom_Hide_Level(obj[7].toString());}
				list.add(vo);
			}
		}
		return list;
	}
	/**
	 * 通过父key获取数据字典子项编号及key
	 */
	
	public List getKey(String K) throws Exception {

		List list = new ArrayList();
		String sql = "SELECT A.child_id, A.dataKey FROM xs_childdictionary A , xs_parentdictionary B WHERE A.parent_id = B.parent_id  AND B.dataKey='"+K+"'";

		List rlist = sellUtilDao.createSQLQuery(sql);
		Object[] obj = null;
		if(rlist!=null && !rlist.equals("")){
		for (int i = 0; i < rlist.size(); i++) {
			obj = (Object[])rlist.get(i);
			ComboBoxJson vo = new ComboBoxJson();
			if(obj[0]!=null && !obj[1].equals("")){
			vo.setId(obj[0].toString());
			vo.setDataKey(obj[1].toString());
			}
			list.add(vo);
		}
		}
		return list;
	
	}
	/**
	 * 获取当前系统时间和默认资料显示时间
	 * */
	
	public Msg initDateAndSearch(int enterpriseId) throws Exception {
		BasCompanyInformationSet bcis=
			basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 
					Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriseId);
		SellUtilVo su=new SellUtilVo();
		int count=6;
		if(bcis!=null){
			if(bcis.getCiValue()!=null&&bcis.getCiValue().length()>0){
				count=Integer.parseInt(bcis.getCiValue());	
			}
		}
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		su.setKey(sdf.format(date).toString());
		date.setDate(date.getDate()-count);
		su.setQ(sdf.format(date).toLowerCase());
		Msg msg=new Msg();
		msg.setObj(su);
		msg.setSuccess(true);
		return msg;
	}
	
}
