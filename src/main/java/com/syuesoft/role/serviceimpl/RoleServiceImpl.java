package com.syuesoft.role.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasPersonnelInformationSetDao;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.base.vo.MenuVo;
import com.syuesoft.base.vo.RoleVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.menu.dao.IMenuDao;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasMenuInfo;
import com.syuesoft.model.BasRoleInfo;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.role.dao.IRoleDao;
import com.syuesoft.role.service.IRoleService;
import com.syuesoft.util.Msg;
import com.syuesoft.util.TreeJson;

/**
 * 角色管理
 * 
 * @author HeXin
 * 
 */
@Repository("roleServiceImpl")
public class RoleServiceImpl extends BaseLogServiceImpl implements IRoleService
{
    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private BaseService baseService;
    @Autowired
    private IMenuDao menuDao;

    @Autowired
    private BasPersonnelInformationSetDao basPersonnelInformationSetDao;

    
    public Datagrid getDatagrid(RoleVo vo, BasUsers user) throws Exception
    {
        Datagrid dg = new Datagrid();
        List<RoleVo> rows = new ArrayList<RoleVo>();
        StringBuffer hql = new StringBuffer();
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            hql.append("from BasRoleInfo role where (role.enterpriseInfo.enterpriseId="+vo.getEnterpriseId()+" OR role.enterpriseChildId="+vo.getEnterpriseId()+") ");
        }
        else
        {
            hql.append("from BasRoleInfo role where 1=1 and role.systemType ='"
                    + user.getSystemId() + "' and (role.enterpriseInfo.enterpriseId="+vo.getEnterpriseId()+" OR role.enterpriseChildId="+vo.getEnterpriseId()+") ");
        }
        if (vo.getRoleName() != null && vo.getRoleName().length()>0){
            hql.append(" and role.roleName like '%"
                    + StringEscapeUtils.escapeSql(vo.getRoleName()) + "%'");
        }
        List<BasRoleInfo> basRoleInfos = roleDao.find(hql.toString(), vo
                .getPage(), vo.getRows());
        int total = roleDao.getCount(hql.toString());
        if (basRoleInfos != null && basRoleInfos.size() > 0)
        {
            for (BasRoleInfo role : basRoleInfos)
            {
                RoleVo roleVo = new RoleVo();
                roleVo.setRoleId(role.getRoleId());
                roleVo.setRoleName(role.getRoleName());
                List<BasChilddictionary> childs = baseService
                        .getBasChilddictionary(Contstants.SYSTEMTYPE.SYSTEM);
                if (childs != null && childs.size() > 0)
                {
                    for (BasChilddictionary child : childs)
                    {
                        if (role.getSystemType().equals(child.getDataKey()))
                        {
                            roleVo.setSystemTypekey(role.getSystemType());
                            roleVo.setSystemTypevalue(child.getDataValue());
                        }
                    }
                }
                if (role.getPerson() != null)
                {
                    BasStuff basStuff = (BasStuff) basPersonnelInformationSetDao
                            .getObject(BasStuff.class, role.getPerson());
                    if (basStuff != null)
                    {
                        roleVo.setPerson(basStuff.getStfId());
                        roleVo.setPersonName(basStuff.getStfName());
                    }
                }
                roleVo.setCreateDate(role.getCreateDate());
                roleVo.setRemark(role.getRemark());
                roleVo.setRoleDefaultTag(role.getRoleDefaultTag());
                roleVo.setEnterpriseId(role.getEnterpriseInfo().getEnterpriseId().toString());
                rows.add(roleVo);
            }
        }
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    
    @Log(moduleName = "角色管理", opertype = "删除角色", content = "角色管理-->删除角色")
    public Msg delete(RoleVo roleVo) throws Exception
    {
        Msg msg = new Msg();
        if(roleVo.getRoleDeleteValidateFlag()!=null&&roleVo.getRoleDeleteValidateFlag()==true){
        	if(isExistsUserOfRole(roleVo.getRoleId())){
        		msg.setSuccess(false);
        		msg.setMsg("角色中存在用户，不可删除！");
        		return msg;
        	}
        }
        roleDao.deleteUserRoleByRoleId(roleVo.getRoleId());
        roleDao.deleteMenuRoleByRoleId(roleVo.getRoleId());
        BasRoleInfo basRoleInfo = new BasRoleInfo();
        basRoleInfo.setRoleId(roleVo.getRoleId());
        basRoleInfo.setRoleName(roleVo.getRoleName());
        basRoleInfo.setPerson(roleVo.getPerson());
        basRoleInfo.setCreateDate(roleVo.getCreateDate());
        basRoleInfo.setSystemType(roleVo.getSystemTypekey());
        roleDao.deleteRole(basRoleInfo);
        msg.setMsg("角色删除成功");
        msg.setSuccess(true);
        setContent("删除【" + roleVo.getRoleName() + "】的角色信息");
        return msg;
    }

    
    public Object findnoselectedBasStuff(RoleVo roleVo, BasUsers user)
            throws Exception
    {
        List<Object[]> objs = roleDao.findnoselected(roleVo, user);
        List<BasStuff> basStuffs = null;
        if(objs!=null&&objs.size()>0){
	        for (Object[] obj : objs)
	        {
	            BasStuff basStuff = new BasStuff();
	            basStuff.setStfYid(obj[0].toString());
	            basStuff.setStfName(obj[1].toString());
	            if (basStuffs == null)
	                basStuffs = new ArrayList<BasStuff>();
	            basStuffs.add(basStuff);
	        }
        }
        return basStuffs;
    }

