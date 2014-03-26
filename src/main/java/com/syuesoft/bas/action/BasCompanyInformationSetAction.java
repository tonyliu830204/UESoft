package com.syuesoft.bas.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.base.vo.BasCompanyInformationSetVo;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.util.Msg;

/**
 * 基础资料->公司信息设定Controller
 * @author Liujian
 */
@ParentPackage(value = "basePackage")
@Action("basCompanyInformationSetAction")
public class BasCompanyInformationSetAction extends BaseAction implements
        ModelDriven<BasCompanyInformationSetVo>
{
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(this.getClass());
    BasCompanyInformationSetVo cis = new BasCompanyInformationSetVo();
    @Autowired private BasCompanyInformationSetService basCompanyInformationSetService;
    
    /**
     * 带条件查询数据的方法
     */
    public void findCompanyAll(){
        try{
        	cis.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(basCompanyInformationSetService.findCompanyAll(cis));
        }catch(Exception e){
            logger.error("带条件查询数据的方法失败", e);
        }
    }
    
    public void getInformation(){
      Msg msg = new Msg();
      try{
          BasCompanyInformationSet bs= basCompanyInformationSetService.getBasCompanyInformationSet(cis.getCiType(), cis.getCiName(),getNowEnterpriseId());
          msg.setSuccess(true);
          msg.setObj(bs);
          msg.setMsg("查询");
      }catch(Exception e){
          e.printStackTrace();
      }finally{
    	  super.writeJson(msg);
      }
    }

    public void findParameterAll()
    {
        try{
        	cis.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(basCompanyInformationSetService.findParameterAll(cis));
        }catch(Exception e){
            logger.error("带条件查询数据的方法失败", e);
        }
    }

    /**
     * 保存数据的方法
     * @return
     */
    public void save()
    {
        Msg msg = new Msg();
        try{
        	cis.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            basCompanyInformationSetService.save(cis);
            msg.setSuccess(true);
            msg.setMsg("新增公司参数[" + cis.getCiName() + "]成功!");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        	super.writeJson(msg);
        }
    }

    /**
     * 根据id删除数据的方法
     * @return
     */
    public void delete()
    {
        Msg msg = new Msg();
        try{
            basCompanyInformationSetService.delete(cis.getCiId());
            msg.setSuccess(true);
            msg.setMsg("删除公司参数[" + cis.getCiName() + "]成功!");
        }catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(msg);
    }

    /**
     * 修改(更新)数据的方法
     * @return
     */
    public void edit()
    {
        Msg msg = new Msg();
        try{
        	cis.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            basCompanyInformationSetService.update(cis);
            msg.setSuccess(true);
            msg.setMsg("修改公司参数[" + cis.getCiName() + "]成功!");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        	super.writeJson(msg);
        }
    }

    /**
     * 保存系统参数
     */
    public void addParameter()
    {
        Msg msg = new Msg();
        try{
        	cis.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            basCompanyInformationSetService.saveOrUpdate(cis);
            msg.setSuccess(true);
            msg.setMsg("参数配置成功成功!");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        	super.writeJson(msg);
        }
    }

    /**
     * 查询参数一
     */
    public void searchParameterOne(){
        try{
        	cis.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(basCompanyInformationSetService.searchParameterOne(cis));
        }catch(Exception e){
            logger.error("带条件查询数据的方法失败", e);
        }
    }
    
    public BasCompanyInformationSetVo getModel(){
        return cis;
    }
   
}
