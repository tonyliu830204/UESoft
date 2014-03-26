package com.syuesoft.st.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.model.StPreOutDetail;
import com.syuesoft.model.StPreOutMain;
import com.syuesoft.st.dao.BasClaimsTypeDAO;
import com.syuesoft.st.dao.StPreOutDetailDAO;
import com.syuesoft.st.dao.StPreOutMainDAO;
import com.syuesoft.st.service.StPreOutService;
import com.syuesoft.st.vo.StPreOutVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;


@SuppressWarnings("serial")
@Service("stPreOutService")
public class StPreOutServiceImpl extends BaseAction implements StPreOutService {

	@Autowired StPreOutDetailDAO stPreOutDetailDAO;   // 预出库信息DAO
	@Autowired BasClaimsTypeDAO basClaimsTypeDAO;     // 索赔分类DAO
	@Autowired StPreOutMainDAO stPreOutMainDAO;         // 预出库汇总表DAO
	@Autowired BasStuffDao basStuffDao;                   // 员工信息DAO
	
	/**
	 * 明细数据由JSON转换为List集合
	 */
	public List<StPreOutVo> jsonChangeDetailList(StPreOutVo stPreOutVo){
		List<StPreOutVo> list =new ArrayList<StPreOutVo>();
		if(stPreOutVo.getDetailData()!=null&&!stPreOutVo.getDetailData().equals("")){
			JSONObject jsDetailData=JSON.parseObject(stPreOutVo.getDetailData());
			List<StPreOutVo> resultList = JSON.parseArray(jsDetailData.get("rows").toString(), StPreOutVo.class);
			if(resultList.size() > 0){
				for (StPreOutVo spovo : resultList) {
					list.add(spovo);
				}
			}
		}
		return list;
	}
	
	/**
	 * 预出库汇总信息    综合查询
	 */
	public Json searchByCondition(StPreOutVo stPreOutVo) throws Exception {
		return stPreOutMainDAO.searchByCondition(stPreOutVo.getPage(), stPreOutVo.getRows(), stPreOutVo.getSort(), stPreOutVo.getOrder(),stPreOutVo.getStpremTimeStart(),stPreOutVo.getStpremTimeEnd(),stPreOutVo.getReceptionId(), stPreOutVo.getStpremId(), stPreOutVo.getStpremFlg(),stPreOutVo.getEnterpriseId());
	}
	
	/**
	 * 领用人信息    预加载
	 */
	public Json loadBasStuff(StPreOutVo stPreOutVo) throws Exception
	{
		return basStuffDao.loadPickingMember(stPreOutVo.getPage(), stPreOutVo.getRows(), stPreOutVo.getSort(), stPreOutVo.getOrder(),stPreOutVo.getEnterpriseId());
	}
	
	/**
	 * 领用人信息    综合查询
	 */
	public Json searchBasStuffByCondition(StPreOutVo stPreOutVo) throws Exception
	{
		return basStuffDao.searchPickingMemberByCondition(stPreOutVo.getStfId(), stPreOutVo.getStfName(),stPreOutVo.getEnterpriseId());
	}
	
	/**
	 * 根据预出库单号加载相关预出库明细信息
	 */
	public List<StPreOutVo> searchStPreOutDetailByStpremId(StPreOutVo stPreOutVo) throws Exception{
		return stPreOutDetailDAO.searchStPreOutDetailByStpremId(stPreOutVo.getStpremId());
	}
	
	/**
	 * 预出库管理模块         预出库汇总信息          添加
	 */
	@Log(moduleName = "预出库管理", opertype = "新增预出库", content = "预出库管理-->新增预出库")
	public void add(StPreOutVo stPreOutVo,List<StPreOutVo> list)throws Exception
	{
		StPreOutMain stPreOutMain=new StPreOutMain();
		stPreOutMain.setStpremId(CreateID.createId("StPreOutMain", "YCK"));
		stPreOutMain=this.copyMainProperty(stPreOutVo, stPreOutMain);
		stPreOutMainDAO.save(stPreOutMain);//预出库汇总添加
		if(list!=null&&list.size()>0){
			for (StPreOutVo spovo:list) {
				StPreOutDetail stPreOutDetail=new StPreOutDetail();
				stPreOutDetail.setStPreOutMain(stPreOutMain);
				stPreOutDetail=copyDetailProperty(spovo, stPreOutDetail);
				stPreOutDetailDAO.save(stPreOutDetail);//预出库明细添加
			}
		}
	}

