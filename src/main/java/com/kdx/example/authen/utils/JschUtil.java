package com.kdx.example.authen.utils;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class JschUtil {

    private static final Logger log = LoggerFactory.getLogger(JschUtil.class);

    private String host;
    private Integer port;
    private String user;
    private String password;
    private JSch jSch;
    private ChannelSftp sftp;

    public JschUtil(String host, Integer port, String user, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public void connect() throws JSchException {
        jSch = new JSch();
        Session session = jSch.getSession(user, host, port);
        session.setPassword(password);
        Properties properties = new Properties();
        properties.put("StrictHostKeyChecking","no");
        session.setConfig(properties);
        session.connect();
        Channel sftpChannel = session.openChannel("sftp");
        sftpChannel.connect();
        sftp = (ChannelSftp) sftpChannel;
        log.info("连接到sftp成功。host:{}",host);
    }

    public boolean isDirExists(String path){
        try {
            sftp.ls(path);
            return true;
        }catch (SftpException e){
            log.error("目录不存在：{}",path);
            return false;
        }
    }

    public void createDir(String path){
        try {
            if (isDirExists(path)){
                sftp.cd(path);
                log.info("当前目录：{}",sftp.pwd());
                return;
            }
            String[] paths = path.split("/");
            StringBuffer pathStr = new StringBuffer("/");
            for (String item : paths) {
                if (item.equals(""))
                    continue;
                pathStr.append(item + "/");
                if (isDirExists(pathStr.toString())){
                    sftp.cd(pathStr.toString());
                    log.info("当前目录：{}",sftp.pwd());
                }else {
                    //创建目录
                    sftp.mkdir(pathStr.toString());
                    //进入当前目录
                    sftp.cd(pathStr.toString());
                    log.info("当前目录：{}",sftp.pwd());
                }
            }
            sftp.cd(path);
            log.info("当前目录：{}",sftp.pwd());
        }catch (SftpException e){
            log.error("创建目录异常",e);
        }
    }

    public void uploadFile(String local,String remote) throws Exception{
        String currentDir = System.getProperty("user.dir");
        File localFile = new File(currentDir + File.separator + "application.properties");

        InputStream inputStream = null;

        String remoteBashPath = remote.substring(0,remote.lastIndexOf("/"));
        log.info("目标路径：{}",remoteBashPath);

        try {
            inputStream = new FileInputStream(localFile);
            sftp.setInputStream(inputStream);
            sftp.put(inputStream,remote);
        }catch (Exception e){

        }finally {
            if (null != inputStream)
                inputStream.close();
        }
    }

}
