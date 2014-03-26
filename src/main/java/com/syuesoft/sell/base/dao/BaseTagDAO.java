package com.syuesoft.sell.base.dao;

import java.util.List;

import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.base.vo.ParentdictionaryVo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsParentdictionary;
import com.syuesoft.util.Message;



public interface BaseTagDAO{
	public List<XsParentdictionary> findPkey(String pkey) throws Exception;
	public List<XsParentdictionary> findPkey(String pkey,Integer parentId) throws Exception;
	public List<XsChilddictionary> findCkey(String cKey,Integer parentId) throws Exception;
	public List<XsChilddictionary> findCkey(String cKey, Integer parentId, Integer childId,String cValue,Integer enid)throws Exception;
	// 添加BasParentdictionary
	public void addParentElement(XsParentdictionary xsParentdictionary) throws Exception;
	// 删除BasParentdictionary（删除其所有的子级数据字典）
	public void deleteParentElement(XsParentdictionary xsParentdictionary) throws Exception;
	// 根据父级数据字典编号查找所有的BasChilddictionary
	public List<XsChilddictionary> findAllChildrenElement(Integer parentId) throws Exception;
	// 修改BasParentdictionary
	public void updateParentElement(XsParentdictionary xsParentdictionary) throws Exception;
	// 根据创建者查找所有的BasParentdictionary
	public List<XsParentdictionary> findAllParentElement(final ParentdictionaryVo parent) throws Exception;
	public List<XsParentdictionary> findAllParentCount(ParentdictionaryVo parent) throws Exception;
	// 添加BasChilddictionary
	public void addChildrenElement(XsChilddictionary xsChilddictionary) throws Exception;
	// 删除BasChilddictionary
	public Message deleteChildrenElement(XsChilddictionary xsChilddictionary) throws Exception;
	// 修改BasChilddictionary
	public void updateChildrenElement(XsChilddictionary xsChilddictionary) throws Exception;
	// 根据父级数据字典编号查找所有的BasChilddictionary
	public List<XsChilddictionary> findAllChildrenElement(final String sql,final String sort,final String order,final int rows,final int page) throws Exception;
	// 根据条件查找BasChilddictionary
	public List<XsChilddictionary> findByChildrenElement(StringBuffer  sql) throws Exception;
	public List<BasStuff> getUserNameById(Long id) throws Exception;
	public List<BasStuff> getUserNameByStfId(Long id) throws Exception;
	public List<XsParentdictionary> findParent(String key) throws Exception;
	public void addParent(XsParentdictionary b) throws Exception;
	//根据key查询与key关联的子表数据
	public List<XsChilddictionary> findChildByKey(String sql,final int rows,final  int page) throws Exception;
	public List datagrid(String sql) throws Exception;
	public XsChilddictionary findById(Integer childId) throws Exception;
	//public XsChilddictionary  findById(Integer childId,Integer enterpriseId) throws Exception;
	//根据key查询所有子表关联的数据
	public List<XsChilddictionary> findChilds(String key,String str,Integer enterprise_id) throws Exception;
	public List<XsChilddictionary> findChilds(String key) throws Exception;
	//根据id和value获取子表数据
	public List<XsChilddictionary> findChildByValue(Integer childId ,String value) throws Exception;
	//查询所有用户
	public List<Object[]> findUsers(BasUsers user) throws Exception;
	public XsChilddictionary findChildId(String childName,String pKey,Integer enterpriseId) throws Exception;
	public Integer findChildCon(String ckey,String pKey,Integer enterpriseId) throws Exception;
	public XsChilddictionary findChildsCon(String ckey,String pKey,Integer enterpriseId) throws Exception;
	public List<XsChilddictionary> findCkey(String cKey, Integer parentId,String cValue,Integer enid)throws Exception;
	//根据地区查邮编
	public String findChild(String childName,String pKey,Integer enterpriseId) throws Exception;
	
	public XsChilddictionary getChild(String pkey,String ckey,Integer enterpriseId)throws Exception;
		public Integer getChildId(String pkey,String ckey,Integer enterpriseId)throws Exception;
	public String findPdataValue(Integer parentId) throws Exception;
	public XsChilddictionary getXsChilddictionaryById(Integer childId)throws Exception;
}
