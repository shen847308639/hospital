package com.gxuwz.hospital.service;

import com.gxuwz.hospital.entity.Schedule;
import com.gxuwz.hospital.mapper.AssociationMapper;
import com.gxuwz.hospital.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private AssociationMapper associationMapper;

    public List<Schedule> findAny(String key){
        if (key == null){
            key = "";
        }
        List<Schedule> schedules = associationMapper.findAllSchedule();
        String finalKey = key;
        return schedules.stream().filter(schedule -> schedule.toString().contains(finalKey)).collect(Collectors.toList());
    }

    public boolean deleteById(int id){
        scheduleRepository.deleteById(id);
        return false;
    }

    public boolean save(Schedule schedule) {
        Schedule save = scheduleRepository.save(schedule);
        if (save!=null){
            return true;
        }
        return false;
    }

    public void update(Schedule schedule) {
        scheduleRepository.save(schedule);
    }
}
