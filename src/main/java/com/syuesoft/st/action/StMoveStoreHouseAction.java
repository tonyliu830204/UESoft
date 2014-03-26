package com.syuesoft.st.action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.base.vo.BasStorehouseVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.st.service.StMoveStorehouseService;
import com.syuesoft.st.service.StOutService;
import com.syuesoft.st.vo.StMoveStorehouseVo;
import com.syuesoft.util.Msg;
import org.apache.struts2.convention.annotation.ParentPackage;
/**
 * 移仓单管理Action
 * @author SuMing
 */
@SuppressWarnings("serial")
@ParentPackage(value="basePackage")@Action("StMoveStoreHouseAction")
public class StMoveStoreHouseAction extends BaseAction implements
ModelDriven<StMoveStorehouseVo>{
	
	private Logger logger = Logger.getLogger(this.getClass());
	private StMoveStorehouseVo stMoveStorehouseVo=new StMoveStorehouseVo();
	@Autowired StMoveStorehouseService stMoveStorehouseService;
	@Autowired StOutService stOutService;
	
	/**
	 * 移仓单汇总信息      条件查询
	 */
	public void searchByCondition(){
		try {
			stMoveStorehouseVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stMoveStorehouseService.searchByCondition(stMoveStorehouseVo));
		} catch (Exception e) {
			logger.error("移仓单汇总信息      条件查询   异常", e);
		}
	}
	
	/**
	 * 移仓单汇总信息        预加载
	 */
	public void loadUnExamineStMoveStorehouse(){
		try {
			stMoveStorehouseVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stMoveStorehouseService.loadUnExamineStMoveStorehouse(stMoveStorehouseVo));
		} catch (Exception e) {
			logger.error("移仓单汇总信息       预加载     异常", e);
		}
	}
	
	/**
	 * 移仓单汇总信息      条件查询
	 */
	public void searchUnExamineByCondition(){
		try {
			stMoveStorehouseVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stMoveStorehouseService.searchUnExamineByCondition(stMoveStorehouseVo));
		} catch (Exception e) {
			logger.error("移仓单汇总信息      条件查询   异常", e);
		}
	}
	
	/**
	 * 仓库名称信息   预加载
	 */
	public void loadBasStorehouse()
	{
		try {
			BasStorehouseVo basStorehouseVo = new BasStorehouseVo();
	        basStorehouseVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(stMoveStorehouseService.findAllStorehouse(basStorehouseVo));
		} catch (Exception e) {
			logger.error("仓库名称信息   预加载异常", e);
		}
	}
	
	/**
	 *根据移仓单单号获取 移出仓配件明细信息
	 */
	public void loadMoveOutPartsDetail()
	{
		try {
			super.writeJson(stMoveStorehouseService.loadMoveOutPartsDetail(stMoveStorehouseVo));
		} catch (Exception e) {
			logger.error("仓库名称信息   预加载异常", e);
		}
	}
	
	/**
	 * 根据移仓单单号获取 移入仓配件明细信息
	 */
	public void loadMoveInPartsDetail()
	{
		try {
			super.writeJson(stMoveStorehouseService.loadMoveInPartsDetail(stMoveStorehouseVo));
		} catch (Exception e) {
			logger.error("仓库名称信息   预加载异常", e);
		}
	}
	
	/**
	 *  配件信息       预加载
	 */
	public void loadSelectParts()
	{
        try {
			super.writeJson(stOutService.loadFrtParts(stMoveStorehouseVo.getPartsId(), stMoveStorehouseVo.getPartsName(),stMoveStorehouseVo.getStoreId(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("配件信息       预加载异常", e);
		}
	}
	
	/**
	 * 已选配件信息     预加载
	 */
	public void	loadSelectedParts(){
		super.writeJson(super.listConvertJson(getSessionList()));
    }
	
    /**
	 * 根据移仓单号移仓单明细      预加载
	 */
	public void searchStMoveStorehouseDetailByMsdmId()
	{
		List<StMoveStorehouseVo> list = this.getSessionList();
		try {
			List<StMoveStorehouseVo> resultList=stMoveStorehouseService.searchStMoveStorehouseDetailByMsdmId(stMoveStorehouseVo);
			for (StMoveStorehouseVo smsvo:resultList) {
				list.add(smsvo);
			}
			super.writeJson(super.listConvertJson(list));
		} catch (Exception e) {
			logger.error("根据移仓单号移仓单明细      预加载异常", e);
		}
	}
	
	/**
	 * 获取移仓单明细缓存数据
	 */
	@SuppressWarnings("unchecked")
	public List<StMoveStorehouseVo> getSessionList()
	{
		HttpSession session = super.getRequest().getSession();
    	List<StMoveStorehouseVo> list = (List<StMoveStorehouseVo>) session.getAttribute("list");
		if (list == null) {
			list = new ArrayList<StMoveStorehouseVo>();
			session.setAttribute("list", list);
		}
		return list;
	}
	
	/**
	 * 清空移仓单明细缓存数据
	 */
	public void clear()
	{
		List<StMoveStorehouseVo> list = this.getSessionList();
		list.clear();
		super.writeJson(super.listConvertJson(list));
	}
	
	/**
	 * 移仓单审核
	 */
	public void examine()
	{
		try {
			stMoveStorehouseVo.setEnterpriseId(getNowEnterpriseId());
			stMoveStorehouseService.updateExamine(stMoveStorehouseVo);
			Msg msg=new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("仓库名称信息   预加载异常", e);
		}
	}
	
	/**
	 * 移仓单明细    添加
	 */
	public void addStMoveStorehouseDetail() {
		List<StMoveStorehouseVo> list = this.getSessionList();
		List<StMoveStorehouseVo> acceptList = stMoveStorehouseService.acceptChangesSelectedItem(stMoveStorehouseVo);
		for(StMoveStorehouseVo stMoveStorehouseVo : acceptList){
			list.add(stMoveStorehouseVo);
		}
		if(list.size() > 0){
			super.writeJson(super.listConvertJson(list));
		}else{
			Msg msg = new Msg();
			msg.setMsg("对不起，添加维修项目失败！");
			msg.setSuccess(false);
			super.writeJson(msg);
		}
	}
	
	/**
	 * 移仓单明细     删除
	 */
	public void deleteStMoveStorehouseDetail()
	{
		List<StMoveStorehouseVo> list = this.getSessionList();
		Iterator<StMoveStorehouseVo> it = list.iterator();
		synchronized (list) {
			while (it.hasNext()) {
				StMoveStorehouseVo smsvo = it.next();
				if (smsvo.getPartsId().equals(stMoveStorehouseVo.getPartsId().trim())) {
					it.remove();
				}
			}
		}
		super.writeJson(super.listConvertJson(list));
	}
	
	/**
	 * 移仓单汇总 ，明细        添加
	 */
	public void addMoveStorehouseMain()
	{
		BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
		if(user!=null&&!user.equals(""))
		    stMoveStorehouseVo.setMsdmManager(user.getBasStuff().getStfId()+"");
		try {
			stMoveStorehouseVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			stMoveStorehouseService.add(stMoveStorehouseVo,this.getSessionList());
			Msg msg = new Msg();
			msg.setMsg("success");
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("移仓单汇总 ，明细        添加异常", e);
		}
	}
	
	/**
	 * 移仓单汇总，明细         删除
	 */
	public void delMoveStorehouseMain()
	{
		try {
			stMoveStorehouseService.delete(stMoveStorehouseVo);
			Msg msg = new Msg();
			msg.setMsg("success");
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("移仓单汇总，明细         删除异常", e);
		}
	}
	
	/**
	 * 移仓单汇总 ，明细        修改
	 */
	public void updateMoveStorehouseMain()
	{
		try {
			stMoveStorehouseService.update(stMoveStorehouseVo,getSessionList());
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("移仓单汇总 ，明细        修改异常", e);
		}
	}
	
	/**
	 * 提交所有已选配件
	 */
	public void acceptChangesSelectedParts(){
		List<StMoveStorehouseVo> list = this.getSessionList();
		List<StMoveStorehouseVo> acceptList = stMoveStorehouseService.acceptChangesSelectedItem(stMoveStorehouseVo);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < acceptList.size(); j++) {
				if(acceptList.get(j).getPartsId().equals(list.get(i).getPartsId()))
				{
					StMoveStorehouseVo stMoveStorehouseVo=acceptList.get(j);
					list.set(i, stMoveStorehouseVo);
				}
			}
		}
		Msg msg = new Msg();
		msg.setSuccess(true);
		super.writeJson(msg);
	}
	
	/**
	 * 计算数量，成本额
	 */
	public void getSum()
	{
		List<StMoveStorehouseVo> list = this.getSessionList();
		double msdmSumCnt = 0;       //移仓总数量
		double msdmSumAmont=0;    //移仓总金额
		for (int i = 0; i < list.size(); i++) {
			msdmSumCnt+=Double.parseDouble(list.get(i).getMsdCnt());
			msdmSumAmont+=Double.parseDouble(list.get(i).getMsdNocastAmont());
		}
		StMoveStorehouseVo stMoveStorehouseVo=new StMoveStorehouseVo();
		stMoveStorehouseVo.setMsdmSumAmont(super.doubleFormat(msdmSumAmont)+"");
		stMoveStorehouseVo.setMsdmSumCnt(super.doubleFormat(msdmSumCnt)+"");
		super.writeJson(stMoveStorehouseVo);
	}
	
	public StMoveStorehouseVo getModel() {
		return stMoveStorehouseVo;
	}

}
