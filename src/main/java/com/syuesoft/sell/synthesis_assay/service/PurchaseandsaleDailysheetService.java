package com.syuesoft.sell.synthesis_assay.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.synthesis_assay.vo.PurchaseandsaleDailysheetVo;


public interface PurchaseandsaleDailysheetService{
	public Datagrid getPurchaseandsaleDailysheet(PurchaseandsaleDailysheetVo purchaseandsaleDailysheetVo) throws Exception;
	public List getDailysheetChild(PurchaseandsaleDailysheetVo purchaseandsaleDailysheetVo) throws Exception;
}
