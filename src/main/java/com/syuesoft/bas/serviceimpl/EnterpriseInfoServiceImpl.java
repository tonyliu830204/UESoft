package com.syuesoft.bas.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.BasDeptDao;
import com.syuesoft.bas.dao.EnterpriseInfoDAO;
import com.syuesoft.bas.service.EnterpriseInfoService;
import com.syuesoft.base.vo.EnterpriseInfoVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasDept;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.model.EnterpriseWork;
import com.syuesoft.print.service.DownloadService;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.util.ComboTreeJson;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Msg;

@Service("enterpriseInfoService")
public class EnterpriseInfoServiceImpl  implements EnterpriseInfoService {
	private static final Logger logger = Logger.getLogger(EnterpriseInfoServiceImpl.class);
    @Autowired
    private EnterpriseInfoDAO enterpriseInfoDAO;
    @Autowired
    private BasDeptDao basDeptDao;
    @Autowired
    private DownloadService downloadService;
    /**
	 * @Title: modifyImportPartsArchives 
     * @Description: TODO(导入企业信息) 
     * @param @param enterpriseInfoVo
     * @param @return
     * @param @throws Exception    设定文件 
     * @return Msg    返回类型 
     * @throws 
	 * */
    
	public Msg modifyImportEnterpriseInfo(EnterpriseInfoVo enterpriseInfoVo, BasUsers user)
			throws Exception {
    	Msg msg=new Msg();
		List<EnterpriseInfo> eis =null;
		String type = enterpriseInfoVo.getType();
		try {
            if (type != null && !"".equals(type)){
                List<Object> downloadlist = downloadService.findDownLoad(type, enterpriseInfoVo, user);
                if(downloadlist != null && downloadlist.size() > 0){
                    eis = new ArrayList<EnterpriseInfo>();
                    for (Object obj : downloadlist) {
                    	EnterpriseInfoVo eiVo = (EnterpriseInfoVo)obj;
                        if(eiVo.getEnterpriseName() != null && !"".equals(eiVo.getEnterpriseName())){
                            EnterpriseInfo ei = new EnterpriseInfo();
                            BeanUtils.copyProperties(eiVo, ei);
                            if(eiVo.getParentEnterpriseName() != null){
                                EnterpriseInfo pei = (EnterpriseInfo) enterpriseInfoDAO.get("from EnterpriseInfo ei where ei.enterpriseName='"+eiVo.getParentEnterpriseName()+"'");
                                if(pei!=null){
                                	ei.setParentEnterpriseId(pei.getEnterpriseId());
                                }
                            }else{
                                ei.setParentEnterpriseId(-1);
                            }
                            ei.setDelTag("n");
                            eis.add(ei);
                        }else{
                            msg.setSuccess(false);
                            msg.setMsg("导入企业中不包含企业名称，请确认！");
                            return msg;
                        }
                    }
                    if(eis!=null&&eis.size()>0){
                    	EnterpriseInfoVo eiVo=null;
                    	for (EnterpriseInfo enterpriseInfo : eis) {
                    		eiVo=new EnterpriseInfoVo();
                    		BeanUtils.copyProperties(enterpriseInfo, eiVo);
                    		addEnterprise(eiVo);
                		}
                    }
                    msg.setMsg("导入企业信息成功！");
                    msg.setSuccess(true);			
                }
            }else{
            	msg.setMsg("请选择一个要倒入的类型！");
            	msg.setSuccess(false);
            	return msg;
            }
		} catch (Exception e) {
			msg.setMsg("导入企业信息失败！");
			msg.setSuccess(false);
			logger.error("导入企业信息失败！", e);
		}
		return msg;
	}

