package com.syuesoft.systemmanagement.serviceimpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
import com.syuesoft.util.ProcessUtil;
import com.syuesoft.util.WPS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.BasUsers;
import com.syuesoft.systemmanagement.dao.DBBackUpLoadDao;
import com.syuesoft.systemmanagement.service.DBBackUpLoadService;
import com.syuesoft.systemmanagement.vo.DataBackupVo;
import com.syuesoft.util.GetDateByString;
import com.zbyinterface.yhInterface.DES3.DES3Utils;

@Service("dBBackUpLoadService")
public class DBBackUpLoadServiceImpl implements DBBackUpLoadService {
	//com.syuesoft.bas.service.BasCompanyInformationSetService.getBasCompanyInformationSet(String type, String name);
    Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DBBackUpLoadDao dBBackUpLoadDao;
	
	@Autowired
    private BasCompanyInformationSetService basCompanyInformationSetService;

	/**
	 * 备份数据库
	 * nowdate(当前时间)
	 * address(备份路径)
	 * filename(备份文件名称)
	  @throws Exception
	 */
	public Msg saveBackUp(DataBackupVo dataBackupVo, BasUsers user) throws Exception
    {
	    Msg msg = new Msg();
	    boolean success = true;
        Integer enterpriseId = user.getBasStuff().getEnterpriseInfo().getEnterpriseId();
        File file = null;
        File file0 = null;
        File file1 = null;
        String filename = null;
        String filename_ = null;
        //获取备份路径
        BasCompanyInformationSet basCompanyInformationSet = basCompanyInformationSetService.getBasCompanyInformationSet(Contstants.PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DBBACKUPPATH,enterpriseId);
        String outaddress = basCompanyInformationSet.getCiValue();
        if(outaddress != null || !"".equals(outaddress)){
            file = new File(outaddress);
            if(!file.exists())
                file.mkdirs();
        }
        //获取备份文件名称
        GetDateByString getdate = new GetDateByString();
        filename = getdate.getNowTime().trim().replace("-", "").replace(":", "").replace(" ", "")+".sql";
        filename_ = filename+"_";
        file1 = new File(outaddress+filename_);
        try
        {
            if(file1.exists()){
                file1.delete();
            }
            file1.createNewFile();
        }
        catch(IOException e)
        {
            logger.error("创建文件失败", e);
        }
        //解析jdbc配置文件
        ResourceBundle bundleMessage = PropertyResourceBundle.getBundle("jdbc");
        //解析databackup配置文件
        ResourceBundle databackup = PropertyResourceBundle.getBundle("databackup");
        //获取执行命令字符串
        String address = databackup.getString("connection.address");
        String connectionurl = bundleMessage.getString("connection.url");
        int ipStartIndex = connectionurl.indexOf("://") + "://".length();
        String temp0 = connectionurl.substring(ipStartIndex, connectionurl.length());
        int ipEndIndex = temp0.indexOf(":");
        String ip = temp0.substring(0, ipEndIndex);
        String temp1 = temp0.substring(ipEndIndex+1, temp0.length());
        int portEndIndex = temp1.indexOf("/");
        String port = temp1.substring(0, portEndIndex);
        String temp2 = temp1.substring(portEndIndex+1, temp1.length());
        int dataBaseEndIndex = temp2.indexOf("?");
        String dateBase = temp2.substring(0, dataBaseEndIndex);
        String cmd = address.replace("USER", bundleMessage.getString("connection.username"))
                                .replace("PASSWORD", bundleMessage.getString("connection.password"))
                                .replace("#", outaddress)
                                .replace("NAME", filename_)
                                .replace("URL", ip)
                                .replace("PORT", port)
                                .replace("database", dateBase);
        //执行数据库命令
        try {
            String batContent = cmd ;
            msg = resolveBatFile(batContent, 0, "备份数据库");
            if(msg.getSuccess()){
                //将备份信息存入数据库
                file0 = new File(outaddress+filename);
                if(file0.exists()){
                    file0.delete();
                }
                file0.createNewFile();
                if(!DES3Utils.getDESedeKeyExist(enterpriseId.toString())){
                    DES3Utils.createDesKey(enterpriseId.toString());
                }
                if(!DES3Utils.encryptFile(file1.getAbsolutePath(),file0.getAbsolutePath(),enterpriseId.toString())){
                    msg.setMsg("数据库备份成功!但是加密过程异常");
                    msg.setSuccess(true);
                    filename = filename_;
                    success = false;
                }
                dBBackUpLoadDao.saveBackup(outaddress,filename, user);
            }
        }
        catch(Exception e)
        {
            msg.setMsg("数据库备份失败");
            msg.setSuccess(false);
            logger.error("数据库备份失败", e);
        }finally{
            if(success){
                file1.delete();
            }
        }
        return msg;
    }
	
