package com.syuesoft.bas.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasPersonnelInformationService;
import com.syuesoft.base.vo.BasStuffVo;
import com.syuesoft.base.vo.BasStuffVo1;
import com.syuesoft.base.vo.BasStuffVo3;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Msg;

/**
 * 人事资料设定 Action
 * @author wucuancuan
 */
@ParentPackage(value = "basePackage")
@Action("basPersonnelInformationSetAction")
public class BasPersonnelInformationSetAction extends BaseAction implements
        ModelDriven<BasStuffVo>
{
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(this.getClass());

    BasStuffVo stuffVo = new BasStuffVo();
    @Autowired
    private BasPersonnelInformationService basPersonnelInformationService;
    
    public BasStuffVo getModel()
    {
        return stuffVo;
    }

    /**
     * 获取员工持有的属性
     * */
    public void selectJobProId(){
        try{
            super.writeJson(basPersonnelInformationService.selectJobProId(stuffVo));
        }catch(Exception e){
            logger.error("获取员工持有的属性失败", e);
        }
    }

    /**
     * 查询指定公司下的部门
     * */
    public void findAllDept(){
        try{
        	if(stuffVo.getEnterpriseId()==null||stuffVo.getEnterpriseId().length()==0)
        		stuffVo.setEnterpriseId(getNowEnterpriseId()+"");
            super.writeJson(basPersonnelInformationService.findAllDept(stuffVo));
        }catch(Exception e){
            logger.error("获取部门失败", e);
        }
    }
    /**
     * 查询集团公司及其子公司
     * */
    public void findAllCompany(){
        try{	
            stuffVo.setEnterpriseId(getNowEnterpriseId().toString());
            super.writeJson(basPersonnelInformationService.findAllCompany(stuffVo));
        }catch(Exception e){
            logger.error("查询集团公司及其子公司失败！", e);
        }
    }

    /**
     * 查询工作属性
     * */
    public void findAllJobProperty(){
        try{
            super.writeJson(basPersonnelInformationService
                    .findAllBasJobProperty(stuffVo));
        }catch(Exception e){
            logger.error("获取工作属性失败", e);
        }
    }

    /**
     * 查询维修班组
     * */
    public void findAllCJDept()
    {
        try{
            super.writeJson(basPersonnelInformationService.findAllCJDept(stuffVo));
        	if(stuffVo.getEnterpriseId()==null||stuffVo.getEnterpriseId().length()==0){
        		stuffVo.setEnterpriseId(getNowEnterpriseId().toString());
        	}
            super.writeJson(basPersonnelInformationService.findAllCJDept(stuffVo));
        }catch(Exception e){
            logger.error("获取维修班组失败！", e);
        }
    }
    /**
     * 查询仓别分类
     * */
    public void findAllCBFL(){
        try{
        	stuffVo.setEnterpriseId(getNowEnterpriseId().toString());
        	super.writeJson(basPersonnelInformationService.findAllCBFL(stuffVo.getEnterpriseId()));
        }catch(Exception e){
            logger.error("获取仓别分类失败", e);
        }
    }

    /**
     * 注销某个用户
     * */
    public void logoutUser(){
        Msg msg = new Msg();
        try{
            basPersonnelInformationService.updateBasStuff(stuffVo);
            msg.setMsg("注销用户成功");
            msg.setSuccess(true);
        }catch(Exception e){
            msg.setMsg("注销用户失败!");
            logger.error("注销用户失败", e);
        }finally{
        	writeJson(msg);
        }
    }
    /**
     * 变更用户
     * */
    public void doChangeBasStuff(){
        try{
            writeJson(basPersonnelInformationService.changeUserStfYid(stuffVo));
        }catch(Exception e){
            logger.error("变更用户失败", e);
        }
    }

    /**
     * 分页 展现员工List 携带查询
     * */
    public void findAllParam(){
        try{
        	stuffVo.setEnterpriseId(getNowEnterpriseId().toString());
            writeJson(basPersonnelInformationService.findByParam(stuffVo, this.getUsers()));
        }catch(Exception e){
            logger.error("分页 展现员工List 携带查询失败", e);
        }
    }

    /**
     * 人事资料删除
     * */
    public void delete(){
        Msg msg = new Msg();
        try{
            if (stuffVo.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR)){
                msg.setMsg("对不起，超级管理员不允许删除！");
                msg.setSuccess(false);
            }else{
            	stuffVo.setEnterpriseName(this.getSession().getAttribute(Contstants.ENTERPRISENAME).toString());
                boolean flag=basPersonnelInformationService.delete(stuffVo);
                if(flag){
                	msg.setMsg("删除成功！");
                	msg.setSuccess(true);
                }else{
                	msg.setSuccess(false);
                	msg.setMsg("数据不完整，删除失败！");
                }
            }
        }catch(Exception e){
        	logger.error("对不起，该员工编号为[" + stuffVo.getStfYid() + "]已经被使用，不允许删除！",e);
            msg.setSuccess(false);
            msg.setMsg("对不起，该员工编号为[" + stuffVo.getStfYid() + "]已经被使用，不允许删除！");
        }finally{
        	super.writeJson(msg);
        }
    }
    /**
     * 人事资料新增
     * */
    public void doSave()
    {
        // 开始一次性新增此对象
        Msg msg = new Msg();
        try{
            if (stuffVo != null){
            	stuffVo.setTempEnterpriseId(getNowEnterpriseId().toString());
            	stuffVo.setEnterpriseName(this.getSession().getAttribute(Contstants.ENTERPRISENAME).toString());
                BasStuffVo1 basstu1 = JSON.parseObject(stuffVo.getBaseInfo(),
                        BasStuffVo1.class);
                BasStuffVo3 basstu3 = JSON.parseObject(stuffVo.getOtherInfo(),
                        BasStuffVo3.class);
                if (basstu1 != null)
                    BeanUtils.copyProperties(basstu1, stuffVo);
                if (basstu3 != null)
                    BeanUtils.copyProperties(basstu3, stuffVo);
                basPersonnelInformationService.save(stuffVo, this.getUsers());
                msg.setMsg("保存成功！");
                msg.setSuccess(true);
            }
        }catch(Exception e){
            msg.setMsg("对不起，该员工编号为【" + stuffVo.getStfYid() + "】新增失败，请重新输入！");
            logger.error("对不起，该员工编号为【" + stuffVo.getStfYid() + "】新增失败，请重新输入！",e);
        }finally{
        	super.writeJson(msg);
        }
    }
    /**
     * 查找指定登陆用户
     * */
    public void getUserInfo(){
        try{
            writeJson(basPersonnelInformationService.findBasStuffByYid(stuffVo));
        }catch(Exception e){
            logger.error("查询失败！", e);
        }
    }
    /**
     * 人事资料修改
     * */
    public void doEdit(){
        Msg msg = new Msg();
        try{
            if (stuffVo != null){
            	stuffVo.setTempEnterpriseId(getNowEnterpriseId().toString());
                BasStuffVo1 basstu1 = JSON.parseObject(stuffVo.getBaseInfo(),
                        BasStuffVo1.class);
                BasStuffVo3 basstu3 = JSON.parseObject(stuffVo.getOtherInfo(),
                        BasStuffVo3.class);
                if (basstu1 != null)
                    BeanUtils.copyProperties(basstu1, stuffVo);
                if (basstu3 != null)
                    BeanUtils.copyProperties(basstu3, stuffVo);
                if (stuffVo.getStfYid() != null){
                	stuffVo.setEnterpriseName(this.getSession().getAttribute(Contstants.ENTERPRISENAME).toString());
                	basPersonnelInformationService.update(stuffVo, this.getUsers());
                    msg.setMsg("修改成功！");
                    msg.setSuccess(true);
                }else{
                    msg.setMsg("修改失败！");
                    msg.setSuccess(false);
                }
            }
        }catch(Exception e){
            msg.setMsg("对不起，该员工编号为[" + stuffVo.getStfYid() + "]修改失败，请重新输入！");
            logger.error("编辑失败！", e);
        }finally{
        	super.writeJson(msg);
        }
    }

    /**
     * 校验员工编号
     * */
    public void validateStfYid(){
        Msg msg = new Msg();
        try{
            if (basPersonnelInformationService.findStfYid(stuffVo)){
                msg.setMsg("对不起，您输入的编号【" + stuffVo.getStfYid() + "】已存在，请重新输入！");
            }else{
                msg.setSuccess(true);
            }
        }catch(Exception e){
            logger.error("校验校验员工编号失败！", e);
            msg.setMsg("校验员工编号失败！");
        }finally{
        	super.writeJson(msg);
        }
    }

    /**
     * 校验员工姓名
     * */
    public void validateStfName(){
        Msg msg = new Msg();
        try{
            if (basPersonnelInformationService.findUserName(stuffVo, this.getUsers())){
                msg.setMsg("对不起，您输入的姓名【" + stuffVo.getStfName() + "】已存在，是否继续！");
            }else{
                msg.setSuccess(true);
            }
        }catch(Exception e){
            logger.error("校验姓名失败", e);
            msg.setMsg("校验姓名失败！");
        }finally{
        	super.writeJson(msg);
        }
    }

    /**
     * 模板打印
     */
    public void print(){
        try{
            BasUsers user = this.getUsers();
            this.sendJson(basPersonnelInformationService.getPrintHtml(stuffVo,user));
        }catch(Exception e){
            logger.error("生成打印数据失败", e);
        }
    }
    
    /**
     * 用户角色查询
     * */
    public void findUserRole(){
        try{
        	stuffVo.setEnterpriseId(getNowEnterpriseId().toString());
            writeJson(basPersonnelInformationService.findUserRole(stuffVo));
        }catch(Exception e){
            logger.error("分页 展现员工角色查询查询失败", e);
        }
    }
    /**
     * 人事资料设定Datagrid
     */
    public void basPersonnelInformationDatagrid(){
    	try {
			super.writeJson(basPersonnelInformationService.basPersonnelInformationDatagrid(stuffVo));
		} catch (Exception e) {
			logger.error("人事资料查询失败！", e);
		}
    }
    /**
     * 设置集团管理员
     * */
    public void modifyCombinePerson(){
    	Msg msg=new Msg();
    	try {
    		boolean tag=basPersonnelInformationService.modifyCombinePerson(stuffVo);
    		if(tag){
    			msg.setSuccess(true);
    			msg.setMsg("设置集团管理员成功！");
    		}else{
    			msg.setSuccess(false);
    			msg.setMsg("集团管理员已存在或数据不完整，操作失败！");
    		}
		} catch (Exception e) {
			logger.error("设置集团管理员失败！", e);
			msg.setSuccess(false);
			msg.setMsg("设置集团管理员失败！");
		}finally{
			super.writeJson(msg);
		}
    }
    /**
     * 系统用户中集团管理员是否存在
     * */
    public void isExistsCombineAdmin(){
    	Msg msg=new Msg();
    	try {
			boolean flag=basPersonnelInformationService.isExistsCombineAdmin();
			if(!flag){
				msg.setSuccess(true);
				msg.setMsg("系统用户中不存在集团管理员！");
			}else{
				msg.setSuccess(false);
				msg.setMsg("集团管理员已设置，不能重复设置！");
			}
		} catch (Exception e) {
			logger.error("查找系统用户中集团管理员是否存在失败！", e);
			msg.setSuccess(false);
			msg.setMsg("查找系统用户中集团管理员是否存在失败！");
		}finally{
			super.writeJson(msg);
		}
    }
}