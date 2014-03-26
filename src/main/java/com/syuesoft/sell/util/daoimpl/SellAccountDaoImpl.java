package com.syuesoft.sell.util.daoimpl;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsSellAccount;
import com.syuesoft.sell.model.XsSellFlowControl;
import com.syuesoft.sell.util.dao.SellAccountDao;
import com.syuesoft.util.CreateID;

@Repository("sellAccountDao")
public class SellAccountDaoImpl extends BaseDaoImpl<Object> implements SellAccountDao {
	@Autowired
	private BaseTagDAO  baseTagDAO;
	//private static String insuranceType="否";//215
     /**
      * 转结算接口
      */
	public void saveSellAccount(Integer xsCarSelId, String accountTypeId,Integer accountType, Double accountMoney,String remark,Integer enterpriseId) throws Exception {
		XsSellAccount sellAccount=new XsSellAccount();
		XsCarSellInfo sellInfo=(XsCarSellInfo) this.get("from  XsCarSellInfo sell where sell.xsCarSelId="+xsCarSelId);
			sellAccount.setXsCarSellInfo(sellInfo);
			sellAccount.setAccountCode(CreateID.createId("account", Contstants.SELL_BILLSDEPLOY.ACCOUNT ));
			sellAccount.setAccountTypeId(accountTypeId);
			sellAccount.setAccountType(accountType);
			sellAccount.setAccountMoney(accountMoney);
			sellAccount.setAccountDate(new Date());
			sellAccount.setRemark(remark);
			sellAccount.setEnterpriseId(enterpriseId);
			BasUsers user = (BasUsers) ServletActionContext.getRequest().getSession().getAttribute(Contstants.CUSTOMER);
			sellAccount.setAccountPerson(Integer.parseInt(String.valueOf(user.getBasStuff().getStfId())));
			sellAccount.setAccountState(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,enterpriseId));
		this.save(sellAccount);
		XsSellFlowControl flow=new XsSellFlowControl();
		flow.setXsfcontrolCode(Contstants.SELLFLOWCONTROLCODE.CONTROLCODE7);
		flow.setXsfcontrolDocument(sellAccount.getAccountCode());
		flow.setXsfcontrolCarId(sellInfo.getXsCarId());
		this.save(flow);
	}
}
	

