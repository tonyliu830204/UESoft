package com.syuesoft.systemmanagement.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.systemmanagement.service.InventoryFindService;
import com.syuesoft.systemmanagement.vo.InventoryFindVo;
import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value="basePackage")
@Action("inventoryFindAction")
public class InventoryFindAction extends BaseAction implements ModelDriven<InventoryFindVo> {

	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
	private InventoryFindService InventoryFindService;

	private InventoryFindVo inventoryFindVo = new InventoryFindVo();

	
	public InventoryFindVo getModel() {
		return inventoryFindVo;
	}
	
	/**
	 * 件盘点查询树形结构查询 父项
	 */
	public void getFatherInfor(){
		try {
		    inventoryFindVo.setEnterpriseId(this.getUserEnterpriseId());
		    super.writeJson(InventoryFindService.getFatherInfor(inventoryFindVo));
		} catch (Exception e) {
		    logger.error("盘点查询失败", e);
		}
	}
	
	
	/**
	 * 配件盘点查询树形结构 通过配件编号查询 子项
	 */
	public void getChildInfor(){
		try {
		    inventoryFindVo.setEnterpriseId(this.getUserEnterpriseId());
		    super.writeJson(InventoryFindService.getChildInfor(inventoryFindVo));
		} catch (Exception e) {
		    logger.error("配件盘点查询失败", e);
		}
	}
	/**
	 * 查询配件档案 获取配件名称
	 */
	public String getPartsName(){
		List list = new ArrayList();
		try {
			List rlist = InventoryFindService.getPartsName();
			for (int i = 0; i < rlist.size(); i++) {
				ComboxVo vo = new ComboxVo();
				vo.setId(rlist.get(i).toString());
				vo.setName(rlist.get(i).toString());
				list.add(vo);
			}
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
