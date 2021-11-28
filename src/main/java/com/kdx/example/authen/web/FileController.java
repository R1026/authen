package com.kdx.example.authen.web;

import com.kdx.example.authen.entity.DxFile;
import com.kdx.example.authen.service.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private IFileService service;

    @PostMapping("/uploads")
    public List<DxFile> uploads(MultipartFile[] files, HttpServletRequest request){
        String busiid = request.getParameter("busiid");
        List<DxFile> list = null;
        try {
            list = service.uploads(files,busiid);
        }catch (Exception e){
            log.error("上传失败：【{}】",e.getMessage());
        }
        return list;
    }

}
