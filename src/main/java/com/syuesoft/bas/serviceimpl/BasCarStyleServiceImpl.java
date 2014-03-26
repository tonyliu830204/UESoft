package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCarStyleDao;
import com.syuesoft.bas.service.BasCarStyleService;
import com.syuesoft.base.vo.BasCarStyleVo;
import com.syuesoft.model.BasCarStyle;
import com.syuesoft.model.BasCarType;
import com.syuesoft.st.dao.BasCarTypeDAO;
import com.syuesoft.util.ComboBoxJson;

public class BasCarStyleServiceImpl extends BaseLogServiceImpl implements
        BasCarStyleService
{
    BasCarStyleDao basCarStyleDao = null;
    BasCarTypeDAO basCarTypeDAO = null;

    public boolean isExist(BasCarStyleVo basCarStyleVo) throws Exception
    {
        String param = "";
        if (basCarStyleVo.getCarCstlName() != null
                && !"".equals(basCarStyleVo.getCarCstlName().trim()))
            param += " and bcs.carCstlName not in ("
                    + basCarStyleVo.getCarCstlName() + ")";
        BasCarType bct = basCarTypeDAO
                .get(" from BasCarType bct where bct.ctypeId="
                        + basCarStyleVo.getCtypeId());
        if (bct != null && !"".equals(bct))
            param += " and bcs.basCarType.ctypeId="
                    + Short.parseShort(basCarStyleVo.getCtypeId());
        if (basCarStyleVo.getCstlName() != null
                && !"".equals(basCarStyleVo.getCstlName()))
            param += " and bcs.cstlName='" + basCarStyleVo.getCstlName() + "'";
        List<BasCarStyle> list = basCarStyleDao
                .find("FROM BasCarStyle  bcs WHERE bcs.enterpriseId="+basCarStyleVo.getEnterpriseId()+" " + param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean isUsed(BasCarStyleVo basCarStyleVo) throws Exception
    {
        String queryString = " SELECT basCarStyle.carCstlName FROM BasCarStyle basCarStyle,"
                + " FrtCar frtCar WHERE basCarStyle.carCstlName=frtCar.basCarStyle.carCstlName and basCarStyle.enterpriseId="+basCarStyleVo.getEnterpriseId();
        if (basCarStyleVo.getCarCstlName() != null
                && !"".equals(basCarStyleVo.getCarCstlName()))
            queryString += " And " + basCarStyleVo.getCarCstlName()
                    + " IN (SELECT frtCar.basCarStyle.carCstlName FROM frtCar where enterpriseId="+basCarStyleVo.getEnterpriseId()+")";
        List list = basCarStyleDao.find(queryString);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增车辆款式", content = "基础资料-->新增车辆款式")
    public void add(BasCarStyleVo basCarStyleVo) throws Exception
    {
        BasCarType bct = basCarTypeDAO
                .get(" from BasCarType bct where bct.ctypeId="
                        + basCarStyleVo.getCtypeId());
        BasCarStyle basCarStyle = new BasCarStyle();
        if (basCarStyleVo.getCtypePrice() == null
                || basCarStyleVo.getCtypePrice().equals(""))
            basCarStyle.setCtypePrice(0.0);
        else
            basCarStyle.setCtypePrice(Double.parseDouble(basCarStyleVo
                    .getCtypePrice()));
        basCarStyle.setCstlName(basCarStyleVo.getCstlName());
        basCarStyle.setBasCarType(bct);
        basCarStyle.setEnterpriseId(basCarStyleVo.getEnterpriseId());
        Serializable dd = basCarStyleDao.save(basCarStyle);
        setContent("基础资料-->新增车辆款式 ,车辆款式编号:" + dd);
    }

    
    @Log(moduleName = "基础资料", opertype = "删除车辆款式", content = "基础资料-->删除车辆款式")
    public void delete(BasCarStyleVo basCarStyleVo) throws Exception
    {
        BasCarStyle basCarStyle = basCarStyleDao.get(BasCarStyle.class,new Short(basCarStyleVo.getCarCstlName()));
        if(basCarStyle!=null){
        	 basCarStyleDao.delete(basCarStyle);
             setContent("基础资料-->删除车辆款式 ,车辆款式编号:" + basCarStyleVo.getCtypeId());
        }
    }

    
    @Log(moduleName = "基础资料", opertype = "修改车辆款式", content = "基础资料-->修改车辆款式")
    public void update(BasCarStyleVo basCarStyleVo) throws Exception
    {
        BasCarStyle basCarStyle = basCarStyleDao.get(BasCarStyle.class,
                new Short(basCarStyleVo.getCarCstlName()));
        if(basCarStyle!=null){
    	  BasCarType bct = basCarTypeDAO.get(" from BasCarType bct where bct.ctypeId=" + basCarStyleVo.getCtypeId());
          if (basCarStyleVo.getCtypePrice() == null|| basCarStyleVo.getCtypePrice().equals(""))
              basCarStyle.setCtypePrice(0.0);
          else
              basCarStyle.setCtypePrice(Double.parseDouble(basCarStyleVo
                      .getCtypePrice()));
          basCarStyle.setCstlName(basCarStyleVo.getCstlName());
          basCarStyle.setBasCarType(bct);
          basCarStyleDao.update(basCarStyle);
          setContent("基础资料-->修改车辆款式 ,车辆款式编号:" + basCarStyleVo.getCtypeId());
        }
    }

    
    public List<BasCarStyleVo> findAll(BasCarStyleVo bcs) throws Exception
    {
        List<BasCarStyleVo> basCarStyleVoList = new ArrayList<BasCarStyleVo>();
        List<BasCarStyle> basCarStyleList = basCarStyleDao.find("from BasCarStyle  bcb where bcb.enterpriseId="+bcs.getEnterpriseId());
        if (basCarStyleList != null && basCarStyleList.size() > 0)
        {
            for (int i = 0; i < basCarStyleList.size(); i++)
            {
                BasCarStyleVo basCarStyleVo = new BasCarStyleVo();
                basCarStyleVo.setCstlName(basCarStyleList.get(i).getCstlName());
                if (basCarStyleList.get(i).getBasCarType() != null
                        && !"".equals(basCarStyleList.get(i).getBasCarType()))
                {
                    BasCarType bct = basCarTypeDAO
                            .get(" from BasCarType bct where bct.ctypeId="
                                    + basCarStyleList.get(i).getBasCarType()
                                            .getCtypeId());
                    if (bct != null && !"".equals(bct))
                    {
                        basCarStyleVo.setCtypeId(bct.getCtypeId() + "");
                        basCarStyleVo.setCtypeName(bct.getCtypeName());
                    }
                    else
                    {
                        basCarStyleVo.setCtypeId("");
                        basCarStyleVo.setCtypeName("");
                    }
                }
                else
                {
                    basCarStyleVo.setCtypeId("");
                    basCarStyleVo.setCtypeName("");
                }
                basCarStyleVo.setCtypePrice(basCarStyleList.get(i)
                        .getCtypePrice()
                        + "");
                basCarStyleVo.setCarCstlName(basCarStyleList.get(i)
                        .getCarCstlName()
                        + "");
                basCarStyleVoList.add(basCarStyleVo);
            }
        }
        return basCarStyleVoList;
    }

    
    public List<BasCarStyleVo> getAllByPage(BasCarStyleVo basCarStyleVo)
            throws Exception{
        List<BasCarStyleVo> basCarStyleVoList = new ArrayList<BasCarStyleVo>();
        String sql = "from BasCarStyle basCarStyle where 1=1 and basCarStyle.enterpriseId="+basCarStyleVo.getEnterpriseId();
        if (basCarStyleVo.getSort() != null && basCarStyleVo.getOrder() != null)
            if (basCarStyleVo.getSort().equals("ctypeId"))
                sql += " order by basCarStyle.basCarType."
                        + basCarStyleVo.getSort() + " "
                        + basCarStyleVo.getOrder();
            else
                sql += " order by basCarStyle." + basCarStyleVo.getSort() + " "
                        + basCarStyleVo.getOrder();
        List<BasCarStyle> basCarStyleList = basCarStyleDao.find(sql,
                basCarStyleVo.getPage(), basCarStyleVo.getRows());
        if (basCarStyleList != null && basCarStyleList.size() > 0)
        {
            for (int i = 0; i < basCarStyleList.size(); i++)
            {
                BasCarStyleVo bcsVo = new BasCarStyleVo();
                bcsVo.setCstlName(basCarStyleList.get(i).getCstlName());
                if (basCarStyleList.get(i).getBasCarType() != null
                        && !"".equals(basCarStyleList.get(i).getBasCarType()))
                {
                    BasCarType bct = basCarTypeDAO
                            .get(" from BasCarType bct where bct.ctypeId="
                                    + basCarStyleList.get(i).getBasCarType()
                                            .getCtypeId());
                    if (bct != null && !"".equals(bct))
                    {
                        bcsVo.setCtypeId(bct.getCtypeId() + "");
                        bcsVo.setCtypeName(bct.getCtypeName());
                    }
                    else
                    {
                        bcsVo.setCtypeId("");
                        bcsVo.setCtypeName("");
                    }
                }
                else
                {
                    basCarStyleVo.setCtypeId("");
                    basCarStyleVo.setCtypeName("");
                }
                bcsVo.setCtypePrice(basCarStyleList.get(i).getCtypePrice()+ "");
                bcsVo.setCarCstlName(basCarStyleList.get(i).getCarCstlName() + "");
                basCarStyleVoList.add(bcsVo);
            }
        }
        return basCarStyleVoList;
    }

    
    public List<ComboBoxJson> findAllCarType(BasCarStyleVo basCarStyleVo)
            throws Exception{
        List<ComboBoxJson> bcsList = new ArrayList<ComboBoxJson>();
        String param = "";
        if (basCarStyleVo.getQ() != null && !"".equals(basCarStyleVo.getQ()))
            param += " and  bct.ctypeName like '%" + StringEscapeUtils.escapeSql(basCarStyleVo.getQ())
                    + "%'";
        List<BasCarType> bctList = basCarTypeDAO
                .find("from BasCarType bct where bct.enterpriseId="+basCarStyleVo.getEnterpriseId()+" " + param);
        if (bctList != null && bctList.size() > 0)
            for (int i = 0; i < bctList.size(); i++)
            {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(bctList.get(i).getCtypeId().toString());
                json.setText(bctList.get(i).getCtypeName());
                bcsList.add(json);
            }
        return bcsList;
    }

    public BasCarStyleDao getBasCarStyleDao(){
        return basCarStyleDao;
    }

    public void setBasCarStyleDao(BasCarStyleDao basCarStyleDao){
        this.basCarStyleDao = basCarStyleDao;
    }

    public BasCarTypeDAO getBasCarTypeDAO(){
        return basCarTypeDAO;
    }

    public void setBasCarTypeDAO(BasCarTypeDAO basCarTypeDAO){
        this.basCarTypeDAO = basCarTypeDAO;
    }
}
