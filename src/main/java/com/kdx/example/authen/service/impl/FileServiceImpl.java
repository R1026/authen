package com.kdx.example.authen.service.impl;

import com.kdx.example.authen.entity.DxFile;
import com.kdx.example.authen.service.IFileService;
import com.kdx.example.authen.utils.UploadsUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
@Service
public class FileServiceImpl implements IFileService {

    @Value("${com.kdx.filepath:null}")
    private String filepath;

    @Override
    public List<DxFile> uploads(MultipartFile[] files, String busiid) throws IOException {
        List<DxFile> list = UploadsUtil.uploads(filepath,files);
        if (!StringUtils.isEmpty(busiid)){
            list.forEach(item -> {
                item.setBusiid(busiid);
            });
        }
        return list;
    }
}
