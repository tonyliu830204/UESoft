package com.syuesoft.bas.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasDeptDao;
import com.syuesoft.bas.dao.BasJobPropertyDao;
import com.syuesoft.bas.dao.BasPersonnelInformationSetDao;
import com.syuesoft.bas.dao.BasRepairGroupDao;
import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.dao.DefaultDAO;
import com.syuesoft.bas.dao.EnterpriseInfoDAO;
import com.syuesoft.bas.service.BasPersonnelInformationService;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.base.vo.BasStuffVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasDept;
import com.syuesoft.model.BasJobProperty;
import com.syuesoft.model.BasRepairGroup;
import com.syuesoft.model.BasStorehouse;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasStuffJob;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.print.service.PrintService;
import com.syuesoft.qx.dao.BasUsersDao;
import com.syuesoft.role.dao.IRoleDao;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.ComboTreeJson;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.HtmlParse;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 人事资料设定Service实现  
 */
@Service("basPersonnelInformationService")
public class BasPersonnelInformationServiceImpl extends BaseLogServiceImpl
        implements BasPersonnelInformationService
{
    @Autowired
    private BasPersonnelInformationSetDao basPersonnelInformationSetDao;
    @Autowired
    private DefaultDAO defaultDao;
    @SuppressWarnings("unused")
    @Autowired
    private BasJobPropertyDao basJobPropertyDao;
    @SuppressWarnings("unused")
    @Autowired
    private BasRepairGroupDao basRepairGroupDao;
    @SuppressWarnings("unused")
    @Autowired
    private BasStorehouseDAO basStorehouseDAO;
    @Autowired
    private BasDeptDao basDeptDao;
    @Autowired
    private BaseService baseService;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private PrintService printService;
    @Autowired
    private BasStuffDao basStuffDao;
    @Autowired
    private EnterpriseInfoDAO enterpriseInfoDAO;
    @Autowired
    private BasUsersDao basUsersDao; 

    /**
     * 分页 展现员工List 携带查询
     * @param basStuffVo 员工视图对象
     * @param user 登陆用户对象
     * */
    public Json findByParam(BasStuffVo basStuffVo, BasUsers user)
            throws Exception
    {
        Json json = basPersonnelInformationSetDao.findByParam(basStuffVo, user);
        List<BasStuffVo> basStuffVoList = new ArrayList<BasStuffVo>();
        if (json != null)
        {
            if (json.getRows() != null)
            {
                List<Object[]> basStuffList = (List<Object[]>) json.getRows();
                for (Object[] objs : basStuffList)
                {
                    BasStuff basStuff = objs[0] != null ? (BasStuff) objs[0]
                            : null;
                    BasUsers basUsers = objs[1] != null ? (BasUsers) objs[1]
                            : null;
                    BasStuffVo stuffVo = new BasStuffVo();
                    if (basStuff != null)
                    {
                        // 1 1>
                        if (basStuff.getStfId() != null
                                && !"".equals(basStuff.getStfId()))
                        {
                            stuffVo.setStfId(basStuff.getStfId().toString());
                        }
                        if (basStuff.getStfYid() != null
                                && !"".equals(basStuff.getStfYid().trim()))
                        {
                            stuffVo.setStfYid(basStuff.getStfYid());
                        }
                        if (basStuff.getStfName() != null
                                && !"".equals(basStuff.getStfName().trim()))
                        {
                            stuffVo.setStfName(basStuff.getStfName());
                        }
                        if (basStuff.getStfMark() != null
                                && !"".equals(basStuff.getStfMark().trim()))
                        {
                            stuffVo.setStfMark(basStuff.getStfMark());
                        }
                        if (basStuff.getStfSex() != null
                                && !"".equals(basStuff.getStfSex()))
                        {
                            List<BasChilddictionary> childs = baseService
                                    .getBasChilddictionary(Contstants.SEXTYPE.SEX);
                            for (BasChilddictionary child : childs)
                            {
                                if (basStuff.getStfSex().equals(
                                        child.getDataKey()))
                                {
                                    stuffVo.setStfSex(basStuff.getStfSex());
                                    stuffVo
                                            .setStfSexvalue(child
                                                    .getDataValue());
                                }
                            }
                        }
                        if (basStuff.getStfYes() != null
                                && !"".equals(basStuff.getStfYes()))
                        {
                            List<BasChilddictionary> childs = baseService
                                    .getBasChilddictionary(Contstants.SYSTEMUSER.PSTFYES);
                            for (BasChilddictionary child : childs)
                            {
                                if (basStuff.getStfYes().equals(
                                        child.getDataKey()))
                                {
                                    stuffVo.setStfYes(basStuff.getStfYes());
                                    stuffVo
                                            .setStfYesvalue(child
                                                    .getDataValue());
                                }
                            }
                        }
                        // 所属部门
                        if (basStuff.getBasDept() != null
                                && !"".equals(basStuff.getBasDept()))
                        {
                            stuffVo.setDeptId(basStuff.getBasDept().getDeptId()
                                    .toString());
                            stuffVo.setDeptName(basStuff.getBasDept()
                                    .getDeptName());
                            if(basStuff.getEnterpriseInfo()!=null){
                            	if(basStuff.getEnterpriseInfo().getEnterpriseId()!=null){
                            		stuffVo.setEnterpriseId(basStuff.getEnterpriseInfo().getEnterpriseId().toString());
                            		stuffVo.setParentEnterpriseId(basStuff.getEnterpriseInfo().getParentEnterpriseId().toString());
                            	}
                            	if(basStuff.getEnterpriseInfo().getEnterpriseName()!=null
                            			&&basStuff.getEnterpriseInfo().getEnterpriseName().length()>0){
                            		stuffVo.setEnterpriseName(basStuff.getEnterpriseInfo().getEnterpriseName());
                            	}
                            }
                        }
                        // 维修班组
                        if (basStuff.getRepgrpId() != null
                                && !"".equals(basStuff.getRepgrpId()))
                        {
                            BasRepairGroup brg = (BasRepairGroup) basPersonnelInformationSetDao
                                    .getObject(BasRepairGroup.class, basStuff
                                            .getRepgrpId());
                            if (brg != null && !"".equals(brg))
                            {
                                stuffVo.setRepgrpId(brg.getRepgrpId()
                                        .toString());
                                stuffVo.setRepgrpName(brg.getRepgrpName());
                            }
                        }
                        // 销售班组
                        if (basStuff.getRepgrpId2() != null
                                && !"".equals(basStuff.getRepgrpId2()))
                        {
                            stuffVo.setRepgrpId2(basStuff.getRepgrpId2());
                        }
                        // 1 2>
                        if (basStuff.getStfZwgz() != null
                                && !"".equals(basStuff.getStfZwgz().trim()))
                        {
                            stuffVo.setStfZwgz(basStuff.getStfZwgz());
                        }
                        if (basStuff.getStfPhone() != null
                                && !"".equals(basStuff.getStfPhone().trim()))
                        {
                            stuffVo.setStfPhone(basStuff.getStfPhone());
                        }
                        if (basStuff.getStfZxqk() != null
                                && !"".equals(basStuff.getStfZxqk().trim()))
                        {
                            List<BasChilddictionary> childs = baseService
                                    .getBasChilddictionary(Contstants.ZXQKF.ZXQKF);
                            for (BasChilddictionary child : childs)
                            {
                                if (basStuff.getStfZxqk().equals(
                                        child.getDataKey()))
                                {
                                    stuffVo.setStfZxqk(basStuff.getStfZxqk());
                                    stuffVo.setStfZxqkvalue(child
                                            .getDataValue());
                                }
                            }
                        }
                        if (basStuff.getStfTel() != null
                                && !"".equals(basStuff.getStfTel().trim()))
                        {
                            stuffVo.setStfTel(basStuff.getStfTel());
                        }
                        if (basStuff.getStfBirthday() != null
                                && !"".equals(basStuff.getStfBirthday()))
                        {
                            stuffVo.setStfBirthday(basStuff.getStfBirthday()
                                    .toString());
                        }
                        if (basStuff.getStfGj() != null
                                && !"".equals(basStuff.getStfGj()))
                        {
                            stuffVo.setStfGj(basStuff.getStfGj());
                        }
                        if (basStuff.getStfWorkDate() != null
                                && !"".equals(basStuff.getStfWorkDate()))
                        {
                            stuffVo.setStfWorkDate(basStuff.getStfWorkDate()
                                    .toString());
                        }
                        StringBuffer sb=new StringBuffer();
                        StringBuffer sb1=new StringBuffer();
                        // 工作属性
                        if(basStuff.getBasStuffJobs()!=null&&basStuff.getBasStuffJobs().size()>0){
                        	Set<BasStuffJob> set=basStuff.getBasStuffJobs();
                        	if(set!=null&&set.size()>0){
                        	    for (BasStuffJob basStuffJob : set) {
								     sb.append(","+basStuffJob.getId().getBasJobProperty().getJobProId());
								     sb1.append(","+basStuffJob.getId().getBasJobProperty().getJobProName());
							    }
                        		stuffVo.setJobProId(sb.substring(1));
                        		stuffVo.setJobProName(sb1.substring(1));
                        	}
                        }
                        // 2 1>
                        if (basStuff.getStfByyx() != null
                                && !"".equals(basStuff.getStfByyx().trim()))
                        {
                            stuffVo.setStfByyx(basStuff.getStfByyx());
                        }
                        if (basStuff.getStfBysj() != null
                                && !"".equals(basStuff.getStfBysj()))
                        {
                            stuffVo
                                    .setStfBysj(basStuff.getStfBysj()
                                            .toString());
                        }
                        if (basStuff.getStfSxzy() != null
                                && !"".equals(basStuff.getStfSxzy().trim()))
                        {
                            stuffVo.setStfSxzy(basStuff.getStfSxzy());
                        }
                        if (basStuff.getStfWhcd() != null
                                && !"".equals(basStuff.getStfWhcd().trim()))
                        {
                            stuffVo.setStfWhcd(basStuff.getStfWhcd());
                        }
                        if (basStuff.getStfGznx() != null
                                && !"".equals(basStuff.getStfGznx().trim()))
                        {
                            stuffVo.setStfGznx(basStuff.getStfGznx());
                        }
                        if (basStuff.getStfJsdj() != null
                                && !"".equals(basStuff.getStfJsdj().trim()))
                        {
                            stuffVo.setStfJsdj(basStuff.getStfJsdj());
                        }
                        if (basStuff.getStfYhbyzs() != null
                                && !"".equals(basStuff.getStfYhbyzs().trim()))
                        {
                            stuffVo.setStfYhbyzs(basStuff.getStfYhbyzs());
                        }

                        // 2 2>
                        if (basStuff.getStfSfzhm() != null
                                && !"".equals(basStuff.getStfSfzhm()))
                        {
                            stuffVo.setStfSfzhm(basStuff.getStfSfzhm());
                        }
                        if (basStuff.getStfSg() != null
                                && !"".equals(basStuff.getStfSg().trim()))
                        {
                            stuffVo.setStfSg(basStuff.getStfSg());
                        }
                        if (basStuff.getStfTz() != null
                                && !"".equals(basStuff.getStfTz().trim()))
                        {
                            stuffVo.setStfTz(basStuff.getStfTz());
                        }
                        if (basStuff.getStfSl() != null
                                && !"".equals(basStuff.getStfSl().trim()))
                        {
                            stuffVo.setStfSl(basStuff.getStfSl());
                        }
                        if (basStuff.getStfXx() != null
                                && !"".equals(basStuff.getStfXx().trim()))
                        {
                            stuffVo.setStfXx(basStuff.getStfXx());
                        }
                        if (basStuff.getStfHyzk() != null
                                && !"".equals(basStuff.getStfHyzk().trim()))
                        {
                            stuffVo.setStfHyzk(basStuff.getStfHyzk());
                        }
                        if (basStuff.getStfHkszd() != null
                                && !"".equals(basStuff.getStfHkszd().trim()))
                        {
                            stuffVo.setStfHkszd(basStuff.getStfHkszd());
                        }
                        if (basStuff.getStfXjzdz() != null
                                && !"".equals(basStuff.getStfXjzdz().trim()))
                        {
                            stuffVo.setStfXjzdz(basStuff.getStfXjzdz());
                        }

                        // 2 3>
                        if (basStuff.getStfMz() != null
                                && !"".equals(basStuff.getStfMz().trim()))
                        {
                            stuffVo.setStfMz(basStuff.getStfMz());
                        }
                        if (basStuff.getStfZzmm() != null
                                && !"".equals(basStuff.getStfZzmm().trim()))
                        {
                            stuffVo.setStfZzmm(basStuff.getStfZzmm());
                        }
                        if (basStuff.getStfWysp() != null
                                && !"".equals(basStuff.getStfWysp().trim()))
                        {
                            stuffVo.setStfWysp(basStuff.getStfWysp());
                        }
                        if (basStuff.getStfJsjsp() != null
                                && !"".equals(basStuff.getStfJsjsp().trim()))
                        {
                            stuffVo.setStfJsjsp(basStuff.getStfJsjsp());
                        }
                        if (basStuff.getStfDzyx() != null
                                && !"".equals(basStuff.getStfDzyx().trim()))
                        {
                            stuffVo.setStfDzyx(basStuff.getStfDzyx());
                        }
                        if (basStuff.getStfTc() != null
                                && !"".equals(basStuff.getStfTc().trim()))
                        {
                            stuffVo.setStfTc(basStuff.getStfTc());
                        }
                        if (basStuff.getStfAh() != null
                                && !"".equals(basStuff.getStfAh().trim()))
                        {
                            stuffVo.setStfAh(basStuff.getStfAh());
                        }

                        // 2 4>
                        if (basStuff.getStfDbrxm() != null
                                && !"".equals(basStuff.getStfDbrxm().trim()))
                        {
                            stuffVo.setStfDbrxm(basStuff.getStfDbrxm());
                        }
                        if (basStuff.getStfDbrsfzhm() != null
                                && !"".equals(basStuff.getStfDbrsfzhm().trim()))
                        {
                            stuffVo.setStfDbrsfzhm(basStuff.getStfDbrsfzhm());
                        }
                        if (basStuff.getStfPoxm() != null
                                && !"".equals(basStuff.getStfPoxm().trim()))
                        {
                            stuffVo.setStfPoxm(basStuff.getStfPoxm());
                        }
                        if (basStuff.getStfPosfzhm() != null
                                && !"".equals(basStuff.getStfPosfzhm().trim()))
                        {
                            stuffVo.setStfPosfzhm(basStuff.getStfPosfzhm());
                        }
                        if (basStuff.getStfYjrq() != null
                                && !"".equals(basStuff.getStfYjrq()))
                        {
                            stuffVo.setStfYjrq(basStuff.getStfYjrq().toString());
                        }

                        if (basStuff.getStfYjje() != null
                                && !"".equals(basStuff.getStfYjje().trim()))
                        {
                            stuffVo.setStfYjje(basStuff.getStfYjje());
                        }
                        if (basStuff.getStfYjbz() != null
                                && !"".equals(basStuff.getStfYjbz().trim()))
                        {
                            stuffVo.setStfYjbz(basStuff.getStfYjbz());
                        }
                    }
                    if (basUsers != null)
                    {
                        if (basUsers.getUserName() != null
                                && !"".equals(basUsers.getUserName().trim()))
                        {
                            stuffVo.setLoginName(basUsers.getUserName());
                        }
                        if (basUsers.getSystemId() != null
                                && !"".equals(basUsers.getSystemId().trim()))
                        {
                            List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.SYSTEMTYPE.SYSTEM);
                            for (BasChilddictionary child : childs)
                            {
                                if (basUsers.getSystemId().equals(child.getDataKey()))
                                {
                                    stuffVo.setSystemId(basUsers.getSystemId());
                                    stuffVo.setSystemValue(child.getDataValue());
                                }
                            }
                        }
                        if (basUsers.getLeavl() != null
                                && !"".equals(basUsers.getLeavl().trim()))
                        {
                            stuffVo.setLeavl(basUsers.getLeavl());
                        }
                    }
                    basStuffVoList.add(stuffVo);
                }
            }
        }
        json.setRows(basStuffVoList);
        return json;
    }

    /**
     * 查询所有员工
     * @exception Exception 总异常
     * return List<BasStuff> 返回包含员工实体的集合
     * */
    
    public List<BasStuff> getBasStuff()
    {
        return basPersonnelInformationSetDao.getBasStuff();
    }

    /**
     * 查询指定公司的部门
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return List<ComboBoxJson> 返回包含部门Id和Name的ComboBoxJson对象集合
     * */
    
    public List<ComboBoxJson> findAllDept(BasStuffVo stuffVo) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList();
        List<Object[]> tempList=basPersonnelInformationSetDao.createSQLQuery(
        			"SELECT bd.DEPT_ID,bd.DEPT_NAME FROM bas_dept bd INNER JOIN enterprise_info ei ON ei.enterprise_id=bd.enterprise_id where ei.enterprise_id="+stuffVo.getEnterpriseId());
        ComboBoxJson json = null;
        if(tempList!=null&&tempList.size()>0)
        	for (Object[] obj : tempList) {
        		json=new ComboBoxJson();
        		if(obj[0]!=null&&obj[0].toString().length()>0)
        			json.setId(obj[0].toString());
        		if(obj[1]!=null&&obj[1].toString().length()>0)
        			json.setText(obj[1].toString());
                list.add(json);
			}
        return list;
    }
    /**
     * 查询集团公司及其子公司
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return List<ComboTreeJson> 返回包含企业Id和Name的ComboTreeJson对象集合
     * */
    
	public List<ComboTreeJson> findAllCompany(BasStuffVo stuffVo) throws Exception {
    	List<ComboTreeJson> list = new ArrayList<ComboTreeJson>();
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ei.enterprise_id,ei.enterprise_name FROM enterprise_info ei WHERE ei.del_tag='n' and ei.enterprise_id="+stuffVo.getEnterpriseId());
        List<Object[]> tempList= basPersonnelInformationSetDao.createSQLQuery(sb.toString());
        if (tempList != null && tempList.size() > 0)
            for (Object[] obj: tempList)
            {
                ComboTreeJson json = new ComboTreeJson();
                if(obj[0]!=null&&obj[0].toString().length()>0)
                	json.setId(obj[0].toString());
                if(obj[1]!=null&&obj[1].toString().length()>0)
                	json.setText(obj[1].toString());
                	getComboTreeMenuChild(json,obj[0].toString());
                list.add(json);
            }
        return list;
	}
    /**
     * 查询菜单ComboTree结构下拉框子级节点
     * 
     * @param json
     * @param menuVo
     */
    private void getComboTreeMenuChild(ComboTreeJson json,String parentId) throws Exception
    {
        List<Object[]> tempList= basPersonnelInformationSetDao.createSQLQuery(
        			"SELECT ei.enterprise_id,ei.enterprise_name FROM enterprise_info ei WHERE ei.del_tag='n' and ei.parentEnterprise_id ="+parentId);
        if (tempList != null && tempList.size() > 0){
        	List<ComboTreeJson> rows = new ArrayList<ComboTreeJson>();
        	for (Object[] obj: tempList)
        	{
	    		 ComboTreeJson json_ = new ComboTreeJson();
	    		 if(obj[0]!=null&&obj[0].toString().length()>0)
                	json_.setId(obj[0].toString());
                 if(obj[1]!=null&&obj[1].toString().length()>0)
                	json_.setText(obj[1].toString());
        		 //getComboTreeMenuChild(json_, obj[0].toString());
        		   rows.add(json_);
        	}
        	json.setChildren(rows);
        	json.setState("closed");
        }
    }
    /**
     * 查询工作属性
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return List<ComboBoxJson> 返回包含工作属性Id和Name的ComboBoxJson对象集合
     * */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<ComboBoxJson> findAllBasJobProperty(BasStuffVo stuffVo)
    {
        List<ComboBoxJson> list = new ArrayList();
        List<BasJobProperty> jobPropertyList = defaultDao.getObjList("from BasJobProperty bjp where bjp.jobProName in(select distinct temp.jobProName from BasJobProperty temp)");
        for (BasJobProperty bd : jobPropertyList){
            ComboBoxJson json = new ComboBoxJson();
            json.setId(bd.getJobProId().toString());
            json.setText(bd.getJobProName());
            list.add(json);
        }
        return list;
    }

    /**
     * 查询车间部门
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return List<ComboBoxJson> 返回包含车间部门Id和Name的ComboBoxJson对象集合 
     * */
    @SuppressWarnings("unchecked")
    public List<ComboBoxJson> findAllCJDept(BasStuffVo stuffVo)
    {
        List<ComboBoxJson> list = new ArrayList();
        List<BasRepairGroup> brg = defaultDao.getObjList("from BasRepairGroup brg where brg.enterpriseId="+stuffVo.getEnterpriseId());
        ComboBoxJson jso = new ComboBoxJson();
        for (BasRepairGroup basRepairGroup : brg)
        {
            ComboBoxJson json = new ComboBoxJson();
            json.setId(basRepairGroup.getRepgrpId().toString());
            json.setText(basRepairGroup.getRepgrpName());
            list.add(json);
        }
        return list;
    }

    /**
     * 查询仓别分类
     * @exception Exception 总异常
     * @return List<ComboBoxJson> 返回包含仓别分类Id和Name的ComboBoxJson对象集合
     * */
    @SuppressWarnings("unchecked")
    public List<ComboBoxJson> findAllCBFL(String enterpriseId)
    {
        List<ComboBoxJson> list = new ArrayList();
        List<BasStorehouse> bsh = defaultDao.getObjList("from BasStorehouse bs where bs.enterpriseId="+enterpriseId);
        for (BasStorehouse basStorehouse : bsh)
        {
            ComboBoxJson json = new ComboBoxJson();
            json.setId(basStorehouse.getStoreId().toString());
            json.setText(basStorehouse.getStoreName());
            list.add(json);
        }
        return list;
    }

    /**
     * 获取员工持有的属性
     * @param basStuffVo 员工视图对象
     * @exception Exception 总异常
     * @return List 返回包含员工属性Id的集合
     * */
    @SuppressWarnings("unchecked")
    
    public List selectJobProId(BasStuffVo basStuffVo)
    {
        List selectId = basPersonnelInformationSetDao.selectJobProId(basStuffVo);
        return selectId;
    }

    /**
     * 人事资料新增
     * @param stuffVo 员工视图对象
     * @param user 登陆用户对象
     * @exception Exception 总异常
     * */
    
    @Log(moduleName = "人事信息设定", opertype = "保存人事信息", content = "保存人事信息")
    public synchronized void save(BasStuffVo stuffVo, BasUsers user)
            throws Exception
    {
        BasStuff stuff = new BasStuff();
        stuff.setDelTag("n");
        // 1 1>
        if (stuffVo.getStfId() != null && !"".equals(stuffVo.getStfId()))
        {
            stuff.setStfId(Long.parseLong(stuffVo.getStfId()));
        }
        // if (stuffVo.getStfYid() != null &&
        // !"".equals(stuffVo.getStfYid().trim())) {
        String stfyid = null;
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
            stfyid = CreateID.createId("personnelInformation", "UE");
        else if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMIN))
            stfyid = CreateID.createId("personnelInformation", "YG");
        else
        	stfyid = CreateID.createId("personnelInformation", "FD");
        stuff.setStfYid(stfyid);
        stuffVo.setStfYid(stfyid);
        // }
        if (stuffVo.getStfName() != null
                && !"".equals(stuffVo.getStfName().trim()))
        {
            stuff.setStfName(stuffVo.getStfName());
        }
        if (stuffVo.getStfMark() != null
                && !"".equals(stuffVo.getStfMark().trim()))
        {
            stuff.setStfMark(stuffVo.getStfMark());
        }
        if (stuffVo.getStfSex() != null && !"".equals(stuffVo.getStfSex()))
        {
            stuff.setStfSex(stuffVo.getStfSex());

        }
        if (stuffVo.getStfYes() != null && !"".equals(stuffVo.getStfYes()))
        {
            stuff.setStfYes(stuffVo.getStfYes());
        }
        // 设置注销情况
        if (stuffVo.getStfZxqk() == null || "".equals(stuffVo.getStfZxqk()))
        {
            stuff.setStfZxqk(Contstants.ZXQKF.INSERVICE);
        }

        // 所属部门
        if (stuffVo.getDeptId() != null && !"".equals(stuffVo.getDeptId()))
        {
            BasDept dept = basDeptDao.getBasDept(Short.parseShort(stuffVo.getDeptId()));
            stuff.setBasDept(dept);
        }
        // 所属公司
        if (stuffVo.getEnterpriseId() != null && !"".equals(stuffVo.getEnterpriseId()))
        {
            EnterpriseInfo enterpriseInfo = enterpriseInfoDAO.getEnterpriseInfo(Integer.parseInt(stuffVo.getEnterpriseId()));
            stuff.setEnterpriseInfo(enterpriseInfo);
        }
        // 维修班组
        if (stuffVo.getRepgrpId() != null && !"".equals(stuffVo.getRepgrpId()))
        {
            stuff.setRepgrpId(Short.parseShort(stuffVo.getRepgrpId()));
        }
        // 销售班组
        if (stuffVo.getRepgrpId2() != null && !"".equals(stuffVo.getRepgrpId2()))
        {
            stuff.setRepgrpId2(stuffVo.getRepgrpId2());
        }
        // 1 2>
        if (stuffVo.getStfZwgz() != null
                && !"".equals(stuffVo.getStfZwgz().trim()))
        {
            stuff.setStfZwgz(stuffVo.getStfZwgz());
        }
        if (stuffVo.getStfPhone() != null
                && !"".equals(stuffVo.getStfPhone().trim()))
        {
            stuff.setStfPhone(stuffVo.getStfPhone());
        }

        if (stuffVo.getStfTel() != null
                && !"".equals(stuffVo.getStfTel().trim()))
        {
            stuff.setStfTel(stuffVo.getStfTel());
        }
        if (stuffVo.getStfBirthday() != null
                && !"".equals(stuffVo.getStfBirthday()))
        {
            stuff.setStfBirthday(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(stuffVo.getStfBirthday()));
        }
        if (stuffVo.getStfGj() != null && !"".equals(stuffVo.getStfGj()))
        {
            stuff.setStfGj(stuffVo.getStfGj());
        }
        if (stuffVo.getStfWorkDate() != null
                && !"".equals(stuffVo.getStfWorkDate()))
        {
            stuff.setStfWorkDate(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(stuffVo.getStfWorkDate()));
        }

        // 工作属性
        // 2 1>
        if (stuffVo.getStfByyx() != null
                && !"".equals(stuffVo.getStfByyx().trim()))
        {
            stuff.setStfByyx(stuffVo.getStfByyx());
        }
        if (stuffVo.getStfBysj() != null && !"".equals(stuffVo.getStfBysj()))
        {
            stuff.setStfBysj(new SimpleDateFormat("yyyy-MM-dd").parse(stuffVo
                    .getStfBysj()));
        }
        if (stuffVo.getStfSxzy() != null
                && !"".equals(stuffVo.getStfSxzy().trim()))
        {
            stuff.setStfSxzy(stuffVo.getStfSxzy());
        }
        if (stuffVo.getStfWhcd() != null
                && !"".equals(stuffVo.getStfWhcd().trim()))
        {
            stuff.setStfWhcd(stuffVo.getStfWhcd());
        }
        if (stuffVo.getStfGznx() != null
                && !"".equals(stuffVo.getStfGznx().trim()))
        {
            stuff.setStfGznx(stuffVo.getStfGznx());
        }
        if (stuffVo.getStfJsdj() != null
                && !"".equals(stuffVo.getStfJsdj().trim()))
        {
            stuff.setStfJsdj(stuffVo.getStfJsdj());
        }
        if (stuffVo.getStfYhbyzs() != null
                && !"".equals(stuffVo.getStfYhbyzs().trim()))
        {
            stuff.setStfYhbyzs(stuffVo.getStfYhbyzs());
        }

        // 2 2>
        if (stuffVo.getStfSfzhm() != null && !"".equals(stuffVo.getStfSfzhm()))
        {
            stuff.setStfSfzhm(stuffVo.getStfSfzhm());
        }
        if (stuffVo.getStfSg() != null && !"".equals(stuffVo.getStfSg().trim()))
        {
            stuff.setStfSg(stuffVo.getStfSg());
        }
        if (stuffVo.getStfTz() != null && !"".equals(stuffVo.getStfTz().trim()))
        {
            stuff.setStfTz(stuffVo.getStfTz());
        }
        if (stuffVo.getStfSl() != null && !"".equals(stuffVo.getStfSl().trim()))
        {
            stuff.setStfSl(stuffVo.getStfSl());
        }
        if (stuffVo.getStfXx() != null && !"".equals(stuffVo.getStfXx().trim()))
        {
            stuff.setStfXx(stuffVo.getStfXx());
        }
        if (stuffVo.getStfHyzk() != null
                && !"".equals(stuffVo.getStfHyzk().trim()))
        {
            stuff.setStfHyzk(stuffVo.getStfHyzk());
        }
        if (stuffVo.getStfHkszd() != null
                && !"".equals(stuffVo.getStfHkszd().trim()))
        {
            stuff.setStfHkszd(stuffVo.getStfHkszd());
        }
        if (stuffVo.getStfXjzdz() != null
                && !"".equals(stuffVo.getStfXjzdz().trim()))
        {
            stuff.setStfXjzdz(stuffVo.getStfXjzdz());
        }

        // 2 3>
        if (stuffVo.getStfMz() != null && !"".equals(stuffVo.getStfMz().trim()))
        {
            stuff.setStfMz(stuffVo.getStfMz());
        }
        if (stuffVo.getStfZzmm() != null
                && !"".equals(stuffVo.getStfZzmm().trim()))
        {
            stuff.setStfZzmm(stuffVo.getStfZzmm());
        }
        if (stuffVo.getStfWysp() != null
                && !"".equals(stuffVo.getStfWysp().trim()))
        {
            stuff.setStfWysp(stuffVo.getStfWysp());
        }
        if (stuffVo.getStfJsjsp() != null
                && !"".equals(stuffVo.getStfJsjsp().trim()))
        {
            stuff.setStfJsjsp(stuffVo.getStfJsjsp());
        }
        if (stuffVo.getStfDzyx() != null
                && !"".equals(stuffVo.getStfDzyx().trim()))
        {
            stuff.setStfDzyx(stuffVo.getStfDzyx());
        }
        if (stuffVo.getStfTc() != null && !"".equals(stuffVo.getStfTc().trim()))
        {
            stuff.setStfTc(stuffVo.getStfTc());
        }
        if (stuffVo.getStfAh() != null && !"".equals(stuffVo.getStfAh().trim()))
        {
            stuff.setStfAh(stuffVo.getStfAh());
        }
        // 2 4>
        if (stuffVo.getStfDbrxm() != null
                && !"".equals(stuffVo.getStfDbrxm().trim()))
        {
            stuff.setStfDbrxm(stuffVo.getStfDbrxm());
        }
        if (stuffVo.getStfDbrsfzhm() != null
                && !"".equals(stuffVo.getStfDbrsfzhm().trim()))
        {
            stuff.setStfDbrsfzhm(stuffVo.getStfDbrsfzhm());
        }
        if (stuffVo.getStfPoxm() != null
                && !"".equals(stuffVo.getStfPoxm().trim()))
        {
            stuff.setStfPoxm(stuffVo.getStfPoxm());
        }
        if (stuffVo.getStfPosfzhm() != null
                && !"".equals(stuffVo.getStfPosfzhm().trim()))
        {
            stuff.setStfPosfzhm(stuffVo.getStfPosfzhm());
        }
        if (stuffVo.getStfYjrq() != null
                && !"".equals(stuffVo.getStfYjrq().trim()))
        {
            stuff.setStfYjrq(new SimpleDateFormat("yyyy-MM-dd").parse(stuffVo
                    .getStfYjrq()));
        }
        if (stuffVo.getStfYjje() != null
                && !"".equals(stuffVo.getStfYjje().trim()))
        {
            stuff.setStfYjje(stuffVo.getStfYjje());
        }
        if (stuffVo.getStfYjbz() != null
                && !"".equals(stuffVo.getStfYjbz().trim()))
        {
            stuff.setStfYjbz(stuffVo.getStfYjbz());
        }
        stuff.setStfNo(0);
        basPersonnelInformationSetDao.save(stuff, stuffVo, user);
        setContent("【"+stuffVo.getEnterpriseName()+"】新增【"+ stuffVo.getStfYid() + "  " + stuffVo.getStfName()+"】人事信息");
    }

    /**
     * 人事资料删除
     * @param stuffVo 员工视图对象
     * @exception  Exception 总异常
     * @return  boolean true为删除成功，false为员工序号不完整，删除失败
     * */
    
    @Log(moduleName = "人事信息设定", opertype = "删除人事信息", content = "删除人事信息")
    public boolean delete(BasStuffVo stuffVo) throws Exception
    {
    	if(!(stuffVo.getStfId()!=null&&stuffVo.getStfId().length()>0))
    		return false;
    	basPersonnelInformationSetDao.executeSQL("delete from bas_user_role where user_id="+stuffVo.getStfId());
    	basPersonnelInformationSetDao.executeSQL("delete from bas_users where stf_id="+stuffVo.getStfId());
    	basPersonnelInformationSetDao.executeSQL("delete from bas_stuff_job where stf_id="+stuffVo.getStfId());
    	//basPersonnelInformationSetDao.executeSQL("delete from bas_stuff where stf_id="+id);
    	basPersonnelInformationSetDao.executeSQL("update bas_stuff set del_tag='y' where stf_id="+stuffVo.getStfId());
//    	roleDao.deleteUserRoleByUserId(Long.parseLong(id));
//    	basPersonnelInformationSetDao.Delete(id);
//    	roleDao.executeSQL("delete from bas_stuff_job where STF_ID="+id);
//    	basUsersDao.delete(basUsersDao.get("from BasUsers bu where bu.basStuff.stfId="+id));
//    	basStuffDao.delete(basStuffDao.get("from BasStuff bs where bs.stfId="+id));
        setContent("【"+stuffVo.getEnterpriseName()+"】删除【"+ stuffVo.getStfYid() + "  " + stuffVo.getStfName()+"】人事信息");
        return true;
    }
    /**
     * 人事资料修改
     * @param stuffVo 员工视图对象
     * @param user 登陆用户对象
     * @exception Exception 总异常
     * */
    @Log(moduleName = "人事信息设定", opertype = "更新人事信息", content = "更新人事信息")
    public void update(BasStuffVo stuffVo, BasUsers user) throws Exception
    {
        BasStuff stuff =null;
        // 1 1>
        if (stuffVo.getStfId() != null && !"".equals(stuffVo.getStfId()))
        {
        	 stuff=basStuffDao.get("from BasStuff bs where bs.stfId="+stuffVo.getStfId());
        }
        if (stuffVo.getStfYid() != null
                && !"".equals(stuffVo.getStfYid().trim()))
        {
            stuff.setStfYid(stuffVo.getStfYid());
        }
        if (stuffVo.getStfName() != null
                && !"".equals(stuffVo.getStfName().trim()))
        {
            stuff.setStfName(stuffVo.getStfName());
        }
        if (stuffVo.getStfMark() != null
                && !"".equals(stuffVo.getStfMark().trim()))
        {
            stuff.setStfMark(stuffVo.getStfMark());
        }
        if (stuffVo.getStfSex() != null && !"".equals(stuffVo.getStfSex()))
        {
            stuff.setStfSex(stuffVo.getStfSex());
        }
        if (stuffVo.getStfYes() != null && !"".equals(stuffVo.getStfYes()))
        {
            stuff.setStfYes(stuffVo.getStfYes());
        }
        // 所属部门
        if (stuffVo.getDeptId() != null && !"".equals(stuffVo.getDeptId()))
        {
            BasDept dept = new BasDept();
            dept.setDeptId((Short.parseShort(stuffVo.getDeptId())));
            stuff.setBasDept(dept);
        }
        // 所属公司
        if (stuffVo.getEnterpriseId() != null && !"".equals(stuffVo.getEnterpriseId()))
        {
            EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
            enterpriseInfo.setEnterpriseId(Integer.parseInt(stuffVo.getEnterpriseId()));
            if(!stuff.getEnterpriseInfo().getEnterpriseId().toString().equals(stuffVo.getEnterpriseId())){
            	roleDao.deleteUserRoleByUserId(stuff.getStfId());
            }
            stuff.setEnterpriseInfo(enterpriseInfo);
        }
        // 维修班组
        if (stuffVo.getRepgrpId() != null && !"".equals(stuffVo.getRepgrpId()))
        {
            stuff.setRepgrpId(Short.parseShort(stuffVo.getRepgrpId()));
        }
        // 销售班组
        if (stuffVo.getRepgrpId2() != null && !"".equals(stuffVo.getRepgrpId2()))
        {
            stuff.setRepgrpId2(stuffVo.getRepgrpId2());
        }
        // 注销情况
        if (stuffVo.getStfZxqk() != null
                && !"".equals(stuffVo.getStfZxqk().trim()))
        {
            stuff.setStfZxqk(stuffVo.getStfZxqk());
        }
        // 1 2>
        if (stuffVo.getStfZwgz() != null
                && !"".equals(stuffVo.getStfZwgz().trim()))
        {
            stuff.setStfZwgz(stuffVo.getStfZwgz());
        }
        if (stuffVo.getStfPhone() != null
                && !"".equals(stuffVo.getStfPhone().trim()))
        {
            stuff.setStfPhone(stuffVo.getStfPhone());
        }
        if (stuffVo.getStfTel() != null
                && !"".equals(stuffVo.getStfTel().trim()))
        {
            stuff.setStfTel(stuffVo.getStfTel());
        }
        if (stuffVo.getStfBirthday() != null
                && !"".equals(stuffVo.getStfBirthday()))
        {
            stuff.setStfBirthday(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(stuffVo.getStfBirthday()));
        }
        if (stuffVo.getStfGj() != null && !"".equals(stuffVo.getStfGj()))
        {
            stuff.setStfGj(stuffVo.getStfGj());
        }
        if (stuffVo.getStfWorkDate() != null
                && !"".equals(stuffVo.getStfWorkDate()))
        {
            stuff.setStfWorkDate(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(stuffVo.getStfWorkDate()));
        }
        // 工作属性
        // 2 1>
        if (stuffVo.getStfByyx() != null
                && !"".equals(stuffVo.getStfByyx().trim()))
        {
            stuff.setStfByyx(stuffVo.getStfByyx());
        }
        if (stuffVo.getStfBysj() != null && !"".equals(stuffVo.getStfBysj()))
        {
            stuff.setStfBysj(new SimpleDateFormat("yyyy-MM-dd").parse(stuffVo
                    .getStfBysj()));
        }
        if (stuffVo.getStfSxzy() != null
                && !"".equals(stuffVo.getStfSxzy().trim()))
        {
            stuff.setStfSxzy(stuffVo.getStfSxzy());
        }
        if (stuffVo.getStfWhcd() != null
                && !"".equals(stuffVo.getStfWhcd().trim()))
        {
            stuff.setStfWhcd(stuffVo.getStfWhcd());
        }
        if (stuffVo.getStfGznx() != null
                && !"".equals(stuffVo.getStfGznx().trim()))
        {
            stuff.setStfGznx(stuffVo.getStfGznx());
        }
        if (stuffVo.getStfJsdj() != null
                && !"".equals(stuffVo.getStfJsdj().trim()))
        {
            stuff.setStfJsdj(stuffVo.getStfJsdj());
        }
        if (stuffVo.getStfYhbyzs() != null
                && !"".equals(stuffVo.getStfYhbyzs().trim()))
        {
            stuff.setStfYhbyzs(stuffVo.getStfYhbyzs());
        }
        // 2 2>
        if (stuffVo.getStfSfzhm() != null && !"".equals(stuffVo.getStfSfzhm()))
        {
            stuff.setStfSfzhm(stuffVo.getStfSfzhm());
        }
        if (stuffVo.getStfSg() != null && !"".equals(stuffVo.getStfSg().trim()))
        {
            stuff.setStfSg(stuffVo.getStfSg());
        }
        if (stuffVo.getStfTz() != null && !"".equals(stuffVo.getStfTz().trim()))
        {
            stuff.setStfTz(stuffVo.getStfTz());
        }
        if (stuffVo.getStfSl() != null && !"".equals(stuffVo.getStfSl().trim()))
        {
            stuff.setStfSl(stuffVo.getStfSl());
        }
        if (stuffVo.getStfXx() != null && !"".equals(stuffVo.getStfXx().trim()))
        {
            stuff.setStfXx(stuffVo.getStfXx());
        }
        if (stuffVo.getStfHyzk() != null
                && !"".equals(stuffVo.getStfHyzk().trim()))
        {
            stuff.setStfHyzk(stuffVo.getStfHyzk());
        }
        if (stuffVo.getStfHkszd() != null
                && !"".equals(stuffVo.getStfHkszd().trim()))
        {
            stuff.setStfHkszd(stuffVo.getStfHkszd());
        }
        if (stuffVo.getStfXjzdz() != null
                && !"".equals(stuffVo.getStfXjzdz().trim()))
        {
            stuff.setStfXjzdz(stuffVo.getStfXjzdz());
        }
        // 2 3>
        if (stuffVo.getStfMz() != null && !"".equals(stuffVo.getStfMz().trim()))
        {
            stuff.setStfMz(stuffVo.getStfMz());
        }
        if (stuffVo.getStfZzmm() != null
                && !"".equals(stuffVo.getStfZzmm().trim()))
        {
            stuff.setStfZzmm(stuffVo.getStfZzmm());
        }
        if (stuffVo.getStfWysp() != null
                && !"".equals(stuffVo.getStfWysp().trim()))
        {
            stuff.setStfWysp(stuffVo.getStfWysp());
        }
        if (stuffVo.getStfJsjsp() != null
                && !"".equals(stuffVo.getStfJsjsp().trim()))
        {
            stuff.setStfJsjsp(stuffVo.getStfJsjsp());
        }
        if (stuffVo.getStfDzyx() != null
                && !"".equals(stuffVo.getStfDzyx().trim()))
        {
            stuff.setStfDzyx(stuffVo.getStfDzyx());
        }
        if (stuffVo.getStfTc() != null && !"".equals(stuffVo.getStfTc().trim()))
        {
            stuff.setStfTc(stuffVo.getStfTc());
        }
        if (stuffVo.getStfAh() != null && !"".equals(stuffVo.getStfAh().trim()))
        {
            stuff.setStfAh(stuffVo.getStfAh());
        }
        // 2 4>
        if (stuffVo.getStfDbrxm() != null
                && !"".equals(stuffVo.getStfDbrxm().trim()))
        {
            stuff.setStfDbrxm(stuffVo.getStfDbrxm());
        }
        if (stuffVo.getStfDbrsfzhm() != null
                && !"".equals(stuffVo.getStfDbrsfzhm().trim()))
        {
            stuff.setStfDbrsfzhm(stuffVo.getStfDbrsfzhm());
        }
        if (stuffVo.getStfPoxm() != null
                && !"".equals(stuffVo.getStfPoxm().trim()))
        {
            stuff.setStfPoxm(stuffVo.getStfPoxm());
        }
        if (stuffVo.getStfPosfzhm() != null
                && !"".equals(stuffVo.getStfPosfzhm().trim()))
        {
            stuff.setStfPosfzhm(stuffVo.getStfPosfzhm());
        }
        if (stuffVo.getStfYjrq() != null
                && !"".equals(stuffVo.getStfYjrq().trim()))
        {
            stuff.setStfYjrq(new SimpleDateFormat("yyyy-MM-dd").parse(stuffVo
                    .getStfYjrq()));
        }
        if (stuffVo.getStfYjje() != null
                && !"".equals(stuffVo.getStfYjje().trim()))
        {
            stuff.setStfYjje(stuffVo.getStfYjje());
        }
        if (stuffVo.getStfYjbz() != null
                && !"".equals(stuffVo.getStfYjbz().trim()))
        {
            stuff.setStfYjbz(stuffVo.getStfYjbz());
        }
        stuff.setStfNo(0);
        basStuffDao.merge(stuff);
        basPersonnelInformationSetDao.Update(stuff, stuffVo);
        setContent("【"+stuffVo.getEnterpriseName()+"】修改【"+ stuffVo.getStfYid() + "  " + stuffVo.getStfName()+"】人事信息");
    }
    /**
     * 注销指定用户
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * */
    public void updateBasStuff(BasStuffVo stuffVo) throws Exception
    {
        basPersonnelInformationSetDao.updateBasStuff(stuffVo);
    }
    /**
     * 变更用户
     * @param basStuffVo 员工视图对象
     * @exception Exception  总异常
     * @return Msg 返回消息对象
     * */
    
    @Log(moduleName = "人事信息设定", opertype = "更改人事信息状态", content = "更改人事信息状态")
    public Msg changeUserStfYid(BasStuffVo basStuffVo)
    {
        Msg msg = new Msg();
        try
        {
            BasStuff basStuff = basPersonnelInformationSetDao
                    .getObjectBystfYid(basStuffVo);
            if (basStuff != null)
            {
                basStuff.setStfYid(basStuffVo.getStfYid());
                basPersonnelInformationSetDao.changeUserStfYid(basStuff);
                msg.setMsg("变更用户成功");
                msg.setSuccess(true);
            }
            setContent("更改【" + basStuffVo.getStfYid() + "】的人事信息状态");
        }
        catch(Exception e)
        {
            msg.setMsg("变更用户失败");
            msg.setSuccess(false);
        }
        return msg;
    }
    /**
     * 校验员工编号
     * @param stuffVo 员工视图对象
     * @exception Exception  总异常
     * @return boolean  true为已存在，false为不存在
     * */
    
	public boolean findStfYid(BasStuffVo stuffVo) throws Exception {
    	  List list = basPersonnelInformationSetDao.findStfYid(stuffVo);
          if (list != null && list.size() > 0)
          {
              return true;
          }
          return false;
	}
    /**
     * 校验员工姓名
     * @param stuffVo 员工视图对象
     * @exception Exception  总异常
     * @return boolean  true为已存在，false为不存在
     * */
	
    public boolean findUserName(BasStuffVo stuffVo, BasUsers user)
    {
        List list = basPersonnelInformationSetDao.findUserName(stuffVo, user);
        if (list != null && list.size() > 0)
        {
            return true;
        }
        return false;
    }
	/**
     * 查询指定登陆用户
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return BasStuffVo 返回员工视图对象
     * */
    
    public BasStuffVo findBasStuffByYid(BasStuffVo stuffVo)
    {
        BasUsers basUsers = basPersonnelInformationSetDao
                .findBasStuffByYid(stuffVo);
        // stuffVo.setAccountState(basUsers.getAccountState());
        stuffVo.setUserId(basUsers.getUserId());
        stuffVo.setLoginName(basUsers.getUserName());
        stuffVo.setLoginPassword(basUsers.getUserPasswd());
        stuffVo.setSystemId(basUsers.getSystemId());
        return stuffVo;
    }
    /**
     * 默认打印
     * @param stuffVo 员工视图对象
     * @param user 登陆用户对象
     * @exception Exception 总异常
     * @return String 返回打印页面源代码
     * */
    
    public String getPrintHtml(BasStuffVo stuffVo, BasUsers user)
            throws Exception
    {
        String result = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String html = printService.getHtmlTemplet(stuffVo.getPrintTempletId());
        BasUsers user1 = basPersonnelInformationSetDao
                .findBasStuffByYid(stuffVo);
        String stfName = user1.getBasStuff().getStfName();
        map.put("stfName", stfName != null ? !"".equals(stfName) ? stfName : ""
                : "");
        String stfSex = user1.getBasStuff().getStfSex();
        map.put("stfSex", stfSex != null ? !"".equals(stfSex) ? stfSex : ""
                : "");
        String basDept = user1.getBasStuff().getBasDept().getDeptName();
        map.put("deptName", basDept != null ? !"".equals(basDept) ? basDept
                : "" : "");
        String basStuff = user1.getBasStuff().getStfPhone();
        map.put("stfPhone", basStuff != null ? !"".equals(basStuff) ? basStuff
                : "" : "");
        String stfTel = user1.getBasStuff().getStfTel();
        map.put("stfTel", stfTel != null ? !"".equals(stfTel) ? stfTel : ""
                : "");
        String stfGj = user1.getBasStuff().getStfGj();
        map.put("stfGj", stfGj != null ? !"".equals(stfGj) ? stfGj : "" : "");
        Date stfBirthday = user1.getBasStuff().getStfBirthday();
        map.put("stfBirthday", stfBirthday != null ? stfBirthday : "");
        Date stfWorkDate = user1.getBasStuff().getStfWorkDate();
        map.put("stfWorkDate", stfWorkDate != null ? stfWorkDate : "");
        String stfByyx = user1.getBasStuff().getStfByyx();
        map.put("stfByyx", stfByyx != null ? !"".equals(stfByyx) ? stfByyx : ""
                : "");
        Date stfBysj = user1.getBasStuff().getStfBysj();
        map.put("stfBysj", stfBysj != null ? stfBysj : "");
        String stfSxzy = user1.getBasStuff().getStfSxzy();
        map.put("stfSxzy", stfSxzy != null ? !"".equals(stfSxzy) ? stfSxzy : ""
                : "");
        String stfWhcd = user1.getBasStuff().getStfWhcd();
        map.put("stfWhcd", stfWhcd != null ? !"".equals(stfWhcd) ? stfWhcd : ""
                : "");
        String zd = user.getBasStuff().getStfName();
        map.put("zd", zd != null ? !"".equals(zd) ? zd : "" : "");
        String[] htmls = html.split("</p>");
        for (String html_ : htmls)
        {
            html_ = html_.replace("<p>", "").trim();
            result += HtmlParse.parse(html_, map);
        }
        return result;
    }
    /**
     * 用户角色查询
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return Datagrid 返回数据表格对象
     * */
    public Datagrid findUserRole(BasStuffVo stuffVo)
            throws Exception
    {
    	Datagrid dg=new Datagrid();
    	List<BasStuffVo> rows=new ArrayList<BasStuffVo>();
        StringBuffer sb=new StringBuffer("SELECT bs.stf_id,bs.stf_y_id,bs.STF_NAME,bs.STF_MARK,list2.value2,list1.value1,bu.USER_NAME,CASE WHEN bu.LEVAL_ = 'administrator' THEN '超级管理员角色' ELSE roleData.roleName END roleName,bu.LEVAL_,bu.SYSTEMID");
        sb.append(" FROM bas_stuff bs INNER JOIN bas_users bu ON bu.STF_ID=bs.STF_ID INNER JOIN enterprise_info ent ON bs.enterprise_id = ent.enterprise_id");
        sb.append(" INNER JOIN (SELECT bc.datakey AS key1,bc.dataValue AS value1 FROM bas_childdictionary bc");  
        sb.append(" INNER JOIN bas_parentdictionary bp ON bp.parent_id=bc.parent_id AND bp.dataKey='stfYes') AS list1 ON list1.key1=bs.STF_YES"); 
        sb.append(" LEFT OUTER JOIN (SELECT bc.datakey  AS key2,bc.dataValue AS value2 FROM bas_childdictionary bc ");
        sb.append(" INNER JOIN bas_parentdictionary bp ON bp.parent_id=bc.parent_id AND bp.dataKey='sex') AS list2 ON list2.key2=bs.STF_SEX");
        sb.append(" LEFT OUTER JOIN (");
        sb.append(" SELECT bur.user_id,GROUP_CONCAT(br.role_name SEPARATOR ',') AS roleName");
        sb.append(" FROM bas_user_role bur INNER JOIN bas_role br ON br.role_id=bur.role_id GROUP BY bur.user_id");  
        sb.append(" ) AS roleData ON roleData.user_id=bs.stf_id");
        sb.append(" WHERE ent.enterprise_id ="+stuffVo.getEnterpriseId()+" OR ent.parentEnterprise_id ="+stuffVo.getEnterpriseId()); 
        if(stuffVo.getStfYid()!=null&&stuffVo.getStfYid().length()>0)
        	sb.append(" and bs.stf_y_id like '%"+StringEscapeUtils.escapeSql(stuffVo.getStfYid().trim())+"%'");
        if(stuffVo.getStfName()!=null&&stuffVo.getStfName().length()>0)
        	sb.append(" and bs.STF_NAME like '%"+StringEscapeUtils.escapeSql(stuffVo.getStfName().trim())+"%'");
        if(stuffVo.getStfYes()!=null&&stuffVo.getStfYes().length()>0)
        	sb.append(" and bs.STF_YES='"+stuffVo.getStfYes()+"'");
        if(stuffVo.getDeptId()!=null&&stuffVo.getDeptId().length()>0)
        	sb.append(" and bs.DEPT_ID="+stuffVo.getDeptId());
        if(stuffVo.getRepgrpId()!=null&&stuffVo.getRepgrpId().length()>0)
        	sb.append(" and bs.REPGRP_ID="+stuffVo.getRepgrpId());
        if(stuffVo.getStfZxqk()!=null&&stuffVo.getStfZxqk().length()>0){
        	if(stuffVo.getStfZxqk().equals(Contstants.ZXQKF.ALL)){
        		sb.append(" and bs.STF_ZXQK in('"+Contstants.ZXQKF.INSERVICE+"','"+Contstants.ZXQKF.LOGGEDOFF+"')");
        	}else{
        		sb.append(" and bs.STF_ZXQK='"+stuffVo.getStfZxqk()+"'");
        	}
        }
        int total = basPersonnelInformationSetDao.getSQLCount(sb.toString(), null);
        List<Object[]> list=basPersonnelInformationSetDao.createSQLQuery(sb.toString(),stuffVo.getPage(),stuffVo.getRows());
        BasStuffVo bsVo=null;
        if(list!=null&&list.size()>0)
            for (Object[] obj : list) {
                bsVo=new BasStuffVo();
                if(obj[0]!=null&&obj[0].toString().length()>0)
                    bsVo.setStfId(obj[0].toString());
                if(obj[1]!=null&&obj[1].toString().length()>0)
                    bsVo.setStfYid(obj[1].toString());
                if(obj[2]!=null&&obj[2].toString().length()>0)
                    bsVo.setStfName(obj[2].toString());
                if(obj[3]!=null&&obj[3].toString().length()>0)
                    bsVo.setStfMark(obj[3].toString());
                if(obj[4]!=null&&obj[4].toString().length()>0)
                    bsVo.setStfSex(obj[4].toString());
                if(obj[5]!=null&&obj[5].toString().length()>0)
                    bsVo.setStfYes(obj[5].toString());
                if(obj[6]!=null&&obj[6].toString().length()>0)
                    bsVo.setLoginName(obj[6].toString());
                if(obj[7]!=null&&obj[7].toString().length()>0)
                    bsVo.setRoleName(obj[7].toString());
                if(obj[8]!=null&&obj[8].toString().length()>0)
                    bsVo.setLeavl(obj[8].toString());
                if(obj[9]!=null&&obj[9].toString().length()>0)
                    bsVo.setSystemId(obj[9].toString());
                rows.add(bsVo);
            }
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }
    /**
     * 人事资料设定Datagrid
     * @param stuffVo 员工视图对象
     * @exception Exception 总异常
     * @return Datagrid 返回数据表格对象
     * */
	
	public Datagrid basPersonnelInformationDatagrid(BasStuffVo stuffVo)
			throws Exception {
	    List<BasStuffVo> rows=new ArrayList<BasStuffVo>();
	    Datagrid dg=new Datagrid();
		StringBuffer sb=new StringBuffer("SELECT bs.STF_ID,bs.STF_NAME,bs.STF_MARK,bs.STF_SEX,view1.temp2, bs.STF_YES,view2.temp4,bu.USER_NAME,bu.SYSTEMID,view3.temp6,");
		sb.append(" ei.enterprise_id,ei.enterprise_name,bd.DEPT_ID,bd.DEPT_NAME,bs.REPGRP_ID,brg.REPGRP_NAME,bs.STF_ZWGZ,bs.STF_PHONE,bs.STF_TEL,");
		sb.append(" bs.STF_ZXQK,view4.temp8,");
		sb.append(" (SELECT GROUP_CONCAT( bsj.JOB_PRO_ID SEPARATOR ',') AS temps FROM  bas_stuff_job bsj WHERE bsj.STF_ID=bs.STF_ID) AS temp11,");
		sb.append(" bs.STF_BIRTHDAY,bs.STF_WORK_DATE,bs.STF_GJ");
		sb.append(" FROM  bas_stuff bs INNER JOIN bas_users bu ON bu.STF_ID=bs.STF_ID");
		sb.append(" INNER JOIN (SELECT bc.dataKey AS temp3,bc.dataValue AS temp4 FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp ON bp.parent_id=bc.parent_id AND bp.dataKey='stfYes') view2");
		sb.append(" ON view2.temp3=bs.STF_YES");
		sb.append(" INNER JOIN (SELECT bc.dataKey AS temp5,bc.dataValue AS temp6 FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp ON bp.parent_id=bc.parent_id AND bp.dataKey='system_') view3");
		sb.append(" ON view3.temp5=bu.SYSTEMID");
		sb.append(" INNER JOIN bas_dept bd ON bd.DEPT_ID=bs.DEPT_ID");
		sb.append(" INNER JOIN enterprise_info ei ON ei.enterprise_id=bd.enterprise_id");
		sb.append(" INNER JOIN (SELECT bc.dataKey AS temp7,bc.dataValue AS temp8 FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp ON bp.parent_id=bc.parent_id AND bp.dataKey='ZxqkF') view4");
		sb.append(" ON view4.temp7=bs.STF_ZXQK");
		sb.append(" LEFT OUTER JOIN bas_repair_group brg ON brg.REPGRP_ID=bs.REPGRP_ID");
		sb.append(" LEFT OUTER JOIN (SELECT xc.dataKey AS temp9,xc.dataValue AS temp0 FROM xs_childdictionary xc INNER JOIN xs_parentdictionary xp ON xp.parent_id=xc.parent_id AND xp.dataKey='sellTeam') view5");
		sb.append(" ON view5.temp9=bs.REPGRP_ID2");
		sb.append(" LEFT OUTER JOIN (SELECT bc.dataKey AS temp1,bc.dataValue AS temp2 FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp ON bp.parent_id=bc.parent_id AND bp.dataKey='sex') view1");
		sb.append(" ON view1.temp1=bs.STF_SEX");
		int total = basPersonnelInformationSetDao.getCountBySQL(sb.toString());
		List<Object[]> list=basPersonnelInformationSetDao.createSQLQuery(sb.toString(), stuffVo.getPage(), stuffVo.getRows());
		BasStuffVo bsVo=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				bsVo=new BasStuffVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					bsVo.setStfId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					bsVo.setStfName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					bsVo.setStfMark(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					bsVo.setStfSex(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					bsVo.setStfSexvalue(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					bsVo.setStfYes(obj[5].toString());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					bsVo.setStfYesvalue(obj[6].toString());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					bsVo.setStfYesvalue(obj[7].toString());
				if(obj[8]!=null&&obj[8].toString().length()>0)
					bsVo.setLoginName(obj[8].toString());
				if(obj[9]!=null&&obj[9].toString().length()>0)
					bsVo.setSystemId(obj[9].toString());
				if(obj[10]!=null&&obj[10].toString().length()>0)
					bsVo.setSystemValue(obj[10].toString());
				if(obj[11]!=null&&obj[11].toString().length()>0)
					bsVo.setEnterpriseId(obj[11].toString());
				if(obj[12]!=null&&obj[12].toString().length()>0)
					bsVo.setEnterpriseName(obj[12].toString());
				if(obj[13]!=null&&obj[13].toString().length()>0)
					bsVo.setDeptId(obj[13].toString());
				if(obj[14]!=null&&obj[14].toString().length()>0)
					bsVo.setDeptName(obj[14].toString());
				if(obj[15]!=null&&obj[15].toString().length()>0)
					bsVo.setRepgrpId(obj[15].toString());
				if(obj[16]!=null&&obj[16].toString().length()>0)
					bsVo.setRepgrpName(obj[16].toString());
				if(obj[17]!=null&&obj[17].toString().length()>0)
					bsVo.setRepgrpId2(obj[17].toString());
				if(obj[18]!=null&&obj[18].toString().length()>0)
					bsVo.setRepgrpId2Name(obj[18].toString());
				if(obj[19]!=null&&obj[19].toString().length()>0)
					bsVo.setStfZwgz(obj[19].toString());
				if(obj[20]!=null&&obj[20].toString().length()>0)
					bsVo.setStfPhone(obj[20].toString());
				if(obj[21]!=null&&obj[21].toString().length()>0)
					bsVo.setStfTel(obj[21].toString());
				if(obj[22]!=null&&obj[22].toString().length()>0)
					bsVo.setStfZxqk(obj[22].toString());
				if(obj[23]!=null&&obj[23].toString().length()>0)
					bsVo.setStfZxqkvalue(obj[23].toString());
				if(obj[24]!=null&&obj[24].toString().length()>0)
					bsVo.setJobProId(obj[24].toString());
				if(obj[25]!=null&&obj[25].toString().length()>0)
					bsVo.setStfBirthday(obj[25].toString());
				if(obj[26]!=null&&obj[26].toString().length()>0)
					bsVo.setStfWorkDate(obj[26].toString());
				if(obj[27]!=null&&obj[27].toString().length()>0)
					bsVo.setStfGj(obj[27].toString());
				rows.add(bsVo);
			}
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
     * 设置集团管理员
     * @param stuffVo 员工视图对象
     * @exception 总异常
     * @return boolean true为设置成功，false为设置失败
     * */
	
	public boolean modifyCombinePerson(BasStuffVo stuffVo) throws Exception {
		if(!isExistsCombineAdmin()){
			if(stuffVo.getStfId()!=null&&stuffVo.getStfId().length()>0){
				BasUsers user=basUsersDao.get("from BasUsers bu where bu.basStuff.stfId="+stuffVo.getStfId());
				user.setLeavl(Contstants.EMPLOYEELEVEL.ADMIN);
				basUsersDao.merge(user);
				return true;
			}
		}
		return false;
	}
	/**
     * 系统用户中集团管理员是否已存在
     * @exception Exception 总异常
     * @return boolean true为已存在，false为不存在
     * */
	
	public boolean isExistsCombineAdmin() throws Exception {
		return basUsersDao.isExistsCombineAdmin();
	}
}