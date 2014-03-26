package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasStuff entity. @author MyEclipse Persistence Tools
 */

public class BasStuff extends BaseBean{

	// Fields

	private Long  stfId;    //编号
	private String stfYid;   //编码
	private String stfName;  //姓名
	private String stfMark;  //助记码
	private String stfSex;   //性别
	private String  stfYes;  //系统用户
	
	private BasDept basDept;  //部门
	private EnterpriseInfo enterpriseInfo;//公司
	private  Short repgrpId;  //维修班组
	
	private String stfZwgz;   //职位工种
	private String stfPhone;  //电话
	private String stfTel;    //手机
	private String stfZxqk;   //使用还是注销
	
	private Date stfBirthday; //生日
	private String stfGj;    //籍贯
	private Date stfWorkDate;  //工作日期
	
	private String stfByyx;  //毕业院校
	private Date stfBysj;      //  毕业时间
	
	private String stfSxzy;  // 所学专业
	private String stfWhcd; // 文化程度
	private String stfGznx; // 工作年限
	private String stfJsdj;// 技术等级
	
	private String stfYhbyzs;// 已获职业证书
	private String stfSfzhm;// 身份证号码
	private String stfSg;// 身高
	private String stfTz;// 体重
	
	private String stfSl;// 视力
	private String stfXx;// 血型
	
	private String stfHyzk;// 婚姻状况
	private String stfHkszd;// 户口所在地
	private String stfXjzdz;// 现居住地址
	private String stfMz;// 民族
	private String stfZzmm;// 政治面貌
	private String stfWysp;// 外语水平
	private String stfJsjsp;// 计算机水平
	private String stfDzyx;// 电子邮箱
	private String stfTc;// 特长
	
	private String stfAh;// 爱好
	private String stfDbrxm;// 担保人姓名
	private String stfDbrsfzhm;// 担保人身份证号码
	private String stfPoxm;// 配偶姓名
	private String stfPosfzhm;// 配偶身份证号码
	
	private Date stfYjrq;// 押金日期
	private String stfYjje;// 押金金额
	private String stfYjbz;// 押金备注
	private String delTag;	//删除标示:y为已删除,n为未删除
	
	//重要字段
	private Integer stfNo;
	private String repgrpId2;  //销售班组
	
	private Set basUserses = new HashSet(0);
	private Set basStuffJobs = new HashSet(0);
	
	// Constructors

	/** default constructor */
	public BasStuff() {
	}

	/** minimal constructor */
	public BasStuff(String Short, String stfName) {
		this.stfId = stfId;
		this.stfName = stfName;
	}

	

	// Property accessors
	public BasStuff(Long stfId, String stfYid, String stfName, String stfMark,
			String stfSex, String stfYes, BasDept basDept,
			Short repgrpId, String stfZwgz, String stfPhone, String stfTel,
			String  stfZxqk ,Date stfBirthday, String stfGj, Date stfWorkDate, String stfByyx,
			Date stfBysj, String stfSxzy, String stfWhcd, String stfGznx,
			String stfJsdj, String stfYhbyzs, String stfSfzhm, String stfSg,
			String stfTz, String stfSl, String stfXx, String stfHyzk,
			String stfHkszd, String stfXjzdz, String stfMz, String stfZzmm,
			String stfWysp, String stfJsjsp, String stfDzyx, String stfTc,
			String stfAh, String stfDbrxm, String stfDbrsfzhm, String stfPoxm,
			String stfPosfzhm, Date stfYjrq, String stfYjje, String stfYjbz,
			Set basUserses, Set basStuffJobs) {
		super();
		this.stfId = stfId;
		this.stfYid = stfYid;
		this.stfName = stfName;
		this.stfMark = stfMark;
		this.stfSex = stfSex;
		this.stfYes = stfYes;
		this.basDept = basDept;
		this.repgrpId = repgrpId;
		this.stfZwgz = stfZwgz;
		this.stfPhone = stfPhone;
		this.stfTel = stfTel;
		this.stfZxqk=stfZxqk;
		this.stfBirthday = stfBirthday;
		this.stfGj = stfGj;
		this.stfWorkDate = stfWorkDate;
		this.stfByyx = stfByyx;
		this.stfBysj = stfBysj;
		this.stfSxzy = stfSxzy;
		this.stfWhcd = stfWhcd;
		this.stfGznx = stfGznx;
		this.stfJsdj = stfJsdj;
		this.stfYhbyzs = stfYhbyzs;
		this.stfSfzhm = stfSfzhm;
		this.stfSg = stfSg;
		this.stfTz = stfTz;
		this.stfSl = stfSl;
		this.stfXx = stfXx;
		this.stfHyzk = stfHyzk;
		this.stfHkszd = stfHkszd;
		this.stfXjzdz = stfXjzdz;
		this.stfMz = stfMz;
		this.stfZzmm = stfZzmm;
		this.stfWysp = stfWysp;
		this.stfJsjsp = stfJsjsp;
		this.stfDzyx = stfDzyx;
		this.stfTc = stfTc;
		this.stfAh = stfAh;
		this.stfDbrxm = stfDbrxm;
		this.stfDbrsfzhm = stfDbrsfzhm;
		this.stfPoxm = stfPoxm;
		this.stfPosfzhm = stfPosfzhm;
		this.stfYjrq = stfYjrq;
		this.stfYjje = stfYjje;
		this.stfYjbz = stfYjbz;
		this.basUserses = basUserses;
		this.basStuffJobs = basStuffJobs;
	}

