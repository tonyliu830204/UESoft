package com.syuesoft.sell.allocateManage.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.allocateManage.dao.SellAllocatelDao;
import com.syuesoft.sell.allocateManage.service.SellAllocatelService;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.base.dao.BaseTagDAO;

import com.syuesoft.sell.instore.dao.InstorehouseDAO;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsSellAllocatel;
import com.syuesoft.sell.model.XsSellAllocatelDetail;
import com.syuesoft.sell.model.XsSellInstorehouseDel;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.SystemUser;

@Service("sellAllocatelService")
public class SellAllocatelServiceImpl extends BaseLogServiceImpl implements SellAllocatelService {
	private SellAllocatelDao sellAllocatelDao;
	private InstorehouseDAO instorehouseDAO;
	private BaseTagDAO baseTagDAO;

	/*private static String allocatel = "调拨";// 287
	private static String examineState = "未审核";// 185
	private static String examineState1 = "已审核";// 186
	private static String instoreType="入库";//241
	private static String allocateType="分销调拨";//210
	private static String pay="否";//215
	private static String carSellState="二级分销";//215
	private static String sellState="在库待销";//163
*/	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}

	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}

	public InstorehouseDAO getInstorehouseDAO() {
		return instorehouseDAO;
	}

	@Autowired
	public void setInstorehouseDAO(InstorehouseDAO instorehouseDAO) {
		this.instorehouseDAO = instorehouseDAO;
	}

	public SellAllocatelDao getSellAllocatelDao() {
		return sellAllocatelDao;
	}

	@Autowired
	public void setSellAllocatelDao(SellAllocatelDao sellAllocatelDao) {
		this.sellAllocatelDao = sellAllocatelDao;
	}

	/**
	 * 查询调拨单
	 */
	
	public Datagrid querySellAllocatel(SellAllocatelVo sellAllocatelVo)
			throws Exception {
		return sellAllocatelDao.querySellAllocatel(sellAllocatelVo);

	}

	/**
	 * 修改调拨单
	 */
	
	@Log(systemName="销售系统", moduleName="调拨单管理",opertype="修改")
	public void modifySellAllocatel(SellAllocatelVo sellAllocatelVo)
			throws Exception {
		SellAllocatelVo instorehouse = JSON.parseObject(sellAllocatelVo
				.getInstorehousedateGrid(), SellAllocatelVo.class);
		List<SellAllocatelVo> insertedList = JSON.parseArray(sellAllocatelVo
				.getInserted(), SellAllocatelVo.class);
		List<SellAllocatelVo> updateList = JSON.parseArray(sellAllocatelVo
				.getUpdated(), SellAllocatelVo.class);
		
		List<SellAllocatelVo> deletedList = JSON.parseArray(sellAllocatelVo
				.getDeleted(), SellAllocatelVo.class);

		BaseBean b = sellAllocatelDao
				.get("from   XsSellAllocatel  locatel where  locatel.allocateId="
						+ instorehouse.getAllocateId());
		XsSellAllocatel xsSellAllocatel = (XsSellAllocatel) b;
		xsSellAllocatel.setXsDistributorId(instorehouse.getXsDistributorId());
		
		xsSellAllocatel.setConsignee(instorehouse.getConsignee());
		xsSellAllocatel.setAllocatePerson(instorehouse.getAllocatePerson());
		xsSellAllocatel.setWarehouse(instorehouse.getWarehouse());
		xsSellAllocatel.setRemark(instorehouse.getRemark());
		xsSellAllocatel.setAllocateAmount(instorehouse.getAllocateAmount());
		sellAllocatelDao.merge(xsSellAllocatel);
		if (insertedList != null && insertedList.size() > 0) {
			for (SellAllocatelVo sell : insertedList) {
				XsSellInstorehouseDel instoreDel = instorehouseDAO
						.findDelById(sell.getDetailsId());
				
				instoreDel.setSellAllocatelType(baseTagDAO
						.getChildId(Contstants.BASE_SELL.INSTORETYPE,Contstants.BASE_SELL.INSTORETYPE1,sellAllocatelVo.getEnterprise_id()));// 修改库存状态=调拨
				instorehouseDAO.saveOrUpdate(instoreDel);
				XsCarInfo car=(XsCarInfo) instorehouseDAO.get("from XsCarInfo where  carId="+sell.getCarId());
				car.setSellStateChild(baseTagDAO.getChild(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.DISTRi,sellAllocatelVo.getEnterprise_id()));// 修改销售状态=二级分销
				car.setDistributorId(instorehouse.getXsDistributorId());
				instorehouseDAO.saveOrUpdate(car);
				XsSellAllocatelDetail allocatelDetail = new XsSellAllocatelDetail();
				allocatelDetail.setAllocateId(instorehouse.getAllocateId());
				allocatelDetail.setDetailsId(instoreDel.getDetailsId());
				allocatelDetail.setAllocateAmount(sell.getAllAmount());
				instorehouseDAO.save(allocatelDetail);
			}
		}
		if (updateList != null && updateList.size() > 0) {
			for (SellAllocatelVo sell : updateList) {
				XsSellAllocatelDetail  allocatelDetail = (XsSellAllocatelDetail) sellAllocatelDao
				.get("from  XsSellAllocatelDetail tail where  tail.detailsId="+sell.getDetailsId());
					allocatelDetail.setAllocateAmount(sell.getAllAmount());
				instorehouseDAO.save(allocatelDetail);
			}
		}
		if (deletedList != null && deletedList.size() > 0) {
			for (SellAllocatelVo sell : deletedList) {
				XsSellInstorehouseDel instoreDel = instorehouseDAO
						.findDelById(sell.getDetailsId());
				instoreDel.setSellAllocatelType(baseTagDAO
						.getChildId(Contstants.BASE_SELL.INSTORETYPE,Contstants.BASE_SELL.INSTORETYPE3,sellAllocatelVo.getEnterprise_id()));// 修改库存状态=入库
				instorehouseDAO.merge(instoreDel);
				XsSellAllocatelDetail tail = (XsSellAllocatelDetail) sellAllocatelDao
				.get("from  XsSellAllocatelDetail tail where  tail.detailsId="
						+instoreDel.getDetailsId());
				XsCarInfo car=(XsCarInfo) instorehouseDAO.get("from XsCarInfo where  carId="+ instoreDel.getCarInfo().getCarId());
				car.setSellStateChild(baseTagDAO.getChild(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.FORSALE,sellAllocatelVo.getEnterprise_id()));// 修改销售状态=在库待销
				car.setDistributorId(null);
				instorehouseDAO.saveOrUpdate(car);
				sellAllocatelDao.delete(tail);
				setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"修改单号为【"+xsSellAllocatel.getAllocatecode()+"】的调拨单！");
			}

		}

	}

	/**
	 * 新增调拨单信息
	 */
	
	@Log(systemName="销售系统", moduleName="调拨单管理",opertype="新增")
	public void addInstoreCar(SellAllocatelVo sellAllocatelVo) throws Exception {
		SellAllocatelVo instorehouse = JSON.parseObject(sellAllocatelVo
				.getInstorehousedateGrid(), SellAllocatelVo.class);

		List<SellAllocatelVo> insertedList = JSON.parseArray(sellAllocatelVo
				.getDetaildateGrid(), SellAllocatelVo.class);
		XsSellAllocatel tel = new XsSellAllocatel();

		BeanUtils.copyProperties(instorehouse, tel);
		tel.setAllocatecode(CreateID.createId("instorehouse",Contstants.SELL_BILLSDEPLOY.SELLALLOCATEl));//单号
		tel.setExamine(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellAllocatelVo.getEnterprise_id()));// 未审核
		tel.setAllocateType(baseTagDAO.getChildId(Contstants.ALLOCATETYPE.ALLOCATETYPE,Contstants.ALLOCATETYPE.FENXIAO,sellAllocatelVo.getEnterprise_id()));//分销调拨
		tel.setPaymentState(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellAllocatelVo.getEnterprise_id()));//付讫：否
		tel.setEnterprise_id(sellAllocatelVo.getEnterprise_id());
		instorehouseDAO.save(tel);
		if (insertedList != null && insertedList.size() > 0) {
			for (SellAllocatelVo delVo : insertedList) {
				XsSellInstorehouseDel instoreDel = instorehouseDAO
						.findDelById(delVo.getDetailsId());

				instoreDel.setSellAllocatelType(baseTagDAO.getChildId(Contstants.BASE_SELL.INSTORETYPE,Contstants.BASE_SELL.INSTORETYPE1,sellAllocatelVo.getEnterprise_id()));// 修改库存状态=调拨
				instorehouseDAO.saveOrUpdate(instoreDel);
				XsCarInfo car=(XsCarInfo) instorehouseDAO.get("from XsCarInfo where  carId="+delVo.getCarId());
				car.setSellStateChild(baseTagDAO.getChild(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.DISTRi,sellAllocatelVo.getEnterprise_id()));// 修改销售状态=二级分销
				car.setDistributorId(instorehouse.getXsDistributorId());
				instorehouseDAO.saveOrUpdate(car);
				XsSellAllocatelDetail allocatelDetail = new XsSellAllocatelDetail();
				allocatelDetail.setAllocateId(tel.getAllocateId());
				allocatelDetail.setDetailsId(instoreDel.getDetailsId());
				allocatelDetail.setAllocateAmount(delVo.getAllAmount());
				instorehouseDAO.save(allocatelDetail);
				setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"新增单号为【"+tel.getAllocatecode()+"】的调拨单！");
			}
		}

	}

	/**
	 * 删除汇总
	 */
	
	@Log(systemName="销售系统", moduleName="调拨单管理",opertype="删除")
	public void deleteRecord(SellAllocatelVo sellAllocatelVo) throws Exception {
		String sql = "SELECT 	A.details_id FROM "
				+ "xs_sell_instorehouse_del A,xs_sell_allocatel_detail B,"
				+ "xs_sell_allocatel c WHERE "
				+ "b.details_id=A.details_id AND"
				+ " c.allocate_id=b.allocate_id AND " + " c.allocate_id="
				+ sellAllocatelVo.getAllocateId();
		List list = sellAllocatelDao.getInfor(sql);
		if(list!=null&&list.size()>0){
		
			for (int i = 0; i < list.size(); i++) {
				Integer id = (Integer) list.get(i);
				XsSellInstorehouseDel del = sellAllocatelDao.findDelById(id);
				del.setSellAllocatelType(baseTagDAO.getChildId(Contstants.BASE_SELL.INSTORETYPE,Contstants.BASE_SELL.INSTORETYPE3,sellAllocatelVo.getEnterprise_id()));//入库
				sellAllocatelDao.saveOrUpdate(del);
				XsCarInfo car=(XsCarInfo) instorehouseDAO.get("from XsCarInfo where  carId="+ del.getCarInfo().getCarId());
				car.setSellStateChild(baseTagDAO.getChild(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.FORSALE,sellAllocatelVo.getEnterprise_id()));// 修改销售状态=在库待销
				car.setDistributorId(null);
				instorehouseDAO.saveOrUpdate(car);
			}
		
		}
		String sql2 = "SELECT b.allocatel_detail_id FROM xs_sell_allocatel_detail B,xs_sell_allocatel c "
				+ "WHERE  c.allocate_id=b.allocate_id AND c.allocate_id="
				+ sellAllocatelVo.getAllocateId();
		List list1 = sellAllocatelDao.getInfor(sql2);
		if(list1!=null&&list1.size()>0){
		for (int i = 0; i < list1.size(); i++) {
			int id = (Integer) list1.get(i);
			XsSellAllocatelDetail tail = (XsSellAllocatelDetail) sellAllocatelDao
					.get("from  XsSellAllocatelDetail tail where  tail.allocatelDetailId="
							+ id);
			sellAllocatelDao.delete(tail);
		}
		}

		XsSellAllocatel tel = sellAllocatelDao.findById(sellAllocatelVo
				.getAllocateId());
		if(tel!=null){
		sellAllocatelDao.delete(tel);
		}
		setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"删除单号为【"+tel.getAllocatecode()+"】的调拨单！");

	}
	/**
	 * 判断是否已审核
	 * */
	
	public Boolean isRefundment(SellAllocatelVo sellAllocatelVo) throws Exception {
		Integer examine=baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,sellAllocatelVo.getEnterprise_id());
		if(sellAllocatelVo.getExamine().equals(examine))
			return true;
		return false;
	}

	// 审核
	@Log(systemName="销售系统", moduleName="调拨单管理",opertype="审核")
	public void updateExamine(SellAllocatelVo sellAllocatelVo) throws Exception {
		XsSellAllocatel allocatel = sellAllocatelDao.findById(sellAllocatelVo
				.getAllocateId());
		if(allocatel!=null){
			int examine=baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellAllocatelVo.getEnterprise_id());
			if(allocatel.getExamine()==examine){
				allocatel.setExamine(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT1,sellAllocatelVo.getEnterprise_id()));
				setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"审核单号为【"+allocatel.getAllocatecode()+"】的调拨单！");
			}else{
				allocatel.setExamine(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellAllocatelVo.getEnterprise_id()));;
				setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"取消审核单号为【"+allocatel.getAllocatecode()+"】的调拨单！");
			}	
		}
		
		
		sellAllocatelDao.update(allocatel);

	}

	/**
	 * 根据分销商查调拨单
	 */
	
	public Datagrid queryList(SellAllocatelVo sellAllocatelVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer hql = new StringBuffer(
				"from XsSellAllocatel tel where tel.enterprise_id="+sellAllocatelVo.getEnterprise_id()+" and  tel.xsDistributorId="
						+ sellAllocatelVo.getXsDistributorId());
		List<BaseBean> lst = sellAllocatelDao.find(hql.toString(),
				sellAllocatelVo.getPage(), sellAllocatelVo.getRows());
		List<SellAllocatelVo> rows = new ArrayList<SellAllocatelVo>();
		if (lst != null && lst.size() > 0) {
			for (BaseBean base : lst) {
				XsSellAllocatel cal = (XsSellAllocatel) base;
				SellAllocatelVo vo = new SellAllocatelVo();
				vo.setAllocateId(cal.getAllocateId());
				vo.setAllocatecode(cal.getAllocatecode());
				vo.setAllocateDate(cal.getAllocateDate());
				vo.setXsDistributorId(cal.getXsDistributorId());
				rows.add(vo);
			}

		}
		int total = sellAllocatelDao.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

	
	public  DatagridAnalyze  queryFather(SellAllocatelVo sellAllocatelVo)
			throws Exception {
		// TODO Auto-generated method stub
		return  sellAllocatelDao.queryFather(sellAllocatelVo);
	}

	
	public List<SellAllocatelVo> findAllocatel(SellAllocatelVo sellAllocatelVo)
			throws Exception {
		// TODO Auto-generated method stub
		return  sellAllocatelDao.findAllocatel(sellAllocatelVo);
	}

	
	public Boolean isUse(SellAllocatelVo sellAllocatelVo) throws Exception {
		String sql = "SELECT * FROM xs_sell_instorehouse_del a ,xs_sell_allocatel_detail B " +
				"WHERE  a.details_id=b.details_id  and a.xs_sell_allocatel_type='"+baseTagDAO.getChildId(Contstants.BASE_SELL.INSTORETYPE,Contstants.INSTORETYPE.ALLOCATELOUT,sellAllocatelVo.getEnterprise_id()) +"' " +
						" and  b.allocate_id ='"+sellAllocatelVo.getAllocateId()+"'";
		List list = sellAllocatelDao.createSQLQuery(sql);
		if(list!=null  && list.size()>0 ){
			return true;
		}
		return false;
	}

}
