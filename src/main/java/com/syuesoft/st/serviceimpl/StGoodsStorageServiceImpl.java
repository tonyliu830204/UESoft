package com.syuesoft.st.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasChilddictionaryDao;
import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.dao.StageAddpriceDao;
import com.syuesoft.bas.serviceimpl.BaseServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasPartsType;
import com.syuesoft.model.BasRelationCampany;
import com.syuesoft.model.BasStorehouse;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.FrtParts;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.model.StGoodsStorage;
import com.syuesoft.model.StPurOrder;
import com.syuesoft.model.StStorageItem;
import com.syuesoft.model.StageAddprice;
import com.syuesoft.st.dao.BasPartsTypeDAO;
import com.syuesoft.st.dao.BasRelationCampanyDAO;
import com.syuesoft.st.dao.FrtPartsDAO;
import com.syuesoft.st.dao.PartsChangePriceDAO;
import com.syuesoft.st.dao.StGoodsStorageDAO;
import com.syuesoft.st.dao.StPurOrderDAO;
import com.syuesoft.st.dao.StPurOrderitemDAO;
import com.syuesoft.st.dao.StStorageItemDAO;
import com.syuesoft.st.service.StGoodsStorageService;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.st.vo.StPurOrderVo;
import com.syuesoft.st.vo.StStorageVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;
/**
 * 入库单管理Service接口实现类
 * @author SuMing
 */
@Service("stGoodsStorageService")
public class StGoodsStorageServiceImpl extends BaseServiceImpl implements StGoodsStorageService {

	@Autowired StPurOrderDAO stPurOrderDAO;                 // 采购单汇总DAO
	@Autowired BasRelationCampanyDAO basRelationCampanyDAO; // 相关单位DAO
	@Autowired StPurOrderitemDAO stPurOrderitemDAO;         // 采购订单明细DAO
	@Autowired FrtPartsDAO frtPartsDAO;                     // 配件档案DAO
	@Autowired BasPartsTypeDAO basPartsTypeDAO;             // 配件型号DAO
	@Autowired PartsChangePriceDAO partsChangePriceDAO;     // 配件调价表DAO
	@Autowired StGoodsStorageDAO stGoodsStorageDAO;         // 入库单汇总DAO
	@Autowired StStorageItemDAO stStorageItemDAO;           // 入库单明细DAO
	@Autowired BasStorehouseDAO basStorehouseDAO;           //仓库DAO
	@Autowired BasStuffDao basStuffDao;                     //员工信息DAO
	@Autowired StageAddpriceDao stageAddpriceDao;           //剃度加价DAO
	@Autowired BasChilddictionaryDao basChilddictionaryDaoI;
	
	/**
	 * 明细数据由JSON转换为List集合
	 */
	public List<StStorageVo> jsonChangeDetailList(StStorageVo stStorageVo){
		List<StStorageVo> list =new ArrayList<StStorageVo>();
		if(stStorageVo.getDetailData()!=null&&!stStorageVo.getDetailData().equals("")){
			JSONObject jsDetailData=JSON.parseObject(stStorageVo.getDetailData());
			List<StStorageVo> resultList = JSON.parseArray(jsDetailData.get("rows").toString(), StStorageVo.class);
			if(resultList.size() > 0){
				for (StStorageVo ssvo : resultList) {
					list.add(ssvo);
				}
			}
		}
		return list;
	}
	
	/**
	 * 入库单汇总      综合查询
	 */
	public Json searchStGoodsStorageByCondition(StStorageVo stStorageVo)throws Exception{
		return  stGoodsStorageDAO.searchStGoodsStorageByCondition(stStorageVo.getStorageDateStart(),stStorageVo.getStorageDateEnd(),stStorageVo.getRelcampName(), stStorageVo.getStorageId(), stStorageVo.getOrderId(),stStorageVo.getEnterpriseId());
	}
	
