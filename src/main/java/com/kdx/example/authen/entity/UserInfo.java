package com.kdx.example.authen.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/12/4
 */
@Data
@Entity
@Table(name = "t_user_info")
public class UserInfo {

    @Id
    @Column(length = 50)
    private String id;
    @Column(length = 50)
    private String username;
    @Column(length = 50)
    private String nickname;
    @Column(length = 18)
    private String phone;
    @Column(length = 50)
    private String password;
    @Column(length = 50)
    private String pwddata;
    @Column(length = 200)
    private String imgurl;

    private LocalDateTime itime;
    private LocalDateTime utime;

}
