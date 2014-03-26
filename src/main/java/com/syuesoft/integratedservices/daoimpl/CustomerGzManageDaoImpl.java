package com.syuesoft.integratedservices.daoimpl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.fbk.vo.TrackManagementVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.integratedservices.dao.CustomerGzManageDao;
import com.syuesoft.model.FbkCarDcname;
import com.syuesoft.model.FbkCarDcnameId;
import com.syuesoft.model.FbkCollect;
import com.syuesoft.model.FbkDcservey;
import com.syuesoft.model.FbkDcserveyName;
import com.syuesoft.model.FbkDetail;
import com.syuesoft.model.FrtCar;

@Repository("customerGzManageDao")
public class CustomerGzManageDaoImpl extends BaseDaoImpl<Object> implements
        CustomerGzManageDao
{

    /**
     * (non-Javadoc) 查询汇总信息及其部分明细信息 并分页
     * 
     * @see com.syuesoft.integratedservices.dao.CustomerGzManageDao#doFindAll(int,
     *      int)
     */
    @SuppressWarnings("unchecked")
    
    public Datagrid doFindAll(final int page, final int rows,Integer  enterpriseId )throws Exception{
    	Datagrid d=new Datagrid();
    	String queryString = "SELECT c.* FROM custom_follow_view1 c WHERE 1 = 1   and  c.enterpriseId="+enterpriseId;
    	
    	List<Object[]> objs=this.createSQLQuery(queryString);
    	List<TrackManagementVo> trackManagements=new ArrayList<TrackManagementVo>();
    	if(objs!=null && objs.size()>0){
    		for(Object [] obj:objs){
    			 TrackManagementVo trackManagementVo = new TrackManagementVo();
    			   if (obj[0] != null && !obj[0].equals(""))
                   {
                       trackManagementVo.setCarLicense(obj[0].toString());
                   }
                   if (obj[1] != null && !obj[1].equals(""))
                   {
                       trackManagementVo.setCollectId(obj[1].toString());
                   }
                   if (obj[2] != null && !obj[2].equals(""))
                   {
                       trackManagementVo.setReturnVisitDate(obj[2].toString());
                   }
                   if (obj[3] != null && !obj[3].equals(""))
                   {
                       trackManagementVo.setCustomName(obj[3].toString());
                   }
                   if (obj[4] != null && !obj[4].equals(""))
                   {
                       trackManagementVo.setCustomTel1(obj[4].toString());
                   }
                   if (obj[5] != null && !obj[5].equals(""))
                   {
                       trackManagementVo.setCarVan(obj[5].toString());
                   }
                   if (obj[6] != null && !obj[6].equals(""))
                   {
                       trackManagementVo.setRcptitemName(obj[6].toString());
                   }
                   if (obj[7] != null && !obj[7].equals(""))
                   {
                      trackManagementVo.setCtypeId(obj[7].toString());
                   }
                   if (obj[8] != null && !obj[8].equals(""))
                   {
                       trackManagementVo.setCtypeName(obj[8].toString());
                   }
                   if (obj[9] != null && !obj[9].equals(""))
                   {
                       trackManagementVo.setReceptionDistance(obj[9].toString());
                   }
                   if (obj[10] != null && !obj[10].equals(""))
                   {
                       trackManagementVo.setCustomLinkMan(obj[10].toString());
                   }
                   /*if (obj[11] != null && !obj[11].equals(""))
                   {
                       trackManagementVo.setCustomLinkManName(obj[11].toString());
                   }*/
                   if (obj[11] != null && !obj[11].equals(""))
                   {
                       trackManagementVo.setReceptionId(obj[11].toString());
                   }
                   if (obj[12] != null && !obj[12].equals(""))
                   {
                       trackManagementVo.setInterDate(obj[12].toString());
                   }
                   if (obj[13] != null && !obj[13].equals(""))
                   {
                       trackManagementVo.setPreclrTime(obj[13].toString());
                   }
                   if (obj[14] != null && !obj[14].equals(""))
                   {
                       trackManagementVo.setPreclrId(obj[14].toString());
                   }
                   if (obj[15] != null && !obj[15].equals(""))
                   {
                       trackManagementVo.setReceptor(obj[15].toString());
                   }
                   if (obj[16] != null && !obj[16].equals(""))
                   {
                       trackManagementVo.setCarBuyDate(obj[16].toString());
                   }
                   if (obj[17] != null && !obj[17].equals(""))
                   {
                       trackManagementVo.setPropRepPer(obj[17].toString());
                   }
                   /*if (obj[18] != null && !obj[18].equals(""))
                   {
                       trackManagementVo.setPropRepPerName(obj[18].toString());
                   }*/
                   if (obj[18] != null && !obj[18].equals(""))
                   {
                       trackManagementVo.setPropPhone(obj[18].toString());
                   }
                   if (obj[19] != null && !obj[19].equals(""))
                   {
                       trackManagementVo.setCustomAddr(obj[19].toString());
                   }
                   if (obj[20] != null && !obj[20].equals(""))
                   {
                       trackManagementVo.setRepgrpId(obj[20].toString());
                   }
                   if (obj[21] != null && !obj[21].equals(""))
                   {
                       trackManagementVo.setSatisfaction(obj[21].toString());
                   }
                   if (obj[22] != null && !obj[22].equals(""))
                   {
                       trackManagementVo.setMemo(obj[22].toString());
                   }
                   if (obj[23] != null && !obj[23].equals(""))
                   {
                       trackManagementVo.setPreclrRealAmount(obj[23].toString());
                   }
                   if (obj[24] != null && !obj[24].equals(""))
                   {
                       trackManagementVo.setColor(obj[24].toString());
                   }
                   if (obj[25] != null && !obj[25].equals(""))
                   {
                       trackManagementVo.setCarMotorId(obj[25].toString());
                   }
                   if (obj[26] != null && !obj[26].equals(""))
                   {
                       trackManagementVo.setReturnVisitMembers(obj[26].toString());
                   }
                   if (obj[27] != null && !obj[27].equals(""))
                   {
                       trackManagementVo.setCallSituation(obj[27].toString());
                   }
                   if (obj[28] != null && !obj[28].equals(""))
                   {
                       trackManagementVo.setReptName(obj[28].toString());
                   }
                   if (obj[29] != null && !obj[29].equals(""))
                   {
                       trackManagementVo.setReciptReturnvisit(obj[29].toString());
                   }
                   if (obj[30] != null && !obj[30].equals(""))
                   {
                       trackManagementVo.setCustomSex(obj[30].toString());
                   }
                   if (obj[31] != null && !obj[31].equals(""))
                   {
                       trackManagementVo.setComplaintContent(obj[31].toString());
                   }
                   if (obj[32] != null && !obj[32].equals(""))
                   {
                       trackManagementVo.setHandleResult(obj[32].toString());
                   }
                   if (obj[33] != null && !obj[33].equals(""))
                   {
                       trackManagementVo.setComplaintType(obj[33].toString());
                   }
                   if (obj[34] != null && !obj[34].equals(""))
                   {
                       trackManagementVo.setComplaintDegree(obj[34].toString());
                   }
                   if (obj[35] != null && !obj[35].equals(""))
                   {
                       trackManagementVo.setPreclrRemark(obj[35].toString());
                   }
                   if (obj[36] != null && !obj[36].equals(""))
                   {
                       trackManagementVo.setProblemDesc(obj[36].toString());
                   }
                   if (obj[37] != null && !obj[37].equals(""))
                   {
                       trackManagementVo.setHandleProgram(obj[37].toString());
                   }
                   if (obj[38] != null && !obj[38].equals(""))
                   {
                       trackManagementVo.setServiceProposal(obj[38].toString());
                   }
                   if (obj[39] != null && !obj[39].equals(""))
                   {
                       trackManagementVo.setHandlePerson(obj[39].toString());
                   }
                   if (obj[40] != null && !obj[40].equals(""))
                   {
                       trackManagementVo.setCarId(obj[40].toString());
                   }
                   if (obj[41] != null && !obj[41].equals(""))
                   {
                       trackManagementVo.setDetailId(obj[41].toString());
                   }
                   if (obj[42] != null && !obj[42].equals(""))
                   {
                       trackManagementVo.setResvId(obj[42].toString());
                   }
    			 trackManagements.add(trackManagementVo);
    		}
    	}
    	d.setRows(trackManagements);
    	d.setTotal(this.getSQLCount(queryString, null));
    	return d;
    }

    /**
     * (non-Javadoc) 通过条件查询汇总信息
     * 
     * @see com.syuesoft.integratedservices.dao.CustomerGzManageDao#getByCondition(com.syuesoft.fbk.vo.TrackManagementVo)
     */
    @SuppressWarnings("unchecked")
    
    public List<TrackManagementVo> getByCondition(
            final TrackManagementVo trackManagementVo) throws Exception
    {
        List<TrackManagementVo> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "SELECT fcar.carVin ,fcar.carLicense,fcar.carBuyDate,fcar.carMotorId,fcar.color ,ftom.customId,ftom.customName,ftom.customTel1,ftom.customAddr,fri.rcptitemName ,frpt.receptionDistance,frpt.receptionRepPer,frpt.receptionId,frpt.interDate,fpc.preclrTime,fpc.preclrId,frpt.receptor,frpt.propRepPer,frpt.propPhone,fpc.preclrRealAmount,ftom.customSex,fc.satisfaction,fd.complaintContent,fd.memo,fd.handleResult,fc.returnVisitMembers,fc.handlePerson,fc.callSituation,rtp.reptName,fd.complaintType,fd.complaintDegree,fc.reciptReturnvisit,bcb.cbrdName,bct.ctypeName,fc.returnVisitDate,frpt.repgrpId,fc.returnSituatiom,fd.complaintQK,frpt.problemDesc,fpc.preclrRemark,fd.handleProgram,fd.serviceProposal,fcar.carId ,fd.detailId, fc.collectId "
                                + " FROM FrtCar fcar , FbkCollect fc , FbkDetail fd , FrtCustom ftom ,FrtRcptItem fri ,FrtReception frpt , FrtPreClearing fpc , Reptype rtp ,BasCarType bct , BasCarBrand bcb "
                                + " WHERE fcar.basCarType.ctypeId = bct.ctypeId "
                                + " AND bct.basCarBrand.cbrdId = bcb.cbrdId "
                                + " AND fcar.frtCustom.customId = ftom.customId "
                                + " AND fcar.carId = frpt.frtCar.carId "
                                + " AND frpt.receptionId = fri.frtReception.receptionId "
                                + " AND frpt.reptype.reptId = rtp.reptId "
                                + " AND frpt.receptionId = fpc.receptionId "
                                + " AND fc.frtCar.carId = fcar.carId "
                                + " AND fc.collectId = fd.fbkCollect.collectId ";
                        if (trackManagementVo.getPreclrTime() != null
                                && !trackManagementVo.getPreclrTime().trim()
                                        .equals(""))
                        {
                            String[] str = trackManagementVo.getPreclrTime()
                                    .trim().split(",");
                            queryString += " and fpc.preclrTime between '"
                                    + str[0] + "' and '" + str[1] + "' ";
                        }
                        if (trackManagementVo.getReturnVisitDate() != null
                                && !trackManagementVo.getReturnVisitDate()
                                        .trim().equals(""))
                        {
                            String[] str = trackManagementVo
                                    .getReturnVisitDate().trim().split(",");
                            queryString += " and fc.returnVisitDate between '"
                                    + str[0] + "' and '" + str[1] + "' ";
                        }
                        if (trackManagementVo.getCbrdName() != null
                                && !trackManagementVo.getCbrdName().trim()
                                        .equals(""))
                        {
                            queryString += " and bcb.cbrdName like '%"
                                    + StringEscapeUtils.escapeSql(trackManagementVo.getCbrdName().trim()) + "%'";
                        }
                        if (trackManagementVo.getReceptor() != null
                                && !trackManagementVo.getReceptor().trim()
                                        .equals(""))
                        {
                            queryString += " and frpt.receptor like '%"
                                    + StringEscapeUtils.escapeSql(trackManagementVo.getReceptor().trim()) + "%'";
                        }
                        if (trackManagementVo.getCarLicense() != null
                                && !trackManagementVo.getCarLicense().trim()
                                        .equals(""))
                        {
                            queryString += " and fcar.carLicense like '%"
                                    + StringEscapeUtils.escapeSql(trackManagementVo.getCarLicense().trim()) + "%'";
                        }
                        if (trackManagementVo.getCallSituation() != null
                                && !trackManagementVo.getCallSituation().trim()
                                        .equals(""))
                        {
                            queryString += " and fc.callSituation like '%"
                                    + StringEscapeUtils.escapeSql(trackManagementVo.getCallSituation().trim())
                                    + "%'";
                        }
                        if (trackManagementVo.getMemo() != null
                                && !trackManagementVo.getMemo().trim().equals(
                                        ""))
                        {
                            queryString += " and fd.memo like '%"
                                    + StringEscapeUtils.escapeSql(trackManagementVo.getMemo().trim()) + "%'";
                        }
                        if (trackManagementVo.getReturnSituatiom() != null
                                && !trackManagementVo.getReturnSituatiom()
                                        .trim().equals(""))
                        {
                            queryString += " and fc.returnSituatiom like '%"
                                    + StringEscapeUtils.escapeSql(trackManagementVo.getReturnSituatiom().trim())
                                    + "%'";
                        }
                        if (trackManagementVo.getComplaintQK() != null
                                && !trackManagementVo.getComplaintQK().trim()
                                        .equals(""))
                        {
                            queryString += " and fd.complaintQK like '%"
                                    + StringEscapeUtils.escapeSql(trackManagementVo.getComplaintQK().trim()) + "%'";
                        }
                        if (trackManagementVo.getSatisfaction() != null
                                && !trackManagementVo.getSatisfaction().trim()
                                        .equals(""))
                        {
                            String[] str = trackManagementVo.getSatisfaction()
                                    .trim().split(",");
                            String str2 = "";
                            for (int i = 0; i < str.length; i++)
                            {
                                str2 += "," + "'" + str[i] + "'";
                            }
                            queryString += " and fc.satisfaction in " + "("
                                    + str2.replaceFirst(",", "").trim() + ")";
                        }
                        if (trackManagementVo.getReptName() != null
                                && !trackManagementVo.getReptName().trim()
                                        .equals(""))
                        {
                            String[] str = trackManagementVo.getReptName()
                                    .trim().split(",");
                            String str3 = "";
                            for (int i = 0; i < str.length; i++)
                            {
                                str3 += "," + "'" + str[i] + "'";
                            }
                            queryString += " and rtp.reptName in " + "("
                                    + str3.replaceFirst(",", "").trim() + ")";
                        }
                        org.hibernate.Query query = session
                                .createQuery(queryString);
                        HttpSession ssion = ServletActionContext.getRequest()
                                .getSession();
                        ssion.setAttribute("querySize2", query.list().size());
                        return query.list();
                    }

                });
        return list;
    }

    // 通过carid 查询对应的调查记录,并将调查的记录显示
    @SuppressWarnings("unchecked")
    
    public List<FbkDcservey> getDcServeyById(int carId) throws Exception
    {
        String hql = "from FbkDcservey ds where 1 = 1 ";
        if (carId > 0)
        {
            hql += "and ds.frtCar.carId=" + carId;
        }
        return this.getHibernateTemplate().find(hql);
    }

    // 查询3DC调查表的维修项目名称
    
    public List<FbkDcserveyName> getFbkDcserveyName(String str)
            throws Exception
    {
        String hql = " from FbkDcserveyName fdcn where 1 = 1";
        if (str != null && !str.trim().equals(""))
        {
            hql += " and fdcn.dcNameId not in (" + str + ")";
        }
        return this.getHibernateTemplate().find(hql);
    }

    /**
     * 对明细表的信息进行修改 (non-Javadoc)
     * 
     * @see com.syuesoft.integratedservices.dao.CustomerGzManageDao#doUpdate(com.syuesoft.model.FbkDetail)
     */
    
    public void doUpdate(TrackManagementVo trackManagementVo) throws Exception
    {
        FbkDetail fbkDetail = new FbkDetail();
        FbkCollect fbkCollect = new FbkCollect();
        fbkCollect.setCollectId(Integer.parseInt(trackManagementVo
                .getCollectId().trim().toString()));
        fbkDetail.setDetailId(Integer.parseInt(trackManagementVo.getDetailId()
                .trim().toString()));
        fbkDetail.setFbkCollect(fbkCollect);
        if (trackManagementVo.getServiceProposal() != null
                && !trackManagementVo.getServiceProposal().trim().toString()
                        .equals(""))
        {
            fbkDetail.setServiceProposal(trackManagementVo.getServiceProposal()
                    .trim());
        }
        if (trackManagementVo.getComplaintType() != null
                && !trackManagementVo.getComplaintType().trim().toString()
                        .equals(""))
        {
            fbkDetail.setComplaintType(trackManagementVo.getComplaintType()
                    .trim());
        }
        if (trackManagementVo.getComplaintDegree() != null
                && !trackManagementVo.getComplaintDegree().trim().toString()
                        .equals(""))
        {
            fbkDetail.setComplaintDegree(trackManagementVo.getComplaintDegree()
                    .trim());
        }
        if (trackManagementVo.getComplaintContent() != null
                && !trackManagementVo.getComplaintContent().trim().toString()
                        .equals(""))
        {
            fbkDetail.setComplaintContent(trackManagementVo
                    .getComplaintContent().trim());
        }
        if (trackManagementVo.getHandleResult() != null
                && !trackManagementVo.getHandleResult().trim().toString()
                        .equals(""))
        {
            fbkDetail.setHandleResult(trackManagementVo.getHandleResult()
                    .trim());
        }
        if (trackManagementVo.getHandleProgram() != null
                && !trackManagementVo.getHandleProgram().trim().toString()
                        .equals(""))
        {
            fbkDetail.setHandleProgram(trackManagementVo.getHandleProgram()
                    .trim());
        }
        if (trackManagementVo.getMemo() != null
                && !trackManagementVo.getMemo().trim().toString().equals(""))
        {
            fbkDetail.setMemo(trackManagementVo.getMemo().trim());
        }
        if (trackManagementVo.getComplaintQK() != null)
        {
            fbkDetail.setComplaintQK(Integer.parseInt(trackManagementVo
                    .getComplaintQK().trim().toString()));
        }

        Session session = super.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.update(fbkDetail);
        tr.commit();
        session.flush();
        session.close();
    }

    /**
     * (non-Javadoc) 对3DC调查表的新增或修改
     * 
     * @see com.syuesoft.integratedservices.dao.CustomerGzManageDao#saveOrUpdateDcServey()
     */
         
   public void updateFbkCarDcname(TrackManagementVo trackManagementVo,
            int carid) throws Exception
    {
        	 /*  Session session = this.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        FbkCarDcname fcdn = new FbkCarDcname();
        FbkCarDcnameId fcdnid = new FbkCarDcnameId();
        FbkDcserveyName f = new FbkDcserveyName();

        FrtCar car = new FrtCar();
        car.setCarId(carid + "");
        fcdnid.setFrtCar(car);
        f.setDcNameId(Integer.parseInt(trackManagementVo.getDcNameId()));
        fcdnid.setFbkDcserveyName(f);
        if (trackManagementVo.getEvaluate() != null
                && trackManagementVo.getEvaluate().trim().equals("很好"))
        {
            fcdn.setEvaluate(1);
        }
        if (trackManagementVo.getEvaluate() != null
                && trackManagementVo.getEvaluate().trim().equals("好"))
        {
            fcdn.setEvaluate(2);
        }
        if (trackManagementVo.getEvaluate() != null
                && trackManagementVo.getEvaluate().trim().equals("一般"))
        {
            fcdn.setEvaluate(3);
        }
        if (trackManagementVo.getEvaluate() != null
                && trackManagementVo.getEvaluate().trim().equals("不好"))
        {
            fcdn.setEvaluate(4);
        }
        if (trackManagementVo.getEvaluate() != null
                && trackManagementVo.getEvaluate().trim().equals("很不好"))
        {
            fcdn.setEvaluate(5);
        }
        if (trackManagementVo.getScore() != null
                && !trackManagementVo.getEvaluate().trim().equals(""))
        {
            fcdn.setScore(Integer.parseInt(trackManagementVo.getScore()));
        }
        fcdn.setId(fcdnid);
        session.saveOrUpdate(fcdn);
        tr.commit();
        session.flush();
        session.close();*/
    }

    // 对回访表的修改
    
    public void updateCollect(TrackManagementVo trackManagementVo)
            throws Exception
    {
        FbkCollect fbkCollect = new FbkCollect();
        FrtCar frtCar = new FrtCar();
        frtCar.setCarId(trackManagementVo.getCarId().toString());
      //  fbkCollect.setFrtCar(frtCar);
        fbkCollect.setCollectId(Integer.parseInt(trackManagementVo
                .getCollectId().toString()));
        if (trackManagementVo.getReturnVisitMembers() != null
                && !trackManagementVo.getReturnVisitMembers().trim().toString()
                        .equals(""))
        {
            fbkCollect.setReturnVisitMembers(trackManagementVo
                    .getReturnVisitMembers());
        }
        if (trackManagementVo.getCallSituation() != null
                && !trackManagementVo.getCallSituation().trim().toString()
                        .equals(""))
        {
            fbkCollect.setCallSituation(trackManagementVo.getCallSituation());
        }
        if (trackManagementVo.getSatisfaction() != null
                && !trackManagementVo.getSatisfaction().trim().toString()
                        .equals(""))
        {
            fbkCollect.setSatisfaction(trackManagementVo.getSatisfaction());
        }
        if (trackManagementVo.getReturnVisitDate() != null
                && !trackManagementVo.getReturnVisitDate().trim().toString()
                        .equals(""))
        {
            fbkCollect.setReturnVisitDate(new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss").parse(trackManagementVo
                    .getReturnVisitDate()));
        }
        if (trackManagementVo.getReciptReturnvisit() != null
                && !trackManagementVo.getReciptReturnvisit().trim().toString()
                        .equals(""))
        {
            fbkCollect.setReciptReturnvisit(trackManagementVo
                    .getReciptReturnvisit());
        }
        if (trackManagementVo.getHandlePerson() != null
                && !trackManagementVo.getHandlePerson().trim().toString()
                        .equals(""))
        {
            fbkCollect.setHandlePerson(trackManagementVo.getHandlePerson());
        }
        if (trackManagementVo.getReturnSituatiom() != null
                && !trackManagementVo.getReturnSituatiom().trim().toString()
                        .equals(""))
        {
            fbkCollect.setReturnSituatiom(Integer.parseInt(trackManagementVo
                    .getReturnSituatiom()));
        }
        this.getHibernateTemplate().update(fbkCollect);
    }

    /**
     * (non-Javadoc) //通过车辆id和项目表的id查询中间表的评论和评分
     * 
     * @see com.syuesoft.integratedservices.dao.CustomerGzManageDao#getFbkCarDcname()
     */
    
    public List<FbkCarDcname> getFbkCarDcname(
            TrackManagementVo trackManagementVo) throws Exception
    {
        String hql = "from FbkCarDcname fcd where fcd.id.frtCar.carId='"
                + trackManagementVo.getCarId() + "'";
        return this.getHibernateTemplate().find(hql);
    }

    
    public List<FbkDcserveyName> getFbkDcserveyNameByid(int dcNameId)
            throws Exception
    {
        String hql = "from FbkDcserveyName fcd where fcd.dcNameId=" + dcNameId;
        return this.getHibernateTemplate().find(hql);
    }

    /**
     * 获取维修类别名称
     */
    
    public List getBasRepairTypeName() throws Exception
    {

        return this.getHibernateTemplate().find(
                "SELECT A.reptypName from BasRepairType A");
    }

	
	public Datagrid doFindCollect(int page, int rows,String carId) throws Exception {
		String sql="SELECT 	f1.satisfaction, f1.return_visit_date,f2.complaint_content,(SELECT c.dataValue  FROM bas_parentdictionary p ,bas_childdictionary c" +
				"  WHERE c.parent_id=p.parent_id AND p.dataKey='satisfactionDegree' AND c.dataKey=f1.satisfaction) AS satisfactionName" +
				" FROM fbk_collect  f1 LEFT JOIN  fbk_detail f2 ON f1.collect_id=f2.collect_id LEFT JOIN frt_pre_clearing fpc  ON fpc.PRECLR_ID=f1.PRECLR_ID"+
				"	LEFT JOIN frt_reception fr ON fr.RECEPTION_ID=fpc.RECEPTION_ID WHERE fr.CAR_ID='"+carId+"'";
		List<TrackManagementVo> lst=new ArrayList<TrackManagementVo>();
		List<Object[]> objs=this.createSQLQuery(sql);
		if(objs!=null && objs.size()>0){
			for(Object [] obj:objs){
				TrackManagementVo vo=new TrackManagementVo();
				if(obj[0]!=null && !("".equals(obj[0]))){
					vo.setSatisfaction(obj[0].toString());
				}
				if(obj[1]!=null && !("".equals(obj[1]))){
					vo.setReturnVisitDate(obj[1].toString());
				}
				if(obj[2]!=null && !("".equals(obj[2]))){
					vo.setComplaintContent(obj[2].toString());
				}
				if(obj[3]!=null && !("".equals(obj[3]))){
					vo.setSatisfactionName(obj[3].toString());
				}
				lst.add(vo);
			}
		}
		Datagrid d=new Datagrid();
		d.setRows(lst);
		d.setTotal(this.getSQLCount(sql, null));
		return d;
	}

}
