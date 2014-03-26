package com.syuesoft.qx.daoimpi;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.LoginError;
import com.syuesoft.qx.dao.LoginErrorDao;

@Repository
public class LoginErrorDaoImpl extends BaseDaoImpl<LoginError> implements
        LoginErrorDao
{

}