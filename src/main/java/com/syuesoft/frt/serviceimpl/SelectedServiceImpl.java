package com.syuesoft.frt.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.base.vo.ItemVo;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.frt.dao.FrtResevationDao;
import com.syuesoft.frt.service.SelectedService;
import com.syuesoft.model.FrtRushToRepair;

@Service("selectedService")
public class SelectedServiceImpl extends BaseLogServiceImpl implements
        SelectedService
{
    FrtResevationDao frtResevationDao;

    /**
     * @return the frtResevationDao
     */
    public FrtResevationDao getFrtResevationDao()
    {
        return frtResevationDao;
    }

    /**
     * @param frtResevationDao
     *            the frtResevationDao to set
     */
    public void setFrtResevationDao(FrtResevationDao frtResevationDao)
    {
        this.frtResevationDao = frtResevationDao;
    }

    /**
     * 提交增删改的维修配件
     */
    
    public List<PartsVo> acceptChangesParts(String inserted, String deleted,
            String updated) throws Exception
    {
        if (inserted != null && !"".equals(inserted.trim()))
        {
            List<PartsVo> insertedList = JSON.parseArray(inserted,
                    PartsVo.class);
            if (insertedList.size() > 0)
            {
                return insertedList;
            }
        }
        if (deleted != null && !"".equals(deleted.trim()))
        {
            List<PartsVo> deletedList = JSON.parseArray(deleted, PartsVo.class);
            if (deletedList.size() > 0)
            {
                return deletedList;
            }
        }
        if (updated != null && !"".equals(updated.trim()))
        {
            List<PartsVo> updatedList = JSON.parseArray(updated, PartsVo.class);
            if (updatedList.size() > 0)
            {
                return updatedList;
            }
        }
        return null;
    }

    /**
     * 提交增删改的维修配件
     */
    
    public List<PartsVo> acceptChangesParts(PartsVo parts) throws Exception
    {
        return null;
    }

    /**
     * 提交增删改的维修项目
     */
    
    public List<ItemVo> acceptChangesItem(String inserted, String deleted,
            String updated) throws Exception
    {
        if (inserted != null && !"".equals(inserted.trim()))
        {
            List<ItemVo> insertedList = JSON.parseArray(inserted, ItemVo.class);
            if (insertedList.size() > 0)
            {
                return insertedList;
            }
        }
        if (deleted != null && !"".equals(deleted.trim()))
        {
            List<ItemVo> deletedList = JSON.parseArray(deleted, ItemVo.class);
            if (deletedList.size() > 0)
            {
                return deletedList;
            }
        }
        if (updated != null && !"".equals(updated.trim()))
        {
            List<ItemVo> updatedList = JSON.parseArray(updated, ItemVo.class);
            if (updatedList.size() > 0)
            {
                return updatedList;
            }
        }
        return null;
    }

    /**
     * 从session中移除配件
     */
    
    public void removeParts(String id, List<PartsVo> frtResevation)
            throws Exception
    {
        for (int i = 0; i < frtResevation.size(); i++)
        {
            String id2 = ((PartsVo) (frtResevation.get(i))).getPartsId();
            if (id2.equals(id))
            {
                frtResevation.remove(i);
            }
        }
    }

    /**
     * 从session中移除项目
     */
    
    public void removeItem(String id, List<ItemVo> selectedItemList)
            throws Exception
    {
        for (int i = 0; i < selectedItemList.size(); i++)
        {
            String repitemId2 = ((ItemVo) (selectedItemList.get(i)))
                    .getRepitemId();
            if (repitemId2.equals(id))
            {
                selectedItemList.remove(i);
            }
        }
    }

    /**
     * 查找抢修信息
     * */
    
    public FrtRushToRepair findrushToRepair(String resvId) throws Exception
    {
        // TODO Auto-generated method stub

        return frtResevationDao.findrushToRepair(resvId);
    }

    /**
     * 更新抢修信息
     * */
    
    public void updaterushToRepair(FrtRushToRepair frtRushToRepair)
            throws Exception
    {
        // TODO Auto-generated method stub
        frtResevationDao.updaterushToRepair(frtRushToRepair);
    }

}