	/**
	 *  采购单信息         预加载
	 */
	public Json loadStPurOrder(StStorageVo  stStorageVo)throws Exception{
		return stPurOrderDAO.loadStPurOrder(stStorageVo.getSort(), stStorageVo.getOrder(), stStorageVo.getRows(), stStorageVo.getPage(),Contstants.AUDIT_TAG.AUDITYESS,"StGoodsStorage",stStorageVo.getEnterpriseId());
	}
	
	/**
	 *  采购单信息          综合查询
	 */
	public Json searchStPurOrderByCondition(StStorageVo  stStorageVo)throws Exception{
		return stPurOrderDAO.searchByCondition(null, null, null, null, stStorageVo.getOrderId(), null
				, null, null, null, Contstants.AUDIT_TAG.AUDITYESS, null, "StGoodsStorage",stStorageVo.getEnterpriseId());
	}
	
	/**
	 * 根据采购单号查找采购明细记录
	 */
	public List<StPurOrderVo> searchStPurOrderItemByOrderId(StStorageVo stStorageVo)throws Exception {
		return stPurOrderitemDAO.searchStOrderItemByOrderId(stStorageVo.getOrderId());
	}
	
	
	/**
	 * 根据入库单号获取  入库明细配件 信息  
	 */
	public List<PartsNowCountVo> searchStStorageDetailByStorageId(PartsNowCountVo partsNowCountVo) throws Exception{
		return stStorageItemDAO.searchStStorageDetailByStorageId(partsNowCountVo.getStorageId());
	}
	
	/**
	 * 根据入库单ID查找入库单明细信息
	 */
	public Json searchByIdStStorageItem(StStorageVo stStorageVo)throws Exception {
		return stStorageItemDAO.searchStStorageItemByStorageId(stStorageVo.getStorageId());
	}
	
	/**
	 * 供应商信息    预加载
	 */
	public Json loadBasRelationCampany(StStorageVo ssvo)throws Exception{
		return  basRelationCampanyDAO.loadBasRelationCampany(ssvo.getPage(), ssvo.getRows(), ssvo.getSort(), ssvo.getOrder(),ssvo.getEnterpriseId());
	}
	
	/**
	 * 供应商信息    综合查询
	 */
	public Json searchBasRelationCampanyByCondition(StStorageVo stStorageVo)throws Exception{
		return basRelationCampanyDAO.searchBasRelationCampanyByCondition(stStorageVo.getRelcampId(), stStorageVo.getRelcampName(),stStorageVo.getEnterpriseId());
	}
	
	/**
	 * 添加入库单数据前     加价率验证
	 */
	public int addIdentify(StStorageVo stStorageVo)throws Exception{
		int result=0;
		List<StPurOrderVo> spoList = stPurOrderitemDAO.searchStOrderItemByOrderId(stStorageVo.getOrderId());//从采购单中获取入库明细(采购明细)配件信息
		if(spoList!=null&&spoList.size()>0){
			for (StPurOrderVo sp:spoList) {
				if(stStorageVo.getAddpriceRate().equals("300")){//300是剃度加价率 
					double taxPrice=Double.parseDouble(sp.getTaxPrice());
					StageAddprice sa=stageAddpriceDao.get(" from StageAddprice sa where sa.startAmont<="+taxPrice+" and sa.endAmont>"+taxPrice);//获取梯度加价率对象
					if(sa==null||sa.equals(""))
						return result=1;//代表该配件含税成本价不在梯度调价范围内，系统无法调价
				}else if(stStorageVo.getAddpriceRate().equals("301")){
					FrtParts fp = frtPartsDAO.get(" from FrtParts fp where fp.partsId='"+sp.getPartsId()+"'");
					BasPartsType basPartsType=basPartsTypeDAO.get(" from BasPartsType bpt where bpt.ptypeId="+fp.getBasPartsType().getPtypeId()); //获取默认加价率对象
					if(basPartsType==null||basPartsType.equals(""))
						return result=2;//代表该配件含税成本价不在默认调价范围内，系统无法调价
				}
			}
		}
		return result;
	}
	
