package com.example.practice.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.practice.login.dao.UserDao;
import com.example.practice.login.dto.UserDto;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	// user id = username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto userDto = userDao.userAccount(username);
		if (userDto == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
        return userDto;
	}

}
