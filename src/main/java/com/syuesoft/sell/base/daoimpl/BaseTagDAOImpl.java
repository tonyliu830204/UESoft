package com.syuesoft.sell.base.daoimpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.vo.ParentdictionaryVo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsParentdictionary;
import com.syuesoft.util.Message;
/**
 * 销售系统基础资料DAO
 * */
@Repository("baseTagDAO")
public class BaseTagDAOImpl extends BaseDaoImpl<XsChilddictionary> implements BaseTagDAO {
	
	public List<XsParentdictionary> findPkey(String pkey) {
		SQLQuery query=this.getSession().createSQLQuery("SELECT parent_id,createUser,createTime,dataKey,dataValue," +
				"remark from xs_parentdictionary where dataKey='"+pkey+"'");
		query.addEntity(XsParentdictionary.class);
		return query.list();
	}
	
	public List<XsParentdictionary> findPkey(String pkey, Integer parentId) {
		SQLQuery query=this.getSession().createSQLQuery("SELECT parent_id,createUser,createTime,dataKey," +
				"dataValue,remark from xs_parentdictionary where dataKey='"+pkey+"' and parent_id!='"+parentId+"'");
		query.addEntity(XsParentdictionary.class);
		return query.list();
	}
	
	public List<XsChilddictionary> findCkey(String cKey,Integer parentId) throws Exception {
		StringBuffer sql=new StringBuffer("SELECT child_id,parent_id,createUser,createTime," +
				"dataKey,dataValue,remark,enterprise_id from xs_childdictionary " +
				"where dataKey='"+cKey+"' and parent_id='"+parentId+"'");
		return findByChildrenElement(sql);
	}
	//子级菜单
	
	public List<XsChilddictionary> findCkey(String cKey, Integer parentId,
			Integer childId,String cValue,Integer enterpriseId) throws Exception {
		StringBuffer sql=new StringBuffer("SELECT child_id,parent_id,createUser,createTime,dataKey," +
				"dataValue,remark,enterprise_id from xs_childdictionary " +
				"where (dataKey='"+cKey+"'or dataValue='"+cValue+"') and parent_id='"+parentId+"' and child_id!='"+childId+"' and enterprise_id="+enterpriseId);
		return findByChildrenElement(sql);
	}
	

	
	public void addParentElement(XsParentdictionary xsParentdictionary) {
		// TODO Auto-generated method stub
		this.getSession().save(xsParentdictionary);
	}
	
