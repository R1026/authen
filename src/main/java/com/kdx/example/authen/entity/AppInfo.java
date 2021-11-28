package com.kdx.example.authen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
@Data
@Entity
@Table(name = "t_app_info")
public class AppInfo {

    @Id
    @Column(length = 50)
    private String id;
    @Column(length = 200)
    private String clientsecret;
    @Lob
    private String loggo;
    @Column(length = 200)
    private String callbakurl;
    @Column(length = 200)
    private String name;
    @Column(length = 200)
    private String sname;
    @Column(length = 5)
    private Integer sortno;
    @Column(length = 500)
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date itime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date utime;
    @Column(length = 50)
    private String creator;
    @Column(length = 50)
    private String updator;
    @Column(length = 50)
    private String iusername;

}
