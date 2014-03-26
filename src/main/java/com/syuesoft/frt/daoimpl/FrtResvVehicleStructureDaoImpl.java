package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtResvVehicleStructureDao;
import com.syuesoft.model.FrtResvVehicleStructure;

@Repository("frtResvVehicleStructureDao")
public class FrtResvVehicleStructureDaoImpl extends
        BaseDaoImpl<FrtResvVehicleStructure> implements
        FrtResvVehicleStructureDao
{
}
