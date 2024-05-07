package com.example.practice.board.dto;

import lombok.*;

import java.util.Date;

import com.example.practice.board.util.Pagination;




@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	private int row_num;
	private int board_id;
	private int user_no;
	private String board_title;
	private String board_sub;
	private Date board_regdate;
	private String user_name;
	private String board_regdateF;
    
	private Pagination pagination;
}