	public void deleteParentElement(XsParentdictionary xsParentdictionary) {
		this.getSession().delete(xsParentdictionary);	
	}
	/**
	 *根据父级数据字典编号查找所有的BasChilddictionary
	 * @throws Exception 
	 * */
	public List<XsChilddictionary> findAllChildrenElement(Integer parentId) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("select child_id,parent_id,createUser," +
				"createTime,dataKey,dataValue,remark,enterprise_id " +
				"from xs_childdictionary where 1=1 and parent_id='"+parentId+"'");
		return findByChildrenElement(sql);
		//query.addEntity(XsChilddictionary.class);
	}
	/**
	 *修改BasParentdictionary
	 * */
	
	public void updateParentElement(XsParentdictionary xsParentdictionary) {
		// TODO Auto-generated method stub
		this.getSession().merge(xsParentdictionary);
	}
	@SuppressWarnings("unchecked")
	
	public List<XsParentdictionary> findAllParentElement(final ParentdictionaryVo parent) throws Exception {
		StringBuffer  sb=new StringBuffer();
		sb.append("select  parent_id,createUser,createTime,dataKey,dataValue,remark from xs_parentdictionary  where 1=1");
		if(parent.getPqueryKey()!=null && !("".equals(parent.getPqueryKey()))){
			sb.append(" and  dataKey like '%"+StringEscapeUtils.escapeSql(parent.getPqueryKey().trim())+"%'");
		}
		if(parent.getPqueryValue()!=null && !("".equals(parent.getPqueryValue()))){
			sb.append(" and  dataValue like '%"+StringEscapeUtils.escapeSql(parent.getPqueryValue().trim())+"%'");
		}
		final String sql=sb.toString();
		List list=this.getHibernateTemplate().execute(new HibernateCallback<List<XsParentdictionary>>(){
			
			public List<XsParentdictionary> doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery query=session.createSQLQuery(sql);
				query.addEntity(XsParentdictionary.class);
				int beginRow=(parent.getPage()-1)*parent.getRows();
				query.setFirstResult(beginRow);
				query.setMaxResults(parent.getRows());
				return query.list();
			}
		});
		return list;
	}
	/**
	 *根据创建者查找所有的BasParentdictionary
	 * */
	
	public List<XsParentdictionary> findAllParentCount(ParentdictionaryVo parent) {
		// TODO Auto-generated method stub
		StringBuffer  sb=new StringBuffer();
		sb.append("select  parent_id,createUser,createTime,dataKey,dataValue,remark from xs_parentdictionary  where 1=1");
		if(parent.getPqueryKey()!=null && !("".equals(parent.getPqueryKey()))){
			sb.append(" and  dataKey like '%"+StringEscapeUtils.escapeSql(parent.getPqueryKey().trim())+"%'");
		}
		if(parent.getPqueryValue()!=null && !("".equals(parent.getPqueryValue()))){
			sb.append(" and  dataValue like '%"+StringEscapeUtils.escapeSql(parent.getPqueryValue().trim())+"%'");
		}
		final String sql=sb.toString();
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.addEntity(XsParentdictionary.class);
		return query.list();
	}
	/**
	 *  添加BasChilddictionary
	 * */
	
	public void addChildrenElement(XsChilddictionary xsChilddictionary) {
		// TODO Auto-generated method stub
		this.getSession().save(xsChilddictionary);
	}
	/**
	 *  删除BasChilddictionary
	 * */
	
	public Message deleteChildrenElement(XsChilddictionary xsChilddictionary)throws Exception {
		XsChilddictionary xsChilddictionarys = this.get("from XsChilddictionary chd where chd.childId= "+xsChilddictionary.getChildId()+"");
		Message msg = new Message();
		if(xsChilddictionarys!=null){
			if( xsChilddictionarys.getXsSupplierInfos().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被供应商档案使用不可删除！");
			}else if( xsChilddictionarys.getXsSupplierInfos2().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被供应商档案使用不可删除！");
			}else if( xsChilddictionarys.getXsCarModels().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆型号使用不可删除！");
			}else if( xsChilddictionarys.getXsCarInfoBrands().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆档案使用不可删除！");
			}else if( xsChilddictionarys.getXsCarInfoColor().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆档案使用不可删除！");
			}else if( xsChilddictionarys.getXsCarInfoInteriorColor().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆档案使用不可删除！");
			}else if( xsChilddictionarys.getXsCarInfoTypes().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆档案使用不可删除！");
			}else if( xsChilddictionarys.getXsCarInfoNorms().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆档案使用不可删除！");
			}else if( xsChilddictionarys.getXsCarInfoToolCase().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆档案使用不可删除！");
			}else if( xsChilddictionarys.getXsCarInfoFootd().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆档案使用不可删除！");
			}else if( xsChilddictionarys.getXsCarSellState().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆档案使用不可删除！");
			}else if( xsChilddictionarys.getXsCarCertificate().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆档案使用不可删除！");
			}else if( xsChilddictionarys.getXsCarDistribut().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆档案使用不可删除！");
			}else if( xsChilddictionarys.getXsFixStatus().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆档案使用不可删除！");
			}else if( xsChilddictionarys.getXsSellAttachments().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该附件类型已被使用不可删除！");
			}else if( xsChilddictionarys.getXsSellBacksExamine().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被调退单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellBacksState().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被调退单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellBacksType().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被调退单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCarReserve().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被预订单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCarReserve1().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被预订单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCarReserve2().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被预订单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCarReserve3().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被预订单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCarReserve4().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被预订单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCarReserve5().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被预订单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCarReserve6().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被预订单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCarReserve7().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被预订单使用不可删除！");
			}else if(xsChilddictionarys.getXsSellZhProjectAudit().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已存在装潢信息中不可删除！");
			}else if(xsChilddictionarys.getXsSellZhProjectReckoning().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已存在装潢信息中不可删除！");
			}else if( xsChilddictionarys.getXsSellCarinstallExamine().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆加装单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCarinstallFinishState().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被车辆加装单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCertificateState().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被合格证管理使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCollectionClassify().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被收款单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCollectionType().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被收款单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCollectionUnfinished().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被收款单使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCollectionsDetailDetailType().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被收款单明细使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCollectionsDetailExamine().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被收款单明细使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCollectionsDetailInvoice().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被收款单明细使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCollectionsDetailWay().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被收款单明细使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCoverCallState().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被售后回访使用不可删除！");
			}else if( xsChilddictionarys.getXsSellCoverDegree().size()>0){
				msg.setSuccess(false);
				msg.setMsg("该数据已被售后回访使用不可删除！");
			}else{
				this.getSession().delete(xsChilddictionarys);
				msg.setSuccess(true);
				msg.setMsg("删除成功！");
			}
		}else{
			msg.setSuccess(false);
			msg.setMsg("该数据不存在！");
		}
		return msg;
	}
	
	/**
	 *修改BasChilddictionary
	 * */
	public void updateChildrenElement(XsChilddictionary xsChilddictionary) {
		// TODO Auto-generated method stub
		this.getSession().merge(xsChilddictionary);
	}
	
	@SuppressWarnings("unchecked")
	public List<XsChilddictionary> findAllChildrenElement
	(final String sql,final String sort,final String order,final int rows,final int page) {
		List list=this.getHibernateTemplate().execute(new HibernateCallback<List<XsChilddictionary>>(){
			
			public List<XsChilddictionary> doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				SQLQuery query=session.createSQLQuery(sql);
				query.addEntity(XsChilddictionary.class);
				int beginRow=(page-1)*rows;
				query.setFirstResult(beginRow);
				query.setMaxResults(rows);
				return query.list();
			}
			
		});
		return list;
	}
	
	public List<XsChilddictionary> findByChildrenElement(StringBuffer sql) throws Exception {
		// TODO Auto-generated method stub
		/*SQLQuery query=this.getSession().createSQLQuery(sql);
		query.addEntity(XsChilddictionary.class);
		return query.list();*/
		List<Object[]> resultList = createSQLQuery(sql.toString());
		List<XsChilddictionary> list = new ArrayList<XsChilddictionary>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				XsChilddictionary sell = new XsChilddictionary();
				obj = resultList.get(i);
				sell.setChildId(obj[0] != null ? Integer.parseInt(obj[0].toString()) : null);
				sell.setParentId(obj[1] != null ? Integer.parseInt(obj[1].toString()) : null);
				sell.setStfId(obj[2] != null ? Long.parseLong(obj[2].toString()) : null);
				sell.setCreateTime(obj[3] != null ? Timestamp.valueOf(obj[3].toString()) : null);
				sell.setDataKey(obj[4] != null ? obj[4].toString() : null);
				sell.setDataValue(obj[5] != null ? obj[5].toString() : null);
				sell.setRemark(obj[6] != null ? obj[6].toString() : null);
				sell.setEnterpriseId(obj[7] != null ? Integer.parseInt(obj[7].toString()) : null);
				list.add(sell);
			}
		}
		//query.addEntity(XsChilddictionary.class);
		return list;
	}
	
	public List<BasStuff> getUserNameById(Long id) {
		SQLQuery query=this.getSession().createSQLQuery("SELECT  s.* FROM  bas_users u,bas_stuff  s  WHERE  u.STF_ID=s.STF_ID  AND  u.USER_ID="+id);
		query.addEntity(BasStuff.class);
		return query.list();
	}
	public List<BasStuff> getUserNameByStfId(Long id) {
		SQLQuery query=this.getSession().createSQLQuery("SELECT  s.* FROM  bas_users u,bas_stuff  s  WHERE  u.STF_ID=s.STF_ID  AND  u.STF_ID="+id);
		query.addEntity(BasStuff.class);
		return query.list();
	}
	public List<Object[]> findUsers(final BasUsers user) {
		SQLQuery query=this.getSession().createSQLQuery("SELECT stuff.STF_ID,stuff.STF_NAME FROM bas_stuff stuff,bas_users users WHERE stuff.STF_ID = users.STF_ID  AND  users.SYSTEMID = '"+Contstants.SYSTEMTYPE.XIAOSHOU+"'");
		return query.list();
	}
	
	public List<XsParentdictionary> findParent(String key) {	
		SQLQuery query=this.getSession().createSQLQuery("SELECT  parent_id,createUser,createTime,dataKey,dataValue,remark FROM xs_parentdictionary  WHERE dataKey='"+key+"'");
		query.addEntity(XsParentdictionary.class);
		return query.list();
	}
	public void addParent(XsParentdictionary b){
		this.getSession().save(b);
	}
	
	@SuppressWarnings("unchecked")
	public List<XsChilddictionary> findChildByKey(String sql,int rows,int page)throws Exception{
		List rlist = createSQLQuery(sql,page,rows);
		List<XsChilddictionary> list = rlist;
		return list;
	}
	public List<XsChilddictionary> datagrid(String sql) {	
		SQLQuery query=this.getSession().createSQLQuery(sql);
		query.addEntity(XsChilddictionary.class);
		return query.list();
	}
	
	public XsChilddictionary findById(Integer childId) {		
		return (XsChilddictionary) this.getSession().get(XsChilddictionary.class, childId);
	}

	/*
	public XsChilddictionary findById(Integer childId,Integer enterpriseId) {
		if(childId!=null&&!childId.equals("")){
			SQLQuery query=this.getSession().createSQLQuery("SELECT  * FROM xs_childdictionary  child  WHERE  child.enterprise_id="+enterpriseId+" AND  child.child_id="+childId);
			query.addEntity(XsChilddictionary.class);
			List list=query.list();
			if(list!=null && list.size()>0 ){
				return (XsChilddictionary)list.get(0);
			}
		}
		return null;
	}
	*/
	
	public List<XsChilddictionary> findChilds(String key,String str,Integer enterprise_id ) throws Exception{
		StringBuffer sb=new StringBuffer("SELECT child.child_id,child.parent_id,child.createUser," +
				"child.createTime,child.dataKey,child.dataValue,child.remark,child.enterprise_id " +
				" FROM xs_childdictionary  child,xs_parentdictionary parent WHERE 1=1");
		if(enterprise_id!=null&&!"".equals(enterprise_id)){
			sb.append(" and child.enterprise_id ="+enterprise_id);
		}
		sb.append(" and   child.parent_id=parent.parent_id AND parent.dataKey='"+key+"'");
		if(str!=null && !("".equals(str))){
			sb.append("	 and  child.dataValue like '%"+StringEscapeUtils.escapeSql(str.trim())+"%'");
		}
		//SQLQuery query=this.getSession().createSQLQuery(sb.toString());
		
		return findByChildrenElement(sb);
	}
	public List<XsChilddictionary> findChilds(String key) throws Exception{
		StringBuffer sql=new StringBuffer("SELECT child.child_id,child.parent_id,child.createUser,child.createTime,child.dataKey,child.dataValue,child.remark,child.enterprise_id" +
				" FROM xs_childdictionary  child,xs_parentdictionary parent  WHERE child.parent_id=parent.parent_id AND parent.dataKey='"+key+"'");
		return findByChildrenElement(sql);
	}
		/**
	 * 
	 * @param pkey 父key
	 * @param ckey	子key
	 * @return 
	 */
	public Integer getChildId(String pkey,String ckey,Integer enterpriseId)throws Exception{
		List child = this.createSQLQuery("SELECT child.child_id FROM xs_childdictionary  child, xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND parent.dataKey='"+pkey+"' AND child.dataKey='"+ckey+"' and child.enterprise_id="+enterpriseId);
		if(child!=null && child.size()>0){
			return  (Integer) child.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 
	 * @param pkey 父key
	 * @param ckey	子key
	 * @return
	 */
	public XsChilddictionary getChild(String pkey,String ckey,Integer enterpriseId)throws Exception{
		List  child = this.find("SELECT child FROM XsChilddictionary  child, XsParentdictionary  parent  WHERE child.parentId = parent.parentId AND parent.dataKey='"+pkey+"' AND child.dataKey='"+ckey+"' and child.enterpriseId="+enterpriseId);
		if(child!=null && child.size()>0){
			return  (XsChilddictionary) child.get(0);
		}else{
			return null;
		}
	}
	
	public List<XsChilddictionary> findChildByValue(Integer childId ,String value) throws Exception {
		//SQLQuery query=this.getSession().createSQLQuery("select child_id,parent_id,createUser,createTime," +
				//"dataKey,dataValue,remark,enterprise_id from xs_childdictionary " +
				//"where 1=1 and child_id like '%"+childId+"%' and dataValue like '%"+StringEscapeUtils.escapeSql(value.trim())+"%'");
		//query.addEntity(XsChilddictionary.class);
		//return query.list();
				StringBuffer sql=new StringBuffer("select child_id,parent_id,createUser,createTime," +
				"dataKey,dataValue,remark,enterprise_id from xs_childdictionary where 1=1 and child_id like '%"+childId+"%' " +
						"and dataValue like '%"+StringEscapeUtils.escapeSql(value.trim())+"%'");
				return findByChildrenElement(sql);
	}
	public XsChilddictionary findChildId(String childName,String pKey,Integer enterpriseId) throws Exception{
		List  child =this.find("SELECT c FROM  XsChilddictionary c,XsParentdictionary  p  WHERE c.parentId=p.parentId AND "+
								   "p.dataKey='"+pKey+"' AND c.dataValue='"+childName+"' and c.enterpriseId="+enterpriseId);
		if(child!=null && child.size()>0){
			return  (XsChilddictionary) child.get(0);
		}else{
			
			return null;
		}
	}
	public String findChild(String childName,String pKey,Integer enterpriseId) throws Exception{
		List  child = this.createSQLQuery("SELECT c.dataKey FROM  xs_childdictionary c,xs_parentdictionary p  WHERE c.parent_id=p.parent_id AND "+
								   "p.dataKey='"+pKey+"' AND c.dataValue='"+childName+"' and c.enterprise_id="+enterpriseId);
		if(child!=null && child.size()>0){
			return  (String) child.get(0);
		}else{
			return null;
		}
	}
	
	public String findPdataValue(Integer parentId) throws Exception{
		List  child =this.createSQLQuery("SELECT p.dataValue FROM xs_parentdictionary p where p.parent_id ="+parentId);
		if(child!=null && child.size()>0){
			return  (String) child.get(0);
		}else{
			return null;
		}
	}
	
	//000
	public List<XsChilddictionary> findCkey(String cKey, Integer parentId,String cValue,Integer enid) throws Exception {
		StringBuffer sql= new StringBuffer("SELECT C.child_id,C.parent_id,C.createUser,C.createTime,C.dataKey,C.dataValue," +
		"C.remark,C.enterprise_id from xs_childdictionary c ," +
		"xs_parentdictionary p where c.parent_id=p.parent_id and c.parent_id="+ parentId+" and  " +
				" (c.dataValue='"+cValue+"'");
		if(cKey!=null&&!cKey.equals("")&&!cKey.equals("undefined")){
			sql.append(" or c.dataKey='"+cKey+"'");
		}
		sql.append(")");
		if(enid!=null){
			sql.append(" and C.enterprise_id="+enid) ;
		}
		return findByChildrenElement(sql);
	}
	
	public Integer findChildCon(String ckey, String pKey,Integer enterpriseId) throws Exception {
		List  child =this.createSQLQuery("SELECT c.child_id FROM  xs_childdictionary c,xs_parentdictionary p  WHERE c.parent_id=p.parent_id AND "+
			   "p.dataKey='"+pKey+"' AND c.dataKey='"+ckey+"' and c.enterprise_id="+enterpriseId);
			if(child!=null && child.size()>0){
			return  (Integer) child.get(0);
			}else{
			return null;
			}
	}
	
	public XsChilddictionary getXsChilddictionaryById(Integer chilId)
			throws Exception {
		return this.get("from XsChilddictionary xc where xc.childId="+chilId);
	}
	
	@Override
	public XsChilddictionary findChildsCon(String ckey, String pKey,Integer enterpriseId) throws Exception {
			List  child =this.find("SELECT c FROM  XsChilddictionary c,XsParentdictionary p  WHERE c.parentId=p.parentId AND "+
					   "p.dataKey='"+pKey+"' AND c.dataKey='"+ckey+"' and c.enterpriseId="+enterpriseId);
					if(child!=null && child.size()>0){
					  return  (XsChilddictionary) child.get(0);
					}else{
					  return null;
					}
	}
	
}
