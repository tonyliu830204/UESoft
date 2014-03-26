package com.syuesoft.bas.service;

import java.io.Serializable;
import java.util.List;

import com.syuesoft.base.vo.ItemVo;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.base.vo.BasRepairPackageVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.util.Msg;

public interface BasRepairPackageService
{

    public Datagrid datagridParts(BasRepairPackageVo brpVo) throws Exception; // 待选配件datagrid

    public List datagridItem(BasRepairPackageVo brpVo) throws Exception; // 待选项目datagrid

    public Datagrid datagrid(BasRepairPackageVo brpVo) throws Exception; // 查询维修套餐

    public Datagrid getSelectedParts(BasRepairPackageVo brpVo) throws Exception; // 从数据库中查询已选配件并放入session

    public Datagrid getSelectedItem(BasRepairPackageVo brpVo) throws Exception; // 从数据库中查询已选项目并放入session

    public Double[] amount(BasRepairPackageVo brpVo) throws Exception; // 计算费用合计

    public Serializable save(BasRepairPackageVo brpVo) throws Exception; // 保存维修套餐

    public void update(BasRepairPackageVo brpVo) throws Exception; // 更新维修套餐

    public void remove(BasRepairPackageVo brpVo) throws Exception; // 删除维修套餐

}
