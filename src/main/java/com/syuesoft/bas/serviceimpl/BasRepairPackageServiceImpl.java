package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasRepairPackageDao;
import com.syuesoft.bas.dao.BasRepairPackagePartsDao;
import com.syuesoft.bas.service.BasRepairPackageService;
import com.syuesoft.base.vo.BasRepairPackageVo;
import com.syuesoft.base.vo.ItemVo;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.model.BasRepairPackage;
import com.syuesoft.model.BasRepairPackageItem;
import com.syuesoft.model.BasRepairPackageParts;
import com.syuesoft.util.MyBeanUtils;

/**
 * 维修套餐service
 * @author Liujian
 */
@Service("basRepairPackageService")
public class BasRepairPackageServiceImpl extends BaseLogServiceImpl implements
        BasRepairPackageService
{
    @Autowired private BasRepairPackageDao basRepairPackageDao;
    @Autowired private BasRepairPackagePartsDao basRepairPackagePartsDao;
    
    /**
     * 待选配件
     */
    
    public Datagrid datagridParts(BasRepairPackageVo brpVo)
    {
        Datagrid dg = new Datagrid();
        String param = "";
        if (brpVo.getPartsId() != null && !"".equals(brpVo.getPartsId()))
            param += " and fp.partsId like '%" + StringEscapeUtils.escapeSql(brpVo.getPartsId().trim()) + "%'";
        if (brpVo.getPartsId2() != null && !"".equals(brpVo.getPartsId2()))
            param += " and fp.partsId2 like '%" + StringEscapeUtils.escapeSql(brpVo.getPartsId2().trim()) + "%'";
        if (brpVo.getPartsName() != null
                && !"".equals(brpVo.getPartsName().trim()))
        {
            param += " and (fp.partsName like '%"
                    + StringEscapeUtils.escapeSql(brpVo.getPartsName().trim().toUpperCase())
                    + "%' or fp.partsSimpleId like '%"
                    + StringEscapeUtils.escapeSql(brpVo.getPartsName().trim().toUpperCase()) + "%')";
        }
        if (brpVo.getPbrdId() != null && !"".equals(brpVo.getPbrdId()))
            param += " and fp.basPartsType.basPartsBrand.pbrdId = "
                    + brpVo.getPbrdId();
        if (brpVo.getPtypeName() != null
                && !"".equals(brpVo.getPtypeName().trim()))
            param += " and fp.basPartsType.ptypeName like '%"
                    + StringEscapeUtils.escapeSql(brpVo.getPtypeName().trim()) + "%'";
        if (brpVo.getStoreId() != null && !"".equals(brpVo.getStoreId()))
            param += " and bsh.storeId = " + brpVo.getStoreId();
        param+=" and fp.enterpriseId="+brpVo.getEnterpriseId();
        if (brpVo.getSort() != null && !"".equals(brpVo.getSort())
                && brpVo.getOrder() != null && !"".equals(brpVo.getOrder()))
        {
            String sort = "fp";
            if ("pbrdId".equals(brpVo.getSort()))
                sort = "bpb";
            if ("ptypeId".equals(brpVo.getSort()))
                sort = "bpt";
            if ("stateId".equals(brpVo.getSort()))
                sort = "bps";
            if ("punitId".equals(brpVo.getSort()))
                sort = "bpu";
            if ("partsNowCount".equals(brpVo.getSort())
                    || "partsRepairPrice".equals(brpVo.getSort()))
                sort = "pcp";
            if ("storeId".equals(brpVo.getSort()))
                sort = "bsh";
            param += " order by " + sort + "." + brpVo.getSort() + " "
                    + brpVo.getOrder();
        }
        List<FrtPartsVo> list = this.basRepairPackageDao
                .findAllSelectionParts(param);
        dg.setRows(list);
        dg.setTotal(list.size());
        return dg;
    }

    /**
     * 待选项目
     */
    
    public List datagridItem(BasRepairPackageVo brpVo) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        if (brpVo.getRepitemId() != null && !"".equals(brpVo.getRepitemId()))
            sb.append(" and fri.repitem_Id like '%" + StringEscapeUtils.escapeSql(brpVo.getRepitemId().trim())
                    + "%'");
        if (brpVo.getRepitemName() != null
                && !"".equals(brpVo.getRepitemName().trim()))
            sb.append(" and fri.repitem_Name like '%" + StringEscapeUtils.escapeSql(brpVo.getRepitemName().trim())
                    + "%'");
        if (brpVo.getRepitemSeries() != null
                && brpVo.getRepitemSeries().toString().length() > 0)
            sb.append(" AND fri.repitem_Series=" + brpVo.getRepitemSeries());
        if (brpVo.getFitCar() != null && !"".equals(brpVo.getFitCar().trim()))
            sb.append(" and fri.fit_Car like '%" + StringEscapeUtils.escapeSql(brpVo.getFitCar().trim())
                    + "%'");
        return  this.basRepairPackageDao.findAllSelectionItem(sb.toString(),brpVo);
    }

    /**
     * 维修套餐datagrid
     */
    
    public Datagrid datagrid(BasRepairPackageVo brpVo) throws Exception
    {
        Datagrid dg = new Datagrid();
        String hql = "from BasRepairPackage brp where brp.enterpriseId="+brpVo.getEnterpriseId();;
        hql = addWhere(brpVo, hql);
        List<BasRepairPackageVo> rows = new ArrayList<BasRepairPackageVo>();
        List<BasRepairPackage> list = basRepairPackageDao.find(hql, brpVo
                .getPage(), brpVo.getRows());
        int total = basRepairPackageDao.getCount(hql);
        BasRepairPackageVo brpv = null;
        if (list != null && list.size() > 0)
        {
            for (BasRepairPackage rp : list)
            {
                brpv = new BasRepairPackageVo();
                MyBeanUtils.getInstance().copyBeans(rp, brpv);
                if (rp.getApplicableBrands() == null)
                    brpv.setApplicableBrandsName(null);
                brpv.setApplicableBrandsName(brpv.getApplicableBrands());
                rows.add(brpv);
            }
            dg.setTotal(total);
            dg.setRows(rows);
        }else{
            dg.setTotal(0);
            dg.setRows(new ArrayList<BasRepairPackageVo>());
        }
        return dg;
    }

    /**
     * 增加where条件
     */
    private String addWhere(BasRepairPackageVo brpVo, String hql)
    {
        StringBuffer sb = new StringBuffer();
        if (brpVo.getRpName() != null && !"".equals(brpVo.getRpName()))
            sb.append(" and brp.rpName like '%"
                    + StringEscapeUtils.escapeSql(brpVo.getRpName().trim())
                    + "%'");
        if (brpVo.getApplicableBrands() != null
                && !"".equals(brpVo.getApplicableBrands()))
        {
            brpVo.setApplicableBrands(brpVo.getApplicableBrands().replaceAll(
                    " ", ""));
            StringBuffer sbr = new StringBuffer(
                    " and brp.rpId in (select distinct brp.rpId from BasRepairPackage brp where 1=1 ");
            String[] temp = brpVo.getApplicableBrands().split(",");
            if (temp != null && temp.length > 0)
            {
                sbr.append(" and brp.applicableBrands like '%" + StringEscapeUtils.escapeSql(temp[0].trim())
                                + "%'");
                for (int i = 1; i < temp.length; i++){
                    sbr.append(" or brp.applicableBrands like '%" + StringEscapeUtils.escapeSql(temp[i].trim())+ "%'");
                }
            }
            sbr.append(")");
            sb.append(sbr.toString());
        }
        if (brpVo.getSort() != null && !"".equals(brpVo.getSort())
                && brpVo.getOrder() != null && !"".equals(brpVo.getOrder()))
            sb.append(" order by brp." + brpVo.getSort().trim() + " "
                    + brpVo.getOrder().trim());
        return hql + sb.toString();
    }

    /**
     * 从数据库中查询已选配件并放入session
     */
    
    public Datagrid getSelectedParts(BasRepairPackageVo brpVo) throws Exception
    {
        Datagrid dg = new Datagrid();
        List<PartsVo> rows = new ArrayList<PartsVo>();
        int total = 0;
        if (brpVo.getRpId() != null){
            StringBuffer sb = new StringBuffer( "SELECT brpp.parts_id,brpp.parts_name,bpu.punit_id,bpu.punit_name,brpp.parts_num,");
            sb.append(" brpp.parts_repair_price,brpp.parts_amount,bsh.store_id,bsh.store_name");
            sb.append(" FROM bas_repair_package_parts brpp,bas_parts_unit bpu,bas_storehouse bsh");
            sb .append(" WHERE brpp.punit_id=bpu.punit_id AND brpp.store_id=bsh.store_id and bsh.enterprise_id="+brpVo.getEnterpriseId());
            sb.append(" AND brpp.rp_id=" + brpVo.getRpId());
            PartsVo pv = null;
            List<Object[]> rowsList = basRepairPackagePartsDao
                    .createSQLQuery(sb.toString());
            if (rowsList != null && rowsList.size() > 0)
                for (Object[] obj : rowsList){
                    pv = new PartsVo();
                    if (obj[0] != null && obj[0].toString().length() > 0)
                        pv.setPartsId(obj[0].toString());
                    if (obj[1] != null && obj[1].toString().length() > 0)
                        pv.setPartsName(obj[1].toString());
                    if (obj[2] != null && obj[2].toString().length() > 0)
                        pv.setPunitId(Short.parseShort(obj[2].toString()));
                    if (obj[3] != null && obj[3].toString().length() > 0)
                        pv.setPunitName(obj[3].toString());
                    if (obj[4] != null && obj[4].toString().length() > 0)
                        pv.setPartsNum(Double.parseDouble(obj[4].toString()));
                    if (obj[5] != null && obj[5].toString().length() > 0)
                        pv.setPartsRepairPrice(Double.parseDouble(obj[5].toString()));
                    if (obj[6] != null && obj[6].toString().length() > 0)
                        pv.setPartsAmount(Double.parseDouble(obj[6].toString()));
                    if (obj[7] != null && obj[7].toString().length() > 0)
                        pv.setStoreId(Short.parseShort(obj[7].toString()));
                    if (obj[8] != null && obj[8].toString().length() > 0)
                        pv.setStoreName(obj[8].toString());
                    rows.add(pv);
                }
            total = basRepairPackagePartsDao.getSQLCount(sb.toString(), null);
        }
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 从数据库中查询已选项目并放入session
     */
    
    public Datagrid getSelectedItem(BasRepairPackageVo brpVo) throws Exception
    {
        Datagrid dg = new Datagrid();
        List<ItemVo> rows = new ArrayList<ItemVo>();
        int total = 0;
        if (brpVo.getRpId() != null){
            StringBuffer sb = new StringBuffer(
                    "SELECT brpi.repitem_id,brpi.repitem_name,brpi.repitem_num,brpi.repitem_price,brpi.repitem_amount");
            sb.append("  FROM bas_repair_package_item brpi WHERE brpi.rp_id="+ brpVo.getRpId());
            ItemVo iv = null;
            List<Object[]> rowsList = basRepairPackagePartsDao.createSQLQuery(sb.toString());
            if (rowsList != null && rowsList.size() > 0)
                for (Object[] obj : rowsList)
                {
                    iv = new ItemVo();
                    if (obj[0] != null && obj[0].toString().length() > 0)
                        iv.setRepitemId(obj[0].toString());
                    if (obj[1] != null && obj[1].toString().length() > 0)
                        iv.setRepitemName(obj[1].toString());
                    if (obj[2] != null && obj[2].toString().length() > 0)
                        iv.setRepitemNum(Double.parseDouble(obj[2].toString()));
                    if (obj[3] != null && obj[3].toString().length() > 0)
                        iv.setRepitemPrice(Double.parseDouble(obj[3].toString()));
                    if (obj[4] != null && obj[4].toString().length() > 0)
                        iv.setRepitemAmount(Double.parseDouble(obj[4].toString()));
                    rows.add(iv);
                }
            total = basRepairPackagePartsDao.getSQLCount(sb.toString(), null);
        }
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 计算金额合计
     */
    
    public Double[] amount(BasRepairPackageVo brpVo)
    {
        Double partsAmount = 0.0;
        Double itemTimeAmount = 0.0;
        Double rpAmount = 0.0;
        String parts = brpVo.getParts();
        JSONObject jsParts = JSON.parseObject(parts);
        if (jsParts != null)
        {
            List<PartsVo> partsList = JSON.parseArray(jsParts.get("rows")
                    .toString(), PartsVo.class);
            if (partsList != null && partsList.size() > 0){
                for (PartsVo frpVo : partsList){
                    if (frpVo.getPartsAmount() == null)
                        frpVo.setPartsAmount(0.00d);
                    partsAmount += frpVo.getPartsAmount();
                }
            }
        }
        String items = brpVo.getItems();
        JSONObject jsItems = JSON.parseObject(items);
        if (jsItems != null)
        {
            List<ItemVo> itemsList = JSON.parseArray(jsItems.get("rows").toString(), ItemVo.class);
            if (itemsList != null && itemsList.size() > 0){
                for (ItemVo friVo : itemsList){
                    if (friVo.getRepitemAmount() == null)
                        friVo.setRepitemAmount(0.00d);
                    itemTimeAmount += friVo.getRepitemAmount();
                }
            }
        }
        rpAmount = partsAmount + itemTimeAmount;
        Double[] amount = new Double[] { partsAmount, itemTimeAmount, rpAmount };
        return amount;
    }

    /**
     * 保存维修套餐
     */
    
    @Log(moduleName = "基础资料", opertype = "新增维修套餐", content = "基础资料-->新增维修套餐")
    public Serializable save(BasRepairPackageVo brpVo) throws Exception
    {
        BasRepairPackage brp = new BasRepairPackage();
        copyData(brpVo, brp);
        Serializable s = basRepairPackageDao.save(brp);
        setContent("基础资料-->新增维修套餐,维修套餐编号:" + s);
        return s;
    }

    private void copyData(BasRepairPackageVo brpVo, BasRepairPackage brp)
            throws IllegalArgumentException, IllegalAccessException
    {
        MyBeanUtils.getInstance().copyBeans(brpVo, brp);
        if (brp.getApplicableBrands() != null
                && brp.getApplicableBrands().length() > 0)
            brp.setApplicableBrands(brp.getApplicableBrands().replaceAll(" ",""));
        String parts = brpVo.getParts();
        JSONObject jsParts = JSON.parseObject(parts);
        List<PartsVo> partsList = JSON.parseArray(jsParts.get("rows")
                .toString(), PartsVo.class);
        BasRepairPackageParts brpp = null;
        for (PartsVo partsVo : partsList)
        {
            brpp = new BasRepairPackageParts();
            brpp.setPartsId(partsVo.getPartsId());
            brpp.setPartsName(partsVo.getPartsName());
            brpp.setPunitId(partsVo.getPunitId());
            brpp.setPartsNum(partsVo.getPartsNum());
            brpp.setPartsRepairPrice(partsVo.getPartsRepairPrice());
            brpp.setPartsAmount(partsVo.getPartsAmount());
            brpp.setStoreId(partsVo.getStoreId());
            brpp.setBasRepairPackage(brp);
            brpp.setEnterpriseId(brpVo.getEnterpriseId());
            brp.getBasRepairPackagePartses().add(brpp);
        }
        String items = brpVo.getItems();
        JSONObject jsItems = JSON.parseObject(items);
        List<ItemVo> itemsList = JSON.parseArray(
                jsItems.get("rows").toString(), ItemVo.class);
        BasRepairPackageItem brpi = null;
        for (ItemVo itemVo : itemsList)
        {
            brpi = new BasRepairPackageItem();
            brpi.setRepitemId(itemVo.getRepitemId());
            brpi.setRepitemName(itemVo.getRepitemName());
            brpi.setRepitemNum(itemVo.getRepitemNum());
            brpi.setRepitemPrice(itemVo.getRepitemPrice());
            brpi.setRepitemAmount(itemVo.getRepitemAmount());
            brpi.setBasRepairPackage(brp);
            brpi.setEnterpriseId(brpVo.getEnterpriseId());
            brp.getBasRepairPackageItems().add(brpi);
        }
    }

    /**
     * 更新维修套餐
     */
    
    @Log(moduleName = "基础资料", opertype = "更新维修套餐", content = "基础资料-->更新维修套餐")
    public void update(BasRepairPackageVo brpVo) throws Exception
    {
        BasRepairPackage brp = basRepairPackageDao.get(BasRepairPackage.class, brpVo.getRpId());
        if(brp!=null){
        	copyData(brpVo, brp);
        	basRepairPackageDao.merge(brp);
        	setContent("基础资料-->更新维修套餐,维修套餐编号:" + brp.getRpId());
        }
    }

    /**
     * 删除维修套餐
     */
    
    @Log(moduleName = "基础资料", opertype = "删除维修套餐", content = "基础资料-->删除维修套餐")
    public void remove(BasRepairPackageVo brpVo) throws Exception
    {
        BasRepairPackage brp = basRepairPackageDao.get(BasRepairPackage.class,brpVo.getRpId());
        if(brp!=null){
        	brp.getBasRepairPackageItems().clear();
            brp.getBasRepairPackagePartses().clear();
            basRepairPackageDao.delete(brp);
            setContent("基础资料-->删除维修套餐,维修套餐编号:" + brp.getRpId());
        }
    }
}
