package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.base.vo.BasCompanyInformationSetVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.util.Json;

/**
 * 基础资料->公司信息设定Service
 * @author Liujian
 */
@Transactional
@Service("basCompanyInformationSetService")
public class BasCompanyInformationSetServiceImpl extends BaseLogServiceImpl
        implements BasCompanyInformationSetService
{
    @Autowired
    private BasCompanyInformationSetDAO basCompanyInformationSetDAO;

    
    @Log(moduleName = "基础资料", opertype = "新增公司信息设定", content = "基础资料-->新增公司信息设定")
    public void save(BasCompanyInformationSetVo cisVo) throws Exception
    {
        BasCompanyInformationSet cis = new BasCompanyInformationSet();
        BeanUtils.copyProperties(cisVo, cis);
        Serializable bb = basCompanyInformationSetDAO.save(cis);
        setContent("基础资料-->新增公司信息设定 ,公司信息设定编号:" + bb);
    }

    
    @Log(moduleName = "基础资料", opertype = "更新公司信息设定", content = "基础资料-->更新公司信息设定")
    public void update(BasCompanyInformationSetVo cisVo)throws Exception
    {
    	String hql=" from BasCompanyInformationSet cis where cis.ciId='"+cisVo.getCiId()+"' and cis.enterpriseId="+cisVo.getEnterpriseId();
        BasCompanyInformationSet cis =basCompanyInformationSetDAO.get(hql);
        if(cis!=null){
        	if(cisVo.getCiValue()!=null&&!cisVo.getCiValue().equals(""))
        	  cis.setCiValue(cisVo.getCiValue());
        	if(cisVo.getCiId()!=null&&!cisVo.getCiId().equals(""))
        	  cis.setCiId(cisVo.getCiId());
        	if(cisVo.getCiType()!=null&&!cisVo.getCiType().equals(""))
        	  cis.setCiType(cisVo.getCiType());
            basCompanyInformationSetDAO.merge(cis);
            setContent("基础资料-->更新公司信息设定 ,公司信息设定编号:" + cis.getCiId());
        }
    }

    @Log(moduleName = "基础资料", opertype = "保存、更新公司信息设定", content = "基础资料-->保存、更新公司信息设定")
    public void saveOrUpdate(BasCompanyInformationSetVo cisVo)
    {
        List<BasCompanyInformationSet> cisVos = new ArrayList<BasCompanyInformationSet>();
        String[] checkCiIds = cisVo.getCiCheckCiIds();
        String[] checkNames = cisVo.getCiCheckNames();
        String[] checkValues = cisVo.getCiCheckValues();

        String[] ciNames = cisVo.getCiNames();
        String[] ciCiIds = cisVo.getCiCiIds();
        String[] ciValues = cisVo.getCiValues();

        if ((checkNames != null && checkNames.length > 0 )|| (checkValues != null && checkValues.length > 0)){
            List<String> list = null;
            if (checkValues != null && checkValues.length > 0){
                list = new ArrayList<String>();
                for (int j = 0; j < checkValues.length; j++){
                    list.add(checkValues[j]);
                }
            }
            for (int i = 0; i < checkNames.length; i++){
                BasCompanyInformationSet cis = new BasCompanyInformationSet();
                cis.setCiId(checkCiIds[i]);
                cis.setCiName(checkNames[i]);
                cis.setCiValue(list != null ? list.contains(checkNames[i]) ? "checked": null: null);
                cis.setEnterpriseId(cisVo.getEnterpriseId());
                cisVos.add(cis);
            }
        }
        if (ciNames != null && ciNames.length > 0 || ciCiIds != null && ciCiIds.length > 0 || ciValues != null && ciValues.length > 0)
        {
            for (int i = 0; i < ciNames.length; i++)
            {
                BasCompanyInformationSet cis = new BasCompanyInformationSet();
                cis.setCiId(ciCiIds[i]);
                cis.setCiName(ciNames[i]);
                cis.setCiValue(!"".equals(ciValues[i]) ? ciValues[i] : null);
                cis.setEnterpriseId(cisVo.getEnterpriseId());
                cisVos.add(cis);
            }
        }
        basCompanyInformationSetDAO.saveOrUpdate(cisVos);
        setContent("基础资料-->保存、更新公司信息设定 ,公司信息设定编号:" + cisVo.getCiId());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除公司信息设定", content = "基础资料-->删除公司信息设定")
    public void delete(String id)throws Exception
    {
    	BasCompanyInformationSet bci=basCompanyInformationSetDAO.get(BasCompanyInformationSet.class, id);
    	if(bci!=null){
    		basCompanyInformationSetDAO.delete(id);
    	    setContent("基础资料-->删除公司信息设定 ,公司信息设定编号:" + id);
    	}
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Json findCompanyAll(BasCompanyInformationSetVo cis)
    {
        Json json = new Json();
        String param = "";
        param += " and cis.enterpriseId="+cis.getEnterpriseId();
        if (cis.getCiType() != null && !"".equals(cis.getCiType())){
            param += "and cis.ciType = '" + cis.getCiType() + "' ";
        }else{
            param += " and cis.ciType in ('" + Contstants.COPANY_SET.COPANYINFO
            + "','" + Contstants.COPANY_SET.CMSPARAMETER + "','"
            + Contstants.COPANY_SET.CARCONTROL + "','"
            + Contstants.COPANY_SET.CUSTOMCONTROL + "','"
            + Contstants.COPANY_SET.TOCARCONTROL + "') ";
        }
        if (cis.getCiId() != null && !"".equals(cis.getCiId().trim()))
            param += "and cis.ciId = '" + cis.getCiId() + "' ";
        if (cis.getCiName() != null && !"".equals(cis.getCiName().trim()))
            param += "and cis.ciName like '%" + StringEscapeUtils.escapeSql(cis.getCiName().trim()) + "%' ";
        if (cis.getSort() != null && !"".equals(cis.getSort())
                && cis.getOrder() != null && !"".equals(cis.getOrder()))
            param += "order by " + cis.getSort() + " " + cis.getOrder();
        json.setTotal(basCompanyInformationSetDAO.findAll(param).size());
        json.setRows(basCompanyInformationSetDAO.findAll(param, cis.getPage(),cis.getRows()));
        return json;
    };

    public Json findParameterAll(BasCompanyInformationSetVo cis)
    {
        Json json = new Json();

        String param = "";
        param += " and cis.enterpriseId="+cis.getEnterpriseId();
        if (cis.getCiType() != null && !"".equals(cis.getCiType()))
            param += "and cis.ciType = '" + cis.getCiType() + "' ";
        else{
            param += " and cis.ciType in ('"
                    + Contstants.SYSTEMPARAMETER_SET.SYSTEMSAFE + "','"
                    + Contstants.SYSTEMPARAMETER_SET.VIPPARAMETER + "','"
                    + Contstants.SYSTEMPARAMETER_SET.STAGEPARAMETER + "','"
                    + Contstants.SYSTEMPARAMETER_SET.BALANCEPARAMETER + "','"
                    + Contstants.SYSTEMPARAMETER_SET.CLAIMPARAMETER + "','"
                    + Contstants.SYSTEMPARAMETER_SET.BESPOKEPRICE + "','"
                    + Contstants.SYSTEMPARAMETER_SET.WORKSHOPPARAMETER + "','"
                    + Contstants.SYSTEMPARAMETER_SET.CUSTOMINFO + "','"
                    + Contstants.SYSTEMPARAMETER_SET.CARINFO + "') ";
        }
        if (cis.getCiId() != null && !"".equals(cis.getCiId().trim()))
            param += "and cis.ciId = '" + cis.getCiId() + "' ";
        if (cis.getCiName() != null && !"".equals(cis.getCiName().trim()))
            param += "and cis.ciName like '%" + StringEscapeUtils.escapeSql(cis.getCiName().trim()) + "%' ";
        if (cis.getSort() != null && !"".equals(cis.getSort())
                && cis.getOrder() != null && !"".equals(cis.getOrder()))
            param += "order by " + cis.getSort() + " " + cis.getOrder();
        json.setTotal(basCompanyInformationSetDAO.findAll(param).size());
        json.setRows(basCompanyInformationSetDAO.findAll(param, cis.getPage(),
                cis.getRows()));
        return json;
    };

    public Json searchParameterOne(BasCompanyInformationSetVo cis)
    {
        Json json = new Json();
        String param = " and cis.ciType = '"+ cis.getCiType() + "' and cis.enterpriseId="+cis.getEnterpriseId();
        List<BasCompanyInformationSet> list = basCompanyInformationSetDAO
                .findAll(param);
        json.setTotal(list.size());
        json.setRows(list);
        return json;
    }

    public BasCompanyInformationSet getBasCompanyInformationSet(String type,String name){
        return basCompanyInformationSetDAO.getBasCompanyInformationSet(type,name);
    }
    
    
    public BasCompanyInformationSet getBasCompanyInformationSet(String type,String name,int exterpriseId){
        return basCompanyInformationSetDAO.getBasCompanyInformationSet(type,name,exterpriseId);
    }
}