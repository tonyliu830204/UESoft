package com.syuesoft.systemmanagement.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fbk.vo.ComboxidVo;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasStorehouse;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.model.StInventoryDetail;
import com.syuesoft.model.StInventoryMain;
import com.syuesoft.st.dao.PartsChangePriceDAO;
import com.syuesoft.systemmanagement.dao.AccesssoriesInventoryDao;
import com.syuesoft.systemmanagement.dao.StInventoryMainDao;
import com.syuesoft.systemmanagement.service.AccesssoriesInventoryService;
import com.syuesoft.systemmanagement.vo.AccesssoriesInventoryVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@Service("accesssoriesInventoryService")
public class AccesssoriesInventoryServiceImpl implements AccesssoriesInventoryService {

	@Autowired
	private  AccesssoriesInventoryDao accesssoriesInventoryDao;
	@Autowired
	private StInventoryMainDao stInventoryMainDao;
	@Autowired
    private BasStorehouseDAO basStorehouseDAO;
	@Autowired
    private BaseService baseService;
	@Autowired 
	PartsChangePriceDAO partsChangePriceDAO;     // 配件调价表DAO
	
	public Json doFind(
			AccesssoriesInventoryVo accesssoriesInventoryVo) throws Exception {
	    Json json = new Json();
	    List<AccesssoriesInventoryVo> list = new ArrayList<AccesssoriesInventoryVo>();
        String sql = "SELECT A.*, B.STORE_NAME, C.STF_NAME, D.dataValue FROM St_Inventory_Main A, bas_storehouse B, bas_stuff C, bas_childdictionary D WHERE A.enterprise_id="+accesssoriesInventoryVo.getEnterpriseId()+" AND A.STORE_ID = B.STORE_ID AND A.STF_ID = C.STF_ID AND a.STINVM_STATE = D.dataKey ";
        if(accesssoriesInventoryVo.getStinvm_Time()!=null && !"".equals(accesssoriesInventoryVo.getStinvm_Time())){
            sql += " and A.Stinvm_Time > '"+accesssoriesInventoryVo.getStinvm_Time()+"'";
        }
        if(accesssoriesInventoryVo.getStinvm_Time2()!=null && !"".equals(accesssoriesInventoryVo.getStinvm_Time2())){
            sql += " and A.Stinvm_Time < '"+accesssoriesInventoryVo.getStinvm_Time2()+"'";
        }
        if(accesssoriesInventoryVo.getStinvm_Id() != null && !accesssoriesInventoryVo.getStinvm_Id().equals("") ){
            sql += "and A.Stinvm_Id LIKE '%"+StringEscapeUtils.escapeSql(accesssoriesInventoryVo.getStinvm_Id().trim())+"%'";
        }
        if(accesssoriesInventoryVo.getStinvm_State() != null && !"".equals(accesssoriesInventoryVo.getStinvm_State())){
            sql += "and A.Stinvm_State ='"+accesssoriesInventoryVo.getStinvm_State()+"'";
        }
	    List<Object[]> rlist = accesssoriesInventoryDao.createSQLQuery(sql, accesssoriesInventoryVo.getPage(), accesssoriesInventoryVo.getRows());
	    int size = accesssoriesInventoryDao.getCountBySQL(sql);
	    if(rlist != null && rlist.size() > 0){
    	    Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                AccesssoriesInventoryVo vo = new AccesssoriesInventoryVo();
                if(obj[0]!=null){vo.setStinvm_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setStf_Id(obj[1].toString());}
                if(obj[2]!=null){vo.setStore_Id(obj[2].toString());}
                if(obj[3]!=null){vo.setStinvm_Time(obj[3].toString());}
                if(obj[4]!=null){vo.setSyinvm_Sum_Count(obj[4].toString());}
                if(obj[5]!=null){vo.setStinvm_Sum_Amount(obj[5].toString());}
                if(obj[6]!=null){vo.setStinvm_Sum_Cost(obj[6].toString());}
                if(obj[7]!=null){vo.setStinvm_State(obj[7].toString());}
                if(obj[8]!=null){vo.setStinvm_Remark(obj[8].toString());}
                if(obj[9]!=null){vo.setEnterpriseId(obj[9].toString());}
                if(obj[10]!=null){vo.setStore_Name(obj[10].toString());}
                if(obj[11]!=null){vo.setStf_Name(obj[11].toString());}
                if(obj[12]!=null){vo.setStinvm_StateName(obj[12].toString());}
                list.add(vo);
            }
	    }
	    json.setRows(list);
        json.setTotal(size);
        return json;
	}
	
	public Json doFindAccessInfor(AccesssoriesInventoryVo accesssoriesInventoryVo) throws Exception {
	    List<AccesssoriesInventoryVo> list = new ArrayList<AccesssoriesInventoryVo>();
        Json json = new Json();
	    String sql ="select * from accessories_find a where a.enterprise_id="+accesssoriesInventoryVo.getEnterpriseId();
        if(accesssoriesInventoryVo.getParts_Id()!=null && !accesssoriesInventoryVo.getParts_Id().equals("")){
            sql += " and a.parts_id like '%"+StringEscapeUtils.escapeSql(accesssoriesInventoryVo.getParts_Id().trim())+"%'";
        }
        if(accesssoriesInventoryVo.getParts_Name()!=null && !accesssoriesInventoryVo.getParts_Name().equals("")){
            sql += " and a.parts_name like '%"+StringEscapeUtils.escapeSql(accesssoriesInventoryVo.getParts_Name().trim())+"%'";
        }
        if(accesssoriesInventoryVo.getStore_Id()!=null && !accesssoriesInventoryVo.getStore_Id().equals("")){
            sql += " and a.Store_ID like '%"+StringEscapeUtils.escapeSql(accesssoriesInventoryVo.getStore_Id().trim())+"%'";
        }
        List<Object[]> rlist = accesssoriesInventoryDao.createSQLQuery(sql, accesssoriesInventoryVo.getPage(), accesssoriesInventoryVo.getRows());
        int size = accesssoriesInventoryDao.getCountBySQL(sql);
        if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                AccesssoriesInventoryVo vo = new AccesssoriesInventoryVo();
                if(obj[0]!=null){vo.setParts_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setParts_Name(obj[1].toString());}
                if(obj[2]!=null){vo.setParts_Sell_Price(obj[2].toString());}
                if(obj[3]!=null){vo.setParts_Need_Point(obj[3].toString());}
                if(obj[4]!=null){vo.setStinvd_Price1(obj[4].toString());}
                if(obj[5]!=null){vo.setStinvd_Price(obj[5].toString());}
                if(obj[6]!=null){vo.setParts_Library(obj[6].toString());}
                if(obj[7]!=null){vo.setFit_Ptype(obj[7].toString());}
                if(obj[8]!=null){vo.setPtype_Name(obj[8].toString());}
                if(obj[9]!=null){vo.setPbrd_Name(obj[9].toString());}
                if(obj[10]!=null){vo.setPunit_Name(obj[10].toString());}
                if(obj[11]!=null){vo.setStore_Name(obj[11].toString());}
                list.add(vo);
            }
        }
        json.setRows(list);
        json.setTotal(size);
        return json;
	}
	
	public void doAddFather(AccesssoriesInventoryVo accesssoriesInventoryVo, BasUsers user) throws Exception {
        String inserted = accesssoriesInventoryVo.getInserted();
        List<AccesssoriesInventoryVo> insertList = JSON.parseArray(inserted,AccesssoriesInventoryVo.class);
        
        if(insertList != null && insertList.size() > 0){
            if(accesssoriesInventoryVo.getStore_Id() != null && !"".equals(accesssoriesInventoryVo.getStore_Id())){
                String id = "";
                StInventoryMain sm = new StInventoryMain();
                try {
                    id = CreateID.createId("StInventoryMain", "PD");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sm.setStfId(user.getBasStuff().getStfId());
                sm.setStinvmId(id);
                sm.setStoreId(Short.parseShort(accesssoriesInventoryVo.getStore_Id()));
                sm.setStinvmRemark(accesssoriesInventoryVo.getStinvm_Remark());
                sm.setStinvmTime(new Date());
                sm.setEnterpriseId(Integer.parseInt(accesssoriesInventoryVo.getEnterpriseId()));
                List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.AUDIT_TAG.AUDITKEY);
                if(childs != null && childs.size() > 0){
                    for(int i=0; i<childs.size(); i++){
                        BasChilddictionary c = childs.get(i);
                        if(c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITNOS)){
                            sm.setStinvmState(Contstants.AUDIT_TAG.AUDITNOS);
                            break;
                        }
                    }
                }
                inster(sm, insertList);
                stInventoryMainDao.save(sm);
            }
        }
	}
	
	public Json getStInventoryDetailById(
			AccesssoriesInventoryVo accesssoriesInventoryVo) throws Exception {
	    List<AccesssoriesInventoryVo> list = new ArrayList<AccesssoriesInventoryVo>();
        Json json = new Json();
        String sql = "SELECT a.*,b.PARTS_NAME, b.PARTS_LIBRARY, c.PUNIT_NAME FROM st_inventory_detail a, frt_parts b, bas_parts_unit c "+
        " WHERE 1=1 AND a.PARTS_ID = b.PARTS_ID AND b.PUNIT_NAME = c.PUNIT_ID "+
        " AND STINVM_ID = '"+accesssoriesInventoryVo.getStinvm_Id()+"'";
        List<Object[]> rlist = accesssoriesInventoryDao.createSQLQuery(sql, accesssoriesInventoryVo.getPage(), accesssoriesInventoryVo.getRows());
        int size = accesssoriesInventoryDao.getCountBySQL(sql);
        if(rlist != null && rlist.size() > 0){
            for (Object[] obj : rlist) {
                AccesssoriesInventoryVo newvo = new AccesssoriesInventoryVo();
                if(obj[0]!=null){newvo.setIndex_Id(obj[0].toString());}else{newvo.setIndex_Id(null);}
                if(obj[1]!=null){newvo.setStinvm_Id(obj[1].toString());}else{newvo.setStinvm_Id(null);}
                if(obj[2]!=null){newvo.setParts_Id(obj[2].toString());}else{newvo.setParts_Id(null);}
                if(obj[3]!=null){newvo.setStinvd_Count(obj[3].toString());}else{newvo.setStinvd_Count("0.0");}
                if(obj[4]!=null){newvo.setStinvd_Price(obj[4].toString());}else{newvo.setStinvd_Price("0.0");}
                if(obj[5]!=null){newvo.setStinvd_Cost(obj[5].toString());}else{newvo.setStinvd_Cost("0.0");}
                if(obj[7]!=null){newvo.setStinvd_Price1(obj[7].toString());}else{newvo.setStinvd_Price1("0.0");}
                if(obj[8]!=null){newvo.setStinvd_Cost1(obj[8].toString());}else{newvo.setStinvd_Cost1("0.0");}
                if(obj[9]!=null){newvo.setParts_Name(obj[9].toString());}else{newvo.setParts_Name("0.0");}
                if(obj[10]!=null){newvo.setParts_Library(obj[10].toString());}else{newvo.setParts_Library("0.0");}
                if(obj[11]!=null){newvo.setPunit_Name(obj[11].toString());}else{newvo.setStinvd_Cost(null);}
                list.add(newvo);
            }
        }
        json.setRows(list);
        json.setTotal(size);
        return json;
	}

	public void doDelete(AccesssoriesInventoryVo accesssoriesInventoryVo)
			throws Exception {
        String hql = "FROM StInventoryMain where stinvmId = '"+accesssoriesInventoryVo.getStinvm_Id()+"' AND enterpriseId="+accesssoriesInventoryVo.getEnterpriseId();
        List<StInventoryMain> list = stInventoryMainDao.find(hql);
        if(list != null && list.size() > 0){
            StInventoryMain sm = list.get(0);
            if(sm.getStinvmState().equals(Contstants.AUDIT_TAG.AUDITNOS)){
                stInventoryMainDao.delete(sm);
            }
        }
	}

    public void doUpdateFather(AccesssoriesInventoryVo accesssoriesInventoryVo, BasUsers user)
			throws Exception {
	    if(accesssoriesInventoryVo.getStinvm_Id() != null && !"".equals(accesssoriesInventoryVo.getStinvm_Id())){
	        if(accesssoriesInventoryVo.getStore_Id() != null && !"".equals(accesssoriesInventoryVo.getStore_Id())){
    	        String inserted = accesssoriesInventoryVo.getInserted();
    	        String updateed = accesssoriesInventoryVo.getUpdated();
    	        String deleteed = accesssoriesInventoryVo.getDeleted();
    	        List<AccesssoriesInventoryVo> insertList = JSON.parseArray(inserted,AccesssoriesInventoryVo.class);
    	        List<AccesssoriesInventoryVo> updateList = JSON.parseArray(updateed,AccesssoriesInventoryVo.class);
    	        List<AccesssoriesInventoryVo> deleteList = JSON.parseArray(deleteed,AccesssoriesInventoryVo.class);
                StInventoryMain sm = accesssoriesInventoryDao.get("FROM StInventoryMain where stinvmId = '"+accesssoriesInventoryVo.getStinvm_Id()+"' AND enterpriseId="+accesssoriesInventoryVo.getEnterpriseId());
                if(sm.getStinvmState().equals(Contstants.AUDIT_TAG.AUDITNOS)){
                    sm.setStoreId(Short.parseShort(accesssoriesInventoryVo.getStore_Id()));
                    if(insertList != null && insertList.size() > 0){
                        inster(sm, insertList);
                    }
                    if(updateList != null && updateList.size() > 0){
                        update(sm, updateList);
                    }
                    if(deleteList != null && deleteList.size() > 0){
                        delete(sm, deleteList);
                    }
                    accesssoriesInventoryDao.saveOrUpdate(sm);
                }
            }
	    }
	}
	
	private void inster(StInventoryMain sm, List<AccesssoriesInventoryVo> insertList){
	    int totalCount = 0;
        double totalCost = 0.0;
        double totalCost1 = 0.0;
        for(AccesssoriesInventoryVo v : insertList){
            if(v.getStinvd_Count() != null && !"".equals(v.getStinvd_Count()) && 
                    v.getStinvd_Price() != null && !"".equals(v.getStinvd_Price()) &&
                    v.getStinvd_Price1() != null && !"".equals(v.getStinvd_Price1())){
                int count = Integer.parseInt(v.getStinvd_Count());
                double price = Double.parseDouble(v.getStinvd_Price());
                double price1 = Double.parseDouble(v.getStinvd_Price1());
                if(count > 0 && price >= 0.0d && price1 >= 0.0d){
                    StInventoryDetail sd = new StInventoryDetail();
                    totalCount += count;
                    totalCost += count*price;
                    totalCost1 += count*price1;
                    sd.setPartsId(v.getParts_Id());
                    sd.setStinvdCount(count);
                    sd.setStinvdPrice(price);
                    sd.setStinvdCost(count*price);
                    sd.setPunitName(v.getPunit_Name());
                    sd.setStinvdPrice1(price1);
                    sd.setStinvdCost1(count*price1);
                    sd.setStInventoryMain(sm);
                    sm.getStInventoryDetails().add(sd);
                }
            }
        }
        sm.setSyinvmSumCount(sm.getSyinvmSumCount() != null ? sm.getSyinvmSumCount() : 0 + totalCount);
        sm.setStinvmSumCost(sm.getStinvmSumCost() != null ? sm.getStinvmSumCost() : 0.00d + totalCost);
        sm.setStinvmSumAmount(sm.getStinvmSumAmount() != null ? sm.getStinvmSumAmount() : 0.00d + totalCost1);
	}
	
	private void update(StInventoryMain sm, List<AccesssoriesInventoryVo> updateList)
    {
	    for(AccesssoriesInventoryVo v : updateList){
    	    Set<StInventoryDetail> set = sm.getStInventoryDetails();
    	    if(set != null){
	            for(StInventoryDetail detail : set){
	                if(detail.getIndexId() != null && detail.getIndexId().toString().equals(v.getIndex_Id().toString())){
	                    int totalCount = detail.getStinvdCount();
	                    double totalCost = detail.getStinvdCost();
	                    double totalCost1 = detail.getStinvdCost1(); 
	                    if(v.getStinvd_Count() != null && !"".equals(v.getStinvd_Count()) && 
	                            v.getStinvd_Price() != null && !"".equals(v.getStinvd_Price()) &&
	                            v.getStinvd_Price1() != null && !"".equals(v.getStinvd_Price1())){
	                        int count = Integer.parseInt(v.getStinvd_Count());
	                        double price = Double.parseDouble(v.getStinvd_Price());
	                        double price1 = Double.parseDouble(v.getStinvd_Price1());
	                        if(count > 0 && price >= 0.0d && price1 >= 0.0d){
	                            detail.setPartsId(v.getParts_Id());
	                            detail.setStinvdCount(count);
	                            detail.setStinvdPrice(price);
	                            detail.setStinvdCost(count*price);
	                            detail.setPunitName(v.getPunit_Name());
	                            detail.setStinvdPrice1(price1);
	                            detail.setStinvdCost1(count*price1);
	                            
	                            sm.setSyinvmSumCount(sm.getSyinvmSumCount() - totalCount + detail.getStinvdCount()); 
	                            sm.setStinvmSumCost(sm.getStinvmSumCost() - totalCost + detail.getStinvdCost());
	                            sm.setStinvmSumAmount(sm.getStinvmSumAmount() - totalCost1 + detail.getStinvdCost1());
	                        }
	                    }
	                }
	            }
	        }
	    }
    }
	
	private void delete(StInventoryMain sm, List<AccesssoriesInventoryVo> deleteList)
    {
	    for(AccesssoriesInventoryVo v : deleteList){
            Set<StInventoryDetail> set = sm.getStInventoryDetails();
            if(set != null){
                for(StInventoryDetail detail : set){
                    if(detail.getIndexId() != null && detail.getIndexId().equals(v.getIndex_Id())){
                        int totalCount = detail.getStinvdCount();
                        double totalCost = detail.getStinvdCost();
                        double totalCost1 = detail.getStinvdCost1();
                        set.remove(detail);
                        sm.setSyinvmSumCount(sm.getSyinvmSumCount() - totalCount ); 
                        sm.setStinvmSumCost(sm.getStinvmSumCost() - totalCost );
                        sm.setStinvmSumAmount(sm.getStinvmSumAmount() - totalCost1 );
                    }
                }
            }
        }
    }

    public Object doUpdateState(AccesssoriesInventoryVo accesssoriesInventoryVo) throws Exception
    {
        Message msg = new Message();
        if(accesssoriesInventoryVo.getStinvm_Id() != null && !"".equals(accesssoriesInventoryVo.getStinvm_Id())){
           StInventoryMain sm = accesssoriesInventoryDao.get("FROM StInventoryMain where stinvmId = '"+accesssoriesInventoryVo.getStinvm_Id()+"' AND enterpriseId="+accesssoriesInventoryVo.getEnterpriseId());
           if(sm.getStinvmState().equals(Contstants.AUDIT_TAG.AUDITNOS)){
               List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.AUDIT_TAG.AUDITKEY);
               if(childs != null && childs.size() > 0){
                   for(int i=0; i<childs.size(); i++){
                       BasChilddictionary c = childs.get(i);
                       if(c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITYESS)){
                           sm.setStinvmState(Contstants.AUDIT_TAG.AUDITYESS);
                           break;
                       }
                   }
               }
               Set<StInventoryDetail> set = sm.getStInventoryDetails();
               List<PartsChangePrice> list = new ArrayList<PartsChangePrice>();
               if(set != null){
                   for(StInventoryDetail detail : set){
                       PartsChangePrice pcpm = partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.storeId="+sm.getStoreId()+" AND pcp.partsId='"+detail.getPartsId()+"' AND pcp.enterpriseId="+accesssoriesInventoryVo.getEnterpriseId());
                       pcpm.setEnterpriseId(sm.getEnterpriseId());
                       pcpm.setPartsId(detail.getPartsId());
                       pcpm.setStoreId(sm.getStoreId());
                       pcpm.setPartsNowCount(pcpm.getPartsNowCount() + detail.getStinvdCount());
                       list.add(pcpm);
                   }
               }
               accesssoriesInventoryDao.saveOrUpdate(sm);
               partsChangePriceDAO.saveOrUpdates(list);
               msg.setSuccess(true);
               msg.setMsg("审核成功");
           }else{
               msg.setSuccess(false);
               msg.setMsg("盘点单已经被审核");
           }
        }else{
            msg.setSuccess(false);
            msg.setMsg("信息不全");
        }
        return msg;
    }
}