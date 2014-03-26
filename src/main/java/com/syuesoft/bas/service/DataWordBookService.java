package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BarChildrenAndParentVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtCarVo;
import com.syuesoft.frt.vo.FrtCustomVo;
import com.syuesoft.model.FrtCar;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Msg;

public interface DataWordBookService
{

    public Datagrid datagridP(BarChildrenAndParentVo bcapVo) throws Exception; // 查询父级数据

    public Datagrid datagridC(BarChildrenAndParentVo bcapVo) throws Exception; // 查询子级数据

    public void saveP(BarChildrenAndParentVo bcapVo) throws Exception; // 保存父级数据

    public void saveC(BarChildrenAndParentVo bcapVo) throws Exception; // 保存子级数据

    public void removeP(BarChildrenAndParentVo bcapVo) throws Exception; // 删除父级数据

    public void removeC(BarChildrenAndParentVo bcapVo) throws Exception; // 删除子级数据

    public void editP(BarChildrenAndParentVo bcapVo) throws Exception; // 更新父级数据

    public void editC(BarChildrenAndParentVo bcapVo) throws Exception; // 更新子级数据

    public Boolean isExists(BarChildrenAndParentVo bcapVo) throws Exception;// 判断是否有子级数据

    public Boolean isExistsKeyP(BarChildrenAndParentVo bcapVo) throws Exception;// 判断父级Key是否重复

    public Boolean isExistsKeyC(BarChildrenAndParentVo bcapVo) throws Exception;// 判断子级Key是否重复
}
