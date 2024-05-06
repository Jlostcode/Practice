package com.example.practice.melonBot.controller;

import com.example.practice.melonBot.dto.MelonDto;
import com.example.practice.melonBot.service.MelonService;
import com.example.practice.melonBot.service.MelonUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class MelonController {

    @Autowired
    private MelonService melonService;


    @GetMapping("/melon100")
    public String melon100 (Model model) {

        List<MelonDto> melon100 = melonService.melonAllSelect();

        model.addAttribute("melon100", melon100);


        return "melon100/melon100";
    }

}
