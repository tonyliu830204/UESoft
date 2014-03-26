package com.syuesoft.Tag;

import java.net.InetAddress;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.SystemLog;
import com.syuesoft.systemmanagement.service.SystemLogService;

@Aspect
// 该注解标示该类为切面类
@Component
// 注入依赖
public class LogAspect
{

    @Resource
    private SystemLogService logService;

    private Logger logs = org.apache.log4j.Logger.getLogger(this.getClass());

    @Around(value = "@annotation(log)")
    public Object aroundYoung(ProceedingJoinPoint point, Log log)
    {
        try
        {
            // 当前用户
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            BasUsers user = (BasUsers) session.getAttribute(Contstants.CUSTOMER);
            // 操作类型
            String opertype = log.opertype();
            String content = log.content();
            String moduleName = log.moduleName();
            // ip
            InetAddress host = InetAddress.getLocalHost();
            String ip = host.getHostAddress();
            Date date = new Date();
            // 执行目标方法
            Object result = point.proceed();
            Object object = point.getTarget();
            if (object instanceof BaseLogServiceImpl){
                BaseLogServiceImpl obj = (BaseLogServiceImpl) object;
                content = obj.getContent();
            }
            SystemLog logEntity = new SystemLog();
            logEntity.setSystemName(null);
            logEntity.setModuleName(moduleName);
            logEntity.setOpertype(opertype);
            logEntity.setType(user.getSystemId());
            logEntity.setIpName(ip);
            logEntity.setStartTime(date);
            logEntity.setEnterpriseId(Integer.parseInt(((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getEnterpriseInfo().getEnterpriseId().toString()));
            logEntity.setContent(content);
            logEntity.setUserName(user.getUserName() + "--"
                    + user.getBasStuff().getStfName());
            logService.saveLog(logEntity);
            return result;
        }
        catch(Throwable e)
        {
            logs.error("保存系统日志出错", e);
        }
        return null;
    }

}