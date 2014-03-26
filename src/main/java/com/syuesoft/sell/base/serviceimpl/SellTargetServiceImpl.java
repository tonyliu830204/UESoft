package com.syuesoft.sell.base.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.dao.SellTargetDAO;
import com.syuesoft.sell.base.service.SellTargetService;
import com.syuesoft.sell.base.vo.SellTargetVo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellTargetSet;
import com.syuesoft.util.TreeJson;

@Service("sellTargetService")
public class SellTargetServiceImpl extends BaseLogServiceImpl implements SellTargetService {
	private SellTargetDAO sellTargetDAO;
	private BaseTagDAO baseTagDAO;
	@Autowired
	private BasStuffDao  basStuffDao;
	public List<TreeJson> retriveTree(Integer enterprise_id) throws Exception{
		List<TreeJson> trees=new ArrayList<TreeJson>();
		List<Object []> depts=sellTargetDAO.findAllDept(enterprise_id);
		if(depts!=null && depts.size()>0){
			for(Object[] b:depts){
				TreeJson dt=new TreeJson();
				dt.setId(b[0].toString());
				dt.setText(b[1].toString());
				List<TreeJson> childs=new ArrayList<TreeJson>();
				List<Object[]> stuffs=sellTargetDAO.findStuffByDept( Short.valueOf(b[0].toString()),enterprise_id);
				if(stuffs!=null && stuffs.size()>0){
					for(Object[] s:stuffs){
						TreeJson stf=new TreeJson();
						stf.setId(s[0].toString());
						stf.setText(s[1].toString());
						childs.add(stf);
					}
				}
				dt.setChildren(childs);
				trees.add(dt);
			}
		}
		return trees;
		
	}
	public Datagrid getPager(SellTargetVo targetVo) throws Exception{
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsSellTargetSet target where 1=1 and " +
				"target.stfId='"+targetVo.getStfId()+"' and target.enterprise_id="+targetVo.getEnterprise_id() );
		List<BaseBean> lst=sellTargetDAO.find(hql.toString(), targetVo.getPage(), targetVo.getRows());
		List<SellTargetVo> rows =new ArrayList<SellTargetVo>();
		if(lst!=null && lst.size()>0){
			for(BaseBean bs:lst){
				XsSellTargetSet target=(XsSellTargetSet)bs;
				SellTargetVo  tVo=new SellTargetVo();
				BeanUtils.copyProperties(target, tVo);
				if(target.getBrandType()!=null && !("".equals(target.getBrandType()))){
					XsChilddictionary child=baseTagDAO.findById(Integer.parseInt(target.getBrandType()));
					if(child!=null){
						tVo.setBrandName(child.getDataValue());
					}
				}
				if(targetVo.getStfId()!=null && !("".equals(targetVo.getStfId()))){
					List<BasStuff> us=baseTagDAO.getUserNameById((long)(targetVo.getStfId()));
					if(us!=null && us.size()>0){
						tVo.setStfName(us.get(0).getStfName());
					}
				}
				rows.add(tVo);
			}
		}
		int total = sellTargetDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	@Log(systemName="销售系统", moduleName="销售指标设定",opertype="新增")
	public void saveSellTarget(SellTargetVo targetVo) throws Exception{
		XsSellTargetSet sellTargetSet=new XsSellTargetSet();
		BeanUtils.copyProperties(targetVo, sellTargetSet);
		BaseBean bas=(BaseBean)sellTargetSet;
		sellTargetDAO.save(bas);
		XsChilddictionary brand=baseTagDAO.findById(Integer.parseInt(sellTargetSet.getBrandType()));
 	   BasStuff basStuff=basStuffDao.get("from BasStuff where stfId="+sellTargetSet.getStfId());
		setContent("给业务员【"+basStuff.getStfName()+"】新增【销售指标设定】信息,时间为【"+sellTargetSet.getData()+"】," +
				"品牌为【"+brand.getDataValue()+"】,销售台数为【"+sellTargetSet.getBrandType()+"】," +
						"销售金额为【"+sellTargetSet.getBrandType()+"】,销售毛利为【"+sellTargetSet.getSellProfit()+"】！");
	}
	@Log(systemName="销售系统", moduleName="销售指标设定",opertype="删除")
	public void deleteSellTarget(SellTargetVo targetVo) throws Exception{
		XsSellTargetSet sellTargetSet=new XsSellTargetSet();
		BeanUtils.copyProperties(targetVo, sellTargetSet);
		BaseBean bas=(BaseBean)sellTargetSet;
		sellTargetDAO.delete(bas);
		XsChilddictionary brand=baseTagDAO.findById(Integer.parseInt(sellTargetSet.getBrandType()));
		 BasStuff basStuff=basStuffDao.get("from BasStuff where stfId="+sellTargetSet.getStfId());
		setContent("删除业务员【"+basStuff.getStfName()+"】的【销售指标设定】信息,时间为【"+sellTargetSet.getData()+"】," +
				"品牌为【"+brand.getDataValue()+"】！");
	}
	@Log(systemName="销售系统", moduleName="销售指标设定",opertype="修改")
	public void updateSellTarget(SellTargetVo targetVo)throws Exception{
		XsSellTargetSet sellTargetSet=new XsSellTargetSet();
		BeanUtils.copyProperties(targetVo, sellTargetSet);
		BaseBean bas=(BaseBean)sellTargetSet;
		sellTargetDAO.merge(bas);
		XsChilddictionary brand=baseTagDAO.findById(Integer.parseInt(sellTargetSet.getBrandType()));
	 	   BasStuff basStuff=basStuffDao.get("from BasStuff where stfId="+sellTargetSet.getStfId());
			setContent("修改业务员【"+basStuff.getStfName()+"】的【销售指标设定】信息,时间为【"+sellTargetSet.getData()+"】," +
					"品牌为【"+brand.getDataValue()+"】,销售台数为【"+sellTargetSet.getBrandType()+"】," +
							"销售金额为【"+sellTargetSet.getBrandType()+"】,销售毛利为【"+sellTargetSet.getSellProfit()+"】！");
		
	}
	public SellTargetDAO getSellTargetDAO() {
		return sellTargetDAO;
	}
	@Autowired
	public void setSellTargetDAO(SellTargetDAO sellTargetDAO) {
		this.sellTargetDAO = sellTargetDAO;
	}
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}
	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}
	
	
}
