package com.syuesoft.Tag;

import org.apache.log4j.Logger;

import com.syuesoft.model.SystemLog;
import com.syuesoft.systemmanagement.service.SystemLogService;

public class LogThread extends Thread
{
    private Logger logs = org.apache.log4j.Logger.getLogger(this.getClass());

    private SystemLogService logService;

    private SystemLog logEntity;

    public LogThread(SystemLogService logService, SystemLog logEntity)
    {
        super();
        this.logService = logService;
        this.logEntity = logEntity;
    }

    public void run()
    {
        try{
            logService.saveLog(logEntity);
        }catch(Exception e){
            logs.error("保存系统日志出错", e);
        }
    }
}