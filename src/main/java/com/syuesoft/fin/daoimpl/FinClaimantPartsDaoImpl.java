package com.syuesoft.fin.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.dao.FinClaimantPartsDao;
import com.syuesoft.fin.vo.WorkOrderPartsQueryVo;
import com.syuesoft.model.FinClaimantParts;

/**
 * 索赔结算配件DaoImpl
 * 
 * @author SuMing
 */
@Repository("finClaimantPartsDao")
public class FinClaimantPartsDaoImpl extends BaseDaoImpl<FinClaimantParts>
        implements FinClaimantPartsDao
{

}
