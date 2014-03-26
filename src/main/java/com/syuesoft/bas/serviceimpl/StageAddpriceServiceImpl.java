package com.syuesoft.bas.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.StageAddpriceDao;
import com.syuesoft.bas.service.StageAddpriceService;
import com.syuesoft.base.vo.StageAddpriceVo;
import com.syuesoft.model.StageAddprice;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 剃度加价模块ServiceImpl
 * @author SuMing
 */
@Service("stageAddpriceService")
public class StageAddpriceServiceImpl implements StageAddpriceService
{

    @Autowired
    StageAddpriceDao stageAddpriceDao;

    /**
     * 基础资料 配件性质 梯度信息 增加
     */
    
    public void add(StageAddpriceVo stageAddpriceVo) throws Exception
    {
        StageAddprice stageAddprice = new StageAddprice();
        stageAddprice.setStartAmont(Double.parseDouble(stageAddpriceVo
                .getStartAmont()));
        stageAddprice.setEndAmont(Double.parseDouble(stageAddpriceVo.getEndAmont()));
        stageAddprice.setRepairRate(Double.parseDouble(stageAddpriceVo
                .getRepairRate()));
        stageAddprice.setSellRate(Double.parseDouble(stageAddpriceVo
                .getSellRate()));
        stageAddprice.setPointRate(Double.parseDouble(stageAddpriceVo
                .getPointRate()));
        stageAddprice.setSpecialRate(Double.parseDouble(stageAddpriceVo
                .getSpecialRate()));
        stageAddprice.setClaimRate(Double.parseDouble(stageAddpriceVo
                .getClaimRate()));
        stageAddprice.setEnterpriseId(stageAddpriceVo.getEnterpriseId());
        stageAddpriceDao.save(stageAddprice);
    }

    public Msg isOld(StageAddpriceVo stageAddpriceVo)throws Exception{
    	Msg msg=new Msg();
        if(stageAddpriceVo.getStartAmont()!=null&&!stageAddpriceVo.getStartAmont().trim().equals("")
        		&&stageAddpriceVo.getEndAmont()!=null&&!stageAddpriceVo.getEndAmont().trim().equals("")){
        	if(Double.parseDouble(stageAddpriceVo.getStartAmont().trim())>=Double.parseDouble(stageAddpriceVo.getEndAmont().trim())){
        		msg.setMsg("对不起，剃度加价率开始金额必须大于结束金额！");
        	    msg.setSuccess(false);
        	}else{
        		int result=stageAddpriceDao.getSQLCount(" select * from stage_addprice where stage_addprice.enterprise_id="+stageAddpriceVo.getEnterpriseId(), null);
            	if(result==0)
            		msg.setSuccess(true);
            	else{
            		String sql="SELECT * FROM (SELECT MAX(stage_addprice.EndAmont) AS v FROM stage_addprice where stage_addprice.enterprise_id="+stageAddpriceVo.getEnterpriseId()+") AS a WHERE a.v<"+stageAddpriceVo.getStartAmont();
            		int sa=stageAddpriceDao.getSQLCount(sql, null);
            	    if(sa>0)
            	    	msg.setSuccess(true);
            	    else{
            	    	msg.setMsg("对不起，你输入的开始金额必须大于系统之前的最大结束金额，无法保存！");
            	        msg.setSuccess(false);
            	    }
            	}
        	}
        }
    	return msg;
    }
    
    /**
     * 基础资料 配件性质 梯度信息 删除
     */
    
    public void delete(StageAddpriceVo stageAddpriceVo) throws Exception
    {
        StageAddprice stageAddprice = stageAddpriceDao
                .get(" from StageAddprice sa where sa.stageId="
                        + stageAddpriceVo.getStageId());
        if (stageAddprice != null && !stageAddprice.equals(""))
            stageAddpriceDao.delete(stageAddprice);
    }

    /**
     * 基础资料 配件性质 梯度信息 修改
     */
    
    public void update(StageAddpriceVo stageAddpriceVo) throws Exception
    {
        StageAddprice stageAddprice = stageAddpriceDao.get(StageAddprice.class, Short.parseShort(stageAddpriceVo.getStageId()));
        stageAddprice.setStartAmont(Double.parseDouble(stageAddpriceVo.getStartAmont()));
        stageAddprice.setEndAmont(Double.parseDouble(stageAddpriceVo.getEndAmont()));
        stageAddprice.setRepairRate(Double.parseDouble(stageAddpriceVo.getRepairRate()));
        stageAddprice.setSellRate(Double.parseDouble(stageAddpriceVo.getSellRate()));
        stageAddprice.setPointRate(Double.parseDouble(stageAddpriceVo.getPointRate()));
        stageAddprice.setSpecialRate(Double.parseDouble(stageAddpriceVo.getSpecialRate()));
        stageAddprice.setClaimRate(Double.parseDouble(stageAddpriceVo.getClaimRate()));
        stageAddpriceDao.merge(stageAddprice);
    }

    /**
     * 基础资料 配件性质 梯度信息 分页
     */
    
    public Json findAll(StageAddpriceVo stageAddpriceVo)
            throws Exception{
        return stageAddpriceDao.findAll(stageAddpriceVo);
    }

}
