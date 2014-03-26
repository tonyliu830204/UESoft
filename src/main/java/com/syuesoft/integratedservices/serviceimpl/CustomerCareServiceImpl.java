package com.syuesoft.integratedservices.serviceimpl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.fbk.vo.CustomerCareVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.integratedservices.dao.CustomerCareDao;
import com.syuesoft.integratedservices.service.CustomerCareService;
import com.syuesoft.model.FbkCarGroup;
import com.syuesoft.model.FbkCarGroupId;
import com.syuesoft.model.FbkTxGroup;
import com.syuesoft.model.FrtRcptItem;
import com.syuesoft.util.Json;

@Service("customerCareService")
public class CustomerCareServiceImpl implements CustomerCareService
{

    @Autowired
    private CustomerCareDao customerCareDao;

    public CustomerCareDao getCustomerCareDao()
    {
        return customerCareDao;
    }

    public void setCustomerCareDao(CustomerCareDao customerCareDao)
    {
        this.customerCareDao = customerCareDao;
    }

    
    public List<CustomerCareVo> getAll(int page, int rows, String sql)
            throws Exception
    {
        return customerCareDao.getAll(page, rows, sql);
    }

    
    public List<CustomerCareVo> getByCondition(CustomerCareVo customerCareVo)
            throws Exception
    {
        return customerCareDao.getByCondition(customerCareVo);
    }

    
    public Datagrid doResualt(CustomerCareVo customerCareVo) throws Exception
    {
        // TODO Auto-generated method stub
        return customerCareDao.doResualt(customerCareVo);
    }

    
    public List<FbkTxGroup> getFactoryVisit(CustomerCareVo customerCareVo)
            throws Exception
    {
        return customerCareDao.getFactoryVisit(customerCareVo);
    }

    
    public void doSave(FbkTxGroup fbkTxGroup, String carId) throws Exception
    {
        customerCareDao.doSave(fbkTxGroup, carId);
    }

    
    public List getFactoryWxRecord(CustomerCareVo customerCareVo)
            throws Exception
    {
        return customerCareDao.getFactoryWxRecord(customerCareVo);
    }

    
    public void doDelete(FbkCarGroup fbkCarGroup) throws Exception
    {
        customerCareDao.doDelete(fbkCarGroup);
    }

    
    public Datagrid getRemenberSearch(CustomerCareVo customerCareVo)
            throws Exception
    {
        return customerCareDao.getRemenberSearch(customerCareVo);
    }

    
    public List getObject(String sql) throws Exception
    {

        return customerCareDao.getObject(sql);
    }

    
    public Json getBytixing(CustomerCareVo customerCareVo)
            throws Exception{
        return customerCareDao.getBytixing(customerCareVo);
    }

    
    public List<CustomerCareVo> getNianjianShen(CustomerCareVo customerCareVo)
            throws Exception
    {
        return customerCareDao.getNianjianShen(customerCareVo);

    }

    
    public List<CustomerCareVo> getSbtixing(CustomerCareVo customerCareVo)
            throws Exception
    {
        return customerCareDao.getSbtixing(customerCareVo);
    }

    
    public List<CustomerCareVo> getBxjqtixing(CustomerCareVo customerCareVo)
            throws Exception
    {
        return customerCareDao.getBxjqtixing(customerCareVo);
    }

    
    public Json getSrtixing(CustomerCareVo customerCareVo)
            throws Exception
    {
        return customerCareDao.getSrtixing(customerCareVo);
    }

    
    public void deleteftg(FbkTxGroup ftg) throws Exception
    {
        customerCareDao.deleteftg(ftg);
    }

    
    public void updateToF(CustomerCareVo customerCareVo) throws Exception
    {
        customerCareDao.updateToF(customerCareVo);

    }

    /**
     * 车辆流失 分析 查询历史维修记录 子节点维修项目名称
     */
    
