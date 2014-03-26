package com.syuesoft.sell.instore.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.dao.CarModelDAO;
import com.syuesoft.sell.instore.dao.SellPurchaseDAO;
import com.syuesoft.sell.instore.service.SellPurchaseService;
import com.syuesoft.sell.instore.vo.SellPurchaseVo;
import com.syuesoft.sell.model.XsCarModel;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellPurchase;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;

@Service("sellPurchaseService")
public class sellPurchaseServiceImpl extends BaseLogServiceImpl implements SellPurchaseService {
	private SellPurchaseDAO  sellPurchaseDAO; 
	private BaseTagDAO baseTagDAO;
	private CarModelDAO carModelDAO;
	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	public Datagrid getPager(SellPurchaseVo  purchaseVo) throws Exception{
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsSellPurchase purchase where 1=1 and " +
				"purchase.enterprise_id="+purchaseVo.getEnterprise_id());
		if(purchaseVo.getQueryPlanDate()!=null  && !("".equals(purchaseVo.getQueryPlanDate()))){
						hql.append(" and purchase.sellDate >= '"+purchaseVo.getQueryPlanDate()+"'");
			}
		if(purchaseVo.getQueryPlanDate2()!=null  && !("".equals(purchaseVo.getQueryPlanDate2()))){
				hql.append(" and purchase.sellDate <= '"+purchaseVo.getQueryPlanDate2()+"'");
			
		}
		if((purchaseVo.getQueryPlanDate()==null  ||"".equals(purchaseVo.getQueryPlanDate()))&&
				(purchaseVo.getQueryPlanDate2()==null  ||"".equals(purchaseVo.getQueryPlanDate2()))){
			hql.append( " and purchase.sellDate BETWEEN " +
					"'"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(
							PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,purchaseVo.getEnterprise_id()).getCiValue()))+"' " +
							"AND '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
			
		}
		if(purchaseVo.getQueryBrand()!=null && !("".equals(purchaseVo.getQueryBrand()))){
			hql.append( " and purchase.carBrand ="+purchaseVo.getQueryBrand());
		}
		if(purchaseVo.getQueryModel()!=null && !("".equals(purchaseVo.getQueryModel()))){
			hql.append( " and purchase.carModelId  ="+purchaseVo.getQueryModel());
		}
		if(purchaseVo.getQueryColor()!=null && !("".equals(purchaseVo.getQueryColor()))){
			hql.append( " and purchase.carColor  ="+purchaseVo.getQueryColor());
		}
		 if (purchaseVo.getSort() != null && !"".equals(purchaseVo.getSort().trim())
	                && purchaseVo.getOrder() != null
	                && !"".equals(purchaseVo.getOrder().trim()))
	        {
			  hql.append(" order by purchase." + purchaseVo.getSort() + " "
	                    + purchaseVo.getOrder());
	        }
		List<XsSellPurchase> lst=sellPurchaseDAO.find(hql.toString(), purchaseVo.getPage(), purchaseVo.getRows());
		List<SellPurchaseVo> rows =new ArrayList<SellPurchaseVo>();
		if(lst!=null && lst.size()>0){
			for(XsSellPurchase purchase:lst){
				SellPurchaseVo sellPurchaseVo=new SellPurchaseVo();
				BeanUtils.copyProperties(purchase, sellPurchaseVo);
				if(purchase.getCarBrand()!=null && !("".equals(purchase.getCarBrand()))){
					XsChilddictionary  carBrand=baseTagDAO.findById(purchase.getCarBrand());
					if(carBrand!=null){
						sellPurchaseVo.setBrandName(carBrand.getDataValue());
					}
				}
				if(purchase.getCarColor()!=null && !("".equals(purchase.getCarColor()))){
					XsChilddictionary  carColor=baseTagDAO.findById(purchase.getCarColor());
					if(carColor!=null){
						sellPurchaseVo.setColorName(carColor.getDataValue());
					}
				}
				if(purchase.getCarModelId()!=null && !("".equals(purchase.getCarModelId()))){
					XsCarModel carModel=carModelDAO.get(XsCarModel.class,purchase.getCarModelId());
					if(carModel!=null){
						sellPurchaseVo.setModelName(carModel.getModelName());
					}
				}
				rows.add(sellPurchaseVo);
			}
		}
		int total = sellPurchaseDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	@Log(systemName="销售系统", moduleName="采购计划管理",opertype="删除") 
	public void deleteSellPurchase(SellPurchaseVo  purchaseVo) throws Exception{	
		XsSellPurchase purchase=new XsSellPurchase();
		BeanUtils.copyProperties(purchaseVo, purchase);
		sellPurchaseDAO.delete(purchase);
		setContent("删除采购编号为【"+purchase.getPurchaseId()+"】的信息！"); 
	}
	
	@Log(systemName="销售系统", moduleName="采购计划管理",opertype="新增")
	public void addSellPurchase(SellPurchaseVo  purchaseVo) throws Exception{
		XsSellPurchase purchase=new XsSellPurchase();
		BeanUtils.copyProperties(purchaseVo, purchase);
		purchase.setPurchaseId(CreateID.createId( "SellPurchase",Contstants.SELL_BILLSDEPLOY.SELLPURCHASE));
		purchase.setPlanPercent(doubleFormat((purchase.getActualNumber()*100.0/purchase.getPlanNumber()))+"%");
		sellPurchaseDAO.save(purchase);
		XsChilddictionary carBrand=baseTagDAO.findById(purchase.getCarBrand());
		XsCarModel model= carModelDAO.get(" from XsCarModel where modelId="+purchase.getCarModelId());
		XsChilddictionary color=baseTagDAO.findById(purchase.getCarColor());
		setContent("新增【采购计划管理】编号为【"+purchase.getPurchaseId()+"】,品牌为【"+carBrand.getDataValue()+"】," +
				"型号名称为【"+model.getModelName()+"】,颜色为【"+color.getDataValue()+"】," +
				"计划采购数量【"+purchase.getPlanNumber()+"】,实际采购数量【"+purchase.getPlanPercent()+"】," +
				"完成百分比【"+purchase.getPlanPercent()+"】的信息！");
		
	}
	/**
	 * 精度精确到小数点后两位
	 */
	public static double doubleFormat(double value)
	{
		BigDecimal b = new BigDecimal(value);
		double changeValue = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return changeValue;
	}
	
	@Log(systemName="销售系统", moduleName="采购计划管理",opertype="修改")
	public void updateSellPurchase(SellPurchaseVo  purchaseVo) throws Exception{
		XsSellPurchase purchase=new XsSellPurchase();
		BeanUtils.copyProperties(purchaseVo, purchase);
		purchase.setPlanPercent(doubleFormat((purchase.getActualNumber()*100.0/purchase.getPlanNumber()))+"%");
		sellPurchaseDAO.merge(purchase);
		XsChilddictionary carBrand=baseTagDAO.findById(purchase.getCarBrand());
		XsCarModel model= carModelDAO.get(" from XsCarModel where modelId="+purchase.getCarModelId());
		XsChilddictionary color=baseTagDAO.findById(purchase.getCarColor());
		setContent("修改编号为【"+purchase.getPurchaseId()+"】的【采购计划管理】信息,品牌为【"+carBrand.getDataValue()+"】," +
				"型号名称为【"+model.getModelName()+"】,颜色为【"+color.getDataValue()+"】," +
				"计划采购数量【"+purchase.getPlanNumber()+"】,实际采购数量【"+purchase.getPlanPercent()+"】," +
				"完成百分比【"+purchase.getPlanPercent()+"】！");
		
	}

	public SellPurchaseDAO getSellPurchaseDAO() {
		return sellPurchaseDAO;
	}
	@Autowired
	public void setSellPurchaseDAO(SellPurchaseDAO sellPurchaseDAO) {
		this.sellPurchaseDAO = sellPurchaseDAO;
	}
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}
	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}
	public CarModelDAO getCarModelDAO() {
		return carModelDAO;
	}
	@Autowired
	public void setCarModelDAO(CarModelDAO carModelDAO) {
		this.carModelDAO = carModelDAO;
	}
	
	
	
}
