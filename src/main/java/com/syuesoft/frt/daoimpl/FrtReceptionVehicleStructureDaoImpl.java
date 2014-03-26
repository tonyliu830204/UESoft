package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtReceptionVehicleStructureDao;
import com.syuesoft.model.FrtReceptionVehicleStructure;

@Repository("frtReceptionVehicleStructureDao")
public class FrtReceptionVehicleStructureDaoImpl extends
        BaseDaoImpl<FrtReceptionVehicleStructure> implements
        FrtReceptionVehicleStructureDao
{
}
