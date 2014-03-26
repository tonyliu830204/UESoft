package com.syuesoft.bas.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasCarBrandDAO;
import com.syuesoft.base.vo.BasCarBrandVo;
import com.syuesoft.model.BasCarBrand;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
/**
 * 基础资料-->车辆性质：车辆品牌Dao实现类
 * 
 * @author SuMing
 */
@Repository("basCarBrandDAO")
public class BasCarBrandDAOImpl extends BaseDaoImpl<BasCarBrand> implements BasCarBrandDAO
{

    @SuppressWarnings("deprecation")
    public void savaImg(BasCarBrandVo cbVo, FileInputStream in) throws Exception
    {
        PreparedStatement ps=this.getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareStatement("update bas_car_brand set cbrd_logo=? where cbrd_id=?");
        byte [] b=new byte[(int)cbVo.getFile().length()];
        in.read(b);
        ps.setBytes(1,b);
        ps.setString(2, cbVo.getCbrdId());
        ps.execute();     
    }
}