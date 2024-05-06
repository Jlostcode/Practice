package com.example.practice.user.dao;

import com.example.practice.user.dto.UserPasswordChangeDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao {

    void passwordChange(UserPasswordChangeDto userPasswordChangeDto);
}
