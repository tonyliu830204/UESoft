package com.syuesoft.vipmanagement.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.VipServiceProjectDao;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasVipInfor;
import com.syuesoft.model.VipMeal;
import com.syuesoft.model.VipcardMealR;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
import com.syuesoft.vipmanagement.dao.VipRecordMessageDao;
import com.syuesoft.vipmanagement.dao.VipServiceDao;
import com.syuesoft.vipmanagement.service.VipRecordMessageService;
import com.syuesoft.vipmanagement.service.VipServiceService;
import com.syuesoft.vipmanagement.vo.VipServiceTreeGridVo;
/**
 * 会员卡服务项目
* @ClassName: VipServiceServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author HeXin 
* @date 2013-12-5 下午02:50:25 
* @version 1.0
 */
@Service("vipServiceService")
public class VipServiceServiceImpl extends BaseLogServiceImpl implements VipServiceService {
	
	@Autowired
	private VipServiceDao vipServiceDao;
	@Autowired
    private VipRecordMessageService vipRecordMessageService;
	@Autowired
	private VipServiceProjectDao vipServiceProjectDao;
	@Autowired
    private VipRecordMessageDao vipRecordMessageDao;
	
	public Json findVipMeal(VipServiceTreeGridVo vipServiceTreeGridVo, BasUsers user) throws Exception {
        List<VipServiceTreeGridVo> list = new ArrayList<VipServiceTreeGridVo>();
        Json json = new Json();
        //String sql = "SELECT c.VIP_ID, c.VIP_NUMBER, c.JOIN_TIME, b.meal_id, b.meal_name, b.NOTE, a.r_id, d.enterprise_id, d.enterprise_id2 FROM vipcard_meal_r a, vip_meal b, bas_vip_infor c, bas_vip_infor d WHERE a.meal_id = b.meal_id AND a.bas_vip_id = c.VIP_ID AND a.bas_vip_id = d.VIP_ID AND d.enterprise_id2 ='"+vipRecordMessageService.getParentEnterpriseId(user)+"'";
        String sql = "SELECT c.VIP_ID, c.VIP_NUMBER, c.JOIN_TIME, b.meal_id, b.meal_name, b.NOTE, a.r_id, d.enterprise_id, d.enterprise_id2 FROM vipcard_meal_r a, vip_meal b, bas_vip_infor c, bas_vip_infor d WHERE a.meal_id = b.meal_id AND a.bas_vip_id = c.VIP_ID AND a.bas_vip_id = d.VIP_ID ";
        if(vipServiceTreeGridVo.getVip_Number()!=null && !vipServiceTreeGridVo.getVip_Number().equals("")){
            sql += " and c.VIP_NUMBER like '%"+StringEscapeUtils.escapeSql(vipServiceTreeGridVo.getVip_Number().trim())+"%'";
        }
        if(vipServiceTreeGridVo.getMeal_Name()!=null && !vipServiceTreeGridVo.getMeal_Name().equals("")){
            sql += " and b.meal_name like '%"+StringEscapeUtils.escapeSql(vipServiceTreeGridVo.getMeal_Name().trim())+"%'";
        }
        int total = vipServiceDao.getCountBySQL(sql);
        List<Object[]> rlist = vipServiceDao.createSQLQuery(sql, vipServiceTreeGridVo.getPage(), vipServiceTreeGridVo.getRows());
        Object[] obj = null;
        if(rlist != null && rlist.size() > 0){
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                VipServiceTreeGridVo vo = new VipServiceTreeGridVo();
                if(obj[0]!=null){vo.setVip_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setVip_Number(obj[1].toString());}
                if(obj[2]!=null){vo.setJoin_Time(FormatTime.timestamp2Str((Timestamp) obj[2]));}
                if(obj[3]!=null){vo.setMeal_Id(obj[3].toString());}
                if(obj[4]!=null){vo.setMeal_Name(obj[4].toString());}
                if(obj[5]!=null){vo.setNote(obj[5].toString());}
                if(obj[6]!=null){vo.setMeal_RId(obj[6].toString());}
                vo.setState(getChildMenu(vo).size()>0 ? "closed" : "open");
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }

    //查询子项判断该父项是否有子项
    public List<VipServiceTreeGridVo> getChildMenu(VipServiceTreeGridVo vipServiceTreeGridVo)throws Exception{
        List<VipServiceTreeGridVo> list = new ArrayList<VipServiceTreeGridVo>();
        String sql = "SELECT a.meal_Id,a.meal_context,a.memo FROM vip_meal_d a,vipcard_meal_r b WHERE a.meal_id = b.meal_id AND b.meal_id = "+vipServiceTreeGridVo.getMeal_Id()+" AND b.bas_vip_id='"+vipServiceTreeGridVo.getVip_Id()+"'";
        List<Object[]> rlist = vipServiceDao.createSQLQuery(sql);
        if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                VipServiceTreeGridVo vo = new VipServiceTreeGridVo();
                if(obj[0]!=null){vo.setMeal_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setMeal_Name(obj[1].toString());}
                if(obj[2]!=null){vo.setMemo(obj[2].toString());}
                vo.setState("open");
                vo.set_parentId(obj[0].toString());
                list.add(vo);
            }
        }
        return list;
    }
    
	public Json getVipInfo(VipServiceTreeGridVo vipServiceTreeGridVo) throws Exception {
	    Json json= new Json();
        List<VipServiceTreeGridVo> list = new ArrayList<VipServiceTreeGridVo>();
	    String sql = "select A.*, B.VIP_STATUS_VALUE from vip_info A"+
        " LEFT JOIN (SELECT b.dataKey, b.dataValue AS VIP_STATUS_VALUE FROM bas_parentdictionary a, bas_childdictionary b WHERE a.parent_id = b.parent_id AND a.dataKey='"+Contstants.HYKZT.HYKZT+"') B ON (A.VIP_STATUS = B.dataKey)"+
        " where 1 = 1";
	    int total = vipServiceDao.getCountBySQL(sql);
        List<Object[]> rlist = vipServiceDao.createSQLQuery(sql, vipServiceTreeGridVo.getPage(), vipServiceTreeGridVo.getRows());
        Object[] obj = null;
        if(rlist != null && rlist.size() > 0){
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                VipServiceTreeGridVo vo = new VipServiceTreeGridVo();
                if(obj[0]!=null){vo.setVip_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setCar_License(obj[1].toString());}
                if(obj[2]!=null){vo.setCar_Vin(obj[2].toString());}
                if(obj[3]!=null){vo.setJoin_Time(FormatTime.timestamp2Str((Timestamp) obj[3]));}
                if(obj[4]!=null){vo.setVip_Number(obj[4].toString());}
                if(obj[5]!=null){vo.setVip_Level_Id(obj[5].toString());}
                if(obj[6]!=null){vo.setVip_Level_Name(obj[6].toString());}
                if(obj[7]!=null){vo.setVip_Group_Id(obj[7].toString());}
                if(obj[8]!=null){vo.setVip_Group_Name(obj[8].toString());}
                if(obj[9]!=null){vo.setVip_Status(obj[9].toString());}
                if(obj[10]!=null){vo.setEnd_Time(FormatTime.timestamp2Str((Timestamp) obj[10]));}
                if(obj[11]!=null){vo.setVip_Balance(obj[11].toString());}
                if(obj[12]!=null){vo.setVip_Birthday(FormatTime.timestamp2Str((Timestamp) obj[12]));}
                if(obj[13]!=null){vo.setVip_Tel(obj[13].toString());}
                if(obj[14]!=null){vo.setVip_Integral(obj[14].toString());}
                if(obj[15]!=null){vo.setVip_Total_Integral(obj[15].toString());}
                if(obj[16]!=null){vo.setVip_Age(obj[16].toString());}
                if(obj[17]!=null){vo.setVip_Name(obj[17].toString());}
                //obj[18]企业编号
                //obj[19]企业编号
                if(obj[20]!=null){vo.setVip_Status_value(obj[20].toString());}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }
    
	public List<ComboxVo> getVipId(VipServiceTreeGridVo vipServiceTreeGridVo)
    throws Exception
    {
	    List<ComboxVo> list = new ArrayList<ComboxVo>();
	    List<BasVipInfor> bvis = vipRecordMessageDao.find("from BasVipInfor");
        if(bvis != null && bvis.size() > 0){
            for(BasVipInfor bvi : bvis){
                ComboxVo cb = new ComboxVo();
                cb.setId(bvi.getVipId());
                cb.setName(bvi.getVipNumber());
                list.add(cb);
            }
        }
        return list;
    }
	
	public List<ComboxVo> getMealName(int enterprise_id) throws Exception {
	    List<ComboxVo> list = new ArrayList<ComboxVo>();
	    List<VipMeal> rlist = vipServiceProjectDao.getMealName(enterprise_id);
	    if(rlist != null && rlist.size() > 0){
            for (VipMeal vipMeal : rlist) {
                ComboxVo vo = new ComboxVo();
                vo.setId(vipMeal.getMealId().toString());
                vo.setName(vipMeal.getMealName());
                list.add(vo);
            }
	    }
        return list;
    }
	
	//新增会员卡服务项目
    @Log(systemName="维修系统",moduleName="会员管理",opertype="新增")
    public Msg doAdd(VipServiceTreeGridVo vipServiceTreeGridVo, BasUsers user) throws Exception {
        Msg msg = new Msg();
        if(vipServiceTreeGridVo.getVip_Id() != null && !"".equals(vipServiceTreeGridVo.getVip_Id())){
            if(vipServiceTreeGridVo.getMeal_Id() != null && !"".equals(vipServiceTreeGridVo.getMeal_Id())){
                List<?> list = getMealNameById(vipServiceTreeGridVo.getVip_Id());
                if(list != null && list.size()>0){
                    for (int i = 0; i < list.size(); i++) {
                        String name = list.get(0).toString();
                        msg.setMsg("对不起，该会员已享受【"+name+"】!");
                        msg.setSuccess(false);
                        return msg;
                    }
                }else{
                    BasVipInfor bvi = vipRecordMessageService.getBasVipInfor3(vipServiceTreeGridVo.getVip_Id()); 
                    if(bvi != null){
                        VipMeal vmeal = vipServiceProjectDao.getVipMeal(vipServiceTreeGridVo.getMeal_Id());
                        if(vmeal != null){
                            VipcardMealR vcmr = new VipcardMealR();
                            vcmr.setBasVipInfor(bvi);
                            vcmr.setVipMeal(vmeal);
                            vcmr.setCreateTime(new Date());
                            vcmr.setPerson(user.getBasStuff().getStfId().toString());
                            vmeal.getVipcardMealRs().add(vcmr);
                            vipServiceProjectDao.saveOrUpdate(vmeal);
                            msg.setMsg("新增了会员卡服务项目成功");
                            msg.setSuccess(true);
                            setContent("新增了会员卡服务项目，且该会员编号为【"+vipServiceTreeGridVo.getVip_Id()+"】，创建时间为【"+vipServiceTreeGridVo.getCreate_Time()+"】，会员卡服务项目名称为【"+vipServiceTreeGridVo.getMeal_Name()+"】");
                        }else{
                            msg.setMsg("对不起,该套餐不存在");
                            msg.setSuccess(false);
                        }
                    }else{
                        msg.setMsg("对不起,该会员不存在");
                        msg.setSuccess(false);
                    }
                }
            }else{
                msg.setMsg("对不起，数据不完整!");
                msg.setSuccess(false);
            }
        }else{
            msg.setMsg("对不起，数据不完整!");
            msg.setSuccess(false);
        }
        return msg;
    }
    
	@Log(systemName="维修系统",moduleName="会员管理",opertype="修改")
    public Msg doUpdate(VipServiceTreeGridVo vipServiceTreeGridVo, BasUsers user) throws Exception {
	    Msg msg = new Msg();
	    if(vipServiceTreeGridVo.getMeal_RId() != null && !"".equals(vipServiceTreeGridVo.getMeal_RId())){
            if(vipServiceTreeGridVo.getVip_Id() != null && !"".equals(vipServiceTreeGridVo.getVip_Id())){
                if(vipServiceTreeGridVo.getMeal_Id() != null && !"".equals(vipServiceTreeGridVo.getMeal_Id())){
                    VipcardMealR vcmr = vipServiceDao.get("from VipcardMealR where RId ='"+vipServiceTreeGridVo.getMeal_RId()+"'");
                    if(vcmr != null){
                        BasVipInfor bvi = vipRecordMessageService.getBasVipInfor3(vipServiceTreeGridVo.getVip_Id()); 
                        if(bvi != null){
                            VipMeal vmeal = vipServiceProjectDao.getVipMeal(vipServiceTreeGridVo.getMeal_Id());
                            if(vmeal != null){
                                vcmr.setBasVipInfor(bvi);
                                vcmr.setVipMeal(vmeal);
                                vmeal.getVipcardMealRs().add(vcmr);
                                vipServiceProjectDao.saveOrUpdate(vmeal);
                                msg.setMsg("新增了会员卡服务项目成功");
                                msg.setSuccess(true);
                                setContent("修改会员【"+bvi.getVipNumber()+"】服务套餐，会员卡服务项目名称为【"+vmeal.getMealName()+"】");
                            }else{
                                msg.setMsg("对不起,该套餐不存在");
                                msg.setSuccess(false);
                            }
                        }else{
                            msg.setMsg("对不起,该会员不存在");
                            msg.setSuccess(false);
                        }
                    }else{
                        msg.setMsg("对不起,该会员套餐不存在");
                        msg.setSuccess(false);
                    }
                }else{
                    msg.setMsg("对不起，数据不完整!");
                    msg.setSuccess(false);
                }
            }else{
                msg.setMsg("对不起，数据不完整!");
                msg.setSuccess(false);
            }
	    }else{
            msg.setMsg("对不起，数据不完整!");
            msg.setSuccess(false);
        }
        return msg;
    }
	
	//
    @Log(systemName="维修系统",moduleName="会员管理",opertype="删除")
    public Msg doDelete(VipServiceTreeGridVo vipServiceTreeGridVo)
            throws Exception {
        Msg msg = new Msg();
        if(vipServiceTreeGridVo.getMeal_RId() != null && !"".equals(vipServiceTreeGridVo.getMeal_RId())){
            VipcardMealR vcmr = vipServiceDao.get("from VipcardMealR where RId ='"+vipServiceTreeGridVo.getMeal_RId()+"'");
            if(vcmr != null){
                vipServiceDao.delete(vcmr);
                msg.setMsg("删除会员服务套餐成功");
                msg.setSuccess(true);
                setContent("删除了会员为【"+vcmr.getBasVipInfor().getVipNumber()+"】的会员卡服务套餐！");
            }else{
                msg.setMsg("对不起,该会员套餐不存在");
                msg.setSuccess(false);
            }
        }else{
            msg.setMsg("对不起，数据不完整!");
            msg.setSuccess(false);
        }
        return msg;
    }
    
	
	public List<?> getMealNameById(String vipid) throws Exception {
		return vipServiceDao.getMealNameById(vipid);
	}
}