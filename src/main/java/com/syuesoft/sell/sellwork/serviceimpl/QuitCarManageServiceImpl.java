package com.syuesoft.sell.sellwork.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.dao.CarInfoDAO;
import com.syuesoft.sell.control.dao.XsSellFlowControlDao;
import com.syuesoft.sell.model.XsBackCarLog;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsSellCarReserve;
import com.syuesoft.sell.model.XsSellExitCar;
import com.syuesoft.sell.model.XsSellFlowControl;
import com.syuesoft.sell.sellwork.dao.CarSellManageDao;
import com.syuesoft.sell.sellwork.dao.QuitCarManageDao;
import com.syuesoft.sell.sellwork.service.QuitCarManageService;
import com.syuesoft.sell.sellwork.vo.QuitCarManageVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.util.SystemUser;

@Service("quitCarManageService")
public class QuitCarManageServiceImpl extends BaseLogServiceImpl implements
		QuitCarManageService {

	@Autowired
	private QuitCarManageDao quitCarManageDao;
	@Autowired
	private BaseTagDAO baseTagDAO;
	@Autowired
	private XsSellFlowControlDao xsSellFlowControlDao;
	@Autowired
	private CarSellManageDao carSellManageDao;
	@Autowired
	private CarInfoDAO carInfoDAO;
	/**
	 * 添加退车信息
	 */
	
	public void addQuitInfor(QuitCarManageVo quitCarManageVo) throws Exception{
		XsSellExitCar xc = new XsSellExitCar();
		//企业编号
		if(quitCarManageVo.getEnterpriseId()!=null && !quitCarManageVo.getEnterpriseId().equals("")){
			xc.setEnterpriseId(quitCarManageVo.getEnterpriseId());
		}
		if(quitCarManageVo.getExitCar_Type()!=null&&quitCarManageVo.getExitCar_Type().length()>0)
			xc.setExitCarType(Integer.parseInt(quitCarManageVo.getExitCar_Type()));
		xc.setExamine(baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.WEISHENHE,quitCarManageVo.getEnterpriseId()));
		xc.setNotice(0);//默认为通知退款
		xc.setExitCarCode(CreateID.createId("XsSellExitCar", Contstants.SELL_BILLSDEPLOY.BACKTOCAR));
		xc.setExitCarDate(new java.sql.Date(new java.util.Date().getTime())+"");
		
        xc.setExitCarPerson(Integer.parseInt(SystemUser.getUser().getBasStuff().getStfId()+""));
		xc.setXsCarPdsDate(quitCarManageVo.getXs_Car_Pds_Date());
		if(quitCarManageVo.getXs_Car_Pds_Person()!=null&&quitCarManageVo.getXs_Car_Pds_Person().length()>0)
			xc.setXsCarPdsPerson(Integer.parseInt(quitCarManageVo.getXs_Car_Pds_Person()));
		xc.setXsCarPdsResult(quitCarManageVo.getXs_Car_Pds_Result());
		XsCarSellInfo xsCarSellInfo = new XsCarSellInfo();
		if(quitCarManageVo.getXs_Car_Sel_Id()!=null&&quitCarManageVo.getXs_Car_Sel_Id().length()>0)
			xsCarSellInfo.setXsCarSelId(Integer.parseInt(quitCarManageVo.getXs_Car_Sel_Id()));
		xc.setXsCarSellInfo(xsCarSellInfo);
		xc.setExitCarRemark(quitCarManageVo.getExitCar_Remark());
		quitCarManageDao.addQuitInfor(xc);
		XsCarSellInfo xcs=(XsCarSellInfo)carSellManageDao.get(" from XsCarSellInfo xcs where xcs.xsCarSelId="+quitCarManageVo.getXs_Car_Sel_Id());
		XsCarInfo car=(XsCarInfo)carInfoDAO.get("from XsCarInfo where carId="+xcs.getXsCarId());
		car.setSellStateChild(baseTagDAO.getChild(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.FORSALE,quitCarManageVo.getEnterpriseId()));
		carInfoDAO.saveOrUpdate(car);		
		List slist = quitCarManageDao.createSQLQuery("SELECT a.out_id FROM xs_car_sell_info a WHERE a.xs_car_sel_id="+quitCarManageVo.getXs_Car_Sel_Id());
		//已出库时
		if(slist!=null && slist.size()>0){
			//修改车辆档案 销售状态为 在库代销（163） xs_car_sell_state
			int xscarsellstate = baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.FORSALE,quitCarManageVo.getEnterpriseId());
			quitCarManageDao.createSQLQueryOutFind("UPDATE xs_car_info SET xs_car_sell_state="+xscarsellstate+" WHERE xs_car_id=(SELECT a.xs_car_id FROM xs_car_sell_info a WHERE a.xs_car_sel_id="+quitCarManageVo.getXs_Car_Sel_Id()+")");
			//将入库表里的状态改为入库状态 241  INSTORE
			int xssellallocateltype = baseTagDAO.getChildId(Contstants.BASE_SELL.INSTORETYPE, Contstants.INSTORETYPE.INSTORE,quitCarManageVo.getEnterpriseId());
			quitCarManageDao.createSQLQueryOutFind("UPDATE xs_sell_instorehouse_del SET xs_sell_allocatel_type="+xssellallocateltype+" WHERE xs_car_id=(SELECT a.xs_car_id FROM xs_car_sell_info a WHERE a.xs_car_sel_id="+quitCarManageVo.getXs_Car_Sel_Id()+")");
			//修改客户档案 成交状态为 潜在客户 SS
			int xsCustomState = baseTagDAO.getChildId(Contstants.BASE_SELL.BASE_DEALSTATE, Contstants.BASE_SELL.SS,quitCarManageVo.getEnterpriseId());
			quitCarManageDao.createSQLQueryOutFind("UPDATE xs_custom_info SET xs_custom_deal="+xsCustomState+" WHERE custom_id=(SELECT a.custom_id FROM xs_car_sell_info a WHERE a.xs_car_sel_id="+quitCarManageVo.getXs_Car_Sel_Id()+")");
		}
		XsSellFlowControl xsfc=new XsSellFlowControl();
		xsfc.setXsfcontrolDocument(xc.getExitCarCode());
		xsfc.setXsfcontrolCode(Contstants.SELLFLOWCONTROLCODE.CONTROLCODE10);
		xsfc.setXsfcontrolCarId(xcs.getXsCarId());
		xsSellFlowControlDao.save(xsfc);
	}

	/**
	 * 销售退车管理
	 */
	
	public Json getQuitInfor(QuitCarManageVo quitCarManageVo)throws Exception {
		
		return quitCarManageDao.getQuitInfor(quitCarManageVo);
	}

	/**
	 * 审核
	 */
	
	public Message doAuditQuitInfor(QuitCarManageVo quitCarManageVo)
			throws Exception {
		Message msg = new Message();
		try {
			XsSellExitCar xsSellExitCar = (XsSellExitCar)quitCarManageDao.get("from XsSellExitCar where exitCarId="+quitCarManageVo.getExitCar_Id()+"");
			if(xsSellExitCar.getNotice()!=null&&xsSellExitCar.getNotice().equals(1)){
				msg.setMsg("该退车单已通知退款不可反审核！");
				msg.setSuccess(false);
			}else{
				int weishenhe = baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT, Contstants.ADUIT.WEISHENHE,quitCarManageVo.getEnterpriseId());
				int yishenhe = baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT, Contstants.ADUIT.YISHENHE,quitCarManageVo.getEnterpriseId());
				if(Integer.parseInt(quitCarManageVo.getExamine())==weishenhe){
					xsSellExitCar.setExamine(yishenhe);
					xsSellExitCar.setExitCarCheckPerson(Integer.parseInt(quitCarManageVo.getExitCar_Check_Person()));
					msg.setMsg("审核成功！");
				}
				xsSellFlowControlDao.executeSql("update xs_car_info set xs_car_sell_state="+
						baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.FORSALE,quitCarManageVo.getEnterpriseId()));
