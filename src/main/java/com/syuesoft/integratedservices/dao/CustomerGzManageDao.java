package com.syuesoft.integratedservices.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fbk.vo.TrackManagementVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.FbkCarDcname;
import com.syuesoft.model.FbkDcservey;
import com.syuesoft.model.FbkDcserveyName;

public interface CustomerGzManageDao extends BaseDaoI<Object>
{
    public Datagrid doFindAll(int page, int rows,Integer  enterpriseId)
            throws Exception;// 查询所有

    public List<TrackManagementVo> getByCondition(
            TrackManagementVo trackManagementVo) throws Exception;// 通过条件查询

    public List<FbkDcservey> getDcServeyById(int carId) throws Exception;// 查询3DC调查表

    public List<FbkDcserveyName> getFbkDcserveyName(String str)
            throws Exception;// 查询3DC调查表的维修吸项目名称

    public void doUpdate(TrackManagementVo trackManagementVo) throws Exception;

    public void updateCollect(TrackManagementVo trackManagementVo)
            throws Exception;// 修改回访表的信息

    public List<FbkCarDcname> getFbkCarDcname(
            TrackManagementVo trackManagementVo) throws Exception; // 通过车辆id和项目表的id查询中间表的评论和评分

    public List<FbkDcserveyName> getFbkDcserveyNameByid(int dcNameId)
            throws Exception; // 通过回访项目id查询回访项目名称

    public void updateFbkCarDcname(TrackManagementVo trackManagementVo,
            int carid) throws Exception;

    // 获取维修类别名称
    public List getBasRepairTypeName() throws Exception;
   //查询历史满意度
	public Datagrid doFindCollect(int page, int rows,String carId)throws Exception;
}
