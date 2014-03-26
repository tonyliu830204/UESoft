package com.syuesoft.frt.dao;


import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.FrtCustom;

public interface FrtCustomDao extends BaseDaoI<FrtCustom>
{

	public boolean changeCustomId(String customId,String customIdOld, String customName)throws Exception;

}
