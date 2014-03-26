package com.syuesoft.integratedservices.daoimpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.fbk.vo.CustomerCareVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.integratedservices.dao.CustomerCareDao;
import com.syuesoft.model.FbkCarGroup;
import com.syuesoft.model.FbkCarGroupId;
import com.syuesoft.model.FbkTxGroup;
import com.syuesoft.model.FrtRcptItem;
import com.syuesoft.util.Json;

@Repository("customerCareDao")
public class CustomerCareDaoImpl extends BaseDaoImpl<Object> implements
        CustomerCareDao
{
    /*
     * (non-Javadoc) 客户关怀中心的查询所有 此查询是根据前台接车的入场日期 ,查询最近入厂的车辆, 并根据车辆分类的相关信息
     * 
     * @see com.syuesoft.integratedservices.dao.CustomerCareDao#getAll(int, int)
     */
    @SuppressWarnings("unchecked")
    
    public List<CustomerCareVo> getAll(final int page, final int rows,
            final String sql) throws Exception
    {
        List<CustomerCareVo> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        return null;
                    }

                });
        return list;
    }

    /*
     * (non-Javadoc) 客户关怀中心的条件查询
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CustomerCareDao#getByCondition(com
     * .syuesoft.fbk.vo.CustomerCareVo)
     */
    @SuppressWarnings("unchecked")
    
    public List<CustomerCareVo> getByCondition(
            final CustomerCareVo customerCareVo) throws Exception
    {
        return null;
    }

    /*
     * (non-Javadoc) 提醒查询的结果统计 统计流失的数量,总数,流失类别及占总数的百分比例
     * 
     * @see com.syuesoft.integratedservices.dao.CustomerCareDao#doResualt()
     */
    @SuppressWarnings("unchecked")
    
    public Datagrid doResualt(CustomerCareVo customerCareVo) throws Exception
    {
    	Datagrid d=new Datagrid();
       StringBuffer  sqlCount=new StringBuffer("select  *  from remainder_query_view a where 1=1 AND a.enterpriseId="+customerCareVo.getEnterpriseId());
       addWhere(sqlCount,customerCareVo);
       int totalCount=this.getSQLCount(sqlCount.toString(), null);
       String sql="select a.txResult,a.txResultName,count(*) as cnt from ("+sqlCount.toString()+")a  where 1=1" +
       		"  group  by a.txResult ";
       List<Object[]> objs=this.createSQLQuery(sql);
       List<CustomerCareVo> lst=new ArrayList<CustomerCareVo>();
       if(objs!=null && objs.size()>0){
    	   for(Object[] o:objs){
    		   CustomerCareVo vo=new CustomerCareVo();
    		   if(o[0]!=null && !("".equals(o[0]))){
    			  vo.setTx_Resault(o[0].toString()); 
    		   }
			   if(o[1]!=null && !("".equals(o[1]))){
				   vo.setTx_Resault_value(o[1].toString());
				}
			   if(o[2]!=null && !("".equals(o[2]))){
					vo.setCar_sum(o[2].toString());
				}
			   Double i=Double.parseDouble(o[2].toString());
			   vo.setCar_percent(i/totalCount*100+"%");
			   lst.add(vo);
    	   }
       }
       d.setRows(lst);
       d.setTotal(this.getSQLCount(sql, null));
        return d;
    
    }

    /*
     * (non-Javadoc) 此方法为通过车辆的id查询历史回访的记录
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CustomerCareDao#getFactoryVisit(java
     * .lang.String, int, int)
     */
    @SuppressWarnings("unchecked")
    
    public List<FbkTxGroup> getFactoryVisit(final CustomerCareVo customerCareVo)
            throws Exception
    {
        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {
            
            public List doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                String hql = "SELECT * FROM fbk_tx_group ftg WHERE ftg.G_ID IN (SELECT fvv.G_ID FROM fbk_car_group fvv WHERE fvv.carId='"
                        + customerCareVo.getCar_Id() + "')";
                Query query = session.createSQLQuery(hql);
                return query.list();
            }
        });
        return list;
    }

    /*
     * (non-Javadoc) 保存提醒内容
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CustomerCareDao#doSave(com.syuesoft
     * .model.FbkTxGroup, short)
     */
    @SuppressWarnings("unchecked")
    
    public void doSave(FbkTxGroup fbkTxGroup, String carId) throws Exception
    {
        Session session = this.getHibernateTemplate().getSessionFactory()
                .openSession();
        Transaction tr = session.beginTransaction();
        session.save(fbkTxGroup);
        tr.commit();
        session.flush();
        session.close();

        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {// 调用hibernate 的回调函数 执行语句获取最大的ffvid
                    
                    public List doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String sql = "SELECT MAX(ftg.G_Id) FROM fbk_tx_group ftg";
                        Query query = session.createSQLQuery(sql);
                        return query.list();
                    }
                });

        FbkCarGroup fcg = new FbkCarGroup();
        FbkCarGroupId fid = new FbkCarGroupId();
        fid.setCarId(carId);
        fid.setGId(Integer.parseInt(list.get(0).toString()));
        fcg.setId(fid);
        this.getHibernateTemplate().save(fcg);
    }

    /*
     * (non-Javadoc) 此方法为通过车辆的id 查询该车的历史维修的记录
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CustomerCareDao#getFactoryWxRecord
     * (int)
     */
    @SuppressWarnings("unchecked")
    
    public List getFactoryWxRecord(final CustomerCareVo customerCareVo)
            throws Exception
    {
        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {

            
            public List doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                String sql = "SELECT * from car_lost_detial A where 1 = 1 AND A.car_Id ='"
                        + customerCareVo.getCar_Id() + "'";
                return session.createSQLQuery(sql).list();
            }

        });
        return list;
    }

    /*
     * (non-Javadoc) 此方法为:删除历史回访记录的方法
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CustomerCareDao#doDelete(com.syuesoft
     * .model.FbkFactoryVisit)
     */
    
    public void doDelete(FbkCarGroup fbkCarGroup) throws Exception
    {
        this.getHibernateTemplate().delete(fbkCarGroup);
    }

    /*
     * (non-Javadoc) 提醒查询的方法 此查询的方法查询的是已经提醒的记录
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CustomerCareDao#getRemenberSearch()
     */
    @SuppressWarnings("unchecked")
    
    public Datagrid getRemenberSearch(
            final CustomerCareVo customerCareVo) throws Exception
    {
    	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        Datagrid d=new Datagrid();
        StringBuffer sql=new StringBuffer("select *  from  remainder_query_view  a where 1=1  AND a.enterpriseId="+customerCareVo.getEnterpriseId());
        addWhere(sql,customerCareVo);
        List<Object[]> objs=this.createSQLQuery(sql.toString());
        List<CustomerCareVo> lst=new ArrayList<CustomerCareVo>();
        if(objs!=null && objs.size()>0){
        	for(Object [] obj:objs){
        		CustomerCareVo vo=new CustomerCareVo();
        		if(obj[0]!=null  && !("".equals(obj[0]))){
        			vo.setCustom_Name(obj[0].toString());
        		}
				if(obj[1]!=null  && !("".equals(obj[1]))){
					vo.setCar_License(obj[1].toString());
				 }
				if(obj[2]!=null  && !("".equals(obj[2]))){
					vo.setCar_Cstl_id(obj[2].toString());
				}
				if(obj[3]!=null  && !("".equals(obj[3]))){
					vo.setCar_Cstl_Name(obj[3].toString());
				}
				if(obj[4]!=null  && !("".equals(obj[4]))){
					vo.setCtype_Name(obj[4].toString());
				}
				if(obj[5]!=null  && !("".equals(obj[5]))){
					vo.setCtype_Name_id(obj[5].toString());
				}
				if(obj[6]!=null  && !("".equals(obj[6]))){
					vo.setCustom_tel1(obj[6].toString());
				}
				if(obj[7]!=null  && !("".equals(obj[7]))){
					vo.setCar_Buy_Date(f.format((Date)obj[7]));
				}
				if(obj[8]!=null  && !("".equals(obj[8]))){
					vo.setCar_Vin(obj[8].toString());
				}
				if(obj[9]!=null  && !("".equals(obj[9]))){
					vo.setReturn_Visit_Date(f.format((Date)obj[9]));
				}
				if(obj[10]!=null  && !("".equals(obj[10]))){
					vo.setTx_Return_Visit_Date(f.format((Date)obj[10]));
				}
				if(obj[11]!=null  && !("".equals(obj[11]))){
					vo.setVisit_Content(obj[11].toString());
				}
				if(obj[12]!=null  && !("".equals(obj[12]))){
					vo.setGroup_Name(obj[12].toString());
				}
				if(obj[13]!=null  && !("".equals(obj[13]))){
					vo.setGroup_Name_value(obj[13].toString());
				}
				if(obj[14]!=null  && !("".equals(obj[14]))){
					vo.setCar_Last_Repair_Date(f.format((Date)obj[14]));
				}
				if(obj[15]!=null  && !("".equals(obj[15]))){
					vo.setCar_Repair_Cnt(obj[15].toString());
				}
				if(obj[16]!=null  && !("".equals(obj[16]))){
					vo.setCar_Last_Repair_Distance(obj[16].toString());
				}
				if(obj[17]!=null  && !("".equals(obj[17]))){
					vo.setWlcDays(obj[17].toString());
				}
				if(obj[18]!=null  && !("".equals(obj[18]))){
					vo.setCar_lost(obj[18].toString());
				}
				if(obj[19]!=null  && !("".equals(obj[19]))){
					vo.setCar_lost_value(obj[19].toString());
				}
				if(obj[20]!=null  && !("".equals(obj[20]))){
					vo.setTx_Resault(obj[20].toString());
				}
				if(obj[21]!=null  && !("".equals(obj[21]))){
					vo.setTx_Resault_value(obj[21].toString());
				}
				lst.add(vo);
        	}
        }
        d.setRows(lst);
        int total=this.getSQLCount(sql.toString(), null);
        d.setTotal(total);
        return d;
    }
    public StringBuffer addWhere(StringBuffer sb,CustomerCareVo customerCareVo){
    	if(customerCareVo.getReturn_Visit_Date()!=null && !("".equals(customerCareVo.getReturn_Visit_Date()))){
    		String[] str = customerCareVo.getReturn_Visit_Date().split(",");
			if (str.length > 1) {
				sb.append(" and a.returnVisitDate BETWEEN '" + str[0]
						+ "' AND '" + str[1] + "'");
			} else {

				if (customerCareVo.getReturn_Visit_Date().length() > 10) {
					sb.append(" and a.returnVisitDate >= '" + str[0]
							+ "'");
				} else {
					sb.append(" and a.returnVisitDate <='" + str[0]
							+ "'");
				}

			}
    	}
    	if(customerCareVo.getGroup_Name()!=null && !("".equals(customerCareVo.getGroup_Name()))){
    		sb.append(" and a.gropuName = '" + customerCareVo.getGroup_Name()+ "'");
    	}
    	if(customerCareVo.getCar_License()!=null && !("".equals(customerCareVo.getCar_License()))){
    		sb.append(" and a.carLicense like '%" + customerCareVo.getCar_License()+ "%'");
    	}
    	if(customerCareVo.getTx_Resault()!=null && !("".equals(customerCareVo.getTx_Resault()))){
    		sb.append(" and a.txResult = '" + customerCareVo.getTx_Resault()+ "'");
    	}
    	if(customerCareVo.getCar_lost()!=null && !("".equals(customerCareVo.getCar_lost()))){
    		sb.append(" and a.carLost = '" + customerCareVo.getCar_lost()+ "'");
    	}
    	return sb;
    }
    
    // 获取方法 用于下拉列表
    @SuppressWarnings("unchecked")
    
    public List getObject(final String sql) throws Exception
    {
        List list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        Query query = session.createQuery(sql);
                        return query.list();
                    }
                });
        return list;
    }

    /*
     * (non-Javadoc) 获取存储过程中的保养提醒信息
     * 
     * @see com.syuesoft.integratedservices.dao.CustomerCareDao#getBytixing(int,
     * int, java.lang.String)
     */
    
    public Json getBytixing(final CustomerCareVo cVo)
            throws Exception
    {
    	SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
    	 List<CustomerCareVo> list =new ArrayList<CustomerCareVo>();
    	 StringBuffer sb=new StringBuffer("SELECT * FROM car_maintenance_reminders a WHERE 1 = 1 AND a.enterpriseId="+cVo.getEnterpriseId());
    	 addCondition(sb,cVo);
    	 sb.append("	GROUP BY a.carLicense");
         int count=getSQLCount(sb.toString(), null);
         List<Object[]> resultList=createSQLQuery(sb.toString());
         if(resultList!=null&&!resultList.equals("")){
        	 Object[] obj = null;
             for (int i = 0; i < resultList.size(); i++)
             {
                 obj = (Object[]) resultList.get(i);
                 CustomerCareVo customerCareVo = new CustomerCareVo();
                 if (obj[0] != null && !obj[0].equals(""))
                 {
                     customerCareVo.setCar_License(obj[0].toString());
                 }
                 if (obj[1] != null && !obj[1].equals(""))
                 {
                     customerCareVo.setCar_Cstl_id(obj[1].toString());
                 }
                 if (obj[2] != null && !obj[2].equals(""))
                 {
                     customerCareVo.setCar_Cstl_Name(obj[2].toString());
                 }
                 if (obj[3] != null && !obj[3].equals(""))
                 {
                     customerCareVo.setCtype_Name_id(obj[3].toString());
                 }
                 if (obj[4] != null && !obj[4].equals(""))
                 {
                     customerCareVo.setCtype_Name(obj[4].toString());
                 }
                 if (obj[5] != null && !obj[5].equals(""))
                 {
                     customerCareVo.setCar_Vin(obj[5].toString());
                 }
                 if (obj[6] != null && !obj[6].equals(""))
                 {
                     customerCareVo.setYjbydate(s.format((Date)obj[6]));
                 }
                 if (obj[7] != null && !obj[7].equals(""))
                 {
                     customerCareVo.setYjby_Distance(obj[7].toString());
                 }

                 if (obj[8] != null && !obj[8].equals(""))
                 {
                     customerCareVo.setCustom_Name(obj[8].toString());
                 }
                 if (obj[9] != null && !obj[9].equals(""))
                 {
                     customerCareVo.setCustom_tel1(obj[9].toString());
                 }
                 if (obj[10] != null && !obj[10].equals(""))
                 {
                     customerCareVo.setCustom_tel2(obj[10]
                             .toString());
                 }
                 if (obj[11] != null && !obj[11].equals(""))
                 {
                     customerCareVo.setCar_Relation_Person(obj[11].toString());
                 }
                 if (obj[12] != null && !obj[12].equals(""))
                 {
                     customerCareVo.setProp_Tel(obj[12].toString());
                 }

                 if (obj[13] != null && !obj[13].equals(""))
                 {
                     customerCareVo.setProp_Phone(obj[13].toString());
                 }
                 if (obj[14] != null && !obj[14].equals(""))
                 {
                     customerCareVo.setCustom_Name_Addr(obj[14].toString());
                 }
                 if (obj[15] != null && !obj[15].equals(""))
                 {
                
                     customerCareVo.setCar_Last_Maint_Date(s.format((Date)obj[15]));
                 }
                 if (obj[16] != null && !obj[16].equals(""))
                 {
                     customerCareVo.setCar_Last_Maint_Distance(obj[16].toString());
                 }
                 if (obj[17] != null && !obj[17].equals(""))
                 {
                     customerCareVo.setBy_Number(obj[17].toString());
                 }

                 if (obj[18] != null && !obj[18].equals(""))
                 {
                     customerCareVo.setCar_Last_Repair_Date(s.format((Date)obj[18]));
                 }
                 if (obj[19] != null && !obj[19].equals(""))
                 {
                     customerCareVo.setCar_Last_Repair_Distance(obj[19].toString());
                 }
                 if (obj[20] != null && !obj[20].equals(""))
                 {
                     customerCareVo.setCar_Repair_Cnt(obj[20].toString());
                 }
                 if (obj[21] != null && !obj[21].equals(""))
                 {
                     customerCareVo.setCar_Distance_Per_Day(obj[21].toString());
                 }
                 if (obj[22] != null && !obj[22].equals(""))
                 {
                     customerCareVo.setWlcDays(obj[22].toString());
                 }

                 if (obj[23] != null && !obj[23].equals(""))
                 {
                     customerCareVo.setReceptor(obj[23].toString());
                 }
                 if (obj[24] != null && !obj[24].equals(""))
                 {
                     customerCareVo.setCar_Id(obj[24].toString());
                 }
                 if (obj[25] != null && !obj[25].equals(""))
                 {
                     customerCareVo.setCar_Examined_Date(s.format((Date)obj[25]));
                 }
                 if (obj[26] != null && !obj[26].equals(""))
                 {
                     customerCareVo.setCar_Annual_Date(s.format((Date)obj[26]));
                 }
                 if (obj[27] != null && !obj[27].equals(""))
                 {
                     customerCareVo.setCar_Buy_Date(s.format((Date)obj[27]));
                 }
                 if (obj[28] != null && !obj[28].equals(""))
                 {
                     customerCareVo.setCar_Fst_Insurance_Date(s.format((Date)obj[28]));
                 }
                 if (obj[29] != null && !obj[29].equals(""))
                 {
                     customerCareVo.setCar_Basinsurance_Date(s.format((Date)obj[29]));
                 }
                 if (obj[30] != null && !obj[30].equals(""))
                 {
                     customerCareVo.setCar_Businsurance_Date(s.format((Date)obj[30]));
                 }
                 if (obj[31] != null && !obj[31].equals(""))
                 {
                     customerCareVo.setRelcampId(obj[31].toString());
                 }
                 if (obj[32] != null && !obj[32].equals(""))
                 {
                     customerCareVo.setBusRelcamp(obj[32].toString());
                 }
                 if (obj[33] != null && !obj[33].equals(""))
                 {
                     customerCareVo.setCustom_Birthday(s.format((Date)obj[33]));
                 }
                 list.add(customerCareVo);
             }
         }
         Json json=new Json();
         json.setRows(list);
         json.setTotal(count);
        return json;
    }
    public StringBuffer  addCondition(StringBuffer sb,CustomerCareVo customerCareVo){
	    	if(customerCareVo.getYjbydate()!=null && !("".equals(customerCareVo.getYjbydate()))){
	    		String[] str = customerCareVo.getYjbydate().split(",");
	    		if(str[0]!=null && !("".equals(str[0]))){
					sb.append(" and a.carNextMaintDate >= '" + str[0]+ "'");
				}
				if(str[1]!=null && !("".equals(str[1]))){
					sb.append(" and a.carNextMaintDate <= '" + str[1]+ "'");
				}
	    	}
			if(customerCareVo.getBy_Number()!=null && !("".equals(customerCareVo.getBy_Number()))){
				sb.append("	 and a.carMaintCnt ="+customerCareVo.getBy_Number());
			}
			if(customerCareVo.getCar_License()!=null && !("".equals(customerCareVo.getCar_License()))){
				sb.append("	and a.carLicense like '%"+customerCareVo.getCar_License()+"%'");
			}
			if(customerCareVo.getCar_Vin()!=null && !("".equals(customerCareVo.getCar_Vin()))){
				sb.append("	and a.carVin like '%"+customerCareVo.getCar_Vin()+"%'");
			}
			if(customerCareVo.getCar_Buy_Date()!=null && !("".equals(customerCareVo.getCar_Buy_Date()))){
	    		String[] strMaintenance = customerCareVo.getCar_Buy_Date().split(",");
	    		if(strMaintenance[0]!=null && !("".equals(strMaintenance[0]))){
					sb.append(" and a.carBuyDate >= '" + strMaintenance[0]+ "'");
				}
				if(strMaintenance[1]!=null && !("".equals(strMaintenance[1]))){
					sb.append(" and a.carBuyDate <= '" + strMaintenance[1]+ "'");
				}
			}
			if(customerCareVo.getCar_Examined_Date()!=null && !("".equals(customerCareVo.getCar_Examined_Date()))){
	    		String[] strAnnual = customerCareVo.getCar_Examined_Date().split(",");
	    		if(strAnnual[0]!=null && !("".equals(strAnnual[0]))){
					sb.append(" and a.carExaminedDate >= '" + strAnnual[0]+ "'");
				}
				if(strAnnual[1]!=null && !("".equals(strAnnual[1]))){
					sb.append(" and a.carExaminedDate <= '" + strAnnual[1]+ "'");
				}
			}
			if(customerCareVo.getCar_Annual_Date()!=null && !("".equals(customerCareVo.getCar_Annual_Date()))){
	    		String[] annualDate = customerCareVo.getCar_Annual_Date().split(",");
	    		if(annualDate[0]!=null && !("".equals(annualDate[0]))){
					sb.append(" and a.carAnnualDate >= '" + annualDate[0]+ "'");
				}
				if(annualDate[1]!=null && !("".equals(annualDate[1]))){
					sb.append(" and a.carAnnualDate <= '" + annualDate[1]+ "'");
				}
			}
			if(customerCareVo.getCar_Last_Maint_Date()!=null && !("".equals(customerCareVo.getCar_Last_Maint_Date()))){
	    		String[] lastMaint = customerCareVo.getCar_Last_Maint_Date().split(",");
	    		if(lastMaint[0]!=null && !("".equals(lastMaint[0]))){
					sb.append(" and a.carLastMaintDate >= '" + lastMaint[0]+ "'");
				}
				if(lastMaint[1]!=null && !("".equals(lastMaint[1]))){
					sb.append(" and a.carLastMaintDate <= '" + lastMaint[1]+ "'");
				}
			}
			if(customerCareVo.getCar_Basinsurance_Date()!=null && !("".equals(customerCareVo.getCar_Basinsurance_Date()))){
	    		String[] basinsurance = customerCareVo.getCar_Basinsurance_Date().split(",");
	    		if(basinsurance[0]!=null && !("".equals(basinsurance[0]))){
					sb.append(" and a.carBasinsuranceDate >= '" + basinsurance[0]+ "'");
				}
				if(basinsurance[1]!=null && !("".equals(basinsurance[1]))){
					sb.append(" and a.carBasinsuranceDate <= '" + basinsurance[1]+ "'");
				}
			}
			if(customerCareVo.getCar_Fst_Insurance_Date()!=null && !("".equals(customerCareVo.getCar_Fst_Insurance_Date()))){
	    		String[] first = customerCareVo.getCar_Fst_Insurance_Date().split(",");
	    		if(first[0]!=null && !("".equals(first[0]))){
					sb.append(" and a.carFstInsuranceDate >= '" + first[0]+ "'");
				}
				if(first[1]!=null && !("".equals(first[1]))){
					sb.append(" and a.carFstInsuranceDate <= '" + first[1]+ "'");
				}
			}
			if(customerCareVo.getCar_Businsurance_Date()!=null && !("".equals(customerCareVo.getCar_Businsurance_Date()))){
	    		String[] businsurance = customerCareVo.getCar_Businsurance_Date().split(",");
	    		if(businsurance[0]!=null && !("".equals(businsurance[0]))){
					sb.append(" and a.carBusinsuranceDate >= '" + businsurance[0]+ "'");
				}
				if(businsurance[1]!=null && !("".equals(businsurance[1]))){
					sb.append(" and a.carBusinsuranceDate <= '" + businsurance[1]+ "'");
				}
			}
			if(customerCareVo.getCustom_Birthday()!=null && !("".equals(customerCareVo.getCustom_Birthday()))){
	    		String[] customBirth = customerCareVo.getCustom_Birthday().split(",");
	    		if(customBirth[0]!=null && !("".equals(customBirth[0]))){
					sb.append(" and a.customBirthday >= '" + customBirth[0]+ "'");
				}
				if(customBirth[1]!=null && !("".equals(customBirth[1]))){
					sb.append(" and a.customBirthday <= '" + customBirth[1]+ "'");
				}
			}
			return sb;	
    }
    /*
     * (non-Javadoc) 获取年检年审的的信息
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CustomerCareDao#getNianjianShen(int,
     * int)
     */
    @SuppressWarnings("unchecked")
    
    public List<CustomerCareVo> getNianjianShen(
            final CustomerCareVo customerCareVo) throws Exception
    {
        List<CustomerCareVo> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String sql = "";
                        if (customerCareVo.getCar_Annual_Date() != null
                                && !customerCareVo.getCar_Annual_Date().trim()
                                        .equals(""))
                        {
                            String[] str = customerCareVo.getCar_Annual_Date()
                                    .split(",");
                            sql += " AND a.Car_Annual_Date between '" + str[0]
                                    + "' and '" + str[1] + "'";
                        }
                        if (customerCareVo.getCar_License() != null
                                && !customerCareVo.getCar_License().trim()
                                        .equals(""))
                        {
                            sql += " AND a.Car_License LIKE '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getCar_License().trim()) + "%' ";
                        }
                        if (customerCareVo.getGroup_Name() != null
                                && !customerCareVo.getGroup_Name().trim()
                                        .equals(""))
                        {
                            sql += " AND a.Group_Name LIKE '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getGroup_Name().trim()) + "%' ";
                        }
                        if (customerCareVo.getTx_Resault() != null
                                && !customerCareVo.getTx_Resault().trim()
                                        .equals(""))
                        {
                            sql += " AND a.Tx_Resault LIKE '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getTx_Resault().trim()) + "%' ";
                        }
                        if (customerCareVo.getStatus_Name() != null
                                && !customerCareVo.getStatus_Name().trim()
                                        .equals(""))
                        {
                            sql += " AND a.Status_Name LIKE '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getStatus_Name().trim()) + "%' ";
                        }
                        if (customerCareVo.getTx_Status() != null
                                && customerCareVo.getTx_Status().trim().equals(
                                        "已提醒"))
                        {// 已提醒是 1 未提醒是 其他
                            sql += " AND a.tx_status =1";
                        }
                        if (customerCareVo.getTx_Status() != null
                                && customerCareVo.getTx_Status().trim().equals(
                                        "未提醒"))
                        {// 已提醒是 1 未提醒是 其他
                            sql += " AND a.tx_status <> 1";
                        }
                        if (customerCareVo.getCar_Vin() != null
                                && !customerCareVo.getCar_Vin().trim().equals(
                                        ""))
                        {
                            sql += " AND a.car_vin LIKE '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getCar_Vin().trim()) + "%' ";
                        }
                        if (customerCareVo.getBy_Number() != null
                                && !customerCareVo.getBy_Number().equals(""))
                        {
                            sql += " AND a.byNumber ="
                                    + customerCareVo.getBy_Number() + "";
                        }
                        SQLQuery query = null;
                        String s = "";
                        String newsql = "";
                        if (!sql.equals(""))
                        {
                            s = sql.replaceAll("'", "#");
                            newsql = s.replace('#', '"');
                            query = session.createSQLQuery("{CALL njns_find("
                                    + customerCareVo.getPage() + ","
                                    + customerCareVo.getRows() + ",3,'"
                                    + newsql + "')}");
                        }
                        else
                        {
                            query = session.createSQLQuery("{CALL njns_find("
                                    + customerCareVo.getPage() + ","
                                    + customerCareVo.getRows() + ",3,null)}");
                        }
                        HttpSession ssion = ServletActionContext.getRequest()
                                .getSession();
                        SQLQuery querysize = session
                                .createSQLQuery("{CALL njns_find(0,0,3,null)}");
                        ssion.setAttribute("sqlquerySize", querysize.list()
                                .size());
                        return query.list();
                    }
                });
        return list;
    }

    /*
     * (non-Javadoc) 获取首保提醒信息
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CustomerCareDao#getSbtixing(java.
     * lang.String)
     */
    @SuppressWarnings("unchecked")
    
    public List<CustomerCareVo> getSbtixing(final CustomerCareVo customerCareVo)
            throws Exception
    {
        List<CustomerCareVo> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String sql = "";
                        if (customerCareVo.getCar_Fst_Insurance_Date() != null
                                && !customerCareVo.getCar_Fst_Insurance_Date()
                                        .trim().equals(""))
                        {
                            String[] str = customerCareVo
                                    .getCar_Fst_Insurance_Date().split(",");
                            sql += " AND a.Car_Fst_Insurance_Date between '"
                                    + str[0] + "' and '" + str[1] + "'";
                        }
                        if (customerCareVo.getCar_License() != null
                                && !customerCareVo.getCar_License().trim()
                                        .equals(""))
                        {
                            sql += " AND a.Car_License LIKE '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getCar_License().trim()) + "%' ";
                        }
                        if (customerCareVo.getGroup_Name() != null
                                && !customerCareVo.getGroup_Name().trim()
                                        .equals(""))
                        {
                            sql += " AND a.Group_Name LIKE '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getGroup_Name().trim()) + "%' ";
                        }
                        if (customerCareVo.getTx_Resault() != null
                                && !customerCareVo.getTx_Resault().trim()
                                        .equals(""))
                        {
                            sql += " AND a.Tx_Resault LIKE '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getTx_Resault().trim()) + "%' ";
                        }
                        if (customerCareVo.getStatus_Name() != null
                                && !customerCareVo.getStatus_Name().trim()
                                        .equals(""))
                        {
                            sql += " AND a.Status_Name LIKE '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getStatus_Name().trim()) + "%' ";
                        }
                        if (customerCareVo.getTx_Status() != null
                                && customerCareVo.getTx_Status().trim().equals(
                                        "已提醒"))
                        {// 已提醒是 1 未提醒是 其他
                            sql += " AND a.tx_status =1";
                        }
                        if (customerCareVo.getTx_Status() != null
                                && customerCareVo.getTx_Status().trim().equals(
                                        "未提醒"))
                        {// 已提醒是 1 未提醒是 其他
                            sql += " AND a.tx_status <> 1";
                        }
                        if (customerCareVo.getCar_Vin() != null
                                && !customerCareVo.getCar_Vin().trim().equals(
                                        ""))
                        {
                            sql += " AND a.car_vin LIKE '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getCar_Vin().trim()) + "%' ";
                        }
                        if (customerCareVo.getBy_Number() != null
                                && !customerCareVo.getBy_Number().equals(""))
                        {
                            sql += " AND a.byNumber ="
                                    + customerCareVo.getBy_Number() + "";
                        }
                        SQLQuery query = null;
                        String s = "";
                        String newsql = "";
                        if (!sql.equals(""))
                        {
                            s = sql.replaceAll("'", "#");
                            newsql = s.replace('#', '"');
                            query = session.createSQLQuery("{CALL sb_find("
                                    + customerCareVo.getPage() + ","
                                    + customerCareVo.getRows() + ",3,'"
                                    + newsql + "')}");
                        }
                        else
                        {
                            query = session.createSQLQuery("{CALL sb_find("
                                    + customerCareVo.getPage() + ","
                                    + customerCareVo.getRows() + ",3,null)}");
                        }
                        HttpSession ssion = ServletActionContext.getRequest()
                                .getSession();
                        SQLQuery querysize = session
                                .createSQLQuery("{CALL sb_find(0,0,3,null)}");
                        ssion.setAttribute("sqlquerySize", querysize.list()
                                .size());
                        return query.list();
                    }
                });
        return list;
    }

    /*
     * (non-Javadoc) 获取保险/交强提醒信息
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CustomerCareDao#getNjnstixing(int,
     * int)
     */
    @SuppressWarnings("unchecked")
    
    public List<CustomerCareVo> getBxjqtixing(final CustomerCareVo customerCareVo) throws Exception{
        List<CustomerCareVo> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String sql = "SELECT * FROM bxjq_tixing bt where 1 = 1";
                        if (customerCareVo.getCar_Businsurance_Date() != null
                                && !customerCareVo.getCar_Businsurance_Date()
                                        .trim().equals(""))
                        {
                            String[] str = customerCareVo
                                    .getCar_Businsurance_Date().split(",");
                            sql += " and bt.custom_birthday between '" + str[0]
                                    + "' and '" + str[1] + "'";
                        }
                        if (customerCareVo.getCar_Buy_Date() != null
                                && !customerCareVo.getCar_Buy_Date().trim()
                                        .equals(""))
                        {
                            String[] str = customerCareVo.getCar_Buy_Date()
                                    .split(",");
                            sql += " and bt.car_buy_date between '" + str[0]
                                    + "' and '" + str[1] + "'";
                        }
                        if (customerCareVo.getCar_License() != null
                                && !customerCareVo.getCar_License().trim()
                                        .equals(""))
                        {
                            sql += " and bt.car_license like '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getCar_License().trim()) + "%'";
                        }
                        if (customerCareVo.getCar_Vin() != null
                                && !customerCareVo.getCar_Vin().trim().equals(
                                        ""))
                        {
                            sql += " and bt.car_vin like '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getCar_Vin().trim()) + "%'";
                        }
                        if (customerCareVo.getTx_Status() != null
                                && customerCareVo.getTx_Status().trim().equals(
                                        "已提醒"))
                        {
                            sql += " and bt.tx_status = 1";
                        }
                        if (customerCareVo.getTx_Status() != null
                                && customerCareVo.getTx_Status().trim().equals(
                                        "未提醒"))
                        {
                            sql += " and bt.tx_status <> 1";
                        }
                        if (customerCareVo.getTx_Resault() != null
                                && !customerCareVo.getTx_Resault().trim()
                                        .equals(""))
                        {
                            sql += " and bt.tx_resault like '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getTx_Resault().trim()) + "%'";
                        }
                        if (customerCareVo.getStatus_Name() != null
                                && !customerCareVo.getStatus_Name().trim()
                                        .equals(""))
                        {
                            sql += " and bt.status_name like '%"
                                    + StringEscapeUtils.escapeSql(customerCareVo.getStatus_Name().trim()) + "%'";
                        }
                        if (customerCareVo.getBy_Number() != null
                                && !customerCareVo.getBy_Number().trim()
                                        .equals(""))
                        {
                            sql += " and bt.by_Number ="
                                    + customerCareVo.getBy_Number() + "";
                        }
                        SQLQuery query = session.createSQLQuery(sql);
                        HttpSession ssion = ServletActionContext.getRequest()
                                .getSession();
                        ssion.setAttribute("querySize", query.list().size());
                        int beginRow = (customerCareVo.getPage() - 1)
                                * customerCareVo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(customerCareVo.getRows());
                        return query.list();
                    }
                });
        return list;
    }

    /*
     * (non-Javadoc) 获取生日提醒信息
     */
    
    public Json getSrtixing(final CustomerCareVo cvo)
            throws Exception{
    	List<CustomerCareVo> list =new ArrayList<CustomerCareVo>();
    	StringBuffer sb=new StringBuffer("SELECT * FROM sr_tixing st where 1 = 1");
    	if (cvo.getCustom_Birthday() != null&& !cvo.getCustom_Birthday().trim().equals("")){
            String[] str = cvo.getCustom_Birthday().split(",");
            try{
                SimpleDateFormat simp = new SimpleDateFormat( "yyyy-MM-dd");
                java.util.Date udate1 = simp.parse(str[0].toString());
                java.sql.Date date1 = new java.sql.Date(udate1.getTime());
                java.util.Date udate2 = simp.parse(str[1].toString());
                java.sql.Date date2 = new java.sql.Date(udate2 .getTime());
                if (date1.getMonth() == date2.getMonth()){
                	sb.append( " and DAY(st.custom_birthday) between DAY('" + date1 + "') and DAY('" + date2+ "')");
                }
                else{
                	sb.append(" and MONTH(st.custom_birthday) between MONTH('"+ date1+ "') and MONTH('"
                            + date2+ "') and DAY(st.custom_birthday) between DAY('"
                            + date1+ "') and DAY('" + date2+ "')");
                }
            }
            catch(ParseException e){
                e.printStackTrace();
            }
        }
        if (cvo.getCar_Buy_Date() != null && !cvo.getCar_Buy_Date().trim().equals("")){
            String[] str = cvo.getCar_Buy_Date()
                    .split(",");
            sb.append( " and st.car_buy_date between '" + str[0]
                    + "' and '" + str[1] + "'");
        }
        if (cvo.getCar_License() != null&& !cvo.getCar_License().trim().equals("")){
        	sb.append( " and st.car_license like '%"
                    + StringEscapeUtils.escapeSql(cvo.getCar_License().trim()) + "%'");
        }
        if (cvo.getCar_Vin() != null && !cvo.getCar_Vin().trim().equals("")){
        	sb.append(" and st.car_vin like '%"
                    + StringEscapeUtils.escapeSql(cvo.getCar_Vin().trim()) + "%'");
        }
        if (cvo.getTx_Status() != null && cvo.getTx_Status().trim().equals("已提醒")){
        	sb.append( " and st.tx_status = 1");
        }
        if (cvo.getTx_Status() != null&& cvo.getTx_Status().trim().equals("未提醒")){
        	sb.append(" and st.tx_status <> 1");
        }
        if (cvo.getTx_Resault() != null&& !cvo.getTx_Resault().trim().equals("")){
        	sb.append(" and st.tx_resault like '%" + StringEscapeUtils.escapeSql(cvo.getTx_Resault().trim()) + "%'");
        }
        if (cvo.getStatus_Name() != null && !cvo.getStatus_Name().trim().equals("")){
        	sb.append(" and st.status_name like '%"
                    + StringEscapeUtils.escapeSql(cvo.getStatus_Name().trim()) + "%'");
        }
        if (cvo.getBy_Number() != null&& !cvo.getBy_Number().trim().equals("")){
        	sb.append(" and st.by_Number ="
                    + cvo.getBy_Number() + "");
        }
        int count = getSQLCount(sb.toString(), null);
        List<Object[]> resultList= createSQLQuery(sb.toString());
        Object[] obj = null;
        if(resultList!=null&&!resultList.equals("")){
        	for (int i = 0; i < resultList.size(); i++)
            {
                obj = (Object[]) resultList.get(i);
                CustomerCareVo customerCareVo = new CustomerCareVo();
                if (obj[0] != null && !obj[0].equals(""))
                {
                    customerCareVo.setCar_License(obj[0].toString());
                }
                if (obj[1] != null && !obj[1].equals(""))
                {
                    customerCareVo.setCar_Cstl_Name(obj[1].toString());
                }
                if (obj[2] != null && !obj[2].equals(""))
                {
                    customerCareVo.setCar_Relation_Tel1(obj[2].toString());
                }
                if (obj[3] != null && !obj[3].equals(""))
                {
                    customerCareVo.setCar_Relation_Tel2(obj[3].toString());
                }
                if (obj[4] != null && !obj[4].equals(""))
                {
                    customerCareVo.setCar_Relation_Person(obj[4].toString());
                }
                if (obj[5] != null && !obj[5].equals(""))
                {
                    customerCareVo.setCar_Last_Repair_Date(obj[5].toString());
                }
                if (obj[6] != null && !obj[6].equals(""))
                {
                    customerCareVo.setCar_Last_Repair_Distance(obj[6]
                            .toString());
                }
                if (obj[7] != null && !obj[7].equals(""))
                {
                    customerCareVo.setCar_Repair_Cnt(obj[7].toString());
                }
                if (obj[8] != null && !obj[8].equals(""))
                {
                    customerCareVo.setCar_Distance_Per_Day(obj[8].toString());
                }
                if (obj[9] != null && !obj[9].equals(""))
                {
                    customerCareVo.setCar_Id(obj[9].toString());
                }
                if (obj[10] != null && !obj[10].equals(""))
                {
                    customerCareVo.setCtype_Name(obj[10].toString());
                }
                if (obj[11] != null && !obj[11].equals(""))
                {
                    customerCareVo.setCustom_Name(obj[11].toString());
                }
                if (obj[12] != null && !obj[12].equals(""))
                {
                    customerCareVo.setCustom_Name_Addr(obj[12].toString());
                }
                if (obj[13] != null && !obj[13].equals(""))
                {
                    customerCareVo.setCustom_Birthday(obj[13].toString());
                }
                if (obj[14] != null && !obj[14].equals(""))
                {
                    customerCareVo.setProp_Tel(obj[14].toString());
                }
                if (obj[15] != null && !obj[15].equals(""))
                {
                    customerCareVo.setProp_Phone(obj[15].toString());
                }
                if (obj[16] != null && !obj[16].equals(""))
                {
                    customerCareVo.setReceptor(obj[16].toString());
                }
                if (obj[17] != null && !obj[17].equals(""))
                {
                    customerCareVo.setWlcDays(obj[17].toString());
                }
                list.add(customerCareVo);
            }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    // 删除
    
    public void deleteftg(FbkTxGroup ftg)
    {
        this.getHibernateTemplate().delete(ftg);
    }

    /**
     * 转准流失
     */
    @SuppressWarnings("unchecked")
    
    public void updateToF(final CustomerCareVo customerCareVo) throws Exception
    {
        this.getHibernateTemplate().execute(new HibernateCallback()
        {

            
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                String sql = "UPDATE fbk_tx_group ftg SET ftg.CAR_LOST='F' WHERE ftg.G_ID IN (SELECT fvv.G_ID FROM fbk_car_group fvv WHERE fvv.carId='"
                        + customerCareVo.getCar_Id() + "')";
                session.createSQLQuery(sql).executeUpdate();
                return null;
            }
        });
    }

    /**
     * 车辆流失 分析 查询历史维修记录 子节点维修项目名称
     */
    
    public List<FrtRcptItem> getFactoryRepairRecordChild(
            final CustomerCareVo customerCareVo) throws Exception
    {
        return this.getHibernateTemplate().find(
                "FROM FrtRcptItem A WHERE A.frtReception.receptionId='"
                        + customerCareVo.getReception_Id() + "'");

    }
}
