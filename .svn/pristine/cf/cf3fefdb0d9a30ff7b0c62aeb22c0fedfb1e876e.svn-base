package com.example.practice.schedule.service;

import com.example.practice.schedule.dao.ScheduleDao;
import com.example.practice.schedule.dto.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleDao scheduleDao;

    public List<ScheduleDto> getScheduleList(String start_time, String end_time) {
        return scheduleDao.getScheduleList(start_time, end_time);
    }

    public int insertSchedule(String title, String start_time, String end_time) {
        return scheduleDao.insertSchedule(title, start_time, end_time);
    }

    public int deleteSchedule(String title, String start_time, String end_time) {
        return scheduleDao.deleteSchedule(title, start_time, end_time);
    }

}
