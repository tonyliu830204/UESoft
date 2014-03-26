package com.syuesoft.integratedservices.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fbk.vo.TrackManagementVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.integratedservices.dao.CustomerGzManageDao;
import com.syuesoft.integratedservices.service.CustomerGzManageService;
import com.syuesoft.model.FbkCarDcname;
import com.syuesoft.model.FbkCarDcnameId;
import com.syuesoft.model.FbkCollect;
import com.syuesoft.model.FbkDcservey;
import com.syuesoft.model.FbkDcserveyName;
import com.syuesoft.model.FbkDetail;
import com.syuesoft.model.FrtPreClearing;
import com.syuesoft.sell.instore.vo.SellInstorehouseVo;
import com.syuesoft.sell.instore.vo.SellRetreatVo;

@Service("customerGzManageService")
public class CustomerGzManageServiceImpl implements CustomerGzManageService
{
    @Autowired
    private CustomerGzManageDao customerGzManageDao;

    public CustomerGzManageDao getCustomerGzManageDao()
    {
        return customerGzManageDao;
    }

    public void setCustomerGzManageDao(CustomerGzManageDao customerGzManageDao)
    {
        this.customerGzManageDao = customerGzManageDao;
    }

    
    public Datagrid doFindAll(int page, int rows,Integer  enterpriseId)
            throws Exception
    {
        return customerGzManageDao.doFindAll(page, rows, enterpriseId);
    }
    
    public Datagrid doFindCollect(int page, int rows,String carId)
            throws Exception
    {
        return customerGzManageDao.doFindCollect(page, rows,carId);
    }

    
    public List<TrackManagementVo> getByCondition(
            TrackManagementVo trackManagementVo) throws Exception
    {
        return customerGzManageDao.getByCondition(trackManagementVo);
    }

    
    public List<FbkDcservey> getDcServeyById(int carId) throws Exception
    {
        return customerGzManageDao.getDcServeyById(carId);
    }

    // 查询3DC调查表的维修吸项目名称
    
    public List<FbkDcserveyName> getFbkDcserveyName(String str)
            throws Exception
    {
        return customerGzManageDao.getFbkDcserveyName(str);
    }

    // 对明细表的信息修改
    