	/**
	 * 判断该入库单是否已月结
	 */
	public boolean doMonthlyStatemont(StStorageVo stStorageVo)throws Exception{
	    String sql="SELECT * FROM MIN_MAX_MONTHLYSTATEMONT WHERE '"+stStorageVo.getStorageDate().substring(0, 19)+"' BETWEEN MIN_MAX_MONTHLYSTATEMONT.STARTTIME AND  MIN_MAX_MONTHLYSTATEMONT.ENDTIME";
	    List<Object[]> resultList = stPurOrderitemDAO.createSQLQuery(sql);//从采购单中获取入库明细(采购明细)配件信息
	    if(resultList!=null){
	    	return false;//日期属于月结时间内日期，不能操作（删除或修改）
	    }
	    return true;//日期属于月结时间内日期，能操作（删除或修改）
	}
	
	/**
	 * 判断该入库单是否可以删除
	 */
	public boolean doDeleteAndUpdate(StStorageVo stStorageVo)throws Exception{
		List<Object[]> list=stGoodsStorageDAO.createSQLQuery("SELECT st_goods_storage.STORAGE_ID FROM st_goods_storage WHERE st_goods_storage.STORAGE_ID"+
                 " IN (SELECT st_rt_goods_main.STORAGE_ID FROM st_rt_goods_main) and st_goods_storage.STORAGE_ID='"+stStorageVo.getStorageId()+"'");
		if(list!=null&&list.size()>0)
			return false;//该入库单在退货单中存在，返回false
		else
			return true;//该入库单在退货单中不存在，返回true
	}
	
	/**
	 * 入库单汇总         添加
	 */
	@Log(moduleName = "入库单管理", opertype = "新增入库单", content = "入库单管理-->新增入库单")
	public void add(StStorageVo stStorageVo,List<StStorageVo> list) throws Exception {
		StGoodsStorage sgs = new StGoodsStorage();
		sgs.setStorageId(CreateID.createId("StGoodsStorage", "RK"));
		sgs=copyMainProperty(sgs, stStorageVo);
		stGoodsStorageDAO.save(sgs);// 首先保存入库单汇总信息
		
		if(stStorageVo.getOrderId()!=null&&!stStorageVo.getOrderId().trim().equals("")){
			StPurOrder stPurOrder=stPurOrderDAO.get(" from StPurOrder spo where spo.orderId='"+stStorageVo.getOrderId().trim()+"'");
			stPurOrder.setTransitToState("到达");
			stPurOrderDAO.merge(stPurOrder);//修改采购单在途状态为到达
		}
		
		if(list!=null&&list.size()>0){
			for (StStorageVo ssVo:list) {
				ssVo.setEnterpriseId(stStorageVo.getEnterpriseId());
				StStorageItem sgsi = new StStorageItem();
				sgsi.setStGoodsStorage(sgs);
				sgsi=copyDetailProperty(sgsi, ssVo);
				stStorageItemDAO.save(sgsi);//保存入库明细信息
				//if(stStorageVo.getAddpriceRate()!=null&&!stStorageVo.getAddpriceRate().trim().equals(""))//入库加价率选择的话，仓库价格变动
			    changPrice(stStorageVo,sgsi);
			}
		}
	}
	
