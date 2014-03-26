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
import com.syuesoft.sell.base.dao.SupplierInfoDAO;
import com.syuesoft.sell.base.service.SupplierInfoService;
import com.syuesoft.sell.base.vo.SupplierInfoVo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSupplierInfo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Msg;
@Service("supplierInfoService")
public class SupplierInfoServiceImpl  extends BaseLogServiceImpl  implements SupplierInfoService {
	
	private SupplierInfoDAO supplierInfoDAO;
	private BaseTagDAO baseTagDAO;
	
	/**
	 * 查询供应商
	 */
	
	public Datagrid getPager(SupplierInfoVo supplierInfoVo) throws Exception{
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsSupplierInfo supplierInfo where 1=1 " +
				"and supplierInfo.enterprise_id="+supplierInfoVo.getEnterprise_id());
		if(supplierInfoVo.getSupName()!=null && !("".equals(supplierInfoVo.getSupName()))){
			hql.append(" and  supplierInfo.supplierName like '%"+StringEscapeUtils.escapeSql(supplierInfoVo.getSupName().trim())+"%'");
		}
		if(supplierInfoVo.getSupTel()!=null && !("".equals(supplierInfoVo.getSupTel()))){
			hql.append(" and  supplierInfo.supplierTelephone like '%"+StringEscapeUtils.escapeSql(supplierInfoVo.getSupTel().trim())+"%'");
		}
		if(supplierInfoVo.getSupPerson()!=null && !("".equals(supplierInfoVo.getSupPerson()))){
			hql.append(" and  supplierInfo.supplierPerson like '%"+StringEscapeUtils.escapeSql(supplierInfoVo.getSupPerson().trim())+"%'");
		}
		List<XsSupplierInfo> lst=supplierInfoDAO.find(hql.toString(), supplierInfoVo.getPage(), supplierInfoVo.getRows());
		List<SupplierInfoVo> rows =new ArrayList<SupplierInfoVo>();
		if(lst!=null && lst.size()>0){
			for(XsSupplierInfo supplierInfo:lst){
				SupplierInfoVo supplierVo=new SupplierInfoVo();			
//				BeanUtils.copyProperties(supplierInfo, supplierVo);
				supplierVo.setEnterprise_id(supplierInfo.getEnterprise_id());
				supplierVo.setSupplierAddress(supplierInfo.getSupplierAddress());
				if(supplierInfo.getXsSupplierInfoBank()!=null&&!"".equals(supplierInfo.getXsSupplierInfoBank())){
					supplierVo.setSupplierBank(supplierInfo.getXsSupplierInfoBank().getChildId()+"");	
				}
				supplierVo.setSupplierBankCode(supplierInfo.getSupplierBankCode());
				supplierVo.setSupplierBuyerCredit(supplierInfo.getSupplierBuyerCredit());
				supplierVo.setSupplierCode(supplierInfo.getSupplierCode());
				supplierVo.setSupplierFax(supplierInfo.getSupplierFax());
				supplierVo.setSupplierId(supplierInfo.getSupplierId());
				supplierVo.setSupplierMakeInvphone(supplierInfo.getSupplierMakeInvphone());
				supplierVo.setSupplierMoney(supplierInfo.getSupplierMoney());
				supplierVo.setSupplierName(supplierInfo.getSupplierName());
				supplierVo.setSupplierNature(supplierInfo.getXsChilddictionary().getChildId());
				supplierVo.setSupplierPerson(supplierInfo.getSupplierPerson());
				supplierVo.setSupplierPhone(supplierInfo.getSupplierPhone());
				supplierVo.setSupplierRemark(supplierInfo.getSupplierRemark());
				supplierVo.setSupplierRevenue(supplierInfo.getSupplierRevenue());
				supplierVo.setSupplierTelephone(supplierInfo.getSupplierTelephone());
				supplierVo.setSupplierNumber(supplierInfo.getSupplierNumber());
				
				if(supplierInfo.getXsSupplierInfoBank()!=null&&!"".equals(supplierInfo.getXsSupplierInfoBank())){
					XsChilddictionary supper=baseTagDAO.findById(supplierInfo.getXsSupplierInfoBank().getChildId());	
					if(supper!=null){
					supplierVo.setSupplierBankName(supper.getDataValue());	
					}
				}
				if(null!=supplierInfo.getXsChilddictionary() && !("".equals(supplierInfo.getXsChilddictionary()))){
					XsChilddictionary child=baseTagDAO.findById(supplierInfo.getXsChilddictionary().getChildId());
					if(child!=null){
						supplierVo.setSupplierNatureName(child.getDataValue());
					}
				}
				rows.add(supplierVo);
			}	
		}
		int total = supplierInfoDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;	
	}
	
	@Log(systemName="销售系统", moduleName="供应商档案",opertype="删除")
	public Msg deleteSupplierInfo(SupplierInfoVo supplierInfoVo) throws Exception{	
		Msg msg = new Msg();
		List list=supplierInfoDAO.find(" from XsSellInstorehouse where enterprise_id="+supplierInfoVo.getEnterprise_id()+" " +
				"and supplierId="+supplierInfoVo.getSupplierId());
		if(list!=null){
			msg.setMsg("该供应商档案已被入库单使用不可以删除！");
			msg.setSuccess(false);
			
		}else{
			XsSupplierInfo xsSupplierInfo=supplierInfoDAO.get(" from XsSupplierInfo where supplierId="+supplierInfoVo.getSupplierId());
			supplierInfoDAO.delete(xsSupplierInfo);
			setContent("删除【供应商档案】编码为【"+xsSupplierInfo.getSupplierNumber()+"】," +
					"名称为【"+xsSupplierInfo.getSupplierName()+"】！");
			msg.setMsg("删除成功！");
			msg.setSuccess(true);
		}
		return msg;
	}
	/**
	 * 保存供应商档案
	 */
	
	@Log(systemName="销售系统", moduleName="供应商档案",opertype="新增")
	public void addSupplierInfo(SupplierInfoVo vo) throws Exception{
		XsSupplierInfo xsSupplierInfo=new XsSupplierInfo();
//		BeanUtils.copyProperties(supplierInfoVo, xsSupplierInfo);
		xsSupplierInfo.setEnterprise_id(vo.getEnterprise_id());
		xsSupplierInfo.setSupplierAddress(vo.getSupplierAddress());
		XsChilddictionary bank=null;
		if(vo.getSupplierBank()!=null&&!"".equals(vo.getSupplierBank())){
			bank=baseTagDAO.findById(Integer.parseInt(vo.getSupplierBank()));
		}
		xsSupplierInfo.setXsSupplierInfoBank(bank);
		xsSupplierInfo.setSupplierBankCode(vo.getSupplierBankCode());
		xsSupplierInfo.setSupplierBuyerCredit(vo.getSupplierBuyerCredit());
		xsSupplierInfo.setSupplierCode(vo.getSupplierCode());
		xsSupplierInfo.setSupplierFax(vo.getSupplierFax());
		//xsSupplierInfo.setSupplierId(vo.getSupplierId());
		xsSupplierInfo.setSupplierMakeInvphone(vo.getSupplierMakeInvphone());
		xsSupplierInfo.setSupplierMoney(vo.getSupplierMoney());
		xsSupplierInfo.setSupplierName(vo.getSupplierName());
		XsChilddictionary nature=null;
		if(vo.getSupplierNature()!=null&&!"".equals(vo.getSupplierNature())){
			nature=baseTagDAO.findById(vo.getSupplierNature());
		}
		xsSupplierInfo.setXsChilddictionary(nature);
		xsSupplierInfo.setSupplierPerson(vo.getSupplierPerson());
		xsSupplierInfo.setSupplierPhone(vo.getSupplierPhone());
		xsSupplierInfo.setSupplierRemark(vo.getSupplierRemark());
		xsSupplierInfo.setSupplierRevenue(vo.getSupplierRevenue());
		xsSupplierInfo.setSupplierTelephone(vo.getSupplierTelephone());
		xsSupplierInfo.setSupplierNumber(CreateID.createId("SupplierInfo", Contstants.SELL_BILLSDEPLOY.SUPPLIERINFOR));
		supplierInfoDAO.save(xsSupplierInfo);
		setContent("新增【供应商档案】编码为【"+xsSupplierInfo.getSupplierNumber()+"】," +
				"名称为【"+xsSupplierInfo.getSupplierName()+"】,手机号为【"+xsSupplierInfo.getSupplierPhone()+"】," +
						"属性为【"+nature.getDataValue()+"】的信息！");
	}
	
	
	
	@Log(systemName="销售系统", moduleName="供应商档案",opertype="修改")
	public void updateSupplierInfo(SupplierInfoVo supplierInfoVo) throws Exception{
		XsSupplierInfo xsSupplierInfo=supplierInfoDAO.get(" from XsSupplierInfo where supplierId="+supplierInfoVo.getSupplierId());
		xsSupplierInfo.setSupplierAddress(supplierInfoVo.getSupplierAddress());
		XsChilddictionary bank=null;
		if(supplierInfoVo.getSupplierBank()!=null&&!"".equals(supplierInfoVo.getSupplierBank())){
			bank=baseTagDAO.findById(Integer.parseInt(supplierInfoVo.getSupplierBank()));
		}
		xsSupplierInfo.setXsSupplierInfoBank(bank);
		xsSupplierInfo.setSupplierBankCode(supplierInfoVo.getSupplierBankCode());
		xsSupplierInfo.setSupplierBuyerCredit(supplierInfoVo.getSupplierBuyerCredit());
		xsSupplierInfo.setSupplierCode(supplierInfoVo.getSupplierCode());
		xsSupplierInfo.setSupplierFax(supplierInfoVo.getSupplierFax());
		xsSupplierInfo.setSupplierId(supplierInfoVo.getSupplierId());
		xsSupplierInfo.setSupplierMakeInvphone(supplierInfoVo.getSupplierMakeInvphone());
		xsSupplierInfo.setSupplierMoney(supplierInfoVo.getSupplierMoney());
		xsSupplierInfo.setSupplierName(supplierInfoVo.getSupplierName());
		XsChilddictionary nature=null;
		if(supplierInfoVo.getSupplierNature()!=null&&!"".equals(supplierInfoVo.getSupplierNature())){
			nature=baseTagDAO.findById(supplierInfoVo.getSupplierNature());
		}
		xsSupplierInfo.setXsChilddictionary(nature);
		xsSupplierInfo.setSupplierPerson(supplierInfoVo.getSupplierPerson());
		xsSupplierInfo.setSupplierPhone(supplierInfoVo.getSupplierPhone());
		xsSupplierInfo.setSupplierRemark(supplierInfoVo.getSupplierRemark());
		xsSupplierInfo.setSupplierRevenue(supplierInfoVo.getSupplierRevenue());
		xsSupplierInfo.setSupplierTelephone(supplierInfoVo.getSupplierTelephone());
		//xsSupplierInfo.setSupplierNumber(CreateID.createId("SupplierInfo", Contstants.SELL_BILLSDEPLOY.SUPPLIERINFOR));
		supplierInfoDAO.merge(xsSupplierInfo);	
		//XsChilddictionary supplier=baseTagDAO.findById(xsSupplierInfo.getSupplierNature());
		setContent("修改编码为【"+xsSupplierInfo.getSupplierNumber()+"】的【供应商档案】," +
				"名称为【"+xsSupplierInfo.getSupplierName()+"】,手机号为【"+xsSupplierInfo.getSupplierPhone()+"】," +
						"属性为【"+nature.getDataValue()+"】！");
	}
	
	public Boolean existSupplierTwo(String  suppliername,Integer enId ) throws Exception{
		if(suppliername!=null && !("".equals(suppliername))){
			String hql="from XsSupplierInfo supplierInfo where " +
					" supplierInfo.supplierName='"+suppliername+"' and supplierInfo.enterprise_id="+enId;
			List list = supplierInfoDAO.find(hql);
			if(list !=null){
				return true;
			}
		}
		return false;	
	} 
	public Boolean existSupplier(String  suppliername,Integer supplierId,Integer enid ) throws Exception{
		if(suppliername!=null && !("".equals(suppliername))){
			String hql="from XsSupplierInfo supplierInfo where 1=1 and supplierInfo.supplierName='"+suppliername+"' and " +
					"supplierInfo.supplierId!='"+supplierId+"' and supplierInfo.enterprise_id="+enid;
			XsSupplierInfo xsSupplierInfo=supplierInfoDAO.get(hql);
			if(xsSupplierInfo !=null){
				return true;
			}
		}
		return false;	
	} 
	/**
	 * 获取
	 */
	
	public List<ComboBoxJson> findAllSupplier(SupplierInfoVo supplierInfoVo) throws Exception {
		List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		List<XsSupplierInfo> bctList = supplierInfoDAO.find("from XsSupplierInfo supplierInfo " +
				"where 1=1 and supplierInfo.enterprise_id="+supplierInfoVo.getEnterprise_id());
		if(bctList != null && bctList.size() > 0){
			for(XsSupplierInfo supplier : bctList){
				ComboBoxJson json = new ComboBoxJson();
				json.setId(supplier.getSupplierId().toString());
				json.setText(supplier.getSupplierName());
				list.add(json);
			}
		}
		return list;
	}
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}
	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}
	public SupplierInfoDAO getSupplierInfoDAO() {
		return supplierInfoDAO;
	}
	@Autowired
	public void setSupplierInfoDAO(SupplierInfoDAO supplierInfoDAO) {
		this.supplierInfoDAO = supplierInfoDAO;
	}	
}