	/**
     * 
     * */
    public Datagrid getPager(EnterpriseInfoVo enterpriseInfoVo) throws Exception {
        Datagrid dg = new Datagrid();
        StringBuffer hql=new StringBuffer("from EnterpriseInfo where delTag='n' ");
        if(enterpriseInfoVo.getEnterpriseCode() != null && !"".equals(enterpriseInfoVo.getEnterpriseCode())){
            hql.append("and enterpriseCode LIKE '%"+ StringEscapeUtils.escapeSql(enterpriseInfoVo.getEnterpriseCode().trim())+"%' ");
        }
        if(enterpriseInfoVo.getEnterpriseJm() != null && !"".equals(enterpriseInfoVo.getEnterpriseJm())){
            hql.append("and enterpriseJm LIKE '%"+ StringEscapeUtils.escapeSql(enterpriseInfoVo.getEnterpriseJm().trim())+"%' ");
        }
        if(enterpriseInfoVo.getEnterpriseName() != null && !"".equals(enterpriseInfoVo.getEnterpriseName())){
            hql.append("and enterpriseName LIKE '%"+ StringEscapeUtils.escapeSql(enterpriseInfoVo.getEnterpriseName().trim())+"%' ");
        }
        if(enterpriseInfoVo.getEnterprisePerson() != null && !"".equals(enterpriseInfoVo.getEnterprisePerson())){
            hql.append("and enterprisePerson LIKE '%"+ StringEscapeUtils.escapeSql(enterpriseInfoVo.getEnterprisePerson().trim())+"%' ");
        }
        if(enterpriseInfoVo.getEnterpriseId()!=null && !"".equals(enterpriseInfoVo.getEnterpriseId())){
            hql.append(" and (enterpriseId="+enterpriseInfoVo.getEnterpriseId()+" OR parentEnterpriseId = "+enterpriseInfoVo.getEnterpriseId()+")");
        }
        List<Object> lst=enterpriseInfoDAO.find(hql.toString(), enterpriseInfoVo.getPage(), enterpriseInfoVo.getRows());
        List<EnterpriseInfoVo> rows =new ArrayList<EnterpriseInfoVo>();
        if(lst!=null && lst.size()>0){
            for(Object obj:lst){
                EnterpriseInfo info = (EnterpriseInfo)obj;
                EnterpriseInfoVo enterpriseVo=new EnterpriseInfoVo();
                BeanUtils.copyProperties(info, enterpriseVo);
                if(info.getParentEnterpriseId() != null && !"-1".equals(info.getParentEnterpriseId().toString())){
                    enterpriseVo.setParentEnterpriseId(info.getParentEnterpriseId());
                    StringBuffer hql_=new StringBuffer("from EnterpriseInfo enterprise where 1=1 and enterpriseId="+info.getParentEnterpriseId());
                    List<Object> list=enterpriseInfoDAO.find(hql_.toString());
                    EnterpriseInfo parentEnterprise=list != null ? (EnterpriseInfo)list.get(0) : null;
                    enterpriseVo.setParentEnterpriseName(parentEnterprise.getEnterpriseName());
                }
                if(info.getBank()!=null&&!"-1".equals(info.getBank().toString())){
                	StringBuffer hql_=new StringBuffer("from XsChilddictionary xc where xc.enterpriseId="+enterpriseInfoVo.getEnterpriseId()+" and xc.childId='"+info.getBank()+"'");
                    List<Object> list=enterpriseInfoDAO.find(hql_.toString());
                    XsChilddictionary xc=list != null ? (XsChilddictionary)list.get(0) : null;
                    if(xc!=null&&!xc.equals(""))
                       enterpriseVo.setBankName(xc.getDataValue());
                }
                rows.add(enterpriseVo);
            }   
        }
        int total = enterpriseInfoDAO.getCount(hql.toString());
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;  
    }
    
    public Object getEnterpriseWork(EnterpriseInfoVo enterpriseInfoVo) throws Exception
    {
        Datagrid dg = new Datagrid();
        StringBuffer hql=new StringBuffer("from EnterpriseWork where 1=1 and enterpriseId = "+enterpriseInfoVo.getEnterpriseId());
        int total = enterpriseInfoDAO.getCount(hql.toString());
        List<Object> list = enterpriseInfoDAO.find(hql.toString());
        List<EnterpriseInfoVo> rows =new ArrayList<EnterpriseInfoVo>();
        if(list!=null && list.size()>0){
            for(Object obj:list){
                EnterpriseWork e = (EnterpriseWork) obj;
                EnterpriseInfoVo enterprise = new EnterpriseInfoVo();
                enterprise.setEnterpriseId(e.getEnterpriseId());
                enterprise.setWorkId(e.getWorkId());
                enterprise.setEnterpriseGjFootnote(e.getEnterpriseGjFootnote());
                enterprise.setEnterpriseJSISONO(e.getEnterpriseJSISONO());
                enterprise.setEnterprisePgHead(e.getEnterprisePgHead());
                enterprise.setEnterpriseSpFootnot(e.getEnterpriseSpFootnot());
                enterprise.setEnterpriseJcFootnote(e.getEnterpriseJcFootnote());
                enterprise.setEnterpriseJsHead(e.getEnterpriseJsHead());
                enterprise.setEnterpriseLoginLimit(e.getEnterpriseLoginLimit());
                enterprise.setEnterpriseSMSLimit(e.getEnterpriseSMSLimit());
                enterprise.setOutboundnumber(e.getOutboundnumber());
                enterprise.setEnterprisePorint(e.getEnterprisePorint());
                enterprise.setEnterpriseRemark(e.getEnterpriseRemark());
                enterprise.setEnterprisePath(e.getEnterprisePath());
                enterprise.setEnterpriseJsFootnote(e.getEnterpriseJsFootnote());
                rows.add(enterprise);
            }
        }
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }
    
