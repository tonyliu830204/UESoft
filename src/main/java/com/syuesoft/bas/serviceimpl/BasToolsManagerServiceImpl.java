package com.syuesoft.bas.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.syuesoft.bas.dao.BasToolsManagerDAO;
import com.syuesoft.bas.dao.DefaultDAO;
import com.syuesoft.bas.service.BasToolsManagerService;
import com.syuesoft.base.vo.ToolsManagerVo;
import com.syuesoft.model.BasRelationCampany;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasToolsManager;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Json;

/**
 * 工具管理service
 * 
 * @author Liujian
 * 
 */
@Transactional
@Service("basToolsManagerService")
public class BasToolsManagerServiceImpl implements BasToolsManagerService
{

    @Autowired
    private BasToolsManagerDAO basToolsManagerDAO;

    @Autowired
    private DefaultDAO defaultDao;

    
    public void save(ToolsManagerVo tmVo) throws Exception
    {

        BasToolsManager btm = new BasToolsManager();

        btm.setBuyer(tmVo.getBuyer());
        btm.setLinkman(tmVo.getLinkman());
        btm.setProcurementPrice(tmVo.getProcurementPrice());
        btm.setRecordDate(tmVo.getRecordDate());
        btm.setSupplier(tmVo.getSupplier());
        btm.setTelphone(tmVo.getTelphone());
        btm.setToolsId(tmVo.getToolsId());
        btm.setToolsName(tmVo.getToolsName());
        btm.setToolsState(tmVo.getToolsState());
        btm.setToolsType(tmVo.getToolsType());
        btm.setToolsUnit(tmVo.getToolsUnit());

        basToolsManagerDAO.save(btm);

    }

    
    public void delete(Short id)
    {
        basToolsManagerDAO.delete(id);
    }

    
    public void update(ToolsManagerVo tmVo)
    {

        BasToolsManager btm = new BasToolsManager();

        btm.setBuyer(tmVo.getBuyer());
        btm.setLinkman(tmVo.getLinkman());
        btm.setProcurementPrice(tmVo.getProcurementPrice());
        btm.setRecordDate(tmVo.getRecordDate());
        btm.setSupplier(tmVo.getSupplier());
        btm.setTelphone(tmVo.getTelphone());
        btm.setToolsId(tmVo.getToolsId());
        btm.setToolsName(tmVo.getToolsName());
        btm.setToolsState(tmVo.getToolsState());
        btm.setToolsType(tmVo.getToolsType());
        btm.setToolsUnit(tmVo.getToolsUnit());

        basToolsManagerDAO.update(btm);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Json findAll(ToolsManagerVo tmVo)
    {

        String param = "";

        if (tmVo.getToolsId() != null && !"".equals(tmVo.getToolsId()))
        {
            param += "and btm.toolsId = '" + tmVo.getToolsId() + "'";
        }
        if (tmVo.getToolsName() != null
                && !"".equals(tmVo.getToolsName().trim()))
        {
            param += "and btm.toolsName like '%" + StringEscapeUtils.escapeSql(tmVo.getToolsName().trim()) + "%'";
        }
        if (tmVo.getToolsState() != null && !"".equals(tmVo.getToolsState()))
        {
            param += "and btm.toolsState = '" + tmVo.getToolsState() + "'";
        }
        if (tmVo.getSort() != null && !"".equals(tmVo.getSort())
                && tmVo.getOrder() != null && !"".equals(tmVo.getOrder()))
        {
            param += " order by btm." + tmVo.getSort() + " " + tmVo.getOrder();
        }

        Json json = new Json();
        json.setRows(basToolsManagerDAO.findAll(param, tmVo.getPage(), tmVo
                .getRows()));
        json.setTotal(basToolsManagerDAO.findAll(param).size());

        return json;

    };

    /**
     * 查询供应商
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @SuppressWarnings("unchecked")
    
    public List findAllCampany()
    {

        List list = new ArrayList();

        for (BasRelationCampany brc : (List<BasRelationCampany>) defaultDao
                .getObjList("from BasRelationCampany"))
        {
            ComboBoxJson json = new ComboBoxJson();
            json.setId(brc.getRelcampId().toString());
            json.setText(brc.getRelcampName());
            list.add(json);
        }

        return list;
    }

    /**
     * 查找采购员
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @SuppressWarnings("unchecked")
    
    public List findAllStuff()
    {

        List list = new ArrayList();

        for (BasStuff bs : (List<BasStuff>) defaultDao
                .getObjList("from BasStuff"))
        {
            ComboBoxJson json = new ComboBoxJson();

            json.setId(bs.getStfId().toString());
            json.setText(bs.getStfName());

            list.add(json);
        }

        return list;
    }

}
