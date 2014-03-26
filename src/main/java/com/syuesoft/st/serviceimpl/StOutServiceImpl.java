package com.syuesoft.st.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasRepairTypeDao;
import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.bas.serviceimpl.BaseServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.dao.FrtReceptionDao;
import com.syuesoft.model.BasClaimsType;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.model.StOutItem;
import com.syuesoft.model.StOutMain;
import com.syuesoft.st.dao.BasClaimsTypeDAO;
import com.syuesoft.st.dao.FrtRcptPartsDAO;
import com.syuesoft.st.dao.PartsChangePriceDAO;
import com.syuesoft.st.dao.StOutInItemDAO;
import com.syuesoft.st.dao.StOutItemDAO;
import com.syuesoft.st.dao.StOutMainDAO;
import com.syuesoft.st.dao.StRtPartsMainDAO;
import com.syuesoft.st.dao.StStorageItemDAO;
import com.syuesoft.st.service.StOutService;
import com.syuesoft.st.vo.StOutVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;
/**
 * 出库单管理Service实现类
 * @author SuMing
 */
@Service("stOutService")
public class StOutServiceImpl extends BaseServiceImpl implements StOutService {
	
	@Autowired StOutMainDAO stOutMainDAO;                    //出库汇总DAO
	@Autowired PartsChangePriceDAO partsChangePriceDAO;      //配件调价表DAO
	@Autowired BasStorehouseDAO basStorehouseDAO;            //仓库表DAO
	@Autowired FrtReceptionDao frtReceptionDao;              //前台接车DAO
	@Autowired StOutItemDAO stOutItemDAO;                    //出库明细DAO
	@Autowired BasClaimsTypeDAO basClaimsTypeDAO;            //索赔分类DAO
	@Autowired FrtRcptPartsDAO frtRcptPartsDAO;              //前台接车配件表DAO               //收费性质DAO
	@Autowired BasRepairTypeDao basRepairTypeDao;
	@Autowired StOutInItemDAO stOutInItemDAO;
	@Autowired StStorageItemDAO stStorageItemDAO;
	@Autowired StRtPartsMainDAO stRtPartsMainDAO;
	
	/**
	 * 明细数据由JSON转换为List集合
	 */
	public List<StOutVo> jsonChangeDetailList(StOutVo stOutVo){
		List<StOutVo> list =new ArrayList<StOutVo>();
		if(stOutVo.getDetailData()!=null&&!stOutVo.getDetailData().equals("")){
			JSONObject jsDetailData=JSON.parseObject(stOutVo.getDetailData());
			List<StOutVo> resultList = JSON.parseArray(jsDetailData.get("rows").toString(), StOutVo.class);
			if(resultList.size() > 0){
				for (StOutVo ssovo : resultList) {
					list.add(ssovo);
				}
			}
		}
		return list;
	}
	
	/**
	 * 出库单汇总信息     综合查询
	 */
	public Json searchByCondition(StOutVo stOutVo) throws Exception {
		return stOutMainDAO.searchByCondition(stOutVo.getSort(), stOutVo.getOrder(), stOutVo.getPage(), stOutVo.getRows(),stOutVo.getStomDateStart(), stOutVo.getStomDateEnd(), stOutVo.getStomId(), stOutVo.getReceptionId(), 
				stOutVo.getCarLicense(), stOutVo.getCustomName(), stOutVo.getCarVan(),stOutVo.getEnterpriseId());
	}
	
	/**
	 * 通过索赔名称获取索赔ID
	 */
	public BasClaimsType searchByClaimsId(String claimsType)throws Exception{
		BasClaimsType basClaimsType=null;
		if(claimsType!=null&&!claimsType.trim().equals(""))
			basClaimsType=basClaimsTypeDAO.get(" from BasClaimsType basClaimsType where basClaimsType.claimsId="+Short.parseShort(claimsType));
		return basClaimsType;
	}
	
	/**
	 * 出库单管理模块(工单信息)     预加载
	 */
	public Json loadFrtReception(StOutVo sovo) throws Exception{
	    return frtReceptionDao.loadFrtReception(sovo.getPage(),sovo.getRows(),sovo.getSort(),sovo.getOrder(),null,sovo.getEnterpriseId());
	}
	
