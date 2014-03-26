package com.syuesoft.frt.serviceimpl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.frt.dao.FrtResevationDao;
import com.syuesoft.frt.service.BespeakAnalyseService;
import com.syuesoft.frt.vo.FrtQueryVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CakeMap;
import com.syuesoft.util.CreateJFreeChart;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.SnapMap;
/**
 * 预约分析Service
 * */
@Service("bespeakAnalyseService")
public class BespeakAnalyseServiceImpl implements BespeakAnalyseService {
	@Autowired
	private FrtResevationDao frtResevationDao;
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	/**
	 * 查找预约分析数据
	 * */
	
	public DatagridAnalyze findBespeakAnalyse(FrtQueryVo fqVo) throws Exception {
		List<FrtQueryVo> rows=new ArrayList();
		StringBuffer sb=new StringBuffer("SELECT bb.*,(bb.data2/bb.data1)");
		sb.append(" FROM (");
		sb.append(" SELECT aa.temp,COUNT(aa.temp0) AS data1,COUNT(aa.temp1) AS data2,COUNT(aa.temp2) AS data3,COUNT(aa.temp3) AS data4  FROM");			
		sb.append(" (");
		if(fqVo.getSelectedField()!=null&&"sumCount".equals(fqVo.getSelectedField())){
			sb.append(" SELECT DATE(fr.RESV_REAL_TIME) AS temp,fr.RESV_ID AS temp0,");
			sb.append(" '' AS temp1,");
			sb.append(" '' AS temp2,");
			sb.append(" '' AS temp3");
		}else if(fqVo.getSelectedField()!=null&&"successCount".equals(fqVo.getSelectedField())){
			sb.append(" SELECT DATE(fr.RESV_REAL_TIME) AS temp,'' AS temp0,");
			sb.append(" (SELECT frs.RESV_ID FROM frt_resevation frs WHERE frs.RESV_ID=fr.RESV_ID AND frs.FRTRESV_FLG="+Contstants.DELETE_TAG.DELETENO+
					" AND frs.RESV_STATUS!='"+Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKOFF+"' AND frs.RESV_STATUS!='"+Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING+"'  AND  fr.enterprise_id="+fqVo.getEnterpriseId()  +" ) AS temp1,");
			sb.append(" '' AS temp2,");
			sb.append(" '' AS temp3");
		}else if(fqVo.getSelectedField()!=null&&"failCount".equals(fqVo.getSelectedField())){
			sb.append(" SELECT DATE(fr.RESV_REAL_TIME) AS temp,'' AS temp0,");
			sb.append(" '' AS temp1,");
			sb.append(" (SELECT frs.RESV_ID FROM frt_resevation frs WHERE frs.RESV_ID=fr.RESV_ID AND frs.FRTRESV_FLG="+Contstants.DELETE_TAG.DELETENO+
					" AND frs.RESV_STATUS='"+Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKOFF+"'  AND  fr.enterprise_id="+fqVo.getEnterpriseId()  +" ) AS temp2,");
			sb.append(" '' AS temp3");
		}else if(fqVo.getSelectedField()!=null&&"continueCount".equals(fqVo.getSelectedField())){
			sb.append(" SELECT DATE(fr.RESV_REAL_TIME) AS temp,'' AS temp0,");
			sb.append(" '' AS temp1,");
			sb.append(" '' AS temp2,");
			sb.append(" (SELECT frs.RESV_ID FROM frt_resevation frs WHERE frs.RESV_ID=fr.RESV_ID AND frs.FRTRESV_FLG="+Contstants.DELETE_TAG.DELETENO+" AND frs.RESV_STATUS='"+Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING+"' ) AS temp3");
		}else{
			sb.append(" SELECT DATE(fr.RESV_REAL_TIME) AS temp,fr.RESV_ID AS temp0,");
			sb.append(" (SELECT frs.RESV_ID FROM frt_resevation frs WHERE frs.RESV_ID=fr.RESV_ID AND frs.FRTRESV_FLG="+Contstants.DELETE_TAG.DELETENO+" AND frs.RESV_STATUS!='"+Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKOFF+"' AND frs.RESV_STATUS!='"+Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING+"' and  frs.enterprise_id="+fqVo.getEnterpriseId()+" ) AS temp1,");
			sb.append(" (SELECT frs.RESV_ID FROM frt_resevation frs WHERE frs.RESV_ID=fr.RESV_ID AND frs.FRTRESV_FLG="+Contstants.DELETE_TAG.DELETENO+" AND frs.RESV_STATUS='"+Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKOFF+"'  and  frs.enterprise_id="+fqVo.getEnterpriseId()+" ) AS temp2,");
			sb.append(" (SELECT frs.RESV_ID FROM frt_resevation frs WHERE frs.RESV_ID=fr.RESV_ID AND frs.FRTRESV_FLG="+Contstants.DELETE_TAG.DELETENO+" AND frs.RESV_STATUS='"+Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING+"'  and  frs.enterprise_id="+fqVo.getEnterpriseId() +
					") AS temp3");			
		}
		sb.append(" FROM frt_resevation fr where 1=1   and  fr.enterprise_id="+ fqVo.getEnterpriseId() );
		if(fqVo.getResvType()!=null&&fqVo.getResvType().length()>0)
			sb.append(" and fr.RESV_TYPE='"+fqVo.getResvType()+"' AND fr.FRTRESV_FLG="+Contstants.DELETE_TAG.DELETENO);
		sb.append(" ) aa");
		sb.append(" WHERE 1=1");
		if(fqVo.getEnrolTime()!=null&&fqVo.getEnrolTime().length()>0){
			sb.append(" and aa.temp='"+fqVo.getEnrolTime().trim()+"'");
		}
		if(fqVo.getBeginTime()!=null&&fqVo.getBeginTime().length()>0){
			sb.append(" and aa.temp>='"+fqVo.getBeginTime()+"'");
		}
		if(fqVo.getEndTime()!=null&&fqVo.getEndTime().length()>0){
			sb.append(" and aa.temp<='"+fqVo.getEndTime()+"'");
		}
		
		sb.append(" GROUP BY aa.temp");
		sb.append(" ) bb");
		List<Object[]> rowsList=frtResevationDao.createSQLQuery(sb.toString(),fqVo.getPage(), fqVo.getRows());
		FrtQueryVo temp=new FrtQueryVo();
		FrtQueryVo timeVo=null;
		if(rowsList!=null&&rowsList.size()>0)
		for (Object[] objects : rowsList) {
			timeVo=new FrtQueryVo();
			int sumCount=0;
			if(objects[0]!=null&&objects[0].toString().length()>0){
				timeVo.setEnrolTime(objects[0].toString());				
			}
			if(objects[1]!=null&&objects[1].toString().length()>0){
				timeVo.setSumCount(Integer.parseInt(objects[1].toString()));
				temp.setSumCount(temp.getSumCount()+timeVo.getSumCount());
			}
			if(objects[2]!=null&&objects[2].toString().length()>0){
				timeVo.setSuccessCount(Integer.parseInt(objects[2].toString()));
				temp.setSuccessCount(temp.getSuccessCount()+timeVo.getSuccessCount());
			}
			if(objects[3]!=null&&objects[3].toString().length()>0){
				timeVo.setFailCount(Integer.parseInt(objects[3].toString()));
				temp.setFailCount(temp.getFailCount()+timeVo.getFailCount());
			}
			if(objects[4]!=null&&objects[4].toString().length()>0){
				timeVo.setContinueCount(Integer.parseInt(objects[4].toString()));
				temp.setContinueCount(temp.getContinueCount()+timeVo.getContinueCount());
			}
			if(objects[5]!=null&&objects[5].toString().length()>0){
				timeVo.setSuccessRate(Double.parseDouble(objects[5].toString()));		
			}
			rows.add(timeVo);
		}
		temp.setSuccessRate(Double.parseDouble(temp.getSuccessCount().toString())/Double.parseDouble(temp.getSumCount().toString()));
		temp.setEnrolTime("合计");
		List<FrtQueryVo> footers=new ArrayList();
		footers.add(temp);
		DatagridAnalyze dga=new DatagridAnalyze();
		dga.setRows(rows);
		int total=frtResevationDao.getSQLCount(sb.toString(), null);
		dga.setTotal(total);
		dga.setFooter(footers);
		return dga;
	}
	/**
	 * 查找预约分析饼图
	 * */
	
