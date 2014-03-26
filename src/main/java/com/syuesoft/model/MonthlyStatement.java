package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/** 
 * @ClassName: MonthlyDetail 
 * @Description: TODO(月结汇总) 
 * @author HeXin 
 * @date 2013-10-14 下午04:47:39 
 * @version 1.0 
 */
public class MonthlyStatement  implements java.io.Serializable {

     private Integer msId;                         //月结编号
     private String msNumber;                      //月结单号
     private Long stfId;                           //操作员
     private Date msStarttime;                     //月结开始日期
     private Date msEndtime;                       //月结结束日期
     private Date operDate;                        //月结日期
     private Date msRemindtime;                    //反月结日期
     private Date msNexttime;                      //下次月结日期
     private String msRemark;                      //备注
     private Set monthlyDetails  = new HashSet(0);
     private Integer enterpriseId;

     public Integer getEnterpriseId() {
         return enterpriseId;
     }

     public void setEnterpriseId(Integer enterpriseId) {
         this.enterpriseId = enterpriseId;
     }
    public Integer getMsId()
    {
        return msId;
    }
    public void setMsId(Integer msId)
    {
        this.msId = msId;
    }
    public String getMsNumber()
    {
        return msNumber;
    }
    public void setMsNumber(String msNumber)
    {
        this.msNumber = msNumber;
    }
    public Long getStfId()
    {
        return stfId;
    }
    public void setStfId(Long stfId)
    {
        this.stfId = stfId;
    }
    public Date getMsStarttime()
    {
        return msStarttime;
    }
    public void setMsStarttime(Date msStarttime)
    {
        this.msStarttime = msStarttime;
    }
    public Date getMsEndtime()
    {
        return msEndtime;
    }
    public void setMsEndtime(Date msEndtime)
    {
        this.msEndtime = msEndtime;
    }
    public Date getOperDate()
    {
        return operDate;
    }
    public void setOperDate(Date operDate)
    {
        this.operDate = operDate;
    }
    public Date getMsNexttime()
    {
        return msNexttime;
    }
    public void setMsNexttime(Date msNexttime)
    {
        this.msNexttime = msNexttime;
    }
    public Date getMsRemindtime()
    {
        return msRemindtime;
    }
    public void setMsRemindtime(Date msRemindtime)
    {
        this.msRemindtime = msRemindtime;
    }
    public String getMsRemark()
    {
        return msRemark;
    }
    public void setMsRemark(String msRemark)
    {
        this.msRemark = msRemark;
    }
    public Set getMonthlyDetails()
    {
        return monthlyDetails;
    }
    public void setMonthlyDetails(Set monthlyDetails)
    {
        this.monthlyDetails = monthlyDetails;
    }
}