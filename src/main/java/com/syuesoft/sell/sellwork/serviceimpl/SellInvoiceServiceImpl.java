package com.syuesoft.sell.sellwork.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsSellInvoice;
import com.syuesoft.sell.sellInsurance.vo.SellInsuranceListVo;
import com.syuesoft.sell.sellwork.dao.SellInvoiceDAO;
import com.syuesoft.sell.sellwork.service.SellInvoiceService;
import com.syuesoft.sell.sellwork.vo.SellInvoiceVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Msg;
import com.syuesoft.util.SystemUser;


@Service("sellInvoiceService")
public class SellInvoiceServiceImpl extends BaseLogServiceImpl implements SellInvoiceService {
    private SellInvoiceDAO sellInvoiceDAO;
    private BaseTagDAO baseTagDAO;
    @Resource
    private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
    
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}
	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}
	public SellInvoiceDAO getSellInvoiceDAO() {
		return sellInvoiceDAO;
	}
	@Autowired
	public void setSellInvoiceDAO(SellInvoiceDAO sellInvoiceDAO) {
		this.sellInvoiceDAO = sellInvoiceDAO;
	}
	//获取初始化时间
	private String edate = FormatTime.yesTady("yyyy-MM-dd");
	
	
	/**
	 * 车辆销售财务信息管理
	 */
	public Datagrid findSellInfor(SellInvoiceVo sellInvoiceVo)throws Exception{
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 		
				(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 		
				Contstants.COLLIGATES.DEFAULTSHOWDAY,sellInvoiceVo.getEnterprise_id()).getCiValue()));
		Datagrid dg = new Datagrid();
		StringBuffer sql =new StringBuffer( "SELECT   a.xs_car_sel_id,   c.instorehouse_date, a.xs_car_sel_data, d.xs_custom_name, e.stf_NAME,  f.xs_car_vin_number,"+
						"f.xs_car_ocn,(SELECT   k.datavalue  FROM xs_childdictionary k  WHERE g.xs_car_brand = k.child_id) AS xs_car_brand,"+
						"g.xs_model_name,a.xs_car_sel_transaction_money,b.STF_ID_PERSON,h.xs_distributor_name,"+
						"(SELECT  k.datavalue  FROM  xs_childdictionary k WHERE a.audit = k.child_id) AS audit,b.limit_load_num,"+
						"b.mobilephone,a.out_id, b.reserve_code,a.xs_car_give_up,invoice.id,invoice.invoice_code,invoice.invoice_date," +
						" (SELECT suf.stf_NAME FROM bas_stuff suf WHERE suf.STF_ID=invoice.invoice_person ) AS kpPerson," +
						"(SELECT suf.stf_NAME FROM bas_stuff suf WHERE suf.STF_ID=invoice._person ) AS skPerson," +
						"invoice.achievement_,invoice.invoice_parce,invoice.invoice_type,invoice.invoice_remark,invoice.invoice_number,a.is_invoice," +
						"invoice.examin,(SELECT  k.datavalue  FROM  xs_childdictionary k WHERE invoice.examin = k.child_id)as examinName, " +
						"invoice.hsDiscount,invoice.wsDiscount,invoice.discount,invoice.afterHsDiscount," +
						"invoice.wsPurchase,invoice.purchase," +
						"invoice.afterWsMoney,invoice.zhTax,a.sell_code," +
						"invoice.invoice_person,invoice._person " +
						"FROM xs_sell_invoice  invoice"+
						 "  JOIN xs_car_sell_info a  ON invoice.xs_car_sel_id=a.xs_car_sel_id"+
						 " LEFT JOIN xs_sell_instorehouse c  ON a.out_id = c.instorehouse_id"+
						 " LEFT JOIN xs_sell_car_reserve b  ON a.reserve_id = b.reserve_id"+
						 " LEFT  JOIN xs_custom_info d  ON  b.custom_id = d.custom_id"+
						 " LEFT JOIN bas_stuff e  ON  d.STF_ID = e.STF_ID"+
						 " LEFT  JOIN xs_car_info f  ON  b.xs_car_id = f.xs_car_id"+
						 " LEFT JOIN xs_car_model g  ON b.car_model = g.xs_model_id"+
						 " LEFT JOIN xs_distributor_info h   ON f.xs_distributor_id = h.xs_distributor_id" +
						 "	WHERE 1 = 1 ");
		
		if(sellInvoiceVo.getEnterprise_id()!=null && !sellInvoiceVo.getEnterprise_id().equals("")){
			sql.append( "  and a.enterprise_id ="+sellInvoiceVo.getEnterprise_id() );
		}
		
		
		if(sellInvoiceVo.getXs_Car_Sel_Data()!=null  && !("".equals(sellInvoiceVo.getXs_Car_Sel_Data()))){
			
			sql.append( " and Date(a.xs_car_sel_data) >= '"+sellInvoiceVo.getXs_Car_Sel_Data()+"'" );
		}
		if(sellInvoiceVo.getXs_Car_Sel_Data2()!=null  && !("".equals(sellInvoiceVo.getXs_Car_Sel_Data2()))){
			
			sql.append( " and Date(a.xs_car_sel_data) <= '"+sellInvoiceVo.getXs_Car_Sel_Data2()+"'" );
		}
		
		if((sellInvoiceVo.getXs_Car_Sel_Data()==null||"".equals(sellInvoiceVo.getXs_Car_Sel_Data()))&&
				(sellInvoiceVo.getXs_Car_Sel_Data2()==null||"".equals(sellInvoiceVo.getXs_Car_Sel_Data2()))){
			sql.append( " and Date(a.xs_car_sel_data) BETWEEN '"+sdate+"' AND '"+edate+"'");
		}
	
		if(sellInvoiceVo.getXs_Custom_Name()!=null && !sellInvoiceVo.getXs_Custom_Name().equals("")){
			sql.append( "  and d.Xs_Custom_Name like '%"+StringEscapeUtils.escapeSql(sellInvoiceVo.getXs_Custom_Name().trim())+"%'");
		}
		if(sellInvoiceVo.getStf_Name()!=null && !sellInvoiceVo.getStf_Name().equals("")){
			sql.append( "  and d.STF_ID ="+sellInvoiceVo.getStf_Name() );
		}
		if(sellInvoiceVo.getXs_Car_Vin_Number()!=null && !sellInvoiceVo.getXs_Car_Vin_Number().equals("")){
			sql.append( " and f.Xs_Car_Vin_Number like '%"+StringEscapeUtils.escapeSql(sellInvoiceVo.getXs_Car_Vin_Number().trim())+"%'");
		}
		if(sellInvoiceVo.getXs_Car_Ocn()!=null && !sellInvoiceVo.getXs_Car_Ocn().equals("")){
			sql.append( " and f.Xs_Car_Ocn like '%"+StringEscapeUtils.escapeSql(sellInvoiceVo.getXs_Car_Ocn().trim())+"%'");
		}
		List<Object[]> lst=sellInvoiceDAO.createSQLQuery(sql.toString(), sellInvoiceVo.getPage(), sellInvoiceVo.getRows());
		List<SellInvoiceVo> rows=new ArrayList<SellInvoiceVo>();
		if(lst!=null && lst.size()>0){
			for (Object [] obj:lst) {
				SellInvoiceVo vo = new SellInvoiceVo();
				if(obj[0]!=null && !("".equals(obj[0]))){vo.setXs_Car_Sel_Id((Integer)obj[0]);}
				if(obj[1]!=null && !("".equals(obj[1])) ){vo.setInstorehouse_Date((Date)obj[1]);}
				if(obj[2]!=null && !("".equals(obj[2]))){vo.setXs_Car_Sel_Data(obj[2].toString());}
				if(obj[3]!=null && !("".equals(obj[3]))){vo.setXs_Custom_Name(obj[3].toString());}
				if(obj[4]!=null && !("".equals(obj[4]))){vo.setStf_Name(obj[4].toString());}
				if(obj[5]!=null && !("".equals(obj[5]))){vo.setXs_Car_Vin_Number(obj[5].toString());}
				if(obj[6]!=null && !("".equals(obj[6]))){vo.setXs_Car_Ocn(obj[6].toString());}
				if(obj[7]!=null && !("".equals(obj[7]))){vo.setXs_Car_Brand(obj[7].toString());}
				if(obj[8]!=null && !("".equals(obj[8])) ){vo.setXs_Model_Name(obj[8].toString());}
				if(obj[9]!=null && !("".equals(obj[9]))){vo.setXs_Car_Sel_Transaction_Money(obj[9].toString());}
				if(obj[10]!=null && !("".equals(obj[10]))){vo.setUser_Name(obj[10].toString());}
				if(obj[11]!=null && !("".equals(obj[11]))){vo.setXs_Distributor_Name(obj[11].toString());}
				if(obj[12]!=null && !("".equals(obj[12]))){vo.setExamine(obj[12].toString());}
				if(obj[13]!=null && !("".equals(obj[13]))){vo.setLimit_load_num(obj[13].toString());}
				if(obj[14]!=null && !("".equals(obj[14]))){vo.setMobilephone(obj[14].toString());}
				//出库单 用作判断是否出库
				if(obj[15]!=null && !("".equals(obj[15]))){vo.setOut_Id(obj[15].toString());}
				if(obj[16]!=null && !("".equals(obj[16]))){vo.setReserve_Code(obj[16].toString());}
				if(obj[17]!=null && !("".equals(obj[17]))){vo.setXs_Car_Give_Up(obj[17].toString());};
				//开票信息
				if(obj[18]!=null && !("".equals(obj[18]))){vo.setId((Integer)obj[18]);};
				if(obj[19]!=null && !("".equals(obj[19]))){vo.setInvoiceCode(obj[19].toString());};
				if(obj[20]!=null && !("".equals(obj[20]))){vo.setInvoiceDate((Date)obj[20]);};
				if(obj[21]!=null && !("".equals(obj[21]))){vo.setInvoicePersonN(obj[21].toString());};
				if(obj[22]!=null && !("".equals(obj[22]))){vo.setPersonN(obj[22].toString());};
				if(obj[23]!=null && !("".equals(obj[23]))){vo.setAchievement(obj[23].toString());};
				if(obj[24]!=null && !("".equals(obj[24]))){vo.setInvoiceParce((Double)obj[24]);};
				if(obj[25]!=null && !("".equals(obj[25]))){vo.setInvoiceType((Integer)obj[25]);};
				if(obj[26]!=null && !("".equals(obj[26]))){vo.setInvoiceRemark(obj[26].toString());};
				if(obj[27]!=null && !("".equals(obj[27]))){vo.setInvoiceNumber(obj[27].toString());};
				if(obj[28]!=null && !("".equals(obj[28]))){vo.setIsInvoice((Integer)obj[28]);};
				if(obj[29]!=null && !("".equals(obj[29]))){vo.setInvoiceExamin((Integer)obj[29]);};
				if(obj[30]!=null && !("".equals(obj[30]))){vo.setInvoiceExaminName(obj[30].toString());};
				if(obj[31]!=null && !("".equals(obj[31]))){vo.setHsDiscount((Double)obj[31]);};
				if(obj[32]!=null && !("".equals(obj[32]))){vo.setWsDiscount((Double)obj[32]);};
				if(obj[33]!=null && !("".equals(obj[33]))){vo.setDiscount((Double)obj[33]);};
				if(obj[34]!=null && !("".equals(obj[34]))){vo.setAfterHsDiscount((Double)obj[34]);};
				if(obj[35]!=null && !("".equals(obj[35]))){vo.setWsPurchase((Double)obj[35]);};
				if(obj[36]!=null && !("".equals(obj[36]))){vo.setPurchase((Double)obj[36]);};
				if(obj[37]!=null && !("".equals(obj[37]))){vo.setAfterWsMoney((Double)obj[37]);};
				if(obj[38]!=null && !("".equals(obj[38]))){vo.setZhTax((Double)obj[38]);};
				if(obj[39]!=null && !("".equals(obj[39]))){vo.setSellCode(obj[39].toString());};
				if(obj[40]!=null && !("".equals(obj[40]))){vo.setInvoicePerson(Integer.parseInt(obj[40].toString()));};
				if(obj[41]!=null && !("".equals(obj[41]))){vo.setPerson(Integer.parseInt(obj[41].toString()));};
				rows.add(vo);
				}
			}	
		int total = sellInvoiceDAO.getSQLCount(sql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

	/**
	 * 删除销售票据信息
	 */
	@Log(systemName="销售系统", moduleName="车辆销售财务信息管理",opertype="删除")
	
	public Msg deleteSellInvoice(SellInvoiceVo sellInvoiceVo) throws Exception {
		Msg msg=new Msg();
		
		
		XsSellInvoice invoice=(XsSellInvoice) sellInvoiceDAO.get("from XsSellInvoice  sell where sell.id="+sellInvoiceVo.getId());
		if(invoice.getExamin()==185){
			XsCarSellInfo sellInfo=(XsCarSellInfo) sellInvoiceDAO.get("from  XsCarSellInfo sell where sell.xsCarSelId="+sellInvoiceVo.getXs_Car_Sel_Id());
			sellInfo.setIsInvoice(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellInvoiceVo.getEnterprise_id()));
			sellInvoiceDAO.merge(sellInfo);
			
			sellInvoiceDAO.delete(invoice);
			setContent(SystemUser.getUser().getBasStuff().getStfName()+"删除编号为"+invoice.getInvoiceCode()+"的销售票据信息！");
			msg.setMsg("删除成功！");
			msg.setSuccess(true);
		}else if(invoice.getExamin()==186){
			msg.setMsg("该信息已审核，不可以删除！");
			msg.setSuccess(false);
		}
		return msg;
	}
	/**
	 * 保存销售票据信息
	 */
	@Log(systemName="销售系统", moduleName="车辆销售财务信息管理",opertype="新增")
	
	public void saveSellInvoice(SellInvoiceVo sellInvoiceVo) throws Exception {
		XsCarSellInfo sellInfo=(XsCarSellInfo) sellInvoiceDAO.get("from  XsCarSellInfo sell where sell.xsCarSelId="+sellInvoiceVo.getXs_Car_Sel_Id());
		sellInfo.setIsInvoice(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE1,sellInvoiceVo.getEnterprise_id()));
		sellInvoiceDAO.merge(sellInfo);
		XsSellInvoice invoice=new XsSellInvoice();
		BeanUtils.copyProperties(sellInvoiceVo, invoice);
		invoice.setXsCarSellInfo(sellInfo);
		invoice.setInvoiceCode(CreateID.createId("INVOICE", Contstants.SELL_BILLSDEPLOY.INVOICECODE));
		invoice.setExamin(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellInvoiceVo.getEnterprise_id()));
		sellInvoiceDAO.save(invoice);
		setContent(SystemUser.getUser().getBasStuff().getStfName()+"新增编号为"+invoice.getInvoiceCode()+"的销售票据信息！");
		
	}
	
	/**
	 * 修改销售票据信息
	 */
	@Log(systemName="销售系统", moduleName="车辆销售财务信息管理",opertype="修改")
	
	public Msg updateSellInvoice(SellInvoiceVo sellInvoiceVo) throws Exception {
		Msg msg=new Msg();
		XsSellInvoice invoices=(XsSellInvoice) sellInvoiceDAO.get("from XsSellInvoice  sell where sell.id="+sellInvoiceVo.getId());
		if(invoices.getExamin()==185){
			XsCarSellInfo sellInfo=(XsCarSellInfo) sellInvoiceDAO.get("from  XsCarSellInfo sell where sell.xsCarSelId="+sellInvoiceVo.getXs_Car_Sel_Id());
			XsSellInvoice invoice=new XsSellInvoice();
			BeanUtils.copyProperties(sellInvoiceVo, invoice);
			invoice.setXsCarSellInfo(sellInfo);
			invoice.setExamin(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellInvoiceVo.getEnterprise_id()));
			sellInvoiceDAO.merge(invoice);
			msg.setMsg("修改成功！");
			msg.setSuccess(true);
			setContent(SystemUser.getUser().getBasStuff().getStfName()+"修改编号为"+invoice.getInvoiceCode()+"的销售票据信息！");	
		}else if(invoices.getExamin()==186){
			msg.setMsg("该信息已审核，不可以修改！");
			msg.setSuccess(false);
		}
		return msg;
		
		
	}
	
	public void updateExamin(SellInvoiceVo sellInvoiceVo) throws Exception {
		List<Object> lst=sellInvoiceDAO.findExaminState(sellInvoiceVo);
		if(lst!=null && lst.size()>0){
			for(Object obj:lst){
				XsSellInvoice sellInvoice=(XsSellInvoice)obj;
				if(Integer.parseInt(sellInvoice.getExamin().toString())==
					Integer.parseInt(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellInvoiceVo.getEnterprise_id()).toString())){
				
					sellInvoice.setExamin(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT1,sellInvoiceVo.getEnterprise_id()));
				}else{
					sellInvoice.setExamin(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellInvoiceVo.getEnterprise_id()));
				}
			
				sellInvoiceDAO.merge(sellInvoice);
			}
		}
		
	}
	/**
	 * 判断是否已审核
	 * */
	public Boolean isRefundment(SellInvoiceVo sellInvoiceVo) throws Exception {
		Integer examine=baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,sellInvoiceVo.getEnterprise_id());
		if(sellInvoiceVo.getInvoiceExamin().equals(examine))
			return true;
		return false;
	}
}