	/**
	 * 预出库管理模块       预出库汇总信息          删除
	 */
	@Log(moduleName = "预出库管理", opertype = "删除预出库", content = "预出库管理-->删除预出库")
	public void delete(StPreOutVo stPreOutVo)throws Exception
	{
		List<StPreOutDetail> list=stPreOutDetailDAO.find("from StPreOutDetail stPreOutDetail where stPreOutDetail.stPreOutMain.stpremId='"+stPreOutVo.getStpremId()+"'");
		if(list!=null&&list.size()>0)
			for (StPreOutDetail stPreOutDetail:list) {
				stPreOutDetailDAO.delete(stPreOutDetail);//删除相对应预出库明细信息
		    }
		StPreOutMain stPreOutMain=stPreOutMainDAO.get(" from StPreOutMain stPreOutMain where stPreOutMain.stpremId='"+stPreOutVo.getStpremId()+"'");
		stPreOutMainDAO.delete(stPreOutMain);//删除预出库汇总信息
	}
	
	/**
	 * 预出库管理模块       预出库汇总信息           修改
	 */
	@Log(moduleName = "预出库管理", opertype = "修改预出库", content = "预出库管理-->修改预出库")
	public void update(StPreOutVo stPreOutVo,List<StPreOutVo> list)throws Exception
	{
		List<StPreOutDetail> resultList=stPreOutDetailDAO.find("from StPreOutDetail stPreOutDetail where stPreOutDetail.stPreOutMain.stpremId='"+stPreOutVo.getStpremId()+"'");
		if(resultList!=null&&resultList.size()>0){
			for (StPreOutDetail stPreOutDetail:resultList) {
				stPreOutDetailDAO.delete(stPreOutDetail);//删除原有预出库对应预出库明细信息
			}
		}
		StPreOutMain stPreOutMain=stPreOutMainDAO.get(" from StPreOutMain stPreOutMain where stPreOutMain.stpremId='"+stPreOutVo.getStpremId()+"'");
		stPreOutMain=this.copyMainProperty(stPreOutVo, stPreOutMain);
		stPreOutMainDAO.merge(stPreOutMain);//修改预出库汇总信息
		if(list!=null&&list.size()>0){
			for (StPreOutVo spovo:list) {
				StPreOutDetail stPreOutDetail=new StPreOutDetail();
				stPreOutDetail.setStPreOutMain(stPreOutMain);
				stPreOutDetail=copyDetailProperty(spovo, stPreOutDetail);
				stPreOutDetailDAO.save(stPreOutDetail);//预出库明细添加
			}
		}
	}
	
