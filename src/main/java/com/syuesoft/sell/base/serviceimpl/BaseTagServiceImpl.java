package com.syuesoft.sell.base.serviceimpl;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.service.BaseTagService;
import com.syuesoft.sell.base.vo.ChilddictionaryVo;
import com.syuesoft.sell.base.vo.ParentdictionaryVo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsParentdictionary;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Message;
/**
 * 基础资料Service接口实现类
 * @author LiWeijun
 */
@Service("baseTagService")
public class BaseTagServiceImpl extends BaseLogServiceImpl implements BaseTagService {
	@Autowired
	private BaseTagDAO baseTagDAO;
	/**
	 * 获取具体的数据字典编号
	 * */
	
	public Integer getChildId(String pkey, String ckey,Integer enterpriseId) throws Exception {
		// TODO Auto-generated method stub
		return baseTagDAO.getChildId(pkey, ckey,enterpriseId);
	}
	
	public boolean findPkey(String Pkey) throws Exception {	
		List<XsParentdictionary> lst=baseTagDAO.findPkey(Pkey);
		if(lst!=null && lst.size()>0){
			return true;
		}
		return  false;
	}
	
	public boolean findPkey(String Pkey, Integer parentId) throws Exception {
		List<XsParentdictionary> lst=baseTagDAO.findPkey(Pkey,parentId);
		if(lst!=null && lst.size()>0){
			return true;
		}
		return  false;
	}
	
	public boolean findCkey(String cKey,Integer parentId) throws Exception {
		List<XsChilddictionary> lst=baseTagDAO.findCkey(cKey,parentId);
		if(lst!=null && lst.size()>0){
			return true;
		}
		return  false;
	}

	
	public boolean findCkey(String cKey, Integer parentId, Integer childId,String cValue,Integer enterpriseId) throws Exception {
		List<XsChilddictionary> lst=baseTagDAO.findCkey(cKey,parentId,childId,cValue,enterpriseId);
		if(lst!=null && lst.size()>0){
			return true;
		}
		return  false;
	}
	/**
	 *  添加BasParentdictionary
	 * @throws Exception 
	 * */
	
	@Log(systemName="销售系统", moduleName="数据字典",opertype="新增")
	public void addParentElement(ParentdictionaryVo parentdictionary) throws Exception {
		XsParentdictionary parent=new XsParentdictionary();
		BeanUtils.copyProperties(parentdictionary, parent);
		baseTagDAO.addParentElement(parent);
		setContent("新增父级数据字典信息,键名为【"+parentdictionary.getDataKey()+"】," +
				"键值为【"+parentdictionary.getDataValue()+"】！");
	}
	/**
	 *  删除BasParentdictionary(删除其所有的子级数据字典)
	 * @throws Exception 
	 * */
	
	@Log(systemName="销售系统", moduleName="数据字典",opertype="删除")
	public Message deleteParentElement(ParentdictionaryVo parentdictionary) throws Exception {
		// TODO Auto-generated method stub
		List<XsChilddictionary> list=baseTagDAO.findAllChildrenElement(parentdictionary.getParentId());
		Message msg = null;
		if(list!=null&&list.size()>0){
			for (XsChilddictionary xsChilddictionary : list) {
				msg = baseTagDAO.deleteChildrenElement(xsChilddictionary);
				if(msg.isSuccess()==false){
					return msg;
				}
			}
		}
		XsParentdictionary parent=new XsParentdictionary();
		BeanUtils.copyProperties(parentdictionary, parent);
		baseTagDAO.deleteParentElement(parent);
		setContent("删除父级数据字典键名为【"+parentdictionary.getDataKey()+"】," +
				"键值为【"+parentdictionary.getDataValue()+"】的信息！");
		return msg;
	}
	
	@Log(systemName="销售系统", moduleName="数据字典",opertype="修改")
	public void updateParentElement(ParentdictionaryVo parentdictionary) throws Exception {
		XsParentdictionary parent=new XsParentdictionary();
		BeanUtils.copyProperties(parentdictionary, parent);
		baseTagDAO.updateParentElement(parent);
		setContent("修改父级数据字典键名为【"+parentdictionary.getDataKey()+"】的信息," +
				"修改键值为【"+parentdictionary.getDataValue()+"】！");
	}
	
