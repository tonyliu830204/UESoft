package com.syuesoft.base.vo;

import java.io.File;
import java.io.InputStream;

public class DownloadBeanVo extends BaseBeanVo
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String fileName; // 自定的文件名

    private String html; // 要转换的html文本

    private InputStream inputStream; // 导出文件流

    private String fieldNames; // 要导出的字段

    private File filePath; // 导入的文件

    private String fileFileName; // 导入的文件名称

    private String fileFileContentType; // 导入的文件类型
    
    private String type;

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getHtml()
    {
        return html;
    }

    public void setHtml(String html)
    {
        this.html = html;
    }

    public InputStream getInputStream()
    {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public String getFieldNames()
    {
        return fieldNames;
    }

    public void setFieldNames(String fieldNames)
    {
        this.fieldNames = fieldNames;
    }

    public File getFilePath()
    {
        return filePath;
    }

    public void setFilePath(File filePath)
    {
        this.filePath = filePath;
    }

    public String getFileFileName()
    {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName)
    {
        this.fileFileName = fileFileName;
    }

    public String getFileFileContentType()
    {
        return fileFileContentType;
    }

    public void setFileFileContentType(String fileFileContentType)
    {
        this.fileFileContentType = fileFileContentType;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}