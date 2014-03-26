package com.syuesoft.bas.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.syuesoft.base.vo.BasCarBrandVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
/**
 * 基础资料-->车辆性质：车辆品牌Service接口
 * @author SuMing
 */
public interface BasCarBrandService {
	
	public boolean isExist(BasCarBrandVo cbVo) throws Exception;

	//基础资料-->车辆性质：车辆品牌    添加
    public void add(BasCarBrandVo cbVo)throws Exception;
    
    //基础资料-->车辆性质：车辆品牌    删除
	public void delete(BasCarBrandVo cbVo)throws Exception;
	
	//基础资料-->车辆性质：车辆品牌   修改
	public void update(BasCarBrandVo cbVo)throws Exception;
	
	//基础资料-->车辆性质：车辆品牌    判断是否可以删除
	public List delCondition(BasCarBrandVo cbVo)throws Exception;
	
	//基础资料-->车辆性质：车辆品牌    分页
	public Json getAllByPage(BasCarBrandVo cbVo, HttpServletRequest request) throws Exception;
	
	public Msg uploadImg(BasCarBrandVo cbVo) throws Exception;
}
