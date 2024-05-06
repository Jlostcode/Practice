package com.example.practice.layout.controller;

import com.example.practice.layout.dao.LayoutDao;
import com.example.practice.layout.dto.LayoutDto;
import com.example.practice.login.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LayoutController {
@Autowired
    LayoutDao layoutDao;
    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> profileCard(@AuthenticationPrincipal UserDto userDto){
        Map<String, Object> response = new HashMap<>();

        LayoutDto layoutDto = layoutDao.layoutProfile(userDto.getUser_no());
        String user_name = layoutDto.getUser_name();
        String user_id = layoutDto.getUser_id();
        int user_role = layoutDto.getRole();
        response.put("profileName", user_name); // 사용자 이름을 response에 담아 JSON으로 반환
        response.put("profileId", user_id); // 사용자 이름을 response에 담아 JSON으로 반환
        response.put("profileRole", user_role); // 사용자 이름을 response에 담아 JSON으로 반환

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
