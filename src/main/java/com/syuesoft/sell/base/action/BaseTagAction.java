package com.syuesoft.sell.base.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.base.service.BaseTagService;
import com.syuesoft.sell.base.vo.ChilddictionaryVo;
import com.syuesoft.sell.base.vo.ParentdictionaryVo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsParentdictionary;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;
/**
 * 基础资料action
 * @author LiWeijun    
 */
@ParentPackage(value = "basePackage")
@Action("baseTagAction")
public class BaseTagAction extends BaseAction implements ModelDriven<ChilddictionaryVo>{
	private final Logger logger = Logger.getLogger(this.getClass());
	private ChilddictionaryVo childdictionaryVo = new ChilddictionaryVo();
	@Autowired
	private BaseTagService baseTagService;
	
	/**
	 * 增加父级数据字典信息
	 */
	public void addBasParentdictionary(){
		Msg msg = new Msg();
		try {
			String uinPkey=childdictionaryVo.getPdataKey();
			if(baseTagService.findPkey(uinPkey)){
				msg.setMsg("对不起，您输入的键名已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
				childdictionaryVo.setEnterpriseId(getUserEnterpriseId());
			
			baseTagService.addParentElement(convertChildToParent(childdictionaryVo));
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 删除父级数据字典信息
	 */
	public void deleteBasParentdictionary(){
		Message msg = null;
		try {
			msg = baseTagService.deleteParentElement(convertChildToParent(childdictionaryVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 修改父级数据字典信息
	 */
	public void updateBasParentdictionary(){
		Msg msg = new Msg();
		try {
			//childdictionaryVo.setEnterpriseId(getUserEnterpriseId());
			ParentdictionaryVo parentVo= convertChildToParent(childdictionaryVo);
			String uinPkey=parentVo.getDataKey();
			Integer parentId=parentVo.getParentId();
			if(baseTagService.findPkey(uinPkey,parentId)){
				msg.setMsg("对不起，您输入的键名已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			baseTagService.updateParentElement(parentVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 *根据创建者查找所有父级数据字典信息
	 */
	public void findAllParentdictionanry(){
		try {
			//childdictionaryVo.setEnterpriseId(getNowEnterpriseId());
			ParentdictionaryVo parentVo= convertChildToParent(childdictionaryVo);
			List<XsParentdictionary> list =baseTagService.findAllParentElement(parentVo);
			List<ChilddictionaryVo>rows=new ArrayList<ChilddictionaryVo>();
			if(list!=null && list.size()>0){
				for(XsParentdictionary parent:list){
					ChilddictionaryVo cVo=new ChilddictionaryVo();
					cVo.setPparentId(parent.getParentId());
					cVo.setPstfId(parent.getStfId());
					cVo.setStfName(baseTagService.getUserNameById(parent.getStfId()));
					cVo.setPcreateTime(parent.getCreateTime());
					cVo.setPdataKey(parent.getDataKey());
					cVo.setPdataValue(parent.getDataValue());
					rows.add(cVo);
				}
			}
			/** list用于存储当前页面数据 */
			List<XsParentdictionary> lt = baseTagService.findAllParentCount(parentVo);
			/** lt用于存储所有页面数据 */
			Json json = new Json();
			json.setTotal(lt.size());
			json.setRows(rows);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error("根据创建者查找所有父级数据字典信息失败！", e);
		}
	}
	/**
	 *增加子级数据字典信息
	 */
	public void addBasChilddictionary(){
		Msg msg = new Msg();
		try {
			childdictionaryVo.setEnterpriseId(getNowEnterpriseId());
			childdictionaryVo.setCreateTime(new Timestamp(new Date().getTime()));
			childdictionaryVo.setStfId(this.getUsers().getUserId());
			String uinCkey=childdictionaryVo.getDataKey();
			Integer parentId=childdictionaryVo.getParentId();
			if(baseTagService.findCkey(uinCkey, parentId)){
				msg.setMsg("对不起，您输入的编码已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			baseTagService.addChildrenElement(childdictionaryVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 *删除子级数据字典信息
	 */
	public void deleteBasChilddictionary(){
		Message msg = null;
		try {
			childdictionaryVo.setEnterpriseId(getNowEnterpriseId());
			msg = baseTagService.deleteChildrenElement(childdictionaryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 *基础数据：修改子级数据字典信息
	 */
	public void updateBasChilddictionary(){
		Msg msg = new Msg();
		try {
			String uinCkey=childdictionaryVo.getDataKey();
			Integer parentId=childdictionaryVo.getParentId();
			Integer childId=childdictionaryVo.getChildId();
			String cValue=childdictionaryVo.getDataValue();
			childdictionaryVo.setEnterpriseId(getUserEnterpriseId());
			if(baseTagService.findCkey(uinCkey, parentId,childId,cValue,getNowEnterpriseId())){
				msg.setMsg("对不起，您输入的编码或名称已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			baseTagService.updateChildrenElement(childdictionaryVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 *根据父级数据字典编号查找所有子级数据字典信息
	 */
	public void findAllBasChilddictionary(){
		try {
			childdictionaryVo.setEnterpriseId(getNowEnterpriseId());
			List<XsChilddictionary> list =baseTagService.findAllChildrenElement(childdictionaryVo);
			List<ChilddictionaryVo> rows=new ArrayList<ChilddictionaryVo>();
			if(list!=null && list.size()>0){
				for(XsChilddictionary child:list){
					ChilddictionaryVo childVo =new ChilddictionaryVo();
					childVo.setChildId(child.getChildId());
					childVo.setParentId(child.getParentId());
					childVo.setStfId(child.getStfId());
					childVo.setStfName(baseTagService.getUserNameById(child.getStfId()));
					childVo.setCreateTime(child.getCreateTime());
					childVo.setDataKey(child.getDataKey());
					childVo.setDataValue(child.getDataValue());
					childVo.setRemark(child.getRemark());
					rows.add(childVo);
				}
			}
			/** list用于存储当前页面数据 */
			List<XsChilddictionary> lt = baseTagService.findAllcount(childdictionaryVo);
			/** lt用于存储所有页面数据 */
			Json json = new Json();
			json.setTotal(lt.size());
			json.setRows(rows);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error("根据父级数据字典编号查找所有子级数据字典信息失败！", e);
		}
		
	}
	//基础数据设置：添加
	public void  addchiledDictionary(){
		BasUsers user=this.getUsers();
		Msg msg = new Msg();
		try {	
			List<XsParentdictionary>lst=baseTagService.findParent(childdictionaryVo.getPdataKey());
			childdictionaryVo.setEnterpriseId(getUserEnterpriseId());//企业编号
			childdictionaryVo.setCreateTime(new Timestamp(new Date().getTime()));
			childdictionaryVo.setStfId(this.getUsers().getUserId());
			String uniKey=childdictionaryVo.getDataKey();
			String uniPkey=childdictionaryVo.getPdataKey();
			
			if(lst!=null && lst.size()>0){
				XsParentdictionary parent=lst.get(0);
				childdictionaryVo.setParentId(parent.getParentId());
				String lable=childdictionaryVo.getLable();
				if(lable!=null && !("".equals(lable))/** && "地区名称".equals(lable)*/){
					if(baseTagService.findCkey(uniKey, childdictionaryVo.getDataValue(),childdictionaryVo.getParentId(),getNowEnterpriseId())){
						msg.setMsg("对不起，您输入的编码或名称已存在，请重新输入！");
						super.writeJson(msg);
						return;
					}
				}
				baseTagService.addChildrenElement(childdictionaryVo);
			}else{
				XsParentdictionary b=new XsParentdictionary();
				b.setDataKey(childdictionaryVo.getPdataKey());
				b.setDataValue(childdictionaryVo.getPdataValue());
				b.setCreateTime(new Timestamp(new Date().getTime()));
				b.setStfId(this.getUsers().getUserId());
				baseTagService.addParent(childdictionaryVo ,b);
			}
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/*基础数据设置：根据不同的树节点查询数据
	 * */
	public void baseListData(){
		try {
				childdictionaryVo.setEnterpriseId(getNowEnterpriseId());	
			List list =baseTagService.findAllChiledByParentId(childdictionaryVo);
			List<ChilddictionaryVo> rows = new ArrayList<ChilddictionaryVo>();
			if(list!=null && list.size()>0){
				Object[] obj = null;
				for (int i = 0; i < list.size(); i++) {
					obj = (Object[])list.get(i);
					ChilddictionaryVo childVo =new ChilddictionaryVo();
					childVo.setChildId(obj[0]!=null ? Integer.parseInt(obj[0]+"") :null);
					childVo.setParentId(obj[1]!=null ? Integer.parseInt(obj[1]+"") :null);
					childVo.setStfId(obj[2]!=null ? Long.parseLong(obj[2]+"") :null);
					childVo.setStfName(baseTagService.getUserNameById(obj[2]!=null ? Long.parseLong(obj[2]+"") :null));
					childVo.setCreateTime(FormatTime.str2Timestamp(obj[3]!=null ? obj[3]+"" :""));
					childVo.setDataKey(obj[4]!=null ? obj[4]+"" :"");
					childVo.setDataValue(obj[5]!=null ? obj[5]+"" :"");
					childVo.setRemark(obj[6]!=null ? obj[6]+"" :"");
					rows.add(childVo);
				}
			}
			/** list用于存储当前页面数据 */
			List<XsChilddictionary> lt = baseTagService.findAllChiled(childdictionaryVo);
			/** lt用于存储所有页面数据 */
			Json json = new Json();
			json.setTotal(lt.size());
			json.setRows(rows);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error("根据不同的树节点查询数据失败！", e);
		}
	}
	//根据id和value获取子表数据
	public void findChildByValue(){
		try {
			
			super.writeJson(baseTagService.findChildByValue(childdictionaryVo));
		} catch (Exception e) {
			logger.error("根据id和value获取子表数据失败！", e);
		}
	}
	public ParentdictionaryVo  convertChildToParent(ChilddictionaryVo childDictionary){
		ParentdictionaryVo parent=new ParentdictionaryVo();
		if(childDictionary.getPparentId()!=null && !("".equals(childDictionary.getPparentId()))){
			parent.setParentId(childDictionary.getPparentId());
		}
		if(childDictionary.getPdataKey()!=null && !("".equals(childDictionary.getPdataKey()))){
			parent.setDataKey(childDictionary.getPdataKey());
		}
		if(childDictionary.getPdataValue()!=null && !("".equals(childDictionary.getPdataValue()))){
			parent.setDataValue(childDictionary.getPdataValue());
		}
		if(childDictionary.getPremark()!=null && !("".equals(childDictionary.getPremark()))){
			parent.setRemark(childDictionary.getPremark());
		}
		if(childDictionary.getPstfId()!=null && !("".equals(childDictionary.getPstfId()))){
			parent.setStfId(childDictionary.getPstfId());
		}else{
			parent.setStfId(this.getUsers().getUserId());
		}
		if(childDictionary.getPcreateTime()!=null && !("".equals(childDictionary.getPcreateTime()))){
			parent.setCreateTime(childDictionary.getPcreateTime());
		}else{
			parent.setCreateTime(new Timestamp(new Date().getTime()));
		}
		if(childDictionary.getEnterpriseId()!=null && !("".equals(childDictionary.getEnterpriseId()))){
			parent.setEnterpriseId(childDictionary.getEnterpriseId());
		}
		//分页
		parent.setSort(childdictionaryVo.getSort());
		parent.setPage(childdictionaryVo.getPage());
		parent.setRows(childdictionaryVo.getRows());
		parent.setOrder(childdictionaryVo.getOrder());
		//查询
		parent.setPqueryKey(childdictionaryVo.getPqueryKey());
		parent.setPqueryValue(childdictionaryVo.getPqueryValue());
		return parent;
	}
	/**
	 * 查询系统公用基础数据
	 * */
	/*public void findCommonBaseData(){
		try {
			super.writeJson(baseTagService.findChilds(childdictionaryVo.getBaseKey(),childdictionaryVo.getQ(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("查询系统公用基础数据失败!", e);
		}
	}*/
	/**
	 * 查询系统公用基础数据
	 * */
	public void  findBaseData(){
		try {
			super.writeJson(baseTagService.findChilds(childdictionaryVo.getBaseKey(),childdictionaryVo.getQ(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("查询基础数据失败!", e);
		}
	}
	public void  findBase(){
		try {
			super.writeJson(baseTagService.findParameter(childdictionaryVo.getBaseKey(),childdictionaryVo.getQ(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("查询基础数据失败!", e);
		}
	}
/*	public void findSellPerson(){
		try {
			super.writeJson(baseTagService.findUsers(this.getUsers()));
		} catch (Exception e) {
			logger.error("查询业务员信息失败!", e);
		}
	}*/
	public void findYb(){
		try {
			super.writeJson(baseTagService.findChild(childdictionaryVo.getDataValue(),childdictionaryVo.getPdataKey(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("查询邮编信息失败!", e);
		}
	}
	/**
	 * 获取具体的数据字典编号
	 * */
	public void getDataByChildDataKey(){
		try {
			super.writeJson(baseTagService.getChildId(childdictionaryVo.getPdataKey(),childdictionaryVo.getDataKey(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("获取具体的数据字典值失败!", e);
			e.printStackTrace();
		}
	}
	
	
	public ChilddictionaryVo getModel() {
		// TODO Auto-generated method stub
		return childdictionaryVo;
	}

}
