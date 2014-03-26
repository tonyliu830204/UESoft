package com.syuesoft.integratedservices.service;

import java.util.List;

import org.jfree.chart.JFreeChart;

import com.syuesoft.fbk.vo.VTrackRecordVo;
import com.syuesoft.model.VTrackRecord;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface VTrackRecordService
{
    public String getAll(VTrackRecordVo vTrackRecordVo) throws Exception;

    public List getScoreAndName(String carLicense) throws Exception;

    // 获取下拉框的项目名称
    public List getReturnSeverName() throws Exception;

    // 获取不固定列需要显示的的项目名称
    public List getName(VTrackRecordVo vTrackRecordVo) throws Exception;

    // 获取动态显示的数据
    public List getData(String carLicense) throws Exception;

    // 获取车品牌 (用于combox 下拉框)
    public List getCarbrand() throws Exception;

    // 获取员工名称 (用于combox 下拉框)
    public List getStuff() throws Exception;

    // 获取部门名称(用于combox 下拉框)
    public List getPartment() throws Exception;

    // 将一个大的时间段截成每月的小的时间段
    public List getDateInfomation(VTrackRecordVo vTrackRecordVo)
            throws Exception;

    //
    public List getJfreeChartInfomation(VTrackRecordVo vTrackRecordVo)
            throws Exception;

	public List getSatisfaction(VTrackRecordVo vTrackRecordVo) throws Exception;

	public JFreeChart findServiceTimeAnalyzeSnapMap(VTrackRecordVo vTrackRecordVo)throws Exception;
}
