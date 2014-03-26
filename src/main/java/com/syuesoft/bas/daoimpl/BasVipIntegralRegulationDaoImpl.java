package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasVipIntegralRegulationDao;
import com.syuesoft.base.vo.BasVipIntegralRegulationVO;
import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.base.vo.ReptypeVo;
import com.syuesoft.model.BasRepairType;
import com.syuesoft.model.BasVipIntegralRegulation;
import com.syuesoft.model.BasVipLevel;

/**
 * @author mulangtao
 * */
@Repository("basVipIntegralRegulationDao")
public class BasVipIntegralRegulationDaoImpl extends
        BaseDaoImpl<BasVipIntegralRegulation> implements
        BasVipIntegralRegulationDao
{

    
    /**
     * 添加 会员积分规则
     * */
    public void add(BasVipIntegralRegulation bvr) throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(bvr);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    /**
     * 删除会员积分规则
     * */
    public void delte(BasVipIntegralRegulationVO bvrVO) throws Exception
    {
        try
        {
            this
                    .deleteByHql("DELETE FROM BasVipIntegralRegulation where vipInteReg = "
                            + bvrVO.getVipInteReg());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    
    /**
     * 获取所有会员积分规则
     * */
    public List<BasVipIntegralRegulationVO> findAll(final int page,
            final int rows, final String order, final String sort,final int enterprise_id)
            throws Exception
    {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<BasVipIntegralRegulationVO>>()
        {
            
            public List<BasVipIntegralRegulationVO> doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                String hql = "select * from (SELECT  bvi.VIP_INTE_REG as vipInteReg,bvl.VIP_LEVEL_ID as vipLevelId,bvl.VIP_LEVEL_NAME as vipLevelName, bvi.REPTYP_ID as reptypId,bvi.BEGIN_AMOUNT as beginAmount"+
				" , bvi.JF_AMONT as jfAmont, bvi.SCORE as score,bvi.enterprise_id as enterpriseId,  (SELECT  c.rept_name  FROM Reptype c  WHERE  c.rept_id=reptypId)  AS  reptypName FROM bas_vip_integral_regulation AS bvi,bas_vip_level AS  bvl"+
				"  WHERE bvi.VIP_LEVEL_ID=bvl.VIP_LEVEL_ID )as a ";
                //"  WHERE bvi.VIP_LEVEL_ID=bvl.VIP_LEVEL_ID )as a  where enterpriseId="+enterprise_id;
                // 判断是否进行排序
                if (order != null && sort != null)
                    hql += "  order by " + sort + " " + order;
                Query query = session.createSQLQuery(hql);
                // 计算分页开始记录
                int beginRow = (page - 1) * rows;
                query.setFirstResult(beginRow); // 设置分页开始
                query.setMaxResults(rows); // 设置每页显示记录
                List list = query.list();
                List<BasVipIntegralRegulationVO> list1 = new ArrayList<BasVipIntegralRegulationVO>();
                Object[] obj = null;
                BasVipIntegralRegulationVO bvr = null;
                // 编辑查询结果并赋值给会员积分规则
                if (list != null && list.size() > 0)
                    for (int i = 0; i < list.size(); i++)
                    {
                        obj = (Object[]) list.get(i);
                        bvr = new BasVipIntegralRegulationVO();
                        if (obj[0] != null && obj[0].toString().length() > 0)
                            bvr.setVipInteReg(Integer.parseInt(obj[0]
                                    .toString()));
                        if (obj[1] != null && obj[1].toString().length() > 0)
                            bvr.setVipLevelId(Integer.parseInt(obj[1]
                                    .toString()));
                        if (obj[2] != null && obj[2].toString().length() > 0)
                            bvr.setVipLevelName(obj[2].toString());
                        if (obj[3] != null && obj[3].toString().length() > 0)
                            bvr
                                    .setReptypId(Integer.parseInt(obj[3]
                                            .toString()));
                        if (obj[8] != null && obj[8].toString().length() > 0)
                            bvr.setReptypName(obj[8].toString());
                        if (obj[5] != null && obj[5].toString().length() > 0)
                            bvr.setBeginAmount(Integer.parseInt(obj[5]
                                    .toString()));
                        if (obj[6] != null && obj[6].toString().length() > 0)
                            bvr.setJfAmont(Integer.parseInt(obj[6].toString()));
                        if (obj[7] != null && obj[7].toString().length() > 0)
                            bvr.setScore(Integer.parseInt(obj[7].toString()));
                        list1.add(bvr);
                    }
                return list1;
            }
        });
    }

    
    /**
     * 获取所有维修分类
     * */
    public List<ReptypeVo> findAllRepairType(BasVipIntegralRegulationVO bvrVO) throws Exception
    {
        String hql = "select reptId,reptName,workCreditsRate,partCreditsRate,sumCreditsRate,memo from Reptype where enterpriseId="+bvrVO.getEnterpriseId();
        try
        {
            List list = this.getHibernateTemplate().find(hql);
            List<ReptypeVo> list1 = new ArrayList<ReptypeVo>();
            Object[] obj = null;
            for (int i = 0; i < list.size(); i++)
            {
                obj = (Object[]) list.get(i);
                ReptypeVo rv = new ReptypeVo();
                rv.setReptId(obj[0].toString());
                rv.setReptName(obj[1].toString());
                if (obj[2] == null)
                {
                    obj[2] = 0;
                }
                rv.setWorkCreditsRate(obj[2].toString());
                if (obj[3] == null)
                {
                    obj[3] = 0;
                }
                rv.setPartCreditsRate(obj[3].toString());
                if (obj[4] == null)
                {
                    obj[4] = 0;
                }
                rv.setSumCreditsRate(obj[4].toString());
                if (obj[5] == null)
                {
                    obj[5] = "";
                }
                rv.setMemo(obj[5].toString());
                list1.add(rv);
            }
            return list1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * 获取所有会员等级
     * */
    public List<BasVipLevelVO> findAllVipLevel(BasVipIntegralRegulationVO bvrVO) throws Exception
    {
        String hql = "select new com.syuesoft.base.vo.BasVipLevelVO(bvl.vipLevelId,bvl.vipLevelName,bvl.vipLevelNote) from BasVipLevel bvl where bvl.enterpriseId="+bvrVO.getEnterpriseId();
        List list = new ArrayList();
        try{
            list = this.getHibernateTemplate().find(hql);
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    
    /**
     * 根据ID获取维修分类
     * */
    public BasRepairType getRepairTypeByID(int id) throws Exception
    {
        String hql = "from BasRepairType where reptypId='" + id + "'";
        List list = new ArrayList();
        try{
            list = this.getHibernateTemplate().find(hql);
        }catch(Exception e){
            e.printStackTrace();
        }
        return (BasRepairType) list.get(0);
    }

    @SuppressWarnings("unchecked")
    
    /**
     * 获取总记录数
     * */
    public int getTotle(final int enterprise_id) throws Exception
    {
        return this.getHibernateTemplate().execute(new HibernateCallback<Integer>()
        {
            
            public Integer doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                //String hql = "select * from bas_vip_integral_regulation where enterprise_id="+enterprise_id;
                String hql = "select * from bas_vip_integral_regulation ";
                Query query = session.createSQLQuery(hql);
                List list = query.list();
                if (list != null)
                {
                    return list.size();
                }
                return 0;
            }
        });
    }

    
    /**
     * 根据ID获取会员等级
     * */
    public BasVipLevel getVipLevelByID(int id) throws Exception
    {
        String hql = "from BasVipLevel where vipLevelId='" + id + "'";
        List list = new ArrayList();
        try
        {
            list = this.getHibernateTemplate().find(hql);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return (BasVipLevel) list.get(0);
    }

    /**
     * 根据会员等级和维修分类获取会员积分规则
     * 
     * @vipLevelId 会员等级编号
     * @reptypId 维修分类编号
     * */
    
    public boolean getByLevelAndReptye(BasVipIntegralRegulationVO bvrVO)
            throws Exception
    {
        String hql = "from BasVipIntegralRegulation where basVipLevel.vipLevelId='"
                + bvrVO.getVipLevelId()
                + "' and reptypId='"
                + bvrVO.getReptypId() + "' and enterpriseId="+bvrVO.getEnterpriseId();
        if (bvrVO.getVipInteReg() != null)
        {
            hql += " and vipInteReg not in('" + bvrVO.getVipInteReg() + "')";
        }
        try
        {
            List list = this.getHibernateTemplate().find(hql);
            if (list != null && list.size() > 0)
            {
                return true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
