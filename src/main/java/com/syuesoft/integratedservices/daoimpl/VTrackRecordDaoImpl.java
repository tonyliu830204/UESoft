package com.syuesoft.integratedservices.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.fbk.vo.VTrackRecordVo;
import com.syuesoft.integratedservices.dao.VTrackRecordDao;
import com.syuesoft.model.FbkDcserveyName;
import com.syuesoft.model.Reptype;

@Repository("vTrackRecordDao")
public class VTrackRecordDaoImpl extends BaseDaoImpl<Object> implements
        VTrackRecordDao
{

    // 获取跟踪记录查询信息
    @SuppressWarnings("unchecked")
    
    public String getAll(final VTrackRecordVo vTrackRecordVo) throws Exception
    {
    	String sorce=formatServiceReceiveAnalyze(vTrackRecordVo.getPage(),vTrackRecordVo.getRows(),vTrackRecordVo.getEnterpriseId());
    	if(vTrackRecordVo.getFlag()!=null && vTrackRecordVo.getFlag()==true){
			return sorce;
		}
    	return weaveDatagrid( sorce);
    }
	private String formatServiceReceiveAnalyze(int page,int rows,int  enterpriseId) throws Exception{
		String sql="select * from customTj    WHERE  enterpriseId="+enterpriseId; 
		List<Object[]>objs=this.createSQLQuery(sql);
		int count=this.getSQLCount(sql,null);
		Map  map=getAll3Dc(enterpriseId);
		StringBuffer sb1=new StringBuffer("{\"rows\":[");
		if(objs!=null&&objs.size()>0){
			for (int i=0;i<objs.size();i++) {
				Object[] obj= objs.get(i);
				sb1.append("{");
				sb1.append("\"returnVisitDate\":\""+obj[0]+"\",");
				sb1.append("\"carLicense\":\""+obj[1]+"\",");
				sb1.append("\"ctypeId\":\""+obj[2]+"\",");
				sb1.append("\"ctypeName\":\""+obj[3]+"\",");
				sb1.append("\"cbrdId\":\""+obj[4]+"\",");
				sb1.append("\"cbrdName\":\""+obj[5]+"\",");
				sb1.append("\"customName\":\""+obj[6]+"\",");
				sb1.append("\"customTel1\":\""+obj[7]+"\",");
				sb1.append("\"prepItemName\":\""+obj[8]+"\",");
				sb1.append("\"carVan\":\""+obj[9]+"\",");
				sb1.append("\"receptionDistance\":\""+obj[10]+"\",");
				sb1.append("\"linkMan\":\""+obj[11]+"\",");
				sb1.append("\"receptionId\":\""+obj[12]+"\",");
				sb1.append("\"customJm\":\""+obj[13]+"\",");
				sb1.append("\"interDate\":\""+obj[14]+"\",");
				sb1.append("\"receptionFactTime\":\""+obj[15]+"\",");
				sb1.append("\"carBuyDate\":\""+obj[16]+"\",");
				sb1.append("\"propRepPer\":\""+obj[17]+"\",");
				sb1.append("\"propTel\":\""+obj[18]+"\",");
				sb1.append("\"customAddr\":\""+obj[19]+"\",");
				sb1.append("\"satisfaction\":\""+obj[20]+"\",");
				sb1.append("\"satisfactionName\":\""+obj[21]+"\",");
				sb1.append("\"preclrRealAmount\":\""+obj[22]+"\",");
				sb1.append("\"receptor\":\""+obj[23]+"\",");
				sb1.append("\"pareaName\":\""+obj[24]+"\",");
				sb1.append("\"reptId\":\""+obj[25]+"\",");
				sb1.append("\"reptName\":\""+obj[26]+"\",");
				sb1.append("\"memo\":\""+obj[27]+"\",");
				sb1.append("\"complaintContent\":\""+obj[28]+"\",");
				sb1.append("\"handleResult\":\""+obj[29]+"\",");
				sb1.append("\"complaintType\":\""+obj[30]+"\",");
				List lst=(List) map.get(i);
				if(lst!=null && lst.size()>0){
				for(int k=0;k<lst.size();k++ ){
						FbkDcserveyName  f=(FbkDcserveyName)lst.get(k);
						sb1.append("\"eval"+k+"\":\""+f.getMemo()+"\",");
						k++;
					}
				}
				sb1.append("\"complaintDegree\":\""+obj[31]+"\"");
				sb1.append("},");
			}
			String s1=sb1.substring(0,sb1.length()-1);
			sb1=new StringBuffer(s1);
		}
		sb1.append("],\"total\":"+count+"}");
		return sb1.toString();
	}
	private String weaveDatagrid(String source) throws Exception{
		List  dcserveyNames=getAllDcserveyName();
		StringBuffer sb=new StringBuffer("$('#genzongjiluhuizong_tb_id').datagrid({");
		sb.append("url:'${pageContext.request.contextPath}/vTrackRecordAction!getAll.action?flag=true',");
		sb.append("fit:true,nowrap:false,singleSelect:true,pagination:true,rownumbers:true,");
		sb.append("showFooter:true,loadMsg:\"数据加载中，请稍后……\",");
		sb.append("columns : [[");
		sb.append("{field : 'returnVisitDate',title:'回访日期',width : 100,sortable : true}," );
		sb.append("{field : 'carLicense',title:'车辆牌照',width : 100,sortable : true},");
		sb.append("{field : 'ctypeName',title:'车辆型号',width : 100,sortable : true}," );
		sb.append("{field : 'cbrdName',title:'车辆品牌',width : 100,sortable : true}," );
		sb.append("{field : 'customName',title:'客户名称',width : 100,sortable : true}," );
		sb.append("{field : 'customTel1',title:'客户电话',width : 100,sortable : true}," );
		sb.append("{field : 'carVan',title:'VIN号',width : 100,sortable : true},");
		sb.append("{field : 'receptionDistance',title : '公里数',width : 100,sortable : true},") ;
		sb.append("{field : 'receptionId',title : '工单号',width : 100,sortable : true}," );
		sb.append("{field : 'interDate',title : '进厂日期',width : 100,sortable : true}," );
		sb.append("{field : 'receptionFactTime',title : '出厂日期',width : 100,sortable : true}," );
		sb.append("{field : 'carBuyDate',title : '购车日期',width : 100,sortable : true},");
		sb.append("{field : 'propRepPer',title : '托修人',width : 100,sortable : true}," );
		sb.append("{field : 'propTel',title : '托修人电话',width : 100,sortable : true},");
		sb.append("{field : 'customAddr',title : '地址',width : 100,sortable : true},") ;
		sb.append("{field : 'cbrdName',title : '车品牌',width : 100,sortable : true}," );
		sb.append("{field : 'satisfaction',title : '满意度',width : 100,sortable : true},");
		sb.append("{field : 'preclrRealAmount',title : '金额',width : 100,sortable : true},");
		sb.append("{field : 'receptor',title : '接待员',width : 100,sortable : true}, ");
		sb.append("{field : 'pareaName',title : '区域',width : 100,sortable : true}, ");
		sb.append("{field : 'reptName',title : '维修类别',width : 100,sortable : true},");
		sb.append("{field : 'memo',title : '备注',width : 100,sortable : true},") ;
		sb.append("{field : 'complaintContent',title : '客户投诉内容',width : 100,sortable : true}," );
		sb.append("{field : 'handleResult',title : '处理结果',width : 100,sortable : true},");
		sb.append("{field : 'complaintType',title : '投诉类型',width : 100,sortable : true},") ;
		if(dcserveyNames!=null && dcserveyNames.size()>0){
			for(int i=0;i<dcserveyNames.size();i++){
				FbkDcserveyName  f=(FbkDcserveyName)dcserveyNames.get(i);
				sb.append("{field : 'eval"+i+"',title : '"+f.getServeyName()+"',width : 100,sortable : true},") ;
			}			
		}
		sb.append("{field : 'complaintDegree',title : '投诉程度',width : 100,sortable : true}");
		sb.append("]]");
		sb.append("});");
		return sb.toString();
	}
    public List<Object> getAllDcserveyName()throws Exception{
    	//查询客户回访项目表
    	String hql="  from FbkDcserveyName c where 1=1 order by  c.dcNameId  asc";
    	return this.find(hql);
    }
    public Map getAll3Dc(int  enterpriseId) throws Exception{
    	Map map=new HashMap();
    	String sql="SELECT collect_id   FROM fbk_collect where  enterprise_id="+enterpriseId+" ORDER BY  collect_id asc";
    	List lst=this.createSQLQuery(sql);
    	if(lst!=null && lst.size()>0){
    		for(int i=0;i<lst.size();i++){
    			Integer obj=(Integer) lst.get(i);
    			int collectId=obj;
    			String str="SELECT  fdn.SERVEY_NAME,a.evalName ,fdn.DC_NAME_ID FROM fbk_dcservey_name   fdn   LEFT JOIN ( "+
    					   "SELECT  fcd.EVALUATE,fcd.DC_NAME_ID, (SELECT datavalue   FROM bas_childdictionary   c  WHERE  " +
    					   "c.datakey=fcd.EVALUATE )   AS evalName  FROM   fbk_car_dcname  fcd  WHERE fcd.collect_id=1  AND  fcd.enterprise_id="+enterpriseId +
    					   " )a  ON fdn.DC_NAME_ID=a.DC_NAME_ID  WHERE  fdn.enterprise_id="+enterpriseId  +"  ORDER BY fdn.DC_NAME_ID  asc";
    			List<Object[]> carDcnames=this.createSQLQuery(str);
    			List dc=new ArrayList();
    			if(carDcnames!=null && carDcnames.size()>0){
    				for(Object[]o:carDcnames){	
    					FbkDcserveyName  f=new FbkDcserveyName();
    					if(o[0]!=null && !("".equals(o[0]))){
    						f.setServeyName(o[0].toString());
    					}
    					if(o[1]!=null && !("".equals(o[1]))){
    						f.setMemo(o[1].toString());
    					}
    					
    					dc.add(f);
    				}
    			}
    			map.put(i, dc);
    		}
    		
    	}
    	return map;
    }
    /*
     * (non-Javadoc) 获取评分和回访项目名称
     * 
     * @see
     * com.syuesoft.integratedservices.dao.VTrackRecordDao#getScoreAndName()
     */
    @SuppressWarnings("unchecked")
    
    public List getScoreAndName(final String carLicense) throws Exception
    {
        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {

            
            public List doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                String hql = "SELECT	fcd.score, fdn.serveyName "
                        + "FROM FbkCarDcname  fcd,"
                        + "FbkDcserveyName fdn, "
                        + "FrtCar fc "
                        + "WHERE fcd.id.frtCar.carId= fc.carId "
                        + "AND fcd.id.fbkDcserveyName.dcNameId = fdn.dcNameId "
                        + "AND fcd.id.frtCar.carId IN (SELECT fc.carId FROM FrtCar fc WHERE fc.carLicense = '"
                        + carLicense + "')";
                Query query = session.createQuery(hql);
                return query.list();
            }
        });
        return list;
    }

    // 获取下拉框的项目名称
    
    public List getReturnSeverName() throws Exception
    {
        String hql = "from FbkDcserveyName";
        return this.getHibernateTemplate().find(hql);
    }

    // 获取不固定列需要显示的的项目名称
    @SuppressWarnings("unchecked")
    
    public List getName(final VTrackRecordVo vTrackRecordVo) throws Exception
    {
        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {
            
            public List doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                List tlist = new ArrayList();
                session.createSQLQuery("{CALL col_row()}");
                String sql = "SELECT DISTINCT column_name FROM information_schema.columns WHERE table_name='tb_row_col' AND column_name <> 'car_license'";
                String newsql = "";
                SQLQuery query = null;
                if (vTrackRecordVo.getServeyName() != null
                        && !vTrackRecordVo.getServeyName().trim().equals(""))
                {
                    String str = vTrackRecordVo.getServeyName();
                    String[] arry = str.split(",");
                    sql += " AND column_name IN (";
                    String strsql = "";
                    for (int i = 0; i < arry.length; i++)
                    {
                        strsql += ",'" + arry[i] + "'";
                    }
                    newsql = sql + strsql.substring(1) + ")";
                    query = session.createSQLQuery(newsql);
                    return query.list();
                }
                else
                {
                    return tlist;
                }

            }
        });
        return list;
    }

    // 获取动态显示的数据
    @SuppressWarnings("unchecked")
    
    public List getData(final String carLicense) throws Exception
    {
        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {
            
            public List doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                // SQLQuery query =
                // session.createSQLQuery("SELECT DISTINCT * FROM tb_row_col WHERE car_license ='"+carLicense+"'");
                SQLQuery query = session
                        .createSQLQuery("SELECT C.CAR_LICENSE,B.SERVEY_NAME,A.SCORE FROM fbk_car_dcname A,fbk_dcservey_name B,frt_car C WHERE A.DC_NAME_ID = B.DC_NAME_ID AND A.CAR_ID = C.CAR_ID AND car_license ='"
                                + carLicense + "'");
                return query.list();
            }
        });
        return list;
    }

    // 获取车品牌 (用于combox 下拉框)
    
    public List getCarbrand() throws Exception
    {
        return this.getHibernateTemplate().find("from BasCarBrand");
    }

    // 获取员工名称 (用于combox 下拉框)
    
    public List getStuff() throws Exception
    {
        return this.getHibernateTemplate().find("from BasStuff");
    }

    // 获取部门名称(用于combox 下拉框)
    
    public List getPartment() throws Exception
    {
        return this.getHibernateTemplate().find("from BasRepairGroup");
    }

    // 此方法概述:将前台传过来的回访日期分割截取成每一个月的时间段传给service
    @SuppressWarnings("unchecked")
    
    public List getDateInfomation(final VTrackRecordVo vTrackRecordVo)
            throws Exception
    {
        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {

            
            public List doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                HttpSession sesion = ServletActionContext.getRequest()
                        .getSession();
                Map map2 = new LinkedMap();
                List newlist = new ArrayList();
                String sql = "";
                String newtime = "";
                int index = 1;
                // 判断时间是否为空
                if (vTrackRecordVo.getReturnVisitDate() != null
                        && !vTrackRecordVo.getReturnVisitDate().trim().equals(""))
                {
                    String[] str = vTrackRecordVo.getReturnVisitDate().split(",");
                    int year1 = Integer.parseInt(str[0].toString().trim()
                            .substring(0, 4));
                    int month1 = Integer.parseInt(str[0].toString().trim()
                            .substring(5, 7));
                    int year2 = Integer.parseInt(str[1].toString().trim()
                            .substring(0, 4));
                    int month2 = Integer.parseInt(str[1].toString().trim()
                            .substring(5, 7));
                    int day2 = Integer.parseInt(str[1].toString().trim()
                            .substring(str[1].lastIndexOf("-") + 1));
                    int month = 0;

                    // 判断月份是否为 4 , 6 , 9 , 11 月
                    // 循环行数
                    String[] strarry = { " ", "and vtr.satisfaction='很满意'",
                            "and vtr.satisfaction='满意'",
                            "and vtr.satisfaction='一般'",
                            "and vtr.satisfaction='不满意'",
                            "and vtr.satisfaction='很不满'" };
                    String[] title = { "回访件数", "很满意", "满意", "一般", "不满意", "很不满" };

                    for (int j = 0; j < strarry.length; j++)
                    {
                        Map map = new LinkedMap();
                        map.put("biaoti", title[j]);
                        String str0 = str[0] + "";
                        // 获取两时间之间的月份数
                        if (year1 > year2)
                        {
                            month = (year1 - year2) * 12 + (month2 - month1)
                                    + 1;
                        }
                        else
                        {
                            month = (year2 - year1) * 12 + (month2 - month1)
                                    + 1;
                        }

                        // 如果month>24 时 按两年计算 将较大的时间向前推两年
                        if (month > 24)
                        {
                            String m2 = "";
                            String d2 = "";
                            month = 24;
                            if (month2 < 10)
                            {
                                m2 = "0" + month2;
                            }
                            else
                            {
                                m2 = month2 + "";
                            }
                            ;
                            if (day2 < 10)
                            {
                                d2 = "0" + day2;
                            }
                            else
                            {
                                d2 = day2 + "";
                            }
                            ;
                            str0 = year2 - 2 + "-" + m2 + "-" + d2;
                        }
                        int str0year1 = Integer.parseInt(str0.substring(0, 4));
                        int str0month1 = Integer.parseInt(str0.substring(5, 7));
                        int str0day1 = Integer.parseInt(str0.substring(8));
                        // 循环列数
                        for (int i = 0; i < month; i++)
                        {
                            SQLQuery query = null;
                            String mapstr = "";
                            String bili = "";
                            int total = 0;
                            int score = 0;
                            if (str0month1 == 4 || str0month1 == 6
                                    || str0month1 == 9 || str0month1 == 11)
                            {
                                if (str0month1 < 10)
                                {
                                    newtime = str0year1 + "-0" + str0month1
                                            + "-30";
                                }
                                else
                                {
                                    newtime = str0year1 + "-" + str0month1
                                            + "-30";
                                }
                                str0day1 = 30;
                            }

                            if (str0month1 == 1 || str0month1 == 3
                                    || str0month1 == 5 || str0month1 == 7
                                    || str0month1 == 8 || str0month1 == 10
                                    || str0month1 == 12)
                            {
                                if (str0month1 < 10)
                                {
                                    newtime = str0year1 + "-0" + str0month1
                                            + "-31";
                                }
                                else
                                {
                                    newtime = str0year1 + "-" + str0month1
                                            + "-31";
                                }
                                str0day1 = 31;
                            }
                            if (str0month1 == 2)
                            {
                                if (str0month1 < 10)
                                {
                                    newtime = str0year1 + "-0" + str0month1
                                            + "-28";
                                }
                                else
                                {
                                    newtime = str0year1 + "-" + str0month1
                                            + "-28";
                                }
                                str0day1 = 28;
                            }
                            if (str0month1 > 12)
                            {
                                str0year1 = str0year1 + 1;
                                str0month1 = 1;
                            }
                            if (str0month1 < 10)
                            {
                                newtime = str0year1 + "-0" + str0month1 + "-01";
                            }
                            else
                            {
                                newtime = str0year1 + "-" + str0month1 + "-01";
                            }
                            str0 = newtime;
                            newtime = str0year1 + "-" + str0month1 + "-"
                                    + str0day1;
                            sql = "SELECT count(*) FROM v_track_record vtr WHERE vtr.return_visit_date BETWEEN '"
                                    + str0
                                    + "' AND '"
                                    + newtime
                                    + "' "
                                    + strarry[j] + "";
                            query = session.createSQLQuery(sql);
                            score = Integer.parseInt(query.list().get(0)
                                    .toString());
                            // 获取map中的回访件数
                            if (map2.get("id" + i + "") != null)
                            {
                                mapstr = map2.get("id" + i + "").toString();
                                total = Integer.parseInt(mapstr.toString());
                            }
                            if (total > 0 && score > 0)
                            {
                                bili = score * 100 / total + "%";
                            }
                            if (str0month1 < 10)
                            {
                                if (total > 0)
                                {
                                    if (score * 100 / total == 0)
                                    {
                                        map.put("date" + str0year1 + "-" + "0"
                                                + str0month1, "");
                                    }
                                    else
                                    {
                                        map.put("date" + str0year1 + "-" + "0"
                                                + str0month1, bili);
                                    }
                                }
                                else
                                {
                                    if (mapstr.equals("0"))
                                    {
                                        map.put("date" + str0year1 + "-" + "0"
                                                + str0month1, "");
                                    }
                                    else
                                    {
                                        map.put("date" + str0year1 + "-" + "0"
                                                + str0month1, query.list().get(
                                                0));
                                    }
                                }

                            }
                            else
                            {
                                if (total > 0)
                                {
                                    if (score * 100 / total == 0)
                                    {
                                        map.put("date" + str0year1 + "-"
                                                + str0month1, "");
                                    }
                                    else
                                    {
                                        map.put("date" + str0year1 + "-"
                                                + str0month1, bili);
                                    }

                                }
                                else
                                {
                                    if (mapstr.equals("0"))
                                    {
                                        map.put("date" + str0year1 + "-"
                                                + str0month1, "");
                                    }
                                    else
                                    {
                                        if (mapstr.equals("0"))
                                        {
                                            map.put("date" + str0year1 + "-"
                                                    + str0month1, "");
                                        }
                                        else
                                        {
                                            map.put("date" + str0year1 + "-"
                                                    + str0month1, query.list()
                                                    .get(0));
                                        }
                                    }
                                }
                            }
                            str0month1 = str0month1 + 1;
                            // 将回访件数至map
                            if (index == 1)
                            {
                                map2.put("id" + i + "", query.list().get(0));
                            }
                        }// for 列
                        newlist.add(map);
                        index = 2;
                    }// for 行
                }// if
                return newlist;
            }
        });
        return list;
    }

    // 柱状图报表查询信息
    @SuppressWarnings("unchecked")
    
    public List getJfreeChartInfomation(final VTrackRecordVo vTrackRecordVo)
            throws Exception
    {
        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {

            
            public List doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                HttpSession sesion = ServletActionContext.getRequest()
                        .getSession();

                List newlist = new ArrayList();
                String sql = "";
                String newtime = "";
                int index = 1;
                // 判断时间是否为空
                if (vTrackRecordVo.getReturnVisitDate() != null
                        && !vTrackRecordVo.getReturnVisitDate().trim().equals(
                                ""))
                {
                    String[] str = vTrackRecordVo.getReturnVisitDate().split(
                            ",");
                    int year1 = Integer.parseInt(str[0].toString().trim()
                            .substring(0, 4));
                    int month1 = Integer.parseInt(str[0].toString().trim()
                            .substring(5, 7));
                    int year2 = Integer.parseInt(str[1].toString().trim()
                            .substring(0, 4));
                    int month2 = Integer.parseInt(str[1].toString().trim()
                            .substring(5, 7));
                    int day2 = Integer.parseInt(str[1].toString().trim()
                            .substring(str[1].lastIndexOf("-") + 1));
                    int month = 0;
                    // 获取两时间之间的月份数
                    if (year1 > year2)
                    {
                        month = (year1 - year2) * 12 + (month2 - month1) + 1;
                    }
                    else
                    {
                        month = (year2 - year1) * 12 + (month2 - month1) + 1;
                    }
                    // 判断月份是否为 4 , 6 , 9 , 11 月
                    // 循环行数
                    String[] strarry = { "and vtr.satisfaction='很满意'",
                            "and vtr.satisfaction='满意'",
                            "and vtr.satisfaction='一般'",
                            "and vtr.satisfaction='不满意'",
                            "and vtr.satisfaction='很不满'" };

                    String str0 = str[0] + "";
                    // 如果month>24 时 按两年计算 将较大的时间向前推两年
                    if (month > 24)
                    {
                        String m2 = "";
                        String d2 = "";
                        month = 24;
                        if (month2 < 10)
                        {
                            m2 = "0" + month2;
                        }
                        else
                        {
                            m2 = month2 + "";
                        }
                        ;
                        if (day2 < 10)
                        {
                            d2 = "0" + day2;
                        }
                        else
                        {
                            d2 = day2 + "";
                        }
                        ;
                        str0 = year2 - 2 + "-" + m2 + "-" + d2;
                    }
                    int str0year1 = Integer.parseInt(str0.substring(0, 4));
                    ;
                    int str0month1 = Integer.parseInt(str0.substring(5, 7));
                    int str0day1 = Integer.parseInt(str0.substring(8));
                    // 循环列数
                    for (int i = 0; i < month; i++)
                    {
                        Map map2 = new LinkedMap();
                        for (int j = 0; j < strarry.length; j++)
                        {
                            Map map = new LinkedMap();

                            SQLQuery query = null;
                            String mapstr = "";
                            String bili = "";
                            int total = 0;
                            int score = 0;
                            if (str0month1 == 4 || str0month1 == 6
                                    || str0month1 == 9 || str0month1 == 11)
                            {
                                if (str0month1 < 10)
                                {
                                    newtime = str0year1 + "-0" + str0month1
                                            + "-30";
                                }
                                else
                                {
                                    newtime = str0year1 + "-" + str0month1
                                            + "-30";
                                }
                                str0day1 = 30;
                            }

                            if (str0month1 == 1 || str0month1 == 3
                                    || str0month1 == 5 || str0month1 == 7
                                    || str0month1 == 8 || str0month1 == 10
                                    || str0month1 == 12)
                            {
                                if (str0month1 < 10)
                                {
                                    newtime = str0year1 + "-0" + str0month1
                                            + "-31";
                                }
                                else
                                {
                                    newtime = str0year1 + "-" + str0month1
                                            + "-31";
                                }
                                str0day1 = 31;
                            }
                            if (str0month1 == 2)
                            {
                                if (str0month1 < 10)
                                {
                                    newtime = str0year1 + "-0" + str0month1
                                            + "-28";
                                }
                                else
                                {
                                    newtime = str0year1 + "-" + str0month1
                                            + "-28";
                                }
                                str0day1 = 28;
                            }
                            if (str0month1 > 12)
                            {
                                str0year1 = str0year1 + 1;
                                str0month1 = 1;
                            }
                            if (str0month1 < 10)
                            {
                                newtime = str0year1 + "-0" + str0month1 + "-01";
                            }
                            else
                            {
                                newtime = str0year1 + "-" + str0month1 + "-01";
                            }
                            str0 = newtime;
                            newtime = str0year1 + "-" + str0month1 + "-"
                                    + str0day1;
                            sql = "SELECT count(*) FROM v_track_record vtr WHERE vtr.return_visit_date BETWEEN '"
                                    + str0
                                    + "' AND '"
                                    + newtime
                                    + "' "
                                    + strarry[j] + "";
                            query = session.createSQLQuery(sql);

                            score = Integer.parseInt(query.list().get(0)
                                    .toString());
                            // 评价存入map
                            String s = strarry[j].toString().substring(
                                    strarry[j].toString().indexOf("'") + 1,
                                    strarry[j].toString().lastIndexOf("'"));
                            map.put("pingjia" + j, s);
                            // 将每月的满意人数 ，不满意人数， 等 的记录存入map中
                            map.put("score" + j, score);
                            // 存入时间
                            if (str0month1 > 9)
                            {
                                map.put("date" + j, str0year1 + "-"
                                        + str0month1);
                            }
                            else
                            {
                                map.put("date" + j, str0year1 + "-0"
                                        + str0month1);
                            }
                            map2.put("id" + j, map);
                        }// for 行
                        newlist.add(map2);
                        str0month1 = str0month1 + 1;
                    }// for 列

                }// if
                return newlist;
            }
        });
        return list;
    }

}
