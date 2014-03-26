package com.syuesoft.menu.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasMenuInfo;
import com.syuesoft.menu.dao.IMenuDao;

/**
 * 菜单dao
 * 
 * @author HeXin
 * 
 */
@Repository("menuDaoImpl")
public class MenuDaoImpl extends BaseDaoImpl<BasMenuInfo> implements IMenuDao
{

}