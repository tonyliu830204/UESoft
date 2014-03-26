package com.syuesoft.sell.sellAccount.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.sellAccount.vo.SellAccountVo;
import com.syuesoft.util.Msg;

public interface SellAccountService {
	/**
	 * 查询结算清单
	 * @param sellAccountVo
	 * @return
	 * @throws Exception
	 */
	public Datagrid getSellAccount(SellAccountVo sellAccountVo) throws Exception;
	/**
	 * 回转
	 * @param sellAccountVo
	 * @throws Exception
	 */
	public void updateRedoSum(SellAccountVo sellAccountVo)throws Exception;
	/**
	 * 转收银
	 * @param sellAccountVo
	 * @throws Exception
	 */
	public void updateAccount(SellAccountVo sellAccountVo)throws Exception;
	public XsCarSellInfo getSellCar(int id)throws Exception;
	
	public Msg modifyBalance(SellAccountVo sellAccountVo)throws Exception;
	
}