    public boolean deleteEnterprise(EnterpriseInfoVo enterpriseInfoVo) throws Exception {
    	if(enterpriseInfoVo.getEnterpriseDeleteValidateFlag()!=null&&enterpriseInfoVo.getEnterpriseDeleteValidateFlag()==true){
    		List lt1=enterpriseInfoDAO.createSQLQuery("SELECT bd.dept_id FROM bas_dept bd INNER JOIN bas_stuff bs ON bs.dept_id=bd.dept_id and bs.del_tag='n' WHERE bd.enterprise_id="+enterpriseInfoVo.getEnterpriseId());
    		if(lt1!=null&&lt1.size()>0){
    			return false;
    		}
    	}
//      List lt2=enterpriseInfoDAO.createSQLQuery("SELECT bjp.job_pro_id FROM bas_job_property bjp INNER JOIN bas_stuff_job bsj ON bsj.job_pro_id=bjp.job_pro_id WHERE bjp.enterprise_id="+enterpriseInfoVo.getEnterpriseId());
//      if(lt2!=null&&lt2.size()>0){
//          return false;
//      }
//      List lt3=enterpriseInfoDAO.createSQLQuery("SELECT bur.user_id FROM bas_role br INNER JOIN bas_user_role bur ON bur.role_id=br.role_id WHERE br.enterprise_id="+enterpriseInfoVo.getEnterpriseId());
//      if(lt3!=null&&lt3.size()>0){
//          return false;
//      }
//      enterpriseInfoDAO.executeSQL("delete from bas_dept where enterprise_id="+enterpriseInfoVo.getEnterpriseId());
//      enterpriseInfoDAO.executeSQL("delete from bas_job_property where enterprise_id="+enterpriseInfoVo.getEnterpriseId());
//      enterpriseInfoDAO.executeSQL("DELETE FROM bas_role WHERE enterprise_id="+enterpriseInfoVo.getEnterpriseId());
        
//      StringBuffer hql=new StringBuffer("DELETE FROM EnterpriseWork where 1=1 and enterpriseId = "+enterpriseInfoVo.getEnterpriseId());
//      enterpriseInfoDAO.deleteByHql(hql.toString());
        EnterpriseInfo enterpriseInfo=enterpriseInfoDAO.getEnterpriseInfo(enterpriseInfoVo.getEnterpriseId());
        enterpriseInfo.setDelTag("y");
        enterpriseInfoDAO.merge(enterpriseInfo);
        return true;
    }
    public void deleteEnterpriseWork(EnterpriseInfoVo enterpriseInfoVo) throws Exception
    {
        EnterpriseWork enterpriseWork = new EnterpriseWork();
        BeanUtils.copyProperties(enterpriseInfoVo, enterpriseWork);
        enterpriseInfoDAO.delete(enterpriseWork);
    }
    
    public void addEnterprise(EnterpriseInfoVo enterpriseInfoVo) throws Exception {
        EnterpriseInfo enterpriseInfo=new EnterpriseInfo();
        BeanUtils.copyProperties(enterpriseInfoVo, enterpriseInfo);
        if(enterpriseInfo.getEnterpriseCode()==null || enterpriseInfo.getEnterpriseCode().length()==0)
        	enterpriseInfo.setEnterpriseCode(CreateID.createId("enterprise",Contstants.SELL_BILLSDEPLOY.ENTERPRISE));
        enterpriseInfo.setDelTag("n");
        enterpriseInfoDAO.save(enterpriseInfo); 
        if(enterpriseInfo.getParentEnterpriseId()!=null&&enterpriseInfo.getParentEnterpriseId()!=-1){
            BasDept basDept=new BasDept();
            basDept.setEnterpriseId(enterpriseInfo.getEnterpriseId());
            basDept.setDeptDesc(null);
            basDept.setDeptName("人事部");
            basDeptDao.save(basDept);
//          BasJobPropertyVO bjobPropertyVO=new BasJobPropertyVO();
//          bjobPropertyVO.setEnterpriseId(enterpriseInfo.getEnterpriseId());
//          bjobPropertyVO.setJobProName("管理员");
//          bjobPropertyVO.setJobProNote(null);
//          bjobPropertyVO.setSysType(Contstants.SYSTEMTYPE.WEIXIU);
//          basJobPropertyDao.add(bjobPropertyVO); 
        }
    }
    
