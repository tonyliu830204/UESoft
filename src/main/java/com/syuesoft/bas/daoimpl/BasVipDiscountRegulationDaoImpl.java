package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasVipDiscountRegulationDao;
import com.syuesoft.base.vo.BasVipDiscountRegulationVO;
import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.base.vo.ReptypeVo;
import com.syuesoft.model.BasRepairType;
import com.syuesoft.model.BasVipDiscountRegulation;
import com.syuesoft.model.BasVipLevel;

@Repository("basVipDiscountRegulationDao")
/**
 * @author mulangtao
 * */
public class BasVipDiscountRegulationDaoImpl extends
        BaseDaoImpl<BasVipDiscountRegulation> implements
        BasVipDiscountRegulationDao
{

    
    /**
     * 添加会员折扣规则
     * @basVipDiscountRegulation 会员折扣规则实体
     * */
    public void add(BasVipDiscountRegulation basVipDiscountRegulation)
            throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(basVipDiscountRegulation);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    /**
     * 删除会员折扣规则
     * @basVipDiscountRegulation 会员折扣规则实体
     * */
    public void delte(BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception
    {
        try
        {
            this.deleteByHql("DELETE FROM BasVipDiscountRegulation where vipDisRegId = "
                            + basVipDiscountRegulationVO.getVipDisRegId());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    
    /**
     * 分页查询所有规则折扣规则
     * @page 当前页
     * @rows 每页显示记录数
     * @order 排序类型
     * @sort 排序字段
     * */
    public List<BasVipDiscountRegulationVO> findAll(final int page,
            final int rows, final String order, final String sort,final int enterprise_id)
            throws Exception
    {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<BasVipDiscountRegulationVO>>()
        {
            
            public List<BasVipDiscountRegulationVO> doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                String hql = "SELECT * FROM (SELECT bvd.VIP_DIS_REG_ID AS vipDisRegId ,bvl.VIP_LEVEL_ID AS vipLevelId,bvl.VIP_LEVEL_NAME AS vipLevelName,"+
				" bvd.REPTYP_ID AS reptypId,reptype.REPT_NAME AS reptypName,bvd.WORK_REG_DISCOUNT AS workRegDiscount,bvd.MATERIAL_REG_DISCOUNT AS materialRegDiscount,"+
				" bvd.TOTAL_REG_DISCOUNT AS totalRegDiscount"+
				" ,bvd.enterprise_id AS enterpriseId FROM  bas_vip_discount_regulation AS bvd,"+
				" bas_vip_level AS bvl,reptype  WHERE bvd.VIP_LEVEL_ID=bvl.VIP_LEVEL_ID"+
				" AND bvd.REPTYP_ID=reptype.REPT_ID) AS a ";
                //" AND bvd.REPTYP_ID=reptype.REPT_ID) AS a where a.enterpriseId="+enterprise_id;
                // 判断排序类型和排序字段
                if (order != null && sort != null){
                    hql += " order by " + sort + " " + order + "";
                }
                else{
                    hql += " order by vipDisRegId";
                }
                Query query = session.createSQLQuery(hql);
                // 计算分页起始数
                int beginRow = (page - 1) * rows;
                query.setFirstResult(beginRow);
                query.setMaxResults(rows);
                List list = query.list();
                List<BasVipDiscountRegulationVO> list1 = new ArrayList<BasVipDiscountRegulationVO>();
                Object[] obj = null;
                // 遍历结果
                for (int i = 0; i < list.size(); i++)
                {
                    obj = (Object[]) list.get(i);
                    // 创建 一个会员这折扣规则VO并赋值
                    BasVipDiscountRegulationVO bvrVO = new BasVipDiscountRegulationVO();
                    bvrVO.setVipDisRegId(Integer.parseInt(obj[0].toString()));
                    bvrVO.setVipLevelId(Integer.parseInt(obj[1].toString()));
                    bvrVO.setVipLevelName(obj[2].toString());
                    if (obj[3] == null)
                        bvrVO.setReptypId(null);
                    else
                        bvrVO.setReptypId(Integer.parseInt(obj[3].toString()));
                    if (obj[4] == null)
                        bvrVO.setReptypName(null);
                    else
                        bvrVO.setReptypName(obj[4].toString());
                    if (obj[5] == null)
                        obj[5] = 100;
                    bvrVO.setWorkRegDiscount(Long.parseLong(obj[5].toString()));
                    if (obj[6] == null)
                        obj[6] = 100;
                    bvrVO.setMaterialRegDiscount(Long.parseLong(obj[6].toString()));
                    if (obj[7] == null)
                        obj[7] = 100;
                    bvrVO.setTotalRegDiscount(Long.parseLong(obj[7].toString()));
                    // 将创建的会员折扣规则VO添加到List集合中
                    list1.add(bvrVO);
                }
                return list1;
            }
        });
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
                //String hql = " SELECT * FROM bas_vip_discount_regulation where enterprise_id="+enterprise_id;
                String hql = " SELECT * FROM bas_vip_discount_regulation ";
                Query query = session.createSQLQuery(hql);
                List list = query.list();
                if (list != null){
                    return list.size();
                }
                session.clear();
                return 0;
            }
        });
    }

    
    public List<ReptypeVo> findAllRepairType(
            BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception
    {
        String hql = "select rt.reptId,rt.reptName,rt.workCreditsRate,rt.partCreditsRate,rt.sumCreditsRate,rt.memo from Reptype rt";
        try
        {
            if (basVipDiscountRegulationVO.getQ() != null
                    && basVipDiscountRegulationVO.getQ().toString().length() > 0)
            {
                hql += " where rt.reptName like '%"
                        + StringEscapeUtils.escapeSql(basVipDiscountRegulationVO.getQ().trim()) + "%'";
            }
            List list = this.getHibernateTemplate().find(hql);
            List<ReptypeVo> list1 = new ArrayList<ReptypeVo>();
            Object[] obj = null;
            for (int i = 0; i < list.size(); i++)
            {
                obj = (Object[]) list.get(i);
                ReptypeVo rv = new ReptypeVo();
                rv.setReptId(obj[0].toString());
                rv.setReptName(obj[1].toString());
                if (obj[2] == null){
                    obj[2] = 0;
                }
                rv.setWorkCreditsRate(obj[2].toString());
                if (obj[3] == null){
                    obj[3] = 0;
                }
                rv.setPartCreditsRate(obj[3].toString());
                if (obj[4] == null){
                    obj[4] = 0;
                }
                rv.setSumCreditsRate(obj[4].toString());
                if (obj[5] == null){
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
    public List<BasVipLevelVO> findAllVipLevel(
            BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception
    {
        String hql = "select new com.syuesoft.base.vo.BasVipLevelVO(bvl.vipLevelId,bvl.vipLevelName,bvl.vipLevelNote) from BasVipLevel bvl where bvl.enterpriseId="+basVipDiscountRegulationVO.getEnterpriseId();
        if (basVipDiscountRegulationVO.getQ() != null
                && basVipDiscountRegulationVO.getQ().toString().length() > 0)
        {
            hql += " and bvl.vipLevelName like '%"
                    + StringEscapeUtils.escapeSql(basVipDiscountRegulationVO.getQ().trim()) + "%'";
        }
        try
        {
            List list = this.getHibernateTemplate().find(hql);
            return list;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * 获取所有维修分类
     * */
    public BasRepairType getRepairTypeByID(int id) throws Exception
    {
        String hql = "from BasVipLevel where reptypId='" + id + "'";
        try
        {
            List list = this.getHibernateTemplate().find(hql);
            if (list != null)
            {
                return (BasRepairType) list.get(0);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * 根据ID获取会员等级
     * */
    public BasVipLevel getVipLevelByID(int id) throws Exception
    {
        String hql = "from BasVipLevel where vipLevelId='" + id + "'";
        try{
            List list = this.getHibernateTemplate().find(hql);
            if (list != null)
                return (BasVipLevel) list.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据会员等级和维修分类查询会员折扣规则是否存在
     * 
     * @vipLevelId 会员等级编号
     * @reptypId 维修分类编号
     * */
    
    public boolean getByLevelAndReptype(
            BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception
    {
        String hql = "from BasVipDiscountRegulation where basVipLevel.vipLevelId='"
                + basVipDiscountRegulationVO.getVipLevelId()
                + "' and reptypId='"
                + basVipDiscountRegulationVO.getReptypId()
                + "' and enterpriseId="+basVipDiscountRegulationVO.getEnterpriseId();
        if (basVipDiscountRegulationVO.getVipDisRegId() != null)
        {
            hql += " and vipDisRegId not in('"
                    + basVipDiscountRegulationVO.getVipDisRegId() + "')";
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
