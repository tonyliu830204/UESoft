package com.syuesoft.integratedservices.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.fbk.vo.FbkCarDcnameVo;
import com.syuesoft.fbk.vo.TrackManagementVo;
import com.syuesoft.integratedservices.service.CustomerGzManageService;
import com.syuesoft.model.FbkCarDcname;
import com.syuesoft.model.FbkDcserveyName;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@Controller
@Scope("prototype")
public class CustomerGzManageAction extends BaseAction implements
        ModelDriven<TrackManagementVo>
{

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    private TrackManagementVo trackManagementVo = new TrackManagementVo();
    Logger logger=Logger.getLogger(this.getClass());
    @Autowired
    private CustomerGzManageService customerGzManageService = null;

    public CustomerGzManageService getCustomerGzManageService()
    {
        return customerGzManageService;
    }

    public void setCustomerGzManageService(
            CustomerGzManageService customerGzManageService)
    {
        this.customerGzManageService = customerGzManageService;
    }

    public TrackManagementVo getTrackManagementVo()
    {
        return trackManagementVo;
    }

    public void setTrackManagementVo(TrackManagementVo trackManagementVo)
    {
        this.trackManagementVo = trackManagementVo;
    }

    
    public TrackManagementVo getModel()
    {
        return trackManagementVo;
    }

    /**
     * 查询所有,并将查询的数据一一设给vo
     */
    public void doFindAll()
    {
    	try {
    		trackManagementVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson( customerGzManageService.doFindAll(trackManagementVo.getPage(), trackManagementVo.getRows(),trackManagementVo.getEnterpriseId()));
		} catch (Exception e) {
			logger.error("查询客户跟踪汇总信息失败!", e);
		}
    }
    /**
     * 查询3DC回访记录
     */
    public void doFindCarDcname()
    {
    	try {
			super.writeJson( customerGzManageService.doFindCarDcname(trackManagementVo.getPage(), trackManagementVo.getRows(),trackManagementVo.getCollectId()));
		} catch (Exception e) {
			logger.error("查询客户跟踪汇总信息失败!", e);
		}
    }
    /**
     * 查询历史满意度
     */
    public void doFindCollect()
    {
    	try {
			super.writeJson( customerGzManageService.doFindCollect(trackManagementVo.getPage(), trackManagementVo.getRows(),trackManagementVo.getCarId()));
		} catch (Exception e) {
			logger.error("查询客户跟踪汇总信息失败!", e);
		}
    }

    /**
     * 
     * 条件查询
     */
    public String getByCondition()
    {
        HttpSession Sion = ServletActionContext.getRequest().getSession();
        Json json = new Json();
        List list = new ArrayList();
        List volist = null;
        try
        {
            volist = customerGzManageService.getByCondition(trackManagementVo);
            Object[] obj = null;
            if (volist.size() != 0)
            {
                for (int i = 0; i < volist.size(); i++)
                {
                    obj = (Object[]) volist.get(i);

                    TrackManagementVo trackManagementVo = new TrackManagementVo();
                    if (obj[0] != null && !obj[0].equals(""))
                    {
                        trackManagementVo.setCarVan(obj[0].toString());
                    }
                    if (obj[1] != null && !obj[1].equals(""))
                    {
                        trackManagementVo.setCarLicense(obj[1].toString());
                    }
                    if (obj[2] != null && !obj[2].equals(""))
                    {
                        trackManagementVo.setCarBuyDate(obj[2].toString());
                    }
                    if (obj[3] != null && !obj[3].equals(""))
                    {
                        trackManagementVo.setCarMotorId(obj[3].toString());
                    }
                    if (obj[4] != null && !obj[4].equals(""))
                    {
                        trackManagementVo.setColor(obj[4].toString());
                    }
                    if (obj[5] != null && !obj[5].equals(""))
                    {
                        trackManagementVo.setCustomId(obj[5].toString());
                    }
                    if (obj[6] != null && !obj[6].equals(""))
                    {
                        trackManagementVo.setCustomName(obj[6].toString());
                    }
                    if (obj[7] != null && !obj[7].equals(""))
                    {
                        trackManagementVo.setCustomTel1(obj[7].toString());
                    }
                    if (obj[8] != null && !obj[8].equals(""))
                    {
                        trackManagementVo.setCustomAddr(obj[8].toString());
                    }
                    if (obj[9] != null && !obj[9].equals(""))
                    {
                        trackManagementVo.setRcptitemName(obj[9].toString());
                    }
                    if (obj[10] != null && !obj[10].equals(""))
                    {
                        trackManagementVo.setReceptionDistance(obj[10]
                                .toString());
                    }
                    if (obj[11] != null && !obj[11].equals(""))
                    {
                        trackManagementVo
                                .setReceptionRepPer(obj[11].toString());
                    }
                    if (obj[12] != null && !obj[12].equals(""))
                    {
                        trackManagementVo.setReceptionId(obj[12].toString());
                    }
                    if (obj[13] != null && !obj[13].equals(""))
                    {
                        trackManagementVo.setInterDate(obj[13].toString());
                    }
                    if (obj[14] != null && !obj[14].equals(""))
                    {
                        trackManagementVo.setPreclrTime(obj[14].toString());
                    }
                    if (obj[15] != null && !obj[15].equals(""))
                    {
                        trackManagementVo.setPreclrId(obj[15].toString());
                    }
                    if (obj[16] != null && !obj[16].equals(""))
                    {
                        trackManagementVo.setReceptor(obj[16].toString());
                    }
                    if (obj[17] != null && !obj[17].equals(""))
                    {
                        trackManagementVo.setPropRepPer(obj[17].toString());
                    }
                    if (obj[18] != null && !obj[18].equals(""))
                    {
                        trackManagementVo.setPropPhone(obj[18].toString());
                    }
                    if (obj[19] != null && !obj[19].equals(""))
                    {
                        trackManagementVo.setPreclrRealAmount(obj[19]
                                .toString());
                    }
                    if (obj[20] != null && !obj[20].equals(""))
                    {
                        trackManagementVo.setCustomSex(obj[20].toString());
                    }
                    if (obj[21] != null && !obj[21].equals(""))
                    {
                        trackManagementVo.setSatisfaction(obj[21].toString());
                    }
                    if (obj[22] != null && !obj[22].equals(""))
                    {
                        trackManagementVo.setComplaintContent(obj[22]
                                .toString());
                    }
                    if (obj[23] != null && !obj[23].equals(""))
                    {
                        trackManagementVo.setMemo(obj[23].toString());
                    }
                    if (obj[24] != null && !obj[24].equals(""))
                    {
                        trackManagementVo.setHandleResult(obj[24].toString());
                    }
                    if (obj[25] != null && !obj[25].equals(""))
                    {
                        trackManagementVo.setReturnVisitMembers(obj[25]
                                .toString());
                    }
                    if (obj[26] != null && !obj[26].equals(""))
                    {
                        trackManagementVo.setHandlePerson(obj[26].toString());
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
                        trackManagementVo.setComplaintType(obj[29].toString());
                    }
                    if (obj[30] != null && !obj[30].equals(""))
                    {
                        trackManagementVo
                                .setComplaintDegree(obj[30].toString());
                    }
                    if (obj[31] != null && !obj[31].equals(""))
                    {
                        trackManagementVo.setReciptReturnvisit(obj[31]
                                .toString());
                    }
                    if (obj[32] != null && !obj[32].equals(""))
                    {
                        trackManagementVo.setCbrdName(obj[32].toString());
                    }
                    if (obj[33] != null && !obj[33].equals(""))
                    {
                        trackManagementVo.setCtypeName(obj[33].toString());
                    }
                    if (obj[34] != null && !obj[34].equals(""))
                    {
                        trackManagementVo
                                .setReturnVisitDate(obj[34].toString());
                    }
                    if (obj[35] != null && !obj[35].equals(""))
                    {
                        trackManagementVo.setRepgrpId(obj[35].toString());
                    }

                    if (obj[36] != null && !obj[36].equals(""))
                    {
                        trackManagementVo
                                .setReturnSituatiom(obj[36].toString());
                    }
                    if (obj[37] != null && !obj[37].equals(""))
                    {
                        trackManagementVo.setComplaintQK(obj[37].toString());
                    }
                    if (obj[38] != null && !obj[38].equals(""))
                    {
                        trackManagementVo.setProblemDesc(obj[38].toString());
                    }
                    if (obj[39] != null && !obj[39].equals(""))
                    {
                        trackManagementVo.setPreclrRemark(obj[39].toString());
                    }
                    if (obj[40] != null && !obj[40].equals(""))
                    {
                        trackManagementVo.setHandleProgram(obj[40].toString());
                    }
                    if (obj[41] != null && !obj[41].equals(""))
                    {
                        trackManagementVo
                                .setServiceProposal(obj[41].toString());
                    }
                    list.add(trackManagementVo);// fc.returnSituatiom,fd.complaintQK,frpt.problemDesc,fpc.preclrRemark,fd.handleProgram,fd.serviceProposal
                }
            }
            json.setTotal(Integer.parseInt(Sion.getAttribute("querySize2")
                    .toString()));
            json.setRows(list);
            super.writeJson(json);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * //查询3DC调查表 public String getFbkDcserveyName(){ Json json = new Json();
     * List listvo = new ArrayList();
     * 
     * try { List<FbkDcserveyName> namelist =
     * customerGzManageService.getFbkDcserveyName(); for (int i = 0; i <
     * namelist.size(); i++) { FbkDcserveyNameVo fdn = new FbkDcserveyNameVo();
     * fdn.setDcNameId(namelist.get(i).getDcNameId().toString());
     * fdn.setServeyName(namelist.get(i).getServeyName()); listvo.add(fdn); }
     * json.setRows(listvo); json.setTotal(namelist.size());
     * super.writeJson(json); } catch (Exception e) { e.printStackTrace(); }
     * return null; }
     */
    // 通过carid 查询中间表的评论及评分并切查询未回访的项目名称
    public String getFbkCarDcname()
    {
        List list = new ArrayList();
        List list2 = new ArrayList();
        List<FbkDcserveyName> FbkDcserveyNameList2 = null;
        try
        {
            List<FbkCarDcname> FbkCarDcnameList = customerGzManageService
                    .getFbkCarDcname(trackManagementVo);// 通过车辆id获取评论评分
            for (FbkCarDcname fbkCarDcname : FbkCarDcnameList)
            {
               /* FbkCarDcnameVo fbkcdvo = new FbkCarDcnameVo();
                list2.add(fbkCarDcname.getId().getFbkDcserveyName()
                        .getDcNameId());
                FbkDcserveyNameList2 = customerGzManageService
                        .getFbkDcserveyNameByid(fbkCarDcname.getId()
                                .getFbkDcserveyName().getDcNameId());// 通过项目名称id获取评论评分对应的项目名称
*/                for (FbkDcserveyName fbkDcserveyName : FbkDcserveyNameList2)
                {
                   /* fbkcdvo.setServeyName(fbkDcserveyName.getServeyName());
                    fbkcdvo.setDcNameId(fbkDcserveyName.getDcNameId()
                            .toString());*/
                }
              /*  if (fbkCarDcname.getEvaluate() != null
                        && fbkCarDcname.getEvaluate() == 1)
                {
                    fbkcdvo.setEvaluate("很好");
                }
                if (fbkCarDcname.getEvaluate() != null
                        && fbkCarDcname.getEvaluate() == 1)
                {
                    fbkcdvo.setEvaluate("很好");
                }
                if (fbkCarDcname.getEvaluate() != null
                        && fbkCarDcname.getEvaluate() == 2)
                {
                    fbkcdvo.setEvaluate("好");
                }
                if (fbkCarDcname.getEvaluate() != null
                        && fbkCarDcname.getEvaluate() == 3)
                {
                    fbkcdvo.setEvaluate("一般");
                }
                if (fbkCarDcname.getEvaluate() != null
                        && fbkCarDcname.getEvaluate() == 4)
                {
                    fbkcdvo.setEvaluate("不好");
                }
                if (fbkCarDcname.getEvaluate() != null
                        && fbkCarDcname.getEvaluate() == 5)
                {
                    fbkcdvo.setEvaluate("很不好");
                }*/
              /*  if (fbkCarDcname.getScore() != null)
                {
                    fbkcdvo.setScore(fbkCarDcname.getScore().toString());
                }

                list.add(fbkcdvo);*/
            }
            // 将存入集合lsit2的回访项目id处理后传给后台查询
            String str = "";
            String str2 = "";
            if (list2.size() != 0)
            {
                for (int i = 0; i < list2.size(); i++)
                {
                    str += list2.get(i) + ",";
                }
                str2 = str.substring(0, str.lastIndexOf(",")).trim();
            }
            List<FbkDcserveyName> FbkDcserveyNameList = customerGzManageService
                    .getFbkDcserveyName(str2);// 获取所有项目的名称
            for (int i = 0; i < FbkDcserveyNameList.size(); i++)
            {
                FbkCarDcnameVo fbkcdvo2 = new FbkCarDcnameVo();
                fbkcdvo2.setServeyName(FbkDcserveyNameList.get(i)
                        .getServeyName());
                fbkcdvo2.setDcNameId(FbkDcserveyNameList.get(i).getDcNameId()
                        .toString());
                list.add(fbkcdvo2);
            }

            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // 修改明细信息
    public String doUpdate()
    {
        Message msg = new Message();
        try
        {
            customerGzManageService.doUpdate(trackManagementVo);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 对3dc调查表的修改或保存
     */
    public String updateFbkCarDcname()
    {
        try
        {
            HttpServletRequest request = ServletActionContext.getRequest();
            String carId = request.getParameter("carId");
            int carid = 0;
            if (carId != null && !carId.equals(""))
            {
                carid = Integer.parseInt(carId);
            }
            customerGzManageService
                    .updateFbkCarDcname(trackManagementVo, carid);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // 多回访表的修改
    public String updateCollect()
    {
        Message msg = new Message();
        try
        {
            customerGzManageService.updateCollect(trackManagementVo);
            msg.setSuccess(true);
            msg.setMsg("数据修改成功!");
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            msg.setMsg("数据修改失败!");
            e.printStackTrace();
        }
        super.writeJson(msg);
        return null;

    }
    //客户跟踪管理修改方法
    public String updateFbkCollect()
    {
        Message msg = new Message();
        try
        {
        	trackManagementVo.setEnterpriseId(getNowEnterpriseId());
            customerGzManageService.updateFbkCollect(trackManagementVo);
            msg.setSuccess(true);
            msg.setMsg("数据修改成功!");
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            msg.setMsg("数据修改失败!");
            e.printStackTrace();
        }
        super.writeJson(msg);
        return null;

    }  
    //客户跟踪管理删除方法
    public String deleteFbkCollect()
    {
        Message msg = new Message();
        try
        {
            customerGzManageService.deleteFbkCollect(trackManagementVo.getCollectId());
            msg.setSuccess(true);
            msg.setMsg("数据修改成功!");
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            msg.setMsg("数据修改失败!");
            e.printStackTrace();
        }
        super.writeJson(msg);
        return null;

    }

    /**
     * 获取维修类别名称 用于combox
     */
    public String getBasRepairTypeName()
    {
        List list = new ArrayList();
        try
        {
            List rlist = customerGzManageService.getBasRepairTypeName();
            for (int i = 0; i < rlist.size(); i++)
            {
                ComboxVo vo = new ComboxVo();
                vo.setId(rlist.get(i).toString());
                vo.setName(rlist.get(i).toString());
                list.add(vo);
            }
            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
