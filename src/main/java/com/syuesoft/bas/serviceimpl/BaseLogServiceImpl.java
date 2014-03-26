package com.syuesoft.bas.serviceimpl;

import org.springframework.stereotype.Service;

import com.syuesoft.bas.service.BaseLogService;

@Service("baseLogServiceImpl")
public class BaseLogServiceImpl implements BaseLogService
{

    private String content; // 操作内容

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

}