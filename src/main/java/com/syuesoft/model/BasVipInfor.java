package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasVipInfor entity. @author MyEclipse Persistence Tools
 */

public class BasVipInfor extends BaseBean{
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private String vipId;                                        //会员编号
	private String carLicense;                                   //车牌照
    private String carVin;                                       //车架号
	private BasVipGruop basVipGruop;                             //会员分组
	private BasVipLevel basVipLevel;                             //会员等级
	private String vipNumber;                                    //会员卡号
	private Double vipIntegral;                                  //可用积分
	private Double vipTotalIntegral;                             //总积分
	private Double vipBalance;                                   //卡余额
	private String vipHobby;                                     //喜好
	private Date vipBirthday;                                    //会员生日
	private String vipTel;                                       //会员电话
	private Date joinTime;                                       //入会日期
	private Date endTime;                                        //挂失日期 
	private String vipStatus;                                    //会员状态
	private String vipName;                                      //会员名称
	private String vipPassword;                                  //会员密码
	private String memo;                                         //备注
	private Double vipGbfee;                                     //会员卡工本费
	private String makeUser;                                     //经办人
	private Integer enterprise_id;                               //入会办理企业编号
	private Integer enterprise_id2;                              //集团企业编号
	private Set<BasVipRecharge> basVipRecharges = new HashSet<BasVipRecharge>(0);
	private Set<BasVipGiveIntegral> basVipGiveIntegrals = new HashSet<BasVipGiveIntegral>(0);
	private Set<BasVipUpInfo> basInfoUps = new HashSet<BasVipUpInfo>(0);
	private Set<BasVipRelationship15> basVipRelationship15s = new HashSet<BasVipRelationship15>(0);
	private Set<BasVipInforNote> basVipInforNotes = new HashSet<BasVipInforNote>(0);
	private Set<BasVipStock> basVipStocks = new HashSet<BasVipStock>(0);
	private Set<VipcardMealR> vipcardMealRs = new HashSet<VipcardMealR>(0);
	private Set<BasVipInforDefray> basVipInforDefrays = new HashSet<BasVipInforDefray>(0);
	private Set<BasVipInforAccount> basVipInforAccounts = new HashSet<BasVipInforAccount>(0);
	
	public BasVipInfor() {
	}

    public String getVipId()
    {
        return vipId;
    }

    public void setVipId(String vipId)
    {
        this.vipId = vipId;
    }

    public String getCarLicense()
    {
        return carLicense;
    }

    public void setCarLicense(String carLicense)
    {
        this.carLicense = carLicense;
    }

    public String getCarVin()
    {
        return carVin;
    }

    public void setCarVin(String carVin)
    {
        this.carVin = carVin;
    }

    public BasVipGruop getBasVipGruop()
    {
        return basVipGruop;
    }

    public void setBasVipGruop(BasVipGruop basVipGruop)
    {
        this.basVipGruop = basVipGruop;
    }

    public BasVipLevel getBasVipLevel()
    {
        return basVipLevel;
    }

    public void setBasVipLevel(BasVipLevel basVipLevel)
    {
        this.basVipLevel = basVipLevel;
    }

    public String getVipNumber()
    {
        return vipNumber;
    }

    public void setVipNumber(String vipNumber)
    {
        this.vipNumber = vipNumber;
    }

    public Double getVipIntegral()
    {
        return vipIntegral;
    }

    public void setVipIntegral(Double vipIntegral)
    {
        this.vipIntegral = vipIntegral;
    }

    public Double getVipTotalIntegral()
    {
        return vipTotalIntegral;
    }

    public void setVipTotalIntegral(Double vipTotalIntegral)
    {
        this.vipTotalIntegral = vipTotalIntegral;
    }

    public Double getVipBalance()
    {
        return vipBalance;
    }

    public void setVipBalance(Double vipBalance)
    {
        this.vipBalance = vipBalance;
    }

    public String getVipHobby()
    {
        return vipHobby;
    }

    public void setVipHobby(String vipHobby)
    {
        this.vipHobby = vipHobby;
    }

    public Date getVipBirthday()
    {
        return vipBirthday;
    }

