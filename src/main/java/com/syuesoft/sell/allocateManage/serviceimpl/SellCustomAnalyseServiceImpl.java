package com.syuesoft.sell.allocateManage.serviceimpl;

import java.util.HashMap;
import java.util.List;

import ognl.Ognl;

import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.dao.SellCustomAnalyseDao;
import com.syuesoft.sell.allocateManage.service.SellCustomAnalyseService;
import com.syuesoft.sell.allocateManage.vo.SellCustomAnalyseVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateJFreeChart;

@Service("sellCustomAnalyseService")
public class SellCustomAnalyseServiceImpl extends BaseLogServiceImpl implements
		SellCustomAnalyseService {
	private SellCustomAnalyseDao sellCustomAnalyseDao;

	

	public SellCustomAnalyseDao getSellCustomAnalyseDao() {
		return sellCustomAnalyseDao;
	}


 @Autowired
	public void setSellCustomAnalyseDao(SellCustomAnalyseDao sellCustomAnalyseDao) {
		this.sellCustomAnalyseDao = sellCustomAnalyseDao;
	}



	
	public Datagrid querySellCustomInfor(SellCustomAnalyseVo sellCustomAnalyseVo)
			throws Exception {
		// TODO Auto-generated method stub
		return sellCustomAnalyseDao
				.querySellCustomInfor(sellCustomAnalyseVo);
	}


	
	public JFreeChart findAnalyzeCakeMap(SellCustomAnalyseVo sellCustomAnalyseVo)
			throws Exception {
		HashMap<String,Double> cakeHashMap=new HashMap();
		Datagrid source=sellCustomAnalyseDao.querySellCustomInfor(sellCustomAnalyseVo);
		List<SellCustomAnalyseVo> list=source.getRows();
		Double value=null;
		if(list!=null)
		for (SellCustomAnalyseVo temp : list) {
			cakeHashMap.put(temp.getCuName(),temp.getPercent());
			
		}
		return CreateJFreeChart.findCakeMap("销售客户分析饼状图",cakeHashMap,true,true);
	}


	
	public DatagridAnalyze querySellMlAnaly(SellCustomAnalyseVo sellCustomAnalyseVo)
			throws Exception {
		// TODO Auto-generated method stub
		return sellCustomAnalyseDao.querySellMlAnaly(sellCustomAnalyseVo);
	}

}
