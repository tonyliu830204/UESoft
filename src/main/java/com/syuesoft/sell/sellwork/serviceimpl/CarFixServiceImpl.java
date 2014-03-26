package com.syuesoft.sell.sellwork.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellCarinstall;
import com.syuesoft.sell.model.XsSellCarinstallAddItem;
import com.syuesoft.sell.model.XsSellCarinstallDetail;
import com.syuesoft.sell.sellwork.dao.CarFixDao;
import com.syuesoft.sell.sellwork.service.CarFixService;
import com.syuesoft.sell.sellwork.vo.CarFixVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

@Service("carFixService")
public class CarFixServiceImpl extends BaseLogServiceImpl implements
		CarFixService {

	@Resource
	
	
	
	
	private CarFixDao carFixDao;
	
	@Resource
    private BaseTagDAO baseTagDAO;
    
	@Resource
	private BasCompanyInformationSetService BasCompanyInformationSetService;
	
	/**
	 * 获取车辆加装信息
	 */
	@SuppressWarnings("unchecked")
    public Json getCarInfor(CarFixVo carFixVo) throws Exception {
	    String ids = null;
        List<XsChilddictionary> list0 = baseTagDAO.findChilds(Contstants.SELLSTATE.BASE_SELLSTATE,null,carFixVo.getEnterpriseId());
        if(list0 != null){
            for(XsChilddictionary child : list0){
                if(child.getDataKey().equals(Contstants.SELLSTATE.SELLADD) ||
                   child.getDataKey().equals(Contstants.SELLSTATE.FORSALE)){
                    if(ids == null)
                        ids = child.getChildId().toString();
                    else
                        ids += ","+child.getChildId().toString();
                }
            }
        }
        String hql ="from XsCarInfo WHERE sellStateChild.childId in ("+ids+") ";
        if(carFixVo.getEnterpriseId()!=null && !carFixVo.getEnterpriseId().equals("")){
            hql += " AND enterpriseId = '"+carFixVo.getEnterpriseId()+"'";
        }
        if(carFixVo.getXs_car_vin_number()!=null && !carFixVo.getXs_car_vin_number().equals("")){
            hql += " AND carVinNumber like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_vin_number().trim())+"%'";
        }
        if(carFixVo.getXs_car_ocn()!=null && !carFixVo.getXs_car_ocn().equals("")){
            hql += " AND carOcn like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_ocn().trim())+"%'";
        }
        if (carFixVo.getSort() != null && !"".equals(carFixVo.getSort().trim())
                && carFixVo.getOrder() != null
                && !"".equals(carFixVo.getOrder().trim()))
        {
        	 hql += " order by " + carFixVo.getSort().trim() + " "
                    + carFixVo.getOrder().trim();
        }
       
        List rlist = carFixDao.find(hql,carFixVo.getPage(),carFixVo.getRows());
        List<XsCarInfo> zlist = rlist;
        List list = new ArrayList();
        Json json = new Json();
        if(zlist!=null){
            for (XsCarInfo xsCarInfo : zlist) {
                CarFixVo vo = new CarFixVo();
                if(xsCarInfo.getBrandChild().getChildId()!=null && !xsCarInfo.getBrandChild().getChildId().equals("")){
                    List vlist = carFixDao.createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id="+xsCarInfo.getBrandChild().getChildId()+"");
                    if(vlist != null)
                       vo.setXs_car_brand(vlist.get(0)+"");
                }
                vo.setXs_car_code(xsCarInfo.getCarCode());
                
                if(xsCarInfo.getColorChild().getChildId()!=null && !xsCarInfo.getColorChild().getChildId().equals("")){
                    List vlist = carFixDao.createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id="+xsCarInfo.getColorChild().getChildId()+"");
                    if(vlist != null)
                       vo.setXs_car_color(vlist!=null ? vlist.get(0)+"" : "");
                }
                vo.setXs_car_id(xsCarInfo.getCarId()+"");
                
                if(xsCarInfo.getCarModelId()!=null && !xsCarInfo.getCarModelId().equals("")){
                    List vlist = carFixDao.createSQLQuery("SELECT xs_model_name FROM xs_car_model WHERE xs_model_id="+xsCarInfo.getCarModelId()+"");
                    if(vlist != null)
                       vo.setXs_car_model_id(vlist!=null ? vlist.get(0)+"" :"");
                }
                
                vo.setXs_car_motor_number(xsCarInfo.getCarMotorNumber());
                vo.setXs_car_ocn(xsCarInfo.getCarOcn());
                
                if(xsCarInfo.getSellStateChild().getChildId()!=null && !xsCarInfo.getSellStateChild().getChildId().equals("")){
                    List vlist = carFixDao.createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id="+xsCarInfo.getSellStateChild().getChildId()+"");
                    if(vlist != null)
                       vo.setXs_car_sell_state(vlist!=null ? vlist.get(0)+"" : "");
                }
                vo.setXs_car_vin_number(xsCarInfo.getCarVinNumber());
                vo.setFix_status(xsCarInfo.getFixStatusChild().getChildId()+"");
                vo.setFix_statusN(xsCarInfo.getFixStatusChild().getDataKey()+"");
                list.add(vo);
            }
            
            
            json.setTotal(carFixDao.getCount(hql));
            json.setRows(list);
        }
        return json;
	}

	public void doApplyFix(CarFixVo carFixVo, BasUsers user) throws Exception {
	    
	    XsCarInfo xsCarInfo = (XsCarInfo)carFixDao.get("from XsCarInfo where carId="+carFixVo.getXs_car_id());
        List<XsChilddictionary> list = baseTagDAO.findChilds(Contstants.JZZT.JZZT,null,carFixVo.getEnterpriseId());
        if(list != null){
            for(XsChilddictionary child : list){
                if(child.getDataKey().equals(Contstants.JZZT.sqjz)){
                    xsCarInfo.setFixStatusChild(child);
                    break;
                }
            }
        }
        carFixDao.doApplyFix(xsCarInfo);
        
        XsSellCarinstall carinstall = new XsSellCarinstall();
        carinstall.setEnterpriseId(carFixVo.getEnterpriseId());
        carinstall.setXsCarInfo(xsCarInfo);
        carinstall.setInstallCode(CreateID.createId("CarFixVo", Contstants.SELL_BILLSDEPLOY.CARINSTALL));
        carinstall.setSunMoney(0.0);
        carinstall.setInstallApplyPerson(Integer.parseInt(user.getBasStuff().getStfId().toString()));
        carinstall.setInstallApplyDate(new Date());
        Integer cid = baseTagDAO.getChildId(Contstants.FINISHWORK.FINISHWORK,Contstants.FINISHWORK.NOKAIFINISH,carFixVo.getEnterpriseId());
		if(cid!=null){
			 XsChilddictionary finishState= baseTagDAO.findById(cid);
			 carinstall.setSellCarinstallFinishState(finishState);
		}
       
      /*  List<XsChilddictionary> list2 = baseTagDAO.findChilds(Contstants.FINISHWORK.FINISHWORK);
        if(list2 != null){
            for(XsChilddictionary child : list2){
                if(child.getDataKey().equals(Contstants.FINISHWORK.NOKAIFINISH)){
                    
                	carinstall.setFinishState(child.getChildId());
                    break;
                }
            }
        }*/
		Integer examine = baseTagDAO.getChildId(Contstants.ADUIT.ADUIT,Contstants.ADUIT.WEISHENHE,carFixVo.getEnterpriseId());
		if(examine!=null){
			 XsChilddictionary examineS= baseTagDAO.findById(examine);
			 carinstall.setSellCarinstallExamine(examineS);
		}
        /*List<XsChilddictionary> list3 = baseTagDAO.findChilds(Contstants.ADUIT.ADUIT);
        if(list3 != null){
            for(XsChilddictionary child : list3){
                if(child.getDataKey().equals(Contstants.ADUIT.WEISHENHE)){
                    carinstall.setExamine(child.getChildId());
                    break;
                }
            }
        }*/
        carFixDao.saveOrUpdate(carinstall);
	}
   
	/**
	 * 获取加装汇总信息
	 */
	@SuppressWarnings("unchecked")
    public Json getCarFixInforNoFinish(CarFixVo carFixVo) throws Exception {
		Json json = new Json();
		List<CarFixVo> car = new ArrayList<CarFixVo>();
        String sql = "SELECT b.Install_id,b.Install_code,b.sun_money,b.finish_state,b.examine,b.Install_applyperson,b.Install_applydate,b.xs_car_id,a.xs_car_licensePlate,a.xs_car_vin_number,a.xs_car_ocn,a.xs_car_color,a.xs_car_brand,a.xs_car_model_id,a.xs_car_motor_number,a.xs_car_code"+
                     " FROM xs_sell_carInstall b,xs_car_info a WHERE b.xs_car_id = a.xs_car_id";
        //企业编号
        if(carFixVo.getEnterpriseId()!=null && !carFixVo.getEnterpriseId().equals("")){
        	sql += " AND a.enterprise_Id = "+carFixVo.getEnterpriseId()+"";
        }
        if(carFixVo.getXs_car_vin_number()!=null && !carFixVo.getXs_car_vin_number().equals("")){
            sql += " AND a.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_vin_number().trim())+"%'";
        }
        if(carFixVo.getXs_car_ocn()!=null && !carFixVo.getXs_car_ocn().equals("")){
            sql += " AND a.xs_car_ocn like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_ocn().trim())+"%'";
        }
        if(carFixVo.getFinish_state()!=null && !carFixVo.getFinish_state().equals("")){
            sql += " AND b.finish_state ='"+StringEscapeUtils.escapeSql(carFixVo.getFinish_state().trim())+"'";
        }else{
            List<XsChilddictionary> list = baseTagDAO.findChilds(Contstants.FINISHWORK.FINISHWORK,null,carFixVo.getEnterpriseId());
            if(list != null){
                for(XsChilddictionary child : list){
                    if(child.getDataKey().equals(Contstants.FINISHWORK.YIFINISH)){
                        carFixVo.setFinish_state(child.getChildId().toString());
                        break;
                    }
                }
            }
            sql += " AND b.finish_state <>'"+StringEscapeUtils.escapeSql(carFixVo.getFinish_state().trim())+"'";
        }
        sql += " order by b.Install_code desc";
        List<Object[]> list = carFixDao.createSQLQuery(sql, carFixVo.getPage(), carFixVo.getRows());
        if(list != null && list.size() > 0){
            for(Object[] objs : list){
               CarFixVo carFixVo_ = new CarFixVo();
               carFixVo_.setInstall_id(objs[0] != null ? objs[0].toString() : null);
               carFixVo_.setInstall_code(objs[1] != null ? objs[1].toString() : null);
               carFixVo_.setSun_money(objs[2] != null ? objs[2].toString() : null);
               
               
               if(objs[3] != null){
                   carFixVo_.setFinish_state(objs[3] != null ? objs[3].toString() : null);
                   List<Object[]> vlist = carFixDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[3].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                            carFixVo_.setFinish_stateKey(vobjs[0] != null ? vobjs[0].toString() : null);
                            carFixVo_.setFinish_stateName(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               if(objs[4] != null){
                   carFixVo_.setExamine(objs[4] != null ? objs[4].toString() : null);
                   List<Object[]> vlist = carFixDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[4].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                            carFixVo_.setExamineKey(vobjs[0] != null ? vobjs[0].toString() : null);
                            carFixVo_.setExamineName(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               if(objs[5] != null){
                   List<Object[]> vlist = carFixDao.createSQLQuery("SELECT STF_ID, STF_NAME FROM bas_stuff WHERE STF_ID="+objs[5].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                            carFixVo_.setApplySTF_ID(vobjs[0] != null ? vobjs[0].toString() : null);
                            carFixVo_.setApplySTF_NAME(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               carFixVo_.setApplyDate(objs[6] != null ? objs[6].toString() : null);
               carFixVo_.setXs_car_id(objs[7] != null ? objs[7].toString() : null);
               carFixVo_.setXs_car_licensePlate(objs[8] != null ? objs[8].toString() : null);
               carFixVo_.setXs_car_vin_number(objs[9] != null ? objs[9].toString() : null);
               carFixVo_.setXs_car_ocn(objs[10] != null ? objs[10].toString() : null);
               if(objs[11] != null){
                   List<Object[]> vlist = carFixDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[11].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                            carFixVo_.setXs_car_color(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               if(objs[12] != null){
                   List<Object[]> vlist = carFixDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[12].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                            carFixVo_.setXs_car_brand(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               if(objs[13] != null){
                   List vlist = carFixDao.createSQLQuery("SELECT xs_model_name FROM xs_car_model WHERE xs_model_id="+objs[13]);
                   if(vlist != null)
                       carFixVo_.setXs_car_model_id(vlist.get(0).toString());
               }
               carFixVo_.setXs_car_motor_number(objs[14] != null ? objs[14].toString() : null);
               carFixVo_.setXs_car_code(objs[15] != null ? objs[15].toString() : null);
               car.add(carFixVo_);
            }
        }
        json.setTotal(carFixDao.getCountBySQL(sql));
        json.setRows(car);
        return json;
    }
	
	/**
     * 获取审核汇总信息
     */
	@SuppressWarnings("unchecked")
    public Json getCarFixInforExamine(CarFixVo carFixVo) throws Exception {
	    List<XsChilddictionary> childList = baseTagDAO.findChilds(Contstants.FINISHWORK.FINISHWORK,null,carFixVo.getEnterpriseId());
        if(childList != null){
            for(XsChilddictionary child : childList){
                if(child.getDataKey().equals(Contstants.FINISHWORK.YIFINISH)){
                    carFixVo.setFinish_state(child.getChildId().toString());
                    break;
                }
            }
        }
        String sql = "SELECT b.Install_id,b.Install_code,b.sun_money,b.finish_state,b.examine,b.Install_applyperson,b.Install_applydate,b.xs_car_id,a.xs_car_licensePlate,a.xs_car_vin_number,a.xs_car_ocn,a.xs_car_color,a.xs_car_brand,a.xs_car_model_id,a.xs_car_motor_number,a.xs_car_code"+
                     " FROM xs_sell_carInstall b,xs_car_info a WHERE b.xs_car_id = a.xs_car_id AND b.finish_state ='"+StringEscapeUtils.escapeSql(carFixVo.getFinish_state().trim())+"' ";
        //企业编号
        if(carFixVo.getEnterpriseId()!=null && !carFixVo.getEnterpriseId().equals("")){
        	sql += " AND a.enterprise_Id = "+carFixVo.getEnterpriseId()+"";
        }
        if(carFixVo.getXs_car_vin_number()!=null && !carFixVo.getXs_car_vin_number().equals("")){
            sql += " AND a.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_vin_number().trim())+"%'";
        }
        if(carFixVo.getXs_car_ocn()!=null && !carFixVo.getXs_car_ocn().equals("")){
            sql += " AND a.xs_car_ocn like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_ocn().trim())+"%'";
        }
        if(carFixVo.getExamine()!=null && !carFixVo.getExamine().equals("")){
            sql += " AND b.examine ='"+StringEscapeUtils.escapeSql(carFixVo.getExamine().trim())+"'";
        }
//        else{
//            List<XsChilddictionary> list = baseTagDAO.findChilds(Contstants.ADUIT.ADUIT);
//            if(list != null){
//                for(XsChilddictionary child : list){
//                    if(child.getDataKey().equals(Contstants.ADUIT.YISHENHE)){
//                        carFixVo.setExamine(child.getChildId().toString());
//                        break;
//                    }
//                }
//                sql += " AND b.examine ='"+StringEscapeUtils.escapeSql(carFixVo.getExamine().trim())+"'";
//            }
//        }
        sql += " order by b.Install_code desc";
        List<Object[]> list = carFixDao.createSQLQuery(sql, carFixVo.getPage(), carFixVo.getRows());
        Json json = null;
        List<CarFixVo> car = null;
        if(list != null && list.size() > 0){
            json = new Json();
            car = new ArrayList<CarFixVo>();
            for(Object[] objs : list){
               CarFixVo carFixVo_ = new CarFixVo();
               carFixVo_.setInstall_id(objs[0] != null ? objs[0].toString() : null);
               carFixVo_.setInstall_code(objs[1] != null ? objs[1].toString() : null);
               carFixVo_.setSun_money(objs[2] != null ? objs[2].toString() : null);
               
               
               if(objs[3] != null){
                   carFixVo_.setFinish_state(objs[3] != null ? objs[3].toString() : null);
                   List<Object[]> vlist = carFixDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[3].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                            carFixVo_.setFinish_stateKey(vobjs[0] != null ? vobjs[0].toString() : null);
                            carFixVo_.setFinish_stateName(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               if(objs[4] != null){
                   carFixVo_.setExamine(objs[4] != null ? objs[4].toString() : null);
                   List<Object[]> vlist = carFixDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[4].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                            carFixVo_.setExamineKey(vobjs[0] != null ? vobjs[0].toString() : null);
                            carFixVo_.setExamineName(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               if(objs[5] != null){
                   List<Object[]> vlist = carFixDao.createSQLQuery("SELECT STF_ID, STF_NAME FROM bas_stuff WHERE STF_ID="+objs[5].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                            carFixVo_.setApplySTF_ID(vobjs[0] != null ? vobjs[0].toString() : null);
                            carFixVo_.setApplySTF_NAME(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               carFixVo_.setApplyDate(objs[6] != null ? objs[6].toString() : null);
               carFixVo_.setXs_car_id(objs[7] != null ? objs[7].toString() : null);
               carFixVo_.setXs_car_licensePlate(objs[8] != null ? objs[8].toString() : null);
               carFixVo_.setXs_car_vin_number(objs[9] != null ? objs[9].toString() : null);
               carFixVo_.setXs_car_ocn(objs[10] != null ? objs[10].toString() : null);
               if(objs[11] != null){
                   List<Object[]> vlist = carFixDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[11].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                            carFixVo_.setXs_car_color(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               if(objs[12] != null){
                   List<Object[]> vlist = carFixDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[12].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                            carFixVo_.setXs_car_brand(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               if(objs[13] != null){
                   List vlist = carFixDao.createSQLQuery("SELECT xs_model_name FROM xs_car_model WHERE xs_model_id="+objs[13]);
                   if(vlist != null)
                       carFixVo_.setXs_car_model_id(vlist.get(0).toString());
               }
               carFixVo_.setXs_car_motor_number(objs[14] != null ? objs[14].toString() : null);
               carFixVo_.setXs_car_code(objs[15] != null ? objs[15].toString() : null);
               car.add(carFixVo_);
            }
            json.setTotal(carFixDao.getCountBySQL(sql));
            json.setRows(car);
        }
        return json;
    }
	
    public Json getCarFixDetail(CarFixVo carFixVo) throws Exception
    {
        Json json = null;
        List<CarFixVo> carinstallDetail = null;
        String hql = " FROM XsSellCarinstallDetail where xsSellCarinstall.installId = "+carFixVo.getInstall_id();
        List<Object> list = carFixDao.find(hql, carFixVo.getPage(), carFixVo.getRows());
        if(list != null && list.size() > 0){
            json = new Json();
            carinstallDetail = new ArrayList<CarFixVo>();
            for(Object obj : list){
                XsSellCarinstallDetail carDetail = (XsSellCarinstallDetail)obj;
                CarFixVo carFixVo_ = new CarFixVo();
                carFixVo_.setDetailId(carDetail.getDetailId().toString());
                carFixVo_.setInstall_id(carDetail.getXsSellCarinstall().getInstallId().toString());
                carFixVo_.setPartsId(carDetail.getPartsId().toString());
                carFixVo_.setPartsName(carDetail.getPartsName());
                carFixVo_.setPartsRepairPrice(carDetail.getPaetsSellMoney().toString());
                carFixVo_.setParts_case_money(carDetail.getPaetsCaseMoney().toString());
                carinstallDetail.add(carFixVo_);
            }
            json.setTotal(carFixDao.getCount(hql));
            json.setRows(carinstallDetail);
        }
        return json;
    }

    public String findDefaulePartStore(CarFixVo carFixVo) throws Exception
    {
        BasCompanyInformationSet info = BasCompanyInformationSetService.getBasCompanyInformationSet(Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.SELLADDPORT,carFixVo.getEnterpriseId());
        return info.getCiValue();
    }

    //保存加装配件信息
    public void save(CarFixVo carFixVo, BasUsers user) throws Exception
    {
        double money = 0.0d;
        CarFixVo carFixVo_ = new CarFixVo();
        if(carFixVo.getBaseInfo() != null && !carFixVo.getBaseInfo().equals("")){
            JSONObject jsVehicle = JSON.parseObject(carFixVo.getBaseInfo());
            carFixVo_ = JSON.parseObject(jsVehicle.get("baseInfo").toString(),CarFixVo.class);
        }
        BasCompanyInformationSet info = BasCompanyInformationSetService.getBasCompanyInformationSet(Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.SELLADDPORT,carFixVo.getEnterpriseId());
        carFixVo_.setStoreId(info.getCiValue());
        
        if(carFixVo.getConfigParts() != null && !carFixVo.getConfigParts().equals("")){
            JSONObject jsVehicle = JSON.parseObject(carFixVo.getConfigParts());
            List<CarFixVo> insertedList = JSON.parseArray(jsVehicle.get("inserted").toString(),CarFixVo.class);
            List<CarFixVo> deletedList = JSON.parseArray(jsVehicle.get("deleted").toString(),CarFixVo.class);
            List<CarFixVo> updatedList = JSON.parseArray(jsVehicle.get("updated").toString(),CarFixVo.class);
            
            int Install_id = 0;
            for(CarFixVo vo : insertedList){
                if(carFixVo_.getInstall_id() != null){
                	Install_id = Integer.parseInt(carFixVo_.getInstall_id());
                }
            	String PartsId = vo.getPartsId() != null ? vo.getPartsId() : null;
                String partsName = vo.getPartsName();
                Double PaetsCaseMoney = vo.getParts_case_money() != null ? Double.parseDouble(vo.getParts_case_money()) : 0.0d;
                Double PaetsSellMoney = vo.getPartsRepairPrice() != null ? Double.parseDouble(vo.getPartsRepairPrice()) : 0.0d;
                Double PartsAmount = vo.getPartsAmount() != null ? Double.parseDouble(vo.getPartsAmount()) : 0.0d;
                Float PartsNum = vo.getPartsNum() != null ? Float.parseFloat(vo.getPartsNum()) : 0.0f;
                int StoreId = carFixVo_.getStoreId() != null && !"".equals(carFixVo_.getStoreId()) ? Integer.parseInt(carFixVo_.getStoreId()) : null;
                int PunitId = vo.getPunitId()  != null && !"".equals(vo.getPunitId()) ? Integer.parseInt(vo.getPunitId()) : null;
                StringBuilder sql = new StringBuilder("");
            	sql.append("INSERT INTO xs_sell_carinstall_detail (" );
                sql.append("	Install_id," );
                sql.append("	parts_id," );
                sql.append("	parts_name," );
                sql.append("	paets_case_money," );
                sql.append("	paets_sell_money," );
                sql.append("	partsNum," );
                sql.append("	partsAmount," );
                sql.append("	remark," );
                sql.append("	punitId," );
                sql.append("	storeId" );
                sql.append(" 	) VALUES (" );
                sql.append("	'"+Install_id+"'," );
                sql.append("	'"+PartsId+"'," );
                sql.append("	'"+partsName+"'," );
                sql.append("	'"+PaetsCaseMoney+"'," );
                sql.append("	'"+PaetsSellMoney+"'," );
                sql.append("	'"+PartsAmount+"'," );
                sql.append("	'"+PartsNum+"'," );
                sql.append("	'"+vo.getRemark()+"'," );
                sql.append("	'"+PunitId+"'," );
                sql.append("	'"+StoreId+"'" );
                sql.append("	)" );
               
               carFixDao.createSQLQueryOutFind(sql.toString());
                money += Double.parseDouble(vo.getPartsAmount());
            }
            for(CarFixVo vo : deletedList){
                carFixDao.delete(getXsSellCarinstallDetail(vo,carFixVo_));
            }
            for(CarFixVo vo : updatedList){
                carFixDao.saveOrUpdate(getXsSellCarinstallDetail(vo,carFixVo_));
                money += Double.parseDouble(vo.getPartsAmount());
            }
        }
        if(carFixVo.getConfigItem() != null && !carFixVo.getConfigItem().equals("")){
            JSONObject jsVehicle = JSON.parseObject(carFixVo.getConfigItem());
            List<CarFixVo> insertedList = JSON.parseArray(jsVehicle.get("inserted").toString(),CarFixVo.class);
            List<CarFixVo> deletedList = JSON.parseArray(jsVehicle.get("deleted").toString(),CarFixVo.class);
            List<CarFixVo> updatedList = JSON.parseArray(jsVehicle.get("updated").toString(),CarFixVo.class);
            
            for(CarFixVo vo : insertedList){
                carFixDao.save(getXsSellCarinstallAddItem(vo,carFixVo_));
                money += Double.parseDouble(vo.getItemMoney());
            }
            for(CarFixVo vo : deletedList){
                carFixDao.delete(getXsSellCarinstallAddItem(vo,carFixVo_));
            }
            for(CarFixVo vo : updatedList){
                carFixDao.saveOrUpdate(getXsSellCarinstallAddItem(vo,carFixVo_));
                money += Double.parseDouble(vo.getItemMoney());
            }
        }
        String sql = "UPDATE xs_sell_carinstall SET Install_person = '"+user.getBasStuff().getStfId()+"', Install_date = '"+FormatTime.date2Str(new Date(), FormatTime.DEFAULT_FORMAT)+"' ,sun_money = "+money+" WHERE Install_id = "+carFixVo_.getInstall_id();
        carFixDao.createSQLQueryOutFind(sql);
    }
    
    private XsSellCarinstallDetail getXsSellCarinstallDetail(CarFixVo vo, CarFixVo vo_) throws Exception{
        XsSellCarinstallDetail detail = new XsSellCarinstallDetail();
        detail.setDetailId(vo.getDetailId() != null ? Integer.parseInt(vo.getDetailId()) : null);
        XsSellCarinstall xsSellCarinstall = new XsSellCarinstall();
        if(vo_.getInstall_id() != null){
            xsSellCarinstall.setInstallId(Integer.parseInt(vo_.getInstall_id()));
        }
        detail.setXsSellCarinstall(xsSellCarinstall);
        detail.setPartsId(vo.getPartsId() != null ? vo.getPartsId() : null);
        detail.setPartsName(vo.getPartsName());
        detail.setPaetsCaseMoney(vo.getParts_case_money() != null ? Double.parseDouble(vo.getParts_case_money()) : 0.0d);
        detail.setPaetsSellMoney(vo.getPartsRepairPrice() != null ? Double.parseDouble(vo.getPartsRepairPrice()) : 0.0d);
        detail.setPartsAmount(vo.getPartsAmount() != null ? Double.parseDouble(vo.getPartsAmount()) : 0.0d);
        detail.setPartsNum(vo.getPartsNum() != null ? Float.parseFloat(vo.getPartsNum()) : 0.0f);
        detail.setStoreId(vo_.getStoreId() != null && !"".equals(vo_.getStoreId()) ? Integer.parseInt(vo_.getStoreId()) : null);
        detail.setPunitId(vo.getPunitId()  != null && !"".equals(vo.getPunitId()) ? Integer.parseInt(vo.getPunitId()) : null);
        Set<XsSellCarinstallDetail> xsSellCarinstallDetails = new HashSet<XsSellCarinstallDetail>();
        xsSellCarinstallDetails.add(detail);
        xsSellCarinstall.setXsSellCarinstallDetails(xsSellCarinstallDetails);
      return detail;
    }
    
    private XsSellCarinstallAddItem getXsSellCarinstallAddItem(CarFixVo vo, CarFixVo vo_){
        XsSellCarinstallAddItem item = new XsSellCarinstallAddItem();
        item.setInstallItemId(vo.getInstallItemId() != null ? Integer.parseInt(vo.getInstallItemId()) : null);
        if(vo_.getInstall_id() != null){
            XsSellCarinstall xsSellCarinstall = new XsSellCarinstall();
            xsSellCarinstall.setInstallId(Integer.parseInt(vo_.getInstall_id()));
            item.setXsSellCarinstall(xsSellCarinstall);
        }
        item.setItemId(vo.getItemId() != null ? Integer.parseInt(vo.getItemId()) : null);
        item.setItemName(vo.getItemName());
        item.setItemCose(Double.parseDouble(vo.getItemCost()));
        item.setItemMoney(Double.parseDouble(vo.getItemMoney()));
        item.setRemark(vo.getItemRemark());
        return item;
    }

    //根据加装单号获取明细信息
    public Json findPart(CarFixVo carFixVo) throws Exception
    {
        Json json = null;
        List<CarFixVo> list_ = null;
        BasCompanyInformationSet info = BasCompanyInformationSetService.getBasCompanyInformationSet(Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.SELLADDPORT,carFixVo.getEnterpriseId());
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT DISTINCT b.detail_id,a.Install_id,b.parts_id, b.parts_name, f.PUNIT_ID, f.PUNIT_NAME, b.partsNum, b.paets_sell_money, b.partsAmount, g.PARTS_NOW_COUNT ");
        sql.append("FROM   xs_sell_carinstall a,xs_sell_carinstall_detail b,frt_parts c, st_storage_item d, st_goods_storage e, bas_parts_unit f,parts_change_price g ");
        sql.append("WHERE  a.Install_id = b.Install_id AND b.parts_id = c.PARTS_ID AND b.parts_id = d.PARTS_ID AND d.STORAGE_ID = e.STORAGE_ID  AND c.PUNIT_NAME = f.PUNIT_ID AND g.PARTS_ID = b.parts_id ");
        sql.append("AND g.STORE_ID=e.STORE_ID AND e.STORE_ID = "+info.getCiValue()+" AND a.Install_id = "+carFixVo.getInstall_id());
        
        List<Object[]> list = carFixDao.createSQLQuery(sql.toString(), carFixVo.getPage(), carFixVo.getRows());
        if(list != null &&  list.size() > 0){
            json = new Json();
            list_ = new ArrayList<CarFixVo>();
            for(Object[] objs : list){
                CarFixVo carFixVo_ = new CarFixVo();
                carFixVo_.setDetailId(objs[0] != null ? objs[0].toString() : null);
                //carFixVo_.setOutId(objs[0] != null ? objs[0].toString() : null);
                carFixVo_.setInstall_id(objs[1] != null ? objs[1].toString() : null);
                carFixVo_.setPartsId(objs[2] != null ? objs[2].toString() : null);
                carFixVo_.setPartsName(objs[3] != null ? objs[3].toString() : null);
                carFixVo_.setPunitId(objs[4] != null ? objs[4].toString() : null);
                carFixVo_.setPunitName(objs[5] != null ? objs[5].toString() : null);
                carFixVo_.setPartsNum(objs[6] != null ? objs[6].toString() : null);
                carFixVo_.setPartsRepairPrice(objs[7] != null ? objs[7].toString() : null);
                carFixVo_.setPartsAmount(objs[8] != null ? objs[8].toString() : null);
                carFixVo_.setPartsNowCount(objs[9] != null ? objs[9].toString() : null);
                list_.add(carFixVo_);
            }
            json.setTotal(carFixDao.getCountBySQL(sql.toString()));
            json.setRows(list_);
        }
        return json;
    }
    
    public Json findItem(CarFixVo carFixVo) throws Exception
    {
        Json json = null;
        List<CarFixVo> list_ = null;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.Install_id, b.INSTALLITEM_ID, c.ITEM_ID, c.ITEM_NAME, b.ITEM_COST, b.ITEM_MONEY ");
        sql.append("FROM xs_sell_carinstall a, xs_sell_add_item b, frt_add_item c ");
        sql.append("WHERE a.Install_id = b.INSTALL_ID AND b.ITEM_ID = c.ITEM_ID AND a.Install_id = "+carFixVo.getInstall_id());
        
        List<Object[]> list = carFixDao.createSQLQuery(sql.toString(), carFixVo.getPage(), carFixVo.getRows());
        if(list != null &&  list.size() > 0){
            json = new Json();
            list_ = new ArrayList<CarFixVo>();
            for(Object[] objs : list){
                CarFixVo carFixVo_ = new CarFixVo();
                carFixVo_.setInstall_id(objs[0] != null ? objs[0].toString() : null);
                //carFixVo_.setOutProjectId(objs[0] != null ? objs[0].toString() : null);
                carFixVo_.setInstallItemId(objs[1] != null ? objs[1].toString() : null);
                carFixVo_.setItemId(objs[2] != null ? objs[2].toString() : null);
                carFixVo_.setItemName(objs[3] != null ? objs[3].toString() : null);
                carFixVo_.setItemCost(objs[4] != null ? objs[4].toString() : null);
                carFixVo_.setItemMoney(objs[5] != null ? objs[5].toString() : null);
                list_.add(carFixVo_);
            }
            json.setTotal(carFixDao.getCountBySQL(sql.toString()));
            json.setRows(list_);
        }
        return json;
    }
    
    public void updateCarFixFinishState(CarFixVo carFixVo) throws Exception
    {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE xs_sell_carinstall SET ");
        if(carFixVo.getFinish_stateKey() != null && !carFixVo.getFinish_stateKey().equals("")){
            String id = null;
            List<XsChilddictionary> list2 = baseTagDAO.findChilds(Contstants.FINISHWORK.FINISHWORK,null,carFixVo.getEnterpriseId());
            if(list2 != null){
                for(XsChilddictionary child : list2){
                    if(child.getDataKey().equals(carFixVo.getFinish_stateKey())){
                        id = child.getChildId().toString();
                        break;
                    }
                }
            }
            sql.append("finish_state = '"+id+"' WHERE Install_id = "+carFixVo.getInstall_id());
        }
        carFixDao.createSQLQueryOutFind(sql.toString());
    }

    public void updateCarFixAmount(CarFixVo carFixVo) throws Exception
    {
        StringBuffer sql = new StringBuffer();
        if(carFixVo.getSun_money() != null && !carFixVo.getSun_money().equals("")){
            sql.append("UPDATE xs_sell_carinstall SET  sun_money = '"+carFixVo.getSun_money()+"' WHERE Install_id = "+carFixVo.getInstall_id());
            carFixDao.createSQLQueryOutFind(sql.toString());
        }
    }
    
    /**
     * 加装审核
     */
    public Msg  updateCarFixExamineState(CarFixVo carFixVo) throws Exception
    {
    	Msg msg=new Msg();
    	XsSellCarinstall fix=(XsSellCarinstall) carFixDao.get(" from XsSellCarinstall where installId="+carFixVo.getInstall_id());
       int examine=baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.YISHENHE,carFixVo.getEnterpriseId());
    	if(fix!=null){
        	if(fix.getSellCarinstallExamine().getChildId()!=examine){
        		StringBuffer sql = new StringBuffer();
                sql.append("UPDATE xs_sell_carinstall SET ");
                if(carFixVo.getExamineKey() != null && !carFixVo.getExamineKey().equals("")){
                    String id = null;
                    List<XsChilddictionary> list2 = baseTagDAO.findChilds(Contstants.ADUIT.ADUIT,null,carFixVo.getEnterpriseId());
                    if(list2 != null){
                        for(XsChilddictionary child : list2){
                            if(child.getDataKey().equals(carFixVo.getExamineKey())){
                                id = child.getChildId().toString();
                                break;
                            }
                        }
                    }
                    sql.append("examine = '"+id+"' WHERE Install_id = "+carFixVo.getInstall_id());
                }
                carFixDao.createSQLQueryOutFind(sql.toString());
                msg.setMsg("审核该加装单成功！");
    			msg.setSuccess(true);
        	}else{
        		msg.setMsg("该加装单已审核，不可重复审核！");
    			msg.setSuccess(false);
        	}
        }else{
    		msg.setMsg("该加装单不存在！");
			msg.setSuccess(false);
    	}
    	
        return msg;
    }

	
	public Double totemoney(CarFixVo carFixVo) throws Exception {
        List<CarFixVo> list1 = null;
        List<CarFixVo> list2 = null;
        String items = carFixVo.getConfigItem();
        String parts = carFixVo.getConfigParts();
        if (parts != null && parts.length() > 0)
        {
        	JSONObject jsParts = JSON.parseObject(parts);
        	list1 = JSON.parseArray(jsParts.get("rows").toString(),
        			CarFixVo.class);
        }
        if (items != null && items.length() > 0)
        {
            JSONObject jsItems = JSON.parseObject(items);
            list2 = JSON.parseArray(jsItems.get("rows").toString(),
            		CarFixVo.class);
        }
        double sumMoney = 0;
        if (list1 != null && list1.size() > 0)
            for (CarFixVo carFixVo2 : list1) {
            	if(carFixVo2.getPartsAmount()!=null&&carFixVo2.getPartsAmount().length()>0)
            		sumMoney+=Double.parseDouble(carFixVo2.getPartsAmount());
			}
        if (list2 != null && list2.size() > 0)
            for (CarFixVo carFixVo2 : list2) {
            	if(carFixVo2.getItemMoney()!=null&&carFixVo2.getItemMoney().length()>0)
            		sumMoney+=Double.parseDouble(carFixVo2.getItemMoney());
			}
        return sumMoney;
	}
    
}