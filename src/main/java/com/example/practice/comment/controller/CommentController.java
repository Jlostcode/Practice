package com.example.practice.comment.controller;

import com.example.practice.comment.service.CommentService;
import com.example.practice.login.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/board/view/commentInsert")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> commentInsert(
            @RequestParam("board_id") int board_id, @AuthenticationPrincipal UserDto userDto, @RequestParam("comment_sub")String comment_sub) {
        Map<String, Object> response = new HashMap<>();
        int user_no = userDto.getUser_no();
        System.out.println("comment_sub:" + comment_sub);
        commentService.commentInsert(board_id, user_no, comment_sub);
        // 응답 데이터에 추가
        response.put("board_id", board_id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/board/view/commentUpdate")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> commentUpdate(
            @RequestParam("comment_id") int comment_id, @AuthenticationPrincipal UserDto userDto, @RequestParam("comment_sub")String comment_sub) {
        Map<String, Object> response = new HashMap<>();
        commentService.commentUpdate(comment_sub, comment_id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/board/view/commentDelete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> commentDelete(
            @RequestParam("comment_id") int comment_id, @RequestParam("board_id") int board_id) {
        Map<String, Object> response = new HashMap<>();
        commentService.commentDelete(comment_id);
        response.put("board_id", board_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
