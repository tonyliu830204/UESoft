package com.syuesoft.sell.sellParamSet.vo;

import com.syuesoft.base.vo.BaseBeanVo;
import com.syuesoft.model.BaseBean;

public class SellParamSetVo extends BaseBeanVo{
	    private String ciId;

	    private String ciLable;

	    private String ciName;

	    private String ciValue;

	    private String ciRemark;

	    private String ciInputControl;

	    private String ciType;

	    private String[] ciCiIds;

	    private String[] ciNames;

	    private String[] ciValues;

	    private String[] ciCheckCiIds;

	    private String[] ciCheckNames;

	    private String[] ciCheckValues;
	    private Integer enterprise_id ;
	 	
		public Integer getEnterprise_id() {
			return enterprise_id;
		}
		public void setEnterprise_id(Integer enterpriseId) {
			enterprise_id = enterpriseId;
		}
	    public String getCiId()
	    {
	        return ciId;
	    }

	    public void setCiId(String ciId)
	    {
	        this.ciId = ciId;
	    }

	    public String getCiLable()
	    {
	        return ciLable;
	    }

	    public void setCiLable(String ciLable)
	    {
	        this.ciLable = ciLable;
	    }

	    public String getCiName()
	    {
	        return ciName;
	    }

	    public void setCiName(String ciName)
	    {
	        this.ciName = ciName;
	    }

	    public String getCiValue()
	    {
	        return ciValue;
	    }

	    public void setCiValue(String ciValue)
	    {
	        this.ciValue = ciValue;
	    }

	    public String getCiRemark()
	    {
	        return ciRemark;
	    }

	    public void setCiRemark(String ciRemark)
	    {
	        this.ciRemark = ciRemark;
	    }

	    public String getCiInputControl()
	    {
	        return ciInputControl;
	    }

	    public void setCiInputControl(String ciInputControl)
	    {
	        this.ciInputControl = ciInputControl;
	    }

	    public String getCiType()
	    {
	        return ciType;
	    }

	    public void setCiType(String ciType)
	    {
	        this.ciType = ciType;
	    }

	    public String[] getCiCiIds()
	    {
	        return ciCiIds;
	    }

	    public void setCiCiIds(String[] ciCiIds)
	    {
	        this.ciCiIds = ciCiIds;
	    }

	    public String[] getCiNames()
	    {
	        return ciNames;
	    }

	    public void setCiNames(String[] ciNames)
	    {
	        this.ciNames = ciNames;
	    }

	    public String[] getCiValues()
	    {
	        return ciValues;
	    }

	    public void setCiValues(String[] ciValues)
	    {
	        this.ciValues = ciValues;
	    }

	    public String[] getCiCheckCiIds()
	    {
	        return ciCheckCiIds;
	    }

	    public void setCiCheckCiIds(String[] ciCheckCiIds)
	    {
	        this.ciCheckCiIds = ciCheckCiIds;
	    }

	    public String[] getCiCheckNames()
	    {
	        return ciCheckNames;
	    }

	    public void setCiCheckNames(String[] ciCheckNames)
	    {
	        this.ciCheckNames = ciCheckNames;
	    }

	    public String[] getCiCheckValues()
	    {
	        return ciCheckValues;
	    }

	    public void setCiCheckValues(String[] ciCheckValues)
	    {
	        this.ciCheckValues = ciCheckValues;
	    }

}