	/**
	 * 出库单管理模块(工单信息)     综合查询
	 */
	public Json searchFrtReceptInfoByCondition(StOutVo sovo) throws Exception{
	    return frtReceptionDao.searchFrtReceptionByCondition(sovo.getReceptionId(), sovo.getCarLicense(), sovo.getCustomId(), sovo.getCarVan(),sovo.getEnterpriseId());
	}
	
	/**
	 * 出库单管理模块  （ 计划用料信息）     预加载   
	 */
	public Json reFrtReceptParts(StOutVo stOutVo)throws Exception{
		return frtRcptPartsDAO.searchByReceptionId(stOutVo.getReceptionId(),stOutVo.getPage(),stOutVo.getRows(),stOutVo.getEnterpriseId());
	}
	
	/**
	 * 出库单管理(通过出库单号获取出库单明细信息)     预加载
	 */
	public List<StOutVo> serachStOutItemByCondition(StOutVo stOutVo) throws Exception {
		return stOutItemDAO.serachStOutItemByCondition(stOutVo.getStomId());
	}
	
	/**
	 * 出库单管理模块  （ 配件信息）     预加载
	 */
	public Json loadFrtParts(String partsId,String partsName,String storeId,int enterprise_id) throws Exception {
		return partsChangePriceDAO.searchByCondition(partsId, partsName,storeId,enterprise_id);
	}
	
	/**
	 * 判断出库单中工单号是否在工单退料单中存在，若存在，不能进行删除修改操作，若不存在，可进行删除修改操作
	 */
	public boolean doDeleteOrUpdate(StOutVo stOutVo)throws Exception{
		List<Object[]> list=stRtPartsMainDAO.createSQLQuery("SELECT st_out_item.INDEX_ID FROM st_out_item WHERE st_out_item.STOM_ID='"+stOutVo.getStomId()+"' AND st_out_item.INDEX_ID IN (SELECT st_rt_parts_detail.INDEX_ID"+ 
		" FROM st_rt_parts_detail,st_rt_parts_main WHERE st_rt_parts_detail.STRTPM_ID=st_rt_parts_main.STRTPM_ID AND "+
		" st_rt_parts_main.enterprise_id="+stOutVo.getEnterpriseId()+" and st_rt_parts_main.RECEPTION_ID='"+stOutVo.getReceptionId()+"' AND st_rt_parts_detail.STRTPM_ID LIKE '"+Contstants.QUITPARTS_TAG.SERVICEQUEITID+"%')");
		if(list!=null&&list.size()>0)
			return false;   //false为存在
		return true;
	}
	
	
	
	/**
	 * 出库单模块     （出库单明细 ，汇总信息）     添加
	 */
	@Log(moduleName = "出库单管理", opertype = "新增出库单", content = "出库单管理-->新增出库单")
	public void add(StOutVo sovo,List<StOutVo> list) throws Exception {
		StOutMain stOutMain=new StOutMain();
		stOutMain.setStomId(CreateID.createId("StOutMain", "CK"));
		stOutMain=copyMainProperty(stOutMain,sovo,true);
		stOutMainDAO.save(stOutMain);//出库单汇总添加
		for (StOutVo stOutVo:list) {
			StOutItem stOutItem=new StOutItem();
			stOutItem.setStOutMain(stOutMain);  
			stOutItem=copyDetailProperty(stOutItem, stOutVo);
			stOutItemDAO.save(stOutItem);//出库单明细添加
			PartsChangePrice partsChangePrice=partsChangePriceDAO.get(" from PartsChangePrice pc where pc.storeId="+stOutVo.getStoreId()+" and pc.partsId='"+stOutVo.getPartsId()+"'");
			double partscount=partsChangePrice.getPartsNowCount()-Double.parseDouble(stOutVo.getItemCount());//计算配件总数量
			partsChangePrice.setPartsNowCount(partscount);
			partsChangePriceDAO.merge(partsChangePrice);//出库完成后,对配件调价表中的库存量进行修改
		}
	}