	public List<XsParentdictionary> findAllParentElement(
			ParentdictionaryVo parentdictionary) throws Exception {
		return baseTagDAO.findAllParentElement(parentdictionary);
	}
	/**
	 *根据创建者查找所有的BasParentdictionary
	 * @throws Exception 
	 * */
	
	public List<XsParentdictionary> findAllParentCount(ParentdictionaryVo parentdictionary) throws Exception {
		return baseTagDAO.findAllParentCount(parentdictionary);
		
	}
	/**
	 *  添加BasChilddictionary
	 * @throws Exception 
	 * */
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="新增")
	public void addChildrenElement(ChilddictionaryVo childVo) throws Exception {
		XsChilddictionary child=new XsChilddictionary();
		BeanUtils.copyProperties(childVo, child);
		if(child.getDataKey()==null || "".equals(child.getDataKey()) || "undefined".equals(child.getDataKey())){
			child.setDataKey(CreateID.createId("base",Contstants.SELL_BILLSDEPLOY.CHILDDICTIONARY));
		}
		baseTagDAO.addChildrenElement(child);
		String PdataValue=childVo.getPdataValue();
		if(PdataValue==null||"".equals(PdataValue)){
			 PdataValue= baseTagDAO.findPdataValue(childVo.getParentId());
		}
		
		
		setContent("给父级菜单【"+PdataValue+"】新增了键名为【"+childVo.getDataKey()+"】," +
				"键值为【"+childVo.getDataValue()+"】的子项！");

	}
	/**
	 *  删除BasChilddictionary
	 * @throws Exception 
	 * */
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="删除")
	public Message deleteChildrenElement(ChilddictionaryVo vo) throws Exception {
		XsChilddictionary child = new XsChilddictionary();
//		BeanUtils.copyProperties(vo, child);
		child.setChildId(vo.getChildId());
		child.setCreateTime(vo.getCreateTime());
		child.setDataKey(vo.getDataKey());
		child.setDataValue(vo.getDataValue());
		child.setEnterpriseId(vo.getEnterpriseId());
		child.setParentId(vo.getParentId());
		child.setRemark(vo.getRemark());
		child.setStfId(vo.getStfId());
		Message msg = baseTagDAO.deleteChildrenElement(child);
		String PdataValue = vo.getPdataValue();
		if(PdataValue==null||"".equals(PdataValue)){
			 PdataValue= baseTagDAO.findPdataValue(vo.getParentId());
		}
		setContent("删除父级菜单【"+PdataValue+"】键名为【"+vo.getDataKey()+"】," +
				"键值为【"+vo.getDataValue()+"】的子项信息,！");
		return msg;
		
	}
	/**
	 *修改BasChilddictionary
	 * @throws Exception 
	 * */
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="修改")
	public void updateChildrenElement(ChilddictionaryVo childVo) throws Exception {
		XsChilddictionary child = new XsChilddictionary();
		BeanUtils.copyProperties(childVo, child);
		baseTagDAO.updateChildrenElement(child);
		String PdataValue=childVo.getPdataValue();
		if(PdataValue==null||"".equals(PdataValue)){
			 PdataValue= baseTagDAO.findPdataValue(childVo.getParentId());
		}
		setContent("修改父级菜单【"+PdataValue+"】的子项,键名为【"+childVo.getDataKey()+"】," +
				"键值为【"+childVo.getDataValue()+"】！");
	}
	
	public List<XsChilddictionary> findAllChildrenElement(
			ChilddictionaryVo childVo) throws Exception {
		StringBuffer sb=new StringBuffer("select child_id,parent_id,createUser,createTime,dataKey,dataValue,remark,enterprise_id from xs_childdictionary where parent_id='"+childVo.getParentId()+"' and enterprise_id="+childVo.getEnterpriseId());
		
		if(childVo.getCquerydataKey()!=null && !("".equals(childVo.getCquerydataKey()))){
			sb.append("  and  dataKey like'%"+StringEscapeUtils.escapeSql(childVo.getCquerydataKey().trim())+"%'");
		}
		if(childVo.getCquerydataValue()!=null && !("".equals(childVo.getCquerydataValue()))){
			sb.append("  and  dataValue like'%"+StringEscapeUtils.escapeSql(childVo.getCquerydataValue().trim())+"%'");
		}
		final String sql=sb.toString();
		return baseTagDAO.findAllChildrenElement(sql, childVo.getSort(), childVo.getOrder(), childVo.getRows(), childVo.getPage());
	}
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}

	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}
	/**
	 *根据父级数据字典编号查找所有的BasChilddictionary
	 * @throws Exception 
	 * */
	
	public List<XsChilddictionary> findAllcount(ChilddictionaryVo childVo  ) throws Exception {
		StringBuffer sb=new StringBuffer("select child_id,parent_id,createUser,createTime,dataKey,dataValue,remark,enterprise_id from xs_childdictionary where parent_id='"+childVo.getParentId()+"'");
		
		if(childVo.getEnterpriseId()!=null && !childVo.getEnterpriseId().equals("")){
			sb.append(" and enterprise_id="+childVo.getEnterpriseId()+"");
		}
		
		if(childVo.getCquerydataKey()!=null && !("".equals(childVo.getCquerydataKey()))){
			sb.append("  and  dataKey like '%"+StringEscapeUtils.escapeSql(childVo.getCquerydataKey().trim())+"%'");
		}
		if(childVo.getCquerydataValue()!=null && !("".equals(childVo.getCquerydataValue()))){
			sb.append("  and  dataValue like '%"+StringEscapeUtils.escapeSql(childVo.getCquerydataValue().trim())+"%'");
		}
		//final String sql=sb.toString();
		return baseTagDAO.findByChildrenElement(sb);
	}
	
	public String getUserNameById(Long id) throws Exception {
		List<BasStuff> lst=baseTagDAO.getUserNameById(id);
		String userName="";
		if(lst!=null && lst.size()>0){
			userName=lst.get(0).getStfName();
		}
		return userName;
	}
	
	public List<XsParentdictionary> findParent(String key) throws Exception {
		return baseTagDAO.findParent(key);
	}
	public void addParent(ChilddictionaryVo childVo,XsParentdictionary s) throws Exception{
		baseTagDAO.addParent(s);
		XsParentdictionary parent= baseTagDAO.findParent(childVo.getPdataKey()).get(0);
		childVo.setParentId(parent.getParentId());
		XsChilddictionary xsChilddictionary = new XsChilddictionary();
		BeanUtils.copyProperties(childVo, xsChilddictionary);
		if(childVo.getDataKey()==null || "".equals(childVo.getDataKey())|| "undefined".equals(childVo.getDataKey())){
			xsChilddictionary.setDataKey(CreateID.createId("base", "BASE"));
		}
		baseTagDAO.addChildrenElement(xsChilddictionary);		 
	}
	
	/**
	 * 分页数据datagrid
	 * @throws Exception 
	 */
	
	public List<XsChilddictionary> findAllChiledByParentId(ChilddictionaryVo childVo) throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT child.child_id, child.parent_id, child.createUser, child.createTime, child.dataKey," +
				" child.dataValue,child.remark,child.enterprise_id  FROM xs_childdictionary child, xs_parentdictionary parent WHERE 1=1 AND " +
				"parent.dataKey ='"+ childVo.getPdataKey()+"' AND parent.parent_id = child.parent_id");
		sql.append(" and child.enterprise_id="+childVo.getEnterpriseId());
		if(childVo.getCquerydataKey()!=null && childVo.getCquerydataKey().length()>0){
			sql.append(" AND child.dataKey like '%"+StringEscapeUtils.escapeSql(childVo.getCquerydataKey().trim())+"%'");
		}
		if(childVo.getCquerydataValue()!=null && childVo.getCquerydataValue().length()>0 ){
			sql.append(" AND child.dataValue like '%"+StringEscapeUtils.escapeSql(childVo.getCquerydataValue().trim())+"%'");		
		}
		sql.append(" ORDER BY child.child_id ");
		List<XsChilddictionary> list = baseTagDAO.findChildByKey(sql.toString(),childVo.getRows(),childVo.getPage()); 
		
		return list;
	}
	
	public List<XsChilddictionary> findAllChiled(ChilddictionaryVo childVo) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT child.child_id, child.parent_id, child.createUser, child.createTime, child.dataKey," +
		" child.dataValue,child.remark,child.enterprise_id  FROM xs_childdictionary child, xs_parentdictionary parent WHERE 1=1 AND " +
		"parent.dataKey ='"+ childVo.getPdataKey()+"' AND parent.parent_id = child.parent_id");
		
		if(childVo.getEnterpriseId()!=null && !childVo.getEnterpriseId().equals("")){
			sql.append(" AND child.enterprise_id ="+childVo.getEnterpriseId());
		}
		if(childVo.getCquerydataKey()!=null && childVo.getCquerydataKey().length()>0){
			sql.append(" AND child.dataKey like '%"+StringEscapeUtils.escapeSql(childVo.getCquerydataKey().trim())+"%'");
		}
		if(childVo.getCquerydataValue()!=null && childVo.getCquerydataValue().length()>0 ){
			sql.append(" AND child.dataValue like '%"+StringEscapeUtils.escapeSql(childVo.getCquerydataValue().trim())+"%'");		
		}
		return baseTagDAO.findByChildrenElement(sql);
	}
	
	
	public List<ComboBoxJson> findChilds(String key,String str,Integer enterprise_id ) throws Exception {
		List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		List<XsChilddictionary> bctList = baseTagDAO.findChilds(key,str,enterprise_id);
		if(bctList != null && bctList.size() > 0){
			for(XsChilddictionary child : bctList){
				ComboBoxJson json = new ComboBoxJson();
				json.setId(child.getChildId().toString());
				json.setText(child.getDataValue());
				json.setDataKey(child.getDataKey().toString());
				list.add(json);
			}
		}
		return list;
	}
	
	public List<ComboBoxJson> findParameter(String key,String str,Integer enterprise_id) throws Exception {
		List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		List<XsChilddictionary> bctList = baseTagDAO.findChilds(key,str,enterprise_id);
		if(bctList != null && bctList.size() > 0){
			for(XsChilddictionary child : bctList){
				ComboBoxJson json = new ComboBoxJson();
				json.setId(child.getChildId().toString());
				json.setText(child.getDataKey());
				json.setDataKey(child.getDataValue());
				list.add(json);
			}
		}
		return list;
	}
	
	public List<ComboBoxJson> findUsers(BasUsers user) throws Exception {
		List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		List<Object[]> bctList = baseTagDAO.findUsers(user);
		if(bctList != null && bctList.size() > 0){
			for(Object[] obj : bctList){
				ComboBoxJson json = new ComboBoxJson();
				json.setId(obj[0].toString());
				json.setText(obj[1].toString());
				list.add(json);
			}
		}
		return list;
	}
	
	public List<ChilddictionaryVo> findChildByValue(ChilddictionaryVo childVo) throws Exception {
		List<XsChilddictionary> lst=baseTagDAO.findChildByValue(childVo.getChildId(),childVo.getDataValue());
		List<ChilddictionaryVo> childs=new ArrayList<ChilddictionaryVo>();
		for(XsChilddictionary c:lst){
			ChilddictionaryVo cVo=new ChilddictionaryVo();
			BeanUtils.copyProperties(c, cVo);
			childs.add(cVo);
		}
		return childs;
	}
	
	public boolean findCkey(String cKey, String cValue, Integer parentId,Integer enid) throws Exception {
		List<XsChilddictionary> lst=baseTagDAO.findCkey(cKey,parentId,cValue,enid);
		if(lst!=null && lst.size()>0){
			return true;
		}
		return  false;
	}
	
	public String findChild(String childName, String pKey,Integer enterpriseId) throws Exception {
		return baseTagDAO.findChild(childName, pKey,enterpriseId);
	}
	
}
