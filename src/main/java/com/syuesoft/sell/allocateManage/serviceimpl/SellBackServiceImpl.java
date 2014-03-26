package com.syuesoft.sell.allocateManage.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.dao.SellBackDao;
import com.syuesoft.sell.allocateManage.service.SellBackService;
import com.syuesoft.sell.allocateManage.vo.SellBackVo;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.carAllocate.vo.CarBarnInfoVo;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellAllocatelBack;
import com.syuesoft.sell.model.XsSellAllocatelBackId;
import com.syuesoft.sell.model.XsSellAllocatelDetail;
import com.syuesoft.sell.model.XsSellBack;
import com.syuesoft.sell.model.XsSellInstorehouseDel;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.SystemUser;

@Service("sellBackService")
public class SellBackServiceImpl extends BaseLogServiceImpl implements SellBackService {
	private SellBackDao sellBackDao;
	private BaseTagDAO baseTagDAO;
	
/*	private static String allocatelType="调拨";//287
	private static String allocatelType1="调退";//288
	private static String examineState="未审核";//185
	private static String examineState1="已审核";//186
	private static String allocateType="分销调拨";//210
	private static String pay="否";//215
	private static String sellState="在库待销";//163
	private static String carSellState="二级分销";
	*/

	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}

	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}

	public SellBackDao getSellBackDao() {
		return sellBackDao;
	}

	@Autowired
	public void setSellBackDao(SellBackDao sellBackDao) {
		this.sellBackDao = sellBackDao;
	}

	/**
	 * 调退单汇总信息查询
	 */
	
	public Datagrid getSellBackInfo(SellBackVo sellBackVo)
			throws Exception {
		return sellBackDao.getSellBackInfo(sellBackVo);

	}

	/**
	 * 新增调退单
	 */
	
	@Log(systemName="销售系统", moduleName="调退单管理",opertype="新增")
	public void addInstoreCar(SellBackVo sellBackVo) throws Exception {
		XsSellBack back = JSON.parseObject(sellBackVo
				.getInstorehousedateGrid(),XsSellBack.class);
		List<CarBarnInfoVo> insertedList = JSON.parseArray(sellBackVo
				.getDetaildateGrid(), CarBarnInfoVo.class);
		back.setBackCode(CreateID.createId("instorehouse", Contstants.SELL_BILLSDEPLOY.SELLBACK));//单号
		Integer examine=baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellBackVo.getEnterprise_id()); 
		if(examine!=null){
			XsChilddictionary cartrim= baseTagDAO.findById(examine);
			back.setXsChilddictionary(cartrim);
		}
	//	back.setExamine();//未审核	
		Integer backType=baseTagDAO.getChildId(Contstants.ALLOCATETYPE.ALLOCATETYPE,Contstants.ALLOCATETYPE.FENXIAO,sellBackVo.getEnterprise_id());
		if(backType!=null){
			XsChilddictionary backtype= baseTagDAO.findById(backType);
			back.setXsChilddictionary2(backtype);
		}
		//back.setBackType(baseTagDAO.getChildId(Contstants.ALLOCATETYPE.ALLOCATETYPE,Contstants.ALLOCATETYPE.FENXIAO,sellBackVo.getEnterprise_id()));//调退类型：分销调拨
		Integer balanceState= baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellBackVo.getEnterprise_id());
		if(balanceState!=null){
			XsChilddictionary balancestate= baseTagDAO.findById(balanceState);
			back.setXsChilddictionary1(balancestate);
		}
		//back.setBalanceState(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellBackVo.getEnterprise_id()));//对账：否
		
		back.setEnterprise_id(sellBackVo.getEnterprise_id());
		sellBackDao.save(back);
		if(insertedList!=null&&insertedList.size()>0){
			for (CarBarnInfoVo delVo : insertedList) {
				XsSellInstorehouseDel instoreDel = sellBackDao.findDelById(delVo
						.getDetailsId());
				if(instoreDel!=null){
					instoreDel.setSellAllocatelType(baseTagDAO.getChildId(Contstants.BASE_SELL.INSTORETYPE,Contstants.BASE_SELL.INSTORETYPE2,sellBackVo.getEnterprise_id()));// 修改库存状态=调退	
				}
				
				/*instoreDel.setInstorehouseType(271);//271退厂*/
				sellBackDao.saveOrUpdate(instoreDel);
				XsCarInfo car=(XsCarInfo) sellBackDao.get("from XsCarInfo where  carId="+delVo.getCarId());
				if(car!=null){
				car.setSellStateChild(baseTagDAO.getChild(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.FORSALE,sellBackVo.getEnterprise_id()));//销售状态=在库待销
				car.setDistributorId(null);
				}
				sellBackDao.saveOrUpdate(car);
				XsSellAllocatelDetail de = (XsSellAllocatelDetail) sellBackDao
						.get("from XsSellAllocatelDetail where detailsId="
								+ delVo.getDetailsId());
			
				XsSellAllocatelBack allocatel = new XsSellAllocatelBack();
				XsSellAllocatelBackId allocatelDetail =new XsSellAllocatelBackId();
				allocatelDetail.setBackId(back.getBackId());
				allocatelDetail.setAllocatelDetailId(de.getAllocatelDetailId());
				allocatel.setId(allocatelDetail);
				sellBackDao.save(allocatel);
		}
			setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"新增单号为【"+back.getBackCode()+"】的调退单！");

		}
	}

	/**
	 * 删除调退汇总信息
	 */
	@SuppressWarnings("unchecked")
	
	@Log(systemName="销售系统", moduleName="调退单管理",opertype="删除")
	public void deleteRecord(SellBackVo sellBackVo) throws Exception {
		String sql="SELECT 	a.details_id FROM xs_sell_instorehouse_del A," +
				"xs_sell_allocatel_detail B,xs_sell_allocatel_back C," +
				"xs_sell_back D " +
				"WHERE a.details_id=b.details_id AND " +
				"b.allocatel_detail_id=c.allocatel_detail_id " +
				"AND d.back_id=c.back_id " +
				"AND d.back_id="+sellBackVo.getBackId();
		List list = sellBackDao.getInfor(sql);
		if(list!=null && list.size()>0 ){
			for (int i = 0; i < list.size(); i++) {
				Integer id =(Integer)list.get(i);
				XsSellInstorehouseDel del=sellBackDao.findDelById(id);
				del.setSellAllocatelType(baseTagDAO.getChildId(Contstants.BASE_SELL.INSTORETYPE,Contstants.BASE_SELL.INSTORETYPE1,sellBackVo.getEnterprise_id()));// 修改库存状态=调拨
				sellBackDao.saveOrUpdate(del);
				XsCarInfo car=(XsCarInfo) sellBackDao.get("from XsCarInfo where  carId="+del.getCarInfo().getCarId());
				car.setSellStateChild(baseTagDAO.getChild(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.DISTRi,sellBackVo.getEnterprise_id()));////销售状态=二级分销
				car.setDistributorId(sellBackVo.getXsDistributorId());
				sellBackDao.saveOrUpdate(car);
			}
		}
		List<Object>ba= sellBackDao.find("from XsSellAllocatelBack back where back.id.backId="+sellBackVo.getBackId());
		//XsSellAllocatelBack allocatel =( XsSellAllocatelBack) sellBackDao.get("from XsSellAllocatelBack where id="+ba.getAllocatelDetailId());
		if(ba!=null && ba.size()>0 ){
			for(Object o:ba){
				sellBackDao.delete(o);
			}
		}
		//sellBackDao.delete(allocatel);
		XsSellBack back=(XsSellBack) sellBackDao.get("from XsSellBack ba where ba.backId="+sellBackVo.getBackId());
		if(back!=null){
		sellBackDao.delete(back);
		}
		setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"删除单号为【"+back.getBackCode()+"】的调退单！");

	}
	/**
	 * 修改调退汇总
	 */
	
	@Log(systemName="销售系统", moduleName="调退单管理",opertype="修改")
	public void modifySellAllocatel(SellBackVo sellBackVo) throws Exception {
		XsSellBack back = JSON.parseObject(sellBackVo.getInstorehousedateGrid(),XsSellBack.class);
		Integer examine=baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellBackVo.getEnterprise_id()); 
		if(examine!=null){
			XsChilddictionary cartrim= baseTagDAO.findById(examine);
			back.setXsChilddictionary(cartrim);
		}
	//	back.setExamine();//未审核	
		Integer backType=baseTagDAO.getChildId(Contstants.ALLOCATETYPE.ALLOCATETYPE,Contstants.ALLOCATETYPE.FENXIAO,sellBackVo.getEnterprise_id());
		if(backType!=null){
			XsChilddictionary backtype= baseTagDAO.findById(backType);
			back.setXsChilddictionary2(backtype);
		}
		//back.setBackType(baseTagDAO.getChildId(Contstants.ALLOCATETYPE.ALLOCATETYPE,Contstants.ALLOCATETYPE.FENXIAO,sellBackVo.getEnterprise_id()));//调退类型：分销调拨
		Integer balanceState= baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellBackVo.getEnterprise_id());
		if(balanceState!=null){
			XsChilddictionary balancestate= baseTagDAO.findById(balanceState);
			back.setXsChilddictionary1(balancestate);
		}
		sellBackDao.merge(back);
		
		
		List<SellBackVo> insertedList = JSON.parseArray(sellBackVo.getInserted(), SellBackVo.class);
		List<SellBackVo> updateList = JSON.parseArray(sellBackVo.getUpdated(), SellBackVo.class);
		List<SellBackVo> deletedList = JSON.parseArray(sellBackVo.getDeleted(), SellBackVo.class);
		
		if (insertedList != null && insertedList.size() > 0) {
				for (SellBackVo delVo : insertedList) {
				XsSellInstorehouseDel instoreDel = sellBackDao.findDelById(delVo.getDetailsId());
				if(instoreDel!=null){
					instoreDel.setSellAllocatelType(baseTagDAO.getChildId(Contstants.BASE_SELL.INSTORETYPE,Contstants.BASE_SELL.INSTORETYPE2,sellBackVo.getEnterprise_id()));// 修改库存状态=调退
					/*instoreDel.setInstorehouseType(271);//271退厂*/
					sellBackDao.saveOrUpdate(instoreDel);	
				}
			
				XsCarInfo car=(XsCarInfo) sellBackDao.get("from XsCarInfo where  carId="+delVo.getCarId());
				if(car!=null){
				car.setSellStateChild(baseTagDAO.getChild(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.FORSALE,sellBackVo.getEnterprise_id()));//销售状态=在库待销
				car.setDistributorId(null);
				sellBackDao.saveOrUpdate(car);
				}
				XsSellAllocatelDetail de = (XsSellAllocatelDetail) sellBackDao
						.get("from XsSellAllocatelDetail where detailsId="
								+ delVo.getDetailsId());
				XsSellAllocatelBack allocatel = new XsSellAllocatelBack();
				XsSellAllocatelBackId allocatelDetail =new XsSellAllocatelBackId();
				allocatelDetail.setBackId(back.getBackId());
				allocatelDetail.setAllocatelDetailId(de.getAllocatelDetailId());
				allocatel.setId(allocatelDetail);
				sellBackDao.save(allocatel);
			}
		}
				if (updateList != null && updateList.size() > 0) {
					for (SellBackVo delVo : updateList) {
						XsSellAllocatelDetail de = (XsSellAllocatelDetail) sellBackDao
						.get("from XsSellAllocatelDetail where detailsId="
								+ delVo.getDetailsId());
						de.setAllocateAmount(delVo.getAllAmount());
						sellBackDao.update(de);
					}
					}
				if (deletedList != null && deletedList.size() > 0) {
					for (SellBackVo delVo : deletedList) {
						XsSellInstorehouseDel del=sellBackDao.findDelById(delVo.getDetailsId());
						del.setSellAllocatelType(baseTagDAO.getChildId(Contstants.BASE_SELL.INSTORETYPE,Contstants.BASE_SELL.INSTORETYPE1,sellBackVo.getEnterprise_id()));// 修改库存状态=调拨
						sellBackDao.saveOrUpdate(del);
						
						XsSellAllocatelBack allocatel =( XsSellAllocatelBack) sellBackDao.get("from XsSellAllocatelBack back where back.id.allocatelDetailId="+delVo.getAllocatel_detail_id());
						XsCarInfo car=(XsCarInfo) sellBackDao.get("from XsCarInfo where  carId="+del.getCarInfo().getCarId());
						car.setSellStateChild(baseTagDAO.getChild(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.DISTRi,sellBackVo.getEnterprise_id()));
						car.setDistributorId(back.getXsDistributorId());
						sellBackDao.saveOrUpdate(car);
					
						sellBackDao.delete(allocatel);
						setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"删除单号为【"+back.getBackCode()+"】的调退单！");

						
						
					}
					}
			
			
		}
		/*Object b = sellBackDao.get("from   XsSellBack  locatel where  locatel.backId="+ sellBackVo.getBackId());
		 XsSellBack  XsSellBack=(XsSellBack) b;
		 XsSellBack.setBackId(sellBackVo.getBackId());
		 XsSellBack.setHandbackAllocateAmount(sellBackVo.getHandbackAllocateAmount());		 
		 XsSellBack.setRemark(sellBackVo.getRemark());
		 XsSellBack.setWarehouse(sellBackVo.getWarehouse());
		 
		 String sql=" SELECT A.instorehouse_id FROM xs_sell_instorehouse A,xs_sell_instorehouse_del B," +
		 		"xs_sell_allocatel_detail C, xs_sell_allocatel_back D,xs_sell_back E " +
		 		"WHERE e.back_id=d.back_id AND " +
		 		"d.allocatel_detail_id=c.allocatel_detail_id AND " +
		 		"c.details_id=b.details_id AND " +
		 		"b.instorehouse_id=a.instorehouse_id AND  " +
		 		"e.back_id='"+sellBackVo.getBackId()+"'";
		   List list = sellBackDao.getInfor(sql);
			for (int i = 0; i < list.size(); i++) {
				Integer id =(Integer)list.get(i);
				XsSellInstorehouse x = (XsSellInstorehouse) sellBackDao.get(
						"from XsSellInstorehouse house where house.instorehouseId='"
						+ id + "'");
		x.setWarehouse(sellBackVo.getWarehouse());
		 sellBackDao.merge(x);
	}
			 sellBackDao.merge(XsSellBack);*/
	