	/**
	 * 出库单模块     （出库单明细 ，汇总信息）    删除
	 */
	@Log(moduleName = "出库单管理", opertype = "删除出库单", content = "出库单管理-->删除出库单")
	public void delete(StOutVo stOutVo) throws Exception
	{
		List<StOutItem> resultlist=stOutItemDAO.find(" from StOutItem soi where soi.stOutMain.stomId='"+stOutVo.getStomId()+"'");
		if(resultlist!=null&&resultlist.size()>0)
		   for (StOutItem stOutItem:resultlist) {
				stOutItemDAO.delete(stOutItem);
				//修改配件调价表中库存量信息
				PartsChangePrice partsChangePrice=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.storeId="+stOutItem.getStoreId()+" and pcp.partsId='"+stOutItem.getPartsId()+"'");
				if(partsChangePrice!=null&&!partsChangePrice.equals("")){
					double partscount=partsChangePrice.getPartsNowCount()+stOutItem.getItemCount();//计算配件总数量
					partsChangePrice.setPartsNowCount(partscount);
					partsChangePriceDAO.merge(partsChangePrice);
				}
		   }
		StOutMain stOutMain=stOutMainDAO.get(" from StOutMain som where som.stomId='"+stOutVo.getStomId()+"'");
		if(stOutMain!=null&&!stOutMain.equals(""))
		   stOutMainDAO.delete(stOutMain);
	}

	/**
	 * 出库单模块     （出库单明细 ，汇总信息）      修改
	 */
	@Log(moduleName = "出库单管理", opertype = "修改出库单", content = "出库单管理-->修改出库单")
	public void update(StOutVo stOutVo,List<StOutVo> list)throws Exception{
		List<StOutItem> resultList=stOutItemDAO.find(" from StOutItem soi where soi.stOutMain.stomId='"+stOutVo.getStomId()+"'");
		for (StOutItem stOutItem:resultList) {//删除原有出库明细信息
			if(stOutItem.getStOutMain().getStomId().equals(stOutVo.getStomId())){
				PartsChangePrice partsChangePrice=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.storeId="+stOutItem.getStoreId()+" and pcp.partsId='"+stOutItem.getPartsId()+"'");
				if(partsChangePrice!=null&&!partsChangePrice.equals("")){
					partsChangePrice.setPartsNowCount(partsChangePrice.getPartsNowCount()+stOutItem.getItemCount());
					partsChangePriceDAO.merge(partsChangePrice);//删除原有出库明细数据之前，首先还原配件调价表库存量
				}
				stOutItemDAO.delete(stOutItem);//执行删除
			}
		}
		StOutMain stOutMain=stOutMainDAO.get(" from StOutMain som where som.stomId='"+stOutVo.getStomId()+"'");
		stOutMain=copyMainProperty(stOutMain,stOutVo,false);
		stOutMainDAO.merge(stOutMain);//保存出库汇总信息
		
		for (StOutVo sovo:list) {//出库单明细添加
			StOutItem stOutItem=new StOutItem();
			stOutItem.setStOutMain(stOutMain);
			stOutItem=copyDetailProperty(stOutItem, sovo);
			stOutItemDAO.save(stOutItem);
			PartsChangePrice partsChangePrice=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.storeId="+stOutVo.getStoreId()+" and pcp.partsId='"+sovo.getPartsId()+"'");
			if(partsChangePrice!=null&&!partsChangePrice.equals("")){
				partsChangePrice.setPartsNowCount(partsChangePrice.getPartsNowCount()-Double.parseDouble(sovo.getItemCount()));
				partsChangePriceDAO.merge(partsChangePrice);//修改配件调价表中现有库存数量
			}
		}
	}
	
