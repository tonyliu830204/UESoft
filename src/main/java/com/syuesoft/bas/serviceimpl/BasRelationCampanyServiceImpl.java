package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasRelationCampanyAttrDao;
import com.syuesoft.bas.dao.BasRelationCampanyDao;
import com.syuesoft.bas.service.BasRelationCampanyService;
import com.syuesoft.base.vo.BasRelationCampanyVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasRelationCampany;
import com.syuesoft.model.BasRelationCampanyAttr;
/**
 * 相关单位service-(包含供应商和保险公司等)
 * @author SuMing
 */
@Service("basRelationCampanyService")
public class BasRelationCampanyServiceImpl extends BaseLogServiceImpl implements BasRelationCampanyService {

	private BasRelationCampanyDao basRelationCampanyDao;
	private BasRelationCampanyAttrDao basRelationCampanyAttrDao;

	/**
	 * 供应商档案datagrid
	 */
	
	public Datagrid datagridSupplierArchives(BasRelationCampanyVo brcVo) throws Exception {
		String hql = "from BasRelationCampany brc where brc.relcampFlg = 0  and brc.enterpriseId="+brcVo.getNowEnterpriseId();
		Datagrid dg = getDatagrid(brcVo, hql);
		return dg;
	}

	private Datagrid getDatagrid(BasRelationCampanyVo brcVo, String hql) throws Exception {
		Datagrid dg = new Datagrid();
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(brcVo, hql, params);
		List<BasRelationCampanyVo> rows = new ArrayList<BasRelationCampanyVo>();
		List<BasRelationCampany> brcList = basRelationCampanyDao.find(hql, params, brcVo.getPage(), brcVo.getRows());
		if(brcList != null && brcList.size() > 0){
			for(BasRelationCampany brc : brcList){
				BasRelationCampanyVo bVo = new BasRelationCampanyVo();
				BeanUtils.copyProperties(brc, bVo);
				if(brc.getAttrId() != null && !"".equals(brc.getAttrId())){
					BasRelationCampanyAttr brca = basRelationCampanyAttrDao.get(BasRelationCampanyAttr.class, brc.getAttrId());
					if(brca != null){
						bVo.setAttrName(brca.getAttrName());
					}
				}
				rows.add(bVo);
			}
		}
		int total = basRelationCampanyDao.getCount(hql, params);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

	private String addWhere(BasRelationCampanyVo brcVo, String hql, Map<String, Object> params) {
		if(brcVo.getRelcampName() != null && !"".equals(brcVo.getRelcampName().trim())){
			params.put("relcampName", "%%" + StringEscapeUtils.escapeSql(brcVo.getRelcampName().trim()) + "%%");
			hql += "and (brc.relcampName like :relcampName or brc.relcampJm like :relcampName) ";
		}
		if(brcVo.getRelcampAddr() != null && !"".equals(brcVo.getRelcampAddr().trim())){
			params.put("relcampAddr", "%%" + StringEscapeUtils.escapeSql(brcVo.getRelcampAddr().trim()) + "%%");
			hql += "and brc.relcampAddr like :relcampAddr ";
		}
		if(brcVo.getRelcampContact() != null && !"".equals(brcVo.getRelcampContact().trim())){
			params.put("relcampContact", "%%" + StringEscapeUtils.escapeSql(brcVo.getRelcampContact().trim()) + "%%");
			hql += "and brc.relcampContact like :relcampContact ";
		}
		if(brcVo.getRelcampTel1() != null && !"".equals(brcVo.getRelcampTel1().trim())){
			params.put("relcampTel1", "%%" + StringEscapeUtils.escapeSql(brcVo.getRelcampTel1().trim()) + "%%");
			hql += " and brc.relcampTel1 like :relcampTel1 ";
		}
		if(brcVo.getRelcampTel2() != null && !"".equals(brcVo.getRelcampTel2().trim())){
			params.put("relcampTel2", "%%" + StringEscapeUtils.escapeSql(brcVo.getRelcampTel2().trim()) + "%%");
			hql += " and brc.relcampTel2 like :relcampTel2 ";
		}
		if(brcVo.getRelcampRemark1() != null && !"".equals(brcVo.getRelcampRemark1().trim())){
			params.put("relcampRemark1", "%%" + StringEscapeUtils.escapeSql(brcVo.getRelcampRemark1().trim()) + "%%");
			hql += "and brc.relcampRemark1 like :relcampRemark1 ";
		}
		if(brcVo.getSort() != null && !"".equals(brcVo.getSort().trim()) && brcVo.getOrder() != null && !"".equals(brcVo.getOrder().trim())){
			hql += "order by brc." + brcVo.getSort() + " " + brcVo.getOrder();
		}
		return hql;
	}
	
	public boolean isExist(BasRelationCampanyVo brcVo,int i)throws Exception{
		String sql=" from BasRelationCampany brc where brc.relcampName='"+brcVo.getRelcampName()+"' and brc.relcampFlg="+i+"  and brc.enterpriseId="+brcVo.getEnterpriseId();
		if(brcVo.getRelcampId()!=null)
			sql+=" and brc.relcampId!='"+brcVo.getRelcampId()+"'";
		List<BasRelationCampany> result =basRelationCampanyDao.find(sql);
		if(result!=null&&result.size()>0){
			return false;
		}
		return true;
	}
	
	/**
	 * 保存供应商档案
	 */
	
	@Log(moduleName="基础资料",opertype="新增供应商档案",content="基础资料-->新增供应商档案")
	public void saveSupplierArchives(BasRelationCampanyVo brcVo) throws Exception {
		BasRelationCampany brc = new BasRelationCampany();
		BeanUtils.copyProperties(brcVo, brc);
		brc.setRelcampFlg(new Short("0"));
		Serializable bb =basRelationCampanyDao.save(brc);
		setContent("基础资料-->新增供应商档案,供应商档案编号:"+bb);
	}
	
	/**
	 * 删除供应商档案 or 保险(汽厂)档案
	 */
	
	@Log(moduleName="基础资料",opertype="删除供应商档案",content="基础资料-->删除供应商档案")
	public void remove(Short id) throws Exception {
		basRelationCampanyDao.delete(basRelationCampanyDao.get(BasRelationCampany.class, id));
		setContent("基础资料-->删除供应商档案,供应商档案编号:"+id);
	}
	
	/**
	 * 修改供应商档案
	 */
	
	@Log(moduleName="基础资料",opertype="修改供应商档案",content="基础资料-->修改供应商档案")
	public void editSupplierArchives(BasRelationCampanyVo brcVo) throws Exception {
		BasRelationCampany brc = new BasRelationCampany();
		BeanUtils.copyProperties(brcVo, brc);
		brc.setRelcampFlg(new Short("0"));
		basRelationCampanyDao.merge(brc);
		setContent("基础资料-->修改供应商档案,供应商档案编号:"+brcVo.getRelcampId());
	}
	
	/**
	 * 保险(汽厂)档案
	 */
	
	public Datagrid datagridInsuranceCarArchives(BasRelationCampanyVo brcVo) throws Exception {
		String hql = "from BasRelationCampany brc where brc.relcampFlg = 1 and brc.enterpriseId="+brcVo.getNowEnterpriseId();
		Datagrid dg = getDatagrid(brcVo, hql);
		return dg;
	}
	
	/**
	 * 保存保险(汽厂)档案
	 */
	
	public void saveInsuranceCarArchives(BasRelationCampanyVo brcVo) throws Exception {
		BasRelationCampany brc = new BasRelationCampany();
		BeanUtils.copyProperties(brcVo, brc);
		brc.setRelcampFlg(new Short("1"));
		basRelationCampanyDao.save(brc);
	}
	
	/**
	 * 修改保险(汽厂)档案
	 */
	
	public void editInsuranceCarArchives(BasRelationCampanyVo brcVo) throws Exception {
		BasRelationCampany brc = new BasRelationCampany();
		BeanUtils.copyProperties(brcVo, brc);
		brc.setRelcampFlg(new Short("1"));
		basRelationCampanyDao.merge(brc);
	}
	/**
	 * 修改保险（汽厂）属性
	 */
	
	public void editCarCompanyProperties(BasRelationCampanyVo brcVo)
			throws Exception {
		BasRelationCampanyAttr brca=new BasRelationCampanyAttr();
		BeanUtils.copyProperties(brcVo, brca);
		basRelationCampanyAttrDao.merge(brca);
	}
	
	/**
	 * 查找保险（汽厂）属性
	 */
	@SuppressWarnings("unchecked")
	
	public Datagrid findAllCarCompanyProperties(BasRelationCampanyVo brcVo) throws Exception {
		List<BasRelationCampanyAttr> rows=basRelationCampanyAttrDao.find("from BasRelationCampanyAttr bcb where bcb.enterpriseId="+brcVo.getNowEnterpriseId(),brcVo.getPage(),brcVo.getRows());
		int total=basRelationCampanyAttrDao.getCount("from BasRelationCampanyAttr bcb  where bcb.enterpriseId="+brcVo.getNowEnterpriseId());
		if(rows==null)
			rows=new ArrayList();
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	/**
	 * 删除保险（汽厂）属性
	 * */
	
	public void removeCarCompanyProperties(BasRelationCampanyVo brcVo)
			throws Exception {
		basRelationCampanyAttrDao.delete(basRelationCampanyAttrDao.get(BasRelationCampanyAttr.class, brcVo.getAttrId()));
	}
	
	/**
	 * 增加保险（汽厂）属性
	 * */
	
	public void saveCarCompanyProperties(BasRelationCampanyVo brcVo)
			throws Exception {
		BasRelationCampanyAttr brca=new BasRelationCampanyAttr();
		if(brcVo.getAttrName()!=null&&!brcVo.getAttrName().equals("")){
			brca.setAttrName(brcVo.getAttrName());
		}
		if(brcVo.getRemark()!=null&&!brcVo.getRemark().equals("")){
			brca.setRemark(brcVo.getRemark());
		}
		if(brcVo.getEnterpriseId()!=null&&!brcVo.getEnterpriseId().equals("")){
			brca.setEnterpriseId(brcVo.getEnterpriseId());
		}
		basRelationCampanyAttrDao.save(brca);
	}
	public BasRelationCampanyDao getBasRelationCampanyDao() {
		return basRelationCampanyDao;
	}
    @Autowired
    public void setBasRelationCampanyDao(BasRelationCampanyDao basRelationCampanyDao) {
		this.basRelationCampanyDao = basRelationCampanyDao;
	}
	public BasRelationCampanyAttrDao getBasRelationCampanyAttrDao() {
		return basRelationCampanyAttrDao;
	}
	@Autowired
	public void setBasRelationCampanyAttrDao(BasRelationCampanyAttrDao basRelationCampanyAttrDao) {
		this.basRelationCampanyAttrDao = basRelationCampanyAttrDao;
	}

	
	public boolean findCarCompany(BasRelationCampanyVo brcVo) throws Exception {
		List lst=basRelationCampanyAttrDao.find("from  BasRelationCampanyAttr  b where b.attrName='"+brcVo.getAttrName()+"' and  b.enterpriseId="+brcVo.getEnterpriseId());
		if(lst!=null && lst.size()>0)
			return true;
		return false;
	}
}
