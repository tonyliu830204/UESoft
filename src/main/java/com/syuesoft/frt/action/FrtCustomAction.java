package com.syuesoft.frt.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.service.FrtCustomService;
import com.syuesoft.frt.vo.FrtCustomVo;
import com.syuesoft.model.FrtCustom;
import com.syuesoft.util.Msg;

/**
 * 客户档案action
 * @author Liujian
 *
 */
import org.apache.struts2.convention.annotation.ParentPackage;

@SuppressWarnings("serial")
@ParentPackage(value = "basePackage")
@Action("frtCustomAction")
public class FrtCustomAction extends BaseAction implements
        ModelDriven<FrtCustomVo>
{
    private final Logger logger = Logger.getLogger(this.getClass());
    FrtCustomVo fcVo = new FrtCustomVo();
    @Autowired
    private BaseService baseService;
    @Autowired
    private FrtCustomService frtCustomService;
    
    public void test()
    {
        List<Temp> lists = new ArrayList<Temp>();
        for (int i = 0; i < 10; i++){
            Temp temp = new Temp();
            temp.setId(10 + i);
            temp.setName("zs" + i);
            lists.add(temp);
        }
        super.writeJson(lists);
    }

    class Temp
    {
        private int id;

        private String name;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

    }

    /**
     * 查询客户性质
     */
    public void findAllCustomNature()
    {
        try
        {
            super.writeJson(baseService.findAllCustomNature(fcVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询客户性质失败", e);
        }
    }

    /**
     * 查询客户类型
     */
    public void findAllCustomGroup()
    {
        try
        {
            super.writeJson(baseService.findAllCustomGroup(fcVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询客户类型失败", e);
        }
    }

    /**
     * 查询客户分类
     */
    public void findAllCustomType()
    {
        try
        {
            super.writeJson(baseService.findAllCustomType(fcVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询客户分类失败", e);
        }
    }

    /**
     * 查询客户区域
     */
    public void findAllCustomArea()
    {
        try
        {
            super.writeJson(baseService.findAllCustomArea(fcVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询客户区域失败", e);
        }
    }

    /**
     * 查询车牌照
     */
    public void findAllCarLicense()
    {
        try
        {
        	fcVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(baseService.findAllCarLicense(fcVo.getQ(),fcVo.getEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询车牌照失败", e);
        }
    }

    /**
     * 客户档案datagrid
     */
    public void datagrid_frt_custom()
    {
        try
        {
        	fcVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtCustomService.datagrid(fcVo));
        }
        catch(Exception e)
        {
            logger.error("客户档案datagrid失败", e);
        }
    }
    
    /**
     * 客户档案datagrid
     */
    public void datagrid()
    {
        try
        {
        	fcVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtCustomService.datagrid_frt_custom(fcVo));
        }
        catch(Exception e)
        {
            logger.error("客户档案datagrid失败", e);
        }
    }

    /**
     * 保存客户档案
     */
    public void save()
    {
        Msg msg = new Msg();
        try
        {
        	fcVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            Serializable seri = frtCustomService.save(fcVo);
            msg.setSuccess(true);
            msg.setMsg("保存客户档案成功！");
            msg.setObj(seri);
        }
        catch(Exception e)
        {
            logger.error("保存客户档案失败", e);
        }
        super.writeJson(msg);
    }

    /**
     * 删除客户档案
     */
    public void remove()
    {
        Msg msg = new Msg();
        try
        {
            frtCustomService.remove(fcVo);
            msg.setSuccess(true);
            msg.setMsg("删除客户档案成功！");
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            logger.error("删除客户档案失败！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 更新客户档案
     */
    public void edit()
    {
        Msg msg = new Msg();
        try
        {
        	fcVo.setEnterpriseId(getNowEnterpriseId());
            frtCustomService.edit(fcVo);
            msg.setSuccess(true);
            msg.setMsg("更新客户档案成功！");
        }
        catch(Exception e)
        {
            logger.error("更新客户档案失败", e);
        }
        super.writeJson(msg);
    }

    public void saveCustom()
    {
        Msg msg = new Msg();
        try
        {
        	fcVo.setEnterpriseId(getNowEnterpriseId());
            Serializable seri = frtCustomService.saveCustom(fcVo);
            msg.setSuccess(true);
            msg.setMsg("保存客户档案成功！");
            msg.setObj(seri);
        }
        catch(Exception e)
        {
            logger.error("保存客户档案失败", e);
        }
        super.writeJson(msg);
    }

    /**
     * 更改客户使用状态
     * */
    public void modifyPartsFlag(){
    	Msg msg=new Msg();
    	try {
			if(fcVo.getCustomFlag()==Contstants.ONOROFF.ONOROFFYES){
					frtCustomService.modifyCustomFlag(fcVo);
					msg.setMsg("编号【"+fcVo.getCustomId()+"】客户禁用成功！");    					
					msg.setSuccess(true);
			}else if(fcVo.getCustomFlag()==Contstants.ONOROFF.ONOROFFNO){
				frtCustomService.modifyCustomFlag(fcVo);
				msg.setMsg("编号【"+fcVo.getCustomId()+"】客户启用成功！");
				msg.setSuccess(true); 
			}
		} catch (Exception e) {
			logger.error("更改客户使用状态失败！", e);
			if(fcVo.getCustomFlag()==Contstants.ONOROFF.ONOROFFYES){    			
    			msg.setMsg("编号【"+fcVo.getCustomId()+"】客户禁用失败！");
    		}else if(fcVo.getCustomFlag()==Contstants.ONOROFF.ONOROFFNO){
    			msg.setMsg("编号【"+fcVo.getCustomId()+"】客户启用失败！");
    		}
			msg.setSuccess(false);
		}
		super.writeJson(msg);
    }
    
    /**
     * 变更客户档案
     */
    public void changeCustomId()
    {
        Msg msg = new Msg();
        try{
           msg.setSuccess(frtCustomService.changeCustomId(fcVo));
           msg.setMsg("变更客户档案成功！");
        }catch(Exception e){
            msg.setMsg("变更客户档案失败");
            logger.error("变更客户档案失败", e);
        }
        super.writeJson(msg);
    }
    
    public void isExistsCustomId()
    {
        Msg msg = new Msg();
        try
        {
            List<FrtCustom> list = frtCustomService.getCustomId(fcVo);
            if (list != null)
            {
                msg.setMsg("此客户编号已存在，请重新输入！");
                msg.setSuccess(true);
            }
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            logger.error("校验客户编号失败！", e);
        }
    }
    
    
    public FrtCustomVo getModel(){
        return fcVo;
    }

}
