package com.syuesoft.bas.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.EnterpriseInfoDAO;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.bas.service.DistributpurviewService;
import com.syuesoft.base.vo.DistrubtPurviewVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasRoleInfo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.qx.dao.BasUsersDao;
import com.syuesoft.role.dao.IRoleDao;
import com.syuesoft.util.ComboBoxJson;
/**
 * 分布点权限管理Service实现
 * */
@Service("distributpurviewService")
public class DistributpurviewServiceImpl implements DistributpurviewService {
   @Autowired
	private EnterpriseInfoDAO enterpriseInfoDAO;
   @Autowired
    private IRoleDao roleDao;
   @Autowired
   	private BasUsersDao basUsersDao;
   @Autowired
   	private BaseService baseService;
	
	/**
	 * 分布点权限管理datagrid
	 * */
	
	public Datagrid distributpurviewDataGrid(DistrubtPurviewVo dpVo)
			throws Exception {
		Datagrid dg=new Datagrid();
		List<DistrubtPurviewVo> rows=new ArrayList<DistrubtPurviewVo>();
		List<Object> tempList=enterpriseInfoDAO.find("from EnterpriseInfo ei where ei.parentEnterpriseId="+dpVo.getEnterpriseId());
		DistrubtPurviewVo dp=null;
		EnterpriseInfo info=null;
		if(tempList!=null&&tempList.size()>0)
			for (Object object : tempList) {
				info=(EnterpriseInfo)object;
				dp=new DistrubtPurviewVo();
				dp.setEnterpriseId(info.getEnterpriseId());
				dp.setEnterpriseName(info.getEnterpriseName());
				rows.add(dp);
			}
		dg.setRows(rows);
		dg.setTotal(Integer.MAX_VALUE);
		return dg;
	}
	/**
	 * 分布点权限管理treegrid
	 * */
	
	public Datagrid distributpurviewTreeGrid(DistrubtPurviewVo dpVo)
			throws Exception {
		Datagrid dg=new Datagrid();
		List<DistrubtPurviewVo> rows=new ArrayList();
//		String param="1";
//		if(dpVo.getBasUsersLevel()!=null){
//			if(dpVo.getBasUsersLevel().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR)){
//				param="-1";
//			}
//		}
		String param="(select temp.enterpriseId from EnterpriseInfo temp where temp.parentEnterpriseId=1)";
		EnterpriseInfo ei=(EnterpriseInfo)enterpriseInfoDAO.get("from EnterpriseInfo ei where ei.parentEnterpriseId="+param);
		if(ei!=null){
			DistrubtPurviewVo dp=new DistrubtPurviewVo();
			dp.setEnterpriseId(ei.getEnterpriseId());
			dp.setEnterpriseCode(ei.getEnterpriseCode());
			dp.setEnterpriseName(ei.getEnterpriseName());
			if(isExistsChild(dp.getEnterpriseId())){
				dp.setState("closed");
			}else{
				dp.setState("open");
			}
			rows.add(dp);
		}
		dg.setRows(rows);
		dg.setTotal(Integer.MAX_VALUE);
		return dg;
	}
	/**
	 * 分布点权限管理treegrid子级数据
	 * */
	
	public List<DistrubtPurviewVo> distributpurviewTreeGridChild(DistrubtPurviewVo dpVo)
			throws Exception {
		List<DistrubtPurviewVo> rows=new ArrayList();
		List list=enterpriseInfoDAO.find("from EnterpriseInfo ei where ei.parentEnterpriseId="+dpVo.getParentEnterpriseId());
		DistrubtPurviewVo dp=null;
		if(list!=null&&list.size()>0)
			for (Object object : list) {
				EnterpriseInfo ei=(EnterpriseInfo)object;
				dp=new DistrubtPurviewVo();
				dp.setEnterpriseId(ei.getEnterpriseId());
				dp.setEnterpriseCode(ei.getEnterpriseCode());
				dp.setEnterpriseName(ei.getEnterpriseName());
				dp.setParentEnterpriseId(dpVo.getParentEnterpriseId());
				if(isExistsChild(dp.getEnterpriseId())){
					dp.setState("closed");
				}else{
					dp.setState("open");
				}
				rows.add(dp);
			}
		return rows;
	}
	private boolean isExistsChild(Integer parentId) throws Exception{
		List list=enterpriseInfoDAO.createSQLQuery("SELECT enterprise_id FROM enterprise_info WHERE parentEnterprise_id="+parentId);
		if(list!=null&&list.size()>0)
			return true;
		else
			return false;
	}
	/**
	 * 分布点权限管理用户数据
	 * */
	
