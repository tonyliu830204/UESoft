package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCarPhoneTrackerResultDAO;
import com.syuesoft.bas.service.BasCarPhoneTrackerResultService;
import com.syuesoft.base.vo.BasCarPhonetrackerresultVo;
import com.syuesoft.model.BasCarPhonetrackerresult;

/**
 * 基础资料-->车辆性质：电话跟踪结果Service实现类
 * 
 * @author SuMing
 */
public class BasCarPhoneTrackerResultServiceImpl extends BaseLogServiceImpl
        implements BasCarPhoneTrackerResultService
{

    BasCarPhoneTrackerResultDAO dao = null;

    public boolean isExist(BasCarPhonetrackerresultVo basCarPhonetrackerresultVo)
            throws Exception
    {
        String param = "FROM BasCarPhonetrackerresult  bcp WHERE bcp.enterpriseId="+basCarPhonetrackerresultVo.getEnterpriseId();
        if (basCarPhonetrackerresultVo.getCarPhonetrackerresultId() != null
                && !"".equals(basCarPhonetrackerresultVo.getCarPhonetrackerresultId()))
            param += " and bcp.carPhonetrackerresultId!="
                    + basCarPhonetrackerresultVo.getCarPhonetrackerresultId();
        if (basCarPhonetrackerresultVo.getCarPhonetrackerresultName() != null
                && !"".equals(basCarPhonetrackerresultVo
                        .getCarPhonetrackerresultName()))
            param += " and bcp.carPhonetrackerresultName='"
                    + basCarPhonetrackerresultVo.getCarPhonetrackerresultName()
                    + "'";
        List<BasCarPhonetrackerresult> list = this.dao.find(param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->车辆性质：电话跟踪结果 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增电话跟踪结果", content = "基础资料-->新增电话跟踪结果")
    public void add(BasCarPhonetrackerresultVo basCarPhonetrackerresultVo)
            throws Exception
    {
        BasCarPhonetrackerresult bcptr = new BasCarPhonetrackerresult();
        bcptr.setCarPhonetrackerresultName(basCarPhonetrackerresultVo
                .getCarPhonetrackerresultName());
        if (basCarPhonetrackerresultVo.getCarPhonetrackerresultRemark() != null
                && !basCarPhonetrackerresultVo.getCarPhonetrackerresultRemark()
                        .equals(""))
            bcptr.setCarPhonetrackerresultRemark(basCarPhonetrackerresultVo
                    .getCarPhonetrackerresultRemark());
        else
            bcptr.setCarPhonetrackerresultRemark("");
        bcptr.setEnterpriseId(basCarPhonetrackerresultVo.getEnterpriseId());
        Serializable dd = dao.save(bcptr);
        setContent("基础资料-->新增电话跟踪结果 ,电话跟踪结果 编号:" + dd);
    }

    /**
     * 基础资料-->车辆性质：电话跟踪结果 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除电话跟踪结果", content = "基础资料-->删除电话跟踪结果")
    public void delete(BasCarPhonetrackerresultVo basCarPhonetrackerresultVo)
            throws Exception
    {
        BasCarPhonetrackerresult bcptr =dao.get(BasCarPhonetrackerresult.class, basCarPhonetrackerresultVo.getCarPhonetrackerresultId());
        if(bcptr!=null){
        	dao.delete(bcptr);
            setContent("基础资料-->删除电话跟踪结果 ,电话跟踪结果 编号:"
                    + basCarPhonetrackerresultVo.getCarPhonetrackerresultId());
        }
    }

    /**
     * 基础资料-->车辆性质：电话跟踪结果 修改
     */
    @Log(moduleName = "基础资料", opertype = "修改电话跟踪结果", content = "基础资料-->修改电话跟踪结果")
    public void update(BasCarPhonetrackerresultVo bcp)
            throws Exception
    {
        BasCarPhonetrackerresult bcptr =dao.get(BasCarPhonetrackerresult.class, bcp .getCarPhonetrackerresultId());
        if(bcptr!=null){
        	 bcptr.setCarPhonetrackerresultName(bcp.getCarPhonetrackerresultName());
             if (bcp.getCarPhonetrackerresultRemark() != null&& !bcp.getCarPhonetrackerresultRemark().equals(""))
                 bcptr.setCarPhonetrackerresultRemark(bcp
                         .getCarPhonetrackerresultRemark());
             dao.merge(bcptr);
             setContent("基础资料-->修改电话跟踪结果 ,电话跟踪结果 编号:"+ bcp.getCarPhonetrackerresultId());
        }
    }

    /**
     * 基础资料-->车辆性质：电话跟踪结果 全部显示
     */
    public List<BasCarPhonetrackerresultVo> findAll(BasCarPhonetrackerresultVo bcpvo) throws Exception
    {
        List<BasCarPhonetrackerresultVo> list = new ArrayList<BasCarPhonetrackerresultVo>();
        List<BasCarPhonetrackerresult> resultList = dao.findAll(bcpvo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasCarPhonetrackerresultVo basCarPhonetrackerresultVo = new BasCarPhonetrackerresultVo();
                basCarPhonetrackerresultVo
                        .setCarPhonetrackerresultId(resultList.get(i)
                                .getCarPhonetrackerresultId());
                basCarPhonetrackerresultVo
                        .setCarPhonetrackerresultName(resultList.get(i)
                                .getCarPhonetrackerresultName());
                basCarPhonetrackerresultVo
                        .setCarPhonetrackerresultRemark(resultList.get(i)
                                .getCarPhonetrackerresultRemark());
                list.add(basCarPhonetrackerresultVo);
            }
        }
        return list;
    }

    /**
     * 基础资料-->车辆性质：电话跟踪结果 分页
     */
    public List<BasCarPhonetrackerresultVo> getAllByPage(
            BasCarPhonetrackerresultVo basCarPhonetrackerresultVo)
            throws Exception
    {
        List<BasCarPhonetrackerresultVo> list = new ArrayList<BasCarPhonetrackerresultVo>();
        List<BasCarPhonetrackerresult> resultList = dao.getAllByPage(basCarPhonetrackerresultVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasCarPhonetrackerresultVo bcpvo = new BasCarPhonetrackerresultVo();
                bcpvo.setCarPhonetrackerresultId(resultList.get(i)
                        .getCarPhonetrackerresultId());
                bcpvo.setCarPhonetrackerresultName(resultList.get(i)
                        .getCarPhonetrackerresultName());
                bcpvo.setCarPhonetrackerresultRemark(resultList.get(i)
                        .getCarPhonetrackerresultRemark());
                list.add(bcpvo);
            }
        }
        return list;
    }

    public BasCarPhoneTrackerResultDAO getDao()
    {
        return dao;
    }

    public void setDao(BasCarPhoneTrackerResultDAO dao)
    {
        this.dao = dao;
    }
}
