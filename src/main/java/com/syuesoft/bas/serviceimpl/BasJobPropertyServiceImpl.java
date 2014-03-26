package com.syuesoft.bas.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasJobPropertyDao;
import com.syuesoft.bas.service.BasJobPropertyService;
import com.syuesoft.fbk.vo.BasJobPropertyVO;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasJobProperty;
import com.syuesoft.util.MyBeanUtils;
/**
 * 工作属性Service实现
 * */
@Service("basJobPropertyService")
public class BasJobPropertyServiceImpl extends BaseLogServiceImpl implements
        BasJobPropertyService
{
    @Autowired
    private BasJobPropertyDao basJobPropertyDao;
    /**
	 * 工作属性新增
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return boolean true为新增完成，false为此工作属性已存在，新增失败
	 * */
    
    @Log(moduleName = "基础资料-->工作属性", opertype = "新增工作属性信息", content = "新增工作属性信息")
    public boolean add(BasJobPropertyVO bjpVO) throws Exception
    {
        Boolean tag = basJobPropertyDao.add(bjpVO);
        setContent("【"+bjpVO.getEnterpriseName()+"】新增工作属性信息 ,工作属性信息名称:" + bjpVO.getJobProName());
        return tag;
    }

    /**
	 * 工作属性删除
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return boolean true为删除完成，false为此工作属性中拥有用户，删除失败
	 * */
    
    @Log(moduleName = "基础资料-->工作属性", opertype = "删除工作属性信息", content = "删除工作属性信息")
    public boolean delete(BasJobPropertyVO bjpVO) throws Exception
    {
        BasJobProperty bjp=basJobPropertyDao.getBasJobProperty(bjpVO.getJobProId());
    	if(bjp.getBasStuffJobs()!=null&&bjp.getBasStuffJobs().size()>0)
    		return false;
    	basJobPropertyDao.executeSQL("delete from bas_job_property where JOB_PRO_ID="+bjpVO.getJobProId());
        setContent("【"+bjpVO.getEnterpriseName()+"】删除工作属性信息 ,工作属性信息名称:" + bjpVO.getJobProName());
        return true;
    }
    /**
	 * 工作属性查询
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return List<BasJobProperty> 返回包含工作属性视图对象集合
	 * */
    
    public List<BasJobProperty> findAll(BasJobPropertyVO bjpVO)
            throws Exception
    {
    	StringBuffer sb=new StringBuffer("from BasJobProperty bjp where 1=1");
		if(bjpVO.getJobProId()!=null&&bjpVO.getJobProId().toString().length()>0)
			sb.append(" and bjp.jobProId ="+bjpVO.getJobProId());
		if(bjpVO.getJobProName()!=null&&bjpVO.getJobProName().length()>0)
			sb.append(" and bjp.jobProName like '%"+StringEscapeUtils.escapeSql(bjpVO.getJobProName().trim())+"%'");
		if(bjpVO.getEnterpriseName()!=null&&bjpVO.getEnterpriseName().length()>0)
			sb.append(" and bjp.enterpriseInfo.enterpriseName like '%"+StringEscapeUtils.escapeSql(bjpVO.getEnterpriseName().trim())+"%'");
		if(bjpVO.getEnterpriseId()!=null&&bjpVO.getEnterpriseId().toString().length()>0)
			sb.append(" and bjp.enterpriseInfo.enterpriseId = "+bjpVO.getEnterpriseId());
		if(bjpVO.getSysType()!=null&&bjpVO.getSysType().length()>0)
			sb.append(" and bjp.sysType="+bjpVO.getSysType());
		List<BasJobProperty> list=basJobPropertyDao.find(sb.toString());
		return list;
    }

    /**
	 * 工作属性修改
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return boolean true为修改完成，false为此工作属性已存在，修改失败
	 * */
    
    @Log(moduleName = "基础资料-->工作属性", opertype = "修改工作属性信息", content = "修改工作属性信息")
    public boolean update(BasJobPropertyVO bjpVO) throws Exception
    {   
        BasJobProperty temp=basJobPropertyDao.getBasJobProperty(bjpVO.getJobProId());
        BasJobProperty bjp=new BasJobProperty();
    	MyBeanUtils.getInstance().copyBeans(bjpVO, bjp);
    	bjp.setEnterpriseId(temp.getEnterpriseId());
        Boolean tag= basJobPropertyDao.basJobPropertyUpdate(bjp);
        setContent("【"+bjpVO.getEnterpriseName()+"】修改工作属性信息 ,工作属性信息名称:" + bjpVO.getJobProName());
        return tag;
    }
    /**
	 * 工作属性查询
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return Datagrid 返回数据表格对象
	 * */
	
	public Datagrid basJobPropertyDatagrid(BasJobPropertyVO bjpVO)
			throws Exception {
		Datagrid dg=new Datagrid();
		List<BasJobPropertyVO> rows=new ArrayList();
		if(bjpVO.getFlag()==null)
			bjpVO.setFlag(true);
		StringBuffer sb=new StringBuffer("SELECT bjp.JOB_PRO_ID,bjp.JOB_PRO_NAME,bjp.JOB_PRO_NOTE,ei.enterprise_id,ei.enterprise_name");
		sb.append(" FROM bas_job_property bjp INNER JOIN enterprise_info ei ON ei.enterprise_id=bjp.enterprise_id");
		if(bjpVO.getFlag()==true&&bjpVO.getEnterpriseId()!=null)
			sb.append(" and ei.enterprise_id="+bjpVO.getEnterpriseId()+"");
		sb.append(" WHERE 1=1");
		if(bjpVO.getJobProId()!=null&&bjpVO.getJobProId().toString().length()>0)
			sb.append(" and bjp.JOB_PRO_ID ="+bjpVO.getJobProId());
		if(bjpVO.getJobProName()!=null&&bjpVO.getJobProName().length()>0)
			sb.append(" and bjp.JOB_PRO_NAME like '%"+StringEscapeUtils.escapeSql(bjpVO.getJobProName().trim())+"%'");
		if(bjpVO.getEnterpriseName()!=null&&bjpVO.getEnterpriseName().length()>0)
			sb.append(" and ei.enterprise_name like '%"+StringEscapeUtils.escapeSql(bjpVO.getEnterpriseName().trim())+"%'");
		if(bjpVO.getEnterpriseId()!=null&&bjpVO.getEnterpriseId().toString().length()>0)
			sb.append(" and ei.enterprise_id = "+bjpVO.getEnterpriseId());
		if(bjpVO.getSysType()!=null&&bjpVO.getSysType().length()>0)
			sb.append(" and bjp.sys_type='"+bjpVO.getSysType()+"'");
		List<Object[]> list=basJobPropertyDao.createSQLQuery(sb.toString(),bjpVO.getPage(),bjpVO.getRows());
		BasJobPropertyVO bjp=null;
		if(list!=null&&list.size()>0)
		for (Object[] obj : list) {
			bjp=new BasJobPropertyVO();
			if(obj[0]!=null&&obj[0].toString().length()>0)
				bjp.setJobProId(Short.parseShort(obj[0].toString()));
			if(obj[1]!=null&&obj[1].toString().length()>0)
				bjp.setJobProName(obj[1].toString());
			if(obj[2]!=null&&obj[2].toString().length()>0)
				bjp.setJobProNote(obj[2].toString());
			if(obj[3]!=null&&obj[3].toString().length()>0)
				bjp.setEnterpriseId(Integer.parseInt(obj[3].toString()));
			if(obj[4]!=null&&obj[4].toString().length()>0)
				bjp.setEnterpriseName(obj[4].toString());
			rows.add(bjp);
		}
		dg.setRows(rows);
		dg.setTotal(basJobPropertyDao.getSQLCount(sb.toString(),null));
		return dg;
	}
}
