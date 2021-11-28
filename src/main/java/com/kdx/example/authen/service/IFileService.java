package com.kdx.example.authen.service;

import com.kdx.example.authen.entity.DxFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
public interface IFileService {

    List<DxFile> uploads(MultipartFile[] files, String busiid) throws IOException;

}
