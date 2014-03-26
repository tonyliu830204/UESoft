package com.syuesoft.sell.noteManage.daoimpl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.noteManage.dao.SellSmsDao;
import com.syuesoft.sell.noteManage.vo.SellSmsVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
@Repository("sellSmsDao")
public class SellSmsDaoImpl extends BaseDaoImpl<BaseBean> implements SellSmsDao{

	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	@SuppressWarnings("unchecked")
	
	public void smsSend(final SellSmsVo smsSendVo) throws Exception {
		this.getHibernateTemplate().execute(new HibernateCallback() {

			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sms_date = null;
				Integer sms_way= null;
				
		
				if(!smsSendVo.getSms_date().equals("")){
					sms_date = "'"+smsSendVo.getSms_date()+"'";
					sms_way=0;//即时发送
				}else {
					sms_date = "'"+smsSendVo.getD_date()+"'";
					sms_way=1;//定时发送
					
				}
				//获取规定格式的id
				String infoId = "";
				try {
					infoId = CreateID.createId("BasVipSendinfomation",Contstants.SELL_BILLSDEPLOY.NOTE);
				} catch (Exception e) {
					e.printStackTrace();
				}
				 BasUsers user = (BasUsers) ServletActionContext.getRequest().getSession().getAttribute(Contstants.CUSTOMER);
				   Integer  sft_id=Integer.parseInt(String.valueOf(user.getBasStuff().getStfId()));
				//将记录存入已发送信息记录表里面
				session.createSQLQuery("INSERT INTO xs_sell_sms (sms_id,	sft_id,	sms_date,sms,sms_way,sms_type,sms_state,enterprise_id) " +
						"values ('"+infoId+"', '"+sft_id+"','"+smsSendVo.getSms()+"', '"+sms_way+", " +
								"'"+smsSendVo.getSms_type()+"','"+1+"','"+smsSendVo.getEnterprise_id()+"') ").executeUpdate();//1=已发送
				
				//将信息添加到接收表
				session.createSQLQuery("INSERT INTO xs_sell_receivesms (sms_id,custom_id,receive_date,phonecode) " +
						"values ('"+infoId+"','"+smsSendVo.getCustomId()+"','"+sms_date+"','"+smsSendVo.getPhonecode()+"') ").executeUpdate();
				
				return null;
			}
		
		});
		
		
	}

	
	public Datagrid getSmsInfo(SellSmsVo smsSendVo) throws Exception {
		Datagrid dg = new Datagrid();
		List<SellSmsVo> list = new ArrayList<SellSmsVo>();
		StringBuffer sql = new StringBuffer("SELECT a.sms_id,a.sms_date,a.sms,B.custom_id," +
				"b.custom_name,a.sms_state,e.xs_car_licensePlate,e.xs_car_vin_number,a.sms_type," +
				"a.sft_id,f.STF_NAME,b.phonecode ,b.receive_state " +
				"FROM xs_sell_sms A" +
				" JOIN xs_sell_receivesms B ON b.sms_id=a.sms_id" +
				" LEFT JOIN xs_custom_info C ON c.custom_id=b.custom_id" +
				" LEFT JOIN xs_car_sell_info D ON d.custom_id=c.custom_id" +
				" LEFT JOIN xs_car_info E ON e.xs_car_id=d.xs_car_id" +
				" LEFT JOIN bas_stuff F ON f.STF_ID=a.sft_id");
		if (smsSendVo.getEnterprise_id() != null
				&& !"".equals(smsSendVo.getEnterprise_id())) {
			sql.append(" and a.enterprise_id= '"
					+ smsSendVo.getEnterprise_id() + "'");
		}
		if (smsSendVo.getCustom_Name() != null
				&& !"".equals(smsSendVo.getCustom_Name())) {
			sql.append(" and b.custom_name like '%"
					+ StringEscapeUtils.escapeSql(smsSendVo.getCustom_Name().trim()) + "%'");
		}
	
		if (smsSendVo.getSms_date() != null
				&& !"".equals(smsSendVo.getSms_date())) {
			sql.append(" and a.sms_date >= '" + smsSendVo.getSms_date() + "'");
			} 
		if (smsSendVo.getSms_date2() != null
				&& !"".equals(smsSendVo.getSms_date2())) {
			sql.append(" and a.sms_date <= '" + smsSendVo.getSms_date2() + "'");
			} 
		if((smsSendVo.getSms_date() == null
					|| "".equals(smsSendVo.getSms_date()))&&(smsSendVo.getSms_date2() == null
							|| "".equals(smsSendVo.getSms_date2()))){
			sql.append( " and a.sms_date" +
					" between '"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, Integer.parseInt(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,smsSendVo.getEnterprise_id()).getCiValue()))+"'" +
					"and '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"' ");
		}
		if (smsSendVo.getSft_id()!= null
				&& !"".equals(smsSendVo.getSft_id())) {
			sql.append(" and a.sft_id= '"
					+ smsSendVo.getSft_id() + "'");
		}
		if (smsSendVo.getTelephone()!= null
				&& !"".equals(smsSendVo.getTelephone())) {
			sql.append(" and b.phonecode= '"
					+ smsSendVo.getTelephone() + "'");
		}
		List<Object[]> resultList = createSQLQuery(sql.toString(), smsSendVo
				.getPage(), smsSendVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellSmsVo no = new SellSmsVo();
				obj = resultList.get(i);
				no.setSms_id(obj[0] != null ? obj[0].toString() : null);
				no.setSms_date(obj[1] != null ? obj[1].toString() : null);
				no.setSms(obj[2] != null ? obj[2].toString() : null);
				no.setCustomId(obj[3] != null ? Integer.parseInt(obj[3].toString()) : null);
				no.setCustom_Name(obj[4] != null ? obj[4].toString() : null);
				no.setSms_state(obj[5] != null ?Integer.parseInt( obj[5].toString()) : null);
				no.setCarLicensePlate(obj[6] != null ? obj[6].toString() : null);
				no.setCarVinNumber(obj[7] != null ? obj[7].toString() : null);
				no.setSms_type(obj[8] != null ?Integer.parseInt( obj[8].toString()) : null);
				no.setSft_id(obj[9] != null ?Integer.parseInt( obj[9].toString()) : null);
				no.setSft_name(obj[10] != null ? obj[10].toString() : null);
				no.setTelephone(obj[11] != null ? obj[11].toString() : null);
				no.setReceive_state(obj[12] != null ?Integer.parseInt( obj[12].toString()) : null);
				
				list.add(no);
	}
		}
			int total = this.getSQLCount(sql.toString(), null);
			dg.setRows(list);
			dg.setTotal(total);
			return dg;
	}

}