	/**
	 * 入库单汇总      删除
	 */
	@Log(moduleName = "入库单管理", opertype = "删除入库单", content = "入库单管理-->删除入库单")
	public void delete(StStorageVo stStorageVo)throws Exception{
    	StGoodsStorage stGoodsStorage = stGoodsStorageDAO.get(" from StGoodsStorage sgs where sgs.storageId='"+stStorageVo.getStorageId()+"'");
    	List<StStorageItem> resultList=stStorageItemDAO.find("from StStorageItem stStorageItem where stStorageItem.stGoodsStorage.storageId='"+stStorageVo.getStorageId()+"'");
    	if(resultList!=null&&resultList.size()>0){
    		for (StStorageItem stStorageItem : resultList) {
    			stStorageItemDAO.delete(stStorageItem);
	    		PartsChangePrice pcp=null;
	    		if(stStorageItem.getFrtParts().getPartsId()!=null&&!"".equals(stStorageItem.getFrtParts().getPartsId())){
	    			if(stGoodsStorage!=null&&!stGoodsStorage.equals("")){
	    				pcp=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.enterpriseId="+stStorageVo.getEnterpriseId()+" and pcp.partsId='"+stStorageItem.getFrtParts().getPartsId()+"' and pcp.storeId="+stGoodsStorage.getStoreId());
		    			if(pcp!=null&&!pcp.equals("")){
		    		    	double partscount=pcp.getPartsNowCount()-stStorageItem.getPartsCount();//计算配件总数量
		    		    	if(partscount>=0)
		    		    		pcp.setPartsNowCount(partscount);
		    		    	else//如果'partscount'小于零，表示该入库明细配件有出库存在，一部分配件已经出库，导致系统库存量小于当前入库数量
		    		    		pcp.setPartsNowCount(0.0);
		    				partsChangePriceDAO.merge(pcp);
		    		    }
	    			}
	    		}
			}
    	}
    	if(stGoodsStorage!=null&&!stGoodsStorage.equals(""))
    		stGoodsStorageDAO.delete(stGoodsStorage);
    }
	
