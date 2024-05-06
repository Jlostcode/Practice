package com.example.practice.user.controller;

import com.example.practice.login.dto.UserDto;
import com.example.practice.schedule.dto.ScheduleDto;
import com.example.practice.schedule.service.ScheduleService;
import com.example.practice.user.dto.UserPasswordChangeDto;
import com.example.practice.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("changePassword")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> changePassword(
            @RequestParam("changePassword") String changePassword, @AuthenticationPrincipal UserDto userDto) {
        Map<String, Object> response = new HashMap<>();
        // Test SVN Commit
        int user_no = userDto.getUser_no();
        UserPasswordChangeDto userPasswordChangeDto = new UserPasswordChangeDto();
        userPasswordChangeDto.setUser_pw(changePassword);
        userPasswordChangeDto.setUser_no(user_no);
        userInfoService.passwordChange(userPasswordChangeDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("user/schedule")
    public String scheduleForm() {
        return "user/schedule";
    }

    @RequestMapping("user/getScheduleList")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getScheduleList(
           @RequestParam("start_time") String start_time, @RequestParam("end_time") String end_time) {
        System.out.println("scheduleDto" + start_time+end_time);
        Map<String, Object> response = new HashMap<>();
        List<ScheduleDto> scheduleList = scheduleService.getScheduleList(start_time, end_time);
        System.out.println(scheduleList);
        response.put("scheduleList", scheduleList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping("user/insertSchedule")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> insertSchedule(
            @RequestParam("title") String title, @RequestParam("start_time") String start_time, @RequestParam("end_time") String end_time) {
        Map<String, Object> response = new HashMap<>(); // 여기부터 작성하면됨 위에 RequestParam 씀 0408
        scheduleService.insertSchedule(title,start_time,end_time);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping("user/deleteSchedule")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteSchedule(
            @RequestParam("title") String title, @RequestParam("start_time") String start_time, @RequestParam("end_time") String end_time) {
        Map<String, Object> response = new HashMap<>(); // 여기부터 작성하면됨 위에 RequestParam 씀 0408
        scheduleService.deleteSchedule(title,start_time,end_time);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
