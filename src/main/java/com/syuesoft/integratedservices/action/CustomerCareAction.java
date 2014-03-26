package com.syuesoft.integratedservices.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.fbk.vo.CustomerCareVo;
import com.syuesoft.fbk.vo.FbkTxGroupVo;
import com.syuesoft.integratedservices.service.CustomerCareService;
import com.syuesoft.model.BasCarPhonetrackerresult;
import com.syuesoft.model.BasCarStatus;
import com.syuesoft.model.FbkCarGroup;
import com.syuesoft.model.FbkCarGroupId;
import com.syuesoft.model.FbkTxGroup;
import com.syuesoft.model.FrtRcptItem;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.vipmanagement.vo.CarLostAnalysisVo;
import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value = "basePackage")
@Action("customerCareAction")
public class CustomerCareAction extends BaseAction implements
        ModelDriven<CustomerCareVo>
{

    FormatTime ft = new FormatTime();

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private CustomerCareService customerCareService;

    public CustomerCareService getCustomerCareService()
    {
        return customerCareService;
    }

    public void setCustomerCareService(CustomerCareService customerCareService)
    {
        this.customerCareService = customerCareService;
    }

    public CustomerCareVo getCustomerCareVo()
    {
        return customerCareVo;
    }

    public void setCustomerCareVo(CustomerCareVo customerCareVo)
    {
        this.customerCareVo = customerCareVo;
    }

    private CustomerCareVo customerCareVo = new CustomerCareVo();

    
    public CustomerCareVo getModel()
    {
        return customerCareVo;
    }

    /*
     * 获取提醒信息
     */
    public void getBytixing(){
        try{
        	customerCareVo.setEnterpriseId(getNowEnterpriseId());
        	 super.writeJson(customerCareService.getBytixing(customerCareVo));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /*
     * 提醒信息保存方法
     */
    public void saveReminder(){
        Message msg = new Message();
        try
        {
        	customerCareVo.setEnterpriseId(getNowEnterpriseId());
        	customerCareService.saveReminder(customerCareVo);
            msg.setSuccess(true);
            msg.setMsg("数据保存成功!");
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            msg.setMsg("数据保存失败!");
            e.printStackTrace();
        }
        super.writeJson(msg);

    }
    /*
     * 提醒信息更新方法
     */
    public void updateReminder(){
        Message msg = new Message();
        try
        {
        	customerCareService.updateReminder(customerCareVo);
            msg.setSuccess(true);
            msg.setMsg("数据更新成功!");
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            msg.setMsg("数据更新失败!");
            e.printStackTrace();
        }
        super.writeJson(msg);

    }
    /*
     * 提醒信息删除方法
     */
    public void deleteReminder(){
        Message msg = new Message();
        try
        {
        	customerCareService.deleteReminder(customerCareVo);
            msg.setSuccess(true);
            msg.setMsg("数据删除成功!");
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            msg.setMsg("数据删除失败!");
            e.printStackTrace();
        }
        super.writeJson(msg);

    }

    /*
     * 查询历史回访数据
     */
    public void getHistoricalVisit(){
        try{
        	 super.writeJson(customerCareService.getHistoricalVisit(customerCareVo));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public String getByCondition()
    {
        return null;
    }

    /*
     * 提醒查询中的统计分类查询 对统计结果和 结果比例
     */
    public void doResualt()
    {
    	 try{
    		 customerCareVo.setEnterpriseId(getNowEnterpriseId());
        	 super.writeJson(customerCareService.doResualt(customerCareVo));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // 查询历史回访记录
    public String factoryVisit()
    {
        List list = new ArrayList();
        // Json json = new Json();
        try
        {
            if (customerCareVo.getCar_Id() != null
                    && !customerCareVo.getCar_Id().trim().equals(""))
            {
                List Visitlist = customerCareService
                        .getFactoryVisit(customerCareVo);
                Object[] obj = null;
                for (int i = 0; i < Visitlist.size(); i++)
                {
                    FbkTxGroupVo ftgvo = new FbkTxGroupVo();
                    obj = (Object[]) Visitlist.get(i);
                    ftgvo.setCar_Id(customerCareVo.getCar_Id());
                    if (obj[0] != null && !obj[0].equals(""))
                    {
                        ftgvo.setG_Id(obj[0] + "");
                    }
                    if (obj[1] != null && !obj[1].equals(""))
                    {
                        ftgvo.setGroup_Name(obj[1] + "");
                    }
                    if (obj[2] != null && !obj[2].equals(""))
                    {
                        ftgvo.setReturn_Visit_Date(obj[2] + "");
                    }
                    if (obj[3] != null && !obj[3].equals(""))
                    {
                        ftgvo.setTx_Return_Visit_Date(obj[3] + "");
                    }
                    if (obj[4] != null && !obj[4].equals(""))
                    {
                        ftgvo.setVisit_Content(obj[4] + "");
                    }
                    if (obj[5] != null && !obj[5].equals(""))
                    {
                        ftgvo.setTx_Resault(obj[5] + "");
                    }
                    if (obj[6] != null && !obj[6].equals(""))
                    {
                        ftgvo.setStatus_Name(obj[6] + "");
                    }
                    if (obj[7] != null && !obj[7].equals(""))
                    {
                        ftgvo.setReturn_Visit_Resault(obj[7] + "");
                    }
                    if (obj[8] != null && !obj[8].equals(""))
                    {
                        ftgvo.setTx_Status(obj[8] + "");
                    }
                    list.add(ftgvo);
                }
                // json.setTotal(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("FbkFactoryVisitsize").toString()));
            }
            // json.setRows(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(list);
        return null;
    }

    // 历史回访记录的保存方法
    public String doSave()
    {
        Message msg = new Message();

        try
        {
            FbkTxGroup ftg = new FbkTxGroup();
            if (customerCareVo.getGroup_Name() != null
                    && !customerCareVo.getGroup_Name().trim().equals(""))
            {
                ftg.setGroupName(customerCareVo.getGroup_Name());
            }

            if (customerCareVo.getTx_Date() != null
                    && !customerCareVo.getTx_Date().trim().equals(""))
            {
                Date date = java.sql.Date.valueOf(customerCareVo.getTx_Date());
                ftg.setReturnVisitDate(date);
            }
            if (customerCareVo.getTx_Resault() != null
                    && !customerCareVo.getTx_Resault().trim().equals(""))
            {
                ftg.setTxResault(customerCareVo.getTx_Resault());
            }
            if (customerCareVo.getTx_Return_Visit_Date() != null
                    && !customerCareVo.getTx_Return_Visit_Date().trim().equals(
                            ""))
            {
                ftg.setTxReturnVisitDate(java.sql.Date.valueOf(customerCareVo
                        .getTx_Return_Visit_Date()));
            }
            if (customerCareVo.getVisit_Content() != null
                    && !customerCareVo.getVisit_Content().trim().equals(""))
            {
                ftg.setVisitContent(customerCareVo.getVisit_Content());
            }
            if (customerCareVo.getGroup_Name() != null
                    && !customerCareVo.getGroup_Name().trim().equals(""))
            {
                ftg.setGroupName(customerCareVo.getGroup_Name());
            }
            if (customerCareVo.getStatus_Name() != null
                    && !customerCareVo.getStatus_Name().trim().equals(""))
            {
                ftg.setStatusName(customerCareVo.getStatus_Name());
            }
            ftg.setTxStatus(1);// 回访的时候默认将状态改为1
            customerCareService.doSave(ftg, customerCareVo.getCar_Id());
            msg.setSuccess(true);
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(msg);
        return null;
    }

    // 历史维修记录查询方法(查询父节点信息)
    public String getFactoryWxRecord()
    {
        Object[] obj = null;
        List list = new ArrayList();
        try
        {
            if (customerCareVo.getCar_Id() != null
                    && !customerCareVo.getCar_Id().trim().equals(""))
            {
                List Recordlist = customerCareService
                        .getFactoryWxRecord(customerCareVo);
                for (int i = 0; i < Recordlist.size(); i++)
                {
                    obj = (Object[]) Recordlist.get(i);
                    CustomerCareVo vo = new CustomerCareVo();
                    vo.setInter_Date(obj[0] + "");
                    vo.setCar_Last_Maint_Distance(obj[1] + "");
                    vo.setStf_Name(obj[2] + "");
                    vo.setExp_Del_Car_Time(obj[3] + "");
                    vo.setProp_Rep_Per(obj[4] + "");
                    vo.setReception_Id(obj[5] + "");
                    vo.setPreclr_Sum_Amount(obj[6] + "");
                    vo.setCar_Id(obj[7] + "");
                    vo
                            .setState(customerCareService
                                    .getFactoryRepairRecordChild(vo).size() > 0 ? "closed"
                                    : "open");
                    list.add(vo);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(list);
        return null;
    }

    // 历史维修记录查询方法(查询子节点信息)
    public void getFactoryRepairRecordChild()
    {
        List list = new ArrayList();
        try
        {
            List<FrtRcptItem> rlist = customerCareService
                    .getFactoryRepairRecordChild(customerCareVo);
            for (FrtRcptItem frtRcptItem : rlist)
            {
                CarLostAnalysisVo vo = new CarLostAnalysisVo();
                vo.setRcpitem_Name(frtRcptItem.getRepitemName());
                vo.setIconCls("icon-blank");
                vo.setReception_Id("");
                list.add(vo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(list);
    }

    // 删除回访记录
    public String doDelete()
    {
        FbkCarGroupId ftgid = new FbkCarGroupId();
        FbkCarGroup fcg = new FbkCarGroup();
        ftgid.setCarId(customerCareVo.getCar_Id());
        ftgid.setGId(Integer.parseInt(customerCareVo.getG_Id()));
        fcg.setId(ftgid);
        try
        {
            customerCareService.doDelete(fcg); // 删除中间表的记录
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * (non-Javadoc) 提醒查询的方法 此查询的方法查询的是已经提醒的记录
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CustomerCareDao#getRemenberSearch()
     */
    public void getRemenberSearch()
    {
    	 try{
    		 customerCareVo.setEnterpriseId(getNowEnterpriseId());
        	 super.writeJson(customerCareService.getRemenberSearch(customerCareVo));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // 获取值 提醒结果用于下拉框
    public String getTixingjieguo()
    {
        List list = new ArrayList();
        try
        {
            List oblist = customerCareService
                    .getObject("FROM BasCarPhonetrackerresult ");
            BasCarPhonetrackerresult bpk1 = new BasCarPhonetrackerresult();
            for (int i = 0; i < oblist.size(); i++)
            {
                bpk1 = (BasCarPhonetrackerresult) oblist.get(i);
                ComboxVo vo = new ComboxVo();
                vo.setId(bpk1.getCarPhonetrackerresultName());
                vo.setName(bpk1.getCarPhonetrackerresultName());
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

    // 获取值 流失原因用于下拉框
    public String getLuishiyuanying()
    {
        List list = new ArrayList();
        try
        {
            List oblist = customerCareService.getObject("FROM  BasCarStatus");
            BasCarStatus bcs1 = new BasCarStatus();
            for (int i = 0; i < oblist.size(); i++)
            {
                bcs1 = (BasCarStatus) oblist.get(i);
                ComboxVo vo = new ComboxVo();
                vo.setId(bcs1.getStatusName());
                vo.setName(bcs1.getStatusName());
                list.add(vo);
            }
            HttpServletRequest request = ServletActionContext.getRequest();
            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 获取年检年审的信息
     */
    public String getNianjianShen()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List list = new ArrayList();
        Json json = new Json();
        try
        {
            List allList = customerCareService.getNianjianShen(customerCareVo);
            Object[] obj = null;
            for (int i = 0; i < allList.size(); i++)
            {
                obj = (Object[]) allList.get(i);
                if (obj[23] != null)
                {
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
                        customerCareVo.setCar_Annual_Date(obj[2].toString());
                    }
                    if (obj[3] != null && !obj[3].equals(""))
                    {
                        customerCareVo.setCar_Examined_Date(obj[3].toString());
                    }
                    if (obj[4] != null && !obj[4].equals(""))
                    {
                        customerCareVo.setCustom_Name(obj[4].toString());
                    }
                    if (obj[5] != null && !obj[5].equals(""))
                    {
                        customerCareVo.setCar_Relation_Tel1(obj[5].toString());
                    }
                    if (obj[6] != null && !obj[6].equals(""))
                    {
                        customerCareVo.setCar_Relation_Tel2(obj[6].toString());
                    }
                    if (obj[7] != null && !obj[7].equals(""))
                    {
                        customerCareVo
                                .setCar_Relation_Person(obj[7].toString());
                    }
                    if (obj[8] != null && !obj[8].equals(""))
                    {
                        customerCareVo.setProp_Tel(obj[8].toString());
                    }
                    if (obj[9] != null && !obj[9].equals(""))
                    {
                        customerCareVo.setProp_Phone(obj[9].toString());
                    }
                    if (obj[10] != null && !obj[10].equals(""))
                    {
                        customerCareVo.setParea_Name(obj[10].toString());
                    }
                    if (obj[11] != null && !obj[11].equals(""))
                    {
                        customerCareVo.setCar_Last_Repair_Date(obj[11]
                                .toString());
                    }
                    if (obj[12] != null && !obj[12].equals(""))
                    {
                        customerCareVo.setCar_Last_Repair_Distance(obj[12]
                                .toString());
                    }
                    if (obj[13] != null && !obj[13].equals(""))
                    {
                        customerCareVo.setCar_Repair_Cnt(obj[13].toString());
                    }
                    if (obj[14] != null && !obj[14].equals(""))
                    {
                        customerCareVo.setCar_Distance_Per_Day(obj[14]
                                .toString());
                    }
                    if (obj[15] != null && !obj[15].equals(""))
                    {
                        customerCareVo.setWlcDays(obj[15].toString());
                    }
                    if (obj[16] != null && !obj[16].equals(""))
                    {
                        customerCareVo.setReceptor(obj[16].toString());
                    }
                    if (obj[23] != null && !obj[23].equals(""))
                    {
                        customerCareVo.setCar_Id(obj[23].toString());
                    }
                    list.add(customerCareVo);
                }
            }
            json.setTotal(Integer.parseInt(session.getAttribute("sqlquerySize")
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
     * 获取首保提醒信息
     */
    public String getSbtixing()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List list = new ArrayList();
        Json json = new Json();
        try
        {
            List allList = null;
            allList = customerCareService.getSbtixing(customerCareVo);
            Object[] obj = null;
            for (int i = 0; i < allList.size(); i++)
            {
                obj = (Object[]) allList.get(i);
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
                    customerCareVo.setCtype_Name(obj[2].toString());
                }
                if (obj[3] != null && !obj[3].equals(""))
                {
                    customerCareVo.setCustom_Name(obj[3].toString());
                }
                if (obj[4] != null && !obj[4].equals(""))
                {
                    customerCareVo.setCar_Relation_Tel1(obj[4].toString());
                }
                if (obj[5] != null && !obj[5].equals(""))
                {
                    customerCareVo.setCar_Relation_Tel2(obj[5].toString());
                }
                if (obj[6] != null && !obj[6].equals(""))
                {
                    customerCareVo.setCar_Relation_Person(obj[6].toString());
                }
                if (obj[7] != null && !obj[7].equals(""))
                {
                    customerCareVo.setReceptor(obj[7].toString());
                }
                if (obj[8] != null && !obj[8].equals(""))
                {
                    customerCareVo.setCar_Buy_Date(obj[8].toString());
                }
                if (obj[9] != null && !obj[9].equals(""))
                {
                    customerCareVo.setParea_Name(obj[9].toString());
                }
                if (obj[10] != null && !obj[10].equals(""))
                {
                    customerCareVo.setYjsb_Date(obj[10].toString());
                }
                if (obj[18] != null && !obj[18].equals(""))
                {
                    customerCareVo.setCar_Id(obj[18].toString());
                }
                list.add(customerCareVo);
            }
            json.setTotal(Integer.parseInt(session.getAttribute("sqlquerySize")
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
     * 获取保险交强险的提醒信息
     */
    public String getBxjqtixing()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Json json = new Json();
        List list = new ArrayList();
        try
        {
            List allList = null;
            allList = customerCareService.getBxjqtixing(customerCareVo);
            Object[] obj = null;
            for (int i = 0; i < allList.size(); i++)
            {
                obj = (Object[]) allList.get(i);
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
                    customerCareVo.setCar_Basinsurance_Date(obj[2].toString());
                }
                if (obj[3] != null && !obj[3].equals(""))
                {
                    customerCareVo.setCar_Businsurance_Date(obj[3].toString());
                }
                if (obj[4] != null && !obj[4].equals(""))
                {
                    customerCareVo.setCar_Maint_Interva(obj[4].toString());
                }
                if (obj[5] != null && !obj[5].equals(""))
                {
                    customerCareVo.setCar_Relation_Tel1(obj[5].toString());
                }
                if (obj[6] != null && !obj[6].equals(""))
                {
                    customerCareVo.setCar_Relation_Tel2(obj[6].toString());
                }
                if (obj[7] != null && !obj[7].equals(""))
                {
                    customerCareVo.setCar_Relation_Person(obj[7].toString());
                }
                if (obj[8] != null && !obj[8].equals(""))
                {
                    customerCareVo.setCar_Last_Repair_Date(obj[8].toString());
                }
                if (obj[9] != null && !obj[9].equals(""))
                {
                    customerCareVo.setCar_Last_Repair_Distance(obj[9]
                            .toString());
                }
                if (obj[10] != null && !obj[10].equals(""))
                {
                    customerCareVo.setCar_Repair_Cnt(obj[10].toString());
                }
                if (obj[11] != null && !obj[11].equals(""))
                {
                    customerCareVo.setCar_Distance_Per_Day(obj[11].toString());
                }
                if (obj[12] != null && !obj[12].equals(""))
                {
                    customerCareVo.setCar_Buy_Date(obj[12].toString());
                }
                if (obj[13] != null && !obj[13].equals(""))
                {
                    customerCareVo.setCar_Id(obj[13].toString());
                }
                if (obj[14] != null && !obj[14].equals(""))
                {
                    customerCareVo.setCtype_Name(obj[14].toString());
                }
                if (obj[15] != null && !obj[15].equals(""))
                {
                    customerCareVo.setCustom_Name(obj[15].toString());
                }
                if (obj[16] != null && !obj[16].equals(""))
                {
                    customerCareVo.setCustom_Name_Addr(obj[16].toString());
                }
                if (obj[17] != null && !obj[17].equals(""))
                {
                    customerCareVo.setProp_Tel(obj[17].toString());
                }
                if (obj[18] != null && !obj[18].equals(""))
                {
                    customerCareVo.setProp_Phone(obj[18].toString());
                }
                if (obj[19] != null && !obj[19].equals(""))
                {
                    customerCareVo.setReceptor(obj[19].toString());
                }
                if (obj[20] != null && !obj[20].equals(""))
                {
                    customerCareVo.setWlcDays(obj[20].toString());
                }
                list.add(customerCareVo);
            }
            json.setTotal(Integer.parseInt(session.getAttribute("querySize")
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
     * 获取生日提醒信息
     */
    public void getSrtixing()
    {
        try{
            super.writeJson(customerCareService.getSrtixing(customerCareVo));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 将客户 转为准流失状态
     */
    public String updateToF()
    {
        try
        {
            customerCareService.updateToF(customerCareVo);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