    public Object findselectedBasStuff(RoleVo roleVo, BasUsers user)
            throws Exception
    {
        List<Object[]> objs = roleDao.findselected(roleVo, user);
        List<BasStuff> basStuffs = null;
        if(objs != null&&objs.size()>0){
            for (Object[] obj : objs)
            {
                BasStuff basStuff = new BasStuff();
                basStuff.setStfYid(obj[0].toString());
                basStuff.setStfName(obj[1].toString());
                if (basStuffs == null)
                    basStuffs = new ArrayList<BasStuff>();
                basStuffs.add(basStuff);
            }
        }
        return basStuffs;
    }

	/**
     * 分布点权限管理菜单树
     * */
    
	public Object distributePurviewMenuRoleTree(RoleVo roleVo, BasUsers users)
			throws Exception {

        List<TreeJson> list = new ArrayList<TreeJson>();
        String sql="SELECT bs.STF_ID FROM bas_stuff bs INNER JOIN bas_users bu ON bu.stf_id=bs.stf_id AND bu.LEVAL_='"+Contstants.EMPLOYEELEVEL.ADMIN+"'"
        				+" INNER JOIN enterprise_info ei ON ei.enterprise_id=bs.enterprise_id AND ei.enterprise_id="+roleVo.getEnterpriseId();
        List lt=menuDao.createSQLQuery(sql);
        if(lt==null){
        	return list;
        }else{
            Set<String> set=getMenuCodeByRole(Long.parseLong(lt.get(0).toString()));
            String sqls="SELECT bmi.menu_id,bmi.menu_name,bmi.childMenu,bmi.menu_code FROM bas_menu_info bmi WHERE bmi.menu_pid IS NULL";
            List<Object[]> res=menuDao.createSQLQuery(sqls);
            if(res!=null&&res.size()>0){
                for (Object[] obj : res) {
                    if(obj[3]!=null&&obj[3].toString().length()>0){
                        if(set.contains(obj[3].toString())){
                            TreeJson json = new TreeJson();
                            if(obj[0]!=null&&obj[0].toString().length()>0){
                                json.setId(obj[0].toString());
                            }
                            if(obj[1]!=null&&obj[1].toString().length()>0){
                                json.setText(obj[1].toString());
                            }
                            if(obj[2]!=null&&obj[2].toString().length()>0){
                                json.setIconCls(obj[2].toString().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "": "icon-blank");
                                json.setState(obj[2].toString().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "closed": "open");
                            }
                            if (roleVo.getSelecteds() != null){
                                json.setChecked(isExistsMenuStuff(roleVo.getEnterpriseId(), roleVo.getSelecteds(),json.getId()));
                            }else{
                                json.setChecked(false);
                            }
                            if (obj[0]!=null&&obj[2]!=null&&obj[2].toString().equals(Contstants.CHILDMENU.CHILDMENU_YES)){
                                getDistributePurviewMenuRoleTreeChild(json,obj[0].toString(),roleVo,set);
                            }
                            list.add(json);
                        }
                    }
                }
            }
        }
        return list;
	}
    private void getDistributePurviewMenuRoleTreeChild(TreeJson json,String menuId,RoleVo roleVo,Set<String> set) throws Exception{
    	
    	 String sqls="SELECT bmi.menu_id,bmi.menu_name,bmi.childMenu,bmi.menu_code FROM bas_menu_info bmi WHERE bmi.menu_pid="+menuId;
         List<Object[]> res=menuDao.createSQLQuery(sqls);
         TreeJson json_ =null;
         if(res!=null&&res.size()>0){
        	 List<TreeJson> rows = new ArrayList<TreeJson>();
         	 for (Object[] obj : res) {
         		if(obj[3]!=null&&obj[3].toString().length()>0)
         			if(set.contains(obj[3].toString())){
         				json_= new TreeJson();
         				if(obj[0]!=null&&obj[0].toString().length()>0)
         					json_.setId(obj[0].toString());
         				if(obj[1]!=null&&obj[1].toString().length()>0)
         					json_.setText(obj[1].toString());
         				if(obj[2]!=null&&obj[2].toString().length()>0){
         					json_.setIconCls(obj[2].toString().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "": "icon-blank");
         					json_.setState(obj[2].toString().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "closed": "open");
         				}
         				if (roleVo.getSelecteds() != null){
	    					json_.setChecked(isExistsMenuStuff(roleVo.getEnterpriseId(), roleVo.getSelecteds(),json_.getId()));
	    				}else{
	    					json_.setChecked(false);
	    				}
         				if (obj[0]!=null&&obj[2]!=null&&obj[2].toString().equals(Contstants.CHILDMENU.CHILDMENU_YES))
         					getDistributePurviewMenuRoleTreeChild(json_,obj[0].toString(),roleVo,set);
         			   rows.add(json_);
         			   json.setChildren(rows);
         			   json.setState("closed");
         			}
				}
         }
    }
    /**
     * 指定企业中指定用户与菜单关系是否存在
     * */
	
