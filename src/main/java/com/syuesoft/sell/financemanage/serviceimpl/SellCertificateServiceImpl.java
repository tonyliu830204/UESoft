package com.syuesoft.sell.financemanage.serviceimpl;

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
import com.syuesoft.sell.financemanage.dao.SellCertificateDao;
import com.syuesoft.sell.financemanage.service.SellCertificateService;
import com.syuesoft.sell.financemanage.vo.SellCertificateVo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellCertificate;
import com.syuesoft.sell.model.XsSellReceipt;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.SystemUser;

@Service("sellCertificateService")
public class SellCertificateServiceImpl  extends BaseLogServiceImpl implements SellCertificateService {
	
	@Resource
	private SellCertificateDao sellCertificateDao;
	@Resource
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	@Autowired
	private BaseTagDAO baseTagDAO;
	//获取初始化时间
	private String edate = FormatTime.yesTady("yyyy-MM-dd");
	
	/**
	 * 合格证管理
	 */
	
	public Datagrid getPager(SellCertificateVo sellCertificateVo)
			throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 		
				(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 		
				Contstants.COLLIGATES.DEFAULTSHOWDAY,sellCertificateVo.getEnterpriseId()).getCiValue()));
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("SELECT car.xs_car_code,car.xs_car_brand,(SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id=car.xs_car_brand) carBrandName,"+
				"car.xs_car_model_id,(SELECT  m.xs_model_name FROM xs_car_model m WHERE m.xs_model_id=car.xs_car_model_id) carModelName,car.xs_car_color,"+
				"(SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id=car.xs_car_color) carColorName,car.xs_car_vin_number,certificate.certificate_id,"+
				"car.xs_car_id,certificate.receipt_id,certificate.xs_car_certificate,certificate.xs_car_certificate_state,certificate.redemption_date,"+
				"certificate.certificate_person,certificate.certificate_date,certificate.remark,receipt.receipt_code," +
				"(SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id=certificate.xs_car_certificate_state) certificateState,  receipt.receipt_startDate,receipt.receipt_endDate,receipt.receipt_bank, " +
				" (SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id=receipt.receipt_bank) bankName," +
				" car.xs_car_sell_state,(SELECT  c.dataValue FROM xs_childdictionary c WHERE c.child_id= car.xs_car_sell_state) sellSteteName   "+
				" FROM  xs_car_info car 	" +
				" INNER JOIN xs_sell_flow_control xsfc ON xsfc.xsfcontrol_car_id=car.xs_car_id AND xsfc.xsfcontrol_document=car.xs_car_code"+
				" LEFT JOIN xs_sell_certificate certificate  ON car.xs_car_id=certificate.xs_car_id " +
				"LEFT JOIN  xs_sell_receipt receipt ON  certificate.receipt_id=receipt.receipt_id" +
				" where 1=1");
		
		if(sellCertificateVo.getEnterpriseId()!=null && !("".equals(sellCertificateVo.getEnterpriseId()))){
			hql.append(" and car.enterprise_id = "+sellCertificateVo.getEnterpriseId());
		}
		if(sellCertificateVo.getCarBrand()!=null && !("".equals(sellCertificateVo.getCarBrand()))){
			hql.append(" and car.xs_car_brand = "+sellCertificateVo.getCarBrand());
		}
		if(sellCertificateVo.getCarModelId()!=null && !("".equals(sellCertificateVo.getCarModelId()))){
			hql.append(" and car.xs_car_model_id ="+sellCertificateVo.getCarModelId());
		}
		if(sellCertificateVo.getReceiptId()!=null && !("".equals(sellCertificateVo.getReceiptId()))){
			hql.append(" and receipt.receipt_id ='"+sellCertificateVo.getReceiptId()+"'");
		}
		if(sellCertificateVo.getCarVinNumber()!=null && !("".equals(sellCertificateVo.getCarVinNumber()))){
			hql.append(" and car.xs_car_vin_number like'%"+StringEscapeUtils.escapeSql(sellCertificateVo.getCarVinNumber().trim())+"%'");
		}
		//领证日期
		if(sellCertificateVo.getQueryInstorehouseDate()!=null  && !("".equals(sellCertificateVo.getQueryInstorehouseDate()))){
			
			hql.append( " and Date(certificate_date) >= '"+sellCertificateVo.getQueryInstorehouseDate()+"'" );
		}
		if(sellCertificateVo.getQueryInstorehouseDate2()!=null  && !("".equals(sellCertificateVo.getQueryInstorehouseDate2()))){
			
			hql.append( " and Date(certificate_date) <= '"+sellCertificateVo.getQueryInstorehouseDate2()+"'" );
		}
		
		/*if((sellCertificateVo.getQueryInstorehouseDate()==null||"".equals(sellCertificateVo.getQueryInstorehouseDate()))&&
				(sellCertificateVo.getQueryInstorehouseDate2()==null||"".equals(sellCertificateVo.getQueryInstorehouseDate2()))){
			hql.append( " and Date(certificate_date) BETWEEN '"+sdate+"' AND '"+edate+"'");
		}*/
		if(sellCertificateVo.getSellState()!=null && !("".equals(sellCertificateVo.getSellState()))){
			hql.append(" and car.xs_car_sell_state = '"+sellCertificateVo.getSellState()+"'");
		}else{
			hql.append(" and car.xs_car_sell_state = '"+baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.FORSALE,sellCertificateVo.getEnterpriseId())+"'");
		}
		if(sellCertificateVo.getQueryStartDate()!=null  && !("".equals(sellCertificateVo.getQueryStartDate()))){
			
			hql.append( " and Date(receipt.receipt_startDate) >= '"+sellCertificateVo.getQueryStartDate()+"'" );
		}
		if(sellCertificateVo.getQueryStartDate2()!=null  && !("".equals(sellCertificateVo.getQueryStartDate2()))){
			
			hql.append( " and Date(receipt.receipt_startDate) <= '"+sellCertificateVo.getQueryStartDate2()+"'" );
		}
		if(sellCertificateVo.getQueryEndDate()!=null  && !("".equals(sellCertificateVo.getQueryEndDate()))){
			
			hql.append( " and Date(receipt.receipt_endDate) >= '"+sellCertificateVo.getQueryEndDate()+"'" );
		}
		if(sellCertificateVo.getQueryEndDate2()!=null  && !("".equals(sellCertificateVo.getQueryEndDate2()))){
			
			hql.append( " and Date(receipt.receipt_endDate) <= '"+sellCertificateVo.getQueryEndDate2()+"'" );
		}
		if(sellCertificateVo.getReceiptCode()!=null  && !("".equals(sellCertificateVo.getReceiptCode()))){
			
			hql.append( " AND receipt.receipt_code LIKE  '%"+sellCertificateVo.getReceiptCode()+"%'" );
		}
		
		List<Object[]> lst=sellCertificateDao.createSQLQuery(hql.toString(), sellCertificateVo.getPage(), sellCertificateVo.getRows());
		List<SellCertificateVo> rows =new ArrayList<SellCertificateVo>();
		if(lst!=null && lst.size()>0){
			for(Object[] b:lst){
				SellCertificateVo rVo=new SellCertificateVo();
				if(b[0]!=null && !("".equals(b[0]))){
					rVo.setCarCode(b[0].toString());
				}
				if(b[1]!=null && !("".equals(b[1]))){
					rVo.setCarBrand((Integer)b[1]);
				}
				if(b[2]!=null && !("".equals(b[2]))){
					rVo.setCarBrandName(b[2].toString());
				}
				if(b[3]!=null && !("".equals(b[3]))){
					rVo.setCarModelId((Integer)b[3]);
				}
				if(b[4]!=null && !("".equals(b[4]))){
					rVo.setCarModelName(b[4].toString());
				}
				if(b[5]!=null && !("".equals(b[5]))){
					rVo.setCarColor((Integer)b[5]);
				}
				if(b[6]!=null && !("".equals(b[6]))){
					rVo.setColorName(b[6].toString());
				}
				if(b[7]!=null && !("".equals(b[7]))){
					rVo.setCarVinNumber(b[7].toString());
				}
				if(b[8]!=null && !("".equals(b[8]))){
					rVo.setCertificateId((Integer)b[8]);
				}
				if(b[9]!=null && !("".equals(b[9]))){
					rVo.setXsCarId((Integer)b[9]);
				}
				if(b[10]!=null && !("".equals(b[10]))){
					rVo.setReceiptId((Integer)b[10]);
				}
				if(b[11]!=null && !("".equals(b[11]))){
					rVo.setXsCarCertificate(b[11].toString());
				}
				if(b[12]!=null && !("".equals(b[12]))){
					rVo.setXsCarCertificateState((Integer)b[12]);
				}
				if(b[13]!=null && !("".equals(b[13]))){
					rVo.setRedemptionDate((Date)b[13]);
				}
				if(b[14]!=null && !("".equals(b[14]))){
					rVo.setCertificatePerson(b[14].toString());
				}
				if(b[15]!=null && !("".equals(b[15]))){
					rVo.setCertificateDate((Date)b[15]);
				}
				if(b[16]!=null && !("".equals(b[16]))){
					rVo.setRemark(b[16].toString());
				}
				if(b[17]!=null && !("".equals(b[17]))){
					rVo.setReceiptCode(b[17].toString());
				}
				if(b[18]!=null && !("".equals(b[18]))){
					rVo.setCertificateStateName(b[18].toString());
				}
				
				if(b[19]!=null && !("".equals(b[19]))){
					rVo.setReceiptStartDate((Date)b[19]);
				}
				if(b[20]!=null && !("".equals(b[20]))){
					rVo.setReceiptEndDate((Date)b[20]);
				}
				if(b[21]!=null && !("".equals(b[21]))){
					rVo.setReceiptBank((Integer)b[21]);
				}
				if(b[22]!=null && !("".equals(b[22]))){
					rVo.setBankName(b[22].toString());
				}
				if(b[23]!=null && !("".equals(b[23]))){
					rVo.setSellState(b[23].toString());
				}
				if(b[24]!=null && !("".equals(b[24]))){
					rVo.setSellStateName(b[24].toString());
				}
				
				rows.add(rVo);
			}
		}
		int total = sellCertificateDao.getSQLCount(hql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	public void addCertificate(SellCertificateVo sellCertificateVo)
			throws Exception {
		XsSellCertificate sellCertificate=new XsSellCertificate();
		sellCertificate.setRedemptionDate(sellCertificateVo.getRedemptionDate());
		sellCertificate.setReceiptId(sellCertificateVo.getReceiptId());
		sellCertificate.setRemark(sellCertificateVo.getRemark());
		sellCertificate.setXsCarCertificate(sellCertificateVo.getXsCarCertificate());
		if(sellCertificateVo.getXsCarCertificateState()!=null){
		XsChilddictionary state=baseTagDAO.findById(sellCertificateVo.getXsCarCertificateState());
		sellCertificate.setXsSellCertificateState(state);
		}
		sellCertificate.setXsCarId(sellCertificateVo.getXsCarId());
		sellCertificate.setCertificateDate(sellCertificateVo.getCertificateDate());
		sellCertificate.setCertificatePerson(sellCertificateVo.getCertificatePerson());
		
		//BeanUtils.copyProperties(sellCertificateVo, sellCertificate);
		sellCertificateDao.save(sellCertificate);	
		
	}

	/**
	 * 删除合格证信息
	 */
	@Log(systemName="销售系统", moduleName="合格证管理",opertype="删除")
	
	public void deleteCertificate(SellCertificateVo sellCertificateVo)
			throws Exception {
		XsSellCertificate sellCertificate=(XsSellCertificate) sellCertificateDao.get(" from XsSellCertificate where certificateId="+sellCertificateVo.getCertificateId());
		//BeanUtils.copyProperties(sellCertificateVo, sellCertificate);
		sellCertificateDao.delete(sellCertificate);	
		setContent(SystemUser.getUser().getBasStuff().getStfName()+"删除了编号为"+sellCertificate.getCertificateId()+"的合格证信息！");
	}

	/**
	 * 修改合格证信息
	 */
	@Log(systemName="销售系统", moduleName="合格证管理",opertype="修改")
	
	public void updateCertificate(SellCertificateVo sellCertificateVo)
			throws Exception {
		XsSellCertificate sellCertificate=new XsSellCertificate();
		if(sellCertificateVo.getCertificateId()!=null && !("".equals(sellCertificateVo.getCertificateId()))){
			sellCertificate.setCertificateId(sellCertificateVo.getCertificateId());
		}
		sellCertificate.setRedemptionDate(sellCertificateVo.getRedemptionDate());
		sellCertificate.setReceiptId(sellCertificateVo.getReceiptId());
		sellCertificate.setRemark(sellCertificateVo.getRemark());
		sellCertificate.setXsCarCertificate(sellCertificateVo.getXsCarCertificate());
		if(sellCertificateVo.getXsCarCertificateState()!=null){
		XsChilddictionary state=baseTagDAO.findById(sellCertificateVo.getXsCarCertificateState());
		sellCertificate.setXsSellCertificateState(state);
		}
		sellCertificate.setXsCarId(sellCertificateVo.getXsCarId());
		//BeanUtils.copyProperties(sellCertificateVo, sellCertificate);
		sellCertificateDao.saveOrUpdate(sellCertificate);	
		setContent(SystemUser.getUser().getBasStuff().getStfName()+"修改了编号为"+sellCertificate.getCertificateId()+"的合格证信息！");
	}
	
	public List<ComboBoxJson> getReceipt(SellCertificateVo sellCertificateVo)
			throws Exception {
		String hql="from XsSellReceipt  receipt where 1=1 where receipt.enterpriseId="+sellCertificateVo.getEnterpriseId()+"";
		List<Object>lst=sellCertificateDao.find(hql);
		List<ComboBoxJson> receipts=new ArrayList<ComboBoxJson>();
		if(lst!=null && lst.size()>0){
			for(Object obj:lst){
				XsSellReceipt receipt=(XsSellReceipt)obj;
				ComboBoxJson com=new ComboBoxJson();
				com.setId(receipt.getReceiptId().toString());
				com.setText(receipt.getReceiptCode().toString());
				receipts.add(com);
			}
		}
		return receipts;
		
	}
	/**
	 * 领证
	 */
	@Log(systemName="销售系统", moduleName="合格证管理",opertype="领证")
	
	public void modifyCertificate(SellCertificateVo sellCertificateVo)
			throws Exception {
		XsSellCertificate sellCertificate=(XsSellCertificate) sellCertificateDao.get("from XsSellCertificate c where c.certificateId="+sellCertificateVo.getCertificateId());
		sellCertificate.setCertificatePerson(sellCertificateVo.getCertificatePerson());
		sellCertificate.setCertificateDate(sellCertificateVo.getCertificateDate());
		sellCertificateDao.update(sellCertificate);	
		setContent(SystemUser.getUser().getBasStuff().getStfName()+"领取编号为"+sellCertificate.getCertificateId()+"的合格证！");
	}

}