	/**
	 * 价格变动
	 */
	public void changPrice(StStorageVo stStorageVo,StStorageItem sgsi)throws Exception{
		double taxPrice=sgsi.getTaxPrice();
		PartsChangePrice pcpm=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.enterpriseId="+stStorageVo.getEnterpriseId()+"  and pcp.storeId="+stStorageVo.getStoreId()+" and pcp.partsId='"+sgsi.getFrtParts().getPartsId()+"'");
		//获取梯度加价率对象
		StageAddprice stageAddprice=stageAddpriceDao.get(" from StageAddprice sa where sa.enterpriseId="+stStorageVo.getEnterpriseId()+" and sa.startAmont <= "+taxPrice+" and sa.endAmont > "+taxPrice);
		//获取默认加价率对象
		BasPartsType basPartsType=basPartsTypeDAO.get(" from BasPartsType bpt where bpt.enterpriseId="+stStorageVo.getEnterpriseId()+"  and bpt.ptypeId="+sgsi.getFrtParts().getBasPartsType().getPtypeId()); 
		//判断配件信息是否存在于配件调价表中
		if(pcpm==null){//不存在  ，新增配件调价信息
			PartsChangePrice pcp=new PartsChangePrice(); //配件调价表 临时创建ID
			if(stStorageVo.getAddpriceRate().equals(Contstants.ADDPRICERATE.GRADADDPRICERATE)){//是剃度加价率   
				if(stageAddprice!=null&&!stageAddprice.equals("")){
					pcp.setPartsRepairPrice(taxPrice*stageAddprice.getRepairRate());
					pcp.setPartsSellPrice(taxPrice*stageAddprice.getSellRate());
					pcp.setPartsPointPrice(taxPrice*stageAddprice.getPointRate());
					pcp.setPartsSpecialPrice(taxPrice*stageAddprice.getSpecialRate());
					pcp.setPartsClaimantPrice(taxPrice*stageAddprice.getClaimRate());
				}
			}else if(stStorageVo.getAddpriceRate().equals(Contstants.ADDPRICERATE.TYPEADDPRICERATE)){ //是默认型号加价率
			    if(basPartsType!=null&&!basPartsType.equals("")){
			    	pcp.setPartsRepairPrice(taxPrice*basPartsType.getRepairRate());
					pcp.setPartsSellPrice(taxPrice*basPartsType.getSellRate());
					pcp.setPartsPointPrice(taxPrice*basPartsType.getPointRate());
					pcp.setPartsSpecialPrice(taxPrice*basPartsType.getSpecialRate());
					pcp.setPartsClaimantPrice(taxPrice*basPartsType.getClaimRate());
			    }
			}else{//不选择加价率，默认值为1
				pcp.setPartsRepairPrice(taxPrice);
				pcp.setPartsSellPrice(taxPrice);
				pcp.setPartsPointPrice(taxPrice);
				pcp.setPartsSpecialPrice(taxPrice);
				pcp.setPartsClaimantPrice(taxPrice);
			}
			pcp.setStoreId(Short.parseShort(stStorageVo.getStoreId()));
			pcp.setPartsId(sgsi.getFrtParts().getPartsId());
			pcp.setPartsLatestNotaxprice(sgsi.getNotaxPrice());
			pcp.setPartsLatestTaxprice(sgsi.getTaxPrice());
			pcp.setPartsNowCount(sgsi.getPartsCount());
			pcp.setEnterpriseId(stStorageVo.getEnterpriseId());
			partsChangePriceDAO.save(pcp);
		}else{//存在  ，修改配件调价信息
			if(stStorageVo.getAddpriceRate().equals(Contstants.ADDPRICERATE.GRADADDPRICERATE)){//300是剃度加价率   
			  if(stageAddprice!=null&&!stageAddprice.equals("")){
				double newPartsRepairPrice=taxPrice*stageAddprice.getRepairRate();
				double newPartsSellPrice=taxPrice*stageAddprice.getSellRate();
				double newPartsPointPrice=taxPrice*stageAddprice.getPointRate();
				double newPartsSpecialPrice=taxPrice*stageAddprice.getSpecialRate();
				double newClaimantPrice=taxPrice*stageAddprice.getClaimRate();
				//变高不变低
				if(pcpm.getPartsRepairPrice()!=null&&!pcpm.getPartsRepairPrice().equals("")){
					if(pcpm.getPartsRepairPrice()<newPartsRepairPrice)
						pcpm.setPartsRepairPrice(newPartsRepairPrice);
				}
				if(pcpm.getPartsRepairPrice()!=null&&!pcpm.getPartsRepairPrice().equals("")){
					if(pcpm.getPartsSellPrice()<newPartsSellPrice)
						pcpm.setPartsSellPrice(newPartsSellPrice);
				}
				if(pcpm.getPartsRepairPrice()!=null&&!pcpm.getPartsRepairPrice().equals("")){
					if(pcpm.getPartsPointPrice()<newPartsPointPrice)
						pcpm.setPartsPointPrice(newPartsPointPrice);
				}
				if(pcpm.getPartsRepairPrice()!=null&&!pcpm.getPartsRepairPrice().equals("")){
					if(pcpm.getPartsSpecialPrice()<newPartsSpecialPrice)
						pcpm.setPartsSpecialPrice(newPartsSpecialPrice);
				}
				if(pcpm.getPartsRepairPrice()!=null&&!pcpm.getPartsRepairPrice().equals("")){
					if(pcpm.getPartsClaimantPrice()<newClaimantPrice)
						pcpm.setPartsClaimantPrice(newClaimantPrice);
				}
			  }
			}else if(stStorageVo.getAddpriceRate().equals(Contstants.ADDPRICERATE.TYPEADDPRICERATE)){ //301是默认加价率
		      if(basPartsType!=null&&!basPartsType.equals("")){
				double newPartsRepairPrice=taxPrice*basPartsType.getRepairRate();
				double newPartsSellPrice=taxPrice*basPartsType.getSellRate();
				double newPartsPointPrice=taxPrice*basPartsType.getPointRate();
				double newPartsSpecialPrice=taxPrice*basPartsType.getSpecialRate();
				double newClaimantPrice=taxPrice*basPartsType.getClaimRate();
				//变高不变低
				if(pcpm.getPartsRepairPrice()!=null&&!pcpm.getPartsRepairPrice().equals("")){
					if(pcpm.getPartsRepairPrice()<newPartsRepairPrice)
						pcpm.setPartsRepairPrice(newPartsRepairPrice);
				}
				if(pcpm.getPartsRepairPrice()!=null&&!pcpm.getPartsRepairPrice().equals("")){
					if(pcpm.getPartsSellPrice()<newPartsSellPrice)
						pcpm.setPartsSellPrice(newPartsSellPrice);
				}
				if(pcpm.getPartsRepairPrice()!=null&&!pcpm.getPartsRepairPrice().equals("")){
					if(pcpm.getPartsPointPrice()<newPartsPointPrice)
						pcpm.setPartsPointPrice(newPartsPointPrice);
				}
				if(pcpm.getPartsRepairPrice()!=null&&!pcpm.getPartsRepairPrice().equals("")){
					if(pcpm.getPartsSpecialPrice()<newPartsSpecialPrice)
						pcpm.setPartsSpecialPrice(newPartsSpecialPrice);
				}
				if(pcpm.getPartsRepairPrice()!=null&&!pcpm.getPartsRepairPrice().equals("")){
					if(pcpm.getPartsClaimantPrice()<newClaimantPrice)
						pcpm.setPartsClaimantPrice(newClaimantPrice);
				}
		      }
			}
			pcpm.setPartsLatestTaxprice(((pcpm.getPartsLatestTaxprice()*pcpm.getPartsNowCount())+(sgsi.getTaxPrice()*sgsi.getPartsCount()))/(pcpm.getPartsNowCount()+sgsi.getPartsCount()));
			pcpm.setPartsLatestNotaxprice((pcpm.getPartsLatestNotaxprice()*pcpm.getPartsNowCount()+sgsi.getNotaxPrice()*sgsi.getPartsCount())/(pcpm.getPartsNowCount()+sgsi.getPartsCount()));
			pcpm.setPartsNowCount(pcpm.getPartsNowCount()+sgsi.getPartsCount());
			partsChangePriceDAO.merge(pcpm);
		}
	}
	
