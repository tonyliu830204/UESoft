package com.syuesoft.fin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.dao.StRechargeDao;
import com.syuesoft.fin.dao.StSellPerchargeDao;
import com.syuesoft.fin.service.StSellPerchargeService;
import com.syuesoft.fin.vo.StSellPerchargeVo;
import com.syuesoft.frt.dao.FrtCarDao;
import com.syuesoft.model.FrtCar;
import com.syuesoft.model.StRecharge;
import com.syuesoft.model.StSellPercharge;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;

@Service("stSellPerchargeService")
public class StSellPerchargeServiceImpl implements StSellPerchargeService
{

    @Autowired
    StSellPerchargeDao stSellPerchargeDao;
    @Autowired
    BasStuffDao basStuffDao;
    @Autowired
    BaseService baseService;
    @Autowired
    FrtCarDao frtCarDao;
    @Autowired
    StRechargeDao stRechargeDao;

    /**
     * 维修预收款信息 预加载
     */
    public Json loadPreStSellPercharge(StSellPerchargeVo sspvo) throws Exception{
        return stSellPerchargeDao.loadPreStSellPercharge(sspvo.getPage(), sspvo
                .getRows(),sspvo.getPerchargeDateBegin(),sspvo.getPerchargeDateEnd(),sspvo.getEnterpriseId());
    }

    /**
     * 维修预收款信息 综合查询
     */
    public Json searchPreStSellPerchargeByCondition(
            StSellPerchargeVo sspvo) throws Exception{
        return stSellPerchargeDao.searchPreStSellPerchargeByCondition(sspvo
                .getPerchargeDateBegin(), sspvo.getPerchargeDateEnd(), sspvo
                .getCarLicense(), sspvo.getVin());
    }

    /**
     * 维修储备金信息 预加载
     */
    public Json loadStSellPercharge(StSellPerchargeVo sspvo)
            throws Exception{
        return stSellPerchargeDao.loadStSellPercharge(sspvo.getPage(), sspvo.getRows(),sspvo.getPerchargeDateBegin(),sspvo.getPerchargeDateEnd(),sspvo.getEnterpriseId());
    }

    /**
     * 维修储备金信息 综合查询
     */
    public Json searchStSellPerchargeByCondition(
            StSellPerchargeVo sspvo) throws Exception{
        return stSellPerchargeDao.searchStSellPerchargeByCondition(sspvo
                .getPerchargeDateBegin(), sspvo.getPerchargeDateEnd(), sspvo
                .getCarLicense(), sspvo.getVin(),sspvo.getEnterpriseId());
    }
    
    /**
     * 维修储备金 车辆信息 预加载
     */
    public Json loadFrtCarInfo(StSellPerchargeVo sspvo) throws Exception
    {
        return stSellPerchargeDao.loadFrtCarInfo(sspvo.getPage(), sspvo
                .getRows(),sspvo.getEnterpriseId());
    }

    /**
     * 维修储备金 车辆信息 综合查询
     */
    public Json searchFrtCarInfoByCondition(StSellPerchargeVo sspvo) throws Exception{
        return stSellPerchargeDao.searchFrtCarInfoByCondition(sspvo
                .getCarLicense(),sspvo.getEnterpriseId());
    }

    /**
     * 数据加载
     */
    public List<ComboBoxJson> loadData(String key) throws Exception
    {
        return stSellPerchargeDao.loadData(key);
    }

