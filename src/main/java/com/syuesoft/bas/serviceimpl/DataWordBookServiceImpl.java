package com.syuesoft.bas.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.BasChilddictionaryDao;
import com.syuesoft.bas.dao.BasParentdictionaryDao;
import com.syuesoft.bas.service.DataWordBookService;
import com.syuesoft.base.vo.BarChildrenAndParentVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasParentdictionary;
import com.syuesoft.util.MyBeanUtils;

/**
 * 数据字典Service
 * */
@Service("dataWordBookService")
public class DataWordBookServiceImpl implements DataWordBookService
{
    @Autowired
    private BasParentdictionaryDao basParentdictionaryDao;

    @Autowired
    private BasChilddictionaryDao basChilddictionaryDaoI;

    /**
     * 查询子级数据
     * */
    
    public Datagrid datagridC(BarChildrenAndParentVo bcapVo) throws Exception
    {
        StringBuffer sb = new StringBuffer("SELECT a.*,bs.stf_name FROM");
        sb.append(" (SELECT bc.child_id,bp.parent_id,bp.dataValue AS data1,");
        sb.append(" bc.createTime,bc.dataKey,bc.dataValue AS data2,bc.stf_id");
        sb.append(" FROM bas_parentdictionary bp,bas_childdictionary bc");
        sb.append(" WHERE bp.parent_id=bc.parent_id ) a");
        sb.append(" LEFT OUTER JOIN bas_stuff bs");
        sb.append(" ON bs.stf_id=a.stf_id where a.parent_id="
                + bcapVo.getParentId());
        List<BarChildrenAndParentVo> rows = new ArrayList();
        List<Object[]> rowsList = basParentdictionaryDao.createSQLQuery(sb
                .toString(), null, bcapVo.getPage(), bcapVo.getRows());
        BarChildrenAndParentVo bcap = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] obj : rowsList)
            {
                bcap = new BarChildrenAndParentVo();
                if (obj[0] != null && obj[0].toString().length() > 0)
                    bcap.setChildId(Long.parseLong(obj[0].toString()));
                if (obj[1] != null && obj[1].toString().length() > 0)
                    bcap.setParentId(Long.parseLong(obj[1].toString()));
                if (obj[2] != null && obj[2].toString().length() > 0)
                    bcap.setParentName(obj[2].toString());
                if (obj[3] != null && obj[3].toString().length() > 0)
                    bcap.setCreateTime(MyBeanUtils.getInstance().formatString(
                            obj[3].toString()));
                if (obj[4] != null && obj[4].toString().length() > 0)
                    bcap.setDataKey(obj[4].toString());
                if (obj[5] != null && obj[5].toString().length() > 0)
                    bcap.setDataValue(obj[5].toString());
                if (obj[6] != null && obj[6].toString().length() > 0)
                    bcap.setStfId(Short.parseShort(obj[6].toString()));
                if (obj[7] != null && obj[7].toString().length() > 0)
                    bcap.setStfName(obj[7].toString());
                rows.add(bcap);
            }
        int total = basChilddictionaryDaoI.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 查询父级数据
     * */
    
    public Datagrid datagridP(BarChildrenAndParentVo bcapVo) throws Exception
    {
        StringBuffer sb = new StringBuffer(
                "SELECT bp.parent_id,bs.stf_id,bs.stf_name,bp.createTime,bp.dataKey,bp.dataValue");
        sb
                .append(" FROM bas_parentdictionary bp LEFT OUTER JOIN bas_stuff bs ON bs.stf_id=bp.stf_id");
        List<BarChildrenAndParentVo> rows = new ArrayList();
        List<Object[]> rowsList = basParentdictionaryDao.createSQLQuery(sb
                .toString(), bcapVo.getPage(), bcapVo.getRows());
        BarChildrenAndParentVo bcap = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] obj : rowsList)
            {
                bcap = new BarChildrenAndParentVo();
                if (obj[0] != null && obj[0].toString().length() > 0)
                    bcap.setParentId(Long.parseLong(obj[0].toString()));
                if (obj[1] != null && obj[1].toString().length() > 0)
                    bcap.setStfId(Short.parseShort(obj[1].toString()));
                if (obj[2] != null && obj[2].toString().length() > 0)
                    bcap.setStfName(obj[2].toString());
                if (obj[3] != null && obj[3].toString().length() > 0)
                    bcap.setCreateTime(MyBeanUtils.getInstance().formatString(
                            obj[3].toString()));
                if (obj[4] != null && obj[4].toString().length() > 0)
                    bcap.setDataKey(obj[4].toString());
                if (obj[5] != null && obj[5].toString().length() > 0)
                    bcap.setDataValue(obj[5].toString());
                rows.add(bcap);
            }
        int total = basParentdictionaryDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 更新子级数据
     * */
    
    public void editC(BarChildrenAndParentVo bcapVo) throws Exception
    {
        // TODO Auto-generated method stub
        BasChilddictionary bc = new BasChilddictionary();
        MyBeanUtils.getInstance().copyBeans(bcapVo, bc);
        basChilddictionaryDaoI.merge(bc);
    }

    /**
     * 更新父级数据
     * */
    
    public void editP(BarChildrenAndParentVo bcapVo) throws Exception
    {
        // TODO Auto-generated method stub
        BasParentdictionary bp = new BasParentdictionary();
        MyBeanUtils.getInstance().copyBeans(bcapVo, bp);
        basParentdictionaryDao.merge(bp);
    }

    /**
     * 删除子级数据
     * */
    
    public void removeC(BarChildrenAndParentVo bcapVo) throws Exception
    {
        // TODO Auto-generated method stub
        basChilddictionaryDaoI.delete(basChilddictionaryDaoI.get(
                BasChilddictionary.class, bcapVo.getChildId()));
    }

    /**
     * 删除父级数据
     * */
    
    public void removeP(BarChildrenAndParentVo bcapVo) throws Exception
    {
        // TODO Auto-generated method stub
        basParentdictionaryDao.delete(basParentdictionaryDao.get(
                BasParentdictionary.class, bcapVo.getParentId()));
    }

    /**
     * 增加子级数据
     * */
    
    public void saveC(BarChildrenAndParentVo bcapVo) throws Exception
    {
        BasChilddictionary bc = new BasChilddictionary();
        MyBeanUtils.getInstance().copyBeans(bcapVo, bc);
        basChilddictionaryDaoI.save(bc);
    }

    /**
     * 增加父级数据
     * */
    
    public void saveP(BarChildrenAndParentVo bcapVo) throws Exception
    {
        BasParentdictionary bp = new BasParentdictionary();
        MyBeanUtils.getInstance().copyBeans(bcapVo, bp);
        basParentdictionaryDao.save(bp);
    }

    /**
     * 判断是否有子级数据
     * */
    
    public Boolean isExists(BarChildrenAndParentVo bcapVo) throws Exception
    {
        List<BasChilddictionary> list = basChilddictionaryDaoI
                .find("from BasChilddictionary bc where bc.parentId="
                        + bcapVo.getParentId());
        if (list != null && list.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 判断子级Key是否重复
     * */
    
    public Boolean isExistsKeyC(BarChildrenAndParentVo bcapVo) throws Exception
    {
        List<BasChilddictionary> list = basChilddictionaryDaoI
                .find("from BasChilddictionary bc where bc.dataKey='"
                        + bcapVo.getDataKey() + "'");
        if (list != null && list.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 判断父级Key是否重复
     * */
    
    public Boolean isExistsKeyP(BarChildrenAndParentVo bcapVo) throws Exception
    {
        List<BasParentdictionary> list = basParentdictionaryDao
                .find("from BasParentdictionary bp where bp.dataKey='"
                        + bcapVo.getDataKey() + "'");
        if (list != null && list.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