	public Datagrid findDistrubtPurviewUsers(DistrubtPurviewVo dpVo)
			throws Exception {
		Datagrid dg=new Datagrid();
		List<DistrubtPurviewVo> rows=new ArrayList();
		StringBuffer sb=new StringBuffer("SELECT bs.stf_id,bs.stf_name,bd.DEPT_NAME,bs.STF_ZWGZ FROM bas_stuff bs JOIN bas_dept bd ON bd.DEPT_ID = bs.DEPT_ID WHERE 1=1 ");
		sb.append(" AND bs.STF_YES = '"+Contstants.SYSTEMUSER.STFYES+"'");
		sb.append(" AND bs.enterprise_id = "+dpVo.getEnterpriseId());
		if(dpVo.getStfName()!=null&&dpVo.getStfName().length()>0)
			sb.append(" and bs.stf_name like '%"+StringEscapeUtils.escapeSql(dpVo.getStfName().trim())+"%'");
		if(dpVo.getDeptName()!=null&&dpVo.getDeptName().length()>0)
			sb.append(" and bs.dept_id="+dpVo.getDeptName());
		if(dpVo.getStfZwgz()!=null&&dpVo.getStfZwgz().length()>0)
			sb.append(" and bs.STF_ZWGZ like '%"+StringEscapeUtils.escapeSql(dpVo.getStfZwgz().trim())+"%'");
		List<Object[]> list=enterpriseInfoDAO.createSQLQuery(sb.toString(),dpVo.getPage(),dpVo.getRows());
		DistrubtPurviewVo dp=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				dp=new DistrubtPurviewVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					dp.setStfId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					dp.setStfName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					dp.setDeptName(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					dp.setStfZwgz(obj[3].toString());
				rows.add(dp);
			}
		dg.setRows(rows);
		dg.setTotal(enterpriseInfoDAO.getSQLCount(sb.toString(), null));
		return dg;
	}
	/**
	 * 获取集团的所有角色
	 * */
	
	public List<ComboBoxJson> findEnterpriseInfoRoles(DistrubtPurviewVo dpVo)
			throws Exception {
		List<ComboBoxJson> list=new ArrayList();
		ComboBoxJson cbj=null;
		List<Object[]> tempList=null;	
		if(dpVo.getQ()!=null&&dpVo.getQ().length()>0){
			tempList=enterpriseInfoDAO.createSQLQuery("SELECT DISTINCT br.role_id,br.role_name " +
					" FROM bas_user_role bur INNER JOIN bas_role br ON br.role_id=bur.ROLE_ID AND br.role_name like '%"+dpVo.getQ()+
					"%' AND br.enterprise_id=(select ei.enterprise_id from enterprise_info ei where ei.parentEnterprise_id=1)");
		}else{
			tempList=enterpriseInfoDAO.createSQLQuery("SELECT DISTINCT br.role_id,br.role_name " +
					" FROM bas_user_role bur INNER JOIN bas_role br ON br.role_id=bur.ROLE_ID AND br.enterprise_id=(select ei.enterprise_id from enterprise_info ei where ei.parentEnterprise_id=1)");
		}
		if(tempList!=null&&tempList.size()>0)
			for (Object[] obj : tempList) {
				cbj=new ComboBoxJson();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					cbj.setId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cbj.setText(obj[1].toString());
				list.add(cbj);
			}
		return list;
	}
	/**
	 * 指定角色的系统类型与指定的系统类型是否一致
	 * */
	
	public Boolean isAccordSystemType(DistrubtPurviewVo dpVo) throws Exception {
		if(dpVo.getRoleId()!=null&&dpVo.getSystemType().length()>0&&dpVo.getSystemType()!=null&&dpVo.getSystemType().length()>0){
			BasRoleInfo bri=roleDao.get("from BasRoleInfo bri where bri.roleId="+dpVo.getRoleId());
			if(bri.getSystemType()==null||bri.getSystemType().length()==0)
				return null;
			if(dpVo.getSystemType().equals(bri.getSystemType())){
				return true;
			}else{
				return false;				
			}
		}	
		return null;
	}
	/**
	 * 当前用户可访问的所有企业信息
	 * */
	
