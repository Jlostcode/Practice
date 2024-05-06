package com.example.practice.login.controller;

import com.example.practice.login.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@GetMapping("/")
    public String root(@AuthenticationPrincipal UserDto userDto) {
        if (userDto != null) {
            return "redirect:/loginSuccess/main";
        }
        return "redirect:/login";
    }

    /**
     * 로그인 폼
     */
    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDto userDto){
        if (userDto != null) {
            return "redirect:/loginSuccess/main";
        }
        return "login/login";
    }
    
    /**
     * 로그인 성공 폼
     */
    @GetMapping("/loginSuccess/main")
    public String loginSuccess(@AuthenticationPrincipal UserDto userDto) {
        System.out.println(userDto);
        return "login/loginSuccessMain";
    }
    

    /**
     * 로그인 실패 폼
     */
    @GetMapping("/access_denied")
    public String accessDenied() {
        return "login/access_denied";
    }
    
    /**
     * 권한 없는 페이지
     */
    @GetMapping("/accessDenied_page")
    public String accessDenied_page() {
        return "login/accessDenied_page";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }

        return "redirect:/";
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkPassword(@RequestParam("check_password") String check_password) {
        Map<String, Object> response = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        System.out.println("checkPassword = " + check_password);
        System.out.println("userInfoDto.getPassword() = " + userDto.getPassword());
        if(passwordEncoder.matches(check_password, userDto.getPassword())) {
            response.put("status", "true");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.put("status", "false");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/userInfo")
    public String userInfo(@AuthenticationPrincipal UserDto userDto, Model model){
        System.out.println("userDto 값: " + userDto);
        model.addAttribute(userDto);
        return "user/userInfo";
    }

}