    public void addEnterpriseWork(EnterpriseInfoVo enterpriseInfoVo) throws Exception
    {
        EnterpriseWork enterpriseWork = new EnterpriseWork();
        BeanUtils.copyProperties(enterpriseInfoVo, enterpriseWork);
        enterpriseInfoDAO.save(enterpriseWork);
    }
    
    public void updateEnterprise(EnterpriseInfoVo enterpriseInfoVo) throws Exception{
        EnterpriseInfo enterpriseInfo=enterpriseInfoDAO.getEnterpriseInfo(enterpriseInfoVo.getEnterpriseId());
        if(enterpriseInfoVo.getEnterpriseName()!=null&&enterpriseInfoVo.getEnterpriseName().length()>0)
            enterpriseInfo.setEnterpriseName(enterpriseInfoVo.getEnterpriseName());
        if(enterpriseInfoVo.getEnterpriseJm()!=null&&enterpriseInfoVo.getEnterpriseJm().length()>0)
            enterpriseInfo.setEnterpriseJm(enterpriseInfoVo.getEnterpriseJm());
        if(enterpriseInfoVo.getEnterpriseAddress()!=null&&enterpriseInfoVo.getEnterpriseAddress().length()>0)
            enterpriseInfo.setEnterpriseAddress(enterpriseInfoVo.getEnterpriseAddress());
        if(enterpriseInfoVo.getEnterpriseZipcode()!=null&&enterpriseInfoVo.getEnterpriseZipcode().length()>0)
            enterpriseInfo.setEnterpriseZipcode(enterpriseInfoVo.getEnterpriseZipcode());
        if(enterpriseInfoVo.getEnterpriseFax()!=null&&enterpriseInfoVo.getEnterpriseFax().length()>0)
            enterpriseInfo.setEnterpriseFax(enterpriseInfoVo.getEnterpriseFax());
        if(enterpriseInfoVo.getParentEnterpriseId()!=null&&enterpriseInfoVo.getParentEnterpriseId().toString().length()>0)
            enterpriseInfo.setParentEnterpriseId(enterpriseInfoVo.getParentEnterpriseId());
        if(enterpriseInfoVo.getEnterprisePerson()!=null&&enterpriseInfoVo.getEnterprisePerson().length()>0)
            enterpriseInfo.setEnterprisePerson(enterpriseInfoVo.getEnterprisePerson());
        if(enterpriseInfoVo.getEnterpriseTelephone()!=null&&enterpriseInfoVo.getEnterpriseTelephone().length()>0)
            enterpriseInfo.setEnterpriseTelephone(enterpriseInfoVo.getEnterpriseTelephone());
        if(enterpriseInfoVo.getEnterpriseEmail()!=null&&enterpriseInfoVo.getEnterpriseEmail().length()>0)
            enterpriseInfo.setEnterpriseEmail(enterpriseInfoVo.getEnterpriseEmail());
        if(enterpriseInfoVo.getBank()!=null&&enterpriseInfoVo.getBank().toString().length()>0)
            enterpriseInfo.setBank(enterpriseInfoVo.getBank());
        if(enterpriseInfoVo.getBankNumber()!=null&&enterpriseInfoVo.getBankNumber().length()>0)
            enterpriseInfo.setBankNumber(enterpriseInfoVo.getBankNumber());
        if(enterpriseInfoVo.getDutyNumber()!=null&&enterpriseInfoVo.getDutyNumber().length()>0)
            enterpriseInfo.setDutyNumber(enterpriseInfoVo.getDutyNumber());
        if(enterpriseInfoVo.getComplainTelephone()!=null&&enterpriseInfoVo.getComplainTelephone().length()>0)
            enterpriseInfo.setComplainTelephone(enterpriseInfoVo.getComplainTelephone());
        if(enterpriseInfoVo.getHotlineTelephone()!=null&&enterpriseInfoVo.getHotlineTelephone().length()>0)
            enterpriseInfo.setHotlineTelephone(enterpriseInfoVo.getHotlineTelephone());
        if(enterpriseInfoVo.getEnterpriseUrl()!=null&&enterpriseInfoVo.getEnterpriseUrl().length()>0)
            enterpriseInfo.setEnterpriseUrl(enterpriseInfoVo.getEnterpriseUrl());
        enterpriseInfo.setDelTag("n");
        //BeanUtils.copyProperties(enterpriseInfoVo, enterpriseInfo);
        enterpriseInfoDAO.update(enterpriseInfo);           
    }
    