	/**
	 * 还原数据库
	* <p>Title: findBackUp</p> 
	* <p>Description: </p> 
	* @param dataBackupVo
	* @param user
	* @return
	* @throws Exception 
	* @see com.syuesoft.systemmanagement.service.DBBackUpLoadService#findBackUp(com.syuesoft.systemmanagement.vo.DataBackupVo, com.syuesoft.model.BasUsers)
	 */
	public Msg findBackUp(DataBackupVo dataBackupVo, BasUsers user) throws Exception
    {
	    Msg msg = new Msg();
        String filepath = "";
        String filepath_ = "";
        Integer enterpriseId = user.getBasStuff().getEnterpriseInfo().getEnterpriseId();
        File file = null;
        File file_ = null;
        ResourceBundle bundleMessage = null;
        String load = null;
        String connectionurl = null;
        try {
            //获取文件及文件路径
            List<Object[]> rlist = dBBackUpLoadDao.findBackUpPath(dataBackupVo);
            if(rlist != null && rlist.size() > 0){
                Object[] obj = null;
                for (int i = 0; i < rlist.size(); i++) {
                    obj = (Object[])rlist.get(i);
                    filepath = String.valueOf(obj[0])+"\\" + String.valueOf(obj[1])+"";
                    filepath_ = String.valueOf(obj[0])+"\\" + String.valueOf(obj[1])+"__";
                }
                bundleMessage = PropertyResourceBundle.getBundle("jdbc");
                ResourceBundle databackup = PropertyResourceBundle.getBundle("databackup");
                
                load = databackup.getString("connection.load");
                connectionurl = bundleMessage.getString("connection.url");
                int ipStartIndex = connectionurl.indexOf("://") + "://".length();
                String temp0 = connectionurl.substring(ipStartIndex, connectionurl.length());
                int ipEndIndex = temp0.indexOf(":");
                String ip = temp0.substring(0, ipEndIndex);
                String temp1 = temp0.substring(ipEndIndex+1, temp0.length());
                int portEndIndex = temp1.indexOf("/");
                String port = temp1.substring(0, portEndIndex);
                String temp2 = temp1.substring(portEndIndex+1, temp1.length());
                int dataBaseEndIndex = temp2.indexOf("?");
                String dateBase = temp2.substring(0, dataBaseEndIndex);
                file = new File(filepath);
                if(file.exists()){
                    file_ = new File(filepath_);
                    if(file_.exists()){
                        file_.delete();
                    }
                    file_.createNewFile();
                    if(!filepath.endsWith(".sql_")){                                     //只是备份时加密失败，可以直接还原
                        if(DES3Utils.getDESedeKeyExist(enterpriseId.toString())){
                            if(DES3Utils.decryptFile(filepath, filepath_, enterpriseId.toString())){
                                //获取还原执行命令
                                String batContent = createCmd(load,bundleMessage,filepath_,ip,port,dateBase) ;
                                msg = resolveBatFile(batContent, 1, "还原数据库");
                            }else{
                                msg.setMsg("很遗憾!解密失败");
                                msg.setSuccess(true);
                            }
                        }else{
                            msg.setMsg("很遗憾!加密文件丢失");
                            msg.setSuccess(true);
                        }
                    }else{
                        //获取还原执行命令
                        String batContent = createCmd(load,bundleMessage,filepath_,ip,port,dateBase) ;
                        msg = resolveBatFile(batContent, 1, "还原数据库");
                    }
                }else{
                    msg.setMsg("很遗憾!数据库备份文件丢失");
                    msg.setSuccess(true);
                }
            }else{
                msg.setMsg("对不起要还原的文件不存在,请确实是否从数据库删除");
                msg.setSuccess(false);
            }
        }catch (Exception e) {
                msg.setSuccess(false);
                logger.error("数据库还原失败", e);
        }finally{
            file_.delete();
        }
        return msg;
    }
	
