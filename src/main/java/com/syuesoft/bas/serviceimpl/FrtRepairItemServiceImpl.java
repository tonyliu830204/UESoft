package com.syuesoft.bas.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.components.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.bas.dao.FrtRepairItemDao;
import com.syuesoft.bas.service.FrtRepairItemService;
import com.syuesoft.base.vo.FrtRepairItemVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasCarType;
import com.syuesoft.model.BasWorkhourSort;
import com.syuesoft.model.FrtRepairItem;
import com.syuesoft.st.dao.BasCarTypeDAO;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Msg;
import com.syuesoft.util.MyBeanUtils;

@Service("frtRepairItemService")
public class FrtRepairItemServiceImpl implements FrtRepairItemService
{

    @Autowired
    private FrtRepairItemDao frtRepairItemDao;

    @Autowired
    private BasCarTypeDAO basCarTypeDAO;

    // 获取工时分类用于combox
    
    public List<ComboBoxJson> getBasWorkhourSort(String q,Integer id) throws Exception{
        return frtRepairItemDao.getBasWorkhourSort(q,id);
    }

    
    public Datagrid findAll(com.syuesoft.base.vo.FrtRepairItemVo friVo)
            throws Exception
    {
        StringBuffer sb = new StringBuffer(
                "SELECT fri.REPITEM_ID,fri.REPITEM_NAME,fri.REPITEM_CODE,");
        sb.append(" fri.REPITEM_SERIES,bws.WH_SORT_NAME,fri.REPITEM_AMOUNT, ");
        sb.append(" fri.REPITEM_TIME,fri.INTERNAL_TIME,fri.CLAIM_TIME, ");
        sb.append(" fri.FIT_CAR,fri.REPITEM_REMARK");
        sb.append(" FROM frt_repair_item fri,bas_workhour_sort bws");
        sb.append(" WHERE fri.REPITEM_SERIES=bws.WH_SORT_ID" +
        		  " AND fri.enterprise_id="+friVo.getEnterpriseId());
        if (friVo.getRepitemId() != null
                && friVo.getRepitemId().toString().length() > 0)
        {
            sb.append(" AND fri.REPITEM_ID LIKE '%"
                    + StringEscapeUtils.escapeSql(friVo.getRepitemId()
                            .toString().trim()) + "%'");
        }
        if (friVo.getRepitemName() != null
                && friVo.getRepitemName().toString().length() > 0)
        {
            sb.append(" AND fri.REPITEM_NAME LIKE '%"
                    + StringEscapeUtils.escapeSql(friVo.getRepitemName().trim())
                    + "%'");
        }
        if (friVo.getRepitemSeries() != null
                && friVo.getRepitemSeries().toString().length() > 0)
        {
            sb.append(" AND fri.repitemSeries=" + friVo.getRepitemSeries());
        }
        if (friVo.getRepitemCode() != null
                && friVo.getRepitemCode().toString().length() > 0)
        {
            sb.append(" AND fri.REPITEM_CODE like '%"
                    + StringEscapeUtils.escapeSql(friVo.getRepitemCode().trim())
                    + "%'");
        }
        if (friVo.getFitCar() != null && friVo.getFitCar().length() > 0)
        {
            sb.append(" AND fri.REPITEM_ID IN (");
            sb.append(" SELECT DISTINCT a.REPITEM_ID FROM frt_repair_item a");
            String[] temp = friVo.getFitCar().split(",");
            if (temp != null)
            {
                sb.append(" WHERE a.FIT_CAR LIKE '%" + StringEscapeUtils.escapeSql(temp[0].trim()) + "%' ");
                for (int i = 1; i < temp.length; i++)
                {
                    sb.append(" OR a.FIT_CAR LIKE '%" + StringEscapeUtils.escapeSql(temp[i].trim()) + "%'");
                }
            }
            sb.append(" )");
        }
        List<FrtRepairItemVo> rows = new ArrayList<FrtRepairItemVo>();
        List<Object[]> rowsList = frtRepairItemDao.createSQLQuery(
                sb.toString(), null, friVo.getPage(), friVo.getRows());
        FrtRepairItemVo fri = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] obj : rowsList)
            {
                fri = new FrtRepairItemVo();
                if (obj[0] != null && obj[0].toString().length() > 0)
                    fri.setRepitemId(Integer.parseInt(obj[0].toString()));
                if (obj[1] != null && obj[1].toString().length() > 0)
                    fri.setRepitemName(obj[1].toString());
                if (obj[2] != null && obj[2].toString().length() > 0)
                    fri.setRepitemCode(obj[2].toString());
                if (obj[3] != null && obj[3].toString().length() > 0)
                    fri.setRepitemSeries(Short.parseShort(obj[3].toString()));
                if (obj[4] != null && obj[4].toString().length() > 0)
                    fri.setRepitemSeriesName(obj[4].toString());
                if (obj[5] != null && obj[5].toString().length() > 0)
                    fri.setRepitemAmount(Double.parseDouble(obj[5].toString()));
                if (obj[6] != null && obj[6].toString().length() > 0)
                    fri.setRepitemTime(Double.parseDouble(obj[6].toString()));
                if (obj[7] != null && obj[7].toString().length() > 0)
                    fri.setInternalTime(Double.parseDouble(obj[7].toString()));
                if (obj[8] != null && obj[8].toString().length() > 0)
                    fri.setClaimTime(Double.parseDouble(obj[8].toString()));
                if (obj[9] != null && obj[9].toString().length() > 0)
                    fri.setFitCar(obj[9].toString());
                if (obj[10] != null && obj[10].toString().length() > 0)
                    fri.setRepitemRemark(obj[10].toString());
                if (fri.getFitCar() == null)
                {
                    fri.setFitCarName(null);
                }
                else
                {
                    fri.setFitCar(fri.getFitCar().replaceAll(" ", ""));
                    String[] applicableBrands = fri.getFitCar().split(",");
                    StringBuffer sbr = new StringBuffer();
                    if (applicableBrands != null && applicableBrands.length > 0)
                    {
                        List<BasCarType> bctList = basCarTypeDAO
                                .find("select bct from BasCarType bct where bct.ctypeId in("
                                        + fri.getFitCar().trim() + ")");
                        if (bctList != null && bctList.size() > 0)
                            for (BasCarType basCarType : bctList)
                            {
                                sbr.append(basCarType.getCtypeName() + ",");
                            }
                        if (sbr.length() > 0)
                        {
                            fri.setFitCarName(sbr
                                    .substring(0, sbr.length() - 1));
                        }
                        else
                        {
                            fri.setFitCarName(null);
                        }
                    }
                    else
                    {
                        fri.setFitCarName(null);
                    }
                }
                rows.add(fri);
            }
        int total = frtRepairItemDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 增加标准项目工时
     * */
    
    public Msg save(com.syuesoft.base.vo.FrtRepairItemVo friVo)
            throws Exception
    {
        Msg msg = new Msg();
        try{
            FrtRepairItem ftm = new FrtRepairItem();
            List<com.syuesoft.base.vo.FrtRepairItemVo> updateList = JSON
                    .parseArray(friVo.getUpdateData(),
                            com.syuesoft.base.vo.FrtRepairItemVo.class);
            if (updateList.size() > 0){
                MyBeanUtils.getInstance().copyBeans(updateList.get(0), ftm);
                ftm.setEnterpriseId(friVo.getEnterpriseId());
                frtRepairItemDao.save(ftm);
                msg.setMsg("增加标准项目工时成功！");
                msg.setSuccess(true);
            }
            else
                msg.setMsg("增加标准项目工时失败！");
        }
        catch(Exception e)
        {
            msg.setMsg("增加标准项目工时失败！");
        }
        return msg;
    }

    /**
     * 更新标准项目工时
     * */
    
    public Msg update(com.syuesoft.base.vo.FrtRepairItemVo friVo)
            throws Exception
    {
        Msg msg = new Msg();
        try
        {
            FrtRepairItem ftm = new FrtRepairItem();
            List<com.syuesoft.base.vo.FrtRepairItemVo> updateList = JSON
                    .parseArray(friVo.getUpdateData(),
                            com.syuesoft.base.vo.FrtRepairItemVo.class);
            if (updateList.size() > 0)
            {
                MyBeanUtils.getInstance().copyBeans(updateList.get(0), ftm);
                ftm.setEnterpriseId(friVo.getEnterpriseId());
                frtRepairItemDao.merge(ftm);
                msg.setMsg("修改标准项目工时成功！");
                msg.setSuccess(true);
            }
            else
            {
                msg.setMsg("修改标准项目工时失败！");
            }
        }
        catch(Exception e)
        {
            msg.setMsg("修改标准项目工时失败！");
        }
        return msg;
    }

    /**
     * 删除标准项目工时
     * */
    
    public Msg delete(com.syuesoft.base.vo.FrtRepairItemVo friVo)
            throws Exception
    {
        Msg msg = new Msg();
        try
        {
            frtRepairItemDao.delete(frtRepairItemDao.get(FrtRepairItem.class,
                    friVo.getRepitemId()));
            msg.setMsg("删除标准项目工时成功！");
            msg.setSuccess(true);
        }
        catch(Exception e)
        {
            msg.setMsg("删除标准项目工时失败！");
        }
        return msg;
    }

}
