package com.gxuwz.hospital.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gxuwz.hospital.entity.Appointment;
import com.gxuwz.hospital.entity.Schedule;
import com.gxuwz.hospital.service.AppointmentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping ("/findAppointment")
    public String toAppointment(Model model,@RequestParam(required = false) String key) throws JsonProcessingException {
        List<Appointment> appointments = appointmentService.findAny(key);
        model.addAttribute("appointments",appointments);
        return "/pages/adminPages/appointmentInfo";
    }

    @GetMapping ("/findAppointmentById")
    public String findAppointment(Model model) throws JsonProcessingException {
        List<Appointment> appointments = appointmentService.findAppointmentById();
        model.addAttribute("appointments",appointments);
        return "/pages/adminPages/appointmentInfo";
    }

    @GetMapping ("/deleteAppointment/{id}")
    public String deleteAppointment(@PathVariable("id") Integer id, HttpServletResponse httpServletResponse){
        appointmentService.deleteById(id);
        return "redirect:/findAppointment?key=";
    }

    @PostMapping("/addAppointment")
    public String addAppointment(@RequestBody Schedule schedule,Model model){
        Long count = appointmentService.save(schedule);
        model.addAttribute("count",count);
        return "redirect:/findSchedule?key=";
    }

    @PostMapping("/editAppointment")
    public String editAppointment(@RequestBody Appointment appointment){
        appointmentService.update(appointment);
        return "redirect:/findAppointment?Key=";
    }
}
