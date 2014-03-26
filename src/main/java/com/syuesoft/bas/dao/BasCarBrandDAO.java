package com.syuesoft.bas.dao;

import java.io.FileInputStream;

import com.syuesoft.base.vo.BasCarBrandVo;
import com.syuesoft.model.BasCarBrand;

/**
 * 基础资料-->车辆性质-->车辆品牌Dao接口
 * @author SuMing
 */
public interface BasCarBrandDAO extends BaseDaoI<BasCarBrand>
{
  public void savaImg(BasCarBrandVo cbVo, FileInputStream in) throws Exception;
}