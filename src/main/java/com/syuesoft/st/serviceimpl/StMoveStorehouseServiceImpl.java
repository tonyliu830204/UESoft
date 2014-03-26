package com.syuesoft.st.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.dao.BasPartsUnitDAO;
import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.serviceimpl.BaseServiceImpl;
import com.syuesoft.model.BasStorehouse;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.model.StMoveStorehouseDetail;
import com.syuesoft.model.StMoveStorehouseMain;
import com.syuesoft.st.dao.FrtPartsDAO;
import com.syuesoft.st.dao.PartsChangePriceDAO;
import com.syuesoft.st.dao.StMoveStorehouseDetailDAO;
import com.syuesoft.st.dao.StMoveStorehouseMainDAO;
import com.syuesoft.st.service.StMoveStorehouseService;
import com.syuesoft.st.vo.StMoveStorehouseVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;
/**
 * 移仓单Service实现类
 * @author SuMing
 */
@Service("stMoveStorehouseService")
public class StMoveStorehouseServiceImpl extends BaseServiceImpl implements StMoveStorehouseService {

	@Autowired BasStuffDao basStuffDao;  
	@Autowired FrtPartsDAO frtPartsDAO;
	@Autowired PartsChangePriceDAO partsChangePriceDAO;
	@Autowired BasStorehouseDAO basStorehouseDAO;
	@Autowired StMoveStorehouseDetailDAO stMoveStorehouseDetailDAO;
	@Autowired StMoveStorehouseMainDAO stMoveStorehouseMainDAO;
	@Autowired BasPartsUnitDAO basPartsUnitDAO;
	
	/**
	 * 移仓调拨单         反序列化明细JSON数据
	 */
	public List<StMoveStorehouseVo> acceptChangesSelectedItem(StMoveStorehouseVo stMoveStorehouseVo) {
		if(stMoveStorehouseVo.getInserted() != null && !"".equals(stMoveStorehouseVo.getInserted().trim())){
			List<StMoveStorehouseVo> insertedList = JSON.parseArray(stMoveStorehouseVo.getInserted(), StMoveStorehouseVo.class);
			if(insertedList.size() > 0){
				return insertedList;
			}
		}
		if(stMoveStorehouseVo.getDeleted() != null && !"".equals(stMoveStorehouseVo.getDeleted().trim())){
			List<StMoveStorehouseVo> deletedList = JSON.parseArray(stMoveStorehouseVo.getDeleted(), StMoveStorehouseVo.class);
			if(deletedList.size() > 0){
				return deletedList;
			}
		}
		if(stMoveStorehouseVo.getUpdated() != null && !"".equals(stMoveStorehouseVo.getUpdated().trim())){
			List<StMoveStorehouseVo> updatedList = JSON.parseArray(stMoveStorehouseVo.getUpdated(), StMoveStorehouseVo.class);
			if(updatedList.size() > 0){
				return updatedList;
			}
		}
		return null;
	}
	
	/**
	 * 移仓调拨单汇总      综合查询
	 */
	public Json searchByCondition(StMoveStorehouseVo stMoveStorehouseVo)throws Exception
	{
		return stMoveStorehouseMainDAO.searchByCondition(stMoveStorehouseVo.getPage(),stMoveStorehouseVo.getRows(),stMoveStorehouseVo.getSort(),stMoveStorehouseVo.getOrder(),stMoveStorehouseVo.getMsdmDateStart(),stMoveStorehouseVo.getMsdmDateEnd(),stMoveStorehouseVo.getMsdmManagerName(),stMoveStorehouseVo.getMsdmId(),stMoveStorehouseVo.getMsdmRemark(),stMoveStorehouseVo.getEnterpriseId());
	}
	
	/**
	 *  移仓调拨单汇总      预加载
	 */
	public Json loadUnExamineStMoveStorehouse(StMoveStorehouseVo stMoveStorehouseVo)throws Exception
	{
		return stMoveStorehouseMainDAO.loadUnExamineStMoveStorehouse(stMoveStorehouseVo.getPage(),stMoveStorehouseVo.getRows(),stMoveStorehouseVo.getSort(),stMoveStorehouseVo.getOrder(),stMoveStorehouseVo.getEnterpriseId());
	}
	