	/**
	 * 汇总信息获取
	 */
	public StGoodsStorage copyMainProperty(StGoodsStorage sgs,StStorageVo sgsvo) throws Exception{
		if(sgsvo.getOrderId()!=null&&!sgsvo.getOrderId().trim().equals(""))
			sgs.setCerNo(sgsvo.getOrderId().trim());                             // 获取原凭证号（采购单号）
		
		if(sgsvo.getStorageDate()!=null&&!sgsvo.getStorageDate().trim().equals(""))
			sgs.setStorageDate(Utils.dateFormat(sgsvo.getStorageDate().trim())); 
		
		if(sgsvo.getStoreId()!=null&&!sgsvo.getStoreId().trim().equals(""))// 获取入库日期
			sgs.setStoreId(Short.parseShort(sgsvo.getStoreId().trim()));         //获取仓库名称
		
		if(sgsvo.getTotalNum()!=null&&!sgsvo.getTotalNum().trim().equals(""))
			sgs.setTotalNum(Double.parseDouble(sgsvo.getTotalNum().trim()));       // 获取数量
		
		if(sgsvo.getTotalAmount()!=null&&!sgsvo.getTotalAmount().trim().equals(""))
			sgs.setTotalAmount((Double.parseDouble(sgsvo.getTotalAmount().trim()))); // 获取含税金额
		
		if(sgsvo.getRelcampId()!=null&&!sgsvo.getRelcampId().trim().equals(""))
			sgs.setRelcampId(Short.parseShort(sgsvo.getRelcampId().trim()));     // 获取供应商编号
		
		if(sgsvo.getPaid()!=null&&!sgsvo.getPaid().trim().equals(""))
			sgs.setPaid(sgsvo.getPaid());                                // 获取付讫 
		
		if(sgsvo.getTaxRate()!=null&&!sgsvo.getTaxRate().trim().equals(""))
			sgs.setTaxRate(Double.parseDouble(sgsvo.getTaxRate().trim()));       // 获取税率
		
		if(sgsvo.getBill()!=null&&!sgsvo.getBill().trim().equals(""))
			sgs.setBill(sgsvo.getBill().trim());                                 // 获取发票号
		
		if(sgsvo.getIdentifyman()!=null&&!sgsvo.getIdentifyman().trim().equals(""))
			sgs.setIdentifyman(sgsvo.getIdentifyman().trim());                   // 获取验证人
		
		if(sgsvo.getRemark()!=null&&!sgsvo.getRemark().trim().equals(""))
			sgs.setRemark(sgsvo.getRemark().trim());                             // 获取备注
		
		if(sgsvo.getNotaxTotalamont()!=null&&!sgsvo.getNotaxTotalamont().trim().equals(""))
			sgs.setNotaxtotalamont(Double.parseDouble(sgsvo.getNotaxTotalamont().trim()));// 获取未税金额
		
		if(sgsvo.getTaxCount()!=null&&!sgsvo.getTaxCount().trim().equals(""))
			sgs.setTaxcount(Double.parseDouble(sgsvo.getTaxCount().trim()));     // 获取税额
		
		if(sgsvo.getManager()!=null&&!sgsvo.getManager().trim().equals(""))
			sgs.setManager(Short.parseShort(sgsvo.getManager().trim()+""));                             //获取经办人     临时
		
		if(sgsvo.getAddpriceRate()!=null&&!sgsvo.getAddpriceRate().trim().equals(""))
			sgs.setAddpriceRate(sgsvo.getAddpriceRate());
		sgs.setEnterpriseId(sgsvo.getEnterpriseId());
		return sgs;
	}
	
