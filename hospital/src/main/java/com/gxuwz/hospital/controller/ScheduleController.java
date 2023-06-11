package com.gxuwz.hospital.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.gxuwz.hospital.entity.Schedule;
import com.gxuwz.hospital.service.DoctorService;
import com.gxuwz.hospital.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping ("/index")
    public String toIndex(Model model,@RequestParam(required = false) String key) throws JsonProcessingException {
        List<Schedule> schedules = scheduleService.findAny(key);
        JsonNode allDoctor = doctorService.findAny(null).get("doctorsJson");
        model.addAttribute("schedules",schedules);
        model.addAttribute("doctor",allDoctor);
        return "/pages/userPages/index";
    }

    @GetMapping ("/findSchedule")
    public String toSchedule(Model model,String key) throws JsonProcessingException {
        List<Schedule> schedules = scheduleService.findAny(key);
        JsonNode allDoctor = doctorService.findAny(null).get("doctorsJson");

        model.addAttribute("schedules",schedules);
        model.addAttribute("doctor",allDoctor);
        return "/pages/adminPages/scheduleInfo";
    }

    @GetMapping ("/deleteSchedule/{id}")
    public String deleteSchedule(@PathVariable("id") Integer id, HttpServletResponse httpServletResponse){
        scheduleService.deleteById(id);
        return "redirect:/findSchedule?key=";
    }

    @PostMapping("/addSchedule")
    public String addSchedule(@RequestBody Schedule schedule){
        scheduleService.save(schedule);
        return "redirect:/findSchedule?key=";
    }

    @PostMapping("/editSchedule")
    public String editSchedule(@RequestBody Schedule schedule){
        scheduleService.update(schedule);
        return "redirect:/findSchedule?Key=";
    }

}
