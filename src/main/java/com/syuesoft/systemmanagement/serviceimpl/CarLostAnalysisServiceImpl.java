package com.syuesoft.systemmanagement.serviceimpl;

import java.util.Enumeration;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.systemmanagement.dao.CarLostAnalysisDao;
import com.syuesoft.systemmanagement.service.CarLostAnalysisService;
import com.syuesoft.util.Json;
import com.syuesoft.vipmanagement.vo.CarLostAnalysisVo;

@Service("carLostAnalysisService")
public class CarLostAnalysisServiceImpl extends BaseLogServiceImpl implements
		CarLostAnalysisService {

	@Resource
	private CarLostAnalysisDao carLostAnalysisDao;
	
	
	public List<CarLostAnalysisVo> getCollectinfor(
			CarLostAnalysisVo carLostAnalysisVo) throws Exception {
		return carLostAnalysisDao.getCollectinfor(carLostAnalysisVo);
	}

	
	public List<CarLostAnalysisVo> getDetailsInforById(
			CarLostAnalysisVo carLostAnalysisVo) throws Exception {
		return carLostAnalysisDao.getDetailsInforById(carLostAnalysisVo);
	}

	
	public List getchild(CarLostAnalysisVo carLostAnalysisVo) throws Exception {
		// TODO Auto-generated method stub
		return carLostAnalysisDao.getchild(carLostAnalysisVo);
	}

	
	public Json getLiushi() throws Exception {
		Json json = new Json();
		ResourceBundle bundleMessage = PropertyResourceBundle.getBundle("liushihuifang");
		Enumeration<String> keys = bundleMessage.getKeys();
		
		String sql = "slect co";
		while (keys.hasMoreElements()) {
			CarLostAnalysisVo carLostAnalysisVo = new CarLostAnalysisVo();
			String key = keys.nextElement();
			String value = bundleMessage.getString(key);
			carLostAnalysisVo.setTimes(value);
			if(value.equals("准流失车辆")){
				
			}
			if(value.equals("新车")){
				
			}
			if(value.equals("其他")){
				
			}
			String[] time = value.split("~");
			
        }
		
		//json.setRows(list);
		//json.setTotal(Integer.parseInt(session.getAttribute("size").toString()));
		return json;
	}

	/*
	 * (non-Javadoc)
	 * 车辆流失情况分析 统计 已回访 和未回访
	 * @see com.syuesoft.systemmanagement.service.CarLostAnalysisService#finishvisite(java.lang.String)
	 */
	
	public List finishvisite(String sql) throws Exception {
		return carLostAnalysisDao.finishvisite(sql);
	}

}