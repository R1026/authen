package com.kdx.example.authen.utils;

import com.kdx.example.authen.entity.DxFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
public class UploadsUtil<T> {


    public static String UPLOAD_PATH     = "UPLOAD";
    public static String DOWNLOAD_PATH   = "DOWNLOAD";

    public static List<DxFile> uploads(String bashpath,MultipartFile[] files) throws IOException{
        //String uploadDir = getUploadDir();
        File uDir = FileUtil.getUploadDir(bashpath);
        String uploadDir = uDir.getAbsolutePath();
        List<DxFile> list = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String filesubfix = filename.substring(filename.lastIndexOf("."));
            String filepath = uploadDir + File.separator + System.currentTimeMillis() + filesubfix;
            String filetype = file.getContentType();
            String filesize = String.valueOf(file.getSize());
            DxFile dxFile = new DxFile();
            dxFile.setFilename(filename);
            dxFile.setFilepath(filepath);
            dxFile.setFilesize(filesize);
            dxFile.setFilesubfix(filesubfix);
            dxFile.setFilttype(filetype);
            dxFile.setItime(new Date());
            file.transferTo(new File(filepath));
            list.add(dxFile);
        }
        return list;
    }

    /*public static String getUploadDir(){
        file_bashurl = StringUtils.isEmpty(file_bashurl) ? System.getProperty("user.dir") : file_bashurl;
        String uploadDir_ = file_bashurl + File.separator + UPLOAD_PATH;
        File u = new File(uploadDir_);
        if (!u.exists())
            u.mkdirs();
        return uploadDir_;
    }

    public static  String getDownloadDir(){
        file_bashurl = StringUtils.isEmpty(file_bashurl) ? System.getProperty("user.dir") : file_bashurl;
        String downloadDir_ = file_bashurl + File.separator + DOWNLOAD_PATH;
        return downloadDir_;
    }*/

}
