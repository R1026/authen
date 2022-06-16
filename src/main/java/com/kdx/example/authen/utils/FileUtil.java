package com.kdx.example.authen.utils;

import org.springframework.util.StringUtils;

import java.io.File;

public class FileUtil {

    public static final String UPLOAD_PATH     = "UPLOAD";
    public static final String DOWNLOAD_PATH   = "DOWNLOAD";

    public static File getUploadDir(String filepath){
        if (StringUtils.isEmpty(filepath)){
            filepath = System.getProperty("user.dir");
        }
        String uDir = filepath + File.separator + UPLOAD_PATH;
        File file = new File(uDir);
        if (!file.exists())
            file.mkdirs();
        return file;
    }

}
