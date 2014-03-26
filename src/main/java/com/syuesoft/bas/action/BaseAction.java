package com.syuesoft.bas.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.bas.service.BasWorkhourSortService;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.ARCHIVESSE;
import com.syuesoft.contstants.Contstants.EVALUATESE;
import com.syuesoft.contstants.Contstants.INDEMNITYS;
import com.syuesoft.contstants.Contstants.OTHERPARAMETER;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.contstants.Contstants.SCENESET;
import com.syuesoft.contstants.Contstants.STANDERWORKHOUR;
import com.syuesoft.contstants.Contstants.STGCARPARA;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasWorkhourSort;
import com.syuesoft.util.Json;

@ParentPackage(value = "basePackage")
@Action("baseAction")
public class BaseAction extends ActionSupport implements ServletRequestAware,
        ServletResponseAware
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(BaseAction.class);
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String key;
    private String check;
    private String lince;
    @Autowired BasCompanyInformationSetService basCompanyInformationSetService;
    @Autowired BaseService baseService;
    @Autowired 
    private BasWorkhourSortService basWorkhourSortService;
    public Integer getd(){
    	int enterpriseId=-1;
		if(this.getSession().getAttribute(Contstants.ENTERPRISEID).toString()!=null&&
				this.getSession().getAttribute(Contstants.ENTERPRISEID).toString().length()>0)
			enterpriseId=Integer.parseInt(this.getSession().getAttribute(Contstants.ENTERPRISEID).toString());
		return enterpriseId;
    }
    /**
     * 获取当前企业序号(无则返回-1)
     * */
    public Integer getNowEnterpriseId(){
		return getUserEnterpriseId();
    }
    /**
     * 获取当前用户所属企业序号(无则返回-1)
     * */
    public Integer getUserEnterpriseId(){
    	int enterpriseId=-1;
    	if(this.getUsers().getBasStuff()!=null&&this.getUsers().getBasStuff().getEnterpriseInfo()!=null){
    		enterpriseId=this.getUsers().getBasStuff().getEnterpriseInfo().getEnterpriseId();
    	}
    	return enterpriseId;
    }
    /**
     * 获取当前企业名称(无则返回null)
     * */
    public String getNowEnterpriseName(){
		if(this.getSession().getAttribute(Contstants.ENTERPRISENAME).toString()!=null&&
				this.getSession().getAttribute(Contstants.ENTERPRISENAME).toString().length()>0)
			return this.getSession().getAttribute(Contstants.ENTERPRISENAME).toString();
		return null;
    }

    
    public void findBaseListData(){
        try{
            this.writeJson(baseService.baseListData(key));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    /*默认资料显示天数*/
    public void loadCiValue(){
    	try{
            this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    
    /*默认结算查询时间段*/
    public void loadPreClrTime(){
    	try{
            this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANACCOUNTSECT ,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    /*默认收款查询时间段*/
    public void loadPercharge(){
    	try{
            this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANCETIMESECT ,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    /*默认结算收款四舍五入保留小数位*/
    public void loadNumberbit(){
    	try{
            this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.JOINCARACC,Contstants.JOINCARACC.NUMBERBIT  ,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    
    /*输入维修里程不能小于上次信息加载*/
    public void loadRepairDistance(){
    	try{
            this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.STGCARPARA,STGCARPARA.DISTANCELOSSORI,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    /*车辆保养提醒天数*/
    public void loadMaintence(){
    	try{
            this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.ARCHIVESSE,ARCHIVESSE.MAINTAINDAY ,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    /*车辆首保提醒天数*/
    public void loadFirst(){
    	try{
            this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.ARCHIVESSE,ARCHIVESSE.FIRSTWARNDAY ,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    /*车辆保险提醒天数*/
    public void loadInnsure(){
    	try{
            this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.ARCHIVESSE,ARCHIVESSE.INSURANCEWARNDAY ,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    
    /*输入维修里程不能小于上次信息加载*/
    public void loadWorkHour(){
    	try{
    		String ciValue=basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.STGCARPARA,STGCARPARA.SERPSTANDARDTIME ,getNowEnterpriseId()).getCiValue();
    		if(ciValue!=null){
    			BasWorkhourSort bws=basWorkhourSortService.findById(STANDERWORKHOUR.STANDERWORKHOURKEY);
        		if(bws!=null&&!bws.equals("")){
        			this.writeJson(bws);
        		}
    		}
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    
    /*索赔工时加载*/
    public void loadFinClaimantWorkHour(){
    	try{
        	this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.INDEMNITYS,INDEMNITYS.CLAIMTIMEPRICE ,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    /*预约进店时间*/
    public void loadRevationTime(){
    	try{
        	this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.EVALUATESE,EVALUATESE.RESEVATIONTIME ,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    /*工时单价*/
    public void loadTaskPrice(){
    	try{
        	this.writeJson(baseService.getSinglePrice(lince,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    
    /*加载维修项目是否能修改信息*/
    public void loadIsUpdataRepairItemInfo(){
    	try{
        	this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.SCENESET,SCENESET.UPDATEREPAIRPRO,getNowEnterpriseId()));
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
    /*加载现场管理自动显示在修车辆信息*/
    public void loadShowrepairvehivle(){
    	try{
        	this.writeJson(basCompanyInformationSetService.getBasCompanyInformationSet(PARAMETER_SET.SCENESET,SCENESET.SHOWREPAIRVEHIVLE,getNowEnterpriseId()).getCiValue());
        }
        catch(Exception e){
            logger.error("获取下拉框数据失败！", e);
        }
    }
   
    protected void sendJson(Object json)
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("contentType", "text/html");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try
        {
            out = response.getWriter();
            out.print(json);
        }
        catch(IOException e)
        {
            logger.error("异步传输异常", e);
        }
    }

    /**
     * 将对象转换为json格式并返回
     * @param obj
     */
    public void writeJson(Object obj)
    {
        @SuppressWarnings("unused")
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        HttpServletResponse response = (HttpServletResponse) ServletActionContext
                .getResponse();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.println(JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } finally
        {
            out.flush();
            out.close();
        }
    }

    /**
     * 通过google gson解析器转换json
     * 
     * @param obj
     */
    public void writeJsonByGson(Object obj)
    {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        HttpServletResponse response = (HttpServletResponse) ServletActionContext
                .getResponse();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.println(gson.toJson(obj));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } finally
        {
            out.flush();
            out.close();
        }
    }

    /**
     * 将对象转换为json格式并返回
     * 
     * @param obj
     * @param flag
     *            true标识返回日期类型为yyyy-MM-dd hh:mm:ss
     */
    public void writeJson(Object obj, boolean flag)
    {
        String dateFormat = "";
        if (flag)
        {
            dateFormat = "yyyy-MM-dd HH:mm:ss";
        }
        else
        {
            dateFormat = "MM-dd";
        }
        HttpServletResponse response = (HttpServletResponse) ServletActionContext
                .getResponse();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.println(JSON.toJSONStringWithDateFormat(obj, dateFormat));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } finally
        {
            out.flush();
            out.close();
        }
    }

    /**
     * 库存上下限颜色改变
     */
    public String changeColor(String str, String color)
    {
        return "<font style='font-size: 16px;font-weight: bold;' color='"
                + color + "'>" + str + "</font>";
    }

    /**
     * 精度精确到小数点后两位
     */
    public double doubleFormat(double value)
    {
        BigDecimal b = new BigDecimal(value);
        double changeValue = b.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        return changeValue;
    }

    /**
     * 字符转码
     */
    public String transCoding(String str) throws Exception
    {
        return new String(str.getBytes("ISO-8859-1"), "UTF-8");
    }

    // 转换成百分数字符串
    public static String convert(String orignal)
    {
        int index = orignal.indexOf('.');
        if (index < orignal.length() - 2)
        {
            return orignal.substring(0, index)
                    + orignal.substring(index + 1, index + 3) + '.'
                    + orignal.substring(index + 3) + "%";
        }
        else
        {
            return orignal.substring(0, index) + orignal.substring(index + 1)
                    + "%";
        }
    }

    // 去掉小数后面的零值，如：0.02500
    public String zeroSuppression(String str)
    {
        String noDotInteger = Integer.parseInt(str.replace(".", "")) + "";// 2500
        int lengthOfZero = noDotInteger.length() - noDotInteger.indexOf("0");// 2
        str = str.substring(0, str.length() - lengthOfZero);// 0.025
        return str;
    }

    // 将小数变整数
    public String integerChange(String str)
    {
        BigDecimal bd = new BigDecimal(str);
        bd = bd.multiply(new BigDecimal(100));
        boolean isno = false;// 例如：当值为不是0.20，而是0.05时，用来将小数转换为整数5，而不是50
        String value = zeroSuppression(bd + "").substring(0, str.length() - 2);
        if (value.contains("."))
        {
            value = value.replace(".", "");
            isno = true;
        }
        if (value.length() == 1)
        {
            if (!isno)
                value += "0";
        }
        return value; // 输出2.500
    }

    @SuppressWarnings("unchecked")
	public Json listConvertJson(List list)
    {
        Json json = new Json();
        json.setRows(list);
        json.setTotal(list.size());
        return json;
    }

    
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    public BasUsers getUsers(){
        BasUsers obj = (BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
        return obj != null ? obj : null;
    }

    public void setChecked()
    {
        getSession().setAttribute(Contstants.CHECKED, getCheck());
    }

    public void getChecked()
    {
        this.writeJson(getSession().getAttribute(Contstants.CHECKED));
    }

    public String getCheck()
    {
        return check;
    }

    public void setCheck(String check)
    {
        this.check = check;
    }
    
    public HttpSession getSession()
    {
        return this.getRequest().getSession();
    }

    public HttpServletRequest getRequest()
    {
        return request;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public HttpServletResponse getResponse()
    {
        return response;
    }

    public void setResponse(HttpServletResponse response)
    {
        this.response = response;
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	public String getLince() {
		return lince;
	}
	public void setLince(String lince) {
		this.lince = lince;
	}

	
}