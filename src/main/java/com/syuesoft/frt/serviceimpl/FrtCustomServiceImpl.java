package com.syuesoft.frt.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.dao.FrtCarDao;
import com.syuesoft.frt.dao.FrtCustomDao;
import com.syuesoft.frt.service.FrtCustomService;
import com.syuesoft.frt.vo.FrtCustomVo;
import com.syuesoft.model.BasCustomArea;
import com.syuesoft.model.BasCustomGroup;
import com.syuesoft.model.BasCustomNature;
import com.syuesoft.model.BasCustomType;
import com.syuesoft.model.FrtCar;
import com.syuesoft.model.FrtCustom;
import com.syuesoft.util.CreateID;

/**
 * 客户档案service
 * @author Liujian
 * 
 */
@Service("frtCustomService")
public class FrtCustomServiceImpl extends BaseLogServiceImpl implements
        FrtCustomService
{
    private FrtCarDao frtCarDao;
    private FrtCustomDao frtCustomDao;

    /**
     * 客户档案datagrid
     */
    
    public Datagrid datagrid(FrtCustomVo fcVo) throws Exception
    {
        StringBuffer sb= new StringBuffer(" from FrtCustom fcu where  fcu.enterpriseId="+fcVo.getEnterpriseId());
        if(fcVo.getCustomId()!=null&&!fcVo.getCustomId().equals(""))
        	sb.append(" and fcu.customId like '%"+fcVo.getCustomId().trim()+"%'");
        if(fcVo.getCustomName()!=null&&!fcVo.getCustomName().equals(""))
        	sb.append(" and fcu.customName like '%"+fcVo.getCustomName().trim()+"%'");
        if(fcVo.getNatureId()!=null)
        	sb.append(" and fcu.basCustomNature.natureId="+fcVo.getNatureId());
        if(fcVo.getCstgId()!=null)
        	sb.append(" and fcu.basCustomGroup.cstgId="+fcVo.getCstgId());
        if(fcVo.getCstId()!=null)
        	sb.append(" and fcu.basCustomType.cstId="+fcVo.getCstId());
        if(fcVo.getCarLicense()!=null&&!fcVo.getCarLicense().equals(""))
        	sb.append(" and fcu.customLisence like '%"+fcVo.getCarLicense()+"%'");
        if (fcVo.getCreateDateBegin() != null && !fcVo.getCreateDateBegin().equals("")) {
			if (fcVo.getCreateDateEnd() != null && !fcVo.getCreateDateEnd().equals(""))
				sb.append(" and DATE_FORMAT(fcu.createDate,'%Y-%m-%d') BETWEEN '"+ fcVo.getCreateDateBegin() + "' AND '" + fcVo.getCreateDateEnd() + "'");
			else
				sb.append(" and DATE_FORMAT(fcu.createDate,'%Y-%m-%d') >= '"+ fcVo.getCreateDateBegin() + "'");
		} else {
			if (fcVo.getCreateDateEnd() != null && !fcVo.getCreateDateEnd().equals(""))
				sb.append(" and DATE_FORMAT(fcu.createDate,'%Y-%m-%d') <= '"+ fcVo.getCreateDateEnd() + "'");
		}
      /*  
        if(fcVo.getCreateDateBegin()!=null)
        	sb.append(" and LEFT(fcu.createDate,10)>='"+fcVo.getCreateDateBegin()+"'");
        if(fcVo.getCreateDateEnd()!=null)
        	sb.append(" and LEFT(fcu.createDate,10)<='"+fcVo.getCreateDateEnd()+"'");*/
        if(fcVo.getPareaId()!=null&&!fcVo.getPareaId().equals(""))
        	sb.append(" and fcu.basCustomArea.pareaId="+fcVo.getPareaId());
        if(fcVo.getCustomAddr()!=null&&!fcVo.getCustomAddr().equals(""))
        	sb.append(" and fcu.customAddr like '%"+fcVo.getCustomAddr().trim()+"%'");
        if(fcVo.getCustomTel1()!=null&&!fcVo.getCustomTel1().equals(""))
        	sb.append(" and fcu.customTel1 like '%"+fcVo.getCustomTel1().trim()+"%'");
       
        List<FrtCustom> fcList = frtCustomDao.find(sb.toString(), fcVo.getPage(),
                fcVo.getRows());
        List<FrtCustomVo> rows = new ArrayList<FrtCustomVo>();
        int total = frtCustomDao.getCount(sb.toString());
        if (fcList != null && fcList.size() > 0)
        {
            for (FrtCustom fc  : fcList)
            {
                FrtCustomVo fVo = new FrtCustomVo();
                BeanUtils.copyProperties(fc, fVo);
                if (fc.getBasCustomArea() != null)
                {
                    fVo.setPareaId(fc.getBasCustomArea().getPareaId());
                    fVo.setPareaName(fc.getBasCustomArea().getPareaName());
                }
                if (fc.getBasCustomGroup() != null)
                {
                    fVo.setCstgId(fc.getBasCustomGroup().getCstgId());
                    fVo.setCstgName(fc.getBasCustomGroup().getCstgName());
                }
                if (fc.getBasCustomNature() != null)
                {
                    fVo.setNatureId(fc.getBasCustomNature().getNatureId());
                    fVo.setNatureName(fc.getBasCustomNature().getNatureName());
                }
                if (fc.getBasCustomType() != null)
                {
                    fVo.setCstId(fc.getBasCustomType().getCstId());
                    fVo.setCstName(fc.getBasCustomType().getCstName());
                }
                rows.add(fVo);
            }
        }
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 客户档案datagrid
     */
    
    public Datagrid datagrid_frt_custom(FrtCustomVo fcVo) throws Exception
    {
        StringBuffer sb= new StringBuffer(" from FrtCustom fcu where fcu.customFlag="+Contstants.ONOROFF.ONOROFFYES+"  and fcu.enterpriseId="+fcVo.getEnterpriseId());
        if(fcVo.getCustomId()!=null&&!fcVo.getCustomId().equals(""))
        	sb.append(" and fcu.customId like '%"+fcVo.getCustomId().trim()+"%'");
        if(fcVo.getCustomName()!=null&&!fcVo.getCustomName().equals(""))
        	sb.append(" and fcu.customName like '%"+fcVo.getCustomName().trim()+"%'");
        if(fcVo.getNatureId()!=null)
        	sb.append(" and fcu.basCustomNature.natureId="+fcVo.getNatureId());
        if(fcVo.getCstgId()!=null)
        	sb.append(" and fcu.basCustomGroup.cstgId="+fcVo.getCstgId());
        if(fcVo.getCstId()!=null)
        	sb.append(" and fcu.basCustomType.cstId="+fcVo.getCstId());
        if(fcVo.getCarLicense()!=null&&!fcVo.getCarLicense().equals(""))
        	sb.append(" and fcu.customLisence like '%"+fcVo.getCarLicense()+"%'");
        if (fcVo.getCreateDateBegin() != null && !fcVo.getCreateDateBegin().equals("")) {
			if (fcVo.getCreateDateEnd() != null && !fcVo.getCreateDateEnd().equals(""))
				sb.append(" and DATE_FORMAT(fcu.createDate,'%Y-%m-%d') BETWEEN '"+ fcVo.getCreateDateBegin() + "' AND '" + fcVo.getCreateDateEnd() + "'");
			else
				sb.append(" and DATE_FORMAT(fcu.createDate,'%Y-%m-%d') >= '"+ fcVo.getCreateDateBegin() + "'");
		} else {
			if (fcVo.getCreateDateEnd() != null && !fcVo.getCreateDateEnd().equals(""))
				sb.append(" and DATE_FORMAT(fcu.createDate,'%Y-%m-%d') <= '"+ fcVo.getCreateDateEnd() + "'");
		}
      /*  
        if(fcVo.getCreateDateBegin()!=null)
        	sb.append(" and LEFT(fcu.createDate,10)>='"+fcVo.getCreateDateBegin()+"'");
        if(fcVo.getCreateDateEnd()!=null)
        	sb.append(" and LEFT(fcu.createDate,10)<='"+fcVo.getCreateDateEnd()+"'");*/
        if(fcVo.getPareaId()!=null&&!fcVo.getPareaId().equals(""))
        	sb.append(" and fcu.basCustomArea.pareaId="+fcVo.getPareaId());
        if(fcVo.getCustomAddr()!=null&&!fcVo.getCustomAddr().equals(""))
        	sb.append(" and fcu.customAddr like '%"+fcVo.getCustomAddr().trim()+"%'");
        if(fcVo.getCustomTel1()!=null&&!fcVo.getCustomTel1().equals(""))
        	sb.append(" and fcu.customTel1 like '%"+fcVo.getCustomTel1().trim()+"%'");
       
        List<FrtCustom> fcList = frtCustomDao.find(sb.toString(), fcVo.getPage(),
                fcVo.getRows());
        List<FrtCustomVo> rows = new ArrayList<FrtCustomVo>();
        int total = frtCustomDao.getCount(sb.toString());
        if (fcList != null && fcList.size() > 0)
        {
            for (FrtCustom fc  : fcList)
            {
                FrtCustomVo fVo = new FrtCustomVo();
                BeanUtils.copyProperties(fc, fVo);
                if (fc.getBasCustomArea() != null)
                {
                    fVo.setPareaId(fc.getBasCustomArea().getPareaId());
                    fVo.setPareaName(fc.getBasCustomArea().getPareaName());
                }
                if (fc.getBasCustomGroup() != null)
                {
                    fVo.setCstgId(fc.getBasCustomGroup().getCstgId());
                    fVo.setCstgName(fc.getBasCustomGroup().getCstgName());
                }
                if (fc.getBasCustomNature() != null)
                {
                    fVo.setNatureId(fc.getBasCustomNature().getNatureId());
                    fVo.setNatureName(fc.getBasCustomNature().getNatureName());
                }
                if (fc.getBasCustomType() != null)
                {
                    fVo.setCstId(fc.getBasCustomType().getCstId());
                    fVo.setCstName(fc.getBasCustomType().getCstName());
                }
                rows.add(fVo);
            }
        }
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }
    /**
     * 保存客户档案
     */
    @Log(moduleName = "基础资料/前台管理", opertype = "新增", content = "新增客户档案")
    
    public Serializable save(FrtCustomVo fcVo) throws Exception
    {
        FrtCustom fc = new FrtCustom();
        copyData(fcVo, fc);
        try
        {
            fc.setCustomId(CreateID.createId("FrtCustom", ""));
            fc.setEnterpriseId(fcVo.getEnterpriseId());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        fc.setCustomFlag(true);
        Serializable seri = frtCustomDao.save(fc);
        return seri;
    }

    private void copyData(FrtCustomVo fcVo, FrtCustom fc) throws Exception
    {
        BeanUtils.copyProperties(fcVo, fc);
        if (fcVo.getPareaId() != null && !"".equals(fcVo.getPareaId()))
        {
            BasCustomArea bca = new BasCustomArea();
            bca.setPareaId(fcVo.getPareaId());
            fc.setBasCustomArea(bca);
        }
        if (fcVo.getCstId() != null && !"".equals(fcVo.getCstId()))
        {
            BasCustomType bct = new BasCustomType();
            bct.setCstId(fcVo.getCstId());
            fc.setBasCustomType(bct);
        }
        if (fcVo.getCstgId() != null && !"".equals(fcVo.getCstgId()))
        {
            BasCustomGroup bcg = new BasCustomGroup();
            bcg.setCstgId(fcVo.getCstgId());
            fc.setBasCustomGroup(bcg);
        }
        if (fcVo.getNatureId() != null && !"".equals(fcVo.getNatureId()))
        {
            BasCustomNature bcn = new BasCustomNature();
            bcn.setNatureId(fcVo.getNatureId());
            fc.setBasCustomNature(bcn);
        }
    }

    /**
     * 删除客户档案
     */
    @Log(moduleName = "基础资料", opertype = "删除", content = "删除客户档案")
    
    public void remove(FrtCustomVo fcVo) throws Exception
    {
        frtCustomDao.delete(frtCustomDao.get( " from FrtCustom where customId='"+fcVo.getCustomId()+"'"));

    }

    /**
     * 更新客户档案
     */
    @Log(moduleName = "基础资料", opertype = "更新", content = "更新客户档案")
    
    public void edit(FrtCustomVo fcVo) throws Exception
    {
        FrtCustom fc =frtCustomDao.get(FrtCustom.class, fcVo.getCustomId());
        if(fc!=null){
        	copyData(fcVo, fc);
            fc.setEnterpriseId(fcVo.getEnterpriseId());
            fc.setCustomFlag(true);
            frtCustomDao.merge(fc);
        }
    }

    /**
     * 保存客户档案同步车档案
     * */
    @Log(moduleName = "前台管理", opertype = "新增", content = "新增客户档案/同步车档案")
    
    public Serializable saveCustom(FrtCustomVo fcVo) throws Exception
    {
        FrtCustom fc = new FrtCustom();
        copyData(fcVo, fc);
        fc.setCustomId(CreateID.createId("FrtCustom", ""));
        fc.setCustomFlag(true);
        fc.setEnterpriseId(fcVo.getEnterpriseId());
        Serializable seri = frtCustomDao.save(fc);
        fc = (FrtCustom) frtCustomDao.get(" from FrtCustom where customId='"+seri+"'");
        FrtCar fcr = frtCarDao.get(FrtCar.class, fcVo.getCarId());
        fcr.setFrtCustom(fc);
        return seri;
    }
    
    /**
     * 更改客户使用状态
     * */
	
	public Boolean modifyCustomFlag(FrtCustomVo fcVo) throws Exception {
		FrtCustom fc=(FrtCustom) frtCustomDao.get(" from FrtCustom where customId='"+ fcVo.getCustomId()+"'");
		if(fcVo.getCustomFlag()==null){
			fc.setCustomFlag(Contstants.ONOROFF.ONOROFFYES);
			frtCustomDao.merge(fc);
			return true;
		}
		if(fcVo.getCustomFlag()==Contstants.ONOROFF.ONOROFFYES){
			fc.setCustomFlag(Contstants.ONOROFF.ONOROFFNO);
		}else if(fcVo.getCustomFlag()==Contstants.ONOROFF.ONOROFFNO){
			fc.setCustomFlag(Contstants.ONOROFF.ONOROFFYES);
		}
		frtCustomDao.merge(fc);
		return true;
	}

	
	public boolean changeCustomId(FrtCustomVo fcVo) throws Exception {
	  return  frtCustomDao.changeCustomId(fcVo.getCustomId(),fcVo.getCustomIdOld() ,fcVo.getCustomName());
	}
	//
	public List<FrtCustom> getCustomId(FrtCustomVo fcVo) throws Exception {
		String hql=" from  FrtCustom  fc where fc.customId='"+fcVo.getCustomId()+"'";
		List<FrtCustom> lst=frtCustomDao.find(hql);
		return lst;
	}
	public FrtCarDao getFrtCarDao()
    {
        return frtCarDao;
    }
    @Autowired
    public void setFrtCarDao(FrtCarDao frtCarDao)
    {
        this.frtCarDao = frtCarDao;
    }
    public FrtCustomDao getFrtCustomDao()
    {
        return frtCustomDao;
    }
    @Autowired
    public void setFrtCustomDao(FrtCustomDao frtCustomDao)
    {
        this.frtCustomDao = frtCustomDao;
    }

}
