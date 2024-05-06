package com.example.practice.music.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MusicFileDto {
    int mf_no;
    int music_no;
    String mf_ogname;
    String mf_name;
    String mf_path;
    int mf_size;
    Date mf_date;
}
