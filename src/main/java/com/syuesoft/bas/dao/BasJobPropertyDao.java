package com.syuesoft.bas.dao;

import java.util.List;
import com.syuesoft.fbk.vo.BasJobPropertyVO;
import com.syuesoft.model.BasDept;
import com.syuesoft.model.BasJobProperty;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.EnterpriseInfo;

/**
 * 工作属性Dao
 * */
public interface BasJobPropertyDao extends BaseDaoI<BasJobProperty>
{
	/**
	 * 工作属性新增
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return boolean true为新增完成，false为此工作属性已存在，新增失败
	 * */
    public boolean add(BasJobPropertyVO bjobPropertyVO) throws Exception;
    /**
	 * 工作属性修改
	 * @param bjobProperty 工作属性视图对象
	 * @exception Exception 总异常
	 * @return boolean true为修改完成，false为此工作属性已存在，修改失败
	 * */
    public boolean basJobPropertyUpdate(BasJobProperty bjobProperty) throws Exception;
    /**
	 * 获取所有的工作属性对象
	 * @exception Exception 总异常
	 * @return List<BasJobProperty> 返回包含工作属性实体的集合
	 * */
    public List<BasJobProperty> findAll() throws Exception;
    /**
	 * 获取指定的工作属性对象
	 * @param jobProId 工作属性序号
	 * @exception Exception 总异常
	 * @return BasJobProperty 返回工作属性实体
	 * */
    public BasJobProperty getBasJobProperty(Short jobProId)throws Exception;
    /**
	 * 获取指定工作属性所属公司信息
	 * @param jobProId 工作属性序号
	 * @exception Exception 总异常
	 * @return EnterpriseInfo 返回公司实体
	 * */
    public EnterpriseInfo getBasJobPropertyOfEnterpriseInfoByJobProId(Short jobProId)throws Exception;
    /**
	 * 获取指定工作属性下的所有人员
	 * @param jobProId 工作属性序号
	 * @exception Exception 总异常
	 * @return List<BasStuff> 返回包含员工实体的集合
	 * */
    public List<BasStuff> findBasJobPropertyOfBasStuffsByJobProId(Short jobProId)throws Exception;
    /**
	 * 获取指定工作属性下的指定人员的部门信息
	 * @param jobProId 工作属性序号
	 * @param stfId 员工序号
	 * @exception Exception 总异常
	 * @return BasDept 返回员工实体
	 * */
    public BasDept findBasJobPropertyOfBasDeptByJobProId(Short jobProId,Long stfId)throws Exception;
}
