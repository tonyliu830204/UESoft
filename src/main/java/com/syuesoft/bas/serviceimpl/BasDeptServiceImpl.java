package com.syuesoft.bas.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasDeptDao;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.service.BasDeptService;
import com.syuesoft.base.vo.BasDeptVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasDept;
import com.syuesoft.model.BasJobProperty;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.util.MyBeanUtils;
/**
 * 部门设置Service实现
 * @author LWJ
 * */
@Service("basDeptService")
public class BasDeptServiceImpl extends BaseLogServiceImpl implements
        BasDeptService
{
	@Autowired
    private BasDeptDao basDeptDao;
	@Autowired
	private BasStuffDao basStuffDao;
	/**
	 * 部门设置新增方法
	 * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return boolean true为新增完成，false为此部门已存在，新增失败
	 * */
    
    @Log(moduleName = "基础资料-->部门设置", opertype = "新增部门信息", content = "新增部门信息")
    public void add(BasDeptVo basDeptVo) throws Exception
    {
    	BasDept bd=new BasDept();
    	/**
    	 * private String deptName;							//部门名称
	private Short deptNum;								
	private String deptDesc;							//备注
	private String memo;
	private String deptRemarks;
    	 */
    	if(basDeptVo.getDeptName()!=null&&!basDeptVo.getDeptName().trim().equals(""))
    		bd.setDeptName(basDeptVo.getDeptName().trim());
    	if(basDeptVo.getDeptNum()!=null)
    		bd.setDeptNum(Short.parseShort(basDeptVo.getDeptNum().trim()));
    	if(basDeptVo.getDeptDesc()!=null&&!basDeptVo.getDeptDesc().trim().equals(""))
    		bd.setDeptDesc(basDeptVo.getDeptDesc().trim());
    	if(basDeptVo.getMemo()!=null&&!basDeptVo.getMemo().trim().equals(""))
    		bd.setMemo(basDeptVo.getMemo().trim());
    	bd.setEnterpriseId(basDeptVo.getEnterpriseId());
    	basDeptDao.save(bd);
        setContent("【"+basDeptVo.getEnterpriseName()+"】新增部门信息 ,部门信息名称:" + basDeptVo.getDeptName());
    }
    /**
     * 部门设置删除方法
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return boolean true为删除完成，false为此部门拥有用户，删除失败
     * */
    
    @Log(moduleName = "基础资料-->部门设置", opertype = "删除部门信息", content = "删除部门信息")
    public void delete(BasDeptVo basDeptVo) throws Exception
    {
    	BasDept basDept=basDeptDao.get(BasDept.class,Short.parseShort(basDeptVo.getDeptId()));
    	if(basDept!=null){
    		basDeptDao.delete(basDept);
    		setContent("【"+basDeptVo.getEnterpriseName()+"】删除部门信息 ,部门信息名称:" + basDeptVo.getDeptName());
    	}
    }
    /**
     * 部门设置修改方法
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return boolean true为修改完成，false为此部门已存在，修改失败
     * */
    
    @Log(moduleName = "基础资料-->部门设置", opertype = "修改部门信息", content = "修改部门信息")
    public boolean update(BasDeptVo basDeptVo) throws Exception
    {
    	BasDept temp=basDeptDao.getBasDept(Short.parseShort(basDeptVo.getDeptId()));
    	BasDept basDept=new BasDept();
    	MyBeanUtils.getInstance().copyBeans(basDeptVo, basDept);
    	basDept.setEnterpriseId(temp.getEnterpriseId());
        Boolean tag= basDeptDao.basDeptUpdate(basDept);
        setContent("【"+basDeptVo.getEnterpriseName()+"】修改部门信息 ,部门信息名称:" + basDeptVo.getDeptName());
        return tag;
    }
    /**
     * 部门设置查询方法
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return List<BasDept> 返回包含部门实体的集合
     * */
	
	public Datagrid basDeptDatagrid(BasDeptVo basDeptVo) throws Exception {
		Datagrid dg=new Datagrid();
		List<BasDeptVo> rows=new ArrayList();
		if(basDeptVo.getFlag()==null)
			basDeptVo.setFlag(true);
		StringBuffer sb=new StringBuffer("SELECT bd.DEPT_ID,bd.DEPT_NAME,bd.DEPT_DESC,ei.enterprise_id,ei.enterprise_name");
		sb.append(" FROM bas_dept bd INNER JOIN enterprise_info ei ON ei.enterprise_id=bd.enterprise_id ");
		if(basDeptVo.getFlag()==true&&basDeptVo.getNowEnterpriseId()!=null)
			sb.append(" and ei.enterprise_id="+basDeptVo.getNowEnterpriseId()+"");
		sb.append(" WHERE 1=1");
		if(basDeptVo.getDeptId()!=null&&basDeptVo.getDeptId().length()>0)
			sb.append(" and bd.DEPT_ID ="+basDeptVo.getDeptId());
		if(basDeptVo.getDeptName()!=null&&basDeptVo.getDeptName().length()>0)
			sb.append(" and bd.DEPT_NAME like '%"+StringEscapeUtils.escapeSql(basDeptVo.getDeptName().trim())+"%'");
		if(basDeptVo.getEnterpriseName()!=null&&basDeptVo.getEnterpriseName().length()>0)
			sb.append(" and ei.enterprise_name like '%"+StringEscapeUtils.escapeSql(basDeptVo.getEnterpriseName().trim())+"%'");
		List<Object[]> list=basDeptDao.createSQLQuery(sb.toString(),basDeptVo.getPage(),basDeptVo.getRows());
		BasDeptVo basDept=null;
		if(list!=null&&list.size()>0)
		for (Object[] obj : list) {
			basDept=new BasDeptVo();
			if(obj[0]!=null&&obj[0].toString().length()>0)
				basDept.setDeptId(obj[0].toString());
			if(obj[1]!=null&&obj[1].toString().length()>0)
				basDept.setDeptName(obj[1].toString());
			if(obj[2]!=null&&obj[2].toString().length()>0)
				basDept.setDeptDesc(obj[2].toString());
			if(obj[3]!=null&&obj[3].toString().length()>0)
				basDept.setEnterpriseId(Integer.parseInt(obj[3].toString()));
			if(obj[4]!=null&&obj[4].toString().length()>0)
				basDept.setEnterpriseName(obj[4].toString());
			rows.add(basDept);
		}
		dg.setRows(rows);
		dg.setTotal(basDeptDao.getSQLCount(sb.toString(),null));
		return dg;
	}
	/**
     * 部门设置查询方法(公司信息与员工信息加载为延时加载)
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return List<BasDept> 返回包含部门实体的集合
     * */
	
	public List<BasDept> findAll(BasDeptVo basDeptVo) throws Exception {
		StringBuffer sb=new StringBuffer("from BasDept bd where 1=1");
		if(basDeptVo.getDeptId()!=null&&basDeptVo.getDeptId().length()>0)
			sb.append(" and bd.deptId ="+basDeptVo.getDeptId());
		if(basDeptVo.getDeptName()!=null&&basDeptVo.getDeptName().length()>0)
			sb.append(" and bd.deptName like '%"+StringEscapeUtils.escapeSql(basDeptVo.getDeptName().trim())+"%'");
		if(basDeptVo.getEnterpriseName()!=null&&basDeptVo.getEnterpriseName().length()>0)
			sb.append(" and bd.enterpriseInfo.enterpriseName like '%"+StringEscapeUtils.escapeSql(basDeptVo.getEnterpriseName().trim())+"%'");
		if(basDeptVo.getEnterpriseId()!=null&&basDeptVo.getEnterpriseId().toString().length()>0)
			sb.append(" and bd.enterpriseInfo.enterpriseId = "+basDeptVo.getEnterpriseId());
		List<BasDept> list=basDeptDao.find(sb.toString());
		return list;
	}
	 /**
     * 获取当前用户所属公司Id和Name
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return BasDeptVo 返回部门视图对象
     * */
	
	public BasDeptVo findBasStuffOfEnterpriseInfo(BasDeptVo basDeptVo)
			throws Exception {
		EnterpriseInfo info=basStuffDao.findEnterpriseInfoByStfId(basDeptVo.getStfId());
		BasDeptVo basDept=new BasDeptVo();
		basDept.setEnterpriseId(info.getEnterpriseId());
		basDept.setEnterpriseName(info.getEnterpriseName());
		return basDept;
	}

}
