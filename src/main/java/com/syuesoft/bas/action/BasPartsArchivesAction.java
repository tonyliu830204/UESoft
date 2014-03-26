package com.syuesoft.bas.action;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.struts2.convention.annotation.ParentPackage;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasPartsArchivesService;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.model.FrtParts;
import com.syuesoft.util.Msg;

/**
 * 配件档案action
 * @author Liujian
 */
@ParentPackage(value = "basePackage")
@Action("basPartsArchivesAction")
public class BasPartsArchivesAction extends BaseAction implements
        ModelDriven<FrtPartsVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    @Autowired
    private BaseService baseService;

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private BasPartsArchivesService basPartsArchivesService;

    FrtPartsVo fpVo = new FrtPartsVo();
    /**
     * 导入配件档案
     * */
    public void modifyImportPartsArchives(){
    	Msg msg=new Msg();
    	try {
    		fpVo.setEnterpriseId(getNowEnterpriseId());
			msg=basPartsArchivesService.modifyImportPartsArchives(fpVo, this.getUsers());
		} catch (Exception e) {
			logger.error("导入配件档案失败", e);
			msg.setMsg("导入配件档案失败，请确认数据格式是否正确！");
			msg.setSuccess(false);
		}finally{
			super.writeJson(msg);
		}
    }
    /**
     * 查询配件品牌
     */
    public void findPartsBrand()
    {
        try
        {
            super.writeJson(baseService.findAllPartsBrand(fpVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询配件品牌失败", e);
        }
    }

    /**
     * 查询供应商信息
     */
    public void findAllBasRelationCampany()
    {
        try
        {
        	fpVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(baseService.findAllBasRelationCampany(fpVo));
        }
        catch(Exception e)
        {
            logger.error("查询配件品牌失败", e);
        }
    }

    /**
     * 查询配件型号
     */
    public void findPartsType()
    {
        try
        {
            super.writeJson(baseService.findAllPartsType(fpVo.getPbrdId(), fpVo
                    .getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询配件型号失败", e);
        }
    }

    /**
     * 查询配件部位
     */
    public void findAllPartsPosition()
    {
        try
        {
            super.writeJson(baseService.findAllPartsPosition(fpVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询配件部位失败", e);
        }
    }

    /**
     * 查询配件产地
     */
    public void findAllPartsState()
    {
        try{
        	fpVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(baseService.findAllPartsState(fpVo));
        }catch(Exception e){
            logger.error("查询配件产地失败", e);
        }
    }

    /**
     * 查询配件单位
     */
    public void findAllPartsUnit()
    {
        try
        {
            super.writeJson(baseService.findAllPartsUnit(getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询配件单位失败", e);
        }
    }

    /**
     * 查询适合车型
     */
    public void findAllCarType()
    {
        try
        {
        	fpVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(baseService.findAllCarType(fpVo));
        }
        catch(Exception e)
        {
            logger.error("查询适合车型失败", e);
        }
    }

    /**
     * 配件档案datagrid
     */
    public void datagridPartsArchives()
    {
        try
        {
        	fpVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basPartsArchivesService
                            .datagridPartsArchives(fpVo));
        }
        catch(Exception e)
        {
            logger.error("配件档案datagrid失败", e);
            e.printStackTrace();
        }
    }

    /**
     * 保存配件档案
     */
    public void save()
    {
        Msg msg = new Msg();
        try
        {
        	fpVo.setEnterpriseId(getNowEnterpriseId());
                basPartsArchivesService.save(fpVo);
                msg.setSuccess(true);
                msg.setMsg("新增配件档案成功！");
        }
        catch(Exception e)
        {
            msg.setMsg("对不起！保存失败，失败原因：" + e.getMessage());
            logger.error("对不起，您输入的配件编码【" + fpVo.getPartsId() + "】已存在，请重新输入！",e);
        }
        super.writeJson(msg);
    }

    /**
     * 删除配件档案
     */
    public void remove()
    {
        Msg msg = new Msg();
        try
        {
            basPartsArchivesService.remove(fpVo.getPartsId());
            msg.setMsg("删除配件档案成功！");
            msg.setSuccess(true);
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            logger.error("对不起，该数据已经被使用，不允许删除！", e);
        }finally{
        	super.writeJson(msg);        	
        }
    }
    
    /**
	 * 判断配件是否已入库
	 */
    public void isExist(){
    	 Msg msg = new Msg();
         try{
             boolean isno =basPartsArchivesService.isExist(fpVo);
             if(isno)
            	 msg.setSuccess(false);
             else
            	 msg.setSuccess(true);
         }
         catch(Exception e){
             msg.setMsg("对不起，该数据已经被使用，不允许修改！");
             logger.error("对不起，该数据已经被使用，不允许修改！", e);
         }
         super.writeJson(msg);
    }

    /**
     * 修改配件档案
     */
    public void edit()
    {
        Msg msg = new Msg();
        try
        {
        	fpVo.setEnterpriseId(getNowEnterpriseId());
        	  List<FrtParts> lst=basPartsArchivesService.getPartsId(fpVo);
        	  if(lst==null ){
        		  basPartsArchivesService.edit(fpVo);
                  msg.setSuccess(true);
                  msg.setMsg("修改配件档案成功！"); 
        	  }else if(lst!=null && lst.size()>0){
        		  String partIdVo=lst.get(0).getPartsId();
        		  if(fpVo.getPartsId().equals(partIdVo)){
        			  basPartsArchivesService.edit(fpVo);
                      msg.setSuccess(true);
                      msg.setMsg("修改配件档案成功！"); 
        		  }else{
        			  msg.setSuccess(false);
                      msg.setMsg("对不起，您输入的配件编码【" + fpVo.getPartsId() + "】已存在，请重新输入！");
        		  }
        	  }
        }
        catch(Exception e){
            msg.setMsg("对不起，该数据已经被使用，不允许修改！");
            logger.error("对不起，该数据已经被使用，不允许修改！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 变更配件编码
     */
    public void changePartsId()
    {
        Msg msg = new Msg();
        try
        {
        	fpVo.setEnterpriseId(getNowEnterpriseId());
            msg.setSuccess(basPartsArchivesService.changePartsId(fpVo));
            msg.setMsg("变更配件编码成功！");
        }
        catch(Exception e)
        {
            msg.setMsg("变更配件编码失败");
            logger.error("变更配件编码失败", e);
        }
        super.writeJson(msg);
    }

    public void isExistsPartsId()
    {
        Msg msg = new Msg();
        try
        {
        	fpVo.setEnterpriseId(getNowEnterpriseId());
            List<FrtParts> list = basPartsArchivesService.getPartsId(fpVo);
            if (list != null){
                msg.setMsg("此配件编码已存在，请重新输入！");
                msg.setSuccess(true);
            }
            super.writeJson(msg);
        }catch(Exception e){
            logger.error("校验配件编号失败！", e);
        }
    }
    
    public void isExistsPartsIdEdit()
    {
        Msg msg = new Msg();
        try
        {
        	fpVo.setEnterpriseId(getNowEnterpriseId());
            if (basPartsArchivesService.isExistsPartsIdEdit(fpVo)){
                msg.setMsg("此配件编码已存在，请重新输入！");
                msg.setSuccess(true);
            }
            super.writeJson(msg);
        }catch(Exception e){
            logger.error("校验配件编号失败！", e);
        }
    }
    
    /**
     * 查询配件有无入仓信息
     * */
    public void isExistsJoinCompany(){
    	Msg msg=new Msg();
    	try {
    		fpVo.setEnterpriseId(getNowEnterpriseId());
    		Boolean flag=basPartsArchivesService.isExistsJoinCompany(fpVo);
    		if(flag!=null&&flag==true){
    			msg.setSuccess(true);    			
    		}else{
    			msg.setSuccess(false);
    			msg.setMsg("配件无入仓信息，不能进行全局调价操作！");
    		}
		} catch (Exception e) {
			logger.error("查询配件有无入仓信息失败！", e);
		}
		super.writeJson(msg);
    }
    
    /**
     * 更改配件使用状态
     * */
    public void modifyPartsFlag(){
    	Msg msg=new Msg();
    	try {
    		Double count=basPartsArchivesService.findPartsCount(fpVo);
			if(fpVo.getPartsFlag()==Contstants.ONOROFF.ONOROFFYES){
				if(count>0){
					msg.setMsg("编号【"+fpVo.getPartsId()+"】配件现有库存量不为0，不能禁用！");
				}else{
					basPartsArchivesService.modifyPartsFlag(fpVo);
					msg.setMsg("编号【"+fpVo.getPartsId()+"】配件禁用成功！");    					
					msg.setSuccess(true);    			
				}
			}else if(fpVo.getPartsFlag()==Contstants.ONOROFF.ONOROFFNO){
				basPartsArchivesService.modifyPartsFlag(fpVo);
				msg.setMsg("编号【"+fpVo.getPartsId()+"】配件启用成功！");
				msg.setSuccess(true); 
			}
		} catch (Exception e) {
			logger.error("更改配件使用状态失败！", e);
			if(fpVo.getPartsFlag()==Contstants.ONOROFF.ONOROFFYES){    			
    			msg.setMsg("编号【"+fpVo.getPartsId()+"】配件禁用失败！");
    		}else if(fpVo.getPartsFlag()==Contstants.ONOROFF.ONOROFFNO){
    			msg.setMsg("编号【"+fpVo.getPartsId()+"】配件启用失败！");
    		}
			msg.setSuccess(false);
		}
		super.writeJson(msg);
    }
    
    
    public FrtPartsVo getModel(){
        return fpVo;
    }

}