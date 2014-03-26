package com.syuesoft.vipmanagement.daoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasVipInfor;
import com.syuesoft.model.BasVipRecharge;
import com.syuesoft.model.BasVipRechargeRegulation;
import com.syuesoft.vipmanagement.dao.VipRechargeDao;

/**
 * 
 * @author mulangtao
 *
 */
@Repository("vipRechargeDao")
public class VipRechargeDaoImpl extends BaseDaoImpl<BasVipRecharge> implements VipRechargeDao {
    /**
     * 添加会员充值表数据
     * @brr 会员充值实体
     * */
    
    public void add(BasVipRecharge brr) throws Exception {
        this.getHibernateTemplate().save(brr);
    }
    /**
     * 根据会员编号获取会员信息
     * @vipNumber 会员卡号
     * */
    
    public BasVipInfor getByVipNumber(String vipNumber) throws Exception {
        String hql = "from BasVipInfor where vipNumber='"+ vipNumber +"'";
        List list = this.getHibernateTemplate().find(hql);
        if(list.size() > 0){
            return (BasVipInfor)list.get(0);
        }
        return null;
    }
    /**
     * 根据会员储值金额获取会员储值规则
     * @recAmount 储值金额
     * */
    
    public BasVipRechargeRegulation getByRecAmount(Integer recAmount)
            throws Exception {
        String hql = "from BasVipRechargeRegulation where recRulName <= "+recAmount+" and recRulNameEnd > "+recAmount+"";
        List list = this.getHibernateTemplate().find(hql);
        if(list.size() > 0){
            return (BasVipRechargeRegulation)list.get(0);
        }
        return null;
    }
    
    /**
     * 删除会员充值记录
     * @bvi 会员信息
     * */
    
    public void deleteVipRecharge(BasVipRecharge bvr)
            throws Exception {
        this.getHibernateTemplate().delete(bvr);
    }
    /**
     * 根据充值编号获取充值信息
     * @id 充值编号
     * */
    
    public BasVipRecharge getById(String id) throws Exception {
        String hql = "from BasVipRecharge where vipRecId='"+id +"'";
        List list = this.getHibernateTemplate().find(hql);
        if(list.size() > 0){
             return (BasVipRecharge)list.get(0);
        }
        return null;
    }
}