	public JFreeChart findCakeMap(FrtQueryVo fqVo) throws Exception {
		HashMap<String,Double> cakeHashMap=new HashMap();
		List<FrtQueryVo> list=findBespeakAnalyse(fqVo).getFooter();
		if(list!=null&&list.size()>0){
			cakeHashMap.put("接车成功数量",Double.parseDouble(list.get(0).getSuccessCount().toString()));
			cakeHashMap.put("接车失败数量",Double.parseDouble(list.get(0).getFailCount().toString()));
			cakeHashMap.put("预约中数量",Double.parseDouble(list.get(0).getContinueCount().toString()));
		}
		String cakeName="预约分析饼图";
		if(fqVo.getEnrolTime()!=null&&fqVo.getEnrolTime().length()>0){
			cakeName="("+fqVo.getEnrolTime()+")"+cakeName;
		}
		return CreateJFreeChart.findCakeMap(cakeName,cakeHashMap,null,null,null,null,true,null,null,null,
				null,null,null,null,null,null,null,null,null,null,true,fqVo.getIs3D());
	}
	/**
	 * 查找预约分析折线图
	 * */
	
	public JFreeChart findSnapMap(FrtQueryVo fqVo) throws Exception {
		// TODO Auto-generated method stub
		List<SnapMap> snapList=new ArrayList();
		DatagridAnalyze da=findBespeakAnalyse(fqVo);
		List<FrtQueryVo> list=da.getRows();
		String snapName="预约分析折线图";
		SnapMap sm=null;
		if(fqVo.getSelectedField()!=null&&"sumCount".equals(fqVo.getSelectedField())){
			snapName="(总数量)"+snapName;
			if(list!=null&&list.size()>0)
			for (FrtQueryVo frtQueryVO : list) {
				sm=new SnapMap();
				sm.setSnapYData(Double.parseDouble(frtQueryVO.getSumCount().toString()));
				sm.setSnapXData(frtQueryVO.getEnrolTime());
				sm.setSnapLineName("总数量折线");
				sm.setSnapLineColor(Color.GREEN);
				snapList.add(sm);
			}
		}else if(fqVo.getSelectedField()!=null&&"successCount".equals(fqVo.getSelectedField())){
			snapName="(成功数量)"+snapName;
			if(list!=null&&list.size()>0)
			for (FrtQueryVo frtQueryVO : list) {
				sm=new SnapMap();
				sm.setSnapYData(Double.parseDouble(frtQueryVO.getSuccessCount().toString()));
				sm.setSnapXData(frtQueryVO.getEnrolTime());
				sm.setSnapLineName("成功数量折线");
				sm.setSnapLineColor(Color.BLUE);
				snapList.add(sm);
			}
		}else if(fqVo.getSelectedField()!=null&&"failCount".equals(fqVo.getSelectedField())){
			snapName="(失败数量)"+snapName;
			if(list!=null&&list.size()>0)
			for (FrtQueryVo frtQueryVO : list) {
				sm=new SnapMap();
				sm.setSnapYData(Double.parseDouble(frtQueryVO.getFailCount().toString()));
				sm.setSnapXData(frtQueryVO.getEnrolTime());
				sm.setSnapLineName("失败数量折线");
				sm.setSnapLineColor(Color.RED);
				snapList.add(sm);
			}
		}else if(fqVo.getSelectedField()!=null&&"continueCount".equals(fqVo.getSelectedField())){
			snapName="(预约中数量)"+snapName;
			if(list!=null&&list.size()>0)
			for (FrtQueryVo frtQueryVO : list) {
				sm=new SnapMap();
				sm.setSnapYData(Double.parseDouble(frtQueryVO.getContinueCount().toString()));
				sm.setSnapXData(frtQueryVO.getEnrolTime());
				sm.setSnapLineName("预约中数量折线");
				sm.setSnapLineColor(Color.ORANGE);
				snapList.add(sm);
			}
		}else{
			if(list!=null&&list.size()>0)
			for (FrtQueryVo frtQueryVO : list) {
				sm=new SnapMap();
				sm.setSnapYData(Double.parseDouble(frtQueryVO.getSumCount().toString()));
				sm.setSnapXData(frtQueryVO.getEnrolTime());
				sm.setSnapLineName("总数量折线");
				sm.setSnapLineColor(Color.GREEN);
				snapList.add(sm);
				sm=new SnapMap();
				sm.setSnapYData(Double.parseDouble(frtQueryVO.getSuccessCount().toString()));
				sm.setSnapXData(frtQueryVO.getEnrolTime());
				sm.setSnapLineName("成功数量折线");
				sm.setSnapLineColor(Color.BLUE);
				snapList.add(sm);
				sm=new SnapMap();
				sm.setSnapYData(Double.parseDouble(frtQueryVO.getFailCount().toString()));
				sm.setSnapXData(frtQueryVO.getEnrolTime());
				sm.setSnapLineName("失败数量折线");
				sm.setSnapLineColor(Color.RED);
				snapList.add(sm);
				sm=new SnapMap();
				sm.setSnapYData(Double.parseDouble(frtQueryVO.getContinueCount().toString()));
				sm.setSnapXData(frtQueryVO.getEnrolTime());
				sm.setSnapLineName("预约中数量折线");
				sm.setSnapLineColor(Color.ORANGE);
				snapList.add(sm);
			}			
		}
		return CreateJFreeChart.findSnapMap(snapName,"登记时间段", "预约量", snapList,fqVo.getIs3D());
	}

}
