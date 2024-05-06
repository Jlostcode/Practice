package com.example.practice.user.service;

import com.example.practice.user.dao.UserInfoDao;
import com.example.practice.user.dto.UserPasswordChangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoDao userInfoDao;

	public void passwordChange(UserPasswordChangeDto userPasswordChangeDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String userPW = userPasswordChangeDto.getUser_pw();
		String BCryptPW = passwordEncoder.encode(userPW);
		userPasswordChangeDto.setUser_pw(BCryptPW);
		userInfoDao.passwordChange(userPasswordChangeDto);
	}

}
