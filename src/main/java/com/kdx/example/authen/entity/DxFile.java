package com.kdx.example.authen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
@ApiModel(value = "文件",description = "文件")
@Data
@Entity
@Table(name = "t_dx_file")
public class DxFile {

    @ApiModelProperty(value = "数据id",notes = "数据id")
    @Id
    @Column(length = 50)
    private String id;
    @ApiModelProperty(value = "业务id",notes = "业务id")
    @Column(length = 50)
    private String busiid;
    @ApiModelProperty(value = "文件名称",notes = "文件名称")
    @Column(length = 200)
    private String filename;
    @ApiModelProperty(value = "文件路径",notes = "文件路径")
    @Column(length = 200)
    private String filepath;
    @ApiModelProperty(value = "文件类型",notes = "文件类型")
    @Column(length = 200)
    private String filttype;
    @ApiModelProperty(value = "文件后缀",notes = "文件后缀")
    @Column(length = 50)
    private String filesubfix;
    @ApiModelProperty(value = "文件大小",notes = "文件大小")
    @Column(length = 50)
    private String filesize;

    @Column(length = 50)
    private String creator;
    @Column(length = 50)
    private String updator;
    @Column(length = 50)
    private String iusername;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date itime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date utime;

}
