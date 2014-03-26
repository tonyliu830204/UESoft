package com.syuesoft.vipmanagement.daoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasVipSendinfomation;
import com.syuesoft.vipmanagement.dao.SmsRecordManagementDao;
import com.syuesoft.vipmanagement.vo.SmsRecordManagementVo;

@Repository("smsRecordManagementDao")
public class SmsRecordManagementDaoImpl extends BaseDaoImpl<BasVipSendinfomation> implements
		SmsRecordManagementDao {

	/**
	 * 删除短信记录
	 */
	public void doDelete(final SmsRecordManagementVo smsRecordManagementVo)
			throws Exception {
		
	    String sql = "SELECT  ID, info_id FROM bas_vip_sendinfomation_custom WHERE info_id = '"+smsRecordManagementVo.getInfo_Id()+"' ";
	    List<Object[]> list = this.createSQLQuery(sql);
	    if(list != null && list.size() > 1){
	        String sql1 = "DELETE FROM bas_vip_sendinfomation_custom WHERE ID = '"+smsRecordManagementVo.getInfo_detailId()+"' AND info_id ='"+smsRecordManagementVo.getInfo_Id()+"' ";
            this.deleteBySql(sql1);
	    }else{
	        String sql1 = "DELETE FROM bas_vip_sendinfomation_custom WHERE ID = '"+smsRecordManagementVo.getInfo_detailId()+"' AND info_id ='"+smsRecordManagementVo.getInfo_Id()+"' ";
            this.deleteBySql(sql1);
    	    String sql2 = "DELETE FROM bas_vip_sendinfomation WHERE info_id = '"+smsRecordManagementVo.getInfo_Id()+"' ";
            this.deleteBySql(sql2);
	    }
	}
}