    /**
     * 维修储备金信息 添加
     */
    public void saveStSellPercharge(StSellPerchargeVo sspVo) throws Exception{
    	if(sspVo.getCarId()!=null&&!sspVo.getCarId().trim().equals("")){
    	        StRecharge sr = stRechargeDao.get("from StRecharge s where s.perchargeType!='预收款' and s.frtCar.carId='"+ sspVo.getCarId().trim()+ "'");
	            if(sr!=null&&!sr.equals("")){
	            	sr.setFrtCar(frtCarDao.get(FrtCar.class,sspVo.getCarId().trim()));
	                sr.setSurplusMoney(sr.getSurplusMoney() + sspVo.getCurPercharge());
	                sr.setPerchargeType("储备金");
	                sr.setEnterpriseId(sspVo.getEnterpriseId());
	                stRechargeDao.merge(sr);
	            }else{
	            	sr = new StRecharge();
	            	FrtCar fr=frtCarDao.get(" from FrtCar frtCar where frtCar.carId='" + sspVo.getCarId()+"'");
	            	if(fr!=null)
	            	    sr.setFrtCar(fr);
    	            if(sspVo.getCurPercharge()!=null&&!sspVo.getCurPercharge().equals(""))
    	                sr.setSurplusMoney(sspVo.getCurPercharge());
    	            sr.setPerchargeType("储备金");
    	            sr.setEnterpriseId(sspVo.getEnterpriseId());
    	            stRechargeDao.save(sr);
	            }
    	        StSellPercharge ssp = new StSellPercharge();
    	        ssp.setPerchargeId(CreateID.createId("StSellPercharge",Contstants.SERVICEBEFOREGATHERING));
    	        ssp.setStRecharge(sr);
    	        if(sspVo.getStfId()!=null&&!sspVo.getStfId().equals(""))
    	            ssp.setBasStuff(basStuffDao.get("from BasStuff bs where bs.stfId="+ sspVo.getStfId()));
    	        if(sspVo.getPaymentId()!=null&&!sspVo.getPaymentId().trim().equals(""))
    	             ssp.setBasChilddictionary(baseService.findChildById(sspVo.getPaymentId().trim()));
    	        if(sspVo.getPerchargeDate()!=null&&!sspVo.getPerchargeDate().equals(""))
    	             ssp.setPerchargeDate(sspVo.getPerchargeDate());
    	        if(sspVo.getCurPercharge()!=null&&!sspVo.getCurPercharge().equals(""))
    	             ssp.setCurPercharge(sspVo.getCurPercharge());
    	        if(sspVo.getPreclrInoiceType()!=null&&!sspVo.getPreclrInoiceType().trim().equals(""))
    	             ssp.setPreclrInoiceType(sspVo.getPreclrInoiceType().trim());
    	        if(sspVo.getPreclrNo()!=null&&!sspVo.getPreclrNo().trim().equals(""))
    	             ssp.setPreclrNo(sspVo.getPreclrNo().trim());
    	        if(sspVo.getChargeRemark()!=null&&!sspVo.getChargeRemark().trim().equals(""))
    	             ssp.setChargeRemark(sspVo.getChargeRemark().trim());
    	        ssp.setFlag(0);
    	        ssp.setEnterpriseId(sspVo.getEnterpriseId());
    	        stSellPerchargeDao.save(ssp);
    	}
    }
    
    /**
     * 维修储备金/预收款信息    删除
     */
    public void deleteStSellPercharge(StSellPerchargeVo sspVo) throws Exception{
    	if(sspVo.getPerchargeId()!=null&&!sspVo.getPerchargeId().trim().equals("")){
    		StSellPercharge ssp = stSellPerchargeDao.get(" from StSellPercharge ssp where ssp.perchargeId='"+sspVo.getPerchargeId()+"'");
            StRecharge sr = stRechargeDao.get("from StRecharge s where s.rechargeId='"+ ssp.getStRecharge().getRechargeId() + "'");
            if(ssp!=null&&!ssp.equals("")){
    	        if(sr!=null&&!sr.equals("")&&sr.getSurplusMoney()>0){
    	            sr.setSurplusMoney(sr.getSurplusMoney() - ssp.getCurPercharge());
    	            stRechargeDao.merge(sr);
    	        }
            	ssp.setFlag(1);//1代表删除  0代表不删除
                stSellPerchargeDao.merge(ssp);
            }
    	}
    }
    
