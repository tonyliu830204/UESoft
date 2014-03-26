package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasPartsBrandDAO;
import com.syuesoft.bas.dao.BasPartsPositionDAO;
import com.syuesoft.bas.dao.BasPartsStateDao;
import com.syuesoft.bas.dao.BasPartsUnitDAO;
import com.syuesoft.bas.service.BasPartsArchivesService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.dao.FrtPartsDao;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.model.BasPartsBrand;
import com.syuesoft.model.BasPartsPosition;
import com.syuesoft.model.BasPartsState;
import com.syuesoft.model.BasPartsType;
import com.syuesoft.model.BasPartsUnit;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.FrtParts;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.print.service.DownloadService;
import com.syuesoft.st.dao.BasPartsTypeDAO;
import com.syuesoft.st.dao.PartsChangePriceDAO;
import com.syuesoft.util.Msg;
import com.syuesoft.util.MyBeanUtils;

/**
 * 配件档案service
 * 
 * @author Liujian
 * 
 */
@Service("basPartsArchivesService")
public class BasPartsArchivesServiceImpl extends BaseLogServiceImpl implements
        BasPartsArchivesService
{
	
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(BasPartsArchivesServiceImpl.class);
    @Autowired
    private FrtPartsDao frtPartsDao;
    @Autowired
    private BasPartsTypeDAO basPartsTypeDAO;
    @Autowired
    private PartsChangePriceDAO partsChangePriceDAO;
    @Autowired
    private DownloadService downloadService;
    @Autowired
    private BasPartsUnitDAO basPartsUnitDao;
    @Autowired
    private BasPartsStateDao basPartsStateDao;
    @Autowired
    private BasPartsBrandDAO basPartsBrandDAO;
    @Autowired
    private BasPartsPositionDAO basPartsPositionDAO;
    /**
	 * 导入配件档案
	 * */
	
	public Msg modifyImportPartsArchives(FrtPartsVo fpVo, BasUsers user) throws Exception {
		Msg msg=new Msg();
		List<BasPartsType> list=null;
		List<FrtParts> partses =null;
		String type = fpVo.getType();
		FrtParts fp=null;
		try {
            if (type != null && !"".equals(type)){
                List<Object> downloadlist = downloadService.findDownLoad(type, fpVo, user);
                if(downloadlist != null && downloadlist.size() > 0){
                    partses = new ArrayList<FrtParts>();
                    for (Object obj : downloadlist) {
                        FrtPartsVo frtPartsVo = (FrtPartsVo)obj;
                        if(frtPartsVo.getPtypeName() != null && !"".equals(frtPartsVo.getPtypeName())){
                            fp=new FrtParts();
                            fp.setPartsName(frtPartsVo.getPartsName());
                            fp.setPartsLibrary(frtPartsVo.getPartsLibrary());
            				fp.setPartsProperty(frtPartsVo.getPartsProperty());
            				fp.setPartsAge(frtPartsVo.getPartsAge());
            				fp.setPartsSpecs(frtPartsVo.getPartsSpecs());
//                            BasPartsType bpt=basPartsTypeDAO.get("from BasPartsType bpt where bpt.ptypeName='"+frtPartsVo.getPtypeName()+"'");
//                            if(bpt==null){
//                                msg.setSuccess(false);
//                                msg.setMsg("系统找不到配件型号信息，请确认是否已经录入了型号为【"+frtPartsVo.getPtypeName()+"】的型号信息");
//                                break;
//                            }else{
//                                fp.setBasPartsType(bpt);
//                                if(bpt.getBasPartsBrand().getPbrdId() != null){
//                                    list =  basPartsTypeDAO.find("from BasPartsType bpt where bpt.basPartsBrand.pbrdId='"+bpt.getBasPartsBrand().getPbrdId()+"'");
//                                }
//                            }
//                            if(frtPartsVo.getPosName2() != null && !"".equals(frtPartsVo.getPosName2())){
//                                fp.setPosName(frtPartsVo.getPosName2());
//                            }else{
//                                fp.setPosName(Contstants.DEFAULTPARTSPROPERTY.PARTSPLACE.toString());
//                            }
//                            if(frtPartsVo.getPunitName2() != null && !"".equals(frtPartsVo.getPunitName2())){
//                                fp.setPunitName(frtPartsVo.getPunitName2());
//                            }else{
//                                fp.setPunitName(Contstants.DEFAULTPARTSPROPERTY.PARTSUNIT.toString());
//                            }
//                            if(frtPartsVo.getStateName2() !=null && !"".equals(frtPartsVo.getStateName2())){
//                                fp.setStateName(frtPartsVo.getStateName2());
//                            }else{
//                                fp.setStateName(Contstants.DEFAULTPARTSPROPERTY.PARTSAREA.toString());
//                            }
                            if(frtPartsVo.getPartsId() != null && !"".equals(frtPartsVo.getPartsId())){
                                fp.setPartsId(frtPartsVo.getPartsId());
                            }
                            if(frtPartsVo.getPartsId2() != null && !"".equals(frtPartsVo.getPartsId2())){
                                fp.setPartsId2(frtPartsVo.getPartsId2());
                            }
                            if(frtPartsVo.getPartsSimpleId()!= null && !"".equals(frtPartsVo.getPartsSimpleId())){
                                fp.setPartsSimpleId(frtPartsVo.getPartsSimpleId());
                            }
                            BasPartsBrand bpr=null;
                            if(frtPartsVo.getPbrdName()!=null&&frtPartsVo.getPbrdName().length()>0)
                            	bpr=basPartsBrandDAO.get("from BasPartsBrand bpr where bpr.pbrdName='"+frtPartsVo.getPbrdName().trim()+"' and bpr.enterpriseId="+fpVo.getEnterpriseId());
            				if(bpr==null){
            					frtPartsVo.setPbrdId(Contstants.DEFAULTPARTSPROPERTY.PARTSBRAND);
            				}else{
            					frtPartsVo.setPbrdId(bpr.getPbrdId());
            				}
            				BasPartsType bpt=null;
            				BasPartsType temp=new BasPartsType();
            				if(frtPartsVo.getPbrdId()!=null&&frtPartsVo.getPtypeName()!=null&&frtPartsVo.getPtypeName().length()>0)
            					bpt=basPartsTypeDAO.get("from BasPartsType bpt where bpt.basPartsBrand.pbrdId="+frtPartsVo.getPbrdId()
            								+" and bpt.ptypeName='"+frtPartsVo.getPtypeName()+"' and bpt.enterpriseId="+fpVo.getEnterpriseId());
            				if(bpt==null){
            					temp.setPtypeId(Contstants.DEFAULTPARTSPROPERTY.PARTSTYPE);
            				}else{
            					temp=bpt;
            				}
            				fp.setBasPartsType(temp);
            				BasPartsPosition bpp=null;
            				if(frtPartsVo.getPosName2()!=null&&frtPartsVo.getPosName2().length()>0)
            					bpp=basPartsPositionDAO.get("from BasPartsPosition bpp where bpp.posName='"+frtPartsVo.getPosName2().trim()+"' and bpp.enterpriseId="+fpVo.getEnterpriseId());
            				if(bpp!=null){
            					fp.setPosName(bpp.getPosId().toString());
            				}
            				BasPartsUnit bpu=null;
            				if(frtPartsVo.getPunitName2()!=null&&frtPartsVo.getPunitName2().length()>0)
            					bpu= basPartsUnitDao.get("from BasPartsUnit bpu where bpu.punitName='"+frtPartsVo.getPunitName2()+"' and bpu.enterpriseId="+fpVo.getEnterpriseId());
            				if(bpu==null){
            					fp.setPunitName(Contstants.DEFAULTPARTSPROPERTY.PARTSUNIT.toString());
            				}else{
            					fp.setPunitName(bpu.getPunitId().toString());
            				}
            				BasPartsState bps=null;
            				if(frtPartsVo.getStateName2()!=null&&frtPartsVo.getStateName2().length()>0)
            					bps=basPartsStateDao.get("from BasPartsState bps where bps.stateName='"+frtPartsVo.getStateName2().trim()+"' and bps.enterpriseId="+fpVo.getEnterpriseId());
            				if(bps!=null){
            					fp.setStateName(bps.getStateId().toString());
            				}
                            fp.setPartsFlag(Contstants.ONOROFF.ONOROFFYES);
//                            if(list != null && list.size() > 0){
//                                String type_="";
//                                for(int i =0; i<list.size(); i++){
//                                    BasPartsType basPartsType = list.get(i);
//                                    if(i < list.size()-1)
//                                        type_ += basPartsType.getPtypeId()+",";
//                                    else
//                                        type_ += basPartsType.getPtypeId()+"";
//                                }
//                                fp.setFitPtype(type_);
//                            }
                            fp.setFitPtype(frtPartsVo.getFitPtypeName());
                            fp.setEnterpriseId(fpVo.getEnterpriseId());
                            partses.add(fp);
                        }else{
                            msg.setSuccess(false);
                            msg.setMsg("导入配件信息不包含配件型号信息，请确认");
                            break;
                        }
                    }
                    frtPartsDao.saveOrUpdates(partses);
                    msg.setMsg("导入配件档案成功！");
                    msg.setSuccess(true);			
                }
            }else{
            	msg.setMsg("请选择一个要倒入的类型！");
            	msg.setSuccess(false);
            	return msg;
            }
		} catch (Exception e) {
			msg.setMsg("导入配件档案失败！");
			msg.setSuccess(false);
			logger.error("导入配件档案失败！", e);
		}
		return msg;
	}
	
    /**
     * 配件档案datagrid
     * 
     * @throws Exception
     */
    
    public Datagrid datagridPartsArchives(FrtPartsVo fpVo) throws Exception
    {
        StringBuffer sb = new StringBuffer("SELECT p.*  FROM frt_parts_view p where P.enterpriseId="+fpVo.getEnterpriseId());
       
        if(!(fpVo.getFlag()!=null&&fpVo.getFlag()==true))
        	sb.append(" and p.partsFlage="+Contstants.ONOROFF.ONOROFFYES);
        if (fpVo.getPartsId() != null && !"".equals(fpVo.getPartsId().trim()))
            sb.append(" AND p.parttsId LIKE '%" + StringEscapeUtils.escapeSql(fpVo.getPartsId().trim()) + "%'");
        if (fpVo.getPartsId2() != null && !"".equals(fpVo.getPartsId2().trim()))
            sb.append(" AND p.partId2 LIKE '%" + StringEscapeUtils.escapeSql(fpVo.getPartsId2().trim()) + "%'");
        if (fpVo.getPartsName() != null
                && !"".equals(fpVo.getPartsName().trim()))
            sb.append(" AND p.partsName LIKE '%" + StringEscapeUtils.escapeSql(fpVo.getPartsName().trim())+ "%'");
        //查询配件调价信息
        
        if (fpVo.getPbrdId() != null && !"".equals(fpVo.getPbrdId()))
            sb.append(" AND p.pbrdId=" + fpVo.getPbrdId());
        if (fpVo.getPtypeId() != null && !"".equals(fpVo.getPtypeId()))
            sb.append(" AND p.ptypeId=" + fpVo.getPtypeId());
        if (fpVo.getFitPtype() != null && !"".equals(fpVo.getFitPtype().trim()))
        {
            fpVo.setFitPtype(fpVo.getFitPtype().replaceAll(" ", ""));
            StringBuffer sbr = new StringBuffer(
                    " AND p.parttsId in (select distinct fps.PARTS_ID FROM frt_parts fps where 1=1 ");
            String[] temp = fpVo.getFitPtype().split(",");
            if (temp != null && temp.length > 0){
                sbr.append(" and p.fitPtype LIKE '%" + StringEscapeUtils.escapeSql(temp[0].trim()) + "%'");
                for (int i = 1; i < temp.length; i++){
                    sbr.append(" or p.fitPtype LIKE '%" + StringEscapeUtils.escapeSql(temp[i].trim()) + "%'");
                }
            }
            sbr.append(")");
            sb.append(sbr.toString());
        }
        if (fpVo.getPosName() != null && !"".equals(fpVo.getPosName().trim()))
            sb.append(" AND p.posId=" + fpVo.getPosName());
        if (fpVo.getStateName() != null&& !"".equals(fpVo.getStateName().trim()))
            sb.append(" AND p.stateId=" + fpVo.getStateName());
        if (fpVo.getSort() != null && !"".equals(fpVo.getSort().trim())
                && fpVo.getOrder() != null&& !"".equals(fpVo.getOrder().trim()))
        {
            String sort = fpVo.getSort().trim();
            if ("ptypeId".equals(fpVo.getSort().trim()))
            {
                sort = "ptypeId ";
                sb.append("order by p." + sort + " " + fpVo.getOrder().trim());
            }
        }
        List<FrtPartsVo> rows = new ArrayList<FrtPartsVo>();
        FrtPartsVo fp = null;
        List<Object[]> rowsList = frtPartsDao.createSQLQuery(sb.toString(),
                null, fpVo.getPage(), fpVo.getRows());
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] obj : rowsList)
            {
                fp = new FrtPartsVo();
                if (obj[0] != null && obj[0].toString().length() > 0)
                    fp.setPartsId(obj[0].toString());
                if (obj[1] != null && obj[1].toString().length() > 0)
                    fp.setPartsId2(obj[1].toString());
                if (obj[2] != null && obj[2].toString().length() > 0)
                    fp.setPartsName(obj[2].toString());
                if (obj[3] != null && obj[3].toString().length() > 0)
                    fp.setPartsSimpleId(obj[3].toString());
                if (obj[4] != null && obj[4].toString().length() > 0)
                    fp.setPbrdId(Short.parseShort(obj[4].toString()));
                if (obj[5] != null && obj[5].toString().length() > 0)
                    fp.setPbrdName(obj[5].toString());
                if (obj[6] != null && obj[6].toString().length() > 0)
                    fp.setPtypeId(Short.parseShort(obj[6].toString()));
                if (obj[7] != null && obj[7].toString().length() > 0)
                    fp.setPtypeName(obj[7].toString());
                if (obj[8] != null && obj[8].toString().length() > 0)
                    fp.setFitPtype(obj[8].toString());
                if (obj[9] != null && obj[9].toString().length() > 0)
                    fp.setPosName(obj[9].toString());
                if (obj[10] != null && obj[10].toString().length() > 0)
                    fp.setPosName2(obj[10].toString());
                if (obj[11] != null && obj[11].toString().length() > 0)
                    fp.setPunitName(obj[11].toString());
                if (obj[12] != null && obj[12].toString().length() > 0)
                    fp.setPunitName2(obj[12].toString());
                if (obj[13] != null && obj[13].toString().length() > 0)
                    fp.setStateName(obj[13].toString());
                if (obj[14] != null && obj[14].toString().length() > 0)
                    fp.setStateName2(obj[14].toString());
                if (obj[15] != null && obj[15].toString().length() > 0)
                    fp.setPartsRemark(obj[15].toString());
                if (obj[16] != null && obj[16].toString().length() > 0)
                    fp.setPartsNeedPoint(Integer.parseInt(obj[16].toString()));
                if (obj[17] != null && obj[17].toString().length() > 0)
                    fp.setPartsFlag((Boolean)obj[17]);
                if (obj[18] != null && obj[18].toString().length() > 0)
                	fp.setPartsLibrary(obj[18].toString());
                if (obj[19] != null && obj[19].toString().length() > 0)
                	fp.setPartsProperty(obj[19].toString());
                if (obj[20] != null && obj[20].toString().length() > 0)
                	fp.setPartsAge(obj[20].toString());
                if (obj[21] != null && obj[21].toString().length() > 0)
                	fp.setPartsSpecs(obj[21].toString());
                if (fp.getFitPtype() == null){
                    fp.setFitPtypeName(null);
                }
                fp.setFitPtypeName(fp.getFitPtype());
                rows.add(fp);
            }
        int total = frtPartsDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }
    /**
     * 保存配件档案
     * 
     * @throws Exception
     */
    
    @Log(moduleName = "基础资料", opertype = "新增配件档案", content = "基础资料-->新增配件档案")
    public void save(FrtPartsVo fpVo) throws Exception
    {
        FrtParts fp = new FrtParts();
        MyBeanUtils.getInstance().copyBeans(fpVo, fp);
        if (fp.getFitPtype() != null)
            fp.setFitPtype(fp.getFitPtype().replaceAll(" ", ""));
        BasPartsType bpt = new BasPartsType();
        bpt.setPtypeId(fpVo.getPtypeId());
        fp.setBasPartsType(bpt);
        fp.setPartsFlag(Contstants.ONOROFF.ONOROFFYES);
        fp.setEnterpriseId(fpVo.getEnterpriseId());
        Serializable bb = frtPartsDao.save(fp);
        setContent("基础资料-->新增配件档案,配件档案编号:" + bb);
    }

    /**
     * 删除配件档案
     * 
     * @throws Exception
     */
    
    @Log(moduleName = "基础资料", opertype = "删除配件档案", content = "基础资料-->删除配件档案")
    public void remove(String partsId) throws Exception
    {
        frtPartsDao.delete(frtPartsDao.get(FrtParts.class, partsId));
        PartsChangePrice p= partsChangePriceDAO.get("from PartsChangePrice pcp where pcp.partsId='" + partsId+"'");
        if(p!=null)
        	partsChangePriceDAO.delete(p);
        setContent("基础资料-->删除配件档案,配件档案编号:" + partsId);
    }

    /**
     * 修改配件档案
     * 
     * @throws Exception
     */
    
    @Log(moduleName = "基础资料", opertype = "更新配件档案", content = "基础资料-->更新配件档案")
    public void edit(FrtPartsVo fpVo) throws Exception
    {
        FrtParts fp =frtPartsDao.get(FrtParts.class, fpVo.getPartsId()) ;
        if(fp!=null){
        	MyBeanUtils.getInstance().copyBeans(fpVo, fp);
            if (fp.getFitPtype() != null)
                fp.setFitPtype(fp.getFitPtype().replaceAll(" ", ""));
            BasPartsType bpt = new BasPartsType();
            bpt.setPtypeId(fpVo.getPtypeId());
            fp.setBasPartsType(bpt);
            fp.setPartsFlag(Contstants.ONOROFF.ONOROFFYES);
            fp.setEnterpriseId(fpVo.getEnterpriseId());
            frtPartsDao.merge(fp);
            if(!(fpVo.getPartsId().equals(fpVo.getParamPartId()))){
            	  FrtParts p=frtPartsDao.get(FrtParts.class, fpVo.getParamPartId());
                  if(p!=null)
                  	frtPartsDao.delete(p);
            }
            if (fpVo.getTranslationFlag() != null&& fpVo.getTranslationFlag() == true)
            {
                StringBuffer sb = new StringBuffer();
                sb.append("update parts_change_price pcp set pcp.PARTS_REPAIR_PRICE="
                                + fpVo.getPartsRepairPrice());
                sb.append(" ,pcp.PARTS_SELL_PRICE=" + fpVo.getPartsSellPrice());
                sb.append(" ,pcp.PARTS_POINT_PRICE=" + fpVo.getPartsPointPrice());
                sb.append(" ,pcp.PARTS_SPECIAL_PRICE="
                        + fpVo.getPartsSpecialPrice());
                sb.append(" ,pcp.PARTS_CLAIMANT_PRICE="
                        + fpVo.getPartsClaimantPrice());
                sb.append(" ,pcp.PARTS_LATEST_TAXPRICE="
                        + fpVo.getPartsLatestTaxprice());
                sb.append(" ,pcp.PARTS_LATEST_NOTAXPRICE="
                        + fpVo.getPartsLatestNotaxprice());
                sb.append(" ,pcp.STOCK_UPPER=" + fpVo.getStockUpper());
                sb.append(" ,pcp.STOCK_LOWER=" + fpVo.getStockLower());
                sb.append(" where pcp.PARTS_ID='" + fp.getPartsId() + "'");
                frtPartsDao.batchChangePrice(sb.toString());
            }
        }
        setContent("基础资料-->更新配件档案,配件档案编号:" + fpVo.getPartsId());
    }

    /**
     * 变更配件编号
     * @throws Exception
     */
    
    public boolean changePartsId(FrtPartsVo fpVo) throws Exception
    {
        return frtPartsDao.changePartsId(fpVo.getPartsId(), fpVo
                .getChangedPartsId(),fpVo.getEnterpriseId());
    }

    
    public List<FrtParts> getPartsId(FrtPartsVo fpVo) throws Exception
    {
        String hql = "From FrtParts where enterpriseId="+fpVo.getEnterpriseId()+" and partsId = '"
                + fpVo.getPartsId() + "'";
        List<FrtParts> fp = frtPartsDao.find(hql);
        return fp;
    }
	
    /**
     * 查询配件有无入仓信息
     * */
	
	public Boolean isExistsJoinCompany(FrtPartsVo fpVo) throws Exception {
		List list=frtPartsDao.createSQLQuery("SELECT * FROM parts_change_price WHERE enterprise_id="+fpVo.getEnterpriseId()+" and  parts_id='"+fpVo.getPartsId()+"'");
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
	/**
	 * 更改配件使用状态
	 * */
	
	public Boolean modifyPartsFlag(FrtPartsVo fpVo) throws Exception {
		FrtParts fp=frtPartsDao.get(FrtParts.class, fpVo.getPartsId());
		if(fpVo.getPartsFlag()==Contstants.ONOROFF.ONOROFFYES){
			fp.setPartsFlag(Contstants.ONOROFF.ONOROFFNO);
		}else if(fpVo.getPartsFlag()==Contstants.ONOROFF.ONOROFFNO){
			fp.setPartsFlag(Contstants.ONOROFF.ONOROFFYES);
		}
		frtPartsDao.merge(fp);
		return true;
	}
	/**
	 * 查询配件库存量
	 * */
	
	public Double findPartsCount(FrtPartsVo fpVo) throws Exception {
		List list=partsChangePriceDAO.createSQLQuery("SELECT sum(pcp.PARTS_NOW_COUNT) FROM parts_change_price pcp WHERE pcp.PARTS_ID='"+fpVo.getPartsId()+"'");
		if(list!=null&&list.size()>0){
			if(list.get(0)!=null&&list.get(0).toString().length()>0)
				return Double.parseDouble(list.get(0).toString());
			return 0.00d;
		}
		return 0.00d;
	}
	
	
	/**
	 * 判断配件是否已入库
	 */
    public boolean isExist(FrtPartsVo fpVo)throws Exception{
    	StringBuffer sb=new StringBuffer("SELECT st_storage_item.PARTS_ID FROM st_storage_item where 1=1");
    	if(fpVo.getPartsId()!=null&&!fpVo.getPartsId().trim().equals(""))
    	   sb.append(" and st_storage_item.PARTS_ID='"+fpVo.getPartsId().trim()+"'");
    	sb.append(" group by st_storage_item.PARTS_ID");
    	List list=frtPartsDao.createSQLQuery(sb.toString());
    	if(list!=null&&list.size()>0)
    		return true;
    	else
    		return false;
    }

	
	public boolean isExistsPartsIdEdit(FrtPartsVo fpVo) throws Exception {
		String partId=fpVo.getParamPartId();
		 String hql = "From FrtParts where enterpriseId="+fpVo.getEnterpriseId()+" and partsId = '"
             + fpVo.getPartsId() + "'";
		 List<FrtParts> fp = frtPartsDao.find(hql);
		if(fp==null || fp.size()==0){
			return false;	
		}else{
			String id=fp.get(0).getPartsId();
			if(partId!=null && !("").equals(partId)&& partId.equals(id)){
				return false;
			}else{
				return true;
			}
		}
	}
	
}