	public Datagrid findAllStoreOfUser(DistrubtPurviewVo dpVo) throws Exception {
		
		Datagrid dg=new Datagrid();
		StringBuffer sb=new StringBuffer("SELECT  ei.enterprise_id,ei.enterprise_code,ei.enterprise_name,ei.enterprise_jm,ei.enterprise_address,");
		sb.append(" ei.enterprise_zipcode,ei.enterprise_fax,ei.enterprise_telephone,ei.enterprise_person FROM"); 
		sb.append(" enterprise_info ei ");
		if(dpVo.getStfId()!=null&&dpVo.getStfId().length()>0){
			sb.append(" INNER JOIN (SELECT DISTINCT bms.enterprise_id,bms.stf_id FROM bas_menu_stuff bms) AS bms ON bms.enterprise_id=ei.enterprise_id");
			sb.append(" WHERE bms.STF_ID="+dpVo.getStfId()+" AND ei.enterprise_id <> "+dpVo.getEnterpriseId());
			sb.append(" UNION SELECT ei.enterprise_id,ei.enterprise_code,ei.enterprise_name,ei.enterprise_jm,");
			sb.append(" ei.enterprise_address,ei.enterprise_zipcode,ei.enterprise_fax,ei.enterprise_telephone,"); 
			sb.append(" ei.enterprise_person	FROM enterprise_info ei  INNER JOIN bas_stuff bs");  
			sb.append(" ON bs.enterprise_id = ei.enterprise_id WHERE bs.STF_ID ="+dpVo.getStfId()+" AND ei.enterprise_id <> "+dpVo.getEnterpriseId());    
		}else{
			return dg;			
		}
		if(dpVo.getEnterpriseName()!=null&&dpVo.getEnterpriseName().length()>0)
			sb.append(" and ei.enterprise_name like '%"+StringEscapeUtils.escapeSql(dpVo.getEnterpriseName().trim())+"%'");
		if(dpVo.getEnterpriseJm()!=null&&dpVo.getEnterpriseJm().length()>0)
			sb.append(" and ei.enterprise_jm like '%"+StringEscapeUtils.escapeSql(dpVo.getEnterpriseJm().trim())+"%'");
		if(dpVo.getEnterprisePerson()!=null&&dpVo.getEnterprisePerson().length()>0)
			sb.append(" and ei.enterprise_person like '%"+StringEscapeUtils.escapeSql(dpVo.getEnterprisePerson().trim())+"%'");
		List<EnterpriseInfo> rows=new ArrayList();
		EnterpriseInfo ei=null;
		List<Object[]> list=enterpriseInfoDAO.createSQLQuery(sb.toString(),dpVo.getPage(),dpVo.getRows());
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				ei=new EnterpriseInfo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					ei.setEnterpriseId(Integer.parseInt(obj[0].toString()));
				if(obj[1]!=null&&obj[1].toString().length()>0)
					ei.setEnterpriseCode(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					ei.setEnterpriseName(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					ei.setEnterpriseJm(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					ei.setEnterpriseAddress(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					ei.setEnterpriseZipcode(obj[5].toString());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					ei.setEnterpriseFax(obj[6].toString());
				if(obj[7]!=null&&obj[7].toString().length()>0)
				ei.setEnterpriseTelephone(obj[7].toString());
				if(obj[8]!=null&&obj[8].toString().length()>0)
					ei.setEnterprisePerson(obj[8].toString());
				rows.add(ei);
			}
		dg.setRows(rows);
		dg.setTotal(enterpriseInfoDAO.getSQLCount(sb.toString(), null));
		return dg;
	}
	/**
	 * 查询员工系统级别（除超级管理员，集团管理员）
	 * */
	
	public List<ComboBoxJson> findSystemLevel(DistrubtPurviewVo dpVo)
			throws Exception {
		List<ComboBoxJson> lt=new ArrayList();
		List<ComboBoxJson> list=baseService.baseListData(dpVo.getBasUsersLevel());
		if(list!=null&&list.size()>0)
			for (ComboBoxJson comboBoxJson : list) {
				if(dpVo.getRemoveLevel()!=null&&dpVo.getRemoveLevel().length()>0){
					if(!(comboBoxJson.getId().equals(dpVo.getRemoveLevel())||comboBoxJson.getId().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))){
						lt.add(comboBoxJson);
					}
				}else{
					if(!comboBoxJson.getId().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR)){
						if(!comboBoxJson.getId().equals(Contstants.EMPLOYEELEVEL.ADMIN))
							lt.add(comboBoxJson);
					}					
				}
			}
		return lt;
	}
}
