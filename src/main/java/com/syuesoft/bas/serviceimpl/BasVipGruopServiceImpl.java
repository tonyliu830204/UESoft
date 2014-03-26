package com.syuesoft.bas.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipGruopDao;
import com.syuesoft.bas.service.BasVipGruopService;
import com.syuesoft.base.vo.BasVipGruopVO;
import com.syuesoft.model.BasVipGruop;

/**
 * 会员分组
 * 
 * @author HeXin
 * 
 */
@Service("basVipGruopService")
public class BasVipGruopServiceImpl extends BaseLogServiceImpl implements
        BasVipGruopService
{
    @Autowired
    private BasVipGruopDao basVipGruopDao;

    
    /**
     * 添加会员分组
     * */
    @Log(moduleName = "基础资料", opertype = "新增会员分组", content = "基础资料-->新增会员分组")
    public void add(BasVipGruopVO basVipGruopVO) throws Exception
    {
        BasVipGruop bvg = new BasVipGruop();
        bvg.setVipGruopId(basVipGruopVO.getVipGruopId());
        bvg.setVipGruopName(basVipGruopVO.getVipGruopName());
        bvg.setVipGruopNote(basVipGruopVO.getVipGruopNote());
        bvg.setEnterpriseId(basVipGruopVO.getEnterpriseId());
        basVipGruopDao.add(bvg);
        setContent("基础资料-->新增会员分组,会员分组名称:" + basVipGruopVO.getVipGruopName());
    }

    
    /**
     * 删除会员分组
     * */
    @Log(moduleName = "基础资料", opertype = "删除会员分组", content = "基础资料-->删除会员分组")
    public void delete(BasVipGruopVO basVipGruopVO) throws Exception
    {
        BasVipGruop bvg = basVipGruopDao.get(BasVipGruop.class, basVipGruopVO.getVipGruopId());
//        bvg.setVipGruopId(basVipGruopVO.getVipGruopId());
//        bvg.setVipGruopName(basVipGruopVO.getVipGruopName());
//        bvg.setVipGruopNote(basVipGruopVO.getVipGruopNote());
        if(bvg!=null&&!bvg.equals("")){
        	basVipGruopDao.delte(bvg);
            setContent("基础资料-->删除会员分组,会员分组名称:" + bvg.getVipGruopName());
        }
    }

    
    /**
     * 分页获取所有会员分组
     * */
    public List<BasVipGruopVO> findAll(int page, int rows, String order,
            String sort,int  enterprise_id) throws Exception
    {
        return basVipGruopDao.findAll(page, rows, order, sort,enterprise_id);
    }

    
    /**
     * 获取总记录数
     * */
    public int getTotle(int enterprise_id) throws Exception
    {
        return basVipGruopDao.getTotle(enterprise_id);
    }

    
    /**
     * 编辑会员分组
     * */
    @Log(moduleName = "基础资料", opertype = "修改会员分组", content = "基础资料-->修改会员分组")
    public void update(BasVipGruopVO basVipGruopVO) throws Exception
    {
        BasVipGruop bvg = basVipGruopDao.get(BasVipGruop.class, basVipGruopVO.getVipGruopId());
        bvg.setVipGruopName(basVipGruopVO.getVipGruopName());
        bvg.setVipGruopNote(basVipGruopVO.getVipGruopNote());
        basVipGruopDao.update(bvg);
        setContent("基础资料-->修改会员分组,会员分组名称:" + basVipGruopVO.getVipGruopName());
    }

    /**
     * 根据会员分组名称获取会员分组信息
     * 
     * @name 会员分组名称
     * */
    
    public boolean getByName(BasVipGruopVO basVipGruopVO) throws Exception
    {
        return basVipGruopDao.getByName(basVipGruopVO);
    }
}
