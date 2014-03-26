package com.syuesoft.bas.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasJobPropertyService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fbk.vo.BasJobPropertyVO;
import com.syuesoft.util.Message;
/**
 * 工作属性Action
 * @author LWJ
 * */
@ParentPackage(value = "basePackage")
@Action("basJobPropertyAction")
public class BasJobPropertyAction extends BaseAction implements
        ModelDriven<BasJobPropertyVO>
{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    Logger logger=Logger.getLogger(this.getClass());
    @Autowired
    private BasJobPropertyService basJobPropertyService;

    private BasJobPropertyVO bjpVo = new BasJobPropertyVO();

    /**
	 * 工作属性查询
	 */
    public void findAll()
    {
    	try {
    		if(getNowEnterpriseId()!=null)
    			bjpVo.setEnterpriseId(Integer.parseInt(getNowEnterpriseId().toString()));
			super.writeJson(basJobPropertyService.basJobPropertyDatagrid(bjpVo));
		} catch (Exception e) {
			logger.error("查询工作属性失败！", e);
		}
    }

    /**
	 * 工作属性新增
	 */
    public void add()
    {
    	 Message msg = new Message();
         try{
        	 //bjpVo.setSysType(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getSystemId().toString());
             boolean bool = basJobPropertyService.add(bjpVo);
             if (bool){
            	 msg.setSuccess(true);
            	 msg.setMsg("新增工作属性成功！");
             }else{
            	 msg.setSuccess(false);
            	 msg.setMsg("对不起，您输入的工作属性已存在，请重新输入！");
             }
         }catch(Exception e){
         	logger.error("新增工作属性失败！", e);
         	msg.setSuccess(false);
         	msg.setMsg("新增工作属性失败！");
         }finally{
         	super.writeJson(msg);        	
         }
    }

    /**
	 * 工作属性删除
	 */
    public void delete()
    {
		  Message msg = new Message();
	      try{
	         boolean flag=basJobPropertyService.delete(bjpVo);
	         if(flag){
	        	 msg.setSuccess(true);
	        	 msg.setMsg("删除工作属性成功！");
	         }else{
	        	 msg.setSuccess(true);
	        	 msg.setMsg("此工作属性下有员工，不能删除！");
	         }
	      }catch(Exception e){
	      	logger.error("删除工作属性失败！", e);
	      	msg.setSuccess(false);
	      	msg.setMsg("删除工作属性失败！");  	
	      }finally{
	      	super.writeJson(msg);        	
	      }
    }

    /**
	 * 工作属性修改
	 */
    public void update()
    {
    	Message msg = new Message();
        try{
            boolean bool = basJobPropertyService.update(bjpVo);
            if (bool){
            	msg.setSuccess(true);
            	msg.setMsg("修改工作属性成功！");
            }else{
            	msg.setSuccess(false);
            	msg.setMsg("对不起，您输入的工作属性已存在，请重新输入！");
            }
        }catch(Exception e){
        	logger.error("修改工作属性失败！", e);
        	msg.setSuccess(false);
        	msg.setMsg("修改工作属性失败！");
        }finally{
        	super.writeJson(msg);        	
        }
    }

	
	public BasJobPropertyVO getModel() {
		// TODO Auto-generated method stub
		return bjpVo;
	}
}
