package com.syuesoft.sell.base.service;

import java.util.List;

import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.base.vo.ChilddictionaryVo;
import com.syuesoft.sell.base.vo.ParentdictionaryVo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsParentdictionary;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Message;

/**
 * 基础资料Service接口
 * 
 * @author LiWeijun
 */
public interface BaseTagService {
	public boolean findPkey(String Pkey) throws Exception;
	public boolean findPkey(String Pkey,Integer parentId) throws Exception;
	public boolean findCkey(String cKey,Integer parentId) throws Exception;
	public boolean findCkey(String cKey,String cValue,Integer parentId,Integer enid) throws Exception;
	public boolean findCkey(String cKey,Integer parentId,Integer childId,String cValue,Integer enterpriseId) throws Exception;
	// 添加BasParentdictionary
	public void addParentElement(ParentdictionaryVo parentdictionary) throws Exception;
	// 删除BasParentdictionary（删除其所有的子级数据字典）
	public Message deleteParentElement(ParentdictionaryVo parentdictionary) throws Exception;
	// 修改BasParentdictionary
	public void updateParentElement(ParentdictionaryVo parentdictionary) throws Exception;
	// 根据创建者查找所有的BasParentdictionary
	public List<XsParentdictionary> findAllParentElement(ParentdictionaryVo parentdictionary) throws Exception;
	public List<XsParentdictionary> findAllParentCount(ParentdictionaryVo parentdictionary) throws Exception;
	// 添加BasChilddictionary
	public void addChildrenElement(ChilddictionaryVo childVo) throws Exception;
	// 删除BasChilddictionary
	public Message deleteChildrenElement(ChilddictionaryVo childVo) throws Exception;
	// 修改BasChilddictionary
	public void updateChildrenElement(ChilddictionaryVo childVo) throws Exception;
	// 根据父级数据字典编号查找所有的BasChilddictionary
	public List<XsChilddictionary> findAllcount(ChilddictionaryVo childVo) throws Exception;
	public List<XsChilddictionary> findAllChildrenElement(ChilddictionaryVo childVo) throws Exception;
	public  String  getUserNameById(Long id) throws Exception;
	public List<XsParentdictionary>findParent(String key) throws Exception;
	public void addParent(ChilddictionaryVo childVo,XsParentdictionary s) throws Exception;
	//根据key查询与key关联的子表数据
	public List<XsChilddictionary> findAllChiledByParentId(ChilddictionaryVo childVo) throws Exception;
	public List<XsChilddictionary> findAllChiled(ChilddictionaryVo childVo) throws Exception;
	//根据key查询所有子表关联的数据
	public List<ComboBoxJson> findChilds(String key,String str,Integer enterprise_id ) throws Exception;
	
	public List<ComboBoxJson> findParameter(String key,String str,Integer enterprise_id) throws Exception;
	//根据id和value获取子表数据
	public List<ChilddictionaryVo> findChildByValue(ChilddictionaryVo childVo) throws Exception;
	//查询所有用户
	public List<ComboBoxJson> findUsers(BasUsers user) throws Exception;
	//根据地区查邮编
	public String findChild(String childName,String pKey,Integer enterpriseId) throws Exception;
		
	public Integer getChildId(String pkey,String ckey,Integer enterpriseId)throws Exception;
}