//				else{
//					xsSellExitCar.setExamine(weishenhe);
//					xsSellExitCar.setExitCarCheckPerson(null);
//					msg.setMsg("取消审核成功！");
//				}
				quitCarManageDao.merge(xsSellExitCar);
				msg.setSuccess(true);
			}
		} catch (Exception e) {
			msg.setMsg("审核失败！");
		}
		return msg;
		
	}

	/**
	 * 通知退款
	 */
	
	public Boolean doNoticeRefundment(QuitCarManageVo quitCarManageVo) throws Exception {
		if(quitCarManageVo.getReciveOrSellTag()==null)
			return false;
		if(!(quitCarManageVo.getBackCar_Sunmoney()!=null && quitCarManageVo.getBackCar_Sunmoney().length()>0))
			return false;
		if(!(quitCarManageVo.getBackCar_Person()!=null&&quitCarManageVo.getBackCar_Person().length()>0))
			return false;
		if(!(quitCarManageVo.getExitCar_Id()!=null&&quitCarManageVo.getExitCar_Id().length()>0))
			return false;
		XsSellExitCar xsSellExitCar = (XsSellExitCar)quitCarManageDao.get("from XsSellExitCar where exitCarId="+quitCarManageVo.getExitCar_Id()+"");
		xsSellExitCar.setNotice(1);
		XsBackCarLog xsBackCarLog = new XsBackCarLog();
		if(quitCarManageVo.getReciveOrSellTag()==true){			
			if(!(quitCarManageVo.getReserveId()!=null&&quitCarManageVo.getReserveId().length()>0))
				return false;
			xsBackCarLog.setBackCarCode(CreateID.createId("xsBackCarLog_before", Contstants.SELL_BILLSDEPLOY.CARRESERVEREFUNDMENT));
			xsBackCarLog.setBackCarDocument(xsSellExitCar.getExitCarCode());
		}
		else{
			xsBackCarLog.setBackCarCode(CreateID.createId("xsBackCarLog_back", Contstants.SELL_BILLSDEPLOY.BACKTOCARREFUNDMENT));
			xsBackCarLog.setBackCarDocument(xsSellExitCar.getExitCarCode());
		}		
		quitCarManageDao.merge(xsSellExitCar);
		xsBackCarLog.setBackCarDate(new java.sql.Date(new java.util.Date().getTime())+"");
		//退款金额
		xsBackCarLog.setBackCarMoney(0d);
		//经办人
		xsBackCarLog.setBackCarPerson(Integer.parseInt(quitCarManageVo.getBackCar_Person()));
		//应退金额
		xsBackCarLog.setBackCarSunmoney(Double.parseDouble(quitCarManageVo.getBackCar_Sunmoney()));
		if(quitCarManageVo.getInvoice()!=null&&quitCarManageVo.getInvoice().length()>0){
			xsBackCarLog.setInvoice(Integer.parseInt(quitCarManageVo.getInvoice()));
			xsBackCarLog.setInvoiceNum(quitCarManageVo.getInvoice_Num());			
		}
		xsBackCarLog.setExamine(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT, Contstants.ADUIT.WEISHENHE,quitCarManageVo.getEnterpriseId()));
		quitCarManageDao.save(xsBackCarLog);
		return true;
	}

	/**
	 * 删除退车记录
	 */
	
	public void deleteQuitInfor(QuitCarManageVo quitCarManageVo)
			throws Exception {
		XsSellExitCar xsSellExitCar = (XsSellExitCar)quitCarManageDao.get("from XsSellExitCar where exitCarId="+quitCarManageVo.getExitCar_Id()+"");
		quitCarManageDao.delete(xsSellExitCar);
		
	}

	/**
	 * 修改退车记录
	 */
	
	public void updateQuitInfor(QuitCarManageVo quitCarManageVo)
			throws Exception {
		XsSellExitCar xsSellExitCar = (XsSellExitCar)quitCarManageDao.get("from XsSellExitCar where exitCarId="+quitCarManageVo.getExitCar_Id()+"");
		if(quitCarManageVo.getExitCar_Type()!=null&&quitCarManageVo.getExitCar_Type().length()>0)
			xsSellExitCar.setExitCarType(Integer.parseInt(quitCarManageVo.getExitCar_Type()));
		if(quitCarManageVo.getExitCar_Remark()!=null&&quitCarManageVo.getExitCar_Remark().length()>0)
			xsSellExitCar.setExitCarRemark(quitCarManageVo.getExitCar_Remark());
//		if(quitCarManageVo.getXs_Car_Pds_Date()!=null&&quitCarManageVo.getXs_Car_Pds_Date().length()>0)
//			xsSellExitCar.setXsCarPdsDate(quitCarManageVo.getXs_Car_Pds_Date());
//		if(quitCarManageVo.getXs_Car_Pds_Person()!=null&&quitCarManageVo.getXs_Car_Pds_Person().length()>0)
//			xsSellExitCar.setXsCarPdsPerson(Integer.parseInt(quitCarManageVo.getXs_Car_Pds_Person()));
//		if(quitCarManageVo.getXs_Car_Pds_Result()!=null&&quitCarManageVo.getXs_Car_Pds_Result().length()>0)
//			xsSellExitCar.setXsCarPdsResult(quitCarManageVo.getXs_Car_Pds_Result());
		quitCarManageDao.merge(xsSellExitCar);
	}

	/**
	 * 获取预收应收金额
	 */
	
	public QuitCarManageVo getAmountByExit(QuitCarManageVo quitCarManageVo)
			throws Exception {
		QuitCarManageVo vo = new QuitCarManageVo();
		Double backMoney=0d;
		StringBuffer sb1=new StringBuffer("SELECT  (SELECT CASE WHEN  xsc.account_CUMULATIVE!=''  THEN xsc.account_CUMULATIVE ELSE 0 END) AS temp1");
		sb1.append(" FROM xs_sell_collections xsc INNER JOIN xs_sell_account xsa ON xsa.account_code=xsc.account_id");
		sb1.append(" INNER JOIN xs_car_sell_info xcsi ON xcsi.xs_car_sel_id=xsa.xs_car_sel_id");
		sb1.append(" INNER JOIN xs_sell_exitCar xse ON xse.xs_car_sel_id=xcsi.xs_car_sel_id");
		sb1.append(" WHERE xse.exitCar_id="+quitCarManageVo.getExitCar_Id());
			StringBuffer sb2=new StringBuffer("SELECT  (SELECT CASE WHEN  xsc.account_CUMULATIVE!=''  THEN xsc.account_CUMULATIVE ELSE 0 END) AS temp2");
			sb2.append(" FROM xs_sell_collections xsc INNER JOIN xs_sell_car_reserve xscr ON xscr.reserve_code=xsc.account_id");
			sb2.append(" INNER JOIN xs_car_sell_info xcsi ON xcsi.reserve_id=xscr.reserve_id");
			sb2.append(" INNER JOIN xs_sell_exitCar xse ON xse.xs_car_sel_id=xcsi.xs_car_sel_id");
		sb2.append(" WHERE xse.exitCar_id="+quitCarManageVo.getExitCar_Id());
		
		XsSellExitCar xsec=(XsSellExitCar)quitCarManageDao.get(" from XsSellExitCar xsec where exitCarId="+quitCarManageVo.getExitCar_Id());
		XsSellCarReserve xscr=xsec.getXsCarSellInfo().getXsSellCarReserve();
		if(xscr==null){
			List list=quitCarManageDao.createSQLQuery(sb1.toString());
			if(list!=null&&list.size()>0)
				backMoney=(Double)list.get(0);
			vo.setReciveOrSellTag(false);
		}else{
			List list1=quitCarManageDao.createSQLQuery(sb1.toString());
			if(list1!=null&&list1.size()>0)
				backMoney=(Double)list1.get(0);
			List list2=quitCarManageDao.createSQLQuery(sb2.toString());
			if(backMoney.equals(0d)){
				if(list2!=null&&list2.size()>0)
					backMoney=(Double)list2.get(0);
				vo.setReserveId(xscr.getReserveCode().toString());
				vo.setReciveOrSellTag(true);
			}else{
				if(list2!=null&&list2.size()>0)
					backMoney=backMoney+(Double)list2.get(0);
				vo.setReciveOrSellTag(false);
			}
		}
		vo.setBackCar_Sunmoney(backMoney.toString());
		return vo;
	
	}
}
