package com.syuesoft.model;

import java.sql.Timestamp;

/**
 * DataBackup entity. @author MyEclipse Persistence Tools
 */

public class DataBackup implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp dataBackupTime;
	private String dataBackupAddress;
	private String dataBackName;
	private String systemId;                     //系统类型
	private String enterpriseId;                     //系统类型
	
	// Constructors

	/** default constructor */
	public DataBackup() {
	}

	/** full constructor */
	public DataBackup(Timestamp dataBackupTime, String dataBackupAddress,
			String dataBackName) {
		this.dataBackupTime = dataBackupTime;
		this.dataBackupAddress = dataBackupAddress;
		this.dataBackName = dataBackName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDataBackupTime() {
		return this.dataBackupTime;
	}

	public void setDataBackupTime(Timestamp dataBackupTime) {
		this.dataBackupTime = dataBackupTime;
	}

	public String getDataBackupAddress() {
		return this.dataBackupAddress;
	}

	public void setDataBackupAddress(String dataBackupAddress) {
		this.dataBackupAddress = dataBackupAddress;
	}

	public String getDataBackName() {
		return this.dataBackName;
	}

	public void setDataBackName(String dataBackName) {
		this.dataBackName = dataBackName;
	}

    public String getSystemId()
    {
        return systemId;
    }

    public void setSystemId(String systemId)
    {
        this.systemId = systemId;
    }

    public String getEnterpriseId()
    {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }
}