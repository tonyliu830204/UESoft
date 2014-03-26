package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasPartsBrandDAO;
import com.syuesoft.bas.service.BasPartsBrandService;
import com.syuesoft.model.BasPartsBrand;
import com.syuesoft.st.vo.BasPartsBrandVo;

/**
 * 基础资料-->配件性质：配件品牌Service实现类
 * 
 * @author SuMing
 */
@Service("basPartsBrandService")
public class BasPartsBrandServiceImpl extends BaseLogServiceImpl implements
        BasPartsBrandService
{
	@Autowired
    BasPartsBrandDAO basPartsBrandDAO;

    public boolean isExist(BasPartsBrandVo baspartsbrandvo) throws Exception
    {
        String param = "";
        if (baspartsbrandvo.getPbrdId() != null
                && !"".equals(baspartsbrandvo.getPbrdId()))
            param += " and bpb.pbrdId!=" + baspartsbrandvo.getPbrdId();
        if (baspartsbrandvo.getPbrdName() != null
                && !"".equals(baspartsbrandvo.getPbrdName()))
            param += " and bpb.pbrdName='" + baspartsbrandvo.getPbrdName()
                    + "'";
        List<BasPartsBrand> list = basPartsBrandDAO.find("FROM BasPartsBrand bpb WHERE bpb.enterpriseId="+baspartsbrandvo.getEnterpriseId()+""+ param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->配件性质：配件品牌 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增配件品牌", content = "基础资料-->新增配件品牌")
    public void add(BasPartsBrandVo baspartsbrandvo) throws Exception
    {
        BasPartsBrand bpb = new BasPartsBrand();
        bpb.setPbrdName(baspartsbrandvo.getPbrdName());
        if (baspartsbrandvo.getRemark() != null
                && !baspartsbrandvo.getRemark().equals(""))
            bpb.setRemark(baspartsbrandvo.getRemark());
        else
            bpb.setRemark("");
        bpb.setEnterpriseId(baspartsbrandvo.getEnterpriseId());
        Serializable bb = basPartsBrandDAO.save(bpb);
        setContent("基础资料-->新增配件品牌,配件品牌编号:" + bb);
    }

    /**
     * 基础资料-->配件性质：配件品牌 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除配件品牌", content = "基础资料-->删除配件品牌")
    public void delete(BasPartsBrandVo baspartsbrandvo) throws Exception
    {
        BasPartsBrand bpb = new BasPartsBrand();
        bpb.setPbrdId(baspartsbrandvo.getPbrdId());
        basPartsBrandDAO.delete(bpb);
        setContent("基础资料-->删除配件品牌,配件品牌编号:" + baspartsbrandvo.getPbrdId());
    }

    /**
     * 基础资料-->配件性质：配件品牌 修改
     */
    @Log(moduleName = "基础资料", opertype = "更新配件品牌", content = "基础资料-->更新配件品牌")
    public void update(BasPartsBrandVo baspartsbrandvo) throws Exception
    {
        BasPartsBrand bpb =basPartsBrandDAO.get(BasPartsBrand.class, baspartsbrandvo.getPbrdId());
        bpb.setPbrdName(baspartsbrandvo.getPbrdName());
        if (baspartsbrandvo.getRemark() != null
                && !baspartsbrandvo.getRemark().equals(""))
            bpb.setRemark(baspartsbrandvo.getRemark());
        else
            bpb.setRemark("");
        basPartsBrandDAO.merge(bpb);
        setContent("基础资料-->更新配件品牌,配件品牌编号:" + baspartsbrandvo.getPbrdId());
    }

    /**
     *基础资料-->配件性质：配件品牌 全部显示
     */
    public List<BasPartsBrandVo> findAll(BasPartsBrandVo bpbvo) throws Exception
    {
        List<BasPartsBrandVo> list = new ArrayList<BasPartsBrandVo>();
        List<BasPartsBrand> resultList = basPartsBrandDAO.findAll(bpbvo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasPartsBrandVo baspartsbrandvo = new BasPartsBrandVo();
                baspartsbrandvo.setPbrdId(resultList.get(i).getPbrdId());
                baspartsbrandvo.setPbrdName(resultList.get(i).getPbrdName());
                baspartsbrandvo.setRemark(resultList.get(i).getRemark());
                list.add(baspartsbrandvo);
            }
        }
        return list;
    }

    /**
     * 基础资料-->配件性质：配件品牌 分页
     */
    public List<BasPartsBrandVo> getAllByPage(BasPartsBrandVo baspartsbrandvo)
            throws Exception
    {
        List<BasPartsBrandVo> list = new ArrayList<BasPartsBrandVo>();
        List<BasPartsBrand> resultList = basPartsBrandDAO.getAllByPage(baspartsbrandvo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasPartsBrandVo bpbvo = new BasPartsBrandVo();
                bpbvo.setPbrdId(resultList.get(i).getPbrdId());
                bpbvo.setPbrdName(resultList.get(i).getPbrdName());
                bpbvo.setRemark(resultList.get(i).getRemark());
                list.add(bpbvo);
            }
        }
        return list;
    }

}