	public boolean isExistsMenuStuff(String enterpriseId,String stfId,String menuId) throws Exception {
		List list=menuDao.createSQLQuery("SELECT * FROM bas_menu_stuff bms WHERE bms.enterprise_id="+enterpriseId+" AND bms.stf_id="+stfId+" AND bms.menu_id="+menuId);
		if(list!=null&&list.size()>0)
			return true;
		return false;
	}

	
    public List<TreeJson> menuRoleTree(RoleVo roleVo, BasUsers users) throws Exception
    {
    	
        List<TreeJson> list = new ArrayList<TreeJson>();
        StringBuffer hql = new StringBuffer();
        if (users.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR)){
            hql.append("from BasMenuInfo menu where 1=1 and (menu.menuPid is null or menu.menuPid ='') ");
	            if(roleVo.getSystemTypekey() != null && !roleVo.getSystemTypekey().equals("") && !roleVo.getSystemTypekey().equals(Contstants.SYSTEMTYPE.ALL)){
	                hql.append(" and menu.systemMenu ='"+roleVo.getSystemTypekey()+"'");
	            }
        }else if(!users.getSystemId().equals(Contstants.SYSTEMTYPE.ALL)){
            hql.append("from BasMenuInfo menu where 1=1 and (menu.menuPid is null or menu.menuPid ='') and menu.systemMenu ='"+users.getSystemId()+"'");
        }else{
            hql.append("from BasMenuInfo menu where 1=1 and (menu.menuPid is null or menu.menuPid ='') ");
            if(roleVo.getSystemTypekey() != null && !roleVo.getSystemTypekey().equals("") && !roleVo.getSystemTypekey().equals(Contstants.SYSTEMTYPE.ALL)){
                hql.append(" and menu.systemMenu ='"+roleVo.getSystemTypekey()+"'");
            }
        }
        TreeJson json =null;
        List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString());
        if (basMenuInfos != null && basMenuInfos.size() > 0){
            for (BasMenuInfo menu : basMenuInfos){
                json= new TreeJson();
                json.setId(String.valueOf(menu.getMenuId()));
                json.setText(menu.getMenuName());
                json.setIconCls(menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "": "icon-blank");
                json.setState(menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "closed": "open");
                if (roleVo.getRoleId() != null){
                    json.setChecked(getMenutoRole(menu.getMenuId(), roleVo.getRoleId()));
                }else{
                    json.setChecked(false);
                }
                if (menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES))
                    getMenuRoleChild(json, menu, users, roleVo);
                list.add(json);
            }
        }
        return list;
    }
	
	/**
     * 动态菜单树
     * */
    
    public Object dynamicMenuRoleTree(RoleVo roleVo, BasUsers users)
            throws Exception {
        if(roleVo.getRoleId()==null){
            StringBuffer sb=new StringBuffer("SELECT bur.role_id FROM bas_user_role bur INNER JOIN bas_role br ON br.role_id=bur.role_id WHERE bur.user_id="+users.getBasStuff().getStfId());
            List list=roleDao.createSQLQuery(sb.toString());
            if(list!=null&&list.size()>0)
                return findAppointRole(list.get(0).toString(),roleVo.getSystemTypekey(),
                        users.getBasStuff().getStfId().toString(),users.getLeavl(),Integer.parseInt(roleVo.getEnterpriseId()), false);
        }else{
            return findAppointRole(roleVo.getRoleId().toString(),roleVo.getSystemTypekey(),
                    users.getBasStuff().getStfId().toString(),users.getLeavl(),Integer.parseInt(roleVo.getEnterpriseId()), true);
        }
        return null;
    }
    
    private  List<TreeJson> findAppointRole(String roleId,String systemType,String stfId,String level,Integer enterpriseId, boolean select) throws NumberFormatException, Exception{
    	Set<MenuVo> set=null;
    	List list2=roleDao.createSQLQuery("select bur.role_id from bas_user_role bur " +
    			"inner join bas_role br where bur.role_id=br.role_id and br.role_default_tag='y' and br.enterprise_id="+enterpriseId);
    	String defaultRoleId=null;
    	if(list2!=null&&list2.size()>0){
    		defaultRoleId=list2.get(0).toString();
    	}
    	set=getMenuByRole(Long.parseLong(stfId));
    	List<TreeJson> list = new ArrayList<TreeJson>();
    	  TreeJson json =null;
    	  boolean tag=false;
    	  if(systemType!=null&&!(systemType.equals(Contstants.SYSTEMTYPE.ALL))){
				tag=true;
			}
    	  	if(set!=null&&set.size()>0)
    	  		for (MenuVo menu : set) {
    	  			if(menu.getMenuPid()!=null)
    	  				continue;
    	  			if(tag){
    	  				if(!(menu.getSystemMenu().equals(systemType)))
    	  						continue;
    	  			}
    	  			//System.out.println(menu.getMenuId()+":"+menu.getMenuPid());
            	  json= new TreeJson();
            	  json.setId(String.valueOf(menu.getMenuId()));
            	  json.setText(menu.getMenuName());
            	  json.setIconCls(menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "": "icon-blank");
            	  json.setState(menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "closed": "open");
            	  if(select){
                	  if (roleId != null){
                          json.setChecked(getMenutoRole(menu.getMenuId(),Long.parseLong(roleId)));
                      }
            	  }else{
            		  if(defaultRoleId!=null){
            			  json.setChecked(getMenutoRole(menu.getMenuId(),Long.parseLong(defaultRoleId)));
            		  }else{
            			  json.setChecked(false);
            		  }
            	  }
            	  if (menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES))
            		  findSetOfMenuRoleChild(json, menu.getMenuId().toString(),set,roleId, select,defaultRoleId);
            	  list.add(json);
            	  if(tag)
            		  break;
			}
    	return list;
    }
    
    private void findSetOfMenuRoleChild(TreeJson json,String menuId,Set<MenuVo> set,String roleId, boolean select,String defaultRoleId) throws NumberFormatException, Exception{
    	Set<MenuVo> temp=new HashSet();
    	if(set!=null&&set.size()>0)
    	for (MenuVo menu : set) {
    		if(menu.getMenuPid()!=null&&menu.getMenuPid().toString().equals(menuId)){
    			temp.add(menu);
    		}
    	}
    	List<TreeJson> rows = new ArrayList<TreeJson>();
    	TreeJson json_=null;
    	for (MenuVo menu : temp) {
    		json_= new TreeJson();
	      	json_.setId(String.valueOf(menu.getMenuId()));
	      	json_.setText(menu.getMenuName());
	      	json_.setIconCls(menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "": "icon-blank");
	      	json_.setState(menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "closed": "open");
	      	if(select){
    	      	if (roleId != null){
                    json_.setChecked(getMenutoRole(menu.getMenuId(),Long.parseLong(roleId)));
                }
	      	}else{
	      		if(defaultRoleId!=null){
      			  json_.setChecked(getMenutoRole(menu.getMenuId(),Long.parseLong(defaultRoleId)));
	      		}else{
      			  json_.setChecked(false);
	      		}
            }
	      	if (menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES))
	      	  findSetOfMenuRoleChild(json_, menu.getMenuId().toString(),set,roleId, select,defaultRoleId);
	        rows.add(json_);
		}
    	 json.setChildren(rows);
         json.setState("closed");
    }
    private void getMenuRoleChild(TreeJson json, BasMenuInfo menuVo,
            BasUsers user, RoleVo roleVo) throws Exception
    {
        StringBuffer hql = new StringBuffer();
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR)){
            hql.append("from BasMenuInfo menu where 1=1 and menu.menuPid ="+menuVo.getMenuId()+") ");
        }else{
            hql.append("from BasMenuInfo menu where 1=1 and menu.menuPid ="+menuVo.getMenuId()+") and menu.systemMenu ='"
                            + user.getSystemId() + "'");
        }
        List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString());
        if (basMenuInfos != null && basMenuInfos.size() > 0){
            List<TreeJson> rows = new ArrayList<TreeJson>();
            for (BasMenuInfo menu : basMenuInfos){
                TreeJson json_ = new TreeJson();
                json_.setId(String.valueOf(menu.getMenuId()));
                json_.setText(menu.getMenuName());
                json_.setIconCls(menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "": "icon-blank");
                json_.setState(menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "closed": "open");
                if (roleVo.getRoleId() != null){
                    json_.setChecked(getMenutoRole(menu.getMenuId(), roleVo
                            .getRoleId()));
                }else{
                    json.setChecked(false);
                }
                if (menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES))
                    getMenuRoleChild(json_, menu, user, roleVo);
                rows.add(json_);
            }
            json.setChildren(rows);
            json.setState("closed");
        }
    }

    private boolean getMenutoRole(Long menuId, Long roleId) throws Exception
    {
        return roleDao.getMenutoRole(menuId, roleId);
    }
    /**
     * 保存分布点权限信息
     * */
    
	public boolean saveDistributePurviewMenuRoleTree(RoleVo roleVo) throws Exception {
		if(!(roleVo.getSelecteds()!=null&&roleVo.getSelecteds().length()>0))
			return false;
		if(!(roleVo.getEnterpriseId()!=null&&roleVo.getEnterpriseId().length()>0))
			return false;
    	String usercheck = roleVo.getCheckeds();
    	if(usercheck != null){
        	String[] checkeds = usercheck.split(",");
        	roleDao.deleteOfBatch(roleVo.getSelecteds(),roleVo.getEnterpriseId());
        	if(checkeds!=null || checkeds.length>0){
                roleDao.updateOfBatch(checkeds,roleVo.getSelecteds(),roleVo.getEnterpriseId());
        	}
    	}else{
    	    return false; 
    	}
		return true;
	}

	@Log(moduleName = "角色管理", opertype = "保存、更新角色", content = "角色管理-->保存、更新角色")
    public Msg saveAndUpdata(RoleVo roleVo, BasUsers user) throws Exception
    {
        Msg msg = new Msg();
        BasRoleInfo basRoleInfo = null;
        if(roleVo.getRoleId() != null){
            basRoleInfo = roleDao.get("from BasRoleInfo where roleId = "+roleVo.getRoleId());
            if(basRoleInfo == null){
                msg.setMsg("角色不存在");
                msg.setSuccess(false);
                return msg;
            }
        }else{
            basRoleInfo = new BasRoleInfo();
            basRoleInfo.setEnterpriseInfo(user.getBasStuff().getEnterpriseInfo());  //经办人企业
        }
        basRoleInfo.setRoleDefaultTag("n");
        basRoleInfo.setRoleId(roleVo.getRoleId() != null ? roleVo.getRoleId()
                : null);
        basRoleInfo.setRoleName(roleVo.getRoleName());
        if (roleVo.getSystemType() == null || "".equals(roleVo.getSystemType()))
        {
            basRoleInfo.setSystemType(user.getSystemId());
        }
        else
        {
            basRoleInfo.setSystemType(roleVo.getSystemType());
        }
        
        basRoleInfo.setPerson(roleVo.getPerson() == null ? user.getBasStuff().getStfId() : roleVo.getPerson());
        basRoleInfo.setCreateDate(roleVo.getCreateDate() == null ? new Date()
                : roleVo.getCreateDate());
        basRoleInfo.setRemark(roleVo.getRemark());
        String menuselect = roleVo.getSelecteds();
        boolean tag = true;
        String[] selecteds = null;
        if (menuselect.length() > 0)
        {
            selecteds = menuselect.split(",");
            if (selecteds.length > 0)
            {
        		for (String USER_ID : selecteds){
    			    List<?> list1 = roleDao.createSQLQuery("SELECT a.enterprise_id FROM bas_stuff a WHERE a.STF_ID = '"+USER_ID+"'");
                    if(list1 != null && list1.size() > 0){
                        if(tag){
                            basRoleInfo.setEnterpriseChildId(Integer.parseInt(list1.get(0).toString()));   //子企业
                            tag = false;
                        }
                    }else{
                        msg.setMsg("用户不存在");
                        msg.setSuccess(false);
                        return msg;
                    }
        		}   
            }
        }
        String usercheck = roleVo.getCheckeds();
        String[] checkeds = null;
        if (usercheck.length() > 0)
        {
            checkeds = usercheck.split(",");
            if (checkeds.length > 0)
            {
                for (String MENU_ID : checkeds){
                    List<?> list1 = roleDao.createSQLQuery("SELECT a.menu_id FROM bas_menu_info a WHERE a.menu_id = '"+MENU_ID+"'");
                    if(list1 == null){
                        msg.setMsg("菜单不存在");
                        msg.setSuccess(false);
                        return msg;
                    }
                } 
            }
        }
        
        List<?> list=roleDao.createSQLQuery("select br.role_id from bas_role br where br.role_default_tag='y' and br.enterprise_id="+basRoleInfo.getEnterpriseInfo().getEnterpriseId());
        String roleId=null;
        if(list!=null&&list.size()>0)
            roleId=list.get(0).toString();
        
        if (roleVo.getRoleId() != null){
            roleDao.deleteUserRoleByRoleId(basRoleInfo.getRoleId());
            roleDao.deleteMenuRoleByRoleId(roleVo.getRoleId());
        }
        basRoleInfo.setRoleId(roleDao.saveOrUpdateToId(basRoleInfo));
        if(selecteds != null){
            roleDao.updateOfBatchRoleAndUser(selecteds,basRoleInfo.getRoleId().toString());
        }
        if(checkeds != null){
            roleDao.updateOfBatch(checkeds,basRoleInfo.getRoleId().toString());
        }
        if(roleId != null){
            roleDao.deleteUserRoleByRoleId(Long.parseLong(roleId));
            roleDao.updateOfBatchRoleAndUser(selecteds,roleId);
        }
        msg.setMsg("角色保存成功");
        msg.setSuccess(true);
        setContent("保存、更新【" + roleVo.getRoleName() + "】的角色信息");
        return msg;
    }
    /**
     * 查询用户的对应的角色菜单Code
     * */
    
	public Set<String> getMenuCodeByRole(Long stfId) throws Exception {
    	Set<String> set = new HashSet();
        List<Object[]> list = roleDao.getRoleByUserId(stfId);
        if (list != null && list.size() > 0)
        {
            for (Object[] obj : list)
            {
                Long roleId = Long.parseLong(obj[1].toString());
                List menus = roleDao.getMenuCodeByRoleId(roleId);
                if (menus != null && menus.size() > 0)
                {
                    for (Object objs : menus)
                    {
                    	if(objs != null&&objs.toString().length()>0)
                    		set.add(objs.toString());
                    }
                }
            }
        }
        return set;
	}

	
	public Set<MenuVo> getMenuByRole(Long stfId)
			throws Exception {
		 Set<MenuVo> set = new HashSet<MenuVo>();
	        List<Object[]> list = roleDao.getRoleByUserId(stfId);
	        if (list != null && list.size() > 0)
	        {
	            for (Object[] obj : list)
	            {
	                Long roleId = Long.parseLong(obj[1].toString());
	                List<Object[]> menus = roleDao.getMenuByRoleId(roleId);
	                if (menus != null && menus.size() > 0)
	                {
	                	MenuVo vo =null;
	                	boolean flag=false;
	                    for (Object[] nemu : menus)
	                    {
	                    	for (MenuVo menuVo : set) {
	                    		if(menuVo.getMenuId().toString().equals(nemu[0].toString())){
	                    			flag=true;
	                    			break;
	                    		}
	                		}
	                    	if(flag){
	                    		flag=false;
	                    		continue;
	                    	}
	                        vo= new MenuVo();
	                        vo.setMenuId(nemu[0] != null ? Long.parseLong(nemu[0]
	                                .toString()) : null);
	                        vo.setMenuName(nemu[1] != null ? nemu[1].toString()
	                                : null);
	                        vo.setMenuCode(nemu[2] != null ? nemu[2].toString()
	                                : null);
	                        vo.setMenuPid(nemu[3] != null ? Long.parseLong(nemu[3]
	                                .toString()) : null);
	                        vo.setUrl(nemu[4] != null ? nemu[4].toString() : null);
	                        vo.setChildMenu(nemu[7] != null ? nemu[7].toString() : null);
	                        set.add(vo);
	                    }
	                }
	            }
	        }
	    return set;
	}
    /**
     * 查询指定角色的菜单
     * */
    
	public Set<MenuVo> getMenuCodeByRoleId(Long roleId)
			throws Exception {
    	Set<MenuVo> set = new HashSet<MenuVo>();
    	List<Object[]> menus = roleDao.getMenuByRoleId(roleId);
        if (menus != null && menus.size() > 0)
        {
            for (Object[] nemu : menus)
            {
                MenuVo vo = new MenuVo();
                vo.setMenuId(nemu[0] != null ? Long.parseLong(nemu[0]
                        .toString()) : null);
                vo.setMenuName(nemu[1] != null ? nemu[1].toString()
                        : null);
                vo.setMenuCode(nemu[2] != null ? nemu[2].toString()
                        : null);
                vo.setMenuPid(nemu[3] != null ? Long.parseLong(nemu[3]
                        .toString()) : null);
                vo.setUrl(nemu[4] != null ? nemu[4].toString() : null);
                vo.setChildMenu(nemu[7] != null ? nemu[7].toString() : null);
                vo.setSystemMenu(nemu[8] != null ? nemu[8].toString() : null);
                set.add(vo);
            }
        }
		return set;
	}

	
    public Object userRoleMenuTree(RoleVo roleVo, BasUsers user)
            throws Exception
    {
	    List<TreeJson> list = new ArrayList<TreeJson>();
	    if (roleVo.getSystemLevel().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR)){
	        list = menuRoleTree(roleVo, user);
        }else{
            StringBuffer sql = new StringBuffer();
            String type = "";
            if(roleVo.getSystemType().equals(Contstants.SYSTEMTYPE.ALL)){
                type += "'"+Contstants.SYSTEMTYPE.WEIXIU+"','"+Contstants.SYSTEMTYPE.XIAOSHOU+"'";
            }else{
                type += roleVo.getSystemType();
            }
            sql.append("SELECT m.menu_id, m.menu_name, m.childMenu FROM  bas_menu_role rm, bas_menu_info m, bas_stuff f, bas_user_role ur ");
            sql.append("WHERE  1=1 AND rm.menu_id = m.menu_id AND ur.USER_ID = f.STF_ID AND ur.ROLE_ID = rm.role_id ");
            sql.append("AND (m.menu_pid IS NULL OR m.menu_pid = '') AND m.systemMenu IN("+ type + ") AND f.STF_ID = '"+ roleVo.getPerson() + "'");
            List<Object[]> basMenuInfos = menuDao.createSQLQuery(sql.toString());
            if (basMenuInfos != null && basMenuInfos.size() > 0)
            {
                for (Object[] objs : basMenuInfos)
                {
                    TreeJson json = new TreeJson();
                    String menuId = objs[0] != null ? objs[0].toString() : null;
                    String menuName = objs[1] != null ? objs[1].toString() : null;
                    String childMenu = objs[2] != null ? objs[2].toString() : null;
                    json.setId(menuId);
                    json.setText(menuName);
                    json.setIconCls(childMenu
                            .equals(Contstants.CHILDMENU.CHILDMENU_YES) ? ""
                            : "icon-blank");
                    json.setState(childMenu
                            .equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "closed"
                            : "open");
                    if (childMenu.equals(Contstants.CHILDMENU.CHILDMENU_YES))
                        getUserRoleMenuChild(json, menuId, roleVo, user);
                    list.add(json);
                }
            }
        }
        return list;
    }

    private void getUserRoleMenuChild(TreeJson json, String menuPid,
            RoleVo roleVo, BasUsers user) throws Exception
    {
        StringBuffer sql = new StringBuffer();
        sql
                .append("SELECT m.menu_id, m.menu_name, m.childMenu FROM  bas_menu_role rm, bas_menu_info m, bas_stuff f, bas_user_role ur ");
        sql
                .append("WHERE  1=1 AND rm.menu_id = m.menu_id AND ur.USER_ID = f.STF_ID AND ur.ROLE_ID = rm.role_id ");
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            sql.append("AND m.menu_pid = '" + menuPid + "' AND f.STF_ID = '"
                    + roleVo.getPerson() + "'");
        }
        else
        {
            sql.append("AND m.menu_pid = '" + menuPid + "' AND m.systemMenu ='"
                    + roleVo.getSystemType() + "' AND f.STF_ID = '"
                    + roleVo.getPerson() + "'");
        }
        List<Object[]> basMenuInfos = menuDao.createSQLQuery(sql.toString());
        if (basMenuInfos != null && basMenuInfos.size() > 0)
        {
            List<TreeJson> rows = new ArrayList<TreeJson>();
            for (Object[] objs : basMenuInfos)
            {
                TreeJson json_ = new TreeJson();
                String menuId = objs[0] != null ? objs[0].toString() : null;
                String menuName = objs[1] != null ? objs[1].toString() : null;
                String childMenu = objs[2] != null ? objs[2].toString() : null;
                json_.setId(menuId);
                json_.setText(menuName);
                json_.setIconCls(childMenu
                        .equals(Contstants.CHILDMENU.CHILDMENU_YES) ? ""
                        : "icon-blank");
                json_.setState(childMenu
                        .equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "closed"
                        : "open");
                if (childMenu.equals(Contstants.CHILDMENU.CHILDMENU_YES))
                    getUserRoleMenuChild(json_, menuId, roleVo, user);
                rows.add(json_);
            }
            json.setChildren(rows);
            json.setState("closed");
        }
    }

    
    public Msg userRoleInfo(RoleVo roleVo) throws Exception
    {
        Msg msg = new Msg();
        StringBuffer sql = new StringBuffer();
        sql
                .append("SELECT r.role_name FROM bas_user_role ur, bas_role r WHERE 1=1 ");
        sql.append("AND ur.ROLE_ID = r.role_id AND ur.USER_ID = '"
                + roleVo.getPerson() + "'");
        List<Object[]> userRoleInfo = roleDao.createSQLQuery(sql.toString());
        msg.setSuccess(true);
        String roleName = "";
        if (userRoleInfo != null && userRoleInfo.size() > 0)
        {
            for (Object objs : userRoleInfo)
            {
                if (!roleName.equals(""))
                    roleName += ",";
                roleName += objs.toString();
            }
        }
        msg.setMsg(roleName);
        return msg;
    }
    /**
     * 指定角色中是否存在用户
     * */
	
	public boolean isExistsUserOfRole(Long roleId) throws Exception {
		List list=roleDao.createSQLQuery("select * from bas_user_role where role_id="+roleId);
		if(list!=null&&list.size()>0)
			return true;
		else{
			return false;
		}
	}
	/**
	 * 设置系统默认角色
	 * */
	
	public boolean modifyDefaultRole(RoleVo roleVo) throws Exception {
		if(roleVo.getRoleId()==null)
			return false;
		String sql="SELECT bu.stf_id FROM bas_users bu INNER JOIN bas_stuff bs ON bs.stf_id=bu.stf_id AND bs.STF_YES='"+Contstants.SYSTEMUSER.STFYES
						+"' WHERE bu.leval_!='"+Contstants.EMPLOYEELEVEL.ADMIN+"' AND bs.enterprise_id="+roleVo.getEnterpriseId();
		List list=roleDao.createSQLQuery(sql);
		List list2=roleDao.createSQLQuery("select bur.role_id from bas_user_role bur " +
				"inner join bas_role br where bur.role_id=br.role_id and br.role_default_tag='y' and br.enterprise_id="+roleVo.getEnterpriseId());
		if(list2!=null&&list2.size()>0){
			roleDao.executeSQL("delete from bas_user_role where role_id="+list2.get(0).toString());
			roleDao.executeSQL("update bas_role set role_default_tag='n' where role_id="+list2.get(0).toString());
		}
		List tempList=null;
		if(list!=null&&list.size()>0)
			for (Object obj : list) {
				if(!isExistsBasStuffOfRole(tempList, roleVo.getRoleId().toString(), obj.toString())){
					roleDao.executeSQL("insert into bas_user_role values("+obj.toString()+","+roleVo.getRoleId().toString()+")");
				}
			}
		roleDao.executeSQL("update bas_role set role_default_tag='y' where role_id="+roleVo.getRoleId().toString());
		return true;
	}
    private boolean isExistsBasStuffOfRole(List list,String roleId,String stfId)throws Exception{
    	list=null;
    	list=roleDao.createSQLQuery("select * from bas_user_role bur where bur.role_id="+roleId+" and bur.user_id="+stfId);
    	if(list!=null&&list.size()>0)
    		return true;
    	return false;
    }
}