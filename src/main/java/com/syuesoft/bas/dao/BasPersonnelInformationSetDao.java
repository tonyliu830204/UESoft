package com.syuesoft.bas.dao;

import java.io.Serializable;
import java.util.List;

import com.syuesoft.base.vo.BasStuffVo;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Json;
/**
 * 人事资料设定 Dao
 * */
public interface BasPersonnelInformationSetDao extends BaseDaoI<BasStuff>
{
    /*
     * 分页 展现员工List 携带查询
     */
    public Json findByParam(BasStuffVo basStuffVo, BasUsers user)throws Exception;

    /*
     * 3> 获取 员工 List
     */
    public List<BasStuff> getBasStuff();

    public Object getObject(Class c, Serializable id);

    public BasStuff getObjectBystfYid(BasStuffVo stuffVo);

    public List findStfYid(BasStuffVo stuffVo);
    
    public List findUserName(BasStuffVo stuffVo, BasUsers user);

    public void changeUserStfYid(BasStuff basStuff);

    /*
     * 5> 查询员工所拥有的实体
     */
    public List selectJobProId(BasStuffVo basStuffVo);
    /**
     * 新增人事资料
     */
    public void save(BasStuff basStuff, BasStuffVo stuffVo, BasUsers user)throws Exception;
    /**修改人事资料*/
    public void Update(BasStuff stuff, BasStuffVo stuffVo)throws Exception;;
    /**删除人事资料*/
    public void Delete(String id);
    /*
     * 注销某个员工
     */
    public void updateBasStuff(BasStuffVo stuffVo)  throws Exception;

    public BasUsers findBasStuffByYid(BasStuffVo stuffVo);
}