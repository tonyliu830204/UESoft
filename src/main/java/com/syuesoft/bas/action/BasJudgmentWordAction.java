package com.syuesoft.bas.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasJudgmentWordService;
import com.syuesoft.model.BasJudgmentWord;
import com.syuesoft.util.*;

public class BasJudgmentWordAction extends ActionSupport implements
        ModelDriven<BasJudgmentWord>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private BasJudgmentWordService logic = null;
    private int page;
    private int rows;
    private BasJudgmentWord basJudgmentWord = new BasJudgmentWord();

    // 新增方法
    public String doAdd() throws Exception{
        logic.add(basJudgmentWord);
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        Message J = new Message();
        Gson gson = new Gson();
        try{
            J.setSuccess(true);
        }catch(Exception e){
            J.setSuccess(false);
        }
        out.write(gson.toJson(J));
        return null;
    }

    // 删除方法
    public String doDelete() throws Exception{
        try{
            logic.delete(basJudgmentWord);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // 修改方法
    public String doUpdate() throws Exception
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html; charset=utf-8");
        response.setContentType("application/json");
        logic.update(basJudgmentWord);
        Message J = new Message();
        Gson gson = new Gson();
        try{
            J.setSuccess(true);
        }catch(Exception e){
            J.setSuccess(false);
        }
        out.write(gson.toJson(J));
        return null;

    }

    // 查询方法
    public String doFindAll() throws Exception
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html; charset=utf-8");
        response.setContentType("application/json");
        List<BasJudgmentWord> list = logic.findAll(page, rows);
        List<BasJudgmentWord> listtotle = logic.getTotle();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Json json = new Json();
        json.setTotal(listtotle.size());
        json.setRows(list);
        out.write(gson.toJson(json));
        return null;
    }

    // 通过条件 查询
    public String doFindByName() throws Exception{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession sesssion = ServletActionContext.getRequest().getSession();
        response.setContentType("text/html; charset=utf-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        List<BasJudgmentWord> list = logic.findByCondition(page, rows,basJudgmentWord);
        if (list.size() != 0){
            Gson gson = new Gson();
            Json json = new Json();
            int totlesize = (Integer) sesssion.getAttribute("totlesize");
            json.setTotal(totlesize);
            json.setRows(list);
            out.write(gson.toJson(json));
        }
        return null;
    }

    
    public BasJudgmentWord getModel(){
        return basJudgmentWord;
    }

    public int getPage(){
        return page;
    }

    public void setPage(int page){
        this.page = page;
    }

    public int getRows(){
        return rows;
    }

    public void setRows(int rows){
        this.rows = rows;
    }

    public BasJudgmentWordService getLogic(){
        return logic;
    }

    public void setLogic(BasJudgmentWordService logic){
        this.logic = logic;
    }
    
}