	/**
	 * 明细信息获取
	 */
	public StStorageItem copyDetailProperty(StStorageItem sgsi,StStorageVo ssVo) throws Exception{
		FrtParts fp = frtPartsDAO.get(" from FrtParts fp where fp.enterpriseId="+ssVo.getEnterpriseId()+" and fp.partsId='"+ssVo.getPartsId()+"'");
		if(fp!=null)
		   sgsi.setFrtParts(fp);
		if(ssVo.getNotaxPrice()!=null&&!ssVo.getNotaxPrice().equals(""))
		   sgsi.setNotaxPrice(Double.parseDouble(ssVo.getNotaxPrice()));
		if(ssVo.getTaxPrice()!=null&&!ssVo.getTaxPrice().equals(""))
		   sgsi.setTaxPrice(Double.parseDouble(ssVo.getTaxPrice()));
		if(ssVo.getNotaxTotalamont()!=null&&!ssVo.getNotaxTotalamont().equals(""))
		   sgsi.setNotaxTotalamont(Double.parseDouble(ssVo.getNotaxTotalamont()));
		if(ssVo.getTaxTotalamont()!=null&&!ssVo.getTaxTotalamont().equals(""))
		   sgsi.setTaxTotalamont(Double.parseDouble(ssVo.getTaxTotalamont()));
		if(ssVo.getPartsNum()!=null&&!ssVo.getPartsNum().equals(""))
		   sgsi.setPartsCount(Double.parseDouble(ssVo.getPartsNum()));
		return sgsi;
	}
	
	
	/**
	 * 根据入库单ID得到下一条入库单记录
	 */
	public StStorageVo findByStorageId(StStorageVo stStorageVo)
			throws Exception {
		List<StGoodsStorage> list = stGoodsStorageDAO.findBystorageId(stStorageVo.getStorageId()); // 获取入库单对象
		StStorageVo svo = new StStorageVo();
		if(list.size()>0){
			svo.setStorageDate(list.get(0).getStorageDate() + ""); // 获取入库日期
			svo.setStorageId(list.get(0).getStorageId()); // 获取入库单号
			svo.setOrderId(list.get(0).getCerNo()); // 获取订单号
			StPurOrder stPurOrder = stPurOrderDAO.get("from StPurOrder spo where spo.orderId='"+list.get(0).getCerNo()+"'"); // 通过订单号得到订单对象
			BasRelationCampany basRelationCampany = basRelationCampanyDAO.get(" from BasRelationCampany brc where brc.enterpriseId="+stStorageVo.getEnterpriseId()+" and brc.relcampId="+stPurOrder.getBasRelationCampany().getRelcampId());// 通过订单对象获取相关单位对象
			svo.setRelcampId(basRelationCampany.getRelcampId() + ""); // 获取相关单位编号
			svo.setRelcampName(basRelationCampany.getRelcampName()); // 获取相关单位名称
			BasStuff bsf=basStuffDao.get(" from BasStuff bsf where bsf.stfId="+stPurOrder.getBuyer());
			svo.setBuyerName(bsf.getStfName());
			svo.setBuyer(stPurOrder.getBuyer()+""); //获取采购员 
	        BasStuff basStuff1=basStuffDao.get(" from BasStuff bsf where bsf.stfId="+stPurOrder.getManager());
	        svo.setManager(basStuff1.getStfName());//获取经办人
	        svo.setManager(stPurOrder.getManager()+"");
	        svo.setTotalAmount(stPurOrder.getTotalAmount() + ""); //获取采购额
	        svo.setTaxRate(list.get(0).getTaxRate() + ""); //获取税率
	        svo.setNotesType(stPurOrder.getNotesType()); //获取票据类型
	        svo.setBill(list.get(0).getBill()); //获取发票号
	        svo.setIdentifyman(list.get(0).getIdentifyman()); //获取验证人
			BasStorehouse basStorehouse=basStorehouseDAO.findById(list.get(0).getStoreId());
			svo.setStoreName(basStorehouse.getStoreName());//获取仓库名称
			svo.setRemark(list.get(0).getRemark()); // 获取备注
			svo.setTotalNum(list.get(0).getTotalNum()+""); // 获取数量
		}
		return svo;
	}