    public void updateEnterpriseWork(EnterpriseInfoVo enterpriseInfoVo) throws Exception
    {
        EnterpriseWork enterpriseWork = new EnterpriseWork();
        BeanUtils.copyProperties(enterpriseInfoVo, enterpriseWork);
        enterpriseInfoDAO.merge(enterpriseWork);   
    }
    
    public EnterpriseInfoDAO getEnterpriseInfoDAO() {
        return enterpriseInfoDAO;
    }
    
    public List<ComboTreeJson> findPEnterprise(EnterpriseInfoVo enterpriseInfoVo, BasUsers user) throws Exception
    {
        List<ComboTreeJson> list = new ArrayList<ComboTreeJson>();
        StringBuffer hql = new StringBuffer();
        hql.append("from EnterpriseInfo enterprise where 1=1 AND enterpriseId ="+enterpriseInfoVo.getEnterpriseId());
        List<Object> list_=enterpriseInfoDAO.find(hql.toString());
        if(list_ != null && list_.size() > 0){
            for(Object obj : list_){
                EnterpriseInfo enterorise = (EnterpriseInfo)obj;
                ComboTreeJson json = new ComboTreeJson();
                json.setId(String.valueOf(enterorise.getEnterpriseId()));
                json.setText(enterorise.getEnterpriseName());
                if(!(enterpriseInfoVo.getEnterpriseInfoFlag()!=null&&enterpriseInfoVo.getEnterpriseInfoFlag()==true)){
                    getComboChild(json, enterorise, user,enterpriseInfoVo.getEnterpriseInfoFlag());
                }
                list.add(json);
            }
        }
        return list;
    }
    
    private void getComboChild(ComboTreeJson json, EnterpriseInfo enterorise,BasUsers user,Boolean flag) throws Exception
    {
        StringBuffer hql = new StringBuffer();
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            hql.append("from EnterpriseInfo enterprise where 1=1 AND parentEnterpriseId = "+enterorise.getEnterpriseId());
        }
        else if(!user.getSystemId().equals(Contstants.SYSTEMTYPE.ALL))
        {
            hql.append("from EnterpriseInfo enterprise where 1=1 AND parentEnterpriseId ="+enterorise.getEnterpriseId()+" AND enterpriseType ='" + user.getSystemId() + "'");
        }
        else
        {
            hql.append("from EnterpriseInfo enterprise where 1=1 AND parentEnterpriseId ="+enterorise.getEnterpriseId());
        }
        //params.put("parentEnterpriseId", enterorise.getEnterpriseId());
        List<Object> list_=enterpriseInfoDAO.find(hql.toString());
        if(list_ != null && list_.size() > 0){
            List<ComboTreeJson> rows = new ArrayList<ComboTreeJson>();
            for(Object obj : list_){
                EnterpriseInfo enterorise_ = (EnterpriseInfo)obj;
                ComboTreeJson json_ = new ComboTreeJson();
                json_.setId(String.valueOf(enterorise_.getEnterpriseId()));
                json_.setText(enterorise_.getEnterpriseName());
                if(!(flag!=null&&flag==true))
                    getComboChild(json_, enterorise_, user,flag);
                rows.add(json_);
            }
            json.setChildren(rows);
            json.setState("closed");
        }
    }
    /**
     * 校验企业结构
     * */
    
    public boolean enterpriseIsTwoLevel() throws Exception {
        StringBuffer sb=new StringBuffer("SELECT ei.enterprise_id FROM enterprise_info ei WHERE ei.parentEnterprise_id");
        sb.append(" IN(SELECT temp.enterprise_id FROM enterprise_info temp WHERE temp.parentEnterprise_id=1)");
        List list=enterpriseInfoDAO.createSQLQuery(sb.toString());
        if(list!=null&&list.size()>0){
                return true;
        }else{
            return false;
        }
    }
    /**
     * 查询指定企业下的所有子企业
     * @param enterpriseId 指定企业的序号
     * @exception Exception 总异常
     * @return List<EnterpriseInfo> 返回包含企业信息对象的集合
     * */
    
    public List<EnterpriseInfo> findEnterpriseInfoChild(String enterpriseId)
            throws Exception {
        List<Object> list=enterpriseInfoDAO.find("from EnterpriseInfo ei where (ei.parentEnterpriseId="+enterpriseId+" or ei.enterpriseId="+enterpriseId+")");
        List<EnterpriseInfo> ls=new ArrayList();
        EnterpriseInfo ei=null;
        if(list!=null&&list.size()>0)
            for (Object object : list) {
                ei=new EnterpriseInfo();
                BeanUtils.copyProperties(object, ei);
                ls.add(ei);
            }
        return ls;
    }
    
}