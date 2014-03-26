package com.syuesoft.sell.sellwork.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.model.XsCustomInfo;
import com.syuesoft.sell.model.XsCustomLeva;
import com.syuesoft.sell.model.XsSellAllocatel;
import com.syuesoft.sell.model.XsSellCustomTracing;
import com.syuesoft.sell.sellwork.dao.PossibleCustomFollowDao;
import com.syuesoft.sell.sellwork.service.PossibleCustomFollowService;
import com.syuesoft.sell.sellwork.vo.PossibleCustomFollowVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Msg;
import com.syuesoft.util.SystemUser;

@Service("possibleCustomFollowService")
public class PossibleCustomFollowServiceImpl extends BaseLogServiceImpl
		implements PossibleCustomFollowService {

	@Resource
	private PossibleCustomFollowDao possibleCustomFollowDao;
	
	@Resource
	private BaseTagDAO baseTagDAO;
	
	
	/**
	 * 获取转为潜在客户的客户信息
	 */
	
	public List findCustomInfor(PossibleCustomFollowVo possibleCustomFollowVo) throws Exception {
		if (possibleCustomFollowVo.getXs_Custom_Name() != null)
			possibleCustomFollowVo.setXs_Custom_Name(new String(possibleCustomFollowVo
					.getXs_Custom_Name().getBytes("ISO-8859-1"), "UTF-8"));
		List list = new ArrayList();
	try {
		List<Object[]> rlist = possibleCustomFollowDao.findCustomInfor(possibleCustomFollowVo,baseTagDAO);
		if(rlist!=null &&  rlist.size()>0){
			for (Object[] obj : rlist) {
				PossibleCustomFollowVo vo = new PossibleCustomFollowVo();
				if(obj[0]!=null){vo.setCustom_Id(obj[0].toString());}
				if(obj[1]!=null){vo.setXs_Custom_Name(obj[1].toString());}
				if(obj[2]!=null){vo.setXs_Custom_Inputdata(obj[2].toString());}
				if(obj[3]!=null){vo.setCustom_Property_Id(obj[3].toString());}
				if(obj[4]!=null){vo.setCustom_Property(obj[4].toString());}
				if(obj[5]!=null){vo.setHide_Level(obj[5].toString());}
				
				if(obj[7]!=null){vo.setLevaJiange(obj[7].toString());}
				if(obj[8]!=null){vo.setXs_Custom_Hide_Level_Id(obj[8].toString());}
				if(obj[9]!=null){vo.setTracing_Date(obj[9].toString());}
				if(obj[10]!=null){vo.setXs_Custom_Telephone(obj[10].toString());}
				vo.setXs_Custom_Hide_Level("");
				vo.setIconCls("icon-blank");
				vo.setState("open");
				list.add(vo);
			}
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

	/**
	 * 获取级别 和 按级别统计的数量
	 */
	
	public List findCustomInforCount(PossibleCustomFollowVo possibleCustomFollowVo) throws Exception {
		List list = new ArrayList();
		try {
			List rlist = possibleCustomFollowDao.findCustomInforCount(possibleCustomFollowVo,baseTagDAO);
			Object[] obj = null;
			if(rlist!=null && rlist.size()>0){
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[])rlist.get(i);
				PossibleCustomFollowVo vo = new PossibleCustomFollowVo();
				if(obj[0]!=null){vo.setXs_Custom_Name(obj[0].toString());}
				if(obj[1]!=null){vo.setXs_Custom_Hide_Level_Id(obj[1].toString());}
				if(obj[2]!=null){vo.setXs_Custom_Hide_Level(obj[2].toString());}
				if(obj[3]!=null){vo.setLevaJiange(obj[3].toString());}
				vo.setState("closed");
				vo.setIconCls("icon-blank");
				list.add(vo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通过客户编号获取客户信息
	 */
	
	public List findCustomById(PossibleCustomFollowVo possibleCustomFollowVo) throws Exception {
		List list = new ArrayList();
		try {
		List rlist = possibleCustomFollowDao.findCustomById(possibleCustomFollowVo);
		Object[] obj = null;
		if(rlist!=null && rlist.size()>0){
		for (int i = 0; i < rlist.size(); i++) {
			obj = (Object[])rlist.get(i);
			PossibleCustomFollowVo vo = new PossibleCustomFollowVo();
			if(obj[0]!=null){vo.setTracing_Id(obj[0].toString());}
			if(obj[1]!=null){vo.setCustom_Id(obj[1].toString());}
			if(obj[2]!=null){vo.setTracing_Code(obj[2].toString());}
			if(obj[3]!=null){vo.setTracing_Date(obj[3].toString());}
			if(obj[4]!=null){vo.setInterview_Date(obj[4].toString());}
			if(obj[5]!=null){vo.setTracing_Day_Number(obj[5].toString());}
			if(obj[6]!=null){vo.setTracing_Address(obj[6].toString());}
			if(obj[7]!=null){vo.setAmbience(obj[7].toString());}
			if(obj[8]!=null){vo.setTracing_Way(obj[8].toString());}
			if(obj[9]!=null){vo.setSteer_Trial(obj[9].toString());}	
			if(obj[10]!=null){vo.setCar_Model(obj[10].toString());}
			if(obj[11]!=null){vo.setHinder_Content(obj[11].toString());}
			if(obj[12]!=null){vo.setTalk_Title(obj[12].toString());}
			if(obj[13]!=null){vo.setTracing_Summary(obj[13].toString());}
			if(obj[14]!=null){vo.setNext_Interview_Date(obj[14].toString());}
			if(obj[15]!=null){vo.setExamine_Opinion(obj[15].toString());}
			if(obj[16]!=null){vo.setExamine_Flag(obj[16].toString());}
			if(obj[17]!=null){vo.setExamine_Date(obj[17].toString());}
			if(obj[18]!=null){vo.setPrice_Need(obj[18].toString());}
			if(obj[19]!=null){vo.setUse_Need(obj[19].toString());}
			if(obj[20]!=null){vo.setCapability_Need(obj[20].toString());}
			if(obj[21]!=null){vo.setColour_Need(obj[21].toString());}
			if(obj[22]!=null){vo.setCai_Model_Need(obj[22].toString());}
			if(obj[23]!=null){vo.setPayment_Way(obj[23].toString());}
			if(obj[24]!=null){vo.setPredict_Buy_Date(obj[24].toString());}
			if(obj[25]!=null){vo.setBuy_Probability(obj[25].toString());}
			if(obj[26]!=null){vo.setObstacle(obj[26].toString());}
			if(obj[27]!=null){vo.setBespeak_Date(obj[27].toString());}
			if(obj[28]!=null){vo.setNeed_State(obj[28].toString());}
			if(obj[29]!=null){vo.setNeed_Type(obj[29].toString());}
			if(obj[30]!=null){vo.setRemark(obj[30].toString());}
			if(obj[31]!=null){vo.setXs_Custom_Name(obj[31].toString());}
			if(obj[32]!=null){vo.setTracing(obj[32].toString());}
			if(obj[33]!=null){vo.setAmbienceN(obj[33].toString());}
			if(obj[34]!=null){vo.setCarModelN(obj[34].toString());}
			if(obj[35]!=null){vo.setLevel(obj[35].toString());}	
			if(obj[36]!=null){vo.setXs_Custom_Inputdata(obj[36].toString());}	
			list.add(vo);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	/**
	 * 保存一条潜在客户记录
	 */
	
	public void addCustomRecord(PossibleCustomFollowVo possibleCustomFollowVo)
			throws Exception {
		XsSellCustomTracing xs = new XsSellCustomTracing();
		xs.setAmbience(possibleCustomFollowVo.getAmbience()!=null && !possibleCustomFollowVo.getAmbience().equals("") ? Integer.parseInt(possibleCustomFollowVo.getAmbience()):null);
		xs.setCarModel(possibleCustomFollowVo.getCar_Model()!=null && !possibleCustomFollowVo.getCar_Model().equals("") ? Integer.parseInt(possibleCustomFollowVo.getCar_Model()):null);
		xs.setExamineDate(possibleCustomFollowVo.getExamine_Date()!=null && !possibleCustomFollowVo.getExamine_Date().equals("") ? possibleCustomFollowVo.getExamine_Date():null);
		xs.setExamineOpinion(possibleCustomFollowVo.getExamine_Opinion()!=null && !possibleCustomFollowVo.getExamine_Opinion().equals("") ? possibleCustomFollowVo.getExamine_Opinion():null);
		xs.setExamineFlag("0");
		xs.setHinderContent(possibleCustomFollowVo.getHinder_Content()!=null && !possibleCustomFollowVo.getHinder_Content().equals("") ? possibleCustomFollowVo.getHinder_Content():null);
		xs.setInterviewDate(possibleCustomFollowVo.getInterview_Date()!=null && !possibleCustomFollowVo.getInterview_Date().equals("") ? possibleCustomFollowVo.getInterview_Date():null);
		xs.setNextInterviewDate(possibleCustomFollowVo.getNext_Interview_Date()!=null && !possibleCustomFollowVo.getNext_Interview_Date().equals("") ? possibleCustomFollowVo.getNext_Interview_Date():null);
		//xs.setSteerTrial(possibleCustomFollowVo.getSteer_Trial()!=null && !possibleCustomFollowVo.getSteer_Trial().equals("") ? Integer.parseInt(possibleCustomFollowVo.getSteer_Trial()):null);
		xs.setTalkTitle(possibleCustomFollowVo.getTalk_Title()!=null && !possibleCustomFollowVo.getTalk_Title().equals("") ? possibleCustomFollowVo.getTalk_Title():null);
		xs.setTracingAddress(possibleCustomFollowVo.getTracing_Address()!=null && !possibleCustomFollowVo.getTracing_Address().equals("") ? possibleCustomFollowVo.getTracing_Address():null);
		
		xs.setTracingCode(CreateID.createId("XsSellCustomTracing",Contstants.SELL_BILLSDEPLOY.CUSTOMTRACING));
		
		xs.setTracingDate(possibleCustomFollowVo.getTracing_Date()!=null && !possibleCustomFollowVo.getTracing_Date().equals("") ? possibleCustomFollowVo.getTracing_Date():null);
		xs.setTracingDayNumber(possibleCustomFollowVo.getTracing_Day_Number()!=null && !possibleCustomFollowVo.getTracing_Day_Number().equals("") ? Integer.parseInt(possibleCustomFollowVo.getTracing_Day_Number()):null);
		xs.setTracingId(possibleCustomFollowVo.getTracing_Id()!=null && !possibleCustomFollowVo.getTracing_Id().equals("") ? Integer.parseInt(possibleCustomFollowVo.getTracing_Id()):null);
		xs.setTracingSummary(possibleCustomFollowVo.getTracing_Summary()!=null && !possibleCustomFollowVo.getTracing_Summary().equals("") ? possibleCustomFollowVo.getTracing_Summary():null);
		xs.setTracingWay(possibleCustomFollowVo.getTracing_Way()!=null && !possibleCustomFollowVo.getTracing_Way().equals("") ? Integer.parseInt(possibleCustomFollowVo.getTracing_Way()):null);
		int customid = possibleCustomFollowVo.getCustom_Id()!=null && !possibleCustomFollowVo.getCustom_Id().equals("") ? Integer.parseInt(possibleCustomFollowVo.getCustom_Id()):null;
		xs.setCustomId(customid);
		xs.setObstacle(possibleCustomFollowVo.getObstacle()!=null && !possibleCustomFollowVo.getObstacle().equals("") ? Integer.parseInt(possibleCustomFollowVo.getObstacle()):null);
		xs.setBuyProbability(possibleCustomFollowVo.getBuy_Probability()!=null && !possibleCustomFollowVo.getBuy_Probability().equals("") ? possibleCustomFollowVo.getBuy_Probability():null);
		xs.setCaiModelNeed(possibleCustomFollowVo.getCai_Model_Need()!=null && !possibleCustomFollowVo.getCai_Model_Need().equals("") ? Integer.parseInt(possibleCustomFollowVo.getCai_Model_Need()) : null);
		possibleCustomFollowDao.save(xs);
		//修改客户档案的客户等级
		XsCustomInfo xsCustomInfo = (XsCustomInfo)possibleCustomFollowDao.get("from XsCustomInfo where customId="+customid+"");
		xsCustomInfo.setXsCustomHideLevel(possibleCustomFollowVo.getLevel()!=null && !possibleCustomFollowVo.getLevel().equals("") ? Integer.parseInt(possibleCustomFollowVo.getLevel()):null);
		possibleCustomFollowDao.merge(xsCustomInfo);
		
	}

	/**
	 * 删除一条潜在客户记录
	 */
	
	public void deleteCustomRecord(PossibleCustomFollowVo possibleCustomFollowVo)
			throws Exception {
		possibleCustomFollowDao.deleteCustomRecord(possibleCustomFollowVo);
		
	}

	/**
	 * 修改潜在客户记录
	 */
	
	public void updateCustomRecord(PossibleCustomFollowVo possibleCustomFollowVo)
			throws Exception {

		XsSellCustomTracing xs = new XsSellCustomTracing();
		xs.setTracingId(Integer.parseInt(possibleCustomFollowVo.getTracing_Id()));
		xs.setAmbience(possibleCustomFollowVo.getAmbience()!=null && !possibleCustomFollowVo.getAmbience().equals("") ? Integer.parseInt(possibleCustomFollowVo.getAmbience()):null);
		xs.setCarModel(possibleCustomFollowVo.getCar_Model()!=null && !possibleCustomFollowVo.getCar_Model().equals("") ? Integer.parseInt(possibleCustomFollowVo.getCar_Model()):null);
		xs.setExamineDate(possibleCustomFollowVo.getExamine_Date()!=null && !possibleCustomFollowVo.getExamine_Date().equals("") ? possibleCustomFollowVo.getExamine_Date():null);
		xs.setExamineOpinion(possibleCustomFollowVo.getExamine_Opinion()!=null && !possibleCustomFollowVo.getExamine_Opinion().equals("") ? possibleCustomFollowVo.getExamine_Opinion():null);
		xs.setHinderContent(possibleCustomFollowVo.getHinder_Content()!=null && !possibleCustomFollowVo.getHinder_Content().equals("") ? possibleCustomFollowVo.getHinder_Content():null);
		xs.setInterviewDate(possibleCustomFollowVo.getInterview_Date()!=null && !possibleCustomFollowVo.getInterview_Date().equals("") ? possibleCustomFollowVo.getInterview_Date():null);
		xs.setNextInterviewDate(possibleCustomFollowVo.getNext_Interview_Date()!=null && !possibleCustomFollowVo.getNext_Interview_Date().equals("") ? possibleCustomFollowVo.getNext_Interview_Date():null);
		//xs.setSteerTrial(possibleCustomFollowVo.getSteer_Trial()!=null && !possibleCustomFollowVo.getSteer_Trial().equals("") ? Integer.parseInt(possibleCustomFollowVo.getSteer_Trial()):null);
		xs.setTalkTitle(possibleCustomFollowVo.getTalk_Title()!=null && !possibleCustomFollowVo.getTalk_Title().equals("") ? possibleCustomFollowVo.getTalk_Title():null);
		xs.setTracingAddress(possibleCustomFollowVo.getTracing_Address()!=null && !possibleCustomFollowVo.getTracing_Address().equals("") ? possibleCustomFollowVo.getTracing_Address():null);
		
		xs.setTracingCode(possibleCustomFollowVo.getTracing_Code()!=null && !possibleCustomFollowVo.getTracing_Code().equals("") ? possibleCustomFollowVo.getTracing_Code():null);
		
		xs.setTracingDate(possibleCustomFollowVo.getTracing_Date()!=null && !possibleCustomFollowVo.getTracing_Date().equals("") ? possibleCustomFollowVo.getTracing_Date():null);
		xs.setTracingDayNumber(possibleCustomFollowVo.getTracing_Day_Number()!=null && !possibleCustomFollowVo.getTracing_Day_Number().equals("") ? Integer.parseInt(possibleCustomFollowVo.getTracing_Day_Number()):null);
		xs.setTracingId(possibleCustomFollowVo.getTracing_Id()!=null && !possibleCustomFollowVo.getTracing_Id().equals("") ? Integer.parseInt(possibleCustomFollowVo.getTracing_Id()):null);
		xs.setTracingSummary(possibleCustomFollowVo.getTracing_Summary()!=null && !possibleCustomFollowVo.getTracing_Summary().equals("") ? possibleCustomFollowVo.getTracing_Summary():null);
		xs.setTracingWay(possibleCustomFollowVo.getTracing_Way()!=null && !possibleCustomFollowVo.getTracing_Way().equals("") ? Integer.parseInt(possibleCustomFollowVo.getTracing_Way()):null);
		xs.setCaiModelNeed(possibleCustomFollowVo.getCai_Model_Need()!=null && !possibleCustomFollowVo.getCai_Model_Need().equals("") ? Integer.parseInt(possibleCustomFollowVo.getCai_Model_Need()) : null);
		int customid = possibleCustomFollowVo.getCustom_Id()!=null && !possibleCustomFollowVo.getCustom_Id().equals("") ? Integer.parseInt(possibleCustomFollowVo.getCustom_Id()):null;
		xs.setCustomId(customid);
		possibleCustomFollowDao.merge(xs);
		//修改客户档案的客户等级
		XsCustomInfo xsCustomInfo = (XsCustomInfo)possibleCustomFollowDao.get("from XsCustomInfo where customId="+customid+"");
		xsCustomInfo.setXsCustomHideLevel(possibleCustomFollowVo.getLevel()!=null && !possibleCustomFollowVo.getLevel().equals("") ? Integer.parseInt(possibleCustomFollowVo.getLevel()):null);
		possibleCustomFollowDao.merge(xsCustomInfo);
		
	}

	
	public Msg doAudit(PossibleCustomFollowVo possibleCustomFollowVo)
			throws Exception {
		Msg msg=new Msg();
		XsSellCustomTracing trac=(XsSellCustomTracing) possibleCustomFollowDao.get(" from XsSellCustomTracing where tracingId="+possibleCustomFollowVo.getTracing_Id());
		if(trac!=null){
			if(trac.getExamineFlag()==null||"0".equals(trac.getExamineFlag())){
				trac.setExamineDate(new java.sql.Date(new java.util.Date().getTime())+"");
				trac.setExamineFlag("1");
				trac.setExamineOpinion(possibleCustomFollowVo.getExamine_Opinion());
				possibleCustomFollowDao.merge(trac);
				msg.setMsg("审核成功！");
				msg.setSuccess(true);
			}
			else{
				msg.setMsg("该记录已审核，不可再次审核！");
				msg.setSuccess(false);
			}
		}
		return msg;
		
		

		
	}

	
	public Datagrid getTzCustom(PossibleCustomFollowVo possibleCustomFollowVo)
			throws Exception {
		return possibleCustomFollowDao.getTzCustom(possibleCustomFollowVo);
	}

	
	public Datagrid getTzCustomTrack(
			PossibleCustomFollowVo possibleCustomFollowVo) throws Exception {
		return possibleCustomFollowDao.getTzCustomTrack(possibleCustomFollowVo);
	}

	/**
	 * 通过等级编号获取等级间隔时间
	 */
	
	public XsCustomLeva getLevelDays(PossibleCustomFollowVo possibleCustomFollowVo) throws Exception{
		// TODO Auto-generated method stub
		return (XsCustomLeva)possibleCustomFollowDao.get("from XsCustomLeva WHERE levaId="+possibleCustomFollowVo.getXs_Custom_Hide_Level_Id()+"");
	}

}
