package com.syuesoft.bas.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.FbkDcserveyNameDao;
import com.syuesoft.bas.service.FbkDcserveyNameService;
import com.syuesoft.model.FbkDcserveyName;
import com.syuesoft.util.Json;

/**
 * 客户回访项目
 * @author HeXin
 */
@Service("fbkDcserveyNameService")
public class FbkDcserveyNameServiceImpl extends BaseLogServiceImpl implements
        FbkDcserveyNameService
{

    @Autowired
    private FbkDcserveyNameDao fbkDcserveyNameDao;

    
    @Log(moduleName = "基础资料", opertype = "新增客户回访项目", content = "基础资料-->新增客户回访项目")
    public boolean add(FbkDcserveyName fbkDcserveyName) throws Exception
    {
        boolean tag = fbkDcserveyNameDao.Add(fbkDcserveyName);
        setContent("基础资料-->新增客户回访项目,客户回访项目名称:"
                + fbkDcserveyName.getServeyName());
        return tag;
    }

    
    @Log(moduleName = "基础资料", opertype = "删除客户回访项目", content = "基础资料-->删除客户回访项目")
    public void delete(FbkDcserveyName fbkDcserveyName) throws Exception
    {
    	FbkDcserveyName fdn=fbkDcserveyNameDao.get(FbkDcserveyName.class,fbkDcserveyName.getDcNameId());
        fbkDcserveyNameDao.delete(fdn);
        setContent("基础资料-->删除客户回访项目,客户回访项目名称:"+fdn.getServeyName());
    }

    
    @Log(moduleName = "基础资料", opertype = "更新客户回访项目", content = "基础资料-->更新客户回访项目")
    public boolean update(FbkDcserveyName fbkDcserveyName) throws Exception
    {
        boolean tag = fbkDcserveyNameDao.Update(fbkDcserveyName);
        setContent("基础资料-->更新客户回访项目,客户回访项目名称:"
                + fbkDcserveyName.getServeyName());
        return tag;
    }

    
    public Json findAll(int page, int rows, String sort,
            String order,final int enterprise_id) throws Exception
    {
        return fbkDcserveyNameDao.findAll(page, rows, order, sort,enterprise_id);
    }

}
