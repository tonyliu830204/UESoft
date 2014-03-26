package com.syuesoft.systemmanagement.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.model.FrtRcptItem;
import com.syuesoft.systemmanagement.service.CarLostAnalysisService;
import com.syuesoft.util.Json;
import com.syuesoft.vipmanagement.vo.CarLostAnalysisVo;

import org.apache.struts2.convention.annotation.ParentPackage;@SuppressWarnings("serial")
@ParentPackage(value="basePackage")@Action("carLostAnalysisAction")
public class CarLostAnalysisAction extends BaseAction implements
		ModelDriven<CarLostAnalysisVo> {

	private CarLostAnalysisVo carLostAnalysisVo = new CarLostAnalysisVo();
	
	public CarLostAnalysisVo getModel() {
		return carLostAnalysisVo;
	}
	
	@Resource
	private CarLostAnalysisService carLostAnalysisService;
	
	/**
	 * 获取车辆流失汇总信息 
	 */
	public String getCollectinfor(){
		List list = new ArrayList();
		try {
			List rlist = carLostAnalysisService.getCollectinfor(carLostAnalysisVo);
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[])rlist.get(i);
				CarLostAnalysisVo vo = new CarLostAnalysisVo();
				if(obj[0]!=null){vo.setCar_License(obj[0].toString());}
				if(obj[1]!=null){vo.setCar_Vin(obj[1].toString());}
				if(obj[2]!=null){vo.setCbrd_Name(obj[2].toString());}
				if(obj[3]!=null){vo.setCtype_Name(obj[3].toString());}
				if(obj[4]!=null){vo.setCar_Last_Repair_Date(obj[4].toString());}
				if(obj[5]!=null){vo.setCar_Repair_Cnt(obj[5].toString());}
				if(obj[6]!=null){vo.setCar_Last_Maint_Distance(obj[6].toString());}
				if(obj[7]!=null){vo.setCustom_Name(obj[7].toString());}
				if(obj[8]!=null){vo.setCustom_Tel1(obj[8].toString());}
				if(obj[9]!=null){vo.setCustom_Tel2(obj[9].toString());}
				if(obj[10]!=null){vo.setLinkman(obj[10].toString());}
				if(obj[11]!=null){vo.setLinkman_Tel(obj[11].toString());}
				if(obj[12]!=null){vo.setInvoicing_Address(obj[12].toString());}
				if(obj[13]!=null){vo.setCar_Distance_Per_Day(obj[13].toString());}
				if(obj[14]!=null){vo.setColor_Name(obj[14].toString());}
				
				if(obj[15]!=null){vo.setCustom_Addr(obj[15].toString());}
				if(obj[16]!=null){vo.setCar_Buy_Date(obj[16].toString());}
				if(obj[17]!=null){vo.setStf_Name(obj[17].toString());}
				if(obj[18]!=null){vo.setParea_Zip(obj[18].toString());}
				if(obj[19]!=null){vo.setNowdate(obj[19].toString());}
				if(obj[20]!=null){vo.setNextdate(obj[20].toString());}
				if(obj[21]!=null){vo.setLost_date(obj[21].toString());}
				if(obj[22]!=null){vo.setCst_Name(obj[22].toString());}
				if(obj[23]!=null){vo.setCar_Id(obj[23].toString());}
				list.add(vo);
			}
			//json.setRows(list);
			//json.setTotal(Integer.parseInt(session.getAttribute("size").toString()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(list);
		return null;
	}
	
	/**
	 * 通过车辆编号查询 车辆流失明细信息
	 */
	public String getDetailsInforById(){
		List list = new ArrayList();
		try {
			List rlist = carLostAnalysisService.getDetailsInforById(carLostAnalysisVo);
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[])rlist.get(i);
				CarLostAnalysisVo vo = new CarLostAnalysisVo();
				if(obj[0]!=null){vo.setInter_Date(obj[0].toString());}
				if(obj[1]!=null){vo.setCar_Last_Maint_Distance(obj[1].toString());}
				if(obj[2]!=null){vo.setStf_Name(obj[2].toString());}
				if(obj[3]!=null){vo.setExp_Del_Car_Time(obj[3].toString());}
				if(obj[4]!=null){vo.setProp_Rep_Per(obj[4].toString());}
				if(obj[5]!=null){vo.setReception_Id(obj[5].toString());}
				if(obj[6]!=null){vo.setPreclr_Sum_Amount(obj[6].toString());}
				if(obj[7]!=null){vo.setCar_Id(obj[7].toString());}
				vo.setState(carLostAnalysisService.getchild(vo).size()>0 ? "closed" : "open");
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(list);
		return null;
	}
	/**
	 * 获取父节点下的维修项目
	 */
	public void getChildMenus(){
		List list  = new ArrayList();
		try {
			List<FrtRcptItem> rlist = carLostAnalysisService.getchild(carLostAnalysisVo);
			for (FrtRcptItem frtRcptItem : rlist) {
				CarLostAnalysisVo vo = new CarLostAnalysisVo();
				vo.setRcpitem_Name(frtRcptItem.getRepitemName());
				vo.setIconCls("icon-blank");
				vo.setInter_Date("");
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(list);
	}
	/**
	 * 统计已回访和未回访记录
	 */
	public void finishvisite(){
		List list = new ArrayList();
		ResourceBundle bundleMessage = PropertyResourceBundle.getBundle("liushihuifang");
		Enumeration<String> keys = bundleMessage.getKeys();
		String hsql1 = "";
		String hsql2 = "";
		try {
			while (keys.hasMoreElements()) {
				CarLostAnalysisVo carLostAnalysisVo = new CarLostAnalysisVo();
				String key = keys.nextElement();
				String value = bundleMessage.getString(key);
				if(value.equals("A")){
					hsql1 = " AND M.CAR_LOST ='F' AND M.GROUP_NAME!='流失回访'";
					hsql2 = " AND M.CAR_LOST ='F' AND M.GROUP_NAME='流失回访'";
					carLostAnalysisVo.setTimes("准流失车辆");
				}
				else if(value.equals("B")){
					hsql1 = " AND M.GROUP_NAME!='流失回访'";
					hsql2 = " AND M.GROUP_NAME='流失回访'";
					carLostAnalysisVo.setTimes("其他");
				}else{
					String[] time = value.split("~");
					hsql1 = " AND DATEDIFF(CURDATE(),A.CAR_LAST_REPAIR_DATE)>"+time[0]+" AND  DATEDIFF(CURDATE(),A.CAR_LAST_REPAIR_DATE)<"+time[1]+" AND M.GROUP_NAME!='流失回访'";
					hsql2 = " AND DATEDIFF(CURDATE(),A.CAR_LAST_REPAIR_DATE)>"+time[0]+" AND  DATEDIFF(CURDATE(),A.CAR_LAST_REPAIR_DATE)<"+time[1]+" AND M.GROUP_NAME='流失回访'";
					carLostAnalysisVo.setTimes(value);
				}
				String str2 = carLostAnalysisService.finishvisite(hsql2).get(0)+"";
				String str1 = carLostAnalysisService.finishvisite(hsql1).get(0)+"";
				if(!"".equals(str2) && null!=str2)
					carLostAnalysisVo.setFinishvisite(str2);
				if(!"".equals(str1) && null!=str1)
					carLostAnalysisVo.setNovisite(str1);
				list.add(carLostAnalysisVo);
				
			}
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
