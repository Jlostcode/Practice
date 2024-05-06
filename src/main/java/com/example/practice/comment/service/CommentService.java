package com.example.practice.comment.service;

import com.example.practice.comment.dao.CommentDao;
import com.example.practice.comment.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;
    //    댓글 개수 카운트
    public Integer commentCount(int board_id){
        return commentDao.commentCount(board_id);
    };
    //    댓글 목록 select 페이징 처리
    public List<CommentDto> commentSelectDescPage(int startRow, int size, int board_id){
        return commentDao.commentSelectDescPage(startRow, size, board_id);
    };

    public void commentInsert(int board_id, int user_no, String comment_sub){
        commentDao.commentInsert(board_id, user_no, comment_sub);
    };

    public void commentUpdate(String comment_sub, int comment_id){
        commentDao.commentUpdate(comment_sub, comment_id);
    };

    public void commentDelete(int comment_id){
        commentDao.commentDelete(comment_id);
    };
}
