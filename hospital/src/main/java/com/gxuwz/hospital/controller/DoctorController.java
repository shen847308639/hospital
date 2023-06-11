package com.gxuwz.hospital.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxuwz.hospital.entity.Doctor;
import com.gxuwz.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/findDoctor")
    public String toDoctor(Model model, String key) throws JsonProcessingException {
        JsonNode rootJson = doctorService.findAny(key);

        JsonNode departmentsJson = rootJson.get("departmentsJson");
        JsonNode doctorsJson = rootJson.get("doctorsJson");

        List<Doctor> doctors = new ArrayList<>();
        if (doctorsJson != null) {
            doctors = new ObjectMapper().readValue(doctorsJson.toString(), new TypeReference<List<Doctor>>() {});
        }

        model.addAttribute("doctors",doctors);
        model.addAttribute("departmentJson",departmentsJson);
        return "/pages/adminPages/doctorInfo";
    }

    @GetMapping ("/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable("id") Integer id, HttpServletResponse httpServletResponse){
        doctorService.deleteById(id);
        return "redirect:/findDoctor?key=";
    }

    @PostMapping("/addDoctor")
    public String addDoctor(@RequestBody Doctor doctor){
        doctorService.save(doctor);
        return "redirect:/findDoctor?key=";
    }

    @PostMapping("/editDoctor")
    public String editDoctor(@RequestBody Doctor doctor){
        doctorService.update(doctor);
        return "redirect:/findDoctor?Key=" ;
    }
}
