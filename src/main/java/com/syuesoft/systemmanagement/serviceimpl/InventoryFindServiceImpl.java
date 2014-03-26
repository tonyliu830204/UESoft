package com.syuesoft.systemmanagement.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.FrtParts;
import com.syuesoft.systemmanagement.dao.InventoryFindDao;
import com.syuesoft.systemmanagement.service.InventoryFindService;
import com.syuesoft.systemmanagement.vo.InventoryFindVo;

@Service("inventoryFindDaoService")
public class InventoryFindServiceImpl implements InventoryFindService {
	
	@Autowired
	private InventoryFindDao inventoryFindDao;
	public InventoryFindDao getInventoryFindDao() {
		return inventoryFindDao;
	}
	public void setInventoryFindDao(InventoryFindDao inventoryFindDao) {
		this.inventoryFindDao = inventoryFindDao;
	}

	public List<InventoryFindVo> getChildInfor(InventoryFindVo inventoryFindVo)
			throws Exception {
	    List<InventoryFindVo> list = new ArrayList<InventoryFindVo>();
	    String sql = "SELECT c.PARTS_ID, c.PARTS_NAME, d.PUNIT_NAME, a.STINVM_ID, b.STINVM_TIME, a.STINVD_COUNT, a.STINVD_PRICE, a.STINVD_COST " +
                " FROM st_inventory_detail a, st_inventory_main b, frt_parts c, bas_parts_unit d " +
                " WHERE a.STINVM_ID = b.STINVM_ID AND b.enterprise_id ="+inventoryFindVo.getEnterpriseId()+
                " AND a.PARTS_ID = c.PARTS_ID AND c.enterprise_id = b.enterprise_id AND c.PUNIT_NAME = d.PUNIT_ID "+
                " AND c.PARTS_ID='"+inventoryFindVo.getParts_Id()+"'";
	    if(inventoryFindVo.getStinvm_Id()!=null && !inventoryFindVo.getStinvm_Id().equals("")){
            sql +=" AND a.STINVM_ID like '%"+StringEscapeUtils.escapeSql(inventoryFindVo.getStinvm_Id().trim())+"%'";
        }
		 List<Object[]> rlist = inventoryFindDao.createSQLQuery(sql);
		 if(rlist != null && rlist.size() > 0){
		    Object[] obj = null;
	        for (int i = 0; i < rlist.size(); i++) {
	            obj = (Object[])rlist.get(i);
	            InventoryFindVo vo = new InventoryFindVo();
	            if(obj[0]!=null){vo.setParts_Id(obj[0].toString());}
	            if(obj[1]!=null){vo.setParts_Name(obj[1].toString());}
	            if(obj[2]!=null){vo.setPunit_Name(obj[2].toString());}
	            if(obj[3]!=null){vo.setStinvm_Id(obj[3].toString());}
	            if(obj[4]!=null){vo.setStinvm_Time(obj[4].toString());}
	            if(obj[5]!=null){vo.setStinvd_Count(obj[5].toString());}
	            if(obj[6]!=null){vo.setStinvd_Price(obj[6].toString());}
	            if(obj[7]!=null){vo.setStinvd_Cost(obj[7].toString());}
	            list.add(vo);
	        }
		 }
		 return list;
	}

	public Datagrid getFatherInfor(InventoryFindVo inventoryFindVo)
			throws Exception {
	    List<InventoryFindVo> list = new ArrayList<InventoryFindVo>();
	    Datagrid datagrid = new Datagrid();
	    String sql = "SELECT c.PARTS_ID, c.PARTS_NAME, d.PUNIT_NAME, SUM(a.STINVD_COUNT), SUM(a.STINVD_PRICE), SUM(a.STINVD_COST) " +
                " FROM st_inventory_detail a, st_inventory_main b, frt_parts c, bas_parts_unit d" + 
                " WHERE a.STINVM_ID = b.STINVM_ID AND b.enterprise_id ="+inventoryFindVo.getEnterpriseId()+
                " AND a.PARTS_ID = c.PARTS_ID AND c.enterprise_id = b.enterprise_id AND c.PUNIT_NAME = d.PUNIT_ID " ;
        if(inventoryFindVo.getStinvm_Time()!=null && !inventoryFindVo.getStinvm_Time().equals("")){
            String[] str = inventoryFindVo.getStinvm_Time().split(",");
            if(str.length>1){
                sql += " and b.STINVM_TIME BETWEEN '"+str[0]+"' AND '"+str[1]+"'";
            }else{
                if(inventoryFindVo.getStinvm_Time().length()>10){
                    sql += " and b.STINVM_TIME > '"+str[0]+"'";
                }else{
                    sql += " and b.STINVM_TIME < '"+str[0]+"'";
                }
            }
        } 
        if(inventoryFindVo.getParts_Name()!=null && !inventoryFindVo.getParts_Name().equals("")){
            sql +=" and c.PARTS_NAME like '%"+StringEscapeUtils.escapeSql(inventoryFindVo.getParts_Name().trim())+"%'";
        }
        if(inventoryFindVo.getParts_Id()!=null && !inventoryFindVo.getParts_Id().equals("")){
            sql +=" and c.PARTS_ID like '%"+StringEscapeUtils.escapeSql(inventoryFindVo.getParts_Id().trim())+"%'";
        }
        if(inventoryFindVo.getStinvm_Id()!=null && !inventoryFindVo.getStinvm_Id().equals("")){
            sql +=" and a.STINVM_ID like '%"+StringEscapeUtils.escapeSql(inventoryFindVo.getStinvm_Id().trim())+"%'";
        }
        sql +=" GROUP BY a.PARTS_ID"; 
	    int total = inventoryFindDao.getCountBySQL(sql);
	    List<Object[]> rlist = inventoryFindDao.createSQLQuery(sql);
	    if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                InventoryFindVo vo = new InventoryFindVo();
                if(obj[0]!=null){vo.setParts_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setParts_Name(obj[1].toString());}
                if(obj[2]!=null){vo.setPunit_Name(obj[2].toString());}
                if(obj[3]!=null){vo.setStinvd_Count(obj[3].toString());}
                if(obj[4]!=null){vo.setStinvd_Price(obj[4].toString());}
                if(obj[5]!=null){vo.setStinvd_Cost(obj[5].toString());}
                vo.setEnterpriseId(inventoryFindVo.getEnterpriseId());
                List o = getChildInfor(vo);
                if(o != null && o.size() > 0){
                    vo.setIconCls("icon-blank");
                    vo.setState("closed");
                }else{
                    vo.setIconCls("");
                    vo.setState("open");
                }
                list.add(vo);
            }
	    }
        datagrid.setTotal(total);
        datagrid.setRows(list);
		return datagrid;
	}
	
	public List<FrtParts> getPartsName() throws Exception {
		return inventoryFindDao.getPartsName();
	}
}
