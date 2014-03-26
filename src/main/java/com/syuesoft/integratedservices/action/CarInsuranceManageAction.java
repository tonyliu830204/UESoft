package com.syuesoft.integratedservices.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fbk.vo.CarInsuranceManagerVo;
import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.fbk.vo.InsuranceTypeVo;
import com.syuesoft.integratedservices.service.CarInsuranceManageService;
import com.syuesoft.model.CarInsuranceManage;
import com.syuesoft.model.CenterCarinInty;
import com.syuesoft.model.CenterCarinIntyId;
import com.syuesoft.model.InsuranceType;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

public class CarInsuranceManageAction extends BaseAction implements
        ModelDriven<CarInsuranceManagerVo>
{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    private CarInsuranceManageService carInsuranceManageService;

    private CarInsuranceManagerVo carInsuranceManagerVo = new CarInsuranceManagerVo();

    private int page;

    private int rows;

    private String starttime;

    private String endtime;

    public String getStarttime()
    {
        return starttime;
    }

    public void setStarttime(String starttime)
    {
        this.starttime = starttime;
    }

    public String getEndtime()
    {
        return endtime;
    }

    public void setEndtime(String endtime)
    {
        this.endtime = endtime;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public CarInsuranceManageService getService()
    {
        return carInsuranceManageService;
    }

    public void setcarInsuranceManageService(
            CarInsuranceManageService carInsuranceManageService)
    {
        this.carInsuranceManageService = carInsuranceManageService;
    }

    
    public CarInsuranceManagerVo getModel()
    {
        return carInsuranceManagerVo;
    }

    // 保存车辆包单的信息 并 同时保存 险种表的信息
    public String doAdd()
    {
        int a = 0;
        Message msg = new Message();
        try
        {
            CarInsuranceManage cimg = new CarInsuranceManage();
            cimg.setActuallyPaid(carInsuranceManagerVo.getActuallyPaid());
            cimg.setAuditSituation(carInsuranceManagerVo.getAuditSituation());
            cimg.setBillsNumber(carInsuranceManagerVo.getBillsNumber());
            cimg.setBrandModel(carInsuranceManagerVo.getBrandModel());
            cimg.setBusinessDate(carInsuranceManagerVo.getBusinessDate());
            cimg.setBusinessInsurance(carInsuranceManagerVo
                    .getBusinessInsurance());
            cimg.setBusinessPaid(carInsuranceManagerVo.getBusinessPaid());
            cimg.setBusinessUnits(carInsuranceManagerVo.getBusinessUnits());
            cimg.setCarBrand(carInsuranceManagerVo.getCarBrand());
            cimg.setCarColor(carInsuranceManagerVo.getCarColor());
            cimg.setCarPrice(carInsuranceManagerVo.getCarPrice());
            cimg.setCommissionBusiness(carInsuranceManagerVo
                    .getCommissionBusiness());
            cimg.setContact(carInsuranceManagerVo.getContact());
            cimg.setCredirCardTypes(carInsuranceManagerVo.getCredirCardTypes());
            cimg.setCustomerBacksection(carInsuranceManagerVo
                    .getCustomerBacksection());
            cimg.setCustomerPaid(carInsuranceManagerVo.getCustomerPaid());
            cimg.setDiscountRate(carInsuranceManagerVo.getDiscountRate());
            cimg.setEngineNumber(carInsuranceManagerVo.getEngineNumber());
            cimg.setGiftItems(carInsuranceManagerVo.getGiftItems());
            cimg.setIdCard(carInsuranceManagerVo.getIdCard());
            cimg.setInsuranceCompany(carInsuranceManagerVo
                    .getInsuranceCompany());
            cimg.setInsuranceDate(carInsuranceManagerVo.getInsuranceDate());
            cimg.setInsuranceGroup(carInsuranceManagerVo.getInsuranceGroup());
            cimg.setInsuranceNumber(carInsuranceManagerVo.getInsuranceNumber());
            cimg.setInsuredAddress(carInsuranceManagerVo.getInsuredAddress());
            cimg.setInvoiceNumber(carInsuranceManagerVo.getInvoiceNumber());
            cimg.setInvoiceType(carInsuranceManagerVo.getInvoiceType());
            cimg.setJqDate(carInsuranceManagerVo.getJqDate());
            cimg.setJqInsurance(carInsuranceManagerVo.getJqInsurance());
            cimg.setJqInvoiceNumber(carInsuranceManagerVo.getJqInvoiceNumber());
            cimg.setJqPaid(carInsuranceManagerVo.getJqPaid());
            cimg.setJqSingleNumber(carInsuranceManagerVo.getJqSingleNumber());
            cimg.setManager(carInsuranceManagerVo.getManager());
            cimg.setMemo(carInsuranceManagerVo.getMemo());
            cimg.setNextInsuranceCompany(carInsuranceManagerVo
                    .getNextInsuranceCompany());
            cimg.setPremiums(carInsuranceManagerVo.getPremiums());
            cimg.setPresentationValue(carInsuranceManagerVo
                    .getPresentationValue());
            cimg.setProfit(carInsuranceManagerVo.getProfit());
            cimg.setReceiptDate(carInsuranceManagerVo.getReceiptDate());
            cimg.setReceptor(carInsuranceManagerVo.getReceptor());
            cimg.setRecordNumber(carInsuranceManagerVo.getRecordNumber());
            cimg.setRegisterDate(carInsuranceManagerVo.getRegisterDate());
            cimg.setTel(carInsuranceManagerVo.getTel());
            cimg.setTheInsuredPerson(carInsuranceManagerVo
                    .getTheInsuredPerson());
            cimg.setTravelTax(carInsuranceManagerVo.getTravelTax());
            cimg.setTravelTaxNumber(carInsuranceManagerVo.getTravelTaxNumber());
            cimg.setVersionNumber(carInsuranceManagerVo.getVersionNumber());
            cimg.setVinNumber(carInsuranceManagerVo.getVinNumber());
            a = carInsuranceManageService.doAdd(cimg);
            if (a != 0)
            {
                msg.setSuccess(true);
            }
            else
            {
                msg.setSuccess(false);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
        super.writeJson(msg);
        return null;
    }

    /*
     * 向保单表和险种表 的 中间 表里 添加保单的id 以及 险种表的id
     */
    public String doCenterTableAdd()
    {
        int b = 0;
        Message msg = new Message();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        CenterCarinInty ccin = new CenterCarinInty();
        CenterCarinIntyId ccinid = new CenterCarinIntyId();
        InsuranceType intp = new InsuranceType();
        CarInsuranceManage cimg2 = new CarInsuranceManage();
        if (session.getAttribute("cid") != null
                && !session.getAttribute("cid").toString().trim().equals(""))
        {
            cimg2.setCarInsuranceManageId(Integer.parseInt(session
                    .getAttribute("cid").toString()));// 获取最大id
        }
        String str = request.getParameter("ids");
        int i = 0;
        if (str != null)
        {
            i = Integer.parseInt(str);
            intp.setId(i);
        }
        ccinid.setInsuranceType(intp);
        ccinid.setCarInsuranceManage(cimg2);
        ccin.setId(ccinid);
        try
        {
            b = carInsuranceManageService.doCenterTableAdd(ccin);
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

    /*
     * 保单和险种修改后将新的数据添加到 保单和险种的中间表中 （此添加的是id）
     */
    public String doCenterTableEditAdd()
    {
        int b = 0;
        Message msg = new Message();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        CenterCarinInty ccin = new CenterCarinInty();
        CenterCarinIntyId ccinid = new CenterCarinIntyId();
        InsuranceType intp = new InsuranceType();
        CarInsuranceManage cimg2 = new CarInsuranceManage();
        if (session.getAttribute("ID") != null
                && !session.getAttribute("ID").toString().trim().equals(""))
        {
            cimg2.setCarInsuranceManageId(Integer.parseInt(session
                    .getAttribute("ID").toString()));
        }
        String str = request.getParameter("ids");
        int i = 0;
        if (str != null)
        {
            i = Integer.parseInt(str);
            intp.setId(i);
        }
        ccinid.setInsuranceType(intp);
        ccinid.setCarInsuranceManage(cimg2);
        ccin.setId(ccinid);
        try
        {
            b = carInsuranceManageService.doCenterTableAdd(ccin);
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

    /*
     * 查询保单表的信息并设值给VO
     */
    public String doFindAll()
    {
        Message msg = new Message();
        Json json = new Json();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List list = new ArrayList();
        List<CarInsuranceManage> cimglist = null;
        try
        {
            CarInsuranceManage cimg = new CarInsuranceManage();
            cimg.setCarBrand(carInsuranceManagerVo.getCarBrand());
            cimg.setInsuranceCompany(carInsuranceManagerVo
                    .getInsuranceCompany());
            cimg.setInsuranceDate(carInsuranceManagerVo.getInsuranceDate());
            cimg.setInsuranceGroup(carInsuranceManagerVo.getInsuranceGroup());
            cimg.setInsuranceNumber(carInsuranceManagerVo.getInsuranceNumber());
            cimg.setManager(carInsuranceManagerVo.getManager());
            cimg.setReceptor(carInsuranceManagerVo.getReceptor());
            cimg.setTheInsuredPerson(carInsuranceManagerVo
                    .getTheInsuredPerson());
            cimglist = carInsuranceManageService.doFindAll(page, rows,
                    starttime, endtime, cimg);
            for (CarInsuranceManage carInsuranceManage : cimglist)
            {
                CarInsuranceManagerVo carInsuranceManagerVo = new CarInsuranceManagerVo();
                carInsuranceManagerVo
                        .setCarInsuranceManageId(carInsuranceManage
                                .getCarInsuranceManageId()
                                + "");
                carInsuranceManagerVo.setActuallyPaid(carInsuranceManage
                        .getActuallyPaid());
                carInsuranceManagerVo.setAuditSituation(carInsuranceManage
                        .getAuditSituation());
                carInsuranceManagerVo.setBillsNumber(carInsuranceManage
                        .getBillsNumber());
                carInsuranceManagerVo.setBrandModel(carInsuranceManage
                        .getBrandModel());
                carInsuranceManagerVo.setBusinessDate(carInsuranceManage
                        .getBusinessDate());
                carInsuranceManagerVo.setBusinessInsurance(carInsuranceManage
                        .getBusinessInsurance());
                carInsuranceManagerVo.setBusinessPaid(carInsuranceManage
                        .getBusinessPaid());
                carInsuranceManagerVo.setBusinessUnits(carInsuranceManage
                        .getBusinessUnits());
                carInsuranceManagerVo.setCarBrand(carInsuranceManage
                        .getCarBrand());
                carInsuranceManagerVo.setCarColor(carInsuranceManage
                        .getCarColor());
                carInsuranceManagerVo.setCarPrice(carInsuranceManage
                        .getCarPrice());
                carInsuranceManagerVo.setCommissionBusiness(carInsuranceManage
                        .getCommissionBusiness());
                carInsuranceManagerVo.setContact(carInsuranceManage
                        .getContact());
                carInsuranceManagerVo.setCredirCardTypes(carInsuranceManage
                        .getCredirCardTypes());
                carInsuranceManagerVo.setCustomerBacksection(carInsuranceManage
                        .getCustomerBacksection());
                carInsuranceManagerVo.setCustomerPaid(carInsuranceManage
                        .getCustomerPaid());
                carInsuranceManagerVo.setDiscountRate(carInsuranceManage
                        .getDiscountRate());
                carInsuranceManagerVo.setEngineNumber(carInsuranceManage
                        .getEngineNumber());
                carInsuranceManagerVo.setGiftItems(carInsuranceManage
                        .getGiftItems());
                carInsuranceManagerVo.setIdCard(carInsuranceManage.getIdCard());
                carInsuranceManagerVo.setInsuranceCompany(carInsuranceManage
                        .getInsuranceCompany());
                carInsuranceManagerVo.setInsuranceDate(carInsuranceManage
                        .getInsuranceDate());
                carInsuranceManagerVo.setInsuranceGroup(carInsuranceManage
                        .getInsuranceGroup());
                carInsuranceManagerVo.setInsuranceNumber(carInsuranceManage
                        .getInsuranceNumber());
                carInsuranceManagerVo.setInsuredAddress(carInsuranceManage
                        .getInsuredAddress());
                carInsuranceManagerVo.setInvoiceNumber(carInsuranceManage
                        .getInvoiceNumber());
                carInsuranceManagerVo.setInvoiceType(carInsuranceManage
                        .getInvoiceType());
                carInsuranceManagerVo.setJqDate(carInsuranceManage.getJqDate());
                carInsuranceManagerVo.setJqInsurance(carInsuranceManage
                        .getJqInsurance());
                carInsuranceManagerVo.setJqInvoiceNumber(carInsuranceManage
                        .getJqInvoiceNumber());
                carInsuranceManagerVo.setJqPaid(carInsuranceManage.getJqPaid());
                carInsuranceManagerVo.setJqSingleNumber(carInsuranceManage
                        .getJqSingleNumber());
                carInsuranceManagerVo.setManager(carInsuranceManage
                        .getManager());
                carInsuranceManagerVo.setMemo(carInsuranceManage.getMemo());
                carInsuranceManagerVo
                        .setNextInsuranceCompany(carInsuranceManage
                                .getNextInsuranceCompany());
                carInsuranceManagerVo.setPremiums(carInsuranceManage
                        .getPremiums());
                carInsuranceManagerVo.setPresentationValue(carInsuranceManage
                        .getPresentationValue());
                carInsuranceManagerVo.setProfit(carInsuranceManage.getProfit());
                carInsuranceManagerVo.setReceiptDate(carInsuranceManage
                        .getReceiptDate());
                carInsuranceManagerVo.setReceptor(carInsuranceManage
                        .getReceptor());
                carInsuranceManagerVo.setRecordNumber(carInsuranceManage
                        .getRecordNumber());
                carInsuranceManagerVo.setRegisterDate(carInsuranceManage
                        .getRegisterDate());
                carInsuranceManagerVo.setTel(carInsuranceManage.getTel());
                carInsuranceManagerVo.setTheInsuredPerson(carInsuranceManage
                        .getTheInsuredPerson());
                carInsuranceManagerVo.setTravelTax(carInsuranceManage
                        .getTravelTax());
                carInsuranceManagerVo.setTravelTaxNumber(carInsuranceManage
                        .getTravelTaxNumber());
                carInsuranceManagerVo.setVersionNumber(carInsuranceManage
                        .getVersionNumber());
                carInsuranceManagerVo.setVinNumber(carInsuranceManage
                        .getVinNumber());
                list.add(carInsuranceManagerVo);
            }
            json.setTotal(Integer.parseInt(session.getAttribute("size")
                    .toString()));
            json.setRows(list);
            writeJson(json);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * 查询所有的保险信息
     */
    public String doFindByInsuranceType()
    {
        List list = new ArrayList();
        List<InsuranceType> InsuranceTypelist = null;
        try
        {
            InsuranceTypelist = carInsuranceManageService
                    .findAllByInsuranceType();
            Json json = new Json();
            json.setTotal(InsuranceTypelist.size());
            for (InsuranceType insuranceType : InsuranceTypelist)
            {
                InsuranceTypeVo invo = new InsuranceTypeVo();
                invo.setId(insuranceType.getId().toString());
                invo.setIncount(insuranceType.getIncount());
                invo.setInfee(insuranceType.getInfee());
                invo.setInfeelv(insuranceType.getInfeelv());
                invo.setIntype(insuranceType.getIntype());
                list.add(invo);
            }
            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 删除保单信息方法
     */
    public String doDelete()
    {
        HttpSession ssion = ServletActionContext.getRequest().getSession();
        CarInsuranceManage cimg = new CarInsuranceManage();
        CenterCarinInty ccin = new CenterCarinInty();
        CenterCarinIntyId ccid = new CenterCarinIntyId();
        cimg.setCarInsuranceManageId(Integer.parseInt(carInsuranceManagerVo
                .getCarInsuranceManageId()));
        List list = null;
        try
        {
            list = carInsuranceManageService.getID(cimg);
            for (int i = 0; i < list.size(); i++)
            {
                InsuranceType intp = new InsuranceType();
                intp.setId(Integer.parseInt(list.get(i).toString()));
                ccid.setInsuranceType(intp);
                ccid.setCarInsuranceManage(cimg);
                ccin.setId(ccid);
                carInsuranceManageService.doCenterTableDelete(ccin);
            }
            carInsuranceManageService.doDelete(cimg);
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        return null;
    }

    /*
     * (non-Javadoc) 修改车辆保单的方法，将vo里面的相对的信息获取到 设给 保单表 CarInsuranceManage
     * 
     * @see
     * com.syuesoft.integratedcarInsuranceManageServices.dao.CarInsuranceManageDAO
     * #doUpdate(com.syuesoft.model.CarInsuranceManage)
     */
    public String doUpdate()
    {
        Message msg = new Message();
        CarInsuranceManage cimge = new CarInsuranceManage();
        cimge.setCarInsuranceManageId(Integer.parseInt(carInsuranceManagerVo
                .getCarInsuranceManageId().toString()));
        cimge.setActuallyPaid(carInsuranceManagerVo.getActuallyPaid());
        cimge.setAuditSituation(carInsuranceManagerVo.getAuditSituation());
        cimge.setBillsNumber(carInsuranceManagerVo.getBillsNumber());
        cimge.setBrandModel(carInsuranceManagerVo.getBrandModel());
        cimge.setBusinessDate(carInsuranceManagerVo.getBusinessDate());
        cimge
                .setBusinessInsurance(carInsuranceManagerVo
                        .getBusinessInsurance());
        cimge.setBusinessPaid(carInsuranceManagerVo.getBusinessPaid());
        cimge.setBusinessUnits(carInsuranceManagerVo.getBusinessUnits());
        cimge.setCarBrand(carInsuranceManagerVo.getCarBrand());
        cimge.setCarColor(carInsuranceManagerVo.getCarColor());
        cimge.setCarPrice(carInsuranceManagerVo.getCarPrice());
        cimge.setCommissionBusiness(carInsuranceManagerVo
                .getCommissionBusiness());
        cimge.setContact(carInsuranceManagerVo.getContact());
        cimge.setCredirCardTypes(carInsuranceManagerVo.getCredirCardTypes());
        cimge.setCustomerBacksection(carInsuranceManagerVo
                .getCustomerBacksection());
        cimge.setCustomerPaid(carInsuranceManagerVo.getCustomerPaid());
        cimge.setDiscountRate(carInsuranceManagerVo.getDiscountRate());
        cimge.setEngineNumber(carInsuranceManagerVo.getEngineNumber());
        cimge.setGiftItems(carInsuranceManagerVo.getGiftItems());
        cimge.setIdCard(carInsuranceManagerVo.getIdCard());
        cimge.setInsuranceCompany(carInsuranceManagerVo.getInsuranceCompany());
        cimge.setInsuranceDate(carInsuranceManagerVo.getInsuranceDate());
        cimge.setInsuranceGroup(carInsuranceManagerVo.getInsuranceGroup());
        cimge.setInsuranceNumber(carInsuranceManagerVo.getInsuranceNumber());
        cimge.setInsuredAddress(carInsuranceManagerVo.getInsuredAddress());
        cimge.setInvoiceNumber(carInsuranceManagerVo.getInvoiceNumber());
        cimge.setInvoiceType(carInsuranceManagerVo.getInvoiceType());
        cimge.setJqDate(carInsuranceManagerVo.getJqDate());
        cimge.setJqInsurance(carInsuranceManagerVo.getJqInsurance());
        cimge.setJqInvoiceNumber(carInsuranceManagerVo.getJqInvoiceNumber());
        cimge.setJqPaid(carInsuranceManagerVo.getJqPaid());
        cimge.setJqSingleNumber(carInsuranceManagerVo.getJqSingleNumber());
        cimge.setManager(carInsuranceManagerVo.getManager());
        cimge.setMemo(carInsuranceManagerVo.getMemo());
        cimge.setNextInsuranceCompany(carInsuranceManagerVo
                .getNextInsuranceCompany());
        cimge.setPremiums(carInsuranceManagerVo.getPremiums());
        cimge
                .setPresentationValue(carInsuranceManagerVo
                        .getPresentationValue());
        cimge.setProfit(carInsuranceManagerVo.getProfit());
        cimge.setReceiptDate(carInsuranceManagerVo.getReceiptDate());
        cimge.setReceptor(carInsuranceManagerVo.getReceptor());
        cimge.setRecordNumber(carInsuranceManagerVo.getRecordNumber());
        cimge.setRegisterDate(carInsuranceManagerVo.getRegisterDate());
        cimge.setTel(carInsuranceManagerVo.getTel());
        cimge.setTheInsuredPerson(carInsuranceManagerVo.getTheInsuredPerson());
        cimge.setTravelTax(carInsuranceManagerVo.getTravelTax());
        cimge.setTravelTaxNumber(carInsuranceManagerVo.getTravelTaxNumber());
        cimge.setVersionNumber(carInsuranceManagerVo.getVersionNumber());
        cimge.setVinNumber(carInsuranceManagerVo.getVinNumber());
        try
        {
            carInsuranceManageService.doUpdate(cimge);
            this.doCenterTableUpdate();
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

    // 修改时通过保单表的id carInsuranceManagerid 查询险种表InsuranceType的信息
    public String findByCarInsuranceManageid()
    {
        List writelist = new ArrayList();
        List list = null;
        CarInsuranceManage cimg = new CarInsuranceManage();
        HttpServletRequest request = ServletActionContext.getRequest();
        String ID = request.getParameter("CarInsuranceManageId");
        HttpSession session = request.getSession();
        session.setAttribute("ID", ID);
        if (ID != null && !ID.trim().equals(""))
        {
            cimg.setCarInsuranceManageId(Integer.parseInt(ID));
        }
        try
        {
            list = carInsuranceManageService.findByCarInsuranceManageid(cimg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(list);
        return null;
    }

    // 对中间表的车辆保单和险种表的中间表修改的方法
    public String doCenterTableUpdate()
    {
        try
        {
            List list = null;
            HttpSession ssion = ServletActionContext.getRequest().getSession();
            CarInsuranceManage cimg = new CarInsuranceManage();
            String ID = (String) ssion.getAttribute("ID");
            if (ID != null && !ID.trim().equals(""))
            {
                cimg.setCarInsuranceManageId(Integer.parseInt(ID));
            }
            list = carInsuranceManageService.getID(cimg);// 获取险种表的id
            for (int i = 0; i < list.size(); i++)
            {
                InsuranceType intp = new InsuranceType();
                CenterCarinInty ccin = new CenterCarinInty();
                CenterCarinIntyId ccid = new CenterCarinIntyId();
                intp.setId(Integer.parseInt(list.get(i).toString()));
                ccid.setInsuranceType(intp);
                ccid.setCarInsuranceManage(cimg);
                ccin.setId(ccid);
                carInsuranceManageService.doCenterTableDelete(ccin);
            }
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        return null;
    }

    // 只做查询
    public String onlyFind()
    {
        Message msg = new Message();
        Json json = new Json();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List list = new ArrayList();
        List<CarInsuranceManage> cimglist = null;
        try
        {
            cimglist = carInsuranceManageService.onlyFind(page, rows);
            if (cimglist.size() != 0)
            {
                for (CarInsuranceManage carInsuranceManage : cimglist)
                {
                    CarInsuranceManagerVo carInsuranceManagerVo = new CarInsuranceManagerVo();
                    carInsuranceManagerVo
                            .setCarInsuranceManageId(carInsuranceManage
                                    .getCarInsuranceManageId()
                                    + "");
                    carInsuranceManagerVo.setActuallyPaid(carInsuranceManage
                            .getActuallyPaid());
                    carInsuranceManagerVo.setAuditSituation(carInsuranceManage
                            .getAuditSituation());
                    carInsuranceManagerVo.setBillsNumber(carInsuranceManage
                            .getBillsNumber());
                    carInsuranceManagerVo.setBrandModel(carInsuranceManage
                            .getBrandModel());
                    carInsuranceManagerVo.setBusinessDate(carInsuranceManage
                            .getBusinessDate());
                    carInsuranceManagerVo
                            .setBusinessInsurance(carInsuranceManage
                                    .getBusinessInsurance());
                    carInsuranceManagerVo.setBusinessPaid(carInsuranceManage
                            .getBusinessPaid());
                    carInsuranceManagerVo.setBusinessUnits(carInsuranceManage
                            .getBusinessUnits());
                    carInsuranceManagerVo.setCarBrand(carInsuranceManage
                            .getCarBrand());
                    carInsuranceManagerVo.setCarColor(carInsuranceManage
                            .getCarColor());
                    carInsuranceManagerVo.setCarPrice(carInsuranceManage
                            .getCarPrice());
                    carInsuranceManagerVo
                            .setCommissionBusiness(carInsuranceManage
                                    .getCommissionBusiness());
                    carInsuranceManagerVo.setContact(carInsuranceManage
                            .getContact());
                    carInsuranceManagerVo.setCredirCardTypes(carInsuranceManage
                            .getCredirCardTypes());
                    carInsuranceManagerVo
                            .setCustomerBacksection(carInsuranceManage
                                    .getCustomerBacksection());
                    carInsuranceManagerVo.setCustomerPaid(carInsuranceManage
                            .getCustomerPaid());
                    carInsuranceManagerVo.setDiscountRate(carInsuranceManage
                            .getDiscountRate());
                    carInsuranceManagerVo.setEngineNumber(carInsuranceManage
                            .getEngineNumber());
                    carInsuranceManagerVo.setGiftItems(carInsuranceManage
                            .getGiftItems());
                    carInsuranceManagerVo.setIdCard(carInsuranceManage
                            .getIdCard());
                    carInsuranceManagerVo
                            .setInsuranceCompany(carInsuranceManage
                                    .getInsuranceCompany());
                    carInsuranceManagerVo.setInsuranceDate(carInsuranceManage
                            .getInsuranceDate());
                    carInsuranceManagerVo.setInsuranceGroup(carInsuranceManage
                            .getInsuranceGroup());
                    carInsuranceManagerVo.setInsuranceNumber(carInsuranceManage
                            .getInsuranceNumber());
                    carInsuranceManagerVo.setInsuredAddress(carInsuranceManage
                            .getInsuredAddress());
                    carInsuranceManagerVo.setInvoiceNumber(carInsuranceManage
                            .getInvoiceNumber());
                    carInsuranceManagerVo.setInvoiceType(carInsuranceManage
                            .getInvoiceType());
                    carInsuranceManagerVo.setJqDate(carInsuranceManage
                            .getJqDate());
                    carInsuranceManagerVo.setJqInsurance(carInsuranceManage
                            .getJqInsurance());
                    carInsuranceManagerVo.setJqInvoiceNumber(carInsuranceManage
                            .getJqInvoiceNumber());
                    carInsuranceManagerVo.setJqPaid(carInsuranceManage
                            .getJqPaid());
                    carInsuranceManagerVo.setJqSingleNumber(carInsuranceManage
                            .getJqSingleNumber());
                    carInsuranceManagerVo.setManager(carInsuranceManage
                            .getManager());
                    carInsuranceManagerVo.setMemo(carInsuranceManage.getMemo());
                    carInsuranceManagerVo
                            .setNextInsuranceCompany(carInsuranceManage
                                    .getNextInsuranceCompany());
                    carInsuranceManagerVo.setPremiums(carInsuranceManage
                            .getPremiums());
                    carInsuranceManagerVo
                            .setPresentationValue(carInsuranceManage
                                    .getPresentationValue());
                    carInsuranceManagerVo.setProfit(carInsuranceManage
                            .getProfit());
                    carInsuranceManagerVo.setReceiptDate(carInsuranceManage
                            .getReceiptDate());
                    carInsuranceManagerVo.setReceptor(carInsuranceManage
                            .getReceptor());
                    carInsuranceManagerVo.setRecordNumber(carInsuranceManage
                            .getRecordNumber());
                    carInsuranceManagerVo.setRegisterDate(carInsuranceManage
                            .getRegisterDate());
                    carInsuranceManagerVo.setTel(carInsuranceManage.getTel());
                    carInsuranceManagerVo
                            .setTheInsuredPerson(carInsuranceManage
                                    .getTheInsuredPerson());
                    carInsuranceManagerVo.setTravelTax(carInsuranceManage
                            .getTravelTax());
                    carInsuranceManagerVo.setTravelTaxNumber(carInsuranceManage
                            .getTravelTaxNumber());
                    carInsuranceManagerVo.setVersionNumber(carInsuranceManage
                            .getVersionNumber());
                    carInsuranceManagerVo.setVinNumber(carInsuranceManage
                            .getVinNumber());
                    list.add(carInsuranceManagerVo);
                }
                json.setTotal(Integer.parseInt(session.getAttribute("sizes")
                        .toString()));
                json.setRows(list);
                msg.setSuccess(true);
                msg.setMsg(" ");
            }
            else
            {
                msg.setSuccess(false);
                msg.setMsg("暂无相关信息!");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        writeJson(json);
        writeJson(msg);
        return null;
    }

    /**
     * 获取员工名称 用于combox
     */
    public String getBasStuff()
    {
        List list = new ArrayList();
        try
        {
            List rlist = carInsuranceManageService.getBasStuff();
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

    /**
     * 获取部门名称 用于combox
     */
    public String getbasDept()
    {
        List list = new ArrayList();
        try
        {
            List rlist = carInsuranceManageService.getbasDept();
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