    public void doUpdate(TrackManagementVo trackManagementVo) throws Exception
    {
        customerGzManageDao.doUpdate(trackManagementVo);
    }

    
    public void updateFbkCarDcname(TrackManagementVo trackManagementVo,
            int carid) throws Exception
    {
        customerGzManageDao.updateFbkCarDcname(trackManagementVo, carid);
    }

    
    public void updateCollect(TrackManagementVo trackManagementVo)
            throws Exception
    {
        customerGzManageDao.updateCollect(trackManagementVo);
    }

    
    public List<FbkCarDcname> getFbkCarDcname(
            TrackManagementVo trackManagementVo) throws Exception
    {
        return customerGzManageDao.getFbkCarDcname(trackManagementVo);
    }

    
    public List<FbkDcserveyName> getFbkDcserveyNameByid(int dcNameId)
            throws Exception
    {
        return customerGzManageDao.getFbkDcserveyNameByid(dcNameId);
    }

    
    public List getBasRepairTypeName() throws Exception
    {
        return customerGzManageDao.getBasRepairTypeName();
    }

	
	public Datagrid doFindCarDcname(int page, int rows,String collectId) throws Exception {
		Datagrid d=new Datagrid();
		String sql="SELECT f.SERVEY_NAME,a.scoreId,a.evaluate,a.evaluateOne,a.evaluateTwo,a.evaluateThree,a.evaluateFour,a.evaluateFive,f.DC_NAME_ID,a. collectId,a.fcdNote,a.carDcnameId,a.carDcValue FROM"+
					"	(SELECT  fdn.SERVEY_NAME AS serberName,fcd.score AS scoreId,fcd.EVALUATE AS evaluate,"+
					"	(CASE fcd.EVALUATE WHEN 'evaluate1' THEN '√' ELSE '' END) AS  evaluateOne,"+
					"	(CASE fcd.EVALUATE WHEN 'evaluate2' THEN '√' ELSE '' END)  AS evaluateTwo,"+
					"	(CASE fcd.EVALUATE WHEN 'evaluate3' THEN '√' ELSE '' END)  AS evaluateThree ,"+
					"	(CASE fcd.EVALUATE WHEN 'evaluate4' THEN '√' ELSE '' END)  AS evaluateFour,"+
					"	(CASE fcd.EVALUATE WHEN 'evaluate5' THEN '√' ELSE '' END)  AS evaluateFive ,"+
					"	fcd.DC_NAME_ID AS dcNameId,fcd.collect_id AS collectId,fcd.note AS fcdNote,fcd.id   AS carDcnameId, " +
					"   (SELECT c.dataValue  FROM bas_parentdictionary p ,bas_childdictionary c WHERE c.parent_id=p.parent_id AND p.dataKey='score' AND c.dataKey=scoreId) AS carDcValue" +
					"	FROM  fbk_dcservey_name  fdn  LEFT JOIN  fbk_car_dcname  fcd"+
					"	ON fdn.dc_name_id=fcd.dc_name_id WHERE fcd.collect_id='"+collectId+"')a  RIGHT JOIN fbk_dcservey_name f "+
					"	ON a.dcNameId=f.DC_NAME_ID";
		List<Object[]> lst=customerGzManageDao.createSQLQuery(sql, page, rows);
		List<TrackManagementVo> vos=new ArrayList<TrackManagementVo>();
		if(lst!=null && lst.size()>0){
			for(Object [] obj:lst){
				TrackManagementVo vo=new TrackManagementVo();
				if(obj[0]!=null && !("".equals(obj[0]))){
					vo.setProjectName(obj[0].toString());
				}
				if(obj[1]!=null && !("".equals(obj[1]))){
					vo.setScore(obj[1].toString());
				}
				if(obj[2]!=null && !("".equals(obj[2]))){
					vo.setEvaluate(obj[2].toString());
				}
				if(obj[3]!=null && !("".equals(obj[3]))){
					vo.setEvaluateOne(obj[3].toString());
				}
				if(obj[4]!=null && !("".equals(obj[4]))){
					vo.setEvaluateTwo(obj[4].toString());
				}
				if(obj[5]!=null && !("".equals(obj[5]))){
					vo.setEvaluateThree(obj[5].toString());
				}
				if(obj[6]!=null && !("".equals(obj[6]))){
					vo.setEvaluateFour(obj[6].toString());
				}
				if(obj[7]!=null && !("".equals(obj[7]))){
					vo.setEvaluateFive(obj[7].toString());
				}
				if(obj[8]!=null && !("".equals(obj[8]))){
					vo.setDcNameId(obj[8].toString());
				}
				if(obj[9]!=null && !("".equals(obj[9]))){
					vo.setPreclrId(obj[9].toString());
				}
				if(obj[10]!=null && !("".equals(obj[10]))){
					vo.setPreclrId(obj[10].toString());
				}
				if(obj[11]!=null && !("".equals(obj[11]))){
					vo.setDcId(obj[11].toString());
				}
				if(obj[12]!=null && !("".equals(obj[12]))){
					vo.setScoreName(obj[12].toString());
				}
				vos.add(vo);
			}
		}
		d.setRows(vos);
		d.setTotal(customerGzManageDao.getSQLCount(sql, null));
		return d;
	}

	
	public void updateFbkCollect(TrackManagementVo trackManagementVo)
			throws Exception {
		TrackManagementVo summary = JSON.parseObject(trackManagementVo.getCollectInformation(), TrackManagementVo.class);	
		String collectId=summary.getCollectId();
		String detailId=summary.getDetailId();
		String preclrId=summary.getPreclrId();
		FrtPreClearing pre=(FrtPreClearing) customerGzManageDao.get("from  FrtPreClearing f where f.preclrId='"+preclrId+"'");
		FbkCollect collect=new FbkCollect();
		collect=convertVoCollect(collect,summary,pre);
		collect.setEnterpriseId(trackManagementVo.getEnterpriseId());
		customerGzManageDao.saveOrUpdate(collect);
		TrackManagementVo detail = JSON.parseObject(trackManagementVo.getFbkDetail(), TrackManagementVo.class);
		TrackManagementVo detail1 = JSON.parseObject(trackManagementVo.getFbkDetail1(), TrackManagementVo.class);
		FbkDetail fbkDetail=new FbkDetail();
		fbkDetail=convertVoCollect(fbkDetail,detail);
		fbkDetail=convertVoCollect(fbkDetail,detail1);
		fbkDetail.setFbkCollect(collect);
		if(detailId!=null && !("".equals(detailId))){
			fbkDetail.setDetailId(Integer.valueOf(detailId));
		}
		fbkDetail.setEnterpriseId(trackManagementVo.getEnterpriseId());
		customerGzManageDao.saveOrUpdate(fbkDetail);
		List<TrackManagementVo> visits = JSON.parseArray(trackManagementVo.getVisit3Dc(), TrackManagementVo.class);
		if(visits!=null && visits.size()>0){
			for(TrackManagementVo t:visits){
				if(t.getDcId()!=null && !("".equals(t.getDcId()))){
					FbkCarDcname c=(FbkCarDcname) customerGzManageDao.get("from  FbkCarDcname f where f.id='"+t.getDcId()+"'");
					c=convertVoCar(c,t);
					customerGzManageDao.update(c);
				}else{
					FbkCarDcname dcName=new  FbkCarDcname();
					dcName=convertVoCar(dcName,t);
					FbkDcserveyName	dcserveyName=(FbkDcserveyName) customerGzManageDao.get("from  FbkDcserveyName f where f.dcNameId='"+t.getDcNameId()+"'");
				    dcName.setFbkCollect(collect);
					dcName.setFbkDcserveyName(dcserveyName);
					dcName.setEnterpriseId(trackManagementVo.getEnterpriseId());
					customerGzManageDao.save(dcName);
				}
				
			}
		}
		
	}
	public FbkCollect convertVoCollect(FbkCollect c,TrackManagementVo vo,FrtPreClearing pre) throws ParseException{
		SimpleDateFormat formart=new SimpleDateFormat("yyyy-MM-dd");
		if(vo.getCollectId()!=null && !("".equals(vo.getCollectId()))){
			c.setCollectId(Integer.valueOf(vo.getCollectId()));
		}else{
			
		}
		if(vo.getReturnVisitMembers()!=null && !("".equals(vo.getReturnVisitMembers()))){
			c.setReturnVisitMembers(vo.getReturnVisitMembers());
		}
		if(vo.getCollectId()!=null && !("".equals(vo.getCollectId()))){
			c.setCollectId(Integer.valueOf(vo.getCollectId()));
		}
		if(vo.getCallSituation()!=null && !("".equals(vo.getCallSituation()))){
			c.setCallSituation(vo.getCallSituation());
		}
		if(vo.getSatisfaction()!=null && !("".equals(vo.getSatisfaction()))){
			c.setSatisfaction(vo.getSatisfaction());
		}
		if(vo.getNotSatisfaction()!=null && !("".equals(vo.getNotSatisfaction()))){
			c.setNotSatisfaction(vo.getNotSatisfaction());
		}
		if(vo.getReturnVisitDate()!=null && !("".equals(vo.getReturnVisitDate()))){
			c.setReturnVisitDate(formart.parse(vo.getReturnVisitDate()));
		}
		if(vo.getHandlePerson()!=null && !("".equals(vo.getHandlePerson()))){
			c.setHandlePerson(vo.getHandlePerson());
		}
		if(vo.getReciptReturnvisit()!=null && !("".equals(vo.getReciptReturnvisit()))){
			c.setReciptReturnvisit(vo.getReciptReturnvisit());
		}
		if(vo.getReturnSituatiom()!=null && !("".equals(vo.getReturnSituatiom()))){
			c.setReturnSituatiom(Integer.parseInt(vo.getReturnSituatiom()));
		}
		if(vo.getPreclrId()!=null && !("".equals(vo.getPreclrId()))){
			
			c.setFrtPreClearing(pre);
		}
		return c;
	}
	public FbkDetail convertVoCollect(FbkDetail c,TrackManagementVo vo){
		/*if(vo.getCollectId()!=null && !("".equals(vo.getCollectId()))){
			c.getFbkCollect().setCollectId(Integer.parseInt(vo.getCollectId()));
		}*/
		if(vo.getServiceProposal()!=null && !("".equals(vo.getServiceProposal()))){
			c.setServiceProposal(vo.getServiceProposal());
		}
		if(vo.getComplaintType()!=null && !("".equals(vo.getComplaintType()))){
			c.setComplaintType(vo.getComplaintType());
		}
		if(vo.getComplaintDegree()!=null && !("".equals(vo.getComplaintDegree()))){
			c.setComplaintDegree(vo.getComplaintDegree());
		}
		if(vo.getComplaintContent()!=null && !("".equals(vo.getComplaintContent()))){
			c.setComplaintContent(vo.getComplaintContent());
		}
		if(vo.getHandleResult()!=null && !("".equals(vo.getHandleResult()))){
			
			c.setHandleResult(vo.getHandleResult());
			
		}
		if(vo.getHandleProgram()!=null && !("".equals(vo.getHandleProgram()))){
			c.setHandleProgram(vo.getHandleProgram());
		}
		if(vo.getMemo()!=null && !("".equals(vo.getMemo()))){
			c.setMemo(vo.getMemo());
		}
		if(vo.getComplaintQK()!=null && !("".equals(vo.getComplaintQK()))){
			c.setComplaintQK(Integer.parseInt(vo.getComplaintQK()));
		}
		return c;
	}
	public FbkCarDcname convertVoCar(FbkCarDcname c,TrackManagementVo vo){
		Map<String, String>map=new HashMap<String, String>();
		 map.put(Contstants.EVALUATE.EVALUATE1, vo.getEvaluateOne());
		 map.put(Contstants.EVALUATE.EVALUATE2, vo.getEvaluateTwo());
		 map.put(Contstants.EVALUATE.EVALUATE3, vo.getEvaluateThree());
		 map.put(Contstants.EVALUATE.EVALUATE4, vo.getEvaluateFour());
		 map.put(Contstants.EVALUATE.EVALUATE5, vo.getEvaluateFive());
		 Set set=map.keySet();
		Iterator it=set.iterator();
		while(it.hasNext()){
			String key=(String) it.next();
			String value=map.get(key);
			if(value!=null && !("").equals(value)){
				c.setEvaluate(key);
			}
		}
		 
		if(vo.getScore()!=null && !("".equals(vo.getScore()))){
			c.setScore(vo.getScore());
		}
		if(vo.getNote()!=null && !("".equals(vo.getNote()))){
			c.setNote(vo.getNote());
		}
		if(vo.getDcId()!=null && !("".equals(vo.getDcId()))){
			c.setId(Integer.parseInt(vo.getDcId()));
		}
		return c;
	}

	
	public void deleteFbkCollect(String collectId) throws Exception {
		FbkCollect collect=(FbkCollect) customerGzManageDao.get("from FbkCollect c where c.collectId="+collectId);	
		FbkDetail detail=(FbkDetail) customerGzManageDao.get("from FbkDetail d where d.fbkCollect.collectId="+collect.getCollectId());
		customerGzManageDao.delete(detail);
		List<Object> lst=customerGzManageDao.find("from  FbkCarDcname  f where f.fbkCollect.collectId="+collect.getCollectId());
		if(lst!=null && lst.size()>0){
			for(Object f:lst){
				FbkCarDcname d=(FbkCarDcname)f;
				customerGzManageDao.delete(d);
			}
		}
		customerGzManageDao.delete(collect);
	}

}
