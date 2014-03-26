package com.syuesoft.integratedservices.action;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fbk.vo.DailyclientTrackingVo;
import com.syuesoft.integratedservices.service.DailyclientTrackingService;
import com.syuesoft.model.FbkDailyclientTracking;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@ParentPackage(value = "basePackage")
@Action("dailyclientTrackingAction")
public class DailyclientTrackingAction extends BaseAction implements
        ModelDriven<DailyclientTrackingVo>
{
	Logger logger=Logger.getLogger(this.getClass());

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private DailyclientTrackingService dailyclientTrackingService;

    public DailyclientTrackingService getDailyclientTrackingService()
    {
        return dailyclientTrackingService;
    }

    public void setDailyclientTrackingService(
            DailyclientTrackingService dailyclientTrackingService)
    {
        this.dailyclientTrackingService = dailyclientTrackingService;
    }

    private DailyclientTrackingVo dailyclientTrackingVo = new DailyclientTrackingVo();
    
    
    public DailyclientTrackingVo getDailyclientTrackingVo() {
		return dailyclientTrackingVo;
	}

	public void setDailyclientTrackingVo(DailyclientTrackingVo dailyclientTrackingVo) {
		this.dailyclientTrackingVo = dailyclientTrackingVo;
	}

	
    public DailyclientTrackingVo getModel()
    {
        return dailyclientTrackingVo;
    }

    /*
     * 新增方法
     */
    public String doAdd()
    {
        FbkDailyclientTracking fbkDailyclientTracking = new FbkDailyclientTracking();
        fbkDailyclientTracking.setClStatus(dailyclientTrackingVo.getClStatus());
        fbkDailyclientTracking.setMemoInfomation(dailyclientTrackingVo
                .getMemoInfomation());
        fbkDailyclientTracking.setTxDj(dailyclientTrackingVo.getTxDj());
        fbkDailyclientTracking.setTxInfomation(dailyclientTrackingVo
                .getTxInfomation());
        fbkDailyclientTracking.setCarLicense(dailyclientTrackingVo
                .getCarLicense());
        fbkDailyclientTracking.setCustomName(dailyclientTrackingVo
                .getCustomName());
        Message msg = new Message();
        try
        {
            dailyclientTrackingService.doAdd(fbkDailyclientTracking);
            msg.setMsg("车辆牌照为:【" + dailyclientTrackingVo.getCarLicense()
                    + "】的跟踪信息添加成功!");
            msg.setSuccess(true);
        }
        catch(Exception e)
        {
            msg.setMsg("车辆牌照为:【" + dailyclientTrackingVo.getCarLicense()
                    + "】的跟踪信息添加失败!");
            msg.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(msg);
        return null;
    }

    /*
     * 删除方法
     */
    public String doDelete()
    {
        FbkDailyclientTracking fbkDailyclientTracking = new FbkDailyclientTracking();
        fbkDailyclientTracking.setDrId(Integer.parseInt(dailyclientTrackingVo
                .getDrId()));
        fbkDailyclientTracking.setClStatus(dailyclientTrackingVo.getClStatus());
        fbkDailyclientTracking.setMemoInfomation(dailyclientTrackingVo
                .getMemoInfomation());
        fbkDailyclientTracking.setTxDj(dailyclientTrackingVo.getTxDj());
        fbkDailyclientTracking.setTxInfomation(dailyclientTrackingVo
                .getTxInfomation());
        fbkDailyclientTracking.setCarLicense(dailyclientTrackingVo
                .getCarLicense());
        fbkDailyclientTracking.setCustomName(dailyclientTrackingVo
                .getCustomName());
        Message msg = new Message();
        try
        {
            dailyclientTrackingService.doDelete(fbkDailyclientTracking);
            msg.setMsg("车辆牌照为:【" + dailyclientTrackingVo.getCarLicense()
                    + "】的跟踪信息删除成功!");
            msg.setSuccess(true);
        }
        catch(Exception e)
        {
            msg.setMsg("车辆牌照为:【" + dailyclientTrackingVo.getCarLicense()
                    + "】的跟踪信息删除失败!");
            msg.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(msg);
        return null;
    }

    /*
     * 修改方法
     */
    public String doUpdate()
    {
        FbkDailyclientTracking fbkDailyclientTracking = new FbkDailyclientTracking();
        fbkDailyclientTracking.setDrId(Integer.parseInt(dailyclientTrackingVo
                .getDrId()));
        fbkDailyclientTracking.setCarLicense(dailyclientTrackingVo
                .getCarLicense());
        fbkDailyclientTracking.setClStatus(dailyclientTrackingVo.getClStatus());
        fbkDailyclientTracking.setCustomName(dailyclientTrackingVo
                .getCustomName());
        fbkDailyclientTracking.setMemoInfomation(dailyclientTrackingVo
                .getMemoInfomation());
        fbkDailyclientTracking.setTxDj(dailyclientTrackingVo.getTxDj());
        fbkDailyclientTracking.setTxInfomation(dailyclientTrackingVo
                .getTxInfomation());
        Message msg = new Message();
        try
        {
            dailyclientTrackingService.doUpdate(fbkDailyclientTracking);
            msg.setMsg("修改成功!");
            msg.setSuccess(true);
        }
        catch(Exception e)
        {
            msg.setMsg("修改失败!");
            msg.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(msg);
        return null;
    }

    /*
     * 查询有所的查询方法
     */
    public void doFind()
    {
    	try {
    		dailyclientTrackingVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson( dailyclientTrackingService.doFind(dailyclientTrackingVo));
		} catch (Exception e) {
			logger.error("查询维修建议失败!", e);
		}
          
    }

    /*
     * 条件查询的方法
     */
    public String findByCondition()
    {
        HttpSession ssion = ServletActionContext.getRequest().getSession();
        FbkDailyclientTracking fbkDailyclientTracking = new FbkDailyclientTracking();
        fbkDailyclientTracking.setCarLicense(dailyclientTrackingVo
                .getCarLicense());
        fbkDailyclientTracking.setCustomName(dailyclientTrackingVo
                .getCustomName());
        fbkDailyclientTracking.setTxInfomation(dailyclientTrackingVo
                .getTxInfomation());
        List list = null;
        Json json = new Json();
        try
        {
            list = dailyclientTrackingService.doFindByCondition(
                    fbkDailyclientTracking, dailyclientTrackingVo.getPage(),
                    dailyclientTrackingVo.getRows());
            json.setTotal(Integer.parseInt(ssion.getAttribute("querySize")
                    .toString()));
            json.setRows(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(json);
        return null;
    }
}
