package com.syuesoft.sell.sellwork.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.StSellOrderitem;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellCarinstall;
import com.syuesoft.sell.model.XsSellOut;
import com.syuesoft.sell.model.XsSellProjectOut;
import com.syuesoft.sell.sellwork.dao.CarSellOutDao;
import com.syuesoft.sell.sellwork.service.CarSellOutService;
import com.syuesoft.sell.sellwork.vo.CarFixVo;
import com.syuesoft.st.service.StSellOrderService;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;

@Service("carSellOutServiceImpl")
public class CarSellOutServiceImpl extends BaseLogServiceImpl implements
		CarSellOutService {

	@Resource
	private CarSellOutDao carSellOutDao;
	@Resource
    private BaseTagDAO baseTagDAO;
	@Resource
    private BasCompanyInformationSetService BasCompanyInformationSetService;
	@Resource
    private StSellOrderService stSellOrderService;
	
    public Json getSellOut(CarFixVo carFixVo) throws Exception
    {
        Json json = new Json();
        List<CarFixVo> car = new ArrayList<CarFixVo>();
        String sql = "SELECT a.out_code, a.Install_id, b.Install_code, b.xs_car_id," +
        		" b.out_person, b.out_date, b.sun_money, c.xs_car_code, c.xs_car_brand, " +
        		"c.xs_car_model_id, c.xs_car_licensePlate, c.xs_car_ocn, c.xs_car_vin_number, c.xs_car_color, c.xs_car_InteriorColor, c.xs_car_motor_number "+
                     "FROM xs_sell_carinstallout a, xs_sell_carinstall b, " +
                     "xs_car_info c WHERE a.Install_id = b.Install_id AND b.xs_car_id = c.xs_car_id ";
        if(carFixVo.getEnterpriseId()!= null && !"".equals(carFixVo.getEnterpriseId())){
            sql += " AND b.enterprise_id  ='"+carFixVo.getEnterpriseId()+"' ";
        }
        if(carFixVo.getOutCode() != null && !"".equals(carFixVo.getOutCode())){
            sql += " AND a.out_code like '%"+StringEscapeUtils.escapeSql(carFixVo.getOutCode().trim())+"%' ";
        }
        if(carFixVo.getInstall_code() != null && !"".equals(carFixVo.getInstall_code())){
            sql += " AND b.Install_code like '%"+StringEscapeUtils.escapeSql(carFixVo.getInstall_code().trim())+"%' ";
        }
        if(carFixVo.getXs_car_code() != null && !"".equals(carFixVo.getXs_car_code())){
            sql += " AND c.xs_car_code like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_code().trim())+"%' ";
        }
        if(carFixVo.getXs_car_brand() != null && !"".equals(carFixVo.getXs_car_brand())){
            sql += " AND c.xs_car_brand = '"+carFixVo.getXs_car_brand()+"' ";
        }
        if(carFixVo.getXs_car_model_id() != null && !"".equals(carFixVo.getXs_car_model_id())){
            sql += " AND c.xs_car_model_id = '"+carFixVo.getXs_car_model_id()+"' ";
        }
        if(carFixVo.getXs_car_vin_number() != null && !"".equals(carFixVo.getXs_car_vin_number())){
            sql += " AND c.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_vin_number().trim())+"%' ";
        }
        if(carFixVo.getXs_car_ocn() != null && !"".equals(carFixVo.getXs_car_ocn())){
            sql += " AND c.xs_car_ocn like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_ocn().trim())+"%' ";
        }
        sql += " GROUP BY a.Install_id desc";
        List<Object[]> list = carSellOutDao.createSQLQuery(sql, carFixVo.getPage(), carFixVo.getRows());
        if(list != null && list.size() > 0){
            for(Object[] objs : list){
               CarFixVo carFixVo_ = new CarFixVo();
               carFixVo_.setOutCode(objs[0] != null ? objs[0].toString() : null);
               carFixVo_.setInstall_id(objs[1] != null ? objs[1].toString() : null);
               carFixVo_.setInstall_code(objs[2] != null ? objs[2].toString() : null);
               carFixVo_.setXs_car_id(objs[3] != null ? objs[3].toString() : null);
               if(objs[4] != null){
                   List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT STF_ID, STF_NAME FROM bas_stuff WHERE STF_ID="+objs[4].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                           carFixVo_.setOutSTF_ID(vobjs[0] != null ? vobjs[0].toString() : null);
                           carFixVo_.setOutSTF_NAME(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               carFixVo_.setOutDate(objs[5] != null ? objs[5].toString() : null);
               carFixVo_.setSun_money(objs[6] != null ? objs[6].toString() : null);
               carFixVo_.setXs_car_code(objs[7] != null ? objs[7].toString() : null);
               if(objs[8] != null){
                   List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[8].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                           carFixVo_.setXs_car_brand(vobjs[0] != null ? vobjs[0].toString() : null);
                           carFixVo_.setXs_car_brandName(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               if(objs[9] != null){
                   carFixVo_.setXs_car_model_id(objs[9].toString());
                   List<?> vlist = carSellOutDao.createSQLQuery("SELECT xs_model_name FROM xs_car_model WHERE xs_model_id="+objs[9].toString()+"");
                   if(vlist != null)
                       carFixVo_.setXs_car_modelName(vlist!=null ? vlist.get(0)+"" :"");
               }
               carFixVo_.setXs_car_licensePlate(objs[10] != null ? objs[10].toString() : null);
               carFixVo_.setXs_car_ocn(objs[11] != null ? objs[11].toString() : null);
               carFixVo_.setXs_car_vin_number(objs[12] != null ? objs[12].toString() : null);
               if(objs[13] != null){
                   List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[13].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                           carFixVo_.setXs_car_color(vobjs[0] != null ? vobjs[0].toString() : null);
                           carFixVo_.setXs_car_colorName(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               if(objs[14] != null){
                   List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[14].toString()+"");
                   if(vlist != null){
                       for(Object[] vobjs : vlist){
                           carFixVo_.setCarInteriorColor(vobjs[0] != null ? vobjs[0].toString() : null);
                           carFixVo_.setCarInteriorColorName(vobjs[1] != null ? vobjs[1].toString() : null);
                       }
                   }
               }
               carFixVo_.setXs_car_motor_number(objs[15] != null ? objs[15].toString() : null);
               car.add(carFixVo_);
            }
        }
        json.setTotal(carSellOutDao.getCountBySQL(sql));
        json.setRows(car);
        return json;
    }

    public Json getOutInstall(CarFixVo carFixVo) throws Exception
    {
        List<XsChilddictionary> list2 = baseTagDAO.findChilds(Contstants.ADUIT.ADUIT,null,carFixVo.getEnterpriseId());
        if(list2 != null){
            for(XsChilddictionary child : list2){
                if(child.getDataKey().equals(Contstants.ADUIT.YISHENHE)){
                    carFixVo.setExamine(child.getChildId().toString());
                    break;
                }
            }
        }
        
        String sql = "SELECT b.Install_id,b.Install_code,b.sun_money,b.finish_state,b.examine,b.Install_applyperson,b.Install_applydate,b.xs_car_id,a.xs_car_licensePlate,a.xs_car_vin_number,a.xs_car_ocn,a.xs_car_color,a.xs_car_brand,a.xs_car_model_id,a.xs_car_motor_number,a.xs_car_code, " +
        		"a.xs_car_InteriorColor,b.enterprise_id"+
                " FROM xs_sell_carInstall b,xs_car_info a WHERE b.xs_car_id = a.xs_car_id AND b.out_person IS NULL AND b.out_date IS NULL AND b.examine = '"+carFixVo.getExamine()+"' "; 
        if(carFixVo.getEnterpriseId() != null && !"".equals(carFixVo.getEnterpriseId())){
            sql += " AND b.enterprise_id  ='"+carFixVo.getEnterpriseId()+"'";
       }
        if(carFixVo.getInstall_id() != null && !"".equals(carFixVo.getInstall_id())){
             sql += " AND b.Install_id ='"+carFixVo.getInstall_id()+"'";
        }
        if(carFixVo.getXs_car_vin_number()!=null && !carFixVo.getXs_car_vin_number().equals("")){
             sql += " AND a.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_vin_number().trim())+"%'";
        }
        if(carFixVo.getXs_car_ocn()!=null && !carFixVo.getXs_car_ocn().equals("")){
             sql += " AND a.xs_car_ocn like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_ocn().trim())+"%'";
        }
        
        List<Object[]> list = carSellOutDao.createSQLQuery(sql, carFixVo.getPage(), carFixVo.getRows());
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
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[3].toString()+"");
              if(vlist != null){
                  for(Object[] vobjs : vlist){
                       carFixVo_.setFinish_stateKey(vobjs[0] != null ? vobjs[0].toString() : null);
                       carFixVo_.setFinish_stateName(vobjs[1] != null ? vobjs[1].toString() : null);
                  }
              }
          }
          if(objs[4] != null){
              carFixVo_.setExamine(objs[4] != null ? objs[4].toString() : null);
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[4].toString()+"");
              if(vlist != null){
                  for(Object[] vobjs : vlist){
                       carFixVo_.setExamineKey(vobjs[0] != null ? vobjs[0].toString() : null);
                       carFixVo_.setExamineName(vobjs[1] != null ? vobjs[1].toString() : null);
                  }
              }
          }
          if(objs[5] != null){
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT STF_ID, STF_NAME FROM bas_stuff WHERE STF_ID="+objs[5].toString()+"");
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
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[11].toString()+"");
              if(vlist != null){
                  for(Object[] vobjs : vlist){
                       carFixVo_.setXs_car_color(vobjs[0] != null ? vobjs[0].toString() : null);
                       carFixVo_.setXs_car_colorName(vobjs[1] != null ? vobjs[1].toString() : null);
                  }
              }
          }
          if(objs[12] != null){
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[12].toString()+"");
              if(vlist != null){
                  for(Object[] vobjs : vlist){
                       carFixVo_.setXs_car_brand(vobjs[0] != null ? vobjs[0].toString() : null);
                       carFixVo_.setXs_car_brandName(vobjs[1] != null ? vobjs[1].toString() : null);
                  }
              }
          }
          if(objs[13] != null){
              carFixVo_.setXs_car_model_id(objs[13].toString());
              List vlist = carSellOutDao.createSQLQuery("SELECT xs_model_name FROM xs_car_model WHERE xs_model_id="+objs[13]);
              if(vlist != null)
                  carFixVo_.setXs_car_modelName(vlist.get(0).toString());
          }
          carFixVo_.setXs_car_motor_number(objs[14] != null ? objs[14].toString() : null);
          carFixVo_.setXs_car_code(objs[15] != null ? objs[15].toString() : null);
          if(objs[16] != null){
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[16].toString()+"");
              if(vlist != null){
                  for(Object[] vobjs : vlist){
                      carFixVo_.setCarInteriorColor(vobjs[0] != null ? vobjs[0].toString() : null);
                      carFixVo_.setCarInteriorColorName(vobjs[1] != null ? vobjs[1].toString() : null);
                  }
              }
          }
          carFixVo_.setEnterpriseId(objs[17] != null ? Integer.parseInt(objs[17].toString()) : null);
          car.add(carFixVo_);
        }
        json.setTotal(carSellOutDao.getCountBySQL(sql));
        json.setRows(car);
        }
        return json;
    }
    //获取加装明细
    public Json getOutInstallDetail(CarFixVo carFixVo) throws Exception
    {
        List<XsChilddictionary> list2 = baseTagDAO.findChilds(Contstants.ADUIT.ADUIT,null,carFixVo.getEnterpriseId());
        if(list2 != null){
            for(XsChilddictionary child : list2){
                if(child.getDataKey().equals(Contstants.ADUIT.YISHENHE)){
                    carFixVo.setExamine(child.getChildId().toString());
                    break;
                }
            }
        }
        
        String sql = "SELECT b.Install_id,b.Install_code,b.sun_money,b.finish_state,b.examine,b.Install_applyperson,b.Install_applydate,b.xs_car_id,a.xs_car_licensePlate,a.xs_car_vin_number,a.xs_car_ocn,a.xs_car_color,a.xs_car_brand,a.xs_car_model_id,a.xs_car_motor_number,a.xs_car_code, a.xs_car_InteriorColor, "+
                 " c.out_id, c.out_code, b.out_person, b.out_date "+
                 " FROM xs_sell_carInstall b, xs_car_info a, (SELECT out_id, Install_id, out_code FROM xs_sell_carinstallout GROUP BY Install_id ) c "+
                 " WHERE b.xs_car_id = a.xs_car_id  AND b.Install_id = c.Install_id AND b.out_person IS NOT NULL AND b.out_date IS NOT NULL AND b.examine = '"+carFixVo.getExamine()+"' "; 
       
        if(carFixVo.getEnterpriseId() != null && !"".equals(carFixVo.getEnterpriseId())){
            sql += " AND b.enterprise_id  ='"+carFixVo.getEnterpriseId()+"'";
        }
        if(carFixVo.getInstall_id() != null && !"".equals(carFixVo.getInstall_id())){
             sql += " AND b.Install_id ='"+carFixVo.getInstall_id()+"'";
        }
        if(carFixVo.getXs_car_vin_number()!=null && !carFixVo.getXs_car_vin_number().equals("")){
             sql += " AND a.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_vin_number().trim())+"%'";
        }
        if(carFixVo.getXs_car_ocn()!=null && !carFixVo.getXs_car_ocn().equals("")){
             sql += " AND a.xs_car_ocn like '%"+StringEscapeUtils.escapeSql(carFixVo.getXs_car_ocn().trim())+"%'";
        }
        
        List<Object[]> list = carSellOutDao.createSQLQuery(sql);
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
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[3].toString()+"");
              if(vlist != null){
                  for(Object[] vobjs : vlist){
                       carFixVo_.setFinish_stateKey(vobjs[0] != null ? vobjs[0].toString() : null);
                       carFixVo_.setFinish_stateName(vobjs[1] != null ? vobjs[1].toString() : null);
                  }
              }
          }
          if(objs[4] != null){
              carFixVo_.setExamine(objs[4] != null ? objs[4].toString() : null);
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[4].toString()+"");
              if(vlist != null){
                  for(Object[] vobjs : vlist){
                       carFixVo_.setExamineKey(vobjs[0] != null ? vobjs[0].toString() : null);
                       carFixVo_.setExamineName(vobjs[1] != null ? vobjs[1].toString() : null);
                  }
              }
          }
          if(objs[5] != null){
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT STF_ID, STF_NAME FROM bas_stuff WHERE STF_ID="+objs[5].toString()+"");
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
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[11].toString()+"");
              if(vlist != null){
                  for(Object[] vobjs : vlist){
                       carFixVo_.setXs_car_color(vobjs[0] != null ? vobjs[0].toString() : null);
                       carFixVo_.setXs_car_colorName(vobjs[1] != null ? vobjs[1].toString() : null);
                  }
              }
          }
          if(objs[12] != null){
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[12].toString()+"");
              if(vlist != null){
                  for(Object[] vobjs : vlist){
                       carFixVo_.setXs_car_brand(vobjs[0] != null ? vobjs[0].toString() : null);
                       carFixVo_.setXs_car_brandName(vobjs[1] != null ? vobjs[1].toString() : null);
                  }
              }
          }
          if(objs[13] != null){
              carFixVo_.setXs_car_model_id(objs[13].toString());
              List vlist = carSellOutDao.createSQLQuery("SELECT xs_model_name FROM xs_car_model WHERE xs_model_id="+objs[13]);
              if(vlist != null)
                  carFixVo_.setXs_car_modelName(vlist.get(0).toString());
          }
          carFixVo_.setXs_car_motor_number(objs[14] != null ? objs[14].toString() : null);
          carFixVo_.setXs_car_code(objs[15] != null ? objs[15].toString() : null);
          if(objs[16] != null){
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT a.dataKey, a.datavalue FROM xs_childdictionary a WHERE a.child_id="+objs[16].toString()+"");
              if(vlist != null){
                  for(Object[] vobjs : vlist){
                      carFixVo_.setCarInteriorColor(vobjs[0] != null ? vobjs[0].toString() : null);
                      carFixVo_.setCarInteriorColorName(vobjs[1] != null ? vobjs[1].toString() : null);
                  }
              }
          }
          carFixVo_.setOutId(objs[17] != null ? objs[17].toString() : null);
          carFixVo_.setOutCode(objs[18] != null ? objs[18].toString() : null);
          if(objs[19] != null){
              List<Object[]> vlist = carSellOutDao.createSQLQuery("SELECT STF_ID, STF_NAME FROM bas_stuff WHERE STF_ID="+objs[19].toString()+"");
              if(vlist != null){
                  for(Object[] vobjs : vlist){
                       carFixVo_.setOutSTF_ID(vobjs[0] != null ? vobjs[0].toString() : null);
                       carFixVo_.setOutSTF_NAME(vobjs[1] != null ? vobjs[1].toString() : null);
                  }
              }
          }
          carFixVo_.setOutDate(objs[20] != null ? objs[20].toString() : null);
          car.add(carFixVo_);
        }
        json.setTotal(carSellOutDao.getCountBySQL(sql));
        json.setRows(car);
        }
        return json;
    }
    
    public Json getSellOutPart(CarFixVo carFixVo) throws Exception
    {
        Json json = null;
        List<CarFixVo> list_ = null;
        List<Object[]> list = null;
        BasCompanyInformationSet info = BasCompanyInformationSetService.getBasCompanyInformationSet(Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.SELLADDPORT,carFixVo.getEnterpriseId());
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT b.out_id,a.Install_id,b.parts_id, b.parts_name, f.PUNIT_ID, f.PUNIT_NAME, b.partsNum, b.paets_sell_money, b.partsAmount, g.PARTS_NOW_COUNT ");
        sql.append("FROM   xs_sell_carinstall a,xs_sell_carinstallout b,frt_parts c, st_storage_item d, st_goods_storage e, bas_parts_unit f,parts_change_price g ");
        sql.append("WHERE  a.Install_id = b.Install_id AND b.parts_id = c.PARTS_ID AND b.parts_id = d.PARTS_ID AND d.STORAGE_ID = e.STORAGE_ID  AND c.PUNIT_NAME = f.PUNIT_ID AND g.PARTS_ID = b.parts_id ");
        sql.append("AND g.STORE_ID=e.STORE_ID AND e.STORE_ID = "+info.getCiValue()+" AND a.Install_id = "+carFixVo.getInstall_id());
        if(carFixVo.getPage() != 0 && carFixVo.getRows() != 0){
            list = carSellOutDao.createSQLQuery(sql.toString(), carFixVo.getPage(), carFixVo.getRows());
        }else{
            list = carSellOutDao.createSQLQuery(sql.toString());
        }
        if(list != null &&  list.size() > 0){
            json = new Json();
            list_ = new ArrayList<CarFixVo>();
            for(Object[] objs : list){
                CarFixVo carFixVo_ = new CarFixVo();
                carFixVo_.setOutId(objs[0] != null ? objs[0].toString() : null);
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
            json.setTotal(carSellOutDao.getCountBySQL(sql.toString()));
            json.setRows(list_);
        }
        return json;
    }

    public Json getSellOutProject(CarFixVo carFixVo) throws Exception
    {
        Json json = null;
        List<CarFixVo> list_ = null;
        List<Object[]> list = null;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.Install_id, b.outProject_id, c.ITEM_ID, c.ITEM_NAME, b.ITEM_COST, b.ITEM_MONEY ");
        sql.append("FROM xs_sell_carinstall a, xs_sell_carinstallprojectout b, frt_add_item c ");
        sql.append("WHERE a.Install_id = b.INSTALL_ID AND b.ITEM_ID = c.ITEM_ID AND a.Install_id = "+carFixVo.getInstall_id());
        
        if(carFixVo.getPage() != 0 && carFixVo.getRows() != 0){
            list = carSellOutDao.createSQLQuery(sql.toString(), carFixVo.getPage(), carFixVo.getRows());
        }else{
            list = carSellOutDao.createSQLQuery(sql.toString());
        }
        if(list != null &&  list.size() > 0){
            json = new Json();
            list_ = new ArrayList<CarFixVo>();
            for(Object[] objs : list){
                CarFixVo carFixVo_ = new CarFixVo();
                carFixVo_.setInstall_id(objs[0] != null ? objs[0].toString() : null);
                carFixVo_.setOutProjectId(objs[1] != null ? objs[1].toString() : null);
                carFixVo_.setItemId(objs[2] != null ? objs[2].toString() : null);
                carFixVo_.setItemName(objs[3] != null ? objs[3].toString() : null);
                carFixVo_.setItemCost(objs[4] != null ? objs[4].toString() : null);
                carFixVo_.setItemMoney(objs[5] != null ? objs[5].toString() : null);
                list_.add(carFixVo_);
            }
            json.setTotal(carSellOutDao.getCountBySQL(sql.toString()));
            json.setRows(list_);
        }
        return json;
    }
    
    /**
     * 车辆加装出库新增
     */
    @Log( systemName="销售系统", moduleName="车辆加装出库",opertype="新增")
    public void saveOutInstall(CarFixVo carFixVo, BasUsers user) throws Exception
    {
        double money = 0.0d;
        CarFixVo carFixVo_ = new CarFixVo();
       
        if(carFixVo.getBaseInfo() != null && !carFixVo.getBaseInfo().equals("")){
            JSONObject jsVehicle = JSON.parseObject(carFixVo.getBaseInfo());
            carFixVo_ = JSON.parseObject(jsVehicle.get("baseInfo").toString(),CarFixVo.class);
        }
        BasCompanyInformationSet info = BasCompanyInformationSetService.getBasCompanyInformationSet(Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.SELLADDPORT,carFixVo.getEnterpriseId());
        carFixVo_.setStoreId(info.getCiValue());
        String outId = CreateID.createId("CarFixOutVo",  Contstants.SELL_BILLSDEPLOY.CARINSTALLOUT);
        if(carFixVo.getConfigParts() != null && !carFixVo.getConfigParts().equals("")&&!carFixVo.getConfigParts().equals("undefined")){
            JSONObject jsVehicle = JSON.parseObject(carFixVo.getConfigParts());
            List<CarFixVo> insertedList = JSON.parseArray(jsVehicle.get("inserted").toString(),CarFixVo.class);
            List<CarFixVo> deletedList = JSON.parseArray(jsVehicle.get("deleted").toString(),CarFixVo.class);
            List<CarFixVo> updatedList = JSON.parseArray(jsVehicle.get("updated").toString(),CarFixVo.class);
            
            if(insertedList != null && insertedList.size() > 0){
                for(CarFixVo vo : insertedList){
                    carSellOutDao.saveOrUpdate(getXsSellOut(vo, carFixVo_, outId));
                    money += Double.parseDouble(vo.getPartsAmount());
                    update(carFixVo_, false);
                }
            }
            if(deletedList != null && deletedList.size() > 0){
                for(CarFixVo vo : deletedList){
                    carSellOutDao.delete(getXsSellOut(vo, carFixVo_, outId));
                    update(carFixVo_, true);
                }
            }
            if(updatedList != null && updatedList.size() > 0){
                for(CarFixVo vo : updatedList){
                	vo.setEnterpriseId( carFixVo.getEnterpriseId());
                    carSellOutDao.saveOrUpdate(getXsSellOut(vo, carFixVo_, outId));
                    money += Double.parseDouble(vo.getPartsAmount());
                    double partCount = partCount(vo);
                    double partNum = Double.parseDouble(vo.getPartsNum());
                    if(partNum < partCount){
                        vo.setPartsNum(String.valueOf(partCount-partNum));
                        update(vo, true);
                    }else if(partNum > partCount){
                        vo.setPartsNum(String.valueOf(partNum-partCount));
                        update(vo, false);
                    }else{
                        update(vo, false);
                    }
                }
            }
        }
        if(carFixVo.getConfigItem() != null && !carFixVo.getConfigItem().equals("")){
            if(!carFixVo.getConfigItem().equals("-1")){
                JSONObject jsVehicle = JSON.parseObject(carFixVo.getConfigItem());
                List<CarFixVo> insertedList = JSON.parseArray(jsVehicle.get("inserted").toString(),CarFixVo.class);
                List<CarFixVo> deletedList = JSON.parseArray(jsVehicle.get("deleted").toString(),CarFixVo.class);
                List<CarFixVo> updatedList = JSON.parseArray(jsVehicle.get("updated").toString(),CarFixVo.class);
                
                if(insertedList != null && insertedList.size() > 0){
                    for(CarFixVo vo : insertedList){
                        carSellOutDao.saveOrUpdate(getXsSellProjectOut(vo, carFixVo_, outId));
                        money += Double.parseDouble(vo.getItemMoney());
                    }
                }
                if(deletedList != null && deletedList.size() > 0){
                    for(CarFixVo vo : deletedList){
                        carSellOutDao.delete(getXsSellProjectOut(vo, carFixVo_, outId));
                    }
                }
                if(updatedList != null && updatedList.size() > 0){
                    for(CarFixVo vo : updatedList){
                        carSellOutDao.saveOrUpdate(getXsSellProjectOut(vo, carFixVo_, outId));
                        money += Double.parseDouble(vo.getItemMoney());
                    }
                }
            }else{
                List<CarFixVo> list_ = findItem(carFixVo_);
                if(list_ != null &&list_.size() > 0){
                    for(CarFixVo vo : list_){
                        money += Double.parseDouble(vo.getItemMoney());
                        carSellOutDao.saveOrUpdate(getXsSellProjectOut(vo, carFixVo_, outId));
                    }
                }
            }
        }
        carFixVo_.setSTF_ID(carFixVo_.getSTF_ID() != null && !"".equals(carFixVo_.getSTF_ID()) ? carFixVo_.getSTF_ID() : user.getBasStuff().getStfId().toString());
        carFixVo_.setOutDate(carFixVo_.getOutDate() != null && !"".equals(carFixVo_.getOutDate()) ? carFixVo_.getOutDate() : FormatTime.date2Str(new Date(), FormatTime.DEFAULT_FORMAT));
        
        String sql = "UPDATE xs_sell_carinstall SET out_person = '"+carFixVo_.getSTF_ID()+"', out_date = '"+carFixVo_.getOutDate()+"', factSun_money = "+money+" WHERE Install_id = "+carFixVo_.getInstall_id();
        carSellOutDao.createSQLQueryOutFind(sql);
    }
    
    private XsSellOut getXsSellOut(CarFixVo vo, CarFixVo vo_, String outId ){
        XsSellOut detail = new XsSellOut();
        detail.setOutId(vo.getOutId() != null ? Integer.parseInt(vo.getOutId()) : null);
        if(vo_.getInstall_id() != null){
            XsSellCarinstall xsSellCarinstall = new XsSellCarinstall();
            xsSellCarinstall.setInstallId(Integer.parseInt(vo_.getInstall_id()));
            detail.setXsSellCarinstall(xsSellCarinstall);
        }
        detail.setOutCode(vo.getOutCode() != null && !"".equals(vo.getOutCode()) ? vo.getOutCode() : outId != null ? outId : null);
        detail.setPartsId(vo.getPartsId() != null ? vo.getPartsId() : null);
        detail.setPartsName(vo.getPartsName());
        detail.setPaetsCaseMoney(vo.getParts_case_money() != null ? Double.parseDouble(vo.getParts_case_money()) : 0.0d);
        detail.setPaetsSellMoney(vo.getPartsRepairPrice() != null ? Double.parseDouble(vo.getPartsRepairPrice()) : 0.0d);
        detail.setPartsAmount(vo.getPartsAmount() != null ? Double.parseDouble(vo.getPartsAmount()) : 0.0d);
        detail.setPartsNum(vo.getPartsNum() != null ? Float.parseFloat(vo.getPartsNum()) : 0.0f);
        detail.setStoreId(vo_.getStoreId() != null && !"".equals(vo_.getStoreId()) ? Integer.parseInt(vo_.getStoreId()) : null);
        detail.setPunitId(vo.getPunitId() != null && !"".equals(vo.getPunitId()) ? Integer.parseInt(vo.getPunitId()) : null);
        vo_.setPartsId(vo.getPartsId() != null ? vo.getPartsId() : null);
        vo_.setPartsNum(vo.getPartsNum() != null ? vo.getPartsNum() : "0.0f");
        return detail;
    }
    
    private XsSellProjectOut getXsSellProjectOut(CarFixVo vo, CarFixVo vo_, String outId){
        XsSellProjectOut item = new XsSellProjectOut();
        item.setOutItemId(vo.getOutProjectId() != null ? Integer.parseInt(vo.getOutProjectId()) : null);
        if(vo_.getInstall_id() != null){
            XsSellCarinstall xsSellCarinstall = new XsSellCarinstall();
            xsSellCarinstall.setInstallId(Integer.parseInt(vo_.getInstall_id()));
            item.setXsSellCarinstall(xsSellCarinstall);
        }
        item.setItemId(vo.getItemId() != null ? Integer.parseInt(vo.getItemId()) : null);
        item.setOutCode(vo.getOutCode() != null && !"".equals(vo.getOutCode()) ? vo.getOutCode() : outId != null ? outId : null);
        item.setItemName(vo.getItemName());
        item.setItemCose(Double.parseDouble(vo.getItemCost()));
        item.setItemMoney(Double.parseDouble(vo.getItemMoney()));
        item.setRemark(vo.getItemRemark());
        return item;
    }
    
    private List<CarFixVo> findItem(CarFixVo carFixVo) throws Exception
    {
        List<CarFixVo> list_ = null;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.Install_id, b.INSTALLITEM_ID, c.ITEM_ID, c.ITEM_NAME, b.ITEM_COST, b.ITEM_MONEY ");
        sql.append("FROM xs_sell_carinstall a, xs_sell_add_item b, frt_add_item c ");
        sql.append("WHERE a.Install_id = b.INSTALL_ID AND b.ITEM_ID = c.ITEM_ID AND a.Install_id = "+carFixVo.getInstall_id());
        
        List<Object[]> list = carSellOutDao.createSQLQuery(sql.toString());
        if(list != null &&  list.size() > 0){
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
        }
        return list_;
    }
    
    private void update(CarFixVo carFixVo_, boolean tag) throws Exception{
        BasCompanyInformationSet info = BasCompanyInformationSetService.getBasCompanyInformationSet(Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.SELLADDPORT,carFixVo_.getEnterpriseId());
        if(carFixVo_.getPartsNum()== null || carFixVo_.getPartsNum().equals("")){
            StringBuffer sqlpart = new StringBuffer();
            sqlpart.append("SELECT b.detail_id,a.Install_id,b.parts_id, b.parts_name, f.PUNIT_ID, f.PUNIT_NAME, b.partsNum, b.paets_sell_money, b.partsAmount, g.PARTS_NOW_COUNT ");
            sqlpart.append("FROM   xs_sell_carinstall a,xs_sell_carinstall_detail b,frt_parts c, st_storage_item d, st_goods_storage e, bas_parts_unit f,parts_change_price g ");
            sqlpart.append("WHERE  a.Install_id = b.Install_id AND b.parts_id = c.PARTS_ID AND b.parts_id = d.PARTS_ID AND d.STORAGE_ID = e.STORAGE_ID  AND c.PUNIT_NAME = f.PUNIT_ID AND g.PARTS_ID = b.parts_id ");
            sqlpart.append("AND g.STORE_ID=e.STORE_ID AND e.STORE_ID = "+info.getCiValue()+" AND a.Install_id = "+carFixVo_.getInstall_id());
            List<Object[]> list = carSellOutDao.createSQLQuery(sqlpart.toString());
            if(list != null &&  list.size() > 0){
                for(Object[] objs : list){
                    StSellOrderitem ssvo = new StSellOrderitem();
                    ssvo.setPartsId(objs[2] != null ? objs[2].toString() : null);
                    ssvo.setSelldCnt(objs[6] != null ? Double.parseDouble(objs[6].toString()) : null);
                    ssvo.setStoreId(Short.parseShort(info.getCiValue()));
                    stSellOrderService.changPartsNowCount(ssvo, tag);
                }
            }
        }else{
            StSellOrderitem ssvo = new StSellOrderitem();
            ssvo.setPartsId(carFixVo_.getPartsId());
            ssvo.setSelldCnt(Double.parseDouble(carFixVo_.getPartsNum()));
            ssvo.setStoreId(Short.parseShort(info.getCiValue()));
            stSellOrderService.changPartsNowCount(ssvo, tag);
        }
    }
    
    private double partCount(CarFixVo carFixVo) throws Exception{
        double partCount = 0.0d;
        StringBuffer sql = new StringBuffer();
        if(carFixVo.getOutId()!= null && !"".equals(carFixVo.getOutId())){
            sql.append("SELECT partsNum FROM xs_sell_carinstallout WHERE out_id = '"+carFixVo.getOutId()+"'");
        }
        if(carFixVo.getDetailId()!= null && !"".equals(carFixVo.getDetailId())){
            sql.append("SELECT partsNum FROM xs_sell_carinstall_detail WHERE detail_id = '"+carFixVo.getDetailId()+"'");
        }
        List<?> list = carSellOutDao.createSQLQuery(sql.toString());
        if(list != null &&  list.size() > 0){
            partCount = Double.parseDouble(list.get(0).toString());
        }
        return partCount;
    }

    @SuppressWarnings("unchecked")
    public void deleteInstall(CarFixVo carFixVo)
            throws Exception
    {
        CarFixVo carFixVo_ = new CarFixVo();
        if(carFixVo.getBaseInfo() != null && !carFixVo.getBaseInfo().equals("")){
            carFixVo_ = JSON.parseObject(carFixVo.getBaseInfo(),CarFixVo.class);
        }
        if(carFixVo.getConfigParts() != null && !carFixVo.getConfigParts().equals("")){
            if(!carFixVo.getConfigParts().equals("")){
                List<CarFixVo> deletedList = JSON.parseArray(carFixVo.getConfigParts(),CarFixVo.class);
                if(deletedList != null && deletedList.size() > 0){
                   delectParts(carFixVo_, deletedList);
                }
            }
        }else{
            Json json = getSellOutPart(carFixVo_);
            List<CarFixVo> list = (List<CarFixVo>) json.getRows();
            if(list != null && list.size() > 0){
                delectParts(carFixVo_, list);
            }
        }
        if(carFixVo.getConfigItem() != null && !carFixVo.getConfigItem().equals("")){
            if(!carFixVo.getConfigItem().equals("")){
                List<CarFixVo> deletedList = JSON.parseArray(carFixVo.getConfigItem(),CarFixVo.class);
                if(deletedList != null && deletedList.size() > 0){
                    delectItems(carFixVo_, deletedList);
                }
            }
        }else{
            Json json = getSellOutProject(carFixVo_);
            List<CarFixVo> list = (List<CarFixVo>) json.getRows();
            if(list != null && list.size() > 0){
                 delectItems(carFixVo_, list);
            }
        }
        String sql = "UPDATE xs_sell_carinstall SET out_person = null, out_date = null, factSun_money = 0.0 WHERE Install_id = "+carFixVo_.getInstall_id();
        carSellOutDao.createSQLQueryOutFind(sql);
    }
    
    private void delectParts(CarFixVo carFixVo_, List<CarFixVo> list) throws Exception{
        for(CarFixVo vo : list){
            carSellOutDao.delete(getXsSellOut(vo, carFixVo_, null));
            update(carFixVo_, true);
        }
    }
    
    private void delectItems(CarFixVo carFixVo_, List<CarFixVo> list) throws Exception{
        for(CarFixVo vo : list){
            carSellOutDao.delete(getXsSellProjectOut(vo, carFixVo_, null));
        }
    }
}