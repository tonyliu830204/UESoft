package com.syuesoft.print.serviceimpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.base.vo.BaseBeanVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.DownLoad;
import com.syuesoft.print.dao.DownloadDao;
import com.syuesoft.print.service.DownloadService;
import com.syuesoft.util.WPS;

/** 
 * @ClassName: DownloadServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author HeXin 
 * @date 2013-10-29 上午10:43:26 
 * @version 1.0 
 */
@Repository("downloadServiceImpl")
public class DownloadServiceImpl extends BaseLogServiceImpl implements DownloadService
{
    @Autowired
    private DownloadDao downloadDao;

    public void saveDownLoad(String jsonStr, String type, BasUsers user) throws Exception
    {
        String tlg1 = "\\},\\{";
        String tlg2 = "\",\"";
        String tlg3 = "\":\"";
        int index = 1;
        List<DownLoad> downloadList = new ArrayList<DownLoad>();
        JSONObject jsParts = JSON.parseObject(jsonStr); 
        jsonStr = jsParts.get("rows").toString();  
        jsonStr = jsonStr.substring(2, jsonStr.length()-2);
        String[] str = jsonStr.split(tlg1);
        if(str != null && !"".equals(str)){
            for(String s : str){
                s = s.substring(1, s.length()-1);
                String[] lable = s.split(tlg2);
                if(lable != null && !"".equals(lable)){
                    for(int i=0; i<lable.length; i++){
                        DownLoad download = new DownLoad();
                        String[] str_ = lable[i].split(tlg3);
                        if(str_ != null && !"".equals(str_)){
                            if(str_.length == 2){
                                download.setTitle(str_[0]);
                                download.setValue(!"".equals(str_[1]) ? str_[1] : null);
                                download.setType(type);
                                download.setRows(index);
                                download.setEnterpriseId(user.getBasStuff().getEnterpriseInfo().getEnterpriseId());
                                downloadList.add(download);
                            }else if(str_.length == 1){
                                download.setTitle(str_[0]);
                                download.setValue(null);
                                download.setType(type);
                                download.setRows(index);
                                download.setEnterpriseId(user.getBasStuff().getEnterpriseInfo().getEnterpriseId());
                                downloadList.add(download);
                            }
                        }
                    }
                }
                index++;
            }
        }
        String sql = "DELETE FROM download_temp WHERE type_ = '"+type+"' and enterprise_id="+user.getBasStuff().getEnterpriseInfo().getEnterpriseId();
        downloadDao.createSQLQueryOutFind(sql);
        downloadDao.saveOrUpdates(downloadList);
    }

    public List<Object> findDownLoad(String type, Object bean, BasUsers user) throws Exception
    {
        String sql = "SELECT title_,value_,rows_,type_ FROM download_temp WHERE type_ = '"+type+"' and enterprise_id="+user.getBasStuff().getEnterpriseInfo().getEnterpriseId()+" ORDER BY rows_";
        List<Object[]> downloadlist = downloadDao.createSQLQuery(sql);
        return WPS.paert(downloadlist, bean);
    }  

    
}