    /**
     * 维修储备金信息 修改
     */
    public void updateStSellPercharge(StSellPerchargeVo sspVo) throws Exception
    {
    	if(sspVo.getPerchargeId()!=null&&!sspVo.getPerchargeId().trim().equals("")){
    		StSellPercharge ssp = stSellPerchargeDao.get(StSellPercharge.class,sspVo.getPerchargeId());
            if(sspVo.getCarId()!=null&&!sspVo.getCarId().trim().equals("")){
    	        Boolean isno = stRechargeDao.isExist(sspVo.getCarId());
    	        StRecharge sr = new StRecharge();
    	        if (isno){
    	            sr = stRechargeDao.get("from StRecharge s where  s.perchargeType!='预收款' and s.frtCar.carId='" + sspVo.getCarId() + "'");
    	            if(sr!=null&&!sr.equals("")){
    	               sr.setSurplusMoney(sr.getSurplusMoney() - ssp.getCurPercharge() + sspVo.getCurPercharge());
    	               stRechargeDao.merge(sr);
    	            }
    	        }
    	        else{
    	            sr.setFrtCar(frtCarDao.get(FrtCar.class,sspVo.getCarId().trim()));
    	            if(sspVo.getCurPercharge()!=null&&!sspVo.getCurPercharge().equals(""))
    	               sr.setSurplusMoney(sspVo.getCurPercharge());
    	            sr.setPerchargeType("储备金");
    	            stRechargeDao.save(sr);
    	        }
    	        ssp.setStRecharge(sr);
    	        if(sspVo.getPaymentId()!=null&&!sspVo.getPaymentId().trim().equals(""))
    	            ssp.setBasChilddictionary(baseService.findChildById(sspVo.getPaymentId().trim()));
    	        if(sspVo.getPerchargeDate()!=null&&!sspVo.getPerchargeDate().equals(""))
    	            ssp.setPerchargeDate(sspVo.getPerchargeDate());
    	        if(sspVo.getCurPercharge()!=null&&!sspVo.getCurPercharge().equals(""))
    	            ssp.setCurPercharge(sspVo.getCurPercharge());
    	        if(sspVo.getPreclrInoiceType()!=null&&!sspVo.getPreclrInoiceType().trim().equals(""))
    	            ssp.setPreclrInoiceType(sspVo.getPreclrInoiceType().trim());
    	        if(sspVo.getPreclrNo()!=null&&!sspVo.getPreclrNo().trim().equals(""))
    	            ssp.setPreclrNo(sspVo.getPreclrNo().trim());
    	        if(sspVo.getChargeRemark()!=null&&!sspVo.getChargeRemark().trim().equals(""))
    	            ssp.setChargeRemark(sspVo.getChargeRemark().trim());
                stSellPerchargeDao.merge(ssp);
            }
    	}
    }

