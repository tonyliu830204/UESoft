package com.syuesoft.bas.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipLevelDao;
import com.syuesoft.bas.service.BasVipLevelService;
import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.model.BasVipLevel;

/**
 * 会员等级
 * 
 * @author HeXin
 * 
 */
@Service("basVipLevelService")
public class BasVipLevelServiceImpl extends BaseLogServiceImpl implements
        BasVipLevelService
{
    @Autowired
    private BasVipLevelDao basVipLevelDao;

    
    @Log(moduleName = "基础资料", opertype = "新增会员等级", content = "基础资料-->新增会员等级")
    public void add(BasVipLevelVO bvlVO) throws Exception
    {
        BasVipLevel bvl = new BasVipLevel();
        bvl.setVipLevelId(bvlVO.getVipLevelId());
        bvl.setVipLevelName(bvlVO.getVipLevelName());
        bvl.setVipLevelNote(bvlVO.getVipLevelNote());
        bvl.setEnterpriseId(bvlVO.getEnterpriseId());
        basVipLevelDao.add(bvl);
        setContent("基础资料-->新增会员等级,会员等级名称:" + bvlVO.getVipLevelName());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除会员等级", content = "基础资料-->删除会员等级")
    public void delete(BasVipLevelVO bvlVO) throws Exception
    {
        BasVipLevel bvl = basVipLevelDao.get(BasVipLevel.class, bvlVO.getVipLevelId());
        if(bvl!=null&&!bvl.equals("")){
        	  basVipLevelDao.delte(bvl);
              setContent("基础资料-->删除会员等级,会员等级名称:" + bvl.getVipLevelName());
        }
//        bvl.setVipLevelId(bvlVO.getVipLevelId());
//        bvl.setVipLevelName(bvlVO.getVipLevelName());
//        bvl.setVipLevelNote(bvlVO.getVipLevelNote());
      
    }

    
    public List<BasVipLevelVO> findAll(int page, int rows, String order,
            String sort,int enterprise_id) throws Exception
    {
        return basVipLevelDao.findAll(page, rows, order, sort,enterprise_id);
    }

    
    public int getTotle(int enterprise_id) throws Exception
    {
        return basVipLevelDao.getTotle(enterprise_id);
    }

    
    @Log(moduleName = "基础资料", opertype = "更新会员等级", content = "基础资料-->更新会员等级")
    public void update(BasVipLevelVO bvlVO) throws Exception
    {
        BasVipLevel bvl = new BasVipLevel();
        bvl.setVipLevelId(bvlVO.getVipLevelId());
        bvl.setVipLevelName(bvlVO.getVipLevelName());
        bvl.setVipLevelNote(bvlVO.getVipLevelNote());
        bvl.setEnterpriseId(bvlVO.getEnterpriseId());
        basVipLevelDao.update(bvl);
        setContent("基础资料-->更新会员等级,会员等级名称:" + bvlVO.getVipLevelName());
    }

    
    public boolean getByName(BasVipLevelVO bvlVO) throws Exception
    {
        return basVipLevelDao.getByName(bvlVO);
    }

}
