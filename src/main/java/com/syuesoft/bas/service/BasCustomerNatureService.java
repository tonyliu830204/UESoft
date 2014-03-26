package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasCustomNature;

public interface BasCustomerNatureService
{

    public boolean add(BasCustomNature BasCustomNature) throws Exception;

    public void delete(BasCustomNature basCustomNature) throws Exception;

    public boolean update(BasCustomNature basCustomNature) throws Exception;

    public List<BasCustomNature> findAll(int page, int rows, String sort,
            String order,int enterprise_id) throws Exception;

    public List<BasCustomNature> getTotle(BasCustomNature basCustomNature) throws Exception;

}