	/**
	 * 根据入库单ID得到上一条入库单记录
	 */
	public StStorageVo findByStorageId1(StStorageVo stStorageVo)
			throws Exception {
		List<StGoodsStorage> list = stGoodsStorageDAO.findBystorageId1(stStorageVo
				.getStorageId()); // 获取入库单对象
		StStorageVo svo = new StStorageVo();
		if(list.size()>0)
		{
			svo.setStorageDate(list.get(0).getStorageDate() + ""); // 获取入库日期
			svo.setStorageId(list.get(0).getStorageId()); // 获取入库单号
			svo.setOrderId(list.get(0).getCerNo()); // 获取订单号
			StPurOrder stPurOrder = stPurOrderDAO.get(" from StPurOrder spo where spo.orderId='"+list.get(0).getCerNo()+"'"); // 通过订单号得到订单对象
			BasRelationCampany basRelationCampany = basRelationCampanyDAO.get(" from BasRelationCampany brc where brc.relcampId="+stPurOrder.getBasRelationCampany().getRelcampId());// 通过订单对象获取相关单位对象
			svo.setRelcampId(basRelationCampany.getRelcampId() + ""); // 获取相关单位编号
			svo.setRelcampName(basRelationCampany.getRelcampName()); // 获取相关单位名称
			BasStuff basStuff=basStuffDao.get("  from BasStuff bsf where bsf.stfId="+stPurOrder.getBuyer());
			svo.setBuyerName(basStuff.getStfName());
			svo.setBuyer(stPurOrder.getBuyer()+""); // 获取采购员 
	        BasStuff basStuff1=basStuffDao.get(" from BasStuff bsf where bsf.stfId="+stPurOrder.getManager());
	        svo.setManager(basStuff1.getStfName());// 获取经办人
	        svo.setManager(stPurOrder.getManager()+"");
	        svo.setTotalAmount(stPurOrder.getTotalAmount() + ""); // 获取采购额
	        svo.setTaxRate(list.get(0).getTaxRate() + ""); // 获取税率
	        svo.setNotesType(stPurOrder.getNotesType()); // 获取票据类型
	        svo.setBill(list.get(0).getBill()); // 获取发票号
	        svo.setIdentifyman(list.get(0).getIdentifyman()); // 获取验证人
			BasStorehouse basStorehouse=basStorehouseDAO.findById(list.get(0).getStoreId());
			svo.setStoreName(basStorehouse.getStoreName());//获取仓库名称
			svo.setRemark(list.get(0).getRemark()); // 获取备注
			svo.setTotalNum(list.get(0).getTotalNum() + ""); // 获取数量
		}
		return svo;
	}

}