    public void setVipBirthday(Date vipBirthday)
    {
        this.vipBirthday = vipBirthday;
    }

    public String getVipTel()
    {
        return vipTel;
    }

    public void setVipTel(String vipTel)
    {
        this.vipTel = vipTel;
    }

    public Date getJoinTime()
    {
        return joinTime;
    }

    public void setJoinTime(Date joinTime)
    {
        this.joinTime = joinTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public String getVipStatus()
    {
        return vipStatus;
    }

    public void setVipStatus(String vipStatus)
    {
        this.vipStatus = vipStatus;
    }

    public String getVipName()
    {
        return vipName;
    }

    public void setVipName(String vipName)
    {
        this.vipName = vipName;
    }

    public String getVipPassword()
    {
        return vipPassword;
    }

    public void setVipPassword(String vipPassword)
    {
        this.vipPassword = vipPassword;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public Double getVipGbfee()
    {
        return vipGbfee;
    }

    public void setVipGbfee(Double vipGbfee)
    {
        this.vipGbfee = vipGbfee;
    }

    public String getMakeUser()
    {
        return makeUser;
    }

    public void setMakeUser(String makeUser)
    {
        this.makeUser = makeUser;
    }

    public Integer getEnterprise_id()
    {
        return enterprise_id;
    }

    public void setEnterprise_id(Integer enterpriseId)
    {
        enterprise_id = enterpriseId;
    }

    public Integer getEnterprise_id2()
    {
        return enterprise_id2;
    }

    public void setEnterprise_id2(Integer enterpriseId2)
    {
        enterprise_id2 = enterpriseId2;
    }

    public Set<BasVipRecharge> getBasVipRecharges()
    {
        return basVipRecharges;
    }

    public void setBasVipRecharges(Set<BasVipRecharge> basVipRecharges)
    {
        this.basVipRecharges = basVipRecharges;
    }

    public Set<BasVipGiveIntegral> getBasVipGiveIntegrals()
    {
        return basVipGiveIntegrals;
    }

    public void setBasVipGiveIntegrals(Set<BasVipGiveIntegral> basVipGiveIntegrals)
    {
        this.basVipGiveIntegrals = basVipGiveIntegrals;
    }

    public Set<BasVipUpInfo> getBasInfoUps()
    {
        return basInfoUps;
    }

    public void setBasInfoUps(Set<BasVipUpInfo> basInfoUps)
    {
        this.basInfoUps = basInfoUps;
    }

    public Set<BasVipRelationship15> getBasVipRelationship15s()
    {
        return basVipRelationship15s;
    }

    public void setBasVipRelationship15s(
            Set<BasVipRelationship15> basVipRelationship15s)
    {
        this.basVipRelationship15s = basVipRelationship15s;
    }

    public Set<BasVipInforNote> getBasVipInforNotes()
    {
        return basVipInforNotes;
    }

    public void setBasVipInforNotes(Set<BasVipInforNote> basVipInforNotes)
    {
        this.basVipInforNotes = basVipInforNotes;
    }

    public Set<BasVipStock> getBasVipStocks()
    {
        return basVipStocks;
    }

    public void setBasVipStocks(Set<BasVipStock> basVipStocks)
    {
        this.basVipStocks = basVipStocks;
    }

    public Set<VipcardMealR> getVipcardMealRs()
    {
        return vipcardMealRs;
    }

    public void setVipcardMealRs(Set<VipcardMealR> vipcardMealRs)
    {
        this.vipcardMealRs = vipcardMealRs;
    }

    public Set<BasVipInforDefray> getBasVipInforDefrays()
    {
        return basVipInforDefrays;
    }

    public void setBasVipInforDefrays(Set<BasVipInforDefray> basVipInforDefrays)
    {
        this.basVipInforDefrays = basVipInforDefrays;
    }

    public Set<BasVipInforAccount> getBasVipInforAccounts()
    {
        return basVipInforAccounts;
    }

    public void setBasVipInforAccounts(Set<BasVipInforAccount> basVipInforAccounts)
    {
        this.basVipInforAccounts = basVipInforAccounts;
    }
}