package com.example.practice.melonBot.service;

import com.example.practice.melonBot.dao.MelonDao;
import com.example.practice.melonBot.dto.MelonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MelonService {

    @Autowired
    private MelonDao melonDao;

    public List<MelonDto> melonAllSelect(){
        return melonDao.melonAllSelect();
    }
    public void melonSet(int melon_rank, String melon_title, String melon_artist, String melon_like, String melon_titleLink,
    		String melon_artistLink) {
        melonDao.melonSet(melon_rank, melon_title, melon_artist, melon_like, melon_titleLink, melon_artistLink);
    }

    public void melonUpdate(int melon_rank, String melon_title, String melon_artist, String melon_like, String melon_titleLink,
    		String melon_artistLink){
        melonDao.melonUpdate(melon_rank, melon_title, melon_artist, melon_like, melon_titleLink, melon_artistLink);
    }

    public void melonDelete(){
        melonDao.melonDelete();
    }
}
