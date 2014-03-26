package com.syuesoft.model;

public class PrintTemplet  implements java.io.Serializable {
     /**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	 private Integer id;
     private String name;
     private String key;
     private String context;
     private String checked;
     private String systemType;
     private String remark;

     public PrintTemplet() {
     }

    public PrintTemplet(String name, String key, String context, String remark) {
        this.name = name;
        this.key = key;
        this.context = context;
        this.remark = remark;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return this.key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }

    public String getContext() {
        return this.context;
    }
    
    public void setContext(String context) {
        this.context = context;
    }
    
    public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSystemType()
    {
        return systemType;
    }

    public void setSystemType(String systemType)
    {
        this.systemType = systemType;
    }
}