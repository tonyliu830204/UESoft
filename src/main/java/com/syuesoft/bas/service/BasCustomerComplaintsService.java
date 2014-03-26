package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasCustomerComplaints;
import com.syuesoft.util.Json;

public interface BasCustomerComplaintsService
{

    public boolean add(BasCustomerComplaints basCustomerComplaints)
            throws Exception;

    public void delete(BasCustomerComplaints basCustomerComplaints)
            throws Exception;

    public boolean update(BasCustomerComplaints basCustomerComplaints)
            throws Exception;

    public Json findAll(int page, int rows, String sort,
            String order,int enterprise_id) throws Exception;

    public List<BasCustomerComplaints> getTotle() throws Exception;

    public List<BasCustomerComplaints> findByCondition(int page, int rows,
            BasCustomerComplaints basCustomerComplaints) throws Exception;

}
