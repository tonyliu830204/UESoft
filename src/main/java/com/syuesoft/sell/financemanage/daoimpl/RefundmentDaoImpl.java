package com.syuesoft.sell.financemanage.daoimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.sell.financemanage.dao.RefundmentDao;
import com.syuesoft.sell.financemanage.vo.RefundmentVo;
import com.syuesoft.sell.model.XsBackCarLog;
import com.syuesoft.util.Json;

@Repository("refundmentDao")
public class RefundmentDaoImpl extends BaseDaoImpl<XsBackCarLog> implements RefundmentDao {
	
	
	public void flush() throws Exception {
		// TODO Auto-generated method stub
		getSession().flush();
	}

	/**
	 * 查找未退金额
	 * */
	
	public String findNoBackMoney(RefundmentVo refundmentVo) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("SELECT aa.temp1-temp2");
		sb.append(" FROM(SELECT xbl.backCar_sunmoney AS temp1,SUM(xbl.backCar_money) AS temp2");
		sb.append(" FROM xs_backcar_log xbl WHERE xbl.backCar_Document='"+refundmentVo.getBackCar_Document()+"' GROUP BY xbl.backCar_Document) aa");
		List list=createSQLQuery(sb.toString());
		if(list!=null&&list.size()>0){
			if(refundmentVo.getEditTag()!=null&&refundmentVo.getEditTag()==true)
				if(refundmentVo.getBackCar_Money()!=null&&refundmentVo.getBackCar_Money().length()>0)
					return new BigDecimal(((Double)list.get(0))+Double.parseDouble(refundmentVo.getBackCar_Money())).toString();
			return new BigDecimal(((Double)list.get(0))).toString();
		}
		return "0";
	}

	/**
	 * 保存退款记录
	 */
	
	public void saveRefundmentAmount(RefundmentVo refundmentVo)
			throws Exception {
		
		/*
		 *  private Integer backCarPerson;					//经办人
	 private Integer backMoneyPerson;				//退款人
	 private Integer invoice;						//发票类型
	 private Integer examine;						//审核情况
     private XsSellExitCar xsSellExitCar;	
     
     private String invoiceNum;						//票据编号
     private String backMoneyDate;					//退款日期
     private String backCarDate;					//经办日期
		XsBackCarLog xsBackCarLog = (XsBackCarLog)get("from XsBackCarLog where backCarId="+refundmentVo.getBackCar_Id()+"");
		xsBackCarLog.setBackCarMoney(refundmentVo.getBackCar_Money()!=null && !refundmentVo.getBackCar_Money().equals("") ? Double.parseDouble(refundmentVo.getBackCar_Money()) : 0.00 );
		xsBackCarLog.setBackCarDate(new java.sql.Date(new java.util.Date().getTime())+"");
		//当前用户
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        BasUsers user = (BasUsers) session.getAttribute(Contstants.CUSTOMER);
		xsBackCarLog.setBackCarPerson(Integer.parseInt(user.getBasStuff().getStfId().toString()));
		xsBackCarLog.setBackMoneyDate(refundmentVo.getBackMoney_Date()!=null ? refundmentVo.getBackMoney_Date() : "");
		xsBackCarLog.setBackMoneyPerson(refundmentVo.getBackMoney_Person()!=null ? Integer.parseInt(refundmentVo.getBackMoney_Person()) : 0);
		merge(xsBackCarLog);
		
		//获取预收款
		String sql2 ="SELECT a.account_CUMULATIVE FROM xs_sell_collections a WHERE a.account_id=(" +
				"SELECT b.reserve_id FROM xs_sell_car_reserve b WHERE b.xs_car_id =(" +
				"SELECT c.xs_car_id FROM xs_car_sell_info c WHERE c.xs_car_sel_id=(" +
				"SELECT d.xs_car_sel_id FROM xs_sell_exitCar d WHERE d.exitCar_id="+refundmentVo.getExitCar_Id()+"))) ";
		List rlist2 = createSQLQuery(sql2);
		//获取预收款编号
		String sql3 ="SELECT a.collections_id FROM xs_sell_collections a WHERE a.account_id=(" +
				"SELECT b.reserve_id FROM xs_sell_car_reserve b WHERE b.xs_car_id =(" +
				"SELECT c.xs_car_id FROM xs_car_sell_info c WHERE c.xs_car_sel_id=(" +
				"SELECT d.xs_car_sel_id FROM xs_sell_exitCar d WHERE d.exitCar_id="+refundmentVo.getExitCar_Id()+"))) ";
		List rlist3 = createSQLQuery(sql3);
		
		//如果是预收款退款，则将预收款置0 并将预收款使用记录改为退预约金
		if(rlist2!=null && rlist2.size()>0){
			String sql ="UPDATE  xs_sell_collections a SET a.account_CUMULATIVE=0.00 WHERE a.account_id=(" +
			"SELECT b.reserve_id FROM xs_sell_car_reserve b WHERE b.xs_car_id =(" +
			"SELECT c.xs_car_id FROM xs_car_sell_info c WHERE c.xs_car_sel_id=(" +
			"SELECT d.xs_car_sel_id FROM xs_sell_exitCar d WHERE d.exitCar_id="+refundmentVo.getExitCar_Id()+"))) ";
			createSQLQueryOutFind(sql);
			
			//添加预收款使用记录
			XsSellCollectionsDetail xsSellCollectionsDetail = new XsSellCollectionsDetail();
			xsSellCollectionsDetail.setDate(refundmentVo.getBackMoney_Date());
			xsSellCollectionsDetail.setExamine(185);
			xsSellCollectionsDetail.setInvoice(refundmentVo.getInvoice()!=null && !refundmentVo.getInvoice().equals("")?Integer.parseInt(refundmentVo.getInvoice()) : null);
			xsSellCollectionsDetail.setInvoiceNum(refundmentVo.getInvoice_Num());
			xsSellCollectionsDetail.setReceivedMoney(refundmentVo.getBackCar_Money()!=null && !refundmentVo.getBackCar_Money().equals("")?Double.parseDouble(refundmentVo.getBackCar_Money()) : 0.00);
			//xsSellCollectionsDetail.setReceivedWay(gatheringManageVo.getReceived_Way()!=null && !gatheringManageVo.getReceived_Way().equals("")?Integer.parseInt(gatheringManageVo.getReceived_Way()) : null);
			xsSellCollectionsDetail.setStfId(refundmentVo.getBackMoney_Person()!=null && !refundmentVo.getBackMoney_Person().equals("")?Integer.parseInt(refundmentVo.getBackMoney_Person()) : null);
			xsSellCollectionsDetail.setDetailType(339);
			//cllid
			XsSellCollections  xsSellCollections = new XsSellCollections();
			xsSellCollections.setCollectionsId(rlist3!=null && !rlist3.get(0).equals("") ? Integer.parseInt(rlist3.get(0).toString()): 0);
			xsSellCollectionsDetail.setXsSellCollections(xsSellCollections);
			save(xsSellCollectionsDetail);
		}
		*/
	}

}