	public Long getStfId() {
		return stfId;
	}

	public void setStfId(Long stfId) {
		this.stfId = stfId;
	}

	public String getStfYid() {
		return stfYid;
	}

	public void setStfYid(String stfYid) {
		this.stfYid = stfYid;
	}

	public String getStfName() {
		return stfName;
	}

	public void setStfName(String stfName) {
		this.stfName = stfName;
	}

	public String getStfMark() {
		return stfMark;
	}

	public void setStfMark(String stfMark) {
		this.stfMark = stfMark;
	}

	public String getStfSex() {
		return stfSex;
	}

	public void setStfSex(String stfSex) {
		this.stfSex = stfSex;
	}

	public String getStfYes() {
		return stfYes;
	}

	public void setStfYes(String stfYes) {
		this.stfYes = stfYes;
	}

	public BasDept getBasDept() {
		return basDept;
	}

	public void setBasDept(BasDept basDept) {
		this.basDept = basDept;
	}

	public Short getRepgrpId() {
		return repgrpId;
	}

	public void setRepgrpId(Short repgrpId) {
		this.repgrpId = repgrpId;
	}

	public String getStfZwgz() {
		return stfZwgz;
	}

	public void setStfZwgz(String stfZwgz) {
		this.stfZwgz = stfZwgz;
	}

	public String getStfPhone() {
		return stfPhone;
	}

	public void setStfPhone(String stfPhone) {
		this.stfPhone = stfPhone;
	}

	public String getStfTel() {
		return stfTel;
	}

	public void setStfTel(String stfTel) {
		this.stfTel = stfTel;
	}

	public Date getStfBirthday() {
		return stfBirthday;
	}

	public void setStfBirthday(Date stfBirthday) {
		this.stfBirthday = stfBirthday;
	}

	public String getStfGj() {
		return stfGj;
	}

	public void setStfGj(String stfGj) {
		this.stfGj = stfGj;
	}

	public Date getStfWorkDate() {
		return stfWorkDate;
	}

	public void setStfWorkDate(Date stfWorkDate) {
		this.stfWorkDate = stfWorkDate;
	}

	public String getStfByyx() {
		return stfByyx;
	}

	public void setStfByyx(String stfByyx) {
		this.stfByyx = stfByyx;
	}

	public Date getStfBysj() {
		return stfBysj;
	}

	public void setStfBysj(Date stfBysj) {
		this.stfBysj = stfBysj;
	}

	public String getStfSxzy() {
		return stfSxzy;
	}

	public void setStfSxzy(String stfSxzy) {
		this.stfSxzy = stfSxzy;
	}

	public String getStfWhcd() {
		return stfWhcd;
	}

	public void setStfWhcd(String stfWhcd) {
		this.stfWhcd = stfWhcd;
	}

	public String getStfGznx() {
		return stfGznx;
	}

	public void setStfGznx(String stfGznx) {
		this.stfGznx = stfGznx;
	}

	public String getStfJsdj() {
		return stfJsdj;
	}

	public void setStfJsdj(String stfJsdj) {
		this.stfJsdj = stfJsdj;
	}

	public String getStfYhbyzs() {
		return stfYhbyzs;
	}

	public void setStfYhbyzs(String stfYhbyzs) {
		this.stfYhbyzs = stfYhbyzs;
	}

	public String getStfSfzhm() {
		return stfSfzhm;
	}

	public void setStfSfzhm(String stfSfzhm) {
		this.stfSfzhm = stfSfzhm;
	}

	public String getStfSg() {
		return stfSg;
	}

	public void setStfSg(String stfSg) {
		this.stfSg = stfSg;
	}

