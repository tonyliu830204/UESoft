package com.syuesoft.print.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.base.vo.DownloadBeanVo;
import com.syuesoft.print.service.DownloadService;
import com.syuesoft.util.Msg;
import com.syuesoft.util.WPS;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 导出WPS
 * @author HeXin
 */

@ParentPackage(value = "basePackage")
@Action("downLoadAction")
public class DownloadAction extends BaseAction implements
        ModelDriven<DownloadBeanVo>
{
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    public DownloadBeanVo downloadBeanVo = new DownloadBeanVo();
    @Autowired
    private DownloadService downloadService;
    
    public DownloadBeanVo getModel()
    {
        return downloadBeanVo;
    }

    public void downLoad()
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream downOut = null;
        FileInputStream fileIn = null;
        try
        {
            String path = WPS.getRootPath();
            path = path.replace(WPS.webappsCharacteristic, "temp/");
            String html = downloadBeanVo.getHtml();
            String fileName = downloadBeanVo.getFileName();
            downOut = response.getOutputStream();
            response.reset();
            String outPath = WPS.createWPS(path, html, fileName);
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + encodingFileName(fileName));
            response.setCharacterEncoding("utf-8");
            response.setBufferSize(4096);
            fileIn = new FileInputStream(outPath);
            byte[] buffer = new byte[1024];
            int i = 0;
            while ((i = fileIn.read(buffer)) > 0)
            {
                downOut.write(buffer, 0, i);
            }
            downOut.flush();
            response.flushBuffer();
        }
        catch(Exception ex)
        {
            logger.error("导出Excel失败！", ex);
        } finally
        {
            if (fileIn != null)
                try
                {
                    fileIn.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            if (downOut != null)
                try
                {
                    downOut.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
        }
    }

    public void analysisExcel()
    {
        Msg msg = new Msg();
        try
        {
            String path = WPS.getRootPath();
            path = path.replace(WPS.webappsCharacteristic, "temp/");
            if (downloadBeanVo.getFilePath() == null)
            {
                msg.setMsg("对不起,请选择要解析的文件!!!");
            }
            else
            {
                File file_ = new File(downloadBeanVo.getFileFileName());
                if (file_.getName().endsWith(".xls")
                        || file_.getName().endsWith(".xlsx"))
                {
                    FileInputStream inputStream;
                    File dir = new File(path);
                    if (!dir.exists())
                        dir.mkdirs();
                    File file = new File(path + "/" + file_.getName());
                    if (!file.exists())
                        file.createNewFile();
                    inputStream = new FileInputStream(downloadBeanVo
                            .getFilePath());
                    FileOutputStream outputStream = new FileOutputStream(file);
                    byte[] buf = new byte[1024];
                    int length = 0;
                    while ((length = inputStream.read(buf)) != -1)
                    {
                        outputStream.write(buf, 0, length);
                    }
                    inputStream.close();
                    outputStream.flush();
                    Map<String, Object> map = WPS.readExcel(file,downloadBeanVo.getFieldNames());
                    if(map.get("success").toString().equals("true")){
                        
                    }
                    msg.setMsg(map.get("message").toString());
                    msg.setObj(map.get("object"));
                    msg.setSuccess(map.get("success").toString().equals("true") ? true : false);
                    if(map.get("success").toString().equals("true")){
                        downloadService.saveDownLoad(map.get("object").toString(), downloadBeanVo.getType(), this.getUsers());
                    }
                }
                else
                {
                    msg.setMsg("对不起,你上传的文件格式不允许!!!只支持excel文件");
                }
            }
        }catch(Exception e){
            msg.setMsg("对不起,上传失败!!!");
            logger.error("对不起,上传失败!!!", e);
        }
        writeJson(msg);
    }

    /**
     * 乱码出现的原因 　　 * ie采用URLEncoder编码输出中文 　　 * opera采用filename 　　 *
     * safari采用iso-8859-1 　　 * chrome采用base64或iso-8859-1 　　 *
     * firefox采用base64或iso-8859-1
     * 
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    private String encodingFileName(String fileName)
            throws UnsupportedEncodingException
    {
        String charFileName = "";
        HttpServletRequest request = ServletActionContext.getRequest();
        String Agent = request.getHeader("User-Agent");
        if (null != Agent)
        {
            Agent = Agent.toLowerCase();
            if (Agent.indexOf("firefox") != -1)
            {
                charFileName = new String(fileName.getBytes(), "iso8859-1");
            }
            else if (Agent.indexOf("msie") != -1)
            {
                charFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            }
            else if(Agent.indexOf("chrome") != -1)
            {
                charFileName = new String(fileName.getBytes(), "iso8859-1");
            }
            else
            {
                charFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            }
        }
        return charFileName;
    }
}