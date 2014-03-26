package com.syuesoft.bas.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.FrtAddItemDao;
import com.syuesoft.bas.service.FrtAddItemService;
import com.syuesoft.base.vo.FrtRepairItemVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.FrtAddItem;

/** 
 * @ClassName: FrtAddItemServiceImpi 
 * @Description: TODO(加装项目) 
 * @author HeXin 
 * @date 2013-8-29 下午03:29:51 
 * @version 1.0 
 */
@Service("frtAddItemServiceImpi")
public class FrtAddItemServiceImpl implements FrtAddItemService
{
    
    @Autowired
    private FrtAddItemDao frtAddItemDao;

    public Datagrid getTreegrid(FrtRepairItemVo frtRepairItemVo) throws Exception
    {
        String hql = "from FrtAddItem bcb where bcb.enterpriseId="+frtRepairItemVo.getEnterpriseId();
        if(frtRepairItemVo.getItemName() != null && !frtRepairItemVo.getItemName().equals("")){
            hql += " AND itemName like '%"+StringEscapeUtils.escapeSql(frtRepairItemVo.getItemName().trim())+"%'";
        }
        List<FrtAddItem> list = frtAddItemDao.find(hql, frtRepairItemVo.getPage(), frtRepairItemVo.getRows());
        List<FrtRepairItemVo> list_ = new ArrayList<FrtRepairItemVo>();
        int total = frtAddItemDao.getCount(hql);
        Datagrid dg = new Datagrid();
        dg.setTotal(total);
        if(list != null && list.size() > 0){
            for(FrtAddItem item : list){
                FrtRepairItemVo vo = new FrtRepairItemVo();
                vo.setItemId(item.getItemId() != null ? item.getItemId().toString() : null);
                vo.setItemName(item.getItemName() != null ? item.getItemName().toString() : null);
                vo.setItemCost(item.getItemCost() != null ? item.getItemCost().toString() : null);
                vo.setItemMoney(item.getItemMoney() != null ? item.getItemMoney().toString() : null);
                vo.setRemark(item.getRemark() != null ? item.getRemark() : null);
                list_.add(vo);
            }
        }
        dg.setRows(list_);
        return dg;
    }

    /** (非 Javadoc) 
    * <p>Title: delete</p> 
    * <p>Description: </p> 
    * @param frtRepairItemVo
    * @return
    * @throws Exception 
    * @see com.syuesoft.bas.service.FrtAddItemService#delete(com.syuesoft.base.vo.FrtRepairItemVo) 
    */
    
    public void delete(FrtRepairItemVo frtRepairItemVo) throws Exception
    {
        FrtAddItem frtAddItem =frtAddItemDao.get(FrtAddItem.class, Integer.parseInt(frtRepairItemVo.getItemId()));
        if(frtAddItem!=null){
             frtAddItemDao.delete(frtAddItem);
        }
    }

    /** (非 Javadoc) 
    * <p>Title: save</p> 
    * <p>Description: </p> 
    * @param frtRepairItemVo
    * @throws Exception 
    * @see com.syuesoft.bas.service.FrtAddItemService#save(com.syuesoft.base.vo.FrtRepairItemVo) 
    */
    
    public void save(FrtRepairItemVo frtRepairItemVo) throws Exception
    {
        FrtAddItem frtAddItem = new FrtAddItem();
        frtAddItem.setItemName(frtRepairItemVo.getItemName());
        frtAddItem.setItemCost(Double.parseDouble(frtRepairItemVo.getItemCost()));
        frtAddItem.setItemMoney(Double.parseDouble(frtRepairItemVo.getItemMoney()));
        frtAddItem.setEnterpriseId(frtRepairItemVo.getEnterpriseId());
        frtAddItem.setRemark(frtRepairItemVo.getRemark());
        frtAddItemDao.save(frtAddItem);
    }

    /** (非 Javadoc) 
    * <p>Title: update</p> 
    * <p>Description: </p> 
    * @param frtRepairItemVo
    * @throws Exception 
    * @see com.syuesoft.bas.service.FrtAddItemService#update(com.syuesoft.base.vo.FrtRepairItemVo) 
    */
    
    public void update(FrtRepairItemVo fp) throws Exception
    {
        FrtAddItem frtAddItem =frtAddItemDao.get(FrtAddItem.class,Integer.parseInt(fp.getItemId()));
        if(fp!=null){
        	if(fp.getItemName()!=null&&!fp.getItemName().equals(""))
        		frtAddItem.setItemName(fp.getItemName());
        	if(fp.getItemCost()!=null)
        		frtAddItem.setItemCost(Double.parseDouble(fp.getItemCost()));
        	if(fp.getItemMoney()!=null)
        		frtAddItem.setItemMoney(Double.parseDouble(fp.getItemMoney()));
        	if(fp.getRemark()!=null)
        		 frtAddItem.setRemark(fp.getRemark());
        	frtAddItemDao.merge(frtAddItem);
        }
    }

}