/**
 * 审核
 */
	
	@Log(systemName="销售系统", moduleName="调退单管理",opertype="审核")
	public void updateExamine(SellBackVo sellBackVo) throws Exception {
		XsSellBack allocatel=(XsSellBack) sellBackDao.get("from XsSellBack back where back.backId='"+sellBackVo.getBackId()+"'");
		int examine=baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellBackVo.getEnterprise_id());
		if(allocatel!=null){
			if(allocatel.getXsChilddictionary().getChildId()==examine){
				Integer examine1= baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT1,sellBackVo.getEnterprise_id());

				if(examine1!=null){
					XsChilddictionary examines= baseTagDAO.findById(examine1);
					allocatel.setXsChilddictionary(examines);
				}
				setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"审核单号为【"+allocatel.getBackCode()+"】的调退单！");
	
			}else{
				Integer examine2= baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellBackVo.getEnterprise_id());

				if(examine2!=null){
					XsChilddictionary examines2= baseTagDAO.findById(examine2);
					allocatel.setXsChilddictionary(examines2);
				}
				allocatel.setExamine(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellBackVo.getEnterprise_id()));
				setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"取消审核单号为【"+allocatel.getBackCode()+"】的调退单！");
	
			}
		}
		sellBackDao.update(allocatel);
		
		
	}

	
	public Datagrid getSellBackByDis(SellBackVo sellBackVo) throws Exception {	
		return sellBackDao.getSellBackByDis(sellBackVo);
	}

	
	public List<SellBackVo> findBack(SellBackVo sellBackVo) throws Exception {
		
		return sellBackDao.findBack(sellBackVo);
	}

	
	public DatagridAnalyze getSellBack(SellBackVo sellBackVo) throws Exception {
	
		return sellBackDao.getSellBack(sellBackVo);
	}

	
	public Boolean isRefundment(SellBackVo sellBackVo) throws Exception {
		Integer examine=baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,sellBackVo.getEnterprise_id());
		if(sellBackVo.getExamine().equals(examine))
			return true;
		return false;
	}
}
