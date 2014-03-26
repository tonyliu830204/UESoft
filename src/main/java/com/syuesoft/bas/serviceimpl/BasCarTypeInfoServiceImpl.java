package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCarBrandDAO;
import com.syuesoft.bas.dao.BaseCarTypeDAO;
import com.syuesoft.bas.dao.DefaultDAO;
import com.syuesoft.bas.service.BasCarTypeInfoService;
import com.syuesoft.base.vo.BasCarTypeInfoVo;
import com.syuesoft.model.BasCarBrand;
import com.syuesoft.model.BasCarType;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Json;

/**
 * 车型号信息service
 * @author Liujian
 */
@Transactional
@Service("basCarTypeInfoService")
public class BasCarTypeInfoServiceImpl extends BaseLogServiceImpl implements
        BasCarTypeInfoService
{
    @Autowired
    private DefaultDAO defaultDAO;
    @Autowired
    private BaseCarTypeDAO baseCarTypeDAO;
    @Autowired
    private BasCarBrandDAO basCarBrandDAO;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    
    public Json findAll(BasCarTypeInfoVo bctiVo)
    {
        Json json = new Json();
        String param = "";
        if (bctiVo.getCtypeId() != null && !"".equals(bctiVo.getCtypeId()))
        {
            param += " and ct.ctypeId like '%" + StringEscapeUtils.escapeSql(bctiVo.getCtypeId().toString().trim()) + "%'";
        }
        if (bctiVo.getCbrdId() != null && !"".equals(bctiVo.getCbrdId()))
        {
            param += " and cb.cbrdId = '" + bctiVo.getCbrdId() + "'";
        }
        if (bctiVo.getCtypeName() != null && !"".equals(bctiVo.getCtypeName()))
        {
            param += " and ct.ctypeName like '%" + StringEscapeUtils.escapeSql(bctiVo.getCtypeName().trim()) + "%'";
        }
        param += " and ct.enterpriseId="+bctiVo.getEnterpriseId();
        if (bctiVo.getSort() != null && !"".equals(bctiVo.getSort())
                && bctiVo.getOrder() != null && !"".equals(bctiVo.getOrder()))
        {
            String sort = "ct";
            if ("cbrdId".equals(bctiVo.getSort()))
            {
                sort = "ct.basCarBrand";
            }
            param += " order by " + sort + "." + bctiVo.getSort() + " "
                    + bctiVo.getOrder();
        }
        json.setTotal(baseCarTypeDAO.findAll(param).size());
        json.setRows(baseCarTypeDAO.findAll(param, bctiVo.getPage(), bctiVo
                .getRows()));
        return json;
    }

    public boolean isExist(BasCarTypeInfoVo bctiVo) throws Exception
    {
        String param = "FROM BasCarType  bct WHERE bct.enterpriseId="+bctiVo.getEnterpriseId();
        if (bctiVo.getCtypeId() != null && !"".equals(bctiVo.getCtypeId()))
        {
            param += " and bct.ctypeId!=" + bctiVo.getCtypeId();
        }
        if (bctiVo.getCtypeName() != null && !"".equals(bctiVo.getCtypeName()))
        {
            param += " and bct.ctypeName='" + bctiVo.getCtypeName() + "'";
        }
        if (bctiVo.getCbrdId() != null)
        {
            param += " and bct.basCarBrand.cbrdId=" + bctiVo.getCbrdId();
        }
        List<BasCarType> list = baseCarTypeDAO.find(param);
        if (list != null && list.size() > 0)
        {
            return true;
        }
        return false;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增车辆型号", content = "基础资料-->新增车辆型号")
    public void save(BasCarTypeInfoVo bctiVo) throws Exception
    {
        BasCarType ct = new BasCarType();
        ct.setCtypeName(bctiVo.getCtypeName());
        BasCarBrand cb = new BasCarBrand();
        cb.setCbrdId(bctiVo.getCbrdId());
        ct.setBasCarBrand(cb);
        ct.setCtypePrice(bctiVo.getCtypePrice());
        ct.setEnterpriseId(bctiVo.getEnterpriseId());
        Serializable dd = baseCarTypeDAO.save(ct);
        setContent("基础资料-->新增车辆型号 ,车辆型号编号:" + dd);
    }

    
    @Log(moduleName = "基础资料", opertype = "修改车辆型号", content = "基础资料-->修改车辆型号")
    public void update(BasCarTypeInfoVo bctiVo) throws Exception
    {
        BasCarType ct = baseCarTypeDAO.get(BasCarType.class, bctiVo.getCtypeId());
        if(ct!=null){
        	 ct.setCtypeName(bctiVo.getCtypeName());
             ct.setEnterpriseId(bctiVo.getEnterpriseId());
             BasCarBrand cb = basCarBrandDAO.get(BasCarBrand.class, bctiVo.getCbrdId());
             if(cb!=null)
                 ct.setBasCarBrand(cb);
             ct.setCtypePrice(bctiVo.getCtypePrice());
             ct.setEnterpriseId(bctiVo.getEnterpriseId());
             baseCarTypeDAO.update(ct);
             setContent("基础资料-->修改车辆型号 ,车辆型号编号:" + bctiVo.getCbrdId());
        }
    }

    
    @Log(moduleName = "基础资料", opertype = "删除车辆型号", content = "基础资料-->删除车辆型号")
    public void delete(Short id) throws Exception
    {
    	BasCarType ct = baseCarTypeDAO.get(BasCarType.class, id);
    	if(ct!=null){
    		 baseCarTypeDAO.delete(id);
    	     setContent("基础资料-->删除车辆型号 ,车辆型号编号:" + id);
    	}
    }
    
    /**
     * 查询车辆品牌
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @SuppressWarnings("unchecked")
    public List findAllCarBrand(BasCarTypeInfoVo bctiVo)
    {
        List list = new ArrayList();
        String param = "";
        if (bctiVo.getQ() != null && !"".equals(bctiVo.getQ().trim()))
            param += " and bcb.cbrdName like '%" + StringEscapeUtils.escapeSql(bctiVo.getQ().trim()) + "%'";
        if (bctiVo.getCtypeId() != null&& !"".equals(bctiVo.getCtypeId().toString().trim()))
            param += " and bcb.cbrdId = " + bctiVo.getCtypeId();
        List<BasCarBrand> bcbList = defaultDAO
                .getObjList("from BasCarBrand bcb where bcb.enterpriseId="+bctiVo.getEnterpriseId()+" " + param);
        if (bcbList != null && bcbList.size() > 0)
        {
            for (BasCarBrand bcb : bcbList)
            {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(bcb.getCbrdId().toString());
                json.setText(bcb.getCbrdName());
                list.add(json);
            }
        }
        return list;
    }
    
    /**
     * 获取具体车辆品牌信息
     * */
    @SuppressWarnings("unchecked")
    public List getCarTypeInfo(BasCarTypeInfoVo bctiVo) throws Exception
    {
        List list = new ArrayList();
        String param = "";
        if (bctiVo.getCbrdId() != null && !"".equals(bctiVo.getCbrdId().toString().trim()))
            param = " and bcb.basCarBrand.cbrdId =" + bctiVo.getCbrdId() ;
        List<BasCarType> bcbList = defaultDAO.getObjList("from BasCarType bcb where bcb.enterpriseId="+bctiVo.getEnterpriseId()+" " + param);
        if (bcbList != null && bcbList.size() > 0){
            for (BasCarType bcb : bcbList){
                ComboBoxJson json = new ComboBoxJson();
                json.setId(bcb.getCtypeId().toString());
                json.setText(bcb.getCtypeName());
                list.add(json);
            }
        }
        return list;
    }

}