    /**
     * 维修预收款信息      添加
     */
    public void savePreStSellPercharge(StSellPerchargeVo sspVo)
            throws Exception{
    	if(sspVo.getCarId()!=null&&!sspVo.getCarId().trim().equals("")){
	        Boolean isno = stRechargeDao.isPreExist(sspVo.getCarId());//判断维修预收款汇总信息是否存在
	        StRecharge sr = new StRecharge();
	        if (isno){//存在
	            sr = stRechargeDao.get("from StRecharge s where s.perchargeType!='储备金' and s.frtCar.carId='" + sspVo.getCarId() + "'");
	            if(sr!=null&&!sr.equals("")){
	            	 if(sspVo.getCarId()!=null&&!sspVo.getCarId().trim().equals(""))
	            	    sr.setFrtCar(frtCarDao.get(" from FrtCar frtCar where frtCar.carId="+ sspVo.getCarId()));
	            	 if(sspVo.getCurPercharge()!=null&&!sspVo.getCurPercharge().equals(""))
	            	    sr.setSurplusMoney(sr.getSurplusMoney() + sspVo.getCurPercharge());
	                 sr.setPerchargeType("预收款");
	                 stRechargeDao.merge(sr);
	            }
	        }else{//不存在
	        	if(sspVo.getCarId()!=null&&!sspVo.getCarId().trim().equals(""))
	                sr.setFrtCar(frtCarDao.get(" from FrtCar frtCar where frtCar.carId=" + sspVo.getCarId().trim()));
	        	if(sspVo.getCurPercharge()!=null&&!sspVo.getCurPercharge().equals(""))
	        	    sr.setSurplusMoney(sspVo.getCurPercharge());
	            sr.setPerchargeType("预收款");
	            sr.setEnterpriseId(sspVo.getEnterpriseId());
	            stRechargeDao.save(sr);
	        }
	        StSellPercharge ssp = new StSellPercharge();
	        ssp.setPerchargeId(CreateID.createId("StSellPercharge", Contstants.SERVICEBEFOREGATHERING));
	        ssp.setStRecharge(sr);
	        if(sspVo.getStfId()!=null&&!sspVo.getStfId().equals(""))
	            ssp.setBasStuff(basStuffDao.get(" from BasStuff bs where bs.stfId="+ sspVo.getStfId()));
	        if(sspVo.getPaymentId()!=null&&!sspVo.getPaymentId().trim().equals(""))
	            ssp.setBasChilddictionary(baseService.findChildById(sspVo.getPaymentId().trim()));
	        if(sspVo.getPerchargeDate()!=null&&!sspVo.getPerchargeDate().equals(""))
	            ssp.setPerchargeDate(sspVo.getPerchargeDate());
	        if(sspVo.getCurPercharge()!=null&&!sspVo.getCurPercharge().equals(""))
	            ssp.setCurPercharge(sspVo.getCurPercharge());
	        if(sspVo.getPreclrInoiceType()!=null&&!sspVo.getPreclrInoiceType().trim().equals(""))
	            ssp.setPreclrInoiceType(sspVo.getPreclrInoiceType().trim());
	        if(sspVo.getPreclrNo()!=null&&!sspVo.getPreclrNo().trim().equals(""))
	            ssp.setPreclrNo(sspVo.getPreclrNo().trim());
	        if(sspVo.getChargeRemark()!=null&&!sspVo.getChargeRemark().trim().equals(""))
	            ssp.setChargeRemark(sspVo.getChargeRemark().trim());
	        ssp.setFlag(0);
	        ssp.setEnterpriseId(sspVo.getEnterpriseId());
	        stSellPerchargeDao.save(ssp);
    	}
    }

    /**
     * 维修预收款信息 修改
     */
    public void updatePreStSellPercharge(StSellPerchargeVo sspVo)
            throws Exception
    {
    	if(sspVo.getPerchargeId()!=null&&!sspVo.getPerchargeId().trim().equals("")){
    		StSellPercharge ssp = stSellPerchargeDao.get(StSellPercharge.class,sspVo.getPerchargeId());
            Boolean isno = stRechargeDao.isPreExist(sspVo.getCarId());
            StRecharge sr = new StRecharge();
            if (isno){
            	if(sspVo.getCarId()!=null&&!sspVo.getCarId().trim().equals("")){
            		 sr = stRechargeDao.get("from StRecharge s where s.perchargeType!='储备金' and s.frtCar.carId='"+ sspVo.getCarId() + "'");
                     if(sr!=null&&!sr.equals(""))
            		     sr.setSurplusMoney(sr.getSurplusMoney() - ssp.getCurPercharge() + sspVo.getCurPercharge());
                     stRechargeDao.merge(sr);
            	}
            }
            else{
            	if(sspVo.getCarId()!=null&&!sspVo.getCarId().trim().equals(""))
            	   sr.setFrtCar(frtCarDao.get(" from FrtCar frtCar where frtCar.carId="+ sspVo.getCarId()));
                if(sspVo.getCurPercharge()!=null&&!sspVo.getCurPercharge().equals(""))
                   sr.setSurplusMoney(sspVo.getCurPercharge());
                sr.setPerchargeType("预收款");
                sr.setEnterpriseId(sspVo.getEnterpriseId());
                stRechargeDao.save(sr);
            }
            ssp.setStRecharge(sr);
            if(sspVo.getPaymentId()!=null&&!sspVo.getPaymentId().trim().equals(""))
                ssp.setBasChilddictionary(baseService.findChildById(sspVo.getPaymentId().trim()));
            if(sspVo.getPerchargeDate()!=null&&!sspVo.getPerchargeDate().equals(""))
                ssp.setPerchargeDate(sspVo.getPerchargeDate());
            if(sspVo.getCurPercharge()!=null&&!sspVo.getCurPercharge().equals(""))
                ssp.setCurPercharge(sspVo.getCurPercharge());
            if(sspVo.getPreclrInoiceType()!=null&&!sspVo.getPreclrInoiceType().trim().equals(""))
                ssp.setPreclrInoiceType(sspVo.getPreclrInoiceType().trim());
            if(sspVo.getPreclrNo()!=null&&!sspVo.getPreclrNo().trim().equals(""))
                ssp.setPreclrNo(sspVo.getPreclrNo().trim());
            if(sspVo.getChargeRemark()!=null&&!sspVo.getChargeRemark().trim().equals(""))
                ssp.setChargeRemark(sspVo.getChargeRemark().trim());
            stSellPerchargeDao.update(ssp);
    	}
    }

