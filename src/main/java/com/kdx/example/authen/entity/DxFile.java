package com.kdx.example.authen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.Serializers;
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
@Data
@Entity
@Table(name = "t_dx_file")
public class DxFile {

    @Id
    @Column(length = 50)
    private String id;
    @Column(length = 50)
    private String busiid;
    @Column(length = 200)
    private String filename;
    @Column(length = 200)
    private String filepath;
    @Column(length = 200)
    private String filttype;
    @Column(length = 50)
    private String filesubfix;
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
