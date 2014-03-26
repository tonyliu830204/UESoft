package com.syuesoft.sell.base.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.dao.CarInfoDAO;
import com.syuesoft.sell.base.dao.CarModelDAO;
import com.syuesoft.sell.base.service.CarModelService;
import com.syuesoft.sell.base.vo.CarModelVo;
import com.syuesoft.sell.model.XsCarModel;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Msg;

@Service("carModelService")
public class CarModelServiceImpl extends BaseLogServiceImpl  implements CarModelService {
	private CarModelDAO carModelDAO ;
	private BaseTagDAO baseTagDAO;
	@Autowired
	private CarInfoDAO carInfoDAO;
	
	public Datagrid getPager(CarModelVo carModelVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsCarModel model where 1=1 " +
				"and model.enterpriseId="+carModelVo.getEnterpriseId());
		if(carModelVo.getModelCode() != null && !"".equals(carModelVo.getModelCode())){
			hql.append(" and model.modelCode like '%"+StringEscapeUtils.escapeSql(carModelVo.getModelCode().trim())+"%'");
		}
		if(carModelVo.getCarMId() != null && !"".equals(carModelVo.getCarMId())){
			hql.append(" and model.modelId = '"+carModelVo.getCarMId()+"'") ;
		}if(carModelVo.getCarMBrand()!=null && !("".equals(carModelVo.getCarMBrand()))){
			
			hql.append(" and  model.carBrand = '"+carModelVo.getCarMBrand()+"'");
		}
		  if (carModelVo.getSort() != null && !"".equals(carModelVo.getSort().trim())
	                && carModelVo.getOrder() != null
	                && !"".equals(carModelVo.getOrder().trim()))
	        {
			  hql.append(" order by model." + carModelVo.getSort() + " "
	                    + carModelVo.getOrder());
	        }
		List<XsCarModel> lst=carModelDAO.find(hql.toString(), carModelVo.getPage(), carModelVo.getRows());
		List<CarModelVo> rows =new ArrayList<CarModelVo>();
		if(lst!=null && lst.size()>0){
			for(XsCarModel carModel:lst){
				CarModelVo cModelVo=new CarModelVo();
				if(carModel.getXsChilddictionary().getChildId()!=null && !("".equals(carModel.getXsChilddictionary().getChildId()))){
					XsChilddictionary carBrand=baseTagDAO.findById(carModel.getXsChilddictionary().getChildId());
					if(carBrand!=null ){
						cModelVo.setCarBrandName(carBrand.getDataValue());
					}
				}
				cModelVo.setModelId(carModel.getModelId());
				cModelVo.setCarBrand(carModel.getXsChilddictionary().getChildId());
				cModelVo.setModelExamineApprove(carModel.getModelExamineApprove());
				cModelVo.setModelCancelModel(carModel.getModelCancelModel());
				cModelVo.setModelCode(carModel.getModelCode());
				cModelVo.setModelName(carModel.getModelName());
				cModelVo.setModelCostPrice(carModel.getModelCostPrice());
				cModelVo.setModelSalesPrice(carModel.getModelSalesPrice());
				cModelVo.setModelSalesLimitPrice(carModel.getModelSalesLimitPrice());
				cModelVo.setModelTwoSalesLimitPrice(carModel.getModelTwoSalesLimitPrice());
				cModelVo.setModelThreeSalesLimitPrice(carModel.getModelThreeSalesLimitPrice());
				cModelVo.setModelNormsModel(carModel.getModelNormsModel());
				cModelVo.setModelRemark(carModel.getModelRemark());
				rows.add(cModelVo);
			}	
		}
		int total = carModelDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;	
	}
	
	@Log(systemName="销售系统", moduleName="车辆型号资料",opertype="删除")
	public Msg deleteCarModel(CarModelVo carModelVo) throws Exception {	
		Msg msg = new Msg();
		List list=carInfoDAO.createSQLQuery(" select xs_car_model_id from xs_car_info where xs_car_model_id="+carModelVo.getModelId());
		if(list!=null && list.size()>0){
			msg.setMsg("该车辆型号已使用，不可以删除！");
			msg.setSuccess(false);
		}else{
			
			List lst=carInfoDAO.find(" from  XsCarModel  c where c.modelId="+carModelVo.getModelId());
			if(lst!=null && lst.size()>0){
				XsCarModel xsCarModel=(XsCarModel) lst.get(0);
				carModelDAO.delete(xsCarModel);
				setContent("删除【车辆型号资料】编码为【"+xsCarModel.getModelCode()+"】," +
						"名称为【"+xsCarModel.getModelName()+"】的信息！");
					msg.setMsg("删除成功！");
					msg.setSuccess(true);
			}else{
				msg.setMsg("数据不存在！");
				msg.setSuccess(false);
			}
			
			
		}
		return msg;
	}
	
	@Log(systemName="销售系统", moduleName="车辆型号资料",opertype="新增")
	public void addCarModel(CarModelVo carModelVo) throws Exception {
		XsCarModel xsCarModel=new XsCarModel();
		XsChilddictionary carBrand=baseTagDAO.findById(carModelVo.getCarBrand());
		xsCarModel.setXsChilddictionary(carBrand);
		xsCarModel.setModelExamineApprove(carModelVo.getModelExamineApprove());
		xsCarModel.setModelCancelModel(carModelVo.getModelCancelModel());
		xsCarModel.setModelCode(CreateID.createId("carModel", Contstants.SELL_BILLSDEPLOY.CARMODEL));
		xsCarModel.setModelName(carModelVo.getModelName());
		xsCarModel.setModelCostPrice(carModelVo.getModelCostPrice());
		xsCarModel.setModelSalesPrice(carModelVo.getModelSalesPrice());
		xsCarModel.setModelSalesLimitPrice(carModelVo.getModelSalesLimitPrice());
		xsCarModel.setModelTwoSalesLimitPrice(carModelVo.getModelTwoSalesLimitPrice());
		xsCarModel.setModelThreeSalesLimitPrice(carModelVo.getModelThreeSalesLimitPrice());
		xsCarModel.setModelNormsModel(carModelVo.getModelNormsModel());
		xsCarModel.setModelRemark(carModelVo.getModelRemark());
		xsCarModel.setEnterpriseId(carModelVo.getEnterpriseId());
		carModelDAO.save(xsCarModel);
		String cancelName="";
		if(xsCarModel.getModelCancelModel()!=null&&xsCarModel.getModelCancelModel()==1){
			cancelName="否";
		}else if(xsCarModel.getModelCancelModel()!=null&&xsCarModel.getModelCancelModel()==2){
			cancelName="是";
		}
		setContent("新增【车辆型号资料】编码为【"+xsCarModel.getModelCode()+"】,品牌为【"+carBrand.getDataValue()+"】," +
				"型号名称为【"+xsCarModel.getModelName()+"】,成本价为【"+xsCarModel.getModelCostPrice()+"】," +
				"销售价为【"+xsCarModel.getModelSalesPrice()+"】,销售限价为【"+xsCarModel.getModelSalesLimitPrice()+"】," +
				"二级销售限价为【"+xsCarModel.getModelTwoSalesLimitPrice()+"】," +
				"三级销售限价为【"+xsCarModel.getModelThreeSalesLimitPrice()+"】," +
				"是否取消型号为【"+cancelName+"】的信息！");
	}
	
	@Log(systemName="销售系统", moduleName="车辆型号资料",opertype="修改")
	public void updateCarModel(CarModelVo carModelVo) throws Exception {
		List lst=carInfoDAO.find(" from  XsCarModel  c where c.modelId="+carModelVo.getModelId());
		if(lst!=null && lst.size()>0){
			XsCarModel xsCarModel=(XsCarModel) lst.get(0);
			//BeanUtils.copyProperties(carModelVo, xsCarModel);
			xsCarModel.setCarBrand(carModelVo.getCarBrand());
			xsCarModel.setModelExamineApprove(carModelVo.getModelExamineApprove());
			xsCarModel.setModelCancelModel(carModelVo.getModelCancelModel());
			xsCarModel.setModelCode(carModelVo.getModelCode());
			xsCarModel.setModelName(carModelVo.getModelName());
			xsCarModel.setModelCostPrice(carModelVo.getModelCostPrice());
			xsCarModel.setModelSalesPrice(carModelVo.getModelSalesPrice());
			xsCarModel.setModelSalesLimitPrice(carModelVo.getModelSalesLimitPrice());
			xsCarModel.setModelTwoSalesLimitPrice(carModelVo.getModelTwoSalesLimitPrice());
			xsCarModel.setModelThreeSalesLimitPrice(carModelVo.getModelThreeSalesLimitPrice());
			xsCarModel.setModelNormsModel(carModelVo.getModelNormsModel());
			xsCarModel.setModelRemark(carModelVo.getModelRemark());
			carModelDAO.merge(xsCarModel);
			XsChilddictionary carBrand=baseTagDAO.findById(xsCarModel.getCarBrand());
			String cancelName="";
			if(xsCarModel.getModelCancelModel()!=null&&xsCarModel.getModelCancelModel()==1){
				cancelName="否";
			}else if(xsCarModel.getModelCancelModel()!=null&&xsCarModel.getModelCancelModel()==2){
				cancelName="是";
			}
			setContent("修改编码为【"+xsCarModel.getModelCode()+"】的【车辆型号资料】信息,品牌为【"+carBrand.getDataValue()+"】," +
					"型号名称为【"+xsCarModel.getModelName()+"】,成本价为【"+xsCarModel.getModelCostPrice()+"】," +
					"销售价为【"+xsCarModel.getModelSalesPrice()+"】,销售限价为【"+xsCarModel.getModelSalesLimitPrice()+"】," +
					"二级销售限价为【"+xsCarModel.getModelTwoSalesLimitPrice()+"】," +
					"三级销售限价为【"+xsCarModel.getModelThreeSalesLimitPrice()+"】," +
					"是否取消型号为【"+cancelName+"】！");
		}
		
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
	public List<CarModelVo> findCarModel(CarModelVo carModelVo) throws Exception {
		List<XsCarModel> models=carModelDAO.find("from XsCarModel model where  model.enterpriseId="+carModelVo.getEnterpriseId()+"  and model.modelId= '"+carModelVo.getModelId()+"' and " +
				"model.modelName like '%"+StringEscapeUtils.escapeSql(carModelVo.getCarMName().trim())+"%'");
		List<CarModelVo> lst=new ArrayList<CarModelVo>(); 
		if(models!=null && models.size()>0){
			for(XsCarModel model:models){
				CarModelVo mVo=new CarModelVo();
				BeanUtils.copyProperties(model, mVo);
				lst.add(mVo);
			}
		}
		return lst;
	}
	public List<ComboBoxJson>findAllCarModel(CarModelVo model) throws Exception{
		String hql="from XsCarModel model where 1=1 and model.enterpriseId="+model.getEnterpriseId();
		if(model.getQ()!=null && !("".equals(model.getQ()))){
			hql+="	and model.modelName like '%"+StringEscapeUtils.escapeSql(model.getQ().trim())+"%'";
		}
		if(model.getIsCancle()!=null&&model.getIsCancle()==true){
			hql+=" and model.modelCancelModel!=2";
		}
		List<XsCarModel> childs=carModelDAO.find(hql);
		
		List<ComboBoxJson> models=new ArrayList<ComboBoxJson>();
		if(childs!=null && childs.size()>0){
			for(XsCarModel  mo:childs){
				ComboBoxJson cbs=new ComboBoxJson();
				cbs.setId(mo.getModelId().toString());
				cbs.setText(mo.getModelName());
				models.add(cbs);
			}
		}
		return models;
	}
	
	public List<ComboBoxJson> findCarModelByBrand(CarModelVo carModelVo) throws Exception {
		Integer brandId=carModelVo.getCbrdId();
		String sql="from XsCarModel model where 1=1 " +
				" and model.xsChilddictionary.childId="+brandId;
		if(carModelVo.getEnterpriseId()!=null&&!"".equals(carModelVo.getEnterpriseId())){
			sql+=" and model.enterpriseId="+carModelVo.getEnterpriseId()+"";
		}
		if(carModelVo.getIsCancle()!=null&&carModelVo.getIsCancle()==true){
			sql+=" and modelCancelModel!=2";
		}
		List<XsCarModel> childs=carModelDAO.find(sql);
		
		List<ComboBoxJson> models=new ArrayList<ComboBoxJson>();
		if(childs!=null && childs.size()>0){
			for(XsCarModel  model:childs){
				ComboBoxJson cbs=new ComboBoxJson();
				cbs.setId(model.getModelId().toString());
				cbs.setText(model.getModelName());
				models.add(cbs);
			}
		}
		return models;
	}
	
	public List<ComboBoxJson> findBrandByModel(CarModelVo carModelVo)
			throws Exception {
		Integer modelId=carModelVo.getCtypeId();
		List<XsCarModel> childs=carModelDAO.find("from XsCarModel model where model.enterpriseId="+carModelVo.getEnterpriseId()+"  and model.modelId="+modelId);
		List<ComboBoxJson> models=new ArrayList<ComboBoxJson>();
		if(childs!=null && childs.size()>0){
			for(XsCarModel  model:childs){
				ComboBoxJson cbs=new ComboBoxJson();
				cbs.setId(model.getCarBrand().toString());
				cbs.setText(baseTagDAO.findById(model.getCarBrand()).getDataValue());
				models.add(cbs);
			}
		}
		return models;
	}
}
