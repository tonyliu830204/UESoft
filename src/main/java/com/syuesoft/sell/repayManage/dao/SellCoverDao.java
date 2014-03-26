package com.syuesoft.sell.repayManage.dao;


import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fbk.vo.VTrackRecordVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.repayManage.vo.SellCoverVo;

public interface SellCoverDao extends BaseDaoI<BaseBean>{
	/**查询售后回访*/
	public Datagrid getSellCover(SellCoverVo sellCoverVo) throws Exception;
	/**查询问卷调查内容*/
	public List getResearch(SellCoverVo sellCoverVo) throws Exception;
	/**保险模块：查询车辆销售信息*/
	public Datagrid getInSellList(SellCoverVo sellCoverVo) throws Exception;
	/**查询售后回访信息*/
	public Datagrid getSellCoverMange(SellCoverVo sellCoverVo) throws Exception;
	/**查询跟踪项目统计信息*/
	public Datagrid getProjectStatistics(SellCoverVo sellCoverVo) throws Exception;
	/**查询回访及时性分析信息*/
	public Datagrid geTtimelyAnalysis(SellCoverVo sellCoverVo) throws Exception;

}