	public StOutMain copyMainProperty(StOutMain stOutMain,StOutVo sovo,boolean inso){
		if(sovo.getReceptionId()!=null&&!sovo.getReceptionId().trim().equals(""))
			stOutMain.setCerNo(sovo.getReceptionId().trim());                   //原凭证号
		if(sovo.getStomDate()!=null&&!sovo.getStomDate().trim().equals(""))
			stOutMain.setStomDate(Utils.dateFormat(sovo.getStomDate().trim()));                   //获取出库日期
		if(sovo.getPickingMember()!=null&&!sovo.getPickingMember().trim().equals(""))
			stOutMain.setPickingMember(Short.parseShort(sovo.getPickingMember().trim()));  //领料员
		if(inso){
			if(sovo.getManager()!=null&&!sovo.getManager().trim().equals(""))
				stOutMain.setManager(Short.parseShort(sovo.getManager().trim()));//经办人 
		}
		if(sovo.getTotalNum()!=null&&!sovo.getTotalNum().trim().equals(""))
			stOutMain.setTotalNum(Double.parseDouble(sovo.getTotalNum().trim()));            //数量合计 
		if(sovo.getStomSumAmount()!=null&&!sovo.getStomSumAmount().trim().equals(""))
			stOutMain.setStomSumAmount(Double.parseDouble(sovo.getStomSumAmount().trim()));//出库金额合计
		if(sovo.getNoTaxCastamont()!=null&&!sovo.getNoTaxCastamont().trim().equals(""))
			stOutMain.setNotaxCastamont(Double.parseDouble(sovo.getNoTaxCastamont().trim())); //出库未税成本额
		if(sovo.getTaxCastamont()!=null&&!sovo.getTaxCastamont().trim().equals(""))
			stOutMain.setTaxCastamont(Double.parseDouble(sovo.getTaxCastamont().trim()));  //出库含税成本额
		if(sovo.getStomRemark()!=null&&!sovo.getStomRemark().trim().equals(""))
			stOutMain.setStomRemark(sovo.getStomRemark().trim());         
		stOutMain.setEnterpriseId(sovo.getEnterpriseId());
		return stOutMain;
	}
	
	public StOutItem copyDetailProperty(StOutItem stOutItem,StOutVo stOutVo)throws Exception{
		if(stOutVo.getPartsId()!=null&&!stOutVo.getPartsId().trim().equals(""))
			stOutItem.setPartsId(stOutVo.getPartsId().trim());                         //获取配件编号
		if(stOutVo.getItemPrice()!=null&&!stOutVo.getItemPrice().trim().equals(""))
			stOutItem.setItemPrice(Double.parseDouble(stOutVo.getItemPrice().trim())); //获取配件销售价格
		if(stOutVo.getItemCount()!=null&&!stOutVo.getItemCount().trim().equals(""))
			stOutItem.setItemCount(Double.parseDouble(stOutVo.getItemCount().trim()));   //获取配件数量
		if(stOutVo.getStoreId()!=null&&!stOutVo.getStoreId().trim().equals(""))
			stOutItem.setStoreId(Short.parseShort(stOutVo.getStoreId().trim()));       //获取仓库编号
        if(stOutVo.getClaimsId()!=null&&!stOutVo.getClaimsId().equals(""))
			stOutItem.setClaimsType(stOutVo.getClaimsId());
		if(stOutVo.getItemChargeId()!=null&&!"".equals(stOutVo.getItemChargeId()))
			stOutItem.setItemCharge(stOutVo.getItemChargeId());
		if(stOutVo.getNotaxCastamont()!=null&&!stOutVo.getNotaxCastamont().trim().equals(""))
			stOutItem.setNotaxCastamont(Double.parseDouble(stOutVo.getNotaxCastamont()));//获取未税成本额
		if(stOutVo.getTaxCastamont()!=null&&!stOutVo.getTaxCastamont().trim().equals(""))
			stOutItem.setTaxCastamont(Double.parseDouble(stOutVo.getTaxCastamont()));//获取含税成本额
		if(stOutVo.getTaxCast()!=null&&!stOutVo.getTaxCast().trim().equals(""))
			stOutItem.setTaxCast(Double.parseDouble(stOutVo.getTaxCast().trim()));      //获取含税成本
		if(stOutVo.getNotaxCast()!=null&&!stOutVo.getNotaxCast().trim().equals(""))
			stOutItem.setNotaxCast(Double.parseDouble(stOutVo.getNotaxCast().trim()));  //获取未税成本
		if(stOutVo.getAmount()!=null&&!stOutVo.getAmount().trim().equals(""))
			stOutItem.setAmount(Double.parseDouble(stOutVo.getAmount().trim()));        //获取销售额
		if(stOutVo.getOutItemRemark()!=null&&!stOutVo.getOutItemRemark().trim().equals(""))
			stOutItem.setOutItemRemark(stOutVo.getOutItemRemark().trim());
		if(stOutVo.getRcptpartsIndex()!=null&&!stOutVo.getRcptpartsIndex().trim().equals(""))
			stOutItem.setRcptpartsIndex(Integer.parseInt(stOutVo.getRcptpartsIndex().trim()));
	    return stOutItem;
	}
}
