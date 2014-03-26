package com.syuesoft.integratedservices.action;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fbk.vo.ProgramTrackVo;
import com.syuesoft.integratedservices.service.ProgramTrackService;

@Controller
@Scope("prototype")
public class ProgramTrackAction extends BaseAction implements
        ModelDriven<ProgramTrackVo>
{

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private ProgramTrackService programTrackService;

    public ProgramTrackService getProgramTrackService()
    {
        return programTrackService;
    }

    public void setProgramTrackService(ProgramTrackService programTrackService)
    {
        this.programTrackService = programTrackService;
    }

    private ProgramTrackVo programTrackVo = new ProgramTrackVo();

    
    public ProgramTrackVo getModel()
    {
        return programTrackVo;
    }

    /*
     * 客户项目统计的综合查询方法 在此方法中将会调用dao中的两个方法
     * findSameThing()和findSameThing2()将所需信息设给vo
     */
    public void findSameThingAll()
    {
    	 try{
    		 programTrackVo.setEnterpriseId(getNowEnterpriseId());
        	 super.writeJson(programTrackService.findSameThing(programTrackVo));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
