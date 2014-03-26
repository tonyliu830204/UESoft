package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCarBodysStatusDAO;
import com.syuesoft.bas.service.BasCarBodysStatusService;
import com.syuesoft.base.vo.BasCarBodysStatusVo;
import com.syuesoft.model.BasCarBodysStatus;

/**
 * 基础资料-->车辆性质：车身状态Service实现类
 * 
 * @author SuMing
 */
@Service("basCarBodysStatusService")
public class BasCarBodysStatusServiceImpl extends BaseLogServiceImpl implements
        BasCarBodysStatusService
{
    @Autowired BasCarBodysStatusDAO basCarBodysStatusDAO;

    public boolean isExist(BasCarBodysStatusVo basCarBodysStatusVo)
            throws Exception{
        String param = "FROM BasCarBodysStatus bcbs WHERE 1=1 ";
        if (basCarBodysStatusVo.getBodyNum() != null&& !"".equals(basCarBodysStatusVo.getBodyNum()))
            param += " and bcbs.bodyNum!=" + basCarBodysStatusVo.getBodyNum();
        if (basCarBodysStatusVo.getBodyId() != null&& !"".equals(basCarBodysStatusVo.getBodyId()))
            param += " and bcbs.bodyId='" + basCarBodysStatusVo.getBodyId()+ "'";
        List<BasCarBodysStatus> list = basCarBodysStatusDAO.find(param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->车辆性质：车身状态 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增车身状态", content = "基础资料-->新增车身状态")
    public void add(BasCarBodysStatusVo basCarBodysStatusVo) throws Exception
    {
        BasCarBodysStatus bcbs = new BasCarBodysStatus();
        if (basCarBodysStatusVo.getBodyId() != null&& !basCarBodysStatusVo.getBodyId().equals(""))
            bcbs.setBodyId(basCarBodysStatusVo.getBodyId());
        if (basCarBodysStatusVo.getBodyName() != null&& !basCarBodysStatusVo.getBodyName().equals(""))
            bcbs.setBodyName(basCarBodysStatusVo.getBodyName());
        if (basCarBodysStatusVo.getBodyRemark() != null&& !basCarBodysStatusVo.getBodyRemark().equals(""))
            bcbs.setBodyRemark(basCarBodysStatusVo.getBodyRemark());
        Serializable dd = basCarBodysStatusDAO.save(bcbs);
        setContent("基础资料-->新增车身状态,车身状态编号:" + dd);
    }

    /**
     * 基础资料-->车辆性质：车身状态 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除车身状态", content = "基础资料-->删除车身状态")
    public void delete(BasCarBodysStatusVo basCarBodysStatusVo)
            throws Exception
    {
        BasCarBodysStatus bbs = basCarBodysStatusDAO.get(BasCarBodysStatus.class, Short.parseShort(basCarBodysStatusVo.getBodyNum()));
        if(bbs!=null){
        	basCarBodysStatusDAO.delete(bbs);
            setContent("基础资料-->删除车身状态,车身状态编号:" + basCarBodysStatusVo.getBodyId());
        }
    }

    /**
     * 基础资料-->车辆性质：车身状态 修改
     */
    @Log(moduleName = "基础资料", opertype = "修改车身状态", content = "基础资料-->修改车身状态")
    public void update(BasCarBodysStatusVo basCarBodysStatusVo)
            throws Exception
    {
        BasCarBodysStatus bbs =basCarBodysStatusDAO.get(BasCarBodysStatus.class, Short.parseShort(basCarBodysStatusVo.getBodyNum()));
        if(bbs!=null){
        	bbs.setBodyId(basCarBodysStatusVo.getBodyId());
            bbs.setBodyName(basCarBodysStatusVo.getBodyName());
            if (basCarBodysStatusVo.getBodyRemark() != null&& !basCarBodysStatusVo.getBodyRemark().equals(""))
                bbs.setBodyRemark(basCarBodysStatusVo.getBodyRemark());
            basCarBodysStatusDAO.merge(bbs);
            setContent("基础资料-->修改车身状态,车身状态编号:" + basCarBodysStatusVo.getBodyId());
        }
    }

    /**
     * 基础资料-->车辆性质：车身状态 全部显示
     */
    public List<BasCarBodysStatusVo> findAll(BasCarBodysStatusVo basCarBodysStatusVo) throws Exception
    {
        List<BasCarBodysStatusVo> list = new ArrayList<BasCarBodysStatusVo>();
        List<BasCarBodysStatus> resultList = basCarBodysStatusDAO.find(" from BasCarBodysStatus where 1=1");
        if (resultList != null && resultList.size() > 0){
            for (int i = 0; i < resultList.size(); i++){
                BasCarBodysStatusVo bcbvo = new BasCarBodysStatusVo();
                bcbvo.setBodyNum(resultList.get(i).getBodyNum()+ "");
                bcbvo.setBodyId(resultList.get(i).getBodyId());
                bcbvo.setBodyName(resultList.get(i).getBodyName());
                bcbvo.setBodyRemark(resultList.get(i).getBodyRemark());
                list.add(bcbvo);
            }
        }
        return list;
    }

    /**
     * 基础资料-->车辆性质：车身状态 分页
     */
    public List<BasCarBodysStatusVo> getAllByPage(
            BasCarBodysStatusVo basCarBodysStatusVo) throws Exception
    {
        List<BasCarBodysStatusVo> list = new ArrayList<BasCarBodysStatusVo>();
        List<BasCarBodysStatus> resultList = basCarBodysStatusDAO.getAllByPage(basCarBodysStatusVo);
        if (resultList != null && resultList.size() > 0){
            for (int i = 0; i < resultList.size(); i++){
                BasCarBodysStatusVo bcbsVo = new BasCarBodysStatusVo();
                bcbsVo.setBodyNum(resultList.get(i).getBodyNum()+ "");
                bcbsVo.setBodyId(resultList.get(i).getBodyId());
                bcbsVo.setBodyName(resultList.get(i).getBodyName());
                bcbsVo.setBodyRemark(resultList.get(i).getBodyRemark());
                list.add(bcbsVo);
            }
        }
        return list;
    }

}
