package com.syuesoft.model;

/** 
 * @ClassName: BasVipSendinfomations 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author HeXin 
 * @date 2013-12-13 下午02:57:23 
 * @version 1.0 
 */
public class BasVipSendinfomations extends BaseBean 
{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private BasVipSendinfomation basVipSendinfomation;
    private FrtCar frtCar;
    private String smsState;
    private String smsMobile ;
    
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public BasVipSendinfomation getBasVipSendinfomation()
    {
        return basVipSendinfomation;
    }
    public void setBasVipSendinfomation(BasVipSendinfomation basVipSendinfomation)
    {
        this.basVipSendinfomation = basVipSendinfomation;
    }
    public FrtCar getFrtCar()
    {
        return frtCar;
    }
    public void setFrtCar(FrtCar frtCar)
    {
        this.frtCar = frtCar;
    }
    public String getSmsState()
    {
        return smsState;
    }
    public void setSmsState(String smsState)
    {
        this.smsState = smsState;
    }
    public String getSmsMobile()
    {
        return smsMobile;
    }
    public void setSmsMobile(String smsMobile)
    {
        this.smsMobile = smsMobile;
    }
}