package com.syuesoft.bas.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCustomerComplaintsService;
import com.syuesoft.model.BasCustomerComplaints;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

public class BasCustomerComplaintsAction extends BaseAction implements ModelDriven<BasCustomerComplaints>{
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private BasCustomerComplaintsService logic =null;
	private int page;
	private int rows;
	private String order;
	private String sort;
	
	//新增方法
	public String doAdd(){
		Message J = new Message();
		try {
			basCustomerComplaints.setEnterpriseId(getUserEnterpriseId());
			boolean bool = logic.add(basCustomerComplaints);
			if(bool){
				J.setSuccess(false);
				J.setMsg("对不起，您输入的客户投诉分类已存在，请重新输入！");
			}else{
				J.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(J);
		return null;
	}
	
	//删除方法
	public String doDelete(){
		Message J = new Message();
		try {
			logic.delete(basCustomerComplaints);
			J.setSuccess(true);
		} catch (Exception e) {
			J.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(J);
		return null;
	
	}
	//修改方法
	public String doUpdate(){
		Message J = new Message();
		try {
			basCustomerComplaints.setEnterpriseId(getUserEnterpriseId());
			boolean bool = logic.update(basCustomerComplaints);
			if(bool){
				J.setSuccess(false);
				J.setMsg("对不起，您输入的客户投诉分类已存在，请重新输入！");
			}else{
				J.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(J);
		return null;
	
	}
	//查询方法
	public void doFindAll(){
		try {
			super.writeJson( logic.findAll(page, rows,sort,order,getNowEnterpriseId()));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//通过条件 查询
	public String doFindByName()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession sesssion =  ServletActionContext.getRequest().getSession();
		response.setContentType("text/html; charset=utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		basCustomerComplaints.setEnterpriseId(getNowEnterpriseId());
		List<BasCustomerComplaints> list = logic.findByCondition(page, rows,basCustomerComplaints);
		if(list.size()!=0){
			Gson gson = new Gson();
			Json json = new Json();
				int totlesize = (Integer)sesssion.getAttribute("totlesize");
				json.setTotal(totlesize);
				json.setRows(list);
				out.write(gson.toJson(json));
		}
		return null;
	}

	
	public BasCustomerComplaints getModel() {
		return basCustomerComplaints;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

    public void setSort(String sort) {
		this.sort = sort;}
	
	private BasCustomerComplaints basCustomerComplaints = new BasCustomerComplaints();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public BasCustomerComplaintsService getLogic() {
		return logic;
	}

	public void setLogic(BasCustomerComplaintsService logic) {
		this.logic = logic;
	}
}
