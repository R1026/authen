package com.kdx.example.authen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdx.example.authen.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/12/4
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    List<UserInfo> selectByUsername(@Param("username") String username);
}
