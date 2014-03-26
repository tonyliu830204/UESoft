package com.syuesoft.fin.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.fin.dao.BasChilddictionaryDao;
import com.syuesoft.fin.dao.StVendorAccountDao;
import com.syuesoft.fin.vo.RelcampBalanceOfAccountVo;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.StVendorAccount;
import com.syuesoft.util.Json;

@Repository("stVendorAccountDao")
public class StVendorAccountDaoImpl extends BaseDaoImpl<StVendorAccount>
        implements StVendorAccountDao
{

    @Autowired
    BasChilddictionaryDao basChilddictionaryDao;

    /**
     * 供应商对账单信息加载
     */
    public Json loadStVendorAccount(String relcampId)
            throws Exception{
    	Json json=new Json();
        List<RelcampBalanceOfAccountVo> list = new ArrayList<RelcampBalanceOfAccountVo>();
        StringBuffer sb = new StringBuffer("SELECT ACCOUNT_INDEX,ACCOUNT_DATE,ACCOUNT_RECEIPT,BILL_TYPE,RECEIPT_ID"+
		" ,RELCAMP_ID,RELCAMP_NAME,OPER_TYPE,PAY_STYLE,REC_AMOUNT,PAID_AMOUNT,VENDOR_BALANCE"+
		" ,STF_ID,STF_NAME,VENDOR_REMARK,NOW_PAID_AMOUNT FROM ("+
		" SELECT c.*,brc.RELCAMP_NAME FROM ("+
		" SELECT b.*,bc2.dataValue AS PAY_STYLE FROM ("+
		" SELECT a.*,bc1.dataValue AS BILL_TYPE FROM ("+
		" SELECT st_vendor_account.*,bas_stuff.STF_NAME FROM st_vendor_account"+
		" INNER JOIN bas_stuff ON st_vendor_account.STF_ID=bas_stuff.STF_ID) AS a"+
		" INNER JOIN bas_childdictionary bc1 ON a.ACCOUNT_RECEIPT=bc1.dataKey) AS b"+
		" INNER JOIN bas_childdictionary bc2 ON b.OPER_TYPE=bc2.child_id) AS c"+
		" INNER JOIN bas_relation_campany brc ON c.RELCAMP_ID=brc.RELCAMP_ID) AS d where 1=1 ");
        if (relcampId != null && !relcampId.trim().equals(""))
            sb.append(" AND RELCAMP_ID=" + relcampId.trim());
        int count =getSQLCount(sb.toString(), null);
        List<Object[]> resultList = createSQLQuery(sb.toString());
        if (resultList != null && resultList.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                RelcampBalanceOfAccountVo rboavo = new RelcampBalanceOfAccountVo();
                obj = resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    rboavo.setAccountIndex(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    rboavo.setAccountDate(obj[1].toString().substring(0, 19));
                if (obj[2] != null && !obj[2].equals(""))
                    rboavo.setAccountReceipt(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    rboavo.setAccountReceiptName(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    rboavo.setReceiptId(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    rboavo.setRelcampId(obj[5].toString());
                if (obj[6] != null && !obj[6].equals(""))
                    rboavo.setRelcampName(obj[6].toString());
                if (obj[7] != null && !obj[7].equals(""))
                    rboavo.setOperType(obj[7].toString());
                if (obj[8] != null && !obj[8].equals(""))
                    rboavo.setOperTypeName(obj[8].toString());
                if (obj[9] != null && !obj[9].equals(""))
                    rboavo.setRecAmount(obj[9].toString());
                if (obj[10] != null && !obj[10].equals(""))
                    rboavo.setPaidAmount(obj[10].toString());
                if (obj[11] != null && !obj[11].equals(""))
                    rboavo.setVendorBalance(obj[11].toString());
                if (obj[12] != null && !obj[12].equals(""))
                    rboavo.setStfId(obj[12].toString());
                if (obj[13] != null && !obj[13].equals(""))
                    rboavo.setStfName(obj[13].toString());
                if (obj[14] != null && !obj[14].equals(""))
                    rboavo.setVendorRemark(obj[14].toString());
                if (obj[15] != null && !obj[15].equals(""))
                    rboavo.setNowPaidAmount(obj[15].toString());
                list.add(rboavo);
            }
        }
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 供应商对账单汇总 预加载
     */
    public List<RelcampBalanceOfAccountVo> loadStVendorAccountMain(
            String relcampId) throws Exception{
        List<RelcampBalanceOfAccountVo> list = new ArrayList<RelcampBalanceOfAccountVo>();
        StringBuffer sb = new StringBuffer("SELECT st_goods_storage.RELCAMP_ID "+
        		",bas_relation_campany.RELCAMP_NAME "+
        		",SUM(st_goods_storage.TOTAL_NUM) AS '总数量' "+
        		",SUM(st_goods_storage.TOTAL_AMOUNT) AS '总金额' "+
        		",SUM(st_goods_storage.TAXCOUNT) AS '总成本额' "+
        		",SUM(st_goods_storage.TAXCOUNT) AS '应付金额' "+
        		"FROM st_goods_storage,bas_relation_campany "+
        		"WHERE st_goods_storage.RELCAMP_ID=bas_relation_campany.RELCAMP_ID "+
        		"AND st_goods_storage.STORAGE_ID IN  "+
        		"(SELECT st_vendor_account.RECEIPT_ID FROM st_vendor_account  "+
        		"WHERE st_goods_storage.RELCAMP_ID=st_vendor_account.RELCAMP_ID)");
        if (relcampId != null && !relcampId.trim().equals(""))
        	sb.append(" AND st_goods_storage.RELCAMP_ID=" + relcampId.trim());
        sb.append(" GROUP BY st_goods_storage.RELCAMP_ID");
        List<Object[]> resultList = createSQLQuery(sb.toString());
        if (resultList != null && resultList.size() > 0){
        	Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                RelcampBalanceOfAccountVo rvo = new RelcampBalanceOfAccountVo();
                obj = resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    rvo.setRelcampId(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    rvo.setRelcampName(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    rvo.setSumTotalNum(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    rvo.setSumTotalAmont(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    rvo.setSumTaxCount(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    rvo.setSumRecAmont(obj[5].toString());
                list.add(rvo);
            }
        }
        return list;
    }

    /**
     * 供应商未对账单汇总 预加载
     */
    public Json loadNoPaidStVendorAccountMain(String relcampId,int enterpriseId) throws Exception{
    	Json json=new Json();
        List<RelcampBalanceOfAccountVo> list = new ArrayList<RelcampBalanceOfAccountVo>();
        StringBuffer sb = new StringBuffer("SELECT st_goods_storage.RELCAMP_ID "+
            ",bas_relation_campany.RELCAMP_NAME,SUM(st_goods_storage.TOTAL_NUM) AS '总数量'"+
            ",SUM(st_goods_storage.TOTAL_AMOUNT) AS '总金额',SUM(st_goods_storage.TAXCOUNT) AS '总成本额'"+
            ",SUM(st_goods_storage.TAXCOUNT) AS '应付金额',st_goods_storage.STORAGE_ID"+
            " FROM st_goods_storage,bas_relation_campany"+
            " WHERE st_goods_storage.RELCAMP_ID=bas_relation_campany.RELCAMP_ID" +
            " and st_goods_storage.enterprise_id="+enterpriseId+
            " AND st_goods_storage.STORAGE_ID NOT IN (SELECT st_vendor_account.RECEIPT_ID "+
            " FROM st_vendor_account)");
        if (relcampId != null && !relcampId.equals(""))
        	sb.append(" AND st_goods_storage.RELCAMP_ID=" + relcampId);
        sb.append(" GROUP BY st_goods_storage.RELCAMP_ID");
        List<Object[]> resultList = createSQLQuery(sb.toString());
        int count =getSQLCount(sb.toString(), null);
        if (resultList != null && resultList.size() > 0){
        	Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                RelcampBalanceOfAccountVo rvo = new RelcampBalanceOfAccountVo();
                obj = resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    rvo.setRelcampId(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    rvo.setRelcampName(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    rvo.setSumTotalNum(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    rvo.setSumTotalAmont(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    rvo.setSumTaxCount(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    rvo.setSumRecAmont(obj[5].toString());
                list.add(rvo);
            }
        }
        json.setRows(list);
        json.setTotal(count);
        return json;
    }
}
