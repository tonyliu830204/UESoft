package com.syuesoft.sell.sellParamSet.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.sell.model.XsSellParameter;
import com.syuesoft.sell.sellParamSet.dao.SellParamSetDao;
import com.syuesoft.sell.sellParamSet.service.SellParamSetService;
import com.syuesoft.sell.sellParamSet.vo.SellParamSetVo;
import com.syuesoft.util.Json;
@Service("sellParamSetService")
public class SellParamSetServiceImpl extends BaseLogServiceImpl implements SellParamSetService {
	private SellParamSetDao sellParamSetDao;

	public SellParamSetDao getSellParamSetDao() {
		return sellParamSetDao;
	}
	@Autowired
	public void setSellParamSetDao(SellParamSetDao sellParamSetDao) {
		this.sellParamSetDao = sellParamSetDao;
	}
/**
 * 查询
 */
	
	public Json searchParameterOne(SellParamSetVo cis) throws Exception {
		 	Json json = new Json();
			String  param = " and cis.ciType = '"+ cis.getCiType() + "' and cis.enterprise_id="+cis.getEnterprise_id();
	        List<XsSellParameter> list=sellParamSetDao.findAll(param);
	        json.setTotal(list.size());
	        json.setRows(list);
	        return json;
	}
	/**
	 * 查询
	 */
		
		public Json searchParameter(SellParamSetVo cis) throws Exception {
			 Json json = new Json();
			 String param="";
			 if(cis.getCiType()!=null && !("".equals(cis.getCiType()))){
				  param = " and cis.ciType = '"+ cis.getCiType() + "'";
			 }
			 if(cis.getCiName()!=null && !("".equals(cis.getCiName()))){
				  param = " and cis.ciLable = '"+ cis.getCiName() + "'";
			 }
			 String hql="from XsSellParameter cis where 1 = 1 and cis.enterprise_id='"+cis.getEnterprise_id()+"'" + param ;
			 List<XsSellParameter> list=sellParamSetDao.find(hql,cis.getPage(),cis.getRows());
	         if(list==null){
	        	 json.setRows(new ArrayList());
	         }else{
	        	 json.setRows(list); 
	         }
		     json.setTotal(sellParamSetDao.getCount(hql));
		     return json;
		}
	/**
	 * 保存参数信息
	 */
	
	@Log(systemName="销售系统", moduleName="系统参数设定",opertype="保存")
	public void saveOrUpdate(SellParamSetVo cisVo) throws Exception {
		 List<XsSellParameter> cisVos = new ArrayList<XsSellParameter>();
	        String[] checkCiIds = cisVo.getCiCheckCiIds();
	        String[] checkNames = cisVo.getCiCheckNames();
	        String[] checkValues = cisVo.getCiCheckValues();

	        String[] ciNames = cisVo.getCiNames();
	        String[] ciCiIds = cisVo.getCiCiIds();
	        String[] ciValues = cisVo.getCiValues();
	        String log="";
	        if (checkNames.length > 0 || checkValues.length > 0)
	        {
	            List<String> list = null;
	            if (checkValues.length > 0)
	            {
	                list = new ArrayList<String>();
	                for (int j = 0; j < checkValues.length; j++)
	                {
	                    list.add(checkValues[j]);
	                }
	            }
	            
	            for (int i = 0; i < checkNames.length; i++)
	            {
	            	XsSellParameter cis = new XsSellParameter();
	                cis.setCiId(checkCiIds[i]);
	                cis.setCiName(checkNames[i]);
	                cis.setCiValue(list != null ? list.contains(checkNames[i]) ? "checked": null : null);
	                cis.setEnterprise_id(cisVo.getEnterprise_id());
	                cisVos.add(cis);
	                
	                if(cis.getCiId()!=null&&!"".equals(cis.getCiId())){
	                	log+="【编码为:"+cis.getCiId()+",值为:"+cis.getCiValue()+"】";
	                }             
	            }
	           
	        }
	        if (ciNames.length > 0 || ciCiIds.length > 0 || ciValues.length > 0)
	        {
	        	
	            for (int i = 0; i < ciNames.length; i++)
	            {
	            	XsSellParameter cis = new XsSellParameter();
	                cis.setCiId(ciCiIds[i]);
	                cis.setCiName(ciNames[i]);
	                cis.setCiValue(!"".equals(ciValues[i]) ? ciValues[i] : null);
	                cis.setEnterprise_id(cisVo.getEnterprise_id());
	                cisVos.add(cis);
	              
	                if(cis.getCiId()!=null&&!"".equals(cis.getCiId())){
	                	log+="【编码为:"+cis.getCiId()+",值为:"+cis.getCiValue()+"】";
	                }
	              }
	          
	        }
	        setContent("保存、更新"+log+"系统参数！");
	        sellParamSetDao.saveOrUpdate(cisVos);
	        
	       
	    }
	
	public void updateParam(SellParamSetVo params) throws Exception {
		//XsSellParameter p=sellParamSetDao.get("from XsSellParameter cis where 1 = 1 and cis.ciId='"+params.getCiId()+"'");
		XsSellParameter p=new XsSellParameter();
		BeanUtils.copyProperties(params, p);
		sellParamSetDao.update(p);
		
	}
	

}
