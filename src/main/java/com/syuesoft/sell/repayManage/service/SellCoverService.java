package com.syuesoft.sell.repayManage.service;

import java.util.List;

import org.jfree.chart.JFreeChart;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.repayManage.vo.SellCoverVo;
import com.syuesoft.systemmanagement.vo.MaintenanceTrafficAnalysisVo;
import com.syuesoft.util.ComboBoxJson;

public interface SellCoverService {
	/**查询车辆销售信息*/
	public Datagrid getSellList(SellCoverVo sellCoverVo) throws Exception;
	/**查询售后回访*/
	public Datagrid getSellCover(SellCoverVo sellCoverVo) throws Exception;
	/**修改回访信息*/
	public void modifySellCover(SellCoverVo sellCoverVo) throws Exception;
	/**查询回访字段*/
	public List<ComboBoxJson> getInfo(SellCoverVo sellCoverVo)	throws Exception;
	/**查询问卷调查内容*/
	public List getResearch(SellCoverVo sellCoverVo) throws Exception;
	
	/**保存新增问卷调查内容*/
	public void addResearchList(SellCoverVo sellCoverVo) throws Exception;
	public Datagrid getInSellList(SellCoverVo sellCoverVo) throws Exception;
	
	/**查询售后回访信息*/
	public Datagrid getSellCoverMange(SellCoverVo sellCoverVo) throws Exception;
	 // 将一个大的时间段截成每月的小的时间段
    public List getDateInfomation(SellCoverVo sellCoverVo)
            throws Exception;
    
    /**
	 * 获取时间段分析折线图信息 
	 * */
	public JFreeChart getMapbyTime(SellCoverVo sellCoverVo)throws Exception;
	/**查询跟踪项目统计信息*/
	public Datagrid getProjectStatistics(SellCoverVo sellCoverVo) throws Exception;
	/**查询回访及时性分析信息*/
	public Datagrid geTtimelyAnalysis(SellCoverVo sellCoverVo) throws Exception;
	
	//拼接datagrid字符串
	public String getDatagridString(SellCoverVo sellCoverVo)throws Exception;
	
	//获取datagrid的值
	public List getDatagridValue(SellCoverVo sellCoverVo)throws Exception;
	
	/**获取动态时间列
	 * 
	 * @param sellCoverVo
	 * @return
	 * @throws Exception
	 */
	public List getDateColumn(SellCoverVo sellCoverVo)throws Exception;
}
