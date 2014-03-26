package com.syuesoft.bas.serviceimpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipActivitiesProjectDao;
import com.syuesoft.bas.service.BasVipActivitiesProjectService;
import com.syuesoft.base.vo.BasVipActivitiesProjectVO;
import com.syuesoft.model.BasVipActivitiesProject;

@Service("basVipActivitiesProjectService")
public class BasVipActivitiesProjectServiceImpl extends BaseLogServiceImpl
        implements BasVipActivitiesProjectService
{
    @Autowired
    private BasVipActivitiesProjectDao basVipActivitiesProjectDao;

    
    /**
     * 添加会员活动项目
     * */
    @Log(moduleName = "基础资料", opertype = "新增会员活动项目", content = "基础资料-->新增会员活动项目")
    public void add(BasVipActivitiesProjectVO basVipActivitiesProjectVO)
            throws Exception
    {
        BasVipActivitiesProject bvp = new BasVipActivitiesProject();
        bvp
                .setVipActJoinPcount(basVipActivitiesProjectVO
                        .getVipActJoinPcount());
        // 判断会员活动日期是否为空
        if (basVipActivitiesProjectVO.getVipActProDate() == null)
        {
            bvp.setVipActProDate(null);
        }
        else
        {
            bvp.setVipActProDate(new Timestamp(basVipActivitiesProjectVO
                    .getVipActProDate().getTime()));
        }
        bvp.setVipActProId(basVipActivitiesProjectVO.getVipActProId());
        bvp.setVipActProName(basVipActivitiesProjectVO.getVipActProName());
        bvp.setVipActProNote(basVipActivitiesProjectVO.getVipActProNote());
        bvp.setVipActReward(basVipActivitiesProjectVO.getVipActReward());
        bvp.setEnterpriseId(basVipActivitiesProjectVO.getEnterpriseId());
        basVipActivitiesProjectDao.add(bvp);
        setContent("基础资料-->新增会员活动项目,会员活动项目名称:"
                + basVipActivitiesProjectVO.getVipActProName());
    }

    
    /**
     * 删除会员活动项目
     * */
    @Log(moduleName = "基础资料", opertype = "删除会员活动项目", content = "基础资料-->删除会员活动项目")
    public void delete(BasVipActivitiesProjectVO basVipActivitiesProjectVO)
            throws Exception
    {
//        // TODO Auto-generated method stub
//        BasVipActivitiesProject bvp = new BasVipActivitiesProject();
//        bvp.setVipActJoinPcount(basVipActivitiesProjectVO.getVipActJoinPcount());
//        // 判断会员活动日期是否为空
//        if (basVipActivitiesProjectVO.getVipActProDate() == null)
//        {
//            bvp.setVipActProDate(null);
//        }
//        else
//        {
//            bvp.setVipActProDate(new Timestamp(basVipActivitiesProjectVO
//                    .getVipActProDate().getTime()));
//        }
//        bvp.setVipActProId(basVipActivitiesProjectVO.getVipActProId());
//        bvp.setVipActProName(basVipActivitiesProjectVO.getVipActProName());
//        bvp.setVipActProNote(basVipActivitiesProjectVO.getVipActProNote());
//        bvp.setVipActReward(basVipActivitiesProjectVO.getVipActReward());
    	BasVipActivitiesProject bvp =basVipActivitiesProjectDao.get(BasVipActivitiesProject.class,basVipActivitiesProjectVO.getVipActProId());
    	if(bvp!=null&&!bvp.equals(""))
    	{
    		 basVipActivitiesProjectDao.delte(bvp);
    	        setContent("基础资料-->删除会员活动项目,会员活动项目名称:"+ bvp.getVipActProName());
    	}
       
    }

    
    /**
     * 分页查询所有会员活动折扣
     * */
    public List<BasVipActivitiesProjectVO> findAll(int page, int rows,
            String order, String sort,int enterprise_id  ) throws Exception
    {
        // TODO Auto-generated method stub
        return basVipActivitiesProjectDao.findAll(page, rows, order, sort,enterprise_id);
    }

    
    /**
     * 获取所有记录数
     * */
    public int getTotle(int enterprise_id) throws Exception
    {
        // TODO Auto-generated method stub
        return basVipActivitiesProjectDao.getTotle(enterprise_id);
    }

    
    /**
     * 编辑会员活动折扣
     * */
    @Log(moduleName = "基础资料", opertype = "修改会员活动项目", content = "基础资料-->修改会员活动项目")
    public void update(BasVipActivitiesProjectVO basVipActivitiesProjectVO)
            throws Exception
    {
        BasVipActivitiesProject bvp = basVipActivitiesProjectDao.get(BasVipActivitiesProject.class, basVipActivitiesProjectVO.getVipActProId());
        bvp.setVipActJoinPcount(basVipActivitiesProjectVO
                        .getVipActJoinPcount());
        // 判断会员活动日期是否为空
        if (basVipActivitiesProjectVO.getVipActProDate() != null)
            bvp.setVipActProDate(new Timestamp(basVipActivitiesProjectVO.getVipActProDate().getTime()));
        bvp.setVipActProName(basVipActivitiesProjectVO.getVipActProName());
        bvp.setVipActProNote(basVipActivitiesProjectVO.getVipActProNote());
        bvp.setVipActReward(basVipActivitiesProjectVO.getVipActReward());
        basVipActivitiesProjectDao.update(bvp);
        setContent("基础资料-->修改会员活动项目,会员活动项目名称:"
                + basVipActivitiesProjectVO.getVipActProName());
    }

    /**
     * 根据会员活动名称获取会员活动项目名称
     * 
     * @name 会员活动名称
     * */
    
    public boolean getByName(BasVipActivitiesProjectVO basVipActivitiesProjectVO)
            throws Exception
    {
        return basVipActivitiesProjectDao.getByName(basVipActivitiesProjectVO);
    }
}