	public String getStfTz() {
		return stfTz;
	}

	public void setStfTz(String stfTz) {
		this.stfTz = stfTz;
	}

	public String getStfSl() {
		return stfSl;
	}

	public void setStfSl(String stfSl) {
		this.stfSl = stfSl;
	}

	public String getStfXx() {
		return stfXx;
	}

	public void setStfXx(String stfXx) {
		this.stfXx = stfXx;
	}

	public String getStfHyzk() {
		return stfHyzk;
	}

	public void setStfHyzk(String stfHyzk) {
		this.stfHyzk = stfHyzk;
	}

	public String getStfHkszd() {
		return stfHkszd;
	}

	public void setStfHkszd(String stfHkszd) {
		this.stfHkszd = stfHkszd;
	}

	public String getStfXjzdz() {
		return stfXjzdz;
	}

	public void setStfXjzdz(String stfXjzdz) {
		this.stfXjzdz = stfXjzdz;
	}

	public String getStfMz() {
		return stfMz;
	}

	public void setStfMz(String stfMz) {
		this.stfMz = stfMz;
	}

	public String getStfZzmm() {
		return stfZzmm;
	}

	public void setStfZzmm(String stfZzmm) {
		this.stfZzmm = stfZzmm;
	}

	public String getStfWysp() {
		return stfWysp;
	}

	public void setStfWysp(String stfWysp) {
		this.stfWysp = stfWysp;
	}

	public String getStfJsjsp() {
		return stfJsjsp;
	}

	public void setStfJsjsp(String stfJsjsp) {
		this.stfJsjsp = stfJsjsp;
	}

	public String getStfDzyx() {
		return stfDzyx;
	}

	public void setStfDzyx(String stfDzyx) {
		this.stfDzyx = stfDzyx;
	}

	public String getStfTc() {
		return stfTc;
	}

	public void setStfTc(String stfTc) {
		this.stfTc = stfTc;
	}

	public String getStfAh() {
		return stfAh;
	}

	public void setStfAh(String stfAh) {
		this.stfAh = stfAh;
	}

	public String getStfDbrxm() {
		return stfDbrxm;
	}

	public void setStfDbrxm(String stfDbrxm) {
		this.stfDbrxm = stfDbrxm;
	}

	public String getStfDbrsfzhm() {
		return stfDbrsfzhm;
	}

	public void setStfDbrsfzhm(String stfDbrsfzhm) {
		this.stfDbrsfzhm = stfDbrsfzhm;
	}

	public String getStfPoxm() {
		return stfPoxm;
	}

	public void setStfPoxm(String stfPoxm) {
		this.stfPoxm = stfPoxm;
	}

	public String getStfPosfzhm() {
		return stfPosfzhm;
	}

	public void setStfPosfzhm(String stfPosfzhm) {
		this.stfPosfzhm = stfPosfzhm;
	}

	public Date getStfYjrq() {
		return stfYjrq;
	}

	public void setStfYjrq(Date stfYjrq) {
		this.stfYjrq = stfYjrq;
	}

	public String getStfYjje() {
		return stfYjje;
	}

	public void setStfYjje(String stfYjje) {
		this.stfYjje = stfYjje;
	}

	public String getStfYjbz() {
		return stfYjbz;
	}

	public void setStfYjbz(String stfYjbz) {
		this.stfYjbz = stfYjbz;
	}

	public Set getBasUserses() {
		return basUserses;
	}

	public void setBasUserses(Set basUserses) {
		this.basUserses = basUserses;
	}

	public Set getBasStuffJobs() {
		return basStuffJobs;
	}

	public void setBasStuffJobs(Set basStuffJobs) {
		this.basStuffJobs = basStuffJobs;
	}

	public String getStfZxqk() {
		return stfZxqk;
	}

	public void setStfZxqk(String stfZxqk) {
		this.stfZxqk = stfZxqk;
	}

	public Integer getStfNo() {
		return stfNo;
	}

	public void setStfNo(Integer stfNo) {
		this.stfNo = stfNo;
	}

    public String getRepgrpId2()
    {
        return repgrpId2;
    }

    public void setRepgrpId2(String repgrpId2)
    {
        this.repgrpId2 = repgrpId2;
    }

	public EnterpriseInfo getEnterpriseInfo() {
		return enterpriseInfo;
	}

	public void setEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
		this.enterpriseInfo = enterpriseInfo;
	}

	public String getDelTag() {
		return delTag;
	}

	public void setDelTag(String delTag) {
		this.delTag = delTag;
	}
    
}