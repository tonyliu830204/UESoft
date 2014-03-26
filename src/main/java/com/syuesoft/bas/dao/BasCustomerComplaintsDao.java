package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasCustomerComplaints;
import com.syuesoft.util.Json;

public interface BasCustomerComplaintsDao extends
        BaseDaoI<BasCustomerComplaints>
{
    public boolean Add(BasCustomerComplaints basCustomerComplaints)
            throws Exception;

    public void Delete(BasCustomerComplaints basCustomerComplaints)
            throws Exception;

    public boolean Update(BasCustomerComplaints basCustomerComplaints)
            throws Exception;

    public Json findAll(int page, int rows, String sort,
            String order,final int enterprise_id) throws Exception;

    public List<BasCustomerComplaints> getTotle() throws Exception;

    public List<BasCustomerComplaints> findByCondition(int page, int rows,
            BasCustomerComplaints basCustomerComplaints) throws Exception;
}
