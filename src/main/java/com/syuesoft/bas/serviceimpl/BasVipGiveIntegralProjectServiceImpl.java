package com.syuesoft.bas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipGiveIntegralProjectDao;
import com.syuesoft.bas.service.BasVipGiveIntegralProjectService;
import com.syuesoft.base.vo.BasVipGiveIntegralProjectVO;
import com.syuesoft.model.BasVipGiveIntegralProject;

/**
 * 会员赠送积分
 * 
 * @author HeXin
 * 
 */
@Service("basVipGiveIntegralProjectService")
public class BasVipGiveIntegralProjectServiceImpl extends BaseLogServiceImpl
        implements BasVipGiveIntegralProjectService
{
    @Autowired
    private BasVipGiveIntegralProjectDao basVipGiveIntegralProjectDao;

    
    /**
     * 	添加会员赠送项目
     * */
    @Log(moduleName = "基础资料", opertype = "新增会员赠送积分", content = "基础资料-->新增会员赠送积分")
    public void add(BasVipGiveIntegralProjectVO basVipGiveIntegralProjectVO)
            throws Exception
    {
        // 实例化赠送积分项目并给属性赋值
        BasVipGiveIntegralProject bgp = new BasVipGiveIntegralProject();
        bgp.setGiveInteProId(basVipGiveIntegralProjectVO.getGivInteProId());
        bgp.setGiveInteProName(basVipGiveIntegralProjectVO.getGivInteProName());
        bgp.setGiveInteNum(basVipGiveIntegralProjectVO.getGivInteNum());
        bgp.setEnterpriseId(basVipGiveIntegralProjectVO.getEnterpriseId());
        basVipGiveIntegralProjectDao.add(bgp);
        setContent("基础资料-->新增会员赠送积分,会员赠送积分名称:"
                + basVipGiveIntegralProjectVO.getGivInteProName());
    }

    
    /**
     * 	删除 积分赠送项目
     * */
    @Log(moduleName = "基础资料", opertype = "删除会员赠送积分", content = "基础资料-->删除会员赠送积分")
    public void delete(BasVipGiveIntegralProjectVO basVipGiveIntegralProjectVO)
            throws Exception
    {
        // 实例化赠送积分项目并给属性赋值
        BasVipGiveIntegralProject bgp =basVipGiveIntegralProjectDao.get(BasVipGiveIntegralProject.class, basVipGiveIntegralProjectVO.getGivInteProId());
       /* bgp.setGiveInteProId(basVipGiveIntegralProjectVO.getGivInteProId());
        bgp.setGiveInteProName(basVipGiveIntegralProjectVO.getGivInteProName());
        bgp.setGiveInteNum(basVipGiveIntegralProjectVO.getGivInteNum());*/
        if(bgp!=null&&!bgp.equals("")){
        	  basVipGiveIntegralProjectDao.delte(bgp);
              setContent("基础资料-->删除会员赠送积分,会员赠送积分名称:"+ bgp.getGiveInteProName());
        }
      
    }

    
    public List<BasVipGiveIntegralProjectVO> findAll(int page, int rows,
            String order, String sort,int enterprise_id ) throws Exception
    {
        return basVipGiveIntegralProjectDao.findAll(page, rows, order, sort,enterprise_id);
    }

    
    /**
     * 	根据项目编号获取赠送积分项目
     * */
    public BasVipGiveIntegralProject getById(int id) throws Exception
    {
        return basVipGiveIntegralProjectDao.getById(id);
    }

    
    /**
     * 	获取总记录数
     * */
    public int getTotle(int enterprise_id  ) throws Exception
    {
        return basVipGiveIntegralProjectDao.getTotle(enterprise_id);
    }

    
    /**
     * 	编辑会员赠送积分项目
     * */
    @Log(moduleName = "基础资料", opertype = "修改会员赠送积分", content = "基础资料-->修改会员赠送积分")
    public void update(BasVipGiveIntegralProjectVO basVipGiveIntegralProjectVO)
            throws Exception
    {
        // 实例化赠送积分项目并给属性赋值
        BasVipGiveIntegralProject bgp = new BasVipGiveIntegralProject();
        bgp.setGiveInteProId(basVipGiveIntegralProjectVO.getGivInteProId());
        bgp.setGiveInteProName(basVipGiveIntegralProjectVO.getGivInteProName());
        bgp.setGiveInteNum(basVipGiveIntegralProjectVO.getGivInteNum());
        bgp.setEnterpriseId(basVipGiveIntegralProjectVO.getEnterpriseId());
        basVipGiveIntegralProjectDao.update(bgp);
        setContent("基础资料-->修改会员赠送积分,会员赠送积分名称:"
                + basVipGiveIntegralProjectVO.getGivInteProName());
    }

    
    public boolean getByName(
            BasVipGiveIntegralProjectVO basVipGiveIntegralProjectVO)
            throws Exception
    {
        return basVipGiveIntegralProjectDao
                .getByName(basVipGiveIntegralProjectVO);
    }

}