    /**
     * 根据储备金汇总编号获取储备金明细信息
     */
    public Json findStSellPerchargeById(StSellPerchargeVo sspvo) throws Exception{
        return stSellPerchargeDao.findStSellPerchargeById(sspvo.getRechargeId());
    }

    /**
     * 根据预收款汇总编号获取预收款明细信息
     */
    public Json findPreStSellPerchargeById(
            StSellPerchargeVo sspvo) throws Exception{
        return stSellPerchargeDao.findPreStSellPerchargeById(sspvo
                .getRechargeId());
    }

    /**
     * 储备金使用记录信息   预加载
     */
    public Json loadStUsePercharge(StSellPerchargeVo sspvo)
            throws Exception{
        return stSellPerchargeDao.loadStUsePercharge(sspvo.getPage(), sspvo
                .getRows(),sspvo.getRepcollTimeStart(),sspvo.getRepcollTimeEnd(),sspvo.getRechargeId(),sspvo.getEnterpriseId());
    }

    /**
     * 储备金使用记录信息     综合查询
     */
    public Json serachStUsePerchargeByCondition(
            StSellPerchargeVo sspvo) throws Exception{
        return stSellPerchargeDao.serachStUsePerchargeByCondition(sspvo
                .getRepcollTimeStart(), sspvo.getRepcollTimeEnd(), sspvo
                .getCarLicense(), sspvo.getCustomName());
    }

    /**
     * 预收款使用记录信息 预加载
     */
    public Json loadPreStUsePercharge(StSellPerchargeVo sspvo)
            throws Exception{
        return stSellPerchargeDao.loadPreStUsePercharge(sspvo.getPage(), sspvo
                .getRows(),sspvo.getRepcollTimeStart(),sspvo.getRepcollTimeEnd(),sspvo.getRechargeId(),sspvo.getEnterpriseId());
    }

    /**
     * 预收款使用记录信息 综合查询
     */
    public Json serachPreStUsePerchargeByCondition(
            StSellPerchargeVo sspvo) throws Exception{
        return stSellPerchargeDao.serachPreStUsePerchargeByCondition(sspvo
                .getRepcollTimeStart(), sspvo.getRepcollTimeEnd(), sspvo
                .getCarLicense(), sspvo.getCustomName());
    }

    /**
     * 维修储备金余额信息 预加载
     */
    public Json loadStRecharge() throws Exception{
        return stRechargeDao.loadStRecharge();
    }
    
    /**
     * 维修储备金余额信息 综合查询
     */
    public Json searchStRechargeByCondition(StSellPerchargeVo sspvo) throws Exception{
        return stRechargeDao.searchStRechargeByCondition(sspvo.getCarLicense(),
                sspvo.getCustomName());
    }

    /**
     * 维修预收款余额信息 预加载
     */
    public Json loadPreStRecharge(StSellPerchargeVo sspvo) throws Exception{
        return stRechargeDao.loadPreStRecharge(sspvo);
    }

    /**
     * 维修预收款余额信息 综合查询
     */
    public Json searchStPreRechargeByCondition(StSellPerchargeVo sspvo) throws Exception{
        return stRechargeDao.searchStPreRechargeByCondition(sspvo.getCarLicense(),
                sspvo.getCustomName());
    }

}
