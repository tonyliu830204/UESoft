package com.syuesoft.sell.sellwork.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.model.XsSellCustomSalesman;
import com.syuesoft.sell.model.XsSellCustomSalesmanDetail;
import com.syuesoft.sell.sellwork.dao.BackRegisterDao;
import com.syuesoft.sell.sellwork.vo.BackRegisterVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;

@Repository("backRegisterDao")
public class BackRegisterDaoImp extends BaseDaoImpl implements BackRegisterDao {

	/**
	 * 获取登记表汇总信息
	 */
	
	public List findRecord(final BackRegisterVo backRegisterVo,String sql) throws Exception {
		List list = createSQLQuery(sql,backRegisterVo.getPage(),backRegisterVo.getRows());
		return list;
	}

	/**
	 * 获取预定详细信息
	 */
	
	public Json findRecordById(BackRegisterVo backRegisterVo) throws Exception {
		
		List list = new ArrayList();
		String sql = "SELECT " +
				" A.xs_custom_salesman_detail_id," +
				" A.custom_name," +
				" A.telephone," +
				" A.stf_id," +
				" (SELECT c.stf_name FROM bas_stuff c WHERE a.stf_id = c.stf_id)  stf_name," +
				" A.talk_content," +
				" A.custom_level  custom_level," +
				" (SELECT xs_leva_name FROM xs_custom_leva d WHERE a.custom_level = d.xs_leva_id)  custom_level_name," +
				" A.message_channel  message_channel," +
				" (SELECT d.dataValue FROM xs_childdictionary d WHERE a.message_channel = d.child_id)  message_channel_name," +
				" A.register_state," +
				" A.remark," +
				" A.talk_way  talk_way," +
				" (SELECT d.dataValue FROM xs_childdictionary d WHERE a.talk_way = d.child_id)  talk_way_name," +
				" A.istestdrive," +
				" A.testdrive_model testdrive_model," +
				" (SELECT b.xs_model_name FROM xs_car_model b WHERE a.testdrive_model = b.xs_model_id) testdrive_model_name," +
				" a.car_model AS car_model," +
				" (SELECT b.xs_model_name FROM xs_car_model b WHERE a.car_model = b.xs_model_id)  xs_model_name," +
				" A.xs_custom_salesman_id," +
				"  a.in_date,a.out_date,a.man_num,a.woman_num," +
				"(SELECT d.dataValue FROM xs_childdictionary d WHERE a.register_state = d.child_id) as state," +
				" a.car_brand," +
				"(SELECT d.dataValue FROM xs_childdictionary d WHERE a.car_brand = d.child_id) as car_brand_name" +
				" FROM " +
				"Xs_Sell_Custom_Salesman_Detail a" +
				" WHERE a.xs_Custom_Salesman_Id='"+backRegisterVo.getXs_Custom_Salesman_Id()+"'";

		List rlist = createSQLQuery(sql);
		if(rlist!=null && rlist.size()>0){
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[])rlist.get(i);
				BackRegisterVo vo = new BackRegisterVo();
				if(obj[0]!=null){vo.setXs_Custom_Salesman_Detail_Id(obj[0].toString());}
				if(obj[1]!=null){vo.setCustom_Name(obj[1].toString());}
				if(obj[2]!=null){vo.setTelephone(obj[2].toString());}
				if(obj[3]!=null){vo.setStf_Id(obj[3].toString());}
				if(obj[4]!=null){vo.setStf_Name(obj[4].toString());}
				if(obj[5]!=null){vo.setTalk_Content(obj[5].toString());}
				if(obj[6]!=null){vo.setCustom_Level(obj[6].toString());}
				if(obj[7]!=null){vo.setCustom_Level_Name(obj[7].toString());}
				if(obj[8]!=null){vo.setMessage_Channel(obj[8].toString());}
				if(obj[9]!=null){vo.setMessage_Channel_Name(obj[9].toString());}
				if(obj[10]!=null){vo.setRegister_State(obj[10].toString());}
				if(obj[11]!=null){vo.setRemark(obj[11].toString());}
				if(obj[12]!=null){vo.setTalk_Way(obj[12].toString());}
				if(obj[13]!=null){vo.setTalk_Way_Name(obj[13].toString());}
				if(obj[14]!=null){vo.setIstestdrive(obj[14].toString());}
				if(obj[15]!=null){vo.setTestdrive_Model(obj[15].toString());}
				if(obj[16]!=null){vo.setTestdrive_Model_Name(obj[16].toString());}
				if(obj[17]!=null){vo.setCar_Model(obj[17].toString());}
				if(obj[18]!=null){vo.setCar_Model_Name(obj[18].toString());}
				if(obj[19]!=null){vo.setXs_Custom_Salesman_Id(obj[19].toString());}
				if(obj[20]!=null){vo.setRegister_Date(obj[20].toString());}
				if(obj[21]!=null){vo.setExit_Date(obj[21].toString());}
				if(obj[22]!=null){vo.setMan_Num(obj[22].toString());}
				if(obj[23]!=null){vo.setWoman_Num(obj[23].toString());}
				if(obj[24]!=null){vo.setRegister(obj[24].toString());}
				if(obj[25]!=null){vo.setCar_Brand(obj[25].toString());}
				if(obj[26]!=null){vo.setCar_Brand_Name(obj[26].toString());}
				list.add(vo);
			}
		}
		Json json = new Json();
		json.setRows(list);
		json.setTotal(getCountBySQL(sql));
		return json;
	}

	/**
	 * 删除明细信息
	 */
	
	public void deleteDetailInfo(BackRegisterVo backRegisterVo)
			throws Exception {
		String hql ="delete from XsSellCustomSalesmanDetail where xsCustomSalesmanDetailId='"+backRegisterVo.getXs_Custom_Salesman_Detail_Id()+"'";
		deleteByHql(hql);
	}

	/**
	 * 删除汇总信息
	 */
	
	public void deleteRecord(BackRegisterVo backRegisterVo) throws Exception {
		String hql ="delete from XsSellCustomSalesman where xsCustomSalesmanId='"+backRegisterVo.getXs_Custom_Salesman_Id()+"'";
		String hql2 ="delete from XsSellCustomSalesmanDetail where xsSellCustomSalesman.xsCustomSalesmanId='"+backRegisterVo.getXs_Custom_Salesman_Id()+"'";
		List list = find("select xsCustomSalesmanDetailId from XsSellCustomSalesmanDetail  where xsSellCustomSalesman.xsCustomSalesmanId='"+backRegisterVo.getXs_Custom_Salesman_Id()+"'");
		if(list!=null){
			String hql3 ="DELETE FROM xs_sell_receiverelation WHERE xs_custom_salesman_detail_id ='"+list.get(0)+"'";
			deleteBySql(hql3);
		}
		deleteByHql(hql2);
		deleteByHql(hql);
	}

	
	public List getBasStuff() throws Exception {
		return this.getHibernateTemplate().find("from BasStuff");
	}

	/**
	 * 來店客流登记浏览
	 */
	
	public List findRecordLook(String sql,int page,int rows) throws Exception {
		List list = createSQLQuery(sql,page,rows);
		return list;
	
	}
	

}