	public StPreOutMain copyMainProperty(StPreOutVo stPreOutVo,StPreOutMain stPreOutMain)throws Exception{
		if(stPreOutVo.getStpremTime()!=null&&!stPreOutVo.getStpremTime().trim().equals(""))
			stPreOutMain.setStpremTime(Utils.dateFormat(stPreOutVo.getStpremTime().trim()));
		if(stPreOutVo.getReceptionId()!=null&&!stPreOutVo.getReceptionId().trim().equals(""))
			stPreOutMain.setReceptionId(stPreOutVo.getReceptionId().trim());
		if(stPreOutVo.getStfId()!=null&&!stPreOutVo.getStfId().trim().equals(""))
			stPreOutMain.setStfId(Short.parseShort(stPreOutVo.getStfId().trim()));
		if(stPreOutVo.getStpremSumAmount()!=null&&!stPreOutVo.getStpremSumAmount().trim().equals(""))
			stPreOutMain.setStpremSumAmount(Double.parseDouble(stPreOutVo.getStpremSumAmount().trim()));
		if(stPreOutVo.getStpremSumCost()!=null&&!stPreOutVo.getStpremSumCost().trim().equals(""))
			stPreOutMain.setStpremSumCost(Double.parseDouble(stPreOutVo.getStpremSumCost().trim()));
		if(stPreOutVo.getStpremStfId()!=null&&!stPreOutVo.getStpremStfId().trim().equals(""))
			stPreOutMain.setStpremStfId(Short.parseShort(stPreOutVo.getStpremStfId().trim()));   //相关人员编号  
		if(stPreOutVo.getStpremFlg()!=null&&!stPreOutVo.getStpremFlg().trim().equals(""))
			stPreOutMain.setStpremFlg(Short.parseShort(stPreOutVo.getStpremFlg().trim()));
		if(stPreOutVo.getStomRemark()!=null&&!stPreOutVo.getStomRemark().trim().equals(""))
			stPreOutMain.setStomRemark(stPreOutVo.getStomRemark().trim());
		if(stPreOutVo.getNumTotal()!=null&&!stPreOutVo.getNumTotal().trim().equals(""))
			stPreOutMain.setNumTotal(Double.parseDouble(stPreOutVo.getNumTotal().trim()));
		stPreOutMain.setEnterpriseId(stPreOutVo.getEnterpriseId());
		return stPreOutMain;
	}
	
	public StPreOutDetail copyDetailProperty(StPreOutVo spovo,StPreOutDetail stPreOutDetail)throws Exception{
		if(spovo.getPartsId()!=null&&!spovo.getPartsId().trim().equals(""))
			stPreOutDetail.setPartsId(spovo.getPartsId().trim());
		if(spovo.getItemCount()!=null&&!spovo.getItemCount().trim().equals(""))
			stPreOutDetail.setStpredCnt(Double.parseDouble(spovo.getItemCount().trim()));
		stPreOutDetail.setStpredStatus(true);
		if(spovo.getItemPrice()!=null&&!spovo.getItemPrice().trim().equals(""))
			stPreOutDetail.setItemprice(Double.parseDouble(spovo.getItemPrice().trim()));
		if(spovo.getStoreId()!=null&&!spovo.getStoreId().trim().equals(""))
			stPreOutDetail.setStoreId(Short.parseShort(spovo.getStoreId()+""));
		if(spovo.getClaimsId()!=null&&!spovo.getClaimsId().equals("")){
			stPreOutDetail.setClaimsId(Short.parseShort(spovo.getClaimsId()));
			stPreOutDetail.setChargeId(Short.parseShort(spovo.getClaimsId()));
		}
		if(spovo.getAmount()!=null&&!spovo.getAmount().trim().equals(""))
			stPreOutDetail.setAmont(Double.parseDouble(spovo.getAmount().trim()));
		if(spovo.getTaxCast()!=null&&!spovo.getTaxCast().trim().equals(""))
			stPreOutDetail.setTaxcast(Double.parseDouble(spovo.getTaxCast().trim()));
		if(spovo.getNotaxCast()!=null&&!spovo.getNotaxCast().trim().equals(""))
			stPreOutDetail.setNotaxcast(Double.parseDouble(spovo.getNotaxCast().trim()));
		if(spovo.getTaxCastamont()!=null&&!spovo.getTaxCastamont().trim().equals(""))
			stPreOutDetail.setTaxcastamont(Double.parseDouble(spovo.getTaxCastamont().trim()));
		if(spovo.getNotaxCastamont()!=null&&!spovo.getNotaxCastamont().trim().equals(""))
			stPreOutDetail.setNotaxcastamont(Double.parseDouble(spovo.getNotaxCastamont().trim()));
		if(spovo.getItemRemark()!=null&&!spovo.getItemRemark().trim().equals(""))
			stPreOutDetail.setItemRemark(spovo.getItemRemark());
		return stPreOutDetail;
	}

}