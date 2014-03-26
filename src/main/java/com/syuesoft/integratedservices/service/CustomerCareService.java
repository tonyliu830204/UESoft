package com.syuesoft.integratedservices.service;

import java.text.ParseException;
import java.util.List;

import javax.xml.crypto.Data;

import com.syuesoft.fbk.vo.CustomerCareVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.FbkCarGroup;
import com.syuesoft.model.FbkTxGroup;
import com.syuesoft.model.FrtRcptItem;
import com.syuesoft.util.Json;

public interface CustomerCareService
{
    public List<CustomerCareVo> getAll(int page, int rows, String sql)
            throws Exception;

    public List<CustomerCareVo> getByCondition(CustomerCareVo customerCareVo)
            throws Exception;// 通过条件查询

    public Datagrid doResualt(CustomerCareVo customerCareVo) throws Exception;//

    public List<FbkTxGroup> getFactoryVisit(CustomerCareVo customerCareVo)
            throws Exception;//

    public void doSave(FbkTxGroup fbkTxGroup, String carId) throws Exception;

    public List getFactoryWxRecord(CustomerCareVo customerCareVo)
            throws Exception;// 获取历史维修记录

    public void doDelete(FbkCarGroup fbkCarGroup) throws Exception;

    // 删除FbkTxGroup
    public void deleteftg(FbkTxGroup ftg) throws Exception;

    public Datagrid getRemenberSearch(CustomerCareVo customerCareVo)
            throws Exception;

    // 获取提醒结果的方法 用于下拉列表
    public List getObject(String sql) throws Exception;

    public Json getBytixing(CustomerCareVo customerCareVo)
            throws Exception;

    // 获取年检年审信息
    public List<CustomerCareVo> getNianjianShen(CustomerCareVo customerCareVo)
            throws Exception;

    // 获取首保提醒信息
    public List<CustomerCareVo> getSbtixing(CustomerCareVo customerCareVo)
            throws Exception;

    // 获取年检年审提醒信息
    public List<CustomerCareVo> getBxjqtixing(CustomerCareVo customerCareVo)
            throws Exception;

    // 获取生日提醒信息
    public Json getSrtixing(CustomerCareVo customerCareVo)
            throws Exception;

    // 流失客户转 准流失 流失分 流失和准流失两个状态 在数据库准流失用F标示
    public void updateToF(CustomerCareVo customerCareVo) throws Exception;

    // 车辆流失 分析 查询历史维修记录 子节点维修项目名称
    public List<FrtRcptItem> getFactoryRepairRecordChild(
            CustomerCareVo customerCareVo) throws Exception;
    // 查询历史回访数据
	public Datagrid getHistoricalVisit(CustomerCareVo customerCareVo)throws Exception;
	 /*
     * 提醒信息保存方法
     */
	public void saveReminder(CustomerCareVo customerCareVo)throws Exception;

	public void updateReminder(CustomerCareVo customerCareVo)throws Exception;

	public void deleteReminder(CustomerCareVo customerCareVo)throws Exception;
}
