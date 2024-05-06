package com.example.practice.comment.dao;

import com.example.practice.comment.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CommentDao {
    //    댓글 개수 카운트
    Integer commentCount(@Param("board_id") int board_id);
    //    댓글 목록 select 페이징 처리
    List<CommentDto> commentSelectDescPage(@Param("startRow") int startRow, @Param("size") int size, @Param("board_id") int board_id);

    void commentInsert(@Param("board_id") int board_id, @Param("user_no") int user_no, @Param("comment_sub") String comment_sub);

    void commentUpdate(@Param("comment_sub") String comment_sub, @Param("comment_id") int comment_id);

    void commentDelete(@Param("comment_id") int comment_id);
}
