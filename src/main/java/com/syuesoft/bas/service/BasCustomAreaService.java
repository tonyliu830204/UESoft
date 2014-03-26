package com.syuesoft.bas.service;

import java.util.List;
import com.syuesoft.model.BasCustomArea;

public interface BasCustomAreaService
{

    public boolean add(BasCustomArea basCustomArea) throws Exception;

    public void delete(BasCustomArea basCustomArea) throws Exception;

    public boolean update(BasCustomArea basCustomArea) throws Exception;

    public List<BasCustomArea> findAll(int page, int rows, String order,
            String sort,final int enterprise_id) throws Exception;

    public List<BasCustomArea> getTotle(BasCustomArea basCustomArea) throws Exception;

}
