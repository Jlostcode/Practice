package com.example.practice.signUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.practice.signUp.dao.SignUpDao;
import com.example.practice.signUp.dto.SignUpDto;

@Service
public class SignUpService {
	@Autowired
	private SignUpDao signUpDao;
	
	public void signUpUser(SignUpDto signUpDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String userPW = signUpDto.getUser_pw();
		String BCryptPW = passwordEncoder.encode(userPW);
		signUpDto.setUser_pw(BCryptPW);
		System.out.println(signUpDto);
		signUpDao.signUpUser(signUpDto);
	}
}