	/**
	 * 查询数据库备份列表
	* <p>Title: getBackupInfor</p> 
	* <p>Description: </p> 
	* @param dataBackupVo
	* @param user
	* @return
	* @throws Exception 
	* @see com.syuesoft.systemmanagement.service.DBBackUpLoadService#getBackupInfor(com.syuesoft.systemmanagement.vo.DataBackupVo, com.syuesoft.model.BasUsers)
	 */
	public Json getBackupInfor(DataBackupVo dataBackupVo, BasUsers user) throws Exception {
	    List<DataBackupVo> list = new ArrayList<DataBackupVo>();
	    Json json = new Json();
	    String sql = "select * from Data_Backup a where 1 = 1  and enterprise_id='"+user.getBasStuff().getEnterpriseInfo().getEnterpriseId()+"'";
        if(dataBackupVo.getOrder() != null && dataBackupVo.getSort() != null){
            sql += " order by a."+dataBackupVo.getSort()+" "+dataBackupVo.getOrder()+"";
        }
        int total = dBBackUpLoadDao.getCountBySQL(sql);
	    List<Object[]> rlist = dBBackUpLoadDao.createSQLQuery(sql, dataBackupVo.getPage(), dataBackupVo.getRows());
	    if(rlist != null && rlist.size() > 0){
    	    Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                DataBackupVo vo = new DataBackupVo();
                if(obj[0]!=null){vo.setId(obj[0].toString());}
                if(obj[1]!=null){vo.setData_Backup_Time(obj[1].toString());}
                if(obj[3]!=null){vo.setData_Back_Name(obj[3].toString());}
                list.add(vo);
            }
	    }
	    json.setRows(list);
	    json.setTotal(total);
	    return json;
	}
	
	/**
	 * 
	* 处理数据库备份、还原
	* @Title: resolveBatFile 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param batContent
	* @param @param tag
	* @param @param msg
	* @param @return    设定文件 
	* @return Msg    返回类型 
	* @throws
	 */
	private Msg resolveBatFile(String batContent, int tag, String msg){
	    Msg msgO = new Msg();
        File file = null;
        try{
            String tomcatpath = WPS.getRootPath();
            String filepath = null;
            if(tag == 0 && batContent != null && !"".equals(batContent)){              //备份
                filepath = tomcatpath+"runing//backUp.bat";
            }else if(tag == 1 && batContent != null && !"".equals(batContent)){       //还原
                filepath = tomcatpath+"runing//restore.bat";
            }else{
                msgO.setMsg("没有可以辨别的类型");
                msgO.setSuccess(false);
                return msgO;
            }
            String jdkBit = new ProcessUtil().getJdkBit();
            String exePath = tomcatpath+"runing";
            if(tag == 0 && batContent != null && !"".equals(batContent) && filepath != null){ //备份
                File file2 = null;
                file = new File(filepath);
                if(file.exists()){
                    file.delete();
                    file.createNewFile();
                }
                if(jdkBit.trim().equals("64")){
                     file2 = new File(exePath+"\\mysqldump64.exe");
                     batContent = batContent.replace("mysqldump", "mysqldump64");
                }else if(jdkBit.trim().equals("32")){
                     file2 = new File(exePath+"\\mysqldump32.exe");
                     batContent = batContent.replace("mysqldump", "mysqldump32");
                }else{
                     msgO.setMsg("无法识别系统类型");
                     msgO.setSuccess(false);
                     return msgO;
                }
                if(!file2.exists()){
                    msgO.setMsg("备份失败！确实文件");
                    msgO.setSuccess(false);
                    return msgO;
                }
                msgO = wirte(file, exePath, batContent);
            }else if(tag == 1 && batContent != null && !"".equals(batContent) && filepath != null){ //还原
                File file2 = null;
                file = new File(filepath);
                if(file.exists()){
                    file.delete();
                    file.createNewFile();
                }
                if(jdkBit.trim().equals("64")){
                    file2 = new File(exePath+"\\mysql64.exe");
                    batContent = batContent.replace("mysql", "mysql64");
                }else if(jdkBit.trim().equals("32")){
                    file2 = new File(exePath+"\\mysql32.exe");
                    batContent = batContent.replace("mysql", "mysql32");
                }else{
                    msgO.setMsg("无法识别系统类型");
                    msgO.setSuccess(false);
                    return msgO;
                }
                if(!file2.exists()){
                    msgO.setMsg("还原失败！确实文件");
                    msgO.setSuccess(false);
                    return msgO;
                }
                msgO = wirte(file, exePath, batContent);
            }
            if(msgO.getSuccess()){
                msgO = new ProcessUtil().excuteCommand(file.getAbsolutePath(), msg);
            }
        }catch(IOException e){
            logger.error("生成批处理文件错误", e);
        }
        return msgO;
    }
	
	/**
	 * 
	* 装配数据库备份、还原命令 
	* @Title: createCmd 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param load
	* @param @param bundleMessage
	* @param @param filepath_
	* @param @param ip
	* @param @param port
	* @param @param dateBase
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	private String createCmd(String load,ResourceBundle bundleMessage, String filepath_, String ip, String port, String dateBase){
	    String cmd = load.replace("USER", bundleMessage.getString("connection.username"))
        .replace("PASSWORD", bundleMessage.getString("connection.password"))
        .replace("filepath", filepath_)
        .replace("URL", ip)
        .replace("PORT", port)
        .replace("database", dateBase);
	    return cmd;
	}
	
	/**
	 * 
	* 将数据库备份、还原命令写入文件
	* @Title: wirte 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param file
	* @param @param exePath
	* @param @param batContent
	* @param @return    设定文件 
	* @return Msg    返回类型 
	* @throws
	 */
	private Msg wirte(File file, String exePath, String batContent){
	    Msg msgO = new Msg();
        try
        {
            if(exePath.indexOf("webapps") != -1){
                String tomcatroot = "cd .. ";
                tomcatroot = tomcatroot + "\r\n" + "cd " + exePath.substring(exePath.indexOf("webapps"), exePath.length());
                batContent = tomcatroot + "\r\n" + batContent;
                PrintWriter pw = new PrintWriter(new FileWriter(file),true);
                pw.print(batContent);
                pw.close();
                msgO.setSuccess(true);
                msgO.setMsg("生产批处理文件成功");
            }
        }
        catch(IOException e)
        {
            msgO.setSuccess(false);
            msgO.setMsg("生产批处理文件失败");
            logger.info("生产批处理文件失败");
        }
        return msgO;
	}
}