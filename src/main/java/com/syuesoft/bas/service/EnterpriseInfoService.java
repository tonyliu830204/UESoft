package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.EnterpriseInfoVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.util.ComboTreeJson;
import com.syuesoft.util.Msg;

public interface EnterpriseInfoService {
	/**
	 * @Title: modifyImportPartsArchives 
     * @Description: TODO(导入企业信息) 
     * @param @param enterpriseInfoVo
     * @param @return
     * @param @throws Exception    设定文件 
     * @return Msg    返回类型 
     * @throws 
	 * */
	public Msg modifyImportEnterpriseInfo(EnterpriseInfoVo enterpriseInfoVo, BasUsers user)throws Exception;
    /**
    * @Title: getPager 
    * @Description: TODO(查询企业信息) 
    * @param @param enterpriseInfoVo
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Datagrid    返回类型 
    * @throws
     */
	public Datagrid getPager(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
	/** 
    *
    * @Title: getEnterpriseWork 
    * @Description: TODO(查询业务企业信息) 
    * @param @param enterpriseInfoVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Object getEnterpriseWork(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
    /**
    * @Title: addEnterprise 
    * @Description: TODO(保存企业信息) 
    * @param @param enterpriseInfoVo
    * @param @throws Exception    设定文件 
    * @return void    返回类型 
    * @throws
     */
	public void addEnterprise(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
	/** 
    *
    * @Title: addEnterpriseWork 
    * @Description: TODO(保存企业业务信息) 
    * @param @param enterpriseInfoVo    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void addEnterpriseWork(EnterpriseInfoVo enterpriseInfoVo)throws Exception;
    /**
    * @Title: deleteEnterprise 
    * @Description: TODO(删除企业信息) 
    * @param @param enterpriseInfoVo
    * @param @throws Exception    设定文件 
    * @return boolean    返回类型 
    * @throws
     */
	public boolean deleteEnterprise(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
    /**
    * @Title: updateEnterprise 
    * @Description: TODO(更新企业信息) 
    * @param @param enterpriseInfoVo
    * @param @throws Exception    设定文件 
    * @return void    返回类型 
    * @throws
     */
	public void updateEnterprise(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
    /** 
    * @Title: findPEnterprise 
    * @Description: TODO(查询父企业下拉框) 
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public List<ComboTreeJson> findPEnterprise(EnterpriseInfoVo enterpriseInfoVo, BasUsers user) throws Exception;
    /** 
    *
    * @Title: deleteEnterpriseWork 
    * @Description: TODO(删除企业业务信息) 
    * @param @param enterpriseInfoVo    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void deleteEnterpriseWork(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
    /** 
    *
    * @Title: updateEnterpriseWork 
    * @Description: TODO(修改企业业务信息) 
    * @param @param enterpriseInfoVo    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void updateEnterpriseWork(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
    /**
     * @Title: enterpriseIsTwoLevel 
     * @Description: TODO(校验企业结构)
     * @return boolean     
     * @throws  Exception
     * */
    public boolean enterpriseIsTwoLevel() throws Exception;
    /**
     * 查询指定企业下的所有子企业
     * @param enterpriseId 指定企业的序号
     * @exception Exception 总异常
     * @return List<EnterpriseInfo> 返回包含企业信息对象的集合
     * */
    public List<EnterpriseInfo> findEnterpriseInfoChild(String enterpriseId)throws Exception;
}