	/**
	 * 移仓调拨单汇总      综合查询
	 */
	public Json searchUnExamineByCondition(StMoveStorehouseVo stMoveStorehouseVo)throws Exception
	{
		return stMoveStorehouseMainDAO.searchUnExamineByCondition(stMoveStorehouseVo.getMsdmDateStart(),stMoveStorehouseVo.getMsdmDateEnd(),stMoveStorehouseVo.getMsdmManagerName(),stMoveStorehouseVo.getMsdmId(),stMoveStorehouseVo.getMsdmRemark(),stMoveStorehouseVo.getEnterpriseId());
	}
	
	/**
	 *  通过移仓单单号    加载移仓单明细信息
	 */
	public List<StMoveStorehouseVo> searchStMoveStorehouseDetailByMsdmId(StMoveStorehouseVo stMoveStorehouseVo) throws Exception {
		return stMoveStorehouseDetailDAO.searchStMoveStorehouseDetailByMsdmId(stMoveStorehouseVo.getMsdmId());
	}

	/**
	 * 根据移仓单单号获取 移出仓配件明细信息
	 */
	public List<StMoveStorehouseVo> loadMoveOutPartsDetail(StMoveStorehouseVo smsvo)throws Exception{
		List<StMoveStorehouseVo> list=new ArrayList<StMoveStorehouseVo>();
		StMoveStorehouseMain stMoveStorehouseMain = stMoveStorehouseMainDAO.get("from StMoveStorehouseMain smsm where smsm.msdmId='"+smsvo.getMsdmId()+"'");
		if(stMoveStorehouseMain!=null&&!stMoveStorehouseMain.equals("")){
			List<StMoveStorehouseDetail> stMoveStorehouseDetail=stMoveStorehouseDetailDAO.find(" from StMoveStorehouseDetail smsd where smsd.stMoveStorehouseMain.msdmId='"+smsvo.getMsdmId()+"'");
			if(stMoveStorehouseDetail!=null&&stMoveStorehouseDetail.size()>0){
				for (StMoveStorehouseDetail smsmd : stMoveStorehouseDetail) {
					StMoveStorehouseVo stMoveStorehouseVo=new StMoveStorehouseVo();
					BasStorehouse bs=basStorehouseDAO.get(" from BasStorehouse basStorehouse where basStorehouse.storeId="+stMoveStorehouseMain.getMsdmOutStorehouseId());
					if(bs!=null&&!bs.equals("")){
						stMoveStorehouseVo.setStoreId(bs.getStoreId()+"");
						stMoveStorehouseVo.setStoreName(bs.getStoreName());
					}
					PartsChangePrice pcp=partsChangePriceDAO.get(" from PartsChangePrice p where p.storeId="+stMoveStorehouseMain.getMsdmOutStorehouseId()+" and p.partsId='"+smsmd.getMsdPartsid()+"'");
					if(pcp!=null&&!pcp.equals("")){
						stMoveStorehouseVo.setPartsId(smsmd.getMsdPartsid());
						stMoveStorehouseVo.setTaxprice(pcp.getPartsLatestTaxprice()+"");
						stMoveStorehouseVo.setNotaxprice(pcp.getPartsLatestNotaxprice()+"");
						stMoveStorehouseVo.setPartsNowCount(pcp.getPartsNowCount()+"");
					}else{
						stMoveStorehouseVo.setPartsId(smsmd.getMsdPartsid());
						stMoveStorehouseVo.setTaxprice(0.0+"");
						stMoveStorehouseVo.setNotaxprice(0.0+"");
						stMoveStorehouseVo.setPartsNowCount(0+"");
					}
					list.add(stMoveStorehouseVo);
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据移仓单单号获取 移入仓配件明细信息
	 */
	public List<StMoveStorehouseVo> loadMoveInPartsDetail(StMoveStorehouseVo smsvo)throws Exception{
		List<StMoveStorehouseVo> list=new ArrayList<StMoveStorehouseVo>();
		StMoveStorehouseMain stMoveStorehouseMain = stMoveStorehouseMainDAO.get("from StMoveStorehouseMain smsm where smsm.msdmId='"+smsvo.getMsdmId()+"'");
		if(stMoveStorehouseMain!=null&&!stMoveStorehouseMain.equals("")){
			List<StMoveStorehouseDetail> stMoveStorehouseDetail=stMoveStorehouseDetailDAO.find(" from StMoveStorehouseDetail smsd where smsd.stMoveStorehouseMain.msdmId='"+smsvo.getMsdmId()+"'");
			if(stMoveStorehouseDetail!=null&&stMoveStorehouseDetail.size()>0){
				for (StMoveStorehouseDetail smsmd : stMoveStorehouseDetail) {
					StMoveStorehouseVo stMoveStorehouseVo=new StMoveStorehouseVo();
					BasStorehouse bs=basStorehouseDAO.get(" from BasStorehouse basStorehouse where basStorehouse.storeId="+stMoveStorehouseMain.getMsdmInStorehouseId());
					if(bs!=null&&!bs.equals("")){
						stMoveStorehouseVo.setStoreId(bs.getStoreId()+"");
						stMoveStorehouseVo.setStoreName(bs.getStoreName());
					}
					PartsChangePrice pcp=partsChangePriceDAO.get(" from PartsChangePrice p where p.storeId="+stMoveStorehouseMain.getMsdmInStorehouseId()+" and p.partsId='"+smsmd.getMsdPartsid()+"'");
					if(pcp!=null&&!pcp.equals("")){
						stMoveStorehouseVo.setPartsId(smsmd.getMsdPartsid());
						stMoveStorehouseVo.setTaxprice(pcp.getPartsLatestTaxprice()+"");
						stMoveStorehouseVo.setNotaxprice(pcp.getPartsLatestNotaxprice()+"");
						stMoveStorehouseVo.setPartsNowCount(pcp.getPartsNowCount()+"");
					}else{
						stMoveStorehouseVo.setPartsId(smsmd.getMsdPartsid());
						stMoveStorehouseVo.setTaxprice(0.0+"");
						stMoveStorehouseVo.setNotaxprice(0.0+"");
						stMoveStorehouseVo.setPartsNowCount(0+"");
					}
					list.add(stMoveStorehouseVo);
				}
			}
		}
		return list;
	}
	
	/**
	 * 移仓单汇总信息    审核
	 */
	public void updateExamine(StMoveStorehouseVo smsvo)throws Exception{
		StMoveStorehouseMain stMoveStorehouseMain = stMoveStorehouseMainDAO.get("from StMoveStorehouseMain smsm where smsm.msdmId='"+smsvo.getMsdmId()+"'");
		if(smsvo.getExamine()!=null && !("".equals(smsvo.getExamine()))){
			if(smsvo.getExamine().indexOf("未审核")!=-1)
				stMoveStorehouseMain.setExamineState(Short.parseShort(1+""));
			else
				stMoveStorehouseMain.setExamineState(Short.parseShort(0+""));
		}
		stMoveStorehouseMainDAO.merge(stMoveStorehouseMain);
		if(stMoveStorehouseMain.getExamineState()==1)
			examinChange(smsvo,stMoveStorehouseMain);
		else
			unExaminChange(smsvo,stMoveStorehouseMain);
	}
	
	/**
	 * 审核配件调价变动
	 */
	public void examinChange(StMoveStorehouseVo smsvo,StMoveStorehouseMain stMoveStorehouseMain) throws Exception{
		List<StMoveStorehouseDetail> stMoveStorehouseDetail=stMoveStorehouseDetailDAO.find(" from StMoveStorehouseDetail smsd where smsd.stMoveStorehouseMain.msdmId='"+smsvo.getMsdmId()+"'");
		if(stMoveStorehouseDetail!=null&&stMoveStorehouseDetail.size()>0){
			for (StMoveStorehouseDetail smsd : stMoveStorehouseDetail) {
				//修改配件调价表     移出仓配件现有库存量信息
				PartsChangePrice outpcp = partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.partsId='"+smsd.getMsdPartsid()+"' and pcp.storeId="+stMoveStorehouseMain.getMsdmOutStorehouseId());
				if(outpcp!=null&&!outpcp.equals("")){
					outpcp.setPartsNowCount(outpcp.getPartsNowCount()- smsd.getMsdCnt());
					partsChangePriceDAO.merge(outpcp);
				}
				
				//修改配件调价表     移入仓配件现有库存量信息
				PartsChangePrice inpcp = partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.partsId='"+smsd.getMsdPartsid()+"' and pcp.storeId="+ stMoveStorehouseMain.getMsdmInStorehouseId());
				if (inpcp != null){// 配件信息在新仓库中存在 修改
					// 修改配件最新未税成本
					double newpartsLatestNotaxprice = ((smsd.getMsdNocast() * smsd.getMsdCnt()) + (inpcp.getPartsLatestNotaxprice() * inpcp.getPartsNowCount()))/ (smsd.getMsdCnt() + inpcp.getPartsNowCount());
					inpcp.setPartsLatestNotaxprice(new BaseAction().doubleFormat(newpartsLatestNotaxprice));
					// 修改配件最新含税价
					double newpartsLatestTaxprice = ((smsd.getMsdNocast()*1.17 * smsd.getMsdCnt()) + ( inpcp.getPartsLatestTaxprice() * inpcp.getPartsNowCount()))/ (smsd.getMsdCnt() + inpcp.getPartsNowCount());
					inpcp.setPartsLatestNotaxprice(new BaseAction().doubleFormat(newpartsLatestTaxprice));
					inpcp.setPartsNowCount(inpcp.getPartsNowCount()+ smsd.getMsdCnt());
					partsChangePriceDAO.merge(inpcp);
				} else{
					// 配件信息在新仓库中不存在 新增
					PartsChangePrice newpcp = new PartsChangePrice();
					newpcp.setStoreId(stMoveStorehouseMain.getMsdmInStorehouseId());
					newpcp.setPartsId(smsd.getMsdPartsid());
					if(smsd.getMsdNocast()!=null&&!smsd.getMsdNocast().equals(""))
					newpcp.setPartsLatestNotaxprice(smsd.getMsdNocast());
					newpcp.setPartsLatestTaxprice(smsd.getMsdNocast()* 1.17);
					newpcp.setPartsNowCount(smsd.getMsdCnt());
					newpcp.setPartsRepairPrice(outpcp.getPartsRepairPrice());
					newpcp.setPartsSellPrice(outpcp.getPartsSellPrice());
					newpcp.setPartsPointPrice(outpcp.getPartsPointPrice());
					newpcp.setPartsSpecialPrice(outpcp.getPartsSpecialPrice());
					newpcp.setPartsClaimantPrice(outpcp.getPartsClaimantPrice());
					newpcp.setEnterpriseId(outpcp.getEnterpriseId());
					partsChangePriceDAO.save(newpcp);
				}
			}
		}
	}
	
	/**
	 * 未审核配件调价变动
	 */
	public void unExaminChange(StMoveStorehouseVo smsvo,StMoveStorehouseMain stMoveStorehouseMain) throws Exception{
		List<StMoveStorehouseDetail> stMoveStorehouseDetail=stMoveStorehouseDetailDAO.find(" from StMoveStorehouseDetail smsd where smsd.stMoveStorehouseMain.msdmId='"+smsvo.getMsdmId()+"'");
		if(stMoveStorehouseDetail!=null&&stMoveStorehouseDetail.size()>0){
			for (StMoveStorehouseDetail smsd : stMoveStorehouseDetail) {
				//修改配件调价表     修改移入仓配件现有库存量信息
				PartsChangePrice partsChangePrice = partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.partsId='"+smsd.getMsdPartsid()+"' and pcp.storeId="+ stMoveStorehouseMain.getMsdmInStorehouseId());
				partsChangePrice.setPartsNowCount(partsChangePrice.getPartsNowCount()- smsd.getMsdCnt());
				partsChangePriceDAO.merge(partsChangePrice);
				
				//修改配件调价表    还原 移出仓配件现有库存量信息
				PartsChangePrice pcp = partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.partsId='"+smsd.getMsdPartsid()+"' and pcp.storeId="+stMoveStorehouseMain.getMsdmOutStorehouseId());
				if (pcp != null){// 配件信息在新仓库中存在 修改
					// 修改配件最新未税成本
					double newpartsLatestNotaxprice = ((smsd.getMsdNocast() * smsd.getMsdCnt()) + (pcp.getPartsLatestNotaxprice() * pcp.getPartsNowCount()))/ (smsd.getMsdCnt() + pcp.getPartsNowCount());
					pcp.setPartsLatestNotaxprice(new BaseAction().doubleFormat(newpartsLatestNotaxprice));
					// 修改配件最新含税价
					double newpartsLatestTaxprice = ((smsd.getMsdNocast()*1.17 * smsd.getMsdCnt()) + ( pcp.getPartsLatestTaxprice() * pcp.getPartsNowCount()))/ (smsd.getMsdCnt() + pcp.getPartsNowCount());
					pcp.setPartsLatestNotaxprice(new BaseAction().doubleFormat(newpartsLatestTaxprice));
					pcp.setPartsNowCount(pcp.getPartsNowCount()+ smsd.getMsdCnt());
					partsChangePriceDAO.merge(pcp);
				} else{
					// 配件信息在新仓库中不存在 新增
					PartsChangePrice newpcp = new PartsChangePrice();
					newpcp.setStoreId(stMoveStorehouseMain.getMsdmInStorehouseId());
					newpcp.setPartsId(smsd.getMsdPartsid());
					newpcp.setPartsLatestNotaxprice(smsd.getMsdNocast());
					newpcp.setPartsLatestTaxprice(smsd.getMsdNocast()* 1.17);
					newpcp.setPartsNowCount(smsd.getMsdCnt());
					newpcp.setPartsRepairPrice(partsChangePrice.getPartsRepairPrice());
					newpcp.setPartsSellPrice(partsChangePrice.getPartsSellPrice());
					newpcp.setPartsPointPrice(partsChangePrice.getPartsPointPrice());
					newpcp.setPartsSpecialPrice(partsChangePrice.getPartsSpecialPrice());
					newpcp.setPartsClaimantPrice(partsChangePrice.getPartsClaimantPrice());
					newpcp.setEnterpriseId(partsChangePrice.getEnterpriseId());
					partsChangePriceDAO.save(newpcp);
				}
				
			}
		}
	}
	
	/**
	 * 移仓单汇总，明细        添加
	 */
	@Log(moduleName = "移仓单管理", opertype = "新增移仓单", content = "移仓单管理-->新增移仓单")
	public void add(StMoveStorehouseVo stMoveStorehouseVo,List<StMoveStorehouseVo> list) throws Exception {
		StMoveStorehouseMain stMoveStorehouseMain = new StMoveStorehouseMain();
		stMoveStorehouseMain =copyMainProperty(stMoveStorehouseVo,stMoveStorehouseMain);
		stMoveStorehouseMain.setMsdmId(CreateID.createId("StMoveStorehouseMain", "YCD"));
		stMoveStorehouseMain.setExamineState(Short.parseShort("0"));
		stMoveStorehouseMain.setEnterpriseId(stMoveStorehouseVo.getEnterpriseId());
		stMoveStorehouseMainDAO.save(stMoveStorehouseMain);// 保存移仓单汇总信息
		
		for (int i = 0; i < list.size(); i++) {
			StMoveStorehouseDetail stMoveStorehouseDetail=copyDetailProperty(list.get(i));
			stMoveStorehouseDetail.setStMoveStorehouseMain(stMoveStorehouseMain);
			stMoveStorehouseDetailDAO.save(stMoveStorehouseDetail);// 保存移仓单明细信息
		}
	}
   
	/**
	 * 移仓单汇总 ，明细       删除
	 */
	@Log(moduleName = "移仓单管理", opertype = "删除移仓单", content = "移仓单管理-->删除移仓单")
	public void delete(StMoveStorehouseVo stMoveStorehouseVo)throws Exception {
		StMoveStorehouseMain stMoveStorehouseMain = stMoveStorehouseMainDAO.get(" from StMoveStorehouseMain smsm where smsm.msdmId='"+stMoveStorehouseVo.getMsdmId()+"'");
		List<StMoveStorehouseDetail> resultList = stMoveStorehouseDetailDAO.find(" from StMoveStorehouseDetail smsd where smsd.stMoveStorehouseMain.msdmId='"+stMoveStorehouseVo.getMsdmId()+"'");
		if(resultList!=null&&resultList.size()>0){
			for (StMoveStorehouseDetail stMoveStorehouseDetail:resultList) {
				stMoveStorehouseDetailDAO.delete(stMoveStorehouseDetail);//删除移仓单明细信息
				PartsChangePrice inPartsChangePrice = partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.partsId='"+stMoveStorehouseDetail.getMsdPartsid()+"' and pcp.storeId="+stMoveStorehouseMain.getMsdmInStorehouseId());
				double msdCnt = stMoveStorehouseDetail.getMsdCnt();//移仓数量
				if(inPartsChangePrice!=null&&!inPartsChangePrice.equals("")){
					double partNowCount=inPartsChangePrice.getPartsNowCount()-msdCnt;
					inPartsChangePrice.setPartsNowCount(partNowCount);
					partsChangePriceDAO.merge(inPartsChangePrice);
				}
				PartsChangePrice outPartsChangePrice = partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.partsId='"+stMoveStorehouseDetail.getMsdPartsid()+"' and pcp.storeId="+stMoveStorehouseMain.getMsdmOutStorehouseId());
				if (outPartsChangePrice!=null&&!outPartsChangePrice.equals("")){// 配件信息在新仓库中存在 修改
					double newNoTaxPrice = inPartsChangePrice.getPartsLatestNotaxprice();// 新进配件未税价
					double oldNoTaxPrice = outPartsChangePrice.getPartsLatestNotaxprice();// 原来仓库配件最新未税价
					double noTaxPrice = ((newNoTaxPrice * msdCnt) + (oldNoTaxPrice * outPartsChangePrice.getPartsNowCount()))/(msdCnt+outPartsChangePrice.getPartsNowCount());
					outPartsChangePrice.setPartsLatestNotaxprice(new BaseAction().doubleFormat(noTaxPrice));// 修改配件最新未税成本
					double taxPrice=noTaxPrice*1.17;
					outPartsChangePrice.setPartsLatestNotaxprice(new BaseAction().doubleFormat(taxPrice));// 修改配件最新含税价成本
					outPartsChangePrice.setPartsNowCount(outPartsChangePrice.getPartsNowCount()+ msdCnt);
					partsChangePriceDAO.merge(outPartsChangePrice);// 修改配件库存量
				} else{
					PartsChangePrice pcp = new PartsChangePrice();
					pcp.setStoreId(stMoveStorehouseMain.getMsdmInStorehouseId());
					pcp.setPartsId(stMoveStorehouseDetail.getMsdPartsid());
					pcp.setPartsLatestNotaxprice(stMoveStorehouseDetail.getMsdNocast());
					pcp.setPartsLatestTaxprice(stMoveStorehouseDetail.getMsdNocast()+ stMoveStorehouseDetail.getMsdNocast() * 0.17);
					pcp.setPartsNowCount(stMoveStorehouseDetail.getMsdCnt());
					partsChangePriceDAO.save(pcp);// 配件信息在新仓库中不存在 新增
				}
			}
		}
		stMoveStorehouseMainDAO.delete(stMoveStorehouseMain);// 删除移仓调拨单汇总信息
	}

	/**
	 * 移仓单汇总 ，明细     修改
	 */
	@Log(moduleName = "移仓单管理", opertype = "修改移仓单", content = "移仓单管理-->修改移仓单")
	public void update(StMoveStorehouseVo stMoveStorehouseVo,List<StMoveStorehouseVo> list)throws Exception
	{
		StMoveStorehouseMain stMoveStorehouseMain =stMoveStorehouseMainDAO.get(" from StMoveStorehouseMain smsm where smsm.msdmId='"+stMoveStorehouseVo.getMsdmId()+"'");
		stMoveStorehouseMain =copyMainProperty(stMoveStorehouseVo,stMoveStorehouseMain);
		stMoveStorehouseMainDAO.merge(stMoveStorehouseMain);
		List<StMoveStorehouseDetail> resultList = stMoveStorehouseDetailDAO.find(" from StMoveStorehouseDetail smsd where smsd.stMoveStorehouseMain.msdmId='"+stMoveStorehouseVo.getMsdmId()+"'");
		for (StMoveStorehouseDetail stMoveStorehouseDetail:resultList) {
			    stMoveStorehouseDetailDAO.delete(stMoveStorehouseDetail);
		}
		for (int i = 0; i < list.size(); i++) {
			StMoveStorehouseDetail stMoveStorehouseDetail =copyDetailProperty(list.get(i));
			stMoveStorehouseDetail.setStMoveStorehouseMain(stMoveStorehouseMain);
			stMoveStorehouseDetailDAO.save(stMoveStorehouseDetail);// 保存移仓单明细信息
		}
	}
	
	/**
	 * 汇总数据添加数据非空判断
	 */
	public StMoveStorehouseMain copyMainProperty(StMoveStorehouseVo stMoveStorehouseVo,StMoveStorehouseMain stMoveStorehouseMain ) throws Exception{
		if(stMoveStorehouseVo.getMsdmDate()!=null&&!stMoveStorehouseVo.getMsdmDate().trim().equals("")){
			stMoveStorehouseMain.setMsdmDate(Utils.dateFormat(stMoveStorehouseVo.getMsdmDate().trim()));
		}
		if(stMoveStorehouseVo.getMsdmManager()!=null&&!stMoveStorehouseVo.getMsdmManager().trim().equals("")){
			stMoveStorehouseMain.setMsdmManager(Short.parseShort(stMoveStorehouseVo.getMsdmManager().trim()+""));
		}
		if(stMoveStorehouseVo.getMsdmSumAmont()!=null&&!stMoveStorehouseVo.getMsdmSumAmont().trim().equals("")){
			stMoveStorehouseMain.setMsdmSumAmont(Double.parseDouble(stMoveStorehouseVo.getMsdmSumAmont().trim()));
		}
		if(stMoveStorehouseVo.getMsdmSumCnt()!=null&&!stMoveStorehouseVo.getMsdmSumCnt().trim().equals("")){
			stMoveStorehouseMain.setMsdmSumCnt(Double.parseDouble(stMoveStorehouseVo.getMsdmSumCnt().trim()));
		}
		if(stMoveStorehouseVo.getOutStoreId()!=null&&!stMoveStorehouseVo.getOutStoreId().trim().equals("")){
			stMoveStorehouseMain.setMsdmOutStorehouseId(Short.parseShort(stMoveStorehouseVo.getOutStoreId().trim()));
		}
		if(stMoveStorehouseVo.getInStoreId()!=null&&!stMoveStorehouseVo.getInStoreId().trim().equals("")){
			stMoveStorehouseMain.setMsdmInStorehouseId(Short.parseShort(stMoveStorehouseVo.getInStoreId().trim()));
		}
		if(stMoveStorehouseVo.getMsdmRemark()!=null&&!stMoveStorehouseVo.getMsdmRemark().trim().equals("")){
			stMoveStorehouseMain.setMsdmRemark(stMoveStorehouseVo.getMsdmRemark().trim());
		}
		return stMoveStorehouseMain;
	}
	
	/**
	 * 明细数据添加数据非空判断
	 */
	public StMoveStorehouseDetail copyDetailProperty(StMoveStorehouseVo stMoveStorehouseVo) throws Exception
	{
		StMoveStorehouseDetail stMoveStorehouseDetail = new StMoveStorehouseDetail();
		if (stMoveStorehouseVo.getPartsId() != null&& !stMoveStorehouseVo.getPartsId().trim().equals("")) {
			stMoveStorehouseDetail.setMsdPartsid(stMoveStorehouseVo.getPartsId().trim());
		}
		if (stMoveStorehouseVo.getMsdCnt() != null&& !stMoveStorehouseVo.getMsdCnt().trim().equals("")) {
			stMoveStorehouseDetail.setMsdCnt(Double.parseDouble(stMoveStorehouseVo.getMsdCnt().trim()));
		} 
		if (stMoveStorehouseVo.getNotaxCast() != null&& !stMoveStorehouseVo.getNotaxCast().trim().equals("")) {
			stMoveStorehouseDetail.setMsdNocast(Double.parseDouble(stMoveStorehouseVo.getNotaxCast().trim()));
		}
		if (stMoveStorehouseVo.getMsdNocastAmont() != null&& !stMoveStorehouseVo.getMsdNocastAmont().trim().equals("")) {
			stMoveStorehouseDetail.setMsdNocastAmont(Double.parseDouble(stMoveStorehouseVo.getMsdNocastAmont().trim()));
		}
		if (stMoveStorehouseVo.getMsdRemark() != null&& !stMoveStorehouseVo.getMsdRemark().trim().equals("")) {
			stMoveStorehouseDetail.setMsdRemark(stMoveStorehouseVo.getMsdRemark().trim());
		}
		return stMoveStorehouseDetail;
	}
	
}
