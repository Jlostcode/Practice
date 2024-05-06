package com.example.practice.schedule.dao;

import com.example.practice.schedule.dto.ScheduleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ScheduleDao {
    public List<ScheduleDto> getScheduleList(@Param("start_time") String start_time, @Param("end_time") String end_time);
    public int insertSchedule(@Param("title") String title, @Param("start_time") String start_time, @Param("end_time") String end_time);
    public int deleteSchedule(@Param("title") String title, @Param("start_time") String start_time, @Param("end_time") String end_time);
}