    public List<FrtRcptItem> getFactoryRepairRecordChild(
            CustomerCareVo customerCareVo) throws Exception
    {
        return customerCareDao.getFactoryRepairRecordChild(customerCareVo);
    }

	
	public Datagrid getHistoricalVisit(CustomerCareVo customerCareVo) throws Exception {
		Datagrid d=new Datagrid();
		String sql="SELECT   ftg.RETURN_VISIT_DATE,ftg.TX_RETURN_VISIT_DATE,ftg.GROUP_NAME,(SELECT  c.dataValue FROM    bas_parentdictionary p ,bas_childdictionary c WHERE p.parent_id=c.parent_id AND c.dataKey=ftg.GROUP_NAME) AS GROUP_NAME_value," +
				"ftg.VISIT_CONTENT,ftg.TX_RESAULT,(SELECT  c.dataValue FROM    bas_parentdictionary p ,bas_childdictionary c WHERE p.parent_id=c.parent_id AND c.dataKey=ftg.TX_RESAULT) AS TX_RESAULT_value," +
				"ftg.CAR_LOST,(SELECT  c.dataValue FROM    bas_parentdictionary p ,bas_childdictionary c WHERE p.parent_id=c.parent_id AND c.dataKey=ftg.CAR_LOST) AS CAR_LOST_value"+
				   "	,ftg.G_ID	FROM  fbk_tx_group  ftg LEFT JOIN fbk_car_group  fcg  ON ftg.G_ID=fcg.G_ID WHERE fcg.carId='"+customerCareVo.getCar_Id()+"' AND ftg.GROUP_NAME='"+customerCareVo.getGroup_Name()+"'";
		List<Object[]> objs=customerCareDao.createSQLQuery(sql);
		List<CustomerCareVo> lst=new ArrayList<CustomerCareVo>();
		if(objs!=null && objs.size()>0){
			for(Object [] obj:objs){
				CustomerCareVo vo=new CustomerCareVo();
				if(obj[0]!=null && !("".equals(obj[0]))){
					vo.setReturn_Visit_Date(obj[0].toString());
				}
				if(obj[1]!=null && !("".equals(obj[1]))){
					vo.setTx_Return_Visit_Date(obj[1].toString());
				}
				if(obj[2]!=null && !("".equals(obj[2]))){
					vo.setGroup_Name(obj[2].toString());
				}
				if(obj[3]!=null && !("".equals(obj[3]))){
					vo.setGroup_Name_value(obj[3].toString());
				}
				if(obj[4]!=null && !("".equals(obj[4]))){
					vo.setVisit_Content(obj[4].toString());
				}
				
				if(obj[5]!=null && !("".equals(obj[4]))){
					vo.setTx_Resault(obj[5].toString());
				}
				if(obj[6]!=null && !("".equals(obj[6]))){
					vo.setTx_Resault_value(obj[6].toString());
				}
				if(obj[7]!=null && !("".equals(obj[7]))){
					vo.setCar_lost(obj[7].toString());
				}
				if(obj[8]!=null && !("".equals(obj[8]))){
					vo.setCar_lost_value(obj[8].toString());
				}
				if(obj[9]!=null && !("".equals(obj[9]))){
					vo.setG_Id(obj[9].toString());
				}
				lst.add(vo);
			}
		}
		d.setRows(lst);
		d.setTotal(customerCareDao.getSQLCount(sql, null));
		return d;
	}

	
	public void saveReminder(CustomerCareVo customerCareVo) throws Exception {
		String gId=customerCareVo.getG_Id();
		if(gId==null || "".equals(gId)){
			FbkTxGroup fbkTxGroup=new FbkTxGroup();
			getFbkTxGroup(fbkTxGroup,customerCareVo);
			fbkTxGroup.setEnterpriseId(customerCareVo.getEnterpriseId());
			Serializable s=customerCareDao.save(fbkTxGroup);
			FbkCarGroupId id=new FbkCarGroupId();
			id.setCarId(customerCareVo.getCar_Id());
			id.setGId((Integer) s);
			
			FbkCarGroup carGroup=new FbkCarGroup();
			carGroup.setId(id);
			carGroup.setEnterpriseId(customerCareVo.getEnterpriseId());
			customerCareDao.save(carGroup);
		}else{
			updateReminder( customerCareVo);
		}
		
		
	}

	
	public void updateReminder(CustomerCareVo customerCareVo) throws Exception {
		FbkTxGroup  fbkTxGroup=(FbkTxGroup) customerCareDao.get("from  FbkTxGroup f where  f.GId="+customerCareVo.getG_Id());
		getFbkTxGroup(fbkTxGroup,customerCareVo);
		customerCareDao.update(fbkTxGroup);
		
	}

	
	public void deleteReminder(CustomerCareVo customerCareVo) throws Exception {
		FbkCarGroup carGroup=(FbkCarGroup) customerCareDao.get("from  FbkCarGroup f where  f.id.GId="+customerCareVo.getG_Id());
		customerCareDao.delete(carGroup);
		FbkTxGroup  fbkTxGroup=(FbkTxGroup) customerCareDao.get("from  FbkTxGroup f where  f.GId="+customerCareVo.getG_Id());
		customerCareDao.delete(fbkTxGroup);
		
		
	}
	public FbkTxGroup getFbkTxGroup(FbkTxGroup gVo,CustomerCareVo customerCareVo) throws ParseException{
		SimpleDateFormat formart=new SimpleDateFormat("yyyy-MM-dd");
		if(customerCareVo.getG_Id()!=null && !("".equals(customerCareVo.getG_Id()))){
			gVo.setGId(Integer.parseInt(customerCareVo.getG_Id()));
		}
		if(customerCareVo.getGroup_Name()!=null && !("".equals(customerCareVo.getGroup_Name()))){
			gVo.setGroupName(customerCareVo.getGroup_Name().toString());
		}
		if(customerCareVo.getReturn_Visit_Date()!=null && !("".equals(customerCareVo.getReturn_Visit_Date()))){
			gVo.setReturnVisitDate(formart.parse(customerCareVo.getReturn_Visit_Date()));
		}
		if(customerCareVo.getTx_Return_Visit_Date()!=null && !("".equals(customerCareVo.getTx_Return_Visit_Date()))){
			gVo.setTxReturnVisitDate(formart.parse(customerCareVo.getTx_Return_Visit_Date()));
		}
		if(customerCareVo.getVisit_Content()!=null && !("".equals(customerCareVo.getVisit_Content()))){
			gVo.setVisitContent(customerCareVo.getVisit_Content());
		}
		if(customerCareVo.getTx_Resault()!=null && !("".equals(customerCareVo.getTx_Resault()))){
			gVo.setTxResault(customerCareVo.getTx_Resault());
		}
		if(customerCareVo.getStatus_Name()!=null && !("".equals(customerCareVo.getStatus_Name()))){
			gVo.setStatusName(customerCareVo.getStatus_Name());
		}
		if(customerCareVo.getTx_Resault()!=null && !("".equals(customerCareVo.getTx_Resault()))){
			gVo.setTxResault(customerCareVo.getTx_Resault());
		}
		if(customerCareVo.getTx_Status()!=null && !("".equals(customerCareVo.getTx_Status()))){
			gVo.setTxStatus(Integer.parseInt(customerCareVo.getTx_Status()));
		}
		if(customerCareVo.getCar_lost()!=null && !("".equals(customerCareVo.getCar_lost()))){
			gVo.setCarLost(customerCareVo.getCar_lost());
		}
		return gVo;
		
	}
	
	
	
}
