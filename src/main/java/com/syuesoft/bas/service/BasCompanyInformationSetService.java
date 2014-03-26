package com.syuesoft.bas.service;

import com.syuesoft.base.vo.BasCompanyInformationSetVo;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.util.Json;

public interface BasCompanyInformationSetService
{

    /**
     * 保存系统参数
     * 
     * @param cis
     * @throws Exception
     */
    public void save(BasCompanyInformationSetVo cis) throws Exception;

    /**
     * 删除系统参数
     * 
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception;

    /**
     * 根系系统参数
     * 
     * @param cis
     * @throws Exception
     */
    public void update(BasCompanyInformationSetVo cis) throws Exception;

    /**
     * 保存更新系统参数
     * 
     * @param cis
     * @throws Exception
     */
    public void saveOrUpdate(BasCompanyInformationSetVo cis) throws Exception;

    /**
     * 查询公司信息参数
     * 
     * @param cis
     * @return
     * @throws Exception
     */
    public Json findCompanyAll(BasCompanyInformationSetVo cis) throws Exception;

    /**
     * 查询所有系统参数
     * 
     * @param cis
     * @return
     * @throws Exception
     */
    public Json findParameterAll(BasCompanyInformationSetVo cis)
            throws Exception;

    /**
     * 查询系统参数一
     * 
     * @param cis
     * @return
     * @throws Exception
     */
    public Json searchParameterOne(BasCompanyInformationSetVo cis)
            throws Exception;

    public BasCompanyInformationSet getBasCompanyInformationSet(String type,String name);
    
    /**
     * 查询参数
     * 
     * @param type
     *            参数所属类型
     * @param name
     *            参数
     * @return
     */
    public BasCompanyInformationSet getBasCompanyInformationSet(String type,
            String name,int exterpriseId);
}