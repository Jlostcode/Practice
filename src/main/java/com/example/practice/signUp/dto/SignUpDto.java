package com.example.practice.signUp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignUpDto {
	
	private int user_no;
	private String user_name;
	private String user_tel;
	private String user_id;
	private String user_pw;
	private int user_gen;
	private Date user_signdate;

}
