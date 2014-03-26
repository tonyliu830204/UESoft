package com.syuesoft.bas.serviceimpl;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCarBrandDAO;
import com.syuesoft.bas.dao.BaseCarTypeDAO;
import com.syuesoft.bas.service.BasCarBrandService;
import javax.servlet.http.HttpServletRequest;
import com.syuesoft.base.vo.BasCarBrandVo;
import com.syuesoft.model.BasCarBrand;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 基础资料-->车辆性质：车辆品牌Service实现类
 * @author SuMing
 */
public class BasCarBrandServiceImpl extends BaseLogServiceImpl implements
        BasCarBrandService
{
    @Autowired BasCarBrandDAO basCarBrandDAO;
    @Autowired BaseCarTypeDAO baseCarTypeDAO;

	public boolean isExist(BasCarBrandVo cbVo) throws Exception{
        String param = "FROM BasCarBrand  bcb WHERE bcb.enterpriseId="+cbVo.getEnterpriseId();
        if (cbVo.getCbrdId() != null && !"".equals(cbVo.getCbrdId().trim()))
            param += " and  bcb.cbrdId !=" + cbVo.getCbrdId();
        if (cbVo.getCbrdName() != null && !"".equals(cbVo.getCbrdName()))
            param += " and bcb.cbrdName='" + cbVo.getCbrdName() + "'";
        List<BasCarBrand> list = this.basCarBrandDAO.find(param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->车辆性质：车辆品牌 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增车辆品牌", content = "基础资料-->新增车辆品牌")
    public void add(BasCarBrandVo cbVo) throws Exception{
        BasCarBrand bcb = new BasCarBrand();
        bcb.setCbrdName(cbVo.getCbrdName());
        if (cbVo.getCbrdPrice() != null && !cbVo.getCbrdPrice().equals(""))
            bcb.setCbrdPrice(Double.parseDouble(cbVo.getCbrdPrice()));
        else
            bcb.setCbrdPrice(0d);
        if(cbVo.getCbrdDistance()!=null&&cbVo.getCbrdDistance().toString().length()>0)
        	bcb.setCbrdDistance(cbVo.getCbrdDistance());
        if(cbVo.getCbrdDays()!=null&&cbVo.getCbrdDays().toString().length()>0)
        	bcb.setCbrdDays(cbVo.getCbrdDays());
        bcb.setEnterpriseId(cbVo.getEnterpriseId());
        Serializable dd = basCarBrandDAO.save(bcb);
        setContent("基础资料-->新增车辆品牌,车辆品牌编号:" + dd);
    }

    /**
     * 基础资料-->车辆性质：车辆品牌 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除车辆品牌", content = "基础资料-->删除车辆品牌")
    public void delete(BasCarBrandVo cbVo) throws Exception{
    	BasCarBrand bcb=basCarBrandDAO.get("from BasCarBrand bcb where bcb.cbrdId="+cbVo.getCbrdId());
    	if(bcb!=null){
    		 basCarBrandDAO.delete(bcb);
    	     setContent("基础资料-->删除车辆品牌,车辆品牌编号:" + cbVo.getCbrdId());
    	}
    }

    /**
     * 基础资料-->车辆性质：车辆品牌 判断是否满足删除条件
     */
    @SuppressWarnings("unchecked")
    public List delCondition(BasCarBrandVo cbVo) throws Exception{
        return baseCarTypeDAO.find(" from BasCarType  bct where bct.basCarBrand.cbrdId="+ cbVo.getCbrdId());
    }

    /**
     * 基础资料-->车辆性质：车辆品牌 修改
     */
    @Log(moduleName = "基础资料", opertype = "修改车辆品牌", content = "基础资料-->修改车辆品牌")
    public void update(BasCarBrandVo cbVo) throws Exception{
        BasCarBrand bcb = basCarBrandDAO.get(BasCarBrand.class,Short.parseShort(cbVo.getCbrdId()));
        if(bcb!=null){
        	 bcb.setCbrdName(cbVo.getCbrdName());
             if (cbVo.getCbrdPrice() != null && !cbVo.getCbrdPrice().equals(""))
                 bcb.setCbrdPrice(Double.parseDouble(cbVo.getCbrdPrice()));
             else
                 bcb.setCbrdPrice(0d);
             if(cbVo.getCbrdDistance()!=null&&cbVo.getCbrdDistance().toString().length()>0)
             	bcb.setCbrdDistance(cbVo.getCbrdDistance());
             if(cbVo.getCbrdDays()!=null&&cbVo.getCbrdDays().toString().length()>0)
             	bcb.setCbrdDays(cbVo.getCbrdDays());
             basCarBrandDAO.merge(bcb);
             setContent("基础资料-->修改车辆品牌,车辆品牌编号:" + cbVo.getCbrdId());
        }
       
    }

    /**
     * 基础资料-->车辆性质：车辆品牌 分页
     */
    public Json getAllByPage(BasCarBrandVo cbVo, HttpServletRequest request)
            throws Exception{
        Json json = new Json();
        List<BasCarBrandVo> list = new ArrayList<BasCarBrandVo>();
        String hql = "from BasCarBrand  bcb where bcb.enterpriseId="+cbVo.getEnterpriseId();
        if (cbVo.getSort() != null || cbVo.getOrder() != null){
            hql += " order by bcb." + cbVo.getSort() + " " + cbVo.getOrder();
        }
        List<BasCarBrand> carbrandAll = basCarBrandDAO.find(hql, cbVo.getPage(), cbVo.getRows());
        int size = basCarBrandDAO.getCount(hql);
        if (carbrandAll != null && carbrandAll.size() > 0){
            for (int i = 0; i < carbrandAll.size(); i++){
                BasCarBrandVo cbv = new BasCarBrandVo();
                cbv.setCbrdId(carbrandAll.get(i).getCbrdId() + "");
                cbv.setCbrdName(carbrandAll.get(i).getCbrdName());
                cbv.setCbrdPrice(carbrandAll.get(i).getCbrdPrice() + "");
                cbv.setCbrdLogo(request.getContextPath()+"/servlet/PICServlet?cbrdId="+carbrandAll.get(i).getCbrdId()+"&b="+Math.random());
                cbv.setCbrdDistance(carbrandAll.get(i).getCbrdDistance());
                cbv.setCbrdDays(carbrandAll.get(i).getCbrdDays());
                list.add(cbv);
            }
        }
        json.setTotal(size);
        json.setRows(list);
        return json;
    }

    public Msg uploadImg(BasCarBrandVo cbVo) throws Exception{
        Msg msg = new Msg();
        StringBuffer buf = new StringBuffer("from BasCarBrand where cbrdId = "+cbVo.getCbrdId());
        BasCarBrand carBrand = basCarBrandDAO.get(buf.toString());
        if(carBrand != null){
            if(cbVo.getFile()!=null && cbVo.getFile().length()>0){
                FileInputStream in=new FileInputStream(cbVo.getFile());
                basCarBrandDAO.savaImg(cbVo, in);
                msg.setSuccess(true);
                msg.setMsg("更改图标成功！");
            }else{
                msg.setSuccess(false);
                msg.setMsg("图标文件路径不能为空！");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("图标文件路径不能为空！");   
        }
        return msg;
    }

    public BasCarBrandDAO getBasCarBrandDAO(){
        return basCarBrandDAO;
    }

    public void setBasCarBrandDAO(BasCarBrandDAO basCarBrandDAO){
        this.basCarBrandDAO = basCarBrandDAO;
    }
  
    public BaseCarTypeDAO getBaseCarTypeDAO(){
        return baseCarTypeDAO;
    }

    public void setBaseCarTypeDAO(BaseCarTypeDAO baseCarTypeDAO){
        this.baseCarTypeDAO = baseCarTypeDAO;
    }
    
    
}