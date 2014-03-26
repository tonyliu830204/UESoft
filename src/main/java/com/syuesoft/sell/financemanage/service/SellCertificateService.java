package com.syuesoft.sell.financemanage.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.financemanage.vo.SellCertificateVo;
import com.syuesoft.util.ComboBoxJson;


public interface SellCertificateService {

	public Datagrid getPager(SellCertificateVo sellCertificateVo)throws Exception;

	public void addCertificate(SellCertificateVo sellCertificateVo)throws Exception;

	public void deleteCertificate(SellCertificateVo sellCertificateVo)throws Exception;

	public void updateCertificate(SellCertificateVo sellCertificateVo)throws Exception;

	public List<ComboBoxJson> getReceipt(SellCertificateVo sellCertificateVo)throws Exception;

	public void modifyCertificate(SellCertificateVo sellCertificateVo)throws Exception;


}

