package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasStuffVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.ComboTreeJson;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 人事资料设定Service
 * */
public interface BasPersonnelInformationService
{

    /**
     * 分页 展现员工List 携带查询
     * @param basStuffVo 员工视图对象
     * @param user 登陆用户对象
     * @exception Exception 总异常
     * @return Json 返回数据表格对象
     * */
    public Json findByParam(BasStuffVo basStuffVo, BasUsers user)throws Exception;

    /**
     * 查询所有员工
     * @exception Exception 总异常
     * @return List<BasStuff> 返回包含员工实体的集合
     * */
    public List<BasStuff> getBasStuff() throws Exception;
    
    /**
     * 校验员工编号
     * @param stuffVo 员工视图对象
     * @exception Exception  总异常
     * @return boolean  true为已存在，false为不存在
     * */
    public boolean findStfYid(BasStuffVo stuffVo) throws Exception;
    
    /**
     * 校验员工姓名
     * @param stuffVo 员工视图对象
     * @exception Exception  总异常
     * @return boolean  true为已存在，false为不存在
     * */
    public boolean findUserName(BasStuffVo stuffVo, BasUsers user) throws Exception;
    
    /**
     * 变更用户
     * @param basStuffVo 员工视图对象
     * @exception Exception  总异常
     * @return Msg 返回消息对象
     * */
    public Msg changeUserStfYid(BasStuffVo basStuffVo) throws Exception;

    /**
     * 获取员工持有的属性
     * @param basStuffVo 员工视图对象
     * @exception Exception 总异常
     * @return List 返回包含员工属性Id的集合
     * */
    public List selectJobProId(BasStuffVo basStuffVo) throws Exception;
    
    /**
     * 人事资料新增
     * @param stuffVo 员工视图对象
     * @param user 登陆用户对象
     * @exception Exception 总异常
     * */
    public void save(BasStuffVo stuffVo, BasUsers user) throws Exception;
    
    /**
     * 人事资料删除
     * @param stuffVo 员工视图对象
     * @exception  Exception 总异常
     * @return  boolean true为删除成功，false为员工序号不完整，删除失败
     * */
    public boolean delete(BasStuffVo stuffVo) throws Exception;
    
    /**
     * 人事资料修改
     * @param stuffVo 员工视图对象
     * @param user 登陆用户对象
     * @exception Exception 总异常
     * */
    public void update(BasStuffVo stuffVo, BasUsers user) throws Exception;

    /**
     * 查询指定公司的部门
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return List<ComboBoxJson> 返回包含部门Id和Name的ComboBoxJson对象集合
     * */
    public List<ComboBoxJson> findAllDept(BasStuffVo stuffVo) throws Exception;
    
    /**
     * 查询集团公司及其子公司
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return List<ComboTreeJson> 返回包含企业Id和Name的ComboTreeJson对象集合
     * */
    public List<ComboTreeJson> findAllCompany(BasStuffVo stuffVo) throws Exception;

    /**
     * 查询工作属性
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return List<ComboBoxJson> 返回包含工作属性Id和Name的ComboBoxJson对象集合
     * */
    public List<ComboBoxJson> findAllBasJobProperty(BasStuffVo stuffVo) throws Exception;

    /**
     * 查询车间部门
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return List<ComboBoxJson> 返回包含车间部门Id和Name的ComboBoxJson对象集合 
     * */
    public List<ComboBoxJson> findAllCJDept(BasStuffVo stuffVo) throws Exception;

    /**
     * 查询仓别分类
     * @exception Exception 总异常
     * @return List<ComboBoxJson> 返回包含仓别分类Id和Name的ComboBoxJson对象集合
     * */
    public List<ComboBoxJson> findAllCBFL(String enterpriseId) throws Exception;
    
    /**
     * 注销指定用户
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * */
    public void updateBasStuff(BasStuffVo stuffVo) throws Exception;
    
    /**
     * 查询指定登陆用户
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return BasStuffVo 返回员工视图对象
     * */
    public BasStuffVo findBasStuffByYid(BasStuffVo stuffVo) throws Exception;
    
    /**
     * 默认打印
     * @param stuffVo 员工视图对象
     * @param user 登陆用户对象
     * @exception Exception 总异常
     * @return String 返回打印页面源代码
     * */
    public String getPrintHtml(BasStuffVo stuffVo, BasUsers user)
            throws Exception;

    /**
     * 用户角色查询
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return Datagrid 返回数据表格对象
     * */
    public Datagrid findUserRole(BasStuffVo stuffVo) throws Exception;
    
    /**
     * 人事资料设定Datagrid
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return Datagrid 返回数据表格对象
     * */
    public Datagrid basPersonnelInformationDatagrid(BasStuffVo stuffVo) throws Exception;
    
    /**
     * 设置集团管理员
     * @param stuffVo 员工视图对象
     * @exception 总异常
     * @return boolean true为设置成功，false为设置失败
     * */
    public boolean modifyCombinePerson(BasStuffVo stuffVo)throws Exception;
    
    /**
     * 系统用户中集团管理员是否已存在
     * @exception Exception 总异常
     * @return boolean true为已存在，false为不存在
     * */
    public boolean isExistsCombineAdmin()throws Exception;
}